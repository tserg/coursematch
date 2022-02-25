package course_match

import org.scalajs.dom.console
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks.{useEffect, useState}
import slinky.web.html._
import typings.fluentuiReact.{components => FluentUi}
import typings.officeUiFabricReact.stackTypesMod.IStackTokens
import typings.antd.components.{List => AntList, _}
import typings.antd.tableInterfaceMod.{ColumnGroupType, ColumnType}
import typings.reactRouter.mod._
import typings.reactRouterDom.components.{BrowserRouter, Route}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import js.JSConverters._

@JSImport("resources/LandingPage.css", JSImport.Default)
@js.native
object LandingPageCSS extends js.Object

@JSImport("resources/profilePicture.svg", JSImport.Default)
@js.native
object profilePic extends js.Object

@JSImport("antd/dist/antd.css", JSImport.Default)
@js.native
object AntCSS extends js.Any

@react
object LandingPage {

  case class Props(
      username: String,
      user_email: String,
      mods: Seq[(String, String, String, String, String, String)]
  )

  // =================CSS=========================

  val landingPagecss = LandingPageCSS
  val antCss = AntCSS

  // =================Button Text==========================
  val data = Seq(
    ("AY2021-22 Semester 1", 1),
    ("AY2021-22 Semester 2", 2),
    ("AY2022-23 Semester 1", 3)
  )

  // ==================Simulated Table Data============================
  // Ideally this should be pulled from a database but we are hardcoding it for now.

  // Table Data for AY2021-22 Semester 1
  val tableData1 = Seq(
    ("John Tan", "john@u.nus.edu", "CS5219", "IT5001A", "IT5100B", "-"),
    ("Kelly Chia", "kelly@u.nus.edu", "CS5219", "IT5001A", "IT5100B", "CS5218"),
    ("Lee James", "james@u.nus.edu", "CS5100", "CS5200", "-", "-"),
    ("Sue", "sue@u.nus.edu", "IT5100A", "IT5100B", "-", "-")
  )

  // Table Data for AY2021-22 Semester 2
  val tableData2 = Seq(
    ("Chris1", "chris@u.nus.edu", "CS5219", "IT5001A", "IT5100B", "-"),
    ("Gary1", "gary@u.nus.edu", "CS5219", "IT5001A", "IT5100B", "CS5218"),
    ("Felicia1", "felicia@u.nus.edu", "CS5100", "CS5200", "-", "-")
  )

  // Table Data for AY2021-23 Semester 1
  val tableData3 = Seq(
    ("John Tan", "john@u.nus.edu", "CS5219", "IT5001A", "IT5100B", "-"),
    ("Kelly Chia", "kelly@u.nus.edu", "CS5219", "IT5001A", "IT5100B", "CS5218"),
    ("Lee James", "james@u.nus.edu", "CS5100", "CS5200", "-", "-"),
    ("Sue", "sue@u.nus.edu", "IT5100A", "IT5100B", "-", "-"),
    ("Chris1", "chris@u.nus.edu", "CS5219", "IT5001A", "IT5100B", "-"),
    ("Gary1", "gary@u.nus.edu", "CS5219", "IT5001A", "IT5100B", "CS5218"),
    ("Felicia1", "felicia@u.nus.edu", "CS5100", "CS5200", "-", "-")
  )

  // ====================React Hooks Component==================

  val component = FunctionalComponent[Props] { props =>
    // ===============React State Hooks==========================

    val (tableData, updateTableData) = useState(tableData1)
    val (tableData_1, updateTableData1) = useState(tableData1)
    val (tableData_2, updateTableData2) = useState(tableData2)
    val (tableData_3, updateTableData3) = useState(tableData3)

    val (counter, updateCounter) = useState(0)

    // This will set the data that are to be shown in the table
    def setTableData(num: Int): Unit = {
      num match {
        case 1 => updateTableData(tableData_1)
        case 2 => updateTableData(tableData_2)
        case 3 => updateTableData(tableData_3)
      }
    }

    // This will initialise the tableData to include the modules inputted by the user in the registration Page
    useEffect(
      () => {
        props.mods.zipWithIndex.map(d =>
          d._2 match {
            case 0 => updateTableData1(tableData_1 :+ d._1)
            case 1 => updateTableData2(tableData_2 :+ d._1)
            case 2 => updateTableData3(tableData_3 :+ d._1)
          }
        )
        updateTableData(tableData_1)
      },
      Seq.empty
    )
    // ====================welcome text=========================
    val welcome = h1("Welcome to Mcomp Course - Match")

    // ====================Buttons===============================

    // Set gaps between button - Basically equivalent to margin-bottom in CSS
    val stackTokens = IStackTokens().setChildrenGap(10)

    // Output 3 buttons
    // Depending on which button is selected, the data in the table will be updated
    val semesterButton = FluentUi.Stack.tokens(stackTokens)(
      data.map(d =>
        FluentUi.DefaultButton
          .className("Button-Style")
          .text(d._1)
          .onClick(_ => setTableData(d._2))
      )
    )

    // ====================Search Bar==================================

    // Not sure what we can do with this
    val searchBar =
      FluentUi.SearchBox
        .placeholder("Search")
        .className("SearchBar-Style")
        .onEscape(ev => console.log("custom escape called"))
        .onClear(ev => console.log("custom onclear called"))
        .onChange((_, newValue) =>
          console.log("SearchBox onChange fired: " + newValue)
        )
        .onSearch(newValue =>
          console.log("SearchBox onSearch fired: " + newValue)
        )

    // ================== Content======================================

    // Above components like semesterButton, searchBar e.g. are only created into existence but not utilised yet.
    // The below is where the components are actually utilised and can be rendered on screen in a desired format.
    // Desired format is controlled by custom CSS - See landingPage.css in resources file

    val profile = div(h2("Profile"))

    val profileButton = Button("View Profile").onClick(_ => updateCounter(1))

    val selectionSection = div(className := "content-container")(
      div(className := "left")(
        welcome,
        div(className := "inner-left")(
          semesterButton,
          searchBar
        )
      ),
      div(className := "right")(
        img(
          src := profilePic.asInstanceOf[String],
          className := "profile-pic",
          alt := "profilePic"
        ),
        span(props.username),
        span(props.user_email),
        profileButton
      )
    )

    // ============================================Table===============================================

    class TableItem(
        val key: Int,
        val student: String,
        val email: String,
        val module1: String,
        val module2: String,
        val module3: String,
        val module4: String
    ) extends js.Object

    val tableSection =
      div(className := "table-container")(
        Table[TableItem]
          .bordered(true)
          .dataSource(
            // tableData is retrieved from the state hooks - val (tableData , updateTableData) = useState(tableData1) - line 84
            tableData.zipWithIndex
              .map(d =>
                new TableItem(
                  d._2,
                  d._1._1,
                  d._1._2,
                  d._1._3,
                  d._1._4,
                  d._1._5,
                  d._1._6
                )
              )
              .toJSArray
          )
          .columnsVarargs(
            // set the table columns name
            ColumnType[TableItem]()
              .setTitle("Student")
              .setDataIndex("student")
              .setKey("student"),
            ColumnType[TableItem]()
              .setTitle("Email")
              .setDataIndex("email")
              .setKey("email"),
            ColumnType[TableItem]()
              .setTitle("Module 1")
              .setDataIndex("module1")
              .setKey("module1"),
            ColumnType[TableItem]()
              .setTitle("Module 2")
              .setDataIndex("module2")
              .setKey("module2"),
            ColumnType[TableItem]()
              .setTitle("Module 3")
              .setDataIndex("module3")
              .setKey("module3"),
            ColumnType[TableItem]()
              .setTitle("Module 4")
              .setDataIndex("module4")
              .setKey("module4")
          )
      )

    // ============Export landing page===================

    // This will be invoked in App page
    div(className := "page-container")(
      NavBar.component(),
      selectionSection,
      tableSection
    )

    counter match {
      case 0 =>
        div(className := "page-container")(
          NavBar.component(),
          selectionSection,
          tableSection
        )
      case 1 =>
        Route(
          RouteProps()
            .setExact(true)
            .setPath("/")
            .setRender(_ =>
              ProfilePage(
                username = props.username,
                user_email = props.user_email,
                mods = props.mods
              )
            )
        )
    }
  }

}
