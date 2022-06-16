SUMMARY = "Radicale *DAV Server"
DESCRIPTION = "A Free and Open-Source CalDAV and CardDAV Server"
HOMEPAGE="http://www.radicale.org/"
LICENSE = "GPLv3"

DEPENDS = "python3 openssl"

inherit pypi setuptools3 useradd systemd

LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
PYPI_PACKAGE = "Radicale"
SRC_URI += " \
    file://radicale.service \
    file://radicale_location.conf \
"

SRC_URI[md5sum] = "cb33912d023e40b3f4a72e20cefe4686"
SRC_URI[sha256sum] = "7c0bec2666c87671bc95935052d2aec78ceeea699a4802b500c4dd5a7e6d3b5a"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} =  "--system --user-group -d ${datadir}/radicale/ -s ${sbindir}/nologin -c \"Radicale\" radicale"

RDEPENDS_${PN} += "${PYTHON_PN}-misc ${PYTHON_PN}-pprint ${PYTHON_PN}-selectors \
                   ${PYTHON_PN}-enum ${PYTHON_PN}-threading ${PYTHON_PN}-shell \
                   ${PYTHON_PN}-xml ${PYTHON_PN}-subprocess ${PYTHON_PN}-vobject \
                   ${PYTHON_PN}-json  ${PYTHON_PN}-setuptools"

FILES:${PN} += "${datadir} ${systemd_unitdir}"

do_install:append () {
	install -d ${D}${datadir}/radicale/
	install -m755 radicale.wsgi ${D}${datadir}/radicale/
	install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/radicale.service ${D}${systemd_unitdir}/system/
    install -d ${D}${sysconfdir}/nginx/locations
    install -m 0644 ${WORKDIR}/radicale_location.conf ${D}${sysconfdir}/nginx/locations/
}

pkg_postinst_ontarget:${PN} () {
        mkdir -p /var/lib/radicale/collections
        chown -R radicale:radicale /var/lib/radicale
        chmod -R o-r /var/lib/radicale/collections
}

SYSTEMD_SERVICE_${PN} = "radicale.service"
