//*CID://+vby1R~:                             update#=    2;       //~vby1I~
//**************************************************************** //~vby1I~
//vby1:230323 sys/timeb.h is not found on ARM                      //~vby1I~
//**************************************************************** //~vby1I~
#ifndef _TIMEB_DEFINED                                             //~vby1I~
struct timeb{                                                      //~0610I~
//	long time;                                                     //~0610I~//+vby1R~
//	time_t time;                                                   //+vby1R~
  	long time;                                                     //+vby1I~
    short millitm;                                                 //~0610I~
    short timezone;                                                //~0610R~
    short dstflag;                                                 //~0610I~
    };                                                             //~0610I~
#define _TIMEB_DEFINED                                             //~vby1I~
#endif                                                             //~vby1I~
int ftime(struct timeb* buff);                                     //~0610R~
