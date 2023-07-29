#cmake_minimum_required(VERSION 3.4.1)
#cmake_minimum_required(VERSION 3.10.2)
#cmake_minimum_required(VERSION 3.22.1)      #vc57
#project(Alibiconv116)                       #vc57

##############################
set(TOPDIR w:/AndroidStudioProjects/libiconv-1.16.madeOnUbuntu)
set(OUT_SUBDIR ${CMAKE_BUILD_TYPE}/${ANDROID_ABI})
#set(EXECUTABLE_OUTPUT_PATH ${CMAKE_HOME_DIRECTORY}/bin/${OUT_SUBDIR})
#set(RUNTIME_OUTPUT_DIRECTORY ${CMAKE_HOME_DIRECTORY}/bin/${OUT_SUBDIR})
#set(LIBRARY_OUTPUT_PATH    ${CMAKE_HOME_DIRECTORY}/libs/${OUT_SUBDIR})
#set(LIBRARY_OUTPUT_DIRECTORY ${CMAKE_HOME_DIRECTORY}/libs/${OUT_SUBDIR})
#CMAKE_HOME_DIRECTORY=app/src/main/cpp (this folder)
#set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_HOME_DIRECTORY}/libs/${OUT_SUBDIR})
set(CMAKE_ARCHIVE_OUTPUT_DIRECTORY ${CMAKE_HOME_DIRECTORY}/libs/${OUT_SUBDIR})
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_HOME_DIRECTORY}/bin/${OUT_SUBDIR})
set(WORK_LIBS ${CMAKE_HOME_DIRECTORY}/libs/${OUT_SUBDIR})
#set(WORK_RUNTIME ${CMAKE_HOME_DIRECTORY}/bin/${OUT_SUBDIR})

#set(INCLUDE_CONFIGH ${CMAKE_HOME_DIRECTORY})
set(INCLUDE_CONFIGH ${CMAKE_HOME_DIRECTORY}/Aiconv)        #vc57
set(DEF_ANDROID -D__ANDROID__)
#set(DEVCONUP w:/AndroidStudioProjects/Alibiconv116/DevConUp) #vc57
#set(DEVCONUP_BIN ${DEVCONUP}/bin/${OUT_SUBDIR})    #vc57
#set(DEVCONUP_LIBS ${DEVCONUP}/libs/${OUT_SUBDIR})  #vc57
##############################
if (${CMAKE_BUILD_TYPE} STREQUAL "Debug")
	set(OPT_G -g3)   #g3 may support macro expansion
	set(OPT_STRIP "") 
else()
	set(OPT_G -g0)
	set(OPT_STRIP -Wl,-s) 
endif()
##############################
add_subdirectory(biniconv)
add_subdirectory(soiconv)   #vc58
#add_subdirectory(libiconv) #vc57
#add_subdirectory(libicrt)  #vc57
