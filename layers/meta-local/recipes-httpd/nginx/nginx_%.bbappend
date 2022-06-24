FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://default_server.site"

do_install:append () {
    install -d ${D}${sysconfdir}/nginx/locations/
}
