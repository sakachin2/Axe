
#cmake_minimum_required(VERSION 3.4.1)
#cmake_minimum_required(VERSION 3.10.2)
#cmake_minimum_required(VERSION 3.22.1)  #vc57


##############################
#COMPILE = $(CC) $(DEFS) $(DEFAULT_INCLUDES) $(INCLUDES) $(AM_CPPFLAGS) \
#	$(CPPFLAGS) $(AM_CFLAGS) $(CFLAGS)
#DEFAULT_INCLUDES = -I. -I$(top_builddir) -I$(top_builddir)/lib
#AM_CPPFLAGS = -DDEPENDS_ON_LIBICONV=1 -DDEPENDS_ON_LIBINTL=1
#CPPFLAGS = 
#CFLAGS = -g -O2
#DEFS = -DHAVE_CONFIG_H -DEXEEXT=\"\"
#SOURCES = $(libicrt_a_SOURCES) $(EXTRA_libicrt_a_SOURCES)
#libicrt_a_SOURCES = allocator.c areadlink.c binary-io.h binary-io.c \
#    careadlinkat.c dirname-lgpl.c basename-lgpl.c stripslash.c \
#    getprogname.h getprogname.c gettext.h malloca.c progname.h \
#    progname.c safe-read.c stat-time.c unistd.c $(am__append_1) \
#    xalloc.h xmalloc.c xstrdup.c xreadlink.c
#libicrt_a_LIBADD = $(gl_LIBOBJS)
#libicrt_a_DEPENDENCIES = $(gl_LIBOBJS)
#EXTRA_libicrt_a_SOURCES = canonicalize-lgpl.c error.c lstat.c malloc.c \
#    msvc-inval.c msvc-nothrow.c raise.c read.c readlink.c \
#    progreloc.c relocatable.c stdio-write.c sigprocmask.c \
#    stat-w32.c stat.c strerror.c strerror-override.c
##############################
set(DEFAULT_INCLUDES ${TOPDIR}/srclib ${TOPDIR} ${TOPDIR}/lib)
set(AM_CPPFLAGS -DDEPENDS_ON_LIBICONV=1 -DDEPENDS_ON_LIBINTL=1)
set(DEFS -DHAVE_CONFIG_H -DEXEEXT=\"\")

include_directories(
				${INCLUDE_CONFIGH}
				${DEFAULT_INCLUDES}
				${INCLUDES}
             )
add_definitions(
                 ${DEF_ANDROID}
                 ${DEFS}
                 ${AM_CPPFLAGS} ${CPPFLAGS} ${AM_CFLGS} ${CFLAGS}
                 ${OPT_G}
             )

set(SRCLIB ${TOPDIR}/srclib)
set(am__append_1 uniwidth/width.c)
add_library(
             icrt
             STATIC
#libicrt_a_SOURCES
  ${SRCLIB}/allocator.c
  ${SRCLIB}/areadlink.c
  ${SRCLIB}/binary-io.h
  ${SRCLIB}/binary-io.c
  ${SRCLIB}/careadlinkat.c
  ${SRCLIB}/dirname-lgpl.c
  ${SRCLIB}/basename-lgpl.c
  ${SRCLIB}/stripslash.c
  ${SRCLIB}/getprogname.h
  ${SRCLIB}/getprogname.c
  ${SRCLIB}/gettext.h
  ${SRCLIB}/malloca.c
  ${SRCLIB}/progname.h
  ${SRCLIB}/progname.c
  ${SRCLIB}/safe-read.c
  ${SRCLIB}/stat-time.c
  ${SRCLIB}/unistd.c
  ${SRCLIB}/${am__append_1}
  ${SRCLIB}/xalloc.h
  ${SRCLIB}/xmalloc.c
  ${SRCLIB}/xstrdup.c
  ${SRCLIB}/xreadlink.c
#EXTRA_libicrt_a_SOURCES
#   ${SRCLIB}/canonicalize-lgpl.c
	${SRCLIB}/error.c
#   ${SRCLIB}/lstat.c
#   ${SRCLIB}/malloc.c
#   ${SRCLIB}/msvc-inval.c
#   ${SRCLIB}/msvc-nothrow.c
#   ${SRCLIB}/raise.c
#   ${SRCLIB}/read.c
#   ${SRCLIB}/readlink.c
#   ${SRCLIB}/progreloc.c
#   ${SRCLIB}/relocatable.c
#   ${SRCLIB}/stdio-write.c
#   ${SRCLIB}/sigprocmask.c
#   ${SRCLIB}/stat-w32.c
#   ${SRCLIB}/stat.c
#   ${SRCLIB}/strerror.c
#   ${SRCLIB}/strerror-override.c
             )
#set_target_properties(icrt
#    PROPERTIES
##    LIBRARY_OUTPUT_DIRECTORY ${CMAKE_HOME_DIRECTORY}/libs/${OUT_SUBDIR}
#     ARCHIVE_OUTPUT_DIRECTORY ${CMAKE_HOME_DIRECTORY}/libs/${OUT_SUBDIR}
#    )
add_custom_command (TARGET icrt POST_BUILD
#	 COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libicrt.a  ${WORK_LIBS}
#	 COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libicrt.a  ${DEVCONUP_LIBS}
#  	 COMMAND ${CMAKE_COMMAND} -E copy ${ARCHIVE_OUTPUT_DIRECTORY}/libicrt.a  ${DEVCONUP_LIBS}
   	 COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_ARCHIVE_OUTPUT_DIRECTORY}/libicrt.a  ${DEVCONUP_LIBS}
	)

