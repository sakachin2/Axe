//CID://+vc32R~: update#=     53                                   //~vc32R~
//*************************************************************    //~va15I~
//vc32 2020/09/22 ACTION_MULTIPLE deprecated at Android10(Api29)   //~vc32I~
//vc2F 2020/08/21 limit vc2t for other than alphabetic(subtend S.C+'a' and S.C+'A')//~vc2FI~
//vc2w 2020/08/10 (Bug)HWkbd,capslocl ignored                      //~vc2wI~
//vc2v 2020/08/09 xekbd;Shift is not work with Shortcut            //~vc2vI~
//vc2t 2020/08/08 accept shortcut with modifier if not defined as with modifier(if "1"-"F1" is defined generate S+"F1" by S+"1")//~vc2tI~
//vc2f 2020/07/23 getunicode may return 0x80000000(CIMBINING_ACCENT) bit on &=0x7fffffff is printable//~vc2fI~
//vc2d 2020/07/18 hardKB,Alt+1 is invalid. it need modstatus and char code//~vc2dI~
//vc26 2020/07/11 mix AxeKbdDialog and AxeKbdDialogFix(apply map of AxeLstKbdLayout)//~vc26I~
//vaig:130531 Axe:disable by ShortCut:On for direction button for update button function//~vaigI~
//*************************************************************    //~va15I~
package com.xe.Axe;                                                //~va15I~

import android.annotation.TargetApi;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

import com.ahsv.AG;
import com.xe.Axe.kbd.AxeKbdDialogHW;

import static com.xe.Axe.AxeKeyValue.*;

public class AxeKeyAction implements OnKeyListener            //~va15R~
{   
	private static final int KBDACTION_UP=0;                       //~va15I~
	private static final int KBDACTION_DOWN=1;                     //~va15I~
	private static final int KBDACTION_CHAR=2;                     //~va15I~
	private static final int KBDACTION_UNICODE=3;                  //~va15I~
	private static final int SCANCODE_SOFTKBD=KeyEvent.FLAG_SOFT_KEYBOARD; //0x02//~1919I~
	private static final int KE_ACTION_MULTIPLE=2;                 //~vc32I~
                                                                   //~va15I~
	private int modifierStatus;                                    //~va15I~
	private int shortcutStatus;                                    //~va15I~
	private boolean capslockStatus;                                //~va15I~
	private boolean isAltGr;                                       //~va15I~
    private static final int SC_ACTIVE=1;                          //~va15I~
//  private static final int SC_ACTIVE_AFTER_SHIFT=2;              //~va15R~
//  private InputMethodManager imm;                                //~va15I~//~1826R~
//  private AxeScreen screen;                                      //~va15R~//~2402R~
	private int dragging;                                          //~1A04I~
    private static final int DRAG_START=1;                         //~1A04I~
    private static final int DRAG_MOVED=2;                         //~1A04I~
//    private int metaFlag_SoftKbd;                                //~1A08R~
    private int metaFlag_DialogKbd;                                //~1A10I~
                                                                   //~1A08I~
//******************************************************************************//~1A08I~
	public AxeKeyAction()                                          //~va15I~
    {                                                              //~va15I~
//  	imm=(InputMethodManager)AxeG.context.getSystemService(Context.INPUT_METHOD_SERVICE);//~va15I~//~1826R~
    }                                                              //~va15I~
//*************                                                    //~va15I~//~1827R~
    public void showKbd()                                          //~va15I~//~1827R~
    {                                                              //~va15I~//~1827R~
        if (Dump.Y) Dump.println("AxeKeyAction.showKbd");                       //~va15I~//~1827R~//~vaigR~
//      if (!isActive())                                           //~1827R~
//      imm.showSoftInput(screen,InputMethodManager.SHOW_FORCED);   //~va15I~//~1826R~//~1827R~
        AxeG.axeIME.showKbd();                                     //~1826I~//~1827R~
    }                                                              //~va15I~//~1827R~
//*************                                                    //~1830I~
    public void showKbdP()                                         //~1830I~
    {                                                              //~1830I~
        if (Dump.Y) Dump.println("AxeKeyAction:showKbdP");         //~1830I~
        AxeG.axeIME.showKbdP();                                    //~1830I~
    }                                                              //~1830I~
//*************                                                    //~va15I~
//    public void hideKbd()                                          //~va15I~//~1827R~
//    {                                                              //~va15I~//~1827R~
//        if (Dump.Y) Dump.println("hideKbd");                     //~1827R~
////        if (isActive())//~va15I~                                 //~1826R~//~1827R~
////        imm.hideSoftInputFromWindow(screen.getWindowToken(),0);    //~va15I~//~1826R~//~1827R~
//        AxeG.axeIME.hideKbd(AxeG.axeScreen);                                     //~1826I~//~1827R~
//    }                                                              //~va15I~//~1827R~
//*************                                                    //~va15I~
//    public boolean toggleKbd()                                     //~va15I~//~1827R~
//    {                                                            //~1827R~
//        boolean rc=true;                                         //~1827I~
//         boolean rc=AxeG.axeIME.isActive(AxeG.axeScreen);//~va15I~               //~1826R~//~1827R~
//        if (rc)                                //~va15I~         //~1827R~
//            hideKbd();                                             //~va15I~//~1827R~
//        else                                                       //~va15I~//~1827R~
//            showKbd();                                             //~va15I~//~1827R~
//        return rc;                                                //~va15I~//~1827R~
//    }                                                              //~va15I~//~1827R~
//*************                                                    //~va15I~
    public void setKeyListener(AxeScreen Pscreen)                  //~va15M~
    {                                                              //~va15M~
//    	screen=Pscreen;                                            //~va15M~//~2402R~
		Pscreen.setOnKeyListener(this);                            //~va15M~
        if (Dump.Y) Dump.println("AxeKeyAction.setKeyListener to "+Pscreen.toString());                //~va15M~//~vaigR~
    }                                                              //~va15M~
//*************                                                    //~va15I~
    public boolean onKeyDown(int Pkeycode,KeyEvent Pevent)           //~va15I~
    {
    	boolean rc=false;                                          //~va15R~
    	if (Dump.Y) Dump.println("AxeKeyAction.onKeyDown keycode=0x"+Integer.toHexString(Pkeycode)+",flag="+Integer.toHexString(Pevent.getFlags()));//~1831R~//~vaigR~
        if (Dump.Y) Dump.println("AxeKeyAction.onKeyDown metastate=0x"+Integer.toHexString(Pevent.getMetaState())+",scan="+Integer.toHexString(Pevent.getScanCode()));//~1831R~//~vaigR~
        if (Dump.Y) Dump.println("AxeKeyAction.onKeyDown unicode=0x"+Integer.toHexString(Pevent.getUnicodeChar())+",unimeta="+Integer.toHexString(Pevent.getUnicodeChar(Pevent.getMetaState())));//~1831R~//~vaigR~
        switch(Pkeycode)                                           //~va15I~
        {                                                          //~va15I~
//onkeyDown is not scheduled when popup or Menu is up              //~va15I~
        case AxeKeyValue.AKC_BACK:                                 //~va15I~
	    	if (Dump.Y) Dump.println("AxeKeyAction.onKeyDown screen hasFocus="+AxeG.axeScreen.hasFocus());//~va15I~//~vaigR~
            if (AxeG.axeScreen.hasFocus())                         //~va15I~
            {                                                      //~va15I~
//              AxeG.axeMenu.confirmStop();                        //~va15R~
            	rc=true;                                           //~va15I~
        	}                                                      //~va15I~
            break;                                                 //~va15I~
        }                                                          //~va15I~
       if (Dump.Y) Dump.println("AxeKeyAction.onKeyDown rc="+rc);               //~va15I~//~vaigR~
    	return rc;
    }                                                              //~va15I~
    public boolean onKeyUp(int Pkeycode,KeyEvent Pevent)             //~va15I~
    {
    	boolean rc=false;                                          //~va15R~
    	if (Dump.Y) Dump.println("AxeKeyAction.onKeyUp keycode="+Pkeycode+",flag="+Integer.toHexString(Pevent.getFlags()));//~va15I~//~vaigR~
        switch(Pkeycode)                                           //~va15I~
        {                                                          //~va15I~
        case AxeKeyValue.AKC_BACK:                                 //~va15I~
//            if (Dump.Y) Dump.println("screen hasFocus="+AxeG.axeScreen.hasFocus());//~va15R~
//            if (AxeG.axeScreen.hasFocus())                       //~va15R~
//            {                                                    //~va15R~
//                AxeG.axeMenu.confirmStop();                      //~va15R~
//                rc=true;                                         //~va15R~
//            }                                                    //~va15R~
            break;                                                 //~va15I~
        }
       if (Dump.Y) Dump.println("AxeKeyAction.onKeyUp rc="+rc);                                                          //~va15I~//~vaigR~
    	return rc;                                                 //~1831R~
    }                                                              //~va15I~
//***************************                                      //~va15I~
    @Override                                                      //~va15I~
    public boolean onKey(View Pview,int Pkeycode,KeyEvent Pevent)//~v@@@I~//~va15I~
    {                                                      //~v@@@I~//~va15I~
        if (Dump.Y) Dump.println("AxeKeyAction.onKey keycode="+Pkeycode+",view="+Pview.toString());//~vaigI~
    	return onKey(Pkeycode,Pevent);                //~1A08I~
    }                                                              //~1A08I~
//***************************                                      //~1A08I~
    public boolean onKey(int Pkeycode,KeyEvent Pevent)             //~1A08I~
    {                                                              //~1A08I~
        int action;                                //~v@@@I~       //~va15I~
        boolean rc=false;                                          //~va15I~
        String multi;                                              //~va15I~
        int btnkey,keycode;                                        //~1810I~
    //**********************                           //~v@@@I~//~va15I~
        keycode=Pkeycode;                                          //~1810I~
        try                                                        //~va15I~
        {                                                          //~va15I~
            action=Pevent.getAction();                          //~v@@@R~//~va15I~
            if (Dump.Y) Dump.println("AxeKeyAction.onKey action="+Integer.toHexString(action));//~va15I~//~vaigR~
    		if (Dump.Y) Dump.println("AxeKeyAction.onKey keycode=0x"+Integer.toHexString(Pkeycode)+",flag="+Integer.toHexString(Pevent.getFlags()));//~1831R~//~vaigR~
        	if (Dump.Y) Dump.println("AxeKeyAction.onKey metastate=0x"+Integer.toHexString(Pevent.getMetaState())+",scan="+Integer.toHexString(Pevent.getScanCode()));//~1831R~//~vaigR~
        	if (Dump.Y) Dump.println("AxeKeyAction.onKey unicode=0x"+Integer.toHexString(Pevent.getUnicodeChar())+",unimeta="+Integer.toHexString(Pevent.getUnicodeChar(Pevent.getMetaState())));//~1831R~//~vaigR~
            btnkey=chkTerminalKey(action,Pkeycode,Pevent);                   //~1810R~//~1926R~
            if (btnkey==0)	//terminal button is used as modifiedr //~1811R~
            	return true;                                       //~1811I~
            else                                                   //~1811I~
            if (btnkey>0)	//assigned extended key                //~1811I~
            	keycode=btnkey;                                    //~1811R~
            switch(action)                                     //~v@@@I~//~va15I~
            {                                                  //~v@@@I~//~va15I~
            case KeyEvent.ACTION_UP:                           //~v@@@I~//~va15R~
				rc=keyProc(action,keycode,Pevent);                //~va15I~//~1810R~
                break;                                         //~v@@@I~//~va15I~
            case KeyEvent.ACTION_DOWN:                             //~va15R~
				rc=keyProc(action,keycode,Pevent);                //~va15R~//~1810R~
                break;                                             //~va15I~
//            case KeyEvent.ACTION_MULTIPLE:                          //~va15I~//~vc32R~
//                if (keycode==KeyEvent.KEYCODE_UNKNOWN)  //keycode returns by getCharacter()//~va15I~//~1810R~//~vc32R~
//                {                                                  //~va15I~//~vc32R~
//                    multi=Pevent.getCharacters();                         //~va15I~//~vc32R~
//                    if (Dump.Y) Dump.println("AxeKeyAction.onKey multi-string="+multi);//~va15I~//~vaigR~//~vc32R~
//                    strInput(multi);                               //~va15I~//~vc32R~
//                }                                                  //~va15I~//~vc32R~
//                break;                                             //~va15I~//~vc32R~
            default:                                           //~v@@@I~//~va15I~
        		if (AG.osVersion<29) // <android10                 //+vc32R~
					actionMultiple28(Pkeycode,action,Pevent);      //~vc32R~
            }                                                  //~v@@@I~//~va15I~
        }                                                          //~va15I~
        catch(Exception e)                                         //~va15I~
        {                                                          //~va15I~
            Dump.println(e,"AxeKeyAction.OnKey");                  //~va15I~
        }                                                          //~va15I~
        if (Dump.Y) Dump.println("AxeKeyAction.onKey rc="+rc);                  //~va15I~//~vaigR~
        return rc;           //~v@@@I~                             //~va15I~
    }                                                      //~v@@@I~//~va15I~
//*******************************************************************//~vc32I~
	@SuppressWarnings("deprecation") //getCharacters wa deprecated at api29//~vc32R~
    private void actionMultiple28(int Pkeycode,int Paction,KeyEvent Pevent)//~vc32R~
    {                                                              //~vc32I~
        String multi;                                              //~vc32I~
        switch(Paction)                                            //~vc32I~
        {                                                          //~vc32I~
//  	case KeyEvent.ACTION_MULTIPLE:                             //~vc32R~
    	case KE_ACTION_MULTIPLE:                                   //~vc32I~
            if (Pkeycode==KeyEvent.KEYCODE_UNKNOWN)  //keycode returns by getCharacter()//~vc32R~
            {                                                      //~vc32I~
                multi=Pevent.getCharacters();                      //~vc32I~
                if (Dump.Y) Dump.println("AxeKeyAction.actionMultiple28 multi-string="+multi);//~vc32R~
                if (multi!=null)                                   //~vc32I~
    	            strInput(multi);                               //~vc32R~
            }                                                      //~vc32I~
            break;                                                 //~vc32I~
		}                                                          //~vc32I~
    }                                                              //~vc32I~
//******************************************************************************//~1A08I~
//*from AxeKbdDialog hard Kbd Input at KbdDialog Open              //~1A08I~
//*meta state is of SoftKbd                                        //~1A08I~
//******************************************************************************//~1A08I~
    public boolean onKeyFromDialog(int Pkeycode,KeyEvent Pevent,boolean Pshortcut)//~1A10R~
    {                                                              //~1A08I~
        if (Dump.Y) Dump.println("AxeKeyAction.onKeyFromDialog action="+Pevent.getAction()+",keycode=0x"+Integer.toHexString(Pkeycode)+",shortcut="+Pshortcut+",unicode="+Integer.toHexString(Pevent.getUnicodeChar()));//~vaigR~//~vc26R~
//        metaFlag_SoftKbd=PmetaFlag; //id if from AxeKbdDialog    //~1A08R~
//        int unicode,shortcutcode,scancode,action;                //~1A10R~
//        int key=Pkeycode;                                        //~1A10R~
//        if (Pshortcut)                                           //~1A10R~
//        {                                                        //~1A10R~
//            unicode=Pevent.getUnicodeChar();                     //~1A10R~
//            if (unicode>0 && unicode<=AxeKey.END_OF_ASCII)       //~1A10R~
//            {                                                    //~1A10R~
//                shortcutcode=getShortcutcode(unicode); //may generate ext key//~1A10R~
//                if (shortcutcode!=unicode)  //mapping defined    //~1A10R~
//                {                                                //~1A10R~
//                    scancode=Pevent.getScanCode();               //~1A10R~
//                    action=Pevent.getAction();                   //~1A10R~
//                    charInput(action,shortcutcode,scancode,Pevent);//~1A10R~
//                    return true;                                 //~1A10R~
//                }                                                //~1A10R~
//            }                                                    //~1A10R~
//        }                                                        //~1A10R~
        metaFlag_DialogKbd=Pshortcut ? 1 : 0;	//parm to codeMapping//~1A10I~
		boolean rc=onKey(Pkeycode,Pevent);                              //~1A10R~
        metaFlag_DialogKbd=0;                                      //~1A10I~
//        metaFlag_SoftKbd=0;                                      //~1A08R~
        return rc;                                                 //~1A08I~
    }                                                              //~1A08I~
//******************************************************************************//~vc26I~
//*from AxeKbdDialogHW with modified by AxeLstKbdLayout definition //~vc26I~
//*keyvalue=0 for Alt_Left+                                        //~vc26I~
//******************************************************************************//~vc26I~
    public boolean onKeyFromDialogHW(int Paction,int Pkeycode,KeyEvent Pevent,int PkeyValue)//~vc26I~
    {                                                              //~vc26I~
        if (Dump.Y) Dump.println("AxeKeyAction.onKeyFromDialogHW keycode=0x"+Integer.toHexString(Pkeycode)+",keyvalue="+Integer.toHexString(PkeyValue));//~vc26I~
		boolean rc=keyProcHW(Paction,Pkeycode,Pevent,PkeyValue);   //~vc26I~
        return rc;                                                 //~vc26I~
    }                                                              //~vc26I~
//******************************************************************************//~1A08I~
//*terminal button key assignment chk                              //~1810I~
//*rc:-1:not assiged terminal button, 0:processed, >0:continue by new key//~1811R~
//******************************************************************************//~1A08I~
	private int chkTerminalKey(int Paction,int Pkeycode,KeyEvent Pevent)       //~1810R~//~1926R~
    {                                                              //~1810I~
    	int btnkey;                                                //~1810I~
        int rc=0;	//return try from onkey                                               //~1810R~//~1811R~//~1824R~
        AxeButton button=null;                                     //~1824I~
    //***************                                              //~1810I~
    	if ((btnkey=AxeLstTermBtn.chkTerminalKey(Pkeycode))<=0)    //~1810I~
        	return -1; //not assigned                           //~1810R~
        switch(btnkey)                                             //~1810I~
        {                                                          //~1810I~
        case AxeLstTermBtn.TB_SHORTCUT:                            //~1810I~
//            shortcutStatus=SC_ACTIVE;                              //~1810I~//~1824R~
            if (Dump.Y) Dump.println("AxeKeyAction.chkTerminalKey shortcut by "+Integer.toHexString(Pkeycode));//~1810I~//~1824R~//~vaigR~
			button=AxeButton.btnShortCut;                          //~1824R~
            break;                                                 //~1810I~//~1824R~
        case AxeLstTermBtn.TB_CTRL:                                //~1810I~
//            setModifier(true,AxeKeyValue.KBF_CONTROL);             //~1810I~//~1824R~
            if (Dump.Y) Dump.println("AxeKeyAction.chkTerminalKey Control by "+Integer.toHexString(Pkeycode));//~1810I~//~1824R~//~vaigR~
			button=AxeButton.btnCtrl;                              //~1824R~
            break;                                                 //~1810I~//~1824R~
        case AxeLstTermBtn.TB_ALT:                                 //~1810I~
//            setModifier(true,AxeKeyValue.KBF_ALT);                 //~1810I~//~1824R~
            if (Dump.Y) Dump.println("AxeKeyAction.chkTerminalKey Alt by "+Integer.toHexString(Pkeycode));//~1810I~//~1824R~//~vaigR~
			button=AxeButton.btnAlt;                               //~1824R~
            break;                                                 //~1810I~//~1824R~
        case AxeLstTermBtn.TB_ALTG:                                //~1810I~
//            isAltGr=true;                                          //~1810I~//~1824R~
            if (Dump.Y) Dump.println("AxeKeyAction.chkTerminalKey AltGr by "+Integer.toHexString(Pkeycode));//~1810I~//~1824R~//~vaigR~
			button=AxeButton.btnAltGr;                             //~1824R~
            break;                                                 //~1810I~//~1824R~
        case AxeLstTermBtn.TB_KBD:                                 //~1831I~
            if (Dump.Y) Dump.println("AxeKeyAction.chkTerminalKey Kbd      "+Integer.toHexString(Pkeycode));//~1831I~//~vaigR~
			button=AxeButton.btnIM;                                //~1831I~
            break;                                                 //~1831I~
        case AxeLstTermBtn.TB_KBDP:                                //~1831I~
            if (Dump.Y) Dump.println("AxeKeyAction.chkTerminalKey Kbd      "+Integer.toHexString(Pkeycode));//~1831I~//~vaigR~
			button=AxeButton.btnIMP;                               //~1831I~
            break;                                                 //~1831I~
        case AxeLstTermBtn.TB_CLICK:                               //~1926I~
            if (Dump.Y) Dump.println("AxeKeyAction.chkTerminalKey Kbd CLICK     "+Integer.toHexString(Pkeycode));//~1926I~//~1A04R~//~vaigR~
			mouseLbtn(Paction,Pevent);                             //~1A04I~
            if (Paction==KeyEvent.ACTION_UP)                       //~1926I~
		    	resetModifier();		//like as normal key event //~1926I~//~1A03R~
            break;                                                 //~1926I~
        case AxeLstTermBtn.TB_DBLCLICK:                            //~1A04I~
            if (Dump.Y) Dump.println("AxeKeyAction.chkTerminalKey Kbd DBLCLICK     "+Integer.toHexString(Pkeycode));//~1A04I~//~vaigR~
			mouseLbtn2(Paction,Pevent);                            //~1A04I~
            if (Paction==KeyEvent.ACTION_UP)                       //~1A04I~
		    	resetModifier();		//like as normal key event //~1A04I~
            break;                                                 //~1A04I~
        case AxeLstTermBtn.TB_DRAG:                                //~1A04I~
            if (Dump.Y) Dump.println("AxeKeyAction.chkTerminalKey Kbd DRAG "+Integer.toHexString(Pkeycode));//~1A04I~//~vaigR~
			mouseLbtnDrag(Paction,Pevent);                         //~1A04I~
            break;                                                 //~1A04I~
        case AxeLstTermBtn.TB_RBTN:                                //~1A04I~
            if (Dump.Y) Dump.println("AxeKeyAction.chkTerminalKey RBTN"+Integer.toHexString(Pkeycode)+",btnkey="+btnkey);//~1A04I~//~vaigR~
            mouseRbtn(Paction);                                    //~1A04I~
            if (Paction==KeyEvent.ACTION_UP)                       //~1A04I~
		    	resetModifier();		//like as normal key event //~1A04I~
            break;                                                 //~1A04I~
        default:                                                   //~1810I~
        	rc=btnkey;                                             //~1811R~
        }                                                          //~1811R~
        if (button!=null)                                                //~1824I~
        {                                                          //~1824I~
        	if (Paction==KeyEvent.ACTION_DOWN)                     //~1824I~
				AxeButtonAction.onTerminalButtonKey(button);       //~1831R~
        }                                                          //~1824I~
        if (Dump.Y) Dump.println("AxeKeyAction.chkTerminalKey rc="+Integer.toHexString(rc));//~vaigR~
        return rc;//~1811I~
     }                                                              //~1810I~
//**********************                                           //~va15I~
//*keyproc                                                         //~va15I~
//**********************                                           //~va15I~
	private boolean keyProc(int Paction,int Pkeycode,KeyEvent Pevent)//~va15R~
    {                                                              //~va15I~
        boolean rc=false;                                          //~va15I~
    //*************                                                //~va15I~
        int unicode=Pevent.getUnicodeChar();                       //~va15I~
        int scancode=Pevent.getScanCode();                         //~1815I~
        if (unicode==0  //extended key                             //~va15I~//~vc2dR~
        ||  AxeKeyValue.isExtendedKey(Pkeycode)) //"Enter" returns unicode=0x0a//~va15I~//~vc2dR~
//      if (AxeKeyValue.isExtendedKey(Pkeycode)) //"Enter" returns unicode=0x0a//~vc2dR~
        {                                                          //~va15I~
            if (extkeyInput(Paction,Pkeycode,Pevent))              //~va15R~
                rc=true;                                           //~va15I~
        }                                                          //~va15I~
//        else                                                     //~vc2dR~
//        if (unicode==0) //extended key                           //~vc2dR~
//        {                                                        //~vc2dR~
//            int val=complementCharCode(Pevent,Pkeycode);         //~vc2dR~
////            if (val==0)                                        //~vc2dR~
////            {                                                  //~vc2dR~
////                if (extkeyInput(Paction,Pkeycode,Pevent))      //~vc2dR~
////                    rc=true;                                   //~vc2dR~
////            }                                                  //~vc2dR~
////            else                                               //~vc2dR~
////            {                                                  //~vc2dR~
////                charInput(Paction,val,scancode,Pevent);        //~vc2dR~
////                rc=true;                                       //~vc2dR~
////            }                                                  //~vc2dR~
//              charInput(Paction,val,scancode,Pevent);            //~vc2dR~
//              rc=true;                                           //~vc2dR~
//        }                                                        //~vc2dR~
        else                                                       //~va15I~
        {                                                          //~va15I~
            charInput(Paction,unicode,scancode,Pevent);                   //~1815R~//~1A04R~
            rc=true;                                               //~va15I~
        }                                                          //~va15I~
        return rc;                                                 //~va15I~
    }                                                              //~va15I~
//*********************************************************************//~vc26I~
//*keyproc from hard keyboard modified at KeyUp                    //~vc26I~
//*send code by AltGR and Shift+AltGR                              //~vc2dI~
//*********************************************************************//~vc26I~
	private boolean keyProcHW(int Paction,int Pkeycode,KeyEvent Pevent,int PkeyValue)//~vc26I~
    {                                                              //~vc26I~
        boolean rc=true;                                           //~vc26I~
        boolean swGDK=false;                                       //~vc26I~
    //*************                                                //~vc26I~
        if (Dump.Y) Dump.println("AxeKeyAction.keyProcHW keyValue="+Integer.toHexString(PkeyValue));//~vc26I~
        int val=PkeyValue;                                         //~vc26I~
        if ((PkeyValue & KBF_GDK)!=0)           //=0x20000000;    //GDK value by spinner//~vc26I~
        {                                                          //~vc26I~
        	swGDK=true;                                            //~vc26I~
        	val=PkeyValue & ~KBF_GDK;                              //~vc26I~
        }                                                          //~vc26I~
        else                                                       //~vc26I~
        if ((PkeyValue & KBF_UNICODE)!=0)       //=0x40000000;    //GDK value by spinner//~vc26I~
        	if ((val & ~KBF_UNICODE)<=AxeKey.END_OF_ASCII)   //<=7f                  //~vc26I~
	        	val=PkeyValue & ~KBF_UNICODE;                      //~vc26I~
        int scancode=Pevent.getScanCode();                         //~vc26I~
//      if (unicode==0  //extended key                             //~vc26I~
//      ||  AxeKeyValue.isExtendedKey(Pkeycode)) //"Enter" returns unicode=0x0a//~vc26I~
        if (swGDK)                                                 //~vc26I~
        {                                                          //~vc26I~
//          extkeyInput(Paction,Pkeycode,Pevent);                  //~vc26I~
        	kbdInput(Paction,val,0/*scancode*/);                   //~vc26I~
        }                                                          //~vc26I~
        else                                                       //~vc26I~
        {                                                          //~vc26I~
//          charInput(Paction,val/*unicode*/,scancode,Pevent);     //~vc26I~
        	if (Paction==KeyEvent.ACTION_DOWN)                     //~vc26I~
            {                                                      //~vc2dR~
//                if (val==0)                                      //~vc2dR~
//                    val=complementCharCode(Pevent,Pkeycode);     //~vc2dR~
        		kbdInput(KeyEvent.ACTION_DOWN,val,scancode);       //~vc2dR~
            }                                                      //~vc2dR~
            rc=true;                                               //~vc26I~
        }                                                          //~vc26I~
        return rc;                                                 //~vc26I~
    }                                                              //~vc26I~
//***********************                                          //~va15I~
//*extendedkey chk                                                 //~va15I~
//***********************                                          //~va15I~
    public boolean extkeyInput(int Paction,int Pkeycode,KeyEvent Pevent)//~va15R~
    {                                                              //~va15I~
    	int metastate,mod,extkey;                                  //~va15I~
        boolean rc;                                                //~va15I~
    //********                                                     //~va15I~
        metastate=Pevent.getMetaState();                           //~va15I~
		if (Dump.Y) Dump.println("AxeKeyAction.extkeyInput keycode="+Integer.toHexString(Pkeycode)+",meta="+Integer.toHexString(metastate));//~va15I~//~vaigR~
        if (metastate!=0)                                          //~va15I~
        	mod=AxeKeyValue.metaToMod(metastate);                  //~va15I~
        else                                                       //~va15I~
        	mod=0;                                                 //~va15I~
        mod|=(modifierStatus & (AxeKeyValue.KBF_CONTROL|AxeKeyValue.KBF_ALT|AxeKeyValue.KBF_SHIFT));//~1A02I~
//        mod|=metaFlag_SoftKbd;  //from SoftKbd button            //~1A08R~
        extkey=AxeKeyValue.keycodeToExtkey(Pkeycode);         //~va15I~
        if (extkey!=KeyData.NOT_DEFINED)                       //~va15I~
        {                                                          //~va15I~
        	extkey|=mod;                                     //~va15I~
        	kbdInput(Paction,extkey,0/*scancode*/);                //~1815R~
	    	resetModifier();                                    //~va15I~//~1A03R~
            rc=true;                                               //~va15I~
        }                                                          //~va15I~
        else                                                       //~va15I~
        	rc=false;                                              //~va15I~
        return rc;                                                 //~va15I~
    }                                                              //~va15I~
//***********************                                          //~va15I~
//*string input                                                    //~va15I~
//***********************                                          //~va15I~
    public void strInput(String Pstr)                               //~va15I~
    {                                                              //~va15I~
        if (Dump.Y) Dump.println("AxeKeyAction.strInput str="+Pstr);//~vaigI~
    	int key;                                                   //~va15I~
    	resetModifier();                                        //~va15I~//~1A03R~
    	int len=Pstr.length();                                       //~va15I~
        for (int ii=0;ii<len;ii++)                                 //~va15I~
        {                                                          //~va15I~
        	key=Pstr.charAt(ii);                                   //~va15I~
            key=AxeKeyValue.setUnicodeId(key);	//even for ascii   //~va15R~
//          kbdInput(KeyEvent.ACTION_MULTIPLE,key,0/*scancode*/);  //~1815R~//~vc32R~
            kbdInput(KE_ACTION_MULTIPLE,key,0/*scancode*/);        //~vc32I~
        }                                                          //~va15I~
    }                                                              //~va15I~
//***********************                                          //~va15I~
//*unicode input                                                   //~va15I~
//***********************                                          //~va15I~
    public void charInput(int Paction,int Punicode,int Pscancode,KeyEvent Pevent)  //~1815R~//~1A04R~
    {                                                              //~va15I~
    	int key;                                                   //~va15I~
        boolean extkey=false;                                      //~1815I~
        int mouse=0,mod=0;                                         //~1A03I~
    //**********************                                       //~1815I~
		if (Dump.Y) Dump.println("AxeKeyAction.charInput unicode="+Integer.toHexString(Punicode)+",modifierStatus="+Integer.toHexString(modifierStatus));//~va15I~//~vaigR~//~vc2fR~
        if ((Punicode & KeyCharacterMap.COMBINING_ACCENT)!=0)                      //~vc2fI~
        {                                                          //~vc2fI~
        	key=Punicode & KeyCharacterMap.COMBINING_ACCENT_MASK;                  //~vc2fI~
            if (key>AxeKey.END_OF_ASCII)                           //~vc2fI~
        		key=AxeKeyValue.setUnicodeId(key);                 //~vc2fI~
        }                                                          //~vc2fI~
        else                                                       //~vc2fI~
        if (Punicode>AxeKey.END_OF_ASCII)                          //~va15I~
        {		                                                   //~va15I~
        	key=AxeKeyValue.setUnicodeId(Punicode);                //~va15I~
        }                                                          //~va15I~
        else                                                       //~va15I~
        {                                                          //~va15I~
			if (metaFlag_DialogKbd==1)  //shortcut                //~1A10I~//~vc2fR~
            {                                                      //~vc2FI~
//				key=getShortcutcode(Punicode); //may generate ext key//~1A10I~//~vc2tR~
  				key=getShortcutcode(Punicode,Pevent); //may generate ext key//~vc2tI~
		        if (key==AxeKey.KEYVALUE_NOTDEF)                   //~vc2FI~
        			key=Punicode;                                  //~vc2FI~
            }                                                      //~vc2FI~
            else                                                   //~1A10I~
//          	key=codeMapping(Punicode);                         //~1A10R~//~vc2tR~
            	key=codeMapping(Punicode,Pevent);                  //~vc2tI~
            if (key==AxeKeyValue.KEYSYM_CLICK) 	//mouse LB         //~1A04R~
            	mouse=AxeMouse.MK_LBUTTON;                         //~1A04I~
            else                                                   //~1A04I~
            if (key==AxeKeyValue.KEYSYM_DBLCLICK)                  //~1A04R~
            	mouse=AxeMouse.MK_LBUTTON2;                        //~1A04I~
            else                                                   //~1A04I~
            if (key==AxeKeyValue.KEYSYM_DRAG) 	//mouse LB         //~1A04I~
            	mouse=AxeMouse.MK_LBUTTON3;                        //~1A04I~
            else                                                   //~1A04I~
            if (key==AxeKeyValue.KEYSYM_RBTN) 	//mouse RB         //~1A03I~//~1A04R~
            	mouse=AxeMouse.MK_RBUTTON;                         //~1A03I~
            else                                                   //~1A04I~
    		if (!AxeKeyValue.isUnicodeKeyValue(key))               //~1815I~
            {                                                      //~1815I~
                extkey=AxeKeyValue.isValidExtGDK(key);             //~1815I~
        		mod=(modifierStatus & (AxeKeyValue.KBF_CONTROL|AxeKeyValue.KBF_ALT|AxeKeyValue.KBF_SHIFT));//~1815R~//~1A03R~
        		key|=mod;                                          //~1A03I~
            }                                                      //~1815I~
        }                                                          //~va15I~
//        if (extkey)                                              //~1815I~
//            kbdInput(Paction,key,0/*scancode*/);                 //~1815I~
//        else                                                     //~1815I~
		if (mouse!=0)                                              //~1A04I~
        {                                                          //~1A04I~
            if (mouse==AxeMouse.MK_LBUTTON)                        //~1A04R~
                mouseLbtn(Paction,Pevent);                         //~1A04R~
            else                                                   //~1A04R~
            if (mouse==AxeMouse.MK_LBUTTON2)                       //~1A04R~
                mouseLbtn2(Paction,Pevent); //dblclick             //~1A04R~
            else                                                   //~1A04R~
            if (mouse==AxeMouse.MK_LBUTTON3)                       //~1A04I~
                mouseLbtnDrag(Paction,Pevent); //dblclick          //~1A04I~
            else                                                   //~1A04I~
            if (mouse==AxeMouse.MK_RBUTTON)                                                 //~1A03I~//~1A04R~
                mouseRbtn(Paction);                                //~1A04R~
        	if (Paction==KeyEvent.ACTION_UP)  //reset at button up for mouse click//~1A04I~
            	if (dragging==0)                                   //~1A04I~
		    		resetModifier();                               //~1A04I~
        }                                                          //~1A04I~
        else                                                       //~1A03I~
        {                                                          //~1A04I~
        	if (Paction==KeyEvent.ACTION_DOWN)                     //~1815R~
        		kbdInput(KeyEvent.ACTION_DOWN,key,Pscancode);      //~1831R~
	    	resetModifier();                                        //~va15I~//~1A03R~//~1A04R~
        }                                                          //~1A04I~
    }//charInput                                                   //~1919R~
//********************************************                     //~1A04I~
    private  void mouseLbtn(int Paction,KeyEvent Pevent)           //~1A04I~
    {                                                              //~1A04I~
    	dragEnd(true);                                             //~1A04I~
		AxeG.axeMouse.onTouchByKbdLB(Paction,Pevent);              //~1A04I~
    }                                                              //~1A04I~
//********************************************                     //~1A04I~
    private  void mouseLbtn2(int Paction,KeyEvent Pevent)	//dbl clcik//~1A04I~
    {                                                              //~1A04I~
    	dragEnd(true);                                             //~1A04I~
		AxeG.axeMouse.onTouchByKbdLB2(Paction,Pevent);             //~1A04I~
    }                                                              //~1A04I~
//********************************************                     //~1A04I~
    private  void mouseLbtnDrag(int Paction,KeyEvent Pevent)	//dbl clcik//~1A04I~
    {                                                              //~1A04I~
        if (Paction!=KeyEvent.ACTION_DOWN)                         //~1A04I~
        	return;                                                //~1A04I~
    	if (dragging!=0)                                           //~1A04I~
        {                                                          //~1A04I~
        	dragEnd(false);                                             //~1A04I~
            return;                                                //~1A04I~
        }                                                          //~1A04I~
        dragging=DRAG_START;                                       //~1A04I~
        AxeG.axeMouse.onTouchByKbdLBDragStart(Pevent);                   //~1A04R~
    }                                                              //~1A04I~
//********************************************                     //~1A04I~
    private  void dragEnd(boolean Pcancel)	//dbl clcik            //~1A04I~
    {                                                              //~1A04I~
	    AxeG.axeMouse.onTouchByKbdLBDragEnd();                     //~1A04R~
    	dragging=0;                                                //~1A04I~
    }                                                              //~1A04I~
//********************************************                     //~1A04I~
    private  void mouseRbtn(int Paction)                                    //~1A04I~
    {                                                              //~1A04I~
        int mod=(modifierStatus & (AxeKeyValue.KBF_CONTROL|AxeKeyValue.KBF_ALT|AxeKeyValue.KBF_SHIFT));//~1A04I~
    	dragEnd(true);                                             //~1A04I~
    	AxeG.axeMouse.onTouchByKbdRB(Paction,mod);                 //~1A04R~
    }                                                              //~1A04I~
//********************************************                     //~1815I~
//*apply Shift/AltGr/Shortcut mapping                              //~1815R~
//*6 type key sequence                                             //~1815I~
//*                         Apply Shift at first  Apply CapsLock   //~1815I~
//* Shift->AltGr-->S.C                            Y                //~1815I~
//* Shift->S.C  -->AltGr                          Y                //~1815I~
//* AltGr->Shift-->S.C                            Y                //~1815I~
//* AltGr->S.C  -->Shift    Y                     Y                //~1919R~
//* S.C  ->AltGr-->Shift    Y                     Y                //~1919R~
//* S.C  ->Shift-->AltGr                          Y                //~1815I~
//********************************************                     //~1815I~
//  private int codeMapping(int Punicode)                          //~va15R~//~vc2tR~
    private int codeMapping(int Punicode,KeyEvent Pevent)          //~vc2tI~
    {                                                              //~va15I~
//        int unicode,key,ch;                                      //~1815R~
//    //***************                                            //~1815R~
//        if (Dump.Y) Dump.println("getShortcutcode unicode="+Integer.toHexString(Punicode)+",altgr="+isAltGr+",shortcut="+shortcutStatus);//~1815R~
//        ch=Punicode;                                             //~1815R~
//        if (capslockStatus)                                      //~1815R~
//            if (Punicode>='a' && Punicode<='z')                  //~1815R~
//                ch='A'+(Punicode-'a');                           //~1815R~
//        switch(shortcutStatus)                                   //~1815R~
//        {                                                        //~1815R~
//        case SC_ACTIVE:                                          //~1815R~
//            if ((modifierStatus & AxeKeyValue.KBF_SHIFT)!=0)    //Shortcut+Shift//~1815R~
//                unicode=getShiftcode(ch);                        //~1815R~
//            else                                                 //~1815R~
//                unicode=ch;                                      //~1815R~
//            if (isAltGr)                                         //~1815R~
//                unicode=getAltGrCode(unicode);                   //~1815R~
//            key=getShortcutcode(unicode);                        //~1815R~
//            if (key==0) //no shortcut defined                    //~1815R~
//                key=unicode;                                     //~1815R~
//            break;                                               //~1815R~
////        case SC_ACTIVE_AFTER_SHIFT:                 //Shift+Shortcut//~1815R~
////            key=getShortcutcode(ch);                           //~1815R~
////            if (key==0) //no shortcut defined                  //~1815R~
////                key=getShiftcode(ch);                          //~1815R~
////            break;                                             //~1815R~
//        default:        //no shortcut                            //~1815R~
//            if ((modifierStatus & AxeKeyValue.KBF_SHIFT)!=0)    //Shortcut+Shift//~1815R~
//                key=getShiftcode(ch);                            //~1815R~
//            else                                                 //~1815R~
//                key=ch;                                          //~1815R~
//            if (isAltGr)                                         //~1815R~
//                key=getAltGrCode(key);                           //~1815R~
//        }                                                        //~1815R~
//        if (Dump.Y) Dump.println("codeMapping return="+Integer.toHexString(key));//~1815R~
//        return key;                                              //~1815R~
	                                                               //~1815I~
		int stackctr,ch,ii,btnid;                                           //~1815I~
        boolean shifted;                                           //~1815I~
        AxeButtonAction ba;                                        //~1815I~
        AxeButton btn;                                             //~1815I~
	//***************                                              //~1815I~
        if (Dump.Y) Dump.println("AxeKeyAction.codemapping unicode="+Integer.toHexString(Punicode)+",altgr="+isAltGr+",shortcut="+shortcutStatus+",shift="+((modifierStatus & AxeKeyValue.KBF_SHIFT)!=0));//~1815I~//~vaigR~
        ch=Punicode;                                               //~1815I~
    	stackctr=AxeButtonAction.toggleStack.size();               //~1815I~
		if (Dump.Y) Dump.println("AxeKeyAction.codeMapping stackctr="+stackctr);//~1815I~//~vaigR~
        if (stackctr==0)                                           //~1815I~
        {                                                          //~vc2wI~
            if (capslockStatus)                                    //~vc2wI~
            {                                                      //~vc2wI~
                if (ch>='a' && ch<='z')                            //~vc2wI~
                    ch='A'+(Punicode-'a');                         //~vc2wI~
//              else                       //ignore Kbd Caps/Shift, effective button only//~vc2wR~
//              if (ch>='A' && ch<='Z')                            //~vc2wR~
//                  ch='a'+(Punicode-'A');                         //~vc2wR~
            }                                                      //~vc2wI~
            return ch;                                             //~1815I~
        }                                                          //~vc2wI~
        ii=stackctr-1;                                             //~1815I~
        ba=AxeButtonAction.toggleStack.get(ii);  //latest modifier //~1815I~
        btn=ba.button;                                             //~1815I~
        btnid=btn.buttonId;                                        //~1815I~
//        if (ch>='a' && ch<='z')                                    //~1815I~//~vc2wR~
//        {                                                          //~1815I~//~vc2wR~
//            shifted=(btnid==AxeButton.BUTTON_SHIFT);               //~1815I~//~vc2wR~
//            if (shifted ^ capslockStatus)//one of two is on        //~1815I~//~vc2wR~
//            {                                                      //~1815I~//~vc2wR~
//                ch='A'+(Punicode-'a');                             //~1815I~//~vc2wR~
//                if (Dump.Y) Dump.println("AxeKeyAction.codemapping CAPS unicode="+Integer.toHexString(ch));//~1815I~//~vaigR~//~vc2wR~
//            }                                                      //~1815I~//~vc2wR~
//            if (btnid==AxeButton.BUTTON_SHIFT)  //processed        //~1815I~//~vc2wR~
//                ii--;                                              //~1815I~//~vc2wR~
//        }                                                          //~1815I~//~vc2wR~
        for (;ii>=0;ii--) //LiFo seq                               //~1815I~
        {	                                                       //~1815I~
        	ba=AxeButtonAction.toggleStack.get(ii);                //~1815I~
            btn=ba.button;                                         //~1815I~
            btnid=btn.buttonId;                                    //~1815I~
			if (Dump.Y) Dump.println("AxeKeyAction.codeMapping btnid="+Integer.toHexString(btnid));//~vc2wI~
            switch(btnid)                                          //~1815I~
            {                                                      //~1815I~
            case AxeButton.BUTTON_SHIFT:                                     //~1815I~
                if (capslockStatus)                                //~vc2wI~
                {                                                  //~vc2wI~
					if (ch>='a' && ch<='z')                        //~vc2wI~
						break;                                     //~vc2wI~
                }                                                  //~vc2wI~
                else                                               //~vc2wI~
                {                                                  //~vc2wI~
					if (ch>='A' && ch<='Z')                        //~vc2wI~
						break;                                     //~vc2wI~
                }                                                  //~vc2wI~
				ch=getShiftcode(ch);	//generate CharCode        //~1815I~
            	break;                                             //~1815I~
            case AxeButton.BUTTON_SHORTCUT:                                  //~1815I~
//  			ch=getShortcutcode(ch); //may generate ext key     //~1815I~//~vc2tR~
    			ch=getShortcutcode(ch,Pevent); //may generate ext key//~vc2tI~
            	break;                                             //~1815I~
            case AxeButton.BUTTON_ALTGR:                                     //~1815I~
				ch=getAltGrCode(ch);   //generate charcode        //~1815I~
            	break;                                             //~1815I~
            default:                                               //~vc2wI~
            }                                                      //~1815I~
        }
		if (Dump.Y) Dump.println("AxeKeyAction.codeMapping rc="+Integer.toHexString(ch));//~vc2wI~
        return ch;//~1815I~
    }                                                              //~va15I~
    private int getShiftcode(int Punicode)                         //~va15R~
    {                                                              //~va15I~
    	int unicode;                                           //~va15I~
    //***************                                              //~va15I~
		if (Dump.Y) Dump.println("AxeKeyAction.getShiftcode unicode="+Integer.toHexString(Punicode));//~va15I~//~vaigR~
        if ((unicode=AxeG.axeKey.getShiftcode(Punicode))==AxeKey.KEYVALUE_NOTDEF)//~va15I~
        	unicode=Punicode;                                      //~va15R~
        else                                                       //~va15I~
        if (unicode>AxeKey.END_OF_ASCII)                           //~va15I~
        {                                                          //~va15I~
        	unicode=AxeKeyValue.setUnicodeId(unicode);             //~va15I~
        }                                                          //~va15I~
		if (Dump.Y) Dump.println("AxeKeyAction.getShiftcode return="+Integer.toHexString(unicode));//~vaigR~
		return unicode;//~va15I~
    }                                                              //~va15I~
    private int getAltGrCode(int Punicode)                         //~va15I~
    {                                                              //~va15I~
    	int unicode;                                               //~va15I~
    //***************                                              //~va15I~
		if (Dump.Y) Dump.println("AxeKeyAction.getAltGrCode unicode="+Integer.toHexString(Punicode));//~va15I~//~vaigR~
        if ((unicode=AxeG.axeKey.getAltGrCode(Punicode))==AxeKey.KEYVALUE_NOTDEF)//~va15I~
        	unicode=Punicode;                                      //~va15I~
        else                                                       //~va15I~
        if (unicode>AxeKey.END_OF_ASCII)                           //~va15I~
        {                                                          //~va15I~
        	unicode=AxeKeyValue.setUnicodeId(unicode);             //~va15I~
        }                                                          //~va15I~
		if (Dump.Y) Dump.println("AxeKeyAction.getAltGrCode return="+Integer.toHexString(unicode));//~va15I~//~vaigR~
		return unicode;                                            //~va15I~
    }                                                              //~va15I~
//  private int getShortcutcode(int Punicode)                      //~va15R~//~vc2tR~
    private int getShortcutcode(int Punicode,KeyEvent Pevent)      //~vc2tI~
    {                                                              //~va15I~
    	int unicode;                                           //~va15I~
    //***************                                              //~va15I~
		if (Dump.Y) Dump.println("AxeKeyAction.getShortcutcode unicode="+Integer.toHexString(Punicode));//~va15I~//~vaigR~
        if ((unicode=AxeG.axeKey.getShortcutcode(Punicode))==AxeKey.KEYVALUE_NOTDEF)//unicode flag is already set//~va15R~
        {                                                          //~vc2tI~
          if (!(Punicode>='a' && Punicode<='z') && !(Punicode>='A' && Punicode<='Z'))//~vc2FR~
          {                                                        //~vc2FI~
            unicode=Punicode;                                      //~va15I~
            int kc=Pevent.getKeyCode();                            //~vc2tR~
            int base=AxeKbdDialogHW.keycodeToCode(kc);             //~vc2tI~
        	int sc=AxeG.axeKey.getShortcutcode(base); //shortcut defined on base//~vc2tR~
            if (sc!=AxeKey.KEYVALUE_NOTDEF)		//defined on base  //~vc2tM~
            {                                                      //~vc2tM~
	            int meta=Pevent.getMetaState();                    //~vc2tM~
    	        int mod=AxeKeyValue.metaToMod(meta);               //~vc2tM~
                unicode=mod|sc;                                    //~vc2tM~
            }                                                      //~vc2tM~
          }                                                        //~vc2FI~
        }                                                          //~vc2tI~
		if (Dump.Y) Dump.println("AxeKeyAction.getShortcutcode return="+Integer.toHexString(unicode));//~va15I~//~vaigR~
		return unicode;                                            //~va15I~
    }                                                              //~va15I~
//***********************                                          //~1920I~
//*Arrow Button                                                    //~1920I~
//***********************                                          //~1920I~
    public void gdkKey(int PgdkKey)                               //~1920I~
    {                                                              //~1920I~
    //***************                                              //~1920I~
		int key=PgdkKey|getModifier();                                 //~1920I~
		if (Dump.Y) Dump.println("AxeKeyAction:gdkKey key+mod="+Integer.toHexString(key));//~1920I~
        kbdInput(KeyEvent.ACTION_DOWN,key,0/*scancode*/);          //~1920I~
        kbdInput(KeyEvent.ACTION_UP,key,0/*scancode*/);            //~1920I~
    }                                                              //~1920I~
//***********************                                          //~va15I~
//*modifier                                                        //~va15I~
//***********************                                          //~va15I~
    public int getModifier()                                       //~va15I~
    {                                                              //~va15I~
		if (Dump.Y) Dump.println("AxeKeyAction.getModifier "+modifierStatus     //~va15I~//~vaigR~
				+"Shift="+((modifierStatus & AxeKeyValue.KBF_SHIFT)!=0)//~va15R~
				+"Ctrl="+((modifierStatus & AxeKeyValue.KBF_CONTROL)!=0)//~va15R~
				+"Alt="+((modifierStatus & AxeKeyValue.KBF_ALT)!=0));//~va15R~
    	return modifierStatus;                                     //~va15I~
    }                                                              //~va15I~
    public int getModifilerforMouse()                              //~1926I~
    {                                                              //~1926I~
		return modifierStatus>>16;                                 //~1926I~
    }                                                              //~1926I~
    public void resetModifier()                                 //~va15I~//~1A03R~
    {                                                              //~va15I~
		if (Dump.Y) Dump.println("AxeKeyAction.resetModifier");                 //~va15I~//~vaigR~
    	modifierStatus=0;                                          //~va15I~
    	shortcutStatus=0;                                          //~va15I~
    	isAltGr=false;                                             //~va15I~
        AxeButtonAction.resetToggle();  //reset toggle button active status//~1814R~
    }                                                              //~va15I~
    public void setModifier(boolean PsetOn,int Pvalue)             //~va15I~
    {                                                              //~va15I~
		if (Dump.Y) Dump.println("AxeKeyAction.setModifier "+PsetOn+",value="+Integer.toHexString(Pvalue));//~va15I~//~vaigR~
    	if (PsetOn)                                                //~va15I~
        	modifierStatus|=Pvalue;                                //~va15I~
        else                                                       //~va15I~
        	modifierStatus&=~Pvalue;                               //~va15I~
		if (Dump.Y) Dump.println("AxeKeyAction.setModifier new "+modifierStatus //~va15I~//~vaigR~
				+"Shift="+((modifierStatus & AxeKeyValue.KBF_SHIFT)!=0)//~va15R~
				+"Ctrl="+((modifierStatus & AxeKeyValue.KBF_CONTROL)!=0)//~va15R~
				+"Alt="+((modifierStatus & AxeKeyValue.KBF_ALT)!=0));//~va15R~
    }                                                              //~va15I~
//*shift toggle                                                    //~va15I~
    public boolean toggleModifier(int Pvalue)                      //~va15I~
    {                                                              //~va15I~
        boolean on=(modifierStatus & Pvalue)!=0;                   //~va15I~
		if (Dump.Y) Dump.println("AxeKeyAction.toggleModifier old="+on+",value="+Integer.toHexString(Pvalue));//~va15I~//~vaigR~
    	if (on)                                                    //~va15I~
        	modifierStatus&=~Pvalue;                               //~va15I~
        else                                                       //~va15I~
        	modifierStatus|=Pvalue;                                //~va15I~
		if (Dump.Y) Dump.println("AxeKeyAction.toggleModifier new "+modifierStatus//~va15I~//~vaigR~
				+"Shift="+((modifierStatus & AxeKeyValue.KBF_SHIFT)!=0)//~va15R~
				+"Ctrl="+((modifierStatus & AxeKeyValue.KBF_CONTROL)!=0)//~va15R~
				+"Alt="+((modifierStatus & AxeKeyValue.KBF_ALT)!=0));//~va15R~
        return !on;	//new status;                                  //~va15I~
    }                                                              //~va15I~
//*shortcut toggle                                                 //~va15I~
    public int toggleShortcut()                                    //~va15I~
    {                                                              //~va15I~
        boolean on=(shortcutStatus!=0);                            //~va15I~
		if (Dump.Y) Dump.println("AxeKeyAction.toggleShortcut old="+on);        //~va15I~//~vaigR~
    	if (on)                                                    //~va15I~
        	shortcutStatus=0;                                      //~va15I~
        else                                                       //~va15I~
        {                                                          //~va15I~
//            if ((modifierStatus & AxeKeyValue.KBDFLAG_SHIFT)!=0) //~va15R~
//                shortcutStatus=SC_ACTIVE_AFTER_SHIFT;            //~va15R~
//            else                                                 //~va15R~
	        	shortcutStatus=SC_ACTIVE;                          //~va15I~
        }                                                          //~va15I~
		if (Dump.Y) Dump.println("AxeKeyAction.toggleShortcut new "+shortcutStatus);//~va15I~//~vaigR~
        return shortcutStatus;	//new status;                      //~va15I~
    }                                                              //~va15I~
//*shortcut toggle                                                 //~va15I~
    public boolean toggleAltGr()                                   //~va15I~
    {                                                              //~va15I~
    	isAltGr=!isAltGr;                                          //~va15I~
		if (Dump.Y) Dump.println("AxeKeyAction.toggleAltGr new="+isAltGr);      //~va15I~//~vaigR~
        return isAltGr;                                            //~va15I~
    }                                                              //~va15I~
//*shortcut toggle                                                 //~va15I~
    public boolean toggleCapslock()                                //~va15I~
    {                                                              //~va15I~
		if (Dump.Y) Dump.println("AxeKeyAction.toggleCapslock old="+capslockStatus);//~va15I~//~vaigR~
        capslockStatus=!capslockStatus;                            //~va15I~
    	return capslockStatus;                                    //~va15I~
    }                                                              //~va15I~
    public boolean getCapslockStatus()                             //~va15I~
    {                                                              //~va15I~
		if (Dump.Y) Dump.println("AxeKeyAction.getCapslockStatus="+capslockStatus);//~va15I~//~vaigR~
    	return capslockStatus;                                    //~va15I~
    }                                                              //~va15I~
    public int getShortcutStatus()                             //~vaigI~
    {                                                              //~vaigI~
		if (Dump.Y) Dump.println("AxeKeyAction.getShortcutStatus="+shortcutStatus);//~vaigR~
        return shortcutStatus;                                     //~vaigI~
    }                                                              //~vaigI~
//enter by ControlR                                                //~va15I~
    public void enterControlR()                                 //~va15I~
    {                                                              //~va15I~
		if (Dump.Y) Dump.println("AxeKeyAction.enterControlR");                 //~va15I~//~vaigR~
        int key=AxeKeyValue.GDK_Control_R;                                     //~va15I~
        key|=AxeKeyValue.KBF_RIGHTCONTROL|AxeKeyValue.KBF_CONTROL;//~va15R~
    	kbdInput(-1,key,0/*scancode*/);                            //~1815R~
    }                                                              //~va15I~
//enter by userKeyButton                                           //~1A03R~
    public void userKeyButton(AxeButton Pbutton)                   //~va15I~
    {                                                              //~va15I~
		if (Dump.Y) Dump.println("AxeKeyAction.userKeyButton name="+Pbutton.name+",GDKkey="+Integer.toHexString(Pbutton.userGDK));//~va15I~//~vaigR~
        int key=Pbutton.userGDK;                                   //~va15I~
	 	kbdInput(-1,key,0/*scancode*/);                            //~1815R~
    }                                                              //~va15I~
//**************************************************               //~va15I~
//*input to xe                                                     //~va15R~
//*Action:up/down/multibyte/-1                                     //~va15I~
//*UNICODE bit on : unicode>=x80                                   //~va15I~
//*UNICODE bit off: ffxx:extkey                                    //~va15I~
//*UNICODE bit off: 00xx:ascii char                                //~va15I~
//**************************************************               //~va15I~
    public void kbdInput(int Paction,int Pkey,int Pscancode)       //~1815R~
    {                                                              //~va15I~
    	int action,key;                                            //~va15R~
    //*****************                                            //~va15I~
		if (Dump.Y) Dump.println("AxeKeyAction.kbdinput action="+Paction+",key="+Integer.toHexString(Pkey)+",scancode="+Integer.toHexString(Pscancode));//~va15I~//~1801R~//~vaigR~//~vc26R~//~vc32R~
//      screen.drawText(Pkey);                                     //~va15M~
        key=Pkey;	                                               //~va15I~
        if (AxeKeyValue.isUnicodeKeyValue(key))                    //~va15R~
        {                                                          //~va15I~
            key=AxeKeyValue.getUnicodeKeyValue(key);	//drop bit //~va15I~
            action=KBDACTION_UNICODE;       //ignore modifier      //~va15R~
        }                                                          //~va15I~
        else                                                       //~va15I~
        if (!AxeKeyValue.isValidExtGDK(key))	//ffxx:extended key//~va15R~
            action=KBDACTION_CHAR;			//modifier effective   //~va15R~
        else                                                       //~va15I~
        if (Paction==KeyEvent.ACTION_UP)                           //~va15I~
            action=KBDACTION_UP;                                   //~va15I~
        else                                                       //~va15I~
            action=KBDACTION_DOWN;                                 //~va15I~
        AxeJNI.kbdmsg(action,key,Pscancode);                       //~1815R~
//        if (dragging!=0)                                           //~1A04I~
//        	dragMove(action,key);                                  //~1A04I~
    }                                                              //~va15I~
    //*************************************************************//~1A10I~
    //*from AxeKbdDialog:soft keyboard input                       //~1A10I~
    //*************************************************************//~1A10I~
    public void sendSoftKbdInput(int Pgdkcode,int Pmetaflag,boolean Pshortcut)//~1A10R~
    {                                                              //~1919I~
        int key,scancode=0,metastate,action;                              //~1A10R~
        boolean shortcut=false;                                    //~1B05I~
    //***************                                              //~1919I~
    	if (Dump.Y) Dump.println("AxeKeyAction.sendSoftKbdInput code="+Integer.toHexString(Pgdkcode)+",metaflag="+Integer.toHexString(Pmetaflag));//~1919I~//~vaigR~
        key=Pgdkcode|Pmetaflag;                                    //~1919I~
        if (AxeKeyValue.isValidExtGDK(Pgdkcode))                   //~1919I~
        {                                                          //~1919I~
        	kbdInput(KeyEvent.ACTION_DOWN,key,scancode);           //~1919I~
        	kbdInput(KeyEvent.ACTION_UP,key,scancode);             //~1919I~
        }                                                          //~1919I~
        else	//not extended key                                 //~1919I~
        {                                                          //~1919I~
            if (Pgdkcode>AxeKey.END_OF_ASCII)                      //~1919I~
            {                                                      //~1919I~
                key=AxeKeyValue.setUnicodeId(Pgdkcode);            //~1919I~
            }                                                      //~1919I~
            else                                                   //~1919I~
            {                                                      //~1A10I~
            	scancode=SCANCODE_SOFTKBD;                         //~1919R~
                if (Pshortcut)                                     //~1A10I~
                {                                                  //~1A10I~
        			metaFlag_DialogKbd=1;                          //~1A10I~
                    metastate=0;                                   //~1A10I~
                    int unicode=Pgdkcode;                          //~vc2vR~
                    int keycode=getKeycodeOfUnicode(unicode,Pmetaflag);//~vc2vR~
                    if (keycode<0)                                 //~vc2vI~
                    	keycode=unicode;                           //~vc2vI~
                    action=KeyEvent.ACTION_DOWN;                   //~1A10I~
//                  KeyEvent event=new KeyEvent(0/*downtime*/,0/*eventtime*/,action,Pgdkcode,1/*repeat*/,metastate);//~1A10I~//~vc2vR~
                    KeyEvent event=new KeyEvent(0/*downtime*/,0/*eventtime*/,action,keycode,1/*repeat*/,metastate);//~vc2vI~
					charInput(action,Pgdkcode,scancode,event);     //~1A10I~
        			metaFlag_DialogKbd=0;                          //~1A10I~
                    shortcut=true;                                 //~1B05I~
                }                                                  //~1A10I~
            }                                                      //~1A10I~
            if (!shortcut)                                         //~1B05I~
    	    	kbdInput(KeyEvent.ACTION_DOWN,key,scancode);           //~1919I~//~1B05R~
        }                                                          //~1919I~
    }                                                              //~1919I~
    //************************************************             //~vc2vI~
    private int getKeycodeOfUnicode(int Punicode,int Pmetaflag)    //~vc2vI~
    {                                                              //~vc2vI~
        int flatcode=Punicode;                                     //~vc2vI~
        if ((Pmetaflag & KBF_SHIFT)!=0)                            //~vc2vR~
        {                                                          //~vc2vI~
        	flatcode=AxeLstKbdLayout.getBasecodeOfShifted(Punicode);//~vc2vI~
        }                                                          //~vc2vI~
        int keycode=AxeLstKbdLayoutHW.getKeycodeOfUnicode(flatcode);//~vc2vI~
    	if (Dump.Y) Dump.println("AxeKeyAction.getKeycodeOfUnicode unicode="+Integer.toHexString(Punicode)+",metaflag="+Integer.toHexString(Pmetaflag)+",keycode=="+Integer.toHexString(keycode));//~vc2vI~
        return keycode;                                            //~vc2vI~
    }                                                              //~vc2vI~
}//class                                                           //~va15R~
