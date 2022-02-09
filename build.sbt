enablePlugins(ScalaJSPlugin)

name := "Coursematch"
scalaVersion := "3.1.1"

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

// Add DOM library
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.1.0"

// Add testing framework
libraryDependencies += "com.lihaoyi" %%% "utest" % "0.7.11" % "test"
testFrameworks += new TestFramework("utest.runner.Framework")

// Use jsdom library to simulate a DOM in Node.js
jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
