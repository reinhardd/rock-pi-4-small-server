SUMMARY = "build piVCCU3 tools "
DESCRIPTION = "piVCCU3 kernel module tools"

LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

include pivccu-${PV}.inc

inherit cmake

SRC_URI += "file://0001-add-cmake-to-simplify-some-things.patch \
           "
S = "${WORKDIR}/git"
