## gnugo

LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_CPP_EXTENSION:=.cpp .cc
LOCAL_CPPFLAGS := -DHL_DATA_DIR=\"/usr/share/highlight/\" -DHL_CONFIG_DIR=\"/etc/highlight/\"
SRCTOP:=highlight-2.16/src
STDCXX=/home3/androidndk/android-ndk-r7/sources/cxx-stl/gnu-libstdc++

STDCXX_INC1=$(STDCXX)/include
STDCXX_INC2=$(STDCXX)/libs/armeabi/include
LOCAL_C_INCLUDES := $(LOCAL_PATH)/$(SRCTOP)/core $(STDCXX_INC1) $(STDCXX_INC2)
#CXXH=/home3/androidndk/android-ndk-r7/sources/cxx-stl/stlport
#CXXH_INC1=$(CXXH)/stlport
#LOCAL_C_INCLUDES := $(LOCAL_PATH)/$(SRCTOP)/core $(CXXH_INC1)

STDCXX_LIB1=$(STDCXX)/libs/armeabi
LOCAL_LDLIBS:=-L$(STDCXX_LIB1) -lgnustl_static

P=$(SRCTOP)/core
src= \
 $(P)/configurationreader.cpp        \
 $(P)/stylecolour.cpp                \
 $(P)/stringtools.cpp                \
 $(P)/xhtmlgenerator.cpp             \
 $(P)/latexgenerator.cpp             \
 $(P)/texgenerator.cpp               \
 $(P)/rtfgenerator.cpp               \
 $(P)/htmlgenerator.cpp              \
 $(P)/ansigenerator.cpp              \
 $(P)/xmlgenerator.cpp               \
 $(P)/svggenerator.cpp               \
 $(P)/codegenerator.cpp              \
 $(P)/xterm256generator.cpp          \
 $(P)/bbcodegenerator.cpp            \
 $(P)/languagedefinition.cpp         \
 $(P)/elementstyle.cpp               \
 $(P)/documentstyle.cpp              \
 $(P)/datadir.cpp                    \
 $(P)/preformatter.cpp               \
 $(P)/platform_fs.cpp                \
 $(P)/ctagsreader.cpp
LOCAL_SRC_FILES += $(src)

P=$(SRCTOP)/core/astyle
src := \
 $(P)/ASStreamIterator.cpp           \
 $(P)/ASResource.cpp                 \
 $(P)/ASFormatter.cpp                \
 $(P)/ASBeautifier.cpp               \
 $(P)/ASEnhancer.cpp
LOCAL_SRC_FILES += $(src)

P=$(SRCTOP)/core/re
src := \
 $(P)/Pattern.cpp                    \
 $(P)/Matcher.cpp
LOCAL_SRC_FILES += $(src)

P=$(SRCTOP)/cli
src := \
 $(P)/arg_parser.cc                  \
 $(P)/cmdlineoptions.cpp             \
 $(P)/help.cpp                       \
 $(P)/main.cpp
LOCAL_SRC_FILES += $(src)

#include $(BUILD_STATIC_LIBRARY)

#wrapper for dynamic link ####################
LOCAL_MODULE    := highlight
include $(BUILD_EXECUTABLE)
