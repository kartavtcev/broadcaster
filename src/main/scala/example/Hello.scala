package example

import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets
import java.time.{Instant, ZoneId, ZonedDateTime}

import monix.nio.tcp._
import monix.reactive.Consumer

object Hello extends App {//extends Greeting with App {
  //println(greeting)

  implicit val ctx = monix.execution.Scheduler.Implicits.global

  val callback = new monix.eval.Callback[Unit] {
    override def onSuccess(value: Unit): Unit = println("Completed")
    override def onError(ex: Throwable): Unit = println(ex)
  }
  readAsync("localhost", 5555)
    .consumeWith(Consumer.foreach( (c: Array[Byte]) => Console.out.print(parseBytesToString(c) + "\n")))
    .runAsync(callback)

  def parseBytesToString(bytes: Array[Byte]): String = { // 28
    val len = ByteBuffer.wrap(bytes.slice(0, 2)).getShort

    //if(len <= bytes.length) {

    val timeStampEpochMilli = ByteBuffer.wrap(bytes.slice(2, 10)).getLong
    val timeStampInstant = Instant.ofEpochMilli(timeStampEpochMilli)
    val timeStampUtc = ZonedDateTime.ofInstant(timeStampInstant, ZoneId.of("UTC"))

    val tickerLength = ByteBuffer.wrap(bytes.slice(10, 12)).getShort
    val ticker = new String(bytes.slice(12, 12 + tickerLength), StandardCharsets.US_ASCII)
    val price = ByteBuffer.wrap(bytes.slice(12 + tickerLength, 12 + tickerLength + 8)).getDouble
    val size = ByteBuffer.wrap(bytes.slice(12 + tickerLength + 8, 12 + tickerLength + 8 + 4)).getInt

    //s"${Byte.byte2short(len)}, ${Byte.byte2long(timeStamp)}, ${Byte.byte2short(tickerLength)}, ${new String(ticker, StandardCharsets.US_ASCII)}, ${Byte.byte2double(price)}, ${Byte.byte2int(size)}"
    s"$len, $timeStampUtc, $tickerLength, $ticker, $price, $size"
    //} else {
    //s"$len"
    //}
  }
}

/*
trait Greeting {
  lazy val greeting: String = "hello"
}
*/