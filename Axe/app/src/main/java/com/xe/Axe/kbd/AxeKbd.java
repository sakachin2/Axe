//*CID://+vaayR~:                             update#=  144;       //~vaavR~//~vaawR~//~vaayR~
//*********************************************************************//~vaasI~
//vaay:120106 (Axe)even when Caps on, "1" key generates "1"        //~vaayI~
//vaaw:120105 (Axe)add Fn modifier to set key label to Fn          //~vaawI~
//vaav:120104 (Axe:Bug of vaas)minikbd for gdk key dose not show flickkey//~vaavI~
//vaau:120104 (Axe)add \u00a0(NonBrakingSpace) and \u00ad(SoftHyphen:force Linefeed)//~vaauI~
//vaas:120102 (Axe) kbd position change;move SYMs to swipe-right(top-right)//~vaasI~
//*********************************************************************//~vaasI~
package com.xe.Axe.kbd;                                            //~@@@@R~
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

import com.xe.Axe.kbd.ims.Keyboard;                                //~@@@@R~
import com.xe.Axe.AxeG;
import com.xe.Axe.AxeKeyValue;
import com.xe.Axe.Dump;


public class AxeKbd extends Keyboard                               //~@@@@R~
{                                                                  //~@@@@I~
    public int mDefaultHeight;                                     //~@@@@R~
    public int mDefaultWidth;                                      //~@@@@R~
    private int mDefaultVerticalGap;                               //~@@@@R~
    private int mDefaultHorizontalGap;                             //~@@@@I~
    public boolean isMiniKeyboard;                                 //~@@@@R~
    private AxeKbdView myKbdView;                                  //~@@@@R~
    private int modifiedCode;	//output from getModifiedLabel     //~@@@@I~
    private boolean statusAltGr;                                           //~@@@@I~
    private boolean statusAltGrShift;                              //~@@@@I~
    private boolean statusShiftR;                                  //~@@@@I~
    private boolean statusShiftF;                                  //~vaawI~
    private boolean statusCaps;                                    //~@@@@I~
    private boolean statusShortcut;                                //~@@@@I~
    private boolean statusAlt;                                     //~@@@@I~
    private boolean statusCtrl;
    private boolean shiftAtPress;                                  //~@@@@I~
    //    private int mDisplayWidth;                                   //~@@@@R~
//    private List<Key> mKeys;                                     //~@@@@R~
    
    public AxeKbd(Context context, int xmlLayoutResId) {           //~@@@@R~
        super(context, xmlLayoutResId);
        if (Dump.Y) Dump.println("LatinKeyboard minwidth="+getMinWidth());//~@@@@I~
        if (Dump.Y) Dump.println("LatinKeyboard keywidth="+getKeyWidth());//~@@@@I~
        if (Dump.Y) Dump.println("LatinKeyboard keyHeight="+getKeyHeight());//~@@@@I~
//        List<Keyboard.Key> keys=getKeys();                       //~@@@@R~
//        Keyboard.Key key1=keys.get(0);                           //~@@@@R~
//        if (Dump.Y) Dump.println("LatinKeyboard Key0 code0="+key1.codes[0]+",label="+key1.label.toString());//~@@@@R~
        mDefaultHeight=getKeyHeight();                             //~@@@@I~
        mDefaultWidth=getKeyWidth();                               //~@@@@I~
    }                                                              //~@@@@R~

//    public LatinKeyboard(Context context, int layoutTemplateResId,//~@@@@R~
//            CharSequence characters, int columns, int horizontalPadding) {//~@@@@R~
//        super(context, layoutTemplateResId, characters, columns, horizontalPadding);//~@@@@R~
//        if (Dump.Y) Dump.println("LatinKeyboard2minwidth="+getMinWidth());//~@@@@R~
//        List<Keyboard.Key> keys=getKeys();                       //~@@@@R~
//        Keyboard.Key key1=keys.get(0);                           //~@@@@R~
//        if (Dump.Y) Dump.println("LatinKeyboard Key0 code0="+key1.codes[0]+",label="+key1.label.toString());//~@@@@R~
//                                                                 //~@@@@R~
//    }                                                            //~@@@@R~
//***************************************************              //~@@@@I~
//*mini keyboard for the case popupchar is defined                 //~@@@@I~
//***************************************************              //~@@@@I~
    public AxeKbd(Context context, int layoutTemplateResId,        //~@@@@R~
            CharSequence characters,AxeKbdView Pkbdview,int PcurrentKeyindex,int columns, int horizontalPadding) {//~@@@@R~
        this(context, layoutTemplateResId);                        //~@@@@I~
                                                                   //~@@@@I~
        if (Dump.Y) Dump.println("AxeKbd popupchar="+characters);  //~@@@@R~
    	isMiniKeyboard=true;                                       //~@@@@I~
        myKbdView=Pkbdview;	//for getModifiedLabel                 //~@@@@I~
//        ArrayList<String> extkeynamelist=Pkbdview.extkeyNameList[PcurrentKeyindex];//~@@@@R~
//        String[] extkeynamelist=Pkbdview.swipeExtkeyNameTbl[PcurrentKeyindex];//~@@@@R~
//        int [] popupkeycode=Pkbdview.swipeKeycodeTbl[PcurrentKeyindex];//~@@@@R~
        mDefaultHeight=getKeyHeight();                             //~@@@@I~
        mDefaultWidth=getKeyWidth();                               //~@@@@I~
        mDefaultVerticalGap=getVerticalGap();                      //~@@@@R~
        mDefaultHorizontalGap=getHorizontalGap();                  //~@@@@I~
//        DisplayMetrics dm = context.getResources().getDisplayMetrics();//~@@@@R~
//        mDisplayWidth = dm.widthPixels;                          //~@@@@R~
//        mDisplayHeight = dm.heightPixels;                        //~@@@@R~
        if (Dump.Y) Dump.println("AxeKbd key default W="+mDefaultWidth+",H="+mDefaultHeight);//~@@@@R~
 //       int extkeyctr=0;                                           //~@@@@I~
                                                                   //~@@@@I~
        int x = 0;                                                 //~@@@@I~
        int y = 0;                                                 //~@@@@I~
        int column = 0;                                            //~@@@@I~
        mTotalWidth = 0;                                           //~@@@@I~
                                                                   //~@@@@I~
        Row row = new Row(this);                                   //~@@@@I~
        row.defaultHeight = mDefaultHeight;                        //~@@@@I~
        row.defaultWidth = mDefaultWidth;                          //~@@@@I~
        row.defaultHorizontalGap = mDefaultHorizontalGap;          //~@@@@I~
        row.verticalGap = mDefaultVerticalGap;                     //~@@@@I~
        row.rowEdgeFlags = EDGE_TOP | EDGE_BOTTOM;                 //~@@@@I~
        final int maxColumns = columns == -1 ? Integer.MAX_VALUE : columns;//~@@@@I~
        for (int i = 0; i < characters.length(); i++) {            //~@@@@I~
//            char c = characters.charAt(i);                         //~@@@@I~
            if (column >= maxColumns                               //~@@@@I~
                    || x + mDefaultWidth + horizontalPadding > mDisplayWidth) {//~@@@@I~
                x = 0;                                             //~@@@@I~
                y += mDefaultVerticalGap + mDefaultHeight;         //~@@@@I~
                column = 0;                                        //~@@@@I~
            }                                                      //~@@@@I~
            final Key key = new Key(row);                          //~@@@@I~
            key.x = x;                                             //~@@@@I~
            key.y = y;                                             //~@@@@I~
//            String extkeyname=extkeynamelist[extkeyctr++];       //~@@@@R~
////        if (c==Extkey.EXTKEYIDCHAR                             //~@@@@R~
////        &&  extkeynamelist!=null                               //~@@@@R~
////        &&  extkeyctr<extkeynamelist.size())                   //~@@@@R~
//        if (extkeyname!=null)                                    //~@@@@R~
//        {                                                        //~@@@@R~
////            key.label = extkeynamelist.get(extkeyctr++);       //~@@@@R~
//            key.label = extkeyname;                              //~@@@@R~
//            key.codes = new int[] { popupkeycode[column] };      //~@@@@R~
//            if (Dump.Y) Dump.println("AxeKbd extkey column="+column+" label="+extkeyname+" key="+ popupkeycode[column]);//~@@@@R~
//        }                                                        //~@@@@R~
//        else                                                     //~@@@@R~
//        {                                                        //~@@@@R~
//            key.label = String.valueOf(c);                       //~@@@@R~
//            key.label = String.valueOf(c);                       //~@@@@R~
//          if (c==' ')                                            //~@@@@R~
//            key.codes = new int[] { 0 };                         //~@@@@R~
//          else                                                   //~@@@@R~
//            key.codes = new int[] { c };                         //~@@@@R~
//        }                                                        //~@@@@R~
			key.label = getModifiedLabel(0/*primarycode*/," "/*oldlabel*/,characters,PcurrentKeyindex,i);//~@@@@R~
			key.codes = new int[] { modifiedCode };                //~@@@@I~
			                                                       //~@@@@I~
            column++;                                              //~@@@@I~
            x += key.width + key.gap;                              //~@@@@I~
            mKeys.add(key);                                        //~@@@@I~
            if (x > mTotalWidth) {                                 //~@@@@I~
                mTotalWidth = x;                                   //~@@@@I~
            }                                                      //~@@@@I~
        }                                                          //~@@@@I~
        mTotalHeight = y + mDefaultHeight;                         //~@@@@I~
    }                                                              //~@@@@I~
//**********************                                           //~@@@@I~
	public void setKeyboardView(AxeKbdView PkbdView)               //~@@@@R~
    {                                                              //~@@@@I~
    	myKbdView=PkbdView;                                        //~@@@@I~
    }                                                              //~@@@@I~
//**********************                                           //~@@@@I~
	private void resetAlt()                                        //~@@@@I~
    {                                                              //~@@@@I~
		statusAltGrShift=false;                                    //~@@@@I~
		statusAltGr=false;                                         //~@@@@I~
//        resetStickyAlt();                                        //~@@@@R~
        setSticky(myKbdView.modKeyIndex_Alt,false);                //~@@@@I~
    }                                                              //~@@@@I~
	private void resetShift()                                      //~@@@@I~
    {                                                              //~@@@@I~
	    super.setShifted(false);//Keyboard;                        //~@@@@R~
//        resetStickyShift();                                      //~@@@@R~
        setSticky(myKbdView.modKeyIndex_Shift,false);              //~@@@@I~
    }                                                              //~@@@@I~
	private void resetShiftR()                                     //~@@@@I~
    {                                                              //~@@@@I~
		statusShiftR=false;                                        //~@@@@I~
    }                                                              //~@@@@I~
	private void resetShiftF()                                     //~vaawI~
    {                                                              //~vaawI~
		statusShiftF=false;                                        //~vaawI~
    }                                                              //~vaawI~
	private void resetCaps()                                       //~@@@@I~
    {                                                              //~@@@@I~
		statusCaps=false;                                          //~@@@@I~
    }                                                              //~@@@@I~
	public void resetShift123()                                    //~vaawI~
    {                                                              //~vaawI~
		statusCaps=false;                                          //~vaawI~
		statusShiftR=false;                                        //~vaawI~
		statusShiftF=false;                                        //~vaawI~
    }                                                              //~vaawI~
	public void resetShortcut()                                   //~@@@@I~
    {                                                              //~@@@@I~
		statusShortcut=false;                                      //~@@@@I~
    }                                                              //~@@@@I~
//    private void setShiftOn()                                    //~@@@@R~
//    {                                                            //~@@@@R~
//        if (Dump.Y) Dump.println("AxeKbd:setShiftOn isShifted="+isShifted());//~@@@@R~
//        super.setShifted(true);                                  //~@@@@R~
//        setStickyShift();                                        //~@@@@R~
//        if (Dump.Y) Dump.println("AxeKbd:setShiftOn return isShifted="+isShifted());//~@@@@R~
//    }                                                            //~@@@@R~
	private void resetCtrl()                                       //~@@@@I~
    {                                                              //~@@@@I~
		statusCtrl=false;                                          //~@@@@I~
//        resetStickyCtrl();                                       //~@@@@R~
        setSticky(myKbdView.modKeyIndex_Ctrl,false);               //~@@@@I~
    }                                                              //~@@@@I~
//    private void resetStickyAlt()                                //~@@@@R~
//    {                                                            //~@@@@R~
//        if (myKbdView.modKeyIndex_Alt!=0)                        //~@@@@R~
//        {                                                        //~@@@@R~
//            Key key=myKbdView.mKeys[myKbdView.modKeyIndex_Alt];  //~@@@@R~
//            key.pressed=false;                                   //~@@@@R~
//            key.on=false;                                        //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~
//    private void resetStickyCtrl()                               //~@@@@R~
//    {                                                            //~@@@@R~
//        if (myKbdView.modKeyIndex_Ctrl!=0)                       //~@@@@R~
//        {                                                        //~@@@@R~
//            Key key=myKbdView.mKeys[myKbdView.modKeyIndex_Ctrl]; //~@@@@R~
//            key.pressed=false;                                   //~@@@@R~
//            key.on=false;                                        //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~
//    private void setStickyShift(boolean Pshift)                  //~@@@@R~
//    {                                                            //~@@@@R~
//        if (myKbdView.modKeyIndex_Shift!=0)                      //~@@@@R~
//        {                                                        //~@@@@R~
//            Key key=myKbdView.mKeys[myKbdView.modKeyIndex_Shift];//~@@@@R~
//            if (Dump.Y) Dump.println("AxeKbd setStickyShift old="+key.on+",new="+Pshift);//~@@@@R~
//            key.on=Pshift;                                       //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~
//    private void resetStickyShift()                              //~@@@@R~
//    {                                                            //~@@@@R~
//        setStickyShift(false);                                   //~@@@@R~
//    }                                                            //~@@@@R~
//    private void setStickyShift()                                //~@@@@R~
//    {                                                            //~@@@@R~
//        if (myKbdView.modKeyIndex_Shift!=0)                      //~@@@@R~
//        {                                                        //~@@@@R~
//            Key key=myKbdView.mKeys[myKbdView.modKeyIndex_Shift];//~@@@@R~
//            key.pressed=false;                                   //~@@@@R~
//            key.on=true;                                         //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~
	private void setSticky(int Pidx,boolean Pstate)                //~@@@@I~
    {                                                              //~@@@@I~
    	if (Pidx>=0 && Pidx<myKbdView.mKeys.length)                //~@@@@I~
        {                                                          //~@@@@I~
	        Key key=myKbdView.mKeys[Pidx];                          //~@@@@I~
            key.on=Pstate;                                         //~@@@@I~
        }                                                          //~@@@@I~
    }                                                              //~@@@@I~
    //**********************************************************   //~@@@@I~
    @Override /* Override Keyboard*/                               //~@@@@I~
    public boolean setShifted(boolean shiftState) {                //~@@@@I~
    	boolean changed=super.setShifted(shiftState);              //~@@@@I~
        if (Dump.Y) Dump.println("AxeKbd setShifted super rc="+changed+",request="+shiftState);//~@@@@R~
//        if (!isMiniKeyboard)                                     //~@@@@R~
//            if (changed)                                         //~@@@@R~
//            {                                                    //~@@@@R~
//                resetAlt();                                      //~@@@@R~
//                resetCtrl();                                     //~@@@@R~
//                resetShiftR();                                   //~@@@@R~
//                updateLabel(AxeKbdKey.KEYCODE_SHIFT,shiftState); //~@@@@R~
//            }                                                    //~@@@@R~
//        setStickyShift(shiftState);                              //~@@@@R~
        if (changed)                                               //~@@@@I~
	        setSticky(myKbdView.modKeyIndex_Shift,shiftState);    //~@@@@I~
        return changed;                                            //~@@@@I~
    }                                                              //~@@@@I~
//*******************************************************************//~@@@@I~
// Shiftkey:temporary display Shifted character at press           //~@@@@I~
//          and back to nomarl character display at release        //~@@@@I~
//*******************************************************************//~@@@@I~
    public boolean updateShiftLabel(boolean Ppressed)              //~@@@@R~
    {                                                              //~@@@@I~
    	boolean rc=false;                                          //~@@@@I~
    //****************                                             //~@@@@I~
        if (Dump.Y) Dump.println("AxeKbd updateShiftedLabel press="+Ppressed);//~@@@@I~
        if (AxeG.displayFlickKey)                                 //~vaayI~
        	return false;   //no need to invalidateAll()           //~vaayI~
        if (isShiftFed()||isShiftRed())                            //~vaawI~
        	return rc;                                             //~vaawI~
    	if (Ppressed)                                              //~@@@@I~
        {                                                          //~@@@@I~
        	if (!isShifted())                                      //~@@@@I~
            {                                                      //~@@@@I~
                shiftAtPress=true;	                               //~@@@@I~
//                if (isCapsed())                                    //~@@@@I~//~vaayR~
//                    updateLabel(0,false/*shiftstatas*/);           //~@@@@I~//~vaayR~
//                else                                               //~@@@@I~//~vaayR~
					updateLabel(AxeKbdKey.KEYCODE_SHIFT,true/*shiftstatas*/);//~@@@@R~
                rc=true;                                           //~@@@@I~
            }                                                      //~@@@@I~
        }                                                          //~@@@@I~
    	else                                                       //~@@@@I~
            if (shiftAtPress)                                      //~@@@@I~
            {                                                      //~@@@@I~
            	shiftAtPress=false;                                //~@@@@I~
                if (isCapsed())                                    //~@@@@I~//~vaayR~
                    updateLabel(AxeKbdKey.KEYCODE_CAPS,true/*shiftstatas*/);//~@@@@I~//~vaayR~
                else                                               //~@@@@I~//~vaayR~
					updateLabel(0,false/*shiftstatas*/);           //~@@@@R~
                rc=true;                                           //~@@@@I~
            }                                                      //~@@@@I~
        return rc;                                                 //~@@@@I~
    }                                                              //~@@@@I~
//*********************                                            //~@@@@I~
    public boolean setShiftRed(boolean shiftState) {               //~@@@@M~
        if (Dump.Y) Dump.println("AxeKbd setShiftRred "+statusShiftR);//~@@@@R~
        boolean changed=shiftState!=isShiftRed();                  //~@@@@M~
        if (!isMiniKeyboard)                                       //~@@@@M~
	        if (changed)                                           //~@@@@M~
            {                                                      //~@@@@M~
//no shifted to avoid ,ex, Tab generate Shift+Tab                  //~@@@@I~
//                resetAlt();                                      //~@@@@R~
//                resetCtrl();                                     //~@@@@R~
//                if (shiftState)                                  //~@@@@R~
//                    setShiftOn();   //also set on Shift button right//~@@@@R~
//                else                                             //~@@@@R~
//                    resetShift();                                //~@@@@R~
                statusCaps=false;                                  //~vaawI~
                statusShiftR=shiftState;                           //~@@@@M~
                statusShiftF=false;                                //~vaawI~
				setShifted(false);                                 //~@@@@I~
    	    	updateLabel(AxeKbdKey.KEYCODE_SHIFTR,shiftState);  //~@@@@R~
            }                                                      //~@@@@M~
        return changed;                                            //~@@@@M~
    }                                                              //~@@@@M~
//*********************                                            //~vaawI~
    public boolean setShiftFed(boolean shiftState) {               //~vaawI~
        if (Dump.Y) Dump.println("AxeKbd setShiftFed "+statusShiftF);//~vaawI~
        boolean changed=shiftState!=isShiftFed();                  //~vaawI~
        if (!isMiniKeyboard)                                       //~vaawI~
	        if (changed)                                           //~vaawI~
            {                                                      //~vaawI~
                statusCaps=false;                                  //~vaawI~
                statusShiftR=false;                                //~vaawI~
                statusShiftF=shiftState;                           //~vaawI~
				setShifted(false);                                 //~vaawI~
    	    	updateLabel(AxeKbdKey.KEYCODE_SHIFTF,shiftState);  //~vaawI~
            }                                                      //~vaawI~
        return changed;                                            //~vaawI~
    }                                                              //~vaawI~
    public boolean setCapsed(boolean shiftState) {                 //~@@@@I~
        if (Dump.Y) Dump.println("AxeKbd setCapsed "+statusCaps);  //~@@@@I~
        boolean changed=shiftState!=isCapsed();                    //~@@@@I~
        if (!isMiniKeyboard)                                       //~@@@@I~
	        if (changed)                                           //~@@@@I~
            {                                                      //~@@@@I~
                statusCaps=shiftState;                             //~@@@@I~
                if (shiftState)                                    //~vaawI~
                {                                                  //~vaawI~
                	statusShiftR=false;                            //~vaawR~
                	statusShiftF=false;                            //~vaawR~
//					setShifted(true);                                 //~@@@@I~//~vaawR~//~vaayR~
                }                                                  //~vaawI~
    	    	updateLabel(AxeKbdKey.KEYCODE_CAPS,shiftState);    //~@@@@I~
            }                                                      //~@@@@I~
        return changed;                                            //~@@@@I~
    }                                                              //~@@@@I~
    public boolean setShortcut(boolean shiftState) {               //~@@@@I~
        if (Dump.Y) Dump.println("AxeKbd setShortcut "+statusCaps);//~@@@@I~
        boolean changed=shiftState!=isShortcut();                //~@@@@I~
        if (!isMiniKeyboard)                                       //~@@@@I~
	        if (changed)                                           //~@@@@I~
            {                                                      //~@@@@I~
                statusShortcut=shiftState;                         //~@@@@I~
            }                                                      //~@@@@I~
        return changed;                                            //~@@@@I~
    }                                                              //~@@@@I~
    public boolean setAlted(boolean shiftState) {                  //~@@@@I~
        if (Dump.Y) Dump.println("AxeKbd setAlted "+statusAlt);    //~@@@@R~
        boolean changed=shiftState!=isAlted();                     //~@@@@I~
        if (!isMiniKeyboard)                                       //~@@@@I~
        {                                                          //~vaayI~
            if (statusAltGrShift||statusAltGr)                     //~vaayI~
            {                                                      //~vaayI~
                statusAltGrShift=statusAltGr=false;                //~vaayI~
                updateLabel(AxeKbdKey.KEYCODE_ALT,shiftState);  //normal key//~vaayI~
                return false;                                      //~vaayI~
            }                                                      //~vaayI~
	        if (changed)                                           //~@@@@I~
            {                                                      //~@@@@I~
//                resetShift();                                    //~@@@@R~
//                resetCtrl();                                     //~@@@@R~
//                resetShiftR();                                   //~@@@@R~
                statusAlt=shiftState;                              //~@@@@I~
//                if (statusAlt)                                     //~@@@@I~//~vaayR~
//                    if (statusAltGrShift||statusAltGr)             //~@@@@I~//~vaayR~
//                    {                                              //~@@@@I~//~vaayR~
//                        statusAltGrShift=statusAltGr=false;        //~@@@@I~//~vaayR~
//                    }                                              //~@@@@I~//~vaayR~
//                updateLabel(AxeKbdKey.KEYCODE_ALT,shiftState);//~@@@@I~//~vaayR~
		        setSticky(myKbdView.modKeyIndex_Alt,shiftState);  //~@@@@I~
            }                                                      //~@@@@I~
        }                                                          //~vaayI~
        return changed;                                            //~@@@@I~
    }                                                              //~@@@@I~
    public boolean setAltGred(boolean shiftState) {                //~@@@@I~
        if (Dump.Y) Dump.println("AxeKbd setAltGred "+statusAltGr);//~@@@@R~
        boolean changed=shiftState!=isAltGred();                   //~@@@@I~
        if (!isMiniKeyboard)                                       //~@@@@I~
	        if (changed)                                           //~@@@@I~
            {                                                      //~@@@@I~
//                resetShift();                                    //~@@@@R~
//                resetCtrl();                                     //~@@@@R~
//                resetShiftR();                                   //~@@@@R~
//                statusAltGr=shiftState;                            //~@@@@I~//~vaayR~
//              if (statusAltGr)                                   //~@@@@I~//~vaayR~
                if (shiftState)                                    //~vaayI~
                {                                                  //~@@@@I~
//                    statusAltGrShift=false;                        //~@@@@I~//~vaayR~
//                    setAlted(false);                               //~@@@@I~//~vaayR~
	                resetAlt(); //reset stiky and altgr/altgrs flag//~vaayI~
	            	resetShift123();                               //~vaawI~
                }                                                  //~@@@@I~
                statusAltGr=shiftState;                            //~vaayI~
    	    	updateLabel(AxeKbdKey.KEYCODE_ALTGR,shiftState);   //~@@@@R~
            }                                                      //~@@@@I~
        return changed;                                            //~@@@@I~
    }                                                              //~@@@@I~
    public boolean setAltGrSed(boolean shiftState) {               //~@@@@M~
        if (Dump.Y) Dump.println("AxeKbd setAltGrSed "+statusAltGrShift);//~@@@@R~
        boolean changed=shiftState!=isAltGrSed();                  //~@@@@M~
        if (!isMiniKeyboard)                                       //~@@@@M~
	        if (changed)                                           //~@@@@M~
            {                                                      //~@@@@M~
//                resetShift();                                      //~@@@@I~//~vaayR~
//                resetCtrl();                                       //~@@@@I~//~vaayR~
//          	resetShiftR();                                     //~@@@@I~//~vaawR~
//                statusAltGrShift=shiftState;                       //~@@@@M~//~vaayR~
//              if (statusAltGrShift)                              //~@@@@I~//~vaayR~
                if (shiftState)                                    //~vaayI~
                {                                                  //~vaawI~
//              	statusAltGr=false;                             //~@@@@I~//~vaayR~
//                  setAlted(false);                               //~vaayR~
	                resetAlt(); //reset stiky and altgr/altgrs flag//~vaayI~
	            	resetShift123();                               //~vaawI~
                }                                                  //~vaawI~
                statusAltGrShift=shiftState;                       //~vaayI~
    	    	updateLabel(AxeKbdKey.KEYCODE_ALTGRS,shiftState);  //~@@@@R~
            }                                                      //~@@@@M~
        return changed;                                            //~@@@@M~
    }                                                              //~@@@@M~
    public boolean setCtrled(boolean shiftState) {                 //~@@@@I~
        if (Dump.Y) Dump.println("AxeKbd setCtrled "+statusCtrl);  //~@@@@R~
        boolean changed=shiftState!=isCtrled();                    //~@@@@I~
        if (!isMiniKeyboard)                                       //~@@@@I~
	        if (changed)                                           //~@@@@I~
            {                                                      //~@@@@I~
                statusCtrl=shiftState;                             //~@@@@I~
                setSticky(myKbdView.modKeyIndex_Ctrl,shiftState);  //~@@@@I~
            }                                                      //~@@@@I~
        return changed;                                            //~@@@@I~
    }                                                              //~@@@@I~
    //************************************************************ //+vaayI~
    //*update key label                                            //+vaayI~
    //************************************************************ //+vaayI~
    public void updateLabel(int Pmodifier,boolean Pstate)          //~@@@@I~
    {                                                              //~@@@@I~
        if (Dump.Y) Dump.println("AxeKbd updateLabel modifier="+Pmodifier+",state="+Pstate);//~@@@@R~
    	int idx=0;                                                  //~@@@@I~
        if (Pstate)                                                //~@@@@I~
        {                                                          //~@@@@I~
	        switch (Pmodifier)                                     //~@@@@R~
            {                                                      //~@@@@I~
            case AxeKbdKey.KEYCODE_SHIFT:   //from onPress                       //~@@@@R~
            	idx=1;			//shift normal,shift,altgr,shift+altgr//~@@@@R~
                break;                                             //~@@@@I~
            case AxeKbdKey.KEYCODE_CAPS:                           //~@@@@I~
//            	idx=1;			//label is same as shifted,code depends shift status//~@@@@I~//~vaayR~
              	idx=0;			//label is same as shifted,code depends shift status//~vaayI~
                break;                                             //~@@@@I~
            case AxeKbdKey.KEYCODE_ALTGR:                          //~@@@@R~
//          	idx=2;			//normal,shift,altgr,shift+altgr   //~@@@@M~//~vaasR~
            	idx=3;			//normal,shift,altgr,shift+altgr   //~vaasI~
                break;                                             //~@@@@I~
	        case AxeKbdKey.KEYCODE_ALTGRS:                         //~@@@@R~
//          	idx=3;			//normal,shift,altgr,shift+altgr   //~@@@@I~//~vaasR~
            	idx=4;			//normal,shift,altgr,shift+altgr   //~vaasI~
                break;                                             //~@@@@I~
	        case AxeKbdKey.KEYCODE_SHIFTR:                         //~@@@@R~
//            	idx=4;			//normal,shift,altgr,shift+altgr   //~@@@@I~//~vaasR~
              	idx=2;			//normal,shift,altgr,shift+altgr   //~vaasI~
                break;                                             //~@@@@I~
	        case AxeKbdKey.KEYCODE_SHIFTF:                         //~vaawI~
              	idx=AxeKbdView.SWIPE_SHIFTF+1;  //2                //~vaawR~
                break;                                             //~vaawI~
            case AxeKbdKey.KEYCODE_ALT:                            //~@@@@R~
            	idx=0;			//normal,shift,altgr,shift+altgr   //~@@@@I~
            }                                                      //~@@@@I~
        }                                                          //~@@@@I~
        Key[] keys = myKbdView.mKeys;                              //~@@@@I~
        int sz=keys.length;                                        //~@@@@I~
        for (int ii=0;ii<sz;ii++)                                  //~@@@@R~
        {                                                          //~@@@@I~
            CharSequence label;                                    //~@@@@I~
	        Key key=keys[ii];                                      //~@@@@R~
            if (key.icon!=null)                                    //~vaawI~
                key.label=null;     //reset by set ShiftF          //~vaawI~
            if (key.codes[0]==AxeKbdKey.KEYCODE_KBD) //by not modifier but popup selection(IM/Pop/NoPop)//~@@@@R~
                key.label=myKbdView.getPopupLabel();               //~@@@@R~
            else                                                   //~@@@@I~
		    if (Pmodifier==AxeKbdKey.KEYCODE_SHIFTF && Pstate)  //ShiftF on//~vaawM~
            	key.label=getModifiedLabelShiftF(key,ii,idx);      //~vaawM~
            else                                                   //~vaawM~
            if (key.label!=null)	//not icon                     //~@@@@I~
            {                                                      //~@@@@I~
		        if (Pmodifier==AxeKbdKey.KEYCODE_SHIFTR && Pstate)  //ShiftR on//~@@@@R~
                	label=getModifiedLabelShiftR(key.codes[0],key.label,ii,idx);//~@@@@R~
                else                                               //~@@@@I~
                {                                                  //~vaayI~
                	label=getModifiedLabel(key.codes[0],key.label,key.popupCharacters,ii,idx);//~@@@@R~
			        if (Pmodifier==AxeKbdKey.KEYCODE_CAPS && Pstate)//~vaayI~
                    {                                              //~vaayI~
		                if (modifiedCode>='a' && modifiedCode<='z')//~vaayI~
                        {                                          //~vaayI~
                        	modifiedCode+='A'-'a';                 //~vaayI~
                			label=String.valueOf((char)modifiedCode);//~vaayI~
                        }                                          //~vaayI~
                        else                                       //~vaayI~
		                if (modifiedCode>='A' && modifiedCode<='Z')//~vaayI~
                        {                                          //~vaayI~
                        	modifiedCode+='a'-'A';                 //~vaayI~
                			label=String.valueOf((char)modifiedCode);//~vaayI~
                        }                                          //~vaayI~
                    }                                              //~vaayI~
                }                                                  //~vaayI~
                if (modifiedCode==0)                               //~@@@@R~
                    key.label=" ";  //defied code with the modifier//~@@@@R~
                else                                               //~@@@@R~
                {                                                  //~vaauI~
                	String specialLabel=myKbdView.specialCodeLabel(modifiedCode);//~vaauI~
                    if (specialLabel!=null)                        //~vaauI~
                    	label=specialLabel;                        //~vaauI~
                    key.label=label;    //defined code with the modifier//~@@@@R~
                }                                                  //~vaauI~
            }                                                      //~@@@@I~
        }                                                          //~@@@@I~
        if (Dump.Y) Dump.println("AxeKbd updateLabel end modifier="+Pmodifier+",state="+Pstate);//~@@@@R~
    }                                                              //~@@@@I~
    //***************************************                      //~@@@@I~
    private CharSequence getModifiedLabel(int Pprimarycode,CharSequence Pkeylabel,CharSequence Ppopuplabel,int Pkeyindex,int Pswipeindex)//~@@@@R~
    {                                                              //~@@@@I~
    	int code;                                                  //~@@@@I~
        AxeKbdKey.FlickKey flickKey;                               //~vaasI~
//        char c;                                                    //~@@@@I~
	//*******************                                          //~vaasI~
        int swipeindex=Pswipeindex;                                //~@@@@I~
//      if (Dump.Y) Dump.println("AxeKbd getModifiedLabel keyindex="+Pkeyindex+",swipeindex="+Pswipeindex+",popupchar="+Ppopuplabel);//~@@@@R~
//        CharSequence label=myKbdView.swipeExtkeyNameTbl[Pkeyindex][swipeindex];//~@@@@R~
//      AxeKbdKey.FlickKey flickKey=myKbdView.flickKeyTbl[Pkeyindex][swipeindex];//~vaasI~
//      if (AxeKeyValue.isValidExtGDK(myKbdView.flickKeyTbl[Pkeyindex][0].code))//~vaasR~//~vaavR~
//      	flickKey=myKbdView.flickKeyTbl[Pkeyindex][0];          //~vaasI~//~vaavR~
//      else                                                       //~vaasI~//~vaavR~
        	flickKey=myKbdView.flickKeyTbl[Pkeyindex][swipeindex];//~@@@@R~//~vaasR~
        CharSequence label=flickKey.name;                          //~@@@@I~
        if (label==null)                                           //~@@@@I~
        {                                                          //~@@@@I~
//           c=0;                                                  //~@@@@R~
//            if (Pkeylabel!=null && Pkeylabel.length()>1          //~@@@@R~
//            && Pprimarycode!=AxeKbdKey.KEYCODE_ALT)              //~@@@@R~
//            {                                                    //~@@@@R~
//                label=Pkeylabel;                                 //~@@@@R~
//                code=myKbdView.swipeKeycodeTbl[Pkeyindex][0];    //~@@@@R~
//            }                                                    //~@@@@R~
//            else                                                 //~@@@@R~
//            {                                                    //~@@@@R~
//                if (Ppopuplabel!=null                            //~@@@@R~
//                &&  Pkeylabel!=null && Pkeylabel.length()==1)    //~@@@@R~
//                {                                                //~@@@@R~
//                    int swipedefined=Ppopuplabel.length();       //~@@@@R~
//                    if (swipeindex<swipedefined)                 //~@@@@R~
//                    {                                            //~@@@@R~
//                        c=Ppopuplabel.charAt(swipeindex);        //~@@@@R~
//                    }                                            //~@@@@R~
//                    if (c!=0)                                    //~@@@@R~
//                        label=String.valueOf(c);                 //~@@@@R~
//                }                                                //~@@@@R~
//                if (c==' ')                                      //~@@@@R~
//                    code=0;                                      //~@@@@R~
//                else                                             //~@@@@R~
//                    code=c;                                      //~@@@@R~
//            }                                                    //~@@@@R~
//            code=myKbdView.swipeKeycodeTbl[Pkeyindex][swipeindex];//~@@@@R~
            code=flickKey.code;                                    //~@@@@R~
            if (code>0)                                            //~@@@@R~
                label=String.valueOf((char)code);                  //~@@@@R~
            else   //code==0                                       //~@@@@R~
            {                                                      //~@@@@I~
	            code=Pprimarycode; //codes[0]                      //~@@@@I~
            	if (code<0)	//Ctrl,kbd; label is fixed	           //~@@@@I~
            		label=Pkeylabel;                               //~@@@@I~
            	else                                               //~@@@@I~
                if (AxeKeyValue.isValidExtGDK(Pprimarycode))       //~@@@@I~
                {                                                  //~@@@@I~
//                    label=myKbdView.swipeExtkeyNameTbl[Pkeyindex][0];//~@@@@R~
                    label=myKbdView.flickKeyTbl[Pkeyindex][0].name;//~@@@@I~
                    if (label==null)                               //~@@@@R~
                        code=0;                                    //~@@@@R~
                }                                                  //~@@@@I~
                else                                               //~@@@@I~
                	code=0;	//set label=" "                        //~@@@@I~
            }                                                      //~@@@@I~
        }                                                          //~@@@@I~
        else       //ext key                                       //~@@@@R~
        {                                                          //~@@@@I~
//            code=myKbdView.swipeKeycodeTbl[Pkeyindex][Pswipeindex];//~@@@@R~
            code=flickKey.code;                                    //~@@@@I~
        }                                                          //~@@@@I~
        modifiedCode=code;	//for minikbd                          //~@@@@I~
//        if (Dump.Y) Dump.println("AxeKbd getModifiedLabel code="+Integer.toHexString(code)+",label="+label);//~@@@@R~
        return label;
    }//~@@@@I~
    //****************************************************************//~@@@@I~
    //*additional to swipe=1,select from swipe=4 if char code      //~@@@@I~
    //***************************************************************//~@@@@I~
    private CharSequence getModifiedLabelShiftR(int Pprimarycode,CharSequence Pkeylabel,int Pkeyindex,int Pswipeindex)//~@@@@R~
    {                                                              //~@@@@I~
    	int code;                                                  //~@@@@I~
        CharSequence label=null;                                   //~@@@@I~
        int swipeindex=Pswipeindex;                                //~@@@@I~
//        if (Dump.Y) Dump.println("AxeKbd getModifiedLabelShiftR keyindex="+Pkeyindex+",swipeindex="+Pswipeindex);//~@@@@R~
//      if (Pkeylabel!=null && Pkeylabel.length()>1)	//not charkey                //~@@@@I~//+vaayR~
        code=myKbdView.flickKeyTbl[Pkeyindex][0].code;             //+vaayI~
        if (code<' ' || AxeKeyValue.isValidExtGDK(code))           //+vaayI~
        {                                                          //~@@@@I~
        	label=Pkeylabel;                                       //~@@@@I~
//            code=myKbdView.swipeKeycodeTbl[Pkeyindex][0];//set !=0//~@@@@R~
//          code=myKbdView.flickKeyTbl[Pkeyindex][0].code;//set !=0//~@@@@I~//+vaayR~
        }                                                          //~@@@@I~
        else                                                       //~@@@@I~
        {                                                          //~@@@@I~
//            code=myKbdView.swipeKeycodeTbl[Pkeyindex][swipeindex];//~@@@@R~
            code=myKbdView.flickKeyTbl[Pkeyindex][swipeindex].code;//~@@@@I~
//          if (code<' '|| code>=0x80)    //not ascii              //~@@@@R~//~vaasR~
            if (code<' '|| AxeKeyValue.isValidExtGDK(code)) //not defined or Fn//~vaasI~
            {                                                      //~@@@@R~
//                code=myKbdView.swipeKeycodeTbl[Pkeyindex][1];    //~@@@@R~
                code=myKbdView.flickKeyTbl[Pkeyindex][1].code;     //~@@@@I~
//              if (code<' ' || code>=0x80                         //~@@@@R~//~vaasR~
                if (code<' ' || AxeKeyValue.isValidExtGDK(code)    //~vaasI~
                ||  (code>='A' && code<='Z'))                      //~@@@@R~
                	code=0;                                        //~@@@@R~
            }                                                      //~@@@@R~
            if (code!=0)                                           //~@@@@R~
                label=String.valueOf((char)code);                  //~@@@@R~
        }                                                          //~@@@@I~
        modifiedCode=code; //for minikbd                           //~@@@@R~
//        if (Dump.Y) Dump.println("AxeKbd getModifiedLabelShiftR code="+Integer.toHexString(code)+",label="+label);//~@@@@R~
        return label;                                              //~@@@@I~
    }                                                              //~@@@@I~
    //****************************************************************//~vaawI~
    //*additional to swipe=1,select all GDK code                   //~vaawI~
    //***************************************************************//~vaawI~
    private CharSequence getModifiedLabelShiftF(Key Pkey,int Pkeyindex,int Pswipeindex)//~vaawI~
    {                                                              //~vaawI~
    	int code;                                                  //~vaawI~
        CharSequence label;                                        //~vaawR~
        int swipeindex;                                            //~vaawR~
    //***************************                                  //~vaawI~
    	label=Pkey.label;                                          //~vaawI~
        if (Dump.Y) Dump.println("AxeKbd getModifiedLabelShiftF keyindex="+Pkeyindex+",label="+label+",swipeindex="+Pswipeindex);//~vaawR~
		swipeindex=getSwipeIndexShiftF(Pkeyindex,Pswipeindex);     //~vaawI~
        if (swipeindex<0)                                          //~vaawR~
        {                                                          //~vaawI~
		    code=Pkey.codes[0];                                    //~vaawI~
            if (code>' ' && !AxeKeyValue.isValidExtGDK(code))      //~vaawI~
                label=String.valueOf((char)code);                  //~vaawI~
        }                                                          //~vaawI~
        else                                                       //~vaawI~
        {                                                          //~vaawI~
	        code=myKbdView.flickKeyTbl[Pkeyindex][swipeindex].code;//~vaawI~
        	label=myKbdView.flickKeyTbl[Pkeyindex][swipeindex].name;//~vaawI~
        }                                                          //~vaawI~
        modifiedCode=code; //for minikbd                           //~vaawI~
        if (Dump.Y) Dump.println("AxeKbd getModifiedLabelShiftF gdk code="+Integer.toHexString(code)+",label="+label);//~vaawI~
        return label;                                              //~vaawI~
    }//getModifiedLabelShiftF                                      //~vaawI~
    //***************************************************************//~vaawI~
	public int getSwipeIndexShiftF(int Pkeyindex,int Pswipeindex)  //~vaawI~
    {                                                              //~vaawI~
    	int code,swipeindex=Pswipeindex;                           //~vaawI~
    //***********************                                      //~vaawI~
        code=myKbdView.flickKeyTbl[Pkeyindex][swipeindex].code;    //~vaawI~
        if (!AxeKeyValue.isValidExtGDK(code)) //not defined or Fn  //~vaawI~
        {                                                          //~vaawI~
        	for (swipeindex=1;swipeindex<=AxeKbdView.MAXSWIPE;swipeindex++)   //~vaawI~
            {                                                      //~vaawI~
		        code=myKbdView.flickKeyTbl[Pkeyindex][swipeindex].code;//~vaawI~
		        if (AxeKeyValue.isValidExtGDK(code)) //not defined or Fn//~vaawI~
                	break;                                         //~vaawI~
            }                                                      //~vaawI~
            if (swipeindex>AxeKbdView.MAXSWIPE)                               //~vaawI~
            	swipeindex=-1;                                     //~vaawI~
        }                                                          //~vaawI~
        return swipeindex;                                         //~vaawI~
    }                                                              //~vaawI~
    //***************************************************************//~vaawI~
	public int getCodeShiftF(int Pkeyindex)                        //~vaawR~
    {                                                              //~vaawI~
    	int code,swipeindex;                                       //~vaawI~
    //***********************                                      //~vaawI~
    	swipeindex=AxeKbdView.SWIPE_SHIFTF+1;                                  //~vaawI~
		swipeindex=getSwipeIndexShiftF(Pkeyindex,swipeindex);      //~vaawR~
        if (swipeindex<0)                                                 //~vaawI~
        	code=0;                                                //~vaawI~
        else                                                       //~vaawI~
        	code=myKbdView.flickKeyTbl[Pkeyindex][swipeindex].code;//~vaawI~
        return code;                                               //~vaawI~
    }                                                              //~vaawI~
    //***************************************************************//~vaawI~
    public boolean isShiftRed()                                    //~@@@@I~
    {                                                              //~@@@@I~
    	return statusShiftR;                                       //~@@@@I~
    }                                                              //~@@@@I~
    public boolean isShiftFed()                                    //~vaawI~
    {                                                              //~vaawI~
    	return statusShiftF;                                       //~vaawI~
    }                                                              //~vaawI~
    public boolean isCapsed()                                      //~@@@@I~
    {                                                              //~@@@@I~
    	return statusCaps;                                         //~@@@@I~
    }                                                              //~@@@@I~
    public boolean isShortcut()                                    //~@@@@I~
    {                                                              //~@@@@I~
    	return statusShortcut;                                     //~@@@@I~
    }                                                              //~@@@@I~
    public boolean isAlted()                                       //~@@@@R~
    {                                                              //~@@@@I~
    	return statusAlt;                                          //~@@@@R~
    }                                                              //~@@@@I~
    public boolean isAltGred()                                     //~@@@@I~
    {                                                              //~@@@@I~
    	return statusAltGr;                                        //~@@@@I~
    }                                                              //~@@@@I~
    public boolean isAltGrSed()                                    //~@@@@I~
    {                                                              //~@@@@I~
    	return statusAltGrShift;                                   //~@@@@I~
    }                                                              //~@@@@I~
    public boolean isCtrled()                                      //~@@@@I~
    {                                                              //~@@@@I~
    	return statusCtrl;                                         //~@@@@I~
    }                                                              //~@@@@I~
}
