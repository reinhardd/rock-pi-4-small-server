SUMMARY = "homeassistant environment"
DESCRIPTION = "environment to install homeassistant via pip/wheel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " \
    file://homeassistant.service \
    file://homeassistant.conf.sample \
    file://ha_run \
    file://hass_location.conf \
"
inherit systemd allarch

RH1 = "${ROOT_HASS}"
RH2 = "${ROOT_HASS_WEBSOCKET}"

RDEPENDS:${PN} = "nginx bash"

do_install:append() {
    install -d ${D}${sysconfdir}
    install -m755 ${WORKDIR}/homeassistant.conf.sample ${D}${sysconfdir}/
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/homeassistant.service ${D}${systemd_unitdir}/system/
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/ha_run ${D}${bindir}/
    install -d ${D}${sysconfdir}/nginx/locations
    install -m 0644 ${WORKDIR}/hass_location.conf ${D}${sysconfdir}/nginx/locations/
    sed -i "s/HASS_WEB_ROOT/${ROOT_HASS}/g" ${D}${sysconfdir}/nginx/locations/hass_location.conf
    sed -i "s/HASS_API_ROOT/${ROOT_HASS_WEBSOCKET}/g" ${D}${sysconfdir}/nginx/locations/hass_location.conf
}

FILES:${PN} += "${systemd_unitdir}"

SYSTEMD_SERVICE_${PN} = "homeassistant.service"
