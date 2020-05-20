# Virtual Environment and Useful Requirements
Create python virtual environment and install packages
and libraries to serve common data science needs:
- jupyter notebook
- dev tools: style, validation, testing
- flask web application engine
- matplotlib graphics
- bokeh graphics
- sql server API
- postgreSQL API
- mongo API

# System Requirements
You need superuser access for this. Make sure that these are installed.

    sudo apt-get update
    sudo apt-get install build-essential
    sudo apt-get install python3-dev
  

# Basic Virtualenv

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


# Development Tools

    pip install pylint
    pip install yapf
    pip install pytest
    pip install pytest-xdist


# Jupyter Notebook

    source venv/bin/activate
    pip install jupyter


# Basics

    pip install pytz
    pip install progressbar2
    pip install numpy
    pip install pandas
    pip install Bottleneck
    pip install requests
    pip install scipy


# Matplot
    
    pip install matplotlib


# Flask Web Engine

    pip install Flask
    pip install flask_restful
    
Test with

    FLASK_APP=flask_hello.py flask run


# Bokeh

    pip install bokeh


# Databases
We (mostly) assume that you handled system-wide driver installation 
and source configuration like freetds and odbc. Instructions are 
available elsewhere, see for example 
[here](https://github.com/kukarzev/howto/blob/master/sql_server_linux.md).

    pip install pymongo
    pip install pyodbc
    pip install sqlalchemy
    
    # postgreSQL (may be finicky)
    sudo apt-get update
    sudo aptitude install libpq-dev
    pip install psycopg2
    

# Additional Packages
To enable most of existing LS python code and typical needs.
**NOTE:** do not install bson explicitly - pymongo will pull its own!

    pip install configparser
    pip install dask
    pip install lxml
    pip install nltk
    pip install sklearn
    pip install tesseract pytesseract
    pip install s3fs s3transfer
    pip install seaborn
    pip install statsmodels
    pip install sudospawner
    pip install xlrd
    pip install torch
    pip install beautifulsoup4
    pip install toolz
    pip install python-Levenshtein
    pip install tensorflow
    
    
# Various Upgrades
Try to upgrade all packages to latest versions

    pip install pip-upgrader
    pip-upgrade
    
Sometimes when installing from a rather stale requirements.txt, you get an error message. There are a few common reasons:
- some system component is not installed, check things like python-dev and read error message carefully
- newer system components or other packages made an older version of the package that you are installing obsolete. In this case, edit the requirements file and specify a different version or, better yet, change == to >=

**In general, installing from a requirements file seems like a bad idea because versions disappear, and you cannot install anything after a while. It is better to keep a list of packages you need and reinstall them from scratch.**
