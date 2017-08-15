//*CID://+dateR~: update#= 168;                                    //~1107R~
//**********************************************************************//~1107I~
//*AlerDialog                                                      //~1527R~
//**********************************************************************//~1107I~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1527R~

import android.view.ViewGroup;
import android.widget.RadioGroup;

//**********************************************************************//~1107I~
public class AxeDlgDefShift extends AxeDialog                  //~1612I~//~1818R~
{                                                                  //~0914I~
	public  static final int DIALOG  =R.layout.dialogdefshift;//~1528R~//~1818R~
	private static final int TITLE   =R.string.DialogTitle_DefaultShiftMap;//~1528I~//~1818R~
                                                                   //~1809I~
	private AxeDlgShiftList dlgshift;                              //~1818R~
//**********************************                               //~1211I~
	public AxeDlgDefShift()                                    //~1612I~//~1818R~
    {                                                              //~1612I~
    	super(DIALOG);                                //~1612I~    //~1809R~
    }                                                              //~1612I~
//**********************************
    public static AxeDlgDefShift show(AxeDlgShiftList PaxeDialog)                        //~1612R~//~1818R~
    {
     	AxeDlgDefShift axeDialog=new AxeDlgDefShift();
    	axeDialog.dlgshift=PaxeDialog;//~1612R~    //~1818R~
        String title=Utils.getResourceString(TITLE);  //~1602R~    //~1809R~
		axeDialog.showDialog(title);                               //~1602R~
        return axeDialog;                                          //~1601I~
    }
//*********                                                        //~1602I~
	@Override                                                      //~1612I~
	protected void setupDialogExtend(ViewGroup PlayoutView)          //~1602R~
    {                                                              //~1602I~
    //*radiobutton only                                            //~1818I~
    }                                                              //~1602I~
//**********************************                               //~1612I~
	@Override                                                      //~1612I~
    protected boolean onClickClose()                               //+1821R~
    {                                                              //~1612I~
    	boolean rc=true;//dismiss                                                //~1601I~
        RadioGroup rg;                                             //~1818I~
        int buttonid;                                              //~1818I~
    //**********                                                   //~1601I~
        rg=(RadioGroup)layoutView.findViewById(R.id.DEFAULT_SHIFT_MAP);//~1818I~
        buttonid=rg.getCheckedRadioButtonId();                     //~1818I~
    	if (Dump.Y) Dump.println("AxeDefShift onclose radiobutnid="+Integer.toHexString(buttonid));//~1818I~
        if (buttonid<0)                                            //~1818I~
        {                                                          //~1818I~
			Utils.showToastLong(R.string.Err_DefaultShift_NoneChecked);//~1818I~
        	return false;                                          //~1818I~
        }                                                          //~1818I~
        dlgshift.reset2Default(buttonid);                          //~1818R~
        return rc;                                                 //~1601I~
    }                                                              //~1528I~
}//class                                                           //~1612R~
