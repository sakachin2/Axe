#v001 2023/06/22 for libhighlight_so.so
# Sets the minimum version of CMake required to build the native
# library. You should either keep the default value or only pass a
# value of 3.4.0 or lower.

#cmake_minimum_required(VERSION 3.4.1)

###############
set(PTOP w:/AndroidStudioProjects/Ahighlight.copyofhome3/jni/highlight-2.16)
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
# "-funsigned-char"                                                           #v001
  "-funsigned-char -fPIC"                                                           #v001
   )
set(LDFLGS "${LF_PIE}"
   )

#set(ANDROID_NATIVE_API_LEVEL 16)
#set(CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} -pie -fPIE")
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_HOME_DIRECTORY}/bin/${OUT_SUBDIR})
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
add_library(highlight_so  SHARED  ${SRC_CLI})                            #v001 
set_target_properties(highlight_pie
						PROPERTIES LINK_FLAGS ${LDFLGS}	
                     )
set(CFLGS2 -fPIC -DANDROID_PIE=ON -ffunction-sections -fdata-sections)  #v001
target_compile_options(highlight_so PRIVATE ${CFLGS2})                          #v001
target_link_libraries( # Specifies the target library.
                       highlight_pie
                       core_pie
                       ${OPT_STRIP}
                      )                 
set(LDFLAGS3  -Wl,--gc-sections)                                         #v001
target_link_libraries(highlight_so core_pie ${LDFLAGS3})                 #v001
set(OUT_SUBDIR ${CMAKE_BUILD_TYPE}/${ANDROID_ABI})
set(DEVCONUP w:/AndroidStudioProjects/Ahighlight/DevConUp)
set(DEVCONUP_BIN ${DEVCONUP}/bin/${OUT_SUBDIR})
set(EXECUTABLE_OUTPUT_PATH ${CMAKE_HOME_DIRECTORY}/bin/${OUT_SUBDIR})
add_custom_command (TARGET highlight_pie POST_BUILD
	 COMMAND ${CMAKE_COMMAND} -E copy ${EXECUTABLE_OUTPUT_PATH}/highlight_pie ${DEVCONUP_BIN}/highlight)
add_custom_command (TARGET highlight_so POST_BUILD                                                     #v001
	 COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libhighlight_so.so ${DEVCONUP_BIN})    #v001
