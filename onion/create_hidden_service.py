import logging

logger = logging.getLogger(__name__)
logging.basicConfig(level=logging.INFO)


from stem.control import Controller
controller = Controller.from_port(address="127.0.0.1", port=9051)
try:
    controller.authenticate(password="test_password")
    logger.info(f'authenticated')
except Exception as e:
    logger.info(f'failed to authenticate: {e}')
