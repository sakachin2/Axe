//*CID://+vc4yR~: update#=  250;                                   //~vc4yR~
//**********************************************************************//~vaagI~
//vc4y 2023/05/22 >=android11(Api30),access all file option setting by setting-android related dialog//~vc4yI~
//**********************************************************************//~vaagI~
package com.xe.Axe;                                                //~@@@@I~

import com.ahsv.AG;
import com.ahsv.utils.UView;

import android.view.ViewGroup;
import android.widget.CheckBox;
                                                                   //~vc4yI~
public class AxeDlgAllFiles extends AxeDialog                     //~1821R~//~vc4yR~
{                                                                  //~1606I~
//**********************************************************************//~1725I~
	private static final int LAYOUT=R.layout.dialogallfiles;      //~1821R~//~vc4yR~
	private static final int TITLE =R.string.DialogTitle_AllFiles;//~1821R~//~vc4yR~
                                                                   //~1822I~
    private CheckBox   cbStopPopup;                      //~vaagI~ //~vc4yR~
	private boolean swRequestManageAllFile;                        //~vc4yI~
//**********************************                               //~1725I~
	public AxeDlgAllFiles()                                       //~1821R~//~vc4yR~
    {                                                              //~1725I~
    	super(LAYOUT);                                             //~1821R~
    }                                                              //~1725I~
//**********************************                               //~1725I~
    public static AxeDlgAllFiles showDialog()                 //~1821R~//~vc4yR~
    {                                                              //~1725I~
        if (Dump.Y) Dump.println("AxeDlgAllFiles showDialog");     //~vc4yI~
    	AxeDlgAllFiles dlg=new AxeDlgAllFiles();                 //~1821R~//~vc4yR~
        String title=Utils.getResourceString(TITLE);               //~1821R~
		dlg.showDialog(title);                                     //~1725I~
        return dlg;                                                //~1725I~
    }                                                              //~1725I~
//*********                                                        //~1725I~
	@Override                                                      //~1725I~
	protected void setupDialogExtend(ViewGroup PlayoutView)        //~1725I~
    {                                                              //~1725I~
    //********************                                         //~1821M~
	   UView.setDialogWidthMatchParentPortrait(this/*Dialog*/);//~9925I~//~vc4yI~
        setCancelable(false);                                      //~vc4yI~
		cbStopPopup=(CheckBox)layoutView.findViewById(R.id.cbStopPopup);//~vc2RI~//~vc4yR~
    }                                                              //~1821M~
	@Override                                                      //~1821I~
    protected boolean onClickClose()                              //~1821R~//~1826R~
    {                                                              //~1821M~
    	boolean rc=true;	//dismiss                              //~1821I~
    //****************                                             //~1821M~
        if (Dump.Y) Dump.println("AxeDlgAllFiles onClickClose");   //~vc4yI~
        boolean swStopPopup=cbStopPopup.isChecked();    //~vaagI~  //~vc4yR~
        AxeG.setNoAllFile(swStopPopup);                            //~vc4yI~
        if (!swStopPopup)                                          //~vc4yI~
	    	swRequestManageAllFile=true;                           //~vc4yI~
        return rc;                                                 //~1821I~
    }                                                              //~1821M~
//******************                                               //~vc4yI~
	@Override                                                      //~vc4yI~
    protected void onDismiss()                                     //+vc4yR~
    {                                                              //~vc4yI~
        if (Dump.Y) Dump.println("AxeDlgAllFiles onDismiss swRequestManageAllFile="+swRequestManageAllFile);//~vc4yR~
    	if (swRequestManageAllFile)                                //~vc4yI~
	    	AG.aUSAF.onClickChangeAllFiles();                      //~vc4yI~
    }                                                              //~vc4yI~
}                                                                  //~1528R~
