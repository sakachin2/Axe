//*CID://+vaxdR~: update#=  219;                                   //~vaxdR~
//********************************************************************//~vaaBI~
//vaxd:140706 (Axe)Show IM even when hardware kbsd is connected    //~vaxdI~
//vaaC:120110 close preedit when IM was closed by back key         //~vaaCI~
//vaaB:120110 (Axe:Bug)close IM when preedit preview was closed    //~vaaBI~
//********************************************************************//~vaaBI~
package com.xe.Axe;                                                //~@@@@I~

import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.content.Context;                                    //~vaaCI~
import android.util.AttributeSet;                                  //~vaaCI~
                                                                  //~vaaCI~
public class AxeDlgImePreedit extends AxeDialog                     //~1821R~//~1826R~
{                                                                  //~1606I~
///////////////////////////////////////////////////////////////////////////////~1606I~
//**********************************************************************//~1725I~
	private static final int LAYOUT=R.layout.dialogimepreedit;      //~1821R~//~1826R~
	private static final int TITLE =R.string.DialogTitle_ImePreedit;//~1821R~//~1826R~
	private static final int ID_CLOSEIMAGEBUTTON=R.id.ClosePreedit;           //~1919I~
	private static AxeDlgImePreedit preeditDialog;                 //~vaaCR~
	private boolean requestedHideIME;                              //~vaaCR~
                                                                   //~1822I~
    private EditText etPreedit;                                    //~1826R~
//**********************************                               //~1725I~
	public AxeDlgImePreedit()                                       //~1821R~//~1826R~
    {                                                              //~1725I~
    	super(LAYOUT);                                             //~1821R~
    }                                                              //~1725I~
//**********************************                               //~1725I~
    public static AxeDlgImePreedit showDialog()                 //~1821R~//~1826R~
    {                                                              //~1725I~
        if (Dump.Y) Dump.println("AxeDlgImePreedit.showDialog");   //~vaxdI~
    	AxeDlgImePreedit dlg=new AxeDlgImePreedit();                 //~1821R~//~1826R~
        String title=Utils.getResourceString(TITLE);               //~1821R~
		dlg.showDialog(title);                                     //~1725I~
        preeditDialog=dlg;                                         //~vaaCI~
        return dlg;                                                //~1725I~
    }                                                              //~1725I~
////*********                                                        //~1725I~//~1826R~
//    public void onMeasure(int Pw,int Ph)                         //~1826R~
//    {                                                            //~1826R~
//        Dump.println("AxeDlgPreedit onMeasure w="+Integer.toHexString(Pw)+",h="+Integer.toHexString(Ph));//~1826R~
//        int ww=MeasureSpec.getSize(Pw);                          //~1826R~
//        int hh=MeasureSpec.getSize(Ph);                          //~1826R~
//        if (ww>hh)                                               //~1826R~
//            ww=hh;                                               //~1826R~
//        Dump.println("setMeasure w="+Integer.toHexString(ww)+",h="+Integer.toHexString(hh));//~1826R~
//        setMeasuredDimension(ww,hh);                             //~1826R~
//    }                                                            //~1826R~
	@Override                                                      //~1826I~
	protected void setupDialogExtend(ViewGroup PlayoutView)        //~1826I~
    {                                                              //~1826I~
    //********************                                         //~1826I~
        ImageButton imageCloseButton=(ImageButton)layoutView.findViewById(ID_CLOSEIMAGEBUTTON);//~1919I~
		setButtonListener(imageCloseButton);                       //~1919I~
	    etPreedit=(EditText)layoutView.findViewById(R.id.IME_PREEDIT);//~1826I~
//      etPreedit.setFocusableInTouchMode(true);	//DOC says "FocusableInTouch then Focusable"//~1325I~//~1828R~
//      etPreedit.setFocusable(true);                                     //~1127I~//~1828R~
	    etPreedit.setText("");                                     //~1826I~
        addFocusChangeListener(etPreedit);//showKbd by OnShowListener //TODO test    //~1826I~//~1828R~//~vaxdR~
//      etPreedit.requestFocus();                                  //~1826I~//~1828I~
        etPreedit.requestFocusFromTouch();                         //~1828R~
        etPreedit.setOnEditorActionListener(new EditorActionListener());//~1826I~
        setIMFocus();                                              //~1827I~
        if (Dump.Y) Dump.println("AxeDlgImePreedit.setupDialogExtend Open");         //~1827I~//~vaxdR~
    }                                                              //~1826I~
    @Override                                                      //~1827I~//~vaxdR~
    public void onWindowFocusChanged(boolean Phasfocus)            //~1827I~//~vaxdR~
    {                                                              //~1827I~//~vaxdR~
        if (Dump.Y) Dump.println("AxeDlgImePreedit onWindowFocusChanged hasFocus="+Phasfocus);//~1827I~//~vaxdR~
    }                                                              //~1827I~//~vaxdR~
    //****************                                             //~1826I~//~1828R~//~vaxdR~
    public void addFocusChangeListener(EditText PeditText)  //TODO test       //~1826I~//~1828R~//~vaxdR~
    {                                                              //~1826I~//~1828R~//~vaxdR~
        if (Dump.Y) Dump.println("AxeDlgImePreedit.addFocusChangeListener PeditText="+PeditText);//~1827I~//~1828R~//~vaxdR~
        FocusChangeListener l=new FocusChangeListener(PeditText);              //~1826I~//~1828R~//~vaxdR~
        PeditText.setOnFocusChangeListener(l);                     //~1826I~//~1828R~//~vaxdR~
    }                                                              //~1826I~//~1828R~//~vaxdR~
    //****************                                             //~1828I~
    private void setShownListener()                                //~1828I~
    {                                                              //~1828I~
        if (Dump.Y) Dump.println("AxeDlgImePreedit.setShownListener");//~1828I~//~vaxdR~
        /*androidDialog.*/setOnShowListener(                          //~1828I~
        	new OnShowListener()                  //~1828I~
            {                                                      //~1828I~
            	@Override                                          //~1828I~
                public void onShow(DialogInterface Pdialog)        //~1828I~
                {                                                  //~1828I~
					if (Dump.Y) Dump.println("AxeDlgImePreedit:onShow");//~vaxdR~
					etPreedit.requestFocus();	//TODO test        //+vaxdM~
            		AxeG.axeIME.showDefaultKbd(etPreedit);                //~1828I~
					requestedHideIME=false;                        //~vaaCI~
                }                                                  //~1828I~
           }                                                      //~1828I~
        );                                                         //~1828I~
    }                                                              //~1828I~
//**********************************************************************//~1827I~
	private void setIMFocus()                                      //~1827I~
    {                                                              //~1827I~
//        Window window=androidDialog.getWindow();                 //~1827R~
//        if (Dump.Y) Dump.println("AxeDlgImePreedit setIMFocus windos="+window.toString());//~1827R~
//        window.setFlags(                                         //~1827R~
//                            WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,//~1827R~
//                            WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);//~1827R~
////      AxeG.axeIME.showKbd(etPreedit);                          //~1827R~
//		window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);//~1827I~
//		AxeG.axeIME.showKbdDelayed(etPreedit);                     //~1827I~//~1828R~
	    setShownListener(); //                                     //~1828R~
    }                                                              //~1827I~
////**********************************************************************//~1826I~//~1828R~
////*Focuschange listener                                         //~1826I~//~1828R~
////**********************************************************************//~1826I~//~1828R~
//    class FocusListener implements OnFocusChangeListener           //~1826I~//~1828R~
//    {                                                              //~1826I~//~1828R~
//        EditText editText;                                         //~1826I~//~1828R~
//        //*********************                                    //~1826I~//~1828R~
//        public FocusListener(EditText PeditText)                   //~1826I~//~1828R~
//        {                                                          //~1826I~//~1828R~
//            editText=PeditText;                                    //~1826I~//~1828R~
//        }                                                          //~1826I~//~1828R~
//        //*********                                                //~1826I~//~1828R~
//        public void onFocusChange(View Pview,boolean PhasFocus)    //~1826I~//~1828R~
//        {                                                          //~1826I~//~1828R~
//        //**************                                           //~1826I~//~1828R~
//            try                                                    //~1826I~//~1828R~
//            {                                                      //~1826I~//~1828R~
//                if (Dump.Y) Dump.println("AxeDlgImePreedit FocusListener focus="+PhasFocus+",view="+Pview.toString());//~1826I~//~1828R~
//                                                                   //~1826I~//~1828R~
//                if (PhasFocus)                                     //~1826I~//~1828R~
//                {                                                  //~1826I~//~1828R~
//                    AxeG.axeIME.showKbd(Pview);                    //~1826I~//~1828R~
//                }                                                  //~1826I~//~1828R~
//            }                                                      //~1826I~//~1828R~
//            catch(Exception e)                                     //~1826I~//~1828R~
//            {                                                      //~1826I~//~1828R~
//                Dump.println(e,"AxeDlgImePreedit:OnFocusChange");  //~1826I~//~1828R~
//            }                                                      //~1826I~//~1828R~
//        }                                                          //~1826I~//~1828R~
//                                                                   //~1826I~//~1828R~
//    }//inner class                                                 //~1826I~//~1828R~
//******************************                                   //~1919I~
	@Override                                                      //~1919I~
	protected boolean onClickOther(int PbuttonId)                  //~1919I~
    {                                                              //~1919I~
    	switch(PbuttonId)                                          //~1919I~
        {                                                          //~1919I~
        case ID_CLOSEIMAGEBUTTON:                                             //~1919I~
            AxeG.axeIME.hideKbd(etPreedit);                           //~1827R~//~vaaBI~
        	return onClickClose();	//return true fo dismiss       //~1919I~
        }                                                          //~1919I~
        return false;                                              //~1919I~
    }                                                              //~1919I~
	@Override                                                      //~vaaCI~
	protected void onDismiss()                                     //~vaaCI~
    {                                                              //~vaaCI~
        if (Dump.Y) Dump.println("AxeDlgImePreedit.OnDismiss");    //~vaaCI~//~vaxdR~
		hideIME();                                                 //~vaaCI~
    }                                                              //~vaaCI~
	private void hideIME()                                          //~vaaCR~//~vaxdR~
    {                                                              //~vaaCI~
        if (Dump.Y) Dump.println("AxeDlgImePreedit.hideIME requested="+requestedHideIME);//~vaaCI~//~vaxdR~
		if (!requestedHideIME)                                     //~vaaCI~
        {                                                          //~vaaCI~
    		AxeG.axeIME.hideKbd(etPreedit);                        //~vaaCI~
			requestedHideIME=true;                                 //~vaaCI~
        }                                                          //~vaaCI~
    }                                                              //~vaaCI~
//**********************************************************************//~1828I~
//*EditText click listener to send commit text to xe               //~1828I~
//**********************************************************************//~1828I~
    class  EditorActionListener implements OnEditorActionListener
    {                                                              //~1826I~
		public EditorActionListener()                              //~1826I~
        {                                                          //~1826I~
        }                                                          //~1826I~
        @Override                                                  //~1826I~
        public boolean onEditorAction(TextView Pview,int Paction,KeyEvent Pevent)//~1826I~
        {                                                          //~1826I~
                                                                   //~1826I~
            if (Dump.Y) Dump.println("AxeDlgImePreedit.onEditorAction action="+Paction);//~1826I~//~vaaCR~//~vaxdR~
        	try                                                    //~1826I~
            {                                                      //~1826I~
                int keycode=0,scancode=0;                          //~1919R~
                if (Pevent==null)                                   //~1826I~
                {                                                  //~vaaCI~
                    if (Dump.Y) Dump.println("AxeDlgImePreedit.onEditorAction event=null");//~1826I~//~vaaCR~//~vaxdR~
                }                                                  //~vaaCI~
                else                                               //~1826I~
                {                                                  //~1919I~
                    keycode=Pevent.getKeyCode();               //~1826I~//~1827R~
                    scancode=Pevent.getScanCode();//of Enter Key   //~1919I~
                }                                                  //~1919I~
                String eti=Pview.getText().toString();         //~1826I~//~1827R~
                if (Dump.Y) Dump.println("AxeDlgImePreedit.onEditAction str="+eti+",keycode="+keycode);//~1826I~//~1827R~//~vaxdR~
                if (!eti.equals(""))                           //~1826I~//~1827R~
                {                                              //~1826I~//~1827R~
                    Pview.setText("");                         //~1826I~//~1827R~
                    int ch=eti.charAt(0);                          //~1831R~
                    if (eti.length()==1)                           //~1831I~
                    	AxeG.axeKeyAction.charInput(KeyEvent.ACTION_DOWN,ch,scancode,Pevent);//apply codemapping by modifier key(Shortcut etc)//~1831R~
                    else                                           //~1831I~
                    	AxeG.axeKeyAction.strInput(eti);           //~1826R~//~1831R~
                }                                              //~1826I~//~1827R~
            }                                                      //~1826I~
            catch(Exception e)                                     //~1826I~
            {                                                      //~1826I~
                Dump.println(e,"AxeDlgImePreedit:EditorActionListener");//~1826I~
            }                                                      //~1826I~
            return true;                                           //~1826I~
        }//onEditActoon                                            //~1831R~
    }//inner class                                                 //~1831R~
    public static class preEdit	extends EditText                   //~vaaCR~
    {                                                              //~vaaCI~
    	public preEdit(Context Pcontext,AttributeSet attrs,int defStyle)  //~vaaCI~
        {                                                          //~vaaCI~
	    	super(Pcontext,attrs,defStyle);//~vaaCI~
        }                                                          //~vaaCI~
    	public preEdit(Context Pcontext,AttributeSet attrs)               //~vaaCI~
        {                                                          //~vaaCI~
	    	super(Pcontext,attrs);                                 //~vaaCI~
        }                                                          //~vaaCI~
    	public preEdit(Context Pcontext)                                  //~vaaCI~
        {                                                          //~vaaCI~
	    	super(Pcontext);                                       //~vaaCI~
        }                                                          //~vaaCI~
        @Override                                                  //~vaaCI~
        public boolean onKeyPreIme(int Pkeycode,KeyEvent Pevent)   //~vaaCI~
        {                                                          //~vaaCI~
            if (Dump.Y) Dump.println("AxeDlgImePreedit.onKeyPreIme keycoe="+Integer.toHexString(Pkeycode));//~vaaCI~//~vaxdR~
        	if (Pkeycode==KeyEvent.KEYCODE_BACK)                    //~vaaCI~
            {                                                      //~vaaCI~
				if (AxeDlgImePreedit.preeditDialog!=null)          //~vaaCI~
					AxeDlgImePreedit.preeditDialog.hideIME();      //~vaaCI~
                if (Dump.Y) Dump.println("onKeyPreIME BackKey");    //~vaaCI~
            }                                                      //~vaaCI~
            if (Dump.Y) Dump.println("onKeyPreIME before call super");//~vaaCI~
            boolean rc=super.onKeyPreIme(Pkeycode,Pevent);         //~vaaCM~
            if (Dump.Y) Dump.println("onKeyPreIME after  call super rc="+rc);//~vaaCI~
            return rc;                                             //~vaaCR~
        }                                                          //~vaaCI~
    }                                                              //~vaaCI~
//**********************************************************************//~vaxdI~
    class FocusChangeListener implements View.OnFocusChangeListener//~vaxdM~
    {                                                              //~vaxdM~
    	private View view;                                         //~vaxdM~
    	public FocusChangeListener(View Pview)                     //~vaxdM~
        {                                                          //~vaxdM~
        	view=Pview;                                            //~vaxdM~
        }                                                          //~vaxdM~
        @Override                                                  //~vaxdM~
        public void onFocusChange(View Pview, boolean Phasfocus)   //~vaxdM~
        {                                                          //~vaxdM~
            if (Dump.Y) Dump.println("AxeDlgImePreedit.onFocusChanged to "+Phasfocus+",view="+Pview.toString());//~vaxdM~
        }                                                          //~vaxdM~
    }//class FocusChangeListener                                   //~vaxdM~
}                                                                  //~1528R~
