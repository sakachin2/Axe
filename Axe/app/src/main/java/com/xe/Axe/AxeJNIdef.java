//CID://+DATER~:                                                   //~1527R~
//*************************************************************    //~va15I~
//JNI interface                                                    //~1610R~
//*************************************************************    //~va15I~
package com.xe.Axe;                                                //~va15I~
                                                                   //~1610R~
public class AxeJNIdef                                             //~1620R~
{                                                                  //~va15I~
	public static final int UDBCSCHK_DBCS1ST='1';     //on dbcs tbl //~1620I~
                                                                   //~1620I~
    public static final int USMSO_WWSCRPRT=  0x01;   //space print by dbcs padding//~1620I~
    public static final int USMSO_DDFMT   =  0x02;   //scr data is dd fmt//~1620I~
    public static final int USMSO_COMBINEON =0x04;   //scr combine mode//~1620I~
    public static final int USMSO_COMBINEOF =0x08;   //scr no combine mode//~1620I~
    public static final int USMSO_COMBINESP =0x10;   //widen width for the string if ligature occured//~1620I~
//*same as jnic2j.h                                                  //~1622R~//~1826R~
	public static final int BTN_OK=1;                              //~1622I~
	public static final int BTN_NO=2;                              //~1622I~
	public static final int BTN_CAN=3;                             //~1622I~
                                                                   //~1826I~
	public static final int DRAWLINE_DIRECT =1;                    //~1826M~
	public static final int DRAWLINE_CARET  =2;                    //~1826M~
	public static final int DRAWLINE_RULER  =4;                    //+1826R~
                                                                   //~1826M~
}//class                                                           //~va15R~
