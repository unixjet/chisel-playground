package blinky

import chisel3._
import chisel3.util._

class BlinkyIO extends Bundle {
  val led0 = Output(Bool())
  val led1 = Output(Bool())
  val led2 = Output(Bool())
  val led3 = Output(Bool())
}

class Breathe(freq: Int) extends Module {
  val io = IO(new Bundle{
    val led = Output(Bool())
  })

  def pwm(cycles: Int, duty: UInt) = {
    val cnt = RegInit(0.U(unsignedBitLength(cycles - 1).W))
    cnt := Mux(cnt === (cycles - 1).U, 0.U, cnt + 1.U)
    duty > cnt
  }

  val shift = if (freq > 1024) 10 else 0;
  val period = freq >> shift

  val max = freq;

  val up = RegInit(true.B)
  val mod = RegInit(0.U(unsignedBitLength(max).W))

  when (mod < max.U && up) {
    mod := mod + 1.U
  } .elsewhen (mod === max.U && up) {
    up := false.B
  } .elsewhen (mod > 0.U && !up) {
    mod := mod - 1.U
  } .otherwise {
    up := true.B
  }

  io.led := pwm(period, mod >> shift)
}

class Blinky(freq: Int) extends Module {
  val io = IO(new BlinkyIO{})

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

  val br3 = Module(new Breathe(freq))

  io.led0 := led0
  io.led1 := ~led0
  io.led2 := led2
  io.led3 := br3.io.led
}
