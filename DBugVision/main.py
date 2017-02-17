# Imports:
import argparse
import traceback

from cv2 import destroyAllWindows
from constants import *
from dbug_networking import DBugNetworking
from dbug_video_stream import DBugVideoStream
from dbug_result_object import DBugResult
from dbug_lock_file import LockFile
from logger import logger
from dbug_contour import DbugContour


def parse_arguments():
    parser = argparse.ArgumentParser()
    parser.add_argument("--host", type=str, default=ROBORIO_MDNS,
                        help="Roborio host address")
    parser.add_argument("--port", type=int, default=ROBORIO_PORT,
                        help="Roborio host port")
    parser.add_argument("--dump_image", action="store_true", default=False,
                        help="Dump images to current working directory")
    parser.add_argument("--enable_network", action="store_true", default=True,
                        help="Enable network communications")
    return parser.parse_args()


def filter_sort_contours(contours):
    """
    Filters the given list of contours and sorting them according to the probability of being one of the bounders
    (a bounder is one of the 2 reflective tapes we use to find the peg)
    :param contours: The list of DbugContours to filter and sort
    :return: A new list of DbugContours that are sorted according to the probability of being one of the bounders.
             This list may not contain all the original DbugContours in contours, since we filter the given list for
             Better performance.
    """
    potential_bounders = []
    for contour in contours:
        (x_offset, y_offset, width, height) = contour.straight_enclosing_rectangle()
        if not (MIN_BOUND_RECT_AREA < contour.contour_area() < MAX_BOUND_RECT_AREA):
            logger.debug("Rect denied, contour min-max area, contour area is: " + str(width*height))
            continue

        ratio = float(height) / float(width)
        if (MIN_HEIGHT_WIDTH_RATIO > ratio) or (MAX_HEIGHT_WIDTH_RATIO < ratio):
            logger.debug("Rect denied, height/width ratio")
            continue

        potential_bounders.append(contour)

    potential_bounders.sort(key=lambda cnt: cnt.contour_area(), reverse=True)

    return potential_bounders


def init_vision_command(args):

    # The communication channel to the robot
    robot_com = DBugNetworking(host=args.host,port=args.port)

    # Video stream to get the images from
    camera_device_index = DBugVideoStream.get_camera_usb_device_index()

    logger.debug("Found camera index: " + str(camera_device_index))

    if camera_device_index is not None:
        cam = DBugVideoStream(camera_device_index=camera_device_index)
    else:
        cam = DBugVideoStream()

    return cam, robot_com


def run_vision_command(cam, robot_com, args):

    frames_processed = 0
    # Capture frames non-stop, process them and send the results to the robot
    while True:

        # Making sure the vision process does not crash
        try:

            frame = cam.get_image()
            frames_processed += 1

            binary_image = frame.filter_with_colors(LOWER_BOUND, UPPER_BOUND)
            unfiltered_contours = binary_image.detect_contours()
            filtered_contours = filter_sort_contours(unfiltered_contours)

            result_obj = None

            if len(filtered_contours) >= 2:

                result_obj = DBugResult(bounder1=filtered_contours[0],
                                        bounder2=filtered_contours[1])

            if args.enable_network:

                if result_obj is None or result_obj.azimuth_angle == UNABLE_TO_PROC_DEFAULT_VAL:
                    robot_com.send_no_data()
                    logger.warning("Couldn't find bounders... sending no data")
                else:
                    robot_com.send_data(result_obj=result_obj)

            # Saving or showing image

            if args.dump_image and frames_processed % DUMP_IMAGE_EVERY_FRAMES == 0:

                # Filtered contours:
                copy_image = frame.copy()
                copy_image.draw_contours(filtered_contours, line_color=(255,255,0))

                # Rotated enclosing rectangles on bounders:

                for bounder in filtered_contours[:2]:
                    copy_image.draw_rotated_enclosing_rectangle(contour=bounder, line_color=(0,0,255))
                    copy_image.draw_contours([bounder], line_color=(255,0,0))

                # Merged contour of bounders:

                merged_contour = filtered_contours[0] + filtered_contours[1]
                copy_image.draw_rotated_enclosing_rectangle(contour=merged_contour, line_color=(0,255,0))

                # Angle text:

                copy_image.draw_text('AA: ' + str(result_obj.azimuth_angle), origin=(30,30))

                # Finally save the frames:
                logger.debug("Writing frame to path: %s", DUMP_IMAGE_PATH)
                copy_image.save_to_path(path=DUMP_IMAGE_PATH)

        except Exception as ex:

            # MARK: Possible Exception and reasons:
            # 1. Example Exception - Reason For Exception
            logger.error("Unhandled Exception:\n" + traceback.format_exc())
            logger.debug("Skipping to the next frame!")

            raise

        finally:
            destroyAllWindows()
            cam.cam.release()
        if robot_com.sock is not None:
            robot_com.sock.close()

if __name__ == "__main__":

    lock_file = LockFile("dbug-computer-vision-process.loc")

    if lock_file.is_locked():
        raise ValueError("Lock File Locked!")
    else:
        lock_file.lock()

    args = parse_arguments()

    cam, robot_com = init_vision_command(args)

    run_vision_command(cam, robot_com, args)
