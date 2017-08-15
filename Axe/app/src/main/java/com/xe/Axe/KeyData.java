//CID://+DATER~:                                                   //~1609R~
//*************************************************************    //~va15I~
//*************************************************************    //~va15I~
package com.xe.Axe;                                                //~va15I~

import android.view.KeyEvent;

public class KeyData                                               //~1609R~
{                                                                  //~1609R~
 	public static final int NOT_DEFINED =-1;                       //~1609M~
                                                                   //~1609I~
    public static final int KEYCODE_PAGE_UP         =0x5c; //  92    API level 9//~1609R~
    public static final int KEYCODE_PAGE_DOWN       =0x5d; //  93    API level 9//~1609R~
    public static final int KEYCODE_ESCAPE          =0x6f; // 111    API level 11//~1609R~
    public static final int KEYCODE_FORWARD_DEL     =0x70; // 112    API level 11//~1811I~
    public static final int KEYCODE_MOVE_HOME       =0x7a; // 122    API level 11//~1609R~
    public static final int KEYCODE_MOVE_END        =0x7b; // 123    API level 11//~1609R~
    public static final int KEYCODE_INSERT          =0x7c; // 124    API level 11//~1609R~
    public static final int KEYCODE_F1              =0x83; // 131    API level 11//~1609R~
    public static final int KEYCODE_F2              =0x84; // 132    API level 11//~1609R~
    public static final int KEYCODE_F3              =0x85; // 133    API level 11//~1609R~
    public static final int KEYCODE_F4              =0x86; // 134    API level 11//~1609R~
    public static final int KEYCODE_F5              =0x87; // 135    API level 11//~1609R~
    public static final int KEYCODE_F6              =0x88; // 136    API level 11//~1609R~
    public static final int KEYCODE_F7              =0x89; // 137    API level 11//~1609R~
    public static final int KEYCODE_F8              =0x8a; // 138    API level 11//~1609R~
    public static final int KEYCODE_F9              =0x8b; // 139    API level 11//~1609R~
    public static final int KEYCODE_F10             =0x8c; // 140    API level 11//~1609R~
    public static final int KEYCODE_F11             =0x8d; // 141    API level 11//~1609R~
    public static final int KEYCODE_F12             =0x8e; // 142    API level 11//~1609R~
    public static final int KEYCODE_NUMPAD_ENTER    =0xa0; // 160    API level 11//~1609R~
    public static final int KEYCODE_RCTL            =0x72; // 114    API level 11//~1810I~
    public static final int KEYCODE_ENTER           =KeyEvent.KEYCODE_ENTER;//0x42=66//~1831R~
    public static final int KEYCODE_CLICK           =KeyEvent.KEYCODE_BUTTON_L1;//       =0x66; // 102    API level 9; temporary use for Mouse click//+1A04R~
    public static final int KEYCODE_DBLCLICK        =KeyEvent.KEYCODE_BUTTON_L2;//       =0x68; // 104    API level 9; temporary use for Mouse dblclick//+1A04R~
    public static final int KEYCODE_DRAG            =KeyEvent.KEYCODE_BUTTON_R2;//       =0x69; // 105    API level 9; temporary use for Mouse drag//+1A04I~
    public static final int KEYCODE_RBTN            =KeyEvent.KEYCODE_BUTTON_R1;//       =0x67; // 103    API level 9; temporary use for Mouse Rbutton//+1A04R~
    public static final int MAXKEYCODE      =0xff; //for the future//~1815I~
                                                                   //~1609I~
    int    keyGDK;         //GDK key                               //~1609R~
    int    keyCode;        //android keycode                       //~1609R~
    String keyName;        //keyname to display keylist            //~1609R~
    int    defaultShortcut;                                        //~1609R~
    boolean isButtonKey;                                           //~1613I~
    public KeyData(int PGDK,int PCode,String Pname,int PSC,boolean Pbuttonkey)//~1613R~
    {                                                              //~1609R~
        keyGDK=PGDK; keyCode=PCode; keyName=Pname; defaultShortcut=PSC;//~1613R~
        isButtonKey=Pbuttonkey;                                    //~1613I~
    }                                                              //~1609R~
}                                                                  //~1609R~

