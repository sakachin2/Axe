//*CID://+vb91R~:                             update#=  215;       //+vb91R~
//*********************************************************************//~2818I~
//* wxecsub.h                                                      //~2818I~
//*********************************************************************//~2818I~
//vb91:170228 marge rh9j gxe to arm-gxe                            //+vb91I~
//				vb4A:160815 if UNICOMB UNPR mode and LIGATURE ON, write altch just after base char using GetTextExtentPoint32W//+vb91I~
//vax5:140627 (130928:vam4 for jni)file scrprint show unprintable by box char,use altch like scr draw//~vax5I~
//va6y:000706 CUPs default printer is not same as gnome default    //~va6yI~
//va6p:000623 (LNX:BUG) ligature ignored for locale file           //~va6pI~
//va3b:100205 (GXE)no ligature for hex edit(HEX mode and EH cmd)   //~va30I~
//va30:100126 combine mode of diacritical marks by A+/(saved to ini)//~va30I~
//va20:091125 (UTF8)UTF8 full support(display utf8 also unprintable as locale code)//~va20I~
//va1D:091115 gxeprint:2column print for 4byte dbcs if wysiwig is off//~va1DI~
//va1c:091030_merge GB18030 support                                //~va1cI~
//v79Q:081027 (XXE:BUG)0x0a or 0x0d is cause of line is not drawn;replace it by unprintable id//~v79QI~
//v79z:080916 DBCS tbl for C and K of CJK                          //~v79zI~
//v780:080212 syntaxhighlight support (SYN cmd,C+s func key)       //~v780I~
//v76c:070617 (XXE)paste from PRIMARY clipboard by middle button down//~v76cI~
//v75X:070526 (XXE:BUG)assertion fail face!=NULL, gnome_font_find  //~v75XI~
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
//v69s:060531 (XXE:fc5)g_locale_to_utf8 return error if null char contained//~v69sI~
//v69r:060531 (XXE)print debug message by GError                   //~v69rI~
//v68j:060404 (XXE)gtk dmsgdialog okcan button default is cancel,force ok as default like as Windows//~v68jI~
//v68i:060331 (WXEXXE)activate window when dropped                 //~v68iI~
//v686:060323 dnd support by paste(edit:pastev,ins=paste-ins,rep=paste-rep)//~v686I~
//v685:060322 dnd support "file copy into the dir slected" and even in inter-split-screen.//~v685I~
//v66A:051220 dialog to confirm wxe/gxe termination when func-key used//~v66AI~
//v667:050823 (XXE)add printer selection dropdown list(effective only for CUPS)//~v667I~
//vXXE:050611 new for XXE                                          //~vXXEI~
//v560:040324 (WIN)break sleep by esc*2                            //~v560I~
//v55Z:040324 (WXE)ctl multicmd by wxe to display intermediate screen//~v55ZI~
//v55W:040322 (WXE)AT cmd support;"AT(&) interval,count;cmd"       //~v55DI~
//v55D:040307 (WXE)kick wxe.hlp by help cmd                        //~v55DI~
//v55u:040229 (WXE)add open with for dir-list                      //~v55uI~
//v55c:040208 (WXE)distingush L-CTL/R-CTL                          //~v55cI~
//v54y:040118 (WXE)wxp cmd support(send file to wxe for print by wxe)//~v54yI~
//v53t:031004 (WXE:BUG)not freed area exist                        //~v53tI~
//*********************************************************************//~v53tI~
#define INI_FILENAME   "xxeg.ini"                                  //~vXXER~
#define INI_ACRNM   INI_FILENAME "----!!\n"                        //~2907R~
                                                                   //~vXXEI~
#define PRINTGERR(comment,pgerr) {if (pgerr!=NULL) csubprintgerror((char*)__FUNCTION__,__LINE__,comment,pgerr);}//~v705R~
                                                                   //~v69rI~
#define TEXTFILEID     "file://"                                   //~vXXEM~
#define TEXTFILEIDLEN  7                                           //~vXXEM~
#define COPYFILEID     "copy\n"                                    //~vXXER~
#define COPYFILEIDLEN  5                                           //~vXXER~
#define CUTFILEID     "cut\n"                                      //~v686I~
#define CUTFILEIDLEN   4                                           //~v686I~
                                                                   //~2907I~
typedef int (TIMERCALLBACK)(void *Pdata);                          //~vXXEI~
                                                                   //~vXXEI~
#ifdef __cplusplus                                                 //~2818I~
extern  "C"                                                        //~2818I~
{                                                                  //~2818I~
#endif                                                             //~2818I~
                                                                   //~2818I~
	int ugetinidata(PWXEINIDATA Ppwxeini);                         //~vXXER~
	int uputinidata(PWXEINIDATA Ppwxeini);                         //~vXXER~
	int csubinit1(HWND Phwnd);                                     //~vXXEM~
	int uactivate_menuitem(char *Pobjectname);                     //~vXXER~
	int uclick_toolbarbutton(char *Pobjectname);                   //~vXXEI~
	int uerrmsgbox(char *,char *,... );                            //~vXXEM~
	int uerrmsgboxokcan(char * Pemsg,char * Pjmsg,... );           //~v66AI~
//	int umsgbox(char *Ppmsg);                                      //~v685R~
  	int umsgbox(char *Ppmsg,int Pflag);                            //~v685I~
#define MB_OK          GTK_BUTTONS_OK                              //~v685R~
#define MB_YESNO       GTK_BUTTONS_YES_NO                          //~v686I~
#define MB_OKCANCEL    GTK_BUTTONS_OK_CANCEL                       //~v68jI~
#define MB_YESNOCANCEL 101		//not supported                    //~v686R~
                                                                   //~v685I~
#define IDOK     GTK_RESPONSE_OK                                   //~v685I~
#define IDYES    GTK_RESPONSE_YES                                  //~v685I~
#define IDCLOSE  GTK_RESPONSE_CLOSE                                //~v685I~
#define IDCANCEL GTK_RESPONSE_CANCEL                               //~v685I~
                                                                   //~v685I~
	int umsgbox2(char *Ppmsg,int Pmessagetype,int Pbuttontype);    //~v66AR~
	void usettitle(char *Ptitle);                                  //~vXXEI~
	int  ugetcpath(char *Ppath);                                   //~vXXEM~
	int usetcaret(int Pheightrate,int Pcellw,int Pcellh,int Pfonth);//~vXXER~
	int ushowcaret(void);                                          //~vXXEM~
	int uhidecaret(void);                                          //~vXXEM~
	int ugetcaretpos(int *Pprow,int *Ppcol);	//client axis      //~vXXEM~
	int usetcaretpos(int Prow,int Pcol);                           //~vXXEM~
	void usetpalettecolor(int Pmaxpal,int *Pprgb);                 //~vXXEI~
	void usetsynpalettecolor();                                    //~v780I~
	int uinvalidate(int Popt);                                     //~vXXER~
#define UINVO_SYNC  0x01                                           //~vXXEI~
	UINT usettimer(UINT Ptimerid,int Pintvlms,void *Pcallback);    //~vXXER~
    int ukilltimer(UINT Ptimerid);                                 //~vXXEM~
#ifdef UTF8UCS2                                                    //~va20M~
	int uclipboard_settext(int Popt,char *Putf8,int Plen);         //~va20I~
    #define  UCSTO_SRCUTF8        0x01   //data is utf8 encoded    //~va20I~
#else                                                              //~va20I~
	int uclipboard_settext(char *Ptext,int Plen);                  //~vXXEM~
#endif                                                             //~va20I~
//  int uclipboard_gettext(char **Pptext,int *Pplen);              //~v76cR~
    int uclipboard_gettext(int Popt,char **Pptext,int *Pplen,GtkClipboardTextReceivedFunc Ppcbfunc);//~v76cM~
#define UCBGT_PRIMARY 0x01   //GDK_SELECTION_PRIMARY               //~v76cI~
#define UCBGT_SETCB   0x02   //async process,set callback func     //~v76cI~
#define UCBGT_CBDATA  0x04   //process of callback data            //~v76cI~
	int uclipboard_setcopyfile(char *Pptext,int Plen);             //~vXXER~
	void uclipboard_memfree(char *Pmem);                           //~vXXEM~
	void *umemalloc(int Plen);                                     //~vXXEM~
	void umemfree(void *Paddr);                                    //~vXXEM~
	void *wxemalloc(int Plen);                                     //~vXXEM~
	void wxefree(void *Paddr);                                     //~vXXEM~
	int uuridecode(char *Puri,char *Pdecoded,int Pobuffsz);        //~vXXEI~
	char *usetupuri(int Popt,char *Pstrlist);                      //~vXXER~
#define USUO_COPYFILE 0x01		//cut&paste copy file format       //~vXXER~
	void udragbegin(int Ptimeouttseconds);                         //~vXXER~
	void udragend(void);                                           //~vXXEI~
	void udragdestset(void);                                       //~vXXEI~
	void upopupmenu_menuitem_enable(int Pmenuitemid,int Penable);  //~vXXER~
	void upopupmenu_popup(CPoint Ppoint);                          //~vXXER~
	void ugetfontmetrics(PangoFontDescription *Pfontdesc,int *Ppwidth,int *Ppheight,int *Ppmonospace);//~vXXER~
//  GnomeFont *uprtcreatefont(char *Pfontname,int *Ppfontw,int *Ppfonth);//~v75XR~
    GnomeFont *uprtcreatefont(char *Pfontname,int *Ppfontw,int *Ppfonth,char *Poutfontname);//~v75XI~
	void ugetprtfontmetrics(GnomeFont *Ppgfont,int *Ppwidth,int *Ppheight);//~vXXEI~
	void uprttextout(int Pposx,int Pposy,char *Pdata,int Plen);    //~vXXEM~
#ifdef UTF8UCS2                                                    //~va20I~
	int uprttextoutw(int Popt,int Pxx,int Pyy,char *Pdata,char *Pdbcs,int Plen);//~va20I~
#endif                                                             //~va20I~
	void uprtlineout(int Pposx0,int Pposy0,int Pposx1,int Pposy1); //~vXXEI~
	void uprtscrtextout(char *Pputf8str,int Pposx,int Pposy,int Plen);//~vXXEI~
	void upreviewsetfontscale(int Phcopysw,int Pcellw,int Pcellh,gdouble Pfscalex,gdouble Pfscaley);//~vXXER~
//	void usetmonospace(PangoLayout *Pplayout,char *Ppdbcs,int Plen,int Pcellw);//~v79zR~
//	void usetmonospace(PangoLayout *Pplayout,char *Ppdata,char *Ppdbcs,int Plen,int Pcellw);//~va1DR~
  	void usetmonospace(int Popt,PangoLayout *Pplayout,char *Ppdata,char *Ppdbcs,int Plen,int Pcellw);//~va1DI~
    #define USMSO_WWSCRPRT  0x01   //space print by dbcs padding   //~va1DI~
#ifdef UTF8UCS2                                                    //~va20I~
    #define USMSO_DDFMT     0x02   //scr data is dd fmt            //~va20I~
    #define USMSO_COMBINEON 0x04   //scr combine mode              //~va30R~
    #define USMSO_COMBINEOF 0x08   //scr no combine mode           //~va30R~
    #define USMSO_COMBINESP 0x10   //widen width for the string if ligature occured//~va30R~
#endif                                                             //~va20I~
	int usetmonospace_ligature(int Popt,PangoLayout *Pplayout,char *Ppdata,char *Ppdbcs,//~va3bI~
//							int Plen/*column width*/,int Pcellw);  //~vb4AR~//+vb91I~
  							int Plen/*column width*/,int Pcellw,int *Pstrwidth);//~vb4AR~//+vb91I~
	void *uprtgetconfig(void *Ppprtconf,char *Ppprinter);          //~v667I~
	int csubgetdefaultprinter(int Popt);                           //~va6yI~
	int csubgetcupspagesz(int Popt,RECT *Pprect,char *Pform);      //~va6yI~
	int csubgetcupsform(int Popt,char *Ppdest,char **Ppform,int *Ppformctr,int *Ppentsz);//~va6yI~
    #define CGCPOHW     0x01	//get width and height             //~va6yR~
	GtkSelectionData *uclipboard_getfileselection(void);           //~v686R~
	int uclipboard_getcopyfile(char **Pfilenames,int *Pfilectr,int *Pplen);//~v685R~
	int uclipboard_getcopyfilesub(int Popt,GtkSelectionData *Pseldata,char **Pfilenames,int *Pfilectr,int *Pplen);//~v685R~
    #define UCLIPBOARD_FREESELECTION   0x01   //free selection data//~v686I~
	void usetfocus(void);                                          //~v68iI~
	int upostmsg(UINT Pmsg,ULONG Pwparm, ULONG Plparm);            //~v685M~
	void csubprintgerror(char *Pfile,int Pline,char *Pcmt,GError *Ppgerr);//~v69rR~
//  char *csublocale2utf8(char *Ptext,int Plen,int *Ppreadlen,int *Ppwritelen);//~v69VR~
//  int csublocale2utf8(char *Ptext,int Plen,int *Ppreadlen,char **Putf8,int *Ppwritelen);//~v79zR~
    int csublocale2utf8(int Popt,char *Ptext,int Plen,int *Ppreadlen,char **Putf8,int *Ppwritelen);//~v79zI~
#define CSL2UO_ENCENV    0x01    //source encoding is of environment//~v79zI~
#define CSL2UO_REP0a0d   0x02    //replace 0x0a and 0x0d by unprintable//~v79QI~
#define CSL2UO_LIGATURE  0x04    //ligature mode                   //~va6pI~
#define CSL2UO_NULLALT   0x08    //rep null by '.' for gtkprint    //~vax5I~
	int csublocale2utf8scr(int Popt,char *Ptext,char *Pdbcs,int Plen,//~va1cR~
						int *Ppreadlen,char **Pputf8,              //~va1cR~
						char **Ppdata,char **Ppdbcs,USHORT **Ppoffst,//~va1cR~
						int *Ppwritelen,int *Pplclen,int *Ppslno); //~va1cR~
	int csublinechinit(UCHAR **Plinechmap);                        //~v69XI~
	void usetresizehint(int Pwidth,int Pheight,int Pcol,int Prow); //~v69VI~
	void csubacssetup(int Psetsw,int Pentno,UCHAR *Pacstbl,int *Pplentb,char **Pdispstr);//~v705I~
	int ugconfgetkbdrate(int *Ppdelay,int *Pprate);                //~v76cR~
#ifdef UTF8UCS2                                                    //~va20I~
//int csubtextoutw(int Popt,HDC Phdc,int Px,int Py,char *Pddata,char *Pdbcs,int Plen,int Pcellw);//~va20R~
#define CSTOWO_PRINT        0x01 //write a line at once            //~va20I~
//int csubtextoutw1(int Popt,HDC Phdc,int Pxx,int Pyy,char *Pdata,char *Pdbcs,int Plen);//~va20R~
int csubchkdd(int Popt,char *Pdbcs,int Plen);                      //~va20I~
int csubddcombine(int Popt,char *Pdata,char *Pdbcs,char *Pcombineid,int Plen,int *Ppstarterrctr,int *Ppendcombinectr);//~va30R~
void utracelayout(PangoLayout *Pplayout);                          //~va30R~
#ifdef NOTRACE                                                     //~va30I~
	#define UTRACELAYOUT(playout)                                  //~va30I~
#else                                                              //~va30I~
	#define UTRACELAYOUT(playout) utracelayout(playout)            //~va30I~
#endif                                                             //~va30I~
int csublyoutinfo(int Popt,PangoLayout *Pplayout,int *Pretinfo);   //~va30R~
#define CSUBLI_CTR   0                                             //~va30R~
#define CSUBLI_MAX   1      //output fld counter                   //~va30R~
int csubgetcsrposbf(int Popt,char *Pdbcs,int Plen,int Ppos,int *Ppposb,int *Pposc,int *Ppposa);//~va3bR~
#endif                                                             //~va20I~
#ifdef __cplusplus                                                 //~2818I~
}                                                                  //~2818I~
#endif                                                             //~2818I~
