//*CID://+dateR~: update#= 160;                                    //~1107R~
//**********************************************************************//~1107I~
//*AlerDialog                                                      //~1527R~
//**********************************************************************//~1107I~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1527R~

import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;



//**********************************************************************//~1107I~
public class AxeDlgBtnLayout extends AxeDialog                     //~1602R~
{                                                                  //~0914I~
	private static final int DIALOG_BUTTON_LAYOUT =R.layout.dialogbuttonlayout;//~1528I~
	private static final int TITLE_BUTTON_LAYOUT  =R.string.DialogTitle_ButtonLayout;//~1528I~
                                                                   //~1602I~
	EditText editText1,editText2;                                  //~1602I~
//  CheckBox stickyView,useLandView;                               //~1822R~
    CheckBox disableRepeatable;                                    //~1923I~
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
    	if (Dump.Y)	Dump.println("AxeDlgBtnLayout setupDialogExtend");//+4B24I~
		editText1=(EditText)layoutView.findViewById(R.id.ButtonNo1);//~1602I~
		editText2=(EditText)layoutView.findViewById(R.id.ButtonNo2);//~1602I~
        editText1.setText(""+AxeG.axeButtonLayout.buttonNo1);      //~1602I~
        editText2.setText(""+AxeG.axeButtonLayout.buttonNo2);      //~1602I~
        disableRepeatable=(CheckBox)layoutView.findViewById(R.id.Repeatable);//~1923I~
        disableRepeatable.setChecked(AxeButtonAction.disableRepeatable);//~1923I~
//        stickyView=(CheckBox)layoutView.findViewById(R.id.StickyModifier);//~1609R~
//        stickyView.setChecked(AxeG.axeButtonLayout.sticky);      //~1609R~
//		useLandView=(CheckBox)layoutView.findViewById(R.id.UseLandscape);//~1822R~
//      useLandView.setChecked(AxeButtonLayout.getUseLand());      //~1822R~
    }                                                              //~1602I~
//************************                                         //~1602R~
//*dialog button Listener*                                         //~1602R~
//************************                                         //~1602R~
	@Override                                                      //~1602I~
    protected boolean onClickHelp()                                //~1821R~
    {                                                              //~1528I~
    	showDialogHelp(R.string.HelpTitle_ButtonLayout,R.string.Help_ButtonLayout);//~1821R~
        return false;	//no dismiss                               //~1821R~
    }                                                              //~1528I~
//************************                                         //~1821I~
	@Override                                                      //~1821I~
    protected boolean onClickClose()                               //~1821I~
    {                                                              //~1821I~
    	boolean rc=true;	//dismiss                              //~1821I~
        int no1,no2;                                               //~1821I~
    //****************                                             //~1821I~
        CheckBox checkbox=(CheckBox)layoutView.findViewById(R.id.Reset);//~1821I~
        boolean reset=checkbox.isChecked();                        //~1821I~
        AxeButtonAction.disableRepeatable=disableRepeatable.isChecked();//~1923I~
//      boolean useLand=useLandView.isChecked();                   //~1822R~
//      AxeG.axeButtonLayout.setUseLand(useLand);                  //~1822R~
        if (reset)                                                 //~1821I~
        {                                                          //~1821I~
            AxeG.axeButtonLayout.restoreDefaultLayout();           //~1821I~
        }                                                          //~1821I~
        else                                                       //~1821I~
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
}//class AxeDialog                                                 //~1528R~
