#Author: chetan@novelmicrodevices.com
#Date: 8/12/2024
SUMMARY = "A Python bindings generator for C/C++ libraries"
HOMEPAGE = "https://www.riverbankcomputing.com/software/sip/"
LICENSE = "GPL-2.0-or-later"
SECTION = "devel"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ed1d69a33480ebf4ff8a7a760826d84e"

inherit pypi setuptools3 python3native

PYPI_PACKAGE = "sip"
SRC_URI[sha256sum] = "7fc959e48e6ec5d5af8bd026f69f5e24d08b3cb8abb342176f5ab8030cc07d7a"

DEPENDS += " \
    python3-pip-native \
    python3-setuptools-native \
    python3-wheel-native \
"

RDEPENDS_${PN} += "python3-setuptools (>=67.6.0)"

S = "${WORKDIR}/sip-${PV}"

do_configure:prepend() {
    # Create a simple setup.py file
    cat <<EOF > ${S}/setup.py
from setuptools import setup, find_packages

setup(
    name='sip',
    version='6.8.6',
    packages=find_packages(),
    install_requires=[],
)
EOF
}

do_compile() {
    export PIP_NO_INDEX=1
    export PIP_FIND_LINKS="file://${STAGING_DIR_NATIVE}/${PYTHON_SITEPACKAGES_DIR}/pip"
    
    # Print diagnostic information
    echo "PYTHONPATH: $PYTHONPATH"
    python3 -c "import sys; print('Python sys.path:', sys.path)"
    python3 -c "import setuptools; print('Setuptools version:', setuptools.__version__)"
    
    # Build the package using setup.py
    ${PYTHON} setup.py bdist_wheel
}

do_install() {
    pip3 install --no-deps --prefix=${D}${prefix} ${S}/dist/*.whl
}

BBCLASSEXTEND = "native"