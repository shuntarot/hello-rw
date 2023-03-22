package hello

import com.xilinx.rapidwright.design.Cell
import com.xilinx.rapidwright.design.Design
import com.xilinx.rapidwright.design.Net
import com.xilinx.rapidwright.design.PinType
import com.xilinx.rapidwright.design.Unisim
import com.xilinx.rapidwright.device.Device
import com.xilinx.rapidwright.router.Router

object Main extends App {
  val d = new Design("HelloWorld", Device.PYNQ_Z1)

  val and2    = d.createAndPlaceCell("and2"   , Unisim.AND2, "SLICE_X100Y100/A6LUT")
  val button0 = d.createAndPlaceIOB ("button0", PinType.IN , "D19",  "LVCMOS33")
  val button1 = d.createAndPlaceIOB ("button1", PinType.IN , "D20",  "LVCMOS33")
  val led0    = d.createAndPlaceIOB ("led0"   , PinType.OUT, "R14",  "LVCMOS33")

  val net0 = d.createNet("button0_IBUF")
  net0.connect(button0, "O")
  net0.connect(and2, "I0")

  val net1 = d.createNet("button1_IBUF")
  net1.connect(button1, "O")
  net1.connect(and2, "I1")

  val net2 = d.createNet("and2")
  net2.connect(and2, "O")
  net2.connect(led0, "I")

  d.routeSites()

  val r = new Router(d)
  r.routeDesign()

  d.writeCheckpoint("HelloWorld.dcp")
}
