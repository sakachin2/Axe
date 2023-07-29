//CID://+vc45R~:       update#=  141109                            //+vc45R~
//*************************************************************    //~va15I~
//vc45 2022/03/25 deprecated; Java9 new Integer,Boolean,Double-->valueOf//+vc45I~
//vc2Q 2020/09/08 change font size by pinch action                 //~v2cQI~
//vaxe:140707 (Axe)shortcut to filedialog from titlebar touch      //~vaxeI~
//vaim:130601 send mouse rbutton event by long press on AxeScreen  //~vaimI~
//vaai:111227 (Axe:BUG)flick operation generate primekey+flickkey  //~1C28I~
//*************************************************************    //~va15I~
package com.xe.Axe;                                                //~va15I~

import android.view.GestureDetector;
import android.view.ScaleGestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.ViewParent;
import android.view.View;

public class AxeMouse                                              //~1621R~
    	implements AxeTimerI                                       //~1924I~
    	,View.OnTouchListener                                      //~1924I~
{   
    public  static final int MK_LBUTTON=AxeKeyValue.GDK_BUTTON1_MASK;//1<<8  0100//~1A03R~//~1A04R~
    public  static final int MK_LBUTTON2=AxeKeyValue.GDK_BUTTON4_MASK;//1<<11  0800; use for lb dblclick//~1A04I~
    public  static final int MK_LBUTTON3=AxeKeyValue.GDK_BUTTON5_MASK;//1<<12  1000; use for lb drag//~1A05I~
    public  static final int MK_RBUTTON=AxeKeyValue.GDK_BUTTON3_MASK;//1<<10 0400//~1A03I~
    private static final int MK_SHIFT  =AxeKeyValue.GDK_SHIFT_MASK;//~1926I~
    private static final int MK_CONTROL=AxeKeyValue.GDK_CONTROL_MASK;//~1926I~
    private static final int MK_ALT    =AxeKeyValue.GDK_MOD1_MASK; //~1926I~
    private static final int ACTION_DBLCLICK=4;                    //~1A04I~
    private static final int ACTION_DRAG=5;                        //~1A04I~
    private static final int BTNNO_R=3;                            //~1927I~
                                                                   //~1926I~
	private int mSwipeThreshold=100;	//minimum swipe velocity //~1924I~
                                                                   //~1924I~
    private long downTime,lastTime;                                //~1924I~
    private int downPosX,downPosY,movePosX,movePosY;               //~1924R~
    private int downState,moveState;                               //~1926I~
    private boolean swSendDown,swMove,swSwipe;                             //~1924R~
    private boolean swLongPress,swMovedOnce,swLongPressSent;       //~vaimR~
	private GestureDetector mGestureDetector;                      //~1924I~
	private SimpleOnScaleGestureListener mSGL;
	private ScaleGestureDetector mSGD;//~v2cQI~
	private SwipeTracker mSwipeTracker = new SwipeTracker();       //~1924I~
	private boolean mDisambiguateSwipe=true;                       //~1924I~
    private AxeScreen screenView;                                  //~1924I~
    private ViewParent screenParent;                                     //~1924I~
    private AxeTimer axeTimer;                                     //~1924I~
    private AxeTimer longPressTimer;                               //~vaimI~
    private int travelX,travelY;                                   //~1924I~
    private int lastPosX,lastPosY,lastMetaState;                                 //~1A02I~//~1A03R~
    private int status;                                            //~1926I~
    private boolean dragging;                                      //~1A05I~
	private float scaleFactor;	// >1:Zoom out                     //~v2cQI~
//**************************************************               //~1924I~
	public AxeMouse()                                              //~1621R~
    {                                                              //~va15I~
    }                                                              //~va15I~
	public void initScreenMouse(AxeScreen PaxeScreen)                   //~1924I~
    {                                                              //~1924I~
    	screenView=PaxeScreen;                                     //~1924I~
    	screenParent=screenView.getParent();                       //~1924I~
	    screenView.setOnTouchListener(this);//not ontouchevent but ontouch//~1924I~
    	initGestureDetector();	//SWIPE                            //~1924I~
    	initSGL();	//pinch                                        //~v2cQI~
        axeTimer=new AxeTimer(this/*callback*/,AxeG.swipeTimeout/*milisec delay*/,false/*repeat*/,null/*parameter*/);//~1924I~//~1A03R~
//      longPressTimer=new AxeTimer(this/*callback*/,AxeG.longPressTimeout/*milisec delay*/,false/*repeat*/,new Integer(1)/*parameter*/);//~vaimR~//+vc45R~
        longPressTimer=new AxeTimer(this/*callback*/,AxeG.longPressTimeout/*milisec delay*/,false/*repeat*/,Integer.valueOf(1)/*parameter*/);//+vc45I~
        travelX=travelY=Math.min(AxeG.screenW,AxeG.screenH)*AxeG.swipeTravel/100;   //minimum swipe travel distance %//~1A03R~
    }                                                              //~1924I~
//**************************************************               //~va15I~
    public boolean onTouchMain(MotionEvent Pevent)                 //~1922R~
    {                                                              //~va15I~
    	boolean rc=false;                                          //~1621I~
        int xx,yy,action,rctitle;                                       //~1725R~
    //*****************:                                           //~1621I~
        xx=(int)Pevent.getX();                             //~@@@@I~//~1212I~//~1621R~
        yy=(int)Pevent.getY();                             //~@@@@I~//~1212I~//~1621R~
        action=Pevent.getAction();                              //~@@@@R~//~1212I~//~1621R~
        if (Dump.Y) Dump.println("Axe:OnTouchMain action="+action+",x="+xx+",y="+yy);//~1621R~
        if ((rctitle=onTouchTitlebar(action,xx,yy))!=0)                  //~1725I~
            return rctitle==1;	//1:processed,-1:not processed         //~1725I~
//        switch(action)                                           //~1922R~
//        {                                                        //~1922R~
//        case MotionEvent.ACTION_OUTSIDE:                         //~1922R~
//            break;                                               //~1922R~
//        case MotionEvent.ACTION_UP:                              //~1922R~
//        case MotionEvent.ACTION_DOWN:                            //~1922R~
//        case MotionEvent.ACTION_MOVE:                            //~1922R~
//            rc=AxeJNI.onTouch(action,xx,yy);                     //~1922R~
//            break;                                               //~1922R~
//        }                                                        //~1922R~
        return rc;                                                 //~1621I~
    }                                                              //~va15I~
//**************************************************               //~1725I~
//*chk titlebar touch                                              //~1725I~
//*rc:1:titlebar touch processed,-1:ignored,0:continue process     //~1725I~
//**************************************************               //~1725I~
    private int onTouchTitlebar(int Paction,int Pxx,int Pyy)       //~1725I~
    {                                                              //~1725I~
        int xx,yy,xxmenu,titlebarheight,titlebarpos;               //~1725I~
    //****************                                             //~1725I~
    	if (Dump.Y) Dump.println("OnTouchTitlebar action="+Paction+" point=("+Pxx+","+Pyy+")");//~1725I~
        if (Pyy<AxeG.titleBarTop)                                   //~1725I~
        	return -1;                                             //~1725I~
        if (Pyy>=AxeG.titleBarBottom)                               //~1725I~
        	return 0;                                              //~1725I~
//        if (Paction!=MotionEvent.ACTION_UP)                      //~1927R~
//            return -1;                                           //~1927R~
//        AxeG.axeMenu.showContextMenu(AxeMenu.MT_TITLEBAR);       //~1927R~
                                                                   //~vaxeI~
      if (Pxx>AxeG.displayW*2/3)                                   //~vaxeI~
      {                                                            //~vaxeI~
		if (Paction==MotionEvent.ACTION_UP)                        //~vaxeI~
      		AxeG.axeMenu.onFileOpen();                             //~vaxeR~
        else                                                       //~vaxeI~
        	return 0;                                              //~vaxeI~
      }                                                            //~vaxeI~
      else                                                         //~vaxeI~
        showContextMenu(Paction);                                  //~1927I~
        return 1;
    }                                                              //~1725I~
//**********************************************************       //~1927I~
    private void showContextMenu(int Paction)                      //~1927I~
    {                                                              //~1927I~
    //****************                                             //~1927I~
    	if (Dump.Y) Dump.println("OnTouchTitlebar showContextMenu action="+Paction);//~1927I~
    	sendEvent(Paction,BTNNO_R,0/*mod status*/,AxeG.screenW/2,AxeG.screenH/2);	//position is no meaning for right button//~1927I~
    }                                                              //~1927I~
//**********************************************************       //~1924I~
    @Override                                                      //~1924I~
    public boolean onTouch(View view,MotionEvent Pevent)   //onTouch enables to schedule onclick,onlongclick//~1924I~
    {                                                              //~1924I~
    	boolean rc=true;         //enable to schedule following event(ACTION_UP)//~1924R~
    //********************                                         //~1924I~
        try                                                        //~1924I~
        {                                                          //~1924I~
            rc=AxeG.axeMouse.onTouchScreen(Pevent);                //~1924R~
        }                                                          //~1924I~
        catch(Exception e)                                         //~1924I~
        {                                                          //~1924I~
            Dump.println(e,"AxeMouse.OnTouchEvent");              //~1924I~//~v2cQR~
        }                                                          //~1924I~
        if (Dump.Y) Dump.println("AxeMouse screen-ontouch rc="+rc+",action="+Pevent.getAction());//~1924R~
        return rc;                                                 //~1924I~
    }                                                              //~1924I~
//**********************************************************       //~1924I~
//*to get android Canvas and get control of onDraw                 //~1924I~
//*OnTouch-Down-->OnTouchEvent-->LongClick-->onTouch-Move-->onTouch-Up-->onTouchEvent-->onClick//~1924I~
//*if onTouchEvent listener is overridden onclick and onlongclick is not scheduled//~1924R~
//*if onTouchEvent return false following listener is not scheduled//~1924R~
//*after ACTION_CANCEL(moved out of view) ACTION_UP is not scheduled//~1924I~
//*this is yes for ontouch and ontouchevent                        //~1924I~
//**********************************************************       //~1924I~
    public boolean onTouchScreen(MotionEvent Pevent)               //~1924R~
    {                                                              //~1922I~
    	boolean rc=true;         //enable to schedule following event(ACTION_UP)//~1924I~
        int xx,yy,action,metastate;                        //~1924R~
    //**************************                                   //~1924R~
        action=Pevent.getAction();                                 //~1924R~
        xx=(int)Pevent.getX();                                     //~1924R~
        yy=(int)Pevent.getY();                                     //~1924R~
        metastate=getMetaState(Pevent);             //~1926R~
        int ptrs=Pevent.getPointerCount();                             //~1924I~
        if (Dump.Y) Dump.println("Canvas:onTouchScreen action="+action+",ptrs="+ptrs+",x="+xx+",y="+yy+",meta="+Integer.toHexString(metastate));//~1924R~
    	mSGD.onTouchEvent(Pevent);                                 //~v2cQR~
        long eventTime=Pevent.getEventTime();                      //~1924I~
        lastTime=eventTime;                                        //~1924I~
        lastPosX=xx;                                               //~1A02I~
        lastPosY=yy;                                               //~1A02I~
        lastMetaState=metastate;                                   //~1A03I~
        screenParent.requestDisallowInterceptTouchEvent(true);  //don't schedule Action_CANCEL by out of view range//~1924I~
                                                                   //~1924I~
        if (action == MotionEvent.ACTION_DOWN)                     //~1924I~
        	mSwipeTracker.clear();                                 //~1924I~
        mSwipeTracker.addMovement(Pevent);                         //~1924I~
                                                                   //~1924I~
        if (action==MotionEvent.ACTION_DOWN||swSendDown==false) //1st or after timeout//~1925R~
            if (mGestureDetector.onTouchEvent(Pevent))                 //~1924I~
            {                                                      //~1924I~
            	swSwipe=true;                                      //~1924I~
                axeTimer.stop();                                   //~1924I~
                if (Dump.Y) Dump.println("onTouchEvent gestureDetector rc="+rc);//~1924R~
                return rc;	//if false onTouchEvent(onTouchMain is scheduled),so avoid to schedule ACTION_UP use swSwipe//~1924R~
            }                                                      //~1924I~
        switch (action)                                            //~1924I~
        {                                                          //~1924I~
        case MotionEvent.ACTION_DOWN:                              //~1924I~
            downPosX=xx;                                           //~1924I~
            downPosY=yy;                                           //~1924I~
            downTime=eventTime;                                    //~1924I~
            downState=metastate;                                   //~1926I~
            swSendDown=false;                                      //~1924I~
            swMove=false;
            swSwipe=false;//~1924I~
            swLongPress=false;                                     //~vaimI~
            swLongPressSent=false;                                 //~vaimI~
            swMovedOnce=false;                                     //~vaimI~
            settimeout(AxeG.swipeTimeout);                             //~1924I~//~1A03R~
            break;                                                 //~1924I~
        case MotionEvent.ACTION_MOVE:                              //~1924I~
            if (Dump.Y) Dump.println("ACTION MOVE");               //~1924I~
            if (swSwipe)                                           //~1925I~
            	break;                                             //~1925I~
            if (!isMovedOverCell(downPosX,downPosY,xx,yy))         //~vaimI~
            {                                                      //~vaimI~
             	if (!swMovedOnce && swLongPress)	//timeout      //~vaimI~
                {                                                  //~vaimI~
		            if (Dump.Y) Dump.println("ACTION MOVE LongPress Timeout elapsed="+(eventTime-downTime));//~vaimI~
                    swLongPress=false;                             //~vaimI~
                    swLongPressSent=true;                          //~vaimI~
                    sendEventRBDown(metastate,xx,yy);              //~vaimI~
                    break;                                         //~vaimI~
                }                                                  //~vaimI~
            }                                                      //~vaimI~
            else                                                   //~vaimI~
            	swMovedOnce=true;                                  //~vaimI~
            if (swSendDown)                                        //~1925I~
            {                                                      //~1925I~
	            if (Dump.Y) Dump.println("ACTION MOVE after TIMEOUT");//~1926I~
			    sendEvent(action,1,downState,xx,yy);	//action-up//~1927R~
            	break;                                             //~1925I~
            }                                                      //~1925I~
            swMove=true;     //send MOVE at UP                     //~1925R~
            movePosX=xx;                                           //~1924I~
            movePosY=yy;                                           //~1924I~
            moveState=metastate;                                   //~1926I~
            break;                                                 //~1924I~
        case MotionEvent.ACTION_UP:                                //~1924I~
            if (swSwipe)                                           //~1924I~
            	break;                                             //~1924I~
            if (swLongPressSent)                                   //~vaimI~
            	break;                                             //~vaimI~
            axeTimer.stop();                                       //~1925M~
            longPressTimer.stop();                                 //~vaimI~
            long elapsedTime=eventTime-downTime;                   //~1924R~
            if (Dump.Y) Dump.println("ACTION UP down posX="+downPosX+",y="+downPosY+",sendDown="+swSendDown+",elapsed="+elapsedTime);//~1924I~
            if (!swSendDown)                                       //~1924I~
	        	sendEvent(MotionEvent.ACTION_DOWN,1,downState,downPosX,downPosY);//~1927R~
            if (swMove)                                            //~1924I~
	        	sendEvent(MotionEvent.ACTION_MOVE,1,moveState,movePosX,movePosY);//~1927R~
//            else                                                 //~vaimR~
//                if (swLongPress)    //timeout                    //~vaimR~
//                {                                                //~vaimR~
//                    if (Dump.Y) Dump.println("ACTION UP LongPress elapsed="+elapsedTime);//~vaimR~
//                    sendEventRBDown(metastate,xx,yy);            //~vaimR~
//                    break;                                       //~vaimR~
//                }                                                //~vaimR~
		    sendEvent(action,1,metastate,xx,yy);	//action-up    //~1927R~
            break;                                                 //~1924I~
        }                                                          //~1924I~
        if (Dump.Y) Dump.println("onTouchScreen rc="+rc);          //~1924R~
        return rc;                                                 //~1924I~
    }                                                              //~1922I~
//***********************************************************************//~vaimR~
//*return true when movd beyond cell width/height from touch down pos//~vaimI~
//***********************************************************************//~vaimI~
	private boolean isMovedOverCell(int Px1,int Py1,int Px2,int Py2)//~vaimI~
    {                                                              //~vaimI~
        boolean rc;                                                //~vaimI~
    	int movex=Px2-Px1;                                         //~vaimI~
    	int movey=Py2-Py1;                                         //~vaimI~
        if (movex<0)                                               //~vaimI~
        	movex=-movex;                                          //~vaimI~
        if (movey<0)                                               //~vaimI~
        	movey=-movey;                                          //~vaimI~
        rc=movex>Gxeh.Mcellw || movey>Gxeh.Mcellh;                 //~vaimI~
        if (Dump.Y) Dump.println("isMoveOverCell rc="+rc+",x1="+Px1+",y1="+Py1+",x2="+Px2+",y2="+Py2+",mvx="+movex+",mvy="+movey+",w="+Gxeh.Mcellw+",h="+Gxeh.Mcellh);//~vaimI~
        return rc;                                                 //~vaimI~
    }                                                              //~vaimI~
//**********************************************************       //~1926I~
    public boolean onTouchByKbdLB(int Paction,KeyEvent Pevent)       //~1926R~//~1A04R~
    {                                                              //~1926I~
    	int action,state;
    	boolean rc=true;//~1926I~
    	if (Dump.Y) Dump.println("AxeMouse:onTouchByKbd action="+Paction);//~1926I~
    	switch(Paction)                                            //~1926I~
        {                                                          //~1926I~
        case KeyEvent.ACTION_DOWN:                                 //~1926I~
	        action=MotionEvent.ACTION_DOWN;                        //~1926I~
			state=getMetaState(Pevent);                            //~1926R~
    		sendEvent(action,1,state,-1/*keep current carret pos*/,-1);//~1927R~
            break;                                                 //~1926I~
        case KeyEvent.ACTION_UP:                                   //~1926I~
	        action=MotionEvent.ACTION_UP;                          //~1926I~
			state=getMetaState(Pevent);                            //~1926R~
    		sendEvent(action,1,state,-1/*keep current carret pos*/,-1);//~1927R~
            break;                                                 //~1926I~
        default:
        	rc=false;
        }
    	return rc;//~1926I~
    }                                                              //~1926I~
//**********************************************************       //~1A04I~
    public boolean onTouchByKbdLB2(int Paction,KeyEvent Pevent)    //~1A04I~
    {                                                              //~1A04I~
    	int action,state;                                          //~1A04I~
    	boolean rc=true;                                           //~1A04I~
    	if (Dump.Y) Dump.println("AxeMouse:onTouchByKbd action="+Paction);//~1A04I~
    	switch(Paction)                                            //~1A04I~
        {                                                          //~1A04I~
        case KeyEvent.ACTION_DOWN:                                 //~1A04I~
	        action=MotionEvent.ACTION_DOWN;                        //~1A04I~
			state=getMetaState(Pevent);                            //~1A04I~
    		sendEvent(ACTION_DBLCLICK,1/*buttonNo:LB*/,state,-1/*keep current carret pos*/,-1);//~1A04R~//~1A17R~
            break;                                                 //~1A04I~
        case KeyEvent.ACTION_UP:                                   //~1A04I~
        	rc=false;                                              //~1A04I~
            break;                                                 //~1A04I~
        default:                                                   //~1A04I~
        	rc=false;                                              //~1A04I~
        }                                                          //~1A04I~
    	return rc;                                                 //~1A04I~
    }                                                              //~1A04I~
//**********************************************************       //~1A04I~
    public void onTouchByKbdLBDragStart(KeyEvent Pevent)           //~1A04R~
    {                                                              //~1A04I~
		int state=getMetaState(Pevent);                                //~1A04R~
    	sendEvent(ACTION_DRAG,1,state,-1/*keep current carret pos*/,-1);//~1A04R~
        dragging=true;                                             //~1A05I~
    }                                                              //~1A04I~
//**********************************************************       //~1A04I~
    public void onTouchByKbdLBDragEnd()                            //~1A04I~
    {                                                              //~1A04I~
        if (!dragging)                                             //~1A05I~
        	return;                                                //~1A05I~
        dragging=false;                                            //~1A05I~
	    int action=MotionEvent.ACTION_UP;                          //~1A04I~
    	sendEvent(action,1,0/*state*/,-1/*keep current carret pos*/,-1);//~1A04I~
    }                                                              //~1A04I~
//**********************************************************       //~1A03I~
    public boolean onTouchByKbdRB(int Paction,int Pmod)              //~1A03I~//~1A04R~
    {                                                              //~1A03I~
    	boolean rc=true;                                           //~1A03I~
        int action;                                                //~1A03I~
    	if (Dump.Y) Dump.println("AxeMouse:onTouchByKbd RB action="+Paction);//~1A03I~
    	switch(Paction)                                            //~1A03I~
        {                                                          //~1A03I~
        case KeyEvent.ACTION_DOWN:                                 //~1A03I~
	        action=MotionEvent.ACTION_DOWN;                        //~1A03I~
	        showContextMenu(action);                               //~1A03I~
            break;                                                 //~1A03I~
        case KeyEvent.ACTION_UP:                                   //~1A03I~
        	rc=false;                                              //~1A03I~
            break;                                                 //~1A03I~
        default:                                                   //~1A03I~
        	rc=false;                                              //~1A03I~
        }                                                          //~1A03I~
    	return rc;                                                 //~1A03I~
    }                                                              //~1A03I~
//********************************************************         //~1924I~
    public void sendEvent(int Paction,int Pbutton,int Pstatus,int Px,int Py)//~1927R~
    {                                                              //~1926R~
    	int flag;                                                  //~1926I~
        flag=Pstatus;                                              //~1926I~
    	if (Dump.Y) Dump.println("AxeMouse:sendEvent action="+Paction+",status="+Integer.toHexString(Pstatus)+",x="+Px+",y="+Py);//~1926R~
                                                                   //~1926I~
        AxeJNI.onTouch(Paction,Pbutton,Pstatus,Px,Py);             //~1927R~
    }                                                              //~1924I~
//********************************************************         //~vaimI~
    public void sendEventRBDown(int Pstatus,int Px,int Py)         //~vaimI~
    {                                                              //~vaimI~
    	if (Dump.Y) Dump.println("AxeMouse:sendEvent RBDown status="+Integer.toHexString(Pstatus)+",x="+Px+",y="+Py);//~vaimI~
    	onTouchByKbdRB(KeyEvent.ACTION_DOWN,Pstatus);              //~vaimI~
    }                                                              //~vaimI~
//********************************************************         //~1926I~
//*for touch                                                       //~1926I~
//********************************************************         //~1926I~
    private int getMetaState(MotionEvent Pevent)                   //~1926I~
    {                                                              //~1926I~
    	int metatstate,mousemodifier=0,btnmodifier,state;          //~1926R~
    	metatstate=Pevent.getMetaState();                          //~1926R~
        mousemodifier=AxeKeyValue.metaToMod(metatstate);           //~1926R~
        btnmodifier=AxeG.axeKeyAction.getModifier();               //~1926I~
        state=(mousemodifier|btnmodifier)>>16;                     //~1926R~
//        int ptrs=Pevent.getPointerCount();                       //~1926R~
//        if (ptrs==1)                                             //~1926R~
        	state|=MK_LBUTTON;	//middle,right?               //~1926R~
    	if (Dump.Y) Dump.println("AxeMouse:getMetaState btn="+Integer.toHexString(btnmodifier)+",event="+Integer.toHexString(metatstate)+",mouse="+Integer.toHexString(mousemodifier)+",ret mod="+Integer.toHexString(state));//~1926R~
        return state;                                              //~1926R~
    }                                                              //~1926I~
//********************************************************         //~1926I~
//*for trackball center button                                     //~1926I~
//********************************************************         //~1926I~
    private int getMetaState(KeyEvent Pevent)                   //~1926I~
    {                                                              //~1926I~
    	int metatstate,mousemodifier=0,btnmodifier,state;          //~1926I~
    	metatstate=Pevent.getMetaState();                          //~1926I~
        mousemodifier=AxeKeyValue.metaToMod(metatstate);           //~1926I~
        btnmodifier=AxeG.axeKeyAction.getModifier();               //~1926I~
        state=(mousemodifier|btnmodifier)>>16;                     //~1926I~
        state|=MK_LBUTTON;
    	if (Dump.Y) Dump.println("AxeMouse:getMetaState btn="+Integer.toHexString(btnmodifier)+",event="+Integer.toHexString(metatstate)+",mouse="+Integer.toHexString(mousemodifier)+",ret mod="+Integer.toHexString(state));//~1926I~
        return state;                                              //~1926I~
    }                                                              //~1926I~
//********************************************************         //~1924I~
    public void settimeout(int Ptimeout)                           //~1924I~
    {                                                              //~1924I~
    	if (Dump.Y) Dump.println("settimeout t/o="+Ptimeout);      //~1924I~
//      axeTimer.start();                                          //~1C28R~
        axeTimer.start(Ptimeout);                                  //~1C28I~
        longPressTimer.start(AxeG.longPressTimeout);               //~vaimI~
    }                                                              //~1924I~
//********************************************************         //~1924I~
//*assuem up event after time out is end of drag                   //~1A02I~
//*so wait to move util timeout for drag                           //~1A02I~
//********************************************************         //~1A02I~
    @Override                                                      //~1924I~
    public void onTimerExpired(AxeTimer Ptimer,int Pcallctr,Object Pparm)//~1924I~
    {                                                              //~1924I~
    	if (Pparm!=null)	//longpress timer                      //~vaimI~
        {                                                          //~vaimI~
	    	if (Dump.Y) Dump.println("LongPress TIMEOUT=========== lastTime="+Long.toHexString(lastTime)+",systime="+Long.toHexString(System.currentTimeMillis()));//~vaimR~
            swLongPress=true;                                      //~vaimI~
        	return;                                                //~vaimI~
        }                                                          //~vaimI~
    	long elapsedTime=lastTime-downTime;                        //~1924I~
    	if (Dump.Y) Dump.println("TIMEOUT=========== lastTime="+Long.toHexString(lastTime)+",systime="+Long.toHexString(System.currentTimeMillis()));//~1924I~
    	if (Dump.Y) Dump.println("onTimerExpired swMove="+swMove+",down pos x="+downPosX+",y="+downPosY+",elapsed="+elapsedTime);//~1924I~//~3601R~
        swSendDown=true;                                           //~1924I~
        sendEvent(MotionEvent.ACTION_DOWN,1,downState,downPosX,downPosY);//~1927R~
        if (swMove)                                                //~1926I~
        {                                                          //~1926I~
        	swMove=false;                                          //~1926I~
        	sendEvent(MotionEvent.ACTION_MOVE,1,moveState,movePosX,movePosY);//~1927R~
        }                                                          //~1926I~
    }                                                              //~1924I~
//********************************************************         //~1924I~
	private void initGestureDetector()                             //~1924I~
    {                                                              //~1924I~
        mGestureDetector=new GestureDetector(AxeG.context,new ScreenGestureDetector());//~1924R~
        mGestureDetector.setIsLongpressEnabled(false);             //~1924I~
	}                                                              //~1924I~
//********************************************************         //~1924I~
	class ScreenGestureDetector extends GestureDetector.SimpleOnGestureListener//~1924I~
    {                                                              //~1924I~
        @Override                                                  //~1924I~
        public boolean onFling(MotionEvent me1, MotionEvent me2,float velocityX, float velocityY)//~1924I~
        {                                                          //~1924I~
                if (Dump.Y) Dump.println("AxeMouse:SimpleOnGestureListener:onFling verocity X="+velocityX+",Y="+velocityY);//~vaxeR~
//                if (mPossiblePoly) return false;                 //~1924I~
                final float absX = Math.abs(velocityX);            //~1924I~
                final float absY = Math.abs(velocityY);            //~1924I~
                float deltaX = me2.getX() - me1.getX();            //~1924I~
                float deltaY = me2.getY() - me1.getY();            //~1924I~
                if (Dump.Y) Dump.println("AxeMouse:SimpleOnGestureListener:onFling posX="+me1.getX()+"-"+me2.getX());//~vaxeR~
                if (Dump.Y) Dump.println("AxeMouse:SimpleOnGestureListener:onFling posY="+me1.getY()+"-"+me2.getY());//~vaxeR~
                if (Dump.Y) Dump.println("AxeMouse:SimpleOnGestureListener:abs X="+absX+",Y="+absY);//~vaxeI~
//              int travelX = getWidth() / 2; // Half the keyboard width//~1924I~
//              int travelY = getHeight() / 2; // Half the keyboard height//~1924I~
//                int travelX = minSwipeDeltaX;                    //~1924I~
//                int travelY = minSwipeDeltaY;                    //~1924I~
//                int travelX = getHeight()/4;                     //~1924I~
//                int travelY = getHeight()/4;                     //~1924I~
                if (Dump.Y) Dump.println("AxeMouse:SimpleOnGestureListener:onFling delta X="+deltaX+",Y="+deltaY);//~vaxeR~
                if (Dump.Y) Dump.println("AxeMouse:SimpleOnGestureListener:travel delta X="+travelX+",Y="+travelY);//~vaxeR~
                mSwipeTracker.computeCurrentVelocity(1000);        //~1924I~
                final float endingVelocityX = mSwipeTracker.getXVelocity();//~1924I~
                final float endingVelocityY = mSwipeTracker.getYVelocity();//~1924I~
                if (Dump.Y) Dump.println("AxeMouse:SimpleOnGestureListener:ending velocity X="+endingVelocityX+",Y="+endingVelocityY);//~vaxeR~
                if (Dump.Y) Dump.println("AxeMouse:SimpleOnGestureListener:swipe th="+mSwipeThreshold+",ambiguous="+mDisambiguateSwipe);//~vaxeR~
                boolean sendDownKey = false;                       //~1924I~
                if (velocityX > mSwipeThreshold && absY < absX && deltaX > travelX) {//~1924I~
                    if (mDisambiguateSwipe && endingVelocityX < velocityX / 4) {//~1924I~
                        sendDownKey = true;                        //~1924I~
                    } else {                                       //~1924I~
                        /*mKeyboardActionListener.*/swipeRight();  //~1924I~
                        return true;                               //~1924I~
                    }                                              //~1924I~
                } else if (velocityX < -mSwipeThreshold && absY < absX && deltaX < -travelX) {//~1924I~
                    if (mDisambiguateSwipe && endingVelocityX > velocityX / 4) {//~1924I~
                        sendDownKey = true;                        //~1924I~
                    } else {                                       //~1924I~
                        /*mKeyboardActionListener.*/swipeLeft();   //~1924I~
                        return true;                               //~1924I~
                    }                                              //~1924I~
                } else if (velocityY < -mSwipeThreshold && absX < absY && deltaY < -travelY) {//~1924I~
                    if (mDisambiguateSwipe && endingVelocityY > velocityY / 4) {//~1924I~
                        sendDownKey = true;                        //~1924I~
                    } else {                                       //~1924I~
                        /*mKeyboardActionListener.*/swipeUp();     //~1924I~
                        return true;                               //~1924I~
                    }                                              //~1924I~
                } else if (velocityY > mSwipeThreshold && absX < absY / 2 && deltaY > travelY) {//~1924I~
                    if (mDisambiguateSwipe && endingVelocityY < velocityY / 4) {//~1924I~
                        sendDownKey = true;                        //~1924I~
                    } else {                                       //~1924I~
                        /*mKeyboardActionListener.*/swipeDown();   //~1924I~
                        return true;                               //~1924I~
                    }                                              //~1924I~
                }                                                  //~1924I~
                                                                   //~1924I~
                if (sendDownKey)                                   //~1924I~
                {                                                  //~1924I~
//              detectAndSendKey(mDownKey, mStartX, mStartY, me1.getEventTime());//~1924I~
                    if (Dump.Y) Dump.println("onFling sendKey");   //~1924I~
                                                                   //~1924I~
                }                                                  //~1924I~
                if (Dump.Y) Dump.println("onFling return false");  //~1924I~
                return false;                                      //~1924I~
            }                                                      //~1924I~
	}//class ScreenGestureDetector                                 //~1924I~
//***************************************************              //~1924I~
    public void swipeLeft()                                        //~1924I~
    {                                                              //~1924I~
    	int dist=lastPosX-downPosX;       //minus:forward          //~1A03R~
        int rate=dist*100/AxeG.screenW;                            //~1A02I~
    	if (Dump.Y) Dump.println("Swipe Left dist="+dist+",rate="+rate);                    //~1924I~//~1A02R~
        AxeJNI.swipeHorizontal(lastMetaState,dist,-rate);                        //~1A02I~//~1A03R~
    	AxeG.axeKeyAction.resetModifier();                         //~1A03R~
    }                                                              //~1924I~
    public void swipeRight()                                       //~1924I~
    {                                                              //~1924I~
    	int dist=lastPosX-downPosX;          //plus:backward       //~1A03I~
        int rate=dist*100/AxeG.screenW;                            //~1A02I~
    	if (Dump.Y) Dump.println("Swipe Right dist="+dist+",rate="+rate);//~1A02I~
        AxeJNI.swipeHorizontal(lastMetaState,dist,rate);                         //~1A02I~//~1A03R~
    	AxeG.axeKeyAction.resetModifier();                         //~1A03I~
    }                                                              //~1924I~
    public void swipeUp()                                          //~1924I~
    {                                                              //~1924I~
    	int dist=lastPosY-downPosY;          //minus:forward       //~1A03R~
        int rate=dist*100/AxeG.screenH;                            //~1A02I~
    	if (Dump.Y) Dump.println("Swipe Up dist="+dist+",rate="+rate);//~1A02I~
        AxeJNI.swipeVertical(lastMetaState,dist,-rate);                          //~1A02I~//~1A03R~
    	AxeG.axeKeyAction.resetModifier();                         //~1A03I~
    }                                                              //~1924I~
    public void swipeDown()                                        //~1924I~
    {                                                              //~1924I~
    	int dist=lastPosY-downPosY;        //plus:backword         //~1A03I~
        int rate=dist*100/AxeG.screenH;                            //~1A02I~
    	if (Dump.Y) Dump.println("Swipe Down dist="+dist+",rate="+rate);//~1A02I~
        AxeJNI.swipeVertical(lastMetaState,dist,rate);                           //~1A02I~//~1A03R~
    	AxeG.axeKeyAction.resetModifier();                         //~1A03I~
    }                                                              //~1924I~
//***************************************************              //~1924I~
	private static class SwipeTracker                              //~1924I~
    {                                                              //~1924I~
        static final int NUM_PAST = 4;                             //~1924I~
        static final int LONGEST_PAST_TIME = 200;                  //~1924I~
                                                                   //~1924I~
        final float mPastX[] = new float[NUM_PAST];                //~1924I~
        final float mPastY[] = new float[NUM_PAST];                //~1924I~
        final long mPastTime[] = new long[NUM_PAST];               //~1924I~
                                                                   //~1924I~
        float mYVelocity;                                          //~1924I~
        float mXVelocity;                                          //~1924I~
                                                                   //~1924I~
        public void clear() {                                      //~1924I~
            mPastTime[0] = 0;                                      //~1924I~
        }                                                          //~1924I~
                                                                   //~1924I~
        public void addMovement(MotionEvent ev) {                  //~1924I~
            long time = ev.getEventTime();                         //~1924I~
            final int N = ev.getHistorySize();                     //~1924I~
            for (int i=0; i<N; i++) {                              //~1924I~
                addPoint(ev.getHistoricalX(i), ev.getHistoricalY(i),//~1924I~
                        ev.getHistoricalEventTime(i));             //~1924I~
            }                                                      //~1924I~
            addPoint(ev.getX(), ev.getY(), time);                  //~1924I~
        }                                                          //~1924I~
                                                                   //~1924I~
        private void addPoint(float x, float y, long time) {       //~1924I~
            int drop = -1;                                         //~1924I~
            int i;                                                 //~1924I~
            final long[] pastTime = mPastTime;                     //~1924I~
            for (i=0; i<NUM_PAST; i++) {                           //~1924I~
                if (pastTime[i] == 0) {                            //~1924I~
                    break;                                         //~1924I~
                } else if (pastTime[i] < time-LONGEST_PAST_TIME) { //~1924I~
                    drop = i;                                      //~1924I~
                }                                                  //~1924I~
            }                                                      //~1924I~
            if (i == NUM_PAST && drop < 0) {                       //~1924I~
                drop = 0;                                          //~1924I~
            }                                                      //~1924I~
            if (drop == i) drop--;                                 //~1924I~
            final float[] pastX = mPastX;                          //~1924I~
            final float[] pastY = mPastY;                          //~1924I~
            if (drop >= 0) {                                       //~1924I~
                final int start = drop+1;                          //~1924I~
                final int count = NUM_PAST-drop-1;                 //~1924I~
                System.arraycopy(pastX, start, pastX, 0, count);   //~1924I~
                System.arraycopy(pastY, start, pastY, 0, count);   //~1924I~
                System.arraycopy(pastTime, start, pastTime, 0, count);//~1924I~
                i -= (drop+1);                                     //~1924I~
            }                                                      //~1924I~
            pastX[i] = x;                                          //~1924I~
            pastY[i] = y;                                          //~1924I~
            pastTime[i] = time;                                    //~1924I~
            i++;                                                   //~1924I~
            if (i < NUM_PAST) {                                    //~1924I~
                pastTime[i] = 0;                                   //~1924I~
            }                                                      //~1924I~
        }                                                          //~1924I~
                                                                   //~1924I~
        public void computeCurrentVelocity(int units) {            //~1924I~
            computeCurrentVelocity(units, Float.MAX_VALUE);        //~1924I~
        }                                                          //~1924I~
                                                                   //~1924I~
        public void computeCurrentVelocity(int units, float maxVelocity) {//~1924I~
            final float[] pastX = mPastX;                          //~1924I~
            final float[] pastY = mPastY;                          //~1924I~
            final long[] pastTime = mPastTime;                     //~1924I~
                                                                   //~1924I~
            final float oldestX = pastX[0];                        //~1924I~
            final float oldestY = pastY[0];                        //~1924I~
            final long oldestTime = pastTime[0];                   //~1924I~
            float accumX = 0;                                      //~1924I~
            float accumY = 0;                                      //~1924I~
            int N=0;                                               //~1924I~
            while (N < NUM_PAST) {                                 //~1924I~
                if (pastTime[N] == 0) {                            //~1924I~
                    break;                                         //~1924I~
                }                                                  //~1924I~
                N++;                                               //~1924I~
            }                                                      //~1924I~
                                                                   //~1924I~
            for (int i=1; i < N; i++) {                            //~1924I~
                final int dur = (int)(pastTime[i] - oldestTime);   //~1924I~
                if (dur == 0) continue;                            //~1924I~
                float dist = pastX[i] - oldestX;                   //~1924I~
                float vel = (dist/dur) * units;   // pixels/frame. //~1924I~
                if (accumX == 0) accumX = vel;                     //~1924I~
                else accumX = (accumX + vel) * .5f;                //~1924I~
                                                                   //~1924I~
                dist = pastY[i] - oldestY;                         //~1924I~
                vel = (dist/dur) * units;   // pixels/frame.       //~1924I~
                if (accumY == 0) accumY = vel;                     //~1924I~
                else accumY = (accumY + vel) * .5f;                //~1924I~
            }                                                      //~1924I~
            mXVelocity = accumX < 0.0f ? Math.max(accumX, -maxVelocity)//~1924I~
                    : Math.min(accumX, maxVelocity);               //~1924I~
            mYVelocity = accumY < 0.0f ? Math.max(accumY, -maxVelocity)//~1924I~
                    : Math.min(accumY, maxVelocity);               //~1924I~
        }                                                          //~1924I~
                                                                   //~1924I~
        public float getXVelocity() {                              //~1924I~
            return mXVelocity;                                     //~1924I~
        }                                                          //~1924I~
                                                                   //~1924I~
        public float getYVelocity() {                              //~1924I~
            return mYVelocity;                                     //~1924I~
        }                                                          //~1924I~
    }//swipetracker                                                //~1924I~
//********************************************************         //~v2cQI~
	private void initSGL()                                         //~v2cQI~
    {                                                              //~v2cQI~
        mSGL=new SimpleOnScaleGestureListener()//~v2cQI~
        			{                                              //~v2cQI~
                    	@Override                                  //~v2cQI~
                        public boolean onScaleBegin(ScaleGestureDetector Pdetector)//~v2cQI~
                        {                                          //~v2cQI~
    						if (Dump.Y) Dump.println("AxeMouse.onScaleBegin");//~v2cQI~
                        	return super.onScaleBegin(Pdetector);  //~v2cQI~
                        }                                          //~v2cQI~
                    	@Override                                  //~v2cQI~
                        public void onScaleEnd(ScaleGestureDetector Pdetector)//~v2cQI~
                        {                                          //~v2cQI~
    						if (Dump.Y) Dump.println("AxeMouse.onScaleEnd");//~v2cQR~
        					try                                    //~v2cQI~
        					{                                      //~v2cQI~
            					Axegxedlg.onScale(scaleFactor);    //~v2cQI~
                                scaleFactor=0;                     //~v2cQI~
        					}                                      //~v2cQI~
        					catch(Exception e)                     //~v2cQI~
        					{                                      //~v2cQI~
            					Dump.println(e,"AxeMouse.OnScaleEnd");//~v2cQI~
        					}                                      //~v2cQI~
                        	super.onScaleEnd(Pdetector);           //~v2cQM~
    						if (Dump.Y) Dump.println("AxeMouse.onScaleEnd return");//~v2cQI~
                        }                                          //~v2cQI~
                    	@Override                                  //~v2cQI~
                        public boolean onScale(ScaleGestureDetector Pdetector)//~v2cQI~
                        {                                          //~v2cQI~
					    	scaleFactor=Pdetector.getScaleFactor();//~v2cQI~
    						if (Dump.Y) Dump.println("AxeMouse.onScale scaleFactor="+scaleFactor);//~v2cQI~
                            return true;                           //~v2cQR~
                        }                                          //~v2cQI~
                    };                                             //~v2cQI~
        mSGD=new ScaleGestureDetector(AxeG.context,mSGL);          //~v2cQI~
	}                                                              //~v2cQI~
//********************************************************         //~v2cQI~
	private boolean onScaleAction(ScaleGestureDetector Pdetector)  //~v2cQI~
    {                                                              //~v2cQI~
    	float scaleFactor=Pdetector.getScaleFactor();              //~v2cQI~
    	if (Dump.Y) Dump.println("AxeMouse.onScaleAction scaleFactor="+scaleFactor);//~v2cQI~
        if (scaleFactor>1)                                         //~v2cQI~
        {                                                          //~v2cQI~
	    	if (Dump.Y) Dump.println("AxeMouse.onScaleAction ZoomOut");//~v2cQI~
        }                                                          //~v2cQI~
        else                                                       //~v2cQI~
        {                                                          //~v2cQI~
	    	if (Dump.Y) Dump.println("AxeMouse.onScaleAction ZoomIn");//~v2cQI~
        }                                                          //~v2cQI~
	    return true;
    }                                                              //~v2cQI~
}//class                                                           //~va15R~
