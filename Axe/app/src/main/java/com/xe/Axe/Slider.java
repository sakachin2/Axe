package com.xe.Axe;                                                //~1726I~

import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
                                                                   //~1726I~
//************************************                             //~1726I~
//*support SetupColor                *                             //~1726I~
//************************************                             //~1726I~
                                                                   //~1726I~
public class Slider                                                //~1726I~
	implements OnSeekBarChangeListener, EditTextListener           //~1730R~
{                                                                  //~1726I~
	public static final int HORIZONTAL   =1;                       //~1726I~
	public static final int VERTICAL     =2;                       //~1726I~
                                                                   private SeekBar seekbar=null;                                  //~1726I~
    private SliderListener listener;                               //~1726R~
    private EditText edittext;                                     //~1726I~
    private View dialog;                                           //~1726I~
    private int  sliderid;                                         //~1726I~
                                                                   //~1726I~
    public Slider(SliderListener Plistener,int PidEditText,int PidSeekbar,int PinitValue,int Pmin,int Pmax)        //~1213I~//~1726R~
    {                                                              //~1726I~
    	listener=Plistener;                                        //~1726R~
    	dialog=((AxeDialog)Plistener).layoutView;                                            //~1726I~
        sliderid=PidSeekbar;                                       //~1726I~
    	seekbar=(SeekBar)dialog.findViewById(PidSeekbar);         //~1726I~
    	edittext=(EditText)dialog.findViewById(PidEditText);      //~1726I~
    	seekbar.setProgress(PinitValue);                           //~1726I~
        edittext.setText(Integer.toString(PinitValue));            //~1726I~
    	seekbar.setOnSeekBarChangeListener(this); //request callback this//~1726I~
//    	setkeylistener(edittext);                                  //~1730R~
  		EditTextField.setListener(this,edittext);                  //~1730R~
    }                                                              //~1726I~
    //***********************************************              //+0704I~
    @Override                                                      //+0704I~
    public void beforeTextChangedETF(EditText PeditText, String Ptext, int start, int count, int after){};//+0704I~
    @Override                                                      //+0704I~
    public void onTextChangedETF(EditText PeditText,String Ptext,int start,int before,int count){};//+0704I~
    @Override                                                      //+0704I~
    public void afterTextChangedETF(EditText PeditText,String Ptext){};//+0704I~
    @Override                                                      //~0702I~
    public void onEditTextChanged(int Pviewid,String Pvalue)       //~0702I~
    {                                                              //~0702I~
    }                                                              //~0702I~
    @Override                                                      //~1730I~
    public void onEditTextChanged(int Pviewid,int Pvalue)         //~1730I~
    {                                                              //~1730I~
		if (Dump.Y) Dump.println("Slider EditText cahnged value="+Pvalue);//~1730I~
        onProgressChanged(seekbar,Pvalue,false);                   //~1730I~
    }                                                              //~1730I~
//    private void setkeylistener(View Pv)                         //~1730R~
//    {                                                            //~1730R~
//        //********************                                         //~1726I~//~1730R~
//        Pv.setOnKeyListener(this);                               //~1730R~
//        Pv.setFocusableInTouchMode(true);   //DOC says "FocusableInTouch then Focusable"//~1730R~
//        Pv.setFocusable(true);                                   //~1730R~
//        addFocusChangeListener(Pv);                              //~1730R~
//    }                                                            //~1730R~
//    private void addFocusChangeListener(View Pv)                 //~1730R~
//    {                                                            //~1730R~
//        if (Dump.Y) Dump.println("Slider addFocuschange Listener viewid="+Integer.toString(Pv.getId(),16));//~1730R~
//        Pv.setOnFocusChangeListener(                             //~1730R~
//            new OnFocusChangeListener()                          //~1730R~
//                {                                                //~1730R~
////                  @Override                                    //~1730R~
//                    public void onFocusChange(View Pview,boolean Phasfocus)//~1730R~
//                    {                                            //~1730R~
//                        if (Dump.Y) Dump.println("focus changed "+Phasfocus+",viewid="+Integer.toString(Pview.getId(),16));//~1730R~
//                        if (!Phasfocus) //lost focus             //~1730R~
//                            getEditTextValue(edittext);          //~1730R~
//                    }                                            //~1730R~
//                }                                                //~1730R~
//                                );                               //~1730R~
//    }                                                            //~1730R~
    public int getValue()                                          //~1726I~
    {                                                              //~1726I~
    	return seekbar.getProgress();                              //~1726I~
    }                                                              //~1726I~
    public void setValue(int Pvalue)                               //~1726I~
    {                                                              //~1726I~
    	seekbar.setProgress(Pvalue);                               //~1726I~
    }                                                              //~1726I~
//****************************                                     //~1726I~
    @Override                                                      //~1726I~
    public void onProgressChanged(SeekBar PseekBar,int Pvalue,boolean PfromUser)//~1726I~
    {                                                              //~1726I~
    	if (Dump.Y) Dump.println("Slider OnProgressChanged value="+Pvalue+",fromUser="+PfromUser);//~1730R~
        edittext.setText(Integer.toString(Pvalue));                //~1726I~
        listener.onSliderChanged(sliderid,Pvalue);	//notify to dialog to draw sample panel//~1726I~
    }                                                              //~1726I~
//****************************                                     //~1726I~
    @Override                                                      //~1726I~
    public void onStartTrackingTouch(SeekBar PseekBar)             //~1726I~
    {                                                              //~1726I~
    }                                                              //~1726I~
    @Override                                                      //~1726I~
    public void onStopTrackingTouch(SeekBar PseekBar)              //~1726I~
    {                                                              //~1726I~
    	if (Dump.Y) Dump.println("Slider OnOnStop value="+getValue());//~1726I~
    }                                                              //~1726I~
//    @Override                                                    //~1730R~
//    public boolean onKey(View Pview,int Pkeycode,android.view.KeyEvent Pevent)//~v@@@I~//~1730R~
//    {                                                      //~v@@@I~//~1730R~
//        int action;                                //~v@@@I~     //~1730R~
//        boolean rc=false;                                        //~1730R~
//        //**********************                           //~v@@@I~//~1730R~
//        try                                                      //~1730R~
//        {                                                        //~1730R~
//            if (Dump.Y) Dump.println("Onky keycode="+Pkeycode+",viewid="+Integer.toString(Pview.getId(),16)+",isfocus="+Pview.isFocused()); //~v@@@I~//~1116I~//~1730R~
//            action=Pevent.getAction();                          //~v@@@R~//~1730R~
//            switch(action)                                     //~v@@@I~//~1730R~
//            {                                                  //~v@@@I~//~1730R~
//            case android.view.KeyEvent.ACTION_DOWN:              //~1730R~
//                rc=onKeyDown(Pview,Pkeycode,Pevent);             //~1730R~
//                break;                                           //~1730R~
//            default:                                           //~v@@@I~//~1730R~
//            }                                                  //~v@@@I~//~1730R~
//        }                                                        //~1730R~
//        catch(Exception e)                                       //~1730R~
//        {                                                        //~1730R~
//            Dump.println(e,"Slider EditText.OnKey exception");   //~1730R~
//        }                                                        //~1730R~
//        return rc;           //~v@@@I~                           //~1730R~
//    }                                                      //~v@@@I~//~1730R~
////****************************                                   //~1730R~
//    public boolean onKeyDown(View v,int keycode,KeyEvent ev)     //~1730R~
//    {                                                          //~1113I~//~1114I~//~1730R~
//        boolean rc=false;                                        //~1730R~
//    //*******************                                        //~1730R~
//        if (Dump.Y) Dump.println("Slider EditText keyDown code="+keycode);                 //~1114I~//~1730R~
//        if (keycode==KeyEvent.KEYCODE_ENTER)                     //~1730R~
//        {                                                        //~1730R~
//            getEditTextValue(v);                                 //~1730R~
//            rc=true;                                             //~1730R~
//        }                                                        //~1730R~
//        if (Dump.Y) Dump.println("keyDown rc="+rc);              //~1730R~
//        return rc;   //android process                      //~1113R~//~1114I~//~1730R~
//    }                                                            //~1730R~
//    //*************************************                      //~1730R~
//    private void getEditTextValue(View Pview)                    //~1730R~
//    {                                                            //~1730R~
//        String text=((EditText)Pview).getText().toString();      //~1730R~
//        int value;                                               //~1730R~
//        try                                                      //~1730R~
//        {                                                        //~1730R~
//            value=Integer.parseInt(text);                        //~1730R~
//        }                                                        //~1730R~
//        catch (NumberFormatException e)                          //~1730R~
//        {                                                        //~1730R~
//            value=-1;                                            //~1730R~
//        }                                                        //~1730R~
//        if (value<0||value>255)                                  //~1730R~
//            return;                                              //~1730R~
//        setValue(value);                                         //~1730R~
//        if (Dump.Y) Dump.println("Slider EditText value="+value);//~1730R~
//        onProgressChanged(seekbar,value,false);                  //~1730R~
//    }                                                            //~1730R~
}//class                                                           //~1726I~
