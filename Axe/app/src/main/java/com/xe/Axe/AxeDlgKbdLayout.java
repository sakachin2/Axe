//*CID://+vc2DR~: update#= 186;                                    //~vc2DR~
//**********************************************************************//~1107I~
//vc2D 2020/08/19 (Bug)kbd send not to Dialog but to AxeScreen when KbdDialogHW opened after AxeDialog Open//~vc2DI~
//vc1w 2020/07/06 AxeKbd updatelog extends KbdLayoutHW             //~vc1wI~
//**********************************************************************//~vc1wI~
//*AlerDialog                                                      //~1527R~
//**********************************************************************//~1107I~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1527R~

import android.content.DialogInterface;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ahsv.utils.UView;
import com.xe.Axe.kbd.AxeKbdKey;

//**********************************************************************//~1107I~
//public class AxeDlgKbdLayout extends AxeDialog                  //~1612I~//~1919R~//~vc1wR~
//    implements AxeSpinnerI                                         //~1809I~//~vc1wR~
public class AxeDlgKbdLayout extends AxeDlgKbdLayoutHW             //~vc1wI~
{                                                                  //~0914I~
	public  static final int DIALOG     =R.layout.dialogkbdlayout; //~1919R~
	private static final int TITLE      =R.string.DialogTitle_KbdLayout;//~1528I~//~1919R~
	private static final int HELP_TITLE =R.string.HelpTitle_KbdLayout;//~1919I~
//  private static final int HELP_TEXT  =R.string.Help_KbdLayout; //~1919I~//~vc1wR~
	private static final String HELP_FILE  ="AxeKbdLayout";        //~vc1wI~
//**********************************                               //~1211I~
	public AxeDlgKbdLayout()                                    //~1612I~//~1919R~
    {                                                              //~1612I~
    	super(DIALOG);                                             //~vc1wR~
        AxeG.axeDlgKbdLayout=this;                                 //~vc1wI~
    }                                                              //~1612I~
//**********************************
    public static AxeDlgKbdLayout showDialog()                        //~1612R~//~1919R~
    {
    	AxeDlgKbdLayout axeDialog=new AxeDlgKbdLayout();//~1612R~  //~1919R~
        String title=Utils.getResourceString(TITLE);  //~1602R~    //~1809R~
		axeDialog.showDialog(title);                               //~1602R~
        return axeDialog;                                          //~1601I~
    }
//*********                                                        //~1602I~//~vc1wR~
    @Override                                                      //~1612I~//~vc1wR~
    protected void setupDialogExtend(ViewGroup PlayoutView)          //~1602R~//~vc1wR~
    {                                                              //~1602I~//~vc1wR~
        dummyFocus=(LinearLayout)PlayoutView.findViewById(R.id.DummyFocus);//~vc1wI~
        axeSpinner=AxeSpinner.create(DIALOG,PlayoutView,AxeLstKbdLayout.tgtkeynametbl);//~1919R~//~vc1wR~
//      axeList=AxeLstKbdLayout.setupListView(axeSpinner,layoutId,PlayoutView);//~1613R~//~1919R~//~vc1wR~
        axeList=AxeLstKbdLayout.setupListView(this,axeSpinner,layoutId,PlayoutView);//~vc1wI~
        axeSpinner.setListener(this);                                 //~1730R~//~1810I~//~vc1wR~
        setButtonListener(PlayoutView,R.id.Delete);                //~vc1wI~
        btnSoftKbd=setButtonListener(PlayoutView,R.id.SoftKbd);    //~vc1wI~
//      setButtonListener(PlayoutView,R.id.Reset);                 //~vc1wI~
//      setOnKeyListener(this);                                    //~vc1wI~
        etKbd=(EditText)PlayoutView.findViewById(R.id.etSoftKbd);  //~vc1wI~
        etCurrent=etKbd;                                           //~vc1wI~
        setFocusListener(etKbd);//could not detect softkbd close   //~vc1wM~
//      rgAltGr=new URadioGroup(PlayoutView,R.id.rgAltGr,0,rbIDAltGr);//~vc1wI~
//      rgAltGr.setCheckedID(AxeG.keyAltGr,false/*swFixed*/);      //~vc1wI~
                                                                   //~vc1wI~
//  	setIMKey(PlayoutView);                                     //~vc1wI~
                                                                   //~vc1wI~
    	swHardActive=AxeG.isHardKeyboardActive();                  //~vc1wI~
        switchKbdInput(false/*forceSoft*/);                        //~vc1wI~
    	setButtonStatus();                                         //~vc1wI~
        addTextWatcher(etKbd);                                     //~vc1wI~
		setIMEMode();                                              //~vc1wI~
//      etIMKey.setInputType(InputType.TYPE_NULL);                 //~vc1wI~
//      setFocusListener(etIMKey);//could not detect softkbd close //~vc1wI~
//      isSoftKbdOpen();    //set listener  layoutchange           //~vc1wI~
        UView.setDialogWidthMatchParentPortrait(this);             //~vc1wI~
    }                                                              //~1602I~//~vc1wR~
//*********                                                        //~vc1wI~
//    @Override   //need to avoid AxeDlgKbdLayoutHW called         //~vc1wR~
//    protected void setupDialogExtend(ViewGroup PlayoutView)      //~vc1wR~
//    {                                                            //~vc1wR~
//    }                                                            //~vc1wR~
//**********************************                               //~vc1wI~
	protected void setupListView(ViewGroup PlayoutView)            //~vc1wI~
    {                                                              //~vc1wI~
		if (Dump.Y) Dump.println("AxeDlgKbdLayout.setupListView"); //~vc1wI~
        axeSpinner=AxeSpinner.create(DIALOG,PlayoutView, AxeKbdKey.SspinnerData);//~vc1wI~
		axeList=AxeLstKbdLayout.setupListView(this,axeSpinner,layoutId,PlayoutView);//~vc1wI~
	}                                                              //~vc1wI~
//**********************************                               //~1612I~
	@Override                                                      //~1612I~
    protected boolean onClickHelp()                                //~1821R~
    {                                                              //~1612I~
//  	showDialogHelp(HELP_TITLE,HELP_TEXT);                      //~1919R~//~vc1wR~
    	showDialogHelp(HELP_TITLE,HELP_FILE);                      //~vc1wI~
        return false;	//not dismiss                              //~1821R~
    }                                                              //~1528I~
//    @Override                                                      //~1821I~//~vc1wR~
//    protected boolean onClickClose()                               //~1821I~//~vc1wR~
//    {                                                              //~1821I~//~vc1wR~
//        boolean rc;                                                //~1821I~//~vc1wR~
//    //**********                                                   //~1821I~//~vc1wR~
//        rc=axeList.saveUpdate();    //if err exist                 //~1821I~//~vc1wR~
//        return rc;                                                 //~1821I~//~vc1wR~
//    }                                                              //~1821I~//~vc1wR~
	@Override                                                      //~vc1wI~
    protected boolean onClickClose()                               //~vc1wI~
    {                                                              //~vc1wI~
    	boolean rc;                                                //~vc1wI~
    //**********                                                   //~vc1wI~
//        keyOpenIM=getKeyOpenIM();                                //~vc1wI~
//        if (keyOpenIM<0)    //etIMKey was not set                //~vc1wI~
//        {                                                        //~vc1wI~
//            Utils.showToast(R.string.Err_InvalidIMKey);          //~vc1wI~
//            return false;                                        //~vc1wI~
//        }                                                        //~vc1wI~
        rc=axeList.saveUpdate();	//if err exist                 //~vc1wI~
//        int keyAltGr=rgAltGr.getCheckedID();                     //~vc1wI~
//        putAltGr(keyAltGr);                                      //~vc1wI~
//        AxeG.keyOpenIM=keyOpenIM;                                //~vc1wI~
//        AxeG.setParameter(AxeG.PREFKEY_OPENIMKEY,keyOpenIM);     //~vc1wI~
	    if (Dump.Y) Dump.println("AxeDlgKbdLayout.onClickClose");  //~vc1wI~
        return rc;                                                 //~vc1wI~
    }                                                              //~vc1wI~
//    @Override                                                      //~1923I~//~vc1wR~
//    protected boolean onClickOther(int Pbuttonid)                  //~1923I~//~vc1wR~
//    {                                                              //~1923I~//~vc1wR~
//        //**********                                                   //~1923I~//~vc1wR~
//        switch(Pbuttonid)                                           //~1923I~//~vc1wR~
//        {                                                          //~1923I~//~vc1wR~
//        case R.id.Reset:                                           //~1923I~//~vc1wR~
//            if (Dump.Y) Dump.println("AxeDlgKbdLayout reset");     //~1923I~//~vc1wR~
//            ((AxeLstKbdLayout)axeList).resetToDefault();                              //~1923I~//~vc1wR~
//            break;                                                 //~1923I~//~vc1wR~
//        }                                                          //~1923I~//~vc1wR~
//        return false;                                              //~1923I~//~vc1wR~
//    }                                                              //~1923I~//~vc1wR~
	@Override                                                      //~vc1wI~
    protected boolean onClickOther(int Pbuttonid)                  //~vc1wI~
    {                                                              //~vc1wI~
    	//**********                                               //~vc1wI~
    	switch(Pbuttonid)                                          //~vc1wI~
        {                                                          //~vc1wI~
        case R.id.Reset:                                           //~vc1wR~
            if (Dump.Y) Dump.println("AxeDlgKbdLayout.onClickOther reset");//~vc1wR~
            axeList.resetToDefault();                              //~vc1wR~
            break;                                                 //~vc1wR~
        case R.id.Delete:                                          //~vc1wI~
	    	if (Dump.Y) Dump.println("AxeDlgKbdLayout.onClickOther Delete");//~vc1wR~
        	axeList.onClickDelete();                               //~vc1wI~
        	break;                                                 //~vc1wI~
        case R.id.SoftKbd:                                         //~vc1wI~
	    	if (Dump.Y) Dump.println("AxeDlgKbdLayout.onClickOther SoftKbd");//~vc1wR~
        	useSoftKbd();                                          //~vc1wI~
        	break;                                                 //~vc1wI~
        }                                                          //~vc1wI~
        return false;                                              //~vc1wI~
    }                                                              //~vc1wI~
//    //*******************************                              //~1809I~//~vc1wR~
//    @Override       //AxeSpinnerI                                  //~1809I~//~vc1wR~
//    public void onSpinnerItemSelected(int Ppos)                    //~1809I~//~vc1wR~
//    {                                                              //~1809I~//~vc1wR~
//        axeList.onSpinnerItemSelected(Ppos);                       //~1809I~//~vc1wR~
//    }                                                              //~1809I~//~vc1wR~
    //**********************************************************************//~vc2DI~
    public static boolean isShowingDlg()                           //~vc2DI~
    {                                                              //~vc2DI~
    	boolean rc=AxeG.axeDlgKbdLayout!=null &&  AxeG.axeDlgKbdLayout.isShowing();//~vc2DI~
		if (Dump.Y) Dump.println("AxeDlgKbdLayout.isShowing rc="+rc);//~vc2DI~
        return rc;                                                 //~vc2DI~
    }                                                              //~vc2DI~
//**********************************                               //~vc2DI~
//*from Axe;configuration changed-->AxeKbdDialog                   //~vc2DI~
//*return dialog is opening                                        //~vc2DI~
//**********************************                               //~vc2DI~
    public void configChanged(boolean PswChanged)                  //~vc2DI~
    {                                                              //~vc2DI~
        if (Dump.Y) Dump.println("AxeDlgKbdLayout.configChanged"); //~vc2DI~
        if (PswChanged)                                            //~vc2DI~
        {                                                          //~vc2DI~
            AxeG.axeDlgKbdLayout.swHardActive=AxeG.isHardKeyboardActive();//~vc2DI~
            AxeG.axeDlgKbdLayout.switchKbdInput(false/*forceSoft*/);//~vc2DI~
//          AxeG.axeDlgKbdLayout.requestFocusET(false/*PswOpenIM*/);//~vc2DR~
            AxeG.axeDlgKbdLayout.dummyFocus.requestFocus();        //~vc2DI~
            AxeG.axeDlgKbdLayout.setButtonStatus();                //~vc2DI~
        }                                                          //~vc2DI~
    }                                                              //~vc2DI~
    public void configChangedReopen()                              //~vc2DI~
    {                                                              //~vc2DI~
        if (Dump.Y) Dump.println("AxeDlgKbdLayout.configChangedReopen");//~vc2DI~
    	swReopen=true;                                             //~vc2DI~
        dismiss();                                                 //~vc2DI~
    }                                                              //~vc2DI~
	@Override                                                      //~vc2DI~
	protected void onDismiss()                                     //~vc2DI~
    {                                                              //~vc2DI~
	    if (Dump.Y) Dump.println("AxeDlgKbdLayout.onDismiss");     //~vc2DI~
    	AxeG.axeDlgKbdLayout=null;                                 //~vc2DI~
        if (swReopen)                                              //~vc2DI~
        	AxeDlgKbdLayout.showDialog();                          //~vc2DI~
    }                                                              //+vc2DI~
}//class                                                           //~1612R~
