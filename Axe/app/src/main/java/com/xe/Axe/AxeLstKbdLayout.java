//*CID://+vaasR~: update#= 375;                                    //~vaasR~
//**********************************************************************//~1528I~
//vaas:120102 (Axe) kbd position change;move SYMs to swipe-right(top-right)//~vaasI~
//**********************************************************************//~vaasI~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1109R~//~1528I~

import com.xe.Axe.kbd.AxeKbdDialog;
import com.xe.Axe.kbd.AxeKbdKey;
import com.xe.Axe.kbd.AxeKbdView;
import com.xe.Axe.kbd.AxeSoftKbd;

import android.view.ViewGroup;
import android.widget.TextView;
                                                                   //~1528I~
                                                                   //~1528I~
public class AxeLstKbdLayout extends AxeList                        //~1612R~//~1919R~
{                                                                  //~1528I~
	private static final String PREFKEY="KB";                      //~1919R~
//	private static final String PREFKEYSUM="KBSUM";                //~1923I~
	private static final int ROWID =R.layout.listrowkbdlayout;//~1529I~//~1919M~
                                                                   //~1809I~
	public static final String[] swipedest={                        //~1919I~
    										"Base",                 //~1919I~
//  										"Shift+",               //~1919I~//~vaasR~
    										"L:Shift",             //+vaasR~
                        					"R:Symbol",            //+vaasR~
//                     						"AltGr+",              //~1919I~//+vaasR~
                       						"U:AltGr",             //+vaasR~
//                      					"S+AltGr+",            //~1919I~//~vaasR~
                        					"D:S+AltGr",           //+vaasR~
//                      					"Flick-Down",          //~1A11R~//~vaasR~
    								};
	public final static String[] tgtkeynametbl=AxeKbdKey.SspinnerData;//~1919I~
//    private static String[] keyNames;                            //~1920R~
    private static int[][] flagedKeyCodes;	//extkey is flaged index value//~1923R~
    private static int[][] flagedKeyCodesDefault;                  //~1923R~
    private static int[] keyIndexMap;	//list index --> keyindex+swipeindex//~1920R~
    private static String[] keyLabelTbl;                              //~1920I~
    private static int kbdSize;                                    //~1920R~
    private static int mapSize;                                    //~1920I~
    private static boolean existPrefData;                                  //~1923I~
                                                                   //~1809I~
    private int rowSize=10;
                                                                   //~1529I~
	private TextView selectedView2;                                //~1816R~
	private AxeSpinner spinner;                                    //~1816R~
	private int spinnerPos,csrPos;                                 //~1814R~
    private boolean reset;                                         //~1923I~
                                                                   //~1813I~
//*****************                                                //~1813I~
    public AxeLstKbdLayout(int PdialogId,ViewGroup PlayoutView,int Pmenuid,int Prowid)                                               //~1112I~//~1612I~//~1919R~
    {                                                              //~1612I~
		super(PdialogId,PlayoutView,Pmenuid,Prowid,true/*editableRow*/,true/*clickable*/);//~1919R~//~1B02R~
    }                                                              //~1612I~
//*****************                                                //~1528I~
    public static AxeLstKbdLayout setupListView(AxeSpinner Pspinner,int PdialogId,ViewGroup PlayoutView)//~1612R~//~1919R~
    {                                                              //~1528I~
    	AxeLstKbdLayout al=new AxeLstKbdLayout(PdialogId,PlayoutView,0/*Shift menuId*/,ROWID);//~1612R~//~1919R~
        al.spinner=Pspinner;                                       //~1814R~
        return al;                                                 //~1528I~
    }                                                              //~1528I~
//*****************                                                //~1612I~
    @Override                                                      //~1612I~
    public void setListViewData()	//fromAxeList constructor      //~1920R~
    {                                                              //~1612I~
        getKbdLayout();                                            //~1920M~
		setListViewData(this);	//fill arraylist for KbdLayout//~1612R~//~1919R~
    }                                                              //~1612I~
    //*****************************                                //~1809R~
    public void add(int Psource,int Ptarget)                       //~1613R~
    {                                                              //~1612I~
    	ListData linedata=new ListData(Psource,Ptarget);           //~1612I~
        if (Dump.Y) Dump.println("AxeList addKbdLayout src="+Integer.toHexString(Psource)+",tgt="+Integer.toHexString(Ptarget));//~1612I~//~1919R~
    	super.add(linedata);                                       //~1612I~
    }                                                              //~1612I~
	//******************                                           //~1809I~
    @Override                                                      //~1809I~
    public void onItemSelected(int Ppos,ListData Pitem)             //~1816R~//~1824R~
    {                                                              //~1809I~
        if (Dump.Y)Dump.println("AxeLstKbdLayout onItemSelected pos="+Ppos);//~1816R~//~1919R~
    	selectedView2=(Pitem.textView2);                           //~1816R~
        csrPos=Ppos;                                               //~1810I~
        setSpinnerPrompt(csrPos);                                  //~1814I~
    }                                                              //~1809I~
	//******************                                           //~1824I~
    @Override                                                      //~1824I~
    public void onItemClicked(int Ppos,ListData Pitem)             //~1824I~
    {                                                              //~1824I~
        if (Dump.Y)Dump.println("AxeLstKbdLayout onItemClicked pos="+Ppos);//~1919R~
    	selectedView2=(Pitem.textView2);                           //~1824I~
        csrPos=Ppos;                                               //~1824I~
        setSpinnerPrompt(csrPos);                                  //~1824I~
        notifyDataChanged();	//super                            //~1824R~
    }                                                              //~1824I~
	//******************                                               //~1612I~//~1809R~
    @Override                                                      //~1612I~
    public void updateTbl(int[] Pnewtbl)                           //~1612I~
    {                                                              //~1612I~
    	if (reset)                                                 //~1923I~
        	clearPreference(flagedKeyCodes);                       //~1923R~
    	updateKbdLayout(Pnewtbl);                       //~1612R~  //~1919R~
    }                                                              //~1612I~
//************************************                             //~1923R~
//*adaptor data is flagedKeyCode                                   //~1923I~
//************************************                             //~1923I~
    @Override                                                      //~1612I~
    public int chkInputValue(int Ppos,String Pvalue,boolean Ptoast)         //~1613I~//~1A29R~
    {                                                              //~1612I~
    	int intval;                                                //~1920I~
    	int idx=AxeKbdKey.getSpinnerIndex(Pvalue);                 //~1920I~
        if (idx>0)                                                 //~1920I~
	    	intval=idx|AxeSoftKbd.KEYID_EXTKEY;                    //~1920I~
        else                                                       //~1920I~
        {                                                          //~1920I~
	    	intval=AxeKey.chkInputCharCode(Pvalue); //KEYVALUE_ERR if hex numeric err//~1920I~
        }                                                          //~1920I~
        if (Dump.Y)Dump.println("AxeLstKbdLayout chkInputValue Str="+Pvalue+",int="+Integer.toHexString(intval));//~1919I~
 		return	intval;                                            //~1919R~
    }                                                              //~1612I~
//******************                                               //~1531I~
	@Override                                                      //~1612I~
    public String getKeyname(int Ppos,int Pfldid,int Pkey,String Perr)//~1602R~//~1A29R~
    {                                                              //~1531I~                                  //~1920I~
        String label;                                              //~1920I~
        if (Pfldid==1)    	//left field                                         //~1612R~//~1809R~
        {                                                          //~1920I~
        	if (Pkey>=mapSize)                                     //~1920I~
            	return "";                                         //~1920I~
        	int mapidx=keyIndexMap[Pkey];                          //~1920I~
        	int row=mapidx>>8;                                     //~1920R~
        	int col=mapidx & 0xff;                                   //~1920R~
            if (col==0)                                            //~1920I~
            {                                                      //~1920I~
		        label=keyLabelTbl[row];                            //~1920I~
        		if (label==null)                                   //~1920I~
                	label="";                                      //~1920I~
			    label=String.format("%d-%d:",row/rowSize,row%rowSize)+label;//~1920I~
            }                                                      //~1920I~
            else                                                   //~1920I~
            {                                                      //~1920I~
//          	label="   :"+swipedest[col];                      //~1920R~//+vaasR~
            	label="  "+swipedest[col];                         //+vaasI~
            }                                                      //~1920I~
        }                                                          //~1920I~
        else                                                       //~1920I~
        {                                                          //~1920I~
            label=codeToName(Pkey);                                //~1920R~
        }                                                          //~1920I~
        return label;                                              //~1920I~
    }                                                              //~1531I~
    //**************                                               //~1920I~
    public String getSpinnerTitle(int Ppos)                        //~1920I~
    {                                                              //~1920I~
        String label;                                              //~1920I~
        int mapidx=keyIndexMap[Ppos];                              //~1920I~
        int row=mapidx>>8;                                         //~1920I~
        int col=mapidx & 0xff;                                     //~1920I~
        label=keyLabelTbl[row];                             //~1920I~
        if (label==null)                                           //~1920I~
	    	label=String.format("%d-%d",row/rowSize,row%rowSize);  //~1920I~
	    label+=":"+swipedest[col];                                 //~1920I~
        return label;                                              //~1920I~
    }                                                              //~1920I~
//*************                                                    //~1919I~
    public void setListViewData(AxeLstKbdLayout Plist)             //~1919I~
    {                                                              //~1919I~
    	for (int ii=0;ii<mapSize;ii++)                             //~1920R~
        {                                                          //~1809I~
        	int mapidx=keyIndexMap[ii];                            //~1920I~
            int row=mapidx>>8;                                     //~1920I~
            int col=mapidx & 0xff;                                 //~1920I~
	        int codeidx=flagedKeyCodes[row][col];                  //~1923R~
	    	Plist.add(ii,codeidx);                                //~1920R~
        }                                                          //~1809I~
    }                                                              //~1809I~
//*************                                                    //~1809I~
	public void getKbdLayout()                                     //~1919R~
    {                                                              //~1809I~
//        if (keyIndexMap!=null)                                   //~1923R~
//            return;                                              //~1923R~
    	AxeKbdDialog axeKbdDialog=AxeG.axeIME.getXeKbd();           //~1919I~
        int[][] flagedkeycodes=axeKbdDialog.getKbdLayoutCodeTbl(); //~1923R~
        kbdSize=flagedkeycodes.length;                             //~1923R~
		flagedKeyCodes=initKeynameTbl(flagedkeycodes);//codetbl for update//~1923R~
    }                                                              //~1919I~
//*************************************************************    //~1923R~
//*copy flagedCodeTbl for temporary update                         //~1923I~
//*create indexing adaptor list to flagedCodeTbl                   //~1923I~
//*************************************************************    //~1923I~
	public int[][] initKeynameTbl(int [][] Pflagedkeycodes)        //~1923R~
    {                                                              //~1919I~
        int[][] flagedkeycodes=new int[kbdSize][AxeKbdView.MAXSWIPE+1];//~1923R~
        int sz=kbdSize*(AxeKbdView.MAXSWIPE+1);                    //~1920M~
        keyIndexMap=new int[sz];                                   //~1920R~
        keyLabelTbl=new String[kbdSize];                           //~1920I~
        int mapidx=0;                                              //~1920I~
        for (int ii=0;ii<kbdSize;ii++)                                 //~1919I~
        {                                                          //~1919I~
        	int code=Pflagedkeycodes[ii][0];                       //~1923R~
        	flagedkeycodes[ii][0]=code;                            //~1923R~
			keyLabelTbl[ii]=null;                                  //~1920I~
            if (code==AxeSoftKbd.KEYID_FIXED)                      //~1919I~
            	continue;                                          //~1919I~
			keyLabelTbl[ii]=codeToName(code);                      //~1920I~
            for (int jj=0;jj<=AxeKbdView.MAXSWIPE;jj++)            //~1920R~
            {                                                      //~1919I~
                keyIndexMap[mapidx++]=(ii<<8)+jj;                  //~1920I~
        		flagedkeycodes[ii][jj]=Pflagedkeycodes[ii][jj];          //~1923R~
            }                                                      //~1919I~
        }                                                          //~1919I~
        mapSize=mapidx;                                            //~1920I~
        return flagedkeycodes;                                     //~1923R~
    }                                                              //~1919I~
//********************************************************         //~1923R~
//*falgedKeyCode(adaptor data) to keyname                          //~1923I~
//********************************************************         //~1923I~
	private String codeToName(int Pcode)                           //~1919I~
    {                                                              //~1919I~
    	String name;                                               //~1919I~
        if ((Pcode & AxeSoftKbd.KEYID_EXTKEY)!=0)                       //~1919I~
            name=AxeKbdKey.SspinnerData[Pcode & ~AxeSoftKbd.KEYID_EXTKEY];//~1919I~
        else                                                       //~1919I~
        if (Pcode==0)                                              //~1919I~
            name="";                                               //~1919I~
        else                                                       //~1919I~
        if (Pcode<0x80)                                            //~1919I~
            name=String.valueOf((char)Pcode);                      //~1919I~
        else                                                       //~1919I~
			name=String.format("%04x (%s)",Pcode,String.valueOf((char)Pcode));//~1920I~
        if (Dump.Y) Dump.println("AxeLstKbdLayout code="+Integer.toHexString(Pcode)+",name="+name);//~1919I~
        return name;                                               //~1919I~
    }                                                              //~1919I~
//*****************************                                    //~1923R~
//*onClose                                                         //~1923I~
//*actually update data                                            //~1923I~
//*****************************                                    //~1923I~
	public void updateKbdLayout(int[] Pnewtbl)                     //~1919I~
    {                                                              //~1919I~
        boolean changed=false;                                     //~1920I~
        for (int ii=0;ii<mapSize;ii++)                            //~1920R~
        {                                                          //~1919I~
        	int mapidx=keyIndexMap[ii];                            //~1920R~
            int row=mapidx>>8;                                     //~1920I~
            int col=mapidx & 0xff;                                 //~1920I~
 	        int oldkeyid=flagedKeyCodes[row][col];                 //~1923R~
	        int newkeyid=Pnewtbl[ii];                              //~1920R~
            if (newkeyid>0 && newkeyid!=oldkeyid) //updated        //~1920R~
            {                                                      //~1920R~
            	changed=true;                                      //~1920I~
                flagedKeyCodes[row][col]=newkeyid;                 //~1923R~
                savePreference(row,col,newkeyid); //extkey flaged value//~1920R~
//                if (newkeyid==KeyData.NOT_DEFINED)               //~1920R~
//                    keyNames[ii]="";                             //~1920R~
//                else                                             //~1920R~
//                    keyNames[ii]=codeToName(newkeyid);           //~1920R~
            }                                                      //~1920R~
        }                                                          //~1919I~
    	if (changed)                                               //~1920I~
        {                                                          //~1920I~
	    	AxeProp.putPreference(PREFKEY,"1");   //data saved     //~1923R~
    		AxeKbdDialog axeKbdDialog=AxeG.axeIME.getXeKbd();      //~1920I~
        	axeKbdDialog.updateKbd(flagedKeyCodes);                //~1923R~
        }                                                          //~1920I~
    }                                                              //~1809I~
//************************************************                 //~1920R~
//*from AxeKbdDialog;load saved update data                        //~1A11R~
//************************************************                 //~1920I~
    public static int[][] loadPreference(int[][] PflagedKeyCodeTbl)//~1923R~
    {                                                              //~1809I~
        String key,value;                                          //~1809I~
        int sz;                                           //~1920R~
    //**************
    	saveDefault(PflagedKeyCodeTbl);	//save xml data            //~1A11R~
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
                flagedkeycodes[ii][jj]=-1;                         //~1923R~
                key=Integer.toHexString((ii<<8)+jj);               //~1919I~
                value=AxeProp.getPreference(PREFKEY+key,null);     //~1919R~
                if (value==null)                                   //~1919R~
                    continue;                                      //~1919R~
                int newkey=Utils.hexstrToNum(value,KeyData.NOT_DEFINED);//~1919R~
                flagedkeycodes[ii][jj]=newkey;                            //+1919R~//~1923R~
                if (Dump.Y)Dump.println("AxeLstKbdLayout:loadPreference ii="+ii+",jj="+jj+",new="+Integer.toHexString(newkey));//~1919I~
            }                                                      //~1919I~
        }                                                          //~1809I~
    	return flagedkeycodes;                                     //~1923R~
    }                                                              //~1809I~
//************************************************                 //~1923I~
//*from softKbd;load saved updata date                             //~1923I~
//************************************************                 //~1923I~
    public static void clearPreference(int[][] Pcodetbl)           //~1923I~
    {                                                              //~1923I~
        String key;                                          //~1923I~
        int sz;                                                    //~1923I~
    //**************                                               //~1923I~
    	if (!existPrefData)                                        //~1923I~
        	return;                                                //~1923I~
        sz=Pcodetbl.length;                                        //~1923R~
    	for (int ii=0;ii<sz;ii++)                                  //~1923I~
        {                                                          //~1923I~
        	for (int jj=0;jj<=AxeKbdView.MAXSWIPE;jj++)            //~1923I~
            {                                                      //~1923I~
                key=Integer.toHexString((ii<<8)+jj);               //~1923I~
                if (AxeProp.getPreference(PREFKEY+key,null)!=null) //~1923I~
	                AxeProp.putPreference(PREFKEY+key,null);       //~1923I~
            }                                                      //~1923I~
        }                                                          //~1923I~
    	AxeProp.putPreference(PREFKEY,null);                 //~1923I~
    }                                                              //~1923I~
//**********************************************************************//~1810I~
//*value is flagedKeyCodes                                         //~1923I~
//**********************************************************************//~1923I~
    public void savePreference(int Pkey,int Pswipedest,int Pvalue) //~1919R~
    {                                                              //~1809I~
    	int key=(Pkey<<8)+Pswipedest;                              //~1919I~
    	AxeG.axeKey.savePreference_KeyValue(PREFKEY,key,Pvalue);   //~1919R~
    }                                                              //~1809I~
//*************                                                    //~1809I~
	public void onSpinnerItemSelected(int Ppos)                                //~1809I~
    {                                                              //~1809I~
        String keyname;                                            //~1810I~
        if (Dump.Y) Dump.println("AxeLstKbdLayout onSpinnerItemSelected spinner pos="+Ppos);//~1919R~
    	if (selectedView2==null)                                 //~1809I~//~1816R~
        	return;                                                //~1810I~
        spinnerPos=Ppos;                                       //~1809I~//~1810R~
        if (spinnerPos>0)                                          //~1810R~
            keyname=AxeKbdKey.SspinnerData[spinnerPos];                     //~1813R~
        else                                                       //~1810R~
            keyname="";                                            //~1810R~
        if (Dump.Y) Dump.println("AxeLstKbdLayout set edittext spinnerpos="+spinnerPos+",keyname="+keyname);//~1919R~
        selectedView2.setText(keyname);                            //~1816R~
//      select(csrPos);                                            //~1814R~
//      selectedView2.requestFocus();                              //~1816R~
    }                                                              //~1809I~
//*************                                                    //~1813I~
	public void setSpinnerPrompt(int Ppos)                         //~1813I~
    {                                                              //~1813I~
        String prompt;                                             //~1813I~
    //****************                                             //~1813I~
        if (Dump.Y) Dump.println("AxeLstKbdLayout setSpinnerPrompt pos="+Ppos);//~1919R~
        if (Ppos<0)                                                //~1814R~
        {                                                          //~1813I~
	        prompt=Utils.getResourceString(R.string.Prompt_KbdLayout_Not_Selected);//~1919R~
        }                                                          //~1813I~
        else                                                       //~1813I~
        {                                                          //~1813I~
			String linelabel=getSpinnerTitle(Ppos);               //~1920R~
	        prompt=Utils.getResourceString(R.string.Prompt_KbdLayout)+linelabel;//~1919R~
        }                                                          //~1813I~
        spinner.setPrompt(prompt);                                 //~1813I~
    }                                                              //~1813I~
//*************                                                    //~1919R~
    @Override                                                      //~1919R~
    public void onFocusChanged(int Ppos,boolean Phasfocus,ListData Pdata)//~1919R~
    {                                                              //~1919R~
        if (Phasfocus)                                             //~1919R~
        {                                                          //~1919R~
            selectedView2=Pdata.textView2;                                   //~1919R~
            csrPos=Ppos;                                           //~1919R~
            setSpinnerPrompt(csrPos);                              //~1919R~
        }                                                          //~1919R~
    }                                                              //~1919R~
//*************                                                    //~1923I~
    public static void saveDefault(int[][] PflagedKeyCodes)        //~1923R~
    {                                                              //~1923I~
        int keyctr=PflagedKeyCodes.length;                         //~1923R~
        int[][] outtb=new int[keyctr][AxeKbdView.MAXSWIPE+1];      //~1923I~
        Utils.copyArray(PflagedKeyCodes,outtb);                   //~1923I~
        flagedKeyCodesDefault=outtb;                               //~1923R~
    }                                                              //~1923I~
//*************                                                    //~1923I~
    public void resetToDefault()                                   //~1923I~
    {                                                              //~1923I~
		 flagedKeyCodes=initKeynameTbl(flagedKeyCodesDefault); //inedx,label tbl//~1923R~
        int sz=keyIndexMap.length;                                 //~1923I~
        int[] adaptordata=new int[sz];                             //~1923R~
        for (int ii=0;ii<sz;ii++)                                  //~1923I~
        {                                                          //~1923I~
        	int mapidx=keyIndexMap[ii];                            //~1923I~
            int row=mapidx>>8;                                     //~1923I~
            int col=mapidx & 0xff;                                 //~1923I~
 	        adaptordata[ii]=flagedKeyCodes[row][col];                    //~1923R~
        }                                                          //~1923I~
        updateData(adaptordata);                                   //~1923R~
        reset=true;	//remove preference                            //~1923I~
    }                                                              //~1923I~
}//class                                                           //~1528I~
