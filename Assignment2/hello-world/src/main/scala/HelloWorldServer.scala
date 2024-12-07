// src/main/scala/HelloWorldServer.scala

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.ContentTypes
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.StatusCodes
import spray.json._

import scala.io.StdIn

// JSON Protocol for handling the list of strings
trait JsonProtocol extends DefaultJsonProtocol {
  implicit val stringListFormat = jsonFormat1(List[String])
}

object HelloWorldServer extends JsonProtocol {

  def main(args: Array[String]): Unit = {
    // Create an ActorSystem
    implicit val system = ActorSystem(Behaviors.empty, "helloWorldSystem")
    implicit val executionContext = system.executionContext

    // Define the route
    val route =
      path("greet" / Segment) { person =>
        get {
          complete(s"Hello, $person!")
        }
      } ~
      pathSingleSlash {
        get {
          complete(s"Hello!")
        }
      } ~
      path("sortList") {
        post {
          entity(as[List[String]]) { list =>
            val sortedList = list.sorted // Sort the list in functional programming style
            complete(HttpEntity(ContentTypes.`application/json`, sortedList.toJson.prettyPrint))
          }
        }
      }

    // Start the server
    val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)

    println("Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // Keep the server running until user presses return
    bindingFuture
      .flatMap(_.unbind()) // Unbind from the port
      .onComplete(_ => system.terminate()) // Terminate the system when done
  }
}
