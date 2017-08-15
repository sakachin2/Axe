package com.xe.Axe;                                                //~1726I~

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;

//************************************                             //~1726I~
//*support SetupColor                *                             //~1726I~
//************************************                             //~1726I~
                                                                   //~1726I~
public class RadioButtons                                          //~1731R~
	implements OnClickListener                                     //~1731R~
{                                                                  //~1726I~
    private final static int MAXBUTTONS=4;                         //~1731I~
    private RadioButton[] buttons=new RadioButton[MAXBUTTONS];     //~1731R~
    private int btnctr;                                            //+1731R~
    private RadioButton active;                                    //~1731I~
    private RadioButtonsListener listener;                         //~1731I~
                                                                   //~1726I~
    public RadioButtons(RadioButtonsListener Plistener)            //~1731I~
    {                                                              //~1731I~
    	listener=Plistener;                                        //~1731I~
    }                                                              //~1731I~
    public void add(ViewGroup Playout,int Pbtnid)                  //~1731R~
    {                                                              //~1726I~
    	if (btnctr==MAXBUTTONS)                                    //~1731I~
        	return;                                                //~1731I~
    	RadioButton rb=(RadioButton)Playout.findViewById(Pbtnid);  //~1731I~
        rb.setOnClickListener(this);                               //~1731I~
        buttons[btnctr++]=rb;                                      //~1731R~
    }                                                              //~1726I~
    public void check(int Pid)                                    //~1731I~
    {                                                              //~1731I~
    	for (int ii=0;ii<btnctr;ii++)                              //~1731I~
        	if (Pid==buttons[ii].getId())                          //~1731I~
	        	setChecked(buttons[ii]);                           //~1731I~
    }                                                              //~1731I~
    @Override                                                      //~1731I~
    public void onClick(View Pview)                                //~1731R~
    {                                                              //~1730I~
    	setChecked((RadioButton)Pview);                            //~1731I~
    }                                                              //~1730I~
    public void setChecked(RadioButton Pbtn)                       //~1731I~
    {                                                              //~1731I~
        int id=Pbtn.getId();                                       //~1731I~
    	if (active!=null)                                          //~1731I~
        {                                                          //~1731I~
        	if (id==active.getId())                                //~1731I~
        		return;                                            //~1731I~
        	active.setChecked(false);                              //~1731I~
        }                                                          //~1731I~
        active=Pbtn;                                 //~1731I~
        active.setChecked(true);                                   //~1731I~
        if (Dump.Y) Dump.println("RadioButtons setChecked id="+Integer.toString(active.getId()));//~1731I~
        listener.onRadioButtonsChanged(id);                        //~1731I~
    }                                                              //~1731I~
}//class                                                           //~1726I~
