//*CID://+vaxfR~: update#= 126;                                    //~vaxfR~
//**********************************************************************//~1107I~
//*AlerDialog                                                      //~1527R~
//**********************************************************************//~1107I~
//vaxf:140707 (Axe)change font for help dislog                     //~vaxfI~
//vaiq:130606 Axe:issue initial setup Alert(Be paitient)           //~vaiqI~
//**********************************************************************//~vaiqI~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1527R~


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;


//**********************************************************************//~1107I~
public class AxeAlert                                            //~1107R~//~1527R~
{                                                                  //~0914I~
	public static final int BUTTON_POSITIVE =0x01;                      //~1211I~//~1212R~
	public static final int BUTTON_NEGATIVE =0x02;
	public static final int BUTTON_NUTRAL   =0x04;//~1211I~//~1212R~
	public static final int BUTTON_CLOSE    =0x08;                         //~1211I~//~1212R~
	public static final int BUTTON_PAUSE    =0x0200;               //~1A02I~
	public static final int SHOW_DIALOG     =0x10;                 //~1212R~
	public static final int ITEM_SELECTED   =0x20;                       //~1211I~//~1212R~
	public static final int MULTIPLE_CHOICE =0x40;                     //~1211I~//~1212R~
	public static final int EXIT            =0x80;                 //~1212I~
	public static final int NO_TITLE        =0x0100;               //~1212I~
	public static final int CB_ONSHOW       =0x0400;               //~vaiqR~
	public static final int CB_DISMISS      =0x0800;               //~vaiqI~
	public static final String PREF_HELPTEXTSZ="HelpTextSize";     //~vaxfI~
	
	private AxeAlertI callback;                                    //~1527R~
	private int flag;                                              //~1425R~
    private	TextView helpTextView;                                 //~vaxfI~
    private int helpTextSz;                                        //~vaxfI~
                                                                   //~1211I~
  public                                                           //~vaiqI~
	AlertDialog pdlg;                                              //~1425R~
                                          //~1211R~
//******************                                               //~1121I~
//**********************************                               //~1211I~
//*                                                                //~1211R~
//**********************************                               //~1211I~
	public AxeAlert()                                              //~1527R~
    {                                                              //~1211I~
    }                                                              //~1211I~
	public AxeAlert(AxeAlertI Pcallback)                           //~1527R~
    {                                                              //~1211I~
    	callback=Pcallback;                                        //~1211I~
    }                                                              //~1211I~
//===============================================================================//~v@@@I~//~1212I~
//simple alertdialog                                               //~v@@@I~//~1212I~
//===============================================================================//~v@@@I~//~1212I~
//  public static void simpleAlertDialog(AxeAlertI Pcallback,String Ptitle,String Ptext,int Pflag)//~vaiqR~
    public static AxeAlert simpleAlertDialog(AxeAlertI Pcallback,String Ptitle,String Ptext,int Pflag)//~vaiqI~
    {                                                              //~1212I~
    //***********                                                  //~1212I~
    	AxeAlert ajagoalert=new AxeAlert(Pcallback);               //~1527R~
        ajagoalert.createAlertDialog(Ptitle,Ptext,Pflag);          //~1212I~
        return ajagoalert;                                         //~vaiqI~
    }                                                              //~1212I~
//===============================================================================//~1212I~
//  public static void simpleAlertDialog(AxeAlertI Pcallback,int Ptitleid,int Ptextid,int Pflag)//~vaiqR~
    public static AxeAlert simpleAlertDialog(AxeAlertI Pcallback,int Ptitleid,int Ptextid,int Pflag)//~vaiqI~
    {
    	String title;//~1212I~
    //***********                                                  //~1212I~
    	if (Ptitleid==0)                                           //~1604I~
        	title=null;                                             //~1604I~
        else                                                       //~1604I~
    		title=AxeG.resource.getString(Ptitleid);        //~1604R~
    	String text=AxeG.resource.getString(Ptextid);              //~1604I~
      AxeAlert ajagoalert=                                         //~vaiqI~
	    simpleAlertDialog(Pcallback,title,text,Pflag);             //~1604R~
        return ajagoalert;                                         //~vaiqI~
    }                                                              //~1212I~
//**********************************                               //~1211I~
//*simple msg popup                                                //~1211I~
//**********************************                               //~1211I~
	private void createAlertDialog(String Ptitle,String Ptext,int Pflag)//~1211R~//~1212R~
    {                                                              //~1211I~
		AlertDialog.Builder builder=new AlertDialog.Builder(AxeG.context);//~1211I~
    	builder.setMessage(Ptext);                                         //~v@@@I~//~1211I~
        setButton(builder,Pflag);                                  //~1212I~
    	pdlg=builder.create();                                                  //~v@@@I~//~1211I~
    	if (Ptitle!=null)                                              //~v@@@I~//~1211I~//~1212M~
    		pdlg.setTitle(Ptitle);                                      //~v@@@I~//~1211I~//~1212I~
        else                                                       //~1212M~
            if ((Pflag & NO_TITLE)!=0)                             //~1212M~
	        	pdlg.requestWindowFeature(Window.FEATURE_NO_TITLE);//~1212I~
            else                                                   //~1212M~
	    		pdlg.setTitle(AxeG.resource.getString(R.string.app_name));//~1212M~
        if ((Pflag & CB_ONSHOW)!=0)                                //~vaiqI~
        	setOnShow(this);                                       //~vaiqR~
        pdlg.setOnDismissListener(new AxeAlertDismissListener(this));   //~vaiqI~
        if (Dump.Y) Dump.println("AxeAlert.createAlertDialog text="+Ptext);//+vaxfI~
    	pdlg.show();                                                    //~v@@@I~//~1211I~//~1212R~
    }                                                              //~1211I~
//**********************************                               //~1212I~
	private void setButton(AlertDialog.Builder Pbuilder,int Pflag) //~1212I~
    {                                                              //~1212I~
    	flag=Pflag;                                                //~1212I~
        if ((Pflag & BUTTON_POSITIVE)!=0)                          //~1212I~
        {                                                          //~1212I~
            Pbuilder.setPositiveButton("OK",new DialogInterface.OnClickListener()//positive is 1st button//~1A02R~
                                        {                          //~1212I~
                                                                   //~1212I~
                                            public void onClick(DialogInterface Pdlg,int buttonID)//~1212I~
                                            {                      //~1212I~
										        callback(pdlg,callback,BUTTON_POSITIVE);//~1622I~
                                                if ((flag & EXIT)!=0)//~1212I~
	                                            	Utils.finish();//~1212I~//~1527R~
                                            }                      //~1212I~
                                        }                          //~1212I~
                                     );                            //~1212I~
        }                                                          //~1212I~
        if ((Pflag & BUTTON_CLOSE)!=0)                             //~1212I~
        {                                                          //~1212I~
            Pbuilder.setPositiveButton("Close",new DialogInterface.OnClickListener()//~1212I~
                                        {                          //~1212I~
                                                                   //~1212I~
                                            public void onClick(DialogInterface Pdlg,int buttonID)//~1212I~
                                            {                      //~1212I~
										        callback(pdlg,callback,BUTTON_CLOSE);//~1622I~
                                            }                      //~1212I~
                                        }                          //~1212I~
                                     );                            //~1212I~
        }                                                          //~1212I~
        if ((Pflag & BUTTON_PAUSE)!=0)                             //~1A02I~
        {                                                          //~1A02I~
            Pbuilder.setNeutralButton("Pause",new DialogInterface.OnClickListener()//neutral is 2n button//~1A02R~
                                        {                          //~1A02I~
                                                                   //~1A02I~
                                            public void onClick(DialogInterface Pdlg,int buttonID)//~1A02I~
                                            {                      //~1A02I~
										        callback(pdlg,callback,BUTTON_PAUSE);//~1A02I~
                                            }                      //~1A02I~
                                        }                          //~1A02I~
                                     );                            //~1A02I~
        }                                                          //~1A02I~
        if ((Pflag & BUTTON_NEGATIVE)!=0)                          //~1212I~
        {                                                          //~1212I~
            Pbuilder.setNeutralButton("No",new DialogInterface.OnClickListener()//~1622R~//~1A02R~
                                        {                          //~1212I~
                                                                   //~1212I~
                                            public void onClick(DialogInterface Pdlg,int buttonID)//~1212I~
                                            {                      //~1212I~
										        callback(pdlg,callback,BUTTON_NEGATIVE);//~1622I~
                                            }                      //~1212I~
                                        }                          //~1212I~
                                     );                            //~1212I~
        }                                                          //~1212I~
        if ((Pflag & BUTTON_NUTRAL)!=0)                            //~1622I~
        {                                                          //~1622I~
            Pbuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener()	//negative is 3rd button//~1A02R~
                                        {                          //~1622I~
                                                                   //~1622I~
                                            public void onClick(DialogInterface Pdlg,int buttonID)//~1622I~
                                            {                      //~1622I~
										        callback(pdlg,callback,BUTTON_NUTRAL);//~1622I~
                                            }                      //~1622I~
                                        }                          //~1622I~
                                     );                            //~1622I~
        }                                                          //~1622I~
   }//setButton                                                   //~1212I~
    private static void callback(AlertDialog Pdlg,AxeAlertI Pcallback,int Pbuttonid)//~1622I~
    {                                                          //~1622I~
        if (Dump.Y) Dump.println("AxeAlert callback");             //+vaxfI~
    	Pdlg.dismiss();                                        //~1622I~
        if (Pcallback!=null)                                   //~1622I~
        	Pcallback.alertButtonAction(Pbuttonid,0);     //~1622I~
    }                                                          //~1622I~
//**********************************************************************//~1211I~
	public void show()                                             //~1211I~
    {                                                              //~1211I~
		pdlg.show();                                               //~1211I~
    }                                                              //~1211I~
//**********************************************************************//~vaiqI~
	private void setOnShow(AxeAlert Paxealert)                   //~vaiqR~
    {                                                              //~vaiqI~
    	final AxeAlert axealert=Paxealert;                              //~vaiqI~
        pdlg.setOnShowListener(new DialogInterface.OnShowListener()//~vaiqI~
                                   {                               //~vaiqI~
            					   		@Override                  //~vaiqI~
                                        public void onShow(DialogInterface arg0)//~vaiqI~
                                        {                          //~vaiqI~
                                        	if (Dump.Y) Dump.println("AxeAlert onShow");//~vaiqI~
                                            if (callback!=null)    //~vaiqI~
								        		callback.alertOnShow(axealert,false/*show*/);//~vaiqR~
                                        }                          //~vaiqI~
                                   }                               //~vaiqI~
                              );                                   //~vaiqI~
	}                                                              //~vaiqI~
//*******************************                                  //~vaiqI~
//*dismiss listener                                                //~vaiqI~
//*******************************                                  //~vaiqI~
    public class AxeAlertDismissListener                           //~vaiqI~
    		implements OnDismissListener                           //~vaiqI~
	{                                                              //~vaiqI~
    	AxeAlert axealert;                                         //~vaiqI~
    	public AxeAlertDismissListener(AxeAlert Pdlg)                      //~vaiqI~
        {                                                          //~vaiqI~
        	axealert=Pdlg;                                         //~vaiqI~
        }                                                          //~vaiqI~
        @Override                                                  //~vaiqI~
        public void onDismiss(DialogInterface Pdialog)             //~vaiqI~
        {                                                          //~vaiqI~
			if (Dump.Y) Dump.println("AxeAlert onDismiss");        //~vaiqI~
    	    if ((axealert.flag & CB_ONSHOW)!=0)                    //~vaiqI~
            	if (axealert.callback!=null)                       //~vaiqI~
					axealert.callback.alertOnShow(axealert,true/*dismiss*/);//~vaiqR~
        }                                                          //~vaiqI~
    }                                                              //~vaiqI~
//*******************************                                  //~vaxfI~
//*HelpDialog                                                      //~vaxfR~
//*******************************                                  //~vaxfI~
    public static AxeAlert helpDialog(String Ptitle,String Ptext)  //~vaxfI~
    {                                                              //~vaxfI~
    	AxeAlert ajagoalert=new AxeAlert(null);                    //~vaxfI~
        int flag=0;                                                //~vaxfR~
        ajagoalert.createAlertDialogForHelp(Ptitle,Ptext,flag);    //~vaxfI~
        return ajagoalert;                                         //~vaxfI~
    }                                                              //~vaxfI~
	private void createAlertDialogForHelp(String Ptitle,String Ptext,int Pflag)//~vaxfI~
    {                                                              //~vaxfI~
    	flag=Pflag;                                                //~vaxfI~
		AlertDialog.Builder builder=new AlertDialog.Builder(AxeG.context);//~vaxfI~
        setButtonForHelp(builder,Pflag);                           //~vaxfI~
    	pdlg=builder.create();                                     //~vaxfI~
        setscrollview(pdlg,builder,Ptext);                         //~vaxfI~
    	if (Ptitle!=null)                                          //~vaxfI~
    		pdlg.setTitle(Ptitle);                                 //~vaxfI~
        else                                                       //~vaxfI~
            if ((Pflag & NO_TITLE)!=0)                             //~vaxfI~
	        	pdlg.requestWindowFeature(Window.FEATURE_NO_TITLE);//~vaxfI~
            else                                                   //~vaxfI~
	    		pdlg.setTitle(AxeG.resource.getString(R.string.app_name));//~vaxfI~
        setOnShowForHelp();                                        //~vaxfR~
        pdlg.setOnDismissListener(new AxeAlertDismissListener(this));//~vaxfI~
    	pdlg.show();                                               //~vaxfI~
    }                                                              //~vaxfI~
//**********************************                               //~vaxfI~
	private void setButtonForHelp(AlertDialog.Builder Pbuilder,int Pflag)//~vaxfI~
    {                                                              //~vaxfI~
        Pbuilder.setPositiveButton("+",null);	//prevent dismiss,use click listener//~vaxfR~
        Pbuilder.setNeutralButton("-",null);                       //~vaxfR~
        Pbuilder.setNegativeButton("Close",new DialogInterface.OnClickListener()	//negative is 3rd button//~vaxfR~
                                        {                          //~vaxfI~
                                                                   //~vaxfI~
                                            public void onClick(DialogInterface Pdlg,int buttonID)//~vaxfI~
                                            {                      //~vaxfI~
										        callbackForHelp(pdlg,BUTTON_NEGATIVE);//~vaxfI~
                                            }                      //~vaxfI~
                                        }                          //~vaxfI~
                                     );                            //~vaxfI~
    }//setButton                                                   //~vaxfI~
//**********************************                               //~vaxfI~
	private void setOnShowForHelp()                                       //~vaxfI~
    {                                                              //~vaxfI~
        pdlg.setOnShowListener(new DialogInterface.OnShowListener()//~vaxfI~
                                   {                               //~vaxfI~
            					   		@Override                  //~vaxfI~
                                        public void onShow(DialogInterface arg0)//~vaxfI~
                                        {                          //~vaxfI~
                                        	if (Dump.Y) Dump.println("AxeAlert onShow");//~vaxfI~
                                            setFontSzButton();     //~vaxfI~
                                        }                          //~vaxfI~
                                   }                               //~vaxfI~
                              );                                   //~vaxfI~
	}                                                              //~vaxfI~
//**********************************                               //~vaxfI~
	private void setFontSzButton()                                 //~vaxfI~
    {                                                              //~vaxfI~
    	Button btn;                                                //~vaxfI~
        btn=pdlg.getButton(AlertDialog.BUTTON_POSITIVE);           //~vaxfI~
        btn.setOnClickListener(new View.OnClickListener()//~vaxfI~
                                        {                          //~vaxfI~
                                            public void onClick(View Pbtn)//~vaxfI~
                                            {                      //~vaxfI~
										        callbackForHelp(pdlg,BUTTON_POSITIVE);//~vaxfI~
                                            }                      //~vaxfI~
                                        }                          //~vaxfI~
                                     );                            //~vaxfI~
        btn=pdlg.getButton(AlertDialog.BUTTON_NEUTRAL);             //~vaxfI~
        btn.setOnClickListener(new View.OnClickListener()//~vaxfI~
                                        {                          //~vaxfI~
                                            public void onClick(View Pbtn)//~vaxfI~
                                            {                      //~vaxfI~
										        callbackForHelp(pdlg,BUTTON_NUTRAL);//~vaxfR~
                                            }                      //~vaxfI~
                                        }                          //~vaxfI~
                                     );                            //~vaxfI~
    }                                                              //~vaxfI~
//**********************************                               //~vaxfI~
    private void callbackForHelp(AlertDialog Pdlg,int Pbuttonid)   //~vaxfI~
    {                                                              //~vaxfI~
    	switch(Pbuttonid)                                          //~vaxfI~
        {                                                          //~vaxfI~
        case BUTTON_POSITIVE:                                      //~vaxfI~
        	chngFontsz(1);                                         //~vaxfR~
        	break;                                                 //~vaxfI~
        case BUTTON_NUTRAL:                                        //~vaxfI~
        	chngFontsz(-1);                                        //~vaxfR~
        	break;                                                 //~vaxfI~
        case BUTTON_NEGATIVE:                                      //~vaxfM~
	    	Pdlg.dismiss();                                        //~vaxfM~
        	break;                                                 //~vaxfM~
        }                                                          //~vaxfI~
    }                                                              //~vaxfI~
//**********************************                               //~vaxfI~
    private boolean setscrollview(AlertDialog Pdialog,AlertDialog.Builder Pbuilder,String Ptext)//~vaxfI~
	{                                                              //~vaxfI~
    	TextView tv;                                               //~vaxfI~
    	ScrollView sv;                                             //~vaxfI~
        int sz;                                                    //~vaxfI~
    //*************************                                    //~vaxfI~
    	tv=new TextView(AxeG.context);                             //~vaxfI~
        helpTextView=tv;                                           //~vaxfI~
        tv.setText(Ptext);                                         //~vaxfM~
        helpTextSz=(int)tv.getTextSize();                          //~vaxfR~
        sz=AxeProp.getPreference(PREF_HELPTEXTSZ,0/*default*/);    //~vaxfI~
        if (sz!=0)                                                 //~vaxfI~
        {                                                          //~vaxfI~
        	tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,sz);         //~vaxfI~
	        sz=(int)tv.getTextSize();                              //~vaxfI~
        }                                                          //~vaxfI~
        helpTextSz=sz;                                             //~vaxfI~
        AxeProp.putPreference(PREF_HELPTEXTSZ,helpTextSz);         //~vaxfI~
    	sv=new ScrollView(AxeG.context);                           //~vaxfR~
    	sv.addView(tv);                                            //~vaxfR~
        Pdialog.setView(sv);                                       //~vaxfR~
        return true;//~vaxfI~
    }//setscrollview                                               //~vaxfI~
//**********************************                               //~vaxfI~
    private void chngFontsz(int Pincrement)                        //~vaxfI~
	{                                                              //~vaxfI~
    //*************************                                    //~vaxfI~
    	helpTextSz+=Pincrement;                                    //~vaxfI~
        helpTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,helpTextSz);//~vaxfR~
        helpTextView.invalidate();                                           //~vaxfI~
        AxeProp.putPreference(PREF_HELPTEXTSZ,helpTextSz);         //~vaxfI~
    }//setscrollview                                               //~vaxfI~
}//class AxeAlert                                                  //~1527R~
