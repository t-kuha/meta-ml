#
# TVM runtime
#

SUMMARY = "TVM runtime"
DESCRIPTION = "Tensors and Dynamic neural networks in Python with strong GPU acceleration"

# Version to use
SRCREV = "22a9d388d441dbfd917d032564e2a1bccacd5f8c"
PV = "0.18.0"
PR = "r0"

S = "${WORKDIR}/git"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=acf34edaf6d880cc3b205d19eda0c95c"

SRC_URI = " \
    gitsm://github.com/apache/tvm.git;protocol=https;branch=main \
"

DEPENDS += " \
    python3-native \
"

inherit cmake 

EXTRA_OECMAKE = "\
    -DUSE_VITIS_AI=OFF \
    -DUSE_LIBBACKTRACE=OFF \
    -DCMAKE_BUILD_TYPE=Release \
"

do_configure:prepend(){
    echo set\(USE_LLVM OFF\) > ${S}/config.cmake
}

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
