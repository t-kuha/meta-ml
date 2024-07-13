#
# OpenBLAS
# 
SUMMARY = "OpenBLAS"
DESCRIPTION = "OpenBLAS is an optimized BLAS library based on GotoBLAS2 1.13 BSD version."

S = "${WORKDIR}/git"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=5adf4792c949a00013ce25d476a2abc0"

# Version to use
SRCREV = "ce3f668c992cb3cc80849d5c30ed637f5adbd5b2"
PV = "0.3.27"
PR = "r0"

SRC_URI = "\
    git://github.com/xianyi/OpenBLAS.git;protocol=https;branch=develop \
"

inherit cmake pkgconfig

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
    -DNOFORTRAN=1 \
    -DBUILD_SHARED_LIBS=1 \
"
