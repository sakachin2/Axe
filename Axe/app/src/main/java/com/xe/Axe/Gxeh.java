//*CID://+vay7R~: update#=  120;                                   //~vay7R~
//*****************************************************************//~vaafI~
//vay7:141122 (Axe)actionBar:save/saveas item                      //~vay7I~
//vad3:120424 (Axe)crash when SDCard is not mounted,use datadir to avoid it//~vad3I~
//vac6:120217 (Axe)samba share support using jcifs 3.17            //~vac6I~
//vabc:120126 (Axe)for quick startup,split asset to another zip file; user will download and unzip into /data/data/com.xe.Axe/files//~vabcI~
//vab7:120122 (Axe)Android4:getTextWidth returns width=0 if request count is too large//~vab7I~
//vaaf:111219 (Axe)ebcdic support                                  //~vaafI~
//*****************************************************************//~vaafI~
package com.xe.Axe;                                                //~@@@@I~

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

//*****************************************************************//~1606I~
                                                                   //~1606I~
public class Gxeh                                                  //~1716R~
{                                                                  //~1606I~
//**from gxe.h                                                     //~1716R~
////***************************************************************************//~1716I~
//#ifdef DUMMYPRINTF                                               //~1716I~
//    #define dprintf dummyprintf                                  //~1716I~
//#else                                                            //~1716I~
//    #define dprintf printf                                       //~1716I~
//#endif                                                           //~1716I~
public static final String XXEPGMID=     "gxe";                    //~1716I~
//************************************************************     //~1716I~
//typedef struct _FONTDATA {                                       //~1716I~
//                    gchar                 *FDfontname;           //~1716I~
//                    PangoFontDescription  *FDfontdesc;           //~1716I~
//                  } FONTDATA , *PFONTDATA;                       //~1716I~
//#define FONTDATASZ sizeof(FONTDATA)                              //~1716I~
//#define XXE_MAXFONTFACE 4                                        //~1716I~
//        PangoFontDescription  *Gsynfontdesc[XXE_MAXFONTFACE];    //~1716I~
//typedef struct _COLORDATA {                                      //~1716I~
//                    int                   CDstatus;              //~1716I~
//#define CDST_RGB       1  //RGB is valid                         //~1716I~
//#define CDST_PIXEL     2  //collor alloced                       //~1716I~
//                    GdkColor              CDcolor;               //~1716I~
//                  } COLORDATA , *PCOLORDATA;                     //~1716I~
//#define COLORDATASZ sizeof(COLORDATA)                            //~1716I~
public static final int MAX_PALETTE= 256;                          //~1716I~
//************************************************************     //~1716I~
//GXE_EXTERN  GtkWidget *Gpframe;                                  //~1716I~
//GXE_EXTERN  GtkWidget *Gpview;                                   //~1716I~
//GXE_EXTERN  GdkPixmap *Gppixmap,*Gppixmapprtscr,*Gppixmappreview;//~1716I~
//GXE_EXTERN  GdkGC     *Gpgc,*Gpprtgc,*Gpgcpreview;               //~1716I~
//GXE_EXTERN  GdkGC     *Gpgccsr;     //caret display              //~1716I~
//GXE_EXTERN  GdkGC     *Gpgcim;      //im preedit                 //~1716I~
public static Paint      Gpgcruler=new Paint();   //ruler          //~1716I~
//GXE_EXTERN  GdkColormap *Gpcolormap;                             //~1716I~
//GXE_EXTERN  GdkColor   Gxxepalette[MAX_PALETTE];                 //~1716I~
public static final int COLOR_GRAY=Color.GRAY;//    {0,0x8888,0x8888,0x8888}//~1716I~
public static final int COLOR_WHITE=Color.WHITE;//   {0,0xffff,0xffff,0xffff}//~1716I~
public static final int COLOR_BLACK=Color.BLACK;//   {0,0x0000,0x0000,0x0000}//~1716I~
public static       int[]  Gxxesynpalette=new int[16];               //~1716I~
public static final int  Gprintbg=COLOR_WHITE;                     //~1716I~
public static final int  Gprintfg=COLOR_BLACK;                     //~1716I~
public static final int  Gpreviewbg=COLOR_GRAY;                    //~1716I~
public static final int  Gcsrcolor=COLOR_GRAY;                     //~1716I~
public static final int  Gimbgcolor=COLOR_WHITE;                   //~1716I~
public static final int  Gimfgcolor=COLOR_BLACK;                   //~1716I~
//public static       int  Grulerfg;                                 //~1716I~//~1804R~
//GXE_EXTERN  PangoLayout *Gplayout,*Gplayoutpreview;              //~1716I~
//GXE_EXTERN  PangoContext *Gppangocontext;                        //~1716I~
//GXE_EXTERN  PangoLanguage *Gppangolang;                          //~1716I~
public static Font[] Gfontdata=new Font[2];//GXE_EXTERN  FONTDATA  Gfontdata[2];                              //~1716I~
public static       int Gcolordata;                                //~1716I~                                                                   //~1716I~
//GXE_EXTERN  GtkClipboard *Gpclipboard;                           //~1716I~
//GXE_EXTERN  GtkClipboard *Gpclipboardp;    //PRIMARY clipboard   //~1716I~
//GXE_EXTERN  GtkIMContext *Gpimcontext;                           //~1716I~
public static int           Gxxeopt;                               //~1716I~
public static final int     GXXEOPT_KBCHK=  0x01;                    //~1716I~
//GXE_EXTERN  GdkDragContext  *Gpsourcedragcontext;                //~1716I~
//GXE_EXTERN  GtkWidget *Gppopupmenu;                              //~1716I~
//GXE_EXTERN  GnomePrintConfig *Gpprtconf;                         //~1716I~
//GXE_EXTERN  GnomePrintContext *Gpprtctxt;                        //~1716I~
//GXE_EXTERN  PangoAttrList  *Gppangoattrlist;                     //~1716I~
//GXE_EXTERN  PangoAttrList  *Gppangoattrlistscreen;               //~1716I~
//#define DEF_HELPDIR "/usr/local/share/gnome/help/xxe"            //~1716I~
                                                                   //~1716I~
//***************************************************************************//~1716I~
//void gxe_init(int argc,char *arg[]);                             //~1716I~
//void dummyprintf(char *Pfmt,...);   //sto set printf nop         //~1716I~
                                                                   //~1716I~
//**from xxemain.h ******************************                  //~1716I~
//================================================================================//~1716I~
public static final int DEF_BGCOLOR=Color.rgb(0x10,0x20,0x10);   //~1716R~
public static final int DEF_RULERCOLOR=Color.rgb(0x20,0x60,0x20);//~1716R~
//public static final String DEF_RULERKEY="\u0001\u0002\u0003";  //S+C+F1,F2,F3//~1726R~
//================================================================================//~1716I~
public static final int MAXIMECHARCTR=   256;                      //~1716I~
public static final int MAXCOL=          240;                      //~1716I~
public static final int MAXROW=          200;                      //~1716I~
public static final int MAXCOLOR=        16;                       //~1716I~
public static final String DEF_DOSFONT=     "Terminal";  //dosapp.fon(cp437) or app932.fon//~1716I~
public static final String DEF_PRTFONTSTYLE=  "";                  //~1716I~
//public static final String DEF_FONTSTYLE=   ""; //family name; default//~1724R~
public static final String DEF_FONTSTYLE=Font.MONOSPACE;           //~1724R~
//public static final String DEF_FONTSTYLE=Font.SERIF;             //~1724R~
public static final String DEF_FONTSTYLE2=  "System";              //~1716I~
public static final int DEF_FONTSZH=     0; //by fontdescription   //~1716I~
public static final int DEF_FONTSZW=     0; //set at createfont    //~1716I~
public static final int DEF_ROWH=        0; //from font height     //~1716I~
public static final int DEF_COLW=        0; //from font width      //~1716I~
public static final int DEF_SCRROW=      25;                       //~1716I~
public static final int DEF_SCRCOL=      80;                       //~1716I~
public static final int DEF_TEXTCSR_HEIGHT=  2;  //rep mode text cursor height pixel//~1716I~
public static final int DEF_SCROLLCTR=   3;                        //~1716I~
public static final int DEF_SCROLLINITWAIT= 3;                     //~1716I~
public static final int DEF_SCROLLTIMER= 200;    //ms              //~1716I~
public static final int DEF_DBLCLICKTIMER=  500;    //ms time from push to 2nd button up//~1716I~
public static final int DEF_TYPEMATICDELAY=    250;    //typematic start after 250ms wait//~1716I~
public static final int DEF_TYPEMATICINTERVAL=    30;    //repeat typematic each 30 ms=33char/sec//~1716I~
public static final int DEF_TYPEMATICCHKINTERVAL= 30;    //timer interval//~1716I~
//*page setup                                                      //~1716I~
public static final String DEF_FORMSIZE=    "A4";                  //~1716I~
public static final String DEF_PRINTER=     "Default";             //~1716I~
public static final int DEF_A4_COL=       0;	//0 means by max lrecl//~1716I~
public static final int DEF_A4_ROW=       0;  //0 means bu maxcol and cell h/w rate//~1716I~
public static final int DEF_COLFORLRECL0= 80; //maxcol for maxlrecl=0//~1716I~
public static final int DEF_CELLSZHMAX=   14; //cell height maxlimum//~1716I~
public static final int DEF_2PCOL=        4;                       //~1716I~
public static final String PRTSCRID=        "PrtScr";              //~1716I~
                                                                   //~1716I~
//#define POINT2MM(pt)    ((float)(pt)*0.353)                      //~1716I~
//#define MM2POINT(mm)    ((float)(mm)*2.835)      // 72/25.4 //1inch=72point=25.4mm//~1716I~
//#define INCH2MM(inch)    ((float)(inch)*25.4)                    //~1716I~
//#define INCH2POINT(inch)    ((float)(inch)*72)                   //~1716I~
                                                                   //~1716I~
public static final int PRTTYPE_HCOPY=         -1;                 //~1716I~
public static final int PRTTYPE_FILE=          'F';                //~1716I~
public static final int PRTTYPE_DIR=           'D';                //~1716I~
public static final int PRTTYPE_BIN=           'B';                //~1716I~
public static final int PRTTYPE_HEX=           'H';                //~1716I~
public static final int PRTTYPE_VHEXBIN=       'b';                //~1716I~
public static final int PRTTYPE_VHEXTEXT=      't';                //~1716I~
public static final int PRTTYPE_VHEXHHEX=      'V';      //vhex and hex//~1716I~
                                                                   //~1716I~
public static final int PRTLNOTYPE_DEC=        'D';                //~1716I~
public static final int PRTLNOTYPE_HEX=        'X';                //~1716I~
public static final int PRTLNOTYPE_hex=        'x';                //~1716I~
                                                                   //~1716I~
//#define RGB_GETBLUE(rgb)   (((rgb)>>16) & 0xff)                  //~1716I~
//#define RGB_GETGREEN(rgb) (((rgb)>>8) & 0xff)                    //~1716I~
//#define RGB_GETRED(rgb)  ((rgb) & 0xff)                          //~1716I~
                                                                   //~1716I~
//#ifdef __cplusplus                                               //~1716I~
                                                                   //~1716I~
//#define COL(x)     max(0, min( Mscrcmaxcol-1, ((x-BORDERMARGIN)/Mcellw) ) )//~1716I~
//#define ROW(y)     max(0,  min( Mscrcmaxrow-1, ((y-BORDERMARGIN)/Mcellh) ) )//~1716I~
//#define COLCTR(width)   min( MAXCOL, ((width) -BORDERMARGIN)/Mcellw )//~1716I~
//#define ROWCTR(height)  min( MAXROW, (((height)-BORDERMARGIN)/Mcellh) )//~1716I~
//#define CWINWIDTH(col)   ((col)*Mcellw+BORDERMARGIN)             //~1716I~
//#define CWINHEIGHT(row)  ((row)*Mcellh+BORDERMARGIN+max(Mcellh-Mfontheight,0)) //client window height//~1716I~
//#define XX(col)         ((col)*Mcellw+BORDERMARGIN)              //~1716I~
//#define YY(row)         ((row)*Mcellh+BORDERMARGIN)              //~1716I~
//#define INVALIDATE_LINE(row) *(Mwxeintf.WXEIlineupdate+(row))=1  //~1716I~
                                                                   //~1716I~
//#define MAKE_TEXT_RECT(rect,row,col,chars) \
//            (rect)->top=YY(row);     \
//            (rect)->left=XX(col);    \
//            (rect)->bottom=YY((row)+1);    \
//            (rect)->right=XX((col)+(chars))                      //~1716I~
//#define TEXTDATA(row,col)    (Mwxeintf.WXEItext+(row)*MAXCOL+(col))//~1716I~
//#define TEXTDBCS(row,col)    (Mwxeintf.WXEIdbcs+(row)*MAXCOL+(col))//~1716I~
//#define TEXTATTR(row,col)    (Mwxeintf.WXEIattr+(row)*MAXCOL+(col))//~1716I~
//#define TEXTSTYLE(row,col)   (Mwxeintf.WXEIsynstyle+(row)*MAXCOL+(col))//~1716I~
// swap pt1 and pt2 if pt2 is top left corner                      //~1716I~
//#define CAPGETBOX(pt1,pt2)                          \
//       {                                            \
//            CPoint ptw1,ptw2;                       \
//            ptw1.x=min(pt1.x,pt2.x);                \
//            ptw1.y=min(pt1.y,pt2.y);                \
//            ptw2.x=max(pt1.x,pt2.x);                \
//            ptw2.y=max(pt1.y,pt2.y);                \
//            pt1=ptw1;                               \
//            pt2=ptw2;                               \
//        }                                               //       //~1716I~
// chk the point is in cap box                                     //~1716I~
// ret 1:in the range, 0:row is inthe range but col is out of range, -1:row is out of range//~1716I~
//#define CAPCHKINBOX(pt1,pt2,row,col)                    \
//       (                                                \
//          (row>=pt1.y && row<=pt2.y) ?                  \
//            ( (col>=pt1.x && col<=pt2.x) ? 1:0)         \
//            :-1                                         \
//       )                                                         //~1716I~
//#define SAMEPOS(pt1,pt2)                    \
//       (  (pt1.y == pt2.y) && (pt1.x == pt2.x) )                 //~1716I~
//set line draw flag                                               //~1716I~
//#define   SETDRAWLINE(row) (*(Mwxeintf.WXEIlineupdate+(row))=1)  //~1716I~
//================================================================================//~1716I~
//typedef struct _WXEINTF {                                        //~1716I~
////*wxe-->xe                                                      //~1716I~
//                        int   WXEImaxrow;   //screen max row     //~1716I~
//                        int   WXEImaxcol;   //screen max row     //~1716I~
//                        USHORT WXEIpallette[MAXCOLOR];  //dos colorno//~1716I~
//                        UINT   WXEIpalrgb[MAXCOLOR];    //rgb value(24bit color)//~1716I~
////                      int   WXEIctlkey;   //ctl key down       //~1716I~
////                      int   WXEIshiftkey; //shift key down     //~1716I~
////*xe-->wxe                                                      //~1716I~
//                        UCHAR  *WXEItext;       //scr text tbl,gotten by xe//~1716I~
//                        USHORT *WXEIattr;       //scr attr tbl,gotten by xe//~1716I~
//                        UCHAR  *WXEIlineupdate; //scr line update flag//~1716I~
//                        UCHAR  *WXEIdbcs;       //scr dbcsid tbl,gotten by xe//~1716I~
//                        UCHAR  *WXEIsynstyle;   //scr style tbl for syntax highlight//~1716I~
//                        int    *WXEIsynrgb;     //rgb table for syntax highlight//~1716I~
//                        int     WXEIcurmovesw;  //cusor moved sw //~1716I~
//                        int   WXEIcsrhstart;    //csr height start(0-->7)//~1716I~
//                        int   WXEIcsrhend;      //csr height end(0-->7)//~1716I~
//                        int   WXEIcsrvisible;   //csr visible    //~1716I~
//                        int   WXEIcsrchangesw;  //csr type changed//~1716I~
//                        int   WXEIcsrvisiblechangesw;   //csr visibility changed//~1716I~
//                        int   WXEIcsrmovesw;    //csr moved      //~1716I~
//public static final int WXEICSR_CHANGEBYKBD= 0x04;               //~1716I~
//public static final int WXEICSR_CHANGEROW=   0x02;               //~1716I~
//public static final int WXEICSR_CHANGECOL=   0x01;               //~1716I~
////                      int   WXEIcsrdbcssw;    //csr on dbcs    //~1716I~
//                        int   WXEIvsplitsw;                      //~1716I~
//                        int   WXEIsplitpos;                      //~1716I~
//                        int   WXEIscrbg[2]; //split screen bg pallet no//~1716I~
//                        char *WXEIerrmsg;       //multi line uerrmsg//~1716I~
//                        int   WXEIatcmdopt;                      //~1716I~
//                        int   WXEIatcmdintvl;                    //~1716I~
//                        int   WXEIatcmdrepeat;                   //~1716I~
//                        int   WXEIatcmdcurrctr;                  //~1716I~
//                        char *WXEIatcmdstring;                   //~1716I~
//                        char *WXEIatcmdnext;                     //~1716I~
//                        int   WXEIsleepinmult;//sleep in mult cmd//~1716I~
//                        int   WXEIsleeptime;                     //~1716I~
//                        int   WXEIsleepmaxtime;                  //~1716I~
////*xe<-->wxe                                                     //~1716I~
//                        int   WXEIcsrposx;  //curr cursor pos col//~1716I~
//                        int   WXEIcsrposy;  //curr cursor pos row//~1716I~
//                        int   WXEIoldcsrposy;   //prev cursor pos row//~1716I~
//                        int   WXEIxetermsw;    //xeterm requested//~1716I~
//                        int   WXEIstatus;   //comm flag with xe  //~1716I~
//public static final int WXEIS_DRAWMENU=        0x00000001;    //draw menue required by reset func//~1716I~
//public static final int WXEIS_PRTUTF8=         0x00000010;    //fileprint for UTF8 file//~1716I~
////public static final int WXEPRINT_UTF8MODE()  (Mwxeintf.WXEIstatus & WXEIS_PRTUTF8)//~1716I~
//public static final int WXEIS_PRTVHEX=         0x00000020;    //fileprint vhex mode//~1716I~
//public static final int WXEIS_PRTEBC=          0x00000100;    //fileprint for EBC file//~1716I~
////public static final int WXEPRINT_EBCFILE()   (Mwxeintf.WXEIstatus & WXEIS_PRTEBC)//~1716I~
//public static final int WXEIS_PRTEBCBYUCS=     0x00000200;    //fileprint for EBC file//~1716I~
////#define WXEPRINT_EBCBYUCS()  (Mwxeintf.WXEIstatus & WXEIS_PRTEBCBYUCS)//~1716I~
//                        int   WXEIkbddelay; //ms   initial wait to start typematic//~1716I~
//                        int   WXEIkbdspeed; //ms                 //~1716I~
//                        UCHAR WXEIimembs[MAXIMECHARCTR]; //ime translated MBstring//~1716I~
//                    } WXEINTF;                                   //~1716I~
//typedef WXEINTF *PWXEINTF;                                       //~1716I~
//#define WXEINTFSZ (sizeof(WXEINTF))                              //~1716I~
                                                                   //~1716I~
////================================================================================//~1716I~
////                                                               //~1716I~
////================================================================================//~1716I~
////* public func *******************                              //~1716I~
//    void scrruler(int Prow,int Pcol);                            //~1716I~
//    int  scrcsr(void);                                           //~1716I~
//    int  xxemain_iniput(WXEINIDATA *pwxei);                      //~1716I~
//    void xxemain_construct(void);                                //~1716I~
//    int  xxemain_init(int Pswinigetfail);                        //~1716I~
//    int  xxemain_onkeydown(UINT nChar, UINT nScan, UINT nFlags,char *Pstr,int Pstrlen);//~1716I~
//    int  xxemain_onchar(UINT nChar, UINT nScan, UINT nFlags,char *Pstr,int Plen);//~1716I~
//    int  xxemain_onkeyup(UINT nChar, UINT nsCAN, UINT nFlags,char *Pstr);//~1716I~
//    void xxemain_scrinvalidate(int Popt);                        //~1716I~
//#define SCRINVO_FULLDRAW       0x01       //fullscreen update    //~1716I~
//#define SCRINVO_SYNCEXPOSE     0x02       //syncronously expose  //~1716I~
//    int  xxemain_scrsetruler(int Ptype);                         //~1716I~
//    void exitmain();                                             //~1716I~
//    void xxemain_kbdinit(void);                                  //~1716I~
//    void xxemain_scrinit(void);                                  //~1716I~
//    void xxemain_prtinit(void);                                  //~1716I~
//    int  xxemain_createfont(void);                               //~1716I~
//    void xxemain_scrbgrect(void);                                //~1716I~
//    int  xxemain_OnSize(int Pwidth,int Pheight);                 //~1716I~
//    int  xxemain_scrcancel(void);                                //~1716I~
//    int  xxemain_scrend(void);                                   //~1716I~
//    int  xxemain_scrcmd(int Pfuncid,char *Pparm);                //~1716I~
//    int  xxemain_strcmd(char *Pcmd,char *Pparm);                 //~1716I~
//    int  xxemain_scrpreedit(char *Pstr,int Plen,int Pcsrpos);    //~1716I~
//    int  xxemain_onfileopenwith(void);                           //~1716I~
//    void xxemain_mouselbdown(UINT nFlags, CPoint point);         //~1716I~
//    int  xxemain_mouserbdown(UINT nFlags, CPoint point);         //~1716I~
//    int  xxemain_mousembdown(UINT nFlags, CPoint point);         //~1716I~
//    void xxemain_mouselbup(UINT nFlags, CPoint point);           //~1716I~
//    boolean xxemain_onmousewheel(UINT nFlags, int Pdirection, CPoint pt);//~1716I~
//    boolean xxemain_mouselbdblclk(UINT nFlags,CPoint pt);        //~1716I~
//    int xxemain_setupdragfile(ULONG Pcmdparm1,ULONG Pcmdparm2);  //~1716I~
//#define SDFP2_SETUPREQ    0x01  //etupreq                        //~1716I~
//#define SDFP2_SETUPMSG    0x02  //msg to display uerrmsg         //~1716I~
//#define SDFP2_SETUPEND    0x04  //setup end                      //~1716I~
//    boolean xxemain_ontimer(void *Ptimerid);                     //~1716I~
//    boolean xxemain_ontimeratcmd(UINT Ptimerid);                 //~1716I~
//    void xxemain_mousemove(UINT nFlags, CPoint point);           //~1716I~
//    int  xxemain_cpcopy(int Pcutsw);                             //~1716I~
//    int  xxemain_cppaste(int Pinssw);                            //~1716I~
//#define PASTE_TEXT_ONLY      0x100  //only effective when clipboard is not dropfile but text//~1716I~
//#define PASTE_INSMODE        0x001  //paste-ins                  //~1716I~
//#define PASTE_PASTEV         0x002  //pastev                     //~1716I~
//    int  xxemain_mousedragbegin(void);                           //~1716I~
//    int  xxemain_mousedraggetdata(char **Plist);                 //~1716I~
//    int  xxemain_mousedragend(void);                             //~1716I~
//    boolean xxemain_cpupdatecopy();                              //~1716I~
//    boolean xxemain_cpupdatecut();                               //~1716I~
//    boolean xxemain_cpupdatepaste();                             //~1716I~
//    boolean xxemain_cpupdatepastev();                            //~1716I~
//    int mouse_csrmovedbykbd(int Popt);                           //~1716I~
//    void xxemain_scrupdate(CDC *Ppdc,int Pprintsw);              //~1716I~
//    int  xxemain_OnOk(void);                                     //~1716I~
//    void xxemain_scronok(int Pcpchngsw);                         //~1716I~
//    int  xxemain_scrstrcmd(char *Pcmd,char *Pparm);              //~1716I~
//    int  xxemain_scrcmd(int Pfuncid,char *Pparm);                //~1716I~
//    int  xxemain_prtajustcolrow(int Pscrtype,int Pformw,int Pformh,int Phdrftr,int Pcellw,int Pcellh,//~1716I~
//                              int Pmaxlrecl,int Ptotlineno,int Plinenofw,int Pcol2p,//~1716I~
//                              int *Ppcmaxcol,int *Ppcmaxrow,int Pchkpfontsz,//~1716I~
//                              int Ppcellszhmax,                  //~1716I~
//                              int *Ppdatamaxcol,int *Ppdatamaxrow);//~1716I~
//    int  xxemain_onprepareprinting(int Pnotpreviewsw);           //~1716I~
//    int  xxemain_onprint(void);                                  //~1716I~
//    void xxemain_prtpreparedc(int Pcurpageno);                   //~1716I~
//    void xxemain_onendprinting(void);                            //~1716I~
//    void xxemain_prtdefaultmarginrect(RECT *Pprect);             //~1716I~
//    int  xxemain_prtgetscrdata(int Phcopysw,boolean Pwwscrprt,void **Pppcw,void **Pppfh,char *Poutfnm,//~1716I~
//                    int *Pptotlineno,int *Ppmaxlrecl,int *Pplinenodigit,int *Ppcmaxlrecl,int *Pplnotype);//~1716I~
//    int  xxemain_prtpreview(int Phcopysw,int Pcurpage);          //~1716I~
//    void xxemain_mousecpdrawpan(int Prow1,int Prow2,int Prow3);  //~1716I~
//    void xxemain_onsetfocus(void);                               //~1716I~
//    void xxemain_onkillfocus(void);                              //~1716I~
//    int xxemain_csrposchng(int Popt);                            //~1716I~
//    void afterprint(int Popt);                                   //~1716I~
//* public data *******************                                //~1716I~
public static  int  Mxeinitsw;      	    //xe init called       //~1716I~
public static  int  Mxxemaininitsw;      	//xxemain init end     //~1716I~
public static  int  Mbgcolor;                                  //~1716I~
public static  int  Mrulercolor;                               //~1716I~
public static  String Mrulerkey;        //3 letter                //~1716I~
public static  String Mcharinpup;                                //~1716I~
public static  String Mcharinpdn;                                //~1716I~
public static  String Mcharwp;                                   //~1716I~
public static  int  Mscrollctr;                                    //~1716I~
public static  int  Mscrolltimereventctr;	//time event counter   //~1716I~
public static  int  Mscrolltimer;                                  //~1716I~
public static  int  Mdblclicktimer;                                //~1716I~
public static  int  Mtypematicdelay;                               //~1716I~
public static  int  Mtypematicinterval;                            //~1716I~
public static  int  Mtypematicchkinterval;                         //~1716I~
public static  int  Mtypematicintervalwait;                        //~1716I~
public static  int  Mfontwidth;                                    //~1716I~
public static  int  Mfontheight;                                   //~1716I~
public static  int  Mmonospace;		//screen font is monospace     //~1716I~
public static  int  Mmonospacepreviewfile; //file preview font is monospace//~1716I~
public static  int  Mfontoffsx,Mfontoffsy;	//offset in cell       //~1716I~
public static  int  Mcellh;        //row height ,=fontheight if Mcellh0=0//~1716I~
public static  int  Mcellw;         //col width                    //~1716I~
public static  int  Mcellh0;       //row height by option dlg      //~1716I~
public static  int  Mcellw0;        //col width                    //~1716I~
public static  boolean Mttfont;            //row count of displayed scr//~1716I~
public static  String Mfontstyle;                                  //~1716I~
public static  int  Museact;   		//use accelerator tbl          //~1716I~
public static  int  Musemk ;   		//use menukey                  //~1716I~
public static  boolean Mligature; 		//use ligature             //~1716I~
public static  boolean Mligaturereverse;  //temp toggle ligature   //~1716I~
public static  boolean Mrctl  ;   		//use r-ctrl key           //~1716I~
public static  boolean Mfreecsr;  		//free cursor mode         //~1716I~
public static  boolean Mbeep;                                      //~1716I~
public static  int  Mfulldrawsw;                                   //~1716I~
//  CWnd *Mpmainframe;	//mainframe window for set title           //~1716I~
public static  int  Mhcopysw;			//hardcopy or fileprint    //~1716I~
public static  int  Mprtcmaxcol;		//current file print max col per line//~1716I~
public static  int  Mprtdatamaxcol;	//actual colomn for small font //~1716I~
public static  int  Mprtmaxlrecl;		//max lrecl to cut line    //~1716I~
public static  int  Mprtcmaxlrecl;		//hex display width by pfh //~1716I~
public static  int  Mprtcmaxrow;		//current file print max col per line//~1716I~
public static  int  Mprtdatamaxrow;	//actual data row for small font//~1716I~
public static  int  Mprtcurpage;   	//file print current pageno;start 0//~1716I~
public static  int  Mprtlnotype;   	//hex or decimal               //~1716I~
//*pagesetup dialog data                                           //~1716I~
public static  String  	Mformtype;                                 //~1716I~
public static  String  	Mprinter;                                  //~1716I~
public static  String   Mprinter_cupsdefault;                      //~1716I~
public static  String   Mprtfontstyle;                             //~1716I~
public static  boolean	Mportlate;                                 //~1716I~
public static  boolean	M2p;                                       //~1716I~
public static  boolean	Mheader;                                   //~1716I~
public static  boolean	Mfooter;                                   //~1716I~
public static  boolean	Mlinenumber;                               //~1716I~
public static  boolean	Mfiletime;                                 //~1716I~
public static  boolean	Msystime;                                  //~1716I~
public static  boolean	Mlandscape;                                //~1716I~
public static  boolean	Mfullpage ;                                //~1716I~
public static  boolean	Mwwscrprt;                                 //~1716I~
public static  boolean	Mchkpfontsz;                               //~1716I~
public static  int		Mprtcol;                                   //~1716I~
public static  int		Mprtrow;                                   //~1716I~
public static  int		Mpfontszh,Mpfontszw;                       //~1716I~
public static  int		Mpcellszh,Mpcellszw;                       //~1716I~
public static  int		Mpcellszhmax;                              //~1716I~
public static  int		Mdrawcellh,Mdrawcellw;                     //~1716I~
public static  int		Mprt2pcol;                                 //~1716I~
public static  int		Mmarginl;                                  //~1716I~
public static  int		Mmarginr;                                  //~1716I~
public static  int		Mmargint;                                  //~1716I~
public static  int		Mmarginb;                                  //~1716I~
public static  int     Mscrcmaxrow;           //current scr maxrow //~1716I~
public static  int     Mscrcmaxcol;           //current scr maxcol //~1716I~
public static  int  Mswkillfocus;                                  //~1716I~
//public static  time_t  Msetfocustime;                            //~1716I~
public static  int     Msetfocusmsec;                              //~1716I~
//public static  time_t  Mscrolllocktime;                          //~1716I~
public static  int     Mscrolllockmsec;                            //~1716I~
public static  int     Mscrolllocksw; //on from time of VK_SCROLL keyup to VK_SCROLL keydown//~1716I~
public static  int  	 Mscrheight;            //scr height  Y    //~1716I~
public static  int  	 Mscrwidth;             //scr width   X    //~1716I~
//public static  WXEINTF Mwxeintf;                                 //~1716I~
public static  Point   Mcellcopy1,Mcellcopy2;   //cut and paste sw //~1716I~
public static  int     Mcpcopypansw;         //cut on panel(not file data)//~1716I~
public static  int     Mlbdblclicksw;			//dbl click accepted to ignore next lb up//~1716I~
public static  int     Mcpcopysw;            //cut and paste;copy sw//~1716I~
public static  int     Mcppastesw;          //paste done           //~1716I~
public static  int     Mcpblockmode;			                   //~1716I~
public static  Point   Mcpstart;                //mouse move start point//~1716I~
public static  Point   Mcpoldpos;  		 //cap box draw old pos    //~1716I~
public static  int     Mlbposid;			    //for auto scroll by edge mouse//~1716I~
//public static  void   *Mdragplhs;           //drag drop plh slection range start//~1716I~
//public static  void   *Mdragpcw;            //to chk drag range by Shift+Lbutton//~1716I~
//public static  void   *Mdragedpcw;      //darg begin issued pcw  //~1716I~
public static  String  Mdraglist;			//dragged filename list//~1716I~
public static  int     Mdragacceptsw; 	//get data event occued    //~1716I~
public static  int     Mdroprc;       	//internal drag/drop drop rc//~1716I~
public static  int     Mdragtimeout;  	//from dragstart to dragend//~1716I~
public static  int     Mfloatmenusw;                               //~1716I~
public static  int     Mwinexth,Mwinextw;	//windows physical screen size//~1716I~
public static  int     Mprtmaxpage;         	//print total page //~1716I~
public static final int      MAXPAGERANGE=   20;                   //~1716I~
public static  int[]   Mpagerange=new int[MAXPAGERANGE];  //print page range parm//~1716I~
public static  int     Mpagerangeno;	//print page range entry count//~1716I~
                                                        //~1716I~
//public static  void      *Mpprtfont;                               //~1716I~
                                                            //~1716I~
                              //~1716I~
                                                            //~1716I~
public static  int     Mprtfontascendant; //font baseline pos      //~1716I~
public static  boolean    Mimageprtscr;  //print screen by image print//~1716I~
public static  int     Mpreviewmode;                               //~1716I~
public static  double Mprevieworgx,Mprevieworgy,Mpreviewscalex,Mpreviewscaley;	//for preview//~1716I~
public static  int  Mrulermode; //on from time of VK_SCROLL keyup to VK_SCROLL keydown//~1716I~
public static final int  WXERULER_NONE=  00;                       //~1716I~
public static final int  WXERULER_V=     01;                       //~1716I~
public static final int  WXERULER_H=     02;                       //~1716I~
public static final int  WXERULER_VH=    03;                       //~1716I~
public static 	int Mrulerrow,Mrulercol,Mrulermodeo;//old pos to erase prev cursor//~1716I~
//public static 	char Mlinedrawstat[MAXROW];//line draw status  //~1716I~
public static final int LDS_DRAWN=       0x01;   //drawn by update,full draw,combine/ligature//~1716I~
public static final int LDS_DRAWSKIP=    0x02;   //drawn skipped   //~1716I~
public static final int LDS_COMBINE=     0x10;   //drawn line contains combining//~1716I~
public static final int LDS_LIGATURE=    0x20;   //drawn line contains ligature//~1716I~
public static final int LDS_DDFMT=       0x40;   //contains        //~1716I~
                                                                   //~1716I~
//* priveate func ************************************             //~1716I~
//* private data ********************************                  //~1716I~
static  int  Mscrmaxrow;            //row count of displayed scr   //~1716I~
static  int  Mscrmaxcol;            //col count                    //~1716I~
static  int  Mnewimephrase;			//ime position set             //~1716I~
static  int  Mimeonsw;			    //for auto scroll by edge mouse//~1716I~
//static  void *Mdragpfh;			//to chk drag range by Shift+Lbutton//~1716I~
//***************************************************              //~1716I~
                                                                   //~1716I~
public static final int TID_SCROLLCHK=     0x80;     //csr pos is left edge when lbtn down//~1716I~
public static final int TID_SCLEFT=        0x01;     //csr pos is left edge when lbtn down//~1716I~
public static final int TID_SCRIGHT=       0x02;                   //~1716I~
public static final int TID_SCTOP=         0x04;                   //~1716I~
public static final int TID_SCBOTTOM=      0x08;                   //~1716I~
public static final int TID_ATCMD=       0x0100;     //msg for AT cmd//~1716I~
public static final int TID_ATCMDTIMER=  0x0200;     //AT cmd timer interval msg//~1716I~
public static final int TID_ATCMDDRAW=   0x0400;     //to draw err msg//~1716I~
public static final int TID_ATCMDSLEEP=  0x0800;     //sleep timer //~1716I~
public static final int TID_SLEEPSUBCMP= 0x1000;     //sleep cmd in multi-cmd comp//~1716I~
public static final int TID_RCTLKEY=     0x010000;     //rctl typematic support//~1716I~
                                                                   //~1716I~
//    int  createfontlist(CComboBox *Ppcombo,char *Pcurfont);      //~1716I~
//********************** public                                    //~1716I~
//*pagesetup end                                                   //~1716I~
//***************************************************************  //~1716I~
//private :                                                        //~1716I~
//    void prtformsetup(int Pformid,int Plandscape);               //~1716I~
//********************** private                                   //~1716I~
//    CFont *Mpfont;                                               //~1716I~
//    CFont *Mpfonts[WXEFONTSTYLE_MAX];   //normal,bold,italic,... //~1716I~
//    CView *Mpview;                                               //~1716I~
//    CBitmap *Mpbgbitmap;                                         //~1716I~
//*Additional
//public static int Mfontsize;                                     //~1822R~
public static String Mstyle=Font.Normal; //Bold/Italic/Plain/Normal//~1716R~
public static int MbaseLine;                                       //~1823R~
//public static int Mfm_fontwidth,Mfm_fontheight,Mfm_monospace;    //~1822R~
public static boolean Mqexit;                                          //~1725I~
public static String privateTop;//../files/                        //~vabcI~
public static String homeDir;	//for "~/" getEnv(HOME) at ufullpath//~1A12I~
public static String addPath;	//putenv sh and bin on home to PATH//~1A25I~
public static String initCmd;	//when VIEW/EDIT intent received   //~1A17I~
public static String initFile;	//when VIEW/EDIT intent received   //~1A17I~
public static String envPath="";                                   //~1A26R~
public static String localeCharset="";                             //~vad3I~
public static String icuSwFile="";                                 //~vaafI~
public static int    osVersion;                                    //~vab7I~
//*smb                                                             //~vac6I~
public static int      SMB_fileno;                                 //~vac6I~
public static String[] SMB_namelist;                               //~vac6I~
public static int[]    SMB_attrlist;                               //~vac6I~
public static long[]   SMB_sizelist;                               //~vac6I~
public static int[]    SMB_timelist;	//sec from 1970            //~vac6R~
public static String   SMB_name;                                   //~vac6I~
public static int      SMB_attr;                                   //~vac6I~
public static long     SMB_size;                                   //~vac6I~
public static int      SMB_time;                                   //~vac6R~
public static String   SMB_errmsg;                                 //~vac6I~
//*filedialog                                                      //~vay7I~
public static String   mCurrentFilename;                           //~vay7I~
public static int      mCurrentFilenameType;                       //+vay7I~
}                                                                  //~1528R~
