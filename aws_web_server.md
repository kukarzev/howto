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

2) 
