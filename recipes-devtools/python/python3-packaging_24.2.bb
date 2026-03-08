SUMMARY = "Core utilities for Python packages"
HOMEPAGE = "https://pypi.org/project/packaging/"
LICENSE = "Apache-2.0 | BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=faadaedca9251a90b205c9167578ce91"

PYPI_PACKAGE = "packaging"
SRC_URI[sha256sum] = "c228a6dc5e932d346bc5739379109d49e8853dd8223571c7c5b55260edc0b97f"

inherit pypi python3native

# Use flit_core directly (avoid python3-build-native dependency loop)
DEPENDS += "python3-flit-core-native python3-installer-native"

do_compile() {
    ${PYTHON} -m flit_core.wheel --outdir dist
}

do_install() {
    ${PYTHON} -m installer --destdir=${D} dist/*.whl
}

BBCLASSEXTEND = "native nativesdk"
