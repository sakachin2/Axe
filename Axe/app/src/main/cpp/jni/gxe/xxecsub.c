//*CID://+vc1iR~:                             update#=  596;       //~vc1iR~
//***************************************************************************//~2818I~
//* c subrtn for wxe                                               //~2818I~
//***************************************************************************//~2818I~
//vc1i 2020/06/22 display not /emulated/.. but /sdcard to header CWD=//~vc1iI~
//vc07:200613 Axe:utfddcombine(link err)<--csubddcombine(no caller); delete csubddcombine(vbi9:180221 on linux gxe)//~vc07R~
//vc07:2020/06/13 Axe:utfddcombine(link err)<--csubddcombine(no caller); delete csubddcombine(vbi9:180221 on linux gxe)//~vc07I~
//vb91:170228 marge rh9j gxe to arm-gxe                            //~vb91I~
//				vb3z:160623 (gxe)dbcsspace altch by 1 altch for lc file same as utf8 file//~vb91I~
//vax6:140627 (for jni)vamG:131119 (BUG:gxe)lc print conversion fail when dbcs split at eol(recfm=R/V)//~vax6I~
//vax5:140627 (130928:vam4 for jni)file scrprint show unprintable by box char,use altch like scr draw//~vax5I~
//v6un:140502 MultiByte2WideChar failes by 0xfdff(cp)-->f8f1+f8f3(ucs2) by MB_ERR_INVALID_CHARS;pass it not W func as is//~v6unI~
//vak2:130822 Axe:ndk-r9 warning                                   //~vak2R~
//v@@@:110610 Android(-DARM)                                       //~v@@@I~
//va70:000701 LP64 option support. at first chk PTR sz(ini file comaptibility)//~va70I~
//va6X:000728 (BUG)suse64 abend at print dialog open(missing apply of v5nD)//~va6XI~
//va6p:000623 (LNX:BUG) ligature ignored for locale file           //~va6pI~
//va6n:000623 (BUG)Guviompadch was changed to ucs by ulib v65p     //~va6nI~
//va53:100419 num setr/seti should chk margin/lrecl overflow       //~va53I~
//va47:100401 va46 for LNX                                         //~va47I~
//va44:100329 to print 0x14 glyph(q:0xb6),use another altchar(0xff)(DBCS space)//~va44I~
//va42:100328 (WXE:BUG)tab,tab padding char is not visual,print alt char//~va42I~
//va3b:100205 (GXE)no ligature for hex edit(HEX mode and EH cmd)   //~va3bI~
//va30:100126 combine mode of diacritical marks by A+/(saved to ini)//~va30I~
//va20:091125 (UTF8)UTF8 full support(display utf8 also unprintable as locale code)//~va20I~
//va1c:091012 GB18030 4 byte dbcs support                          //~va1cR~
//v7ax:090414 (LNX)compile waring int:gsize                        //~v7axI~
//v7ad:081109 EUC-JP SS3 support(display by .+DBCS)                //~v7adI~
//v7a4:081102 (XXE:BUG)abend by /N8 option                         //~v7a4I~
//v79Y:081029 (XXE) replace unprintable by '.'                     //~v79YI~
//v79Q:081027 (XXE:BUG)0x0a or 0x0d is cause of line is not drawn;replace it by unprintable id//~v79QI~
//v79D:081011 (XXE) draw ime cursor                                //~v79DI~
//v79z:080916 DBCS tbl for C and K of CJK                          //~v79zI~
//v78D:080503 (XXE)csr write to scr direct(not pix map) to avoid old csr line draw//~v77qI~
//v77q:080110*(XXE:BUG) screen hight expand when font style chang  //~v77qI~
//v706:060729 (XXE:BUG)Gerror should be clear before call(cause abend)//~v706I~
//v705:060727 graphic char setup by external file                  //~v705I~
//v69X:060720 (XXE)line-draw support also fo gxe when OPT LINECH ON.(v69M)//~v69XI~
//v69V:060718 (XXE)Performance tuning for pango.                   //~v69VI~
//                 gdk_draw_layout_with_color for each char one by one is too slow//~v69VI~
//                 On the other hand string draw requres monospace and usetmonospace exaust cpu on gprof.//~v69VI~
//                 Bug of monospace determination was exist.(missed to set font to layout)//~v69VI~
//                 Monospace determination obey to font rfamily attribute.//~v69VI~
//                 DBCS is not just double iof sbcs width even for monospace font.//~v69VI~
//                 So use string write for sbcs only if mono space,else use uset monospace.//~v69VI~
//v69U:060718 (XXE:BUG)errmsg "Cut/Pastet source data contains invalid data"//~v69UI~
//            because scr width is not multiple of cellw,copy data contains 1 column over//~v69UI~
//v69Q:060715 (XXE)utf conversion perfomance chk                   //~v69QI~
//v69s:060531 (XXE:fc5)g_locale_to_utf8 return error if null char contained//~v69sI~
//v69r:060531 (XXE)print debug message by GError                   //~v69rI~
//v69n:060530 (XXE:fc5)avoid Gtk warning "GError not initialized". //~v69nI~
//v68j:060404 (XXE)gtk dmsgdialog okcan button default is cancel,force ok as default like as Windows//~v68jI~
//v68i:060331 (WXEXXE)activate window when dropped                 //~v68iI~
//v685:060322 dnd support "file copy into the dir slected" and even in inter-split-screen.//~v685I~
//v67A:051227 (GXE:BUG)msg dialog source string should be utf8     //~v67AI~
//v66A:051220 dialog to confirm wxe/gxe termination when func-key used//~v66AI~
//vXXE:050611 new for XXE                                          //~vXXEI~
//v63m:050510 (WXE)draw screen between exe cmd file                //~v63mI~
//v56h:040404 (WXE)ignore setcaretpos r.c because it may occur when windows z-order is changed//~v56hI~
//v560:040324 (WIN)break sleep by esc*2                            //~v560I~
//v55Z:040324 (WXE)ctl multicmd by wxe to display intermediate screen//~v55ZI~
//v55W:040322 (WXE)AT cmd support;"AT(&) interval,count;KEY_S_P0       cmd"//~vXXER~
//v55D:040307 (WXE)kick wxe.hlp by help cmd             KEY_S_P1   //~vXXER~
//v55c:040208 (WXE)distingush L-CTL/R-CTL               KEY_S_P2   //~vXXER~
//v54y:040118 (WXE)wxp cmd support(send file to wxe for KEY_S_P3       print by wxe)//~vXXER~
//v53t:031004 (WXE:BUG)not freed area exist             KEY_S_P4   //~vXXER~
//******************************************************KEY_S_P5       *********************//~vXXER~
#include <time.h>                                                  //~vXXER~
#include <stdio.h>                                                 //~vXXER~
#include <stdlib.h>                                                //~vXXER~
#include <string.h>                                                //~vXXER~
#ifndef ARM                                                        //~v@@@M~
#include <iconv.h>                                                 //~va1cR~
//******************************************************KEY_S_PPERIOD  *******//~vXXER~
#include <gnome.h>                                                 //~vXXER~
#include <glib.h>                                                  //~v69QI~
#include <libgnomeprint/gnome-print.h>                             //~vXXEI~
#include <libgnomeprint/gnome-print-job.h>                         //~vXXEI~
                                                                   //~vXXER~
#include "callbacks.h"                                             //~vXXER~
#include "interface.h"                                             //~vXXER~
#include "support.h"                                               //~vXXEI~
#else                                                              //~v@@@I~
	#include <jnig.h>                                              //~v@@@I~
	#include <jnia.h>
#endif //ARM                                                       //~v@@@I~
                                                                   //~2907I~
#include "ulib.h"                                                  //~2907I~
#include "ualloc.h"                                                //~2907R~
#include "uque.h"                                                  //~2929I~
#include "uerr.h"                                                  //~2907I~
#include "utrace.h"                                                //~2914I~
#include "ufile.h"                                                 //~2922I~
#include "ufile2.h"                                                //~2923I~
#include "ufile4.h"                                                //~2923I~
#include "udbcschk.h"                                              //~vXXEI~
#include "uedit.h"                                                 //~v69rI~
#include "ucvucs.h"                                                //~va1cR~
#include "utf.h"                                                   //~va1cR~
#include "uviom.h"                                                 //~va1cR~
#ifdef UTF8UCS2                                                    //~va20I~
#include "utf22.h"                                                 //~va20I~
#endif                                                             //~va20I~
#ifdef ARM                                                         //+vc1iI~
    #include <ulibarm.h>                                           //+vc1iI~
#endif                                                             //+vc1iI~
                                                                   //~2818I~
#include "xxedef.h"                                                //~vXXER~
#include "xxecsub.h"                                               //~vXXER~
#include <gxe.h>                                                   //~vXXER~
#include <xe.h>                                                    //~vXXEI~
#include <xescr.h>     //for xesub2.h                              //~va53I~
#include <xefile.h>    //for xesub2.h                              //~va53M~
#include <xesub2.h>                                                //~va1cR~
#include <xxexei.h>                                                //~vXXEI~
#include <xxemain.h>                                               //~vXXEI~
//***************************************************************************//~2818I~
#define UERRMSG_MAXPARM   10                                       //~va6XR~
#define MAX_LINEACS 11                                             //~v705I~
//#define COL(x)          ((x-BORDERMARGIN)/Scellw)                //~vXXER~
//#define ROW(y)          ((y-BORDERMARGIN)/Scellh)                //~vXXER~
//#define XX(col)         ((col)*Scellw+BORDERMARGIN)              //~vXXER~
//#define YY(row)         ((row)*Scellh+BORDERMARGIN)              //~vXXER~
//***************************************************************************//~2831I~
#ifndef XXE                                                        //~vXXEI~
static int Sstylectr=0;                                            //~2818I~
static int Selfmaxctr=0;                                           //~vXXER~
#endif                                                             //~vXXEI~
static GtkWindow  *Shwnd=0;                                        //~vXXER~
//static HBITMAP Shbm_caret[4];                                    //~2914R~
//static int   Scellh,Scellw;                                      //~vXXER~
static int   Scaretheight,Scrow,Sccol;                             //~vXXER~
static char  Swdtemp[_MAX_PATH];                                   //~2B30R~
//static int Sisutf8;                                              //~v79zR~
static const char *Scurrent_charsetname=0;                         //~v69QR~
static GIConv Sconverterdescriptor=0;                              //~v69QR~
static UCHAR *Sacsmap[256]; //line drawing char definition         //~v69XM~
static int    Sacslentb[256]; //line drawing char length           //~v705R~
//***************************************************************************//~2907I~
int  iniacrnmchk(FILE *Pfh);                                       //~2907I~
int  iniacrnmput(FILE *Pfh);                                       //~2907I~
int  usetupwd(void);                                               //~2A12I~
int  usetupwdtemp(void);                                           //~2A12I~
int udrawcaret(int Pshowsw,int Prow,int Pcol);                     //~vXXEI~
int csubutf8init(void);                                            //~v69QI~
//***************************************************************************//~2831I~
//init static                                                      //~2831I~
//***************************************************************************//~2831I~
int csubinit1(HWND Phwnd)                                          //~vXXER~
{                                                                  //~2831I~
	Shwnd=(GtkWindow*)Phwnd;	//Mpview->window                   //~vXXER~
//    memcpy(Shbm_caret,Phbmtbl,sizeof(Shbm_caret));               //~2914R~
	csubutf8init();                                                //~v69QI~
    return 0;                                                      //~2831I~
}                                                                  //~2831I~
int csubutf8init(void)                                             //~v69QI~
{                                                                  //~v69QI~
    int isutf8;                                                    //~v79zI~
//******************                                               //~v79zI~
//  Sisutf8=g_get_charset(&Scurrent_charsetname);                  //~v79zR~
    isutf8=g_get_charset(&Scurrent_charsetname);                   //~v79zI~
    if (isutf8)                                                    //~v79zR~
    	Gwxestat|=GWXES_ENVISUTF8;                                 //~v79zR~
//  if (!Sisutf8)    //current is not utf8                         //~v79zR~
    if (!isutf8    //current is not utf8                           //~v79zR~
//  ||   Gxxestat & GXXES_KBDIMEICONV)  //locale cmdline parm or UTF8 env(file is mb)//~v79zI~//~v7a4R~
    ||   *Gcharset)                     //locale cmdline parm or UTF8 env(file is mb)//~v7a4I~
    {                                                              //~v69QI~
//		if (Gxxestat & GXXES_KBDIMEICONV)  //locale cmdline parm or UTF8 env(file is mb)//~v79zI~//~v7a4R~
  		if (*Gcharset)  //default or /Ccharset(Not UTF8) parm      //~v7a4I~
			Scurrent_charsetname=Gcharset;                         //~v79zI~
        Sconverterdescriptor=g_iconv_open ("UTF-8",Scurrent_charsetname);//~v69QI~
        if (Sconverterdescriptor==(GIConv)-1)                      //~v69QR~
            uerrmsg("%s to UTF-8 is not supported\n",0,            //~v69QI~
					Scurrent_charsetname);                         //~v69XI~
    }                                                              //~v69QI~
//  dprintf("utf8mode=%d charset=%s,converter=%x\n",isutf8,Scurrent_charsetname,(UINT)Sconverterdescriptor);//~v79zR~//~va70R~
    dprintf("utf8mode=%d charset=%s,converter=%p\n",isutf8,Scurrent_charsetname,Sconverterdescriptor);//~va70I~
    return 0;                                                      //~v69QI~
}                                                                  //~v69QI~
//*********************************************************************//~v69XM~
//* uviol_acssetup  for EUC code                                   //~v69XM~
//*   parm seq is fix                                              //~v69XM~
//*********************************************************************//~v69XM~
void csubacssetup(int Psetsw,int Pentno,UCHAR *Pacstbl,int *Pplentb,char **Pdispstr)//~v705R~
{                                                                  //~v69XM~
#ifndef ARM                                                        //~v@@@I~
	int ii;                                                        //~v69XM~
	UCHAR *pc;                                                     //~v69XM~
#endif //ARM                                                       //~v@@@I~
//*************************                                        //~v69XM~
    memset(Sacsmap,0,sizeof(Sacsmap));                             //~v705I~
#ifndef ARM                                                        //~v@@@I~
    if (Psetsw)	//not set req                                      //~v705R~
    {                                                              //~v705I~
        for (ii=0,pc=Pacstbl;ii<MAX_LINEACS;ii++,pc++)             //~v69XM~
        {                                                          //~v69XM~
         	if (ii<Pentno && *pc)                                  //~v69XM~
			{                                                      //~v705I~
//          	Sacsmap[*pc]=Sgraphchutfcode[ii];                  //~v705R~
            	Sacsmap[(int)(*pc)]=Pdispstr[ii];                  //~v705R~
		        Sacslentb[(int)(*pc)]=Pplentb[ii];                 //~v705R~
            }                                                      //~v705I~
        }                                                          //~v69XM~
    }                                                              //~v705I~
#endif //ARM                                                       //~v@@@I~
    return;                                                        //~v69XM~
}//uviol_acssetup                                                  //~v69XM~
//**************************************************************** //~2831I~
// save caret type                                                 //~vXXEI~
// parm1:start line hight; 1:tall(insert),2:low(rep)-->see xecsr.c //~vXXEI~
//*rc:ret code                                                     //~2831I~
//**************************************************************** //~2831I~
int usetcaret(int Pheightrate,int Pcellw,int Pcellh,int Pfonth)    //~vXXER~
{                                                                  //~2831I~
//********************************                                 //~2831I~
//    Scdbcssw=Pdbcssw;   //save for setcaretpos failed            //~vXXER~
	if (Pheightrate==1)     //ins mode                             //~vXXER~
    	Scaretheight=Pfonth/2*2;  //font height from cell bottom,even number to avoid overflow to next cell//~vXXER~
    else                                                           //~vXXEI~
	if (Pheightrate==2)     //rep mode                             //~vXXER~
    {                                                              //~vXXEI~
	    Scaretheight=Pcellh/20*2;   //even number    10%           //~vXXER~
        if (Scaretheight<DEF_TEXTCSR_HEIGHT)                       //~vXXER~
        	Scaretheight=DEF_TEXTCSR_HEIGHT;                       //~vXXER~
    }                                                              //~vXXEI~
    else                                                           //~vXXEI~
        Scaretheight=1;                                            //~vXXEI~
    dprintf("caret height=%d,fonth=%d\n",Scaretheight,Pfonth);     //~vXXER~
	gdk_gc_set_line_attributes(Gpgccsr,Scaretheight,               //~vXXEI~
    	GDK_LINE_SOLID,GDK_CAP_NOT_LAST,GDK_JOIN_MITER);           //~vXXEM~
dprintf("setcaret rate=%d,hight=%d,cellh=%d,fonth=%d\n",Pheightrate,Scaretheight,Pcellh,Pfonth);//~vXXER~
    return 0;                                                      //~vXXER~
}                                                                  //~2831I~
//**************************************************************** //~2922I~
//*rc:ret code                                                     //~2922I~
//**************************************************************** //~2922I~
void uredrawcaret(void)                                            //~2922I~
{                                                                  //~2922I~
//********************************                                 //~2922I~
#ifndef XXE                                                        //~vXXEI~
	usetcaret(Scdbcssw,Scheightrate,Scellw,Scellh,Scfonth);	//recreate//~2922I~
	usetcaretpos(Scrow,Sccol);                                     //~2922I~
#endif                                                             //~vXXEI~
	ushowcaret();                                                  //~2922I~
    return;                                                        //~2922I~
}                                                                  //~2922I~
//**************************************************************** //~2831I~
//*rc:ret code                                                     //~2831I~
//**************************************************************** //~2831I~
int ushowcaret(void)                                               //~2831I~
{                                                                  //~2831I~
	udrawcaret(1,Scrow,Sccol);                                     //~vXXER~
    return 0;                                                      //~vXXEI~
}                                                                  //~2831I~
//**************************************************************** //~2831I~
//*rc:ret code                                                     //~2831I~
//**************************************************************** //~2831I~
int uhidecaret(void)                                               //~2831I~
{                                                                  //~2831I~
	udrawcaret(0,Scrow,Sccol);                                     //~vXXER~
    return 0;                                                      //~vXXEI~
}                                                                  //~2831I~
//**************************************************************** //~2831I~
//*rc:ret code                                                     //~2831I~
//**************************************************************** //~2831I~
int ugetcaretpos(int *Pprow,int *Ppcol)	//client axis              //~2831I~
{                                                                  //~2831I~
    *Pprow=Scrow;                                                  //~vXXEI~
    *Ppcol=Sccol;                                                  //~vXXEI~
    dprintf("get caretpos row=%d,col=%d\n",*Pprow,*Ppcol);         //~vXXER~
    return 0;                                                      //~vXXEI~
}                                                                  //~2831I~
//**************************************************************** //~2831I~
//*rc:ret code                                                     //~2831I~
//**************************************************************** //~2831I~
int usetcaretpos(int Prow,int Pcol)                                //~2831I~
{                                                                  //~2831I~
//********************************                                 //~vXXEI~
	if (Prow!=Scrow||Pcol!=Sccol)	                               //~vXXEI~
		uhidecaret();                                              //~vXXER~
    Scrow=Prow;                                                    //~vXXER~
    Sccol=Pcol;                                                    //~vXXER~
    dprintf("set caretpos row=%d,col=%d\n",Prow,Pcol);             //~vXXER~
//  ushowcaret();                                                  //~vXXER~
    return 0;                                                      //~vXXEI~
}                                                                  //~2831I~
//**************************************************************** //~vXXEI~
//*rc:draw visivle/invisivle caret                                 //~vXXEI~
//**************************************************************** //~vXXEI~
int udrawcaret(int Pshowsw,int Prow,int Pcol)                      //~vXXEI~
{                                                                  //~vXXEI~
    char *pdbcs;                                                   //~vXXER~
    int ww,hh,xx,xx2,yy;                                           //~vXXER~
#ifdef UTF8SUPPH                                                   //~va1cR~
    int dbcssz;                                                    //~va1cR~
#endif                                                             //~va1cR~
//********************************                                 //~vXXEI~
    if (Mwxeintf.WXEIxetermsw)	//xe term called already           //~v78DI~
    	return 0;                                                  //~v78DI~
	if (!Pshowsw)                                                  //~vXXER~
    	return 0;                                                  //~vXXEI~
    pdbcs=(char*)TEXTDBCS(Prow,Pcol);                              //~vXXEI~
#ifdef UTF8UCS2                                                    //~va20I~
    if (UDBCSCHK_DBCS1STLU(*pdbcs))                                //~va20I~
#else                                                              //~va20I~
    if (*pdbcs==UDBCSCHK_DBCS1ST)                                  //~vXXER~
#endif                                                             //~va20I~
    {                                                              //~va1cR~
#ifdef UTF8SUPPH                                                   //~va1cR~
        dbcssz=XESUB_DBCSSPLITCTR(pdbcs,Mscrcmaxcol-Pcol,0);       //~va1cR~
	    ww=Mcellw*dbcssz;                                          //~va1cR~
#else                                                              //~va1cR~
        if ((Pcol+1)>=Mscrcmaxcol)                                 //~vXXEI~
	        ww=Mcellw;                                             //~vXXEI~
        else                                                       //~vXXEI~
	        ww=Mcellw+Mcellw;                                      //~vXXER~
#endif                                                             //~va1cR~
    }                                                              //~va1cR~
    else                                                           //~vXXEI~
        ww=Mcellw;                                                 //~vXXEI~
    hh=Scaretheight;                                               //~vXXER~
    xx=XX(Pcol);                                                   //~vXXER~
    yy=YY(Prow+1)-hh/2;	//from bottom of cell (pos is center of height?)//~vXXER~
#ifdef ARM                                                         //~vax5I~
    if (hh & 0x01)  //odd                                          //~vax5I~
        yy--;                                                      //~vax5I~
#endif                                                             //~vax5I~
    xx2=xx+ww;                                                     //~vXXER~
    dprintf("draw caret loc x=%d,y=%d w=%d,h=%d\n",xx,yy,ww,hh);   //~vXXER~
//  pgc=_gtk_get_insertion_cursor_gc(Gpview,FALSE);//   true:primary(black),false:secondary(gray)//~vXXER~
//#define XXX                                                      //~vXXER~
#ifdef XXX                                                         //~vXXEI~
    _gtk_draw_insertion_cursor(Gpview,Gppixmap,pgc,&csrloc,GTK_TEXT_DIR_LTR,1);//0:no draw arrow//~vXXER~
#else                                                              //~vXXEI~
//    for (ii=0,xx=csrloc.x,xx2=xx+csrloc.width,yy=csrloc.y;ii<csrloc.height;ii++,yy++)//~vXXER~
//        gdk_draw_line(Gppixmap,pgc,xx,yy,xx2,yy);                //~vXXER~
//  gdk_draw_line(Gppixmap,Gpgccsr,xx,yy,xx2,yy);                  //~v78DR~
    dprintf("Gpview=%p,window=%p\n",Gpview,Gpview->window);        //~v78DI~
    gdk_draw_line(Gpview->window,Gpgccsr,xx,yy,xx2,yy);            //~v78DR~
#endif                                                             //~vXXEI~
    return 0;                                                      //~vXXER~
}//udrawcaret                                                      //~vXXEI~
#ifndef ARM                                                        //~v@@@I~
//**************************************************************** //~2901I~
//*rc:ret code                                                     //~2901I~
//**************************************************************** //~2901I~
//int umsgbox(char *Ppmsg)                                         //~v685R~
int umsgbox(char *Ppmsg,int Pflag)                                 //~v685I~
{                                                                  //~2901I~
	GtkWidget *msgdlg;                                             //~vXXEI~
    int button;                                                    //~v685I~
    int rc;                                                        //~v66AI~
//********************************                                 //~2901I~
//  MessageBox(Shwnd,Ppmsg,NULL,MB_OK);                            //~vXXER~
	if (Pflag==MB_YESNOCANCEL)                                     //~v685I~
//  	button=MB_YESNO;                                           //~v68jR~
    	button=GTK_BUTTONS_NONE;                                   //~v68jI~
    else                                                           //~v685I~
	if (Pflag==MB_OKCANCEL)                                        //~v68jR~
    	button=MB_OK;                                              //~v68jI~
    else                                                           //~v68jI~
    	button=Pflag;                                              //~v685I~
    msgdlg=gtk_message_dialog_new(NULL,	//parent                   //~v66AR~
                                GTK_DIALOG_MODAL|GTK_DIALOG_DESTROY_WITH_PARENT, //flag//~vXXER~
                                GTK_MESSAGE_ERROR,	//messagetype  //~vXXEI~
//                              GTK_BUTTONS_CLOSE,   //button      //~v685R~
//                              Pflag,              //button       //~v685R~
                                button,             //button       //~v685I~
//  							Ppmsg);             //msg          //~vax5R~
								"%s",Ppmsg);             //msg     //~vax5I~
	if (Pflag==MB_YESNOCANCEL)                                     //~v685I~
    {                                                              //~v68jI~
        gtk_dialog_add_button(GTK_DIALOG(msgdlg),GTK_STOCK_YES,GTK_RESPONSE_YES);//~v68jI~
        gtk_dialog_add_button(GTK_DIALOG(msgdlg),GTK_STOCK_NO,GTK_RESPONSE_NO);//~v68jI~
        gtk_dialog_add_button(GTK_DIALOG(msgdlg),GTK_STOCK_CANCEL,GTK_RESPONSE_CANCEL);//~v685R~
    }                                                              //~v68jI~
    else                                                           //~v68jI~
	if (Pflag==MB_OKCANCEL)                                        //~v68jR~
        gtk_dialog_add_button(GTK_DIALOG(msgdlg),GTK_STOCK_CANCEL,GTK_RESPONSE_CANCEL);//~v68jI~
    rc=                                                            //~v66AI~
    gtk_dialog_run(GTK_DIALOG(msgdlg));                            //~vXXEI~
    gtk_widget_destroy(msgdlg);                                    //~vXXEI~
    if (rc==IDOK||rc==IDYES)                                       //~v66AI~
    	return 0;                                                  //~v66AI~
    return rc;                                                     //~v66AR~
}                                                                  //~2901I~
#endif //ARM                                                       //~v@@@M~
#ifndef ARM                                                        //~v@@@I~
//**************************************************************** //~v66AI~
//*rc:ret code                                                     //~v66AI~
//**************************************************************** //~v66AI~
int umsgbox2(char *Ppmsg,int Pmessagetype,int Pbuttontype)         //~v66AR~
{                                                                  //~v66AI~
	int rc;                                                        //~v66AI~
	GtkWidget *msgdlg;                                             //~v66AI~
    int button;                                                    //~v685I~
//********************************                                 //~v66AI~
	if (Pbuttontype==MB_YESNOCANCEL)                               //~v685R~
    	button=GTK_BUTTONS_NONE;                                   //~v68jR~
    else                                                           //~v68jI~
	if (Pbuttontype==MB_OKCANCEL)                                  //~v68jI~
    	button=MB_OK;                                              //~v68jI~
    else                                                           //~v685I~
    	button=Pbuttontype;                                        //~v685I~
    msgdlg=gtk_message_dialog_new(NULL,	//parent                   //~v66AR~
                                GTK_DIALOG_MODAL|GTK_DIALOG_DESTROY_WITH_PARENT, //flag//~v66AI~
                                Pmessagetype,	//messagetype      //~v66AI~
//                              Pbuttontype,   //button            //~v685R~
                                button,   //button                 //~v685I~
//  							Ppmsg);             //msg          //~vax5R~
								"%s",Ppmsg);             //msg     //~vax5I~
	if (Pbuttontype==MB_YESNOCANCEL)                               //~v685I~
    {                                                              //~v68jI~
        gtk_dialog_add_button(GTK_DIALOG(msgdlg),GTK_STOCK_YES,GTK_RESPONSE_YES);//~v68jR~
        gtk_dialog_add_button(GTK_DIALOG(msgdlg),GTK_STOCK_NO,GTK_RESPONSE_NO);//~v68jR~
        gtk_dialog_add_button(GTK_DIALOG(msgdlg),GTK_STOCK_CANCEL,GTK_RESPONSE_CANCEL);//~v685R~
    }                                                              //~v68jI~
    else                                                           //~v68jI~
	if (Pbuttontype==MB_OKCANCEL)                                  //~v68jI~
        gtk_dialog_add_button(GTK_DIALOG(msgdlg),GTK_STOCK_CANCEL,GTK_RESPONSE_CANCEL);//~v68jI~
    rc=gtk_dialog_run(GTK_DIALOG(msgdlg));                         //~v66AI~
    gtk_widget_destroy(msgdlg);                                    //~v66AI~
    if (rc==GTK_RESPONSE_OK||rc==GTK_RESPONSE_YES)                 //~v66AI~
    	return 0;                                                  //~v66AI~
    return rc;                                                     //~v66AI~
}                                                                  //~v66AI~
#endif //ARM                                                       //~v@@@I~
//**************************************************************** //~2907I~
//*rc:ret code                                                     //~2907I~
//**************************************************************** //~2907I~
int uerrmsgbox(char * Pemsg,char * Pjmsg,... )                     //~2907R~
{                                                                  //~2907I~
//  GError *errpos=NULL;                                           //~v79zR~
//  ULONG *parm;                                                   //~2907R~//~va6XR~
	unsigned long parm[UERRMSG_MAXPARM];                           //~va6XR~
	char *pmsg;                                                    //~2907I~
    char *putf8;                                                   //~v67AI~
	int readlen,writelen;                                          //~v67AI~
//********************************                                 //~2907I~
//  parm=(unsigned long*)(void*)((&Pjmsg)+1);                      //~2907I~//~va6XR~
	UGETSTDARG(unsigned long,parm,Pjmsg,UERRMSG_MAXPARM);          //~va6XR~
	pmsg=uerrmsgedit("",Pemsg,Pjmsg,parm);                         //~2907R~
//  putf8=g_locale_to_utf8(pmsg,strlen(pmsg),&readlen,&writelen,&errpos);//~v79zR~
//  PRINTGERR("uerrmsgbox-g_locale_to_utf8",errpos);               //~v79zR~
	csublocale2utf8(0,pmsg,strlen(pmsg),&readlen,&putf8,&writelen);//~v79zI~
//	umsgbox(pmsg);                                                 //~v67AR~
//	umsgbox(putf8);                                                //~v685R~
  	umsgbox(putf8,MB_OK);                                          //~v685I~
    ugeterrmsg();		//clear msg saved                          //~2928I~
//  g_free(putf8);                                                 //~v79zR~
    return 4;                                                      //~2B02R~
}//uerrmsgbox                                                      //~2907R~
//**************************************************************** //~v66AI~
//*rc:ret code                                                     //~v66AI~
//**************************************************************** //~v66AI~
int uerrmsgboxokcan(char * Pemsg,char * Pjmsg,... )                //~v66AI~
{                                                                  //~v66AI~
//  GError *errpos=NULL;                                           //~v79zR~
//  ULONG *parm;                                                   //~v66AI~//~va6XR~
	unsigned long parm[UERRMSG_MAXPARM];                           //~va6XR~
	char *pmsg;                                                    //~v66AI~
    char *putf8;                                                   //~v67AI~
    int rc;                                                        //~v66AI~
	int readlen,writelen;                                          //~v67AI~
//********************************                                 //~v66AI~
//  parm=(unsigned long*)(void*)((&Pjmsg)+1);                      //~v66AI~//~va6XR~
	UGETSTDARG(unsigned long,parm,Pjmsg,UERRMSG_MAXPARM);          //~va6XR~
	pmsg=uerrmsgedit("",Pemsg,Pjmsg,parm);                         //~v66AI~
//  putf8=g_locale_to_utf8(pmsg,strlen(pmsg),&readlen,&writelen,&errpos);//~v79zR~
//  PRINTGERR("uerrmsgboxokscan-g_locale_to_utf8",errpos);         //~v79zR~
	csublocale2utf8(0,pmsg,strlen(pmsg),&readlen,&putf8,&writelen);//~v79zI~
//  rc=umsgbox2(pmsg,GTK_MESSAGE_QUESTION,GTK_BUTTONS_OK_CANCEL);  //~v67AR~
    rc=umsgbox2(putf8,GTK_MESSAGE_QUESTION,GTK_BUTTONS_OK_CANCEL); //~v67AI~
    ugeterrmsg();		//clear msg saved                          //~v66AI~
//  g_free(putf8);                                                 //~v79zR~
    return rc;                                                     //~v66AI~
}//uerrmsgboxokcan                                                 //~v66AI~
//**************************************************************** //~vXXEI~
//*rc:ret code                                                     //~vXXEI~
//**************************************************************** //~vXXEI~
int uactivate_menuitem(char *Pobjectname)                          //~vXXER~
{                                                                  //~vXXEI~
  	GtkWidget *pwd;                                                //~vXXEI~
//********************************                                 //~vXXEI~
  	pwd=lookup_widget(GTK_WIDGET(Gpframe),Pobjectname);            //~vXXER~
	if (!pwd)                                                      //~vXXEI~
    	return 4;                                                  //~vXXEI~
	gtk_menu_item_activate(GTK_MENU_ITEM(pwd));                    //~vXXER~
    return 0;                                                      //~vXXEI~
}//uactivate_menuitem                                              //~vXXER~
//**************************************************************** //~vXXEI~
//*rc:ret code                                                     //~vXXEI~
//**************************************************************** //~vXXEI~
int uclick_toolbarbutton(char *Pobjectname)                        //~vXXEI~
{                                                                  //~vXXEI~
  	GtkWidget *pwd;                                                //~vXXEI~
//********************************                                 //~vXXEI~
  	pwd=lookup_widget(GTK_WIDGET(Gpframe),Pobjectname);            //~vXXEI~
	if (!pwd)                                                      //~vXXEI~
    	return 4;                                                  //~vXXEI~
	gtk_signal_emit_by_name(GTK_OBJECT(pwd),"clicked","popupmenu");//~vXXEI~
    return 0;                                                      //~vXXEI~
}//uclick_toolbarbutton                                            //~vXXEI~
//**************************************************************** //~v68iI~
//*rc:ret code                                                     //~v68iI~
//**************************************************************** //~v68iR~
void usetfocus(void)                                               //~v68iI~
{      	                                                           //~v68iR~
#ifndef ARM                                                        //~v@@@I~
//  GtkWidget *top;                                                //~v68jR~
//********************************                                 //~v68iI~
//dprintf("usetfocus can get focus=%d\n",GTK_WIDGET_CAN_FOCUS(Gpview));//~v68iR~
////  gtk_widget_grab_focus (Gpview);                              //~v68iR~
//dprintf("usetfocus signal=%d\n",GTK_WIDGET_GET_CLASS(Gpview)->activate_signal);//~v68iR~
////  rc=gtk_widget_activate(Gpview);                              //~v68iR~
//dprintf("usetfocus activate rc=%d\n",rc);                        //~v68iR~
//dprintf("usetfocus frame can get focus=%d\n",GTK_WIDGET_CAN_FOCUS(Gpframe));//~v68iR~
////  gtk_widget_grab_focus (Gpframe);                             //~v68iR~
//dprintf("usetfocus drame signal=%d\n",GTK_WIDGET_GET_CLASS(Gpframe)->activate_signal);//~v68iR~
////  rc=gtk_widget_activate(Gpframe);                             //~v68iR~
//dprintf("usetfocus frame activate rc=%d\n",rc);                  //~v68iR~
//    gtk_widget_show(Gpview);                                     //~v68iR~
//    gtk_widget_show(Gpframe);                                    //~v68iR~
//  gdk_window_raise(Gpframe->window);                             //~v68iR~
//  gdk_window_raise(Gpview->window);                              //~v68iI~
//  gtk_window_present(Gpview->window);                            //~v68iI~
//    gtk_window_present(Gpframe->window);                         //~v68iI~
//    rc=gtk_widget_activate(Gpframe);                             //~v68iI~
//    gtk_widget_show(Gpframe);                                    //~v68iI~
//    top=gtk_widget_get_toplevel(Gpframe);                        //~v68iR~
//dprintf("top from frame(%p)=%p\n",Gpframe,top);                  //~v68iR~
//    top=gtk_widget_get_toplevel(Gpview);                         //~v68iR~
//dprintf("top from view(%p)=%p\n",Gpview,top);                    //~v68iR~
//  gtk_window_iconify(GTK_WINDOW(Gpframe));                       //~v68iR~
    gtk_window_present(GTK_WINDOW(Gpframe));                       //~v68iI~
#endif //ARM                                                       //~v@@@I~
    return;                                                        //~v68iR~
}//usetfocus                                                       //~v68iI~
//**************************************************************** //~v685R~
//* use client_event(connect signal by Glade is required)          //~v685I~
//* signal_emit,gtk_main_do_event,gtk_widget_event is all syncronouse//~v685R~
//* (but dialog msg allow schedule other msg)                      //~v685R~
//* gdk_event_put is asynchronouse                                 //~v685I~
//**************************************************************** //~v685R~
int upostmsg(UINT Pmsg,ULONG Pwparm,ULONG Plparm)                  //~v685R~
{                                                                  //~v685R~
    GdkEventClient send_event; //gdk_event_put enq copyed event    //~v685R~
    GdkAtom atom=GDK_NONE;                                         //~v685R~
//  BOOL retval=FALSE;	//used when emit                           //~v685R~
    int rc=0;                                                      //~v685I~
    GdkWindow *window;                                             //~v685I~
//********************************                                 //~v685R~
    window=Gpview->window;                                         //~v685I~
//  send_event = (GdkEventClient*)gdk_event_new (GDK_CLIENT_EVENT);//~v685R~
    send_event.type=GDK_CLIENT_EVENT;                              //~v685I~
    send_event.window=window;                                      //~v685R~
    send_event.send_event=FALSE;  //?                              //~v685R~
    send_event.message_type = atom;                                //~v685R~
    send_event.data_format = 32;   //parm data is LONG[4]          //~v685R~
    send_event.data.l[0] = (ULONG)Gpview; //uniq id to chk msg is to me//~v685R~
    send_event.data.l[1] = Pmsg;                                   //~v685R~
    send_event.data.l[2] = Pwparm;                                 //~v685R~
    send_event.data.l[3] = Plparm;                                 //~v685R~
    dprintf("client_event emit start event=%p\n",&send_event);     //~v685R~
//  g_signal_emit_by_name(GTK_OBJECT(widget),"client_event",send_event,&retval);//~v685R~
//  retval=gtk_widget_event(widget,send_event);                    //~v685R~
//  gtk_main_do_event(send_event);                                 //~v685R~
//  gdk_event_send_client_message_for_display (gtk_widget_get_display (widget), send_event, xid);//~v685R~
    gdk_event_put((GdkEvent*)(&send_event));  //put on que copy of event//~v685R~
    dprintf("client_event send end\n");                            //~v685R~
    dprintf("upostmsg event=%p,parm=%lx,%lx\n",&send_event,Pwparm,Plparm);//~v685R~
//  gdk_event_free((GdkEvent*)send_event);	//cause abend even by gdk_event_new//~v685R~
    return rc;                                                     //~v685R~
}//upostmsg                                                        //~v685R~
//**************************************************************** //~v55ZI~
//setup timer callback                                             //~vXXEI~
//interval by miliseconds                                          //~vXXEI~
//*rc:ret timer handler id for killtimer                           //~vXXER~
//**************************************************************** //~v55ZI~
UINT usettimer(UINT Ptimerid,int Pintvlms,void *Pcallback)         //~vXXER~
{                                                                  //~v55ZI~
    UINT timerhandlerid;                                           //~vXXER~
//********************************                                 //~v55ZI~
    dprintf("usettimer id=%x,mills=%d,cb=%p\n",Ptimerid,Pintvlms,Pcallback);//~v@@@R~
#ifndef ARM                                                        //~v@@@I~
	if (Pcallback)                                                 //~vXXEI~
//  	timerhandlerid=gtk_timeout_add(Pintvlms,Pcallback,(void*)Ptimerid);//~vXXER~//~va70R~
    	timerhandlerid=gtk_timeout_add(Pintvlms,Pcallback,(void*)(ULONG)Ptimerid);//~va70I~
    else                                                           //~vXXEI~
#endif                                                             //~v@@@I~
//  	timerhandlerid=gtk_timeout_add(Pintvlms,xxemain_ontimer,(void*)Ptimerid);//~vXXER~//~va70R~
  		timerhandlerid=gtk_timeout_add(Pintvlms,xxemain_ontimer,(void*)(ULONG)Ptimerid);//~va70I~
    return timerhandlerid;                                         //~vXXER~
}//usettimer                                                       //~v55ZI~
//**************************************************************** //~vXXER~
//*rc:ret code                                                     //~vXXER~
//**************************************************************** //~vXXER~
int ukilltimer(UINT Ptimerid)                                      //~vXXER~
{                                                                  //~vXXER~
    gtk_timeout_remove(Ptimerid);                                  //~vXXER~
    return 0;                                                      //~vXXEI~
}//ukilltimer                                                      //~vXXER~
//**************************************************************** //~v55WI~
//*invalidate rect(emit expose event,write pixbuff to window)      //~vXXER~
//*rc:ret code                                                     //~v55WI~
//**************************************************************** //~v55WI~
int uinvalidate(int Popt)                                          //~vXXER~
{                                                                  //~v55WI~
    int hh,ww;                                                     //~vXXEI~
//********************************                                 //~v55WI~
    dprintf("uinvalidate entry\n");                                //~v@@@I~
    ww=Gpview->allocation.width;                                   //~vXXEI~
    hh=Gpview->allocation.height;                                  //~vXXEI~
    if (Popt)	//direct draw                                      //~vXXEI~
		gdk_draw_drawable(Gpview->window,                          //~vXXEI~
                      Gpview->style->fg_gc[GTK_WIDGET_STATE(Gpview)],//~vXXEI~
                      Gppixmap,                                    //~vXXEI~
                      0,0,0,0,ww,hh);                              //~vXXEI~
    else                                                           //~vXXEI~
#ifdef AAA                                                         //~v69VI~
	    gtk_widget_queue_draw_area(Gpview,0,0,ww,hh);              //~vXXER~
#else                                                              //~v69VI~
		gdk_draw_drawable(Gpview->window,                          //~v69VI~
                      Gpview->style->fg_gc[GTK_WIDGET_STATE(Gpview)],//~v69VI~
                      Gppixmap,                                    //~v69VI~
                      0,0,0,0,ww,hh);                              //~v69VI~
#endif                                                             //~v69VI~
    dprintf("uinvalidate return\n");                               //~v@@@R~
    return 0;                                                      //~vXXER~
}//ureqredraw                                                      //~v55WI~
//===============================================================================//~2907I~
//get ini file on work dir                                         //~2907I~
//===============================================================================//~2907I~
int ugetinidata(PWXEINIDATA Ppwxeini)                              //~2907I~
{                                                                  //~2907I~
	int rc=0,len;                                                  //~2907R~
	char fnm[_MAX_PATH];                                           //~2928I~
    WXEINIDATA wei;                                                //~2907I~
    FILE *fh;                                                      //~2907I~
//************************************                             //~2907I~
    sprintf(fnm,"%s%s",Gworkdir,INI_FILENAME);                     //~vXXER~
    memset(Ppwxeini,0,sizeof(WXEINIDATA));                         //~2907I~
	fh=fopen(fnm,"rb");                                            //~2907I~
    if (!fh)                                                       //~2907I~
		return 1;                                                  //~2907I~
    rc=iniacrnmchk(fh);                                            //~2907I~
    if (!rc)                                                       //~2907I~
    {                                                              //~2907I~
		len=fread(&wei,1,sizeof(wei),fh);                          //~2907R~
    	if (len!=sizeof(wei))                                      //~2907I~
        	rc=4;                                                  //~2907I~
//        if (wei.WXEINIver!=WXE_VER)                              //~vXXER~
//        {                                                        //~vXXER~
//            uerrmsgbox("Old Version ini file(%s);Delete then restart.",0,//~vXXER~
//                        fnm);                                    //~vXXER~
//            rc=2;                                                //~vXXER~
//        }                                                        //~vXXER~
    }                                                              //~2907I~
    if (!rc)                                                       //~2907I~
    	rc=iniacrnmchk(fh);                                        //~2907I~
    if (rc)                                                        //~vXXER~
    	uerrmsgbox("Invalid format ini file(%s), It may be of old version.",0,//~vXXER~
					fnm);                                          //~2907R~
    if (!rc)                                                       //~2907I~
    	memcpy(Ppwxeini,&wei,sizeof(WXEINIDATA));                  //~2907R~
    fclose(fh);                                                    //~2907I~
    return rc;                                                     //~2907I~
}//ugetinidata                                                     //~2907I~
//===============================================================================//~2907I~
//put ini file to work dir                                         //~2907I~
//===============================================================================//~2907I~
int uputinidata(PWXEINIDATA Ppwxeini)                              //~2907I~
{                                                                  //~2907I~
	int rc=0,len;                                                  //~2907R~
	char fnm[_MAX_PATH];                                           //~2907R~
    FILE *fh;                                                      //~2907I~
//************************************                             //~2907I~
    sprintf(fnm,"%s%s",Gworkdir,INI_FILENAME);                     //~vXXER~
	fh=fopen(fnm,"wb");                                            //~2907I~
    if (!fh)                                                       //~2907I~
    {                                                              //~2907I~
    	uerrmsgbox("ini file \"wb\" open failed(%s)",0,            //~2928R~
					fnm);                                          //~2928R~
		return 1;                                                  //~2907I~
    }                                                              //~2907I~
    rc=iniacrnmput(fh);                                            //~2907I~
    if (!rc)                                                       //~2907I~
    {                                                              //~2907I~
		len=fwrite(Ppwxeini,1,sizeof(WXEINIDATA),fh);              //~2907R~
    	if (len!=sizeof(WXEINIDATA))                               //~2907I~
        	rc=4;                                                  //~2907I~
    }                                                              //~2907I~
    if (!rc)                                                       //~2907I~
    	rc=iniacrnmput(fh);                                        //~2907I~
    if (rc)                                                        //~2907I~
    	uerrmsgbox("ini file write failed(%s)",0,                  //~2907I~
					fnm);                                          //~2907R~
    fclose(fh);                                                    //~2907I~
    return rc;                                                     //~2907I~
}//uputinidata                                                     //~2907I~
//===============================================================================//~2907I~
//ini file acronym chk                                             //~2907I~
//===============================================================================//~2907I~
int  iniacrnmchk(FILE *Pfh)                                        //~2907I~
{                                                                  //~2907I~
	int rc=0,len;                                                  //~2907I~
	char *inidata[256];                                            //~2907I~
//************************************                             //~2907I~
	len=fread(inidata,1,sizeof(INI_ACRNM),Pfh);                    //~2907I~
    if (len!=sizeof(INI_ACRNM))                                    //~2907I~
    	rc=4;                                                      //~2907I~
    if (!rc)                                                       //~2907I~
    	if (memcmp(inidata,INI_ACRNM,sizeof(INI_ACRNM)))           //~2907R~
        	rc=4;                                                  //~2907I~
    return rc;                                                     //~2907I~
}//iniacrnmchk                                                     //~2907I~
//===============================================================================//~2907I~
//ini file acronym put                                             //~2907I~
//===============================================================================//~2907I~
int  iniacrnmput(FILE *Pfh)                                        //~2907I~
{                                                                  //~2907I~
	int rc=0,len;                                                  //~2907I~
//************************************                             //~2907I~
	len=fwrite(INI_ACRNM,1,sizeof(INI_ACRNM),Pfh);                 //~2907R~
    if (len!=sizeof(INI_ACRNM))                                    //~2907I~
    	rc=4;                                                      //~2907I~
    return rc;                                                     //~2907I~
}//iniacrnmput                                                     //~2907I~
//*************************************************************    //~2922I~
//*get current path                                                //~2922I~
//*rc:1:path changed after prev                                    //~2922I~
//*************************************************************    //~2922I~
int  ugetcpath(char *Ppath)                                        //~2922I~
{                                                                  //~2922I~
	int rc;                                                        //~2922I~
static 	char Scpath[_MAX_PATH];                                    //~2922I~
       	char cwd[_MAX_PATH];                                       //~vc1iI~
//***********************                                          //~2922I~
//  ugetcwd(Ppath);                                                //~2922I~//~vc1iR~
    ugetcwd(cwd);                                                  //~vc1iI~
    ufullpathAltSD(0,cwd,Ppath);                                   //~vc1iI~
    if (strcmp(Ppath,Scpath))                                      //~2922I~
    {                                                              //~2922I~
    	strcpy(Scpath,Ppath);                                      //~2922I~
    	rc=1;                                                      //~2922I~
    }                                                              //~2922I~
    else                                                           //~2922I~
    	rc=0;                                                      //~2922I~
    return rc;                                                     //~2922I~
}//ugetcpath                                                       //~2922I~
//*************************************************************    //~2923I~
//*get tempfilename on workdir                                     //~2923I~
//*option:1 use Gworkdir if avail                                  //~2A12I~
//*************************************************************    //~2923I~
int ugettempfname(int Popt,char *Ptempnam)                         //~2A12R~
{                                                                  //~2923I~
	int rc;                                                        //~2929R~
//static int Stmpdirsw=0;                                          //~vXXER~
//***********************                                          //~2923I~
    if (Popt==1 && *Gworkdir)  //xe called once                    //~2A12R~
    	strcpy(Ptempnam,Gworkdir);                                 //~2A12R~
    else                                                           //~2A12I~
    {                                                              //~2A12I~
		usetupwdtemp();                                            //~2A12I~
    	strcpy(Ptempnam,Swdtemp);                                  //~2A12I~
    }                                                              //~2A12I~
    rc=utempnam(Ptempnam,"nf_",Ptempnam)!=0;     //dir,prefix,outarea//~2929R~
    return rc;                                                     //~2923I~
}//ugettempfname                                                   //~2923I~
#ifndef XXE                                                        //~vXXEM~
//*************************************************************    //~2A12M~
//*allocate dummy file for the case no filename parm in cmdline    //~2A12M~
//*  to avoid filename prompt at "save file"                       //~2A12M~
//*************************************************************    //~2A12M~
void uallocdummynewfile(char *Pfnm)                                //~2A12M~
{                                                                  //~2A12M~
	FILE *fh;                                                      //~2A12M~
//****************************                                     //~2A12M~
	ugettempfname(0,Pfnm); 		//temp on wxe work                 //~2A12M~
    strcpy(Pfnm+PATHLEN(Pfnm),"tmpfile");                          //~2A12M~
	if (ufstat(Pfnm,0))	//not found                                //~2A12M~
    {                                                              //~2A12M~
		fh=fopen(Pfnm,"w");                                        //~2A12M~
		fclose(fh);                                                //~2A12M~
    }                                                              //~2A12M~
    return;                                                        //~2A12M~
}//uallocdummynewfile                                              //~2A12M~
////===============================================================================//~2B30R~
////get ini file on work dir                                       //~2B30R~
////===============================================================================//~2B30R~
//int usetupwd(void)                                               //~2B30R~
//{                                                                //~2B30R~
//    char *fnm;                                                   //~2B30R~
//static int Ssetupwd=0;                                           //~2B30R~
////************************************                           //~2B30R~
//    if (!Ssetupwd)                                               //~2B30R~
//    {                                                            //~2B30R~
//        Ssetupwd=1;                                              //~2B30R~
//        fnm=WXE_WKDIR;                                           //~2B30R~
//        if (ufstat(fnm,0))  //not found                          //~2B30R~
//        {                                                        //~2B30R~
//            if (!umkdir(fnm))                                    //~2B30R~
//                uerrmsgbox("WorkDir %s is for %s now created",0, //~2B30R~
//                        fnm,WXE_PGMNAME);                        //~2B30R~
//            else                                                 //~2B30R~
//                uerrmsgbox("WorkDir %s for %s created",0,        //~2B30R~
//                        fnm,WXE_PGMNAME);                        //~2B30R~
//        }                                                        //~2B30R~
//    }                                                            //~2B30R~
//    return 0;                                                    //~2B30R~
//}//usetupwd                                                      //~2B30R~
#endif                                                             //~vXXEI~
//*************************************************************    //~2A12I~
//*get tempfilename on workdir                                     //~2A12I~
//*option:1 use Gworkdir if avail                                  //~2A12I~
//*************************************************************    //~2A12I~
int usetupwdtemp(void)                                             //~2A12I~
{                                                                  //~2A12I~
static int Ssetupwdtemp=0;                                         //~2A12I~
	int rc=0;                                                      //~2B30I~
//***********************                                          //~2A12I~
	if (!Ssetupwdtemp)                                             //~2A12I~
    {                                                              //~2A12I~
		Ssetupwdtemp=1;                                            //~2A12I~
		strcpy(Swdtemp,Gworkdir);                                  //~2B30I~
		strcat(Swdtemp,WXE_TEMPDIR);                               //~2B30I~
        if (ufstat(Swdtemp,0)) //not found                         //~2A12I~
        	rc=umkdir(Swdtemp);                                    //~2B30R~
    }                                                              //~2A12I~
    return rc;                                                     //~2B30R~
}//usetupwdtemp                                                    //~2A12I~
//*************************************************************    //~2929I~
//*intf to umalloc                                                 //~2929I~
//*************************************************************    //~2929I~
void *umemalloc(int Plen)                                          //~2929I~
{                                                                  //~2929I~
	return umalloc(Plen);	                                       //~2929I~
}//umemalloc                                                       //~2929I~
//*************************************************************    //~2929I~
//*intf to ufree                                                   //~2929I~
//*************************************************************    //~2929I~
void umemfree(void *Paddr)                                         //~2929I~
{                                                                  //~2929I~
	ufree(Paddr);                                                  //~2929I~
    return;                                                        //~2929I~
}//umemfree                                                        //~2929I~
//*************************************************************    //~v53tI~
//*intf to malloc                                                  //~v53tI~
//*************************************************************    //~v53tI~
void *wxemalloc(int Plen)                                          //~v53tI~
{                                                                  //~v53tI~
	return malloc(Plen);                                           //~v53tI~
}//wxemalloc                                                       //~v53tI~
//*************************************************************    //~v53tI~
//*intf to free                                                    //~v53tI~
//*************************************************************    //~v53tI~
void wxefree(void *Paddr)                                          //~v53tI~
{                                                                  //~v53tI~
	free(Paddr);                                                   //~v53tI~
    return;                                                        //~v53tI~
}//wxefree                                                         //~v53tI~
//*************************************************************    //~v69rI~
//*print api err message                                           //~v69rI~
//*************************************************************    //~v69rI~
void csubprintgerror(char *Pfile,int Pline,char *Pcmt,GError *Ppgerr)//~v69rR~
{                                                                  //~v69rI~
#ifndef ARM                                                        //~v@@@I~
    if (Ppgerr==NULL)                                              //~v69rR~
    	return;                                                    //~v69rI~
	dprintf("%s %s:%d %s: %s\n",(char*)utimeedit("HHMMSS.MIL",0),Pfile,Pline,Pcmt,Ppgerr->message);//~v69rR~
    g_error_free(Ppgerr);                                          //~v69rI~
#else                                                              //~v@@@I~
	LOGPE("%s %s:%d %s:",(char*)utimeedit("HHMMSS.MIL",0),Pfile,Pline,Pcmt);//~v@@@I~
#endif //ARM                                                       //~v@@@I~
}//csubprintgerror                                                 //~v69rI~
//*************************************************************    //~v69sI~
//*locale to utf8 conversion with null replacing to space          //~v69sI~
//*************************************************************    //~v69sI~
//*************************************************************    //~v69QI~
//*glib/convert.c                                                  //~v69QI~
//*************************************************************    //~v69QI~
//gchar *                                                          //~v69QI~
//g_locale_to_utf8 (const gchar  *opsysstring,                     //~v69QI~
//          gssize        len,                                     //~v69QI~
//          gsize        *bytes_read,                              //~v69QI~
//          gsize        *bytes_written,                           //~v69QI~
//          GError      **error)                                   //~v69QI~
//{                                                                //~v69QI~
//  const char *charset;                                           //~v69QI~
//  if (g_get_charset (&charset))                                  //~v69QI~
//    return strdup_len (opsysstring, len, bytes_read, bytes_written, error);//~v69QI~
//  else                                                           //~v69QI~
//    return g_convert (opsysstring, len,                          //~v69QI~
//              "UTF-8", charset, bytes_read, bytes_written, error);//~v69QI~
//}                                                                //~v69QI~
//gchar*                                                           //~v69QI~
//g_convert (const gchar *str,                                     //~v69QI~
//           gssize       len,                                     //~v69QI~
//           const gchar *to_codeset,                              //~v69QI~
//           const gchar *from_codeset,                            //~v69QI~
//           gsize       *bytes_read,                              //~v69QI~
//       gsize       *bytes_written,                               //~v69QI~
//       GError     **error)                                       //~v69QI~
//{                                                                //~v69QI~
//  gchar *res;                                                    //~v69QI~
//  GIConv cd;                                                     //~v69QI~
//  g_return_val_if_fail (str != NULL, NULL);                      //~v69QI~
//  g_return_val_if_fail (to_codeset != NULL, NULL);               //~v69QI~
//  g_return_val_if_fail (from_codeset != NULL, NULL);             //~v69QI~
//  cd = open_converter (to_codeset, from_codeset, error);         //~v69QI~
//  if (cd == (GIConv) -1)                                         //~v69QI~
//    {                                                            //~v69QI~
//      if (bytes_read)                                            //~v69QI~
//        *bytes_read = 0;                                         //~v69QI~
//      if (bytes_written)                                         //~v69QI~
//        *bytes_written = 0;                                      //~v69QI~
//      return NULL;                                               //~v69QI~
//    }                                                            //~v69QI~
//  res = g_convert_with_iconv (str, len, cd,                      //~v69QI~
//                  bytes_read, bytes_written,                     //~v69QI~
//                  error);                                        //~v69QI~
//  close_converter (cd);                                          //~v69QI~
//  return res;                                                    //~v69QI~
//}                                                                //~v69QI~
//static GIConv                                                    //~v69QI~
//open_converter (const gchar *to_codeset,                         //~v69QI~
//        const gchar *from_codeset,                               //~v69QI~
//        GError     **error)                                      //~v69QI~
//{                                                                //~v69QI~
//  struct _iconv_cache_bucket *bucket;                            //~v69QI~
//  gchar *key;                                                    //~v69QI~
//  GIConv cd;                                                     //~v69QI~
//  /* create our key */                                           //~v69QI~
//  key = g_alloca (strlen (from_codeset) + strlen (to_codeset) + 2);//~v69QI~
//  _g_sprintf (key, "%s:%s", from_codeset, to_codeset);           //~v69QI~
//  G_LOCK (iconv_cache_lock);                                     //~v69QI~
//  /* make sure the cache has been initialized */                 //~v69QI~
//  iconv_cache_init ();                                           //~v69QI~
//  bucket = g_hash_table_lookup (iconv_cache, key);               //~v69QI~
//  if (bucket)                                                    //~v69QI~
//    {                                                            //~v69QI~
//      if (bucket->used)                                          //~v69QI~
//        {                                                        //~v69QI~
//          cd = g_iconv_open (to_codeset, from_codeset);          //~v69QI~
//          if (cd == (GIConv) -1)                                 //~v69QI~
//            goto error;                                          //~v69QI~
//        }                                                        //~v69QI~
//      else                                                       //~v69QI~
//        {                                                        //~v69QI~
//      /* Apparently iconv on Solaris <= 7 segfaults if you pass in//~v69QI~
//       * NULL for anything but inbuf; work around that. (NULL outbuf//~v69QI~
//       * or NULL *outbuf is allowed by Unix98.)                  //~v69QI~
//       */                                                        //~v69QI~
//      gsize inbytes_left = 0;                                    //~v69QI~
//      gchar *outbuf = NULL;                                      //~v69QI~
//      gsize outbytes_left = 0;                                   //~v69QI~
//          cd = bucket->cd;                                       //~v69QI~
//          bucket->used = TRUE;                                   //~v69QI~
//          /* reset the descriptor */                             //~v69QI~
//          g_iconv (cd, NULL, &inbytes_left, &outbuf, &outbytes_left);//~v69QI~
//        }                                                        //~v69QI~
//      bucket->refcount++;                                        //~v69QI~
//    }                                                            //~v69QI~
//  else                                                           //~v69QI~
//    {                                                            //~v69QI~
//      cd = g_iconv_open (to_codeset, from_codeset);              //~v69QI~
//      if (cd == (GIConv) -1)                                     //~v69QI~
//        goto error;                                              //~v69QI~
//      iconv_cache_expire_unused ();                              //~v69QI~
//      bucket = iconv_cache_bucket_new (key, cd);                 //~v69QI~
//    }                                                            //~v69QI~
//  g_hash_table_insert (iconv_open_hash, cd, bucket->key);        //~v69QI~
//  G_UNLOCK (iconv_cache_lock);                                   //~v69QI~
//  return cd;                                                     //~v69QI~
// error:                                                          //~v69QI~
//  G_UNLOCK (iconv_cache_lock);                                   //~v69QI~
//  /* Something went wrong.  */                                   //~v69QI~
//  if (error)                                                     //~v69QI~
//    {                                                            //~v69QI~
//      if (errno == EINVAL)                                       //~v69QI~
//    g_set_error (error, G_CONVERT_ERROR, G_CONVERT_ERROR_NO_CONVERSION,//~v69QI~
//             _("Conversion from character set '%s' to '%s' is not supported"),//~v69QI~
//             from_codeset, to_codeset);                          //~v69QI~
//      else                                                       //~v69QI~
//    g_set_error (error, G_CONVERT_ERROR, G_CONVERT_ERROR_FAILED, //~v69QI~
//             _("Could not open converter from '%s' to '%s'"),    //~v69QI~
//             from_codeset, to_codeset);                          //~v69QI~
//    }                                                            //~v69QI~
//  return cd;                                                     //~v69QI~
//}                                                                //~v69QI~
//gchar*                                                           //~v69QI~
//g_convert_with_iconv (const gchar *str,                          //~v69QI~
//              gssize       len,                                  //~v69QI~
//              GIConv       converter,                            //~v69QI~
//              gsize       *bytes_read,                           //~v69QI~
//              gsize       *bytes_written,                        //~v69QI~
//              GError     **error)                                //~v69QI~
//{                                                                //~v69QI~
//  gchar *dest;                                                   //~v69QI~
//  gchar *outp;                                                   //~v69QI~
//  const gchar *p;                                                //~v69QI~
//  const gchar *shift_p = NULL;                                   //~v69QI~
//  gsize inbytes_remaining;                                       //~v69QI~
//  gsize outbytes_remaining;                                      //~v69QI~
//  gsize err;                                                     //~v69QI~
//  gsize outbuf_size;                                             //~v69QI~
//  gboolean have_error = FALSE;                                   //~v69QI~
//  gboolean done = FALSE;                                         //~v69QI~
//  g_return_val_if_fail (converter != (GIConv) -1, NULL);         //~v69QI~
//  if (len < 0)                                                   //~v69QI~
//    len = strlen (str);                                          //~v69QI~
//  p = str;                                                       //~v69QI~
//  inbytes_remaining = len;                                       //~v69QI~
//  outbuf_size = len + 1; /* + 1 for nul in case len == 1 */      //~v69QI~
//  outbytes_remaining = outbuf_size - 1; /* -1 for nul */         //~v69QI~
//  outp = dest = g_malloc (outbuf_size);                          //~v69QI~
//  while (!done && !have_error)                                   //~v69QI~
//    {                                                            //~v69QI~
//      err = g_iconv (converter, (char **)&p, &inbytes_remaining, &outp, &outbytes_remaining);//~v69QI~
//      if (err == (size_t) -1)                                    //~v69QI~
//    {                                                            //~v69QI~
//      switch (errno)                                             //~v69QI~
//        {                                                        //~v69QI~
//        case EINVAL:                                             //~v69QI~
//          /* Incomplete text, do not report an error */          //~v69QI~
//          done = TRUE;                                           //~v69QI~
//          break;                                                 //~v69QI~
//        case E2BIG:                                              //~v69QI~
//          {                                                      //~v69QI~
//        size_t used = outp - dest;                               //~v69QI~
//        outbuf_size *= 2;                                        //~v69QI~
//        dest = g_realloc (dest, outbuf_size);                    //~v69QI~
//        outp = dest + used;                                      //~v69QI~
//        outbytes_remaining = outbuf_size - used - 1; /* -1 for nul *///~v69QI~
//          }                                                      //~v69QI~
//          break;                                                 //~v69QI~
//        case EILSEQ:                                             //~v69QI~
//          if (error)                                             //~v69QI~
//        g_set_error (error, G_CONVERT_ERROR, G_CONVERT_ERROR_ILLEGAL_SEQUENCE,//~v69QI~
//                 _("Invalid byte sequence in conversion input"));//~v69QI~
//          have_error = TRUE;                                     //~v69QI~
//          break;                                                 //~v69QI~
//        default:                                                 //~v69QI~
//          if (error)                                             //~v69QI~
//        g_set_error (error, G_CONVERT_ERROR, G_CONVERT_ERROR_FAILED,//~v69QI~
//                 _("Error during conversion: %s"),               //~v69QI~
//                 g_strerror (errno));                            //~v69QI~
//          have_error = TRUE;                                     //~v69QI~
//          break;                                                 //~v69QI~
//        }                                                        //~v69QI~
//    }                                                            //~v69QI~
//      else                                                       //~v69QI~
//    {                                                            //~v69QI~
//      if (!shift_p)                                              //~v69QI~
//        {                                                        //~v69QI~
//          /* call g_iconv with NULL inbuf to cleanup shift state *///~v69QI~
//          shift_p = p;                                           //~v69QI~
//          p = NULL;                                              //~v69QI~
//          inbytes_remaining = 0;                                 //~v69QI~
//        }                                                        //~v69QI~
//      else                                                       //~v69QI~
//        done = TRUE;                                             //~v69QI~
//    }                                                            //~v69QI~
//    }                                                            //~v69QI~
//  if (shift_p)                                                   //~v69QI~
//    p = shift_p;                                                 //~v69QI~
//  *outp = '\0';                                                  //~v69QI~
//  if (bytes_read)                                                //~v69QI~
//    *bytes_read = p - str;                                       //~v69QI~
//  else                                                           //~v69QI~
//    {                                                            //~v69QI~
//      if ((p - str) != len)                                      //~v69QI~
//    {                                                            //~v69QI~
//          if (!have_error)                                       //~v69QI~
//            {                                                    //~v69QI~
//          if (error)                                             //~v69QI~
//        g_set_error (error, G_CONVERT_ERROR, G_CONVERT_ERROR_PARTIAL_INPUT,//~v69QI~
//                 _("Partial character sequence at end of input"));//~v69QI~
//              have_error = TRUE;                                 //~v69QI~
//            }                                                    //~v69QI~
//    }                                                            //~v69QI~
//    }                                                            //~v69QI~
//  if (bytes_written)                                             //~v69QI~
//    *bytes_written = outp - dest;   /* Doesn't include '\0' */   //~v69QI~
//  if (have_error)                                                //~v69QI~
//    {                                                            //~v69QI~
//      g_free (dest);                                             //~v69QI~
//      return NULL;                                               //~v69QI~
//    }                                                            //~v69QI~
//  else                                                           //~v69QI~
//    return dest;                                                 //~v69QI~
//}                                                                //~v69QI~
//char *csublocale2utf8(char *Ptext,int Plen,int *Ppreadlen,int *Ppwritelen)//~v69VR~
//************************************************************     //~v69VI~
//*rc:4 err,1:ascii(0x00-0x7f) only(don't free utf8 buff)          //~vax5I~
//*out utf8 is strZ                                                //~vax5I~
//************************************************************     //~v69VI~
//int csublocale2utf8(char *Ptext,int Plen,int *Ppreadlen,char **Pputf8,int *Ppwritelen)//~v79zR~
int csublocale2utf8(int Popt,char *Ptext,int Plen,int *Ppreadlen,char **Pputf8,int *Ppwritelen)//~v79zI~
{                                                                  //~v69sI~
//#define INVALIDCH '?'                                              //~v69XI~//~v79YR~
#define INVALIDCH '.'                                              //~v79YI~
//#define MAXUTF8CHSZ 6                                            //~va1cR~
static char *Sbuff=0;                                              //~v69sI~
static int   Sbufflen=0;                                           //~v69sI~
    gchar *putf8;                                                  //~v69XI~
  	UCHAR *pc,*pc2,*pcout,*plinech;                                //~va1cR~
    const gchar *pcinvalid;                                        //~va1cR~
//  int readlen,writelen,linechlen;                                //~v7axR~
    gsize readlen,writelen;                                        //~v7axI~
    int linechlen;                                                 //~v7axI~
    GError *perr;/*=NULL*/                                         //~v706R~
    int len,reslen,ch,utflen;                                      //~v69XR~
    int envutf8sw;                                                 //~v79zI~
#ifndef UTF8SUPPH                                                  //~v@@@I~
    int eucjsw,ss3sw;                                              //~v7adI~
#endif                                                             //~v@@@I~
//****************************                                     //~v69sI~
UTRACED("locale2utf8 in",Ptext,Plen);                              //~v69XI~
//  if (Sisutf8)    //current is not utf8                          //~v69VR~
//  	return Ptext;	//no need to convert;do not gfree if Ptext returned//~v69VR~
//if (!Sisutf8)    //current is not utf8                           //~v79zR~
  envutf8sw=(Popt & CSL2UO_ENCENV) && (Gwxestat & GWXES_ENVISUTF8);//~v79zR~
#ifndef UTF8SUPPH                                                  //~v@@@I~
  eucjsw=!envutf8sw && UDBCSCHK_ISEUCJ();                          //~v7adI~
#endif                                                             //~v@@@I~
  if (!envutf8sw)    //current is not utf8                         //~v79zI~
  {                                                                //~v69VI~
    if (Sconverterdescriptor==(GIConv)-1) //no converter           //~v69QR~
//  	return Ptext;	//no conversion                            //~v69VR~
        ;                                                          //~v69VI~
    else             	//no conversion                            //~v69VI~
    if (Sconverterdescriptor==0)                                   //~v69QI~
        csubutf8init();                                            //~v69QI~
  }                                                                //~v69VI~
	if (Plen>Sbufflen)                                             //~v69sI~
    {                                                              //~v69sI~
    	if (Sbuff)                                                 //~v69sI~
        	free(Sbuff);                                           //~v69sR~
        Sbufflen=0;                                                //~v69sI~
//      Sbuff=malloc(Plen);                                        //~v69VR~
//      Sbuff=malloc(Plen+1);                                      //~v69XR~
//      Sbuff=malloc(Plen*MAXUTF8CHSZ+1);	//6:max utfcode size   //~va1cR~
        Sbuff=malloc(Plen*UTF8_MAXCHARSZMAX+1);	//6:max utfcode size//~va1cR~
        if (Sbuff)                                                 //~v69sI~
	        Sbufflen=Plen;                                         //~v69sI~
    }                                                              //~v69sI~
//  ptext=Ptext;                                                   //~v69VR~
    if (!Sbuff)                                                    //~v69VR~
    	return 4;                                                  //~v69VI~
//  if (Sbuff)                                                     //~v69VI~
//  {                                                              //~v69VI~
//        if (pc=memchr(Ptext,0,(UINT)Plen),pc)                    //~v69VR~
//        {                                                        //~v69VR~
//            len=(int)((ULONG)pc-(ULONG)Ptext);                   //~v69VR~
//            memcpy(Sbuff,Ptext,(UINT)len);                       //~v69VR~
//            for (pce=Ptext+Plen,pc2=Sbuff+len;pc<pce;pc++)       //~v69VR~
//            {                                                    //~v69VR~
//                if (!*pc)                                        //~v69VR~
//                    *pc2++=' ';                                  //~v69VR~
//                else                                             //~v69VR~
//                    *pc2++=*pc;                                  //~v69VR~
//            }                                                    //~v69VR~
//            ptext=Sbuff;                                         //~v69VR~
//        }                                                        //~v69VR~
//    for (ii=Plen,pc=Ptext,pc2=Sbuff;ii>0;ii--)                   //~v69XR~
//    {                                                            //~v69XR~
//        ch=*pc++;                                                //~v69XR~
//        if (!ch)                                                 //~v69XR~
//            *pc2++=' ';                                          //~v69XR~
//        else                                                     //~v69XR~
//        {                                                        //~v69XR~
//            *pc2++=ch;                                           //~v69XR~
//            if (ch>=0x80)                                        //~v69XR~
//                noascii=1;                                       //~v69XR~
//        }                                                        //~v69XR~
//    }                                                            //~v69XR~
	for (pc=Ptext,pcout=Sbuff,reslen=Plen;reslen>0;)               //~v69XI~
    {                                                              //~v69XI~
        ch=*pc;                                                    //~v69XI~
        if (!ch)                                                   //~v69XI~
        {                                                          //~v69XI~
          if (Popt & CSL2UO_NULLALT)//0x08    //rep null by '.' for gtkprint//~vax5I~
            *pcout++='.';                                          //~vax5I~
          else                                                     //~vax5I~
            *pcout++=' ';                                          //~v69XI~
            pc++;                                                  //~v69XI~
            reslen--;                                              //~v69XI~
            continue;                                              //~v69XI~
        }                                                          //~v69XI~
        if (ch<0x20)    //ctlch                                    //~v69XI~
        {                                                          //~v69XI~
        	if ((plinech=Sacsmap[(UINT)(*pc)]))	//line drawing char defined//~v69XR~
            {                                                      //~v69XI~
//              linechlen=strlen(plinech);                         //~v705R~
                linechlen=Sacslentb[(int)(*pc)];                   //~v705R~
                memcpy(pcout,plinech,linechlen);                   //~v69XI~
                pcout+=linechlen;                                  //~v69XI~
            }                                                      //~v69XI~
            else                                                   //~v69XI~
            if ((ch==0x0a||ch==0x0d) && (Popt & CSL2UO_REP0a0d))   //~v79QI~
                *pcout++=INVALIDCH;	//current gtk version erase 0a...0d combination//~v79QI~
            else                                                   //~v79QI~
            	*pcout++=ch;	//no conversion                    //~v69XI~
            pc++;                                                  //~v69XI~
            reslen--;                                              //~v69XI~
            continue;                                              //~v69XI~
        }//linech defined                                          //~v69XI~
        if (ch<0x80)    //ascii;                                   //~v69XI~
        {                                                          //~v69XI~
            *pcout++=ch;                                           //~v69XI~
            pc++;                                                  //~v69XI~
            reslen--;                                              //~v69XI~
            continue;                                              //~v69XI~
        }                                                          //~v69XI~
//**  >=0x80                                                       //~v69XI~
#ifndef UTF8SUPPH                                                  //~v@@@I~
        ss3sw=0;                                                   //~v7adI~
#endif                                                             //~v@@@I~
        for (pc2=pc,len=0;len<reslen;len++,pc2++) //search next ascii//~v69XI~
        {                                                          //~v69XI~
#ifdef UTF8SUPPH                                                   //~va1cR~
            if (UDBCSCHK_ISGB4STR(pc2,reslen-len))                 //~va1cR~
            {                                                      //~va1cR~
                len+=UDBCSCHK_GB4SZ-1;                             //~va1cR~
                pc2+=UDBCSCHK_GB4SZ-1;                             //~va1cR~
                continue;                                          //~va1cR~
            }                                                      //~va1cR~
            if (ISSS3STR(pc2,reslen-len))                          //~va1cR~
            {                                                      //~va1cR~
                len+=UDBCSCHK_SS3SZ-1;                             //~va1cR~
                pc2+=UDBCSCHK_SS3SZ-1;                             //~va1cR~
                continue;                                          //~va1cR~
            }                                                      //~va1cR~
//          if (UDBCSCHK_ISDBCS1ST(*pc2))                          //~vax6R~
            if (UDBCSCHK_ISDBCS1ST(*pc2) && (reslen-len)>1)        //~vax6I~
            {                                                      //~va1cR~
                len+=UDBCSCHK_DBCSSZ-1;                            //~va1cR~
                pc2+=UDBCSCHK_DBCSSZ-1;                            //~va1cR~
                continue;                                          //~va1cR~
            }                                                      //~va1cR~
#else                                                              //~va1cR~
            if (eucjsw                                             //~v7adI~
            &&  *pc2==SS3   //SS3+xxxx                             //~v7adI~
            &&  reslen-len>=3)                                     //~v7adI~
            {                                                      //~v7adI~
                if (!len) 	//previous dbcs                        //~v7adI~
                {                                                  //~v7adI~
                	len=3;	//1 char convert;else until before SS3 //~v7adI~
                    ss3sw=1;                                       //~v7adI~
                }                                                  //~v7adI~
                break;  //convert until before SS3                 //~v7adI~
            }                                                      //~v7adI~
#endif                                                             //~va1cR~
            if (*pc2<0x80)                                         //~v69XI~
                break;                                             //~v69XI~
        }                                                          //~v69XI~
        utflen=len;                                                //~v69XI~
//      if (Sisutf8)    //current is utf8                          //~v79zR~
        if (envutf8sw)    //current is utf8                        //~v79zI~
        {                                                          //~v69XR~
UTRACED("noconv",pc,len);                                          //~v69XI~
            if (!g_utf8_validate(pc,len,&pcinvalid))    //invalid exist//~v69XR~
                len=(ULONG)pcinvalid-(ULONG)pc;                    //~v69XR~
UTRACEP("noconv out len=%d pc=%p,invaplid=%p\n",len,pc,pcinvalid); //~v69XI~
            memcpy(pcout,pc,len);                                  //~v69XR~
            pcout+=len;                                            //~v69XR~
            reslen-=len;                                           //~v69XR~
            pc+=len;                                               //~v69XR~
            utflen-=len;                                           //~v69XI~
        }                                                          //~v69XR~
        else        //current is not utf8                          //~v69XR~
            if (Sconverterdescriptor!=(GIConv)-1) //converter exist//~v69XR~
            {                                                      //~v69XR~
//                    writelen=len*MAXUTF8CHSZ;     //buffsz       //~v69XI~
//                    readlen=len;                                 //~v69XI~
//                    pc2=pc;                                      //~v69XI~
//                    for (;;)                                     //~v69XI~
//                    {                                            //~v69XI~
//                        if (g_iconv(Sconverterdescriptor,(gchar **)(&pc2),&readlen,(gchar **)(&pcout),&writelen)==(size_t)-1) //err//~v69XI~
//                            break;                               //~v69XI~
//                    }                                            //~v69XI~
//                    len=(ULONG)pc2-(ULONG)pc;                    //~v69XI~
//                    reslen-=len;                                 //~v69XI~
//                    pc+=len;                                     //~v69XI~
//    UTRACEP("g_iconv out errno=%d,len=%d readlen=%d,writelen=%d readlen=%d pc2=%p,pcout=%p\n",errno,len,readlen,writelen,readlen,pc2,pcout);//~v69XI~
                perr=NULL;                                         //~v706I~
                putf8=g_convert_with_iconv(pc,len,Sconverterdescriptor,&readlen,&writelen,&perr);//~v69XR~
                PRINTGERR("csublocale2utf8-g_locale_to_utf8",perr);//~v69XR~
UTRACEP("iconv out putf8=%p len=%d,readlen=%d,writelen=%d\n",putf8,len,readlen,writelen);//~v69XI~
                if (putf8)                                         //~v69XR~
                {                                                  //~v69XR~
#ifndef UTF8SUPPH                                                  //~va1cR~
                	if (ss3sw)                                     //~v7adI~
                    {                                              //~v7adI~
                    	*pcout++=INVALIDCH;                        //~v7adI~
                    }                                              //~v7adI~
#endif                                                             //~va1cR~
                    memcpy(pcout,putf8,writelen);                  //~v69XR~
                    g_free(putf8);                                 //~v69XR~
UTRACED("iconv out",pcout,writelen);                               //~v69XI~
                    pc+=readlen;                                   //~v69XR~
                    reslen-=readlen;                               //~v69XR~
                    pcout+=writelen;                               //~v69XR~
                    utflen-=readlen;                               //~v69XI~
                }                                                  //~v69XR~
            }//converter exist                                     //~v69XR~
        if (utflen) // >0x80 invalid utf or no converter           //~v69XI~
        {                                                          //~v69XI~
            if ((plinech=Sacsmap[(UINT)(*pc)]))  //line drawing char defined//~v69XR~
            {                                                      //~v69XI~
//              linechlen=strlen(plinech);                         //~v705R~
                linechlen=Sacslentb[(int)(*pc)];                   //~v705R~
                memcpy(pcout,plinech,linechlen);                   //~v69XI~
                pcout+=linechlen;                                  //~v69XI~
            }                                                      //~v69XI~
            else                                                   //~v69XI~
                *pcout++=INVALIDCH;                                //~v69XI~
            pc++;                                                  //~v69XI~
            reslen--;                                              //~v69XI~
        }                                                          //~v69XI~
    }//reslen                                                      //~v69XI~
    *pcout=0;                                                      //~v69XI~
    *Pputf8=Sbuff;                                                 //~v69XI~
    if (Ppreadlen)                                                 //~v69XI~
    	*Ppreadlen=Plen;                                           //~v69XI~
	writelen=(ULONG)pcout-(ULONG)Sbuff;                            //~v69XI~
    if (Ppwritelen)                                                //~v69XI~
		*Ppwritelen=writelen;                                      //~v69XR~
UTRACED("locale2utf8 out",Sbuff,writelen);                         //~v69XI~
    return 1;       //no not free output addr                      //~v69XI~
//    if (!noascii //all ascii                                     //~v69XR~
//    ||  Sisutf8     //current is utf8                            //~v69XR~
//    ||  Sconverterdescriptor==(GIConv)-1) //no converter         //~v69XR~
//    {                                                            //~v69XR~
//        *Pputf8=Sbuff;                                           //~v69XR~
//        if (Ppreadlen)                                           //~v69XR~
//            *Ppreadlen=Plen;                                     //~v69XR~
//        if (Ppwritelen)                                          //~v69XR~
//            *Ppwritelen=Plen;                                    //~v69XR~
//        return 1;       //no not free output addr                //~v69XR~
//    }                                                            //~v69XR~
//  }                                                              //~v69VR~
//  putf8=g_locale_to_utf8(ptext,Plen,&readlen,&writelen,&perr);   //~v69QR~
//	putf8=g_convert_with_iconv(ptext,Plen,Sconverterdescriptor,&readlen,&writelen,&perr);//~v69VR~
//  dprintf("locale2utf8 len=%d %s\n",Plen,Sbuff);                 //~v69VR~
//    putf8=g_convert_with_iconv(Sbuff,Plen,Sconverterdescriptor,&readlen,&writelen,&perr);//~v69XR~
//    PRINTGERR("csublocale2utf8-g_locale_to_utf8",perr);          //~v69XR~
//  if (readlen!=Plen)                                             //~v69VR~
//  	dprintf("locale2utf8 read=%d %s\n",readlen,Sbuff);         //~v69VR~
//    if (Ppreadlen)                                               //~v69XR~
//        *Ppreadlen=readlen;                                      //~v69XR~
//    if (Ppwritelen)                                              //~v69XR~
//        *Ppwritelen=writelen;                                    //~v69XR~
////  return putf8;                                                //~v69XR~
//    *Pputf8=putf8;                                               //~v69XR~
//    return 0;                                                    //~v69XR~
}//csublocale2utf8                                                 //~v69sI~
#ifdef UTF8SUPPH                                                   //~va1cR~
//************************************************************     //~va1cR~
//*for scrdata                                                     //~va1cR~
//*rc:1 dbcs padding done                                          //~va1cR~
//************************************************************     //~va1cR~
int csublocale2utf8scr(int Popt,char *Ptext,char *Pdbcs,int Plen,  //~va1cR~
						int *Ppreadlen,char **Pputf8,              //~va1cR~
						char **Ppdata,char **Ppdbcs,USHORT **Ppoffst,//~va1cR~
						int *Ppwritelen,int *Pplclen,int *Ppslno)  //~va1cR~
{                                                                  //~va1cR~
#undef INVALIDCH                                                   //~va1cR~
#define INVALIDCH '.'                                              //~va1cR~
static char *Sbuff=0;                                              //~va1cR~
static int   Sbufflen=0;                                           //~va1cR~
  	UCHAR *pc,*pcout,*pdbcs,*pdata,*pdbcs0,*pdata0,*pcd,*pcoutpad=0;//~va1cR~
    USHORT *psl,*psl0;                                             //~va1cR~
    int readlen,writelen,slno=0;                                   //~va1cR~
    int reslen,ch,buffsz,outlenlc;                                 //~va1cR~
	char utf8wk[UTF8_MAXCHARSZMAX*MAX_MBCSLEN];                    //~va1cR~
//  int opt;                                                       //~vak2R~
//  wchar_t ucs;                                                   //~va1cR~//~v@@@R~
    UWCHART ucs;                                                   //~v@@@I~
    int swaltch,altch;                                             //~va42R~
    int padu8len;                                                  //~va6nI~
    int swligaturemode;                                            //~va6pI~
//****************************                                     //~va1cR~
UTRACED("locale2utf8 in",Ptext,Plen);                              //~va1cR~
UTRACED("locale2utf8 dbcs",Pdbcs,Plen);                            //~va1cR~
	swligaturemode=Popt & CSL2UO_LIGATURE;                         //~va6pI~
	if (Plen>Sbufflen)                                             //~va1cR~
    {                                                              //~va1cR~
    	if (Sbuff)                                                 //~va1cR~
        	free(Sbuff);                                           //~va1cR~
        Sbufflen=0;                                                //~va1cR~
        buffsz=Plen*(UTF8_MAXCHARSZMAX+(1+2)*2+4);                 //~va1cR~
//            1+2: 2byte ".." may be added by GB4 expansion,*2:dbcs tbl//~va1cR~
//            4: USHORT offset of dbcs padding on utf8 str and dbcs str//~va1cR~
        Sbuff=malloc((UINT)buffsz);                                //~va1cR~
        if (Sbuff)                                                 //~va1cR~
	        Sbufflen=Plen;                                         //~va1cR~
UTRACEP("locale2utf8 malloclen=%d,buff=%p\n",buffsz,Sbuff);        //~va1cR~
    }                                                              //~va1cR~
    if (!Sbuff)                                                    //~va1cR~
    	return 4;                                                  //~va1cR~
// 	opt=UDCGMLO_LEN1IFERR;                                         //~vak2R~
    pdata0=pdata=Sbuff+Plen*UTF8_MAXCHARSZMAX;                     //~va1cR~
    pdbcs0=pdbcs=pdata+Plen*3;                                     //~va1cR~
    psl0=psl=(USHORT*)(ULONG)(pdbcs+Plen*3);                       //~va1cR~
	for (pc=Ptext,pcd=Pdbcs,pcout=Sbuff,reslen=Plen;reslen>0;)     //~va1cR~
    {                                                              //~va1cR~
        ch=*pc;                                                    //~va1cR~
        if (!ch)                                                   //~va1cR~
        {                                                          //~va1cR~
            *pcout++=' ';                                          //~va1cR~
            pc++;                                                  //~va1cR~
            *pdata++=' ';                                          //~va1cR~
          if (Pdbcs)    //if null,all sbcs                         //~vax5I~
            *pdbcs++=*pcd++;                                       //~va1cR~
          else                                                     //~vax5I~
            *pdbcs++=0;                                            //~vax5I~
            reslen--;                                              //~va1cR~
            continue;                                              //~va1cR~
        }                                                          //~va1cR~
        swaltch=0;                                                 //~va42I~
        if (ch<0x80)    //ascii                                    //~va1cR~
        {                                                          //~va1cR~
            ucs=*pc;	//errid                                    //~va42R~
            if (ucs<0x20)                                          //~va42I~
                if ((altch=UTF_GETALTCH_CTL(ucs))>0)               //~va44R~
                {                                                  //~va42R~
                    swaltch=1;                                     //~va42R~
                    ucs=altch;                                     //~va42R~
                }                                                  //~va42R~
          if (!swaltch)                                            //~va42R~
          {                                                        //~va42I~
            if ((ch==0x0a||ch==0x0d) && (Popt & CSL2UO_REP0a0d))   //~va1cR~
                *pcout++=INVALIDCH;	//current gtk version erase 0a...0d combination//~va1cR~
            else                                                   //~va1cR~
            if (UDBCSCHK_ISPRINT(ch))                              //~va1cR~
            	*pcout++=ch;	//no conversion                    //~va1cR~
            else                                                   //~va1cR~
                *pcout++=INVALIDCH;	//current gtk version erase 0a...0d combination//~va1cR~
            *pdata++=*(pcout-1);                                   //~va1cR~
          if (Pdbcs)                                               //~vax5I~
            *pdbcs++=*pcd++;                                       //~va1cR~
          else                                                     //~vax5I~
            *pdbcs++=0;                                            //~vax5I~
            pc++;                                                  //~va1cR~
            reslen--;                                              //~va1cR~
            continue;                                              //~va1cR~
          }//not alt ch trans                                      //~va42I~
        }//linech defined                                          //~va1cR~
        else                                                       //~va42I~
        if (!Pdbcs)                                                //~vax5I~
        {                                                          //~vax5I~
            if (ch==DEFAULT_ALTCH                                  //~vax5I~
            &&  (altch=UTF_GETALTCH_SBCS(0))>0                     //~vax5I~
            )                                                      //~vax5I~
            {                                                      //~vax5I~
                swaltch=1;                                         //~vax5I~
                ucs=altch;                                         //~vax5I~
            }                                                      //~vax5I~
            else                                                   //~vax5I~
            if (!UDBCSCHK_ISPRINT(ch))                             //~vax5I~
            {                                                      //~vax5I~
                *pcout++=INVALIDCH;                                //~vax5I~
            	*pdata++=INVALIDCH;                                //~vax5I~
            	*pc++=INVALIDCH;		//drawrepunprintable did it//~vax5I~
            	*pdbcs++=0;                                        //~vax5I~
            	reslen--;                                          //~vax5I~
            	continue;                                          //~vax5I~
            }                                                      //~vax5I~
        }                                                          //~vax5I~
        else                                                       //~vax5I~
        if (*pcd!=UDBCSCHK_DBCS1ST)                                //~va42I~
        {                                                          //~va42I~
            if (ch==DEFAULT_ALTCH                                  //~va47I~
            &&  (altch=UTF_GETALTCH_SBCS(*pcd))>0                  //~va47I~
            )                                                      //~va47I~
            {                                                      //~va47I~
                swaltch=1;                                         //~va47I~
                ucs=altch;                                         //~va47I~
            }                                                      //~va47I~
            else                                                   //~va47I~
            if (!UDBCSCHK_ISPRINT(ch))                             //~va42I~
            {                                                      //~va42I~
                *pcout++=INVALIDCH;                                //~va42I~
            	*pdata++=INVALIDCH;                                //~va42I~
            	*pc++=INVALIDCH;		//drawrepunprintable did it//~va42I~
            	*pdbcs++=*pcd++;                                   //~va42I~
            	reslen--;                                          //~va42I~
            	continue;                                          //~va42I~
            }                                                      //~va42I~
        }                                                          //~va42I~
        else    //DBCS1ST                                          //~va44I~
        {                                                          //~va44I~
            if (*pc==DEFAULT_ALTCH                                 //~va44R~
            &&   reslen>0                                          //~va44I~
            &&  *(pc+1)==DEFAULT_ALTCH                             //~va44R~
            &&  ((altch=UTF_GETALTCH_DBCS())>0)                    //~va44R~
            )                                                      //~va44I~
            {                                                      //~va44I~
                swaltch=2;                                         //~va44I~
            	ucs=altch;                                         //~va44I~
            }                                                      //~va44I~
        }                                                          //~va44I~
//**  >=0x80                                                       //~va1cR~
    if (swaltch==2)	//not DBCSSPACE                                //~va44I~
        readlen=2;   //DBCS                                        //~va44I~
    else                                                           //~va44I~
    {                                                              //~va44I~
      if (swaltch)                                                 //~va42R~
        readlen=1;                                                 //~va42I~
      else                                                         //~va42I~
//      readlen=utfcvl2uany1mb(0,pc,reslen,&ucs);                  //~v6unR~
        readlen=utfcvl2uany1mb(0,pc,reslen,&ucs,NULL/*rc*/);       //~v6unI~
    }                                                              //~va44I~
        if (readlen>0)  //cv ok                                    //~va1cR~
        {                                                          //~va1cR~
            writelen=uccvucs2utf((ULONG)ucs,utf8wk);               //~va1cR~
//          width=utfwcwidth(0,ucs,&widthflag);                    //~va1cR~
UTRACED("csubl2f inp lc data",pc,readlen);                         //~va1cR~
UTRACED("csubl2f out utf8wk",utf8wk,writelen);                     //~va1cR~
            memcpy(pcout,utf8wk,(UINT)writelen);                   //~va1cR~
            pcout+=writelen;                                       //~va1cR~
            memcpy(pdata,pc,(UINT)readlen);                        //~va1cR~
          if (Pdbcs)                                               //~vax5I~
            memcpy(pdbcs,pcd,(UINT)readlen);                       //~va1cR~
          else                                                     //~vax5I~
            memset(pdbcs,0,(UINT)readlen);                         //~vax5I~
            pdata+=readlen;                                        //~va1cR~
            pdbcs+=readlen;                                        //~va1cR~
#ifdef SSS                                                         //~vb91I~
            if (swaltch==2)                                        //~va44I~
            {                                                      //~va44I~
	            memcpy(pcout,utf8wk,(UINT)writelen);               //~va44I~
    	        pcout+=writelen;                                   //~va44I~
                *(pdbcs-2)=0;   //set to sbcs                      //~va44I~
                *(pdbcs-1)=0;                                      //~va44I~
            }                                                      //~va44I~
            else                                                   //~va44I~
#endif                                                             //~vb91I~
            if (readlen==UDBCSCHK_SS3SZ)                           //~va1cR~
            {                                                      //~va1cR~
              if (!swligaturemode)                                 //~va6pI~
              {                                                    //~va6pI~
            	slno++;                                            //~va1cR~
            	*psl++=(USHORT)((ULONG)pcout-(ULONG)Sbuff);        //~va1cR~
            	*psl++=(USHORT)((ULONG)pdata-(ULONG)pdata0);       //~va1cR~
//              *pcout++=Guviomdbcspad;                            //~va6nR~
                padu8len=uccvucs2utf((ULONG)Guviomdbcspad,pcout);  //~va6nR~
                pcout+=padu8len;                                   //~va6nI~
                pcoutpad=pcout;	//end chk                          //~va1cR~
//              *pdata++=Guviomdbcspad;                            //~va6nR~
                *pdata++=',';	//set only to keep column for drawtextstr_ligature//~va6nR~
    	        *pdbcs++=0;                                        //~va1cR~
              }                                                    //~va6pI~
            }                                                      //~va1cR~
            else                                                   //~va1cR~
            if (readlen==UDBCSCHK_GB4SZ)                           //~va1cR~
            {                                                      //~va1cR~
              if (!swligaturemode)                                 //~va6pI~
              {                                                    //~va6pI~
            	slno++;                                            //~va1cR~
            	*psl++=(USHORT)(((ULONG)pcout-(ULONG)Sbuff)|0x8000/*gb4 id*/);//~va1cR~
            	*psl++=(USHORT)((ULONG)pdata-(ULONG)pdata0);       //~va1cR~
//              *pcout++=Guviomdbcspad;                            //~va6nR~
//              *pcout++=Guviomdbcspad;                            //~va6nR~
                padu8len=uccvucs2utf((ULONG)Guviomdbcspad,pcout);  //~va6nR~
                pcout+=padu8len;                                   //~va6nI~
                padu8len=uccvucs2utf((ULONG)Guviomdbcspad,pcout);  //~va6nR~
                pcout+=padu8len;                                   //~va6nI~
                pcoutpad=pcout;	//end chk                          //~va1cR~
//              *pdata++=Guviomdbcspad;                            //~va6nR~
                *pdata++=',';	//set only to keep column          //~va6nI~
    	        *pdbcs++=0;                                        //~va1cR~
//              *pdata++=Guviomdbcspad;                            //~va6nR~
                *pdata++=',';	//set only to keep column          //~va6nI~
    	        *pdbcs++=0;                                        //~va1cR~
              }                                                    //~va6pI~
            }                                                      //~va1cR~
        }                                                          //~va1cR~
        else    //readlen=0;trans err                              //~va1cR~
        {                                                          //~va1cR~
            *pcout++=INVALIDCH;                                    //~va1cR~
            *pdata++=INVALIDCH;                                    //~va1cR~
            *pdbcs++=0;                                            //~va1cR~
            readlen=1;                                             //~va1cR~
        }                                                          //~va1cR~
        pc+=readlen;                                               //~va1cR~
        pcd+=readlen;                                              //~va1cR~
        reslen-=readlen;                                           //~va1cR~
    }//reslen                                                      //~va1cR~
    *pcout=0;                                                      //~va1cR~
    *Pputf8=Sbuff;                                                 //~va1cR~
    *Ppdata=pdata0;                                                //~va1cR~
    *Ppdbcs=pdbcs0;                                                //~va1cR~
    if (Ppreadlen)                                                 //~va1cR~
    	*Ppreadlen=Plen;                                           //~va1cR~
	writelen=(ULONG)pcout-(ULONG)Sbuff;                            //~va1cR~
    if (Ppwritelen)                                                //~va1cR~
		*Ppwritelen=writelen;                                      //~va1cR~
	outlenlc=(ULONG)pdata-(ULONG)pdata0;                           //~va1cR~
    if (Pplclen)                                                   //~va1cR~
		*Pplclen=outlenlc;                                         //~va1cR~
    if (slno                                                       //~va1cR~
    && pcoutpad!=pcout)	//not end by padding                       //~va1cR~
    {                                                              //~va1cR~
    	*psl++=writelen;	//set end of string                    //~va1cR~
    	*psl++=outlenlc;                                           //~va1cR~
        slno++;                                                    //~va1cR~
    }                                                              //~va1cR~
    if (Ppslno)                                                    //~va1cR~
		*Ppslno=slno;                                              //~va1cR~
    if (Ppoffst)                                                   //~va1cR~
		*Ppoffst=psl0;                                             //~va1cR~
UTRACED("csubl2f out return",Sbuff,writelen);                      //~va1cR~
UTRACED("csubl2f out return lc",pdata0,outlenlc);                  //~va1cR~
UTRACED("csubl2f out return dbcs",pdbcs0,outlenlc);                //~va1cR~
UTRACED("csubl2f out return slt ",psl0,slno*4);                    //~va1cR~
UTRACEP("slno=%d\n",slno);                                         //~va1cR~
    return slno!=0;       //dbcs padding done sw                   //~va1cR~
}//csublocale2utf8scr                                              //~va1cR~
#endif                                                             //~va1cR~
//===============================================================================//~v69UI~
//force scr resize by unit of cell size                            //~v69UI~
//parm:cell width/height                                           //~v69VI~
//===============================================================================//~v69UI~
void usetresizehint(int Pwidth,int Pheight,int Pcol,int Prow)      //~v69VR~
{                                                                  //~v69UI~
#ifndef ARM	//no screen size change request internaly              //~v@@@I~
	GdkGeometry hints;                                             //~v69UI~
    GtkRequisition req;                                            //~v69UI~
    int basex,basey,minx,miny,neww,newh;                           //~v69VR~
  	static int Sreqh=0;                                            //~v706R~
//************************************                             //~v69UI~
    memset(&hints,0,sizeof(hints));                                //~v69VR~
//  gtk_widget_get_child_requisition(Gpframe,&req);                //~v77qR~
//dprintf("requisition  t w=%d,h=%d\n",req.width,req.height);      //~v77qR~
	hints.width_inc =Pwidth;		//force width unit             //~v69UI~
	hints.height_inc=Pheight;		//force width unit             //~v69UI~
                                                                   //~v69VI~
    minx=Pwidth*MINCOLUMN;  //in fc5 when shrink to min,window is not movable//~v69UR~
	basex=0;//base of column calc -req.width;//RESIZE_HINT_BASE_ADJUST-Pheight*RESIZE_HINT_BASE_COLUMN;//ajust to display cols from left boundary//~v69UR~
    miny=Pheight*3;     //hdr ,cmdline,top line                    //~v69VR~
    basey=0;                                                       //~v69VI~
                                                                   //~v69VI~
	hints.base_width=basex;                                        //~v69UI~
	hints.base_height=basey;                                       //~v69VR~
	hints.min_width=minx;                                          //~v69VR~
	hints.min_height=miny;                                         //~v69VI~
dprintf("setresize hint inc=(w=%d,h=%d),base=(w=%d,h=%d),min=(w=%d,h=%d)\n",Pwidth,Pheight,basex,basey,minx,miny);//~v69UR~
    gtk_window_set_geometry_hints(GTK_WINDOW(Gpframe),GTK_WIDGET(Gpview),//~v69UI~
    									&hints,                    //~v69UI~
                                        GDK_HINT_MIN_SIZE|         //~v69XR~
                                        GDK_HINT_BASE_SIZE|        //~v69UR~
                                        GDK_HINT_RESIZE_INC);      //~v69XI~
                                                                   //~v69VI~
  if (!Sreqh)                                                      //~v77qR~
  {                                                                //~v77qR~
    gtk_widget_get_child_requisition(Gpframe,&req);                //~v77qI~
	dprintf("sizereq w=%d,h=%d\n",req.width,req.height);           //~v77qR~
    Sreqh=req.height;                                              //~v77qR~
	gtk_widget_set_size_request(Gpview,req.width,req.height); //requred for column calc from base//~v69UI~
  }                                                                //~v77qR~
    neww=Pwidth*Pcol;                                              //~v69VR~
//  newh=Pheight*Prow+req.height;                                  //~v77qR~
    newh=Pheight*Prow+Sreqh;                                       //~v77qM~
dprintf("setresize w=%d,h=%d,col=%d,row=%d\n",neww,newh,Pcol,Prow);//~v706R~
    gtk_window_resize(GTK_WINDOW(Gpframe),neww,newh);              //~v69VR~
#endif //ARM                                                       //~v@@@I~
    return;                                                        //~v69UI~
}//usetresizehint                                                  //~v69UI~
#ifdef UTF8UCS2                                                    //~va20I~
//**************************************************************** //~va20I~
//*                                                                //~va20I~
//**************************************************************** //~va20I~
int csubchkdd(int Popt,char *Pdbcs,int Plen)                       //~va20I~
{                                                                  //~va20I~
    return utfchkdd(0,(UCHAR*)Pdbcs,Plen);                         //~va20I~
}//csubchkdd                                                       //~va20I~
#ifdef AAA                                                         //~vc07R~
//**************************************************************** //~va30I~
//*                                                                //~va30I~
//**************************************************************** //~va30I~
int csubddcombine(int Popt,char *Pdata,char *Pdbcs,char *Pcombineid,int Plen,int *Ppstarterrctr,int *Ppendcombinectr)//~va30R~
{                                                                  //~va30I~
	return utfddcombine(Popt,Pdata,Pdbcs,Pcombineid,Plen,Ppstarterrctr,Ppendcombinectr);//~va30R~
}//csubddcombine                                                   //~va30I~
#endif //AAA                                                       //~vc07R~
//**************************************************************** //~va3bI~
//*get column of csr position-1 and +1 and +0                      //~va3bR~
//**************************************************************** //~va3bI~
int csubgetcsrposbf(int Popt,char *Pdbcs,int Plen,int Ppos,int *Ppposb,int *Ppposc,int *Ppposa)//~va3bR~
{                                                                  //~va3bI~
	int rc;                                                        //~va3bI~
//**************************                                       //~va3bM~
	rc=utfddgetcsrposbca(Popt,Pdbcs,Plen,Ppos,Ppposb,Ppposc,Ppposa);//~va3bI~
//dprintf("getcsrposba len=%d,pos=%d,prev=%d,cpos=%d,next=%d\n",Plen,Ppos,*Ppposb,*Ppposc,*Ppposa);//~va3bR~
    return rc;                                                     //~va3bR~
}//csubgetcsrposbf                                                 //~va3bI~
#endif                                                             //~va20I~
