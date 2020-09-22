//CID://+vc17R~:        update#=     4                             //~vc17R~
//******************************************************************************//~v003I~
//vc17 2020/06/15 malloc/free requires malloc.h                    //~vc17I~
//vax7:140627 (for jni)vaf6:120604 (XXE) fildialog(gtkfileselection) display previous selection directory//~vax7I~
//v@@@:110610 Android(-DARM)                                       //~v@@@I~
//v76j:070626 (WXEXXE)dnd by paste to utility panel                //~v76jI~
//v76g:070620 utility panel(3.14:grep and 3.12:compare);f5:open dirlist, dlcmd "^":send//~v76gI~
//v68i:060331 (WXEXXE)activate window when dropped                 //~v68iI~
//v686:060323 dnd support by paste(edit:pastev,ins=paste-ins,rep=paste-rep)//~v686I~
//v685:060322 dnd support "file copy into the dir slected" and even in inter-split-screen.//~v685I~
//v63k:050509 (WXE)change mouse csr when dragout in progress       //~v63kI~
//v63h:050504 (WXE)support DragOut                                 //~v63gI~
//v63g:050504 (WXE)DrgFinish required to avoid memory leak         //~v63gI~
//******************************************************************************//~v003I~
//******************************************************************************//~v003I~
//******************************************************************************//~v003I~
//******************************************************************************//~v003I~
#ifndef ARM                                                        //~v@@@I~
#include <gnome.h>                                                 //~vXXEI~
#include <libgnomeprint/gnome-print.h>                             //~vXXEI~
#include <libgnomeprint/gnome-print-job.h>                         //~vXXEI~
                                                                   //~vXXEI~
#include "callbacks.h"                                             //~vXXEI~
#include "interface.h"                                             //~vXXEI~
#include "support.h"                                               //~vXXEI~
#else                                                              //~v@@@I~
	#include <malloc.h>                                            //+vc17I~
	#include <jnig.h>                                              //~v@@@I~
#endif //ARM                                                       //~v@@@I~
                                                                   //~vXXEI~
#include <ulib.h>                                                  //~vXXEI~
#include <uedit.h>                                                 //~vXXEI~
#include <ustring.h>                                               //~vax7I~
#include <utrace.h>                                                //~v@@@I~
                                                                   //~vXXER~
#include    "xxedef.h"                                             //~vXXEI~
#define     GBL_XXEFILE                                            //~vax7I~
#include    "xxefile.h"                                            //~vXXEI~
#include    "xxexei.h"                                             //~vXXEI~
#include    "xxemain.h"                                            //~vXXEI~
#include    "xxecsub.h"                                            //~vXXEI~
#include    "gxedlg.h"                                             //~vXXEI~
#include    "xxeres.h"                                             //~vXXEI~
#include    "gxe.h"                                                //~vXXEI~
                                                                   //~vXXEI~
                                                                   //~v63gI~
//*******************************************************************//~vXXEI~
#define FILEDLG_OPEN    1                                          //~vXXEI~
#define FILEDLG_NEW     2                                          //~vXXEI~
#define FILEDLG_SAVEAS  3                                          //~vXXEI~
//*******************************************************************//~vXXEI~
static    GtkWidget *Sfiledlg;                                     //~vXXEI~
static    int       Sdlgmode;	//open.new,saveas                  //~vXXEI~
//*******************************************************************//~vXXEI~
int  xxefile_filesave(char *Pfilename);                            //~vXXER~
int  xxefile_fileopen(char *Pfilename);                            //~vXXER~
int  xxefile_filenew(char *Pfnm);                                  //~vXXER~
void invalidate(void);                                             //~v685I~
void dlgcreate(GtkMenuItem *Pmenuitem,int Poptnew);                //~vXXER~
//*******************************************************************//~vXXEM~
//*wxedoc ***********************************************************//~vXXEM~
//*******************************************************************//~vXXEM~
void gxedoc_construct(void)                                        //~vXXEM~
{                                                                  //~vXXEM~
//    Mcmdid=0;                                                    //~vXXEM~
//    Mpview=0;                                                    //~vXXEM~
//    Mpapp=(CWxeApp*)AfxGetApp();                                 //~vXXEM~
//    Mwxefile.init(this);                                         //~vXXEM~
}                                                                  //~vXXEM~
void gxedoc_init(void)                                             //~vXXEM~
{                                                                  //~vXXEM~
//    Mpview=Ppview;                                               //~vXXEM~
}                                                                  //~vXXEM~
                                                                   //~vXXEM~
void gxedoc_destroy(void)                                          //~vXXEM~
{                                                                  //~vXXEM~
}                                                                  //~vXXEM~
void gxedoc_Serialize(void)                                        //~vXXEM~
{                                                                  //~vXXEM~
}                                                                  //~vXXEM~
#ifndef ARM                                                        //~v@@@I~
void gxedoc_OnNewDocument 	           (GtkMenuItem     *menuitem, //~vXXEM~
                                        gpointer         user_data)//~vXXEM~
{                                                                  //~vXXEM~
	 dprintf("on_new_activate  \n");                                //~vXXER~
	dlgcreate(menuitem,FILEDLG_NEW);	//1:new                    //~vXXER~
    return;                                                        //~vXXEM~
}                                                                  //~vXXEM~
void gxedoc_OnOpenDocument             (GtkMenuItem     *menuitem, //~vXXER~
                                        gpointer         user_data)//~vXXEI~
{                                                                  //~vXXEM~
//*************************                                        //~vXXEM~
	 dprintf("on_open_activate  \n");                               //~vXXER~
    if (!menuitem)                                                 //~vXXEI~
    {                                                              //~vXXEI~
		uactivate_menuitem(STRID_FILE_OPEN);	//to get menuitem  //~vXXEI~
    	return;                                                    //~vXXEI~
    }                                                              //~vXXEI~
	dlgcreate(menuitem,FILEDLG_OPEN);                              //~vXXER~
    return;                                                        //~vXXEI~
}                                                                  //~vXXEM~
                                                                   //~vXXEM~
void gxedoc_OnSaveDocument(void)                                   //~vXXEM~
{                                                                  //~vXXEM~
	 dprintf("on_save_activate  \n");                               //~vXXER~
//    UTRACEP("Mcmdid %0x\n",Mcmdid);                              //~vXXEM~
//    if (Mcmdid==ID_FILE_SAVE_AS)                                 //~vXXEM~
//        Mwxefile.filesave(lpszPathName);                         //~vXXEM~
//    else                                                         //~vXXEM~
          xxefile_filesave("");      //no filename                 //~vXXER~
////  return CDocument::OnSaveDocument(lpszPathName);              //~vXXEM~
      return;                                                      //~vXXER~
}                                                                  //~vXXEM~
void gxedoc_OnSaveAsDocument            (GtkMenuItem     *menuitem,//~vXXEM~
                                        gpointer         user_data)//~vXXEM~
{                                                                  //~vXXEM~
	 dprintf("on_saveas_ctivate  \n");                              //~vXXER~
	dlgcreate(menuitem,FILEDLG_SAVEAS);                            //~vXXER~
    return;                                                        //~vXXEM~
}                                                                  //~vXXEM~
#endif //ARM                                                       //~v@@@I~
int gxedoc_OnCmdMsg(void)                                          //~vXXEM~
{                                                                  //~vXXEM~
//    UTRACEP("OnCmdMsg nID=%x,nCode=%d\n",nID,nCode);             //~vXXEM~
//    Mcmdid=nID;     //for distinguish save and save as           //~vXXEM~
//    int rc=CDocument::OnCmdMsg(nID, nCode, pExtra, pHandlerInfo);//~vXXEM~
////  int rc2=CommDlgExtendedError();                              //~vXXEM~
//    return rc;                                                   //~vXXEM~
    return 0;                                                      //~vXXEM~
}                                                                  //~vXXEM~
int  gxedoc_ondropfiles(void)                                      //~vXXEM~
{                                                                  //~vXXEM~
//    return                                                       //~vXXEM~
//    Mwxefile.ondropfiles(hDropInfo);                             //~vXXEM~
////  return;                                                      //~vXXEM~
    return 0;                                                      //~vXXEM~
}                                                                  //~vXXEM~
void xxefile_dlgOnOK      				(void)                     //~vXXER~
{                                                                  //~vXXEM~
    const gchar *pfnm;                                             //~vXXER~
    char *pc;                                                      //~vax7I~
//****************************                                     //~vXXEM~
//  dprintf("gxedlg File onok mainlevel=%d\n",gtk_main_level());   //~vXXER~
    pfnm=gtk_file_selection_get_filename(GTK_FILE_SELECTION(Sfiledlg));//~vXXER~
    gtk_widget_destroy(Sfiledlg);                                  //~vXXEM~
    if (!pfnm || !*pfnm)                                           //~vax7I~
    	return;                                                    //~vax7I~
    if (!Gfileselectiondir)                                        //~vax7I~
    {                                                              //~vax7I~
    	pc=(char*)malloc(_MAX_PATH);                               //~vax7I~
        if (!pc)                                                   //~vax7I~
        	return;                                                //~vax7I~
    	Gfileselectiondir=pc;   //interface.c set filename to fileselection//~vax7I~
    }                                                              //~vax7I~
    pc=Gfileselectiondir;                                          //~vax7I~
    UstrncpyZ(pc,pfnm,_MAX_PATH);                                  //~vax7I~
    switch(Sdlgmode)                                               //~vXXEI~
    {                                                              //~vXXEI~
    case FILEDLG_OPEN:                                             //~vXXEI~
        xxefile_fileopen((char*)pfnm);                             //~vXXER~
        break;                                                     //~vXXEI~
    case FILEDLG_NEW:                                              //~vXXEI~
        xxefile_filenew((char*)pfnm);                              //~vXXER~
        break;                                                     //~vXXEI~
    case FILEDLG_SAVEAS:                                           //~vXXEI~
        xxefile_filesave((char*)pfnm);                             //~vXXER~
        break;                                                     //~vXXEI~
    }                                                              //~vXXEI~
	return;                                                        //~vXXEM~
}                                                                  //~vXXEM~
                                                                   //~vXXEM~
void xxefile_dlgOnCancel  				(void)                     //~vXXER~
{                                                                  //~vXXEM~
//****************************                                     //~vXXEM~
	 dprintf("gxedlg File cancel\n");                               //~vXXER~
    gtk_widget_destroy(Sfiledlg);                                  //~vXXEM~
	return;                                                        //~vXXEM~
}                                                                  //~vXXEM~
//===============================================================================//~v003I~
//filenew is always called at start by setting at wxe.app on commmandlineinfo//~v003R~
//===============================================================================//~v003I~
int  xxefile_filenew(char *Pfnm)                                   //~vXXER~
{                                                                  //~v003I~
//*********************                                            //~v003I~
    wxe_editcmd("E",Pfnm);                                         //~v003R~
    xxemain_scrinvalidate(0);                                      //~vXXER~
    return 0;                                                      //~v003R~
}//filenew                                                         //~vXXER~
//===============================================================================//~v003I~
//Open                                                             //~v003R~
//===============================================================================//~v003I~
int xxefile_fileopen(char *Pfilename)                              //~vXXER~
{                                                                  //~v003I~
     wxe_editcmd("E",(char *)Pfilename);                           //~v003R~
	 xxemain_scrinvalidate(0);                                     //~vXXER~
     return 0;                                                     //~v003I~
}//fileopen                                                        //~v003R~
//===============================================================================//~v003I~
//Open                                                             //~v003I~
//===============================================================================//~v003I~
int xxefile_filesave(char *Pfilename)                              //~vXXER~
{                                                                  //~v003I~
//*********************                                            //~v003I~
     wxe_editcmd("S",(char *)Pfilename);                           //~v003R~
	 xxemain_scrinvalidate(0);                                     //~vXXEI~
     return 0;                                                     //~v003I~
}//filesave                                                        //~v003I~
//===============================================================================//~v003I~
//                                                                 //~v003I~
//===============================================================================//~v003I~
void invalidate(void)                                              //~v685R~
{                                                                  //~v003I~
//************************                                         //~v003I~
    xxemain_scrinvalidate(0);                                      //~v685I~
    return;                                                        //~v003I~
}//invalidate                                                      //~v003I~
//===============================================================================//~v63gI~
//open dropped file                                                //~v63gR~
//===============================================================================//~v63gI~
void xxefile_ondropfiles(GdkDragContext  *Pcontext,                //~vXXEI~
                         GtkSelectionData *Pseldata,               //~vXXEI~
                         int Px,int Py,                            //~v686R~
                         UINT Ptime)                               //~vXXEI~
{                                                                  //~v003I~
    BOOL rc;                                                       //~v685R~
    int row,col;                                                   //~v686R~
    int viewposx,viewposy,vieww,viewh,viewd;                       //~v685I~
//***************************************                          //~v003I~
    dprintf("%s:ondrop: pos=(%d,%d) protocol=%d,is_source=%d,actions=%d,suggested_action=%d,action=%d\n",//~v685R~
    	(char*)utimeedit("hh:mm:ss.mil",0),                        //~vXXEI~
    	Px,Py,                                                     //~v685M~
  		Pcontext->protocol,                                        //~vXXEI~
  		Pcontext->is_source,                                       //~vXXEI~
		  /*GList *targets;  */                                    //~vXXEI~
		Pcontext->actions,                                         //~vXXEI~
		Pcontext->suggested_action,                                //~vXXEI~
		Pcontext->action);                                         //~vXXEI~
                                                                   //~v685I~
//    dprintf("ondrop:targetwin=%p,mainwindow=%p,frame=%p\n",      //~v685R~
//        Pcontext->dest_window,Gpview->window,Gpframe->window);   //~v685R~
//    gdk_window_get_position(Pcontext->dest_window,&col,&row);    //~v685R~
//    dprintf("ondrop:get posistion=(y,x)=(%d,%d)\n",row,col);     //~v685R~
//    row=ROW(row);                                                //~v685R~
//    col=COL(col);                                                //~v685R~
//    dprintf("ondrop: get posistion=(row,col)=(%d,%d)\n",row,col);//~v685R~
//    gdk_window_get_origin(Pcontext->dest_window,&col,&row);      //~v685R~
//    dprintf("ondrop: get origin=(y  ,x  )=(%d,%d)\n",row,col);   //~v685R~
//    row=ROW(row);                                                //~v685R~
//    col=COL(col);                                                //~v685R~
//    dprintf("ondrop: get origin=(row,col)=(%d,%d)\n",row,col);   //~v685R~
//    gdk_window_get_origin(Gpview->window,&col,&row);             //~v685R~
//    dprintf("ondrop: get origin of view =(y  ,x  )=(%d,%d)\n",row,col);//~v685R~
//    row=ROW(row);                                                //~v685R~
//    col=COL(col);                                                //~v685R~
//    dprintf("ondrop: get origin of view =(row,col)=(%d,%d)\n",row,col);//~v685R~
//    int buttonmask;                                              //~v685R~
//    gdk_window_get_pointer(Pcontext->dest_window,&col,&row,&buttonmask);//~v685R~
//    dprintf("ondrop: get pointer=(y  ,x  )=(%d,%d)\n",row,col);  //~v685R~
//    row=ROW(row);                                                //~v685R~
//    col=COL(col);                                                //~v685R~
//    dprintf("ondrop: get pointer=(row,col)=(%d,%d)\n",row,col);  //~v685R~
//    int gx,gy,gw,gh,gd;                                          //~v685R~
//    gdk_window_get_geometry(Pcontext->dest_window,&gx,&gy,&gw,&gh,&gd);//~v685R~
//    dprintf("ondrop: dest geometry (x,y)=(%d,%d),(w,h)=(%d,%d),depth=%d\n",gx,gy,gw,gh,gd);//~v685R~
//    gdk_window_get_geometry(Gpview->window,&gx,&gy,&gw,&gh,&gd); //~v685R~
//    dprintf("ondrop: view geometry (x,y)=(%d,%d),(w,h)=(%d,%d),depth=%d\n",gx,gy,gw,gh,gd);//~v685R~
//    gdk_window_get_geometry(Gpframe->window,&gx,&gy,&gw,&gh,&gd);//~v685R~
//    dprintf("ondrop: frame geometry (x,y)=(%d,%d),(w,h)=(%d,%d),depth=%d\n",gx,gy,gw,gh,gd);//~v685R~
//ajust pos on frame window to pos on view window                  //~v685R~
    gdk_window_get_geometry(Gpview->window,&viewposx,&viewposy,&vieww,&viewh,&viewd);//~v685I~
    row=Py-viewposy;                                               //~v685I~
    if (row<0)                                                     //~v685I~
    	row=0;                                                     //~v685I~
    col=Px-viewposx;                                               //~v685I~
    if (col<0)                                                     //~v685I~
    	col=0;                                                     //~v685I~
	row=ROW(row);                                                  //~v685R~
	col=COL(col);                                                  //~v685R~
    dprintf("ondrop: rowcol=(%d,%d)\n",row,col);                   //~v685R~
	rc=xxefile_ondropfilesub(0,Pseldata,0,row,col);                //~v685R~
    dprintf("%s:drag data finish req success=%d\n",(char*)utimeedit("HH:MM:SS.MIL",0),rc);//~v685R~
    Mdroprc=rc; //to notify dragend for internal dnd case          //~v685I~
	gtk_drag_finish (Pcontext,rc,FALSE,Ptime);//del FALSE          //~v685R~
    dprintf("%s:drag data finished\n",(char*)utimeedit("HH:MM:SS.MIL",0));//~v685I~
    usetfocus();                                                   //~v68iI~
}//xxefile_ondropfiles                                             //~v686I~
//===============================================================================//~v686I~
//open dropped file                                                //~v686I~
//return drop success id                                           //~v685I~
//===============================================================================//~v686I~
int xxefile_ondropfilesub(int Popt,                                //~v686R~
                         GtkSelectionData *Pseldata,               //~v686I~
                         void *Ppcwtarget,                         //~v685I~
                         int Prow,int Pcol)                        //~v686I~
{                                                                  //~v686I~
	int  rc;                                                       //~v686I~
    char *pc;                                                      //~v686I~
    char *filenames;                                               //~v686I~
    int fileno,ii;                                                 //~v685R~
    int filecopy;                                                  //~v685I~
    char targetdir[_MAX_PATH+1];                                   //~v685I~
    int rc2=TRUE;                                                  //~v685I~
//***************************************                          //~v686I~
	Gxxestat&=~GXXES_DNDUTIL;                                      //~v76gI~
	filecopy=wxe_dndfilecopychk(DROP_COPYCHKMSG,Mdragedpcw,Ppcwtarget,Prow,Pcol,targetdir);//~v685R~
  if (filecopy<0)	//drop on dir list failed(target not selected) or not dir//~v685I~
  	rc2=FALSE;                                                     //~v685I~
  else                                                             //~v685I~
  if (filecopy==DROPRC_UTIL)	//drop to utility panel            //~v76gI~
  {                                                                //~v76gI~
	Gxxestat|=GXXES_DNDUTIL;                                       //~v76gI~
	if (!uclipboard_getcopyfilesub(0,Pseldata,&filenames,&fileno,0))//~v76gI~
    {                                                              //~v76gI~
        for (pc=filenames,ii=fileno;ii>0;ii--)                     //~v76gI~
        {                                                          //~v76gI~
    	 	rc=wxe_dndutil(Popt,pc,Prow);                          //~v76jR~
            if (rc)                                                //~v76gI~
                break;                                             //~v76gI~
            pc+=strlen(pc)+1;                                      //~v76gI~
        }                                                          //~v76gI~
        umemfree(filenames);                                       //~v76gI~
    }                                                              //~v76gI~
	rc2=TRUE;	//done                                             //~v76jR~
  }                                                                //~v76gI~
  else                                                             //~v76gI~
  if (filecopy)                                                    //~v685I~
  {                                                                //~v685I~
    if (filepostcopybydndmsg(Popt,Pseldata,targetdir))             //~v685R~
	  	rc2=FALSE;                                                 //~v685R~
  }                                                                //~v685I~
  else                                                             //~v685I~
  {                                                                //~v685I~
//     dprintf("drag data received selection=%d,target=%d,type=%d,format=%d,data=%s,len=%d\n",//~v686R~
//        (int)(Pseldata->selection),                              //~v686R~
//        (int)(Pseldata->target),                                 //~v686R~
//        (int)(Pseldata->type),                                   //~v686R~
//        Pseldata->format,                                        //~v686R~
//        Pseldata->data,                                          //~v686R~
//        Pseldata->length);                                       //~v686R~
//    dprintf("receive name selection=%s\n",                       //~v686R~
//             gdk_atom_name(Pseldata->selection));                //~v686R~
//    dprintf("receive name target=%s\n",                          //~v686R~
//             gdk_atom_name(Pseldata->target));                   //~v686R~
//    dprintf("receive name type=%s\n",                            //~v686R~
//             gdk_atom_name(Pseldata->type));                     //~v686R~
//    if (Pseldata->length>0 && Pseldata->format==8)               //~v686R~
//    {                                                            //~v686R~
//        dropsuccess=TRUE;                                        //~v686R~
//        pc0=umemalloc(Pseldata->length);                         //~v686R~
//        memcpy(pc0,Pseldata->data,Pseldata->length);             //~v686R~
//        for (pc=pc0;*pc;)                                        //~v686R~
//        {                                                        //~v686R~
//            pce=strchr(pc,'\n');                                 //~v686R~
//            if (!memcmp(pc,TEXTFILEID,TEXTFILEIDLEN))            //~v686R~
//            {                                                    //~v686R~
//                if (pce)                                         //~v686R~
//                {                                                //~v686R~
//                    if (pce>pc && *(pce-1)=='\r')                //~v686R~
//                        *(pce-1)=0;         //0d0a               //~v686R~
//                    else                                         //~v686R~
//                        *pce=0;             //0a                 //~v686R~
//                }                                                //~v686R~
////              pfnm=g_filename_from_uri(pc,&phostname,&perror);    //api may have bug//~v686R~
//                if (uuridecode(pc+TEXTFILEIDLEN,fnm,_MAX_PATH)<0)//~v686R~
//                {                                                //~v686R~
//                    dropsuccess=FALSE;                           //~v686R~
//                    break;                                       //~v686R~
//                }                                                //~v686R~
//                rc=wxe_editcmd("E",fnm);                         //~v686R~
////              g_free(pfnm);                                    //~v686R~
//                if (rc)                                          //~v686R~
//                    break;                                       //~v686R~
//            }                                                    //~v686R~
//            else                                                 //~v686R~
//            {                                                    //~v686R~
//                dropsuccess=FALSE;                               //~v686R~
//                break;                                           //~v686R~
//            }                                                    //~v686R~
//            if (!pce)                                            //~v686R~
//                break;                                           //~v686R~
//            pc=pce+1;                                            //~v686R~
//        }                                                        //~v686R~
//        umemfree(pc0);                                           //~v686R~
//    }                                                            //~v686R~
	if (!uclipboard_getcopyfilesub(0,Pseldata,&filenames,&fileno,0))//~v685R~
    {                                                              //~v686I~
        for (pc=filenames,ii=fileno;ii>0;ii--)                     //~v685R~
        {                                                          //~v686I~
            rc=wxe_editcmd("E",pc);                                //~v686R~
            if (rc)                                                //~v686I~
                break;                                             //~v686I~
            pc+=strlen(pc)+1;                                      //~v686R~
        }                                                          //~v686I~
        umemfree(filenames);                                       //~v686R~
    }                                                              //~v686I~
    else                                                           //~v685I~
  		rc2=FALSE;                                                 //~v685I~
  }                                                                //~v685I~
//	gtk_drag_finish (Pcontext,dropsuccess,FALSE,Ptime);//del FALSE //~v686R~
//    if (!dropsuccess)                                            //~v685R~
//        umsgbox("DragDrop;Unkown data format",MB_OK);            //~v685R~
    xxemain_scrinvalidate(0);                                      //~vXXER~
//	return;                                                        //~v685R~
  	return rc2;                                                    //~v685I~
}//xxefile_ondropfiles                                             //~vXXER~
#ifndef XXE                                                        //~vXXEI~
//===============================================================================//~v63gI~
//1st time drop csr entered                                        //~v63gI~
//===============================================================================//~v63gI~
DROPEFFECT CWxefile::ondragenter(COleDataObject* pDataObject, DWORD dwKeyState, CPoint point)//~v63gI~
{                                                                  //~v63gI~
//  if (Mswdndinprogress)    //dodrag in progress by wxemouse      //~v63kR~
//  	return DROPEFFECT_NONE;                                    //~v63kR~
    return DROPEFFECT_COPY;                                        //~v63gI~
}                                                                  //~v63gI~
//===============================================================================//~v63gI~
//1st time drop csr entered                                        //~v63gI~
//===============================================================================//~v63gI~
DROPEFFECT CWxefile::ondragover(COleDataObject* pDataObject, DWORD dwKeyState, CPoint point)//~v63gI~
{                                                                  //~v63gI~
//  if (Mswdndinprogress)    //dodrag in progress by wxemouse      //~v63kR~
//  	return DROPEFFECT_NONE;                                    //~v63kR~
    return DROPEFFECT_COPY;                                        //~v63gI~
}                                                                  //~v63gI~
#endif                                                             //~vXXEI~
//===============================================================================//~v685I~
//send copy file msg                                               //~v685I~
//===============================================================================//~v685I~
int  filepostcopybydndmsg(int Popt,GtkSelectionData *Pseldata,char *Ptarget)//~v685I~
{                                                                  //~v685I~
    char *msg,*filenames;                                          //~v685R~
    int len,totlen,msglen,fileno;                                  //~v685R~
    long parm2;                                                    //~v685I~
//***************************************                          //~v685I~
	if (uclipboard_getcopyfilesub(0,Pseldata,&filenames,&fileno,&totlen))//~v685R~
    	return -1;                                                 //~v685I~
    if (!fileno)                                                   //~v685I~
    	return -1;                                                 //~v685I~
    len=strlen(Ptarget)+1;                                         //~v685I~
    msglen=len+totlen;                                             //~v685I~
    msg=(char*)umemalloc(msglen);                                  //~v685I~
    memcpy(msg,Ptarget,len);	//top is target                    //~v685I~
    memcpy(msg+len,filenames,totlen);                              //~v685I~
    umemfree(filenames);                                           //~v685I~
    parm2=(1<<16)|fileno;	//seq & total                          //~v685I~
    parm2|=Popt;                                                   //~v685I~
	return upostmsg(ID_FILE_DNDCOPY,(ULONG)msg,parm2);             //~v685I~
}//filepostcopybydnd                                               //~v685I~
//===============================================================================//~v685I~
//execute copy by post msg                                         //~v685I~
//===============================================================================//~v685I~
BOOL  filecopybydnd(ULONG Pcmdparm1,ULONG Pcmdparm2)               //~v685R~
{                                                                  //~v685I~
    char *tgt,*src;                                                //~v685I~
    int rc,seq,tot,ii;                                             //~v685I~
    ULONG nextseq,flag;                                            //~v685I~
    int opt=0;                                                     //~v685I~
//***************************************                          //~v685I~
dprintf("@@@@dragdrop postmag enter parm=%lx,%lx\n",Pcmdparm1,Pcmdparm2);//~v685R~
    flag=Pcmdparm2 & DROPFILE_FLAGMASK;                            //~v685I~
    Pcmdparm2 &= ~DROPFILE_FLAGMASK;                               //~v685I~
    if (flag & DROPFILE_REP)                                       //~v685I~
    	opt|=DNDCOPY_REP;                                          //~v685I~
    seq=Pcmdparm2>>16;                                             //~v685I~
    tot=Pcmdparm2&0xffff;                                          //~v685I~
	tgt=(char*)Pcmdparm1;                                          //~v685I~
    src=tgt+strlen(tgt)+1;                                         //~v685I~
    for (ii=seq-1;ii>0;ii--)                                       //~v685I~
    	src+=strlen(src)+1;                                        //~v685I~
dprintf("@@@@dragdrop postmag enter seq=%d of %d s=%s,t=%s\n",seq,tot,src,tgt);//~v685R~
	rc=wxe_dndcopyfile(opt,seq,tot,src,tgt);                       //~v685I~
dprintf("@@@@dragdrop dndcopyfile rc=%d\n",rc);                    //~v685R~
    if (rc==-1||seq==tot)	//cancelled or last                    //~v685I~
	    umemfree((void*)Pcmdparm1);                                //~v685I~
    else                                                           //~v685I~
    {                                                              //~v685I~
    	seq++;                                                     //~v685I~
        nextseq=(seq<<16)|tot;                                     //~v685I~
        nextseq|=flag;                                             //~v685I~
		upostmsg(ID_FILE_DNDCOPY,(ULONG)Pcmdparm1,nextseq);        //~v685I~
    }                                                              //~v685I~
    invalidate();                                                  //~v685I~
	return TRUE;                                                   //~v685R~
}//filecopybydnd                                                   //~v685I~
#ifndef ARM                                                        //~v@@@I~
//***************************************************************************//~vXXEM~
// File                                                            //~vXXEM~
//***************************************************************************//~vXXEM~
void dlgcreate 				(GtkMenuItem     *Pmenuitem,           //~vXXER~
                                        int              Pdlgmode)//1:new,0:saveas//~vXXER~
{                                                                  //~vXXEM~
  	GtkWidget *framewindow;                                        //~vXXEM~
//****************************                                     //~vXXEM~
	 dprintf("on_file_activate  \n");                               //~vXXER~
  	framewindow = lookup_widget (GTK_WIDGET (Pmenuitem), "framewindow");//~vXXEM~
    Sfiledlg = create_fileselection();                             //~vXXEM~
//  gtk_object_set_data (GTK_OBJECT (Sfiledlg), FRAME_WINDOW_KEY, framewindow);//~vXXER~
                                                                   //~vXXEM~
      /* Make sure the dialog doesn't disappear behind the main window. *///~vXXEM~
    gtk_window_set_transient_for (GTK_WINDOW (Sfiledlg),           //~vXXEM~
				    GTK_WINDOW (framewindow));                     //~vXXEM~
  	gtk_widget_show (Sfiledlg);                                    //~vXXEM~
                                                                   //~vXXEM~
  	/* We make sure the dialog is visible. */                      //~vXXEM~
  	gtk_window_present (GTK_WINDOW (Sfiledlg));                    //~vXXEM~
    Sdlgmode=Pdlgmode;                                             //~vXXER~
    return;                                                        //~vXXEM~
}                                                                  //~vXXEM~
#endif //ARM                                                       //~v@@@I~
