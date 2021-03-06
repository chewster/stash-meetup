import Dependencies._

lazy val root = (project in file("."))
  .enablePlugins(
    DockerPlugin,
    SbtS3Resolver
  )
  .settings(
    inThisBuild(List(
      organization := "stash",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "stash-yeezus",
    libraryDependencies ++= dependencies
  )

resolvers ++= Seq[Resolver](
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/public"
)

scalacOptions ++= Seq(
  "-language:_",
  "-target:jvm-1.8",
  "-deprecation",
  // yes, this is 2 args
  "-encoding", "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked",
//  "-Xfatal-warnings",
  "-Xlint",
  "-Yno-adapted-args",
  // "-Ypartial-unification",
  // N.B. doesn't work well with the ??? hole
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture"
)

parallelExecution in Test := false
parallelExecution in IntegrationTest := false

mainClass in (Compile, run) := Some("stash.yeezus.Server")

assemblyOutputPath in assembly := file(s"docker/service/${name.value}.jar")
