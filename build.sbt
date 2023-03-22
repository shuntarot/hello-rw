ThisBuild / scalaVersion := "2.13.10"
ThisBuild / version := "0.1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(
    name := "hello",
    libraryDependencies ++= Seq(
      "com.xilinx.rapidwright" % "rapidwright" % "2022.2.2"
    )
  )
