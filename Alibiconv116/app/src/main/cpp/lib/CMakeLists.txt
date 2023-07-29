
#cmake_minimum_required(VERSION 3.4.1)
#cmake_minimum_required(VERSION 3.10.2)
cmake_minimum_required(VERSION 3.22.1)

##############################
#$(LIBTOOL_COMPILE) $(CC) $(INCLUDES) $(CPPFLAGS) $(CFLAGS) $(DEFS) -c $(srcdir)/../woe32dll/iconv-exports.c
#exec_prefix = ${prefix}
#libdir = ${exec_prefix}/lib
#CFLAGS = -g -O2 -fvisibility=hidden
#CPPFLAGS = 
#DEFS = -DLIBDIR=\"$(libdir)\" -DBUILDING_LIBICONV -DBUILDING_DLL \
#-DENABLE_RELOCATABLE=1 -DIN_LIBRARY -DINSTALLDIR=\"$(libdir)\" -DNO_XMALLOC \
#-Dset_relocation_prefix=libiconv_set_relocation_prefix \
#-Drelocate=libiconv_relocate -Drelocate2=libiconv_relocate2 -DHAVE_CONFIG_H
#SOURCES = $(srcdir)/iconv.c $(srcdir)/../libcharset/lib/localcharset.c $(srcdir)/relocatable.c
#INCLUDES = -I. -I$(srcdir) -I../include -I$(srcdir)/../include -I.. -I$(srcdir)/..
##############################
#/lib
set(srcdir ${TOPDIR}/lib)
set(INCLUDES ${srcdir}   ${srcdir}/../include   ${srcdir}/../)
set(CPPFLAGS "")
#Cmake set Debug: O0 -g, Release: -g -O2
set(CFLAGS -fvisibility=hidden)
set(DEFS
		                       -DBUILDING_LIBICONV -DBUILDING_DLL
		-DENABLE_RELOCATABLE=1 -DIN_LIBRARY                            -DNO_XMALLOC
		-Dset_relocation_prefix=libiconv_set_relocation_prefix
		-Drelocate=libiconv_relocate -Drelocate2=libiconv_relocate2 -DHAVE_CONFIG_H
        )

include_directories(
				${INCLUDE_CONFIGH}
				${INCLUDES}
             )
add_definitions(
                 ${DEF_ANDROID}
                 ${CPPFLAGS} ${CFLAGS}
                 ${DEFS}
                 ${OPT_G}
             )
add_library(
             iconv
             STATIC
  			${srcdir}/iconv.c
  			${srcdir}/../libcharset/lib/localcharset.c
  			${srcdir}/relocatable.c
             )
#set_target_properties(iconv
#    PROPERTIES
##   RUNTIME_OUTPUT_DIRECTORY ${CMAKE_HOME_DIRECTORY}/bin/${OUT_SUBDIR}
#    LIBRARY_OUTPUT_DIRECTORY ${CMAKE_HOME_DIRECTORY}/libs/${OUT_SUBDIR}
#    )
add_custom_command (TARGET iconv POST_BUILD
#	 COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libiconv.a  ${DEVCONUP_LIBS}
#  	 COMMAND ${CMAKE_COMMAND} -E copy ${ARCHIVE_OUTPUT_DIRECTORY}/libiconv.a  ${DEVCONUP_LIBS}
   	 COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_ARCHIVE_OUTPUT_DIRECTORY}/libiconv.a  ${DEVCONUP_LIBS}
	)
