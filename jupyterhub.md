JupyterHub Configuration and Deployment
=======================================

This repository contains a few files needed to configure and deploy
JupyterHub. This is a multiuser server setup, you probably do not need
this explicitly. This is set up for the team on one of team's servers.



Installation
------------
Adopted from 

* [Using sudo to run JupyterHub without root privileges](https://github.com/jupyter/jupyterhub/wiki/Using-sudo-to-run-JupyterHub-without-root-privileges)
* [JupyterHub: A multi-user server for Jupyter notebooks](https://github.com/jupyter/jupyterhub)


1) Install dependencies

        sudo apt-get install python3-dev
        sudo apt-get update​
        sudo apt-get install npm nodejs-legacy
        sudo apt-get install npm
        sudo npm install -g configurable-http-proxy

2) Create a dedicated jupyterhub user. It will be in charge of
spawning user servers. This way, we avoid running the whole thing as
root, which would be unsafe. This is going to be a very restricted
user: no login, no password, and sudo privileges to run exactly one
command (to be configured later).

        sudo adduser jupyterhub --disabled-login --disabled-password
        sudo adduser jupyterhub shadow

3) Clone JupiterHub configuration from git, move it to where we will
run it, and give ownership to the jupiterhub user.

        cd
        mkdir tmp
        cd tmp
        git clone git@git.renaissance.com:LearningScience/jupyterhub.git
        sudo mkdir /software
        sudo mv jupyterhub /software/
        sudo chown -R jupyterhub /software/jupyterhub
        cd ..
        rm -rf tmp

4) Configure virtualenv (as jupyterhub user because we want it to own the environment).

        sudo su jupyterhub
        cd /software/jupyterhub
        python3 -m venv --without-pip venv
        source venv/bin/activate
        wget https://bootstrap.pypa.io/get-pip.py
        python3 get-pip.py
        rm get-pip.py 
        pip install -U pip
        exit

5) Install sudospawner and jupiterhub (as jupyterhub user because we want it to own the environment).

        sudo su jupyterhub
        pip install git+https://github.com/jupyter/sudospawner
        pip install jupyterhub
        exit

6) Edit jupiterhub_config.py: make sure that the server IP address is
correct, possibly other settings like ssl certificate and key (as
jupyterhub user because we want it to own the environment).

        sudo su jupyterhub
        emacs -nw jupyterhub_config.py
        # look for c.JupyterHub.ip = '127.0.0.1'
        exit

7) Copy jupyterhub.conf to /etc/init/ so the server respawns and loads
on boot.

        sudo cp jupyterhub.conf /etc/init/

8) Give jupyterhub user specific sudo privileges.

        # copy contents of sudoers.add to sudoers file using visudo
        sudo visudo

9) Add users who are allowed to use JupiterHub to the jupyterhub group:

        sudo adduser newuser jupyterhub

10) Optionally, test the setup

        cd /software/jupyterhub/
        sudo -u jupyterhub venv/bin/jupyterhub --JupyterHub.spawner_class=sudospawner.SudoSpawner

## Update

Sometimes you need to update jupyterhub database:

    sudo su jupyterhub
    source /software/jupyterhub/venv/bin/activate
    pip install -U jupyterhub jupyter-core jupyter-client notebook
    jupyterhub update-db
    sudo service jupyterhub restart
    sudo reboot

Sometimes after a system update you need to switch to a new version of python. Reinstall packages using the old directory as a reference:

    pip install  `ls -d venv/lib/python3.4/site-packages/*/ | \
                  grep -v 'info' |grep -v '__' | \
                  awk -F '/' '{print $(NF-1)}' | \
                  tr '\n' ' '`