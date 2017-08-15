#####################################################################
cmake_minimum_required(VERSION 3.4.1)

#set(INSTALL_TARGET ${CMAKE_HOME_DIRECTORY}/install_target)
set(EXECUTABLE_OUTPUT_PATH ${CMAKE_HOME_DIRECTORY}/bin)
set(LIBRARY_OUTPUT_PATH    ${CMAKE_HOME_DIRECTORY}/libs)
add_subdirectory(xsub)
add_subdirectory(xxe)
add_subdirectory(ulib)
