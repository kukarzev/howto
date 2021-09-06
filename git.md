# Git Workflow and Useful Tricks 

# Typical Workflow
Starting with master branch
```
# make sure you have the latest version of master
git checkout master
git pull --rebase origin master

# create your branch
git checkout -b my_branch

# do the work and commit to branch
git add ...
git commit -m 'my changes'

# rebase your changes on top of all master updates
git checkout master
git pull --rebase origin master
git rebase master my_branch

# merge into master
git checkout master
git merge --no-ff my_branch

# push to remote and delete branch
git push origin master
git branch -d my_branch
```

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
