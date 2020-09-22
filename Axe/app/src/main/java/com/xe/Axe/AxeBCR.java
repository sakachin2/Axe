//*CID://+vc2KR~:                             update#=  106;       //~vc2KR~
//*****************************************************************************************//~vabeR~
//vc2K 2020/08/28 receive intent(View/Edit)                        //~vc2KI~
//*****************************************************************************************//~vabeR~
package com.xe.Axe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import com.ahsv.utils.Utils;

public class AxeBCR extends BroadcastReceiver                      //~vc2KR~
{                                                                  //~vaiqI~
    private static boolean swRegistered;//=true; //TODO  if not registered, new process show dialog to request select app//~vc2KR~
//****************************************                         //~vaiqI~
    public AxeBCR()                                                //~vc2KR~
	{                                                              //~1526I~
        if (Dump.Y) Dump.println("AxeBCR.constructor");            //~vc2KI~
        AxeG.axeBCR=this;                                          //~vc2KI~
	}                                                              //~1526I~
    public void onCreate()                                         //~vc2KR~
	{                                                              //~vc2KI~
        if (Dump.Y) Dump.println("AxeBCR.onCreate");               //~vc2KR~
    }                                                              //~vc2KI~
    public void onStart()                                          //~vc2KI~
	{                                                              //~vc2KI~
        if (Dump.Y) Dump.println("AxeBCR.onStart");                //~vc2KI~
//      chkIntent();    //TODO test                                //~vc2KR~
    }                                                              //~vc2KI~
    public void onDestroy()                                        //~vc2KI~
	{                                                              //~vc2KI~
        if (Dump.Y) Dump.println("AxeBCR.onDestroy");              //~vc2KI~
        unregister();                                              //~vc2KI~
    }                                                              //~vc2KI~
    public void onPause()                                          //~vc2KI~
	{                                                              //~vc2KI~
        if (Dump.Y) Dump.println("AxeBCR.onPause");                //~vc2KI~
    }                                                              //~vc2KI~
    public void onResume()                                         //~vc2KI~
	{                                                              //~vc2KI~
        if (Dump.Y) Dump.println("AxeBCR.onResume");               //~vc2KI~
//      chkIntent();    //TODO test                                //~vc2KR~
        register();                                                //~vc2KM~
    }                                                              //~vc2KI~
    private void chkIntent()                                        //~vc2KI~
	{                                                              //~vc2KI~
    	Intent intent=AxeG.activity.getIntent();                   //~vc2KI~
        if (Dump.Y) Dump.println("AxeBCR.chkIntent intent="+Utils.toString((Object)intent));//~vc2KI~
        if (intent!=null)                                          //~vc2KI~
        {                                                          //~vc2KI~
	        String action=intent.getAction();                      //~vc2KI~
	        if (Dump.Y) Dump.println("AxeBCR.chkIntent action="+action);//~vc2KI~
    	    Uri uri=intent.getData();                              //~vc2KI~
            if (uri!=null)                                         //~vc2KI~
		        if (Dump.Y) Dump.println("AxeBCR.chkIntent uri="+uri.toString());//~vc2KI~
        }                                                          //~vc2KI~
    }                                                              //~vc2KI~
    //*********************************************************    //~vc2KI~
    //*for ACTION_VIEW,no register requied, Manifest definition required//+vc2KI~
    //*********************************************************    //+vc2KI~
    public void register()                                         //~vc2KI~
	{
        if (Dump.Y) Dump.println("AxeBCR.register swRegistered="+swRegistered);//~vc2KR~
        if (swRegistered)                                          //~vc2KI~
        	return;                                                //~vc2KI~
        swRegistered=true;                                         //~vc2KI~
        IntentFilter filter;                                       //~vc2KR~
        String mimetype="text/*";                                   //~vc2KI~
//      filter = new IntentFilter(Intent.ACTION_VIEW);             //~vc2KR~
		try                                                        //~vc2KR~
    	{                                                          //~vc2KR~
        	filter = new IntentFilter(Intent.ACTION_VIEW,mimetype);//~vc2KR~
    	}                                                          //~vc2KR~
    	catch (IntentFilter.MalformedMimeTypeException e)          //~vc2KR~
    	{                                                          //~vc2KR~
			Utils.showToast("mimetyoe err"+e);                     //~vc2KR~
        	return;                                                //~vc2KR~
    	}                                                          //~vc2KR~
                                                                   //~vc2KI~
        AxeG.activity.registerReceiver(this, filter);              //~vc2KI~
//      filter = new IntentFilter(Intent.ACTION_EDIT);             //~vc2KR~
		try                                                        //~vc2KR~
    	{                                                          //~vc2KR~
        	filter = new IntentFilter(Intent.ACTION_EDIT,mimetype);//~vc2KR~
    	}                                                          //~vc2KR~
    	catch (IntentFilter.MalformedMimeTypeException e)          //~vc2KR~
    	{                                                          //~vc2KR~
			Utils.showToast("mimetyoe err"+e);                     //~vc2KR~
        	return;                                                //~vc2KR~
    	}                                                          //~vc2KR~
        AxeG.activity.registerReceiver(this, filter);              //~vc2KI~
	}
    //*********************************************************    //~vc2KI~
    public void unregister()                                       //~vc2KI~
    {                                                              //~vc2KI~
        if (Dump.Y) Dump.println("AxeBCR.unregister swRegistered="+swRegistered);//~vc2KR~
        if (swRegistered)                                          //~vc2KI~
        {                                                          //~vc2KI~
        	swRegistered=false;                                    //~vc2KR~
			AxeG.activity.unregisterReceiver(this);                //~vc2KR~
        }                                                          //~vc2KI~
    }                                                              //~vc2KI~
    //*********************************************************    //~vc2KI~
    @Override                                                      //~vc2KI~
    public void onReceive(Context context, Intent Pintent)         //~vc2KR~
    {                                                              //~vc2KI~
        if (Dump.Y) Dump.println("AxeBCR.onReceive");              //~vc2KI~
    	uriReceived(Pintent);                                       //~vc2KI~
	}                                                              //~vc2KI~
    //*********************************************************    //~vc2KI~
    //*for ACTION_VIEW,startActivity() pass request, if already up, newIntent will be called//+vc2KI~
    //*********************************************************    //+vc2KI~
    public void onNewIntent(Intent Pintent)                        //~vc2KI~
    {                                                              //~vc2KI~
      	if (Dump.Y) Dump.println("AxeBCR.onNewIntent");            //~vc2KI~
    	uriReceived(Pintent);                                       //~vc2KI~
    }                                                              //~vc2KI~
    //*********************************************************    //~vc2KI~
    private void uriReceived(Intent Pintent)                       //~vc2KI~
    {                                                              //~vc2KI~
      	try                                                        //~vc2KI~
      	{                                                          //~vc2KI~
        	String action=Pintent.getAction();                     //~vc2KI~
    	    Uri uri=Pintent.getData();                             //~vc2KI~
            if (uri!=null)                                         //~vc2KI~
            {                                                      //~vc2KI~
            	String strUri=uri.toString();                      //~vc2KI~
        		if (Dump.Y) Dump.println("AxeBCR.uriReceive action="+action+",uri="+strUri);//~vc2KI~
            	AxeJNI.uriReceived(action,strUri);                 //~vc2KI~
            }                                                      //~vc2KI~
            else                                                   //~vc2KI~
        		if (Dump.Y) Dump.println("AxeBCR.onReceive uri=null");//~vc2KI~
      	}//try                                                     //~vc2KI~
      	catch(Exception e)                                         //~vc2KI~
      	{                                                          //~vc2KI~
        	Dump.println(e,"AxeBCR.uriReceive");                   //~vc2KI~
      	}                                                          //~vc2KI~
      	if (Dump.Y) Dump.println("AxeBCR.uriReceive return");      //~vc2KI~
	}                                                              //~vc2KI~
}//class                                                           //~1621R~