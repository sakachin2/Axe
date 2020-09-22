//*CID://+vc2kR~: update#= 169;                                    //~vc2kR~
//**********************************************************************//~1107I~
//vc2k 2020/07/28 Button layout with hardware keyboard             //~vc2kI~
//**********************************************************************//~vc2kI~
//*AlerDialog                                                      //~1527R~
//**********************************************************************//~1107I~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1527R~

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;



//**********************************************************************//~1107I~
public class AxeDlgBtnLayout extends AxeDialog                     //~1602R~
	implements EditTextListener                                     //~vc2kI~
{                                                                  //~0914I~
	private static final int DIALOG_BUTTON_LAYOUT =R.layout.dialogbuttonlayout;//~1528I~
	private static final int TITLE_BUTTON_LAYOUT  =R.string.DialogTitle_ButtonLayout;//~1528I~
	private static final String HELP_FILE  ="AxeBtnLayout";        //+vc2kI~
                                                                   //~1602I~
	private EditText editText1,editText2;                                  //~1602I~//~vc2kR~
//  CheckBox stickyView,useLandView;                               //~1822R~
    private CheckBox disableRepeatable;                                    //~1923I~//~vc2kR~
    private Button btnDefault,btnForHWKbd;                         //~vc2kI~
    private int layoutID=0;                                        //~vc2kI~
    private static final int LAYOUTID_USER=0;                      //~vc2kI~
    private static final int LAYOUTID_DEFAULT=1;                   //~vc2kI~
    private static final int LAYOUTID_HWKBD=2;                     //~vc2kI~
//**********************************                               //~1211I~
	public AxeDlgBtnLayout()                                       //~1602R~
    {                                                              //~1211I~
    	super(DIALOG_BUTTON_LAYOUT);                               //~1602I~
    }                                                              //~1211I~
//**********************************
    public static AxeDlgBtnLayout showDialog()                           //~1604R~
    {                                                              //~1528I~
    	AxeDlgBtnLayout axeDialog=new AxeDlgBtnLayout();           //~1602R~
        String title=Utils.getResourceString(TITLE_BUTTON_LAYOUT); //~1602R~
		axeDialog.showDialog(title);                               //~1602R~
        return axeDialog;                                          //~1601I~
    }                                                              //~1528I~
//*********                                                        //~1602I~
	@Override                                                      //~1602I~
	protected void setupDialogExtend(ViewGroup PlayoutView)          //~1602R~
    {                                                              //~1602I~
    	if (Dump.Y)	Dump.println("AxeDlgBtnLayout setupDialogExtend");//~4B24I~
		editText1=(EditText)layoutView.findViewById(R.id.ButtonNo1);//~1602I~
		editText2=(EditText)layoutView.findViewById(R.id.ButtonNo2);//~1602I~
        editText1.setText(""+AxeG.axeButtonLayout.buttonNo1);      //~1602I~
        editText2.setText(""+AxeG.axeButtonLayout.buttonNo2);      //~1602I~
        EditTextField.addWatcher(this,editText1);                  //~vc2kI~
        EditTextField.addWatcher(this,editText2);                  //~vc2kI~
        disableRepeatable=(CheckBox)layoutView.findViewById(R.id.Repeatable);//~1923I~
        disableRepeatable.setChecked(AxeButtonAction.disableRepeatable);//~1923I~
//        stickyView=(CheckBox)layoutView.findViewById(R.id.StickyModifier);//~1609R~
//        stickyView.setChecked(AxeG.axeButtonLayout.sticky);      //~1609R~
//		useLandView=(CheckBox)layoutView.findViewById(R.id.UseLandscape);//~1822R~
//      useLandView.setChecked(AxeButtonLayout.getUseLand());      //~1822R~
	    btnDefault=(Button)layoutView.findViewById(R.id.ResetToDefault);//~vc2kI~
        setButtonListener(btnDefault);                             //~vc2kI~
	    btnForHWKbd=(Button)layoutView.findViewById(R.id.ForHardKbd);//~vc2kI~
        setButtonListener(btnForHWKbd);                            //~vc2kI~
    }                                                              //~1602I~
//************************                                         //~1602R~
//*dialog button Listener*                                         //~1602R~
//************************                                         //~1602R~
	@Override                                                      //~1602I~
    protected boolean onClickHelp()                                //~1821R~
    {                                                              //~1528I~
//  	showDialogHelp(R.string.HelpTitle_ButtonLayout,R.string.Help_ButtonLayout);//~1821R~//+vc2kR~
    	showDialogHelp(R.string.HelpTitle_ButtonLayout,HELP_FILE); //+vc2kI~
        return false;	//no dismiss                               //~1821R~
    }                                                              //~1528I~
//************************                                         //~1821I~
	@Override                                                      //~1821I~
    protected boolean onClickClose()                               //~1821I~
    {                                                              //~1821I~
    	boolean rc=true;	//dismiss                              //~1821I~
        int no1,no2;                                               //~1821I~
    //****************                                             //~1821I~
        if (Dump.Y) Dump.println("AxeDialog onClickClose layoutID="+layoutID);//~vc2kI~
//      CheckBox checkbox=(CheckBox)layoutView.findViewById(R.id.Reset);//~1821I~//~vc2kR~
//      boolean reset=checkbox.isChecked();                        //~1821I~//~vc2kR~
        AxeButtonAction.disableRepeatable=disableRepeatable.isChecked();//~1923I~
//      boolean useLand=useLandView.isChecked();                   //~1822R~
//      AxeG.axeButtonLayout.setUseLand(useLand);                  //~1822R~
//      if (reset)                                                 //~1821I~//~vc2kR~
        if (layoutID==LAYOUTID_DEFAULT)                            //~vc2kI~
        {                                                          //~1821I~
            AxeG.axeButtonLayout.restoreDefaultLayout();           //~1821I~
        }                                                          //~1821I~
        else                                                       //~1821I~
        if (layoutID==LAYOUTID_HWKBD)                              //~vc2kI~
        {                                                          //~vc2kI~
            AxeG.axeButtonLayout.restoreHWKbdLayout();             //~vc2kI~
        }                                                          //~vc2kI~
        else                                                       //~vc2kI~
        {                                                          //~1821I~
            no1=getTextNum(editText1);                             //~1821I~
            if (no1<0)                                             //~1821I~
                rc=false;                                          //~1821I~
            no2=getTextNum(editText2);                             //~1821I~
            if (no2<0)                                             //~1821I~
                rc=false;                                          //~1821I~
            if (!rc)                                               //~1821I~
            {                                                      //~1821I~
                Utils.showToast(R.string.Err_Numeric);             //~1821I~
                return false;                                      //~1821I~
            }                                                      //~1821I~
            rc=AxeG.axeButtonLayout.updateButtonLayout(no1,no2);   //~1821I~
        }                                                          //~1821I~
        return rc;                                                 //~1821I~
    }                                                              //~1821I~
//************************************************                 //~1528I~
//*for ButtonLayout                                                //~1601I~
//************************************************                 //~1601I~
	private int getTextNum(EditText Pview)                         //~1602R~
    {                                                              //~1528I~
        int value=Utils.strToNum(Pview.getText().toString(),-1);   //~1602R~
        if (Dump.Y) Dump.println("AxeDialog getTextNum value="+value);//~1528I~
        return value;                                              //~1528I~
    }                                                              //~1528I~
    //****************************************                     //~vc2kI~
	@Override                                                      //~vc2kI~
    protected boolean onClickOther(int PbuttonId)                  //~vc2kI~
    {                                                              //~vc2kI~
    	boolean rc=false;   //not dismiss at return                //~vc2kI~
    //****************                                             //~vc2kI~
        if (Dump.Y) Dump.println("AxeDlgBtnLayout onClickOther buttonid="+Integer.toHexString(PbuttonId));//~vc2kI~
        switch(PbuttonId)                                          //~vc2kI~
        {                                                          //~vc2kI~
        case R.id.ResetToDefault:                                  //~vc2kI~
	        editText1.setText(""+AxeButtonLayout.DEFAULT_BUTTONS); //~vc2kI~
	        editText2.setText(""+AxeButtonLayout.DEFAULT_BUTTONS); //~vc2kI~
		    layoutID=LAYOUTID_DEFAULT;                             //~vc2kM~
            break;                                                 //~vc2kI~
        case R.id.ForHardKbd:                                      //~vc2kI~
	        editText1.setText(""+AxeButtonLayout.HWKBD_BUTTONS1);  //~vc2kR~
	        editText2.setText(""+AxeButtonLayout.HWKBD_BUTTONS2);  //~vc2kI~
		    layoutID=LAYOUTID_HWKBD;                               //~vc2kM~
            break;                                                 //~vc2kI~
        }                                                          //~vc2kI~
        return rc;                                                 //~vc2kI~
    }                                                              //~vc2kI~
    //*************************************************************//~vc2kI~
    @Override //EditTextListener OnKeyListener                     //~vc2kI~
	public void onEditTextChanged(int Pviewid,int Pvalue)          //~vc2kR~
	{                                                              //~vc2kI~
        if (Dump.Y) Dump.println("AxeDlgBtnLayout.onEditTextChanged");//~vc2kI~
		layoutID=LAYOUTID_USER;                                    //~vc2kI~
	}                                                              //~vc2kI~
    @Override //EditTextListener                                   //~vc2kI~
	public void onEditTextChanged(int Pviewid,String Pvalue)       //~vc2kR~
	{                                                              //~vc2kI~
        if (Dump.Y) Dump.println("AxeDlgBtnLayout.onEditTextChanged");//~vc2kI~
		layoutID=LAYOUTID_USER;                                    //~vc2kI~
	}                                                              //~vc2kI~
    //*************************************************************//~vc2kI~
    @Override //EditTextListener Watcher                           //~vc2kI~
    public void beforeTextChangedETF(EditText PeditText,String Ptext,int start,int count,int after)//~vc2kI~
    {                                                              //~vc2kI~
        if (Dump.Y) Dump.println("AxeDlgBtnLayout.beforeTextChangeETF");//~vc2kI~
    }                                                              //~vc2kI~
    @Override //EditTextListener                                   //~vc2kI~
    public void onTextChangedETF(EditText PeditText,String Ptext,int start/*rep start pos*/,int before/*replaced length*/,int count/*new length*/)//~vc2kI~
    {                                                              //~vc2kI~
	    if (Dump.Y) Dump.println("AxeDlgBtnLayout.onTextChangedETF");//~vc2kI~
    }                                                              //~vc2kI~
    @Override //EditTextListener                                   //~vc2kI~
    public void afterTextChangedETF(EditText PeditText,String Ptext)//~vc2kI~
    {                                                              //~vc2kI~
	    if (Dump.Y) Dump.println("AxeDlgBtnLayout");//~vc2kI~
    }                                                              //~vc2kI~
}//class AxeDialog                                                 //~1528R~
