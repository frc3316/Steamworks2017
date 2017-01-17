import socket
from dbug_result_object import DBugResult
from logger import logger

class DBugNetworking(object):
    """
    A class to send data to a UDP socket
    """

    ERROR_VALUE = DBugResult()

    def __init__(self, host, port):
        """
        :param host: The host name to be connected to.
        :param port: The port for the communication.
        """
        try:
            self._address = (socket.gethostbyname(host), port)
            logger.debug("Finally managed to get host: %r" % (self._address,))
        except:
            self._address = ("10.33.16.20", port)
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

    @staticmethod
    def format_parameter(param):
        return '{0:.2f}'.format(float(param))

    def format_data(self, result_obj):
        if result_obj is not None:
            identified = 1
        else:
            identified = 0
            result_obj = self.ERROR_VALUE

        return dict(AA=DBugNetworking.format_parameter(result_obj.azimuth_angle),
                    IOD=DBugNetworking.format_parameter(identified))

    def send_data(self, result_obj=None):

        # TODO:add docs

        formatted_data = str(self.format_data(result_obj))
        self.sock.sendto(formatted_data.replace(' ', '') + '\n', self._address)

    def send_no_data(self):
        self.send_data()