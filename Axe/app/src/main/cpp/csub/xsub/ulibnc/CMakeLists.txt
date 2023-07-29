#==exec-compile==arm-linux-androideabi-gcc
#-c
#-o
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
#-g
#-funsigned-char
#-DLNX
#-DUNX
#-DNOCURSES
#-DARM
#-DARMAPI9
#-DNOMT
#-DNOMT_TRACE
#-DANDROID
#-DLNXLC2
#-DLNXLC2TL6
#-DUTF8SUPP
#-DUTF8SUPPH
#-DUTF8UCS2
#-DUTF8EBCD
#-DNOTRACE
#-D_FILE_OFFSET_BITS=64
#-I/g/src/ulib
#-I/g/src/ulib/gnuregex
#-I/src
#-I/home3/androidndk/Projects/Aiconv/jni/libiconv_install/include
#-I/g/src/ulib/icuLinux/include
#/g/src/ulib/uunxsub.c
#####
##-DANDROID
##-DSK_RELEASE
##-DNDEBUG
##-UDEBUG
#####################################################################
# Sets the minimum version of CMake required to build the native
# library. You should either keep the default value or only pass a
# value of 3.4.0 or lower.

cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds it for you.
# Gradle automatically packages shared libraries with your APK.

#set(AXEJNI w:/AndroidStudioProjects/Axe/app/src/main/cpp/jni)	//2023/06/14 del
#set(RH9JGXE g:/src/gxe-rh9j)
#set(LIBICONV c:/users/sakstyle/AppData/Local/Android/sdk/ndk-bundle/sources/android/support/include) 
#set(LIBICONV ${ANDROID_NDK}/sources/android/support/include) 
#set(LIBICONV W:/AndroidStudioProjects/libiconv114LinuxMade/libiconv-1.14/include)
#set(ICONV_TOP W:/AndroidStudioProjects/libiconv-1.14.cfgathome3)
#set(ICONV_INC ${ICONV_TOP}/include)
set(ULIB g:/src/ulib)
#set(OPTDEBUG "-DTRACE -g3") 
set(OPTDEBUG ${OPT_TRACE}) 
#set(UDEF "-DLNX -DUNX -DXSUB -DNOCURSES -DARM -DARMAPI9 -DNOMT -DNOMT_TRACE -DANDROID -DLNXLC2 -DLNXLC2TL6 -DUTF8SUPP -DUTF8SUPPH -DUTF8UCS2 -DUTF8EBCD -D_FILE_OFFSET_BITS=64 ${OPTDEBUG}"
set(UDEF "-DLNX -DUNX -DXSUB -DNOCURSES -DARM -DARMAPI9 -DNOMT -DNOMT_TRACE -DANDROID -DLNXLC2 -DLNXLC2TL6 -DUTF8SUPP -DUTF8SUPPH -DUTF8UCS2 -DUTF8EBCD -D_FILE_OFFSET_BITS=64 -D__USE_FILE_OFFSET64 ${OPTDEBUG}"
		) 
#set(CFLGS  "-march=armv7-a -fpic -msoft-float -fno-exceptions -fstack-protector -Wall -Wno-pointer-sign -Wuninitialized -O0 -funsigned-char")
 set(CFLGS  "               -fpic              -fno-exceptions -fstack-protector -Wall -Wno-pointer-sign -Wuninitialized -O0 -funsigned-char")

#set(CFLGS2 "-finput-charset=cp932")
#set(CFLGS2 "-Wno-invalid-source-encoding -Wno-misleading-indentation")
#set(CFLGS2 "-Wno-invalid-source-encoding -Wno-misleading-indentation -ffunction-sections -fdata-sections")  #2023/0622 to remove dead section
 set(CFLGS2 "-Wno-invalid-source-encoding -Wno-misleading-indentation                                    ")  #2023/0622 to remove dead section
#-I/rh9j/home/sak/Projects/gxe/src 
include_directories(
				${INCLUDE_ULIB_UPDATE}
				${INCLUDE_ICONVH}
             	${ULIB}
             	${ULIB}/gnuregex
				${ULIB}/icuLinux/include 
				../../ulib         #sys/timeb.h
             )
add_definitions(
                 ${CFLGS}
                 ${CFLGS2}
				 ${UDEF}
             )

add_library(
             userxxe_nc
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
            ${ULIB}/ukbdlnxc.c
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
            ${ULIB}/utf22.c
            ${ULIB}/utf3.c
            ${ULIB}/utf4.c
            ${ULIB}/utrace.c
            ${ULIB}/uunxsub.c
            ${ULIB}/uregex.c
            ${ULIB}/ulibarm.c
            ${ULIB}/uprocExit.c        #2023/06/22
             )
#debug and release exchanging deletes other side debug/release so save to DevConUp 
add_custom_command (TARGET userxxe_nc POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy ${LIBRARY_OUTPUT_PATH}/libuserxxe_nc.a ${DEVCONUP_LIBS})
