# DRM (purely hypothetical, naturally)

**see Test section at the end!**

_from the last answer (as of march 2018) here:_

https://askubuntu.com/questions/461409/how-can-i-remove-the-drm-from-an-epub-file

Wine from the repo below + default winetricks from apt-get seem to work fine, the extra repo may not be even necessary.

1. Winetricks repo - winetricks
Remove your current version of wine prior to installing the new version

        sudo add-apt-repository ppa:ricotz/unstable
        sudo apt-get update && sudo apt install wine-stable
 
    1.1 Here is another way to install 
    - [wine](http://ubuntuhandbook.org/index.php/2017/01/install-wine-2-0-ubuntu-16-04-14-04-16-10/)
    - [winetricks](https://github.com/Winetricks/winetricks)
 
2. Installing ADE 2.0.1. Download
[Dotnet Framework 3.5SP1](https://www.microsoft.com/en-us/download/details.aspx?id=22) and
[ADE 2.0.1](http://www.adobe.com/support/digitaleditions/downloads.html)

        WINEPREFIX=~/.wine WINEARCH=win32 wineboot
        export WINEPREFIX=$HOME/.wine/
        winetricks -q windowscodecs && winetricks -q corefonts
        
    2.1 Run winetricks, navigate menus to set windows version to XP, something like:

        winetricks
   select default prefic-> change config -> set to winxp, then continue with
        
        wine ~/Downloads/dotnetfx35setup.exe # Will take awhile
        wine ~/Downloads/ADE_2.0_Installer.exe

3. At this point, you should open ADE and login to authorize it to allow calibre to gain your key later on.

        WINEPREFIX=$HOME/.wine/ wine DigitalEditions.exe

4. Install Python

        export WINEPREFIX=$HOME/.wine/
        winetricks python27
        
5. Install Pycrypto (from [here](http://www.voidspace.org.uk/python/modules.shtml#pycrypto)

        export WINEPREFIX=$HOME/.wine/

6. Install calibre

        sudo apt-get install calibre

7. Install DeDRM calibre plugin: download [DeDRM_tools](https://github.com/apprenticeharper/DeDRM_tools/releases)
   1. Extract the DeDRM_calibre_plugin directory inside the zipfile to anywhere
   2. Open Calibre and go into: Preferences -> Plugins -> Load Plugin from file -> Choose DeDRM_plugin.zip from the directory you extracted too.
   
8. Configure DRM Plugin
   1. In plugins (from before) search for drm or DeDRM
   2. Select Customize plugin -> Adobe Digital Editions ebooks
   3. In WINEPREFIX put in /home/YOURUSERNAME/.wine # Replace YOURUSERNAME with your username :)
   4. Select the Green Plus sign and give the key a name (not sure if this is needed)

## Test
1. Download a book to ADE by opening the .ascm file (or dragging into it). You can open ADE by running 
        
        WINEPREFIX=$HOME/.wine/ wine DigitalEditions.exe

2. Run Calibre and add a book from ~/Documents/My Digital Editions/bookname.epub

