//*CID://+vc08R~:                                   update#=  419  //~vc08R~
//**************************************************************** //~1610I~
//vc08:2020/06/13 Axe:architecture __arm__ is 32 bit, arm64 is __aarch64__//~vc08I~
//vb95:170228 _URC_OK(=0) is not defined in uwind.h of llvm(used without /I)//~vb95I~
//vb90:170220 (Axe) BIONIC_HAVE_UCONTEXT_T was changed from undef to defined at r13b//~vb90I~
//vay2:140726 (Axe)backtrace using "corkscrew" if lib prepared(>=android4.1.1)//~vay2I~
//vay0:140710 (Axe)jni exception handling
// Copy from xroche/coffecatch                                     //~vay0I~
//**************************************************************** //~1610I~
                                                                   //~vay2I~
//*************************************************                //~1617I~
/* CoffeeCatch, a tiny native signal handler/catcher for JNI code.
 * (especially for Android/Dalvik)
 *
 * Copyright (c) 2013, Xavier Roche (http://www.httrack.com/)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

#ifdef __ANDROID__
#define USE_UNWIND
//#ifdef AAA                                                       //~vay2R~
#define USE_CORKSCREW
//#endif                                                           //~vay2R~
#endif

#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <unistd.h>
#include <string.h>
#include <errno.h>
#include <sys/types.h>
#include <assert.h>
#include <signal.h>
#include <asm/signal.h>                                            //~vb90I~
#include <setjmp.h>
                                                                   //~vay0I~
#ifdef AAA                                                         //~4719I~
#else                                                              //~4719I~
#include <ulib.h>                                                  //~vay0I~
#include <ustring.h>                                               //~vay0I~
#include <utrace.h>                                                //~vay0I~
#include <uerr.h>                                                  //~vay0I~
#include <ulibarm.h>                                               //~vay0I~
#include <jni.h>                                                   //~vay0M~
#include <jnisigh.h>                                               //~vay0M~
#include <jnig.h>                                                  //~vay0I~
#include <jnic2j.h>                                                //~vay0I~
#include <android/log.h>                                           //~vay0M~
#endif                                                             //~4719I~
                                                                   //~vay0I~
#if defined(__ANDROID__) && !defined(__BIONIC_HAVE_UCONTEXT_T) && \
    defined(__arm__) && !defined(__BIONIC_HAVE_STRUCT_SIGCONTEXT)
#include <asm/sigcontext.h>
#endif 
//#if (defined(USE_UNWIND) && !defined(USE_CORKSCREW))             //~vay2R~
#if (defined(USE_UNWIND))                                          //~vay2I~
//#ifdef __arm__  //@@@@test :true                                 //~vb90R~//~vb95R~
//ii                                                               //~vb90R~
//#else                                                            //~vb90R~
//ii                                                               //~vb90R~
//#endif                                                           //~vb90R~
#include <unwind.h>
#define _URC_OK  0                                                 //~vb95R~
static int Sdosigh=1;                                              //~vay2R~
#endif
#if defined(USE_CORKSCREW)                                         //~vay2I~
static int Suse_corkscrew=0;                                       //~vay2I~
#endif                                                             //~vay2I~
#include <pthread.h>
#include <dlfcn.h>
#ifdef BBB                                                         //~vay0M~
#include "coffeecatch.h"
#endif //BBB                                                       //~vay0R~
/*#define NDK_DEBUG 1*/
#if ( defined(NDK_DEBUG) && ( NDK_DEBUG == 1 ) )
#define DEBUG(A) do { A; } while(0)
#define FD_ERRNO 2
static void print(const char *const s) {
  size_t count;
  for(count = 0; s[count] != '\0'; count++) ;
  /* write() is async-signal-safe. */
  (void) write(FD_ERRNO, s, count);
}
#else
#define DEBUG(A)
#endif
/* Alternative stack size. */
#define SIG_STACK_BUFFER_SIZE SIGSTKSZ

#ifdef USE_UNWIND
/* Number of backtraces to get. */
#define BACKTRACE_FRAMES_MAX 32
#endif
/* Signals to be caught. */
#ifdef BBB                                                         //~vay0I~
#define SIG_CATCH_COUNT 7
static const int native_sig_catch[SIG_CATCH_COUNT + 1]
  = { SIGABRT, SIGILL, SIGTRAP, SIGBUS, SIGFPE, SIGSEGV, SIGSTKFLT, 0 };
#else                                                              //~vay0I~
#define SIG_CATCH_COUNT 6                                          //~vay0I~
static const int native_sig_catch[SIG_CATCH_COUNT + 1]             //~vay0I~
  = { SIGABRT, SIGILL,          SIGBUS, SIGFPE, SIGSEGV, SIGSTKFLT, 0 };//~vay0I~
#endif                                                             //~vay0I~
/* Maximum value of a caught signal. */
#define SIG_NUMBER_MAX 32
#if (defined(__ANDROID__) && (!defined(__BIONIC_HAVE_UCONTEXT_T)))
#ifndef ucontext_h_seen
#define ucontext_h_seen

/* stack_t definition */
//#include <asm/signal.h>                                          //~vb90R~

#if defined(__arm__)

/* Taken from richard.quirk's header file. (Android does not have it) */

typedef struct ucontext {
  unsigned long uc_flags;
  struct ucontext *uc_link;
  stack_t uc_stack;
  struct sigcontext uc_mcontext;
  unsigned long uc_sigmask;
} ucontext_t;

#elif (defined(__aarch64__))                                       //~vc08R~
                                                                   //~vc08R~
typedef struct ucontext {                                          //~vc08R~
  unsigned long uc_flags;                                          //~vc08R~
  struct ucontext *uc_link;                                        //~vc08R~
  stack_t uc_stack;                                                //~vc08R~
  struct sigcontext uc_mcontext;                                   //~vc08R~
  unsigned long uc_sigmask;                                        //~vc08R~
} ucontext_t;                                                      //~vc08R~
                                                                   //~vc08R~
#elif defined(__i386__)

/* Taken from Google Breakpad. */

/* 80-bit floating-point register */
struct _libc_fpreg {
  unsigned short significand[4];
  unsigned short exponent;
};

/* Simple floating-point state, see FNSTENV instruction */
struct _libc_fpstate {
  unsigned long cw;
  unsigned long sw;
  unsigned long tag;
  unsigned long ipoff;
  unsigned long cssel;
  unsigned long dataoff;
  unsigned long datasel;
  struct _libc_fpreg _st[8];
  unsigned long status;
};

typedef uint32_t  greg_t;

typedef struct {
  uint32_t gregs[19];
  struct _libc_fpstate* fpregs;
  uint32_t oldmask;
  uint32_t cr2;
} mcontext_t;

enum {
  REG_GS = 0,
  REG_FS,
  REG_ES,
  REG_DS,
  REG_EDI,
  REG_ESI,
  REG_EBP,
  REG_ESP,
  REG_EBX,
  REG_EDX,
  REG_ECX,
  REG_EAX,
  REG_TRAPNO,
  REG_ERR,
  REG_EIP,
  REG_CS,
  REG_EFL,
  REG_UESP,
  REG_SS,
};

typedef struct ucontext {
  uint32_t uc_flags;
  struct ucontext* uc_link;
  stack_t uc_stack;
  mcontext_t uc_mcontext;
} ucontext_t;

#elif defined(__mips__)

/* Taken from Google Breakpad. */

typedef struct {
  uint32_t regmask;
  uint32_t status;
  uint64_t pc;
  uint64_t gregs[32];
  uint64_t fpregs[32];
  uint32_t acx;
  uint32_t fpc_csr;
  uint32_t fpc_eir;
  uint32_t used_math;
  uint32_t dsp;
  uint64_t mdhi;
  uint64_t mdlo;
  uint32_t hi1;
  uint32_t lo1;
  uint32_t hi2;
  uint32_t lo2;
  uint32_t hi3;
  uint32_t lo3;
} mcontext_t;

typedef struct ucontext {
  uint32_t uc_flags;
  struct ucontext* uc_link;
  stack_t uc_stack;
  mcontext_t uc_mcontext;
} ucontext_t;

#else
#error "Architecture is not supported (unknown ucontext layout)"
#endif

#endif
#endif //BIONIC_HAVE_UCONTEXT_T                                    //~vb90I~
                                                                   //~vb90I~
#ifdef USE_CORKSCREW
typedef struct map_info_t map_info_t;
/* Extracted from Android's include/corkscrew/backtrace.h */
typedef struct {
    uintptr_t absolute_pc;
    uintptr_t stack_top;
    size_t stack_size;
} backtrace_frame_t;
typedef struct {
    uintptr_t relative_pc;
    uintptr_t relative_symbol_addr;
    char* map_name;
    char* symbol_name;
    char* demangled_name;
} backtrace_symbol_t;
/* Extracted from Android's libcorkscrew/arch-arm/backtrace-arm.c */
typedef ssize_t (*t_unwind_backtrace_signal_arch)
(siginfo_t* si, void* sc, const map_info_t* lst, backtrace_frame_t* bt,
size_t ignore_depth, size_t max_depth);
typedef map_info_t* (*t_acquire_my_map_info_list)();
typedef void (*t_release_my_map_info_list)(map_info_t* milist);
typedef void (*t_get_backtrace_symbols)(const backtrace_frame_t* backtrace,
                                        size_t frames,
                                        backtrace_symbol_t* symbols);
typedef void (*t_free_backtrace_symbols)(backtrace_symbol_t* symbols,
                                         size_t frames);
#endif

//#endif //BIONIC_HAVE_UCONTEXT_T                                  //~vb90R~
//*****************************************************            //~vay0I~
/* Process-wide crash handler structure. */
typedef struct native_code_global_struct {
  /* Initialized. */
  int initialized;

  /* Lock. */
  pthread_mutex_t mutex;

  /* Backup of sigaction. */
  struct sigaction *sa_old;
} native_code_global_struct;
#define NATIVE_CODE_GLOBAL_INITIALIZER { 0, PTHREAD_MUTEX_INITIALIZER, NULL }
                                                                   //~vay0I~
//*****************************************************            //~vay0I~
/* Thread-specific crash handler structure. */
typedef struct native_code_handler_struct {
  /* Restore point context. */
  sigjmp_buf ctx;
  int ctx_is_set;

  /* Alternate stack. */
  char *stack_buffer;
  size_t stack_buffer_size;
  stack_t stack_old;

  /* Signal code and info. */
  int code;
  siginfo_t si;
  ucontext_t uc;

  /* Uwind context. */
#if (defined(USE_CORKSCREW))
//  backtrace_frame_t frames[BACKTRACE_FRAMES_MAX];                //~vay2R~
  backtrace_frame_t frames_corkscrew[BACKTRACE_FRAMES_MAX];        //~vay2I~
  uintptr_t frames[BACKTRACE_FRAMES_MAX];                          //~vay2I~
#elif (defined(USE_UNWIND))
  uintptr_t frames[BACKTRACE_FRAMES_MAX];
#endif
  size_t frames_size;
  size_t frames_skip;

  /* Custom assertion failures. */
  const char *expression;
  const char *file;
  int line;

  /* Alarm was fired. */
  int alarm;
#ifdef AAA                                                         //~4719I~
#else                                                              //~4719I~
	JNIEnv *threadEnv;                                             //~4719I~
	char   funcid[MAX_FUNCID];                                     //~vay0I~
#endif                                                             //~4719I~
} native_code_handler_struct;
//*****************************************************            //~vay0I~
/* Global crash handler structure. */
static native_code_global_struct native_code_g =
  NATIVE_CODE_GLOBAL_INITIALIZER;
//*****************************************************            //~vay0I~
/* Thread variable holding context. */
pthread_key_t native_code_thread;
//***********************************************************************************//~vay0I~
void  jnisigh_popupdialog(native_code_handler_struct *t);          //~vay0I~
//***********************************************************************************//~vay0I~

//#if (defined(USE_UNWIND) && !defined(USE_CORKSCREW))             //~vay2R~
#if (defined(USE_UNWIND))                                          //~vay2I~
//***********************************************************************************
/* Unwind callback */
//***********************************************************************************
static _Unwind_Reason_Code
coffeecatch_unwind_callback(struct _Unwind_Context* context, void* arg) {
  native_code_handler_struct *const s = (native_code_handler_struct*) arg;

  const uintptr_t ip = _Unwind_GetIP(context);

  DEBUG(print("called unwind callback\n"));
  LOGPD("@@@@ unwind_callback GetIp=%x,s=%p\n",ip,s);              //~vay2R~

  if (ip != 0x0) {
  LOGPD("@@@@ unwind_callback frame_skip=%d\n",s->frames_skip);    //~vay2R~
    if (s->frames_skip == 0) {
      s->frames[s->frames_size] = ip;
      s->frames_size++;
    } else {
      s->frames_skip--;
    }
  }
  LOGPD("@@@@ unwind_callback frames_size=%d\n",s->frames_size);   //~vay2R~

  if (s->frames_size == BACKTRACE_FRAMES_MAX) {
    return _URC_END_OF_STACK;
  } else {
    DEBUG(print("returned _URC_OK\n"));
    return _URC_OK;
  }
}
#endif
//***********************************************************************************//~vay0I~
/* Use libcorkscrew to get a backtrace inside a signal handler.
   Will only return a non-zero code on Android >= 4 (with libcorkscrew.so
   being shipped) */
//***********************************************************************************//~vay0I~
#ifdef USE_CORKSCREW
static ssize_t coffeecatch_backtrace_signal(siginfo_t* si, void* sc, 
                                            backtrace_frame_t* frames,
                                            size_t ignore_depth,
                                            size_t max_depth) {
  void *const libcorkscrew = dlopen("libcorkscrew.so", RTLD_LAZY | RTLD_LOCAL);
  if (libcorkscrew != NULL) {
    t_unwind_backtrace_signal_arch unwind_backtrace_signal_arch 
      = (t_unwind_backtrace_signal_arch)
      dlsym(libcorkscrew, "unwind_backtrace_signal_arch");
    t_acquire_my_map_info_list acquire_my_map_info_list 
      = (t_acquire_my_map_info_list)
      dlsym(libcorkscrew, "acquire_my_map_info_list");
    t_release_my_map_info_list release_my_map_info_list 
      = (t_release_my_map_info_list)
      dlsym(libcorkscrew, "release_my_map_info_list");
    if (unwind_backtrace_signal_arch != NULL
        && acquire_my_map_info_list != NULL
        && release_my_map_info_list != NULL) {
      map_info_t*const info = acquire_my_map_info_list();
      const ssize_t size = 
        unwind_backtrace_signal_arch(si, sc, info, frames, ignore_depth,
                                     max_depth);
      release_my_map_info_list(info);
      Suse_corkscrew=1;                                            //~vay2I~
      LOGPD("@@@@ Suse_corkscrew=1\n");                            //~vay2I~
      return size;
    } else {
      DEBUG(print("symbols not founs in libcorkscrew.so\n"));
    }
    dlclose(libcorkscrew);
  } else {
    DEBUG(print("libcorkscrew.so could not be loaded\n"));
  }
  return -1;
}
//***********************************************************************************//~vay0I~
static void coffeecatch_backtrace_symbols(const backtrace_frame_t* backtrace,
                                          size_t frames,
                                          void (*fun)(void *arg,
                                          const backtrace_symbol_t *sym),
                                          void *arg) {
  void *const libcorkscrew = dlopen("libcorkscrew.so", RTLD_LAZY | RTLD_LOCAL);
  if (libcorkscrew != NULL) {
    t_get_backtrace_symbols get_backtrace_symbols 
      = (t_get_backtrace_symbols)
      dlsym(libcorkscrew, "get_backtrace_symbols");
    t_free_backtrace_symbols free_backtrace_symbols 
      = (t_free_backtrace_symbols)
      dlsym(libcorkscrew, "free_backtrace_symbols");
    if (get_backtrace_symbols != NULL
        && free_backtrace_symbols != NULL) {
      backtrace_symbol_t symbols[BACKTRACE_FRAMES_MAX];
      size_t i;
      if (frames > BACKTRACE_FRAMES_MAX) {
        frames = BACKTRACE_FRAMES_MAX;
      }
      get_backtrace_symbols(backtrace, frames, symbols);
      for(i = 0; i < frames; i++) {
        fun(arg, &symbols[i]);
      }
      free_backtrace_symbols(symbols, frames);
    }
    dlclose(libcorkscrew);
  }
}
#endif
//***********************************************************************************//~vay0I~
/* Call the old handler. */
//***********************************************************************************//~vay0I~
static void coffeecatch_call_old_signal_handler(const int code, siginfo_t *const si,
                                                       void * const sc) {
  /* Call the "real" Java handler for JIT and internals. */
  if (code >= 0 && code < SIG_NUMBER_MAX) {
    UTRACEP("@@@@ call_old_signal_handler code=%d,old_sigaction=%p,handler=%p\n",code,native_code_g.sa_old[code].sa_sigaction,native_code_g.sa_old[code].sa_handler);//~vay0R~
    if (native_code_g.sa_old[code].sa_sigaction != NULL) {
    	UTRACEP("@@@@ call_old_signal_handler sigaction\n");       //~vay0R~
      native_code_g.sa_old[code].sa_sigaction(code, si, sc);
    } else if (native_code_g.sa_old[code].sa_handler != NULL) {
    	UTRACEP("@@@@ call_old_signal_handler call handler\n");    //~vay0R~
     if (native_code_g.sa_old[code].sa_handler != SIG_IGN)	//SIG_DFL=0,SIG_IGN=1//~vay0I~
      native_code_g.sa_old[code].sa_handler(code);
    }
  }
    UTRACEP("@@@@ call_old_signal_handler return code=%d\n",code); //~vay0R~//+vc08R~
    UTRACE_FLUSH("call_old_signal_handler exit");
}
#ifdef BBB                                                         //~vay0I~
//***********************************************************************************//~vay0I~
/* Unflag "on stack" */
//***********************************************************************************//~vay0I~
static void coffeecatch_revert_alternate_stack(void) {
  stack_t ss;
  if (sigaltstack(NULL, &ss) == 0) {
    ss.ss_flags &= ~SS_ONSTACK;
    sigaltstack (&ss, NULL);
  }
}
//***********************************************************************************//~vay0I~
/* Try to jump to userland. */
//***********************************************************************************//~vay0I~
static void coffeecatch_try_jump_userland(native_code_handler_struct*
                                                 const t,
                                                 const int code,
                                                 siginfo_t *const si,
                                                 void * const sc) {
  (void) si; /* UNUSED */
  (void) sc; /* UNUSED */

  /* Valid context ? */
  if (t != NULL && t->ctx_is_set) {
    DEBUG(print("calling siglongjmp()\n"));

    /* Invalidate the context */
    t->ctx_is_set = 0;

    /* We need to revert the alternate stack before jumping. */
    coffeecatch_revert_alternate_stack();

    /*
     * Note on async-signal-safety of siglongjmp() [POSIX] :
     * "Note that longjmp() and siglongjmp() are not in the list of
     * async-signal-safe functions. This is because the code executing after
     * longjmp() and siglongjmp() can call any unsafe functions with the same
     * danger as calling those unsafe functions directly from the signal
     * handler. Applications that use longjmp() and siglongjmp() from within
     * signal handlers require rigorous protection in order to be portable.
     * Many of the other functions that are excluded from the list are
     * traditionally implemented using either malloc() or free() functions or
     * the standard I/O library, both of which traditionally use data
     * structures in a non-async-signal-safe manner. Since any combination of
     * different functions using a common data structure can cause
     * async-signal-safety problems, this volume of POSIX.1-2008 does not
     * define the behavior when any unsafe function is called in a signal
     * handler that interrupts an unsafe function."
     */
    siglongjmp(t->ctx, code);
  }
}
#endif   //BBB                                                     //~vay0M~
//***********************************************************************************//~vay0I~
static void coffeecatch_start_alarm(void) {
  /* Ensure we do not deadlock. Default of ALRM is to die.
   * (signal() and alarm() are signal-safe) */
  (void) alarm(30);
}
//***********************************************************************************//~vay0I~
static void coffeecatch_mark_alarm(native_code_handler_struct *const t) {
  t->alarm = 1;
}
//***********************************************************************************//~vay0I~
/* Copy context infos (signal code, etc.) */
//***********************************************************************************//~vay0I~
static void coffeecatch_copy_context(native_code_handler_struct *const t,
                                     const int code, siginfo_t *const si,
                                     void *const sc) {
  t->code = code;
  t->si = *si;
  if (sc != NULL) {
    ucontext_t *const uc = (ucontext_t*) sc;
    t->uc = *uc;
  } else {
    memset(&t->uc, 0, sizeof(t->uc));
  }
  UTRACEP("@@@@ copy_context\n");                                  //~vay0R~
#ifdef USE_UNWIND
  /* Frame buffer initial position. */
  t->frames_size = 0;

  /* Skip us and the caller. */
  t->frames_skip = 2;

  /* Use the corkscrew library to extract the backtrace. */
#ifdef USE_CORKSCREW
//t->frames_size = coffeecatch_backtrace_signal(si, sc, t->frames, 0,//~vay2R~
//                                              BACKTRACE_FRAMES_MAX);//~vay2R~
  t->frames_size = coffeecatch_backtrace_signal(si, sc, t->frames_corkscrew, 0,BACKTRACE_FRAMES_MAX);//~vay2I~
  LOGPD("@@@@ copy_context frames_size(CORKSCREW)=%x\n",t->frames_size);//~vay2R~
  if ((int)t->frames_size<0)                                       //~vay2R~
  {                                                                //~vay2I~
	t->frames_size = 0;                                            //~vay2I~
  	LOGPD("@@@@ c_Unwind_Backtrace\n");                            //~vay2I~
	_Unwind_Backtrace(coffeecatch_unwind_callback, t);             //~vay2I~
  }                                                                //~vay2I~
#else
  UTRACEP("@@@@ copy_context Unwind_Backtrace\n");                 //~vay0R~
  /* Unwind frames (equivalent to backtrace()) */
  _Unwind_Backtrace(coffeecatch_unwind_callback, t);
#endif

  LOGPD("@@@@ copy_context frames_size=%d\n",t->frames_size);      //~vay2R~
  if (t->frames_size != 0) {
    DEBUG(print("called _Unwind_Backtrace()\n"));
  } else {
    DEBUG(print("called _Unwind_Backtrace(), but no traces\n"));
  }
#endif
}
//***********************************************************************************//~vay0I~
/* Return the thread-specific native_code_handler_struct structure, or
 * @c null if no such structure is available. */
//***********************************************************************************//~vay0I~
static native_code_handler_struct* coffeecatch_get() {
//	UTRACEP("@@@@ coffeecatch_get native_code_thread=%p,t=%p\n",native_code_thread,pthread_getspecific(native_code_thread));//~vay0R~
  return (native_code_handler_struct*)
      pthread_getspecific(native_code_thread);
}
//***********************************************************************************//~vay0I~
int coffeecatch_cancel_pending_alarm() {
  native_code_handler_struct *const t = coffeecatch_get();
  if (t != NULL && t->alarm) {
    t->alarm = 0;
    /* "If seconds is 0, a pending alarm request, if any, is canceled." */
    alarm(0);
    return 0;
  }
  return -1;
}
//***********************************************************************************//~vay0I~
/* Internal signal pass-through. Allows to peek the "real" crash before
 * calling the Java handler. Remember than Java needs many of the signals
 * (for the JIT, for test-free NullPointerException handling, etc.)
 * We record the siginfo_t context in this function each time it is being
 * called, to be able to know what error caused an issue.
 */
//***********************************************************************************//~vay0I~
void coffeecatch_signal_pass(const int code, siginfo_t *const si,  //~vay0R~
                                    void *const sc) {
  native_code_handler_struct *t;

  DEBUG(print("caught signal\n"));
  UTRACEP("@@@@signal_pass code=%d\n",code);                       //~vay0R~
  /* Call the "real" Java handler for JIT and internals. */
  coffeecatch_call_old_signal_handler(code, si, sc);

  /* Still here ?
   * FIXME TODO: This is the Dalvik behavior - but is it the SunJVM one ? */

  /* Ensure we do not deadlock. Default of ALRM is to die.
   * (signal() and alarm() are signal-safe) */
  signal(code, SIG_DFL);
  coffeecatch_start_alarm();

  /* Available context ? */
  t = coffeecatch_get();
  if (t != NULL) {
    /* An alarm() call was triggered. */
    coffeecatch_mark_alarm(t);

    /* Take note of the signal. */
    coffeecatch_copy_context(t, code, si, sc);

    /* Back to the future. */
    UTRACEP("@@@@ signal_pass\n");                                 //~vay0R~
#ifdef AAA                                                         //~4719I~
    coffeecatch_try_jump_userland(t, code, si, sc);
#else                                                              //~4719I~
  	jnisigh_popupdialog(t);                                        //~vay0R~
#endif                                                             //~4719I~
  }

  /* Nope. (abort() is signal-safe) */
  DEBUG(print("calling abort()\n"));
  signal(SIGABRT, SIG_DFL);
  abort();
}
//***********************************************************************************//~vay0I~
/* Internal crash handler for abort(). Java calls abort() if its signal handler
 * could not resolve the signal ; thus calling us through this handler. */
//***********************************************************************************//~vay0I~
void coffeecatch_signal_abort(const int code, siginfo_t *const si, //~vay0R~
                                            void *const sc) {
  native_code_handler_struct *t;

  (void) sc; /* UNUSED */

  DEBUG(print("caught abort\n"));
  UTRACEP("@@@@signal_abort code=%d\n",code);                      //~vay0R~

  /* Ensure we do not deadlock. Default of ALRM is to die.
   * (signal() and alarm() are signal-safe) */
  signal(code, SIG_DFL);
  coffeecatch_start_alarm();

  /* Available context ? */
  t = coffeecatch_get();
  if (t != NULL) {
    /* An alarm() call was triggered. */
    coffeecatch_mark_alarm(t);

    /* Take note (real "abort()") */
    coffeecatch_copy_context(t, code, si, sc);

    /* Back to the future. */
    UTRACEP("@@@@ signal_abort\n");                                //~vay0R~
#ifdef AAA                                                         //~4719I~
    coffeecatch_try_jump_userland(t, code, si, sc);
#else                                                              //~4719I~
  	jnisigh_popupdialog(t);                                        //~vay0R~
#endif                                                             //~4719I~
  }

  /* No such restore point, call old signal handler then. */
  DEBUG(print("calling old signal handler\n"));
  coffeecatch_call_old_signal_handler(code, si, sc);

  /* Nope. (abort() is signal-safe) */
  DEBUG(print("calling abort()\n"));
  abort();
}
//******************************************************************************//~vay0I~
/* Internal globals initialization. */
//******************************************************************************//~vay0I~
static int coffeecatch_handler_setup_global(void) {
  if (native_code_g.initialized++ == 0) {
    size_t i;
    struct sigaction sa_abort;
    struct sigaction sa_pass;

    DEBUG(print("installing global signal handlers\n"));

    /* Setup handler structure. */
    memset(&sa_abort, 0, sizeof(sa_abort));
    sigemptyset(&sa_abort.sa_mask);
    sa_abort.sa_sigaction = coffeecatch_signal_abort;
    sa_abort.sa_flags = SA_SIGINFO | SA_ONSTACK;

    memset(&sa_pass, 0, sizeof(sa_pass));
    sigemptyset(&sa_pass.sa_mask);
    sa_pass.sa_sigaction = coffeecatch_signal_pass;
    sa_pass.sa_flags = SA_SIGINFO | SA_ONSTACK;

    /* Allocate */
    native_code_g.sa_old = calloc(sizeof(struct sigaction), SIG_NUMBER_MAX);
    UTRACEP("@@@@setup_global calloc sa_old=%p,len=%d\n",native_code_g.sa_old,sizeof(struct sigaction));//~vay0R~
    if (native_code_g.sa_old == NULL) {
      return -1;
    }

    /* Setup signal handlers for SIGABRT (Java calls abort()) and others. **/
    for (i = 0; native_sig_catch[i] != 0; i++) {
      const int sig = native_sig_catch[i];
      const struct sigaction * const action =
          sig == SIGABRT ? &sa_abort : &sa_pass;
      assert(sig < SIG_NUMBER_MAX);
      if (sigaction(sig, action, &native_code_g.sa_old[sig]) != 0) {
        return -1;
      }
    }

    /* Initialize thread var. */
    if (pthread_key_create(&native_code_thread, NULL) != 0) {
      return -1;
    }

    DEBUG(print("installed global signal handlers\n"));
  }

  /* OK. */
  return 0;
}
//******************************************************************************//~vay0R~
/**
 * Acquire the crash handler for the current thread.
 * The coffeecatch_handler_cleanup() must be called to release allocated
 * resources.
 **/
//******************************************************************************//~vay0I~
static int coffeecatch_handler_setup(int setup_thread) {
  int code;

  DEBUG(print("setup for a new handler\n"));

  /* Initialize globals. */
  if (pthread_mutex_lock(&native_code_g.mutex) != 0) {
    return -1;
  }
  code = coffeecatch_handler_setup_global();
  if (pthread_mutex_unlock(&native_code_g.mutex) != 0) {
    return -1;
  }

  /* Global initialization failed. */
  if (code != 0) {
    return -1;
  }

  /* Initialize locals. */
  if (setup_thread && coffeecatch_get() == NULL) {
    stack_t stack;
    native_code_handler_struct *const t =
      calloc(sizeof(native_code_handler_struct), 1);
    UTRACEP("@@@@ handler_setup t=%p,len=%d\n",t,sizeof(native_code_handler_struct));//~vay0R~

    DEBUG(print("installing thread alternative stack\n"));

    /* Initialize structure */
    t->stack_buffer_size = SIG_STACK_BUFFER_SIZE;
    t->stack_buffer = malloc(t->stack_buffer_size);
    UTRACEP("@@@@ handler_setup malloc stackbuff=%p,size=%d\n",t->stack_buffer,t->stack_buffer_size);//~vay0R~
    if (t->stack_buffer == NULL) {
      return -1;
    }
  
    /* Setup alternative stack. */
    memset(&stack, 0, sizeof(stack));
    stack.ss_sp = t->stack_buffer;
    stack.ss_size = t->stack_buffer_size;
    stack.ss_flags = 0;

    /* Install alternative stack. This is thread-safe */
    if (sigaltstack(&stack, &t->stack_old) != 0) {
      return -1;
    }

    /* Set thread-specific value. */
    if (pthread_setspecific(native_code_thread, t) != 0) {
      return -1;
    }

    DEBUG(print("installed thread alternative stack\n"));
  }

  /* OK. */
  return 0;
}

//******************************************************************************//~vay0I~
/**
 * Release the resources allocated by a previous call to
 * coffeecatch_handler_setup().
 * This function must be called as many times as
 * coffeecatch_handler_setup() was called to fully release allocated
 * resources.
 **/
//******************************************************************************//~vay0I~
int coffeecatch_handler_cleanup() {                                //~vay0R~
  /* Cleanup locals. */
  native_code_handler_struct *const t = coffeecatch_get();
  if (t != NULL) {
    DEBUG(print("removing thread alternative stack\n"));

    /* Erase thread-specific value now (detach). */
    if (pthread_setspecific(native_code_thread, NULL) != 0) {
      assert(! "pthread_setspecific() failed");
    }

    /* Restore previous alternative stack. */
    if (sigaltstack(&t->stack_old, NULL) != 0) {
      return -1;
    }

    /* Free alternative stack */
    if (t->stack_buffer != NULL) {
    UTRACEP("@@@@ handler_cleanup free stackbuff=%p\n",t->stack_buffer);//~vay0R~
      free(t->stack_buffer);
      t->stack_buffer = NULL;
      t->stack_buffer_size = 0;
    }

    /* Free structure. */
    UTRACEP("@@@@ handler_cleanup free t=%p,funcid=%s\n",t,t->funcid);//~vay0R~
    UTRACEP("@@@@ handler_cleanup free t=%p\n",t);                 //~vay0R~
    free(t);

    DEBUG(print("removed thread alternative stack\n"));
  }

  /* Cleanup globals. */
  if (pthread_mutex_lock(&native_code_g.mutex) != 0) {
    assert(! "pthread_mutex_lock() failed");
  }
  assert(native_code_g.initialized != 0);
  if (--native_code_g.initialized == 0) {
    size_t i;

    DEBUG(print("removing global signal handlers\n"));

    /* Restore signal handler. */
    for(i = 0; native_sig_catch[i] != 0; i++) {
      const int sig = native_sig_catch[i];
      assert(sig < SIG_NUMBER_MAX);
      if (sigaction(sig, &native_code_g.sa_old[sig], NULL) != 0) {
        return -1;
      }
    }

    /* Initialize thread var. */
    if (pthread_key_delete(native_code_thread) != 0) {
      assert(! "pthread_key_delete() failed");
    }

    DEBUG(print("removed global signal handlers\n"));
  }
  if (pthread_mutex_unlock(&native_code_g.mutex) != 0) {
    assert(! "pthread_mutex_unlock() failed");
  }

  return 0;
}
#ifdef BBB                                                         //~vay0R~
//******************************************************************************//~vay0I~
/**
 * Get the signal associated with the crash.
 */
//******************************************************************************//~vay0I~
int coffeecatch_get_signal() {
  const native_code_handler_struct* const t = coffeecatch_get();
  if (t != NULL) {
    return t->code;
  } else {
    return -1;
  }
}
#endif //BBB                                                       //~vay0I~
//***************************************************************************//~vay0I~
/* Signal descriptions.
   See <http://pubs.opengroup.org/onlinepubs/009696699/basedefs/signal.h.html>
*/
//***************************************************************************//~vay0I~
static const char* coffeecatch_desc_sig(int sig, int code) {
  switch(sig) {
  case SIGILL:
    switch(code) {
    case ILL_ILLOPC:
      return "Illegal opcode";
    case ILL_ILLOPN:
      return "Illegal operand";
    case ILL_ILLADR:
      return "Illegal addressing mode";
    case ILL_ILLTRP:
      return "Illegal trap";
    case ILL_PRVOPC:
      return "Privileged opcode";
    case ILL_PRVREG:
      return "Privileged register";
    case ILL_COPROC:
      return "Coprocessor error";
    case ILL_BADSTK:
      return "Internal stack error";
    default:
      return "Illegal operation";
    }
    break;
  case SIGFPE:
    switch(code) {
    case FPE_INTDIV:
      return "Integer divide by zero";
    case FPE_INTOVF:
      return "Integer overflow";
    case FPE_FLTDIV:
      return "Floating-point divide by zero";
    case FPE_FLTOVF:
      return "Floating-point overflow";
    case FPE_FLTUND:
      return "Floating-point underflow";
    case FPE_FLTRES:
      return "Floating-point inexact result";
    case FPE_FLTINV:
      return "Invalid floating-point operation";
    case FPE_FLTSUB:
      return "Subscript out of range";
    default:
      return "Floating-point";
    }
    break;
  case SIGSEGV:
    switch(code) {
    case SEGV_MAPERR:
      return "Address not mapped to object";
    case SEGV_ACCERR:
      return "Invalid permissions for mapped object";
    default:
      return "Segmentation violation";
    }
    break;
  case SIGBUS:
    switch(code) {
    case BUS_ADRALN:
      return "Invalid address alignment";
    case BUS_ADRERR:
      return "Nonexistent physical address";
    case BUS_OBJERR:
      return "Object-specific hardware error";
    default:
      return "Bus error";
    }
    break;
  case SIGTRAP:
    switch(code) {
    case TRAP_BRKPT:
      return "Process breakpoint";
    case TRAP_TRACE:
      return "Process trace trap";
    default:
      return "Trap";
    }
    break;
  case SIGCHLD:
    switch(code) {
    case CLD_EXITED:
      return "Child has exited";
    case CLD_KILLED:
      return "Child has terminated abnormally and did not create a core file";
    case CLD_DUMPED:
      return "Child has terminated abnormally and created a core file";
    case CLD_TRAPPED:
      return "Traced child has trapped";
    case CLD_STOPPED:
      return "Child has stopped";
    case CLD_CONTINUED:
      return "Stopped child has continued";
    default:
      return "Child";
    }
    break;
  case SIGPOLL:
    switch(code) {
    case POLL_IN:
      return "Data input available";
    case POLL_OUT:
      return "Output buffers available";
    case POLL_MSG:
      return "Input message available";
    case POLL_ERR:
      return "I/O error";
    case POLL_PRI:
      return "High priority input available";
    case POLL_HUP:
      return "Device disconnected";
    default:
      return "Pool";
    }
    break;
  case SIGABRT:
    return "Process abort signal";
  case SIGALRM:
    return "Alarm clock";
  case SIGCONT:
    return "Continue executing, if stopped";
  case SIGHUP:
    return "Hangup";
  case SIGINT:
    return "Terminal interrupt signal";
  case SIGKILL:
    return "Kill";
  case SIGPIPE:
    return "Write on a pipe with no one to read it";
  case SIGQUIT:
    return "Terminal quit signal";
  case SIGSTOP:
    return "Stop executing";
  case SIGTERM:
    return "Termination signal";
  case SIGTSTP:
    return "Terminal stop signal";
  case SIGTTIN:
    return "Background process attempting read";
  case SIGTTOU:
    return "Background process attempting write";
  case SIGUSR1:
    return "User-defined signal 1";
  case SIGUSR2:
    return "User-defined signal 2";
  case SIGPROF:
    return "Profiling timer expired";
  case SIGSYS:
    return "Bad system call";
  case SIGVTALRM:
    return "Virtual timer expired";
  case SIGURG:
    return "High bandwidth data is available at a socket";
  case SIGXCPU:
    return "CPU time limit exceeded";
  case SIGXFSZ:
    return "File size limit exceeded";
  default:
    switch(code) {
    case SI_USER:
      return "Signal sent by kill()";
    case SI_QUEUE:
      return "Signal sent by the sigqueue()";
    case SI_TIMER:
      return "Signal generated by expiration of a timer set by timer_settime()";
    case SI_ASYNCIO:
      return "Signal generated by completion of an asynchronous I/O request";
    case SI_MESGQ:
      return
        "Signal generated by arrival of a message on an empty message queue";
    default:
      return "Unknown signal";
    }
    break;
  }
}
//***************************************************************************//~vay0I~
/**
 * Get the backtrace size. Returns 0 if no backtrace is available.
 */
//***************************************************************************//~vay0I~
size_t coffeecatch_get_backtrace_size(void) {
#ifdef USE_UNWIND
  const native_code_handler_struct* const t = coffeecatch_get();
  if (t != NULL) {
  	UTRACEP("@@@@ get_backtrace_size:%d\n",t->frames_size);        //~vay0R~
    return t->frames_size;
  } else {
  	UTRACEP("get_backtrace_size=0\n");                             //~vay0R~
    return 0;
  }
#else
  return 0;
#endif
}
//#ifdef BBB                                                       //~vay2R~
//***************************************************************************//~vay0I~
/**
 * Get the <index>th element of the backtrace, or 0 upon error.
 */
//***************************************************************************//~vay0I~
uintptr_t coffeecatch_get_backtrace(ssize_t index) {
#ifdef USE_UNWIND
  const native_code_handler_struct* const t = coffeecatch_get();
  if (t != NULL) {
    if (index < 0) {
      index = t->frames_size + index;
    }
    if (index >= 0 && (size_t) index < t->frames_size) {
#ifdef USE_CORKSCREW
//    return t->frames[index].absolute_pc;                         //~vay2I~
     if (Suse_corkscrew)                                           //~vay2I~
      return t->frames_corkscrew[index].absolute_pc;               //~vay2R~
     else                                                          //~vay2I~
      return t->frames[index];                                     //~vay2I~
#else
      return t->frames[index];
#endif
    }
  }
#else
  (void) index;
#endif
  return 0;
}
//#endif //BBB                                                     //~vay2R~
//***************************************************************************//~vay0I~
/**
 * Get the program counter, given a pointer to a ucontext_t context.
 **/
//***************************************************************************//~vay0I~
static uintptr_t coffeecatch_get_pc_from_ucontext(const ucontext_t *uc) {
#ifdef __arm__
  return uc->uc_mcontext.arm_pc;
#elif (defined(__aarch64__))                                       //~vc08R~
  return uc->uc_mcontext.pc;                                       //~vc08R~
#elif (defined(__x86_64__))
  return uc->uc_mcontext.gregs[REG_RIP];
#elif (defined(__i386))
  return uc->uc_mcontext.gregs[REG_EIP];
#else
#error "Architecture is unknown, please report me!"
#endif
}
//***************************************************************************//~vay0I~
/* Is this module name look like a DLL ?
   FIXME: find a better way to do that...  */
//***************************************************************************//~vay0I~
static int coffeecatch_is_dll(const char *name) {
  size_t i;
  for(i = 0; name[i] != '\0'; i++) {
    if (name[i + 0] == '.' &&
        name[i + 1] == 's' &&
        name[i + 2] == 'o' &&
        ( name[i + 3] == '\0' || name[i + 3] == '.') ) {
      return 1;
    }
  }
  return 0;
}
//***************************************************************************//~vay0I~
/* Extract a line information on a PC address. */
//***************************************************************************//~vay0I~
static void format_pc_address_cb(uintptr_t pc, 
                                 void (*fun)(void *arg, const char *module, 
                                             uintptr_t addr,
                                             const char *function,
                                             uintptr_t offset), void *arg) {
    UTRACEP("@@@@ format_pc_Address pc=%x\n",pc);                  //~vay0R~
  if (pc != 0) {
    Dl_info info;
    void * const addr = (void*) pc;
    /* dladdr() returns 0 on error, and nonzero on success. */
    if (dladdr(addr, &info) != 0 && info.dli_fname != NULL) {
      const uintptr_t near = (uintptr_t) info.dli_saddr;
      const uintptr_t offs = pc - near;
      const uintptr_t addr_rel = pc - (uintptr_t) info.dli_fbase;
      /* We need the absolute address for the main module (?).
         TODO FIXME to be investigated. */
      const uintptr_t addr_to_use = coffeecatch_is_dll(info.dli_fname)
        ? addr_rel : pc;
      fun(arg, info.dli_fname, addr_to_use, info.dli_sname, offs);
    } else {
      fun(arg, NULL, pc, NULL, 0);
    }
  }
}
//***************************************************************************//~vay0I~
typedef struct t_print_fun {
  char *buffer;
  size_t buffer_size;
} t_print_fun;
//***************************************************************************//~vay0I~
static void print_fun(void *arg, const char *module, uintptr_t uaddr,
                      const char *function, uintptr_t offset) {
  t_print_fun *const t = (t_print_fun*) arg;
  char *const buffer = t->buffer;
  const size_t buffer_size = t->buffer_size;
  const void*const addr = (void*) uaddr;
  if (module == NULL) {
    snprintf(buffer, buffer_size, "[at %p]", addr);
  } else if (function != NULL) {
    snprintf(buffer, buffer_size, "[at %s:%p (%s+0x%x)]", module, addr,
             function, (int) offset);
  } else {
    snprintf(buffer, buffer_size, "[at %s:%p]", module, addr);
  }
    UTRACEP("@@@@ print_fun buffser=%s\n",buffer);                 //~vay0R~
}
//***************************************************************************//~vay0I~
/* Format a line information on a PC address. */
//***************************************************************************//~vay0I~
static void format_pc_address(char *buffer, size_t buffer_size, uintptr_t pc) {
  t_print_fun t;
  t.buffer = buffer;
  t.buffer_size = buffer_size;
  format_pc_address_cb(pc, print_fun, &t);
}
//***************************************************************************//~vay0I~
/**
 * Get the full error message associated with the crash.
 */
//***************************************************************************//~vay0I~
const char* coffeecatch_get_message() {
  const int error = errno;
  const native_code_handler_struct* const t = coffeecatch_get();

    UTRACEP("@@@@ get_message t=%p\n",t);                          //~vay0R~
  /* Found valid handler. */
  if (t != NULL) {
    char * const buffer = t->stack_buffer;
    const size_t buffer_len = t->stack_buffer_size;
    size_t buffer_offs = 0;

    const char* const posix_desc =
      coffeecatch_desc_sig(t->si.si_signo, t->si.si_code);

    /* Assertion failure ? */
    if ((t->code == SIGABRT
#ifdef __ANDROID__
        /* See Android BUG #16672:
         * "C assert() failure causes SIGSEGV when it should cause SIGABRT" */
        || (t->code == SIGSEGV && (uintptr_t) t->si.si_addr == 0xdeadbaad)
#endif
        ) && t->expression != NULL) {
      snprintf(&buffer[buffer_offs], buffer_len - buffer_offs,
          "assertion '%s' failed at %s:%d",
          t->expression, t->file, t->line);
      buffer_offs += strlen(&buffer[buffer_offs]);
    }
    /* Signal */
    else {
      snprintf(&buffer[buffer_offs], buffer_len - buffer_offs, "signal %d",
               t->si.si_signo);
      buffer_offs += strlen(&buffer[buffer_offs]);

      /* Description */
      snprintf(&buffer[buffer_offs], buffer_len - buffer_offs, " (%s)",
               posix_desc);
      buffer_offs += strlen(&buffer[buffer_offs]);

      /* Address of faulting instruction */
      if (t->si.si_signo == SIGILL || t->si.si_signo == SIGSEGV) {
        snprintf(&buffer[buffer_offs], buffer_len - buffer_offs, " at address %p",
                 t->si.si_addr);
        buffer_offs += strlen(&buffer[buffer_offs]);
      }
    }

    /* [POSIX] If non-zero, an errno value associated with this signal,
     as defined in <errno.h>. */
    if (t->si.si_errno != 0) {
      snprintf(&buffer[buffer_offs], buffer_len - buffer_offs, ": ");
      buffer_offs += strlen(&buffer[buffer_offs]);
      if (strerror_r(t->si.si_errno, &buffer[buffer_offs],
                     buffer_len - buffer_offs) == 0) {
        snprintf(&buffer[buffer_offs], buffer_len - buffer_offs,
                 "unknown error");
        buffer_offs += strlen(&buffer[buffer_offs]);
      }
    }

    /* Sending process ID. */
    if (t->si.si_signo == SIGCHLD && t->si.si_pid != 0) {
      snprintf(&buffer[buffer_offs], buffer_len - buffer_offs,
               " (sent by pid %d)", (int) t->si.si_pid);
      buffer_offs += strlen(&buffer[buffer_offs]);
    }

    /* Faulting program counter location. */
    if (coffeecatch_get_pc_from_ucontext(&t->uc) != 0) {
      const uintptr_t pc = coffeecatch_get_pc_from_ucontext(&t->uc);
      UTRACEP("@@@@ get_message context pc=%x\n",pc);              //~vay0R~
      snprintf(&buffer[buffer_offs], buffer_len - buffer_offs, " ");
      buffer_offs += strlen(&buffer[buffer_offs]);
      format_pc_address(&buffer[buffer_offs], buffer_len - buffer_offs, pc);
      buffer_offs += strlen(&buffer[buffer_offs]);
    }

    /* Return string. */
    buffer[buffer_offs] = '\0';
    return t->stack_buffer;
  } else {
    /* Static buffer in case of emergency */
    static char buffer[256];
#ifdef _GNU_SOURCE
    return strerror_r(error, &buffer[0], sizeof(buffer));
#else
    const int code = strerror_r(error, &buffer[0], sizeof(buffer));
    errno = error;
    if (code == 0) {
      return buffer;
    } else {
      return "unknown error during crash handler setup";
    }
#endif
  }
}
#if (defined(USE_CORKSCREW))
//***************************************************************************//~vay0I~
typedef struct t_coffeecatch_backtrace_symbols_fun {
  void (*fun)(void *arg, const char *module, uintptr_t addr,
              const char *function, uintptr_t offset);
  void *arg;
} t_coffeecatch_backtrace_symbols_fun;
//***************************************************************************//~vay0I~
static void coffeecatch_backtrace_symbols_fun(void *arg, const backtrace_symbol_t *sym) {
  t_coffeecatch_backtrace_symbols_fun *const bt =
    (t_coffeecatch_backtrace_symbols_fun*) arg;
  const char *symbol = sym->demangled_name != NULL 
    ? sym->demangled_name : sym->symbol_name;
  const uintptr_t rel = sym->relative_pc - sym->relative_symbol_addr;
  bt->fun(bt->arg, sym->map_name, sym->relative_pc, symbol, rel);
}
//***************************************************************************//~vay0I~
#endif
//***************************************************************************//~vay0I~
/**
 * Enumerate backtrace information.
 */
//***************************************************************************//~vay0I~
void coffeecatch_get_backtrace_info(void (*fun)(void *arg,
                                    const char *module,
                                    uintptr_t addr,
                                    const char *function,
                                    uintptr_t offset), void *arg) {
  const native_code_handler_struct* const t = coffeecatch_get();
  UTRACEP("backtrace_info t=%p\n",t);                              //~vay0R~
  if (t != NULL) {
#if (defined(USE_CORKSCREW))
    t_coffeecatch_backtrace_symbols_fun bt;
    bt.fun = fun;
    bt.arg = arg;
//  coffeecatch_backtrace_symbols(t->frames, t->frames_size,       //~vay2I~
//                                coffeecatch_backtrace_symbols_fun, &bt);//~vay2I~
  if (Suse_corkscrew)                                              //~vay2I~
    coffeecatch_backtrace_symbols(t->frames_corkscrew, t->frames_size,coffeecatch_backtrace_symbols_fun, &bt);//~vay2R~
  else                                                             //~vay2I~
  {                                                                //~vay2I~
    size_t i;                                                      //~vay2I~
	UTRACEP("backtrace_info size=%d\n",t->frames_size);            //~vay2I~
    for(i = 0; i < t->frames_size; i++) {                          //~vay2I~
      const uintptr_t pc = t->frames[i];                           //~vay2I~
      UTRACEP("@@@@ backtrace_info unwind i=%d,pc=%x\n",i,pc);     //~vay2I~
      format_pc_address_cb(pc, fun, arg);                          //~vay2I~
    }                                                              //~vay2I~
  }                                                                //~vay2I~
#elif (defined(USE_UNWIND))
    size_t i;
	UTRACEP("backtrace_info size=%d\n",t->frames_size);            //~vay0R~
    for(i = 0; i < t->frames_size; i++) {
      const uintptr_t pc = t->frames[i];
      UTRACEP("@@@@ backtrace_info unwind i=%d,pc=%x\n",i,pc);     //~vay0R~
      format_pc_address_cb(pc, fun, arg);
    }
#else
  (void) fun;
  (void) arg;
#endif
  }
}
//***************************************************************************//~vay0I~
/**
 * Calls coffeecatch_handler_setup(1) to setup a crash handler, mark the
 * context as valid, and return 0 upon success.
 */
//***************************************************************************//~vay0I~
#ifdef AAA                                                         //~4719I~
int coffeecatch_setup() {
  if (coffeecatch_handler_setup(1) == 0) {
    native_code_handler_struct *const t = coffeecatch_get();
    assert(t != NULL);
    t->ctx_is_set = 1;
    return 0;
  } else {
    return -1;
  }
}
#else                                                              //~4719R~
//***************************************************************************//~vay0I~
int jnisigh_setup(JNIEnv *Penv,char *Pfuncid)                      //~vay0R~
{                                                                  //~vay0I~
	if (!Sdosigh)                                                  //~vay0I~
		return 0;                                                  //~vay0R~
  	if (coffeecatch_handler_setup(1) == 0)                         //~vay0R~
    {                                                              //~vay0I~
    	native_code_handler_struct *const t = coffeecatch_get();   //~vay0R~
    	assert(t != NULL);                                         //~vay0R~
    	t->ctx_is_set = 1;                                         //~vay0R~
    	t->threadEnv=Penv;                                         //~vay0R~
    	UstrncpyZ(t->funcid,Pfuncid,MAX_FUNCID);                   //~vay0R~
		UTRACEP("@@@@ jnisigh_setup func=%s,setspecific:key=%x,value(t)=%p,tfuncid=%s\n",Pfuncid,native_code_thread,t,t->funcid);//~vay0R~
    	return 0;                                                  //~vay0R~
  	}                                                              //~vay0R~
  	else                                                           //~vay0I~
    	return -1;                                                 //~vay0R~
}//jnisigh_setup                                                   //~vay0R~
#endif  //AAA                                                      //~4719I~
//***************************************************************************//~vay0M~
/**
 * Calls coffeecatch_handler_cleanup()
 */
//***************************************************************************//~vay0I~
#ifdef AAA                                                         //~vay0I~
void coffeecatch_cleanup() {
  native_code_handler_struct *const t = coffeecatch_get();
  assert(t != NULL);
  t->ctx_is_set = 0;
  coffeecatch_handler_cleanup();
}
#else                                                              //~vay0R~
//***************************************************************************//~vay0I~
void jnisigh_cleanup()                                             //~vay0R~
{                                                                  //~vay0I~
	if (!Sdosigh)                                                  //~vay0I~
		return;                                                    //~vay0R~
  	native_code_handler_struct *const t = coffeecatch_get();       //~vay0I~
  	assert(t != NULL);                                             //~vay0I~
  	t->ctx_is_set = 0;                                             //~vay0I~
  	coffeecatch_handler_cleanup();                                 //~vay0I~
}                                                                  //~vay0I~
//***************************************************************************//~vay0I~
#endif	//AAA                                                      //~vay0I~
#ifdef BBB                                                         //~vay0I~
sigjmp_buf* coffeecatch_get_ctx() {
  native_code_handler_struct* t = coffeecatch_get();
  assert(t != NULL);
  return &t->ctx;
}
void coffeecatch_abort(const char* exp, const char* file, int line) {
  native_code_handler_struct *const t = coffeecatch_get();
  if (t != NULL) {
    t->expression = exp;
    t->file = file;
    t->line = line;
  }
  abort();
}
#endif //BBB                                                       //~vay0I~
//***************************************************************************//~vay0I~
JNIEnv *getthreadenv()                                             //~4719R~
{                                                                  //~4719I~
	JNIEnv *env;                                                   //~4719I~
	native_code_handler_struct* t=coffeecatch_get();               //~4719I~
    env=t->threadEnv;                                              //~4719I~
    return env;                                                    //~4719I~
}                                                                  //~4719I~
//***************************************************************************//~vay0I~
//*when jnisigh_setup failed                                       //~vay0R~
//***************************************************************************//~vay0I~
void jnisigh_catch(JNIEnv *env)                                    //~vay0R~
{                                                                  //~vay0I~
    UTRACE_FLUSH("jnisigh_catch");                                  //~vc08I~
//	native_code_handler_struct *const t = coffeecatch_get();       //~vay2R~
//  const char*const message = coffeecatch_get_message();          //~vay2R~
//  UTRACEP("** NDK exception setup failed for func:%s by %s\n",t->funcid,message);//~vay2R~
    // cancel alarm (otherwise the process die in few seconds)     //~vay0I~
    coffeecatch_cancel_pending_alarm();                            //~vay0I~
    // throw exception                                             //~vay0I~
//  coffeecatch_throw_exception(env);                              //~vay0I~
}//jnisigh_catch                                                   //~vay0R~
//***************************************************************************//~vay0I~
typedef struct t_bt_fun {                                          //~vay0I~
  size_t size;                                                     //~vay0I~
  size_t index;                                                    //~vay0I~
  char *btmsg;                                                     //~vay0I~
} t_bt_fun;                                                        //~vay0I~
//***************************************************************************//~vay0I~
static char* bt_print(const char *function, uintptr_t offset) {    //~vay0I~
  if (function != NULL) {                                          //~vay0I~
    char buffer[256];                                              //~vay0I~
    snprintf(buffer, sizeof(buffer), "%s+%04x", function,offset);  //~vay2R~
    return strdup(buffer);                                         //~vay0I~
  } else {                                                         //~vay0I~
    return "<unknown>";                                            //~vay0I~
  }                                                                //~vay0I~
}                                                                  //~vay0I~
//***************************************************************************//~vay0I~
static char* bt_addr(uintptr_t addr) {                             //~vay0I~
  char buffer[32];                                                 //~vay0I~
  snprintf(buffer, sizeof(buffer), "%08x",addr);                   //~vay2R~
  return strdup(buffer);                                           //~vay0I~
}                                                                  //~vay0I~
//***************************************************************************//~vay0I~
#define IS_VALID_CLASS_CHAR(C) ( \
  ((C) >= 'a' && (C) <= 'z')     \
  || ((C) >= 'A' && (C) <= 'Z')  \
  || ((C) >= '0' && (C) <= '9')  \
  || (C) == '_'                  \
  )                                                                //~vay0I~
//***************************************************************************//~vay0I~
static char* bt_module(const char *module) {                       //~vay0I~
  if (module != NULL) {                                            //~vay0I~
    size_t i;                                                      //~vay0I~
    char *copy;                                                    //~vay0I~
    if (*module == '/') {                                          //~vay0I~
      module++;                                                    //~vay0I~
    }                                                              //~vay0I~
    copy = strdup(module);                                         //~vay0I~
    /* Pseudo-java-class. */                                       //~vay0I~
    for(i = 0; copy[i] != '\0'; i++) {                             //~vay0I~
      if (copy[i] == '/') {                                        //~vay0I~
        copy[i] = '.';                                             //~vay0I~
      } else if (!IS_VALID_CLASS_CHAR(copy[i])) {                  //~vay0I~
        copy[i] = '_';                                             //~vay0I~
      }                                                            //~vay0I~
    }                                                              //~vay0I~
    return copy;                                                   //~vay0I~
  } else {                                                         //~vay0I~
    return "<unknown>";                                            //~vay0I~
  }                                                                //~vay0I~
}                                                                  //~vay0I~
//***************************************************************************//~vay0I~
void jnisigh_bt_fun(void *arg, const char *module, uintptr_t addr, //~vay0I~
                   const char *function, uintptr_t offset)         //~vay0R~
{                                                                  //~vay0I~
  	t_bt_fun *const t = (t_bt_fun*) arg;                           //~vay0I~
	char *pmod=bt_module(module);                                  //~vay0I~
	char *paddr=bt_addr(addr);                                     //~vay0I~
	char *pfnm=bt_print(function,offset);                          //~vay0I~
  	char *ptrace=malloc(strlen(pmod)+strlen(paddr)+strlen(pfnm)+128);//~vay2R~
//  const int lineNumber = function != NULL ? 0 : -2;  /* "-2" is "inside JNI code" *///~vay2R~
  	UTRACEP("@@@@ bt_fun method=%s,function=%s,offset=%x,lineno=%d\n",module,function,offset);//~vay2R~
  	int curlen=sprintf(ptrace,"#%02d:%s,%s:%s\n",t->index,paddr,pmod,pfnm);//~vay2R~
    t->index++;                                                    //~vay0I~
    if (t->btmsg)                                                  //~vay0I~
    {                                                              //~vay0I~
    	int oldlen=strlen(t->btmsg);                               //~vay0I~
    	char *newbt=malloc(oldlen+curlen+4);                       //~vay0I~
        sprintf(newbt,"%s%s",t->btmsg,ptrace);                     //~vay2R~
        free(t->btmsg);                                            //~vay0I~
        free(ptrace);                                              //~vay0I~
        t->btmsg=newbt;                                            //~vay0I~
    }                                                              //~vay0I~
    else                                                           //~vay0I~
    	t->btmsg=ptrace;                                           //~vay0I~
  	UTRACEP("@@@@ bt_fun ptrace=%s",t->btmsg);                     //~vay0R~
}                                                                  //~vay0I~
//*********************************************************        //~vay0M~
//*like as func_catch                                              //~vay0M~
//*********************************************************        //~vay0M~
void  jnisigh_popupdialog(native_code_handler_struct *t)           //~vay0I~
{                                                                  //~vay0M~
    char *dlgmsg,*btmsg,*uemsg;                                    //~vay0R~
    int msgll,uemsglen;                                            //~vay0R~
//****************************                                     //~vay0I~
//  alarm(0); //@@@@test                                           //~vay2R~
   	const char*const message = coffeecatch_get_message();          //~vay0M~
    UTRACEP("@@@@ jnisigh_popupdialog msg:%s\n", message);         //~vay0R~
    // cancel alarm (otherwise the process die in few seconds)     //~vay0M~
    coffeecatch_cancel_pending_alarm();                            //~vay0M~
    // throw exception                                             //~vay0M~
	const ssize_t bt_size = coffeecatch_get_backtrace_size();      //~vay0I~
    /* Can we produce a stack trace ? */                           //~vay0I~
  	if (bt_size > 0)                                               //~vay0I~
    {                                                              //~vay0I~
      	t_bt_fun t;                                                //~vay0I~
      	t.index = 0;                                               //~vay0I~
      	t.size = bt_size;                                          //~vay0I~
      	t.btmsg=0;                                                 //~vay0I~
      	coffeecatch_get_backtrace_info(jnisigh_bt_fun,&t);         //~vay0I~
        btmsg=t.btmsg;                                             //~vay0I~
    }                                                              //~vay0I~
    else                                                           //~vay0I~
    	btmsg="";                                                  //~vay0I~
    uemsg=ugeterrmsg();                                            //~vay0I~
    msgll=MAX_FUNCID+strlen(message)+strlen(btmsg);                //~vay0I~
    if (uemsg)                                                     //~vay0I~
    	uemsglen=strlen(uemsg);                                    //~vay0I~
    else                                                           //~vay0I~
    	uemsglen=0;                                                //~vay0I~
    msgll+=uemsglen;                                               //~vay0I~
    dlgmsg=malloc(msgll+32);                                       //~vay0R~
    if (uemsglen)                                                  //~vay0I~
    	sprintf(dlgmsg,"func=%s,\n%s;\n%s;\n%s",t->funcid,uemsg,message,btmsg);//~vay0I~
    else                                                           //~vay0I~
    	sprintf(dlgmsg,"func=%s,\n%s;\n%s",t->funcid,message,btmsg);//~vay0R~
  	UTRACEP("@@@@ JniCrash Info:%s\n",dlgmsg);                     //~vay0R~
    c2j_msgboxACRA((char *)dlgmsg);                                //~vay0R~
}//jnisigh_popupdialog                                             //~vay0I~
//*********************************************************        //~vay0I~
//*for c2j                                                         //~vay0I~
//*save current funcid                                             //~vay0I~
//*********************************************************        //~vay0I~
void  jnisigh_handler_save(JNIEnv *Penv,char *Pc2jmethod,char *Pj2cfuncid)//~vay0R~
{                                                                  //~vay0I~
//****************************                                     //~vay0I~
	if (!Sdosigh)                                                  //~vay0I~
		return;                                                    //~vay0R~
  	UTRACEP("@@@@ c2j save:%s\n",Pc2jmethod);                      //~vay0M~
	native_code_handler_struct* t=coffeecatch_get();               //~vay0I~
	if (t!=NULL)                                                   //~vay0I~
		snprintf(Pj2cfuncid,MAX_FUNCID,"%s->%s",t->funcid,Pc2jmethod);//~vay0I~
    else                                                           //~vay0I~
		snprintf(Pj2cfuncid,MAX_FUNCID,"unknown->%s",Pc2jmethod);  //~vay0I~
  	UTRACEP("@@@@ c2j save:%s\n",Pj2cfuncid);                      //~vay0R~
	jnisigh_cleanup();                                             //~vay0I~
}//                                                                //~vay0I~
//*********************************************************        //~vay0I~
//*for c2j                                                         //~vay0I~
//*restor current funcid                                           //~vay0I~
//*********************************************************        //~vay0I~
void  jnisigh_handler_restore(JNIEnv *Penv,char *Pj2cfuncid)       //~vay0R~
{                                                                  //~vay0I~
	char funcid[MAX_FUNCID];                                       //~vay0I~
//****************************                                     //~vay0I~
	if (!Sdosigh)                                                  //~vay0I~
		return;                                                    //~vay0I~
  	UTRACEP("@@@@ c2j restore:%s\n",Pj2cfuncid);                   //~vay0R~
	char *pc=strrchr(Pj2cfuncid,'-');                              //~vay0R~
    if (pc)                                                        //~vay0I~
    {                                                              //~vay0I~
    	*pc=0;                                                     //~vay0R~
		snprintf(funcid,MAX_FUNCID,"%s",Pj2cfuncid);               //~vay0R~
    }                                                              //~vay0I~
    else                                                           //~vay0I~
		snprintf(funcid,MAX_FUNCID,"%s",Pj2cfuncid);               //~vay0I~
	jnisigh_setup(Penv,funcid);                                    //~vay0R~
}//                                                                //~vay0I~
//*********************************************************        //~vay0I~
//*for uerrexit                                                    //~vay0I~
//*abort after cleanup AxeChild show uerrmsg                       //~vay0I~
//*********************************************************        //~vay0I~
void  jnisigh_uerrexit(char *Pmsg)                                 //~vay0R~
{                                                                  //~vay0I~
  	UTRACEP("@@@@ jnisigh_uerrexit Abort after handler cleanup:%s\n",Pmsg);//~vay0R~
	jnisigh_cleanup();                                             //~vay0I~
	abort();                                                       //~vay0I~
}//                                                                //~vay0I~
