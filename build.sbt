
lazy val root = project
  .in(file("."))
  .enablePlugins(
    ScalablyTypedConverterPlugin,
    ScalaJSBundlerPlugin,
    ScalaJSPlugin
  )
  .configure(
    projectSettings,
    slinkyBasics,
    webpackSettings,
    antdSettings,
    fluentUI,
    react_dom
  )

lazy val projectSettings: Project => Project =
  _.settings(
    name := "course_match",
    scalaVersion := "2.13.8",
    webpackDevServerPort := 8023,
  )

lazy val slinkyBasics: Project => Project =
  _.settings(
    scalacOptions += "-Ymacro-annotations",
    requireJsDomEnv in Test := true,
    addCommandAlias("dev", ";fastOptJS::startWebpackDevServer;~fastOptJS"),
    addCommandAlias("build", "fullOptJS::webpack"),
    scalacOptions ++= ScalacOptions.flags,
    stIgnore += "react-proxy",
    libraryDependencies ++= Seq(
      "me.shadaj" %%% "slinky-web" % "0.7.0",
      "me.shadaj" %%% "slinky-hot" % "0.7.0",
    ),
    libraryDependencies ++= Seq(
      "org.scalatest" %%% "scalatest" % "3.2.9" % Test
    ),
    Compile / npmDependencies ++= Seq(
      "react" -> "16.13.1",
      "react-dom" -> "16.13.1",
      "@types/react" -> "16.9.42",
      "@types/react-dom" -> "16.9.8",
      "react-proxy" -> "1.1.8",
    ),
    Compile / npmDevDependencies ++= Seq(
      "file-loader" -> "6.0.0",
      "style-loader" -> "2.0.0",
      "css-loader" -> "5.2.6",
      "html-webpack-plugin" -> "4.5.1",
      "copy-webpack-plugin" -> "6.4.0",
      "webpack-merge" -> "5.8.0"
    )
  )


lazy val antdSettings: Project => Project =
  _.settings(
    useYarn:= true,
    Compile / npmDependencies ++= Seq(
      "antd" -> "4.8.2"
    )
  )


lazy val fluentUI: Project => Project =
  _.settings(
    useYarn:= true,
    stFlavour := Flavour.Slinky,
    stReactEnableTreeShaking := Selection.All,
    Compile / npmDependencies ++= Seq(
      "@fluentui/react" -> "7.149.2"
    )
  )

lazy val react_dom: Project => Project =
  _.settings(
    useYarn:= true,
    stFlavour := Flavour.Slinky,
    stReactEnableTreeShaking := Selection.All,
    Compile / npmDependencies ++= Seq(
      "react-router" -> "5.1.2",
      "@types/react-router" -> "5.1.2",
      "react-router-dom" -> "5.1.2",
      "@types/react-router-dom" -> "5.1.2"// note 5.1.4 did weird things to the Link component
    )
  )

lazy val webpackSettings: Project => Project =
  _.settings(
    version in webpack := "4.44.2",
    version in startWebpackDevServer := "3.11.2",
    webpackResources := baseDirectory.value / "webpack" * "*",
    webpackConfigFile in fastOptJS := Some(
      baseDirectory.value / "webpack" / "webpack-fastopt.config.js"
    ),
    webpackConfigFile in fullOptJS := Some(
      baseDirectory.value / "webpack" / "webpack-opt.config.js"
    ),
    webpackConfigFile in Test := Some(
      baseDirectory.value / "webpack" / "webpack-core.config.js"
    ),
    webpackDevServerExtraArgs in fastOptJS := Seq("--inline", "--hot"),
    webpackBundlingMode in fastOptJS := BundlingMode.LibraryOnly()
  )

