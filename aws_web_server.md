# AWS Web Server

How to stand up a web server on AWS using one of their low tier 
instances (I was running it on t1.micro for about four years - hat 
tip to Jim Pivarski, now I am about to switch to t2.micro.)

I have a running server on t1.micro but t1 instances are at their end 
of support, I just bought a reserved t2.micro for the next three
years, and apparently there is no way to migrate t1 to t2, so I need
to recreate it.

1) Buy a reserved instance. I am going with t2.micro this time.

    AWS Console -> Instances -> Reserved Instances -> Button on top "Purchase Reserved Instances"

## Create and Launch instance

1) AWS Console -> Instances -> Instances -> Button on top "Launch Instance"
2) Choose image

        AWS Marketplace -> Search Ubuntu 16.04 -> Select Ubuntu 16.04 LTS - Xenial (HVM)
        
3) Select instance type. 

        We want to go with the previously bought t2.micro. 

        Once you've chose the image in the previous step, the next screen will show 
        pricing summary, we can ignore that because we already bought the reserved instance, 
        so click Continue.

        Select t2.micro, click configure instance details.
        
4) Configure instance details.

        Keep defaults, review just in case.
        
        Must create a subnet if no subnets are available. Click and follow instructions.
        Most likely, you'll have to start again from Select instance type step above.
        
5) Add Storage

        We need to add a disk for the OS. Default seems fine (currently, SSD GP2 8 GB)
        
        Click Add Tags.
        
6) Add Tags.

        This is probably not important but let's add a tag, which is a key-value pair.
        I added the suggested default: Name:webserver
        
        Click Next: Configure Security Group.
        
7) Configure Security Group.

        This is where firewall rules are specified. I copied my old EP2 group because
        I cannot use it directly: apparently my account does not support old EP2 virtual
        services, everything need to switch to VPC. But the rules necessary for web and SSH
        are simple, I have two lines:
        
        Type: SSH, Protocol: TCP, Port Range: 22, Source: Custom 0.0.0.0/0
        Type: HTTP, Protocol: TCP, Port Range: 80, Source: Custom 0.0.0.0/0
        
        Click Review and Launch
        
9) Review and Launch

        Review that everything is as you think you want it. I got a warning that the security
        opens my instance to the world but that is hopefully only means web and SSH, in which 
        case that's how I want it.
        
        Click Launch.
        
10) SSH key pair.

        It offers to select an SSH key pair - I selected from those that I had defined but you
        can also create a new pair. As far as I can tell, if you lose it, there is no way to regain
        access to the instance.
        
        Click Launch instances.
        
## AWS Instance Management

1) Create IAM user. This is a recommended way to manage AWS resources: as in Linux, you can use 
   the default root user for everything but it is better to create users and specify permissions etc.

[Follow these instructions: Creating Your First IAM Admin User and Group](http://docs.aws.amazon.com/IAM/latest/UserGuide/getting-started_create-admin-group.html)

**NOTE** when you create the user, make sure you save "AWS secret access key" somewhere - you will need it
for access, and it is not possible to lookup later (You can create a new one if you lose this one.)

2) Install AWS command line interface

        sudo apt-get update
        sudo apt-get install awscli
        
 3) Configure AWS CLI, you will need your user's AWS access key and secret access key. Apparently, you also 
    better specify region (which is similar to availability zone but different). E.g. region: us-east-1.
 
        aws configure
        
 4) If you are moving server from EC2 to VPN, you may need to move public IP. Here is a dry run:
 
        aws ec2 move-address-to-vpc --dry-run --public-ip 50.17.218.156
        
        
