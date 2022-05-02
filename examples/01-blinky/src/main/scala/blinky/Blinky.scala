package blinky

import chisel3._
import chisel3.util._

class BlinkyIO extends Bundle {
  val led0 = Output(Bool())
  val led1 = Output(Bool())
  val led2 = Output(Bool())
}

class Blinky(freq: Int) extends Module {
  val io = IO(new BlinkyIO)

  val led0 = RegInit(false.B)
  val led2 = RegInit(false.B)

  val (_, wrap0) = Counter(true.B, freq / 2)
  val (_, wrap2) = Counter(true.B, freq / 4)

  when (wrap0) {
    led0 := ~led0
  }

  when (wrap2) {
    led2 := ~led2
  }

  io.led0 := led0
  io.led1 := ~led0
  io.led2 := led2
}
