//*CID://+vby0R~:                             update#=  125;       //+vby0R~
//********************************************************************//~2B09I~
//vby0:230322 for Axe Api33; ld duplicate symbole                  //+vby0I~
//va6y:000706 CUPs default printer is not same as gnome default    //~va20I~
//va20:091125 (UTF8)UTF8 full support(display utf8 also unprintable as locale code)//~va20I~
//vXXE:050611 new for XXE                                          //~vXXEI~
//v62W:050422 (WXE)maximum cell height definition for small doc    //~v62WI~
//v55D:040309 (WXE)popup help for dialog control                   //~v55DI~
//v51h:030525 (WXE)col/row by font size specification              //~3525I~
//********************************************************************//~3525I~
#define MARGIN_PREVIEWBOX    4  //8    //preview box border margin //~vXXER~
#define MARGIN_FORM_CLIP     0    //10mm form minimum margin       //~vXXER~
#define PRINTCOL_MIN         0    //print page column minimum      //~3525I~
//#define PRINTCOL_MAX         500  //print page column maximum      //~3525R~//~va20R~
#define PRINTROW_MIN         0    //print page row    minimum      //~3525I~
#define PRINTROW_MAX         500  //print page row    maximum      //~3525R~
#define PRINTFONTSZH_MIN      2    //print page fontsz minimum     //~3608R~
#define PRINTFONTSZH_MAX     999   //print page fontsz maximum     //~3601R~
#define PRINTFONTSZW_MIN      0    //print page fontsz minimum     //~3525I~
#define PRINTFONTSZW_MAX     999   //print page fontsz maximum     //~3601R~
#define PRINTCELLSZH_MIN      0    //print page fontsz minimum     //~v51hI~
#define PRINTCELLSZH_MAX     999   //print page fontsz maximum     //~v51hI~
#define PRINTCELLSZW_MIN      0    //print page fontsz minimum     //~v51hI~
#define PRINTCELLSZW_MAX     999   //print page fontsz maximum     //~v51hI~
#define PRINTCELLSZHMAX_MIN      0    //print page cell height max //~v62WI~
#define PRINTCELLSZHMAX_MAX     999   //print page cell height max //~v62WI~
//********************************************************************//~2B09I~
void gxepage_init(void);                                           //~vXXEI~
void gxepage_fontstyle(void);                                      //~vXXEI~
void gxepage_OnPreview(void);                                      //~vXXEI~
void gxepage_OnPreviewNextpage(void);                              //~vXXEI~
void gxepage_OnPreviewprevpage(void);                              //~vXXER~
void gxepage_OnPreviewprtscr(void);                                //~vXXEI~
void gxepage_OnPrint(void);                                        //~vXXEI~
void gxepage_OnPrtscr(void);                                       //~vXXEI~
void gxepage_OnOk(void);                                           //~vXXEI~
void gxepage_OnCancel(void);                                       //~vXXEI~
void gxepage_create	(GtkMenuItem     *Pmenuitem,                   //~vXXER~
                                        gpointer         Puser_data);//~vXXEI~
void gxepage_OnCheckHeader(void);                                  //~vXXEI~
void gxepage_OnCheckFooter(void);                                  //~vXXEI~
void gxepage_OnCheckpfontsz(void);                                 //~vXXEI~
void gxepage_OnCheck2p(void);                                      //~vXXEI~
void gxepage_OnChangeEditCurpage(void);                            //~vXXEI~
void gxepage_fontstyle(void);                                      //~vXXEI~
void gxepage_Font_OnOK(void);                                      //~vXXER~
void gxepage_Font_OnApply(void);                                   //~vXXEI~
int  gxepage_prtgetformsz(int Plandscape,char *Pformtype,int *Ppwidth,int *Ppheight);//~vXXER~
void gxepage_OnChanged_ComboPrinter(GtkEditable *editable,gpointer user_data);//~va20I~
//********************************************************************//~vXXEI~
#ifdef GBL_XXEMAIN                                                 //+vby0I~
	int		Mcurpage;                                              //~vXXEM~
#else                                                              //+vby0I~
	extern int Mcurpage;                                           //+vby0I~
#endif                                                             //+vby0I~
#ifndef XXE                                                        //~vXXEI~
};


#endif                                                             //~vXXEI~
