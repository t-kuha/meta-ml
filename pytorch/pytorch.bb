#
# PyTorch
#

SUMMARY = "PyTorch"
DESCRIPTION = "Tensors and Dynamic neural networks in Python with strong GPU acceleration"

# Version to use
PV = "1.13.0"
PR = "r0"

S = "${WORKDIR}/git"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=5c853508d63a8090fa952ff1af58217d"

SRC_URI = " \
    gitsm://github.com/pytorch/pytorch.git;protocol=https;nobranch=1;tag=v${PV} \
    file://0001-Add-support-for-Yocto-build.patch \
    file://0002-Quick-fix-for-Yocto-build.patch \
"

RDEPENDS:${PN} += "protobuf gflags glog python3-numpy "
DEPENDS += " \
    python3-native \
    python3-pip-native \
    python3-numpy \
    protobuf-native \
    sleef-native \
    coreutils-native \
    protobuf \
"

inherit cmake setuptools3

do_configure:prepend(){
    # install required modules
    pip3 install pyyaml typing_extensions opt-einsum
}

do_compile() {
    export _GLIBCXX_USE_CXX11_ABI=1
    export NATIVE_BUILD_DIR=${STAGING_DIR_NATIVE}/usr
    export CAFFE2_CUSTOM_PROTOC_EXECUTABLE=${STAGING_DIR_NATIVE}/usr/bin/protoc
    export USE_CUDA=OFF
    export USE_DISTRIBUTED=OFF
    export USE_NUMPY=ON
    export USE_XNNPACK=OFF
    export BUILD_CUSTOM_PROTOBUF=OFF
    export PYTHON_EXECUTABLE=${PYTHON}
    export CMAKE_SYSTEM_NAME=Linux
    export CMAKE_BUILD_TYPE=Release
    export CMAKE_SYSTEM_PROCESSOR=aarch64
    export NUMPY_INCLUDE_DIR=${PKG_CONFIG_SYSROOT_DIR}/usr/lib/python3.9/site-packages/numpy/core/include
    export CMAKE_PREFIX_PATH=${STAGING_DIR_TARGET}/usr
    export CAFFE2_CPU_FLAGS="-mfpu=neon -mfloat-abi=hard"
    distutils3_do_compile
}
