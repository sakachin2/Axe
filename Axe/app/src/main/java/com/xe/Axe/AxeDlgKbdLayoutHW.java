//*CID://+vc2YR~: update#= 367;                                    //~vc2YR~
//**********************************************************************//~1107I~
//vc2Y 2020/09/19 (BUG)HW spaceKey opens IM                        //~vc2YI~
//vc2E 2020/08/21 (Bug)have to consider shift effect for ALt/Ctrl+Ascii symbol//~vc2EI~
//vc2j 2020/07/27 IMopen key on hardKbd                            //~vc2jI~
//vc2g 2020/07/26 AltGr key option                                 //~vc2gI~
//vc26 2020/07/11 mix AxeKbdDialog and AxeKbdDialogFix(apply map of AxeLstKbdLayout)//~vc26I~
//vc24 2020/07/11 no focus warining at kbd input                   //~vc24I~
//vc1t 2020/07/02 multi column EditText ListView callback OnFocusListener called twice True then False(mey be bug)//~vc1tI~
//                requestFocus after short time may fix it but SoftKbd popup could not be protected(can be disapper after once popupped)//~vc1tI~
//                Try to receive input by hard/soft keyboard by dummy(InVisible) EditText then send to ListView.//~vc1tI~
//                Receive HardKbd input by textview(requestFocus), //~vc1tI~
//                By IME button set dummy EditText to focusable and receive from SoftKbd//~vc1tI~
//vc1s 2020/06/29 hardKeyboard layout;set default                  //~vc1sI~
//**********************************************************************//~vc1sI~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1527R~

import android.content.DialogInterface;
import android.text.InputType;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;                                      //~vc1sI~
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ahsv.gui.URadioGroup;
import com.ahsv.utils.UView;
import com.xe.Axe.kbd.AxeKbdDialogHW;
import com.xe.Axe.kbd.AxeKbdKey;
import com.ahsv.utils.Utils;

import static android.view.KeyEvent.*;
import static com.xe.Axe.AxeKeyValue.*;
import static com.xe.Axe.Gxeh.*;

//**********************************************************************//~1107I~
public class AxeDlgKbdLayoutHW extends AxeDialog                  //~1612I~//~1919R~//~vc1sR~
	implements AxeSpinnerI                                         //~1809I~
	, EditTextListener                                                    //~vc1tI~
	, AxeTimerI
    , URadioGroup.URadioGroupI                                     //~vc2jI~
//    , TextView.OnEditorActionListener                            //~vc1tR~
    , DialogInterface.OnKeyListener                              //~vc1tR~//~vc24R~
{                                                                  //~0914I~
	public  static final int DIALOG     =R.layout.dialogkbdlayouthw; //~1919R~//~vc1sR~
	private static final int TITLE      =R.string.DialogTitle_KbdLayoutHW;//~1528I~//~1919R~//~vc1sR~
	private static final int HELP_TITLE =R.string.HelpTitle_KbdLayoutHW;//~1919I~//~vc1sR~
//  private static final int HELP_TEXT  =R.string.Help_KbdLayout; //TODO //~1919I~//~vc1sR~//~vc1tR~
	private static final String HELP_FILE  ="AxeKbdLayoutHW";         //~vc1tI~
	private static final int COLOR_SOFTKBD=0xfff0f0f0;             //~vc1tI~
	private static final int COLOR_HARDKBD=0xffffff00;             //~vc1tI~
	private static final int COLOR_DISABLE=0xff808080;             //~vc2jI~
	private static final int COLOR_ENABLE=0xffffff00;              //~vc2jR~
    private static final int[] rbIDAltGr=new int[]{R.id.rbAltGrNo,R.id.rbAltGrRightAlt,R.id.rbAltGrLeftAlt,R.id.rbAltGrRightShift};//~vc2gI~
    private static final int[] rbIDIMKey=new int[]{R.id.rbIMKeyNo,R.id.rbIMKeyZenHan,R.id.rbIMKeyOther};//~vc2jI~
    private static final int rbID_IMKeyOther=2;                    //~vc2jI~
    private static final int UBRGP_IMKEY=1;                //~9B10I~//~vc2jI~
                                                                   //~1809I~
    protected EditText etKbd;                                        //~vc1tI~//~vc2jR~
    protected LinearLayout dummyFocus;                             //~vc2jI~
    private EditText etIMKey;                                      //~vc2jI~
	protected AxeSpinner axeSpinner;                                 //~1809I~//~vc1tR~
	protected Button btnSoftKbd;                                     //~vc1tI~//~vc2jR~
	protected boolean swHardActive;                                  //~vc1tI~//~vc2jR~
    private int currentRow,currentCol;                             //~vc1tI~
    protected AxeLstKbdLayoutHW axeList;                           //~vc1tR~
    private String itemText="";                                    //~vc1tI~
    private boolean swInternalChange;                              //~vc1tI~
    protected EditText etCurrent=null;                               //~vc1tR~//~vc2jR~
    private ViewGroup rootView;                                    //~vc1tI~
    private boolean swSoftKbdOpen;                                 //~vc1tR~
    private int hhDialog;                                              //~vc1tI~
    protected int layoutID;                                        //~vc1tI~
    private URadioGroup rgAltGr;                                   //~vc2gI~
    private URadioGroup rgIMKey;                                   //~vc2jI~
    private int keyOpenIM,keycodeOpenIM;                           //~vc2jI~
    private boolean swFocusETIMKey;                                //~vc2jI~
    protected boolean swReopen;                                    //~vc2jI~
//**********************************                               //~1211I~
	public AxeDlgKbdLayoutHW()                                    //~1612I~//~1919R~//~vc1sR~
    {                                                              //~1612I~
    	super(DIALOG);                                //~1612I~    //~1809R~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.constructor"); //~vc1sI~
        AxeG.axeDlgKbdLayoutHW=this;                                //~vc1tI~
    }                                                              //~1612I~
//**********************************                               //~vc1tI~
//*from AxeDlgKbdLayout                                            //~vc1tI~
//**********************************                               //~vc1tI~
	public AxeDlgKbdLayoutHW(int PlayoutID)                        //~vc1tI~
    {                                                              //~vc1tI~
    	super(PlayoutID);                                          //~vc1tI~
        layoutID=PlayoutID;                                        //~vc1tI~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.constructor"); //~vc1tI~
        AxeG.axeDlgKbdLayoutHW=this;                               //~vc1tI~
    }                                                              //~vc1tI~
//**********************************
    public static AxeDlgKbdLayoutHW showDialog()                        //~1612R~//~1919R~//~vc1sR~
    {
    	AxeDlgKbdLayoutHW axeDialog=new AxeDlgKbdLayoutHW();//~1612R~  //~1919R~//~vc1sR~
        String title=Utils.getResourceString(TITLE);  //~1602R~    //~1809R~
		axeDialog.showDialog(title);                               //~1602R~
        return axeDialog;                                          //~1601I~
    }
//*********                                                        //~1602I~
	protected void setupListView(ViewGroup PlayoutView)            //~vc1tI~
    {                                                              //~vc1tI~
        axeSpinner=AxeSpinner.create(DIALOG,PlayoutView, AxeKbdKey.SspinnerData);//~vc1tI~
		axeList=AxeLstKbdLayoutHW.setupListView(this,axeSpinner,layoutId,PlayoutView);//~vc1tI~
	}                                                              //~vc1tI~
	@Override                                                      //~1612I~
	protected void setupDialogExtend(ViewGroup PlayoutView)          //~1602R~
    {                                                              //~1602I~
//      axeSpinner=AxeSpinner.create(DIALOG,PlayoutView, AxeKbdKey.SspinnerData);//~1919R~//~vc1sR~//~vc1tR~
//  	axeList=AxeLstKbdLayoutHW.setupListView(this,axeSpinner,layoutId,PlayoutView);//~1613R~//~1919R~//~vc1sR~//~vc1tR~
        dummyFocus=(LinearLayout)PlayoutView.findViewById(R.id.DummyFocus);//~vc2jI~
    	setupListView(PlayoutView);                                //~vc1tI~
	   	axeSpinner.setListener(this);                                 //~1730R~//~1810I~
        setButtonListener(PlayoutView,R.id.Delete);                //~vc1sI~
        btnSoftKbd=setButtonListener(PlayoutView,R.id.SoftKbd);    //~vc1tR~
        setButtonListener(PlayoutView,R.id.Reset);                 //~vc1tI~
	    setOnKeyListener(this);                                    //~vc24I~
        etKbd=(EditText)PlayoutView.findViewById(R.id.etSoftKbd);  //~vc1tI~
        etCurrent=etKbd;                                           //~vc1tI~
        setFocusListener(etKbd);//could not detect softkbd close   //~vc1tI~
        rgAltGr=new URadioGroup(PlayoutView,R.id.rgAltGr,0,rbIDAltGr);//~vc2gI~
        rgAltGr.setCheckedID(AxeG.keyAltGr,false/*swFixed*/);      //~vc2gI~
                                                                   //~vc2jI~
		setIMKey(PlayoutView);                                     //~vc2jI~
//        setEditorActionListener(etKbd);                          //~vc1tR~
                                                                   //~vc1tI~
    	swHardActive=AxeG.isHardKeyboardActive();                  //~vc1tI~
        switchKbdInput(false/*forceSoft*/);                        //~vc1tR~
    	setButtonStatus();                                         //~vc1tI~
        addTextWatcher(etKbd);                                     //~vc1tI~
		setIMEMode();                                              //~vc1tI~
        etIMKey.setInputType(InputType.TYPE_NULL);                 //~vc2jI~
        setFocusListener(etIMKey);//could not detect softkbd close //~vc2jI~
        isSoftKbdOpen();    //set listener  layoutchange           //~vc1tR~
        UView.setDialogWidthMatchParentPortrait(this);             //~vc1tI~
    }                                                              //~1602I~
    //*********************************************************    //~vc2jI~
	private void setIMKey(ViewGroup PlayoutView)                   //~vc2jI~
    {                                                              //~vc2jI~
        rgIMKey=new URadioGroup(PlayoutView,R.id.rgIMKey,UBRGP_IMKEY,rbIDIMKey);//~vc2jI~
        keyOpenIM=AxeG.keyOpenIM;                                  //~vc2jI~
	    if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.setIMKey keyOpenIM="+Integer.toHexString(keyOpenIM));//~vc2jR~
        if (keyOpenIM<rbID_IMKeyOther)  //non or zen-han           //~vc2jI~
        {                                                          //~vc2jI~
        	rgIMKey.setCheckedID(keyOpenIM,false/*swFixed*/);      //~vc2jR~
            keycodeOpenIM=-1;                                      //~vc2jR~
        }                                                          //~vc2jI~
        else                                                       //~vc2jI~
        {                                                          //~vc2jI~
        	rgIMKey.setCheckedID(rbID_IMKeyOther,false/*swFixed*/);//~vc2jI~
            keycodeOpenIM=keyOpenIM;                               //~vc2jI~
        }                                                          //~vc2jI~
        rgIMKey.setListener(this);                              //~9705I~//~vc2jI~
                                                                   //~vc2jI~
        etIMKey=(EditText)PlayoutView.findViewById(R.id.etIMKey);  //~vc2jI~
		setETIMKey(keyOpenIM,keycodeOpenIM,false/*requestFocus*/); //~vc2jR~
		setETIMKeyListener();                                      //~vc2jI~
    }                                                              //~vc2jI~
//******************************************************************//~vc2jR~
//*from init and onChangedURG                                      //~vc2jI~
//******************************************************************//~vc2jI~
	private void setETIMKey(int PkeyOpenIM,int Pkeycode/*mod+keycode from init*/,boolean PsetFocus/*true from onChangeURG*/)//~vc2jR~
    {                                                              //~vc2jI~
	    if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.setIMKey PkeyOpenIM="+Integer.toHexString(PkeyOpenIM)+",keycode="+Integer.toHexString(Pkeycode)+",swSetFocus="+PsetFocus);//~vc2jR~
        swFocusETIMKey=etIMKey.hasFocus();                         //~vc2jI~
        etIMKey.setBackgroundColor(swFocusETIMKey ? COLOR_ENABLE : COLOR_DISABLE);//~vc2jI~
        if (PkeyOpenIM>=0 && PkeyOpenIM<rbID_IMKeyOther)  //non or zen-han//~vc2jI~
        {                                                          //~vc2jI~
	        etIMKey.setEnabled(false);                             //~vc2jI~
        }                                                          //~vc2jI~
        else                                                       //~vc2jI~
        {                                                          //~vc2jI~
	        etIMKey.setEnabled(true);                              //~vc2jI~
            if (Pkeycode>0) //from init                            //~vc2jR~
            {                                                      //~vc2jI~
        		String keyName=getKeycodeName(Pkeycode);           //~vc2jR~
	        	etIMKey.setText(keyName);                          //~vc2jR~
            }                                                      //~vc2jI~
            if (PsetFocus)                                         //~vc2jI~
            {                                                      //~vc2jI~
	        	etIMKey.requestFocus();                            //~vc2jR~
	    		if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.setIMKey requestFocus");//~vc2jI~
            }                                                      //~vc2jI~
        }                                                          //~vc2jI~
    }                                                              //~vc2jI~
//*********                                                        //~vc2jI~
	private void setETIMKeyListener()                              //~vc2jI~
    {                                                              //~vc2jI~
	    etIMKey.setOnKeyListener(new View.OnKeyListener()          //~vc2jI~
        						{
                                	@Override                      //~vc2jI~
                                	public boolean onKey(View Pview,int Pkeycode,KeyEvent Pevent)//~vc2jI~
                                	{                              //~vc2jI~
                                    	if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.setETIMKey.onOnKeyListener.onKey swFocusETIMKey="+swFocusETIMKey);//~vc2jR~
								        if (!swFocusETIMKey)       //~vc2jR~
								            return false;          //~vc2jI~
                                		return onKeyETIMKey(Pkeycode,Pevent);//~vc2jI~
                                	}                              //~vc2jI~
        						}                                  //~vc2jI~
                        );                                         //~vc2jI~
    }                                                              //~vc2jI~
	private boolean onKeyETIMKey(int Pkeycode,KeyEvent Pevent)        //~vc2jI~
    {                                                              //~vc2jI~
        boolean rc=true;                                           //~vc2jI~
    	int meta=Pevent.getMetaState();                            //~vc2jI~
        int action=Pevent.getAction();                             //~vc2jI~
        int unicode=Pevent.getUnicodeChar();                       //~vc2jR~
	    if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.onKeyETIMKey action="+action+",keycode="+Integer.toHexString(Pkeycode)+",meta="+Integer.toHexString(meta)+",unicode="+Integer.toHexString(unicode));//~vc2jR~
        if (action!=KeyEvent.ACTION_DOWN)                          //~vc2jI~
            return rc;                                             //~vc2jI~
	    String keyName=null;                                       //~vc2jI~
        int imkey=getKeycodeID(Pkeycode,meta,unicode);              //~vc2jR~
        if (imkey<0)	//modifier key                             //~vc2jI~
        	return false;                                          //~vc2jI~
        if (imkey!=0)                                              //~vc2jR~
	        keyName=getKeycodeName(imkey);                         //~vc2jI~
        if (keyName==null)                                         //~vc2jI~
        {                                                          //~vc2jI~
			Utils.showToast(R.string.Err_InvalidIMKeyTyped);       //~vc2jI~
        }                                                          //~vc2jI~
        keycodeOpenIM=imkey;                                       //~vc2jI~
        etIMKey.setText(keyName);                                  //~vc2jI~
	    if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.onKeyETIMKey setText="+keyName+",keycodeOpenIM="+Integer.toHexString(keycodeOpenIM)+",keyname="+keyName);//~vc2jR~
        return rc;                                                 //~vc2jI~
    }                                                              //~vc2jI~
//*********                                                        //~vc2jI~
	protected void setIMEMode()                                          //~vc1tI~//~vc2jR~
    {                                                              //~vc1tI~
	    if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.setIMEMode");  //~vc1tI~
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);//~vc1tR~
    }                                                              //~vc1tI~
//*********                                                        //~vc1tI~
	@Override                                                      //~vc1tI~
	protected void onDismiss()                                     //~vc1tI~
    {                                                              //~vc1tI~
	    if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.onDismiss");   //~vc1tI~
    	AxeG.axeDlgKbdLayoutHW=null;                                //~vc1tI~
        if (swReopen)                                              //~vc2jI~
        	AxeDlgKbdLayoutHW.showDialog();                        //~vc2jI~
    }                                                              //~vc1tI~
////**********************************                             //~vc26I~
////*from Axe;configuration changed                                  //~vc1tR~//~vc26R~
////*return dialog is opening                                        //~vc1tI~//~vc26R~
////**********************************                               //~vc1tI~//~vc26R~
//    public static boolean configChanged(boolean PswChanged)        //~vc1tR~//~vc26R~
//    {                                                              //~vc1sI~//~vc26R~
//        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.configChanged");//~vc1tR~//~vc26R~
//        if (AxeG.axeDlgKbdLayoutHW==null || !AxeG.axeDlgKbdLayoutHW.isShowing())//~vc26R~
//            return false;                                          //~vc1tR~//~vc26R~
//        if (PswChanged)                                            //~vc1tI~//~vc26R~
//        {                                                          //~vc1tI~//~vc26R~
//            AxeG.axeDlgKbdLayoutHW.swHardActive=AxeG.isHardKeyboardActive();//~vc1tR~//~vc26R~
//            AxeG.axeDlgKbdLayoutHW.switchKbdInput(false/*forceSoft*/);//~vc1tR~//~vc26R~
//            AxeG.axeDlgKbdLayoutHW.requestFocusET(false/*PswOpenIM*/);//~vc1tR~//~vc26R~
//            AxeG.axeDlgKbdLayoutHW.setButtonStatus();              //~vc1tR~//~vc26R~
//        }                                                          //~vc1tI~//~vc26R~
//        return true;                                               //~vc1tR~//~vc26R~
//    }                                                              //~vc1sI~//~vc26R~
//**********************************                               //~vc26I~
//*from Axe;configuration changed-->AxeKbdDialog                   //~vc26I~
//*return dialog is opening                                        //~vc26I~
//**********************************                               //~vc26I~
    public void configChanged(boolean PswChanged)                       //~vc26I~
    {                                                              //~vc26I~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.configChanged");//~vc26I~
        if (PswChanged)                                            //~vc26I~
        {                                                          //~vc26I~
            AxeG.axeDlgKbdLayoutHW.swHardActive=AxeG.isHardKeyboardActive();//~vc26I~
            AxeG.axeDlgKbdLayoutHW.switchKbdInput(false/*forceSoft*/);//~vc26I~
            AxeG.axeDlgKbdLayoutHW.requestFocusET(false/*PswOpenIM*/);//~vc26I~
            AxeG.axeDlgKbdLayoutHW.setButtonStatus();              //~vc26I~
        }                                                          //~vc26I~
    }                                                              //~vc26I~
//**********************************                               //~vc2jI~
//*hardKbd was opend after dialog open                             //~vc2jI~
//*restart dialog to set zorder top                                //~vc2jI~
//**********************************                               //~vc2jI~
    public void configChangedReopen()                              //~vc2jI~
    {                                                              //~vc2jI~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.configChangedReopen");//~vc2jI~
        swReopen=true;                                             //~vc2jI~
        dismiss();                                                 //~vc2jI~
    }                                                              //~vc2jI~
//**********************************                               //~vc26I~
    private void statusChangedIME(boolean PswOpen)                 //~vc1tI~
    {                                                              //~vc1tI~
	    if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.statusChangedIME swOpen="+PswOpen);//~vc1tI~
        if (!PswOpen)	//closed                                   //~vc1tI~
	        if (swHardActive)   //hardKbd and not openIM request   //~vc1tI~
    			switchKbdInput(false/*PswForceSoft*/);	//back to hardkbd input mode//~vc1tI~
    }                                                              //~vc1tI~
//**********************************                               //~vc1tI~
    public void switchKbdInput(boolean PswForceSoft)               //~vc1tR~
    {                                                              //~vc1tI~
	    if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.switchKbdInput swHardActive="+swHardActive+",swForceSoft="+PswForceSoft);//~vc1tR~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.switchKbdInput forcusViewID="+Integer.toHexString(UView.getViewFocusedID(this)));//~vc1tR~
        if (swHardActive)   //hardKbd and not openIM request       //~vc1tR~
        {                                                          //~vc1tI~
	        if (PswForceSoft)   //hardKbd and not openIM request   //~vc1tI~
            {                                                      //~vc1tI~
	            etKbd.setInputType(InputType.TYPE_CLASS_TEXT);     //~vc1tR~
	            etKbd.setBackgroundColor(COLOR_SOFTKBD);           //~vc1tI~
            }                                                      //~vc1tI~
            else                                                   //~vc1tI~
            {                                                      //~vc1tI~
            	etKbd.setInputType(InputType.TYPE_NULL);           //~vc1tR~
//          	etKbd.setBackgroundColor(COLOR_HARDKBD);           //~vc1tR~//~vc2jR~
	            etKbd.setBackgroundColor(COLOR_SOFTKBD);    //set when forcus gain//~vc2jI~
            }                                                      //~vc1tI~
        }                                                          //~vc1tI~
        else     //no hardKbd || swOpen                            //~vc1tR~
        {                                                          //~vc1tI~
            etKbd.setInputType(InputType.TYPE_CLASS_TEXT);         //~vc1tR~
            etKbd.setBackgroundColor(COLOR_SOFTKBD);                    //~vc1tI~
        }                                                          //~vc1tI~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.switchKbdInput setEditText="+itemText);//~vc1tI~
        setEditText(itemText);                                     //~vc1tI~
    }                                                              //~vc1tI~
//**********************************                               //~vc1tI~
    public void requestFocusET(boolean PswOpenIM)                  //~vc1tR~
    {                                                              //~vc1tI~
	    if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.requestFocusET swOpenIM="+PswOpenIM+",etCurrent="+Utils.toString(etCurrent));//~vc1tR~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.requestFocusET forcusViewID="+Integer.toHexString(UView.getViewFocusedID(this)));//~vc1tR~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.requestFocusET forcusChildViewID="+UView.getViewFocusedChildID(layoutView));//~vc1tR~
        if (etCurrent==null)                                       //~vc1tI~
        	return;                                                //~vc1tI~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.requestFocusET requestFocus");//~vc1tR~
        requestFocusDelayed(etCurrent);                            //~vc1tI~
        if (!swHardActive || PswOpenIM)   //hardKbd and not openIM request//~vc1tR~
            AxeG.axeIME.showKbdDelayed(etKbd);                     //~vc1tR~
    }                                                              //~vc1tI~
//**********************************                               //~vc1tI~
    private void setEditText(String Ptext)                         //~vc1tI~
    {                                                              //~vc1tI~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.setEditText text="+Utils.toString(Ptext)+",etCurrent="+Utils.toString(etCurrent));//~vc1tI~
    	if (Ptext!=null)                                           //~vc1tI~
        {                                                          //~vc1tI~
            if (etCurrent!=null)                                   //~vc1tI~
            {                                                      //~vc1tI~
                swInternalChange=true;                             //~vc1tI~
                etCurrent.setText(Ptext);                          //~vc1tI~
            }                                                      //~vc1tI~
        }                                                          //~vc1tI~
    }                                                              //~vc1tI~
//**********************************                               //~vc1tI~
    private void setEditTextCursor(String Ptext)                   //~vc1tI~
    {                                                              //~vc1tI~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.setEditTextCorsor text="+Ptext);//~vc1tI~
    	if (Ptext!=null)                                           //~vc1tI~
        {                                                          //~vc1tI~
			etKbd.setSelection(Ptext.length());                     //~vc1tI~
        }                                                          //~vc1tI~
    }                                                              //~vc1tI~
//**********************************                               //~vc1tI~
    protected void setButtonStatus()                                 //~vc1tI~//~vc2jR~
    {                                                              //~vc1tI~
	    if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.setButtonStatus swHardActive="+swHardActive);//~vc1tI~
        if (swHardActive)                                          //~vc1tI~
        {                                                          //~vc1tI~
        	btnSoftKbd.setEnabled(true);                            //~vc1tI~
        }                                                          //~vc1tI~
        else                                                       //~vc1tI~
        {                                                          //~vc1tI~
        	btnSoftKbd.setEnabled(false);                           //~vc1tI~
        }                                                          //~vc1tI~
    }                                                              //~vc1tI~
//**********************************                               //~vc1tI~
//*open IM under hardKbd available                                 //~vc1tI~
//**********************************                               //~vc1tI~
    protected void useSoftKbd()                                    //~vc1tR~
    {                                                              //~vc1tI~
	    if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.useSoftKbd");  //~vc1tI~
        itemText=axeList.chkFocus();                               //~vc1tR~
        if (itemText==null)                                        //~vc1tI~
        {                                                          //~vc1tI~
//  		Utils.showToast(R.string.Err_InvalidCursor);           //~vc1tI~//~vc24R~
            return;                                                //~vc1tI~
        }                                                          //~vc1tI~
        switchKbdInput(true/*forceSoft*/);                         //~vc1tR~
		requestFocusET(true/*PswOpenIM*/);                         //~vc1tR~
    }                                                              //~vc1tI~
//**********************************                               //~vc1tI~
    protected void hideSoftKbd()                                   //~vc1tI~
    {                                                              //~vc1tI~
	    if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.hideSoftKbd"); //~vc1tI~
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);//~vc1tI~
    }                                                              //~vc1tI~
//**********************************                               //~1612I~
	@Override                                                      //~1612I~
    protected boolean onClickHelp()                                //~1821R~
    {                                                              //~1612I~
//  	showDialogHelp(HELP_TITLE,HELP_TEXT);                      //~1919R~//~vc1tR~
    	showDialogHelp(HELP_TITLE,HELP_FILE);                      //~vc1tI~
        return false;	//not dismiss                              //~1821R~
    }                                                              //~1528I~
	@Override                                                      //~1821I~
    protected boolean onClickClose()                               //~1821I~
    {                                                              //~1821I~
    	boolean rc;                                                //~1821I~
    //**********                                                   //~1821I~
		keyOpenIM=getKeyOpenIM();                                  //~vc2jM~
        if (keyOpenIM<0)	//etIMKey was not set                  //~vc2jI~
        {                                                          //~vc2jI~
            Utils.showToast(R.string.Err_InvalidIMKey);            //~vc2jI~
        	return false;                                          //~vc2jI~
        }                                                          //~vc2jI~
        rc=axeList.saveUpdate();	//if err exist                 //~1821I~
        int keyAltGr=rgAltGr.getCheckedID();                      //~vc2gI~
	    putAltGr(keyAltGr);                                           //~vc2gI~
		AxeG.keyOpenIM=keyOpenIM;                                  //~vc2jI~
		AxeG.setParameter(AxeG.PREFKEY_OPENIMKEY,keyOpenIM);            //~vc2jI~
	    if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.onClickClose keyOpenIM="+Integer.toHexString(keyOpenIM));//~vc2jI~
        return rc;                                                 //~1821I~
    }                                                              //~1821I~
	@Override                                                      //~1923I~
    protected boolean onClickOther(int Pbuttonid)                  //~1923I~
    {                                                              //~1923I~
    	//**********                                                   //~1923I~
    	switch(Pbuttonid)                                           //~1923I~
        {                                                          //~1923I~
        case R.id.Reset:                                           //~1923I~
	    	if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.onClickOther reset");     //~1923I~//~vc1sR~
        	axeList.resetToDefault();                              //~1923I~//~vc1tR~
        	break;                                                 //~1923I~
                                                       //~1923I~
        case R.id.Delete:                                          //~vc1sI~
	    	if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.onClickOther Delete");//~vc1sR~
        	axeList.onClickDelete();          //~vc1sR~            //~vc1tR~
        	break;                                                 //~vc1sI~
        case R.id.SoftKbd:                                         //~vc1tI~
	    	if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.onClickOther SoftKbd");//~vc1tI~
        	useSoftKbd();                                          //~vc1tI~
        	break;                                                 //~vc1tI~
        }                                                          //~vc1sI~
        return false;                                              //~1923I~
    }                                                              //~1923I~
    //*******************************                              //~1809I~
    //*fromAxeSpinner                                              //~vc1sI~
    //*******************************                              //~vc1sI~
    @Override //AxeSpinnerI                                        //~vc1tI~
    public void onSpinnerItemSelected(int Ppos)                    //~1809I~
    {                                                              //~1809I~
	    if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW onSpinnerItemSelected pos="+Ppos);//~vc1sI~
//        isSoftKbdOpen();                                         //~vc1tR~
//        isSoftKbdOpen2();                                        //~vc1tR~
        String text=axeList.setSpinnerItemSelected(Ppos);                       //~vc1sI~//~vc1tR~
		setEditText(text);                                         //~vc1tI~
        setEditTextCursor(text);                                   //~vc1tI~
    }                                                              //~1809I~
    //*******************************                              //~vc1tI~
    //*from AxeLstKbdLayoutHW;clicked not protected column         //~vc1tR~
    //*******************************                              //~vc1tI~
	public void onClickListItem(int Ppos/*line*/,int Pcol/*column*/,String Ptext)//~vc1tR~
    {                                                              //~vc1tI~
	    if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.onClickListItem pos="+Ppos+",col="+Pcol);//~vc1tI~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.onClickListItem curr forcusViewID="+Integer.toHexString(UView.getViewFocusedID(this)));//~vc1tR~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.onClickListItem focusedChild="+UView.getViewFocusedChild(layoutView));//~vc1tI~
        boolean swOpenIM=!swHardActive;                            //~vc1tI~
        itemText=Ptext;                                            //~vc1tI~
        currentRow=Ppos;                                           //~vc1tI~
        currentCol=Pcol;                                           //~vc1tI~
	    switchKbdInput(false/*forceSoft*/);                        //~vc1tR~
		requestFocusET(false/*PswOpenIM*/);                        //~vc1tR~
        setEditTextCursor(Ptext);                                  //~vc1tI~
    }                                                              //~vc1tI~
    //*******************************                              //~vc1tI~
    //*from AxeLstKbdLayoutHW;onClickDelete                        //~vc1tI~
    //*******************************                              //~vc1tI~
	public void clearET()                                          //~vc1tI~
    {                                                              //~vc1tI~
	    if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.clearET");      //~vc1tI~
        itemText="";                                                //~vc1tI~
        setEditText("");                                           //~vc1tR~
    }                                                              //~vc1tI~
  //*************************************************************  //~vc1tI~
    protected void addTextWatcher(EditText PeditText)              //~vc1tR~
    {                                                              //~vc1tI~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.addTextWatcher to"+PeditText.toString());//~vc1tR~
        EditTextField.addWatcher(this,PeditText);                       //~vc1tI~
    }                                                              //~vc1tI~
    //*************************************************************//~vc1tI~
    @Override //EditTextListener OnKeyListener                     //~vc1tI~
	public void onEditTextChanged(int Pviewid,int Pvalue){}        //~vc1tI~
    @Override //EditTextListener                                   //~vc1tI~
	public void onEditTextChanged(int Pviewid,String Pvalue){}     //~vc1tI~
    //*************************************************************//~vc1tR~
    @Override //EditTextListener Watcher                           //~vc1tR~
    public void beforeTextChangedETF(EditText PeditText,String Ptext,int start,int count,int after)//~vc1tR~
    {                                                              //~vc1tI~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.beforeTextChangeETF swInternalChange="+swInternalChange+",curr forcusViewID="+Integer.toHexString(UView.getViewFocusedID(this)));//~vc1tR~
        if (swInternalChange)                                      //~vc1tI~
        	return;                                                //~vc1tI~
//        if (axeList.chkFocus()==null)                                   //~vc1tI~//~vc24R~
//        {                                                          //~vc1tI~//~vc24R~
//            Utils.showToast(R.string.Err_InvalidCursor);           //~vc1tI~//~vc24R~
//        }                                                          //~vc1tI~//~vc24R~
        axeList.chkFocus();                                        //~vc24I~
    }                                                              //~vc1tI~
    @Override //EditTextListener                                   //~vc1tR~
    public void onTextChangedETF(EditText PeditText,String Ptext,int start/*rep start pos*/,int before/*replaced length*/,int count/*new length*/)//~vc1tR~
    {                                                              //~vc1tI~
	    if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.onTextChangedETF swInternalChange="+swInternalChange+",curr forcusViewID="+Integer.toHexString(UView.getViewFocusedID(this)));//~vc1tR~
        if (swInternalChange)                                      //~vc1tI~
        	return;                                                //~vc1tI~
        itemText=Ptext; //for setEditText at IM closed             //~vc1tI~
        axeList.onEditTextChanged(Ptext,start,before,count);       //~vc1tR~
    }                                                              //~vc1tI~
    @Override //EditTextListener                                   //~vc1tR~
    public void afterTextChangedETF(EditText PeditText,String Ptext)//~vc1tR~
    {                                                              //~vc1tI~
	    if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.afterTextChangedETF swInternalChange="+swInternalChange+",curr forcusViewID="+Integer.toHexString(UView.getViewFocusedID(this)));//~vc1tR~
    	if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.afterTextChangedETF text="+Ptext);//~vc1tR~
	    if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.afterTextChangedETF curr forcusViewID="+Integer.toHexString(UView.getViewFocusedID(this)));//~vc1tR~
        swInternalChange=false;                                    //~vc1tI~
	    if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.afterTextChangedETF exit swInternalChange="+swInternalChange);//~vc1tI~
    }                                                              //~vc1tI~
//****************************                                   //~vc1tR~//~vc24R~
    @Override                                                      //~vc24R~
    public boolean onKey(DialogInterface Pdlg,int Pkeycode,android.view.KeyEvent Pevent)//~vc1tR~//~vc24R~
    {                                                            //~vc1tR~//~vc24R~
        int action;                                              //~vc1tR~//~vc24R~
        boolean rc=false;   //goto textchanged                   //~vc1tR~//~vc24R~
        //**********************                                 //~vc1tR~//~vc24R~
        try                                                      //~vc1tR~//~vc24R~
        {                                                        //~vc1tR~//~vc24R~
            if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.onKey action="+Pevent.getAction()+",keycode=0x"+Integer.toHexString(Pkeycode)+",swFocusETIMKey="+swFocusETIMKey);//~vc1tR~//~vc24R~//~vc2jR~
        	if (Pkeycode==AxeKeyValue.AKC_BACK)                    //~vc24I~
        		return rc;                                         //~vc24I~
            if (swFocusETIMKey)                                    //~vc2jR~
            	return false;                                      //~vc2jI~
			axeList.chkFocus();                                    //~vc24I~
        }                                                        //~vc1tR~//~vc24R~
        catch(Exception e)                                       //~vc1tR~//~vc24R~
        {                                                        //~vc1tR~//~vc24R~
            Dump.println(e,"TextField EditText.OnKey exception");//~vc1tR~//~vc24R~
        }                                                        //~vc1tR~//~vc24R~
        return rc;                                               //~vc1tR~//~vc24R~
    }                                                            //~vc1tR~//~vc24R~
//****************************                                     //~vc1tI~
    protected void setFocusListener(View Pview)                      //~vc1tI~//~vc2jR~
    {                                                              //~vc1tI~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW Listener view="+Pview.toString());//~vc1tI~
    	Pview.setOnFocusChangeListener(new FocusChangeListener(Pview));//~vc1tI~
    }                                                              //~vc1tI~
    class FocusChangeListener implements View.OnFocusChangeListener     //~vc1tI~
    {                                                              //~vc1tI~
    	private View view;                                         //~vc1tI~
    	public FocusChangeListener(View Pview)                     //~vc1tI~
        {                                                          //~vc1tI~
        	view=Pview;                                            //~vc1tI~
        }                                                          //~vc1tI~
        @Override                                                  //~vc1tI~
        public void onFocusChange(View Pview, boolean Phasfocus)    //~vc1tI~
        {                                                          //~vc1tI~
            if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.onFocusChanged to "+Phasfocus+",view="+Pview.toString());//~vc1tR~
            swFocusETIMKey=false;                                  //~vc2jI~
            if (Pview.getId()==R.id.etIMKey)                       //~vc2jR~
            {                                                      //~vc2jI~
            	swFocusETIMKey=Phasfocus;                          //~vc2jI~
	            etIMKey.setBackgroundColor(Phasfocus?COLOR_ENABLE:COLOR_DISABLE);//~vc2jR~
            }
            else                                                    //~vc2jI~
            if (Pview.getId()==R.id.etSoftKbd)                         //~vc2jI~
            {                                                      //~vc2jI~
            	if (Phasfocus)                                     //~vc2jI~
					if (!axeList.isItemFocused())                  //~vc2jI~
                    {                                              //~vc2jI~
                    	dummyFocus.requestFocus();                 //~vc2jR~
    	            	return;                                    //~vc2jI~
                    }                                              //~vc2jI~
	            etKbd.setBackgroundColor(Phasfocus?COLOR_ENABLE:COLOR_DISABLE);//~vc2jI~
            }                                                      //~vc2jI~
        }                                                          //~vc1tI~
    }//class FocusChangeListener                                   //~vc1tI~
                                                                   //~vc1tI~
    public void requestFocusDelayed(View Pview)                    //~vc1tI~
    {                                                              //~vc1tI~
    	if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.requestFocusDelayed view="+Pview.toString());//~vc1tI~
//      showSoftKbd();                                             //~vc1tR~
        new AxeTimer(this/*callback*/,50/*milisec delay*/,false/*repeat*/,Pview/*parameter*/).start();//~vc1tR~
	}                                                              //~vc1tI~
    @Override                                                      //~vc1tI~
    public void onTimerExpired(AxeTimer Ptimer,int Pcallctr,Object Pparm)//~vc1tI~
    {                                                              //~vc1tI~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.onTimerExpired forcusViewID="+Integer.toHexString(UView.getViewFocusedID(this)));//~vc1tR~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.inTimerExpired forcusChildView="+UView.getViewFocusedChild(layoutView));//~vc1tR~
    	if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.onTimerExpired requestFocus view="+Pparm.toString());//~vc1tR~
        ((View)Pparm).requestFocus();                                 //~vc1tI~
    }                                                              //~vc1tI~
    public void showSoftKbd()                                      //~vc1tI~
    {                                                              //~vc1tI~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.showSoftKbd"); //~vc1tI~
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);//~vc1tI~
    }                                                              //~vc1tI~
//    //****************************                               //~vc1tR~
//    private void setEditorActionListener(TextView Pview)         //~vc1tR~
//    {                                                            //~vc1tR~
//        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.setEditorActionListener");//~vc1tR~
//        Pview.setOnEditorActionListener(this);                   //~vc1tR~
//    }                                                            //~vc1tR~
//    //**********************************************************************//~vc1tR~
//    //actionID:EditorInfo.IME_ACTION_XXX                         //~vc1tR~
//    //**********************************************************************//~vc1tR~
//    @Override                                                    //~vc1tR~
//    public boolean onEditorAction(TextView Pview, int PactionID, KeyEvent Pevent)//~vc1tR~
//    {                                                            //~vc1tR~
//        boolean rc=false;   //continue process                   //~vc1tR~
//        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.onEditorAction actionID="+PactionID+",event="+Utils.toString(Pevent)+",view="+Pview.toString());//~vc1tR~
//        return rc;                                               //~vc1tR~
//    }                                                            //~vc1tR~
//    //**********************************************************************//~vc1tR~
//    public boolean isSoftKbdOpen2() //TODO test                  //~vc1tR~
//    {                                                            //~vc1tR~
//        boolean rc; //continue process                           //~vc1tR~
//        InputMethodManager imm=(InputMethodManager)AxeG.context.getSystemService(AxeG.context.INPUT_METHOD_SERVICE);//~vc1tR~
//        rc=imm.isAcceptingText();                                //~vc1tR~
//        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.isSoftKbdOpen2 rc="+rc);//~vc1tR~
//        return rc;                                               //~vc1tR~
//    }                                                            //~vc1tR~
    //**********************************************************************//~vc1tI~
    public boolean isSoftKbdOpen()                                 //~vc1tR~
    {                                                              //~vc1tI~
	    if (rootView==null)                                        //~vc1tR~
        {                                                          //~vc1tI~
            rootView=(ViewGroup)layoutView.getRootView();                     //~vc1tR~
            hhDialog=rootView.getHeight();                         //~vc1tI~
			if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.isSoftKbdOpen hhDialog="+hhDialog+",rootView="+rootView);//~vc1tI~
        	rootView.getViewTreeObserver().addOnGlobalLayoutListener//~vc1tI~
			(                                                      //~vc1tI~
				new ViewTreeObserver.OnGlobalLayoutListener()                       //~vc1tI~
                {                                                  //~vc1tI~
                	@Override                                      //~vc1tI~
                    public void onGlobalLayout()                               //~vc1tI~
                    {                                              //~vc1tI~
                        int hh=rootView.getRootView().getHeight(); //~vc1tR~
				        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.isSoftKbdOpen onGlobalLayout height current="+hh+",initial="+hhDialog);//~vc1tR~
                        if (hhDialog==0)                           //~vc1tI~
                        {                                          //~vc1tI~
                            hhDialog=hh;                           //~vc1tI~
                            return;                                //~vc1tI~
                        }                                          //~vc1tI~
                        boolean old=swSoftKbdOpen;                 //~vc1tI~
                        swSoftKbdOpen=hh<hhDialog;                 //~vc1tM~
                        if (swSoftKbdOpen!=old)                    //~vc1tR~
                        {                                          //~vc1tI~
                        	Utils.showToast(swSoftKbdOpen ? "IME Open" : "IME Close");//TODO test//~vc1tR~
                        	statusChangedIME(swSoftKbdOpen);       //~vc1tI~
                        }                                          //~vc1tI~
                    }                                              //~vc1tI~
                }                                                  //~vc1tI~
            );                                                     //~vc1tI~
        }                                                          //~vc1tI~
		if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.isSoftKbdOpen rc="+swSoftKbdOpen);//~vc1tI~
    	return swSoftKbdOpen;                                      //~vc1tR~
    }                                                              //~vc1tI~
    //**********************************************************************//~vc26I~
    public static boolean isShowingDlg()                              //~vc26I~
    {                                                              //~vc26I~
    	boolean rc=AxeG.axeDlgKbdLayoutHW!=null &&  AxeG.axeDlgKbdLayoutHW.isShowing();//~vc26I~
		if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.isShowing rc="+rc);//~vc26I~
        return rc;                                                 //~vc26I~
    }                                                              //~vc26I~
    //**********************************************************************//~vc2gI~
    //*from AxeG                                                   //~vc2gI~
    //**********************************************************************//~vc2gI~
    public static void setAltGrKey()                               //~vc2gR~
    {                                                              //~vc2gI~
        Gxeh.axeStatus &=~AXES_ALTG_MASK;                          //~vc2gI~
    	switch (AxeG.keyAltGr)                                     //~vc2gI~
        {                                                          //~vc2gI~
        case 1:	//R.id.rbAltGrRightAlt                             //~vc2gI~
        	Gxeh.axeStatus|=AXES_ALTG_RIGHTALT;                    //~vc2gI~
        	break;                                                 //~vc2gI~
        case 2:	//R.id.rbAltGrLeftAlt                              //~vc2gI~
        	Gxeh.axeStatus|=AXES_ALTG_LEFTALT;                     //~vc2gI~
        	break;                                                 //~vc2gI~
        case 3:	//R.id.rbAltGrRightShift                           //~vc2gI~
        	Gxeh.axeStatus|=AXES_ALTG_RIGHTSHIFT;                  //~vc2gI~
        	break;                                                 //~vc2gI~
        }                                                          //~vc2gI~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW setAltGr keyAltGr="+Integer.toHexString(AxeG.keyAltGr)+",axeStatus="+Integer.toHexString(Gxeh.axeStatus));//~vc2gI~
    }                                                              //~vc2gI~
    //**********************************************************************//~vc2gI~
    public static void putAltGr(int PaltGrKey)                     //~vc2gI~
    {                                                              //~vc2gI~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW putAltGr PkeyAltGr="+Integer.toHexString(PaltGrKey));//~vc2gI~
        AxeG.putAltGrKey(PaltGrKey);	//also update axeStatus                //~vc2gI~
    }                                                              //~vc2gI~
    //**********************************************************************//~vc2jI~
    private int getKeyOpenIM()                                     //~vc2jI~
    {                                                              //~vc2jI~
        int rc=rgIMKey.getCheckedID();                             //~vc2jI~
        if (rc==rbID_IMKeyOther)                                   //~vc2jI~
        	rc=keycodeOpenIM;                                      //~vc2jI~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW getKeyOpenIM key="+Integer.toHexString(rc));//~vc2jI~
        return rc;
    }                                                              //~vc2jI~
    //*******************************************************      //~9220I~//~vc2jI~
    //*URadioGroupI                                                //~9220I~//~vc2jI~
    //*******************************************************      //~9220I~//~vc2jI~
    @Override                                                      //~9220I~//~vc2jI~
    public void onChangedURG(int PrbID,int Pparm)                  //~9220I~//~vc2jI~
    {                                                              //~9220I~//~vc2jI~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.onChangedURG parm="+Pparm+",btnid="+Integer.toHexString(PrbID));//~9220I~//~vc2jR~
        switch (Pparm)                                             //~9220I~//~vc2jI~
        {                                                          //~9220I~//~vc2jI~
        case UBRGP_IMKEY:                                          //~vc2jI~
        	keyOpenIM=rgIMKey.searchID(PrbID);                     //~vc2jR~
			setETIMKey(keyOpenIM,-1/*set keyname at onKey*/,true/*setFocus*/);//~vc2jR~
            break;                                                 //~9220I~//~vc2jR~
        }                                                          //~9220I~//~vc2jI~
    }                                                              //~9220I~//~vc2jI~
    //*******************************************************      //~vc2jI~
    private static boolean isModifierKey(int Pkeycode)                 //~vc2jI~
    {                                                              //~vc2jI~
    	boolean rc=                                                //~vc2jI~
   					Pkeycode==KEYCODE_ALT_LEFT	//                             57  (0x00000039)//~vc2jI~
   				||  Pkeycode==KEYCODE_ALT_RIGHT //                           58  (0x0000003a)//~vc2jI~
   				||  Pkeycode==KEYCODE_SHIFT_LEFT//                           59  (0x0000003b)//~vc2jI~
   				||  Pkeycode==KEYCODE_SHIFT_RIGHT//                          60  (0x0000003c)//~vc2jI~
   				||  Pkeycode==KEYCODE_CTRL_LEFT  //                         113  (0x00000071)//~vc2jI~
   				||  Pkeycode==KEYCODE_CTRL_RIGHT //                         114  (0x00000072)//~vc2jI~
                ;                                                  //~vc2jI~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.isModifierKey keycode="+Integer.toHexString(Pkeycode)+",rc="+rc);//~vc2jR~
        return rc;                                                 //~vc2jI~
   	}                                                              //~vc2jI~
    //*******************************************************      //~vc2jI~
    //*chk valid for IMKey                                         //~vc2YI~
    //*******************************************************      //~vc2YI~
    private static int getKeycodeID(int Pkeycode,int Pmeta,int Punicode)//~vc2jI~
    {                                                              //~vc2jI~
    	int rc=0;                                                  //~vc2jR~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.getKeycodeID keycode="+Integer.toHexString(Pkeycode)+",meta="+Integer.toHexString(Pmeta)+",unicode="+Integer.toHexString(Punicode));//~vc2jR~
        if (isModifierKey(Pkeycode))                               //~vc2jI~
        	return -1;                                             //~vc2jR~
        int mod=AxeKeyValue.metaToMod(Pmeta);	//KBF_             //~vc2jI~
//      int ch=AxeKbdDialogHW.keycodeToCode(Pkeycode);             //~vc2jR~//~vc2ER~
        int ch=AxeKbdDialogHW.keycodeToCode(Pkeycode,Pmeta);       //~vc2EI~
        if (AxeKeyValue.getIMKeyID(Pkeycode)!=0)                   //~vc2jI~
        	rc=KBF_IM|mod|Pkeycode;                                //~vc2jI~
        else                                                       //~vc2jI~
        if (AxeKeyValue.isExtendedKey(Pkeycode))                   //~vc2jI~
        {                                                          //~vc2jI~
        	if (Punicode==0)                                       //~vc2jR~
	        	rc=KBF_GDK|Pkeycode|mod;                           //~vc2jR~
        }                                                          //~vc2jI~
        else                                                       //~vc2jM~
        if (ch>0x20 && ch<0x7f)  //tab=x09,enter=0x0d is extended  //~vc2jM~//~vc2YR~
        {                                                          //~vc2jI~
//      	if ((Punicode & KeyCharacterMap.COMBINING_ACCENT_MASK)>=0x80)//~vc2jR~
//      		rc=KBF_UNICODE|(Punicode & KeyCharacterMap.COMBINING_ACCENT_MASK);//~vc2jR~
//      	else                                                   //~vc2jR~
            if ((mod & (KBF_ALT|KBF_CONTROL))!=0)                  //~vc2YI~
        		rc=ch|mod;                                         //~vc2jI~
        }                                                          //~vc2jI~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.getKeycodeID mod="+Integer.toHexString(mod)+",rc="+Integer.toHexString(rc));//~vc2jR~//~vc2YR~
        return rc;
    }                                                              //~vc2jI~
    //*******************************************************      //~vc2jI~
    private static String getKeycodeName(int PkeyID)               //~vc2jR~
    {                                                              //~vc2jI~
    	String nm=null;                                            //~vc2jR~
        int key=PkeyID & 0xffff;                                   //~vc2jI~
        int mod=PkeyID & 0x00ff0000;                               //~vc2jI~
        String strMod=AxeKeyValue.modifierToString(mod,null);      //~vc2jR~
        if ((PkeyID & KBF_IM)!=0)                                  //~vc2jI~
        	nm=strMod+Utils.getStr(AxeKeyValue.getIMKeyID(key));   //~vc2jR~
        else                                                       //~vc2jI~
        if ((PkeyID & KBF_UNICODE)!=0)                            //~vc2jI~
        	nm=AxeLstKbdLayoutHW.getKeynameUnicode(key);           //~vc2jI~
        else                                                       //~vc2jI~
        if ((PkeyID & KBF_GDK)!=0)                                //~vc2jI~
        	nm=strMod+AxeKeyValue.getExtendedKeyname(key);         //~vc2jR~
        else                                                       //~vc2jI~
        if ((key>0x20 && key<0x7f) && (mod & (KBF_ALT|KBF_CONTROL))!=0)//~vc2jR~
	        nm=strMod+String.valueOf((char)key);                   //~vc2jR~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.getKeycodeName keyID="+Integer.toHexString(PkeyID)+",rc="+nm);//~vc2jR~
        return nm;                                                 //~vc2jI~
    }                                                              //~vc2jI~
    //*******************************************************      //~vc2jI~
    public static boolean isOpenIMKey(int Pkeycode,KeyEvent Pevent)//~vc2jI~
    {                                                              //~vc2jI~
    	boolean rc;                                                //~vc2jI~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.isOpenIMKey keycode="+Integer.toHexString(Pkeycode)+",AxeG.keyOpenIM="+Integer.toHexString(AxeG.keyOpenIM));//~vc2jR~
        AxeDlgKbdLayoutHW dlg=AxeG.axeDlgKbdLayoutHW;              //~vc2jI~
        if (dlg==null || !dlg.isShowing())                         //~vc2jI~
        {                                                          //~vc2jI~
	    	int meta=Pevent.getMetaState();                        //~vc2jI~
    	    int action=Pevent.getAction();                         //~vc2jI~
        	int unicode=Pevent.getUnicodeChar();                   //~vc2jI~
    	    if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.isOpenIMKey action="+action+",meta="+Integer.toHexString(meta)+",unicode="+Integer.toHexString(unicode));//~vc2jR~
    	    if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.isOpenIMKey AxeG.keyOpenIM="+Integer.toHexString(AxeG.keyOpenIM));//+vc2YI~
            rc=false;                                              //~vc2jI~
            if (action==KeyEvent.ACTION_DOWN)                      //~vc2jI~
            {                                                      //~vc2jI~
			    int keyID=getKeycodeID(Pkeycode,meta,unicode);         //~vc2jI~
//              if (keyID==AxeG.keyOpenIM)                         //~vc2jI~//~vc2YR~
                if (AxeG.keyOpenIM!=0 && keyID==AxeG.keyOpenIM)    //~vc2YI~
                {                                                  //~vc2jI~
                	openIM();                                      //~vc2jI~
                	rc=true;                                       //~vc2jI~
                }                                                  //~vc2jI~
            }                                                      //~vc2jI~
	        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.rc="+rc);  //~vc2jI~
            return rc;                                             //~vc2jI~
        }                                                          //~vc2jI~
        if (dlg.swFocusETIMKey)                                    //~vc2jI~
        {                                                          //~vc2jI~
            dlg.onKeyETIMKey(Pkeycode,Pevent);                         //~vc2jI~
        	rc=true;                                               //~vc2jR~
        }                                                          //~vc2jI~
        else                                                       //~vc2jI~
        	rc=false;                                              //~vc2jI~
    	if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.isOpenIMKey swFocusETIMKey="+dlg.swFocusETIMKey+",rc="+rc);//~vc2jI~
        return rc;                                                 //~vc2jR~
    }                                                              //~vc2jI~
    //*******************************************************      //~vc2jI~
    private static void openIM()                                   //~vc2jI~
    {                                                              //~vc2jI~
        if (Dump.Y) Dump.println("AxeDlgKbdLayoutHW.openIM");      //~vc2jR~
//      AxeG.axeIME.showKbdP();                                    //~vc2jR~
        AxeG.axeIME.showKbdPDelayed();	//delayed to avoid focus lost by onkey up//~vc2jR~
    }                                                              //~vc2jI~
}//class                                                           //~1612R~
