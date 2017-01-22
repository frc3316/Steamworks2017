from constants import *


# TODO: add docs
class DBugResult(object):

    def __init__(self, bounder1=UNABLE_TO_PROC_DEFAULT_VAL, bounder2=UNABLE_TO_PROC_DEFAULT_VAL):
        if bounder1 == UNABLE_TO_PROC_DEFAULT_VAL or bounder2 == UNABLE_TO_PROC_DEFAULT_VAL:
            self.azimuth_angle = UNABLE_TO_PROC_DEFAULT_VAL
        else:
            self.azimuth_angle = 0  # TODO: get the real azimuth angle
