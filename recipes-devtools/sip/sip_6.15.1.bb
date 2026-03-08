SUMMARY = "A Python bindings generator for C/C++ libraries"
HOMEPAGE = "https://www.riverbankcomputing.com/software/sip/"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=236276327275fdb261636fb40b18d88d"

inherit python3native

SRC_URI = "\
    https://files.pythonhosted.org/packages/source/s/sip/sip-${PV}.tar.gz;name=sdist \
    https://files.pythonhosted.org/packages/1c/7d/136d2f521fa6d9b9921e41d141be605ad57f32a055ff7141ebd63b830bfc/sip-6.15.1-py3-none-any.whl;name=wheel \
"
SRC_URI[sdist.sha256sum] = "dc2e58c1798a74e1b31c28e837339822fe8fa55288ae30e8986eb28100ebca5a"
SRC_URI[wheel.sha256sum] = "596cd4b306cf11de469f3f638b24c592763b2d8accb127170b0db95c530b0700"

S = "${WORKDIR}/sip-${PV}"

DEPENDS += "python3-pip-native"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    export PIP_NO_INDEX=1
    export PIP_DISABLE_PIP_VERSION_CHECK=1
    ${PYTHON} -m pip install --no-deps --prefix=${D}${prefix} \
        ${WORKDIR}/sip-${PV}-py3-none-any.whl
}

FILES:${PN} += " ${PYTHON_SITEPACKAGES_DIR} "

BBCLASSEXTEND = "native nativesdk"
