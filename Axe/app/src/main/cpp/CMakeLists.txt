#cmake_minimum_required(VERSION 3.10.2)
cmake_minimum_required(VERSION 3.22.1)
#cmake_minimum_required(VERSION 3.18.1)
#cmake_minimum_required(VERSION 3.17.3)##~v700M~
project(AxeA9)
##############################
set(OUT_SUBDIR ${CMAKE_BUILD_TYPE}/${ANDROID_ABI})
set(INCLUDE_ULIB_UPDATE ${CMAKE_HOME_DIRECTORY}/csub/ulib)
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_HOME_DIRECTORY}/bin/${OUT_SUBDIR})
set(EXECUTABLE_OUTPUT_PATH ${CMAKE_HOME_DIRECTORY}/bin/${OUT_SUBDIR})
set(LIBRARY_OUTPUT_PATH    ${CMAKE_HOME_DIRECTORY}/libs/${OUT_SUBDIR})
set(INCLUDE_ICONVH W:/AndroidStudioProjects/libiconv-1.16.madeOnUbuntu/include)
set(DEVCONUP w:/AndroidStudioProjects/Axe/DevConUp)
set(DEVCONUP_BIN ${DEVCONUP}/bin/${OUT_SUBDIR})
set(DEVCONUP_LIBS ${DEVCONUP}/libs/${OUT_SUBDIR})
#set(RESOURCE_LIBS ${CMAKE_HOME_DIRECTORY}/../resources/lib/${ANDROID_ABI})
#set(RESOURCE_BIN  ${CMAKE_HOME_DIRECTORY}/../resources/bin/${ANDROID_ABI})
#set(RESOURCE_LIBS ${CMAKE_HOME_DIRECTORY}/../resources/lib/${OUT_SUBDIR})
set(RESOURCE_LIBS ${CMAKE_HOME_DIRECTORY}/../resources/libs/${OUT_SUBDIR})   #2023/06/15 lib-->libs
set(RESOURCE_BIN  ${CMAKE_HOME_DIRECTORY}/../resources/bin/${OUT_SUBDIR})
#set(LIBDIR_ICONV ${DEVCONUP_LIBS})           #vc57
#set(LIBDIR_ICONV  ${LIBRARY_OUTPUT_PATH})     #vc57
 set(LIBDIR_ICONV  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY})     #vc57

set(ULIB g:/src/ulib)
set(XE   g:/src/xe)
if (${CMAKE_BUILD_TYPE} STREQUAL "Debug")
	set(OPT_TRACE -DTRACE -g3)   #g3 may support macro expansion
	set(OPT_STRIP "") 
else()
	set(OPT_TRACE -DNOTRACE -g0)
#   set(OPT_STRIP -Wl,-s)   //-g0 means no debug info, so -Wl,-s(strip) is unused warning
	set(OPT_STRIP "")       
endif()
set(CHK_BUFFER_OVERFLOW -D_FORTIFY_SOURCE=1)   #chk only compile time,no abort at runtime
string(REGEX REPLACE "-D_FORTIFY_SOURCE=[0-9]" "" CMAKE_C_FLAGS "${CMAKE_C_FLAGS}") #drop to avoid dup macro warning
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} ${CHK_BUFFER_OVERFLOW}") #then add needed
##############################
#set(LIBSRC ${LIBRARY_OUTPUT_PATH}/)
#add_custom_target(libsall ALL
#         ${CMAKE_COMMAND} -E copy "${LIBSRC}/libuserxxe.a"    "${DEVCONUP_LIBS}"
#       COMMAND
#         ${CMAKE_COMMAND} -E copy "${LIBSRC}/libuserxxe_nc.a" "${DEVCONUP_LIBS}"
#       COMMAND
#         ${CMAKE_COMMAND} -E copy "${LIBSRC}/libxxe.a"        "${DEVCONUP_LIBS}"
#       COMMAND
#         ${CMAKE_COMMAND} -E copy "${LIBSRC}/libaxejni.a"     "${DEVCONUP_LIBS}"
# DEPENDS
#    csub/ulib/userxxe
#    csub/xsub/ulibnc/userxxe_nc
#    csub/xxe/xxe
#    jni/axejni
# )
#add_dependencies(libsall axejni)##~v700R~
##############################

add_subdirectory(Ahighlight )              #vc57
add_subdirectory(Aiconv)                   #vc57
add_subdirectory(jni)
add_subdirectory(csub)

#add_subdirectory(csub/ulib)
#add_subdirectory(csub/xxe)
#add_subdirectory(csub/xsub)
#add_dependencies(axejni  userxxe xxe)##~v700R~
add_dependencies(axejni  csuball)##~v700R~

##add_dependencies(axejni  ${LINK_LIBS})
##add_library(axejni SHARED jni/jnij2c.c)
##add_library(userxxe STATIC IMPORTED)
#set_target_properties(userxxe PROPERTIES IMPORTED_LOCATION ${BUILD_OUT}/${ANDROID_ABI}/userxxe.a)
#set_target_properties(xxe PROPERTIES IMPORTED_LOCATION ${BUILD_OUT}/${ANDROID_ABI}/xxe.a)
##target_link_libraries(axejni userxxe xxe)
#set(FIND_ULIB ${BUILD_OUT}/userxxe)
#set(FIND_XXE  ${BUILD_OUT}/xxe)
##find_library( FIND_ULIB userxxe)
##find_library( FIND_XXE  xxe)
#set(LINK_LIBS ${FIND_ULIB} ${FIND_XXE})
##target_link_libraries(axejni ${LINK_LIBS})


#include_directories(${ULIB})
#include_directories(${XE})
#include_directories(jni)
#include_directories(jni/gxe)

