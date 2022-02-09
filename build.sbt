enablePlugins(ScalaJSPlugin)

name := "Coursematch"
scalaVersion := "3.1.1"

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

// Add DOM library
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "1.1.0"
