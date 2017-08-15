//*CID://+vaawR~:                             update#=    2;       //~vaawI~
//******************************************************************//~vaawI~
//vaaw:120105 (Axe)add Fn modifier to set key label to Fn          //~vaawI~
//******************************************************************//~vaawI~
package com.xe.Axe.kbd;                                            //~1918R~


import com.xe.Axe.AxeKeyValue;
import com.xe.Axe.Dump;
import com.xe.Axe.kbd.ims.Keyboard;
import com.xe.Axe.kbd.ims.Keyboard.Key;
import com.xe.Axe.KeyData;

public class AxeKbdKey                                             //~1918R~
{                                                                  //~1911I~
    public static class FlickKey                                   //~1922I~
    {                                                              //~1922I~
        public String   name;	      //extended key name          //~1922I~
        public int      code;         //primarycode                //~1922I~
        public boolean  repeatable;   //save repeatable value of xml(ignore longpress)//~1A09R~
                                                                   //~1922I~
//        public FlickKey(String Pname,int Pcode,boolean Prepeatable)//~1922R~
        public FlickKey(String Pname,int Pcode)                    //~1922I~
        {                                                          //~1922I~
            name=Pname;                                            //~1922I~
            code=Pcode;                                            //~1922I~
//            repeatable=Prepeatable;                              //~1922R~
        }                                                          //~1922I~
    }                                                              //~1922I~
    public static final int KEYCODE_SHIFT=Keyboard.KEYCODE_SHIFT;  //~1919M~
    public static final int KEYCODE_ALT=Keyboard.KEYCODE_ALT;      //~1919M~
    public static final int KEYCODE_KBD=Keyboard.KEYCODE_MODE_CHANGE;//~1919M~
    public static final int KEYCODE_BACKSPACE=AxeKeyValue.GDK_BackSpace;//~1919M~
    public static final int KEYCODE_ALTGR=-10;                     //~1919M~
    public static final int KEYCODE_ALTGRS=-11;                    //~1919M~
    public static final int KEYCODE_SHIFTR=-12;                    //~1919M~
    public static final int KEYCODE_CTRL=-13;//ffe3                //~1919M~
    public static final int KEYCODE_KBDPOPUP=-14;	//not repeatable but popup swipe code//~1922I~
    public static final int KEYCODE_KBDNOPOP=-15;   //back to repeatable//~1A07R~
    public static final int KEYCODE_CAPS=-16;                      //~1A07I~
//    public static final int KEYCODE_BACKTAB=-17;                 //~1A09R~
    public static final int KEYCODE_SHORTCUT=-17;                  //~1A09I~
    public static final int KEYCODE_SHIFTF=-18;     //display key:Fn(GdkKey)//+vaawR~
                                                                   //~1919I~
    public static final String KEYLBL_SWKBD ="IM";                 //~1922R~
    public static final String KEYLBL_SWKBDPOPUP="Pop";            //~1922I~
    public static final String KEYLBL_SWKBDNOPOP="NoP";            //~1923I~
    public static final String KEYLBL_FN="Fn";                     //~vaawI~
    public static final int EXTKEYIDCHAR='e';	//may not appear as char by Shift,AltGr                     //~1911I~//~1920M~
    public static final int EXTKEYNAME_STARTER='e';                //~1920M~
    public static final int EXTKEYNAME_STOPPER=';';                //~1920M~
                                                                   //~1920M~
//*popup char spcification                                         //~1A09R~
    private static final String[] SextendedkeyName=                //~1A12R~
        {                                                          //~1919M~
            AxeKeyValue.KEYLBL_SPACE               ,               //~1A12I~
            AxeKeyValue.KEYLBL_ESCAPE              ,               //~1919I~
            AxeKeyValue.KEYLBL_TAB                 ,               //~1919I~
            AxeKeyValue.KEYLBL_DEL                 ,               //~1919I~
            AxeKeyValue.KEYLBL_ENTER               ,               //~1919I~
            AxeKeyValue.KEYLBL_INSERT              ,               //~1919I~
            AxeKeyValue.KEYLBL_FORWARD_DEL         ,               //~1919I~
            AxeKeyValue.KEYLBL_MOVE_HOME           ,               //~1919I~
            AxeKeyValue.KEYLBL_MOVE_END            ,               //~1919I~
            AxeKeyValue.KEYLBL_PAGE_UP             ,               //~1919I~
            AxeKeyValue.KEYLBL_PAGE_DOWN           ,               //~1919I~
            AxeKeyValue.KEYLBL_DPAD_UP             ,               //~1919I~
            AxeKeyValue.KEYLBL_DPAD_DOWN           ,               //~1919I~
            AxeKeyValue.KEYLBL_DPAD_LEFT           ,               //~1919I~
            AxeKeyValue.KEYLBL_DPAD_RIGHT          ,               //~1919I~
            AxeKeyValue.KEYLBL_F1                  ,               //~1919I~
            AxeKeyValue.KEYLBL_F2                  ,               //~1919I~
            AxeKeyValue.KEYLBL_F3                  ,               //~1919I~
            AxeKeyValue.KEYLBL_F4                  ,               //~1919I~
            AxeKeyValue.KEYLBL_F5                  ,               //~1919I~
            AxeKeyValue.KEYLBL_F6                  ,               //~1919I~
            AxeKeyValue.KEYLBL_F7                  ,               //~1919I~
            AxeKeyValue.KEYLBL_F8                  ,               //~1919I~
            AxeKeyValue.KEYLBL_F9                  ,               //~1919I~
            AxeKeyValue.KEYLBL_F10                 ,               //~1919I~
            AxeKeyValue.KEYLBL_F11                 ,               //~1919I~
            AxeKeyValue.KEYLBL_F12                 ,               //~1919I~
                                                                   //~1919I~
            AxeKeyValue.KEYLBL_ALT                 ,               //~1919I~
            AxeKeyValue.KEYLBL_ALTR                ,               //~1919I~
            AxeKeyValue.KEYLBL_ALTRS               ,               //~1919I~
            AxeKeyValue.KEYLBL_CTL                 ,               //~1919I~
//            AxeKeyValue.KEYLBL_CTLR                ,             //~1919R~
            AxeKeyValue.KEYLBL_SHIFT               ,               //~1919I~
            AxeKeyValue.KEYLBL_CAPS                ,               //~1A07I~
            AxeKeyValue.KEYLBL_SHIFTR              ,               //~1919I~
                        KEYLBL_SWKBD               ,               //~1922R~
                        KEYLBL_SWKBDPOPUP          ,               //~1923R~
                        KEYLBL_SWKBDNOPOP          ,               //~1A09R~
            AxeKeyValue.KEYLBL_SHORTCUT            ,               //~1A09I~
                        KEYLBL_FN                  ,               //~vaawI~
        };                                                         //~1919M~
	private static final int[]    SextendedkeyIdTbl=               //~1A12R~
    	{                                                          //~1919M~
            ' '                     ,	//Space                    //~1A12I~
            AxeKeyValue.GDK_Escape  ,                              //~1919I~
            AxeKeyValue.GDK_Tab     ,                              //~1919I~
//                        KEYCODE_BACKTAB, //-6     GDK_Alt_L   ,      //ffe9//~1A09R~
            AxeKeyValue.GDK_BackSpace,                             //~1919I~
            AxeKeyValue.GDK_Return  ,                              //~1919I~
            AxeKeyValue.GDK_Insert  ,                              //~1919I~
            AxeKeyValue.GDK_Delete  ,                              //~1919I~
            AxeKeyValue.GDK_Home    ,                              //~1919I~
            AxeKeyValue.GDK_End     ,                              //~1919I~
            AxeKeyValue.GDK_Page_Up ,                              //~1919I~
            AxeKeyValue.GDK_Page_Down,                             //~1919I~
            AxeKeyValue.GDK_Up      ,                              //~1919I~
            AxeKeyValue.GDK_Down    ,                              //~1919I~
            AxeKeyValue.GDK_Left    ,                              //~1919I~
            AxeKeyValue.GDK_Right   ,                              //~1919I~
            AxeKeyValue.GDK_F1      ,                              //~1919I~
            AxeKeyValue.GDK_F2      ,                              //~1919I~
            AxeKeyValue.GDK_F3      ,                              //~1919I~
            AxeKeyValue.GDK_F4      ,                              //~1919I~
            AxeKeyValue.GDK_F5      ,                              //~1919I~
            AxeKeyValue.GDK_F6      ,                              //~1919I~
            AxeKeyValue.GDK_F7      ,                              //~1919I~
            AxeKeyValue.GDK_F8      ,                              //~1919I~
            AxeKeyValue.GDK_F9      ,                              //~1919I~
            AxeKeyValue.GDK_F10     ,                              //~1919I~
            AxeKeyValue.GDK_F11     ,                              //~1919I~
            AxeKeyValue.GDK_F12     ,                              //~1919I~
                                                                   //~1919I~
                        KEYCODE_ALT, //-6     GDK_Alt_L   ,      //ffe9//~1919I~
                        KEYCODE_ALTGR,      //-10 ffea             //~1919I~
                        KEYCODE_ALTGRS,      //-11 ffea            //~1919I~
                        KEYCODE_CTRL,      //-13                   //~1919I~
//            AxeKeyValue.GDK_Control_R,     //ffe4                //~1919R~
                        KEYCODE_SHIFT, //-1                        //~1919I~
                        KEYCODE_CAPS,   //-16                      //~1A07I~
                        KEYCODE_SHIFTR,  //-12                     //~1919I~
                        KEYCODE_KBD,           //-2                //~1919I~
                        KEYCODE_KBDPOPUP,      //-14               //~1922I~
                        KEYCODE_KBDNOPOP,      //-15               //~1923I~
                        KEYCODE_SHORTCUT,      //-17               //~1A09I~
                        KEYCODE_SHIFTF  ,      //-18               //+vaawR~
       };                                                          //~1919M~
//*assign to key(kbd layout)                                       //~1A09I~
    public static final String[] SspinnerData=                     //~1919I~
        {                                                          //~1919I~
    		"(Not Use)",                                           //~1919I~
            AxeKeyValue.KEYLBL_ESCAPE              ,               //~1919I~
            AxeKeyValue.KEYLBL_TAB                 ,               //~1919I~
//            AxeKeyValue.KEYLBL_BACKTAB             ,             //~1A09R~
            AxeKeyValue.KEYLBL_DEL                 ,               //~1919I~
            AxeKeyValue.KEYLBL_ENTER               ,               //~1919I~
            AxeKeyValue.KEYLBL_INSERT              ,               //~1919I~
            AxeKeyValue.KEYLBL_FORWARD_DEL         ,               //~1919I~
            AxeKeyValue.KEYLBL_MOVE_HOME           ,               //~1919I~
            AxeKeyValue.KEYLBL_MOVE_END            ,               //~1919I~
            AxeKeyValue.KEYLBL_PAGE_UP             ,               //~1919I~
            AxeKeyValue.KEYLBL_PAGE_DOWN           ,               //~1919I~
            AxeKeyValue.KEYLBL_DPAD_UP             ,               //~1919I~
            AxeKeyValue.KEYLBL_DPAD_DOWN           ,               //~1919I~
            AxeKeyValue.KEYLBL_DPAD_LEFT           ,               //~1919I~
            AxeKeyValue.KEYLBL_DPAD_RIGHT          ,               //~1919I~
            AxeKeyValue.KEYLBL_F1                  ,               //~1919I~
            AxeKeyValue.KEYLBL_F2                  ,               //~1919I~
            AxeKeyValue.KEYLBL_F3                  ,               //~1919I~
            AxeKeyValue.KEYLBL_F4                  ,               //~1919I~
            AxeKeyValue.KEYLBL_F5                  ,               //~1919I~
            AxeKeyValue.KEYLBL_F6                  ,               //~1919I~
            AxeKeyValue.KEYLBL_F7                  ,               //~1919I~
            AxeKeyValue.KEYLBL_F8                  ,               //~1919I~
            AxeKeyValue.KEYLBL_F9                  ,               //~1919I~
            AxeKeyValue.KEYLBL_F10                 ,               //~1919I~
            AxeKeyValue.KEYLBL_F11                 ,               //~1919I~
            AxeKeyValue.KEYLBL_F12                 ,               //~1919I~
        };                                                         //~1919I~
      public static final int[]    SspinnerCode=                   //~1919I~
    	{                                                          //~1919I~
    			KeyData.NOT_DEFINED,         //-1                  //~1919I~
            AxeKeyValue.GDK_Escape  ,                              //~1919I~
            AxeKeyValue.GDK_Tab     ,                              //~1919I~
            AxeKeyValue.GDK_BackSpace,                             //~1919I~
            AxeKeyValue.GDK_Return  ,                              //~1919I~
            AxeKeyValue.GDK_Insert  ,                              //~1919I~
            AxeKeyValue.GDK_Delete  ,                              //~1919I~
            AxeKeyValue.GDK_Home    ,                              //~1919I~
            AxeKeyValue.GDK_End     ,                              //~1919I~
            AxeKeyValue.GDK_Page_Up ,                              //~1919I~
            AxeKeyValue.GDK_Page_Down,                             //~1919I~
            AxeKeyValue.GDK_Up      ,                              //~1919I~
            AxeKeyValue.GDK_Down    ,                              //~1919I~
            AxeKeyValue.GDK_Left    ,                              //~1919I~
            AxeKeyValue.GDK_Right   ,                              //~1919I~
            AxeKeyValue.GDK_F1      ,                              //~1919I~
            AxeKeyValue.GDK_F2      ,                              //~1919I~
            AxeKeyValue.GDK_F3      ,                              //~1919I~
            AxeKeyValue.GDK_F4      ,                              //~1919I~
            AxeKeyValue.GDK_F5      ,                              //~1919I~
            AxeKeyValue.GDK_F6      ,                              //~1919I~
            AxeKeyValue.GDK_F7      ,                              //~1919I~
            AxeKeyValue.GDK_F8      ,                              //~1919I~
            AxeKeyValue.GDK_F9      ,                              //~1919I~
            AxeKeyValue.GDK_F10     ,                              //~1919I~
            AxeKeyValue.GDK_F11     ,                              //~1919I~
            AxeKeyValue.GDK_F12     ,                              //~1919I~
       };                                                          //~1919I~
    //**********************                                       //~1920I~
    private static  StringBuffer sb=new StringBuffer();            //~1920M~
    public static int extkeynamelen;                               //~1920M~
//    public static boolean isRepeatable;                          //~1922R~
                                                                   //~1919M~
                                                                   //~1918I~
//    public static CharSequence transPopupCharacters(Key Pkey,int Pmaxdest,int[] Pcodes,String[] PextkeyName)//~1922R~
    public static CharSequence transPopupCharacters(Key Pkey,int Pmaxdest,FlickKey [] PflickKey)//~1922I~
    {                                                              //~1916I~
    	int primarycode=Pkey.codes[0];                             //~1916I~
        String old=(String)Pkey.popupCharacters;                          //~1916I~
        String newstr=null;                                        //~1922I~
        String extkeyname;                                         //~1922I~
        sb.setLength(0);                                           //~1916I~
        int jj=0;                                                  //~1916I~
        if (old!=null)                                             //~1916I~
        {                                                          //~1916I~
            for (int kk=0;kk<old.length() && jj<=Pmaxdest;kk++,jj++)//~1916I~
            {                                                      //~1916I~
//                PextkeyName[jj]=null;                            //~1922R~
                extkeyname=null;                                   //~1922I~
                int ch=old.charAt(kk);                             //~1916I~
                if (ch==' ')                                       //~1916I~
                {                                                  //~1916I~
                    sb.append((char)ch);                           //~1916I~
                    ch=0;	//keycode tbl value;                   //~1916I~
                }                                                  //~1916I~
                else                                               //~1916I~
                if (ch==EXTKEYNAME_STARTER && !(primarycode==EXTKEYNAME_STARTER && kk==0))//ext key length//~1A12R~
                {                                                  //~1916I~
                 	ch=getExtKeycode(old,kk+1);	//code and name length//~1916I~
                    int keynamelen=extkeynamelen;                  //~1916I~
                    if (keynamelen>1)                              //~1916I~
                    {                                              //~1916I~
//                        PextkeyName[jj]=old.substring(kk+1,kk+keynamelen);//~1922R~
                        extkeyname=old.substring(kk+1,kk+keynamelen);//~1922I~
	                    kk+=keynamelen;                            //~1916I~
	                    sb.append((char)AxeKbdKey.EXTKEYIDCHAR);   //~1918R~
                    }                                              //~1916I~
                    else                                           //~1916I~
	                    sb.append((char)ch);                       //~1916I~
                }                                                  //~1916I~
                else                                               //~1916I~
                {                                                  //~1916I~
                    sb.append((char)ch);                           //~1916I~
                }                                                  //~1916I~
//                Pcodes[jj]=ch;                                   //~1922R~
//                PflickKey[jj]=new FlickKey(extkeyname,ch,isRepeatable);//~1922R~
	            PflickKey[jj]=new FlickKey(extkeyname,ch);         //~1922I~
            }                                                      //~1916I~
	        newstr=sb.toString();                                  //~1922I~
        }                                                          //~1916I~
        for (;jj<=Pmaxdest;jj++)                                   //~1916I~
        {                                                          //~1916I~
//            Pcodes[jj]=0;                                        //~1922R~
//            PextkeyName[jj]=null;                                //~1922R~
              PflickKey[jj]=new FlickKey(null,0);            //~1922I~
        }
        return newstr;                                             //~1922R~
    }                                                              //~1916M~
    public static int getExtKeycode(String Ppopup,int Ppos)        //~1916R~
    {                                                              //~1911I~
    	int code=0;                                                //~1916R~
    	int endpos=Ppopup.indexOf(EXTKEYNAME_STOPPER,Ppos);        //~1911I~
//        isRepeatable=false;                                      //~1922R~
        if (endpos>0)                                              //~1911I~
        {                                                          //~1911I~
    		String name=Ppopup.substring(Ppos,endpos);             //~1911I~
        	int ctr=SextendedkeyName.length;                       //~1911I~
        	for (int ii=0;ii<ctr;ii++)                             //~1922R~
        		if (name.equals(SextendedkeyName[ii]))             //~1911I~
                {                                                  //~1922I~
//                    isRepeatable=SextendedkeyRepeatable[ii];     //~1922R~
            		code=SextendedkeyIdTbl[ii];                    //~1911I~
                }                                                  //~1922I~
            if (code==0)                                           //~1916R~
            {                                                      //~1911I~
            	code=EXTKEYNAME_STARTER;	//eat 1 byte           //~1911I~
	        	endpos=Ppos;                                       //~1911I~
//                isRepeatable=false;                              //~1922R~
            }                                                      //~1911I~
            else                                                   //~1911I~
            {                                                      //~1922I~
            	endpos++;                                          //~1911I~
            }                                                      //~1922I~
        }                                                          //~1911I~
        else                                                       //~1911I~
        	endpos=Ppos;                                           //~1911I~
		if (Dump.Y) Dump.println("MyKbdView:sgetAxeKbdKeyCpde popup="+Ppopup+",code="+Integer.toHexString(code)+",charCast="+(char)code);//~1918R~
        extkeynamelen=endpos-Ppos;                                 //~1911I~
        return code;                                               //~1916R~
    }                                                              //~1911I~
//    public static CharSequence getAxeKbdKeyLabel(CharSequence Ppopup,CharSequence Plabel,int Ppos)//~1920R~
//    {                                                            //~1920R~
//        String extkeyname=null;                                  //~1920R~
//        String old=Ppopup.toString();                            //~1920R~
//        int cslen=Plabel.length();                               //~1920R~
////      test(old);                                               //~1920R~
//        for (int ii=0,jj=0;ii<cslen;ii++,jj++)                   //~1920R~
//        {                                                        //~1920R~
//            if (Plabel.charAt(ii)==EXTKEYIDCHAR                  //~1920R~
//            &&  Ppopup.charAt(jj)==EXTKEYNAME_STARTER)           //~1920R~
//            {                                                    //~1920R~
//                int endpos=old.indexOf(EXTKEYNAME_STOPPER,ii+1); //~1920R~
//                if (ii==Ppos)                                    //~1920R~
//                {                                                //~1920R~
//                    extkeyname=old.substring(ii+1,endpos);       //~1920R~
//                    break;                                       //~1920R~
//                }                                                //~1920R~
//                else                                             //~1920R~
//                    jj=endpos;                                   //~1920R~
//            }                                                    //~1920R~
//        }                                                        //~1920R~
//        if (Dump.Y) Dump.println("AxeKbdKey:getAxeKbdKeyLabel="+extkeyname);//~1920R~
//        return extkeyname;                                       //~1920R~
//    }                                                            //~1920R~
    public static int getSpinnerIndex(int Pcode)                          //~1919I~
    {                                                              //~1919I~
    	int sz=SspinnerCode.length;                                    //~1919I~
    	for (int ii=0;ii<sz;ii++)                                      //~1919I~
        {                                                          //~1919I~
        	if (Pcode==SspinnerCode[ii])                           //~1919I~
            	return ii;                                         //~1919I~
        }                                                          //~1919I~
        return 0;	//not defined                                  //~1919I~
    }                                                              //~1919I~
    public static int getSpinnerIndex(String Pname)                       //~1919I~
    {                                                              //~1919I~
    	int sz=SspinnerData.length;                                    //~1919I~
    	for (int ii=0;ii<sz;ii++)                                      //~1919I~
        {                                                          //~1919I~
        	if (Pname.equalsIgnoreCase(SspinnerData[ii]))          //~1920R~
            	return ii;                                         //~1919I~
        }                                                          //~1919I~
        return 0;	//not defined                                  //~1919I~
    }                                                              //~1919I~
//    public static boolean isRepeatableKey(int Pcode)             //~1A09R~
//    {                                                            //~1A09R~
//        return !(   Pcode==Keyboard.KEYCODE_SHIFT            //-1//~1A09R~
//                ||  Pcode==Keyboard.KEYCODE_MODE_CHANGE      //-2(Kbd)//~1A09R~
//                ||  Pcode==Keyboard.KEYCODE_ALT              //-6//~1A09R~
//                ||  Pcode==KEYCODE_CTRL                      //=-13;//ffe3//~1A09R~
//        );                                                       //~1A09R~
//    }                                                            //~1A09R~
}//class                                                           //~1911I~
