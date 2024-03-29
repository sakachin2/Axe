//*CID://+vc67R~:                                   update#=  483; //+vc67R~
//**************************************************************** //~1822R~
//vc67 2023/08/11 warning struct timeval will not be visible outside of this function//+vc67I~
//vc5r 2023/07/25 try /sdcard for realpath for api<30              //~v5crI~
//v77T:230708 ARM;locale was not set for xsub tool(pass Gjnilocale by putenv)//~v77TI~
//v77Q:230630 (BUG)utempnam returns /sdcard, it cause permission err. use private for also XSUB; /data/local/tmp is also permission err//~v77QI~
//vc59 2023/06/26 (Bug)clush at initial install for traceopen of sdcard if TRACE OFF//~vc59I~
//v77C:230617 ARM;try dlopen to execute xe tool for >=Android10:api29//~v77CI~
//vbym:230612 ARM;warning by audroidstudio compiler                //~vbymI~
//v77m:230429 ARM:try stat(fpath) by fstat(fd) for ufstat          //~v77mI~
//v77h:230424 ARM;copy                                             //~v77hI~
//v77e:230424 ARM;try fd for opendir/readdir                       //~v77eI~
//vby8:230415 (ARM)open document file                              //~vby8I~
//vc4q 2023/04/01 support shared storage using SAF(StorageAccessFramework)//~vc4qI~
//vc4p 2023/03/30 android10(api29) executable permission; try Manifest:extractNativeLibs=true and getApplicationInfo().nativeLibrary//~vc4pI~
//vc4k 2023/03/28 always utrace.out to private(not on /sdcard)     //~vc4kI~
//vby0:230322 for Axe Api33; ld duplicate symbole                  //~vby0I~
//vc2W 2020/09/19 at first install, uerrexit by ualloc size=0(Gscrbuffwidth); Because notifyOptionChangedOther is called before initscreen; notify trace option by another interface//~vc2WI~
//vc2L 2020/09/02 display TMPDIR                                   //~vc2LI~
//vc2H 2020/08/24 xehelp folder changed to files/myhome/xehelp from files/xehelp//~vc2HI~
//vc1p 2020/06/24 display path env                                 //~vc1pI~
//vc1i 2020/06/22 display not /emulated/.. but /sdcard to header CWD=//~vc1iI~
//vc1f 2020/06/20 ARM;chk sdcard writable                          //~vc1fI~
//vc1d 2020/06/20 ARM;use private dir when SDcard not available    //~vc1dI~
//vc1c 2020/06/19 /proc/version access denied, use Build.VERSION.SDK_INT R RELEASE//~vc1cI~
//vc1a 2020/06/19 keep TRACEOPT LOGCAT option                      //~vc1aI~
//vb05 2014/12/01 ndk release version missing NOTRACE option,but NDEBUG is not applicable(always set)//~vb05I~
//                change "ifndef NOTRACE" to "ifdef TRACE"         //~vb05I~
//vb04 2014/12/01 ndk crash:utraceopen faile by err=1e for utrace.out//~vb04I~
//                mkjni compile with NOTRACE(utracefile is utrace.out)//~vb04I~
//                no release version is debugmoed:true and allow trace option settion//~vb04I~
//                when trace set on,open utrace.out                //~vb04I~
//                jnij2c set sdcard/utrace.out when !NOTRACE       //~vb04I~
//                it should be sdcard/utrace.out also when NOTRACE like as !NOTRACE case//~vb04I~
//vay7:141122 (Axe)actionBar:save/saveas item                      //~vay7I~
//vay1:140722 (Axe)"trace on at dialog" crash by trace file is not set(implicit open of utrace.out fails by pernmission err)//~vay1I~
//vay0:140710 (Axe)jni exception handling                          //~vay0I~
//vais:130607 Axe:xehelp errmsg is not draw justafter menu selected(missing invalidation of xxemain_scrinvalidate)//~vaisI~
//vain:130601 Axe:Bug:popup menu did not processed selected menu item//~vainI~
//v6k1:130524 (Axe)sbcsmap tbl recreate option                     //~v6k1I~
//vai1:130525 (Axe)change utrace.out dir to sdcard because /data/data is protected to opendir//~vai1I~
//vae0:120508 (Axe)crash when Codepage==""(uerrexit cause sigsegv at next JNI call)//~vae0I~
//vad2:120423 (Axe)Charset option by Arm related option dialog     //~vad2I~
//v6dg:120220 (Axe)ftp downloaded file attr is 075(sdcard is read only and FAT); change to private dir//~v6dgI~
//vabg:120127 (Axe)file top/bottom by swipe up/down if rate is over 60(page>40,half>20)//~vabgI~
//vabc:120126 (Axe)for quick startup,split asset to another zip file; user will download and unzip into /data/data/com.xe.Axe/files//~vabcI~
//vab7:120122 (Axe)Android4:getTextWidth returns width=0 if request count is too large//~vab7I~
//vab5:120121 (Axe)add /data/busybox to $PATH(created by "busybox --install")//~vab5I~
//v6c1:120120 udos_setenv:to append to $PATH,specify explicit option(duplicated fpr ICUDATA)//~v6c1I~
//v6bq:111221 icu additional directory name should be icudtxxl     //~v6bqI~
//v6bp:111214 putenv/setenv is not effective for ICU dll;use u_setDataDirectory()//~v6bpI~
//**************************************************************** //~1610I~
#include <time.h>                                                  //+vc67I~
#include <stdlib.h>                                                //~1A12R~
#include <stdio.h>                                                 //~1A12I~
#include <string.h>                                                //~1614I~
//*********************                                            //~1702R~
//#define JNIGBL       //external, jnic2j defines gbl                                      //~1616M~//~vby0R~
#include "jnig.h"                                                  //~1616M~
#include "jnia.h"                                                  //~1616I~
#include <android/log.h>                                           //~1616M~
                                                                   //~1616I~
#include "AxeJNI_sig.h"                                            //~1610I~
#include "jnij2c.h"                                                //~1617R~
#include "jnic2j.h"                                                //~vby8I~
#include "jnisigh.h"                                               //~vay0I~
                                                                   //~1614I~
#include <ulib.h>                                                  //~1614I~
#include <uque.h>                                                  //~vay0I~
#include <ucvucs.h>                                                //~1614I~
#include <utrace.h>                                                //~1623I~
#include <ualloc.h>                                                //~1623I~
#include <udbcschk.h>                                              //~1820I~
#include <udos2.h>                                                 //~v6bqI~
#include <utf.h>                                                   //~vab7I~
#include <ucvext.h>                                                //~v6dgI~
#include <ufile.h>                                                 //~vay7I~
#include <ufiledoc.h>                                              //~vby8I~
#include <ustring.h>                                               //~vay7I~
                                                                   //~1717I~
#include <gxe.h>                                                   //~1717M~
#include <gxeview.h>                                               //~1717M~
#include <xxedef.h>                                                //~1717M~
#include <xxemain.h>                                               //~1717M~
#include <xxexei.h>                                                //~1803I~
#include <gxemfrm.h>                                               //~1802R~
#include <xxecsub.h>                                               //~1926I~
#include <xxefile.h>                                                //~1A18I~//~1A21R~
#include <xxeres.h>                                                //~1A21I~
#include <xe.h>                                                    //~vay0R~
#include <xescr.h>                                                 //~vay7I~
#include <xefile.h>                                                //~vay7I~
#include <xeutf2.h>                                                //~vay7I~
#include <xefcmd7.h>                                               //~vc4qI~
                                                                   //~1717M~
//********************************                                 //~1610I~
#define CLASSNAME_C2J "com/xe/Axe/AxeJNI"                          //~1616R~//~v77eR~
#define CLASSNAME_GXEH "com/xe/Axe/Gxeh"                           //~1716I~//~v77eR~
//static char *Sbusybox_path="/data/busybox"; set at AxeProp                       //~vab5I~//~vc1pR~
//#define CALLC2J (*Gpjnienv)->CallStaticVoidMethod(Gpjnienv,Gclass//~1616I~
//********************************                                 //~1610R~
//#ifndef NOTRACE                                                  //~vb05R~
#ifdef TRACE                                                       //~vb05I~
static int inittraceopt;                                           //~1A18I~
#endif                                                             //~1B05I~
//********************************                                 //~1A18I~
//void c2jdraw(int Pkey);                                          //~1714R~
void logerr(char *Pmsg);                                           //~1610I~
void setupKeyEvent(GdkEventKey *Pevent,int Paction,int Pkey,int Pscan,char *Pstr);//~1815R~
void setupMouseEvent(GdkEventButton *Pevent,int Paction,int Pbutton,int Pflag,int Px,int Py);//~1927R~
void setupMotionEvent(GdkEventMotion *Pevent,int Paction,int Pbutton,int Pflag,int Px,int Py);//~1927R~
void dragStart(int Pflag,int Px,int Py);                           //~1A05I~
void dragEnd(JNIEnv *Penv,int Pflag,int Px,int Py);                //~1A18I~
static char Swdpath[_MAX_PATH];                                    //~vc59R~
//********************************                                 //~1803I~
//*notify inidata after read ini save                              //~1803I~
//********************************                                 //~1803I~
void notifyinidataxe(JNIEnv *Penv)                                 //~1803R~
{                                                                  //~1803I~
//************************************                             //~1803I~
	SETSTATIC_BOOLEAN(Penv,Mbeep,wxe_getbeep()!=0); //Gopt4        //~1803R~
	SETSTATIC_BOOLEAN(Penv,Mfreecsr,wxe_getfreecsr()!=0); //GOPT5CSRSCROLL==0//~1929R~
	SETSTATIC_BOOLEAN(Penv,Mqexit,(Gwxestat & GWXES_OPT_QEXIT)!=0); //GOPT5CSRSCROLL==0//~1929I~
	SETSTATIC_INT(Penv,Museact,Museact); //GOPT5CSRSCROLL==0       //~1929I~
    return;                                                        //~1803R~
}                                                                  //~1803I~
#ifdef AAA                                                         //~vc1pI~
//********************************                                 //~1A26M~
void setpath(JNIEnv *Penv)                                         //~1A26M~
{                                                                  //~1A26M~
	char *phomeshbin,*pc,*pc3,*poptpath,*poptpathlasthalf,*penvpath;//~1A26R~
    int offs,lenhomeshbin,lenenvpath,lenoptpath,lenoptpathlasthalf;//~1A26M~
    int opt=0;                                                     //~v6c1I~
    char *pc4;                                                     //~vab5I~
//*******************************                                  //~1A26M~
	phomeshbin=GETSTATIC_STRING(Penv,addPath);	//~/sh:~/bin       //~1A26M~
    lenhomeshbin=strlen(phomeshbin);                               //~1A26M~
    penvpath=getenv("PATH");					//system env       //~1A26M~
    UTRACEP("%s:phomeshbin=%s,penvpath=%s\n",UTT,phomeshbin,penvpath);//~vc1iI~
    if (penvpath)                                                  //~1A26M~
    {                                                              //~1A26M~
//        lenenvpath=strlen(penvpath);                               //~1A26M~//~vc1iR~
//        if (*penvpath==':')                                        //~1A26R~//~vc1iR~
//        {                                                          //~1A26M~//~vc1iR~
//            penvpath++;                                            //~1A26M~//~vc1iR~
//            lenenvpath--;                                          //~1A26M~//~vc1iR~
//        }                                                          //~1A26M~//~vc1iR~
//        if (lenenvpath>0 && *(penvpath+lenenvpath-1)==':')         //~1A26R~//~vc1iR~
//        {                                                          //~1A26M~//~vc1iR~
//            *(penvpath+lenenvpath-1)=0;                            //~1A26M~//~vc1iR~
//            lenenvpath--;                                          //~1A26M~//~vc1iR~
//        }                                                          //~1A26M~//~vc1iR~
        lenenvpath=strlen(penvpath);                               //~vc1iI~
        pc=strstr(penvpath,phomeshbin);                            //~vc1iR~
        if (pc)                                                    //~vc1iR~
        {                                                          //~vc1iI~
        	penvpath=pc+lenhomeshbin+1;                            //~vc1iR~
            lenenvpath=(int)strlen(penvpath);                      //~vc1iI~
        }                                                          //~vc1iI~
    }                                                              //~1A26M~
    else                                                           //~1A26M~
    	lenenvpath=0;                                              //~1A26M~
    UTRACEP("%s:penvpath=%s,lenenvpath=%d\n",UTT,phomeshbin,penvpath,lenenvpath);//~vc1iI~
	poptpath=GETSTATIC_STRING(Penv,envPath);	//armrelated option dialog PATH env//~1A26M~
    if (poptpath && *poptpath)                                     //~1A26M~//~1B06R~
    {                                                              //~1A26M~
    	pc=strstr(poptpath,"$PATH");                               //~1A26M~
        lenoptpath=strlen(poptpath);                               //~1A26M~
    }                                                              //~1A26M~
    else                                                           //~1A26M~
    {                                                              //~1A26M~
    	pc=0;                                                      //~1A26M~
        lenoptpath=0;                                              //~1A26M~
    }                                                              //~1A26M~
    if (pc)	//optpath contains $PATH                               //~1A26M~
    {                                                              //~1A26M~
    	offs=(ULONG)pc-(ULONG)poptpath;                            //~1A26M~
//        *pc=0;                                                     //~1A26M~//~vc1iR~
//        poptpathlasthalf=pc+5;                                     //~1A26M~//~vc1iR~
//        lenoptpathlasthalf=strlen(poptpathlasthalf);               //~1A26M~//~vc1iR~
//        if (*poptpathlasthalf==':')                                //~1A26R~//~vc1iR~
//        {                                                          //~1A26M~//~vc1iR~
//            poptpathlasthalf++;                                    //~1A26M~//~vc1iR~
//            lenoptpathlasthalf--;                                  //~1A26M~//~vc1iR~
//        }                                                          //~1A26M~//~vc1iR~
//        if (lenoptpathlasthalf>0 && *(poptpathlasthalf+lenoptpathlasthalf-1)==':')//~1A26R~//~vc1iR~
//        {                                                          //~1A26M~//~vc1iR~
//            *(poptpathlasthalf+lenoptpathlasthalf-1)=0;            //~1A26M~//~vc1iR~
//            lenoptpathlasthalf--;                                  //~1A26M~//~vc1iR~
//        }                                                          //~1A26M~//~vc1iR~
	    pc3=umalloc(lenoptpath+lenenvpath+lenhomeshbin+16);        //~1A26M~
        if (!offs)	//$PATH is top                                 //~1A26M~
        {                                                          //~1A26M~
//            if (lenenvpath)                                        //~1A26M~//~vc1iR~
//                if (lenoptpathlasthalf)                            //~1A26M~//~vc1iR~
//                    sprintf(pc3,"PATH=%s:%s:%s",penvpath,poptpathlasthalf,phomeshbin);//~1A26M~//~vc1iR~
//                else                                               //~1A26M~//~vc1iR~
//                    sprintf(pc3,"PATH=%s:%s",penvpath,phomeshbin); //~1A26M~//~vc1iR~
//            else                                                   //~1A26M~//~vc1iR~
//                if (lenoptpathlasthalf)                            //~1A26M~//~vc1iR~
//                    sprintf(pc3,"PATH=%s:%s",poptpathlasthalf,phomeshbin);//~1A26M~//~vc1iR~
//                else                                               //~1A26M~//~vc1iR~
//                    sprintf(pc3,"PATH=%s",phomeshbin);             //~1A26M~//~vc1iR~
            sprintf(pc3,"PATH=%s:%s",phomeshbin,lenenvpath?penvpath:"");//~vc1iR~
        }                                                          //~1A26M~
        else		//$PATH intermediate or last                   //~1A26M~
        {                                                          //~1A26M~
//            if (*(poptpath+offs-1)==':')                           //~1A27I~//~vc1iR~
//                *(poptpath+offs-1)=0;                              //~1A27I~//~vc1iR~
//            if (lenenvpath)                                        //~1A26M~//~vc1iR~
//                if (lenoptpathlasthalf)                            //~1A26M~//~vc1iR~
//                    sprintf(pc3,"PATH=%s:%s:%s:%s",poptpath,penvpath,poptpathlasthalf,phomeshbin);//~1A26R~//~vc1iR~
//                else                                               //~1A26M~//~vc1iR~
//                    sprintf(pc3,"PATH=%s:%s:%s",poptpath,penvpath,phomeshbin);//~1A26R~//~vc1iR~
//            else                                                   //~1A26M~//~vc1iR~
//                if (lenoptpathlasthalf)                            //~1A26M~//~vc1iR~
//                    sprintf(pc3,"PATH=%s:%s:%s",poptpath,poptpathlasthalf,phomeshbin);//~1A26M~//~vc1iR~
//                else                                               //~1A26M~//~vc1iR~
//                    sprintf(pc3,"PATH=%s:%s",poptpath,phomeshbin); //~1A26M~//~vc1iR~
            sprintf(pc3,"PATH=%s",poptpath);                       //~vc1iI~
            sprintf(pc3+offs,"%s:%s",phomeshbin,lenenvpath?penvpath:"");//~vc1iR~
        }                                                          //~1A26M~
    }                                                              //~1A26M~
    else	//no $PATH in optpath                                  //~1A26M~
    {                                                              //~1A26M~
	    pc3=umalloc(lenoptpath+lenhomeshbin+16);                   //~1A26M~
//        if (lenoptpath>0 && *(poptpath+lenoptpath-1)==':')         //~1A26M~//~vc1iR~
//        {                                                          //~1A26M~//~vc1iR~
//            *(poptpath+lenoptpath-1)=0;                            //~1A26M~//~vc1iR~
//            lenoptpath--;                                          //~1A26M~//~vc1iR~
//        }                                                          //~1A26M~//~vc1iR~
//        if (lenoptpath)                                            //~1A26M~//~vc1iR~
//            sprintf(pc3,"PATH=%s:%s",poptpath,phomeshbin);         //~1A26M~//~1B05R~//~vc1iR~
//        else                                                       //~1A26M~//~vc1iR~
//            sprintf(pc3,"PATH=%s",phomeshbin);                     //~1A26M~//~1B05R~//~vc1iR~
        sprintf(pc3,"PATH=%s",lenoptpath?poptpath:"");             //~vc1iI~
        opt=UDSE_PREPEND;                                          //~v6c1I~
    }                                                              //~1A26M~
//  putenv(pc3);                                                   //~1A26M~//~v6bpR~
//  udos_setenv(0,"PATH",pc3+5);                                   //~v6bpI~//~v6c1R~
//  pc4=umalloc(strlen(pc3)+strlen(Sbusybox_path)+4);              //~vab5I~//~vc1iR~
//  sprintf(pc4,"%s:%s",pc3,Sbusybox_path);                        //~vab5I~//~vc1iR~
//  ufree(pc3);                                                    //~vab5I~//~vc1iR~
//  pc3=pc4;                                                       //~vab5I~//~vc1iR~
    udos_setenv(opt,"PATH",pc3+5);                                 //~v6c1I~
    ufree(pc3);                                                    //~1A26M~
}                                                                  //~1A26M~
#else //!AAA                                                       //~vc1pI~
//********************************                                 //~vc1pI~
void setArmEnvPATH()                                               //~vc1pR~
{                                                                  //~vc1pM~
    char *old;                                                     //~v77TI~
	int opt=UDSE_IGNOREDUP;                                                     //~vc1pM~//~v77TR~
//*******************************                                  //~vc1pM~
    old=getenv("PATH");                                            //~v77TI~
    UTRACEP("%s:envPATH=%s,old=%s\n",UTT,GenvVarPATH,old);                    //~vc1pR~//~v77TI~
    udos_setenv(opt,"PATH",GenvVarPATH);                           //~vc1pR~
}                                                                  //~vc1pM~
//********************************                                 //~vc1pI~
void setpath(JNIEnv *Penv)                                         //~vc1pI~
{                                                                  //~vc1pI~
	char *penvpath;
//*******************************                                  //~vc1pI~
	penvpath=GETSTATIC_STRING(Penv,envVarPATH);	//~/sh:~/bin       //~vc1pI~
    penvpath=strdup(penvpath);                                     //~vc1pR~
    GenvVarPATH=penvpath;                                          //~vc1pI~
    UTRACEP("%s:envPATH=%s\n",UTT,GenvVarPATH);                    //~vc1pI~
    setArmEnvPATH();                                               //~vc1pR~
}                                                                  //~vc1pI~
//********************************                                 //~v77QI~
void setEnvTmp(char *PprivatePath)                                 //~v77QI~
{                                                                  //~v77QI~
	char tmp[_MAX_PATH];                                     //~v77QI~
//*******************************                                  //~v77QI~
	sprintf(tmp,"%s/tmp",PprivatePath);                            //~v77QI~
    LOGPD("tmp=%s",tmp);                                          //~v77QI~
	udos_setenv(UDSE_PREPEND,ENV_TMPDIR,tmp);                      //~v77QR~
}                                                                  //~v77QI~
//********************************                                 //~vc1pM~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniSetEnvPath        //~vc1pM~
  (JNIEnv *Penv, jclass Pthis)                                     //~vc1pM~
{                                                                  //~vc1pM~
//********************                                             //~vc1pM~
	void jniSetEnvPath(JNIEnv *Penv, jclass Pthis);                //~vc1pM~
	NDK_TRY_CATCH(Penv,"jniSetEnvPath",                            //~vc1pM~
					jniSetEnvPath(Penv,Pthis);                     //~vc1pM~
        		 );                                                //~vc1pM~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vc1pM~
//********************************                                 //~vc1pM~
void jniSetEnvPath(JNIEnv *Penv,jclass Pthis)                      //~vc1pM~
{                                                                  //~vc1pM~
	setpath(Penv);                                                 //~vc1pM~
}                                                                  //~vc1pM~
#endif                                                             //~vc1pI~
//********************************                                 //~1803I~
void setgbl(JNIEnv *Penv)                                                      //~1703M~//~1803R~
{                                                                  //~1703M~
	char *pc,home[_MAX_PATH];                                 //~1A12I~//~1A25R~//~1A26R~
    FILEFINDBUF3 ffb3,ffb4;                                        //~vay7R~
//*******************************                                  //~1A12I~
	pc=GETSTATIC_STRING(Penv,homeDir);                             //~1A12I~
    sprintf(home,"HOME=%s",pc);                                    //~1A12I~
//  putenv(home);	//for ufullpath ~/                             //~1A12I~//~v6bpR~
    udos_setenv(0,"HOME",home+5);                                  //~v6bpI~
    setpath(Penv);                                                 //~1A26R~
//  Gpview=lookup_widget (GTK_WIDGET (Ppwidget), "maindraw"); //   //~1717I~
    Gpframe=lookup_widget(NULL,"maindraw");                         //~1717I~//~1802R~
	Gicuswfile=GETSTATIC_STRING(Penv,icuSwFile);                           //~1C22I~//~v6bqR~
    gxemfrm_realize(Gpframe);                                      //~1802I~
    //@@@@test                                                     //~vay7I~
    ufstat("/sdcard",&ffb3);	                                   //~vay7I~
    UTRACED("/sdcard",&ffb3,sizeof(ffb3));                         //~vay7R~
    UTRACED("/sdcard slink",ffb3.slink,sizeof(ffb3.slink));        //~vay7I~
    ufstat(ffb3.slink,&ffb4);                                      //~vay7I~
    UTRACED("/sdcard/lagacy",&ffb4,sizeof(ffb4));                  //~vay7I~
	pc=GETSTATIC_STRING(Penv,envVarTMPDIR);                        //~vc2LI~
    if (pc)                                                        //~vc2LR~
	    udos_setenv(0,ENV_TMPDIR,pc);                              //~vc2LR~
	setEnvTmp(Gjniprivatepath);   //prepend private data area(files/tmp)//~v77QI~
	udos_setenv(0,ENV_AXELOCALE,Gjnilocale);                       //~v77TI~
    UTRACEP("%s:envVarTMPDIR=%s,pc=%s\n",UTT,getenv(ENV_TMPDIR),pc);//~vc2LR~
}                                                                  //~1703M~
//********************************                                 //~1713I~
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *Pvm,void *Preserved)     //~1713I~
{                                                                  //~1713I~
	static int Sloadctr=0;                                         //~1714I~
//*************************	                                       //~1714I~
    LOGPD("JNI_OnLoad");                                           //~1714R~
    Gvm=Pvm;                                                       //~1714R~
    Sloadctr++;                                                    //~1714I~
    if (Sloadctr>1)                                                //~1714I~
    {                                                              //~1714I~
    	UTRACEP("JNI_OnLoad ctr=%p\n",Sloadctr);                   //~1714I~
		UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                //~vc4qR~
    }                                                              //~1714I~
    return JNI_VERSION;                                            //~1714R~
}                                                                  //~1713I~
//********************************                                 //~1703I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniInit              //~1623R~
//(JNIEnv *Penv,jobject Pthis,jstring Psdpath,jstring Pwkdir,jstring Plocale,int Ptraceopt)//~1824R~//~1A17R~//~vc1fR~
  (JNIEnv *Penv,jobject Pthis,jstring PsdRoot,jstring Psdpath,jstring Pwkdir,jstring Plocale,int Ptraceopt)//~vc1fI~
{                                                                  //~vay0I~
//  void jniInit(JNIEnv *Penv,jobject Pthis,jstring Psdpath,jstring Pwkdir,jstring Plocale,int Ptraceopt);//~vay0I~//~vc1fR~
    void jniInit(JNIEnv *Penv,jobject Pthis,jstring PsdRoot,jstring Psdpath,jstring Pwkdir,jstring Plocale,int Ptraceopt);//~vc1fI~
	utrace_init(0,0);	//for UTRACE at jnisigh_setup_handler      //~vay0I~
	NDK_TRY_CATCH(Penv,"jniInit",                                  //~vay0I~
//  				jniInit(Penv,Pthis,Psdpath,Pwkdir,Plocale,Ptraceopt);//~vay0I~//~vc1fR~
    				jniInit(Penv,Pthis,PsdRoot,Psdpath,Pwkdir,Plocale,Ptraceopt);//~vc1fI~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
//void jniInit(JNIEnv *Penv,jobject Pthis,jstring Psdpath,jstring Pwkdir,jstring Plocale,int Ptraceopt)//~vay0I~//~vc1fR~
void jniInit(JNIEnv *Penv,jobject Pthis,jstring PsdRoot,jstring Psdpath,jstring Pwkdir,jstring Plocale,int Ptraceopt)//~vc1fI~
{                                                                  //~1617I~
	char *arg[10];	                                               //~1A17R~
    static char wkdirparm[_MAX_PATH];                             //~1623I~//~1809R~//~1A17R~
    static char initFile[_MAX_PATH];                               //~1A17R~
    static char initCmd[4];                                        //~1A17R~
    static char charset[MAXLOCALESZ+4];                            //~vad2I~
    static char helpdirparm[_MAX_PATH];                            //~1A06I~//~1A17R~
    char *wdpath,*locale,*phd,*pc;                                 //~1A06R~//~1A17R~
    int parmctr=1;                                                 //~1629I~
//  jthrowable jt;                                                 //~1715R~
    int apilevel;                                                  //~vab7M~
    int apilevelRelease;                                           //~vc1cI~
//****************	                                               //~1714R~
	memset(arg,0,sizeof(arg));                                     //~1A17I~
    arg[0]="Axe";                                                  //~1A17I~
    LOGPD("jniInit Entry env=%p,this=%p",Penv,Pthis);              //~1713R~
//  SAVE_JAVA_EXCEPTION(Penv,jt);    //save pending exception      //~1715R~
//    Gthis=(*Penv)->NewGlobalRef(Penv,Pthis);                     //~1714R~
//    LOGPD("jniInit Gthis=%p",Gthis);                             //~1714R~
//  Gclass=(*Penv)->FindClass(Penv,CLASSNAME_C2J);                 //~1714R~//~v77eR~
//  GclassGxeh=(*Penv)->FindClass(Penv,CLASSNAME_GXEH);            //~1716I~//~v77eR~
  	Gclass=(*Penv)->FindClass(Penv, "com/xe/Axe/AxeJNI");          //~v77eR~
    GclassGxeh=(*Penv)->FindClass(Penv, "com/xe/Axe/Gxeh");        //~v77eR~
    LOGPD("jniInit FindClass %s=%p,%s=%p\n",CLASSNAME_C2J,Gclass,CLASSNAME_GXEH,GclassGxeh);//~1716R~
    Gclass=(*Penv)->NewGlobalRef(Penv,Gclass);                     //~1714R~
    GclassGxeh=(*Penv)->NewGlobalRef(Penv,GclassGxeh);             //~1716I~
    LOGPD("jniInit FindClass GlobalRef %s=%p,%s=%p\n",CLASSNAME_C2J,Gclass,CLASSNAME_GXEH,GclassGxeh);//~1716R~
	GaxeStatus=GETSTATIC_INT(Penv,axeStatus);                      //~vc1dI~
    LOGPD("jniInit axeStatus=x%x",GaxeStatus);                     //~vc1dI~
  if (PsdRoot)	// /sdcard                                         //~vc1fI~
    GjnisdRoot=jstring2char(Penv,PsdRoot);                         //~vc1fI~
  else                                                             //~vc1fI~
    GjnisdRoot="";                                                 //~vc1fI~
  if (Psdpath)	// /sdcard/Axe                                     //~vc1fR~
    Gjnisdpath=jstring2char(Penv,Psdpath);                            //~1714R~//~1A06R~
  else                                                             //~vc1fI~
    Gjnisdpath=NULL;                                               //~vc1fI~
    LOGPD("jniInit sdpath=%s",Gjnisdpath);                         //~1A06R~
    locale=jstring2char(Penv,Plocale);                             //~1A06I~
    LOGPD("jniInit locale=%s,Gjnilocale=%p",locale,Gjnilocale);    //~1A06I~
    strncpy(Gjnilocale,locale,sizeof(Gjnilocale));  //in ulibarm.h //~1820R~
    ufree(locale);                                                 //~v6dgI~
    wdpath=jstring2char(Penv,Pwkdir);                              //~1820I~
    strcpy(Swdpath,wdpath);                                        //~vc59I~
    LOGPD("jniInit Swdpath=%s",Swdpath);                             //~1820I~//~vc59R~
#ifdef NDEBUG                                                      //~vb05I~
    LOGPD("Release version by -DNDEBUG");                          //~vb05R~
#else                                                              //~vb05I~
    LOGPD("Debug version by No -DNDEBUG");                         //~vb05I~
#endif                                                             //~vb05I~
//#ifdef UTSSTART                                                  //~vb05R~
//    LOGPD("UTSSTART %s","\"" UTSSTART(a) "\"");                  //~vb05R~
//#else                                                            //~vb05R~
//    LOGPD("no UTSSTART defined");                                //~vb05R~
//#endif                                                           //~vb05R~
//#ifdef DEBUG                                                     //~vb05R~
//    LOGPD("Release version by -DDEBUG");                         //~vb05R~
//#else                                                            //~vb05R~
//    LOGPD("Debug version by No -DDEBUG");                        //~vb05R~
//#endif                                                           //~vb05R~
//#ifdef _DEBUG                                                    //~vb05R~
//    LOGPD("Release version by -D_DEBUG");                        //~vb05R~
//#else                                                            //~vb05R~
//    LOGPD("Debug version by No -D_DEBUG");                       //~vb05R~
//#endif                                                           //~vb05R~
//#ifndef NOTRACE                                                  //~vb05R~
#ifdef TRACE                                                       //~vb05I~
//  sprintf(wkdirparm,"%s/utrace.out",wdpath);                     //~vai1R~
//  sprintf(wkdirparm,"%s/utrace.out",Gjnisdpath);                 //~vai1I~//~vc4kR~
//  if (!Gjnisdpath||(GaxeStatus & (AXES_NOPERMISSION_EXTERNAL_STIORAGE_WRITE|AXES_NOT_CANWRITE)))//~vc1dR~//~vc1fR~//~vc4kR~
	    sprintf(wkdirparm,"%s/utrace.out",wdpath);                 //~vc1dI~
    LOGPD("TRACEINIT=%s",wkdirparm);                               //~1629I~
    LOGPD("TRACEINIT=%d",Ptraceopt);                               //~1824I~
//  UTRACE_INIT(wkdirparm,UTRACEO_ON|UTRACEO_LOGCAT);              //~1717R~
	if (Ptraceopt & UTRACEO_ON)     //not restart(TRACE2)          //~1824I~//~1A19R~
//      UTRACE_INIT(wkdirparm,Ptraceopt & UTRACEO_ON);     //at start ignore LOGCAT//~1A17R~//~vc1aR~
        UTRACE_INIT(wkdirparm,Ptraceopt & (UTRACEO_ON | UTRACEO_LOGCAT));     //at start ignore LOGCAT//~vc1aI~
    else                                                           //~1A17I~
//      UTRACE_INIT(0,0);       //set trace_off to avaoid default name(utrace.out) of current path open cause errno=13//~vay1R~
        UTRACE_INIT(wkdirparm,0);  //set filename for later open by trace option change//~vay1I~
//  UTRACE_INIT(wkdirparm,UTRACEO_ON | UTRACEO_LOGCAT);     //TODO test//~vc1aR~//~vc1fR~
    inittraceopt=Ptraceopt; //activate LOGCAT at screeninit        //~1A18I~
#else                                                              //~vay0I~
//  sprintf(wkdirparm,"%s/utrace.out",Gjnisdpath);                 //~vb04I~//~vc59R~
//  if (!Gjnisdpath)                                               //~vc1dR~
//  if (!Gjnisdpath||(GaxeStatus & (AXES_NOPERMISSION_EXTERNAL_STIORAGE_WRITE|AXES_NOT_CANWRITE)))//~vc1fI~//~vc59R~
	    sprintf(wkdirparm,"%s/utrace.out",wdpath);                 //~vc1dI~
    LOGPD("NOTRACE:TRACEINIT=%s",wkdirparm);                       //~vb04I~
//  utrace_init(0,0);	//for the case ulib/xe compiled with !NOTRACE that open utrace.out//~vb04R~
    utrace_init(wkdirparm,0);	//for the case ulib/xe compiled with !NOTRACE that open utrace.out//~vb04I~
#endif                                                             //~1629I~
    Gjniopt=Ptraceopt;  //other than traceopt                      //~v6k1I~
    sprintf(wkdirparm,"-w%s",wdpath);                              //~1629R~
    LOGPD("wkd=%s",wkdirparm);                                     //~1629R~
    arg[parmctr++]=wkdirparm;        //1                           //~1A06I~
//  phd=jstring2char(Penv,Psdpath);                                //~1A06I~//~vabcR~
	phd=GETSTATIC_STRING(Penv,privateTop);	//../files/            //~vabcI~
    Gjniprivatepath=phd;                //for ulibarm<--jnij2cu    //~v6dgI~
//  sprintf(helpdirparm,"-hd%s/xehelp",phd);   //mnt/sdcard/Axe/xehelp//~1A06R~//~vc2HR~
    sprintf(helpdirparm,"-hd%s/myhome/xehelp",phd);   //mnt/sdcard/Axe/xehelp//~vc2HI~
    LOGPD("hd=%s",helpdirparm);                                    //~1A06I~
    arg[parmctr++]=helpdirparm;      //2                           //~1A06R~
//#ifndef NOTRACE                                                  //~vb05R~
#ifdef TRACE                                                       //~vb05I~
	if (Ptraceopt & UTRACEO_ON) //if REOPEN set later at screen init end//~1A19R~
    {                                                              //~1824I~
    	arg[parmctr++]="-D01";  //3: 0:no menory trace,1:trace on                 //~1629R~//~1A06R~
    }                                                              //~1824I~
#endif                                                             //~1629I~
    ufree(wdpath);                                                 //~1623I~
    GjnisdRootPath=GETSTATIC_STRING(Penv,sdRootPath);              //~vc1iI~
    GjniNativeLibraryDir=GETSTATIC_STRING(Penv,nativeLibraryDir);  //~vc4pR~
    GjniNativeLibraryPath=strdup(GjniNativeLibraryDir);            //~v77CI~
    UTRACEP("%s:GjniNativeLibraryDir=%s\n",UTT,GjniNativeLibraryDir);//~vc4pI~
	udos_setenv(0,"LD_LIBRARY_PATH",GjniNativeLibraryDir);    //add to old//~v77CI~
    pc=GETSTATIC_STRING(Penv,initCmd);                             //~1A17R~
    LOGPD("initcmd=%s\n",pc);                                      //~1A17R~
    if (pc)                                                        //~1A17I~
    {                                                              //~1A17I~
    	strncpy(initCmd,pc,sizeof(initCmd));                       //~1A17I~
        ufree(pc);                                                 //~1A17I~
		pc=GETSTATIC_STRING(Penv,initFile);                        //~1A17I~
    	LOGPD("initFile=%s\n",pc);                                 //~1A17I~
    	strncpy(initFile,pc,sizeof(initFile));                     //~1A17I~
        ufree(pc);                                                 //~1A17M~
    	arg[parmctr++]=initCmd;                                    //~1A17I~
    	arg[parmctr++]=initFile;                                   //~1A17I~
        UTRACEP("initfile=%s,cmd=%s\n",initFile,initCmd);          //~1A17I~
    }                                                              //~1A17I~
    pc=GETSTATIC_STRING(Penv,localeCharset);                       //~vad2I~
    LOGPD("localecharset=%s\n",pc);                                //~vad2I~
//  if (pc)                                                        //~vad2I~//~vae0R~
    if (pc && *pc)                                                 //~vad2I~//~vae0R~
    {                                                              //~vad2I~
    	strcpy(charset,"-C");                                      //~vad2I~
    	strncpy(charset+2,pc,sizeof(charset)-2);                   //~vad2R~
        ufree(pc);                                                 //~vad2I~
    	arg[parmctr++]=charset;                                    //~vad2I~
    }                                                              //~vad2I~
	apilevel=GETSTATIC_INT(Penv,osVersion);                        //~vab7M~
	apilevelRelease=GETSTATIC_INT(Penv,osVersionRelease);          //~vc1cI~
    setArmOSVersion(0,apilevel,apilevelRelease);                   //~vc1cR~
    UTRACEP("%s:apilevel=%d,release=%d\n",UTT,apilevel,apilevelRelease);//~vc1cI~
    if (apilevel>=API_ICE_CREAM_SANDWICH)                          //~vab7M~
    	Gulibutfmode|=GULIBUTF_JAVAWCWIDTH; //char width(sbcs or dbcs) determined not by mk_wcwidth() but by Java wcwidth()//~vab7M~
    gxe_init(parmctr,arg);	//preinit,read ini data                                         //~1629R~//~1823R~
    setgbl(Penv);                                                      //~1703I~//~1803R~
//  THROW_JAVA_EXCEPTION_IF_SAVED(Penv,jt);    //save pending exception//~1715R~
    return;                                                        //~1623R~
}                                                                  //~1617I~
//********************************                                 //~1617I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniSetScreenSize     //~1623R~
  (JNIEnv *Penv, jclass Pthis,jint Pw,jint Ph)                     //~1713R~
{                                                                  //~vay0I~
	void jniSetScreenSize(JNIEnv *Penv, jclass Pthis,jint Pw,jint Ph);//~vay0I~
	NDK_TRY_CATCH(Penv,"jniSetScreenSize",                         //~vay0I~
					jniSetScreenSize(Penv,Pthis,Pw,Ph);            //~vay0I~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniSetScreenSize(JNIEnv *Penv, jclass Pthis,jint Pw,jint Ph)  //~vay0I~
{                                                                  //~1617I~
//  jthrowable jt;                                                 //~1715R~
    static int Ssw1st=1;                                           //~1821I~
//*************                                                    //~1617I~
    UTRACEP("jnij2c.jniSetScreenSize env=%p,this=%p,\n",Penv,Pthis);//~1714R~//~vc4qR~
//  SAVE_JAVA_EXCEPTION(Penv,jt);    //save pending exception      //~1715R~
//  Gclass=(*Penv)->GetObjectClass(Penv,Pclass);                   //~1714R~
//  LOGPD("jniSetScreenSize GetObjectClass=%p",Gclass);            //~1713R~
//  Gclass=(*Penv)->NewGlobalRef(Penv,Gclass);                     //~1714R~
                                                                   //~1713I~
	GjniScrW=(int)Pw;                                              //~1617R~
    GjniScrH=(int)Ph;                                              //~1617R~//~1803R~
    UTRACEP("jniInit jniSetScreenSize w=%d,h=%d\n",Pw,Ph);         //~1716I~
    if (Pw && Ph)                                                  //~1703I~
    {                                                              //~1717I~
        if (Ssw1st)                                                //~1821I~
        {                                                          //~1821I~
        	Ssw1st=0;                                              //~1821I~
    		gxeview_realize(Gpview);                               //~1821R~
    		notifyinidataxe(Penv);	//after REQ_INIT from xxemain_init<-gxeview_init<-gxeview_realize//~1821R~
        }                                                          //~1821I~
    	xxemain_OnSize(Pw,Ph);	//issue initally xxemain_scrinvalidate to draw at first//~1717I~
    }                                                              //~1717I~
//  THROW_JAVA_EXCEPTION_IF_SAVED(Penv,jt);    //save pending exception//~1715R~
//    if (inittraceopt & UTRACEO_REOPEN)  //TRACE2:start at init end axeview will call after jnisetscreensize//~1A19R~
//    {                                                            //~1A19R~
//        LOGPD("jniSetScreenSize traceopt=%x\n",inittraceopt);    //~1A19R~
//        UTRACE_INIT(0,inittraceopt);     //set LOGCAT//~1A18I~   //~1A19R~
//    }                                                            //~1A19R~
    UTRACE_FLUSH("jnij2c.jniSetScreenSize exit\n");                //~vc4qI~
    return;                                                        //~1623R~
}                                                                  //~1617I~
//********************************                                 //~1617I~
JNIEXPORT jint JNICALL Java_com_xe_Axe_AxeJNI_jniKbdMsg            //~1617R~
  (JNIEnv *Penv, jclass Pthis, jint Paction,jint Pkey,jint Pscancode)//~1815R~
{                                                                  //~vay0I~
	int rc=-1;                                                     //~vay0I~
	int jniKbdMsg(JNIEnv *Penv, jclass Pthis, jint Paction,jint Pkey,jint Pscancode);//~vay0R~
	NDK_TRY_CATCH(Penv,"jniKbdMsg",                                //~vay0I~
					rc=jniKbdMsg(Penv,Pthis,Paction,Pkey,Pscancode);//~vay0R~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
	return rc;                                                     //~vay0I~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
int jniKbdMsg(JNIEnv *Penv, jclass Pthis, jint Paction,jint Pkey,jint Pscancode)//~vay0R~
{
	int key,scan,action;                                           //~1815R~
    unsigned char charstr[32];                                     //~1614R~
    GdkEventKey gdkevent;                                          //~1614I~
//  jthrowable jt;                                                 //~1715R~
//*************                                                    //~1611I~
    UTRACEP("jniKbdMsg env=%p,this=%p\n",Penv,Pthis);              //~1714R~
//  SAVE_JAVA_EXCEPTION(Penv,jt);    //save pending exception      //~1715R~
    key=(int)Pkey;                                                 //~1610I~
    scan=(int)Pscancode;                                           //~1815R~
    action=(int)Paction;                                           //~1815I~
    UTRACEP("jniKbdMsg key=%x scan=%x,action=%x\n",key,scan,action);//~1815R~
//*                                                                //~1616I~
    setupKeyEvent(&gdkevent,action,key,scan,charstr);              //~1815R~
    switch(action)                                                 //~1616R~
    {                                                              //~1616R~
    case KBDACTION_UNICODE:                                        //~1616I~
        OnChar(NULL/*imecontext*/,charstr,NULL/*GtkWidget*/);      //~1616R~
        break;                                                     //~1616I~
    case KBDACTION_UP:                                             //~1616R~
        gxeview_OnKeyUp(NULL/*GtkWidget*/,&gdkevent);              //~1616R~
        break;                                                     //~1616R~
    default:        //down(action=0) and rctl by button(action=-1) //~1616R~
//        if (action==KBDACTION_CHAR &&                              //~1616I~//~1829R~
//            (Pkey & (KBF_CONTROL|KBF_ALT))                         //~1616I~//~1829R~
//        )                                          //Alt+ch/Ctrl+ch//~1616I~//~1829R~
//            OnChar(NULL/*imecontext*/,charstr,NULL/*GtkWidget*/);  //~1616I~//~1829R~
//        else                                                       //~1616I~//~1829R~
        	gxeview_OnKeyDown(NULL/*GtkWidget*/,&gdkevent);        //~1616R~
    }                                                              //~1616R~
//  c2jdraw(key);//test@@@@                                        //~1621R~
//  THROW_JAVA_EXCEPTION_IF_SAVED(Penv,jt);    //save pending exception//~1715R~
    UTRACE_FLUSH("jnij2c.kbdMsg exit\n");                          //~vc4qI~
	return 0;                                                      //~1610I~
}
//*******************************************                      //~1614I~
//*setup Key evnet                                                 //~1621R~
//*******************************************                      //~1614I~
void setupKeyEvent(GdkEventKey *Pevent,int Paction,int Pkey,int Pscan,char *Pstr)//~1815R~
{                                                                  //~1614I~
	int len,ucs;                                                   //~1616R~
//***********                                                      //~1614I~
    memset(Pevent,0,sizeof(GdkEventKey));                          //~1614R~
    if (Paction==KBDACTION_UNICODE) //unicode>=0x80                //~1616I~
    {                                                              //~1616I~
    	Pevent->keyval=ucs=Pkey;                                   //~1616I~
	    len=uccvucs2utf(ucs,Pstr);                                 //~1616I~
    }                                                              //~1616I~
    else                                                           //~1616I~
    {                                                              //~1616I~
    	Pevent->state=Pkey>>16;                                    //~1616R~
    	Pevent->keyval=ucs=Pkey & 0xffff;                          //~1616R~
    	Pevent->hardware_keycode=Pscan;                            //~1815I~
	    if (Paction==KBDACTION_CHAR) //unicode<0x80                //~1616I~
        {                                                          //~1616I~
        	len=1;                                                 //~1616I~
            *Pstr=ucs;                                             //~1616I~
        }                                                          //~1616I~
        else                                                       //~1616I~
        	len=0;                                                 //~1616I~
    }                                                              //~1616I~
    Pevent->string=Pstr;                                           //~1614R~
    Pevent->length=len;                                            //~1614R~
    Pevent->time=gtk_get_current_event_time();                     //~1621I~
    *(Pstr+len)=0;                                                 //~1614I~
    return;                                                        //~1614I~
}                                                                  //~1614I~
//********************************                                 //~1621I~
JNIEXPORT jint JNICALL Java_com_xe_Axe_AxeJNI_jniMouseMsg          //~1621R~
  (JNIEnv *Penv, jclass Pthis, jint Paction,jint Pbutton,jint Pflag,jint Px,jint Py)//~1927R~
{                                                                  //~vay0I~
	int rc=-1;                                                     //~vay0I~
	jint jniMouseMsg(JNIEnv *Penv, jclass Pthis, jint Paction,jint Pbutton,jint Pflag,jint Px,jint Py);//~vay0I~
	NDK_TRY_CATCH(Penv,"jniMouseMsg",                              //~vay0I~
					rc=jniMouseMsg(Penv,Pthis,Paction,Pbutton,Pflag,Px,Py);//~vay0I~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
    return rc;                                                     //~vay0I~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
jint jniMouseMsg(JNIEnv *Penv, jclass Pthis, jint Paction,jint Pbutton,jint Pflag,jint Px,jint Py)//~vay0I~
{                                                                  //~1621I~
    GdkEventButton gdkevent;                                       //~1621I~
    GdkEventMotion gdkeventmotion;                                 //~1621I~
    int xx,yy,action,col,row;                                      //~1926R~
    CPoint pt;                                                     //~1A04R~
//  jthrowable jt;                                                 //~1715R~
//*************                                                    //~1621I~
    UTRACEP("jniMouseMsg env=%p,this=%p,action=%d,btn=%d,x=%d,y=%d\n",Penv,Pthis,Paction,Pbutton,Px,Py);//~1926R~//~1930R~
//  SAVE_JAVA_EXCEPTION(Penv,jt);    //save pending exception      //~1715R~
    action=(int)Paction;                                           //~1621I~
    if (Px>=0)	 //Px=Py=-1 when trackball push                    //~1926R~
    {                                                              //~1926I~
    	xx=(int)Px;                                                //~1926R~
    	yy=(int)Py;                                                //~1926R~
    }                                                              //~1926I~
    else                                                           //~1926I~
    {                                                              //~1926I~
        ugetcaretpos(&row,&col);                                   //~1926R~
        xx=XX(col);                                                //~1926I~
        yy=YY(row);                                                //~1926R~
    	UTRACEP("jniMouseMsg mouseByKbd col=%d,row=%d-->xx=%d,yy=%d\n",col,row,xx,yy);//~1926I~
    }                                                              //~1926I~
//*                                                                //~1621I~
    switch(action)                                                 //~1621I~
    {                                                              //~1621I~
    case MOUSEACTION_DOWN:                                         //~1621I~
	    setupMouseEvent(&gdkevent,action,Pbutton,Pflag,xx,yy);     //~1927R~
        gxeview_buttondown(NULL/*GtkWidget*/,&gdkevent);           //~1621R~
        break;                                                     //~1621I~
    case MOUSEACTION_UP:                                           //~1621M~
	    setupMouseEvent(&gdkevent,action,Pbutton,Pflag,xx,yy);     //~1927R~
        gxeview_buttonup(NULL/*GtkWidget*/,&gdkevent);             //~1621I~
        if (Mdragedpcw)                                            //~1A18R~
        {                                                          //~1A18I~
        	dragEnd(Penv,Pflag,xx,yy);                             //~1A18R~
        }                                                          //~1A18I~
        break;                                                     //~1621M~
    case MOUSEACTION_MOVE:                                         //~1621I~
	    setupMotionEvent(&gdkeventmotion,action,Pbutton,Pflag,xx,yy);//~1927R~
        gxeview_motion(NULL/*GtkWidget*/,&gdkeventmotion);         //~1621R~
        break;                                                     //~1621I~
    case MOUSEACTION_DBLCLICK:                                     //~1A04I~
        pt.x=xx;                                                   //~1A04I~
        pt.y=yy;                                                   //~1A04I~
        xxemain_mouselbdblclk(Pflag,pt);                           //~1A04R~
        break;                                                     //~1A04I~
    case MOUSEACTION_DRAG:                                         //~1A04I~
        dragStart(Pflag,xx,yy);                                    //~1A05R~
        break;                                                     //~1A04I~
    }                                                              //~1621I~
//  THROW_JAVA_EXCEPTION_IF_SAVED(Penv,jt);    //save pending exception//~1715R~
    UTRACE_FLUSH("jnij2c.MouseMsg exit\n");                        //~vc4qI~
	return 0;                                                      //~1621I~
}                                                                  //~1621I~
//*******************************************                      //~1621I~
//*drag start by Kbd                                                      //~1A05R~//~1A18R~
//* lbdown then move                                               //~1A05I~
//*******************************************                      //~1621I~
void dragStart(int Pflag,int Px,int Py)                            //~1A05R~
{                                                                  //~1621I~
    GdkEventButton gdkevent;                                       //~1A05I~
    GdkEventMotion gdkeventmotion;                                 //~1A05I~
    int action;                                                    //~1A05I~
//***********                                                      //~1621I~
    UTRACEP("jniMouseMsg dragstart xx=%d,yy=%d\n",Px,Py);          //~1A05R~
    action=MOUSEACTION_DOWN;                                       //~1A05I~
	setupMouseEvent(&gdkevent,action,1/*buttonid*/,Pflag,Px,Py);   //~1A05I~
    gxeview_buttondown(NULL/*GtkWidget*/,&gdkevent);               //~1A05I~
    action=MOUSEACTION_MOVE;                                       //~1A05I~
	setupMotionEvent(&gdkeventmotion,action,1/*buttonid*/,Pflag,Px+Mcellw,Py);//set dragstart(Mcpcopysw==2)//~1A05R~
    gxeview_motion(NULL/*GtkWidget*/,&gdkeventmotion);             //~1A05I~
	setupMotionEvent(&gdkeventmotion,action,1/*buttonid*/,Pflag,Px+Mcellw,Py); //moved right by 1 cell//~1A05R~
    gxeview_motion(NULL/*GtkWidget*/,&gdkeventmotion);             //~1A05I~
    return;                                                        //~1621I~
}                                                                  //~1621I~
//*******************************************                      //~1A18I~
//*drag end by touch                                               //~1A18I~
//*******************************************                      //~1A18I~
void dragEnd(JNIEnv *Penv,int Pflag,int Px,int Py)                 //~1A18R~
{                                                                  //~1A18I~
    static jmethodID staticMethod_dragsend;                        //~1A18I~
    GdkDragContext dragcontext;                                    //~1A18M~
	GtkSelectionData seldata;                                      //~1A19I~
    jstring jstr;                                                  //~1A18I~
//*********************                                            //~1A18I~
    UTRACEP("jniMouseMsg dragEnd xx=%d,yy=%d,Mdraglist=%p\n",Px,Py,Mdraglist);//~1A18R~
    memset(&dragcontext,0,sizeof(dragcontext)); //for trace only   //~1A18I~//~1A19M~
    memset(&seldata,0,sizeof(seldata)); //for trace only           //~1A19I~
    if (Py<0 && Mdraglist)//process send                           //~1A18R~
    {	                                                           //~1A18I~
        Gdndselection=0;    //gxeview_dragdataget-->gtk_selection_data_set will set it//~1A19I~
		gxeview_dragdataget(&dragcontext,&seldata);                //~1A19R~
        if (Gdndselection)                                         //~1A19I~
        {                                                          //~1A19I~
	    	C2J_GETMETHODID_GOTOIFERR(Penv,dragsend,"(Ljava/lang/String;)V");//~1A18I~//~1A19R~
			jstr=utf8z2jstring(Penv,Gdndselection);                        //~1A18I~//~1A19R~
    		C2J_VOID(Penv,dragsend,jstr);                              //~1A18R~//~1A19R~
	    	unrefLocal(Penv,jstr);                                     //~1A18I~//~1A19R~
ret:                                                               //~1A18M~//~1A19M~
            ufree(Gdndselection);                                  //~1A19I~
            Gdndselection=0;                                       //~1A19I~
        }                                                          //~1A19I~
	    gxeview_dragend(&dragcontext);                                 //~1A18R~//~1A21I~
    }                                                              //~1A18I~
    else	//split screen drop?                                   //~1A21I~
    {                                                              //~1A21I~
        Gdndselection=0;    //gxeview_dragdataget-->gtk_selection_data_set will set it//~1A21I~
		gxeview_dragdataget(&dragcontext,&seldata);                //~1A21I~
        if (Gdndselection)                                         //~1A21I~
        {                                                          //~1A21I~
			xxefile_ondropfiles(&dragcontext,&seldata,Px,Py,0/*Ptime*/);//~1A21I~
        }                                                          //~1A21I~
	    gxeview_dragend(&dragcontext);                             //~1A21I~
    }                                                              //~1A21I~
}                                                                  //~1A18I~
//*******************************************                      //~1A05I~
//*setup Key evnet                                                 //~1A05I~
//*******************************************                      //~1A05I~
void setupMouseEvent(GdkEventButton *Pevent,int Paction,int Pbutton,int Pflag,int Px,int Py)//~1A05I~
{                                                                  //~1A05I~
//***********                                                      //~1A05I~
    memset(Pevent,0,sizeof(GdkEventButton));                       //~1A05I~
    Pevent->x=(gdouble)Px;                                         //~1A05I~
    Pevent->y=(gdouble)Py;                                         //~1A05I~
    Pevent->button=Pbutton;	//left button                          //~1A05I~
    Pevent->time=gtk_get_current_event_time();                     //~1A05I~
    Pevent->state=Pflag;                                           //~1A05I~
    return;                                                        //~1A05I~
}                                                                  //~1A05I~
//*******************************************                      //~1621I~
//*setup Key evnet                                                 //~1621I~
//*******************************************                      //~1621I~
void setupMotionEvent(GdkEventMotion *Pevent,int Paction,int Pbutton,int Pflag,int Px,int Py)//~1927R~
{                                                                  //~1621I~
//***********                                                      //~1621I~
    memset(Pevent,0,sizeof(GdkEventMotion));                       //~1621I~
    Pevent->x=(gdouble)Px;                                         //~1621I~
    Pevent->y=(gdouble)Py;                                         //~1621I~
    Pevent->time=gtk_get_current_event_time();                     //~1621I~
    Pevent->state=Pflag;                                           //~1926I~
    return;                                                        //~1621I~
}                                                                  //~1621I~
////*************************************************              //~1714R~
//void c2jdraw(int Pkey)                                           //~1714R~
//{                                                                //~1714R~
//    jstring jstr;                                                //~1714R~
//    static jmethodID staticMethod_drawTextFromJNI;               //~1714R~
//    char text[128];                                              //~1714R~
////*********************                                          //~1714R~
////    cls=(*Penv)->FindClass(Penv,CLASSNAME_C2J);                //~1714R~
////    if (!cls)                                                  //~1714R~
////    {                                                          //~1714R~
////        logerrps1("class(%s) not found",CLASSNAME_C2J);        //~1714R~
////        return;                                                //~1714R~
////    }                                                          //~1714R~
////  cls=Gclass; //AxeJNI.java                                    //~1714R~
////  method=(*Penv)->GetMethodID(Penv,cls,METHODNAME_DRAWFROMJNI,"([java/lang/String;)V");//~1714R~
////  method=(*Penv)->GetStaticMethodID(Penv,cls,METHODNAME_DRAWFROMJNI,"(Ljava/lang/String;)V");//~1714R~
////    method=C2J_GETMETHODID(METHODNAME_DRAWFROMJNI,"(Ljava/lang/String;)V");//~1714R~
////    if (!method)                                               //~1714R~
////    {                                                          //~1714R~
////        LOGPE("method(%s) not found",METHODNAME_DRAWFROMJNI);  //~1714R~
////        return;                                                //~1714R~
////    }                                                          //~1714R~
//    C2J_GETMETHODID_RETIFERR(drawTextFromJNI,"(Ljava/lang/String;)V");//~1714R~
//    sprintf(text,"key=x%x",Pkey);                                //~1714R~
//    jstr=(*Penv)->NewStringUTF(Penv,text);                       //~1714R~
////  (*Penv)->CallVoidMethod(Penv,cls,method,jstr);               //~1714R~
////  (*Penv)->CallStaticVoidMethod(Penv,cls,method,jstr);         //~1714R~
//    C2J_VOID(staticMethod_drawTextFromJNI,jstr);                 //~1714R~
//}                                                                //~1714R~
//*************************************************                //~1616I~
////*************************                                      //~1616R~
//void logprintf(char *Pfmt,...)                                   //~1616R~
//{                                                                //~1616R~
//    va_list pargn0;                                              //~1616R~
//    int ii0;                                                     //~1616R~
////***********                                                    //~1616R~
//    va_start(pargn0,Pfmt);                                       //~1616R~
//    for (ii0=0;ii0<LOGMSG_MAXPARM;ii0++)                         //~1616R~
//        va_arg(pargn0,unsigned long);                            //~1616R~
//    va_end(pargn0);                                              //~1616R~
//    __android_log_vprint(ANDROID_LOG_DEBUG,LOG_TAG,Pfmt,pargn0); //~1616R~
//}                                                                //~1616R~
//********************************                                 //~1802I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniTerminateXe       //~1925R~
  (JNIEnv *Penv, jclass Pthis)                                     //~1802I~
{                                                                  //~vay0I~
	void jniTerminateXe(JNIEnv *Penv, jclass Pthis);               //~vay0I~
	NDK_TRY_CATCH(Penv,"jniTerminateXe",                           //~vay0I~
					jniTerminateXe(Penv,Pthis);                    //~vay0I~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniTerminateXe(JNIEnv *Penv, jclass Pthis)                    //~vay0I~
{                                                                  //~1802I~
//*************                                                    //~1802I~
	gxemfrm_onclose();                                             //~1802R~
}                                                                  //~1802I~
//********************************                                 //~1803I~
//*gxedlg otheroption chaged                                       //~1803I~
//********************************                                 //~1803I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOptionChangedOther//~1803I~
  (JNIEnv *Penv, jclass Pthis,                                     //~1803I~
//  jint Prulermode,jint Puseact,jboolean Pfreecsr,jboolean Pbeep,jboolean Pqexit)//~1803I~//~vc1fR~
    jint Prulermode,jint Puseact,jboolean Pfreecsr,jboolean Pbeep,jboolean Pqexit,jint PaxeStatus)//~vc1fI~
{                                                                  //~vay0I~
//  void jniOptionChangedOther(JNIEnv *Penv, jclass Pthis,jint Prulermode,jint Puseact,jboolean Pfreecsr,jboolean Pbeep,jboolean Pqexit);//~vay0I~//~vc1fR~
    void jniOptionChangedOther(JNIEnv *Penv, jclass Pthis,jint Prulermode,jint Puseact,jboolean Pfreecsr,jboolean Pbeep,jboolean Pqexit,jint PaxeStatus);//~vc1fI~
	NDK_TRY_CATCH(Penv,"jniOptionChangedOther",                    //~vay0I~
//  				jniOptionChangedOther(Penv,Pthis,Prulermode,Puseact,Pfreecsr,Pbeep,Pqexit);//~vay0I~//~vc1fR~
    				jniOptionChangedOther(Penv,Pthis,Prulermode,Puseact,Pfreecsr,Pbeep,Pqexit,PaxeStatus);//~vc1fI~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//*************************                                                           //~vay0I~//~vc2WR~
void jniOptionChangedOtherPermission(JNIEnv *Penv, jclass Pthis,jint PaxeStatus)//~vc2WI~
{                                                                  //~vc2WI~
#ifdef TRACE                                                       //~v77mI~
    char wkdirparm[_MAX_PATH];                                     //~vc2WI~
    int traceopt;                                                  //~vc2WI~
#endif                                                             //~v77mI~
//*************                                                    //~vc2WI~
//  Mrulermode=Prulermode;                                         //~vc2WI~
//  Museact=Puseact;                                               //~vc2WI~
//  Mfreecsr=(BOOL)Pfreecsr;                                       //~vc2WI~
//  wxe_setfreecsr(Mfreecsr);                                      //~vc2WI~
//  wxe_setbeep(Pbeep);                                            //~vc2WI~
//  if (Pqexit)                                                    //~vc2WI~
//  	Gwxestat|=GWXES_OPT_QEXIT;                                 //~vc2WI~
//  else                                                           //~vc2WI~
//  	Gwxestat&=~GWXES_OPT_QEXIT;                                //~vc2WI~
#ifdef TRACE                                                       //~vc2WI~
	traceopt=utrace_getopt();                                      //~vc2WI~
    UTRACEP("%s:traceopt=x%x,PaxeStatus=x%x,GaxeStatus=x%x\n",UTT,traceopt,PaxeStatus,GaxeStatus);//~vc2WR~
    UTRACEDIFNZ("Gjnisdpath",Gjnisdpath,strlen(Gjnisdpath));       //~vc2WI~
    if ((traceopt & UTRACEO_ON)                                    //~vc2WI~
//  &&  Gjnisdpath                                                 //~vc2WI~//~vc59R~
//  &&  (!(PaxeStatus & AXES_NOPERMISSION_EXTERNAL_STIORAGE_WRITE) //~vc2WI~//~vc59R~
//  &&  GaxeStatus & AXES_NOPERMISSION_EXTERNAL_STIORAGE_WRITE)    //~vc2WI~//~vc59R~
    )                                                              //~vc2WI~
    {                                                              //~vc2WI~
//      sprintf(wkdirparm,"%s/utrace.out",Gjnisdpath);             //~vc2WI~//~vc59R~
        sprintf(wkdirparm,"%s/utrace.out",Swdpath);                //~vc59I~
        UTRACE_INIT(wkdirparm,UTRACEO_ON);     //at start ignore LOGCAT//~vc2WI~
	    UTRACEP("%s:traceopt=x%x,PaxeStatus=x%x,GaxeStatus=x%x\n",UTT,traceopt,PaxeStatus,GaxeStatus);//~vc2WR~
    	UTRACEDIFNZ("Gjnisdpath",Gjnisdpath,strlen(Gjnisdpath));   //~vc2WI~
    }                                                              //~vc2WI~
#endif                                                             //~vc2WI~
    GaxeStatus=PaxeStatus;                                         //~vc2WI~
    return;                                                        //~vc2WI~
}                                                                  //~vc2WI~
//void jniOptionChangedOther(JNIEnv *Penv, jclass Pthis,jint Prulermode,jint Puseact,jboolean Pfreecsr,jboolean Pbeep,jboolean Pqexit)//~vay0I~//~vc1fR~
void jniOptionChangedOther(JNIEnv *Penv, jclass Pthis,jint Prulermode,jint Puseact,jboolean Pfreecsr,jboolean Pbeep,jboolean Pqexit,jint PaxeStatus)//~vc1fI~
{                                                                  //~1803I~
//*************                                                    //~1803I~
	if (Prulermode==-1)                                            //~vc2WI~
    {                                                              //~vc2WI~
		jniOptionChangedOtherPermission(Penv,Pthis,PaxeStatus);    //~vc2WI~
        return;                                                    //~vc2WI~
    }                                                              //~vc2WI~
	Mrulermode=Prulermode;                                         //~1803I~
	Museact=Puseact;                                               //~1803R~
	Mfreecsr=(BOOL)Pfreecsr;                                       //~1803I~
	wxe_setfreecsr(Mfreecsr);                                      //~1929I~
	wxe_setbeep(Pbeep);                                            //~1803I~
	if (Pqexit)                                                    //~1803I~
    	Gwxestat|=GWXES_OPT_QEXIT;                                 //~v73yI~//~1803I~
    else                                                           //~v73yI~//~1803I~
    	Gwxestat&=~GWXES_OPT_QEXIT;                                //~v73yI~//~1803I~
    GaxeStatus=PaxeStatus;                                         //~vc1fI~
    UTRACEP("Mrulermode=%d,Museact=%d,Mfreecsr=%d,beep=%d,qexit=%d,axestatus=x%x\n",Prulermode,Puseact,Pfreecsr,Pbeep,Pqexit,PaxeStatus);//~vc1fI~
    return;                                                        //~1803I~
}                                                                  //~1803I~
//********************************                                 //~1803I~
//*gxedlg color chaged                                             //~1803I~
//********************************                                 //~1803I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOptionChangedColor//~1803R~
  (JNIEnv *Penv, jclass Pthis,                                     //~1803I~
    jint Pbgcolor,jint Prulercolor)                                //~1803R~
{                                                                  //~vay0I~
	void jniOptionChangedColor(JNIEnv *Penv, jclass Pthis,jint Pbgcolor,jint Prulercolor);//~vay0I~
	NDK_TRY_CATCH(Penv,"jniOptionChangedColor",                    //~vay0I~
					jniOptionChangedColor(Penv,Pthis,Pbgcolor,Prulercolor);//~vay0I~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniOptionChangedColor(JNIEnv *Penv, jclass Pthis,jint Pbgcolor,jint Prulercolor)//~vay0I~
{                                                                  //~1803I~
//*************                                                    //~1803I~
	COLOR_INT2XXECOLOR(Mbgcolor,Pbgcolor);                         //~1803R~
	COLOR_INT2XXECOLOR(Mrulercolor,Prulercolor);                   //~1803R~
    return;                                                        //~1803I~
}                                                                  //~1803I~
//********************************                                 //~1803I~
//*gxedlg font chaged                                              //~1803I~
//********************************                                 //~1803I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOptionChangedFont //~1803I~
  (JNIEnv *Penv, jclass Pthis,                                     //~1803I~
    jstring Pfontname,int Pfontwidth,int Pfontheight,int Pcellw,int Pcellh,//~1823R~
//  int Pmonospace,jboolean Pligature,int Pbaseline,int Pfontoffsx)//offsy is by baseline//~1823R~
    int Pmonospace,jboolean Pligature,int Pcellw0,int Pcellh0)                             //~1823R~//~1A02R~
{                                                                  //~vay0I~
	void jniOptionChangedFont(JNIEnv *Penv, jclass Pthis,jstring Pfontname,int Pfontwidth,int Pfontheight,int Pcellw,int Pcellh,int Pmonospace,jboolean Pligature,int Pcellw0,int Pcellh0);//~vay0I~
	NDK_TRY_CATCH(Penv,"jniOptionChangedFont",                     //~vay0I~
					jniOptionChangedFont(Penv,Pthis,Pfontname,Pfontwidth,Pfontheight,Pcellw,Pcellh,Pmonospace,Pligature,Pcellw0,Pcellh0);//~vay0I~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniOptionChangedFont(JNIEnv *Penv, jclass Pthis,jstring Pfontname,int Pfontwidth,int Pfontheight,int Pcellw,int Pcellh,int Pmonospace,jboolean Pligature,int Pcellw0,int Pcellh0)//~vay0I~
{                                                                  //~1803I~
//*************                                                    //~1803I~
	jstring2buff(Penv,Pfontname,Mfontstyle,sizeof(Mfontstyle));    //~1803I~
	Mcellw=Pcellw; //McellW0 is writen to ini                                                //~1803I~//~1804R~//~1A02R~
	Mcellh=Pcellh;                                                 //~1803I~//~1804R~//~1A02R~
	Mcellw0=Pcellw0; //McellW0 is writen to ini                    //~1A02I~
	Mcellh0=Pcellh0;                                               //~1A02I~
	Mligature=Pligature;                                           //~1803I~
	Mfontheight=Pfontheight;                                       //~1822I~
	Mfontwidth=Pfontwidth;                                         //~1823I~
	Mmonospace=(Pmonospace==1);                                    //~1823R~
//	MjniBaseLine=Pbaseline;                                        //~1823R~
//  MjniFontoffsx=Pfontoffsx;                                      //~1823R~
    if (Pmonospace<0)                                              //~1823R~
        Gxxestat|=GXXES_FONTLIGATURE;                              //~1823I~
    else                                                           //~1823I~
        Gxxestat&=~GXXES_FONTLIGATURE;                             //~1823I~
    UTRACEP("jniOptionChangedFont name=%s,font=(%d,%d),cell=(%d,%d),monospace=%d,ligature=%d\n",Mfontstyle,Mfontwidth,Mfontheight,Mcellw,Mcellh,Mmonospace,Mligature);//~1823R~
    return;                                                        //~1803I~
}                                                                  //~1803I~
//********************************                                 //~1823I~
//*gxedlg font chaged                                              //~1823I~
//********************************                                 //~1823I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniFullDraw          //~1823R~
  (JNIEnv *Penv, jclass Pthis ,jint Presize)                       //~1927R~
{                                                                  //~vay0I~
	void jniFullDraw(JNIEnv *Penv, jclass Pthis ,jint Presize);    //~vay0I~
	NDK_TRY_CATCH(Penv,"jniFullDraw",                              //~vay0I~
					jniFullDraw(Penv,Pthis,Presize);               //~vay0I~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniFullDraw(JNIEnv *Penv, jclass Pthis ,jint Presize)         //~vay0I~
{                                                                  //~1823I~
//*************                                                    //~1823I~
	UTRACEP("jniFullDraw resize=%d\n",Presize);                    //~1927I~
    if (Presize)                                                   //~1927I~
    	xxemain_OnSize(GjniScrW,GjniScrH);	//issue initally xxemain_scrinvalidate to draw at first//~1927I~
    else                                                           //~1927I~
	    xxemain_scrinvalidate(SCRINVO_FULLDRAW);                   //~1927R~
    return;                                                        //~1823I~
}                                                                  //~1823I~
//********************************                                 //~1808I~
//*gxedlg font chaged                                              //~1808I~
//********************************                                 //~1808I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOnTimer           //~1808I~
  (JNIEnv *Penv, jclass Pthis,                                     //~1808I~
    jint Ptimerid,jlong Pcallback)                                 //~1925R~
{                                                                  //~vay0I~
	void jniOnTimer(JNIEnv *Penv, jclass Pthis,jint Ptimerid,jlong Pcallback);//~vay0I~
	NDK_TRY_CATCH(Penv,"jniOnTimer",                               //~vay0I~
					jniOnTimer(Penv,Pthis,Ptimerid,Pcallback);     //~vay0I~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniOnTimer(JNIEnv *Penv, jclass Pthis,jint Ptimerid,jlong Pcallback)//~vay0I~
{                                                                  //~1808I~
//*************                                                    //~1808I~
	UTRACEP("jniOnTimer id=%x\n",Ptimerid);                        //~1808I~
//  typedef gboolean (*GtkFunction)	    (gpointer      data);      //~1808I~
    (*(GtkFunction)(unsigned long)Pcallback)(&Ptimerid);           //~1925R~
    return;                                                        //~1808I~
}                                                                  //~1808I~
//********************************                                 //~1821I~
//*gxedlg font chaged                                              //~1821I~
//********************************                                 //~1821I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniGetSampleColor    //~1821I~
  (JNIEnv *Penv, jclass Pthis,                                     //~1821I~
    jintArray Psamplecolor)                                        //~1925R~
{                                                                  //~vay0I~
	void jniGetSampleColor(JNIEnv *Penv,jclass Pthis,jintArray Psamplecolor);//~vay0I~
	NDK_TRY_CATCH(Penv,"jniGetSampleColor",                        //~vay0I~
					jniGetSampleColor(Penv,Pthis,Psamplecolor);    //~vay0I~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniGetSampleColor(JNIEnv *Penv,jclass Pthis,jintArray Psamplecolor)//~vay0I~
{                                                                  //~1821I~
	char attrtbl[3];                                               //~1822R~
    int samplecolor[3];                                            //~1821I~
    GdkColor *pcolor;                                              //~1822R~
//*************                                                    //~1821I~
	UTRACEP("jnigetSampleColor\n");                                //~1821I~
    wxe_getsamplecolor(attrtbl);                                   //~1822R~
    pcolor=Gxxepalette+(int)attrtbl[0];                            //~1822R~
    samplecolor[0]=GDKCOLOR2COLOR(pcolor);                         //~1822R~
    pcolor=Gxxepalette+(int)attrtbl[1];                            //~1822R~
    samplecolor[1]=GDKCOLOR2COLOR(pcolor);                         //~1822R~
    pcolor=Gxxepalette+(int)attrtbl[2];                            //~1822R~
    samplecolor[2]=GDKCOLOR2COLOR(pcolor);                         //~1822R~
    UTRACEP("GetSampleColor %x,%x,%x\n",samplecolor[0],samplecolor[1],samplecolor[2]);//~1821I~
    copyint2jint(Penv,Psamplecolor,samplecolor,3);                 //~1821I~
    return;                                                        //~1821I~
}                                                                  //~1821I~
//****************************************************             //~1824I~
//*armoption:internal option:trace changed by dialog               //~1824I~
//****************************************************             //~1824I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniSetTraceOpt       //~1824R~
  (JNIEnv *Penv, jclass Pthis,                                     //~1824I~
    jint Ptraceopt)                                                //~1926R~
{                                                                  //~vay0I~
	void jniSetTraceOpt(JNIEnv *Penv, jclass Pthis,jint Ptraceopt);//~vay0I~
	NDK_TRY_CATCH(Penv,"jniSetTraceOpt",                           //~vay0I~
					jniSetTraceOpt(Penv,Pthis,Ptraceopt);          //~vay0I~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniSetTraceOpt(JNIEnv *Penv, jclass Pthis,jint Ptraceopt)     //~vay0I~
{                                                                  //~1824I~
//*************                                                    //~1824I~
	if (Ptraceopt)                                                 //~1824I~
		utrace_init(NULL,Ptraceopt); //UTRACEO,UTRACEO_LOGCAT      //~1926R~
    else                                                           //~1824I~
		utrace_init(NULL,UTRACEO_TEMPCLOSE);                       //~1824I~
    return;                                                        //~1824I~
}                                                                  //~1824I~
//****************************************************             //~vay0I~
//*armoption:internal option:trace changed by dialog               //~vay0I~
//****************************************************             //~vay0I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniSetDebugOpt       //~vay0I~
  (JNIEnv *Penv, jclass Pthis,                                     //~vay0I~
    jint Pdebugopt)                                                //~vay0I~
{                                                                  //~vay0I~
	void jniSetDebugOpt(JNIEnv *Penv,jclass Pthis,jint Pdebugopt); //~vay0I~
	NDK_TRY_CATCH(Penv,"jniSetDebugOpt",                           //~vay0I~
					jniSetDebugOpt(Penv,Pthis,Pdebugopt);          //~vay0R~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniSetDebugOpt(JNIEnv *Penv,jclass Pthis,jint Pdebugopt)      //~vay0I~
{                                                                  //~vay0I~
//*************                                                    //~vay0I~
	if (Pdebugopt & DEBUGO_ABEND)                                  //~vay0R~
        UCBITON(Gopt3,GOPT3TESTABEND);//abend by double Esc        //~vay0I~
    else                                                           //~vay0I~
        UCBITOFF(Gopt3,GOPT3TESTABEND);//abend by double Esc       //~vay0I~
	if (Pdebugopt & DEBUGO_UERREXIT)                               //~vay0R~
	    UCBITON(Gopt3,GOPT3TESTEXIT);//uerrexit by double Esc      //~vay0I~
    else                                                           //~vay0I~
	    UCBITOFF(Gopt3,GOPT3TESTEXIT);//uerrexit by double Esc     //~vay0I~
    return;                                                        //~vay0I~
}                                                                  //~vay0I~
//****************************************************             //~1930I~
//*contextmenu closed                                              //~1930I~
//****************************************************             //~1930I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOnContextMenuClosed//~1930I~
  (JNIEnv *Penv, jclass Pthis)                                     //~1930I~
{                                                                  //~vay0I~
    void jniOnContextMenuClosed(JNIEnv *Penv,jclass Pthis);        //~vay1R~
    NDK_TRY_CATCH(Penv,"jniContextMenuClosed",                     //~vay1R~
					jniOnContextMenuClosed(Penv,Pthis);            //~vay0I~
                 );                                                //~vay1R~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniOnContextMenuClosed(JNIEnv *Penv,jclass Pthis)             //~vay0I~
{                                                                  //~1930I~
//*************                                                    //~1930I~
	Mfloatmenusw=0;                                                //~1930R~
    return;                                                        //~1930I~
}                                                                  //~1930I~
//****************************************************             //~1A02I~
//*Cut and Paste                                                   //~1A02I~
//****************************************************             //~1A02I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOnEditCut         //~1A02R~
  (JNIEnv *Penv, jclass Pthis)                                     //~1A02I~
{                                                                  //~vay0I~
	void jniOnEditCut(JNIEnv *Penv,jclass Pthis);                  //~vay0I~
	NDK_TRY_CATCH(Penv,"jniOnEditCut",                             //~vay0I~
					jniOnEditCut(Penv,Pthis);                      //~vay0I~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniOnEditCut(JNIEnv *Penv,jclass Pthis)                       //~vay0I~
{                                                                  //~1A02I~
    gxeview_OnEditCut();                                           //~1A02I~
    return;                                                        //~1A02I~
}                                                                  //~1A02I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOnEditCopy        //~1A02R~
  (JNIEnv *Penv, jclass Pthis)                                     //~1A02I~
{                                                                  //~vay0I~
	void jniOnEditCopy(JNIEnv *Penv,jclass Pthis);                 //~vay0I~
	NDK_TRY_CATCH(Penv,"jniOnEditCopy",                            //~vay0I~
					jniOnEditCopy(Penv,Pthis);                     //~vay0I~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniOnEditCopy(JNIEnv *Penv,jclass Pthis)                      //~vay0I~
{                                                                  //~1A02I~
    gxeview_OnEditCopy();                                          //~1A02I~
    return;                                                        //~1A02I~
}                                                                  //~1A02I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOnEditClear       //~1A02R~
  (JNIEnv *Penv, jclass Pthis)                                     //~1A02I~
{                                                                  //~vay0I~
	void jniOnEditClear(JNIEnv *Penv,jclass Pthis);                //~vay0I~
	NDK_TRY_CATCH(Penv,"jniOnEditClear",                           //~vay0I~
					jniOnEditClear(Penv,Pthis);                    //~vay0I~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniOnEditClear(JNIEnv *Penv,jclass Pthis)                     //~vay0I~
{                                                                  //~1A02I~
    gxeview_OnEditClear();                                         //~1A02I~
    return;                                                        //~1A02I~
}                                                                  //~1A02I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOnEditPasteV      //~1A02R~
  (JNIEnv *Penv, jclass Pthis)                                     //~1A02I~
{                                                                  //~vay0I~
	void jniOnEditPasteV(JNIEnv *Penv, jclass Pthis);              //~vay0I~
	NDK_TRY_CATCH(Penv,"jniOnEditPasteV",                          //~vay0I~
					jniOnEditPasteV(Penv,Pthis);                   //~vay0I~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniOnEditPasteV(JNIEnv *Penv, jclass Pthis)                   //~vay0I~
{                                                                  //~1A02I~
    gxeview_OnEditPasteV();                                        //~1A02R~
    return;                                                        //~1A02I~
}                                                                  //~1A02I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOnEditPasteIns    //~1A02R~
  (JNIEnv *Penv, jclass Pthis)                                     //~1A02I~
{                                                                  //~vay0I~
	void jniOnEditPasteIns(JNIEnv *Penv, jclass Pthis);            //~vay0I~
	NDK_TRY_CATCH(Penv,"jniOnEditPasteIns",                        //~vay0I~
					jniOnEditPasteIns(Penv,Pthis);                 //~vay0I~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniOnEditPasteIns(JNIEnv *Penv, jclass Pthis)                 //~vay0I~
{                                                                  //~1A02I~
    gxeview_OnEditPasteIns();                                      //~1A02I~
    return;                                                        //~1A02I~
}                                                                  //~1A02I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOnEditPasteRep    //~1A02R~
  (JNIEnv *Penv, jclass Pthis)                                     //~1A02I~
{                                                                  //~vay0I~
	void jniOnEditPasteRep(JNIEnv *Penv, jclass Pthis);            //~vay0I~
	NDK_TRY_CATCH(Penv,"jniOnEditPasteRep",                        //~vay0I~
					jniOnEditPasteRep(Penv,Pthis);                 //~vay0I~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniOnEditPasteRep(JNIEnv *Penv, jclass Pthis)                 //~vay0I~
{                                                                  //~1A02I~
    gxeview_OnEditPasteRep();                                      //~1A02I~
    return;                                                        //~1A02I~
}                                                                  //~1A02I~
//****************************************************             //~vainI~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOnFileEnd         //~vainI~
  (JNIEnv *Penv, jclass Pthis)                                     //~vainI~
{                                                                  //~vay0I~
	void jniOnFileEnd(JNIEnv *Penv, jclass Pthis);                 //~vay0I~
	NDK_TRY_CATCH(Penv,"jniOnFileEnd",                             //~vay0I~
					jniOnFileEnd(Penv,Pthis);                      //~vay0I~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniOnFileEnd(JNIEnv *Penv, jclass Pthis)                      //~vay0I~
{                                                                  //~vainI~
	UTRACEP("jniOnFileEnd\n");                                     //~vainI~
    gxeview_OnEditEnd();                                           //~vainR~
    return;                                                        //~vainI~
}                                                                  //~vainI~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOnFileCancel      //~vainI~
  (JNIEnv *Penv, jclass Pthis)                                     //~vainI~
{                                                                  //~vay0I~
	void jniOnFileCancel(JNIEnv *Penv, jclass Pthis);              //~vay0I~
	NDK_TRY_CATCH(Penv,"jniOnFileCancel",                          //~vay0I~
					jniOnFileCancel(Penv,Pthis);                   //~vay0I~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniOnFileCancel(JNIEnv *Penv, jclass Pthis)                   //~vay0I~
{                                                                  //~vainI~
	UTRACEP("jniOnFileCancel\n");                                  //~vainI~
    gxeview_OnEditCancel();                                        //~vainI~
    return;                                                        //~vainI~
}                                                                  //~vainI~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOnFileOpenWith    //~vainI~
  (JNIEnv *Penv, jclass Pthis)                                     //~vainI~
{                                                                  //~vay0I~
	void jniOnFileOpenWith(JNIEnv *Penv, jclass Pthis);            //~vay0I~
	NDK_TRY_CATCH(Penv,"jniOnFileOpenWith",                        //~vay0I~
					jniOnFileOpenWith(Penv,Pthis);                 //~vay0I~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniOnFileOpenWith(JNIEnv *Penv, jclass Pthis)                 //~vay0I~
{                                                                  //~vainI~
	UTRACEP("jniOnFileOpenWith\n");                                //~vainI~
    gxeview_OnFileOpenwith();                                      //~vainR~
    return;                                                        //~vainI~
}                                                                  //~vainI~
//****************************************************             //~1A03I~
//*Swipe for scroll                                                //~1A03I~
//****************************************************             //~1A03I~
//int getscrollctr(int Pmax,int Prate)                               //~1A03I~//~vabgR~
int getscrollctr(int Popt,int Pmax,int Prate)                      //~vabgI~
{                                                                  //~1A03I~
	int ctr;                                                       //~1A03I~
//*************************                                        //~1A03I~
//	if (Prate>=50)	//over 1/2 of screen size                      //~1A03R~//~vabgR~
  	if (Prate>=60)	//over 1/2 of screen size                      //~vabgI~
    {                                                              //~vabgI~
    	if (Popt & 0x01) //vertical                                //~vabgI~
    		ctr=Pmax+1; //top/bottom if vertical                   //~vabgI~
        else                                                       //~vabgI~
	    	ctr=Pmax;   //scroll:Page                              //~vabgI~
    }                                                              //~vabgI~
    else                                                           //~vabgI~
  	if (Prate>=40)	//over 1/2 of screen size                      //~vabgI~
    	ctr=Pmax;   //scroll:Page                                  //~1A03R~
    else                                                           //~1A03I~
	if (Prate>=20)	//over 1/4 of screen size                      //~1A03R~
    	ctr=max(Pmax/2,1);    //scroll:Half                        //~1A03R~
    else                                                           //~1A03I~
    	ctr=1;                                                     //~1A03R~
	UTRACEP("getscrollctr max=%d,rate=%d,ctr=%d\n",Pmax,Prate,ctr);//~1A03I~
    return ctr;                                                    //~1A03I~
}                                                                  //~1A03I~
//******************************************************************//~vay0I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniSwipeHorizontal   //~1A03I~
  (JNIEnv *Penv, jclass Pthis, jint Pmodstatus,jint Pdest, jint Prate)//~1A03I~
{                                                                  //~vay0I~
	void jniSwipeHorizontal(JNIEnv *Penv,jclass Pthis,jint Pmodstatus,jint Pdest,jint Prate);//~vay0I~
	NDK_TRY_CATCH(Penv,"jniSwipeHorizontal",                       //~vay0I~
					jniSwipeHorizontal(Penv,Pthis,Pmodstatus,Pdest,Prate);//~vay0I~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniSwipeHorizontal(JNIEnv *Penv,jclass Pthis,jint Pmodstatus,jint Pdest,jint Prate)//~vay0I~
{                                                                  //~1A03I~
	int scrollctr,flag;                                            //~1A03I~
//********************                                             //~1A03I~
	UTRACEP("jniSwipeHorizontal mod=%x,dest=%d,rate=%d\n",Pmodstatus,Pdest,Prate);//~1A03I~
    flag=(Pmodstatus & MK_CONTROL)|MK_SHIFT;	//shift:horizontal id//~1A03R~
//  scrollctr=getscrollctr(Mscrcmaxcol,Prate);                     //~1A03I~//~vabgR~
    scrollctr=getscrollctr(0/*Horizontal*/,Mscrcmaxcol,Prate);     //~vabgI~
	wxe_scroll(scrollctr,flag,Pdest);                              //~1A03I~
	xxemain_scrinvalidate(0);                                      //~vXXEI~//~1A03I~
    return;                                                        //~1A03I~
}                                                                  //~1A03I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniSwipeVertical     //~1A03I~
  (JNIEnv *Penv, jclass Pthis, jint Pmodstatus,jint Pdest, jint Prate)//~1A03I~
{                                                                  //~vay0I~
	void jniSwipeVertical(JNIEnv *Penv, jclass Pthis, jint Pmodstatus,jint Pdest, jint Prate);//~vay0I~
	NDK_TRY_CATCH(Penv,"jniSwipeVertical",                         //~vay0I~
					jniSwipeVertical(Penv,Pthis,Pmodstatus,Pdest,Prate);//~vay0I~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniSwipeVertical(JNIEnv *Penv, jclass Pthis, jint Pmodstatus,jint Pdest, jint Prate)//~vay0I~
{                                                                  //~1A03I~
	int scrollctr,flag;                                            //~1A03I~
//********************                                             //~1A03I~
	UTRACEP("jniSwipeVertical mod=%x,dest=%d,rate=%d\n",Pmodstatus,Pdest,Prate);//~1A03I~
    flag=(Pmodstatus & MK_CONTROL);	//drop shift:horizontal id     //~1A03I~
//  scrollctr=getscrollctr(Mscrcmaxrow,Prate);                     //~1A03R~//~vabgR~
    scrollctr=getscrollctr(0x01/*vertical*/,Mscrcmaxrow,Prate);    //~vabgI~
    if (scrollctr>Mscrcmaxrow)                                     //~vabgI~
    	flag|=MK_CONTROL;	//top/bottom                           //~vabgI~
	wxe_scroll(scrollctr,flag,Pdest);                              //~1A03I~
	xxemain_scrinvalidate(0);                                      //~1A03I~
    return;                                                        //~1A03I~
}                                                                  //~1A03I~
//******************************************************************//~vay0I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniCmd               //~1A07I~
  (JNIEnv *Penv, jclass Pthis, jstring Pcmd, jstring Popd)         //~1A07I~
{                                                                  //~vay0I~
	void jniCmd(JNIEnv *Penv, jclass Pthis, jstring Pcmd, jstring Popd);//~vay0I~
	NDK_TRY_CATCH(Penv,"jniCmd",                                   //~vay0I~
					jniCmd(Penv,Pthis,Pcmd,Popd);                  //~vay0I~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniCmd(JNIEnv *Penv, jclass Pthis, jstring Pcmd, jstring Popd)//~vay0I~
{                                                                  //~1A07I~
    char *pcmd,*popd;                                              //~1A07I~
//********************                                             //~1A07I~
    pcmd=jstring2char(Penv,Pcmd);                                  //~1A07I~
    popd=jstring2char(Penv,Popd);                                  //~1A07I~
	UTRACEP("jniCmd cmd=%s,opd=%s\n",pcmd,popd);                   //~1A07R~
//  wxe_strcmd(pcmd,popd);                                         //~vaisR~
    xxemain_scrstrcmd(pcmd,popd);                                  //~vaisI~
    ufree(pcmd);                                                   //~v6dgI~
    ufree(popd);                                                   //~v6dgI~
    return;                                                        //~1A07I~
}                                                                  //~1A07I~
//******************************************************************//~vay0I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniUserMsg           //~1A18I~
  (JNIEnv *Penv, jclass Pthis, jint Pmsgid, jlong Pparm)           //~1A18I~
{                                                                  //~vay0I~
	void jniUserMsg(JNIEnv *Penv, jclass Pthis, jint Pmsgid, jlong Pparm);//~vay0I~
	NDK_TRY_CATCH(Penv,"jniUserMsg",                               //~vay0I~
					jniUserMsg(Penv,Pthis,Pmsgid,Pparm);           //~vay0R~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniUserMsg(JNIEnv *Penv, jclass Pthis, jint Pmsgid, jlong Pparm)//~vay0I~
{                                                                  //~1A18I~
    GdkEventClient *pec;                                           //~1A18I~
//********************                                             //~1A18I~
	UTRACEP("jniuserMsg msg=%x,parm=%x\n",Pmsgid,Pparm);           //~1A18R~
    pec=(GdkEventClient*)(ULONG)Pparm;                             //~1A18I~
    switch(Pmsgid)                                                 //~1A18I~
    {                                                              //~1A18I~
    case ID_SCREEN_DRAW:	//dragout file setup                   //~1A18I~
    	break;                                                     //~1A18I~
    case ID_FILE_DNDDRAGOUTSETUP:	//dragout file setup           //~1A18I~
    	gxeview_clientevent(0/*Widget*/,pec,0/*user_data*/);       //~1A18I~
    	break;                                                     //~1A18I~
	case ID_FILE_DNDCOPY:                                          //~1A18I~
    	gxeview_clientevent(0/*Widget*/,pec,0/*user_data*/);       //~1A21I~
    	break;                                                     //~1A18I~
    }                                                              //~1A18I~
//  LOGPD("@@@@ jniUserMsg free pevc=%p\n",pec);                   //~vay1R~
    ufree(pec);                                                    //~1A18I~
    return;                                                        //~1A18I~
}                                                                  //~1A18I~
//******************************************************************//~vay0I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniDndRepCopy        //~1A22I~
  (JNIEnv *Penv, jclass Pthis, jstring Pcmd)                       //~1A22I~
{                                                                  //~vay0I~
	void jniDndRepCopy(JNIEnv *Penv, jclass Pthis, jstring Pcmd);  //~vay0I~
	NDK_TRY_CATCH(Penv,"jniDndRepCopy",                            //~vay0I~
					jniDndRepCopy(Penv,Pthis,Pcmd);                //~vay0R~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
void jniDndRepCopy(JNIEnv *Penv, jclass Pthis, jstring Pcmd)       //~vay0I~
{                                                                  //~1A22I~
    char *pcmd;                                                    //~1A22I~
    char cmd[_MAX_PATH*2+32];                                      //~1A22I~
//********************                                             //~1A22I~
    pcmd=jstring2char(Penv,Pcmd);                                  //~1A22I~
	UTRACEP("jniDndRepcopy cmd=%s\n",pcmd);                        //~1A22I~
    sprintf(cmd,"%s /y",pcmd);//append replace option              //~1A22R~
    wxe_strcmd("XCOPY",cmd);                                       //~1A22R~
    ufree(pcmd);                                                   //~v6dgI~
}                                                                  //~1A22I~
//******************************************************************//~vay0R~
JNIEXPORT jint JNICALL Java_com_xe_Axe_AxeJNI_jniIsValidCharset    //~vay0R~
  (JNIEnv *Penv, jclass Pthis, jstring Pcharset)                   //~vad2R~
{                                                                  //~vay0I~
	int rc=-1;                                                     //~vay0I~
	jint jniIsValidCharset(JNIEnv *Penv, jclass Pthis, jstring Pcharset);//~vay0I~
	NDK_TRY_CATCH(Penv,"jniIsValidCharset",                        //~vay0I~
					rc=jniIsValidCharset(Penv,Pthis,Pcharset);     //~vay0I~
        		 );                                                //~vay0I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
    return rc;                                                     //~vay0I~
}                                                                  //~vay0I~
//******                                                           //~vay0I~
jint jniIsValidCharset(JNIEnv *Penv, jclass Pthis, jstring Pcharset)//~vay0I~
{                                                                  //~vad2R~
    char *pcs;                                                     //~vad2R~
    int rc;                                                        //~vad2R~
//********************                                             //~vad2R~
    pcs=jstring2char(Penv,Pcharset);                               //~vad2R~
	rc=ucvext_icugetmbconverter(0,pcs,0/*Ppconverter=0:chk only*/);//~vad2R~
    ufree(pcs);                                                    //~vad2R~
	UTRACEP("jniIsValidCharset charset=%s,rc=%d-->%d\n",pcs,rc,rc==0);//~vad2R~
    return rc==0;                                                  //~vad2R~
}                                                                  //~vad2R~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniGetCurrentFilename//~vay7I~
  (JNIEnv *Penv, jclass Pthis)                                     //~vay7I~
{                                                                  //~vay7I~
//********************                                             //~vay7I~
	void jniGetCurrentFilename(JNIEnv *Penv, jclass Pthis);        //~vay7I~
	NDK_TRY_CATCH(Penv,"jniGetCurrentFilename",                    //~vay7I~
					jniGetCurrentFilename(Penv,Pthis);             //~vay7I~
        		 );                                                //~vay7I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
}                                                                  //~vay7I~
void jniGetCurrentFilename(JNIEnv *Penv,jclass Pthis)              //~vay7I~
{                                                                  //~vay7I~
	PUCLIENTWE pcw;                                                //~vay7I~
    PUFILEC pfc;                                                   //~vay7I~
    PUFILEH pfh;                                                   //~vay7I~
    int rc;                                                        //~vay7I~
    char fnm[_MAX_PATH*2]={0};                                     //~vay7R~
//***********************                                          //~vay7I~
    pcw=scrgetcw(0); 	//get active                               //~vay7I~
    switch(pcw->UCWtype)                                           //~vay7R~
	{                                                              //~vay7I~
	case UCWTDIR:                                                  //~vay7I~
    	rc=0;                                                      //~vay7R~
        break;                                                     //~vay7I~
	case UCWTFILE:                                                 //~vay7I~
    	rc=1;                                                      //~vay7R~
        break;                                                     //~vay7I~
    default:                                                       //~vay7I~
    	rc=-1;                                                     //~vay7R~
	}                                                              //~vay7I~
    if (rc>=0)                                                     //~vay7R~
    {                                                              //~vay7I~
    	pfc=pcw->UCWpfc;                                           //~vay7I~
    	pfh=pfc->UFCpfh;                                           //~vay7I~
    	strcpy(fnm,pfh->UFHfilename);                              //~vay7M~
        if (pfh->UFHfilenamedddata)                                //~vay7I~
        {                                                          //~vay7I~
            UCHAR *pu8;                                            //~vay7I~
            int u8len,rc3;                                         //~vay7I~
            //******************                                   //~vay7I~
            rc3=xeutfcvdd2f(0,pfh->UFHfilenamedddata,pfh->UFHfilenamedddbcs,pfh->UFHfilenameddlen,&pu8,&u8len,NULL/*outdbcs*/);//~vay7I~
            if (rc3<4)                                             //~vay7I~
                UmemcpyZ(fnm,pu8,u8len);                           //~vay7I~
        }                                                          //~vay7I~
    }                                                              //~vay7I~
    SETSTATIC_STRING(Penv,mCurrentFilename,fnm);                   //~vay7I~
    SETSTATIC_INT(Penv,mCurrentFilenameType,rc);                   //~vay7I~
}                                                                  //~vc4qI~
//*****************************************************************//~vc4qR~
JNIEXPORT jint JNICALL Java_com_xe_Axe_AxeJNI_j2cOnActivityResult  //~vc4qI~
  (JNIEnv *Penv, jclass Pthis, jint PreqID,jint Prc,jstring Pparm,jstring PstrUri,jstring Ppath)//~vc4qI~
{                                                                  //~vc4qI~
	int rc=0;                                                     //~vc4qI~
    UTRACEP("%s:reqid=%d,Prc=%d,Pparm=%p,PstrUri=%p,Ppath=%p\n",UTT,(int)PreqID,(int)Prc,Pparm,PstrUri,Ppath);//~vc4qI~
	void j2cOnActivityResult(JNIEnv *Penv, jclass Pthis, jint PreqID,jint Prc,jstring Pparm,jstring PstrUri,jstring Ppath);//~vc4qI~
	NDK_TRY_CATCH(Penv,"j2cOnActivityResult",                      //~vc4qI~
					j2cOnActivityResult(Penv,Pthis,PreqID,Prc,Pparm,PstrUri,Ppath);//~vc4qI~
        		 );                                                //~vc4qI~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vc4qR~
	return rc;                                                     //~vc4qI~
}                                                                  //~vc4qI~
//*****************************************************************//~vc4qI~
void j2cOnActivityResult(JNIEnv *Penv, jclass Pthis, jint PreqID,jint Prc,jstring Pparm,jstring PstrUri,jstring Ppath)//~vc4qI~
{                                                                  //~vc4qI~
    char *parm,*strUri,*path;                                      //~vc4qI~
    int swfreeparm=0,swfreestruri=0,swfreepath=0;                  //~vc4qI~
//*************                                                    //~vc4qI~
    if (Pparm)                                                     //~vc4qI~
    {                                                              //~vc4qI~
	    parm=jstring2char(Penv,Pparm);                             //~vc4qI~
        swfreeparm=1;                                              //~vc4qI~
    }                                                              //~vc4qI~
    else                                                           //~vc4qI~
    	parm="";                                                   //~vc4qI~
    if (PstrUri)                                                   //~vc4qI~
    {                                                              //~vc4qI~
    	strUri=jstring2char(Penv,PstrUri);                         //~vc4qI~
        swfreestruri=1;                                            //~vc4qI~
    }                                                              //~vc4qI~
    else                                                           //~vc4qI~
    	strUri="";                                                 //~vc4qI~
    if (Ppath)                                                     //~vc4qI~
    {                                                              //~vc4qI~
	    path=jstring2char(Penv,Ppath);                             //~vc4qI~
        swfreepath=1;                                              //~vc4qI~
    }                                                              //~vc4qI~
    else                                                           //~vc4qI~
    	path="";                                                   //~vc4qI~
    UTRACEP("j2cOnActivityResult env=%p,this=%p,reqID=%d,rc=%d,parm=%p=%s,strUri=%p=%s,path=%p=%s\n",Penv,Pthis,(int)PreqID,(int)Prc,parm,parm,strUri,strUri,path,path);//~vc4qR~
    xefc7_OnActivityResult((int)PreqID,(int)Prc,parm,strUri,path); //~vc4qR~
    if (swfreeparm)                                                //~vc4qR~
	    ufree (parm);                                              //~vc4qI~
    if (swfreestruri)                                              //~vc4qR~
	    ufree (strUri);                                            //~vc4qI~
    if (swfreepath)                                                //~vc4qR~
	    ufree (path);                                              //~vc4qI~
    UTRACE_FLUSH("j2cOnActivityResult exit");                      //~vc4qR~
}                                                                  //~vay7I~//~vc4qI~
//******************************************************************//~vby8I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniNotifyOpenDocResult//~vby8I~
  (JNIEnv *Penv, jclass Pthis, jint Popt, jstring Pfpath, jint PrcOpt, jstring PrcString, jbyteArray PrcByte)//~vby8I~
{                                                                  //~vby8I~
	void jniNotifyOpenDocResult(JNIEnv *Penv, jclass Pthis, jint Popt, jstring Pfpath, jint PrcOpt, jstring PrcString, jbyteArray PrcByte);//~vby8I~
	NDK_TRY_CATCH(Penv,"jniNotifyOpenDocResult",                   //~vby8R~
					jniNotifyOpenDocResult(Penv,Pthis,Popt,Pfpath,PrcOpt,PrcString,PrcByte);//~vby8I~
        		 );                                                //~vby8I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vby8I~
}                                                                  //~vby8I~
//************************************************************     //~vby8R~
void jniNotifyOpenDocResult                                        //~vby8I~
  (JNIEnv *Penv, jclass Pthis, jint Popt, jstring Pfpath, jint PrcOpt, jstring PrcString, jbyteArray PrcByte)//~vby8I~
{                                                                  //~vby8I~
//  char *pbuff=0,*pfpath,*parmBuffer;                             //~vby8R~//~vbymR~
    char *pbuff=0,*pfpath            ;                             //~vbymI~
//  int sz=0;                                                      //~vby8R~//~vbymR~
    char fpath[_MAX_PATH];                                         //~vby8I~
//********************                                             //~vby8I~
	UTRACEP("%s:entry opt=0x%x,rcOpt=0x%x\n",UTT,Popt,PrcOpt);     //~vby8I~
	pfpath=fpath;                                                  //~vby8I~
    jstr2UTFcharCopy(Penv,Pfpath,&pfpath);   //copy to fpath       //~vby8R~
	UTRACEP("%s:fpath=%s\n",UTT,fpath);                            //~vby8R~
//  parmBuffer=c2j_getOpenDocParm();                               //~vby8R~
//  parmBuffer=c2j_notifiedOpenDocResult(PrcOpt);                  //~vby8I~//~vbymR~
               c2j_notifiedOpenDocResult(PrcOpt);                  //~vbymI~
    if (PrcString)                                                 //~vby8I~
    {                                                              //~vby8I~
//      sz=jstr2UTFcharCopy(Penv,PrcString,&pbuff);	//pbuff:umalloced addr//~vby8R~//~vbymR~
           jstr2UTFcharCopy(Penv,PrcString,&pbuff);	//pbuff:umalloced addr//~vbymI~
    }                                                              //~vby8I~
    else                                                           //~vby8I~
    if (PrcByte)                                                   //~vby8I~
    {                                                              //~vby8I~
//      sz=jbyte2byteCopy(Penv,PrcByte,&pbuff);	//pbyte:umalloced addr//~vby8R~//~vbymR~
           jbyte2byteCopy(Penv,PrcByte,&pbuff);	//pbyte:umalloced addr//~vbymI~
    }                                                              //~vby8I~
//  ufile_openDocResult(fpath,Popt,PrcOpt,pbuff,sz);               //~vby8R~
    if (pbuff)	 //pbuff malloced                                  //~vby8R~
	    ufree(pbuff);                                              //~vby8R~
}                                                                  //~vby8I~
//******************************************************************//~vby8I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniNotifyfgetsDocResult//~vby8R~
  (JNIEnv *Penv, jclass Pthis, jint Popt, jstring Pfpath, jstring PrcString, jint Pbuffsz)//~vby8I~
{                                                                  //~vby8I~
	void jniNotifyfgetsDocResult(JNIEnv *Penv, jclass Pthis, jint Popt, jstring Pfpath, jstring PrcString, jint Pbuffsz);//~vby8R~
	NDK_TRY_CATCH(Penv,"jniNotifyfgetsDocResult",                  //~vby8R~
					jniNotifyfgetsDocResult(Penv,Pthis,Popt,Pfpath,PrcString,Pbuffsz);//~vby8R~
        		 );                                                //~vby8I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vby8I~
}                                                                  //~vby8I~
//************************************************************     //~vby8I~
void jniNotifyfgetsDocResult                                       //~vby8R~
  (JNIEnv *Penv, jclass Pthis, jint Popt, jstring Pfpath, jstring PrcString,jint Pbuffsz)//~vby8R~
{                                                                  //~vby8I~
//  char *pbuff=0,*pfpath,*parmBuffer,*pu8work;                    //~vby8R~//~vbymR~
    char          *pfpath,*parmBuffer,*pu8work;                    //~vbymI~
    char fpath[_MAX_PATH];                                         //~vby8I~
//********************                                             //~vby8I~
	UTRACEP("%s:entry opt=0x%x,buffsz=%d\n",UTT,Popt,Pbuffsz);     //~vby8R~
	pfpath=fpath;                                                  //~vby8I~
    jstr2UTFcharCopy(Penv,Pfpath,&pfpath);   //copy to fpath       //~vby8I~
	UTRACEP("%s:fpath=%s\n",UTT,fpath);                            //~vby8I~
    parmBuffer=c2j_get_fgetsDocParm();                             //~vby8R~
//  pbuff=parmBuffer;                                              //~vby8I~//~vbymR~
	jstr2UTFcharCopy(Penv,PrcString,&pu8work);	//u8work:umalloced addr//~vby8R~
    ustrncpyz(parmBuffer,pu8work,Pbuffsz);                         //~vby8I~
	ufree(pu8work);                                                //~vby8R~
    return;                                                        //~vby8I~
}                                                                  //~vby8I~
//******************************************************************//~vby8I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniNotifyfreadDocResult//~vby8R~
  (JNIEnv *Penv, jclass Pthis, jint Popt, jstring Pfpath, jbyteArray PrcByte, jint Preadlen)//~vby8I~
{                                                                  //~vby8I~
	void jniNotifyfreadDocResult(JNIEnv *Penv, jclass Pthis, jint Popt, jstring Pfpath, jbyteArray PrcByte, jint Preadlen);//~vby8I~
	NDK_TRY_CATCH(Penv,"jniNotifyfreadDocResult",                  //~vby8R~
					jniNotifyfreadDocResult(Penv,Pthis,Popt,Pfpath,PrcByte,Preadlen);//~vby8R~
        		 );                                                //~vby8I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vby8I~
}                                                                  //~vby8I~
//************************************************************     //~vby8I~
void jniNotifyfreadDocResult                                       //~vby8R~
  (JNIEnv *Penv, jclass Pthis, jint Popt, jstring Pfpath, jbyteArray PrcByte,jint Preadlen)//~vby8R~
{                                                                  //~vby8I~
    char *pbuff=0,*pfpath,*parmBuffer;                             //~vby8R~
    char fpath[_MAX_PATH];                                         //~vby8I~
//********************                                             //~vby8I~
	UTRACEP("%s:entry opt=0x%x,buffsz=%d\n",UTT,Popt,Preadlen);    //~vby8R~
	pfpath=fpath;                                                  //~vby8I~
    jstr2UTFcharCopy(Penv,Pfpath,&pfpath);   //copy to fpath       //~vby8I~
	UTRACEP("%s:fpath=%s\n",UTT,fpath);                            //~vby8I~
    parmBuffer=c2j_notified_freadDocResult(Preadlen);              //~vby8R~
    pbuff=parmBuffer;                                              //~vby8I~
	jbyte2byteCopy(Penv,PrcByte,&pbuff);                           //~vby8R~
//  memcpy(parmBuffer,pcopybuff,Preadlen);                           //~vby8R~
	UTRACED("byteBuff",pbuff,Preadlen);                            //~vby8I~
//	ufree(pcopybuff);                                              //~vby8R~
}                                                                  //~vby8I~
//******************************************************************//~vby8I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniNotifyfwriteDocResult//~vby8I~
  (JNIEnv *Penv, jclass Pthis, jint Popt, jstring Pfpath,jint Pwritelen)//~vby8R~
{                                                                  //~vby8I~
	void jniNotifyfwriteDocResult(JNIEnv *Penv, jclass Pthis, jint Popt,jstring Pfpath,jint Pwritelen);//~vby8R~
	NDK_TRY_CATCH(Penv,"jniNotifyfwriteDocResult",                 //~vby8I~
					jniNotifyfwriteDocResult(Penv,Pthis,Popt,Pfpath,Pwritelen);//~vby8R~
        		 );                                                //~vby8I~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~vby8I~
}                                                                  //~vby8I~
//************************************************************     //~vby8I~
void jniNotifyfwriteDocResult                                      //~vby8I~
  (JNIEnv *Penv, jclass Pthis, jint Popt, jstring Pfpath,jint Pwritelen)//~vby8I~
{                                                                  //~vby8I~
    char *pfpath;                                                  //~vby8R~
    char fpath[_MAX_PATH];                                         //~vby8I~
//********************                                             //~vby8I~
	UTRACEP("%s:entry opt=0x%x,buffsz=%d\n",UTT,Popt,Pwritelen);   //~vby8R~
	pfpath=fpath;                                                  //~vby8I~
    jstr2UTFcharCopy(Penv,Pfpath,&pfpath);   //copy to fpath       //~vby8I~
	UTRACEP("%s:fpath=%s\n",UTT,fpath);                            //~vby8I~
    c2j_notified_fwriteDocResult(Pwritelen);                       //~vby8R~
}                                                                  //~vby8I~
//******************************************************************//~v77eI~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniNotifyopendirDocResult//~v77eI~
  (JNIEnv *Penv, jclass Pthis, jint Popt, jstring Pfpath,jint Pfd)//~v77eI~
{                                                                  //~v77eI~
	void jniNotifyopendirDocResult(JNIEnv *Penv, jclass Pthis, jint Popt,jstring Pfpath,jint Pfd);//~v77eR~
	NDK_TRY_CATCH(Penv,"jniNotifyopendirDocResult",                //~v77eI~
					jniNotifyopendirDocResult(Penv,Pthis,Popt,Pfpath,Pfd);//~v77eR~
        		 );                                                //~v77eI~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~v77eI~
}                                                                  //~v77eI~
//************************************************************     //~v77eI~
void jniNotifyopendirDocResult                                     //~v77eI~
  (JNIEnv *Penv, jclass Pthis, jint Popt, jstring Pfpath,jint Pfd) //~v77eI~
{                                                                  //~v77eI~
    char *pfpath;                                                  //~v77eI~
    char fpath[_MAX_PATH];                                         //~v77eI~
//********************                                             //~v77eI~
	UTRACEP("%s:entry opt=0x%x,fd=%d\n",UTT,Popt,Pfd);             //~v77eR~
	pfpath=fpath;                                                  //~v77eI~
    jstr2UTFcharCopy(Penv,Pfpath,&pfpath);   //copy to fpath       //~v77eI~
	UTRACEP("%s:fpath=%s\n",UTT,fpath);                            //~v77eI~
    c2j_notified_opendirDocResult(Pfd);                            //~v77eI~
}                                                                  //~v77eI~
//******************************************************************//~v77hI~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniNotifycopyDocResult//~v77hI~
  (JNIEnv *Penv, jclass Pthis, jint Popt, jint PerrSrc,jint PerrTgt)//~v77hI~
{                                                                  //~v77hI~
	void jniNotifycopyDocResult(JNIEnv *Penv, jclass Pthis, jint Popt,jint PerrSrc,jint PerrTgt);//~v77hI~
	NDK_TRY_CATCH(Penv,"jniNotifycopyDocResult",                   //~v77hR~
					jniNotifycopyDocResult(Penv,Pthis,Popt,PerrSrc,PerrTgt);//~v77hR~
        		 );                                                //~v77hI~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~v77hI~
}                                                                  //~v77hI~
//************************************************************     //~v77hI~
void jniNotifycopyDocResult                                        //~v77hI~
  (JNIEnv *Penv, jclass Pthis,jint Popt,jint PerrSrc,jint PerrTgt) //~v77hI~
{                                                                  //~v77hI~
//********************                                             //~v77hI~
	UTRACEP("%s:entry opt=0x%x,errSrc=%d,errTgt=%d\n",UTT,Popt,PerrSrc,PerrTgt);//~v77hI~
    c2j_notified_copyDocResult(PerrSrc,PerrTgt);                   //~v77hI~
}                                                                  //~v77hI~
//******************************************************************//~v77mI~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniNotifystatDocResult//~v77mI~
  (JNIEnv *Penv, jclass Pthis, jint Popt, jstring Pfpath,jint Pfd) //~v77mI~
{                                                                  //~v77mI~
	void jniNotifystatDocResult(JNIEnv *Penv, jclass Pthis, jint Popt,jstring Pfpath,jint Pfd);//~v77mI~
	NDK_TRY_CATCH(Penv,"jniNotifystatDocResult",                   //~v77mI~
					jniNotifystatDocResult(Penv,Pthis,Popt,Pfpath,Pfd);//~v77mI~
        		 );                                                //~v77mI~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~v77mI~
}                                                                  //~v77mI~
//************************************************************     //~v77mI~
void jniNotifystatDocResult                                        //~v77mI~
  (JNIEnv *Penv, jclass Pthis, jint Popt, jstring Pfpath,jint Pfd) //~v77mI~
{                                                                  //~v77mI~
    char *pfpath;                                                  //~v77mI~
    char fpath[_MAX_PATH];                                         //~v77mI~
//********************                                             //~v77mI~
	UTRACEP("%s:entry opt=0x%x,fd=%d\n",UTT,Popt,Pfd);             //~v77mI~
	pfpath=fpath;                                                  //~v77mI~
    jstr2UTFcharCopy(Penv,Pfpath,&pfpath);   //copy to fpath       //~v77mI~
	UTRACEP("%s:fpath=%s\n",UTT,fpath);                            //~v77mI~
    c2j_notified_statDocResult(Pfd);                               //~v77mI~
}                                                                  //~v77mI~
//******************************************************************//~v77mI~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniNotifygetDocPathResult//~v77mI~
  (JNIEnv *Penv, jclass Pthis, jint Popt, jstring Pfpath)          //~v77mI~
{                                                                  //~v77mI~
	void jniNotifygetDocPathResult(JNIEnv *Penv, jclass Pthis, jint Popt,jstring Pfpath);//~v77mI~
	NDK_TRY_CATCH(Penv,"jniNotifygetDocPathResult",                //~v77mI~
					jniNotifygetDocPathResult(Penv,Pthis,Popt,Pfpath);//~v77mR~
        		 );                                                //~v77mI~
	UTRACEP_FLUSH("%s:return to AxeJNI\n",UTT);                    //~v77mI~
}                                                                  //~v77mI~
//************************************************************     //~v77mI~
void jniNotifygetDocPathResult                                     //~v77mR~
  (JNIEnv *Penv, jclass Pthis, jint Popt, jstring Pfpath)          //~v77mI~
{                                                                  //~v77mI~
    char *pfpath;                                                  //~v77mI~
    char fpath[_MAX_PATH];                                         //~v77mI~
//********************                                             //~v77mI~
	UTRACEP("%s:entry opt=0x%x\n",UTT,Popt);                       //~v77mI~
	pfpath=fpath;                                                  //~v77mI~
    jstr2UTFcharCopy(Penv,Pfpath,&pfpath);   //copy to fpath       //~v77mI~
	UTRACEP("%s:fpath=%s\n",UTT,fpath);                            //~v77mI~
//  c2j_notified_getDocPathResult(fpath);                          //~v77mI~//~v5crR~
    c2j_notified_getDocPathResult(Popt,fpath);                     //~v5crI~
}                                                                  //~v77mI~
