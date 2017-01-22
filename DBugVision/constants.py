import numpy as np

# Video Color Filtering

LOWER_BOUND = np.array([0,0,0])
UPPER_BOUND = np.array([10,10,10])

# Unable To Process Value

UNABLE_TO_PROC_DEFAULT_VAL = 3316
DEFAULT_CAMERA_USB_INDEX = 0

# Camera Settings:

BRIGHTNESS = 0.01
SATURATION = 1
EXPOSURE = 0.9
CONTRAST = 0.01
ROTATE_CLOCKWISE = False  # Should we rotate the photos received from the camera by 90 degrees
RESIZE_IMAGE_WIDTH = 320  # The frame width we want to capture from the camera device
RESIZE_IMAGE_HEIGHT = 240  # The frame height we want to capture from the camera device

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
MAX_HEIGHT_WIDTH_RATIO = 10
MIN_HEIGHT_WIDTH_RATIO = 0
