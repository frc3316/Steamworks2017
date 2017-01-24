import numpy as np

# Video Color Filtering
LOWER_BOUND = np.array([100,0,0])
UPPER_BOUND = np.array([150,255,255])

# Unable To Process Value

UNABLE_TO_PROC_DEFAULT_VAL = 3316
DEFAULT_CAMERA_USB_INDEX = 0

# Camera Settings:

CAMERA_VIEW_ANGLE_Y = 59.8  # in degrees
CAMERA_VIEW_ANGLE_X = 35.2  # in degrees

BRIGHTNESS = 0.01
SATURATION = 1
EXPOSURE = 0.9
CONTRAST = 0.01

_BASE_IMAGE_WIDTH = 240
_BASE_IMAGE_HEIGHT = 320

ROTATE_CLOCKWISE = False  # Should we rotate the photos received from the camera by 90 degrees.
if ROTATE_CLOCKWISE:
    RESIZE_IMAGE_WIDTH = _BASE_IMAGE_WIDTH  # The frame width we want to capture from the camera device
    RESIZE_IMAGE_HEIGHT = _BASE_IMAGE_HEIGHT  # The frame height we want to capture from the camera device
else:
    RESIZE_IMAGE_WIDTH = _BASE_IMAGE_HEIGHT  # The frame width we want to capture from the camera device
    RESIZE_IMAGE_HEIGHT = _BASE_IMAGE_WIDTH  # The frame height we want to capture from the camera device

# The default amount of frames to read from the camera in each update in order to clear the buffer
DEFAULT_READ_BUFFER_AMOUNT = 4

ROBORIO_MDNS = "roborio-3316-frc.local"
ROBORIO_PORT = 8000

SHOULD_SHOW_GUI_IMAGES = True

# Contour Filtering:

# The minimum/max area of a contour to be considered as a potential bounder
# The area should be calculated using cv2.contourArea
MIN_BOUND_RECT_AREA = 550
MAX_BOUND_RECT_AREA = 4800

# Only contours with height/width ration between those values are considered as potential bounders
MAX_HEIGHT_WIDTH_RATIO = 100
MIN_HEIGHT_WIDTH_RATIO = 0
