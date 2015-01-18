#!/bin/bash

export GRADLE_OPTS="-XX:MaxPermSize=2048m"
BUILD_TYPE=${1:?"usage: <build_type>: all/mr/scalding/spark"}
IS_UBER_JAR=${2:-false}

build-all() {
    ./gradlew clean build
}

build-mapreduce() {
  if [ $IS_UBER_JAR == "true" ]
  then
    ./gradlew -p job-runner-mapreduce clean build uberjar
  else
    ./gradlew -p job-runner-mapreduce clean build
  fi
}

build-scalding() {
   if [ $IS_UBER_JAR == "true" ]
   then
     ./gradlew -p job-runner-scalding clean build uberjar
   else
     ./gradlew -p job-runner-scalding clean build
   fi
}

build-spark() {
   if [ $IS_UBER_JAR == "true" ]
   then
     ./gradlew -p job-runner-spark clean build uberjar
   else
     ./gradlew -p job-runner-spark clean build
   fi
}

if [ $BUILD_TYPE == "all" ]
then
    build-mapreduce $IS_UBER_JAR
elif [ $BUILD_TYPE == "mr" ]
then
    build-mapreduce $IS_UBER_JAR
elif [ $BUILD_TYPE == "scalding" ]
then
    build-scalding $IS_UBER_JAR
elif [ $BUILD_TYPE == "spark" ]
then
    build-spark $IS_UBER_JAR
else
    echo "unsupported build type"
fi