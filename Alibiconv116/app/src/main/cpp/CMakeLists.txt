#cmake_minimum_required(VERSION 3.4.1)
cmake_minimum_required(VERSION 3.10.2)

##############################
set(TOPDIR X:/AndroidStudioProjects/libiconv-1.16.madeOnUbuntu)
set(OUT_SUBDIR ${CMAKE_BUILD_TYPE}/${ANDROID_ABI})
set(EXECUTABLE_OUTPUT_PATH ${CMAKE_HOME_DIRECTORY}/bin/${OUT_SUBDIR})
set(LIBRARY_OUTPUT_PATH    ${CMAKE_HOME_DIRECTORY}/libs/${OUT_SUBDIR})
set(INCLUDE_CONFIGH ${CMAKE_HOME_DIRECTORY})
set(DEF_ANDROID -D__ANDROID__)
set(DEVCONUP x:/AndroidStudioProjects/Alibiconv116/DevConUp)
set(DEVCONUP_BIN ${DEVCONUP}/bin/${OUT_SUBDIR})
set(DEVCONUP_LIBS ${DEVCONUP}/libs/${OUT_SUBDIR})
##############################
if (${CMAKE_BUILD_TYPE} STREQUAL "Debug")
	set(OPT_G -g3)   #g3 may support macro expansion
	set(OPT_STRIP "") 
else()
	set(OPT_G -g0)
	set(OPT_STRIP -Wl,-s) 
endif()
##############################
add_subdirectory(srclib)
add_subdirectory(lib)
add_subdirectory(src)
