//*CID://+vc53R~: update#= 166;                                    //+vc53R~
//**********************************************************************//~1107I~
//vc53 2023/06/12 java error;switch-case requres constant          //+vc53I~
//**********************************************************************//+vc53I~
//*AlerDialog                                                      //~1527R~
//**********************************************************************//~1107I~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1527R~

import android.view.ViewGroup;



//**********************************************************************//~1107I~
public class AxeDlgShiftList extends AxeDialog                                             //~1528R~
{                                                                  //~0914I~
	public  static final int DIALOG_SHIFTKEY_MAP  =R.layout.dialogshiftkey;//~1528R~
	private static final int TITLE_SHIFTKEY_MAP   =R.string.DialogTitle_ShiftkeyMap;//~1528I~
//**********************************                               //~1211I~
	public AxeDlgShiftList()                                       //~1612R~
    {                                                              //~1211I~
    	super(DIALOG_SHIFTKEY_MAP);                                //~1612I~
    }                                                              //~1211I~
//  public static AxeDlgShiftList show(int Pmenuid)                //~1817R~
    public static AxeDlgShiftList showDialog()                           //~1817I~
    {
    	AxeDlgShiftList axeDialog=new AxeDlgShiftList();            //~1612R~
//      axeDialog.menuId=Pmenuid;                                  //~1817R~
        String title=Utils.getResourceString(TITLE_SHIFTKEY_MAP);  //~1612R~
		axeDialog.showDialog(title);                               //~1602R~
        return axeDialog;                                          //~1601I~
    }
//*********                                                        //~1612I~
	@Override                                                      //~1612I~
	protected void setupDialogExtend(ViewGroup PlayoutView)        //~1612I~
    {                                                              //~1528I~
//		axeList=AxeLstShift.setupListView(layoutId,layoutView,menuId);//~1817R~
  		axeList=AxeLstShift.setupListView(layoutId,layoutView);    //~1817I~
    }                                                              //~1528I~
//************************                                         //~1602R~
	@Override                                                      //~1612I~
    protected boolean onClickHelp()                                //~1821R~
    {                                                              //~1528I~
		showDialogHelp(R.string.HelpTitle_ShiftKeyMap,R.string.Help_ShiftKeyMap);//~1821R~
        return false;	//not dismiss                              //~1821R~
    }                                                              //~1528I~
//************************                                         //~1821I~
	@Override                                                      //~1821I~
    protected boolean onClickClose()                               //~1821I~
    {                                                              //~1821I~
    	boolean rc=false;	//dismiss                              //~1821I~
    //**********                                                   //~1821I~
        rc=axeList.saveUpdate();	//if err exist                 //~1821I~
        return rc;                                                 //~1821I~
    }                                                              //~1821I~
//************************                                         //~1821I~
	@Override                                                      //~1821I~
    protected boolean onClickOther(int PbuttonId)                  //~1821I~
    {                                                              //~1821I~
    	boolean rc=false;	//dismiss                              //~1821I~
    //**********                                                   //~1821I~
//    	switch(PbuttonId)                                          //~1821I~//+vc53R~
//      {                                                          //~1821I~//+vc53R~
//      case R.id.Reset:                                           //~1821I~//+vc53R~
      	if (PbuttonId==R.id.Reset)                                 //+vc53I~
        {                                                          //+vc53I~
    		AxeDlgDefShift.show(this);                             //~1821I~
        }                                                          //~1821I~
        return rc;                                                 //~1821I~
    }                                                              //~1821I~
//************************                                         //~1818I~
    protected void reset2Default(int PbuttonId)                    //~1818I~
    {                                                              //~1818I~
    	((AxeLstShift)axeList).reset2Default(PbuttonId);                          //~1818I~
    }                                                              //~1818I~
}//class AxeDialog                                                 //~1528R~
