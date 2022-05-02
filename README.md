FuseSoC is prerequisite. To install FuseSoC in Ubuntu, just type the following command:
$ pip3 install --upgrade --user fusesoc

Uninstall FuseSoC:
$ pip3 uninstall fusesoc

When this repository is downloaded, fusesoc-cores must be installed in
fusesoc_libraries/fusesoc-cores. To achieve that, just execute:
$ git clone https://github.com/fusesoc/fusesoc-cores fusesoc_libraries/fusesoc-cores

We have to note that fusesoc-cores must reside in fusesoc_libraries/fusesoc-cores,
which is mandatory.

Start a build of 01-blinky for artya7-100t :
$ fusesoc run --build --target=artya7-100t 01-blinky
