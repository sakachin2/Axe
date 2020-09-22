//*CID://+vc1tR~:                             update#=    9;       //~vc1tI~
//***********************************************************************************************************//~vc1tI~
//vc1t 2020/07/02 multi column EditText ListView callback OnFocusListener called twice True then False(mey be bug)//~vc1tI~
//                requestFocus after short time may fix it but SoftKbd popup could not be protected(can be disapper after once popupped)//~vc1tI~
//                Try to receive input by hard/soft keyboard by dummy(InVisible) EditText then send to ListView.//~vc1tI~
//                Receive HardKbd input by textview(requestFocus), //~vc1tI~
//                By IME button set dummy EditText to focusable and receive from SoftKbd//~vc1tI~
//***********************************************************************************************************//~vc1tI~
package com.xe.Axe;                                                //~1726I~

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TextView;

//~1726I~
//************************************                             //~1726I~
//*support SetupColor                *                             //~1726I~
//************************************                             //~1726I~
                                                                   //~1726I~
public class EditTextField                                         //~1730R~
	implements OnKeyListener                                       //~1730R~
{                                                                  //~1726I~
    private EditTextListener listener;                        //~1730R~
    private EditText edittext;                                     //~1726I~
    private int  value;                                            //~1730I~
    private int  viewid;                                           //~1730I~
    private boolean swNumeric;                                             //~vc1tI~
                                                                   //~1726I~
    public EditTextField(EditTextListener Plistener,EditText PeditText)//~1730R~
    {                                                              //~1726I~
    	listener=Plistener;                                        //~1726R~
        edittext=PeditText;                                        //~1730R~
        viewid=edittext.getId();                                   //~1730I~
    }                                                              //~1726I~
    public static EditTextField setListener(EditTextListener Plistener,EditText PeditText)//~1730R~
    {                                                              //~1730I~
    	EditTextField etf=new EditTextField(Plistener,PeditText);//~1730R~
    	etf.setkeylistener(PeditText);                                  //~1726I~//~vc1tI~
    	return etf;                                                //~1730I~
    }                                                              //~1730I~
    public static EditTextField setListener(EditTextListener Plistener,EditText PeditText,boolean PswNumeric)//~vc1tR~
    {                                                              //~vc1tR~
        EditTextField etf=new EditTextField(Plistener,PeditText);  //~vc1tR~
    	etf.setkeylistener(PeditText);                              //~vc1tI~
        etf.swNumeric=PswNumeric;                                  //~vc1tR~
        return etf;                                                //~vc1tR~
    }                                                              //~vc1tR~
    public static EditTextField addWatcher(EditTextListener Plistener,EditText PeditText)//~vc1tI~
    {                                                              //~vc1tI~
        EditTextField etf=new EditTextField(Plistener,PeditText);  //~vc1tI~
        etf.addWatcher(PeditText);                                  //~vc1tI~
        return etf;                                                //~vc1tI~
    }                                                              //~vc1tI~
    private void  addWatcher(EditText PeditText)                   //~vc1tI~
    {                                                              //~vc1tI~
		if (Dump.Y) Dump.println("EditTextField.addWatcher editText="+PeditText.toString());//~vc1tI~
        ((TextView)PeditText).addTextChangedListener              //~vc1tI~
        	(                                                      //~vc1tI~
        	new TextWatcher()                                      //~vc1tI~
            	{                                                  //~vc1tI~
                    @Override                                      //+vc1tR~
                    public void beforeTextChanged(CharSequence s,int start,int count,int after)//+vc1tR~
                    {                                              //+vc1tR~
                        try                                        //+vc1tR~
                        {                                          //+vc1tR~
                            if (Dump.Y) Dump.println("EditTextField.beforeTextChanged text="+s.toString()+",start="+start+",count="+count+",after="+after+",str="+s.toString()+",editText="+edittext.toString());//+vc1tR~
                            String text=s.toString();              //+vc1tR~
                            listener.beforeTextChangedETF(edittext,text,start,count,after);//+vc1tR~
                        }                                          //+vc1tR~
                        catch(Exception e)                         //+vc1tR~
                        {                                          //+vc1tR~
                            Dump.println(e,"EditTextField.beforeTextChanged");//+vc1tR~
                        }                                          //+vc1tR~
                    }                                              //+vc1tR~
                    @Override                                      //+vc1tR~
                    public void onTextChanged(CharSequence s,int start,int before,int count)//+vc1tR~
                    {                                              //+vc1tR~
                        try                                        //+vc1tR~
                        {                                          //+vc1tR~
                            if (Dump.Y) Dump.println("EditTextField.onTextChanged text="+s.toString()+",start="+start+",before="+before+",count="+count+",str="+s.toString()+",editText="+edittext.toString());//+vc1tR~
                            String text=s.toString();              //+vc1tR~
                            listener.onTextChangedETF(edittext,text,start,before,count);//+vc1tR~
                        }                                          //+vc1tR~
                        catch(Exception e)                         //+vc1tR~
                        {                                          //+vc1tR~
                            Dump.println(e,"EditTextField.onTextChanged");//+vc1tR~
                        }                                          //+vc1tR~
                    }                                              //+vc1tR~
                    @Override //TextWatcher                        //+vc1tR~
                    public void afterTextChanged(Editable s)       //+vc1tR~
                    {                                              //+vc1tR~
                        try                                        //+vc1tR~
                        {                                          //+vc1tR~
                            String text=s.toString();              //+vc1tR~
                            if (Dump.Y) Dump.println("EditTextField.afterTextChanged text="+text+",editText="+edittext.toString());//+vc1tR~
                            listener.afterTextChangedETF(edittext,text);//+vc1tR~
                        }                                          //+vc1tR~
                        catch(Exception e)                         //+vc1tR~
                        {                                          //+vc1tR~
                            Dump.println(e,"EditTextField.afterTextChanged");//+vc1tR~
                        }                                          //+vc1tR~
                    }                                              //+vc1tR~
                }                                                  //+vc1tI~
    		);                                                     //~vc1tI~
    }                                                              //~vc1tI~
    private void setkeylistener(View Pv)                           //~1726I~
    {                                                              //~1726I~
        //********************                                         //~1726I~                                                             //~1726I~
        Pv.setOnKeyListener(this);                                 //~1726I~
        Pv.setFocusableInTouchMode(true);	//DOC says "FocusableInTouch then Focusable"//~1726I~
        Pv.setFocusable(true);                                     //~1726I~
        addFocusChangeListener(Pv);                                //~1726I~
    }                                                              //~1726I~
    private void addFocusChangeListener(View Pv)                   //~1726I~
    {                                                              //~1726I~ 
    	if (Dump.Y) Dump.println("EditTextField.addFocuschange Listener viewid="+Integer.toString(Pv.getId(),16));//~1730R~//~0702R~
    	Pv.setOnFocusChangeListener(                               //~1726I~
        	new OnFocusChangeListener()                            //~1726I~
            	{                                                  //~1726I~
                  	@Override                                      //~1830R~
                    public void onFocusChange(View Pview,boolean Phasfocus)//~1726I~
                    {                                              //~1726I~
				        if (Dump.Y) Dump.println("EditTextField.onFocusChange hasfocus="+Phasfocus+",viewid="+Integer.toString(Pview.getId(),16));//~1726I~//~0702R~
                        if (!Phasfocus)	//lost focus               //~1726I~
    						getEditTextValue(edittext);                  //~1726I~
                    }                                              //~1726I~
                }                                                  //~1726I~
                				);                                 //~1726I~
    }                                                              //~1726I~
    public int getValue()                                          //~1726I~
    {                                                              //~1726I~
    	return value;                                              //~1730R~
    }                                                              //~1726I~
    public void setValue(int Pvalue)                               //~1726I~//~vc1tR~
    {                                                              //~1726I~
    	value=Pvalue;                                              //~1730R~
        listener.onEditTextChanged(viewid,Pvalue);                 //~1730I~//~vc1tR~
    }                                                              //~1726I~
    public void setValue(String Pvalue)                            //~vc1tR~
    {                                                              //~vc1tR~
        listener.onEditTextChanged(viewid,Pvalue);                 //~vc1tR~
    }                                                              //~vc1tR~
//****************************                                     //~1726I~
    public boolean onKey(View Pview,int Pkeycode,android.view.KeyEvent Pevent)//~v@@@I~//~1726I~
    {                                                      //~v@@@I~//~1726I~
        int action;                                //~v@@@I~       //~1726I~
        boolean rc=false;                                          //~1726I~
        //**********************                           //~v@@@I~//~1726I~
        try                                                        //~1726I~
        {                                                          //~1726I~
            action=Pevent.getAction();                          //~v@@@R~//~1726I~
            if (Dump.Y) Dump.println("EditTextField.Onkey action="+action+",keycode="+Pkeycode+",viewid="+Integer.toString(Pview.getId(),16)+",isfocus="+Pview.isFocused()); //~v@@@I~//~1116I~//~1726I~//~0702R~//~vc1tI~
            switch(action)                                     //~v@@@I~//~1726I~
            {                                                  //~v@@@I~//~1726I~
            case android.view.KeyEvent.ACTION_DOWN:                //~1726I~
                rc=onKeyDown(Pview,Pkeycode,Pevent);               //~1726I~
                break;                                             //~1726I~
            default:                                           //~v@@@I~//~1726I~
            }                                                  //~v@@@I~//~1726I~
        }                                                          //~1726I~
        catch(Exception e)                                         //~1726I~
        {                                                          //~1726I~
            Dump.println(e,"EditTextField EditText.OnKey exception");//~1730R~
        }                                                          //~1726I~
        return rc;           //~v@@@I~                             //~1726I~
    }                                                      //~v@@@I~//~1726I~
//****************************                                     //~1726M~
    public boolean onKeyDown(View v,int keycode,KeyEvent ev)       //~1726M~
    {                                                          //~1113I~//~1114I~//~1726M~
    	boolean rc=false;                                          //~1726M~
    //*******************                                          //~1726M~
    	if (Dump.Y) Dump.println("EditTextField.keyDown code="+keycode);                 //~1114I~//~1730R~//~0702R~
        if (keycode==KeyEvent.KEYCODE_ENTER)                       //~1726I~
        {                                                          //~1726I~
    		if (Dump.Y) Dump.println("EditTextField.keyDown code="+keycode+"=KEYCODE_ENTER");//~vc1tI~
            getEditTextValue(v);                                   //~1726I~
            rc=true;                                               //~1726I~
        }                                                          //~1726I~
    	if (Dump.Y) Dump.println("EditTextField.keyDown rc="+rc);                //~1726M~//~0702R~
        return rc;   //android process                      //~1113R~//~1114I~//~1726M~
    }                                                              //~1726M~
    //*************************************                        //~1726M~
    private void getEditTextValue(View Pview)                      //~1726M~
	{                                                              //~1726M~
        String text=((EditText)Pview).getText().toString();                    //~1726M~
    	if (Dump.Y) Dump.println("EditTextField.getEditTextValue text="+text);//~vc1tI~
    	if (!swNumeric)                                            //~vc1tI~
        {                                                          //~vc1tI~
	        setValue(text);                                        //~vc1tI~
            return;                                                //~vc1tI~
        }                                                          //~vc1tI~
        int value;                                                 //~1726M~
		try                                                        //~1726M~
		{                                                          //~1726M~
        	value=Integer.parseInt(text);                          //~1726M~
		}                                                          //~1726M~
		catch (NumberFormatException e)                            //~1726M~
		{                                                          //~1726M~
        	value=-1;                                              //~1726M~
		}                                                          //~1726M~
        if (value<0||value>255)                                    //~1726M~//~vc1tR~
        	return;                                                //~1726M~//~vc1tR~
        setValue(value);                                           //~1726M~//~vc1tR~
    	if (Dump.Y) Dump.println("EditTextField.getEditTextValue value="+value);//~1730R~//~0702R~//~vc1tR~
	}                                                              //~1726M~
}//class                                                           //~1726I~
