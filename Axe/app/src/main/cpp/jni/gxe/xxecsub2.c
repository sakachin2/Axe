//*CID://+vb91R~:                             update#=  819;       //~vb91R~
//***************************************************************************//~2818I~
//* c subrtn for wxe                                               //~2818I~
//***************************************************************************//~2818I~
//vb91:170228 marge rh9j gxe to arm-gxe                            //~vb91I~
//				vb7x:170128 (gxe:BUG)paste to out of gxe get warning operation invalid//~vb91I~
//				vamh:131005*roundup font width(digit width is almost narrow)//~vb91I~
//            				also for gnome print                   //~vb91I~
//				vam9:130929*(BUG)Pango font size is by points metrics is by pixcel;no need to round up(del vam6)//~vb91I~
//				vak6:130905 compiler warning;never executes        //~vb91I~
//v@@@:110610 Android(-DARM)                                       //~v@@@I~
//va72:100801 LP64 wchar_t is 4byte on LP64                        //~va72I~
//va6X:000728*(BUG)S390:abend;suse64 abend at print dialog open(missing apply of v5nD)//~va6XI~
//va6y:000706 CUPs default printer is not same as gnome default    //~va6yI~
//va4a:100411 (XXE:BUG)gnome_get_glyph_width returns width=0 for DBCS//~va4aI~
//va3u:100221 (BUG)not unconditionaly set green for combining char when ligature+split mode//~va3uI~
//            If so so,sub string split at it,then ligature in effective.//~va3uI~
//            (Split altchar is Linux console only)                //~va3uI~
//va3i:100208 (XXE)leave ligature to pango when ligature on        //~va3iI~
//va30:100126 combine mode of diacritical marks by A+/(saved to ini)//~va30I~
//va20:091125 (UTF8)UTF8 full support(display utf8 also unprintable as locale code)//~va20I~
//va1M:091121 (LNX)ligature support;keep monospace for english lagature; Arabian ? I don't know.//~va1MI~
//va1D:091115 gxeprint:2column print for 4byte dbcs if wysiwig is off//~va1DI~
//va1c:091030_merge GB18030 support                                //~va1cI~
//v7ax:090414 (LNX)compile waring int:gsize                        //~v7axR~
//v79z:080916 DBCS tbl for C and K of CJK                          //~v79zI~
//v77u:080111*(XXE:BUG)gnome font search(gnome_font_find_from_full_name) fails for complex font name//~v77sI~
//            alternnative way is gnome_font_face_find_from_family_and_style+gnome_font_face_get_font_full//~v77sI~
//v77s:080110*(XXE:BUG) no need to suppy "regular" because gxedlg do it by v77t//~v77sI~
//v76R:071015 change default print color 000000->141414            //~v76RR~
//v76c:070617 (XXE)paste from PRIMARY clipboard by middle button down//~v76cI~
//v75Y:070527 (XXE:BUG)popup menu disappear by lbutton up for FC5  //~v75YI~
//v75X:070526 (XXE:BUG)assertion fail face!=NULL, gnome_font_find  //~v75XI~
//v69V:060718 (XXE)Performance tuning for pango.                   //~v69VI~
//                 gdk_draw_layout_with_color for each char one by one is too slow//~v69VI~
//                 On the other hand string draw requres monospace and usetmonospace exaust cpu on gprof.//~v69VI~
//                 Bug of monospace determination was exist.(missed to set font to layout)//~v69VI~
//                 Monospace determination obey to font rfamily attribute.//~v69VI~
//                 DBCS is not just double iof sbcs width even for monospace font.//~v69VI~
//                 So use string write for sbcs only if mono space,else use uset monospace.//~v69VI~
//v69S:060716 (WXEXXE:BUG)corrupted dbcs disply by mouse drag when dbcs split//~v69SI~
//            It cause abend for xxe.                              //~v69SI~
//v69s:060531 (XXE:fc5)g_locale_to_utf8 return error if null char contained//~v69sI~
//v69r:060531 (XXE)print debug message by GError                   //~v69rI~
//v69n:060530 (XXE:fc5)avoid Gtk warning "GError not initialized". //~v69nI~
//v686:060323 dnd support by paste(edit:pastev,ins=paste-ins,rep=paste-rep)//~v686I~
//v66q:051023 (XXE) v66i of WXE;C & P;if no Clipboard data on Windows CB,get data from ::CB//~v66qI~
//vX03:050729 for the case sbcs is monospace but dbcs is not       //~vX03I~
//vXXE:050611 new for XXE                                          //~vXXEI~
//***************************************************************************//~vXXEI~
//vXXE:050611 new for XXE                                          //~vXXEI~
//***************************************************************************//~vXXEI~
#include <time.h>                                                  //~2907I~
#include <stdio.h>                                                 //~2907I~
#include <stdlib.h>                                                //~2907I~
#include <string.h>                                                //~2907I~
//*************************************************************    //~2907I~
#ifndef ARM                                                        //~v@@@I~
#include <gnome.h>                                                 //~5624I~
#include <libgnomeprint/gnome-print.h>                             //~vXXEI~
#include <libgnomeprint/gnome-print-job.h>                         //~vXXEI~
#include <cups/cups.h>                                             //~va6yI~
#ifdef GCONFKBD                                                    //~v76cI~
	#include <gconf/gconf.h>                                       //~v76cR~
	#include <gconf/gconf-client.h>                                //~v76cR~
#else                                                              //~v76cI~
	#include <X11/XKBlib.h>                                        //~v76cI~
	#include <gdk/gdkx.h>                                          //~v76cI~
#endif                                                             //~v76cI~
#else                                                              //~v@@@I~
	#include <jnig.h>                                              //~v@@@I~
#endif //ARM                                                       //~v@@@I~
#ifdef UTF8UCS2                                                    //~va30I~
	#define __USE_XOPEN		//wcwidth requires                     //~va30I~
	#include <wchar.h>                                             //~va30I~
#endif                                                             //~va30I~
#include "ulib.h"                                                  //~2907I~
#include "ualloc.h"                                                //~2907R~
#include "uque.h"                                                  //~2929I~
#include "uerr.h"                                                  //~2907I~
#include "ustring.h"                                               //~va6yI~
#include "utrace.h"                                                //~2914I~
#include "ufile.h"                                                 //~2922I~
#include "uedit.h"                                                 //~5625I~
#include "udbcschk.h"                                              //~vXXEI~
#include "utf.h"                                                   //~va1DI~
#ifdef UTF8UCS2                                                    //~va20I~
#include "utf22.h"                                                 //~va20I~
#endif                                                             //~va20I~
                                                                   //~2818I~
#include "xe.h"                                                    //~2929I~
#ifdef UTF8UCS2                                                    //~va20M~
#include "xescr.h"                                                 //~va20I~
#include "xefile.h"                                                //~va20I~
#include "xeutf2.h"                                                //~va20I~
#endif                                                             //~va20M~
#include "xesub2.h"                                                //~v7axI~
                                                                   //~2929I~
#include "gxe.h"                                                   //~5624I~
#include "xxedef.h"                                                //~5624I~
#include "xxemain.h"                                               //~5624I~
#include "xxecsub.h"                                               //~5624I~
#include "gxemfrm.h"                                               //~vXXEI~
#include "xxexei.h"                                                //~va1MR~
                                                                   //~5624I~
//***************************************************************************//~2818I~
//***************************************************************************//~2831I~
//#define DEFAULT_PRINTCOLOR  0x00000001 //RRGGBBAA                //~v76RR~
#define DEFAULT_PRINTCOLOR  0x404040ff //RRGGBBAA                  //~v76RR~
//***************************************************************************//~2907I~
#ifndef ARM                                                        //~v@@@I~
#define COPYFILETARGETNO 1                                         //~vXXEI~
#define COPYFILETARGETID "x-special/gnome-copied-files"            //~vXXEI~
static   GtkTargetEntry Scopyfiletarget[COPYFILETARGETNO]={        //~vXXEI~
        						{COPYFILETARGETID,0,0}             //~vXXER~
														  };       //~vXXEI~
#define DRAGFILETARGETNO 1                                         //~vXXEI~
#define DRAGFILETARGETID "text/uri-list"                           //~vXXEI~
static   GtkTargetEntry Sdragfiletarget[DRAGFILETARGETNO]={        //~vXXEI~
        						{DRAGFILETARGETID,0,0}             //~vXXEI~
														  };       //~vXXEI~
#endif //ARM                                                       //~v@@@I~
#ifdef XXX //GtkDragSourceInfo,gtk_drag_cancel is internal use only now//~vXXEI~
static 	GtkDragSourceInfo *Spdraginfo;                             //~vXXEI~
static  int Sdragtimeout;                                          //~vXXEI~
#endif                                                             //~vXXEI~
//***************************************************************************//~2831I~
#ifndef ARM                                                        //~v@@@I~
//int ufontismonospace(PangoFontDescription *Pfontdesc);           //~va1MR~
int ufontismonospace(PangoFontDescription *Pfontdesc,int Pfontw);  //~va1MR~
PangoFontFamily *usrchfontfamily(PangoFontDescription *Pfontdesc); //~v69VR~
#ifdef UTF8UCS2                                                    //~va20I~
int uprttextoutwsub(int Popt,int Pposx,int Pposy,char *Pdata,char *Pdbcs,int Plen,int Plenlc);//~va20R~
#endif                                                             //~va20I~
#endif //ARM                                                       //~v@@@M~
//*************************************************************    //~5624M~
//*intf to free                                                    //~5624M~
//*************************************************************    //~5624M~
void usettitle(char *Ptitle)                                       //~5624M~
{                                                                  //~5624M~
	gtk_window_set_title(GTK_WINDOW(Gpframe),Ptitle);              //~5624M~
    return;                                                        //~5624M~
}//wxefree                                                         //~5624M~
//*************************************************************    //~5624M~
//*setup color palette input color is rgb by 00rrggbb fmt          //~5624M~
//*************************************************************    //~5624M~
void usetpalettecolor(int Pmaxpal,int *Pprgb)                      //~5624M~
{                                                                  //~5624M~
	int ii,rgb,*prgb,rr,gg,bb;                                     //~5624M~
    GdkColor *pcol;                                                //~5624M~
//*********************************                                //~5624M~
	pcol=Gxxepalette;                                              //~5624M~
	for (ii=0,prgb=Pprgb;ii<Pmaxpal;ii++,prgb++,pcol++)            //~5624M~
    {                                                              //~5624M~
    	rgb=*prgb;                                                 //~5624M~
    	rr=RGB_GETRED(rgb);                                        //~5624M~
    	gg=RGB_GETGREEN(rgb);                                      //~5624M~
    	bb=RGB_GETBLUE(rgb);                                       //~5624M~
//dprintf("palette setup ii=%d,r=%x,g=%x,b=%x\n",ii,rr,gg,bb);     //~v69VR~
        pcol->red  =0xffff*rr/0xff;      //in the range of 0-0xffff from 0-0xff//~5624M~
        pcol->green=0xffff*gg/0xff;                                //~5624M~
        pcol->blue =0xffff*bb/0xff;                                //~5624M~
	    gdk_colormap_alloc_color(Gpcolormap,pcol,FALSE,TRUE);//writeable?,bestmatch?//~5624M~
//dprintf("palette setup p=%x,r=%x,g=%x,b=%x\n",pcol->pixel,pcol->red,pcol->green,pcol->blue);//~v69VR~
    }                                                              //~5624M~
    return;                                                        //~5624M~
}//usetpalettecolor                                                //~5624M~
//*************************************************************    //~5624I~
//*set text to clipboard                                           //~5624I~
//*rc:                                                             //~5624I~
//*************************************************************    //~5624I~
#ifdef UTF8UCS2                                                    //~va20I~
int uclipboard_settext(int Popt,char *Ptext,int Plen)              //~va20I~
#else                                                              //~va20I~
int uclipboard_settext(char *Ptext,int Plen)                       //~5624R~
#endif                                                             //~va20I~
{                                                                  //~5624I~
//  GError *errpos=NULL;                                           //~v69VR~
	int readlen,writelen;                                          //~5624R~
    char *putf8;                                                   //~5624I~
//*********************************                                //~5624I~
//  putf8=g_locale_to_utf8(Ptext,Plen,&readlen,&writelen,&errpos); //~v69VR~
//  PRINTGERR("uclipboard_settext-g_locale_to_utf8",errpos);       //~v69VR~
//  if (readlen!=Plen)                                             //~v69VR~
//  if (csublocale2utf8(Ptext,Plen,&readlen,&putf8,&writelen)>1)   //~v79zR~
    UTRACED("source",Ptext,Plen);                                  //~v79zI~
#ifdef UTF8UCS2                                                    //~va20M~
  if ((Popt & UCSTO_SRCUTF8))                                      //~va20R~
  {                                                                //~va20I~
  	putf8=Ptext;                                                   //~va20I~
    writelen=Plen;                                                 //~va20I~
  }                                                                //~va20I~
  else                                                             //~va20I~
#endif                                                             //~va20M~
  {                                                                //~va20I~
    if (csublocale2utf8(0/*data source is locale encoding*/,Ptext,Plen,&readlen,&putf8,&writelen)>1)//~v79zR~
    {                                                              //~5624I~
//  	if (putf8)                                                 //~v69VR~
//      	g_free(putf8);                                         //~v69VR~
//    	return uerrmsgbox("Cut/Paste:sorce data contains invalid text",0);//~vak6R~//~vb91I~
        uerrmsgbox("Cut/Paste:sorce data contains invalid text",0);//~vak6I~//~vb91I~
//  	return -1;     //unreachable                               //~v@@@R~
    	return -1;                                                 //~5624I~//~vb91I~
    }                                                              //~5624I~
  }                                                                //~va20I~
	gtk_clipboard_set_text(Gpclipboard,putf8,writelen);//void func //~5624R~
UTRACED("uclipboard_settext",putf8,writelen);                      //~va20I~
//  g_free(putf8);                                                 //~v69VR~
    return 0;                                                      //~5624R~
}//uclipboard_settext                                               //~5624R~//~va20R~
#ifndef ARM                                                        //~v@@@I~
//*************************************************************    //~vXXEI~
//*set nuatilus target(not text) string to clipboard               //~vXXEI~
//*rc:                                                             //~vXXEI~
//*************************************************************    //~vXXEI~
void uclipboard_file_get_callback(GtkClipboard *Pcb,GtkSelectionData *Pseldata,guint Pinfo,gpointer Pdata)//~vXXEI~
{                                                                  //~vXXEI~
	char *strdata;                                                 //~vXXER~
    GdkAtom cpatom;                                                //~vXXEI~
//***********************************                              //~vXXEI~
    dprintf("copy get_cb data=%s,info=%d\n",(char*)Pdata,Pinfo);   //~vX03R~
	strdata=(char *)Pdata;                                         //~vXXER~
    cpatom=gdk_atom_intern(COPYFILETARGETID,FALSE);                //~vXXEI~
	gtk_selection_data_set(Pseldata,cpatom,8,strdata,strlen(strdata));//~vXXEI~
    return;                                                        //~vXXEI~
}                                                                  //~vXXEI~
//*************************************************************    //~vXXEI~
void uclipboard_file_clear_callback(GtkClipboard *Pcb,gpointer Pdata)//~vXXEI~
{                                                                  //~vXXEI~
    dprintf("clipboard file clear_cb data=%s\n",(char*)Pdata);     //~vX03R~
    return;                                                        //~vXXEI~
}                                                                  //~vXXEI~
#endif //ARM                                                       //~v@@@I~
//*************************************************************    //~vXXEI~
int uclipboard_setcopyfile(char *Pfilenames,int Plen)              //~vXXEI~
{                                                                  //~vXXEI~
#ifndef ARM                                                        //~v@@@I~
	int rc;                                                        //~vXXEI~
//*********************************                                //~vXXEI~
    dprintf("uclipboard setcopyfile str=%s\n",Pfilenames);         //~vX03R~
    rc=gtk_clipboard_set_with_data(Gpclipboard,Scopyfiletarget,COPYFILETARGETNO,//~vXXEI~
    							uclipboard_file_get_callback,uclipboard_file_clear_callback,//~vXXEI~
    							Pfilenames);                       //~vXXER~
    if (!rc)                                                       //~vXXEI~
    	uerrmsgbox("Cut/Paste:failed to copy filenames to clipboard.",0);//~vXXEI~
    return rc; 	//TRUE|FALSE                                       //~vXXEI~
#else                                                              //~v@@@I~
	return FALSE;                                                  //~v@@@I~
#endif //ARM                                                       //~v@@@I~
}//uclipboard_setcopyfile                                          //~vXXEI~
//*************************************************************    //~v686I~
GtkSelectionData *uclipboard_getfileselection(void)                //~v686R~
{                                                                  //~v686I~
#ifndef ARM                                                        //~v@@@I~
    GdkAtom  atomtgt;                                              //~v686I~
//*********************************                                //~v686I~
    atomtgt=gdk_atom_intern(COPYFILETARGETID,FALSE);               //~v686I~
	return gtk_clipboard_wait_for_contents(Gpclipboard,atomtgt);   //~v686R~
#else                                                              //~v@@@I~
	return NULL;                                                   //~v@@@I~
#endif //ARM                                                       //~v@@@I~
}// uclipboard_getfileselection()                                  //~v686I~
//*************************************************************    //~v686I~
//*get text from clipboard                                         //~v686I~
//*                                                                //~v686I~
//*************************************************************    //~v686I~
int uclipboard_getcopyfile(char **Pfilenames,int *Pfilectr,int *Pplen)//~v686R~
{                                                                  //~v686I~
    GtkSelectionData *pseldata;                                    //~v686I~
//  GdkAtom  atomtgt;                                              //~v69rR~
    int rc;                                                        //~v686I~
//*********************************                                //~v686I~
	if (!(pseldata=uclipboard_getfileselection()))                 //~v686R~
    {                                                              //~v686I~
    	return -1;		//no drop file                             //~v686I~
    }                                                              //~v686I~
	rc=uclipboard_getcopyfilesub(UCLIPBOARD_FREESELECTION,pseldata,Pfilenames,Pfilectr,Pplen);//~v686R~
    return rc;                                                     //~v686R~
}//uclipboard_getcopyfile                                          //~v686R~
//*************************************************************    //~v686M~
//*get dropfile info from clipboard                                //~v686M~
//rc:err                                                           //~v686M~
//*************************************************************    //~v686M~
int uclipboard_getcopyfilesub(int Popt,GtkSelectionData *Pseldata,char **Pfilenames,int *Pfilectr,int *Pplen)//~v686R~
{                                                                  //~v686M~
	int  rc=0,totlen=0,len,filectr=0,reslen,reslen0;               //~v686R~
    char *pc,*pce,*pc0,*pcout,*pcout0;                             //~v686M~
    char uri[_MAX_PATH];                                           //~v686M~
    char fnm[_MAX_PATH];                                           //~v686M~
//***************************************                          //~v686M~
//	dprintf("drag data received selection=%d,target=%d,type=%d,format=%d,data=%s,len=%d\n",//~v686M~//~va72R~
//		(int)(Pseldata->selection),                                //~v686M~//~va72R~
//		(int)(Pseldata->target),                                   //~v686M~//~va72R~
//		(int)(Pseldata->type),                                     //~v686M~//~va72R~
  	dprintf("drag data received selection=%p,target=%p,type=%p,format=%d,data=%s,len=%d\n",//~va72I~
  		(Pseldata->selection),                                     //~va72I~
  		(Pseldata->target),                                        //~va72I~
  		(Pseldata->type),                                          //~va72I~
		Pseldata->format,                                          //~v686M~
		Pseldata->data,                                            //~v686M~
		Pseldata->length);                                         //~v686M~
    dprintf("receive name selection=%s\n",                         //~v686M~
			 gdk_atom_name(Pseldata->selection));                  //~v686M~
    dprintf("receive name target=%s\n",                            //~v686M~
			 gdk_atom_name(Pseldata->target));                     //~v686M~
    dprintf("receive name type=%s\n",                              //~v686M~
			 gdk_atom_name(Pseldata->type));                       //~v686M~
    if (!(Pseldata->length>0 && Pseldata->format==8))	//format:8:char bit size//~v686M~
    	return 8;                                                  //~v686M~
    pc0=Pseldata->data;                                            //~v686R~
    reslen0=Pseldata->length;                                      //~v686R~
    if (reslen0>COPYFILEIDLEN && !memcmp(pc0,COPYFILEID,COPYFILEIDLEN))//copy\n for paste after copy//~v686R~
    {                                                              //~v686I~
		reslen0-=COPYFILEIDLEN;     //"copy\n"                     //~v686I~
        pc0+=COPYFILEIDLEN;                                        //~v686I~
    }                                                              //~v686I~
    else                                                           //~v686I~
    if (reslen0>CUTFILEIDLEN && !memcmp(pc0,CUTFILEID,CUTFILEIDLEN))//copy\n for paste after copy//~v686R~
    {                                                              //~v686I~
		reslen0-=CUTFILEIDLEN;     //"copy\n"                      //~v686I~
        pc0+=CUTFILEIDLEN;                                         //~v686I~
    }                                                              //~v686I~
    for (pc=pc0,reslen=reslen0;reslen>0;)                          //~v686R~
    {                                                              //~v686M~
        if (reslen<=TEXTFILEIDLEN || memcmp(pc,TEXTFILEID,TEXTFILEIDLEN))//no drop fmt//~v686M~
        {                                                          //~v686M~
        	rc=4;                                                  //~v686M~
            break;                                                 //~v686M~
        }                                                          //~v686M~
        pc+=TEXTFILEIDLEN;                                         //~v686M~
        pce=strchr(pc,'\n');                                       //~v686M~
        if (pce)                                                   //~v686M~
        {                                                          //~v686M~
            len=(ULONG)pce-(ULONG)pc;                              //~v686M~
            if (pce>pc && *(pce-1)=='\r')                          //~v686M~
                len--;                                             //~v686M~
            memcpy(uri,pc,len);                                    //~v686M~
            *(uri+len)=0;                                          //~v686M~
        }                                                          //~v686M~
        else                                                       //~v686M~
        	strncpy(uri,pc,sizeof(uri));                           //~v686M~
//		pfnm=g_filename_from_uri(pc,&phostname,&perror);	//api may have bug//~v686M~
        if (uuridecode(uri,fnm,_MAX_PATH)<0)                       //~v686M~
        {                                                          //~v686M~
            rc=4;                                                  //~v686M~
            break;                                                 //~v686M~
        }                                                          //~v686M~
        totlen+=strlen(fnm)+1;                                     //~v686M~
        filectr++;                                                 //~v686M~
    	dprintf("file# %d=%s\n",filectr,fnm);                      //~v686I~
        if (!pce)                                                  //~v686M~
            break;                                                 //~v686M~
        pc=pce+1;                                                  //~v686M~
        if (!*pc) 	//linux len contain last null                  //~v686I~
        	break;                                                 //~v686I~
    	reslen=reslen0-((LONG)pc-(ULONG)pc0);                      //~v686R~
    }                                                              //~v686M~
    if (rc)                                                        //~v686I~
        return rc;                                                 //~v686I~
    pcout=pcout0=umemalloc(totlen);                                //~v686M~
    for (pc=pc0,reslen=reslen0;reslen>0;)                          //~v686R~
    {                                                              //~v686M~
        pc+=TEXTFILEIDLEN;                                         //~v686M~
        pce=strchr(pc,'\n');                                       //~v686M~
        if (pce)                                                   //~v686M~
        {                                                          //~v686M~
            len=(ULONG)pce-(ULONG)pc;                              //~v686M~
            if (pce>pc && *(pce-1)=='\r')                          //~v686M~
                len--;                                             //~v686M~
            memcpy(uri,pc,len);                                    //~v686M~
            *(uri+len)=0;                                          //~v686M~
        }                                                          //~v686M~
        else                                                       //~v686M~
        	strncpy(uri,pc,sizeof(uri));                           //~v686M~
//		pfnm=g_filename_from_uri(pc,&phostname,&perror);	//api may have bug//~v686M~
        uuridecode(uri,fnm,_MAX_PATH);                             //~v686R~
        len=strlen(fnm)+1;                                         //~v686M~
        memcpy(pcout,fnm,len);                                     //~v686M~
        pcout+=len;                                                //~v686M~
        if (!pce)                                                  //~v686M~
            break;                                                 //~v686M~
        pc=pce+1;                                                  //~v686M~
        if (!*pc) 	//linux len contain last null                  //~v686I~
        	break;                                                 //~v686I~
    	reslen=reslen0-((LONG)pc-(ULONG)pc0);                      //~v686I~
    }                                                              //~v686M~
    if (Popt & UCLIPBOARD_FREESELECTION)  //free selection data    //~v686R~
		gtk_selection_data_free(Pseldata);                         //~v686R~
    *Pfilenames=pcout0;                                            //~v686R~
    *Pfilectr=filectr;                                             //~v686I~
    if (Pplen)                                                     //~v686R~
    	*Pplen=totlen;                                             //~v686R~
    return 0;                                                      //~v686M~
}//uclipboard_getcopyfilesub                                       //~v686I~
//*************************************************************    //~5624I~
//*get text from clipboard                                         //~5624I~
//*return by utf8 string for the case paste insto utf8 file(wide range translation)                                                                //~5624I~//~va20R~
//*************************************************************    //~5624I~
//int uclipboard_gettext(char **Pptext,int *Pplen)                 //~v76cR~
int uclipboard_gettext(int Popt,char **Pptext,int *Pplen,GtkClipboardTextReceivedFunc Ppcallback)//~v76cR~
{                                                                  //~5624I~
#ifdef UTF8UCS2                                                    //~va20I~
#else                                                              //~va20I~
    GError *errpos=NULL;                                           //~v69nR~
//	int readlen,writelen,len;                                      //~v7axR~
  	gsize readlen,writelen;                                        //~v7axR~
#endif                                                             //~va20I~
  	int len;                                                       //~v7axR~
    char *putf8/*,*ptext*/;                                            //~5624I~//~va20R~
//*********************************                                //~5624I~
	putf8=*Pptext;                                                 //~v76cI~
	*Pptext=0;                                                     //~v66qI~
//  if (!(putf8=gtk_clipboard_wait_for_text(Gpclipboard)))	//accepted len//~v76cR~
    dprintf("gettextentry \n");                                    //~v76cI~
  if (!(Popt & UCBGT_CBDATA))                                      //~v76cI~
  {                                                                //~v76cI~
    if (Popt & UCBGT_PRIMARY)                                      //~v76cI~
    {                                                              //~v76cI~
//        dprintf("PRIMARY=%p\n",Gpclipboardp);                    //~v76cR~
//        if (!Gpclipboardp)                                       //~v76cR~
//            Gpclipboardp=gtk_clipboard_get(GDK_SELECTION_PRIMARY);//~v76cR~
//        dprintf("new PRIMARY=%p\n",Gpclipboardp);                //~v76cR~
//        if (!Gpclipboardp)                                       //~v76cR~
//            return -2;                                           //~v76cR~
		if (Popt & UCBGT_SETCB)                                    //~v76cR~
        {                                                          //~v76cI~
	        dprintf("PRIMARY(%p) request data\n",Gpclipboardp);    //~v76cI~
        	gtk_clipboard_request_text(Gpclipboardp,(GtkClipboardTextReceivedFunc)Ppcallback,0);//~v76cR~
            return 0;                                              //~v76cI~
        }                                                          //~v76cI~
	    putf8=gtk_clipboard_wait_for_text(Gpclipboardp);	//PRIMARY clipboard accepted len//~v76cR~
        dprintf("PRIMARY text=%s\n",putf8);                        //~v76cI~
    }                                                              //~v76cI~
    else                                                           //~v76cI~
    	putf8=gtk_clipboard_wait_for_text(Gpclipboard);	//accepted len//~v76cI~
  }//not cbdata                                                    //~v76cI~
    if (!putf8)	//accepted len                                     //~v76cI~
    {                                                              //~5624I~
//  	return uerrmsgbox("Cut/Paste:failed to get text from clipboard",0);//~v66qR~
//  	return -1;                                                 //~v66qR~
    	return -2;                                                 //~v66qI~
    }                                                              //~5624I~
    len=strlen(putf8);                                             //~5624I~
#ifdef UTF8UCS2                                                    //~va20I~
    *Pptext=putf8;                                                 //~va20I~
    *Pplen=len;                                                    //~va20I~
UTRACED("uclipboard gettext",putf8,len);                           //~va20I~
#else                                                              //~va20I~
    ptext=g_locale_from_utf8(putf8,len,&readlen,&writelen,&errpos);//~5624I~
    PRINTGERR("uclipboard_gettext-g_locale_from_utf8",errpos);     //~v69rR~
    if (readlen!=len)                                              //~5624I~
    {                                                              //~5624I~
    	if (ptext)                                                 //~5624I~
	    	g_free(ptext);                                         //~5624I~
    	return uerrmsgbox("Cut/Paste:clipboard data contains invalid text",0);//~5624I~
    	return -1;                                                 //~5624I~
    }                                                              //~5624I~
  if (!(Popt & UCBGT_CBDATA))                                      //~v76cR~
    g_free(putf8);                                                 //~5624I~
    *Pptext=ptext;                                                 //~5624I~
    *Pplen=writelen;                                               //~5624I~
#endif                                                             //~va20M~
    return 0;                                                      //~5624I~
}//clipboard_gettext                                               //~5624I~
//*************************************************************    //~5624I~
//*get text from clipboard                                         //~5624I~
//*                                                                //~5624I~
//*************************************************************    //~5624I~
void uclipboard_memfree(char *Pmem)                                //~5624I~
{                                                                  //~5624I~
UTRACEP("uclipboard memfree addr=%p\n",Pmem);                      //~va20I~
    g_free(Pmem);                                                  //~5624I~
    return;                                                        //~5624I~
}//uclipboard_memfree(char *Pmem)                                  //~5624I~
//*************************************************************    //~5625I~
//*decode %xx:output is strz                                       //~5625R~
//*rc:1:converted,0:no encoded data,-1:fmt err,-2:outbuff ovf      //~5625R~
//*************************************************************    //~5625I~
int uuridecode(char *Puri,char *Pdecoded,int Pobuffsz)             //~5625R~
{                                                                  //~5625I~
	int encodedsw=0;                                               //~5625I~
	char *pci,*pco,*pce,*pcoe;                                     //~5625R~
//***************************                                      //~5625I~
	for (pci=Puri,pco=Pdecoded,pce=pci+strlen(pci)+1,pcoe=pco+Pobuffsz;pci<pce;)//~5625R~
    {                                                              //~5625I~
    	if (pco>=pcoe)	//buff overflow                            //~5625I~
        	return -2;                                             //~5625I~
		if (*pci!='%')                                             //~5625I~
        {                                                          //~5625I~
        	*pco++=*pci++;                                         //~5625R~
			continue;                                              //~5625I~
		}                                                          //~5625I~
        encodedsw=1;                                               //~5625R~
        pci++;                                                     //~5625I~
        if (ugethex(pci,pco,2)<0)	//hex err                      //~5625I~
        	return -1;                                             //~5625I~
    	pci+=2;                                                    //~5625R~
        pco++;                                                     //~5625I~
	}                                                              //~5625I~
    return encodedsw;                                              //~5625R~
}//uuridecode                                                      //~5625I~
//*******************************************************************//~5625M~
//*filenames setup         xxx\0yyy-->copy\nfile://xxx\ryyy\r\0    //~5628R~
//*******************************************************************//~5625M~
char *usetupuri(int Popt,char *Pstrlist)                           //~vXXER~
{                                                                  //~5625M~
	char *pci,*pco,*puri;                                          //~5625R~
    int len,filecnt=0,ii,copyfilesw;                               //~vXXER~
//*****************************                                    //~5625M~
	copyfilesw=Popt & USUO_COPYFILE;                               //~vXXEI~
    for(ii=0,pci=Pstrlist;*pci!=0;ii++)                            //~5625R~
    	pci+=strlen(pci)+1;                                        //~5625M~
    filecnt=ii;                                                    //~5625M~
    if (!filecnt)                                                  //~vXXEI~
    	return "";                                                 //~vXXEI~
    len=(ULONG)pci-(ULONG)Pstrlist+filecnt*TEXTFILEIDLEN+1;        //~vXXER~
//  len=(ULONG)pci-(ULONG)Pstrlist+filecnt*(TEXTFILEIDLEN+1)+1;    //~vXXER~
    if (copyfilesw)                                                //~vXXEI~
		len+=COPYFILEIDLEN;     //"copy\n"                         //~vXXER~
    dprintf("usetupcopyuri=len=%d,copyfilesw=%d\n",len,copyfilesw);//~vX03R~
	pco=puri=umemalloc((UINT)len);                                 //~vXXER~
    if (copyfilesw)                                                //~vXXEI~
    {                                                              //~vXXEI~
	    memcpy(pco,COPYFILEID,COPYFILEIDLEN);	//"copy\n"         //~vXXER~
    	pco+=COPYFILEIDLEN;                                        //~vXXER~
    }                                                              //~vXXEI~
    for(ii=0,pci=Pstrlist;ii<filecnt;ii++)                         //~vXXER~
	{                                                              //~5625M~
		memcpy(pco,TEXTFILEID,TEXTFILEIDLEN);                      //~5625M~
		pco+=TEXTFILEIDLEN;                                        //~5625M~
		len=strlen(pci);                                           //~5625M~
        memcpy(pco,pci,(UINT)len);	//no hostname (file://hostname/...)//~vXXER~
        pci+=len+1;                                                //~5625M~
        pco+=len;                                                  //~5625M~
//    	*pco++='\r';    //target dose not accept when \r exist     //~vXXER~
    	*pco++='\n';                                               //~vXXER~
    }                                                              //~5625M~
    pco--;	//drop last "\n"                                       //~vb7xI~//~vb91I~
	*pco=0;		                                                   //~vXXER~
    dprintf("usetupcopyuri=%s\n",puri);                            //~vX03R~
//  puri=gnome_vfs_escape_host_and_path_string(puri);   //fail when encoded//~vXXER~
//  dprintf("uri encode=%s\n",puri);                               //~vX03R~
    return puri;                                                   //~5625R~
}//setupuri                                                        //~5625M~
#ifdef XXX                                                         //~vXXEI~
//*******************************************************************//~vXXEI~
//*start drag                                                      //~vXXEI~
//*******************************************************************//~vXXEI~
void udragtimeout_callback(void *Pdraginfo)                        //~vXXER~
{                                                                  //~vXXEI~
    GtkDragSourceInfo *pdraginfo;                                  //~vXXER~
//*****************************                                    //~vXXEI~
	 dprintf("udrag timeout callback\n");                           //~vX03R~
    if (Sdragtimeout)                                              //~vXXEI~
    {                                                              //~vXXEI~
    	pdraginfo=(GtkDragSourceInfo)Pdraginfo;                    //~vXXER~
    	gtk_drag_cancel(pdraginfo,GDK_CURRENT_TIME);               //~vXXER~
        uerrmsgbox("Drag Timeout(%d seconds)",Sdragtimeout);       //~vXXEI~
    }                                                              //~vXXEI~
    return;                                                        //~vXXEI~
}//udragtimeout_callback                                           //~vXXER~
#endif                                                             //~vXXEI~
//*******************************************************************//~5627I~
//*start drag                                                      //~5627I~
//*******************************************************************//~5627I~
void udragbegin(int Ptimeout)                                      //~vXXER~
{                                                                  //~5627I~
#ifndef ARM                                                        //~v@@@M~
    GtkTargetList *ptl;                                            //~5627I~
//*****************************                                    //~5627I~
	 dprintf("%s:udragbegin source_set & drag_begin \n",(char*)utimeedit("hh:mm:ss.mil",0));//~vX03R~
    ptl= gtk_target_list_new (Sdragfiletarget, 1);	//1 entry, texturi-list//~vXXER~
//  gtk_drag_source_set(Gpview,GDK_BUTTON1_MASK,Sdragfiletarget,1,GDK_ACTION_COPY);//~vXXER~
    dprintf("%s:udragbegin:before begin request\n",(char*)utimeedit("HHMMSS.MIL",0));//~v69VI~
    Gpsourcedragcontext=gtk_drag_begin(Gpview,ptl,GDK_ACTION_COPY,1,(GdkEvent*)NULL);//1=button1//~vXXER~
#ifdef XXX                                                         //~vXXEI~
    if (Ptimeout)                                                  //~vXXEI~
    {                                                              //~vXXEI~
    	Spdraginfo=gtk_drag_get_source_info(Gpsourcedragcontext,FALSE);  //FALSE:createsw//~vXXEI~
        Sdragtimeout=Ptimeout;                                     //~vXXEI~
    	gtk_timeout_add(Ptimeout*1000,udragtimeout_callback,Sdraginfo);//~vXXEI~
    }                                                              //~vXXEI~
#endif                                                             //~vXXEI~
    dprintf("%s:udragbegin:begin requested\n",(char*)utimeedit("HHMMSS.MIL",0));//~v69VI~
#endif //ARM                                                       //~v@@@I~
    return;                                                        //~5627I~
}//udragbegin                                                      //~5627I~
//*******************************************************************//~5627I~
//*end drag                                                        //~5627I~
//*******************************************************************//~5627I~
void udragend(void)                                                //~5627I~
{                                                                  //~5627I~
//*****************************                                    //~5627I~
	 dprintf("udragend unset requested ***\n");                     //~vX03R~
#ifdef XXX                                                         //~vXXEI~
    Sdragtimeout=0;                                                //~vXXEI~
#endif                                                             //~vXXEI~
    dprintf("is grabed?=%d\n",gdk_pointer_is_grabbed());           //~vX03R~
//  gtk_drag_source_unset(Gpview);                                 //~vXXER~
    return;                                                        //~5627I~
}//udragend                                                        //~vXXER~
//***************************************************************************//~vXXEI~
//* setup as drag target    *************************************************//~vXXEI~
//***************************************************************************//~vXXEI~
void udragdestset(void)                                            //~vXXEI~
{                                                                  //~vXXEI~
#ifndef ARM                                                        //~v@@@I~
//*******************************                                  //~vXXEI~
	gtk_drag_dest_set(Gpframe,		//drop on all window           //~vXXEI~
       					GTK_DEST_DEFAULT_ALL,                      //~vXXEI~
                    	Sdragfiletarget,DRAGFILETARGETNO,GDK_ACTION_COPY);//~vXXEI~
//  gtk_drag_source_set(Gpview,     //drag from client window      //~vXXEI~
//                    (GDK_BUTTON1_MASK)|(GDK_BUTTON2_MASK),       //~vXXEI~
//                    tgte,DND_TARGETNO,GDK_ACTION_COPY);          //~vXXEI~
#endif //ARM                                                       //~v@@@I~
	return;                                                        //~vXXEI~
}//udragdestset                                                    //~vXXER~
//***************************************************************************//~vXXEI~
//* popupmenu set sensitive                                        //~vXXER~
//***************************************************************************//~vXXEI~
#ifndef ARM                                                        //~v@@@I~
void upopupmenu_menuitem_enable(int Pmenuitemid,int Penable)       //~vXXER~
{                                                                  //~vXXEI~
    GtkWidget *pmenuitem;                                          //~vXXEI~
//*******************************                                  //~vXXEI~
	pmenuitem=gxemfrm_popupmenu_getmenuitem(Pmenuitemid);          //~vXXEI~
    dprintf("menuitem=%d enable=%d\n",Pmenuitemid,Penable);        //~vX03R~
    if (pmenuitem)                                                 //~vXXEI~
    {                                                              //~vXXEI~
		gtk_widget_set_sensitive(pmenuitem,Penable);               //~vXXEI~
//        if (Penable)                                             //~vXXER~
//        {                                                        //~vXXER~
//            switch(Pselect)                                      //~vXXER~
//            {                                                    //~vXXER~
//            case 1: //set select                                 //~vXXER~
//                gtk_menu_item_select(GTK_MENU_ITEM(pmenuitem));  //~vXXER~
//                break;                                           //~vXXER~
//            case -1:    //set deselect                           //~vXXER~
//                gtk_menu_item_deselect(GTK_MENU_ITEM(pmenuitem));//~vXXER~
//            }                                                    //~vXXER~
//        }                                                        //~vXXER~
    }                                                              //~vXXEI~
	return;                                                        //~vXXEI~
}//upopupmenu_menuitem_enable                                      //~vXXER~
#else                                                              //~v@@@I~
void upopupmenu_menuitem_enable(int Pmenuitemid,int Penable)       //~v@@@I~
{                                                                  //~v@@@I~
//*******************************                                  //~v@@@I~
	gxemfrm_upopupmenu_menuitem_enable(Pmenuitemid,Penable);       //~v@@@R~
	return;                                                        //~v@@@I~
}//upopupmenu_menuitem_enable                                      //~v@@@I~
#endif                                                             //~v@@@I~
////***************************************************************************//~vXXER~
////* popupmenu popup                                              //~vXXER~
////***************************************************************************//~vXXER~
//void popupmenu_position_func(GtkMenu *Ppmenu,gint *Ppx,gint *Ppy,gboolean *Pppushin,gpointer Pudata)//~vXXER~
//{                                                                //~vXXER~
//    CPoint *ppoint;                                              //~vXXER~
//    GtkRequisition req;                                          //~vXXER~
//    GdkScreen *pscr;                                             //~vXXER~
//    int rootx,rooty,xx,yy,ww,hh;                                 //~vXXER~
////*******************************                                //~vXXER~
//    ppoint=(CPoint*)Pudata;                                      //~vXXER~
//    dprintf("popupmenu position buttonpos x=%d,y=%d\n",ppoint->x,ppoint->y);//~vX03R~
//    gdk_window_get_origin(Gpview->window,&rootx,&rooty);         //~vXXER~
//    dprintf("popupmenu position rootx=%d,rooty=%d\n",rootx,rooty);//~vX03R~
//    gtk_widget_size_request(GTK_WIDGET(Ppmenu),&req);            //~vXXER~
//    pscr=gtk_widget_get_screen(Gpview);                          //~vXXER~
//    ww=gdk_screen_get_width(pscr);                               //~vXXER~
//    hh=gdk_screen_get_height(pscr);                              //~vXXER~
//    dprintf("popupmenu view ww=%d,hh=%d\n",ww,hh);               //~vX03R~
//    yy=rooty+ppoint->y+5;                                        //~vXXER~
//    xx=rootx+ppoint->x+5;                                        //~vXXER~
//    *Ppx=xx;                                                     //~vXXER~
//    *Ppy=yy;                                                     //~vXXER~
//    *Pppushin=0;    //not pushin completely inside the scr       //~vXXER~
//    dprintf("popupmenu ret xx=%d,yy=%d\n",xx,yy);                //~vX03R~
//    return;                                                      //~vXXER~
//}//upopupmenu_popup                                              //~vXXER~
//***************************************************************************//~vXXEI~
//* popupmenu popup                                                //~vXXEI~
//***************************************************************************//~vXXEI~
void upopupmenu_popup(CPoint Ppoint)                               //~vXXER~
{                                                                  //~vXXEI~
//*******************************                                  //~vXXEI~
    gtk_menu_popup(GTK_MENU(Gppopupmenu),NULL,NULL,                //~vXXER~
//  				*popupmenu_position_func,&Ppoint,3,GDK_CURRENT_TIME);//posfunc use cause menu disapear by rb-up//~vXXER~
//				NULL,NULL,3,GDK_CURRENT_TIME);//3:button3          //~v75YR~
  				NULL,NULL,3,gtk_get_current_event_time());//3:button3//~v75YI~
	return;                                                        //~vXXEI~
}//upopupmenu_popup                                                //~vXXER~
#ifndef ARM                                                        //~v@@@I~
//***************************************************************************//~vXXEI~
//* get font metrics                                               //~vXXEI~
//set monospace sw 0:none,1:monospace,2:sbcs only mnonospace       //~vX03I~
//***************************************************************************//~vXXEI~
void ugetfontmetrics(PangoFontDescription *Pfontdesc,int *Ppwidth,int *Ppheight,int *Ppmonospace)//~vXXER~
{                                                                  //~vXXEI~
	PangoFontMetrics *pfm;                                         //~vXXEI~
    int asc,des,wch,wdz,fw,fh;                                     //~v69VR~
    int sz;                                                        //~vam9I~//~vb91I~
//*******************************                                  //~vXXEI~
    dprintf("ugetfontmetrics desc=%p\n",Pfontdesc);                //~v76RI~
    pfm=pango_context_get_metrics(Gppangocontext,Pfontdesc,Gppangolang);//~vXXEI~
    asc=pango_font_metrics_get_ascent(pfm);                        //~vXXEI~
    des=pango_font_metrics_get_descent(pfm);                       //~vXXEI~
    sz=pango_font_description_get_size(Pfontdesc);//point*PANGO_SCALE//~vam9R~//~vb91I~
    wch=pango_font_metrics_get_approximate_char_width(pfm);        //~vXXEI~
    wdz=pango_font_metrics_get_approximate_digit_width(pfm);       //~vXXEI~
//  fw=max(wch,wdz);            //pango unit=PANGO_SCALE           //~vXXER~
#ifndef AAA                                                        //~vam9R~//~vb91I~
    fw=wdz;            //char_width is affected by dbcs(double width)//~vXXEI~
    fh=asc+des;                                                    //~vamhI~//~vb91I~
    UTRACEP("ugetfontmetrics before roundup width by half of height fw=%d,fh=%d\n",fw,fh);//~vamhI~//~vb91I~
    if (fw<fh/2)                                                   //~vamhI~//~vb91I~
    {                                                              //~vamhI~//~vb91I~
		fw+=PANGO_SCALE-1;	//roundup                              //~vamhI~//~vb91I~
        UTRACEP("ugetfontmetrics roundup width by half of height fw=%d,fh=%d\n",fw,fh);//~vamhI~//~vb91I~
    }                                                              //~vamhI~//~vb91I~
    *Ppwidth=PANGO_PIXELS(fw);                                     //~vXXEI~
//  fh=asc+des;                                                    //~vXXEI~//~vamhR~//~vb91I~
//  fh+=PANGO_SCALE-1;   //roundup                                 //~vam6R~//~vam9R~//~vb91I~
    *Ppheight=PANGO_PIXELS(fh);                                    //~vXXEI~
#else                                                              //~vam9I~//~vb91I~
    pixbypoint=(double)(asc+des)/sz;                               //~vam9I~//~vb91I~
    fh=sz;                                                         //~vam9I~//~vb91I~
    fw=(int)wdz/pixbypoint+0.999;                                  //~vam9R~//~vb91I~
    *Ppwidth=PANGO_PIXELS(fw);                                     //~vam9I~//~vb91I~
    *Ppheight=PANGO_PIXELS(fh);                                    //~vam9I~//~vb91I~
    UTRACEP("ugetfontmetrics pixbypoint=%lf ascent=%d,descent=%d,size=%d(point*PANGO_SCALE),descwsz=%d\n",pixbypoint,asc,des,sz,wdz);//~vam9R~//~vb91I~
#endif                                                             //~vam9I~//~vb91I~
    if (Ppmonospace)                                               //~vXXEI~
//  	*Ppmonospace=ufontismonospace(Pfontdesc);                  //~va1MR~
    	*Ppmonospace=ufontismonospace(Pfontdesc,*Ppwidth);         //~va1MR~
    UTRACEP("metrics ascent=%d,descent=%d,char-ww=%d,digit-ww=%d,ww=%d,hh=%d\n",asc,des,wch,wdz,*Ppwidth,*Ppheight);//~va1MR~
    return;                                                        //~vXXEI~
}//ugetfontmetrics                                                 //~vXXEI~
#ifdef UTF8SUPPH                                                   //~va1MR~
//***************************************************************************//~va1MR~
//* chk string width                                               //~va1MR~
//* (usetmonospace required for logical extent difference)         //~va1MR~
//***************************************************************************//~va1MR~
int ufontgetextentw(PangoLayout *Pplayout,int Pfontw,char *Pteststr)//~va1MR~
{                                                                  //~va1MR~
	PangoRectangle rect;                                           //~va1MR~
    int width,len;                                                 //~va1MR~
    char dbcswk[256];                                              //~va1MR~
//************************************                             //~va1MR~
	len=strlen(Pteststr);                                          //~va1MR~
    len=min(len,sizeof(dbcswk));                                   //~va1MR~
    pango_layout_set_text(Pplayout,Pteststr,len);  //chk ligature(mix 2 font->1 font)//~va1MR~
    memset(dbcswk,0,(UINT)len);                                    //~va1MR~
  	usetmonospace(0,Pplayout,Pteststr,dbcswk,len,Pfontw);          //~va1MR~
    pango_layout_get_pixel_extents(Pplayout,NULL,&rect);//get logical extent//~va1MR~
UTRACEP("pango layout pixcel char=%s,len=%d,ogical=(x=%d,y=%d-w=%d,h=%d)\n",//~va1MR~
            Pteststr,strlen(Pteststr),rect.x,rect.y,rect.width,rect.height);//~va1MR~
    width=rect.width;                                              //~va1MR~
    return width;                                                  //~va1MR~
}//ufontgetextentw                                                 //~va1MR~
#endif                                                             //~va1MR~
//***************************************************************************//~v69VI~
//* chk monospace:pango_font_family_is_monospace is not supported pango 1.0//~v69VI~
//***************************************************************************//~v69VI~
//int ufontismonospace(PangoFontDescription *Pfontdesc)            //~va1MR~
int ufontismonospace(PangoFontDescription *Pfontdesc,int Pfontw)   //~va1MR~
{                                                                  //~v69VI~
	PangoLayout *playout;                                          //~v69VI~
	PangoRectangle rect1,rect2/*,rect3*/;                          //~va1MR~
    int monospace=0;                                               //~v69VR~
#ifdef UTF8SUPPH                                                   //~va1MR~
#ifdef AAA  //ligture check is done by glyph count on layout       //~va30R~
    int ligaturechkw1,ligaturechkw2;                               //~va1MR~
#endif                                                             //~va30R~
#endif                                                             //~va1MR~
//************************************                             //~v69VI~
    playout=gtk_widget_create_pango_layout(Gpview,NULL);           //~v69VI~
    pango_layout_set_font_description(playout,Pfontdesc);          //~v69VI~
    pango_layout_set_text(playout,"PP",2);                         //~v69VI~
    pango_layout_get_extents(playout,&rect1,&rect2);//get logical extent//~v69VI~
UTRACEP("pango layout extent char=%s,cellw=%d,ink=(x=%d,y=%d-w=%d,h=%d),logical=(x=%d,y=%d-w=%d,h=%d)\n","PP",//~va1MR~
            Pfontw,rect1.x,rect1.y,rect1.width,rect1.height,       //~va1MR~
            rect2.x,rect2.y,rect2.width,rect2.height);             //~v69VI~
    pango_layout_get_pixel_extents(playout,NULL,&rect1);//get logical extent//~v69VI~
UTRACEP("pango layout pixel char=%s,logical=(x=%d,y=%d-w=%d,h=%d)\n","PP",//~va1MR~
            rect1.x,rect1.y,rect1.width,rect1.height);             //~v69VI~
    pango_layout_set_text(playout,"  ",2);  //space+space          //~v69VR~
    pango_layout_get_pixel_extents(playout,NULL,&rect2);//get logical extent//~v69VI~
UTRACEP("pango layout 2 space pixel char=%s,logical=(x=%d,y=%d-w=%d,h=%d)\n","  ",//~va1MR~
            rect2.x,rect2.y,rect2.width,rect2.height);             //~v69VI~
                                                                   //~va1MR~
#ifdef UTF8SUPPH                                                   //~va1MR~
#ifdef AAA  //ligture check is done by glyph count on layout       //~va30R~
	ligaturechkw1=ufontgetextentw(playout,Pfontw,"fi");            //~va1MR~
	ligaturechkw2=ufontgetextentw(playout,Pfontw,"if");            //~va1MR~
    if (ligaturechkw1<ligaturechkw2)  //"fi" is shrinked to one glyph//~va1MR~
    	Gxxestat|=GXXES_FONTLIGATURE;                              //~va1MR~
    else                                                           //~va1MR~
    	Gxxestat&=~GXXES_FONTLIGATURE;                             //~va1MR~
UTRACEP("ligature chk flag=%x,w-(fi)=%d,w-(if)=%d\n",(Gxxestat & GXXES_FONTLIGATURE),ligaturechkw1,ligaturechkw2);//~va1MR~
#endif                                                             //~va30R~
#endif                                                             //~va1MR~
    monospace=(rect1.width==rect2.width);                          //~v69VI~
#ifndef UTF8SUPPH	//no DBCS chk                                  //~va1MR~
    if (monospace)                                                 //~v69VI~
    {                                                              //~v69VI~
        pango_layout_set_text(playout,"\xe3\x81\x82",3);    //japanese A//~v69VI~
        pango_layout_get_pixel_extents(playout,NULL,&rect3);//get logical extent//~v69VI~
        dprintf("pango layout pixel char=%s,logical=(x=%d,y=%d-w=%d,h=%d)\n","дв",//~v69VI~
        rect3.x,rect3.y,rect3.width,rect3.height);                 //~v69VI~
        monospace+=(rect1.width!=rect3.width);  //2:dbcs is not monospace//~v69VI~
    }                                                              //~v69VI~
#endif                                                             //~va1MR~
    g_object_unref(G_OBJECT(playout));                             //~v69VI~
    dprintf("%s is mnospace=%d\n",pango_font_description_get_family(Pfontdesc),monospace);//~v69VR~
    return monospace;                                              //~v69VI~
}//ufontismonospace                                                //~v69VI~
//***************************************************************************//~v69VI~
//* search family                                                  //~v69VI~
//***************************************************************************//~v69VI~
PangoFontFamily *usrchfontfamily(PangoFontDescription *Pfontdesc)  //~v69VR~
{                                                                  //~v69VI~
    PangoFontFamily *family,**pfamily;                             //~v69VI~
    int numfam;                                                    //~v69VR~
    char *famname,*famname2;                                       //~v69VI~
//*******************************                                  //~v69VI~
	famname=(char*)pango_font_description_get_family(Pfontdesc);          //~v69VR~
	dprintf("fontdesc famname=%s\n",famname);                      //~v69VI~
    pango_context_list_families(Gppangocontext,&pfamily,&numfam);  //~v69VI~
	dprintf("familis=%d\n",numfam);                                //~v69VI~
    for (;numfam>0;numfam--,pfamily++)                             //~v69VI~
    {                                                              //~v69VI~
    	family=*pfamily;                                           //~v69VI~
		famname2=(char*)pango_font_family_get_name(family);               //~v69VI~
        if (!strcmp(famname2,famname))                             //~v69VI~
        	return family;                                         //~v69VI~
    }                                                              //~v69VI~
    return 0;                                                      //~v69VI~
}//usrchfontfamily                                                 //~v69VI~
#ifdef AAA                                                         //~v77sR~
//***************************************************************************//~v77sI~
//* search printer font familyname                                 //~v77sI~
//***************************************************************************//~v77sI~
void uprtsrchfamily(char *Pfontname)                               //~v77sR~
{                                                                  //~v77sI~
	GList * families, * l;                                         //~v77sI~
#ifdef AAA                                                         //~v77sI~
    GnomeFontFamily *pfamily;                                      //~v77sI~
#endif                                                             //~v77sI~
	int ii;                                                        //~v77sI~
//*******************************                                  //~v77sI~
	families=gnome_font_family_list();                             //~v77sR~
    dprintf("families=%p\n",families);                             //~v77sI~
	if (families==NULL)                                            //~v77sR~
    	return;                                                    //~v77sI~
	for (ii=0,l = families; l != NULL; l = l->next,ii++)           //~v77sI~
    {                                                              //~v77sI~
    	dprintf("%02d:list=%p,data=%p:%s\n",ii,l,l->data,(char*)(l->data));//~v77sI~
#ifdef AAA                                                         //~v77sI~
        family=(GnomeFontFamily*)l;                                //~v77sI~
#endif                                                             //~v77sI~
	}                                                              //~v77sI~
	gnome_font_family_list_free (families);                        //~v77sI~
    return;                                                        //~v77sI~
}//uprtsrchfamily                                                  //~v77sR~
#endif                                                             //~v77sI~
//===============================================================================//~v77sI~
//serach gnomeFont                                                 //~v77sI~
//===============================================================================//~v77sI~
GnomeFont *uprtfontsrchsub(char *Pfontname)                        //~v77sI~
{                                                                  //~v77sI~
	char fontname[256],*pc;                                        //~v77sI~
	GnomeFont *pfont;                                              //~v77sI~
#ifdef AAA                                                         //~v77sI~
    GnomeFontFace *pface;                                          //~v77sI~
#endif                                                             //~v77sI~
    gdouble dsize;                                                 //~v77sI~
    int italic;                                                    //~v77sI~
    PangoFontDescription *pfd;                                     //~v77sI~
    PangoWeight pweight;                                           //~v77sI~
    PangoStyle pstyle;                                             //~v77sI~
//*************                                                    //~v77sI~
    dprintf("uprtfontsrchsub parm=%s\n",Pfontname);                //~v77sI~
	strcpy(fontname,Pfontname);                                    //~v77sI~
    if (pc=strrchr(fontname,' '),!pc)	//search size backward     //~v77sI~
    	return 0;                                                  //~v77sI~
    *pc++=0;      //del size                                       //~v77sI~
    dsize=(gdouble)atoi(pc);                                       //~v77sI~
    if (pc=strchr(fontname,','),pc)                                //~v77sI~
    	*pc++=0;	//style                                        //~v77sI~
    else                                                           //~v77sI~
    if (pc=strchr(fontname,' '),pc)	//space embedding(gxedlg would set "," for end of space embedding family name)//~v77sI~
    	*pc++=0;	//style                                        //~v77sI~
    else                                                           //~v77sI~
    	return 0;              //no style                          //~v77sI~
    pc+=strspn(pc," ");                                            //~v77sI~
#ifdef AAA // gnome_font_face_get_font_fuull is external but private.h//~v77sI~
    pface=gnome_font_face_find_from_family_and_style(fontname,pc); //~v77sI~
    dprintf("find face=%p,family=%s,style=%s,size=%d\n",pface,fontname,pc,(int)dsize);//~v77sI~
    if (!pface)                                                    //~v77sI~
    	return 0;                                                  //~v77sI~
    pfont=gnome_font_face_get_font_full(pface,dsize,NULL);         //~v77sI~
	dprintf("find font by face font=%p\n",pfont);                  //~v77sI~
#endif                                                             //~v77sI~
#ifndef AAA 	//test weight_slant api                            //~v77sI~
	pfd=pango_font_description_from_string(Pfontname);//desc of context may freed,so allocate new//~v77sI~
    dprintf("pangoFD=%p by %s\n",pfd,Pfontname);                   //~v77sI~
    if (!pfd)                                                      //~v77sI~
    	return 0;                                                  //~v77sI~
    pweight=pango_font_description_get_weight(pfd);                //~v77sI~
    pstyle=pango_font_description_get_style(pfd);                  //~v77sI~
    pango_font_description_free(pfd);                              //~v77sI~
    italic=(pstyle==PANGO_STYLE_ITALIC||pstyle==PANGO_STYLE_OBLIQUE);//~v77sI~
    pfont=gnome_font_find_closest_from_weight_slant(fontname,pweight,italic,dsize);//~v77sI~
    dprintf("weigth slant serach pfont=%p,by font=%s,weight=%d,italic=%d,size=%d\n",pfont,fontname,pweight,italic,(int)dsize);//~v77sI~//~va1MR~
#endif                                                             //~v77sI~
    if (pfont)                                                     //~v77sI~
    	dprintf("new prtfont for %s,fullname=%s,name=%s,family=%s,species=%s,ps=%s,size=%d\n",//~v77sI~
    			Pfontname,                                         //~v77sI~
    			gnome_font_get_full_name(pfont),                   //~v77sI~
    			gnome_font_get_name(pfont),                        //~v77sI~
    			gnome_font_get_family_name(pfont),                 //~v77sI~
    			gnome_font_get_species_name(pfont),                //~v77sI~
    			gnome_font_get_ps_name(pfont),                     //~v77sI~
    			(int)gnome_font_get_size(pfont));                  //~v77sI~
    return pfont;                                                  //~v77sI~
}//uprtfontsrchsub                                                 //~v77sI~
//===============================================================================//~vXXEI~
//get font for printer                                             //~vXXEI~
//outfontname :se if changed from input                            //~v75XI~
//===============================================================================//~vXXEI~
//GnomeFont *uprtcreatefont(char *Pfontname,int *Ppfontw,int *Ppfonth)//~v75XR~
GnomeFont *uprtcreatefont(char *Pfontname,int *Ppfontw,int *Ppfonth,char *Poutfontname)//~v75XI~
{                                                                  //~vXXEI~
	GnomeFont *pfont;                                              //~vXXEI~
//  char fontname[256],*pc,*pc2,*pfontname;                        //~v77sR~
    char *pfontname;                                               //~v77sI~
    int swnopopuperr;                                              //~va6XR~
//  int len1,len2;                                                 //~v77sR~
//************************************                             //~vXXEI~
	if (Poutfontname)                                              //~v75XI~
    	*Poutfontname=0;	//clear                                //~v75XI~
	pfontname=Pfontname;                                           //~vXXEI~
    swnopopuperr=!strcmp(Mprtfontstyle,Mfontstyle);//same as screen,set when *WXEINIprtfontstyle=0//~va6XR~
   	 dprintf("uprtcreatefont=== %s\n",pfontname);                  //~v75XR~
    pfont=uprtfontsrchsub(pfontname);                              //~v77sI~
 if (!pfont)                                                       //~v77sI~
	pfont=gnome_font_find_from_full_name(pfontname);               //~vXXER~
 if (pfont)                                                        //~vX03I~
    dprintf("font=%p for %s,name=%s,family=%s,species=%s,ps=%s\n",pfont,pfontname,//~vX03R~
    	gnome_font_get_name(pfont),                                //~vXXEI~
    	gnome_font_get_family_name(pfont),                         //~vXXEI~
    	gnome_font_get_species_name(pfont),                        //~vXXEI~
    	gnome_font_get_ps_name(pfont));                            //~vXXEI~
#ifdef AAA                                                         //~v77sR~
    uprtsrchfamily(pfontname);                                     //~v77sI~
#endif                                                             //~v77sI~
    if (!pfont) //not found                                        //~vXXEI~
    {                                                              //~vXXEI~
//Regular missing case                                             //~vXXEI~
//      len1=strlen(pfontname);                                    //~v77sR~
//    	for (pc=pfontname+len1-1;pc>pfontname;pc--)                //~v77sR~
//        	if (*pc==' ')                                          //~v77sR~
//            	break;                                             //~v77sR~
//      len2=(ULONG)pc-(ULONG)pfontname+1;                         //~v77sR~
//      memcpy(fontname,pfontname,(UINT)len2);                     //~v77sR~
//      sprintf(fontname+len2,"Regular %s",pc+1);                  //~v77sR~
//      pfontname=fontname;                                        //~v77sR~
//  	pfont=gnome_font_find_from_full_name(pfontname);           //~v77sR~
// 		dprintf("search by adding regular font=%p for %s\n",pfont,pfontname);//~v77sR~
        if (!pfont)                                                //~vXXEI~
        {                                                          //~vXXEI~
			pfont=gnome_font_find_closest_from_full_name(Pfontname);//~vXXEI~
	    	if (!pfont)                                            //~vXXEI~
    		{                                                      //~vXXEI~
    			uerrmsgbox("Font %s not found",0,                  //~vXXEI~
	        			Pfontname);                                //~vXXEI~
    	    	return 0;                                          //~vXXEI~
    		}                                                      //~vXXEI~
    		dprintf("new prtfont for %s,fullname=%s,name=%s,family=%s,species=%s,ps=%s,size=%d\n",//~v77sR~
    			pfontname,                                         //~v77sR~
    			gnome_font_get_full_name(pfont),                   //~v77sR~
    			gnome_font_get_name(pfont),                        //~v77sR~
    			gnome_font_get_family_name(pfont),                 //~v77sR~
    			gnome_font_get_species_name(pfont),                //~v77sR~
    			gnome_font_get_ps_name(pfont),                     //~v77sR~
    			(int)gnome_font_get_size(pfont));                  //~v77sR~
//		    pfontname=(char*)gnome_font_get_name(pfont);           //~v77sR~
		    pfontname=(char*)gnome_font_get_full_name(pfont);      //~v77sI~
          if (swnopopuperr)                                        //~va6XR~
    		printf("Infomation:Font \"%s\" not found,closest Font \"%s\" was initialy selected.",0,//~va6XM~
            			Pfontname,pfontname);                      //~va6XM~
          else                                                     //~va6XM~
    		uerrmsgbox("Font \"%s\" not found,closest Font \"%s\" will be selected",0,//~vXXER~
            			Pfontname,pfontname);                      //~vXXEI~
        }                                                          //~vXXEI~
		if (Poutfontname)                                          //~v75XI~
        {                                                          //~v75XI~
   		 	dprintf("outfontname=%s\n",pfontname);                 //~v75XR~
    		strcpy(Poutfontname,pfontname);                        //~v75XI~
        }                                                          //~v75XI~
    }                                                              //~vXXEI~
	ugetprtfontmetrics(pfont,Ppfontw,Ppfonth);                     //~vXXER~
    if (!*Ppfontw||!*Ppfonth)                                      //~vXXEI~
    {                                                              //~vXXEI~
    	uerrmsgbox("Font %s has size 0",0,                         //~vXXER~
        			pfontname);                                    //~vXXER~
        return 0;                                                  //~vXXEI~
    }                                                              //~vXXEI~
    return pfont;                                                  //~vXXEI~
}//uprtcreatefont                                                  //~vXXEI~
//***************************************************************************//~vXXEI~
//* get printer font metrics                                       //~vXXEI~
//***************************************************************************//~vXXEI~
void ugetprtfontmetrics(GnomeFont *Ppgfont,int *Ppwidth,int *Ppheight)//~vXXEI~
{                                                                  //~vXXEI~
	gdouble fsz;                                                   //~vXXEI~
//*******************************                                  //~vXXEI~
    fsz=gnome_font_get_size(Ppgfont);                              //~vXXEI~
    *Ppheight=(int)fsz;                                            //~vXXEI~
//  *Ppwidth=(int)(fsz/2);                                         //~vamhR~//~vb91I~
    *Ppwidth=(int)((fsz+1.0)/2);                                   //~vamhR~//~vb91I~
    dprintf("print font size=%lf,hh=%d,ww=%d\n",fsz,*Ppheight,*Ppwidth);//~vX03R~
    return;                                                        //~vXXEI~
}//ugetprtfontmetrics                                              //~vXXEI~
#endif //ARM                                                       //~v@@@I~
//***************************************************************************//~vX03I~
//* dbcschk                                                        //~vX03I~
//***************************************************************************//~vX03I~
char *ugetdbcschktbl(char *Pdata,int Plen)                         //~vX03I~
{                                                                  //~vX03I~
static char *Spdbcs=0;                                             //~vX03I~
static int  Smaxlinelen=0;                                         //~vX03I~
//*******************************                                  //~vX03I~
    if (Plen>Smaxlinelen)                                          //~vX03I~
    {                                                              //~vX03I~
        if (Spdbcs)                                                //~vX03I~
            umemfree(Spdbcs);                                      //~vX03I~
        Spdbcs=umemalloc(Plen);                                    //~vX03I~
        Smaxlinelen=Plen;                                          //~vX03I~
    }                                                              //~vX03I~
    usetdbcstbl(USDT_CODE_DEFAULT,Pdata,Spdbcs,Plen);              //~vX03I~
    return Spdbcs;                                                 //~vX03I~
}//ugetdbcschktbl                                                  //~vX03I~
//***************************************************************************//~va1DI~
//* unicode print width                                            //~va1DI~
//***************************************************************************//~va1DI~
int uprtwcwidth(int Popt,gunichar Pucs,int Pdbcssz)                //~va1DR~
{                                                                  //~va1DI~
	int width;                                                     //~va1DI~
//******************                                               //~va1DI~
	width=utfwcwidth(0,(ULONG)Pucs,0/*&flag*/);                    //~va1DI~
//	width=wcwidth(Pucs);                                           //~va1DR~
    width=max(1,width);                                            //~va1DI~
UTRACEP("uprtwcwidth ucs=%x,width=%d\n ",Pucs,width);              //~va1DI~
    return width;                                                  //~va1DI~
}                                                                  //~va1DI~
#ifndef ARM                                                        //~v@@@I~
//***************************************************************************//~va1DI~
//* printer text font size limit of sbcs spacing                   //~va1DI~
//***************************************************************************//~va1DI~
gdouble uprtgetsbcswidth(int Pposx,GnomeFont *Ppfont)              //~va1DI~
{                                                                  //~va1DI~
#define SAMPLE_CHAR  'P'                                           //~va1DI~
#define WIDTH_ALLOWANCE 1.2                                        //~va1DI~
    gunichar ucs=SAMPLE_CHAR;                                      //~va1DI~
    gint glyph;                                                    //~va1DI~
    gdouble xwidth,xsbcswidth;                                     //~va1DI~
//*************                                                    //~va1DI~
    glyph=gnome_font_lookup_default(Ppfont,ucs);                   //~va1DI~
    xwidth=gnome_font_get_glyph_width(Ppfont,glyph);               //~va1DR~
    xsbcswidth=xwidth*WIDTH_ALLOWANCE;                             //~va1DI~
UTRACEP("uprtgetsbcswidth ucs=%02x glyphwidth=%lf,retwidth=%lf\n",ucs,xwidth,xsbcswidth);//~va1DI~
    return xsbcswidth;                                             //~va1DI~
}//uprtgetsbcswidth                                                //~va1DI~
//***************************************************************************//~vXXEI~
//* printer text write                                             //~vXXEI~
//*  for locale code                                               //~va20I~
//***************************************************************************//~vXXEI~
void uprttextout(int Pposx,int Pposy,char *Pdata,int Plen)         //~vXXER~
{                                                                  //~vXXEI~
    GnomeGlyphList *pgl;                                           //~vXXEI~
    GnomeFont *pfont;                                              //~vXXEI~
	int print_color =DEFAULT_PRINTCOLOR;                           //~vXXEI~
    gdouble yy,xx,dx;                                              //~vXXER~
    gchar *putf80;                                                 //~vXXER~
    char *putf8,*pc,*putf8next,*pdbcs;                             //~vX03R~
    int /*readlen,*/writelen,ii,utf8len;                           //~v69sR~
//  GError *errpos=NULL;                                           //~v69sR~
    gunichar ch;                                                   //~vXXER~
    gint glyph;                                                    //~vXXEI~
//	ArtPoint adv;                                                  //~va1DR~
	int utfrc;                                                     //~v69VI~
#ifdef UTF8SUPPH                                                   //~va1cR~
    int dbcssz;                                                    //~va1cR~
    char *pdbcse;                                                  //~va1cR~
    int opt,widesw;                                                //~va1DR~
    gdouble xsbcswidth,glyphwidth,xcellwidth,xoffs;                //~va1DR~
#endif                                                             //~va1cR~
//*******************************                                  //~vXXEI~
	if (!Plen)                                                     //~vXXEI~
    	return;                                                    //~vXXEI~
UTRACED("uprttext input data",Pdata,Plen);                         //~va1cR~
//gnome_print_line_stroked(Gpprtctxt,0.0,(gdouble)(-Pposy),(gdouble)(Mdrawcellw*(Mprtcmaxcol+4)),(gdouble)(-Pposy));//~vXXER~
//  xx=(gdouble)(Mprtrect.left);                                   //~vXXER~
//  yy=(gdouble)(Mprtrect.top-Pposy-Mprtfontascendant);            //~vXXER~
//printf("uprttextout x=%lf,y=%d->%lf,len=%d,%.*s\n",xx,Pposy,yy,Plen,Plen,Pdata);//~vXXER~
//  putf80=g_locale_to_utf8(Pdata,Plen,&readlen,&writelen,&errpos);//~v69sR~
//  PRINTGERR("uprttextout-g_locale_to_utf8",errpos);              //~v69sR~
//  putf80=csublocale2utf8(Pdata,Plen,0,&writelen);                //~v69VR~
//  utfrc=csublocale2utf8(Pdata,Plen,0,&putf80,&writelen);         //~v79zR~
    utfrc=csublocale2utf8(0/*locale encoding*/,Pdata,Plen,0,&putf80,&writelen);//~v79zI~
//  if (!putf80)                                                   //~v69VR~
    if (utfrc>1)                                                   //~v69VR~
    	return;                                                    //~vXXEI~
UTRACED("uprttext utf8  data",putf80,writelen);                    //~va1cR~
UTRACEP("preview mode=%d,ww=%d,monospace=%d\n",Mpreviewmode,Mwwscrprt,Mmonospacepreviewfile);//~va1DR~
	utf8len=g_utf8_strlen (putf80,writelen);                       //~vXXEI~
//printf("uprttextout utf8len=%d\n",utf8len);                      //~vXXER~
    if (Mpreviewmode)                                              //~vXXEI~
    {                                                              //~vXXEI~
        xx=Pposx*Mpreviewscalex+Mprevieworgx;                      //~vXXEI~
        yy=Pposy*Mpreviewscaley+Mprevieworgy;                      //~vXXEI~
//printf("uprttextout utf8len=%d,xx=%lf,yy=%lf\n",utf8len,xx,yy);  //~vXXER~
#ifdef UTF8SUPPH                                                   //~va1DI~
		if (Mwwscrprt)   //print dbcs padding                      //~va1DI~
        {                                                          //~va1DI~
#else                                                              //~va1DI~
#ifdef XXX                                                         //~vX03I~
		if (Mmonospacepreviewfile)                                 //~vXXEI~
        {                                                          //~vXXEI~
#endif                                                             //~vX03I~
#endif                                                             //~va1DI~
            pango_layout_set_text(Gplayoutpreview,putf80,writelen);//~vXXER~
            pango_layout_set_attributes(Gplayoutpreview,Gppangoattrlist);//scaling//~vXXEI~
			if (!(Mmonospacepreviewfile==1 && Mdrawcellw==Mpfontszw))//~vX03R~
            {                                                      //~vX03I~
//dbcschk for monospcing                                           //~vX03I~
                pdbcs=ugetdbcschktbl(Pdata,Plen);                  //~vX03I~
//            	 dprintf("preview monospacing cellw=%d,fontw=%d,monospace=%d\n",Mdrawcellw,Mpfontszw,Mmonospacepreviewfile);//~v69VR~
//  			usetmonospace(Gplayoutpreview,pdbcs,Plen,(int)(Mdrawcellw*Mpreviewscalex));//~v79zR~
//    			usetmonospace(Gplayoutpreview,Pdata,pdbcs,Plen,(int)(Mdrawcellw*Mpreviewscalex));//~va1DR~
                opt=USMSO_WWSCRPRT;                                //~va1DR~
      			usetmonospace(opt,Gplayoutpreview,Pdata,pdbcs,Plen,(int)(Mdrawcellw*Mpreviewscalex));//~va1DI~
            }                                                      //~vX03I~
            gdk_draw_layout(Gppixmappreview,Gpgcpreview,xx,yy,Gplayoutpreview);//~vXXEI~
//#ifdef XXX                                                       //~va1DR~
#ifdef UTF8SUPPH                                                   //~va1DI~
        }                                                          //~vXXEI~
        else                                                       //~vXXEI~
        {                                                          //~vXXEI~
		    pdbcs=ugetdbcschktbl(Pdata,Plen);                      //~va1DI~
        	pdbcse=pdbcs+Plen;                                     //~va1DI~
    		dx=Mdrawcellw*Mpreviewscalex;                          //~vXXEI~
            for (ii=0,putf8=(char*)putf80,pc=Pdata;ii<utf8len;ii++,pc++,xx+=dx)//~vXXER~
            {                                                      //~vXXER~
                putf8next=g_utf8_next_char(putf8);                 //~vXXER~
                pango_layout_set_text(Gplayoutpreview,putf8,(ULONG)putf8next-(ULONG)putf8);//~vXXER~
                pango_layout_set_attributes(Gplayoutpreview,Gppangoattrlist);//~vXXER~
                gdk_draw_layout(Gppixmappreview,Gpgcpreview,xx,yy,Gplayoutpreview);//~vXXER~
//#ifndef UTF8SUPPJ                                                //~va1DR~
//        	    if (UDBCSCHK_ISDBCS1ST(*pc))                       //~va1DR~
//#else                                                            //~va1DR~
//                if (SJIS1(*pc))                                  //~va1DR~
//#endif                                                           //~va1DR~
UTRACED("preview dbcs ",pdbcs,1);                                  //~va1DI~
//*for locale code                                                 //~va20I~
		        if (*pdbcs==UDBCSCHK_DBCS1ST)  //require  2byte write for DBCS//~va1DI~
                {                                                  //~vXXER~
        		    ch=g_utf8_get_char(putf8);                     //~va1DI~
                    dbcssz=XESUB_DBCSSPLITCTR(pdbcs,(int)((ULONG)pdbcse-(ULONG)pdbcs),0);//~va1DM~
UTRACEP("preview ucs=%x,dbcssz=%d\n",ch,dbcssz);                   //~va1DR~
UTRACED("preview pc ",pc,dbcssz);                                  //~va1DI~
//                  pc++;                                          //~va1DR~
//                  xx+=dx;                                        //~va1DI~
                    pc+=(dbcssz-1);                                //~va1DI~
                    pdbcs+=dbcssz;                                 //~va1DI~
                    xx+=dx*(uprtwcwidth(0,ch,dbcssz)-1);           //~va1DI~
                }                                                  //~vXXER~
                else                                               //~va1DI~
                    pdbcs++;                                       //~va1DI~
                putf8=putf8next;                                   //~vXXER~
            }                                                      //~vXXER~
        }                                                          //~vXXEI~
#endif    //UTF8SUPPH                                              //~va1DR~
//dprintf("prttext last=(%d,%d)-->(%lf,%lf)\n",Pposx,Pposy,xx,yy); //~vX03R~
    }                                                              //~vXXEI~
    else                                                           //~vXXEI~
    {                                                              //~vXXEI~
        pdbcs=ugetdbcschktbl(Pdata,Plen);                          //~vX03I~
#ifdef UTF8SUPPH                                                   //~va1cR~
        pdbcse=pdbcs+Plen;                                         //~va1cR~
#endif                                                             //~va1cR~
    	dx=Mdrawcellw;                                             //~vXXEI~
    	xx=(gdouble)Pposx;//(gdouble)Mprtrect.left;                //~vXXEI~
	    yy=(gdouble)(-Pposy-Mprtfontascendant);//(Mprtrect.top-Pposy);//font baseline pos from origin(set at vieportfile)//~vXXEI~
	    pfont=Mpprtfont;                                           //~vXXEI~
        xsbcswidth=uprtgetsbcswidth(0,pfont);                      //~va1DI~
//printf("font=%p\n",pfont);                                       //~vXXER~
        pgl=gnome_glyphlist_from_text_dumb(pfont,print_color,0.0,0.0,"");//kerning=letterspace=0//~vXXER~
//      if (Mchkpfontsz)                                           //~vX03R~
            gnome_glyphlist_advance (pgl,FALSE);//move pen by cellszw//~vXXER~
//      else                                                       //~vX03R~
//      {                                                          //~vX03R~
//          gnome_glyphlist_advance (pgl,TRUE);//move pen by font standard advance vector//~vX03R~
//          gnome_glyphlist_moveto(pgl,xx,yy);                     //~vX03R~
//      }                                                          //~vX03R~
        for (ii=0,putf8=(char*)putf80,pc=Pdata;ii<utf8len;ii++,pc++)//~vXXER~
        {                                                          //~vXXER~
        	putf8next=g_utf8_next_char(putf8);                     //~vXXER~
            ch=g_utf8_get_char(putf8);                             //~vXXER~
UTRACEP("uprttext utf8 charii=%d,ch=%x,xx=%lf,*pc=%02x\n ",ii,ch,xx,*pc);//~va1cR~
            glyph=gnome_font_lookup_default(pfont,ch);             //~vX03R~
UTRACEP("uprttext utf8 pgl=%p,font=%p,griph=%p\n",pgl,pfont,glyph);//~va1cR~
#ifdef UTF8SUPPH                                                   //~va1DI~
        	glyphwidth=gnome_font_get_glyph_width(pfont,glyph);    //~va1DR~
            widesw=(glyphwidth>xsbcswidth);                        //~va1DI~
		    if (!glyphwidth && *pdbcs==UDBCSCHK_DBCS1ST)  //require  2byte write for DBCS//~va4aR~
                widesw=1;       //sometime gnome_font_get_glyph_width returns sbcs width for DBCS//~va4aI~
            xcellwidth=dx+dx*widesw;                               //~va1DI~
            xoffs=(glyphwidth && glyphwidth<xcellwidth ? (xcellwidth-glyphwidth)/2:0);//~va1DR~
        	UTRACEP("ucs=%02x glyphwidth=%lf,sbcswidth=%lf,cellwidth=%lf,xoffs=%lf\n",ch,glyphwidth,xsbcswidth,xcellwidth,xoffs);//~va1DR~
#endif                                                             //~va1DI~
//          if (Mchkpfontsz)                                       //~vX03R~
//          {                                                      //~vX03R~
#ifdef UTF8SUPPH                                                   //~va1DI~
                gnome_glyphlist_moveto(pgl,xx+xoffs,yy); //move to center of cell//~va1DI~
#else                                                              //~va1DR~
                gnome_glyphlist_moveto(pgl,xx,yy);                 //~vXXER~
#endif                                                             //~va1DI~
                xx+=dx;                                            //~vXXER~
//*for locale code                                                 //~va20I~
		        if (*pdbcs==UDBCSCHK_DBCS1ST)  //require  2byte write for DBCS//~vX03I~
                {                                                  //~vX03I~
#ifdef UTF8SUPPH                                                   //~va1cR~
                    dbcssz=XESUB_DBCSSPLITCTR(pdbcs,(int)((ULONG)pdbcse-(ULONG)pdbcs),0);//~va1cR~
                  if (Mwwscrprt)    //wysisig option on page dialog//~va1DI~
                    xx+=dx*(dbcssz-1);                             //~va1cR~
                  else                                             //~va1DI~
                  	xx+=dx*widesw;                                 //~va1DR~
                    pdbcs+=dbcssz;                                 //~va1cR~
                    pc+=dbcssz-1;                                  //~va1DI~
UTRACEP("uprttext utf8 dbcssz=%d\n ",dbcssz);                      //~va1cR~
#else                                                              //~va1cR~
                    xx+=dx;                                        //~vXXER~
                    pdbcs+=2;                                      //~vX03I~
#endif                                                             //~va1cR~
                }                                                  //~vX03I~
                else                                               //~vX03I~
                    pdbcs++;                                       //~vX03I~
//          }                                                      //~vX03R~
            gnome_glyphlist_glyph(pgl,glyph);                      //~vX03R~
            putf8=putf8next;                                       //~vXXEI~
        }                                                          //~vXXER~
    //  gnome_print_moveto(Gpprtctxt,xx,yy);                       //~vXXER~
        gnome_print_moveto(Gpprtctxt,0.0,0.0);                     //~vXXER~
        gnome_print_glyphlist(Gpprtctxt,pgl);                      //~vXXER~
        gnome_glyphlist_unref(pgl);                                //~vXXER~
//printf("prttext last=(%d,%d)-->(%lf,%lf) dx=%d\n",Pposx,Pposy,xx,yy,Mdrawcellw);//~vX03R~
    }                                                              //~vXXEI~
//if (!utfrc)                                                      //~v79zR~
//  g_free(putf80);                                                //~v79zR~
    return;                                                        //~vXXEI~
}//uprttextout                                                     //~vXXER~
#endif //ARM                                                       //~v@@@M~
#ifdef UTF8UCS2                                                    //~va20I~
//***************************************************************************////~va20I~
//* print dd fmt text                                              //~va20I~
//* rc :UALLOC_FAILED,4                                            //~va20I~
//***************************************************************************////~va20I~
int uprttextoutw(int Popt,int Pxx,int Pyy,char *Pdata,char *Pdbcs,int Plen)//~va20I~
{                                                                  //~va20I~
#ifdef ARM                                                         //~v@@@I~
    return 0;                                                      //~v@@@I~
#else                                                              //~v@@@I~
    int rc,u8len,opt;                                              //~va20R~
    UCHAR *pu8,*pdbcs;                                             //~va20I~
//*******************************                                  //~va20I~
	opt=UTFDD2FO_ERRREP;                                           //~va20R~
    rc=xeutfcvdd2f(opt,Pdata,Pdbcs,Plen,&pu8,&u8len,&pdbcs); //dbcstbl is for locale code//~va20R~
    if (rc)                                                        //~va20I~
    	return rc;                                                 //~va20I~
    opt=0;                                                         //~va20I~
	rc=uprttextoutwsub(opt,Pxx,Pyy,pu8,pdbcs,u8len,Plen);          //~va20I~
    return rc;                                                     //~va20I~
#endif //ARM                                                       //~v@@@I~
}//uprttextoutw                                                    //~va20I~
#ifndef ARM                                                        //~v@@@I~
//***************************************************************************//~vXXEI~//~va20I~
//* printer text write from utf8 str                                             //~vXXEI~//~va20I~
//* dbcs tbl is old fmt(DBCS1ST/DBCS2ND)                           //~va20I~
//***************************************************************************//~vXXEI~//~va20I~
int uprttextoutwsub(int Popt,int Pposx,int Pposy,char *Pdata,char *Pdbcs,int Plen,int Plenlc)         //~vXXER~//~va20R~
{                                                                  //~vXXEI~//~va20I~
    GnomeGlyphList *pgl;                                           //~vXXEI~//~va20I~
    GnomeFont *pfont;                                              //~vXXEI~//~va20I~
	int print_color =DEFAULT_PRINTCOLOR;                           //~vXXEI~//~va20I~
    gdouble yy,xx,dx;                                              //~vXXER~//~va20I~
    gchar *putf80;                                                 //~vXXER~//~va20I~
    char *putf8,*putf8next,*pdbcs;                             //~vX03R~//~va20R~
    int writelen,ii,utf8len,dbcsid;                           //~v69sR~//~va20R~
    gunichar ch;                                                   //~vXXER~//~va20I~
    gint glyph;                                                    //~vXXEI~//~va20I~
    int widesw;                                                //~va1DR~//~va20R~
    int combinesw;                                                 //~va30I~
    gdouble xsbcswidth,glyphwidth,xcellwidth,xoffs;                //~va1DR~//~va20I~
//*******************************                                  //~vXXEI~//~va20I~
	if (!Plen)                                                     //~vXXEI~//~va20I~
    	return 0;                                                  //~vXXEI~//~va20R~
    putf80=Pdata;                                                  //~va20I~
    writelen=Plen;                                                 //~va20I~
UTRACED("uprttextwsub utf8 data",Pdata,Plen);                    //~va1cR~//~va20I~
UTRACEP("preview mode=%d,ww=%d,monospace=%d\n",Mpreviewmode,Mwwscrprt,Mmonospacepreviewfile);//~va1DR~//~va20I~
	utf8len=g_utf8_strlen (putf80,writelen);                       //~vXXEI~//~va20I~
printf("uprttextoutwsub utf8len=%d\n",utf8len);                      //~vXXER~//~va20I~
    if (Mpreviewmode)                                              //~vXXEI~//~va20I~
    {                                                              //~vXXEI~//~va20I~
        xx=Pposx*Mpreviewscalex+Mprevieworgx;                      //~vXXEI~//~va20I~
        yy=Pposy*Mpreviewscaley+Mprevieworgy;                      //~vXXEI~//~va20I~
printf("uprttextout utf8len=%d,xx=%lf,yy=%lf\n",utf8len,xx,yy);  //~vXXER~//~va20I~
		    pdbcs=Pdbcs;                                           //~va20I~
    		dx=Mdrawcellw*Mpreviewscalex;                          //~vXXEI~//~va20I~
//no monospace consideration for preview                           //~va20I~
            for (ii=0,putf8=(char*)putf80;ii<utf8len;ii++,xx+=dx)//~vXXER~//~va20I~
            {                                                      //~vXXER~//~va20I~
                putf8next=g_utf8_next_char(putf8);                 //~vXXER~//~va20I~
                pango_layout_set_text(Gplayoutpreview,putf8,(ULONG)putf8next-(ULONG)putf8);//~vXXER~//~va20I~
                pango_layout_set_attributes(Gplayoutpreview,Gppangoattrlist);//~vXXER~//~va20I~
                gdk_draw_layout(Gppixmappreview,Gpgcpreview,xx,yy,Gplayoutpreview);//~vXXER~//~va20I~
UTRACED("preview dbcs ",pdbcs,1);                                  //~va1DI~//~va20I~
                dbcsid=*pdbcs;                                     //~va20I~
//for utf8 file dd2f changed dbcscstbl                             //~va20I~
		        if (dbcsid==UDBCSCHK_DBCS1ST)  //require  2byte write for DBCS//~va1DI~//~va20R~
                {                                                  //~vXXER~//~va20I~
        		    ch=g_utf8_get_char(putf8);                     //~va1DI~//~va20I~
                    pdbcs+=2;                                 //~va1DI~//~va20I~
                    xx+=dx;           //~va1DI~                    //~va20I~
                }                                                  //~vXXER~//~va20I~
                else                                               //~va1DI~//~va20I~
                    pdbcs++;                                       //~va1DI~//~va20I~
                putf8=putf8next;                                   //~vXXER~//~va20I~
            }                                                      //~vXXER~//~va20I~
//dprintf("prttext last=(%d,%d)-->(%lf,%lf)\n",Pposx,Pposy,xx,yy); //~vX03R~//~va20I~
    }                                                              //~vXXEI~//~va20I~
    else                                                           //~vXXEI~//~va20I~
    {                                                              //~vXXEI~//~va20I~
        pdbcs=Pdbcs;                                               //~va20I~
    	dx=Mdrawcellw;                                             //~vXXEI~//~va20I~
    	xx=(gdouble)Pposx;//(gdouble)Mprtrect.left;                //~vXXEI~//~va20I~
	    yy=(gdouble)(-Pposy-Mprtfontascendant);//(Mprtrect.top-Pposy);//font baseline pos from origin(set at vieportfile)//~vXXEI~//~va20I~
	    pfont=Mpprtfont;                                           //~vXXEI~//~va20I~
        xsbcswidth=uprtgetsbcswidth(0,pfont);                      //~va1DI~//~va20I~
//printf("font=%p\n",pfont);                                       //~vXXER~//~va20I~
        pgl=gnome_glyphlist_from_text_dumb(pfont,print_color,0.0,0.0,"");//kerning=letterspace=0//~vXXER~//~va20I~
            gnome_glyphlist_advance (pgl,FALSE);//move pen by cellszw//~vXXER~//~va20I~
        for (ii=0,putf8=(char*)putf80;ii<utf8len;ii++)//~vXXER~    //~va20I~
        {                                                          //~vXXER~//~va20I~
        	putf8next=g_utf8_next_char(putf8);                     //~vXXER~//~va20I~
            ch=g_utf8_get_char(putf8);                             //~vXXER~//~va20I~
UTRACEP("uprttext utf8 Mwwscrprt=%d,charii=%d,ch=%x,xx=%lf\n ",Mwwscrprt,ii,ch,xx);//~va30I~
            glyph=gnome_font_lookup_default(pfont,ch);             //~vX03R~//~va20I~
        	glyphwidth=gnome_font_get_glyph_width(pfont,glyph);    //~va1DR~//~va20I~
            combinesw=0;                                           //~va30I~
	        if (UTF_COMBINEMODE()    //combine mode                //~va30R~
          	||  !Mwwscrprt          //combine for print if not wysisig//~va30I~
            )                                                      //~va30I~
            	combinesw=(glyphwidth==0) && (wcwidth(ch)==0);     //~va30R~
UTRACEP("xxecsub2.uprttextoutwsub utf8 ucs=%04x,pgl=%p,font=%p,griph=%x,glyphwidth=%lf,combinesw=%d\n",ch,pgl,pfont,glyph,glyphwidth,combinesw);//~va30R~//+vb91R~
            xoffs=0;                                               //~va20I~
          if (combinesw)                                           //~va30I~
            xoffs=-dx;                                             //~va30I~
          else                                                     //~va30I~
          if (Mwwscrprt)    //wysisig option on page dialog        //~va20I~
          {                                                        //~va20I~
            widesw=(glyphwidth>xsbcswidth);                        //~va1DI~//~va20I~
            xcellwidth=dx+dx*widesw;                               //~va1DI~//~va20I~
            xoffs=(glyphwidth && glyphwidth<xcellwidth ? (xcellwidth-glyphwidth)/2:0);//~va1DR~//~va20I~
          }                                                        //~va20I~
                gnome_glyphlist_moveto(pgl,xx+xoffs,yy); //move to center of cell//~va1DI~//~va20I~
                dbcsid=*pdbcs;                                     //~va20I~
        	UTRACEP("ucs=%02x dbcsid=%x,glyphwidth=%lf,sbcswidth=%lf,cellwidth=%lf,xoffs=%lf,dx=%lf\n",ch,dbcsid,glyphwidth,xsbcswidth,xcellwidth,xoffs,dx);//~va1DR~//~va30R~
//for utf8 file dd2f changed dbcscstbl                             //~va20I~
		        if (dbcsid==UDBCSCHK_DBCS1ST)  //dd2f change dbcstbl to locale code from ucs//~va20R~
                {                                                  //~vX03I~//~va20I~
                  if (Mwwscrprt||!glyphwidth)   //wysisig option on page dialog,glyph width mwy be 0 //~va1DI~//~va20R~
                    xx+=dx+dx;                                     //~va20I~
                  else                                             //~va1DI~//~va20I~
                  	xx+=glyphwidth;                                 //~va1DR~//~va20I~
                    pdbcs+=2;                                      //~va20I~
                }                                                  //~vX03I~//~va20I~
                else                                               //~vX03I~//~va20I~
                {                                                  //~va20I~
				 if (!combinesw)                                   //~va30I~
                 {                                                 //~va30I~
                  if (Mwwscrprt||!glyphwidth)    //wysisig option on page dialog//~va20R~
                    xx+=dx;                                        //~va20I~
                  else                                             //~va20I~
                  	xx+=glyphwidth;                                //~va20I~
                 }                                                 //~va30I~
                    pdbcs++;                                       //~vX03I~//~va20I~
                }                                                  //~va20I~
            gnome_glyphlist_glyph(pgl,glyph);                      //~vX03R~//~va20I~
            putf8=putf8next;                                       //~vXXEI~//~va20I~
        }                                                          //~vXXER~//~va20I~
        gnome_print_moveto(Gpprtctxt,0.0,0.0);                     //~vXXER~//~va20I~
        gnome_print_glyphlist(Gpprtctxt,pgl);                      //~vXXER~//~va20I~
        gnome_glyphlist_unref(pgl);                                //~vXXER~//~va20I~
    }                                                              //~vXXEI~//~va20I~
    return 0;                                                        //~vXXEI~//~va20R~
}//uprttextoutwsub                                                 //~vXXER~//~va20R~
#endif //ARM                                                       //~v@@@M~
#endif                                                             //~va20I~
//***************************************************************************//~vXXEI~
//* printer text write from screen data utf string(preview),or char(print)//~vX03R~
//***************************************************************************//~vXXEI~
void uprtscrtextout(char *Pputf8str,int Pposx,int Pposy,int Plen)  //~vXXEI~
{                                                                  //~vXXEI~
#ifndef ARM                                                        //~v@@@I~
    GnomeGlyphList *pgl;                                           //~vXXEI~
    GnomeFont *pfont;                                              //~vXXEI~
	int print_color =DEFAULT_PRINTCOLOR;                           //~vXXEI~
    gdouble yy,xx;                                                 //~vXXEI~
    gunichar ch;                                                   //~vXXEI~
    gint glyph;                                                    //~vXXEI~
//*******************************                                  //~vXXEI~
	if (!Plen)                                                     //~vXXEI~
    	return;                                                    //~vXXEI~
    if (Mpreviewmode)                                              //~vX03R~
    {                                                              //~vXXEI~
        xx=Pposx*Mpreviewscalex+Mprevieworgx;                      //~vXXEM~
        yy=Pposy*Mpreviewscaley+Mprevieworgy;                      //~vXXEM~
    	pango_layout_set_text(Gplayoutpreview,Pputf8str,Plen);     //~vXXER~
        pango_layout_set_attributes(Gplayoutpreview,Gppangoattrlist);//~vXXER~
//printf("text xx=%d--%lf,yy=%d-->%lf,len=%d\n",Pposx,xx,Pposy,yy,Plen);//~vXXER~
      	gdk_draw_layout(Gppixmappreview,Gpgcpreview,xx,yy,Gplayoutpreview);//~vXXER~
    }                                                              //~vXXEI~
    else                                                           //~vXXEI~
    {                                                              //~vXXEI~
        pfont=Mpprtfont;	//@@@@                                 //~vX03R~
        xx=(gdouble)Pposx;//(gdouble)Mprtrect.left;                //~vXXER~
        yy=(gdouble)(-Pposy-Mprtfontascendant);//(Mprtrect.top-Pposy);//font baseline pos from origin(set at vieportfile)//~vXXER~
        pgl=gnome_glyphlist_from_text_dumb(pfont,print_color,0.0,0.0,"");//kerning=letterspace=0//~vXXER~
        gnome_glyphlist_advance (pgl,FALSE);//not move pen by font standard advance vector//~vXXER~
        gnome_glyphlist_moveto(pgl,xx,yy);                         //~vXXER~
        ch=g_utf8_get_char(Pputf8str);                             //~vXXER~
        glyph=gnome_font_lookup_default (pfont,ch);                //~vXXER~
        gnome_glyphlist_glyph (pgl,glyph);                         //~vXXER~
        gnome_print_moveto(Gpprtctxt,0.0,0.0);                     //~vXXER~
        gnome_print_glyphlist(Gpprtctxt,pgl);                      //~vXXER~
        gnome_glyphlist_unref(pgl);                                //~vXXER~
//      dprintf("scrprint ch=%c,len=%d,x=%lf,y=%lf,posx=%d,posy=%d\n",*Pputf8str,Plen,xx,yy,Pposx,Pposy);//~vX03R~
    }                                                              //~vXXEI~
#endif //ARM                                                       //~v@@@I~
    return;                                                        //~vXXEI~
}//uprtscrtextout                                                  //~vXXEI~
#ifndef ARM                                                        //~v@@@I~
//***************************************************************************//~vXXEI~
//* printer line write                                             //~vXXEI~
//***************************************************************************//~vXXEI~
void uprtlineout(int Pposx0,int Pposy0,int Pposx1,int Pposy1)      //~vXXER~
{                                                                  //~vXXEI~
	gdouble x0,y0,x1,y1;                                           //~vXXEI~
//*******************************                                  //~vXXEI~
    if (Mpreviewmode)                                              //~vXXEI~
    {                                                              //~vXXEI~
        x0=Pposx0*Mpreviewscalex+Mprevieworgx;                     //~vXXEI~
        y0=Pposy0*Mpreviewscaley+Mprevieworgy;                     //~vXXEI~
        x1=Pposx1*Mpreviewscalex+Mprevieworgx;                     //~vXXEI~
        y1=Pposy1*Mpreviewscaley+Mprevieworgy;                     //~vXXEI~
	    gdk_draw_line(Gppixmappreview,Gpgcpreview,x0,y0,x1,y1);    //~vXXEI~
    }                                                              //~vXXEI~
    else                                                           //~vXXEI~
    {                                                              //~vXXEI~
        x0=(gdouble)Pposx0;                                        //~vXXER~
        y0=(gdouble)(-Pposy0);                                     //~vXXER~
        x1=(gdouble)Pposx1;                                        //~vXXER~
        y1=(gdouble)(-Pposy1);                                     //~vXXER~
        gnome_print_line_stroked(Gpprtctxt,x0,y0,x1,y1);           //~vXXER~
	}                                                              //~vXXEI~
//dprintf("linedraw parm=(%d,%d-->%d,%d line %lf,%lf-->%lf,%lf\n",Pposx0,Pposy0,Pposx1,Pposy1,x0,y0,x1,y1);//~vX03R~
    return;                                                        //~vXXEI~
}//uprtlineout;                                                    //~vXXEI~
//***************************************************************************//~vXXEI~
//* preview font scale set                                         //~vXXEI~
//***************************************************************************//~vXXEI~
void upreviewsetfontscale(int Phcopysw,int Pcellw,int Pcellh,gdouble Pfscalex,gdouble Pfscaley)//~vXXER~
{                                                                  //~vXXEI~
	int fontsize;                                                  //~vXXER~
    int fontw,fonth;                                               //~vXXER~
//  gdouble fscalex,fscaley,fscale;                                //~vXXER~
    gdouble frateh,fratew,ffontsize;                               //~vXXER~
    PangoAttribute *ppa;                                           //~vXXEI~
    PangoAttrList  *ppal;                                          //~vXXER~
//*******************************                                  //~vXXEI~
//    stretch=pango_font_description_get_stretch(Gfontdata[1].FDfontdesc);//~vXXER~
//    dprintf("stretch=%d\n",stretch);                             //~vX03R~
//    stretch=3;                                                   //~vXXER~
//    pango_font_description_set_stretch(Gfontdata[1].FDfontdesc,stretch);//~vXXER~
UTRACEP("previewsetfontscale Phcopysw=%d\n",Phcopysw);             //~va1cR~
    if (Phcopysw)                                                  //~vXXEI~
    	ugetfontmetrics(Gfontdata[0].FDfontdesc,&fontw,&fonth,0);//0 no moospace chk//~vXXER~
    else                                                           //~vXXEI~
    	ugetfontmetrics(Gfontdata[1].FDfontdesc,&fontw,&fonth,&Mmonospacepreviewfile);//~vXXER~
//    dprintf("preview scrfont ww=%d,hh=%d prtfont ww=%d,hh=%d,scalex=%lf,scaley=%lf\n",fontw,fonth,Mpfontszh,Mpfontszw,Pfscalex,Pfscaley);//~v69VR~
//    fscalex=Pfscalex*Pcellw/fontw;                               //~vXXER~
//    fscaley=Pfscaley*Pcellh/fonth;                               //~vXXER~
//    if (fscalex<fscaley)                                         //~vXXER~
//        fscale=fscalex;                                          //~vXXER~
//    else                                                         //~vXXER~
//        fscale=fscaley;                                          //~vXXER~
//  dprintf("preview font scale set size=%d,scaley=%lf,scalex=%lf,scale=%lf\n",fontsize,fscalex,fscaley,fscale);//~vX03R~
//  ppa=pango_attr_scale_new(fscale);                              //~vXXER~
    frateh=(gdouble)Mpfontszh/fonth*Pfscaley;                      //~vXXER~
    fratew=(gdouble)Mpfontszw/fontw*Pfscalex;                      //~vXXER~
    dprintf("preview frateh=%lf,fratew=%lf\n",frateh,fratew);      //~vX03R~
    if (frateh<fratew)                                             //~vXXEI~
    	ffontsize=Mpfontszh*frateh;                                //~vXXER~
    else                                                           //~vXXEI~
    	ffontsize=Mpfontszh*fratew;                                //~vXXER~
	fontsize=(int)(ffontsize*PANGO_SCALE);                         //~vXXER~
    dprintf("preview frateh=%lf,fratew=%lf ffontsize=%lf %d(pangounit=%d)\n",frateh,fratew,ffontsize,fontsize,PANGO_SCALE);//~vX03R~
    ppa=pango_attr_size_new(fontsize);                             //~vXXER~
    ppa->start_index=0;         //start byte offset                //~vXXEI~
    ppa->end_index=G_MAXINT;    //end                              //~vXXEI~
    ppal=pango_attr_list_new();                                    //~vXXEI~
    pango_attr_list_insert(ppal,ppa);                              //~vXXER~
    Gppangoattrlist=ppal;	//unref at gxepage                     //~vXXER~
#ifdef XXX  //matrix support from 1.6                              //~vXXEI~
    const PangoMatrix *ppm=pangeo_context_get_matrix(Gppangocontext);//~vXXER~
    dprintf("pango matrix old ppm=%p,xx=%lf,xy=%lf,yx=%x,yy=%lf,x0=%lf,y0=%lf\n",//~vX03R~
            ppm,ppm->xx,ppm->xy,ppm->yx,ppm->yy,ppm->x0,ppm.y0);   //~vXXER~
    pango_matrix_scale(ppm,Pfscalex,Pfscaley);                     //~vXXEI~
    ppm->xx=Pfscaley;ppm->yy=Pfscaley;                             //~vXXEI~
    ppm=pangeo_context_get_matrix(Gppangocontext);                 //~vXXER~
    dprintf("pango matrix new ppm=%p,xx=%lf,xy=%lf,yx=%x,yy=%lf,x0=%lf,y0=%lf\m",//~vX03R~
            ppm,ppm->xx,ppm->xy,ppm->yx,ppm->yy,ppm->x0,ppm.y0);   //~vXXER~
#endif                                                             //~vXXEI~
    return;                                                        //~vXXEI~
}//upreviewsetfontscale                                            //~vXXEI~
#endif //ARM                                                       //~v@@@I~
#ifdef UTF8UCS2                                                    //~va20I~
//===============================================================================//~va20I~
//trace layout glyph gemetry                                       //~va20I~
//===============================================================================//~va20I~
void utracelayout(PangoLayout *Pplayout)                           //~va20I~
{                                                                  //~va20I~
#ifndef ARM                                                        //~v@@@I~
  	GSList *tmp_list;                                              //~va20I~
  	PangoLayoutIter *iter;                                         //~va20I~
    PangoLayoutLine *line;                                         //~va20I~
    PangoLayoutRun *run;                                           //~va20I~
    PangoGlyphString *pglyphstr;                                   //~va20I~
    PangoGlyphInfo *pglyphinfo;                                    //~va20I~
    PangoGlyphGeometry *pgeom;                                     //~va20I~
    int ii;                                                        //~va20I~
//******************************                                   //~va20I~
UTRACEP("utracelayout\n");                                         //~va20I~
  	iter = pango_layout_get_iter(Pplayout);                        //~va20I~
    line=pango_layout_iter_get_line (iter);                        //~va20I~
    if (!line)  //split dbcs is set to layout                      //~va20I~
        return;                                                    //~va20I~
    tmp_list=line->runs;                                           //~va20I~
  	while(tmp_list)                                                //~va20I~
  	{                                                              //~va20I~
    	run=tmp_list->data;                                        //~va20I~
		UTRACEP("run @@@=%p\n",run);                               //~va20I~
    	if (!run)  //split dbcs is set to layout                   //~va20I~
        	return;                                                //~va20I~
    	pglyphstr=run->glyphs;                                     //~va20I~
    	if (!pglyphstr)  //split dbcs is set to layout             //~va20I~
        	return;                                                //~va20I~
		UTRACEP("temp list=%p\n",pglyphstr);                       //~va20I~
    	for (ii=pglyphstr->num_glyphs,pglyphinfo=pglyphstr->glyphs;ii>0;ii--,pglyphinfo++)//~va20I~
    	{                                                          //~va20I~
        	pgeom=&(pglyphinfo->geometry);                         //~va20I~
			UTRACEP("geometry ii=%d,offs_x=%d,offs_y=%d,width=%d\n",ii,pgeom->x_offset,pgeom->y_offset,pgeom->width);//~va20R~
    	}                                                          //~va20I~
    	tmp_list=tmp_list->next;                                   //~va20I~
  }//runs                                                          //~va20I~
  	pango_layout_iter_free (iter);                                 //~va20I~
#endif //ARM                                                       //~v@@@I~
  	return;                                                        //~va20I~
}//utracelayout                                                    //~va20I~
//===============================================================================//~va30R~
//get layoutinfo:glyphctr,width0 glyph                             //~va30R~
//===============================================================================//~va30R~
#ifndef ARM                                                        //~v@@@M~
int csublyoutinfo(int Popt,PangoLayout *Pplayout,int *Pretinfo)    //~va30R~
{                                                                  //~va30R~
  	GSList *tmp_list;                                              //~va30R~
  	PangoLayoutIter *iter;                                         //~va30R~
    PangoLayoutLine *line;                                         //~va30R~
    PangoLayoutRun *run;                                           //~va30R~
    PangoGlyphString *pglyphstr;                                   //~va30R~
    int glyphctr=0;                                                //~va30R~
//******************************                                   //~va30R~
UTRACEP("csublayoutinfo\n");                                       //~va30R~
    Pretinfo[CSUBLI_CTR]=0;                                        //~va30R~
  	iter = pango_layout_get_iter(Pplayout);                        //~va30R~
    line=pango_layout_iter_get_line (iter);                        //~va30R~
    if (!line)  //split dbcs is set to layout                      //~va30R~
        return 4;                                                  //~va30R~
    tmp_list=line->runs;                                           //~va30R~
  	while(tmp_list)                                                //~va30R~
  	{                                                              //~va30R~
    	run=tmp_list->data;                                        //~va30R~
		UTRACEP("run @@@=%p\n",run);                               //~va30R~
    	if (!run)  //split dbcs is set to layout                   //~va30R~
        	break;                                                 //~va30R~
    	pglyphstr=run->glyphs;                                     //~va30R~
    	if (!pglyphstr)  //split dbcs is set to layout             //~va30R~
        	break;                                                 //~va30R~
		UTRACEP("temp list=%p\n",pglyphstr);                       //~va30R~
        glyphctr+=pglyphstr->num_glyphs;                           //~va30R~
    	tmp_list=tmp_list->next;                                   //~va30R~
    }//runs                                                        //~va30R~
  	pango_layout_iter_free (iter);                                 //~va30R~
    Pretinfo[CSUBLI_CTR]=glyphctr;                                 //~va30R~
UTRACEP("csublayoutinfo glyphctr=%d\n",glyphctr);                  //~va30R~
  	return 0;                                                      //~va30R~
}//csublayoutinfo                                                  //~v@@@R~
#endif //ARM                                                       //~v@@@M~
#endif                                                             //~va20I~
#ifndef ARM                                                        //~v@@@I~
//===============================================================================//~vX03M~
//set monospaceing                                                 //~vX03M~
//===============================================================================//~vX03M~
//void usetmonospace(PangoLayout *Pplayout,char *Ppdbcs,int Plen,int Pcellw)//~v79zR~
//void usetmonospace(PangoLayout *Pplayout,char *Ppdata,char *Ppdbcs,int Plen,int Pcellw)//~va1DR~
void usetmonospace(int Popt,PangoLayout *Pplayout,char *Ppdata,char *Ppdbcs,int Plen,int Pcellw)//~va1DI~
{                                                                  //~vX03M~
  	GSList *tmp_list;                                              //~vX03M~
  	PangoLayoutIter *iter;                                         //~vX03M~
    PangoLayoutLine *line;                                         //~vX03M~
    PangoLayoutRun *run;                                           //~vX03M~
    PangoGlyphString *pglyphstr;                                   //~vX03M~
    PangoGlyphInfo *pglyphinfo;                                    //~vX03M~
    PangoGlyphGeometry *pgeom;                                     //~vX03M~
    char *pdbcs,*pdbcse;                                          //~vX03M~//~va30R~
    char *pdata;                                                   //~v79zI~
    int ii;                                                        //~vX03M~
    int oldw,neww;                                                 //~v69VI~
#ifdef UTF8SUPPH                                                   //~v7axI~
	int dbcssz;                                                    //~v7axI~
#endif                                                             //~v7axI~
#ifdef UTF8UCS2                                                    //~va20I~
	int swutf8data;                                                //~va20I~
    int oldx,oldy,wwpango,w0ctr=0,glyphctr=0,swtoperr,combspan,wwtotal=0;//~va30R~
    PangoGlyphGeometry *pgeomlast;                                 //~va30R~
#endif                                                             //~va20I~
//******************************                                   //~vX03M~
#ifdef UTF8UCS2                                                    //~va20I~
	swutf8data=Popt & USMSO_DDFMT;   //scr data is dd fmt,pdata is not lc,dont access//~va20I~
#endif                                                             //~va20I~
  	iter = pango_layout_get_iter(Pplayout);                        //~vX03R~
    line=pango_layout_iter_get_line (iter);                        //~vX03M~
UTRACEP("usetmonospace opt=%x,len=%d\n",Popt,Plen);                //~va30R~
    if (!line)  //split dbcs is set to layout                      //~v69VR~
        return;                                                    //~v69VR~
    tmp_list=line->runs;                                           //~vX03M~
//dprintf("list@@@=%p\n",tmp_list);                                //~v69VR~
  pdata=Ppdata;                                                    //~v79zI~
  pdbcs=Ppdbcs;                                                    //~v69VI~
  pdbcse=Ppdbcs+Plen;		//end addr;                            //~v69VI~
  while(tmp_list)                                                  //~v69VR~
  {                                                                //~v69VI~
    run=tmp_list->data;                                            //~vX03M~
//dprintf("run @@@=%p\n",run);                                     //~v69VR~
    if (!run)  //split dbcs is set to layout                       //~v69VR~
        return;                                                    //~v69VR~
    pglyphstr=run->glyphs;                                         //~vX03M~
    if (!pglyphstr)  //split dbcs is set to layout                 //~v69VR~
        return;                                                    //~v69VR~
UTRACEP("temp list=%p\n",pglyphstr);                               //~va1cR~
//  pdbcs=Ppdbcs;                                                  //~v69VR~
//  pdbcse=Ppdbcs+Plen;		//end addr;                            //~v69VR~
	wwpango=Pcellw*PANGO_SCALE;                                    //~va30R~
    for (ii=pglyphstr->num_glyphs,pglyphinfo=pglyphstr->glyphs;ii>0;ii--,pglyphinfo++)//~vX03M~
    {                                                              //~vX03M~
        glyphctr++;                                                //~va30R~
        pgeom=&(pglyphinfo->geometry);                             //~vX03M~
        pgeomlast=pgeom;                                           //~va30R~
	    oldw=pgeom->width;                                         //~v69VI~
	    oldx=pgeom->x_offset;                                      //~va30R~
	    oldy=pgeom->y_offset;                                      //~va30R~
UTRACEP("geometry ii=%d,offs_x=%d,offs_y=%d,width=%d cellw=%d\n",ii,pgeom->x_offset,pgeom->y_offset,pgeom->width,Pcellw);//~va1cR~
//      if (*pdbcs==UDBCSCHK_DBCS1ST && pdbcs+1<pdbcse)  //require  2byte write for DBCS//~v79zR~
#ifdef UTF8SUPPH                                                   //~va1cR~
		dbcssz=1;	//sbcs                                         //~va1cR~
#ifdef UTF8UCS2                                                    //~va20I~
        if ((swutf8data||*pdata>=0x80)                             //~va20I~
#else                                                              //~va20I~
        if (*pdata>=0x80                                           //~va1cR~
#endif                                                             //~va20I~
//*after ucs dbcsid is converted to locale dbcsid                  //~va20I~
        &&  *pdbcs==UDBCSCHK_DBCS1ST                               //~va1cR~
        &&  pdbcs+(dbcssz=XESUB_DBCSSPLITCTR(pdbcs,(int)((ULONG)pdbcse-(ULONG)pdbcs),0))<=pdbcse//~va1cR~
        )                                                          //~va1cR~
        {                                                          //~va1cR~
            neww=                                                  //~va1cR~
	        pgeom->width=(Pcellw+Pcellw)*PANGO_SCALE;              //~va1cR~
          	if (Popt & USMSO_WWSCRPRT)	//wysiwig print            //~va1DI~
            	pgeom->width=(Pcellw*dbcssz)*PANGO_SCALE;	//for dbcs padding space//~va1DR~
            pdata+=dbcssz; //print width is 2 column but data is 3-4 byte//~va1cR~
            pdbcs+=dbcssz; //after 3/4 byte dbcs "." is padded by csublocale2utfscr//~va1cR~
        }                                                          //~va1cR~
#else                                                              //~va1cR~
        if (*pdata>=0x80 && *pdbcs==UDBCSCHK_DBCS1ST && pdbcs+1<pdbcse)  //require  2byte write for DBCS//~v79zI~
        {                                                          //~vX03M~
            neww=                                                  //~v69VI~
	        pgeom->width=(Pcellw+Pcellw)*PANGO_SCALE;              //~vX03M~
//          if (Pcellw>Pfontw)                                     //~vX03M~
//      	    pgeom->x_offset=(Pcellw-Pfontw)*PANGO_SCALE;       //~vX03M~
            pdata+=2;                                              //~v79zI~
            pdbcs+=2;                                              //~vX03M~
        }                                                          //~vX03M~
#endif                                                             //~va1cR~
        else                                                       //~vX03M~
        {                                                          //~vX03M~
        	neww=                                                  //~v69VI~
	        pgeom->width=Pcellw*PANGO_SCALE;                       //~vX03M~
            pdata++;                                               //~v79zI~
            pdbcs++;                                               //~vX03M~
        }                                                          //~vX03M~
        if (oldw)                                                  //~va30R~
        	pgeom->x_offset=(neww-oldw)/2;                         //~va30R~
        if (Popt & USMSO_COMBINEOF)	//1 column for each combining char//~va30R~
        {                                                          //~va30R~
	        if (!oldw)	//width=0                                  //~va30R~
            {                                                      //~va30R~
		        pgeom->width=wwpango;                              //~va30R~
//		        pgeom->y_offset=0;                                 //~va30R~
                while (oldx<=0) { oldx+=wwpango; }                 //~va30R~
		        pgeom->x_offset=oldx;                              //~va30R~
	            w0ctr++;                                           //~va30R~
            }                                                      //~va30R~
            else                                                   //~va30R~
            if (w0ctr)  //after combining char                     //~va30R~
            {                                                      //~va30R~
                w0ctr=0;                                           //~va30R~
            }                                                      //~va30R~
        }                                                          //~va30R~
        else                                                       //~va30R~
        if (Popt & USMSO_COMBINEON)	//combine width 0 char         //~va30R~
        {                                                          //~va30R~
	        if (!oldw)	//width=0                                  //~va30R~
            {                                                      //~va30R~
		        pgeom->x_offset=oldx;	//keep original            //~va30R~
		        pgeom->width=0;	//keep original                    //~va30R~
            	if (!w0ctr)	//combine start                        //~va30R~
                {                                                  //~va30R~
                    combspan=0;                                    //~va30R~
                	swtoperr=(glyphctr==1);	//err if str top is combine chae//~va30R~
                    if (swtoperr)                                  //~va30R~
                    {                                              //~va30R~
		        		pgeom->x_offset+=wwpango;	//offset from next cell right boundary//~va30R~
		        		pgeom->width=wwpango;                      //~va30R~
                    }                                              //~va30R~
                    else                                           //~va30R~
                    	combspan=wwpango;	//span to skip to net no combining char//~va30R~
                }                                                  //~va30R~
                else	//2nd combining                            //~va30R~
                {                                                  //~va30R~
	                combspan+=wwpango;  //span to next noncombining char//~va30R~
                    if (swtoperr)                                  //~va30R~
                        pgeom->x_offset+=wwpango;                  //~va30R~
                }                                                  //~va30R~
//		        pgeom->y_offset=0;                                 //~va30R~
	            w0ctr++;                                           //~va30R~
            }                                                      //~va30R~
            else    //not combining char                           //~va30R~
            if (w0ctr)  //next of non combining                    //~va30R~
            {                                                      //~va30R~
		        pgeom->width=combspan+wwpango;	//for width0 and itself//~va30R~
		        pgeom->x_offset=pgeom->width-neww+(neww-oldw)/2;   //~va30R~
                w0ctr=0;                                           //~va30R~
            }                                                      //~va30R~
        }                                                          //~va30R~
UTRACEP("geometry new glyphctr=%d width=%d,offsx=%d,offsy=%d,combspan=%d,swtoperr=%d\n",glyphctr,pgeom->width,pgeom->x_offset,pgeom->y_offset,combspan,swtoperr);//~va30R~
		wwtotal+=pgeom->width;                                     //~va30R~
#ifdef AAA                                                         //~va1cR~
#ifdef UTF8SUPPH                                                   //~va1cR~
        if (dbcssz>UDBCSCHK_DBCSSZ) //3 or 4 byte dbcs             //~va1cR~
        {                                                          //~va1cR~
UTRACEP("dbcs3/4 ii=%d\n",ii);                                     //~va1cR~
    		pglyphinfo++;                                          //~va1cR~
        	pgeom=&(pglyphinfo->geometry);                         //~va1cR~
	    	pgeom->width=Pcellw*PANGO_SCALE;                       //~va1cR~
            ii--;                                                  //~va1cR~
	        if (dbcssz=UDBCSCHK_GB4SZ) //4 byte dbcs               //~va1cR~
            {                                                      //~va1cR~
UTRACEP("dbcs4 ii=%d\n",ii);                                       //~va1cR~
	    		pglyphinfo++;                                      //~va1cR~
    	    	pgeom=&(pglyphinfo->geometry);                     //~va1cR~
	    		pgeom->width=Pcellw*PANGO_SCALE;                   //~va1cR~
	            ii--;                                              //~va1cR~
            }                                                      //~va1cR~
        }                                                          //~va1cR~
#endif                                                             //~va1cR~
#endif	//AAA                                                      //~va1cR~
    }                                                              //~vX03M~
    tmp_list=tmp_list->next;                                       //~v69VI~
  }//runs                                                          //~v69VI~
  	if (glyphctr<Plen	//ligatured occured                        //~va30R~
    &&  glyphctr                                                   //~va30R~
    &&  Popt & USMSO_COMBINESP	//add space whe ligture occued     //~va30R~
    )                                                              //~va30R~
    {                                                              //~va30R~
  		pgeomlast->width=Plen*wwpango-wwtotal;                     //~va30R~
		UTRACEP("geometry last width after ligature width=%d,glyphctr=%d,len=%d\n",pgeomlast->width,glyphctr,Plen);//~va30R~
    }                                                              //~va30R~
  	pango_layout_iter_free (iter);                                 //~vX03M~
  	return;                                                        //~vX03M~
}//usetmonospace                                                   //~vX03I~
//===============================================================================//~va3iI~
//set monospaceing for ligature mode (utf8 file)                   //~va3iI~
//leave spacing to pango                                           //~va3iI~
//  except, adjust last for next string column position            //~va3iI~
//  (combining char under nocombine mode is split by color attr at xefile23)//~va3iI~
//===============================================================================//~va3iI~
int usetmonospace_ligature(int Popt,PangoLayout *Pplayout,         //~va3iR~
							char *Ppdataxx/*not used*/,char *Ppdbcsxx/*not used*/,//~va3iI~
							int Plen/*column width*/,int Pcellw)   //~va3iI~
{                                                                  //~va3iI~
  	GSList *tmp_list;                                              //~va3iI~
  	PangoLayoutIter *iter;                                         //~va3iI~
    PangoLayoutLine *line;                                         //~va3iI~
    PangoLayoutRun *run;                                           //~va3iI~
    PangoGlyphString *pglyphstr;                                   //~va3iI~
    PangoGlyphInfo *pglyphinfo;                                    //~va3iI~
    PangoGlyphGeometry *pgeom,*pgeomlast;                          //~va3iI~
    int ii,wwpango,glyphctr=0,wwtotal=0,wwglyph;                   //~va3iR~
    int diff,ccoffs,difftotal;                                     //~va3uR~
//******************************                                   //~va3iI~
  	iter = pango_layout_get_iter(Pplayout);                        //~va3iI~
    line=pango_layout_iter_get_line (iter);                        //~va3iI~
UTRACEP("usetmonospace_ligature opt=%x,len=%d\n",Popt,Plen);       //~va3iR~
dprintf("usetmonospace_ligature opt=%x,len=%d\n",Popt,Plen);       //~va3iI~
    if (!line)  //split dbcs is set to layout                      //~va3iI~
        return 4;                                                  //~va3iR~
    tmp_list=line->runs;                                           //~va3iI~
//dprintf("list@@@=%p\n",tmp_list);                                //~va3iI~
	wwpango=Pcellw*PANGO_SCALE;                                    //~va3iI~
  	while(tmp_list)                                                //~va3iI~
  	{                                                              //~va3iI~
    	run=tmp_list->data;                                        //~va3iI~
//dprintf("run @@@=%p\n",run);                                     //~va3iI~
    	if (!run)  //split dbcs is set to layout                   //~va3iI~
        	break;                                                 //~va3iI~
    	pglyphstr=run->glyphs;                                     //~va3iI~
    	if (!pglyphstr)  //split dbcs is set to layout             //~va3iI~
        	break;                                                 //~va3iI~
UTRACEP("temp list=%p\n",pglyphstr);                               //~va3iI~
    	for (ii=pglyphstr->num_glyphs,pglyphinfo=pglyphstr->glyphs;ii>0;ii--,pglyphinfo++)//~va3iI~
    	{                                                          //~va3iI~
        	pgeom=&(pglyphinfo->geometry);                         //~va3iI~
        	pgeomlast=pgeom;                                       //~va3iI~
UTRACEP("geometry ii=%d,offs_x=%d,offs_y=%d,width=%d cellw=%d\n",ii,pgeom->x_offset,pgeom->y_offset,pgeom->width,Pcellw);//~va3iI~
	    	wwglyph=pgeom->width;                                  //~va3iI~
//          if (!wwglyph && !glyphctr)	//start with combining char//~va3iI~//~va3uR~
            if (!wwglyph)	//combining char                       //~va3uI~
            if (!glyphctr	//start with combining char            //~va3uI~
            ||  (Popt & USMSO_COMBINEOF)	//split mode           //~va3uI~
            )                                                      //~va3uI~
            {                                                      //~va3iI~
            	ccoffs=pgeom->x_offset;			//offset from roght boundary of prev cell//~va3uM~
              if (ccoffs<0)                                        //~va3uI~
              {                                                    //~va3uI~
            		wwglyph=wwpango+ccoffs;	//offset is right end? //~va3uR~
            		pgeom->width=wwglyph;	//it may be combining char glyph size//~va3uI~
            		pgeom->x_offset=wwglyph;                       //~va3uR~
              }                                                    //~va3uI~
              else                                                 //~va3uI~
              {                                                    //~va3uI~
            	pgeom->width=wwglyph=wwpango;	//space for next char//~va3iR~
            	pgeom->x_offset+=wwglyph;		//required to see it//~va3iR~
              }                                                    //~va3uI~
            	if (Popt & USMSO_COMBINEOF)	//split mode           //~va3uI~
            		pgeom->y_offset=0;	//up when continued combining char//~va3uI~
            }                                                      //~va3iI~
	    	wwtotal+=wwglyph;                                      //~va3iR~
        	glyphctr++;                                            //~va3iM~
UTRACEP("geometry gliphctr=%d, new width==%d offs==%d totalwidth=%d\n",glyphctr,pgeom->width,pgeom->x_offset,wwtotal);//~va3iR~//~va3uR~
        }                                                          //~va3iI~
    	tmp_list=tmp_list->next;                                   //~va3iI~
  	}//runs                                                        //~va3iI~
    if (glyphctr)                                                  //~va3iI~
    {                                                              //~va3iI~
//		pgeomlast->width=Plen*wwpango-wwtotal;                     //~va3iI~//~va3uR~
  		diff=Plen*wwpango-wwtotal;                                 //~va3uI~
UTRACEP("geometry last before adjusted new width==%d offs==%d totalwidth=%d,diff=%d\n",pgeomlast->width,pgeom->x_offset,wwtotal,diff);//~va3uI~
        if (diff>0)                                                //~va3uI~
			pgeomlast->width=diff;                                 //~va3uI~
        else // overflow, do monospacing                           //~va3uR~
        {                                                          //~va3uI~
        	wwtotal=0;                                             //~va3uI~
    		tmp_list=line->runs;                                   //~va3uI~
            difftotal=-diff;                                       //~va3uR~
            while(tmp_list)                                        //~va3uI~
            {                                                      //~va3uI~
                run=tmp_list->data;                                //~va3uI~
                if (!run)  //split dbcs is set to layout           //~va3uI~
                    break;                                         //~va3uI~
                pglyphstr=run->glyphs;                             //~va3uI~
                if (!pglyphstr)  //split dbcs is set to layout     //~va3uI~
                    break;                                         //~va3uI~
                for (ii=pglyphstr->num_glyphs,pglyphinfo=pglyphstr->glyphs;ii>0;ii--,pglyphinfo++)//~va3uI~
                {                                                  //~va3uI~
                    pgeom=&(pglyphinfo->geometry);                 //~va3uI~
                    wwglyph=pgeom->width;                          //~va3uI~
                    if (wwglyph>wwpango)                           //~va3uI~
                    {                                              //~va3uI~
                        diff=wwglyph-wwpango;                      //~va3uI~
                        difftotal-=diff;                           //~va3uI~
UTRACEP("geometry ii=%d,adjust old offs_x=%d,width=%d\n",ii,pgeom->x_offset,pgeom->width);//~va3uI~
                        pgeom->width=wwpango;                      //~va3uI~
                        if (pgeom->x_offset>wwpango)	//combining ch//~va3uI~
                            pgeom->x_offset=wwpango;               //~va3uI~
UTRACEP("geometry ii=%d,adjust offs_x=%d,width=%d\n",ii,pgeom->x_offset,pgeom->width);//~va3uI~
                    }                                              //~va3uI~
                    wwtotal+=wwglyph;                              //~va3uI~
                    if (difftotal<=0)                              //~va3uI~
                        break;                                     //~va3uI~
                }                                                  //~va3uI~
                if (difftotal<=0)                                  //~va3uI~
                    break;                                         //~va3uI~
                tmp_list=tmp_list->next;                           //~va3uI~
            }//runs                                                //~va3uI~
			pgeomlast->width+=-difftotal;                          //~va3uR~
UTRACEP("geometry last adjusted new width==%d offs==%d totalwidth=%d,diff=%d\n",pgeomlast->width,pgeom->x_offset,wwtotal,diff);//~va3uI~
        }                                                          //~va3uI~
    }                                                              //~va3iI~
  	pango_layout_iter_free (iter);                                 //~va3iI~
  	return 0;                                                      //~va3iI~
}//usetmonospace_ligature                                          //~va3iI~
//===============================================================================//~va6yI~
//get default printer  Mprinter_cupsdefault                        //~va6yI~
//rc:1: cups default ult defined                                   //~va6yI~
//===============================================================================//~va6yI~
int csubgetdefaultprinter(int Popt)                                //~va6yI~
{                                                                  //~2B03aI~//~va6yI~
    int pn,pn0,rc=0;                                               //~va6yI~
    cups_dest_t  *pl,*pl0;                                         //~va6yI~
//********************************                                 //~va6yI~
    *Mprinter_cupsdefault=0;                                       //~va6yI~
    pn0=pn=cupsGetDests(&pl0);                                     //~va6yI~
    for (pl=pl0;pn>0;pn--,pl++)                                    //~va6yI~
    {                                                              //~va6yI~
    	dprintf("cups printer list=%s,isdefault=%d\n",pl->name,pl->is_default);//~va6yI~
        if (pl->is_default)                                        //~va6yI~
        {                                                          //~va6yI~
        	UstrncpyZ(Mprinter_cupsdefault,pl->name,sizeof(Mprinter_cupsdefault));//~va6yI~
            rc=1;                                                  //~va6yI~
        }                                                          //~va6yI~
    }                                                              //~va6yI~
    free(pl0);  //free printer list                                //~va6yI~
    return rc;                                                     //~va6yI~
}//csubgetdefaultprinter                                           //~va6yR~
//===============================================================================//~va6yI~
//get default printer geometry                                     //~va6yR~
//rc:4: err                                                        //~va6yI~
//===============================================================================//~va6yI~
int csubgetcupspagesz(int Popt,RECT *Pprect,char *Pform)           //~va6yI~
{                                                                  //~2B03aI~//~va6yI~
    int rc=0;                                                      //~va6yI~
    char *pdest,*pppdnm,*pform;                                    //~va6yR~
    ppd_file_t *ppd;                                               //~va6yI~
    ppd_size_t *ppgsz;                                             //~va6yI~
    gdouble fmr,fml,fmt,fmb,fmw,fmh;                               //~va6yR~
//************************************                             //~va6yI~
    dprintf("cups margin inrect dest=%s,default=%s\n",Mprinter,Mprinter_cupsdefault);//~va6yI~
    if (strcmp(Mprinter,DEF_PRINTER) || !*Mprinter_cupsdefault)    //~va6yI~
        pdest=Mprinter;                                            //~va6yI~
    else                                                           //~va6yI~
        pdest=Mprinter_cupsdefault;                                //~va6yI~
    pppdnm=(char *)cupsGetPPD((const char *)pdest);                //~va6yR~
    dprintf("cups margin inrect dest=%s,default=%s,ppd=%s\n",Mprinter,Mprinter_cupsdefault,pppdnm);//~va6yI~
    if (!pppdnm)                                                   //~va6yI~
    	return 4;                                                  //~va6yI~
    ppd=ppdOpenFile(pppdnm);                                       //~va6yR~
    if (!ppd)                                                      //~va6yI~
    	return 4;                                                  //~va6yI~
    pform=Pform;                                                   //~va6yR~
    if (!(pform))                                                  //~va6yR~
   		pform=Mformtype;                                           //~va6yR~
    ppgsz=ppdPageSize(ppd,pform);   //page size by point 72point/inch//~va6yR~
    if (!ppgsz)                                                    //~va6yI~
	    ppgsz=ppdPageSize(ppd,NULL);   //currentry seslected       //~va6yI~
    if (!ppgsz)                                                    //~va6yI~
    	rc=4;                                                      //~va6yR~
    else                                                           //~va6yI~
    {                                                              //~va6yI~
      if (Popt & CGCPOHW)	//get width and height                 //~va6yI~
      {                                                            //~va6yI~
        if (ppgsz->bottom>ppgsz->top)                              //~va6yI~
        	fmt=ppgsz->top;                                        //~va6yR~
        else                                                       //~va6yI~
        	fmt=ppgsz->bottom;                                     //~va6yI~
        fml=ppgsz->left;                                           //~va6yI~
        fmr=ppgsz->width;                                          //~va6yI~
        fmb=ppgsz->length;                                         //~va6yI~
      }                                                            //~va6yI~
      else                                                         //~va6yI~
      {                                                            //~va6yI~
        fml=ppgsz->left;                                           //~va6yR~
        fmr=ppgsz->right;                                          //~va6yR~
        fmt=ppgsz->top;                                            //~va6yR~
        fmb=ppgsz->bottom;                                         //~va6yR~
        fmw=ppgsz->width;                                          //~va6yR~
        fmh=ppgsz->length;                                         //~va6yI~
        fmr=fmw-fml-fmr;     //width-leftmargin-rightPos           //~va6yI~
        if (fmr<0)                                                 //~va6yI~
        	fmr=0;                                                 //~va6yI~
        if (fmt>fmb)                                               //~va6yI~
        {                                                          //~va6yI~
        	fmb=fmh-fmb-fmt; //height-topmargin-bottomPos          //~va6yI~
            if (fmb<0)                                             //~va6yI~
            	fmb=0;                                             //~va6yI~
            fmt=ppgsz->bottom;                                     //~va6yR~
        }                                                          //~va6yI~
        else                                                       //~va6yI~
        {                                                          //~va6yI~
        	fmb=fmh-fmt-fmb; //height-topmargin-bottomPos          //~va6yI~
            if (fmb<0)                                             //~va6yI~
            	fmb=0;                                             //~va6yI~
        }                                                          //~va6yI~
      }                                                            //~va6yI~
        fml=POINT2MM(fml);                                         //~va6yR~
        fmr=POINT2MM(fmr);                                         //~va6yR~
        fmt=POINT2MM(fmt);                                         //~va6yR~
        fmb=POINT2MM(fmb);                                         //~va6yR~
        Pprect->top =(int)(fmt+0.9999);                            //~va6yR~
        Pprect->left=(int)(fml+0.9999);                            //~va6yR~
        Pprect->bottom=(int)(fmb+0.9999);                          //~va6yR~
        Pprect->right =(int)(fmr+0.9999);                          //~va6yR~
        dprintf("subgetcupspagesz form=%s,mark=%d,top=%d,left=%d,bot=%d,right=%d\n",//~va6yR~
            ppgsz->name,ppgsz->marked,Pprect->top,Pprect->left,Pprect->bottom,Pprect->right);//~va6yR~
    }                                                              //~va6yI~
    ppdClose(ppd);                                                 //~va6yM~
    return rc;                                                     //~va6yR~
}//csubgetcupspagesz                                               //~va6yI~
//===============================================================================//~va6yI~
//get default printer geometry                                     //~va6yI~
//rc:4: err                                                        //~va6yI~
//===============================================================================//~va6yI~
int csubgetcupsform(int Popt,char *Pprinter,char **Pppform,int *Ppformno,int *Ppentsz)//~va6yI~
{                                                                  //~2B03aI~//~va6yI~
    int ii,entsz,formno;                                           //~va6yI~
    char *pdest,*pform,*pform0,*pppdnm;                            //~va6yR~
    ppd_file_t *ppd;                                               //~va6yI~
    ppd_size_t *ppgsz;                                             //~va6yI~
//************************************                             //~va6yI~
    dprintf("cups getform dest=%s,default=%s\n",Mprinter,Mprinter_cupsdefault);//~va6yI~
    if (Pprinter)                                                  //~va6yI~
        pdest=Pprinter;                                            //~va6yI~
    else                                                           //~va6yI~
    if (strcmp(Mprinter,DEF_PRINTER) || !*Mprinter_cupsdefault)    //~va6yI~
        pdest=Mprinter;                                            //~va6yI~
    else                                                           //~va6yI~
        pdest=Mprinter_cupsdefault;                                //~va6yI~
    pppdnm=(char *)cupsGetPPD((const char *)pdest);                //~va6yI~
    dprintf("cups margin inrect dest=%s,default=%s,ppd=%s\n",Mprinter,Mprinter_cupsdefault,pppdnm);//~va6yI~
    if (!pppdnm)                                                   //~va6yI~
    	return 4;                                                  //~va6yI~
    ppd=ppdOpenFile(pppdnm);                                       //~va6yI~
    if (!ppd)                                                      //~va6yI~
    	return 4;                                                  //~va6yI~
    formno=ppd->num_sizes;                                         //~va6yI~
    ppgsz=ppd->sizes;                                              //~va6yR~
    if (!formno)                                                   //~va6yI~
    	return 4;                                                  //~va6yI~
    entsz=sizeof(ppgsz->name);                                     //~va6yR~
    pform=pform0=umalloc(formno*entsz);                            //~va6yR~
    for (ii=formno;ii>0;ii--,ppgsz++,pform+=entsz)                 //~va6yI~
    	memcpy(pform,ppgsz->name,entsz);	                       //~va6yI~
    ppdClose(ppd);                                                 //~va6yI~
    *Ppformno=formno;                                              //~va6yI~
    *Ppentsz=entsz;                                                //~va6yR~
    *Pppform=pform0;                                               //~va6yI~
    return 0;                                                      //~va6yI~
}//csubgetcupsform                                                 //~va6yI~
//===============================================================================//~vX03I~
//get default config                                               //~vX03I~
//parm1:prev pointer to be freed                                   //~vX03I~
//parm2:printer name to be set to config                           //~vX03I~
//ret  :new config                                                 //~vX03I~
//===============================================================================//~vX03I~
void *uprtgetconfig(void *Ppprtconf,char *Ppprinter)               //~vX03R~
{                                                                  //~vX03I~
    GnomePrintConfig *pprtconf;                                    //~vX03R~
    char *poldprinter;                                             //~vX03I~
//************************************                             //~vX03I~
	if (Ppprtconf)	//free previous req                            //~vX03R~
    	gnome_print_config_unref((GnomePrintConfig*)(Ppprtconf));  //~vX03R~
    pprtconf=gnome_print_config_default();                         
    poldprinter=gnome_print_config_get(pprtconf,"Printer");        //~vX03I~
//  dprintf("config printer before=%s,req=%s\n",poldprinter,Ppprinter);//~v69VR~
    if (Ppprinter)	//printer specified                            //~vX03I~
    {                                                              //~vX03I~
    	if (!strcmp(Ppprinter,DEF_PRINTER)	//default specified    //~va4aI~
        && *Mprinter_cupsdefault)			//cups defult was set  //~va4aI~
        {                                                          //~va4aI~
        	gnome_print_config_set(pprtconf,"Printer",Mprinter_cupsdefault);//~va4aI~
  		    dprintf("config printer %s->%s\n",Mprinter_cupsdefault,gnome_print_config_get(pprtconf,"Printer"));//~va4aI~
        }                                                          //~va4aI~
        else                                                       //~va4aI~
    	if (strcmp(Ppprinter,DEF_PRINTER)	//not default specified//~vX03R~
//  	&&  strcmp(Ppprinter,poldprinter))	//not default          //~va6yR~
    	&&  (!poldprinter ||strcmp(Ppprinter,poldprinter)))	//not default//~va6yI~
        {                                                          //~vX03I~
        	gnome_print_config_set(pprtconf,"Printer",Ppprinter);  //~vX03I~
    		 dprintf("config printer after=%s,Mprinter=%s\n",gnome_print_config_get(pprtconf,"Printer"),Ppprinter);//~va6yR~
        }                                                          //~vX03I~
    }                                                              //~vX03I~
    if (poldprinter)                                               //~va6yI~
    	g_free(poldprinter);                                       //~va6yI~
	return (void*)pprtconf;                                        //~vX03R~
}//uprtgetconfig                                                   //~vX03I~
#endif //ARM                                                       //~v@@@M~
//===============================================================================//~v76cI~
//get keyboard rate from gconf db                                  //~v76cI~
//parm1:output typematic delay                                     //~v76cI~
//parm2:output typematic interval(ms)                              //~v76cR~
//ret  :err                                                        //~v76cI~
//===============================================================================//~v76cI~
int ugconfgetkbdrate(int *Ppdelay,int *Ppinterval)                 //~v76cR~
{                                                                  //~v76cI~
#ifndef ARM                                                        //~v@@@I~
#ifdef GCONFKBD                                                    //~v76cI~
	GConfClient *client;                                           //~v76cI~
#endif                                                             //~v76cI~
    int rate,delay;                                                //~v76cI~
//************************************                             //~v76cI~
#ifdef GCONFKBD	//KDE may not have gconf db                        //~v76cR~
	client = gconf_client_get_default ();                          //~v76cI~
    dprintf("client=%p\n",client);                                 //~v76cI~
    if (!client)                                                   //~v76cI~
    	return 4;                                                  //~v76cI~
    delay=gconf_client_get_int(client,"/desktop/gnome/peripherals/keyboard/delay",NULL);//~v76cI~
    rate =gconf_client_get_int(client,"/desktop/gnome/peripherals/keyboard/rate",NULL);//~v76cI~
    dprintf("delay=%d,rate=%d\n",delay,rate);                      //~v76cR~
    *Ppdelay=delay;                                                //~v76cI~
    if (rate)                                                      //~v76cI~
    	*Ppinterval=1000/rate;                                     //~v76cR~
    else                                                           //~v76cI~
    	*Ppinterval=0;                                             //~v76cR~
	g_object_unref (G_OBJECT (client));                            //~v76cI~
#else                                                              //~v76cI~
    XkbGetAutoRepeatRate(GDK_DISPLAY (),XkbUseCoreKbd,(UINT*)(&delay),(UINT*)(&rate));//~v76cI~//~va72R~
    dprintf("delay=%d,rate=%d\n",delay,rate);                      //~v76cI~
    *Ppdelay=delay;                                                //~v76cI~
    *Ppinterval=rate;                                              //~v76cR~
#endif                                                             //~v76cI~
    return 0;                                                      //~v76cI~
#else                                                              //~v@@@I~
    return 4;   //err                                              //~v@@@I~
#endif //ARM                                                       //~v@@@I~
}//ugconfgetkbdrate                                                //~v76cI~
