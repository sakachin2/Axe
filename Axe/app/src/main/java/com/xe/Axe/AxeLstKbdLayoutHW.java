//*CID://+vc2GR~: update#= 687;                                    //+vc2GR~
//**********************************************************************//~1528I~//~vc1xR~
//vc2G 2020/08/23 chk validity of unicode value                    //+vc2GI~
//vc2i 2020/07/26 restore default when Shift colomn deleted        //~vc2iI~
//vc2g 2020/07/26 AltGr key option                                 //~vc2gI~
//vc2e 2020/07/18 keycode d8(x5c-Yen) is reject                    //~vc29I~
//vc29 2020/07/12 Alt+u generate unicode a8,xe use Alt+u as function key. so ignore unicode by Alt+//~vc29I~
//vc26 2020/07/11 mix AxeKbdDialog and AxeKbdDialogFix(apply map of AxeLstKbdLayout)//~vc26I~
//vc24 2020/07/11 no focus warining at kbd input                   //~vc24I~
//vc23 2020/07/10 HardKey list row sequence change, char ken then extended ked//~vc23I~
//vc22 2020/07/10 send kbd msg from hardkbd                        //~vc22I~
//vc1z 2020/07/09 ext key by not gdkkey but index onmap            //~vc1zI~
//vc1x 2020/07/08 member variable is initialized when defualt constructor defined(not non default constructor is define)//~vc1xI~
//vc1w 2020/07/06 AxeKbd updatelog extends KbdLayoutHW             //~vc1wI~
//vc1t 2020/07/02 multi column EditText ListView callback OnFocusListener called twice True then False(mey be bug)//~vc1tI~
//                requestFocus after short time may fix it but SoftKbd popup could not be protected(can be disapper after once popupped)//~vc1tI~
//                Try to receive input by hard/soft keyboard by dummy(InVisible) EditText then send to ListView.//~vc1tI~
//                Receive HardKbd input by textview(requestFocus), //~vc1tI~
//                By IME button set dummy EditText to focusable and receive from SoftKbd//~vc1tI~
//vc1s 2020/06/29 hardKeyboard layout;set default                  //~vc1sI~
//**********************************************************************//~vaasI~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1109R~//~1528I~

import com.ahsv.utils.UView;
import com.ahsv.utils.Utils;
import com.xe.Axe.kbd.AxeKbdDialogHW;
import com.xe.Axe.kbd.AxeKbdKey;

import android.app.Dialog;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;

import static android.view.KeyEvent.*;
import static com.xe.Axe.Gxeh.*;

//~1528I~
                                                                   //~1528I~
public class AxeLstKbdLayoutHW extends AxeList                     //~vc1sR~
{                                                                  //~1528I~
    public  static final int HWKEYID_GDK=AxeKeyValue.KBF_GDK;  //0x20000000    //~vc1tR~
    public  static final int HWKEYID_UNICODE=AxeKeyValue.KBF_UNICODE;//0x40000000//~vc1tI~//~vc26R~
//	private static final String PREFKEY="KB";                      //~1919R~//~vc1sR~
  	protected static final String PREFKEY="KBHW";                    //~vc1sI~//~vc1wR~
//	private static final String PREFKEYSUM="KBSUM";                //~1923I~
	public  static final int ROWID =R.layout.listrowkbdlayouthw;//~1529I~//~1919M~//~vc1sR~
	protected static final int COLID_NORMAL=R.id.Normal;             //~vc1sR~//~vc1wR~
	protected static final int COLID_SHIFT=R.id.Shift;               //~vc1sR~//~vc1wR~
	protected static final int COLID_ALTGR=R.id.AltGr;               //~vc1sR~//~vc1wR~
	protected static final int COLID_SHIFTALTGR=R.id.ShiftAltGr;     //~vc1sR~//~vc1wR~
	private static final int[] specialKey=new int[]{0x09,0x0a,0x20,AxeKbdDialogHW.KEYCODE_BACKSPACE};//~vc1sR~
//  private static final String[] specialKeyName=new String[]{"(Tab)","(Enter)","(Space)","(BkSp)"};//~vc1sR~//~vc1tR~//~vc23R~
    private static final String[] specialKeyName=new String[]{"Tab","Enter","Space","BkSp"};//~vc23I~
                                                                   //~1809I~
//    public static final String[] swipedest={                        //~1919I~//~vc1sR~
//                                            "Base",                 //~1919I~//~vc1sR~
////                                          "Shift+",               //~1919I~//~vaasR~//~vc1sR~
//                                            "L:Shift",             //~vaasR~//~vc1sR~
//                                            "R:Symbol",            //~vaasR~//~vc1sR~
////                                          "AltGr+",              //~1919I~//~vaasR~//~vc1sR~
//                                            "U:AltGr",             //~vaasR~//~vc1sR~
////                                          "S+AltGr+",            //~1919I~//~vaasR~//~vc1sR~
//                                            "D:S+AltGr",           //~vaasR~//~vc1sR~
////                                          "Flick-Down",          //~1A11R~//~vaasR~//~vc1sR~
//                                    };                           //~vc1sR~
	public final static String[] tgtkeynametbl=AxeKbdKey.SspinnerData;//~1919I~
//    private static String[] keyNames;                            //~1920R~
//    private static int[][] flagedKeyCodes;  //extkey is flaged index value//~1923R~//~vc1tR~
//    private static int[][] flagedKeyCodesDefault;                  //~1923R~//~vc1tR~
//  private static int[] keyIndexMap;	//list index --> keyindex+swipeindex//~1920R~//~vc1tR~
//  private static String[] keyLabelTbl;                              //~1920I~//~vc1tR~
    protected int mapSize;                                    //~1920I~//~vc1tR~//~vc1wR~
    private static boolean existPrefData;                                  //~1923I~
                                                                   //~1809I~
//  private int rowSize=10;                                        //~vc1tR~
                                                                   //~1529I~
//  private TextView selectedView2;                                //~1816R~//~vc1sR~
	protected AxeSpinner spinner;                                    //~1816R~//~vc1wR~
	protected int spinnerPos;                                 //~1814R~//~vc1sR~//~vc1wR~
//  private boolean reset;                                         //~1923I~//~vc1tR~
    private static int[][] defaultMap;                             //~vc1tR~
//  private int[][] currentMap;                                    //~vc1tI~//~vc22R~
    private static int[][] currentMap;                             //~vc22I~
                                                                   //~vc1sI~
    protected int[][] listViewData;                                  //~vc1sI~//~vc1wR~
    protected static final int LVDI_KEYCODE=0;                       //~vc1sI~//~vc1wR~
    protected static final int LVDI_KEYCHAR=1;                       //~vc1sI~//~vc1wR~
    protected int kbdSize;     //listViewData size                //~1920R~//~vc1wI~
//  private boolean swOnceFocused;                                 //~vc1sI~//~vc1tR~
    protected AxeDlgKbdLayoutHW axeDialog;                           //~vc1sI~//~vc1wR~
//  private int dialogHeight;                                      //~vc1sI~//~vc1tR~
//  private boolean initialFocus;                                  //~vc1sI~//~vc1tR~
    protected boolean isUpdateAll;	//reset to default             //~vc1tI~//~vc1xR~
//  protected int maxMod=AxeKbdDialogHW.METACTR;     //3           //~vc1wR~//~vc22R~
    protected static int maxMod=AxeKbdDialogHW.METACTR;     //3    //~vc22I~
    protected int maxCol=1+maxMod;    //RowCol+normal+4swipe       //~vc1wI~
	protected int colNormal=LVDI_KEYCHAR;                             //~vc1wI~
//*****************                                                //~vc1wI~
    public AxeLstKbdLayoutHW()                                     //~vc1xR~
    {
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.defaultConstructor");//~vc1xR~
    }                                                              //~vc1xR~
//*****************                                                //~1813I~
//    public AxeLstKbdLayoutHW(int PdialogId,ViewGroup PlayoutView,int Pmenuid,int Prowid)                                              //~1112I~//~1612I~//~1919R~//~vc1sR~//~vc1wR~//~vc1xR~
//    {                                                              //~1612I~//~vc1xR~
//        super(PdialogId,PlayoutView,Pmenuid,Prowid,true/*editableRow*/,true/*clickable*/);//~1919R~//~1B02R~//~vc1xR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.constructor");  //~vc1sI~//~vc1xR~
//    }                                                              //~1612I~//~vc1xR~
    public static AxeLstKbdLayoutHW newInstance(int PdialogId,ViewGroup PlayoutView,int Pmenuid,int Prowid)//~vc1xR~
    {                                                              //~vc1xR~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.newInstance"); //~vc1xR~
        AxeLstKbdLayoutHW al=new AxeLstKbdLayoutHW();              //~vc1xR~
        al.initInstance(PdialogId,PlayoutView,Pmenuid,Prowid,true/*editableRow*/,true/*clickable*/);//~vc1xR~
        return al;                                                 //~vc1xR~
    }                                                              //~vc1xR~
//*****************                                                //~1528I~
    public static AxeLstKbdLayoutHW setupListView(AxeDlgKbdLayoutHW Pdialog,AxeSpinner Pspinner,int PdialogId,ViewGroup PlayoutView)//~1612R~//~1919R~//~vc1sR~//~vc1wR~
    {                                                              //~1528I~//~vc1wR~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setupListView");//~vc1wI~
//      AxeLstKbdLayoutHW al=new AxeLstKbdLayoutHW(PdialogId,PlayoutView,0/*Shift menuId*/,ROWID);//~1612R~//~1919R~//~vc1sR~//~vc1xR~
        AxeLstKbdLayoutHW al=AxeLstKbdLayoutHW.newInstance(PdialogId,PlayoutView,0/*Shift menuId*/,ROWID);//~vc1xR~
        al.spinner=Pspinner;                                       //~1814R~//~vc1xR~
        al.axeDialog=Pdialog;                                      //~vc1sI~//~vc1xR~
        return al;                                                 //~1528I~//~vc1xR~
    }                                                              //~1528I~//~vc1xR~
//*****************                                                //~1612I~
    @Override  //AxeList                                                    //~1612I~//~vc1wR~
    public void setListViewData()	//fromAxeList constructor      //~1920R~
    {                                                              //~1612I~
        getKbdLayout();                                            //~1920M~
		setListViewData(this);	//fill arraylist for KbdLayout//~1612R~//~1919R~
    }                                                              //~1612I~
    //*****************************                                //~1809R~
//    public void add(int Psource,int Ptarget)                       //~1613R~//~vc1sR~
//    {                                                              //~1612I~//~vc1sR~
//        ListData linedata=new ListData(Psource,Ptarget);           //~1612I~//~vc1sR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.add src="+Integer.toHexString(Psource)+",tgt="+Integer.toHexString(Ptarget));//~1612I~//~1919R~//~vc1sR~
//        super.add(linedata);                                       //~1612I~//~vc1sR~
//    }                                                              //~1612I~//~vc1sR~
    protected void add(int Ppos,int[] Pmap/*listViewData*/)                           //~vc1sR~//~vc1wR~
    {                                                              //~vc1sI~
    	String str0,str1,str2,str3;                                //~vc1sI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.add pos="+Ppos+",map="+ Utils.toHexString(Pmap));//~vc1sI~//~vc1tR~//~vc1wR~
        int key=Pmap[LVDI_KEYCHAR];                                //~vc1sR~
        str0=chkKeyName(key);                                      //~vc1sI~
        if (str0==null)                                             //~vc1sI~
//          str0=String.valueOf((char)key);                        //~vc1sR~//~vc22R~
        	str0=getKeyname(key);                                  //~vc22I~
        int withmeta;                                              //~vc1sI~
        withmeta=Pmap[LVDI_KEYCHAR+1];                             //~vc1sR~
        str1=withmeta==0 ? "" : getKeyname(withmeta);              //~vc1sR~
        withmeta=Pmap[LVDI_KEYCHAR+2];                             //~vc1sR~
        str2=withmeta==0 ? "" : getKeyname(withmeta);              //~vc1sR~
        withmeta=Pmap[LVDI_KEYCHAR+3];                             //~vc1sR~
        str3=withmeta==0 ? "" : getKeyname(withmeta);              //~vc1sR~
        ListData linedata=new ListData(key,str0,str1,str2,str3);   //~vc1sI~
        linedata.pos=Ppos;                                         //~vc1sI~
        super.add(linedata);                        //~vc1sI~
    }                                                              //~vc1sI~
	//******************                                           //~1809I~
    @Override                                                      //~1809I~
    public void onItemSelected(int Ppos,ListData Pitem)             //~1816R~//~1824R~
    {                                                              //~1809I~
        if (Dump.Y)Dump.println("AxeLstKbdLayoutHW onItemSelected pos="+Ppos);//~1816R~//~1919R~//~vc1sR~
//        selectedView2=(Pitem.textView2);                           //~1816R~//~vc1sR~
//        csrPos=Ppos;                                               //~1810I~//~vc1sR~
//        setSpinnerPrompt(csrPos);                                  //~1814I~//~vc1sR~
    }                                                              //~1809I~
	//******************                                           //~1824I~
    @Override                                                      //~1824I~
    public void onItemClicked(int Ppos,ListData Pitem)             //~1824I~
    {                                                              //~1824I~
        if (Dump.Y)Dump.println("AxeLstKbdLayoutHW onItemClicked pos="+Ppos);//~1919R~//~vc1sR~
//        selectedView2=(Pitem.textView2);                           //~1824I~//~vc1sR~
//        csrPos=Ppos;                                               //~1824I~//~vc1sR~
//        setSpinnerPrompt(csrPos);                                  //~1824I~//~vc1sR~
//        notifyDataChanged();    //super                            //~1824R~//~vc1sR~
    }                                                              //~1824I~
//    //******************                                               //~1612I~//~1809R~//~vc1sR~
//    @Override                                                      //~1612I~//~vc1sR~
//    public void updateTbl(int[] Pnewtbl)                           //~1612I~//~vc1sR~
//    {                                                              //~1612I~//~vc1sR~
//        if (reset)                                                 //~1923I~//~vc1sR~
//            clearPreference(flagedKeyCodes);                       //~1923R~//~vc1sR~
//        updateKbdLayout(Pnewtbl);                       //~1612R~  //~1919R~//~vc1sR~
//    }                                                              //~1612I~//~vc1sR~
//************************************                             //~1923R~
//*adaptor data is flagedKeyCode                                   //~1923I~
//************************************                             //~1923I~
    @Override                                                      //~1612I~
    protected int chkInputValue(int Ppos,String Pvalue,boolean Ptoast)         //~1613I~//~1A29R~//~vc1wR~
    {                                                              //~1612I~
    	int intval;                                                //~1920I~
//    	intval=AxeKbdKey.getSpinnerKey(Pvalue);                 //~1920I~//~vc1sR~//~vc1zR~
      	intval=AxeKbdKey.getSpinnerIndex(Pvalue);                  //~vc1zI~
        if (intval>0)                                                 //~1920I~//~vc1sR~
        	intval=intval | HWKEYID_GDK;		//=0x20000000;     //~vc1sR~
        else                                                       //~1920I~
        {                                                          //~1920I~
	    	intval=AxeKey.chkInputCharCode(Pvalue); //KEYVALUE_ERR if hex numeric err//~1920I~
        }                                                          //~1920I~
        if (Dump.Y)Dump.println("AxeLstKbdLayoutHW chkInputValue Str="+Pvalue+",int="+Integer.toHexString(intval));//~1919I~//~vc1sR~
 		return	intval;                                            //~1919R~
    }                                                              //~1612I~
//******************                                               //~1531I~
//	@Override                                                      //~1612I~
//    public String getKeyname(int Ppos,int Pfldid,int Pkey,String Perr)//~1602R~//~1A29R~//~vc1sR~
//    {                                                              //~1531I~                                  //~1920I~//~vc1sR~
//        String label;                                              //~1920I~//~vc1sR~
//        if (Pfldid==1)      //left field                                         //~1612R~//~1809R~//~vc1sR~
//        {                                                          //~1920I~//~vc1sR~
//            if (Pkey>=mapSize)                                     //~1920I~//~vc1sR~
//                return "";                                         //~1920I~//~vc1sR~
//            int mapidx=keyIndexMap[Pkey];                          //~1920I~//~vc1sR~
//            int row=mapidx>>8;                                     //~1920R~//~vc1sR~
//            int col=mapidx & 0xff;                                   //~1920R~//~vc1sR~
//            if (col==0)                                            //~1920I~//~vc1sR~
//            {                                                      //~1920I~//~vc1sR~
//                label=keyLabelTbl[row];                            //~1920I~//~vc1sR~
//                if (label==null)                                   //~1920I~//~vc1sR~
//                    label="";                                      //~1920I~//~vc1sR~
//                label=String.format("%d-%d:",row/rowSize,row%rowSize)+label;//~1920I~//~vc1sR~
//            }                                                      //~1920I~//~vc1sR~
//            else                                                   //~1920I~//~vc1sR~
//            {                                                      //~1920I~//~vc1sR~
////              label="   :"+swipedest[col];                      //~1920R~//~vaasR~//~vc1sR~
//                label="  "+swipedest[col];                         //~vaasI~//~vc1sR~
//            }                                                      //~1920I~//~vc1sR~
//        }                                                          //~1920I~//~vc1sR~
//        else                                                       //~1920I~//~vc1sR~
//        {                                                          //~1920I~//~vc1sR~
//            label=codeToName(Pkey);                                //~1920R~//~vc1sR~
//        }                                                          //~1920I~//~vc1sR~
//        return label;                                              //~1920I~//~vc1sR~
//    }                                                              //~1531I~//~vc1sR~
//    public String getKeyname(int Ppos,int Pfldid,int Pkey,String Perr)//~vc1sR~
//    {                                                            //~vc1sR~
//        int normal=listViewData[Ppos][LVDI_KEYCHAR];             //~vc1sR~
//        String label=String.valueOf((char)normal);               //~vc1sR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW:getKeyname name="+label);//~vc1sR~
//        return label;                                            //~vc1sR~
//    }                                                            //~vc1sR~
    //******************************************************************//~vc1sI~
	private String findKeyGDK(int Pkey)                            //~vc1sI~
    {                                                              //~vc1sI~
    	int idx=AxeKbdKey.getSpinnerIndex(Pkey);                 //~vc1sI~
        String rc=idx>0 ? AxeKbdKey.SspinnerData[idx] : "";        //~vc1sI~
        if (Dump.Y) Dump.println("findKeyGDK key="+Integer.toHexString(Pkey)+",kename="+rc);//~vc1sR~
        return rc;                                                 //~vc1sR~
    }                                                              //~vc1sI~
    //******************************************************************//~vc1sI~
    public String getKeyname(int Pkey)                             //~vc1sI~
    {                                                              //~vc1sI~
    	String rc="";                                               //~vc1sI~
        int key=Pkey & ~HWKEYID_GDK;                               //+vc2GI~
//  	if ((Pkey & HWKEYID_GDK)!=0)                               //~vc1sR~//+vc2GR~
    	if ((Pkey & HWKEYID_GDK)!=0 && key<AxeKbdKey.SspinnerData.length)//+vc2GI~
        {                                                          //~vc1sI~
//      	int key=Pkey & ~HWKEYID_GDK;                           //~vc1sR~//+vc2GR~
//          rc=findKeyGDK(key);                                    //~vc1sI~//~vc1zR~
            rc=AxeKbdKey.SspinnerData[key];                        //~vc1zI~
        }                                                          //~vc1sI~
        else                                                       //~vc1sI~
    	if ((Pkey & HWKEYID_UNICODE)!=0)                           //~vc1tI~
        	rc=getKeynameUnicode(Pkey);                            //~vc1tI~
        else                                                       //~vc1tI~
	        rc=String.valueOf((char)Pkey);                         //~vc1sI~
        if (Dump.Y) Dump.println("getKeyname key="+Integer.toHexString(Pkey)+",rc="+rc);//~vc1sI~
        return rc;                                                 //~vc1sI~
    }                                                              //~vc1sI~
    //**************                                               //~1920I~
//    public String getSpinnerTitle(int Ppos)                        //~1920I~//~vc1sR~
//    {                                                              //~1920I~//~vc1sR~
//        String label;                                              //~1920I~//~vc1sR~
//        int mapidx=keyIndexMap[Ppos];                              //~1920I~//~vc1sR~
//        int row=mapidx>>8;                                         //~1920I~//~vc1sR~
//        int col=mapidx & 0xff;                                     //~1920I~//~vc1sR~
//        label=keyLabelTbl[row];                             //~1920I~//~vc1sR~
//        if (label==null)                                           //~1920I~//~vc1sR~
//            label=String.format("%d-%d",row/rowSize,row%rowSize);  //~1920I~//~vc1sR~
//        label+=":"+swipedest[col];                                 //~1920I~//~vc1sR~
//        return label;                                              //~1920I~//~vc1sR~
//    }                                                              //~1920I~//~vc1sR~
//    public String getSpinnerTitle(int Ppos,int Pcol)               //~vc1sI~//~vc1tR~
//    {                                                              //~vc1sI~//~vc1tR~
//        int normal=listViewData[Ppos][LVDI_KEYCHAR];               //~vc1sR~//~vc1tR~
//        String label=String.valueOf((char)normal);                 //~vc1sI~//~vc1tR~
//        label+="+"+ AxeKbdDialogHW.metaTblLabel[Pcol];             //~vc1sI~//~vc1tR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW:getSpinnerTitle label="+label);//~vc1sI~//~vc1tR~
//        return label;                                              //~vc1sI~//~vc1tR~
//    }                                                              //~vc1sI~//~vc1tR~
//*************                                                    //~1919I~
//    public void setListViewData(AxeLstKbdLayoutHW Plist)             //~1919I~//~vc1sR~
//    {                                                              //~1919I~//~vc1sR~
//        for (int ii=0;ii<mapSize;ii++)                             //~1920R~//~vc1sR~
//        {                                                          //~1809I~//~vc1sR~
//            int mapidx=keyIndexMap[ii];                            //~1920I~//~vc1sR~
//            int row=mapidx>>8;                                     //~1920I~//~vc1sR~
//            int col=mapidx & 0xff;                                 //~1920I~//~vc1sR~
//            int codeidx=flagedKeyCodes[row][col];                  //~1923R~//~vc1sR~
//            Plist.add(ii,codeidx);                                //~1920R~//~vc1sR~
//        }                                                          //~1809I~//~vc1sR~
//    }                                                              //~1809I~//~vc1sR~
    public void setListViewData(AxeLstKbdLayoutHW Plist)           //~vc1sI~
    {                                                              //~vc1sI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW:setListViewData kbdsize="+kbdSize+",listViewData="+Utils.toHexString(listViewData));//~vc1wR~
    	for (int ii=0;ii<kbdSize;ii++)                             //~vc1sR~
        {                                                          //~vc1sI~
        	add(ii,listViewData[ii]);                              //~vc1sR~
        }                                                          //~vc1sI~
    }                                                              //~vc1sI~
//*************                                                    //~1809I~
//    public void getKbdLayout()                                     //~1919R~//~vc1sR~
//    {                                                              //~1809I~//~vc1sR~
////        if (keyIndexMap!=null)                                   //~1923R~//~vc1sR~
////            return;                                              //~1923R~//~vc1sR~
//        AxeKbdDialog axeKbdDialog=AxeG.axeIME.getXeKbd();           //~1919I~//~vc1sR~
//        int[][] flagedkeycodes=axeKbdDialog.getKbdLayoutCodeTbl(); //~1923R~//~vc1sR~
//        kbdSize=flagedkeycodes.length;                             //~1923R~//~vc1sR~
//        flagedKeyCodes=initKeynameTbl(flagedkeycodes);//codetbl for update//~1923R~//~vc1sR~
//    }                                                              //~1919I~//~vc1sR~
//*************                                                      //~vc1sI~
//  public void getKbdLayout()                                     //~vc1sI~//~vc1wR~
    protected void getKbdLayout()                                  //~vc1wR~
    {                                                              //~vc1sI~
//        if (currentMap!=null)                                      //~vc1sI~//~vc22R~
//            return;                                                //~vc1sI~//~vc22R~
//        if (defaultMap==null)                                      //~vc1tI~//~vc22R~
//            defaultMap=AxeKbdDialogHW.getDefaultMap();                   //~vc1sI~//~vc1tR~//~vc22R~
////      mapSize=defaultMap.length;                                 //~vc1sI~//~vc1tR~//~vc22R~
//        loadPreference();                                          //~vc1sI~//~vc22R~
		getCurrentMap();                                           //~vc22I~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW getKbdLayout defaultMap="+Utils.toHexString(defaultMap));//~vc1tI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW getKbdLayout currentMap="+Utils.toHexString(currentMap));//~vc1tI~
//      getListViewData();                                         //~vc1sI~//~vc1tR~
        getListViewData(currentMap);                               //~vc1tI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW getKbdLayout listViewData="+Utils.toHexString(listViewData));//~vc1sR~//~vc1wR~
    }                                                              //~vc1sI~
//*************************************************************    //~vc22I~
	public static int[][] getCurrentMap()                          //~vc22I~
    {                                                              //~vc22I~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.getCurrentMap currentMap="+currentMap);//~vc22I~
        if (currentMap!=null)                                      //~vc22I~
        {                                                          //~vc22I~
//          if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.getCurrentMap currentMap="+Utils.toHexString(currentMap));//~vc22I~//~vc26R~
        	return currentMap;                                     //~vc22I~
        }                                                          //~vc22I~
        if (defaultMap==null)                                      //~vc22I~
	        defaultMap=AxeKbdDialogHW.getDefaultMap();             //~vc22I~
        loadPreference();                                          //~vc22I~
	    if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.getCurrentMap currentMap="+Utils.toHexString(currentMap));//~vc22I~
        return currentMap;                                         //~vc22I~
    }                                                              //~vc22I~
//*************************************************************    //~1923R~
//*copy flagedCodeTbl for temporary update                         //~1923I~
//*create indexing adaptor list to flagedCodeTbl                   //~1923I~
//*************************************************************    //~1923I~
//    public int[][] initKeynameTbl(int [][] Pflagedkeycodes)        //~1923R~//~vc1sR~
//    {                                                              //~1919I~//~vc1sR~
//        int[][] flagedkeycodes=new int[kbdSize][AxeKbdView.MAXSWIPE+1];//~1923R~//~vc1sR~
//        int sz=kbdSize*(AxeKbdView.MAXSWIPE+1);                    //~1920M~//~vc1sR~
//        keyIndexMap=new int[sz];                                   //~1920R~//~vc1sR~
//        keyLabelTbl=new String[kbdSize];                           //~1920I~//~vc1sR~
//        int mapidx=0;                                              //~1920I~//~vc1sR~
//        for (int ii=0;ii<kbdSize;ii++)                                 //~1919I~//~vc1sR~
//        {                                                          //~1919I~//~vc1sR~
//            int code=Pflagedkeycodes[ii][0];                       //~1923R~//~vc1sR~
//            flagedkeycodes[ii][0]=code;                            //~1923R~//~vc1sR~
//            keyLabelTbl[ii]=null;                                  //~1920I~//~vc1sR~
//            if (code==AxeSoftKbd.KEYID_FIXED)                      //~1919I~//~vc1sR~
//                continue;                                          //~1919I~//~vc1sR~
//            keyLabelTbl[ii]=codeToName(code);                      //~1920I~//~vc1sR~
//            for (int jj=0;jj<=AxeKbdView.MAXSWIPE;jj++)            //~1920R~//~vc1sR~
//            {                                                      //~1919I~//~vc1sR~
//                keyIndexMap[mapidx++]=(ii<<8)+jj;                  //~1920I~//~vc1sR~
//                flagedkeycodes[ii][jj]=Pflagedkeycodes[ii][jj];          //~1923R~//~vc1sR~
//            }                                                      //~1919I~//~vc1sR~
//        }                                                          //~1919I~//~vc1sR~
//        mapSize=mapidx;                                            //~1920I~//~vc1sR~
//        return flagedkeycodes;                                     //~1923R~//~vc1sR~
//    }                                                              //~1919I~//~vc1sR~
////********************************************************         //~1923R~//~vc1xR~
////*falgedKeyCode(adaptor data) to keyname                          //~1923I~//~vc1xR~
////********************************************************         //~1923I~//~vc1xR~
//    private String codeToName(int Pcode)                           //~1919I~//~vc1xR~
//    {                                                              //~1919I~//~vc1xR~
//        String name;                                               //~1919I~//~vc1xR~
//        if ((Pcode & AxeSoftKbd.KEYID_EXTKEY)!=0)                       //~1919I~//~vc1xR~
//            name=AxeKbdKey.SspinnerData[Pcode & ~AxeSoftKbd.KEYID_EXTKEY];//~1919I~//~vc1xR~
//        else                                                       //~1919I~//~vc1xR~
//        if (Pcode==0)                                              //~1919I~//~vc1xR~
//            name="";                                               //~1919I~//~vc1xR~
//        else                                                       //~1919I~//~vc1xR~
//        if (Pcode<0x80)                                            //~1919I~//~vc1xR~
//            name=String.valueOf((char)Pcode);                      //~1919I~//~vc1xR~
//        else                                                       //~1919I~//~vc1xR~
//            name=String.format("%04x (%s)",Pcode,String.valueOf((char)Pcode));//~1920I~//~vc1xR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW code="+Integer.toHexString(Pcode)+",name="+name);//~1919I~//~vc1sR~//~vc1xR~
//        return name;                                               //~1919I~//~vc1xR~
//    }                                                              //~1919I~//~vc1xR~
//*****************************                                    //~1923R~
//*onClose                                                         //~1923I~
//*actually update data                                            //~1923I~
//*****************************                                    //~1923I~
//    public void updateKbdLayout(int[] Pnewtbl)                     //~1919I~//~vc1sR~
//    {                                                              //~1919I~//~vc1sR~
//        boolean changed=false;                                     //~1920I~//~vc1sR~
//        for (int ii=0;ii<mapSize;ii++)                            //~1920R~//~vc1sR~
//        {                                                          //~1919I~//~vc1sR~
//            int mapidx=keyIndexMap[ii];                            //~1920R~//~vc1sR~
//            int row=mapidx>>8;                                     //~1920I~//~vc1sR~
//            int col=mapidx & 0xff;                                 //~1920I~//~vc1sR~
//            int oldkeyid=flagedKeyCodes[row][col];                 //~1923R~//~vc1sR~
//            int newkeyid=Pnewtbl[ii];                              //~1920R~//~vc1sR~
//            if (newkeyid>0 && newkeyid!=oldkeyid) //updated        //~1920R~//~vc1sR~
//            {                                                      //~1920R~//~vc1sR~
//                changed=true;                                      //~1920I~//~vc1sR~
//                flagedKeyCodes[row][col]=newkeyid;                 //~1923R~//~vc1sR~
//                savePreference(row,col,newkeyid); //extkey flaged value//~1920R~//~vc1sR~
////                if (newkeyid==KeyData.NOT_DEFINED)               //~1920R~//~vc1sR~
////                    keyNames[ii]="";                             //~1920R~//~vc1sR~
////                else                                             //~1920R~//~vc1sR~
////                    keyNames[ii]=codeToName(newkeyid);           //~1920R~//~vc1sR~
//            }                                                      //~1920R~//~vc1sR~
//        }                                                          //~1919I~//~vc1sR~
//        if (changed)                                               //~1920I~//~vc1sR~
//        {                                                          //~1920I~//~vc1sR~
//            AxeProp.putPreference(PREFKEY,"1");   //data saved     //~1923R~//~vc1sR~
//            AxeKbdDialog axeKbdDialog=AxeG.axeIME.getXeKbd();      //~1920I~//~vc1sR~
//            axeKbdDialog.updateKbd(flagedKeyCodes);                //~1923R~//~vc1sR~
//        }                                                          //~1920I~//~vc1sR~
//    }                                                              //~1809I~//~vc1sR~
//************************************************                 //~1920R~
//*from AxeKbdDialog;load saved update data                        //~1A11R~
//************************************************                 //~1920I~
//    public static int[][] loadPreference(int[][] PflagedKeyCodeTbl)//~1923R~//~vc1sR~
//    {                                                              //~1809I~//~vc1sR~
//        String key,value;                                          //~1809I~//~vc1sR~
//        int sz;                                           //~1920R~//~vc1sR~
//    //**************                                             //~vc1sR~
//        saveDefault(PflagedKeyCodeTbl); //save xml data            //~1A11R~//~vc1sR~
//        sz=PflagedKeyCodeTbl.length;                               //~1923R~//~vc1sR~
//        value=AxeProp.getPreference(PREFKEY,null);                 //~1920R~//~vc1sR~
//        if (value==null)                                           //~1920R~//~vc1sR~
//            return null;    //no data                              //~1920R~//~vc1sR~
//        existPrefData=true;                                        //~1923I~//~vc1sR~
//        int[][] flagedkeycodes=new int[sz][AxeKbdView.MAXSWIPE+1]; //~1923R~//~vc1sR~
//        for (int ii=0;ii<sz;ii++)                             //~1919R~//~vc1sR~
//        {                                                          //~1809I~//~vc1sR~
//            for (int jj=0;jj<=AxeKbdView.MAXSWIPE;jj++)                           //~1919I~//~vc1sR~
//            {                                                      //~1919I~//~vc1sR~
//                flagedkeycodes[ii][jj]=-1;                         //~1923R~//~vc1sR~
//                key=Integer.toHexString((ii<<8)+jj);               //~1919I~//~vc1sR~
//                value=AxeProp.getPreference(PREFKEY+key,null);     //~1919R~//~vc1sR~
//                if (value==null)                                   //~1919R~//~vc1sR~
//                    continue;                                      //~1919R~//~vc1sR~
//                int newkey=Utils.hexstrToNum(value,KeyData.NOT_DEFINED);//~1919R~//~vc1sR~
//                flagedkeycodes[ii][jj]=newkey;                            //+1919R~//~1923R~//~vc1sR~
//                if (Dump.Y)Dump.println("AxeLstKbdLayoutHW:loadPreference ii="+ii+",jj="+jj+",new="+Integer.toHexString(newkey)+",unicode=0x"+Integer.toHexString(Pevent.getUnicodeChar())+",unimeta="+Integer.toHexString(Pevent.getUnicodeChar(Pevent.getMetaState())));//~vc22R~
//            }                                                      //~1919I~//~vc1sR~
//        }                                                          //~1809I~//~vc1sR~
//        return flagedkeycodes;                                     //~1923R~//~vc1sR~
//    }                                                              //~1809I~//~vc1sR~
//************************************************                 //~vc1sI~
//  public void loadPreference()                                   //~vc1sI~//~vc1wR~
    private static void loadPreference()                                  //~vc1wI~//~vc22R~
    {                                                              //~vc1sI~
        String key,value;                                          //~vc1sI~
        int sz;                                                    //~vc1sI~
    //**************                                               //~vc1sI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW:loadPreference");//~vc1sI~
        sz=defaultMap.length;                                      //~vc1sI~
        if (Dump.Y)Dump.println("AxeLstKbdLayoutHW:loadPreference before defaurtMap="+Utils.toHexString(defaultMap));//~vc1tI~
//      currentMap=defaultMap.clone();                             //~vc1sI~//~vc1tR~
//      currentMap=new int[defaultMap.length][];                   //~vc1tI~//~vc1zR~
        value=AxeProp.getPreference(PREFKEY,null);                 //~vc1sI~
        if (value==null)                                           //~vc1sI~
        {                                                          //~vc1sI~
    	    currentMap=Utils.cloneArray2(defaultMap);              //~vc1zI~
            return;                                                //~vc1sI~
        }                                                          //~vc1sI~
        currentMap=new int[defaultMap.length][];                   //~vc1zI~
        existPrefData=true;                                        //~vc1sI~
        for (int ii=0;ii<sz;ii++)                                  //~vc1sI~
        {                                                          //~vc1sI~
        	currentMap[ii]=defaultMap[ii].clone();                 //~vc1tI~
            int normal=currentMap[ii][0];                          //~vc1sI~
            if (normal==0)                                         //~vc1sI~
                continue;                                          //~vc1sI~
            for (int jj=1;jj<maxMod;jj++)          //~vc1sR~       //~vc1wR~
            {                                                      //~vc1sI~
                key=ii+"_"+jj;                                     //~vc1sR~
                value=AxeProp.getPreference(PREFKEY+key,null);     //~vc1sI~
                if (Dump.Y)Dump.println("AxeLstKbdLayoutHW:loadPreference ii="+ii+",jj="+jj+",val="+value);//~vc1wI~
                if (value==null)                                   //~vc1sI~
                    continue;                                      //~vc1sI~
                int newkey=Utils.hexstrToNum(value,KeyData.NOT_DEFINED);//~vc1sI~
                currentMap[ii][jj]=newkey;                         //~vc1sR~
                if (Dump.Y)Dump.println("AxeLstKbdLayoutHW:loadPreference ii="+ii+",jj="+jj+",new="+Integer.toHexString(newkey));//~vc1sI~
            }                                                      //~vc1sI~
        }                                                          //~vc1sI~
        if (Dump.Y)Dump.println("AxeLstKbdLayoutHW:loadPreference currentMap="+Utils.toHexString(currentMap));//~vc1sI~//~vc1tR~
        if (Dump.Y)Dump.println("AxeLstKbdLayoutHW:loadPreference after defaurtMap="+Utils.toHexString(defaultMap));//~vc1tI~
    }//loadPreference                                              //~vc1sR~
//************************************************                 //~vc1sI~
//  public void savePreference()                                   //~vc1sI~//~vc1wR~
    protected void savePreference()                                  //~vc1wI~//~vc1xR~
    {                                                              //~vc1sI~
        ListData data;                                             //~vc1sI~
        int[] keys;                                                //~vc1sI~
        boolean swChanged=false;                                   //~vc1sI~
    //**************                                               //~vc1sI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW:savePreference isUpdateAll="+isUpdateAll);//~vc1sI~//~vc1tR~
        if (Dump.Y)Dump.println("AxeLstKbdLayoutHW:savePreference currentMap="+com.ahsv.utils.Utils.toHexString(currentMap));//~vc1sI~
        int sz=arrayData.size();                                   //~vc1sI~
        for (int ii=0;ii<sz;ii++)                                  //~vc1sI~
        {                                                          //~vc1sI~
            data=arrayData.get(ii);                                //~vc1sI~
            if (Dump.Y)Dump.println("AxeLstKbdLayoutHW:savePreference ii="+ii+",isUpadated="+data.isUpdated+",data="+data.toString());//~vc1sI~
          if (!isUpdateAll)                                        //~vc1tI~
            if (!data.isUpdated)                                   //~vc1sI~
            	continue;                                          //~vc1sI~
            keys=listViewData[ii];                                 //~vc1sM~
            int keycode=keys[LVDI_KEYCODE];                       //~vc1sI~
            int[] cmap=currentMap[keycode];                        //~vc1sI~
            if (Dump.Y)Dump.println("AxeLstKbdLayoutHW:savePreference keycode="+keycode+",cmap="+Utils.toHexString(cmap)+",listViewData="+Utils.toHexString(keys));//~vc1sI~//~vc1wR~
            for (int jj=1;jj<maxMod;jj++)          //~vc1sI~       //~vc1wR~
            {                                                      //~vc1sI~
            	int newval=keys[LVDI_KEYCHAR+jj];                  //~vc1sI~
                if (Dump.Y)Dump.println("AxeLstKbdLayoutHW:savePreference err="+data.colerr+",ii="+ii+",jj="+jj+",new="+Integer.toHexString(newval)+",old="+Integer.toHexString(cmap[jj]));//~vc1sR~//~vc1tR~
//              if ((data.colerr & (1<<jj))!=0)                    //~vc1tI~
                if (data.isColErr(jj))                             //~vc1tI~
                	continue;                                       //~vc1tI~
            	if (newval==cmap[jj])                              //~vc1sI~
                	continue;                                      //~vc1sI~
                String key=keycode+"_"+jj;                         //~vc1sR~
                if (Dump.Y)Dump.println("AxeLstKbdLayoutHW:savePreference ii="+ii+",jj="+jj+",new="+Integer.toHexString(newval));//~vc1sR~
                savePreference(PREFKEY+key,newval==0 ? null : Integer.toHexString(newval));//~vc1sR~
                cmap[jj]=newval;                                   //~vc1sI~
                swChanged=true;                                     //~vc1sI~
            }                                                      //~vc1sI~
        }                                                          //~vc1sI~
        if (swChanged)                                             //~vc1sI~
        {                                                          //~vc1sI~
            AxeProp.putPreference(PREFKEY,"1");   //data saved     //~vc1sI~
        }                                                          //~vc1sI~
        if (Dump.Y)Dump.println("AxeLstKbdLayoutHW:savePreference swChanged="+swChanged);//~vc1sI~
    }                                                              //~vc1sI~
    //**************************************************************//~vc1sI~
    //*map index by lisview position                               //~vc1sI~
    //**************************************************************//~vc1sI~
//  public void getListViewData()                                  //~vc1sI~//~vc1tR~
    protected void getListViewData(int[][] Pmap/*default or current*/)//~vc1tI~//~vc1wR~
    {                                                              //~vc1sI~
        String key,value;                                          //~vc1sI~
        int sz,ctr=0;                                          //~vc1sI~
    //**************                                               //~vc1sI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW:getListViewData mapSize="+Pmap.length+",Pmap="+Utils.toHexString(Pmap));//~vc1tR~
//      sz=currentMap.length;                                      //~vc1sI~//~vc1tR~
        sz=Pmap.length;                                            //~vc1tI~
        for (int ii=0;ii<sz;ii++)                                  //~vc1sI~
        {                                                          //~vc1sI~
//          int normal=currentMap[ii][0];                          //~vc1sR~//~vc1tR~
            int normal=Pmap[ii][0];                                //~vc1tI~
            if (normal==0)                                         //~vc1sI~
                continue;                                          //~vc1sI~
            ctr++;                                                 //~vc1sI~
        }                                                          //~vc1sI~
        kbdSize=ctr;                                               //~vc1sM~
        int[][] map=new int[ctr][LVDI_KEYCHAR+maxMod];//~vc1sR~    //~vc1wR~
        ctr=0;                                                     //~vc1sI~
        for (int ii=0;ii<sz;ii++)                                  //~vc1sI~
        {                                                          //~vc1sI~
        	int idx;                                               //~vc1sI~
        	if (ii>=KEYCODE_0 && ii<=KEYCODE_8) //move 0 to next of 9//~vc1sI~
            	idx=ii+1;                                          //~vc1sI~
            else                                                   //~vc1sI~
        	if (ii==KEYCODE_9)                                     //~vc1sI~
            	idx=KEYCODE_0;                                     //~vc1sI~
            else                                                   //~vc1sI~
            	idx=ii;                                            //~vc1sI~
//          int[] map1=currentMap[idx];                            //~vc1sR~//~vc1tR~
            int[] map1=Pmap[idx];                                  //~vc1tI~
            int normal=map1[0];                                    //~vc1sI~
            if (normal==0)                                         //~vc1sI~
                continue;                                          //~vc1sI~
            if (normal>' ' && normal <0x80) //charcter key         //~vc23I~
                ;                                                  //~vc23I~
            else                                                   //~vc23I~
                continue;                                          //~vc23I~
            map[ctr++]=new int[]{idx,normal,map1[1],map1[2],map1[3]};//~vc1sR~
	        if (Dump.Y)Dump.println("AxeLstKbdLayoutHW:getListViewData set ii="+ii+",ctr="+ctr+",normal="+Integer.toHexString(normal)+",map="+Utils.toHexString(map[ctr-1]));//~vc1sI~//~vc1tI~
        }                                                          //~vc1sI~
        for (int ii=0;ii<sz;ii++)                                  //~vc23I~
        {                                                          //~vc23I~
        	int idx;                                               //~vc23I~
//            if (ii>=KEYCODE_0 && ii<=KEYCODE_8) //move 0 to next of 9//~vc23I~
//                idx=ii+1;                                        //~vc23I~
//            else                                                 //~vc23I~
//            if (ii==KEYCODE_9)                                   //~vc23I~
//                idx=KEYCODE_0;                                   //~vc23I~
//            else                                                 //~vc23I~
            	idx=ii;                                            //~vc23I~
            int[] map1=Pmap[idx];                                  //~vc23I~
            int normal=map1[0];                                    //~vc23I~
            if (normal==0)                                         //~vc23I~
                continue;                                          //~vc23I~
            if (normal>' ' && normal <0x80) //charcter key         //~vc23I~
                continue;                                          //~vc23I~
            map[ctr++]=new int[]{idx,normal,map1[1],map1[2],map1[3]};//~vc23I~
	        if (Dump.Y)Dump.println("AxeLstKbdLayoutHW:getListViewData set ii="+ii+",ctr="+ctr+",normal="+Integer.toHexString(normal)+",map="+Utils.toHexString(map[ctr-1]));//~vc23I~
        }                                                          //~vc23I~
        if (Dump.Y)Dump.println("AxeLstKbdLayoutHW:getListViewData data="+Utils.toHexString(map));//~vc1sR~//~vc1tR~
        listViewData=map;                                               //~vc1sI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.getListViewData ctr="+kbdSize);//~vc1sR~
    }                                                              //~vc1sI~
//************************************************                 //~1923I~
//*from softKbd;load saved updata date                             //~1923I~
//************************************************                 //~1923I~
//    public static void clearPreference(int[][] Pcodetbl)           //~1923I~//~vc1sR~
//    {                                                              //~1923I~//~vc1sR~
//        String key;                                          //~1923I~//~vc1sR~
//        int sz;                                                    //~1923I~//~vc1sR~
//    //**************                                               //~1923I~//~vc1sR~
//        if (!existPrefData)                                        //~1923I~//~vc1sR~
//            return;                                                //~1923I~//~vc1sR~
//        sz=Pcodetbl.length;                                        //~1923R~//~vc1sR~
//        for (int ii=0;ii<sz;ii++)                                  //~1923I~//~vc1sR~
//        {                                                          //~1923I~//~vc1sR~
//            for (int jj=0;jj<=AxeKbdView.MAXSWIPE;jj++)            //~1923I~//~vc1sR~
//            {                                                      //~1923I~//~vc1sR~
//                key=Integer.toHexString((ii<<8)+jj);               //~1923I~//~vc1sR~
//                if (AxeProp.getPreference(PREFKEY+key,null)!=null) //~1923I~//~vc1sR~
//                    AxeProp.putPreference(PREFKEY+key,null);       //~1923I~//~vc1sR~
//            }                                                      //~1923I~//~vc1sR~
//        }                                                          //~1923I~//~vc1sR~
//        AxeProp.putPreference(PREFKEY,null);                 //~1923I~//~vc1sR~
//    }                                                              //~1923I~//~vc1sR~
////************************************************                 //~1923I~//~vc1sI~//~vc1tR~
////*from softKbd;load saved updata date                             //~1923I~//~vc1sI~//~vc1tR~
////************************************************                 //~1923I~//~vc1sI~//~vc1tR~
//    public static void clearPreference(int[][] Pcodetbl)           //~1923I~//~vc1sR~//~vc1tR~
//    {                                                              //~1923I~//~vc1sI~//~vc1tR~
//        String key;                                          //~1923I~//~vc1sI~//~vc1tR~
//        int sz;                                                    //~1923I~//~vc1sI~//~vc1tR~
//    //**************                                               //~1923I~//~vc1sI~//~vc1tR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.clearPreference");//~vc1sI~//~vc1tR~
//        if (!existPrefData)                                        //~1923I~//~vc1sI~//~vc1tR~
//            return;                                                //~1923I~//~vc1sI~//~vc1tR~
//        sz=Pcodetbl.length;                                        //~1923R~//~vc1sI~//~vc1tR~
//        for (int ii=0;ii<sz;ii++)                                  //~1923I~//~vc1sI~//~vc1tR~
//        {                                                          //~1923I~//~vc1sI~//~vc1tR~
//            for (int jj=1;jj<AxeKbdDialogHW.METACTR;jj++)            //~1923I~//~vc1sR~//~vc1tR~
//            {                                                      //~1923I~//~vc1sI~//~vc1tR~
//                key=ii+"_"+jj;                                     //~vc1sI~//~vc1tR~
//                if (AxeProp.getPreference(PREFKEY+key,null)!=null) //~1923I~//~vc1sI~//~vc1tR~
//                    savePreference(PREFKEY+key,null);       //~1923I~//~vc1sR~//~vc1tR~
//            }                                                      //~1923I~//~vc1sI~//~vc1tR~
//        }                                                          //~1923I~//~vc1sI~//~vc1tR~
//        AxeProp.putPreference(PREFKEY,null);                 //~1923I~//~vc1sI~//~vc1tR~
//    }                                                              //~1923I~//~vc1sI~//~vc1tR~
//**********************************************************************//~1810I~
    public static void savePreference(String Pkey,String Pvalue) //~1919R~//~vc1sR~
    {                                                              //~1809I~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.savePreference key="+Pkey+",value="+Pvalue);//~vc1sI~
		AxeProp.putPreference(Pkey,Pvalue);                        //~vc1sR~
    }                                                              //~1809I~
//**********************************************************************//~vc1tI~
//*from AxeDlgKbdLayoutHW at openIM                                //~vc1tI~
//**********************************************************************//~vc1tI~
	protected String chkFocus()                                       //~vc1tR~//~vc1wR~
    {                                                              //~vc1tI~
        int cpos,col=0,key;                                          //~vc1tI~
        ListData data;                                             //~vc1tI~
        String rc=null;                                            //~vc1tI~
    //*********************:                                       //~vc1tI~
        cpos=getSelectedPos();                                     //~vc1tI~
    	if (cpos>=0 && cpos<arrayData.size())                                     //~vc1tI~
        {                                                          //~vc1tI~
        	data=arrayData.get(cpos);                              //~vc1tI~
	        col=data.col;  //forcused col                          //~vc1tI~
	        key=data.key1;                                         //~vc1tI~
//          if (col>0 && col<4 && !fldProtected(key,col))          //~vc1tR~//~vc1wR~
//          if (col>0 && col<maxMod && !fldProtected(key,col))     //~vc1wR~
            if (col>0 && col<maxCol && !fldProtected(key,col))     //~vc1wI~
            	rc=data.getColStr();                               //~vc1tI~
        }                                                          //~vc1tI~
        if (rc==null)                                              //~vc24I~
			Utils.showToast(R.string.Err_InvalidCursor);           //~vc24I~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.chkFocus cursorpos="+cpos+",col="+col+",rc="+rc);//~vc1tI~
        return rc;
    }                                                              //~vc1tI~
	protected boolean isItemFocused()                              //~vc2iI~
    {                                                              //~vc2iI~
        int cpos,col=0,key;                                        //~vc2iI~
        ListData data;                                             //~vc2iI~
        boolean rc=false;                                          //~vc2iI~
    //*********************:                                       //~vc2iI~
        cpos=getSelectedPos();                                     //~vc2iI~
    	if (cpos>=0 && cpos<arrayData.size())                      //~vc2iI~
        {                                                          //~vc2iI~
        	data=arrayData.get(cpos);                              //~vc2iI~
	        col=data.col;  //forcused col                          //~vc2iI~
	        key=data.key1;                                         //~vc2iI~
            if (col>0 && col<maxCol && !fldProtected(key,col))     //~vc2iI~
            	rc=true;                                           //~vc2iI~
        }                                                          //~vc2iI~
        if (!rc)                                                   //~vc2iI~
			Utils.showToast(R.string.Err_InvalidCursor);           //~vc2iI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.isItemFocused cursorpos="+cpos+",col="+col+",rc="+rc);//~vc2iI~
        return rc;                                                 //~vc2iI~
    }                                                              //~vc2iI~
//**********************************************************************//~vc1sI~
//*from AxeDlgKbdLayoutHW                                          //~vc1tI~
//**********************************************************************//~vc1tI~
	protected String setSpinnerItemSelected(int Ppos)                                //~1809I~//~vc1tR~//~vc1wR~
    {                                                              //~1809I~
        String keyname;                                            //~1810I~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setSpinnerItemSelected spinner pos="+Ppos);//~1919R~//~vc1sR~//~vc1tR~
    	int[] keytbl=AxeKbdKey.SspinnerCode;                       //~vc1sI~
        if (Ppos>=keytbl.length)                                   //~vc1sI~
        	return null;                                                //~vc1sI~//~vc1tR~
        if (Ppos==0)	//(show)                                   //~vc1sI~
        	return null;                                                //~vc1sI~//~vc1tR~
        spinner.setSelection(0);	//listener will not be called when selected same item,so back to 0 alaways//~vc1sI~
        int key=keytbl[Ppos];                                      //~vc1sI~
        if (key==KeyData.NOT_DEFINED)                              //~vc1sI~
        	keyname="";                                            //~vc1sI~
        else                                                       //~vc1sI~
        	keyname=AxeKbdKey.SspinnerData[Ppos];                  //~vc1sI~
        int cpos=getSelectedPos();                                 //~vc1sM~
        ListData data=null;                                        //~vc1sI~
        int col=0;                                                 //~vc1sI~
    	if (cpos!=INVALID_POS)                                     //~vc1sI~
        {                                                          //~vc1sI~
        	data=arrayData.get(cpos);                              //~vc1sI~
	        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setSpinnerItemSelected data="+data.toString());//~vc1sI~//~vc1wR~
	        col=data.col;  //forcused col                          //~vc1sR~
        }                                                          //~vc1sI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setSpinnerItemSelected cursorpos="+cpos+",col="+col);//~vc1sR~//~vc1tR~//~vc1wR~
//  	if (col<1 || col>3)                                 //~1809I~//~1816R~//~vc1sR~//~vc1wR~
//  	if (col<1 || col>maxMod)                                   //~vc1wR~
    	if (col<1 || col>=maxCol)                                  //~vc1wI~
        {                                                          //~vc1sI~
//  	    if (swOnceFocused)                                     //~vc1sR~
			    Utils.showToast(R.string.Err_SpinnerNoItemSelected);//~vc1sR~
        	return null;                                                //~1810I~//~vc1tR~
        }                                                          //~vc1sI~
        spinnerPos=Ppos;                                       //~1809I~//~1810R~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setSpinnerItemSelected data.view1="+data.textView1.getText()+"="+data.textView1.toString());//~vc1sI~//~vc1wR~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setSpinnerItemSelected data.view2="+data.textView2.getText()+"="+data.textView2.toString());//~vc1sI~//~vc1wR~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setSpinnerItemSelected data.view3="+data.textView3.getText()+"="+data.textView3.toString());//~vc1sI~//~vc1wR~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setSpinnerItemSelected data.view4="+data.textView4.getText()+"="+data.textView4.toString());//~vc1sI~//~vc1wR~
//        TextView tv;                                             //~vc1sR~
//        if (col==1)                                              //~vc1sR~
//        {                                                        //~vc1sR~
//            data.str2=keyname;                                   //~vc1sR~
//            tv=data.textView2;                                   //~vc1sR~
//        }                                                        //~vc1sR~
//        else                                                     //~vc1sR~
//        if (col==2)                                              //~vc1sR~
//        {                                                        //~vc1sR~
//            data.str3=keyname;                                   //~vc1sR~
//            tv=data.textView3;                                   //~vc1sR~
//        }                                                        //~vc1sR~
//        else                                                     //~vc1sR~
//        {                                                        //~vc1sR~
//            data.str3=keyname;                                   //~vc1sR~
//            tv=data.textView4;                                   //~vc1sR~
//        }                                                        //~vc1sR~
//      tv.setText(keyname);                                       //~vc1sR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setSpinnerItemSelected settext textview="+tv.getText()+"="+tv.toString());//~vc1sR~//~vc1wR~
		data.updateText(keyname);                                  //~vc1sI~
//      int keycode=listViewData[Ppos][LVDI_KEYCODE];              //~vc1sR~
//      currentMap[keycode][col]=0;                                //~vc1sR~
        int gdkkey=0;                                              //~vc1sI~
        if (key!=KeyData.NOT_DEFINED)                              //~vc1sI~
        {                                                          //~vc1tI~
//          gdkkey=key|HWKEYID_GDK;                                //~vc1sR~//~vc1zR~
            gdkkey=Ppos|HWKEYID_GDK;                               //~vc1zI~
        }                                                          //~vc1tI~
//      listViewData[cpos][LVDI_KEYCHAR+col]=gdkkey;               //~vc1sR~//~vc1wR~
        listViewData[cpos][colNormal+col]=gdkkey;                  //~vc1wI~
    	resetAdapter(); //notifyDataChanged                        //~vc1sI~
        data.resetColErr(col);                                     //~vc1tI~
        data.isUpdated=true;                                       //~vc1sI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setSpinnerItemSelected spinnerpos="+spinnerPos+",keyname="+keyname+",key="+Integer.toHexString(gdkkey));//~vc1sI~//~vc1wR~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setSpinnerItemSelected data="+data.toString());//~vc1sI~//~vc1wR~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setSpinnerItemSelected cpos="+cpos+",listViewData="+Arrays.toString(listViewData[cpos]));//~vc1sI~//~vc1wR~
        return keyname;                                            //~vc1tI~
    }                                                              //~1809I~
//**********************************************************************//~vc1sI~
	protected void onClickDelete()                                    //~vc1sI~//~vc1wR~
    {                                                              //~vc1sI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onClickDelete");//~vc1sI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onClickDelete forcusViewID="+Integer.toHexString(UView.getViewFocusedID((Dialog)axeDialog)));//~vc1tR~//~vc1zR~
        int cpos=getSelectedPos();                                 //~vc1sI~
        ListData data=null;                                        //~vc1sI~
        int col=0;                                                 //~vc1sI~
    	if (cpos!=INVALID_POS)                                     //~vc1sI~
        {                                                          //~vc1sI~
        	data=arrayData.get(cpos);                              //~vc1sI~
	        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onClickDelete data="+data.toString());//~vc1sI~
	        col=data.col;     //forcused col                       //~vc1sR~
        }                                                          //~vc1sI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onClickDelete cursorpos="+cpos+",col="+col);//~vc1sI~
//  	if (col<1 || col>3)                                        //~vc1sI~//~vc1wR~
//  	if (col<1 || col>maxMod)                                   //~vc1wR~
    	if (col<1 || col>=maxCol)                                  //~vc1wI~
        {                                                          //~vc1sI~
			Utils.showToast(R.string.Err_DeleteNoItemSelected);    //~vc1sI~
        	return;                                                //~vc1sI~
        }                                                          //~vc1sI~
//      int oldkey=listViewData[cpos][LVDI_KEYCHAR+col];           //~vc1sI~//~vc1wR~
        int oldkey=listViewData[cpos][colNormal+col];              //~vc1wI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onClickDelete data="+data.toString());//~vc1sI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onClickDelete oldkey="+Integer.toHexString(oldkey));//~vc1sI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onClickDelete data v1="+data.textView1.getText()+"="+data.textView1.toString());//~vc1sR~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onClickDelete data v2="+data.textView2.getText()+"="+data.textView2.toString());//~vc1sR~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onClickDelete data v3="+data.textView3.getText()+"="+data.textView3.toString());//~vc1sR~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onClickDelete data v4="+data.textView4.getText()+"="+data.textView4.toString());//~vc1sR~
        ListViewTag tag=(ListViewTag)data.textView1.getTag();      //~vc1sR~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onClickDelete tag.view1="+tag.view1.getText()+"="+tag.view1.toString());//~vc1sI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onClickDelete tag.view2="+tag.view2.getText()+"="+tag.view2.toString());//~vc1sI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onClickDelete tag.view3="+tag.view3.getText()+"="+tag.view3.toString());//~vc1sI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onClickDelete tag.view4="+tag.view4.getText()+"="+tag.view4.toString());//~vc1sI~
        if (oldkey==0)                                             //~vc1sI~
        	return;                                                //~vc1sI~
//        TextView tv;                                             //~vc1sR~
//        String keyname="";                                       //~vc1sR~
//        if (col==1)                                              //~vc1sR~
//        {                                                        //~vc1sR~
//            data.str2=keyname;                                   //~vc1sR~
//            tv=data.textView2;//TODO test                        //~vc1sR~
//        }                                                        //~vc1sR~
//        else                                                     //~vc1sR~
//        if (col==2)                                              //~vc1sR~
//        {                                                        //~vc1sR~
//            data.str3=keyname;                                   //~vc1sR~
//            tv=data.textView3;                                   //~vc1sR~
//        }                                                        //~vc1sR~
//        else                                                     //~vc1sR~
//        {                                                        //~vc1sR~
//            data.str3=keyname;                                   //~vc1sR~
//            tv=data.textView4;                                   //~vc1sR~
//        }                                                        //~vc1sR~
//        tv.setText(keyname);                                     //~vc1sR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onClickDelete settext textview="+tv.getText()+"="+tv.toString());//~vc1sR~
        int kc=listViewData[cpos][0];                              //~vc2iI~
        int uc=defaultMap[kc][0];                                  //~vc2iI~
      if (col==1 && uc!=0) //Shift column,default!=0               //~vc2iI~
      {                                                            //~vc2iI~
      	String defChar=Character.valueOf((char)uc).toString();           //~vc2iR~
      	data.updateText(defChar);                                  //~vc2iI~
        listViewData[cpos][colNormal+col]=uc;                      //~vc2iI~
        axeDialog.onClickListItem(cpos,col,defChar);               //~vc2iI~
      }                                                            //~vc2iI~
      else                                                         //~vc2iI~
      {                                                            //~vc2iI~
		data.updateText("");                                       //~vc1sI~
//      AxeG.axeIME.hideKbd(Pview);                                //~vc1sI~
//      listViewData[cpos][LVDI_KEYCHAR+col]=0;                    //~vc1sI~//~vc1wR~
        listViewData[cpos][colNormal+col]=0;                       //~vc1wI~
//      int keycode=listViewData[cpos][LVDI_KEYCODE];              //~vc1sR~
//      currentMap[keycode][col]=0;                                //~vc1sR~
        axeDialog.clearET();                                       //~vc2iI~
      }                                                            //~vc2iI~
    	resetAdapter(); //notifyDataChanged                        //~vc1sI~
        data.isUpdated=true;                                       //~vc1sI~
//      axeDialog.clearET();                                       //~vc1zI~//~vc2iR~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onClickDelete cpos="+cpos+",listViewData="+Arrays.toString(listViewData[cpos]));//~vc1sI~
    }//onClickDelete                                               //~vc1sR~
//*************                                                    //~1813I~
//    public void setSpinnerPrompt(int Ppos)                         //~1813I~//~vc1sR~
//    {                                                              //~1813I~//~vc1sR~
//        String prompt;                                             //~1813I~//~vc1sR~
//    //****************                                             //~1813I~//~vc1sR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW setSpinnerPrompt pos="+Ppos);//~1919R~//~vc1sR~
//        if (Ppos<0)                                                //~1814R~//~vc1sR~
//        {                                                          //~1813I~//~vc1sR~
//            prompt=Utils.getResourceString(R.string.Prompt_KbdLayout_Not_Selected);//~1919R~//~vc1sR~
//        }                                                          //~1813I~//~vc1sR~
//        else                                                       //~1813I~//~vc1sR~
//        {                                                          //~1813I~//~vc1sR~
//            String linelabel=getSpinnerTitle(Ppos);               //~1920R~//~vc1sR~
//            prompt=Utils.getResourceString(R.string.Prompt_KbdLayout)+linelabel;//~1919R~//~vc1sR~
//        }                                                          //~1813I~//~vc1sR~
//        spinner.setPrompt(prompt);                                 //~1813I~//~vc1sR~
//    }                                                              //~1813I~//~vc1sR~
//    public void setSpinnerPrompt(int Ppos,int Pcol)                //~vc1sI~//~vc1tR~
//    {                                                              //~vc1sI~//~vc1tR~
//        String prompt;                                             //~vc1sI~//~vc1tR~
//    //****************                                             //~vc1sI~//~vc1tR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW setSpinnerPrompt pos="+Ppos+",col="+Pcol);//~vc1sI~//~vc1tR~
//        if (Ppos<0)                                                //~vc1sI~//~vc1tR~
//        {                                                          //~vc1sI~//~vc1tR~
//            prompt=Utils.getResourceString(R.string.Prompt_KbdLayout_Not_Selected);//~vc1sI~//~vc1tR~
//        }                                                          //~vc1sI~//~vc1tR~
//        else                                                       //~vc1sI~//~vc1tR~
//        {                                                          //~vc1sI~//~vc1tR~
//            String linelabel=getSpinnerTitle(Ppos,Pcol);           //~vc1sI~//~vc1tR~
//            prompt=Utils.getResourceString(R.string.Prompt_KbdLayout)+linelabel;//~vc1sI~//~vc1tR~
//        }                                                          //~vc1sI~//~vc1tR~
//        spinner.setPrompt(prompt);                                 //~vc1sI~//~vc1tR~
//    }                                                              //~vc1sI~//~vc1tR~
//*************                                                    //~1919R~
//    @Override                                                      //~1919R~
//    public void onFocusChanged(int Ppos,boolean Phasfocus,ListData Pdata)//~1919R~//~vc1sR~
//    {                                                              //~1919R~//~vc1sR~
//        if (Phasfocus)                                             //~1919R~//~vc1sR~
//        {                                                          //~1919R~//~vc1sR~
//            selectedView2=Pdata.textView2;                                   //~1919R~//~vc1sR~
//            csrPos=Ppos;                                           //~1919R~//~vc1sR~
//            setSpinnerPrompt(csrPos);                              //~1919R~//~vc1sR~
//        }                                                          //~1919R~//~vc1sR~
//    public void onFocusChanged(int Ppos,boolean Phasfocus,ListData Pdata)//~vc1sR~
//    {                                                            //~vc1sR~
//        if (Phasfocus)                                           //~vc1sR~
//        {                                                        //~vc1sR~
//            cursorPos=Ppos;                                      //~vc1sR~
//            int col=Pdata.col;                                   //~vc1sR~
//            selectedView2=Pdata.textView1;                       //~vc1sR~
//            if (col==1)                                          //~vc1sR~
//                selectedView2=Pdata.textView2;                   //~vc1sR~
//            else                                                 //~vc1sR~
//            if (col==2)                                          //~vc1sR~
//                selectedView2=Pdata.textView3;                   //~vc1sR~
//            else                                                 //~vc1sR~
//            if (col==3)                                          //~vc1sR~
//                selectedView2=Pdata.textView4;                   //~vc1sR~
//            setSpinnerPrompt(cursorPos,col);                     //~vc1sR~
//        }                                                        //~vc1sR~
//    }                                                              //~1919R~//~vc1sR~
////*************                                                    //~1923I~//~vc1tR~
//    public static void saveDefault(int[][] PflagedKeyCodes)        //~1923R~//~vc1tR~
//    {                                                              //~1923I~//~vc1tR~
//        int keyctr=PflagedKeyCodes.length;                         //~1923R~//~vc1tR~
//        int[][] outtb=new int[keyctr][AxeKbdView.MAXSWIPE+1];      //~1923I~//~vc1tR~
//        Utils.copyArray(PflagedKeyCodes,outtb);                   //~1923I~//~vc1tR~
//        flagedKeyCodesDefault=outtb;                               //~1923R~//~vc1tR~
//    }                                                              //~1923I~//~vc1tR~
//*************                                                    //~1923I~
//    public void resetToDefault()                                   //~1923I~//~vc1sR~
//    {                                                              //~1923I~//~vc1sR~
//         flagedKeyCodes=initKeynameTbl(flagedKeyCodesDefault); //inedx,label tbl//~1923R~//~vc1sR~
//        int sz=keyIndexMap.length;                                 //~1923I~//~vc1sR~
//        int[] adaptordata=new int[sz];                             //~1923R~//~vc1sR~
//        for (int ii=0;ii<sz;ii++)                                  //~1923I~//~vc1sR~
//        {                                                          //~1923I~//~vc1sR~
//            int mapidx=keyIndexMap[ii];                            //~1923I~//~vc1sR~
//            int row=mapidx>>8;                                     //~1923I~//~vc1sR~
//            int col=mapidx & 0xff;                                 //~1923I~//~vc1sR~
//            adaptordata[ii]=flagedKeyCodes[row][col];                    //~1923R~//~vc1sR~
//        }                                                          //~1923I~//~vc1sR~
//        updateData(adaptordata);                                   //~1923R~//~vc1sR~
//        reset=true; //remove preference                            //~1923I~//~vc1sR~
//    }                                                              //~1923I~//~vc1sR~
    //***************************************************
    //*from AxeDlgKbdLayoutHW
    //***************************************************
    protected void resetToDefault()                                   //~vc1sI~//~vc1wR~
    {                                                              //~vc1sI~
		if (Dump.Y) Dump.println("AxeListKbdLayoutHW.resetToDefault");//~vc1tI~
        getListViewData(defaultMap);	//make ListViewData        //~vc1tR~
        isUpdateAll=true;                                          //~vc1tI~
    	clearList();                                               //~vc1tI~
		setListViewData(this);	//fill arraylist for KbdLayout     //~vc1tI~
    	resetAdapter(); //notifyDataChanged                        //~vc1tI~
    }                                                              //~vc1sI~
//*************                                                    //~vc1sI~
    public String chkKeyName(int Pkey)                             //~vc1sI~
    {                                                              //~vc1sI~
        int idx;                                                   //~vc1sI~
    	for (idx=0;idx<specialKey.length;idx++)                    //~vc1sI~
        	if (Pkey==specialKey[idx])                             //~vc1sI~
        		return specialKeyName[idx];                        //~vc1sI~
        return null;                                               //~vc1sR~
    }                                                              //~vc1sI~
	//**********************************************************************//~vc1sR~
    @Override                                                      //~vc1sI~
    protected View getViewEach(AxeList.ListArrayAdapter Padapter, int Ppos, View Pview, ViewGroup Pparent)//~vc1sR~//~vc1wR~
    {                                                              //~vc1sR~
        View /*heldview,*/convertview;                             //~vc1sR~
        ListViewTag tag;                                           //~vc1sR~
        TextView v1,v2,v3,v4;                                      //~vc1sR~
    //*******************                                          //~vc1sR~
        if (Dump.Y) Dump.println("AxeListKbdLayoutHW.getviewEach Ppos="+Ppos+",view="+Pview);//~vc1sR~//~vc1tR~//~vc1wR~
        convertview=Pview;                                         //~vc1sR~
//      setInitialFocus();  //TODO  test                           //~vc1sI~//~vc1tR~
            if (convertview==null)      //shown 1st time           //~vc1sR~
            {                                                      //~vc1sR~
                convertview=AxeG.inflater.inflate(ROWID,Pparent,false/*attachToRoot*/);//~vc1sR~
			    if (Dump.Y) Dump.println("AxeListKbdLayoutHW.getview converview inflated="+convertview.toString());//~vc1sI~
                v1=(TextView)(convertview.findViewById(COLID_NORMAL));//~vc1sR~//~vc1wR~
                v2=(TextView)(convertview.findViewById(COLID_SHIFT));//~vc1sR~//~vc1wR~
                v3=(TextView)(convertview.findViewById(COLID_ALTGR));//~vc1sR~//~vc1wR~
                v4=(TextView)(convertview.findViewById(COLID_SHIFTALTGR));//~vc1sR~//~vc1wR~
                tag=new ListViewTag(v1,v2,v3,v4,0,0);              //~vc1sR~
                convertview.setTag(tag);                           //~vc1sR~
            }                                                      //~vc1sR~
            else                                                   //~vc1sR~
            {                                                      //~vc1sR~
                tag=(ListViewTag)convertview.getTag();             //~vc1sR~
                v1=tag.view1;                                      //~vc1sR~
                v2=tag.view2;                                      //~vc1sR~
                v3=tag.view3;                                      //~vc1sR~
                v4=tag.view4;                                      //~vc1sR~
            }                                                      //~vc1sR~
//            if (Ppos==0)                                         //~vc1tR~
//                isSoftKbdOpen();     //TODO test                 //~vc1tR~
            setLineView(convertview,Ppos,v1,v2,v3,v4);             //~vc1sR~
        return convertview;                                        //~vc1sR~
    }                                                              //~vc1sR~
    //************************************************************************************//~vc1sI~
    private void setLineView(View Pconvertview,int Ppos,TextView Pview1,TextView Pview2,TextView Pview3,TextView Pview4)//~vc1sR~
    {                                                              //~vc1sI~
        int bg,fg;                                                 //~vc1sI~
        ListData data;                                             //~vc1sI~
        int key;                                                   //~vc1sI~
        TextView v1,v2,v3,v4;                                      //~vc1sI~
        ListViewTag tag1,tag2,tag3,tag4;                           //~vc1sI~
    //*******************                                          //~vc1sI~
        v1=Pview1;                                                 //~vc1sI~
        v2=Pview2;                                                 //~vc1sI~
        v3=Pview3;                                                 //~vc1sI~
        v4=Pview4;                                                 //~vc1sI~
        tag1=new ListViewTag(v1,v2,v3,v4,Ppos,0);                  //~vc1sR~
        tag2=new ListViewTag(v1,v2,v3,v4,Ppos,1);                  //~vc1sR~
        tag3=new ListViewTag(v1,v2,v3,v4,Ppos,2);                  //~vc1sR~
        tag4=new ListViewTag(v1,v2,v3,v4,Ppos,3);                  //~vc1sR~
        v1.setTag(tag1);    //for FocusChangeListener              //~vc1sI~
        v2.setTag(tag2);    //for FocusChangeListener              //~vc1sI~
        v3.setTag(tag3);    //for FocusChangeListener              //~vc1sI~
        v4.setTag(tag4);    //for FocusChangeListener              //~vc1sI~
        data=arrayData.get(Ppos);                                  //~vc1sI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setLineView pos="+Ppos+",data="+data.toString());//~vc1tR~
        key=data.key1;                                             //~vc1sI~
        int col=data.col;                                          //~vc1sI~
        int colerr=data.colerr;                                    //~vc1sI~
        int posCursor=data.posCursor;                              //~vc1tI~
        String s1=data.str1;                                       //~vc1sI~
        String s2=data.str2;                                       //~vc1sI~
        String s3=data.str3;                                       //~vc1sI~
        String s4=data.str4;                                       //~vc1sI~
        v1.setText(s1);                                            //~vc1sI~
        v2.setText(s2);                                            //~vc1sI~
        v3.setText(s3);                                            //~vc1sI~
        v4.setText(s4);                                            //~vc1sI~
//      if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setLineView settext csrPos="+cursorpos+",pos="+Ppos+",key="+Integer.toHexString(key)+",col="+col+",colerr="+colerr+",str="+s1+","+s2+","+s3+","+s4);//~vc1sR~//~vc1tR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setLineView v1="+v1.getText()+"="+v1.toString());//~vc1sR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setLineView v2="+v2.getText()+"="+v2.toString());//~vc1sR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setLineView v3="+v3.getText()+"="+v3.toString());//~vc1sR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setLineView v4="+v4.getText()+"="+v4.toString());//~vc1sR~
        data.textView1=v1;                                         //~vc1sI~
        data.textView2=v2;  //for getText                          //~vc1sI~
        data.textView3=v3;  //for getText                          //~vc1sI~
        data.textView4=v4;  //for getText                          //~vc1sI~
//      if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setLineView data="+data.toString());//~vc1sI~//~vc1tR~
//        if (fldProtected(key,1/*col*/))                          //~vc1sR~
//        {                                                        //~vc1sR~
//            v2.setFocusable(false);                              //~vc1sR~
//            v2.setFocusableInTouchMode(false); //fail if Array is empty//~vc1sR~
//            if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setLineView disable focusable");//~vc1sR~
//        }                                                        //~vc1sR~
//        addFocusChangeListener(v2);                                //~vc1sR~//~vc1tR~
//    if (true)                                                    //~vc1sI~
//    {                                                            //~vc1sI~
//          v3.setFocusable(false);                                //~vc1sI~
//          v3.setFocusableInTouchMode(false); //fail if Array is empty//~vc1sI~
//    }//TODO test                                                 //~vc1sI~
//    else                                                         //~vc1sI~
//        addFocusChangeListener(v3);                                //~vc1sR~//~vc1tR~
//      addFocusChangeListener(v4); TODO test                      //~vc1sR~
//        addClickListenerV1(v1);                                  //~vc1sR~
//        addClickListenerV1(v2); //TODO test                      //~vc1sR~
//        addClickListenerV1(v3); //TODO test                      //~vc1sR~
        addClickListenerV1(v2);                                    //~vc1tI~
        addClickListenerV1(v3);                                    //~vc1tI~
        addClickListenerV1(v4);                                    //~vc1tI~
//        if (Ppos==cursorpos && posCursor>=0)  //showing cursor did not work because not having focus//~vc1tR~
//        {                                                        //~vc1tR~
//            if (col==1)                                          //~vc1tR~
//                ((EditText)v2).setSelection(posCursor);          //~vc1tR~
//            else                                                 //~vc1tR~
//            if (col==2)                                          //~vc1tR~
//                ((EditText)v3).setSelection(posCursor);          //~vc1tR~
//            else                                                 //~vc1tR~
//            if (col==3)                                          //~vc1tR~
//                ((EditText)v4).setSelection(posCursor);          //~vc1tR~
//        }                                                        //~vc1tR~
        if (fldProtected(key,1/*col*/))                               //~vc1sI~
        	bg=COLOR_BG_DISABLE;                                   //~vc1sR~
        else                                                       //~vc1sI~
        if (Ppos==cursorpos)                                       //~vc1sI~
        	if (col==1)                                            //~vc1sI~
        		bg=COLOR_BGFOCUS;                                  //~vc1sR~
            else                                                   //~vc1sI~
        		bg=COLOR_BG;
        else
            bg=COLOR_BG;//~vc1sR~
        v2.setBackgroundColor(bg);                                 //~vc1sI~
//      if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setLineView settext pos="+Ppos+",key="+Integer.toHexString(key)+",col="+col+",colerr="+colerr+",str="+s1+","+s2+","+s3+","+s4);//~vc1sI~//~vc1tR~
        if (Ppos==cursorpos)                                       //~vc1sI~
        {                                                          //~vc1sI~
        	if (col==2)                                            //~vc1sI~
        		v3.setBackgroundColor(COLOR_BGFOCUS);              //~vc1sI~
            else                                                   //~vc1sI~
        		v3.setBackgroundColor(COLOR_BG_ENABLE3);           //~vc1sI~
        	if (col==3)                                            //~vc1sI~
        		v4.setBackgroundColor(COLOR_BGFOCUS);              //~vc1sI~
            else                                                   //~vc1sI~
        		v4.setBackgroundColor(COLOR_BG_ENABLE4);           //~vc1sI~
        }                                                          //~vc1sI~
        else                                                       //~vc1sI~
        {                                                          //~vc1sI~
        	v3.setBackgroundColor(COLOR_BG_ENABLE3);               //~vc1sI~
        	v4.setBackgroundColor(COLOR_BG_ENABLE4);               //~vc1sI~
        }                                                          //~vc1sI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setLineView colerr="+colerr+",key="+Integer.toHexString(key)+",col="+col+",colerr="+colerr+",str="+s1+","+s2+","+s3+","+s4);//~vc1tI~
        if (colerr!=0)                                             //~vc1tR~
        {                                                          //~vc1sI~
//      	if ((colerr & 0x02)!=0)                                //~vc1tR~
        	if (data.isColErr(1))                                  //~vc1tI~
	            v2.setTextColor(COLOR_FGERR);                      //~vc1sI~
            else                                                   //~vc1sI~
	            v2.setTextColor(COLOR_FG);                         //~vc1sI~
//      	if ((colerr & 0x04)!=0)                                //~vc1tR~
        	if (data.isColErr(2))                                  //~vc1tI~
	            v3.setTextColor(COLOR_FGERR);                      //~vc1sI~
            else                                                   //~vc1sI~
	            v3.setTextColor(COLOR_FG);                         //~vc1sI~
//      	if ((colerr & 0x08)!=0)                                //~vc1tR~
        	if (data.isColErr(3))                                  //~vc1tI~
	            v4.setTextColor(COLOR_FGERR);                      //~vc1sI~
            else                                                   //~vc1sI~
	            v4.setTextColor(COLOR_FG);                         //~vc1sI~
        }                                                          //~vc1sI~
        else                                                       //~vc1sI~
        {                                                          //~vc1sI~
	        v2.setTextColor(COLOR_FG);                             //~vc1sI~
	        v3.setTextColor(COLOR_FG);                             //~vc1sI~
	        v4.setTextColor(COLOR_FG);                             //~vc1sI~
        }                                                          //~vc1sI~
    }                                                              //~vc1sI~
//    //**************************************************************//~vc1sI~//~vc1tR~
//    @Override                                                      //~vc1sI~//~vc1tR~
//    public boolean onFocusChangedEach(View Pview,boolean PhasFocus)//~vc1sR~//~vc1tR~
//    {                                                              //~vc1sI~//~vc1tR~
//        int bg;                                                    //~vc1sI~//~vc1tR~
//        int pos;                                                   //~vc1sI~//~vc1tR~
//        TextView editText,v1,v2,v3,v4;                             //~vc1sI~//~vc1tR~
//        ListData data;                                             //~vc1sI~//~vc1tR~
//        boolean rc=true;                                           //~vc1sI~//~vc1tR~
//    //**************                                               //~vc1sI~//~vc1tR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onFocusChangedEach hasFocus="+PhasFocus+",view="+Pview.toString());//~vc1sR~//~vc1tR~
//        isSoftKbdOpen();    //TODO test                            //~vc1sI~//~vc1tR~
//            editText=(TextView) Pview;                             //~vc1sI~//~vc1tR~
//            ListViewTag tag=(ListViewTag)Pview.getTag();           //~vc1sI~//~vc1tR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onFocusChangedEach tag="+tag.toString());//~vc1sI~//~vc1tR~
//            pos=tag.pos;                                           //~vc1sI~//~vc1tR~
////            int col=0;                                           //~vc1sR~//~vc1tR~
////            v1=tag.view1;                                        //~vc1sR~//~vc1tR~
////            if (Pview==tag.view2)                                //~vc1sR~//~vc1tR~
////                col=1;                                           //~vc1sR~//~vc1tR~
////            else                                                 //~vc1sR~//~vc1tR~
////            if (Pview==tag.view3)                                //~vc1sR~//~vc1tR~
////                col=2;                                           //~vc1sR~//~vc1tR~
////            if (Pview==tag.view4)                                //~vc1sR~//~vc1tR~
////                col=3;                                           //~vc1sR~//~vc1tR~
//        int col=tag.cols;                                          //~vc1sI~//~vc1tR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onFocusChangedEach PView    ="+((TextView)Pview).getText()+"="+Pview.toString());//~vc1sR~//~vc1tR~
////        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onFocusChangedEach tag.view1="+tag.view1.getText()+"="+tag.view1.toString());//~vc1sR~//~vc1tR~
////        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onFocusChangedEach tag.view2="+tag.view2.getText()+"="+tag.view2.toString());//~vc1sR~//~vc1tR~
////        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onFocusChangedEach tag.view3="+tag.view3.getText()+"="+tag.view3.toString());//~vc1sR~//~vc1tR~
////        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onFocusChangedEach tag.view4="+tag.view4.getText()+"="+tag.view4.toString());//~vc1sR~//~vc1tR~
//            data=arrayData.get(pos);                               //~vc1sI~//~vc1tR~
//            editText=(TextView)Pview;                              //~vc1sI~//~vc1tR~
//            if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onFocusChangedEach col="+col+",view="+((TextView)Pview).getText()+"="+Pview.toString());//~vc1sR~//~vc1tR~
//            if (fldProtected(data.key1,col))                       //~vc1sI~//~vc1tR~
//            {                                                      //~vc1sI~//~vc1tR~
////              ((TextView)Pview).setText(data.str2);              //~vc1sR~//~vc1tR~
////              Pview.setBackgroundColor(COLOR_BG_DISABLE);        //~vc1sR~//~vc1tR~
//                if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onFocusChangedEach protected");//~vc1sI~//~vc1tR~
//                if (PhasFocus)                                     //~vc1sI~//~vc1tR~
//                    Utils.showToast(R.string.Err_FieldProtected);  //~vc1sI~//~vc1tR~
//                AxeG.axeIME.hideKbd(Pview);                        //~vc1sI~//~vc1tR~
//                return true;                                       //~vc1sI~//~vc1tR~
//            }                                                      //~vc1sI~//~vc1tR~
//            Axe.getViewFocused((Dialog)axeDialog); //TODO test     //~vc1sI~//~vc1tR~
//            if (PhasFocus)                                         //~vc1sI~//~vc1tR~
//            {                                                      //~vc1sI~//~vc1tR~
//                data.col=col;           //focused col              //~vc1sI~//~vc1tR~
//                swOnceFocused=true;                                //~vc1sI~//~vc1tR~
//                cursorpos=pos;                                     //~vc1sI~//~vc1tR~
//                bg=COLOR_BGFOCUS;                                  //~vc1sI~//~vc1tR~
////              onFocusChanged(pos,true,data);                     //~vc1sR~//~vc1tR~
//                addTextWatcher(editText); //many duplicated listner call//~vc1sI~//~vc1tR~
//                focusView1=(TextView)Pview;                                  //~vc1sR~//~vc1tR~
////              focusView2=editText;                               //~vc1sR~//~vc1tR~
////              focusPos=pos;                                      //~vc1sR~//~vc1tR~
//            }                                                      //~vc1sI~//~vc1tR~
//            else                                                   //~vc1sI~//~vc1tR~
//            {                                                      //~vc1sI~//~vc1tR~
//                chkInput(pos,(TextView)Pview);                               //~vc1sR~//~vc1tR~
////              onFocusChanged(pos,false,data);                    //~vc1sR~//~vc1tR~
//                removeTextWatcher(editText); //many duplicated listner call//~vc1sI~//~vc1tR~
////              cursorpos=INVALID_POS;                             //~vc1sR~//~vc1tR~
////              focusPos=INVALID_POS;                              //~vc1sR~//~vc1tR~
////                if (col==2)                                      //~vc1sR~//~vc1tR~
////                {                                                //~vc1sR~//~vc1tR~
////                    if (fldProtected(data.key1,1/*col*/))        //~vc1sR~//~vc1tR~
////                        bg=COLOR_BG_DISABLE;                     //~vc1sR~//~vc1tR~
////                    else                                         //~vc1sR~//~vc1tR~
////                        bg=COLOR_BG;                             //~vc1sR~//~vc1tR~
////                }                                                //~vc1sR~//~vc1tR~
////                else                                             //~vc1sR~//~vc1tR~
////                if (col==2)                                      //~vc1sR~//~vc1tR~
////                    bg=COLOR_BG_ENABLE3;                         //~vc1sR~//~vc1tR~
////                else                                             //~vc1sR~//~vc1tR~
////                    bg=COLOR_BG_ENABLE4;                         //~vc1sR~//~vc1tR~
//            }                                                      //~vc1sI~//~vc1tR~
//            if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.OnFocusChanged FocusListener cursorpos="+cursorpos+",text="+((TextView)Pview).getText());//~vc1sR~//~vc1tR~
////            editText.setBackgroundColor(bg);                     //~vc1sR~//~vc1tR~
////          invalidate();  //not to editText to redraw bg color changed//~vc1sR~//~vc1tR~
//        resetAdapter(); //notifyDataChanged                        //~vc1sI~//~vc1tR~
//        return rc;                                                 //~vc1sR~//~vc1tR~
//    }                                                              //~vc1sI~//~vc1tR~
    //**************************************************************//~vc1sI~
    @Override //AxeList                                            //~vc1sI~
    public boolean onClickEach(View Pview)                         //~vc1sI~
    {                                                              //~vc1sI~
        int pos,col;                                               //~vc1sI~
        ListData data=null;                                        //~vc1sI~
        TextView v1,v2;                                            //~vc1sI~
    //*********                                                    //~vc1sI~
        	if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onClickEach view="+Pview.toString());//~vc1sI~
        	if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onClickEach forcusViewID="+Integer.toHexString(UView.getViewFocusedID((Dialog)axeDialog))); //TODO test//~vc1tI~
            ListViewTag tag=(ListViewTag)Pview.getTag();           //~vc1sI~
        	if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onClickEach tag="+tag.toString());//~vc1sI~
            pos=tag.pos;                                           //~vc1sI~
            col=tag.cols;                                          //~vc1sI~
            data=arrayData.get(pos);                               //~vc1sI~
            if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onClickEach col="+col+",data="+data.toString());//~vc1sI~
            if (fldProtected(data.key1,col))                       //~vc1sI~
            {                                                      //~vc1sI~
		        Utils.showToast(R.string.Err_FieldProtected);      //~vc1sI~
                return true;                                            //~vc1sI~
            }                                                      //~vc1sI~
            data.col=col;                                          //~vc1tI~
            cursorpos=pos;                                         //~vc1sI~
//          if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onClickEach setFocusable true");//~vc1sR~//~vc1tR~
//  		Pview.setFocusable(true);                              //~vc1sR~//~vc1tR~
//  		Pview.setFocusableInTouchMode(true); //fail if Array is empty//~vc1sR~//~vc1tR~
//          PView.requestFocus();                                  //~vc1sI~
            String text=((TextView)Pview).getText().toString();    //~vc1tR~
            axeDialog.onClickListItem(cursorpos,col,text);         //~vc1tR~
    		resetAdapter(); //notifyDataChanged                    //~vc1tI~
            return true;//~vc1sI~
    }                                                              //~vc1sI~
    //**************************************************************//~vc1sI~
	protected boolean chkInput(int Ppos,TextView Pview)               //~vc1sI~//~vc1wR~
    {                                                              //~vc1sI~
    	boolean rc=true;                                           //~vc1sI~
        int intvalue;                                              //~vc1sI~
        ListData data;                                             //~vc1sI~
        String str;                                                //~vc1sI~
    //*******************                                          //~vc1sI~
		data=arrayData.get(Ppos);                                  //~vc1sI~
//      data.status=DATA_NORMAL;                                   //~vc1sI~//~vc1tR~
        int key=data.key1;                                         //~vc1sI~
        int col=data.col;                                          //~vc1sI~
    	String value=Pview.getText().toString();                   //~vc1sI~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.chkInput gettext str="+value+",pos="+Ppos+",col="+col+",key="+Integer.toHexString(key));//~vc1sR~//~vc1tI~
        intvalue=chkInputValue(Ppos,value,true/*toast*/);          //~vc1sI~
//      data.colerr &= ~(1<<col);                                  //~vc1tR~
        data.resetColErr(col);                                     //~vc1tI~
        switch(intvalue)                                           //~vc1sI~
        {                                                          //~vc1sI~
        case AxeKey.KEYVALUE_ERR:                                  //~vc1sI~
        	if (value.length()==1)                                   //~vc1tI~
            {                                                      //~vc1tI~
	        	data.updateText(value);                            //~vc1tI~
		        data.intValue=value.charAt(0);	//unicode          //~vc1tI~
                break;                                             //~vc1tI~
            }                                                      //~vc1tI~
        	rc=false;                                              //~vc1sI~
	        data.updateText(value);                                  //~vc1tI~
        	Pview.setTextColor(COLOR_FGERR);                       //~vc1sI~
//          data.status=DATA_ERR;                                  //~vc1sI~//~vc1tR~
//          data.colerr|=(1<<col);                                 //~vc1tR~
        	data.setColErr(col);                                   //~vc1tI~
            break;                                                 //~vc1sI~
        case AxeKey.KEYVALUE_NOTDEF:                               //~vc1sI~
	        data.updateText("");                                   //~vc1tI~
	        data.intValue=0;                                       //~vc1tI~
            break;                                                 //~vc1sI~
//        case AxeKey.KEYVALUE_KEY1:                               //~vc1sI~
//            str=getKeyname(Ppos,1,intvalue/*not used*/,value/*set if err*/);//~vc1sI~
//            Pview1.setText(str);                                 //~vc1sI~
//            Pview2.setText("");                                  //~vc1sI~
//            if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.chkInput settext key1 str="+str);//~vc1sI~
//            break;                                               //~vc1sI~
        default:                                                   //~vc1sI~
        	if ((intvalue & HWKEYID_GDK)!=0)                       //~vc1sR~
            	str=value;	//extendkey name                       //~vc1sI~
            else                                                   //~vc1sI~
        	if ((intvalue & HWKEYID_UNICODE)!=0)                   //~vc1tI~
	        	str=getKeynameUnicode(intvalue);                   //~vc1tI~
            else                                                   //~vc1tI~
        		str=String.valueOf((char)intvalue);                //~vc1sR~
	        data.updateText(str);                                  //~vc1sI~
	        data.intValue=intvalue;                                //~vc1tI~
            if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.chkInput data="+data.toString());//~vc1sR~
        }                                                          //~vc1sI~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.chkInput rc="+rc+",data="+data.toString());//~vc1tI~
        return rc;                                                 //~vc1sI~
    }                                                              //~vc1sI~
    //**************************************************************//~vc1tI~
    public static String getKeynameUnicode(int Pkey)                  //~vc1tI~//~vc1xR~
    {                                                              //~vc1tI~
    	int unicode=Pkey & ~HWKEYID_UNICODE;                       //~vc1tI~
        String rc=Integer.toHexString(unicode)+" ("+String.valueOf((char)unicode)+")";//~vc1tI~
		if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.getKeynameUnicode key="+Integer.toHexString(Pkey)+",name="+rc);//~vc1tI~
        return rc;                                                 //~vc1tI~
    }                                                              //~vc1tI~
//    //**************************************************************//~vc1sI~//~vc1tR~
//    @Override                                                      //~vc1sI~//~vc1tR~
//    public boolean afterTextChangedEach(TextView PtextView,Editable Peditable)//~vc1sR~//~vc1tR~
//    {                                                              //~vc1sI~//~vc1tR~
//        ListViewTag tag=(ListViewTag)PtextView.getTag();           //~vc1sR~//~vc1tR~
//            int pos=tag.pos;                                       //~vc1sI~//~vc1tR~
//            int col=tag.cols;                                      //~vc1sI~//~vc1tR~
//            ListData data=arrayData.get(pos);                      //~vc1sI~//~vc1tR~
//            int key=data.key1;                                     //~vc1sI~//~vc1tR~
//            if (fldProtected(key,col))                             //~vc1sI~//~vc1tR~
//                return true;                                      //~vc1sI~//~vc1tR~
//            data.isUpdated=true;                                   //~vc1sI~//~vc1tR~
//            data.col=col;      //focused col                       //~vc1sR~//~vc1tR~
//            if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.afterTextChanged view="+PtextView.toString()+",pos="+pos+",col="+col);//~vc1sR~//~vc1tR~
//            if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.afterTextChanged key="+Integer.toHexString(key)+",str="+Peditable.toString());//~vc1sI~//~vc1tR~
//            cursorpos=pos;                                         //~vc1sI~//~vc1tR~
//            if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.afterTextChanged cursorpos="+cursorpos);//~vc1sI~//~vc1tR~
////          PtextView.setBackgroundColor(COLOR_BGFOCUS);           //~vc1sR~//~vc1tR~
////          PtextView.setTextColor(COLOR_FG);                      //~vc1sR~//~vc1tR~
//            data.status=DATA_NORMAL;                               //~vc1sI~//~vc1tR~
//        String txt=(String)PtextView.getText();                            //~vc1sI~//~vc1tR~
//        data.updateText(txt);                                      //~vc1sI~//~vc1tR~
//        resetAdapter(); //notifyDataChanged                        //~vc1sI~//~vc1tR~
//        return true;                                               //~vc1sI~//~vc1tR~
//    }                                                              //~vc1sI~//~vc1tR~
    //**************************************************************//~vc1tI~
    //*from AxeDlgKbdLayoutHW by SoftKbd input                     //~vc1tI~
    //**************************************************************//~vc1tI~
    protected void onEditTextChanged(String Ptext,int Pstart,int Pbefore,int Pcount)//~vc1tR~//~vc1wR~
    {                                                              //~vc1tI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onEditTextChanged text="+Ptext+",cursorpos="+cursorpos);//~vc1tI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onEditTextChanged start="+Pstart+",count="+Pcount+",before="+Pbefore);//~vc1tR~
        if (cursorpos<0 || cursorpos>=arrayData.size())            //~vc1tI~
        	return;                                                //~vc1tI~
        int pos=cursorpos;                                         //~vc1tI~
        ListData data=arrayData.get(pos);                          //~vc1tI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.onEditTextChanged data="+data.toString());//~vc1tI~
        int col=data.col;                                          //~vc1tI~
        int key=data.key1;                                         //~vc1tI~
        if (fldProtected(key,col))                                 //~vc1tI~
        	return;                                                //~vc1tI~
        data.isUpdated=true;                                       //~vc1tI~
        TextView tv=null;                                          //~vc1tI~
        if (col==1)                                                //~vc1tI~
        	tv=data.textView2;                                     //~vc1tI~
        else                                                       //~vc1tI~
        if (col==2)                                                //~vc1tI~
            tv=data.textView3;                                     //~vc1tI~
        else                                                       //~vc1tI~
        if (col==3)                                                //~vc1tI~
            tv=data.textView4;                                     //~vc1tI~
        if (tv!=null)                                              //~vc1tI~
        {                                                          //~vc1tI~
        	tv.setText(Ptext);	//for chkInput                     //~vc1tI~
            data.posCursor=Pstart+Pcount;                          //~vc1tR~
        	if (chkInput(pos,tv))	//valid                        //~vc1tR~
		        listViewData[pos][LVDI_KEYCHAR+col]=data.intValue; //~vc1tI~
        	resetAdapter(); //notifyDataChanged                    //~vc1tI~
        }                                                          //~vc1tI~
    }                                                              //~vc1tI~
    //*********************************************                //~vc1sI~
    //*can be override                                             //~vc26I~
    //*********************************************                //~vc26I~
    protected boolean fldProtected(int Pkey,int Pcol)                //~vc1sI~//~vc1wR~//~vc26R~
    {                                                              //~vc1sI~
	    return fldProtectedStatic(Pkey,Pcol);//~vc26I~
    }                                                              //~vc1sI~
    //*********************************************                //~vc26I~
    protected static boolean fldProtectedStatic(int Pkey,int Pcol) //~vc26I~
    {                                                              //~vc26I~
		boolean rc=Pcol==1 && (Pkey>='a' && Pkey<='z');            //~vc26I~
        return rc;                                                 //~vc26I~
    }                                                              //~vc26I~
//**********************************************************************//~vc1sI~
//*check update and save to profile at dialog close                //~vc1sI~
//**********************************************************************//~vc1sI~
	@Override //AxeList                                            //~vc1sI~
	public boolean saveUpdate()                                    //~vc1sI~
    {                                                              //~vc1sI~
    	boolean rc=true;                                           //~vc1sI~
    //***********                                                  //~vc1sI~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.saveUpdate");  //~vc1xI~
//        int listsz=arrayData.size();                               //~vc1sI~//~vc1xR~
//        if (cursorpos!=INVALID_POS && cursorpos<listsz) //last focused entry//~vc1sR~//~vc1xR~
//        {                                                          //~vc1tI~//~vc1xR~
//            if (focusView1!=null)                                  //~vc1tI~//~vc1xR~
//                rc=chkInput(cursorpos,focusView1);                     //~vc1sR~//~vc1tR~//~vc1xR~
//        }                                                          //~vc1tI~//~vc1xR~
//        if (rc)                                                    //~vc1sI~//~vc1xR~
	        savePreference();                                      //~vc1sR~
        return rc;                                                 //~vc1sI~
    }//saveupdate                                                  //~vc1sI~
////**********************************************************************//~vc1sI~//~vc1tR~
//    private boolean isSoftKbdOpen()     //TODO test                //~vc1sI~//~vc1tR~
//    {                                                              //~vc1sI~//~vc1tR~
//        boolean rc=false;                                          //~vc1sI~//~vc1tR~
//        int[] loc=new int[2];                                      //~vc1sM~//~vc1tR~
//        Rect r=new Rect();                                         //~vc1sM~//~vc1tR~
//    //********************                                         //~vc1sI~//~vc1tR~
////        Button btn=axeDialog.btnClose;                             //~vc1sM~//~vc1tR~
//        int dialogHH=axeDialog.getWindow().getDecorView().getHeight();  //TODO test//~vc1sM~//~vc1tR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.isSoftKbdOpen dialogHH="+dialogHH+",dialogHeight="+dialogHeight);//~vc1sI~//~vc1tR~
//        int viewHH=listView.getHeight();                           //~vc1sM~//~vc1tR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.isSoftKbdOpen viewHH="+viewHH);//~vc1sI~//~vc1tR~
//        if (dialogHeight==0)                                       //~vc1sM~//~vc1tR~
//        {                                                          //~vc1sI~//~vc1tR~
//            dialogHeight=dialogHH;  //initial height               //~vc1sM~//~vc1tR~
////            btn.requestFocus();                                    //~vc1sI~//~vc1tR~
//        }                                                          //~vc1sI~//~vc1tR~
//        else                                                       //~vc1sI~//~vc1tR~
//            rc=dialogHH!=dialogHeight;                             //~vc1sI~//~vc1tR~
//        listView.getLocationOnScreen(loc);                         //~vc1sI~//~vc1tR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.isSoftKbdOpen loc onScreen="+loc[0]+","+loc[1]);//~vc1sI~//~vc1tR~
//        listView.getLocationInWindow(loc);                         //~vc1sI~//~vc1tR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.isSoftKbdOpen loc inWindows="+loc[0]+","+loc[1]);//~vc1sI~//~vc1tR~
//        listView.getLocalVisibleRect(r);                        //~vc1sI~//~vc1tR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.isSoftKbdOpen localVisibility="+r.toString());//~vc1sI~//~vc1tR~
//        listView.getGlobalVisibleRect(r);                       //~vc1sI~//~vc1tR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.isSoftKbdOpen GlobalVisibility="+r.toString());//~vc1sI~//~vc1tR~
//                                                                   //~vc1sI~//~vc1tR~
//                                                                   //~vc1sI~//~vc1tR~
////        btn.getLocationOnScreen(loc);                              //~vc1sI~//~vc1tR~
////        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.isSoftKbdOpen btn loc onScreen="+loc[0]+","+loc[1]);//~vc1sI~//~vc1tR~
////        btn.getLocationInWindow(loc);                              //~vc1sI~//~vc1tR~
////        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.isSoftKbdOpen btn loc inWindows="+loc[0]+","+loc[1]);//~vc1sI~//~vc1tR~
////        btn.getLocalVisibleRect(r);                                //~vc1sI~//~vc1tR~
////        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.isSoftKbdOpen btn localVisible="+r.toString());//~vc1sI~//~vc1tR~
////        btn.getGlobalVisibleRect(r);                               //~vc1sI~//~vc1tR~
////        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.isSoftKbdOpen btn GlobalVisible="+r.toString());//~vc1sI~//~vc1tR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.isSoftKbdOpen rc="+rc);//~vc1sI~//~vc1tR~
//        if (rc)                                                    //~vc1sI~//~vc1tR~
//            Utils.showToast("imeUP");                              //~vc1sI~//~vc1tR~
//        return rc;                                                 //~vc1sI~//~vc1tR~
//    }                                                              //~vc1sI~//~vc1tR~
//    //****************************                                 //~vc1sI~//~vc1tR~
//    private void setInitialFocus()                                 //~vc1sI~//~vc1tR~
//    {                                                              //~vc1sI~//~vc1tR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.setInitialFocus dialogHeight="+dialogHeight);//~vc1sI~//~vc1tR~
//        if (getSelectedPos()<0)                                    //~vc1sI~//~vc1tR~
//            select(0);                                             //~vc1sI~//~vc1tR~
//    }                                                              //~vc1sI~//~vc1tR~
//**********************************                               //~vc26I~
//complement +Shift,+AltGr and +Shift+AltGR,                       //~vc29R~
//return 0:goto onKeyFromDialog                                    //~vc29R~
//return -1:goto onKeyFroimDialogHW                                //~vc29R~
//return >0:goto onKeyFroimDialogHW width new keyvlue              //~vc29R~
//extkey 0x20000000+gdkKeyvalue                                    //~vc26I~
//unicode 0x40000000+unicode                                       //~vc26I~
//**********************************                               //~vc26I~
    public static int chkHWLayout(int Pkeycode,int Pmeta,int Punicode)//~vc26R~//~vc29R~
    {                                                              //~vc26I~
    	int rc=0;                                                  //~vc26I~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.chkHWLayout keycode=0x"+Integer.toHexString(Pkeycode)+",meta0x="+Integer.toHexString(Pmeta)+",unicode=0x"+Integer.toHexString(Punicode));//~vc26R~//~vc29R~
        if (Dump.Y) Dump.println("AxeLstKbdLayoutHW.chkHWLayout axeStatus=0x"+Integer.toHexString(Gxeh.axeStatus));//~vc2gI~
    	getCurrentMap();                                           //~vc26I~
        if (Pkeycode<0||Pkeycode>=currentMap.length)               //~vc26I~
        	return rc;                                             //~vc26I~
        int col=0;                                                 //~vc26I~
//      if (true && (Pmeta & KeyEvent.META_SHIFT_RIGHT_ON)!=0)	//TODO test AltGR//~vc2gI~
  		boolean swSR=(Gxeh.axeStatus & AXES_ALTG_RIGHTSHIFT)!=0;   //~vc2gR~
  		boolean swAR=(Gxeh.axeStatus & AXES_ALTG_RIGHTALT)!=0;     //~vc2gR~
  		boolean swAL=(Gxeh.axeStatus & AXES_ALTG_LEFTALT)!=0;      //~vc2gI~
//		swSR=true;  //TODO test                                    //~vc2gI~
        if (swSR && (Pmeta & KeyEvent.META_SHIFT_RIGHT_ON)!=0)	//TODO test AltGR//~vc2gR~
        {                                                          //~vc26I~
	        if ((Pmeta & KeyEvent.META_SHIFT_LEFT_ON)!=0)          //~vc26R~
            	col=3;                                             //~vc26I~
            else                                                   //~vc26I~
            	col=2;                                             //~vc26I~
        }                                                          //~vc26I~
        else                                                       //~vc26I~
//      if ((Pmeta & AxeKeyValue.META_ALT_R)!=0)                   //~vc26I~//~vc2gR~
        if (swAR && (Pmeta & AxeKeyValue.META_ALT_R)!=0)           //~vc2gR~
	        if ((Pmeta & AxeKeyValue.META_SHIFT)!=0)               //~vc26I~
            	col=3;                                             //~vc26I~
            else                                                   //~vc26I~
            	col=2;                                             //~vc26I~
        else                                                       //~vc26I~
        if (swAL && (Pmeta & AxeKeyValue.META_ALT_L)!=0)           //~vc2gI~
	        if ((Pmeta & AxeKeyValue.META_SHIFT)!=0)               //~vc2gI~
            	col=3;                                             //~vc2gI~
            else                                                   //~vc2gI~
            	col=2;                                             //~vc2gI~
        else                                                       //~vc2gI~
////      if ((Pmeta & AxeKeyValue.META_ALT_L)!=0 && Punicode==0)  //~vc29R~
//        if ((Pmeta & AxeKeyValue.META_ALT_L)!=0)                 //~vc29R~
//        {                                                        //~vc29R~
//            if (Dump.Y) Dump.println("AxeKbdDialogHW.chkHWLayout ALT_LEFT ON keycode="+Integer.toHexString(Pkeycode)+",unicode="+Integer.toHexString(Punicode));//~vc29R~
//            if (Pkeycode==KEYCODE_ALT_LEFT)                      //~vc29R~
//                return 0;                                        //~vc29R~
//            if (AxeKeyValue.isExtendedKey(Pkeycode))             //~vc29R~
//                return 0;   //no translation                     //~vc29R~
//            return -1;                                           //~vc29R~
//        }                                                        //~vc29R~
//        else                                                     //~vc29R~
//        if ((Pmeta & AxeKeyValue.META_CTL)!=0)                   //~vc29R~
//        {                                                        //~vc29R~
//            if (Dump.Y) Dump.println("AxeKbdDialogHW.chkHWLayout CTRL ON keycode="+Integer.toHexString(Pkeycode)+",unicode="+Integer.toHexString(Punicode));//~vc29R~
//            if (Pkeycode==KEYCODE_CTRL_LEFT||Pkeycode==KEYCODE_CTRL_RIGHT)//~vc29R~
//                return 0;                                        //~vc29R~
//            if (AxeKeyValue.isExtendedKey(Pkeycode))             //~vc29R~
//                return 0;   //no translation                     //~vc29R~
//            return -1;                                           //~vc29R~
//        }                                                        //~vc29R~
//        else                                                     //~vc29R~
        if ((Pmeta & AxeKeyValue.META_SHIFT)!=0)                   //~vc26I~
        	col=1;                                                 //~vc26I~
//        else                                                     //~vc29R~
//        if (Punicode==0)                                         //~vc29R~
////          return -1;  //goto onKeyFromDialogHW(with keycodeToCode())//~vc29R~
//            return 0;   //goto onKeyFromDialog                   //~vc29R~
        if (col==0)                                                //~vc26I~
        	return rc;                                             //~vc26I~
        int key=currentMap[Pkeycode][0];                               //~vc26I~
    	if (fldProtectedStatic(key,col))                           //~vc26R~
        	return rc;                                             //~vc26I~
        if (key==Punicode)	//no translation                       //~vc26I~
        	return rc;                                             //~vc26I~
	    key=currentMap[Pkeycode][col];                         //~vc26R~
        if (key!=0)                                                //~vc26I~
        {                                                          //~vc26I~
        	if ((key & HWKEYID_GDK)!=0)                            //~vc26I~
            {                                                      //~vc26I~
            	rc=AxeKbdKey.getSpinnerKey(key & ~ HWKEYID_GDK);   //~vc26I~
            	rc|=HWKEYID_GDK;                                   //~vc26I~
            }                                                      //~vc26I~
            else                                                   //~vc26I~
        	if ((key & HWKEYID_UNICODE)!=0)  //hex specification   //~vc26R~
            {                                                      //~vc26I~
            	int uc=key & ~HWKEYID_UNICODE;                     //~vc26I~
            	if (uc<=AxeKey.END_OF_ASCII)   //<=7f              //~vc26I~
                    rc=uc;  //ascii                                //~vc26I~
                else                                               //~vc26I~
                    rc=key;                                        //~vc26I~
            }                                                      //~vc26I~
            else                                                   //~vc26I~
            {                                                      //~vc26I~
            	if (key>AxeKey.END_OF_ASCII)   //<=7f              //~vc26I~
            		rc=key | HWKEYID_UNICODE;                      //~vc26I~
            }                                                      //~vc26I~
        }                                                          //~vc26I~
		if (Dump.Y) Dump.println("AxeKbdDialogHW.chkHWLayout col="+col+",rc="+Integer.toHexString(rc)+",key="+Integer.toHexString(key));//~vc26R~//~vc29R~
        return rc;                                                 //~vc26I~
    }                                                              //~vc26I~
    //***************************************************          //~vc2iI~
    public static int getKeycodeOfUnicode(int Punicode)            //~vc2iI~
    {                                                              //~vc2iI~
        int[][] cmap=AxeKbdDialogHW.getDefaultMap();               //~vc2iR~
        int keycode=-1;                                            //~vc2iI~
        for (int ii=0;ii<cmap.length;ii++)                            //~vc2iI~
        {                                                          //~vc2iI~
            if (Punicode==cmap[ii][0])                             //~vc2iI~
            {                                                      //~vc2iI~
                keycode=ii;                                        //~vc2iI~
                break;                                             //~vc2iI~
            }                                                      //~vc2iI~
        }                                                          //~vc2iI~
        if (Dump.Y)Dump.println("AxeLstKbdLayoutHW:getKeycodeOfUnicode keycode="+Integer.toHexString(keycode)+",unicode="+Integer.toHexString(Punicode));//~vc2iI~
        return keycode;                                            //~vc2iI~
    }                                                              //~vc2iI~
}//class                                                           //~1528I~
