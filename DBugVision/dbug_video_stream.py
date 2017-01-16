from dbug_image import DBugColorImage
from glob import glob


class DBugVideoStream(object):
    #  TODO: add docs

    def __init__(self, camera_device_index):
        pass

    def get_image(self):
        return DBugColorImage()

    @staticmethod
    def get_camera_usb_device_index():
        """
        finds the camera usb device index from /dev
        return: Int, the usb device index of the camera that is currently connected
        """
        video_devices_names = glob("/dev/video*")
        if video_devices_names:
            first_cam_name = video_devices_names[0]
            cam_index_start_index = first_cam_name.find("video")
            return int(first_cam_name[cam_index_start_index + len("video"):])
        else:
            return 0  # Default camera value
