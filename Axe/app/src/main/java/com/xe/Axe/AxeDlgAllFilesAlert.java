//*CID://+vc51R~: update#=  267;                                   //~vc51R~
//**********************************************************************//~vaagI~
//vc51 2023/05/30 avoid dismiss by button(need to set listener after show)//~vc51I~
//vc50 2023/05/30 support help button for AxeAlert                 //~vc50I~
//vc4y 2023/05/22 >=android11(Api30),access all file option setting by setting-android related dialog//~vc4yI~
//**********************************************************************//~vaagI~
package com.xe.Axe;                                                //~@@@@I~

import com.ahsv.AG;
import com.ahsv.dialog.HelpDialog;

import android.app.AlertDialog;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;

public class AxeDlgAllFilesAlert                                   //~vc4yR~
	implements AxeAlertI                                           //~vc4yI~
{                                                                  //~1606I~
//**********************************************************************//~1725I~
	private static final int LAYOUT=R.layout.dialogallfilesalert;      //~1821R~//~vc4yR~
	private static final int TITLE =R.string.DialogTitle_AllFiles;//~1821R~//~vc4yR~
	private static final String HELP_FILE  ="AxeDlgAllFilesAlert"; //~vc50R~
                                                                   //~1822I~
    private CheckBox   cbStopPopup;                      //~vaagI~ //~vc4yR~
	private boolean swRequestManageAllFile;                        //~vc4yI~
	AxeAlert dlg;                                                  //~vc4yI~
  	AlertDialog.Builder pbuilder;                                  //~vc4yI~
//**********************************                               //~1725I~
    public static void showDialog()                                //~vc4yR~
    {                                                              //~1725I~
        if (Dump.Y) Dump.println("AxeDlgAllFilesAlert showDialog");//~vc4yR~
        AxeDlgAllFilesAlert inst=new AxeDlgAllFilesAlert();        //~vc4yI~
        inst.show();                                               //~vc4yI~
    }                                                              //~1725I~
//**********************************                               //~vc4yI~
    public void show()                                      //~vc4yI~
    {                                                              //~vc4yI~
        if (Dump.Y) Dump.println("AxeDlgAllFilesAlert show");      //~vc4yI~
//      int flag=AxeAlert.BUTTON_CLOSE/*OK*/|AxeAlert.CB_ONSHOW/*dismissCallback*/|AxeAlert.CB_CUSTOM/*customiz view*/;//~vc4yR~
        int flag=AxeAlert.BUTTON_POSITIVE/*OK*/                    //~vc4yI~
                |AxeAlert.BUTTON_NEGATIVE/*No*/                    //~vc4yI~
//              |AxeAlert.BUTTON_NUTRAL/*Cancel*/                  //~vc4yI~//~vc50R~
                |AxeAlert.BUTTON_HELP/*help*/                      //~vc50I~
                |AxeAlert.CB_CUSTOM/*call issue show()*/           //~vc50I~
				|AxeAlert.CB_ONSHOW/*dismissCallback*/;            //~vc4yI~
		dlg=AxeAlert.simpleAlertDialog(this/*callback*/,TITLE,R.string.descAllFilesAlert,flag);//~vc4yR~
//      setCustomLayout(dlg);                                      //~vc4yR~//~vc50R~//+vc51R~
    	dlg.show();                                                //~vc4yR~//~vc50R~
        setCustomLayout(dlg);                                      //+vc51I~
    }                                                              //~vc4yI~
//****************************************************************************//~vc4yI~
	@Override                                                      //~vc4yI~
	public int alertButtonAction(int PbuttonID,int Pitempos)      //~vc4yI~
    {                                                              //~vc4yI~
        if (Dump.Y) Dump.println("AxeDlgAllFilesAlert alertButtonAction btnID="+Integer.toHexString(PbuttonID));//~vc4yI~
//      onClickClose();                                            //~vc4yR~
        if (PbuttonID==AxeAlert.BUTTON_POSITIVE)                            //~vc4yI~
            onClickClose(false/*open setting dialog*/);            //~vc4yI~
        else                                                       //~vc4yI~
        if (PbuttonID==AxeAlert.BUTTON_NEGATIVE)                            //~vc4yI~
            onClickClose(true/*fix AllFiles=NO*/);                 //~vc4yI~
        else                                                       //~vc50I~
        if (PbuttonID==AxeAlert.BUTTON_HELP)                       //~vc50I~
            onClickHelp();                                         //~vc50I~
        return 0;//~vc4yI~
    }                                                              //~vc4yI~
//****************************************************************************//~vc4yI~
	@Override                                                      //~vc4yI~
	public int alertOnShow(AxeAlert Paxealert,boolean Pdismiss)   //~vc4yI~
    {                                                              //~vc4yI~
		if (Pdismiss)
            onDismiss();
        return 0;                                                  //~vc4yI~
    }                                                              //~vc4yI~
//*********                                                        //~1725I~
	private void setCustomLayout(AxeAlert Pdlg)                                 //~vc4yR~
    {                                                              //~1725I~
    //********************                                         //~1821M~
    	if (Dump.Y) Dump.println("AxeDlgAllFilesAlert setCustomLayout");//~vc4yI~
//      View v=AxeG.inflater.inflate(LAYOUT,null);                 //~vc4yR~//~vc50R~
//      FrameLayout fl=(FrameLayout)(Pdlg.pdlg.findViewById(android.R.id.custom));//~vc4yR~
//      fl.addView(v);                                             //~vc4yR~
//  	Pdlg.pbuilder.setView(v);                                  //~vc4yI~//~vc50R~
        Pdlg.pdlg.setCancelable(false);                                      //~vc4yI~
        Pdlg.setButtonHelpNoDismiss();                        //~vc51I~
//  	cbStopPopup=(CheckBox)v.findViewById(R.id.cbStopPopup);//~vc2RI~//~vc4yR~//~vc50R~
    }                                                              //~1821M~
    protected boolean onClickClose(boolean PswStopPopup/*replyed NO*/)                              //~1821R~//~1826R~//~vc4yR~
    {                                                              //~1821M~
    	boolean rc=true;	//dismiss                              //~1821I~
    //****************                                             //~1821M~
//      boolean swStopPopup=cbStopPopup.isChecked();    //~vaagI~  //~vc4yR~
        if (Dump.Y) Dump.println("AxeDlgAllFilesAlert onClickClose swStopPopup="+PswStopPopup);//~vc4yI~
        AxeG.setNoAllFile(PswStopPopup);                           //~vc4yR~
        if (!PswStopPopup)                                          //~vc4yI~
	    	swRequestManageAllFile=true;                           //~vc4yI~
        return rc;                                                 //~1821I~
    }                                                              //~1821M~
//******************                                               //~vc50I~
    protected boolean onClickHelp()                                //~vc50I~
    {                                                              //~vc50I~
    	boolean rc=false;	//dismiss                              //~vc50R~
    //****************                                             //~vc50I~
        if (Dump.Y) Dump.println("AxeDlgAllFilesAlert onClickHelp");//~vc50I~
        HelpDialog.newInstance(TITLE,HELP_FILE).showDlg();         //~vc50I~
        return rc;                                                 //~vc50I~
    }                                                              //~vc50I~
//******************                                               //~vc4yI~
    protected void onDismiss()                                     //~vc4yR~
    {                                                              //~vc4yI~
        if (Dump.Y) Dump.println("AxeDlgAllFilesAlert onDismiss swRequestManageAllFile="+swRequestManageAllFile);//~vc4yR~
    	if (swRequestManageAllFile)                                //~vc4yI~
	    	AG.aUSAF.onClickChangeAllFiles();                 //~vc4yR~
    }                                                              //~vc4yI~
}                                                                  //~1528R~
