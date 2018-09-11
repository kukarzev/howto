# Create Python Virtual Environment

    python3 -m venv --without-pip venv
    source venv/bin/activate
    wget https://bootstrap.pypa.io/get-pip.py
    python3 get-pip.py
    rm get-pip.py 
    pip install -U pip
    deactivate
    
Activate whenever you need:

    source venv/bin/activate
    
When activated, install packages with pip:

    pip install mypackage
    
# Various Upgrades
Try to upgrade all packages to latest versions

    pip install pip-upgrader
    pip-upgrade
    
Sometimes when installing from a rather stale requirements.txt, you get an error message. There are a few common reasons:
- some system component is not installed, check things like python-dev and read error message carefully
- newer system components or other packages made an older version of the package that you are installing obsolete. In this case, edit the requirements file and specify a different version or, better yet, change == to >=
