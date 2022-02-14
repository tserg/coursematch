package controllers

object ListingForm {
  import play.api.data.Forms._
  import play.api.data.Form

  case class UserData(name: String, module1: String, module2: String, module3: String, module4: String)

  val userForm = Form(
    mapping(
      "name" -> text,
      "module1" -> text,
      "module2" -> text,
      "module3" -> text,
      "module4" -> text
    )(UserData.apply)(UserData.unapply)
  )
}