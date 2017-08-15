# Sets the minimum version of CMake required to build the native
# library. You should either keep the default value or only pass a
# value of 3.4.0 or lower.

cmake_minimum_required(VERSION 3.4.1)


##############################
set(TOPDIR x:/AndroidStudioProjects/libiconv-1.14.cfgathome3)
set(SUPPORT ${ANDROID_NDK}/sources/android/support)
set(LINKDIR_LANGINFO ${ANDROID_NDK}/sources/cxx-stl/llvm-libc++/libs/${ANDROID_ABI})
set(LIB_LANGINFO android_support)
set(LIB_LANGINFO_A libandroid_support.a)
set(CFLGS  "-Wno-multichar -D_ANDROID -DLIBDIR='\"./\"' -DBUILDING_LIBICONV -DIN_LIBRARY -DLOCALEDIR='\"./\"' " )
set(CFLGS2  "-Wno-error=format-security")
set(EXECUTABLE_OUTPUT_PATH ${CMAKE_HOME_DIRECTORY}/bin)
set(LIBRARY_OUTPUT_PATH   ${CMAKE_HOME_DIRECTORY}/libs)
set(P ${TOPDIR}/srclib)
##############################
include_directories(
				${TOPDIR}/
				${TOPDIR}/include
				${TOPDIR}/lib
				${TOPDIR}/libcharset/include
				${P}
				${SUPPORT}/include
				${SUPPORT}/src/musl-locale
             )

add_definitions(
                 ${CFLGS}
                 ${CFLGS2}
             )

add_library(
             iconv
             STATIC
  			${TOPDIR}/lib/iconv.c
  			${TOPDIR}/libcharset/lib/localcharset.c
  			${TOPDIR}/lib/relocatable.c
             )
add_library(
             icrt
             STATIC
            ${P}/allocator.c 
            ${P}/areadlink.c 
            ${P}/careadlinkat.c 
            ${P}/malloca.c 
            ${P}/progname.c 
            ${P}/safe-read.c 
            ${P}/uniwidth/width.c 
            ${P}/xmalloc.c 
            ${P}/xstrdup.c 
            ${P}/xreadlink.c 
            ${P}/lstat.c
            ${P}/error.c
             )

add_executable(iconv.pie
	  			${TOPDIR}/src/iconv.c
              )
#find_library(   result_find_langinfo
#				 ${LIB_LANGINFO}
#  			 HINTS ${LINKDIR_LANGINFO}
#			 )
#find_library(   result_find_langinfo
#                NAMES "android_support" "libandroid_support" "libandroid_support.a"
#                PATHS "c:/users/sakstyle/appdata/local/android/sdk/ndk-bundle/sources/cxx-stl/llvm-libc++/libs/armeabi-v7a"
#                NO_DEFAULT_PATH
#            )
#link_directories(${LINKDIR_LANGINFO})
target_link_libraries( # Specifies the target library.
                       iconv.pie
                       iconv
                       icrt
#                    	android_support   	
# 						${result_find_langinfo}
             	${LINKDIR_LANGINFO}/${LIB_LANGINFO_A}
                       )
link_directories(${LINKDIR_LANGINFO})
