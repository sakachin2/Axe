//*CID://+vag1R~:                                   update#=  305; //~vag1R~
//**************************************************************** //~1822R~
//vag1:120724 (Axe)warning no write permisson from unod for sdcard(permission 075)//~vag1I~
//		      sdcard group is sdcard_rm and manifest permission set application groupid to it//~vag1I~
//v6dg:120220 (Axe)ftp downloaded file attr is 075(sdcard is read only and FAT); change to private dir//~v6dgI~
//**************************************************************** //~v6dgI~
//*utility for ulib                                                //~1A23R~
//*prototype is defined on ulibarm                                 //~1A23I~
//**************************************************************** //~1610I~
#include <stdlib.h>                                                //~1A12R~
#include <stdio.h>                                                 //~1A12I~
#include <string.h>                                                //~1614I~
//*********************                                            //~1702R~
#include "jnig.h"                                                  //~1A23I~
#include "jnia.h"                                                  //~1616I~
#include "jnic2j.h"                                                //~1A23R~
#include <android/log.h>                                           //~1616M~
                                                                   //~1616I~
#include <ulib.h>                                                  //~1614I~
#include <ustring.h>                                               //~1C22I~
#include <utrace.h>                                                //~1A23I~
                                                                   //~1717M~
//********************************                                 //~1610I~
//*******************************************************          //~v6a0M~//~1A23I~
//*chk file path is on sdcard                                      //~1A23R~
//*******************************************************          //~v6a0M~//~1A23I~
int isonsdpath(char *Ppath)                                        //~v6a0M~//~1A23I~
{                                                                  //~v6a0M~//~1A23I~
	int len,rc=0,lenp;                                             //~v6a0M~//~1A23I~
    static int Ssdpathlen=0;                                       //~vag1I~
    char *pc;                                                      //~vag1I~
//**********************                                           //~v6a0M~//~1A23I~
	lenp=strlen(Ppath);                                             //~v6a0M~//~1A23R~
    if (Gjnisdpath)                                                //~v6a0M~//~1A23I~
    {                                                              //~v6a0M~//~1A23I~
//      len=strlen(Gjnisdpath);                                   //~v6a0M~//~vag1R~
        if (!Ssdpathlen)                                           //~vag1I~
        {                                                          //~vag1I~
            pc=strstr(Gjnisdpath,"sdcard");                        //~vag1I~
            if (pc)                                                //~vag1I~
            	len=PTRDIFF(pc,Gjnisdpath)+6;                      //~vag1R~
            else                                                   //~vag1I~
            	len=strlen(Gjnisdpath);                            //~vag1R~
            Ssdpathlen=len;                                        //~vag1I~
        }                                                          //~vag1I~
        else                                                       //~vag1I~
        	len=Ssdpathlen;                                        //~vag1I~
        if (len<=lenp                                              //~1A23R~
        &&  (*(Ppath+len)=='/'||*(Ppath+len)==0)                   //~1A23I~
        &&  !memcmp(Ppath,Gjnisdpath,len)                          //~1A23R~
        )                                                          //~v6a0M~//~1A23I~
        	rc=1;                                                  //~v6a0M~//~1A23I~
    }                                                              //~v6a0M~//~1A23I~
    return rc;                                                     //~v6a0M~//~1A23I~
}//isonsdpath                                                      //~1A23I~
//*******************************************************          //~1A23I~
//*chk file path is on vfat  filesystem                            //~1A23I~
//*******************************************************          //~1A23I~
int isonvfat(char *Ppath)                                          //~1A23I~
{                                                                  //~1A23I~
	static char *Svfatlist;                                        //~1A23I~
    static int Sinitsw=0,Slistsz=0;                                //~1A23R~
    int lenp,len=0,rc=0;                                           //~1A23I~
    char *pc,*pce,*pc2;                                            //~1A23R~
//*********************                                            //~1A23I~
	if (!Sinitsw)                                                  //~1A23I~
    {                                                              //~1A23I~
    	Slistsz=c2j_getvfatlist(&Svfatlist);                       //~1A23R~
        Sinitsw=1;                                                 //~1A23I~
    }                                                              //~1A23I~
    if (!Slistsz)                                                  //~1A23I~
    	return 0;                                                  //~1A23I~
    lenp=strlen(Ppath);                                            //~1A23I~
    for (pc=Svfatlist,pce=pc+Slistsz;pc<pce;pc+=len+1)             //~1A23I~
    {                                                              //~1A23I~
    	pc2=strchr(pc,'\n');                                       //~1A23R~
        if (!pc2) 	//invalid format(lie split by "\n")            //~1A23I~
        	break;                                                 //~1A23I~
    	len=(ULONG)pc2-(ULONG)pc;                                  //~1A23R~
        if (len<=lenp                                              //~1A23R~
        &&  (*(Ppath+len)=='/'||*(Ppath+len)==0)                   //~1A23I~
        &&  !memcmp(Ppath,pc,len)                                  //~1A23I~
        )                                                          //~1A23I~
        {                                                          //~1A23I~
            rc=1;                                                  //~1A23I~
        	break;                                                 //~1A23I~
        }                                                          //~1A23I~
    }                                                              //~1A23I~
    UTRACEP("isonvfat %s rc=%d\n",Ppath,rc);                       //~1A23I~//+vag1M~
    return rc;                                                     //~1A23I~
}//isonvfat                                                        //~v6dgR~
//*******************************************************          //~1C22I~
//*return icu dir in /data/data/com.xe.Axe/file to copy cnv to icudtxxl//~1C22I~
//*******************************************************          //~1C22I~
int jniu_geticudir(int Popt,char *Picudir,char *Pswfile)           //~1C22I~
{                                                                  //~1C22I~
	char *pc,*pc2;                                                 //~1C22R~
    int len;                                                       //~1C22I~
//***************                                                  //~1C22I~
	*Picudir=0;                                                    //~1C22I~
	*Pswfile=0;                                                    //~1C22I~
	pc=Gicuswfile;	// /data/data/com.xe.Axe/files/icu/Installedsw //~1C22I~
    if (!pc)                                                       //~1C22I~
    	return 4;                                                  //~1C22I~
    strcpy(Pswfile,pc);                                            //~1C22I~
    pc2=strrchr(pc,'/');                                           //~1C22I~
    if (!pc2)                                                      //~1C22I~
        return 4;                                                  //~1C22I~
    len=(ULONG)pc2-(ULONG)pc;                                      //~1C22I~
    UmemcpyZ(Picudir,pc,len);                                      //~1C22R~
    return 0;                                                      //~1C22I~
}//jniu_geticudir                                                  //~1C22I~
//*******************************************************          //~v6dgI~
char *jniu_getPrivatePath(int Popt)                               //~v6bqI~//~v6dgI~
{                                                                  //~v6dgI~
    return Gjniprivatepath;                                        //~v6dgI~
}                                                                  //~v6dgI~
