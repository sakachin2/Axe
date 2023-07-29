//*CID://+vc58R~:                             update#=  437;       //+vc58R~
//************************************************************* //~5825I~
//* redirect exit() to pthread_exit() to protect iconv_so.so exit cause process exit                 //~v065R~//~v77FR~//+vc58R~
//*************************************************************    //~v022I~
#include <stdio.h>                                              //~5825I~
#include <stdlib.h>                                             //~5825I~
#include <pthread.h>                                           //~v77DI~//~v77FR~
//*********************************************************************//~v77FI~
//*for exit() of xsub tool executed for >Android10(dlopen,dlsym,call)//~v77DI~
//*redirect by #define on ulibarm.h                                //~v77DI~
//*********************************************************************//~v77DI~
void exitPthread(int PexitCode)                                    //~v77DR~//+vc58R~
{                                                                  //~v77DI~
    static int SexitCode;                                              //~v77DI~
//**************                                                   //~v77DI~
    SexitCode=PexitCode;                                       //~v77DR~//+vc58R~
	pthread_exit(&SexitCode);                                  //~v77DR~//+vc58R~
}                                                                  //~v77DI~
