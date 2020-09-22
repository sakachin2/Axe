//*CID://+vai7R~:                             update#=  211;       //+vai7R~
//***************************************************************************//~v59zI~
//vai7:130526 (Axe)change cursor color(when cmderr,not visible on cmdline)//+vai7I~
//v@@@:110610 Android(-DARM)                                       //~v@@@I~
//v780:080212 syntaxhighlight support (SYN cmd,C+s func key)       //~v780I~
//v76c:070617 (XXE)paste from PRIMARY clipboard by middle button down//~v76cI~
//v73t:070125 cross-hair ruler support by Shift+Ctl+lbuton         //~v73tI~
//v71A:061030 Use HtmlHelp;Winhelp is deprecated at Vista;XP english ersion cannot read .hlp by the readson of codepage unsupported//~v71AI~
//v66a:050827 (XXE)printf used,so change to dprintf which change to nop by DUMMYPRINTF//~v66aI~
//v669:050826 (XXE)help support                                    //~v66aI~
//vX03:050729 force monospacefor the case sbcs is monospace but dbcs is not//~vX03I~
//***************************************************************************//~v59zI~
// gxe.h                                                           //~v59zI~
//***************************************************************************//~v59zI~
#ifdef DUMMYPRINTF                                                 //~vX03R~
  #ifdef ARM                                                       //~v@@@I~
	#define dprintf(...)                                           //~v@@@I~
  #else                                                            //~v@@@I~
	#define dprintf dummyprintf                                    //~v66aR~
  #endif                                                           //~v@@@I~
#else                                                              //~v66aI~
#ifdef ARM                                                         //~v@@@I~
	#include <android/log.h>                                       //~v@@@I~
//  #define dprintf(...)  __android_log_print(ANDROID_LOG_DEBUG,"dprintf",__VA_ARGS__)//~v@@@R~
    #define dprintf(...)  UTRACEP(__VA_ARGS__)                     //~v@@@I~
#else                                                              //~v@@@I~
	#define dprintf printf                                         //~v66aI~
#endif //ARM                                                       //~v@@@I~
#endif                                                             //~vX03M~
#define XXEPGMID     "gxe"                                         //~v59zR~
#ifdef GBL_GXE                                                     //~v59zI~
	#define GXE_EXTERN                                             //~v59zI~
    #define INITVAL(val) =val                                      //~v59zR~
#else                                                              //~v59zI~
	#define GXE_EXTERN extern                                      //~v59zI~
    #define INITVAL(val)                                           //~v59zI~
#endif                                                             //~v59zI~
//************************************************************     //~v59zI~
typedef struct _FONTDATA {                                         //~v59zM~
					gchar                 *FDfontname;             //~v59zM~
					PangoFontDescription  *FDfontdesc;             //~v59zM~
                  } FONTDATA , *PFONTDATA;                         //~v59zM~
#define FONTDATASZ sizeof(FONTDATA)                                //~v59zM~
#define XXE_MAXFONTFACE 4                                          //~v780I~
		PangoFontDescription  *Gsynfontdesc[XXE_MAXFONTFACE];      //~v780I~
typedef struct _COLORDATA {                                        //~v59zM~
					int                   CDstatus;                //~v59zR~
#define CDST_RGB       1  //RGB is valid                           //~v59zI~
#define CDST_PIXEL     2  //collor alloced                         //~v59zI~
					GdkColor              CDcolor;                 //~v59zM~
                  } COLORDATA , *PCOLORDATA;                       //~v59zM~
#define COLORDATASZ sizeof(COLORDATA)                              //~v59zM~
#define MAX_PALETTE 256                                            //~v59zI~
//************************************************************     //~v59zI~
GXE_EXTERN  GtkWidget *Gpframe;                                    //~v59zR~
GXE_EXTERN  GtkWidget *Gpview;                                     //~v59zR~
GXE_EXTERN  GdkPixmap *Gppixmap,*Gppixmapprtscr,*Gppixmappreview;  //~v59zR~
GXE_EXTERN  GdkGC     *Gpgc,*Gpprtgc,*Gpgcpreview;                 //~v59zR~
GXE_EXTERN  GdkGC     *Gpgccsr;     //caret display                //~v59zR~
GXE_EXTERN  GdkGC     *Gpgcim;      //im preedit                   //~v59zI~
GXE_EXTERN  GdkGC     *Gpgcruler;   //ruler                        //~v73tI~
GXE_EXTERN  GdkColormap *Gpcolormap;                               //~v59zI~
GXE_EXTERN  GdkColor   Gxxepalette[MAX_PALETTE];                   //~v59zR~
#define COLOR_GRAY    {0,0x8888,0x8888,0x8888}                     //~v59zR~
#define COLOR_LBLUE   {0,0x8888,0xffff,0xffff}                     //+vai7I~
#define COLOR_WHITE   {0,0xffff,0xffff,0xffff}                     //~v59zI~
#define COLOR_BLACK   {0,0x0000,0x0000,0x0000}                     //~v59zR~
GXE_EXTERN  GdkColor   Gxxesynpalette[16];                         //~v780R~
GXE_EXTERN  GdkColor   Gprintbg  INITVAL(COLOR_WHITE);             //~v59zI~
GXE_EXTERN  GdkColor   Gprintfg  INITVAL(COLOR_BLACK);             //~v59zI~
GXE_EXTERN  GdkColor   Gpreviewbg  INITVAL(COLOR_GRAY);            //~v59zI~
//GXE_EXTERN  GdkColor   Gcsrcolor INITVAL(COLOR_GRAY);            //+vai7R~
GXE_EXTERN  GdkColor   Gcsrcolor INITVAL(COLOR_LBLUE);             //+vai7I~
GXE_EXTERN  GdkColor   Gimbgcolor INITVAL(COLOR_WHITE);            //~v59zR~
GXE_EXTERN  GdkColor   Gimfgcolor INITVAL(COLOR_BLACK);            //~v59zR~
GXE_EXTERN  GdkColor   Grulerfg;                                   //~v73tI~
GXE_EXTERN  PangoLayout *Gplayout,*Gplayoutpreview;                //~v59zR~
GXE_EXTERN  PangoContext *Gppangocontext;                          //~v59zR~
GXE_EXTERN  PangoLanguage *Gppangolang;                            //~v59zR~
GXE_EXTERN  FONTDATA  Gfontdata[2];                                //~v59zR~
GXE_EXTERN  COLORDATA Gcolordata;                                  //~v59zR~
                                                                   //~v59zI~
GXE_EXTERN  GtkClipboard *Gpclipboard;                             //~v59zR~
GXE_EXTERN  GtkClipboard *Gpclipboardp;    //PRIMARY clipboard     //~v76cI~
GXE_EXTERN  GtkIMContext *Gpimcontext;                             //~v59zI~
GXE_EXTERN  int           Gxxeopt;                                 //~v59zI~
#define     GXXEOPT_KBCHK  0x01                                    //~v59zI~
GXE_EXTERN  GdkDragContext  *Gpsourcedragcontext;                  //~v59zI~
GXE_EXTERN  GtkWidget *Gppopupmenu;                                //~v59zI~
GXE_EXTERN  GnomePrintConfig *Gpprtconf;                           //~v59zI~
GXE_EXTERN  GnomePrintContext *Gpprtctxt;                          //~v59zI~
GXE_EXTERN  PangoAttrList  *Gppangoattrlist;                       //~v59zI~
GXE_EXTERN  PangoAttrList  *Gppangoattrlistscreen;                 //~vX03I~
//GXE_EXTERN  char *Ghelpdir;                                      //~v71AR~
#define DEF_HELPDIR "/usr/local/share/gnome/help/xxe"              //~v669R~
                                                                   //~v59zI~
#ifndef XXE                                                        //~v59zI~
GXE_EXTERN  void *Mppdevmode;                                      //~v59zM~
GXE_EXTERN  int Mappinitsw;                                        //~v59zM~
GXE_EXTERN  int Mshowmaxsw; //at init CMainfrm set from ini file   //~v59zM~
#endif                                                             //~v59zI~
//***************************************************************************//~v59zI~
void gxe_init(int argc,char *arg[]);                               //~v59zR~
void dummyprintf(char *Pfmt,...);	//sto set printf nop           //~v59zI~
