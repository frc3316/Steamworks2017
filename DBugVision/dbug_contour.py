from cv2 import boundingRect


class DbugContour(object):
    """
    The Dbug rapper for a cv2 contour
    """
    def __init__(self, cv_contour):
        """
        :param cv_contour: The cv2 contour that self is wrapping
        """
        self.contour = cv_contour

    def get_rotated_enclosing_rectangle(self):
        """
        Calculates and returns the minimum enclosing bounding rectangle of the contour self is wrapping
        :return: (x,y,w,h), Let (x,y) be the top-left coordinate of the rectangle and (w,h) be its width and height.
        """
        return boundingRect(self.contour)
