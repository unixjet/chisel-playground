# Clock pin
set_property -dict { PACKAGE_PIN E3 IOSTANDARD LVCMOS33 } [get_ports {clock}]

# Clock constraints
create_clock -period 10.0 [get_ports {clock}]

# Switches
set_property -dict { PACKAGE_PIN A8 IOSTANDARD LVCMOS33 } [get_ports { reset }]

#LEDs
set_property -dict { PACKAGE_PIN H5 IOSTANDARD LVCMOS33 } [get_ports {io_led0}]
set_property -dict { PACKAGE_PIN J5 IOSTANDARD LVCMOS33 } [get_ports {io_led1}]
set_property -dict { PACKAGE_PIN T9 IOSTANDARD LVCMOS33 } [get_ports {io_led2}]
