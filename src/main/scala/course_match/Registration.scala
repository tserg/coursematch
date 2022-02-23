package course_match

import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.Hooks.useState

import slinky.web.html.{className, div, h1, h2, h3, h4, section, span}
import typings.antd.components.{List => AntList, _}
import typings.antd.components.Form.{Form => FormItem}
import typings.antd.antdStrings
import typings.antd.formFormMod.useForm
import typings.antd.gridColMod.ColProps
import typings.antd.useFormMod.FormInstance
import typings.rcFieldForm.interfaceMod.{AggregationRule, FieldData}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import typings.reactRouter.mod._
import typings.reactRouterDom.components._

@JSImport("antd/dist/antd.css", JSImport.Default)
@js.native
object AntCSS2 extends js.Any

@JSImport("resources/RegistrationPage.css", JSImport.Default)
@js.native
object RegistrationPageCSS extends js.Object

@react object Registration {
  case class Props(testing: String)

  val antCss = AntCSS2
  val registrationPageCSS = RegistrationPageCSS

  val component = FunctionalComponent[Props] { _ =>
    // ===============React State Hooks==========================
    val (modules, updateCourseModules) =
      useState(Seq[(String, String, String, String, String, String)]())
    val (counter, updateCounter) = useState(0)
    val (headerText, updateHeaderText) = useState("Enter your name and email")
    val (user_name, updateUserName) = useState("")
    val (user_email, updateUserEmail) = useState("")

    // ================Action taken after form submit for semester modules======================
    def setCourseModules(
        courseModules: Values,
        form: FormInstance[Values]
    ): Unit = {
      form.resetFields()
      val mods =
        modules :+ (user_name, user_email, courseModules.module1, courseModules.module2, courseModules.module3, courseModules.module4)
      updateCourseModules(mods)
      updateCounter(counter + 1)

      counter + 1 match {
        case 2 => updateHeaderText("AY2021-22 Semester 2")
        case 3 => updateHeaderText("AY2022-23 Semester 1")
        case _ => None
      }
    }

    // ===============Action taken after form submit for name and email==============================
    def setUserDetails(details: userValues): Unit = {
      updateUserName(details.name)
      updateUserEmail(details.email)
      updateCounter(counter + 1)
      updateHeaderText("AY2021-22 Semester 1")
    }

    val welcome = h1("Welcome to Mcomp Course - Match")

    val instruction = h4(headerText)

    // ========================Crate form for name input===========================
    class userValues(
        val name: String,
        val email: String
    ) extends js.Object
    val userDetailsForm: FormInstance[userValues] = useForm[userValues]().head
    val userForm = Form[userValues]()
      .form(userDetailsForm)
      .labelCol(ColProps().setSpan(5))
      .wrapperCol(ColProps().setSpan(12))
      .onFinish(details => setUserDetails(details))(
        FormItem[userValues]()
          .label("Name")
          .name("name")
          .rulesVarargs(
            AggregationRule()
              .setRequired(true)
              .setMessage("Please input your name")
          )(
            Input()
          ),
        FormItem[userValues]()
          .label("Email")
          .name("email")
          .rulesVarargs(
            AggregationRule()
              .setRequired(true)
              .setMessage("Please input your email")
          )(
            Input()
          ),
        FormItem[userValues]().wrapperCol(ColProps().setSpan(12).setOffset(5))(
          Button
            .`type`(antdStrings.primary)
            .htmlType(antdStrings.submit)("Submit")
        )
      )

    // ===================Create form for semester modules===========================
    class Values(
        val module1: String,
        val module2: String,
        val module3: String,
        val module4: String
    ) extends js.Object
    val form: FormInstance[Values] = useForm[Values]().head
    val tempForm = Form[Values]()
      .form(form)
      .labelCol(ColProps().setSpan(5))
      .wrapperCol(ColProps().setSpan(12))
      .onFinish(mods => setCourseModules(mods, form))(
        FormItem[Values]()
          .label("Module 1")
          .name("module1")
          .rulesVarargs(
            AggregationRule()
              .setRequired(false)
              .setMessage("Please input your module")
          )(
            Input()
          )
          .initialValue("-"),
        FormItem[Values]()
          .label("Module 2")
          .name("module2")
          .rulesVarargs(
            AggregationRule()
              .setRequired(false)
              .setMessage("Please input your module")
          )(
            Input()
          )
          .initialValue("-"),
        FormItem[Values]()
          .label("Module 3")
          .name("module3")
          .rulesVarargs(
            AggregationRule()
              .setRequired(false)
              .setMessage("Please input your module")
          )(
            Input()
          )
          .initialValue("-"),
        FormItem[Values]()
          .label("Module 4")
          .name("module4")
          .rulesVarargs(
            AggregationRule()
              .setRequired(false)
              .setMessage("Please input your module")
          )(
            Input()
          )
          .initialValue("-"),
        FormItem[Values]().wrapperCol(ColProps().setSpan(12).setOffset(5))(
          Button
            .`type`(antdStrings.primary)
            .htmlType(antdStrings.submit)("Submit")
        )
      )

    // Render different view based on counter count
    // When user has submitted all his/her inputs, passed user name and modules data to landing page (counter = 4)
    counter match {
      case 0 => div(className := "r-container")(welcome, instruction, userForm)
      case 4 =>
        Route(
          RouteProps()
            .setExact(true)
            .setPath("/")
            .setRender(_ =>
              LandingPage(
                username = user_name,
                user_email = user_email,
                mods = modules
              )
            )
        )
      case _ => div(className := "r-container")(welcome, instruction, tempForm)
    }

  }
}
