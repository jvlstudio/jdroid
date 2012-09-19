#!/bin/sh

# jdroid java
cd jdroid-java
mvn dependency:resolve clean install -Dmaven.test.skip=true
cd ..

# jdroid javaweb
cd jdroid-javaweb
mvn dependency:resolve clean install -Dmaven.test.skip=true
cd ..

# jdroid android
cd jdroid-android
mvn dependency:resolve
cd ..

# jdroid android leftnavbar
cd jdroid-android-leftnavbar
mvn dependency:resolve
cd ..
