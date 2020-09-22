//*CID://+vc2BR~: update#=    277;                                 //+vc2BR~
//**********************************************************************//~1107I~
//vc2B 2020/08/12 Help  by file                                    //+vc2BI~
//vc1k 2020/06/23 help html from not web but assets                //~vc1kI~
//vaya:141125 (Axe)utilize actionbar:home button click event(customizable by settion,default is home)//~vayaI~
//vay7:141122 (Axe)actionBar:save/saveas item                      //~vay7R~
//vay5:141122 (Axe)actionBar as alternative of menu button for api>=11(android3)//~vay5I~
//            When requestWindowFeature(FEATURE_LEFT_ICON). onCreateOptionsMenu is not called(No ActionBar on android>=3.0)//~vay5I~
//            titlebar touch is not effective,open fildialog by menu item selection//~vay5I~
//            showcontexmenu nest may be limited(actionbar->setup-->buttonconfig dose not show submenu)//~vay5I~
//vaxf:140707 (Axe)change font for help dislog                     //~vaxfI~
//vaxc:140705 (Axe)FileDialog from menutop(from"File" menu,"Open" and Download" to top)//~vaxcI~
//vaiq:130606 Axe:issue initial setup Alert(Be paitient)           //~vaiqI~
//vain:130601 Axe:Bug:popup menu did not processed selected menu item//~vainI~
//vag0:120719 (Axe)function to download asset from web             //~vag0I~
//**********************************************************************//~1107I~
package com.xe.Axe;                                                //~1527I~

import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
//import android.widget.PopupMenu;
import android.annotation.TargetApi;                               //~vayaR~
import android.app.ActionBar;
                                                                   //+vc2BI~
import com.ahsv.dialog.HelpDialog;                                 //+vc2BI~

//**********************************************************************//~1107I~
public class AxeMenu implements AxeAlertI                           //~1527R~
{                                                                  //~0914I~
	MenuInflater inflater;                                         //~1527I~              //~1425R~
	private View menuRegisteredView;                               //~1725R~
    public static final int  POPUPSUBMENUID=0x80;                  //~1328I~
    public static final int  AJAGOHELPMENUID=0x7f00;               //~1411I~
 //   private static final int none=android.view.Menu.NONE;          //~1927I~
                                                                   //~1802I~
    private static final int CBID_CONFIRMSTOP=1;                   //~1802I~
    private static final int[] popupMenuIdTbl={                    //~1927I~
        	R.id.CM_CUT,                                           //~1927I~
        	R.id.CM_COPY,                                          //~1927I~
        	R.id.CM_CLEAR,                                         //~1927I~
        	R.id.CM_PASTEV,                                         //~1927I~//~1A02R~
        	R.id.CM_PASTEINS,                                      //~1927I~
        	R.id.CM_PASTEREP,                                      //~1927I~
        	R.id.CM_SAVE,                                          //~1927I~
        	R.id.CM_END,                                           //~1927I~
        	R.id.CM_DISCARD,                                       //~1927R~
        	R.id.CM_OPENWITH,                                      //~1927I~
            };                                                     //~1927I~
//    private static final int[] popupMenuStringIdTbl={            //~1927R~
//            R.string.ContextMenu_EditCut,                        //~1927R~
//            R.string.ContextMenu_EditCopy,                       //~1927R~
//            R.string.ContextMenu_EditClear,                      //~1927R~
//            R.string.ContextMenu_EditPaste,                      //~1927R~
//            R.string.ContextMenu_EditPasteIns,                   //~1927R~
//            R.string.ContextMenu_EditPasteRep,                   //~1927R~
//            R.string.ContextMenu_FileSave,                       //~1927R~
//            R.string.ContextMenu_FileEnd,                        //~1927R~
//            R.string.ContextMenu_FileDiscard,                    //~1927R~
//            R.string.ContextMenu_FileOpenWith,                   //~1927R~
//            };                                                   //~1927R~
//    private static String[] popupMenuNameTbl=new String[popupMenuIdTbl.length];//~1927R~
    private int[] popupEnableTbl;//~1927I~
//*******                                                          //~1528I~
    private int menuType;                                          //~1528I~
    private static final int MT_CONTEXT=0;	//by long press        //~1528I~
    private static final int MT_OPTMENU=1;  //by optionmenu:menu   //~1528I~
    private static final int MT_OPTOPT =2;  //by optionmenu:option //~1528I~
    public  static final int MT_TITLEBAR=3; //                     //~1820R~
    public  static final int MT_POPUP=4; //                        //~1927I~
    public  static final int MT_ACTIONBAR=5;                       //~vay5I~
//*******************                                              //~1802I~
    private int  callbackId;                                       //~1802I~
    private int  menuresidActionbar;                               //~vay5I~
//******************                                               //~1121I~
	public AxeMenu()                                               //~1527R~
    {                                                              //~1107I~
    	AxeG.axegxedlg=new Axegxedlg();                             //~1715I~                         //~1715I~
    	inflater=AxeG.activity.getMenuInflater();                   //~1527I~
        registerContextMenu(AxeG.mainView); //request callback onCreateContextMenu()//~1527I~
//        setupPopupMenuTitle();                                   //~1927R~
		enableHomeButton();                                        //~vayaI~
    }                                                              //~1107I~
//    private void setupPopupMenuTitle()                           //~1927R~
//    {                                                            //~1927R~
//        int sz=popupMenuIdTbl.length;                            //~1927R~
//        for (int ii=0;ii<sz;ii++)                                //~1927R~
//            popupMenuNameTbl[ii]=AxeG.resource.getString(popupMenuStringIdTbl[ii]);//~1927R~
//    }                                                            //~1927R~
//******************                                               //~1527I~
	public void registerContextMenu(View Pview)                          //~1527I~
    {                                                              //~1527I~
        AxeG.activity.registerForContextMenu(Pview); //request callback onCreateContextMenu()//~1527I~
        if (Dump.Y) Dump.println("AxeMenu registerContextMenu view="+Pview.toString());//~1527I~
		menuRegisteredView=Pview;                                  //~1725I~
    }                                                              //~1527I~
//*********************************************                    //~1527M~
//*Option Menu ********************************                    //~1527M~
//*********************************************                    //~1527M~
//* called only once                                               //~1527M~
 	public  void onCreateOptionMenu(Menu Pmenu)                    //~1527M~
	{                                                              //~1527M~
        if (AxeG.osVersion>=AxeG.HONEYCOMB)  //android3            //~vay5I~
        {                                                          //~vay5I~
            onCreateOptionMenu_V11(Pmenu);                         //~vay5I~
            return;                                                //~vay5I~
        }                                                          //~vay5I~
    	inflater.inflate(R.menu.optionmenu,Pmenu);                 //~1527I~
    }                                                              //~1527M~
//**************                                                   //~vay5I~
 	public void onCreateOptionMenu_V11(Menu Pmenu)                 //~vay5I~
	{                                                              //~vay5I~
        MenuInflater inf=AxeG.activity.getMenuInflater();          //~vay5I~
        inf.inflate(R.menu.actionbar,Pmenu);                       //~vay5I~
    }                                                              //~vay5I~
//**************                                                   //~vay5I~
 	public static void staticCreateOptionMenu(Menu Pmenu)          //~vay5I~
	{                                                              //~vay5I~
        MenuInflater inf=AxeG.activity.getMenuInflater();          //~vay5I~
        inf.inflate(R.menu.actionbar,Pmenu);                       //~vay5I~
    }                                                              //~vay5I~
//**************                                                   //~1527M~
 	public  int onOptionMenuSelected(MenuItem Pitem)               //~1527R~
	{                                                              //~1527M~
        int itemid=Pitem.getItemId();                              //~1527R~
        if (Dump.Y) Dump.println("AxeMenu:OptionMenuSelected="+Integer.toHexString(itemid));//~1314I~ //~1326R~//~1607R~
        switch(itemid)                                             //~1527M~
        {                                                          //~1527M~
        case    R.id.OM_STOP:                                       //~1314R~//~1527R~
        case    R.id.ACTIONBAR_STOP:                               //~vay5R~
            optionStop();                                          //~1527R~
            break;                                                 //~1527M~
        case    R.id.OM_MENU:                                      //~1527R~
            optionMenu();                                          //~1527R~
            break;                                                 //~1527M~
        case    R.id.OM_HELP:                                      //~1820R~
//          optionOption();                                        //~1820R~
            optionHelp();                                          //~1820I~
            break;                                                 //~1527M~
        case    R.id.ACTIONBAR_FILE:                               //~vay5I~
            actionbarFile();                                       //~vay5I~
            break;                                                 //~vay5I~
        case    R.id.ACTIONBAR_EDIT:                               //~vay5I~
            actionbarEdit();                                       //~vay5I~
            break;                                                 //~vay5I~
        case    R.id.ACTIONBAR_SETUP:                              //~vay5I~
            actionbarSetup();                                      //~vay5I~
            break;                                                 //~vay5I~
        case    R.id.ACTIONBAR_HELP:                               //~vay5I~
            actionbarHelp();                                       //~vay5I~
            break;                                                 //~vay5I~
        }                                                          //~1527M~
        return 0;                                                  //~1527M~
    }//selected                                                    //~1527M~
//**************                                                   //~1527M~
    public void optionStop()                                       //~1527R~
    {                                                              //~1527M~
        optionMenuConfirmStop();                                             //~1527M~//~1A02R~
    }                                                              //~1527M~
//******************                                                   //~1527M~//~1A02R~
//* from OptionMenu                                                //~1A02I~
//******************                                               //~1A02I~
    public void optionMenuConfirmStop()                                      //~1527M~//~1802R~//~1A02R~
    {                                                              //~1527M~
    	int flag=AxeAlert.BUTTON_POSITIVE/*OK*/|AxeAlert.BUTTON_PAUSE/*pause*/|AxeAlert.BUTTON_NUTRAL/*Cancel*/|AxeAlert.EXIT;//~1527I~//~1A02R~//~1A22R~
        callbackId=CBID_CONFIRMSTOP;                               //~1802I~
        AxeAlert.simpleAlertDialog(this/*callback*/,0/*title*/,R.string.Qstop,flag);//CB:alertButtonAction//~1929R~//~1A02R~
    }                                                              //~1527M~
//**************                                                   //~1A02I~
//* from xe                                                        //~1A02I~
//**************                                                   //~1A02I~
    public void confirmStop()                                      //~1A02I~
    {                                                              //~1A02I~
    	int flag=AxeAlert.BUTTON_POSITIVE/*OK*/|AxeAlert.BUTTON_NUTRAL/*Cancel*/|AxeAlert.EXIT;//~1A02I~
        callbackId=CBID_CONFIRMSTOP;                               //~1A02I~
        AxeAlert.simpleAlertDialog(this/*callback*/,0/*title*/,R.string.Qexit,flag);//CB:alertButtonAction//~1A02I~
    }                                                              //~1A02I~
//**************                                                   //~1527I~
    public void optionMenu()                                       //~1527I~
    {                                                              //~1527I~
	    showContextMenu(MT_OPTMENU);                               //~1528R~
    }                                                              //~1527I~
//**************                                                   //~1820I~
    public void optionHelp()                                       //~1820I~
    {                                                              //~1820I~
        if (Dump.Y) Dump.println("AxeMenu:optionHelp");            //~1820I~
        String title=Utils.getResourceString(R.string.HelpTitle_OptionHelp);//~1A11R~
//        String helpmsg=Utils.getResourceString(R.string.Help_OptionHelp);//~1A09R~
        String helpmsg=Nls.getText(R.string.Help_OptionHelp,title/*as textfile name*/);//~1A11R~
//      int flag=AxeAlert.BUTTON_CLOSE|AxeAlert.SHOW_DIALOG;       //~vaxfR~
//  	AxeAlert.simpleAlertDialog(null,title,helpmsg,flag);       //~vaxfR~
    	AxeAlert.helpDialog(title,helpmsg);                        //~vaxfI~
    }                                                              //~1820I~
  //**************                                                   //~1820R~
    public void onButtonConfig()                                   //~1820R~
    {                                                              //~1820R~
        showContextMenu(MT_OPTOPT);                                //~1820R~
    }                                                              //~1820R~
  //**************                                                 //~1920I~
    public void onKbdConfig()                                      //~1920I~
    {                                                              //~1920I~
    	AxeDlgKbdLayout.showDialog();                              //~1920I~
    }                                                              //~1920I~
  //**************                                                 //~vc1kI~
    public void onKbdConfigHW()                                    //~vc1kI~
    {                                                              //~vc1kI~
    	AxeDlgKbdLayoutHW.showDialog();                            //~vc1kI~
    }                                                              //~vc1kI~
//**************                                                   //~1527M~
    public void showContextMenu(int PmenuType)                     //~1528R~
    {                                                              //~1527M~
        View view=AxeG.mainView;        //~1527M~                                        //~1527M~
        if (Dump.Y) Dump.println("AxeMenu showContextMenu case="+PmenuType+",view="+view.toString());//~1528R~
        menuType=PmenuType;     //from option menu                 //~1528I~
        menuRegisteredView.showContextMenu();                      //~1725R~
    }                                                              //~1527M~
//*************************************************                //~1122R~
//*ContextMenu                                                     //~1527R~
//*************************************************                //~1122I~
    public void onCreateContextMenu(ContextMenu Pmenu,View Pview,ContextMenuInfo Pinfo)//~1121I~
    {                                                                  //~1107I~//~1121R~
		if (Dump.Y) Dump.println("AxeMenu:onCreateContextMenu menuType="+menuType+",View="+Pview.toString());                                       //~1306I~//~1528R~
        int rid;                                                   //~1528I~
        switch(menuType)                                           //~1528I~
        {                                                          //~1528I~
        case MT_OPTOPT:                                            //~1528I~
	        rid=R.menu.axeoption;                                  //~1528I~
        	break;                                                 //~1528I~
        case MT_POPUP:  //titlebar click:rbutton down              //~1927I~
	        rid=R.menu.popupmenu;                                  //~1927I~
        	break;                                                 //~1927I~
        case MT_ACTIONBAR:  //titlebar click:rbutton down          //~vay5I~
	        rid=menuresidActionbar;                                //~vay5I~
        	break;                                                 //~vay5I~
        default:                                                   //~1528I~
	        rid=R.menu.contextmenu;                                //~1528I~
        }                                                          //~1528I~
        inflater.inflate(rid,Pmenu);                               //~1927I~
        if (menuType==MT_POPUP)                                    //~1927I~
        	createPopupMenu(Pmenu);                                //~1927I~
    }                                                                  //~1107I~//~1121R~
//****************************************************             //~1123I~
    public boolean onContextItemSelected(MenuItem Pitem)           //~1121R~
    {                                                              //~1121I~
    	boolean rc=true;                                           //~1121I~
		//************************                                     //~1124I~
    	int itemid=Pitem.getItemId();
    	if (Dump.Y) Dump.println("AxeMenu:ContextItemSelected itemid="+Integer.toHexString(itemid));//~1121R~     //~1123R~//~1124I~//~1527R~//~vc1kR~
        switch(itemid)                                             //~1527I~
        {                                                          //~1527I~
//        case    R.id.CM_NEW:                                     //~vaxcR~
//            onFileNew();                                         //~vaxcR~
//            break;                                               //~vaxcR~
        case    R.id.CM_OPEN:                                      //~1725I~
            onFileOpen();                                          //~1725I~
            break;                                                 //~1725I~
//        case    R.id.CM_SAVE:                                    //~vaxcR~
//            onFileSave();                                        //~vaxcR~
//            break;                                               //~vaxcR~
        case    R.id.CM_SAVE:                                      //~vay7R~
            onFileSave("");                                        //~vay7R~
            break;                                                 //~vay7R~
//        case    R.id.CM_SAVEAS:                                  //~vaxcR~
//            onFileSaveAs();                                      //~vaxcR~
//            break;                                               //~vaxcR~
        case    R.id.CM_SAVEAS:                                    //~vay7R~
            onFileSaveAs();                                        //~vay7R~
            break;                                                 //~vay7R~
        case    R.id.CM_CUT:                                       //~1725R~
            onEditCut();                                           //~1725R~
            break;                                                 //~1527I~
        case    R.id.CM_COPY:                                      //~1725I~
            onEditCopy();                                          //~1725I~
            break;                                                 //~1725I~
        case    R.id.CM_CLEAR:                                     //~1725I~
            onEditClear();                                         //~1725I~
            break;                                                 //~1725I~
        case    R.id.CM_PASTEV:                                     //~1725I~//~1A02R~
            onEditPasteV();                                         //~1725I~//~1A02R~
            break;                                                 //~1725I~
        case    R.id.CM_PASTEINS:                                  //~1725I~
            onEditPasteIns();                                       //~1725I~
            break;                                                 //~1725I~
        case    R.id.CM_PASTEREP:                                  //~1725I~
            onEditPasteRep();                                           //~1725I~
            break;                                                 //~1725I~
        case    R.id.CM_FONT:                                      //~1725R~
            onSetupFont();                                         //~1725R~
            break;                                                 //~1527I~
        case    R.id.CM_COLOR:                                     //~1725I~
            onSetupColor();                                        //~1725I~
            break;                                                 //~1725I~
        case    R.id.CM_BUTTON_CONFIG:                             //~1820I~
    		onButtonConfig();                                       //~1820I~
            break;                                                 //~1820I~
        case    R.id.CM_KBD_CONFIG:                                //~1920I~
    		onKbdConfig();                                         //~1920I~
            break;                                                 //~1920I~
        case    R.id.CM_KBD_CONFIG_HW:                             //~vc1kI~
    		onKbdConfigHW();                                       //~vc1kI~
            break;                                                 //~vc1kI~
        case    R.id.CM_OTHER:                                     //~1725I~
            onSetupOther();                                        //~1725I~
            break;                                                 //~1725I~
        case    R.id.CM_OTHER_ARM:                                  //~1821I~
            onSetupOtherArm();                                     //~1821I~
            break;                                                 //~1821I~
//        case    R.id.CM_DL_ASSET:                                  //~vag0I~//~vc1kR~
//            onSetupDLAsset();                                      //~vag0I~//~vc1kR~
//            break;                                                 //~vag0I~//~vc1kR~
        case    R.id.CM_DOWNLOAD:                                  //~vag0I~
            onSetupDownload();                                     //~vag0I~
            break;                                                 //~vag0I~
        case    R.id.CM_HELPVERSION:                               //~1527I~
            onVersion();                                            //~1527I~
            break;                                                 //~1527I~
        case    R.id.CM_HELPAXE:                                   //~1527I~
            onHelpAxe();                                           //~1A07R~
            break;                                                 //~1527I~
        case    R.id.CM_HELPXE:                                    //~1527I~
            onHelpXe();                                            //~1A07R~
            break;                                                 //~1527I~
        case    R.id.CM_BUTTON_LAYOUT:                             //~1820I~
            onButtonLayout();                                      //~1820I~
            break;                                                 //~1820I~
        case    R.id.CM_SHORTCUT_KEY:                              //~1528I~
            onShortcutkeyMap();                                    //~1528R~
            break;                                                 //~1528M~
        case    R.id.CM_SHIFT_KEY:                                 //~1529I~
//          onShiftkeyMap(AxeKey.SHIFT_UPDATE);                    //~1817R~
            onShiftkeyMap();                                       //~1817I~
            break;                                                 //~1529I~
//        case    R.id.CM_SHIFT_KEY_RESET101:                      //~1817R~
//            onShiftkeyMap(AxeKey.SHIFT_RESET101);                //~1817R~
//            break;                                               //~1817R~
//        case    R.id.CM_SHIFT_KEY_RESET106:                      //~1817R~
//            onShiftkeyMap(AxeKey.SHIFT_RESET106);                //~1817R~
//            break;                                               //~1817R~
        case    R.id.CM_ALTGR_KEY:                                 //~1611I~
            onAltGrMap();                                          //~1611I~
            break;                                                 //~1611I~
        case    R.id.CM_TERMINAL_BUTTON:                           //~1809I~
            onTerminalButtonUsage();                               //~1809I~
            break;                                                 //~1809I~
//*popupmenu                                                       //~vainI~
        case    R.id.CM_END:                                       //~vainI~
            onFileEnd();                                           //~vainI~
            break;                                                 //~vainI~
        case    R.id.CM_DISCARD:                                   //~vainI~
            onFileCancel();                                        //~vainI~
            break;                                                 //~vainI~
        case    R.id.CM_OPENWITH:                                 //~vainI~
            onFileOpenWith();                                      //~vainI~
            break;                                                 //~vainI~
        }                                                          //~1527I~
    	return rc;                                                 //~1121R~
    }                                                              //~1121I~
//****************************************************             //~1930I~
    public void onContextMenuClosed(Menu Pmenu)                    //~1930I~
    {                                                              //~1930I~
    	if (Dump.Y) Dump.println("AxeMenu:ContextMenuClosed");     //~1930I~
        AxeJNI.onContextMenuClosed();                               //~1930I~
    }                                                              //~1930I~
//****************************************************             //~1527I~
//    private void  onFileNew(){}                                    //~1725R~//~1A02R~//~vaxcR~
//    private void  onFileOpen(){}                                 //~vaxcR~
    public void  onFileOpen()                                      //~vaxcR~
    {                                                              //~vaxcI~
		AxeDlgFileChooser.showDialog(AxeLstFileChooser.OPEN);      //~vaxcI~
    }                                                              //~vaxcI~
//    private void  onFileSave(){}                                 //~vaxcR~
    private void  onFileSave(String Pfnm/*not null but "" */)      //~vay7R~
    {                                                              //~vay7R~
    	AxeJNI.onFileSave(Pfnm);                                   //~vay7R~
    }                                                              //~vay7R~
//    private void  onFileSaveAs(){}                               //~vay7R~
    private void  onFileSaveAs()                                   //~vay7R~
    {                                                              //~vay7R~
		AxeDlgFileChooser.showDialog(AxeLstFileChooser.SAVEAS);    //~vay7R~
    }                                                              //~vay7R~
    private void  onEditCut()                                      //~1A02R~
    {                                                              //~1A02I~
    	AxeJNI.onEditCut();                                        //~1A02I~
    }                                                              //~1A02I~
    private void  onEditCopy()                                     //~1A02R~
    {                                                              //~1A02I~
    	AxeJNI.onEditCopy();                                       //~1A02I~
    }                                                              //~1A02I~
    private void  onEditClear()                                    //~1A02R~
    {                                                              //~1A02I~
    	AxeJNI.onEditClear();                                      //~1A02I~
    }                                                              //~1A02I~
    private void  onEditPasteV()                                   //~1A02R~
    {                                                              //~1A02I~
    	AxeJNI.onEditPasteV();                                     //~1A02R~
    }                                                              //~1A02I~
    private void  onEditPasteIns()                                 //~1A02R~
    {                                                              //~1A02I~
    	AxeJNI.onEditPasteIns();                                   //~1A02I~
    }                                                              //~1A02I~
    private void  onEditPasteRep()                                 //~1A02R~
    {                                                              //~1A02I~
    	AxeJNI.onEditPasteRep();                                   //~1A02I~
    }                                                              //~1A02I~
                                //~1725R~
//*********************************                                //~1725I~
    private void  onSetupFont()                                    //~1725R~
    {                                                              //~1725I~
    	Axegxedlg.showSetupFont();                                 //~1725R~
    }                                                              //~1725I~
//*********************************                                //~1725I~
    private void  onSetupColor()                                   //~1725I~
    {                                                              //~1725I~
    	Axegxedlg.showSetupColor();                                //~1725I~
    }                                                              //~1725I~
//*********************************                                //~1725I~
    private void  onSetupOther()                                   //~1725I~
    {                                                              //~1725I~
    	Axegxedlg.showSetupOther();                                //~1725I~
    }                                                              //~1725I~
//*********************************                                //~1821I~
    private void  onSetupOtherArm()                                //~1821I~
    {                                                              //~1821I~
    	AxeDlgArmOption.showDialog();                                    //~1821I~
    }                                                              //~1821I~
////*********************************                                //~vag0I~//~vc1kR~
//    private void  onSetupDLAsset()                                 //~vag0I~//~vc1kR~
//    {                                                              //~vag0I~//~vc1kR~
//        AxeDlgDownload.showDialog(true/*asset*/);                  //~vag0R~//~vc1kR~
//    }                                                              //~vag0I~//~vc1kR~
//*********************************                                //~vag0I~
    private void  onSetupDownload()                                //~vag0I~
    {                                                              //~vag0I~
    	AxeDlgDownload.showDialog(false/*any file*/);              //~vag0R~
    }                                                              //~vag0I~
//*********************************                                //~1725I~
    private void  onHelpXe()                                       //~1A07R~
    {                                                              //~1A07I~
    	AxeJNI.xecmd("?","");                                      //~1A07M~
    }                                                              //~1A07I~
    private void  onHelpAxe()                                      //~1A07R~
    {                                                              //~1A07I~
//        String title=Utils.getResourceString(R.string.DialogTitle_OptionHelpAxe);//~1A08I~//+vc2BR~
//        String helpmsg=Utils.getResourceString(R.string.Help_OptionHelpAxe);//~1A08I~//+vc2BR~
////        int flag=AxeAlert.BUTTON_CLOSE|AxeAlert.SHOW_DIALOG;       //~1A08I~//+vc2BR~
////      AxeAlert.simpleAlertDialog(null,title,helpmsg,flag);       //~vaxfR~//+vc2BR~
//        AxeAlert.helpDialog(title,helpmsg);                        //~vaxfI~//+vc2BR~
    	HelpDialog.newInstance(R.string.DialogTitle_OptionHelpAxe,"OptionHelpAxe").showDlg();//+vc2BI~
    }                                                              //~1A07I~
//*********************************                                //~vainI~
    private void onFileEnd()                                       //~vainI~
    {                                                              //~vainI~
    	if (Dump.Y) Dump.println("AxeMenu FileEnd");               //~vainI~
    	AxeJNI.onFileEnd();                                          //~vainI~
    }                                                              //~vainI~
    private void onFileCancel()                                    //~vainI~
    {                                                              //~vainI~
    	if (Dump.Y) Dump.println("AxeMenu FileCancel");            //~vainI~
    	AxeJNI.onFileCancel();                                       //~vainI~
    }                                                              //~vainI~
    private void onFileOpenWith()                                  //~vainI~
    {                                                              //~vainI~
    	if (Dump.Y) Dump.println("AxeMenu FileOpenWith");          //~vainI~
    	AxeJNI.onFileOpenWith();                                     //~vainI~
    }                                                              //~vainI~
//*********************************                                //~1528I~
    private void  onButtonLayout()                                 //~1528I~
	{                                                              //~1528I~
    	if (Dump.Y) Dump.println("AxeMenu onButtonLayout");        //~vay7I~
//  	AxeG.axeDialog.buttonLayout();                             //~1601R~
    	AxeDlgBtnLayout.showDialog();                                  //~1601I~
    	if (Dump.Y) Dump.println("AxeMenu onButtonLayout return"); //~vay7I~
	}                                                              //~1528I~
    private void  onShortcutkeyMap()                                  //~1528I~
	{                                                              //~1528I~
        Utils.showToastLong(R.string.Info_ShortcutkeyMap);         //~1531M~
//  	AxeG.axeDialog.shortcutkeyMap();                           //~1601R~
    	AxeDlgShortcutList.showDialog();                                //~1601I~
	}                                                              //~1528I~
//  private void  onShiftkeyMap(int Pfuncid)                       //~1817R~
    private void  onShiftkeyMap()                                  //~1817I~
	{                                                              //~1528I~
        Utils.showToastLong(R.string.Info_ShiftkeyMap);            //~1531M~
 //   	AxeG.axeDialog.shiftkeyMap(Pfuncid);                       //~1612R~
//    	AxeDlgShiftList.show(Pfuncid);                             //~1817R~
      	AxeDlgShiftList.showDialog();                                    //~1817I~
	}                                                              //~1528I~
    private void  onAltGrMap()                                     //~1611I~
	{                                                              //~1611I~
        Utils.showToastLong(R.string.Info_AltGrMap);               //~1611I~
    	AxeDlgAltGrList.showDialog();                                    //~1612R~
	}                                                              //~1611I~
    private void  onTerminalButtonUsage()                          //~1809I~
	{                                                              //~1809I~
        Utils.showToastLong(R.string.Info_TerminalButton);         //~1809I~
    	AxeDlgTermBtnList.showDialog();                                  //~1809I~
	}                                                              //~1809I~
//****************************************************             //~1411I~
	private void onVersion()                                       //~1527R~
    {                                                              //~1411I~
        if (Dump.Y) Dump.println("AxeMenu:Version");               //~1527I~
        String version=Utils.getResourceString(R.string.Version);   //~1527I~
        String date=Utils.getResourceString(R.string.VerDate);      //~1527I~
        String title=Utils.getResourceString(R.string.DialogTitle_Version);//~1527I~
        int flag=AxeAlert.BUTTON_CLOSE|AxeAlert.SHOW_DIALOG;       //~1527I~
    	AxeAlert.simpleAlertDialog(null,title,version+":"+date,flag);       //~1527I~
    }                                                              //~1411I~
//****************************************************             //~1527I~
//*AxeAlertI:callback                                              //~1802I~
//****************************************************             //~1802I~
	@Override                                                      //~1802I~
    public int alertButtonAction(int Pbuttonid, int Pitempos)
    {
    	if (callbackId==CBID_CONFIRMSTOP)                          //~1802I~
        {                                                          //~1A02I~
        	if (Pbuttonid==AxeAlert.BUTTON_PAUSE)                  //~1A02I~
        		AxeG.main.pauseActivity();                               //~1A02I~
            else                                                   //~1A02I~
	        	Axecsub.umsgbox2Reply(Pbuttonid);                      //~1802I~//~1A02R~
        }                                                          //~1A02I~
		return 0;
	}
	@Override                                                      //~vaiqM~
	public int alertOnShow(AxeAlert Paxealert,boolean Pdismiss) {                   //~vaiqM~
		return 0;                                                  //~vaiqM~
	}                                                              //~vaiqM~
//****************************************************             //~1927I~
//*Menu by R-button<--Titlebar click                               //~1927I~
//****************************************************             //~1927I~
	public void popupMenu(int[] Penabletbl)                        //~1927I~
    {                                                              //~1927I~
    	popupEnableTbl=Penabletbl;                                 //~1927I~
    	showContextMenu(MT_POPUP);	//callback:onCreateContextMenu //~1927I~
    }                                                              //~1927I~
	private void createPopupMenu(ContextMenu Pmenu)                //~1927I~
    {                                                              //~1927I~
    	int sz=Math.min(popupEnableTbl.length,popupMenuIdTbl.length);   //~1927I~
        for (int ii=0;ii<sz;ii++)                                  //~1927I~
        {                                                          //~1927I~
            if (popupEnableTbl[ii]==0)  //disable                  //~1927I~
        		Pmenu.findItem(popupMenuIdTbl[ii]).setEnabled(false);//~1927R~
        }                                                          //~1927I~
    }                                                              //~1927I~
//****************************************************             //~vay5I~
    private void actionbarFile()                                   //~vay5I~
    {                                                              //~vay5I~
    	actionbarPopupMenu(R.menu.actionbarfile);                  //~vay5I~
    }                                                              //~vay5I~
    private void actionbarEdit()                                   //~vay5I~
    {                                                              //~vay5I~
    	actionbarPopupMenu(R.menu.actionbaredit);                  //~vay5I~
    }                                                              //~vay5I~
    private void actionbarHelp()                                   //~vay5I~
    {                                                              //~vay5I~
    	actionbarPopupMenu(R.menu.actionbarhelp);                  //~vay5I~
    }                                                              //~vay5I~
    private void actionbarSetup()                                  //~vay5I~
    {                                                              //~vay5I~
    	actionbarPopupMenu(R.menu.actionbarsetup);                 //~vay5I~
    }                                                              //~vay5I~
//****************************************************             //~vay5I~
//    @TargetApi(AxeG.HONEYCOMB)                                   //~vay5R~
//    private void actionbarPopupMenu(int Pmenuid)                 //~vay5R~
//    {                                                            //~vay5R~
//        View anchorView=AxeG.mainView;                           //~vay5R~
////      int gravity=RIGHT|BOTTOM;   //from api19                 //~vay5R~
////      PopupMenu pm=new PopupMenu(AxeG.context,anchorView,gravity);//~vay5R~
//        PopupMenu pm=new PopupMenu(AxeG.context,anchorView);     //~vay5R~
//        inflater.inflate(Pmenuid,pm.getMenu());                  //~vay5R~
//        pm.show();                                               //~vay5R~
//        pm.setOnMenuItemClickListener(                           //~vay5R~
//            new PopupMenu.OnMenuItemClickListener()              //~vay5R~
//            {                                                    //~vay5R~
//                @Override                                        //~vay5R~
//                public boolean onMenuItemClick(MenuItem Pitem)   //~vay5R~
//                {                                                //~vay5R~
//                    return onContextItemSelected(Pitem);         //~vay5R~
//                }                                                //~vay5R~
//            });                                                  //~vay5R~
//    }                                                            //~vay5R~
    private void actionbarPopupMenu(int Pmenuresid)                //~vay5I~
    {                                                              //~vay5I~
    	menuresidActionbar=Pmenuresid;                              //~vay5I~
    	showContextMenu(MT_ACTIONBAR);	//callback:onCreateContextMenu//~vay5I~
    }                                                              //~vay5I~
//****************************************************             //~vayaI~
//from ICS enable required,before ICS it is default                //~vayaI~
//****************************************************             //~vayaI~
    public void enableHomeButton()                                 //~vayaI~
    {                                                              //~vayaI~
        if (AxeG.osVersion>=AxeG.ICE_CREAM_SANDWICH)  //android4 api14//~vayaR~
		    enableHomeButton_V11();                                 //~vayaI~
    }                                                              //~vayaI~
    @TargetApi(AxeG.ICE_CREAM_SANDWICH)                           //~vayaR~
    public void enableHomeButton_V11()                             //~vayaI~
    {                                                              //~vayaI~
    	ActionBar ab=AxeG.activity.getActionBar();                 //~vayaR~
    	ab.setHomeButtonEnabled(true);                             //~vayaI~
    }                                                              //~vayaI~
//***************                                                  //~vayaI~
    public void onClickHomeButton()                                //~vayaI~
    {                                                              //~vayaI~
        int key,scancode=0;                                        //~vayaI~
        key=AxeDlgArmOption.homebuttonKeyValue;                    //~vayaR~
        if (Dump.Y) Dump.println("onClickHomeButton key="+Integer.toHexString(key));//~vayaI~
        if (key!=0)                                                //~vayaI~
        {                                                          //~vayaI~
    		AxeG.axeKeyAction.kbdInput(KeyEvent.ACTION_DOWN,key,scancode);//~vayaR~
    		AxeG.axeKeyAction.kbdInput(KeyEvent.ACTION_UP,key,scancode);//~vayaR~
        }                                                          //~vayaI~
    }                                                              //~vayaI~
}//class AxeMenu                                                   //~1527R~
