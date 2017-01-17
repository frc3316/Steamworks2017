# Imports:

from constants import *
from dbug_networking import DBugNetworking
from dbug_video_stream import DBugVideoStream
from dbug_result_object import DBugResult
from dbug_contour import DbugContour


# NOTE: For Barak's QA - is this the right place to write such function?
def filter_sort_contours(contours):
    """
    Filters the given list of contours and sorting them according to the probability of being one of the bounders
    (a bounder is one of the 2 reflective tapes we use to find the peg)
    :param contours: The list of DbugContours to filter and sort
    :return: A new list of DbugContours that are sorted according to the probability of being one of the bounders.
             This list may not contain all the original DbugContours in contours, since we filter the given list for
             Better performance.
    """

    return []


def init_vision_command():

    # The communication channel to the robot
    robot_com = DBugNetworking()
    # Video stream to get the images from
    cam = DBugVideoStream(camera_device_index=DBugVideoStream.get_camera_usb_device_index())

    return cam, robot_com


def run_vision_command(cam, robot_com):
    # Capture images non-stop, process them and send the results to the robot
    while True:

        # Making sure the vision process does not crash
        try:

            image = cam.get_image()
            binary_image = image.filter_with_colors(LOWER_BOUND, UPPER_BOUND)
            unfiltered_contours = binary_image.detect_contours()
            filtered_contours = filter_sort_contours(unfiltered_contours)

            result_obj = None

            if len(filtered_contours) >=2:
                result_obj = DBugResult(bounder1=filtered_contours[0],
                                        bounder2=filtered_contours[1])

            if result_obj is None or result_obj.azimuth_angle == UNABLE_TO_PROC_DEFULT_VAL:
                robot_com.send_no_data()
            else:
                robot_com.send_data(result_obj=result_obj)

            # TODO: Get the 2 contours, calculate center, send to robot

        except Exception as error:

            # MARK: Possible Exception and reasons:
            # 1. Example Exception - Reason For Exception

            print "An Error Occurred: " + str(error)
            print "Skipping to the next frame!"

if __name__ == "__main__":

    # TODO: Create a lock file to be sure that no other process is currently processing and sending data to the robot

    cam, robot_com = init_vision_command()

    run_vision_command(cam, robot_com)
