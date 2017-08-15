//*CID://+dateR~: update#= 166;                                    //~1107R~
//**********************************************************************//~1107I~
//*AlerDialog                                                      //~1527R~
//**********************************************************************//~1107I~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1527R~

import android.view.ViewGroup;

//**********************************************************************//~1107I~
public class AxeDlgKbdLayout extends AxeDialog                  //~1612I~//~1919R~
	implements AxeSpinnerI                                         //~1809I~
{                                                                  //~0914I~
	public  static final int DIALOG     =R.layout.dialogkbdlayout; //~1919R~
	private static final int TITLE      =R.string.DialogTitle_KbdLayout;//~1528I~//~1919R~
	private static final int HELP_TITLE =R.string.HelpTitle_KbdLayout;//~1919I~
	private static final int HELP_TEXT  =R.string.Help_KbdLayout; //~1919I~
                                                                   //~1809I~
	private AxeSpinner axeSpinner;                                 //~1809I~
//**********************************                               //~1211I~
	public AxeDlgKbdLayout()                                    //~1612I~//~1919R~
    {                                                              //~1612I~
    	super(DIALOG);                                //~1612I~    //~1809R~
    }                                                              //~1612I~
//**********************************
    public static AxeDlgKbdLayout showDialog()                        //~1612R~//~1919R~
    {
    	AxeDlgKbdLayout axeDialog=new AxeDlgKbdLayout();//~1612R~  //~1919R~
        String title=Utils.getResourceString(TITLE);  //~1602R~    //~1809R~
		axeDialog.showDialog(title);                               //~1602R~
        return axeDialog;                                          //~1601I~
    }
//*********                                                        //~1602I~
	@Override                                                      //~1612I~
	protected void setupDialogExtend(ViewGroup PlayoutView)          //~1602R~
    {                                                              //~1602I~
        axeSpinner=AxeSpinner.create(DIALOG,PlayoutView,AxeLstKbdLayout.tgtkeynametbl);//~1919R~
		axeList=AxeLstKbdLayout.setupListView(axeSpinner,layoutId,PlayoutView);//~1613R~//~1919R~
	   	axeSpinner.setListener(this);                                 //~1730R~//~1810I~
    }                                                              //~1602I~
//**********************************                               //~1612I~
	@Override                                                      //~1612I~
    protected boolean onClickHelp()                                //~1821R~
    {                                                              //~1612I~
		showDialogHelp(HELP_TITLE,HELP_TEXT);                      //~1919R~
        return false;	//not dismiss                              //~1821R~
    }                                                              //~1528I~
	@Override                                                      //~1821I~
    protected boolean onClickClose()                               //~1821I~
    {                                                              //~1821I~
    	boolean rc;                                                //~1821I~
    //**********                                                   //~1821I~
        rc=axeList.saveUpdate();	//if err exist                 //~1821I~
        return rc;                                                 //~1821I~
    }                                                              //~1821I~
	@Override                                                      //+1923I~
    protected boolean onClickOther(int Pbuttonid)                  //+1923I~
    {                                                              //+1923I~
    	//**********                                                   //+1923I~
    	switch(Pbuttonid)                                           //+1923I~
        {                                                          //+1923I~
        case R.id.Reset:                                           //+1923I~
	    	if (Dump.Y) Dump.println("AxeDlgKbdLayout reset");     //+1923I~
        	((AxeLstKbdLayout)axeList).resetToDefault();                              //+1923I~
        	break;                                                 //+1923I~
        }                                                          //+1923I~
        return false;                                              //+1923I~
    }                                                              //+1923I~
    //*******************************                              //~1809I~
    @Override		//AxeSpinnerI                                  //~1809I~
    public void onSpinnerItemSelected(int Ppos)                    //~1809I~
    {                                                              //~1809I~
    	axeList.onSpinnerItemSelected(Ppos);                       //~1809I~
    }                                                              //~1809I~
}//class                                                           //~1612R~
