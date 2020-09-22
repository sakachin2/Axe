#==exec-compile==arm-linux-androideabi-gcc
#-c
#-o
#uregex.o
#-I/home3/androidndk/android-ndk-r9d/toolchains/arm-linux-androideabi-4.8/../../sources/wchar-support/include
#-I/home3/androidndk/android-ndk-r9d/toolchains/arm-linux-androideabi-4.8/../../platforms/android-9/arch-arm/usr/include
#-I/home3/androidndk/android-ndk-r9d/toolchains/arm-linux-androideabi-4.8/prebuilt/linux-x86/lib/gcc/arm-linux-androideabi/4.4.3/include
#-D__ARM_ARCH_5__
#-D__ARM_ARCH_5T__
#-D__ARM_ARCH_5E__
#-D__ARM_ARCH_5TE__
#-DANDROID
#-DSK_RELEASE
#-DNDEBUG
#-UDEBUG
#-march=armv5te
#-mtune=xscale
#-msoft-float
#-mthumb-interwork
#-fpic
#-fno-exceptions
#-ffunction-sections
#-funwind-tables
#-fstack-protector
#-fmessage-length=0
#-Wall
#-Wno-unused
#-Wno-multichar
#-Wno-pointer-sign
#-Wuninitialized
#-Wmaybe-uninitialized
#-Wstrict-aliasing=2
#-O0
#-finline-functions
#-finline-limit=300
#-fno-inline-functions-called-once
#-fgcse-after-reload
#-frerun-cse-after-loop
#-frename-registers
#-fomit-frame-pointer
#-fstrict-aliasing
#-funswitch-loops
#-gstabs+
#-funsigned-char
#-g3
#-DLNX
#-DUNX
#-DARM
#-DARMAPI9             
#-DNOMT
#-DNOMT_TRACE
#-DANDROID
#-DLNXLC2
#-DLNXLC2TL6
#-DXXE
#-DGTK
#-DNOICONV              Not Used
#-DUTF8SUPPH
#-DUTF8UCS2
#-DUTF8EBCD
#-DNOTRACE
#-D_FILE_OFFSET_BITS=64
#-I/g/src/ulib
#-I/g/src/ulib/gnuregex
#-I/rh9j/home/sak/Projects/gxe/src
#-I/home3/androidndk/Projects/Aiconv/jni/libiconv_install/include
#-I/g/src/ulib/icuLinux/include
#-I/home3/AndroidStudioProjects/Axe/app/src/main/jni
#/g/src/ulib/uregex.c
#####
##-DANDROID
##-DSK_RELEASE
##-DNDEBUG
##-UDEBUG
#####################################################################
# Sets the minimum version of CMake required to build the native
# library. You should either keep the default value or only pass a
# value of 3.4.0 or lower.

#cmake_minimum_required(VERSION 3.4.1)


set(ULIB g:/src/ulib)
#set(OPTDEBUG "-DTRACE -g3") 
set(OPTDEBUG ${OPT_TRACE}) 
#set(UDEFAPI "-D__ANDROID_API__=${ANDROID_PLATFORM}") 
set(UDEFAPI "-D__ANDROID_API__=${ANDROID_NATIVE_API_LEVEL}") 
#set(UDEF "-DLNX -DUNX -DARM -DNOMT -DNOMT_TRACE -DANDROID -DLNXLC2 -DLNXLC2TL6 -DXXE -DGTK -DNOICONV -DUTF8SUPPH -DUTF8UCS2 -DUTF8EBCD -D_FILE_OFFSET_BITS=64 -DARMAPI9 ${OPTDEBUG}"
set(UDEF "-DLNX -DUNX -DARM -DNOMT -DNOMT_TRACE -DANDROID -DLNXLC2 -DLNXLC2TL6 -DXXE -DGTK -DNOICONV -DUTF8SUPPH -DUTF8UCS2 -DUTF8EBCD -D_FILE_OFFSET_BITS=64 -D__USE_FILE_OFFSET64 -DARMAPI9 ${OPTDEBUG}"
		) 

#set(CFLGS  "-march=armv7-a -fpic -msoft-float -fno-exceptions -fstack-protector -Wall -Wno-pointer-sign -Wuninitialized -O0 -funsigned-char")
 set(CFLGS  "               -fpic              -fno-exceptions -fstack-protector -Wall -Wno-pointer-sign -Wuninitialized -O0 -funsigned-char")
#set(CFLGS2 "-finput-charset=cp932")
set(CFLGS2 "-Wno-invalid-source-encoding")
set(AXE_TOP x:/AndroidStudioProjects/Axe)
#set(AXE_JNI ${AXE_TOP}/app/src/main/jni)
set(AXE_JNI ${AXE_TOP}/app/src/main/cpp/jni)
##################
include_directories(
				${INCLUDE_ULIB_UPDATE}
				${INCLUDE_ICONVH}
             	${ULIB}
             	${ULIB}/gnuregex
				${ULIB}/icuLinux/include 
				${AXE_JNI}
				${AXE_JNI}/gxe
				./	           #sys/timeb @@@@
             )
add_definitions(
                 ${CFLGS}
                 ${CFLGS2}
				 ${UDEF}
             )

add_library(
             userxxe
             STATIC
            ${ULIB}/u3270.c
            ${ULIB}/u32702.c
            ${ULIB}/u32703.c
            ${ULIB}/uabend.c
            ${ULIB}/uallocl.c
            ${ULIB}/udbcschk.c
            ${ULIB}/udbcsck2.c
            ${ULIB}/ucalc.c
            ${ULIB}/ucalc2.c
            ${ULIB}/ucvebc.c
            ${ULIB}/ucvebc2.c
            ${ULIB}/ucvebc3.c
            ${ULIB}/ucvebc32.c
            ${ULIB}/ucvebc4.c
            ${ULIB}/ucvebct.c
            ${ULIB}/ucvext.c
            ${ULIB}/ucvext12.c
            ${ULIB}/ucvext2.c
            ${ULIB}/ucvucs.c
            ${ULIB}/ucvucst.c
            ${ULIB}/udos2.c
            ${ULIB}/udosl.c
            ${ULIB}/uedit.c
            ${ULIB}/uedit2.c
            ${ULIB}/uehl.c
            ${ULIB}/uerr.c
            ${ULIB}/ufemsg.c
            ${ULIB}/ufile.c
            ${ULIB}/ufile12.c
            ${ULIB}/ufile1f.c
            ${ULIB}/ufile1l.c
            ${ULIB}/ufile1l2.c
            ${ULIB}/ufile2.c
            ${ULIB}/ufile2f.c
            ${ULIB}/ufile2l.c
            ${ULIB}/ufile2l2.c
            ${ULIB}/ufile3.c
            ${ULIB}/ufile3f.c
            ${ULIB}/ufile4.c
            ${ULIB}/ufile5.c
            ${ULIB}/uftp.c
            ${ULIB}/uftp2.c
            ${ULIB}/uftp3.c
            ${ULIB}/ugcclib.c
            ${ULIB}/ukbdl.c
            ${ULIB}/umiscf.c
            ${ULIB}/uparse.c
            ${ULIB}/uproc.c
            ${ULIB}/uproc2.c
            ${ULIB}/uproc22.c
            ${ULIB}/uproc3.c
            ${ULIB}/uproc4.c
            ${ULIB}/uque.c
            ${ULIB}/ustring.c
            ${ULIB}/ustrcpy.c
            ${ULIB}/utf.c
            ${ULIB}/utf2.c
            ${ULIB}/utf22.c     #@@@@
            ${ULIB}/utf3.c      #@@@@
            ${ULIB}/utf4.c      #@@@@
            ${ULIB}/utrace.c
            ${ULIB}/uunxsub.c
            ${ULIB}/uviom.c    #@@@@
            ${ULIB}/uregex.c
            ${ULIB}/uviolx.c
            ${ULIB}/ulibarm.c
            ${ULIB}/ugcclib.c
             )
#file(COPY ${BUILD_OUT}/libuserxxe.a DESTINATION ${OUTPATH_LIB})
##find_file(
##    LIB_USERXXE #variable        #will be set to  ..../csub/ulib/
##    userxxe  #name to be search
##    PATH ${CMAKE_BINARY_DIR}     #.cxx/cmake/debug/armeabi-v7a/
##        )
#set(COPYSRC ${CMAKE_BINARY_DIR}/csub/ulib/libuserxxe.a)
#add_custom_command (
#        TARGET userxxe  POST_BUILD
#        COMMAND
#        ${CMAKE_COMMAND} -E copy
#        ${COPYSRC}
#        ${COPYPATH_LIB}
#        )
#debug and release exchanging deletes other side debug/release so save to DevConUp 
add_custom_command (TARGET userxxe POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy ${LIBRARY_OUTPUT_PATH}/libuserxxe.a ${DEVCONUP_LIBS})
