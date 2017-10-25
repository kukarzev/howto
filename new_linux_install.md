# Linux Desktop Config
The steps to do a fresh install and bring it to a configuration I
like. Always keep /home in a separate partition.

## KDE
1) Before install, move home directory to avoid collisions with older
config files

    mv /home/kukarzev /home/kukarzev.old
	
2) Make sure that no occasional useful configs are left in /,
e.g. freetds, /data, etc.

3) Install with manual disk management. Keep /home unformatted. Format everything else.

4) Install core packages. Move configs.

    sudo apt-get update
	sudo apt-get install emacs git
	mv kukarzev.old/.emacs.d kukarzev/
	mv /home/kukarzev.old/.gitconfig /home/kukarzev/


5) Move bin, git, github to the new home. Check and fix softlinks in
bin as needed: autorandr, ...

    cd /home/
	mv kukarzev.old/bin kukarzev/
	mv kukarzev.old/git kukarzev/
	mv kukarzev.old/github kukarzev/
	
6) Run a diff between old and new .bashrc and add relevant lines from
the old to new

7) Move autorandr profiles, check and fix their config files if
needed. Sometimes the display names change a bit, like dashes/no
dashes etc.

    mv kukarzev.old/.config/autorandr kukarzev/.config/
	
8) Install google chrome

    wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | sudo apt-key add - 
	sudo sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list'
	sudo apt-get update
	sudo apt-get install google-chrome-stable
	sudo rm /etc/apt/sources.list.d/google.list

9) Set window translucency dial to mouse wheel

    System Settings -> Window Actions -> Mouse wheel dropdown to "Change Opacity"
	
10) Install skype: download deb package from skype.com

