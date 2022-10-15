#!/bin/bash
# this script runs on the server
# it stops the server, compiles the java, 
#    replaces the systemd file, and restarts
#

# get the java ready
rm -rf $HOME/.m2
mvn clean
mvn compile
mvn package
echo "finished java compile"
# kill the running service
echo systemctl --user stop  ${1}.service
systemctl --user stop  ${1}.service
# replace the systemd file
echo "replace the service file"
mkdir --parents $HOME/.config/systemd/user
cp -f scripts/${1}.service $HOME/.config/systemd/user/${1}.service
systemctl --user daemon-reload
# restart the running service
systemctl --user start  ${1}.service
#systemctl --user enable  ${1}.service
systemctl --user status ${1}.service
echo "done"

