//*CID://+vc60R~:                                   update#=  347; //~vc60R~
//**************************************************************** //~1610I~
//vc60 2023/08/03 mediaview as openWith                            //~vc60I~
//vc5r 2023/07/25 try /sdcard for realpath for api<30              //~vc5rI~
//vc5p 2023/07/22 Dump open with for //Axe/x1.(mime type=text/x-csrc for ////Axe/x1.c. normal file is file:///sdcard/...)//~vc5pI~
//vc5c 2023/07/04 display directory set to access by ACTION_OPEN_DOCUMENT_TREE//~vc5cI~
//v77w:230519 uri-->path is avalable from api30(android11:R) and readdir using fd gotten by openDescriptor returns null//~v77wI~
//v77m:230429 ARM:try stat(fpath) by fstat(fd) for ufstat          //~v77mI~
//v77h:230424 ARM;copy                                             //~v77hI~
//v77f:230424 ARM;rmdir                                            //~v77fI~
//v77e:230424 ARM;try fd for opendir/readdir                       //~v77eI~
//v77c:230422 ARM;mkdir                                            //~v77cI~
//vby8:230415 (ARM)open document file                              //~vby8I~
//vby7:230415 (ARM)split ufile1l.c to ufiledoc.c                   //~vby7I~
//vby4:230402 (ARM)shared resource support by //shareName defined by SP(ShortPath) cmd.//~vby4I~
//vbrh:200824 (AXE)notify ruler changed to Axe dialog              //~vbrhI~
//vay0:140710 (Axe)jni exception handling                          //~vay0I~
//vak2:130822 Axe:ndk-r9 warning                                   //~vak2I~
//vaba:120125 (Axe)crash for ucs4 when android4(getTextWidth output double length of width tbl)//~vabaI~
//**************************************************************** //~1610I~
#include <stdio.h>                                                 //~1610I~
#include <stdlib.h>                                                //~1803I~
#include <string.h>                                                //~1614I~
#include <dirent.h>   //for func in ufile1l.h                      //~vby4I~
#include <unistd.h>   //for func in ufile1l.h                      //~v77eI~
//**********************                                           //~1610I~
                                                                   //~1614I~
#define JNIGBL                                                     //~1616M~
#include "jnig.h"                                                  //~1616M~
#include "jnia.h"                                                  //~1616I~
#include "jnic2j.h"                                                //~1826I~
#include "jnisigh.h"                                               //~vay0I~
#include <android/log.h>                                           //~1616M~
                                                                   //~1616I~
#include <ulib.h>                                                  //~1620M~
#include <uque.h>                                                  //~1A06I~
#include <ucvucs.h>                                                //~1620M~
#include <utrace.h>                                                //~1623I~
#include <ufile.h>                                                 //~1A06I~
#include <uerr.h>                                                  //~1A06I~
#include <uftp.h>                                                  //~1A06I~
#include <uproc.h>                                                 //~1A06I~
#include <ukbdl.h>                                                 //~1A06I~
#include <utf22.h>                                                 //~1A13I~
#include <ufile1l.h>                                               //~vby4I~
#include <ufiledoc.h>                                              //~vby7R~
#include <ualloc.h>                                                                   //~1620I~
#include <xxedef.h>                                                //~1620R~
#include <xxecsub.h>                                               //~1620I~
#include <xxemain.h>                                               //~1716I~
#include <xxexei.h>                                                //~1719I~
#include <xe.h>                                                    //~1A22I~
//***************************************************************  //~1620R~
#define PREFIX_ERROR ':'                                           //~vby4I~
//***************************************************************  //~vby8I~
static char *SopenDocParmBuffer;                                   //~vby8I~
static char *SfgetsDocParmBuffer;                                  //~vby8I~
static char *SfreadDocParmBuffer;                                  //~vby8R~
static char *SgetDocPath;                                          //~v77wI~
static int   SgetDocPathOpt;                                       //~vc5rI~
static int   SopenDocOptRC;                                        //~vby8I~
static int   SfreadDocReadLen;                                     //~vby8I~
static int   SfwriteDocWriteLen;                                   //~vby8I~
static int   SopendirFD;                                           //~v77eI~
static int   SstatFD;                                              //~v77mI~
static int   ScopyErrSrc,ScopyErrTgt;                              //~v77hI~
//***************************************************************  //~vby4I~
//*layout contains utf8 str and length at set_text                 //~1620R~
//***************************************************************  //~1620I~
//void usetmonospace(int Popt,PangoLayout *Pplayout,char *Ppdata,char *Ppdbcs,int Plen,int Pcellw)//~1620R~//~1718R~
//{                                                                  //~1617I~//~1718R~
//    static jmethodID staticMethod_usetmonospace;                   //~1620I~//~1718R~
//    int *ucstb,*offsettb,ucsctr;                                   //~1716R~//~1718R~
//    jbyteArray jbytes;                                             //~1715I~//~1718R~
//    jintArray jucstb,joffstb;                                      //~1715R~//~1718R~
//    JNIEnv *penv;                                                  //~1714I~//~1718R~
////****************************                                     //~1620I~//~1718R~
//    penv=getThreadEnv();                                           //~1714I~//~1718R~
//    UTRACEP("usetmonospace,env=%p,Pcellw=%d\n",penv,Pcellw);       //~1715R~//~1718R~
//    UTRACED("usetmonospace,dbcs",Ppdbcs,Plen);                     //~1715I~//~1718R~
//    ucstb=Pplayout->jni_ucstb;                                     //~1620I~//~1718R~
//    ucsctr=Pplayout->jni_ucsctr;                                   //~1620I~//~1718R~
//    UTRACED("usetmonospace,ucstb",ucstb,ucsctr*sizeof(int));       //~1715I~//~1718R~
//    offsettb=Pplayout->jni_offsettb;    //output;set by Axecsub2:usetmonospace//~1715R~//~1718R~
//    memset(offsettb,0,ucsctr*sizeof(int));                         //~1716R~//~1718R~
//    jbytes=byte2jbyte(penv,Ppdbcs,Plen);                           //~1715R~//~1718R~
//    jucstb=int2jint(penv,ucstb,ucsctr);                            //~1715R~//~1718R~
//    joffstb=int2jint(penv,offsettb,ucsctr);                        //~1716R~//~1718R~
//    C2J_GETMETHODID_RETIFERR(penv,usetmonospace,"(I[I[BIII[I)V");  //~1714R~//~1718R~
//    C2J_VOID(penv,staticMethod_usetmonospace,Popt,jucstb,jbytes,ucsctr,Plen,Pcellw,joffstb);//~1715R~//~1718R~
//    Pplayout->jni_ligature=0;                                      //~1620R~//~1718R~
//    jint2int(penv,joffstb,0,ucsctr,offsettb);                      //~1716R~//~1718R~
//    unrefLocal(penv,jbytes);                                       //~1715R~//~1718R~
//    unrefLocal(penv,jucstb);                                       //~1715R~//~1718R~
//    unrefLocal(penv,joffstb);                                      //~1715R~//~1718R~
//    return;                                                        //~1620R~//~1718R~
//}                                                                  //~1620I~//~1718R~
void c2j_gettextwidths(int Popt,PangoLayout *Pplayout,float **Ppfwidthtb)//~1718R~
{                                                                  //~1718I~
    static jmethodID staticMethod_gettextwidths;                   //~1718R~
    int *ucstb,ucsctr;                                             //~1718R~
    float *fwidthtb;                                               //~1718R~
    jintArray jucstb;                                              //~1A14R~
    jfloatArray jwidthtb;                                          //~1A14I~
    JNIEnv *penv;                                                  //~1718I~
//****************************                                     //~1718I~
    penv=getThreadEnv();                                           //~1718I~
    UTRACEP("usetmonospace,env=%p\n",penv);                        //~1718I~
    ucstb=Pplayout->jni_ucstb;                                     //~1718I~
    ucsctr=Pplayout->jni_ucsctr;                                   //~1718I~
    UTRACED("usetmonospace,ucstb",ucstb,ucsctr*sizeof(int));       //~1718I~
    fwidthtb=(float*)getvoidtb(GETTB_TEXTWIDTHTB,ucsctr*sizeof(float));//~1718R~
    memset(fwidthtb,0,ucsctr*sizeof(int));                         //~1718R~
                                                                   //~1A14I~
//    jucstb=int2jint(penv,ucstb,ucsctr);                            //~1718I~//~1A14R~
    jucstb=gblint2jint(penv,ucstb,ucsctr);                         //~1A14R~
//    jwidthtb=newjfloat(penv,ucsctr);                               //~1718R~//~1A14R~
//  jwidthtb=getgbljfloat(penv,ucsctr);                            //~1A14I~//~vabaR~
    jwidthtb=getgbljfloat(penv,ucsctr*2);//for android4,ucs4 needs 2 entry//~vabaI~
    C2J_GETMETHODID_RETIFERR(penv,gettextwidths,"([I[F)V");        //~1718R~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~vby4I~
    C2J_VOID(penv,gettextwidths,jucstb,jwidthtb);                  //~1718R~
//  LOGPD("@@@@c2j_gettextwidth jucstb=%p\n",jucstb);              //~vay0R~
//  LOGPD("@@@@c2j_gettextwidth jwidthtb=%p\n",jwidthtb);          //~vay0R~
    Pplayout->jni_ligature=0;                                      //~1718I~
    jfloat2float(penv,jwidthtb,0,ucsctr,fwidthtb);                 //~1718R~
//    unrefLocal(penv,jucstb);                                       //~1718I~//~1A14R~
//    unrefLocal(penv,jwidthtb);                                     //~1718R~//~1A14R~
    *Ppfwidthtb=fwidthtb;                                          //~1718R~
    return;                                                        //~1718I~
}                                                                  //~1718I~
////***************************************************************  //~1620I~//~1719R~
//int usetmonospace_ligature(int Popt,PangoLayout *Pplayout,char *Ppdata,char *Ppdbcs,//~1620I~//~1719R~
//                            int Plen/*column width*/,int Pcellw)   //~1620R~//~1719R~
//{                                                                  //~1620I~//~1719R~
//    static jmethodID staticMethod_usetmonospace_ligature;          //~1620R~//~1719R~
//    int *ucstb;                                                    //~1620R~//~1719R~
//    int strsz,ucsctr,colw;                                         //~1620R~//~1719R~
//    float scale;                                                   //~1620I~//~1719R~
//    jintArray jints;                                               //~1715I~//~1719R~
//    JNIEnv *penv;                                                  //~1714I~//~1719R~
////****************************                                     //~1620I~//~1719R~
//    penv=getThreadEnv();                                           //~1714I~//~1719R~
//    UTRACEP("usetmonospace_ligature env=%p\n",penv);               //~1714R~//~1719R~
//    ucstb=Pplayout->jni_ucstb;                                     //~1620I~//~1719R~
//    ucsctr=Pplayout->jni_ucsctr;                                   //~1620I~//~1719R~
//    jints=int2jint(penv,ucstb,ucsctr);                             //~1715I~//~1719R~
//    C2J_GETMETHODID_RETIFERR_WITHRC(penv,4,usetmonospace_ligature,"(I[III)I");//~1714R~//~1719R~
//    strsz=(int)C2J_INT(penv,usetmonospace_ligature,Popt,jints,ucsctr,Pcellw);//~1715R~//~1718R~//~1719R~
//    Pplayout->jni_ligature=1;                                      //~1620R~//~1719R~
//    colw=Pcellw*Plen;                                              //~1620R~//~1719R~
//    if (colw<strsz)                                                //~1620I~//~1719R~
//        scale=(float)colw/strsz;                                   //~1620I~//~1719R~
//    else                                                           //~1620I~//~1719R~
//        scale=0;    //no scale                                     //~1620I~//~1719R~
//    Pplayout->jni_scale=scale;                                     //~1620R~//~1719R~
//    unrefLocal(penv,jints);                                        //~1715R~//~1719R~
//    return 0;                                                      //~1620R~//~1719R~
//}                                                                  //~1620I~//~1719R~
////***************************************************************  //~1621I~//~1719R~
//void c2j_ugetfontmetrics(char *Pfontname,int *Ppwidth,int *Ppheight,int *Ppmonospace)//~1621I~//~1718R~//~1719R~
//{                                                                  //~1621I~//~1719R~
//    static jmethodID staticMethod_ugetfontmetrics;                 //~1621I~//~1719R~
//    jstring jfontname;                                             //~1715R~//~1719R~
//    JNIEnv *penv;                                                  //~1714I~//~1719R~
////****************************                                     //~1621I~//~1719R~
//    penv=getThreadEnv();                                           //~1714I~//~1719R~
//    UTRACEP("ugetfontmetrics env=%p",penv);                        //~1714R~//~1719R~
//    jfontname=utf8z2jstring(penv,Pfontname);                        //~1715R~//~1718R~//~1719R~
//    C2J_GETMETHODID_RETIFERR(penv,ugetfontmetrics,"(Ljava/lang/String;)V");//~1715R~//~1718R~//~1719R~
//    C2J_VOID(penv,ugetfontmetrics,jfontname);//~1715R~             //~1718R~//~1719R~
//    *Ppwidth=GETSTATIC_INT(penv,Mfm_fontwidth);                    //~1718R~//~1719R~
//    *Ppheight=GETSTATIC_INT(penv,Mfm_fontheight);                  //~1718R~//~1719R~
//    *Ppmonospace=GETSTATIC_INT(penv,Mfm_monospace); //1:monospace,-1:ligature("if"!="fi")//~1719R~
//    return;                                                        //~1621I~//~1719R~
//}                                                                  //~1621I~//~1719R~
//***************************************************************  //~1716I~
//void gxedlg_init_getoutput(JNIEnv *Penv)                         //~1823R~
//{                                                                //~1823R~
//    int monospace;                                               //~1823R~
////****************************                                   //~1823R~
//    Mcellw=GETSTATIC_INT(Penv,Mcellw);                                    //~1716R~//~1823R~
//    Mcellh=GETSTATIC_INT(Penv,Mcellh);                                    //~1716R~//~1823R~
//    Mfontheight=GETSTATIC_INT(Penv,Mfontheight);                 //~1823R~
//    Mfontwidth=GETSTATIC_INT(Penv,Mfontwidth);                   //~1823R~
//    monospace=GETSTATIC_INT(Penv,Mmonospace);                                //~1716R~//~1718R~//~1823R~
//    Mmonospace=monospace==1;                                     //~1823R~
//    if (monospace<0)                                             //~1823R~
//        Gxxestat|=GXXES_FONTLIGATURE;                            //~1823R~
//    else                                                         //~1823R~
//        Gxxestat&=~GXXES_FONTLIGATURE;                           //~1823R~
//    MjniBaseLine=GETSTATIC_INT(Penv,MbaseLine);                  //~1823R~
//    UTRACEP("gxedlg_init output cellw=%d,cellh=%d,monospace=%d\n",Mcellw,Mcellh,monospace);//~1823R~
//    return;                                                      //~1823R~
//}                                                                //~1823R~
//********************************                                 //~1803I~
void parsefontname(char *Pfontstyle,char *Pfontname,char *Pstyle,int *Pfontsize)//~1803I~
{                                                                  //~1803I~
	char *pc;                                                      //~1803I~
//***************                                                  //~1803I~
	UTRACEP("parsefontnam=%s\n",Pfontstyle);                       //~1804I~
	*Pstyle=0;                                                     //~1803I~
    *Pfontsize=0;                                                  //~1803I~
    strcpy(Pfontname,Pfontstyle);                                  //~1803I~
	pc=strchr(Pfontname,';');	//fontname;fontstyle               //~1803R~
    if (!pc)                                                       //~1803I~
    	return;                                                    //~1803I~
    *pc++=0;                                                       //~1803I~
    strcpy(Pstyle,pc);                                             //~1803I~
	pc=strchr(Pstyle,';');     //..fontstyle;fontsize           //~1803R~//~1804R~
    if (!pc)                                                       //~1803I~
    	return;                                                    //~1803I~
    *pc++=0;                                                       //~1803I~
    *Pfontsize=atoi(pc);                                           //~1803I~
	UTRACEP("parsefontname %s-%s-%d\n",Pfontname,Pstyle,*Pfontsize);//~1804I~
    return;                                                        //~1803I~
}                                                                  //~1803I~
//********************************                                 //~1610I~//~1803M~
//*notify inidata after gxe_init                                   //~1803M~
//********************************                                 //~1803M~
void notifyinidata(JNIEnv *Penv)                                   //~1803R~
{                                                                  //~1803M~
    char fontname[sizeof(Mfontstyle)];	                           //~1803I~
    char fontstyle[sizeof(Mfontstyle)];                            //~1803I~
    int fontsize;                                                  //~1803I~
//************************************                             //~1803M~
	parsefontname(Mfontstyle,fontname,fontstyle,&fontsize);        //~1803I~
    SETSTATIC_STRING(Penv,Mfontstyle,fontname); //font style       //~1803I~
    SETSTATIC_STRING(Penv,Mstyle,fontstyle); //bold ,..            //~1803I~
//  SETSTATIC_INT(Penv,Mfontsize,fontsize);                        //~1822R~
    SETSTATIC_INT(Penv,Mfontheight,fontsize);                      //~1822I~
	SETSTATIC_INT(Penv,Mscrcmaxrow,Mscrcmaxrow);                   //~1803I~
	SETSTATIC_INT(Penv,Mscrcmaxcol,Mscrcmaxcol);                   //~1822R~
	SETSTATIC_INT(Penv,Mcellh0,Mcellh0);	//cell height          //~1803I~
    SETSTATIC_INT(Penv,Mcellw0,Mcellw0);	//cell width           //~1803R~
    SETSTATIC_INT(Penv,Mbgcolor,GDKCOLOR2COLOR(&Mbgcolor));//background color//~1803R~
    SETSTATIC_INT(Penv,Mrulercolor,GDKCOLOR2COLOR(&Mrulercolor));//background color//~1803R~
    SETSTATIC_INT(Penv,Museact,Museact);                           //~1803I~
    SETSTATIC_BOOLEAN(Penv,Mqexit,(Gwxestat & GWXES_OPT_QEXIT)!=0);//~1803R~
	SETSTATIC_BOOLEAN(Penv,Mligature,Mligature);                   //~1803I~
	SETSTATIC_INT(Penv,Mrulermode,Mrulermode);                     //~1803I~
    return;                                                        //~1803R~
}                                                                  //~1803M~
//***************************************************************  //~1621I~
//*from xxemain_init after iniget()                                //~1803I~
//***************************************************************  //~1803I~
void gxedlg_init(void)	//no need to implement                     //~1621I~
{                                                                  //~1621I~
    static jmethodID staticMethod_gxedlg_init;                     //~1715I~
    JNIEnv *penv;                                                  //~1715I~
//****************************                                     //~1715I~
    penv=getThreadEnv();                                           //~1715I~
	UTRACEP("gxedlg_init env=%p\n",penv);                          //~1715R~
    notifyinidata(penv);                                           //~1803I~
    C2J_GETMETHODID_RETIFERR(penv,gxedlg_init,"()V");              //~1715I~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~vby4I~
    C2J_VOIDVOID(penv,gxedlg_init);                       //~1716I~//~1718R~
	UTRACEP("gxedlg_init returned\n");                             //~1716I~
//  gxedlg_init_getoutput(penv);     //after createfont-->receive by notifychangedFont           //~1716R~//~1823R~
    return;                                                        //~1621I~
}                                                                  //~1621I~
//***************************************************************  //~1621I~
void gxepage_init(void) //no need to implement                     //~1621I~
{                                                                  //~1621I~
    static jmethodID staticMethod_gxepage_init;                    //~1715I~
    JNIEnv *penv;                                                  //~1715I~
//****************************                                     //~1715I~
    penv=getThreadEnv();                                           //~1715I~
    C2J_GETMETHODID_RETIFERR(penv,gxepage_init,"()V");             //~1715I~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~vby4I~
    C2J_VOIDVOID(penv,gxepage_init);                      //~1715I~//~1718R~
    return;                                                        //~1715I~
}                                                                  //~1621I~
//***************************************************************  //~1623I~
int wxe_uerrexitstdo(char *Ppmsg)                                 //~1623R~//~1719R~
{                                                                  //~1623I~
    static jmethodID staticMethod_wxe_uerrexitstdo;                //~1623I~
    jbyteArray jbytes;                                             //~1623I~
    JNIEnv *penv;                                                  //~1714I~
//****************************                                     //~1623I~
    penv=getThreadEnv();                                           //~1714I~
	UTRACEP("wxe_uerrexitstdo env=%p,msg=%s\n",penv,Ppmsg);        //~1714R~
    UTRACEP("wxe_uerrexitstdo entr msg=%s\n",Ppmsg);               //~1623I~
    jbytes=byte2jbyte(penv,Ppmsg,0);                               //~1714R~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,0,wxe_uerrexitstdo,"([B)V");       //~1714R~//~1719R~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~vby4I~
    C2J_VOID(penv,wxe_uerrexitstdo,jbytes);           //~1714R~    //~1718R~
    unrefLocal(penv,jbytes);                                       //~1715R~
    UTRACEP("wxe_uerrexitstdo return");                            //~1623I~
    jnisigh_uerrexit(Ppmsg);	//abort after cleanup sigh         //~vay0R~
    return 0;                                                      //~1623I~//~1719R~
}                                                                  //~1623I~
//***************************************************************  //~1621I~
void jni_exit(int Pexitcode)                                       //~1621R~
{                                                                  //~1621I~
    static jmethodID staticMethod_jni_exit;                        //~1621R~
    JNIEnv *penv;                                                  //~1714I~
//****************************                                     //~1621I~
    penv=getThreadEnv();                                           //~1714I~
    C2J_GETMETHODID_RETIFERR(penv,jni_exit,"(I)V");                //~1714R~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~vby4I~
    C2J_VOID(penv,jni_exit,Pexitcode);                //~1714R~    //~1718R~
    return;                                                        //~1621I~
}                                                                  //~1621I~
//**************************************************************** //~1A22I~
//*rc:ret code                                                     //~1A22I~
//**************************************************************** //~1A22I~
int umsgbox(char *Ppmsg,int Pflag)                                 //~1A22I~
{                                                                  //~1A22I~
    static jmethodID staticMethod_umsgbox;                         //~1A22I~
    int rc;                                                        //~1A22I~
    JNIEnv *penv;                                                  //~1A22I~
    jstring jmsg;                                                  //~1A22R~
//********************************                                 //~1A22I~
    penv=getThreadEnv();                                           //~1A22I~
	UTRACEP("umsgbox env=%p,msg=%s,flag=%x\n",penv,Ppmsg,Pflag);   //~1A22I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,0/*IDOK*/,umsgbox,"(Ljava/lang/String;I)I");//~1A22R~
    jmsg=utf8z2jstring(penv,Ppmsg);                                //~1A22I~
//  rc=C2J_INT(penv,umsgbox,jmsg,Pflag);                           //~vay0R~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~vby4I~
    C2J_INT(&rc,penv,umsgbox,jmsg,Pflag);                          //~vay0I~
    unrefLocal(penv,jmsg);                                         //~1A22I~
	UTRACEP("umsgbox rc=%x\n",rc);                                 //~1A22I~
    return rc;                                                     //~1A22I~
}                                                                  //~1A22I~
//**************************************************************** //~1622I~
//*from wxe_xecall when rc=-1(quit)                                //~1622I~
//*because no Modal dialog support,once return by CANCEL           //~1622I~
//*and listener schedule quit when replyed yes                     //~1622I~
//*rc:0:ok                                                         //~1622I~
//**************************************************************** //~1622I~
int umsgbox2(char *Ppmsg,int Pmessagetype,int Pbuttontype)         //~1801I~
{                                                                  //~1622I~
    static jmethodID staticMethod_umsgbox2;                 //~1622I~//~1801R~
    JNIEnv *penv;                                                  //~1714I~
    jstring jmsg;                                                  //~1801I~
//****************************                                     //~1622I~
    penv=getThreadEnv();                                           //~1714I~
	UTRACEP("umsgbox2 env=%p,msg=%s\n",penv,Ppmsg);         //~1714R~//~1801R~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,BTN_CAN,umsgbox2,"(Ljava/lang/String;II)V");//~1714R~//~1801R~
    jmsg=utf8z2jstring(penv,Ppmsg);                                //~1801I~//~1A22M~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~vby4I~
    C2J_VOID(penv,umsgbox2,jmsg,Pmessagetype,Pbuttontype);                   //~1714R~//~1718R~//~1801R~
    unrefLocal(penv,jmsg);                                         //~1A22I~
    return BTN_CAN;     //!=0                                      //~1622R~
}                                                                  //~1622I~
//**************************************************************** //~vay0I~
//*from wxe_xecall when rc=-1(quit)                                //~vay0I~
//*because no Modal dialog support,once return by CANCEL           //~vay0I~
//*and listener schedule quit when replyed yes                     //~vay0I~
//*rc:0:ok                                                         //~vay0I~
//**************************************************************** //~vay0I~
int c2j_msgboxACRA(char *Ppmsg)                                    //~vay0R~
{                                                                  //~vay0I~
    static jmethodID staticMethod_umsgboxACRA;                     //~vay0I~
    JNIEnv *penv;                                                  //~vay0I~
    jstring jmsg;                                                  //~vay0I~
//****************************                                     //~vay0I~
    penv=getThreadEnv();                                           //~vay0I~
	UTRACEP("umsgboxACRA env=%p,msg=%s\n",penv,Ppmsg);             //~vay0I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,BTN_CAN,umsgboxACRA,"(Ljava/lang/String;)V");//~vay0R~
    jmsg=utf8z2jstring(penv,Ppmsg);                                //~vay0I~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~vby4I~
    C2J_VOID(penv,umsgboxACRA,jmsg);                               //~vay0R~
    unrefLocal(penv,jmsg);                                         //~vay0I~
    return BTN_OK;                                                 //~vay0I~
}                                                                  //~vay0I~
//****************************************************************
//fromdlcmd6                                                       //~vc60I~
//*kick app by mime type
//* send file to associated application
//* rc:1 requre terminal for chkonly option
//****************************************************************
//int ushellexec(int Popt,char *Pfnm)                              //~vc60R~
int ushellexec(int Popt,char *Pfnm,int Psize)                      //~vc60R~
{
//int ushellexecsub(char *Pfpath,char *Pcmd,int Ptermuse);           //~1A06R~//~vc60R~
int ushellexecsub(char *Pfpath,char *Pcmd,int Ptermuse,int Psize); //~vc60R~
 // GnomeVFSMimeActionType actiontype;                             //~1A06R~
    GnomeVFSMimeApplication *papp;                                 //~1A06R~
	char *pmimetype,*puri,*pparmname,*pdircmd=0,*pcmd=""/*,*pname*/;//~vay0R~
    char fpath[_MAX_PATH];
    int rc=0,termsw,simid=0/*,termid*/,actiontypeerr=0,reqterm;    //~1A06R~//~vc5pR~
#define MIME_DIR  "x-directory/"                                   //~1A06R~
#define MIME_DIR_CMD  "nautilus"                                   //~1A06R~
//*********************
//    termid=ukbdl_gettermid();                                    //~1A06R~
//    if ((termid & TERM_IDMASK)==TERM_TTYLCONS)                   //~1A06R~
//    {                                                            //~1A06R~
//        if (!(Popt & USHEXE_NOMSG))                              //~1A06R~
//            uerrmsg("Open With Application is not avail on console screen",0);//~1A06R~
//        return -1;      //no mime type defined                   //~1A06R~
//    }                                                            //~1A06R~
//    simid=termid&TERM_SIMID;                                     //~1A06R~
//    if (uproc_gvfsinit())                                        //~1A06R~
//        return -1;                                               //~1A06R~
	if(!ufullpath(fpath,Pfnm,sizeof(fpath)))
    	return -1;
	if (uftpisremote(fpath,0))  //gnome_vfs assersion fail for "hostid:/" format
    	return -1;
    if (ufstat(fpath,0))
    {
		uerrmsg("%s not found",0,
        		fpath);
        return -1;
    }
    puri=gnome_vfs_get_uri_from_local_path(fpath);  // file:///root/... fmt
    if (!puri)                                                  
    {                                                           
        uerrmsg("uri format err(%s)",0,
                fpath);                
        return -1;                     
    }                                  
    pmimetype=gnome_vfs_get_mime_type(puri);
//printf("mimetype=%s from uristr=%s \n",pmimetype,puri);
    if (!pmimetype)
    {
		if (!(Popt & USHEXE_NOMSG))
	    	uerrmsg("MIME type not defined for %s",0,
        				fpath);                                    //+vc60R~
    	return -1;		//no mime type defined
    }
//    papp=gnome_vfs_mime_get_default_application(pmimetype);      //~1A19R~
    papp=gnome_vfs_mime_get_default_application_dataandtype(puri,pmimetype);	//chk by data and type//~1A19I~
  if (!papp)	//no application defined
  {
#ifdef AAA                                                         //~vc5pI~
    actiontype=gnome_vfs_mime_get_default_action_type(pmimetype);
//printf("mimetype:%s actiontype=%d (0:non,1:app,2:comp)\n",pmimetype,actiontype);
    if (actiontype==GNOME_VFS_MIME_ACTION_TYPE_COMPONENT)
    {
		if (strlen(pmimetype)>sizeof(MIME_DIR) && !memcmp(pmimetype,MIME_DIR,sizeof(MIME_DIR)-1))
        	pdircmd=MIME_DIR_CMD;
        else
        	actiontypeerr=1;
    }
    else
//  	if (actiontype!=GNOME_VFS_MIME_ACTION_TYPE_APPLICATION)
        	actiontypeerr=1;
#else                                                              //~vc5pI~
        	actiontypeerr=1;                                       //~vc5pI~
#endif                                                             //~vc5pI~
    if (actiontypeerr)
    {
		if (!(Popt & USHEXE_NOMSG))
	    	uerrmsg("default action is not defined for MIME type:%s",0,
        				pmimetype);
    	return -1;		//no application binded
    }
  }//application not defined
    if (pdircmd)
    {
    	papp=0;
        pparmname=fpath;
        reqterm=0;
//      pname=pcmd=pdircmd;                                        //~vak2R~
    }
    else
    {
//      papp=gnome_vfs_mime_get_default_application(pmimetype);
//printf("mimetype:%s papp=%p\n",pmimetype,papp);
//printf("id=%s,name=%s,command=%s,can_open_multiple_files=%d,expects_uris=%d,requires_terminal=%d\n",
//papp->id,papp->name,papp->command,papp->can_open_multiple_files,papp->expects_uris,papp->requires_terminal);
        if (papp->expects_uris==GNOME_VFS_MIME_APPLICATION_ARGUMENT_TYPE_URIS) //
            pparmname=puri;
        else
        if (papp->expects_uris==GNOME_VFS_MIME_APPLICATION_ARGUMENT_TYPE_PATHS) //
            pparmname=fpath;
        else
        {
            if (!(Popt & USHEXE_NOMSG))
                uerrmsg("parameter type for default application(%s) for MIME type:%s is not local-pathname nor uri",0,
                            papp->name,pmimetype);
            return -1;      //no application binded
        }
    //*mimeType                                                    //~vc5pI~
		pcmd=papp->command;
//  	pname=papp->name;                                          //~vak2R~
		reqterm=(papp->requires_terminal!=0);
    }
    if (Popt & USHEXE_CHKONLY)
		rc=reqterm;		//terminal requred as rc
    else
    {
        termsw=reqterm || (Popt & USHEXE_FORCETERM);
        if (termsw)
        {
	        if (!simid)
    	    	simid=TERM_OTHERSIM;
        }
        else
        	simid=0;	//no term required
//		rc=ushellexecsub(pparmname,pcmd,simid);                    //~vc60R~
  		rc=ushellexecsub(pparmname,pcmd,simid,Psize);              //~vc60I~
//        if (rc>0)   //pid                                        //~1A06R~
//            if (!(Popt & USHEXE_NOMSG))                          //~1A06R~
//                uerrmsg("sent \"%s\" to %s(pid=%d)",0,           //~1A06R~
//                        Pfnm,pname,rc);                          //~1A06R~
        if (rc==0) //ok                                            //~1A06I~
        {                                                          //~1A06I~
//        if (!(Popt & USHEXE_NOMSG))                              //~1A06I~//~vc60R~
//            uerrmsg("sent \"%s\" to %s",0,                       //~1A06I~//~vc60R~
//                    Pfnm,pcmd,rc);                               //~1A06I~//~vc60R~
			UTRACEP("%s: usheeexecsub rc=0,fnm=%s\n",UTT,pparmname);//~vc60I~
        }                                                          //~1A06I~
        else                                                       //~1A06I~
        {                                                          //~1A06I~
            if (!(Popt & USHEXE_NOMSG))                            //~1A06I~
                uerrmsg("default action is not defined for MIME type:%s",0,//~1A06I~
                            pmimetype);                            //~1A06I~
            return -1;      //no application binded                //~1A06I~
        }                                                          //~1A06I~
    }
//    if (papp)                                                    //~1A06R~
//        gnome_vfs_mime_application_free(papp);                   //~1A06R~
//printf("use term");
    return rc;
}//ushellexec
//**************************************************************** //~1A06I~
//* ushellexecsub                                                  //~1A06I~
//*startIntent                                                     //~1A06I~
//* rc:                                                            //~1A06I~
//**************************************************************** //~1A06I~
//int ushellexecsub(char *Pfpath/*uri*/,char *Pcmd/*mimetype*/,int Ptermsimid)//~1A06R~//~vc60R~
int ushellexecsub(char *Pfpath/*uri*/,char *Pcmd/*mimetype*/,int Ptermsimid,int Psize)//~vc60I~
{                                                                  //~1A06I~
    static jmethodID staticMethod_openWith;                        //~1A06R~
    jstring juri,jmime;                                            //~1A06R~
    JNIEnv *penv;                                                  //~1A06I~
    int rc;                                                        //~1A06I~
//****************************                                     //~1A06I~
    penv=getThreadEnv();                                           //~1A06I~
	UTRACEP("%s: env=%p,parm=%s,mimeType=%s,size=%d\n",UTT,penv,Pfpath,Pcmd,Psize);          //~1A06I~//~vc5pR~//~vc60R~
//  C2J_GETMETHODID_RETIFERR_WITHRC(penv,4/*rc*/,openWith,"(Ljava/lang/String;Ljava/lang/String;)I");//~1A06R~//~vc60R~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,4/*rc*/,openWith,"(Ljava/lang/String;Ljava/lang/String;I)I");//~vc60I~
    juri=utf8z2jstring(penv,(char *)Pfpath);                       //~1A06I~
    jmime=utf8z2jstring(penv,(char *)Pcmd);                        //~1A06I~
//  rc=(int)C2J_INT(penv,openWith,juri,jmime);                     //~vay0R~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~vby4I~
//  C2J_INT(&rc,penv,openWith,juri,jmime);                         //~vay0I~//~vc60R~
    C2J_INT(&rc,penv,openWith,juri,jmime,Psize);                   //~vc60I~
    unrefLocal(penv,juri);                                         //~1A06I~
    unrefLocal(penv,jmime);                                        //~1A06I~
    UTRACEP("openWith rc=%d\n",rc);                                //~1A06R~
    return rc;                                                     //~1A06R~
}                                                                  //~1A06I~
//*******************************************************          //~v62UR~//~1A13I~
//*wcwidth by java Paint.getWidths                                 //~v6a0R~//~1A13I~
//*rc:1/2/0/-1(not defined)                                        //~v6a0R~//~1A13I~
//*******************************************************          //~v640I~//~1A13I~
int wcwidth(UWCHART Pucs)                                          //~v6a0I~//~1A13I~
{                                                                  //~v6a0I~//~1A13I~
    JNIEnv *penv;                                                  //~1A13I~
    static jmethodID staticMethod_wcwidth;                         //~1A13I~
    static jmethodID staticMethod_getwcwidthtbl;                   //~1A13I~
    int rc,sz,ctr,byte;                                                   //~v6a0R~//~1A13I~
static char *Swcwidthtbl=0;	//0/1/2/3(unprintable)                 //~v6a0I~//~1A13I~
    jbyteArray  jbyte;                                             //~1A13I~
//    int ii;                                                        //~1A13I~//~1A14R~
//************************************                             //~v6a0I~//~1A13I~
    penv=getThreadEnv();                                           //~1A13I~
	if (Pucs>=UCS2DDMAP_ENTNO)	//no ucs->sbcsid mapping entry     //~v6a0I~//~1A13I~
    {                                                              //~1A13I~
    	C2J_GETMETHODID_RETIFERR_WITHRC(penv,-1/*rc*/,wcwidth,"(I)I");//~1A13R~
//  	rc=(int)C2J_INT(penv,wcwidth,Pucs);                        //~vay0R~
		UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);              //~vby4I~
    	C2J_INT(&rc,penv,wcwidth,Pucs);                            //~vay0I~
    }                                                              //~1A13I~
    else                                                           //~v6a0I~//~1A13I~
    {                                                              //~v6a0I~//~1A13I~
    	ctr=UCS2DDMAP_ENTNO;                                    //~v6a0I~//~1A13I~
    	sz=ctr/4+1;                                                //~1A13I~
    	if (!Swcwidthtbl)                                          //~v6a0I~//~1A13I~
        {                                                          //~1A13I~
		    C2J_GETMETHODID_RETIFERR_WITHRC(penv,-1/*rc*/,getwcwidthtbl,"(I[B)V");//~1A13I~
    		jbyte=newjbyte(penv,sz);                               //~1A13I~
			UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);          //~vby4I~
    		C2J_VOID(penv,getwcwidthtbl,ctr,jbyte);                //~1A13I~
        	Swcwidthtbl=malloc(sz);	//0/1/2/3(unprintable)         //~v6a0I~//~1A13I~
		    jbyte2byte(penv,jbyte,0,sz,Swcwidthtbl);               //~1A13I~
//  		LOGPD("@@@@c2j wcwidth unref jbyte=%p\n",jbyte);       //~vay0R~
		    unrefLocal(penv,jbyte);                                //~1A13I~
//            //@@@@test                                           //~1A13R~
//            for (ii=0;ii<0x10ffff;ii++)                          //~1A13R~
//            {                                                    //~1A13R~
//                wcwidth(ii);                                     //~1A13R~
//            }                                                    //~1A13R~
			UTRACED("jnic2j:wcwidth tbl",Swcwidthtbl,sz);          //~vabaI~
        }                                                          //~1A13I~
        byte=(int)*(Swcwidthtbl+Pucs/4);                           //~1A13I~
        switch(Pucs%4)                                             //~1A13I~
        {                                                          //~1A13I~
        case 0:                                                    //~1A13I~
			rc=(byte & 0xc0)>>6;                      //~1A12R~    //~1A13R~
            break;                                                 //~1A13I~
        case 1:                                                    //~1A13I~
			rc=(byte & 0x30)>>4;                          //~1A12R~//~1A13I~
            break;                                                 //~1A13I~
        case 2:                                                    //~1A13I~
			rc=(byte & 0x0c)>>2;                                   //~1A13I~
            break;                                                 //~1A13I~
        default:                                                   //~1A13I~
			rc=(byte & 0x03);                             //~1A12R~//~1A13I~
        }                                                          //~1A13I~
    }                                                              //~v6a0I~//~1A13I~
    if (rc==3)  //not defined                                      //~1A13I~
        rc=-1;                                                     //~1A13I~
    UTRACEP("jnic2j:wcwidth sizeof UWCHART=%d ucs=x%x,rc=%d\n",sizeof(Pucs),Pucs,rc);                 //~v6a0I~//~1A13R~//~vabaR~//~vay0R~
	return rc;                                                     //~v6a0R~//~1A13I~
}//wcwidth                                                         //~v6a0I~//~1A13I~
//**************************************************************** //~1A15I~
//send mail etc                                                    //~1A15I~
//**************************************************************** //~1A15I~
int usend2app(int Popt,char *Pfnm,char *Pattachfnm)                //~1A15R~
{                                                                  //~1A15I~
    static jmethodID staticMethod_androsend;                       //~1A15I~
    jstring jfnm,jafnm;                                            //~1A15I~
    JNIEnv *penv;                                                  //~1A15I~
    int rc;                                                        //~1A15I~
//*********************                                            //~1A15I~
    penv=getThreadEnv();                                           //~1A15I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,4/*rc*/,androsend,"(ILjava/lang/String;Ljava/lang/String;)I");//~1A15I~
    jfnm=utf8z2jstring(penv,(char *)Pfnm);                         //~1A15I~
    jafnm=utf8z2jstring(penv,(char *)Pattachfnm);                  //~1A15I~
//  rc=C2J_INT(penv,androsend,Popt,jfnm,jafnm);                    //~vay0R~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~vby4I~
    C2J_INT(&rc,penv,androsend,Popt,jfnm,jafnm);                   //~vay0I~
    unrefLocal(penv,jfnm);                                         //~1A15I~
    unrefLocal(penv,jafnm);                                        //~1A15I~
    return rc;                                                     //~1A15I~
}//usend2app                                                       //~1A15R~
//**************************************************************** //~1A23I~
//setup vfat file system list                                      //~1A23I~
//**************************************************************** //~1A23I~
int c2j_getvfatlist(char **Pplist)                                 //~1A23I~
{                                                                  //~1A23I~
    static jmethodID staticMethod_getVfatList;                     //~1A23I~
    jstring jstr;                                                  //~1A23R~
    JNIEnv *penv;                                                  //~1A23I~
    int rc;                                                        //~1A23I~
//*********************                                            //~1A23I~
    penv=getThreadEnv();                                           //~1A23I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,0/*rc*/,getVfatList,"()Ljava/lang/String;");//~1A23R~
//  jstr=C2J_OBJVOID(penv,getVfatList);                            //~vay0R~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~vby4I~
    C2J_OBJVOID(&jstr,penv,getVfatList);                           //~vay0I~
    if (jstr)                                                      //~1A23I~
    {                                                              //~1A23I~
    	*Pplist=jstring2char(penv,jstr);                           //~1A23R~
        rc=strlen(*Pplist);	//list len(delm='\n')                  //~1A23I~
    }                                                              //~1A23I~
    else                                                           //~1A23I~
    	rc=0;                                                      //~1A23I~
    return rc;                                                     //~1A23I~
}//usend2app                                                       //~1A23I~
//**************************************************************** //~vbrhI~
int c2j_notifyRulerMode(int Prulermode)                            //~vbrhR~
{                                                                  //~vbrhI~
    static jmethodID staticMethod_notifyRulerMode;                  //~vbrhI~
    JNIEnv *penv;                                                  //~vbrhI~
//****************************                                     //~vbrhI~
	UTRACEP("%s:rulermode=%d\n",UTT,Prulermode);                   //~vbrhI~
    penv=getThreadEnv();                                           //~vbrhI~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,4/*rc*/,notifyRulerMode,"(I)V");//~vbrhR~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~vby4I~
    C2J_VOID(penv,notifyRulerMode,Prulermode);                     //~vbrhI~
    return 0;                                                      //~vbrhI~
}                                                                  //~vbrhI~
//**************************************************************** //~vby4I~
int c2j_startPicker(int Popt,char *Palias)                         //~vby4I~
{                                                                  //~vby4I~
    static jmethodID staticMethod_startPicker;                     //~vby4I~
    jstring jstr;                                                  //~vby4I~
    JNIEnv *penv;                                                  //~vby4I~
    int rc;                                                        //~vby4I~
//****************************                                     //~vby4I~
    penv=getThreadEnv();                                           //~vby4I~
    jstr=utf8z2jstring(penv,Palias);                               //~vby4I~
	UTRACEP("c2j_startPicker env=%p,Palias=%s,jstr=%p\n",penv,Palias,jstr);//~vby4I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,-1,startPicker,"(ILjava/lang/String;)I");//~vby4R~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~vby4I~
    C2J_INT(&rc,penv,startPicker,Popt,jstr);                       //~vby4R~
    unrefLocal(penv,jstr);                                         //~vby4I~
	UTRACEP("c2j_startPicker exit Palias=%s\n",Palias);            //~vby4I~
    UTRACE_FLUSH("c2j_startPicker exit");                          //~vby4R~
	return rc;                                                     //~vby4R~
}                                                                  //~vby4I~
//**************************************************************** //~vby4I~
int c2j_getDirFD(int Popt,char *PstrUri)                           //~vby4I~
{                                                                  //~vby4I~
    static jmethodID staticMethod_getDirFD;                        //~vby4R~
    jstring jstr;                                                  //~vby4I~
    JNIEnv *penv;                                                  //~vby4I~
    int rc;                                                        //~vby4I~
//****************************                                     //~vby4I~
    penv=getThreadEnv();                                           //~vby4I~
	UTRACEP("c2j_getDirFD env=%p,strUri=%s\n",penv,PstrUri);       //~vby4I~
    jstr=utf8z2jstring(penv,PstrUri);                              //~vby4I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,-1,getDirFD,"(ILjava/lang/String;)I");//~vby4R~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~vby4I~
    C2J_INT(&rc,penv,getDirFD,Popt,jstr);                          //~vby4I~
    unrefLocal(penv,jstr);                                         //~vby4I~
	UTRACEP("c2j_getDirFD exit PstrUri=%s\n",PstrUri);             //~vby4I~
	return rc;                                                     //~vby4I~
}                                                                  //~vby4I~
//**************************************************************** //~vby4I~
//*rc;ctr list                                                     //~vby4I~
//**************************************************************** //~vby4I~
int getError(char *PmemberData)                                    //~vby4I~
{                                                                  //~vby4I~
	int err=0;                                                     //~vby4I~
	if (*PmemberData==PREFIX_ERROR)                                //~vby4I~
    	err=atoi(PmemberData+1);                                   //~vby4R~
	UTRACEP("%s:err=%d\n",UTT,err);                                //~vby4R~
    return err;                                                    //~vby4I~
}                                                                  //~vby4I~
//**************************************************************** //~vby4I~
//*rc;ctr list                                                     //~vby4I~
//**************************************************************** //~vby4I~
int c2j_udirlistDoc(int Popt,char *PnameDir,char *PstrUri,unsigned Pattr,UDIRLIST **Pppudirlist,int *Perrcode)//~vby4R~
{                                                                  //~vby4I~
    static jmethodID staticMethod_getDirDoc;                       //~vby4I~
    jstring jstrName,jstrUri,jstrMembers;                          //~vby4I~
//  jobject jobjReturn;                                            //~vby4R~
    jstring jobjReturn;                                            //~vby4I~
    JNIEnv *penv;                                                  //~vby4I~
    int rc=-1/*ctrList*/,lenString,lenBuff;                        //~vby4R~
    char *pbuff;                                                   //~vby4I~
    PUDIRLIST pudirlist;                                           //~vby4I~
//****************************                                     //~vby4I~
    *Perrcode=EINTR;  //apierr:DocumentProcess at ufile1l          //~vby4R~
    penv=getThreadEnv();                                           //~vby4I~
	UTRACEP("%s: env=%p,nameDir=%s,strUri=%s,attr=0x%x\n",UTT,penv,PnameDir,PstrUri,Pattr);//~vby4R~
    jstrName=utf8z2jstring(penv,PnameDir);                         //~vby4I~
    jstrUri=utf8z2jstring(penv,PstrUri);                           //~vby4I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,-1,getDirDoc,"(ILjava/lang/String;Ljava/lang/String;)Ljava/lang/String;");//~vby4R~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~vby4I~
    C2J_OBJ(&jobjReturn,penv,getDirDoc,Popt,jstrName,jstrUri);     //~vby4I~
    jstrMembers=(jstring)jobjReturn;                               //~vby4I~
    lenString=(*penv)->GetStringLength(penv,jstrMembers);          //~vby4I~
    if (lenString)	//rc!=""                                       //~vby4I~
    {                                                              //~vby4I~
    	lenBuff=(*penv)->GetStringUTFLength(penv,jstrMembers);     //~vby4I~
		UTRACEP("%s: lenString=%d,lenBuff=%d\n",UTT,lenString,lenBuff);//~vby4I~
    	pbuff=calloc(1,lenBuff);                                   //~vby4I~
    	(*penv)->GetStringUTFRegion(penv,jstrMembers,0,lenString,pbuff);//~vby4I~
    	*Perrcode=getError(pbuff);                                 //~vby4I~
        if (!*Perrcode)                                            //~vby4I~
        {                                                          //~vby4I~
        	rc=parseMemberData(pbuff,lenBuff,Popt,PnameDir,PstrUri,Pattr,&pudirlist);//listctr//~vby4R~
        	*Pppudirlist=pudirlist;                                //~vby4R~
        }                                                          //~vby4I~
        else                                                       //~vby4I~
        if (*Perrcode==ERROR_NO_MORE_FILES)                        //~vby4R~
        {                                                          //~vby4I~
            rc=0;                                                  //~vby4I~
        	*Pppudirlist=ucalloc(1,UDIRLISTSZ);                    //~vby4I~
        }                                                          //~vby4I~
    }                                                              //~vby4I~
    unrefLocal(penv,jstrName);                                     //~vby4I~
    unrefLocal(penv,jstrUri);                                      //~vby4I~
	UTRACEP("%s:exit PnameDir=%s,PstrUri=%s\n",UTT,PnameDir,PstrUri);//~vby4I~
    UTRACE_FLUSH("c2j_udirlistDoc exit");                          //~vby4R~
	return rc;                                                     //~vby4I~
}                                                                  //~vby4I~
//**************************************************************** //~vby4I~
//*rc;ctr list                                                     //~vby4I~
//**************************************************************** //~vby4I~
int c2j_ufstatDoc(int Popt,char *PnameDir,char *PstrUri,int Ppathlen,UDIRLIST **Pppudirlist)//~vby4R~
{                                                                  //~vby4I~
    static jmethodID staticMethod_getFileStat;                     //~vby4R~
    jstring jstrName,jstrUri,jstrMembers;                          //~vby4I~
    jstring jobjReturn;                                            //~vby4I~
    JNIEnv *penv;                                                  //~vby4I~
    int rc=EINTR,lenString,lenBuff;                                //~vby4R~
    char *pbuff;                                                   //~vby4I~
    PUDIRLIST pudirlist;                                           //~vby4I~
//****************************                                     //~vby4I~
    penv=getThreadEnv();                                           //~vby4I~
	UTRACEP("%s: env=%p,nameDir=%s,strUri=%s\n",UTT,penv,PnameDir,PstrUri);//~vby4I~
    *Pppudirlist=0;                                                //~vby4I~
    jstrName=utf8z2jstring(penv,PnameDir);                         //~vby4I~
    jstrUri=utf8z2jstring(penv,PstrUri);                           //~vby4I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,-1,getFileStat,"(ILjava/lang/String;Ljava/lang/String;I)Ljava/lang/String;");//~vby4R~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~vby4I~
    C2J_OBJ(&jobjReturn,penv,getFileStat,Popt,jstrName,jstrUri,Ppathlen);//~vby4R~
    jstrMembers=(jstring)jobjReturn;                               //~vby4I~
    lenString=(*penv)->GetStringLength(penv,jstrMembers);          //~vby4I~
	UTRACEP("%s:ret from Axe lenString=%d\n",UTT,lenString);       //~vby4R~
    if (lenString)	//rc!=""                                       //~vby4I~
    {                                                              //~vby4I~
    	lenBuff=(*penv)->GetStringUTFLength(penv,jstrMembers);     //~vby4I~
		UTRACEP("%s: lenString=%d,lenBuff=%d\n",UTT,lenString,lenBuff);//~vby4I~
    	pbuff=calloc(1,lenBuff);                                   //~vby4I~
    	(*penv)->GetStringUTFRegion(penv,jstrMembers,0,lenString,pbuff);//~vby4I~
    	rc=getError(pbuff);                                        //~vby4I~
        if (!rc)                                                   //~vby4I~
        {                                                          //~vby4I~
        	parseMemberData(pbuff,lenBuff,Popt,PnameDir,PstrUri,0,&pudirlist);//rc:ctrList//~vby4R~
        	*Pppudirlist=pudirlist;                                //~vby4R~
			UTRACED("udirlist=",pudirlist,UDIRLISTSZ);             //~vby4R~
        }                                                          //~vby4I~
    }                                                              //~vby4I~
    unrefLocal(penv,jstrName);                                     //~vby4I~
    unrefLocal(penv,jstrUri);                                      //~vby4I~
	UTRACEP("%s:exit rc=%d,PnameDir=%s,PstrUri=%s\n",UTT,rc,PnameDir,PstrUri);//~vby4R~
    UTRACE_FLUSH("c2j_ufstatDoc exit");                            //~vby4I~
	return rc;                                                     //~vby4I~
}                                                                  //~vby4I~
//**************************************************************** //~vby8I~
void getStrOut(JNIEnv *penv,jobjectArray Parray,char **Pppc)       //~vby8R~
{                                                                  //~vby8I~
    UTRACEP("%s:entry\n", UTT);                                    //~vby8R~
//  int sz=getArrayLength(penv,Parray);                            //~vby8I~//~v77wR~
//  UTRACEP("%s:sz=%d\n", UTT,sz);                                 //~vby8I~//~v77wR~
	jstr2char(penv,Parray,0,-1/*all*/,Pppc);                       //~vby8I~
//  UTRACED("exit",Pppc,sizeof(char*)*sz);                         //~vby8I~//~v77wR~
}                                                                  //~vby8I~
//**************************************************************** //~vby8I~
void getIntOut(JNIEnv *penv,jintArray Parray,int *Ppint)           //~vby8R~
{                                                                  //~vby8I~
    UTRACEP("%s entry\n", UTT);                                    //~vby8I~
//  int sz=getArrayLength(penv,Parray);                            //~vby8I~//~v77wR~
//  UTRACEP("%s:sz=%d\n", UTT,sz);                                 //~vby8I~//~v77wR~
	jint2int(penv,Parray,0,-1,Ppint);                           //~vby8I~
//  UTRACED("exit",Ppint,sizeof(int)*sz);                          //~vby8R~//~v77wR~
}                                                                  //~vby8I~
////****************************************************************//~vby8R~
//char *c2j_getOpenDocParm()                                       //~vby8R~
//{                                                                //~vby8R~
//    UTRACEP("%s:buffer=%p\n",UTT,SopenDocParmBuffer);            //~vby8R~
//    return SopenDocParmBuffer;                                   //~vby8R~
//}                                                                //~vby8R~
////****************************************************************//~vby8I~
char *c2j_notifiedOpenDocResult(int PoptRC)                        //~vby8I~
{                                                                  //~vby8I~
    UTRACEP("%s:PoptRC=%d,buffer=%p\n",UTT,PoptRC,SopenDocParmBuffer);//~vby8I~
    SopenDocOptRC=PoptRC;                                          //~vby8I~
    return SopenDocParmBuffer;                                     //~vby8I~
}                                                                  //~vby8I~
//**************************************************************** //~vby8R~
int c2j_openDoc(int Popt,char *Pfpath,char *PstrUri,char *PtempDir,char **Ppbuff,int *Pplen,int *PpoptRC)//~vby8R~
{                                                                  //~vby8R~
    static jmethodID staticMethod_openDoc;                         //~vby8R~
    jstring jstrPath, jstrUri, jstrTemp;                           //~vby8R~
//  jobjectArray jstrOut;                                          //~vby8R~
//  jintArray    jintOut;                                          //~vby8R~
    JNIEnv *penv;                                                  //~vby8R~
    int rc = EINTR/*, intOut[1], lenBuff*/;                        //~vby8R~
//  char *strOut[1];                                               //~vby8R~
//****************************                                     //~vby8R~
    UTRACEP_FLUSH("%s:opt=0x%x,fpath=%s,strUri=%s,tempDir=%s,*Ppbuff=%p\n", UTT, Popt, Pfpath, PstrUri,PtempDir,*Ppbuff);//~vby8R~
    SopenDocOptRC=0;                                               //~vby8I~
    SopenDocParmBuffer=*Ppbuff;                                    //~vby8I~
    penv = getThreadEnv();                                           //~vby8R~
    jstrPath = utf8z2jstring(penv, Pfpath);                           //~vby8R~
    jstrUri = utf8z2jstring(penv, PstrUri);                           //~vby8R~
    jstrTemp = utf8z2jstring(penv, PtempDir);                         //~vby8I~
//  jstrOut=newjstrArray(penv,1);                                  //~vby8R~
//  jintOut=newjintArray(penv,1);                                  //~vby8R~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv, -1, openDoc,
//                                  "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;[I)I");//~vby8R~
                                    "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)I");//~vby8I~
    UTRACEP_FLUSH("%s:before call AxeJNI\n", UTT);                  //~vby8R~
//  C2J_INT(&rc, penv, openDoc, Popt, jstrPath, jstrUri, jstrTemp, jstrOut, jintOut);//~vby8R~
    C2J_INT(&rc, penv, openDoc, Popt, jstrPath, jstrUri, jstrTemp);//~vby8I~
    UTRACEP_FLUSH("%s:after call AxeJNI\n", UTT);                  //~vby8I~
//  getIntOut(penv,jintOut,intOut);                                //~vby8R~
//  getStrOut(penv,jstrOut,strOut);                                //~vby8R~
//  *PpoptRC =intOut[0];                                           //~vby8R~
//  *Ppbuff=strOut[0];                                             //~vby8R~
//  lenBuff=strlen(*Ppbuff);//TODO test binfile                    //~vby8R~
//  *Pplen = lenBuff;                                              //~vby8R~
//  UTRACEP("%s: env=%p,rc=%d,buff=%p,optRC=0x%x\n", UTT, penv, rc,*Ppbuff,*PpoptRC);//~vby8R~
	*PpoptRC=SopenDocOptRC;                                        //~vby8R~
    unrefLocal(penv, jstrPath);                                     //~vby8R~
    unrefLocal(penv, jstrUri);                                      //~vby8R~
    unrefLocal(penv, jstrTemp);                                     //~vby8I~
    UTRACEP("%s:exit rc=%d\n", UTT, rc);
    return rc;
}//~vby8R~
//**************************************************************** //~vby8I~
char *c2j_get_fgetsDocParm()                                       //~vby8R~
{                                                                  //~vby8I~
    UTRACEP("%s:buffer=%p\n",UTT,SfgetsDocParmBuffer);             //~vby8R~
	return SfgetsDocParmBuffer;                                    //~vby8I~
}                                                                  //~vby8I~
//**************************************************************** //~vby8I~
int c2j_fgetsDoc(int Popt,char *Pfpath,char *Pbuff,int Plen)       //~vby8R~
{                                                                  //~vby8I~
    static jmethodID staticMethod_fgetsDoc;                        //~vby8R~
    jstring jstrPath;                                              //~vby8R~
    JNIEnv *penv;                                                  //~vby8I~
    int rc=EINTR;                                                  //~vby8R~
//****************************                                     //~vby8I~
	UTRACEP_FLUSH("%s:opt=0x%x,fpath=%s\n",UTT,Popt,Pfpath);       //~vby8I~
    SfgetsDocParmBuffer=Pbuff;                                     //~vby8R~
    penv=getThreadEnv();                                           //~vby8I~
    jstrPath=utf8z2jstring(penv,Pfpath);                           //~vby8I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,-1,fgetsDoc,"(ILjava/lang/String;I)I");//~vby8R~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~vby8I~
    C2J_INT(&rc,penv,fgetsDoc,Popt,jstrPath,Plen);                 //~vby8R~
    UTRACEP_FLUSH("%s:after call AxeJNI\n", UTT);                  //~vby8I~
    unrefLocal(penv,jstrPath);                                     //~vby8I~
	UTRACEP("%s:exit rc=%d\n",UTT,rc);                             //~vby8I~
    return rc;                                                     //~vby8I~
}                                                                  //~vby8R~
//**************************************************************** //~vby8I~
char *c2j_notified_freadDocResult(int Preadlen)                    //~vby8R~
{                                                                  //~vby8I~
    UTRACEP("%s:buffer=%p\n",UTT,SfreadDocParmBuffer);             //~vby8I~
    SfreadDocReadLen=Preadlen;                                     //~vby8I~
	return SfreadDocParmBuffer;                                    //~vby8I~
}                                                                  //~vby8I~
//**************************************************************** //~vby8I~
int c2j_freadDoc(int Popt,char *Pfpath,char *Pbuff,int Plen,int *Ppreadlen)//~vby8R~
{                                                                  //~vby8I~
    static jmethodID staticMethod_freadDoc;                        //~vby8I~
    jstring jstrPath;                                              //~vby8R~
    JNIEnv *penv;                                                  //~vby8I~
    int rc=EINTR;                                                  //~vby8R~
//****************************                                     //~vby8I~
	UTRACEP_FLUSH("%s:opt=0x%x,fpath=%s\n",UTT,Popt,Pfpath);       //~vby8I~
    SfreadDocParmBuffer=Pbuff;                                     //~vby8I~
    SfreadDocReadLen=0;                                            //~vby8I~
    penv=getThreadEnv();                                           //~vby8I~
    jstrPath=utf8z2jstring(penv,Pfpath);                           //~vby8I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,-1,freadDoc,"(ILjava/lang/String;I)I");//~vby8I~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~vby8I~
    C2J_INT(&rc,penv,freadDoc,Popt,jstrPath,Plen);                 //~vby8I~
    UTRACEP_FLUSH("%s:after call AxeJNI\n", UTT);                  //~vby8I~
    if (!rc)                                                       //~vby8I~
    	*Ppreadlen=SfreadDocReadLen;                               //~vby8R~
    unrefLocal(penv,jstrPath);                                     //~vby8I~
	UTRACEP("%s:exit rc=%d,readlen=%d\n",UTT,rc,*Ppreadlen);       //~vby8R~
    return rc;                                                     //~vby8I~
}                                                                  //~vby8I~
//**************************************************************** //~vby8I~
void c2j_notified_fwriteDocResult(int Pwritelen)                  //~vby8I~
{                                                                  //~vby8I~
    UTRACEP("%s:writelen=%d\n",UTT,Pwritelen);                     //~vby8R~
    SfwriteDocWriteLen=Pwritelen;                                  //~vby8I~
}                                                                  //~vby8I~
//**************************************************************** //~vby8I~
int c2j_fwriteDoc(int Popt,char *Pfpath,char *Pbuff,int Plen,int *Ppwritelen)//~vby8R~
{                                                                  //~vby8I~
    static jmethodID staticMethod_fwriteDoc;                       //~vby8I~
    jstring jstrPath;                                              //~vby8I~
    jbyteArray jbytes;                                             //~vby8I~
    JNIEnv *penv;                                                  //~vby8I~
    int rc=EINTR;                                                  //~vby8I~
//****************************                                     //~vby8I~
	UTRACEP_FLUSH("%s:opt=0x%x,fpath=%s\n",UTT,Popt,Pfpath);       //~vby8M~
	UTRACED("buff",Pbuff,Plen);                                    //~vby8I~
    SfwriteDocWriteLen=0;                                          //~vby8I~
    penv=getThreadEnv();                                           //~vby8I~
    jstrPath=utf8z2jstring(penv,Pfpath);                           //~vby8I~
    jbytes=byte2jbyte(penv,Pbuff,Plen);                            //~vby8I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,-1,fwriteDoc,"(ILjava/lang/String;[BI)I");//~vby8I~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~vby8I~
    C2J_INT(&rc,penv,fwriteDoc,Popt,jstrPath,jbytes,Plen);         //~vby8I~
    UTRACEP_FLUSH("%s:after call AxeJNI\n", UTT);                  //~vby8I~
    if (!rc)                                                       //~vby8I~
    	*Ppwritelen=SfwriteDocWriteLen;                            //~vby8I~
    unrefLocal(penv,jstrPath);                                     //~vby8I~
    unrefLocal(penv,jbytes);                                       //~vby8I~
	UTRACEP("%s:exit rc=%d,writelen=%d\n",UTT,rc,*Ppwritelen);     //~vby8I~
    return rc;                                                     //~vby8I~
}                                                                  //~vby8I~
//**************************************************************** //~vby8I~
int c2j_fcloseDoc(int Popt,char *Pfpath)                           //~vby8I~
{                                                                  //~vby8I~
    static jmethodID staticMethod_fcloseDoc;                       //~vby8I~
    jstring jstrPath;                                              //~vby8I~
    JNIEnv *penv;                                                  //~vby8I~
    int rc=EINTR;                                                  //~vby8I~
//****************************                                     //~vby8I~
	UTRACEP_FLUSH("%s:opt=0x%x,fpath=%s\n",UTT,Popt,Pfpath);       //~vby8I~
    penv=getThreadEnv();                                           //~vby8I~
    jstrPath=utf8z2jstring(penv,Pfpath);                           //~vby8I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,-1,fcloseDoc,"(ILjava/lang/String;)I");//~vby8I~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~vby8I~
    C2J_INT(&rc,penv,fcloseDoc,Popt,jstrPath);                     //~vby8I~
    UTRACEP_FLUSH("%s:after call AxeJNI\n", UTT);                  //~vby8I~
    unrefLocal(penv,jstrPath);                                     //~vby8I~
	UTRACEP("%s:exit rc=%d\n",UTT,rc);                             //~vby8I~
    return rc;                                                     //~vby8I~
}                                                                  //~vby8I~
//**************************************************************** //~v77cI~
int c2j_mkdirDoc(int Popt,char *Pfpath,char *PstrUri)              //~v77cI~
{                                                                  //~v77cI~
    static jmethodID staticMethod_mkdirDoc;                        //~v77cI~
    jstring jstrPath,jstrUri;                                      //~v77cI~
    JNIEnv *penv;                                                  //~v77cI~
    int rc=EINTR;                                                  //~v77cI~
//****************************                                     //~v77cI~
	UTRACEP_FLUSH("%s:opt=0x%x,fpath=%s\n",UTT,Popt,Pfpath);       //~v77cI~
    penv=getThreadEnv();                                           //~v77cI~
    jstrPath = utf8z2jstring(penv, Pfpath);                        //~v77cI~
    jstrUri = utf8z2jstring(penv, PstrUri);                        //~v77cI~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,-1,mkdirDoc,"(ILjava/lang/String;Ljava/lang/String;)I");//~v77cI~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~v77cI~
    C2J_INT(&rc,penv,mkdirDoc,Popt,jstrPath,jstrUri);             //~v77cI~
    UTRACEP_FLUSH("%s:after call AxeJNI\n", UTT);                  //~v77cI~
    unrefLocal(penv,jstrPath);                                     //~v77cI~
    unrefLocal(penv,jstrUri);                                      //~v77cI~
	UTRACEP("%s:exit rc=%d\n",UTT,rc);                             //~v77cI~
    return rc;                                                     //~v77cI~
}                                                                  //~v77cI~
//**************************************************************** //~v77fI~
int c2j_rmdirDoc(int Popt,char *Pfpath,char *PstrUri)              //~v77fR~
{                                                                  //~v77fI~
    static jmethodID staticMethod_rmdirDoc;                        //~v77fI~
    jstring jstrPath,jstrUri;                                      //~v77fI~
    JNIEnv *penv;                                                  //~v77fI~
    int rc=EINTR;                                                  //~v77fI~
//****************************                                     //~v77fI~
	UTRACEP_FLUSH("%s:opt=0x%x,fpath=%s\n",UTT,Popt,Pfpath);       //~v77fI~
    penv=getThreadEnv();                                           //~v77fI~
    jstrPath = utf8z2jstring(penv, Pfpath);                        //~v77fI~
    jstrUri = utf8z2jstring(penv, PstrUri);                        //~v77fI~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,-1,rmdirDoc,"(ILjava/lang/String;Ljava/lang/String;)I");//~v77fI~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~v77fI~
    C2J_INT(&rc,penv,rmdirDoc,Popt,jstrPath,jstrUri);              //~v77fI~
    UTRACEP_FLUSH("%s:after call AxeJNI\n", UTT);                  //~v77fI~
    unrefLocal(penv,jstrPath);                                     //~v77fI~
    unrefLocal(penv,jstrUri);                                      //~v77fI~
	UTRACEP("%s:exit rc=%d\n",UTT,rc);                             //~v77fI~
    return rc;                                                     //~v77fI~
}                                                                  //~v77fI~
//**************************************************************** //~v77cI~
int c2j_unlinkDoc(int Popt,char *Pfpath,char *PstrUri)             //~v77cI~
{                                                                  //~v77cI~
    static jmethodID staticMethod_unlinkDoc;                       //~v77cI~
    jstring jstrPath,jstrUri;                                      //~v77cI~
    JNIEnv *penv;                                                  //~v77cI~
    int rc=EINTR;                                                  //~v77cI~
//****************************                                     //~v77cI~
	UTRACEP_FLUSH("%s:opt=0x%x,fpath=%s\n",UTT,Popt,Pfpath);       //~v77cI~
    penv=getThreadEnv();                                           //~v77cI~
    jstrPath = utf8z2jstring(penv, Pfpath);                        //~v77cI~
    jstrUri = utf8z2jstring(penv, PstrUri);                        //~v77cI~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,-1,unlinkDoc,"(ILjava/lang/String;Ljava/lang/String;)I");//~v77cI~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~v77cI~
    C2J_INT(&rc,penv,unlinkDoc,Popt,jstrPath,jstrUri);             //~v77cI~
    UTRACEP_FLUSH("%s:after call AxeJNI\n", UTT);                  //~v77cI~
    unrefLocal(penv,jstrPath);                                     //~v77cI~
    unrefLocal(penv,jstrUri);                                      //~v77cI~
	UTRACEP("%s:exit rc=%d\n",UTT,rc);                             //~v77cI~
    return rc;                                                     //~v77cI~
}                                                                  //~v77cI~
//**************************************************************** //~v77fI~
int c2j_renameDoc(int Popt,char *Pold,char *PstrUri,char *Pnew)    //~v77fI~
{                                                                  //~v77fI~
    static jmethodID staticMethod_renameDoc;                       //~v77fI~
    jstring jstrOld,jstrNew,jstrUri;                               //~v77fI~
    JNIEnv *penv;                                                  //~v77fI~
    int rc=EINTR;                                                  //~v77fI~
//****************************                                     //~v77fI~
	UTRACEP("%s:opt=0x%x,old=%s,new=%s\n",UTT,Popt,Pold,Pnew);     //~v77fR~
    penv=getThreadEnv();                                           //~v77fI~
    jstrOld = utf8z2jstring(penv, Pold);                           //~v77fI~
    jstrUri = utf8z2jstring(penv, PstrUri);                        //~v77fI~
    jstrNew = utf8z2jstring(penv, Pnew);                           //~v77fR~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,-1,renameDoc,"(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)I");//~v77fI~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~v77fI~
    C2J_INT(&rc,penv,renameDoc,Popt,jstrOld,jstrUri,jstrNew);       //~v77fI~
    UTRACEP_FLUSH("%s:after call AxeJNI\n", UTT);                  //~v77fI~
    unrefLocal(penv,jstrOld);                                      //~v77fI~
    unrefLocal(penv,jstrUri);                                      //~v77fI~
    unrefLocal(penv,jstrNew);                                      //~v77fI~
	UTRACEP("%s:exit rc=%d\n",UTT,rc);                             //~v77fI~
    return rc;                                                     //~v77fI~
}                                                                  //~v77fI~
//**************************************************************** //~v77eI~
void c2j_notified_opendirDocResult(int Pfd)                        //~v77eI~
{                                                                  //~v77eI~
//  SopendirFD=dup(Pfd);                                           //~v77eR~
    SopendirFD=Pfd; //TODO test                                    //~v77eI~
    UTRACEP("%s:fd=%d,dup=%d\n",UTT,Pfd,SopendirFD);               //~v77eI~
}                                                                  //~v77eI~
//**************************************************************** //~v77eI~
int c2j_opendirDoc(int Popt,char *Pfpath,char *PstrUri,int *Ppfd) //~v77eI~
{                                                                  //~v77eI~
    static jmethodID staticMethod_opendirDoc;                      //~v77eI~
    jstring jstrPath,jstrUri;                                      //~v77eI~
    JNIEnv *penv;                                                  //~v77eI~
    int rc=EINTR;                                                  //~v77eI~
//****************************                                     //~v77eI~
	UTRACEP_FLUSH("%s:opt=0x%x,fpath=%s\n",UTT,Popt,Pfpath);       //~v77eI~
    penv=getThreadEnv();                                           //~v77eI~
    jstrPath = utf8z2jstring(penv, Pfpath);                        //~v77eI~
    jstrUri = utf8z2jstring(penv, PstrUri);                        //~v77eI~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,-1,opendirDoc,"(ILjava/lang/String;Ljava/lang/String;)I");//~v77eI~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~v77eI~
    C2J_INT(&rc,penv,opendirDoc,Popt,jstrPath,jstrUri);            //~v77eI~
    UTRACEP_FLUSH("%s:after call AxeJNI\n", UTT);                  //~v77eI~
    unrefLocal(penv,jstrPath);                                     //~v77eI~
    unrefLocal(penv,jstrUri);                                      //~v77eI~
    *Ppfd=SopendirFD;                                              //~v77eI~
	UTRACEP("%s:exit rc=%d\n",UTT,rc);                             //~v77eI~
    return rc;                                                     //~v77eI~
}                                                                  //~v77eI~
//**************************************************************** //~v77hI~
void c2j_notified_copyDocResult(int PerrSrc,int PerrTgt)           //~v77hR~
{                                                                  //~v77hI~
    UTRACEP("%s:errS=%d,errT=%d\n",UTT,PerrSrc,PerrTgt);           //~v77hR~
    ScopyErrSrc=PerrSrc;                                            //~v77hI~
    ScopyErrTgt=PerrTgt;                                            //~v77hI~
}                                                                  //~v77hI~
//**************************************************************** //~v77hI~
int c2j_copyDoc(int Popt,char *Psrc,char *PstrUriSrc,char *Ptgt,char *PstrUriTgt,int *PperrSrc,int *PperrTgt)//~v77hR~
{                                                                  //~v77hI~
    static jmethodID staticMethod_copyDoc;                         //~v77hI~
    jstring jstrSrc,jstrUriS=0,jstrTgt,jstrUriT=0;                 //~v77hR~
    JNIEnv *penv;                                                  //~v77hI~
    int rc=EINTR;                                                  //~v77hI~
//****************************                                     //~v77hI~
	ScopyErrSrc=0; ScopyErrTgt=0;                                  //~v77hI~
	UTRACEP_FLUSH("%s:opt=0x%x,src=%s,tgt=%s,strUriS=%s,strUriT=%s\n",UTT,Popt,Psrc,Ptgt,PstrUriSrc,PstrUriTgt);//~v77hI~
    penv=getThreadEnv();                                           //~v77hI~
    jstrSrc = utf8z2jstring(penv, Psrc);                           //~v77hI~
    jstrTgt = utf8z2jstring(penv, Ptgt);                           //~v77hI~
    if (PstrUriSrc)                                                //~v77hI~
	    jstrUriS= utf8z2jstring(penv, PstrUriSrc);                 //~v77hR~
    if (PstrUriTgt)                                                //~v77hI~
	    jstrUriT= utf8z2jstring(penv, PstrUriTgt);                 //~v77hR~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,-1,copyDoc,"(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I");//~v77hR~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~v77hI~
    C2J_INT(&rc,penv,copyDoc,Popt,jstrSrc,jstrUriS,jstrTgt,jstrUriT);//~v77hI~
    UTRACEP_FLUSH("%s:after call AxeJNI\n", UTT);                  //~v77hI~
    unrefLocal(penv,jstrSrc);                                      //~v77hI~
    unrefLocal(penv,jstrTgt);                                      //~v77hI~
    unrefLocal(penv,jstrUriS);                                     //~v77hI~
    unrefLocal(penv,jstrUriT);                                     //~v77hI~
    *PperrSrc=ScopyErrSrc;                                         //~v77hR~
    *PperrTgt=ScopyErrTgt;                                         //~v77hR~
	UTRACEP("%s:exit rc=%d,copyerrSrc=%d,coperrTgt=%d\n",UTT,rc,*PperrSrc,*PperrTgt);//~v77hI~
    return rc;                                                     //~v77hI~
}                                                                  //~v77hI~
//**************************************************************** //~v77mI~
void c2j_notified_statDocResult(int Pfd)                           //~v77mI~
{                                                                  //~v77mI~
    SstatFD=Pfd;                                                   //~v77mI~
    UTRACEP("%s:fd=%d\n",UTT,Pfd);                                 //~v77mI~
}                                                                  //~v77mI~
//**************************************************************** //~v77mI~
int c2j_statDoc(int Popt,char *Pfpath,char *PstrUri,int *Ppfd)     //~v77mI~
{                                                                  //~v77mI~
    static jmethodID staticMethod_statDoc;                         //~v77mI~
    jstring jstrPath,jstrUri;                                      //~v77mI~
    JNIEnv *penv;                                                  //~v77mI~
    int rc=EINTR;                                                  //~v77mI~
//****************************                                     //~v77mI~
	UTRACEP_FLUSH("%s:opt=0x%x,fpath=%s\n",UTT,Popt,Pfpath);       //~v77mI~
    penv=getThreadEnv();                                           //~v77mI~
    jstrPath = utf8z2jstring(penv, Pfpath);                        //~v77mI~
    jstrUri = utf8z2jstring(penv, PstrUri);                        //~v77mI~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,-1,statDoc,"(ILjava/lang/String;Ljava/lang/String;)I");//~v77mI~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~v77mI~
    C2J_INT(&rc,penv,statDoc,Popt,jstrPath,jstrUri);               //~v77mI~
    UTRACEP_FLUSH("%s:after call AxeJNI\n", UTT);                  //~v77mI~
    unrefLocal(penv,jstrPath);                                     //~v77mI~
    unrefLocal(penv,jstrUri);                                      //~v77mI~
    *Ppfd=SstatFD;                                                 //~v77mI~
	UTRACEP("%s:exit rc=%d\n",UTT,rc);                             //~v77mI~
    return rc;                                                     //~v77mI~
}                                                                  //~v77mI~
//**************************************************************** //~v77wI~
//void c2j_notified_getDocPathResult(char *Ppath)                    //~v77wR~//~vc5rR~
void c2j_notified_getDocPathResult(int Popt,char *Ppath)           //~vc5rI~
{                                                                  //~v77wI~
    strcpy(SgetDocPath,Ppath);                                     //~v77wI~
    SgetDocPathOpt=Popt;                                           //~vc5rI~
    UTRACEP("%s:path=%s\n",UTT,Ppath);                             //~v77wR~
}                                                                  //~v77wI~
//**************************************************************** //~v77wI~
int c2j_getDocPath(int Popt,char *Pfpath,char *PstrUri,char *Ppath)//~v77wI~
{                                                                  //~v77wI~
    static jmethodID staticMethod_getDocPath;                      //~v77wI~
    jstring jstrPath,jstrUri;                                      //~v77wI~
    JNIEnv *penv;                                                  //~v77wI~
    int rc=EINTR;                                                  //~v77wI~
//****************************                                     //~v77wI~
	UTRACEP_FLUSH("%s:opt=0x%x,fpath=%s,struri=%s\n",UTT,Popt,Pfpath,PstrUri);//~v77wI~
    SgetDocPath=Ppath;	//set at j2c notify                        //~v77wI~
    SgetDocPathOpt=0;                                              //~vc5rI~
    penv=getThreadEnv();                                           //~v77wI~
    jstrPath = utf8z2jstring(penv, Pfpath);                        //~v77wI~
    jstrUri = utf8z2jstring(penv, PstrUri);                        //~v77wI~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,-1,getDocPath,"(ILjava/lang/String;Ljava/lang/String;)I");//~v77wI~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~v77wI~
    C2J_INT(&rc,penv,getDocPath,Popt,jstrPath,jstrUri);            //~v77wI~
    UTRACEP_FLUSH("%s:after call AxeJNI\n", UTT);                  //~v77wI~
    unrefLocal(penv,jstrPath);                                     //~v77wI~
    unrefLocal(penv,jstrUri);                                      //~v77wI~
    if (SgetDocPathOpt & ROPT_REALPATH29)                             //~vc5rI~
    	rc=ROPT_REALPATH29;                                        //~vc5rI~
	UTRACEP("%s:exit rc=%d,SgetDocPathOpt=0x%x\n",UTT,rc,SgetDocPathOpt);                             //~v77wI~//~vc5rR~
    return rc;                                                     //~v77wI~
}                                                                  //~v77wI~
//**************************************************************** //~vc5cI~
int c2j_notifyAllSP(char* PallSP)                                  //~vc5cR~
{                                                                  //~vc5cI~
    static jmethodID staticMethod_notifyAllSP;                     //~vc5cI~
    jstring jstrAllSP;                                             //~vc5cI~
    JNIEnv *penv;                                                  //~vc5cI~
    int rc=0;                                                      //~vc5cI~
//****************************                                     //~vc5cI~
	UTRACEP_FLUSH("%s:allSP=%s\n",UTT,PallSP);       //~vc5cI~
    penv=getThreadEnv();                                           //~vc5cI~
    jstrAllSP=utf8z2jstring(penv,PallSP);                          //~vc5cI~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,-1,notifyAllSP,"(Ljava/lang/String;)I");//~vc5cI~
	UTRACEP_FLUSH("%s:before call AxeJNI\n",UTT);                  //~vc5cI~
    C2J_INT(&rc,penv,notifyAllSP,jstrAllSP);                       //~vc5cI~
    UTRACEP_FLUSH("%s:after call AxeJNI\n", UTT);                  //~vc5cI~
    unrefLocal(penv,jstrAllSP);                                    //~vc5cI~
	UTRACEP("%s:exit rc=%d\n",UTT,rc);                             //~vc5cI~
    return rc;                                                     //~vc5cI~
}                                                                  //~vc5cI~
