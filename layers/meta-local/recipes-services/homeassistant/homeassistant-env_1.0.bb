SUMMARY = "homeassistant environment"
DESCRIPTION = "environment to install homeassistant via pip/wheel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " \
    file://homeassistant.service \
    file://homeassistant.conf.sample \
    file://ha_run \
"
inherit systemd allarch

RDEPENDS:${PN} = "nginx bash"

do_install:append() {
    install -d ${D}${sysconfdir}
    install -m755 ${WORKDIR}/homeassistant.conf.sample ${D}${sysconfdir}/
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/homeassistant.service ${D}${systemd_unitdir}/system/
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/ha_run ${D}${bindir}/
}

FILES:${PN} += "${systemd_unitdir}"

#pkg_postinst_ontarget:${PN} () {
#    
#}

SYSTEMD_SERVICE_${PN} = "homeassistant.service"
