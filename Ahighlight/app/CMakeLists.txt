# Sets the minimum version of CMake required to build the native
# library. You should either keep the default value or only pass a
# value of 3.4.0 or lower.

cmake_minimum_required(VERSION 3.4.1)
############################
set(EXECUTABLE_OUTPUT_PATH ${CMAKE_HOME_DIRECTORY}/bin)
add_subdirectory(nonpie/core)
add_subdirectory(nonpie/cli)
add_subdirectory(pie/core)
add_subdirectory(pie/cli)
###############
