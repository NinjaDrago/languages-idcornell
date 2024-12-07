import play.api.libs.json.{JsArray, JsObject, JsValue, Json}

object JsonSorter {

  ///def sort(json: String): String = {
    ///val root: JsValue = Json.parse(json)
    ///val newRoot = sortElements(root)
    ///newRoot.toString
  //}

  //def sortElements(root: JsValue): JsValue = root match {
    //case obj: JsObject => JsObject(obj.fields.sortBy(e => e._2.isInstanceOf[JsObject]).map(t => (t._1, sortElements(t._2))))
    ///case array: JsArray => JsArray(array.value.map(e => sortElements(e)))
    ///case other => other
  ///}

    // This function now accepts a JsArray (JSON list) and returns a sorted JsArray
  def sort(jsonList: JsArray): JsArray = {
    val sortedArray = sortElements(jsonList)
    sortedArray.as[JsArray] // Ensuring the result is a JsArray
  }

  // Sorting elements recursively
  def sortElements(root: JsValue): JsValue = root match {
    case obj: JsObject =>
      JsObject(obj.fields.sortBy(_._1).map { case (key, value) =>
        (key, sortElements(value))
      })
      
    case array: JsArray =>
      // Sort the array's elements (if they are objects, sort them too)
      JsArray(array.value.sortBy {
        case o: JsObject => o.toString() // Sort based on string representation of objects
        case other => other.toString()    // Sort other types similarly
      }.map(sortElements)) // Recursively sort array elements

    case other => other // Leave non-objects and non-arrays untouched
  }
}