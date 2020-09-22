//*CID://+vb91R~:                             update#=  389;       //+vb91R~
//************************************************************************//~v51dI~
//vb91:170228 marge rh9j gxe to arm-gxe                            //+vb91I~
//				vb74:161220 Gtk3 warning                           //+vb91I~
//v@@@:110610 Android(-DARM)                                       //~v@@@I~
//va72:100801 LP64 wchar_t is 4byte on LP64                        //~va72I~
//v71A:061030 Use HtmlHelp;Winhelp is deprecated at Vista;XP english ersion cannot read .hlp by the readson of codepage unsupported//~v71AI~
//v69r:060531 (XXE)print debug message by GError                   //~v69rI~
//v66D:051220 (BUG:XXE)gxe need Ctrl+C after System-Close button pushed//~v66DI~
//v66B:051220 (XXE)html help top index split to japanese and english//~v66BI~
//v66r:051025 (XXE) for v66m for WXE                               //~v66rI~
//v66p:051026 (XXE)std paste support                               //~v66pI~
//v669:050826 (XXE)help support                                    //~v669I~
//vXXE:050611 new for XXE                                          //~vXXEI~
//v55F:040314 (WXE)english help                                    //~v55FI~
//v55z:040304 (WXE)help support                                    //~v55zI~
//v53t:031004 (WXE:BUG)not freed area exist                        //~v51wI~
//v51w:030615 (WXE)icon for MDOS and Explorer                      //~v51wI~
//v51d:030517 (WXE)bitmap menu                                     //~v51dI~
//************************************************************************//~v51dI~

#ifdef HAVE_CONFIG_H                                               //~vXXEI~
#  include <config.h>                                              //~vXXEI~
#endif                                                             //~vXXEI~
                                                                   //~vXXEI~
#ifndef ARM                                                        //~v@@@I~
#include <gnome.h>                                                 //~vXXEI~
#include <libgnomeprint/gnome-print.h>                             //~vXXEI~
#include <libgnomeprint/gnome-print-job.h>                         //~vXXEI~
//#include <libgnome/gnome-help.h>                                 //~v71AR~
                                                                   //~vXXEI~
#include "callbacks.h"                                             //~vXXEI~
#include "interface.h"                                             //~vXXEI~
#include "support.h"                                               //~vXXEI~
#else                                                              //~v@@@I~
	#include <jnig.h>                                              //~v@@@I~
#endif //ARM                                                       //~v@@@I~
                                                                   //~vXXEI~
#include <ulib.h>                                                  //~vXXEI~
#include <ufile.h>                                                 //~v669I~
#include <uerr.h>                                                  //~v669I~
#include <utrace.h>                                                //~v@@@I~
                                                                   //~vXXEI~
#include <xxedef.h>                                                //~vXXER~
#include <xxeres.h>                                                //~vXXEI~
#define GBL_GXEMFRM                                                //~vXXEI~
#include <gxe.h>                                                   //~vXXEI~
#include <xxemain.h>                                               //~vXXEI~
#include <xxecsub.h>                                               //~vXXEI~
#include <xxexei.h>                                                //~vXXEI~
                                                                   //~vXXEI~
#include "gxemfrm.h"                                               //~vXXEI~
#include "gxeview.h"                                               //~vXXEI~

//#include "wxefile.h"                                             //~vXXER~
//#include "wxedoc.h"                                              //~vXXER~
//#include "wxehelp.h"                                             //~vXXER~
//                                                                 //~vXXER~
//#include "utrace.h"                                              //~vXXER~

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

//**************************************************************************//~vXXEI~
//for menu key enable/disable                                      //~vXXEI~
typedef struct _MAINMENUTBL {                                      //~vXXER~
								int          MMTid;                //~vXXEM~
                                UCHAR       *MMTstrid;             //~vXXEM~
                                GtkMenuItem *MMTpmenuitem;         //~vXXEM~
                                GtkLabel    *MMTplabel;            //~vXXEM~
                                UCHAR        MMTlabelon[16];       //~vXXER~
                                UCHAR        MMTlabeloff[16];      //~vXXER~
                            } MAINMENUTBL,*PMAINMENUTBL;           //~vXXEM~
#define MAINMENUTBL_ENTRYDEF(menuid,labon)  {menuid,STR##menuid,0,0,labon,""}//~vXXER~
                                                                   //~vXXEI~
//for accelerator enable/disable                                   //~vXXEI~
typedef struct _ACCMENUTBL {                                       //~vXXER~
								int          AMTid;                //~vXXEI~
                                UCHAR       *AMTstrid;             //~vXXEI~
                                GtkMenuItem *AMTpmenuitem;         //~vXXEI~
                                int          AMTkey;               //~vXXEI~
                                int          AMTmodifier;          //~vXXER~
                                UCHAR        AMTpath[32];;         //~vXXEI~
                            } ACCMENUTBL,*PACCMENUTBL;             //~vXXEI~
#define ACCPATH_PREFIX   "<"XXEPGMID"-"XXEPGMID">"                 //~vXXER~
#define ACCMENUTBL_ENTRYDEF(menuid,path)  \
	{menuid,STR##menuid,0,ACCKEY##menuid,(GDK_CONTROL_MASK),ACCPATH_PREFIX "/" path "/" STR##menuid}//~vXXEI~
                                                                   //~vXXEI~
//for context menuitem enable/disable                              //~vXXEI~
typedef void (CONTEXTMENU_ENABLE_FUNC)(GtkMenuItem  *Pmenuitem);   //~vXXEI~
typedef struct _CONTEXTMENUTBL {                                   //~vXXEI~
								int          CMTid;                //~vXXEI~
                                UCHAR       *CMTstrid;             //~vXXEI~
                                GtkMenuItem *CMTpmenuitem;         //~vXXEI~
        						CONTEXTMENU_ENABLE_FUNC *CMTfunc;  //~vXXEI~
                                UCHAR       *CMTtbstrid;           //~vXXEI~
                                GtkWidget   *CMTptoolbarbutton;    //~vXXEI~
                            } CONTEXTMENUTBL,*PCONTEXTMENUTBL;     //~vXXER~
#define CONTEXTMENUTBL_ENTRYDEF(menuid,func)  {menuid,STR##menuid,0,func,STRTB##menuid,0}//~vXXER~
                                                                   //~vXXEI~
//for popup context menuitem enable/disable                        //~vXXEI~
typedef struct _POPUPMENUTBL {                                     //~vXXEI~
								int          PMTid;                //~vXXEI~
                                UCHAR       *PMTstrid;             //~vXXEI~
                                GtkMenuItem *PMTpmenuitem;         //~vXXER~
                            } POPUPMENUTBL,*PPOPUPMENUTBL;         //~vXXEI~
#define POPUPMENUTBL_ENTRYDEF(menuid,strid)  {menuid,strid,0}      //~vXXER~
//**************************************************************************//~vXXEI~
void popupmenu_activate_callback(GtkWidget  *Ppmenuitem,gpointer Puparm);//~vXXER~
void popupmenu_activate_callback2(GtkWidget  *Ppmenuitem,gpointer Puparm);//~vXXEI~
void popupmenu_activate_openwith(GtkWidget  *Ppmenuitem,gpointer Puparm);//~vXXEI~
int popupmenu_srchid(int Pitemid);                                 //~vXXEM~
void popupmenu_init(void);                                         //~vXXEM~
int append_menuitem_by_stockid(int Pitemid,char *Pstockid);        //~vXXEM~
void popupmenu_activate_callback(GtkWidget  *Ppmenuitem,gpointer Puparm);//~vXXEM~
void popupmenu_deactivate_callback(GtkWidget  *Ppmenuitem,gpointer Puparm);//~vXXEM~
int  updatemainmenutext(PMAINMENUTBL Ppmt,int Pusemk);             //~vXXEI~
int  updatemenu(int Puseact);                                      //~vXXEI~
int  actinit(void);                                                //~vXXEI~
void changemenuaccelkey(void);                                     //~vXXER~
//**************************************************************************//~vXXEI~
//mainmenu menuitem name and widget ptr for change menukey use     //~vXXEI~
static MAINMENUTBL Smainmenutbl[]={                                //~vXXEI~
					MAINMENUTBL_ENTRYDEF(ID_FILE,"_File"),         //~vXXER~
					MAINMENUTBL_ENTRYDEF(ID_EDIT,"_Edit"),         //~vXXER~
					MAINMENUTBL_ENTRYDEF(ID_SETUP,"_Setup"),       //~vXXER~
					MAINMENUTBL_ENTRYDEF(ID_PAGE_SETUP,"_Preview"),//~vXXER~
					MAINMENUTBL_ENTRYDEF(ID_HELP,"_Help")          //~vXXER~
								  };                               //~vXXEI~
#define MAINMENUTBLENTNO  (sizeof(Smainmenutbl)/sizeof(MAINMENUTBL))//~vXXER~
                                                                   //~vXXEI~
//accelerator set menuitem tbl                                     //~vXXEI~
static ACCMENUTBL Saccmenutbl[]={                                  //~vXXEI~
					ACCMENUTBL_ENTRYDEF(ID_FILE_NEW,"file"),       //~vXXEI~
					ACCMENUTBL_ENTRYDEF(ID_FILE_OPEN,"file"),      //~vXXEI~
					ACCMENUTBL_ENTRYDEF(ID_FILE_SAVE,"file"),      //~vXXEI~
					ACCMENUTBL_ENTRYDEF(ID_FILE_SAVEAS,"file"),    //~vXXEI~
					ACCMENUTBL_ENTRYDEF(ID_FILE_PRINT,"file"),     //~vXXEI~
					ACCMENUTBL_ENTRYDEF(ID_FILE_PRTSCR,"file"),    //~vXXEI~
//  				ACCMENUTBL_ENTRYDEF(ID_FILE_PREVIEW,"file"),   //~v66pR~
					ACCMENUTBL_ENTRYDEF(ID_FILE_HOME,"file"),      //~vXXEI~
					ACCMENUTBL_ENTRYDEF(ID_FILE_TERMINAL,"file"),  //~vXXEI~
					ACCMENUTBL_ENTRYDEF(ID_FILE_EXIT,"file"),      //~vXXEI~
					ACCMENUTBL_ENTRYDEF(ID_EDIT_CUT,"edit"),       //~vXXEI~
					ACCMENUTBL_ENTRYDEF(ID_EDIT_COPY,"edit"),      //~vXXEI~
					ACCMENUTBL_ENTRYDEF(ID_EDIT_CLEAR,"edit"),     //~vXXEI~
					ACCMENUTBL_ENTRYDEF(ID_EDIT_PASTE_V,"edit"),   //~v66pR~
					ACCMENUTBL_ENTRYDEF(ID_EDIT_PASTE,"edit"),     //~vXXEI~
					ACCMENUTBL_ENTRYDEF(ID_EDIT_PASTE_REP,"edit")  //~vXXEI~
								  };                               //~vXXEI~
#define ACCMENUTBLENTNO  (sizeof(Saccmenutbl)/sizeof(ACCMENUTBL))  //~vXXEI~
                                                                   //~vXXEI~
//context menuitem/toolbar-button widget ptr for disable/enable (edit context menu)****************//~vXXER~
#ifndef ARM                                                        //~v@@@I~
static  CONTEXTMENUTBL Scontextmenutbl[]={                         //~vXXER~
				CONTEXTMENUTBL_ENTRYDEF(ID_EDIT_CUT,gxeview_OnUpdateEditCut),//~vXXEI~
				CONTEXTMENUTBL_ENTRYDEF(ID_EDIT_COPY,gxeview_OnUpdateEditCopy),//~vXXEI~
				CONTEXTMENUTBL_ENTRYDEF(ID_EDIT_CLEAR,gxeview_OnUpdateEditClear),//~vXXEI~
				CONTEXTMENUTBL_ENTRYDEF(ID_EDIT_PASTE_V,gxeview_OnUpdateEditPasteV),//~v66pR~
				CONTEXTMENUTBL_ENTRYDEF(ID_EDIT_PASTE,gxeview_OnUpdateEditPaste),//~vXXEI~
				CONTEXTMENUTBL_ENTRYDEF(ID_EDIT_PASTE_REP,gxeview_OnUpdateEditPasteRep)//~vXXEI~
                						};                         //~vXXEI~
#define CONTEXTMENU_ITEMCTR (sizeof(Scontextmenutbl)/sizeof(CONTEXTMENUTBL))//~vXXER~
#endif                                                             //~v@@@I~
                                                                   //~vXXEI~
//popupmenu menuitem id,name and widget ptr ******************     //~vXXER~
static POPUPMENUTBL  Spopupmenutbl[]={                             //~vXXEI~
				POPUPMENUTBL_ENTRYDEF(ID_EDIT_CUT,STRID_EDIT_CUT), //~vXXER~
				POPUPMENUTBL_ENTRYDEF(ID_EDIT_COPY,STRID_EDIT_COPY),//~vXXER~
				POPUPMENUTBL_ENTRYDEF(ID_EDIT_CLEAR,STRID_EDIT_CLEAR),//~vXXER~
				POPUPMENUTBL_ENTRYDEF(ID_EDIT_PASTE_V,STRID_EDIT_PASTE_V),//~v66pI~
				POPUPMENUTBL_ENTRYDEF(ID_EDIT_PASTE,STRID_EDIT_PASTE),//~vXXER~
				POPUPMENUTBL_ENTRYDEF(ID_EDIT_PASTE_REP,STRID_EDIT_PASTE_REP),//~vXXER~
				POPUPMENUTBL_ENTRYDEF(ID_FILE_SAVE,STRID_FILE_SAVE),//~vXXER~
				POPUPMENUTBL_ENTRYDEF(ID_EDIT_END,STRTBID_EDIT_END),//~vXXER~
				POPUPMENUTBL_ENTRYDEF(ID_EDIT_CANCEL,STRTBID_EDIT_CANCEL),//~vXXER~
				POPUPMENUTBL_ENTRYDEF(ID_FILE_OPENWITH,"")         //~vXXER~
                				};                                 //~vXXEI~
#define POPUPMENU_ITEMCTR (sizeof(Spopupmenutbl)/sizeof(POPUPMENUTBL))//~vXXER~
                                                                   //~vXXEI~
#ifndef ARM                                                        //~v@@@I~
//* popup menu table                                               //~vXXEI~
#define PIX_STOCK(stockid)  GNOME_APP_PIXMAP_STOCK,stockid         //~vXXER~
static GnomeUIInfo Spopupmenuuiinfo[] = {                          //~vXXEI~
                                                                   //~vXXEI~
	GNOMEUIINFO_SEPARATOR,                                         //~vXXEI~
                                                                   //~vXXEI~
	{GNOME_APP_UI_ITEM, N_("Cut(_T)"),                        //type,label//~vXXER~
	 NULL,                                                 //hint  //~vXXEI~
	 popupmenu_activate_callback,(gpointer)1, NULL, //callback,callback parm(table index),reseved//~vXXER~
	 PIX_STOCK(GTK_STOCK_CUT),								//pixmap type and id//~vXXEI~
	 0, 0, NULL},   										//acc key,acc key modifier,output menuitem widget//~vXXER~
	{GNOME_APP_UI_ITEM, N_("Copy(_C)"),                            //~vXXER~
	 NULL,                                                         //~vXXEI~
	 popupmenu_activate_callback,(gpointer)2, NULL,                //~vXXER~
	 PIX_STOCK(GTK_STOCK_COPY),                                    //~vXXEI~
	 0, 0, NULL},                                                  //~vXXER~
	{GNOME_APP_UI_ITEM, N_("Clear(_L)"),                           //~vXXER~
	 NULL,                                                         //~vXXEI~
	 popupmenu_activate_callback,(gpointer)3, NULL,                //~vXXER~
	 PIX_STOCK(GTK_STOCK_COPY),                                    //~vXXEI~
	 0, 0, NULL},                                                  //~vXXER~
	{GNOME_APP_UI_ITEM, N_("Paste(_V)"),                           //~v66pI~
	 NULL,                                                         //~v66pI~
	 popupmenu_activate_callback,(gpointer)4, NULL,                //~v66pI~
	 PIX_STOCK(GTK_STOCK_PASTE),                                   //~v66pI~
	 0, 0, NULL},                                                  //~v66pI~
	{GNOME_APP_UI_ITEM, N_("PasteIns(_I)"),                        //~vXXER~
	 NULL,                                                         //~vXXEI~
	 popupmenu_activate_callback,(gpointer)5, NULL,                //~v66pR~
	 PIX_STOCK(GTK_STOCK_PASTE),                                   //~vXXEI~
	 0, 0, NULL},                                                  //~vXXER~
	{GNOME_APP_UI_ITEM, N_("PasteRep(_R)"),                        //~vXXER~
	 NULL,                                                         //~vXXEI~
	 popupmenu_activate_callback,(gpointer)6, NULL,                //~v66pR~
	 PIX_STOCK(GTK_STOCK_PASTE),                                   //~vXXEI~
	 0, 0, NULL},                                                  //~vXXER~
                                                                   //~vXXEI~
	GNOMEUIINFO_SEPARATOR,                                         //~vXXER~
                                                                   //~vXXEI~
	{GNOME_APP_UI_ITEM, N_("Save(_S)"),                            //~vXXER~
	 NULL,                                                         //~vXXEI~
	 popupmenu_activate_callback,(gpointer)7, NULL,                //~v66pR~
	 PIX_STOCK(GTK_STOCK_SAVE),                                    //~vXXEI~
	 0, 0, NULL},                                                  //~vXXER~
	{GNOME_APP_UI_ITEM, N_("End(_E)"),                             //~vXXER~
	 N_("Save,then Close"),                                        //~vXXEI~
	 popupmenu_activate_callback2,(gpointer)8,NULL,                //~v66pR~
	 PIX_STOCK(GTK_STOCK_CLOSE),                                   //~vXXEI~
	 0, 0, NULL},                                                  //~vXXER~
	{GNOME_APP_UI_ITEM, N_("Discard(_D)"),                         //~vXXER~
	 N_("Cancel,then Close"),                                      //~vXXEI~
	 popupmenu_activate_callback2,(gpointer)9, NULL,               //~v66pR~
	 PIX_STOCK(GTK_STOCK_CANCEL),                                  //~vXXEI~
	 0, 0, NULL},                                                  //~vXXER~
	{GNOME_APP_UI_ITEM, N_("OpenWidthA(_A)"),                      //~vXXER~
	 NULL,                                                         //~vXXEI~
	 popupmenu_activate_openwith,(gpointer)10, NULL,               //~v66pR~
	 PIX_STOCK(GTK_STOCK_OPEN),                                    //~vXXEI~
	 0, 0, NULL},                                                  //~vXXER~
	                                                               //~vXXER~
	GNOMEUIINFO_END                                                //~vXXEI~
};                                                                 //~vXXEI~
#endif //ARM                                                       //~v@@@I~
//	 GNOME_APP_PIXMAP_NONE, NULL,		for no icon                //~vXXEI~
///////////////////////////////////////////////////////////////////////////////~vXXEI~
//#define APPENDPOS   9                                            //~v51wR~
#define APPENDPOS   11                                             //~v51wI~
/////////////////////////////////////////////////////////////////////////////
// CMainFrame

/////////////////////////////////////////////////////////////////////////////

void gxemfrm_construct(void)                                       //~vXXER~
{
//******************************                                   //~vXXEI~
//  Mpxemain=0;	//for OnSize                                       //~vXXER~
	Mswgetinifail=                                                 //~vXXEI~
	ugetinidata(&Mwxeinidata);		//read ini data                //~2A19I~
#ifndef XXE                                                        //~vXXEI~
    Mshowmaxsw=Mwxeinidata.WXEINIshowmaxsw;	//show max at init instance of wxe.cpp//~vXXER~
//  Museact=Mwxeinidata.WXEINIuseact;	//accelerator use          //~vXXER~
//  Musemk =Mwxeinidata.WXEINIusemk ;	//accelerator use          //~vXXER~
    Mpact=0;        //accelerator table copy                       //~3104I~
#endif                                                             //~v66rM~
    Menablecut=-1;    //initialy dispaly menu bitmap               //~v66rR~
    Menablecopy=-1;                                                //~v66rR~
    Menablepaste=-1;                                               //~v66rR~
    Menablepastev=-1;                                              //~v66rI~
	popupmenu_init();		//read ini data                        //~vXXEI~
    changemenuaccelkey();                                          //~vXXER~
}

//***********************************************                  //~vXXEI~
//*accelerator key update                                          //~vXXEI~
//***********************************************                  //~vXXEI~
int  gxemfrm_Loadact(int Puseact)                                  //~vXXER~
{                                                                  //~3103I~
#ifndef XXE                                                        //~vXXEM~
	int delsw=0;                                                   //~3105R~
    CMenu *pmenu;                                                  //~3103I~
#endif                                                             //~vXXEI~
    static int Suseact=1;	//add entry by actinit                 //~vXXEI~
//*******************************                                  //~3103I~
//	if (Puseact!=-1)        //init req from wxe.cpp                //~vXXER~
    	if (Suseact==Puseact)   //not changed                      //~vXXER~
            return 0;                                              //~3103R~
        else                                                       //~3103I~
            Suseact=Puseact;                                       //~vXXER~
#ifndef XXE                                                        //~vXXEI~
	if (Museact)	//accelerator use                              //~3103I~
    {                                                              //~3103I~
    	if (m_hAccelTable)	//already loaded,mfc asset fail        //~3103R~
        	delsw=1;                                               //~3103I~
    }                                                              //~3103I~
    else                                                           //~3103I~
    	if (m_hAccelTable)	//already loaded,mfc asset fail        //~3103I~
        	delsw=1;                                               //~3103I~
    if (delsw)                                                     //~3103I~
    {                                                              //~3103I~
        DestroyAcceleratorTable(m_hAccelTable);                    //~3103R~
    	m_hAccelTable=0;                                           //~3103R~
    }                                                              //~3103I~
    pmenu=GetMenu();                                               //~3103M~
	updatemenu(0,pmenu);                                           //~3103R~
	if (Puseact==-1)        //init req from wxe.cpp                //~v51dI~
		appendmenu(pmenu);                                         //~v51dI~
    Invalidate(FALSE);                                             //~v51dI~
#endif                                                             //~vXXEI~
	updatemenu(Puseact);                                           //~vXXEI~
	return 1;                                                      //~3103R~
}                                                                  //~3103I~
//***********************************************                  //~vXXEI~
//*acc table init for menuitem with accelerator                    //~vXXEI~
//***********************************************                  //~vXXEI~
int  actinit(void)                                                 //~vXXEI~
{                                                                  //~vXXEI~
    GtkMenuItem *pmenuitem;                                        //~vXXEI~
	PACCMENUTBL pamt;                                              //~vXXEI~
    int ii;                                                        //~vXXEI~
//***************************                                      //~vXXEI~
    for (ii=0,pamt=Saccmenutbl;ii<ACCMENUTBLENTNO;ii++,pamt++)     //~vXXEI~
    {                                                              //~vXXEI~
	    pmenuitem=(GtkMenuItem*)lookup_widget(GTK_WIDGET(Gpframe),pamt->AMTstrid); //edit menuitem//~vXXEI~
        pamt->AMTpmenuitem=pmenuitem;                              //~vXXEI~
		gtk_menu_item_set_accel_path(pmenuitem,pamt->AMTpath);     //~vXXEI~
		gtk_accel_map_add_entry(pamt->AMTpath,pamt->AMTkey,pamt->AMTmodifier);//~vXXER~
        dprintf("act add menu=%p,path=%s,key=%c\n",pmenuitem,pamt->AMTpath,pamt->AMTkey);//~v669R~
	}                                                              //~vXXEI~
	return 0;                                                      //~vXXEI~
}//actinit                                                         //~vXXEI~
//***********************************************                  //~v51dI~
//* set enable/disable toolbar button                              //~vXXEI~
//***********************************************                  //~vXXEI~
int  gxemfrm_enabletoolbar(int Penterid)                           //~vXXER~
{                                                                  //~v51dI~
#ifndef ARM                                                        //~v@@@I~
    GtkWidget *pwd;                                                //~vXXEI~
    PCONTEXTMENUTBL pcmt;                                          //~vXXEI~
    int enablecopy,enablepaste,enablecut,ii,buttonid,flag;         //~vXXER~
    int enablepastev;                                              //~v66pI~
    int chngsw=0;                                                  //~v51dI~
    int flago=0;                                                   //~v66rR~
//***************************                                      //~v51dI~
    enablecopy =xxemain_cpupdatecopy();                            //~vXXER~
    enablecut  =xxemain_cpupdatecut();                             //~vXXER~
    enablepaste=xxemain_cpupdatepaste();                           //~vXXER~
    enablepastev=xxemain_cpupdatepastev();                         //~v66pI~
    for (ii=0,pcmt=Scontextmenutbl;ii<CONTEXTMENU_ITEMCTR;ii++,pcmt++)//~vXXER~
    {                                                              //~vXXEI~
		buttonid=pcmt->CMTid;                                      //~vXXER~
        pwd=pcmt->CMTptoolbarbutton;                               //~vXXER~
//      flag=1;    //enable if leave notify                        //~v66rR~
        flag=-1;   //enable if leave notify                        //~v66rI~
//      if (Penterid)   //enter notify                             //~v66rR~
            switch(buttonid)                                       //~vXXER~
            {                                                      //~vXXER~
            case ID_EDIT_CUT:                                      //~vXXER~
            case ID_EDIT_CLEAR:                                    //~vXXER~
                flago=Menablecut;                                  //~v66rI~
                flag=enablecut;                                    //~vXXER~
//printf("cut old=%d,new=%d\n",flago,flag);                        //~v66rR~
                break;                                             //~vXXER~
            case ID_EDIT_COPY:                                     //~vXXER~
                flago=Menablecopy;                                 //~v66rI~
                flag=enablecopy;                                   //~vXXER~
//printf("cpy old=%d,new=%d\n",flago,flag);                        //~v66rR~
                break;                                             //~vXXER~
            case ID_EDIT_PASTE:                                    //~vXXER~
            case ID_EDIT_PASTE_REP:                                //~vXXER~
                flago=Menablepaste;                                //~v66rI~
                flag=enablepaste;                                  //~vXXER~
//printf("paste old=%d,new=%d\n",flago,flag);                      //~v66rR~
                break;                                             //~vXXER~
            case ID_EDIT_PASTE_V:                                  //~v66pI~
                flago=Menablepastev;                               //~v66rI~
                flag=enablepastev;                                 //~v66pI~
//printf("pasteV old=%d,new=%d\n",flago,flag);                     //~v66rR~
                break;                                             //~v66pI~
            }                                                      //~vXXER~
      if (flag>=0 && flag!=flago)                                  //~v66rI~
      {                                                            //~v66rI~
        chngsw=1;                                                  //~v66rI~
		gtk_widget_set_sensitive(GTK_WIDGET(pwd),flag);            //~vXXEI~
//      dprintf("edit toolbar button=%s,enable=%d\n",pcmt->CMTtbstrid,flag);//~v669R~
      }                                                            //~v66rI~
    }                                                              //~vXXEI~
//printf("chngsw=%d\n",chngsw);                                    //~v66rR~
    if (chngsw)                                                    //~v66rR~
        gtk_widget_show(Mtoolbar);                                 //~v66rI~
    Menablecut=enablecut;                                          //~v66rI~
    Menablecopy=enablecopy;                                        //~v66rI~
    Menablepaste=enablepaste;                                      //~v66rI~
    Menablepastev=enablepastev;                                    //~v66rI~
#endif //ARM                                                       //~v@@@I~
    return 0;                                                      //~v51dI~
}//gxemfrm_enabletoolbar                                           //~v66rR~
//***********************************************                  //~3103I~
int  updatemenu(int Puseact)                                       //~vXXER~
{                                                                  //~3103I~
	int ii;                                                        //~vXXEI~
    PACCMENUTBL pamt;                                              //~vXXEI~
//**************************************                           //~vXXEI~
    for (ii=0,pamt=Saccmenutbl;ii<ACCMENUTBLENTNO;ii++,pamt++)     //~vXXEI~
    {                                                              //~vXXEI~
        if (Puseact)                                               //~vXXEI~
			gtk_accel_map_change_entry(pamt->AMTpath,pamt->AMTkey,pamt->AMTmodifier,0);//~vXXER~
        else                                                       //~vXXEI~
			gtk_accel_map_change_entry(pamt->AMTpath,0,0,0);       //~vXXEI~
	}                                                              //~vXXEI~
    return 0;                                                      //~3103I~
}//updatemenu                                                      //~3103I~
//***********************************************                  //~3104I~
//*update mein manue text                                          //~3104I~
//*return:1:updated                                                //~3104I~
//***********************************************                  //~3104I~
int  gxemfrm_updatemainmenu(int Pusemk)                            //~vXXER~
{                                                                  //~3104I~
#ifndef XXE                                                        //~vXXEI~
    CMenu *pmenu;                                                  //~3105I~
	int ii,cmdid,updatesw=0;                                       //~3104R~
    char menutext[64];                                             //~3104I~
#endif                                                             //~vXXEI~
static int Susemk=-1;                                              //~vXXER~
    int ii;                                                        //~vXXEI~
    PMAINMENUTBL pmt;                                              //~vXXER~
//***************************                                      //~3104I~
//	if (Pusemk!=-1)        //init req from wxe.cpp                 //~vXXER~
//  {                                                              //~vXXER~
    	if (Susemk==Pusemk)   //not changed                        //~vXXER~
            return 0;                                              //~3105I~
        else                                                       //~3105I~
            Susemk=Pusemk;                                         //~vXXER~
//  }                                                              //~vXXER~
    for (ii=0,pmt=Smainmenutbl;ii<MAINMENUTBLENTNO;ii++,pmt++)     //~vXXER~
		updatemainmenutext(pmt,Pusemk);                            //~vXXER~
    return 1;                                                      //~vXXEI~
}//gxemfrm_updatemainmenu                                          //~vXXER~
//***********************************************                  //~3104I~
//*ret 0:no update required                                        //~3104I~
//***********************************************                  //~3104I~
//nt  CMainFrame::updatemainmenutext(int Pmenupos,char *Ptext,int Pusemk)//~vXXER~
int  updatemainmenutext(PMAINMENUTBL Ppmt,int Pusemk)              //~vXXEI~
{                                                                  //~3104I~
    if (Pusemk) //set on                                           //~vXXEI~
    {                                                              //~vXXEI~
        gtk_label_set_text_with_mnemonic(Ppmt->MMTplabel,Ppmt->MMTlabelon);//~vXXER~
        dprintf("menu on %s\n",Ppmt->MMTlabelon);                  //~v669R~
    }                                                              //~vXXEI~
    else                                                           //~vXXEI~
    {                                                              //~vXXEI~
        gtk_label_set_text(Ppmt->MMTplabel,Ppmt->MMTlabeloff);     //~vXXEI~
        dprintf("menu off %s\n",Ppmt->MMTlabeloff);                //~v669R~
    }                                                              //~vXXEI~
    return 1;                                                      //~vXXEI~
}//updatemainmenutext                                              //~3104I~
#ifndef ARM                                                        //~v@@@I~
//***************************************************************************//~vXXEM~
//* mainmenu enter notify callback                                 //~vXXEM~
//***************************************************************************//~vXXEM~
void on_mainmenu_enter_notify_edit(GtkMenuItem     *Pmenuitem,     //~vXXEM~
                              GdkEventCrossing *Pevent,            //~vXXEM~
                              gpointer         Puser_data)         //~vXXEM~
{                                                                  //~vXXEM~
	int ii;                                                        //~vXXEM~
    PCONTEXTMENUTBL pcmt;                                          //~vXXEI~
//*********************                                            //~vXXEM~
    dprintf("mainmenu enter notify=%p\n",Pmenuitem);               //~v669R~
    for (ii=0,pcmt=Scontextmenutbl;ii<CONTEXTMENU_ITEMCTR;ii++,pcmt++)//~vXXER~
		(pcmt->CMTfunc)(pcmt->CMTpmenuitem);	//enable func call //~vXXER~
    return;                                                        //~vXXEM~
}                                                                  //~vXXEM~
#endif //ARM                                                       //~v@@@I~
//***************************************************************************//~vXXEM~
//* mainmenu init <--gxemfrm_realize                               //~vXXEM~
//***************************************************************************//~vXXEM~
void mainmenu_init(void)                                           //~vXXEM~
{                                                                  //~vXXEM~
#ifndef ARM                                                        //~v@@@I~
    GtkMenuItem *pmenuitem;                                        //~vXXEM~
    char *pci,*pco;                                                //~vXXER~
    int ii,jj;                                                     //~vXXEM~
    PMAINMENUTBL pmt;                                              //~vXXEM~
    PCONTEXTMENUTBL pcmt;                                          //~vXXEI~
//******************************                                   //~vXXEM~
//for mainmenu to usemk(menu key usage) change                     //~vXXEM~
    for (ii=0,pmt=Smainmenutbl;ii<MAINMENUTBLENTNO;ii++,pmt++)     //~vXXER~
    {                                                              //~vXXEM~
	    pmenuitem=(GtkMenuItem*)lookup_widget(GTK_WIDGET(Gpframe),pmt->MMTstrid); //edit menuitem//~vXXEM~
    	pmt->MMTpmenuitem=pmenuitem;                               //~vXXEM~
        pmt->MMTplabel=(GtkLabel*)gtk_bin_get_child(GTK_BIN(pmenuitem));	//label//~vXXEM~
//      ptext=(char*)gtk_label_get_label(pmt->MMTplabel);          //~vXXER~
//      strncpy(pmt->MMTlabelon,ptext,sizeof(pmt->MMTlabelon)-1);  //~vXXER~
        pci=pmt->MMTlabelon;                                       //~vXXEM~
        pco=pmt->MMTlabeloff;                                      //~vXXEM~
        for (jj=0;jj<strlen(pmt->MMTlabelon);jj++,pci++)           //~vXXEM~
            if (*pci!='_')                                         //~vXXEM~
                *pco++=*pci;                                       //~vXXEM~
        *pco=0;                                                    //~vXXEM~
        dprintf("menu label ii=%d,on=%s,off=%s\n",ii,pmt->MMTlabelon,pmt->MMTlabeloff);//~v669R~
    }                                                              //~vXXEM~
    gxemfrm_updatemainmenu(Mwxeinidata.WXEINIusemk) ;	//set menukey enable/disable//~vXXER~
	actinit();	//accelerater tbl init                             //~vXXER~
	gxemfrm_Loadact(Mwxeinidata.WXEINIuseact);                     //~vXXEI~
//for edit submenu to enable/disable                               //~vXXEM~
    pmenuitem=(GtkMenuItem*)lookup_widget(GTK_WIDGET (Gpframe),STRID_EDIT); //edit menuitem//~vXXEM~
	g_signal_connect ((gpointer)pmenuitem,"enter_notify_event",    //~vXXEM~
                    G_CALLBACK (on_mainmenu_enter_notify_edit),    //~vXXEM~
                    NULL);                                         //~vXXEM~
//get context menuitem widget addr                                 //~vXXER~
    for (ii=0,pcmt=Scontextmenutbl;ii<CONTEXTMENU_ITEMCTR;ii++,pcmt++)//~vXXER~
    {                                                              //~vXXEM~
		pcmt->CMTpmenuitem=(GtkMenuItem*)lookup_widget (GTK_WIDGET (Gpframe),pcmt->CMTstrid); //edit menuitem//~vXXER~
        dprintf("edit menu widget=%p,str=%s\n",pcmt->CMTpmenuitem,pcmt->CMTstrid);//~v669R~
    }                                                              //~vXXEM~
#endif //ARM                                                       //~v@@@I~
    return;                                                        //~vXXEM~
}//mainmenu_init                                                   //~vXXEM~
//***************************************************************************//~vXXEM~
//* toolbar init                                                   //~vXXEI~
//***************************************************************************//~vXXEM~
void toolbar_init(void)                                            //~vXXEM~
{                                                                  //~vXXEM~
#ifndef ARM                                                        //~v@@@I~
	PCONTEXTMENUTBL pcmt;                                          //~vXXER~
    int ii;                                                        //~vXXEM~
//******************************                                   //~vXXEM~
//get button widget addr                                           //~vXXEM~
    for (ii=0,pcmt=Scontextmenutbl;ii<CONTEXTMENU_ITEMCTR;ii++,pcmt++)//~vXXER~
    {                                                              //~vXXEM~
		pcmt->CMTptoolbarbutton=(GtkWidget*)lookup_widget(GTK_WIDGET(Gpframe),pcmt->CMTtbstrid); //edit menuitem//~vXXER~
        dprintf("edit toolbar widget=%p,str=%s\n",pcmt->CMTptoolbarbutton,pcmt->CMTtbstrid);//~v669R~
    }                                                              //~vXXEM~
#endif //ARM                                                       //~v@@@I~
    return;                                                        //~vXXEM~
}//toolbar_init                                                    //~vXXEM~
void gxemfrm_OnHelp(void)                                          //~v669R~
{
//    char fnm[_MAX_PATH],uri[_MAX_PATH+8],*phd,**pphd;              //~v669R~,//~v71AR~
//    GError *perr=0;                                                //~v669I~,//~v71AR~
//    int rc;                                                        //~v669I~,//~v71AR~
//    char *helpdir[]={"~/xehelp",DEF_HELPDIR,0};                    //~v669I~,//~v71AR~
//    FILEFINDBUF3 ffb3;                                             //~v669I~,//~v71AR~
//    char *html;                                                    //~v66BI~,//~v71AR~
//#ifndef XXE                                                      //~v71AR~
//    int ctxid;                                                   //~v71AR~
////******************                                             //~v71AR~
////  Mpapp->WinHelp(0,HELP_CONTENTS);                             //~v71AR~
////  Mpapp->WinHelp(0,HELP_FINDER);                               //~v71AR~
//    if (wxegetdbcsmode())                                        //~v71AR~
//        ctxid=IDH_JAPANESE;                                      //~v71AR~
//    else                                                         //~v71AR~
//        ctxid=IDH_ENGLISH;                                       //~v71AR~
//    WinHelp(ctxid,HELP_CONTEXT);                                 //~v71AR~
//#endif                                                           //~v71AR~
//	fnm="file:///d/mydoc/hpb/xehelp/index.htm";                    //~v669R~
//    if (wxegetdbcsmode())                                          //~v66BI~,//~v71AR~
//        html="/indexj.htm";                                        //~v66BI~,//~v71AR~
//    else                                                           //~v66BI~,//~v71AR~
//        html="/indexe.htm";                                        //~v66BI~,//~v71AR~
//    phd=Ghelpdir;                                                  //~v669I~ ,//~v71AR~
//    if (phd)                                                       //~v669I~,//~v71AR~
//    {                                                              //~v669I~,//~v71AR~
////      sprintf(fnm,"%s/index.htm",phd);                           //~v66BR~,//~v71AR~
//        sprintf(fnm,"%s%s",phd,html);                              //~v66BI~,//~v71AR~
//        if (ufstat(fnm,&ffb3))                                     //~v669I~,//~v71AR~
//        {                                                          //~v669I~,//~v71AR~
//            uerrmsg("%s is not found",0,                           //~v669R~,//~v71AR~
//                    fnm);                                          //~v669I~,//~v71AR~
//            phd=0;//errid                                          //~v669I~,//~v71AR~
//        }                                                          //~v669I~,//~v71AR~
//    }                                                              //~v669I~,//~v71AR~
//    else                                                           //~v669I~,//~v71AR~
//    {                                                              //~v669I~,//~v71AR~
//        for (pphd=helpdir;;pphd++)                                 //~v669I~,//~v71AR~
//        {                                                          //~v669I~,//~v71AR~
//            phd=*pphd;                                             //~v669I~,//~v71AR~
//            if (!phd)                                              //~v669I~,//~v71AR~
//                break;  //eol                                      //~v669I~,//~v71AR~
//            ufullpath(fnm,phd,_MAX_PATH);                          //~v669R~,//~v71AR~
////          strcat(fnm,"/index.htm");                              //~v66BR~,//~v71AR~
//            strcat(fnm,html);                                      //~v66BI~,//~v71AR~
//            if (!ufstat(fnm,&ffb3))                                //~v669I~,//~v71AR~
//                break;                                             //~v669I~,//~v71AR~
//        }                                                          //~v669I~,//~v71AR~
//        if (!phd)                                                  //~v669I~,//~v71AR~
////          uerrmsg("helpdir/index.htm not found on %s nor %s",0,  //~v66BR~,//~v71AR~
////                  helpdir[0],helpdir[1]);                        //~v66BI~,//~v71AR~
//            uerrmsg("helpdir%s not found on %s nor %s",0,          //~v66BI~,//~v71AR~
//                    html,helpdir[0],helpdir[1]);                   //~v66BI~,//~v71AR~
//    }                                                              //~v669I~,//~v71AR~
//    if (phd)                                                       //~v669R~,//~v71AR~
//    {                                                              //~v669I~,//~v71AR~
//        sprintf(uri,"file://%s",fnm);                              //~v669I~,//~v71AR~
//        rc=gnome_help_display_uri(uri,&perr);                      //~v669R~,//~v71AR~
//        PRINTGERR("gxemfrm_OnHelp-gnome_help_display_uri",perr);   //+v69rR~,//~v71AR~
//        if (!rc)    //false                                        //~v669R~,//~v71AR~
//            uerrmsg("%s exist?\n",uri);                            //~v669R~,//~v71AR~
//    }                                                              //~v669I~,//~v71AR~
//    xxe_scrdisp();//display errmsg                                 //~v669I~,//~v71AR~
	wxe_helpcall();                                                //~v71AI~
	xxemain_scrinvalidate(0);                                      //~v669I~
    return;                                                        //~v669I~
}//gxemain_OnHelp                                                  //~v669R~
#ifndef ARM                                                        //~v@@@I~
//***************************************************************************//~vXXEI~
//* search mainmenu menuitem tbl to get widget name                //~vXXEI~
//***************************************************************************//~vXXEI~
int mainmenu_srchmenuitem(GtkWidget *Ppmenuitem)                   //~vXXER~
{                                                                  //~vXXEI~
	int ii;                                                        //~vXXEI~
    PCONTEXTMENUTBL pcmt;                                          //~vXXEI~
//********************************                                 //~vXXEI~
	for (ii=0,pcmt=Scontextmenutbl;ii<CONTEXTMENU_ITEMCTR;ii++,pcmt++)//~vXXER~
		if (GTK_WIDGET(pcmt->CMTpmenuitem)==Ppmenuitem)            //~vXXER~
        	return ii;                                             //~vXXEI~
    return -1;                                                     //~vXXEI~
}//mainmenu_srchmenuitem                                           //~vXXEI~
#endif //ARM                                                       //~v@@@R~
//***************************************************************************//~vXXEI~
//* popupmenu init                                                 //~vXXEI~
//***************************************************************************//~vXXEI~
void popupmenu_init(void)                                          //~vXXER~
{                                                                  //~vXXEI~
#ifndef ARM                                                        //~v@@@I~
	GnomeUIInfo *puiinfo;                                          //~vXXER~
    int itemcount,ii,itemidx;                                      //~vXXEI~
//******************************                                   //~vXXEI~
	Gppopupmenu=gtk_menu_new();                                    //~vXXEI~
    g_signal_connect(Gppopupmenu,"deactivate",G_CALLBACK(popupmenu_deactivate_callback),NULL);//~vXXER~
#ifdef AAA                                                         //~vXXEI~
	append_menuitem_by_stockid(ID_EDIT_CUT,      GTK_STOCK_CUT);   //~vXXER~
	append_menuitem_by_stockid(ID_EDIT_CLEAR,    GTK_STOCK_CLEAR); //~vXXER~
	append_menuitem_by_stockid(ID_EDIT_COPY,     GTK_STOCK_COPY);  //~vXXER~
	append_menuitem_by_stockid(ID_EDIT_PASTE,    GTK_STOCK_PASTE); //~vXXER~
	append_menuitem_by_stockid(ID_EDIT_PASTE_REP,GTK_STOCK_PASTE); //~vXXEI~
	append_menuitem_by_stockid(ID_EDIT_CANCEL,   GTK_STOCK_CANCEL);//~vXXER~
	append_menuitem_by_stockid(ID_EDIT_END,      GTK_STOCK_PASTE); //~vXXER~
	append_menuitem_by_stockid(ID_FILE_SAVE,     GTK_STOCK_SAVE);  //~vXXER~
	append_menuitem_by_stockid(ID_FILE_OPENWITH, GTK_STOCK_OPEN);  //~vXXER~
#else                                                              //~vXXEI~
	gnome_app_fill_menu (GTK_MENU_SHELL(Gppopupmenu),Spopupmenuuiinfo,//~vXXEI~
			     NULL,TRUE,0);	//acc group,underline accelerator efective,menuitem insert pos//~vXXEI~
	itemcount=sizeof(Spopupmenuuiinfo)/sizeof(Spopupmenuuiinfo[0]);//~vXXEI~
	for (ii=0,puiinfo=Spopupmenuuiinfo;ii<itemcount;ii++,puiinfo++)//~vXXER~
    {                                                              //~vXXEI~
//  	itemidx=(int)(puiinfo->user_data)-1;                       //~vXXER~//~va72R~
    	itemidx=(int)(ULONG)(puiinfo->user_data)-1;	//gpointer     //~va72I~
        if (itemidx>=0)    //not seperator                         //~vXXEI~
        {                                                          //~vXXEI~
        	 dprintf("idx=%d,menuitem=%p\n",itemidx,puiinfo->widget);//~v669R~
			Spopupmenutbl[itemidx].PMTpmenuitem=(GtkMenuItem*)(puiinfo->widget);		//save menuitem widget ptr//~vXXER~
        }                                                          //~vXXEI~
    }                                                              //~vXXEI~
#endif                                                             //~vXXEI~
#endif //ARM                                                       //~v@@@I~
    return;                                                        //~vXXEI~
}//popupmenu_init                                                  //~vXXER~
#ifdef AAA                                                         //~vXXEI~
//***************************************************************************//~vXXEI~
//* popupmenu menuitem append by stock id                          //~vXXEI~
//***************************************************************************//~vXXEI~
int append_menuitem_by_stockid(int Pitemid,char *Pstockid)         //~vXXEI~
{                                                                  //~vXXEI~
	GtkWidget *menuitem;                                           //~vXXEI~
    int itemtbidx;                                                 //~vXXEI~
//******************************                                   //~vXXEI~
	itemtbidx=popupmenu_srchid(Pitemid);                           //~vXXEI~
    if (itemtbidx<0)                                               //~vXXER~
    	return -1;                                                 //~vXXEI~
	 dprintf("apend stoc menuitemid=%d,stockid=%s\n",Pitemid,Pstockid);//~v669R~
//	menuitem=gtk_image_menu_item_new_from_stock(Pstockid,NULL);//NULL:GtkAccelGroup//~vXXER~
  	menuitem=gtk_image_menu_item_new_with_mnemonic("_File");       //~vXXER~
  	g_signal_connect(menuitem,"activate",G_CALLBACK(popupmenu_activate_callback),(gpointer)Pitemid);//~vXXER~
	 dprintf("activate signal connect\n");                          //~v669R~
	gtk_widget_show (menuitem);                                    //~vXXEI~
  	gtk_menu_shell_append (GTK_MENU_SHELL(Gppopupmenu), menuitem); //~vXXER~
    Spopupmenuitemtbl[itemtbidx]=menuitem;                         //~vXXER~
    return itemtbidx;                                              //~vXXER~
}                                                                  //~vXXEI~
#endif                                                             //~vXXEI~
//***************************************************************************//~vXXEI~
//* popupmenu activate callback                                    //~vXXEI~
//***************************************************************************//~vXXEI~
void popupmenu_deactivate_callback(GtkWidget  *Ppmenuitem,gpointer Puparm)//~vXXEI~
{                                                                  //~vXXEI~
//******************************                                   //~vXXEI~
	Mfloatmenusw=0;                                                //~vXXEI~
	 dprintf("popupmenu deactivate cb\n"); 	//file print           //~v669R~
    return;                                                        //~vXXEI~
}                                                                  //~vXXEI~
//***************************************************************************//~vXXEI~
//* popupmenu activate callback,emit by menu                       //~vXXER~
//***************************************************************************//~vXXEI~
void popupmenu_activate_callback(GtkWidget  *Ppmenuitem,gpointer Puparm)//~vXXER~
{                                                                  //~vXXEI~
    int itemtbidx;                                                 //~vXXEI~
    char *strid;                                                   //~vXXEI~
//******************************                                   //~vXXEI~
//	 dprintf("popupmenu activate cb menuitemid=%d\n",(int)Puparm);  //~v669R~//~va72R~
  	 dprintf("popupmenu activate cb menuitemid=%p\n",Puparm);      //~va72I~
//  itemtbidx=popupmenu_srchid((int)Puparm);                       //~vXXER~
//  if (itemtbidx<0)                                               //~vXXER~
//  	return;                                                    //~vXXER~
//  itemtbidx=(int)Puparm-1;                                       //~vXXER~//~va72R~
    itemtbidx=(int)(ULONG)Puparm-1;                                //~va72I~
    if (itemtbidx>=0)                                              //~vXXER~
    {                                                              //~vXXEI~
		strid=Spopupmenutbl[itemtbidx].PMTstrid;                   //~vXXER~
		uactivate_menuitem(strid); 	//file print                   //~vXXER~
		 dprintf("popupmenu activate cb menuitem=%s\n",strid); 	//file print//~v669R~
    }                                                              //~vXXEI~
    return;                                                        //~vXXEI~
}                                                                  //~vXXEI~
//***************************************************************************//~vXXEI~
//* popupmenu activate callback,emit by toolbar button             //~vXXER~
//***************************************************************************//~vXXEI~
void popupmenu_activate_callback2(GtkWidget  *Ppmenuitem,gpointer Puparm)//~vXXEI~
{                                                                  //~vXXEI~
    int itemtbidx;                                                 //~vXXEI~
    char *strid;                                                   //~vXXEI~
//******************************                                   //~vXXEI~
//	 dprintf("popupmenu activate cb2 menuitemid=%d\n",(int)Puparm); //~v669R~//~va72R~
	 dprintf("popupmenu activate cb2 menuitemid=%p\n",Puparm);     //~va72I~
//  itemtbidx=(int)Puparm-1;                                       //~vXXER~//~va72R~
    itemtbidx=(int)(ULONG)Puparm-1;                                //~va72I~
    if (itemtbidx>=0)                                              //~vXXER~
    {                                                              //~vXXEI~
		strid=Spopupmenutbl[itemtbidx].PMTstrid;                   //~vXXEI~
		uclick_toolbarbutton(strid); 	//file print               //~vXXER~
		 dprintf("popupmenu activate cb2 menuitem=%s\n",strid); 	//file print//~v669R~
    }                                                              //~vXXEI~
    return;                                                        //~vXXEI~
}                                                                  //~vXXEI~
//***************************************************************************//~vXXEI~
//* popupmenu activate callback                                    //~vXXEI~
//***************************************************************************//~vXXEI~
void popupmenu_activate_openwith(GtkWidget  *Ppmenuitem,gpointer Puparm)//~vXXEI~
{                                                                  //~vXXEI~
//******************************                                   //~vXXEI~
//	 dprintf("popupmenu activate openwith itemid=%d\n",(int)Puparm);//~v669R~//~va72R~
	 dprintf("popupmenu activate openwith itemid=%p\n",Puparm);    //~va72I~
    gxeview_OnFileOpenwith();                                      //~vXXER~
    return;                                                        //~vXXEI~
}                                                                  //~vXXEI~
//***************************************************************************//~vXXEI~
//* search popupmenu item id tbl                                   //~vXXEI~
//***************************************************************************//~vXXEI~
//int popupmenu_srchid(Pitemid)                                      //~vXXEI~//+vb91R~
int popupmenu_srchid(int Pitemid)                                  //~vb74I~//+vb91I~
{                                                                  //~vXXEI~
	int ii;                                                        //~vXXEI~
//********************************                                 //~vXXEI~
	for (ii=0;ii<POPUPMENU_ITEMCTR;ii++)                           //~vXXEI~
		if (Spopupmenutbl[ii].PMTid==Pitemid)                      //~vXXER~
        	return ii;                                             //~vXXEI~
    return -1;                                                     //~vXXEI~
}                                                                  //~vXXEI~
//***************************************************************************//~vXXEI~
//* get itemid fromm tbl index                                     //~vXXER~
//***************************************************************************//~vXXEI~
GtkWidget *gxemfrm_popupmenu_getmenuitem(int Pitemid)              //~vXXER~
{                                                                  //~vXXEI~
	int itemtbidx;                                                 //~vXXER~
//**************************                                       //~vXXEI~
	itemtbidx=popupmenu_srchid(Pitemid);                           //~vXXER~
    if (itemtbidx<0)                                               //~vXXER~
    	return 0;                                                  //~vXXEI~
	return (GtkWidget*)(Spopupmenutbl[itemtbidx].PMTpmenuitem);    //~vXXER~
}                                                                  //~vXXEI~
//***************************************************************************//~vXXEI~
//* search popupmenu item                                          //~vXXEI~
//***************************************************************************//~vXXEI~
int gxemfrm_popupmenu_getmenuitemid(int Pindex)                    //~vXXER~
{                                                                  //~vXXEI~
	if (Pindex>=0 && Pindex<POPUPMENU_ITEMCTR)                     //~vXXER~
    	return Spopupmenutbl[Pindex].PMTid;                        //~vXXER~
    return 0;                                                      //~vXXER~
}                                                                  //~vXXEI~
//***************************************************************************//~vXXEI~
//***************************************************************************//~vXXEI~
//***************************************************************************//~vXXEI~
//* realize *****************************************************************//~vXXEI~
//***************************************************************************//~vXXEI~
void gxemfrm_realize                    (GtkWidget       *Ppwidget)//~vXXER~
{                                                                  //~vXXEI~
	dprintf("main frame realize\n");                               //~v@@@R~
	Gpframe=Ppwidget;                                              //~vXXER~
    Gpview=lookup_widget (GTK_WIDGET (Ppwidget), "maindraw"); //   //~vXXER~
    Mtoolbar=lookup_widget (GTK_WIDGET (Ppwidget),STRTBID_TOOLBAR); ////~v66rI~
    gxemfrm_setwindowsize();                                       //~vXXEI~
    udragdestset();                                                //~vXXER~
    mainmenu_init();                                               //~vXXEI~
	toolbar_init();		//read ini data                            //~vXXEI~
    return;                                                        //~vXXEI~
}                                                                  //~vXXEI~
//***************************************************************************//~vXXEI~
//* unrealize ***************************************************************//~vXXEI~
//***************************************************************************//~vXXEI~
void gxemfrm_unrealize                  (GtkWidget       *widget)  //~vXXEI~
{                                                                  //~vXXEI~
	 dprintf("mainframe  unrealize\n");                             //~v669R~
    return;                                                        //~vXXEI~
}                                                                  //~vXXEI~
//***************************************************************************//~vXXEI~
//* configure ***************************************************************//~vXXEI~
//***************************************************************************//~vXXEI~
void gxemfrm_configure            (GtkWidget       *Ppwidget,      //~vXXER~
                                        GdkEventConfigure *event)  //~vXXEI~
{                                                                  //~vXXEI~
//*********************************                                //~vXXEI~
//  dprintf("mainframe configure\n");                              //~v669R~
    return;                                                        //~vXXEI~
}                                                                  //~vXXEI~
//***************************************************************************//~vXXEI~
//* delete    ***************************************************************//~vXXEI~
//***************************************************************************//~vXXEI~
void gxemfrm_delete               (GtkWidget       *Ppwidget,      //~vXXEI~
                                        GdkEvent        *event)    //~vXXEI~
{                                                                  //~vXXEI~
//*********************************                                //~vXXEI~
//  dprintf("mainframe delete   \n");                              //~v66DR~
	printf("Update discarded.\n");                                 //~v66DR~
    exit(12);                                                      //~v66DI~
    return;                                                        //~vXXEI~
}                                                                  //~vXXEI~
//***************************************************************************//~vXXEI~
//* destroy   ***************************************************************//~vXXEI~
//***************************************************************************//~vXXEI~
void gxemfrm_destroy              (GtkWidget       *Ppwidget,      //~vXXEI~
                                        GdkEvent        *event)    //~vXXEI~
{                                                                  //~vXXEI~
//*********************************                                //~vXXEI~
	 dprintf("mainframe destroy  \n"); //destroy unreachable?       //~v669R~
    return;                                                        //~vXXEI~
}                                                                  //~vXXEI~
//***************************************************************************//~vXXEI~
//* set initial window size *************************************************//~vXXEI~
//***************************************************************************//~vXXEI~
void gxemfrm_setwindowsize(void)                                   //~vXXEI~
{                                                                  //~vXXEM~
#ifndef ARM                                                        //~v@@@I~
	int ww,hh;                                                     //~vXXEI~
//**********************************                               //~vXXEM~
	if (Mswgetinifail)                                             //~vXXEI~
    {                                                              //~vXXEI~
        ww=DEF_FRAMEWIDTH;                                         //~vXXEI~
        hh=DEF_FRAMEHEIGHT;                                        //~vXXEI~
    }                                                              //~vXXEI~
    else                                                           //~vXXEI~
    {                                                              //~vXXEI~
        ww=Mwxeinidata.WXEINIframewidth;    //restore size         //~vXXER~
        hh=Mwxeinidata.WXEINIframeheight;   //restore size         //~vXXER~
    }                                                              //~vXXEI~
//	gtk_widget_set_size_request(Gpframe,ww,hh);      //cannot shrink//~vXXER~
  	gtk_window_set_default_size(GTK_WINDOW(Gpframe),ww,hh);  //shrink avail//~vXXER~
	 dprintf("precreate window after w=%d,h=%d\n",ww,hh);           //~v669R~
#endif //ARM                                                       //~v@@@I~
	return;                                                        //~vXXEI~
}                                                                  //~vXXEM~
//***************************************************************************//~vXXEI~
//* on close write ini      *************************************************//~vXXEI~
//***************************************************************************//~vXXEI~
void gxemfrm_onclose(void)                                         //~vXXEI~
{                                                                  //~vXXEM~
//  WINDOWPLACEMENT wp;                                            //~vXXEI~
//*******************************                                  //~vXXEM~
//  if (Mpxemain)                                                  //~vXXEI~
//  {                                                              //~vXXEI~
        xxemain_iniput(&Mwxeinidata);//write scr size              //~vXXER~
//      GetWindowPlacement(&wp);                                   //~vXXER~
        Mwxeinidata.WXEINIframewidth=Gpframe->allocation.width;    //~vXXER~
        Mwxeinidata.WXEINIframeheight=Gpframe->allocation.height;  //~vXXER~
//      Mwxeinidata.WXEINIshowmaxsw=IsZoomed();                    //~vXXER~
dprintf("close window w=%d,h=%d\n",Mwxeinidata.WXEINIframewidth,Mwxeinidata.WXEINIframeheight);//~v669R~
        uputinidata(&Mwxeinidata);                                 //~vXXEM~
        wxe_xecall(WXE_REQ_TERM,0);	//write xe.ini                 //~vXXER~
//  }                                                              //~vXXEI~
//  Mpapp->WinHelp(0,HELP_QUIT);                                   //~vXXEI~
    dprintf("mfrm onclose return\n");                              //~v669R~
    return;                                                        //~vXXEI~
}	                                                               //~vXXEI~
//***************************************************************************//~vXXEI~
//* release f10 for xe;and use Shift+Control+F10 as menu acceskey  //~vXXEI~
//***************************************************************************//~vXXEI~
void changemenuaccelkey(void)                                      //~vXXER~
{                                                                  //~vXXEI~
#ifndef ARM                                                        //~v@@@I~
	char *orgvalue;;                                               //~vXXEI~
//*******************************                                  //~vXXEI~
    g_object_get(G_OBJECT(gtk_settings_get_default()),             //~vXXEI~
                        "gtk-menu-bar-accel",                      //~vXXEI~
                        &orgvalue,                                 //~vXXEI~
                        NULL);                                     //~vXXEI~
    dprintf("org menu_bar_accel=%s\n",orgvalue);                   //~v669R~
    gtk_settings_set_string_property(                              //~vXXEI~
                          gtk_settings_get_default(),              //~vXXEI~
                        "gtk-menu-bar-accel",                      //~vXXEI~
                        "<Shift><Control>F10","gxe-gxe");          //~vXXEI~
    g_object_get(G_OBJECT(gtk_settings_get_default()),             //~vXXEI~
                        "gtk-menu-bar-accel",                      //~vXXEI~
                        &orgvalue,                                 //~vXXEI~
                        NULL);                                     //~vXXEI~
    dprintf("get changed  menu_bar_accel=%s\n",orgvalue);          //~v669R~
#endif //ARM                                                       //~v@@@I~
    return;                                                        //~vXXEI~
}                                                                  //~vXXEI~
#ifdef ARM                                                         //~v@@@I~
//**************************************************************   //~v@@@I~
//*from csub2                                                      //~v@@@I~
//**************************************************************   //~v@@@I~
int gxemfrm_upopupmenu_menuitem_enable(int Pitemid,int Penable)    //~v@@@I~
{                                                                  //~v@@@I~
	int itemtbidx;                                                 //~v@@@I~
//**************************                                       //~v@@@I~
	itemtbidx=popupmenu_srchid(Pitemid);                           //~v@@@I~
    if (itemtbidx<0)                                               //~v@@@I~
    	return 0;                                                  //~v@@@I~
	jnig_upopupmenu_menuitem_enable(POPUPMENU_ITEMCTR,itemtbidx,Penable);//~v@@@R~
    return 1;                                                      //~v@@@I~
}                                                                  //~v@@@I~
#endif                                                             //~v@@@I~
