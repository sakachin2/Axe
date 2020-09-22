rem  execute in the project
set NDK=x:androidndkW\android-ndk-r5b
set ERR=err
rem cd jni
%NDK%\ndk-build %1 %2 >%ERR%  2>&1
type %ERR%
