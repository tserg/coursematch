package course_match

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._
import typings.reactRouter.mod._
import typings.reactRouterDom.components._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("resources/logo.svg", JSImport.Default)
@js.native
object ReactLogo extends js.Object


object App {

    //Usually this is where we will prepare statements and invoke queries to database or API(s)
    //but we are not doing any of that so just redirect to registration page
    val component = FunctionalComponent[Unit] { _ =>
      div(
        BrowserRouter(
          div(
            Switch(
              Route(RouteProps().setExact(true).setPath("/").setRender(_ => Registration(testing = ""))),
            )
          )
        )
      )
    }

}
