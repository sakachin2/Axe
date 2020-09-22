
#cmake_minimum_required(VERSION 3.4.1)
cmake_minimum_required(VERSION 3.10.2)

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
