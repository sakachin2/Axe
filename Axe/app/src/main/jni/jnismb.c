//*CID://+vay0R~:                                   update#= 139;  //~vay0R~
//****************************************************************
//vay0:140710 (Axe)jni exception handling                          //~vay0I~
//vak2:130822 Axe:ndk-r9 warning                                   //~vak2I~
//v6dh:120224 (Axe)there is a case smb user is null                //~vadhI~
//vac6:120217 (Axe)samba share support using jcifs 3.17
//****************************************************************
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <dirent.h>                                                //~vac6I~
//*********************
#include "jnig.h"
#include "jnia.h"
#include <android/log.h>

#include "AxeJNI_sig.h"
#include "jnij2c.h"
#include "jnisigh.h"                                               //~vay0I~

#include <ulib.h>
#include <utrace.h>
#include <ualloc.h>
#include <uque.h>                                                  //~vac6I~
#include <ufile.h>                                                 //~vac6I~
#include <uftp.h>                                                  //~vac6M~
#include <ufile1f.h>                                               //~vac6M~
#include <ufile1l.h>                                               //~vac6I~
#include <uftp3.h>                                                 //~vac6M~
#include <ustring.h>                                               //~vac6I~
#include <uerr.h>                                                  //~vac6I~
#include <ufemsg.h>                                                //~vac6I~

#include "jnic2ju.h"                                               //~vac6M~
//********************************
typedef struct _USMBJCMDQE {                                       //~vac6I~
                            UQUEE      USJCQEqe;                   //~vac6I~
                            int        USJCQEcmdlen;               //~vac6I~
                            char       USJCQEcmd[1];               //~vac6R~
                            } USMBJCMDQE,*PUSMBJCMDQE;             //~vac6I~
#define USMBJCMDQESZ sizeof(USMBJCMDQE)                            //~vac6I~
//********************************
#define MAX_CPATH (_MAX_PATH+_MAX_PATH)                            //~vac6I~
//********************************                                 //~vac6I~
int smb_canonicalpath(int Popt,PUFTPHOST Ppuftph,char *Pfpath,char *Pcpath);//~vac6R~
#define CPO_SERVER  0x01                                           //~vac6R~
#define CPO_SHARE   0x02                                           //~vac6R~
#define CPO_NOHID   0x04        //no hostid in parameter           //~vac6I~
//********************************                                 //~vac6I~
int jnismb_chkerr(int Popt,int Prc);                               //~vac6R~
#define CKEO_NFMSG            0x01                                 //~vac6R~
#define CKEO_EXCEPTIONONLY    0x02                                 //~vac6I~
//*******************************************************************//~vac6I~
//url format fullpath                                              //~vac6I~
//rc:length                                                        //~vac6I~
//*******************************************************************//~vac6I~
int smb_canonicalpath(int Popt,PUFTPHOST Ppuftph,char *Pfpath,char *Pcpath)//~vac6R~
{                                                                  //~vac6I~
    char *pc,*puser;                                                      //~vac6R~//~vadhR~
    int rc;                                                        //~vac6R~
//****************************                                     //~vac6I~
	puser=Ppuftph->UFTPHuser;                                      //~vadhI~
	if (Popt & CPO_SERVER)                                         //~vac6R~
    {                                                              //~vadhI~
      if (*puser)                                                  //~vadhI~
	    rc=sprintf(Pcpath,"smb://%s:%s@%s",puser,Ppuftph->UFTPHpswd,Ppuftph->UFTPHipad);//~vac6I~//~vadhR~
      else                                                         //~vadhI~
	    rc=sprintf(Pcpath,"smb://%s",Ppuftph->UFTPHipad);          //~vadhI~
    }                                                              //~vadhI~
    else                                                           //~vac6I~
	if (Popt & CPO_SHARE)                                          //~vac6R~
    {                                                              //~vac6I~
//        pc=Pfpath+Ppuftph->UFTPHhostnmlen+1;                     //~vac6R~
//        pc2=strchr(pc+1,'/');                                    //~vac6R~
//        if (pc2)                                                 //~vac6R~
//        {                                                        //~vac6R~
//            len=(ULONG)pc2-(ULONG)pc+1;                          //~vac6R~
//            rc=sprintf(Pcpath,"smb://%s:%s@%s",Ppuftph->UFTPHuser,Ppuftph->UFTPHpswd,Ppuftph->UFTPHipad);//~vac6R~
//            UmemcpyZ(Pcpath+rc,pc,len);                          //~vac6R~
//        }                                                        //~vac6R~
//        else                                                     //~vac6R~
//            rc=sprintf(Pcpath,"smb://%s:%s@%s%s/",Ppuftph->UFTPHuser,Ppuftph->UFTPHpswd,Ppuftph->UFTPHipad,pc);//~vac6R~
      if (*puser)                                                  //~vadhI~
	    rc=sprintf(Pcpath,"smb://%s:%s@%s/%s/",puser,Ppuftph->UFTPHpswd,Ppuftph->UFTPHipad,Ppuftph->UFTPHshare);//~vac6I~//~vadhR~
      else                                                         //~vadhI~
	    rc=sprintf(Pcpath,"smb://%s/%s/",Ppuftph->UFTPHipad,Ppuftph->UFTPHshare);//~vadhI~
    }                                                              //~vac6I~
    else                                                           //~vac6I~
    {                                                              //~vac6I~
		if (Popt & CPO_NOHID)                                      //~vac6I~
			pc=Pfpath;                                             //~vac6I~
        else                                                       //~vac6I~
			pc=Pfpath+Ppuftph->UFTPHhostnmlen+1;	//skip ":"     //~vac6R~
        if (*pc=='/')                                              //~vac6I~
        	pc++;                                                  //~vac6I~
      if (*puser)                                                  //~vadhI~
    	rc=sprintf(Pcpath,"smb://%s:%s@%s/%s/%s",puser,Ppuftph->UFTPHpswd,Ppuftph->UFTPHipad,Ppuftph->UFTPHshare,pc);//~vac6R~//~vadhR~
      else                                                         //~vadhI~
    	rc=sprintf(Pcpath,"smb://%s/%s/%s",Ppuftph->UFTPHipad,Ppuftph->UFTPHshare,pc);//~vadhI~
    }                                                              //~vac6I~
    UTRACEP("smb_canonicalpath node=%s,fpath=%s,cpath=%s\n",Ppuftph->UFTPHhost,Pfpath,Pcpath);//~vac6R~
    return rc;                                                     //~vac6I~
}//smb_canonicalpath                                               //~vac6R~
//*******************************************************************//~vac6I~
//udirlist                                                         //~vac6I~
//*******************************************************************//~vac6I~
void smb_freestrarray(int Pctr,char **Plist)                       //~vac6I~
{                                                                  //~vac6I~
    char *pc,**ppc;                                                //~vac6I~
    int ii;                                                        //~vac6I~
//****************************                                     //~vac6I~
	if (!Plist)                                                    //~vac6I~
    	return;                                                    //~vac6I~
	for (ii=0,ppc=Plist;ii<Pctr;ii++)                              //~vac6R~
    {                                                              //~vac6I~
    	pc=*ppc++;                                                 //~vac6I~
        if (pc)                                                    //~vac6I~
    	    ufree(pc);                                             //~vac6R~
    }                                                              //~vac6I~
    ufree(Plist);                                                  //~vac6I~
    return;                                                        //~vac6I~
}//smb_freestrarray                                                //~vac6I~
//*******************************************************************//~vadhI~
//nfmsg                                                            //~vadhI~
//*******************************************************************//~vadhI~
int smb_dirlistnfmsg(int Prc,char *Pfpath,UINT Pattr)              //~vadhR~
{                                                                  //~vadhI~
    int rc;                                                        //~vadhI~
//****************                                                 //~vadhI~
    rc=Prc;                                                        //~vadhI~
    if (Prc==ERROR_NO_MORE_FILES)                             //~v5d9R~//~vadhI~
    {                                                              //~vadhI~
        if (!(Pattr & FILE_DIRECTORY))                         //~v62bR~//~vadhI~
            rc=ufileerrnofileentry(Pfpath,ERROR_FILE_NOT_FOUND);  //~v62bI~//~vadhI~
        else                                                   //~v62bI~//~vadhI~
            rc=ufileerrnoentry(Pfpath,ERROR_FILE_NOT_FOUND);      //~v5d9I~//~vadhI~
    }                                                              //~vadhI~
    else                                                           //~vadhI~
    if (Prc==ENOENT) //cd faile                                //~v6daR~//~vac6I~//~vadhR~
    {                                                        //~v6daR~//~vac6I~//~vadhI~
        ufilenotfound(Pfpath,Prc);                        //~v6daR~//~vac6I~//~vadhI~
    }                                                        //~v6daR~//~vac6I~//~vadhI~
    return rc;                                                     //~vadhR~
}                                                                  //~vadhI~
//*******************************************************************//~vac6I~
//udirlist                                                         //~vac6I~
//*******************************************************************//~vac6I~
int smb_udirlistsub(int Popt,PUFTPHOST Ppuftph,PUDIRLIST *Pppudl,int Pfileno,char **Ppname,int *Pattr,jlong *Psize,int* Ptime)//~vac6R~
{                                                                  //~vac6I~
	PUDIRLIST pudl;                                                //~vac6I~
    int *pattr,fileno,sz,len;                                      //~vac6R~
    jlong *psize;                                                  //~vac6R~
    int *ptime;                                                    //~vac6I~
    char *pc,**pname;                                              //~vac6R~
//****************************                                     //~vac6I~
	sz=sizeof(UDIRLIST)*(Pfileno+1);                               //~vac6I~
	pudl=umalloc(sz);                                              //~vac6I~
    memset(pudl,0,sz);                                             //~vac6I~
    *Pppudl=pudl;                                                  //~vac6I~
	for (fileno=0,pname=Ppname,pattr=Pattr,psize=Psize,ptime=Ptime;fileno<Pfileno;fileno++,pudl++)//~vac6R~
    {                                                              //~vac6I~
    	pc=*pname++;                                               //~vac6R~
        len=strlen(pc);                                            //~vac6I~
        if (len>0 && *(pc+len-1)=='/')                             //~vac6R~
            len--;                                                 //~vac6I~
        len=min(len,sizeof(pudl->name)-1);                         //~vac6I~
        UmemcpyZ(pudl->name,pc,len);                               //~vac6R~
    	pudl->attr=*pattr++;                                       //~vac6R~
    	pudl->size=(FILESZT)*psize++;                              //~vac6R~
    	pudl->mtime=*ptime++;                                      //~vac6R~
        ufilegetftime(&pudl->time,&pudl->date,pudl->mtime);        //~vac6R~
        uftp3smb_mode2attr_win(0,Ppuftph,pudl);                    //~vac6I~
    }                                                              //~vac6I~
	UTRACEP("smb_udirlistsub Pfileno=%d,fileno=%d\n",Pfileno,fileno);//~vac6I~
    return fileno;                                                 //~vac6I~
}//smb_udirlistsub                                                 //~vac6R~
//*******************************************************************//~vac6I~
int jnismb_udirlist(int Popt,PUFTPHOST Ppuftph,int *Ppentno,char *Pfpath,ULONG Pattr,PUDIRLIST *Pppudl)//~vac6R~
{                                                                  //~1718I~//~vac6I~
    static jmethodID staticMethod_smb_udirlist;                   //~1718R~//~vac6R~
    JNIEnv *penv;                                                  //~1718I~//~vac6I~
    char cpath[MAX_CPATH];                                         //~vac6I~
    jstring jpath;                                                 //~vac6I~
    char **namelist=0;                                             //~vac6R~
    jlong *sizelist;                                               //~vac6R~
    int *timelist;                                                 //~vac6I~
    int  *attrlist,fileno=0,rc,opt,ctr;                            //~vac6R~
//****************************                                     //~1718I~//~vac6I~
    penv=getThreadEnv();                                           //~1718I~//~vac6I~
    UTRACEP("smb_udirlist,env=%p\n",penv);                        //~1718I~//~vac6R~
    UTRACED("smb_udirlist fpath=%s,attr=%04x\n",Pfpath,Pattr);     //~vac6R~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,8/*rc*/,smb_udirlist,"(ILjava/lang/String;I)I");//~vac6R~
    smb_canonicalpath(0,Ppuftph,Pfpath,cpath);                     //~vac6R~
    jpath=utf8z2jstring(penv,cpath);                               //~vac6M~
    opt=0;                                                         //~vac6M~
//  rc=C2J_INT(penv,smb_udirlist,opt,jpath,(int)Pattr);                  //~1718R~//~vay0R~
    C2J_INT(&rc,penv,smb_udirlist,opt,jpath,(int)Pattr);           //~vay0I~
    unrefLocal(penv,jpath);                                        //~vac6I~
    if (rc)                                                        //~vadhI~
    {                                                              //~vadhI~
        jnismb_chkerr(0,rc);                                       //~vadhI~
	    if (rc==ERROR_NO_MORE_FILES)	//no nfmsg                 //~vadhI~
			smb_dirlistnfmsg(rc,Pfpath,Pattr);                     //~vadhI~
    }                                                              //~vadhI~
    while(!rc)                                                     //~vac6R~
    {                                                              //~vac6I~
		fileno=GETSTATIC_INT(penv,SMB_fileno);                     //~vac6I~
        if (!fileno)                                               //~vac6I~
        {                                                          //~vac6I~
            rc=ufileerrnofileentry(Pfpath,ERROR_FILE_NOT_FOUND);  //~v62bI~//~vac6R~
            break;                                                 //~vac6I~
        }                                                          //~vac6I~
		ctr=GETSTATIC_STRARRAY_UNREF(penv,SMB_namelist,&namelist); //~vac6R~
        if (ctr!=fileno)                                           //~vac6I~
        	break;                                                 //~vac6I~
		ctr=GETSTATIC_INTARRAY_UNREF(penv,SMB_attrlist,&attrlist); //~vac6R~
        if (ctr!=fileno)                                           //~vac6I~
        	break;                                                 //~vac6I~
		ctr=GETSTATIC_LONGARRAY_UNREF(penv,SMB_sizelist,&sizelist);//~vac6R~
        if (ctr!=fileno)                                           //~vac6I~
        	break;                                                 //~vac6I~
		ctr=GETSTATIC_INTARRAY_UNREF(penv,SMB_timelist,&timelist); //~vac6R~
        if (ctr!=fileno)                                           //~vac6I~
        	break;                                                 //~vac6I~
        smb_udirlistsub(0,Ppuftph,Pppudl,fileno,namelist,attrlist,sizelist,timelist);//~vac6R~
        break;                                                     //~vac6I~
    }                                                              //~vac6I~
	smb_freestrarray(fileno,namelist);                             //~vac6I~
    *Ppentno=fileno;                                               //~vac6R~
	UTRACEP("smb_udirlist rc=%x,fileno=%d\n",rc,fileno);           //~vac6R~
    return rc;                                                        //~1718I~//~vac6R~
}//jnismb_udirlist                                                 //~vac6R~
//*******************************************************************//~vac6I~
//ufstat                                                           //~vac6I~
//*******************************************************************//~vac6I~
int smb_ufstatsub(int Popt,PUFTPHOST Ppuftph,char *Pfpath,FILEFINDBUF3 *Ppffb3,char *Pname,int Pattr,jlong Psize,int Ptime)//~vac6R~
{                                                                  //~vac6I~
	char *pfnm;                                                    //~vac6I~
    int attr,isroot,len;                                           //~vac6R~
//****************************                                     //~vac6I~
    memset(Ppffb3,0,sizeof(FILEFINDBUF3));                         //~vac6M~
	pfnm=Pfpath+Ppuftph->UFTPHhostnmlen+1;                         //~vac6I~
    isroot=FTP_ROOTDIR(pfnm);                                      //~vac6I~
    attr=Pattr;                                                    //~vac6I~
    if (isroot)                                                    //~vac6I~
    {                                                              //~vac6I~
    	attr|=FILE_DIRECTORY;                                      //~vac6I~
    	strcpy(Ppffb3->achName,DIR_SEPS);                          //~vac6I~
    }                                                              //~vac6I~
    else                                                           //~vac6I~
    {                                                              //~vac6I~
        len=strlen(Pname);                                         //~vac6I~
        if (len>0 && *(Pname+len-1)=='/')//for directory,"/" is appended//~vac6R~
            len--;                                                 //~vac6I~
        len=min(len,sizeof(Ppffb3->achName)-1);                    //~vac6I~
    	UmemcpyZ(Ppffb3->achName,Pname,len);                       //~vac6R~
    }                                                              //~vac6I~
	Ppffb3->attrFile=uftp3smb_mode2attr_winsub(0,attr);            //~vac6R~
	Ppffb3->cbFile=(FILESZT)Psize;                                 //~vac6R~
	Ppffb3->mtime=Ptime;                                           //~vac6R~
    ufilegetftime(&Ppffb3->ftimeLastWrite,&Ppffb3->fdateLastWrite,Ptime);//~vac6R~
	UTRACEP("smb_ufstatsub path=%s,size=%x,attr=%x,date=%x,time=%x\n",Pname,Psize,Ppffb3->attrFile,Ppffb3->fdateLastWrite,Ppffb3->ftimeLastWrite);//~vac6I~
    return 0;                                                      //~vac6I~
}                                                                  //~vac6I~
//*******************************************************************//~vac6I~
unsigned int jnismb_ufstat(int Popt,PUFTPHOST Ppuftph,char *Pfpath,FILEFINDBUF3 *Ppffb3)//~v5afR~//~vac6R~
{                                                                  //~vac6I~
    static jmethodID staticMethod_smb_ufstat;                      //~vac6R~
    JNIEnv *penv;                                                  //~vac6I~
    char cpath[MAX_CPATH];                                         //~vac6I~
    jstring jpath;                                                 //~vac6I~
    char *name;                                                    //~vac6R~
    jlong size;                                                    //~vac6R~
    int time;                                                      //~vac6I~
    int  attr,rc,opt=0;                                            //~vac6R~
//****************************                                     //~vac6I~
    penv=getThreadEnv();                                           //~vac6I~
    UTRACEP("smb_ufstat,env=%p\n",penv);                           //~vac6I~
    UTRACEP("smb_ufstat fpath=%s\n",Pfpath);                       //~vac6R~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,8/*rc*/,smb_ufstat,"(ILjava/lang/String;)I");//~vac6I~
	if (Popt & JNISMBO_FPWOHID)    //fullpath without hostid       //~vac6I~
    	opt|=CPO_NOHID;                                            //~vac6I~
    smb_canonicalpath(opt,Ppuftph,Pfpath,cpath);                   //~vac6R~
    jpath=utf8z2jstring(penv,cpath);                               //~vac6I~
    opt=0;                                                         //~vac6I~
//  rc=C2J_INT(penv,smb_ufstat,opt,jpath);                         //~vay0R~
    C2J_INT(&rc,penv,smb_ufstat,opt,jpath);                        //~vay0I~
    unrefLocal(penv,jpath);                                        //~vac6I~
    if (rc)                                                        //~vac6I~
    {                                                              //~vac6I~
        if (rc==ENETUNREACH)    //see AxeSMBCifs;                  //~vac6I~
            uerrmsg("Connection Failed to %s",0,                   //~vac6I~
                Pfpath);                                           //~vac6I~
        else                                                       //~vac6I~
        if(rc!=ENOENT && rc!=ERROR_PATH_NOT_FOUND) //not found or no more file//~v61mI~//~vac6I~
            ufileapierr("jcifs-stat",Pfpath,rc);              //~v5afR~//~vac6R~
    }                                                              //~vac6I~
    else                                                           //~vac6R~
    {                                                              //~vac6I~
		name=GETSTATIC_STRING_UNREF(penv,SMB_name);                //~vac6R~
        if (name)                                                  //~vac6I~
        {                                                          //~vac6I~
			attr=GETSTATIC_INT(penv,SMB_attr);                     //~vac6R~
			size=GETSTATIC_LONG(penv,SMB_size);                    //~vac6R~
			time=GETSTATIC_INT(penv,SMB_time);                     //~vac6R~
        	smb_ufstatsub(0,Ppuftph,Pfpath,Ppffb3,name,attr,size,time);//~vac6R~
        }                                                          //~vac6I~
        else                                                       //~vac6I~
        	rc=SMBREQRC_IE;                                        //~vac6I~
    }                                                              //~vac6I~
	UTRACEP("smb_ufstat %s:rc=%x\n",Pfpath,rc);                    //~vac6R~
    return rc;                                                     //~vac6R~
}//ufstat                                                          //~vac6I~
//*******************************************************************//~vac6I~
int jnismb_ufindfirst(int Popt,PUFTPHOST Ppuftph,char *Pfpath,UINT Pattr,FILEFINDBUF3 *Ppffb3)//~vac6R~
{                                                                  //~vac6I~
    static jmethodID staticMethod_smb_ufindfirst;                  //~vac6I~
    JNIEnv *penv;                                                  //~vac6I~
    char cpath[MAX_CPATH];                                         //~vac6I~
    jstring jpath;                                                 //~vac6I~
    char *name;                                                    //~vac6I~
    jlong size;                                                    //~vac6I~
    int time;                                                      //~vac6I~
    int  attr,rc,opt=0/*,fileno*/;                                 //~vak2R~
//****************************                                     //~vac6I~
    penv=getThreadEnv();                                           //~vac6I~
    UTRACEP("smb_ufstat,env=%p\n",penv);                           //~vac6I~
    UTRACEP("smb_ufstat fpath=%s\n",Pfpath);                       //~vac6I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,8/*rc*/,smb_ufindfirst,"(ILjava/lang/String;I)I");//~vac6R~
	if (Popt & JNISMBO_FPWOHID)    //fullpath without hostid       //~vac6I~
    	opt|=CPO_NOHID;                                            //~vac6I~
    smb_canonicalpath(opt,Ppuftph,Pfpath,cpath);                   //~vac6I~
    jpath=utf8z2jstring(penv,cpath);                               //~vac6I~
    opt=0;                                                         //~vac6I~
//  rc=C2J_INT(penv,smb_ufindfirst,opt,jpath,(jint)Pattr);         //~vay0R~
    C2J_INT(&rc,penv,smb_ufindfirst,opt,jpath,(jint)Pattr);        //~vay0I~
    unrefLocal(penv,jpath);                                        //~vac6I~
    if (rc)                                                        //~vac6I~
    {                                                              //~vac6I~
        jnismb_chkerr(0,rc);                                       //~vac6I~
        if (!(Popt & UFTPFF_NOMSG))                          //~v6daR~//~vac6I~//~vadhI~
			smb_dirlistnfmsg(rc,Pfpath,Pattr);                     //~vadhI~
    }                                                              //~vac6I~
    else                                                           //~vac6I~
    {                                                              //~vac6I~
		/*fileno=*/GETSTATIC_INT(penv,SMB_fileno);                 //~vak2R~
		name=GETSTATIC_STRING_UNREF(penv,SMB_name);                //~vac6I~
        if (name)                                                  //~vac6I~
        {                                                          //~vac6I~
			attr=GETSTATIC_INT(penv,SMB_attr);                     //~vac6I~
			size=GETSTATIC_LONG(penv,SMB_size);                    //~vac6I~
			time=GETSTATIC_INT(penv,SMB_time);                     //~vac6I~
        	smb_ufstatsub(0,Ppuftph,Pfpath,Ppffb3,name,attr,size,time);//~vac6I~
        }                                                          //~vac6I~
    }                                                              //~vac6I~
	UTRACEP("smb_ufstat %s:rc=%x\n",Pfpath,rc);                    //~vac6I~
    return rc;                                                     //~vac6I~
}//findfirst                                                       //~vac6R~
//*******************************************************************//~vac6I~
//*connection text                                                 //~vac6I~
//*******************************************************************//~vac6I~
int jnismb_nodetest(int Popt,PUFTPHOST Ppuftph)                    //~vac6I~
{                                                                  //~vac6I~
    static jmethodID staticMethod_smb_nodetest;                    //~vac6I~
    JNIEnv *penv;                                                  //~vac6I~
    char cpath[MAX_CPATH];                                         //~vac6I~
    jstring jpath;                                                 //~vac6I~
    int  rc,opt;                                                   //~vac6I~
//****************************                                     //~vac6I~
    penv=getThreadEnv();                                           //~vac6I~
    UTRACEP("smb_nodetest,env=%p\n",penv);                         //~vac6I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,8/*rc*/,smb_nodetest,"(ILjava/lang/String;)I");//~vac6I~
	opt=CPO_SHARE;                                                 //~vac6I~
    smb_canonicalpath(opt,Ppuftph,"",cpath);                       //~vac6R~
    jpath=utf8z2jstring(penv,cpath);                               //~vac6I~
    opt=Popt;                                                      //~vac6I~
//  rc=C2J_INT(penv,smb_nodetest,opt,jpath);                       //~vay0R~
    C2J_INT(&rc,penv,smb_nodetest,opt,jpath);                      //~vay0I~
    unrefLocal(penv,jpath);                                        //~vac6I~
	if (rc==SMBREQRC_ASYNC)      //async req                       //~vac6I~
    	rc=0;                                                      //~vac6I~
    return rc;                                                     //~vac6I~
}//jnismb_nodetest                                                 //~vac6I~
//*******************************************************************//~vac6I~
//*chdir (fpath:without node prefix)                               //~vac6I~
//*******************************************************************//~vac6I~
int jnismb_uchdir(int Popt,PUFTPHOST Ppuftph,char *Pfpath)         //~vac6I~
{                                                                  //~vac6I~
	int rc,opt;                                                    //~vac6R~
    FILEFINDBUF3 ffb3;                                             //~vac6I~
//****************************                                     //~vac6I~
	opt=JNISMBO_FPWOHID;    //fullpath without hostid              //~vac6R~
	rc=jnismb_ufstat(opt,Ppuftph,Pfpath,&ffb3);                    //~vac6R~
    return rc;                                                     //~vac6I~
}//jnismb_uchdir                                                   //~vac6I~
//*******************************************************************//~vac6I~
//*Enq request                                                     //~vac6I~
//*return cmd length                                               //~vac6I~
//*******************************************************************//~vac6I~
int jnismb_enqrequest(int Popt,char *Pcmd,UQUEH *Ppqh)             //~vac6R~
{                                                                  //~vac6I~
    int cmdlen;                                                    //~vac6R~
    PUSMBJCMDQE pcqe;                                              //~vac6I~
//***********************************                              //~vac6I~
    UTRACEP("jnismb_enqrequest cmd=%s\n",Pcmd);                    //~vac6I~
    cmdlen=strlen(Pcmd);                                           //~vac6I~
    pcqe=(PUSMBJCMDQE)umalloc(cmdlen+USMBJCMDQESZ);                //~vac6I~
    memset(pcqe,0,USMBJCMDQESZ);                                   //~vac6I~
    pcqe->USJCQEcmdlen=cmdlen;                                     //~vac6I~
    UmemcpyZ(pcqe->USJCQEcmd,Pcmd,cmdlen);                         //~vac6I~
	UENQ(UQUE_END,Ppqh,pcqe);                                      //~vac6R~
    return cmdlen;                                                 //~vac6R~
}//jnismb_putstmt                                                  //~vac6I~
//*******************************************************************//~vac6I~
//*req to one string                                               //~vac6I~
//*******************************************************************//~vac6I~
char *jnismb_getreqstring(int Popt,UQUEH *Ppqh,int Ptotlen)        //~vac6R~
{                                                                  //~vac6I~
    PUSMBJCMDQE pcqe;                                              //~vac6I~
    char *pcmd,*pc;                                                //~vac6I~
    int totlen,len;                                                //~vac6R~
//*******************************                                  //~vac6I~
    if (!(totlen=Ptotlen))                                         //~vac6I~
    {                                                              //~vac6I~
        pcqe=UGETQTOP(Ppqh);                                       //~vac6I~
        for (;pcqe;pcqe=UGETQNEXT(pcqe))                           //~vac6I~
        {                                                          //~vac6I~
            totlen+=pcqe->USJCQEcmdlen+1;                          //~vac6I~
        }                                                          //~vac6I~
    }                                                              //~vac6I~
    pcmd=pc=umalloc(totlen+1);//1 for strz                         //~vac6I~
    for (;;)                                                       //~vac6I~
    {                                                              //~vac6I~
    	pcqe=UDEQ(UQUE_TOP,Ppqh,0);                                //~vac6I~
        if (!pcqe)                                                 //~vac6I~
        	break;                                                 //~vac6I~
        len=pcqe->USJCQEcmdlen;                                    //~vac6I~
        memcpy(pc,&pcqe->USJCQEcmd,len);                           //~vac6I~
        pc+=len;                                                   //~vac6I~
        *pc++=';';                                                 //~vac6I~
        ufree(pcqe);                                               //~vac6I~
    }                                                              //~vac6I~
    *pc=0;                                                         //~vac6I~
    return pcmd;                                                   //~vac6R~
}//jnismb_getreqstring                                             //~vac6I~
//*******************************************************************//~vac6I~
//*get/put(append) cmd enq                                         //~vac6I~
//*fnm1:local,fnm2:remote                                          //~vac6I~
//*******************************************************************//~vac6I~
int jnismb_putstmt(int Popt,PUSMBJCMDPARM Ppsjcp,char *Pcmd,char *Plocal,char *Premote)//~vac6I~
{                                                                  //~vac6I~
	PUFTPHOST puftph;                                              //~v6daM~//~vac6I~
    int cmd,pos,opt,cmdlen;                                        //~vac6R~
    char *pcd,*plcd;                                               //~vac6I~
    char smbpath[MAX_CPATH+_MAX_PATH];                             //~vac6I~
    char cmdbuff[MAX_CPATH+_MAX_PATH+32];                          //~vac6I~
//***********************************                              //~v6daM~//~vac6I~
    puftph=Ppsjcp->USJCPpuftph;                                        //~v6daM~//~vac6R~
    cmd=*Pcmd;                                                     //~vac6I~
    pcd=Ppsjcp->USJCPcdpath;                                       //~vac6R~
    plcd=Ppsjcp->USJCPlcdpath;                                     //~vac6R~
    opt=CPO_NOHID;                                                 //~vac6R~
    if (*pcd)                                                      //~vac6I~
    {                                                              //~vac6I~
    	pos=smb_canonicalpath(opt,puftph,pcd,smbpath);             //~vac6I~
        if (!FTP_ROOTDIR(pcd))                                     //~vac6R~
	        *(smbpath+pos++)='/';                                  //~vac6R~
        strcpy(smbpath+pos,Premote);                               //~vac6I~
    }                                                              //~vac6I~
    else	//put case                                             //~vac6I~
    	smb_canonicalpath(opt,puftph,Premote,smbpath);             //~vac6I~
    switch(cmd)                                                    //~vac6I~
    {                                                              //~vac6I~
    case 'g':		//get                                          //~vac6I~
        sprintf(cmdbuff,"g\t%s\t%s/%s",smbpath,plcd,Plocal);       //~vac6I~
    	break;                                                     //~vac6I~
    case 'p':		//put                                          //~vac6R~
        sprintf(cmdbuff,"p\t%s\t%s/%s",smbpath,plcd,Plocal);       //~vac6I~
    	break;                                                     //~vac6I~
    case 'a':		//putappend                                    //~vac6R~
        sprintf(cmdbuff,"a\t%s\t%s/%s",smbpath,plcd,Plocal);       //~vac6I~
    	break;                                                     //~vac6I~
    default:                                                       //~vac6I~
        return 4;                                                  //~vac6I~
    }                                                              //~vac6I~
	cmdlen=jnismb_enqrequest(0,cmdbuff,&Ppsjcp->USJCPcmdq);        //~vac6R~
    Ppsjcp->USJCPcmdtotlen+=cmdlen+1;    //1 for ";" seperator     //~vac6R~
    return 0;                                                      //~vac6I~
}//jnismb_putstmt                                                  //~vac6I~
//*******************************************************************//~vac6I~
//*get/put(append) cmd enq                                         //~vac6I~
//*fnm1:local,fnm2:remote                                          //~vac6I~
//*******************************************************************//~vac6I~
int jnismb_putstmtNoCD(int Popt,PUFTPHOST Ppuftph,char *Pfnm,UQUEH *Ppqh)//~vac6R~
{                                                                  //~vac6I~
    int opt;                                                       //~vac6R~
    char smbpath[MAX_CPATH+_MAX_PATH];                             //~vac6I~
//***********************************                              //~vac6I~
    opt=CPO_NOHID;                                                 //~vac6I~
    smb_canonicalpath(opt,Ppuftph,Pfnm,smbpath);                   //~vac6R~
	jnismb_enqrequest(0,smbpath,Ppqh);                             //~vac6R~
    return 0;                                                      //~vac6I~
}//jnismb_putstmtNoCD                                              //~vac6R~
//*******************************************************************//~vac6I~
//*get/put(append) cmd enq                                         //~vac6I~
//*fnm1:local,fnm2:remote                                          //~vac6I~
//*******************************************************************//~vac6I~
int jnismb_putstmtNoCD2(int Popt,PUFTPHOST Ppuftph,char *Pfnm1,char *Pfnm2,UQUEH *Ppqh)//~vac6I~
{                                                                  //~vac6I~
    int opt,pos;                                                   //~vac6I~
    char *pc;                                                      //~vac6I~
    char smbpath[(MAX_CPATH+_MAX_PATH)*2];                         //~vac6I~
//***********************************                              //~vac6I~
    opt=CPO_NOHID;                                                 //~vac6I~
    pos=smb_canonicalpath(opt,Ppuftph,Pfnm1,smbpath);              //~vac6I~
    pc=smbpath+pos;                                                //~vac6I~
    *pc++='\t';                                                    //~vac6I~
    pos=smb_canonicalpath(opt,Ppuftph,Pfnm2,pc);                   //~vac6I~
	jnismb_enqrequest(0,smbpath,Ppqh);                             //~vac6I~
    return 0;                                                      //~vac6I~
}//jnismb_putstmtNoCD2                                             //~vac6I~
//*******************************************************************//~vac6I~
//*get/put(append) cmd enq                                         //~vac6I~
//*******************************************************************//~vac6I~
int jnismb_getput(int Popt,PUSMBJCMDPARM Ppsjcp)                   //~vac6I~
{                                                                  //~vac6I~
    JNIEnv *penv;                                                  //~vac6I~
    static jmethodID staticMethod_smb_getput;                      //~vac6I~
    int totlen,opt,rc;                                             //~vac6R~
    char *pcmd;                                                    //~vac6R~
    jstring jcmd;                                                  //~vac6I~
//***********************************                              //~vac6I~
	totlen=Ppsjcp->USJCPcmdtotlen;                                 //~vac6I~
    if (totlen<=1)                                                 //~vac6I~
        return 0;                                                  //~vac6M~
	pcmd=jnismb_getreqstring(0,&Ppsjcp->USJCPcmdq,totlen);         //~vac6R~
//    pcmd=pc=umalloc(totlen+1);//1 for strz                       //~vac6R~
//    pqh=&Ppsjcp->USJCPcmdq;                                      //~vac6R~
//    for (;;)                                                     //~vac6R~
//    {                                                            //~vac6R~
//        pcqe=UDEQ(UQUE_TOP,pqh,0);                               //~vac6R~
//        if (!pcqe)                                               //~vac6R~
//            break;                                               //~vac6R~
//        len=pcqe->USJCQEcmdlen;                                  //~vac6R~
//        memcpy(pc,&pcqe->USJCQEcmd,len);                         //~vac6R~
//        pc+=len;                                                 //~vac6R~
//        *pc++=';';                                               //~vac6R~
//        ufree(pcqe);                                             //~vac6R~
//        ctr++;                                                   //~vac6R~
//    }                                                            //~vac6R~
//    *pc=0;                                                       //~vac6R~
    UTRACEP("jnismb_getput cmd=%s\n",pcmd);                        //~vac6I~
    penv=getThreadEnv();                                           //~vac6I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,8/*rc*/,smb_getput,"(ILjava/lang/String;)I");//~vac6I~
    jcmd=utf8z2jstring(penv,pcmd);                                 //~vac6I~
    opt=0;                                                         //~vac6I~
//  rc=C2J_INT(penv,smb_getput,opt,jcmd);                          //~vay0R~
    C2J_INT(&rc,penv,smb_getput,opt,jcmd);                         //~vay0I~
    unrefLocal(penv,jcmd);                                         //~vac6I~
    if (rc)                                                        //~vac6I~
        jnismb_chkerr(0,rc);                                       //~vac6R~
    ufree(pcmd);                                                   //~vac6I~
    return rc;                                                     //~vac6I~
}//jnismb_getput                                                   //~vac6R~
//*******************************************************************//~vac6I~
int jnismb_chkerr(int Popt,int Prc)                                //~vac6R~
{                                                                  //~vac6I~
    JNIEnv *penv;                                                  //~vac6I~
    char *errmsg;                                                  //~vac6I~
    int  rc=0;                                                     //~vac6I~
//****************************                                     //~vac6I~
    penv=getThreadEnv();                                           //~vac6I~
	errmsg=GETSTATIC_STRING(penv,SMB_errmsg);//not UNREF for async process nodetest//~vac6R~
    if (errmsg)                                                    //~vac6I~
    {                                                              //~vac6I~
    	rc=1;                                                      //~vac6I~
    	if (Prc!=ERROR_FILE_NOT_FOUND                              //~vac6I~
        || Popt & CKEO_NFMSG)                                      //~vac6I~
        	uerrmsg(errmsg,0);                                     //~vac6R~
		UTRACEP("smb_chkerr msg=%s\n",errmsg);                     //~vac6I~
    }                                                              //~vac6I~
    else                                                           //~vac6I~
    if (!(Popt & CKEO_EXCEPTIONONLY))                              //~vac6I~
    {                                                              //~vac6I~
//  	if (Prc!=ERROR_FILE_NOT_FOUND && Prc!=ERROR_PATH_NOT_FOUND)//~vac6R~
    	if (Prc!=ERROR_FILE_NOT_FOUND                              //~vac6I~
        || Popt & CKEO_NFMSG)                                      //~vac6I~
        	uerrmsg("smbj operation failed by errno=%d",0,         //~vac6R~
            	Prc);                                              //~vac6R~
    }                                                              //~vac6I~
    return rc;                                                     //~vac6I~
}//jnismb_chkerr                                                   //~vac6I~
//*******************************************************************//~vac6I~
//*path is without hostid                                          //~vac6I~
//*******************************************************************//~vac6I~
int jnismb_delete(int Popt,PUFTPHOST Ppuftph,char *Pfpath)         //~vac6R~
{                                                                  //~vac6I~
    static jmethodID staticMethod_smb_delete;                      //~vac6I~
    JNIEnv *penv;                                                  //~vac6I~
    char cpath[MAX_CPATH];                                         //~vac6I~
    jstring jpath;                                                 //~vac6I~
    int  rc,opt=0;                                                 //~vac6R~
//****************************                                     //~vac6I~
    penv=getThreadEnv();                                           //~vac6I~
    UTRACEP("smb_delete,env=%p\n",penv);                           //~vac6I~
    UTRACEP("smb_delete fpath=%s\n",Pfpath);                       //~vac6I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,8/*rc*/,smb_delete,"(ILjava/lang/String;)I");//~vac6I~
    opt|=CPO_NOHID;                                                //~vac6I~
    smb_canonicalpath(opt,Ppuftph,Pfpath,cpath);                   //~vac6I~
    jpath=utf8z2jstring(penv,cpath);                               //~vac6I~
    opt=0;                                                         //~vac6I~
//  rc=C2J_INT(penv,smb_delete,opt,jpath);                         //~vay0R~
    C2J_INT(&rc,penv,smb_delete,opt,jpath);                        //+vay0I~
    unrefLocal(penv,jpath);                                        //~vac6I~
    if (rc)                                                        //~vac6I~
        jnismb_chkerr(CKEO_NFMSG,rc);                              //~vac6I~
	UTRACEP("smb_delete %s:rc=%x\n",Pfpath,rc);                    //~vac6I~
    return rc;                                                     //~vac6I~
}//delete                                                          //~vac6I~
//*******************************************************************//~vac6I~
//*path is without hostid                                          //~vac6I~
//*******************************************************************//~vac6I~
int jnismb_multidel(int Popt,PUFTPHOST Ppuftph,UQUEH *Ppqh)        //~vac6R~
{                                                                  //~vac6I~
    static jmethodID staticMethod_smb_multidel;                    //~vac6I~
    JNIEnv *penv;                                                  //~vac6I~
    char *pcmd;                                                    //~vac6R~
    jstring jcmd;                                                  //~vac6R~
    int  rc,opt=0;                                                 //~vac6I~
//****************************                                     //~vac6I~
    penv=getThreadEnv();                                           //~vac6I~
    UTRACEP("smb_multidel,env=%p\n",penv);                         //~vac6R~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,8/*rc*/,smb_multidel,"(ILjava/lang/String;)I");//~vac6I~
	pcmd=jnismb_getreqstring(0,Ppqh,0/*totlen*/);                  //~vac6I~
    jcmd=utf8z2jstring(penv,pcmd);                                 //~vac6I~
    opt=0;                                                         //~vac6I~
//  rc=C2J_INT(penv,smb_multidel,opt,jcmd);                        //~vay0R~
    C2J_INT(&rc,penv,smb_multidel,opt,jcmd);                       //~vay0I~
    unrefLocal(penv,jcmd);                                         //~vac6I~
    ufree(pcmd);                                                   //~vac6I~
	UTRACEP("smb_multidel rc=%x\n",rc);                            //~vac6I~
    return rc;                                                     //~vac6I~
}//multidel                                                        //~vac6R~
//*******************************************************************//~vac6I~
//*path is without hostid                                          //~vac6I~
//*******************************************************************//~vac6I~
int jnismb_rename(int Popt,PUFTPHOST Ppuftph,char *Pold,char *Pnew)//~vac6I~
{                                                                  //~vac6I~
    static jmethodID staticMethod_smb_rename;                      //~vac6I~
    JNIEnv *penv;                                                  //~vac6I~
    char cpath1[MAX_CPATH];                                        //~vac6I~
    char cpath2[MAX_CPATH];                                        //~vac6I~
    jstring jpath1,jpath2;                                         //~vac6I~
    int  rc,opt=0;                                                 //~vac6I~
//****************************                                     //~vac6I~
    penv=getThreadEnv();                                           //~vac6I~
    UTRACEP("smb_rename,env=%p\n",penv);                           //~vac6I~
    UTRACEP("smb_rename old=%s,new=%s\n",Pold,Pnew);               //~vac6I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,8/*rc*/,smb_rename,"(ILjava/lang/String;Ljava/lang/String;)I");//~vac6R~
    opt|=CPO_NOHID;                                                //~vac6I~
    smb_canonicalpath(opt,Ppuftph,Pold,cpath1);                    //~vac6I~
    smb_canonicalpath(opt,Ppuftph,Pnew,cpath2);                    //~vac6R~
    jpath1=utf8z2jstring(penv,cpath1);                             //~vac6I~
    jpath2=utf8z2jstring(penv,cpath2);                             //~vac6I~
    opt=0;                                                         //~vac6I~
//  rc=C2J_INT(penv,smb_rename,opt,jpath1,jpath2);                 //~vay0R~
    C2J_INT(&rc,penv,smb_rename,opt,jpath1,jpath2);                //~vay0I~
    unrefLocal(penv,jpath1);                                       //~vac6I~
    unrefLocal(penv,jpath2);                                       //~vac6I~
    if (rc)                                                        //~vac6I~
        jnismb_chkerr(CKEO_NFMSG,rc);                              //~vac6I~
	UTRACEP("smb_rename %s->%s:rc=%x\n",Pold,Pnew,rc);             //~vac6I~
    return rc;                                                     //~vac6I~
}//rename                                                          //~vac6I~
//*******************************************************************//~vac6I~
//*path is without hostid                                          //~vac6I~
//*******************************************************************//~vac6I~
int jnismb_multirename(int Popt,PUFTPHOST Ppuftph,UQUEH *Ppqh)     //~vac6I~
{                                                                  //~vac6I~
    static jmethodID staticMethod_smb_multirename;                 //~vac6I~
    JNIEnv *penv;                                                  //~vac6I~
    char *pcmd;                                                    //~vac6I~
    jstring jcmd;                                                  //~vac6I~
    int  rc,opt=0;                                                 //~vac6I~
//****************************                                     //~vac6I~
    penv=getThreadEnv();                                           //~vac6I~
    UTRACEP("smb_multirename,env=%p\n",penv);                      //~vac6I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,8/*rc*/,smb_multirename,"(ILjava/lang/String;)I");//~vac6I~
	pcmd=jnismb_getreqstring(0,Ppqh,0/*totlen*/);                  //~vac6I~
    jcmd=utf8z2jstring(penv,pcmd);                                 //~vac6I~
    opt=0;                                                         //~vac6I~
//  rc=C2J_INT(penv,smb_multirename,opt,jcmd);                     //~vay0R~
    C2J_INT(&rc,penv,smb_multirename,opt,jcmd);                    //~vay0I~
    unrefLocal(penv,jcmd);                                         //~vac6I~
    ufree(pcmd);                                                   //~vac6I~
	UTRACEP("smb_multirename rc=%x\n",rc);                         //~vac6I~
    return rc;                                                     //~vac6I~
}//multirename                                                     //~vac6I~
//**************************************************************** //~v6daI~//~vac6I~
//*chmod is not supported;ignore the parameter                     //~vac6I~
//**************************************************************** //~vac6I~
int jnismb_mkdir(int Popt,PUFTPHOST Ppuftph,char *Pfnm,ULONG Pattr,ULONG *Ppattr)//~v6daI~//~vac6I~
{                                                                  //~v6daI~//~vac6I~
    static jmethodID staticMethod_smb_mkdir;                       //~vac6I~
    JNIEnv *penv;                                                  //~vac6I~
    char cpath[MAX_CPATH];                                         //~vac6I~
    jstring jpath;                                                 //~vac6I~
    int  rc,opt=0;                                                 //~vac6I~
//****************************                                     //~vac6I~
    penv=getThreadEnv();                                           //~vac6I~
    UTRACEP("smb_mkdir fnm=%s,attr=%x\n",Pfnm,Pattr);              //~vac6R~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,8/*rc*/,smb_mkdir,"(ILjava/lang/String;)I");//~vac6R~
    opt|=CPO_NOHID;                                                //~vac6I~
    smb_canonicalpath(opt,Ppuftph,Pfnm,cpath);                     //~vac6I~
    jpath=utf8z2jstring(penv,cpath);                               //~vac6I~
    opt=0;                                                         //~vac6I~
    if (Ppattr)                                                    //~vac6I~
		opt|=SMBOPT_AFTERSTAT;//   0x02;    //do fstat after operation such as mkdir//~vac6I~
//  rc=C2J_INT(penv,smb_mkdir,opt,jpath);                          //~vay0R~
    C2J_INT(&rc,penv,smb_mkdir,opt,jpath);                         //~vay0I~
    unrefLocal(penv,jpath);                                        //~vac6I~
    if (rc)                                                        //~vac6I~
        jnismb_chkerr(CKEO_NFMSG,rc);                              //~vac6I~
    if (Ppattr)                                                    //~vac6I~
    {                                                              //~vac6I~
		*Ppattr=GETSTATIC_INT(penv,SMB_attr);                      //~vac6I~
    }                                                              //~vac6I~
	UTRACEP("smb_mkdir %s:rc=%x\n",Pfnm,rc);                       //~vac6R~
    return rc;                                                     //~vac6I~
}//jnismb_mkdir                                                    //~vac6I~
//**************************************************************** //~vac6I~
//**************************************************************** //~vac6I~
int jnismb_rmdir(int Popt,PUFTPHOST Ppuftph,char *Pfnm)            //~vac6I~
{                                                                  //~vac6I~
    static jmethodID staticMethod_smb_rmdir;                       //~vac6R~
    JNIEnv *penv;                                                  //~vac6I~
    char cpath[MAX_CPATH];                                         //~vac6I~
    jstring jpath;                                                 //~vac6I~
    int  rc,opt=0;                                                 //~vac6I~
//****************************                                     //~vac6I~
    penv=getThreadEnv();                                           //~vac6I~
    UTRACEP("smb_rmdir fnm=%s\n",Pfnm);                            //~vac6I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,8/*rc*/,smb_rmdir,"(ILjava/lang/String;)I");//~vac6I~
    opt|=CPO_NOHID;                                                //~vac6I~
    smb_canonicalpath(opt,Ppuftph,Pfnm,cpath);                     //~vac6I~
    jpath=utf8z2jstring(penv,cpath);                               //~vac6I~
    opt=0;                                                         //~vac6I~
//  rc=C2J_INT(penv,smb_rmdir,opt,jpath);                          //~vay0R~
    C2J_INT(&rc,penv,smb_rmdir,opt,jpath);                         //~vay0I~
    unrefLocal(penv,jpath);                                        //~vac6I~
    if (rc)                                                        //~vac6I~
    {                                                              //~vac6I~
        if (rc==ENOTEMPTY)                                         //~vac6I~
        {                                                          //~vac6I~
        	uerrmsg("%s is not empty",0,                       //~v015R~//~vac6I~
            			Pfnm);                                     //~vac6I~
        }                                                          //~vac6I~
        else                                                       //~vac6I~
        {                                                          //~vac6I~
        	opt=CKEO_NFMSG;                                        //~vac6I~
        	if (rc==ENOTDIR)                                       //~vac6R~
            {                                                      //~vac6I~
            	ufilenotdir(Pfnm,rc);                              //~vac6R~
                opt|=CKEO_EXCEPTIONONLY;                           //~vac6I~
            }                                                      //~vac6I~
        	jnismb_chkerr(opt,rc);   //override notdir by pathnot found if smbexception exist//~vac6R~
        }                                                          //~vac6I~
    }                                                              //~vac6I~
	UTRACEP("smb_rmdir %s:rc=%x\n",Pfnm,rc);                       //~vac6R~
    return rc;                                                     //~vac6I~
}//jnismb_mkdir                                                    //~vac6I~
//**************************************************************** //~vac6I~
int jnismb_setattr(int Popt,PUFTPHOST Ppuftph,char *Pfnm,int Pattr,ULONG *Ppattr)//~vac6R~
{                                                                  //~vac6I~
    static jmethodID staticMethod_smb_setattr;                     //~vac6I~
    JNIEnv *penv;                                                  //~vac6I~
    char cpath[MAX_CPATH];                                         //~vac6I~
    jstring jpath;                                                 //~vac6I~
    int  rc,opt=0,attr,outattr;                                    //~vac6R~//~vadhR~
//****************************                                     //~vac6I~
    penv=getThreadEnv();                                           //~vac6I~
    UTRACEP("smb_setattr fnm=%s\n",Pfnm);                          //~vac6I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,8/*rc*/,smb_setattr,"(ILjava/lang/String;I)I");//~vac6I~
    smb_canonicalpath(opt,Ppuftph,Pfnm,cpath);                     //~vac6I~
    jpath=utf8z2jstring(penv,cpath);                               //~vac6I~
    attr=Pattr & FILE_CHMODMASK;    //except dir                      //~v6daR~//~vac6I~
//  rc=C2J_INT(penv,smb_setattr,opt,jpath,attr);                   //~vay0R~
    C2J_INT(&rc,penv,smb_setattr,opt,jpath,attr);                  //~vay0I~
    unrefLocal(penv,jpath);                                        //~vac6I~
	outattr=GETSTATIC_INT(penv,SMB_attr);                          //~vadhI~
    if (Ppattr)                                                    //~vac6I~
		*Ppattr=outattr;                                           //~vadhR~
    if (rc)                                                        //~vac6I~
        jnismb_chkerr(opt,rc);   //override notdir by pathnot found if smbexception exist//~vac6I~
	UTRACEP("smb_rmdir %s:rc=%x\n",Pfnm,rc);                       //~vac6I~
    return rc;                                                     //~vac6I~
}//jnismb_setattr                                                  //~vac6I~//~vadhR~
