package example

import monix.execution.Scheduler
import monix.reactive.{Consumer, Observable}

//class Test(val i: Int) {}

class SocketNio(val port: Int, val host: String = "localhost") {
  def readAsync(implicit ctx: Scheduler): Observable[Array[Byte]] = monix.nio.tcp.readAsync(host, port)
  def writeAsync(implicit ctx: Scheduler): Consumer[Array[Byte], Long] = monix.nio.tcp.writeAsync(host, port)
}