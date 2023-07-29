//CID://+vcadR~:       update#=4                                   //~vcadR~
//*************************************************************    //~vaaiI~
//vc4d 2023/03/25 androd11(api30) deprecated at api30;Handler default constructor(requires parameter)//~vcadI~
//vaai:111227 (Axe:BUG)flick operation generate primekey+flickkey  //~vaaiI~
//*************************************************************    //~vaaiI~
package com.xe.Axe;                                                //~1808I~
                                                                   //~1808I~
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.os.Handler;                                         //~1808I~
import android.os.Looper;
import android.os.Message;                                         //~1808I~
                                                                   //~1808I~
public class AxeTimer extends Handler                              //~0915R~//~1808I~
{                                                                  //~0915I~//~1808I~
	private static int lastSeqNo;                                  //~1808I~
    private static List<AxeTimer> list=new ArrayList<AxeTimer>();                            //~1808I~
	private int callCtr;                                  //~0915I~//~1808I~
	private long interval;                                         //~0915I~//~1808I~
	private long callback;                                         //~1808I~
	private AxeTimerI listener;                                    //~1827I~
	private Object userParm;                                       //~1827I~
	public int userTimerId;                                             //~0915I~//~1808I~//~1A18R~
	private boolean swActive,swRepeat/*,swCoalesce*/;                           //~0915I~//~1808I~
    private int timerId;                                           //~1808I~
    public AxeTimer(Looper Plooper)                                //~vcadI~
    {                                                              //~vcadI~
    	super(Plooper);                                            //~vcadI~
        if (Dump.Y) Dump.println("AxeTimer.constructor");          //~vcadI~
    }                                                              //~vcadI~
    public AxeTimer(int PuserTimerId,int Pinterval,long Pcallback)           //~0915I~//~1808I~
    {                                                              //~0915I~//~1808I~
    	this(Looper.getMainLooper());                              //~vcadI~
    	swActive=false;                                                //~0915I~//~1808I~
    	swRepeat=false;                                            //~0915I~//~1808I~
        userTimerId=PuserTimerId;                                          //~0915I~//~1808I~
        interval=(long)Pinterval;                                    //~0915I~//~1808I~
        callback=Pcallback;                                        //~1808I~
    	timerId=lastSeqNo++;                                       //~1808I~
        list.add(this);                                            //~1808I~
    }                                                              //~0915I~//~1808I~
    public AxeTimer(AxeTimerI Plistener,int Pinterval,boolean Prepeat,Object Pparm)//~1827R~
    {                                                              //~1827I~
    	this(Looper.getMainLooper());                              //+vcadI~
    	swActive=false;                                            //~1827I~
    	swRepeat=false;                                            //~1827I~
        userTimerId=0;                             //~1827I~
        interval=(long)Pinterval;                                  //~1827I~
        listener=Plistener;                                        //~1827I~
        swRepeat=Prepeat;                                          //~1827I~
        userParm=Pparm;                                            //~1827I~
    	timerId=lastSeqNo++;                                       //~1827I~
        list.add(this);                                            //~1827I~
    }                                                              //~1827I~
    public AxeTimer(AxeTimerI Plistener,int Pinterval,boolean Prepeat,int Ptimerid,Object Pparm)//~1A18I~
    {                                                              //~1A18I~
	    this(Plistener,Pinterval,Prepeat,Pparm);                    //~1A18I~
    	userTimerId=Ptimerid;                                      //~1A18I~
    }                                                              //~1A18I~
//**************************************************************   //~vaaiI~
//*handleMessage:exceuted on UI thread                             //~vaaiI~
//**************************************************************   //~vaaiI~
	@Override                                                      //~0915I~//~1808I~
	public void handleMessage(Message msg)                         //~0915I~//~1808I~
    {                                                              //~0915I~//~1808I~
    	if (swActive)	                                           //~0915I~//~1808I~
        {                                                          //~0915I~//~1808I~
        	if (listener!=null)	//not jni                              //~1827I~
            	listener.onTimerExpired(this,callCtr,userParm);      //~1827R~
            else                                                   //~1827I~
	        	AxeJNI.OnTimer(userTimerId,callback);                                  //~0915I~//~0A06R~//~1808I~//~1827R~
        	if (swRepeat)                                          //~1827R~
        		nextMessage(interval);                              //~0915I~//~1808I~
            else                                                   //~0A06I~//~1808I~
            {                                                      //~1827I~
            	remove(this);	                                   //~1827I~
                swActive=false;                                    //~0A06I~//~1808I~
            }                                                      //~1827I~
            callCtr++;                                             //~0915I~//~1808I~
        }                                                          //~0915I~//~1808I~
    }                                                              //~0915I~//~1808I~
	public void nextMessage(long delay)                            //~0915I~//~1808I~
    {                                                              //~0915I~//~1808I~
    	removeMessages(timerId);                                      //~0915I~//~1808I~
        sendMessageDelayed(obtainMessage(timerId),delay);            //~0915I~//~1808I~
    }                                                              //~0915I~//~1808I~
	public int start()                                            //~0915I~//~1808I~
    {                                                              //~0915I~//~1808I~
    	swActive=true;                                             //~1808I~
		nextMessage(interval);                                     //~0A06R~//~1808I~
        return timerId;                                            //~1808I~
    }                                                              //~0915I~//~1808I~
	public int start(int Pdelay)                                   //~1924I~
    {                                                              //~1924I~
    	interval=(long)Pdelay;                                     //~1924I~
        return start();                                            //~1924I~
    }                                                              //~1924I~
	public void stop()                                             //~0915I~//~1808I~
    {                                                              //~0915I~//~1808I~
    	if (swActive)                                              //~1808I~
        {                                                          //~1808I~
    		swActive=false;                                                //~0915I~//~1808I~
    		removeMessages(timerId);                                      //~0915I~//~1808I~
        }                                                          //~1808I~
    }                                                              //~0915I~//~1808I~
	public static void remove(AxeTimer Ptimer)                     //~1827I~
    {                                                              //~1827I~
    	remove(Ptimer.timerId);                                    //~1827I~
    }                                                              //~1827I~
	public static void remove(int PtimerId)                        //~1808I~
    {                                                              //~1808I~
    	AxeTimer tm;                                               //~1808I~
        Iterator<AxeTimer> it=list.iterator();                               //~1808I~
        while(it.hasNext())                                        //~1808I~
        {                                                          //~1808I~
        	tm=it.next();                              //~1808I~
            if (tm.timerId==PtimerId)                             //~1808I~
            {                                                      //~1808I~
            	tm.stop();                                         //~1808I~
//            	list.remove(tm);//issue ConcurrentModificationException//~1A10R~
              	it.remove();                                     //~1A10I~
            }                                                      //~1808I~
        }                                                          //~1808I~
    }                                                              //~1808I~
	public void setRepeats(boolean repeat)                         //~0915I~//~1808I~
    {                                                              //~0915I~//~1808I~
    	swRepeat=repeat;                                           //~0915I~//~1808I~
    }                                                              //~0915I~//~1808I~
	public boolean isActive()                                          //~vaaiI~
    {                                                              //~vaaiI~
    	return swActive;                                      //~vaaiI~
    }                                                              //~vaaiI~
}//class AxeTimer                                                       //~5922M~//~0915R~//~1808I~
