//*CID://+vc2vR~: update#= 465;                                    //~vc21R~//~vc2vR~
//**********************************************************************//~1528I~
//vc2v 2020/08/09 xekbd;Shift is not work with Shortcut            //~vc2vI~
//vc21 2020/07/10 AxeKbd protect delete or nullify 1st column because it delete th line//~vc21I~
//vc1y 2020/07/09 AxeKbd reset to Default did not cleared when not app restart case//~vc1yI~
//vc1x 2020/07/08 member variable is initialized when defualt constructor defined(not non default constructor is define)//~vc1xI~
//vc1w 2020/07/06 AxeKbd updatelog extends KbdLayoutHW             //~vc1wI~
//vaas:120102 (Axe) kbd position change;move SYMs to swipe-right(top-right)//~vaasI~
//**********************************************************************//~vaasI~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1109R~//~1528I~

import com.ahsv.utils.UView;
import com.xe.Axe.kbd.AxeKbdDialog;
import com.xe.Axe.kbd.AxeKbdKey;
import com.xe.Axe.kbd.AxeKbdView;
import com.xe.Axe.kbd.AxeSoftKbd;
import com.ahsv.utils.Utils;

import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;

import static com.xe.Axe.AxeLstKbdLayoutHW.*;

//~1528I~
                                                                   //~1528I~
//public class AxeLstKbdLayout extends AxeList                        //~1612R~//~1919R~//~vc1wR~
public class AxeLstKbdLayout extends AxeLstKbdLayoutHW             //~vc1wI~
{                                                                  //~1528I~
	private static final String PREFKEY="KB";                      //~1919R~
//	private static final String PREFKEYSUM="KBSUM";                //~1923I~
	private static final int ROWID =R.layout.listrowkbdlayout;//~1529I~//~1919M~
	private static final int COLID_SYMBOL=R.id.Symbol;             //~vc1wI~
	private static final int COLID_ROWCOL=R.id.RowCol;             //~vc1wI~
    public  static final int KEYID_UNICODE=AxeKeyValue.KBF_UNICODE;//~vc1wI~
    public  static final int DBCS_SPACE=0x3000;                    //~vc1yR~
                                                                   //~1809I~
	public static final String[] swipedest={                        //~1919I~
    										"Base",                 //~1919I~
//  										"Shift+",               //~1919I~//~vaasR~
    										"L:Shift",             //~vaasR~
                        					"R:Symbol",            //~vaasR~
//                     						"AltGr+",              //~1919I~//~vaasR~
                       						"U:AltGr",             //~vaasR~
//                      					"S+AltGr+",            //~1919I~//~vaasR~
                        					"D:S+AltGr",           //~vaasR~
//                      					"Flick-Down",          //~1A11R~//~vaasR~
    								};
	public final static String[] tgtkeynametbl=AxeKbdKey.SspinnerData;//~1919I~
//    private static String[] keyNames;                            //~1920R~
//  private static int[][] flagedKeyCodes;	//extkey is flaged index value//~1923R~//~vc1wR~
    private int[][] flagedKeyCodes;	//extkey is flaged index value //~vc1wI~
    private static int[][] flagedKeyCodesStatic;	//extkey is flaged index value//~vc1yI~
//  private static int[][] flagedKeyCodesDefault;                  //~1923R~//~vc1wR~
//  private int[][] flagedKeyCodesDefault;                         //~vc1wI~//~vc1yR~
//  private static int[][] currentMapStatic;                       //~vc1wI~//~vc1yR~
//  private static int[] keyIndexMap;	//list index --> keyindex+swipeindex//~1920R~//~vc1wR~
//  private static String[] keyLabelTbl;                              //~1920I~//~vc1wR~
    private String[] keyLabelTbl;                                  //~vc1wI~
//  private static int mapSize;                                    //~1920I~//~vc1wR~
    private static boolean existPrefData;                                  //~1923I~
                                                                   //~1809I~
    private static final int rowSize=10;                           //~vc1wR~
                                                                   //~1529I~
	private TextView selectedView2;                                //~1816R~
//  private AxeSpinner spinner;                                    //~1816R~//~vc1wR~
//  private int spinnerPos,csrPos;                                 //~1814R~//~vc1wR~
    private boolean reset;                                         //~1923I~
                                                                   //~1813I~
//*****************                                                //~1813I~
    public AxeLstKbdLayout()                                       //~vc1xR~
    {                                                              //~vc1xR~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.defaultConstructor");//~vc1xR~
    }                                                              //~vc1xR~
//    public AxeLstKbdLayout(int PdialogId,ViewGroup PlayoutView,int Pmenuid,int Prowid)                                               //~1112I~//~1612I~//~1919R~//~vc1wR~
//    {                                                              //~1612I~//~vc1xR~
////      super(PdialogId,PlayoutView,Pmenuid,Prowid,true/*editableRow*/,true/*clickable*/);//~1919R~//~1B02R~//~vc1xR~
//        super(PdialogId,PlayoutView,Pmenuid,Prowid);             //~vc1xR~
//        maxMod=1+AxeKbdView.MAXSWIPE;   //Normal+4               //~vc1xR~
//        maxCol=1+maxMod; //RowCol+Normal+4                       //~vc1xR~
//        colNormal=0;    //for setSpinnerItemSelected(int Ppos)   //~vc1xR~
//    }                                                              //~1612I~//~vc1xR~
    public static AxeLstKbdLayout newInstance(int PdialogId,ViewGroup PlayoutView,int Pmenuid,int Prowid)//~vc1xR~
    {                                                              //~vc1xR~
    	AxeLstKbdLayout al=new AxeLstKbdLayout();                  //~vc1xR~
//      al.maxMod=1+AxeKbdView.MAXSWIPE;   //Normal+4=5            //~vc1xR~//~vc1wR~//~vc21R~
        maxMod=1+AxeKbdView.MAXSWIPE;   //Normal+4=5               //~vc21I~
//      al.maxCol=1+al.maxMod; //RowCol+Normal+4=6                 //~vc1xR~//~vc1wR~//~vc21R~
        al.maxCol=1+maxMod; //RowCol+Normal+4=6                    //~vc21I~
        al.colNormal=0;    //for setSpinnerItemSelected(int Ppos)  //~vc1xR~
        al.initInstance(PdialogId,PlayoutView,Pmenuid,Prowid,true/*editable*/,true/*clickable*/);//~vc1xR~
        return al;
    }                                                              //~vc1wI~
////******for AxeLstKbdLayoutHW                                    //~vaasR~
//    public AxeLstKbdLayout(int PdialogId,ViewGroup PlayoutView,int Pmenuid,int Prowid,boolean PeditableRow,boolean Pclickable)//~vaasR~
//    {                                                            //~vaasR~
//        super(PdialogId,PlayoutView,Pmenuid,Prowid,PeditableRow,Pclickable);//~vaasR~
//    }                                                            //~vaasR~
//*****************                                                //~1528I~
//    public static AxeLstKbdLayout setupListView(AxeSpinner Pspinner,int PdialogId,ViewGroup PlayoutView)//~1612R~//~1919R~//~vc1wR~
//    {                                                              //~1528I~//~vc1wR~
//        AxeLstKbdLayout al=new AxeLstKbdLayout(PdialogId,PlayoutView,0/*Shift menuId*/,ROWID);//~1612R~//~1919R~//~vc1wR~
//        al.spinner=Pspinner;                                       //~1814R~//~vc1wR~
//        return al;                                                 //~1528I~//~vc1wR~
//    }                                                              //~1528I~//~vc1wR~
	//******************************************************       //~vc1wI~
	//*from AxeDlgKbdLayout                                        //~vc1wI~
	//******************************************************       //~vc1wI~
    public static AxeLstKbdLayout setupListView(AxeDlgKbdLayout Pdialog,AxeSpinner Pspinner,int PdialogId,ViewGroup PlayoutView)//~vc1wI~
    {                                                              //~vc1wI~
//  	AxeLstKbdLayout al=new AxeLstKbdLayout(PdialogId,PlayoutView,0/*Shift menuId*/,ROWID);//~vc1xR~
    	AxeLstKbdLayout al=AxeLstKbdLayout.newInstance(PdialogId,PlayoutView,0/*Shift menuId*/,ROWID);//~vc1xR~
        al.spinner=Pspinner;                                       //~vc1wI~
        al.axeDialog=Pdialog;                                      //~vc1wI~
        return al;                                                 //~vc1wI~
    }                                                              //~vc1wI~
//*****************                                                //~1612I~
//    @Override                                                      //~1612I~//~vc1wR~
//    public void setListViewData()   //fromAxeList constructor      //~1920R~//~vc1wR~
//    {                                                              //~1612I~//~vc1wR~
//        getKbdLayout();                                            //~1920M~//~vc1wR~
//        setListViewData(this);  //fill arraylist for KbdLayout//~1612R~//~1919R~//~vc1wR~
//    }                                                              //~1612I~//~vc1wR~
//    //*****************************                                //~1809R~//~vc1wR~
//    public void add(int Psource,int Ptarget)                       //~1613R~//~vc1wR~
//    {                                                              //~1612I~//~vc1wR~
//        ListData linedata=new ListData(Psource,Ptarget);           //~1612I~//~vc1wR~
//        if (Dump.Y) Dump.println("AxeList addKbdLayout src="+Integer.toHexString(Psource)+",tgt="+Integer.toHexString(Ptarget));//~1612I~//~1919R~//~vc1wR~
//        super.add(linedata);                                       //~1612I~//~vc1wR~
//    }                                                              //~1612I~//~vc1wR~
    protected void add(int Ppos,int[] Pmap)                        //~vc1wI~
    {                                                              //~vc1wI~
    	String str0,str1,str2,str3,str4,str5;                           //~vc1wI~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.add map="+ Utils.toHexString(Pmap));//~vc1wI~
        int pos=Pmap[0];                                           //~vc1wR~
//      str0=chkKeyName(key);                                      //~vc1wR~
//      if (str0==null)                                            //~vc1wR~
//          str0=String.valueOf((char)key);                        //~vc1wR~
    	str0=String.format("%d-%d",pos/rowSize+1,pos%rowSize+1);   //~vc1wR~
    	str1=codeToName(Pmap[1]);    //normal                       //~vc1wR~
    	str2=codeToName(Pmap[2]);    //shift                        //~vc1wI~
    	str3=codeToName(Pmap[3]);    //symbol                       //~vc1wI~
    	str4=codeToName(Pmap[4]);    //altgr                        //~vc1wI~
    	str5=codeToName(Pmap[5]);    //s+altgr                      //~vc1wI~
        ListData linedata=new ListData(pos,str0,str1,str2,str3,str4,str5);//~vc1wR~
        linedata.pos=Ppos;                                         //~vc1wI~
        add(linedata);                                             //~vc1wI~
    }                                                              //~vc1wI~
	//******************                                           //~1809I~
//    @Override                                                      //~1809I~//~vc1wR~
//    public void onItemSelected(int Ppos,ListData Pitem)             //~1816R~//~1824R~//~vc1wR~
//    {                                                              //~1809I~//~vc1wR~
//        if (Dump.Y)Dump.println("AxeLstKbdLayout onItemSelected pos="+Ppos);//~1816R~//~1919R~//~vc1wR~
//        selectedView2=(Pitem.textView2);                           //~1816R~//~vc1wR~
//        csrPos=Ppos;                                               //~1810I~//~vc1wR~
//        setSpinnerPrompt(csrPos);                                  //~1814I~//~vc1wR~
//    }                                                              //~1809I~//~vc1wR~
//    //******************                                           //~1824I~//~vc1wR~
//    @Override           //by onClickItemEach                                           //~1824I~//~vc1wR~
//    public void onItemClicked(int Ppos,ListData Pitem)             //~1824I~//~vc1wR~
//    {                                                              //~1824I~//~vc1wR~
//        if (Dump.Y)Dump.println("AxeLstKbdLayout onItemClicked pos="+Ppos);//~1919R~//~vc1wR~
//        selectedView2=(Pitem.textView2);                           //~1824I~//~vc1wR~
//        csrPos=Ppos;                                               //~1824I~//~vc1wR~
//        setSpinnerPrompt(csrPos);                                  //~1824I~//~vc1wR~
//        notifyDataChanged();    //super                            //~1824R~//~vc1wR~
//    }                                                              //~1824I~//~vc1wR~
    //**************************************************************//~vc21I~
    @Override //AxeList                                            //~vc21I~
    public boolean onClickEach(View Pview)                         //~vc21I~
    {                                                              //~vc21I~
        int pos,col;                                               //~vc21I~
        ListData data=null;                                        //~vc21I~
        TextView v1,v2;                                            //~vc21I~
    //*********                                                    //~vc21I~
        	if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickEach view="+Pview.toString());//~vc21I~
        	if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickEach forcusViewID="+Integer.toHexString(UView.getViewFocusedID((Dialog)axeDialog))); //TODO test//~vc21I~
            ListViewTag tag=(ListViewTag)Pview.getTag();           //~vc21I~
        	if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickEach tag="+tag.toString());//~vc21I~
            pos=tag.pos;                                           //~vc21I~
            col=tag.cols;                                          //~vc21I~
            data=arrayData.get(pos);                               //~vc21I~
            if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickEach col="+col+",data="+data.toString());//~vc21I~
//            if (fldProtected(data.key1,col))                     //~vc21I~
//            {                                                    //~vc21I~
//                Utils.showToast(R.string.Err_FieldProtected);    //~vc21I~
//                return true;                                     //~vc21I~
//            }                                                    //~vc21I~
            data.col=col;                                          //~vc21I~
            cursorpos=pos;                                         //~vc21I~
            String text=((TextView)Pview).getText().toString();    //~vc21I~
            axeDialog.onClickListItem(cursorpos,col,text);         //~vc21I~
    		resetAdapter(); //notifyDataChanged                    //~vc21I~
            return true;                                           //~vc21I~
    }                                                              //~vc21I~
//*************************************************                //~vc1wR~
//    //******************                                               //~1612I~//~1809R~//~vc1wR~
//    @Override //AxeList.saveUpdate                                                     //~1612I~//~vc1wR~
//    public void updateTbl(int[] Pnewtbl)                           //~1612I~//~vc1wR~
//    {                                                              //~1612I~//~vc1wR~
//        if (reset)                                                 //~1923I~//~vc1wR~
//            clearPreference(flagedKeyCodes);                       //~1923R~//~vc1wR~
//        updateKbdLayout(Pnewtbl);                       //~1612R~  //~1919R~//~vc1wR~
//    }                                                              //~1612I~//~vc1wR~
//************************************                             //~1923R~
//*adaptor data is flagedKeyCode                                   //~1923I~
//************************************                             //~1923I~
//    @Override                                                      //~1612I~//~vc1wR~
//    public int chkInputValue(int Ppos,String Pvalue,boolean Ptoast)         //~1613I~//~1A29R~//~vc1wR~
//    {                                                              //~1612I~//~vc1wR~
//        int intval;                                                //~1920I~//~vc1wR~
//        int idx=AxeKbdKey.getSpinnerIndex(Pvalue);                 //~1920I~//~vc1wR~
//        if (idx>0)                                                 //~1920I~//~vc1wR~
//            intval=idx|AxeSoftKbd.KEYID_EXTKEY;                    //~1920I~//~vc1wR~
//        else                                                       //~1920I~//~vc1wR~
//        {                                                          //~1920I~//~vc1wR~
//            intval=AxeKey.chkInputCharCode(Pvalue); //KEYVALUE_ERR if hex numeric err//~1920I~//~vc1wR~
//        }                                                          //~1920I~//~vc1wR~
//        if (Dump.Y)Dump.println("AxeLstKbdLayout chkInputValue Str="+Pvalue+",int="+Integer.toHexString(intval));//~1919I~//~vc1wR~
//        return  intval;                                            //~1919R~//~vc1wR~
//    }                                                              //~1612I~//~vc1wR~
//******************                                               //~1531I~
//    @Override                                                      //~1612I~//~vc1wR~
//    public String getKeyname(int Ppos,int Pfldid,int Pkey,String Perr)//~1602R~//~1A29R~//~vc1wR~
//    {                                                              //~1531I~                                  //~1920I~//~vc1wR~
//        label=codeToName(Pkey);                                //~1920R~//~vc1wR~
//        return label;                                              //~1920I~//~vc1wR~
//    }                                                              //~1531I~//~vc1wR~
    //**************                                               //~1920I~
//    public String getSpinnerTitle(int Ppos)                        //~1920I~//~vc1wR~
//    {                                                              //~1920I~//~vc1wR~
//        String label;                                              //~1920I~//~vc1wR~
//        int mapidx=keyIndexMap[Ppos];                              //~1920I~//~vc1wR~
//        int row=mapidx>>8;                                         //~1920I~//~vc1wR~
//        int col=mapidx & 0xff;                                     //~1920I~//~vc1wR~
//        label=keyLabelTbl[row];                             //~1920I~//~vc1wR~
//        if (label==null)                                           //~1920I~//~vc1wR~
//            label=String.format("%d-%d",row/rowSize,row%rowSize);  //~1920I~//~vc1wR~
//        label+=":"+swipedest[col];                                 //~1920I~//~vc1wR~
//        return label;                                              //~1920I~//~vc1wR~
//    }                                                              //~1920I~//~vc1wR~
//*************                                                    //~1919I~
//    public void setListViewData(AxeLstKbdLayout Plist)             //~1919I~//~vc1wR~
//    {                                                              //~1919I~//~vc1wR~
//        for (int ii=0;ii<mapSize;ii++)                             //~1920R~//~vc1wR~
//        {                                                          //~1809I~//~vc1wR~
//            int mapidx=keyIndexMap[ii];                            //~1920I~//~vc1wR~
//            int row=mapidx>>8;                                     //~1920I~//~vc1wR~
//            int col=mapidx & 0xff;                                 //~1920I~//~vc1wR~
//            int codeidx=flagedKeyCodes[row][col];                  //~1923R~//~vc1wR~
//            Plist.add(ii,codeidx);                                //~1920R~//~vc1wR~
//        }                                                          //~1809I~//~vc1wR~
//    }                                                              //~1809I~//~vc1wR~
//*************                                                    //~1809I~
//  public void getKbdLayout()                                     //~1919R~//~vc1wR~
    protected void getKbdLayout()                                  //~vc1wR~
    {                                                              //~1809I~
//        if (keyIndexMap!=null)                                   //~1923R~
//            return;                                              //~1923R~
    	AxeKbdDialog axeKbdDialog=AxeG.axeIME.getXeKbd();           //~1919I~
//      int[][] flagedkeycodes=axeKbdDialog.getKbdLayoutCodeTbl(); //~1923R~//~vc1yR~
        int[][] flagedkeycodes;                                    //~vc1yI~
        if (flagedKeyCodesStatic!=null)                            //~vc1yI~
        {                                                          //~vc1yI~
       		flagedkeycodes=flagedKeyCodesStatic;	//status after previously closed(at savePreference)//~vc1yI~
        }                                                          //~vc1yI~
        else                                                       //~vc1yI~
        {                                                          //~vc1yI~
			flagedkeycodes=axeKbdDialog.getKbdLayoutCodeTbl();	//preference applyed at initial//~vc1yI~
        }                                                          //~vc1yI~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.getKbdLayout flagedKeycodes="+Utils.toHexString(flagedkeycodes));//~vc1yI~
//  	flagedKeyCodesDefault=flagedkeycodes;                      //~vc1wI~//~vc1yR~
//      kbdSize=flagedkeycodes.length;                             //~1923R~//~vc1wR~
//      mapSize=flagedkeycodes.length;                             //~vc1wI~//~vc1yR~
		flagedKeyCodes=initKeynameTbl(flagedkeycodes);//codetbl for update//~1923R~
	    flagedKeyCodesStatic=flagedKeyCodes;	//for next dialog open//~vc1yR~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.getKbdLayout static flagedKeycodes="+Utils.toHexString(flagedKeyCodesStatic));//~vc1yI~
        getListViewData(flagedkeycodes);                           //~vc1wI~
    }                                                              //~1919I~
//*************************************************************    //+vc2vI~
    private static int[][]  getFlagedKeyCodesStatic()               //+vc2vI~
    {                                                              //+vc2vI~
        int[][] rc;                                                //+vc2vI~
        if (flagedKeyCodesStatic==null)                            //+vc2vI~
        {                                                          //+vc2vI~
	    	AxeKbdDialog dlg=AxeG.axeIME.getXeKbd();               //+vc2vI~
			flagedKeyCodesStatic=dlg.getKbdLayoutCodeTbl();	//preference applyed at initial//+vc2vI~
        }                                                          //+vc2vI~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.getFlagedKeycodesStatic flagedKeycodes="+Utils.toHexString(flagedKeyCodesStatic));//+vc2vI~
       	return flagedKeyCodesStatic;                               //+vc2vI~
    }                                                              //+vc2vI~
//*************************************************************    //~1923R~
//*copy flagedCodeTbl for temporary update                         //~1923I~
//*create indexing adaptor list to flagedKeyCodes[flat+4swipe]     //~vc1wR~
//*************************************************************    //~1923I~
	public int[][] initKeynameTbl(int [][] Pflagedkeycodes)        //~1923R~
    {                                                              //~1919I~
        mapSize=Pflagedkeycodes.length;                            //~vc1yM~
//      int[][] flagedkeycodes=new int[kbdSize][AxeKbdView.MAXSWIPE+1];//~1923R~//~vc1wR~
        int[][] flagedkeycodes=new int[mapSize][AxeKbdView.MAXSWIPE+1];//~vc1wI~
//      int sz=kbdSize*(AxeKbdView.MAXSWIPE+1);                    //~1920M~//~vc1wR~
        int sz=mapSize*(AxeKbdView.MAXSWIPE+1);                    //~vc1wI~
//      keyIndexMap=new int[sz];                                   //~1920R~//~vc1wR~
//      keyLabelTbl=new String[kbdSize];                           //~1920I~//~vc1wR~
        keyLabelTbl=new String[mapSize];                           //~vc1wI~
        int mapidx=0;                                              //~1920I~
//      for (int ii=0;ii<kbdSize;ii++)                                 //~1919I~//~vc1wR~
        for (int ii=0;ii<mapSize;ii++)                             //~vc1wI~
        {                                                          //~1919I~
        	int code=Pflagedkeycodes[ii][0];                       //~1923R~
        	flagedkeycodes[ii][0]=code;                            //~1923R~
			keyLabelTbl[ii]=null;                                  //~1920I~
            if (code==AxeSoftKbd.KEYID_FIXED)                      //~1919I~
            	continue;                                          //~1919I~
            mapidx++;                                              //~vc1yI~
			keyLabelTbl[ii]=codeToName(code);                      //~1920I~
            for (int jj=0;jj<=AxeKbdView.MAXSWIPE;jj++)            //~1920R~
            {                                                      //~1919I~
//              keyIndexMap[mapidx++]=(ii<<8)+jj;                  //~1920I~//~vc1wR~
        		flagedkeycodes[ii][jj]=Pflagedkeycodes[ii][jj];          //~1923R~
            }                                                      //~1919I~
        }                                                          //~1919I~
        mapSize=mapidx;                                            //~1920I~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.initKeynameTbl flagedKeycodes="+Utils.toHexString(flagedkeycodes));//~vaasI~
//      if (Dump.Y) Dump.println("AxeLstKbdLayout.initKeynameTbl keyIndexMap="+Utils.toHexString(keyIndexMap));//~vaasI~//~vc1wR~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.initKeynameTbl keyLabelTbl="+keyLabelTbl.length+"="+Utils.toString(keyLabelTbl));//~vaasI~//~vc1wR~
        return flagedkeycodes;                                     //~1923R~
    }                                                              //~1919I~
//********************************************************         //~vc1wI~
//*create listViewData(base of arrayAdapter)                       //~vc1wI~
//********************************************************         //~vc1wI~
    @Override                                                      //~vc21I~
    protected void getListViewData(int[][] Pmap/*flagedkeycodetbl*/)//~vc1wR~
    {                                                              //~vc1wI~
        String key,value;                                          //~vc1wI~
        int sz,ctr=0;                                              //~vc1wR~
    //**************                                               //~vc1wI~
        if (Dump.Y) Dump.println("AxeLstKbdLayout:getListViewData mapSize="+Pmap.length+",Pmap="+Utils.toHexString(Pmap));//~vc1wR~
        sz=Pmap.length;                                            //~vc1wR~
        for (int ii=0;ii<sz;ii++)                                  //~vc1wR~
        {                                                          //~vc1wR~
            int normal=Pmap[ii][0];                                //~vc1wR~
//          if (normal<=0)                                         //~vc1wR~//~vc21R~
            if (normal<0)                                          //~vc21I~
                continue;                                          //~vc1wR~
            ctr++;                                                 //~vc1wR~
        }                                                          //~vc1wR~
	    if (Dump.Y)Dump.println("AxeLstKbdLayout:getListViewData set ctr="+ctr);//~vc1wI~
        kbdSize=ctr;   //litViewData size                          //~vc1wI~
        int[][] map=new int[ctr][LVDI_KEYCHAR+maxMod];             //~vc1wR~
        ctr=0;                                                     //~vc1wR~
        for (int ii=0;ii<sz;ii++)                                  //~vc1wI~
        {                                                          //~vc1wI~
//            int idx;                                             //~vc1wI~
//            if (ii>=KEYCODE_0 && ii<=KEYCODE_8) //move 0 to next of 9//~vc1wI~
//                idx=ii+1;                                        //~vc1wI~
//            else                                                 //~vc1wI~
//            if (ii==KEYCODE_9)                                   //~vc1wI~
//                idx=KEYCODE_0;                                   //~vc1wI~
//            else                                                 //~vc1wI~
//                idx=ii;                                          //~vc1wI~
            int[] map1=Pmap[ii];                            //~vc1wI~
//  		int idx=ii;                                            //~vc1wR~
            int normal=map1[0];                                    //~vc1wR~
//          if (normal<=0)                                         //~vc1wR~//~vc21R~
            if (normal<0)     //don not delete flat=0 line         //~vc21I~
                continue;                                          //~vc1wR~
//          map[ctr++]=new int[]{idx,normal,map1[1],map1[2],map1[3]};//~vc1wI~
            map[ctr]=new int[]{ii,normal,map1[1],map1[2],map1[3],map1[4]};//~vc1wR~
	        if (Dump.Y)Dump.println("AxeLstKbdLayout:getListViewData set ctr="+ctr+",ii="+ii+",map="+Utils.toHexString(map[ctr]));//~vc1wR~
            ctr++;                                                 //~vc1wI~
        }                                                          //~vc1wI~
        if (Dump.Y)Dump.println("AxeLstKbdLayout:getListViewData listViewData="+Utils.toHexString(map));//~vc1wR~
        listViewData=map;                                          //~vc1wI~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.getListViewData ctr="+kbdSize+",mapSize="+mapSize);//~vc1wR~
    }                                                              //~vc1wI~
//********************************************************         //~1923R~
//*falgedKeyCode(adaptor data) to keyname                          //~1923I~
//********************************************************         //~1923I~
	private String codeToName(int Pcode)                           //~1919I~
    {                                                              //~1919I~
    	String name;                                               //~1919I~
        if (Pcode==AxeSoftKbd.KEYID_FIXED) //-1                    //~vc1wI~
            name="";                                               //~vc1wI~
        else                                                       //~vc1wI~
        if ((Pcode & AxeSoftKbd.KEYID_EXTKEY)!=0)                       //~1919I~
            name=AxeKbdKey.SspinnerData[Pcode & ~AxeSoftKbd.KEYID_EXTKEY];//~1919I~
        else                                                       //~1919I~
        if (Pcode==0)                                              //~1919I~
            name="";                                               //~1919I~
        else                                                       //~1919I~
    	if ((Pcode & AxeLstKbdLayoutHW.HWKEYID_UNICODE)!=0)        //~vc1wI~
        	name=getKeynameUnicode(Pcode);                            //~vc1wI~
        else                                                       //~vc1wI~
        if (Pcode==' ')                                            //~vc1yI~
            name="("+AxeKeyValue.KEYLBL_SPACE+")";                 //~vc1yR~
        else                                                       //~vc1yI~
        if (Pcode==DBCS_SPACE)                                     //~vc1yI~
            name=String.valueOf((char)DBCS_SPACE)+" (dbcs)";       //~vc1yR~
        else                                                       //~vc1yI~
//      if (Pcode<0x80)                                            //~1919I~//~vc1wR~
            name=String.valueOf((char)Pcode);                      //~1919I~
//      else                                                       //~1919I~//~vc1wR~
//  		name=String.format("%04x (%s)",Pcode,String.valueOf((char)Pcode));//~1920I~//~vc1wR~
        if (Dump.Y) Dump.println("AxeLstKbdLayout code="+Integer.toHexString(Pcode)+",name="+name);//~1919I~
        return name;                                               //~1919I~
    }                                                              //~1919I~
//*****************************                                    //~1923R~
//*onClose                                                         //~1923I~
//*actually update data                                            //~1923I~
//*****************************                                    //~1923I~
//    public void updateKbdLayout(int[] Pnewtbl)                     //~1919I~//~vc1wR~
//    {                                                              //~1919I~//~vc1wR~
//        boolean changed=false;                                     //~1920I~//~vc1wR~
//        for (int ii=0;ii<mapSize;ii++)                            //~1920R~//~vc1wR~
//        {                                                          //~1919I~//~vc1wR~
//            int mapidx=keyIndexMap[ii];                            //~1920R~//~vc1wR~
//            int row=mapidx>>8;                                     //~1920I~//~vc1wR~
//            int col=mapidx & 0xff;                                 //~1920I~//~vc1wR~
//            int oldkeyid=flagedKeyCodes[row][col];                 //~1923R~//~vc1wR~
//            int newkeyid=Pnewtbl[ii];                              //~1920R~//~vc1wR~
//            if (newkeyid>0 && newkeyid!=oldkeyid) //updated        //~1920R~//~vc1wR~
//            {                                                      //~1920R~//~vc1wR~
//                changed=true;                                      //~1920I~//~vc1wR~
//                flagedKeyCodes[row][col]=newkeyid;                 //~1923R~//~vc1wR~
//                savePreference(row,col,newkeyid); //extkey flaged value//~1920R~//~vc1wR~
////                if (newkeyid==KeyData.NOT_DEFINED)               //~1920R~//~vc1wR~
////                    keyNames[ii]="";                             //~1920R~//~vc1wR~
////                else                                             //~1920R~//~vc1wR~
////                    keyNames[ii]=codeToName(newkeyid);           //~1920R~//~vc1wR~
//            }                                                      //~1920R~//~vc1wR~
//        }                                                          //~1919I~//~vc1wR~
//        if (changed)                                               //~1920I~//~vc1wR~
//        {                                                          //~1920I~//~vc1wR~
//            AxeProp.putPreference(PREFKEY,"1");   //data saved     //~1923R~//~vc1wR~
//            AxeKbdDialog axeKbdDialog=AxeG.axeIME.getXeKbd();      //~1920I~//~vc1wR~
//            axeKbdDialog.updateKbd(flagedKeyCodes);                //~1923R~//~vc1wR~
//        }                                                          //~1920I~//~vc1wR~
//    }                                                              //~1809I~//~vc1wR~
    protected void savePreference()                                //~vc1wI~
    {                                                              //~vc1wI~
        ListData data;                                             //~vc1wI~
        int[] keys;                                                //~vc1wI~
        boolean swChanged=false;                                   //~vc1wI~
    //**************                                               //~vc1wI~
        if (Dump.Y) Dump.println("AxeLstKbdLayout:savePreference isUpdateAll="+isUpdateAll);//~vc1wI~
        if (Dump.Y)Dump.println("AxeLstKbdLayout:savePreference old flagedKeyCode="+Utils.toHexString(flagedKeyCodes));//~vc1wR~//~vc21R~
        if (Dump.Y)Dump.println("AxeLstKbdLayout:savePreference new listViewData="+Utils.toHexString(listViewData));//~vc1wI~//~vc21R~
        int sz=arrayData.size();                                   //~vc1wI~
        for (int ii=0;ii<sz;ii++)                                  //~vc1wI~
        {                                                          //~vc1wI~
            data=arrayData.get(ii);                                //~vc1wI~
            if (Dump.Y)Dump.println("AxeLstKbdLayout:savePreference ii="+ii+",isUpadated="+data.isUpdated+",data="+data.toString());//~vc1wI~
          if (!isUpdateAll)                                        //~vc1wI~
            if (!data.isUpdated)                                   //~vc1wI~
            	continue;                                          //~vc1wI~
            keys=listViewData[ii];                                 //~vc1wI~
//          int keycode=keys[LVDI_KEYCODE];                        //~vc1wI~
            int keycode=keys[0];      //flagedKeyCode tbl index    //~vc1wR~
//          int[] cmap=currentMap[keycode];                        //~vc1wI~
            int[] cmap=flagedKeyCodes[keycode];                     //~vc1wI~
            if (Dump.Y)Dump.println("AxeLstKbdLayout:savePreference mapIdx="+keycode+",flagedCodeTbl="+Utils.toHexString(cmap)+",listViewData="+Utils.toHexString(keys));//~vc1wI~
//          for (int jj=1;jj<maxMod;jj++)                          //~vc1wI~
            for (int jj=1;jj<maxCol;jj++)                          //~vc1wI~
            {                                                      //~vc1wI~
//          	int newval=keys[LVDI_KEYCHAR+jj];                  //~vc1wI~
            	int newval=keys[jj];    //listViewData             //~vc1wI~
                if (Dump.Y)Dump.println("AxeLstKbdLayout:savePreference err="+data.colerr+",ii="+ii+",jj="+jj+",new="+Integer.toHexString(newval)+",old="+Integer.toHexString(cmap[jj-1]));//~vc1wR~
                if (data.isColErr(jj))                             //~vc1wI~
                	continue;                                      //~vc1wI~
//          	if (newval==cmap[jj])                              //~vc1wR~
            	if (newval==cmap[jj-1])                            //~vc1wI~
                	continue;                                      //~vc1wI~
//              String key=keycode+"_"+jj;                         //~vc1wI~
//              if (Dump.Y)Dump.println("AxeLstKbdLayout:savePreference ii="+ii+",jj="+jj+",new="+Integer.toHexString(newval));//~vc1wI~//~vc21R~
//              savePreference(PREFKEY+key,newval==0 ? null : Integer.toHexString(newval));//~vc1wI~
//        		int mapidx=keyIndexMap[keycode];                   //~vc1wR~
//            	int row=mapidx>>8;                                 //~vc1wR~
//            	int col=mapidx & 0xff;                             //~vc1wR~
//              if (Dump.Y)Dump.println("AxeLstKbdLayout:savePreference ii="+ii+",jj="+jj+",row="+row+",coL="+col+",new="+Integer.toHexString(newval));//~vc1wR~//~vc21R~
//              savePreference(row,col,newval); //extkey flaged value//~vc1wR~
                savePreference(keycode,jj-1,newval); //extkey flaged value//~vc1wI~
//              cmap[jj]=newval;	//update flagedCodeTbl         //~vc1wR~
                cmap[jj-1]=newval;	//update flagedCodeTbl         //~vc1wI~
                swChanged=true;                                    //~vc1wI~
            }                                                      //~vc1wI~
        }                                                          //~vc1wI~
        if (swChanged)                                             //~vc1wI~
        {                                                          //~vc1wI~
            AxeProp.putPreference(PREFKEY,"1");   //data saved     //~vc1wI~
            AxeKbdDialog axeKbdDialog=AxeG.axeIME.getXeKbd();      //~vc1wI~
            axeKbdDialog.updateKbd(flagedKeyCodes);                //~vc1wI~
        }                                                          //~vc1wI~
//      currentMapStatic=flagedKeyCodes;	                       //~vc1wI~//~vc1yR~
        if (Dump.Y)Dump.println("AxeLstKbdLayout:savePreference swChanged="+swChanged);//~vc1wI~//~vc21R~
        if (Dump.Y)Dump.println("AxeLstKbdLayout:savePreference flagedKeyCodesStatic="+flagedKeyCodesStatic);//~vc1yI~//~vc21R~
        if (Dump.Y)Dump.println("AxeLstKbdLayout:savePreference flagedKeyCodes="+flagedKeyCodes);//~vc1yI~//~vc21R~
        if (Dump.Y)Dump.println("AxeLstKbdLayout:savePreference flagedKeyCodesStatic="+Utils.toHexString(flagedKeyCodesStatic));//~vc1yI~//~vc21R~
        if (Dump.Y)Dump.println("AxeLstKbdLayout:savePreference flagedKeyCodes="+Utils.toHexString(flagedKeyCodes));//~vc1yI~//~vc21R~
    }                                                              //~vc1wI~
//************************************************                 //~1920R~
//*from AxeKbdDialog;apply preference to degfault layout           //~vc1yR~
//************************************************                 //~1920I~
    public static int[][] loadPreference(int[][] PflagedKeyCodeTbl)//~1923R~
    {                                                              //~1809I~
        String key,value;                                          //~1809I~
        int sz;                                           //~1920R~
    //**************
        if (Dump.Y)Dump.println("AxeLstKbdLayout:loadPreference flagedKeyCode="+Utils.toHexString(PflagedKeyCodeTbl));//~vc1wI~
    //	saveDefault(PflagedKeyCodeTbl);	//save xml data            //~1A11R~
        sz=PflagedKeyCodeTbl.length;                               //~1923R~
    	value=AxeProp.getPreference(PREFKEY,null);                 //~1920R~
        if (value==null)                                           //~1920R~
        	return null;	//no data                              //~1920R~
        existPrefData=true;                                        //~1923I~
    	int[][] flagedkeycodes=new int[sz][AxeKbdView.MAXSWIPE+1]; //~1923R~
    	for (int ii=0;ii<sz;ii++)                             //~1919R~
        {                                                          //~1809I~
        	for (int jj=0;jj<=AxeKbdView.MAXSWIPE;jj++)                           //~1919I~
            {                                                      //~1919I~
//              flagedkeycodes[ii][jj]=-1;                         //~1923R~//~vc1wR~
                flagedkeycodes[ii][jj]=PflagedKeyCodeTbl[ii][jj];  //~vc1wI~
                key=Integer.toHexString((ii<<8)+jj);               //~1919I~
                value=AxeProp.getPreference(PREFKEY+key,null);     //~1919R~
                if (value==null)                                   //~1919R~
                    continue;                                      //~1919R~
                int newkey=Utils.hexstrToNum(value,KeyData.NOT_DEFINED);//~1919R~
                if (Dump.Y)Dump.println("AxeLstKbdLayout:loadPreference ii="+ii+",jj="+jj+",old="+flagedkeycodes[ii][jj]+",new="+Integer.toHexString(newkey));//~1919I~//~vc1yI~
                flagedkeycodes[ii][jj]=newkey;                            //+1919R~//~1923R~
            }                                                      //~1919I~
        }                                                          //~1809I~
        if (Dump.Y)Dump.println("AxeLstKbdLayout:loadPreference exit flagedKeyCode="+Utils.toHexString(flagedkeycodes));//~vc1yI~
    	return flagedkeycodes;                                     //~1923R~
    }                                                              //~1809I~
////************************************************                 //~1923I~//~vc1wR~
////*from softKbd;load saved updata date                             //~1923I~//~vc1wR~
////************************************************                 //~1923I~//~vc1wR~
//    public static void clearPreference(int[][] Pcodetbl)           //~1923I~//~vc1wR~
//    {                                                              //~1923I~//~vc1wR~
//        String key;                                          //~1923I~//~vc1wR~
//        int sz;                                                    //~1923I~//~vc1wR~
//    //**************                                               //~1923I~//~vc1wR~
//        if (!existPrefData)                                        //~1923I~//~vc1wR~
//            return;                                                //~1923I~//~vc1wR~
//        sz=Pcodetbl.length;                                        //~1923R~//~vc1wR~
//        for (int ii=0;ii<sz;ii++)                                  //~1923I~//~vc1wR~
//        {                                                          //~1923I~//~vc1wR~
//            for (int jj=0;jj<=AxeKbdView.MAXSWIPE;jj++)            //~1923I~//~vc1wR~
//            {                                                      //~1923I~//~vc1wR~
//                key=Integer.toHexString((ii<<8)+jj);               //~1923I~//~vc1wR~
//                if (AxeProp.getPreference(PREFKEY+key,null)!=null) //~1923I~//~vc1wR~
//                    AxeProp.putPreference(PREFKEY+key,null);       //~1923I~//~vc1wR~
//            }                                                      //~1923I~//~vc1wR~
//        }                                                          //~1923I~//~vc1wR~
//        AxeProp.putPreference(PREFKEY,null);                 //~1923I~//~vc1wR~
//    }                                                              //~1923I~//~vc1wR~
//**********************************************************************//~1810I~
//*value is flagedKeyCodes                                         //~1923I~
//**********************************************************************//~1923I~
//  public void savePreference(int Pkey,int Pswipedest,int Pvalue) //~1919R~//~vc1wR~
    private void savePreference(int Pkey,int Pswipedest,int Pvalue)//~vc1wI~
    {                                                              //~1809I~
    	int key=(Pkey<<8)+Pswipedest;                              //~1919I~
    	AxeG.axeKey.savePreference_KeyValue(PREFKEY,key,Pvalue);   //~1919R~
    }                                                              //~1809I~
////*************                                                    //~1809I~//~vc1wR~
//    public void onSpinnerItemSelected(int Ppos)                                //~1809I~//~vc1wR~
//    {                                                              //~1809I~//~vc1wR~
//        String keyname;                                            //~1810I~//~vc1wR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayout onSpinnerItemSelected spinner pos="+Ppos);//~1919R~//~vc1wR~
//        if (selectedView2==null)                                 //~1809I~//~1816R~//~vc1wR~
//            return;                                                //~1810I~//~vc1wR~
//        spinnerPos=Ppos;                                       //~1809I~//~1810R~//~vc1wR~
//        if (spinnerPos>0)                                          //~1810R~//~vc1wR~
//            keyname=AxeKbdKey.SspinnerData[spinnerPos];                     //~1813R~//~vc1wR~
//        else                                                       //~1810R~//~vc1wR~
//            keyname="";                                            //~1810R~//~vc1wR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayout set edittext spinnerpos="+spinnerPos+",keyname="+keyname);//~1919R~//~vc1wR~
//        selectedView2.setText(keyname);                            //~1816R~//~vc1wR~
////      select(csrPos);                                            //~1814R~//~vc1wR~
////      selectedView2.requestFocus();                              //~1816R~//~vc1wR~
//    }                                                              //~1809I~//~vc1wR~
////*************                                                    //~1813I~//~vc1wR~
//    public void setSpinnerPrompt(int Ppos)                         //~1813I~//~vc1wR~
//    {                                                              //~1813I~//~vc1wR~
//        String prompt;                                             //~1813I~//~vc1wR~
//    //****************                                             //~1813I~//~vc1wR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayout setSpinnerPrompt pos="+Ppos);//~1919R~//~vc1wR~
//        if (Ppos<0)                                                //~1814R~//~vc1wR~
//        {                                                          //~1813I~//~vc1wR~
//            prompt=Utils.getResourceString(R.string.Prompt_KbdLayout_Not_Selected);//~1919R~//~vc1wR~
//        }                                                          //~1813I~//~vc1wR~
//        else                                                       //~1813I~//~vc1wR~
//        {                                                          //~1813I~//~vc1wR~
//            String linelabel=getSpinnerTitle(Ppos);               //~1920R~//~vc1wR~
//            prompt=Utils.getResourceString(R.string.Prompt_KbdLayout)+linelabel;//~1919R~//~vc1wR~
//        }                                                          //~1813I~//~vc1wR~
//        spinner.setPrompt(prompt);                                 //~1813I~//~vc1wR~
//    }                                                              //~1813I~//~vc1wR~
//*************                                                    //~1919R~
//    @Override                                                      //~1919R~//~vc1wR~
//    public void onFocusChanged(int Ppos,boolean Phasfocus,ListData Pdata)//~1919R~//~vc1wR~
//    {                                                              //~1919R~//~vc1wR~
//        if (Phasfocus)                                             //~1919R~//~vc1wR~
//        {                                                          //~1919R~//~vc1wR~
//            selectedView2=Pdata.textView2;                                   //~1919R~//~vc1wR~
//            csrPos=Ppos;                                           //~1919R~//~vc1wR~
//            setSpinnerPrompt(csrPos);                              //~1919R~//~vc1wR~
//        }                                                          //~1919R~//~vc1wR~
//    }                                                              //~1919R~//~vc1wR~
//*************                                                    //~1923I~
//    private static void saveDefault(int[][] PflagedKeyCodes)        //~1923R~//~vc1wR~
//    {                                                              //~1923I~//~vc1wR~
//        int keyctr=PflagedKeyCodes.length;                         //~1923R~//~vc1wR~
//        int[][] outtb=new int[keyctr][AxeKbdView.MAXSWIPE+1];      //~1923I~//~vc1wR~
//        Utils.copyArray(PflagedKeyCodes,outtb);                   //~1923I~//~vc1wR~
//        flagedKeyCodesDefault=outtb;                               //~1923R~//~vc1wR~
//        if (Dump.Y)Dump.println("AxeLstKbdLayout:saveDefault flagedKeyCodesDefault="+Utils.toHexString(flagedKeyCodesDefault));//~vc1wR~
//    }                                                              //~1923I~//~vc1wR~
//*************                                                    //~1923I~
//    public void resetToDefault()                                   //~1923I~//~vc1wR~
//    {                                                              //~1923I~//~vc1wR~
////         flagedKeyCodes=initKeynameTbl(flagedKeyCodesDefault); //inedx,label tbl//~1923R~//~vc1wR~
////        int sz=keyIndexMap.length;                                 //~1923I~//~vc1wR~
////        int[] adaptordata=new int[sz];                             //~1923R~//~vc1wR~
////        for (int ii=0;ii<sz;ii++)                                  //~1923I~//~vc1wR~
////        {                                                          //~1923I~//~vc1wR~
////            int mapidx=keyIndexMap[ii];                            //~1923I~//~vc1wR~
////            int row=mapidx>>8;                                     //~1923I~//~vc1wR~
////            int col=mapidx & 0xff;                                 //~1923I~//~vc1wR~
////            adaptordata[ii]=flagedKeyCodes[row][col];                    //~1923R~//~vc1wR~
////        }                                                          //~1923I~//~vc1wR~
////        updateData(adaptordata);                                   //~1923R~//~vc1wR~
//        super.resetToDefault();                                  //~vc1wR~
//        reset=true; //remove preference                            //~1923I~//~vc1wR~
//    }                                                              //~1923I~//~vc1wR~
    //***************************************************          //~vc1wI~
    //*from AxeDlgKbdLayoutHW                                      //~vc1wI~
    //***************************************************          //~vc1wI~
    @Override                                                      //~vc21I~
    protected void resetToDefault()                                //~vc1wI~
    {                                                              //~vc1wI~
		if (Dump.Y) Dump.println("AxeListKbdLayout resetToDefault");//~vc1wR~
//      getListViewData(defaultMap);	//make ListViewData        //~vc1wI~
    	AxeKbdDialog axeKbdDialog=AxeG.axeIME.getXeKbd();          //~vc1yI~
        int[][] defaultCT=axeKbdDialog.getKbdLayoutCodeTblDefault(); //~vc1yI~
        int[][] saveOld=flagedKeyCodes;		//keep current map applyed preference//~vc21I~
		flagedKeyCodes=initKeynameTbl(defaultCT);//default applyed;//~vc21R~
        getListViewData(defaultCT);                  //~vc1wI~     //~vc1yR~
        isUpdateAll=true;                                          //~vc1wI~
    	clearList();                                               //~vc1wI~
		setListViewData(this);	//fill arraylist for KbdLayout     //~vc1wI~
    	resetAdapter(); //notifyDataChanged                        //~vc1wI~
		flagedKeyCodes=saveOld;                                    //~vc21I~
    }                                                              //~vc1wI~
	//**********************************************************************//~vc1wI~
    @Override                                                      //~vc1wI~
    protected View getViewEach(AxeList.ListArrayAdapter Padapter, int Ppos, View Pview, ViewGroup Pparent)//~vc1wR~
    {                                                              //~vc1wI~
        View /*heldview,*/convertview;                             //~vc1wI~
        ListViewTag tag;                                           //~vc1wI~
        TextView v1,v2,v3,v4;                                      //~vc1wI~
        TextView v5,v6;                                            //~vc1wR~
    //*******************                                          //~vc1wI~
        if (Dump.Y) Dump.println("AxeListKbdLayout.getviewEach Ppos="+Ppos+",view="+Pview);//~vc1wR~
        convertview=Pview;                                         //~vc1wI~
            if (convertview==null)      //shown 1st time           //~vc1wI~
            {                                                      //~vc1wI~
                convertview=AxeG.inflater.inflate(ROWID,Pparent,false/*attachToRoot*/);//~vc1wI~
			    if (Dump.Y) Dump.println("AxeListKbdLayout.getview converview inflated="+convertview.toString());//~vc1wR~
//                v1=(TextView)(convertview.findViewById(COLID_NORMAL));//~vc1wR~
//                v2=(TextView)(convertview.findViewById(COLID_SHIFT));//~vc1wR~
//                v3=(TextView)(convertview.findViewById(COLID_ALTGR));//~vc1wR~
//                v4=(TextView)(convertview.findViewById(COLID_SHIFTALTGR));//~vc1wR~
                v1=(TextView)(convertview.findViewById(COLID_ROWCOL));//~vc1wR~
                v2=(TextView)(convertview.findViewById(COLID_NORMAL));//~vc1wI~
                v3=(TextView)(convertview.findViewById(COLID_SHIFT));//~vc1wR~
                v4=(TextView)(convertview.findViewById(COLID_SYMBOL));//~vc1wR~
                v5=(TextView)(convertview.findViewById(COLID_ALTGR));//~vc1wR~
                v6=(TextView)(convertview.findViewById(COLID_SHIFTALTGR));//~vc1wR~
//              tag=new ListViewTag(v1,v2,v3,v4,0,0);              //~vc1wI~
                tag=new ListViewTag(v1,v2,v3,v4,v5,v6,0,0);        //~vc1wR~
                convertview.setTag(tag);                           //~vc1wI~
            }                                                      //~vc1wI~
            else                                                   //~vc1wI~
            {                                                      //~vc1wI~
                tag=(ListViewTag)convertview.getTag();             //~vc1wI~
                v1=tag.view1;                                      //~vc1wI~
                v2=tag.view2;                                      //~vc1wI~
                v3=tag.view3;                                      //~vc1wI~
                v4=tag.view4;                                      //~vc1wI~
                v5=tag.view5;                                      //~vc1wI~
                v6=tag.view6;                                      //~vc1wI~
            }                                                      //~vc1wI~
//          setLineView(convertview,Ppos,v1,v2,v3,v4);             //~vc1wI~
            setLineView(convertview,Ppos,v1,v2,v3,v4,v5,v6);       //~vc1wR~
        return convertview;                                        //~vc1wI~
    }                                                              //~vc1wI~
    //************************************************************************************//~vc1wI~
//  private void setLineView(View Pconvertview,int Ppos,TextView Pview1,TextView Pview2,TextView Pview3,TextView Pview4)//~vc1wI~
    private void setLineView(View Pconvertview,int Ppos,TextView Pview1,TextView Pview2,TextView Pview3,TextView Pview4,TextView Pview5,TextView Pview6)//~vc1wR~
    {                                                              //~vc1wI~
        int bg,fg;                                                 //~vc1wI~
        ListData data;                                             //~vc1wI~
        int key;                                                   //~vc1wI~
        TextView v1,v2,v3,v4;                                      //~vc1wI~
        TextView v5,v6;                                            //~vc1wR~
        ListViewTag tag1,tag2,tag3,tag4;                           //~vc1wR~
        ListViewTag tag5,tag6;                                     //~vc1wR~
    //*******************                                          //~vc1wI~
        v1=Pview1;                                                 //~vc1wI~
        v2=Pview2;                                                 //~vc1wI~
        v3=Pview3;                                                 //~vc1wI~
        v4=Pview4;                                                 //~vc1wI~
        v5=Pview5;                                                 //~vc1wI~
        v6=Pview6;                                                 //~vc1wI~
//        tag1=new ListViewTag(v1,v2,v3,v4,Ppos,0);                //~vc1wI~
//        tag2=new ListViewTag(v1,v2,v3,v4,Ppos,1);                //~vc1wI~
//        tag3=new ListViewTag(v1,v2,v3,v4,Ppos,2);                //~vc1wI~
//        tag4=new ListViewTag(v1,v2,v3,v4,Ppos,3);                //~vc1wI~
        tag1=new ListViewTag(v1,v2,v3,v4,v5,v6,Ppos,0);               //~vc1wI~//~vc21R~
        tag2=new ListViewTag(v1,v2,v3,v4,v5,v6,Ppos,1);               //~vc1wI~//~vc21R~
        tag3=new ListViewTag(v1,v2,v3,v4,v5,v6,Ppos,2);               //~vc1wI~//~vc21R~
        tag4=new ListViewTag(v1,v2,v3,v4,v5,v6,Ppos,3);               //~vc1wI~//~vc21R~
        tag5=new ListViewTag(v1,v2,v3,v4,v5,v6,Ppos,4);               //~vc1wI~//~vc21R~
        tag6=new ListViewTag(v1,v2,v3,v4,v5,v6,Ppos,5);               //~vc1wI~//~vc21R~
        v1.setTag(tag1);    //for FocusChangeListener              //~vc1wI~
        v2.setTag(tag2);    //for FocusChangeListener              //~vc1wI~
        v3.setTag(tag3);    //for FocusChangeListener              //~vc1wI~
        v4.setTag(tag4);    //for FocusChangeListener              //~vc1wI~
        v5.setTag(tag5);    //for FocusChangeListener              //~vc1wI~
        v6.setTag(tag6);    //for FocusChangeListener              //~vc1wI~
        data=arrayData.get(Ppos);                                  //~vc1wI~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.setLineView pos="+Ppos+",data="+data.toString());//~vc1wR~
        key=data.key1;                                             //~vc1wI~
        int col=data.col;                                          //~vc1wI~
        int colerr=data.colerr;                                    //~vc1wI~
        int posCursor=data.posCursor;                              //~vc1wI~
        String s1=data.str1;                                       //~vc1wI~
        String s2=data.str2;                                       //~vc1wI~
        String s3=data.str3;                                       //~vc1wI~
        String s4=data.str4;                                       //~vc1wI~
        String s5=data.str5;                                       //~vc1wI~
        String s6=data.str6;                                       //~vc1wI~
        v1.setText(s1);                                            //~vc1wI~
        v2.setText(s2);                                            //~vc1wI~
        v3.setText(s3);                                            //~vc1wI~
        v4.setText(s4);                                            //~vc1wI~
        v5.setText(s5);                                            //~vc1wI~
        v6.setText(s6);                                            //~vc1wI~
//      if (Dump.Y) Dump.println("AxeLstKbdLayout.setLineView settext csrPos="+cursorpos+",pos="+Ppos+",key="+Integer.toHexString(key)+",col="+col+",colerr="+colerr+",str="+s1+","+s2+","+s3+","+s4);//~vc1wR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayout.setLineView v1="+v1.getText()+"="+v1.toString());//~vc1wR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayout.setLineView v2="+v2.getText()+"="+v2.toString());//~vc1wR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayout.setLineView v3="+v3.getText()+"="+v3.toString());//~vc1wR~
//        if (Dump.Y) Dump.println("AxeLstKbdLayout.setLineView v4="+v4.getText()+"="+v4.toString());//~vc1wR~
        data.textView1=v1;                                         //~vc1wI~
        data.textView2=v2;  //for getText                          //~vc1wI~
        data.textView3=v3;  //for getText                          //~vc1wI~
        data.textView4=v4;  //for getText                          //~vc1wR~
        data.textView5=v5;  //for getText                          //~vc1wI~
        data.textView6=v6;  //for getText                          //~vc1wI~
        addClickListenerV1(v2);                                    //~vc1wI~
        addClickListenerV1(v3);                                    //~vc1wI~
        addClickListenerV1(v4);                                    //~vc1wI~
        addClickListenerV1(v5);                                    //~vc1wI~
        addClickListenerV1(v6);                                    //~vc1wI~
        if (fldProtected(key,1/*col*/))                            //~vc1wI~
        	bg=COLOR_BG_DISABLE;                                   //~vc1wI~
        else                                                       //~vc1wI~
        if (Ppos==cursorpos)                                       //~vc1wI~
        	if (col==1)                                            //~vc1wI~
        		bg=COLOR_BGFOCUS;                                  //~vc1wI~
            else                                                   //~vc1wI~
        		bg=COLOR_BG;                                       //~vc1wI~
        else                                                       //~vc1wI~
            bg=COLOR_BG;                                           //~vc1wI~
        v2.setBackgroundColor(bg);                                 //~vc1wI~
//      if (Dump.Y) Dump.println("AxeLstKbdLayout.setLineView settext pos="+Ppos+",key="+Integer.toHexString(key)+",col="+col+",colerr="+colerr+",str="+s1+","+s2+","+s3+","+s4);//~vc1wR~
        if (Ppos==cursorpos)                                       //~vc1wI~
        {                                                          //~vc1wI~
        	if (col==2)  //shift                                   //~vc1wR~
        		v3.setBackgroundColor(COLOR_BGFOCUS);              //~vc1wI~
            else                                                   //~vc1wI~
        		v3.setBackgroundColor(COLOR_BG_ENABLE3);           //~vc1wI~
        	if (col==3)  //symbol                                  //~vc1wR~
        		v4.setBackgroundColor(COLOR_BGFOCUS);              //~vc1wI~
            else                                                   //~vc1wR~
        		v4.setBackgroundColor(COLOR_BG_ENABLE4);           //~vc1wI~
        	if (col==4)  //altgr                                   //~vc1wR~
        		v5.setBackgroundColor(COLOR_BGFOCUS);              //~vc1wI~
            else                                                   //~vc1wI~
        		v5.setBackgroundColor(COLOR_BG_ENABLE5);           //~vc1wI~
        	if (col==5)  //altgr                                   //~vc1wI~
        		v6.setBackgroundColor(COLOR_BGFOCUS);              //~vc1wI~
            else                                                   //~vc1wI~
        		v6.setBackgroundColor(COLOR_BG_ENABLE6);           //~vc1wI~
        }                                                          //~vc1wI~
        else                                                       //~vc1wI~
        {                                                          //~vc1wI~
        	v2.setBackgroundColor(COLOR_BG);                       //~vc1wI~
        	v3.setBackgroundColor(COLOR_BG_ENABLE3);               //~vc1wI~
        	v4.setBackgroundColor(COLOR_BG_ENABLE4);               //~vc1wI~
        	v5.setBackgroundColor(COLOR_BG_ENABLE5);               //~vc1wI~
        	v6.setBackgroundColor(COLOR_BG_ENABLE6);               //~vc1wI~
        }                                                          //~vc1wI~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.setLineView colerr="+colerr+",key="+Integer.toHexString(key)+",col="+col+",colerr="+colerr+",str="+s1+","+s2+","+s3+","+s4);//~vc1wR~
        if (colerr!=0)                                             //~vc1wI~
        {                                                          //~vc1wI~
        	if (data.isColErr(1))                                  //~vc1wI~
	            v2.setTextColor(COLOR_FGERR);                      //~vc1wI~
            else                                                   //~vc1wI~
	            v2.setTextColor(COLOR_FG);                         //~vc1wI~
        	if (data.isColErr(2))                                  //~vc1wI~
	            v3.setTextColor(COLOR_FGERR);                      //~vc1wI~
            else                                                   //~vc1wI~
	            v3.setTextColor(COLOR_FG);                         //~vc1wI~
        	if (data.isColErr(3))                                  //~vc1wI~
	            v4.setTextColor(COLOR_FGERR);                      //~vc1wI~
            else                                                   //~vc1wI~
	            v4.setTextColor(COLOR_FG);                         //~vc1wI~
        	if (data.isColErr(4))                                  //~vc1wI~
	            v5.setTextColor(COLOR_FGERR);                      //~vc1wR~
            else                                                   //~vc1wI~
	            v5.setTextColor(COLOR_FG);                         //~vc1wR~
        	if (data.isColErr(5))                                  //~vc1wI~
	            v6.setTextColor(COLOR_FGERR);                      //~vc1wI~
            else                                                   //~vc1wI~
	            v6.setTextColor(COLOR_FG);                         //~vc1wI~
        }                                                          //~vc1wI~
        else                                                       //~vc1wI~
        {                                                          //~vc1wI~
	        v2.setTextColor(COLOR_FG);                             //~vc1wI~
	        v3.setTextColor(COLOR_FG);                             //~vc1wI~
	        v4.setTextColor(COLOR_FG);                             //~vc1wI~
	        v5.setTextColor(COLOR_FG);                             //~vc1wI~
	        v6.setTextColor(COLOR_FG);                             //~vc1wI~
        }                                                          //~vc1wI~
    }                                                              //~vc1wI~
    //**************************************************************//~vc1wI~
    //*from AxeDlgKbdLayoutHW by SoftKbd input                     //~vc1wI~
    //**************************************************************//~vc1wI~
    @Override                                                      //~vc1wI~
    protected void onEditTextChanged(String Ptext,int Pstart,int Pbefore,int Pcount)//~vc1wI~
    {                                                              //~vc1wI~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.onEditTextChanged text="+Ptext+",cursorpos="+cursorpos);//~vc1wI~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.onEditTextChanged start="+Pstart+",count="+Pcount+",before="+Pbefore);//~vc1wI~
        if (cursorpos<0 || cursorpos>=arrayData.size())            //~vc1wI~
        	return;                                                //~vc1wI~
        int pos=cursorpos;                                         //~vc1wI~
        ListData data=arrayData.get(pos);                          //~vc1wI~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.onEditTextChanged data="+data.toString());//~vc1wI~
        int col=data.col;                                          //~vc1wI~
        int key=data.key1;                                         //~vc1wI~
        if (fldProtected(key,col))                                 //~vc1wI~
        	return;                                                //~vc1wI~
        if (Ptext.length()==0 && col==1)                             //~vc21I~
        {                                                          //~vc21I~
			Utils.showToast(R.string.Err_DeleteFlatCode);          //~vc21I~
        	return;                                                //~vc21I~
        }                                                          //~vc21I~
        data.isUpdated=true;                                       //~vc1wI~
        TextView tv=null;                                          //~vc1wI~
        if (col==1)                                                //~vc1wI~
        {                                                          //~vc21I~
        	tv=data.textView2;                                     //~vc1wI~
        }                                                          //~vc21I~
        else                                                       //~vc1wI~
        if (col==2)                                                //~vc1wI~
            tv=data.textView3;                                     //~vc1wI~
        else                                                       //~vc1wI~
        if (col==3)                                                //~vc1wI~
            tv=data.textView4;                                     //~vc1wI~
        else                                                       //~vc1wI~
        if (col==4)                                                //~vc1wI~
            tv=data.textView5;                                     //~vc1wI~
        else                                                       //~vc1wI~
        if (col==5)                                                //~vc1wI~
            tv=data.textView6;                                     //~vc1wI~
        if (tv!=null)                                              //~vc1wI~
        {                                                          //~vc1wI~
        	tv.setText(Ptext);	//for chkInput                     //~vc1wI~
            data.posCursor=Pstart+Pcount;                          //~vc1wI~
        	if (chkInput(pos,tv))	//valid                        //~vc1wI~
		        listViewData[pos][col]=data.intValue;              //~vc1wR~
        	resetAdapter(); //notifyDataChanged                    //~vc1wI~
        }                                                          //~vc1wI~
    }                                                              //~vc1wI~
    //*********************************************                //~vc1wI~
    protected boolean fldProtected(int Pkey,int Pcol)              //~vc1wI~
    {                                                              //~vc1wI~
//  	boolean rc=Pcol==1 && (Pkey>='a' && Pkey<='z');            //~vc1wI~
    	boolean rc=false;    //allow change for all key             //~vc1wI~
//      if (Dump.Y) Dump.println("AxeLstKbdLayout.fldProtected key="+Integer.toHexString(Pkey)+",col="+Pcol+",rc="+rc);//~vc1wR~
        return rc;                                                 //~vc1wI~
    }                                                              //~vc1wI~
////**********************************************************************//~vc21R~
//    protected void onClickDelete()                               //~vc21R~
//    {                                                            //~vc21R~
//        if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickDelete");//~vc21R~
//        int cpos=getSelectedPos();                               //~vc21R~
//        if (cpos!=INVALID_POS)                                   //~vc21R~
//        {                                                        //~vc21R~
//            ListData data=arrayData.get(cpos);                   //~vc21R~
//            if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickDelete data="+data.toString());//~vc21R~
//            int col=data.col;     //forcused col                 //~vc21R~
//            if (col==1)                                          //~vc21R~
//            {                                                    //~vc21R~
//                Utils.showToast(R.string.Err_DeleteFlatCode);    //~vc21R~
//                return;                                          //~vc21R~
//            }                                                    //~vc21R~
//        }                                                        //~vc21R~
//        super.onClickDelete();                                   //~vc21R~
//    }//onClickDelete                                             //~vc21R~
//**********************************************************************//~vc21I~
	@Override                                                      //~vc21I~
	protected void onClickDelete()                                 //~vc21I~
    {                                                              //~vc21I~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickDelete"); //~vc21I~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickDelete forcusViewID="+Integer.toHexString(UView.getViewFocusedID((Dialog)axeDialog)));//~vc21I~
        int cpos=getSelectedPos();                                 //~vc21I~
        ListData data=null;                                        //~vc21I~
        int col=0;                                                 //~vc21I~
    	if (cpos!=INVALID_POS)                                     //~vc21I~
        {                                                          //~vc21I~
        	data=arrayData.get(cpos);                              //~vc21I~
	        if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickDelete data="+data.toString());//~vc21I~
	        col=data.col;     //forcused col                       //~vc21I~
        }                                                          //~vc21I~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickDelete cursorpos="+cpos+",col="+col);//~vc21I~
    	if (col==1)                                                //~vc21I~
        {                                                          //~vc21I~
			Utils.showToast(R.string.Err_DeleteAxeKbdFixed);       //~vc21I~
        	return;                                                //~vc21I~
        }                                                          //~vc21I~
        else                                                       //~vc21I~
    	if (col<1 || col>=maxCol)                                  //~vc21R~
        {                                                          //~vc21I~
			Utils.showToast(R.string.Err_DeleteNoItemSelected);    //~vc21I~
        	return;                                                //~vc21I~
        }                                                          //~vc21I~
        int oldkey=listViewData[cpos][colNormal+col];              //~vc21R~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickDelete data="+data.toString());//~vc21I~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickDelete oldkey="+Integer.toHexString(oldkey));//~vc21I~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickDelete data v1="+data.textView1.getText()+"="+data.textView1.toString());//~vc21I~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickDelete data v2="+data.textView2.getText()+"="+data.textView2.toString());//~vc21I~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickDelete data v3="+data.textView3.getText()+"="+data.textView3.toString());//~vc21I~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickDelete data v4="+data.textView4.getText()+"="+data.textView4.toString());//~vc21I~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickDelete data v5="+data.textView5.getText()+"="+data.textView5.toString());//~vc21I~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickDelete data v6="+data.textView6.getText()+"="+data.textView6.toString());//~vc21I~
        ListViewTag tag=(ListViewTag)data.textView1.getTag();      //~vc21I~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickDelete tag.view1="+tag.view1.getText()+"="+tag.view1.toString());//~vc21I~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickDelete tag.view2="+tag.view2.getText()+"="+tag.view2.toString());//~vc21I~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickDelete tag.view3="+tag.view3.getText()+"="+tag.view3.toString());//~vc21I~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickDelete tag.view4="+tag.view4.getText()+"="+tag.view4.toString());//~vc21I~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickDelete tag.view5="+tag.view5.getText()+"="+tag.view5.toString());//~vc21I~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickDelete tag.view6="+tag.view6.getText()+"="+tag.view6.toString());//~vc21I~
        if (oldkey==0)                                             //~vc21I~
        	return;                                                //~vc21I~
        int kc=listViewData[cpos][0];                              //~vc21I~
//      int uc=defaultMap[kc][0];                                  //~vc21I~
//      if (col==1 && uc!=0) //Shift column,default!=0             //~vc21I~
//      {                                                          //~vc21I~
//        String defChar=Character.valueOf((char)uc).toString();   //~vc21I~
//        data.updateText(defChar);                                //~vc21I~
//        listViewData[cpos][colNormal+col]=uc;                    //~vc21I~
//        axeDialog.onClickListItem(cpos,col,defChar);             //~vc21I~
//      }                                                          //~vc21I~
//      else                                                       //~vc21I~
//      {                                                          //~vc21I~
		data.updateText("");                                       //~vc21I~
        listViewData[cpos][colNormal+col]=0;                       //~vc21R~
        axeDialog.clearET();                                       //~vc21I~
//      }                                                          //~vc21I~
    	resetAdapter(); //notifyDataChanged                        //~vc21I~
        data.isUpdated=true;                                       //~vc21I~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.onClickDelete cpos="+cpos+",listViewData="+ Arrays.toString(listViewData[cpos]));//~vc21I~
    }//onClickDelete                                               //~vc21I~
//**********************************************************************//~vc2vI~
	public static int getBasecodeOfShifted(int Punicode)           //~vc2vI~
    {                                                              //~vc2vI~
    	int out=Punicode;                                          //~vc2vI~
        int[][] tb=getFlagedKeyCodesStatic();                        //+vc2vR~
        int mapSize=tb.length;                                     //~vc2vR~
        for (int ii=0;ii<mapSize;ii++)                             //~vc2vI~
        {                                                          //~vc2vI~
        	if (Punicode==tb[ii][1])                               //~vc2vI~
            {                                                      //~vc2vI~
            	out=tb[ii][0];                                     //~vc2vI~
                break;                                             //~vc2vI~
            }                                                      //~vc2vI~
        }                                                          //~vc2vI~
        if (Dump.Y) Dump.println("AxeLstKbdLayout.getBasecodeOfShifted in="+Integer.toHexString(Punicode)+",out="+Integer.toHexString(out));//~vc2vI~
        return out;                                                //~vc2vI~
    }                                                              //~vc2vI~
}//class                                                           //~1528I~
