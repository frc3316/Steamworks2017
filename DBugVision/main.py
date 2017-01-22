# Imports:
import cv2
from constants import *
from dbug_networking import DBugNetworking
from dbug_video_stream import DBugVideoStream
from dbug_result_object import DBugResult
from logger import logger
from dbug_contour import DbugContour


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
        (x_offset, y_offset, width, height) = contour.get_rotated_enclosing_rectangle()
        if (MIN_BOUND_RECT_AREA > width*height) or (MAX_BOUND_RECT_AREA < width*height):
            logger.debug("Rect denied, min-max rect area with area: " + str(width*height))
            continue

        ratio = height / width
        if (MIN_HEIGHT_WIDTH_RATIO > ratio) or (MIN_HEIGHT_WIDTH_RATIO < ratio):
            logger.debug("Rect denied, height/width ratio")
            continue

        potential_bounders.append(contour)

    return potential_bounders


def init_vision_command():

    # The communication channel to the robot
    robot_com = DBugNetworking(host=ROBORIO_MDNS,port=ROBORIO_PORT)

    # Video stream to get the images from
    camera_device_index = DBugVideoStream.get_camera_usb_device_index()
    camera_device_index = 0 if camera_device_index is None else camera_device_index

    print("Found camera index: " + str(camera_device_index))

    if camera_device_index is None:
        cam = DBugVideoStream(camera_device_index=camera_device_index)
    else:
        cam = DBugVideoStream()

    return cam, robot_com


def run_vision_command(cam, robot_com):

    # Capture frames non-stop, process them and send the results to the robot
    while True:

        # Making sure the vision process does not crash
        try:

            frame = cam.get_image()
            binary_image = frame.filter_with_colors(LOWER_BOUND, UPPER_BOUND)
            unfiltered_contours = binary_image.detect_contours()
            filtered_contours = filter_sort_contours(unfiltered_contours)

            result_obj = None

            if len(filtered_contours) >= 2:

                result_obj = DBugResult(bounder1=filtered_contours[0],
                                        bounder2=filtered_contours[1])

            if result_obj is None or result_obj.azimuth_angle == UNABLE_TO_PROC_DEFAULT_VAL:
                robot_com.send_no_data()
                logger.warning("Couldn't find bounders... sending no data")
            else:
                robot_com.send_data(result_obj=result_obj)

            # Saving or showing image
            # TODO move this to a separate method

            if SHOULD_SHOW_GUI_IMAGES:

                # Display the image
                cv2_contours = [c.contour for c in filtered_contours[:2]]
                copy_image = frame.image.copy()
                cv2.drawContours(copy_image, cv2_contours, -1, (0,255,0), 1)
                cv2.imshow("Original", copy_image)

        except Exception as error:

            # MARK: Possible Exception and reasons:
            # 1. Example Exception - Reason For Exception

            print "An Error Occurred: " + str(error)
            print "Skipping to the next frame!"

if __name__ == "__main__":

    # TODO: Create a lock file to be sure that no other process is currently processing and sending data to the robot

    cam, robot_com = init_vision_command()

    run_vision_command(cam, robot_com)
