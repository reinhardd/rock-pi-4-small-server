FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

KERNEL_FEATURES:append = " needed-features.scc"

SRC_URI:append = "\
    file://needed-features.scc \
    file://usb-acm.cfg \
"


