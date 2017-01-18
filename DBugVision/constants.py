import numpy as np

# Video Color Filtering

LOWER_BOUND = np.array([0,0,0])
UPPER_BOUND = np.array([0,0,0])

# Unable To Process Value

UNABLE_TO_PROC_DEFAULT_VAL = 3316
DEFAULT_CAMERA_USB_INDEX = 0

# Camera Settings:

BRIGHTNESS = 0.01
SATURATION = 1
EXPOSURE = 0.9
CONTRAST = 0.01
ROTATE_CLOCKWISE = False  # Should we rotate the photos received from the camera by 90 degrees
RESIZE_IMAGE_WIDTH = 320
RESIZE_IMAGE_HEIGHT = 240

# The default amount of frames to read from the camera in each update in order to clear the buffer
DEFAULT_READ_BUFFER_AMOUNT = 4

ROBORIO_MDNS = "roborio-3316-frc.local"
ROBORIO_PORT = 8000
