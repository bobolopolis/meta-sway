SUMMARY = "A modular Wayland compositor library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7578fad101710ea2d289ff5411f1b818"

DEPENDS = " \
    libinput \
    pixman \
    virtual/mesa \
    wayland \
    wayland-native \
    wayland-protocols \
"

SRC_URI = "https://github.com/swaywm/wlroots/releases/download/${PV}/${BP}.tar.gz"
SRC_URI[sha256sum] = "b84baefbaff7bb04b3d2c43cbacef1a433e2cd65111f8fbf4bfc5faaa4b34b08"

inherit meson

PACKAGECONFIG ??= " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xwayland', '', d)} \
"
PACKAGECONFIG[systemd] = "-Dlogind=enabled -Dlogind-provider=systemd,-Dlogind=disabled"
PACKAGECONFIG[xcb-errors] = "-Dxcb-errors=enabled,-Dxcb-errors=disabled"
PACKAGECONFIG[xcb-icccm] = "-Dxcb-icccm=enabled,-Dxcb-icccm=disabled"
PACKAGECONFIG[xwayland] = "-Dxwayland=enabled,-Dxwayland=disabled"
PACKAGECONFIG[examples] = "-Dexamples=true,-Dexamples=false"
