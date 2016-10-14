name := "spark-simple-job"

organization := "org.stratio"

version := "0.0.1"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % "1.12.1" % "test" withSources() withJavadoc(),
  "org.apache.spark" % "spark-core_2.11" % "2.0.0"
)

initialCommands := "import org.stratio.sparksimplejob._"

