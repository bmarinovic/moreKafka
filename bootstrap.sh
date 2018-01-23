#!/bin/bash

# Install add-apt-repository command
sudo apt-get install software-properties-common python-software-properties

#Install Java
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
sudo apt-get install oracle-java8-installer
update-alternatives --config java

#Install Confluent platform for Kafka and Schema Registry
wget -qO - https://packages.confluent.io/deb/4.0/archive.key | sudo apt-key add -
add-apt-repository "deb [arch=amd64] https://packages.confluent.io/deb/4.0 stable main"
apt-get update && sudo apt-get install confluent-platform-oss-2.11

# Install Nuxeo
sudo apt-get install gnupg
wget -q -O- http://apt.nuxeo.org/nuxeo.key | sudo apt-key add -
sudo add-apt-repository "deb http://apt.nuxeo.org/ $(lsb_release -cs) releases"
sudo add-apt-repository "deb http://apt.nuxeo.org/ $(lsb_release -cs) fasttracks"
sudo apt-get update
sudo apt-get install nuxeo

# Limit swappiness to prevent JVM Heap being swapped to disk
sudo sysctl vm.swappiness=1
