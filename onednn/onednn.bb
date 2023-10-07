#
# oneDNN
#

SUMMARY = "oneDNN"
DESCRIPTION = "oneAPI Deep Neural Network Library (oneDNN)"

# oneDNN version to use
SRCREV = "08fea71aff4c273e34579e86396405f95d34aa74"
PV = "3.3"
PR = "r0"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=3b64000f6e7d52516017622a37a94ce9"

S = "${WORKDIR}/git"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI = "\
    git://github.com/oneapi-src/oneDNN.git;protocol=https;branch=rls-v3.3 \
    file://0001-Add-support-for-Yocto-build.patch \
"

DEPENDS += " arm-computelibrary"

inherit cmake

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
    -DACL_ROOT_DIR=${PKG_CONFIG_SYSROOT_DIR}/usr \
    -DDNNL_AARCH64_USE_ACL=ON \
"
