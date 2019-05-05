package example

import monix.reactive.Consumer
import monix.nio.tcp._

object Hello extends App {//extends Greeting with App {
  //println(greeting)

  implicit val ctx = monix.execution.Scheduler.Implicits.global

  val callback = new monix.execution.Callback[Throwable, Unit] {
    override def onSuccess(value: Unit): Unit = println("Completed")
    override def onError(ex: Throwable): Unit = println(ex)
  }
  readAsync("localhost", 5555)
    .consumeWith(Consumer.foreach(c => Console.out.print(new String(c))))
    .runAsync(callback)

}

/*
trait Greeting {
  lazy val greeting: String = "hello"
}
*/