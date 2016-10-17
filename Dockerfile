FROM mesosphere/spark:1.0.2-2.0.0

ADD "target/scala-2.11/spark-simple-job_2.11-0.0.1.jar" /opt/spark/dist/examples/jars/

