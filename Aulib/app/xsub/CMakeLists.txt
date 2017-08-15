##*CID:##+dateR~:                             update#=   13;##~7224I~
# Sets the minimum version of CMake required to build the native
# library. You should either keep the default value or only pass a
# value of 3.4.0 or lower.

#-MMD
#-MP
#-MF
#./obj/local/armeabi/objs/xbc/__/xbc.o.d
#-fpic
#-ffunction-sections
#-funwind-tables
#-fstack-protector
#-no-canonical-prefixes
#-march=armv5te
#-mtune=xscale
#-msoft-float
#-mthumb
#-Os
#-g
#-DNDEBUG
#-fomit-frame-pointer
#-fno-strict-aliasing
#-finline-limit=64
#-I/g/src/ulib
#-Ijni
#-DANDROID
#""-DNOTRACE
#-DARM
#-DARMAPI9
#-DLNX
#-DUNX
#-DLNXLC2
#-DUTF8SUPPH
#-DUTF8UCS2
#-DUTF8EBCD
#-D_FILE_OFFSET_BITS=64""
#""-funsigned-char
#-Wall
#-Wformat=0
#-Wno-pointer-sign
#-g
#-Wuninitialized
#-Wmaybe-uninitialized
#-O0""
#-Wa,--noexecstack
#-Wformat
#-Werror=format-security
 
cmake_minimum_required(VERSION 3.4.1)


add_subdirectory(ulibnc)          #dependency
#add_subdirectory(userxxe_nc)          #dependency
##############################
set(ULIB g:/src/ulib)
set(XSUB g:/src/xsub)

#-DNDEBUG
set(OPTDEBUG "-DNOTRACE -g3") 
#set(UDEF "-MMD -MP -MF -DANDROID -DARM -DARMAPI9 -DLNX -DUNX -DLNXLC2 -DUTF8SUPPH -DUTF8UCS2 -DUTF8EBCD -D_FILE_OFFSET_BITS=64")
 set(UDEF "             -DANDROID -DARM -DARMAPI9 -DLNX -DUNX -DLNXLC2 -DUTF8SUPPH -DUTF8UCS2 -DUTF8EBCD -D_FILE_OFFSET_BITS=64")##~7225R~
 
#set(CFLGS  "-fpic -ffunction-sections -funwind-tables -fstack-protector -no-canonical-prefixes -mtune=xscale -msoft-float -mthumb -fomit-frame-pointer -fno-strict-aliasing -finline-limit=64 -funsigned-char -Wall -Wformat=0 -Wno-pointer-sign -Wuninitialized -Wa,--noexecstack -Wformat -Werror=format-security")
 set(CFLGS  "-fpic -march=armv7-a      -funwind-tables -fstack-protector -no-canonical-prefixes               -msoft-float         -fomit-frame-pointer -fno-strict-aliasing                   -funsigned-char -Wall -Wformat=0 -Wno-pointer-sign -Wuninitialized                   -Wformat -Werror=format-security")##~7224R~##~7225R~
set(CFLGS2 "-Wno-invalid-source-encoding")
#set(EXECUTABLE_OUTPUT_PATH ${CMAKE_HOME_DIRECTORY}/bin)##~7224R~
set(LINKDIR_ULIB ${CMAKE_HOME_DIRECTORY}/libs)
set(ICONV_TOP  x:/AndroidStudioProjects/Aiconv114)##~7224R~
set(ICONV_LIBS ${ICONV_TOP}/app/libs)##~7224R~
##############################
include_directories(
             	${ULIB}
             )

add_definitions(
                 ${CFLGS}
                 ${CFLGS2}
				 ${OPTDEBUG}
				 ${UDEF}
             )

add_executable(xbc  ${XSUB}/xbc.c )
add_executable(xci  ${XSUB}/xci.c )
add_executable(xcv  ${XSUB}/xcv.c )
add_executable(xdc  ${XSUB}/xdc.c )
add_executable(xdd  ${XSUB}/xdd.c )
add_executable(xdig ${XSUB}/xdig.c)
add_executable(xds  ${XSUB}/xds.c )
add_executable(xfc  ${XSUB}/xfc.c )
add_executable(xff  ${XSUB}/xff.c )
add_executable(xfg  ${XSUB}/xfg.c )
add_executable(xfmt ${XSUB}/xfmt.c)
add_executable(xfs  ${XSUB}/xfs.c )
add_executable(xlow ${XSUB}/xlow.c)
add_executable(xmj  ${XSUB}/xmj.c )
add_executable(xpe  ${XSUB}/xpe.c )
add_executable(xts  ${XSUB}/xts.c )
########
set(DEP_ULIB userxxe_nc)
set(LIB_DEP_ULIB lib${DEP_ULIB}.a)
set(FIND_ULIBNC ${LINKDIR_ULIB}/${LIB_DEP_ULIB})
set(FIND_ULIBNC userxxe_nc)
#set(FIND_ULIBNC ${DEPULIB})
#add_dependencies(xbc  ${DEP_ULIB})
#add_dependencies(xci  ${DEP_ULIB})
#add_dependencies(xcv  ${DEP_ULIB})
#add_dependencies(xdc  ${DEP_ULIB})
#add_dependencies(xdd  ${DEP_ULIB})
#add_dependencies(xdig ${DEP_ULIB})
#add_dependencies(xds  ${DEP_ULIB})
#add_dependencies(xfc  ${DEP_ULIB})
#add_dependencies(xff  ${DEP_ULIB})
#add_dependencies(xfg  ${DEP_ULIB})
#add_dependencies(xfmt ${DEP_ULIB})
#add_dependencies(xfs  ${DEP_ULIB})
#add_dependencies(xlow ${DEP_ULIB})
#add_dependencies(xmj  ${DEP_ULIB})
#add_dependencies(xpe  ${DEP_ULIB})
#add_dependencies(xts  ${DEP_ULIB})
#find_library( FIND_ULIBNC
#              NAMES "${DEP_ULIB}"
#              PATHS "ulibnc"
#			 )
find_library( FIND_ULIBNC
              userxxe_nc
			 )
########
set(LINK_LIBS ${FIND_ULIBNC} ${ICONV_LIBS}/libiconv.a log)##~7224R~##+7225R~
#set(CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAG} --verbose")##~7224I~##+7225R~
target_link_libraries(xbc  ${LINK_LIBS})
target_link_libraries(xci  ${LINK_LIBS})
target_link_libraries(xcv  ${LINK_LIBS})
target_link_libraries(xdc  ${LINK_LIBS})
target_link_libraries(xdd  ${LINK_LIBS})
target_link_libraries(xdig ${LINK_LIBS})
target_link_libraries(xds  ${LINK_LIBS})
target_link_libraries(xfc  ${LINK_LIBS})
target_link_libraries(xff  ${LINK_LIBS})
target_link_libraries(xfg  ${LINK_LIBS})
target_link_libraries(xfmt ${LINK_LIBS})
target_link_libraries(xfs  ${LINK_LIBS})
target_link_libraries(xlow ${LINK_LIBS})
target_link_libraries(xmj  ${LINK_LIBS})
target_link_libraries(xpe  ${LINK_LIBS})
target_link_libraries(xts  ${LINK_LIBS})
target_link_libraries(xbc  ${LINK_LIBS})
target_link_libraries(xci  ${LINK_LIBS})
target_link_libraries(xcv  ${LINK_LIBS})
target_link_libraries(xdc  ${LINK_LIBS})
target_link_libraries(xdd  ${LINK_LIBS})
target_link_libraries(xdig ${LINK_LIBS})
target_link_libraries(xds  ${LINK_LIBS})
target_link_libraries(xfc  ${LINK_LIBS})
target_link_libraries(xff  ${LINK_LIBS})
target_link_libraries(xfg  ${LINK_LIBS})
target_link_libraries(xfmt ${LINK_LIBS})
target_link_libraries(xfs  ${LINK_LIBS})
target_link_libraries(xlow ${LINK_LIBS})
target_link_libraries(xmj  ${LINK_LIBS})
target_link_libraries(xpe  ${LINK_LIBS})
target_link_libraries(xts  ${LINK_LIBS})

