#
# TensorFlow Lite (TF v1)
# 

SUMMARY = "TensorFLow Lite"
DESCRIPTION = "TensorFlow Lite is TensorFlow's lightweight solution for mobile and embedded devices."

# TFLite version to use
SRCREV = "3db52be7be81a87c623cdeb7f03d3767521c5246"
PV = "1.15.5"
PR = "r0"

S = "${WORKDIR}/git"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=64a34301f8e355f57ec992c2af3e5157"

DEPENDS = "wget-native unzip-native "

SRC_URI = "\
    git://github.com/tensorflow/tensorflow.git;protocol=https;branch=r1.15 \
    file://0001-Update-download_dependencies.sh.patch \
    file://0002-Fix-compile-error-and-warning.patch \
    "

do_compile[network] = "1"
do_compile(){
    ${S}/tensorflow/lite/tools/make/download_dependencies.sh
    oe_runmake -f ${S}/tensorflow/lite/tools/make/Makefile \
    TARGET_ARCH=${OECORE_TARGET_ARCH}
}

do_install(){
    install -d ${D}${bindir}
    install -d ${D}${libdir}

    install -m 755 ${S}/tensorflow/lite/tools/make/gen/linux_/bin/* ${D}${bindir}
    install -m 755 ${S}/tensorflow/lite/tools/make/gen/linux_/lib/* ${D}${libdir}
}
