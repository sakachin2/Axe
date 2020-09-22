# Simple Makefile for Highlight
# This file will compile the highlight library and binaries.
# See INSTALL for instructions.

# Add -DHL_DATA_DIR=\"/your/path/\" to CFLAGS if you want to define a
# custom installation directory not listed in INSTALL.
# Copy *.conf, ./langDefs, ./themes amd ./indentSchemes to /your/path/.
# See ../makefile for the definition of ${data_dir}

# Add -DHL_CONFIG_DIR=\"/your/path/\" to define the configuration directory
# (default: /etc/highlight)

# Add -DCONFIG_FILE_PATH=\"/your/path/.highlightrc\" if you want to define a
# custom path to the highlight configuration file (default: $HOME/.highlightrc)

# See src/gui-qt/highlight.pro for the Qt GUI compilation options

CXX=c++

QMAKE=qmake

CFLAGS:=-O2 ${CFLAGS}
#CFLAGS:=-ggdb ${CFLAGS}

SO_VERSION=2.13

# Source paths
CORE_DIR=./core/
CLI_DIR=./cli/
GUI_QT_DIR=./gui-qt/

# Third-Party software paths
ASTYLE_DIR=${CORE_DIR}astyle/
REGEX_DIR=${CORE_DIR}re/

ifndef HL_CONFIG_DIR
	HL_CONFIG_DIR = /etc/highlight/
endif
ifndef HL_DATA_DIR
	HL_DATA_DIR = /usr/share/highlight/
endif
ifdef PIC
	CFLAGS+=-fPIC
endif

# Do not strip by default (Mac OS X lazy pointer issues)
# Add -static to avoid linking with shared libs (can cause trouble when highlight
# is run as service)
#LDFLAGS = 
#LDFLAGS = ${LDFLAGS} -s
#LDFLAGS= -Wl,--as-needed

CXX_COMPILE=${CXX} ${CFLAGS} -c -I ${CORE_DIR}

# Data directories (data dir, configuration file dir)
CXX_DIR=-DHL_DATA_DIR=\"${HL_DATA_DIR}\" -DHL_CONFIG_DIR=\"${HL_CONFIG_DIR}\"

AR=ar
ARFLAGS=-crs

# objects files to build the library
CORE_OBJECTS:=configurationreader.o stylecolour.o stringtools.o \
	xhtmlgenerator.o latexgenerator.o texgenerator.o rtfgenerator.o \
	htmlgenerator.o ansigenerator.o xmlgenerator.o svggenerator.o codegenerator.o \
	xterm256generator.o bbcodegenerator.o \
	languagedefinition.o elementstyle.o documentstyle.o \
	datadir.o preformatter.o platform_fs.o ctagsreader.o\
	ASStreamIterator.o ASResource.o ASFormatter.o ASBeautifier.o ASEnhancer.o\
	Pattern.o Matcher.o

# command line interface
CLI_OBJECTS:=arg_parser.o cmdlineoptions.o main.o help.o

# Qt user interface
GUI_OBJECTS:=${GUI_QT_DIR}main.cpp ${GUI_QT_DIR}mainwindow.cpp ${GUI_QT_DIR}io_report.cpp ${GUI_QT_DIR}showtextfile.cpp


cli: libhighlight.a ${CLI_OBJECTS}
	${CXX} ${LDFLAGS} -o highlight ${CLI_OBJECTS} -L. -lhighlight

lib-static libhighlight.a: ${CORE_OBJECTS}
	${AR} ${ARFLAGS} libhighlight.a ${CORE_OBJECTS}

lib-shared libhighlight.so.1.0: ${CORE_OBJECTS}
	ld -shared -soname libhighlight.so.1 -o libhighlight.so.${SO_VERSION} -lc ${CORE_OBJECTS}

gui-qt: highlight-gui

highlight-gui: libhighlight.a ${GUI_OBJECTS}
	cd gui-qt && \
	${QMAKE} 'DEFINES+=DATA_DIR=\\\"${HL_DATA_DIR}\\\" CONFIG_DIR=\\\"${HL_CONFIG_DIR}\\\" DOC_DIR=\\\"${HL_DOC_DIR}\\\"' && \
	make

$(OBJECTFILES) : makefile

ansigenerator.o: ${CORE_DIR}ansigenerator.cpp ${CORE_DIR}ansigenerator.h ${CORE_DIR}codegenerator.h
	${CXX_COMPILE} ${CORE_DIR}ansigenerator.cpp

ASBeautifier.o: ${ASTYLE_DIR}ASBeautifier.cpp ${ASTYLE_DIR}astyle.h
	${CXX_COMPILE} ${ASTYLE_DIR}ASBeautifier.cpp

ASFormatter.o: ${ASTYLE_DIR}ASFormatter.cpp ${ASTYLE_DIR}astyle.h
	${CXX_COMPILE} ${ASTYLE_DIR}ASFormatter.cpp

ASResource.o: ${ASTYLE_DIR}ASResource.cpp ${ASTYLE_DIR}astyle.h
	${CXX_COMPILE} ${ASTYLE_DIR}ASResource.cpp

ASEnhancer.o: ${ASTYLE_DIR}ASResource.cpp ${ASTYLE_DIR}astyle.h
	${CXX_COMPILE} ${ASTYLE_DIR}ASEnhancer.cpp

ASStreamIterator.o: ${ASTYLE_DIR}ASStreamIterator.cpp ${ASTYLE_DIR}astyle.h
	${CXX_COMPILE} ${ASTYLE_DIR}ASStreamIterator.cpp

cmdlineoptions.o: ${CLI_DIR}cmdlineoptions.cpp ${CLI_DIR}cmdlineoptions.h
	${CXX_COMPILE} ${CLI_DIR}cmdlineoptions.cpp

codegenerator.o: ${CORE_DIR}codegenerator.cpp ${CORE_DIR}codegenerator.h ${CORE_DIR}languagedefinition.h \
  ${CORE_DIR}configurationreader.h ${CORE_DIR}stringtools.h ${CORE_DIR}enums.h ${CORE_DIR}documentstyle.h \
  ${CORE_DIR}elementstyle.h ${CORE_DIR}stylecolour.h ${ASTYLE_DIR}astyle.h ${CORE_DIR}preformatter.h \
  ${CORE_DIR}htmlgenerator.h ${CORE_DIR}version.h ${CORE_DIR}charcodes.h ${CORE_DIR}xhtmlgenerator.h ${CORE_DIR}rtfgenerator.h \
  ${CORE_DIR}latexgenerator.h ${CORE_DIR}texgenerator.h ${CORE_DIR}ansigenerator.h
	${CXX_COMPILE} ${CORE_DIR}codegenerator.cpp

configurationreader.o: ${CORE_DIR}configurationreader.cpp ${CORE_DIR}configurationreader.h \
  ${CORE_DIR}stringtools.h
	${CXX_COMPILE} ${CORE_DIR}configurationreader.cpp

datadir.o: ${CORE_DIR}datadir.cpp ${CORE_DIR}datadir.h ${CORE_DIR}platform_fs.h
	${CXX_COMPILE} ${CORE_DIR}datadir.cpp ${CXX_DIR}

platform_fs.o: ${CORE_DIR}platform_fs.cpp ${CORE_DIR}platform_fs.h
	${CXX_COMPILE} ${CORE_DIR}platform_fs.cpp

documentstyle.o: ${CORE_DIR}documentstyle.cpp ${CORE_DIR}documentstyle.h ${CORE_DIR}configurationreader.h \
  ${CORE_DIR}stringtools.h ${CORE_DIR}elementstyle.h ${CORE_DIR}stylecolour.h
	${CXX_COMPILE} ${CORE_DIR}documentstyle.cpp

elementstyle.o: ${CORE_DIR}elementstyle.cpp ${CORE_DIR}elementstyle.h ${CORE_DIR}stylecolour.h
	${CXX_COMPILE} ${CORE_DIR}elementstyle.cpp

help.o: ${CLI_DIR}help.cpp ${CLI_DIR}help.h
	${CXX_COMPILE} ${CLI_DIR}help.cpp

htmlgenerator.o: ${CORE_DIR}htmlgenerator.cpp ${CORE_DIR}htmlgenerator.h ${CORE_DIR}codegenerator.h
	${CXX_COMPILE} ${CORE_DIR}htmlgenerator.cpp

languagedefinition.o: ${CORE_DIR}languagedefinition.cpp ${CORE_DIR}languagedefinition.h \
  ${CORE_DIR}configurationreader.h ${CORE_DIR}platform_fs.h ${CORE_DIR}enums.h ${CORE_DIR}stringtools.h
	${CXX_COMPILE} ${CORE_DIR}languagedefinition.cpp

latexgenerator.o: ${CORE_DIR}latexgenerator.cpp ${CORE_DIR}latexgenerator.h ${CORE_DIR}codegenerator.h
	${CXX_COMPILE} ${CORE_DIR}latexgenerator.cpp

bbcodegenerator.o: ${CORE_DIR}bbcodegenerator.cpp ${CORE_DIR}bbcodegenerator.h ${CORE_DIR}codegenerator.h
	${CXX_COMPILE} ${CORE_DIR}bbcodegenerator.cpp

preformatter.o: ${CORE_DIR}preformatter.cpp ${CORE_DIR}preformatter.h ${CORE_DIR}stringtools.h
	${CXX_COMPILE} ${CORE_DIR}preformatter.cpp

main.o: ${CLI_DIR}main.cpp ${CLI_DIR}main.h ${CLI_DIR}cmdlineoptions.h ${CORE_DIR}platform_fs.h \
  ${CORE_DIR}configurationreader.h ${CORE_DIR}datadir.h ${CORE_DIR}enums.h ${CORE_DIR}codegenerator.h \
  ${CORE_DIR}languagedefinition.h ${CORE_DIR}documentstyle.h ${CORE_DIR}elementstyle.h \
  ${CORE_DIR}stylecolour.h ${ASTYLE_DIR}astyle.h ${CORE_DIR}preformatter.h \
  ${CLI_DIR}help.h ${CORE_DIR}version.h
	${CXX_COMPILE} ${CLI_DIR}main.cpp ${CXX_DIR}

rtfgenerator.o: ${CORE_DIR}rtfgenerator.cpp ${CORE_DIR}rtfgenerator.h ${CORE_DIR}codegenerator.h
	${CXX_COMPILE} ${CORE_DIR}rtfgenerator.cpp

stringtools.o: ${CORE_DIR}stringtools.cpp ${CORE_DIR}stringtools.h
	${CXX_COMPILE} ${CORE_DIR}stringtools.cpp

stylecolour.o: ${CORE_DIR}stylecolour.cpp ${CORE_DIR}stylecolour.h ${CORE_DIR}enums.h ${CORE_DIR}stringtools.h
	${CXX_COMPILE} ${CORE_DIR}stylecolour.cpp

texgenerator.o: ${CORE_DIR}texgenerator.cpp ${CORE_DIR}texgenerator.h ${CORE_DIR}codegenerator.h
	${CXX_COMPILE} ${CORE_DIR}texgenerator.cpp

xhtmlgenerator.o: ${CORE_DIR}xhtmlgenerator.cpp ${CORE_DIR}xhtmlgenerator.h ${CORE_DIR}htmlgenerator.h \
  ${CORE_DIR}codegenerator.h
	${CXX_COMPILE} ${CORE_DIR}xhtmlgenerator.cpp

xmlgenerator.o: ${CORE_DIR}xmlgenerator.cpp ${CORE_DIR}xmlgenerator.h ${CORE_DIR}codegenerator.h
	${CXX_COMPILE} ${CORE_DIR}xmlgenerator.cpp

svggenerator.o: ${CORE_DIR}svggenerator.cpp ${CORE_DIR}svggenerator.h ${CORE_DIR}codegenerator.h
	${CXX_COMPILE} ${CORE_DIR}svggenerator.cpp

xterm256generator.o: ${CORE_DIR}xterm256generator.cpp ${CORE_DIR}xterm256generator.h ${CORE_DIR}codegenerator.h
	${CXX_COMPILE} ${CORE_DIR}xterm256generator.cpp

Matcher.o:  ${REGEX_DIR}Matcher.cpp ${REGEX_DIR}Matcher.h
	${CXX_COMPILE} ${REGEX_DIR}Matcher.cpp

Pattern.o:  ${REGEX_DIR}Pattern.cpp ${REGEX_DIR}Pattern.h
	${CXX_COMPILE} ${REGEX_DIR}Pattern.cpp

arg_parser.o: ${CLI_DIR}arg_parser.cc ${CLI_DIR}arg_parser.h
	${CXX_COMPILE} ${CLI_DIR}arg_parser.cc

ctagsreader.o: ${CORE_DIR}ctagsreader.cpp ${CORE_DIR}ctagsreader.h
	${CXX_COMPILE} ${CORE_DIR}ctagsreader.cpp


.PHONY: ${GUI_OBJECTS}

clean:
	@rm -f *.o
	@rm -f ./highlight
	@rm -f ./highlight-gui
	@rm -f ./libhighlight.a
	@rm -f ./libhighlight.so.*
	@rm -f ./.deps/*
	@rm -f gui-qt/*.o
	@rm -f gui-qt/Makefile*
	@rm -f gui-qt/object_script.*
	@rm -f gui-qt/ui_*.h gui-qt/qrc_*.cpp gui-qt/moc_*.cpp
	@rm -rf gui-qt/highlight-gui.gch/
