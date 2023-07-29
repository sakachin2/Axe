
#cmake_minimum_required(VERSION 3.4.1)
#cmake_minimum_required(VERSION 3.10.2)
#cmake_minimum_required(VERSION 3.22.1) #vc57

##############################
#iconv.o : $(srcdir)/iconv.c
#	$(CC) -c $(INCLUDES) -I../lib $(CFLAGS) $(CPPFLAGS) -DINSTALLDIR=\"$(bindir)\" -DLOCALEDIR=\"$(localedir)\" $(srcdir)/iconv.c
#
#INCLUDES = -I. -I$(srcdir) -I.. -I../include -I$(srcdir)/../include -I../srclib -I$(srcdir)/../srclib
#CFLAGS = -g -O2
#CPPFLAGS = 
#srcdir = .
#exec_prefix = ${prefix}
#local_prefix = /usr/local
#bindir = ${exec_prefix}/bin
add_subdirectory(libiconv) #vc57
add_subdirectory(libicrt)  #vc57
##############################
set(srcdir ${TOPDIR}/src)
set(INCLUDES  ${srcdir} ${srcdir}/.. ${srcdir}/../include ${srcdir}/../srclib)
set(INCLUDES2 ${srcdir}/../lib)
set(CPPFLAGS "")
set(DEF "")
#/src
include_directories(
				${INCLUDE_CONFIGH}
 				${INCLUDES}
				${INCLUDES2}
             )
add_definitions(
                 ${DEF_ANDROID}
                 ${CFLGS} ${CPPFLAGS}
                 ${DEFS}
                 ${OPT_G}
             )
add_executable(iconvbin
	  			${TOPDIR}/src/iconv.c
              )
#add_library(iconv_so  SHARED
#	  			${TOPDIR}/src/iconv.c
#              )	#vc57     #vc58
#set(CMAKE_EXE_LINKER_FLAGS -Wl,-v,--verbose)	# for link err test only 
target_link_libraries( # Specifies the target library.
                       iconvbin
                       iconv
                       icrt
                       ${OPT_STRIP}
                       )
#                      c         #libc vc57
set(CFLGS2 -fPIC -DANDROID_PIE=ON)                                         #vc57
#target_compile_options(iconv_so PRIVATE ${CFLGS2})                          #vc57   #vc58
#set(LDFLAGS3  -Wl,--gc-sections)                                           #vc57
#target_link_libraries( 
#                       iconv_so
#                       iconv
#                       icrt
#                       ${OPT_STRIP}
#                       ${LDFLAGS3}
#                       )													#vc57 #vc58
#set_target_properties(iconvbin
#    PROPERTIES
#    RUNTIME_OUTPUT_DIRECTORY ${CMAKE_HOME_DIRECTORY}/bin/${OUT_SUBDIR}
#    LIBRARY_OUTPUT_DIRECTORY ${CMAKE_HOME_DIRECTORY}/libs/${OUT_SUBDIR}
#    )
#set_target_properties(iconv_so
#     PROPERTIES
#     LIBRARY_OUTPUT_DIRECTORY ${CMAKE_HOME_DIRECTORY}/libs/${OUT_SUBDIR}
#     )         #vc58
set(LF_PIE "-fPIE -pie")
set(LDFLGS "${LF_PIE}")
set_target_properties(iconvbin
						PROPERTIES LINK_FLAGS ${LDFLGS}	
                     )

#add_custom_command (TARGET iconvbin POST_BUILD
#	 COMMAND ${CMAKE_COMMAND} -E copy ${EXECUTABLE_OUTPUT_PATH}/iconvbin ${DEVCONUP_BIN}/iconv
#	 COMMAND ${CMAKE_COMMAND} -E copy ${LIBRARY_OUTPUT_PATH}/libiconv.a  ${DEVCONUP_LIBS}
#	)
add_custom_command (TARGET iconvbin POST_BUILD
# 	 COMMAND ${CMAKE_COMMAND} -E copy ${EXECUTABLE_OUTPUT_DIRECTORY}/iconvbin ${DEVCONUP_BIN}/iconv
# 	 COMMAND ${CMAKE_COMMAND} -E copy ${RUNTIME_OUTPUT_DIRECTORY}/iconvbin ${DEVCONUP_BIN}/iconv
#  	 COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_RUNTIME_OUTPUT_DIRECTORY}/iconvbin ${DEVCONUP_BIN}/iconv
	 COMMAND ${CMAKE_COMMAND} -E copy ${EXECUTABLE_OUTPUT_PATH}/iconvbin   ${RESOURCE_BIN}/iconv 
	 COMMAND ${CMAKE_COMMAND} -E copy ${EXECUTABLE_OUTPUT_PATH}/iconvbin   ${DEVCONUP_BIN}/iconv
#     COMMAND ${CMAKE_COMMAND} -E copy ${LIBRARY_OUTPUT_PATH}/libiconv_so.so   ${RESOURCE_LIBS}   #vc58
#     COMMAND ${CMAKE_COMMAND} -E copy ${LIBRARY_OUTPUT_PATH}/libiconv_so.so   ${DEVCONUP_LIBS}   #vc58
	)
