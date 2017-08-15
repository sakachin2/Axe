//*CID://+vay0R~:                                   update#=  341; //~vay0R~
//**************************************************************** //~1610I~
//vay0:140710 (Axe)jni exception handling                          //~vay0I~
//vak2:130822 Axe:ndk-r9 warning                                   //~vak2I~
//vad0:120417 (Lnx)option to use ICU as local converter            //~vad0I~
//rtn for gtk on ARM                                               //~1713R~
//**************************************************************** //~1610I~
#include <stdio.h>                                                 //~1610I~
#include <string.h>                                                //~1614I~
#include <iconv.h>                                                 //~1618I~
#include <sys/timeb.h>                                             //~1618I~
//**********************                                           //~1610I~
#include <jni.h>                                                   //~1616R~
#include <jnig.h>                                                  //~1616I~
#include <jnia.h>                                                  //~1616I~
#include <jnic2j.h>                                                //~1826I~
#include <jnicsub.h>                                               //~1724I~
#include <jnisigh.h>                                               //~vay0I~
#include <android/log.h>                                           //~1616I~
                                                                   //~1618I~
#include <ulib.h>                                                  //~1618I~
#include <ualloc.h>                                                //~1618I~
#include <ustring.h>                                               //~1618I~
#include <utf.h>                                                   //~1618I~
#include <ucvucs.h>                                                //~1829I~
#include <utrace.h>                                                //~1714I~
#include <ustring.h>                                               //~1A06I~
#include <udbcschk.h>                                              //~vad0I~
#include <ucvext.h>                                                //~vad0I~
                                                                   //~1621I~
#include <xxedef.h>                                                //~1621R~
#include <gxe.h>                                                   //~1713I~
#include <gxemfrm.h>                                               //~1621I~
#include <gxeview.h>                                               //~1A18I~
#include <xxemain.h>                                               //~1621I~
#include <xxecsub.h>                                               //~1622R~
#include <xxeres.h>                                                //~1A05I~
//*************************************************                //~1617I~
static int *Spopupmenu_enabletbl;                                  //~1927R~
static int  Spopupmenu_enabletblsz;                                //~1927I~
static char Smimetype[128];                                        //~1A06I~//~1A19M~
//*************************************************                //~1618I~
//*************************************************                //~1617I~
//*************************************************                //~1622I~
int gtk_im_context_filter_keypress(GtkIMContext *Ppimcontext,GdkEventKey *Pevent)//~1616R~
	{ return 0; }                                                  //~1617I~
//*************************************************                //~1617I~
GdkScreen *gdk_screen_get_default (void)                           //~1617I~
	{ return NULL; }                                               //~1617I~
//*************************************************                //~1617I~
GdkPixmap* gdk_pixmap_new		(GdkDrawable *drawable,            //~1617I~
					 gint	      width,                           //~1617I~
					 gint	      height,                          //~1617I~
					 gint	      depth)                           //~1617I~
	{ return NULL; }                                               //~1617I~
//*************************************************                //~1617I~
GdkColormap* gdk_colormap_get_system	        (void)             //~1617I~
	{ return NULL; }                                               //~1617I~
//*************************************************                //~1617I~
gboolean gdk_colormap_alloc_color (GdkColormap    *colormap,       //~1617I~
				   GdkColor       *color,                          //~1617I~
				   gboolean        writeable,                      //~1617I~
				   gboolean        best_match)                     //~1617I~
	{ return TRUE; }                                               //~1617R~
//*************************************************                //~1617I~
void   gdk_gc_set_function	  (GdkGC	    *gc,                   //~1617I~
				   GdkFunction	     function)                     //~1617I~
	{ return; }                                                    //~1617I~
//*************************************************                //~1617I~
//*************************************************                //~1617I~
void	   gtk_widget_queue_draw_area	  (GtkWidget	       *widget,//~1617I~
					   gint                 x,                     //~1617I~
					   gint                 y,                     //~1617I~
					   gint                 width,                 //~1617I~
					   gint                 height)                //~1617I~
	{ return; }                                                    //~1617I~
//*************************************************                //~1617I~
GtkIMContext *gtk_im_multicontext_new      (void)                  //~1617I~
	{ return NULL; }                                               //~1617I~
//***********************************************************      //~1926R~
//**GDK_SELECTION_CLIPBOARD and GDK_SELECTION_PRIMARY(Not used)    //~1926I~
//************************************************************     //~1926I~
GtkClipboard *gtk_clipboard_get(GdkAtom selection)                 //~1926R~
{                                                                  //~1926R~
    static jmethodID staticMethod_clipboard_get;                   //~1926I~
    JNIEnv *penv;                                                  //~1926I~
//****************************                                     //~1926I~
    penv=getThreadEnv();                                           //~1926I~
	UTRACEP("gtk_clipboard_get env=%p\n",penv);                    //~1926I~
    C2J_GETMETHODID_GOTOIFERR(penv,clipboard_get,"()V");           //~1926R~
    UTRACEP("gtk_clipboard_get addr=%p\n",staticMethod_clipboard_get);//~vad0I~
    C2J_VOIDVOID(penv,clipboard_get);                              //~1926R~
ret:                                                               //~1926I~
	return NULL;                                                   //~1926I~
}                                                                  //~1926I~
//*************************************************                //~1617I~
//*************************************************                //~1617I~
//*************************************************                //~1617I~
void g_object_unref(gpointer object)                               //~1618R~
{                                                                  //~1617I~
	UTRACEP("g_object_unref object=%p\n",object);                  //~vay0R~
 	return;                                                        //~1617I~
}                                                                  //~1617I~
//*************************************************                //~1617I~
void g_free(gpointer mem)                                          //~1618R~
{                                                                  //~1617I~
	UTRACEP("g_free mem=%p,\n",mem);                               //~vay0R~
    if (mem)                                                       //~1617I~
    	free(mem);                                                 //~1617I~
}                                                                  //~1617I~
//*************************************************                //~1617I~
//*return true if charset is utf8                                  //~1617I~
//*************************************************                //~1617I~
gboolean g_get_charset (G_CONST_RETURN char **charset)             //~1617I~
{                                                                  //~1617I~
    static jmethodID staticMethod_get_charset;                     //~1617I~
    char buff[128],*cs;                                            //~1617I~
    gboolean isutf8=FALSE;                                         //~1715R~
    jbyteArray jbytes;                                             //~1703I~
    int len;                                                       //~1618I~
    JNIEnv *penv;                                                  //~1714I~
//****************************                                     //~1714I~
    penv=getThreadEnv();                                           //~1714I~
	UTRACEP("g_get_charset entry env=%p,class=%p\n",penv,Gclass);  //~1714R~
	memset(buff,0,sizeof(buff));                                   //~1617I~
    jbytes=byte2jbyte(penv,buff,sizeof(buff));                     //~1715M~
    C2J_GETMETHODID_GOTOIFERR(penv,get_charset,"([B)V");           //~1715R~
    C2J_VOID(penv,get_charset,jbytes);                //~1714R~    //~1718R~
    jbyte2byte(penv,jbytes,0,sizeof(buff),buff);                   //~1714R~
	UTRACEP("g_get_charset name=%s\n",buff);                       //~vay0R~
    len=strlen(buff);                                              //~1617I~
    cs=(char*)umalloc(len+1);                                      //~1618R~
    UmemcpyZ(cs,buff,len);                                         //~1618R~
    *charset=cs;                                                   //~1617I~
    isutf8=ustrstri(cs,"UTF-8")!=0||ustrstri(cs,"UTF8")!=0;        //~1618R~
ret:                                                               //~1715I~
//	LOGPD("@@@@g_get_charset unref jbyte=%p\n",jbytes);            //+vay0R~
    unrefLocal(penv,jbytes);                                       //~1715R~
    return isutf8;                                                 //~1617R~
}                                                                  //~1617I~
//*************************************************                //~1617I~
gboolean g_utf8_validate (const gchar  *str,                       //~1618I~
                          gssize        max_len,                   //~1618I~
                          const gchar **end)                       //~1618I~
{                                                                  //~1618I~
	int opt,chrcnt,utf8len;                                        //~1618R~
    gchar *errpos,*utf8str;                                        //~1618R~
    gboolean rc;                                                   //~1618I~
//*********************                                            //~1618I~
	UTRACEP("g_utf8_validate %*s\n",max_len,str);                  //~vay0R~
    utf8str=(gchar*)str;                                           //~1618R~
	opt=0;                                                         //~1618I~
	utf8len=utfgetutf8strlen(opt,utf8str,max_len,&chrcnt);         //~1618R~
    errpos=utf8str+max_len-utf8len;                                //~1618R~
    *end=errpos;                                                   //~1618I~
    rc=max_len==utf8len;                                           //~1618I~
	UTRACEP("g_utf8_validate rc=%d,validlen=%d\n",rc,utf8len);     //~vay0R~
    return rc;                                                     //~1618I~
}                                                                  //~1618I~
//*************************************************                //~1618I~
//*from csubutf8init                                               //~vad0I~
//* to_charset:UTF-8                                               //~vad0I~
//* 	called if current charset by Java is not UTF-8(maybe it is always UTF-8)//~vad0I~
//*************************************************                //~vad0I~
GIConv g_iconv_open   (const gchar  *to_codeset,                   //~1617I~
		       const gchar  *from_codeset)                         //~1617I~
{                                                                  //~1617I~
	iconv_t handle;                                                //~1617I~
    ULONG pconverters;                                             //~vad0R~
//******************                                               //~vad0I~
  if (UDBCSCHK_IS_LOCALICU())	//LOCALICU mode is set at PREINIT call//~vad0I~
  {                                                                //~vad0I~
	udbcschk_getbaseconverter(0,&pconverters,0/*ppdbcstbl*/);      //~vad0I~
//  UTRACEP("g_iconv_open converters=%p\n",to_codeset,from_codeset,pconverters);//~vay0R~
	return (gpointer)pconverters;                                  //~vad0R~
  }                                                                //~vad0I~
  else                                                             //~vad0I~
  {                                                                //~vad0I~
	handle=iconv_open(to_codeset,from_codeset);                    //~1617R~
	UTRACEP("g_iconv_open to=%s,from=%s handle=%p\n",to_codeset,from_codeset,handle);//~vay0R~
	return (gpointer)handle;                                       //~1617I~
  }                                                                //~vad0I~
}                                                                  //~1617I~
//*************************************************                //~1617I~
//*from csublocaletoutf8 if env is not UTF8-(maybe always it is UTF-8)//~vad0I~
//*************************************************                //~vad0I~
gchar* g_convert_with_iconv    (const gchar  *str,                 //~1617I~
				gssize        len,                                 //~1617I~
				GIConv        converter,                           //~1617I~
				gsize        *bytes_read,                          //~1617I~
				gsize        *bytes_written,                       //~1617I~
//  			GError      **error) G_GNUC_MALLOC;                //~1618R~
    			GError      **error)                               //~1618I~
{                                                                  //~1617I~
	iconv_t handle;                                                //~1617I~
    size_t /*nrctr,*/resi,reso,outbuffsz;	//non reversivle ctr   //~vak2R~
    char *pbuff,*pco,*pci;                                         //~1618R~
    ULONG *pconverters;                                            //~vad0R~
    int readlen,outlen;                                            //~vad0I~
//********************                                             //~1617I~
	UTRACEP("g_convert_with_iconv handle=%p\n",converter);         //~vay0R~
  if (UDBCSCHK_IS_LOCALICU())	//LOCALICU mode is set at PREINIT call//~vad0I~
  {                                                                //~vad0I~
    pconverters=(ULONG*)(ULONG)converter;	//Sconverterdescriptor of xxecsub//~vad0R~
    resi=len;                                                      //~vad0I~
    pci=(char*)str;                                                //~vad0I~
    outbuffsz=len*UTF8_MAXCHARSZMAX;	//*6                       //~vad0I~
    pbuff=(char*)umalloc(outbuffsz);                               //~vad0I~
    reso=outbuffsz;                                                //~vad0I~
    pco=pbuff;                                                     //~vad0I~
	ucvext_icumb2utf(UCVEXTO_SETSUBCHRC,pconverters,pci,resi,pbuff,reso,0/*repch*/,&readlen,&outlen,0/*repctr*/);//~vad0R~
    *bytes_read=readlen;                                           //~vad0I~
    *bytes_written=outlen;                                         //~vad0I~
  }                                                                //~vad0I~
  else                                                             //~vad0I~
  {                                                                //~vad0I~
    handle=(iconv_t)converter;                                     //~1617I~
    resi=len;                                                      //~1617I~
    pci=(char*)str;                                                //~1618R~
    outbuffsz=len*UTF8_MAXCHARSZMAX;	//*6                       //~1617I~
    pbuff=(char*)umalloc(outbuffsz);                               //~1618R~
    reso=outbuffsz;                                                //~1617I~
    pco=pbuff;                                                     //~1618I~
	/*nrctr=*/iconv(handle,&pci,&resi,&pco,&reso);                 //~vak2R~
    *bytes_read=(ULONG)pci-(ULONG)str;                             //~1618R~
    *bytes_written=(ULONG)pco-(ULONG)pbuff;                        //~1618R~
  }                                                                //~vad0I~
	return pbuff;                                                  //~1617I~
}                                                                  //~1617I~
//*************************************************                //~1617I~
void gdk_gc_set_background(GdkGC *Pgc,const GdkColor *Pcolor)      //~1617R~
{                                                                  //~1617M~
//*********************                                            //~1617M~
	Pgc->background=*Pcolor;                                       //~1620R~
    return;                                                        //~1617I~
}                                                                  //~1617M~
//*************************************************                //~1617I~
void gdk_gc_set_foreground(GdkGC *Pgc,const GdkColor *Pcolor)      //~1617I~
{                                                                  //~1617I~
//*********************                                            //~1617I~
	Pgc->foreground=*Pcolor;                                       //~1617R~
    return;                                                        //~1617I~
}                                                                  //~1617I~
//*************************                                        //~1617M~
void gdk_beep(void)                                                //~1617M~
{                                                                  //~1617M~
    static jmethodID staticMethod_beep;                            //~1617M~
    JNIEnv *penv;                                                  //~1714I~
//****************************                                     //~1714I~
    penv=getThreadEnv();                                           //~1714I~
	UTRACEP("beep env=%p\n",penv);                                 //~1714R~
    C2J_GETMETHODID_RETIFERR(penv,beep,"()V");                     //~1714R~
    C2J_VOIDVOID(penv,beep);                              //~1714R~//~1718R~
    return;                                                        //~1617I~
}                                                                  //~1617M~
//*************************************************                //~1617M~
gint         gdk_screen_get_width             (GdkScreen   *screen)//~1617M~
{                                                                  //~1617M~
	UTRACEP("gdk_screen_get_width width=%d\n",GjniScrW);           //~vay0R~
    Gpview->allocation.width=GjniScrW;	//for xxemain_scrinit      //~1713I~
    return GjniScrW;                                               //~1617M~
}                                                                  //~1617M~
//*************************************************                //~1617M~
gint         gdk_screen_get_height            (GdkScreen   *screen)//~1617R~
{                                                                  //~1617M~
	UTRACEP("gdk_screen_get_width height=%d\n",GjniScrH);          //~vay0R~
    Gpview->allocation.height=GjniScrH;                            //~1713I~
    return GjniScrH;                                               //~1617M~
}                                                                  //~1617M~
//*************************************************                //~1617I~
void   gdk_gc_set_line_attributes (GdkGC	    *gc,               //~1617I~
				   gint		     line_width,                       //~1617I~
				   GdkLineStyle	     line_style,                   //~1617I~
				   GdkCapStyle	     cap_style,                    //~1617I~
				   GdkJoinStyle	     join_style)                   //~1617I~
{                                                                  //~1617I~
	UTRACEP("gdk_set_line_attribute width=%d\n",line_width);       //~vay0R~
    gc->line_width=line_width;                                     //~1620R~
    return;                                                        //~1617I~
}                                                                  //~1617I~
//*************************************************                //~1617I~
void	   gtk_main_quit	   (void)                              //~1617I~
{                                                                  //~1617I~
    static jmethodID staticMethod_quit;                            //~1617I~
    JNIEnv *penv;                                                  //~1714I~
//****************************                                     //~1714I~
    penv=getThreadEnv();                                           //~1714I~
	UTRACEP("gdk_main_quit env=%p\n",penv);                        //~1714R~
    gxemfrm_onclose();      //putinidata(gtk calls at gtk_main_quit but)//~1929I~
    C2J_GETMETHODID_RETIFERR(penv,quit,"()V");                     //~1714R~
    C2J_VOIDVOID(penv,quit);                              //~1714R~//~1718R~
    return;                                                        //~1617I~
}                                                                  //~1617I~
//*************************************************                //~1617I~
void gdk_draw_rectangle (GdkDrawable      *drawable,               //~1617I~
			 GdkGC            *gc,                                 //~1617I~
			 gboolean          filled,                             //~1617I~
			 gint              x,                                  //~1617I~
			 gint              y,                                  //~1617I~
			 gint              width,                              //~1617I~
			 gint              height)                             //~1617I~
{                                                                  //~1617I~
    static jmethodID staticMethod_draw_rectangle;                  //~1617I~
    int fg;                                                        //~1620I~
    JNIEnv *penv;                                                  //~1714I~
//****************************                                     //~1714I~
    penv=getThreadEnv();                                           //~1714I~
    fg=GDKCOLOR2COLOR(&(gc->foreground));                          //~1620R~
	UTRACEP("gdk_draw_rectangle env=%p,fill=%d,x=%d,y=%d,w=%d,h=%d\n",penv,filled,x,y,width,height);//~1714R~
    C2J_GETMETHODID_RETIFERR(penv,draw_rectangle,"(IIIIII)V");     //~1714R~
    C2J_VOID(penv,draw_rectangle,filled,x,y,width,height,fg);//~1714R~//~1718R~
    return;                                                        //~1617I~
}                                                                  //~1617I~
//*************************************************                //~1617I~
void gdk_draw_line      (GdkDrawable      *drawable,               //~1617I~
			 GdkGC            *gc,                                 //~1617I~
			 gint              x1_,                                //~1617I~
			 gint              y1_,                                //~1617I~
			 gint              x2_,                                //~1617I~
			 gint              y2_)                                //~1617I~
{                                                                  //~1617I~
    static jmethodID staticMethod_draw_line;                       //~1617I~
    int fg,flag=0;                                             //~1620I~//~1809R~//~1826R~
    JNIEnv *penv;                                                  //~1714I~
//****************************                                     //~1714I~
    penv=getThreadEnv();                                           //~1714I~
    fg=GDKCOLOR2COLOR(&(gc->foreground));                          //~1620R~
    if (drawable!=NULL)     //if Gppixmap(=null) offscreen draw//~1809R~//~1826R~
    	flag|=DRAWLINE_DIRECT;	//draw to Canvas not offscreen buffer//~1826I~
    if (gc==Gpgcruler)             //draw ruler                    //~1826R~
    	flag|=DRAWLINE_RULER;                                      //~1826I~
    else                                                           //~1826I~
    if (gc==Gpgccsr)               //draw cursor                   //~1826R~
    	flag|=DRAWLINE_CARET;                                      //~1826I~
    //else ime(not used) and preview(not used)                     //~1826I~
	UTRACEP("gdk_draw_line env=%p,line_width=%d,x1=%d,y1=%d,x2=%d,y2=%d\n",penv,gc->line_width,x1_,y1_,x2_,y2_);//~1714R~
    C2J_GETMETHODID_RETIFERR(penv,draw_line,"(IIIIIII)V");          //~1714R~//~1809R~
    C2J_VOID(penv,draw_line,flag,gc->line_width,x1_,y1_,x2_,y2_,fg);//~1714R~//~1718R~//~1809R~//~1826R~
    return;                                                        //~1617I~
}                                                                  //~1617I~
//*************************************************                //~1617I~
void	pango_layout_set_text	(PangoLayout    *layout,           //~1620R~
					    const char     *text,                      //~1617I~
					    int             length)                    //~1617R~
{                                                                  //~1617I~
    int utf8len,ucstbsz,ucsctr,*ucstb,opt,readlen;                 //~1620R~//~1829R~
	PangoGlyphGeometry *pgeom;                                     //~1724I~
//***********                                                      //~1620I~
	UTRACEP("pango_layout_set_text len=%d,text=%*s\n",length,length,text);//~1716R~
	layout->text=(char*)text;                                      //~1617R~
	layout->length=length;                                         //~1617I~
	UTRACED("pango_layout_set_text",text,length);                  //~1716I~
//hold text by ucs                                                 //~1620I~
    utf8len=layout->length;                                        //~1620R~
    ucstbsz=utf8len*sizeof(int);                                   //~1620I~
	ucstb=layout->jni_ucstb;                                       //~1620I~
    if (ucstbsz>layout->jni_ucstbsz)                               //~1620R~
    {                                                              //~1620I~
    	ucstb=umalloc(ucstbsz);                                  //~1620R~//~1718R~
	    layout->jni_ucstbsz=ucstbsz;                               //~1620R~
        if (layout->jni_ucstb)                                     //~1620R~
        {                                                          //~1620I~
	        ufree(layout->jni_ucstb);                              //~1620R~
        }                                                          //~1620I~
	    layout->jni_ucstb=ucstb;                                   //~1620R~
    }                                                              //~1620I~
//    opt=UCVUCS_UCS4|UCVUCS_LE|UCVUCS_FULLREC;                    //~1829R~
//    ucvsutf2ucs(0/*Perr*/,opt,(UCHAR*)text,utf8len,(UCHAR*)(ULONG)ucstb,ucstbsz,&readlen,&outlen,&repctr);//~v57mR~//~1829R~
//    ucsctr=outlen/sizeof(int);                                   //~1829R~
    opt=0;                                                         //~1829R~
    utfcvf2u(opt,(WUCS*)ucstb,(UCHAR *)text,NULL/*pchof*/,NULL/*outdbcs*/,0,utf8len,&readlen,&ucsctr,NULL);//fail by ucvext_iconvucs2local1<-utfmb2wc//~1829I~
    //*no iconv called by UTCO_NOCHKLOCALE at utfcvf2u             //~vad0I~
	layout->jni_ucsctr=ucsctr;                                     //~1620R~
	layout->jni_fstrwidth=getgeometry(0,layout,&pgeom);            //~1724R~
    layout->jni_pgeom=pgeom;                                       //~1724I~
	layout->jni_settext=TRUE;                                      //~1620R~
    return;                                                        //~1617I~
}                                                                  //~1617I~
//*************************************************                //~1617I~
void           pango_layout_set_font_description (PangoLayout                *layout,//~1617I~
						  const PangoFontDescription *desc)        //~1617I~
{                                                                  //~1617I~
	UTRACEP("pango_layout_set_fontdescription");                     //~1617R~//~1828R~
	layout->font_desc=(PangoFontDescription*)desc;                 //~1617R~
    return;                                                        //~1617I~
}                                                                  //~1617I~
//*************************************************                //~1617I~
char *                pango_font_description_to_string   (const PangoFontDescription  *desc)//~1617I~
{                                                                  //~1617I~
	UTRACEP("pango_font_description_to_string familyname=%s\n",desc->family_name);//~vay0R~
	return desc->family_name;                                      //~1617I~
}                                                                  //~1617I~
//*************************************************                //~1617I~
PangoFontDescription *pango_font_description_from_string (const char *str)//~1617R~
{                                                                  //~1617I~
	PangoFontDescription *desc;                                    //~1617R~
//*********************                                            //~1617I~
	UTRACEP("pango_font_description_from_string fontname=%s\n",str);//~vay0R~
	desc=(PangoFontDescription*)umalloc(sizeof(PangoFontDescription));//~1618R~
	memset(desc,0,sizeof(PangoFontDescription));                   //~1617I~
	desc->family_name=strdup(str);                                 //~1617I~
    return desc;                                                   //~1617I~
}                                                                  //~1617I~
//*************************************************                //~1617I~
void gdk_draw_layout_with_colors      (GdkDrawable     *drawable,  //~1617I~
                                       GdkGC           *gc,        //~1617I~
                                       gint             x,         //~1617I~
                                       gint             y,         //~1617I~
                                       PangoLayout     *layout,    //~1617I~
                                       const GdkColor  *foreground,//~1617I~
                                       const GdkColor  *background)//~1617I~
{                                                                  //~1617I~
    static jmethodID staticMethod_draw_layout_with_color_monospace;//~1620R~
    static jmethodID staticMethod_draw_layout_with_color_ligature; //~1620I~
    int fg,bg,ucsctr/*,utf8len*/;                                      //~1715R~//~vak2R~
    int *ucstb;                                                    //~1719R~
    float *fpostb;                                                 //~1719I~
    float fscale;                                                   //~1620I~//~1719R~
    jintArray jucstb;                                              //~1719R~
    jfloatArray jfpostb=NULL;                                      //~1719I~
    JNIEnv *penv;                                                  //~1714I~
//****************************                                     //~1714I~
    penv=getThreadEnv();                                           //~1714I~
    fg=GDKCOLOR2COLOR(foreground);                                 //~1620I~
    bg=GDKCOLOR2COLOR(background);                                 //~1620I~
    ucsctr=layout->jni_ucsctr;                                     //~1620I~
    UTRACEP("gdk_draw_layout_width_color env=%p,x=%d,y=%d,fg=%x,bg=%x,ucsctr=%d\n",penv,x,y,fg,bg,ucsctr);//~1714R~
	if (!layout->jni_settext)                                      //~1620I~
    	return;                                                    //~1620I~
    ucstb=layout->jni_ucstb;                                       //~1620M~
    jucstb=int2jint(penv,ucstb,ucsctr);                            //~1715I~//~1A14R~
//    jucstb=gblint2jint(penv,ucstb,ucsctr);                       //~1A14R~
	if (layout->jni_ligature)	//string put at once               //~1620I~
    {                                                              //~1620I~
		if (layout->jni_ligature==2)	//string put at once       //~1719I~
	    	fscale=layout->jni_scale;                                   //~1620I~//~1719R~
        else                                                       //~1719I~
	    	fscale=0.0;                                            //~1719R~
        UTRACEP("gdk_draw_layout_width_color_ligature scale=%f\n",fscale);//~1715R~//~1719R~
        C2J_GETMETHODID_GOTOIFERR(penv,draw_layout_with_color_ligature,"([IIIIIIF)V");//~1715R~
        C2J_VOID(penv,draw_layout_with_color_ligature,jucstb,ucsctr,x,y,fg,bg,fscale);//~1715R~//~1718R~//~1719R~
    }                                                              //~1620I~
    else                                                           //~1620I~
    {                                                              //~1620I~
        fpostb=layout->jni_fpostb;                             //~1620I~//~1719R~
//      for (ii=0;ii<ucsctr;ii++)                                  //~1823R~
//      {                                                          //~1823R~
//          fpostb[ii*2]+=MjniFontoffsx;	//                     //~1823R~
//          fpostb[ii*2+1]=(float)(y+MjniBaseLine);	//(x,y) pair for drawPosText()//~1823R~
//      }                                                          //~1823R~
//      utf8len=layout->length;                                    //~vak2R~
//      UTRACEP("gdk_draw_layout_width_color_monospace ucsctr=%d,datalen=%d\n",ucsctr,utf8len);//~vak2R~
        jfpostb=float2jfloat(penv,fpostb,ucsctr*2);                    //~1718R~//~1719R~//~1A14R~
//        jfpostb=gblfloat2jfloat(penv,fpostb,ucsctr*2);           //~1A14R~
        C2J_GETMETHODID_GOTOIFERR(penv,draw_layout_with_color_monospace,"([IIIIII[F)V");//~1715R~//~1823R~
        C2J_VOID(penv,draw_layout_with_color_monospace,jucstb,ucsctr,x,y,fg,bg,jfpostb);//~1715R~//~1718R~//~1719R~
    }                                                              //~1620I~
ret:                                                               //~1715I~
//	LOGPD("@@@@draw_layout_with_color jbyte=%p\n",jucstb);         //+vay0R~
    unrefLocal(penv,jucstb);                                       //~1715R~//~1A14R~
    if (jfpostb)                                                   //~1715I~//~1719R~//~1A14R~
    {                                                              //~vay0I~
//		LOGPD("@@@@draw_layout_with_color jfpostb=%p\n",jfpostb);  //+vay0R~
        unrefLocal(penv,jfpostb);                                  //~1715R~//~1719R~//~1A14R~
    }                                                              //~vay0I~
    return;                                                        //~1617I~
}// gdk_draw_layout_with_colors                                    //~1715R~
//*************************************************                //~1617M~
GdkGC *gdk_gc_new		  (GdkDrawable	    *drawable)             //~1617M~
{                                                                  //~1617I~
    GdkGC *pgc;                                                    //~1617I~
//******************                                               //~1617I~
    pgc=umalloc(sizeof(GdkGC));                                    //~1618R~
    memset(pgc,0,sizeof(GdkGC));                                   //~1617I~
    return pgc;                                                    //~1617I~
}                                                                  //~1617I~
//*************************************************                //~1617I~
GdkGC *gdk_gc_new_with_values	  (GdkDrawable	    *drawable,     //~1617I~
				   GdkGCValues	    *values,                       //~1617I~
				   GdkGCValuesMask   values_mask)                  //~1617I~
{                                                                  //~1617I~
    GdkGC *pgc;                                                    //~1617I~
//******************                                               //~1617I~
    pgc=umalloc(sizeof(GdkGC));                                    //~1618R~
    memset(pgc,0,sizeof(GdkGC));                                   //~1617I~
    if (values_mask & GDK_GC_FOREGROUND)                           //~1617I~
    	pgc->foreground=values->foreground;                        //~1617R~
    if (values_mask & GDK_GC_LINE_WIDTH)                           //~1617I~
    	pgc->line_width=values->line_width;                        //~1617R~
    if (values_mask & GDK_GC_LINE_STYLE)                           //~1617I~
    	pgc->line_style=values->line_style;                        //~1617R~
    return pgc;                                                    //~1617I~
}                                                                  //~1617I~
//*************************************************                //~1617I~
GtkWidget* gtk_widget_new(void)                                    //~1618I~
{                                                                  //~1618I~
    GtkWidget *w;                                                  //~1618R~
    GtkStyle *s;                                                   //~1716I~
    char *pc;                                                      //~1716I~
    int l1,l2,len;                                                 //~1716I~
//****************                                                 //~1716I~
    l1=sizeof(GtkWidget);                                          //~1716R~
    l2=sizeof(GtkStyle);                                           //~1716R~
    len=l1+l2;                                                     //~1716I~
    pc=(char*)umalloc(len);                                        //~1716I~
    memset(pc,0,len);                                              //~1716R~
    w=(GtkWidget*)(ULONG)pc;                                       //~1716I~
    s=(GtkStyle*)((ULONG)pc+l1);                                   //~1716I~
    w->style=s;                 //for csub:uinvalidate             //~1716I~
    return w;                                                      //~1618I~
}                                                                  //~1618I~
////*************************************************                //~1618I~//~1A22R~
//GtkWidget* gtk_message_dialog_new      (GtkWindow      *parent,    //~1617I~//~1A22R~
//                                        GtkDialogFlags  flags,     //~1617I~//~1A22R~
//                                        GtkMessageType  type,      //~1617I~//~1A22R~
//                                        GtkButtonsType  buttons,   //~1617I~//~1A22R~
//                                        const gchar    *message_format,//~1617I~//~1A22R~
////                                      ...) G_GNUC_PRINTF (5, 6); //~1617I~//~1A22R~
//                                        ...)                       //~1618R~//~1A22R~
//{                                                                  //~1617I~//~1A22R~
//    GtkWidget *pdlg;                                               //~1618R~//~1A22R~
////******************                                               //~1617I~//~1A22R~
//    pdlg=gtk_widget_new();                                         //~1618R~//~1A22R~
//    pdlg->flags=flags;                                             //~1618R~//~1A22R~
//    pdlg->type=type;                                               //~1618R~//~1A22R~
//    pdlg->buttons=buttons;                                         //~1618R~//~1A22R~
//    pdlg->jni_message=(char*)message_format;                       //~1618R~//~1A22R~
//    return pdlg;                                                   //~1617I~//~1A22R~
//}                                                                  //~1617I~//~1A22R~
////*************************************************                //~1618I~//~1A22R~
//GtkWidget* gtk_dialog_add_button        (GtkDialog   *dialog,      //~1618I~//~1A22R~
//                                         const gchar *button_text, //~1618I~//~1A22R~
//                                         gint         response_id) //~1618I~//~1A22R~
//{                                                                  //~1618I~//~1A22R~
//    int ctr;                                                       //~1622R~//~1A22R~
//    GtkWidget *pw;                                                 //~1618I~//~1A22R~
////******************                                               //~1618I~//~1A22R~
//    UTRACEP("gdk_dialog_add_button text=%s,respid=%d\n",button_text,response_id);//~1618I~//~vay0R~
//    pw=(GtkWidget*)dialog;                                         //~1618I~//~1A22R~
//    ctr=pw->jni_btnctr;                                            //~1618I~//~1A22R~
//    pw->jni_btn_text[ctr]=(char*)button_text;                      //~1618R~//~1A22R~
//    pw->jni_btn_respid[ctr]=response_id;                           //~1618I~//~1A22R~
//    pw->jni_btnctr=++ctr;                                          //~1618I~//~1A22R~
//    UTRACEP("gdk_dialog_add_button buttonids=%08x,ctr=%d\n",response_id,ctr);//~1622R~//~vay0R~
//    return NULL;    //not used                                     //~1618R~//~1A22R~
//}                                                                  //~1618I~//~1A22R~
////*************************************************                //~1618I~//~1A22R~
////*wxe_call->uerrmsgboxokcan-->umsgbox2:exit confirmation:MB_OKCANCEL//~1622R~//~1A22R~
////* OKCANCEL is this case only                                     //~1622I~//~1A22R~
////*file:filecopybydnd-->xei3:wxe_dndcopyfile->umsgbox:retry by replace option?:MB_YESNOCANCEL//~1622I~//~1A22R~
////* YESNOCANCEL is this case only                                  //~1622I~//~1A22R~
////*************************************************                //~1622I~//~1A22R~
//gint gtk_dialog_run               (GtkDialog *dialog)              //~1713R~//~1A22R~
//{                                                                  //~1618I~//~1A22R~
//    static jmethodID staticMethod_dialog_run;                      //~1618I~//~1A22R~
//    int ctr=0,btnok=0,btnno=0,btncan=0,respid,ii;                  //~1622R~//~1A22R~
//    gint rc=0;                                                     //~1715R~//~1A22R~
//    GtkWidget *pw;                                                 //~1618I~//~1A22R~
//    char *msg;                                                     //~1618I~//~1A22R~
//    jstring jmsg;                                                  //~1715R~//~1A22R~
//    JNIEnv *penv;                                                  //~1714I~//~1A22R~
////****************************                                     //~1714I~//~1A22R~
//    penv=getThreadEnv();                                           //~1714I~//~1A22R~
//    UTRACEP("gdk_dialog_run env=%p\n",penv);                       //~1714I~//~1A22R~
//    pw=(GtkWidget*)dialog;                                         //~1618I~//~1A22R~
//    if (pw->buttons==MB_OK)    //OK of OKCANCEL                    //~1622R~//~1A22R~
//    {                                                              //~1622I~//~1A22R~
//        ctr++;                                                     //~1622I~//~1A22R~
//        btnok=BTN_OK;                                              //~1622I~//~1A22R~
//    }                                                              //~1622I~//~1A22R~
//    for (ii=0;ii<JNI_WIDGET_MAX_BUTTON;ii++)                       //~1622R~//~1A22R~
//    {                                                              //~1621I~//~1A22R~
//        respid=pw->jni_btn_respid[ii];                             //~1621I~//~1A22R~
//        switch(respid)                                             //~1622R~//~1A22R~
//        {                                                          //~1621I~//~1A22R~
//        case GTK_RESPONSE_YES:                                     //~1622R~//~1A22R~
//        case GTK_RESPONSE_OK:                                      //~1622I~//~1A22R~
//            ctr++;                                                 //~1622I~//~1A22R~
//            btnok=BTN_OK;                                          //~1622I~//~1A22R~
//            break;                                                 //~1622I~//~1A22R~
//        case GTK_RESPONSE_NO:                                      //~1621I~//~1A22R~
//            ctr++;                                                 //~1622I~//~1A22R~
//            btnno=BTN_NO;                                          //~1621I~//~1A22R~
//            break;                                                 //~1621I~//~1A22R~
//        case GTK_RESPONSE_CANCEL:                                  //~1621I~//~1A22R~
//            ctr++;                                                 //~1622I~//~1A22R~
//            btncan=BTN_CAN;                                        //~1621I~//~1A22R~
//            break;                                                 //~1621I~//~1A22R~
//        }                                                          //~1621I~//~1A22R~
//    }                                                              //~1621I~//~1A22R~
//    msg=pw->jni_message;                                           //~1618I~//~1A22R~
//    UTRACEP("gtk_dialog_run btnok=%d,btnno=%d,btncan=%d,msg=%s\n",btnok,btnno,btncan,msg);//~1713R~//~vay0R~
//    jmsg=utf8z2jstring(penv,msg);                                  //~1715R~//~1A22R~
//    C2J_GETMETHODID_GOTOIFERR(penv,dialog_run,"(IIILjava/lang/String;)I");//~1715R~//~1A22R~
//    rc=(gint)C2J_INT(penv,dialog_run,btnok,btnno,btncan,jmsg);//~1715R~//~1718R~//~1A22R~
//ret:                                                               //~1715I~//~1A22R~
//    unrefLocal(penv,jmsg);                                         //~1715R~//~1A22R~
//    return rc;                                                     //~1618I~//~1A22R~
//}                                                                  //~1618I~//~1A22R~
//*************************************************                //~1618I~
void gtk_widget_destroy(GtkWidget *widget)                         //~1618R~
{                                                                  //~1618I~
	UTRACEP("gdk_widget_destroy addr=%p\n",widget);                //~vay0R~
    if (widget)                                                    //~1618I~
		ufree(widget);                                             //~1618R~
}                                                                  //~1618I~
//*************************************************                //~1618I~
GtkWidget *lookup_widget(GtkWidget *Pwidget,char *Pname)           //~1618R~
{                                                                  //~1618I~
    static jmethodID staticMethod_lookup_widget;                   //~1618I~
	jobject view;                                                  //~1618I~
    GtkWidget *pw=NULL;                                            //~1715R~
    JNIEnv *penv;                                                  //~1714I~
    jstring jname;                                                 //~1715I~
//****************************                                     //~1714I~
    penv=getThreadEnv();                                           //~1714I~
	UTRACEP("lookup_widget env=%p,parent=%p,childname=%s",penv,Pwidget,Pname);//~1714R~
    jname=utf8z2jstring(penv,Pname);                               //~1715R~
    C2J_GETMETHODID_GOTOIFERR(penv,lookup_widget,"(Ljava/lang/String;)Landroid/view/View;");//~1717R~
//  view=C2J_OBJ(penv,lookup_widget,jname);           //~1715R~    //~vay0R~
    C2J_OBJ(&view,penv,lookup_widget,jname);                       //~vay0I~
	pw=gtk_widget_new();                                           //~1618R~
    pw->window=view;                                               //~1618R~
ret:                                                               //~1715I~
//	LOGPD("@@@@lookup_widget jname=%p\n",jname);                   //+vay0R~
    unrefLocal(penv,jname);                                        //~1715R~
    return pw;                                                     //~1618R~
}                                                                  //~1618I~
//*************************************************                //~1618I~
void  gtk_menu_item_activate(GtkMenuItem *menu_item)               //~1618I~
{                                                                  //~1618I~
//    static jmethodID staticMethod_menu_item_activate;              //~1618I~//~1929R~
//    JNIEnv *penv;                                                  //~1714I~//~1929R~
////****************************                                     //~1714I~//~1929R~
//    penv=getThreadEnv();                                           //~1714I~//~1929R~
//    UTRACEP("gtk_menu_item_activate env=%p,menuitem=%p\n",penv,menu_item);//~1714R~//~1929R~
//    C2J_GETMETHODID_RETIFERR(penv,menu_item_activate,"(Landroid/view/View;)V");//~1715R~//~1929R~
//    C2J_VOID(penv,menu_item_activate,menu_item);      //~1714R~   //~1718R~//~1719R~//~1929R~
//*so far,no need to activate menuitem(so,always activated)        //~1929I~
    return;                                                        //~1618I~
}                                                                  //~1618I~
//*************************************************                //~1618I~
//*from upostmsg                                                   //~1A05I~
//*************************************************                //~1A05I~
void      gdk_event_put	 		(const GdkEvent *event)            //~1618I~
{                                                                  //~1618I~
    static jmethodID staticMethod_event_put;                       //~1A18R~
    GdkEventClient *pec;                                           //~1A18I~
//    long wparm,lparm;                                              //~1A05R~//~1A18R~
    int msg;                                                      //~1A05R~//~1A18R~
    JNIEnv *penv;                                                  //~1714I~
//****************************                                     //~1714I~
    penv=getThreadEnv();                                           //~1714I~
    pec=(GdkEventClient*)umalloc(sizeof(GdkEventClient));          //~1A18R~
    memcpy(pec,event,sizeof(GdkEventClient));	                   //~1A18R~
    msg=(int)pec->data.l[1];                                       //~1A05R~
	UTRACEP("gtk_event_put msg=%x parm=%x\n",msg,pec);            //~1A05I~//~1A18R~
    C2J_GETMETHODID_RETIFERR(penv,event_put,"(IJ)V");              //~1A18R~
    C2J_VOID(penv,event_put,msg,(LONG)pec);                        //~1A18R~
//    LOGPD("@@@@ gdk_event_put alloc pec=%p\n",pec);              //+vay0R~
    return;                                                        //~1618I~
}                                                                  //~1618I~
//*************************************************                //~1618I~
guint gtk_timeout_add(guint32	       interval,                   //~1618I~
				    GtkFunction	       function,                   //~1618I~
				    gpointer	       data)                       //~1618I~
{                                                                  //~1618I~
    static jmethodID staticMethod_timeout_add;                     //~1618I~
    int timerid/*,func*/;                                          //~vak2R~
    guint handle;                                                  //~1618I~//~1808R~
    JNIEnv *penv;                                                  //~1714I~
//****************************                                     //~1714I~
    penv=getThreadEnv();                                           //~1714I~
	UTRACEP("gtk_timeout_add env=%p,id=%p",penv,data);             //~1714R~
    timerid=(int)data;                                             //~1618I~
//  func=(int)function;                                            //~vak2R~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,0,timeout_add,"(IIJ)I");//~1715R~//~1808R~
//  handle=(guint)C2J_OBJ(penv,timeout_add,timerid,interval,(LONG)function);//~1714R~ //~1718R~//~vay0R~
    C2J_OBJ((jobject*)(&handle),penv,timeout_add,timerid,interval,(LONG)function);//~vay0R~
//    LOGPD("@@@@ gdk_timer add handle=%x\n",handle);              //+vay0R~
    return handle;                                                 //~1618R~
}                                                                  //~1618I~
//*************************************************                //~1618I~
void gtk_timeout_remove(guint	       timeout_handler_id)         //~1618I~
{                                                                  //~1618I~
    static jmethodID staticMethod_timeout_remove;                  //~1618I~
    JNIEnv *penv;                                                  //~1714I~
//****************************                                     //~1714I~
    penv=getThreadEnv();                                           //~1714I~
	UTRACEP("gtk_timeout_remove env=%p\n",penv);                   //~1714R~
    C2J_GETMETHODID_RETIFERR(penv,timeout_remove,"(I)V");          //~1714R~
    C2J_VOID(penv,timeout_remove,timeout_handler_id); //~1714R~    //~1718R~
    return;                                                        //~1618I~
}                                                                  //~1618I~
//*************************************************                //~1618I~
//main view invalidate                                             //~1618I~
//*************************************************                //~1618I~
void gdk_draw_drawable  (GdkDrawable      *drawable,               //~1618I~
			 GdkGC            *gc,                                 //~1618I~
			 GdkDrawable      *src,                                //~1618I~
			 gint              xsrc,                               //~1618I~
			 gint              ysrc,                               //~1618I~
			 gint              xdest,                              //~1618I~
			 gint              ydest,                              //~1618I~
			 gint              width,                              //~1618I~
			 gint              height)                             //~1618I~
{                                                                  //~1618I~
    static jmethodID staticMethod_draw_drawable;                   //~1618I~
    JNIEnv *penv;                                                  //~1714I~
//****************************                                     //~1714I~
    penv=getThreadEnv();                                           //~1714I~
	UTRACEP("gdk_draw_drawable env=%p\n",penv);                    //~1714R~
    C2J_GETMETHODID_RETIFERR(penv,draw_drawable,"()V");            //~1714R~
    C2J_VOIDVOID(penv,draw_drawable);                     //~1714R~    //~1718R~//~1719R~
	UTRACEP("gdk_draw_drawable return\n");                         //~1716I~
    return;                                                        //~1618I~
}                                                                  //~1618I~
//*************************************************                //~1618I~
void gtk_menu_item_set_accel_path(GtkMenuItem *menu_item,          //~1618I~
					       const gchar *accel_path)                //~1618I~
{                                                                  //~1618R~
	UTRACEP("gtk_menu_item_set_accel_path:NOP\n");                 //~vay0R~
    return;                                                        //~1618I~
}                                                                  //~1618I~
//*************************************************                //~1618I~
void	   gtk_accel_map_add_entry	(const gchar		*accel_path,//~1618I~
					 guint			 accel_key,                    //~1618I~
					 GdkModifierType         accel_mods)           //~1618I~
{                                                                  //~1618I~
	UTRACEP("gtk_accell_map_add_entry:NOP\n");                     //~vay0R~
    return;                                                        //~1618I~
}                                                                  //~1618I~
//*************************************************                //~1618I~
gboolean   gtk_accel_map_change_entry	(const gchar		*accel_path,//~1618I~
					 guint			 accel_key,                    //~1618I~
					 GdkModifierType	 accel_mods,               //~1618I~
					 gboolean		 replace)                      //~1618I~
{                                                                  //~1618I~
	UTRACEP("gtk_accell_map_change_entry:NOP\n");                  //~vay0R~
    return 0;                                                      //~1618R~
}                                                                  //~1618I~
//*************************************************                //~1618I~
void gtk_label_set_text(GtkLabel *label,                           //~1618I~
						   const gchar *str)                       //~1618R~
{                                                                  //~1618I~
	UTRACEP("gtk_label_set_text\n");                               //~vay0R~
    return;                                                        //~1618I~
}                                                                  //~1618I~
//*************************************************                //~1618I~
void gtk_label_set_text_with_mnemonic(GtkLabel *label,             //~1618I~
						   const gchar *str)                       //~1618R~
{                                                                  //~1618I~
	UTRACEP("gtk_label_set_text_with_mnemonic\n");                 //~vay0R~
    return;                                                        //~1618I~
}                                                                  //~1618I~
////*************************************************              //~1927R~
//void gtk_widget_set_sensitive(GtkWidget    *widget,              //~1927R~
//                             gboolean      sensitive)            //~1927R~
//{                                                                //~1927R~
//    static jmethodID staticMethod_set_sensitive;                 //~1927R~
//    char *pmenuname;                                             //~1927R~
//    GtkMenuItem *pmi;                                            //~1927R~
//    JNIEnv *penv;                                                //~1927R~
////****************************                                   //~1927R~
//    penv=getThreadEnv();                                         //~1927R~
//    pmi=(GtkMenuItem*)widget;                                    //~1927R~
//    pmenuname=pmi->accel_path;                                   //~1927R~
//    UTRACEP("gdk_set_sensitive env=%p,menu=%s\n",penv,pmenuname);//~1927R~
//    C2J_GETMETHODID_RETIFERR(penv,set_sensitive,"(I)V");         //~1927R~
//    C2J_VOID(penv,set_sensitive,sensitive);           //~1714R~  //~1927R~
//    return;                                                      //~1927R~
//}                                                                //~1927R~
//*************************************************                //~1618I~
void gtk_menu_popup(GtkMenu	       *menu,                          //~1618I~
					   GtkWidget	       *parent_menu_shell,     //~1618I~
					   GtkWidget	       *parent_menu_item,      //~1618I~
					   GtkMenuPositionFunc	func,                  //~1618I~
					   gpointer		data,                          //~1618I~
					   guint		button,                        //~1618I~
					   guint32		activate_time)                 //~1618I~
{                                                                  //~1618I~
    static jmethodID staticMethod_menu_popup;                      //~1618I~
    JNIEnv *penv;                                                  //~1714I~
    int *penabletbl,sz;                                            //~1927I~
    jintArray jia;                                                 //~1927I~
//****************************                                     //~1714I~
    penv=getThreadEnv();                                           //~1714I~
	UTRACEP("gtk_menu_popup env=%p\n",penv);                       //~1714R~
    penabletbl=Spopupmenu_enabletbl;                               //~1927R~
    sz=Spopupmenu_enabletblsz;                                     //~1927R~
    jia=int2jint(penv,penabletbl,sz);                              //~1927I~
    C2J_GETMETHODID_GOTOIFERR(penv,menu_popup,"([I)V");            //~1927R~
    C2J_VOID(penv,menu_popup,jia);                        //~1714R~    //~1718R~//~1927R~
ret:                                                               //~1927I~
//	LOGPD("@@@@menu_popup unref jia=%p\n",jia);                    //+vay0R~
    unrefLocal(penv,jia);                                          //~1927I~
    return;                                                        //~1618I~
}                                                                  //~1618I~
//*************************************************                //~1618I~
guint32 gtk_get_current_event_time(void)                           //~1618I~
{                                                                  //~1618I~
    struct timeb tmb;                                              //~1618I~
    guint32 rc;                                                    //~1618I~
//***************************                                      //~1618I~
	ftime(&tmb);                                                   //~1618I~
    rc=(tmb.time*1000)+tmb.millitm;   //unit:milisec               //~1926R~
//    rc/=10;     //TODO @@@@ test;                                //~1926R~
	UTRACEP("gtk_get_current_event_time milisec=%x\n",rc);             //~1618I~//~1925R~
    return rc;                                                     //~1618I~
}                                                                  //~1618I~
//*************************************************                //~1618I~
void gtk_clipboard_set_text(GtkClipboard *clipboard,               //~1618I~
				       const gchar           *text,                //~1618I~
				       gint                   len)                 //~1618R~
{                                                                  //~1618I~
    static jmethodID staticMethod_clipboard_set_text;              //~1618I~
    char *ptext;                                                   //~1618I~
    int  textlen;                                                  //~1618I~
    jstring jtext;                                                 //~1715I~
    JNIEnv *penv;                                                  //~1714I~
//****************************                                     //~1714I~
    penv=getThreadEnv();                                           //~1714I~
	UTRACEP("gtk_clipboard_set_text env=%p,len=%d\n",penv,len);    //~1926R~
    UTRACED("clipboard_set_text",text,len);                        //~1926I~
    ptext=(char*)text;                                             //~1618I~
    textlen=(int)len;                                              //~1618I~
    jtext=utf82jstring(penv,ptext,textlen);                        //~1715I~
    C2J_GETMETHODID_GOTOIFERR(penv,clipboard_set_text,"(Ljava/lang/String;)V");//~1926R~
    C2J_VOID(penv,clipboard_set_text,jtext);  //~1715R~            //~1926R~
ret:                                                               //~1715I~
//    LOGPD("@@@@ clipboard settext unref jtext=%p\n",jtext);      //+vay0R~
    unrefLocal(penv,jtext);                                        //~1715R~
    return;                                                        //~1618I~
}                                                                  //~1618I~
//*************************************************                //~1618I~
gchar *gtk_clipboard_wait_for_text(GtkClipboard  *clipboard)       //~1618I~
{                                                                  //~1618I~
    static jmethodID staticMethod_clipboard_wait_for_text;         //~1618I~
    char *ptext;                                                   //~1926R~
    jstring pstr;                                                  //~1926R~
//  int  textlen;                                                  //~vak2R~
    JNIEnv *penv;                                                  //~1714I~
//****************************                                     //~1714I~
    penv=getThreadEnv();                                           //~1714I~
	UTRACEP("gtk_clipboard_wait_for_text env=%p\n",penv);          //~1714R~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,NULL,clipboard_wait_for_text,"()Ljava/lang/String;");//~1926R~
//  pstr=C2J_OBJVOID(penv,clipboard_wait_for_text);//~1714R~   //~1718R~//~vay0R~
    C2J_OBJVOID(&pstr,penv,clipboard_wait_for_text);               //~vay0I~
    if (pstr)                                                      //~1926I~
    {                                                              //~1926I~
	    ptext=jstring2char(penv,pstr);                             //~1926I~
//  	textlen=strlen(ptext);                                     //~vak2R~
    }                                                              //~1926I~
    else                                                           //~1926I~
    {                                                              //~1926I~
    	ptext=0;                                                   //~1926I~
//  	textlen=0;                                                 //~vak2R~
    }                                                              //~1926I~
//  UTRACEP("clipboard_wait_for_text textlen=%d\n",textlen);       //~vak2R~
//  UTRACED("clipboard_wait_for_text",ptext,textlen);              //~vak2R~
    return ptext;                                                  //~1926R~
}                                                                  //~1618I~
//*************************************************                //~1618I~
void	gtk_clipboard_request_text	(GtkClipboard *clipboard,      //~1618I~
                                      GtkClipboardTextReceivedFunc callback,//~1618I~
                                      gpointer user_data)          //~1618I~
{                                                                  //~1618I~
	UTRACEP("gtk_clipboard_request_text\n");	//for PRIMARY clipboard;not used//~vay0R~
    return;                                                        //~1618I~
}                                                                  //~1618I~
//*************************************************                //~1620I~
void	gdk_pointer_ungrab     (guint32       time_)               //~1620I~
{}                                                                 //~1620I~
//*************************************************                //~1620I~
void	gdk_keyboard_ungrab    (guint32       time_)               //~1620I~
{}                                                                 //~1620I~
//*************************************************                //~1620I~
gboolean	gdk_pointer_is_grabbed (void)                          //~1620I~
{                                                                  //~1620I~
	return FALSE;                                                  //~1620R~
}                                                                  //~1620I~
//*************************************************                //~1621I~
void	gtk_signal_emit_by_name			(GtkObject	    *object,   //~1621I~
						 const gchar	    *name,                 //~1621I~
						 ...)                                      //~1621I~
{                                                                  //~1621I~
    static jmethodID staticMethod_signal_emit_by_name;             //~1621I~
    JNIEnv *penv;                                                  //~1714I~
//****************************                                     //~1714I~
    penv=getThreadEnv();                                           //~1714I~
	UTRACEP("gtk_signal_emit_by_name env=%p,widget=%p name=%s\n",penv,object,name);//~1714R~
    C2J_GETMETHODID_RETIFERR(penv,signal_emit_by_name,"(Landroid/view/View;[B)V");//~1715R~
    C2J_VOID(penv,signal_emit_by_name,object,name);   //~1714R~    //~1718R~
	return;                                                        //~1621I~
}                                                                  //~1621I~
//*************************************************                //~1621I~
void	gtk_window_set_title	(GtkWindow           *window,      //~1621I~
						const gchar         *title)                //~1621R~
{                                                                  //~1621I~
    static jmethodID staticMethod_set_title;                       //~1621I~
    jstring jtitle;                                                //~1715R~
    JNIEnv *penv;                                                  //~1714I~
//****************************                                     //~1714I~
    penv=getThreadEnv();                                           //~1714I~
	UTRACEP("gtk_window_set_title env=%p,title=%s\n",penv,title);  //~1714R~
    jtitle=utf8z2jstring(penv,(char *)title);                      //~1715R~
    C2J_GETMETHODID_GOTOIFERR(penv,set_title,"(Ljava/lang/String;)V");//~1715R~
    C2J_VOID(penv,set_title,jtitle);                  //~1715R~    //~1718R~
ret:                                                               //~1715I~
//	LOGPD("@@@@gtk_window_set_title jtitle=%p\n",jtitle);          //+vay0R~
    unrefLocal(penv,jtitle);                                       //~1715R~
	return;                                                        //~1621I~
}                                                                  //~1621I~
//*************************************************                //~1621I~
gchar* gtk_file_selection_get_filename(GtkFileSelection *filesel)  //~1621I~
{                                                                  //~1621I~
	UTRACEP("gtk_file_slection_get_filename dummy\n");             //~vay0R~
	return NULL;       //@@@@ not yet implemented                  //~1621I~
}                                                                  //~1621I~
//*************************************************                //~1621I~
void	gdk_window_get_geometry(GdkWindow	  *window,             //~1621I~
					  gint		  *x,                              //~1621I~
					  gint		  *y,                              //~1621I~
					  gint		  *width,                          //~1621I~
					  gint		  *height,                         //~1621I~
					  gint		  *depth)                          //~1621I~
{                                                                  //~1621I~
	UTRACEP("gdk_window_get_geometry ww=%d,hh=%d\n",Mscrwidth,Mscrheight);//~vay0R~
	*x=0;                                                          //~1621I~
	*y=0;                                                          //~1621I~
	*width=Mscrwidth;      //xxemain.h                             //~1621I~
	*height=Mscrheight;                                            //~1621I~
	*depth=1;                                                      //~1621I~
    return;                                                        //~1621I~
}                                                                  //~1621I~
//*************************************************                //~1A18I~
GtkTargetList *gtk_target_list_new(const GtkTargetEntry *targets,guint ntargets)//~1A18R~
{                                                                  //~1A18I~
	return 0;                                                      //~1A18I~
}                                                                  //~1A18I~
//*************************************************                //~1A18I~
GdkDragContext *gtk_drag_begin (GtkWidget         *widget,         //~1A18I~
				GtkTargetList     *targets,                        //~1A18I~
				GdkDragAction      actions,                        //~1A18I~
				gint               button,                         //~1A18I~
				GdkEvent          *event)                          //~1A18R~
{                                                                  //~1A18I~
	UTRACEP("gtk_drag_begin:call gxeview_dragbegin");              //~1A18I~
	gxeview_dragbegin(0/*GdkDragContext*/);                        //~1A18R~
	return 0;                                                      //~1A18I~
}                                                                  //~1A18I~
//*************************************************                //~1A18I~
void     gtk_selection_data_set      (GtkSelectionData     *selection_data,//~1A18I~
				      GdkAtom               type,                  //~1A18I~
				      gint                  format,                //~1A18I~
				      const guchar         *data,                  //~1A18I~
				      gint                  length)                //~1A18I~
{                                                                  //~1A18I~
	char *pc;                                                      //~1A19I~
//***************                                                  //~1A19I~
	UTRACEP("gtk_selection_data_Set data=%s,len=%d\n",data,length);                             //~1A18I~//~1A19R~
    if (length>0)                                                  //~1A19I~
    {                                                              //~1A19I~
    	pc=umalloc(length);                                        //~1A19I~
        memcpy(pc,data,length);                                    //~1A19I~
        Gdndselection=pc;                                          //~1A19I~
        selection_data->data=pc;                                   //~1A21R~
        selection_data->length=length;                             //~1A21R~
        selection_data->format=8; //xxecsub2:uclipboar_getcopyfilesub check//~1A21R~
    }                                                              //~1A19I~
	return;                                                        //~1A18I~
}                                                                  //~1A18I~
//*************************************************                //~1621I~
void gtk_drag_finish   (GdkDragContext *context,                   //~1621I~
			gboolean        success,                               //~1621I~
			gboolean        del,                                   //~1621I~
			guint32         time_)                                 //~1621I~
{                                                                  //~1621M~
	UTRACEP("gtk_drag_finished dummy\n");                          //~vay0R~
	return;       //@@@@ not yet implemented                       //~1621M~
}                                                                  //~1621M~
//*************************************************                //~1621I~
void		  gtk_selection_data_free     (GtkSelectionData *data) //~1621I~
{                                                                  //~1621I~
	UTRACEP("gtk_selection_data_free addr=%p\n",data);             //~vay0R~
    ufree(data);                                                   //~1621I~
	return;                                                        //~1621I~
}                                                                  //~1621I~
//*************************************************                //~1715M~
gchar*  gdk_atom_name   (GdkAtom      atom)                        //~1621I~
{                                                                  //~1621I~
	UTRACEP("gtk_atom_name addr=%p\n",atom);                       //~vay0R~
    return (gchar*)atom;                                           //~1621I~
}                                                                  //~1621I~
//*************************************************                //~1715I~
PangoLayout  *gtk_widget_create_pango_layout  (GtkWidget   *widget,//~1715I~
					       const gchar *text)                      //~1715I~
{                                                                  //~1715I~
    PangoLayout *playout;                                          //~1715I~
	UTRACEP("gtk_widget_cretae_pango_description\n");              //~vay0R~
	playout=(PangoLayout*)umalloc(sizeof(PangoLayout));            //~1715I~
	memset(playout,0,sizeof(PangoLayout));                         //~1715I~
    return playout;                                                //~1715R~
}                                                                  //~1715I~
//**************************************************************   //~1927I~
//*save popupmenu enable status for later gtk_menu_popup           //~1927I~
//**************************************************************   //~1927I~
int jnig_upopupmenu_menuitem_enable(int Ptblsz,int Pidx,int Penable)//~1927I~
{                                                                  //~1927I~
	if (!Spopupmenu_enabletbl)                                     //~1927R~
    {                                                              //~1927I~
    	Spopupmenu_enabletbl=malloc((Ptblsz+1)*sizeof(int));       //~1927R~
    	Spopupmenu_enabletblsz=Ptblsz;                             //~1927R~
    }                                                              //~1927I~
    UTRACEP("jnig_upoupmenu_menuitem_enable idx=%d,enable=%d\n",Pidx,Penable);//~1930R~
    Spopupmenu_enabletbl[Pidx]=Penable;                            //~1927R~
    return Penable;                                                //~1927I~
}                                                                  //~1927I~
//**************************************************************   //~1A06I~
char *gnome_vfs_get_uri_from_local_path(const char *local_full_path)//~1A06I~
{                                                                  //~1A06I~
//    static char uri[_MAX_PATH];                                  //~1A06I~
//    sprintf(uri,"file://%s",local_full_path);                    //~1A06I~
//    UTRACEP("gnome_vfs_get_uri_from_local_path in=%s,out=%s\n",local_full_path,uri);//~1A06I~
//    return uri;                                                  //~1A06I~
    static jmethodID staticMethod_file2url;                        //~1A06I~
    char *puri;                                                    //~1A06I~
    jstring jpath,juri;                                            //~1A06I~
    JNIEnv *penv;                                                  //~1A06I~
//****************************                                     //~1A06I~
    penv=getThreadEnv();                                           //~1A06I~
	UTRACEP("gnome_vfs_get_uri_from_local_path path=%s\n",local_full_path);//~1A06I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,NULL,file2url,"(Ljava/lang/String;)Ljava/lang/String;");//~1A06I~
    jpath=utf8z2jstring(penv,(char *)local_full_path);             //~1A06I~
//  juri=C2J_OBJ(penv,file2url,jpath);                             //~vay0R~
    C2J_OBJ(&juri,penv,file2url,jpath);                            //~vay0I~
    if (juri)                                                      //~1A06I~
	    puri=jstring2char(penv,juri);                              //~1A06I~
    else                                                           //~1A06I~
    	puri=0;                                                    //~1A06I~
    UTRACEP("file2uri puri=%s\n",puri);                            //~1A06I~
    return puri;                                                   //~1A06I~
}                                                                  //~1A06I~
char *gnome_vfs_get_mime_type(const char *text_uri)                //~1A06I~
{                                                                  //~1A06I~
//*******************                                              //~1A06I~
//    len=strlen(text_uri);                                        //~1A06I~
//    pc=umemrchr(text_uri,'/',len);                               //~1A06I~
//    if (pc)                                                      //~1A06I~
//        pc=umemrchr(pc,'.',strlen(pc));                          //~1A06I~
//    if (pc)                                                      //~1A06I~
//        pc++;                                                    //~1A06I~
//    UTRACEP("gnome_vfs_get_mime_type in=%s,out=%s\n",text_uri,pc);//~1A06I~
//    return pc;                                                   //~1A06I~
    static jmethodID staticMethod_getMimeType;                     //~1A06I~
    char *pmimetype;                                               //~1A06I~
    jstring jmimetype,juri;                                        //~1A06I~
    JNIEnv *penv;                                                  //~1A06I~
//****************************                                     //~1A06I~
    penv=getThreadEnv();                                           //~1A06I~
	UTRACEP("gnome_vfs_get_mime_type url=%s\n",text_uri);          //~1A06I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,NULL,getMimeType,"(Ljava/lang/String;)Ljava/lang/String;");//~1A06I~
    juri=utf8z2jstring(penv,(char *)text_uri);                     //~1A06I~
//  jmimetype=C2J_OBJ(penv,getMimeType,juri);                      //~vay0R~
    C2J_OBJ(&jmimetype,penv,getMimeType,juri);                     //~vay0I~
    if (jmimetype)                                                 //~1A06I~
	    pmimetype=jstring2char(penv,jmimetype);                    //~1A06I~
    else                                                           //~1A06I~
    	pmimetype=0;                                               //~1A06I~
    UTRACEP("getmimetype mimetype=%s\n",pmimetype);                //~1A06I~
    return pmimetype;                                              //~1A06I~
}                                                                  //~1A06I~
GnomeVFSMimeApplication *gnome_vfs_mime_get_default_application(const char *mime_type)//~1A06R~
{                                                                  //~1A06I~
    static jmethodID staticMethod_getMimeApplication;              //~1A06I~
//  char *pmimetype;                                               //~1A12R~
    jstring jmimetype;                                             //~1A06R~
    JNIEnv *penv;                                                  //~1A06I~
static GnomeVFSMimeApplication Sapp;                               //~1A06I~
	int rc;                                                        //~1A06I~
//****************************                                     //~1A06I~
    penv=getThreadEnv();                                           //~1A06I~
	UTRACEP("gnome_vfs_mime_get_default_application mimetype=%s\n",mime_type);//~1A06I~
    UstrncpyZ(Smimetype,mime_type,sizeof(Smimetype));              //~1A06R~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,NULL,getMimeApplication,"(Ljava/lang/String;)I");//~1A06I~
    jmimetype=utf8z2jstring(penv,(char *)mime_type);               //~1A06I~
//  rc=C2J_INT(penv,getMimeApplication,jmimetype);                 //~vay0R~
    C2J_INT(&rc,penv,getMimeApplication,jmimetype);                //~vay0I~
    if (!rc)    //no application(rc:appl count)                    //~1A19I~
        return 0;                                                  //~1A19I~
    memset(&Sapp,0,sizeof(Sapp));                                  //~1A06I~
    Sapp.expects_uris=GNOME_VFS_MIME_APPLICATION_ARGUMENT_TYPE_URIS;    //request uri//~1A06R~//~1A19R~
    Sapp.command=Smimetype; //parm of ushellexec-->ushellexecsub//~1A06R~//~1A19R~
    UTRACEP("getmimeApplication rc=%d\n",rc);                      //~1A06I~
    return &Sapp;                                                  //~1A06I~
}                                                                  //~1A06I~
GnomeVFSMimeApplication *gnome_vfs_mime_get_default_application_dataandtype(char *Puri,char *mime_type)//~1A19I~
{                                                                  //~1A19I~
    static jmethodID staticMethod_getMimeApplication_dataandtype;  //~1A19I~
    jstring jmimetype,juri;                                        //~1A19I~
    JNIEnv *penv;                                                  //~1A19I~
static GnomeVFSMimeApplication Sapp;                               //~1A19I~
	int rc;                                                        //~1A19I~
//****************************                                     //~1A19I~
    penv=getThreadEnv();                                           //~1A19I~
	UTRACEP("gnome_vfs_mime_get_default_application mimetype=%s,uri=%s\n",mime_type,Puri);//~1A19I~
    UstrncpyZ(Smimetype,mime_type,sizeof(Smimetype));              //~1A19I~
    C2J_GETMETHODID_RETIFERR_WITHRC(penv,NULL,getMimeApplication_dataandtype,"(Ljava/lang/String;Ljava/lang/String;)I");//~1A19R~
    jmimetype=utf8z2jstring(penv,(char *)mime_type);               //~1A19I~
    juri=utf8z2jstring(penv,(char *)Puri);                         //~1A19I~
//  rc=C2J_INT(penv,getMimeApplication_dataandtype,juri,jmimetype);//~vay0R~
    C2J_INT(&rc,penv,getMimeApplication_dataandtype,juri,jmimetype);//~vay0I~
    if (!rc)    //no application(rc:appl count)                    //~1A19I~
        return 0;                                                  //~1A19I~
    memset(&Sapp,0,sizeof(Sapp));                                  //~1A19I~
    Sapp.expects_uris=GNOME_VFS_MIME_APPLICATION_ARGUMENT_TYPE_URIS;    //request uri//~1A19I~
    Sapp.command=Smimetype; //parm of ushellexec-->ushellexecsub   //~1A19I~
    UTRACEP("getmimeApplication_dataandtype rc=%d\n",rc);          //~1A19I~
    return &Sapp;                                                  //~1A19I~
}                                                                  //~1A19I~
GnomeVFSMimeActionType gnome_vfs_mime_get_default_action_type(const char              *mime_type)//~1A06R~
{                                                                  //~1A06I~
	return GNOME_VFS_MIME_ACTION_TYPE_NONE;	//err                  //~1A06I~
}                                                                  //~1A06I~
