SUMMARY = "i3-compatible Wayland compositor"
HOMEPAGE = "https://swaywm.org"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dfc67e5b1fa10ebb4b70eb0c0ca67bea"

DEPENDS = " \
    cairo \
    json-c \
    libevdev \
    libinput \
    libpcre \
    libxkbcommon \
    pango \
    wayland \
    wayland-native \
    wayland-protocols \
    wlroots \
"

SRC_URI = "https://github.com/swaywm/sway/releases/download/${PV}/${BP}.tar.gz"
SRC_URI[sha256sum] = "e2805291fc01d49e21dda4b273e38170d1fff4e1757215439729edbed880dfbe"

inherit bash-completion features_check gtk-icon-cache meson

REQUIRED_DISTRO_FEATURES = "pam"

EXTRA_OEMESON = "-Dman-pages=disabled"

PACKAGECONFIG ??= " \
    wallpaper \
    bash-completions \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xwayland', '', d)} \
    tray \
    gdk-pixbuf \
"

PACKAGECONFIG[wallpaper] = "-Ddefault-wallpaper=true,-Ddefault-wallpaper=false"
PACKAGECONFIG[zsh-completions] = "-Dzsh-completions=true,-Dzsh-completions=false"
PACKAGECONFIG[bash-completions] = "-Dbash-completions=true,-Dbash-completions=false"
PACKAGECONFIG[fish-completions] = "-Dfish-completions=true,-Dfish-completions=false"
PACKAGECONFIG[xwayland] = "-Dxwayland=enabled,-Dxwayland=disabled,,xkeyboard-config"
PACKAGECONFIG[tray] = "-Dtray=enabled,-Dtray=disabled"
PACKAGECONFIG[gdk-pixbuf] = "-Dgdk-pixbuf=enabled,-Dgdk-pixbuf=disabled,gdk-pixbuf"

PACKAGES += "${PN}-fish-completion ${PN}-zsh-completion"

FILES_${PN} += " \
    ${datadir}/backgrounds/sway \
    ${datadir}/wayland-sessions \
"

FILES_${PN}-fish-completion = "${datadir}/fish"
FILES_${PN}-zsh-completion = "${datadir}/zsh"
