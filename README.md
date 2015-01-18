WORK IN PROGRESS !!! 
=============
Service for submitting apps to cluster (through Ambari Service)

Usage: ./build.sh <mr/scalding/spark/all> <uberjar: true/false>

Example
=============
```
# build scalding uberjar
./build.sh scalding true

java -cp myJobJar.jar:job-runner-scalding-0.1.jar com.sequenceiq.jobrunner.ScaldingRunner com.example.MyJob --input /sample --output /samlpleOutput
```

