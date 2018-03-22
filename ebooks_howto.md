see test(!)
from the last answer (as of march 2018) here:

https://askubuntu.com/questions/461409/how-can-i-remove-the-drm-from-an-epub-file

The other answers no longer work (or did for me). Unfortunately getting it to work is convoluted and will use +5GB of space. However, this is what I did to get it working. I hope I included all the steps. It installs ADE 2.0.1

Note: wine1.7 and the version in repo for winetricks failed for installing dotdeb (required for ADE 2.0.1) so I installed the latest winetricks and wine

Winetricks repo - winetricks
I used whereis winetricks to find it and moved the downloaded winetricks to that location (for example /usr/bin/winetricks)
Wine2.0 - guide
Remove your current version of wine prior to installing the new version
sudo add-apt-repository ppa:ricotz/unstable
sudo apt-get update && sudo apt install wine-stable
Installing ADE 2.0.1
I used this guide (partially replicated here)

Files to download
Dotnet Framework 3.5SP1
ADE 2.0.1
Steps
WINEPREFIX=~/.adewine WINEARCH=win32 wineboot
export WINEPREFIX=$HOME/.adewine/
winetricks -q windowscodecs && winetricks -q corefonts
wine ~/Downloads/dotnetfx35setup.exe # Will take awhile
wine ~/Downloads/ADE_2.0_Installer.exe
At this point, you should open ADE and login to authorize it to allow calibre to gain your key later on.

WINEPREFIX=$HOME/.adewine/ wine DigitalEditions.exe

Install Python and Pycrypto for Calibre
The script for removing drm with calibre recommends python2.7 but python2.6 seems to work. For me, there was a bug where wine cannot find python.exe and has a error of

wine: cannot find L"C:\\windows\\system32\\python.exe"

I worked around that by linking it.

Install python26
export WINEPREFIX=$HOME/.adewine/
winetricks python26
cd ~/.adewine/drive_c/windows/system32 && ln -s ../../Python26/python.exe ./; cd -
Install Pycrypto
export WINEPREFIX=$HOME/.adewine/
I installed the file from here (Voidspace) and this file - PyCrypto 2.6 for Python 2.6 32bit (sig)
wine pycrypto-2.6.win32-py2.6.exe
Install calibre and drm plugin
Install calibre
sudo apt-get install calibre

Install DeDRM calibre plugin
Download DeDRM_tools from this repo

Extract the DeDRM_calibre_plugin directory inside the zipfile to anywhere
Open Calibre and go into: Preferences -> Plugins -> Load Plugin from file -> Choose DeDRM_plugin.zip from the directory you extracted too.
Configure DRM Plugin
In plugins (from before) search for drm or DeDRM
Select Customize plugin -> Adobe Digital Editions ebooks
In WINEPREFIX put in /home/YOURUSERNAME/.adewine # Replace YOURUSERNAME with your username :)
Select the Green Plus sign and give the key a name
Test
Download a book to ADE by opening the .ascm file (or dragging into it). You can open ADE by running WINEPREFIX=$HOME/.adewine/ wine DigitalEditions.exe

Run Calibre and add a book from ~/Documents/My Digital Editions/bookname.epub

shareedit
edited Apr 26 '17 at 18:42
answered Apr 26 '17 at 18:27

Miati
