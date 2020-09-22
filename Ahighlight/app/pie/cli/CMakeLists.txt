# Sets the minimum version of CMake required to build the native
# library. You should either keep the default value or only pass a
# value of 3.4.0 or lower.

#cmake_minimum_required(VERSION 3.4.1)

###############
set(PTOP x:/AndroidStudioProjects/Ahighlight.copyofhome3/jni/highlight-2.16)
set(SRCTOP ${PTOP}/src)
set(P ${SRCTOP}/cli)
set(DIR_CORE ${SRCTOP}/core)
set(SRC_CLI  
 				${P}/arg_parser.cc
				${P}/cmdlineoptions.cpp
				${P}/help.cpp                      
				${P}/main.cpp
              )
#set (P src/main/cpp/cli)
if (${CMAKE_BUILD_TYPE} STREQUAL "Debug")
    set(OPT_STRIP "")
else()
    set(OPT_STRIP -Wl,-s)
endif()

set(CF_PIE -fPIE)
set(LF_PIE "-fPIE -pie")
set(CFLGS ${CF_PIE}
   )
set(LDFLGS "${LF_PIE}"
   )

#set(ANDROID_NATIVE_API_LEVEL 16)
#set(CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} -pie -fPIE")
###############
add_definitions(
				 ${CFLGS}
             )
#				 ${LDFLGS}
include_directories(
				${DIR_CORE}
             )
add_executable(highlight_pie
 				${SRC_CLI}
              )
set_target_properties(highlight_pie
						PROPERTIES LINK_FLAGS ${LDFLGS}	
                     )
target_link_libraries( # Specifies the target library.
                       highlight_pie
                       core_pie
                       ${OPT_STRIP}
                      )
set(OUT_SUBDIR ${CMAKE_BUILD_TYPE}/${ANDROID_ABI})
set(DEVCONUP x:/AndroidStudioProjects/Ahighlight/DevConUp)
set(DEVCONUP_BIN ${DEVCONUP}/bin/${OUT_SUBDIR})
set(EXECUTABLE_OUTPUT_PATH ${CMAKE_HOME_DIRECTORY}/bin/${OUT_SUBDIR})
add_custom_command (TARGET highlight_pie POST_BUILD
	 COMMAND ${CMAKE_COMMAND} -E copy ${EXECUTABLE_OUTPUT_PATH}/highlight_pie ${DEVCONUP_BIN}/highlight)
