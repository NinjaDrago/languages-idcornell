// src/main/scala/HelloWorldServer.scala

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes

import io.circe.generic.auto._
import io.circe.syntax._
import io.circe.parser.decode
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json._

import scala.io.StdIn

// Define case class for JSON
case class StringList(strings: List[String])

object HelloWorldServer {
  def main(args: Array[String]): Unit = {
    // Create an ActorSystem
    implicit val system = ActorSystem(Behaviors.empty, "helloWorldSystem")
    implicit val executionContext = system.executionContext

    // Functional style sorting function
    def sortStrings(input: StringList): StringList = {
      StringList(input.strings.sorted)
    }

    // Define the route
    val route =
      path("greet" / Segment) { person =>
        get {
          complete(s"Hello, $person!")
        }
      } ~
      path("sort") {
        post {
          entity(as[String]) { jsonInput =>
            decode[StringList](jsonInput) match {
              case Right(stringList) =>
                val sortedList = sortStrings(stringList)
                complete(StatusCodes.OK -> sortedList.asJson.noSpaces)
              case Left(error) =>
                complete(StatusCodes.BadRequest -> s"Invalid JSON: ${error.getMessage}")
            }
          }
        }
      } ~
      pathSingleSlash {
        get {
          complete(s"Hello!")
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
