//CID://+DATER~:                                                   //~1608R~
//*************************************************************    //~va15I~
//Shift/Shortcut/AltGr key define for shiftkeymap/shortcutkeymap dialog//~1611R~
//*************************************************************    //~va15I~
package com.xe.Axe;                                                //~va15I~

import java.util.Arrays;
import static com.xe.Axe.AxeKeyValue.Sindex_Extendedkey.*;

public class AxeKey                                                //~va15R~
{   
	public static final int SHIFT_UPDATE    =1;                //~va15I~
	public static final int SHIFT_RESET101  =2;                //~va15I~
	public static final int SHIFT_RESET106  =3;                //~va15I~
	
//	private static final String PREFKEY_SHIFTKEY_JIS="JIS106";     //~va15I~
	private static final String PREFKEY_SHORTCUT="SC";
	private static final String PREFKEY_SHIFTKEY="SK";//~va15I~
	private static final String PREFKEY_ALTGR="AG";                //~1611I~
                                                                   //~va15I~
	public static final int KEYTBL_START=' ';                     //~va15R~
	public static final int KEYTBL_END=0x80;                      //~va15I~
	public static final int END_OF_ASCII=0x7f;                     //~1616I~
	public static final int KEYTBL_SIZE=KEYTBL_END-KEYTBL_START;   //~va15R~
	public static final int KEYVALUE_NOTDEF=-1;                   //~va15I~
	public static final int KEYVALUE_ERR=-2;                      //~va15I~
	public static final int KEYVALUE_KEY1=-3;	//usable as view1 key//+1B02I~
                                                                   //~va15I~
    private int[] defaultShiftMapLower_JIS={                       //~va15R~
'1' , '2'  , '3' , '4' , '5' , '6' , '7'  , '8' , '9' , '0' , '-' , '^' , '\\',//~va15R~
'@' , '[' ,                                                        //~va15R~
';' , ':' , ']' ,                                                 //~va15R~
',' , '.' , '/' ,                                                  //~va15R~
	};                                                             //~va15I~
    private int[] defaultShiftMapUpper_JIS={                       //~va15R~
'!' , '\"' , '#' , '$' , '%' , '&' , '\'' , '(' , ')' , '~' , '=' , '~' , '|' ,//~va15R~
'`' , '{' ,                                                        //~va15R~
'+' , '*' , '}' ,                                                  //~va15R~
'<' , '>' , '?' , '_' ,                                            //~va15R~
	};                                                             //~va15I~
    private int[] defaultShiftMapLower_101={                       //~va15I~
'~' , '1' , '2'  , '3' , '4' , '5' , '6' , '7'  , '8' , '9' , '0' , '-' , '=' ,//~va15I~
'[' , ']' , '\\' ,                                                 //~va15I~
';' , '\'' ,                                                       //~va15I~
',' , '.' , '/' ,                                                  //~va15I~
	};                                                             //~va15I~
    private int[] defaultShiftMapUpper_101={                       //~va15I~
'`' , '!' , '@'  , '#' , '$' , '%' , '^' , '&'  , '*' , '(' , ')' , '_' , '+' ,//~va15I~
'{' , '}' , '|'  ,                                                 //~va15I~
':' , '\"' ,                                                       //~va15I~
'<' , '>' , '?' ,                                                  //~va15I~
	};                                                             //~va15I~
    private int[] defaultShortcut_JP={'-','^'};//left adjacent of key '0' on Kbd106//~va15R~
                                                                   //~va15I~
	public static int[] shortcuttbl;                               //~va15R~
	public static int[] shiftkeytbl;                               //~va15R~
	public static int[] altgrtbl;                                  //~1611I~
    private boolean isKbd106;                                      //~va15R~
    //*********************************************                    //~va15I~
    public AxeKey()                                                //~va15R~
    {                                                              //~va15I~
    	AxeKeyValue.init();                                         //~1609I~
                                                                   //~1609I~
    	shortcuttbl=new int[KEYTBL_SIZE];                          //~va15R~
    	shiftkeytbl=new int[KEYTBL_SIZE];                          //~va15R~
    	altgrtbl=new int[KEYTBL_SIZE];                             //~1611I~
        Arrays.fill(shortcuttbl,KEYVALUE_NOTDEF);                  //~va15R~
        Arrays.fill(shiftkeytbl,KEYVALUE_NOTDEF);                  //~va15R~
        Arrays.fill(altgrtbl,KEYVALUE_NOTDEF);                     //~1611I~
//        loadOptionShiftkey();                                    //~va15R~
        isKbd106=AxeG.isLangJP;                                    //~va15M~
    	setDefault_Shiftkey(shiftkeytbl,isKbd106);                 //~1818R~
    	setDefault_Shortcut();                                     //~va15I~
    	loadPreference_AltGr();                                    //~1612I~
    	loadPreference_TermBtn();                                  //~1809I~
        AxeG.axeKeyAction=new AxeKeyAction();                      //~va15M~
    }                                                              //~va15I~
////*********************************************                  //~va15R~
//    private void loadOptionShiftkey()                            //~va15R~
//    {                                                            //~va15R~
//        String value=AxeProp.getPreference(PREFKEY_SHIFTKEY_JIS,"0");//~va15R~
//        isKbd106=value.equals("1");                              //~va15R~
//    }                                                            //~va15R~
//    private void saveOptionShiftkey()                            //~va15R~
//    {                                                            //~va15R~
//        String value=(isKbd106?"1":"0");                         //~va15R~
//        AxeProp.putPreference(PREFKEY_SHIFTKEY_JIS,value);       //~va15R~
//    }                                                            //~va15R~
//*********************************************                    //~va15I~
//    public void setListViewData(int PdialogId,AxeList Plist,int Pmenuid)//~1612R~
//    {                                                            //~1612R~
//        if (PdialogId==AxeDlgShortcutList.DIALOG_SHORTCUT_MAP)   //~1612R~
//        {                                                        //~1612R~
//            setListViewData_Shortcut(Plist,shortcuttbl);         //~1612R~
//        }                                                        //~1612R~
//        else                                                     //~1612R~
//        if (PdialogId==AxeDlgAltGrList.DIALOG_ALTGR_MAP)         //~1612R~
//        {                                                        //~1612R~
//            setListViewData_AltGr(Plist,altgrtbl);               //~1612R~
//        }                                                        //~1612R~
//        else                                                     //~1612R~
//        {                                                        //~1612R~
//            if (Pmenuid==SHIFT_RESET101)                         //~1612R~
//            {                                                    //~1612R~
//                isKbd106=false;                                  //~1612R~
//                setDefault_Shiftkey();                           //~1612R~
//            }                                                    //~1612R~
//            else                                                 //~1612R~
//            if (Pmenuid==SHIFT_RESET106)                         //~1612R~
//            {                                                    //~1612R~
//                isKbd106=true;                                   //~1612R~
//                setDefault_Shiftkey();                           //~1612R~
//            }                                                    //~1612R~
//            setListViewData_Shiftkey(Plist,shiftkeytbl);         //~1612R~
//        }                                                        //~1612R~
//    }                                                            //~1612R~
//************************************                             //~va15R~
//*x20 to x7f                                                      //~va15I~
//************************************                             //~va15I~
    public void setListViewData(AxeLstShortcut Plist)              //~1612R~
    {                                                              //~va15I~
		int[] map=shortcuttbl;                                     //~1612I~
    	for (int ii=KEYTBL_START;ii<KEYTBL_END;ii++)               //~va15R~
        {                                                          //~va15I~
	    	Plist.add(ii,map[ii-KEYTBL_START]);                    //~1613R~
        }                                                          //~va15I~
    }                                                              //~va15I~
//************************************                             //~1611I~
//*x20 to x7f                                                      //~1611I~
//************************************                             //~1611I~
    public void setListViewData(AxeLstAltGr Plist)                 //~1612R~
    {                                                              //~1611I~
		int[] map=altgrtbl;                                        //~1612I~
    	for (int ii=KEYTBL_START;ii<KEYTBL_END;ii++)               //~1611I~
        {                                                          //~1611I~
	    	Plist.add(ii,map[ii-KEYTBL_START]);                    //~1613R~
        }                                                          //~1611I~
    }                                                              //~1611I~
//************************************                             //~va15R~
//*x20 to x7f except aplhabetic *                                  //~va15I~
//************************************                             //~va15I~
//    public void setListViewData(AxeLstShift Plist,int PmenuId)   //~1612R~
//    {                                                            //~1612R~
//        if (PmenuId==SHIFT_RESET101)                             //~1612R~
//        {                                                        //~1612R~
//            isKbd106=false;                                      //~1612R~
//            setDefault_Shiftkey();                               //~1612R~
//        }                                                        //~1612R~
//        else                                                     //~1612R~
//        if (PmenuId==SHIFT_RESET106)                             //~1612R~
//        {                                                        //~1612R~
//            isKbd106=true;                                       //~1612R~
//            setDefault_Shiftkey();                               //~1612R~
//        }                                                        //~1612R~
//        setListViewData(Plist);                                  //~1612R~
//    }                                                            //~1612R~
//*************                                                    //~1612I~
    public void setListViewData(AxeLstShift Plist)                 //~1612R~
    {                                                              //~va15I~
		int[] map=shiftkeytbl;                                     //~1612I~
    	for (int ii=KEYTBL_START;ii<KEYTBL_END;ii++)               //~va15R~
        {                                                          //~va15I~
			if (!isValidShiftLower(ii))                            //~va15R~
            	continue;                                          //~va15I~
	    	Plist.add(ii,map[ii-KEYTBL_START]);                    //~1613R~
        }                                                          //~va15I~
    }                                                              //~va15I~
//*************                                                    //~va15I~
//write to preference                                              //~va15I~
//*************                                                    //~va15I~
//    public void updateTbl(int PdialogId,int[] Pnewtbl)           //~1612R~
//    {                                                            //~1612R~
//        if (PdialogId==AxeDlgShortcutList.DIALOG_SHORTCUT_MAP)   //~1612R~
//            updateShortcut(Pnewtbl);                             //~1612R~
//        else                                                     //~1612R~
//        if (PdialogId==AxeDlgAltGrList.DIALOG_ALTGR_MAP)         //~1612R~
//            updateAltGr(Pnewtbl);                                //~1612R~
//        else                                                     //~1612R~
//            updateShiftkey(Pnewtbl);                             //~1612R~
//    }                                                            //~1612R~
//*************                                                    //~va15I~
	public void updateShortcut(int[] Pnewtbl)                      //~va15R~
    {                                                              //~va15I~
    	int newval,sz;
		sz=Pnewtbl.length;                                         //~va15I~
    	for (int ii=0;ii<sz;ii++)                                  //~va15I~
        {                                                          //~va15I~
        	newval=Pnewtbl[ii];                                    //~va15I~
            if (newval!=KEYVALUE_ERR)	//updated                  //~va15R~
            {                                                      //~va15I~
            	shortcuttbl[ii]=newval;                            //~va15R~
		    	savePreference_Shortcut(ii+KEYTBL_START,newval);   //~va15R~
            }                                                      //~va15I~
        }                                                          //~va15I~
    }                                                              //~va15I~
//*************                                                    //~1611I~
	public void updateAltGr(int[] Pnewtbl)                         //~1611I~
    {                                                              //~1611I~
    	int newval,sz;                                             //~1611I~
		sz=Pnewtbl.length;                                         //~1611I~
    	for (int ii=0;ii<sz;ii++)                                  //~1611I~
        {                                                          //~1611I~
        	newval=Pnewtbl[ii];                                    //~1611I~
            if (newval!=KEYVALUE_ERR)	//updated                  //~1611I~
            {                                                      //~1611I~
            	altgrtbl[ii]=newval;                               //~1611I~
		    	savePreference_AltGr(ii+KEYTBL_START,newval);      //~1611I~
            }                                                      //~1611I~
        }                                                          //~1611I~
    }                                                              //~1611I~
//*************                                                    //~va15I~
	public void updateShiftkey(int[] Pnewtbl)                      //~va15I~
    {   
		int newval,sz;//~va15I~
    	sz=Pnewtbl.length;                                         //~va15I~
    	for (int ii=0;ii<sz;ii++)                                  //~va15I~
        {                                                          //~va15I~
        	newval=Pnewtbl[ii];                                    //~va15I~
            if (newval!=KEYVALUE_ERR)	//updated                  //~va15R~
            {                                                      //~va15I~
            	shiftkeytbl[ii]=newval;                            //~va15R~
		    	savePreference_Shiftkey(ii+KEYTBL_START,newval);   //~va15R~
            }                                                      //~va15I~
        }                                                          //~va15I~
    }                                                              //~va15I~
//*************                                                    //~va15I~
//setup default tbl                                                //~va15I~
//*************                                                    //~va15I~
    public void setDefault_Shortcut()                              //~va15I~
    {                                                              //~va15I~
    	int key,extkey;                                      //~va15I~
    //******************                                           //~va15I~
    	if (isKbd106)                                              //~va15I~
        {                                                          //~va15I~
    		AxeKeyValue.SkdTbl[IKY_F11.ordinal()].defaultShortcut=defaultShortcut_JP[0];//~1609R~
    		AxeKeyValue.SkdTbl[IKY_F12.ordinal()].defaultShortcut=defaultShortcut_JP[1];//~1609R~
        }                                                          //~va15I~
    	for (int ii=0;ii<AxeKeyValue.KDTBLSZ;ii++)                             //~1609R~
        {                                                          //~va15I~
            KeyData kd=AxeKeyValue.SkdTbl[ii];                                 //~1609R~
            key=kd.defaultShortcut;	//a,b,c,...                    //~1609I~
            if (key==0)	//not defined                              //~va15I~
            	continue;                                          //~va15I~
            extkey=kd.keyGDK;                                      //~1609R~
            shortcuttbl[key-KEYTBL_START]=extkey;               //~va15I~
//          if (Dump.Y) Dump.println("shortcut ii="+ii+",key="+Integer.toHexString(key)+",extkey="+Integer.toHexString(extkey));//~1623R~
        }                                                          //~va15I~
    }                                                              //~va15I~
//    public void setDefault_Shiftkey(boolean PisKbd106)           //~1818R~
//    {                                                            //~1818R~
//        isKbd106=PisKbd106;                                      //~1818R~
//        setDefault_Shiftkey();                                   //~1818R~
//    }                                                            //~1818R~
    public void setDefault_Shiftkey(int [] Pshiftkeytbl,boolean PisKbd106)//~1818R~
    {                                                              //~va15I~
    	int lower,ctr;                                       //~va15I~
        int [] keymapL,keymapU,outtbl;                             //~1818R~
    //******************                                           //~va15I~
    	outtbl=Pshiftkeytbl;                                       //~1818I~
    	if (PisKbd106)                                             //~1818R~
        {                                                          //~va15I~
        	keymapL=defaultShiftMapLower_JIS;                      //~va15I~
        	keymapU=defaultShiftMapUpper_JIS;                      //~va15I~
        }                                                          //~va15I~
        else                                                       //~va15I~
        {                                                          //~va15I~
        	keymapL=defaultShiftMapLower_101;                      //~va15I~
        	keymapU=defaultShiftMapUpper_101;                      //~va15I~
        }                                                          //~va15I~
        Arrays.fill(outtbl,KEYVALUE_NOTDEF);                       //~1818R~
        ctr=keymapL.length;                                         //~va15R~
    	for (int ii=0;ii<ctr;ii++)                                 //~va15I~
        {                                                          //~va15I~
            lower=keymapL[ii];                                     //~va15R~
//          if (Dump.Y) Dump.println("shift ii="+ii+",low="+Integer.toHexString(lower)+",upper="+Integer.toHexString(keymapU[ii]));//~1623R~
            outtbl[lower-KEYTBL_START]=keymapU[ii];                //~1818R~
        }                                                          //~va15I~
    }                                                              //~va15I~
    public int [] getDefault_Shiftkey(boolean PisKbd106)           //~1818I~
    {                                                              //~1818I~
    //******************                                           //~1818I~
    	int [] newshiftkeytbl=new int[KEYTBL_SIZE];                       //~1818I~
		setDefault_Shiftkey(newshiftkeytbl,PisKbd106);             //~1818I~
    	return newshiftkeytbl;                                     //~1818I~
    }                                                              //~1818I~
//*************                                                    //~va15I~
    public boolean loadPreference_Shiftkey()                       //~va15R~
    {                                                              //~va15I~
        String key,value;                                          //~va15I~
        boolean rc=false;	//true if found                        //~va15I~
    //**************                                               //~va15I~
    	for (int ii=KEYTBL_START;ii<KEYTBL_END;ii++)               //~va15R~
        {                                                          //~va15I~
        	if (!isValidShiftLower(ii))                            //~va15I~
            	continue;                                          //~va15I~
            key=Integer.toHexString(ii);                           //~va15I~
		    value=AxeProp.getPreference(PREFKEY_SHIFTKEY+key,null);//~va15R~
            if (value==null)                                       //~va15R~
            	continue;                                          //~va15I~
            rc=true;   	//exist a definition                       //~va15R~
            int intvalue=Utils.hexstrToNum(value,KEYVALUE_NOTDEF); //~va15R~
            shiftkeytbl[ii-KEYTBL_START]=intvalue;                 //~va15R~
        }
    	return rc;//~va15I~
    }                                                              //~va15I~
//*************                                                    //~va15I~
    public boolean loadPreference_Shortcut()                       //~va15R~
    {                                                              //~va15I~
        String key,value;                                          //~va15I~
        boolean rc=false;	//true if found                        //~va15I~
        int intval;                                                //~va15I~
    //**************                                               //~va15I~
    	for (int ii=KEYTBL_START;ii<KEYTBL_END;ii++)               //~va15R~
        {                                                          //~va15I~
            key=Integer.toHexString(ii);                           //~va15I~
		    value=AxeProp.getPreference(PREFKEY_SHORTCUT+key,null);//~va15R~
            if (value==null)                                       //~va15I~
            	continue;                                          //~va15I~
            rc=true;   	//exist a definition                       //~va15R~
            intval=Utils.hexstrToNum(value,KEYVALUE_NOTDEF);       //~va15R~
            shortcuttbl[ii-KEYTBL_START]=intval;                   //~va15I~
        	if (Dump.Y) Dump.println("loadPreference Shortcut "+key+"="+value);//~va15I~
        }
    	return rc;//~va15I~
    }                                                              //~va15I~
//*************                                                    //~1611I~
    public boolean loadPreference_AltGr()                          //~1611I~
    {                                                              //~1611I~
        String key,value;                                          //~1611I~
        boolean rc=false;	//true if found                        //~1611I~
        int intval;                                                //~1611I~
    //**************                                               //~1611I~
    	for (int ii=KEYTBL_START;ii<KEYTBL_END;ii++)               //~1611I~
        {                                                          //~1611I~
            key=Integer.toHexString(ii);                           //~1611I~
		    value=AxeProp.getPreference(PREFKEY_ALTGR+key,null);   //~1611I~
            if (value==null)                                       //~1611I~
            	continue;                                          //~1611I~
            rc=true;   	//exist a definition                       //~1611I~
            intval=Utils.hexstrToNum(value,KEYVALUE_NOTDEF);       //~1611I~
            altgrtbl[ii-KEYTBL_START]=intval;                      //~1611I~
        	if (Dump.Y) Dump.println("loadPreference Altgr "+key+"="+value);//~1611I~
        }                                                          //~1611I~
    	return rc;                                                 //~1611I~
    }                                                              //~1611I~
//*************                                                    //~1809I~
    public void loadPreference_TermBtn()                           //~1809I~
    {                                                              //~1809I~
    	AxeLstTermBtn.loadPreference();                            //~1809I~
    }                                                              //~1809I~
//*************                                                    //~va15M~
    public void savePreference_Shiftkey()                          //~va15M~
    {                                                              //~va15M~                                        //~va15M~
    	for (int ii=KEYTBL_START;ii<KEYTBL_END;ii++)               //~va15R~
        {                                                          //~va15M~
    		if (!isValidShiftLower(ii))                            //~va15I~
            	continue;                                          //~va15M~
			savePreference_Shiftkey(ii,shiftkeytbl[ii]);           //~va15I~
        }                                                          //~va15M~
    }                                                              //~va15M~
//*************                                                    //~va15I~
    public void savePreference_Shiftkey(int Pkey,int Pvalue)       //~va15I~
    {                                                              //~va15I~
        String key,strvalue;                                       //~va15I~
        key=Integer.toHexString(Pkey);                             //~va15R~
        if (Pvalue<0)                                              //~va15I~
        	strvalue="";                                           //~va15I~
        else                                                       //~va15I~
			strvalue=Integer.toHexString(Pvalue);                  //~va15R~
		key=PREFKEY_SHIFTKEY+key;                                  //~va15I~
		AxeProp.putPreference(key,strvalue);                          //~va15I~
        if (Dump.Y) Dump.println("savePreference Shiftkey "+key+"="+strvalue);//~va15I~
    }                                                              //~va15I~
//*************                                                    //~va15I~
    public void savePreference_Shortcut()                          //~va15I~
    {                                                              //~va15I~                                       //~va15I~
    	for (int ii=KEYTBL_START;ii<KEYTBL_END;ii++)               //~va15R~
        {                                                          //~va15I~
			savePreference_Shortcut(ii,shortcuttbl[ii]);           //~va15I~
        }                                                          //~va15I~
    }                                                              //~va15I~
//*************                                                    //~1611I~
    public void savePreference_AltGr()                             //~1611I~
    {                                                              //~1611I~
    	for (int ii=KEYTBL_START;ii<KEYTBL_END;ii++)               //~1611I~
        {                                                          //~1611I~
			savePreference_AltGr(ii,altgrtbl[ii]);                 //~1611I~
        }                                                          //~1611I~
    }                                                              //~1611I~
//*************                                                    //~va15I~
    public void savePreference_Shortcut(int Pkey,int Pvalue)       //~va15I~
    {                                                              //~va15I~
    	savePreference_KeyValue(PREFKEY_SHORTCUT,Pkey,Pvalue);     //~1611I~
    }                                                              //~va15I~
//*************                                                    //~1611I~
    public void savePreference_AltGr(int Pkey,int Pvalue)          //~1611I~
    {                                                              //~1611I~
    	savePreference_KeyValue(PREFKEY_ALTGR,Pkey,Pvalue);        //~1611I~
    }                                                              //~1611I~
//*************                                                    //~1611I~
    public void savePreference_KeyValue(String Pkeyprefix,int Pkey,int Pvalue)//~1611I~
    {                                                              //~1611I~
        String key,strvalue;                                       //~1611I~
        key=Integer.toHexString(Pkey);                             //~1611I~
        if (Pvalue<0)                                              //~1611I~
        	strvalue="";                                           //~1611I~
        else                                                       //~1611I~
			strvalue=Integer.toHexString(Pvalue);                  //~1611I~
		key=Pkeyprefix+key;                                        //~1611I~
		AxeProp.putPreference(key,strvalue);                       //~1611I~
        if (Dump.Y) Dump.println("savePreference "+key+"="+strvalue);//~1611I~
    }                                                              //~1611I~
//*************                                                    //~1A29I~
    public void savePreference_KeyValue(String Pkeyprefix,int Pkey,String Pstrvalue)//~1A29I~
    {                                                              //~1A29I~
        String key;                                                //~1A29I~
        key=Integer.toHexString(Pkey);                             //~1A29I~
		key=Pkeyprefix+key;                                        //~1A29I~
		AxeProp.putPreference(key,Pstrvalue);                      //~1A29I~
        if (Dump.Y) Dump.println("savePreference "+key+"="+Pstrvalue);//~1A29I~
    }                                                              //~1A29I~
//*************                                                    //~va15I~
    public static String keyToString(int Pkey)                     //~va15I~
    {                                                              //~va15I~
	    return keyToString(Pkey,"");                               //~va15R~
    }                                                              //~va15I~
//*************                                                    //~va15I~
    public static String keyToString(int Pkey,String PerrStr)             //~va15I~
    {                                                              //~va15I~
    	String hex;                                                //~va15I~
        if (Pkey<0)                                                //~va15I~
        	return PerrStr;                                        //~va15I~
        
        if (Pkey<16)                                               //~va15I~
            hex='0'+Integer.toHexString(Pkey);                     //~va15I~
        else                                                       //~va15I~
            hex=Integer.toHexString(Pkey);                         //~va15I~
    	String str=hex+" ("+Character.toString((char)Pkey) +")";//~va15I~
        return str;                                                //~va15I~
    }                                                              //~va15I~
//*************                                                    //~va15I~
    public static String extendedkeyToString(int Pkey,String Perr) //~va15R~
    {                                                              //~va15I~
    	if (AxeKeyValue.isUnicodeKeyValue(Pkey))                    //~va15I~
        {                                                          //~va15I~
        	int unicode=AxeKeyValue.getUnicodeKeyValue(Pkey);      //~va15I~
            return Integer.toHexString(unicode);                   //~va15I~
        }                                                          //~va15I~
    	return AxeKeyValue.extendedkeyToString(Pkey,Perr);         //~va15R~
    }                                                              //~va15I~
//*************                                                    //~va15I~
//    public static int chkInput(int PdialogId,String Pstrvalue)   //~1612R~
//    {                                                            //~1612R~
//        int intvalue;                                            //~1612R~
//        if (PdialogId==AxeDlgShortcutList.DIALOG_SHORTCUT_MAP)   //~1612R~
//            intvalue=chkInputShortcut(Pstrvalue);                //~1612R~
//        else                                                     //~1612R~
//        if (PdialogId==AxeDlgAltGrList.DIALOG_ALTGR_MAP)         //~1612R~
//            intvalue=chkInputAltGr(Pstrvalue);                   //~1612R~
//        else                                                     //~1612R~
//            intvalue=chkInputShiftkey(Pstrvalue);                //~1612R~
//        if (Dump.Y) Dump.println("AxeKey chkInput int="+Integer.toHexString(intvalue)+",input="+Pstrvalue);//~1612R~
//        return intvalue;                                         //~1612R~
//    }                                                            //~1612R~
//***********************************                              //~va15R~
//*rc: -1:null or space,-2:err                                     //~va15I~
//***********************************                              //~va15I~
    public static int chkInputShiftkey(String Pstr)                //~va15R~
    {                                                              //~va15I~                                           //~va15I~
        int intval;                                                //~va15I~
        String str;                                                //~va15I~
    //*************                                                //~va15I~
        if (Pstr==null)                                            //~va15I~
            return KEYVALUE_NOTDEF;                                //~va15R~
        str=Pstr.trim();                                           //~va15I~
        if (str.equals(""))                                        //~va15I~
            return KEYVALUE_NOTDEF;                                //~va15R~
        int offs=str.indexOf(' ');                                //~va15I~
        if (offs>0)                                                //~va15I~
        str=str.substring(0,offs);                                 //~va15R~
    	if (str.length()==1)                                       //~va15R~
        {                                                          //~va15I~
        	intval=str.charAt(0);                                  //~va15R~
            if (!isValidShiftUpper(intval))                            //~va15I~
                intval=KEYVALUE_ERR;                               //~1612R~
        }                                                          //~va15I~
        else                                                       //~va15I~
        	intval=Utils.hexstrToNum(str,KEYVALUE_ERR);            //~va15R~
        if (Dump.Y) Dump.println("chkInputShiftkey input="+Pstr+",int="+Integer.toHexString(intval));//~va15I~
        return intval;                                             //~va15R~
    }                                                              //~va15I~
//***********************************                              //~1919I~
    public static int chkInputCharCode(String Pstr)                //~1919I~
    {                                                              //~1919I~
        int intval;                                                //~1919I~
        String str;                                                //~1919I~
    //*************                                                //~1919I~
        if (Pstr==null)                                            //~1919I~
            return KEYVALUE_NOTDEF;                                //~1919I~
        str=Pstr.trim();                                           //~1919I~
        if (str.equals(""))                                        //~1919I~
            return KEYVALUE_NOTDEF;                                //~1919I~
        int offs=str.indexOf(' ');                                 //~1919I~
        if (offs>0)                                                //~1919I~
        str=str.substring(0,offs);                                 //~1919I~
    	if (str.length()==1)                                       //~1919I~
        {                                                          //~1919I~
        	intval=str.charAt(0);                                  //~1919I~
        }                                                          //~1919I~
        else                                                       //~1919I~
        	intval=Utils.hexstrToNum(str,KEYVALUE_ERR);            //~1919I~
        if (Dump.Y) Dump.println("chkInputCharCode input="+Pstr+",int="+Integer.toHexString(intval));//~1919I~
        return intval;                                             //~1919I~
    }                                                              //~1919I~
    //***************                                              //~va15I~
    public static boolean isValidShiftLower(int Pch)               //~va15I~
    {                                                              //~va15I~
    	if (Pch<KEYTBL_START                                       //~va15R~
        ||  Pch>=KEYTBL_END                                        //~va15R~
        ||  Pch>='a' && Pch<='z'                                     //~va15I~
        ||  Pch>='A' && Pch<='Z'                                     //~va15I~
        )
        	return false;                                          //~va15I~
        return true;                                               //~va15I~
    }                                                              //~va15I~
    //***************                                              //~va15I~
    public static boolean isValidShiftUpper(int Pch)               //~va15I~
    {                                                              //+va15I~                                             //~va15I~
    	if (Pch<KEYTBL_START                                       //~va15R~
        ||  Pch>='0' && Pch<='9'                                     //~va15I~
        ||  Pch>='a' && Pch<='z'                                     //~va15I~
        ||  Pch>='A' && Pch<='Z'
        )//~va15I~
            return false;                                          //~va15I~
        return true;                                               //~va15I~
    }                                                              //~va15I~
//*************                                                    //~va15I~
    public static int chkInputShortcut(String Pstr)                //~va15R~
    {                                                              //~va15I~
        if (Pstr==null)                                            //~va15I~
            return KEYVALUE_NOTDEF;                                //~va15I~
        String str=Pstr.trim();                                           //~va15I~
        if (str.equals(""))                                        //~va15I~
            return KEYVALUE_NOTDEF;                                //~va15I~
        int intvalue=AxeKeyValue.strToExtendedkey(str,KEYVALUE_ERR);//~va15R~
        if (intvalue==KEYVALUE_ERR)                                //~va15I~
        {                                                          //~va15I~
            intvalue=Utils.hexstrToNum(str,KEYVALUE_ERR);            //~va15I~
        	if (intvalue!=KEYVALUE_ERR)                            //~va15I~
            {                                                      //~va15I~
            	if (AxeKeyValue.isUnicodeKeyValue(intvalue))           //~va15I~
                	intvalue=KEYVALUE_ERR;                         //~va15I~
                else                        
                	intvalue=AxeKeyValue.setUnicodeId(intvalue);            //~va15I~
            }                                                      //~va15I~
        }                                                          //~va15I~
        return intvalue;                                           //~va15I~
    }                                                              //~va15I~
//*************                                                    //~1611I~
    public static int chkInputAltGr(String Pstr)                   //~1611I~
    {                                                              //~1611I~
	    return chkInputShiftkey(Pstr);                             //~1920R~
    }                                                              //~1611I~
//*************                                                    //~va15I~
//	private int[] copytbl(int [] src)                       //~va15I~
//    {                                                              //~va15I~
//    	int [] copy=new int[src.length];
//    	System.arraycopy(src,0,copy,0,src.length);//~va15I~
//       return copy;                                               //~va15I~
//   }                                                              //~va15I~
//*************                                                    //~1608I~
    public int getShortcutcode(int Pkey)                           //~1608I~
    {                                                              //~1608I~
    	int rc;                                                    //~1608I~
    	if (Pkey<KEYTBL_START||Pkey>=KEYTBL_END)               //~1608I~
        	rc=KEYVALUE_NOTDEF;                                    //~1608I~
        else                                                       //~1608I~
    		rc=shortcuttbl[Pkey-KEYTBL_START];                     //~1608I~
        if (Dump.Y) Dump.println("getShortcutcode input="+Integer.toHexString(Pkey)+",out="+Integer.toHexString(rc));//~1608I~
        return rc;                                                 //~1608I~
    }                                                              //~1608I~
//*************                                                    //~1611I~
    public int getAltGrCode(int Pkey)                              //~1611I~
    {                                                              //~1611I~
    	int rc;                                                    //~1611I~
    	if (Pkey<KEYTBL_START||Pkey>=KEYTBL_END)                   //~1611I~
        	rc=KEYVALUE_NOTDEF;                                    //~1611I~
        else                                                       //~1611I~
    		rc=altgrtbl[Pkey-KEYTBL_START];                        //~1611I~
        if (Dump.Y) Dump.println("getAltGrCode input="+Integer.toHexString(Pkey)+",out="+Integer.toHexString(rc));//~1611I~
        return rc;                                                 //~1611I~
    }                                                              //~1611I~
//*************                                                    //~1608I~
    public int getShiftcode(int Pkey)                              //~1608I~
    {                                                              //~1608I~
    	int rc;                                                    //~1608I~
    	if (Pkey<KEYTBL_START||Pkey>=KEYTBL_END)               //~1608I~
        	rc=KEYVALUE_NOTDEF;                                    //~1608I~
        else                                                       //~1608I~
        {                                                          //~1608I~
        	if (Pkey>='a' && Pkey<='z')                            //~1608I~
            	rc='A'+(Pkey-'a');                                //~1608I~
            else                                                   //~1608I~
        	if (Pkey>='A' && Pkey<='Z')   //capsLock case          //~1608I~
            	rc='a'+(Pkey-'A');                                 //~1608I~
            else                                                   //~1608I~
    			rc=shiftkeytbl[Pkey-KEYTBL_START];                 //~1608R~
//        	if (rc!=KEYVALUE_NOTDEF)                               //~1815R~
//            	rc|=AxeKeyValue.KBF_SHIFT;	//set when pass to wxe //~1815R~
        }                                                          //~1608I~
        if (Dump.Y) Dump.println("getShiftcode input="+Integer.toHexString(Pkey)+",out="+Integer.toHexString(rc));//~1608I~
        return rc;                                                 //~1608I~
    }                                                              //~1608I~
}//class                                                           //~va15R~
