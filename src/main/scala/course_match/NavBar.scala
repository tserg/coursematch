package course_match

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("resources/NavBar.css", JSImport.Default)
@js.native
object NavBarCSS extends js.Object

object NavBar {
  val css = NavBarCSS

  val component = FunctionalComponent[Unit] { _ =>
    div(className := "NavBar")(
      span(className := "dot"),
      span(className := "dot"),
      span(className := "dot")
    )
  }
}
