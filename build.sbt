lazy val metaVersion = "1.6.0"
// scalaVersion in ThisBuild := dottyLatestNightlyBuild.get
scalaVersion in ThisBuild := "0.1.2-RC1"

lazy val settings = Seq(
  name := "dotty-test",
  version := "0.0.1",
  organization := "vlthr"
)

lazy val root = Project(id = "dotty-test", base = file("."), settings = settings)
