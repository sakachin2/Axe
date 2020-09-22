struct timeb{                                                      //~0610I~
	long time;                                                     //~0610I~
    short millitm;                                                 //~0610I~
    short timezone;                                                //~0610R~
    short dstflag;                                                 //~0610I~
    };                                                             //~0610I~
int ftime(struct timeb* buff);                                     //+0610R~
