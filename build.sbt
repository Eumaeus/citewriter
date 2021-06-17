name := "CITE Writer Trait"

crossScalaVersions in ThisBuild := Seq("2.12.4")
scalaVersion := (crossScalaVersions in ThisBuild).value.last

// shadow sbt-scalajs' crossProject and CrossType from Scala.js 0.6.x
import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

lazy val root = project.in(file(".")).
aggregate(crossedJVM, crossedJS).
settings(
	publish := {},
	publishLocal := {}
)

//bintrayVcsUrl := Some("git@github.com:Eumaeus/citewriter.git")

lazy val crossed = crossProject(JSPlatform, JVMPlatform).in(file("."))
.settings(
	name := "citewriter",
	organization := "edu.furman.classics",

	version := "1.2.3",

	licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html")),

  resolvers += "Nexus" at "https://terracotta.hpcc.uh.edu/nexus/repository/maven-releases/",

	libraryDependencies ++= Seq(
		"org.scalatest" %%% "scalatest" % "3.1.2" % "test",
		"edu.holycross.shot.cite" %%% "xcite" % "4.3.1",
		"edu.holycross.shot" %%% "ohco2" % "10.20.5",
		"edu.holycross.shot" %%% "scm" % "7.4.1",
		"edu.holycross.shot" %%% "citeobj" % "7.5.2",
		"edu.holycross.shot" %%% "citerelations" % "2.7.1"
	),
    credentials += Credentials(Path.userHome / ".sbt" / ".credentials"),

    publishTo := Some("releases" at "https://terracotta.hpcc.uh.edu/nexus/repository/maven-releases/")
).
jvmSettings(
	libraryDependencies ++= Seq(
        "org.scala-js" %% "scalajs-stubs" % "1.0.0" % "provided",
      )
).
jsSettings(
	scalaJSUseMainModuleInitializer in Compile := true
)

lazy val crossedJVM = crossed.jvm
lazy val crossedJS = crossed.js
