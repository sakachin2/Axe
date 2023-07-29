//*CID://+vc53R~: update#=  204;                                   //+vc53R~
//*****************************************************************//~vai6I~
//vc53 2023/06/12 java error;switch-case requres constant          //+vc53I~
//vc2S 2020/09/12 add ruler width option                           //~vc2SI~
//vc2Q 2020/09/08 change font size by pinch action                 //~vc2QI~
//vc2B 2020/08/12 Help  by file                                    //~vc2BI~
//vai6:130526 (Axe)ucstbl recreate only when fontname changed(not delete when size/style changed) for performance//~vai6I~
//*****************************************************************//~vai6I~
package com.xe.Axe;                                                //~@@@@I~

import android.graphics.Color;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import com.ahsv.utils.Utils;
//*****************************************************************//~1606I~
//output option                                                    //~1727I~
//        Gxeh.Mrulermode                                          //~1727I~//~1803R~
//        Gxeh.Museact                                             //~1727I~
//        Gxeh.Mfreecsr                                            //~1727I~
//        Gxeh.Mbeep                                               //~1727I~
//        Gxeh.Mqexit                                              //~1727I~
//*                                                                //~1727I~
//        Gxeh.Mstyle                                              //~1727I~
////      Gxeh.Mfontsize                                           //~1822R~
//        Gxeh.Mfontheight,Gxeh.Mfontwidth                         //~1822I~
//        Gxeh.Mfontstyle                                          //~1727I~
//        Gxeh.Mcellw                                              //~1727I~
//        Gxeh.Mcellh                                              //~1727I~
//        Gxeh.Mligature                                           //~1803I~
//*                                                                //~1727I~
//        Gxeh.Mbgcolor                                            //~1727I~
//        Gxeh.Mrulercolor                                         //~1727I~
                                                                   //~1606I~
public class Axegxedlg extends AxeDialog                           //~1725I~
    implements SliderListener,EditTextListener,AxeSpinnerI,RadioButtonsListener//~1731R~
{                                                                  //~1606I~
///////////////////////////////////////////////////////////////////////////////~1606I~
//**********************************************************************//~1725I~
	private static final String KEY_FONTSIZE="FontSize";              //~1A02I~
                                                                   //~1A02I~
	private static final int LAYOUT_SETUPFONT=R.layout.dialogsetupfont;//~1727R~
	private static final int LAYOUT_SETUPCOLOR=R.layout.dialogsetupcolor;//~1726R~
	private static final int LAYOUT_SETUPOTHER=R.layout.dialogsetupother;//~1726R~
	private static final int TITLE_SETUPFONT=R.string.DialogTitle_SetupFont;//~1727R~
	private static final int TITLE_SETUPCOLOR=R.string.DialogTitle_SetupColor;//~1726R~
	private static final int TITLE_SETUPOTHER=R.string.DialogTitle_SetupOther;//~1725I~
	private static final int HELPTITLE_SETUPFONT=R.string.HelpTitle_SetupFont;//~1A11I~
	private static final int HELPTITLE_SETUPCOLOR=R.string.HelpTitle_SetupColor;//~1A11I~
	private static final int HELPTITLE_SETUPOTHER=R.string.HelpTitle_SetupOther;//~1A11I~
//    private static final int HELP_SETUPFONT=R.string.Help_SetupFont;//~1929I~//~vc2BR~
//    private static final int HELP_SETUPCOLOR=R.string.Help_SetupColor;//~1929I~//~vc2BR~
//    private static final int HELP_SETUPOTHER=R.string.Help_SetupOther;//~1929I~//~vc2BR~
    private static final String HELP_SETUPFONT="SetupFont";        //~vc2BI~
    private static final String HELP_SETUPCOLOR="SetupColor";      //~vc2BI~
    private static final String HELP_SETUPOTHER="SetupOther";      //~vc2BI~
	private static final int DIALOG_FONTNAME=LAYOUT_SETUPFONT;
	private static final int[] FONT_SAMPLE=//"AaGgPpWw-xc0-XF0-x100"//~1731R~
    			{'A','a','G','g','P','p','W','w',' ',              //~1822R~
    			'u','-','c','0','(',0xc0,')',' ',                      //~1803R~//~vc2SR~
    			'u','-','f','0','(',0xf0,')',' ',                      //~1803R~//~vc2SR~
    			'u','-','3','0','4','2','(',0x3042,')',            //~vc2SI~
                };                                                  //~1822I~
	private static final int[] FONT_SAMPLE2=                       //~1822I~
                {                                                  //~1822I~
    			'u','-','1','0','0','(',0x100,')',                 //~1803R~
    			'u','-','1','d','4','0','0','(',0x1d400,')',       //~1803R~
//    			'u','-','1','f','0','0','0','(',0x1f000,')'         //~1803R~//~2126R~
    			'u','-','1','0','4','4','f','(',0x1044f,')'        //~2126I~
                };                                                 //~1803I~
	private static final String FONT_SAMPLE_LIGATURE1="ffff-fi-if";//~1803I~
	private static final String FONT_SAMPLE_LIGATURE2="if-fi-ffff";//~1803I~
	private static final int INCL_FONTSIZE=4;                      //~vc2QI~
	private static final int MIN_FONTSIZE=20;                      //~vc2QI~
	private static final int MAX_RULER_WIDTH=10;                   //~vc2SI~
//*********************                                            //~1725M~
	private static int  Slayoutid;                                 //~1725I~
	public static int  Srulergdkcolor;                             //~1725I~
	private int  layoutid;                                         //~1725I~
    private Slider slider_bgR,slider_bgG,slider_bgB;                      //~1726I~
    private Slider slider_rulerR,slider_rulerG,slider_rulerB;             //~1726I~
    private DialogCanvas samplePanel;                              //~1726I~
    private int bgcolor,rulercolor;                                //~1726I~
    private String[] spinnerData={                       //~1727I~
    		Font.MONOSPACE,                                        //~1727I~
    		Font.SANS,                                             //~1727I~
    		Font.SERIF,                                            //~1831R~
    		Font.DEFAULT                                           //~1831I~
    	};                                                         //~1727I~
    AxeSpinner spinner;                                            //~1727I~
    private EditText etCellW,etCellH,etFontSize;                   //~1730I~
    private TextView tvFontH,tvFontW;                              //~1730I~
    private RadioButtons rgFontStyle;                              //~1731R~
    private CheckBox cbLigature;                                   //~1803I~
    private EditText etRulerWidth;                                 //~vc2SR~
    private String tempfontname,tempstylename;                     //~1730R~
    private int tempfontsize,tempfontstyle,tempcellw,tempcellh;                                   //~1730I~
    private int valuefontsize,valuecellw,valuecellh;               //~1927I~
    private Font tempfont;                                         //~1803I~
    private boolean templigature;                                  //~1803I~
    CheckBox /*cbAccelerator,*/cbFreeCursor,cbBeep,cbQExit;            //~1730I~//~1929R~
    private String fontSampleText,fontSampleText2;                 //~1822I~
    private int[] samplecolor;                                     //~1821I~
    private static int parmFontSize;                                      //~1A02I~
//**********************************                               //~1725I~
	public Axegxedlg()                                             //~1725I~
    {                                                              //~1725I~
    	super(Slayoutid);                                          //~1725I~
        layoutid=Slayoutid;                                        //~1725I~
    }                                                              //~1725I~
//**********************************                               //~1725I~
    public static Axegxedlg showSetupOther()                       //~1725I~
    {                                                              //~1725I~
    	Slayoutid=LAYOUT_SETUPOTHER;                               //~1725I~
    	Axegxedlg dlg=new Axegxedlg();                             //~1725I~
        String title=Utils.getResourceString(TITLE_SETUPOTHER);    //~1725I~
		dlg.showDialog(title);                                     //~1725I~
        return dlg;                                                //~1725I~
    }                                                              //~1725I~
//**********************************                               //~1725I~
    public static Axegxedlg showSetupFont()                        //~1725I~
    {                                                              //~1725I~
        Slayoutid=LAYOUT_SETUPFONT;                                //~1725R~
        Axegxedlg dlg=new Axegxedlg();                             //~1725R~
        String title=Utils.getResourceString(TITLE_SETUPFONT);     //~1725R~
        dlg.fontSampleText=new String(FONT_SAMPLE,0,FONT_SAMPLE.length);//~1731I~
        dlg.fontSampleText2=new String(FONT_SAMPLE2,0,FONT_SAMPLE2.length);//~1822I~
        dlg.showDialog(title);                                     //~1725R~
        return dlg;                                                //~1725R~
    }                                                              //~1725I~
//**********************************                               //~1725I~
    public static Axegxedlg showSetupColor()                       //~1725I~
    {                                                              //~1725I~
        Slayoutid=LAYOUT_SETUPCOLOR;                               //~1725R~
        Axegxedlg dlg=new Axegxedlg();                             //~1725R~
        String title=Utils.getResourceString(TITLE_SETUPCOLOR);    //~1725R~
        dlg.samplecolor=AxeJNI.getSampleColor();	                   //~1821I~
        dlg.showDialog(title);                                     //~1725R~
        return dlg;                                                //~1725R~
    }                                                              //~1725I~
//*********                                                        //~1725I~
	@Override                                                      //~1725I~
	protected void setupDialogExtend(ViewGroup PlayoutView)        //~1725I~
    {                                                              //~1725I~
    //********************                                         //~1726I~
//        switch (layoutid)                                          //~1726I~//+vc53R~
//        {                                                          //~1726I~//+vc53R~
//        case LAYOUT_SETUPOTHER:                                    //~1726I~//+vc53R~
//            setupDialogExtendOther(PlayoutView);                   //~1726I~//+vc53R~
//            break;                                                 //~1726I~//+vc53R~
//        case LAYOUT_SETUPCOLOR:                                    //~1726I~//+vc53R~
//            setupDialogExtendColor(PlayoutView);                   //~1726I~//+vc53R~
//            break;                                                 //~1726I~//+vc53R~
//        case LAYOUT_SETUPFONT:                                     //~1727I~//+vc53R~
//            setupDialogExtendFont(PlayoutView);                    //~1727I~//+vc53R~
//            break;                                                 //~1727I~//+vc53R~
//        }                                                          //~1726I~//+vc53R~
//      switch (layoutid)                                          //+vc53I~
//      {                                                          //+vc53I~
        if (layoutid== LAYOUT_SETUPOTHER)                          //+vc53I~
            setupDialogExtendOther(PlayoutView);                   //+vc53I~
        else //break;                                              //+vc53I~
        if (layoutid== LAYOUT_SETUPCOLOR)                          //+vc53I~
            setupDialogExtendColor(PlayoutView);                   //+vc53I~
        else //break;                                              //+vc53I~
        if (layoutid== LAYOUT_SETUPFONT)                           //+vc53I~
            setupDialogExtendFont(PlayoutView);                    //+vc53I~
//          break;                                                 //+vc53I~
//      }                                                          //+vc53I~
    }                                                              //~1725I~
//************************                                         //~1929I~
//*Help                                                            //~1929I~
//************************                                         //~1929I~
	@Override                                                      //~1929I~
    protected boolean onClickHelp()                                //~1821R~//~1929I~
    {                                                              //~1528I~//~1929I~
//  	int id=0,title=0;                                          //~1929I~//~vc2BR~
    	int title=0;                                               //~vc2BI~
    	String id=null;                                            //~vc2BI~
//        switch (layoutid)                                          //~1929I~//+vc53R~
//        {                                                          //~1929I~//+vc53R~
//        case LAYOUT_SETUPOTHER:                                    //~1929I~//+vc53R~
//            title=HELPTITLE_SETUPOTHER;                            //~1A11R~//+vc53R~
//            id=HELP_SETUPOTHER;                                    //~1929I~//+vc53R~
//            break;                                                 //~1929I~//+vc53R~
//        case LAYOUT_SETUPCOLOR:                                    //~1929I~//+vc53R~
//            title=HELPTITLE_SETUPCOLOR;                            //~1A11R~//+vc53R~
//            id=HELP_SETUPCOLOR;                                    //~1929I~//+vc53R~
//            break;                                                 //~1929I~//+vc53R~
//        case LAYOUT_SETUPFONT:                                     //~1929I~//+vc53R~
//            title=HELPTITLE_SETUPFONT;                             //~1A11R~//+vc53R~
//            id=HELP_SETUPFONT;                                     //~1929I~//+vc53R~
//            break;                                                 //~1929I~//+vc53R~
//        }                                                          //~1929I~//+vc53R~
//      switch (layoutid)                                          //+vc53I~
//      {                                                          //+vc53I~
        if (layoutid== LAYOUT_SETUPOTHER)                          //+vc53I~
        {                                                          //+vc53I~
            title=HELPTITLE_SETUPOTHER;                            //+vc53I~
            id=HELP_SETUPOTHER;                                    //+vc53I~
        }                                                          //+vc53I~
        else //break;                                              //+vc53I~
        if (layoutid== LAYOUT_SETUPCOLOR)                          //+vc53I~
        {                                                          //+vc53I~
            title=HELPTITLE_SETUPCOLOR;                            //+vc53I~
            id=HELP_SETUPCOLOR;                                    //+vc53I~
        }                                                          //+vc53I~
        else //break;                                              //+vc53I~
        if (layoutid== LAYOUT_SETUPFONT)                           //+vc53I~
        {                                                          //+vc53I~
            title=HELPTITLE_SETUPFONT;                             //+vc53I~
            id=HELP_SETUPFONT;                                     //+vc53I~
        }                                                          //+vc53I~
//             break;                                              //+vc53I~
//      }                                                          //+vc53I~
//      if (id!=0)                                                 //~1929I~//~vc2BR~
        if (title!=0)                                              //~vc2BI~
			showDialogHelp(title,id);//~1821R~                     //~1929I~
        return false;	//not dismiss                              //~1821R~//~1929I~
    }                                                              //~1528I~//~1929I~
//************************                                         //~1730M~
//*dialog button Listener*                                         //~1730M~
//************************                                         //~1730M~
	@Override                                                      //~1730M~
    protected boolean onClickClose()           //~1730M~
    {                                                              //~1730M~
    	boolean rc=true;	//dismiss                              //~1730M~
    //****************                                             //~1730M~
//        switch (layoutid)                                          //~1730M~//+vc53R~
//        {                                                          //~1730M~//+vc53R~
//        case LAYOUT_SETUPOTHER:                                    //~1730M~//+vc53R~
//            onCloseSetupOther();                                   //~1730M~//+vc53R~
//            break;                                                 //~1730M~//+vc53R~
//        case LAYOUT_SETUPCOLOR:                                    //~1730M~//+vc53R~
//            onCloseSetupColor();                                   //~1730M~//+vc53R~
//            break;                                                 //~1730M~//+vc53R~
//        case LAYOUT_SETUPFONT:                                     //~1730M~//+vc53R~
//            onCloseSetupFont();                                    //~1730M~//+vc53R~
//            break;                                                 //~1730M~//+vc53R~
//        }                                                          //~1730M~//+vc53R~
//      switch (layoutid)                                          //+vc53I~
//      {                                                          //+vc53I~
        if (layoutid== LAYOUT_SETUPOTHER)                          //+vc53I~
            onCloseSetupOther();                                   //+vc53I~
        else //break;                                              //+vc53I~
        if (layoutid== LAYOUT_SETUPCOLOR)                          //+vc53I~
            onCloseSetupColor();                                   //+vc53I~
        else //break;                                              //+vc53I~
        if (layoutid== LAYOUT_SETUPFONT)                           //+vc53I~
            onCloseSetupFont();                                    //+vc53I~
//           //break;                                              //+vc53I~
//      }                                                          //+vc53I~
        return rc;                                                 //~1730M~
    }                                                              //~1730M~
//***************************************************************************//~1730I~
// static init<--xxemain_init<--gxeview_init<--gxeview_realize     //~1715I~
//***************************************************************************//~1715I~
	public static void gxedlg_init()                                      //~1715I~
    {                                                               //~1715I~//~1719R~
    	boolean defaultfontname=false;                             //~1823I~
        Font font;                                                 //~1823I~
    //*****************                                            //~1715I~
    	if (Dump.Y) Dump.println("Axegxedlg:gxedlg_init");         //~1715I~
//      if (Gxeh.Mfontsize==0)                                          //~1719I~//~1822R~
//        if (Gxeh.Mfontheight==0)                                 //~1927R~
//        {                                                        //~1927R~
//            if (Gxeh.Mcellh0!=0)                                 //~1927R~
//                fontsz=Gxeh.Mcellh0;    //dialog option save on ini//~1927R~
//            else                                                 //~1927R~
//                if (Gxeh.Mcellw0!=0)                             //~1927R~
//                    fontsz=Gxeh.Mcellw0*2;                       //~1927R~
//                else                                             //~1927R~
//                {                                                //~1927R~
//                    fontsz=AxeG.screenW*2/Gxeh.DEF_SCRCOL;       //~1927R~
//                    int fontsz2=AxeG.screenH/Gxeh.DEF_SCRROW;    //~1927R~
//                    fontsz=Math.min(fontsz,fontsz2);             //~1927R~
//                }                                                //~1927R~
//            Gxeh.Mfontheight=fontsz;                             //~1927I~
//        }                                                        //~1927R~
		parmFontSize=AxeG.getParameter(KEY_FONTSIZE,0);            //~1A02I~
//      if (Gxeh.Mfontheight==0)                                   //~1927I~//~1A02R~
        if (parmFontSize==0)                                       //~1A02I~
        {                                                          //~1927I~
        	Gxeh.Mfontheight=calcFontSize(Gxeh.Mcellh0,Gxeh.Mcellw0);//~1927I~
        }                                                          //~1927I~
        else                                                       //~vc2QI~
        	Gxeh.Mfontheight=parmFontSize;                         //~vc2QI~
        if (Dump.Y) Dump.println("Mfontheight="+Gxeh.Mfontheight); //~1822R~
        if (Gxeh.Mfontstyle==null || Gxeh.Mfontstyle.equals("")) //after ini deleted//~1716R~
        {                                                          //~1716R~
//            Gfontdata[0].FDfontdesc=pango_context_get_font_description(Gppangocontext);//~1715I~
//            gstr=pango_font_description_to_string((const PangoFontDescription*)(Gfontdata[0].FDfontdesc));//~1715I~
//            strcpy(Mfontstyle,gstr);                             //~1715I~
//            Gfontdata[0].FDfontname=gstr;   //scr fontname from default or ini file//~1715I~
//            Gfontdata[0].FDfontdesc=pango_font_description_from_string(gstr);//desc of context may freed,so allocate new//~1715I~
//            Gxeh.Gfontdata[0]=new Font(Gxeh.Mfontsize);                              //~1716I~//~1822R~
              Gxeh.Gfontdata[0]=font=new Font(Gxeh.Mfontheight);   //~1823R~
			  defaultfontname=true;                                //~1823I~
        }                                                          //~1716R~
        else                                                       //~1716R~
        {                                                          //~1716R~
//            gstr=g_new(gchar,strlen(Mfontstyle)+1);              //~1715I~
//            strcpy(gstr,Mfontstyle);                             //~1715I~
//            Gfontdata[0].FDfontname=gstr;   //scr fontname from default or ini file//~1715I~
//            Gfontdata[0].FDfontdesc=pango_font_description_from_string(gstr);//~1715I~
//            dprintf("gxedlg_init fontdesc=%p,name=%s\n",Gfontdata[0].FDfontdesc,gstr);//~1715I~
//  		Gxeh.Gfontdata[0]=new Font(Gxeh.Mfontstyle,Gxeh.Mstyle,Gxeh.Mfontsize);//~1822R~
    		Gxeh.Gfontdata[0]=font=new Font(Gxeh.Mfontstyle,Gxeh.Mstyle,Gxeh.Mfontheight);//~1823R~
        }                                                          //~1716R~
                                                                   //~1719I~
        AxeG.axeScreen.xxemain_createfont();    //set Mcellh,Mcellw from Mcellh0,Mcellw0,Mfontheight//~1929R~
        if (defaultfontname)                                       //~1823I~
        {                                                          //~1823I~
	          Gxeh.Mfontstyle=font.name;	//fontname             //~1823I~
	          Gxeh.Mstyle=font.getStylename();	//Bold etc         //~1823I~
        }                                                          //~1823I~
//        Gxeh.Mcellw0=Gxeh.Mcellw;   //result of cretefont for the case Mcellh0=0 at first//~1823I~//~1929R~
//        Gxeh.Mcellh0=Gxeh.Mcellh;                                  //~1823I~//~1929R~
        AxeJNI.notifyOptionChangedFont();	//notify to xe for usetmonospace(Mecllw)//~1823I~
        if (Dump.Y) Dump.println("after createfont h="+Gxeh.Mcellh0+"-->"+Gxeh.Mcellh+",w="+Gxeh.Mcellw0+"-->"+Gxeh.Mcellw);//~1716R~
        gxedlg_setcolor(Gxeh.Mbgcolor);                            //~1804R~
        gxedlg_setrulercolor(Gxeh.Mrulercolor);    //Grulerfg->Srulergdkcolor     //~1715I~//~1804R~
    	if (Dump.Y) Dump.println("Axegxedlg:gxedlg_init exit");    //~vc2SI~
        return;                                                    //~1715I~
    }//gxedlg_init                                                 //~1715I~
    //***************************************************************************//~1927I~
    private static int calcFontSize(int Pcellh0,int Pcellw0)       //~1927R~
    {                                                              //~1927I~
    	int fontsz;                                                //~1927I~
        if (Pcellh0!=0)                                            //~1927I~
            fontsz=Pcellh0;    //dialog option save on ini         //~1927I~
        else                                                       //~1927I~
            if (Pcellw0!=0)                                        //~1927I~
                fontsz=Pcellw0*2;                                  //~1927I~
            else                                                   //~1927I~
            {                                                      //~1927I~
                fontsz=AxeG.screenW*2/Gxeh.DEF_SCRCOL;             //~1927I~
                int fontsz2=AxeG.screenH/Gxeh.DEF_SCRROW;          //~1927I~
                fontsz=Math.min(fontsz,fontsz2);                   //~1927I~
            }                                                      //~1927I~
        if (Dump.Y) Dump.println("Axegxedlg calcfontsize="+fontsz);//~1927I~
        return fontsz;                                             //~1927I~
    }                                                              //~1927I~
    private static int calcCellH(int Pfontsize)                    //~1927I~
    {                                                              //~1927I~
    	return Pfontsize;                                          //~1927I~
    }                                                              //~1927I~
    private static int calcCellW(int Pfontsize,int Pcellh)         //~1927I~
    {                                                              //~1927I~
    	if (Pcellh!=0)                                                //~1927I~
        	return (Pcellh+1)/2;                                   //~1927I~
    	return (Pfontsize+1)/2;                                    //~1927I~
    }                                                              //~1927I~
    //***************************************************************************//~1715I~
    //color data exchange                                          //~1715I~
    //***************************************************************************//~1715I~
    public static int gxedlg_setcolor(int Pcolor)                  //~1715I~//~1804R~
    {                                                              //~1715I~
//        if (Popt!=0)       //gbl init                              //~1715I~
//        {                                                          //~1715I~//~1804R~
//            Gxeh.Gcolordata=Ppcolor;                                     //~1715I~//~1804R~
//        }                                                          //~1715I~//~1804R~
//        return Gxeh.Gcolordata;                                         //~1715I~//~1804R~
		Gxeh.Mbgcolor=Pcolor;        return Gxeh.Gcolordata;       //~1804I~
    }                                                              //~1715I~
    //***************************************************************************//~1715I~
    //color data exchange  for ruler                               //~1715I~
    //***************************************************************************//~1715I~
    public static int gxedlg_setrulercolor(int Pcolor)                             //~1715I~//~1804R~
    {                                                              //~1715I~
//        if (Pcolor!=0)//gbl init,setup for dialog                    //~1715I~//~1804R~
//        {                                                          //~1715I~//~1804R~
//            Srulergdkcolor=Gxeh.Mrulercolor;                   //~1716R~//~1804R~
//        }                                                          //~1715I~//~1804R~
//        else    //exit setup dlg by OK button             //set to Mbgcolor to save to inifile//~1715I~//~1804R~
//        {                                                          //~1715I~//~1804R~
//          Gxeh.Mrulercolor=Srulergdkcolor;                   //~1716R~//~1804R~
//          Gxeh.Grulerfg=Srulergdkcolor;                     //~1715I~//~1804R~
//          Gxeh.Gpgcruler.setColor(Gxeh.Grulerfg);                //~1715I~//~1804R~
            Gxeh.Mrulercolor=Pcolor;                               //~1804I~
            Gxeh.Gpgcruler.setColor(Pcolor);                       //~1804I~
//        }                                                          //~1715I~//~1804R~
        return 0;                                                  //~1715I~
    }                                                              //~1715I~
//*********                                                        //~1730M~
//*Other  *                                                        //~1730I~
//*********                                                        //~1730I~
	private void setupDialogExtendOther(ViewGroup PlayoutView)     //~1730M~
    {                                                              //~1730M~
        RadioGroup rg;                                             //~1730M~
        int buttonid;                                              //~1730M~
    //********************                                         //~1730M~
        int ruler=Gxeh.Mrulermode;                                 //~1730M~//~1803R~
//        switch(ruler)                                              //~1730M~//+vc53R~
//        {                                                          //~1730M~//+vc53R~
//        case Gxeh.WXERULER_V:                                                    //~1730M~//~1803R~//+vc53R~
//            buttonid=R.id.RULER_V;                                 //~1730M~//+vc53R~
//            break;                                                 //~1730M~//+vc53R~
//        case Gxeh.WXERULER_H:                                                    //~1730M~//~1803R~//+vc53R~
//            buttonid=R.id.RULER_H;                                 //~1730M~//+vc53R~
//            break;                                                 //~1730M~//+vc53R~
//        case Gxeh.WXERULER_VH:                                                    //~1730M~//~1803R~//+vc53R~
//            buttonid=R.id.RULER_CROSS;                             //~1730M~//+vc53R~
//            break;                                                 //~1730M~//+vc53R~
//        default:                                                   //~1730M~//+vc53R~
//            buttonid=R.id.RULER_NONE;                              //~1730M~//+vc53R~
//        }                                                          //~1730M~//+vc53R~
//      switch(ruler)                                              //+vc53I~
//      {                                                          //+vc53I~
        if (ruler== Gxeh.WXERULER_V)                               //+vc53I~
            buttonid=R.id.RULER_V;                                 //+vc53I~
        else //break;                                              //+vc53I~
        if (ruler== Gxeh.WXERULER_H)                               //+vc53I~
            buttonid=R.id.RULER_H;                                 //+vc53I~
        else //break;                                              //+vc53I~
        if (ruler== Gxeh.WXERULER_VH)                              //+vc53I~
            buttonid=R.id.RULER_CROSS;                             //+vc53I~
        else //break;                                              //+vc53I~
//      default:                                                   //+vc53I~
            buttonid=R.id.RULER_NONE;                              //+vc53I~
//      }                                                          //+vc53I~
        rg=(RadioGroup)layoutView.findViewById(R.id.RULER);        //~1730M~
        rg.check(buttonid);                                        //~1730M~
                                                                   //~1730M~
        etRulerWidth=(EditText)layoutView.findViewById(R.id.Ruler_Width);//~vc2SI~
        etRulerWidth.setText(Integer.toString(AxeG.RulerWidth));   //~vc2SR~
        TextView tvRulerWidth=(TextView)layoutView.findViewById(R.id.Ruler_WidthMax);//~vc2SI~
        tvRulerWidth.setText(Utils.getStr(R.string.Setup_RulerWidthMax,AxeG.MAX_RULER_WIDTH));//~vc2SI~
//        cbAccelerator=(CheckBox)layoutView.findViewById(R.id.ACCELERATOR);//~1730R~//~1929R~
//        cbAccelerator.setChecked(Gxeh.Museact==1);                 //~1730I~//~1929R~
        cbFreeCursor=(CheckBox)layoutView.findViewById(R.id.FREECURSOR);//~1730R~
        cbFreeCursor.setChecked(Gxeh.Mfreecsr);                    //~1730I~
        cbBeep=(CheckBox)layoutView.findViewById(R.id.BEEP);       //~1730R~
        cbBeep.setChecked(Gxeh.Mbeep);                             //~1730I~
        cbQExit=(CheckBox)layoutView.findViewById(R.id.QEXIT);     //~1730R~
        cbQExit.setChecked(Gxeh.Mqexit);                           //~1730I~
    }                                                              //~1730M~
    private void onCloseSetupOther()                               //~1730M~
    {                                                              //~1730M~
        boolean freecursor,beep,qexit;                 //~1730M~
        int ruler,buttonid;                                        //~1730M~
        RadioGroup rg;                                             //~1730M~
    //****************                                             //~1730M~
        rg=(RadioGroup)layoutView.findViewById(R.id.RULER);        //~1730M~
        buttonid=rg.getCheckedRadioButtonId();                     //~1730M~
//        switch(buttonid)                                           //~1730M~//+vc53R~
//        {                                                          //~1730M~//+vc53R~
//        case R.id.RULER_V:                                         //~1730M~//+vc53R~
//            ruler=1;                                               //~1730M~//+vc53R~
//            break;                                                 //~1730M~//+vc53R~
//        case R.id.RULER_H:                                         //~1730M~//+vc53R~
//            ruler=2;                                               //~1730M~//+vc53R~
//            break;                                                 //~1730M~//+vc53R~
//        case R.id.RULER_CROSS:                                     //~1730M~//+vc53R~
//            ruler=3;                                               //~1730M~//+vc53R~
//            break;                                                 //~1730M~//+vc53R~
//        default:                                                   //~1730M~//+vc53R~
//            ruler=0;                                               //~1730M~//+vc53R~
//        }                                                          //~1730M~//+vc53R~
//      switch(buttonid)                                           //+vc53I~
//      {                                                          //+vc53I~
        if (buttonid== R.id.RULER_V)                               //+vc53I~
            ruler=1;                                               //+vc53I~
        else //break;                                              //+vc53I~
        if (buttonid== R.id.RULER_H)                               //+vc53I~
            ruler=2;                                               //+vc53I~
        else //break;                                              //+vc53I~
        if (buttonid== R.id.RULER_CROSS)                           //+vc53I~
            ruler=3;                                               //+vc53I~
        else //break;                                              //+vc53I~
//      default:                                                   //+vc53I~
            ruler=0;                                               //+vc53I~
//      }                                                          //+vc53I~
        int ww=Utils.strToNum(etRulerWidth.getText().toString(),0);//~vc2SI~
        if (ww>0 && ww<AxeG.MAX_RULER_WIDTH)                       //~vc2SR~
        {                                                          //~vc2SI~
	        AxeG.RulerWidth=ww;                                   //~vc2SR~
	        AxeG.setParameter(AxeG.PREFKEY_RULER_WIDTH,ww);        //~vc2SR~
        }                                                          //~vc2SI~
//        accelerator=cbAccelerator.isChecked();                     //~1730R~//~1929R~
        freecursor=cbFreeCursor.isChecked();                       //~1730R~
        beep=cbBeep.isChecked();                                   //~1730R~
        qexit=cbQExit.isChecked();                                 //~1730R~
                                                                   //~1730M~
        Gxeh.Mrulermode=ruler;                                     //~1730M~//~1803R~
//        Gxeh.Museact=(accelerator?1:0);                            //~1730M~//~1929R~
        Gxeh.Mfreecsr=freecursor;                                  //~1730M~
        Gxeh.Mbeep=beep;                                           //~1730M~
        Gxeh.Mqexit=qexit;                                         //~1730M~
        if (Dump.Y) Dump.println("AxeDialog OtherOption ruler="+ruler+",freecursor="+freecursor+",beep="+beep+",qexit="+qexit);//~1730M~
        AxeJNI.notifyOptionChangedOther();                         //~1803I~
        AxeJNI.fullDraw();                                         //~1823I~
    }                                                              //~1730M~
//*********                                                        //~1730I~
//*Color  *                                                        //~1730I~
//*********                                                        //~1730I~
	private void setupDialogExtendColor(ViewGroup PlayoutView)     //~1730M~
    {                                                              //~1730M~
        int rr,gg,bb,color;                                        //~1730M~
    //********************                                         //~1730M~
        if (Dump.Y) Dump.println("AxeDialog Color dlginit bg="+Integer.toHexString(Gxeh.Mbgcolor)+",ruler="+Integer.toHexString(Gxeh.Mrulercolor));//~1730M~
    	color=Gxeh.Mbgcolor;                                       //~1730M~
    	rr=Color.red(color);                                       //~1730M~
    	gg=Color.green(color);                                     //~1730M~
    	bb=Color.blue(color);                                      //~1730M~
    	slider_bgR=new Slider(this,R.id.EDITTEXT_BG_RED,R.id.SEEKBAR_BG_RED,rr,0,255);//~1730M~
    	slider_bgG=new Slider(this,R.id.EDITTEXT_BG_GREEN,R.id.SEEKBAR_BG_GREEN,gg,0,255);//~1730M~
    	slider_bgB=new Slider(this,R.id.EDITTEXT_BG_BLUE,R.id.SEEKBAR_BG_BLUE,bb,0,255);//~1730M~
    	color=Gxeh.Mrulercolor;                                    //~1730M~
    	rr=Color.red(color);                                       //~1730M~
    	gg=Color.green(color);                                     //~1730M~
    	bb=Color.blue(color);                                      //~1730M~
    	slider_rulerR=new Slider(this,R.id.EDITTEXT_RULER_RED,R.id.SEEKBAR_RULER_RED,rr,0,255);//~1730M~
    	slider_rulerG=new Slider(this,R.id.EDITTEXT_RULER_GREEN,R.id.SEEKBAR_RULER_GREEN,gg,0,255);//~1730M~
    	slider_rulerB=new Slider(this,R.id.EDITTEXT_RULER_BLUE,R.id.SEEKBAR_RULER_BLUE,bb,0,255);//~1730M~
        samplePanel=new DialogCanvas(LAYOUT_SETUPCOLOR,layoutView);//~1730M~
        bgcolor=Gxeh.Mbgcolor;                                     //~1730M~
        rulercolor=Gxeh.Mrulercolor;                               //~1730M~
        drawSamplePanel(bgcolor,rulercolor);                       //~1730R~
    }                                                              //~1730M~
//*********                                                        //~1730M~
    private void onCloseSetupColor()                               //~1730M~
    {                                                              //~1730M~
        int rr,gg,bb,colorbg,colorruler;                           //~1730M~
    //********************                                         //~1730M~
    	rr=slider_bgR.getValue();                                  //~1730M~
    	gg=slider_bgG.getValue();                                  //~1730M~
    	bb=slider_bgB.getValue();                                  //~1730M~
    	colorbg=Color.rgb(rr,gg,bb);                               //~1730M~//~1804R~
    	rr=slider_rulerR.getValue();                               //~1730M~
    	gg=slider_rulerG.getValue();                               //~1730M~
    	bb=slider_rulerB.getValue();                               //~1730M~
    	colorruler=Color.rgb(rr,gg,bb);                            //~1730M~//~1804R~
    	Gxeh.Mbgcolor=colorbg;                                     //~1730M~
        gxedlg_setcolor(colorbg);                                  //~1804I~
    	gxedlg_setrulercolor(colorruler);                          //~1804I~
        AxeJNI.notifyOptionChangedColor();                         //~1803I~
        AxeJNI.fullDraw();                                         //~1823I~
        if (Dump.Y) Dump.println("AxeDialog Color bg="+Integer.toHexString(Gxeh.Mbgcolor)+",ruler="+Integer.toHexString(Gxeh.Mrulercolor));//~1730M~
    }                                                              //~1730M~
//*********                                                        //~1730M~
    @Override                                                      //~1730M~
    public void onSliderChanged(int Psliderid,int Pvalue)          //~1730M~
    {                                                              //~1730M~
        if (Dump.Y) Dump.println("OnSliderChanged id="+Integer.toHexString(Psliderid)+",value="+Integer.toHexString(Pvalue));//~1730M~
//        switch(Psliderid)                                          //~1730M~//+vc53R~
//        {                                                          //~1730M~//+vc53R~
//        case R.id.SEEKBAR_BG_RED:                                  //~1730M~//+vc53R~
//            bgcolor=Color.rgb(Pvalue,Color.green(bgcolor),Color.blue(bgcolor));//~1730M~//+vc53R~
//            break;                                                 //~1730M~//+vc53R~
//        case R.id.SEEKBAR_BG_GREEN:                                //~1730M~//+vc53R~
//            bgcolor=Color.rgb(Color.red(bgcolor),Pvalue,Color.blue(bgcolor));//~1730M~//+vc53R~
//            break;                                                 //~1730M~//+vc53R~
//        case R.id.SEEKBAR_BG_BLUE:                                 //~1730M~//+vc53R~
//            bgcolor=Color.rgb(Color.red(bgcolor),Color.green(bgcolor),Pvalue);//~1730M~//+vc53R~
//            break;                                                 //~1730M~//+vc53R~
//        case R.id.SEEKBAR_RULER_RED:                               //~1730M~//+vc53R~
//            rulercolor=Color.rgb(Pvalue,Color.green(rulercolor),Color.blue(rulercolor));//~1730M~//+vc53R~
//            break;                                                 //~1730M~//+vc53R~
//        case R.id.SEEKBAR_RULER_GREEN:                             //~1730M~//+vc53R~
//            rulercolor=Color.rgb(Color.red(rulercolor),Pvalue,Color.blue(rulercolor));//~1730M~//+vc53R~
//            break;                                                 //~1730M~//+vc53R~
//        case R.id.SEEKBAR_RULER_BLUE:                              //~1730M~//+vc53R~
//            rulercolor=Color.rgb(Color.red(rulercolor),Color.green(rulercolor),Pvalue);//~1730M~//+vc53R~
//            break;                                                 //~1730M~//+vc53R~
//        }                                                          //~1730M~//+vc53R~
//  	switch(Psliderid)                                          //+vc53I~
//      {                                                          //+vc53I~
    	if (Psliderid== R.id.SEEKBAR_BG_RED)                       //+vc53I~
        	bgcolor=Color.rgb(Pvalue,Color.green(bgcolor),Color.blue(bgcolor));//+vc53I~
        else //break;                                              //+vc53I~
    	if (Psliderid== R.id.SEEKBAR_BG_GREEN)                     //+vc53I~
        	bgcolor=Color.rgb(Color.red(bgcolor),Pvalue,Color.blue(bgcolor));//+vc53I~
        else //break;                                              //+vc53I~
    	if (Psliderid== R.id.SEEKBAR_BG_BLUE)                      //+vc53I~
        	bgcolor=Color.rgb(Color.red(bgcolor),Color.green(bgcolor),Pvalue);//+vc53I~
        else //break;                                              //+vc53I~
    	if (Psliderid== R.id.SEEKBAR_RULER_RED)                    //+vc53I~
        	rulercolor=Color.rgb(Pvalue,Color.green(rulercolor),Color.blue(rulercolor));//+vc53I~
        else //break;                                              //+vc53I~
    	if (Psliderid== R.id.SEEKBAR_RULER_GREEN)                  //+vc53I~
        	rulercolor=Color.rgb(Color.red(rulercolor),Pvalue,Color.blue(rulercolor));//+vc53I~
        else //break;                                              //+vc53I~
    	if (Psliderid== R.id.SEEKBAR_RULER_BLUE)                   //+vc53I~
        	rulercolor=Color.rgb(Color.red(rulercolor),Color.green(rulercolor),Pvalue);//+vc53I~
//          break;                                                 //+vc53I~
//      }                                                          //+vc53I~
	    drawSamplePanel(bgcolor,rulercolor);                       //~1730R~
    }                                                              //~1730M~
//*********                                                        //~1731M~
    private void drawSamplePanel(int Pbg,int Pruler)               //~1731M~
    {                                                              //~1731M~
        int xx,yy,ww,hh,baseline,ww3,fonth;                        //~1821R~
        float fhbyw,fh;                                          //~1821I~
        Point ptsz;//~1731M~//~1803R~
    //********************                                         //~1731M~
        samplePanel.fillRect(Pbg);                                 //~1731M~
        ptsz=samplePanel.getSize(); //panel size                   //~1731M~
		 ww=ptsz.x;                                                 //~1731M~
		 hh=ptsz.y;                                                 //~1731M~
        fhbyw=((float)ww*2)/(6*3); //char height by "Browse"*3        //~1821R~
        if (Dump.Y) Dump.println("AxeDialog fontsz ww="+ww+",by width="+fhbyw);//~1821R~
        if (hh>fhbyw)                                              //~1821R~
        	fh=fhbyw;                                              //~1821R~
        else                                                       //~1821I~
        	fh=(float)hh;                                          //~1821R~
        fonth=(int)((fh*2)/3);	//consider baseline                //~1821R~
    	samplePanel.setTextSize(fonth);                            //~1821R~
        baseline=samplePanel.getBaseline();                        //~1803R~
        ww3=ww/3;                                                  //~1731M~
        xx=0;                                                      //~1731M~
//  	samplePanel.drawString("Browse",xx,baseline,Color.WHITE);  //~1821R~
    	samplePanel.drawString("Browse",xx,baseline,samplecolor[0]);//~1821I~
        xx+=ww3;                                                   //~1731M~
//  	samplePanel.drawString("Edit",xx,baseline,Color.YELLOW);   //~1821R~
    	samplePanel.drawString("Edit",xx,baseline,samplecolor[1]); //~1821I~
        xx+=ww3;                                                   //~1731M~
//  	samplePanel.drawString("Lineno",xx,baseline,Color.GREEN);  //~1821R~
    	samplePanel.drawString("Lineno",xx,baseline,samplecolor[2]);//~1821I~
        yy=samplePanel.getFontheight();                            //~1803R~
        xx=0;                                                      //~1731M~
    	samplePanel.drawLine(xx,yy,-1,yy,Pruler);                  //~1731M~
        yy=0;                                                      //~1731M~
        xx=samplePanel.getStringWidth("B");                        //~1731M~
    	samplePanel.drawLine(xx,yy,xx,-1,Pruler);                  //~1731M~
        samplePanel.invalidate();                                  //~1731M~
    }                                                              //~1731M~
//*********                                                        //~1730M~
//*Font   *                                                        //~1730I~
//*********                                                        //~1730I~
	private void setupDialogExtendFont(ViewGroup PlayoutView)      //~1730M~
    {                                                              //~1730M~
        int hh,ww,btnid;                                           //~1730M~
    //********************                                         //~1730M~
//      if (Dump.Y) Dump.println("AxeDialog Font="+Gxeh.Mfontstyle+",size="+Gxeh.Mfontsize+",style="+Gxeh.Mstyle);//~1822R~
        if (Dump.Y) Dump.println("AxeDialog Font="+Gxeh.Mfontstyle+",size="+Gxeh.Mfontheight+",style="+Gxeh.Mstyle);//~1822I~
        if (Dump.Y) Dump.println("AxeDialog cellh="+Gxeh.Mcellh+",cellw="+Gxeh.Mcellw);//~1730M~
        setupFontSpinner();                                        //~1730M~
        etFontSize=(EditText)layoutView.findViewById(R.id.FONT_FONTSIZE);//~1730M~
//      etFontSize.setText(Integer.toString(Gxeh.Mfontsize));      //~1822R~
//      etFontSize.setText(Integer.toString(Gxeh.Mfontheight));    //~1822I~//~1A02R~
//      etFontSize.setText(Integer.toString(parmFontSize));        //~1A02I~//~vc2QR~
        etFontSize.setText(Integer.toString(Gxeh.Mfontheight));    //~vc2QI~
        Font font=Gxeh.Gfontdata[0];                               //~1730M~
        FontMetrics fm=FontMetrics.getFontMetrics(font);           //~1730M~
        hh=fm.getHeight();                                         //~1730M~
        ww=fm.getAvWidth();                                        //~1730M~
        tvFontH=(TextView)layoutView.findViewById(R.id.FONT_FONTH);//~1730M~
        tvFontH.setText(Integer.toString(hh));                     //~1730M~
        tvFontW=(TextView)layoutView.findViewById(R.id.FONT_FONTW);//~1730M~
        tvFontW.setText(Integer.toString(ww));                     //~1730M~
        etCellH=(EditText)layoutView.findViewById(R.id.FONT_CELLH);//~1730M~
        etCellH.setText(Integer.toString(Gxeh.Mcellh0));            //~1730M~//~1929R~
        etCellW=(EditText)layoutView.findViewById(R.id.FONT_CELLW);//~1730M~
        etCellW.setText(Integer.toString(Gxeh.Mcellw0));            //~1730M~//~1929R~
        cbLigature=(CheckBox)layoutView.findViewById(R.id.FONT_LIGATURE);//~1803I~
        cbLigature.setChecked(Gxeh.Mligature);                          //~1803I~
//radio button for Font Style                                      //~1730M~
        tempstylename=Gxeh.Mstyle;                                 //~1804I~
        if (Gxeh.Mstyle.equals(Font.Bold))                         //~1730M~
        	btnid=R.id.FONT_STYLE_BOLD;                            //~1730M~
        else                                                       //~1730M~
        if (Gxeh.Mstyle.equals(Font.Italic))                       //~1730M~
        	btnid=R.id.FONT_STYLE_ITALIC;                          //~1730M~
        else                                                       //~1730M~
        if (Gxeh.Mstyle.equals(Font.BoldItalic))                   //~1730M~
        	btnid=R.id.FONT_STYLE_BOLDITALIC;                      //~1730M~
        else                                                       //~1730M~
        {                                                          //~1804I~
        	btnid=R.id.FONT_STYLE_NORMAL;                          //~1730M~
            tempstylename=Font.Normal;                             //~1804I~
        }                                                          //~1804I~
//      rgFontStyle=(RadioGroup)layoutView.findViewById(R.id.FONT_STYLE);//~1731R~
        rgFontStyle=new RadioButtons(this);                        //~1731I~
        rgFontStyle.add(layoutView,R.id.FONT_STYLE_NORMAL);        //~1731I~
        rgFontStyle.add(layoutView,R.id.FONT_STYLE_BOLD);          //~1731I~
        rgFontStyle.add(layoutView,R.id.FONT_STYLE_ITALIC);        //~1731I~
        rgFontStyle.add(layoutView,R.id.FONT_STYLE_BOLDITALIC);    //~1731I~
        rgFontStyle.check(btnid);                                  //~1730M~
        samplePanel=new DialogCanvas(LAYOUT_SETUPFONT,layoutView); //~1730M~
    	setFontChangeListener();                                   //~1730M~
        tempfontname=font.name;                                    //~1730M~
        valuefontsize=tempfontsize=font.getSize();                 //~1927R~
        tempfontstyle=font.getStyle();                             //~1730I~
        valuecellh=tempcellh=Gxeh.Mcellh;                          //~1927R~
        valuecellw=tempcellw=Gxeh.Mcellw;                          //~1927R~
        valuecellh=Gxeh.Mcellh0;                                   //~1A02I~
        valuecellw=Gxeh.Mcellw0;                                   //~1A02I~
        templigature=Gxeh.Mligature;                               //~1803I~
        tempfont=font;                                             //~1803I~
        drawSamplePanel(tempfont,tempcellw,tempcellh,templigature);                 //~1730I~//~1803R~
    }                                                              //~1730M~
//*********                                                        //~1730M~
	private void setupFontSpinner()                                //~1730M~
    {                                                              //~1730M~
		int pos;                                                   //~1730M~
	//*********************	                                       //~1730M~
        spinner=AxeSpinner.create(DIALOG_FONTNAME,layoutView,spinnerData);//~1730M~
        pos=getInitialPos(Gxeh.Mfontstyle);                        //~1730M~
        spinner.setSelection(pos);                                 //~1730M~
    }                                                              //~1730M~
//*****************                                                //~1730M~
    private int getInitialPos(String Pfontname)                    //~1730M~
    {                                                              //~1730M~
    	int pos=0;                                                 //~1730M~
    //*************************                                    //~1730M~
    	for (int ii=0;ii<spinnerData.length;ii++)                  //~1730M~
        	if (Pfontname.equals(spinnerData[ii]))                 //~1730M~
            {                                                      //~1730M~
            	pos=ii;                                            //~1730M~
            	break;                                             //~1730M~
            }                                                      //~1730M~
	    if (Dump.Y) Dump.println("getInitial pos="+pos);           //~1730M~
        return pos;                                                //~1730M~
    }                                                              //~1730M~
//*********                                                        //~1730M~
    private void onCloseSetupFont()                                //~1730M~
    {                                                              //~1730M~
        int intval;                                                //~1823I~
        int oldh,oldw;                                             //~1927I~
        boolean deleteucstb=false;                                 //~vai6I~
    //********************                                         //~1730M~
        deleteucstb=!Gxeh.Mfontstyle.equals(tempfontname);         //~vai6I~
        Gxeh.Mstyle=tempstylename;                                 //~1730R~
        Gxeh.Mfontstyle=tempfontname;                              //~1730M~
        Gxeh.Mligature=templigature;                               //~1823M~
                                                                   //~1823I~
        intval=Utils.strToNum(etFontSize.getText().toString(),0);	//textchange listener is not called by push "OK"(called if focus was lost)//~1927R~
        if (intval>=0)	                                           //~1927R~
	        parmFontSize=Gxeh.Mfontheight=intval;                               //~1823I~//~1A02R~
        intval=Utils.strToNum(etCellW.getText().toString(),0);     //~1927R~
        if (intval>=0)                                             //~1927R~
	        Gxeh.Mcellw0=intval;                                   //~1927R~
        intval=Utils.strToNum(etCellH.getText().toString(),0);     //~1927R~
        if (intval>=0)                                             //~1927R~
	        Gxeh.Mcellh0=intval;                                   //~1927R~
                                                                   //~1823I~
        if (Gxeh.Mfontheight==0)                                   //~1927R~
	        Gxeh.Mfontheight=calcFontSize(Gxeh.Mcellh0,Gxeh.Mcellw0);//~1927I~
        oldh=Gxeh.Mcellh;                                          //~1927I~
        oldw=Gxeh.Mcellw;                                          //~1927I~
        Font font=new Font(tempfontname,tempfontstyle,Gxeh.Mfontheight);//~1823R~
        Gxeh.Gfontdata[0]=font;                                    //~1730M~
        AxeG.axeScreen.xxemain_createfont();	//set Mcellh,Mcellw from Mcellh0,Mcellw0//~1927R~
        AxeJNI.notifyOptionChangedFont();                          //~1803I~
		AxeG.setParameter(KEY_FONTSIZE,parmFontSize);   //fontsize parameter as isplay value//~1A02I~
        AxeJNI.fullDraw(oldh!=Gxeh.Mcellh||oldw!=Gxeh.Mcellw);     //~1927R~
      if (deleteucstb)                                             //~vai6I~
        Ucs.resetwidthtbl();                                       //~1A14I~
    }                                                              //~1730M~
//***************************************************************************//~1730M~
	private void setFontChangeListener()                           //~1730M~
    {                                                              //~1730M~
    	EditTextField.setListener(this,etFontSize);                //~1730M~
    	EditTextField.setListener(this,etCellH);                   //~1730M~
    	EditTextField.setListener(this,etCellW);                   //~1730M~
	    setCheckBoxListener(cbLigature);                           //~1803I~
	   	spinner.setListener(this);                                 //~1730R~
//     	setRadioButtonListener();                                  //~1731R~
    }                                                              //~1730M~
//*****************                                                //~1803I~
//*CheckBox Lister                                                 //~1803I~
//*****************                                                //~1803I~
    private void setCheckBoxListener(CheckBox Pcb)                 //~1803I~
    {                                                              //~1803I~
    //************                                                 //~1803I~
        CheckBoxListener l=this.new CheckBoxListener();            //~1803I~
    	Pcb.setOnClickListener(l);                                 //~1803I~
    }                                                              //~1803I~
//********                                                         //~1803I~
    public class CheckBoxListener implements View.OnClickListener       //~1803I~
    {                                                              //~1803I~
        public void onClick(View Pview)                            //~1803I~
        {                                                          //~1803I~
            templigature=((CheckBox)Pview).isChecked();            //~1803I~
	        drawSamplePanel(tempfont,tempcellw,tempcellh,templigature);//~1803I~
        }                                                          //~1803I~
    }                                                              //~1803I~
//*********************************************************        //~vai6I~
    @Override                                                      //~vai6I~
    public void beforeTextChangedETF(EditText PeditText, String Ptext, int start, int count, int after){};//~vai6I~
    @Override                                                      //~vai6I~
    public void onTextChangedETF(EditText PeditText,String Ptext,int start,int before,int count){};//~vai6I~
    @Override                                                      //~vai6I~
    public void afterTextChangedETF(EditText PeditText,String Ptext){};//~vai6I~
    @Override                                                      //~vai6I~
    public void onEditTextChanged(int Pviewid,String Pvalue)       //~vai6I~
    {                                                              //~vai6I~
    }                                                              //~vai6I~
    @Override                                                      //~1730M~
    public void onEditTextChanged(int Pviewid,int Pvalue)                      //~1730M~
    {                                                              //~1730M~
    	int hh,ww;                                              //~1927R~
    //************                                                 //~1730M~
    	if (Dump.Y) Dump.println("Axegxedlg:edittextChanged viewid="+Integer.toHexString(Pviewid)+",value="+Pvalue);//~1730M~
//        switch(Pviewid)                                            //~1730M~//+vc53R~
//        {                                                          //~1730M~//+vc53R~
//        case R.id.FONT_FONTSIZE:                                   //~1730M~//+vc53R~
//            if (valuefontsize==Pvalue)                             //~1927R~//+vc53R~
//                return;                                          //+vc53R~
//            valuefontsize=tempfontsize=Pvalue;                     //~1927I~//~1A02R~//+vc53R~
//            if (tempfontsize==0)                                   //~1927I~//+vc53R~
//                tempfontsize=calcFontSize(valuecellh,valuecellw);     //re-evaluate by parm value//~1927R~//+vc53R~
//            tempfont=new Font(tempfontname,tempfontstyle,tempfontsize);//~1731R~//~1803R~//~1927R~//+vc53R~
//            FontMetrics fm=FontMetrics.getFontMetrics(tempfont);           //~1731I~//~1803R~//~1804I~//+vc53R~
//            hh=fm.getHeight();                                         //~1731I~//~1804I~//+vc53R~
//            ww=fm.getAvWidth();                                        //~1731I~//~1804I~//+vc53R~
//            tvFontH.setText(Integer.toString(hh));                     //~1731I~//~1804I~//+vc53R~
//            tvFontW.setText(Integer.toString(ww));                     //~1731I~//~1804I~//+vc53R~
//            if (valuecellh==0)                                     //~1A02I~//+vc53R~
//                tempcellh=calcCellH(tempfontsize);                 //~1A02I~//+vc53R~
//            if (valuecellw==0)                                     //~1A02I~//+vc53R~
//                tempcellw=calcCellW(tempfontsize,valuecellh);      //~1A02I~//+vc53R~
//            break;                                                 //~1730M~//+vc53R~
//        case R.id.FONT_CELLH:                                      //~1730M~//+vc53R~
//            if (valuecellh==Pvalue)                                //~1927R~//+vc53R~
//                return;                                            //~1804I~//+vc53R~
//            valuecellh=tempcellh=Pvalue;                                      //~1731R~//~1927M~//+vc53R~
//            if (valuefontsize==0)                                  //~1927I~//+vc53R~
//                tempfontsize=calcFontSize(valuecellh,valuecellw);     //re-evaluate by parm value//~1927I~//+vc53R~
//            if (valuecellh==0)                                      //~1927R~//~1A02R~//+vc53R~
//                tempcellh=calcCellH(tempfontsize);                 //~1927R~//+vc53R~
//            if (valuecellw==0)                                     //~1A02I~//+vc53R~
//                tempcellw=calcCellW(tempfontsize,valuecellh);      //~1A02I~//+vc53R~
//            break;                                                 //~1730M~//+vc53R~
//        case R.id.FONT_CELLW:                                      //~1730M~//+vc53R~
//            if (valuecellw==Pvalue)                                //~1927R~//+vc53R~
//                return;                                            //~1804I~//+vc53R~
//            valuecellw=tempcellw=Pvalue;                                      //~1731R~//~1927R~//+vc53R~
//            if (valuefontsize==0)                                  //~1927I~//+vc53R~
//                tempfontsize=calcFontSize(valuecellh,valuecellw);     //re-evaluate by parm value//~1927I~//+vc53R~
//            if (valuecellh==0)                                     //~1A02I~//+vc53R~
//                tempcellh=calcCellH(tempfontsize);                 //~1A02I~//+vc53R~
//            if (valuecellw==0)                                      //~1927R~//~1A02R~//+vc53R~
//                tempcellw=calcCellW(tempfontsize,valuecellh);      //~1927R~//+vc53R~
//            break;                                                 //~1730M~//+vc53R~
//        }                                                          //~1730M~//+vc53R~
//      switch(Pviewid)                                            //+vc53I~
//      {                                                          //+vc53I~
        if (Pviewid== R.id.FONT_FONTSIZE)                          //+vc53I~
        {                                                          //+vc53I~
            if (valuefontsize==Pvalue)                             //+vc53I~
                return;                                            //+vc53I~
            valuefontsize=tempfontsize=Pvalue;                     //+vc53I~
            if (tempfontsize==0)                                   //+vc53I~
                tempfontsize=calcFontSize(valuecellh,valuecellw);     //re-evaluate by parm value//+vc53I~
            tempfont=new Font(tempfontname,tempfontstyle,tempfontsize);//+vc53I~
            FontMetrics fm=FontMetrics.getFontMetrics(tempfont);   //+vc53I~
            hh=fm.getHeight();                                     //+vc53I~
            ww=fm.getAvWidth();                                    //+vc53I~
            tvFontH.setText(Integer.toString(hh));                 //+vc53I~
            tvFontW.setText(Integer.toString(ww));                 //+vc53I~
            if (valuecellh==0)                                     //+vc53I~
                tempcellh=calcCellH(tempfontsize);                 //+vc53I~
            if (valuecellw==0)                                     //+vc53I~
                tempcellw=calcCellW(tempfontsize,valuecellh);      //+vc53I~
        }                                                          //+vc53I~
        else //break;                                              //+vc53I~
        if (Pviewid== R.id.FONT_CELLH)                             //+vc53I~
        {                                                          //+vc53I~
            if (valuecellh==Pvalue)                                //+vc53I~
                return;                                            //+vc53I~
            valuecellh=tempcellh=Pvalue;                           //+vc53I~
            if (valuefontsize==0)                                  //+vc53I~
                tempfontsize=calcFontSize(valuecellh,valuecellw);     //re-evaluate by parm value//+vc53I~
            if (valuecellh==0)                                     //+vc53I~
                tempcellh=calcCellH(tempfontsize);                 //+vc53I~
            if (valuecellw==0)                                     //+vc53I~
                tempcellw=calcCellW(tempfontsize,valuecellh);      //+vc53I~
        }                                                          //+vc53I~
        else //break;                                              //+vc53I~
        if (Pviewid== R.id.FONT_CELLW)                             //+vc53I~
        {                                                          //+vc53I~
            if (valuecellw==Pvalue)                                //+vc53I~
                return;                                            //+vc53I~
            valuecellw=tempcellw=Pvalue;                           //+vc53I~
            if (valuefontsize==0)                                  //+vc53I~
                tempfontsize=calcFontSize(valuecellh,valuecellw);     //re-evaluate by parm value//+vc53I~
            if (valuecellh==0)                                     //+vc53I~
                tempcellh=calcCellH(tempfontsize);                 //+vc53I~
            if (valuecellw==0)                                     //+vc53I~
                tempcellw=calcCellW(tempfontsize,valuecellh);      //+vc53I~
        }                                                          //+vc53I~
//          break;                                                 //+vc53I~
//      }                                                          //+vc53I~
        drawSamplePanel(tempfont,tempcellw,tempcellh,templigature);                 //~1731R~//~1803R~
    }                                                              //~1730M~
//***************                                                  //~1730M~
	@Override                                                      //~1730M~
	public void onSpinnerItemSelected(int Ppos)                    //~1730I~
    {                                                              //~1730M~
    	String newname=spinnerData[Ppos];                            //~1730M~//~1804R~
    	if (tempfontname.equals(newname))                          //~1804I~
        	return;                                                //~1804I~
    	tempfontname=newname;                                      //~1804I~
        tempfont=new Font(tempfontname,tempfontstyle,tempfontsize); //~1730M~//~1803R~
        drawSamplePanel(tempfont,tempcellw,tempcellh,templigature);                 //~1730R~//~1803R~
    }                                                              //~1730M~
////***************                                                //~1731R~
//    private void setRadioButtonListener()                        //~1731R~
//    {                                                            //~1731R~
//        rgFontStyle.setOnCheckedChangeListener(                  //~1731R~
//            new RadioGroup.OnCheckedChangeListener()             //~1731R~
//                {                                                //~1731R~
//                    @Override                                    //~1731R~
//                    public void onCheckedChanged(RadioGroup Prg,int PcheckedId)//~1731R~
//                    {                                            //~1731R~
//                        if (Dump.Y) Dump.println("RadioButton id="+Integer.toHexString(PcheckedId));//~1731R~
//                        fontstyleSelected(PcheckedId);           //~1731R~
//                    }                                            //~1731R~
//                }                                                //~1731R~
//                                        );                       //~1731R~
//    }                                                            //~1731R~
//***************                                                    //~1731I~
	@Override                                                      //~1731I~
    public void onRadioButtonsChanged(int Pviewid)                 //~1731I~
    {                                                              //~1730M~
//        switch (Pviewid)                                           //~1731R~//+vc53R~
//        {                                                          //~1730M~//+vc53R~
//        case R.id.FONT_STYLE_BOLD:                                 //~1730M~//+vc53R~
//            tempfontstyle=Font.BOLD;                                   //~1730M~//+vc53R~
//            tempstylename=Font.Bold;                               //~1730I~//+vc53R~
//            break;                                                 //~1730M~//+vc53R~
//        case R.id.FONT_STYLE_ITALIC:                               //~1730M~//+vc53R~
//            tempfontstyle=Font.ITALIC;                                 //~1730M~//+vc53R~
//            tempstylename=Font.Italic;                             //~1730I~//+vc53R~
//            break;                                                 //~1730M~//+vc53R~
//        case R.id.FONT_STYLE_BOLDITALIC:                           //~1730M~//+vc53R~
//            tempfontstyle=Font.BOLD_ITALIC;                             //~1730M~//+vc53R~
//            tempstylename=Font.BoldItalic;                         //~1730I~//+vc53R~
//            break;                                                 //~1730M~//+vc53R~
//        default:                                                   //~1730M~//+vc53R~
//            tempfontstyle=Font.PLAIN;                                  //~1730M~//+vc53R~
//            tempstylename=Font.Normal;                             //~1730I~//+vc53R~
//        }                                                          //~1730M~//+vc53R~
//      switch (Pviewid)                                           //+vc53I~
//      {                                                          //+vc53I~
        if (Pviewid== R.id.FONT_STYLE_BOLD)                        //+vc53I~
        {                                                          //+vc53I~
            tempfontstyle=Font.BOLD;                               //+vc53I~
            tempstylename=Font.Bold;                               //+vc53I~
        }                                                          //+vc53I~
        else //break;                                              //+vc53I~
        if (Pviewid== R.id.FONT_STYLE_ITALIC)                      //+vc53I~
        {                                                          //+vc53I~
            tempfontstyle=Font.ITALIC;                             //+vc53I~
            tempstylename=Font.Italic;                             //+vc53I~
        }                                                          //+vc53I~
        else //break;                                              //+vc53I~
        if (Pviewid== R.id.FONT_STYLE_BOLDITALIC)                  //+vc53I~
        {                                                          //+vc53I~
            tempfontstyle=Font.BOLD_ITALIC;                        //+vc53I~
            tempstylename=Font.BoldItalic;                         //+vc53I~
        }                                                          //+vc53I~
        else //break;                                              //+vc53I~
//      default:                                                   //+vc53I~
        {                                                          //+vc53I~
            tempfontstyle=Font.PLAIN;                              //+vc53I~
            tempstylename=Font.Normal;                             //+vc53I~
        }                                                          //+vc53I~
//      }                                                          //+vc53I~
    	if (samplePanel!=null)	//not initial check                //~1731I~
        {                                                          //~1731I~
        	tempfont=new Font(tempfontname,tempfontstyle,tempfontsize);//~1731R~//~1803R~
	        drawSamplePanel(tempfont,tempcellw,tempcellh,templigature);             //~1731R~//~1803R~
        }                                                          //~1731I~
    }                                                              //~1730M~
//*********                                                        //~1730I~
    private void drawSamplePanel(Font Pfont,int Pcellw,int Pcellh,boolean Pligature) //~1730I~//~1803R~
    {                                                              //~1730I~
        int xx,yy,baseline,bgcolor,rulercolor;//~1730I~//~1803R~
        String str;                                                //~1730I~
    //********************                                         //~1730I~
        bgcolor=Gxeh.Mbgcolor;                                     //~1730M~
        rulercolor=Gxeh.Mrulercolor;                               //+1730I~//~1730M~
        samplePanel.fillRect(bgcolor);                             //~1730I~
    	samplePanel.setFont(Pfont);                                //~1730I~
    	baseline=samplePanel.getBaseline();                                           //~1730I~//~1803R~
        xx=0;                                                      //~1730I~
                                                                   //~1822I~
        str=fontSampleText;                                        //~1803R~
        yy=baseline;                                               //~1803R~
    	samplePanel.drawString(str,xx,yy,Color.WHITE,Pcellw,Pligature);//~1730R~//~1803R~
                                                                   //~1822I~
        str=fontSampleText2;                                       //~1822R~
        yy+=Pcellh;                                                //~1803I~
    	samplePanel.drawString(str,xx,yy,Color.WHITE,Pcellw,false);//~1822R~
                                                                   //~1822I~
        str=fontSampleText+" (ColW="+Pcellw+")";                   //~1822I~
        yy+=Pcellh;                                                //~1822I~
    	samplePanel.drawString(str,xx,yy,Color.YELLOW,Pcellw,false);//~1822I~
                                                                   //~1822I~
        str=FONT_SAMPLE_LIGATURE1+FONT_SAMPLE_LIGATURE2;           //~1803I~
        yy+=Pcellh;                                                //~1803I~
    	samplePanel.drawString(str,xx,yy,Color.WHITE,Pcellw,Pligature);//~1730R~//~1803R~
                                                                   //~1822I~
        str=FONT_SAMPLE_LIGATURE2+FONT_SAMPLE_LIGATURE1+" (ColW="+Pcellw+")";           //~1803I~//~1804R~
        yy+=Pcellh;                                                //~1803I~
    	samplePanel.drawString(str,xx,yy,Color.YELLOW,Pcellw,false);   //no ligature//~1803R~
                                                                   //~1822I~
        yy=Pcellh;                                                 //~1730I~
    	samplePanel.drawLine(xx,yy,-1,yy,rulercolor);                  //~1730I~
        yy=0;                                                      //~1730I~
        xx=Pcellw;                                                 //~1730I~
    	samplePanel.drawLine(xx,yy,xx,-1,rulercolor);                  //~1730I~
        samplePanel.invalidate();                                  //~1730I~
    }                                                              //~1730I~
//*********                                                        //~vc2QI~
    public static void onScale(float PscaleFactor/* >1:ZoomOut:Enlarge*/)//~vc2QI~
    {                                                              //~vc2QI~
		if (Dump.Y) Dump.println("Axegxedlg.onScale scaleFactor="+PscaleFactor);//~vc2QI~
        if (PscaleFactor==(float)1.0)                           //~vc2QI~
        	return;                                                //~vc2QI~
        if (Dump.Y) Dump.println("Axegxedlg Font="+Gxeh.Mfontstyle+",size="+Gxeh.Mfontheight+",style="+Gxeh.Mstyle);//~vc2QI~
        if (Dump.Y) Dump.println("Axegxedlg cellh="+Gxeh.Mcellh+",cellw="+Gxeh.Mcellw);//~vc2QI~
        Font font=Gxeh.Gfontdata[0];                               //~vc2QI~
        String fontname=font.name;                                 //~vc2QI~
        int fontstyle=font.getStyle();                          //~vc2QI~
        int fontsize=font.getSize();                               //~vc2QI~
        if (Dump.Y) Dump.println("Axgxedlg fontname="+fontname+",fontstype="+fontstyle+",fontsize="+fontsize);//~vc2QI~
        int newfontsize;                                           //~vc2QI~
        if (PscaleFactor>(float)1.0)                               //~vc2QI~
        	newfontsize=fontsize+INCL_FONTSIZE;                    //~vc2QI~
        else                                                       //~vc2QI~
        	newfontsize=Math.max(MIN_FONTSIZE,fontsize-INCL_FONTSIZE);//~vc2QI~
        if (Dump.Y) Dump.println("Axgxedlg newfontsize="+newfontsize);//~vc2QI~
	    Gxeh.Mfontheight=newfontsize;                              //~vc2QI~
        double rate=(double)newfontsize/fontsize;                  //~vc2QI~
        if (Gxeh.Mcellw0!=0)                                       //~vc2QR~
	    	Gxeh.Mcellw0=(int)(rate*Gxeh.Mcellw0);                 //~vc2QR~
        if (Gxeh.Mcellh0!=0)                                       //~vc2QR~
	    	Gxeh.Mcellh0=(int)(rate*Gxeh.Mcellh0);                 //~vc2QR~
        if (Dump.Y) Dump.println("Axgxedlg newfontsize="+newfontsize+",new Mcellw0="+Gxeh.Mcellw0+",new Mcellh0="+Gxeh.Mcellh0);//~vc2QI~
        font=new Font(fontname,fontstyle,Gxeh.Mfontheight);   //~vc2QI~
        Gxeh.Gfontdata[0]=font;                                    //~vc2QI~
        AxeG.axeScreen.xxemain_createfont();	//set Mcellh,Mcellw from Mcellh0,Mcellw0//~vc2QI~
        AxeJNI.notifyOptionChangedFont();                          //~vc2QI~
		AxeG.setParameter(KEY_FONTSIZE,newfontsize);               //~vc2QI~
        AxeJNI.fullDraw(true);                                     //~vc2QI~
    }                                                              //~vc2QI~
                                                                   //~vc2QI~
}                                                                  //~1528R~
