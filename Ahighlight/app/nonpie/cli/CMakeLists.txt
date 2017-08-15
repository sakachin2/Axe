# Sets the minimum version of CMake required to build the native
# library. You should either keep the default value or only pass a
# value of 3.4.0 or lower.

cmake_minimum_required(VERSION 3.4.1)

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
###############
include_directories(
				${DIR_CORE}
             )
add_executable(highlight
 				${SRC_CLI}
              )
target_link_libraries( # Specifies the target library.
                       highlight
                       core
                      )
