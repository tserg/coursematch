package coursematch.webapp

import org.scalajs.dom
import org.scalajs.dom.document

object App {
  def main(args: Array[String]): Unit = {
    document.addEventListener(
      "DOMContentLoaded",
      { (e: dom.Event) =>
        setupUI()
      }
    )
  }

  def setupUI(): Unit = {

    val topHeader = document.createElement("h1")
    topHeader.textContent = "Welcome to Coursematch"
    document.body.appendChild(topHeader)

    val form = document.createElement("form")
    document.body.appendChild(form)

    val nameInput = document.createElement("input")
    form.appendChild(nameInput)

    val button = document.createElement("button")
    button.textContent = "Click me!"
    button.addEventListener(
      "click",
      { (e: dom.MouseEvent) =>
        addEntry()
      }
    )
    document.body.appendChild(button)

    val table = createTable(document.body)
    
  }

  def createTable(targetNode: dom.Node): Unit = {
    val tableNode = document.createElement("table")

    val headerRow = document.createElement("tr")
    tableNode.appendChild(headerRow)

    val headerColOne = document.createElement("th")
    headerColOne.textContent = "Name"
    headerRow.appendChild(headerColOne)

    val headerColTwo = document.createElement("th")
    headerColTwo.textContent = "Module 1"
    headerRow.appendChild(headerColTwo)

    val headerColThree = document.createElement("th")
    headerColThree.textContent = "Module 2"
    headerRow.appendChild(headerColThree)

    val headerColFour = document.createElement("th")
    headerColFour.textContent = "Module 3"
    headerRow.appendChild(headerColFour)

    targetNode.appendChild(tableNode)
    generateTableEntries(tableNode)
  }

  def generateTableEntries(tableNode: dom.Node): Unit = {
    val row = document.createElement("tr")
    tableNode.appendChild(row)

    val headerColOne = document.createElement("td")
    headerColOne.textContent = "John Tan"
    row.appendChild(headerColOne)

    val headerColTwo = document.createElement("td")
    headerColTwo.textContent = "IT5001"
    row.appendChild(headerColTwo)

    val headerColThree = document.createElement("td")
    headerColThree.textContent = "IT5002"
    row.appendChild(headerColThree)

    val headerColFour = document.createElement("td")
    headerColFour.textContent = "IT5003"
    row.appendChild(headerColFour)

    tableNode.appendChild(row)
  }

  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    parNode.textContent = text
    targetNode.appendChild(parNode)
  }

  def addEntry(): Unit = {
    appendPar(document.body, "You clicked the button!")
  }
}
