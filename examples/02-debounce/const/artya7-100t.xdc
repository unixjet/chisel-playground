# ref:
# https://github.com/Digilent/digilent-xdc/blob/master/Arty-A7-100-Master.xdc
#

## Clock pin
set_property -dict { PACKAGE_PIN E3 IOSTANDARD LVCMOS33 } [get_ports {clock}]

## Clock constraints
create_clock -period 10.0 [get_ports {clock}]

## Switches
set_property -dict { PACKAGE_PIN A8 IOSTANDARD LVCMOS33 } [get_ports {reset}]

## Buttons
set_property -dict { PACKAGE_PIN D9 IOSTANDARD LVCMOS33 } [get_ports {io_btn}]

## LEDs
set_property -dict { PACKAGE_PIN H5 IOSTANDARD LVCMOS33 } [get_ports {io_led[0]}]
set_property -dict { PACKAGE_PIN J5 IOSTANDARD LVCMOS33 } [get_ports {io_led[1]}]
set_property -dict { PACKAGE_PIN T9 IOSTANDARD LVCMOS33 } [get_ports {io_led[2]}]
set_property -dict { PACKAGE_PIN T10 IOSTANDARD LVCMOS33 } [get_ports {io_led[3]}]
