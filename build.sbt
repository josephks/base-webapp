/** Project */
name := "Lift SBT Template"

version := "0.1"

organization := "Lift"

scalaVersion := "2.9.1"

//xsbt-webplugin isn't published for 0.11.3 right now, so use 0.11.2

sbtVersion := "0.11.2"

//for web plugin
seq(webSettings :_*)




/** Shell */
shellPrompt := { state => System.getProperty("user.name") + " " +System.getProperty("user.dir") + " sbt> " }


shellPrompt in ThisBuild := { state => Project.extract(state).currentRef.project + "> " }

/** Dependencies */
resolvers ++= Seq(
                 //"snapshots-repo" at "http://scala-tools.org/repo-snapshots", 
                  "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository")




/** Compilation */
javacOptions ++= Seq("-Xmx1812m", "-Xms512m", "-Xss4m")

scalacOptions += "-deprecation"

maxErrors := 20

pollInterval := 1000

testFrameworks += new TestFramework("org.specs2.runner.SpecsFramework")

testOptions := Seq(Tests.Filter(s =>
  Seq("Spec", "Suite", "Unit", "Specs", "Test", "all").exists(s.endsWith(_)) &&
    ! s.endsWith("FeaturesSpec") ||
    s.contains("UserGuide") || 
    s.matches("org.specs2.guide.*")))



/** Console */
initialCommands in console in Test := "import org.specs2._"

/* run test:console at the sbt prompt in order to use specs2 classes */
