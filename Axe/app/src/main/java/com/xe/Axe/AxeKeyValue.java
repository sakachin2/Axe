//CID://+vc2jR~:                                 updateno=8                  //~1609R~//~vc23R~//~vc2jR~
//*************************************************************    //~va15I~
//vc2j 2020/07/27 IMopen key on hardKbd                            //~vc2jI~
//vc23 2020/07/10 HardKey list row sequence change, char ken then extended ked//~vc23I~
//*************************************************************    //~va15I~
package com.xe.Axe;                                                //~va15I~

import java.util.Arrays;

import android.view.KeyEvent;
import static com.xe.Axe.AxeKeyValue.Sindex_Extendedkey.*;         //~1609I~

public class AxeKeyValue                                           //~va15R~
{   
 	public static final int SCANCHAR =0xffff;                      //~va15I~
                                                                   //~va15I~
//    public static final int KEY_BS      =0x0e08; //*             //~va15R~
//    public static final int KEY_TAB     =0x0f09; //*             //~va15R~
//    public static final int KEY_BACKTAB =0x0f00; //*             //~va15R~
//    public static final int KEY_ENTER   =0x1c0d; //*             //~va15R~
//    public static final int KEY_SPACE   =0x3920; //*             //~va15R~
//    public static final int KEY_INS     =0x5200;                 //~va15R~
//    public static final int KEY_DEL     =0x5300;                 //~va15R~
//    public static final int KEY_LEFT    =0x4b00;                 //~va15R~
//    public static final int KEY_HOME    =0x4700;                 //~va15R~
//    public static final int KEY_END     =0x4f00;                 //~va15R~
//    public static final int KEY_UP      =0x4800;                 //~va15R~
//    public static final int KEY_DOWN    =0x5000;                 //~va15R~
//    public static final int KEY_PGUP    =0x4900;                 //~va15R~
//    public static final int KEY_PGDN    =0x5100;                 //~va15R~
//    public static final int KEY_RIGHT   =0x4d00;                 //~va15R~
//    public static final int KEY_ESC     =0x011b;//*              //~va15R~
//    public static final int KEY_PENTR   =0xe00d;//*              //~va15R~
//    public static final int KEY_F1      =0x3b00;                 //~va15R~
//    public static final int KEY_F2      =0x3c00;                 //~va15R~
//    public static final int KEY_F3      =0x3d00;                 //~va15R~
//    public static final int KEY_F4      =0x3e00;                 //~va15R~
//    public static final int KEY_F5      =0x3f00;                 //~va15R~
//    public static final int KEY_F6      =0x4000;                 //~va15R~
//    public static final int KEY_F7      =0x4100;                 //~va15R~
//    public static final int KEY_F8      =0x4200;                 //~va15R~
//    public static final int KEY_F9      =0x4300;                 //~va15R~
//    public static final int KEY_F10     =0x4400;                 //~va15R~
//    public static final int KEY_F11     =0x8500;                 //~va15R~
//    public static final int KEY_F12     =0x8600;                 //~va15R~
                                       //~va15I~
    public static final String KEYLBL_ESCAPE      = "Esc"      ;   //~1919I~
    public static final String KEYLBL_TAB         = "Tab"      ;   //~1919I~
//    public static final String KEYLBL_BACKTAB     = "BkTab"    ; //~1A09R~
    public static final String KEYLBL_SPACE       = "Sp"     ;     //~1A12I~
    public static final String KEYLBL_DEL         = "BkSp"     ;   //~1919I~
    public static final String KEYLBL_ENTER       = "Entr"     ;   //~1919I~
    public static final String KEYLBL_INSERT      = "Ins"      ;   //~1919I~
    public static final String KEYLBL_FORWARD_DEL = "Del"      ;   //~1919I~
    public static final String KEYLBL_MOVE_HOME   = "Home"     ;   //~1919I~
    public static final String KEYLBL_MOVE_END    = "End"      ;   //~1919I~
    public static final String KEYLBL_PAGE_UP     = "PgUp"     ;   //~1919I~
    public static final String KEYLBL_PAGE_DOWN   = "PgDn"     ;   //~1919I~
    public static final String KEYLBL_DPAD_UP     = "Up"       ;   //~1919I~
    public static final String KEYLBL_DPAD_DOWN   = "Down"     ;   //~1919I~
    public static final String KEYLBL_DPAD_LEFT   = "Left"     ;   //~1919I~
    public static final String KEYLBL_DPAD_RIGHT  = "Right"    ;   //~1919I~
    public static final String KEYLBL_F1          = "F1"       ;   //~1919I~
    public static final String KEYLBL_F2          = "F2"       ;   //~1919I~
    public static final String KEYLBL_F3          = "F3"       ;   //~1919I~
    public static final String KEYLBL_F4          = "F4"       ;   //~1919I~
    public static final String KEYLBL_F5          = "F5"       ;   //~1919I~
    public static final String KEYLBL_F6          = "F6"       ;   //~1919I~
    public static final String KEYLBL_F7          = "F7"       ;   //~1919I~
    public static final String KEYLBL_F8          = "F8"       ;   //~1919I~
    public static final String KEYLBL_F9          = "F9"       ;   //~1919I~
    public static final String KEYLBL_F10         = "F10"      ;   //~1919I~
    public static final String KEYLBL_F11         = "F11"      ;   //~1919I~
    public static final String KEYLBL_F12         = "F12"      ;   //~1919I~
    public static final String KEYLBL_NUMPAD_ENTER= "PEnt"     ;   //~1919I~
                                                                   //~1919I~
    public static final String KEYLBL_SHIFT       = "Shift"    ;   //~1919I~
    public static final String KEYLBL_CAPS        = "Caps"     ;   //~1A07I~
    public static final String KEYLBL_SHIFTR      = "SYM"      ;   //~1A07R~
    public static final String KEYLBL_CTL         = "Ctrl"     ;   //~1919I~
    public static final String KEYLBL_CTLR        = "CtrlR"    ;   //~1919I~
    public static final String KEYLBL_ALT         = "Alt"      ;   //~1919I~
    public static final String KEYLBL_ALTR        = "AltG"     ;   //~1919I~
    public static final String KEYLBL_ALTRS       = "AltGS"    ;   //~1919R~
                                                                   //~1A03I~
    public static final String KEYLBL_CLICK       = "Mouse-Click"    ;   //~1A03I~//~1A04R~
    public static final String KEYLBL_DBLCLICK    = "Mouse-DblClick"   ;//~1A04R~
    public static final String KEYLBL_DRAG        = "Mouse-Drag"   ;//~1A04I~
    public static final String KEYLBL_RBTN        = "Mouse-Rbtn"    ;//~1A04R~
    public static final String KEYLBL_SHORTCUT    = "S.C"         ;//~1A09I~
                                                                   //~1919I~
    public static final int GDK_Escape=0xff1b           ;          //~1918R~
    public static final int GDK_Tab=0xff09              ;          //~1918R~
    public static final int GDK_BackSpace=0xff08        ;          //~1918R~
    public static final int GDK_Return=0xff0d           ;          //~1918R~
                                                         ;         //~va15I~
    public static final int GDK_Insert=0xff63           ;          //~1918R~
    public static final int GDK_Delete=0xffff           ;          //~1918R~
    public static final int GDK_Home=0xff50             ;          //~1918R~
    public static final int GDK_End=0xff57              ;          //~1918R~
    public static final int GDK_Page_Up=0xff55          ;          //~1918R~
    public static final int GDK_Page_Down=0xff56        ;          //~1918R~
                                                         ;         //~va15I~
    public static final int GDK_Left=0xff51             ;          //~1918R~
    public static final int GDK_Right=0xff53            ;          //~1918R~
    public static final int GDK_Up=0xff52               ;          //~1918R~
    public static final int GDK_Down=0xff54             ;          //~1918R~
                                                         ;         //~va15I~
    public static final int GDK_F1=0xffbe               ;          //~1918R~
    public static final int GDK_F2=0xffbf               ;          //~1918R~
    public static final int GDK_F3=0xffc0               ;          //~1918R~
    public static final int GDK_F4=0xffc1               ;          //~1918R~
    public static final int GDK_F5=0xffc2               ;          //~1918R~
    public static final int GDK_F6=0xffc3               ;          //~1918R~
    public static final int GDK_F7=0xffc4               ;          //~1918R~
    public static final int GDK_F8=0xffc5               ;          //~1918R~
    public static final int GDK_F9=0xffc6               ;          //~1918R~
    public static final int GDK_F10=0xffc7              ;          //~1918R~
    public static final int GDK_F11=0xffc8              ;          //~1918R~
    public static final int GDK_F12=0xffc9              ;          //~1918R~
                                                                   //~va15I~
    public static final int GDK_KP_Enter=0xff8d         ;          //~1918R~
	public static final int GDK_Control_R=0xffe4        ;         //~1611I~
	public static final int GDK_Super_L=0xffeb          ;          //~1A04R~
	public static final int GDK_Super_R=0xffec          ; //GDK_Pointer_Right=0xfee1;-->use ffxx valus temporary//~1A04I~
	public static final int GDK_Hyper_L=0xffed          ; //for LB double click//~1A04I~
	public static final int GDK_Hyper_R=0xffee          ; //for LB double click//~1A05I~
	public static final int EXTKEY_MASK =0xff00;                    //~1815R~
//*modifier                                                        //~va15R~
    public static final int GDK_SHIFT_MASK   =0x01; // = 1 << 0,  //~1611I~
    public static final int GDK_CONTROL_MASK =0x04; // = 1 << 2,  //~1611I~
	public static final int GDK_MOD1_MASK    =0x08; // = 1 << 3,  //~1611I~
	public static final int GDK_BUTTON1_MASK =0x0100; // = 1 << 8,//~1926I~
	public static final int GDK_BUTTON3_MASK =0x0400; // = 1 <<10, //~1A03I~
	public static final int GDK_BUTTON4_MASK =0x0800; // = 1 <<11 for lb double click//~1A04I~
	public static final int GDK_BUTTON5_MASK =0x1000; // = 1 <<12 for lb double click//~1A05I~
                                                                   //~1A04I~
    public static final int KEYSYM_CLICK=GDK_Super_L;	//ffeb:for double click//~1A04R~
    public static final int KEYSYM_RBTN=GDK_Super_R;	//ffec GDL_Super_R is temorary use for simulate mouse rbutton//~1A04I~
    public static final int KEYSYM_DBLCLICK=GDK_Hyper_L;//ffed:for lbutton click//~1A04I~
    public static final int KEYSYM_DRAG=GDK_Hyper_R;    //ffee:for lbutton drag//~1A04I~
//    public static final int KEYSYM_BACKTAB=GDK_Tab|MK_SHIFT;     //~1A09R~
                                                                   //~1A04I~
//  GDK_MOD2_MASK     = 1 << 4,                                    //~1611I~
//  GDK_MOD3_MASK     = 1 << 5,                                    //~1611I~
//  GDK_MOD4_MASK     = 1 << 6,                                    //~1611I~
	private static final int GDK_MOD5_MASK    =0x80;  // = 1 << 7,  //~1611I~
//  GDK_BUTTON1_MASK  = 1 << 8,                                    //~1611I~
//  GDK_BUTTON2_MASK  = 1 << 9,                                    //~1611I~
//  GDK_BUTTON3_MASK  = 1 << 10,                                   //~1611I~
//  GDK_BUTTON4_MASK  = 1 << 11,                                   //~1611I~
//  GDK_BUTTON5_MASK  = 1 << 12,                                   //~1611I~
    public static final int KBF_SHIFT         =(GDK_SHIFT_MASK<<16);    //0x00010000//~1611R~
    public static final int KBF_CONTROL       =(GDK_CONTROL_MASK<<16);	//0x00040000;//~1611R~
    public static final int KBF_ALT           =(GDK_MOD1_MASK<<16);     //0x00080000;//~1611R~
    public static final int KBF_ALTGR         =(GDK_MOD5_MASK<<16);     //0x00800000;//~1815R~
    public static final int KBF_RIGHTCONTROL  =0x04000000;         //~1611R~
    public static final int KBF_IM            =0x10000000;    //for IM//~vc2jI~
    public static final int KBF_GDK           =0x20000000;    //GDK value by spinner//~0703R~//~0707R~
    public static final int KBF_UNICODE       =0x40000000;    //2byte unicode support//~0703I~
//    public static final int META_ALT_ON = 0x02;                  //~va15I~
//    public static final int META_ALT_RIGHT_ON = 0x20;            //~va15I~
//    public static final int META_ALT_LEFT_ON = 0x10;             //~va15I~
//    public static final int META_SHIFT_ON = 0x1;                 //~va15I~
//    public static final int META_SHIFT_LEFT_ON = 0x40;           //~va15I~
//    public static final int META_SHIFT_RIGHT_ON = 0x80;          //~va15I~
//    public static final int KEYCODE_CTRL_LEFT   =113 //apilevel11//~va15I~
//    public static final int KEYCODE_CTRL_RIGHT  =114 //apilevel11//~va15I~//~1810R~
//    public static final int META_CTRL_ON        =0x1000;  //api11//~va15I~
//    public static final int META_CTRL_LEFT_ON   =0x2000;  //api11//~va15I~
//    public static final int META_CTRL_RIGHT_ON  =0x4000;  //api11//~va15I~
//    public static int metaToMod(int metastate)                   //~va15I~
	public  static final int META_SHIFT=KeyEvent.META_SHIFT_ON;                          //~va15I~//~vc23R~
	public  static final int META_ALT  =KeyEvent.META_ALT_ON;                       //~va15I~//~vc23R~
	public  static final int META_ALT_L=KeyEvent.META_ALT_LEFT_ON; //~vc23I~
	public  static final int META_ALT_R=KeyEvent.META_ALT_RIGHT_ON;//~vc23R~
	public  static final int META_CTL  =0x01000;                                    //~va15I~//~vc23R~
	public  static final int META_CTL_R=0x04000;                                    //~va15I~//~vc23R~
//indea table  modifier                                            //~va15I~
    enum Sindex_Modifier{                                          //~va15R~
    		IMOD_SHIFT,IMOD_CONTROL,IMOD_ALT,IMOD_RIGHTCONTROL   //~va15I~
        };                                                         //~va15I~
    public static final int[]    SmodifierId=                       //~va15I~
        {                                                          //~va15I~
            KBF_SHIFT,KBF_CONTROL,KBF_ALT,KBF_RIGHTCONTROL//~va15I~
        };                                                         //~va15I~
    public static final String[] SmodifierName=                     //~va15I~
        {                                                          //~va15I~
//          "Shift","Ctrl","Alt","R-Ctrl"                          //~1919R~
            KEYLBL_SHIFT,KEYLBL_CTL,KEYLBL_ALT,KEYLBL_CTLR         //~1919I~
        };                                                         //~va15I~
    public static final String[] SmodifierNameShort=               //~va15I~
        {                                                          //~va15I~
            "S+","C+","A+","R-C"                                   //~va15I~
        };                                                         //~va15I~
    private static final int[] SkeycodeIM={                          //~vc2jI~
				KeyEvent.KEYCODE_MUHENKAN,                         //~vc2jI~
				KeyEvent.KEYCODE_HENKAN,                           //~vc2jI~
				KeyEvent.KEYCODE_KATAKANA_HIRAGANA,                //~vc2jI~
				KeyEvent.KEYCODE_EISU,                             //~vc2jI~
				KeyEvent.KEYCODE_KANA,                             //~vc2jI~
        };                                                         //~vc2jI~
    private static final int[] SkeycodeIMStrID={                     //~vc2jI~
				R.string.KEYCODE_MUHENKAN,                         //~vc2jI~
				R.string.KEYCODE_HENKAN,                           //~vc2jI~
				R.string.KEYCODE_KATAKANA_HIRAGANA,                //~vc2jI~
				R.string.KEYCODE_EISU,                             //~vc2jI~
				R.string.KEYCODE_KANA,                             //~vc2jI~
        };                                                         //~vc2jI~
//*Android KEYCODE ***********************                         //~va15M~
	public static final int AKC_BACK    =KeyEvent.KEYCODE_BACK;    //~va15M~
	public static final int AKC_MENU    =KeyEvent.KEYCODE_MENU;    //~va15M~
	public static final int AKC_CAMERA  =KeyEvent.KEYCODE_CAMERA;//x1b//~1809I~
	public static final int AKC_SEARCH  =KeyEvent.KEYCODE_SEARCH;//x54//~1809I~
	public static final int AKC_VOLUP   =KeyEvent.KEYCODE_VOLUME_UP;//x18//~1809I~
	public static final int AKC_VOLDN   =KeyEvent.KEYCODE_VOLUME_DOWN;//x19//~1809I~
	public static final int AKC_HOME    =KeyEvent.KEYCODE_HOME;//x03//~1809I~
	public static final int AKC_CENTER  =KeyEvent.KEYCODE_DPAD_CENTER;//x17//~1809I~
	public static final int AKC_CALL    =KeyEvent.KEYCODE_CALL;//x05//~1809I~
	public static final int AKC_ENDCALL =KeyEvent.KEYCODE_ENDCALL;//x06//~1809I~
	public static final int AKC_RCTL    =114; //KeyEvent.KEYCODE_CTRL_RIGHT;//x06//~1810I~
                                                                   //~1809I~
    private static KeyData kdEscape   ;                            //~1609R~
    private static KeyData kdTab      ;                            //~1609R~
    private static KeyData kdBackspace;                            //~1609R~
    private static KeyData kdEnter    ;                            //~1609R~
    private static KeyData kdInsert   ;                            //~1609R~
    private static KeyData kdDelete   ;                            //~1609R~
    private static KeyData kdHome     ;                            //~1609R~
    private static KeyData kdEnd      ;                            //~1609R~
    private static KeyData kdPgUp     ;                            //~1609R~
    private static KeyData kdPgDn     ;                            //~1609R~
    private static KeyData kdUp       ;                            //~1609R~
    private static KeyData kdDown     ;                            //~1609R~
    private static KeyData kdLeft     ;                            //~1609R~
    private static KeyData kdRight    ;                            //~1609R~
    private static KeyData kdF1       ;                            //~1609R~
    private static KeyData kdF2       ;                            //~1609R~
    private static KeyData kdF3       ;                            //~1609R~
    private static KeyData kdF4       ;                            //~1609R~
    private static KeyData kdF5       ;                            //~1609R~
    private static KeyData kdF6       ;                            //~1609R~
    private static KeyData kdF7       ;                            //~1609R~
    private static KeyData kdF8       ;                            //~1609R~
    private static KeyData kdF9       ;                            //~1609R~
    private static KeyData kdF10      ;                            //~1609R~
    private static KeyData kdF11      ;                            //~1609R~
    private static KeyData kdF12      ;                            //~1609R~
    private static KeyData kdPEnter   ;                            //~1609R~
    private static KeyData kdRCTL     ;                            //~1810I~
    private static KeyData kdClick    ;                            //~1A03I~//~1A04R~
    private static KeyData kdDblClick ;                            //~1A04I~
    private static KeyData kdDrag     ;                            //~1A04I~
    private static KeyData kdRBtn     ;                            //~1A04I~
                                                                   //~1609I~
    private static KeyData kdError;                                //~1609I~
                                                                   //~va15I~
    public static KeyData[] SkdTbl;                         //~va15I~
//index table extended key                                         //~va15M~
    enum Sindex_Extendedkey {                                      //~va15M~
            IKY_ESC     ,                                          //~va15M~
            IKY_TAB     ,                                          //~va15M~
            IKY_BS      ,                                          //~va15M~
            IKY_ENTER   ,                                          //~va15M~
            IKY_INS     ,                                          //~va15M~
            IKY_DEL     ,                                          //~va15M~
            IKY_HOME    ,                                          //~va15M~
            IKY_END     ,                                          //~va15M~
            IKY_PGUP    ,                                          //~va15M~
            IKY_PGDN    ,                                          //~va15M~
            IKY_UP      ,                                          //~va15M~
            IKY_DOWN    ,                                          //~va15M~
            IKY_LEFT    ,                                          //~va15M~
            IKY_RIGHT   ,                                          //~va15M~
            IKY_F1      ,                                          //~va15M~
            IKY_F2      ,                                          //~va15M~
            IKY_F3      ,                                          //~va15M~
            IKY_F4      ,                                          //~va15M~
            IKY_F5      ,                                          //~va15M~
            IKY_F6      ,                                          //~va15M~
            IKY_F7      ,                                          //~va15M~
            IKY_F8      ,                                          //~va15M~
            IKY_F9      ,                                          //~va15M~
            IKY_F10     ,                                          //~va15M~
            IKY_F11     ,                                          //~va15M~
            IKY_F12     ,                                          //~va15M~
            IKY_PENTR   ,                                          //~va15M~
            IKY_RCTL   ,                                           //~1810I~
            IKY_CLICK  ,                                           //~1A04I~
            IKY_DBLCLICK,                                          //~1A04I~
            IKY_DRAG,                                              //~1A04I~
            IKY_RBTN   ,                                           //~1A03I~
            IKY_LENGTH                                             //~1609I~
        };                                                         //~va15M~
    public final static int KDTBLSZ=IKY_LENGTH.ordinal();         //~1609I~
//    private static final int[]    SkeycodeTbl2=                    //~va15I~//~1809R~
//        {   //Android specific extended key                        //~va15I~//~1809R~
//            KeyEvent.KEYCODE_DPAD_CENTER                           //~va15I~//~1809R~
//        };                                                         //~va15I~//~1809R~
    private static int[] SkeycodeToExtkeyTbl;                              //~va15I~
    private static String[] SkeycodeToExtkeyNameTbl;               //~vc2jI~
    public  static String[] SkeyNameList;                          //~1609I~
//*****************************************************************//~va15I~
//*extkey and printable                                            //~va15I~
//*****************************************************************//~va15I~
    private static StringBuffer wksb;                              //~va15I~
	public static void init()                                      //~1609R~
    {                                                              //~va15I~
	    KeyData kd;                                                //~va15I~
        int keycode;                                               //~va15I~
    //********************                                         //~va15I~
    	wksb=new StringBuffer(32);                                 //~va15I~
        initKD();                                                  //~1609I~
//*keycode->extended key mapping                                   //~va15I~
        SkeycodeToExtkeyTbl=new int[KeyData.MAXKEYCODE];           //~1815R~
        SkeycodeToExtkeyNameTbl=new String[KeyData.MAXKEYCODE];    //~vc2jI~
        Arrays.fill(SkeycodeToExtkeyTbl,KeyData.NOT_DEFINED);      //~1609R~
        int sz=KDTBLSZ;                                            //~1609R~
        for (int ii=0;ii<sz;ii++)                                  //~va15I~
        {                                                          //~va15I~
        	kd=SkdTbl[ii];                                         //~va15R~
        	keycode=kd.keyCode;                                    //~va15R~
            if (keycode!=KeyData.NOT_DEFINED)                      //~1609R~
            {                                                      //~vc2jI~
        		SkeycodeToExtkeyTbl[keycode]=kd.keyGDK;            //~va15I~
        		SkeycodeToExtkeyNameTbl[keycode]=kd.keyName;       //~vc2jI~
            }                                                      //~vc2jI~
        }                                                          //~va15I~
//*keynameList                                                     //~1609I~
        SkeyNameList=new String[KDTBLSZ];                        //~1609I~
        for (int ii=0;ii<KDTBLSZ;ii++)                                  //~1609I~
        {                                                          //~1609I~
            kd=SkdTbl[ii];                                         //~1609I~
            SkeyNameList[ii]=kd.keyName;                           //~1609I~
        }                                                          //~1609I~
    }                                                              //~va15I~
    private static void initKD()                                   //~1609R~
    {                                                              //~1609I~
        kdEscape   =new KeyData( GDK_Escape   ,  KeyData.KEYCODE_ESCAPE      , KEYLBL_ESCAPE      ,'c',false);//~1919R~
        kdTab      =new KeyData( GDK_Tab      , KeyEvent.KEYCODE_TAB         , KEYLBL_TAB         ,'t',false);//~1919R~
        kdBackspace=new KeyData( GDK_BackSpace, KeyEvent.KEYCODE_DEL         , KEYLBL_DEL         ,'b',false);//~1919R~
        kdEnter    =new KeyData( GDK_Return   , KeyEvent.KEYCODE_ENTER       , KEYLBL_ENTER       ,'n',false);//~1919R~//~1A04R~
        kdInsert   =new KeyData( GDK_Insert   ,  KeyData.KEYCODE_INSERT      , KEYLBL_INSERT      ,'i',false);//~1919R~
        kdDelete   =new KeyData( GDK_Delete   ,  KeyData.KEYCODE_FORWARD_DEL , KEYLBL_FORWARD_DEL ,'d',false);//~1613R~//~1919R~
        kdHome     =new KeyData( GDK_Home     ,  KeyData.KEYCODE_MOVE_HOME   , KEYLBL_MOVE_HOME   ,'h',false);//~1919R~
        kdEnd      =new KeyData( GDK_End      ,  KeyData.KEYCODE_MOVE_END    , KEYLBL_MOVE_END    ,'e',false);//~1919R~
        kdPgUp     =new KeyData( GDK_Page_Up  ,  KeyData.KEYCODE_PAGE_UP     , KEYLBL_PAGE_UP     ,'u',false);//~1919R~
        kdPgDn     =new KeyData( GDK_Page_Down,  KeyData.KEYCODE_PAGE_DOWN   , KEYLBL_PAGE_DOWN   ,'f',false);//~1919R~
        kdUp       =new KeyData( GDK_Up       , KeyEvent.KEYCODE_DPAD_UP     , KEYLBL_DPAD_UP     ,0  ,true );//~1919R~
        kdDown     =new KeyData( GDK_Down     , KeyEvent.KEYCODE_DPAD_DOWN   , KEYLBL_DPAD_DOWN   ,0  ,true );//~1919R~
        kdLeft     =new KeyData( GDK_Left     , KeyEvent.KEYCODE_DPAD_LEFT   , KEYLBL_DPAD_LEFT   ,0  ,true );//~1919R~
        kdRight    =new KeyData( GDK_Right    , KeyEvent.KEYCODE_DPAD_RIGHT  , KEYLBL_DPAD_RIGHT  ,0  ,true );//~1919R~
        kdF1       =new KeyData( GDK_F1       ,  KeyData.KEYCODE_F1          , KEYLBL_F1          ,'1',false);//~1919R~
        kdF2       =new KeyData( GDK_F2       ,  KeyData.KEYCODE_F2          , KEYLBL_F2          ,'2',false);//~1919R~
        kdF3       =new KeyData( GDK_F3       ,  KeyData.KEYCODE_F3          , KEYLBL_F3          ,'3',false);//~1919R~
        kdF4       =new KeyData( GDK_F4       ,  KeyData.KEYCODE_F4          , KEYLBL_F4          ,'4',false);//~1919R~
        kdF5       =new KeyData( GDK_F5       ,  KeyData.KEYCODE_F5          , KEYLBL_F5          ,'5',false);//~1919R~
        kdF6       =new KeyData( GDK_F6       ,  KeyData.KEYCODE_F6          , KEYLBL_F6          ,'6',false);//~1919R~
        kdF7       =new KeyData( GDK_F7       ,  KeyData.KEYCODE_F7          , KEYLBL_F7          ,'7',false);//~1919R~
        kdF8       =new KeyData( GDK_F8       ,  KeyData.KEYCODE_F8          , KEYLBL_F8          ,'8',false);//~1919R~
        kdF9       =new KeyData( GDK_F9       ,  KeyData.KEYCODE_F9          , KEYLBL_F9          ,'9',false);//~1919R~
        kdF10      =new KeyData( GDK_F10      ,  KeyData.KEYCODE_F10         , KEYLBL_F10         ,'0',false);//~1919R~
        kdF11      =new KeyData( GDK_F11      ,  KeyData.KEYCODE_F11         , KEYLBL_F11         ,'-',false);//~1919R~
        kdF12      =new KeyData( GDK_F12      ,  KeyData.KEYCODE_F12         , KEYLBL_F12         ,'=',false);//~1919R~
        kdPEnter   =new KeyData( GDK_KP_Enter ,  KeyData.KEYCODE_NUMPAD_ENTER, KEYLBL_NUMPAD_ENTER,'p',false);//~1919R~
        kdRCTL     =new KeyData( GDK_Control_R,  KeyData.KEYCODE_RCTL        , KEYLBL_CTLR        ,'r',false);//~1919R~//~1A04R~
        kdClick    =new KeyData( KEYSYM_CLICK ,  KeyData.KEYCODE_CLICK       , KEYLBL_CLICK       ,'v',false);//~1A04R~
        kdDblClick =new KeyData( KEYSYM_DBLCLICK,KeyData.KEYCODE_DBLCLICK    , KEYLBL_DBLCLICK    ,'w',false);//~1A04I~
        kdDrag     =new KeyData( KEYSYM_DRAG  ,  KeyData.KEYCODE_DRAG        , KEYLBL_DRAG        ,'g',false);//~1A04I~
        kdRBtn     =new KeyData( KEYSYM_RBTN  ,  KeyData.KEYCODE_RBTN        , KEYLBL_RBTN        ,'m',false);//~1A03I~//~1A04R~
        kdError    =new KeyData( KeyData.NOT_DEFINED,    KeyData.NOT_DEFINED , "NotDefined",0,false);//~1613R~
                                                                   //~1609I~
    	SkdTbl=new KeyData[KDTBLSZ];                               //~1609I~
    	
        SkdTbl[IKY_ESC.ordinal()]    =kdEscape    ;                //~1609I~
        SkdTbl[IKY_TAB.ordinal()]    =kdTab       ;                //~1609I~
        SkdTbl[IKY_BS.ordinal()]     =kdBackspace ;                //~1609I~
        SkdTbl[IKY_ENTER.ordinal()]  =kdEnter     ;                //~1609I~
        SkdTbl[IKY_INS.ordinal()]    =kdInsert    ;                //~1609I~
        SkdTbl[IKY_DEL.ordinal()]    =kdDelete    ;                //~1609I~
        SkdTbl[IKY_HOME.ordinal()]   =kdHome      ;                //~1609I~
        SkdTbl[IKY_END.ordinal()]    =kdEnd       ;                //~1609I~
        SkdTbl[IKY_PGUP.ordinal()]   =kdPgUp      ;                //~1609I~
        SkdTbl[IKY_PGDN.ordinal()]   =kdPgDn      ;                //~1609I~
        SkdTbl[IKY_UP.ordinal()]     =kdUp        ;                //~1609I~
        SkdTbl[IKY_DOWN.ordinal()]   =kdDown      ;                //~1609I~
        SkdTbl[IKY_LEFT.ordinal()]   =kdLeft      ;                //~1609I~
        SkdTbl[IKY_RIGHT.ordinal()]  =kdRight     ;                //~1609I~
        SkdTbl[IKY_F1.ordinal()]     =kdF1        ;                //~1609I~
        SkdTbl[IKY_F2.ordinal()]     =kdF2        ;                //~1609I~
        SkdTbl[IKY_F3.ordinal()]     =kdF3        ;                //~1609I~
        SkdTbl[IKY_F4.ordinal()]     =kdF4        ;                //~1609I~
        SkdTbl[IKY_F5.ordinal()]     =kdF5        ;                //~1609I~
        SkdTbl[IKY_F6.ordinal()]     =kdF6        ;                //~1609I~
        SkdTbl[IKY_F7.ordinal()]     =kdF7        ;                //~1609I~
        SkdTbl[IKY_F8.ordinal()]     =kdF8        ;                //~1609I~
        SkdTbl[IKY_F9.ordinal()]     =kdF9        ;                //~1609I~
        SkdTbl[IKY_F10.ordinal()]    =kdF10       ;                //~1609I~
        SkdTbl[IKY_F11.ordinal()]    =kdF11       ;                //~1609I~
        SkdTbl[IKY_F12.ordinal()]    =kdF12       ;                //~1609I~
        SkdTbl[IKY_PENTR.ordinal()]  =kdPEnter    ;                //~1609I~
        SkdTbl[IKY_RCTL.ordinal()]   =kdRCTL      ;                  //~1810I~//~1A04R~
        SkdTbl[IKY_CLICK.ordinal()]  =kdClick     ;                //~1A04I~
        SkdTbl[IKY_DBLCLICK.ordinal()]=kdDblClick ;                //~1A04I~
        SkdTbl[IKY_DRAG.ordinal()]   =kdDrag      ;                //~1A04I~
        SkdTbl[IKY_RBTN.ordinal()]   =kdRBtn      ;                  //~1A03I~//~1A04R~
    }                                                              //~1609I~
    static String extendedkeyToString(int Pkey,String Perr)        //~va15R~
    {                                                              //~va15I~
        int sz;                                                    //~va15I~
        String keynm;                                              //~va15I~
    //***************                                              //~va15I~
        if (Pkey==KeyData.NOT_DEFINED)                             //~1609I~
        	return Perr;                                           //~1609I~
        int idx=srchExtendedkey(Pkey);                             //~va15M~
        if (idx<0)                                                 //~va15M~
        	return Perr;                                           //~va15I~
        keynm=SkdTbl[idx].keyName;                                 //~va15R~
        wksb.setLength(0);                                         //~va15M~
        sz=SmodifierId.length;                                     //~va15I~
        for (int ii=0;ii<sz;ii++)                                  //~va15I~
        {                                                          //~va15I~
			if ((Pkey & SmodifierId[ii])!=0)                       //~va15I~
            	wksb.append(SmodifierNameShort[ii]);               //~va15I~
        }                                                          //~va15I~
        wksb.append(keynm);                                        //~va15M~
        if (Dump.Y) Dump.println("AxeKeyValue.extendedkeyToString key="+Integer.toHexString(Pkey)+"="+wksb);//~va15I~//~vc2jR~
        return wksb.toString();                                    //~va15I~
    }                                                              //~va15I~
    static String modifierToString(int Pmod,String Pkeyname)    //~va15I~
    {                                                              //~va15I~
        int sz;                                                    //~va15I~                                            //~va15I~
    //***************                                              //~va15I~
        wksb.setLength(0);                                         //~va15I~
        sz=SmodifierId.length;                                     //~va15I~
        for (int ii=0;ii<sz;ii++)                                  //~va15I~
        {                                                          //~va15I~
			if ((Pmod & SmodifierId[ii])!=0)                       //~va15I~
            	wksb.append(SmodifierNameShort[ii]);               //~va15I~
        }                                                          //~va15I~
        if (Pkeyname!=null)
        	wksb.append(Pkeyname);                                     //~va15I~
        if (Dump.Y) Dump.println("AxeKeyvalue.modifierToString mod="+Integer.toHexString(Pmod)+"="+wksb);//~va15I~//~vc2jR~
        return wksb.toString();                                    //~va15I~
    }                                                              //~va15I~
//**************************************************************** //~vc23I~
//*Event.getMetaState()-->A+..                                     //~vc23I~
//**************************************************************** //~vc23I~
    public static String metaToString(int Pmeta,String Pkeyname)          //~vc23I~
    {                                                              //~vc23I~
        int mod=metaToMod(Pmeta);                                  //~vc23I~
    	String rc=modifierToString(mod,Pkeyname);                   //~vc23I~
        if (Dump.Y) Dump.println("metaToString meta="+Integer.toHexString(Pmeta)+",rc="+rc);//~vc23I~
        return rc;                                                 //~vc23I~
    }                                                              //~vc23I~
//********	                                                       //~va15I~
    static int strToExtendedkey(String Pstr,int Perr)           //~va15I~
    {                                                              //~va15I~
    	int modkey,offs,xkey;                                      //~va15I~
    //****************                                             //~va15I~
    	if (Pstr==null)                                            //~va15I~
        	return Perr;                                           //~va15I~
	    modkey=chkModifier(Pstr);                                  //~va15I~
        offs=modkey&0xffff;                                        //~va15I~
        modkey&=0xffff0000;                                        //~va15I~
        int len=Pstr.length();                                       //~va15I~
        if (offs>=len)                                             //~va15I~
        {                                                          //~va15I~
        	if (modkey==0)                                         //~va15I~
        		return Perr;                                       //~va15I~
            else                                                   //~va15I~
        		return modkey;                                     //~va15I~
        }                                                          //~va15I~
        String keyname=Pstr.substring(offs);                              //~va15I~
        xkey=srchExtendedkeyByName(keyname);                       //~va15I~
        if (xkey<0)                                                //~va15I~
        	return Perr;                                           //~va15I~
        xkey|=modkey;                                              //~va15I~
        if (Dump.Y) Dump.println("strToExtendedkey "+Pstr+",key="+Integer.toHexString(xkey));//~va15I~
    	return xkey;                                               //~va15I~
    }                                                              //~va15I~
//********                                                         //~va15I~
    static int strToExtendedkeyNoModifier(String Pstr,int Perr)    //~va15I~
    {                                                              //~va15I~
    	int xkey;                                                  //~va15I~
    //****************                                             //~va15I~
    	if (Pstr==null)                                            //~va15I~
        	return Perr;                                           //~va15I~
        xkey=srchExtendedkeyByName(Pstr);                          //~va15I~
        if (xkey<0)                                                //~va15I~
        	return Perr;                                           //~va15I~
        if (Dump.Y) Dump.println("strToExtendedkeyNoModifier "+Pstr+",key="+Integer.toHexString(xkey));//~va15I~
    	return xkey;                                               //~va15I~
    }                                                              //~va15I~
//********                                                         //~va15I~
    private static int chkModifier(String Pstr)                           //~va15I~
    {                                                              //~va15I~
    	int ctr,offs=0,xkey=0,len;                                 //~va15I~
    //**************                                               //~va15I~
    	if (Pstr==null)                                            //~va15I~
        	return -1;                                             //~va15I~
        len=Pstr.length();                                           //~va15I~
        ctr=SmodifierNameShort.length;                           //~va15I~
        for (int jj=0;jj<ctr;jj++)                                     //~va15I~
        {                                                          //~va15I~
            int ii;
        	for (ii=0;ii<ctr;ii++)                                 //~va15I~
            {                                                      //~va15I~
                if (!Pstr.startsWith(SmodifierNameShort[ii],offs))  //~va15I~
                    continue;                                      //~va15I~
                xkey|=SmodifierId[ii];                             //~va15I~
                offs+=SmodifierNameShort[ii].length();               //~va15I~
                break;                                             //~va15I~
            }                                                      //~va15I~
            if (ii==ctr)	//not found                            //~va15I~
            	break;                                             //~va15I~
            if (offs>=len)                                         //~va15I~
            	break;                                             //~va15I~
        }                                                          //~va15I~
        xkey|=offs;                                                //~va15I~
        if (Dump.Y) Dump.println("chkModifier "+Pstr+",key="+Integer.toHexString(xkey));//~va15I~
        return xkey;                                               //~va15I~
    }                                                              //~va15I~
//********                                                         //~va15I~
    public static int srchExtendedkey(int Pkey)                               //~va15I~
    {                                                              //~va15I~
        KeyData kd;                                                //~va15I~
    //*******************                                          //~va15I~
        int xkey=Pkey & SCANCHAR;                              //~va15I~
        for (int ii=0;ii<KDTBLSZ;ii++)                             //~1609R~
        {                                                          //~va15I~
        	kd=SkdTbl[ii];                                         //~va15I~
			if (xkey==kd.keyGDK)                                   //~va15R~
			{
				if (Dump.Y) Dump.println("srchExtendedKey "+Integer.toHexString(Pkey)+"="+kd.keyName);//~va15R~
            	return ii;
			}//~va15I~
        }                                                          //~va15I~
        if (Dump.Y) Dump.println("srchExtendedKey not found "+Integer.toHexString(Pkey));//~va15I~
        return KeyData.NOT_DEFINED;                                //~1609R~
    }//~va15I~
//********                                                         //~va15I~
    public static int srchExtendedkeyByName(String Pkeynm)         //~va15I~
    {                                                              //~va15I~
        int sz;                                                    //~va15I~
        KeyData kd;                                                //~va15I~
    //*******************                                          //~va15I~
        sz=KDTBLSZ;                                //~va15I~
        for (int ii=0;ii<sz;ii++)                                  //~va15I~
        {                                                          //~va15I~
        	kd=SkdTbl[ii];                                         //~va15I~
			if (Pkeynm.equals(kd.keyName))                         //~va15R~
			{
				if (Dump.Y) Dump.println("srchExtendedKeyByName "+Integer.toHexString(kd.keyCode)+"="+Pkeynm);				
			   	return kd.keyGDK;                                  //~va15R~
			}//~va15R~
        }                                                          //~va15I~
        if (Dump.Y) Dump.println("srchExtendedKeyByName not found "+Pkeynm);//~va15I~
        return -1;                                                 //~va15I~
    }                                                              //~va15I~
//************                                                     //~va15I~
    public static KeyData getKeyData(int Pidx)                     //~1609R~
    {                                                              //~va15I~
        if (Pidx<0||Pidx>=SkdTbl.length)                           //~va15R~
        	return kdError;                                        //~1609R~
        return SkdTbl[Pidx];                                       //~1609R~
    }                                                              //~va15I~
//***************************************************              //~1613R~
//*spinner data from ext key except direction key                  //~1613I~
//***************************************************              //~1613I~
    public static int setUserKeyCandidate(String[] Pspinnerdata,int Ppos)//~1613R~
    {                                                              //~1613I~
        int keyGDK,ctr=0;                                          //~1613R~
        KeyData kd;                                                //~1613I~
    //*******************                                          //~1613I~
        for (int ii=0;ii<KDTBLSZ;ii++)                             //~1613I~
        {                                                          //~1613I~
        	kd=SkdTbl[ii];                                         //~1613I~
        	keyGDK=kd.keyGDK;                                      //~1613I~
			if (!isValidExtGDK(keyGDK)                    //~1613R~
			||  kd.isButtonKey                                     //~1613I~
            )                                                      //~1613I~
            	continue;                                          //~1613I~
            if (Pspinnerdata!=null)                                //~1613I~
	            Pspinnerdata[ctr+Ppos]=kd.keyName;                      //~1613R~
            ctr++;                                                 //~1613R~
        }                                                          //~1613I~
        if (Dump.Y) Dump.println("getUserKeyCandidate ctr="+ctr);  //~1613R~
        return ctr;                                                //~1613R~
    }                                                              //~1613I~
//***************************************************              //~1613I~
//*spinner data from ext key except direction key                  //~1613I~
//***************************************************              //~1613I~
    public static int getUserKeyPos(int PuserGDK)                  //~1613I~
    {                                                              //~1613I~
        int keyGDK,ctr=0,ii;                                       //~1613R~
        KeyData kd;                                                //~1613I~
    //*******************                                          //~1613I~
        for (ii=0;ii<KDTBLSZ;ii++)                                 //~1613R~
        {                                                          //~1613I~
        	kd=SkdTbl[ii];                                         //~1613I~
        	keyGDK=kd.keyGDK;                                      //~1613I~
			if (!isValidExtGDK(keyGDK)                            //~1613I~
			||  kd.isButtonKey                                     //~1613R~
            )                                                      //~1613I~
            	continue;                                          //~1613I~
            if (keyGDK==PuserGDK)                                  //~1613I~
	            break;                                             //~1613I~
            ctr++;                                                 //~1613I~
        }                                                          //~1613I~
        if (ii==KDTBLSZ)	//not found                            //~1613I~
        	ctr=0;                                                 //~1613I~
        if (Dump.Y) Dump.println("getUserKeyPos pos="+ctr+",GDK="+Integer.toHexString(PuserGDK));//~1613I~
        return ctr;                                                //~1613I~
    }                                                              //~1613I~
//***************************************************              //~1613I~
//*spinner data from ext key except direction key                  //~1613I~
//***************************************************              //~1613I~
    public static KeyData getUserGDK(int Ppos)                     //~1613R~
    {                                                              //~1613I~
        int keyGDK=0,ctr=0,ii;                                     //~1613I~
        KeyData kd;                                                //~1613I~
    //*******************                                          //~1613I~
        for (ii=0;ii<KDTBLSZ;ii++)                                 //~1613I~
        {                                                          //~1613I~
        	kd=SkdTbl[ii];                                         //~1613I~
        	keyGDK=kd.keyGDK;                                      //~1613I~
			if (!isValidExtGDK(keyGDK)                            //~1613I~
			||  kd.isButtonKey                                     //~1613I~
            )                                                      //~1613I~
            	continue;                                          //~1613I~
            if (ctr==Ppos)                                         //~1613I~
	            break;                                             //~1613I~
            ctr++;                                                 //~1613I~
        }                                                          //~1613I~
        if (ii==KDTBLSZ)	//not found                            //~1613I~
        	ii=0;                                                  //~1613R~
        if (Dump.Y) Dump.println("getUserGDK pos="+Ppos+",GDK="+Integer.toHexString(keyGDK));//~1613I~
        return SkdTbl[ii];                                     //~1613R~
    }                                                              //~1613I~
//************                                                     //~va15I~
    public static boolean isUnicodeKeyValue(int Pkey)                          //~va15I~
    {                                                              //~va15I~
        return (Pkey>=0 && (Pkey & KBF_UNICODE)!=0);               //~va15I~
    }                                                              //~va15I~
//************                                                     //~va15I~
    public static int getUnicodeKeyValue(int Pkey)                         //~va15I~
    {                                                              //~va15I~
        return (Pkey & ~KBF_UNICODE);                           //~va15I~
    }
//*************
    public static int setUnicodeId(int Pkey)                         //~va15I~
    {                                                              //~va15I~
        return (Pkey | KBF_UNICODE);                           //~va15I~
    }                                                                                                 //~va15I~
//*************                                                    //~va15I~
    public static int metaToMod(int Pmetastate)                    //~va15R~
    {                                                              //~va15I~
    	int mod=0;                                                 //~va15I~
		if ((Pmetastate & META_SHIFT)!=0)                               //~va15I~
        	mod|=KBF_SHIFT;                                     //~va15I~
		if ((Pmetastate & META_ALT_R)!=0)                          //~vc23I~
        	mod|=KBF_ALTGR;                                        //~vc23I~
		if ((Pmetastate & META_ALT)!=0)                                 //~va15I~
        	mod|=KBF_ALT;                                       //~va15I~
		if ((Pmetastate & META_CTL)!=0)                                 //~va15I~
        	mod|=KBF_CONTROL;                                   //~va15I~
		if ((Pmetastate & META_CTL_R)!=0)                               //~va15I~
        	mod|=KBF_RIGHTCONTROL;                              //~va15I~
        if (Dump.Y) Dump.println("AxeKeyValue.metaToMod meta="+Integer.toHexString(Pmetastate)+",mod="+Integer.toHexString(mod));//~va15I~//+vc2jR~
        return mod;                                                //~va15I~
    }                                                              //~va15I~
//*************                                                    //~va15I~
    public static int keycodeToExtkey(int Pkeycode)                  //~va15I~
    {                                                              //~va15I~
    	int extkey;                                                //~va15I~
    	if (Pkeycode<0||Pkeycode>=KeyData.MAXKEYCODE)              //~1815R~
        	extkey=KeyData.NOT_DEFINED;                            //~1609R~
        else                                                       //~va15I~
        	extkey=SkeycodeToExtkeyTbl[Pkeycode];                  //~va15I~
        if (Dump.Y) Dump.println("keycodeToExtkey keycode="+Integer.toHexString(Pkeycode)+",extkey="+Integer.toHexString(extkey));//~va15I~
        return extkey;                                             //~va15I~
    }                                                              //~va15I~
//*************                                                    //~1609I~
    public static boolean isExtendedKey(int Pkeycode)            //~1609I~
    {                                                              //~1609I~
        boolean rc;                                                //~1609I~
    	if (Pkeycode<0||Pkeycode>=KeyData.MAXKEYCODE)              //~1815R~
        	rc=false;                                              //~1609I~
        else                                                       //~1609I~
        	rc=SkeycodeToExtkeyTbl[Pkeycode]!=KeyData.NOT_DEFINED; //~1609I~
        if (Dump.Y) Dump.println("AxeKeyValue.isExtendedKey keycode="+Integer.toHexString(Pkeycode)+"="+rc);//~1609I~//~vc2jR~
        return rc;                                                 //~1609I~
    }                                                              //~1609I~
//*************                                                    //~1B06I~
    public static String getExtendedKeyname(int Pkeycode)         //~vc2jI~
    {                                                              //~vc2jI~
    	String rc=null;                                            //~vc2jI~
	    if (isExtendedKey(Pkeycode))                                //~vc2jI~
        	rc=SkeycodeToExtkeyNameTbl[Pkeycode];                  //~vc2jI~
        if (Dump.Y) Dump.println("AxeKeyvalue.getExtendedKeyname keycode="+Integer.toHexString(Pkeycode)+"="+rc);//~vc2jR~
        return rc;                                                 //~vc2jI~
    }                                                              //~vc2jI~
//*************                                                    //~vc2jI~
    public static boolean isRepeatableExtKey(int Pkeycode)         //~1B06I~
    {                                                              //~1B06I~
        boolean rc;                                                //~1B06I~
	    rc=(                                                       //~1B06I~
                Pkeycode==GDK_Tab                                  //~1B06I~
             || Pkeycode==GDK_BackSpace                            //~1B06I~
             || Pkeycode==GDK_Return                               //~1B06I~
             || Pkeycode==GDK_Page_Up                              //~1B06I~
             || Pkeycode==GDK_Page_Down                            //~1B06I~
             || Pkeycode==GDK_Delete                               //~1B06I~
             || Pkeycode==GDK_Left                                 //~1B06I~
             || Pkeycode==GDK_Right                                //~1B06I~
             || Pkeycode==GDK_Up                                   //~1B06I~
             || Pkeycode==GDK_Down                                 //~1B06I~
             || Pkeycode==GDK_KP_Enter                             //~1B06I~
           );                                                       //~1B06I~
        if (Dump.Y) Dump.println("AxeKeyvalue:isRepeatableExtKey keycode="+Integer.toHexString(Pkeycode)+"="+rc);//~1B06I~
        return rc;                                                 //~1B06I~
    }                                                              //~1B06I~
//*************                                                    //~1B06I~
    public static boolean isRepeatableExtKeyForSoftKbd(int Pkeycode)//~1B06I~
    {                                                              //~1B06I~
        boolean rc;                                                //~1B06I~
	    rc=(                                                       //~1B06I~
                Pkeycode==GDK_Tab                                  //~1B06I~
             || Pkeycode==GDK_BackSpace                            //~1B06I~
             || Pkeycode==GDK_Delete                               //~1B06I~
             || Pkeycode==GDK_Left                                 //~1B06I~
             || Pkeycode==GDK_Right                                //~1B06I~
             || Pkeycode==GDK_Up                                   //~1B06I~
             || Pkeycode==GDK_Down                                 //~1B06I~
           );                                                       //~1B06I~
        if (Dump.Y) Dump.println("AxeKeyvalue:isRepeatableExtKeyForSoftKbd keycode="+Integer.toHexString(Pkeycode)+"="+rc);//~1B06I~
        return rc;                                                 //~1B06I~
    }                                                              //~1B06I~
//*************                                                    //~1613I~
    public static boolean isValidExtGDK(int Pkeycode)              //~1616R~
    {                                                              //~1613I~
    	return Pkeycode>0 && (Pkeycode & EXTKEY_MASK)==EXTKEY_MASK;//~1815R~
    }                                                              //~1613I~
//*************                                                    //~vc2jI~
    public static int getIMKeyID(int Pkeycode)                     //~vc2jI~
    {                                                              //~vc2jI~
    	int rc=0;                                                  //~vc2jI~
        for (int ii=0;ii<SkeycodeIM.length;ii++)                   //~vc2jI~
        	if (Pkeycode==SkeycodeIM[ii])                          //~vc2jI~
            {                                                      //~vc2jI~
            	rc=SkeycodeIMStrID[ii];                            //~vc2jI~
                break;                                             //~vc2jI~
            }                                                      //~vc2jI~
        if (Dump.Y) Dump.println("AxeKeyvalue.getIMKeyID keycode="+Integer.toHexString(Pkeycode)+",rc="+Integer.toHexString(rc));//~vc2jI~
    	return rc;                                                 //~vc2jI~
    }                                                              //~vc2jI~
}//class                                                           //~va15R~
