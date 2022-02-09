enablePlugins(ScalaJSPlugin)

name := "Coursematch"
scalaVersion := "2.13.6"

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

// Add DOM library
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "1.1.0"
