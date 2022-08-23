name := "example"

version := "0.1.0"

versionScheme := Some("early-semver")

scalaVersion := "3.1.3"

enablePlugins(ScalaNativePlugin)

nativeLinkStubs := true

nativeMode := "debug"

nativeLinkingOptions := Seq(s"-L${baseDirectory.value}/native-lib")

scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
  "-language:postfixOps",
  "-language:implicitConversions",
  "-language:existentials",
)

organization := "io.github.spritzsn"

githubOwner := "spritzsn"

githubRepository := name.value

Global / onChangedBuildSource := ReloadOnSourceChanges

resolvers += Resolver.githubPackages("edadma")

licenses := Seq("ISC" -> url("https://opensource.org/licenses/ISC"))

homepage := Some(url("https://github.com/spritzsn/" + name.value))

//libraryDependencies += "org.scalatest" %%% "scalatest" % "3.2.13" % "test"

libraryDependencies ++= Seq(
  "io.github.spritzsn" %%% "spritz" % "0.0.21",
  "io.github.spritzsn" %%% "body-parser" % "0.0.4",
  "io.github.spritzsn" %%% "cors" % "0.0.2",
  "io.github.spritzsn" %%% "serve-static" % "0.0.3",
  "io.github.spritzsn" %%% "logger" % "0.0.5",
  "io.github.spritzsn" %%% "compression" % "0.0.3",
)

publishMavenStyle := true

Test / publishArtifact := false
