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
