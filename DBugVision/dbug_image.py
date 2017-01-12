from dbug_contour import DbugContour


class DBugAbstractImage(object):
    #  TODO: add docs
    def __init__(self, image):
        pass


class DBugColorImage(DBugAbstractImage):
    """
    a DBugColorImage instance represents an image with colors.
    This class helps interact and extract data from the underling image represented as an OpenCV image.
    """

    def filter_with_colors(self, lower_bound, upper_bound):
        return DBugBinaryImage()


class DBugBinaryImage(DBugAbstractImage):
    """
    a DBugBinaryImage instance represents an image with white or black pixels only.
    This class helps interact and extract data from the underling image represented as an OpenCV image.
    """

    def detect_contours(self):
        return [DbugContour(), DbugContour()]