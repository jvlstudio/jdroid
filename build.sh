#!/bin/sh

BUILD_DIRECTORY=$1
USER_NAME=$2
CLEAN=$3
BRANCH=$4
PROJECT_NAME=jdroid

# Help
# ****
if [ $# -eq 1 ] && [ $1 = -h ]
then
        echo "Help"
        echo "****"
        echo ""
        echo "This script will build the application."
        echo "Available parameters"
        echo ""
        echo " 1) The path to a directory where the code will be checked out and the assemblies would be generated. For example: /home/user/build. Required."
        echo ""
        echo " 2) The Git user name used to checkout the code. Required."
        echo ""
        echo " 3) Whether the source code and assemblies on the build directory should be cleaned or not. Required. Default value: true"
        echo ""
        echo " 4) The branch from where check out the code. Optional. Default value: master"
        echo ""
        exit 0
fi

# Parameters validation
# ************************
if [ -z "$BUILD_DIRECTORY" ]
then
	echo "[ERROR] The BUILD_DIRECTORY parameter is required"
	echo "Run the script with '-h' for help"
	exit 1;
fi

if [ ! -d "$BUILD_DIRECTORY" ]
then
	echo "[ERROR] - The BUILD_DIRECTORY directory does not exist."
	echo "Run the script with '-h' for help"
	exit 1;
fi

if [ -z "$USER_NAME" ]
then
	echo "[ERROR] The USER_NAME parameter is required"
        echo "Run the script with '-h' for help"
        exit 1
fi

if [ -z "$CLEAN" ]
then
	CLEAN="true"
fi

if [ -z "$BRANCH" ]
then
	BRANCH=master
fi

SOURCE_DIRECTORY=$BUILD_DIRECTORY/$PROJECT_NAME/source
ASSEMBLIES_DIRECTORY=$BUILD_DIRECTORY/$PROJECT_NAME/assemblies

# Cleaning
# ************************
if [ "$CLEAN" = "true" ]
then
	# Clean the directories
	rm -r -f $SOURCE_DIRECTORY
	mkdir -p $SOURCE_DIRECTORY

	rm -r -f $ASSEMBLIES_DIRECTORY
	mkdir -p $ASSEMBLIES_DIRECTORY

	# Checkout the project
	cd $SOURCE_DIRECTORY
	echo Cloning git@github.com:maxirosson/jdroid.git
	git clone git@github.com:maxirosson/jdroid.git $PROJECT_NAME

	cd $SOURCE_DIRECTORY/$PROJECT_NAME
	if [ "$BRANCH" != 'master' ] 
	then
		git checkout -b $BRANCH origin/$BRANCH --track
	fi
fi

# Assemblies Generation
# ************************

# Install the jdroid java jar
cd $SOURCE_DIRECTORY/$PROJECT_NAME/jdroid-java
mvn dependency:resolve clean install -Dmaven.test.skip=true

# Install the jdroid javaweb jar
cd $SOURCE_DIRECTORY/$PROJECT_NAME/jdroid-javaweb
mvn dependency:resolve clean install -Dmaven.test.skip=true

# Install the jdroid android apk lib
cd $SOURCE_DIRECTORY/$PROJECT_NAME/jdroid-android
mvn dependency:resolve clean install

# Install the jdroid android leftnavbar apk lib
cd $SOURCE_DIRECTORY/$PROJECT_NAME/jdroid-android-leftnavbar
mvn dependency:resolve clean install

