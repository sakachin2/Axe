##*CID:##+v700R~:                             update#=   92;
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
#set(OPTDEBUG "-DNOTRACE -g3")
set(OPTDEBUG ${OPT_TRACE})
#set(UDEF "-MMD -MP -MF -DANDROID -DARM -DARMAPI9 -DLNX -DUNX -DLNXLC2 -DUTF8SUPPH -DUTF8UCS2 -DUTF8EBCD -D_FILE_OFFSET_BITS=64")
#set(UDEF "             -DANDROID -DARM -DARMAPI9 -DLNX -DUNX -DLNXLC2 -DUTF8SUPPH -DUTF8UCS2 -DUTF8EBCD -D_FILE_OFFSET_BITS=64 ${OPTDEBUG}")##~v700R~
 set(UDEF "-DXSUB       -DANDROID -DARM -DARMAPI9 -DLNX -DUNX -DLNXLC2 -DUTF8SUPPH -DUTF8UCS2 -DUTF8EBCD -D_FILE_OFFSET_BITS=64 ${OPTDEBUG}")##~v700I~
 
#set(CFLGS  "-fpic -ffunction-sections -funwind-tables -fstack-protector -no-canonical-prefixes -mtune=xscale -msoft-float -mthumb -fomit-frame-pointer -fno-strict-aliasing -finline-limit=64 -funsigned-char -Wall -Wformat=0 -Wno-pointer-sign -Wuninitialized -Wa,--noexecstack -Wformat -Werror=format-security")
#set(CFLGS  "-fpic -march=armv7-a      -funwind-tables -fstack-protector -no-canonical-prefixes               -msoft-float         -fomit-frame-pointer -fno-strict-aliasing                   -funsigned-char -Wall -Wformat=0 -Wno-pointer-sign -Wuninitialized                   -Wformat -Werror=format-security")
 set(CFLGS  "-fpic                     -funwind-tables -fstack-protector -no-canonical-prefixes                                    -fomit-frame-pointer -fno-strict-aliasing                   -funsigned-char -Wall -Wformat=0 -Wno-pointer-sign -Wuninitialized                   -Wformat -Werror=format-security")
set(CFLGS2 "-Wno-invalid-source-encoding -Wno-misleading-indentation")##~v700R~
#set(CFLGS3 "-Wl,-E")##~v700R~
#set(CFLGS3 "-e main")##~v700R~
#set(CFLGS3 -shared -fPIC -pie)       #2023/06/16##~v700R~
#set(CFLGS3 -fPIC -DANDROID_PIE=ON -Wl,-E)       #2023/06/16##~v700R~
#set(CFLGS3 -fPIC -DANDROID_PIE=ON -Wl,-shared -Wl,-E)       #2023/06/16##~v700R~
#set(CFLGS3 -fPIC -DANDROID_PIE=ON)       #2023/06/20##~v700R~
#set(CFLGS3 -fPIC -DANDROID_PIE=ON -Wl,--static -Wl,--gc-sections)       #2023/06/22 for _so to remove dead section##~v700R~
#set(CFLGS3 -fPIC -DANDROID_PIE=ON                               )       #2023/06/22 for _so to remove dead section##~v700R~
#set(CFLGS3 -fPIC -DANDROID_PIE=ON -ffunction-sections -fdata-sections)  #2023/06/23##~v700R~
 set(CFLGS3 -fPIC -DANDROID_PIE=ON                                    )  #2023/06/23##~v700I~
#set(LDFLAGS3 "-e _start")        #2023/06/16##~v700R~
#set(LDFLAGS3 "-e __libc_main")        #2023/06/16##~v700R~
#set(LDFLAGS3 "-e main" -shared -fPIC -pie -E)        #2023/06/16##~v700R~
#set(LDFLAGS3 -shared -fPIC -pie)        #2023/06/16##~v700R~
#set(LDFLAGS3 -e main -shared -fPIC -pie -E)        #2023/06/16##~v700I~
#set(LDFLAGS3 -Wl,--static -Wl,--gc-sections)        #2023/06/16##~v700R~
#set(LDFLAGS3              -Wl,--gc-sections)        #2023/06/23##~v700R~
#set(EXECUTABLE_OUTPUT_PATH ${CMAKE_HOME_DIRECTORY}/bin)
#set(LINKDIR_ULIB ${CMAKE_HOME_DIRECTORY}/libs)
#set(ICONV_TOP  w:/AndroidStudioProjects/Aiconv114)##~v700R~
#set(ICONV_LIBS ${ICONV_TOP}/app/libs)
set(ICONV_LIBS ${LIBDIR_ICONV})
set(DEP_HIGHLIGHT ${CMAKE_HOME_DIRECTORY}/bin/highlight_pie)##~v700I~
#################################~v700R~
#add_custom_target(csuball ALL##~v700I~
#        ${CMAKE_COMMAND} -E copy ${EXECUTABLE_OUTPUT_PATH}/* ${DEVCONUP_BIN}##~v700I~
# DEPENDS##~v700I~
# xbc##~v700I~
# xci##~v700I~
# xcv##~v700I~
# xdc##~v700I~
# xdd##~v700I~
# xdig##~v700I~
# xds##~v700I~
# xfc##~v700I~
# xff##~v700I~
# xfg##~v700I~
# xfmt##~v700I~
# xfs##~v700I~
# xlow##~v700I~
# xmj##~v700I~
# xpe##~v700I~
# xts##~v700I~
# )##~v700I~
#################################~v700I~
include_directories(
				${INCLUDE_ULIB_UPDATE}
				${INCLUDE_ICONVH}
             	${ULIB}
             	sys
				../ulib          #sys/timeb @@@@
             )

add_definitions(
                 ${CFLGS}
                 ${CFLGS2}
				 ${OPT_STRIP}##~v700I~
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
add_executable(xtestenv ${XSUB}/xtestenv.c )
########  libbrary of tools  2023/06/14##~v700R~
add_library(xbc_so      SHARED  ${XSUB}/xbc.c)##~v700R~
add_library(xci_so      SHARED  ${XSUB}/xci.c)##~v700R~
add_library(xcv_so      SHARED  ${XSUB}/xcv.c)##~v700R~
add_library(xdc_so      SHARED  ${XSUB}/xdc.c)##~v700R~
add_library(xdd_so      SHARED  ${XSUB}/xdd.c)##~v700R~
add_library(xdig_so     SHARED  ${XSUB}/xdig.c)##~v700R~
add_library(xds_so      SHARED  ${XSUB}/xds.c)##~v700R~
add_library(xfc_so      SHARED  ${XSUB}/xfc.c)##~v700R~
add_library(xff_so      SHARED  ${XSUB}/xff.c)##~v700R~
add_library(xfg_so      SHARED  ${XSUB}/xfg.c)##~v700R~
add_library(xfmt_so     SHARED  ${XSUB}/xfmt.c)##~v700R~
add_library(xfs_so      SHARED  ${XSUB}/xts.c)##~v700R~
add_library(xlow_so     SHARED  ${XSUB}/xlow.c)##~v700R~
add_library(xmj_so      SHARED  ${XSUB}/xmj.c)##~v700R~
add_library(xpe_so      SHARED  ${XSUB}/xpe.c)##~v700R~
add_library(xts_so      SHARED  ${XSUB}/xts.c)##~v700R~
add_library(xtestenv_so SHARED  ${XSUB}/xtestenv.c) #2023/06/16##+v700R~
##########~v700I~
target_compile_options(xbc_so       PRIVATE ${CFLGS3})##+v700I~
target_compile_options(xci_so       PRIVATE ${CFLGS3})##+v700I~
target_compile_options(xcv_so       PRIVATE ${CFLGS3})##+v700I~
target_compile_options(xdc_so       PRIVATE ${CFLGS3})##+v700I~
target_compile_options(xdd_so       PRIVATE ${CFLGS3})##+v700I~
target_compile_options(xdig_so      PRIVATE ${CFLGS3})##+v700I~
target_compile_options(xds_so       PRIVATE ${CFLGS3})##+v700I~
target_compile_options(xfc_so       PRIVATE ${CFLGS3})##+v700I~
target_compile_options(xff_so       PRIVATE ${CFLGS3})##+v700I~
target_compile_options(xfg_so       PRIVATE ${CFLGS3})##+v700I~
target_compile_options(xfmt_so      PRIVATE ${CFLGS3})##+v700I~
target_compile_options(xfs_so       PRIVATE ${CFLGS3})##+v700I~
target_compile_options(xlow_so      PRIVATE ${CFLGS3})##+v700I~
target_compile_options(xmj_so       PRIVATE ${CFLGS3})##+v700I~
target_compile_options(xpe_so       PRIVATE ${CFLGS3})##+v700I~
target_compile_options(xts_so       PRIVATE ${CFLGS3})##+v700I~
target_compile_options(xtestenv_so  PRIVATE ${CFLGS3})##+v700I~
##+v700I~
##+v700I~
##########~v700I~
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
#add_dependencies(xtestenv ${DEP_ULIB})
#add_dependencies(xtestenv ${DEP_HIGHLIGHT})     #vc57##~v700I~
#find_library( FIND_ULIBNC
#              NAMES "${DEP_ULIB}"
#              PATHS "ulibnc"
#			 )
find_library( FIND_ULIBNC
              userxxe_nc
			 )
#find_library( FIND_LIBICONV##~v700I~
#              NAMES iconv   HINTS "${CMAKE_HOME_DIRECTORY}/Aiconv/biniconv/libiconv"##~v700I~
#			 )##~v700I~
########
#set(LINK_LIBS ${FIND_ULIBNC} ${ICONV_LIBS}/libiconv.a log)##~v700R~
#set(LINK_LIBS ${FIND_ULIBNC} ${ICONV_LIBS}/libiconv.a log ${OPT_STRIP})##~v700R~
#et(LINK_LIBS ${FIND_ULIBNC}  iconv                    log ${OPT_STRIP})##~v700R~
#set(LINK_LIBS ${FIND_ULIBNC}  ${ICONV_LIBS}/libiconv.a log ${OPT_STRIP})  #vc57##~v700R~
set(LINK_LIBS ${FIND_ULIBNC}   iconv            log ${OPT_STRIP})  #vc57##~v700I~
#set(CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAG} --verbose")
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
target_link_libraries(xtestenv  ${LINK_LIBS})##~v700R~
######################~v700I~
target_link_libraries(xfc_so      ${LINK_LIBS} ${LDFLAGS3})##+v700R~
target_link_libraries(xbc_so      ${LINK_LIBS} ${LDFLAGS3})##+v700R~
target_link_libraries(xci_so      ${LINK_LIBS} ${LDFLAGS3})##+v700R~
target_link_libraries(xcv_so      ${LINK_LIBS} ${LDFLAGS3})##+v700R~
target_link_libraries(xdc_so      ${LINK_LIBS} ${LDFLAGS3})##+v700R~
target_link_libraries(xdd_so      ${LINK_LIBS} ${LDFLAGS3})##+v700R~
target_link_libraries(xdig_so     ${LINK_LIBS} ${LDFLAGS3})##+v700R~
target_link_libraries(xds_so      ${LINK_LIBS} ${LDFLAGS3})##+v700R~
target_link_libraries(xfc_so      ${LINK_LIBS} ${LDFLAGS3})##+v700R~
target_link_libraries(xff_so      ${LINK_LIBS} ${LDFLAGS3})##+v700R~
target_link_libraries(xfg_so      ${LINK_LIBS} ${LDFLAGS3})##+v700R~
target_link_libraries(xfmt_so     ${LINK_LIBS} ${LDFLAGS3})##+v700R~
target_link_libraries(xfs_so      ${LINK_LIBS} ${LDFLAGS3})##+v700R~
target_link_libraries(xlow_so     ${LINK_LIBS} ${LDFLAGS3})##+v700R~
target_link_libraries(xmj_so      ${LINK_LIBS} ${LDFLAGS3})##+v700R~
target_link_libraries(xpe_so      ${LINK_LIBS} ${LDFLAGS3})##+v700R~
target_link_libraries(xts_so      ${LINK_LIBS} ${LDFLAGS3})##+v700R~
target_link_libraries(xtestenv_so ${LINK_LIBS} ${LDFLAGS3}) #2023/06/16##~v700R~
##+v700I~
####################
####################
#LIBRARY_OUTPUT_PATH did not worked for this txt ==>alternative by custom_target##~v700R~
#set (BIN xbc) add_custom_command (TARGET ${BIN} POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy ${EXECUTABLE_OUTPUT_PATH}/${BIN} ${DEVCONUP_BIN})##~v700R~
# -->cpp/libs##~v700I~
add_custom_command (TARGET xfc_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxfc_so.so         ${LIBRARY_OUTPUT_PATH} ) #2023/0618##~v700I~
add_custom_command (TARGET xbc_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxbc_so.so         ${LIBRARY_OUTPUT_PATH} ) #2023/0618##~v700I~
add_custom_command (TARGET xci_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxci_so.so         ${LIBRARY_OUTPUT_PATH} ) #2023/0618##~v700I~
add_custom_command (TARGET xcv_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxcv_so.so         ${LIBRARY_OUTPUT_PATH} ) #2023/0618##~v700I~
add_custom_command (TARGET xdc_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxdc_so.so         ${LIBRARY_OUTPUT_PATH} ) #2023/0618##~v700I~
add_custom_command (TARGET xdd_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxdd_so.so         ${LIBRARY_OUTPUT_PATH} ) #2023/0618##~v700I~
add_custom_command (TARGET xdig_so     POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxdig_so.so        ${LIBRARY_OUTPUT_PATH} ) #2023/0618##~v700I~
add_custom_command (TARGET xds_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxds_so.so         ${LIBRARY_OUTPUT_PATH} ) #2023/0618##~v700I~
add_custom_command (TARGET xfc_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxfc_so.so         ${LIBRARY_OUTPUT_PATH} ) #2023/0618##~v700I~
add_custom_command (TARGET xff_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxff_so.so         ${LIBRARY_OUTPUT_PATH} ) #2023/0618##~v700I~
add_custom_command (TARGET xfg_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxfg_so.so         ${LIBRARY_OUTPUT_PATH} ) #2023/0618##~v700I~
add_custom_command (TARGET xfmt_so     POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxfmt_so.so        ${LIBRARY_OUTPUT_PATH} ) #2023/0618##~v700I~
add_custom_command (TARGET xfs_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxfs_so.so         ${LIBRARY_OUTPUT_PATH} ) #2023/0618##~v700I~
add_custom_command (TARGET xlow_so     POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxlow_so.so        ${LIBRARY_OUTPUT_PATH} ) #2023/0618##~v700I~
add_custom_command (TARGET xmj_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxmj_so.so         ${LIBRARY_OUTPUT_PATH} ) #2023/0618##~v700I~
add_custom_command (TARGET xpe_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxpe_so.so         ${LIBRARY_OUTPUT_PATH} ) #2023/0618##~v700I~
add_custom_command (TARGET xts_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxts_so.so         ${LIBRARY_OUTPUT_PATH} ) #2023/0618##~v700I~
add_custom_command (TARGET xtestenv_so POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxtestenv_so.so    ${LIBRARY_OUTPUT_PATH} ) #2023/0616##~v700I~
# -->main/resources/libs##~v700I~
add_custom_command (TARGET xfc_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxfc_so.so         ${RESOURCE_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xbc_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxbc_so.so         ${RESOURCE_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xci_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxci_so.so         ${RESOURCE_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xcv_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxcv_so.so         ${RESOURCE_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xdc_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxdc_so.so         ${RESOURCE_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xdd_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxdd_so.so         ${RESOURCE_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xdig_so     POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxdig_so.so        ${RESOURCE_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xds_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxds_so.so         ${RESOURCE_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xfc_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxfc_so.so         ${RESOURCE_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xff_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxff_so.so         ${RESOURCE_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xfg_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxfg_so.so         ${RESOURCE_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xfmt_so     POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxfmt_so.so        ${RESOURCE_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xfs_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxfs_so.so         ${RESOURCE_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xlow_so     POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxlow_so.so        ${RESOURCE_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xmj_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxmj_so.so         ${RESOURCE_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xpe_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxpe_so.so         ${RESOURCE_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xts_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxts_so.so         ${RESOURCE_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xtestenv_so POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxtestenv_so.so    ${RESOURCE_LIBS} ) #2023/0616##~v700I~
# -->DEvConUp##~v700I~
add_custom_command (TARGET xfc_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxfc_so.so         ${DEVCONUP_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xbc_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxbc_so.so         ${DEVCONUP_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xci_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxci_so.so         ${DEVCONUP_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xcv_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxcv_so.so         ${DEVCONUP_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xdc_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxdc_so.so         ${DEVCONUP_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xdd_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxdd_so.so         ${DEVCONUP_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xdig_so     POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxdig_so.so        ${DEVCONUP_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xds_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxds_so.so         ${DEVCONUP_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xfc_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxfc_so.so         ${DEVCONUP_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xff_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxff_so.so         ${DEVCONUP_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xfg_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxfg_so.so         ${DEVCONUP_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xfmt_so     POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxfmt_so.so        ${DEVCONUP_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xfs_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxfs_so.so         ${DEVCONUP_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xlow_so     POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxlow_so.so        ${DEVCONUP_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xmj_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxmj_so.so         ${DEVCONUP_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xpe_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxpe_so.so         ${DEVCONUP_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xts_so      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxts_so.so         ${DEVCONUP_LIBS} ) #2023/0618##~v700I~
add_custom_command (TARGET xtestenv_so POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libxtestenv_so.so    ${DEVCONUP_LIBS} ) #2023/0616##~v700I~
#### HOME/bin -->HOME/lobs #####~v700I~
#add_custom_command (TARGET xtestenv POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xtestenv ${LIBRARY_OUTPUT_PATH}/libxtestenv.so)##~v700R~
#add_custom_command (TARGET xbc      POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xbc      ${LIBRARY_OUTPUT_PATH}/libxbc.so     )##~v700R~
#csub/CmakeList copy all bin including iconvbin, del it , only iconv remains                   #vc57##~v700I~
#add_custom_command (TARGET xtestenv    POST_BUILD COMMAND ${CMAKE_COMMAND} -E remove ${EXECUTABLE_OUTPUT_PATH}/iconvbin ) #vc57##~v700R~
add_custom_command (TARGET xbc        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xbc      ${RESOURCE_BIN}    )##~v700I~
add_custom_command (TARGET xci        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xci      ${RESOURCE_BIN}    )##~v700I~
add_custom_command (TARGET xcv        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xcv      ${RESOURCE_BIN}    )##~v700I~
add_custom_command (TARGET xdc        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xdc      ${RESOURCE_BIN}    )##~v700I~
add_custom_command (TARGET xdd        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xdd      ${RESOURCE_BIN}    )##~v700I~
add_custom_command (TARGET xdig       POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xdig     ${RESOURCE_BIN}    )##~v700I~
add_custom_command (TARGET xds        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xds      ${RESOURCE_BIN}    )##~v700I~
add_custom_command (TARGET xfc        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xfc      ${RESOURCE_BIN}    )##~v700I~
add_custom_command (TARGET xff        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xff      ${RESOURCE_BIN}    )##~v700I~
add_custom_command (TARGET xfg        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xfg      ${RESOURCE_BIN}    )##~v700I~
add_custom_command (TARGET xfmt       POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xfmt     ${RESOURCE_BIN}    )##~v700I~
add_custom_command (TARGET xfs        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xfs      ${RESOURCE_BIN}    )##~v700I~
add_custom_command (TARGET xlow       POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xlow     ${RESOURCE_BIN}    )##~v700I~
add_custom_command (TARGET xmj        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xmj      ${RESOURCE_BIN}    )##~v700I~
add_custom_command (TARGET xpe        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xpe      ${RESOURCE_BIN}    )##~v700I~
add_custom_command (TARGET xts        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xts      ${RESOURCE_BIN}    )##~v700I~
add_custom_command (TARGET xtestenv   POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xtestenv ${RESOURCE_BIN}    )##~v700I~
##+v700I~
add_custom_command (TARGET xbc        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xbc      ${DEVCONUP_BIN}    )##~v700I~
add_custom_command (TARGET xci        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xci      ${DEVCONUP_BIN}    )##~v700I~
add_custom_command (TARGET xcv        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xcv      ${DEVCONUP_BIN}    )##~v700I~
add_custom_command (TARGET xdc        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xdc      ${DEVCONUP_BIN}    )##~v700I~
add_custom_command (TARGET xdd        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xdd      ${DEVCONUP_BIN}    )##~v700I~
add_custom_command (TARGET xdig       POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xdig     ${DEVCONUP_BIN}    )##~v700I~
add_custom_command (TARGET xds        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xds      ${DEVCONUP_BIN}    )##~v700I~
add_custom_command (TARGET xfc        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xfc      ${DEVCONUP_BIN}    )##~v700I~
add_custom_command (TARGET xff        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xff      ${DEVCONUP_BIN}    )##~v700I~
add_custom_command (TARGET xfg        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xfg      ${DEVCONUP_BIN}    )##~v700I~
add_custom_command (TARGET xfmt       POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xfmt     ${DEVCONUP_BIN}    )##~v700I~
add_custom_command (TARGET xfs        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xfs      ${DEVCONUP_BIN}    )##~v700I~
add_custom_command (TARGET xlow       POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xlow     ${DEVCONUP_BIN}    )##~v700I~
add_custom_command (TARGET xmj        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xmj      ${DEVCONUP_BIN}    )##~v700I~
add_custom_command (TARGET xpe        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xpe      ${DEVCONUP_BIN}    )##~v700I~
add_custom_command (TARGET xts        POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xts      ${DEVCONUP_BIN}    )##~v700I~
add_custom_command (TARGET xtestenv   POST_BUILD COMMAND ${CMAKE_COMMAND} -E copy  ${EXECUTABLE_OUTPUT_PATH}/xtestenv ${DEVCONUP_BIN}    )##~v700I~
