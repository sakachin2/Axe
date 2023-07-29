//CID://+vc5rR~:        update#=  122221                           //+vc5rR~
//*************************************************************    //~va15I~
//JNI interface                                                    //~1610R~
//*************************************************************    //~va15I~
//vc5r 2023/07/25 try /sdcard for realpath for api<30              //+vc5rI~
//vbyf:230502 (ARM) "//" option required to delete sortpath root(such as //Axe)//~vbyfI~
//vc4s 2023/04/15 edit using SAF                                   //~vc4sI~
//*************************************************************    //~vc4sI~
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
	public static final int DRAWLINE_RULER  =4;                    //~1826R~
                                                                   //~vc4sI~
    public static final String MEMBER_ALL="*";                    //~vc4qI~//~vc4sR~
    public static final String FULLPATHID ="//";                   //~vc4sI~
	public static final String PREFIX_ERROR=":";                  //~vc4qI~//~vc4sR~
	public static final String SEP_ARM_SHARE=":";                 //~vc4qI~//~vc4sR~
    public static final String SEP_ARM_SHARE_PATH="%3A";   //separator between uri and root path//~v777R~//~vc4qI~//~vc4sR~
    public static final int SEP_ARM_SHARE_PATH_LEN=3;   //separator between uri and root path//~vc4qI~//~vc4sR~
    public  static final String SEP_MEMBER="%2F";   //separator "/"      //~v777I~//~vc4qI~//~vc4rR~//~vc4sM~
    public  static final int    ALIAS_PREFIX_LEN=2;		//starting "//" of alias//~vc4rI~//~vc4sM~
	public static  final int EPERM       =1;                       //~vc4qI~//~vc4sM~
	public static  final int ENOENT      =2;                       //~vc4qI~//~vc4sM~
	public static  final int EINTR       =4;    //failed                   //~vc4qI~//~vc4sM~
	public static  final int ECHILD      =10;                      //~vc4qI~//~vc4sM~
	public static  final int ERROR_NO_MORE_FILES =ECHILD;   //as EMPTY//~vc4qI~//~vc4sM~
	public static  final int EACCES      =13;   //permission       //~vbyfI~
	public static  final int EEXIST      =17;                      //~vc4qI~//~vc4sM~
	public static  final int ENOTDIR     =20;                      //~vc4qI~//~vc4sM~
	public static  final int ERROR_PATH_NOT_FOUND  =ENOTDIR;   //Win32,see ufile.h//~vc4qI~//~vc4sM~
	public static  final int EISDIR      =21;                      //~vc4qI~//~vc4sM~
	public static  final int EINVAL      =22;	//invalid argument //~vc4qI~//~vc4sM~
	public static  final int EFBIG       =27;	//too large        //~vc4sM~
	public static  final int ENOTEMPTY   =39;                      //~vc4qI~//~vc4sM~
                                                                   //~1826M~
    public static final int ROPT_DEPEND_FILESIZE   =0x01;	//return string of full file contents//~vc4sR~
    public static final int ROPT_STRING            =0x02;	//return string of full file contents//~vc4sR~
    public static final int ROPT_COPYPRIVATE       =0x04 ;	//copy to private dir,then return fullpath name//~vc4sR~
    public static final int ROPT_OPEN              =0x08 ;	//read line by following READLINE request//~vc4sR~
    public static final int ROPT_BINARY            =0x10 ;	//binary file//~vc4sR~
    public static final int ROPT_READLINE          =0x20 ;	//after OPEN,read a line//~vc4sR~
	public static final int ROPT_INPUT             =0x40 ;	//open input mode          //~v77bI~//~vc4sI~
	public static final int ROPT_APPEND            =0x80 ;	//open input mode//~vc4sI~
    public static final int ROPT_RC_ERR_TOOBIG   =0x010000;        //~vc4sR~
    public static final int ROPT_REALPATH29      =0x020000;        //+vc5rI~
                                                                   //~vc4sI~
	public static final int UFCDO_APPEND         =0x01;	//copyDoc option                                          //~v77hR~//~vc4sI~
}//class                                                           //~va15R~
