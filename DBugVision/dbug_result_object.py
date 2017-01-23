from constants import *
from dbug_contour import DbugContour


class DBugResult(object):
    """
    This class represents the result of the image processing - the data that was extracted from the camera.
    This result can be later sent to the robot.
    """
    def __init__(self, bounder1=UNABLE_TO_PROC_DEFAULT_VAL, bounder2=UNABLE_TO_PROC_DEFAULT_VAL):
        if bounder1 == UNABLE_TO_PROC_DEFAULT_VAL or bounder2 == UNABLE_TO_PROC_DEFAULT_VAL:
            self.azimuth_angle = UNABLE_TO_PROC_DEFAULT_VAL
            self.bounder1 = None
            self.bounder2 = None
            self.merger_contour = None
        else:
            self.bounder1 = bounder1
            self.bounder2 = bounder2
            self.merged_contour = DbugContour.merge_contours(bounder1, bounder2)
            (x_offset, y_offset, width, height) = self.merged_contour.straight_enclosing_rectangle()
            self.azimuth_angle = DBugResult._get_azimuth_angle(object_x_offset=x_offset,
                                                               object_width=width,
                                                               frame_width=RESIZE_IMAGE_WIDTH,
                                                               viewing_angle=CAMERA_VIEW_ANGLE_X,
                                                               object_rotated_enclosing_rect_angle=self.merged_contour.min_rotated_enclosing_rectangle_angle())

    @staticmethod
    def _get_angle(offset, object_size, frame_size, viewing_angle):
        """
        Get angle from center of object to center of frame
        :param offset: offset from start of frame in pixels to the start of the object (x coord of object)
        :param object_size: size of object in pixels
        :param frame_size: size of whole frame in pixels
        :param viewing_angle: viewing angle for frame in requested coord
        :return: Angle from center of object to center of frame in requested coord with the given viewing angle.
        """
        return ((float(offset + (object_size / 2)) / frame_size) - 0.5) * viewing_angle

    @staticmethod
    def _get_azimuth_angle(object_x_offset,
                           object_width,
                           frame_width,
                           viewing_angle,
                           object_rotated_enclosing_rect_angle,
                           azimuthal_go_magic=1):
        """
        Calculates the right azimuthal angle using the angle of the rotated enclosing rect of the contour
        :param object_x_offset: offset from start of frame in pixels to start of object
        :param object_width: the width in pixels of the object
        :param frame_width: the width of the frame
        :param viewing_angle: viewing angle for frame
        :param object_rotated_enclosing_rect_angle:
        :param azimuthal_go_magic: the coefficient for the azimuth correction
        :return: The azimuthal angle of center of frame to center of object
        """
        # Normalize angle:

        if object_rotated_enclosing_rect_angle < -45:
            object_rotated_enclosing_rect_angle += 90
        if object_rotated_enclosing_rect_angle > 45:
            object_rotated_enclosing_rect_angle -= 90

        offset = ((object_rotated_enclosing_rect_angle/90.0)*frame_width/2.0) * azimuthal_go_magic
        return DBugResult._get_angle(object_x_offset, object_width + offset, frame_width, viewing_angle)
