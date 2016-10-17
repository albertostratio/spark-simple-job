# Spark job

This project includes a Spark job that performs <something with HDFS>
 
This is meant to be used as a Metronome scheduled job within the Stratio PaaS.
 
# Instructions 

To use this project, please follow the steps described below:

1. You have to compile and package the code by executing: sbt package. This will generate a "fat jar" that will be copied
   within the docker image.
   
2. Build the docker image by executing: docker build .
3. Push the image to docker hub: docker push <dockerhubrepo> 
4. Execute the job from metronome. See following a job definition example:

```
{
  "description": "WordCountJob",
  "id": "word-count",
  "labels": {
    "location": "olympus",
    "owner": "zeus"
  },
  "run": {
    "cmd": "/opt/spark/dist/bin/spark-submit --master mesos://zk://zk-1.zk:2181,zk-2.zk:2181,zk-3.zk:2181,zk-4.zk:2181,zk-5.zk:2181/mesos 
            --name WordCountJob --conf spark.mesos.coarse=true --conf spark.cores.max=4 --conf spark.driver.cores=1 --conf spark.driver.memory=1g 
            --conf spark.driver.supervise=false --conf spark.executor.cores=2 --conf spark.executor.memory=1g 
            --conf spark.executor.home=/opt/spark/dist --conf spark.mesos.executor.docker.image=ardlema/spark-job:latest 
            --conf spark.ssl.noCertVerification=true 
            --class org.stratio.sparksimplejob.WordCount /opt/spark/dist/examples/jars/spark-simple-job_2.11-0.0.1.jar 30",
    "cpus": 1,
    "mem": 1024,
    "disk": 0,
    "docker": {
      "image": "ardlema/spark-job:latest"
    },
    "env": {
      "FOO": "bar"
    },
    "user": "nobody"
  }
}
```
   