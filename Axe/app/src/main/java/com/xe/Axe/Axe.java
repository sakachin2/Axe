//*CID://+vc2UR~:                             update#=  104;       //~vc2UR~
//*****************************************************************************************//~vabeR~
//vc2U 2020/09/18 initialize progress dialog not dismiss automatically//~vc2KI~
//vc2K 2020/08/28 receive intent(View/Edit)                        //~vc2KI~
//vc26 2020/07/11 mix AxeKbdDialog and AxeKbdDialogFix(apply map of AxeLstKbdLayout)//~vc26I~
//vc1u 2020/07/06 helpdialog for asset/helptexts                   //~vc1uI~
//vc1t 2020/07/02 multi column EditText ListView callback OnFocusListener called twice True then False(mey be bug)//~vc1tI~
//                requestFocus after short time may fix it but SoftKbd popup could not be protected(can be disapper after once popupped)//~vc1tI~
//                Try to receive input by hard/soft keyboard by dummy(InVisible) EditText then send to ListView.//~vc1tI~
//                Receive HardKbd input by textview(requestFocus), //~vc1tI~
//                By IME button set dummy EditText to focusable and receive from SoftKbd//~vc1tI~
//vc1r 2020/06/26 avoid ime popup implicitly                       //~vc1rI~
//vc1b 2020/06/19 runtime permission frp android6                  //~vc1bI~
//vc10 2020/06/14 update Dump to write to terminal(copy from Ahsv) //~vc10R~
//vaye:141125 (Axe)orientationfix allow to change to reverse orientation//~vayeI~
//vaya:141125 (Axe)utilize actionbar:home button click event(customizable by settion,default is home)//~vayaI~
//vay5:141122 (Axe)actionBar as alternative of menu button for api>=11(android3)//~vay5I~
//            When requestWindowFeature(FEATURE_LEFT_ICON). onCreateOptionsMenu is not called(No ActionBar on android>=3.0)//~vay5I~
//vaiq:130606 Axe:issue initial setup Alert(Be paitient)           //~vaiqI~
//vaic:130528 Axe:Bug:when resize cusor/ruler is not displayedatfirst//~vaicI~
//            (invalidate is async,if 2 invalidate() was issued,   //~vaicI~
//            1st onDraw diplay cursor and reset drawCursor,       //~vaicI~
//            2nd onDraw draw line but dose not drow cursor)       //~vaicI~
//            android may call onDraw without invalidate() request.//~vaicI~
//            avoid issue invalidate() when orientation change processing//~vaicI~
//vai8:130526 (Axe)if soft keyboard up at startup,onDestroy scheduled when typed on bluetooth keyboard//~vai8I~
//            ConfigChanges=screenLayout is required to detect it by OnConfigurationChanged()//~vai8I~
//            (getChangingConfigurations() on onDestroy() shows reason of sheduled onDestroy by config change,//~vai8I~
//             and it was 0x30:SCREENLAYOUT_LONG_MASK)             //~vai8I~
//*****************************************************************************************//~vabeR~
package com.xe.Axe;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ahsv.AG;
import com.ahsv.utils.URunnable;
import com.ahsv.utils.URunnableI;
import com.ahsv.utils.Utils;
import com.xe.Axe.kbd.AxeKbdDialogHW;

import java.util.Arrays;

public class Axe extends Activity                                  //~vaiqR~
                    implements AxeAlertI                           //~vaiqI~
                      , URunnableI    //at stop APP                  //~vc10I~
{                                                                  //~vaiqI~
    public static final int PERMISSION_LOCATION=1;                 //~vc10I~
    public static final int PERMISSION_EXTERNAL_STORAGE=2;         //~vc1bI~
    public static final int PERMISSION_EXTERNAL_STORAGE_READ=3;         //~vc10I~//~vc1bI~
                                                                   //~vc10I~
    private boolean scrinitialized;                                //~1606R~
    private static boolean destroying=false;                       //~1607I~
    private boolean contextMenuShowing;
    private Menu menuCreated;
    public  int axeStat;                                           //~vaicI~
    private int initFirsttime;                                         //~vaiqI~
    private AxeAlert initAlertDialog2;                             //~vaiqI~
    private AxePermission aAxePermission;                          //~vc1bI~
    public static final int AXES_ORIENTATION_CHANGING=   0x01;      //~vaicI~
    //~1930I~
    @Override                                                      //~1526I~
    public void onCreate(Bundle icicle)                            //~1526I~
	{                                                              //~1526I~
    	super.onCreate(icicle);                                    //~1526I~
//  	requestWindowFeature(Window.FEATURE_LEFT_ICON);            //~vay5R~
    	try                                                        //~1528I~
        {                                                          //~1528I~
    	    Dump.openExOnlyTerminal();	//write exception only to Terminal//~vc10I~
//          AxeG.init(this);                                       //~vc1uR~
            new AG(this);                                          //~vc1uR~
            if (AxeG.osVersion<AxeG.HONEYCOMB)  //<android3=api-11 //~vay5R~
            	requestWindowFeature(Window.FEATURE_LEFT_ICON);    //~vay5I~
//        if (AxeG.isDebuggable)                                   //~vaiqI~//~vc10R~
//          Dump.open("dump.dat");                                 //~1528I~//~vc10R~
            AxeView.setContentView();                              //~1606R~
            if (AxeG.startupCtr==0)                                //~vaiqI~
            {                                                      //~vaiqI~
//            	Dump.setOption(true);	//@@@@test                 //~vaiqR~
            	initFirsttime=1;                                   //~vaiqI~
        		AxeView.startInitializeInfo();                     //~vaiqR~
        		if (Dump.Y) Dump.println("Axe onCreate:alert start");//~vaiqR~
            }                                                      //~vaiqI~
//			AxeG.axeView=new AxeView();                            //~vaiqR~
			AxeView.setInitialOrientation();                       //~vayeI~
		    hideSoftKbd();	//TODO test                            //~vc1rI~
		    AxeG.axeBCR.onCreate();                                //~vc2KI~
        }                                                          //~1528I~
        catch(Exception e)                                         //~1528I~
        {                                                          //~1528I~
        	Dump.println(e,"onCreate");                            //~1528I~
        }                                                          //~1528I~
//  	this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);//setup menu//~1526R~
//  	this.getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,R.drawable.wnp);//~1526R~
    }                                                              //~1526I~
//****************************************                         //~vc2KI~
    @Override                                                      //~vc2KI~
    public void onNewIntent(Intent Pintent)                        //~vc2KI~
	{                                                              //~vc2KI~
        if (Dump.Y) Dump.println("Axe:onNewIntent intent="+Utils.toString(Pintent));//~vc2KI~
        AxeG.axeBCR.onNewIntent(Pintent);                          //~vc2KI~
    }                                                              //~vc2KI~
//****************************************                         //~vaiqI~
    @Override                                                      //~1526I~
    public boolean onCreateOptionsMenu(Menu Pmenu)                 //~1527R~
	{                                                              //~1526I~
    	try                                                        //~1527I~
        {                                                          //~1527I~
	    	super.onCreateOptionsMenu(Pmenu);                      //~1527R~
          if (AxeG.axeMenu!=null)                                        //~vay5I~
    		AxeG.axeMenu.onCreateOptionMenu(Pmenu);		//setup menu//~1527R~
          else                //the case scheduled before onWindowFocusChanged//~vay5I~
    		AxeMenu.staticCreateOptionMenu(Pmenu);		//setup menu   //~vay5I~
        }                                                          //~1527I~
        catch(Exception e)                                         //~1527I~
        {                                                          //~1527I~
        	Dump.println(e,"onCreateOptionMenu");                  //~1527I~
        }                                                          //~1527I~
    	return true;                                               //~1526I~
	}                                                              //~1526I~
//**                                                               //~1526I~
    @Override                                                      //~1526I~
    public boolean onOptionsItemSelected(MenuItem Pitem)           //~1527R~
	{                                                              //~1526I~
    	try                                                        //~1527I~
        {                                                          //~1527I~
	    	AxeG.axeMenu.onOptionMenuSelected(Pitem);		//setup menu//~1527R~
        }                                                          //~1527I~
        catch(Exception e)                                         //~1527I~
        {                                                          //~1527I~
        	Dump.println(e,"onCreateOptionItemSelected");          //~1527I~
        }                                                          //~1527I~
    	return true;                                               //~1526I~
	}                                                              //~1526I~
//**ContextMenu***********************                             //~1527I~
    @Override                                                      //~1527I~
    public void onCreateContextMenu(ContextMenu Pmenu,View Pview,ContextMenuInfo Pinfo)//~1527I~
    {                                                              //~1527I~
    	contextMenuShowing=true;
    	menuCreated=Pmenu;//~1930I~
    	try                                                        //~1527I~
        {                                                          //~1527I~
		    super.onCreateContextMenu(Pmenu,Pview,Pinfo);          //~1527I~
	        AxeG.axeMenu.onCreateContextMenu(Pmenu,Pview,Pinfo);      //~1527I~
        }                                                          //~1527I~
        catch(Exception e)                                         //~1527I~
        {                                                          //~1527I~
        	Dump.println(e,"onCreateContextMenu");                 //~1527I~
        }                                                          //~1527I~
    }                                                              //~1527I~
//*************************                                        //~1527I~
    @Override                                                      //~1527I~
    public boolean onContextItemSelected(MenuItem Pitem)           //~1527I~
    {                                                              //~1527I~
        boolean rc=false;                                          //~1527I~
    	try                                                        //~1527I~
        {                                                          //~1527I~
            rc=AxeG.axeMenu.onContextItemSelected(Pitem);             //~1527I~
            if (!rc)    //not processed                            //~1527I~
                rc=super.onContextItemSelected(Pitem);             //~1527I~
        }                                                          //~1527I~
        catch(Exception e)                                         //~1527I~
        {                                                          //~1527I~
        	Dump.println(e,"onContextItemSelected");               //~1527I~
        }                                                          //~1527I~
        return rc;                                                 //~1527I~
    }                                                              //~1527I~
//*************************                                        //~1930I~
    @Override                                                      //~1930I~
    public void onContextMenuClosed(Menu Pmenu)                     //~1930I~
    {                                                              //~1930I~
        if (Dump.Y) Dump.println("Axe:OnContextMenuClose");         //~1930I~
    	contextMenuShowing=false;                                  //~1930I~
    	try                                                        //~1930I~
        {                                                          //~1930I~
            AxeG.axeMenu.onContextMenuClosed(Pmenu);               //~1930I~
        }                                                          //~1930I~
        catch(Exception e)                                         //~1930I~
        {                                                          //~1930I~
        	Dump.println(e,"onContextMenuClosed");                 //~1930I~
        }                                                          //~1930I~
    }                                                              //~1930I~
//*************************                                        //~1530I~
    @Override                                                      //~1530I~
    public void onWindowFocusChanged(boolean Phasfocus)            //~1530I~
    {                                                              //~1530I~
    	try                                                        //~1714I~
        {                                                          //~1714I~
//            if (AxeG.axeView==null)                              //~1715R~
//            {                                                    //~1715R~
//                if (AxeView.isMeasured())                        //~1715R~
//                {                                                //~1715R~
//                    AxeG.axeView=new AxeView(); //view size available//~1715R~
//                    AxeG.axeView.initScreen();  //set axeView!=NULL before JNI call//~1715R~
//                }                                                //~1715R~
//            }                                                    //~1715R~
//                if (AxeView.isMeasured())                        //~1715I~
//                {                                                //~1715I~
//                    AxeG.axeView=new AxeView(); //view size available//~1715I~
//                    AxeG.axeView.initScreen();  //set axeView!=NULL before JNI call//~1715I~
//                }                                                //~1715I~
            View v=getCurrentFocus();                              //~vaiqI~
        	if (Dump.Y) Dump.println("Axe.onWindowFocusChanged focus="+Phasfocus+",view="+(v==null?"null":v.toString()));//~vaiqR~//~vc1bR~
        	if (Dump.Y) Dump.println("Axe.onWindowFocusChanged initFirsttime="+initFirsttime+",scrinitialized="+scrinitialized+",initAlertDialog2="+initAlertDialog2);//~vc2KI~
				if (AxeG.axeView==null)                            //~vaiqI~
                {                                                  //~vaiqI~
		        	if (Dump.Y) Dump.println("Axe.onWindowFocusChanged axeView=null");//~vaiqR~//~vc1bR~
                	if (initFirsttime==1)                          //~vaiqI~
                    {                                              //~vaiqI~
			        	if (Dump.Y) Dump.println("Axe.onWindowFocusChanged init=1");//~vaiqR~//~vc26R~
		            	initFirsttime=2;                           //~vaiqR~
    	    			AxeView.waitInitializeWarning();           //~vaiqR~
//                      return;                                    //~vaiqI~//~vc2UR~
                    }                                              //~vaiqI~
			        if (Dump.Y) Dump.println("Axe.onWindowFocusChanged new AxeView");//~vaiqR~//~vc1bR~
					AxeG.axeView=new AxeView();                    //~vaiqI~
			        if (Dump.Y) Dump.println("Axe.onWindowFocusChanged new AxeView end");//~vaiqR~//~vc1bR~
                  if (initFirsttime!=0)                            //~vc2UI~
                  {                                                //~vc2UI~
                    initFirsttime=3;	//jni init end             //~vaiqI~
                    return;                                        //~vc2UI~
                  }                                                //~vc2UI~
                }                                                  //~vaiqI~
    	    	if (Dump.Y) Dump.println("Axe.onWindowFocusChanged initAlertDialog2="+initAlertDialog2);//~vc2UI~
		        if (initAlertDialog2!=null)                        //~vaiqI~
    				dismissInitAlertDialog();                      //~vaiqI~
			  if (!scrinitialized)                              //~1715I~
                  if (AxeView.isMeasured())                        //~1715I~
                  {                                                //~1715I~
                      scrinitialized=AxeG.axeView.initScreen();  //set axeView!=NULL before JNI call//~1715I~
                  }                                                //~1715I~
//          View v=getCurrentFocus();      	                       //~vaiqR~
            if (v==AxeG.axeScreen)                                 //~1827I~
            	AxeG.axeScreen.windowFocusChanged(Phasfocus);      //~1827I~
            if (Phasfocus && contextMenuShowing)  //disabled menuitem is so far clickable and menu is closed when clicked and oncontextmenuclosed is not calle//~1930R~
            {                                                      //~1930I~
            	AxeG.axeMenu.onContextMenuClosed(menuCreated);           //~1930I~
    			menuCreated=null;	//for GC                       //~1930I~
        	}                                                      //~1930I~
        }                                                          //~1714I~
        catch(Exception e)                                         //~1714I~
        {                                                          //~1714I~
        	Dump.println(e,"Axe.onWindowFocusChanged");                //~1714I~//~vc26R~
        }                                                          //~1714I~
        if (Dump.Y) Dump.println("Axe.onWindowFocusChanged "+Phasfocus); //~1530I~//~vc2UR~
        if (Dump.Y) Dump.println("forcus view="+getCurrentFocus());//~1530I~
        Configuration cfg=AxeG.resource.getConfiguration();        //~vaiqI~
        if (Dump.Y) Dump.println("Axe.onWindowFocusChanged keyboard="+Integer.toHexString(cfg.keyboard));//~vaiqI~//~vc1bR~//~vc2UR~
        if (Dump.Y) Dump.println("Axe.onWindowFocusChanged keyboardHidden="+Integer.toHexString(cfg.keyboardHidden));//~vaiqI~//~vc1bR~//~vc2UR~
        if (Dump.Y) Dump.println("Axe.onWindowFocusChanged hardKeyboardHidden="+Integer.toHexString(cfg.hardKeyboardHidden));//~vaiqI~//~vc1bR~//~vc2UR~
        if (Dump.Y) Dump.println("Axe.onWindowFocusChanged screenLayout="+Integer.toHexString(cfg.screenLayout));//~vaiqI~//~vc1bR~//~vc2UR~
    }                                                              //~1530I~
//**************************************                           //~1527I~
//*Config changed                                                  //~1527I~
//**************************************                           //~1527I~
    @Override                                                      //~1526I~
    public void onConfigurationChanged(Configuration Pcfg)         //~1526I~
	{                                                              //~1526I~
    	super.onConfigurationChanged(Pcfg);                        //~1526I~
        boolean orichanged=(Pcfg.orientation==Configuration.ORIENTATION_PORTRAIT)!=(AxeG.displayPL==0);//~1606R~
        if (Dump.Y) Dump.println("Axe.onConfigurationChanged orichanged="+orichanged);//~vc1bR~
        if (Dump.Y) Dump.println("Axe.onConfigurationChanged keyboard="+Integer.toHexString(Pcfg.keyboard));//~vai8I~//~vc1bR~
        if (Dump.Y) Dump.println("Axe.onConfigurationChanged keyboardHidden="+Integer.toHexString(Pcfg.keyboardHidden));//~vai8I~//~vc1bR~
        if (Dump.Y) Dump.println("Axe.onConfigurationChanged hardKeyboardHidden="+Integer.toHexString(Pcfg.hardKeyboardHidden));//~vai8I~//~vc1bR~
        if (Dump.Y) Dump.println("Axe.onConfigurationChanged screenLayout="+Integer.toHexString(Pcfg.screenLayout));//~vai8I~//~vc1bR~
        boolean swChangeKbd=(Pcfg.hardKeyboardHidden!=AxeG.hardKeyboard);                      //~vc1rR~//~vc1tI~
        AxeG.setHardKeyboard(Pcfg);                                //~vc1tI~
//      if (!AxeDlgKbdLayoutHW.configChanged(swChangeKbd))	//AxeDlgKbdLayout is not opened//~vc1tI~//~vc26R~
//          AxeScreen.configChanged(); 	//open keyboardHW               //~vc1rR~//~vc1tR~//~vc26I~
        AxeKbdDialogHW.configChanged(swChangeKbd);	//AxeDlgKbdLayout is not opened//~vc26R~
        if (orichanged)                                            //~1606R~
        {                                                          //~vaicI~
    		axeStat|=AXES_ORIENTATION_CHANGING;                    //~vaicI~
        	if (scrinitialized)                                    //~1606R~
            {                                                      //~1821I~
              try                                                  //~vayeI~
              {                                                    //~vayeI~
	        	AxeG.axeView.orientationChanged();                 //~1821R~
              }                                                    //~vayeI~
        	  catch(Exception e)                                   //~vayeI~
              {                                                    //~vayeI~
        		Dump.println(e,"onConfigurationChanged");          //~vayeI~
        	  }                                                    //~vayeI~
            }                                                      //~1821I~
    		axeStat&=~AXES_ORIENTATION_CHANGING;                   //~vaicI~
        }                                                          //~vaicI~
	}                                                              //~1526I~
//**************************************                           //~1A02I~
    @Override                                                      //~vaiqM~
    public void onStart()                                          //~vaiqM~
	{                                                              //~vaiqM~
    	super.onStart();                                           //~vaiqM~
        if (Dump.Y) Dump.println("onStart");                       //~vaiqM~
//      View v=getCurrentFocus();                                  //~vaiqM~
//     	AxeG.mainView.requestFocus();                              //~vaiqM~
    	AxeG.axeBCR.onStart();                                     //~vc2KI~
	}                                                              //~vaiqM~
    @Override                                                      //~1A02I~
    public void onPause()                                          //~1A02I~
    {                                                              //~1A02I~
        if (Dump.Y) Dump.println("onPause");                       //~1A02I~
    	super.onPause();                                           //~1A02I~
    	AxeG.axeBCR.onPause();                                     //~vc2KI~
    }                                                              //~1A02I~
    @Override                                                      //~1A02I~
    public void onResume()                                         //~1A02I~
    {                                                              //~1A02I~
        if (Dump.Y) Dump.println("onResume");                      //~1A02I~
    	super.onResume();                                          //~1A02I~
    	AxeG.axeBCR.onResume();                                    //~vc2KI~
//      View v=getCurrentFocus();                                  //~vaiqR~
//   	AxeG.mainView.requestFocus();                              //~vaiqR~
		if (aAxePermission==null)                                  //~vc1bI~
        {                                                          //~vc1bI~
	        aAxePermission=new AxePermission();    //grant permission//~vc1bI~
	        aAxePermission.request();                              //~vc1bI~
        }                                                          //~vc1bI~
    }                                                              //~1A02I~
    //****************************************************         //~1A09I~
    //*when Home button pressed onUserLeaveHint is called          //~1A09I~
    //* onUserLeaveHint-->onSaveInstanceState-->onPause            //~1A09I~
    //* And it could be resumed by long-press Home key             //~1A09I~
    //****************************************************         //~1A09I~
    @Override                                                      //~1A09I~
    public void onUserLeaveHint()                                  //~1A09I~
    {                                                              //~1A09I~
        if (Dump.Y) Dump.println("onUserLeaveHint");               //~1A09I~
//        AxeG.axeMenu.optionMenuConfirmStop();	could not wait     //~1A09R~
    	super.onUserLeaveHint();                                   //~1A09I~
    }                                                              //~1A09I~
    @Override                                                      //~1A09I~
    public void onSaveInstanceState(Bundle Pbundle)                //~1A09I~
    {                                                              //~1A09I~
        if (Dump.Y) Dump.println("onSaveInstanceState");           //~1A09I~
    	super.onSaveInstanceState(Pbundle);                        //~1A09I~
    }                                                              //~1A09I~
//**************************************                           //~1607I~
    @Override                                                      //~1607I~
    public void onDestroy()                                        //~1607I~
	{                                                              //~1607I~
        super.onDestroy();                                         //~1607I~
    	 destroying=true;                                           //~1607I~
    	AxeG.axeBCR.onDestroy();                                   //~vc2KI~
        if (Dump.Y) Dump.println("OnDestroy configchange="+Integer.toHexString(getChangingConfigurations())+",isfinishing="+isFinishing()+",initfirsttime sw="+initFirsttime);//~vayeR~
        Dump.close();                                              //~1607I~
//    if (isFinishing())                                           //~vayeR~
//language change calls onDestry , app restart after it.           //~vayeI~
//thaen cause illeagal state exception at AxeView:initScreen.add screen to frame(already parented)//~vayeI~
        Utils.exit(0,true);	//System.exit() to kill myself to clear static variable//~1607I~
    }                                                              //~1607I~
//*********                                                        //~1607I~
    public static boolean isTerminating()                          //~1607I~
    {                                                              //~1607I~
    	return destroying;                                         //~1607I~
    }                                                              //~1607I~
//*********************************************                    //~1607I~
//* callback after OnKey if the view has focus                     //~vc1rR~
//*********************************************                    //~1607I~
    @Override	// Activity.KeyEventCallback                       //~vc1rI~
    public boolean onKeyDown(int keyCode,KeyEvent event)           //~1607I~
	{                                                              //~1607I~
        if (Dump.Y) Dump.println("Axe.onKeyDown");                 //~vc1rI~
        if (!scrinitialized)                                       //~1607I~
            return false;                                          //~1607I~
        try                                                        //~1607I~
        {                                                          //~1607I~
            return AxeG.axeKeyAction.onKeyDown(keyCode,event);     //~1607I~
        }                                                          //~1607I~
        catch (Exception e)                                        //~1607I~
        {                                                          //~1607I~
        	Dump.println(e,"Ajagoc:OnKeyDown");                    //~1607I~
        }                                                          //~1607I~
        return false;                                              //~1607I~
    }                                                              //~1607I~
//******************                                               //~vc1rI~
    @Override	// Activity.KeyEventCallback                       //~vc1rR~
    public boolean onKeyUp(int keyCode,KeyEvent event)             //~1607I~
	{                                                              //~1607I~
        if (Dump.Y) Dump.println("Axe.onKeyUp");                   //~vc1rI~
        if (!scrinitialized)                                       //~1607I~
            return false;                                          //~1607I~
        try                                                        //~1607I~
        {                                                          //~1607I~
            return AxeG.axeKeyAction.onKeyUp(keyCode,event);    //true//~1607I~
        }                                                          //~1607I~
        catch (Exception e)                                        //~1607I~
        {                                                          //~1607I~
        	Dump.println(e,"Ajagoc:OnKeyUp");                      //~1607I~
        }                                                          //~1607I~
        return false;                                              //~1607I~
    }                                                              //~1607I~
//******************                                               //~0914I~//~1621I~
    @Override                                                      //~0914I~//~1621I~
    public boolean onTouchEvent(MotionEvent event)                 //~1621I~
	{                                                              //~1621I~
    	//********************                                         //~@@@@I~//~1621I~
        if (Dump.Y) Dump.println("Axe.TouchEvent");                //~vc1rI~
        if (!scrinitialized)                                       //~1621I~
            return false;                                          //~1621I~
        try                                                        //~1621I~
        {                                                          //~1621I~
            return AxeG.axeMouse.onTouchMain(event);               //~1922R~
        }                                                          //~1621I~
        catch (Exception e)                                        //~1621I~
        {                                                          //~1621I~
        	Dump.println(e,"Axe:OnTouch");                         //~1621I~
        }                                                          //~1621I~
        return false;                                              //~@@@@I~//~1621I~
    }                                                              //~0914I~//~1621I~
    //********************                                         //~1A02I~
    public void pauseActivity()                                   //~1A02I~
    {                                                              //~1A02I~
        Intent intent=new Intent(Intent.ACTION_MAIN,null);         //~1A02I~
        intent.addCategory(Intent.CATEGORY_HOME);                  //~1A02I~
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);//~1A02I~
        startActivity(intent);                                     //~1A02I~
    }                                                              //~1A02I~
    //******************************************                   //~vaiqI~
    public int alertOnShow(AxeAlert Paxealert,boolean Pdismiss)                     //~vaiqI~
    {                                                              //~vaiqI~
        if (Dump.Y) Dump.println("Axe.alertOnShow dismiss="+Pdismiss+",initsw="+initFirsttime+",initComp="+AxeProp.initCompleted); //~vaiqR~//+vc2UR~
	    if (initFirsttime==1)	//shown "start firsttime init"     //~vaiqR~
        {                                                          //~vaiqI~
        	if (Dump.Y) Dump.println("Axe.alertOnShow req dismiss 1"); //~vaiqR~//+vc2UR~
          if (!Pdismiss)                                           //+vc2UI~
	        Paxealert.pdlg.dismiss();  //onece lose focus to schedule onfocuschanged//~vaiqR~
        }                                                          //~vaiqI~
        else                                                       //~vaiqI~
//      if (initFirsttime==2)	//shown "waiting init"             //~vaiqI~//~vc2UR~
        if (initFirsttime==2	//shown "waiting init"             //~vc2UI~
        ||  initFirsttime==3)	//shown "waiting init"             //~vc2UI~
        {                                                          //~vaiqI~
//            if (AxeG.axeView==null)                              //~vaiqR~
//            {                                                    //~vaiqR~
//                if (Dump.Y) Dump.println("alertOnShow new AxeView");//~vaiqR~
//                AxeG.axeView=new AxeView();                      //~vaiqR~
//                if (Dump.Y) Dump.println("alertOnShow new AxeView end");//~vaiqR~
//            }                                                    //~vaiqR~
            if (Pdismiss)                                          //~vaiqI~
    	    	initAlertDialog2=null;	//user pushed "close" button//~vaiqI~
            else                                                   //~vaiqI~
            {                                                      //+vc2UI~
    	    	initAlertDialog2=Paxealert;                        //~vaiqR~
        		if (AxeProp.initCompleted)                         //+vc2UI~
			    	initComp();	//didmiss alert dialog to onwindowfocuschanged//+vc2UI~
            }                                                      //+vc2UI~
                                                                   //+vc2UI~
        }                                                          //~vaiqI~
        return 0;                                                  //~vaiqI~
    }                                                              //~vaiqI~
    //******************************************                   //~vc2UI~
    public static void initComp()                                  //~vc2UI~
    {                                                              //~vc2UM~
        if (Dump.Y) Dump.println("Axe.initComp initsw="+AxeG.main.initFirsttime);//+vc2UR~
        AxeG.main.dismissInitAlertDialog();                         //~vc2UI~
    }                                                              //~vc2UM~
    //******************************************                   //~vaiqI~
    private void dismissInitAlertDialog()                          //~vaiqR~
    {                                                              //~vaiqI~
        if (Dump.Y) Dump.println("Axe.dismissInitAlertDialog dislog2="+(initAlertDialog2==null?"null":"not null")+",initcomp="+AxeProp.initCompleted+",initsw="+initFirsttime);//~vaiqR~//+vc2UR~
        if (initAlertDialog2!=null)                                //~vaiqI~
        {                                                          //~vaiqI~
            if (AxeProp.initCompleted                              //~vaiqR~
            &&  initFirsttime==3)                                  //~vaiqI~
            {                                                      //~vaiqI~
    	    	if (Dump.Y) Dump.println("Axe.dismissInitAlertDialog dismiss dislog2");//~vaiqR~//+vc2UR~
	        	initAlertDialog2.pdlg.dismiss();  //dismiss waiting//~vaiqR~
	        	initAlertDialog2=null;                             //~vaiqR~
            }                                                      //~vaiqI~
        }                                                          //~vaiqI~
    }                                                              //~vaiqI~
    //******************************************                   //~vaiqI~
	@Override                                                      //~vaiqI~
	public int alertButtonAction(int Pbuttonid, int Pitempos) {    //~vaiqI~
		return 0;                                                  //~vaiqI~
	}                                                              //~vaiqI~
    //******************************************                   //~vayaI~
	@Override                                                      //~vayaI~
	public boolean onMenuItemSelected(int Pfeature,MenuItem Pitem) //~vayaI~
    {                                                              //~vayaI~
		if (Pitem.getItemId()==android.R.id.home)                  //~vayaI~
        {                                                          //~vayaI~
	    	AxeG.axeMenu.onClickHomeButton();                      //~vayaI~
        	return true;	//exausted                             //~vayaI~
        }                                                          //~vayaI~
        return super.onMenuItemSelected(Pfeature,Pitem);           //~vayaI~
	}                                                              //~vayaI~
//***********************************************************      //~@@@@I~//~@@@2I~//~@@@@I~//~vc10I~
    public void destroyClose()                                     //~@@@@I~//~@@@2I~//~@@@@I~//~vc10I~
    {                                                              //~@@@@I~//~@@@2I~//~@@@@I~//~vc10I~
        if (Dump.Y) Dump.println("Axe.destroyClose");//~@@@@I~//~@@@2I~//~@@@@I~//~vc10I~
        AG.status=AG.STATUS_STOPFINISH;                               //~@@@@I~//~vc10I~
	    URunnable.setRunFunc(this/*RunnableI*/,0/*sleep*/,null/*parm*/,0/*phase*/);//~@@@@R~//~@@@2I~//~@@@@I~//~vc10I~
    }                                                              //~@@@@I~//~@@@2I~//~@@@@I~//~vc10I~
//*************************                                        //~@@@@I~//~@@@2I~//~@@@@I~//~vc10I~
//*callback from Runnable *                                        //~@@@@I~//~@@@2I~//~@@@@I~//~vc10I~
//*************************                                        //~@@@@I~//~@@@2I~//~@@@@I~//~vc10I~
    public void runFunc(Object Pparmobj,int Pphase)                //~@@@@I~//~@@@2I~//~@@@@I~//~vc10I~
    {                                                              //~@@@@I~//~@@@2I~//~@@@@I~//~vc10I~
        int wait=0,wait2=100;                                   //~@@@2I~//~@@@@R~//~vc10I~
    //*********************                                        //~@@@2I~//~@@@@I~//~vc10I~
    	if (Dump.Y) Dump.println("Axe.destroyClose runfunc phase="+Pphase);   //~@@@@I~//~@@@2I~//~@@@@I~//~vc10I~
    	if (Pphase==0)	//initial call,close socket streamIO       //~@@@@I~//~@@@2I~//~@@@@I~//~vc10I~
        {                                                          //~@@@@I~//~@@@2I~//~@@@@I~//~vc10I~
	    	if (Dump.Y) Dump.println("Axe.destroyClose runfunc phase=0 closeStream");//~@@@@I~//~@@@2I~//~@@@@I~//~vc10I~//~vc2UR~
		    URunnable.setRunFunc(this/*RunnableI*/,wait2/*sleep ms*/,null/*parm*/,1/*phase*/);//~@@@@R~//~@@@2I~//~@@@@R~//~vc10I~
	        return;                                                //~@@@@R~//~@@@2I~//~@@@@I~//~vc10I~
        }                                                          //~@@@@I~//~@@@2I~//~@@@@I~//~vc10I~
    	if (Pphase==1)	//stop timer,board                         //~@@@2I~//~@@@@I~//~vc10I~
        {                                                          //~@@@@I~//~@@@2I~//~@@@@I~//~vc10I~
    		if (Dump.Y) Dump.println("Axe.destroyClose runfunc phase=1 stopBoard");//~@@@@I~//~@@@2I~//~@@@@I~//~vc10I~//~vc2UR~
            URunnable.setRunFunc(this/*RunnableI*/,wait/*sleep ms*/,null/*parm*/,2/*phase*/);//~@@@@R~//~@@@2I~//~@@@@I~//~vc10I~
            return;                                            //~@@@@I~//~@@@2I~//~@@@@I~//~vc10I~
        }                                                          //~@@@@I~//~@@@2I~//~@@@@I~//~vc10I~
    	if (Pphase==2)	//stop timer,board                         //~@@@2I~//~@@@@I~//~vc10I~
        {                                                          //~@@@2I~//~@@@@I~//~vc10I~
    		if (Dump.Y) Dump.println("Axe.destroyClose runfunc phase=2 stop BT");//~@@@@I~//~vc10I~//~vc2UR~
            URunnable.setRunFunc(this/*RunnableI*/,wait/*sleep ms*/,null/*parm*/,3/*phase*/);//~@@@2I~//~@@@@I~//~vc10I~
            return;                                                //~@@@2I~//~@@@@I~//~vc10I~
        }                                                          //~@@@2I~//~@@@@I~//~vc10I~
    	if (Dump.Y) Dump.println("destroyClose runfunc phase=3"); //~@@@@I~//~@@@2I~//~@@@@R~//~vc10I~
		finish();	//shedule ondestroy                            //~@@@2I~//~@@@@I~//~vc10I~
    }                                                              //~@@@@I~//~@@@2I~//~@@@@I~//~vc10I~
//***************************************************************************//~9930I~//~1AhdI~//~vc1bI~
	@Override                                                      //~vc1bI~
    public void onRequestPermissionsResult(int PrequestID,String[] Ptypes,int[] Presults)//~vc1bI~
    {                                                              //~vc1bI~
    	try                                                        //~vc1bI~
        {                                                          //~vc1bI~
        	if (Dump.Y) Dump.println("Axe.onRequestPermissionResult reqid="+PrequestID+",type="+ com.ahsv.utils.Utils.toString(Ptypes)+",result="+ Arrays.toString(Presults));//~vc1bR~//~vc2UR~
            if (Presults.length!=0)                                 //~vc1bI~
	        	aAxePermission.onRequestPermissionsResult(PrequestID,Ptypes,Presults);//~vc1bR~
        }                                                          //~vc1bI~
        catch(Exception e)                                         //~vc1bI~
        {                                                          //~vc1bI~
        	Dump.println(e,"Axe.onRequestPermissionsResult");          //~vc1bI~//~vc2UR~
        }                                                          //~vc1bI~
    }                                                              //~vc1bI~
//**********************************                               //~vc1rI~
    protected void hideSoftKbd()                                   //~vc1rI~
    {                                                              //~vc1rI~
	    if (Dump.Y) Dump.println("Axe.hideSoftKbd"); //~vc1rI~     //~vc2UR~
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);//~vc1rI~
    }                                                              //~vc1rI~
}//class                                                           //~1621R~