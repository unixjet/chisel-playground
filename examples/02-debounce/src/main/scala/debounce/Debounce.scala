package debounce

import chisel3._
import chisel3.util._

class DebounceIO extends Bundle {
  val btn = Input(Bool())
  val led = Output(UInt(4.W))
}

class Debounce(freq: Int)  extends Module {
  var io = IO(new DebounceIO{})

  var FAC = if (freq > 100) freq / 100 else freq

  def sync(s: Bool) = RegNext(RegNext(s))
  def rise(s: Bool) = s & !RegNext(s)

  def tick(fac: Int) = {
    val reg = RegInit(0.U(log2Up(fac).W))
    val t = reg === (fac - 1).U
    reg := Mux(t, 0.U, reg + 1.U)
    t
  }

  def sample(s: Bool, tick: Bool) = {
    val reg = RegInit(0.U(3.W))
    when (tick) {
      reg := reg(1, 0) ## s
    }
    (reg(2) & reg(1)) | (reg(2) & reg(0)) | (reg(1) & reg(0))
  }

  val sbtn = sync(io.btn)
  val t = tick(FAC)
  val sig = sample(sbtn, t)
  val rising = rise(sig)

  val cnt = RegInit(0.U(4.W))

  when (rising) {
    cnt := Mux(cnt === 15.U, 0.U, cnt + 1.U)
  }

  io.led := cnt
}
