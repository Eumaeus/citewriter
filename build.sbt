ThisBuild / crossScalaVersions  := List("2.11.8", "2.12.4")

name := "citewriter"
organization := "edu.classics.furman"


version := "0.1.0"




licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html"))

resolvers += Resolver.jcenterRepo
resolvers += Resolver.bintrayRepo("neelsmith", "maven")
resolvers += Resolver.bintrayRepo("eumaeus", "maven")


libraryDependencies ++= Seq(
  "edu.holycross.shot.cite" %% "xcite" % "4.0.2",
  "edu.holycross.shot" %% "ohco2" % "10.12.5",

  "edu.holycross.shot" %% "greek" % "2.1.0",
  "edu.holycross.shot" %% "gsphone" % "1.3.0",
  "edu.holycross.shot" %% "midvalidator" % "6.0.0",
  "edu.holycross.shot" %% "scm" % "6.1.3",
  "edu.holycross.shot" %% "citerelations" % "2.4.0",

  "org.scalatest" %% "scalatest" % "3.0.1" %  "test",
  "org.homermultitext" %% "hmt-textmodel" % "5.0.0"

)

tutSourceDirectory := file("tut")
tutTargetDirectory := file("docs")
enablePlugins(TutPlugin)
