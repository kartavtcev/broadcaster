ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
lazy val root = (project in file("."))
  .settings(
    name := "broadcaster",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "io.monix" %% "monix" % "3.0.0-RC1",
      "io.monix" %% "monix-nio" % "0.0.3" // monix-nio supports Monix 3 RC1 only so far. No support for RC2 yet.
    )
  )