//*CID://+vc2uR~: update#= 161;                                    //+vc2uR~
//**********************************************************************//~1107I~
//vc2u 2020/08/08 Help  by file                                    //+vc2uI~
//**********************************************************************//+vc2uI~
//*AlerDialog                                                      //~1527R~
//**********************************************************************//~1107I~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1527R~

import android.view.ViewGroup;



//**********************************************************************//~1107I~
public class AxeDlgShortcutList extends AxeDialog                  //~1612I~
{                                                                  //~0914I~
	public  static final int DIALOG_SHORTCUT_MAP  =R.layout.dialogshortcut;//~1528R~
	private static final int TITLE_SHORTCUT_MAP   =R.string.DialogTitle_ShortcutkeyMap;//~1528I~
	private static final int HELP_TITLE=R.string.HelpTitle_ShortcutKeyMap;//+vc2uI~
	private static final String HELP_FILE  ="AxeDlgShortcutList";  //+vc2uI~
//**********************************                               //~1211I~
	public AxeDlgShortcutList()                                    //~1612I~
    {                                                              //~1612I~
    	super(DIALOG_SHORTCUT_MAP);                                //~1612I~
    }                                                              //~1612I~
//**********************************
    public static AxeDlgShortcutList showDialog()                        //~1612R~
    {
    	AxeDlgShortcutList axeDialog=new AxeDlgShortcutList();//~1612R~
        String title=Utils.getResourceString(TITLE_SHORTCUT_MAP);  //~1602R~
		axeDialog.showDialog(title);                               //~1602R~
        return axeDialog;                                          //~1601I~
    }
//*********                                                        //~1602I~
	@Override                                                      //~1612I~
	protected void setupDialogExtend(ViewGroup PlayoutView)          //~1602R~
    {                                                              //~1602I~
		axeList=AxeLstShortcut.setupListView(layoutId,PlayoutView);//~1613R~
    }                                                              //~1602I~
//**********************************                               //~1612I~
	@Override                                                      //~1612I~
    protected boolean onClickHelp()                                //~1821R~
    {                                                              //~1612I~
//  	showDialogHelp(R.string.HelpTitle_ShortcutKeyMap,R.string.Help_ShortcutKeyMap);//~1821R~//+vc2uR~
    	showDialogHelp(HELP_TITLE,HELP_FILE);                      //+vc2uI~
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
}//class                                                           //~1612R~
