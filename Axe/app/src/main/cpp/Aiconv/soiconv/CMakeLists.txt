
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
add_subdirectory(libiconvso) #vc57 #vc58
add_subdirectory(libicrtso)  #vc57 #vc58
##############################
set(srcdir ${TOPDIR}/src)
set(INCLUDES  ${srcdir} ${srcdir}/.. ${srcdir}/../include ${srcdir}/../srclib)
set(INCLUDES2 ${srcdir}/../lib)
set(CPPFLAGS "")
set(DEFSO -DARM_AXE)                                         #vc58
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
                 ${DEFSO}
                 ${OPT_G}
             )
#add_executable(iconvbin
#	  			${TOPDIR}/src/iconv.c
#              )           #vc58
add_library(iconv_so  SHARED
	  			${TOPDIR}/src/iconv.c
				${INCLUDE_CONFIGH}/exitPthread.c      #vc58
              )	#vc57
#set(CMAKE_EXE_LINKER_FLAGS -Wl,-v,--verbose)	# for link err test only 
#target_link_libraries( # Specifies the target library.
#                       iconvbin
#                       iconv
#                       icrt
#                       ${OPT_STRIP}
#                       )             #vc58
#                      c         #libc vc57
set(CFLGS2 -fPIC -DANDROID_PIE=ON)                                         #vc57
target_compile_options(iconv_so PRIVATE ${CFLGS2})                          #vc57
#set(LDFLAGS3  -Wl,--gc-sections)                                           #vc57
target_link_libraries( 
                       iconv_so
#                      iconv
                       iconvso
#                      icrt
                       icrtso
                       ${OPT_STRIP}
                       ${LDFLAGS3}
                       )													#vc57
#set_target_properties(iconvbin
#    PROPERTIES
#    RUNTIME_OUTPUT_DIRECTORY ${CMAKE_HOME_DIRECTORY}/bin/${OUT_SUBDIR}
#    LIBRARY_OUTPUT_DIRECTORY ${CMAKE_HOME_DIRECTORY}/libs/${OUT_SUBDIR}
#    )
set_target_properties(iconv_so
     PROPERTIES
     LIBRARY_OUTPUT_DIRECTORY ${CMAKE_HOME_DIRECTORY}/libs/${OUT_SUBDIR}
     )
set(LF_PIE "-fPIE -pie")
set(LDFLGS "${LF_PIE}")
#set_target_properties(iconvbin
#						PROPERTIES LINK_FLAGS ${LDFLGS}	
#                     )     #vc58

#add_custom_command (TARGET iconvbin POST_BUILD
#	 COMMAND ${CMAKE_COMMAND} -E copy ${EXECUTABLE_OUTPUT_PATH}/iconvbin ${DEVCONUP_BIN}/iconv
#	 COMMAND ${CMAKE_COMMAND} -E copy ${LIBRARY_OUTPUT_PATH}/libiconv.a  ${DEVCONUP_LIBS}
#	)
#add_custom_command (TARGET iconvbin POST_BUILD
add_custom_command (TARGET iconv_so POST_BUILD
# 	 COMMAND ${CMAKE_COMMAND} -E copy ${EXECUTABLE_OUTPUT_DIRECTORY}/iconvbin ${DEVCONUP_BIN}/iconv
# 	 COMMAND ${CMAKE_COMMAND} -E copy ${RUNTIME_OUTPUT_DIRECTORY}/iconvbin ${DEVCONUP_BIN}/iconv
#  	 COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_RUNTIME_OUTPUT_DIRECTORY}/iconvbin ${DEVCONUP_BIN}/iconv
#	 COMMAND ${CMAKE_COMMAND} -E copy ${EXECUTABLE_OUTPUT_PATH}/iconvbin   ${RESOURCE_BIN}/iconv	#vc58 
#	 COMMAND ${CMAKE_COMMAND} -E copy ${EXECUTABLE_OUTPUT_PATH}/iconvbin   ${DEVCONUP_BIN}/iconv    #vc58
     COMMAND ${CMAKE_COMMAND} -E copy ${LIBRARY_OUTPUT_PATH}/libiconv_so.so   ${RESOURCE_LIBS}
     COMMAND ${CMAKE_COMMAND} -E copy ${LIBRARY_OUTPUT_PATH}/libiconv_so.so   ${DEVCONUP_LIBS}
	)
