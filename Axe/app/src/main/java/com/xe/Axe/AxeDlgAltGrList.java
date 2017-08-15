//*CID://+dateR~: update#= 158;                                    //~1107R~
//**********************************************************************//~1107I~
//*AlerDialog                                                      //~1527R~
//**********************************************************************//~1107I~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1527R~

import android.view.ViewGroup;



//**********************************************************************//~1107I~
public class AxeDlgAltGrList extends AxeDialog                     //~1612R~
{                                                                  //~0914I~
	public  static final int DIALOG_ALTGR_MAP     =R.layout.dialogaltgrmap;//~1611I~
	private static final int TITLE_ALTGR_MAP      =R.string.DialogTitle_AltGrMap;//~1611I~
//**********************************
	public AxeDlgAltGrList()                                       //~1612I~
    {                                                              //~1612I~
    	super(DIALOG_ALTGR_MAP);                            //~1612I~
    }                                                              //~1612I~
//**********************************
	public static AxeDlgAltGrList showDialog()                           //~1612R~
    {                                                              //~1528I~
    	AxeDlgAltGrList axeDialog=new AxeDlgAltGrList();           //~1612I~
        String title=Utils.getResourceString(TITLE_ALTGR_MAP);     //~1612I~
		axeDialog.showDialog(title);                               //~1612I~
        return axeDialog;                                          //~1612I~
    }                                                              //~1612I~
//*********                                                        //~1602I~
	@Override                                                      //~1612I~
	protected void setupDialogExtend(ViewGroup PlayoutView)          //~1602R~
    {                                                              //~1602I~
		axeList=AxeLstAltGr.setupListView(layoutId,PlayoutView);   //~1613R~
    }                                                              //~1602I~
//**********************************                               //~1528I~
	@Override                                                      //~1612I~
    protected boolean onClickHelp()                                //+1821R~
    {                                                              //~1612I~
		showDialogHelp(R.string.HelpTitle_AltGrMap,R.string.Help_AltGrMap);//+1821R~
        return false;	//not dismiss                              //+1821R~
    }                                                              //~1612I~
//**********************************                               //+1821I~
	@Override                                                      //+1821I~
    protected boolean onClickClose()                               //+1821I~
    {                                                              //+1821I~
    	boolean rc;                                                //+1821I~
    //****************                                             //+1821I~
        rc=axeList.saveUpdate();	//if err exist                 //+1821I~
        return rc;                                                 //+1821I~
    }                                                              //+1821I~
}//class AxeDialog                                                 //~1528R~
