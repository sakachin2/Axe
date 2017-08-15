//*CID://+vb10R~: update#=  103;                                   //+vb10R~
//*****************************************************************//~vay6I~
//vb10 2014/12/08 (Asgts:1A4x) (Bug)Clipboard (String)item.getText() may cause invalid cast exception; .toString() is valid//+vb10I~
//vay6:141122 (Axe)text.ClipboardManager was deplicated and content.Clipboardmanager is the alternative//~vay6I~
//*****************************************************************//~vay6I~
package com.xe.Axe;                                                //~@@@@I~

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
//import android.text.ClipboardManager;                            //~vay6R~
import android.content.ClipboardManager;                         //~vay6I~

//*****************************************************************//~1606I~
                                                                   //~1606I~
public class Axecsub2                                              //~1622R~//~1718R~
{                                                                  //~1606I~//~1718R~
//  static ClipboardManager clipboardManager;                      //~vay6R~
    static Object           clipboardManager;                      //~vay6I~
/////////////////////////////////////////////////////////////////////////////////~1606I~//~1718R~
////*********************                                            //~1620I~//~1718R~
//    public static void usetmonospace(int Popt,int[] Ppdata,byte[] Ppdbcs,int Plen,int Pdbcslen,int Pcellw,int[]Ppoffsettb)//~1622R~//~1718R~
//    {                                                              //~1620I~//~1718R~
//        byte[] pdbcs;                                              //~1620I~//~1718R~
//        int [] pdata;                                              //~1620I~//~1718R~
//        int ii;                                                    //~1620I~//~1718R~
//        int dbcssz;                                                //~1620I~//~1718R~
//        int oldx,wwpango,w0ctr=0,glyphctr=0,combspan=0,wwtotal=0;  //~1620I~//~1718R~
//                                                                   //~1620I~//~1718R~
//        float[] pgeom_width;                                       //~1620I~//~1718R~
//        int[] pgeom_x_offset;                                      //~1620I~//~1718R~
//        String textstr;                                            //~1620I~//~1718R~
//        boolean swtoperr=false,swutf8data;                         //~1620I~//~1718R~
//        int pgeomlast=0,iipdbcs;                                   //~1620I~//~1718R~
//        float oldw,neww;                                           //~1620I~//~1718R~
//        Paint paint;                                               //~1620I~//~1718R~
//    //******************************                               //~1620I~//~1718R~
//        paint=AxeG.axeScreen.paintText;                                           //~1620I~//~1718R~
//        swutf8data=(Popt & AxeJNIdef.USMSO_DDFMT)!=0;   //scr data is dd fmt,pdata is not lc,dont access//~1620R~//~1718R~
//        if (Dump.Y) Dump.println("usetmonospace opt="+Popt+"len="+Plen);//~1620I~//~1718R~
//        if (Dump.Y) Dump.dumpucs("usetmonospace data",Ppdata);   //~1718R~
//        textstr=new String(Ppdata,0,Plen);                         //~1620I~//~1718R~
//        pgeom_width=new float[Plen];                               //~1620I~//~1718R~
//        paint.getTextWidths(textstr,pgeom_width);                  //~1620I~//~1718R~
//        pgeom_x_offset=Ppoffsettb;                                 //~1620I~//~1718R~
//        Arrays.fill(pgeom_x_offset,0);                             //~1620I~//~1718R~
//      pdata=Ppdata;                                                //~1620I~//~1718R~
//      pdbcs=Ppdbcs;                                                //~1620I~//~1718R~
//        wwpango=Pcellw;                                            //~1620R~//~1718R~
//        for (ii=0,iipdbcs=0;ii<Plen && iipdbcs<Pdbcslen;ii++)      //~1620I~//~1718R~
//        {                                                          //~1620I~//~1718R~
//            glyphctr++;                                            //~1620I~//~1718R~
//    //      pgeom=&(pglyphinfo->geometry);                         //~1620I~//~1718R~
//    //      pgeomlast=pgeom;                                       //~1620I~//~1718R~
//            pgeomlast=ii;                                          //~1620I~//~1718R~
//    //      oldw=pgeom->width;                                     //~1620I~//~1718R~
//            oldw=pgeom_width[ii];                                  //~1620I~//~1718R~
//    //      oldx=pgeom->x_offset;                                  //~1620I~//~1718R~
//            oldx=0;                                                //~1620I~//~1718R~
//    //      oldy=pgeom->y_offset;                                  //~1620I~//~1718R~
////  if (Dump.Y) Dump.println("geometry ii="+ii+"offs_x="+pgeom_x_offset[ii]+",width="+pgeom_width[ii]+",cellw="+Pcellw);//~1718R~
//            dbcssz=1;   //sbcs                                     //~1620I~//~1718R~
//    //      if ((swutf8data||*pdata>=0x80)                         //~1620I~//~1718R~
//            if ((swutf8data||pdata[ii]>=0x80)                      //~1620I~//~1718R~
//    //*after ucs dbcsid is converted to locale dbcsid              //~1620I~//~1718R~
//    //      &&  *pdbcs==UDBCSCHK_DBCS1ST                           //~1620I~//~1718R~
//            &&  pdbcs[iipdbcs]==AxeJNIdef.UDBCSCHK_DBCS1ST         //~1620I~//~1718R~
//    //      &&  pdbcs+(dbcssz=XESUB_DBCSSPLITCTR(pdbcs,(int)((ULONG)pdbcse-(ULONG)pdbcs),0))<=pdbcse//~1620I~//~1718R~
//            )                                                      //~1620I~//~1718R~
//            {                                                      //~1620I~//~1718R~
//                neww=                                              //~1620I~//~1718R~
//                pgeom_width[ii]=(Pcellw+Pcellw);                   //~1620R~//~1718R~
//                if ((Popt & AxeJNIdef.USMSO_WWSCRPRT)!=0)  //wysiwig print//~1620R~//~1718R~
//                    pgeom_width[ii]=(Pcellw*dbcssz);   //for dbcs padding space//~1620R~//~1718R~
//    //          pdata+=dbcssz; //print width is 2 column but data is 3-4 byte//~1620I~//~1718R~
//                ii+=dbcssz-2; //ucs char count + padding           //~1620I~//~1718R~
//    //          pdbcs+=dbcssz; //after 3/4 byte dbcs "." is padded by csublocale2utfscr//~1620I~//~1718R~
//                iipdbcs+=dbcssz; //after 3/4 byte dbcs "." is padded by csublocale2utfscr//~1620I~//~1718R~
//            }                                                      //~1620I~//~1718R~
//            else                                                   //~1620I~//~1718R~
//            {                                                      //~1620I~//~1718R~
//                neww=                                              //~1620I~//~1718R~
//                pgeom_width[ii]=Pcellw;                            //~1620R~//~1718R~
//    //          pdata++;                                           //~1620I~//~1718R~
//    //          pdbcs++;                                           //~1620I~//~1718R~
//                iipdbcs++;                                         //~1620I~//~1718R~
//            }                                                      //~1620I~//~1718R~
//            if (oldw!=0)                                           //~1620I~//~1718R~
//    //          pgeom->x_offset=(neww-oldw)/2;                     //~1620I~//~1718R~
//                pgeom_x_offset[ii]=(int)(neww-oldw)/2;             //~1620I~//~1718R~
//            else                                                   //~1620I~//~1718R~
//                pgeom_x_offset[ii]=0;                              //~1620I~//~1718R~
//            if ((Popt & AxeJNIdef.USMSO_COMBINEOF)!=0) //1 column for each combining char//~1620R~//~1718R~
//            {                                                      //~1620I~//~1718R~
//                if (oldw==0)  //width=0                            //~1620I~//~1718R~
//                {                                                  //~1620I~//~1718R~
//    //              pgeom->width=wwpango;                          //~1620I~//~1718R~
//                    pgeom_width[ii]=wwpango;                       //~1620I~//~1718R~
//                    while (oldx<=0) { oldx+=wwpango; }             //~1620I~//~1718R~
//    //              pgeom->x_offset=oldx;                          //~1620I~//~1718R~
//                    pgeom_x_offset[ii]=oldx;                       //~1620I~//~1718R~
//                    w0ctr++;                                       //~1620I~//~1718R~
//                }                                                  //~1620I~//~1718R~
//                else                                               //~1620I~//~1718R~
//                if (w0ctr!=0)  //after combining char              //~1620I~//~1718R~
//                {                                                  //~1620I~//~1718R~
//                    w0ctr=0;                                       //~1620I~//~1718R~
//                }                                                  //~1620I~//~1718R~
//            }                                                      //~1620I~//~1718R~
//            else                                                   //~1620I~//~1718R~
//            if ((Popt & AxeJNIdef.USMSO_COMBINEON)!=0) //combine width 0 char//~1620R~//~1718R~
//            {                                                      //~1620I~//~1718R~
//                if (oldw==0)  //width=0                            //~1620I~//~1718R~
//                {                                                  //~1620I~//~1718R~
//    //              pgeom->x_offset=oldx;   //keep original        //~1620I~//~1718R~
//                    pgeom_x_offset[ii]=oldx;   //keep original     //~1620I~//~1718R~
//    //              pgeom->width=0; //keep original                //~1620I~//~1718R~
//                      pgeom_width[ii]=0;                           //~1620I~//~1718R~
//                    if (w0ctr==0) //combine start                  //~1620I~//~1718R~
//                    {                                              //~1620I~//~1718R~
//                        combspan=0;                                //~1620I~//~1718R~
//                        swtoperr=(glyphctr==1); //err if str top is combine chae//~1620I~//~1718R~
//                        if (swtoperr)                              //~1620I~//~1718R~
//                        {                                          //~1620I~//~1718R~
//    //                      pgeom->x_offset+=wwpango;   //offset from next cell right boundary//~1620I~//~1718R~
//                            pgeom_x_offset[ii]+=wwpango;   //offset from next cell right boundary//~1620I~//~1718R~
//    //                      pgeom->width=wwpango;                  //~1620I~//~1718R~
//                            pgeom_width[ii]=wwpango;               //~1620I~//~1718R~
//                        }                                          //~1620I~//~1718R~
//                        else                                       //~1620I~//~1718R~
//                            combspan=wwpango;   //span to skip to net no combining char//~1620I~//~1718R~
//                    }                                              //~1620I~//~1718R~
//                    else    //2nd combining                        //~1620I~//~1718R~
//                    {                                              //~1620I~//~1718R~
//                        combspan+=wwpango;  //span to next noncombining char//~1620I~//~1718R~
//                        if (swtoperr)                              //~1620I~//~1718R~
//    //                      pgeom->x_offset+=wwpango;              //~1620I~//~1718R~
//                            pgeom_x_offset[ii]+=wwpango;           //~1620I~//~1718R~
//                    }                                              //~1620I~//~1718R~
//                    w0ctr++;                                       //~1620I~//~1718R~
//                }                                                  //~1620I~//~1718R~
//                else    //not combining char                       //~1620I~//~1718R~
//                if (w0ctr!=0)  //next of non combining             //~1620I~//~1718R~
//                {                                                  //~1620I~//~1718R~
//    //              pgeom->width=combspan+wwpango;  //for width0 and itself//~1620I~//~1718R~
//                    pgeom_width[ii]=combspan+wwpango;  //for width0 and itself//~1620I~//~1718R~
//    //              pgeom->x_offset=pgeom->width-neww+(neww-oldw)/2;//~1620I~//~1718R~
//                    pgeom_x_offset[ii]=(int)(pgeom_width[ii]-neww+(neww-oldw)/2);//~1620I~//~1718R~
//                    w0ctr=0;                                       //~1620I~//~1718R~
//                }                                                  //~1620I~//~1718R~
//            }                                                      //~1620I~//~1718R~
//            pgeom_x_offset[ii]+=wwtotal;    //set offset from top for AxeScreen//~1718R~
////  if (Dump.Y) Dump.println("geometry new glyphctr="+glyphctr+",width="+pgeom_width[ii]+",offsx="+pgeom_x_offset[ii]+",combspan="+combspan+",swtoperr="+swtoperr);//~1718R~
//            wwtotal+=pgeom_width[ii];                              //~1620I~//~1718R~
//        }                                                          //~1620I~//~1718R~
//    //    tmp_list=tmp_list->next;                                 //~1620I~//~1718R~
//    //  }//runs                                                    //~1620I~//~1718R~
//        if (glyphctr<Plen   //ligatured occured                    //~1620I~//~1718R~
//        &&  glyphctr!=0                                            //~1620I~//~1718R~
//        &&  (Popt & AxeJNIdef.USMSO_COMBINESP)!=0  //add space whe ligture occued//~1620R~//~1718R~
//        )                                                          //~1620I~//~1718R~
//        {                                                          //~1620I~//~1718R~
//    //      pgeomlast->width=Plen*wwpango-wwtotal;                 //~1620I~//~1718R~
//            pgeom_width[pgeomlast]=Plen*wwpango-wwtotal;           //~1620I~//~1718R~
//            if (Dump.Y) Dump.println("geometry last width after ligature width="+pgeom_width[pgeomlast]+",glyphctr="+glyphctr+",len="+Plen);//~1718R~
//        }                                                          //~1620I~//~1718R~
//    //    pango_layout_iter_free (iter);                           //~1620I~//~1718R~
//        if (Dump.Y) Dump.dump("usetmonospace offsettb",Ppoffsettb);//~1718R~
//        return;                                                    //~1620I~//~1718R~
//    }//usetmonospace                                               //~1620I~//~1718R~
    //===============================================================================//~1620I~
    public static int usetmonospace_ligature(int Popt,int[] Ppdata,int Plen,int Pcellw)//~1622R~
    {                                                              //~1620I~
        float textstrsz;                                           //~1620I~
        Paint paint;                                               //~1620I~
    //******************************                               //~1620I~
        paint=AxeG.axeScreen.paintText;                                           //~1620I~
        String textstr=new String(Ppdata,0,Plen);                  //~1620I~
        textstrsz=paint.measureText(textstr);                      //~1620I~
        return (int)textstrsz;                                     //~1620I~
    }                                                              //~1620I~
//***************************************************************************//~1716I~
//* get font metrics                                               //~1716I~
//set monospace sw 0:none,1:monospace,2:sbcs only mnonospace       //~1716I~
//***************************************************************************//~1716I~
public static FontMetrics ugetfontmetrics(Font Pfont)              //~1718R~
{                                                                  //~1716I~
	 FontMetrics pfm;                                         //~1716I~
    int wch,wdz,fw,fh;                                     //~1716I~
//*******************************                                  //~1716I~
    if (Dump.Y) Dump.println("ugetfontmetrics font="+Pfont);       //~1716I~
    pfm=FontMetrics.getFontMetrics(Pfont);                                     //~1716R~
//  asc=pango_font_metrics_get_ascent(pfm);                        //~1716R~
//    asc=pfm.getAscent();                                           //~1716I~
//  des=pango_font_metrics_get_descent(pfm);                       //~1716R~
//    des=pfm.getDescent();                                          //~1716I~
//  wch=pango_font_metrics_get_approximate_char_width(pfm);        //~1716R~
    wch=pfm.getAvWidth();                                          //~1716I~
//  wdz=pango_font_metrics_get_approximate_digit_width(pfm);       //~1716R~
    wdz=pfm.getAvDigitWidth();                                       //~1716I~
    fw=wdz;            //char_width is affected by dbcs(double width)//~1716I~
//  *Ppwidth=PANGO_PIXELS(fw);                                     //~1716R~
//  Gxeh.Mfm_fontwidth=fw;	//output to jnic2j                                                   //~1716I~//~1822R~
    fh=pfm.getHeight();//asc+des;                                                    //~1716I~
//  *Ppheight=PANGO_PIXELS(fh);                                    //~1716R~
//  Gxeh.Mfm_fontheight=fh;                                                   //~1716I~//~1822R~
//  if (Ppmonospace)                                               //~1716R~
//  	*Ppmonospace=ufontismonospace(Pfontdesc,*Ppwidth);         //~1716R~
//  Gxeh.Mfm_monospace=ufontismonospace(pfm,fh);                       //~1716I~//~1822R~
    if (Dump.Y) Dump.println("metrics char-ww="+wch+",digit-ww="+wdz+",avww="+wch+",hh="+fh);//~1716R~//~1718R~
    return pfm;                                                    //~1718R~
}//ugetfontmetrics                                                 //~1716I~
//***************************************************************************//~1716I~
//* chk monospace:pango_font_family_is_monospace is not supported pango 1.0//~1716I~
//***************************************************************************//~1716I~
public static int ufontismonospace(FontMetrics Ppfm,int Pfontw)    //~1716I~
{                                                                  //~1716I~
    int monospace=0;                                               //~1716I~
//************************************                             //~1716I~
	int w1=Ppfm.stringWidth("WW");                                 //~1716I~
	if (Dump.Y) Dump.println("axecsub2 ismonospace WW="+w1);       //~1802I~
	int w2=Ppfm.stringWidth("  ");                                 //~1716I~
	if (Dump.Y) Dump.println("axecsub2 ismonospace 2space="+w2);   //~1802I~
	int w3=Ppfm.stringWidth("fi");                                 //~1724I~
	if (Dump.Y) Dump.println("axecsub2 ismonospace fi="+w3);       //~1802I~
	int w4=Ppfm.stringWidth("if");                                 //~1724I~
	if (Dump.Y) Dump.println("axecsub2 ismonospace if="+w4);       //~1802I~
    if (w3!=w4)                                                    //~1724I~
    	monospace=-1;         //@@@@ actualy no ligature support yet;complex text is drawn by the other unicode//~1724R~
    else                                                           //~1724I~
    if (w1==w2)
    	monospace=1;    	
    return monospace;                                              //~1716I~
}//ufontismonospace                                                //~1716I~
//***********************************************************      //~1926I~
//*Clipboard                                                       //~1926I~
//***********************************************************      //~1926I~
//	public static ClipboardManager clipboard_get()                 //~vay6R~
  	public static Object           clipboard_get()                 //~vay6I~
	{                                                              //~1926I~
		if (clipboardManager==null)                                //~1926I~
//  		clipboardManager=(ClipboardManager) AxeG.context.getSystemService(Context.CLIPBOARD_SERVICE);//~vay6R~
    		getcm();                                               //~vay6I~
		if (Dump.Y) Dump.println("axecsub2 gtk_clipboard_get cm="+clipboardManager);//~1926I~
                                                                   //~1926I~
        return clipboardManager;                                   //~1926I~
	}                                                              //~1926I~
//***********************************************************      //~1926I~
	public static String clipboard_wait_for_text()                 //~1926I~
	{                                                              //~1926I~
//        String str;                                              //~vay6R~
//        if (!clipboardManager.hasText())                         //~vay6R~
//            str=null;                                            //~vay6R~
//        else                                                     //~vay6R~
//            str=clipboardManager.getText().toString();           //~vay6R~
//        return str;                                              //~vay6R~
        if (AxeG.osVersion>=AxeG.HONEYCOMB)  //android3                //~vay6I~
 			return getContents_V11();                              //~vay6I~
        else                                                       //~vay6I~
			return getContents_deprecated();                       //~vay6I~
	}                                                              //~1926I~
//***********************************************************      //~1926I~
	public static void clipboard_set_text(String Pstr)             //~1926I~
	{                                                              //~1926I~
//  	clipboardManager.setText(Pstr);                            //~vay6R~
        if (AxeG.osVersion>=AxeG.HONEYCOMB)  //android3                //~vay6I~
            setContents_V11(Pstr);                                 //~vay6I~
        else                                                       //~vay6I~
            setContents_deprecated(Pstr);                          //~vay6I~
	}                                                              //~1926I~
//********                                                         //~vay6I~
    private static void getcm()                                           //~vay6I~
    {                                                              //~vay6I~
        if (AxeG.osVersion>=AxeG.HONEYCOMB)  //android3              //~vay6I~
			getcm_V11();                                           //~vay6I~
        else                                                       //~vay6I~
			getcm_deprecated();                                    //~vay6I~
    }                                                              //~vay6I~
    //*******************************************************************//~vay6I~
    @SuppressWarnings("deprecation")                               //~vay6I~
    private static void getcm_deprecated()                                //~vay6I~
    {                                                              //~vay6I~
		android.text.ClipboardManager cm=(android.text.ClipboardManager)AxeG.main.getSystemService(Context.CLIPBOARD_SERVICE);//~vay6I~
        clipboardManager=cm;                                       //~vay6I~
    }                                                              //~vay6I~
	//********                                                     //~vay6I~
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)                      //~vay6I~
    private static void getcm_V11()                                       //~vay6I~
    {                                                              //~vay6I~
    	ClipboardManager cm=(ClipboardManager)AxeG.main.getSystemService(Context.CLIPBOARD_SERVICE);//~vay6I~
        clipboardManager=cm;                                       //~vay6I~
    }                                                              //~vay6I~
    //*******************************************************************//~vay6I~
    @SuppressWarnings("deprecation")                               //~vay6I~
	public static String getContents_deprecated()                         //~vay6I~
	{                                                              //~vay6I~
        String str;                                                //~vay6I~
		android.text.ClipboardManager cm=(android.text.ClipboardManager)clipboardManager;//~vay6I~
        if (!cm.hasText())                                         //~vay6I~
        {                                                          //~vay6I~
            str=null;                                              //~vay6I~
        	if (Dump.Y) Dump.println("Clipboard deprecated getText:null");//~vay6I~
        }                                                          //~vay6I~
        else                                                       //~vay6I~
        {                                                          //~vay6I~
            str=cm.getText().toString();                           //~vay6I~
        	if (Dump.Y) Dump.println("Clipboard deprecated getText:"+str);//~vay6I~
        }                                                          //~vay6I~
        return str;                                                //~vay6I~
	}                                                              //~vay6I~
    //*******                                                      //~vay6I~
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)                      //~vay6I~
	private static String getContents_V11()                               //~vay6I~
	{                                                              //~vay6I~
    	String str;                                                //~vay6I~
    	ClipData.Item item;                                        //~vay6I~
		ClipboardManager cm=(ClipboardManager)clipboardManager;    //~vay6I~
        if (cm.hasPrimaryClip())                                   //~vay6I~
        {                                                          //~vay6I~
    		item=cm.getPrimaryClip().getItemAt(0);                 //~vay6I~
//          str=(String)item.getText();                            //+vb10R~
            str=item.getText().toString();                         //+vb10I~
	        if (Dump.Y) Dump.println("Clipboard getTextv11:"+str); //~vay6I~
        }                                                          //~vay6I~
        else                                                       //~vay6I~
        {                                                          //~vay6I~
        	str=null;                                              //~vay6I~
	        if (Dump.Y) Dump.println("Clipboard getTextv11:null"); //~vay6I~
        }                                                          //~vay6I~
		return str;                          //~vay6I~
	}                                                              //~vay6I~
    //*******************************************************************//~vay6I~
    @SuppressWarnings("deprecation")                               //~vay6I~
	private static void setContents_deprecated(String Pstr)               //~vay6I~
    {                                                              //~vay6I~
		android.text.ClipboardManager cm=(android.text.ClipboardManager)clipboardManager;//~vay6I~
		cm.setText(Pstr);                                          //~vay6I~
	    if (Dump.Y) Dump.println("Clipboard setText deprecated:"+Pstr);//~vay6I~
    }                                                              //~vay6I~
    //*********                                                    //~vay6I~
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)                      //~vay6I~
	private static void setContents_V11(String Pstr)                      //~vay6I~
	{                                                              //~vay6I~
    	ClipData.Item item=new ClipData.Item(Pstr);                //~vay6I~
        String[] mymetype=new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN};//~vay6I~
        ClipData cd=new ClipData("data",mymetype,item);            //~vay6I~
		ClipboardManager cm=(ClipboardManager)clipboardManager;    //~vay6I~
		cm.setPrimaryClip(cd);                                     //~vay6I~
	    if (Dump.Y) Dump.println("Clipboard setTextv11:"+Pstr);    //~vay6I~
	}                                                              //~vay6I~
}//class                                                           //~1716R~
