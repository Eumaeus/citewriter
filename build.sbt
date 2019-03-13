name := "CITE Writer Trait"

crossScalaVersions in ThisBuild := Seq("2.11.8", "2.12.4")
scalaVersion := (crossScalaVersions in ThisBuild).value.last

// shadow sbt-scalajs' crossProject and CrossType from Scala.js 0.6.x
import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

lazy val root = project.in(file(".")).
aggregate(crossedJVM, crossedJS).
settings(
	publish := {},
	publishLocal := {}
)

lazy val crossed = crossProject(JSPlatform, JVMPlatform).in(file("."))
.settings(
	name := "citewriter",
	organization := "edu.furman.classics",

	version := "0.4.0",

	licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html")),

	resolvers += Resolver.jcenterRepo,
	resolvers += Resolver.bintrayRepo("neelsmith", "maven"),
	resolvers += Resolver.bintrayRepo("eumaeus", "maven"),

	libraryDependencies ++= Seq(
		"org.scala-js" %% "scalajs-stubs" % scalaJSVersion % "provided",
		"org.scalatest" %%% "scalatest" % "3.0.1" % "test",
		"edu.holycross.shot.cite" %%% "xcite" % "4.0.2",
		"edu.holycross.shot" %%% "ohco2" % "10.12.5",
		"edu.holycross.shot" %%% "scm" % "6.1.3",
		"edu.holycross.shot" %%% "citeobj" % "7.3.0",
		"edu.holycross.shot" %%% "citerelations" % "2.4.0",
		"org.scalatest" %%% "scalatest" % "3.0.1" %  "test",
	),
).
jvmSettings(
	tutTargetDirectory := file("docs"),
	tutSourceDirectory := file("shared/src/main/tut")
).
jsSettings(
	skip in packageJSDependencies := false,
	scalaJSUseMainModuleInitializer in Compile := true
)

lazy val crossedJVM = crossed.jvm.enablePlugins(TutPlugin)
lazy val crossedJS = crossed.js
