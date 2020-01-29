name := "scala-slack-client"

version := "0.1"

scalaVersion := "2.12.8"

scalacOptions += "-Ypartial-unification"

libraryDependencies ++= {

  Seq(
    "org.scalaj" %% "scalaj-http" % "2.4.2"
  )
}
