name := "Scopt4"

version := "0.1"
scalaVersion := "2.12.10"
val sparkVersion = "3.0.0"

lazy val ScoptVersion = "4.0.0"

libraryDependencies ++= Seq(
  "org.apache.spark"              %% "spark-sql"      % sparkVersion % "provided",
  "com.github.scopt" %% "scopt" % ScoptVersion
)
