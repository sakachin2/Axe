#########
CD:= $(call my-dir)
#########
LOCAL_PATH:= $(CD)
include $(CLEAR_VARS)
############################################
#XXELPATH=../libs/armeabi
XXELPATH=../jnilibs
LOCAL_MODULE :=static-xxe-prebuilt
LOCAL_SRC_FILES :=$(XXELPATH)/libxxe.a
include $(PREBUILT_STATIC_LIBRARY)
include $(CLEAR_VARS)
############################################
#ULIBLPATH=../libs/armeabi
ULIBLPATH=../jnilibs
LOCAL_MODULE :=static-ulibxxe-prebuilt
LOCAL_SRC_FILES :=$(ULIBLPATH)/libuserxxe.a
include $(PREBUILT_STATIC_LIBRARY)
include $(CLEAR_VARS)
#############################################
DEBUG_RELEASE=debug

ANDROID_NDK=c:/Users/sakstyle/AppData/Local/Android/sdk/ndk-bundle

#ARMEABI=armeabi-v7a
#ICONVLPATH=../libs/armeabi
ICONVLPATH=../jnilibs
#ICONVLPATH=x:/AndroidStudioProjects/Aiconv114/app/.externalnativeBuild/cmake/$(DEBUG_RELEASE)/$(ARMEABI)

#INC_UNWIND=$(ANDROID_NDK)/sources/cxx-stl/gabi++/include

LOCAL_MODULE :=static-iconv-prebuilt
LOCAL_SRC_FILES :=$(ICONVLPATH)/libiconv.a
include $(PREBUILT_STATIC_LIBRARY)
include $(CLEAR_VARS)
#############################################
#include  $(CD)/gxe/Android.mk
#include  $(call all-subdir-makefiles)
############################################
TRACE=-DNOTRACE
LOCAL_PATH:= $(CD)
include $(CLEAR_VARS)
#APP_OPTIM:=release      #use cmdline "make APP-OPTIOM=release"
#		-funsigned-char -Wall -Wformat=0 -gstabs+  #AndroidStudio:"-gstabs+" not supported 
#   	-Wmaybe-uninitialized 
#		-fexceptions    :default is Yes for cpp No for C
cflags= -DARM -DARMAPI9 -DXXE -DUNX -DLNX -DGTK -DUTF8SUPPH -DUTF8UCS2 -DUTF8EBCD $(TRACE) $(DPRINTF) $(GPROF) \
		-D_FILE_OFFSET_BITS=64 \
		-D__ANDROID__ \
		-DLNXLC2 -DLNXLC2TL6 -DUTF8SUPP -DMALLOCCHK \
		-funsigned-char -Wall -Wformat=0          \
		-fexceptions        \
		-Wno-pointer-sign \
		-Wuninitialized $(OPT)\
		-Wunreachable-code    \
		-Wno-invalid-source-encoding \
        -v \
		-g3 -O0
#		-funsigned-char -Wall -Wformat=0 -g -O0

LOCAL_CFLAGS :=  $(cflags)
#LOCAL_CFLAGS :=  -E
#########
GXE=gxe
#ULIBH=/g/src/ulib
ULIBH=g:\src\ulib
#ROOT=../../../../
#ULIB=$(ROOT)g/src/ulib
#XE=/g/src/xe
XE=g:\src\xe
#ICONVTOP=/home2/androidndk/Projects/Aiconv/jni/libiconv_install
#ICONVTOP=/home3/androidndk/Projects/Aiconv/jni/libiconv_install
ICONVTOP=x:/AndroidStudioProjects/libiconv-1.14.cfgathome3
ICONVH=$(ICONVTOP)/include

#########
srcgxe=\
  $(GXE)/gxe.c     \
  $(GXE)/gxemfrm.c \
  $(GXE)/gxeview.c \
  $(GXE)/xxecsub.c \
  $(GXE)/xxecsub2.c\
  $(GXE)/xxefile.c \
  $(GXE)/xxekbd.c  \
  $(GXE)/xxemain.c \
  $(GXE)/xxemouse.c\
  $(GXE)/xxescr.c  \
  $(GXE)/xxexei.c  \
  $(GXE)/xxexei2.c \
  $(GXE)/xxexei3.c
# $(GXE)/gxedlg.c        \
# $(GXE)/gxepage.c        \
# $(GXE)/main.c        \
# $(GXE)/xxeprt.c      \
# $(GXE)/xxexei.c      \

LOCAL_SRC_FILES +=$(srcgxe)


LOCAL_C_INCLUDES := $(CD)  \
                    $(CD)/$(GXE)  \
					$(ICONVH)  \
					$(ULIBH) \
					$(XE) \
					$(INC_UNWIND)

LOCAL_MODULE    := axejni
srcjni=\
 jnij2c.c \
 jnic2j.c \
 jnicsub2.c \
 jnia.c   \
 jnig.c   \
 jnismb.c \
 jnisigh.c \
 jniu.c
LOCAL_SRC_FILES += $(srcjni)
#@@@1:LOCAL_LDLIBS    := -llog -L../libs/armeabi -lxxe -luserxxe -liconv
LOCAL_LDLIBS    := -llog
#LOCAL_STATIC_LIBRARIES := ../libs/armeabi/libuserxxe.a
LOCAL_STATIC_LIBRARIES := static-xxe-prebuilt static-ulibxxe-prebuilt static-iconv-prebuilt

include $(BUILD_SHARED_LIBRARY)

