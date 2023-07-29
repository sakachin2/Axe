##############################
set(BINFROM ${EXECUTABLE_OUTPUT_PATH})
set(LIBFROM ${LIBRARY_OUTPUT_PATH})	  #2023/06/14    cpp/libs
set(BINSRC ${BINFROM}/)
set(INTERMEDIATE_LIBDIR ${CMAKE_LIBRARY_OUTPUT_DIRECTORY})
add_custom_target(csuball ALL
#          ${CMAKE_COMMAND} -E copy_directory "${BINFROM}" "${DEVCONUP_BIN}"       #vc57
#COMMAND  ${CMAKE_COMMAND} -E copy_directory "${BINFROM}" "${RESOURCE_LIBS}"
#COMMAND  ${CMAKE_COMMAND} -E copy_directory "${CMAKE_LIBRARY_OUTPUT_DIRECTORY}" "${LIBRARY_OUTPUT_PATH}" #2023/06/14
#COMMAND  ${CMAKE_COMMAND} -E copy_directory "${INTERMEDIATE_LIBDIR}" "${LIBFROM}"           #2023/06/14 2023/06/15del
#COMMAND  ${CMAKE_COMMAND} -E copy_directory "${BINFROM}" "${RESOURCE_BIN}"        #vc57
#COMMAND  ${CMAKE_COMMAND} -E copy_directory "${LIBFROM}" "${RESOURCE_LIBS}"                   #2023/06/14   2023/06/15 del 
#COMMAND  ${CMAKE_COMMAND} -E copy_directory "${LIBFROM}" "${DEVCONUP_LIBS}"      #2023/06/14  #2023/06/15 del
#        ${CMAKE_COMMAND} -E xcopy "${BINFROM}" "${DEVCONUP_BIN}"
#        ${CMAKE_COMMAND} -E copy "${BINFROM}/x*" "${DEVCONUP_BIN}/"
#       copy ${BINFROM}/* ${DEVCONUP_BIN}
 DEPENDS
  ${BINSRC}xbc
  ${BINSRC}xci
  ${BINSRC}xcv
  ${BINSRC}xdc
  ${BINSRC}xdd
  ${BINSRC}xdig
  ${BINSRC}xds
  ${BINSRC}xfc
  ${BINSRC}xff
  ${BINSRC}xfg
  ${BINSRC}/xfmt
  ${BINSRC}/xfs
  ${BINSRC}/xlow
  ${BINSRC}/xmj
  ${BINSRC}/xpe
  ${BINSRC}/xts
  ${BINSRC}/xtestenv
 )
add_subdirectory(ulib)
add_subdirectory(xxe)
add_subdirectory(xsub)
