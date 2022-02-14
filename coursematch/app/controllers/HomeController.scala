package controllers

import models.Widget
import javax.inject._
import play.api._
import play.api.i18n._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data.Form

import scala.collection._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val cc: MessagesControllerComponents) 
    extends MessagesAbstractController(cc)
    with play.api.i18n.I18nSupport {
  import ListingForm._

  private val widgets = mutable.ArrayBuffer(
    Widget("John Tan", "CS5219", "IT5100A", "IT5100B", "-"),
    Widget("Kelly Chia", "CS5219", "IT5100A", "IT5100B", "CS5218"),
    Widget("Lee James", "CS5100", "CS5200", "-", "-")
  )

  private val postUrl = routes.HomeController.createWidget

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def hello(name: String) = Action {
    Ok(views.html.hello(name))
  }

  def listWidgets = Action { implicit request: MessagesRequest[AnyContent] =>
    // Pass an unpopulated form to the template
    Ok(views.html.listWidgets(widgets.toSeq, userForm, postUrl))
  }

  // This will be the action that handles our form post
  def createWidget = Action { implicit request: MessagesRequest[AnyContent] =>
    val errorFunction = { formWithErrors: Form[UserData] =>
      // This is the bad case, where the form had validation errors.
      // Let's show the user the form again, with the errors highlighted.
      // Note how we pass the form with errors to the template.
      BadRequest(views.html.listWidgets(widgets.toSeq, formWithErrors, postUrl))
    }

    val successFunction = { data: UserData =>
      // This is the good case, where the form was successfully parsed as a Data object.
      val widget = Widget(
        name = data.name, 
        module1 = data.module1,
        module2 = data.module2,
        module3 = data.module3,
        module4 = data.module4
      )
      widgets += widget
      Redirect(routes.HomeController.listWidgets).flashing("info" -> "Widget added!")
    }

    val formValidationResult = userForm.bindFromRequest()
    formValidationResult.fold(errorFunction, successFunction)
  }
}
