from dbug_contour import DbugContour
import cv2


class DBugAbstractImage(object):
    """
    The abstract class of an image (e.g: frame taken from a camera), all types of images should inherit from
    this class.
    """

    def __init__(self, cv2_image):
        """
        :param cv2_image: The image (a CV2 image, which is a numpy array) this instance will represent
        """
        self.image = cv2_image


class DBugColorImage(DBugAbstractImage):
    """
    a DBugColorImage instance represents an image with colors.
    This class helps interact and extract data from the underling image represented as an OpenCV image.
    """

    def filter_with_colors(self, lower_thresh, upper_thresh):
        """
        Filters self.image with the given rgb thresh values
        :param lower_thresh: the lower hsv thresh value (as a np.array(hsv))
        :param upper_thresh: the upper hsv thresh value (as a np.array(hsv))
        :return: A DBugBinaryImage instance. Each pixel in the returned image is white iff:
                 lower_bound <= pixel <= upper_bound.

        """
        hsv_image = cv2.cvtColor(self.image, cv2.COLOR_BGR2HSV)

        # Threshold the HSV image with the given thresh values
        return DBugBinaryImage(cv2.inRange(hsv_image, lower_thresh, upper_thresh))


class DBugBinaryImage(DBugAbstractImage):
    """
    a DBugBinaryImage instance represents an image with white or black pixels only.
    This class helps interact and extract data from the underling image represented as an OpenCV image.
    """

    def detect_contours(self):
        """
        Detects the contours in self. A contour is a set of white pixels connected to each other.
        :return: the contours that were found as cv2 contours.
        """
        (contours, _) = cv2.findContours(self.image.copy(), cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
        return [DbugContour(cv_contour=contour) for contour in contours]
