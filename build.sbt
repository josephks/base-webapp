/** Project */
name := "Lift SBT Template"

version := "0.2"

organization := "Lift"

scalaVersion := "2.10.3"

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

// run test:console at the sbt prompt in order to use specs2 classes


/** For web plugin: */
libraryDependencies += "org.mortbay.jetty" % "jetty" % "6.1.22" % "container,test"

libraryDependencies ++= {
  val liftVersion = "2.5.1"
  Seq(
    "ch.qos.logback" % "logback-classic" % "1.0.13",
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile",
    "net.liftweb" %% "lift-mapper" % liftVersion % "compile",
    "org.eclipse.jetty" % "jetty-webapp" % "8.1.7.v20120910"  % "container,test",
    "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container,compile" artifacts Artifact("javax.servlet", "jar", "jar"),
    "org.specs2" %% "specs2" % "2.2.3" % "test",
    "junit" % "junit" % "4.11" % "test",
    "com.h2database" % "h2" % "1.3.174"
  )
}

// For specs 2:

  scalacOptions in Test ++= Seq("-Yrangepos")

  // Read here for optional dependencies:
  // http://etorreborre.github.io/specs2/guide/org.specs2.guide.Runners.html#Dependencies

  resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                    "releases"  at "http://oss.sonatype.org/content/repositories/releases")

