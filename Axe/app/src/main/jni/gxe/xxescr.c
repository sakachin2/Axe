//CID://+vb91R~:                             update#=  162;        //+vb91R~
//******************************************************************************//~v003I~
//vb91:170228 marge rh9j gxe to arm-gxe                            //+vb91I~
//				vb5e:160918 gxe compiler warning                   //+vb91I~
//				vb4A:160815 if UNICOMB UNPR mode and LIGATURE ON, write altch just after base char using GetTextExtentPoint32W//+vb91I~
//				vb4r:160811 (XXE) v4f for XXE((ULIB:v6Ei)specify ligature on/off,combine on/of line by line(used for edit/filename  panel))//+vb91I~
//vax5:140627 (130928:vam4 for jni)file scrprint show unprintable by box char,use altch like scr draw//~vax5I~
//vak2:130822 Axe:ndk-r9 warning                                   //~vak2I~
//vaik:130601 virtical ruler disappear when col=0                  //~vai5I~
//vai5:130526 (Axe)Because depending font width draw_rect width<screen width,//~vai5I~
//            half gliph remains at right edge                     //~vai5I~
//v@@@:110610 Android(-DARM)                                       //~v@@@I~
//va7S:100907 (BUG)mouse drag at edge of screen dose not expand copy range bat scroll.//~va7SI~
//va7G:100902 (GXE:BUG)ss3 display invalid padding char '?'        //~va7GI~
//va7D:100830 (XXE:BUG)cap menu update err(it should not be based on Mcellcopy1 bur Gcsrposy)//~va7DI~
//va6p:000623 (LNX:BUG) ligature ignored for locale file           //~va6pI~
//va6n:000623 (BUG)Guviompadch was changed to ucs by ulib v65p     //~va6nI~
//va53:100419 num setr/seti should chk margin/lrecl overflow       //~va53I~
//va42:100328 (WXE:BUG)tab,tab padding char is not visual,print alt char//~va42I~
//va3t:100221 (WXEXXE)break also at prev char of vhex data csr pos for backspace at vhex line//~va3tI~
//va3i:100208 (XXE)leave ligature to pango when ligature on        //~va3iI~
//va3d:100206 (LNX)toggle ligature temprary by A+";", change combine key A+"/"-->C+":"//~va3dI~
//va3b:100205 (GXE)no ligature for hex edit(HEX mode and EH cmd)   //~va30I~
//va30:100126 combine mode of diacritical marks by A+/(saved to ini)//~va30I~
//va20:091125 (UTF8)UTF8 full support(display utf8 also unprintable as locale code)//~va20I~
//va1M:091121 (LNX)ligature support;keep monospace for english lagature; Arabian ? I don't know.//~va1MI~
//va1K:091119 slow performance at UB9,abend when -d01(trace)       //~va1KI~
//va1D:091115 gxeprint:2column print for 4byte dbcs if wysiwig is off//~va1DI~
//va1c:091030_merge GB18030 support                                //~va1cI~
//va0U:091003!(BUG:XXE)dbcstbl should be localdbcstbl for UTF8env  //~va0UI~
//v79Y:081029 (XXE) replace unprintable by '.'                     //~v79YI~
//v79Q:081027 (XXE:BUG)0x0a or 0x0d is cause of line is not drawn;replace it by unprintable id//~v79QI~
//v79D:081011 (XXE) draw ime cursor                                //~v79DI~
//v79z:080916 DBCS tbl for C and K of CJK                          //~v79zI~
//v78D:080503 (XXE)csr write to scr direct(not pix map) to avoid old csr line draw//~v78DI~
//v78B:080426*(wxe,gxe)vertical ruler draw performance             //~v78BI~
//v780:080212 syntaxhighlight support (SYN cmd,C+s func key)       //~v780I~
//v73t:070125 cross-hair ruler support by Shift+Ctl+lbuton         //~v73tI~
//v69V:060718 (XXE)Performance tuning for pango.                   //~v69VI~
//                 gdk_draw_layout_with_color for each char one by one is too slow//~v69VI~
//                 On the other hand string draw requres monospace and usetmonospace exaust cpu on gprof.//~v69VI~
//                 Bug of monospace determination was exist.(missed to set font to layout)//~v69VI~
//                 Monospace determination obey to font rfamily attribute.//~v69VI~
//                 DBCS is not just double iof sbcs width even for monospace font.//~v69VI~
//                 So use string write for sbcs only if mono space,else use uset monospace.//~v69VI~
//v69U:060718 (XXE:BUG)errmsg "Cut/Pastet source data contains invalid data"//~v69UI~
//            because scr width is not multiple of cellw,copy data contains 1 column over//~v69UI~
//v69S:060716 (WXEXXE:BUG)corrupted dbcs disply by mouse drag when dbcs split//~v69SI~
//            It cause abend for xxe.                              //~v69SI~
//v69Q:060715 (XXE)utf conversion perfomance chk                   //~v69QI~
//v69y:060608 (XXE:BUG)missing g_free for im msg translation       //~v69yI~
//v69v:060602 (XXE:FC5)utf8 im text is not displayed               //~v69vI~
//v69s:060531 (XXE:fc5)g_locale_to_utf8 return error if null char contained//~v69sI~
//v69r:060531 (XXE)print debug message by GError                   //~v69rI~
//v69n:060530 (XXE:fc5)avoid Gtk warning "GError not initialized". //~v69nI~
//v69d:060518 (GXE:BUG)killfocus chk logic missing                 //~v69dI~
//v682:060313 (WXE:BUG)menu enable re-evaluation requred for drag selection on dirlist;and also when reset by esc key//~v682I~
//vX03:050729 for the case sbcs is monospace but dbcs is not       //~vX03I~
//vX01:050729 cellh/cellw=0 mean same as fonth/fontw               //~vX01I~
//vXXE:050611 new for XXE                                          //~vXXEI~
//v63r:050525 ignore(no cursor move) 1st mouse click after killfocus//~v63rI~
//v55W:040322 (WXE)AT cmd support;"AT(&) interval,count;cmd"       //~v55WI~
//v55u:040229 (WXE)add open with for dir-list                      //~v55uI~
//v51w:030615 (WXE)icon for MDOS and Explorer                      //~v51wI~
//******************************************************************************//~v003I~
//******************************************************************************//~v003I~
#include <sys/timeb.h>                                             //~v63rI~
                                                                   //~vXXEI~
#ifndef ARM                                                        //~v@@@R~
#include <gnome.h>                                                 //~vXXEI~
#include <libgnomeprint/gnome-print.h>                             //~vXXEI~
#include <libgnomeprint/gnome-print-job.h>                         //~vXXEI~
#ifdef UTF8UCS2                                                                   //~vXXEI~//~va20R~
#include <pango/pango-types.h>                                     //~va20I~
#endif                                                             //~va20I~
#else                                                              //~v@@@R~
	#include <jnig.h>                                              //~v@@@R~
#endif //ARM                                                       //~v@@@R~
                                                                   //~v003I~
#include    "ulib.h"                                               //~v003I~
#include    "udbcschk.h"                                           //~v003I~
#include    "utrace.h"                                             //~2914I~
#include    "uque.h"                                               //~v780I~
#include    "ustring.h"                                            //+vb91I~
#include    "utf.h"                                                //~v79DR~
#include    "utf2.h"                                               //~v79DR~
#include    "ufile.h"                                              //~va1cR~
#include    "uviom.h"                                              //~va1cR~
#ifdef UTF8UCS2                                                    //~va20I~
#include    "ucvucs.h"                                             //~va20R~
#include    "utf22.h"                                              //~va20I~
#include    "utrace.h"                                             //~va30R~
#endif                                                             //~va20I~
                                                                   //~vXXEI~
#include    "gxe.h"                                                //~vXXEI~
#include    "xxedef.h"                                             //~vXXEI~
#include    "xxemain.h"                                            //~vXXEI~
#include    "xxexei.h"                                             //~vXXEI~
#include    "xxecsub.h"                                            //~vXXEI~
#include    "gxemfrm.h"                                            //~v682I~
//#include    "xeacb.h"                                            //~va1cR~
//#include    "xesub2.h"                                             //~va1cR~//~va53R~
#ifdef UTF8UCS2                                                    //~va20I~
#include    "xescr.h"                                              //~va20R~
#include    "xefile.h"                                             //~va20I~
#include    "xeutf2.h"                                             //~va20I~
#endif                                                             //~va20I~
#include    "xesub2.h"                                             //~va53I~
//===============================================================================//~v003I~
//===============================================================================//~va30R~
//  int  lineput(CDC *Ppdc,int Pprintsw,int Prow);                 //~va3bR~
    int  lineput(int Popt,CDC *Ppdc,int Pprintsw,int Prow);        //~va3bR~
	#define LPO_CSRBREAK     0x01	//strput split at csr pos      //~va3bR~
	#define LPO_CPU8         0x02	//csr is on CPU8 text          //~va3bI~
	#define LPO_VHEX         0x04	//data line of vhex line       //~va3tI~
	#define LPO_VHEXMASK   0xff00                                  //~va3tI~
	#define LPO_VHEXSHIFT       8                                  //~va3tI~
	#define LPO_GETVHEXPOS(opt) (((opt) & LPO_VHEXMASK)>>LPO_VHEXSHIFT)//~va3tI~
//	int  strput(CDC *Ppdc,int Pprintsw,int Prow,int Pcol,int Plen,USHORT Pattr,int Pcapsw);//~v69VR~
//	int  strput(CDC *Ppdc,int Pprintsw,int Prow,int Pcol,int Plen,USHORT Pattr,int Pcapsw,int Pdbcsctr);//~v780R~
	int  strput(CDC *Ppdc,int Pprintsw,int Prow,int Pcol,int Plen,USHORT Pattr,int Pstyle,int Pcapsw,int Pdbcsctr);//~v780I~
#ifdef UTF8UCS2                                                    //~va20I~
	#define  SPO_PRINT         0x01       //printsw                //~va20I~
	#define  SPO_DDFMT         0x02       //data/dbcs              //~va20I~
	#define  SPO_NOLIGATURE    0x04       //str is of part of ligature disabled//~va3bI~
#endif                                                             //~va20I~
//  int  scrcsr(void);                                             //~v78DR~
	void  setfgbgcolorfrompal(int Pfgpal,int Pbgpal);              //~vXXER~
	void  scrdrawbrush(CDC *Ppdc,PRECT Pprect,int Pbgpal);         //~vXXER~
	void  drawtext(CDC *Ppdc,int Pprintsw,int Px,int Py,char *Ptext,int Plen,int Pfgpal,int Pbgpal);//~vXXER~
//	void  drawtextstr(CDC *Ppdc,int Pprintsw,int Px,int Py,char *Ptext,char *Pdbcs,int Plen,int Pfgpal,int Pbgpal);//~v69VR~
// 	void  drawtextstr(CDC *Ppdc,int Pprintsw,int Px,int Py,char *Ptext,char *Pdbcs,int Plen,int Pfgpal,int Pbgpal,int Pdbcsctr);//~va30R~
//	void  drawtextstr(CDC *Ppdc,int Pprintsw,int Px,int Py,char *Ptext,char *Pdbcs,int Plen,int Pfgpal,int Pbgpal,int Pdbcsctr,int Prow);//~va30R~
//	void  drawtextstr(CDC *Ppdc,int Pprintsw,int Px,int Py,char *Ptext,char *Pdbcs,int Plen,int Pfgpal,int Pbgpal,int Pdbcsctr,int Prow,int Pcol);//~vb4rR~//+vb91I~
  	void  drawtextstr(int Popt,CDC *Ppdc,int Pprintsw,int Px,int Py,char *Ptext,char *Pdbcs,int Plen,int Pfgpal,int Pbgpal,int Pdbcsctr,int Prow,int Pcol);//~vb4rI~//+vb91I~
//	void scrruler(int Prow,int Pcol);                              //~v78BR~
//	void scrrulererase(int Prow,int Pcol);                         //~v78BR~
//	void scrdrawline(CPoint *Pppoint);                             //~v78BR~
  	void scrdrawline(CDC *Ppdc,CPoint *Pppoint);                   //~v78BI~
	int  xxemain_setfont(int Pcase);                               //~v780R~
	int drawimcsr(int Popt,int Pxx,int Pyy);                       //~v79DR~
int drawtextstr_ligatureu8_nocombine(int Popt,CDC *Ppdc,GdkGC *Ppgc,int Px,int Py,GdkColor *Ppfg,GdkColor *Ppbg,char *Ppu8,int Pu8len,char *Pdddata,char *Pdddbcs,char *Plcdbcs,int Pddlen);//~vb4AR~//+vb91I~
//===============================================================================//~vXXEI~
//                                                                 //~v003I~
//===============================================================================//~v003I~
void  xxemain_scrinit(void)                                        //~vXXER~
{                                                                  //~v003I~
    int ww,hh;                                                     //~vXXEI~
    GdkScreen *psc;                                                //~vXXEI~
    GdkGCValues gcvalue;                                           //~v73tI~
//************************************                             //~v003I~
    psc=gdk_screen_get_default();                                  //~vXXER~
	Mwinextw=gdk_screen_get_width(psc);		//physical screen size by pixel//~vXXEI~
	Mwinexth=gdk_screen_get_height(psc);                           //~vXXEI~
    Mscrwidth=ww=Gpview->allocation.width;                         //~vXXER~
    Mscrheight=hh=Gpview->allocation.height;                       //~vXXER~
    dprintf("scrinit w=%d,h=%d\n",ww,hh);                          //~vX03R~
    Gppixmap=gdk_pixmap_new(Gpview->window,ww,hh,-1);              //~vXXER~
    Gpgc=gdk_gc_new(Gpview->window);                               //~vXXEI~
//color                                                            //~vXXEI~
    Gpcolormap=gdk_colormap_get_system();                          //~vXXEI~
    gdk_colormap_alloc_color(Gpcolormap,&(Gcolordata.CDcolor),FALSE,TRUE);//writeable?,bestmatch?//~vXXER~
    Gcolordata.CDstatus=CDST_PIXEL;     //alloc end                //~vXXEI~
//setup for ruler                                                  //~v73tI~
	Grulerfg.red=Mrulercolor.red;                                  //~v73tI~
	Grulerfg.green=Mrulercolor.green;                              //~v73tI~
	Grulerfg.blue=Mrulercolor.blue;                                //~v73tI~
    gdk_colormap_alloc_color(Gpcolormap,&Grulerfg,FALSE,TRUE);//writeable?,bestmatch?//~v73tI~
	gcvalue.line_width=1;                                          //~v73tI~
	gcvalue.line_style=GDK_LINE_SOLID;                             //~v73tI~
	gcvalue.foreground=Grulerfg;                                   //~v73tR~
    Gpgcruler=gdk_gc_new_with_values(Gpview->window,&gcvalue,GDK_GC_FOREGROUND|GDK_GC_LINE_WIDTH|GDK_GC_LINE_STYLE);//~v73tI~
//color for hcopy                                                  //~vXXER~
    Gpprtgc=gdk_gc_new(Gpview->window);                            //~vXXER~
    gdk_colormap_alloc_color(Gpcolormap,&Gprintbg,FALSE,TRUE);//writeable?,bestmatch?//~vXXEI~
    gdk_colormap_alloc_color(Gpcolormap,&Gprintfg,FALSE,TRUE);//writeable?,bestmatch?//~vXXEI~
    gdk_colormap_alloc_color(Gpcolormap,&Gpreviewbg,FALSE,TRUE);//writeable?,bestmatch?//~vXXEI~
    gdk_gc_set_foreground(Gpprtgc,&Gprintfg);                      //~vXXER~
    gdk_gc_set_background(Gpprtgc,&Gprintbg);                      //~vXXER~
//cursor                                                           //~vXXEI~
    Gpgccsr=gdk_gc_new(Gpview->window);                            //~vXXEI~
    gdk_colormap_alloc_color(Gpcolormap,&Gcsrcolor,FALSE,TRUE);//writeable?,bestmatch?//~vXXER~
    gdk_gc_set_foreground(Gpgccsr,&Gcsrcolor);                     //~vXXER~
	gdk_gc_set_function(Gpgccsr,GDK_INVERT /*GDK_XOR GDK_COPY*/);  //~vXXER~
//im preedit                                                       //~vXXEI~
    Gpgcim=gdk_gc_new(Gpview->window);                             //~vXXEI~
    gdk_colormap_alloc_color(Gpcolormap,&Gimbgcolor,FALSE,TRUE);//writeable?,bestmatch?//~vXXEI~
    gdk_colormap_alloc_color(Gpcolormap,&Gimfgcolor,FALSE,TRUE);//writeable?,bestmatch?//~vXXEI~
	gdk_gc_set_line_attributes(Gpgcim,1,      //line width         //~vXXEI~
    	GDK_LINE_SOLID,GDK_CAP_NOT_LAST,GDK_JOIN_MITER);           //~vXXEI~
    xxemain_scrbgrect();                                           //~vXXER~
	return;                                                        //~v003I~
}//scrinit                                                         //~v003I~
//===============================================================================//~2908I~
//                                                                 //~2908I~
//===============================================================================//~2908I~
void  xxemain_scronok(int Pcpchngsw)                               //~vXXER~
{                                                                  //~2908I~
    Mscrcmaxrow=ROWCTR(Mscrheight);                                //~2908I~
    Mscrcmaxcol=COLCTR(Mscrwidth);                                 //~2908I~
    Mwxeintf.WXEIcsrchangesw=1;	//recreate caret                   //~2914I~
#ifndef XXE                                                        //~vXXEI~
    createfont();                                                  //~2A23M~
#endif                                                             //~vXXEI~
//  OnSize(0,Mscrwidth,Mscrheight); 	//ajust row/col ctr and resize//~vXXER~
    xxemain_OnSize(Mscrwidth,Mscrheight); 	//ajust row/col ctr and resize//~vXXEI~
//  wxe_onsize(Mscrcmaxrow,Mscrcmaxcol,Pcpchngsw);	//init         //~2A23R~
//  scrbgrect(Mpmemdc);                                            //~2A23R~
//  Mfulldrawsw=1;                                                 //~2A23R~
//  scrinvalidate();                                               //~2A23R~
	return;                                                        //~2908I~
}//scronok                                                         //~2928R~
//===============================================================================//~2908I~
//draw background rectangle                                        //~2908I~
//===============================================================================//~2908I~
void  xxemain_scrbgrect(void)                                      //~vXXER~
{                                                                  //~2908I~
    int hh,ww;                                                     //~vXXEI~
//************************************                             //~vXXER~
    ww=Gpview->allocation.width;                                   //~vXXEI~
    hh=Gpview->allocation.height;                                  //~vXXEI~
//  dprintf("main scrbgrect w=%d,h=%d,status=%d\n",ww,hh,Gcolordata.CDstatus);//~vX03R~
    if (Gcolordata.CDstatus!=CDST_PIXEL)     //alloc end           //~vXXEI~
    {                                                              //~vXXEI~
    	if (Gcolordata.CDstatus==CDST_RGB)     //valid rgb set     //~vXXEI~
//	    	gdk_color_alloc(Gpcolormap,&(Gcolordata.CDcolor));     //~v73tR~
  	    	gdk_colormap_alloc_color(Gpcolormap,&(Gcolordata.CDcolor),FALSE,TRUE);//gdk_alloc_color deplicated//~v73tI~
        else                                                       //~vXXEI~
	        return;     //not yet initialize Gpcolormap            //~vXXER~
    }                                                              //~vXXEI~
//    dprintf("color %x=(%x,%x,%x)\n",Gcolordata.CDcolor.pixel,    //~vX03R~
//                                      Gcolordata.CDcolor.red,    //~vX03R~
//                                      Gcolordata.CDcolor.green,  //~vX03R~
//                                      Gcolordata.CDcolor.blue);  //~vX03R~
//  Gcolordata.CDstatus=CDST_PIXEL;     //alloc end                //~vXXER~
    gdk_gc_set_foreground(Gpgc,&(Gcolordata.CDcolor));             //~vXXER~
//  gdk_gc_set_background(Gpgc,&(Gcolordata.CDcolor));             //~vXXER~
    gdk_draw_rectangle(Gppixmap,Gpgc,TRUE,0,0,ww,hh);              //~vXXEI~
//    dprintf("draw rect w=%d,h=%d\n",ww,hh);                      //~vX03R~
    gtk_widget_queue_draw_area(Gpview,0,0,ww,hh);                  //~vXXER~
	return;                                                        //~2908I~
}//xxemain_scrbgrect                                               //~vXXER~
//===============================================================================//~v003I~
//                                                                 //~v003I~
//===============================================================================//~v003I~
int  xxemain_OnSize(int Px,int Py)                                 //~vXXER~
{                                                                  //~v003I~
//    TEXTMETRIC tm;                                               //~vXXER~
//    CSize tl;                                                    //~vXXER~
//	WINDOWPLACEMENT wp;                                            //~vXXER~
//  int /*screxpandsw,*/ceilx,ceily;                               //~vak2R~
//************************************                             //~v003I~
    dprintf("wxescr onsize x=%d,y=%d\n",Px,Py);                    //~vX03R~
    if (!Px || !Py)                                                //~2A23I~
		return 0;                                                  //~2A23I~
    Mscrheight=Py;                                                 //~2908R~
    Mscrwidth=Px;                                                  //~2908R~
	if (Gppixmap)                                                  //~vXXEI~
		g_object_unref(Gppixmap);                                  //~vXXEI~
    if (!Mxxemaininitsw) //before xxemain init end                 //~vX03R~
        return 0;                                                  //~vX01I~
    Gppixmap=gdk_pixmap_new(Gpview->window,Px,Py,-1);              //~vXXER~
    Mscrcmaxrow=ROWCTR(Mscrheight);                                //~v003R~
    Mscrcmaxcol=COLCTR(Mscrwidth);                                 //~v003R~
//  ceily=Py-CWINHEIGHT(Mscrcmaxrow);                              //~vak2R~
//  ceilx=Px-CWINWIDTH(Mscrcmaxcol);                               //~vak2R~
//dprintf("maxcol=%d,maxrow=%d,ceilx=%d,ceily=%d,mcellw=%d,mcellh=%d\n",Mscrcmaxcol,Mscrcmaxrow,ceilx,ceily,Mcellw,Mcellh);//~vak2R~
	xxemain_scrbgrect();                                           //~vXXER~
//    if (!Mxeinitsw) //before xe init                             //~vX01R~
//        return 0;                                                //~vX01R~
    wxe_onsize(Mscrcmaxrow,Mscrcmaxcol,0);                         //~vXXEM~
    Mfulldrawsw=1;                                                 //~2914I~
	xxemain_scrinvalidate(0);                                      //~vXXER~
	return 1;                                                      //~v003R~
}//xxemain_OnSize                                                  //~vXXER~
//===============================================================================//~v003M~
//                                                                 //~v003M~
//===============================================================================//~v003M~
int  xxemain_draw(void)                                            //~vXXER~
{                                                                  //~v003M~
    char newpath[_MAX_PATH];                                       //~2922I~
    char newtitle[_MAX_PATH*2];                                    //~2922I~
//************************************                             //~v003M~
	if (!Mxeinitsw)		//xe init called                           //~2914I~
    	return 0;                                                  //~2914I~
    if (Mwxeintf.WXEIxetermsw)	//xe term called already           //~v55WR~
    	return 0;                                                  //~v55WR~
//  Mfulldrawsw=1;                                                 //~2914R~
    if (ugetcpath(newpath))    //current path changed              //~2922I~
    {                                                              //~2922I~
    	sprintf(newtitle,"%s    CWD=%s",WXE_TITLE,newpath);        //~2922R~
    	usettitle(newtitle);                                       //~vXXEI~
    }                                                              //~2922I~
	xxemain_scrupdate(Gppixmap,0);                                 //~vXXER~
	return 0;                                                      //~v003M~
}//draw                                                            //~v003M~
//===============================================================================//~v003I~
//                                                                 //~v003I~
//===============================================================================//~v003I~
void xxemain_scrupdate(CDC *Ppdc,int Pprintsw)                     //~vXXER~
{                                                                  //~v003I~
//  int row,currow,redrawcsrsw=0;                                  //~v78DR~
    int row;                                                       //~v78DR~
    UCHAR *puf;                                                    //~v003R~
    int linectr;                                                   //~v69UR~
    int csrrow,csrcol,csrredrawreqcond;                            //~va30R~
    int csrdataline=-1,rc2,opt,opt0,fileinfo;                      //~va3bR~
//  char *pdbcs;                                                   //~vak2R~
    int vhexpos;                                                   //~va3tI~
//************************************                             //~v003I~
	xxe_setsynpalettecolor();                                      //~v780I~
    puf=Mwxeintf.WXEIlineupdate;                                   //~v003R~
//  currow=Mwxeintf.WXEIcsrposy;                                   //~v78DR~
    csrrow=Mwxeintf.WXEIcsrposy;                                   //~va30R~
    csrcol=Mwxeintf.WXEIcsrposx;                                   //~va30R~
//  pdbcs=(char*)TEXTDBCS(csrrow,0);                               //~vak2R~
//	scrrulererase(Mwxeintf.WXEIcsrposy,Mwxeintf.WXEIcsrposx);	//set redraw flag to erase prev line//~v78BR~
//  if (Mwxeintf.WXEIcsrmovesw & WXEICSR_CHANGEROW)                //~v78DR~
//  	puf[Mwxeintf.WXEIoldcsrposy]=1;	//redraw the old cursor line to clear old cursor//~v78DR~
//  if (Mwxeintf.WXEIcsrmovesw || Mwxeintf.WXEIcsrchangesw) //colomn change or ins/rep change//~v78DR~
//  	puf[currow]=1;	//redraw the current cursor line to clear old cursor type//~v78DR~
    dprintf("scrupdate hdrline print=%x\n",*puf);                  //~v69UR~
    UTRACED("drawlineflag",puf,Mscrcmaxrow);                       //~va6pI~
    linectr=0;                                                     //~v69UI~
    csrredrawreqcond=(!Pprintsw                                    //~va30R~
//  					 && (Mlinedrawstat[csrrow] & (LDS_COMBINE|LDS_LIGATURE))//~va30R~
                     );//previously drawn combine char or ligature //~va30R~
	opt0=0;                                                        //~va3bI~
    if (csrredrawreqcond)                                          //~va3bR~
    {                                                              //~va3bR~
    	rc2=xxe_chkcsrpos(XXECCPO_CSRLINEBREAK,csrrow,csrcol,&fileinfo);//csr is on text area?//~va3bR~
        if (rc2)	//csr is on data or vhex line                  //~va3bI~
        {                                                          //~va3bI~
	        if (fileinfo & XXECCPI_CPU8)                           //~va3bI~
            {                                                      //~va3bI~
                opt0|=LPO_CPU8;                                    //~va3bI~
                if (rc2>0)      //csr is on dataline               //~va3bR~
                    csrdataline=csrrow; //csr is on dataline       //~va3bR~
                //else;  data line is redrawn when csr is on vhex line for CPU8 file//~va3bI~
                else                                               //~va3tI~
                {                                                  //~va3tI~
                    if (fileinfo & XXECCPI_VHEXCSRPOS)   //vheccsrpos is returned//~va3tI~
                    {                                              //~va3tI~
						vhexpos=XXECCPI_GETVHEXCSR(fileinfo);      //~va3tI~
                    	csrdataline=csrrow+rc2; //draw dataline on csr corresponding to csr vhex line//~va3tI~
                        vhexpos<<=LPO_VHEXSHIFT;                   //~va3tI~
                		opt0|=LPO_VHEX|vhexpos;                    //~va3tI~
                    }                                              //~va3tI~
                }                                                  //~va3tI~
            }                                                      //~va3bI~
            else                                                   //~va3bI~
            {                                                      //~va3bI~
//              if (Mligature)  //allow ligature                   //~va3dR~
                if (Mligature^Mligaturereverse)  //allow ligature  //~va3dI~
                {                                                  //~va3bI~
                    if (rc2>0)      //csr is ondataline            //~va3bI~
                        csrdataline=csrrow; //csr is on dataline   //~va3bI~
                    else                                           //~va3bI~
                        csrdataline=csrrow+rc2; //draw dataline on csr corresponding to csr vhex line//~va3bI~
                }                                                  //~va3bI~
            }                                                      //~va3bI~
        }                                                          //~va3bI~
    }                                                              //~va3bR~
    for (row=0;row<Mscrcmaxrow;row++,puf++)                        //~v003R~
    {                                                              //~v003I~
UTRACEP("scrupdate row=%d,Mfulldrawsw=%d,puf=%x\n",row,Mfulldrawsw,*puf);//~va20I~
//  	if (Mfulldrawsw || *puf!=0)	//line updated                 //~va30R~
    	if (Mfulldrawsw	//full scr draw                            //~va30R~
    	||  *puf!=0	//line updated                                 //~va30R~
//  	||  (row==csrrow && csrredrawreqcond)                      //~va3bR~
    	||  (row==csrdataline)                                     //~va3bR~
        )                                                          //~va30R~
        {                                                          //~v003I~
//  		Mlinedrawstat[row]=LDS_DRAWN;    //once clear combine\/ligature flag//~va30R~
dprintf("*************lineput row=%d\n",row);                      //~va3bR~
//        	lineput(Ppdc,Pprintsw,row);                            //~va3bR~
			opt=opt0;                                              //~va3bI~
			if (row==csrdataline)                                  //~va3bR~
				opt|=LPO_CSRBREAK;	//strput split at csr pos      //~va3bR~
          	lineput(opt,Ppdc,Pprintsw,row);                        //~va3bR~
		    *puf=0;	//reset line updated                           //~v003I~
//      	if (row==currow)	//cursor line reddrawn	           //~v78DR~
//          	redrawcsrsw=1;                                     //~v78DR~
            linectr++;                                             //~v69UI~
        }                                                          //~v003I~
    }                                                              //~v003I~
//UTRACEP("@@@@ scrupdate line=%d\n",linectr);                     //~v69UR~
    if (!Pprintsw)                                                 //~2A27I~
    {                                                              //~v682I~
//      scrruler(Mwxeintf.WXEIcsrposy,Mwxeintf.WXEIcsrposx);       //~v78BR~
//    if (redrawcsrsw)                                             //~v78DR~
//      scrcsr();                                                  //~v78DR~
    	if (Mwxeintf.WXEIstatus & WXEIS_DRAWMENU)	//draw menu req by reset key//~v682I~
        {                                                          //~v682I~
    		Mwxeintf.WXEIstatus &=~WXEIS_DRAWMENU;	//processed    //~v682I~
    		gxemfrm_enabletoolbar(0);//toolbar icon enable/disable //~v682I~
        }                                                          //~v682I~
    }                                                              //~v682I~
    Mfulldrawsw=0;	//reset                                        //~2914R~
    return;                                                        //~v003I~
}//xxemain_scrupdate                                               //~vXXER~
//===============================================================================//~v73tI~
//csr process                                                      //~v73tI~
//===============================================================================//~v73tI~
void  scrruler(int Prow,int Pcol)                                  //~v73tI~
{                                                                  //~v73tI~
    CPoint pt[2];                                                  //~v73tI~
	int xx,yy;                                                     //~v73tI~
//************************************                             //~v73tI~
//	if (Pcol && Mrulermode & WXERULER_V)	//vertical line        //~v78BR~
  	if (Mrulermode & WXERULER_V)	//vertical line                //~v78BI~
    {                                                              //~v73tI~
		xx=XX(Pcol)-1;                                             //~v73tI~
        if (xx<0)                                                  //~vak2I~
	        xx=0;                                                  //~vak2R~
    	pt[0].x=xx;                                                //~v73tI~
    	pt[1].x=xx;                                                //~v73tI~
    	pt[0].y=0;                                                 //~v73tI~
    	pt[1].y=Mscrheight;                                        //~v73tI~
//  	scrdrawline(pt);		//draw line                        //~v78BR~
    	scrdrawline(Gpview->window,pt);		//draw line            //~v78BI~
	    Mrulercol=Pcol;                                            //~v73tI~
        Mrulermodeo|=WXERULER_V;                                   //~v73tI~
    }                                                              //~v73tI~
//  if (Prow && Mrulermode & WXERULER_H)	//horizontal           //~v78BR~
    if (Mrulermode & WXERULER_H)	//horizontal                   //~v78BI~
    {                                                              //~v73tI~
		yy=YY(Prow+1)-1;                                           //~v73tI~
    	pt[0].x=0;                                                 //~v73tI~
    	pt[1].x=Mscrwidth;                                         //~v73tI~
    	pt[0].y=yy;                                                //~v73tI~
    	pt[1].y=yy;                                                //~v73tI~
//  	scrdrawline(pt);		//draw line                        //~v78BR~
    	scrdrawline(Gpview->window,pt);		//draw line            //~v78BI~
    	Mrulerrow=Prow;                                            //~v73tI~
        Mrulermodeo|=WXERULER_H;                                   //~v73tI~
    }                                                              //~v73tI~
    return;                                                        //~v73tI~
}//scrruler                                                        //~v73tI~
#ifdef AAA                                                         //~v78BI~
//===============================================================================//~v73tI~
//csr process                                                      //~v73tI~
//===============================================================================//~v73tI~
void  scrrulererase(int Prow,int Pcol)                             //~v73tI~
{                                                                  //~v73tI~
    UCHAR *puf;                                                    //~v73tI~
//************************************                             //~v73tI~
    if (Mrulermodeo & WXERULER_V)                                  //~v73tI~
    {                                                              //~v73tI~
    	Mfulldrawsw=1;                                             //~v73tI~
		Mrulermodeo&=~WXERULER_V;       //erased                   //~v73tI~
	}                                                              //~v73tI~
    else                                                           //~v73tI~
    if (Mrulermodeo & WXERULER_H && Mrulerrow!=0 && Mrulerrow!=Prow)    //col changed//~v73tI~
    {                                                              //~v73tI~
    	puf=Mwxeintf.WXEIlineupdate;                               //~v73tI~
        puf+=Mrulerrow;                                            //~v73tI~
        *puf=1;		//drw prev line to erase ruler for the case v-split screen//~v73tI~
		Mrulermodeo&=~WXERULER_H;                                  //~v73tI~
    }                                                              //~v73tI~
    return;                                                        //~v73tI~
}//scrruler                                                        //~v73tI~
#endif                                                             //~v78BI~
//===============================================================================//~v73tI~
//open by application                                              //~v73tI~
//===============================================================================//~v73tI~
int xxemain_scrsetruler(int Ptype)                                 //~v73tR~
{                                                                  //~v73tI~
//************************************                             //~v73tI~
    if (Ptype)                                                     //~v73tI~
    {                                                              //~v73tI~
    	if (Mrulermode==Ptype)                                     //~v73tI~
    		Mrulermode=0;                                          //~v73tI~
        else                                                       //~v73tI~
    		Mrulermode=Ptype;                                      //~v73tR~
    }                                                              //~v73tI~
    else                                                           //~v73tI~
    if (++Mrulermode>WXERULER_VH)                                  //~v73tI~
    	Mrulermode=WXERULER_NONE;                                  //~v73tI~
dprintf("setruler mode=%d\n",Mrulermode);                          //~v78BI~
    Mfulldrawsw=1;                                                 //~v73tI~
    xxemain_scrinvalidate(0);                                      //~v73tI~
    return Mrulermode;                                             //~v73tI~
}//scrsetruler                                                     //~v73tI~
//***************************************************************************//~v73tI~
//*linedraw                                                        //~v73tI~
//***************************************************************************//~v73tI~
//void scrdrawline(CPoint *Pppoint)                                //~v78BR~
void scrdrawline(CDC *Pdc,CPoint *Pppoint)                         //~v78BI~
{                                                                  //~v73tI~
//*******************************                                  //~v73tI~
dprintf("drawline (%d,%d)->(%d,%d)\n",Pppoint->x,Pppoint->y,(Pppoint+1)->x,(Pppoint+1)->y);//~v73tI~
//  gdk_draw_line(Gppixmap,Gpgcruler,Pppoint->x,Pppoint->y,(Pppoint+1)->x,(Pppoint+1)->y);//~v78BR~
    gdk_draw_line(Pdc,Gpgcruler,Pppoint->x,Pppoint->y,(Pppoint+1)->x,(Pppoint+1)->y);//~v78BI~
    return;                                                        //~v73tI~
}//scrdrawline                                                     //~v73tI~
//===============================================================================//~2914I~
//csr process                                                      //~2914I~
//===============================================================================//~2914I~
int  scrcsr(void)                                                  //~vXXER~
{                                                                  //~2914I~
	int rc=0;                                                      //~2914I~
//************************************                             //~2914I~
    if (Mwxeintf.WXEIcsrchangesw)                                  //~2914R~
    {                                                              //~2914I~
	    Mwxeintf.WXEIcsrchangesw=0;                                //~vXXEI~
//      rc+=usetcaret(Mwxeintf.WXEIcsrdbcssw,                      //~2915R~
        rc+=usetcaret( //for performance use sbcs csr always.      //~vXXER~
					Mwxeintf.WXEIcsrhstart,Mcellw,Mcellh,Mfontheight);//~2915I~
//      rc+=ushowcaret();   //duplicated req by csrmovesw on       //~vXXER~
    }                                                              //~2914I~
#ifndef XXE                                                        //~vXXEI~
    if (Mwxeintf.WXEIcsrvisiblechangesw)                           //~2914R~
    {                                                              //~2914I~
	    Mwxeintf.WXEIcsrvisiblechangesw=0;		//processed        //~2914M~
    	if (Mwxeintf.WXEIcsrvisible)                               //~2914R~
        	rc+=ushowcaret();	                                   //~2914R~
        else                                                       //~2914I~
        	rc+=uhidecaret();                                      //~2914R~
    }                                                              //~2914I~
#endif                                                             //~vXXEI~
    if (Mwxeintf.WXEIcsrmovesw)	//csr moved                        //~vXXER~
    {                                                              //~vXXER~
//  	Mwxeintf.WXEIcsrmovesw=0;	//processed                    //~va7DR~
		rc+=usetcaretpos(Mwxeintf.WXEIcsrposy,Mwxeintf.WXEIcsrposx);//~vXXER~
//      if (Mwxeintf.WXEIcsrmovesw & WXEICSR_CHANGEBYKBD)          //~va7DI~//~va7SR~
            mouse_csrmovedbykbd(0);        //update toolbar menu item//~va7DI~
    	Mwxeintf.WXEIcsrmovesw=0;	//processed                    //~va7DI~
    }                                                              //~vXXER~
    ushowcaret();                                                  //~vXXEI~
    return rc;                                                     //~2914I~
}//scrcsr                                                          //~2914I~
//===============================================================================//~v003I~
//1 line write                                                     //~v003I~
//===============================================================================//~v003I~
//int  lineput(CDC *Ppdc,int Pprintsw,int Prow)                    //~va3bR~
int  lineput(int Popt,CDC *Ppdc,int Pprintsw,int Prow)             //~va3bR~
{                                                                  //~v003I~
    USHORT *pattr,attr;                                            //~v003I~
    int col,totlen,len,colstart;                                   //~2A05R~
    int inboxswn,inboxsw;                                          //~2A05R~
    int nextcolstart;                                              //~v69SI~
//  CPoint pt1,pt2;                                                //~v780R~
    CPoint pt1={0,0},pt2={0,0};                                    //~v780I~
    char *pdbcs;                                                   //~v69SI~
    int dbcsctr=0,nextdbcsctr;                                     //~v69VI~
    char *pstyle,style;                                            //~v780I~
    int styleid,oldstyle=0;                                          //~v780I~//~v@@@R~
#ifdef UTF8SUPPH                                                   //~va1cR~
    char *pdbcs0;                                                  //~va1cR~
    int csrposbreaksw,csrpos,csrposnext,csrposprev;         //~va3bR~//~va30R~
    int vhexcol;                                                   //~va3tI~
#endif                                                             //~va1cR~
//************************************                             //~v003I~
	pattr=TEXTATTR(Prow,0);                                        //~2901R~
	pstyle=(char*)TEXTSTYLE(Prow,0);                               //~v780I~
    totlen=Mscrcmaxcol;                                            //~v003I~
 	UTRACEP("lineput opt=%x,row=%d\n",Popt,Prow);                  //~va3bR~
  	UTRACED("line data",TEXTDATA(Prow,0),totlen);                  //~v78DR~
  	UTRACED("line dbcs",TEXTDBCS(Prow,0),totlen);                  //~v78DR~
	UTRACED("line attr",TEXTATTR(Prow,0),totlen*2);                //+vb91I~
	UTRACED("line style",pstyle,totlen);                           //~v780I~
    len=0;                                                         //~2901R~
    attr=*pattr;                                                   //~2901I~
    style=*pstyle;	//top byte may be set no use                   //~v780I~
    if (Mcpcopypansw)	//pan copy                                 //~2A05I~
    {                                                              //~2A05R~
        pt1=Mcellcopy1;                                            //~2A05R~
        pt2=Mcellcopy2;                                            //~2A05R~
        CAPGETBOX(pt1,pt2);  //get let top and right bottom        //~2A05R~
        inboxsw=CAPCHKINBOX(pt1,pt2,Prow,0);	//attr of col 0    //~2A05R~
    }                                                              //~2A05R~
    else                                                           //~2A05R~
        inboxsw=-1;     //id of line is out of range               //~2A05R~
    colstart=0;                                                    //~2A05I~
    pdbcs=(char*)TEXTDBCS(Prow,0);  //top of the line              //~v69SI~
#ifdef UTF8SUPPH                                                   //~va1cR~
	pdbcs0=pdbcs;                                                  //~va1cR~
//  csrposbreaksw=( (Prow==Mwxeintf.WXEIcsrposy)                   //~va3bR~
//  			&&	!Pprintsw                                      //~va3bR~
//                );                                               //~va3bR~
    csrposbreaksw=(Popt & LPO_CSRBREAK);                           //~va3bR~
	csrpos=-1;                                                     //~va30R~
    csrposnext=-1;				//for no combining case(CPLC)      //~va30R~
    csrposprev=-1;				//for no combining case(CPLC)      //~va30I~
    if (csrposbreaksw)          //also for CPLC for ligatutre chk  //~va30R~
    {                                                              //~va30R~
      if (Popt & LPO_VHEX)                                         //~va3tI~
      {                                                            //~va3tI~
        vhexcol=LPO_GETVHEXPOS(Popt);                              //~va3tI~
		csubgetcsrposbf(UTFDDGSP_ALSOLOCAL,pdbcs,Mscrcmaxcol,vhexcol,&csrposprev,&csrpos,0/*next*/);//~va3tI~
	  }                                                            //~va3tI~
      else                                                         //~va3tI~
		csubgetcsrposbf(UTFDDGSP_ALSOLOCAL,pdbcs,Mscrcmaxcol,Mwxeintf.WXEIcsrposx,&csrposprev,&csrpos,&csrposnext);//~va3bR~//~va3dR~
    }                                                              //~va30R~
dprintf("csrpos sw=%d,b=%d,c=%d,a=%d\n",csrposbreaksw,csrposprev,csrpos,csrposnext);//~va30I~
#endif                                                             //~va1cR~
//  for (col=0;col<totlen;col++,pattr++,len++)                     //~v69SR~
//  for (col=0;col<totlen;col++,pattr++,pdbcs++)                   //~v780R~
    for (col=0;col<totlen;col++,pattr++,pdbcs++,pstyle++)          //~v780I~
    {                                                              //~v003I~
        if (inboxsw>=0)	//row is in the cap range                  //~2A05R~
            inboxswn=CAPCHKINBOX(pt1,pt2,Prow,col);                //~2A05R~
        else                                                       //~2A05R~
            inboxswn=-1;                                           //~2A05R~
//      if (attr!=*pattr || inboxswn!=inboxsw)                     //~v780R~
        if (                                                       //~v780I~
			attr!=*pattr                                           //~v780I~
		||  (!Pprintsw && style!=*pstyle)                          //~v780I~
		||  (col==csrposprev||col==csrpos||col==csrposnext)	//to keep csr position char//~va3bR~
		|| inboxswn!=inboxsw                                       //~v780I~
        )                                                          //~v780I~
        {                                                          //~v003I~
        	nextdbcsctr=0;                                         //~v69VI~
#ifdef UTF8SUPPH                                                   //~va1cR~
            if (inboxswn==1 && inboxsw==0 && UDBCSCHK_DBCSNOT1ST(*pdbcs))//pad or 2nd//~va1cR~
	            nextcolstart=col+XESUB_DBCSSPLITCTR_R(pdbcs,totlen-col);//~va1cR~
#else                                                              //~va1cR~
            if (inboxswn==1 && inboxsw==0 && *pdbcs==UDBCSCHK_DBCS2ND)//left boundary split dbcs//~v69SI~
			                                                       //~va1cR~
	            nextcolstart=col+1;                                //~v69SI~
#endif                                                             //~va1cR~
            else                                                   //~v69SI~
#ifdef UTF8SUPPH                                                   //~va1cR~
            if (inboxswn==0 && inboxsw==1 && UDBCSCHK_DBCSNOT1ST(*pdbcs))//next is pad or end//~va1cR~
#else                                                              //~va1cR~
            if (inboxswn==0 && inboxsw==1 && *pdbcs==UDBCSCHK_DBCS2ND)//right boundary split dbcs//~v69SI~
#endif                                                             //~va1cR~
            {                                                      //~v69VI~
#ifdef UTF8SUPPH                                                   //~va1cR~
	            nextcolstart=col-XESUB_DBCSSPLITCTR_L(pdbcs0,col); //~va1cR~
#else                                                              //~va1cR~
	            nextcolstart=col-1;  //next start col by dbcs 1st  //~v69SI~
#endif                                                             //~va1cR~
                nextdbcsctr=1;                                     //~v69VI~
                dbcsctr--;		//process on top of next string    //~v69VI~
            }                                                      //~v69VI~
            else                                                   //~v69SI~
	            nextcolstart=col;  //next start col by dbcs 1st    //~v69SI~
            len=nextcolstart-colstart;                             //~v69SI~
            if (col && style==oldstyle)                            //~v780I~
                styleid=-1;                                        //~v780I~
            else                                                   //~v780I~
                styleid=style;                                     //~v780I~
UTRACEP("row=%d,colstart=%d,len=%d,attr=%x,inbox=%d,dbcsctr=%d\n",Prow,colstart,len,attr,inboxsw,dbcsctr);//~va1cR~
          if (len>0)                                               //~v69SI~
//          strput(Ppdc,Pprintsw,Prow,colstart,len,attr,inboxsw);  //~v69VR~
//          strput(Ppdc,Pprintsw,Prow,colstart,len,attr,inboxsw,dbcsctr);//~v780R~
            strput(Ppdc,Pprintsw,Prow,colstart,len,attr,styleid,inboxsw,dbcsctr);//~v780R~
        	attr=*pattr;                                           //~v003I~
            oldstyle=style;                                        //~v780I~
        	style=*pstyle;                                         //~v780I~
//          colstart=col;                                          //~v69SR~
            colstart=nextcolstart;                                 //~v69SI~
            inboxsw=inboxswn;                                      //~2A05R~
            len=0;                                                 //~v003I~
            dbcsctr=nextdbcsctr;                                   //~v69VI~
        }                                                          //~v003I~
#ifdef UTF8UCS2                                                    //~va20I~
        if (UDBCSCHK_DBCS1STLU(*pdbcs))                            //~va20I~
#else                                                              //~va20I~
        if (*pdbcs==UDBCSCHK_DBCS1ST)                              //~v69VI~
#endif                                                             //~va20I~
            dbcsctr++;                                             //~v69VI~
    }                                                              //~v003I~
    len=totlen-colstart;                                           //~v69SI~
UTRACEP("last str row=%d,colstart=%d,len=%d,dbcsctr=%d\n",Prow,colstart,len,dbcsctr);//~va20I~
    if (len)                                                       //~2901I~
// 		strput(Ppdc,Pprintsw,Prow,colstart,len,attr,inboxsw);      //~v69VR~
// 		strput(Ppdc,Pprintsw,Prow,colstart,len,attr,inboxsw,dbcsctr);//~v780R~
   		strput(Ppdc,Pprintsw,Prow,colstart,len,attr,style,inboxsw,dbcsctr);//~v780I~
//dprintf("endofstrput stype=%d\n",style);                         //~v78BR~
    if (style!=WXEFONTSTYLE_NOSYNTAX) //not syntax highlight part  //~v780I~
		xxemain_setfont(-1); 			//back to original         //~v780I~
    return 0;                                                      //~v003I~
}//lineput                                                         //~v003R~
//===============================================================================//~2901I~
//1 line write                                                     //~2901I~
//parm:capsw:-1:no cap line,0:cap line but out of range,1:str in cap range//~2A01I~
//===============================================================================//~2901I~
//int  strput(CDC *Ppdc,int Pprintsw,int Prow,int Pcol,int Plen,USHORT Pattr,int Pcapsw)//~v69VR~
//int  strput(CDC *Ppdc,int Pprintsw,int Prow,int Pcol,int Plen,USHORT Pattr,int Pcapsw,int Pdbcsctr)//~v780R~
int  strput(CDC *Ppdc,int Pprintsw,int Prow,int Pcol,int Plen,USHORT Pattr,int Pstyle,int Pcapsw,int Pdbcsctr)//~v780I~
{                                                                  //~2901I~
    char   *pdata,*pdbcs/*,*pdatasbcs*/;                           //~vX03R~
    int  xx,yy,fgpal=0,bgpal=0;                                    //~vX03R~
    int ii/*,dbcssw*/;//sbcscnt,xxsbcs;                            //~vak2R~
#define PANCOLAJUST 20                                             //~2A05R~
    RECT rect;                                                     //~2A05R~
#ifdef UTF8UCS2                                                    //~va20I~
    int swutf8data;                                                //~va20I~
    int opt;                                                       //~va3bI~
#endif                                                             //~va20I~
    int lineopt;                                                   //~vb4rI~//+vb91I~
//************************************                             //~2901I~
  	lineopt=wxe_getlineopt(0,Prow,Pcol);                           //~vb4rI~//+vb91I~
	MAKE_TEXT_RECT(&rect,Prow,Pcol,Plen);                          //~vXXEI~
  dprintf("strput row=%d,col=%d,len=%d,attr=%x,stype=%x\n",Prow,Pcol,Plen,Pattr,Pstyle);//~va1cR~//~va30R~
    if (Pprintsw)                                                  //~2A27M~
    {                                                              //~2A27I~
        ;                                                          //~vXXEI~
    }                                                              //~2A27I~
    else                                                           //~2A27I~
    {                                                              //~2A27M~
UTRACEP("strput row=%d,col=%d,style=%d,len=%d\n",Prow,Pcol,Pstyle,Plen);//~v780I~
		if (Pstyle>=0) 	//style changed                            //~v780I~
        	xxemain_setfont(Pstyle);            //back to original //~v780R~
        if (Pcapsw==1)                                             //~2A27R~
        {                                                          //~2A27R~
            bgpal=Pattr & 0x0f;                                    //~2A27R~
            fgpal=(Pattr>>4) & 0x0f;                               //~2A27R~
        }                                                          //~2A27R~
        else                                                       //~2A27R~
        {                                                          //~2A27R~
            fgpal=Pattr & 0x0f;                                    //~2A27R~
            bgpal=(Pattr>>4) & 0x0f;                               //~2A27R~
        }                                                          //~2A27R~
	  if (Pcapsw==1 || Pstyle==WXEFONTSTYLE_NOSYNTAX) //capcopy on pan or not syntax highlight part//~v780I~
      {                                                            //~v780I~
        //fgcolor=Mwxeintf.WXEIpalrgb[fgpal];                      //~v780I~
        //bgcolor=Mwxeintf.WXEIpalrgb[bgpal];                      //~v780I~
        ;                                                          //~v780I~
      }                                                            //~v780I~
      else                                                         //~v780I~
      {                                                            //~v780I~
        if (Mwxeintf.WXEIsynrgb[fgpal] & WXE_SYNTAXRGB)            //~v780R~
	        fgpal|=WXE_SYNTAXRGB;                                  //~v780R~
//      else                                                       //~v780I~
//          fgcolor=Mwxeintf.WXEIpalrgb[fgpal];                    //~v780I~
        if (Mwxeintf.WXEIsynrgb[bgpal] & WXE_SYNTAXRGB)            //~v780R~
	        bgpal|=WXE_SYNTAXRGB;                                  //~v780R~
//      else                                                       //~v780I~
//          bgcolor=Mwxeintf.WXEIpalrgb[bgpal];                    //~v780I~
      }                                                            //~v780I~
                                                                   //~2A27R~
//      if (!Pprintsw && Prow==Mscrcmaxrow-1) //last row           //~vXXER~
//          rect.bottom=Mscrheight;                                //~vXXER~
//      if (rect.right<Mscrwidth && rect.right+Mcellw>Mscrwidth)   //~vXXER~
//          rect.right+=Mscrwidth;                                 //~vXXER~
        if (rect.right<Mscrwidth && rect.right+Mcellw>Mscrwidth)   //~vai5I~
            rect.right=Mscrwidth;   //drawrect upto right edge     //~vai5I~
                                                                   //~vXXEI~
//  	setfgbgcolorfrompal(bgpal,fgpal);                          //~vXXER~
//      Ppmemdc->FillRect(&rect,&br);                              //~vXXER~
		scrdrawbrush(Ppdc,&rect,bgpal);                            //~vXXER~
//printf("strput bgpal=%x\n",bgpal);                               //~vXXER~
//      Ppmemdc->SetTextColor(fgcolor);                            //~vXXER~
//      Ppmemdc->SetBkColor(bgcolor);                              //~vXXER~
//  	setfgbgcolorfrompal(fgpal,bgpal);                          //~vXXER~
    }                                                              //~2A27M~
    pdata=(char*)TEXTDATA(Prow,Pcol);                              //~2901R~
    pdbcs=(char*)TEXTDBCS(Prow,Pcol);                              //~2901R~
    UTRACED("strput data",pdata,Plen);                             //~vai5I~
#ifdef UTF8UCS2                                                    //~va20I~
    swutf8data=csubchkdd(0,pdbcs,Plen)*SPO_DDFMT;                  //~va20I~
//  if (swutf8data)                                                //~va30R~
//  	Mlinedrawstat[Prow]|=LDS_DDFMT;                            //~va30R~
#endif                                                             //~va20I~
//printf("strput row=%d,col=%d,len=%d,str=%s\n",Prow,Pcol,Plen,pdata);//~vXXER~
//printf("strput attr=%x,str=%s\n",Pattr,pdata);                   //~vXXER~
	xx=rect.left+Mfontoffsx;                                       //~vXXER~
	yy=rect.top+Mfontoffsy;                                        //~vXXER~
  if (!Pprintsw || Mpreviewmode)//for performance put whole string to screen//~vX03R~
  {                                                                //~va20I~
//	drawtextstr(Ppdc,Pprintsw,xx,yy,pdata,pdbcs,Plen,fgpal,bgpal);//@@@@//~v69VR~
#ifdef UTF8UCS2                                                    //~va20I~
    opt=swutf8data|Pprintsw;                                       //~va3bI~
  if (lineopt & UVIOO_CSRPOSCHK)                                   //+vb91I~
  {                                                                //+vb91I~
    if (xxe_chkcsrpos(XXECCPO_STRCHK,Prow,Pcol,0/*fileinfo*/))//chk disable ligature for each str//~va3bI~
    	opt|=SPO_NOLIGATURE;                                       //~va3bR~
  }                                                                //+vb91I~
//	drawtextstr(Ppdc,Pprintsw|swutf8data,xx,yy,pdata,pdbcs,Plen,fgpal,bgpal,Pdbcsctr);//~va30R~
//	drawtextstr(Ppdc,Pprintsw|swutf8data,xx,yy,pdata,pdbcs,Plen,fgpal,bgpal,Pdbcsctr,Prow);//~va30R~
//	drawtextstr(Ppdc,Pprintsw|swutf8data,xx,yy,pdata,pdbcs,Plen,fgpal,bgpal,Pdbcsctr,Prow,Pcol);//~va3bR~
//	drawtextstr(Ppdc,opt,xx,yy,pdata,pdbcs,Plen,fgpal,bgpal,Pdbcsctr,Prow,Pcol);//~vb4rR~//+vb91I~
  	drawtextstr(lineopt,Ppdc,opt,xx,yy,pdata,pdbcs,Plen,fgpal,bgpal,Pdbcsctr,Prow,Pcol);//~vb4rI~//+vb91I~
#else                                                              //~va20I~
  	drawtextstr(Ppdc,Pprintsw,xx,yy,pdata,pdbcs,Plen,fgpal,bgpal,Pdbcsctr);//@@@@//~v69VI~
#endif                                                             //~va20I~
  }                                                                //~va20I~
  else	//scrprint to paper                                        //~vX03R~
#ifdef UTF8UCS2                                                    //~va20I~
  if (swutf8data)                                                  //~va20I~
  {                                                                //~va20I~
	uprttextoutw(0,xx,yy,pdata,pdbcs,Plen);	//write dd string      //~va20I~
  }                                                                //~va20I~
  else                                                             //~va20I~
#endif                                                             //~va20I~
  {                                                                //~vX03I~
//  dbcssw=0;                                                      //~vak2R~
    for (ii=0;ii<Plen;ii++,pdata++,pdbcs++,xx+=Mcellw)             //~vXXER~
    {                                                              //~2901I~
        if (!*pdata)                                               //~2901I~
            *pdata=' ';                                            //~vXXEI~
//      if (*pdbcs==UDBCSCHK_DBCS1ST && (ii+1)<Plen)  //require  2byte write for DBCS//~v79zR~
        if (*pdata>=0x80            //no ascii(for the case dbcs space rep char)//~v79zI~
#ifdef UTF8SUPPH                                                   //~va1cR~
//*not utf8 data                                                   //~va20I~
        &&  *pdbcs==UDBCSCHK_DBCS1ST //require  full byte write for DBCS//~va1cR~
        &&  (ii+XESUB_DBCSSPLITCTR(pdbcs,Plen-ii,0))<=Plen)  //require  2byte write for DBCS//~va1cR~
#else                                                              //~va1cR~
        &&  *pdbcs==UDBCSCHK_DBCS1ST && (ii+1)<Plen)  //require  2byte write for DBCS//~v79zI~
#endif                                                             //~va1cR~
        {                                                          //~vXXEI~
			drawtext(Ppdc,Pprintsw,xx,yy,pdata,2,fgpal,bgpal);     //~vXXER~
//          dbcssw=1;                                              //~vak2R~
        }                                                          //~vXXEI~
        else                                                       //~2901I~
#ifdef UTF8SUPPH                                                   //~va1cR~
        if (UDBCSCHK_DBCSNOT1ST(*pdbcs))	//pad or 2nd           //~va1cR~
            continue;                                              //~va1cR~
#else                                                              //~va1cR~
        if (*pdbcs==UDBCSCHK_DBCS2ND)                              //~2901I~
        {                                                          //~vXXEI~
            if (dbcssw)                                            //~vXXEI~
            	continue;                                          //~vXXER~
			drawtext(Ppdc,Pprintsw,xx,yy,pdata,1,fgpal,bgpal);     //~vXXER~
        }                                                          //~vXXEI~
#endif                                                             //~va1cR~
        else                                                       //~2901I~
        {                                                          //~vXXEI~
//        	dbcssw=0;                                              //~vak2R~
			drawtext(Ppdc,Pprintsw,xx,yy,pdata,1,fgpal,bgpal);     //~vXXER~
        }                                                          //~vXXEI~
    }                                                              //~2901I~
  }//not monospace                                                 //~vX03R~
    return 0;                                                      //~2901I~
}//strput                                                          //~2901I~
//===============================================================================//~vXXEI~
//draw text background rectangle                                   //~vXXEI~
//===============================================================================//~vXXEI~
void  setfgbgcolorfrompal(int Pfgpal,int Pbgpal)                   //~vXXER~
{                                                                  //~vXXEI~
	GdkColor *pfg,*pbg;                                            //~vXXEI~
//************************************                             //~vXXEI~
	pfg=Gxxepalette+Pfgpal;                                        //~vXXER~
    if (Pbgpal)                                                    //~vXXER~
		pbg=Gxxepalette+Pbgpal;                                    //~vXXER~
    else                                                           //~vXXEI~
	    pbg=&(Gcolordata.CDcolor);                                 //~vXXEI~
    gdk_gc_set_foreground(Gpgc,pfg);                               //~vXXEI~
    gdk_gc_set_background(Gpgc,pbg);                               //~vXXEI~
	return;                                                        //~vXXEI~
}//setfgbgcolorfrompal                                             //~vXXEI~
//===============================================================================//~vXXEI~
//draw background rectangle                                        //~vXXEI~
//===============================================================================//~vXXEI~
void  scrdrawbrush(CDC *Ppdc,PRECT Pprect,int Pbgpal)              //~vXXER~
{                                                                  //~vXXEI~
    int xx,yy,hh,ww;                                               //~vXXEI~
	GdkColor *pbg;                                                 //~vXXEI~
    GdkGC    *pgc;                                                 //~vXXEI~
//************************************                             //~vXXEI~
    pgc=Gpgc;                                                      //~vXXER~
    if (Pbgpal)                                                    //~vXXER~
        pbg=Gxxepalette+Pbgpal;                                    //~vXXER~
    else                                                           //~vXXER~
        pbg=&(Gcolordata.CDcolor);                                 //~vXXER~
//  gdk_gc_set_background(Gpgc,pbg);                               //~vXXER~
	gdk_gc_set_foreground(pgc,pbg);                                //~vXXER~
	xx=Pprect->left;                                               //~vXXEI~
	yy=Pprect->top;                                                //~vXXEI~
	ww=Pprect->right-xx;                                           //~vXXER~
	hh=Pprect->bottom-yy;                                          //~vXXER~
dprintf("scrdrawbrush xx=%d,yy=%d,ww=%d,hh=%d,bg=%x\n",xx,yy,ww,hh,Pbgpal);//~va1cR~
UTRACEP("scrdrawbrush xx=%d,yy=%d,ww=%d,hh=%d,bg=%x\n",xx,yy,ww,hh,Pbgpal);//~va1cR~
    gdk_draw_rectangle(Ppdc,pgc,TRUE,xx,yy,ww,hh);                 //~vXXER~
//    if (Pbgpal)                                                  //~vXXER~
//        {                                                        //~vXXER~
//            pbg=&(Gcolordata.CDcolor);                           //~vXXER~
//            gdk_gc_set_background(Gpgc,pbg);                     //~vXXER~
//        }                                                        //~vXXER~
	return;                                                        //~vXXEI~
}//scrdrawbrush                                                    //~vXXER~
//===============================================================================//~vXXEI~
//draw a text char by utf8                                         //~vXXER~
//===============================================================================//~vXXEI~
void  drawtext(CDC *Ppdc,int Pprintsw,int Px,int Py,char *Ptext,int Plen,int Pfgpal,int Pbgpal)//~vXXER~
{                                                                  //~vXXEI~
//  GError *errpos=NULL;                                           //~v69sR~
	char *ptext;                                                   //~vXXEI~
    int /*readlen,*/writelen;                                      //~v69sR~
	GdkColor *pfg,*pbg;                                            //~vXXEI~
    GdkGC *pgc;                                                    //~vXXEI~
    int utfrc;                                                     //~v69VI~
#ifdef UTF8SUPPH                                                   //~vax5I~
    char *pdatascr,*pdbcsscr,*ptextscr;	//expanded  by "." insersion for SS3/GB4//~vax5I~
    USHORT *poffst;                                                //~vax5I~
    int writelenlc,slno,opt;                                       //~vax5I~
#endif                                                             //~vax5I~
//********************                                             //~vXXEI~
	if (Pprintsw)                                                  //~vXXEI~
    {                                                              //~vXXEI~
        pfg=&Gprintfg;                                             //~vXXEI~
        pbg=&Gprintbg;                                             //~vXXEI~
        pgc=Gpprtgc;                                               //~vXXER~
    }                                                              //~vXXEI~
    else                                                           //~vXXEI~
    {                                                              //~vXXEI~
	  if (Pfgpal & WXE_SYNTAXRGB)                                  //~v780I~
        pfg=Gxxesynpalette+(Pfgpal&0x0f);                          //~v780I~
      else                                                         //~v780I~
        pfg=Gxxepalette+Pfgpal;                                    //~vXXER~
	  if (Pbgpal & WXE_SYNTAXRGB)                                  //~v780I~
        pbg=Gxxesynpalette+(Pbgpal&0x0f);                          //~v780I~
      else                                                         //~v780I~
        if (Pbgpal)                                                //~vXXER~
            pbg=Gxxepalette+Pbgpal;                                //~vXXER~
        else                                                       //~vXXER~
            pbg=&(Gcolordata.CDcolor);                             //~vXXER~
        pgc=Gpgc;                                                  //~vXXEI~
    }                                                              //~vXXEI~
//  ptext=g_locale_to_utf8(Ptext,Plen,&readlen,&writelen,&errpos); //~v69sR~
//  PRINTGERR("drawtext g_locale_to_utf8",errpos);                 //~v69sR~
//  ptext=csublocale2utf8(Ptext,Plen,0,&writelen);                 //~v69VR~
UTRACED("drawtext",Ptext,Plen);                                    //~v69UR~
//  utfrc=csublocale2utf8(Ptext,Plen,0,&ptext,&writelen);          //~v79zR~
//  utfrc=csublocale2utf8(0/*SOURCE IS LOCALE MULTIBYTE*/,Ptext,Plen,0,&ptext,&writelen);//~v79QR~
#ifdef UTF8SUPPH                                                   //~vax5I~
	opt=CSL2UO_REP0a0d/*rep 0a 0d by ?*/;                          //~vax5I~
    utfrc=csublocale2utf8scr(opt,Ptext,NULL/*pdbcs*/,Plen,0/*preadlen*/,&ptextscr,&pdatascr,&pdbcsscr,&poffst,&writelen,&writelenlc,&slno);//apply altch for ctl char//~vax5I~
    ptext=ptextscr;                                                //~vax5I~
#else                                                              //~vax5I~
    utfrc=csublocale2utf8(CSL2UO_REP0a0d/*rep 0a 0d by ?*/,Ptext,Plen,0,&ptext,&writelen);//~v79QI~
#endif                                                             //~vax5I~
//  if (!ptext)                                                    //~v69VR~
    if (utfrc>1)                                                   //~v69VI~
    	return;                                                    //~vXXEI~
    if (Pprintsw && !Mimageprtscr) //screen print by gnome_print   //~vXXEI~
    	uprtscrtextout(ptext,Px,Py,writelen);                      //~vXXER~
    else                                                           //~vXXER~
    {                                                              //~vXXER~
    	pango_layout_set_text(Gplayout,ptext,writelen);            //~vXXER~
UTRACEP("drawtext xx=%d,yy=%d,len=%d,bg=%x,fg=%x\n",Px,Py,writelen,Pbgpal,Pfgpal);//~v69UR~
//printf("fgcolor=%x=(%x,%x,%x,)\n",pfg->pixel,pfg->red,pfg->green,pfg->blue);//~vXXER~
    	gdk_draw_layout_with_colors(Ppdc,pgc,Px,Py,Gplayout,pfg,pbg);//~vXXER~
	}                                                              //~vXXEI~
//if (ptext!=Ptext)	//converted by csyblocale2utf8                 //~v69VR~
//if (!utfrc)      	//converted by csyblocale2utf8                 //~v79DR~
//  g_free(ptext);                                                 //~v79DR~
    return;                                                        //~vXXEI~
}//drawtext                                                        //~vXXER~
#ifdef AAA  //function moved to csub                               //~va42I~
//===============================================================================//~v79YI~
//replace unprintable                                              //~v79YI~
//===============================================================================//~v79YI~
void  drawrepunprintable(char *Ptext,char *Pdbcs,int Plen)         //~v79YI~
{                                                                  //~v79YI~
	UCHAR *pdata,*pdbcs;                                           //~v79YI~
    int ii,errrep='.';                                             //~v79YI~
//************************                                         //~v79YI~
    for (ii=0,pdata=Ptext,pdbcs=Pdbcs;ii<Plen;ii++,pdata++,pdbcs++)//~v79YI~
    {                                                              //~v79YI~
#ifdef UTF8SUPPH                                                   //~va1cR~
        if (UDBCSCHK_DBCSCOLS(*pdbcs))	//1st,pad,2nd              //~va1cR~
            continue;                                              //~va1cR~
#else                                                              //~va1cR~
        if (*pdbcs==UDBCSCHK_DBCS1ST)                              //~v79YI~
        {                                                          //~v79YI~
			pdata++;                                               //~v79YI~
            pdbcs++;                                               //~v79YI~
            ii++;                                                  //~v79YI~
            continue;                                              //~v79YI~
        }                                                          //~v79YI~
#endif                                                             //~va1cR~
        if (!*pdata)	//rep space at csublocale2utf8             //~v79YI~
            continue;                                              //~v79YI~
#ifdef UTF8SUPPH                                                   //~va0UI~
        if (Gpxxedbcstbl[*pdata]&UDBCSCHK_TYPEUNP)	//unprintable ascii//~va0UI~
#else                                                              //~va0UI~
        if (Gpdbcstbl[*pdata]&UDBCSCHK_TYPEUNP)	//unprintable ascii//~v79YR~
#endif                                                             //~va0UI~
        	*pdata=errrep;                                         //~v79YI~
    }                                                              //~v79YI~
}//drawrepunprintable                                              //~v79YI~
#endif                                                             //~va42I~
#ifdef UTF8UCS2                                                    //~va20I~
//===============================================================================//~va20I~
//RTL chk (unicode is right to left)                               //~va20I~
//===============================================================================//~va20I~
int drawtextstr_chkrtl(int Popt,char *Ppu8,int Pu8len)             //~va20R~
{                                                                  //~va20I~
#ifdef UTF8RTL                                                     //~va30I~
#ifndef  S390                                                      //~va30I~
	ULONG ucs;                                                     //~va20I~
    char *pu8;                                                     //~va20I~
    int reslen,chsz,dir,rc=0;                                      //~va20I~
//*****************                                                //~va20I~
    for (reslen=Pu8len,pu8=Ppu8;reslen>0;pu8+=chsz,reslen-=chsz)   //~va20I~
    {                                                              //~va20I~
		uccvutf2ucs(0,pu8,reslen,&ucs,&chsz);                      //~va20I~
        if (!chsz)                                                 //~va20I~
        	break;                                                 //~va20I~
        dir=pango_unichar_direction(ucs);                          //~va20I~
        if (dir==PANGO_DIRECTION_RTL)                              //~va20I~
        {                                                          //~va20I~
        	rc=1;                                                  //~va20I~
        	break;                                                 //~va20I~
        }                                                          //~va20I~
    }                                                              //~va20I~
UTRACEP("rtlchk rc=%d\n",rc);                                      //~va20I~
    return rc;                                                     //~va20I~
#else 	//S390                                                     //~va30I~
	return 0;                                                      //~va30I~
#endif	//S390                                                     //~va30I~
#else                                                              //~va30I~
	return 0;                                                      //~va30I~
#endif                                                             //~va30I~
}//drawtextstr_chkrtl                                              //~va20I~
#endif                                                             //~va20I~
#ifdef UTF8SUPPH                                                   //~va1MR~
//===============================================================================//~va30R~
//draw textstring by utf8; 1 by 1 for ligature                     //~va30R~
//===============================================================================//~va30R~
int xxescr_chkligature(char *Pdbcs,int Plclen)                     //~va30R~
{                                                                  //~va30R~
	int layoutinfo[CSUBLI_MAX],ii,dbcsctr,rc=0,glyphctr;           //~va30R~
    char *pcd;                                                     //~va30R~
//***************                                                  //~va30R~
	UTRACED("chkligature dbcs",Pdbcs,Plclen);                      //~va30R~
	csublyoutinfo(0,Gplayout,layoutinfo);                          //~va30R~
	glyphctr=layoutinfo[CSUBLI_CTR];                               //~va30R~
    for (ii=0,dbcsctr=0,pcd=Pdbcs;ii<Plclen;ii++)                  //~va30R~
    {                                                              //~va30R~
    	if (*pcd++==UDBCSCHK_DBCS1ST)                              //~va30R~
        	dbcsctr++;                                             //~va30R~
    }                                                              //~va30R~
    if (glyphctr<Plclen-dbcsctr)                                   //~va30R~
    	rc=1;                                                      //~va30R~
    UTRACEP("chkligature rc=%d,lclen=%d,glyphctr=%d,dbcsctr=%d\n",rc,Plclen,glyphctr,dbcsctr);//~va30R~
    return rc;                                                     //~va30R~
}//xxescr_chkligature                                              //~va30R~
//===============================================================================//~va1MR~
//draw textstring by utf8; 1 by 1 for ligature                     //~va1MR~
//===============================================================================//~va1MR~
#ifdef UTF8UCS2                                                    //~va20I~
void  drawtextstr_ligature(int Popt,CDC *Ppdc,GdkGC *Ppgc,int Px,int Py,GdkColor *Ppfg,GdkColor *Ppbg,char *Pptext,int Plenu8,char *Ppdata,char *Ppdbcs,int Plen)//~va20I~
#else                                                              //~va20I~
void  drawtextstr_ligature(CDC *Ppdc,GdkGC *Ppgc,int Px,int Py,GdkColor *Ppfg,GdkColor *Ppbg,char *Pptext,int Plenu8,char *Ppdata,char *Ppdbcs,int Plen)//~va1MR~
#endif                                                             //~va20I~
{                                                                  //~va1MR~
	char *ptext,*pdata,*pdbcs;                                     //~va1MR~
    int ii,chsz,xx,dbcssz;                                         //~va1MR~
    int accumctr=0,xx1;                                            //~va3bI~
    char wknonalpha[MAXCOL],*pc=wknonalpha,*pcd;//accum numeric(no ligature) write at once//~va3bI~
//*****************                                                //~va1MR~
#ifdef ARM                                                         //~v@@@I~
	pcd=0;xx1=0;	//for compiler warning                         //~v@@@I~
#endif //ARM                                                       //~v@@@I~
UTRACED("pu8",Pptext,Plenu8);                                      //~va1MR~
UTRACED("plc",Ppdata,Plen);                                        //~va1MR~
UTRACED("pdbcs",Ppdbcs,Plen);                                      //~va1MR~
    for (ii=0,ptext=Pptext,pdata=Ppdata,pdbcs=Ppdbcs,xx=Px;        //~va1MR~
		ii<Plen;                                                   //~va1MR~
		ii++,ptext+=chsz,pdata+=dbcssz,pdbcs+=dbcssz,xx+=Mcellw)   //~va1MR~
    {                                                              //~va1MR~
    	if (*ptext<'A')	//no ligature may occur                    //~va3bI~
        {                                                          //~va3bI~
        	dbcssz=1;                                              //~va3bI~
            chsz=1;                                                //~va3bI~
            *pc++=*ptext;	//                                     //~va3bI~
            if (!accumctr)	//top                                  //~va3bI~
            {                                                      //~va3bI~
            	pcd=pdbcs;                                         //~va3bI~
                xx1=xx;                                            //~va3bI~
            }                                                      //~va3bI~
            accumctr++;                                            //~va3bI~
            continue;                                              //~va3bI~
        }                                                          //~va3bI~
        if (accumctr)                                              //~va3bI~
        {                                                          //~va3bI~
        	pc=wknonalpha;                                         //~va3bI~
	    	pango_layout_set_text(Gplayout,pc,accumctr);           //~va3bI~
  			usetmonospace(Popt,Gplayout,pc,pcd,accumctr,Mcellw);   //~va3bI~
UTRACED("drawtextstr_ligature nonalpha",pc,accumctr);              //~va3bI~
dprintf("drawtextstr_ligature nonalpha xx=%d,len=%d\n",xx1,accumctr);//~va3bI~
    		gdk_draw_layout_with_colors(Ppdc,Ppgc,xx1,Py,Gplayout,Ppfg,Ppbg);//~va3bI~
            accumctr=0;                                            //~va3bI~
        }                                                          //~va3bI~
    	chsz=UTF8CHARLENERR1(*ptext);                              //~va1MR~
//*after converted dd dbcs to locale dbcs                          //~va20I~
        if (*pdbcs==UDBCSCHK_DBCS1ST) //require  full byte write for DBCS//~va1MR~
        	dbcssz=XESUB_DBCSSPLITCTR(pdbcs,Plen-ii,0);            //~va1MR~
        else                                                       //~va1MR~
        	dbcssz=1;                                              //~va1MR~
    	pango_layout_set_text(Gplayout,ptext,chsz);                //~va1MR~
#ifdef UTF8UCS2                                                    //~va20I~
  		usetmonospace(Popt,Gplayout,pdata,pdbcs,dbcssz,Mcellw);    //~va20I~
#else                                                              //~va20I~
  		usetmonospace(0,Gplayout,pdata,pdbcs,dbcssz,Mcellw);       //~va1MR~
#endif                                                             //~va20I~
UTRACEP("drawtextstr_ligature utf8 ii=%d,dbcssz=%d\n",ii,dbcssz);  //~va1MR~
UTRACED("drawtextstr_ligature utf8",ptext,chsz);                   //~va1MR~
UTRACED("drawtextstr_ligature lc  ",pdata,dbcssz);                 //~va1MR~
    	gdk_draw_layout_with_colors(Ppdc,Ppgc,xx,Py,Gplayout,Ppfg,Ppbg);//~va1MR~
        if (dbcssz>1)                                              //~va1MR~
        {                                                          //~va1MR~
            ii++;                                                  //~va1MR~
            xx+=Mcellw;                                            //~va1MR~
        }                                                          //~va1MR~
    }                                                              //~va1MR~
    if (accumctr)	//last remaining                               //~va3bI~
    {                                                              //~va3bI~
        pc=wknonalpha;                                             //~va3bI~
        pango_layout_set_text(Gplayout,pc,accumctr);               //~va3bI~
        usetmonospace(Popt,Gplayout,pc,pcd,accumctr,Mcellw);       //~va3bI~
UTRACED("drawtextstr_ligature nonalpha last",pc,accumctr);         //~va3bI~
dprintf("drawtextstr_ligature nonalpha last xx=%d,len=%d\n",xx1,accumctr);//~va3bI~
        gdk_draw_layout_with_colors(Ppdc,Ppgc,xx1,Py,Gplayout,Ppfg,Ppbg);//~va3bI~
    }                                                              //~va3bI~
}//drawtextstr_ligature                                            //~va1MR~
#endif                                                             //~va1MR~
#ifdef UTF8UCS2                                                    //~va30R~
//===============================================================================//~va30R~
//draw textstring by utf8; 1 by 1 for ligature considering combining char//~va30R~
//===============================================================================//~va30R~
void  drawtextstr_ligatureu8(int Popt,CDC *Ppdc,GdkGC *Ppgc,int Px,int Py,GdkColor *Ppfg,GdkColor *Ppbg,char *Pptext,int Plenu8,char *Ppdata,char *Ppdbcs,int Plen,char *Ppdddbcs)//~va30R~
{                                                                  //~va30R~
	char *pu8,*pdbcs,*pdddbcs,*pdata;                              //~va30R~
	char *pu8n,*pdbcsn,*pdddbcsn,*pcombdddbcs,*pcombu8,*pdatan;    //~va30R~
    int ii,xx,chsz,dbcssz,lenu8,lenlc,dbcsid,comblenlc,comblenu8;  //~va30R~
//*****************                                                //~va30R~
UTRACED("pu8",Pptext,Plenu8);                                      //~va30R~
UTRACED("plc",Ppdata,Plen);                                        //~va30R~
UTRACED("pdbcs",Ppdbcs,Plen);                                      //~va30R~
UTRACED("pdddbcs",Ppdddbcs,Plen);                                  //~va30R~
#ifdef ARM                                                         //~v@@@I~
	comblenu8=0;	//for compiler warning                         //~v@@@I~
#endif //ARM                                                       //~v@@@I~
    for (ii=0,pu8=Pptext,pdbcs=Ppdbcs,pdddbcs=Ppdddbcs,pdata=Ppdata,xx=Px;//~va30R~
		ii<Plen;                                                   //~va30R~
		pdata+=lenlc,pu8+=lenu8,pdbcs+=lenlc,pdddbcs+=lenlc)       //~va30R~
    {                                                              //~va30R~
        for (lenu8=0,lenlc=0,comblenlc=0,pu8n=pu8,pdbcsn=pdbcs,pdddbcsn=pdddbcs,pdatan=pdata;//~va30R~
            ii<Plen;                                               //~va30R~
            ii+=dbcssz,pu8n+=chsz,pdbcsn+=dbcssz,pdddbcsn+=dbcssz,lenlc+=dbcssz,lenu8+=chsz,pdatan+=dbcssz)//~va30R~
        {                                                          //~va30R~
            chsz=UTF8CHARLENERR1(*pu8n);                           //~va30R~
    //*after converted dd dbcs to locale dbcs                      //~va30R~
            if (*pdbcsn==UDBCSCHK_DBCS1ST) //require  full byte write for DBCS//~va30R~
                dbcssz=2;                                          //~va30R~
            else                                                   //~va30R~
                dbcssz=1;                                          //~va30R~
            if (ii+dbcssz<Plen)                                    //~va30R~
            {                                                      //~va30R~
                dbcsid=*(pdddbcsn+dbcssz);                         //~va30R~
                if (UDBCSCHK_ISUCSWIDTH0(dbcsid))    //next has following combining char//~va30R~
                {                                                  //~va30R~
                	for (comblenu8=chsz,comblenlc=dbcssz,ii+=dbcssz,pcombu8=pu8n+chsz,pcombdddbcs=pdddbcsn+dbcssz;//~va30R~
							ii<Plen;                               //~va30R~
							ii++,pcombdddbcs++,comblenlc++,pcombu8+=chsz,comblenu8+=chsz)//~va30R~
                    {                                              //~va30R~
			            chsz=UTF8CHARLENERR1(*pcombu8);            //~va30R~
		                dbcsid=*pcombdddbcs;                       //~va30R~
		                if (!UDBCSCHK_ISUCSWIDTH0(dbcsid))    //next has following combining char//~va30R~
	                		break;                                 //~va30R~
                    }                                              //~va30R~
                    break;                                         //~va30R~
                }                                                  //~va30R~
            }                                                      //~va30R~
        }                                                          //~va30R~
        if (lenlc)                                                 //~va30R~
        {                                                          //~va30R~
			drawtextstr_ligature(Popt,Ppdc,Ppgc,xx,Py,Ppfg,Ppbg,pu8,lenu8,pdata,pdbcs,lenlc);//~va30R~
            xx+=lenlc*Mcellw;                                      //~va30R~
        }                                                          //~va30R~
        if (comblenlc)                                             //~va30R~
        {                                                          //~va30R~
UTRACED("drawtextstr_ligatureu8 settext utf8",pu8n,comblenu8);     //~va30R~
UTRACED("drawtextstr_ligatureu8 settext lc  ",pdatan,comblenlc);   //~va30R~
    		pango_layout_set_text(Gplayout,pu8n,comblenu8);        //~va30R~
  			usetmonospace(Popt,Gplayout,pdatan,pdbcsn,comblenlc,Mcellw);//~va30R~
    		gdk_draw_layout_with_colors(Ppdc,Ppgc,xx,Py,Gplayout,Ppfg,Ppbg);//~va30R~
            xx+=comblenlc*Mcellw;                                  //~va30R~
            lenlc+=comblenlc;                                      //~va30R~
            lenu8+=comblenu8;                                      //~va30R~
        }                                                          //~va30R~
    }                                                              //~va30R~
}//drawtextstr_ligatureu8                                          //~va30R~
#endif                                                             //~va30R~
//===============================================================================//~vXXEI~
//draw textstring by utf8                                          //~vXXEI~
//scr draw or print preview                                        //~va30R~
//===============================================================================//~vXXEI~
//void  drawtextstr(CDC *Ppdc,int Pprintsw,int Px,int Py,char *Ptext,char *Ppdbcs,int Plen,int Pfgpal,int Pbgpal)//~v69VR~
//void  drawtextstr(CDC *Ppdc,int Pprintsw,int Px,int Py,char *Ptext,char *Ppdbcs,int Plen,int Pfgpal,int Pbgpal,int Pdbcsctr)//~va30R~
//void  drawtextstr(CDC *Ppdc,int Pprintsw,int Px,int Py,char *Ptext,char *Ppdbcs,int Plen,int Pfgpal,int Pbgpal,int Pdbcsctr,int Prow)//~va30R~
//void  drawtextstr(CDC *Ppdc,int Pprintsw,int Px,int Py,char *Ptext,char *Ppdbcs,int Plen,int Pfgpal,int Pbgpal,int Pdbcsctr,int Prow,int Pcol)//~vb4rR~//+vb91I~
void  drawtextstr(int Popt,CDC *Ppdc,int Pprintsw,int Px,int Py,char *Ptext,char *Ppdbcs,int Plen,int Pfgpal,int Pbgpal,int Pdbcsctr,int Prow,int Pcol)//~vb4rI~//+vb91I~
{                                                                  //~vXXEI~
//  GError *errpos=NULL;                                           //~v69sR~
	char *ptext/*,*pc,*pce*/;                                      //~v69sR~
    int /*readlen,*/writelen;                                      //~v69sR~
	GdkColor *pfg,*pbg;                                            //~vXXEI~
    GdkGC *pgc;                                                    //~vXXEI~
#ifdef AAA                                                         //~va30R~
    PangoRectangle recti,rectl;                                    //~va1KR~
#endif                                                             //~va30R~
//  PangoAttrList  *ppal;                                          //~vX03R~
	int utfrc;                                                     //~v69VI~
#ifndef NOTRACE                                                    //~v69UI~
    struct timeb tb,tb2;                                           //~v69VI~
    static int Setutf,Setmono,Setstr,Settext,Setdraw;              //~v69VI~
#endif                                                             //~v69UI~
#ifdef UTF8SUPPH                                                   //~va1cR~
	char *pdatascr,*pdbcsscr,*ptextscr,*pdata,*pdbcs;	//expanded  by "." insersion for SS3/GB4//~va1cR~
    USHORT *poffst;                                                //~va1cR~
    int writelenlc,slno,xx,offsu8,offslc,oldoffslc,oldoffsu8,padlen,jj,reslen;//~va1cR~
	GdkColor *pfgpad,*pfgorg;                                      //~va1cR~
    int swligature;                                                //~va1MR~
#ifdef AAA                                                         //~va30R~
    int boxwidth;                                                  //~va1MR~
#endif                                                             //~va30R~
#endif                                                             //~va1cR~
#ifdef UTF8UCS2                                                    //~va20I~
	int optds,swutf8data,opt,rc,optusms=0;                         //~va20R~
    int swrtl=0;    //right to left                                //~va20I~
    char *pdddbcs;                                                 //~va30R~
#endif                                                             //~va20I~
    int swligaturemode;                                            //~va6pR~
//********************                                             //~vXXEI~
UTRACEP("drawtextstr row=%d,xx=%d,yy=%d,len=%d,opt=%x\n",Prow,Px,Py,Plen,Pprintsw);//~va6pR~
#ifdef ARM                                                         //~v@@@I~
	swligature=0; pdddbcs=0; pfgpad=0;	//for compiler warning     //~v@@@R~
#endif //ARM                                                       //~v@@@I~
#ifdef UTF8UCS2                                                    //~va20I~
	optds=Pprintsw;                                                //~va20I~
	Pprintsw &=SPO_PRINT;                                          //~va20I~
    swutf8data=optds & SPO_DDFMT;                                  //~va20I~
//  swligaturemode= (Mligature^Mligaturereverse)  //ligature mode  //~vb4rR~//+vb91I~
    swligaturemode= WXE_LIGATUREMODE(Popt)  //ligature mode        //~vb4rI~//+vb91I~
   					&&  !(optds & SPO_NOLIGATURE);	 //not ligature disabled part//~va6pI~
  if (swutf8data)                                                  //~va20I~
  {                                                                //~va20I~
	opt=UTFDD2FO_ERRREP|UTFDD2FO_DBCSSPACEREP|UTFDD2FO_DUPREPCHK|UTFDD2FO_VIO;//~va20R~
    rc=xeutfcvdd2f(opt,Ptext,Ppdbcs,Plen,(UCHAR**)&ptext,&writelen,(UCHAR**)&pdbcs);//~va20R~
    if (rc)                                                        //~va20I~
    	return;                                                    //~va20R~
UTRACED("drawtextstr inp data",Ptext,Plen);                        //~va20I~
UTRACED("drawtextstr inp dbcs",Ppdbcs,Plen);                       //~va20I~
UTRACED("drawtextstr out utf8",ptext,writelen);                    //~va20I~
UTRACED("drawtextstr out dbcs",pdbcs,Plen);                        //~va20I~
    pdddbcs=Ppdbcs; //dd fmt dbcs for ligature process             //~va30R~
    Ppdbcs=pdbcs;	//like setdbcstbl,set DBCS1ST and DBCS2ND and rep errch//~va20M~
    slno=0; //no dbcs padding                                      //~va20I~
    writelenlc=Plen;    //dd str len                               //~va20I~
    optusms=USMSO_DDFMT; //scr data is dd fmt                      //~va20R~
    pdata=Ptext;	//ddfmt should not used,but safety for access vioration err//~va20R~
    swrtl=drawtextstr_chkrtl(0,ptext,writelen);                    //~va20I~
  }                                                                //~va20I~
  else                                                             //~va20I~
#endif                                                             //~va20I~
  {                                                                //~va20I~
UTRACED("drawtextstr",Ptext,Plen);                                 //~v69UI~
//  drawrepunprintable(Ptext,Ppdbcs,Plen);                         //~v79YI~//~va42R~
UTSSTART(tb2);                                                     //~v69VI~
//  ptext=g_locale_to_utf8(Ptext,Plen,&readlen,&writelen,&errpos); //~v69sR~
//  PRINTGERR("drawtextstr-g_locale_to_utf8",errpos);              //~v69sR~
//  ptext=csublocale2utf8(Ptext,Plen,0,&writelen);                 //~v69VR~
UTSSTART(tb);                                                      //~v69VI~
//  utfrc=csublocale2utf8(Ptext,Plen,0,&ptext,&writelen);          //~v79zR~
//  utfrc=csublocale2utf8(0/*source is locale encoding*/,Ptext,Plen,0,&ptext,&writelen);//~v79QR~
#ifdef UTF8SUPPH                                                   //~va1cR~
//    utfrc=csublocale2utf8scr(CSL2UO_REP0a0d/*rep 0a 0d by ?*/,Ptext,Ppdbcs,Plen,0,&ptextscr,&pdatascr,&pdbcsscr,&poffst,&writelen,&writelenlc,&slno);//~va6pR~
	opt=CSL2UO_REP0a0d/*rep 0a 0d by ?*/;                          //~va6pI~
    if (swligaturemode)                                            //~va6pI~
		opt=CSL2UO_LIGATURE;	//set no padding                   //~va6pI~
    utfrc=csublocale2utf8scr(opt,Ptext,Ppdbcs,Plen,0,&ptextscr,&pdatascr,&pdbcsscr,&poffst,&writelen,&writelenlc,&slno);//~va6pI~
    ptext=ptextscr;                                                //~va1cR~
    pdata=pdatascr;                                                //~va1cR~
    pdbcs=pdbcsscr;                                                //~va1cR~
//  if (!(Guviomopt2 & UVIOMO2_PADON))	//padding display?         //~va1cR~//~va1MR~
//  	slno=0;	//no padding display attr setting                  //~va1cR~//~va1MR~
#else                                                              //~va1cR~
    utfrc=csublocale2utf8(CSL2UO_REP0a0d/*rep 0a 0d by ?*/,Ptext,Plen,0,&ptext,&writelen);//~va1cR~
#endif                                                             //~va1cR~
UTSEND(tb,Setutf);                                                 //~v69VI~
//  if (!ptext)                                                    //~v69VR~
    if (utfrc>1)                                                   //~v69VI~
    	return;                                                    //~vXXEM~
  }//not dd fmt                                                    //~va20I~
//replace null-->space because pango_layout_set_text ingnore after null//~vXXEI~
//  if (pc=memchr(ptext,0,(UINT)writelen),pc)                      //~v69sR~
//  	for (pce=ptext+writelen;pc<pce;pc++)                       //~v69sR~
//      	if (!*pc)                                              //~v69sR~
//          	*pc=' ';	                                       //~v69sR~
	if (Pprintsw)	//previewmode                                  //~vXXEI~
    {                                                              //~vXXEI~
    	uprtscrtextout(ptext,Px,Py,writelen);                      //~vXXEI~
//      dprintf("preview prtscr=%s",ptext);                        //~vX03R~
    }                                                              //~vXXEI~
    else                                                           //~vXXEI~
    {                                                              //~vXXEI~
	  if (Pfgpal & WXE_SYNTAXRGB)                                  //~v780I~
        pfg=Gxxesynpalette+(Pfgpal&0x0f);                          //~v780I~
      else                                                         //~v780I~
    	pfg=Gxxepalette+Pfgpal;                                    //~vXXER~
#ifdef UTF8SUPPH                                                   //~va1cR~
		if (slno)                                                  //~va1cR~
        {                                                          //~va1cR~
		  	if (Pfgpal & WXE_SYNTAXRGB)                            //~va1cR~
        		pfgpad=Gxxesynpalette+UVIOM_PADATTR;               //~va1cR~
      		else                                                   //~va1cR~
    			pfgpad=Gxxepalette+UVIOM_PADATTR;                  //~va1cR~
        }                                                          //~va1cR~
#endif                                                             //~va1cR~
	  if (Pbgpal & WXE_SYNTAXRGB)                                  //~v780I~
        pbg=Gxxesynpalette+(Pbgpal&0x0f);                          //~v780I~
      else                                                         //~v780I~
    	if (Pbgpal)                                                //~vXXER~
        	pbg=Gxxepalette+Pbgpal;                                //~vXXER~
    	else                                                       //~vXXER~
        	pbg=&(Gcolordata.CDcolor);                             //~vXXER~
    	pgc=Gpgc;                                                  //~vXXER~
UTRACEP("pos=y=%d,x=%d,bg=%x,fg=%x\n",Py,Px,*pbg,*pfg);            //~va1cR~
UTRACED("drawtextstr",ptext,writelen);                             //~va1cR~
//dprintf("drawtextstr plen=%d,len=%d,%*.*s\n",Plen,writelen,writelen,writelen,ptext);//~v69VR~
#ifdef UTF8SUPPH                                                   //~va1cR~
    	if (UTF_COMBINEMODE())                                     //~va30R~
        	optusms|=USMSO_COMBINEON;	//parm to also draw ligature//~va30R~
    	else                                                       //~va30R~
        	optusms|=USMSO_COMBINEOF;                              //~va30R~
        pfgorg=pfg;                                                //~va1cR~
      for (oldoffsu8=0,oldoffslc=0,xx=Px,reslen=writelenlc;reslen>0;)//~va1cR~
      {	//padding attr setting                                     //~va1cR~
        pfg=pfgorg;                                                //~va1cR~
UTRACEP("drawtextstr reslen=%d,slno=%d\n",reslen,slno);            //~va1DI~
		if (slno)                                                  //~va1cR~
        {                                                          //~va1cR~
            offsu8=*poffst++;                                      //~va1cR~
            offslc=*poffst++;                                      //~va1cR~
	        padlen=((offsu8 & 0x8000)!=0)+1;	//2 for gb4,1 for ss3//~va1cR~
            offsu8&=0x7fff;                                        //~va1cR~
            writelen=offsu8-oldoffsu8;                             //~va1cR~//~va7GR~
            writelenlc=offslc-oldoffslc;                           //~va1cR~//~va7GR~
//    	    oldoffsu8=offsu8+padlen;                               //~va7GR~
      	    oldoffsu8=offsu8;                                      //~va7GI~
          	oldoffslc=offslc+padlen;                               //~va1cR~
            reslen-=writelenlc+padlen;                             //~va1cR~
            slno--;                                                //~va1cR~
        }                                                          //~va1cR~
        else                                                       //~va1KI~
        {                                                          //~va1KI~
	        padlen=0;                                              //~va1KI~
            reslen-=writelenlc;                                    //~va1KI~
        }                                                          //~va1KI~
UTRACEP("drawtextstr2 reslen=%d,slno=%d\n",reslen,slno);           //~va1KI~
       for (jj=0;jj<2;jj++)                                        //~va1cR~
       {                                                           //~va1cR~
UTRACEP("drawtextstr jj=%d,paddlen=%d,reslen=%d,slno=%d,writelen=%d,writelenlc=%d\n",jj,padlen,reslen,slno,writelen,writelenlc);//~va1DI~
#endif                                                             //~va1cR~
UTSSTART(tb);                                                      //~v69VI~
    	pango_layout_set_text(Gplayout,ptext,writelen);            //~vXXER~
UTSEND(tb,Settext);                                                //~v69VI~
		UTRACED("drawtextstr pango settext",ptext,writelen);       //~va6nR~
// if (swutf8data                    //utf8 file                   //~va6pR~
// &&  (Mligature^Mligaturereverse)  //ligature mode               //~va6pR~
// &&  !(optds & SPO_NOLIGATURE)     //not ligature disabled part  //~va6pR~
// )                                                               //~va6pR~
   if (swligaturemode)                                             //~va6pI~
   {                                                               //~va3iI~
    UTRACEP("drawtextstr ligaturwe mode");                         //~va6pI~
    if (swutf8data                                                 //~vb4AR~//+vb91I~
    &&  !COMBINEMODE(Popt))  //split or unpr                       //~vb4AR~//+vb91I~
      	drawtextstr_ligatureu8_nocombine(optusms,Ppdc,pgc,xx,Py,pfg,pbg,ptext,writelen,pdata,pdddbcs,pdbcs,writelenlc);//~vb4AR~//+vb91I~
    else                                                           //~vb4AR~//+vb91I~
    {                                                              //~vb4AR~//+vb91I~
//	usetmonospace_ligature(optusms,Gplayout,pdata,pdbcs,writelenlc,Mcellw);//~vb4rR~//+vb91I~
  	usetmonospace_ligature(optusms,Gplayout,pdata,pdbcs,writelenlc,Mcellw,0/*strwidth=xx*/);//~vb4rI~//+vb91I~
    gdk_draw_layout_with_colors(Ppdc,pgc,xx,Py,Gplayout,pfg,pbg);  //~va3iI~
    }                                                              //~vb4AR~//+vb91I~
   }                                                               //~va3iI~
   else	//not ligature mode write                                  //~va3iI~
   {                                                               //~va3iI~
	   	swligature=xxescr_chkligature(pdbcs,writelenlc);           //~va3bM~
dprintf("chkligature %d\n",swligature);                            //~va3bM~
//        if (swligature) //ligature detected                      //~va3iR~
//          if (!(optds & SPO_NOLIGATURE))    //not ligature disabled part//~va3iR~
////          if (Mligature)          //dialog option allow ligature//~va3iR~
//            if (Mligature^Mligaturereverse)  //allow ligature    //~va3iR~
//            {                                                    //~va3iR~
////              Mlinedrawstat[Prow]|=LDS_LIGATURE;  //redraw csrline even if not updated//~va3iR~
//                swligature=0;   //no regature correction         //~va3iR~
//            }                                                    //~va3iR~
dprintf("ligature %d,opt=x%x\n",swligature,optds);                 //~va3bM~
    if (swligature)	//correct ligature to monospace                //~va30R~
    {                                                              //~va30R~
      if (swutf8data)                                              //~va30R~
	    drawtextstr_ligatureu8(optusms,Ppdc,pgc,xx,Py,pfg,pbg,ptext,writelen,pdata,pdbcs,writelenlc,pdddbcs);//~va30R~
      else                                                         //~va30R~
	    drawtextstr_ligature(optusms,Ppdc,pgc,xx,Py,pfg,pbg,ptext,writelen,pdata,pdbcs,writelenlc);//~va30R~
    }                                                              //~va30R~
    else                                                           //~va30R~
    {	//no ligature occurred                                     //~va30R~
//dprintf("drawtextstr @@@@;%s;\n",pango_layout_get_text(Gplayout));//~v69UR~
//dprintf("draw text row=%d,monospace=%d,Pdbcsctr=%d,Mcellw=%d,Mfontw=%d\n",Py,Mmonospace,Pdbcsctr,Mcellw,Mfontwidth);//~v69VR~
UTSSTART(tb);                                                      //~v69VI~
#ifdef AAA  //mono font is not mono for space char at fc4          //~v69VI~
        if (!(Mmonospace==1&&Mcellw==Mfontwidth))                  //~vX03I~
          if (!(!Pdbcsctr && Mmonospace && Mcellw==Mfontwidth))	//except (all sbcs and sbcs monospace and cellsz=fontsz)//~v69VI~
#endif                                                             //~v69VI~
//			usetmonospace(Gplayout,Ppdbcs,Plen,Mcellw);            //~v79zR~
#ifdef UTF8SUPPH                                                   //~va1cR~
//			usetmonospace(Gplayout,pdata,pdbcs,writelenlc,Mcellw); //~va1DR~
            if (optusms & USMSO_COMBINEON)   //scr combine mode    //~va30R~
    			optusms|=USMSO_COMBINESP;    //widen width for the string if ligature occured//~va30R~
    #ifdef UTF8UCS2                                                //~va20I~
  			usetmonospace(optusms,Gplayout,pdata,pdbcs,writelenlc,Mcellw);//~va20I~
    #else                                                          //~va20I~
  			usetmonospace(0,Gplayout,pdata,pdbcs,writelenlc,Mcellw);//~va1DI~
    #endif                                                         //~va20I~
#else                                                              //~va1cR~
  			usetmonospace(Gplayout,Ptext,Ppdbcs,Plen,Mcellw);      //~v79zI~
#endif                                                             //~va1cR~
		UTRACED("pango setmonospace",pdbcs,writelenlc);            //~va1cR~
UTSEND(tb,Setmono);                                                //~v69VI~
#ifdef UTF8SUPPH                                                   //~va1MR~
#ifdef UTF8UCS2                                                    //~va20I~
      if (swrtl)                                                   //~va20I~
      {                                                            //~va20I~
        swligature=1;                                              //~va20I~
	    drawtextstr_ligature(optusms,Ppdc,pgc,xx,Py,pfg,pbg,ptext,writelen,pdata,pdbcs,writelenlc);//~va20I~
      }                                                            //~va20I~
      else                                                         //~va20I~
#endif                                                             //~va20I~
      {                                                            //~va20I~
#ifdef AAA                                                         //~va30R~
		swligature=0;                                              //~va1MR~
//    		pango_layout_get_extents(Gplayout,&recti,&rectl);      //~va1MR~
//  		UTRACEP("pango attr shape rectl x=%d,y=%d,w=%d,h=%d,cellw=%d\n",//~va1MR~
//    		rectl.x,rectl.y,rectl.width,rectl.height,Mcellw*writelenlc*PANGO_SCALE);//~va1MR~
//  	if (!Mligature			//disalog option dose not allow ligature//~va3dR~
//      if (!(Mligature^Mligaturereverse))  //not allow ligature   //~vb4rR~//+vb91I~
        if (!WXE_LIGATUREMODE(Popt))  //not allow ligature         //~vb4rI~//+vb91I~
    	&& Gxxestat & GXXES_FONTLIGATURE)                          //~va1MI~
        {                                                          //~va1MR~
    		pango_layout_get_extents(Gplayout,&recti,&rectl);          //~va1KR~//~va1MR~
  			UTRACEP("pango attr shape rectl x=%d,y=%d,w=%d,h=%d,cellw=%d\n",          //~va1KR~//~va1MR~
    		rectl.x,rectl.y,rectl.width,rectl.height,Mcellw*writelenlc*PANGO_SCALE);                     //~va1KR~//~va1MR~
  			UTRACEP("drawtextstr xx=%d,yy=%d,bg=%x,fg=%x\n",Px,Py,pbg,pfg);  //~va1KR~//~va1MR~
            boxwidth=Mcellw*(writelenlc-padlen)*PANGO_SCALE;	//ligature occured//~va1MR~
  			UTRACEP("drawtextstr padlen=%d,boxw=%d\n",padlen,boxwidth);//~va1MR~
            if (rectl.width<boxwidth)	//ligature occured         //~va1MR~
            {                                                      //~va1MR~
#ifdef UTF8UCS2                                                    //~va20I~
	        	drawtextstr_ligature(optusms,Ppdc,pgc,xx,Py,pfg,pbg,ptext,writelen,pdata,pdbcs,writelenlc);//~va20I~
#else                                                              //~va20I~
	        	drawtextstr_ligature(Ppdc,pgc,xx,Py,pfg,pbg,ptext,writelen,pdata,pdbcs,writelenlc);//~va1MR~
#endif                                                             //~va20I~
                swligature=1;                                      //~va1MR~
            }                                                      //~va1MR~
        }                                                          //~va1MR~
#endif //AAA                                                       //~va30R~
		UTRACELAYOUT(Gplayout);                                    //~va30R~
      }//!swrtl                                                    //~va20I~
#endif                                                             //~va1MR~
UTSSTART(tb);                                                      //~v69VI~
#ifdef UTF8SUPPH                                                   //~va1cR~
	if (!swligature)                                               //~va1MR~
    	gdk_draw_layout_with_colors(Ppdc,pgc,xx,Py,Gplayout,pfg,pbg);//~va1cR~
#else                                                              //~va1cR~
    	gdk_draw_layout_with_colors(Ppdc,pgc,Px,Py,Gplayout,pfg,pbg);//~vXXER~
#endif                                                             //~va1cR~
    }	//no ligature occurred                                     //~va30R~
   }//not ligature mode wrire                                      //~va3iI~
		UTRACEP("pango color swligature=%d,xx=%d\n",swligature,xx);                        //~va1cR~//~va30R~
UTSEND(tb,Setdraw);                                                //~v69VI~
#ifdef UTF8SUPPH                                                   //~va1cR~
		if (reslen<0)     //last may not end by padding            //~va1cR~
        	break;                                                 //~va1cR~
        ptext+=writelen;                                           //~va1cR~
        pdata+=writelenlc;                                         //~va1cR~
        pdbcs+=writelenlc;                                         //~va1cR~
        xx+=Mcellw*(writelenlc-padlen);	//advance by char width(drop adjust by padlen for pad write)//~va1cR~
        UTRACEP("xx=%d\n",xx);                                     //~va7GR~
		if (!padlen)     //last has no padding                     //~va1KI~
        	break;                                                 //~va1KI~
//      writelen=padlen;                                           //~va6nR~
        writelen=padlen*UTF8CHARLENERR1(*ptext);//Guviomdbcspad set by csublocaletoutf8 may not be ascii but ucs altchar//~va6nR~
      	oldoffsu8+=writelen;	//offsetlc is already updated      //~va7GI~
        writelenlc=padlen;                                         //~va1cR~
        padlen=0;	//no padding for padd write                    //~va1cR~
        pfg=pfgpad; //for padding                                  //~va1cR~
       }//org string and padding                                   //~va1cR~
		if (!slno)                                                 //~va1cR~
        	break;                                                 //~va1cR~
      }	//padding attr setting loop                                //~va1cR~
#endif                                                             //~va1cR~
//!printsw                                                         //~va30R~
    }                                                              //~vXXEI~
#ifndef UTF8SUPPH                                                  //~va1cR~
//if (ptext!=Ptext)	//converted by csyblocale2utf8                 //~v69VR~
#ifdef UTF8UCS2                                                    //~va20I~
 if (!swutf8data)                                                  //~va20I~
#endif                                                             //~va20I~
  if (!utfrc)       //converted by csyblocale2utf8                 //~v69VI~
    g_free(ptext);                                                 //~vXXEI~
#endif                                                             //~va1cR~
UTSEND(tb2,Setstr);                                                //~v69VI~
//UTRACEP("@@@ str=%d,utf=%d,mono=%d,text=%d,draw=%d\n",Setstr,Setutf,Setmono,Settext,Setdraw);//~v69VR~
    return;                                                        //~vXXEI~
}//drawtextstr                                                     //~vXXER~
#ifdef XXX                                                         //~vX03R~
//===============================================================================//~vX03I~
// monospace setting                                               //~vX03I~
//!!! glyph draw bypassed at gdk_draw_layout if shape attr is set,so use geomery setting(csub2)//~vX03I~
//===============================================================================//~vX03I~
void setlayoutattr(void)                                           //~vX03I~
{                                                                  //~vX03I~
    PangoAttrList  *ppal,*ppalo;                                   //~vX03I~
	PangoRectangle recti,rectl;                                    //~vX03M~
    PangoAttribute *ppa;                                           //~vX03M~
//********************                                             //~vX03I~
//  if (Gppangoattrlistscreen)  //may original by gtk              //~vX03I~
//  	pango_attr_list_unref(Gppangoattrlistscreen);              //~vX03I~
    ppalo=pango_layout_get_attributes(Gplayout);                   //~vX03I~
//dprintf("old attrlist=%p\n",ppalo);                              //~vX03R~
    if (!ppalo)                                                    //~vX03I~
    	ppal=pango_attr_list_new();                                //~vX03I~
    else                                                           //~vX03I~
    	ppal=ppalo;                                                //~vX03I~
//dprintf("new attrlist=%p\n",ppal);                               //~vX03R~
    pango_layout_get_extents(Gplayout,&recti,&rectl);              //~vX03I~
//dprintf("pango before shape recti x=%d,y=%d,w=%d,h=%d\n",        //~vX03R~
	recti.x,recti.y,recti.width,recti.height);                     //~vX03I~
//dprintf("pango before shape rectl x=%d,y=%d,w=%d,h=%d\n",        //~vX03R~
	rectl.x,rectl.y,rectl.width,rectl.height);                     //~vX03I~
//shape attr                                                       //~vX03I~
	recti.x=0;                                                     //~vX03M~
	recti.y=0;//-Mfontheight*PANGO_SCALE;                          //~vX03R~
	recti.width=Mfontwidth*PANGO_SCALE;                            //~vX03R~
	recti.height=Mfontheight*PANGO_SCALE;                          //~vX03M~
	rectl.x=0;                                                     //~vX03M~
	rectl.y=0;//-Mcellh*PANGO_SCALE;                               //~vX03R~
	rectl.width=Mcellw*PANGO_SCALE;                                //~vX03R~
	rectl.height=Mcellh*PANGO_SCALE;                               //~vX03R~
    ppa=pango_attr_shape_new(&recti,&rectl);                       //~vX03M~
//dprintf("pango  shape new recti x=%d,y=%d,w=%d,h=%d\n",          //~vX03R~
	recti.x,recti.y,recti.width,recti.height);                     //~vX03I~
//dprintf("pango shape new rectl x=%d,y=%d,w=%d,h=%d\n",           //~vX03R~
	rectl.x,rectl.y,rectl.width,rectl.height);                     //~vX03I~
    ppa->start_index=0;         //start byte offset                //~vX03M~
    ppa->end_index=G_MAXINT;    //end                              //~vX03M~
//  pango_attr_list_change(ppal,ppa);  //@@@@                      //~vX03R~
                                                                   //~vX03I~
//font attr                                                        //~vX03R~
    ppa=pango_attr_font_desc_new(Gfontdata[0].FDfontdesc);         //~vX03I~
    ppa->start_index=0;         //start byte offset                //~vX03I~
    ppa->end_index=G_MAXINT;    //end                              //~vX03I~
    pango_attr_list_change(ppal,ppa);                              //~vX03R~
                                                                   //~vX03I~
    Gppangoattrlistscreen=ppal;                                    //~vX03M~
    if (!ppalo)	                                                   //~vX03I~
        pango_layout_set_attributes(Gplayout,ppal); //monospacing  //~vX03I~
    pango_layout_get_extents(Gplayout,&recti,&rectl);              //~vX03I~
//dprintf("pango after shape recti x=%d,y=%d,w=%d,h=%d\n",         //~vX03R~
	recti.x,recti.y,recti.width,recti.height);                     //~vX03M~
//dprintf("pango after shape rectl x=%d,y=%d,w=%d,h=%d\n",         //~vX03R~
	rectl.x,rectl.y,rectl.width,rectl.height);                     //~vX03M~
}                                                                  //~vX03I~
#endif                                                             //~vX03R~
#ifndef ARM     //do at java from gxedlg_init                      //~v@@@I~
//===============================================================================//~v003I~
// screen font                                                     //~vXXER~
//===============================================================================//~v003I~
int  xxemain_createfont()                                          //~vXXER~
{                                                                  //~v003I~
	int oldh,oldw;                                                 //~v69UI~
//************************************                             //~v003I~
	oldh=Mcellh;                                                   //~v69UI~
	oldw=Mcellw;                                                   //~v69UI~
	ugetfontmetrics(Gfontdata[0].FDfontdesc,&Mfontwidth,&Mfontheight,&Mmonospace);//~vXXER~
    if (Mcellh0)                                                   //~vX03M~
    	Mcellh=Mcellh0;                                            //~vX03M~
    else                                                           //~vX03M~
    	Mcellh=Mfontheight;                                        //~vX03M~
    if (Mcellw0)                                                   //~vX03M~
    	Mcellw=Mcellw0;                                            //~vX03M~
    else                                                           //~vX03M~
    	Mcellw=Mfontwidth;                                         //~vX03M~
	if (Mfontheight<Mcellh-DEF_TEXTCSR_HEIGHT)                     //~vXXEI~
    {                                                              //~vXXEI~
        Mfontoffsy=(Mcellh-DEF_TEXTCSR_HEIGHT-Mfontheight);        //~vXXEI~
//      Mfontheight=Mcellh-Mfontoffsy;                             //~vXXER~
    }                                                              //~vXXEI~
    else                                                           //~vXXEI~
    	Mfontoffsy=0;                                              //~vXXEI~
    if (Mfontwidth<Mcellw)                                         //~vXXEI~
    	Mfontoffsx=(Mcellw-Mfontwidth)/2;                          //~vXXER~
    else                                                           //~vXXEI~
    	Mfontoffsx=0;                                              //~vXXEI~
//setup for monospace                                              //~vX03I~
    dprintf("create font name=%s,w=%d,h=%d,offsx=%d,offsy=%d\n",Gfontdata[0].FDfontname,Mfontwidth,Mfontheight,Mfontoffsx,Mfontoffsy);//~vX03R~
#ifdef XXX                                                         //~vX03I~
    setlayoutattr();                                               //~vX03R~
#else                                                              //~vX03I~
    pango_layout_set_font_description(Gplayout,Gfontdata[0].FDfontdesc);//~vX03R~
#endif                                                             //~vX03I~
    dprintf("create font old w=%d,h=%d new w=%d,h=%d,desc=%p\n",oldw,oldh,Mcellw,Mcellh,Gfontdata[0].FDfontdesc);//~v73tR~
	if (oldh!=Mcellh||oldw!=Mcellw)	//col/row may chnged           //~v69UI~
    	usetresizehint(Mcellw,Mcellh,Mscrcmaxcol,Mscrcmaxrow);     //~v69VR~
    xxe_setsynfontface();                                          //~v780I~
	return 0;                                                      //~v003I~
}//xxemain_createfont                                              //~v69UR~
#endif //ARM                                                       //~v@@@I~
//===============================================================================//~v780I~
// change font                                                     //~v780I~
//===============================================================================//~v780I~
int  xxemain_setfont(int Pcase)                                    //~v780I~
{                                                                  //~v780I~
    PangoFontDescription *pfd;                                     //~v780I~
//************************************                             //~v780I~
	pfd=Gfontdata[0].FDfontdesc;                                   //~v780I~
	if (Pcase>=0                                                   //~v780R~
    &&  Pcase!=WXEFONTSTYLE_NOSYNTAX //not syntax highlight part   //~v780R~
	&&  Gsynfontdesc[Pcase])                                       //~v780I~
		pfd=Gsynfontdesc[Pcase];                                   //~v780I~
//  dprintf("set font case=%d\n",Pcase);                           //~v78BR~
//  dprintf("syn font toring=%s\n",pango_font_description_to_string((const PangoFontDescription*)(pfd)));//~v78BR~
	pango_layout_set_font_description(Gplayout,pfd);               //~v780I~
	return 0;                                                      //~v780I~
}//xxemain_setfont                                                 //~v780I~
//===============================================================================//~2901I~
//                                                                 //~2901I~
//===============================================================================//~2901I~
void xxemain_scrinvalidate(int Popt)                               //~vXXER~
{                                                                  //~vXXER~
//************************************                             //~2901I~
    if (Mwxeintf.WXEIxetermsw)	//xe term called already           //~v78DI~
    	return;                                                    //~v78DI~
dprintf("scrinvalidate opt=%d col=%d,row=%d \n",Popt,Mwxeintf.WXEIcsrposx,Mwxeintf.WXEIcsrposy);                           //~v69UR~//~vai5R~
    if (Popt & SCRINVO_FULLDRAW)                                   //~vXXER~
		Mfulldrawsw=1;	//reset                                    //~vXXEI~
	xxemain_draw();                                                //~vXXEI~
    if (Popt & SCRINVO_SYNCEXPOSE)                                 //~vXXEI~
    	uinvalidate(UINVO_SYNC);	//direct draw                  //~vXXER~
    else                                                           //~vXXEI~
		uinvalidate(0);	//queue request                            //~vXXER~
    scrruler(Mwxeintf.WXEIcsrposy,Mwxeintf.WXEIcsrposx);           //~v78BI~
    scrcsr();                                                      //~v78DR~
    return;                                                        //~2901I~
}//xxemain_scrinvalidate                                           //~vXXER~
//===============================================================================//~2922I~
//                                                                 //~2922I~
//===============================================================================//~2922I~
void xxemain_onsetfocus(void)                                      //~v69dR~
{                                                                  //~2922I~
    struct timeb ctm;                                              //~v69dR~
//*********************                                            //~v682I~
//dprintf("active onsetfocus\n");                                  //~v69VR~
//    if (Mxeinitsw)      	    //xe init called                   //~v69dR~
//		uredrawcaret();                                            //~v69dR~
//  Mswkillfocus=0;             //by frame window clicked          //~v63rR~
	ftime(&ctm);		//chk later lbdown                         //~v69dR~
	Msetfocustime=ctm.time;		//chk later lbdown                 //~v63rI~
	Msetfocusmsec=ctm.millitm;		//chk later lbdown             //~v63rI~
    return;                                                        //~2922I~
}//xxemain_onsetfocus                                              //~v69dR~
//===============================================================================//~2922I~
//                                                                 //~2922I~
//===============================================================================//~2922I~
void xxemain_onkillfocus(void)                                     //~v69dR~
{                                                                  //~2922I~
//************************************                             //~2922I~
//dprintf("active onkillfocus \n");                                //~v69VR~
//  if (Mxeinitsw)      	    //xe init called                   //~v69dR~
//  	DestroyCaret();                                            //~v69dR~
    Mswkillfocus=1;             //ignore 1st click after kill focus//~v63rI~
    return;                                                        //~2922I~
}//onkillfocus                                                     //~2922I~
//===============================================================================//~3102I~
//End command by popup menu                                        //~3102I~
//===============================================================================//~3102I~
int xxemain_scrend(void)                                           //~vXXER~
{                                                                  //~3102I~
    return xxemain_scrcmd(WXEOF_END,"");                           //~vXXER~
}//scrend                                                          //~3102I~
//===============================================================================//~3102I~
//Cancel command by popup menu                                     //~3102I~
//===============================================================================//~3102I~
int xxemain_scrcancel(void)                                        //~vXXER~
{                                                                  //~3102I~
    return xxemain_scrcmd(WXEOF_CANCEL,"");                        //~vXXER~
}//scrcancel                                                       //~3102I~
//===============================================================================//~3102I~
//End command by popup menu                                        //~3102I~
//===============================================================================//~3102I~
int xxemain_scrcmd(int Pfuncid,char *Pparm)                        //~vXXER~
{                                                                  //~3102I~
    int rc;                                                        //~3102I~
//************************************                             //~3102I~
    rc=wxe_otherfunc(Pfuncid,Pparm);                               //~3102R~
    if (rc==-1)                                                    //~3102I~
    	exitmain();                                                //~3102I~
	xxemain_scrinvalidate(0);                                      //~vXXER~
    return rc;                                                     //~3102R~
}//xxemain_scrcmd                                                  //~vXXER~
//===============================================================================//~v51wI~
//exlorler(nautilus) kick                                          //~vXXER~
//===============================================================================//~v51wI~
int xxemain_scrstrcmd(char *Pcmd,char *Pparm)                      //~vXXER~
{                                                                  //~v51wI~
    int rc;                                                        //~v51wI~
//************************************                             //~v51wI~
    rc=wxe_strcmd(Pcmd,Pparm);                                     //~v51wI~
    if (rc==-1)                                                    //~v51wI~
    	exitmain();                                                //~v51wI~
	xxemain_scrinvalidate(0);                                      //~vXXER~
    return rc;                                                     //~v51wI~
}//xxemain_scrstrcmd                                               //~vXXER~
//===============================================================================//~v55uI~
//open by application                                              //~v55uI~
//===============================================================================//~v55uI~
int xxemain_onfileopenwith(void)                                   //~vXXER~
{                                                                  //~v55uI~
    int rc;                                                        //~v55uI~
//************************************                             //~v55uI~
    rc=wxe_onfileopenwith();                                       //~v55uI~
    if (!rc)                                                       //~v55uI~
	    xxemain_scrinvalidate(0);                                  //~vXXER~
    return rc;                                                     //~v55uI~
}//onfileopenwith                                                  //~v55uI~
#ifndef ARM                                                        //~v@@@I~
//===============================================================================//~vXXEI~
//display preedit string                                           //~vXXER~
//===============================================================================//~vXXEI~
int xxemain_scrpreedit(char *Pstr,int Plen,int Pcsrpos)            //~vXXEI~
{                                                                  //~vXXEI~
//static int Smaxtextlen,Ssplitlen;                                //~v79DR~
static int Smaxtextlen;                                            //~v79DI~
void  scrpreeditbrush(RECT *Pprect);                               //~vXXER~
//int   scrpreedittext(RECT *Pprect,char *Pstr,int Plen);          //~v79DR~
int scrpreedittext(RECT *Pprect,char *Pstr,int Plen,int Putflen,int Pcsrpos,int *Ppoutcharcnt);//~v79DR~
//  int row,col,len,writelen,textlen,maxlinech,splitlen;           //~v79DR~
    int row,col,len,writelen,textlen,maxlinech;                    //~v79DI~
    RECT rect;                                                     //~vXXER~
    char *pstr;                                                    //~vXXEI~
    int utflen,charcnt,strwidth,csrpos;                            //~v79DR~
//************************************                             //~vXXEI~
  dprintf("scrpreedit str=%s,len=%d,csrpos=%d,Smaxtextlen=%d\n",Pstr,Plen,Pcsrpos,Smaxtextlen);//~v79DR~
//  if (Plen+Ssplitlen>Smaxtextlen)	//to clear pre-edit area when backspace used//~v79DR~
//  	Smaxtextlen=Plen+Ssplitlen;                                //~v79DR~
//  if (strwidth+Ssplitlen>Smaxtextlen)	//to clear pre-edit area when backspace used//~v79DR~
//  	Smaxtextlen=strwidth+Ssplitlen;                            //~v79DR~
    row=Mwxeintf.WXEIcsrposy;                                      //~vXXEM~
    col=Mwxeintf.WXEIcsrposx;                                      //~vXXEM~
    if (!Plen)                                                     //~vXXEI~
    {                                                              //~vXXEI~
	    Smaxtextlen=0;	//to clear pre-edit area when backspace used//~vXXEI~
//      Ssplitlen=0;                                               //~v79DR~
		xxemain_scrinvalidate(0);	//clear preedit string         //~vXXEI~
//  	 dprintf("scrpreedit clear\n");                             //~vX03R~
        return 0;                                                  //~vXXEI~
    }                                                              //~vXXEI~
//bkg fill                                                         //~vXXEI~
//	textlen=Smaxtextlen;                                           //~v79DR~
    maxlinech=Mscrcmaxcol-col;                                     //~vXXER~
    utfgetstrwidth(0,Pstr,Plen,&len,0/*out utflen*/);              //~v79DI~
UTRACED("imepreedit str",Pstr,Plen);                               //~va1cR~
UTRACEP("ime str width=%d\n",len);                                 //~va1cR~
    dprintf("strwidth=%d\n",len);                                  //~v79DI~
    strwidth=len;                                                  //~v79DI~
    if (len>maxlinech)	//dbcs may be cut                          //~v79DI~
    {                                                              //~v79DI~
    	len=maxlinech;                                             //~v79DI~
	    utfgetstrwidth(UTCO_MAXCOL|UTCO_DBCSBACK/*get len before split dbcs*/,Pstr,Plen,&len,0);//~v79DI~
#ifdef UTF8SUPPH                                                   //~va1cR~
        len=strwidth+(maxlinech-len);	//fill space for fold space//~va1cR~
#else                                                              //~va1cR~
        len=strwidth+(len!=maxlinech);	//+1 if dbcs split         //~v79DI~
#endif                                                             //~va1cR~
    }                                                              //~v79DI~
    if (len>Smaxtextlen)	//to clear pre-edit area when backspace used//~v79DI~
   		Smaxtextlen=len;                                           //~v79DI~
	textlen=Smaxtextlen;	//brush width                          //~v79DI~
    dprintf("bkgd clear len=%dn",textlen);                         //~v79DI~
    for (;;)                                                       //~vXXEI~
    {                                                              //~vXXEI~
		INVALIDATE_LINE(row);		//flag line is updated to clear later//~vXXEI~
        len=min(textlen,maxlinech);                                //~v79zR~
dprintf("preedit Makerect len=%d,textlen=%d\n",len,textlen);       //~v79DR~
    	MAKE_TEXT_RECT(&rect,row,col,len);                         //~v79zR~
		scrpreeditbrush(&rect);                                    //~vXXER~
    	textlen-=len;                                              //~v79zR~
        if (textlen<=0)                                            //~v79zR~
        	break;                                                 //~vXXEI~
//      row++;                                                     //~vXXER~
//      if (row>=Mscrcmaxrow)                                      //~v79DR~
//      	break;                                                 //~v79DR~
        col=0;                                                     //~vXXEI~
        maxlinech=Mscrcmaxcol;                                     //~vXXER~
    }                                                              //~vXXEI~
//text fill                                                        //~vXXEI~
    row=Mwxeintf.WXEIcsrposy;                                      //~vXXEI~
    col=Mwxeintf.WXEIcsrposx;                                      //~vXXEI~
dprintf("csrposx=%d,Ppos=%d\n",col,Pcsrpos);                       //~v79DR~
//  textlen=Plen;                                                  //~v79DR~
    textlen=strwidth;                                              //~v79DI~
    maxlinech=Mscrcmaxcol-col;                                     //~vXXER~
    pstr=Pstr;                                                     //~vXXEI~
//  splitlen=0;                                                    //~v79DR~
	csrpos=Pcsrpos;                                                //~v79DI~
    for (;;)                                                       //~vXXEI~
    {                                                              //~vXXEI~
//      len=min(textlen,maxlinech);                                //~v79DR~
		len=maxlinech;	//right boundary                           //~v79DI~
	    utfgetstrwidth(UTCO_MAXCOL|UTCO_DBCSBACK,pstr,0,&len,&utflen);//~v79DI~
#ifdef UTF8SUPPH                                                   //~va1cR~
      if (!utflen)                                                 //~va1cR~
      {                                                            //~va1cR~
      	writelen=0;                                                //~va1cR~
        charcnt=0;                                                 //~va1cR~
      }                                                            //~va1cR~
      else                                                         //~va1cR~
#endif                                                             //~va1cR~
      {                                                            //~va1cR~
    	MAKE_TEXT_RECT(&rect,row,col,len);                         //~v79zR~
//  	writelen=scrpreedittext(&rect,pstr,len);                   //~v79DR~
    	writelen=scrpreedittext(&rect,pstr,len,csrpos,utflen,&charcnt);//~v79DR~
        if (!writelen)                                             //~vXXEI~
        	break;		//protect loop                             //~vXXEI~
      }                                                            //~va1cR~
//      if (writelen<len)   //dbcs splitted                        //~v79DR~
//          splitlen+=(len-writelen);                              //~v79DR~
    	textlen-=writelen;                                         //~vXXEI~
        csrpos-=charcnt;                                           //~v79DR~
dprintf("csrpos parm=%d,curr==%d,charcnt=%d\n",Pcsrpos,csrpos,charcnt);//~va1cR~
        if (textlen<=0)                                            //~vXXEI~
        	break;                                                 //~vXXEI~
//      pstr+=writelen;                                            //~v79DR~
        pstr+=utflen;                                              //~v79DR~
//      row++;                 //wrap in the line                  //~vXXER~
//      if (row>=Mscrcmaxrow)                                      //~vXXER~
//        	break;                                                 //~vXXER~
        col=0;                                                     //~vXXEI~
        maxlinech=Mscrcmaxcol;                                     //~vXXER~
    }                                                              //~vXXEI~
//  if (splitlen>Ssplitlen)                                        //~v79DR~
//      Ssplitlen=splitlen;                                        //~v79DR~
	uinvalidate(0);	//queue expose req                             //~vXXER~
    return 0;                                                      //~vXXEI~
}//xxemain_scrpreedit                                              //~vXXEI~
//===============================================================================//~vXXEI~
//draw preedit string text                                         //~vXXEI~
//===============================================================================//~vXXEI~
//int scrpreedittext(RECT *Pprect,char *Pstr,int Plen)             //~v79DR~
int scrpreedittext(RECT *Pprect,char *Pstr,int Plen,int Pcsrpos,int Putflen,int *Ppoutcharcnt)//~v79DR~
{                                                                  //~vXXEI~
	void  drawimtext(int Px,int Py,char *Pdata,int Plen);          //~vXXEI~
	char *pdata;                                                   //~vXXEI~
//  int ii,xx,yy,dbcssw,len,xxcell,writelen=0;                     //~v79DR~
    int ii,xx,yy,xxcell,writelen=0;                                //~v79DI~
    int charcnt,chklen,chwidth,right,rc,rlen,csrpos;               //~v79DR~
//************************************                             //~vXXEI~
dprintf("preedittext str=%s,width=%d,csrpos=%d,utflen=%d\n",Pstr,Plen,Pcsrpos,Putflen);//~v79DI~
    pdata=Pstr;                                                    //~vXXEI~
	xx=Pprect->left+Mfontoffsx;                                    //~vXXEI~
	yy=Pprect->top+Mfontoffsy;                                     //~vXXEI~
	xxcell=Pprect->left;                                           //~vXXEI~
//if (Gudbcschk_flag & UDBCSCHK_UTF8)  //ja_JP.UTF-8               //~v79DR~
//{                                                                //~v79DR~
//  writelen=Plen;                                                 //~v79zR~
//  drawimtext(xx,yy,pdata,writelen);                              //~v79DR~
//}                                                                //~v79DR~
//else                                                             //~v79DR~
//{                                                                //~v79DR~
//  dbcssw=0;                                                      //~v79DR~
//  for (ii=0;ii<Plen;ii++,pdata++,xx+=Mcellw,xxcell+=Mcellw)      //~v79DR~
//  {                                                              //~v79DR~
//  	if (dbcssw)                                                //~v79DR~
//      {                                                          //~v79DR~
//      	dbcssw=0;                                              //~v79DR~
//          continue;                                              //~v79DR~
//      }                                                          //~v79DR~
//      if (udbcschk(*pdata))                                      //~v79DR~
//      {                                                          //~v79DR~
//          if (writelen+2>Plen)                                   //~v79DR~
//              break;                                             //~v79DR~
//      	dbcssw=1;                                              //~v79DR~
//  		drawimtext(xx,yy,pdata,2);                             //~v79DR~
//          len=2;                                                 //~v79DR~
//      }                                                          //~v79DR~
//      else                                                       //~v79DR~
//          len=1;                                                 //~v79DR~
//  	drawimtext(xx,yy,pdata,len);                               //~v79DR~
//      writelen+=len;                                             //~v79DR~
//  }                                                              //~v79DR~
//}                                                                //~v79DR~
    charcnt=0;                                                     //~v79DR~
    csrpos=0;                                                      //~v79DI~
    rlen=Putflen;                                                  //~v79DI~
    for (ii=0;ii<Plen;ii+=chwidth,pdata+=chklen,xx+=right,csrpos++)//~v79DI~
    {                                                              //~v79DI~
        rc=utfmb2wc(0,pdata,rlen,0/*out ucs*/,&chklen,&chwidth);   //~v79DI~
        right=Mcellw;                                              //~v79DI~
        if (!rc)                                                   //~v79DI~
        {                                                          //~v79DI~
            if (chwidth==2)   //dbcs                               //~v79DI~
            {                                                      //~v79DI~
                if (ii+1>=Plen)                                    //~v79DI~
                    break;                                         //~v79DI~
                right+=Mcellw;                                     //~v79DI~
            }                                                      //~v79DI~
            else                                                   //~v79DI~
#ifdef UTF8SUPPH                                                   //~va1cR~
            if (chwidth>UDBCSCHK_DBCSSZ)   //dbcs sz is 3 or 4     //~va1cR~
            {                                                      //~va1cR~
                if (ii+chwidth>Plen)                               //~va1cR~
                    break;                                         //~va1cR~
                right+=Mcellw*(chwidth-1);  //display by byte length//~va1cR~
            }                                                      //~va1cR~
            else                                                   //~va1cR~
#endif            	                                               //~va1cR~
            if (chwidth!=1)                                        //~v79DI~
                right=0;                                           //~v79DI~
        }                                                          //~v79DI~
		drawimtext(xx,yy,pdata,chklen);                            //~v79DI~
dprintf("im char write csrpos=%d,chklen=%d,xx=%d,chwidth=%d\n",csrpos,chklen,xx,chwidth);//~va1cR~
UTRACED("drwaimtext",pdata,chklen);                                //~va1cR~
        if (csrpos==Pcsrpos)    //intermediate by left arrow-key   //~v79DI~
        	drawimcsr(0,xx,yy);                                    //~v79DI~
        charcnt++;                                                 //~v79DR~
        rlen-=chklen;                                              //~v79DI~
        writelen+=chwidth;                                         //~v79DI~
    }                                                              //~v79DI~
  	if (csrpos==Pcsrpos)    //at string end                        //~v79DR~
  	{                                                              //~v79DR~
  		drawimcsr(0,xx,yy);                                        //~v79DR~
        dprintf("eos cursol pos=%d\n",csrpos);                     //~v79DR~
    }                                                              //~v79DR~
    *Ppoutcharcnt=charcnt;                                         //~v79DR~
    return writelen;                                               //~vXXER~
}//scrpreedittext                                                  //~vXXER~
//===============================================================================//~v79DR~
//draw im csursor                                                  //~v79DR~
//===============================================================================//~v79DR~
int drawimcsr(int Popt,int Pxx,int Pyy)                            //~v79DR~
{                                                                  //~v79DR~
    CPoint pt[2];                                                  //~v79DR~
//******************                                               //~v79DR~
    pt[0].x=Pxx;                                                   //~v79DR~
    pt[1].x=Pxx;                                                   //~v79DR~
    pt[0].y=Pyy;                                                   //~v79DR~
    pt[1].y=Pyy+Mcellh;                                            //~v79DR~
dprintf("drawimecsr xx=%d,yy=%d,cellh=%d\n",Pxx,Pyy,Mcellh);       //~v79DR~
    gdk_draw_lines(Gppixmap,Gpgcim,pt,2);   //vertical line        //~v79DR~
    return 0;                                                      //~v79DR~
}//drawimcsr                                                       //~v79DR~
//===============================================================================//~vXXEI~
//draw im text char                                                //~vXXEI~
//===============================================================================//~vXXEI~
void  drawimtext(int Px,int Py,char *Pdata,int Plen)               //~vXXEI~
{                                                                  //~vXXEI~
//  GError *errpos=NULL;                                           //~v69QR~
	char *ptext;                                                   //~vXXEI~
//	int utfrc,writelen;                                            //~v79DR~
  	int writelen;                                                  //~v79DI~
//****************************                                     //~vXXEI~
//dprintf("imtext write str=%s (%x),len=%d\n",Pdata,*Pdata,Plen);  //~vX03R~
//UTRACED("@@@drawimtext locale",Pdata,Plen);                      //~v69UR~
//  ptext=g_locale_to_utf8(Pdata,Plen,&readlen,&writelen,&errpos); //~v69QR~
//  PRINTGERR("drawimtext-g_locale_to_utf8",errpos);               //~v69QR~
//  ptext=csublocale2utf8(Pdata,Plen,0,&writelen);                 //~v69VR~
//  utfrc=csublocale2utf8(Pdata,Plen,0,&ptext,&writelen);          //~v79DR~
//  if (!ptext)                                                    //~v69VR~
//  if (utfrc>1)                                                   //~v79DR~
//  	return;                                                    //~v79DR~
    ptext=Pdata;                                                   //~v79DI~
    writelen=Plen;                                                 //~v79DI~
UTRACEP("@@@drawimtext x=%d,y=%d\n",Px,Py);                        //~va1cR~
UTRACED("@@@drawimtext utf8",ptext,writelen);                      //~v69sR~
    pango_layout_set_text(Gplayout,ptext,writelen);                //~vXXEI~
//dprintf("imtext write utfstr=%s (%x),len=%d\n",ptext,*ptext,writelen);//~vX03R~
    gdk_draw_layout_with_colors(Gppixmap,Gpgcim,Px,Py,Gplayout,&Gimfgcolor,&Gimbgcolor);//~vXXEI~
//if (ptext!=Pdata)	//converted                                    //~v69VR~
//if (!utfrc)	//converted                                        //~v79DR~
//  g_free(ptext);                                                 //~v79DR~
    return;                                                        //~vXXEI~
}//drawimtext                                                      //~vXXEI~
//===============================================================================//~vXXEI~
//draw background rectangle                                        //~vXXEI~
//===============================================================================//~vXXEI~
void  scrpreeditbrush(RECT *Pprect)                                //~vXXER~
{                                                                  //~vXXEI~
    int xx,yy,hh,ww,xx2,yy2;                                       //~vXXER~
    GdkPoint points[4];                                            //~vXXEI~
//************************************                             //~vXXEI~
//brush box                                                        //~vXXEI~
    gdk_gc_set_foreground(Gpgcim,&Gimbgcolor);                     //~vXXER~
	xx=Pprect->left;                                               //~vXXEI~
	yy=Pprect->top;                                                //~vXXEI~
	xx2=Pprect->right-1;                                           //~vXXER~
	yy2=Pprect->bottom-1;                                          //~vXXER~
	ww=xx2-xx;                                                     //~vXXEI~
	hh=yy2-yy;                                                     //~vXXEI~
dprintf("im brush xx=%d,yy=%d,ww=%d,hh=%d\n",xx,yy,ww,hh);         //~v79DR~
    gdk_draw_rectangle(Gppixmap,Gpgcim,TRUE,xx,yy,ww,hh);          //~vXXER~
//lim                                                              //~vXXEI~
    points[0].x=xx;                                                //~vXXEI~
    points[0].y=yy;                                                //~vXXEI~
    points[1].x=xx2;                                               //~vXXEI~
    points[1].y=yy;                                                //~vXXEI~
    points[2].x=xx2;                                               //~vXXEI~
    points[2].y=yy2;                                               //~vXXEI~
    points[3].x=xx;                                                //~vXXEI~
    points[3].y=yy2;                                               //~vXXEI~
    gdk_gc_set_foreground(Gpgcim,&Gimfgcolor);                     //~vXXER~
    gdk_draw_lines(Gppixmap,Gpgcim,points,4);   //edge box         //~vXXEI~
	return;                                                        //~vXXEI~
}//scrpreeditbrush                                                 //~vXXER~
#endif //ARM                                                       //~v@@@I~
//===============================================================================//~vb4AR~//+vb91I~
//write combining char by altch under UNICOMB UNPR and Ligature ON //~vb4AR~//+vb91I~
//return string width                                              //~vb4AI~//+vb91I~
//===============================================================================//~vb4AR~//+vb91I~
int drawtextstr_ligatureu8_nocombine(int Popt,CDC *Ppdc,GdkGC *Ppgc,int Px,int Py,GdkColor *Ppfg,GdkColor *Ppbg,char *Ppu8,int Pu8len,char *Pdddata,char *Pdddbcs,char *Plcdbcs,int Pddlen)//~vb4AR~//+vb91I~
{                                                                  //~vb4AR~//+vb91I~
    int xx,len,reslen,optusms,stru8len,strlclen,strwidth;          //~vb4AR~//+vb91I~
    int dbcsid,chsz,lenalt,lenu8;                                  //~vb4AR~//+vb91I~
//  char *pdddbcs,*plcdbcs,*pu8,*pdddbcso,*plcdbcso,*pu8o;         //~vb4AR~//~vb5eR~//+vb91I~
    char *pdddbcs,*plcdbcs,*pu8,          *plcdbcso,*pu8o;         //~vb5eI~//+vb91I~
    char wku8[8],wkdbcs[1]={0};                                    //~vb4AI~//+vb91I~
    GdkColor fgaltch={0,0/*r*/,0xffff/*g*/,0/*b*/};                //~vb4AR~//+vb91I~
//************************************                             //~vb4AR~//+vb91I~
	UTRACEP("%s:opt=%04x,xx=%d,yy=%d,fg=%04x,bg=%04x\n",UTT,Popt,Px,Py,Ppfg,Ppbg);//~vb4AR~//+vb91I~
	UTRACED("u8data",Ppu8,Pu8len);                                 //~vb4AR~//+vb91I~
	UTRACED("dddata",Pdddata,Pddlen);                              //~vb4AR~//+vb91I~
	UTRACED("dddbcs",Pdddbcs,Pddlen);                              //~vb4AR~//+vb91I~
	UTRACED("lcdbcs",Plcdbcs,Pddlen);                              //~vb4AR~//+vb91I~
    optusms=Popt;                                                  //~vb4AR~//+vb91I~
    xx=Px;                                                         //~vb4AI~//+vb91I~
    strlclen=0,stru8len=0;                                         //~vb4AI~//+vb91I~
    pu8o=Ppu8,plcdbcso=Plcdbcs;                                    //~vb4AR~//+vb91I~
    for (pu8=Ppu8,pdddbcs=Pdddbcs,plcdbcs=Plcdbcs,reslen=Pddlen;reslen>0;)//~vb4AI~//+vb91I~
    {                                                              //~vb4AR~//+vb91I~
        dbcsid=*pdddbcs;                                           //~vb4AI~//+vb91I~
        if (!dbcsid)                                               //~vb4AI~//+vb91I~
        {                                                          //~vb4AI~//+vb91I~
    		len=umemspnc(pdddbcs,0,reslen);                        //~vb4AR~//+vb91I~
        	strlclen+=len;                                         //~vb4AR~//+vb91I~
        	stru8len+=len;                                         //~vb4AR~//+vb91I~
            pu8+=len;                                              //~vb4AI~//+vb91I~
            pdddbcs+=len;                                          //~vb4AI~//+vb91I~
            plcdbcs+=len;                                          //~vb4AI~//+vb91I~
            reslen-=len;                                           //~vb4AI~//+vb91I~
        }                                                          //~vb4AI~//+vb91I~
        else                                                       //~vb4AI~//+vb91I~
        if (UDBCSCHK_ISUCSWIDTH0(dbcsid))//combining char detected //~vb4AI~//+vb91I~
        {                                                          //~vb4AI~//+vb91I~
            if (stru8len)                                          //~vb4AI~//+vb91I~
            {                                                      //~vb4AI~//+vb91I~
                UTRACED("pango_layout_set_text pu8",pu8o,stru8len);//~vb4AI~//+vb91I~
                pango_layout_set_text(Gplayout,pu8o,stru8len);     //~vb4AI~//+vb91I~
                usetmonospace_ligature(optusms,Gplayout,pu8o,plcdbcso,strlclen,Mcellw,&strwidth);//~vb4AI~//+vb91I~
                UTRACEP("%s:draw_layout_with_colors xx=%d,fg RGB=%04x,%04x,%04x\n",UTT,xx,Ppfg->red,Ppfg->green,Ppfg->blue);//~vb4AI~//+vb91I~
                gdk_draw_layout_with_colors(Ppdc,Ppgc,xx,Py,Gplayout,Ppfg,Ppbg);//~vb4AI~//+vb91I~
                xx+=strwidth;                                      //~vb4AI~//+vb91I~
            }                                                      //~vb4AI~//+vb91I~
            lenu8=UTF8CHARLEN(*pu8);                               //~vb4AI~//+vb91I~
            if (UTF_COMBINEMODE_NP())                              //~vb4AI~//+vb91I~
            {                                                      //~vb4AI~//+vb91I~
            	lenalt=uccvucs2utf(Gutfcombaltch,wku8);            //~vb4AI~//+vb91I~
    	        UTRACED("combineNP pango_layout_set_text wku8",wku8,lenalt);//~vb4AI~//+vb91I~
            	pango_layout_set_text(Gplayout,wku8,lenalt);       //~vb4AI~//+vb91I~
	            usetmonospace_ligature(optusms,Gplayout,wku8,wkdbcs,1,Mcellw,0/*strwidth:xx*/);//~vb4AI~//+vb91I~
            }                                                      //~vb4AI~//+vb91I~
            else                                                   //~vb4AI~//+vb91I~
            {                                                      //~vb4AI~//+vb91I~
    	        UTRACED("combine SPLIT pango_layout_set_text pu8",pu8,lenu8);//~vb4AR~//+vb91I~
            	pango_layout_set_text(Gplayout,pu8,lenu8);         //~vb4AI~//+vb91I~
	            usetmonospace_ligature(optusms,Gplayout,pu8,plcdbcs,1,Mcellw,0/*xx:strwidth*/);//~vb4AR~//+vb91I~
            }                                                      //~vb4AI~//+vb91I~
            UTRACEP("%s:draw_layout_with_colors xx=%d,altfg RGB r=%04x,g=%04x,b=%04x\n",UTT,xx,fgaltch.red,fgaltch.green,fgaltch.blue);//~vb4AI~//+vb91I~
            gdk_draw_layout_with_colors(Ppdc,Ppgc,xx,Py,Gplayout,&fgaltch,Ppbg);//~vb4AI~//+vb91I~
            xx+=Mcellw;                                            //~vb4AI~//+vb91I~
        	strlclen=0;                                            //~vb4AI~//+vb91I~
        	stru8len=0;                                            //~vb4AI~//+vb91I~
            pu8+=lenu8;                                            //~vb4AI~//+vb91I~
            pdddbcs++;                                             //~vb4AI~//+vb91I~
            plcdbcs++;                                             //~vb4AI~//+vb91I~
            reslen--;                                              //~vb4AI~//+vb91I~
                                                                   //~vb4AI~//+vb91I~
            pu8o=pu8;                                              //~vb4AI~//+vb91I~
//          pdddbcso=pdddbcs;                                      //~vb4AI~//~vb5eR~//+vb91I~
            plcdbcso=plcdbcs;                                      //~vb4AI~//+vb91I~
        }                                                          //~vb4AI~//+vb91I~
        else                                                       //~vb4AI~//+vb91I~
        {                                                          //~vb4AI~//+vb91I~
        	if (UDBCSCHK_ISUCSSBCS(dbcsid))//combining char detected//~vb4AI~//+vb91I~
                chsz=1;                                            //~vb4AI~//+vb91I~
            else                                                   //~vb4AI~//+vb91I~
            	chsz=2;                                            //~vb4AI~//+vb91I~
            lenu8=UTF8CHARLEN(*pu8);                               //~vb4AI~//+vb91I~
        	strlclen+=chsz;                                        //~vb4AI~//+vb91I~
        	stru8len+=lenu8;                                       //~vb4AI~//+vb91I~
            pu8+=lenu8;                                            //~vb4AI~//+vb91I~
            pdddbcs+=chsz;                                         //~vb4AI~//+vb91I~
            plcdbcs+=chsz;                                         //~vb4AI~//+vb91I~
            reslen-=chsz;                                          //~vb4AI~//+vb91I~
        }                                                          //~vb4AI~//+vb91I~
    }//for                                                         //~vb4AR~//+vb91I~
    if (stru8len)                                                  //~vb4AI~//+vb91I~
    {                                                              //~vb4AI~//+vb91I~
        UTRACED("pango_layout_set_text pu8:last",pu8o,stru8len);   //~vb4AI~//+vb91I~
        pango_layout_set_text(Gplayout,pu8o,stru8len);             //~vb4AI~//+vb91I~
        usetmonospace_ligature(optusms,Gplayout,pu8o,plcdbcso,strlclen,Mcellw,&strwidth);//~vb4AI~//+vb91I~
        UTRACEP("%s:draw_layout_with_colors xx=%d,fg RGB=%04x,%04x,%04x\n",UTT,xx,Ppfg->red,Ppfg->green,Ppfg->blue);//~vb4AI~//+vb91I~
        gdk_draw_layout_with_colors(Ppdc,Ppgc,xx,Py,Gplayout,Ppfg,Ppbg);//~vb4AI~//+vb91I~
        xx+=strwidth;                                              //~vb4AI~//+vb91I~
    }                                                              //~vb4AI~//+vb91I~
    UTRACEP("%s:return strwidth=%d\n",UTT,xx-Px);                  //~vb4AI~//+vb91I~
    return xx-Px;                                                  //~vb4AR~//+vb91I~
}//drawtextstr_ligatureu8_nocombine                                //~vb4AR~//+vb91I~
