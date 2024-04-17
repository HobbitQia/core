package mini

import chisel3.stage.ChiselGeneratorAnnotation
import chisel3.stage.ChiselStage
import firrtl.options.TargetDirAnnotation
import mini.foo._
import mini.core._

object elaborate extends App {
  val targetDirectory = "Verilog"
  val dir 	= TargetDirAnnotation("Verilog")
  args(0) match{
    case "test_csr" => (new ChiselStage()).emitSystemVerilog(
        new test(),
        Array("--target-dir", "Verilog", "--full-stacktrace", "--output-annotation-file", "Foo.sv")
      )
		case "AlveoDynamicTop" => (new ChiselStage()).emitSystemVerilog(
        new Foo(),
        Array("--target-dir", "Verilog", "--full-stacktrace", "--output-annotation-file", "Foo.sv")
      )
		case "mini" => (new ChiselStage()).emitSystemVerilog(
        new Tile(
          coreParams = config.core,
          bramParams = config.bram,
          nastiParams = config.nasti,
          cacheParams = config.cache
        ),
        Array("--target-dir", "Verilog", "--full-stacktrace"),
      )
  }
}
