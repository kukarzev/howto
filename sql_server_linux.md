Connect to SQL Server from Linux
================================

Based on [http://www.bictor.com/2014/05/13/configure-unixodbc-for-ubuntu-14-04-using-freetds/](http://www.bictor.com/2014/05/13/configure-unixodbc-for-ubuntu-14-04-using-freetds/)

Special attention for the Windows Domain authentication - a few roadblocks there.

Let's set up drivers and packages to be able to communicate with SQL
Server from Linux, in particular using Python. We'll need

- FreeTDS driver
- ODBC driver
- pyodbc Python package
- whatever else may be needed



Install unixODBC and FreeTDS
----------------------------

Install packages

    sudo apt-get install unixodbc unixodbc-dev unixodbc-bin libodbc1 odbcinst1debian2 tdsodbc php5-odbc
    sudo apt-get install freetds-bin freetds-common freetds-dev libct4 libsybdb5

Find basic info about freeTDS. Note the ``freetds.conf`` directory and
TDS version. 

    tsql -C

**NOTE that tsql LIES about the TDS version, at least in
0.91! If you see 4.2 or some version smaller than 7.0, it is very
likely wrong. If you are running freetds 0.91 or later, specify TDS
version no smaller than 8.0.**



Configure FreeTDS 
----------------- 

Edit ``freetds.conf`` that you located above, most likely in
``/etc/freetds/freetds.conf``, and add the following.

    [serveralias]
            host = 172.18.13.18
            Port = 1433
            tds version = 8.0
            use ntlmv2 = yes
    
Note two things:

1) Use IP address for the server if you can. You can use domain name
but make sure that you can resolve it (e.g. with ping). If you SQL
Server, like mine, is on a Windows domain, and your linux server where
you are setting this up is not, you will not be able to resolve the
name.

2) Make sure that you include ``use ntlmv2 = yes``. This is often
necessary for the Windows domain authentication. Even if it is not,
and your server is not configured for it, it will fall back gracefully
onto default.

Test the connection (it should prompt you for password):

    tsql -S <serveralias> -U 'DOMAIN\LOGINNAME'



Configure ODBC
--------------

Find location of libtdsodbc.so (likely ``/usr/lib/x86_64-linux-gnu/odbc/libtdsodbc.so``)

    sudo find / -name libtdsodbc.so

Now open /etc/odbcinst.ini and create a new section:

    [FreeTDS]
    Description = FreeTDS Driver v0.91
    Driver = /usr/lib/x86_64-linux-gnu/odbc/libtdsodbc.so
    Setup = /usr/lib/x86_64-linux-gnu/odbc/libtdsS.so
    fileusage=1
    dontdlclose=1
    UsageCount=1
    
Now open /etc/odbc.ini and create a new connection
identifier. **NOTE** that here under ServerName you must use the
serveralias from your freetds.conf and not the IP address or server
domain name.

    [serveralias]
    Driver = FreeTDS
    Description = RCSDBDevel01
    Trace = No
    ServerName = serveralias
    Port = 1433
    Database = LPPublishing
    TDS_Version = 8.0
    UseNTLMv2 = Yes

Test with isql. Note that if you are using domain authentication, you have to spell out password, you won't be prompted.

    isql -v <serveralias> 'DOMAIN\LOGINNAME' <password>
