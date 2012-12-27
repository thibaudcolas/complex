#!/usr/bin/env bash
#
# This is a Play! installation and deployment script for the complex project.
#

echo -e "*****\n***** Downloading and installing Play! framework \n*****"

# Download, unzip & move Play!
curl "http://download.playframework.org/releases/play-2.0.4.zip" > ~/play-2.0.4.zip
unzip ~/play-2.0.4.zip
mv ~/play-2.0.4 ~/.play
rm ~/play-2.0.4.zip

# Add PLAY_HOME & update PATH environment variable in .bashrc
echo "export PLAY_HOME=~/.play" >> ~/.bashrc
echo "export PATH=$PATH:$PLAY_HOME" >> ~/.bashrc
source ~/.bashrc

echo -e "*****\n***** Play! succesfully installed. > which play : \n*****"
which play


echo -e "*****\n***** Downloading the complex project \n*****"

# Downloads the project from git & Jena dependencies
git clone git@github.com:masalthunlass/complex.git

echo -e "*****\n***** Downloading and configuring the Jena dependencies \n*****"

curl "https://archive.apache.org/dist/jena/binaries/apache-jena-2.7.3.zip" > ~/apache-jena-2.7.3.zip
curl "https://archive.apache.org/dist/jena/binaries/jena-sdb-1.3.5-distribution.zip" > ~/jena-sdb-1.3.5.zip
unzip ~/apache-jena-2.7.3.zip
unzip ~/jena-sdb-1.3.5.zip
mkdir ~/complex/lib
mv ~/apache-jena-2.7.3/lib/*.jar ~/complex/lib
mv ~/jena-sdb-1.3.5/lib/jena-sdb-1.3.5.jar ~/complex/lib/jena-sdb-1.3.5.jar
rm ~/apache-jena-2.7.3.zip
rm ~/jena-sdb-1.3.5.zip
rm -rf ~/apache-jena-2.7.3
rm -rf ~/jena-sdb-1.3.5

echo -e "*****\n***** complex has been downloaded at ~/complex. > play eclipsify : \n*****"

# Run eclipsify for our project
cd complex
play eclipsify

echo -e "*****\n***** Done ! \n*****"
