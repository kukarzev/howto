# Set up RTSP server on Raspberry Pi

## Summary
- Using v4l2rtspserver starting [here](https://kevinsaye.wordpress.com/2018/10/17/making-a-rtsp-server-out-of-a-raspberry-pi-in-15-minutes-or-less/)
- Tried the most popular precscription but it is slow: [Motion](https://pimylifeup.com/raspberry-pi-webcam-server/)
  - See also more about motion [Motion config](https://motion-project.github.io/motion_config.html)
  
## Using v4l2rtspserver 
[Started here.](https://kevinsaye.wordpress.com/2018/10/17/making-a-rtsp-server-out-of-a-raspberry-pi-in-15-minutes-or-less/)

Note that starting without explicit video parameters like height and width and frame rate does not produce a stream. Need to do something like

```
sudo v4l2rtspserver -H 480 -W 640 -F 15 -P 8554 /dev/video0
```

The above works fine, can be viewed in VLC as [rtsp://192.168.1.150:8554/unicast](rtsp://192.168.1.150:8554/unicast)

To run on startup,

```
sudo systemctl enable v4l2rtspserver
sudo emacs /lib/systemd/system/v4l2rtspserver.service
sudo systemctl start v4l2rtspserver
```

(`sudo service v4l2rtspserver start` works too)

Now need to figure out how to view it in browser and broadcast outside local network...

## Broadcasting video outside local network

In the router (Linksys) go to gaming -> set up single port forwarding. Both internal and external ports are 8554, the internal IP address of the raspberry PI with the camera (192.168.1.150)

RTSP stream can be seen externally as `rtsp://public_IP:8554/unicast`

## Notes

[Disable Raspberry Pi's wifi power management](https://thepihut.com/blogs/raspberry-pi-tutorials/disable-wifi-power-management)
