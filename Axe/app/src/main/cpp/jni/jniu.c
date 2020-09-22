//*CID://+vc1iR~:                                   update#=  318; //~vc1iR~
//**************************************************************** //~1822R~
//vc1i 2020/06/22 display not /emulated/.. but /sdcard to header CWD=//~vc1iI~
//vc1f 2020/06/20 ARM;chk sdcard writable                          //~vc1fI~
//vag1:120724 (Axe)warning no write permisson from unod for sdcard(permission 075)//~vag1I~
//		      sdcard group is sdcard_rm and manifest permission set application groupid to it//~vag1I~
//v6dg:120220 (Axe)ftp downloaded file attr is 075(sdcard is read only and FAT); change to private dir//~v6dgI~
//**************************************************************** //~v6dgI~
//*utility for ulib/xe                                                //~1A23R~//~vag1R~
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
static int swAltSD;                                                //~vc1fI~
static int Ssdpathlen=0;                                           //~vc1fI~
//*******************************************************          //~v6a0M~//~1A23I~
//*chk file path is on sdcard                                      //~1A23R~
//*rc 1:on sd path, 2:/sdcard permission not granted               //~vc1fI~
//*******************************************************          //~v6a0M~//~1A23I~
int isonsdpath(char *Ppath)                                        //~v6a0M~//~1A23I~
{                                                                  //~v6a0M~//~1A23I~
	int len,rc=0,lenp;                                             //~v6a0M~//~1A23I~
//  static int Ssdpathlen=0;                                       //~vag1I~//~vc1fR~
//  char *pc;                                                      //~vag1I~
//**********************                                           //~v6a0M~//~1A23I~
	lenp=(int)strlen(Ppath);                                             //~v6a0M~//~1A23R~//~vc1fR~
    swAltSD=0;                                                     //~vc1fI~
    if (GjnisdRoot)	//confirmed same rwx attr as /emulated         //~vc1fI~
    {                                                              //~vc1fM~
    	len=(int)strlen(GjnisdRoot);                               //~vc1fI~
        if (len<=lenp                                              //~vc1fM~
        &&  (*(Ppath+len)=='/'||*(Ppath+len)==0)                   //~vc1fM~
        &&  !memcmp(Ppath,GjnisdRoot,(size_t)len)                  //~vc1fI~
        )                                                          //~vc1fM~
        {                                                          //~vc1fM~
		  swAltSD=1;                                               //~vc1fI~
          if (GaxeStatus & AXES_NOPERMISSION_EXTERNAL_STIORAGE_WRITE)//~vc1fM~
            rc=2;                                                  //~vc1fM~
          else                                                     //~vc1fM~
        	rc=1;                                                  //~vc1fM~
        }                                                          //~vc1fM~
    }                                                              //~vc1fM~
  if (!swAltSD)                                                    //~vc1fI~
//  if (Gjnisdpath)                                                //~v6a0M~//~1A23I~//~vc1iR~
    if (GjnisdRootPath)                                            //~vc1iI~
    {                                                              //~v6a0M~//~1A23I~
//      len=strlen(Gjnisdpath);                                   //~v6a0M~//~vag1R~
//        if (!Ssdpathlen)                                           //~vag1I~//~vc1fR~
//        {                                                          //~vag1I~//~vc1fR~
//            pc=strstr(Gjnisdpath,"sdcard");                        //~vag1I~//~vc1fR~
//            if (pc)                                                //~vag1I~//~vc1fR~
//                len=PTRDIFF(pc,Gjnisdpath)+6;                      //~vag1R~//~vc1fR~
//            else                                                   //~vag1I~//~vc1fR~
//                len=strlen(Gjnisdpath);                            //~vag1R~//~vc1fR~
//            Ssdpathlen=len;                                        //~vag1I~//~vc1fR~
//        }                                                          //~vag1I~//~vc1fR~
//        else                                                       //~vag1I~//~vc1fR~
//            len=Ssdpathlen;                                        //~vag1I~//~vc1fR~
        if (Ssdpathlen)                                            //~vc1fI~
			len=Ssdpathlen;                                        //~vc1fI~
        else                                                       //~vc1fI~
//          Ssdpathlen=len=(int)strlen(Gjnisdpath);                //~vc1fI~//~vc1iR~
            Ssdpathlen=len=(int)strlen(GjnisdRootPath);            //~vc1iI~
        if (len<=lenp                                              //~1A23R~
        &&  (*(Ppath+len)=='/'||*(Ppath+len)==0)                   //~1A23I~
//      &&  !memcmp(Ppath,Gjnisdpath,(size_t)len)                          //~1A23R~//~vc1fR~//~vc1iR~
        &&  !memcmp(Ppath,GjnisdRootPath,(size_t)len)              //~vc1iI~
        )                                                          //~v6a0M~//~1A23I~
        {                                                          //~vc1fI~
		  swAltSD=2;                                               //~vc1iI~
          if (GaxeStatus & AXES_NOPERMISSION_EXTERNAL_STIORAGE_WRITE)//~vc1fI~
            rc=2;                                                  //~vc1fI~
          else                                                     //~vc1fI~
        	rc=1;                                                  //~v6a0M~//~1A23I~
        }                                                          //~vc1fI~
    }                                                              //~v6a0M~//~1A23I~
    UTRACEP("%s:rc=%d,in=%s,sdRootPath=%s,sdRoot=%s\n",UTT,rc,Ppath,GjnisdRootPath,GjnisdRoot);//~vc1iR~
    return rc;                                                     //~v6a0M~//~1A23I~
}//isonsdpath                                                      //~1A23I~
//*******************************************************          //~vc1iI~
int ufullpathAltSD(int Popt,char *Ppath,char *Pout)                //~vc1iR~
{                                                                  //~vc1iI~
	int rc;                                                       //~vc1iI~
//**********************                                           //~vc1iI~
	rc=isonsdpath(Ppath);                                          //~vc1iI~
    if (rc) //on sdpath 1:sdpath,2:not granted                     //~vc1iR~
    {                                                              //~vc1iI~
        if (swAltSD==2 && GjnisdRoot)   //longname                 //~vc1iR~
        {                                                          //~vc1iI~
    		strcpy(Pout,GjnisdRoot);                                //~vc1iI~
        	strcat(Pout,Ppath+Ssdpathlen);                          //~vc1iI~
            if (Popt & UFPASDO_SETRC_CHANGED)                      //~vc1iI~
                rc|=UFPASDRC_RC_CHANGED;                           //~vc1iI~
        }                                                          //~vc1iI~
    }                                                              //~vc1iI~
    else                                                           //~vc1iI~
      if (Pout!=Ppath)                                             //~vc1iI~
    	strcpy(Pout,Ppath);                                        //~vc1iI~
    UTRACEP("%s:rc=%d,swAltSD=%d,in=%s,out=%s\n",UTT,rc,swAltSD,Ppath,Pout);//~vc1iR~
    return rc;                                                     //~vc1iI~
}//ufullpathAltSD                                                  //+vc1iR~
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
    UTRACEP("isonvfat %s rc=%d\n",Ppath,rc);                       //~1A23I~//~vag1M~
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
