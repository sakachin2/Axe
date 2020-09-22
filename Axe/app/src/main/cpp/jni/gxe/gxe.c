//*CID://+vc01R~:                             update#=  196;       //+vc01R~
//***************************************************************************//~v59zI~
//vc01 2020/06/10 malloc required include stdlib.h                 //+vc01I~
//v@@@:110610 Android(-DARM)                                       //~v@@@I~
//va70:000701 LP64 option support. at first chk PTR sz(ini file comaptibility)//~v770I~
//v76c:070617 (XXE)paste from PRIMARY clipboard by middle button down//~v76cI~
//v71A:061030 Use HtmlHelp;Winhelp is deprecated at Vista;XP english ersion cannot read .hlp by the readson of codepage unsupported//~v71AI~
//v69u:060602 (XXE)try im for english mode                         //~v69uI~
//v669:050826 (XXE)help support                                    //~v669I~
//***************************************************************************//~v59zI~
// gxe.c                                                           //~v59zI~
//***************************************************************************//~v59zI~
//                                                                 //~v59zI~
#include <stdio.h>                                                 //~v@@@I~
#include <stdlib.h>                                                //+vc01I~
                                                                   //~v59zI~
#ifdef HAVE_CONFIG_H                                               //~v59zI~
#  include <config.h>                                              //~v59zI~
#endif                                                             //~v59zI~
                                                                   //~v59zI~
#ifndef ARM                                                        //~v@@@M~
#include <gnome.h>                                                 //~v59zI~
#include <libgnomeprint/gnome-print.h>                             //~v59zI~
#include <libgnomeprint/gnome-print-job.h>                         //~v59zI~
                                                                   //~v59zI~
#include "callbacks.h"                                             //~v59zI~
#include "interface.h"                                             //~v59zI~
#include "support.h"                                               //~v59zI~
#else                                                              //~v@@@I~
	#include <jnig.h>                                              //~v@@@I~
#endif                                                             //~v@@@I~
                                                                   //~v59zI~
#include <ulib.h>                                                  //~v59zI~
#include <ufile.h>                                                 //~v669I~
                                                                   //~v59zI~
#include <xxedef.h>                                                //~v59zM~
#include <xxemain.h>                                               //~v59zM~
#include <xxexei.h>                                                //~v59zM~
                                                                   //~v59zI~
#define  GBL_GXE                                                   //~v59zI~
#include "gxe.h"                                                   //~v59zI~
#include "gxemfrm.h"                                               //~v59zR~
#include "gxeview.h"                                               //~v59zI~
                                                                   //~v59zI~
//***************************************************************************//~v59zI~
void gxe_im_init(void);                                            //~v59zI~
void gxe_parmchk(int *Ppargc,char ***Ppargv);                      //~v59zI~
void gxe_appinit(void);                                            //~v59zI~
void gxe_help(void);                                               //~v59zI~
//***************************************************************************//~v59zI~
// init1:before frame window create                                //~v59zR~
//***************************************************************************//~v59zI~
void gxe_init(int argc,char *arg[])                                //~v59zR~
{                                                                  //~v59zI~
	gxe_parmchk(&argc,&arg);                                       //~v59zI~
    wxe_preinit(argc,arg);  //xe preinit;get Gworkdir              //~v59zR~
    gxemfrm_construct();  		//read ini data                    //~v59zI~
    gxe_appinit();                                                 //~v59zI~
    xxemain_construct();		//xxemain global init              //~v59zR~
	gxe_im_init();                                                 //~v59zI~
}                                                                  //~v59zI~
//***************************************************************************//~v59zI~
// drop gnome parameter and chk xxe parameter                      //~v59zI~
//***************************************************************************//~v59zI~
void gxe_parmchk(int *Ppargc,char ***Ppargv)                       //~v59zI~
{                                                                  //~v59zI~
	int argc,nargc,ii;                                             //~v59zR~
	char **argv,**nargv,*pc;                                       //~v59zR~
//  FILEFINDBUF3 ffb3;                                             //~v71AR~
//static char Shelpdir[_MAX_PATH];                                 //~v71AR~
//*********************                                            //~v59zI~
    argc=*Ppargc;                                                  //~v59zI~
    argv=*Ppargv;                                                  //~v59zI~
//  *Ppargv=nargv=(char**)malloc((argc+1)*4);                      //~v59zR~//~v770R~
    *Ppargv=nargv=(char**)malloc((argc+1)*PTRSZ);                  //~v770I~
    nargc=0;                                                       //~v59zI~
    for (ii=0;ii<argc;ii++,argv++)                                 //~v59zI~
    {                                                              //~v59zI~
    	pc=*argv;                                                  //~v59zI~
    	if (*pc=='-'&&*(pc+1)=='-')                                //~v59zI~
        {                                                          //~v59zI~
        	pc+=2;                                                 //~v59zI~
//            if (!memicmp(pc,"hd:",3)) //helpdir                  //~v71AR~
//            {                                                    //~v71AR~
//                ufullpath(Shelpdir,pc+3,_MAX_PATH);              //~v71AR~
//                Ghelpdir=Shelpdir;                               //~v71AR~
//                if (ufstat(Ghelpdir,&ffb3)||!(ffb3.attrFile & FILE_DIRECTORY))//~v71AR~
//                {                                                //~v71AR~
//                    printf("helpdir %s is not found or not dir\n",Ghelpdir);//~v71AR~
//                    exit(8);                                     //~v71AR~
//                }                                                //~v71AR~
//            }                                                    //~v71AR~
//            else                                                 //~v71AR~
        	if (!stricmp(pc,"h"))                                  //~v59zR~
            	gxe_help();                                        //~v59zI~
            else                                                   //~v59zI~
        	if (!stricmp(pc,"?"))                                  //~v59zI~
            	gxe_help();                                        //~v59zI~
            else                                                   //~v59zI~
        	if (!stricmp(pc,"xxekbchk"))                           //~v59zR~
            	Gxxeopt|=GXXEOPT_KBCHK;                            //~v59zI~
            continue;                                              //~v59zI~
        }                                                          //~v59zI~
        *nargv++=pc;                                               //~v59zI~
        nargc++;                                                   //~v59zI~
    }                                                              //~v59zI~
    *nargv=0;                                                      //~v59zI~
    *Ppargc=nargc;                                                 //~v59zI~
	return;                                                        //~v59zI~
}//gxe_parmchk                                                     //~v59zI~
//********************************************************         //~v59zI~
// help msg                                                        //~v59zR~
//********************************************************         //~v59zI~
void gxe_help(void)                                                //~v59zI~
{                                                                  //~v59zI~
//*********************************                                //~v59zI~
	fprintf(stdout,"gxe usage\n");                                 //~v59zR~
	fprintf(stdout,"gxe [--xxeoptions] [-xeoptions] [filename]\n");//~v59zR~
	fprintf(stdout,"  --xxe-options :\n");                         //~v59zR~
	fprintf(stdout,"    --h         : print this help.\n");        //~v59zR~
	fprintf(stdout,"   (--help      : gtk help)\n");               //~v59zI~
	fprintf(stdout,"    --?         : print this help.\n");        //~v59zR~
//  fprintf(stdout,"    --hd:dir    : help dir.\n");               //~v71AR~
//  fprintf(stdout,"                  default=$HOME/xehelp:%s\n",DEF_HELPDIR);//~v71AR~
	fprintf(stdout,"    --xxekbchk  : print key-code acceptance.\n");//~v59zR~
	fprintf(stdout,"  -xe-options   : option to xe (chk it by \"gxe -h\")\n");//~v59zR~
	fprintf(stdout,"  filename      : file or dir name to be opened.\n");//~v59zR~
    exit(1);                                                       //~v59zI~
}//gxe_im_init(void)                                               //~v59zI~
//********************************************************         //~v59zI~
// im init                                                         //~v59zI~
//********************************************************         //~v59zI~
void gxe_appinit(void)                                             //~v59zI~
{                                                                  //~v59zI~
//*********************************                                //~v59zI~
	Gpclipboard=gtk_clipboard_get(GDK_SELECTION_CLIPBOARD);        //~v59zI~
	Gpclipboardp=gtk_clipboard_get(GDK_SELECTION_PRIMARY);         //~v76cI~
    return;                                                        //~v59zI~
}//gxe_appinit                                                     //~v59zR~
//********************************************************         //~v59zI~
// im init                                                         //~v59zI~
//********************************************************         //~v59zI~
void gxe_im_init(void)                                             //~v59zI~
{                                                                  //~v59zI~
//*********************************                                //~v59zI~
//  if (wxegetdbcsmode)      //no compile err?                     //~v669R~
//  if (wxegetdbcsmode())                                          //~v69uR~
		Gpimcontext=gtk_im_multicontext_new();                     //~v59zR~
    return;                                                        //~v59zI~
}//gxe_im_init(void)                                               //~v59zI~
#ifndef ARM                                                        //~v@@@I~
//**************************************************************** //~v59zI~
//* printf format  nop mode                                        //~v59zI~
//**************************************************************** //~v59zI~
void dummyprintf(char *Pfmt,...)                                   //~v59zI~
{                                                                  //~v59zI~
    return;                                                        //~v59zI~
}//dummyprintf                                                     //~v59zI~
#endif //ARM                                                       //~v@@@I~
