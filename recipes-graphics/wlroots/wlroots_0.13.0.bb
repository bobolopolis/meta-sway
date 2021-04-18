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
SRC_URI[sha256sum] = "f6bea37fd4a6f5e5f552b83d61adae8c73e64b0bcb9ae0ab464ebcd9309d3cf3"

inherit meson

EXTRA_OEMESON = " \
    -Dlibseat=disabled \
    -Dxcb-errors=disabled \
    -Dxwayland=disabled \
"

PACKAGECONFIG ??= " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'systemd x11', d)} \
    xdg-foreign \
"
PACKAGECONFIG[systemd] = "-Dlogind=enabled -Dlogind-provider=systemd,-Dlogind=disabled"
PACKAGECONFIG[x11] = "-Dx11-backend=enabled,-Dx11-backend=disabled,xcb-util-renderutil"
PACKAGECONFIG[examples] = "-Dexamples=true,-Dexamples=false"
PACKAGECONFIG[xdg-foreign] = "-Dxdg-foreign=enabled,-Dxdg-foreign=disabled"
