
class DbugContour(object):
    """
    The Dbug rapper for a cv2 contour
    """
    def __init__(self, cv_contour):
        """
        :param cv_contour: The cv2 contour that self is wrapping
        """
        self.contour = cv_contour
