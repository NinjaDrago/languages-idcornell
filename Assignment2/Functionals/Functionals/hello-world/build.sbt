ThisBuild / scalaVersion := "2.13.12" // Adjust according to your Scala version

lazy val root = (project in file("."))
  .settings(
    name := "HelloWorldServer",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor-typed" % "2.6.20",
      "com.typesafe.akka" %% "akka-http" % "10.2.10",
      "de.heikoseeberger" %% "akka-http-circe" % "1.39.2", // Optional: for Circe support
      "io.circe" %% "circe-core" % "0.14.6",
      "io.circe" %% "circe-generic" % "0.14.6",
      "io.circe" %% "circe-parser" % "0.14.6"
    )
  )
