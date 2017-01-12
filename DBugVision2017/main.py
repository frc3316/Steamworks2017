# Imports:

import constants
import dbug_networking
import dbug_video_stream


# NOTE: For Barak's QA - is this the right place to write such function?
def filter_sort_contours(contours):
    pass

if __name__ == "__main__":

    # TODO: Create a lock file to be sure that no other process is currently processing and sending data to the robot

    # The communication channel to the robot
    robot_com = dbug_networking.DBugNetworking()
    # Video stream to get the images from
    cam = dbug_video_stream.DBugVideoStream(camera_device_index=constants.CAMERA_INDEX)

    # Capture images non-stop, process them and send the results to the robot
    while True:

        # Making sure the vision process does not crash
        try:

            image = cam.get_image()
            binary_image = image.filter_with_colors(constants.LOWER_BOUND, constants.UPPER_BOUND)
            unfiltered_contours = binary_image.detect_contours()
            filtered_contours = filter_sort_contours(unfiltered_contours)

            # TODO: Get the 2 contours, calculate center, send to robot

        except Exception as error:
            print "An Error Occurred: " + str(error)
            print "Skipping to the next frame!"