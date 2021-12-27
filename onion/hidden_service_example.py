from stem.control import Controller
from flask import Flask
import logging

logger = logging.getLogger(__name__)

logging.basicConfig(level=logging.INFO)

if __name__ == "__main__":

    app = Flask("example")
    port = 5000
    host = "127.0.0.1"
    hidden_svc_dir = "/var/lib/tor/hidden_service"

    @app.route('/')
    def index():
        return "<h1>Tor works!</h1>"
    print(" * Getting controller")
    controller = Controller.from_port(address="127.0.0.1", port=9051)
    try:
        controller.authenticate(password="test_password")
        controller.set_options([
            ("HiddenServiceDir", hidden_svc_dir),
            ("HiddenServicePort", "80 %s:%s" % (host, str(port)))
            ])
        #svc_name = open(hidden_svc_dir + "/hostname", "r").read().strip()
        svc_name = '7tcowwy2zjdfed4vdmooh2267i2qxzgw6jldgky7rmhnpoaxth5wahad.onion'
        print(" * Created host: %s" % svc_name)
    except Exception as e:
        logger.info(f'failed to create host, exception: {e}')
    app.run()
