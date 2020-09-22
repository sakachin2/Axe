builddir="`pwd`"; cd libcharset && make all && make install-lib libdir="$builddir/lib" includedir="$builddir/lib"
make[1]: ディレクトリ '/home/sak/libiconv-1.16/libcharset' に入ります
cd lib && make all
make[2]: ディレクトリ '/home/sak/libiconv-1.16/libcharset/lib' に入ります
/bin/bash ../libtool --mode=compile gcc -I. -I. -I.. -I./.. -I../include -g -O2 -fvisibility=hidden  -DBUILDING_LIBCHARSET -DHAVE_CONFIG_H -c ./localcharset.c
libtool: compile:  gcc -I. -I. -I.. -I./.. -I../include -g -O2 -fvisibility=hidden -DBUILDING_LIBCHARSET -DHAVE_CONFIG_H -c ./localcharset.c  -fPIC -DPIC -o .libs/localcharset.o
libtool: compile:  gcc -I. -I. -I.. -I./.. -I../include -g -O2 -fvisibility=hidden -DBUILDING_LIBCHARSET -DHAVE_CONFIG_H -c ./localcharset.c -o localcharset.o >/dev/null 2>&1
/bin/bash ../libtool --mode=compile gcc -I. -I. -I.. -I./.. -I../include -g -O2 -fvisibility=hidden  -DBUILDING_LIBCHARSET -DHAVE_CONFIG_H -c ./relocatable-stub.c
libtool: compile:  gcc -I. -I. -I.. -I./.. -I../include -g -O2 -fvisibility=hidden -DBUILDING_LIBCHARSET -DHAVE_CONFIG_H -c ./relocatable-stub.c  -fPIC -DPIC -o .libs/relocatable-stub.o
libtool: compile:  gcc -I. -I. -I.. -I./.. -I../include -g -O2 -fvisibility=hidden -DBUILDING_LIBCHARSET -DHAVE_CONFIG_H -c ./relocatable-stub.c -o relocatable-stub.o >/dev/null 2>&1
/bin/bash ../libtool --mode=link gcc  -g -O2 -fvisibility=hidden -o libcharset.la -rpath /home/sak/libiconv-1.16.build/lib -version-info 1:0:0 -no-undefined localcharset.lo relocatable-stub.lo
libtool: link: gcc -shared  -fPIC -DPIC  .libs/localcharset.o .libs/relocatable-stub.o    -g -O2   -Wl,-soname -Wl,libcharset.so.1 -o .libs/libcharset.so.1.0.0
libtool: link: (cd ".libs" && rm -f "libcharset.so.1" && ln -s "libcharset.so.1.0.0" "libcharset.so.1")
libtool: link: (cd ".libs" && rm -f "libcharset.so" && ln -s "libcharset.so.1.0.0" "libcharset.so")
libtool: link: ar cru .libs/libcharset.a  localcharset.o relocatable-stub.o
ar: `u' modifier ignored since `D' is the default (see `U')
libtool: link: ranlib .libs/libcharset.a
libtool: link: ( cd ".libs" && rm -f "libcharset.la" && ln -s "../libcharset.la" "libcharset.la" )
make[2]: ディレクトリ '/home/sak/libiconv-1.16/libcharset/lib' から出ます
make[1]: ディレクトリ '/home/sak/libiconv-1.16/libcharset' から出ます
make[1]: ディレクトリ '/home/sak/libiconv-1.16/libcharset' に入ります
cd lib && make all
make[2]: ディレクトリ '/home/sak/libiconv-1.16/libcharset/lib' に入ります
make[2]: 'all' に対して行うべき事はありません.
make[2]: ディレクトリ '/home/sak/libiconv-1.16/libcharset/lib' から出ます
cd lib && make install-lib libdir='/home/sak/libiconv-1.16/lib' includedir='/home/sak/libiconv-1.16/lib'
make[2]: ディレクトリ '/home/sak/libiconv-1.16/libcharset/lib' に入ります
/bin/bash ../build-aux/mkinstalldirs /home/sak/libiconv-1.16/lib
/bin/bash ../libtool --mode=install /usr/bin/install -c libcharset.la /home/sak/libiconv-1.16/lib/libcharset.la
libtool: install: /usr/bin/install -c .libs/libcharset.so.1.0.0 /home/sak/libiconv-1.16/lib/libcharset.so.1.0.0
libtool: install: (cd /home/sak/libiconv-1.16/lib && { ln -s -f libcharset.so.1.0.0 libcharset.so.1 || { rm -f libcharset.so.1 && ln -s libcharset.so.1.0.0 libcharset.so.1; }; })
libtool: install: (cd /home/sak/libiconv-1.16/lib && { ln -s -f libcharset.so.1.0.0 libcharset.so || { rm -f libcharset.so && ln -s libcharset.so.1.0.0 libcharset.so; }; })
libtool: install: /usr/bin/install -c .libs/libcharset.lai /home/sak/libiconv-1.16/lib/libcharset.la
libtool: install: /usr/bin/install -c .libs/libcharset.a /home/sak/libiconv-1.16/lib/libcharset.a
libtool: install: chmod 644 /home/sak/libiconv-1.16/lib/libcharset.a
libtool: install: ranlib /home/sak/libiconv-1.16/lib/libcharset.a
libtool: warning: remember to run 'libtool --finish /home/sak/libiconv-1.16.build/lib'
make[2]: ディレクトリ '/home/sak/libiconv-1.16/libcharset/lib' から出ます
/bin/bash ./build-aux/mkinstalldirs /home/sak/libiconv-1.16/lib
/usr/bin/install -c -m 644 include/libcharset.h /home/sak/libiconv-1.16/lib/libcharset.h
/usr/bin/install -c -m 644 include/localcharset.h.inst /home/sak/libiconv-1.16/lib/localcharset.h
make[1]: ディレクトリ '/home/sak/libiconv-1.16/libcharset' から出ます
cd lib && make all
make[1]: ディレクトリ '/home/sak/libiconv-1.16/lib' に入ります
/bin/bash ../libtool --mode=compile gcc -I. -I. -I../include -I./../include -I.. -I./..  -g -O2 -fvisibility=hidden -DLIBDIR=\"/home/sak/libiconv-1.16.build/lib\" -DBUILDING_LIBICONV -DBUILDING_DLL -DENABLE_RELOCATABLE=1 -DIN_LIBRARY -DINSTALLDIR=\"/home/sak/libiconv-1.16.build/lib\" -DNO_XMALLOC -Dset_relocation_prefix=libiconv_set_relocation_prefix -Drelocate=libiconv_relocate -Drelocate2=libiconv_relocate2 -DHAVE_CONFIG_H -c ./iconv.c
libtool: compile:  gcc -I. -I. -I../include -I./../include -I.. -I./.. -g -O2 -fvisibility=hidden -DLIBDIR=\"/home/sak/libiconv-1.16.build/lib\" -DBUILDING_LIBICONV -DBUILDING_DLL -DENABLE_RELOCATABLE=1 -DIN_LIBRARY -DINSTALLDIR=\"/home/sak/libiconv-1.16.build/lib\" -DNO_XMALLOC -Dset_relocation_prefix=libiconv_set_relocation_prefix -Drelocate=libiconv_relocate -Drelocate2=libiconv_relocate2 -DHAVE_CONFIG_H -c ./iconv.c  -fPIC -DPIC -o .libs/iconv.o
libtool: compile:  gcc -I. -I. -I../include -I./../include -I.. -I./.. -g -O2 -fvisibility=hidden -DLIBDIR=\"/home/sak/libiconv-1.16.build/lib\" -DBUILDING_LIBICONV -DBUILDING_DLL -DENABLE_RELOCATABLE=1 -DIN_LIBRARY -DINSTALLDIR=\"/home/sak/libiconv-1.16.build/lib\" -DNO_XMALLOC -Dset_relocation_prefix=libiconv_set_relocation_prefix -Drelocate=libiconv_relocate -Drelocate2=libiconv_relocate2 -DHAVE_CONFIG_H -c ./iconv.c -o iconv.o >/dev/null 2>&1
/bin/bash ../libtool --mode=compile gcc -I. -I. -I../include -I./../include -I.. -I./..  -g -O2 -fvisibility=hidden -DLIBDIR=\"/home/sak/libiconv-1.16.build/lib\" -DBUILDING_LIBICONV -DBUILDING_DLL -DENABLE_RELOCATABLE=1 -DIN_LIBRARY -DINSTALLDIR=\"/home/sak/libiconv-1.16.build/lib\" -DNO_XMALLOC -Dset_relocation_prefix=libiconv_set_relocation_prefix -Drelocate=libiconv_relocate -Drelocate2=libiconv_relocate2 -DHAVE_CONFIG_H -c ./../libcharset/lib/localcharset.c
libtool: compile:  gcc -I. -I. -I../include -I./../include -I.. -I./.. -g -O2 -fvisibility=hidden -DLIBDIR=\"/home/sak/libiconv-1.16.build/lib\" -DBUILDING_LIBICONV -DBUILDING_DLL -DENABLE_RELOCATABLE=1 -DIN_LIBRARY -DINSTALLDIR=\"/home/sak/libiconv-1.16.build/lib\" -DNO_XMALLOC -Dset_relocation_prefix=libiconv_set_relocation_prefix -Drelocate=libiconv_relocate -Drelocate2=libiconv_relocate2 -DHAVE_CONFIG_H -c ./../libcharset/lib/localcharset.c  -fPIC -DPIC -o .libs/localcharset.o
libtool: compile:  gcc -I. -I. -I../include -I./../include -I.. -I./.. -g -O2 -fvisibility=hidden -DLIBDIR=\"/home/sak/libiconv-1.16.build/lib\" -DBUILDING_LIBICONV -DBUILDING_DLL -DENABLE_RELOCATABLE=1 -DIN_LIBRARY -DINSTALLDIR=\"/home/sak/libiconv-1.16.build/lib\" -DNO_XMALLOC -Dset_relocation_prefix=libiconv_set_relocation_prefix -Drelocate=libiconv_relocate -Drelocate2=libiconv_relocate2 -DHAVE_CONFIG_H -c ./../libcharset/lib/localcharset.c -o localcharset.o >/dev/null 2>&1
/bin/bash ../libtool --mode=compile gcc -I. -I. -I../include -I./../include -I.. -I./..  -g -O2 -fvisibility=hidden -DLIBDIR=\"/home/sak/libiconv-1.16.build/lib\" -DBUILDING_LIBICONV -DBUILDING_DLL -DENABLE_RELOCATABLE=1 -DIN_LIBRARY -DINSTALLDIR=\"/home/sak/libiconv-1.16.build/lib\" -DNO_XMALLOC -Dset_relocation_prefix=libiconv_set_relocation_prefix -Drelocate=libiconv_relocate -Drelocate2=libiconv_relocate2 -DHAVE_CONFIG_H -c ./relocatable.c
libtool: compile:  gcc -I. -I. -I../include -I./../include -I.. -I./.. -g -O2 -fvisibility=hidden -DLIBDIR=\"/home/sak/libiconv-1.16.build/lib\" -DBUILDING_LIBICONV -DBUILDING_DLL -DENABLE_RELOCATABLE=1 -DIN_LIBRARY -DINSTALLDIR=\"/home/sak/libiconv-1.16.build/lib\" -DNO_XMALLOC -Dset_relocation_prefix=libiconv_set_relocation_prefix -Drelocate=libiconv_relocate -Drelocate2=libiconv_relocate2 -DHAVE_CONFIG_H -c ./relocatable.c  -fPIC -DPIC -o .libs/relocatable.o
libtool: compile:  gcc -I. -I. -I../include -I./../include -I.. -I./.. -g -O2 -fvisibility=hidden -DLIBDIR=\"/home/sak/libiconv-1.16.build/lib\" -DBUILDING_LIBICONV -DBUILDING_DLL -DENABLE_RELOCATABLE=1 -DIN_LIBRARY -DINSTALLDIR=\"/home/sak/libiconv-1.16.build/lib\" -DNO_XMALLOC -Dset_relocation_prefix=libiconv_set_relocation_prefix -Drelocate=libiconv_relocate -Drelocate2=libiconv_relocate2 -DHAVE_CONFIG_H -c ./relocatable.c -o relocatable.o >/dev/null 2>&1
/bin/bash ../libtool --mode=link gcc  -g -O2 -fvisibility=hidden -o libiconv.la -rpath /home/sak/libiconv-1.16.build/lib -version-info 8:1:6 -no-undefined iconv.lo localcharset.lo relocatable.lo  
libtool: link: gcc -shared  -fPIC -DPIC  .libs/iconv.o .libs/localcharset.o .libs/relocatable.o    -g -O2   -Wl,-soname -Wl,libiconv.so.2 -o .libs/libiconv.so.2.6.1
libtool: link: (cd ".libs" && rm -f "libiconv.so.2" && ln -s "libiconv.so.2.6.1" "libiconv.so.2")
libtool: link: (cd ".libs" && rm -f "libiconv.so" && ln -s "libiconv.so.2.6.1" "libiconv.so")
libtool: link: ar cru .libs/libiconv.a  iconv.o localcharset.o relocatable.o
ar: `u' modifier ignored since `D' is the default (see `U')
libtool: link: ranlib .libs/libiconv.a
libtool: link: ( cd ".libs" && rm -f "libiconv.la" && ln -s "../libiconv.la" "libiconv.la" )
make[1]: ディレクトリ '/home/sak/libiconv-1.16/lib' から出ます
cd srclib && make all
make[1]: ディレクトリ '/home/sak/libiconv-1.16/srclib' に入ります
rm -f alloca.h-t alloca.h && \
{ echo '/* DO NOT EDIT! GENERATED AUTOMATICALLY! */'; \
  sed -e 's|@''HAVE_ALLOCA_H''@||g' < ./alloca.in.h; \
} > alloca.h-t && \
mv -f alloca.h-t alloca.h
rm -f fcntl.h-t fcntl.h && \
{ echo '/* DO NOT EDIT! GENERATED AUTOMATICALLY! */'; \
  sed -e 's|@''GUARD_PREFIX''@|GL|g' \
      -e 's|@''INCLUDE_NEXT''@|include_next|g' \
      -e 's|@''PRAGMA_SYSTEM_HEADER''@|#pragma GCC system_header|g' \
      -e 's|@''PRAGMA_COLUMNS''@||g' \
      -e 's|@''NEXT_FCNTL_H''@|<fcntl.h>|g' \
      -e 's/@''GNULIB_FCNTL''@/0/g' \
      -e 's/@''GNULIB_NONBLOCKING''@/0/g' \
      -e 's/@''GNULIB_OPEN''@/0/g' \
      -e 's/@''GNULIB_OPENAT''@/0/g' \
      -e 's|@''HAVE_FCNTL''@|1|g' \
      -e 's|@''HAVE_OPENAT''@|1|g' \
      -e 's|@''REPLACE_FCNTL''@|0|g' \
      -e 's|@''REPLACE_OPEN''@|0|g' \
      -e 's|@''REPLACE_OPENAT''@|0|g' \
      -e '/definitions of _GL_FUNCDECL_RPL/r ./c++defs.h' \
      -e '/definition of _GL_ARG_NONNULL/r ./arg-nonnull.h' \
      -e '/definition of _GL_WARN_ON_USE/r ./warn-on-use.h' \
      < ./fcntl.in.h; \
} > fcntl.h-t && \
mv fcntl.h-t fcntl.h
rm -f limits.h-t limits.h && \
{ echo '/* DO NOT EDIT! GENERATED AUTOMATICALLY! */' && \
  sed -e 's|@''GUARD_PREFIX''@|GL|g' \
      -e 's|@''INCLUDE_NEXT''@|include_next|g' \
      -e 's|@''PRAGMA_SYSTEM_HEADER''@|#pragma GCC system_header|g' \
      -e 's|@''PRAGMA_COLUMNS''@||g' \
      -e 's|@''NEXT_LIMITS_H''@|<limits.h>|g' \
      < ./limits.in.h; \
} > limits.h-t && \
mv limits.h-t limits.h
rm -f signal.h-t signal.h && \
{ echo '/* DO NOT EDIT! GENERATED AUTOMATICALLY! */' && \
  sed -e 's|@''GUARD_PREFIX''@|GL|g' \
      -e 's|@''INCLUDE_NEXT''@|include_next|g' \
      -e 's|@''PRAGMA_SYSTEM_HEADER''@|#pragma GCC system_header|g' \
      -e 's|@''PRAGMA_COLUMNS''@||g' \
      -e 's|@''NEXT_SIGNAL_H''@|<signal.h>|g' \
      -e 's/@''GNULIB_PTHREAD_SIGMASK''@/0/g' \
      -e 's/@''GNULIB_RAISE''@/1/g' \
      -e 's/@''GNULIB_SIGNAL_H_SIGPIPE''@/1/g' \
      -e 's/@''GNULIB_SIGPROCMASK''@/1/g' \
      -e 's/@''GNULIB_SIGACTION''@/0/g' \
      -e 's|@''HAVE_POSIX_SIGNALBLOCKING''@|1|g' \
      -e 's|@''HAVE_PTHREAD_SIGMASK''@|1|g' \
      -e 's|@''HAVE_RAISE''@|1|g' \
      -e 's|@''HAVE_SIGSET_T''@|1|g' \
      -e 's|@''HAVE_SIGINFO_T''@|1|g' \
      -e 's|@''HAVE_SIGACTION''@|1|g' \
      -e 's|@''HAVE_STRUCT_SIGACTION_SA_SIGACTION''@|1|g' \
      -e 's|@''HAVE_TYPE_VOLATILE_SIG_ATOMIC_T''@|1|g' \
      -e 's|@''HAVE_SIGHANDLER_T''@|1|g' \
      -e 's|@''REPLACE_PTHREAD_SIGMASK''@|0|g' \
      -e 's|@''REPLACE_RAISE''@|0|g' \
      -e '/definitions of _GL_FUNCDECL_RPL/r ./c++defs.h' \
      -e '/definition of _GL_ARG_NONNULL/r ./arg-nonnull.h' \
      -e '/definition of _GL_WARN_ON_USE/r ./warn-on-use.h' \
      < ./signal.in.h; \
} > signal.h-t && \
mv signal.h-t signal.h
rm -f stdio.h-t stdio.h && \
{ echo '/* DO NOT EDIT! GENERATED AUTOMATICALLY! */' && \
  sed -e 's|@''GUARD_PREFIX''@|GL|g' \
      -e 's|@''INCLUDE_NEXT''@|include_next|g' \
      -e 's|@''PRAGMA_SYSTEM_HEADER''@|#pragma GCC system_header|g' \
      -e 's|@''PRAGMA_COLUMNS''@||g' \
      -e 's|@''NEXT_STDIO_H''@|<stdio.h>|g' \
      -e 's/@''GNULIB_DPRINTF''@/0/g' \
      -e 's/@''GNULIB_FCLOSE''@/0/g' \
      -e 's/@''GNULIB_FDOPEN''@/0/g' \
      -e 's/@''GNULIB_FFLUSH''@/0/g' \
      -e 's/@''GNULIB_FGETC''@/1/g' \
      -e 's/@''GNULIB_FGETS''@/1/g' \
      -e 's/@''GNULIB_FOPEN''@/0/g' \
      -e 's/@''GNULIB_FPRINTF''@/1/g' \
      -e 's/@''GNULIB_FPRINTF_POSIX''@/0/g' \
      -e 's/@''GNULIB_FPURGE''@/0/g' \
      -e 's/@''GNULIB_FPUTC''@/1/g' \
      -e 's/@''GNULIB_FPUTS''@/1/g' \
      -e 's/@''GNULIB_FREAD''@/1/g' \
      -e 's/@''GNULIB_FREOPEN''@/0/g' \
      -e 's/@''GNULIB_FSCANF''@/1/g' \
      -e 's/@''GNULIB_FSEEK''@/0/g' \
      -e 's/@''GNULIB_FSEEKO''@/0/g' \
      -e 's/@''GNULIB_FTELL''@/0/g' \
      -e 's/@''GNULIB_FTELLO''@/0/g' \
      -e 's/@''GNULIB_FWRITE''@/1/g' \
      -e 's/@''GNULIB_GETC''@/1/g' \
      -e 's/@''GNULIB_GETCHAR''@/1/g' \
      -e 's/@''GNULIB_GETDELIM''@/0/g' \
      -e 's/@''GNULIB_GETLINE''@/0/g' \
      -e 's/@''GNULIB_OBSTACK_PRINTF''@/0/g' \
      -e 's/@''GNULIB_OBSTACK_PRINTF_POSIX''@/0/g' \
      -e 's/@''GNULIB_PCLOSE''@/0/g' \
      -e 's/@''GNULIB_PERROR''@/0/g' \
      -e 's/@''GNULIB_POPEN''@/0/g' \
      -e 's/@''GNULIB_PRINTF''@/1/g' \
      -e 's/@''GNULIB_PRINTF_POSIX''@/0/g' \
      -e 's/@''GNULIB_PUTC''@/1/g' \
      -e 's/@''GNULIB_PUTCHAR''@/1/g' \
      -e 's/@''GNULIB_PUTS''@/1/g' \
      -e 's/@''GNULIB_REMOVE''@/0/g' \
      -e 's/@''GNULIB_RENAME''@/0/g' \
      -e 's/@''GNULIB_RENAMEAT''@/0/g' \
      -e 's/@''GNULIB_SCANF''@/1/g' \
      -e 's/@''GNULIB_SNPRINTF''@/0/g' \
      -e 's/@''GNULIB_SPRINTF_POSIX''@/0/g' \
      -e 's/@''GNULIB_STDIO_H_NONBLOCKING''@/0/g' \
      -e 's/@''GNULIB_STDIO_H_SIGPIPE''@/1/g' \
      -e 's/@''GNULIB_TMPFILE''@/0/g' \
      -e 's/@''GNULIB_VASPRINTF''@/0/g' \
      -e 's/@''GNULIB_VDPRINTF''@/0/g' \
      -e 's/@''GNULIB_VFPRINTF''@/1/g' \
      -e 's/@''GNULIB_VFPRINTF_POSIX''@/0/g' \
      -e 's/@''GNULIB_VFSCANF''@/0/g' \
      -e 's/@''GNULIB_VSCANF''@/0/g' \
      -e 's/@''GNULIB_VPRINTF''@/1/g' \
      -e 's/@''GNULIB_VPRINTF_POSIX''@/0/g' \
      -e 's/@''GNULIB_VSNPRINTF''@/0/g' \
      -e 's/@''GNULIB_VSPRINTF_POSIX''@/0/g' \
      < ./stdio.in.h | \
  sed -e 's|@''HAVE_DECL_FPURGE''@|1|g' \
      -e 's|@''HAVE_DECL_FSEEKO''@|1|g' \
      -e 's|@''HAVE_DECL_FTELLO''@|1|g' \
      -e 's|@''HAVE_DECL_GETDELIM''@|1|g' \
      -e 's|@''HAVE_DECL_GETLINE''@|1|g' \
      -e 's|@''HAVE_DECL_OBSTACK_PRINTF''@|1|g' \
      -e 's|@''HAVE_DECL_SNPRINTF''@|1|g' \
      -e 's|@''HAVE_DECL_VSNPRINTF''@|1|g' \
      -e 's|@''HAVE_DPRINTF''@|1|g' \
      -e 's|@''HAVE_FSEEKO''@|1|g' \
      -e 's|@''HAVE_FTELLO''@|1|g' \
      -e 's|@''HAVE_PCLOSE''@|1|g' \
      -e 's|@''HAVE_POPEN''@|1|g' \
      -e 's|@''HAVE_RENAMEAT''@|1|g' \
      -e 's|@''HAVE_VASPRINTF''@|1|g' \
      -e 's|@''HAVE_VDPRINTF''@|1|g' \
      -e 's|@''REPLACE_DPRINTF''@|0|g' \
      -e 's|@''REPLACE_FCLOSE''@|0|g' \
      -e 's|@''REPLACE_FDOPEN''@|0|g' \
      -e 's|@''REPLACE_FFLUSH''@|0|g' \
      -e 's|@''REPLACE_FOPEN''@|0|g' \
      -e 's|@''REPLACE_FPRINTF''@|0|g' \
      -e 's|@''REPLACE_FPURGE''@|0|g' \
      -e 's|@''REPLACE_FREOPEN''@|0|g' \
      -e 's|@''REPLACE_FSEEK''@|0|g' \
      -e 's|@''REPLACE_FSEEKO''@|0|g' \
      -e 's|@''REPLACE_FTELL''@|0|g' \
      -e 's|@''REPLACE_FTELLO''@|0|g' \
      -e 's|@''REPLACE_GETDELIM''@|0|g' \
      -e 's|@''REPLACE_GETLINE''@|0|g' \
      -e 's|@''REPLACE_OBSTACK_PRINTF''@|0|g' \
      -e 's|@''REPLACE_PERROR''@|0|g' \
      -e 's|@''REPLACE_POPEN''@|0|g' \
      -e 's|@''REPLACE_PRINTF''@|0|g' \
      -e 's|@''REPLACE_REMOVE''@|0|g' \
      -e 's|@''REPLACE_RENAME''@|0|g' \
      -e 's|@''REPLACE_RENAMEAT''@|0|g' \
      -e 's|@''REPLACE_SNPRINTF''@|0|g' \
      -e 's|@''REPLACE_SPRINTF''@|0|g' \
      -e 's|@''REPLACE_STDIO_READ_FUNCS''@|0|g' \
      -e 's|@''REPLACE_STDIO_WRITE_FUNCS''@|0|g' \
      -e 's|@''REPLACE_TMPFILE''@|0|g' \
      -e 's|@''REPLACE_VASPRINTF''@|0|g' \
      -e 's|@''REPLACE_VDPRINTF''@|0|g' \
      -e 's|@''REPLACE_VFPRINTF''@|0|g' \
      -e 's|@''REPLACE_VPRINTF''@|0|g' \
      -e 's|@''REPLACE_VSNPRINTF''@|0|g' \
      -e 's|@''REPLACE_VSPRINTF''@|0|g' \
      -e 's|@''ASM_SYMBOL_PREFIX''@|""|g' \
      -e '/definitions of _GL_FUNCDECL_RPL/r ./c++defs.h' \
      -e '/definition of _GL_ARG_NONNULL/r ./arg-nonnull.h' \
      -e '/definition of _GL_WARN_ON_USE/r ./warn-on-use.h'; \
} > stdio.h-t && \
mv stdio.h-t stdio.h
rm -f stdlib.h-t stdlib.h && \
{ echo '/* DO NOT EDIT! GENERATED AUTOMATICALLY! */' && \
  sed -e 's|@''GUARD_PREFIX''@|GL|g' \
      -e 's|@''INCLUDE_NEXT''@|include_next|g' \
      -e 's|@''PRAGMA_SYSTEM_HEADER''@|#pragma GCC system_header|g' \
      -e 's|@''PRAGMA_COLUMNS''@||g' \
      -e 's|@''NEXT_STDLIB_H''@|<stdlib.h>|g' \
      -e 's/@''GNULIB__EXIT''@/0/g' \
      -e 's/@''GNULIB_ATOLL''@/0/g' \
      -e 's/@''GNULIB_CALLOC_POSIX''@/0/g' \
      -e 's/@''GNULIB_CANONICALIZE_FILE_NAME''@/1/g' \
      -e 's/@''GNULIB_GETLOADAVG''@/0/g' \
      -e 's/@''GNULIB_GETSUBOPT''@/0/g' \
      -e 's/@''GNULIB_GRANTPT''@/0/g' \
      -e 's/@''GNULIB_MALLOC_POSIX''@/1/g' \
      -e 's/@''GNULIB_MBTOWC''@/0/g' \
      -e 's/@''GNULIB_MKDTEMP''@/0/g' \
      -e 's/@''GNULIB_MKOSTEMP''@/0/g' \
      -e 's/@''GNULIB_MKOSTEMPS''@/0/g' \
      -e 's/@''GNULIB_MKSTEMP''@/0/g' \
      -e 's/@''GNULIB_MKSTEMPS''@/0/g' \
      -e 's/@''GNULIB_POSIX_OPENPT''@/0/g' \
      -e 's/@''GNULIB_PTSNAME''@/0/g' \
      -e 's/@''GNULIB_PTSNAME_R''@/0/g' \
      -e 's/@''GNULIB_PUTENV''@/0/g' \
      -e 's/@''GNULIB_QSORT_R''@/0/g' \
      -e 's/@''GNULIB_RANDOM''@/0/g' \
      -e 's/@''GNULIB_RANDOM_R''@/0/g' \
      -e 's/@''GNULIB_REALLOC_POSIX''@/0/g' \
      -e 's/@''GNULIB_REALLOCARRAY''@/0/g' \
      -e 's/@''GNULIB_REALPATH''@/1/g' \
      -e 's/@''GNULIB_RPMATCH''@/0/g' \
      -e 's/@''GNULIB_SECURE_GETENV''@/0/g' \
      -e 's/@''GNULIB_SETENV''@/0/g' \
      -e 's/@''GNULIB_STRTOD''@/0/g' \
      -e 's/@''GNULIB_STRTOLD''@/0/g' \
      -e 's/@''GNULIB_STRTOLL''@/0/g' \
      -e 's/@''GNULIB_STRTOULL''@/0/g' \
      -e 's/@''GNULIB_SYSTEM_POSIX''@/0/g' \
      -e 's/@''GNULIB_UNLOCKPT''@/0/g' \
      -e 's/@''GNULIB_UNSETENV''@/0/g' \
      -e 's/@''GNULIB_WCTOMB''@/0/g' \
      < ./stdlib.in.h | \
  sed -e 's|@''HAVE__EXIT''@|1|g' \
      -e 's|@''HAVE_ATOLL''@|1|g' \
      -e 's|@''HAVE_CANONICALIZE_FILE_NAME''@|1|g' \
      -e 's|@''HAVE_DECL_GETLOADAVG''@|1|g' \
      -e 's|@''HAVE_GETSUBOPT''@|1|g' \
      -e 's|@''HAVE_GRANTPT''@|1|g' \
      -e 's|@''HAVE_INITSTATE''@|1|g' \
      -e 's|@''HAVE_DECL_INITSTATE''@|1|g' \
      -e 's|@''HAVE_MBTOWC''@|1|g' \
      -e 's|@''HAVE_MKDTEMP''@|1|g' \
      -e 's|@''HAVE_MKOSTEMP''@|1|g' \
      -e 's|@''HAVE_MKOSTEMPS''@|1|g' \
      -e 's|@''HAVE_MKSTEMP''@|1|g' \
      -e 's|@''HAVE_MKSTEMPS''@|1|g' \
      -e 's|@''HAVE_POSIX_OPENPT''@|1|g' \
      -e 's|@''HAVE_PTSNAME''@|1|g' \
      -e 's|@''HAVE_PTSNAME_R''@|1|g' \
      -e 's|@''HAVE_QSORT_R''@|1|g' \
      -e 's|@''HAVE_RANDOM''@|1|g' \
      -e 's|@''HAVE_RANDOM_H''@|1|g' \
      -e 's|@''HAVE_RANDOM_R''@|1|g' \
      -e 's|@''HAVE_REALLOCARRAY''@|1|g' \
      -e 's|@''HAVE_REALPATH''@|1|g' \
      -e 's|@''HAVE_RPMATCH''@|1|g' \
      -e 's|@''HAVE_SECURE_GETENV''@|1|g' \
      -e 's|@''HAVE_DECL_SETENV''@|1|g' \
      -e 's|@''HAVE_SETSTATE''@|1|g' \
      -e 's|@''HAVE_DECL_SETSTATE''@|1|g' \
      -e 's|@''HAVE_STRTOD''@|1|g' \
      -e 's|@''HAVE_STRTOLD''@|1|g' \
      -e 's|@''HAVE_STRTOLL''@|1|g' \
      -e 's|@''HAVE_STRTOULL''@|1|g' \
      -e 's|@''HAVE_STRUCT_RANDOM_DATA''@|1|g' \
      -e 's|@''HAVE_SYS_LOADAVG_H''@|0|g' \
      -e 's|@''HAVE_UNLOCKPT''@|1|g' \
      -e 's|@''HAVE_DECL_UNSETENV''@|1|g' \
      -e 's|@''REPLACE_CALLOC''@|0|g' \
      -e 's|@''REPLACE_CANONICALIZE_FILE_NAME''@|0|g' \
      -e 's|@''REPLACE_INITSTATE''@|0|g' \
      -e 's|@''REPLACE_MALLOC''@|0|g' \
      -e 's|@''REPLACE_MBTOWC''@|0|g' \
      -e 's|@''REPLACE_MKSTEMP''@|0|g' \
      -e 's|@''REPLACE_PTSNAME''@|0|g' \
      -e 's|@''REPLACE_PTSNAME_R''@|0|g' \
      -e 's|@''REPLACE_PUTENV''@|0|g' \
      -e 's|@''REPLACE_QSORT_R''@|0|g' \
      -e 's|@''REPLACE_RANDOM''@|0|g' \
      -e 's|@''REPLACE_RANDOM_R''@|0|g' \
      -e 's|@''REPLACE_REALLOC''@|0|g' \
      -e 's|@''REPLACE_REALPATH''@|0|g' \
      -e 's|@''REPLACE_SETENV''@|0|g' \
      -e 's|@''REPLACE_SETSTATE''@|0|g' \
      -e 's|@''REPLACE_STRTOD''@|0|g' \
      -e 's|@''REPLACE_STRTOLD''@|0|g' \
      -e 's|@''REPLACE_UNSETENV''@|0|g' \
      -e 's|@''REPLACE_WCTOMB''@|0|g' \
      -e '/definitions of _GL_FUNCDECL_RPL/r ./c++defs.h' \
      -e '/definition of _Noreturn/r ./_Noreturn.h' \
      -e '/definition of _GL_ARG_NONNULL/r ./arg-nonnull.h' \
      -e '/definition of _GL_WARN_ON_USE/r ./warn-on-use.h'; \
} > stdlib.h-t && \
mv stdlib.h-t stdlib.h
rm -f string.h-t string.h && \
{ echo '/* DO NOT EDIT! GENERATED AUTOMATICALLY! */' && \
  sed -e 's|@''GUARD_PREFIX''@|GL|g' \
      -e 's|@''INCLUDE_NEXT''@|include_next|g' \
      -e 's|@''PRAGMA_SYSTEM_HEADER''@|#pragma GCC system_header|g' \
      -e 's|@''PRAGMA_COLUMNS''@||g' \
      -e 's|@''NEXT_STRING_H''@|<string.h>|g' \
      -e 's/@''GNULIB_EXPLICIT_BZERO''@/0/g' \
      -e 's/@''GNULIB_FFSL''@/0/g' \
      -e 's/@''GNULIB_FFSLL''@/0/g' \
      -e 's/@''GNULIB_MBSLEN''@/0/g' \
      -e 's/@''GNULIB_MBSNLEN''@/0/g' \
      -e 's/@''GNULIB_MBSCHR''@/0/g' \
      -e 's/@''GNULIB_MBSRCHR''@/0/g' \
      -e 's/@''GNULIB_MBSSTR''@/0/g' \
      -e 's/@''GNULIB_MBSCASECMP''@/0/g' \
      -e 's/@''GNULIB_MBSNCASECMP''@/0/g' \
      -e 's/@''GNULIB_MBSPCASECMP''@/0/g' \
      -e 's/@''GNULIB_MBSCASESTR''@/0/g' \
      -e 's/@''GNULIB_MBSCSPN''@/0/g' \
      -e 's/@''GNULIB_MBSPBRK''@/0/g' \
      -e 's/@''GNULIB_MBSSPN''@/0/g' \
      -e 's/@''GNULIB_MBSSEP''@/0/g' \
      -e 's/@''GNULIB_MBSTOK_R''@/0/g' \
      -e 's/@''GNULIB_MEMCHR''@/0/g' \
      -e 's/@''GNULIB_MEMMEM''@/0/g' \
      -e 's/@''GNULIB_MEMPCPY''@/0/g' \
      -e 's/@''GNULIB_MEMRCHR''@/0/g' \
      -e 's/@''GNULIB_RAWMEMCHR''@/0/g' \
      -e 's/@''GNULIB_STPCPY''@/0/g' \
      -e 's/@''GNULIB_STPNCPY''@/0/g' \
      -e 's/@''GNULIB_STRCHRNUL''@/0/g' \
      -e 's/@''GNULIB_STRDUP''@/0/g' \
      -e 's/@''GNULIB_STRNCAT''@/0/g' \
      -e 's/@''GNULIB_STRNDUP''@/0/g' \
      -e 's/@''GNULIB_STRNLEN''@/0/g' \
      -e 's/@''GNULIB_STRPBRK''@/0/g' \
      -e 's/@''GNULIB_STRSEP''@/0/g' \
      -e 's/@''GNULIB_STRSTR''@/0/g' \
      -e 's/@''GNULIB_STRCASESTR''@/0/g' \
      -e 's/@''GNULIB_STRTOK_R''@/0/g' \
      -e 's/@''GNULIB_STRERROR''@/1/g' \
      -e 's/@''GNULIB_STRERROR_R''@/0/g' \
      -e 's/@''GNULIB_STRSIGNAL''@/0/g' \
      -e 's/@''GNULIB_STRVERSCMP''@/0/g' \
      < ./string.in.h | \
  sed -e 's|@''HAVE_EXPLICIT_BZERO''@|1|g' \
      -e 's|@''HAVE_FFSL''@|1|g' \
      -e 's|@''HAVE_FFSLL''@|1|g' \
      -e 's|@''HAVE_MBSLEN''@|0|g' \
      -e 's|@''HAVE_MEMCHR''@|1|g' \
      -e 's|@''HAVE_DECL_MEMMEM''@|1|g' \
      -e 's|@''HAVE_MEMPCPY''@|1|g' \
      -e 's|@''HAVE_DECL_MEMRCHR''@|1|g' \
      -e 's|@''HAVE_RAWMEMCHR''@|1|g' \
      -e 's|@''HAVE_STPCPY''@|1|g' \
      -e 's|@''HAVE_STPNCPY''@|1|g' \
      -e 's|@''HAVE_STRCHRNUL''@|1|g' \
      -e 's|@''HAVE_DECL_STRDUP''@|1|g' \
      -e 's|@''HAVE_DECL_STRNDUP''@|1|g' \
      -e 's|@''HAVE_DECL_STRNLEN''@|1|g' \
      -e 's|@''HAVE_STRPBRK''@|1|g' \
      -e 's|@''HAVE_STRSEP''@|1|g' \
      -e 's|@''HAVE_STRCASESTR''@|1|g' \
      -e 's|@''HAVE_DECL_STRTOK_R''@|1|g' \
      -e 's|@''HAVE_DECL_STRERROR_R''@|1|g' \
      -e 's|@''HAVE_DECL_STRSIGNAL''@|1|g' \
      -e 's|@''HAVE_STRVERSCMP''@|1|g' \
      -e 's|@''REPLACE_MEMCHR''@|0|g' \
      -e 's|@''REPLACE_MEMMEM''@|0|g' \
      -e 's|@''REPLACE_STPNCPY''@|0|g' \
      -e 's|@''REPLACE_STRCHRNUL''@|0|g' \
      -e 's|@''REPLACE_STRDUP''@|0|g' \
      -e 's|@''REPLACE_STRNCAT''@|0|g' \
      -e 's|@''REPLACE_STRNDUP''@|0|g' \
      -e 's|@''REPLACE_STRNLEN''@|0|g' \
      -e 's|@''REPLACE_STRSTR''@|0|g' \
      -e 's|@''REPLACE_STRCASESTR''@|0|g' \
      -e 's|@''REPLACE_STRTOK_R''@|0|g' \
      -e 's|@''REPLACE_STRERROR''@|0|g' \
      -e 's|@''REPLACE_STRERROR_R''@|0|g' \
      -e 's|@''REPLACE_STRSIGNAL''@|0|g' \
      -e 's|@''UNDEFINE_STRTOK_R''@|0|g' \
      -e '/definitions of _GL_FUNCDECL_RPL/r ./c++defs.h' \
      -e '/definition of _GL_ARG_NONNULL/r ./arg-nonnull.h' \
      -e '/definition of _GL_WARN_ON_USE/r ./warn-on-use.h'; \
      < ./string.in.h; \
} > string.h-t && \
mv string.h-t string.h
/bin/mkdir -p sys
rm -f sys/stat.h-t sys/stat.h && \
{ echo '/* DO NOT EDIT! GENERATED AUTOMATICALLY! */'; \
  sed -e 's|@''GUARD_PREFIX''@|GL|g' \
      -e 's|@''INCLUDE_NEXT''@|include_next|g' \
      -e 's|@''PRAGMA_SYSTEM_HEADER''@|#pragma GCC system_header|g' \
      -e 's|@''PRAGMA_COLUMNS''@||g' \
      -e 's|@''NEXT_SYS_STAT_H''@|<sys/stat.h>|g' \
      -e 's|@''WINDOWS_64_BIT_ST_SIZE''@|0|g' \
      -e 's|@''WINDOWS_STAT_TIMESPEC''@|0|g' \
      -e 's/@''GNULIB_FCHMODAT''@/0/g' \
      -e 's/@''GNULIB_FSTAT''@/0/g' \
      -e 's/@''GNULIB_FSTATAT''@/0/g' \
      -e 's/@''GNULIB_FUTIMENS''@/0/g' \
      -e 's/@''GNULIB_LCHMOD''@/0/g' \
      -e 's/@''GNULIB_LSTAT''@/1/g' \
      -e 's/@''GNULIB_MKDIRAT''@/0/g' \
      -e 's/@''GNULIB_MKFIFO''@/0/g' \
      -e 's/@''GNULIB_MKFIFOAT''@/0/g' \
      -e 's/@''GNULIB_MKNOD''@/0/g' \
      -e 's/@''GNULIB_MKNODAT''@/0/g' \
      -e 's/@''GNULIB_STAT''@/1/g' \
      -e 's/@''GNULIB_UTIMENSAT''@/0/g' \
      -e 's/@''GNULIB_OVERRIDES_STRUCT_STAT''@/0/g' \
      -e 's|@''HAVE_FCHMODAT''@|1|g' \
      -e 's|@''HAVE_FSTATAT''@|1|g' \
      -e 's|@''HAVE_FUTIMENS''@|1|g' \
      -e 's|@''HAVE_LCHMOD''@|1|g' \
      -e 's|@''HAVE_LSTAT''@|1|g' \
      -e 's|@''HAVE_MKDIRAT''@|1|g' \
      -e 's|@''HAVE_MKFIFO''@|1|g' \
      -e 's|@''HAVE_MKFIFOAT''@|1|g' \
      -e 's|@''HAVE_MKNOD''@|1|g' \
      -e 's|@''HAVE_MKNODAT''@|1|g' \
      -e 's|@''HAVE_UTIMENSAT''@|1|g' \
      -e 's|@''REPLACE_FSTAT''@|0|g' \
      -e 's|@''REPLACE_FSTATAT''@|0|g' \
      -e 's|@''REPLACE_FUTIMENS''@|0|g' \
      -e 's|@''REPLACE_LSTAT''@|0|g' \
      -e 's|@''REPLACE_MKDIR''@|0|g' \
      -e 's|@''REPLACE_MKFIFO''@|0|g' \
      -e 's|@''REPLACE_MKNOD''@|0|g' \
      -e 's|@''REPLACE_STAT''@|0|g' \
      -e 's|@''REPLACE_UTIMENSAT''@|0|g' \
      -e '/definitions of _GL_FUNCDECL_RPL/r ./c++defs.h' \
      -e '/definition of _GL_ARG_NONNULL/r ./arg-nonnull.h' \
      -e '/definition of _GL_WARN_ON_USE/r ./warn-on-use.h' \
      < ./sys_stat.in.h; \
} > sys/stat.h-t && \
mv sys/stat.h-t sys/stat.h
/bin/mkdir -p sys
rm -f sys/types.h-t sys/types.h && \
{ echo '/* DO NOT EDIT! GENERATED AUTOMATICALLY! */'; \
  sed -e 's|@''GUARD_PREFIX''@|GL|g' \
      -e 's|@''INCLUDE_NEXT''@|include_next|g' \
      -e 's|@''PRAGMA_SYSTEM_HEADER''@|#pragma GCC system_header|g' \
      -e 's|@''PRAGMA_COLUMNS''@||g' \
      -e 's|@''NEXT_SYS_TYPES_H''@|<sys/types.h>|g' \
      -e 's|@''WINDOWS_64_BIT_OFF_T''@|0|g' \
      -e 's|@''WINDOWS_STAT_INODES''@|0|g' \
      < ./sys_types.in.h; \
} > sys/types.h-t && \
mv sys/types.h-t sys/types.h
rm -f time.h-t time.h && \
{ echo '/* DO NOT EDIT! GENERATED AUTOMATICALLY! */' && \
  sed -e 's|@''GUARD_PREFIX''@|GL|g' \
      -e 's|@''INCLUDE_NEXT''@|include_next|g' \
      -e 's|@''PRAGMA_SYSTEM_HEADER''@|#pragma GCC system_header|g' \
      -e 's|@''PRAGMA_COLUMNS''@||g' \
      -e 's|@''NEXT_TIME_H''@|<time.h>|g' \
      -e 's/@''GNULIB_CTIME''@/0/g' \
      -e 's/@''GNULIB_LOCALTIME''@/0/g' \
      -e 's/@''GNULIB_MKTIME''@/0/g' \
      -e 's/@''GNULIB_NANOSLEEP''@/0/g' \
      -e 's/@''GNULIB_STRFTIME''@/0/g' \
      -e 's/@''GNULIB_STRPTIME''@/0/g' \
      -e 's/@''GNULIB_TIMEGM''@/0/g' \
      -e 's/@''GNULIB_TIME_R''@/0/g' \
      -e 's/@''GNULIB_TIME_RZ''@/0/g' \
      -e 's/@''GNULIB_TZSET''@/0/g' \
      -e 's|@''HAVE_DECL_LOCALTIME_R''@|1|g' \
      -e 's|@''HAVE_NANOSLEEP''@|1|g' \
      -e 's|@''HAVE_STRPTIME''@|1|g' \
      -e 's|@''HAVE_TIMEGM''@|1|g' \
      -e 's|@''HAVE_TIMEZONE_T''@|0|g' \
      -e 's|@''HAVE_TZSET''@|1|g' \
      -e 's|@''REPLACE_CTIME''@|GNULIB_PORTCHECK|g' \
      -e 's|@''REPLACE_GMTIME''@|0|g' \
      -e 's|@''REPLACE_LOCALTIME''@|0|g' \
      -e 's|@''REPLACE_LOCALTIME_R''@|GNULIB_PORTCHECK|g' \
      -e 's|@''REPLACE_MKTIME''@|GNULIB_PORTCHECK|g' \
      -e 's|@''REPLACE_NANOSLEEP''@|GNULIB_PORTCHECK|g' \
      -e 's|@''REPLACE_STRFTIME''@|GNULIB_PORTCHECK|g' \
      -e 's|@''REPLACE_TIMEGM''@|GNULIB_PORTCHECK|g' \
      -e 's|@''REPLACE_TZSET''@|GNULIB_PORTCHECK|g' \
      -e 's|@''PTHREAD_H_DEFINES_STRUCT_TIMESPEC''@|0|g' \
      -e 's|@''SYS_TIME_H_DEFINES_STRUCT_TIMESPEC''@|0|g' \
      -e 's|@''TIME_H_DEFINES_STRUCT_TIMESPEC''@|1|g' \
      -e 's|@''UNISTD_H_DEFINES_STRUCT_TIMESPEC''@|0|g' \
      -e '/definitions of _GL_FUNCDECL_RPL/r ./c++defs.h' \
      -e '/definition of _GL_ARG_NONNULL/r ./arg-nonnull.h' \
      -e '/definition of _GL_WARN_ON_USE/r ./warn-on-use.h' \
      < ./time.in.h; \
} > time.h-t && \
mv time.h-t time.h
rm -f unistd.h-t unistd.h && \
{ echo '/* DO NOT EDIT! GENERATED AUTOMATICALLY! */'; \
  sed -e 's|@''GUARD_PREFIX''@|GL|g' \
      -e 's|@''HAVE_UNISTD_H''@|1|g' \
      -e 's|@''INCLUDE_NEXT''@|include_next|g' \
      -e 's|@''PRAGMA_SYSTEM_HEADER''@|#pragma GCC system_header|g' \
      -e 's|@''PRAGMA_COLUMNS''@||g' \
      -e 's|@''NEXT_UNISTD_H''@|<unistd.h>|g' \
      -e 's|@''WINDOWS_64_BIT_OFF_T''@|0|g' \
      -e 's/@''GNULIB_CHDIR''@/0/g' \
      -e 's/@''GNULIB_CHOWN''@/0/g' \
      -e 's/@''GNULIB_CLOSE''@/0/g' \
      -e 's/@''GNULIB_DUP''@/0/g' \
      -e 's/@''GNULIB_DUP2''@/0/g' \
      -e 's/@''GNULIB_DUP3''@/0/g' \
      -e 's/@''GNULIB_ENVIRON''@/1/g' \
      -e 's/@''GNULIB_EUIDACCESS''@/0/g' \
      -e 's/@''GNULIB_FACCESSAT''@/0/g' \
      -e 's/@''GNULIB_FCHDIR''@/0/g' \
      -e 's/@''GNULIB_FCHOWNAT''@/0/g' \
      -e 's/@''GNULIB_FDATASYNC''@/0/g' \
      -e 's/@''GNULIB_FSYNC''@/0/g' \
      -e 's/@''GNULIB_FTRUNCATE''@/0/g' \
      -e 's/@''GNULIB_GETCWD''@/0/g' \
      -e 's/@''GNULIB_GETDOMAINNAME''@/0/g' \
      -e 's/@''GNULIB_GETDTABLESIZE''@/0/g' \
      -e 's/@''GNULIB_GETGROUPS''@/0/g' \
      -e 's/@''GNULIB_GETHOSTNAME''@/0/g' \
      -e 's/@''GNULIB_GETLOGIN''@/0/g' \
      -e 's/@''GNULIB_GETLOGIN_R''@/0/g' \
      -e 's/@''GNULIB_GETPAGESIZE''@/0/g' \
      -e 's/@''GNULIB_GETPASS''@/0/g' \
      -e 's/@''GNULIB_GETUSERSHELL''@/0/g' \
      -e 's/@''GNULIB_GROUP_MEMBER''@/0/g' \
      -e 's/@''GNULIB_ISATTY''@/0/g' \
      -e 's/@''GNULIB_LCHOWN''@/0/g' \
      -e 's/@''GNULIB_LINK''@/0/g' \
      -e 's/@''GNULIB_LINKAT''@/0/g' \
      -e 's/@''GNULIB_LSEEK''@/0/g' \
      -e 's/@''GNULIB_PIPE''@/0/g' \
      -e 's/@''GNULIB_PIPE2''@/0/g' \
      -e 's/@''GNULIB_PREAD''@/0/g' \
      -e 's/@''GNULIB_PWRITE''@/0/g' \
      -e 's/@''GNULIB_READ''@/1/g' \
      -e 's/@''GNULIB_READLINK''@/1/g' \
      -e 's/@''GNULIB_READLINKAT''@/0/g' \
      -e 's/@''GNULIB_RMDIR''@/0/g' \
      -e 's/@''GNULIB_SETHOSTNAME''@/0/g' \
      -e 's/@''GNULIB_SLEEP''@/0/g' \
      -e 's/@''GNULIB_SYMLINK''@/0/g' \
      -e 's/@''GNULIB_SYMLINKAT''@/0/g' \
      -e 's/@''GNULIB_TRUNCATE''@/0/g' \
      -e 's/@''GNULIB_TTYNAME_R''@/0/g' \
      -e 's/@''GNULIB_UNISTD_H_GETOPT''@/0/g' \
      -e 's/@''GNULIB_UNISTD_H_NONBLOCKING''@/0/g' \
      -e 's/@''GNULIB_UNISTD_H_SIGPIPE''@/1/g' \
      -e 's/@''GNULIB_UNLINK''@/0/g' \
      -e 's/@''GNULIB_UNLINKAT''@/0/g' \
      -e 's/@''GNULIB_USLEEP''@/0/g' \
      -e 's/@''GNULIB_WRITE''@/0/g' \
      < ./unistd.in.h | \
  sed -e 's|@''HAVE_CHOWN''@|1|g' \
      -e 's|@''HAVE_DUP2''@|1|g' \
      -e 's|@''HAVE_DUP3''@|1|g' \
      -e 's|@''HAVE_EUIDACCESS''@|1|g' \
      -e 's|@''HAVE_FACCESSAT''@|1|g' \
      -e 's|@''HAVE_FCHDIR''@|1|g' \
      -e 's|@''HAVE_FCHOWNAT''@|1|g' \
      -e 's|@''HAVE_FDATASYNC''@|1|g' \
      -e 's|@''HAVE_FSYNC''@|1|g' \
      -e 's|@''HAVE_FTRUNCATE''@|1|g' \
      -e 's|@''HAVE_GETDTABLESIZE''@|1|g' \
      -e 's|@''HAVE_GETGROUPS''@|1|g' \
      -e 's|@''HAVE_GETHOSTNAME''@|1|g' \
      -e 's|@''HAVE_GETPAGESIZE''@|1|g' \
      -e 's|@''HAVE_GETPASS''@|1|g' \
      -e 's|@''HAVE_GROUP_MEMBER''@|1|g' \
      -e 's|@''HAVE_LCHOWN''@|1|g' \
      -e 's|@''HAVE_LINK''@|1|g' \
      -e 's|@''HAVE_LINKAT''@|1|g' \
      -e 's|@''HAVE_PIPE''@|1|g' \
      -e 's|@''HAVE_PIPE2''@|1|g' \
      -e 's|@''HAVE_PREAD''@|1|g' \
      -e 's|@''HAVE_PWRITE''@|1|g' \
      -e 's|@''HAVE_READLINK''@|1|g' \
      -e 's|@''HAVE_READLINKAT''@|1|g' \
      -e 's|@''HAVE_SETHOSTNAME''@|1|g' \
      -e 's|@''HAVE_SLEEP''@|1|g' \
      -e 's|@''HAVE_SYMLINK''@|1|g' \
      -e 's|@''HAVE_SYMLINKAT''@|1|g' \
      -e 's|@''HAVE_UNLINKAT''@|1|g' \
      -e 's|@''HAVE_USLEEP''@|1|g' \
      -e 's|@''HAVE_DECL_ENVIRON''@|1|g' \
      -e 's|@''HAVE_DECL_FCHDIR''@|1|g' \
      -e 's|@''HAVE_DECL_FDATASYNC''@|1|g' \
      -e 's|@''HAVE_DECL_GETDOMAINNAME''@|1|g' \
      -e 's|@''HAVE_DECL_GETLOGIN''@|1|g' \
      -e 's|@''HAVE_DECL_GETLOGIN_R''@|1|g' \
      -e 's|@''HAVE_DECL_GETPAGESIZE''@|1|g' \
      -e 's|@''HAVE_DECL_GETUSERSHELL''@|1|g' \
      -e 's|@''HAVE_DECL_SETHOSTNAME''@|1|g' \
      -e 's|@''HAVE_DECL_TRUNCATE''@|1|g' \
      -e 's|@''HAVE_DECL_TTYNAME_R''@|1|g' \
      -e 's|@''HAVE_OS_H''@|0|g' \
      -e 's|@''HAVE_SYS_PARAM_H''@|0|g' \
  | \
  sed -e 's|@''REPLACE_CHOWN''@|0|g' \
      -e 's|@''REPLACE_CLOSE''@|0|g' \
      -e 's|@''REPLACE_DUP''@|0|g' \
      -e 's|@''REPLACE_DUP2''@|0|g' \
      -e 's|@''REPLACE_FACCESSAT''@|0|g' \
      -e 's|@''REPLACE_FCHOWNAT''@|0|g' \
      -e 's|@''REPLACE_FTRUNCATE''@|0|g' \
      -e 's|@''REPLACE_GETCWD''@|0|g' \
      -e 's|@''REPLACE_GETDOMAINNAME''@|0|g' \
      -e 's|@''REPLACE_GETDTABLESIZE''@|0|g' \
      -e 's|@''REPLACE_GETLOGIN_R''@|0|g' \
      -e 's|@''REPLACE_GETGROUPS''@|0|g' \
      -e 's|@''REPLACE_GETPAGESIZE''@|0|g' \
      -e 's|@''REPLACE_GETPASS''@|0|g' \
      -e 's|@''REPLACE_ISATTY''@|0|g' \
      -e 's|@''REPLACE_LCHOWN''@|0|g' \
      -e 's|@''REPLACE_LINK''@|0|g' \
      -e 's|@''REPLACE_LINKAT''@|0|g' \
      -e 's|@''REPLACE_LSEEK''@|0|g' \
      -e 's|@''REPLACE_PREAD''@|0|g' \
      -e 's|@''REPLACE_PWRITE''@|0|g' \
      -e 's|@''REPLACE_READ''@|0|g' \
      -e 's|@''REPLACE_READLINK''@|0|g' \
      -e 's|@''REPLACE_READLINKAT''@|0|g' \
      -e 's|@''REPLACE_RMDIR''@|0|g' \
      -e 's|@''REPLACE_SLEEP''@|0|g' \
      -e 's|@''REPLACE_SYMLINK''@|0|g' \
      -e 's|@''REPLACE_SYMLINKAT''@|0|g' \
      -e 's|@''REPLACE_TRUNCATE''@|0|g' \
      -e 's|@''REPLACE_TTYNAME_R''@|0|g' \
      -e 's|@''REPLACE_UNLINK''@|0|g' \
      -e 's|@''REPLACE_UNLINKAT''@|0|g' \
      -e 's|@''REPLACE_USLEEP''@|0|g' \
      -e 's|@''REPLACE_WRITE''@|0|g' \
      -e 's|@''UNISTD_H_HAVE_WINSOCK2_H''@|0|g' \
      -e 's|@''UNISTD_H_HAVE_WINSOCK2_H_AND_USE_SOCKETS''@|0|g' \
      -e '/definitions of _GL_FUNCDECL_RPL/r ./c++defs.h' \
      -e '/definition of _GL_ARG_NONNULL/r ./arg-nonnull.h' \
      -e '/definition of _GL_WARN_ON_USE/r ./warn-on-use.h'; \
} > unistd.h-t && \
mv unistd.h-t unistd.h
rm -f unitypes.h-t unitypes.h && \
{ echo '/* DO NOT EDIT! GENERATED AUTOMATICALLY! */'; \
  cat ./unitypes.in.h; \
} > unitypes.h-t && \
mv -f unitypes.h-t unitypes.h
rm -f uniwidth.h-t uniwidth.h && \
{ echo '/* DO NOT EDIT! GENERATED AUTOMATICALLY! */'; \
  cat ./uniwidth.in.h; \
} > uniwidth.h-t && \
mv -f uniwidth.h-t uniwidth.h
make  all-am
make[2]: ディレクトリ '/home/sak/libiconv-1.16/srclib' に入ります
gcc -DHAVE_CONFIG_H -DEXEEXT=\"\" -I. -I.. -I../lib  -DDEPENDS_ON_LIBICONV=1 -DDEPENDS_ON_LIBINTL=1   -g -O2 -c -o allocator.o allocator.c
gcc -DHAVE_CONFIG_H -DEXEEXT=\"\" -I. -I.. -I../lib  -DDEPENDS_ON_LIBICONV=1 -DDEPENDS_ON_LIBINTL=1   -g -O2 -c -o areadlink.o areadlink.c
gcc -DHAVE_CONFIG_H -DEXEEXT=\"\" -I. -I.. -I../lib  -DDEPENDS_ON_LIBICONV=1 -DDEPENDS_ON_LIBINTL=1   -g -O2 -c -o binary-io.o binary-io.c
gcc -DHAVE_CONFIG_H -DEXEEXT=\"\" -I. -I.. -I../lib  -DDEPENDS_ON_LIBICONV=1 -DDEPENDS_ON_LIBINTL=1   -g -O2 -c -o careadlinkat.o careadlinkat.c
gcc -DHAVE_CONFIG_H -DEXEEXT=\"\" -I. -I.. -I../lib  -DDEPENDS_ON_LIBICONV=1 -DDEPENDS_ON_LIBINTL=1   -g -O2 -c -o dirname-lgpl.o dirname-lgpl.c
gcc -DHAVE_CONFIG_H -DEXEEXT=\"\" -I. -I.. -I../lib  -DDEPENDS_ON_LIBICONV=1 -DDEPENDS_ON_LIBINTL=1   -g -O2 -c -o basename-lgpl.o basename-lgpl.c
gcc -DHAVE_CONFIG_H -DEXEEXT=\"\" -I. -I.. -I../lib  -DDEPENDS_ON_LIBICONV=1 -DDEPENDS_ON_LIBINTL=1   -g -O2 -c -o stripslash.o stripslash.c
gcc -DHAVE_CONFIG_H -DEXEEXT=\"\" -I. -I.. -I../lib  -DDEPENDS_ON_LIBICONV=1 -DDEPENDS_ON_LIBINTL=1   -g -O2 -c -o getprogname.o getprogname.c
gcc -DHAVE_CONFIG_H -DEXEEXT=\"\" -I. -I.. -I../lib  -DDEPENDS_ON_LIBICONV=1 -DDEPENDS_ON_LIBINTL=1   -g -O2 -c -o malloca.o malloca.c
gcc -DHAVE_CONFIG_H -DEXEEXT=\"\" -I. -I.. -I../lib  -DDEPENDS_ON_LIBICONV=1 -DDEPENDS_ON_LIBINTL=1   -g -O2 -c -o progname.o progname.c
gcc -DHAVE_CONFIG_H -DEXEEXT=\"\" -I. -I.. -I../lib  -DDEPENDS_ON_LIBICONV=1 -DDEPENDS_ON_LIBINTL=1   -g -O2 -c -o safe-read.o safe-read.c
gcc -DHAVE_CONFIG_H -DEXEEXT=\"\" -I. -I.. -I../lib  -DDEPENDS_ON_LIBICONV=1 -DDEPENDS_ON_LIBINTL=1   -g -O2 -c -o stat-time.o stat-time.c
gcc -DHAVE_CONFIG_H -DEXEEXT=\"\" -I. -I.. -I../lib  -DDEPENDS_ON_LIBICONV=1 -DDEPENDS_ON_LIBINTL=1   -g -O2 -c -o unistd.o unistd.c
gcc -DHAVE_CONFIG_H -DEXEEXT=\"\" -I. -I.. -I../lib  -DDEPENDS_ON_LIBICONV=1 -DDEPENDS_ON_LIBINTL=1   -g -O2 -c -o uniwidth/width.o uniwidth/width.c
gcc -DHAVE_CONFIG_H -DEXEEXT=\"\" -I. -I.. -I../lib  -DDEPENDS_ON_LIBICONV=1 -DDEPENDS_ON_LIBINTL=1   -g -O2 -c -o xmalloc.o xmalloc.c
gcc -DHAVE_CONFIG_H -DEXEEXT=\"\" -I. -I.. -I../lib  -DDEPENDS_ON_LIBICONV=1 -DDEPENDS_ON_LIBINTL=1   -g -O2 -c -o xstrdup.o xstrdup.c
gcc -DHAVE_CONFIG_H -DEXEEXT=\"\" -I. -I.. -I../lib  -DDEPENDS_ON_LIBICONV=1 -DDEPENDS_ON_LIBINTL=1   -g -O2 -c -o xreadlink.o xreadlink.c
rm -f libicrt.a
ar cr libicrt.a allocator.o areadlink.o binary-io.o careadlinkat.o dirname-lgpl.o basename-lgpl.o stripslash.o getprogname.o malloca.o progname.o safe-read.o stat-time.o unistd.o uniwidth/width.o xmalloc.o xstrdup.o xreadlink.o 
ranlib libicrt.a
make[2]: ディレクトリ '/home/sak/libiconv-1.16/srclib' から出ます
make[1]: ディレクトリ '/home/sak/libiconv-1.16/srclib' から出ます
cd src && make all
make[1]: ディレクトリ '/home/sak/libiconv-1.16/src' に入ります
gcc -c -I. -I. -I.. -I../include -I./../include -I../srclib -I./../srclib -I../lib -g -O2  -DINSTALLDIR=\"/home/sak/libiconv-1.16.build/bin\" -DLOCALEDIR=\"/home/sak/libiconv-1.16.build/share/locale\" ./iconv_no_i18n.c
/bin/bash ../libtool --mode=link gcc  -g -O2 iconv_no_i18n.o ../srclib/libicrt.a ../lib/libiconv.la  -o iconv_no_i18n
libtool: link: gcc -g -O2 iconv_no_i18n.o -o .libs/iconv_no_i18n  ../srclib/libicrt.a ../lib/.libs/libiconv.so -Wl,-rpath -Wl,/home/sak/libiconv-1.16.build/lib
gcc -c -I. -I. -I.. -I../include -I./../include -I../srclib -I./../srclib -I../lib -g -O2  -DINSTALLDIR=\"/home/sak/libiconv-1.16.build/bin\" -DLOCALEDIR=\"/home/sak/libiconv-1.16.build/share/locale\" ./iconv.c
test `ls -ld . | sed -e 's/^d\(.........\).*/\1/'` = rwxrwxrwx || chmod 777 .
make[1]: ディレクトリ '/home/sak/libiconv-1.16/src' から出ます
cd po && make all
make[1]: ディレクトリ '/home/sak/libiconv-1.16/po' に入ります
make[1]: 'all' に対して行うべき事はありません.
make[1]: ディレクトリ '/home/sak/libiconv-1.16/po' から出ます
cd man && make all
make[1]: ディレクトリ '/home/sak/libiconv-1.16/man' に入ります
make[1]: 'all' に対して行うべき事はありません.
make[1]: ディレクトリ '/home/sak/libiconv-1.16/man' から出ます
if test -d tests; then cd tests && make all; fi
make[1]: ディレクトリ '/home/sak/libiconv-1.16/tests' に入ります
make[1]: 'all' に対して行うべき事はありません.
make[1]: ディレクトリ '/home/sak/libiconv-1.16/tests' から出ます
