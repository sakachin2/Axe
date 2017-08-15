//*CID://+dateR~: update#= 164;                                    //~1107R~
//**********************************************************************//~1107I~
//*AlerDialog                                                      //~1527R~
//**********************************************************************//~1107I~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1527R~

import android.view.ViewGroup;

//**********************************************************************//~1107I~
public class AxeDlgTermBtnList extends AxeDialog                  //~1612I~//~1809R~
	implements AxeSpinnerI                                         //~1809I~
{                                                                  //~0914I~
	public  static final int DIALOG  =R.layout.dialogtermbtn;//~1528R~//~1809R~
	private static final int TITLE   =R.string.DialogTitle_TerminalButton;//~1528I~//~1809R~
                                                                   //~1809I~
	private AxeSpinner axeSpinner;                                 //~1809I~
//**********************************                               //~1211I~
	public AxeDlgTermBtnList()                                    //~1612I~//~1809R~
    {                                                              //~1612I~
    	super(DIALOG);                                //~1612I~    //~1809R~
    }                                                              //~1612I~
//**********************************
    public static AxeDlgTermBtnList showDialog()                        //~1612R~//~1809R~
    {
    	AxeDlgTermBtnList axeDialog=new AxeDlgTermBtnList();//~1612R~//~1809R~
        String title=Utils.getResourceString(TITLE);  //~1602R~    //~1809R~
		axeDialog.showDialog(title);                               //~1602R~
        return axeDialog;                                          //~1601I~
    }
//*********                                                        //~1602I~
	@Override                                                      //~1612I~
	protected void setupDialogExtend(ViewGroup PlayoutView)          //~1602R~
    {                                                              //~1602I~
        axeSpinner=AxeSpinner.create(DIALOG,PlayoutView,AxeLstTermBtn.tgtkeynametbl);//~1809I~
		axeList=AxeLstTermBtn.setupListView(axeSpinner,layoutId,PlayoutView);//~1613R~//~1813R~
	   	axeSpinner.setListener(this);                                 //~1730R~//~1810I~
    }                                                              //~1602I~
//**********************************                               //~1612I~
	@Override                                                      //~1612I~
    protected boolean onClickHelp()                                //+1821R~
    {                                                              //~1612I~
		showDialogHelp(R.string.HelpTitle_TerminalButton,R.string.Help_TerminalButton);//~1604I~//+1821R~
        return false;	//not dismiss                              //+1821R~
    }                                                              //~1528I~
	@Override                                                      //+1821I~
    protected boolean onClickClose()                               //+1821I~
    {                                                              //+1821I~
    	boolean rc;                                                //+1821I~
    //**********                                                   //+1821I~
        rc=axeList.saveUpdate();	//if err exist                 //+1821I~
        return rc;                                                 //+1821I~
    }                                                              //+1821I~
    //*******************************                              //~1809I~
    @Override		//AxeSpinnerI                                  //~1809I~
    public void onSpinnerItemSelected(int Ppos)                    //~1809I~
    {                                                              //~1809I~
    	axeList.onSpinnerItemSelected(Ppos);                       //~1809I~
    }                                                              //~1809I~
}//class                                                           //~1612R~
