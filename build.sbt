name := "Akka-Chat-Room"

version := "0.1"

scalaVersion := "2.11.7"

libraryDependencies ++= {
  val akkaV = "2.4.0"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV
  )
}