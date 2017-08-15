//CID://+vaigR~:                update#=     66                    //~vaigR~
//*************************************************************    //~va15I~
//vaig:130531 Axe:disable by ShortCut:On for direction button for update button function//~vaigI~
//vab4:120120 (Axe:BUG)bottom edge button generate duplicated key event for repeatable key(direction key)//~vab4I~
//*************************************************************    //~va15I~
package com.xe.Axe;

import java.util.ArrayList;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
//import android.widget.ToggleButton;                              //~1810R~

//******************************************************************//~1923R~
//*ButtonAction:Button Listener                                    //~1923I~
//******************************************************************//~1923I~
public class AxeButtonAction                                       //~1528R~
	implements AxeTimerI                                           //~1923I~
{                                                                  //~va15I~
	public static ArrayList<AxeButtonAction> toggleStack;         //~1814I~
	public static AxeButtonAction goneActionShortCut,goneActionCtrl,goneActionAlt,goneActionAltGr;//~1824I~
	public static boolean disableRepeatable;                       //~1923I~
	public Button buttonView;                                      //~1602R~//~1810R~
    int buttonNo;                                                  //~1602I~
    AxeButton button;                                              //~1605I~
    ButtonListener listener;                                       //~1607I~
    ButtonListenerLong listenerlong;
    ButtonListenerTouch listenertouch;//~1607I~
    public boolean isToggleOn;                                             //~1811I~
    public boolean isRepeatable;                                   //~1923I~
    public AxeTimer timer;                                         //~1923I~
    private int repeatTimeout=200;//TODO                           //~1923I~
    //*********************************************                    //~va15I~
	public AxeButtonAction(int PbuttonNo,Button Pbutton)           //~1605R~//~1810R~
    {                                                              //~va15I~
    	buttonView=Pbutton;                                        //~1602I~
        buttonNo=PbuttonNo;                                        //~1602I~
	    listener=new ButtonListener(this);                         //~1607I~
    	listenerlong=new ButtonListenerLong(this);                 //~1607I~
    	listenertouch=new ButtonListenerTouch(this);               //~1923I~
        setListener();                                             //~1602I~
        if (toggleStack==null)                                     //~1824I~
        	staticinit();                                          //~1824I~
	    toggleStack=new ArrayList<AxeButtonAction>(0);             //~1814I~
        timer=new AxeTimer(this/*AxeTimerI:callback*/,repeatTimeout,true/*repeatable*/,this/*parm*/);//~1923I~
    }                                                              //~va15I~
	public void staticinit()                                       //~1824I~
    {                                                              //~1824I~
	    toggleStack=new ArrayList<AxeButtonAction>(0);             //~1824I~
        goneActionShortCut=new AxeButtonAction(AxeButton.btnShortCut);//used when button is not layouted//~1926R~
        goneActionCtrl=new AxeButtonAction(AxeButton.btnCtrl);     //~1824I~
        goneActionAlt=new AxeButtonAction(AxeButton.btnAlt);       //~1824I~
        goneActionAltGr=new AxeButtonAction(AxeButton.btnAltGr);   //~1824I~
    }                                                              //~1824I~
	public AxeButtonAction(AxeButton Pbutton)                      //~1824I~
    {                                                              //~1824I~
	    toggleStack=new ArrayList<AxeButtonAction>(0);             //~1824I~
    }                                                              //~1824I~
	public void setButton(AxeButton Pbutton)                       //~1605I~
    {                                                              //~1605I~
        button=Pbutton;                                            //~1605I~
        Pbutton.setActionIndex(buttonNo);   //latest assigned action table index;//~1923R~
    }                                                              //~1605I~
	public void setButtonView(Button Pbtnview)                             //~1607I~
    {                                                              //~1607I~
        buttonView=Pbtnview;                                         //~1607I~//~1810R~
	    setListener();                                             //~1607I~
    }                                                              //~1607I~
//**************                                                   //~1528I~
    private void setListener()                                     //~1923R~
    {                                                              //~1528I~
        buttonView.setOnClickListener(listener);                   //~1607R~
        buttonView.setOnLongClickListener(listenerlong);                    //+1602R~//~1607R~
        buttonView.setOnTouchListener(listenertouch);              //~1923I~
    }                                                              //~1528I~
//*********************                                            //~1528I~
    public class ButtonListener implements OnClickListener         //~1528I~
    {                                                              //~1528I~
    	private AxeButtonAction action;                            //~1602R~
        public ButtonListener(AxeButtonAction Paction)             //~1602R~
        {                                                          //~1528I~
            action=Paction;                                        //~1602R~
        }                                                          //~1528I~
                                               //~1528I~
        public void onClick(View Pv)                               //~1528I~
        {                                                          //~1528I~
            try                                                    //~1810I~
            {                                                      //~1810I~
	            if (Dump.Y) Dump.println("AxeButtonAction onclick hh="+Pv.getHeight());//+vaigI~
        		if ((action.isRepeatable && !AxeButtonAction.disableRepeatable))   	//generate key(left,right,up,down)//~vab4I~
        			return;		//processed by onTouch             //~vab4I~
                action.onClickButtonAction(Pv);                 //~1602R~//~1810R~
            }                                                      //~1810I~
            catch(Exception e)                                     //~1810I~
            {                                                      //~1810I~
                Dump.println(e,"AxeButtonAction.OnClick");         //~1810I~
            }                                                      //~1810I~
        }                                                          //~1528I~

    }                                                              //~1528I~
//*********************                                            //~1528I~
    public class ButtonListenerLong implements OnLongClickListener //~1528I~
    {                                                              //~1528I~
    	private AxeButtonAction action;                            //~1602I~
        public ButtonListenerLong(AxeButtonAction Paction)         //~1602R~
        {                                                          //~1528I~
            action=Paction;                                        //~1602R~
        }                                                          //~1528I~
                                   //~1528I~
        public boolean onLongClick(View Pv)                           //~1528I~
        {                                                          //~1528I~
            try                                                    //~1810I~
            {                                                      //~1810I~
            	action.onClickButtonActionLong(Pv);                    //~1602R~//~1810R~
            }                                                      //~1810I~
            catch(Exception e)                                     //~1810I~
            {                                                      //~1810I~
                Dump.println(e,"AxeButtonAction.OnLongClick");     //~1810I~
            }                                                      //~1810I~
            return true;
        }                                                          //~1528I~
    }                                                              //~1528I~
//*********************************************************        //~1923I~
//*for repeatable event                                            //~1923I~
//*OnTouch(Down), OnLongClick, OnTouch(UP), OnClick                //~1923I~
//*********************************************************        //~1923I~
    public class ButtonListenerTouch implements OnTouchListener    //~1923I~
    {                                                              //~1923I~
    	private AxeButtonAction action;                            //~1923I~
        public ButtonListenerTouch(AxeButtonAction Paction)        //~1923I~
        {                                                          //~1923I~
            action=Paction;                                        //~1923I~
        }                                                          //~1923I~
        @Override                                                  //~1923I~
        public boolean onTouch(View Pbutton,MotionEvent Pevent)                 //~1923I~
        {                                                          //~1923I~
            try                                                    //~1923I~
            {                                                      //~1923I~
            	action.onTouch(Pevent);                            //~1923I~
            }                                                      //~1923I~
            catch(Exception e)                                     //~1923I~
            {                                                      //~1923I~
                Dump.println(e,"AxeButtonAction.OnTouch");         //~1923I~
            }                                                      //~1923I~
            return false;                                          //~1923I~
        }                                                          //~1923I~
   }                                                              //~1923I~
//*************                                                    //~1528I~
	public void onClickButtonAction(View Pview)                    //~1602R~
    {                                                              //~1528I~
        boolean active;                                            //~1810I~
    //********************                                         //~1810I~
    	if (Dump.Y) Dump.println("onClickButtonAction view="+Pview.toString()+",Button="+button.name);//~1608R~
        switch(button.buttonId)                                   //~1608I~
        {                                                          //~1608I~
        case AxeButton.BUTTON_LEFT:                                //~1920I~
        	AxeG.axeKeyAction.gdkKey(AxeKeyValue.GDK_Left);        //~1920R~
        	break;                                                 //~1920I~
        case AxeButton.BUTTON_RIGHT:                               //~1920I~
        	AxeG.axeKeyAction.gdkKey(AxeKeyValue.GDK_Right);       //~1920R~
        	break;                                                 //~1920I~
        case AxeButton.BUTTON_UP:                                  //~1920I~
        	AxeG.axeKeyAction.gdkKey(AxeKeyValue.GDK_Up);          //~1920R~
        	break;                                                 //~1920I~
        case AxeButton.BUTTON_DOWN:                                //~1920I~
        	AxeG.axeKeyAction.gdkKey(AxeKeyValue.GDK_Down);        //~1920R~
        	break;                                                 //~1920I~
        case AxeButton.BUTTON_IM:                                  //~1608I~
        	AxeG.axeKeyAction.showKbd();                           //~1608I~
        	break;                                                 //~1608I~
        case AxeButton.BUTTON_IMP:                                 //~1830I~
        	AxeG.axeKeyAction.showKbdP();                          //~1830I~
        	break;                                                 //~1830I~
        case AxeButton.BUTTON_SHIFT:                               //~1608I~
        	active=AxeG.axeKeyAction.toggleModifier(AxeKeyValue.KBF_SHIFT);//~1608I~//~1810R~
            activateButton(active);                          //~1810R~//~1811R~
	        stackToggle(active);                                  //~1814I~
        	break;                                                 //~1608I~
        case AxeButton.BUTTON_CTL:                             //~1608I~
        	active=AxeG.axeKeyAction.toggleModifier(AxeKeyValue.KBF_CONTROL);//~1608I~//~1810R~
            activateButton(active);                          //~1810R~//~1811R~
	        stackToggle(active);                                  //~1814I~
        	break;                                                 //~1608I~
        case AxeButton.BUTTON_ALT:                                 //~1608I~
        	active=AxeG.axeKeyAction.toggleModifier(AxeKeyValue.KBF_ALT);//~1608I~//~1810R~
            activateButton(active);                          //~1810R~//~1811R~
	        stackToggle(active);                                  //~1814I~
        	break;                                                 //~1608I~
        case AxeButton.BUTTON_ALTGR:                               //~1612I~
        	active=AxeG.axeKeyAction.toggleAltGr();                       //~1612I~//~1810R~
            activateButton(active);                          //~1810R~//~1811R~
	        stackToggle(active);                                  //~1814I~
        	break;                                                 //~1612I~
        case AxeButton.BUTTON_SHORTCUT:                            //~1608I~
        	active=AxeG.axeKeyAction.toggleShortcut()!=0;             //~1608I~//~1810R~
            activateButton(active);                          //~1810R~//~1811R~
	        stackToggle(active);                                  //~1814I~
        	break;                                                 //~1608I~
        case AxeButton.BUTTON_CAPSLOCK:                            //~1608I~
        	active=AxeG.axeKeyAction.toggleCapslock();                    //~1608I~//~1810R~
            activateButton(active);                          //~1810R~//~1811R~
        	break;                                                 //~1608I~
        case AxeButton.BUTTON_RCTL:                                //~1609I~
        	AxeG.axeKeyAction.enterControlR();                     //~1609I~
        	break;                                                 //~1609I~
        case AxeButton.BUTTON_USER:                                //~1613I~
        	AxeG.axeKeyAction.userKeyButton(button);               //~1613I~
        	break;                                                 //~1613I~
        }                                                          //~1608I~
    }                                                              //~1528I~
//*************************************************************    //~va15I~//~1824M~
//*from AxeKeyAction:terminal button used as Buttan Action         //~va15I~//~1824M~
//*************************************************************    //~va15I~//~1824M~
    public static void onTerminalButtonKey(AxeButton Pbutton)                              //~va15I~//~1824I~
    {                                                              //~va15I~//~1824M~
        boolean active=false;                                            //~1824I~
        AxeButtonAction goneaction=null;                           //~1824I~
    //*********************
        int actionidx=Pbutton.actionIndex;//~va15I~//~1824M~
        if (Dump.Y) Dump.println("AxeButton OnTerminalButtonKey buttonid="+Pbutton.buttonId+",name="+Pbutton.name);//~va15I~//~1824M~
        AxeButtonAction action=AxeButtonLayout.getAction(actionidx,Pbutton);//~va15I~//~1824M~
        if (action!=null)                                          //~1824I~
        {                                                          //~1824I~
        	Button view=action.buttonView;                                //~1824I~
			action.onClickButtonAction(view);                      //~1824I~
            return;                                                //~1824I~
    	}                                                          //~1824I~
        switch(Pbutton.buttonId)                                    //~1824I~
        {                                                          //~1824I~
        case AxeButton.BUTTON_CTL:                                 //~1824I~
        	goneaction=goneActionCtrl;                             //~1824I~
        	active=AxeG.axeKeyAction.toggleModifier(AxeKeyValue.KBF_CONTROL);//~1824I~
        	break;                                                 //~1824I~
        case AxeButton.BUTTON_ALT:                                 //~1824I~
        	goneaction=goneActionAlt;                              //~1824I~
        	active=AxeG.axeKeyAction.toggleModifier(AxeKeyValue.KBF_ALT);//~1824I~
        	break;                                                 //~1824I~
        case AxeButton.BUTTON_ALTGR:                               //~1824I~
        	goneaction=goneActionAltGr;                            //~1824I~
        	active=AxeG.axeKeyAction.toggleAltGr();                //~1824I~
        	break;                                                 //~1824I~
        case AxeButton.BUTTON_SHORTCUT:                            //~1824I~
        	goneaction=goneActionShortCut;                         //~1824I~
        	active=AxeG.axeKeyAction.toggleShortcut()!=0;          //~1824I~
        	break;                                                 //~1824I~
        case AxeButton.BUTTON_IM:                                  //~1831I~
        	AxeG.axeKeyAction.showKbd();                           //~1831I~
        	break;                                                 //~1831I~
        case AxeButton.BUTTON_IMP:                                 //~1831I~
        	AxeG.axeKeyAction.showKbdP();                          //~1831I~
        	break;                                                 //~1831I~
        }                                                          //~1824I~
        if (goneaction!=null)                                      //~1824I~
        {                                                          //~1824I~
            goneaction.activateButton(active);                     //~1824I~
	        goneaction.stackToggle(active);                        //~1824I~
        }                                                          //~1824I~
    }                                                              //~1824I~
//*************                                                    //~1810I~
	public void activateButton(boolean Pactive)         //~1810R~  //~1811R~
    {                                                              //~1810I~
    	if (Dump.Y) Dump.println("activateButton active="+Pactive);//~1810R~
		if (buttonView!=null)	//!goneButton                      //~1824I~
        {                                                          //~1824I~
			if (Pactive)                                               //~1810I~//~1824R~
				buttonView.setBackgroundResource(button.backgroundRidActive);//~1810R~//~1811R~//~1824R~
        	else                                                       //~1810I~//~1824R~
				buttonView.setBackgroundResource(button.backgroundRid);     //~1810R~//~1811R~//~1824R~
        }                                                          //~1824I~
        isToggleOn=Pactive;    	                                   //~1811I~
    }                                                              //~1810I~
//*********************************                                //~1814I~
//*stack toggle status to reset                                    //~1814I~
//********************************                                 //~1814I~
	public void stackToggle(boolean Pactive)                       //~1814I~
    {                                                              //~1814I~
		if (Pactive)                                               //~1814I~
        {                                                          //~1814I~
			toggleStack.add(this);                                 //~1814I~
            if (Dump.Y) Dump.println("AxeButtonAction toggle On stackctr="+toggleStack.size());//~1814I~
        }                                                          //~1814I~
        else                                                       //~1814I~
        {                                                          //~1814I~
			toggleStack.remove(this);                              //~1814I~
            if (Dump.Y) Dump.println("AxeButtonAction toggle Off stackctr="+toggleStack.size());//~1814I~
        }                                                          //~1814I~
    }                                                              //~1814I~
    //*************************************                        //~1814M~
    public static void resetToggle()                               //~1814I~
    {                                                              //~1814M~
     	AxeButtonAction btnAction;                                 //~1814M~
    //***********************                                      //~1814M~
    	if (Dump.Y) Dump.println("resetToggleButton");             //~1814M~
        int sz=toggleStack.size();                                 //~1814R~
        if (sz==0)                                                 //~1814I~
        	return;                                                //~1814I~
        for (int ii=0;ii<sz;ii++)                                  //~1814I~
        {                                                          //~1814M~
        	btnAction=toggleStack.get(ii);                         //~1814I~
        	if (btnAction.isToggleOn)                              //~1814I~
            	btnAction.activateButton(false);                   //~1814M~
        }                                                          //~1814M~
        toggleStack.clear();                                       //~1814I~
    }                                                              //~1814M~
//*************                                                    //~1604I~
	public void onClickButtonActionLong(View Pview)                //~1602R~
    {                                                              //~1528I~

    	if (Dump.Y) Dump.println("onClickButtonActionLong view="+Pview.toString()+",Button="+button.name);//~1608R~
//      if (isRepeatable && !disableRepeatable)   	//generate key(left,right,up,down)//~1923I~//~vaigR~
        if (isRepeatable                                           //~vaigI~
        &&  (!disableRepeatable && AxeG.axeKeyAction.getShortcutStatus()==0)//~vaigI~
        )                                                          //~vaigI~
        	return;                                                //~1923I~
    	AxeDlgBtnUpdate.show(this);                                //~1605R~
    }                                                              //~1528I~
//********                                                         //~1923I~
    public void activateRepeatable(boolean Prepeatable)            //~1923I~
    {                                                              //~1923I~
        if (Dump.Y) Dump.println("AxeButtonAction activateRepeatable repeatable="+Prepeatable);//~1923I~
        isRepeatable=Prepeatable;                                  //~1923I~
    }                                                              //~1923I~
//********                                                         //~1923I~
    public void onTouch(MotionEvent Pevent)                        //~1923I~
    {                                                              //~1923I~
        int action = Pevent.getAction();                           //~1923I~
        if (Dump.Y) Dump.println("AxeButtonAction aonTouch action=="+action+",repeatable="+isRepeatable);//~1923I~
        if (!(isRepeatable && !disableRepeatable))   	//generate key(left,right,up,down)//~1923I~
        	return;		//process onClick,onLongClick              //~1923I~
        switch(action)                                             //~1923I~
        {                                                          //~1923I~
        case MotionEvent.ACTION_DOWN:                              //~1923I~
			onClickButtonAction(buttonView);                       //~1923I~
        	if (Dump.Y) Dump.println("AxeButtonAction onTouch start timer");//~1923I~
	        timer.start();                                         //~1923I~
            break;                                                 //~1923I~
        case MotionEvent.ACTION_MOVE:                              //~1923I~
            break;                                                 //~1923I~
        case MotionEvent.ACTION_UP:                                //~1923I~
	        timer.stop();                                          //~1923I~
            break;                                                 //~1923I~
        case MotionEvent.ACTION_CANCEL:                            //~1923I~
	        timer.stop();                                          //~1923I~
            break;                                                 //~1923I~
        }                                                          //~1923I~
    }                                                              //~1923I~
     @Override
    public void onTimerExpired(AxeTimer Ptimer,int Pctr,Object Pparm)//~1923I~
    {                                                              //~1923I~
        if (Dump.Y) Dump.println("AxeButtonAction onTimerExpired ctr="+Pctr);//~1923I~
		onClickButtonAction(buttonView);                           //~1923I~
    }                                                              //~1923I~
}//class                                                           //~va15R~
