//*CID://+vainR~:                             update#=  496;       //+vainR~
//***************************************************************************//~v516I~
//vain:130601 Axe:Bug:popup menu did not processed selected menu item//+vainI~
//v@@@:110610 Android(-DARM)                                       //~v@@@I~
//va8a:100926 (WXE)end wxp work at end print                       //~va8aI~
//va72:100801 LP64 wchar_t is 4byte on LP64                        //~va72I~
//va6y:000706 CUPs default printer is not same as gnome default    //~va6yI~
//va30:100126 combine mode of diacritical marks by A+/(saved to ini)//~va30I~
//va0S:091006_(BUG:XXE)if g_locale_from_utf8 fail and return null if LANG is not proper,the abend//~va0SI~
//v7ax:090414 (LNX)compile waring int:gsize                        //~v7axI~
//v79E:081013 (XXE)initialy show on top level(kick by terminal emulater case)//~v79EI~
//v79D:081011 (XXE) draw ime cursor                                //~v79DI~
//v79z:080916 DBCS tbl for C and K of CJK                          //~v79zI~
//v78D:080503 (XXE)csr write to scr direct(not pix map) to avoid old csr line draw//~v78DI~
//v78B:080426*(wxe,gxe)vertical ruler draw performance             //~v78BI~
//v76c:070617 (XXE)paste from PRIMARY clipboard by middle button down//~v76cI~
//v75W:070526 (XXE:BUG)assertion fail gnome_print_config_unref GNOME_IS_PRINT_CONFIG//~v75WI~
//v740:070201 (WXE/XXE)kde dose not report keypress event for S+C+F1;so use keyup event//~v740I~
//v71s:061022 (WXE/XXE)err msg when drag canceled by mouse lb release//~v71sI~
//v69w:060603 (XXE:FC5)abend by left key after UTF-8 dbcs input because key event string ptr is null//~v69wI~
//v69r:060531 (XXE)print debug message by GError                   //~v69rI~
//v69n:060530 (XXE:fc5)avoid Gtk warning "GError not initialized". //~v69nI~
//v69d:060518 (GXE:BUG)killfocus chk logic missing                 //~v69dI~
//v69a:060429 (BUG)English version crash by IM(at first -n9 option chk timing bug;force_english should be set after PRE_INIT,//~v69aI~
//            then chk imcontext gotten or not)                    //~v69aI~
//v685:060322 dnd support "file copy into the dir slected" and even in inter-split-screen.//~v685I~
//v66p:051026 (XXE)std paste support                               //~v66pI~
//v667:050823 (XXE)add printer selection dropdown list(effective only for CUPS)//~v667I~
//v666:050817 (XXE)for FC4:libgnome default setting is not syncronouse;so request gnome_print config_default twice after gtkmain//~v666I~
//vXXE:050611 new for XXE                                          //~vXXEI~
//***************************************************************************//~v516I~
// gxeview.c                                                       //~v59zR~
//***************************************************************************//~v59zI~
#include <stdlib.h>                                                //~v740I~
#include <string.h>                                                //~v740I~
#include <ctype.h>                                                 //~v740I~
//

#ifdef HAVE_CONFIG_H                                               //~v59zI~
#  include <config.h>                                              //~v59zI~
#endif                                                             //~v59zI~
#include <sys/timeb.h>                                             //~v69wI~
                                                                   //~v59zI~
#ifdef ARM                                                         //~v@@@I~
    #include <jnig.h>                                              //~v@@@I~
#endif //ARM                                                       //~v@@@I~
#ifndef ARM                                                        //~v@@@R~
#include <gnome.h>                                                 //~v59zI~
#include <libgnomeprint/gnome-print.h>                             //~vXXEI~
#include <libgnomeprint/gnome-print-job.h>                         //~vXXEI~
#ifdef UTF8UCS2                                                    //~va0SI~
#include <pango/pango-types.h>                                     //~va0SI~
#endif                                                             //~va0SI~
                                                                   //~v59zI~
#include "callbacks.h"                                             //~v59zI~
#include "interface.h"                                             //~v59zI~
#include "support.h"                                               //~v59zI~
#endif //ARM                                                       //~v@@@R~

#include <ulib.h>                                                  //~v59zI~
#include <uerr.h>                                                  //~vXXEI~
#include <uedit.h>                                                 //~vXXEI~
#include <utrace.h>                                                //~v69rI~
                                                                   //~v59zI~
#include <xxedef.h>                                                //~v59zR~
#include <xxeres.h>                                                //~v685I~
#include <xxemain.h>                                               //~v59zI~
#include <xxecsub.h>                                               //~v59zI~
#include <xxexei.h>                                                //~vXXEI~
#include <xxefile.h>                                               //~v685I~
#include "gxe.h"                                                   //~v59zI~
#include "gxemfrm.h"                                               //~v59zR~
#include "gxeview.h"                                               //~v59zI~
#include "gxedlg.h"                                                //~v59zI~
#include "gxepage.h"                                               //~vXXEI~

//***************************************************************************//~v59zI~
static GdkEventKey Spresskeyevent;                                 //~vXXER~
static UINT   Slbdowntime[2];                                      //~vXXEI~
static int    Slbdowntimeidx;                                      //~vXXEI~
#ifndef ARM                                                        //~v@@@M~
static GnomePrintJob     *Spgnomejob;                              //~vXXEI~
#endif //ARM                                                       //~v@@@I~
//***************************************************************************//~v59zI~
void gxeview_init(GtkWidget       *Ppwidget);                      //~v59zI~
void OnChar(GtkIMContext *Ppimcontext,const gchar *Pstr,GtkWidget *Ppwidget);//~vXXER~
void impreedit(GtkIMContext *Ppcontext,GtkWidget *Ppwidget);       //~vXXER~
int OnPreparePrinting(int Pnotpreviewsw);                          //~vXXEI~
int OnBeginPrinting(void);                                         //~vXXER~
void OnEndPrinting(int Phcopysw);                                  //~vXXEI~
void OnPrepareDC(int Ppageno);                                     //~vXXEI~
int chkrulerkey(UINT nChar);                                       //~v740I~
int gxeview_showtop(void);                                         //~v79EI~
//***************************************************************************//~v59zI~
//***************************************************************************//~v59zI~
// wxeview                                                         //~v59zI~
//***************************************************************************//~v59zI~
//********************************************************         //~v59zI~
// view init                                                       //~v59zI~
//********************************************************         //~v59zI~
void gxeview_init(GtkWidget       *Ppwidget)                       //~v59zI~
{                                                                  //~v59zI~
//*********************************                                //~v59zI~
#ifndef ARM                                                        //~v@@@I~
//ime                                                              //~vXXEI~
  if (Gpimcontext)                                                 //~v69aI~
  {                                                                //~v69aI~
    g_signal_connect(Gpimcontext,"commit",G_CALLBACK(OnChar),Ppwidget);//~vXXER~
	g_signal_connect(Gpimcontext,"preedit_changed", G_CALLBACK (impreedit),Ppwidget);//~vXXER~
    gtk_im_context_set_client_window(Gpimcontext,Ppwidget->window);//~vXXEM~
  }                                                                //~v69aI~
//pango                                                            //~vXXEM~
    Gppangocontext=gtk_widget_get_pango_context(Gpview);           //~vXXEM~
    Gppangolang=pango_context_get_language(Gppangocontext);        //~vXXEM~
#endif                                                             //~v@@@R~
	Gplayout=gtk_widget_create_pango_layout(Gpview,NULL);          //~vXXEM~
#ifndef ARM                                                        //~v@@@I~
#ifdef UTF8UCS2                                                    //~va0SI~
#ifndef  S390                                                      //~va30R~
#ifdef UTF8RTL     //rtl support                                   //~va30I~
    UTRACEP("context=%p,layout=%p,layoutcontext=%p\n",Gppangocontext,Gplayout,pango_layout_get_context(Gplayout));//force Left to Right//~va0SI~
    UTRACEP("before basedir=%d\n",pango_context_get_base_dir(Gppangocontext));//force Left to Right//~va0SI~
    pango_context_set_base_dir(Gppangocontext,PANGO_DIRECTION_LTR);//force Left to Right//~va0SI~
    UTRACEP("after basedir=%d\n",pango_context_get_base_dir(Gppangocontext));//force Left to Right//~va0SI~
UTRACEP("autodir before set=%d\n",pango_layout_get_auto_dir(Gplayout));//~va0SI~
//*currentry NO effect,but for the future                          //~va0SI~
    pango_layout_set_auto_dir(Gplayout,FALSE);  	//no auto dir by unicode type,LTR only by pango context_set_dir at gxeview//~va0SI~
UTRACEP("autodir after  set=%d\n",pango_layout_get_auto_dir(Gplayout));//~va0SI~
//UTRACEP("before single paragraph mode=%d\n",pango_layout_get_single_paragraph_mode(Gplayout));//~va0SI~
//        pango_layout_set_single_paragraph_mode(Gplayout,TRUE);      //no auto dir by unicode type,LTR only by pango context_set_dir at gxeview//~va0SI~
//UTRACEP("after single=%d\n",pango_layout_get_single_paragraph_mode(Gplayout));//~va0SI~
//UTRACEP("before setwidth width=%d\n",pango_layout_get_width(Gplayout));//~va0SI~
//        pango_layout_set_width(Gplayout,-1);    //no auto dir by unicode type,LTR only by pango context_set_dir at gxeview//~va0SI~
//UTRACEP("after  setwidth width=%d\n",pango_layout_get_width(Gplayout));//~va0SI~
    pango_layout_context_changed(Gplayout);                        //~va0SI~
#endif //UTF8RTL     //rtl support                                 //~va30R~
#endif //!S390                                                     //~va30I~
#endif                                                             //~va0SI~
#endif //ARM                                                       //~v@@@I~
	UTRACEP("gxeview_init call xxemain_init\n");                   //~v@@@I~
	xxemain_init(Mswgetinifail);                                   //~v59zR~
	return;                                                        //~v59zI~
}//gxeview_init                                                    //~v59zI~
//***************************************************************************//~v59zI~
// wxeview                                                         //~v59zI~
//***************************************************************************//~v59zI~
void gxeview_realize                    (GtkWidget       *Ppwidget)//~v59zR~
{                                                                  //~v59zI~
	UTRACEP("gxeview  realize;init\n");                            //~v@@@R~
    gxeview_init(Ppwidget);                                        //~vXXER~
    return;                                                        //~v59zI~
}                                                                  //~v59zI~
#ifndef ARM                                                        //~v@@@M~
void gxeview_unrealize                  (GtkWidget       *widget)  //~v59zI~
{                                                                  //~v59zI~
	 dprintf("main unrealize\n");                                   //~v667R~
  if (Gpimcontext)                                                 //~v69aI~
    gtk_im_context_set_client_window(Gpimcontext,NULL);            //~v59zR~
    return;                                                        //~v59zI~
}                                                                  //~v59zI~
int gxeview_focus_in             (GtkWidget       *widget,         //~v59zR~
                                        GdkEventFocus   *event)    //~v59zI~
{                                                                  //~v59zI~
	 dprintf("main focus in\n");                                    //~v667R~
  if (Gpimcontext)                                                 //~v69aI~
    gtk_im_context_focus_in(Gpimcontext);	//show im status window//~v59zI~
    xxemain_onsetfocus();                                          //~v69dI~
  	return FALSE;                                                  //~v59zI~
}                                                                  //~v59zI~
int gxeview_focus_out            (GtkWidget       *widget,         //~v59zR~
                                        GdkEventFocus   *event)    //~v59zI~
{                                                                  //~v59zI~
	 dprintf("main focus out\n");                                   //~v667R~
  if (Gpimcontext)                                                 //~v69aI~
    gtk_im_context_focus_out(Gpimcontext);                         //~v59zI~
    xxemain_onkillfocus();                                         //~v69dI~
  	return FALSE;                                                  //~v59zI~
}                                                                  //~v59zI~
void gxeview_configure            (GtkWidget       *Ppwidget,      //~v59zR~
                                        GdkEventConfigure *event)  //~v59zI~
{                                                                  //~v59zI~
    int ww,hh;                                                     //~v59zI~
//*********************************                                //~v59zI~
    ww=event->width;                                               //~v59zI~
    hh=event->height;                                              //~v59zI~
    dprintf("mainview configure event type=%d,w=%d,h=%d\n",event->type,ww,hh);//~v667R~
    ww=Ppwidget->allocation.width;                                 //~v59zI~
    hh=Ppwidget->allocation.height;                                //~v59zI~
    dprintf("mainview configure widget=%p,w=%d,h=%d\n",Ppwidget,ww,hh);//~v667R~
    if (ww!=Mscrwidth||hh!=Mscrheight)                             //~vXXEI~
    	xxemain_OnSize(ww,hh);                                     //~vXXER~
	gxeview_showtop();                                             //~v79EI~
    return;                                                        //~v59zI~
}                                                                  //~v59zI~
void gxeview_expose               (GtkWidget       *Ppwidget,      //~v59zR~
                                        GdkEventExpose  *Ppevent)  //~v59zR~
{                                                                  //~v59zI~
    if (Mwxeintf.WXEIxetermsw)	//xe term called already           //~v78DI~
        return;                                                    //~v78DI~
    dprintf("gxeview_expose: wifget=%p,ww=%d,hh=%d,x=%d,y=%d\n",   //~v667R~
            Ppwidget,Ppevent->area.width,                          //~vXXER~
            Ppevent->area.height,                                  //~vXXER~
            Ppevent->area.x,                                       //~vXXER~
            Ppevent->area.y);                                      //~vXXER~
	gdk_draw_drawable(Ppwidget->window,                            //~v59zI~
                      Ppwidget->style->fg_gc[GTK_WIDGET_STATE(Ppwidget)],//~v59zI~
                      Gppixmap,                                    //~vXXER~
                      Ppevent->area.x,Ppevent->area.y,             //~v59zI~
                      Ppevent->area.x,Ppevent->area.y,             //~v59zI~
                      Ppevent->area.width,Ppevent->area.height);   //~v59zI~
    scrruler(Mwxeintf.WXEIcsrposy,Mwxeintf.WXEIcsrposx);           //~v78BI~
    scrcsr();                                                      //~v78DI~
    return;                                                        //~v59zI~
}                                                                  //~v59zI~
//******************************************************************//~v79EI~
//*show at top level of window manager                             //~v79EI~
//******************************************************************//~v79EI~
int gxeview_showtop(void)                                          //~v79EI~
{                                                                  //~v79EI~
    int rc=0;                                                      //~v79EI~
//*********************************                                //~v79EI~
//  rc=gtk_window_activate_focus(GTK_WINDOW(Ppwidget));            //~v79EI~
//  gtk_window_set_focus(GTK_WINDOW(Ppwidget),Ppwidget);           //~v79EI~
    gtk_window_present(GTK_WINDOW(Gpframe));                       //~v79EI~
	dprintf("showtop rc=%d\n",rc);                                 //~v79EI~
    return rc;                                                     //~v79EI~
}//gxeview_showtop                                                 //~v79EI~
#endif //ARM                                                       //~v@@@I~
//******************************************************************//~v79EI~
int gxeview_buttondown           (GtkWidget       *Pwidget,        //~vXXER~
                                        GdkEventButton  *Pevent)   //~vXXER~
{                                                                  //~v59zI~
	CPoint pt;                                                     //~vXXEI~
//*****************************                                    //~vXXEI~
	 dprintf("btn press   event,type=%d,send=%d,time=%x,x=%d,y=%d,state=%x,button=%d,xroot=%d,yroot=%d\n",//~v@@@R~
    Pevent->type,Pevent->send_event,Pevent->time,                  //~vXXER~
    (int)(Pevent->x),                                              //~vXXER~
    (int)(Pevent->y),                                              //~vXXER~
    (int)(Pevent->state),                                          //~vXXER~
    (int)(Pevent->button),                                         //~vXXER~
    (int)(Pevent->x_root),                                         //~vXXER~
    (int)(Pevent->y_root));                                        //~vXXER~
    pt.x=(int)(Pevent->x);                                         //~vXXER~
    pt.y=(int)(Pevent->y);                                         //~vXXER~
    if (Pevent->button==1) //lb                                    //~vXXEI~
    {                                                              //~vXXEI~
    	Slbdowntimeidx=!Slbdowntimeidx;                            //~vXXEI~
    	Slbdowntime[Slbdowntimeidx]=Pevent->time;   //button push time//~vXXEI~
    	xxemain_mouselbdown(Pevent->state,pt);                     //~vXXER~
    }                                                              //~vXXEI~
    else                                                           //~vXXEI~
    {                                                              //~vXXEI~
    	Slbdowntime[0]=0;	//stop lbtn dblclick chk               //~vXXEI~
      	if (Pevent->button==3) //lb                                //~vXXER~
    		return xxemain_mouserbdown(Pevent->state,pt);          //~vXXER~
        else                                                       //~v76cI~
      	if (Pevent->button==2) //middle button                     //~v76cI~
    		return xxemain_mousembdown(Pevent->state,pt);          //~v76cI~
    }                                                              //~vXXEI~
    return FALSE;                                                  //~v59zI~
}                                                                  //~v59zI~
int gxeview_buttonup             (GtkWidget       *Pwidget,        //~vXXER~
                                        GdkEventButton  *Pevent)   //~vXXER~
{                                                                  //~v59zI~
	CPoint pt;                                                     //~vXXER~
    UINT lbdowntime,dblclickinterval;                              //~vXXER~
//*****************************                                    //~vXXEI~
	 dprintf("%s:btn up   event,type=%d,send=%d,time=%x,x=%d,y=%d,state=%x,button=%d,xroot=%d,yroot=%d\n",//~v667R~
    (char*)utimeedit("hh:mm:ss.mil",0),                            //~vXXER~
    Pevent->type,Pevent->send_event,Pevent->time,                  //~vXXEI~
    (int)(Pevent->x),                                              //~vXXER~
    (int)(Pevent->y),                                              //~vXXER~
    (int)(Pevent->state),                                          //~vXXER~
    (int)(Pevent->button),                                         //~vXXER~
    (int)(Pevent->x_root),                                         //~vXXER~
    (int)(Pevent->y_root));                                        //~vXXER~
    pt.x=(int)(Pevent->x);                                         //~vXXER~
    pt.y=(int)(Pevent->y);                                         //~vXXER~
    if (Pevent->button==1) //lb                                    //~vXXEI~
    {                                                              //~vXXEI~
    	lbdowntime=min(Slbdowntime[0],Slbdowntime[1]);             //~vXXEI~
    	dblclickinterval=(int)(Pevent->time-lbdowntime);           //~vXXEI~
	 	dprintf("btn up dblclickinterval=%x,lbdowntime=%x,eventtime=%x,limit=%d\n",dblclickinterval,lbdowntime,Pevent->time,Mdblclicktimer);//~v@@@R~
        if (dblclickinterval<Mdblclicktimer)	                   //~vXXER~
    		xxemain_mouselbdblclk(Pevent->state,pt);               //~vXXER~
        else                                                       //~vXXEI~
    		xxemain_mouselbup(Pevent->state,pt);                   //~vXXER~
    }                                                              //~vXXEI~
//  if (Pevent->button==3) //lb                                    //~vXXER~
//  {                                                              //~vXXER~
//  	if (Pevent->button==3) //lb                                //~vXXER~
//  		return xxemain_mouserbdown(Pevent->state,pt);          //~vXXER~
//  }                                                              //~vXXER~
    return FALSE;                                                  //~v59zI~
}                                                                  //~v59zI~
#ifndef ARM                                                        //~v@@@I~
int gxeview_mousewheel             (GtkWidget       *Pwidget,      //~vXXEI~
                                        GdkEventScroll  *Pevent)   //~vXXEI~
{                                                                  //~vXXEI~
	CPoint pt;                                                     //~vXXEI~
    int dir;                                                       //~vXXEI~
//*****************************                                    //~vXXEI~
	 dprintf("btn wheel event,type=%d,x=%d,y=%d,state=%x,direction=%d,xroot=%d,yroot=%d\n",//~v667R~
    Pevent->type,                                                  //~vXXEI~
    (int)(Pevent->x),                                              //~vXXEI~
    (int)(Pevent->y),                                              //~vXXEI~
    (int)(Pevent->state),                                          //~vXXEI~
    (int)(Pevent->direction),                                      //~vXXEI~
    (int)(Pevent->x_root),                                         //~vXXEI~
    (int)(Pevent->y_root));                                        //~vXXEI~
    pt.x=(int)(Pevent->x);                                         //~vXXER~
    pt.y=(int)(Pevent->y);                                         //~vXXER~
    dir=Pevent->direction;                                         //~vXXEI~
    switch (dir)                                                   //~vXXER~
    {                                                              //~vXXEI~
    case GDK_SCROLL_UP:                                            //~vXXEI~
    	dir=1;       //backword                                    //~vXXEI~
        break;                                                     //~vXXEI~
    case GDK_SCROLL_DOWN:                                          //~vXXEI~
    	dir=-1;      //forward                                     //~vXXEI~
        break;                                                     //~vXXEI~
    case GDK_SCROLL_LEFT:                                          //~vXXEI~
    	dir=2;                                                     //~vXXEI~
        break;                                                     //~vXXEI~
    case GDK_SCROLL_RIGHT:                                         //~vXXEI~
    	dir=-2;                                                    //~vXXEI~
        break;                                                     //~vXXEI~
    default:                                                       //~vXXEI~
    	return FALSE;                                              //~vXXEI~
    }                                                              //~vXXEI~
    xxemain_onmousewheel(Pevent->state,dir,pt);                    //~vXXER~
    return FALSE;                                                  //~vXXEI~
}                                                                  //~vXXEI~
#endif //ARM                                                       //~v@@@I~
int gxeview_motion               (GtkWidget       *Ppwidget,       //~vXXER~
                                        GdkEventMotion  *Pevent)   //~vXXER~
{                                                                  //~v59zI~
	CPoint pt;                                                     //~vXXEI~
//*****************************                                    //~vXXEI~
//    dprintf("mouse move event,type=%d,x=%d,y=%d,state=%x,hint=%d,axes=%d,xroot=%d,yroot=%d\n",//~v667R~
//    Pevent->type,                                                //~vXXER~
//    (int)(Pevent->x),                                            //~vXXER~
//    (int)(Pevent->y),                                            //~vXXER~
//    (int)(Pevent->state),                                        //~vXXER~
//    (int)(Pevent->is_hint),                                      //~vXXER~
//    (int)(Pevent->axes),                                         //~vXXER~
//    (int)(Pevent->x_root),                                       //~vXXER~
//    (int)(Pevent->y_root));                                      //~vXXER~
      pt.x=(int)(Pevent->x);                                       //~vXXER~
      pt.y=(int)(Pevent->y);                                       //~vXXER~
    xxemain_mousemove(Pevent->state,pt);                           //~vXXEI~
    return FALSE;                                                  //~v59zI~
}                                                                  //~v59zI~
#ifndef ARM                                                        //~v@@@I~
int gxeview_leave                (GtkWidget       *widget,         //~v59zI~
                                        GdkEventCrossing *event)   //~v59zR~
{                                                                  //~v59zI~
	 dprintf("main leave\n");                                       //~v667R~
//	if (event->window==Gmainwindow->window)                        //~v59zR~
//	   	gtk_grab_remove(widget);                                   //~v59zR~
    return FALSE;                                                  //~v59zI~
}                                                                  //~v59zI~
int gxeview_enter                (GtkWidget       *widget,         //~v59zI~
                                        GdkEventCrossing *event)   //~v59zR~
{                                                                  //~v59zI~
	 dprintf("main enter\n");                                       //~v667R~
//  if (event->window==Gmainwindow->window)                        //~v59zR~
//   	gtk_grab_add(widget);                                      //~v59zR~
    return FALSE;                                                  //~v59zI~
}                                                                  //~v59zI~
void gwxeview_ondraw(void)                                         //~v59zR~
{
//************************                                         //~2C03I~
//  CWxeDoc* pDoc = GetDocument();                                 //~v59zR~
//  Mwxemain.draw(pDC,pDoc);                                       //~v59zR~
}
/////////////////////////////////////////////////////////////////////////////
//BOOL CWxeView::OnPreparePrinting(CPrintInfo* pInfo)              //~vXXEI~
int OnPreparePrinting(int Pnotpreviewsw)                           //~vXXER~
{
	int rc;                                                        //~vXXEI~
//**********************:                                          //~vXXEI~
	 dprintf("on_print_activate,notpreview=%d \n",Pnotpreviewsw);   //~v667R~
//	rc=Mwxemain.onprepareprinting(pInfo);                          //~vXXEI~
	rc=xxemain_onprepareprinting(Pnotpreviewsw);//1:not preview    //~vXXEI~
    if (rc==FALSE)                                                 //~vXXEI~
    {                                                              //~va8aI~
    	afterprint(2);   //free work,2:cancel                      //~va8aI~
    	return FALSE;                                              //~vXXEI~
    }                                                              //~va8aI~
    return rc;                                                     //~vXXEI~
}
//vod CWxeView::OnBeginPrinting(CDC* pDC, CPrintInfo* pInfo)       //~vXXER~
int  OnBeginPrinting(void)                                         //~vXXER~
{
    char *pori;                                                    //~vXXEI~
    gdouble fw,fh;                                                 //~vXXEI~
//*************************                                        //~vXXEI~
//  if (Gpprtconf)                                                 //~v75WR~
//  	gnome_print_config_unref(Gpprtconf);//freeed in uprtgetconfig//~v75WR~
    dprintf("beginprint\n");                                       //~v667R~
    Gpprtconf=(GnomePrintConfig*)uprtgetconfig((void*)Gpprtconf,Mprinter);//~v667R~
    if (Mhcopysw)	//prtscr                                       //~vXXEI~
      	if (Mimageprtscr)                                          //~vXXEI~
		    Gppixmapprtscr=gdk_pixmap_new(Gpview->window,Mscrwidth,Mscrheight,-1);//for prtscr draw pixmap//~vXXER~
    Spgnomejob=gnome_print_job_new(Gpprtconf);                     //~vXXEI~
    Gpprtctxt=gnome_print_job_get_context(Spgnomejob);             //~vXXER~
	if (Mlandscape)                                                //~vXXER~
	    pori="R90";                                                //~vXXEM~
    else                                                           //~vXXEM~
	    pori="R0";                                                 //~vXXEM~
	gnome_print_config_set(Gpprtconf,GNOME_PRINT_KEY_PAGE_ORIENTATION,pori);//~vXXEI~
    dprintf("xxeprt:set page orientation=%s\n",pori);              //~v667R~
	gnome_print_job_get_page_size_from_config(Gpprtconf,&fw,&fh);  //~vXXEI~
    dprintf("gnome page size from config=w=%lf,h=%lf\n",fw,fh);    //~v667R~
                                                                   //~vXXEI~
//    ll=0.0;                                                      //~vXXER~
//    gnome_print_config_set_length(Gpprtconf,GNOME_PRINT_KEY_PAGE_MARGIN_LEFT,ll,pgpu);//~vXXER~
//    gnome_print_config_set_length(Gpprtconf,GNOME_PRINT_KEY_PAGE_MARGIN_RIGHT,ll,pgpu);//~vXXER~
//    gnome_print_config_set_length(Gpprtconf,GNOME_PRINT_KEY_PAGE_MARGIN_TOP,ll,pgpu);//~vXXER~
//    gnome_print_config_set_length(Gpprtconf,GNOME_PRINT_KEY_PAGE_MARGIN_BOTTOM,ll,pgpu);//~vXXER~
//                                                                 //~vXXER~
//    gnome_print_config_get_length(Gpprtconf,GNOME_PRINT_KEY_PAGE_MARGIN_LEFT,&ll,&pgpu);//~vXXER~
//    dprintf("page margin left=%lf,unit name=%s,unittobase=%lf\n",ll,pgpu->name,pgpu->unittobase);//~v667R~
//    gnome_print_config_get_length(Gpprtconf,GNOME_PRINT_KEY_PAGE_MARGIN_RIGHT,&ll,&pgpu);//~vXXER~
//    dprintf("page margin right=%lf,unit name=%s,unittobase=%lf\n",ll,pgpu->name,pgpu->unittobase);//~v667R~
//    gnome_print_config_get_length(Gpprtconf,GNOME_PRINT_KEY_PAGE_MARGIN_TOP,&ll,&pgpu);//~vXXER~
//    dprintf("page margin top=%lf,unit name=%s,unittobase=%lf\n",ll,pgpu->name,pgpu->unittobase);//~v667R~
//    gnome_print_config_get_length(Gpprtconf,GNOME_PRINT_KEY_PAGE_MARGIN_BOTTOM,&ll,pgpu);//~vXXER~
//    dprintf("page margin bottom=%lf,unit name=%s,unittobase=%lf\n",ll,pgpu->name,&pgpu->unittobase);//~v667R~
                                                                   //~vXXEI~
	return 0;                                                      //~vXXER~
}
//void CWxeView::OnEndPrinting(CDC* pDC, CPrintInfo* pInfo)        //~vXXEI~
void OnEndPrinting(int Phcopysw)                                   //~vXXER~
{
	GdkPixbuf *pixbuf;                                             //~vXXEM~
    int ww,hh,rowstride;                                           //~vXXEI~
    UCHAR *pixbufdata;                                             //~vXXEI~
//**************************************                           //~vXXEI~
//	Mwxemain.onendprinting(pDC,pInfo);                             //~vXXEI~
	xxemain_onendprinting();                                       //~vXXEI~
	if (Phcopysw)       //Mhcopysw creared at prtscr               //~vXXEI~
    {                                                              //~vXXEI~
    	if (Mimageprtscr)                                          //~vXXEI~
        {                                                          //~vXXEI~
            pixbuf=gdk_pixbuf_get_from_drawable(NULL,Gppixmapprtscr,NULL,0,0,0,0,-1/*Mscrwidth*/,-1/*Mscrheight*/);//~vXXER~
            hh=gdk_pixbuf_get_height(pixbuf);                      //~vXXER~
            ww=gdk_pixbuf_get_width(pixbuf);                       //~vXXER~
            rowstride=gdk_pixbuf_get_rowstride(pixbuf);            //~vXXER~
            pixbufdata=gdk_pixbuf_get_pixels(pixbuf);              //~vXXER~
            dprintf("pixbuff w=%d,h=%d,rowstride=%d,scrw=%d,scrh=%d\n",ww,hh,rowstride,Mscrwidth,Mscrheight);//~v667R~
            gnome_print_rgbimage(Gpprtctxt,pixbufdata,ww,hh,rowstride);//~vXXER~
            g_object_unref(G_OBJECT(pixbuf));                      //~vXXER~
        }                                                          //~vXXEI~
    }                                                              //~vXXEI~
	gnome_print_job_close(Spgnomejob);                             //~vXXEI~
	gnome_print_job_print(Spgnomejob);                             //~vXXEI~
    g_object_unref(Gpprtctxt);                                     //~vXXER~
    g_object_unref(Spgnomejob);                                    //~vXXEI~
    if (Mhcopysw)	//prtscr                                       //~vXXEI~
    	g_object_unref(Gppixmapprtscr);                            //~vXXEI~
    g_object_unref(Gpprtconf);                                     //~va6yI~
    Gpprtconf=0;    //avoid SIGPIPE at printque changed            //~va6yI~
}

#ifndef XXE                                                        //~vXXEM~
/////////////////////////////////////////////////////////////////////////////

int gxeview_Create(void)                                           //~v59zR~
{
    return 0;                                                      //~v59zI~
}
#endif                                                             //~vXXER~
#endif //ARM                                                       //~v@@@R~
//**********************************                               //~vXXEM~
// key down                                                        //~vXXEM~
//**********************************                               //~vXXEM~
int  gxeview_OnKeyDown(GtkWidget       *Ppwidget,                  //~vXXER~
                                        GdkEventKey     *Ppevent)  //~vXXEM~
{
    char ch,*pstr="";                                              //~v69wI~
#ifndef NOTRACE                                                    //~v69wR~
    struct timeb tb;                                               //~v69wR~
    static int Setkbdown;                                          //~v78BI~
#endif                                                             //~v69wI~
    int rc;                                                        //~v78BR~
    int rulerid;                                                   //~v740I~
//**********************                                           //~v69wI~
//    UTRACEP("OnKeyDown ch=%x,rep=%d,flag=%08x\n",nChar,nRepCnt,nFlags);//~v59zR~
//                                                                 //~v59zR~
//    Mwxemain.onkeydown(nChar, nRepCnt, nFlags);                  //~v59zR~
//  char ch=*(Ppevent->string);                                    //~v69wR~
    if (Ppevent->length)    //string length not 0                  //~v69wI~
        pstr=Ppevent->string;                                      //~v69wI~
    ch=*pstr;                                                      //~v69wI~
    if (ch<0x20)                                                   //~vXXEM~
        ch='.';                                                    //~vXXEM~
    dprintf("key down state=%x,keyval=%x,scan=%x,len=%d,string=%c(x%02x)\n",//~v667R~
               Ppevent->state,Ppevent->keyval,                     //~vXXEM~
               Ppevent->hardware_keycode,Ppevent->length,ch,*pstr);//~v69wR~
    if (Ppevent->state & GDK_SHIFT_MASK                            //~v740I~
    &&  Ppevent->state & GDK_CONTROL_MASK)                         //~v740I~
    {                                                              //~v740I~
        rulerid=chkrulerkey(Ppevent->keyval);                      //~v740R~
        if (rulerid)                                               //~v740I~
        {                                                          //~v740I~
            xxemain_scrsetruler(rulerid);                          //~v740I~
            return TRUE;    //no kbdinput  process                 //~v740I~
        }                                                          //~v740I~
    }                                                              //~v740I~
    memcpy(&Spresskeyevent,Ppevent,sizeof(Spresskeyevent));	//for commit event//~vXXEM~
  if (Gpimcontext)                                                 //~v69aI~
  {                                                                //~v69aI~
    if (gtk_im_context_filter_keypress(Gpimcontext,Ppevent))       //~vXXEM~
    {                                                              //~vXXEM~
		 dprintf("keynpress IME  event\n");                         //~v667R~
        return TRUE;                                               //~vXXEM~
    }                                                              //~vXXEM~
  }                                                                //~v69aI~
//  return xxemain_onkeydown(Ppevent->keyval,Ppevent->hardware_keycode,Ppevent->state,Ppevent->string,Ppevent->length);//~v69wR~
UTSSTART(tb);                                                      //~v69wI~
//  return xxemain_onkeydown(Ppevent->keyval,Ppevent->hardware_keycode,Ppevent->state,pstr,Ppevent->length);//~v69wR~
    rc=xxemain_onkeydown(Ppevent->keyval,Ppevent->hardware_keycode,Ppevent->state,pstr,Ppevent->length);//~v69wI~
UTSEND(tb,Setkbdown);                                              //~v69wI~
UTRACEP("kbdown =%d\n",Setkbdown);                                 //~v69wI~
    return rc;                                                     //~v69wI~
}//gxeview_OnKeyDown                                               //~v69wR~
//===============================================================================//~v740M~
//rc:0:call defaultwindowproc                                      //~v740M~
//p1:event->keycode                                                //~v740M~
//p2:event->hardware_keycode                                       //~v740M~
//p3:event->state                                                  //~v740M~
//p4:event->string                                                 //~v740M~
//p5:event->length                                                 //~v740M~
//===============================================================================//~v740M~
int chkrulerkey(UINT nChar)                                        //~v740M~
{                                                                  //~v740M~
	int ch,ii;                                                     //~v740M~
//************************************                             //~v740M~
dprintf("chkrulerkey key=%x,optparm=%s\n",nChar,Mrulerkey);        //~v740M~
    if (nChar>=GDK_F1 && nChar<=GDK_F12)                           //~v740M~
        ch=(int)(nChar-GDK_F1+1);                                  //~v740M~
    else                                                           //~v740M~
        ch=toupper(nChar);                                         //~v740M~
    for (ii=0;ii<3;ii++)                                           //~v740M~
    	if (Mrulerkey[ii]==ch)                                     //~v740M~
        	return ii+1;                                           //~v740M~
    return 0;                                                      //~v740M~
}//xxemain_keydown                                                 //~v740M~
//********************************************************         //~vXXEM~
// im commit callback                                              //~vXXEM~
//********************************************************         //~vXXEM~
void OnChar(GtkIMContext *Ppimcontext,const gchar *Pstr,GtkWidget *Ppwidget)//~vXXER~
{                                                                  //~2908M~
//    UTRACEP("OnChar key ch=%x,rep=%d,flag=%08x\n",nChar,nRepCnt,nFlags);//~v59zR~
//                                                                 //~v59zR~
//    Mwxemain.onchar(nChar, nRepCnt, nFlags);                     //~v59zR~
//  int readlen,writelen,utf8len,len;                              //~v7axR~
    gchar *pstrlocale;                                             //~vXXEM~
    int len;                                                       //~va0QI~
#ifdef UTF8SUPPH                                                //~va0QR~//~va0SR~
#else                                                              //~va0SR~
    int utf8len;                                               //~v7axR~//~va0QI~
    gsize readlen,writelen;                                        //~v7axR~//~va0QM~
    GError  *perr=NULL;                                            //~v69nR~
#endif                                                             //~va0SR~
//*********************************                                //~vXXEM~
	len=strlen(Pstr);                                              //~v79zI~
	dprintf("IME commit string=%s,len=%d\n",Pstr,len);             //~v79zI~
  if (Gxxestat & GXXES_KBDNOUTF8)                                  //~v79zI~
  {                                                                //~v79zI~
  	 pstrlocale=(gchar*)Pstr;                                      //~v79zR~
     xxemain_onchar(Spresskeyevent.keyval,Spresskeyevent.hardware_keycode,Spresskeyevent.state,pstrlocale,len);//~v79zR~
  }                                                                //~v79zI~
  else                                                             //~v79zI~
  if (Gxxestat & GXXES_KBDIMEICONV)                                //~v79zR~
  {                                                                //~v79zI~
//env is UTF8                                                      //~va0QI~
  	 pstrlocale=(gchar*)Pstr;                                      //~v79zR~
     xxemain_onchar(Spresskeyevent.keyval,Spresskeyevent.hardware_keycode,Spresskeyevent.state,pstrlocale,-len/*iconv requiredID*/);//~v79zR~
  }                                                                //~v79zI~
  else                                                             //~v79zI~
  {                                                                //~v79zI~
#ifdef UTF8SUPPH                                                   //~va0SR~
  	pstrlocale=(gchar*)Pstr;                                       //~va0SR~
    xxemain_onchar(Spresskeyevent.keyval,Spresskeyevent.hardware_keycode,Spresskeyevent.state,pstrlocale,-len/*iconv requiredID*/);//~va0SR~
	dprintf("Envis Locale keyval=%02x,string=%s(x%02x),eventstr=%s \n",Spresskeyevent.keyval,Pstr,*Pstr,Spresskeyevent.string);//~va0QR~//~va0SR~
#else                                                              //~va0SR~
	utf8len=g_utf8_strlen(Pstr, -1);                               //~vXXEM~
//  len=strlen(Pstr);                                              //~v79zR~
  	pstrlocale=g_locale_from_utf8(Pstr,len,&readlen,&writelen,&perr);//~vXXEM~
    PRINTGERR("OnChar-g_locale_from_utf8",perr);                   //~v69rR~
	dprintf("Not IME commit string=%s->%s,len=%d->utf8len=%d,\n",Pstr,pstrlocale,len,utf8len);//~v667R~//~va0QR~
	dprintf("Not IME keyval=%02x,string=%s,eventstr=%s-->%s: \n",Spresskeyevent.keyval,Pstr,Spresskeyevent.string,pstrlocale);//~va0QR~
    xxemain_onchar(Spresskeyevent.keyval,Spresskeyevent.hardware_keycode,Spresskeyevent.state,pstrlocale,writelen);//~vXXEM~
  	g_free (pstrlocale);                                           //~vXXEM~
#endif                                                             //~va0SR~
  }                                                                //~v79zI~
    return;                                                        //~vXXEM~
}                                                                  //~2908M~
#ifndef ARM                                                        //~v@@@R~
//********************************************************         //~vXXEM~
// im preedit change callback                                      //~vXXEM~
//********************************************************         //~vXXEM~
void impreedit(GtkIMContext *Ppcontext,GtkWidget *Ppwidget)        //~vXXEM~
{                                                                  //~vXXEM~
//  int readlen,writelen,utf8len,len,cursor_pos;                   //~v79DR~
    int utf8len,len,cursor_pos;                                    //~v79DI~
//  gchar  *pstrlocale;                                            //~v79DR~
	gchar *pstrpreedit;                                            //~vXXEM~
//  GError  *perr=NULL;                                            //~v79DR~
//*********************************                                //~vXXEM~
  	gtk_im_context_get_preedit_string (Ppcontext,&pstrpreedit, NULL,&cursor_pos);//~vXXEM~
  	dprintf("imepreedit str=%s,csrpos=%d\n",pstrpreedit,cursor_pos);//~v79DI~
  	len=strlen(pstrpreedit);                                       //~vXXEM~
UTRACED("@@@preedit utf8",pstrpreedit,len);                        //~v69rR~
  	utf8len=g_utf8_strlen(pstrpreedit, -1);                        //~vXXEM~
  	cursor_pos=CLAMP(cursor_pos, 0,utf8len);                       //~vXXEM~
//  pstrlocale=g_locale_from_utf8(pstrpreedit,len,&readlen,&writelen,&perr);//~v79DR~
//  PRINTGERR("impreedit-g_locale_from_utf8",perr);                //~v79DR~
//  dprintf("preedit changed len=%d,utf8len=%d,str=%s->%s,csrpos=%d\n",len,utf8len,pstrpreedit,pstrlocale,cursor_pos);//~v79DR~
//UTRACED("@@@preedit locale",pstrlocale,len);                     //~v79DR~
//  xxemain_scrpreedit(pstrlocale,writelen,cursor_pos);            //~v79DR~
  	dprintf("preedit changed len=%d,utf8len=%d,csrpos=%d\n",len,utf8len,cursor_pos);//~v79DI~
    xxemain_scrpreedit(pstrpreedit,len,cursor_pos); //process always UTF8 string//~v79DR~
  	g_free (pstrpreedit);                                          //~vXXEM~
//	g_free (pstrlocale);                                           //~v79DR~
	return;                                                        //~vXXEM~
}//impreedit                                                       //~vXXEM~
#endif //ARM                                                       //~v@@@R~
//**********************************                               //~vXXEM~
// key up                                                          //~vXXEM~
//**********************************                               //~vXXEM~
int gxeview_OnKeyUp(GtkWidget       *Ppwidget,                     //~vXXEI~
                                        GdkEventKey     *Ppevent)  //~vXXEM~
{
    char ch,*pstr="";                                              //~v69wI~
//**********************                                           //~v69wI~
//    UTRACEP("OnKeyUp   ch=%x,rep=%d,flag=%08x\n",nChar,nRepCnt,nFlags);//~v59zR~
//    Mwxemain.onkeyup(nChar, nRepCnt, nFlags);                    //~v59zR~
//  char ch=*(Ppevent->string);                                    //~v69wR~
    if (Ppevent->length)    //string length not 0                  //~v69wI~
        pstr=Ppevent->string;                                      //~v69wI~
    ch=*pstr;                                                      //~v69wI~
    if (ch<0x20)                                                   //~vXXEM~
        ch='.';                                                    //~vXXEM~
    dprintf("key up state=%x,keyval=%x,scan=%x,string=%c(x%02x)\n",//~v667R~//~va30R~
               Ppevent->state,Ppevent->keyval,                     //~vXXEM~
               Ppevent->hardware_keycode,ch,*pstr);                //~v69wR~
  if (Gpimcontext)                                                 //~v69aI~
  {                                                                //~v69aI~
    if (gtk_im_context_filter_keypress(Gpimcontext,Ppevent))       //~vXXEM~
    {                                                              //~vXXEM~
		 dprintf("keynreleasw IME  event\n");                       //~v667R~
        return TRUE;                                               //~vXXEM~
    }                                                              //~vXXEM~
  }                                                                //~v69aI~
//  return xxemain_onkeyup(Ppevent->keyval,Ppevent->hardware_keycode,Ppevent->state,Ppevent->string);//~v69wR~
    return xxemain_onkeyup(Ppevent->keyval,Ppevent->hardware_keycode,Ppevent->state,pstr);//~v69wI~
}//gxeview_OnKeyUp                                                 //~v69wR~
#ifndef ARM                                                        //~v@@@R~
#ifndef XXE                                                        //~vXXER~
int gxeview_DefWindowProc(void)                                    //~v59zR~
{
//**************************                                       //~2914I~
    return 0;                                                      //~v59zI~
}

int gxeview_OnCreate(void)                                         //~v59zR~
{
    return 0;                                                      //~v59zR~
}
#endif                                                             //~vXXER~
void gxeview_OnOptions                      (GtkMenuItem     *menuitem,//~v59zR~
                                        gpointer         user_data)//~v59zI~
{                                                                  //~v59zI~
	dprintf("gxeview_OnOptions\n");                                //~va0SI~
	gxedlg_option_create(menuitem,user_data);                      //~v59zR~
	return;                                                        //~v59zI~
}                                                                  //~v59zI~
void gxeview_OnOk(void)                                            //~v59zR~
{                                                                  //~2817I~
//    Mwxemain.OnOk();                                             //~v59zR~
//    Invalidate(FALSE);                                           //~v59zR~
}                                                                  //~2817I~

int gxeview_OnCommand(void)                                        //~v59zR~
{
	
//    return CView::OnCommand(wParam, lParam);                     //~v59zR~
    return 0;                                                      //~v59zI~
}

void gxeview_OnSize(void)                                          //~v59zR~
{                                                                  //~2818I~
//    WINDOWPLACEMENT wp;                                          //~v59zR~
////**************************                                     //~v59zR~
//    UTRACEP("View onsize:type=%d (%d,%d)\n",nType,cx,cy);        //~v59zR~
//    CView::OnSize(nType, cx, cy);                                //~v59zR~
//    GetWindowPlacement(&wp);                                     //~v59zR~
//    UTRACED("View onsize:wp",&wp,sizeof(wp));                    //~v59zR~
//    Mwxemain.OnSize(nType,cx,cy);                                //~v59zR~
//                                                                 //~v59zR~
	dprintf("gxeview_OnSize\n");                                   //~v76cI~
}                                                                  //~2818I~
#endif //ARM                                                       //~v@@@I~
#ifndef ARM                                                        //~v@@@I~
void gxeview_OnUpdateEditPaste(GtkMenuItem *Ppmenuid)              //~vXXER~
{                                                                  //~vXXEI~
//  pCmdUI->Enable(Mwxemain.cpupdatepaste());                      //~vXXER~
	gtk_widget_set_sensitive(GTK_WIDGET(Ppmenuid),xxemain_cpupdatepaste());//~vXXER~
	return;                                                        //~vXXEI~
}
                                                                   //~2A03M~
void gxeview_OnUpdateEditPasteRep(GtkMenuItem *Ppmenuid)           //~vXXER~
{                                                                  //~2A03M~
//    pCmdUI->Enable(Mwxemain.cpupdatepaste());                    //~v59zR~
	gtk_widget_set_sensitive(GTK_WIDGET(Ppmenuid),xxemain_cpupdatepaste());//~vXXER~
	return;                                                        //~vXXEI~
}                                                                  //~2A03M~
void gxeview_OnUpdateEditPasteV(GtkMenuItem *Ppmenuid)             //~v66pI~
{                                                                  //~v66pI~
	gtk_widget_set_sensitive(GTK_WIDGET(Ppmenuid),xxemain_cpupdatepastev());//~v66pR~
	return;                                                        //~v66pI~
}                                                                  //~v66pI~
#endif //ARM                                                       //~v@@@R~
void gxeview_OnEditPasteIns(void)                                  //~v59zR~
{
	 dprintf("on_paste_insert_activate  \n");                       //~v667R~
    xxemain_cppaste(1);                                            //~vXXER~
	
}
                                                                   //~2A03I~
void gxeview_OnEditPasteRep(void)                                  //~v59zR~
{                                                                  //~2A03M~
	 dprintf("on_paste_replace_activate  \n");                      //~v667R~
    xxemain_cppaste(0);                                            //~vXXER~
	                                                               //~2A03M~
}                                                                  //~2A03M~
void gxeview_OnEditPasteV(void)                                    //~v66pI~
{                                                                  //~v66pI~
	dprintf("on_paste_V_activate  \n");                            //~v66pI~
    xxemain_cppaste(2);			//2:std paste                      //~v66pI~
}                                                                  //~v66pI~
#ifndef ARM                                                        //~v@@@I~
void gxeview_OnUpdateEditCopy(GtkMenuItem *Ppmenuid)               //~vXXER~
{
//    pCmdUI->Enable(Mwxemain.cpupdatecopy());                     //~v59zR~
	gtk_widget_set_sensitive(GTK_WIDGET(Ppmenuid),xxemain_cpupdatecopy());//~vXXER~
	return;                                                        //~vXXEI~
}
#endif //ARM                                                       //~v@@@I~

void gxeview_OnEditCopy(void)                                      //~v59zR~
{
	 dprintf("on_copy_activate  \n");                               //~v667R~
    xxemain_cpcopy(0);                                             //~vXXER~
	xxemain_scrinvalidate(0);                                      //~v66pI~
	return;                                                        //~vXXEI~
}

#ifndef ARM                                                        //~v@@@I~
void gxeview_OnUpdateEditCut(GtkMenuItem *Ppmenuid)                //~vXXER~
{
//    pCmdUI->Enable(Mwxemain.cpupdatecut());                      //~v59zR~
	gtk_widget_set_sensitive(GTK_WIDGET(Ppmenuid),xxemain_cpupdatecut());//~vXXER~
	return;                                                        //~vXXER~
}
                                                                   //~2A06M~
void gxeview_OnUpdateEditClear(GtkMenuItem *Ppmenuid)              //~vXXER~
{                                                                  //~2A06M~
//    pCmdUI->Enable(Mwxemain.cpupdatecut());                      //~v59zR~
	gtk_widget_set_sensitive(GTK_WIDGET(Ppmenuid),xxemain_cpupdatecut());//~vXXER~
	return;                                                        //~vXXEI~
}                                                                  //~2A06M~
#endif //ARM                                                       //~v@@@I~

void gxeview_OnEditCut(void)                                       //~v59zR~
{
	 dprintf("on_cut_activate  \n");                                //~v667R~
    xxemain_cpcopy(1);                                             //~vXXER~
	xxemain_scrinvalidate(0);                                      //~v66pI~
}
                                                                   //~2A06M~
void gxeview_OnEditClear(void)                                     //~v59zR~
{                                                                  //~2A06M~
	 dprintf("on_clear_activate  \n");                              //~v667R~
    xxemain_cpcopy(2);                                             //~vXXER~
	xxemain_scrinvalidate(0);                                      //~v66pI~
}                                                                  //~2A06M~

void gxeview_OnLButtonDown(void)                                   //~v59zR~
{
//    Mwxemain.mouselbdown(nFlags,point);                          //~v59zR~
//    CView::OnLButtonDown(nFlags, point);                         //~v59zR~
}

void gxeview_OnLButtonUp(void)                                     //~v59zR~
{
//    Mwxemain.mouselbup(nFlags,point);                            //~v59zR~
//                                                                 //~v59zR~
//    CView::OnLButtonUp(nFlags, point);                           //~v59zR~
}

void gxeview_OnMouseMove(void)                                     //~v59zR~
{
//    Mwxemain.mousemove(nFlags,point);                            //~v59zR~
//                                                                 //~v59zR~
//    CView::OnMouseMove(nFlags, point);                           //~v59zR~
}

void gxeview_OnRButtonDown(void)                                   //~v59zR~
{
//    Mwxemain.mouserbdown(nFlags,point);                          //~v59zR~
//                                                                 //~v59zR~
//    CView::OnRButtonDown(nFlags, point);                         //~v59zR~
}

#ifndef ARM                                                        //~v@@@I~

//void gxeview_OnSetFocus(void)                                    //~v69dR~
//{                                                                //~v69dR~
//    CView::OnSetFocus(pOldWnd);                                  //~v59zR~
//                                                                 //~v59zR~
//    Mwxemain.onsetfocus(pOldWnd);                                //~v59zR~
	
//}                                                                //~v69dR~

//void gxeview_OnKillFocus(void)                                   //~v69dR~
//{                                                                //~v69dR~
//    CView::OnKillFocus(pNewWnd);                                 //~v59zR~
	
//    Mwxemain.onkillfocus(pNewWnd);                               //~v59zR~
	
//}                                                                //~v69dR~


//======================================================================//~2A13I~
// ret 0:mousewheel is effective 1:inefective                      //~2A13I~
//======================================================================//~2A13I~
int gxeview_OnMouseWheel(void)                                     //~v59zR~
{
//    UTRACEP("view:onmousewheel flag=%x,delta=%x,(%x,%x)\n",nFlags,zDelta,pt.x,pt.y);//~v59zR~
//    Mwxemain.onmousewheel(nFlags,zDelta,pt);                     //~v59zR~
//                                                                 //~v59zR~
//    return CView::OnMouseWheel(nFlags, zDelta, pt);              //~v59zR~
//    return FALSE;       //not efective                           //~v59zR~
    return 0;                                                      //~v59zI~
}

void gxeview_OnDropFiles(void)                                     //~v59zR~
{
	
//  CView::OnDropFiles(hDropInfo);                                 //~2A14R~
//    GetDocument()->ondropfiles(hDropInfo);                       //~v59zR~
}

int gxeview_OnFilePrint(void)                                      //~vXXER~
{                                                                  //~vXXEI~
	int rc=0,hcopysw,ii,jj,spage,epage,printpageno=0;              //~v667R~
//***********************                                          //~vXXEI~
    hcopysw=Mhcopysw;                                              //~vXXEI~
	if (OnPreparePrinting(1)==FALSE)	//1:not preview            //~vXXER~
    	return 4;                                                  //~vXXEI~
	if (OnBeginPrinting())                                         //~vXXER~
    	return 4;                                                  //~vXXEI~
    for (jj=0;;)                                                   //~vXXEI~
    {                                                              //~vXXEI~
        if (Mhcopysw)                                              //~vXXEI~
            spage=epage=1;                                         //~vXXEI~
        else                                                       //~vXXEI~
        {                                                          //~vXXEI~
            if (Mpagerangeno)	//not all                          //~vXXEI~
            {                                                      //~vXXEI~
                spage=Mpagerange[jj++];                            //~vXXEI~
                epage=Mpagerange[jj++];                            //~vXXEI~
            }                                                      //~vXXEI~
            else                                                   //~vXXEI~
            {                                                      //~vXXEI~
                spage=1;                                           //~vXXEI~
                epage=0;                                           //~vXXEI~
            }                                                      //~vXXEI~
            if (!epage)                                            //~vXXEI~
                epage=Mprtmaxpage;                                 //~vXXEI~
        }                                                          //~vXXEI~
        for (ii=spage;ii<=epage;ii++)  //all page                  //~vXXER~
        {                                                          //~vXXER~
            if (ii>Mprtmaxpage)                                    //~vXXEI~
                break;                                             //~vXXEI~
            OnPrepareDC(ii);                                       //~vXXER~
            rc=xxemain_onprint();                                  //~vXXER~
            gnome_print_showpage (Gpprtctxt);                      //~vXXER~
            if (rc)         //eof                                  //~vXXER~
                break;                                             //~vXXER~
            printpageno++;                                         //~vXXEI~
        }                                                          //~vXXER~
        if (rc)         //eof                                      //~vXXEI~
            break;                                                 //~vXXEI~
        if (Mhcopysw)                                              //~vXXEI~
            break;                                                 //~vXXEI~
        if (epage>=Mprtmaxpage)                                    //~vXXER~
            break;                                                 //~vXXEI~
        if (jj>=Mpagerangeno)                                      //~vXXEI~
            break;                                                 //~vXXEI~
    }                                                              //~vXXEI~
	OnEndPrinting(hcopysw);                                        //~vXXER~
    uerrmsg("%d page printed",0,                                   //~vXXEI~
    		printpageno);                                          //~vXXEI~
	xxe_scrdisp();//display errmsg                                 //~vXXEI~
    xxemain_scrinvalidate(0);     //redraw scree for uerrmsg       //~vXXER~
    return 0;                                                      //~vXXER~
}                                                                  //~vXXEI~
void gxeview_OnFileScrprt(void)                                    //~v59zR~
{
    Mhcopysw=1;                                                    //~vXXER~
//  Mpmainframewnd->PostMessage(WM_COMMAND,ID_FILE_PRINT,0);       //~vXXER~
	gxeview_OnFilePrint();	//1:fikleprintid                       //~vXXER~
    return;                                                        //~vXXER~
}

//void CWxeView::OnPrepareDC(CDC* pDC, CPrintInfo* pInfo)          //~vXXEI~
void OnPrepareDC(int Ppageno)                                      //~vXXER~
{
//    if (pInfo)                      //print,not scr              //~v59zR~
//        Mwxemain.prtpreparedc(pDC,pInfo);                        //~v59zR~
//    CView::OnPrepareDC(pDC, pInfo);                              //~v59zR~
    dprintf("prepareDC pageno=%d\n",Ppageno);                      //~v667R~
    gnome_print_beginpage(Gpprtctxt,NULL);                         //~vXXER~
    gnome_print_setrgbcolor(Gpprtctxt,0,0,0);                      //~vXXEM~
    gnome_print_setopacity(Gpprtctxt,1.0);                         //~vXXEM~
	xxemain_prtpreparedc(Ppageno);                                 //~vXXEI~
}

void gxeview_OnPageSetup               (GtkMenuItem     *menuitem, //~v59zR~
                                        gpointer         user_data)//~v59zI~
{
	 dprintf("on_pagesetup  \n");                                  //~va0SI~
	gxepage_create(menuitem,user_data);                            //~vXXER~
    return;                                                        //~v59zI~
}
void gxeview_OnExit(void)                                          //~v59zI~
{                                                                  //~v59zI~
	 dprintf("on_exit_activate  \n");                               //~v667R~
//  gtk_signal_emit_by_name(GTK_OBJECT(Gpframe),"delete_event","menu-exit");//~vXXER~
    gxemfrm_onclose();	//write ini                                //~vXXEM~
    gtk_main_quit();                                               //~vXXEI~
    return;                                                        //~v59zI~
}                                                                  //~v59zI~
#endif  //!ARM                                                     //+vainR~
void gxeview_OnEditEnd(void)                                       //~v59zR~
{
	 dprintf("on_button_end_clicked\n");                            //~v667R~
	xxemain_scrend();                                              //~vXXER~
}

void gxeview_OnEditCancel(void)                                    //~v59zR~
{
	 dprintf("on_button_cancel_clicked\n");                         //~v667R~
	xxemain_scrcancel();                                           //~vXXER~
}
#ifndef ARM                                                        //+vainI~

void gxeview_OnLButtonDblClk(void)                                 //~v59zR~
{
//    if (Mwxemain.mouselbdblclk(nFlags,point))   //processed      //~v59zR~
//        return;                                                  //~v59zR~
//    CView::OnLButtonDblClk(nFlags, point);                       //~v59zR~
}

void gxeview_OnTimer(void)                                         //~v59zR~
{
//    Mwxemain.ontimer(nIDEvent);                                  //~v59zR~
}

void gxeview_OnFileDos(void)                                       //~v59zR~
{
	 dprintf("on_terminal_activate  \n");                           //~v667R~
    xxemain_scrcmd(WXEOF_DOSPROMPT,"sh");                          //~vXXER~
}

void gxeview_OnEditExplorer(void)                                  //~v59zR~
{
	 dprintf("on_home_activate  \n");                               //~v667R~
    xxemain_scrstrcmd("SPAWN","nautilus");                         //~vXXER~
}

#endif //ARM                                                       //~v@@@I~

void gxeview_OnFileOpenwith(void)                                  //~v59zR~
{                                                                  //~v55uR~
	 dprintf("on_openwith  \n");                                    //~v667R~
   	xxemain_onfileopenwith();                                      //~vXXER~
}                                                                  //~v55uR~

#ifndef ARM                                                        //~v@@@I~
void gxeview_OnUpdateFileOpenwith(void)                            //~v59zR~
{                                                                  //~v59zI~
}                                                                  //~v55uR~

#ifndef XXE                                                        //~vXXER~
void gxeview_OnDragEnter(void)                                     //~v59zR~
{
	
    return (GetDocument()->Mwxefile).ondragenter(pDataObject,dwKeyState,point);//~vXXER~
}

void gxeview_OnDragOver(void)                                      //~v59zR~
{
    return (GetDocument()->Mwxefile).ondragover(pDataObject,dwKeyState,point);//~vXXER~
}
#endif                                                             //~vXXEI~
#endif //ARM                                                       //~v@@@I~
//*******************************************************************//~v71sI~
//*drag                                                            //~v71sI~
//*******************************************************************//~v71sI~
void gxeview_dragbegin              (GdkDragContext  *Ppcontext)   //~v71sI~
{                                                                  //~v71sI~
//************************************                             //~v71sI~
	 dprintf("%s:gxeview drag begin entry\n",(char*)utimeedit("hh:mm:ss.mil",0));//~v71sI~
     xxemain_mousedragbegin();                                     //~v71sI~
     return;                                                       //~v71sR~
}//gxeview_dragbegin                                               //~v71sI~
//*******************************************************************//~vXXEI~
//*drag                                                            //~vXXEI~
//*******************************************************************//~vXXEI~
void gxeview_dragdataget              (GdkDragContext  *Ppcontext, //~vXXER~
                                        GtkSelectionData *Ppseldata)//~vXXEI~
{                                                                  //~vXXEI~
//] GtkWidget *psource_widget;                                     //~vXXER~
    char *pfnm,*puri;                                              //~vXXEI~
//************************************                             //~vXXEI~
	 dprintf("%s:drag data get: source-window=%p,dest-window=%p,protocol=%d,is_source=%d,actions=%d,suggested_action=%d,action=%d\n",//~v667R~
    	(char*)utimeedit("hh:mm:ss.mil",0),                        //~vXXER~
  		Ppcontext->source_window,                                  //~vXXEI~
  		Ppcontext->dest_window,                                    //~vXXEI~
  		Ppcontext->protocol,                                       //~vXXEI~
  		Ppcontext->is_source,                                      //~vXXEI~
		  /*GList *targets;  */                                    //~vXXEI~
		Ppcontext->actions,                                        //~vXXEI~
		Ppcontext->suggested_action,                               //~vXXEI~
		Ppcontext->action);                                        //~vXXEI~
//	 dprintf("drag data get selection=%d,target=%d,type=%d,format=%d,data=%s,len=%d\n",//~v667R~//~va72R~
//		(int)(Ppseldata->selection),                               //~vXXEI~//~va72R~
//		(int)(Ppseldata->target),                                  //~vXXEI~//~va72R~
//		(int)(Ppseldata->type),                                    //~vXXEI~//~va72R~
  	 dprintf("drag data get selection=%p,target=%p,type=%p,format=%d,data=%s,len=%d\n",//~va72I~
		(Ppseldata->selection),                                    //~va72I~
		(Ppseldata->target),                                       //~va72I~
		(Ppseldata->type),                                         //~va72I~
		Ppseldata->format,                                         //~vXXEI~
		Ppseldata->data,                                           //~vXXEI~
		Ppseldata->length);                                        //~vXXEI~
//  psource_widget=gtk_drag_get_source_widget(Ppcontext);          //~vXXER~
	 dprintf("gpview drag dataget:window=%p,mfrm win=%p\n",Gpview->window,Gpframe->window);//~v667R~
    for (;;)                                                       //~vXXEI~
    {                                                              //~vXXEI~
#ifdef AAA                                                         //~v685I~
        if (Ppcontext->dest_window==Gpframe->window) //to myself   //~vXXER~
        {                                                          //~vXXER~
            uerrmsg("dropped on myself",0);                        //~vXXER~
			xxe_scrdisp();//display errmsg                         //~vXXEI~
            break;                                                 //~vXXER~
        }                                                          //~vXXER~
#endif                                                             //~v685I~
        if (!xxemain_mousedraggetdata(&pfnm))   //no work          //~vXXER~
            break;                                                 //~vXXEI~
        puri=usetupuri(0,pfnm);   //file://...\n00 fmt,0:no "copy\n" prefix//~vXXER~
        if (!puri)  //file://...\n00 fmt                           //~vXXER~
            break;                                                 //~vXXEI~
        dprintf("get data uri=%s\n",puri);                         //~v667R~
        gtk_selection_data_set(Ppseldata,                          //~vXXER~
                        Ppseldata->target,8,puri,strlen(puri)+1);  //~vXXER~
        umemfree(puri);                                            //~vXXER~
        break;                                                     //~vXXEI~
    }                                                              //~vXXEI~
    return;                                                        //~vXXEI~
}                                                                  //~vXXEI~
//*******************************************************************//~vXXEI~
//*drag                                                            //~vXXEI~
//*******************************************************************//~vXXEI~
void gxeview_dragend         (GdkDragContext  *Ppcontext)          //~vXXER~
{                                                                  //~vXXEI~
//************************************                             //~vXXEI~
	 dprintf("%s:gxeview:drag data end: source-window=%p,dest-window=%p,protocol=%d,is_source=%d,actions=%d,suggested_action=%d,action=%d\n",//~v667R~
    	(char *)utimeedit("hh:mm:ss.mil",0),                       //~vXXER~
  		Ppcontext->source_window,                                  //~vXXEI~
  		Ppcontext->dest_window,                                    //~vXXEI~
  		Ppcontext->protocol,                                       //~vXXEI~
  		Ppcontext->is_source,                                      //~vXXEI~
		  /*GList *targets;  */                                    //~vXXEI~
		Ppcontext->actions,                                        //~vXXEI~
		Ppcontext->suggested_action,                               //~vXXEI~
		Ppcontext->action);                                        //~vXXEI~
    udragend();		//unset source                                 //~vXXEI~
    xxemain_mousedragend();                                        //~vXXEI~
    return;                                                        //~vXXEI~
}                                                                  //~vXXEI~
//*******************************************************************//~v685I~
//*client event fro file drop-in internal msg                      //~v685I~
//*******************************************************************//~v685I~
gboolean gxeview_clientevent(GtkWidget       *widget,              //~v685I~
                             GdkEventClient  *event,               //~v685I~
                             gpointer         user_data)           //~v685I~
{                                                                  //~v685I~
	gboolean rc;                                                   //~v685I~
//********************                                             //~v685I~
    dprintf("view client_event=%p\n",event);                       //~v685R~
	if (event->data_format==32 && event->data.l[0]==(ULONG)Gpview)	//by upostmsg//~v685I~
    {                                                              //~v685I~
    dprintf("view p1=%lx,p2=%lx,p3=%lx\n",	event->data.l[1],event->data.l[2],event->data.l[3]);//~v71sR~
    	if (event->data.l[1]==ID_FILE_DNDCOPY)	//drop file msg    //~v685I~
			filecopybydnd(event->data.l[2],event->data.l[3]);      //~v685I~
        else                                                       //~v71sI~
    	if (event->data.l[1]==ID_FILE_DNDDRAGOUTSETUP)	//dragout file setup//~v71sI~
			xxemain_setupdragfile(event->data.l[2],event->data.l[3]);//~v71sR~
        else                                                       //~v71sI~
    	if (event->data.l[1]==ID_SCREEN_DRAW)	//dragout file setup//~v71sI~
        {                                                          //~v71sI~
			xxemain_scrinvalidate(event->data.l[2]);               //~v71sI~
        }                                                          //~v71sI~
    	rc=TRUE;	//not processed                                //~v685I~
    }                                                              //~v685I~
    else                                                           //~v685I~
    	rc=FALSE;	//not processed                                //~v685R~
    dprintf("view client_event rc=%d\n",rc);                       //~v685I~
    return rc;                                                     //~v685I~
}                                                                  //~v685I~
