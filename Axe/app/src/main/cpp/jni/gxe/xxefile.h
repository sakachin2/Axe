//*CID://+vax7R~:                             update#=   44;       //+vax7R~
//******************************************************************************//~v63hI~
//vax7:140627 (for jni)vaf6:120604 (XXE) fildialog(gtkfileselection) display previous selection directory//+vax7I~
//v686:060323 dnd support by paste(edit:pastev,ins=paste-ins,rep=paste-rep)//~v686I~
//v685:060322 dnd support "file copy into the dir slected" and even in inter-split-screen.//~v685I~
//vXXE:050611 new for XXE                                          //~vXXEI~
//v63k:050509 (WXE)change mouse csr when dragout in progress       //~v63kI~
//v63h:050504 (WXE)support DragOut                                 //~5506I~
//******************************************************************************//~v63hI~
#ifndef HEADER_WXEFILE                                             //~2817R~
#define HEADER_WXEFILE                                             //~2817R~
                                                                   //~2816I~
//================================================================================//~2816I~
#ifdef GBL_XXEFILE                                                 //+vax7I~
	char *Gfileselectiondir;                                       //+vax7I~
#else                                                              //+vax7I~
	extern char *Gfileselectiondir;                                //+vax7I~
#endif                                                             //+vax7I~
//================================================================================//~vXXEI~
//file                                                             //~2817R~
//================================================================================//~2816I~
	void gxedoc_OnSaveDocument(void);                              //~vXXEI~
	void gxedoc_OnOpenDocument             (GtkMenuItem     *menuitem,//~vXXEI~
                                        gpointer         user_data);//~vXXEI~
	void gxedoc_OnNewDocument 	           (GtkMenuItem     *menuitem,//~vXXEI~
                                        gpointer         user_data);//~vXXEM~
	void gxedoc_OnSaveAsDocument 	       (GtkMenuItem     *menuitem,//~vXXEI~
                                        gpointer         user_data);//~vXXEM~
	void xxefile_ondropfiles(GdkDragContext  *drag_context,        //~vXXEI~
                         GtkSelectionData *Pseldata,               //~vXXEI~
                         int Px,int Py,                            //~v686R~
                         UINT Ptime);                              //~vXXEI~
	int xxefile_ondropfilesub(int Popt,                            //~v686R~
                         GtkSelectionData *Pseldata,               //~v686I~
                         void *Ppcwtarget,                         //~v686I~
                         int Prow,int Pcol);                       //~v686I~
#define DROPFILE_CP       0x80000000   //drag&drop by cut&paste    //~v686I~
#define DROPFILE_REP      0x40000000   //file copy by replace mode //~v686I~
#define DROPFILE_FLAGMASK 0xf0000000   //post message flag mask    //~v686I~
	int  filepostcopybydndmsg(int Popt,GtkSelectionData *Pseldata,char *Ptarget);//~v686I~
	BOOL  filecopybydnd(ULONG Pcmdparm1,ULONG Pcmdparm2);          //~v686I~
                                                                   //~v686I~
	void xxefile_dlgOnOK      				(void);                //~vXXEI~
	void xxefile_dlgOnCancel  				(void);                //~vXXER~
#ifndef XXE                                                        //~vXXEI~
class   CWxefile                                                   //~2817R~
{                                                                  //~2816I~
public :                                                           //~2816I~
    CWxefile();                                                    //~2817R~
    ~CWxefile();                                                   //~2817R~
	int init(CDocument *Ppdoc);                                    //~2817R~
	DROPEFFECT ondragenter(COleDataObject* pDataObject, DWORD dwKeyState, CPoint point);//~v63hI~
	DROPEFFECT ondragover(COleDataObject* pDataObject, DWORD dwKeyState, CPoint point);//~v63hI~
	int xxefile_ondropfilesub(int Popt,                            //~v686I~
                         GtkSelectionData *Pseldata,               //~v686I~
                         void *Ppcwtarget,                         //~v686I~
                         int Prow,int Pcol);                       //~v686I~
    int Mxeinitsw;                                                 //~2A08R~
//  int Mswdndinprogress;   //dodrag in progress by wxemouse       //~v63kR~
private :                                                          //~2816I~
	void invalidate(void);                                         //~2817R~
    CDocument  *Mpdoc;                                             //~2A10R~
};                                                                 //~2816I~
#endif                                                             //~vXXEI~
                                                                   //~2816I~
#endif	//HEADER_WXEFILE                                           //~2817R~
