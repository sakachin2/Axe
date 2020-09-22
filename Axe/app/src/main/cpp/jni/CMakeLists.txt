##############################
set(JAVA_JVM_LIBRARY NotNeeded)
#set(JAVA_INCLUDE_PATH2 ${JAVA_HOME}/include)
set(JAVA_INCLUDE_PATH2 "c:/Program Files/Android/Android Studio/jre/include")
set(JAVA_AWT_LIBRARY NotNeeded)
set(JAVA_AWT_INCLUDE_PATH NotNeeded)
find_package(JNI REQUIRED)

include_directories(${INCLUDE_ICONVH})
include_directories(./)
include_directories(gxe)
include_directories(${JAVA_INCLUDE_PATH2}/win32)
include_directories(
					${ULIB}
					${ULIB}/icuLinux/include
					)
include_directories(${XE})
include_directories(../csub/ulib)

#from OLD jni/Android.mk
#cflags= -DARM -DARMAPI9 -DXXE -DUNX -DLNX -DGTK -DUTF8SUPPH -DUTF8UCS2 -DUTF8EBCD $(TRACE) $(DPRINTF) $(GPROF) \
#        -D_FILE_OFFSET_BITS=64 \
#        -D__ANDROID__ \
#        -DLNXLC2 -DLNXLC2TL6 -DUTF8SUPP -DMALLOCCHK \
#        -funsigned-char -Wall -Wformat=0          \
#        -fexceptions        \
#        -Wno-pointer-sign \
#        -Wuninitialized $(OPT)\
#        -Wunreachable-code    \
#        -Wno-invalid-source-encoding \
#        -v \
#        -g3 -O0
set (DEBUGOPT ${OPT_TRACE})

set (CPPDEFS -DARM -DARMAPI9 -DXXE -DUNX -DLNX -DGTK -DUTF8SUPPH -DUTF8UCS2 -DUTF8EBCD ${DEBUGOPT} 
		-D_FILE_OFFSET_BITS=64 
		-D__ANDROID__ 
		-DLNXLC2 -DLNXLC2TL6 -DUTF8SUPP -DMALLOCCHK
		)
#CMake set default 
#  FLAGS = -g -DANDROID -fdata-sections -ffunction-sections -funwind-tables -fstack-protector-strong -no-canonical-prefixes -D_FORTIFY_SOURCE=2 -march=armv7-a -mthumb -Wformat -Werror=format-security  -O0 -fno-limit-debug-info  -fPIC
set (CPPOPTS -funsigned-char -Wall -Wformat=0 
		-fexceptions 
		-Wno-pointer-sign 
		-Wuninitialized 
		-Wunreachable-code 
		-Wno-invalid-source-encoding 
        -v 
#   	-g3
        )
#add_definitions(${CPPDEFS})
#target_compile_definitions(axejni PUBLIC ${CPPDEFS})
#add_compile_options(${CPPOPTS})
#target_compile_options(axejni PUBLIC ${CPPOPTS})
add_definitions(
					${CPPDEFS}
					${CPPOPTS}
             )
############################################
#add_library(axejni SHARED jnij2c.c)
add_library(
             axejni
             SHARED
 jnij2c.c 
 jnic2j.c 
 jnicsub2.c 
 jnia.c   
 jnig.c   
 jnismb.c 
 jnisigh.c
 jniu.c
  gxe/gxe.c     
  gxe/gxemfrm.c 
  gxe/gxeview.c 
  gxe/xxecsub.c 
  gxe/xxecsub2.c
  gxe/xxefile.c 
  gxe/xxekbd.c  
  gxe/xxemain.c 
  gxe/xxemouse.c
  gxe/xxescr.c  
  gxe/xxexei.c  
  gxe/xxexei2.c 
  gxe/xxexei3.c
             )

####################
target_link_libraries(axejni log
				xxe
				${LIBDIR_ICONV}/libiconv.a
                )
####################
#LIBRARY_OUTPUT_PATH did not worked for this txt ###################
add_custom_command (
        TARGET axejni  POST_BUILD
      COMMAND
        ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libaxejni.so ${LIBRARY_OUTPUT_PATH}
      COMMAND
        ${CMAKE_COMMAND} -E copy  ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libaxejni.so ${DEVCONUP_LIBS}
        )
##POST_BUILD for each dose not work
#add_custom_command (
#        TARGET axejni  PRE_BUILD
#      COMMAND
#        ${CMAKE_COMMAND} -E copy  ${LIBRARY_OUTPUT_PATH}/libuserxxe.a    ${DEVCONUP_LIBS}
#      COMMAND
#        ${CMAKE_COMMAND} -E copy  ${LIBRARY_OUTPUT_PATH}/libuserxxe_nc.a ${DEVCONUP_LIBS}
#      COMMAND
#        ${CMAKE_COMMAND} -E copy  ${LIBRARY_OUTPUT_PATH}/libxxe.a        ${DEVCONUP_LIBS}
#        )
