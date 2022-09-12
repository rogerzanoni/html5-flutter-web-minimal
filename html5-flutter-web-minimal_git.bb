SUMMARY     = "AGL HTML5 web demo"
HOMEPAGE    = "https://"
SECTION     = "apps"
LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b1e01b26bacfc2232046c90a330332b3"

PV      = "1.0+git${SRCPV}"
S       = "${WORKDIR}/git"
B       = "${WORKDIR}/build"

SRC_URI = "git:///home/zanoni/projects/github/flutter_agl_web_demo/;protocol=file;branch=main"

SRCREV = "a27cff29f6fc0489203adcfc8c56b9956410b0fd"

inherit pythonnative agl-app

AGL_APP_TEMPLATE = "agl-app-web"
AGL_APP_ID = "webapps-flutter-web-demo"
AGL_APP_NAME = "HTML5 flutter demo"

DEPENDS = "flutter-sdk-native"

FLUTTER_SDK = "${STAGING_DIR_NATIVE}/usr/share/flutter/sdk"

do_compile[network] = "1"
do_compile() {
  export PATH=${FLUTTER_SDK}/bin:$PATH
  cd ${S}
  flutter build web
}

WAM_APPLICATIONS_DIR = "${libdir}/wam_apps"

do_install() {
  install -d ${D}${WAM_APPLICATIONS_DIR}/${PN}
  cp -R --no-dereference --preserve=mode,links ${S}/build/web/* ${D}${WAM_APPLICATIONS_DIR}/${PN}
}

FILES:${PN} = "${WAM_APPLICATIONS_DIR}/${PN}"
