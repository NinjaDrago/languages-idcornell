import play.api.libs.json._
import JsonSorter._
object Main extends App {
  println("Hello, World!")
  
  // Define a list of strings
  //val Flist = List("banana", "apple", "pear")
  //val jsonList: JsArray = Json.toJson(Flist)
  //sort(jsonList)

  val jsonList = Json.arr(
    Json.obj("name" -> "banana", "type" -> "fruit"),
    Json.obj("name" -> "apple", "type" -> "fruit"),
    Json.obj("name" -> "carrot", "type" -> "vegetable")
  )
  Json.prettyPrint(sort(jsonList))
}