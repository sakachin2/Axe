
#cmake_minimum_required(VERSION 3.4.1)
#cmake_minimum_required(VERSION 3.10.2)
cmake_minimum_required(VERSION 3.22.1)

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
add_executable(iconv.bin
	  			${TOPDIR}/src/iconv.c
              )
#set(CMAKE_EXE_LINKER_FLAGS -Wl,-v,--verbose)	# for link err test only 
target_link_libraries( # Specifies the target library.
                       iconv.bin
                       iconv
                       icrt
                       c         #libc
                       ${OPT_STRIP}
                       )
#set_target_properties(iconv.bin
#    PROPERTIES
#    RUNTIME_OUTPUT_DIRECTORY ${CMAKE_HOME_DIRECTORY}/bin/${OUT_SUBDIR}
#    LIBRARY_OUTPUT_DIRECTORY ${CMAKE_HOME_DIRECTORY}/libs/${OUT_SUBDIR}
#    )

#add_custom_command (TARGET iconv.bin POST_BUILD
#	 COMMAND ${CMAKE_COMMAND} -E copy ${EXECUTABLE_OUTPUT_PATH}/iconv.bin ${DEVCONUP_BIN}/iconv
#	 COMMAND ${CMAKE_COMMAND} -E copy ${LIBRARY_OUTPUT_PATH}/libiconv.a  ${DEVCONUP_LIBS}
#	)
add_custom_command (TARGET iconv.bin POST_BUILD
# 	 COMMAND ${CMAKE_COMMAND} -E copy ${EXECUTABLE_OUTPUT_DIRECTORY}/iconv.bin ${DEVCONUP_BIN}/iconv
# 	 COMMAND ${CMAKE_COMMAND} -E copy ${RUNTIME_OUTPUT_DIRECTORY}/iconv.bin ${DEVCONUP_BIN}/iconv
   	 COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_RUNTIME_OUTPUT_DIRECTORY}/iconv.bin ${DEVCONUP_BIN}/iconv
	)
