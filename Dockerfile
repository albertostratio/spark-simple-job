FROM anistal/spark-2.0.1-2

ADD "target/scala-2.11/spark-simple-job-assembly-0.0.1.jar" /opt/spark/dist/examples/jars/spark-simple-job.jar
ADD "postgresql-9.1-901.jdbc4.jar" /opt/spark/dist/examples/jars/
