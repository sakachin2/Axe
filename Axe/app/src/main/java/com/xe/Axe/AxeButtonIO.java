//CID://+DATER~:                                                   //~1527R~
//*************************************************************    //~va15I~
package com.xe.Axe;                                                //~va15I~
//*************************************************************    //~1602M~
//* save/load Button layout to/from preference                     //~1602I~
//*************************************************************    //~1602I~

public class AxeButtonIO                                           //~va15R~
{                                                                  //~va15I~
	private static final int DATA_SIZE       =32;                  //~va15I~
    private static final int DATA_VERSION    =0;                    //~1527I~
    private static final String VERSION      ="1";                 //~1527I~
    private static final int DATA_LABEL      =DATA_VERSION+1;      //~1527I~
    private static final int DATA_LABELSZ    =4;                   //~1527I~
    private static final int DATA_BUTTONID   =DATA_LABEL+DATA_LABELSZ;//~1527I~
    private static final int DATA_BUTTONIDSZ =2;                   //~1604I~
    private static final int DATA_ISFREE     =DATA_BUTTONID+DATA_BUTTONIDSZ;//~1604R~
    private static final int DATA_ISTOGGLE   =DATA_ISFREE+1;       //~va15I~
    private static final int DATA_ISACTIVE   =DATA_ISTOGGLE+1;     //~va15I~
    private static final int DATA_ISGONE     =DATA_ISACTIVE+1;     //~1602I~
    private static final int DATA_GDKKEY     =DATA_ISGONE+1;       //~1613I~
    private static final int DATA_GDKKEYSZ   =4;                   //~1613I~
	private static final String strPrefKey="Button";               //~va15I~
//*****************                                                //~1527I~
	public static AxeButton load(int PbuttonNo)                    //~1527R~
	{                                                              //~va15I~
    	String key=strPrefKey+PbuttonNo;                           //~1527R~
   		String data=AxeProp.getPreference(key,"");                          //~1527I~
        if (data.equals(""))                                       //~1527R~
        	return null;                                           //~1527I~
        AxeButton btn=stringToButton(data);                        //~1527R~
        return btn;                                                //~1527I~
	}                                                              //~va15I~
//*****************                                                //~1527I~
	public static void save(int PbuttonNo,AxeButton Pbutton)       //~1527R~
	{                                                              //~va15I~
        String data=buttonToString(Pbutton);                       //~1527R~
    	String key=strPrefKey+PbuttonNo;                            //~1527R~
        AxeProp.putPreference(key,data);                           //~1527I~
        if (Dump.Y) Dump.println("ButtonIO save btnno="+PbuttonNo+",id="+Pbutton.buttonId+",label="+Pbutton.label);//~1602I~
	}                                                              //~va15I~
//*****************                                                //~1527I~
	public static AxeButton stringToButton(String Pdata)           //~1527I~
	{                                                              //~1527I~
        int buttonid=(Pdata.charAt(DATA_BUTTONID)-'0')*10+(Pdata.charAt(DATA_BUTTONID+1)-'0');//~1602R~
        if (buttonid<0 || buttonid> AxeButton.BUTTON_IDMAX) //in the developping status//~1613I~
            return null;                                           //~1613I~
//      String label=Pdata.substring(DATA_LABEL,DATA_LABEL+DATA_LABELSZ);//+1830I~
        String label=AxeButton.buttonTypeTbl[buttonid].label;      //+1830I~
        boolean isfree=Pdata.charAt(DATA_ISFREE)=='1';             //~1602R~
        boolean istoggle=Pdata.charAt(DATA_ISTOGGLE)=='1';         //~1602R~
        boolean isactive=Pdata.charAt(DATA_ISACTIVE)=='1';         //~1602R~
        boolean isgone=Pdata.charAt(DATA_ISGONE)=='1';             //~1602R~
        AxeButton btn=new AxeButton(buttonid,label,isfree,istoggle,isactive,isgone);//~1602R~
        int gdkkey=Utils.hexstrToNum(Pdata.substring(DATA_GDKKEY,DATA_GDKKEY+DATA_GDKKEYSZ),KeyData.NOT_DEFINED);//~1613I~
        if (buttonid==AxeButton.BUTTON_USER                          //~1613I~
        &&  AxeKeyValue.isValidExtGDK(gdkkey))                     //~1613I~
        	btn.setUserGDK(gdkkey);                                //~1613I~
        if (Dump.Y) Dump.println("ButtonIO strToButton read="+Pdata);//~1602I~
        return btn;                                                //~1527I~
	}                                                              //~1527I~
//*****************                                                //~1527I~
	public static String buttonToString(AxeButton Pbutton)         //~1527I~
	{                                                              //~1527I~
        StringBuffer sb=new StringBuffer(DATA_SIZE);               //~1602R~
        sbclear(sb,0,DATA_SIZE);                                     //~1527I~
        sb.replace(DATA_VERSION,DATA_VERSION+1,VERSION);           //~1527I~
        sb.replace(DATA_LABEL,DATA_LABEL+DATA_LABELSZ,Pbutton.label);//~1527I~
        if (Pbutton.buttonId<10)                                   //~1527I~
        	sb.replace(DATA_BUTTONID,DATA_BUTTONID+DATA_BUTTONIDSZ,"0"+Pbutton.buttonId);//~1604R~
        else                                                       //~1527I~
        	sb.replace(DATA_BUTTONID,DATA_BUTTONID+DATA_BUTTONIDSZ,Integer.toString(Pbutton.buttonId));//~1604R~
        sb.setCharAt(DATA_ISFREE,(Pbutton.isFree?'1':'0'));            //~1527I~
        sb.setCharAt(DATA_ISTOGGLE,(Pbutton.isToggle?'1':'0'));        //~1527I~
        sb.setCharAt(DATA_ISACTIVE,(Pbutton.isActive?'1':'0'));        //~1527I~
        sb.setCharAt(DATA_ISGONE,(Pbutton.isGone?'1':'0'));        //~1602I~
        if (Pbutton.buttonId==AxeButton.BUTTON_USER                  //~1613I~
        &&  AxeKeyValue.isValidExtGDK(Pbutton.userGDK))            //~1613I~
        	sb.replace(DATA_GDKKEY,DATA_GDKKEY+DATA_GDKKEYSZ,Integer.toHexString(Pbutton.userGDK));//~1613I~
        if (Dump.Y) Dump.println("ButtonIO btntostring="+sb.toString()+";");//~1604R~
        return sb.toString();                                      //~1527I~
	}                                                              //~1527I~
//*****************                                                //~1527I~
	public static void sbclear(StringBuffer Psb,int Ppos,int Pend)      //~1527I~
	{                                                              //~1527I~
    	Psb.setLength(Pend);                                       //~1602I~
    	for (int ii=Ppos;ii<Pend;ii++)                             //~1527I~
        	Psb.setCharAt(ii,' ');                                  //~1527I~
	}                                                              //~1527I~
}//class                                                           //~va15R~
