package org.stratio.sparksimplejob

import java.util.Properties

import com.databricks.spark.avro._
import org.apache.spark.sql.{SaveMode, SparkSession}

object WordCount {
  def main(args: Array[String]) {
    if(args.size != 5) {
      println("wordcount.jar <hdfsPath> <postgresUser> <postgresPassword> <postgresConnectionChain> <postgresTableName>")
      System.exit(1)
    }

    val sparkSession = SparkSession.builder().getOrCreate()

    val hdfsPath = args(0)
    val postgresUser = args(1)
    val postgresPassword = args(2)
    val postgresConnectionChain = args(3)
    val postgresTableName = args(4)

    val df = sparkSession.read.avro(hdfsPath)

    df.printSchema()
    df.createOrReplaceTempView("data")

    val properties = new Properties()
    properties.put("user", postgresUser)
    properties.put("password", postgresPassword)

    sparkSession
      .sql("select count(*) as counts from data")
      .write.mode(SaveMode.Overwrite)
      .jdbc(postgresConnectionChain, postgresTableName, properties)

  }
}

