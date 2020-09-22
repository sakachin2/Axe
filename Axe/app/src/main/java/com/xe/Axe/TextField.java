//*CID://+vc1tR~:                             update#=    6;       //~vc1tI~
//***********************************************************************************************************//~vc1tI~
//vc1t 2020/07/02 multi column EditText ListView callback OnFocusListener called twice True then False(mey be bug)//~vc1tI~
//                requestFocus after short time may fix it but SoftKbd popup could not be protected(can be disapper after once popupped)//~vc1tI~
//                Try to receive input by hard/soft keyboard by dummy(InVisible) EditText then send to ListView.//~vc1tI~
//                Receive HardKbd input by textview(requestFocus), //~vc1tI~
//                By IME button set dummy EditText to focusable and receive from SoftKbd//~vc1tI~
//***********************************************************************************************************//~vc1tI~
package com.xe.Axe;                                                //~1726I~

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.TextView;

import com.xe.Axe.kbd.AxeKbdDialogHW;

import static android.view.KeyEvent.*;

//~1726I~
//************************************                             //~1726I~
//*support SetupColor                *                             //~1726I~
//************************************                             //~1726I~
                                                                   //~1726I~
public class TextField                                         //~1730R~//~vc1tR~
	implements OnKeyListener                                       //~1730R~
{                                                                  //~1726I~
    private TextView textView;                                     //~vc1tR~
    private int  value;                                            //~1730I~
    private int  viewid;                                           //~1730I~
    private int[][] defaultMap;
    private TextFieldI listener;//~vc1tI~
                                                                   //~1726I~
    public TextField(TextFieldI Plistener,TextView Pview)          //~vc1tR~
    {                                                              //~1726I~
    	listener=Plistener;                                        //~1726R~
        textView=Pview;                                            //~vc1tR~
    	setkeylistener(Pview);                                  //~1726I~//~vc1tR~
	    defaultMap= AxeKbdDialogHW.getDefaultMap();                 //~vc1tI~
    }                                                              //~1726I~
    public static TextField setListener(TextFieldI Plistener,TextView Pview)//~1730R~//~vc1tR~
    {                                                              //~1730I~
    	TextField tf=new TextField(Plistener,Pview);//~1730R~      //~vc1tR~
    	return tf;                                                //~1730I~//~vc1tR~
    }                                                              //~1730I~
    private void setkeylistener(TextView Pv)                           //~1726I~//~vc1tR~
    {                                                              //~1726I~
        //********************                                         //~1726I~                                                             //~1726I~
        Pv.setOnKeyListener(this);                                 //~1726I~
    }                                                              //~1726I~
    public int getText()                                          //~1726I~//~vc1tR~
    {                                                              //~1726I~
    	return value;                                              //~1730R~
    }                                                              //~1726I~
    public void setText(String Ptext)                               //~1726I~//~vc1tR~
    {                                                              //~1726I~
        if (Dump.Y) Dump.println("TextField.setText text="+Ptext); //~vc1tI~
    	textView.setText(Ptext);                                   //~vc1tR~
    }                                                              //~1726I~
//****************************                                     //~1726I~
    public boolean onKey(View Pview,int Pkeycode,android.view.KeyEvent Pevent)//~v@@@I~//~1726I~
    {                                                      //~v@@@I~//~1726I~
        int action;                                //~v@@@I~       //~1726I~
        boolean rc=false;                                          //~1726I~
        //**********************                           //~v@@@I~//~1726I~
        try                                                        //~1726I~
        {                                                          //~1726I~
            action=Pevent.getAction();                          //~v@@@R~//~1726I~
            if (Dump.Y) Dump.println("TextField.Onkey action="+action+",keycode="+Pkeycode+",viewid="+Integer.toString(Pview.getId(),16)+",isfocus="+Pview.isFocused()); //~v@@@I~//~1116I~//~1726I~//~0702R~//~vc1tR~
            switch(action)                                     //~v@@@I~//~1726I~
            {                                                  //~v@@@I~//~1726I~
            case android.view.KeyEvent.ACTION_DOWN:                //~1726I~
                rc=onKeyDown(Pview,Pkeycode,Pevent);               //~1726I~
                break;                                             //~1726I~
            case android.view.KeyEvent.ACTION_UP:                  //~vc1tI~
                rc=onKeyUp(Pview,Pkeycode,Pevent);                 //~vc1tI~
                break;                                             //~vc1tI~
            default:                                           //~v@@@I~//~1726I~
            }                                                  //~v@@@I~//~1726I~
        }                                                          //~1726I~
        catch(Exception e)                                         //~1726I~
        {                                                          //~1726I~
            Dump.println(e,"TextField EditText.OnKey exception");//~1730R~//~vc1tR~
        }                                                          //~1726I~
        return rc;           //~v@@@I~                             //~1726I~
    }                                                      //~v@@@I~//~1726I~
//****************************                                     //~1726M~
    public boolean onKeyDown(View Pview,int keycode,KeyEvent ev)       //~1726M~//~vc1tR~
    {                                                          //~1113I~//~1114I~//~1726M~
    	boolean rc=false;                                          //~1726M~
    //*******************                                          //~1726M~
    	if (Dump.Y) Dump.println("TextField.keyDown code="+keycode);                 //~1114I~//~1730R~//~0702R~//~vc1tR~
        return rc;   //continue;                                   //~vc1tR~
    }                                                              //~1726M~
//****************************                                     //~vc1tI~
    public boolean onKeyUp(View Pview,int Pkeycode,KeyEvent ev)     //~vc1tI~
    {                                                              //~vc1tI~
    	boolean rc;                                                //~vc1tI~
    //*******************                                          //~vc1tI~
    	if (Dump.Y) Dump.println("TextField.keyUp code="+Pkeycode); //~vc1tI~
        boolean swPrintable=ev.isPrintingKey();                    //~vc1tI~
        rc=sendText((TextView)Pview,Pkeycode,swPrintable);          //~vc1tI~
        return rc;   //android process                             //~vc1tI~
    }                                                              //~vc1tI~
    //*************************************                        //~1726M~
    private boolean sendText(TextView Pview,int Pkeycode,boolean PswPrintable)                      //~1726M~//~vc1tR~
	{                                                              //~1726M~
    	boolean rc=false;
	    String str="";                                             //~vc1tI~
        boolean swSend=false;                                       //~vc1tI~
    	switch(Pkeycode)                                           //~vc1tI~
        {                                                          //~vc1tI~
        case KEYCODE_DEL: //backspace                              //~vc1tI~
        	swSend=true;                                           //~vc1tI~
        	break;                                                 //~vc1tI~
        case KEYCODE_ENTER:                                        //~vc1tI~
        	swSend=true;                                           //~vc1tI~
            str=(String)Pview.getText();                                   //~vc1tI~
        	break;                                                 //~vc1tI~
        default:                                                   //~vc1tI~
        	if (PswPrintable)                                       //~vc1tI~
            	if (Pkeycode<defaultMap.length)                    //~vc1tI~
                {                                                  //~vc1tI~
	            	int ch=defaultMap[Pkeycode][0];                //~vc1tI~
                    str=String.valueOf((char)ch);                  //~vc1tI~
		        	swSend=true;                                   //~vc1tI~
                }                                                  //~vc1tI~
        }                                                          //~vc1tI~
    	if (Dump.Y) Dump.println("TextField.getEditTextValue Pkeycode="+Pkeycode+",printable="+PswPrintable+",str="+str+",swSend="+swSend);//~vc1tI~
        if (swSend)                                                 //~vc1tI~
        {                                                          //~vc1tI~
        	listener.onKeyTextField(Pview,Pkeycode,str);           //+vc1tR~
        	rc=true;//~vc1tI~
        }
        return rc;//~vc1tI~
	}                                                              //~1726M~
}//class                                                           //~1726I~
