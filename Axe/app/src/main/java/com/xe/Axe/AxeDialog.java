//*CID://+vc2DR~: update#= 187;                                    //+vc2DR~
//**********************************************************************//~1107I~
//vc2D 2020/08/19 (Bug)kbd send not to Dialog but to AxeScreen when KbdDialogHW opened after AxeDialog Open//+vc2DI~
//vc1u 2020/07/06 helpdialog for asset/helptexts                   //~vc1uI~
//vay7:141122 (Axe)actionBar:save/saveas item                      //~vay7I~
//vagF:120920 (Axe)local html viewer fail by permission err(uid of process of HtmlViewer was checked)//~vagFI~
//vaaC:120110 close preedit when IM was closed by back key         //~2111I~
//**********************************************************************//~2111I~
//*AlerDialog                                                      //~1527R~
//**********************************************************************//~1107I~
package com.xe.Axe;//~1107R~  //~1108R~//~1527R~
import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.content.DialogInterface;                            //~2111I~

import com.ahsv.dialog.HelpDialog;

//**********************************************************************//~1107I~
public class AxeDialog extends Dialog                              //~1830R~
{                                                                  //~0914I~
	private static final int buttonGroup          =R.id.DialogButtons;       //~1528I~
//    private static final int DIALOG_BUTTON_LAYOUT =R.layout.dialogbuttonlayout;//~1602R~
//    public  static final int DIALOG_SHORTCUT_MAP  =R.layout.dialogshortcut;//~1612R~
//    public  static final int DIALOG_SHIFTKEY_MAP  =R.layout.dialogshiftkey;//~1612R~
//    public  static final int DIALOG_EXTKEY        =R.layout.dialogextkey;//~1602R~
                                                                   //~1531I~
//    private static final int TITLE_BUTTON_LAYOUT  =R.string.DialogTitle_ButtonLayout;//~1602R~
//    private static final int TITLE_SHORTCUT_MAP   =R.string.DialogTitle_ShortcutkeyMap;//~1612R~
//    private static final int TITLE_SHIFTKEY_MAP   =R.string.DialogTitle_ShiftkeyMap;//~1612R~
    public  static final String NO_TITLE="NoTitle";                //~vagFI~
//	protected Dialog androidDialog;                                  //~1818R~//~1830R~
	protected ViewGroup layoutView;                                  //~1818R~
	protected AxeList axeList;                                     //~1818R~
    protected int layoutId;                                          //~1818R~
    protected boolean dialogSetupError;                            //~vay7R~
    //**********************************                               //~1211I~
	public AxeDialog(int Playoutid)                                //~1601R~
    {                                                              //~1211I~
        super(AxeG.context);
		layoutId=Playoutid;                                        //~1601I~
        AxeG.axeDialog=this;                                       //+vc2DR~
    }                                                              //~1211I~
//**********************************
//    public static AxeDialog buttonLayout()                       //~1602R~
//    {                                                            //~1602R~
//        AxeDialog axeDialog=new AxeDialog(DIALOG_BUTTON_LAYOUT); //~1602R~
//        String title=Utils.getResourceString(TITLE_BUTTON_LAYOUT);//~1602R~
//        axeDialog.showDialog(title);                             //~1602R~
//        return axeDialog;                                        //~1602R~
//    }                                                            //~1602R~
//    public static AxeDialog shortcutkeyMap()                     //~1612R~
//    {                                                            //~1612R~
//        AxeDialog axeDialog=new AxeDialog(DIALOG_SHORTCUT_MAP);  //~1612R~
//        String title=Utils.getResourceString(TITLE_SHORTCUT_MAP);//~1612R~
//        axeDialog.showDialog(title);                             //~1612R~
//        return axeDialog;                                        //~1612R~
//    }                                                            //~1612R~
//    public static AxeDialog shiftkeyMap(int Pmenuid)             //~1612R~
//    {                                                            //~1612R~
//        AxeDialog axeDialog=new AxeDialog(DIALOG_SHIFTKEY_MAP);  //~1612R~
//        axeDialog.menuId=Pmenuid;                                //~1612R~
//        String title=Utils.getResourceString(TITLE_SHIFTKEY_MAP);//~1612R~
//        axeDialog.showDialog(title);                             //~1612R~
//        return axeDialog;                                        //~1612R~
//    }                                                            //~1612R~
//    public static AxeDialog selectExtkey(ListData PlistData)     //~1602R~
//    {                                                            //~1602R~
//        AxeDialog axeDialog=new AxeDialog(DIALOG_EXTKEY);        //~1602R~
//        axeDialog.listData=PlistData;                            //~1602R~
//        String title=Utils.getResourceString(TITLE_EXTKEY)+"\""+AxeKey.keyToString(PlistData.key1,"??")+"\"";//~1602R~
//        axeDialog.showDialog(title);                             //~1602R~
//        return axeDialog;                                        //~1602R~
//    }                                                            //~1602R~
//**********************************
	public void showDialog(String Ptitle)                         //~1602R~
    {                                                              //~1528I~
//    	androidDialog=new android.app.Dialog(AxeG.context);                               //~1214I~//~1307R~//~1830R~
		layoutView=(ViewGroup)(AxeG.inflater.inflate(layoutId,null));//~1601R~
//      androidDialog.setContentView(layoutView);                        //~1214I~//~1310I~//~1830R~
      if (Ptitle.equals(NO_TITLE))                                 //~vagFI~
	    requestWindowFeature(Window.FEATURE_NO_TITLE);  //must before setcontent//~vagFI~
      else                                                         //~vagFI~
        setTitle(Ptitle);                                          //~vagFI~
        setupDialogExtendPre(layoutView);	//before setContentView//~vagFI~
        setContentView(layoutView);                                //~1830I~
//      androidDialog.setTitle(Ptitle);                            //~1830R~
//      setTitle(Ptitle);                                          //~vagFR~
        switch(layoutId)                                            //~1601I~
        {                                                          //~1601I~
//        case DIALOG_SHORTCUT_MAP:                                //~1612R~
//        case DIALOG_SHIFTKEY_MAP:                                //~1612R~
//            setupListView(layoutId,layoutView);                  //~1612R~
//            break;                                               //~1612R~
//      case DIALOG_EXTKEY:                                        //~1602R~
//          setupSpinnerView(layoutId,layoutView);                 //~1602R~
//          break;                                                 //~1602R~
        default:                                                   //~1602I~
            setupDialogExtend(layoutView);                          //~1602I~
        }                                                          //~1601I~
        if (dialogSetupError)                                      //~vay7R~
        	return;                                                //~vay7I~
        setButtonListenerAll(layoutView);                          //~1602R~
        setOnDismissListener(new dismissListener(this));   //~1215I~//~1410R~//~2111I~
//      androidDialog.show();                                      //~1830R~
        show();                                                    //~1830R~
    }                                                              //~1528I~
//*********                                                        //~1831I~
	@Override                                                      //~1831I~
	public void onWindowFocusChanged(boolean PhasFocus)         //~1831I~
    {                                                              //~1831I~
		if (Dump.Y) Dump.println("AxeDialog.onwindowFocusChanged:"+PhasFocus+","+this.toString());//~1831R~//~vc1uR~
    }                                                              //~1831I~
//*********                                                        //~1602I~
	protected void setupDialogExtend(ViewGroup PlayoutView)          //~1602R~
    {                                                              //~1602I~
//override by Extender                                             //~1602I~
    }                                                              //~1602I~
//*********                                                        //~vagFI~
	protected void setupDialogExtendPre(ViewGroup PlayoutView)     //~vagFI~
    {                                                              //~vagFI~
//override by Extender                                             //~vagFI~
    }                                                              //~vagFI~
//**********************************                               //~1528I~
//    public void setupListView(int PlayoutId,ViewGroup Pvg)       //~1613R~
//    {                                                            //~1613R~
//        axeList=AxeList.setupListView(PlayoutId,Pvg,menuId);     //~1613R~
//    }                                                            //~1613R~
//**********************************                               //~1528I~
	private void setButtonListenerAll(ViewGroup Pview)             //~1602R~
    {                                                              //~1528I~
        ViewGroup vg=(ViewGroup)(Pview.findViewById(buttonGroup));//~1528I~
        int ctr=vg.getChildCount();                                //~1528I~
        for (int ii=0;ii<ctr;ii++)                                     //~1528I~
        {                                                          //~1528I~
        	Button btn=(Button)(vg.getChildAt(ii));                //~1529R~
        	int btnid=btn.getId();                                 //~1528I~
            if (Dump.Y) Dump.println("AxeDialog layout="+Integer.toHexString(Pview.getId())+",btnid="+Integer.toHexString(btnid));//~1528I~//~vay7R~
            setButtonListener(btn);                                //~1602R~
        }                                                          //~1528I~
    }                                                              //~1528I~
//*********************                                            //~1528I~
	protected void setButtonListener(View Pbutton)                 //~1919R~
    {                                                              //~1528I~
        ButtonListener cl=this.new ButtonListener(this);           //~1528I~
        Pbutton.setOnClickListener(cl);                            //~1528I~
    }                                                              //~1528I~
//*********************                                            //~vay7I~
	protected Button setButtonListener(ViewGroup Pview,int Pid)    //~vay7R~
    {                                                              //~vay7I~
        Button btn=(Button)(Pview.findViewById(Pid));              //~vay7I~
    	if (Dump.Y) Dump.println("AxeDialog.setButtonListener id="+Integer.toHexString(Pid)+",btn="+com.ahsv.utils.Utils.toString(btn));//~vay7I~
        if (btn!=null)                                             //~vay7I~
        	setButtonListener(btn);                                //~vay7I~
        return btn;                                                //~vay7I~
    }                                                              //~vay7I~
//************************                                         //~1602R~
//*dialog button Listener*                                         //~1602R~
//************************                                         //~1602R~
	private void onClickButtons(int PbuttonId)                     //~1821R~
    {                                                              //~1528I~
        boolean rc=true;                                           //~1528I~
    	if (Dump.Y) Dump.println("AxeDialog onClick layoutid="+Integer.toHexString(layoutId)+",buttonid="+Integer.toHexString(PbuttonId));//~1528I~
    	switch(PbuttonId)                                          //~1821I~
        {                                                          //~1821I~
        case R.id.Help:                                            //~1821I~
        	rc=onClickHelp();                                      //~1821I~
        	break;                                                 //~1821I~
        case R.id.Cancel:                                          //~1821I~
        	rc=onClickCancel();                                    //~1821I~
        	break;                                                 //~1821I~
        case R.id.Close:                                           //~1821I~
        	rc=onClickClose();                                     //~1821I~
        	break;                                                 //~1821I~
        default:                                                   //~1821I~
        	rc=onClickOther(PbuttonId);                            //~1821I~
        }                                                          //~1821I~
        if (rc)                                                    //~1528I~
//        	androidDialog.dismiss();                               //~1830R~
          	dismiss();                                             //~1830I~
    }                                                              //~1528I~
//************                                                     //~1602I~
    protected boolean onClickHelp()                                //~1821R~
    {                                                              //~1602I~
//extender will override
    	return false;                                              //~1821R~
    }                                                              //~1602I~
//************                                                     //~1821I~
    protected boolean onClickCancel()                              //~1821I~
    {                                                              //~1821I~
//extender will override                                           //~1821I~
    	return true;                                               //~1821I~
    }                                                              //~1821I~
//************                                                     //~1821I~
    protected boolean onClickClose()                               //~1821I~
    {                                                              //~1821I~
    	return true;                                               //~1821I~
    }                                                              //~1821I~
//************                                                     //~1821I~
    protected boolean onClickOther(int PbuttonId)                  //~1821I~
    {                                                              //~1821I~
//extender will override                                           //~1821I~
    	return true;                                               //~1821I~
    }                                                              //~1821I~
//************                                                     //~2111I~
    protected void onDismiss()                                     //~2111I~
    {                                                              //~2111I~
//extender will override                                           //~2111I~
    	return;                                                    //~2111I~
    }                                                              //~2111I~
//*********************                                            //~1528I~
    public class ButtonListener implements View.OnClickListener         //~1528I~
    {                                                              //~1528I~
    	AxeDialog axeDialog;                                       //~1528I~
        public ButtonListener(AxeDialog PaxeDialog)                //~1528I~
        {                                                          //~1528I~
        	axeDialog=PaxeDialog;                                  //~1528I~
        }                                                          //~1528I~
        @Override                                                  //~1831I~
        public void onClick(View Pv)                               //~1528I~
        {                                                          //~1528I~
            try                                                    //~1831I~
            {                                                      //~1831I~
            	axeDialog.onClickButtons(Pv.getId());              //~1831R~
            }                                                      //~1831I~
            catch(Exception e)                                     //~1831I~
            {                                                      //~1831I~
                Dump.println(e,"AxeDialog.OnClick");               //~1831I~
            }                                                      //~1831I~
        }                                                          //~1528I~
    }                                                              //~1528I~
//************************************************                 //~1604I~
	public void showDialogHelp(int PtitleResId,int PmsgResId)      //~1604I~
    {                                                              //~1604I~
        String title=Utils.getResourceString(PtitleResId);         //~1A11I~
		String helpmsg=Nls.getText(PmsgResId,title/*as textfile name*/);//~1A11I~
    	int flag=AxeAlert.BUTTON_CLOSE|AxeAlert.SHOW_DIALOG;       //~1604I~
        AxeAlert.simpleAlertDialog(null/*callback*/,title,helpmsg,flag);//~1A11R~
    }                                                              //~1604I~
//************************************************                 //~vc1uI~
	public void showDialogHelp(int PtitleResId,String PhelpFile)   //~vc1uI~
    {                                                              //~vc1uI~
        HelpDialog.newInstance(PtitleResId,PhelpFile).showDlg();        //~vc1uI~
    }                                                              //~vc1uI~
//************************************************                 //~1604I~
	public void setEditTextEnable(EditText Pview,boolean Peditable)//~1604I~
    {                                                              //~1604I~
        Pview.setEnabled(Peditable); 	//currently cannot set editable=false  by setEnabled//~1604I~
        if (Peditable)                                             //~1604I~
	        Pview.setFocusableInTouchMode(true);                   //~1604I~
        else                                                       //~1604I~
	        Pview.setFocusable(false);                             //~1604I~
    }                                                              //~1604I~
//*******************************                                  //~1126M~//~1215M~//~2111I~
//*dismiss listener                                                //~1126I~//~1215M~//~2111I~
//*******************************                                  //~1126I~//~1215M~//~2111I~
    public class dismissListener                                   //~1126M~//~1215M~//~2111I~
    		implements OnDismissListener                           //~1126M~//~1215M~//~2111I~
	{                                                              //~1126M~//~1215M~//~2111I~
    	AxeDialog dlg;                                             //~2111I~
    	public dismissListener(AxeDialog Pdlg)                     //~2111I~
        {                                                          //~2111I~
        	dlg=Pdlg;                                              //~2111I~
        }                                                          //~2111I~
        @Override                                                  //~1126M~//~1215M~//~2111I~
        public void onDismiss(DialogInterface Pdialog)             //~1126M~//~1215M~//~2111I~
        {                                                          //~1126M~//~1215M~//~2111I~
			if (Dump.Y) Dump.println("AxeDialog onDismiss");        //~1127I~//~1506R~//~2111I~
            dlg.onDismiss();	                                   //~2111I~
        }                                                          //~1126M~//~1215M~//~2111I~
    }                                                              //~1126M~//~1215M~//~2111I~
}//class AxeDialog                                                 //~1528R~
