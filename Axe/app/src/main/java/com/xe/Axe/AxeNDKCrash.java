//*CID://+vay0R~: update#=11;                                      //~vay0I~
//**********************************************************************//~vay0I~
//vay0:140710 (Axe)jni exception handling                          //~vay0I~
//**********************************************************************//~vay0I~
//*NDKCrash Activity                                               //~vay0I~
//**********************************************************************//~vay0I~
package com.xe.Axe;                                                //~vay0I~

import org.acra.ACRA;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;

public class AxeNDKCrash extends Activity {                        //~vay0R~

	private String msgText;                                        //~vay0R~
	private Context contextNDKCrash;                               //~vay0I~
    //===============================================================================//~vay0I~
	@Override
	protected void onCreate(Bundle savedInstanceState)             //~vay0R~
	{                                                              //~vay0I~
		super.onCreate(savedInstanceState);
    	System.out.println("AxeNDKCrash Activity onCreate");       //~vay0R~
        contextNDKCrash=(Context)this;                             //~vay0I~
        try                                                        //~vay0I~
        {                                                          //~vay0I~
			String msg=(String)getIntent().getSerializableExtra("error"); //~vay0R~
        	System.out.println("AxeNDKCrash:"+msg);                //~vay0R~
	    	umsgboxCrashReport(msg,0);                             //~vay0R~
        }                                                          //~vay0I~
        catch(Exception e)                                         //~vay0I~
        {                                                          //~vay0I~
        	Toast.makeText(this,"AxeNDKCrash Activity:get exception"+e.toString(),Toast.LENGTH_LONG).show();//~vay0I~
        }                                                          //~vay0I~
	}
//**************************************                           //~vay0I~
    @Override                                                      //~vay0I~
    public void onDestroy()                                        //~vay0I~
	{                                                              //~vay0I~
        super.onDestroy();                                         //~vay0I~
        System.out.println("AxeNDKCrash onDestroy");               //~vay0I~
        System.exit(8);//kill process                              //~vay0I~
    }                                                              //~vay0I~
    //===============================================================================//~4719M~
    //=!!! var in AxeG is now not available;such as resource       //~vay0I~
    //===============================================================================//~vay0I~
    private void umsgboxCrashReport(String Pmsg,int Pflag)         //~vay0R~
    {                                                              //~4719M~
//  	System.out.println("AxeNDKCrash umsgboxCrashReport");      //~vay0R~
//      Toast.makeText(this, "umsgbox", Toast.LENGTH_LONG).show(); //~vay0R~
		Resources  resource=getResources();                        //~vay0I~
//  	System.out.println("AxeNDKCrash umsgboxCrashReport resource:"+resource.toString());//~vay0R~
        msgText=resource.getString(R.string.NDKCrash_alertHeader)+"\n"+Pmsg;//+vay0R~
//      System.out.println("AxeNDKCrash umsgboxCrashReport msgText="+msgText);//~vay0R~
        String title=resource.getString(R.string.app_name);        //~vay0R~
//      System.out.println("AxeNDKCrash umsgboxCrashReport title="+title);//~vay0R~
		umsgboxdialogCrashReport(title,msgText);                   //~vay0R~
//      System.out.println("AxeNDKCrash after dialog show");       //~vay0R~
    }                                                              //~4719M~
    //===============================================================================//~vay0I~
    private void umsgboxdialogCrashReport(String Ptitle,String Pmsg)//~vay0R~
    {                                                              //~4719M~
//  	System.out.println("AxeNDKCrash umsgboxdialogCrashReport:title="+Ptitle+",msg="+Pmsg);//~vay0R~
		AlertDialog.Builder builder=new AlertDialog.Builder(this); //~4719R~
//  	System.out.println("AxeNDKCrash after builder");           //~vay0R~
    	builder.setMessage(Pmsg);                                  //~vay0R~
//  	System.out.println("AxeNDKCrash after setMsg");            //~vay0R~
       	setButton(builder);                                        //~vay0R~
//  	System.out.println("AxeNDKCrash after setButton");         //~vay0R~
       	AlertDialog pdlg=builder.create();                         //~vay0R~
//  	System.out.println("AxeNDKCrash dlg create");              //~vay0R~
    	pdlg.setTitle(Ptitle);                                      //~v@@@I~//~1211I~//~vay0I~
//  	System.out.println("AxeNDKCrash dlg settitle");            //~vay0R~
    	pdlg.show();                                               //~4719M~
//  	System.out.println("AxeNDKCrash umsgboxdialogCrashReport exit");//~vay0R~
    }                                                              //~4719M~
//**********************************                               //~4719M~
	private void setButton(AlertDialog.Builder Pbuilder)           //~vay0R~
    {                                                              //~4719M~
//  	System.out.println("AxeNDKCrash setButton");               //~vay0R~
    	Pbuilder.setPositiveButton("Close",new DialogInterface.OnClickListener()//~vay0R~
                                        {                          //~4719M~
                                                                   //~4719M~
                                            public void onClick(DialogInterface Pdlg,int buttonID)//~4719M~
                                            {                      //~4719M~
                                            	try                //~vay0I~
                                                {                  //~vay0I~
													System.out.println("AxeNDKCrash Diloag Close Button callback");//~vay0R~
        											sendACRAMail();//~vay0R~
        											finish();      //~vay0R~
                                                }                  //~vay0I~
                                                catch(Exception e) //~vay0I~
                                                {                  //~vay0I~
										        	Toast.makeText(contextNDKCrash,"AxeNDKCrash sendMail exception"+e.toString(),Toast.LENGTH_LONG).show();//~vay0I~
                                                }                  //~vay0I~
                                            }                      //~4719M~
                                        }                          //~4719M~
                                     );                            //~4719M~
   }//setButton                                                    //~4719M~
//**********************************                               //~vay0I~
    public void sendACRAMail()                                     //~vay0I~
    {                                                              //~vay0I~
		System.out.println("AxeNDKCrash sendMail\n");              //~vay0I~
		Error e=new Error("AxeNDKCrash:"+msgText);         //~vay0R~
        ACRA.getErrorReporter().putCustomData("error",msgText);    //~vay0I~
        ACRA.getErrorReporter().handleException(e);                //~vay0I~
    }                                                              //~vay0I~
}
