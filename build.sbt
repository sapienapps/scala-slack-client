name := "scala-slack-client"

version := "0.1.2"
organization := "com.sapienapps"

githubOwner := "sapienapps"
githubRepository := "scala-slack-client"

lazy val scala212 = "2.12.16"
lazy val scala213 = "2.13.10"
lazy val supportedScalaVersions = List(scala212, scala213)

scalaVersion := scala213

libraryDependencies ++= {
  Seq(
    "org.scalaj" %% "scalaj-http" % "2.4.2"
  )
}
