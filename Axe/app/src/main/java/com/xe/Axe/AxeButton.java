//CID://+vc2yR~:                                                   //~va15I~//~vc2yR~
//*************************************************************    //~va15I~
//vc2y 2020/08/11 update buttonupdate dialog                       //~vc2yI~
//va15:051112 if return maked data when multisolution detected,it is redundant;delete redundancy option add//~va15I~
//*************************************************************    //~va15I~
package com.xe.Axe;                                                //~va15I~

import android.view.View;
import android.widget.Button;

public class AxeButton                                             //~va15R~
{                                                                  //~va15I~
                                                                   //~va15I~
    public static final String LABEL_CTL       ="Ctrl";            //~va15R~
    public static final String LABEL_SHIFT     ="";                //~va15R~
    public static final String LABEL_ALT       ="Alt";             //~va15R~
    public static final String LABEL_CAPSLOCK  ="Caps";            //~va15R~
    public static final String LABEL_SHORTCUT  ="S.C";             //~va15R~
    public static final String LABEL_FREE      ="Free";            //~va15R~
    public static final String LABEL_USER      ="User";            //~va15I~
    public static final String LABEL_GONE      ="";                //~va15R~
    public static final String LABEL_NONE      ="";                //~va15R~
    public static final String LABEL_IM        ="Kbd";  //xekbd    //~va15R~
    public static final String LABEL_IMP       ="IM";	//with preedit line//~va15R~
    public static final String LABEL_RCTL      ="CtlR";            //~va15I~
    public static final String LABEL_ALTGR     ="AltG";            //~va15I~
                                                                   //~va15M~
    public static final int BUTTON_FREE      =0;                   //~va15R~
    public static final int BUTTON_IM        =1;                   //~va15I~
    public static final int BUTTON_IMP       =2;                   //~va15I~
    public static final int BUTTON_SHORTCUT  =3;                   //~va15R~
    public static final int BUTTON_CAPSLOCK  =4;                   //~va15R~
    public static final int BUTTON_SHIFT     =5;                   //~va15R~
    public static final int BUTTON_CTL       =6;                   //~va15R~
    public static final int BUTTON_ALT       =7;                   //~va15R~
    public static final int BUTTON_UP        =8;                   //~va15R~
    public static final int BUTTON_DOWN      =9;                   //~va15R~
    public static final int BUTTON_LEFT      =10;                  //~va15R~
    public static final int BUTTON_RIGHT     =11;                  //~va15R~
    public static final int BUTTON_RCTL      =12;                  //~va15R~
    public static final int BUTTON_ALTGR     =13;                  //~va15R~
    public static final int BUTTON_USER      =14;                  //~va15R~
    public static final int BUTTON_GONE      =15;  //GONE should be last for AxeDlgBtnUpdate createSpinnerData//~va15R~
    public static final int BUTTON_IDMAX     =15;                  //~va15R~
                                                                   //~va15I~
    public static final String[] buttonName={  //indexd by buttonId shown above//~va15R~
		"Free",                                                    //~va15I~
    	"Kbd",                                                     //~va15R~
    	"KbP",                                                      //~va15I~
    	"Shortcut",                                                 //~va15I~
		"CapsLock",                                                 //~va15I~
    	"Shift",                                                    //~va15I~
    	"Control",                                                  //~va15I~
		"Alt",                                                      //~va15I~
    	"Up",                                                       //~va15I~
    	"Down",                                                     //~va15I~
    	"Left",                                                     //~va15I~
    	"Right",                                                    //~va15I~
    	"Control-R",                                                //~va15I~
    	"AltGr",                                                   //~va15R~
    	"User",                                                    //~va15I~
    	"Gone",                                                    //~va15M~
    };
    public static AxeButton btnGone,btnFree,btnUser,btnIM,btnShortCut,btnCapsLock, btnShift,btnCtrl,btnAlt,btnLeft,btnUp,btnDown,btnRight;//~va15R~
    public static AxeButton btnRCtrl,btnAltGr,btnIMP;              //~va15R~
    public static AxeButton[] buttonTypeTbl=new AxeButton[buttonName.length];//~va15R~
    public static final int COLOR_FG_ALT     =0xff14a804;          //~va15I~
    public static final int COLOR_FG_NORMAL  =0xff000000;          //~va15I~
                                                                   //~va15I~
    public int buttonId;                                          //~va15I~
    public int actionIndex; //latest action layout index; need confiramtion yet active//~va15I~
    public String label,name;                                      //~va15R~
    public boolean isFree,isToggle,isActive,isGone,isRepeatable;   //~va15R~
    public int backgroundRid,backgroundRidActive;;                 //~va15R~
    public int userGDK;                                            //~va15I~
    public int textColor=COLOR_FG_NORMAL;;                         //~va15R~
//*********************************************                    //~va15I~
    public AxeButton(int PbuttonId,String Plabel,boolean PisFree,boolean PisToggle,boolean PisActive,boolean PisGone)//~va15R~
    {                                                              //~va15I~
    	buttonId=PbuttonId;                                        //~va15I~
    	label=Plabel;                                              //~va15I~
    	isFree=PisFree;                                            //~va15I~
    	isToggle=PisToggle;                                        //~va15I~
    	isActive=PisActive;                                        //~va15I~
    	isGone=PisGone;                                            //~va15R~
        name=buttonName[PbuttonId];                                //~va15I~
    	setResource(buttonId);                                     //~va15I~
    }                                                              //~va15I~
	public static void buttonInit()                                //~va15I~
    {                                                              //~va15M~
//*                               buttonId                   label                           Free   Toggle Active)//~va15M~
		buttonTypeTbl[BUTTON_GONE]=                                //~va15M~
        btnGone    =new AxeButton(AxeButton.BUTTON_GONE,     AxeButton.LABEL_GONE          , true , false, false//~va15M~
					,true);                                        //~va15M~
		buttonTypeTbl[BUTTON_FREE]=                                //~va15M~
        btnFree    =new AxeButton(AxeButton.BUTTON_FREE,     AxeButton.LABEL_FREE          , true , false, false//~va15M~
					,false);                                       //~va15M~
		buttonTypeTbl[BUTTON_USER]=                                //~va15M~
//      btnUser    =new AxeButton(AxeButton.BUTTON_USER,     AxeButton.LABEL_USER          , true , false, false//~va15M~//~vc2yR~
//  				,false);                                       //~va15M~//~vc2yR~
        btnUser=getNewButtonUser();                                //~vc2yI~
		buttonTypeTbl[BUTTON_SHORTCUT]=                            //~va15M~
        btnShortCut=new AxeButton(AxeButton.BUTTON_SHORTCUT, AxeButton.LABEL_SHORTCUT      , false, true , false//~va15R~
					,false);                                       //~va15M~
		buttonTypeTbl[BUTTON_CAPSLOCK]=                            //~va15M~
        btnCapsLock=new AxeButton(AxeButton.BUTTON_CAPSLOCK, AxeButton.LABEL_CAPSLOCK      , false, true , false//~va15M~
					,false);                                       //~va15M~
		buttonTypeTbl[BUTTON_SHIFT]=                               //~va15M~
        btnShift   =new AxeButton(AxeButton.BUTTON_SHIFT   , AxeButton.LABEL_SHIFT         , false, true , false//~va15R~
					,false);                                       //~va15M~
		buttonTypeTbl[BUTTON_CTL]=                                 //~va15M~
        btnCtrl    =new AxeButton(AxeButton.BUTTON_CTL     , AxeButton.LABEL_CTL           , false, true , false//~va15R~
					,false);                                       //~va15M~
		buttonTypeTbl[BUTTON_ALT]=                                 //~va15M~
        btnAlt     =new AxeButton(AxeButton.BUTTON_ALT     , AxeButton.LABEL_ALT           , false, true , false//~va15R~
					,false);                                       //~va15M~
		buttonTypeTbl[BUTTON_LEFT]=                                //~va15M~
        btnLeft    =new AxeButton(AxeButton.BUTTON_LEFT    , AxeButton.LABEL_NONE          , false, false, false//~va15M~
					,false);                                       //~va15M~
		buttonTypeTbl[BUTTON_UP]=                                  //~va15M~
        btnUp      =new AxeButton(AxeButton.BUTTON_UP      , AxeButton.LABEL_NONE          , false, false, false//~va15M~
					,false);                                       //~va15M~
		buttonTypeTbl[BUTTON_DOWN]=                                //~va15M~
        btnDown    =new AxeButton(AxeButton.BUTTON_DOWN    , AxeButton.LABEL_NONE          , false, false, false//~va15M~
					,false);                                       //~va15M~
		buttonTypeTbl[BUTTON_RIGHT]=                               //~va15M~
        btnRight   =new AxeButton(AxeButton.BUTTON_RIGHT   , AxeButton.LABEL_NONE          , false, false, false//~va15M~
					,false);                                       //~va15M~
		buttonTypeTbl[BUTTON_IM]=                                  //~va15I~
        btnIM      =new AxeButton(AxeButton.BUTTON_IM      , AxeButton.LABEL_IM            , false, false, false//~va15I~
					,false);                                       //~va15I~
		buttonTypeTbl[BUTTON_IMP]=                                 //~va15I~
        btnIMP     =new AxeButton(AxeButton.BUTTON_IMP     , AxeButton.LABEL_IMP           , false, false, false//~va15I~
					,false);                                       //~va15I~
		buttonTypeTbl[BUTTON_RCTL]=                                //~va15I~
        btnRCtrl   =new AxeButton(AxeButton.BUTTON_RCTL    , AxeButton.LABEL_RCTL          , false, false, false//~va15R~
					,false);                                       //~va15I~
		buttonTypeTbl[BUTTON_ALTGR]=                               //~va15I~
        btnAltGr   =new AxeButton(AxeButton.BUTTON_ALTGR   , AxeButton.LABEL_ALTGR         , false, true , false//~va15R~
					,false);                                       //~va15I~
                                                                   //~va15I~
    }                                                              //~va15M~
//load from preference ***********                                 //~va15I~
    public AxeButton(int PbuttonNo,AxeButton Pdefault)             //~va15R~
    {                                                              //~va15I~
    	AxeButton loaddata=AxeButtonIO.load(PbuttonNo);            //~va15I~
        if (loaddata==null)	//no saved                             //~va15I~
        	loaddata=Pdefault;                                     //~va15R~
    	buttonId=loaddata.buttonId;                               //~va15R~
    	label=loaddata.label;                                     //~va15R~
    	isFree=loaddata.isFree;                                   //~va15R~
	    if (buttonId>=0 && buttonId<buttonTypeTbl.length)          //~va15I~
    		isToggle=buttonTypeTbl[buttonId].isToggle;             //~va15R~
        else                                                       //~va15I~
    		isToggle=false;                                        //~va15I~
    	isActive=loaddata.isActive;                               //~va15R~
    	isGone=loaddata.isGone;                                    //~va15I~
        name=buttonName[buttonId];                                 //~va15I~
        userGDK=loaddata.userGDK;                                  //~va15I~
    	setResource(buttonId);                                     //~va15R~
    }                                                              //~va15I~
//*init resource                                                   //~va15I~
    private void setResource(int PbuttonId)                        //~va15R~
    {                                                              //~va15I~
    	int rid=0,ridActive=0;                                     //~va15R~
    	switch(PbuttonId)                                          //~va15I~
        {                                                          //~va15I~
        case BUTTON_ALT:                                           //~va15I~
        case BUTTON_ALTGR:                                         //~va15I~
			textColor=COLOR_FG_ALT;                                //~va15I~
        	break;                                                 //~va15I~
//        case BUTTON_CAPSLOCK:                                    //~va15R~
//            rid=R.drawable.checkbox;                             //~va15R~
//            break;                                               //~va15R~
        case BUTTON_SHIFT:                                         //~va15I~
			rid=R.drawable.button_shift_inactive;                           //~va15I~
			ridActive=R.drawable.button_shift_active;              //~va15R~
        	break;                                                 //~va15I~
        case BUTTON_UP:                                            //~va15R~
            rid=R.drawable.button_up;                              //~va15I~
            isRepeatable=true;                                     //~va15I~
        	break;                                                 //~va15I~
        case BUTTON_DOWN:                                           //~va15I~
            rid=R.drawable.button_down;                            //~va15I~
            isRepeatable=true;                                     //~va15I~
        	break;                                                 //~va15I~
        case BUTTON_LEFT:                                          //~va15I~
            rid=R.drawable.button_left;                            //~va15I~
            isRepeatable=true;                                     //~va15I~
        	break;                                                 //~va15I~
        case BUTTON_RIGHT:                                          //~va15I~
            rid=R.drawable.button_right;                           //~va15I~
            isRepeatable=true;                                     //~va15I~
        	break;                                                 //~va15I~
        }                                                          //~va15I~
        if (rid==0) //default                                      //~va15I~
	    	if (isToggle)                                          //~va15M~
            {                                                      //~va15I~
  				rid=R.drawable.button_inactive;                    //~va15R~
  				ridActive=R.drawable.button_active;                //~va15R~
            }                                                      //~va15I~
            else                                                   //~va15I~
    			rid=R.drawable.button;                             //~va15I~
        backgroundRid=rid;                                         //~va15R~
        backgroundRidActive=ridActive;                             //~va15I~
    }                                                              //~va15I~
//*setup view                                                      //~va15I~
    public static void setButtonView(AxeButton Pbutton,Button Pview)//~va15R~
    {                                                              //~va15I~
        int visibility;                                            //~va15I~
        //************                                                 //~va15I~
        if (Pbutton.buttonId==BUTTON_GONE)                         //~va15R~
            visibility=View.GONE;                                  //~va15I~
        else                                                       //~va15I~
            visibility=View.VISIBLE;                               //~va15I~
        Pview.setVisibility(visibility);                           //~va15I~
        Pview.setText(Pbutton.label);                              //~va15R~
//        ToggleButton tb=(ToggleButton)Pview;                     //~va15R~
//        tb.setTextOn(null);                                      //~va15R~
//        tb.setTextOff(null);                                     //~va15R~
        if (Dump.Y) Dump.println("Button visibilty="+visibility+",label="+Pbutton.label);//~va15R~
		Pview.setBackgroundResource(Pbutton.backgroundRid);        //~va15R~
		Pview.setTextColor(Pbutton.textColor);                     //~va15R~
//        Pview.setGravity(Gravity.RIGHT|Gravity.BOTTOM);          //~va15R~
//        repeatable=!Pbutton.setLongClickable(Pview);             //~va15R~
        AxeButtonAction ba=AxeButtonLayout.getAction(Pbutton.actionIndex,Pbutton);//~va15M~
        if (ba!=null)                                              //~va15M~
        	ba.activateRepeatable(Pbutton.isRepeatable);	//activate onTouch for repeatable event generation//~va15R~
    }                                                              //~va15I~
////**************                                                 //~va15R~
//    private boolean setLongClickable(Button Pbutton)             //~va15R~
//    {                                                            //~va15R~
//        boolean lc;                                              //~va15R~
//        if (isRepeatable)                                        //~va15R~
//            lc=false;                                            //~va15R~
//        else                                                     //~va15R~
//            lc=true;                                             //~va15R~
//        Pbutton.setLongClickable(lc);                            //~va15R~
//        if (Dump.Y) Dump.println("AxeButton setLongClickable button="+name+":"+lc);//~va15R~
//        return lc;                                               //~va15R~
//    }                                                            //~va15R~
//**************                                                   //~va15I~
    public String getButtonName()                                  //~va15I~
    {                                                              //~va15I~
    	return buttonName[buttonId];                               //~va15I~
    }                                                              //~va15I~
    public void setUserGDK(int Pgdkkey)                            //~va15I~
    {                                                              //~va15I~
    	userGDK=Pgdkkey;                                           //~va15I~
        if (Dump.Y) Dump.println("setUserGDK "+getButtonName()+"="+Integer.toHexString(Pgdkkey));//~va15I~
    }                                                              //~va15I~
    public void setActionIndex(int Pindex)                         //~va15I~
    {                                                              //~va15I~
    	actionIndex=Pindex;                                        //~va15I~
        if (Dump.Y) Dump.println("AxeButton setActionIndex button="+name+",index="+Pindex);//~va15I~
    }                                                              //~va15I~
    //* for multiple instance                                      //~vc2yI~
    public static AxeButton getNewButtonUser()                     //~vc2yI~
    {                                                              //~vc2yI~
        AxeButton  btn=new AxeButton(AxeButton.BUTTON_USER,     AxeButton.LABEL_USER          , true , false, false//~vc2yI~
					,false);                                       //~vc2yI~
        if (Dump.Y) Dump.println("AxeButton.getNewButtonUser");    //~vc2yI~
        return btn;                                                //~vc2yI~
    }                                                              //~vc2yI~
}//class                                                           //~va15R~
