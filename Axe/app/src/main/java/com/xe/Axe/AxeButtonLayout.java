//CID://+vc2ZR~:         update#=     16                           //~vc2ZR~
//*************************************************************
//vc2Z 2020/09/20 set Default button layout to HW                  //~vc2ZI~
//vc2A 2020/08/12 (BUG)ButtonLayout insert after 1st line end added to last of 2nd line//~vc2AI~
//vc2x 2020/08/10 change buttons layout for HWKbd                  //~vc2xI~
//vc2k 2020/07/28 Button layout with hardware keyboard             //~vc2kI~
//vaie:130529 Axe:Bug:When landscape,screen hight was changed when button array1 changed count=0<-->!0//~vaieI~
//*************************************************************
package com.xe.Axe;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

//*************************************************************    //~1923I~
//*Layout Button array                                             //~1923I~
//*************************************************************    //~1923I~
public class AxeButtonLayout
{
	private static final int MAX_BUTTONS_PER_LINE=8;
	public  static final int MAX_BUTTONS=MAX_BUTTONS_PER_LINE*2;
	public  static final int DEFAULT_BUTTONS     =6;               //~vaieR~
	public  static final int HWKBD_BUTTONS1      =0;               //~vc2kR~
	public  static final int HWKBD_BUTTONS2      =6;               //~vc2kR~//~vc2xR~

    private static final int[] buttonResourceId={
    		R.id.Button21,R.id.Button22,R.id.Button23,R.id.Button24,R.id.Button25,R.id.Button26,R.id.Button27,R.id.Button28,
    		R.id.Button11,R.id.Button12,R.id.Button13,R.id.Button14,R.id.Button15,R.id.Button16,R.id.Button17,R.id.Button18};
//  private static final int buttonArray1ContainerId=R.id.Buttons2;//~3529R~
    private static AxeButtonAction[] buttonActionTbl;              //~1811R~

//  private static final String PREFKEY_STICKY ="Sticky";          //~1609R~
    private static final String PREFKEY_BUTTONNO="BtnNo";
    private static final String PREFKEY_ORIENTATIONFIX="Orientation";//~1822R~

//  public boolean sticky=true;                                    //~1822R~
    public int buttonNo1,buttonNo2;
//  private static boolean useLand;		//1line button when landscape//~1822R~
    private static int     orientationFix=-1;                      //~1822I~
    public  static final int PORTRAIT=0;                           //~1822R~
    public  static final int LANDSCAPE=1;                          //~1822R~
    public  static final int ROTATE=2;                             //~1822R~
                                                                   //~1822I~
//  private static boolean initOseLand;                            //~1822R~
    private static int oldButtonNo1=-1;                            //~1607I~
//  private LinearLayout buttonArray1Container;                    //~3529R~
//*********************************************
	public AxeButtonLayout()
    {
    	AxeButton.buttonInit();	//init statics
    	initialfindView();
    	setDefaultLayout();
    	initialload();
    	setButtonLayout(buttonNo1,buttonNo2);
    }
//**************
//**************
	public void initialfindView()
    {
    	int id;
        ViewGroup layoutView;
        Button btn;                                                //~1810R~
    //**************
	    buttonActionTbl=new AxeButtonAction[MAX_BUTTONS];
        layoutView=(ViewGroup)AxeG.mainView;
    	for (int ii=0;ii<MAX_BUTTONS;ii++)
		{
			id=buttonResourceId[ii];
    		btn=(Button)layoutView.findViewById(id);               //~1810R~
            if (Dump.Y) Dump.println("ButtonLayoutinitialFindView hh="+btn.getHeight());//~vaieI~
            buttonActionTbl[ii]=new AxeButtonAction(ii,btn);
        }
//  	buttonArray1Container=(LinearLayout)layoutView.findViewById(buttonArray1ContainerId);//~3529R~
    }
//**************
	public void orientationChanged()
    {
    	int id;
        ViewGroup layoutView;
        Button btnView;                                            //~1607R~//~1810R~
        AxeButton btn;                                             //~1607I~
        AxeButtonAction act;                                       //~1607I~
    //**************
        layoutView=(ViewGroup)AxeG.mainView;
    	for (int ii=0;ii<MAX_BUTTONS;ii++)
		{
			id=buttonResourceId[ii];
    		btnView=(Button)layoutView.findViewById(id);           //~1607R~//~1810R~
            act=buttonActionTbl[ii];                                //~1607R~
            btn=act.button;                                        //~1607I~
            act.setButtonView(btnView);	//set listener             //~1607I~
            AxeButton.setButtonView(btn,btnView);	//visbility/background//~1607I~
        }
    }
//**************
	public void setDefaultLayout()
    {
        buttonActionTbl[0].setButton(AxeButton.btnIM);               //~1830R~
        buttonActionTbl[1].setButton(AxeButton.btnIMP);            //~1830I~
        buttonActionTbl[2].setButton(AxeButton.btnShortCut);       //~1830R~
        buttonActionTbl[3].setButton(AxeButton.btnCapsLock);       //~1830R~
        buttonActionTbl[4].setButton(AxeButton.btnShift);          //~1830R~
        buttonActionTbl[5].setButton(AxeButton.btnCtrl);           //~1830R~
        buttonActionTbl[6].setButton(AxeButton.btnGone);           //~1830I~
        buttonActionTbl[7].setButton(AxeButton.btnGone);           //~1830I~

        buttonActionTbl[8].setButton(AxeButton.btnAlt);            //~1830I~
        buttonActionTbl[9].setButton(AxeButton.btnAltGr);          //~1830R~
        buttonActionTbl[10].setButton(AxeButton.btnLeft);
        buttonActionTbl[11].setButton(AxeButton.btnUp);
        buttonActionTbl[12].setButton(AxeButton.btnDown);
        buttonActionTbl[13].setButton(AxeButton.btnRight);
        buttonActionTbl[14].setButton(AxeButton.btnGone);
        buttonActionTbl[15].setButton(AxeButton.btnGone);

        buttonNo1=DEFAULT_BUTTONS;
        buttonNo2=DEFAULT_BUTTONS;
    }
//**************                                                   //~vc2kI~
	public void setHWKbdLayout()                                   //~vc2kR~
    {                                                              //~vc2kI~
        buttonActionTbl[0].setButton(AxeButton.btnIM);             //~vc2kI~
        buttonActionTbl[1].setButton(AxeButton.btnIMP);            //~vc2kI~
        buttonActionTbl[2].setButton(AxeButton.btnCtrl);           //~vc2xI~
        buttonActionTbl[3].setButton(AxeButton.btnAlt);            //~vc2xI~
        buttonActionTbl[4].setButton(AxeButton.btnAltGr);          //~vc2xI~
        buttonActionTbl[5].setButton(AxeButton.btnShortCut);       //~vc2xI~
        buttonActionTbl[6].setButton(AxeButton.btnGone);           //~vc2kR~//~vc2xR~
        buttonActionTbl[7].setButton(AxeButton.btnGone);           //~vc2kR~//~vc2xR~
                                                                   //~vc2kI~
        buttonActionTbl[8].setButton(AxeButton.btnGone);           //~vc2kI~
        buttonActionTbl[9].setButton(AxeButton.btnGone);           //~vc2kR~
        buttonActionTbl[10].setButton(AxeButton.btnGone);          //~vc2kI~
        buttonActionTbl[11].setButton(AxeButton.btnGone);          //~vc2kI~
        buttonActionTbl[12].setButton(AxeButton.btnGone);          //~vc2kI~
        buttonActionTbl[13].setButton(AxeButton.btnGone);          //~vc2kI~
        buttonActionTbl[14].setButton(AxeButton.btnGone);          //~vc2kI~
        buttonActionTbl[15].setButton(AxeButton.btnGone);          //~vc2kI~
                                                                   //~vc2kI~
        buttonNo1=HWKBD_BUTTONS1;                                  //~vc2kR~
        buttonNo2=HWKBD_BUTTONS2;                                  //~vc2kI~
    }                                                              //~vc2kI~
//**************
//    public void save(int PbuttonNo)
//    {
//        AxeButtonIO.save(PbuttonNo,buttonLayout[PbuttonNo]);
//    }
//**************
    private void initialload()                                     //+vc2ZR~
    {
    	AxeButton btn;
        for (int ii=0;ii<MAX_BUTTONS;ii++)
        {
        	btn=new AxeButton(ii,buttonActionTbl[ii].button);
        	buttonActionTbl[ii].setButton(btn);
        }
        loadoptions();
        oldButtonNo1=buttonNo1;                                    //~1607I~
        if (Dump.Y) Dump.println("AxeButtonLayout.initialLoad buttonNo1="+buttonNo1);//~vc2kI~
    }
//**************
    private void loadoptions()                                     //+vc2ZR~
    {
//  	sticky=restoreOptionBoolean(PREFKEY_STICKY);               //~1609R~
//  	buttonNo1=restoreOptionInt(PREFKEY_BUTTONNO+1,DEFAULT_BUTTONS);//+vc2ZR~
//  	buttonNo2=restoreOptionInt(PREFKEY_BUTTONNO+2,DEFAULT_BUTTONS);//+vc2ZR~
    	buttonNo1=restoreOptionInt(PREFKEY_BUTTONNO+1,HWKBD_BUTTONS1);//+vc2ZI~
    	buttonNo2=restoreOptionInt(PREFKEY_BUTTONNO+2,HWKBD_BUTTONS2);//+vc2ZI~
    }
//**************
    public static boolean restoreOptionBoolean(String Pkey)
    {
        String value=AxeProp.getPreference(Pkey);
        boolean status=value.equals("1");
        if (Dump.Y) Dump.println("AxeButtonLayout "+Pkey+"="+status);
        return status;
    }
//**************                                                   //~1810I~
    public static boolean restoreOptionBoolean(String Pkey,String Pdefault)//~1810I~
    {                                                              //~1810I~
        String value=AxeProp.getPreference(Pkey,Pdefault);         //~1810I~
        boolean status=value.equals("1");                          //~1810I~
        if (Dump.Y) Dump.println("AxeButtonLayout "+Pkey+"="+status);//~1810I~
        return status;                                             //~1810I~
    }                                                              //~1810I~
//**************
    public static int restoreOptionInt(String Pkey,int Pdefault)   //~1822R~
    {
    	int intval;
        String value=AxeProp.getPreference(Pkey);
        if (value==null || (intval=Utils.strToNum(value,-1))==-1)
        	intval=Pdefault;
        if (Dump.Y) Dump.println("AxeButtonNo "+Pkey+"="+intval);
        return intval;
    }
//**************
    public static void saveOption(String Pkey,boolean Pstatus)     //~1822R~
    {
        AxeProp.putPreference(Pkey,(Pstatus?"1":"0"));
    }
//**************                                                   //~1822I~
    public static void saveOption(String Pkey,int Pvalue)          //~1822R~
    {                                                              //~1822I~
        AxeProp.putPreference(Pkey,Integer.toString(Pvalue));      //~1822I~
    }                                                              //~1822I~
//**************
    private void saveLayout()
    {
    	AxeProp.putPreference(PREFKEY_BUTTONNO+1,""+buttonNo1);
    	AxeProp.putPreference(PREFKEY_BUTTONNO+2,""+buttonNo2);
        if (Dump.Y) Dump.println("saveLayout no1="+buttonNo1+",no2="+buttonNo2);
        for (int ii=0;ii<MAX_BUTTONS;ii++)
        {
        	AxeButtonIO.save(ii,buttonActionTbl[ii].button);
        }
    }
////**************                                                 //~1609R~
//    public void setSticky(boolean Pstatus)                       //~1609R~
//    {                                                            //~1609R~
//        sticky=Pstatus;                                          //~1609R~
//        saveOption(PREFKEY_STICKY,sticky);                       //~1609R~
//    }                                                            //~1609R~
////**************                                                 //~1609R~
//    public boolean getSticky()                                   //~1609R~
//    {                                                            //~1609R~
//        return sticky;                                           //~1609R~
//    }                                                            //~1609R~
//**************
    public static int getOrientationFix()                          //~1822R~
    {                                                              //~1822R~
        if (orientationFix==-1)//1st time                         //~1822R~
        {                                                          //~1822R~
            orientationFix=restoreOptionInt(PREFKEY_ORIENTATIONFIX,ROTATE/*defauult*/);//~1822R~
        }                                                          //~1822R~
        if (Dump.Y) Dump.println("getOrientationFix="+orientationFix);//~1822R~
        return orientationFix;                                     //~1822R~
    }                                                              //~1822R~
//**************                                                   //~1822R~
    public static void setOrientationFix(int PorientationFix)      //~1822R~
    {                                                              //~1822R~
        orientationFix=PorientationFix;                            //~1822R~
        saveOption(PREFKEY_ORIENTATIONFIX,orientationFix);         //~1822R~
    }                                                              //~1822R~
//******************************
    public void restoreHWKbdLayout()                               //~vc2kR~
    {
    	setHWKbdLayout();                                          //~vc2kR~
    	setButtonLayout(buttonNo1,buttonNo2);
    }
//******************************                                   //~vc2kI~
    public void restoreDefaultLayout()                             //~vc2kI~
    {                                                              //~vc2kI~
    	setDefaultLayout();                                        //~vc2kI~
    	setButtonLayout(buttonNo1,buttonNo2);                      //~vc2kI~
    }                                                              //~vc2kI~
//**************
//*from dialog
//**************
    public boolean updateButtonLayout(int Pno1,int Pno2)
    {
    	if (Pno1<0||Pno1>MAX_BUTTONS_PER_LINE
    	||  Pno2<0||Pno2>MAX_BUTTONS_PER_LINE)
        {
            Utils.showToastLong(R.string.Err_ButtonNo,Integer.toString(MAX_BUTTONS_PER_LINE));
            return false;
        }
        buttonNo1=Pno1;
        buttonNo2=Pno2;
    	setButtonLayout(Pno1,Pno2);
    	return true;
    }
//**************
    private void setButtonLayout(int Pno1,int Pno2)
    {
    	AxeButton[] newlayout;
        newlayout=arrangeLayout(Pno1,Pno2);
        setButtonStatusALL(newlayout);                             //~1923R~
	    saveLayout();
    	if (Dump.Y) Dump.println("AxeButtonLayout no="+Pno1+","+Pno2+",oldNo1="+oldButtonNo1);//~vc2kI~
        if (oldButtonNo1>=0 && oldButtonNo1!=Pno1)	               //~1607I~
        {                                                          //~1607I~
        	if (oldButtonNo1==0||Pno1==0)	//changed 0 and non-0  //~1607I~
            {                                                      //~3529I~
              if (AxeG.displayPL==AxeG.PORTRAIT)                     //~vaieI~
            	AxeG.axeView.layoutChanged(Pno1==0);                //~1607R~
            }                                                      //~3529I~
	        oldButtonNo1=Pno1;                                     //~1607I~
        }                                                          //~1607I~
    }
//**************
    public AxeButton[] arrangeLayout(int Pno1,int Pno2)
    {
        int ctr,ctr1,ctr2;
    //**************
    	if (Dump.Y) Dump.println("arrnageButtonLaout no="+Pno1+","+Pno2);
    	AxeButton[] newlayout=new AxeButton[MAX_BUTTONS];
        AxeButton btn;
//chk overflow free entry to be dropped
		int ctrfree=0,ctrnotfree=0;
        for (int ii=0;ii<MAX_BUTTONS;ii++)
        {
        	btn=buttonActionTbl[ii].button;
            if (btn.buttonId==AxeButton.BUTTON_GONE)
            	continue;
            if (btn.buttonId==AxeButton.BUTTON_FREE)
            	ctrfree++;
            else
            	ctrnotfree++;
        }
        int ctrshow=Pno1+Pno2;
        int ctrovf=(ctrnotfree+ctrfree)-ctrshow;
        int ctrdropfree=0;
        if (ctrovf>0)
			if (ctrnotfree<ctrshow)
            	ctrdropfree=ctrfree-(ctrshow-ctrnotfree);  //dro free up to limit
            else
            	ctrdropfree=ctrfree;		//drop all free
    	if (Dump.Y) Dump.println("arrnageButtonLaout dropfree="+ctrdropfree);
//* button line 1
		ctr=0;
        for (ctr1=0;ctr<MAX_BUTTONS && ctr1<Pno1;ctr++)
        {
        	btn=buttonActionTbl[ctr].button;
            if (btn.buttonId==AxeButton.BUTTON_GONE)
            	continue;
            if (btn.buttonId==AxeButton.BUTTON_FREE)
            {
            	if (ctrdropfree-->0)
                	continue;
			}
            if (Dump.Y) Dump.println("ButtonLayout pos="+ctr+",1st seq="+ctr1+",id="+btn.buttonId+",label="+btn.name);
            newlayout[ctr1++]=btn;
        }
        if (ctr==MAX_BUTTONS)	//exausted
            for (;ctr1<Pno1;ctr1++)
            {
                newlayout[ctr1]=AxeButton.btnFree;
            }
        for (int ii=ctr1;ii<MAX_BUTTONS_PER_LINE;ii++)
        {
            newlayout[ii]=AxeButton.btnGone;
        }
//skip intermediate GONE entry
        for (;ctr<MAX_BUTTONS;ctr++)
        {
        	btn=buttonActionTbl[ctr].button;
            if (btn.buttonId!=AxeButton.BUTTON_GONE)
            	break;
        }
 //* button line 2
        for (ctr2=0;ctr<MAX_BUTTONS && ctr2<Pno2;ctr++)
        {
        	btn=buttonActionTbl[ctr].button;
            if (btn.buttonId==AxeButton.BUTTON_GONE)
            	continue;
            if (btn.buttonId==AxeButton.BUTTON_FREE)
            {
            	if (ctrdropfree-->0)
                	continue;
			}
            if (Dump.Y) Dump.println("ButtonLayout pos="+ctr+",2nd seq="+ctr2+",id="+btn.buttonId+",label="+btn.name);
            newlayout[MAX_BUTTONS_PER_LINE+ctr2++]=btn;
        }
        if (ctr==MAX_BUTTONS)	//exausted
            for (;ctr2<Pno2;ctr2++)
            {
                newlayout[MAX_BUTTONS_PER_LINE+ctr2]=AxeButton.btnFree;
            }
        for (int ii=ctr2;ii<MAX_BUTTONS_PER_LINE;ii++)
        {
            newlayout[MAX_BUTTONS_PER_LINE+ii]=AxeButton.btnGone;
        }
        return newlayout;
    }
//**************
    private void setButtonStatusALL(AxeButton[] PbuttonLayout)     //~1923R~
    {
    //************
        for (int ii=0;ii<MAX_BUTTONS;ii++)
        {
			setButtonStatus1(ii,PbuttonLayout[ii]);
        }
    }
//**************
    private void resetButtonNo()
    {
    	AxeButton btn;
        int no1=0,no2=0;
    //************
        for (int ii=0;ii<MAX_BUTTONS;ii++)
        {
        	btn=buttonActionTbl[ii].button;
            if (btn.buttonId==AxeButton.BUTTON_GONE)
            	continue;
            if (ii<MAX_BUTTONS_PER_LINE)
            	no1++;
            else
            	no2++;
        }
        buttonNo1=no1;
        buttonNo2=no2;
        if (Dump.Y) Dump.println("resetButtonNo no1="+no1+",noe2="+no2);
    }
//**************
    private void setButtonStatus1(int Pbtnno,AxeButton Pbtn)       //~1923R~
    {
    //************
        if (Dump.Y) Dump.println("setButtonStatus no="+Pbtnno);    //~3529I~
    	buttonActionTbl[Pbtnno].setButton(Pbtn);//~1923R~
        AxeButton.setButtonView(Pbtn,buttonActionTbl[Pbtnno].buttonView);	//visibility,test//~1923R~
    }
//**************
    public boolean buttonUpdate(AxeButtonAction Paction,AxeButton Pnewbtn,int Pfuncid,int Pmovevalue)
    {      
    	AxeButton button=Pnewbtn;
        int btnno=Paction.buttonNo;
    	switch(Pfuncid)
        {
        case AxeDlgBtnUpdate.VIEWID_CB_DELETE:
        	setButtonStatus1(btnno,AxeButton.btnGone);
        	break;
        case AxeDlgBtnUpdate.VIEWID_CB_REPLACE:
        	setButtonStatus1(btnno,button);
        	break;
        case AxeDlgBtnUpdate.VIEWID_CB_INSERTAFTER:
			buttonUpdate_Insert(btnno+1,button);
        	break;
        case AxeDlgBtnUpdate.VIEWID_CB_INSERTBEFORE:
			buttonUpdate_Insert(btnno,button);
        	break;
        case AxeDlgBtnUpdate.VIEWID_CB_MOVE:
			buttonUpdate_Move(btnno,button,Pmovevalue);
        	break;
        default:
        	return true;
        }
        if (Pfuncid!=AxeDlgBtnUpdate.VIEWID_CB_MOVE)
		    resetButtonNo();	//update ctr/line
	    setButtonLayout(buttonNo1,buttonNo2);	//keep btn count on each line
    	return true;
    }
//********
    public boolean buttonUpdate_Insert(int Ppos,AxeButton Pbutton)
    {
    	AxeButton[] newlayout=new AxeButton[MAX_BUTTONS];
        AxeButton btn;
        int pos1=0,pos2=0;
        boolean inserted=false,deleted=false,overflow=false;
    //************
        if (Dump.Y) Dump.println("buttonUpdate insert pos="+Ppos+",button="+Pbutton.name);
    	for (;pos1<MAX_BUTTONS;pos1++)
		{
        	btn=buttonActionTbl[pos1].button;
            if (btn.buttonId==AxeButton.BUTTON_GONE)
            {
                if (!deleted)
                {
	        		if (Dump.Y) Dump.println("AxeButtonLayout.buttonUpdate inserted pos="+pos1);//~vc2AR~
                    deleted=true;
                    pos1--;                                        //~vc2AI~
                    continue;
                }
            }
			if (pos1==Ppos && !inserted)
            {
        		if (Dump.Y) Dump.println("buttonUpdate inserted pos="+pos1);
                pos1--;	//set at next
                btn=Pbutton;	//to be inserted
                inserted=true;
            }
        	if (Dump.Y) Dump.println("AxeButtonLayout.buttonUpdate insert pos="+pos2+",button="+btn.name);//~vc2AR~
            if (pos2<MAX_BUTTONS)
            	newlayout[pos2++]=btn;
            else
            if (btn.buttonId!=AxeButton.BUTTON_GONE)
            	overflow=true;
        }
        if (deleted && !inserted)	//GONE at last entry is not replaced
        {
            if (pos2<MAX_BUTTONS)
            {
                newlayout[pos2++]=Pbutton;
                inserted=true;
            }
        }
        if (overflow||!inserted)
        {
            Utils.showToastLong(R.string.Err_BtnUpdate_Overflow);
            return false;
        }
	    setButtonStatusALL(newlayout);
        return true;
    }
//********
    public void buttonUpdate_Move(int Ppos,AxeButton Pbutton,int Pshiftctr)
    {
//    	AxeButton[] newlayout=new AxeButton[MAX_BUTTONS];          //~1822R~
     	AxeButton btn;
        int shiftctr=Pshiftctr,insertpos;
    //************
    	if (shiftctr<0)
        {
            insertpos=0;
            for (int ii=Ppos-1;ii>=0;ii--)
            {
            	btn=buttonActionTbl[ii].button;
                if (btn.buttonId!=AxeButton.BUTTON_GONE)
                {
                	if (++shiftctr==0)
                    {
                    	insertpos=ii;
                        break;
                    }
                }
        	}
        }
        else
        {
            insertpos=MAX_BUTTONS;
            shiftctr++;		//after
            for (int ii=Ppos+1;ii<MAX_BUTTONS;ii++)
            {
            	btn=buttonActionTbl[ii].button;
                if (btn.buttonId!=AxeButton.BUTTON_GONE)
                {
                	if (--shiftctr==0)
                    {
                    	insertpos=ii;
                        break;
                    }
                }
        	}
        }
        buttonActionTbl[Ppos].setButton(AxeButton.btnGone);
        buttonUpdate_Insert(insertpos,Pbutton);
    }
//********                                                         //~1824I~
    public static AxeButtonAction getAction(int Pindex,AxeButton Pbutton)//~1824I~
    {                                                              //~1824I~
        if (Dump.Y) Dump.println("AxeButtonLayout getAction index="+Pindex+",button="+Pbutton.name);//~1824I~
    	AxeButtonAction action=buttonActionTbl[Pindex];            //~1824I~
        if (action.button.buttonId==Pbutton.buttonId) //yet asigned to layout                               //~1824I~
	        return action;                                         //~1824I~
        return null;                                               //~1824I~
    }                                                              //~1824I~
}//class
