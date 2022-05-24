SUMMARY = "A very basic Wayland image with a terminal"

IMAGE_FEATURES += "splash package-management ssh-server-openssh hwcodecs weston"

LICENSE = "MIT"

inherit core-image

CORE_IMAGE_BASE_INSTALL += "gtk+3-demo"
CORE_IMAGE_BASE_INSTALL += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'weston-xwayland matchbox-terminal', '', d)}"

#
# network
CORE_IMAGE_BASE_INSTALL += " \
networkmanager networkmanager-nmtui networkmanager-nmcli \
networkmanager-wifi \
firewalld"

#
# services
CORE_IMAGE_BASE_INSTALL += "nginx mosquitto python3-radicale"

#
# python basics for homeassistant
#
CORE_IMAGE_BASE_INSTALL += "python3-wheel python3-pip"

QB_MEM = "-m 512"

