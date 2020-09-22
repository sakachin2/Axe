# -------------------------------------------------
# Project created by QtCreator 2009-03-03T22:45:06
# -------------------------------------------------
TARGET = highlight-gui
TEMPLATE = app
INCLUDEPATH += . \
    ../core/
win32:CONFIG += static
CONFIG += precompile_header
PRECOMPILED_HEADER = precomp.h
DEFINES += O2 QT
win32:DESTDIR = ../../
unix:DESTDIR = ../
SOURCES += main.cpp \
    mainwindow.cpp \
    io_report.cpp \
    showtextfile.cpp
HEADERS += mainwindow.h \
    ../core/*.h \
    precomp.h \
    io_report.h \
    showtextfile.h
FORMS += mainwindow.ui \
    io_report.ui \
    showtextfile.ui
RESOURCES = highlight-gui.qrc
TRANSLATIONS = highlight_de_DE.ts highlight_es_ES.ts

win32:RC_FILE = highlight-gui.rc
win32:LIBS += -L../.. -lhighlight
unix:LIBS += -L.. -lhighlight

win32:QMAKE_POST_LINK = f:/upx/upx.exe --best ../../highlight-gui.exe
#unix {
#DEFINES += DATA_DIR=\\\"/usr/share/highlight\\\" \
#    CONFIG_DIR=\\\"/etc/highlight\\\" \
#    DOC_DIR=\\\"/usr/share/doc/highlight\\\"
#    message("setting unix default paths")
#}
