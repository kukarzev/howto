# Git Workflow and Useful Tricks 

# SSH Forwarding to Another User
1) Make sure the necessary environment is transferred via
sudo. In sudoers, add

        sudo visudo
        # keep ssh credentials when su into another user
        Defaults    env_keep+=SSH_AUTH_SOCK
        
2) If you want to forward SSH credentials to another user
via sudo su user on the same server, by default another
user cannot read the ssh-agent temp files. You can do this
(not safe - other users can sudo into user and get your ssh
auth):

        setfacl -m anotheruser:x   $(dirname "$SSH_AUTH_SOCK")
        setfacl -m anotheruser:rwx "$SSH_AUTH_SOCK"
  
 3) Check ssh with github:
 
        sudo -u anotheruser ssh -T git@github.com
  
 4) Now you can sudo and use git:
 
        sudo su anotheruser
        git clone git@github.com:kukarzev/howto.git
