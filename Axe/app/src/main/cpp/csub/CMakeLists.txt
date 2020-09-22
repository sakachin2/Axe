##############################
set(BINFROM ${EXECUTABLE_OUTPUT_PATH})
set(BINSRC ${BINFROM}/)
add_custom_target(csuball ALL
         ${CMAKE_COMMAND} -E copy_directory "${BINFROM}" "${DEVCONUP_BIN}"
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
 )
add_subdirectory(ulib)
add_subdirectory(xxe)
add_subdirectory(xsub)
