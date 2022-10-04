SUMMARY = "build external piVCCU3 Linux kernel modules"
DESCRIPTION = "piVCCU3 kernel modules"

LIC_FILES_CHKSUM = "file://../LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

include pivccu-${PV}.inc

inherit module

RDEPENDS:${PN} = "pivccu-tools"

SRC_URI += "file://0001-added-install-target.patch;patchdir=.. \
           "
EXTRA_OEMAKE='KERNEL_DIR="${STAGING_KERNEL_DIR}"'

S = "${WORKDIR}/git/kernel"
