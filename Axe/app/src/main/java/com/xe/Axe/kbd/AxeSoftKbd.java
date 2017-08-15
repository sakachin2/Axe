//*CID://+vaybR~:                             update#=  139;       //+vaybR~
//*********************************************************************//~vaatI~
//vayd:141125 (Axe)modifier reset option                           //~vaydI~
//vayb:141125 (Axe)Disply:getWidth/getHeight was deprecated at aoi13(HONNEYCOMB_MR2) change to getSize//+vaybI~
//vaaw:120105 (Axe)add Fn modifier to set key label to Fn          //~vaawI~
//vaat:120103 (Axe:Bug)xekbd should support orientaion change      //~vaatI~
//*********************************************************************//~vaatI~
//*from sample:SoftKeyboard.java                                   //~@@@@I~
//* handle key event from AxeKbdView                               //~@@@@R~
//***********************************************                  //~@@@@I~
package com.xe.Axe.kbd;                                            //~@@@@R~
                                                                   //~@@@@I~
import android.content.Context;
import android.graphics.Point;
import android.os.SystemClock;
import android.view.KeyEvent;                                      //~@@@@I~
import android.view.View;                                          //~@@@@I~
import android.view.WindowManager;
                                                                   //~@@@@I~
import com.xe.Axe.kbd.ims.Keyboard;                                //~@@@@M~
import com.xe.Axe.kbd.ims.Keyboard.Key;
import com.xe.Axe.AxeView;
import com.xe.Axe.R;                                               //~@@@@I~
import com.xe.Axe.AxeG;                                            //~@@@@I~
import com.xe.Axe.Dump;
import com.xe.Axe.AxeKeyValue;//~@@@@I~

public class AxeSoftKbd                                            //~@@@@R~
        implements AxeKbdView.OnKbdViewListener                    //~@@@@R~
{                                                                  //~@@@@I~
    private static final int META_SHIFT=AxeKeyValue.KBF_SHIFT;//0x00010000//~@@@@R~
    private static final int META_CTL  =AxeKeyValue.KBF_CONTROL;//0x00040000//~@@@@R~
    private static final int META_ALT  =AxeKeyValue.KBF_ALT;    //0x00080000//~@@@@R~
                                                                   //~@@@@I~
    private static final int INPUT_FLICK=1;                        //~@@@@I~
    private static final int INPUT_MINIKBD=-1;                     //~@@@@I~
    private static final int INPUT_SOFTKBD=0;                       //~@@@@I~
                                                                   //~@@@@I~
//    public static final String KEYID_UNICODE="u-";               //~@@@@R~
//    public static final String KEYID_FIXED="";                   //~@@@@R~
    public static final int KEYID_EXTKEY=0x010000;              //~@@@@I~
    public static final int KEYID_FIXED=-1;                     //~@@@@I~
                                                                   //~@@@@I~
    static final int KBDLAYOUT=R.xml.kbd_xe;                       //~@@@@R~
//    static final boolean PROCESS_HARD_KEYS = true;               //~@@@@R~
    
//    private LatinKeyboardView mInputView;                        //~@@@@R~
    private AxeKbdView mInputView;                                 //~@@@@R~
    
    private int mLastDisplayWidth;
    private boolean mCapsLock;                                      //~@@@@R~
//    private long mLastShiftTime;
//    private long mMetaState;
    
//    private LatinKeyboard mSymbolsKeyboard;
//    private LatinKeyboard mSymbolsShiftedKeyboard;
//    private LatinKeyboard mQwertyKeyboard;                       //~@@@@R~
    private AxeKbd axeKbd;                                         //~@@@@R~
    
//    private LatinKeyboard mCurKeyboard;
    
    private Context context;                                       //~@@@@I~
    private AxeKbdDialog kbdDialog;
    private OnSoftKbdListener listener;//~@@@@R~
    private int [][] kbdLayoutCodeTbl;                               //~@@@@I~
//    private int [][] kbdLayoutCodeTblDefault;                    //~@@@@R~
    
//    @Override public void onCreate() {                           //~@@@@R~
//        super.onCreate();                                        //~@@@@R~
//        mWordSeparators = getResources().getString(R.string.word_separators);//~@@@@R~
//    }                                                            //~@@@@R~
    public interface OnSoftKbdListener                             //~@@@@M~
    {                                                              //~@@@@M~
		public void sendSoftKbdKey(int Pkeycode,int Pmetastate,boolean Pshortcut);//~@@@@R~
    }                                                              //~@@@@M~
//********************************************************************//~@@@@I~
    public AxeSoftKbd(AxeKbdDialog Pdlg)                           //~@@@@R~
    {                                                              //~@@@@R~
       kbdDialog=Pdlg;                                             //~@@@@R~
       if (Dump.Y) System.out.println("Softkbd onCreate");         //~@@@@R~
       context=AxeG.context;                                       //~@@@@R~
    }                                                              //~@@@@R~
    public void setSoftKbdListener(OnSoftKbdListener Plistener)         //~@@@@I~
    {                                                              //~@@@@I~
       listener=Plistener;                                         //~@@@@I~
       if (Dump.Y) System.out.println("Softkbd setListener");      //~@@@@I~
    }                                                              //~@@@@I~
//********************************************************************//~@@@@I~
//  /*@Override*/ public void onInitializeInterface() {            //~@@@@R~//~vaatR~
    public boolean onInitializeInterface() {                       //~vaatI~
        if (Dump.Y) Dump.println("Softkbd onInitializeInterface");                //~@@@@I~//~vaatR~
//        if (mQwertyKeyboard != null) {                           //~@@@@R~
        if (axeKbd != null) {                                      //~@@@@R~
            // Configuration changes can happen after the keyboard gets recreated,
            // so we need to be able to re-build the keyboards if the available
            // space has changed.
            int displayWidth = getMaxWidth();                      //~@@@@R~
//          if (displayWidth == mLastDisplayWidth) return;         //~vaatR~
            if (displayWidth == mLastDisplayWidth)                 //~vaatI~
                return false;                                      //~vaatI~
            mLastDisplayWidth = displayWidth;
        }
//        mQwertyKeyboard = new LatinKeyboard(this, R.xml.qwerty); //~@@@@R~
//        mSymbolsKeyboard = new LatinKeyboard(this, R.xml.symbols);//~@@@@R~
//        mSymbolsShiftedKeyboard = new LatinKeyboard(this, R.xml.symbols_shift);//~@@@@R~
        axeKbd=new AxeKbd(context,KBDLAYOUT);                      //~@@@@R~
        return true;                                               //~vaatI~
    }
//*************************************                            //~@@@@R~
//*from KbdDialog                                                  //~@@@@I~
//*************************************                            //~@@@@I~
    public View onCreateInputView()                                //~@@@@R~
    {                                                              //~@@@@I~
        if (Dump.Y) Dump.println("Softkbd onCreateView");          //~@@@@R~
//      mInputView = (LatinKeyboardView)Canvastest2.mainactivity.getLayoutInflater().inflate(//~@@@@R~
//              R.layout.input, null);                             //~@@@@R~
        mInputView = (AxeKbdView)AxeG.inflater.inflate(            //~@@@@R~
                R.layout.keyboard_view,null);                      //~@@@@R~
//      mInputView.setOnKeyboardActionListener(this);              //~@@@@R~
        mInputView.setKbdListener(this);                         //~@@@@I~
//        mInputView.setKeyboard(mQwertyKeyboard);                 //~@@@@R~
        mInputView.setKeyboard(axeKbd);                            //~@@@@R~
        return mInputView;
    }

    
//    private boolean translateKeyDown(int keyCode, KeyEvent event) {//~@@@@R~
//        mMetaState = MetaKeyKeyListener.handleKeyDown(mMetaState,//~@@@@R~
//                keyCode, event);                                 //~@@@@R~
//        int c = event.getUnicodeChar(MetaKeyKeyListener.getMetaState(mMetaState));//~@@@@R~
//        mMetaState = MetaKeyKeyListener.adjustMetaAfterKeypress(mMetaState);//~@@@@R~
//        InputConnection ic = getCurrentInputConnection();        //~@@@@R~
//        if (c == 0 || ic == null) {                              //~@@@@R~
//            return false;                                        //~@@@@R~
//        }                                                        //~@@@@R~
//                                                                 //~@@@@R~
//        boolean dead = false;                                    //~@@@@R~

//        if ((c & KeyCharacterMap.COMBINING_ACCENT) != 0) {       //~@@@@R~
//            dead = true;                                         //~@@@@R~
//            c = c & KeyCharacterMap.COMBINING_ACCENT_MASK;       //~@@@@R~
//        }                                                        //~@@@@R~
//                                                                 //~@@@@R~
//        if (mComposing.length() > 0) {                           //~@@@@R~
//            char accent = mComposing.charAt(mComposing.length() -1 );//~@@@@R~
//            int composed = KeyEvent.getDeadChar(accent, c);      //~@@@@R~

//            if (composed != 0) {                                 //~@@@@R~
//                c = composed;                                    //~@@@@R~
//                mComposing.setLength(mComposing.length()-1);     //~@@@@R~
//            }                                                    //~@@@@R~
//        }                                                        //~@@@@R~
//                                                                 //~@@@@R~
//        onKey(c, null);                                          //~@@@@R~
//                                                                 //~@@@@R~
//        return true;                                             //~@@@@R~
//    }                                                            //~@@@@R~
//*override Method of InputMethodService                           //~@@@@R~
//    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {//~@@@@R~
//        if (Dump.Y) Dump.println("OnKeyDown keycode="+Integer.toHexString(keyCode));//~@@@@R~
//        switch (keyCode) {                                       //~@@@@R~
//            case KeyEvent.KEYCODE_BACK:                          //~@@@@R~
//                // The InputMethodService already takes care of the back//~@@@@R~
//                // key for us, to dismiss the input method if it is shown.//~@@@@R~
//                // However, our keyboard could be showing a pop-up window//~@@@@R~
//                // that back should dismiss, so we first allow it to do that.//~@@@@R~
//                if (event.getRepeatCount() == 0 && mInputView != null) {//~@@@@R~
//                    if (mInputView.handleBack()) {               //~@@@@R~
//                        return true;                             //~@@@@R~
//                    }                                            //~@@@@R~
//                }                                                //~@@@@R~
//                break;                                           //~@@@@R~
//                                                                 //~@@@@R~
//            case KeyEvent.KEYCODE_DEL:                           //~@@@@R~
//                // Special handling of the delete key: if we currently are//~@@@@R~
//                // composing text for the user, we want to modify that instead//~@@@@R~
//                // of let the application to the delete itself.  //~@@@@R~
//                if (mComposing.length() > 0) {                   //~@@@@R~
//                    onKey(Keyboard.KEYCODE_DELETE, null);        //~@@@@R~
//                    return true;                                 //~@@@@R~
//                }                                                //~@@@@R~
//                break;                                           //~@@@@R~
//                                                                 //~@@@@R~
//            case KeyEvent.KEYCODE_ENTER:                         //~@@@@R~
//                // Let the underlying text editor always handle these.//~@@@@R~
//                return false;                                    //~@@@@R~
//                                                                 //~@@@@R~
//            default:                                             //~@@@@R~
//                // For all other keys, if we want to do transformations on//~@@@@R~
//                // text being entered with a hard keyboard, we need to process//~@@@@R~
//                // it and do the appropriate action.             //~@@@@R~
//                if (PROCESS_HARD_KEYS) {                         //~@@@@R~
//                    if (keyCode == KeyEvent.KEYCODE_SPACE        //~@@@@R~
//                            && (event.getMetaState()&KeyEvent.META_ALT_ON) != 0) {//~@@@@R~
//                        // A silly example: in our input method, Alt+Space//~@@@@R~
//                        // is a shortcut for 'android' in lower case.//~@@@@R~
//                        InputConnection ic = getCurrentInputConnection();//~@@@@R~
//                        if (ic != null) {                        //~@@@@R~
//                            // First, tell the editor that it is no longer in the//~@@@@R~
//                            // shift state, since we are consuming this.//~@@@@R~
//                            ic.clearMetaKeyStates(KeyEvent.META_ALT_ON);//~@@@@R~
//                            keyDownUp(KeyEvent.KEYCODE_A);       //~@@@@R~
//                            keyDownUp(KeyEvent.KEYCODE_N);       //~@@@@R~
//                            keyDownUp(KeyEvent.KEYCODE_D);       //~@@@@R~
//                            keyDownUp(KeyEvent.KEYCODE_R);       //~@@@@R~
//                            keyDownUp(KeyEvent.KEYCODE_O);       //~@@@@R~
//                            keyDownUp(KeyEvent.KEYCODE_I);       //~@@@@R~
//                            keyDownUp(KeyEvent.KEYCODE_D);       //~@@@@R~
//                            // And we consume this event.        //~@@@@R~
//                            return true;                         //~@@@@R~
//                        }                                        //~@@@@R~
//                    }                                            //~@@@@R~
//                    if (mPredictionOn && translateKeyDown(keyCode, event)) {//~@@@@R~
//                        return true;                             //~@@@@R~
//                    }                                            //~@@@@R~
//                }                                                //~@@@@R~
//        }                                                        //~@@@@R~
//                                                                 //~@@@@R~
//        return super.onKeyDown(keyCode, event);                  //~@@@@R~
//    }                                                            //~@@@@R~

//*override Method of InputMethodService                           //~@@@@I~
//    @Override public boolean onKeyUp(int keyCode, KeyEvent event) {//~@@@@R~
//        // If we want to do transformations on text being entered with a hard//~@@@@R~
//        // keyboard, we need to process the up events to update the meta key//~@@@@R~
//        // state we are tracking.                                //~@@@@R~
//        if (Dump.Y) Dump.println("OnKeyUp keycode="+Integer.toHexString(keyCode));//~@@@@R~
//        if (PROCESS_HARD_KEYS) {                                 //~@@@@R~
//            if (mPredictionOn) {                                 //~@@@@R~
//                mMetaState = MetaKeyKeyListener.handleKeyUp(mMetaState,//~@@@@R~
//                        keyCode, event);                         //~@@@@R~
//            }                                                    //~@@@@R~
//        }                                                        //~@@@@R~
//                                                                 //~@@@@R~
//        return super.onKeyUp(keyCode, event);                    //~@@@@R~
//    }                                                            //~@@@@R~

//    private void updateShiftKeyState(EditorInfo attr) {          //~@@@@R~
//        if (attr != null                                         //~@@@@R~
//                && mInputView != null && mQwertyKeyboard == mInputView.getKeyboard()) {//~@@@@R~
//            int caps = 0;                                        //~@@@@R~
//            EditorInfo ei = getCurrentInputEditorInfo();         //~@@@@R~
//            if (ei != null && ei.inputType != EditorInfo.TYPE_NULL) {//~@@@@R~
//                caps = getCurrentInputConnection().getCursorCapsMode(attr.inputType);//~@@@@R~
//            }                                                    //~@@@@R~
//            mInputView.setShifted(mCapsLock || caps != 0);       //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~
    
//    private boolean isAlphabet(int code) {                       //~@@@@R~
//        if (Character.isLetter(code)) {                          //~@@@@R~
//            return true;                                         //~@@@@R~
//        } else {                                                 //~@@@@R~
//            return false;                                        //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~
    
//    private void keyDownUp(int keyEventCode) {                   //~@@@@R~
//        if (Dump.Y) Dump.println("KeyDownUp keycode="+Integer.toHexString(keyEventCode));//~@@@@R~
////        getCurrentInputConnection().sendKeyEvent(              //~@@@@R~
////                new KeyEvent(KeyEvent.ACTION_DOWN, keyEventCode));//~@@@@R~
////        getCurrentInputConnection().sendKeyEvent(              //~@@@@R~
////                new KeyEvent(KeyEvent.ACTION_UP, keyEventCode));//~@@@@R~
//        int meta=getMataStatus();                                //~@@@@R~
//        KeyEvent ev;                                             //~@@@@R~
//        long ts=SystemClock.uptimeMillis();                      //~@@@@R~
//        ev=new KeyEvent(ts,ts,KeyEvent.ACTION_DOWN,keyEventCode,1/*repeat*/,meta);//~@@@@R~
//        sendKeyEvent(ev);                                        //~@@@@R~
//        ev=new KeyEvent(ts,ts,KeyEvent.ACTION_UP,keyEventCode,1/*repeat*/,meta);//~@@@@R~
//        sendKeyEvent(ev);                                        //~@@@@R~
//    }                                                            //~@@@@R~
    
    private void sendKey(int keyCode) {
        if (Dump.Y) Dump.println("sendKey keycode="+Integer.toHexString(keyCode));//~@@@@I~
//        switch (keyCode) {                                       //~@@@@R~
//            case '\n':                                           //~@@@@R~
//                keyDownUp(KeyEvent.KEYCODE_ENTER);               //~@@@@R~
//                break;                                           //~@@@@R~
//            default:                                             //~@@@@R~
//                if (keyCode >= '0' && keyCode <= '9') {          //~@@@@R~
//                    keyDownUp(keyCode - '0' + KeyEvent.KEYCODE_0);//~@@@@R~
//                } else {                                         //~@@@@R~
//                    getCurrentInputConnection().commitText(String.valueOf((char) keyCode), 1);//~@@@@R~
//                }                                                //~@@@@R~
//                break;                                           //~@@@@R~
//        }                                                        //~@@@@R~
//        keyDownUp(keyCode);      //send android keycode          //~@@@@R~
		int metastate=getMetaStatus();                              //~@@@@I~
        boolean shortcut=mInputView.isShortcut();                  //~@@@@R~
        listener.sendSoftKbdKey(keyCode,metastate,shortcut);       //~@@@@I~
        if (shortcut)                                              //~@@@@I~
    		mInputView.setShortcut(false);	//shortcut active only once        //~@@@@I~
        if (AxeG.resetModifier)                                    //~vaydI~
        	resetModifier();                                       //~vaydI~
    }
    // Implementation of KeyboardViewListener
                                                                   //~@@@@M~
    @Override	//Listener                                         //~@@@@M~
    public void onFlickRight(int Pcode) {                          //~@@@@M~
		if (Dump.Y) Dump.println("AxeSoftKbd:onSwipe Right code="+Integer.toHexString(Pcode));//~@@@@M~
		onKey(Pcode,INPUT_FLICK);                                  //~@@@@R~
    }                                                              //~@@@@M~
                                                                   //~@@@@M~
    @Override	//Listener                                         //~@@@@M~
    public void onFlickLeft(int Pcode) {                           //~@@@@M~
		if (Dump.Y) Dump.println("AxeSoftKbd:onSwipe Left code="+Integer.toHexString(Pcode));//~@@@@M~
		onKey(Pcode,INPUT_FLICK);                                  //~@@@@R~
    }                                                              //~@@@@M~
                                                                   //~@@@@M~
    @Override	//Listener                                         //~@@@@M~
    public void onFlickDown(int Pcode) {                           //~@@@@M~
		if (Dump.Y) Dump.println("AxeSoftKbd:onSwipe Down code="+Integer.toHexString(Pcode));//~@@@@M~
		onKey(Pcode,INPUT_FLICK);                                  //~@@@@R~
    }                                                              //~@@@@M~
                                                                   //~@@@@M~
    @Override	//Listener                                         //~@@@@M~
    public void onFlickUp(int Pcode) {                             //~@@@@M~
		if (Dump.Y) Dump.println("AxeSoftKbd:onSwipe Up code="+Integer.toHexString(Pcode));//~@@@@M~
		onKey(Pcode,INPUT_FLICK);                                  //~@@@@R~
    }                                                              //~@@@@M~
                                                                   //~@@@@M~
    @Override	//Listener                                         //~@@@@M~
    public void onPress(int primaryCode) {                         //~@@@@M~
		if (Dump.Y) Dump.println("AxeSoftKbd:OnPress keycode="+Integer.toHexString(primaryCode));//~@@@@M~
        if (primaryCode==Keyboard.KEYCODE_SHIFT)                   //~@@@@R~
            mInputView.updateShiftLabel(true/*press*/);            //~@@@@R~
    }                                                              //~@@@@M~
                                                                   //~@@@@M~
    @Override	//Listener                                         //~@@@@M~
    public void onRelease(int primaryCode) {                       //~@@@@M~
		if (Dump.Y) Dump.println("AxeSoftKbd:OnRelease keycode="+Integer.toHexString(primaryCode));//~@@@@M~
        if (primaryCode==Keyboard.KEYCODE_SHIFT)                   //~@@@@I~
        	mInputView.updateShiftLabel(false/*press*/);           //~@@@@I~
    }                                                              //~@@@@M~
    @Override	//Listener                                         //~@@@@I~
    public void onKey(int primaryCode,int[] keyCodes)              //~@@@@R~
    {                                                              //~@@@@I~
	    onKey(primaryCode,keyCodes==null ? INPUT_MINIKBD : INPUT_SOFTKBD);//~@@@@R~
    }                                                              //~@@@@I~
    private void onKey(int primaryCode)                            //~@@@@I~
    {                                                              //~@@@@I~
	    onKey(primaryCode,INPUT_SOFTKBD);                          //~@@@@R~
    }                                                              //~@@@@I~
    //**********************************************************   //~@@@@I~
    //*input type:1:flick,-1:mini kbd touch;0:else                 //~@@@@I~
    //**********************************************************   //~@@@@I~
    private void onKey(int primaryCode,int Pinputtype)             //~@@@@R~
    {                                                              //~@@@@I~
        if (Dump.Y) Dump.println("MsoftKbd:onKey keycode="+Integer.toHexString(primaryCode));//~@@@@R~
//        if (isWordSeparator(primaryCode)) {                      //~@@@@R~
//            // Handle separator                                  //~@@@@R~
//            if (mComposing.length() > 0) {                       //~@@@@R~
//                commitTyped(getCurrentInputConnection());        //~@@@@R~
//            }                                                    //~@@@@R~
//            sendKey(primaryCode);                                //~@@@@R~
//            updateShiftKeyState(getCurrentInputEditorInfo());    //~@@@@R~
//        }                                                        //~@@@@R~
//          else if (primaryCode == Keyboard.KEYCODE_DELETE) {     //~@@@@I~
//            handleBackspace();                                   //~@@@@R~
//        } else if (primaryCode == Keyboard.KEYCODE_SHIFT) {      //~@@@@R~
//            handleShift();                                       //~@@@@R~
//        } else if (primaryCode == Keyboard.KEYCODE_CANCEL) {     //~@@@@R~
//            handleClose();                                       //~@@@@R~
//            return;                                              //~@@@@R~
//        } else if (primaryCode == LatinKeyboardView.KEYCODE_OPTIONS) {//~@@@@R~
//            // Show a menu or somethin'                          //~@@@@R~
//        }//fixme                                                 //~@@@@R~
//         else if (primaryCode == Keyboard.KEYCODE_MODE_CHANGE    //~@@@@R~
//                && mInputView != null) {                         //~@@@@R~
//            Keyboard current = mInputView.getKeyboard();         //~@@@@R~
//            if (current == mSymbolsKeyboard || current == mSymbolsShiftedKeyboard) {//~@@@@R~
//                current = mQwertyKeyboard;                       //~@@@@R~
//            } else {                                             //~@@@@R~
//                current = mSymbolsKeyboard;                      //~@@@@R~
//            }                                                    //~@@@@R~
//            mInputView.setKeyboard(current);                     //~@@@@R~
//            if (current == mSymbolsKeyboard) {                   //~@@@@R~
//                current.setShifted(false);                       //~@@@@R~
//            }                                                    //~@@@@R~
//        } else {                                                 //~@@@@R~
//            handleCharacter(primaryCode, keyCodes);              //~@@@@R~
//        }                                                        //~@@@@R~
		int keycode=primaryCode;                                   //~@@@@I~
        switch (primaryCode)                                       //~@@@@R~
        {                                                          //~@@@@M~
        case Keyboard.KEYCODE_SHIFT:                               //~@@@@I~
            handleShift(Pinputtype);                               //~@@@@R~
            return;                                                //~@@@@I~
        case Keyboard.KEYCODE_DELETE:                              //~@@@@I~
            keycode=AxeKbdKey.KEYCODE_BACKSPACE;      //Gdk_BackSpace;//~@@@@I~
            break;                                                 //~@@@@I~
        case AxeKbdKey.KEYCODE_SHIFTR:                             //~@@@@I~
            handleShiftR();                                        //~@@@@I~
            return;                                                //~@@@@I~
        case AxeKbdKey.KEYCODE_CAPS:                               //~@@@@R~
            handleCaps();                                          //~@@@@R~
            return;                                                //~@@@@I~
        case AxeKbdKey.KEYCODE_SHIFTF:                                 //~vaawI~
            handleShiftF();                                        //~vaawI~
            return;                                                //~vaawI~
        case AxeKbdKey.KEYCODE_SHORTCUT:                           //~@@@@I~
            handleShortcut();                                      //~@@@@I~
            return;                                                //~@@@@I~
        case AxeKbd.KEYCODE_ALT:                                   //~@@@@R~
            handleAlt(Pinputtype);                                           //~@@@@R~
            return;                                                //~@@@@I~
        case AxeKbdKey.KEYCODE_ALTGR:                                 //~@@@@R~
            handleAltGr();                                         //~@@@@I~
            return;                                                //~@@@@I~
        case AxeKbdKey.KEYCODE_ALTGRS:                                //~@@@@R~
            handleAltGrS();                                        //~@@@@I~
            return;                                                //~@@@@I~
        case AxeKbdKey.KEYCODE_CTRL:                                  //~@@@@R~
            handleCtrl();                                          //~@@@@I~
            return;                                                //~@@@@I~
        case AxeKbdKey.KEYCODE_KBD:                                //~@@@@R~
        	handleKbd(Pinputtype);                                           //~@@@@I~
            return;                                                //~@@@@I~
        case AxeKbdKey.KEYCODE_KBDPOPUP:                           //~@@@@I~
            mInputView.setPopupMode();//toggle                     //~@@@@R~
            return;                                                //~@@@@I~
//        case AxeKbdKey.KEYCODE_KBDNOPOP:                         //~@@@@R~
//            mInputView.setPopupMode(false);                      //~@@@@R~
//            return;                                              //~@@@@R~
        }                                                          //~@@@@I~
		sendKey(keycode);                                          //~@@@@R~
    }
    @Override	//Listener                                         //~@@@@I~
    public void onText(CharSequence text) {                        //~@@@@R~
//        InputConnection ic = getCurrentInputConnection();        //~@@@@R~
//        if (ic == null) return;                                  //~@@@@R~
//        ic.beginBatchEdit();                                     //~@@@@R~
//        if (mComposing.length() > 0) {                           //~@@@@R~
//            commitTyped(ic);                                     //~@@@@R~
//        }                                                        //~@@@@R~
//        ic.commitText(text, 0);                                  //~@@@@R~
//        ic.endBatchEdit();                                       //~@@@@R~
//        updateShiftKeyState(getCurrentInputEditorInfo());        //~@@@@R~
    }                                                              //~@@@@R~

    
//    private void handleBackspace() {                             //~@@@@R~
//        final int length = mComposing.length();                  //~@@@@R~
//        if (length > 1) {                                        //~@@@@R~
//            mComposing.delete(length - 1, length);               //~@@@@R~
//            getCurrentInputConnection().setComposingText(mComposing, 1);//~@@@@R~
//            updateCandidates();                                  //~@@@@R~
//        } else if (length > 0) {                                 //~@@@@R~
//            mComposing.setLength(0);                             //~@@@@R~
//            getCurrentInputConnection().commitText("", 0);       //~@@@@R~
//            updateCandidates();                                  //~@@@@R~
//        } else {                                                 //~@@@@R~
//            keyDownUp(KeyEvent.KEYCODE_DEL);                     //~@@@@R~
//        }                                                        //~@@@@R~
//        updateShiftKeyState(getCurrentInputEditorInfo());        //~@@@@R~
//    }                                                            //~@@@@R~

//    private void handleShift() {                                 //~@@@@R~
//        if (mInputView == null) {                                //~@@@@R~
//            return;                                              //~@@@@R~
//        }                                                        //~@@@@R~
//                                                                 //~@@@@R~
//        Keyboard currentKeyboard = mInputView.getKeyboard();     //~@@@@R~
//        if (mQwertyKeyboard == currentKeyboard) {                //~@@@@R~
//            // Alphabet keyboard                                 //~@@@@R~
//            checkToggleCapsLock();                               //~@@@@R~
//            mInputView.setShifted(mCapsLock || !mInputView.isShifted());//~@@@@R~
//        } else if (currentKeyboard == mSymbolsKeyboard) {        //~@@@@R~
//            mSymbolsKeyboard.setShifted(true);                   //~@@@@R~
//            mInputView.setKeyboard(mSymbolsShiftedKeyboard);     //~@@@@R~
//            mSymbolsShiftedKeyboard.setShifted(true);            //~@@@@R~
//        } else if (currentKeyboard == mSymbolsShiftedKeyboard) { //~@@@@R~
//            mSymbolsShiftedKeyboard.setShifted(false);           //~@@@@R~
//            mInputView.setKeyboard(mSymbolsKeyboard);            //~@@@@R~
//            mSymbolsKeyboard.setShifted(false);                  //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~
    private void handleShift(int Pinputtype) {                                   //~@@@@I~
        if (Dump.Y) Dump.println("AxeSoftKbd:handleShift isShifted="+mInputView.isShifted());//~@@@@R~
        if (mInputView == null) {                                  //~@@@@I~
            return;                                                //~@@@@I~
        }                                                          //~@@@@I~
//draw label at onPress momentally                                 //~@@@@I~
//        mInputView.updateShiftLabel(true/*press*/);              //~@@@@R~
//      checkToggleCapsLock();                                     //~@@@@I~
//      mInputView.setShifted(mCapsLock || !mInputView.isShifted());//~@@@@I~
//        if (!mInputView.setCapsed(false) && !mInputView.setShiftRed(false))     //reset caps//~@@@@R~
        if (Pinputtype!=INPUT_SOFTKBD) 	//flick or minikbd         //~@@@@I~
        {                                                          //~@@@@I~
			mInputView.setCapsed(false);                           //~@@@@R~
	        mInputView.setShiftRed(false);     //reset SHIFTR, shift sticky on if Caps//~@@@@I~
	        mInputView.setShiftFed(false);     //reset SHIFTR, shift sticky on if Caps//~vaawI~
	        mInputView.setShifted(!mInputView.isShifted());	//caps not changed//~@@@@I~
        }                                                          //~@@@@I~
        else                                                       //~@@@@M~
        if (!mInputView.setShiftRed(false))     //reset SHIFTR, shift sticky on if Caps//~@@@@M~
        {                                                          //~@@@@I~
	        mInputView.setShifted(!mInputView.isShifted());	//caps not changed//~@@@@M~
        }                                                          //~@@@@I~
//keep ShiftF for S+F1
    }                                                              //~@@@@I~
    private void handleShiftR() {                                  //~@@@@I~
        if (Dump.Y) Dump.println("AxeSoftKbd:handleShiftR isShifted="+mInputView.isShiftRed());//~@@@@R~
        if (mInputView == null) {                                  //~@@@@I~
            return;                                                //~@@@@I~
        }                                                          //~@@@@I~
        mInputView.setShiftRed(!mInputView.isShiftRed());          //~@@@@R~
    }                                                              //~@@@@I~
    private void handleShiftF() {                                  //~vaawI~
        if (Dump.Y) Dump.println("AxeSoftKbd:handleShiftF isShifted="+mInputView.isShiftFed());//~vaawI~
        if (mInputView == null) {                                  //~vaawI~
            return;                                                //~vaawI~
        }                                                          //~vaawI~
        mInputView.setShiftFed(!mInputView.isShiftFed());          //~vaawI~
    }                                                              //~vaawI~
    private void handleCaps() {                                    //~@@@@I~
        if (Dump.Y) Dump.println("AxeSoftKbd:handleCaps isCapsed="+mInputView.isCapsed());//~@@@@I~
        if (mInputView == null) {                                  //~@@@@I~
            return;                                                //~@@@@I~
        }                                                          //~@@@@I~
        mInputView.setCapsed(!mInputView.isCapsed());              //~@@@@I~
    }                                                              //~@@@@I~
    private void handleShortcut() {                                //~@@@@I~
        if (Dump.Y) Dump.println("AxeSoftKbd:handleShortcut isShortcut="+mInputView.isShortcut());//~@@@@I~
        if (mInputView == null) {                                  //~@@@@I~
            return;                                                //~@@@@I~
        }                                                          //~@@@@I~
        mInputView.setShortcut(!mInputView.isShortcut());          //~@@@@I~
    }                                                              //~@@@@I~
    
    private void handleAlt(int Pinputtype) {                                     //~@@@@R~
        if (Dump.Y) Dump.println("handleAlt isAlt="+mInputView.isAlted());//~@@@@R~
        if (mInputView == null) {                                  //~@@@@I~
            return;                                                //~@@@@I~
        }                                                          //~@@@@I~
        if (Pinputtype!=INPUT_SOFTKBD) 	//flick or minikbd         //~@@@@I~
        {                                                          //~@@@@I~
	        mInputView.setAltGred(false);     //did not reset Altgr//~@@@@I~
        	mInputView.setAltGrSed(false);     //did not re

        	//~@@@@I~
        }                                                          //~@@@@I~
        else                                                       //~@@@@I~
        if (!mInputView.setAltGred(false)     //did not reset Altgr//~@@@@I~
        &&  !mInputView.setAltGrSed(false))     //did not reset AltgrS//~@@@@I~
        {                                                          //~@@@@I~
	        mInputView.setAlted(!mInputView.isAlted());            //~@@@@I~
        }                                                          //~@@@@I~
    }                                                              //~@@@@I~
    private void handleAltGr() {                                   //~@@@@I~
        if (Dump.Y) Dump.println("handleAltGr isAltGr="+mInputView.isAltGred());//~@@@@R~
        if (mInputView == null) {                                  //~@@@@I~
            return;                                                //~@@@@I~
        }                                                          //~@@@@I~
        mInputView.setAltGred(!mInputView.isAltGred());            //~@@@@I~
    }                                                              //~@@@@I~
    private void handleAltGrS() {                                  //~@@@@I~
        if (Dump.Y) Dump.println("handleShift isAltGrS="+mInputView.isAltGrSed());//~@@@@I~
        if (mInputView == null) {                                  //~@@@@I~
            return;                                                //~@@@@I~
        }                                                          //~@@@@I~
        mInputView.setAltGrSed(!mInputView.isAltGrSed());          //~@@@@R~
    }                                                              //~@@@@I~
    private void handleCtrl() {                                    //~@@@@I~
        if (Dump.Y) Dump.println("handleCtrl isCtrl="+mInputView.isCtrled());//~@@@@I~
        if (mInputView == null) {                                  //~@@@@I~
            return;                                                //~@@@@I~
        }                                                          //~@@@@I~
        mInputView.setCtrled(!mInputView.isCtrled());            //~@@@@I~
    }                                                              //~@@@@I~
    private void handleKbd(int Pinputtype)                                       //~@@@@I~
    {                                                              //~@@@@I~
        if (mInputView.popupMode && Pinputtype==INPUT_SOFTKBD)     //~@@@@I~
            mInputView.setPopupMode(false);                        //~@@@@I~
        else                    //swipe or minikbd                 //~@@@@I~
        if (mInputView.isShortcut())                               //~@@@@I~
            handleShortcut();   //reset shortcut;                  //~@@@@I~
        else                                                       //~@@@@I~
            handleModeChange(); //start IME                        //~@@@@I~
    }                                                              //~@@@@I~
    private void handleModeChange() {                              //~@@@@I~
        if (Dump.Y) Dump.println("handleModeChange ModeChange");   //~@@@@R~
        kbdDialog.startIME();                                      //~@@@@R~
//        kbdDialog.showKbdDelayed();                              //~@@@@R~
    }                                                              //~@@@@I~
                                                                   //~@@@@I~
//    private void handleCharacter(int primaryCode, int[] keyCodes) {//~@@@@R~
//        if (Dump.Y) Dump.println("handleChar keycode="+Integer.toHexString(primaryCode));//~@@@@R~
//        if (isInputViewShown()) {                                //~@@@@R~
//            if (mInputView.isShifted()) {                        //~@@@@R~
//                primaryCode = Character.toUpperCase(primaryCode);//~@@@@R~
//            }                                                    //~@@@@R~
//        }                                                        //~@@@@R~
//        if (isAlphabet(primaryCode) && mPredictionOn) {          //~@@@@R~
//            mComposing.append((char) primaryCode);               //~@@@@R~
//            getCurrentInputConnection().setComposingText(mComposing, 1);//~@@@@R~
//            updateShiftKeyState(getCurrentInputEditorInfo());    //~@@@@R~
//            updateCandidates();                                  //~@@@@R~
//        } else {                                                 //~@@@@R~
//            getCurrentInputConnection().commitText(              //~@@@@R~
//                    String.valueOf((char) primaryCode), 1);      //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~

//    private void handleClose() {                                 //~@@@@R~
//        commitTyped(getCurrentInputConnection());                //~@@@@R~
//        requestHideSelf(0);                                      //~@@@@R~
//        mInputView.closing();                                    //~@@@@R~
//    }                                                            //~@@@@R~

//    private void checkToggleCapsLock() {                         //~@@@@R~
//        long now = System.currentTimeMillis();                   //~@@@@R~
//        if (mLastShiftTime + 800 > now) {                        //~@@@@R~
//            mCapsLock = !mCapsLock;                              //~@@@@R~
//            mLastShiftTime = 0;                                  //~@@@@R~
//        } else {                                                 //~@@@@R~
//            mLastShiftTime = now;                                //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~
//                                                                 //~@@@@R~
//    private String getWordSeparators() {                         //~@@@@R~
//        return mWordSeparators;                                  //~@@@@R~
//    }                                                            //~@@@@R~
    
//    public boolean isWordSeparator(int code) {                   //~@@@@R~
//        String separators = getWordSeparators();                 //~@@@@R~
//        return separators.contains(String.valueOf((char)code));  //~@@@@R~
//    }                                                            //~@@@@R~

//    public void pickDefaultCandidate() {                         //~@@@@R~
//        pickSuggestionManually(0);                               //~@@@@R~
//    }                                                            //~@@@@R~
//*****************************************************************//~@@@@I~
//*from InputMethodService                                         //~@@@@I~
//*****************************************************************//~@@@@I~
    public int getMaxWidth() {                                     //~@@@@I~
//        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);//~@@@@R~
//          WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);//+vaybR~
//        if (Dump.Y) Dump.println("getMAxWidth:"+wm.getDefaultDisplay().getWidth());//+vaybR~
//        return wm.getDefaultDisplay().getWidth();                //+vaybR~
		Point p=AxeView.getDisplayRegion();                        //+vaybI~
        if (Dump.Y) Dump.println("getMaxWidth:"+p.x);              //+vaybI~
        return p.x;                                                //+vaybI~
    }                                                              //~@@@@I~
//*****************************************************************//~@@@@I~
//*meta flag(High)                                                 //~@@@@R~
//*****************************************************************//~@@@@I~
    public int getMetaStatus()                                     //~@@@@R~
    {                                                              //~@@@@I~
    	int meta=0;                                                //~@@@@I~
        if (mInputView.isShifted())                                //~@@@@I~
        	meta|=META_SHIFT;                                      //~@@@@I~
        if (mInputView.isCtrled())                                 //~@@@@I~
        	meta|=META_CTL;                                        //~@@@@I~
        if (mInputView.isAlted())                                  //~@@@@I~
        	meta|=META_ALT;                                        //~@@@@I~
    	return meta;                                               //~@@@@I~
    }                                                              //~@@@@I~
//*********************                                            //~@@@@I~
    public boolean isShortcut()                                    //~@@@@I~
    {                                                              //~@@@@I~
    	return mInputView.isShortcut();                            //~@@@@I~
    }                                                              //~@@@@I~
    public boolean setShortcut(boolean Pstatus)                    //~@@@@I~
    {                                                              //~@@@@I~
    	return mInputView.setShortcut(Pstatus);                    //~@@@@I~
    }                                                              //~@@@@I~
//*********************                                            //~@@@@I~
//*for Kbdlayout update                                            //~@@@@I~
//*********************                                            //~@@@@I~
//    private String[][] getKbdLayoutTbl()                         //~@@@@R~
//    {                                                            //~@@@@R~
//        if (kbdLayoutTbl==null)                                  //~@@@@R~
//            kbdLayoutTbl=initKbdLayoutTbl()                      //~@@@@R~
//        return kbdLayoutTbl;                                     //~@@@@R~
//    }                                                            //~@@@@R~
    public int getKbdSize()                                       //~@@@@I~
    {                                                              //~@@@@I~
    	AxeKbdView kv=mInputView;                                  //~@@@@I~
        return kv.mKeys.length;                                    //~@@@@I~
    }                                                              //~@@@@I~
    public Key[] getKbd()                                        //~@@@@I~
    {                                                              //~@@@@I~
    	AxeKbdView kv=mInputView;                                  //~@@@@I~
        return kv.mKeys;                                           //~@@@@I~
    }                                                              //~@@@@I~
    public int[][] getKbdLayoutCodeTbl()                          //~@@@@R~
    {                                                              //~@@@@I~
    	if (kbdLayoutCodeTbl==null)                                //~@@@@I~
        {                                                          //~@@@@I~
        	kbdLayoutCodeTbl=initKbdLayoutCodeTbl();                //~@@@@I~
//            saveDefault();                                       //~@@@@R~
        }                                                          //~@@@@I~
        return kbdLayoutCodeTbl;                                   //~@@@@R~
    }                                                              //~@@@@I~
//    private String[][] initKbdLayoutTbl()                        //~@@@@R~
//    {                                                            //~@@@@R~
//        AxeKbdView kv=mInputView;                                //~@@@@R~
//        int keyctr=kv.mKeys.length;                              //~@@@@R~
//        outtb=new String[keyctr][MAXSWIPE+1];                    //~@@@@R~
//        String old;                                              //~@@@@R~
//        for (int ii=0;ii<keyctr;ii++)                            //~@@@@R~
//        {                                                        //~@@@@R~
//            outtb[ii][0]=KEYID_FIXED;    //not updatable key id  //~@@@@R~
//            Key key=mKeys[ii];                                   //~@@@@R~
//            CharSequence popup=key.popupCharacters; //translated //~@@@@R~
//            if (popup==null)                                     //~@@@@R~
//                continue;                                        //~@@@@R~
//            int code=kv.swipeKeyCodeTbl[ii][0];                  //~@@@@R~
//            if (code<0)   //control key such as modifier         //~@@@@R~
//                continue;                                        //~@@@@R~
//            for (jj=0;jj<=MAXSWIPE;jj++)                         //~@@@@R~
//            {                                                    //~@@@@R~
//                String label=kv.swipeExtKeyNameTbl[ii][jj];      //~@@@@R~
//                if (label==null);                                //~@@@@R~
//                {                                                //~@@@@R~
//                    code=swipeKeyCodeTbl[ii][jj];                //~@@@@R~
//                    if (code==0)                                 //~@@@@R~
//                        label=" ";                               //~@@@@R~
//                    else                                         //~@@@@R~
//                    if (code<0x80)                               //~@@@@R~
//                        label=String.valueOf((char)code);        //~@@@@R~
//                    else                                         //~@@@@R~
//                        label=KEYID_UNICODE+Integer.toHexString(code);//~@@@@R~
//                }                                                //~@@@@R~
//                outtb[ii][jj]=label;                             //~@@@@R~
//                if (Dump.Y) Dump.println("AxeSoftKbd kbdlayout list for ("+ii+","+jj+"),code="+Integer.toHexString(code)+",label="label);//~@@@@R~
//            }                                                    //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~
//*******************************************************          //~@@@@I~
//*from AxeLstKbdLayout                                            //~@@@@I~
//*setup list for update keyboard key assignment                   //~@@@@I~
//*******************************************************          //~@@@@I~
    private int[][] initKbdLayoutCodeTbl()                         //~@@@@I~
    {                                                              //~@@@@I~
        AxeKbdView kv=mInputView;                                  //~@@@@I~
        int keyctr=kv.mKeys.length;                                //~@@@@I~
        int[][] outtb=new int[keyctr][AxeKbdView.MAXSWIPE+1];                         //~@@@@I~
        for (int ii=0;ii<keyctr;ii++)                              //~@@@@I~
        {                                                          //~@@@@I~
            outtb[ii][0]=KEYID_FIXED;    //not updatable key id    //~@@@@I~
            Key key=kv.mKeys[ii];                                     //~@@@@I~
            CharSequence popup=key.popupCharacters; //translated   //~@@@@I~
            if (popup==null)                                       //~@@@@I~
                continue;                                          //~@@@@I~
//            int code=kv.swipeKeycodeTbl[ii][0];                  //~@@@@R~
            int code=kv.flickKeyTbl[ii][0].code;                      //~@@@@I~
            if (code<0)   //control key such as modifier           //~@@@@I~
                continue;                                          //~@@@@I~
            for (int jj=0;jj<=AxeKbdView.MAXSWIPE;jj++)                           //~@@@@I~
            {                                                      //~@@@@I~
//                String label=kv.swipeExtkeyNameTbl[ii][jj];      //~@@@@R~
                String label=kv.flickKeyTbl[ii][jj].name;          //~@@@@I~
//                int outcode=kv.swipeKeycodeTbl[ii][jj];          //~@@@@R~
                int outcode=kv.flickKeyTbl[ii][jj].code;           //~@@@@I~
                if (label!=null)                                //~@@@@I~
                {                                                  //~@@@@I~
                	int idx=AxeKbdKey.getSpinnerIndex(outcode);       //~@@@@I~
                    if (idx>0)                                     //~@@@@I~
                    	outcode=idx|KEYID_EXTKEY;                  //~@@@@I~
                    else                                           //~@@@@I~
                    	outcode=0;                                 //~@@@@I~
                }                                                  //~@@@@I~
                outtb[ii][jj]=outcode;                             //~@@@@I~
                if (Dump.Y) Dump.println("AxeSoftKbd kbdlayout list for ("+ii+","+jj+"),code="+Integer.toHexString(code)+",out="+Integer.toHexString(outcode));//~@@@@R~
            }                                                      //~@@@@I~
        }
        return outtb;//~@@@@I~
    }                                                              //~@@@@I~
////*******************************************************        //~@@@@R~
//    private void saveDefault()                                   //~@@@@R~
//    {                                                            //~@@@@R~
//        int keyctr=kv.mKeys.length;                              //~@@@@R~
//        int[][] outtb=new int[keyctr][AxeKbdView.MAXSWIPE+1];    //~@@@@R~
//        Util.copyArray(KbdLayoutCodeTbl,outtb);                  //~@@@@R~
//        kbdLayoutCodeTblDefault=outtb;                           //~@@@@R~
//    }                                                            //~@@@@R~
////*******************************************************        //~@@@@R~
//    public void resetToDefault()                                 //~@@@@R~
//    {                                                            //~@@@@R~
//        Util.copyArray(KbdLayoutCodeTblDefault,KbdLayoutCodeTbl);//~@@@@R~
//    }                                                            //~@@@@R~
//*************************************************************    //~@@@@R~
//*if preference exist or AxeKbdLayout dialog changed layout       //~@@@@R~
//*************************************************************    //~@@@@R~
	public void updateKeys(int[][] Pnewcodes)                      //~@@@@I~
    {                                                              //~@@@@I~
        AxeKbdView kv=mInputView;                                  //~@@@@I~
        Key[] keys=kv.mKeys;                                       //~@@@@I~
//        int[][] tgttbl=kv.swipeKeycodeTbl;                       //~@@@@R~
//        String[][] labeltbl=kv.swipeExtkeyNameTbl;               //~@@@@R~
        AxeKbdKey.FlickKey[][] tgttbl=kv.flickKeyTbl;                 //~@@@@I~
        int sz=keys.length;//~@@@@I~
        for (int ii=0;ii<sz;ii++)                                  //~@@@@I~
        {                                                          //~@@@@I~
              Key key=keys[ii];
        	for (int jj=0;jj<=AxeKbdView.MAXSWIPE;jj++)            //~@@@@I~
            {                                                      //~@@@@I~
                int newcode=Pnewcodes[ii][jj];                     //~@@@@I~
                int oldcode=kbdLayoutCodeTbl[ii][jj];              //~@@@@I~
                boolean extkeysw=false;                            //~@@@@I~
//                boolean repeatable=false;                        //~@@@@R~
                if (Dump.Y) Dump.println("AxeSoftKbd updateKeys ii="+ii+",jj="+jj+",primarycode="+Integer.toHexString(key.codes[0])+",old="+Integer.toHexString(oldcode)+",new="+Integer.toHexString(newcode));//~@@@@I~
                if (newcode>0 && newcode!=oldcode)                 //~@@@@I~
                {                                                  //~@@@@I~
	                kbdLayoutCodeTbl[ii][jj]=newcode;              //~@@@@I~
                    int keycode=newcode;                           //~@@@@I~
                    String label=null;                             //~@@@@I~
                    if ((newcode & KEYID_EXTKEY)!=0)               //~@@@@I~
                    {                                              //~@@@@I~
                        extkeysw=true;                             //~@@@@I~
                        int idx=newcode & ~KEYID_EXTKEY;         //~@@@@I~
                        keycode=AxeKbdKey.SspinnerCode[idx];  //~@@@@I~
                        label=AxeKbdKey.SspinnerData[idx];   //~@@@@I~
//                        repeatable=AxeKbdKey.SspinnerRepeatable[idx];//~@@@@R~
                    }                                              //~@@@@I~
//                    tgttbl[ii][jj]=keycode;                      //~@@@@R~
                    tgttbl[ii][jj].code=keycode;                   //~@@@@I~
//                    labeltbl[ii][jj]=label;                      //~@@@@R~
                    tgttbl[ii][jj].name=label;                     //~@@@@R~
//                    tgttbl[ii][jj].repeatable=repeatable;        //~@@@@R~
                    if (Dump.Y) Dump.println("AxeSoftKbd updateKeys new keycode="+Integer.toHexString(keycode)+",old="+tgttbl[ii][jj]+",label="+label);//~@@@@I~
                    if (jj==0)                                     //~@@@@I~
                    {                                              //~@@@@I~
                    	key.codes[0]=keycode;                      //~@@@@I~
//                        key.repeatable=AxeKbdKey.isRepeatableKey(keycode);//~@@@@R~
                    	label=String.valueOf((char)keycode);       //~@@@@I~
	                    if (Dump.Y) Dump.println("AxeSoftKbd updateKeys base changed old="+key.label+",new="+label);//~@@@@I~
                    	key.label=label;                           //~@@@@I~
                    }                                              //~@@@@I~
                }                                                  //~@@@@I~
            }                                                      //~@@@@I~
        }                                                          //~@@@@I~
    }                                                              //~@@@@I~
//*****************************************************************//~@@@@I~
//*avoid dismiss by pack key when popup minikbd up                 //~@@@@I~
//*****************************************************************//~@@@@I~
	public boolean dismissMiniKeyboardOnScreen()                   //~@@@@R~
    {                                                              //~@@@@I~
    	boolean rc;                                                    //~@@@@I~
		if (mInputView.mMiniKeyboardOnScreen)                      //~@@@@R~
        {                                                          //~@@@@I~
			mInputView.dismissPopupKeyboard();                                //~@@@@I~
            rc=true;                                               //~@@@@I~
        }                                                          //~@@@@I~
        else                                                       //~@@@@I~
        	rc=false;                                              //~@@@@I~
    	if (Dump.Y) Dump.println("AxeSoftKbd isMiniKeyboardOnScreen="+mInputView.mMiniKeyboardOnScreen+",rc="+rc);//~@@@@I~
        return rc;                                                 //~@@@@I~
    }                                                              //~@@@@I~
//***********************************************                  //~@@@@I~
//*AxeDlgArmOption: display flickkey toggled                       //~@@@@I~
//***********************************************                  //~@@@@I~
	public void invalidateAllKeys()                                //~@@@@I~
    {                                                              //~@@@@I~
    	mInputView.mKeyboardChanged=true;                          //~@@@@I~
    }                                                              //~@@@@I~
//***********************************************                  //~vaydI~
	private void resetModifier()                                   //~vaydI~
    {                                                              //~vaydI~
        if (Dump.Y) Dump.println("resetModifier");                 //~vaydI~
        if (mInputView == null) {                                  //~vaydI~
            return;                                                //~vaydI~
        }                                                          //~vaydI~
        if (mInputView.isShifted())                                //~vaydI~
        	mInputView.setShifted(false);                          //~vaydI~
        if (mInputView.isCtrled())                                 //~vaydI~
        	mInputView.setCtrled(false);                           //~vaydI~
	    if (mInputView.isAlted())                                  //~vaydI~
	        mInputView.setAlted(false);                            //~vaydI~
    }                                                              //~vaydI~
}
