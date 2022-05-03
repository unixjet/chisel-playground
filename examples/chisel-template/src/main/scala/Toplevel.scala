import chisel3._
import com.carlosedp.scalautils.ParseArguments
import blinky._

class Toplevel(freq: Int) extends Module {
  val io = IO(new BlinkyIO)

  val bl = Module(new Blinky(freq))
  bl.io <> io
}

/* The Main object extending App to generate the Verilog code. */

object Toplevel extends App {

  val (params, chiselargs) = ParseArguments(args,
                        List("board", "invreset", "freq"))

  val freq: Int = params.getOrElse("freq", "50000000").toInt

  /* Generate Verilog */

  (new chisel3.stage.ChiselStage).emitVerilog(
    new Toplevel(freq),
    chiselargs
  )
}
