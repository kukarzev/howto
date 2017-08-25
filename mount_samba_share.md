# How To Mount a Samba Share 
[Taken from https://wiki.ubuntu.com/MountWindowsSharesPermanently](https://wiki.ubuntu.com/MountWindowsSharesPermanently)

## CIFS installation

    sudo apt-get install cifs-utils
    
## Mount password protected network folders
The quickest way to auto-mounting a password-protected share is to edit /etc/fstab (with root privileges), to add this line:

    //servername/sharename  /media/windowsshare  cifs  username=msusername,password=mspassword,iocharset=utf8,sec=ntlm  0  0

This is not a good idea however: /etc/fstab is readable by everyone and so is your Windows password in it. The way around this is to use a credentials file. This is a file that contains just the username and password.

Using a text editor, create a file for your remote servers logon credential:

    gedit ~/.smbcredentials
    
Enter your Windows username and password in the file:

    username=msusername
    password=mspassword

Save the file, exit the editor.

Change the permissions of the file to prevent unwanted access to your credentials:

    chmod 600 ~/.smbcredentials

Then edit your /etc/fstab file (with root privileges) to add this line (replacing the insecure line in the example above, if you added it):

    //servername/sharename /media/windowsshare cifs credentials=/home/ubuntuusername/.smbcredentials,iocharset=utf8,sec=ntlm 0 0 

Save the file, exit the editor.

Finally, test the fstab entry by issuing:

    sudo mount -a

If there are no errors, you should test how it works after a reboot. Your remote share should mount automatically.
