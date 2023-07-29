##*CID:##+v700R~:                             update#=   42;##~v700I~
#arm-linux-androideabi-gcc
#-c
#-o
#xeundo.o
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
#-DNOTRACE
#-pthread
#-DLNX
#-DUNX
#-DARM
#-DARMAPI9
#-DNOMT
#-DLNXLC2
#-DXXE
#-DGTK
#-D_FILE_OFFSET_BITS=64
#-DUTF8SUPPH
#-DUTF8UCS2
#-DUTF8EBCD
#-I/g/src/xe/../ulib
#-I/g/src/xe/../ulib/gnuregex
#-I/g/src/xe
#-I/rh9j/home/sak/Projects/gxe/src
#/g/src/xe/xeundo.c
#####################################################################
# Sets the minimum version of CMake required to build the native
# library. You should either keep the default value or only pass a
# value of 3.4.0 or lower.

#cmake_minimum_required(VERSION 3.4.1)##~v700R~

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds it for you.
# Gradle automatically packages shared libraries with your APK.
##+v700I~

#set(AXEJNI x:/AndroidStudioProjects/Axe/app/src/main/jni)##~v700R~
#set(AXEJNI w:/AndroidStudioProjects/Axe/app/src/main/cpp/jni)##~v700R~
#set(RH9JGXE g:/src/gxe-rh9j)
#set(LIBICONV c:/users/sakstyle/AppData/Local/Android/sdk/ndk-bundle/sources/android/support/include) 
#set(LIBICONV ${ANDROID_NDK}/sources/android/support/include) 
#set(LIBICONV x:/AndroidStudioProjects/libiconv114LinuxMade/libiconv-1.14/include)##~v700R~
set(ULIB g:/src/ulib)
set(XE g:/src/xe)
#set(GXE g:/src/gxe-rh9j)
#set(GXE x:/AndroidStudioProjects/Axe/app/src/main/jni/gxe)##~v700R~
set(GXE w:/AndroidStudioProjects/Axe/app/src/main/cpp/jni/gxe)##~v700R~
set(AXE_JNI w:/AndroidStudioProjects/Axe/app/src/main/cpp/jni)##+v700I~
#set(OPTDEBUG "-DTRACE -g3") 
set(OPTDEBUG ${OPT_TRACE})##~v700R~
set(UDEF "-DANDROID -DSK_RELEASE -DNDEBUG -UDEBUG -DLNX -DUNX -DARM -DARMAPI9 -DNOMT -DLNXLC2 -DXXE -DGTK -D_FILE_OFFSET_BITS=64 -DUTF8SUPPH -DUTF8UCS2 -DUTF8EBCD ${OPTDEBUG}" )##~v700R~
#set(CFLGS  "-march=armv7-a -fpic -msoft-float -fno-exceptions -fstack-protector -Wall -Wno-pointer-sign -Wuninitialized -O0 -funsigned-char")##~v700R~
set(CFLGS   "               -fpic              -fno-exceptions -fstack-protector -Wall -Wno-pointer-sign -Wuninitialized -O0 -funsigned-char")##~v700R~

#set(CFLGS2 "-finput-charset=cp932")
set(CFLGS2 "-Wno-invalid-source-encoding -Wno-misleading-indentation")##~v700I~
#-I/rh9j/home/sak/Projects/gxe/src 
include_directories(
				${INCLUDE_ULIB_UPDATE}##~v700I~
				${INCLUDE_ICONVH}##~v700I~
             	${ULIB}
             	${XE}
				${AXE_JNI}
             	${GXE}
             	../ulib
             )
add_definitions(
                 ${CFLGS}
                 ${CFLGS2}
				 ${UDEF}
             )

add_library(
             xxe
             STATIC
                    ${XE}/xe.c
                    ${XE}/xecalc.c
                    ${XE}/xecalc2.c
                    ${XE}/xecalc21.c
                    ${XE}/xecalc22.c
                    ${XE}/xecalc3.c
                    ${XE}/xecap.c
                    ${XE}/xecap2.c
                    ${XE}/xechar.c
                    ${XE}/xechar12.c
                    ${XE}/xechar13.c
                    ${XE}/xechar2.c
                    ${XE}/xecsr.c
                    ${XE}/xedcmd.c
                    ${XE}/xedcmd2.c
                    ${XE}/xedcmd3.c
                    ${XE}/xedcmd4.c
                    ${XE}/xedir.c
                    ${XE}/xedir2.c
                    ${XE}/xedir3.c
                    ${XE}/xedir4.c
                    ${XE}/xedlcmd.c
                    ${XE}/xedlcmd2.c
                    ${XE}/xedlcmd3.c
                    ${XE}/xedlcmd4.c
                    ${XE}/xedlcmd5.c
                    ${XE}/xedlcmd6.c
                    ${XE}/xeebc.c
                    ${XE}/xeerr.c
                    ${XE}/xefcmd.c
                    ${XE}/xefcmd2.c
                    ${XE}/xefcmd22.c
                    ${XE}/xefcmd23.c
                    ${XE}/xefcmd24.c
                    ${XE}/xefcmd25.c
                    ${XE}/xefcmd26.c
                    ${XE}/xefcmd27.c
                    ${XE}/xefcmd3.c
                    ${XE}/xefcmd4.c
                    ${XE}/xefcmd42.c
                    ${XE}/xefcmd43.c
                    ${XE}/xefcmd5.c
                    ${XE}/xefcmd6.c
                    ${XE}/xefcmd7.c
                    ${XE}/xefile.c
                    ${XE}/xefile12.c
                    ${XE}/xefile13.c
                    ${XE}/xefile14.c
                    ${XE}/xefile15.c
                    ${XE}/xefile2.c
                    ${XE}/xefile22.c
                    ${XE}/xefile23.c
                    ${XE}/xefile3.c
                    ${XE}/xefile4.c
                    ${XE}/xefile42.c
                    ${XE}/xefile43.c
                    ${XE}/xefile5.c
                    ${XE}/xefile6.c
                    ${XE}/xefile7.c
                    ${XE}/xefsub.c
                    ${XE}/xeftp.c
                    ${XE}/xefunc.c
                    ${XE}/xefunc2.c
                    ${XE}/xefunct.c
                    ${XE}/xefunct2.c
                    ${XE}/xegbl.c
                    ${XE}/xeini.c
                    ${XE}/xeini2.c
                    ${XE}/xeini3.c
                    ${XE}/xekbd.c
                    ${XE}/xelcmd.c
                    ${XE}/xelcmd2.c
                    ${XE}/xelcmd3.c
                    ${XE}/xelcmd4.c
                    ${XE}/xelcmd5.c
                    ${XE}/xeopt.c
                    ${XE}/xeopt2.c
                    ${XE}/xepan.c
                    ${XE}/xepan2.c
                    ${XE}/xepan22.c
                    ${XE}/xepan23.c
                    ${XE}/xepan24.c
                    ${XE}/xepan3.c
                    ${XE}/xescr.c
                    ${XE}/xescr2.c
                    ${XE}/xesub.c
                    ${XE}/xesub2.c
                    ${XE}/xesyn.c
                    ${XE}/xesyn2.c
                    ${XE}/xetso.c
                    ${XE}/xetso2.c
                    ${XE}/xeundo.c
                    ${XE}/xeutf.c
                    ${XE}/xeutf2.c
             )

target_link_libraries(xxe userxxe)##~v700I~
#set(COPYSRC ${CMAKE_BINARY_DIR}/csub/xxe/libxxe.a)##~v700R~
#add_custom_command (##~v700R~
#        TARGET xxe  POST_BUILD##~v700R~
#        COMMAND##~v700R~
#        ${CMAKE_COMMAND} -E copy##~v700R~
#        ${COPYSRC}##~v700R~
#        ${COPYPATH_LIB}##~v700R~
#        )##~v700R~
#debug and release exchanging deletes other side debug/release so save to DevConUp##~v700I~
add_custom_command (TARGET xxe POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy ${LIBRARY_OUTPUT_PATH}/libxxe.a ${DEVCONUP_LIBS})##~v700I~
