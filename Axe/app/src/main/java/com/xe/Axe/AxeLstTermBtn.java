//*CID://+vc1cR~: update#= 372;                                    //~vc1cR~
//**********************************************************************//~1528I~
//vc1x 2020/07/08 member variable is initialized when defualt constructor defined(not non default constructor is define)//~vc1cI~
//**********************************************************************//~vc1cI~
//*ListView                                                        //~1530R~
//**********************************************************************//~1528I~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1109R~//~1528I~

import java.util.Arrays;

import android.text.InputFilter;                                   //~1B02I~
import android.text.InputType;                                     //~1B02I~
import android.view.ViewGroup;
import android.widget.TextView;
                                                                   //~1528I~
                                                                   //~1528I~
public class AxeLstTermBtn extends AxeList                        //~1612R~//~1809R~
{                                                                  //~1528I~
	private static final String PREFKEY="TB";                      //~1809I~
//	private static final String PREFKEYUSR="TBU";                  //~1A29I~
//index of nametbl(keep continuous value)                          //~1809I~//~1A25R~
	public static final int TB_SHORTCUT=  1;                      //~1809I~
	public static final int TB_CTRL=      2;                      //~1809I~
	public static final int TB_ALT =      3;                      //~1809I~
	public static final int TB_ALTG=      4;                      //~1809I~
	public static final int TB_ESC=       5;                       //~1813R~
	public static final int TB_ENTER=     6;                       //~1824I~
	public static final int TB_HOME=      7;                       //~1813R~//~1824R~
	public static final int TB_END =      8;                       //~1813R~//~1824R~
	public static final int TB_INS =      9;                       //~1813R~//~1824R~
	public static final int TB_DEL =     10;                       //~1813R~//~1824R~
	public static final int TB_F1=       11;                       //~1A25I~
	public static final int TB_F2=       12;                       //~1A25I~
	public static final int TB_F3=       13;                       //~1A25I~
	public static final int TB_F4=       14;                       //~1A25I~
	public static final int TB_F5=       15;                       //~1A25I~
	public static final int TB_F6=       16;                       //~1A25I~
	public static final int TB_F7=       17;                       //~1A25I~
	public static final int TB_F8=       18;                       //~1A25I~
	public static final int TB_F9=       19;                       //~1A25I~
	public static final int TB_F10=      20;                       //~1A25I~
	public static final int TB_F11=      21;                       //~1A25I~
	public static final int TB_F12=      22;                       //~1A25I~
	public static final int TB_KBD =     23;                       //~1831I~//~1A25R~
	public static final int TB_KBDP=     24;                       //~1831I~//~1A25R~
	public static final int TB_CLICK=    25;	//mouse lb click   //~1926I~//~1A25R~
	public static final int TB_DBLCLICK= 26;	//mouse lb dblclick//~1A04I~//~1A25R~
	public static final int TB_DRAG=     27;	//mouse lb drag    //~1A04I~//~1A25R~
	public static final int TB_RBTN=     28;	//mouse right button click//~1A04R~//~1A25R~
                                                                   //~1A29I~
                                                                  //~1A29I~
                                                           //~1809I~
	public static final int[] btnkeytbl={                                //~1813R~//~1A29R~
    			AxeKeyValue.AKC_BACK,                              //~1824I~
    			AxeKeyValue.AKC_CENTER,                            //~1809I~
    			AxeKeyValue.AKC_SEARCH,                            //~1809I~
    			AxeKeyValue.AKC_CAMERA,                            //~1809I~
    			AxeKeyValue.AKC_VOLUP,                             //~1809I~
    			AxeKeyValue.AKC_VOLDN,                             //~1809I~
    			AxeKeyValue.AKC_CALL,                              //~1809I~
//                AxeKeyValue.AKC_ENDCALL,  //ENDCALL and HOME could not be intecepted//~1A08R~
                0,0,0,0,0,0,0,0,0,             //9 user button     //~1A29I~
    								};
	public static int[] oldbtnkeytbl;                              //~1B02I~
	public static final int MAXUSERKEY =9;                         //~1A29R~
	public static final int USERBTN1=btnkeytbl.length-MAXUSERKEY;  //~1A29R~
 
	public static int[] btnnameidtbl={                             //~1813R~
    			R.string.Btnnm_BACK,                               //~1824I~
    			R.string.Btnnm_CENTER,                             //~1809I~
    			R.string.Btnnm_SEARCH,                             //~1809I~
    			R.string.Btnnm_CAMERA,                             //~1809I~
    			R.string.Btnnm_VOLUP,                              //~1809I~
    			R.string.Btnnm_VOLDN,                              //~1809I~
    			R.string.Btnnm_CALL,                               //~1809I~
//                R.string.Btnnm_ENDCALL,                          //~1A08R~
    								};                             //~1809I~
	public static int[] tgtkeyidtbl={		//assignable key tbl   //~1813R~
    					KeyData.NOT_DEFINED,                       //~1809I~
                        TB_SHORTCUT,                               //~1809I~
                        TB_CTRL,                                   //~1809I~
                        TB_ALT,                                    //~1809I~
                        TB_ALTG,                                   //~1809I~
                        TB_ESC,                                    //~1811I~
                        TB_ENTER,                                  //~1824I~
                        TB_HOME,                                   //~1811I~
                        TB_END,                                    //~1811I~
                        TB_INS,                                    //~1811I~
                        TB_DEL,                                    //~1811I~
                        TB_F1,                                     //~1A25I~
                        TB_F2,                                     //~1A25I~
                        TB_F3,                                     //~1A25I~
                        TB_F4,                                     //~1A25I~
                        TB_F5,                                     //~1A25I~
                        TB_F6,                                     //~1A25I~
                        TB_F7,                                     //~1A25I~
                        TB_F8,                                     //~1A25I~
                        TB_F9,                                     //~1A25I~
                        TB_F10,                                    //~1A25I~
                        TB_F11,                                    //~1A25I~
                        TB_F12,                                    //~1A25I~
                        TB_KBD,                                    //~1831I~
                        TB_KBDP,                                   //~1831I~
                        TB_CLICK,                                  //~1926I~
                        TB_DBLCLICK,                               //~1A04I~
                        TB_DRAG,                                   //~1A04I~
                        TB_RBTN,                                   //~1A04I~
							};                             //~1809I~
	public static int[] tgtkeytbl={//for key2key                   //~1813I~
    					KeyData.NOT_DEFINED,         //-1          //~1813I~
						TB_SHORTCUT,                 //1           //~1813I~
						TB_CTRL,                     //2           //~1813I~
						TB_ALT,                      //3           //~1813I~
						TB_ALTG,                     //4           //~1813I~
						KeyData.KEYCODE_ESCAPE,      //TB_ESC      //~1813I~
						KeyData.KEYCODE_ENTER,       //TB_ENTER    //~1824I~
						KeyData.KEYCODE_MOVE_HOME,   //TB_HOME     //~1813I~
						KeyData.KEYCODE_MOVE_END,    //TB_END      //~1831R~
						KeyData.KEYCODE_INSERT,      //TB_INS      //~1831I~
						KeyData.KEYCODE_FORWARD_DEL, //TB_DEL      //~1831M~
						KeyData.KEYCODE_F1,                        //~1A25I~
						KeyData.KEYCODE_F2,                        //~1A25I~
						KeyData.KEYCODE_F3,                        //~1A25I~
						KeyData.KEYCODE_F4,                        //~1A25I~
						KeyData.KEYCODE_F5,                        //~1A25I~
						KeyData.KEYCODE_F6,                        //~1A25I~
						KeyData.KEYCODE_F7,                        //~1A25I~
						KeyData.KEYCODE_F8,                        //~1A25I~
						KeyData.KEYCODE_F9,                        //~1A25I~
						KeyData.KEYCODE_F10,                       //~1A25I~
						KeyData.KEYCODE_F11,                       //~1A25I~
						KeyData.KEYCODE_F12,                       //~1A25I~
						TB_KBD,                      //11          //~1831M~
						TB_KBDP,                     //12          //~1831M~
						TB_CLICK,                    //13          //~1926R~
						TB_DBLCLICK,                 //14          //~1A04R~
						TB_DRAG,                     //15          //~1A04I~
						TB_RBTN,                     //16          //~1A04R~
                        };                                         //~1926R~
                                                                   //~1813I~
	public static String[] tgtkeynametbl={		//assignable key tbl//~1813R~
    					"(Not Use)",                               //~1814R~
    					"S.C(Shortcut)",                           //~1809I~
                        AxeKeyValue.KEYLBL_CTL,	//"Ctrl",                                    //~1809I~//~1A25R~
                        AxeKeyValue.KEYLBL_ALT, //"Alt",                                     //~1809I~//~1A25R~
                        AxeKeyValue.KEYLBL_ALTR, //"AltG",                                    //~1809I~//~1A25R~
                        AxeKeyValue.KEYLBL_ESCAPE, //"Esc",                                     //~1811I~//~1A25R~
                        AxeKeyValue.KEYLBL_ENTER, //"Entr",                                   //~1824I~//~1A25R~
                        AxeKeyValue.KEYLBL_MOVE_HOME, //"Home",                                    //~1811I~//~1A25R~
                        AxeKeyValue.KEYLBL_MOVE_END, //"End",                                     //~1811I~//~1A25R~
                        AxeKeyValue.KEYLBL_INSERT, //"Ins",                                     //~1811I~//~1A25R~
                        "Del(Forward)",                            //~1814R~
                        AxeKeyValue.KEYLBL_F1,                     //~1A25I~
                        AxeKeyValue.KEYLBL_F2,                     //~1A25I~
                        AxeKeyValue.KEYLBL_F3,                     //~1A25I~
                        AxeKeyValue.KEYLBL_F4,                     //~1A25I~
                        AxeKeyValue.KEYLBL_F5,                     //~1A25I~
                        AxeKeyValue.KEYLBL_F6,                     //~1A25I~
                        AxeKeyValue.KEYLBL_F7,                     //~1A25I~
                        AxeKeyValue.KEYLBL_F8,                     //~1A25I~
                        AxeKeyValue.KEYLBL_F9,                     //~1A25I~
                        AxeKeyValue.KEYLBL_F10,                    //~1A25I~
                        AxeKeyValue.KEYLBL_F11,                    //~1A25I~
                        AxeKeyValue.KEYLBL_F12,                    //~1A25I~
                        AxeButton.LABEL_IM,  //"Kbd",                                     //~1831I~//~1A25R~
                        AxeButton.LABEL_IMP, //"IM",                                     //~1831I~//~1A25R~
                        AxeKeyValue.KEYLBL_CLICK,                  //~1A04R~
                        AxeKeyValue.KEYLBL_DBLCLICK,               //~1A04I~
                        AxeKeyValue.KEYLBL_DRAG,                   //~1A04I~
                        AxeKeyValue.KEYLBL_RBTN,                   //~1A04I~
    								};                             //~1809I~
	private static final int BTNTBLSZ=btnkeytbl.length;            //~1813R~
	private static final int TGTKEYTBLSZ=tgtkeyidtbl.length;       //~1813R~
	public static String[] btnnametbl;                             //~1813R~
	public static int[] btn2keyidmap;	      //assigned ext key tbl//~1813R~
	public static int[] btn2keymap;       //for chkTerminalKey     //~1813R~
                                                                   //~1809I~
	private static final int ROWID =R.layout.listrowtermbtn;//~1529I~//~1809R~
                                                                   //~1529I~
	private TextView selectedView2;                                //~1816R~
	private AxeSpinner spinner;                                    //~1816R~
	private int spinnerPos,csrPos;                                 //~1814R~
    private InputFilter[] inputFilter,inputFilter0;                //~1B02I~
                                                                   //~1813I~
//*****************                                                //~1612I~
	static                                                         //~1813I~
    {                                                              //~1813I~
    	btntblinit();                                              //~1813I~
    }                                                              //~1813I~
//*****************                                                //~1813M~
    public static void btntblinit()                                //~1813M~
    {                                                              //~1813M~
    	btn2keymap=new int[KeyData.MAXKEYCODE];                    //~1815R~
	    Arrays.fill(btn2keymap,KeyData.NOT_DEFINED);               //~1813R~
    	btn2keyidmap=new int[BTNTBLSZ];                            //~1813R~
	    Arrays.fill(btn2keyidmap,KeyData.NOT_DEFINED);             //~1813R~
    	btnnametbl=new String[BTNTBLSZ];                           //~1813R~
        for (int ii=0;ii<BTNTBLSZ;ii++)                            //~1813R~
        {                                                          //~1813M~
        	if (ii>=USERBTN1)                                      //~1A29I~
        		btnnametbl[ii]="User"+(ii-USERBTN1+1);             //~1A29I~
            else                                                   //~1A29I~
        		btnnametbl[ii]=Utils.getResourceString(btnnameidtbl[ii]);//~1813R~//~1A29R~
        }                                                          //~1813M~
    	oldbtnkeytbl=new int[BTNTBLSZ];                            //~1B02I~
    }                                                              //~1813M~
//*****************                                                //~1813I~
//    public AxeLstTermBtn(int PdialogId,ViewGroup PlayoutView,int Pmenuid,int Prowid)                                               //~1112I~//~1612I~//~1809R~//~vc1cR~
//    {                                                              //~1612I~//~vc1cR~
////        super(PdialogId,PlayoutView,Pmenuid,Prowid,false/*editableRow*/);                       //~1612I~//~1A29R~//~1B02R~//~vc1cR~
////      super(PdialogId,PlayoutView,Pmenuid,Prowid,true/*editableRow*/);//~1A29R~//~1A30R~//~vc1cR~
//        super(PdialogId,PlayoutView,Pmenuid,Prowid,true/*editableRow*/,true/*clickable*/); //use focus listener//~1B02I~//~vc1cR~
//    }                                                              //~1612I~//~vc1cR~
    public AxeLstTermBtn()                                         //+vc1cI~
    {                                                              //+vc1cI~
        if (Dump.Y) Dump.println("AxeLstTermBtn.defaultConstructor");//+vc1cI~
    }                                                              //+vc1cI~
    public static AxeLstTermBtn newInstance(int PdialogId,ViewGroup PlayoutView,int Pmenuid,int Prowid)//~vc1cI~
    {                                                              //~vc1cI~
        if (Dump.Y) Dump.println("AxeLstTermBtn.newInstance");     //+vc1cI~
	    AxeLstTermBtn al=new AxeLstTermBtn();                      //~vc1cI~
        al.initInstance(PdialogId,PlayoutView,Pmenuid,Prowid,true/*editableRow*/,true/*clickable*/); //use focus listener//~vc1cI~
        return al;                                                 //~vc1cI~
    }                                                              //~vc1cI~
//*****************                                                //~1528I~
    public static AxeLstTermBtn setupListView(AxeSpinner Pspinner,int PdialogId,ViewGroup PlayoutView)//~1612R~//~1813R~
    {                                                              //~1528I~
        if (Dump.Y) Dump.println("AxeLstTermBtn.setupListView");   //+vc1cI~
//  	AxeLstTermBtn al=new AxeLstTermBtn(PdialogId,PlayoutView,0/*Shift menuId*/,ROWID);//~1612R~//~1814R~//~vc1cR~
    	AxeLstTermBtn al=AxeLstTermBtn.newInstance(PdialogId,PlayoutView,0/*Shift menuId*/,ROWID);//~vc1cI~
        al.spinner=Pspinner;                                       //~1814R~
        return al;                                                 //~1528I~
    }                                                              //~1528I~
//*****************                                                //~1612I~
    @Override                                                      //~1612I~
    public void setListViewData()                                  //~1612I~
    {                                                              //~1612I~
		setListViewData(this);	//fill arraylist for TermBtn//~1612R~//~1809R~
    }                                                              //~1612I~
    //*****************************                                //~1809R~
    public void add(int Psource,int Ptarget)                       //~1613R~
    {                                                              //~1612I~
        boolean editable=(Psource>=USERBTN1);                    //~1A29R~//~1A30R~
    	ListData linedata=new ListData(Psource,Ptarget,editable);           //~1612I~//~1A29R~//~1A30R~
        if (Dump.Y) Dump.println("AxeList addTermBtn src="+Integer.toHexString(Psource)+",tgt="+Integer.toHexString(Ptarget)+",editable="+editable);//~1612I~//~1809R~//~1A29R~//~1A30R~
    	super.add(linedata);                                       //~1612I~
    }                                                              //~1612I~
//    //****************                                           //~1816R~
//    @Override                                                    //~1816R~
//    public void addClickListener(int Ppos,EditText PeditText)    //~1816R~
//    {                                                            //~1816R~
////      BufferType editable=BufferType.NORMAL;  //editable:false //~1816R~
////      PeditText.setText(Ptext,editable);                       //~1816R~
////      PeditText.setClickable(true);//avoid SoftKeyboard popup  //~1816R~
//        ClickListener l=new ClickListener(Ppos);                 //~1816R~
//        PeditText.setOnClickListener(l);                         //~1816R~
//        if (Dump.Y)Dump.println("addCliskListener="+l.toString());//~1816R~
//    }                                                            //~1816R~
	//******************                                           //~1809I~
    @Override                                                      //~1809I~
    public void onItemSelected(int Ppos,ListData Pitem)             //~1816R~//~1824R~
    {                                                              //~1809I~
        if (Dump.Y)Dump.println("AxeLstTermBtn onItemSelected pos="+Ppos);//~1816R~//~1824R~
//        selectedView2=(Pitem.textView2);                         //~1B02R~
//        int oldpos=csrPos;                                       //~1B02R~
//        csrPos=Ppos;                                             //~1B02R~
//        setSpinnerPrompt(csrPos);                                //~1B02R~
////        drawLineChange(oldpos,csrPos);                         //~1B02R~
//        notifyDataChanged();    //super                          //~1B02R~
    }                                                              //~1809I~
	//******************                                           //~1824I~
    @Override                                                      //~1824I~
    public void onItemClicked(int Ppos,ListData Pitem)             //~1824I~
    {                                                              //~1824I~
        if (Dump.Y)Dump.println("AxeLstTermBtn onItemClicked pos="+Ppos);//~1824I~
//        selectedView2=(Pitem.textView2);                         //~1B02R~
//        int oldpos=csrPos;                                       //~1B02R~
//        csrPos=Ppos;                                             //~1B02R~
//        setSpinnerPrompt(csrPos);                                //~1B02R~
////      invalidate();   //super                                  //~1B02R~
////        drawLineChange(oldpos,csrPos);                         //~1B02R~
//        notifyDataChanged();    //super                            //~1824R~//~1B02R~
    }                                                              //~1824I~
	//******************                                               //~1612I~//~1809R~
    @Override                                                      //~1612I~
    public void updateTbl(int[] Pnewtbl)                           //~1612I~
    {                                                              //~1612I~
    	updateTermBtn(Pnewtbl);                       //~1612R~    //~1809R~
    }                                                              //~1612I~
//******************                                               //~1612I~
    @Override                                                      //~1612I~
    public int chkInputValue(int Ppos,String Pvalue,boolean Ptoast)         //~1613I~//~1A29R~
    {                                                              //~1612I~
    	String value=Pvalue;                                       //~1A29I~
        if (Dump.Y) Dump.println("TermBtn chkinput pos="+Ppos+",str="+Pvalue);//~1B02I~
	    if (Pvalue.equals(""))                                     //~1810I~
        {                                                          //~1A29I~
//            if (Ppos>=USERBTN1) //user key                       //~1B02R~
//                btnkeytbl[Ppos]=KeyData.NOT_DEFINED;             //~1B02R~
	 		return	KeyData.NOT_DEFINED;                            //~1810I~
        }                                                          //~1A29I~
        if (Ppos>=USERBTN1)	//user key                             //~1A29I~
        {                                                          //~1A29I~
//            btnkeytbl[Ppos]=KeyData.NOT_DEFINED;                 //~1B02R~
//            int offs=Pvalue.indexOf(',');                        //~1B02R~
//            if (offs<=0||offs==Pvalue.length()-1)                //~1B02R~
//                return  AxeKey.KEYVALUE_ERR;                     //~1B02R~
//            value=Pvalue.substring(0,offs);                      //~1B02R~
//            int keycode=Utils.hexstrToNum(Pvalue.substring(offs+1),-1);//~1B02R~
//            if (keycode<0||keycode>=KeyData.MAXKEYCODE)          //~1B02R~
//                return  AxeKey.KEYVALUE_ERR;                     //~1B02R~
//            btnkeytbl[Ppos]=keycode;                             //~1B02R~
            if (chkUserKey(Ppos,Pvalue))                           //~1B02I~
		 		return	AxeKey.KEYVALUE_KEY1;	//until set using spinner//~1B02I~
        }                                                          //~1A29I~
    	for (int ii=0;ii<TGTKEYTBLSZ;ii++)                               //~1810I~//~1813R~
	    	if (value.equalsIgnoreCase(tgtkeynametbl[ii]))                          //~1809R~//~1813R~//~1A29R~
            	return tgtkeyidtbl[ii];                            //~1813R~
        if (Dump.Y) Dump.println("TermBtn chkinput err");          //~1B02I~
 		return	AxeKey.KEYVALUE_ERR;                               //~1810I~
    }                                                              //~1612I~
//******************                                               //~1B02I~
    private boolean chkUserKey(int Ppos,String Pvalue)             //~1B02I~
    {                                                              //~1B02I~
    	boolean rc=false;                                          //~1B02I~
//      int keycode=Utils.hexstrToNum(Pvalue,0/*err*/); //F1 is also valid hex char//~1B02I~
        int keycode=Utils.strToNum(Pvalue,0/*err*/);               //~1B02I~
        if (keycode>0 && keycode<KeyData.MAXKEYCODE)               //~1B02I~
        {                                                          //~1B02I~
	    	oldbtnkeytbl[Ppos]=btnkeytbl[Ppos];	//for chk update   //~1B02I~
	    	btnkeytbl[Ppos]=keycode;                               //~1B02I~
            rc=true;                                               //~1B02I~
        }                                                          //~1B02I~
        if (Dump.Y) Dump.println("chkUserkey pos="+Ppos+",value="+Pvalue+",keycode="+keycode+",rc="+rc);//~1B02I~
        return rc;                                                 //~1B02I~
    }                                                              //~1B02I~
//******************                                               //~1531I~
	@Override                                                      //~1612I~
    public String getKeyname(int Ppos,int Pfldid,int Pkey,String Perr) //~1602R~//~1A29R~
    {                                                              //~1531I~
        if (Pfldid==1)    	//left field                                         //~1612R~//~1809R~
        {                                                          //~1B02I~
//            return btnnametbl[Pkey];                             //~1B02I~
        	String keyname;                                        //~1B02I~
        	int keycode=btnkeytbl[Ppos];                           //~1B02I~
			if (Ppos<USERBTN1)                                     //~1B02I~
	        	keyname=btnnametbl[Ppos];                          //~1B02I~
            else                                                   //~1B02I~
            if (keycode<=0)                                        //~1B02I~
            	keyname=btnnametbl[Ppos]+"[]";                     //~1B02I~
            else                                                   //~1B02I~
            	keyname=btnnametbl[Ppos]+"["+Integer.toString(keycode)+"]";//~1B02I~
//            if (Dump.Y) Dump.println("TermBtn getname1 pos="+Ppos+",name="+keyname);//~1B02I~
            return keyname;                                        //~1B02I~
        }                                                          //~1B02I~
        if (Pkey<=0||Pkey>TGTKEYTBLSZ)                             //~1814R~
        	return "";                                             //~1810I~
		String keynm=tgtkeynametbl[Pkey];                                //~1813R~//~1A29R~
//        if (Ppos>=USERBTN1)                                      //~1B02R~
//        {                                                        //~1B02R~
//            keynm+=","+Integer.toHexString(btnkeytbl[Ppos]);     //~1B02R~
//        }                                                        //~1B02R~
		return keynm;                                              //~1A29I~
    }                                                              //~1531I~
//*************                                                    //~1809I~
    public void setListViewData(AxeLstTermBtn Plist)                  //~1809I~
    {                                                              //~1809I~
    	for (int ii=0;ii<BTNTBLSZ;ii++)                            //~1813R~
        {                                                          //~1809I~
	    	Plist.add(ii,btn2keyidmap[ii]);                        //~1813R~
        }                                                          //~1809I~
    }                                                              //~1809I~
//*************                                                    //~1809I~
	public void updateTermBtn(int[] Pnewtbl)                       //~1809I~
    {                                                              //~1809I~
    	int newkeyid,oldkeyid,sz;                           //~1813R~
		sz=Pnewtbl.length;                                         //~1809I~
    	for (int ii=0;ii<sz;ii++)                                  //~1809I~
        {                                                          //~1809I~
        	newkeyid=Pnewtbl[ii];                                  //~1813R~
            if (newkeyid==AxeKey.KEYVALUE_ERR)	//AxeList filled;not updated//~1813R~
            	continue;                                          //~1813R~
        	oldkeyid=btn2keyidmap[ii];                             //~1813R~
//            if (newkeyid!=oldkeyid) //updated                    //~1B02R~
            if (newkeyid!=oldkeyid 	//func key                     //~1B02I~
	    	||  oldbtnkeytbl[ii]!=btnkeytbl[ii]);	//user keycode //~1B02I~
            {                                                      //~1809I~
            	btn2keyidmap[ii]=newkeyid;                         //~1813R~
		    	savePreference(ii,newkeyid);                       //~1813R~
        		int keycode=btnkeytbl[ii];                         //~1A29R~
                if (keycode>0)	//not undefined userkey            //~1A29I~
                {                                                  //~1A29I~
                    if (newkeyid==KeyData.NOT_DEFINED)                 //~1813R~//~1A29R~
                        btn2keymap[keycode]=KeyData.NOT_DEFINED;        //~1813I~//~1A29R~
                    else                                               //~1813I~//~1A29R~
                        btn2keymap[keycode]=tgtkeytbl[newkeyid];        //~1813R~//~1A29R~
                }                                                  //~1A29I~
            }                                                      //~1809I~
        }                                                          //~1809I~
    }                                                              //~1809I~
//*******************************                                                    //~1809I~//~1A29R~
//*TB(btnNo)=tgtkeyidIndex                                         //~1A29I~
//*for user defined                                                //~1A29I~
//*		TB(btnNo)=tgtkeyidIndex,keycode                            //~1A29I~
//*******************************                                  //~1A29I~
    public static boolean loadPreference()                         //~1809I~
    {                                                              //~1809I~
        String key,value;                                          //~1809I~
        boolean rc=false;	//true if found                        //~1809I~
        int tgtkeyid;                                              //~1813R~//~1A29R~
    //**************                                               //~1809I~
    	for (int ii=0;ii<BTNTBLSZ;ii++)                            //~1813R~
        {                                                          //~1809I~
            key=Integer.toHexString(ii);                           //~1809I~
		    value=AxeProp.getPreference(PREFKEY+key,null);         //~1809I~
            if (value==null)                                       //~1809I~
            	continue;                                          //~1809I~
            rc=true;   	//exist a definition                       //~1809I~
	    	if (ii>=USERBTN1)                                      //~1A29I~
            {                                                      //~1A29I~
//*user defined                                                    //~1A29I~
                int offs=value.indexOf(',');                      //~1A29I~
                String kcv=value.substring(offs+1);                //~1A29I~
                int keycode=Utils.hexstrToNum(kcv,KeyData.NOT_DEFINED);//~1A29I~
                btnkeytbl[ii]=keycode;          //btnno:keycode    //~1A29I~
                value=value.substring(0,offs);                     //~1A29I~
            }                                                      //~1A29I~
            tgtkeyid=Utils.hexstrToNum(value,KeyData.NOT_DEFINED); //~1813R~
            int keycode=btnkeytbl[ii];                             //~1A29I~
            if (keycode>0)                                         //~1A29I~
                if (tgtkeyid>=0 && tgtkeyid<TGTKEYTBLSZ)               //~1813I~//~1A29R~
                {                                                      //~1813I~//~1A29R~
                    btn2keyidmap[ii]=tgtkeyid;                         //~1813R~//~1A29R~
                    btn2keymap[keycode]=tgtkeytbl[tgtkeyid];  //terminal btn keycode:key code to be generated(android keycode or internal function key code)//~1926R~//~1A29R~
                    if (Dump.Y) Dump.println("loadPreference termbtn "+key+"="+btnkeytbl[ii]+"="+Integer.toHexString(tgtkeyid));//~1813R~//~1A29R~
                }                                                      //~1813I~//~1A29R~
        }                                                          //~1809I~
        System.arraycopy(btnkeytbl,0,oldbtnkeytbl,0,BTNTBLSZ);     //~1B02I~
    	return rc;                                                 //~1809I~
    }                                                              //~1809I~
//**********************************************************************//~1810I~
    public void savePreference(int Pkey,int Pvalue)                //~1809I~
    {                                                              //~1809I~
    	if (Pkey>=USERBTN1)                                         //~1A29I~
        {                                                          //~1A29I~
        	String strvalue;                                       //~1A29I~
            int keycode=Math.max(btnkeytbl[Pkey],0);               //~1A29R~
            strvalue=Integer.toHexString(Pvalue)+","+Integer.toHexString(keycode);//~1A29R~
    		AxeG.axeKey.savePreference_KeyValue(PREFKEY,Pkey,strvalue);//~1A29I~
        }                                                          //~1A29I~
        else                                                       //~1A29I~
        {                                                          //~1B02I~
    		AxeG.axeKey.savePreference_KeyValue(PREFKEY,Pkey,Pvalue);              //~1809I~//~1A29R~
        }                                                          //~1B02I~
    }                                                              //~1809I~
//*************                                                    //~1809I~
	public void onSpinnerItemSelected(int Ppos)                                //~1809I~
    {                                                              //~1809I~
        String keyname;                                            //~1810I~
        if (Dump.Y) Dump.println("AxeLstTermBtn onSpinnerItemSelected spinner pos="+Ppos);//~1814R~
    	if (selectedView2==null)                                 //~1809I~//~1816R~
        	return;                                                //~1810I~
        spinnerPos=Ppos;                                       //~1809I~//~1810R~
        if (spinnerPos>0)                                          //~1810R~
            keyname=tgtkeynametbl[spinnerPos];                     //~1813R~
        else                                                       //~1810R~
            keyname="";                                            //~1810R~
        if (Dump.Y) Dump.println("AxeLstTermBtn set edittext spinnerpos="+spinnerPos+",keyname="+keyname);//~1814I~
//        selectedView2.setText(keyname);                            //~1816R~//~1A29R~
//        ((EditText)selectedView2).setSelection(keyname.length()); //move cursor at string end//~1A29R~
//        if (Ppos>=USERBTN1)                                      //~1B02R~
//            ((EditText)selectedView2).setText(keyname,BufferType.EDITABLE); //show cursor//~1B02R~
//        else                                                     //~1B02R~
//            ((EditText)selectedView2).setText(keyname,BufferType.NORMAL);//~1B02R~
        if (selectedView2!=null)                                   //~1B02I~
        {                                                          //~1B02I~
        	if (csrPos>=USERBTN1)                                  //~1B02I~
            {                                                      //~1B02I~
        		String old=selectedView2.getText().toString();     //~1B02I~
            	chkUserKey(Ppos,old);	//chk keycode              //~1B02I~
            }                                                      //~1B02I~
            else                                                   //~1B02I~
            {                                                      //~1B02I~
    			setInputFilter(selectedView2,false);	//remove filter//~1B02I~
            }                                                      //~1B02I~
        	selectedView2.setText(keyname);                        //~1B02I~
        }                                                          //~1B02I~
//      select(csrPos);                                            //~1814R~
//      selectedView2.requestFocus();                              //~1816R~
    }                                                              //~1809I~
//*************                                                    //~1813I~
	public void setSpinnerPrompt(int Ppos)                         //~1813I~
    {                                                              //~1813I~
        String prompt;                                             //~1813I~
    //****************                                             //~1813I~
        if (Dump.Y) Dump.println("AxeLstTermBtn setSpinnerPrompt pos="+Ppos);//~1814R~
        if (Ppos<0)                                                //~1814R~
        {                                                          //~1813I~
	        prompt=Utils.getResourceString(R.string.Prompt_TerminalButton_Not_Selected);//~1813I~
        }                                                          //~1813I~
        else                                                       //~1813I~
        {                                                          //~1813I~
	        prompt=Utils.getResourceString(R.string.Prompt_TerminalButton)+btnnametbl[Ppos];//~1814R~
        }                                                          //~1813I~
        spinner.setPrompt(prompt);                                 //~1813I~
    }                                                              //~1813I~
//*********************************************                                                    //~1809I~//~1811R~
//* chk terminal buttonkey is used                                 //~1811I~
//* rc=-1/0 not terminal btn or not assigned                         //~1811I~//~1A29R~
//*******************************************                      //~1811I~
	public static int chkTerminalKey(int Pkeycode)                 //~1809I~
    {                                                              //~1809I~
//        int termbtnkey=KeyData.NOT_DEFINED;                      //~1813R~
//        for (int ii=0;ii<BTNTBLSZ;ii++)                          //~1813R~
//        {                                                        //~1813R~
//            if (btnkeytbl[ii]==Pkeycode)                         //~1813R~
//            {                                                    //~1813R~
//                termbtnkey=btn2keyidmap[ii];                     //~1813R~
//                break;                                           //~1813R~
//            }                                                    //~1813R~
//        }                                                        //~1813R~
		int termbtnkey=btn2keymap[Pkeycode];                       //~1813R~
        if (Dump.Y) Dump.println("AxeLstBtn:chkTerminalKey keycode="+Integer.toHexString(Pkeycode)+",btnkey="+termbtnkey);//~1809I~
        return termbtnkey;
    }                                                              //~1809I~
////*************                                                  //~1816R~
//    @Override                                                    //~1816R~
//    public void onFocusChanged(int Ppos,boolean Phasfocus,EditText Pview)//~1816R~
//    {                                                            //~1816R~
//        if (Phasfocus)                                           //~1816R~
//        {                                                        //~1816R~
//            selectedView2=Pview;                                 //~1816R~
//            csrPos=Ppos;                                         //~1816R~
//            setSpinnerPrompt(csrPos);                            //~1816R~
//        }                                                        //~1816R~
//    }                                                            //~1816R~
//*********************************************************        //~1B02I~
	@Override                                                      //~1B02I~
    public void onFocusChanged(int Ppos,boolean Phasfocus,ListData Pdata)//~1B02I~
    {                                                              //~1B02I~
    	TextView v2;                                               //~1B02I~
        v2=Pdata.textView2;                                        //~1B02I~
        if (Dump.Y) Dump.println("AxeTermBtn:onFocusChanged focus="+Phasfocus+",pos="+Ppos+",v2="+v2.toString());//~1B02I~
    	if (Ppos>=USERBTN1)	//editable                             //~1B02I~
        {                                                          //~1B02I~
            v2.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS|InputType.TYPE_CLASS_TEXT);//~1B02I~
//            v2.setEnabled(true);                                 //~1B02I~
	        if (Dump.Y) Dump.println("AxeTermBtn:onFocusChanged inputtype:not NULL");//~1B02I~
//            String old=v2.getText().toString();                  //~1B02I~
//            v2.setText(old,BufferType.EDITABLE);    //not editable//~1B02I~
            setInputFilter(v2,false);                              //~1B02I~
        }                                                          //~1B02I~
        else                                                       //~1B02I~
        {                                                          //~1B02I~
            v2.setInputType(InputType.TYPE_NULL);                  //~1B02I~
            setInputFilter(v2,true);                               //~1B02I~
	        if (Dump.Y) Dump.println("AxeTermBtn:onFocusChanged inputtype:NULL");//~1B02I~
//            v2.setEnabled(false);                                //~1B02I~
//            String old=v2.getText().toString();                  //~1B02I~
//            v2.setText(old,BufferType.NORMAL);  //not editable   //~1B02I~
        }                                                          //~1B02I~
        if (Phasfocus)                                             //~1B02I~
        	setSelection(Ppos,v2);                                 //~1B02I~
    }                                                              //~1B02I~
//*********************************************************        //~1B02I~
    private void setInputFilter(TextView PeditText,boolean Plock)  //~1B02I~
    {                                                              //~1B02I~
    	if (inputFilter==null)                                     //~1B02I~
        {                                                          //~1B02I~
            inputFilter=new InputFilter[1];                        //~1B02I~
            inputFilter[0]=new InputFilter.LengthFilter(0);       //set maxLength=0//~1B02I~
            inputFilter0=new InputFilter[0];                       //~1B02I~
        }                                                          //~1B02I~
        if (Plock)                                                 //~1B02I~
			PeditText.setFilters(inputFilter);	//maxlength=0      //~1B02I~
        else                                                       //~1B02I~
			PeditText.setFilters(inputFilter0);	//null filter      //~1B02I~
	    if (Dump.Y) Dump.println("AxeTermBtn:setInputFilter lock="+Plock);//~1B02I~
	}                                                              //~1B02I~
//*********************************************************        //~1B02I~
    private void setSelection(int Ppos,TextView PeditView)         //~1B02I~
    {                                                              //~1B02I~
		selectedView2=PeditView;                                   //~1B02I~
		csrPos=Ppos;                                               //~1B02I~
		setSpinnerPrompt(csrPos);                                  //~1B02I~
	}                                                              //~1B02I~
//*********************************************************        //~1B02I~
    @Override                                                      //~1B02I~
    public void onTextChanged(int Ppos,TextView Pview,String Pstr) //~1B02I~
    {                                                              //~1B02I~
    	if (Ppos<USERBTN1)	//not editable                         //~1B02I~
			setInputFilter(Pview,true);                            //~1B02I~
        else                                                       //~1B02I~
            setInputFilter(Pview,false);                           //~1B02I~
    }                                                              //~1B02I~
}//class                                                           //~1528I~
