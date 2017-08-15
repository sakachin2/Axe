//*CID://+vae0R~:                             update#=    1;       //+vae0I~
//********************************************************************//+vae0I~
//vae0:120508 (Axe)crash when Codepage==""(uerrexit cause sigsegv at next JNI call(AxeChild is not on manifest))//+vae0I~
//********************************************************************//+vae0I~
package com.xe.Axe;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;                                      //~1623I~
import android.widget.LinearLayout;                                //~1623I~
import android.widget.TextView;                                    //~1623I~
import android.view.View;
import android.view.Window;

public class AxeChild extends Activity                             //~1623R~
    implements View.OnClickListener                                //~1623I~
{                                                                  //~1623I~
    Button btn;                                                    //~1623I~
    TextView textv;                                                //~1623I~
    @Override                                                      //~1526I~
    public void onCreate(Bundle icicle)                            //~1526I~
	{                                                              //~1526I~
    	super.onCreate(icicle);                                    //~1526I~
        System.out.println("Axechild OnCreate");                   //~1623I~
    	requestWindowFeature(Window.FEATURE_LEFT_ICON);            //~1526I~
//      setResult(Activity.RESULT_CANCELED);                       //~1623R~
        Bundle bundle=getIntent().getExtras();	                   //~1623I~
        String uerrexitmsg="unknown";                              //~1623I~
        if (bundle!=null)                                          //~1623I~
        	uerrexitmsg=bundle.getString("uerrexitmsg");          //~1623I~
//                                                                 //~1623I~
        LinearLayout layout=new LinearLayout(this);                //~1623I~
        layout.setOrientation(LinearLayout.VERTICAL);              //~1623I~
        setContentView(layout);                                    //~1623I~
        textv=new TextView(this);                                  //~1623I~
        textv.setText(uerrexitmsg);                                //~1623I~
        layout.addView(textv);                                     //+vae0I~
        btn=new Button(this);                                      //~1623I~
        btn.setText("OK");                                         //~1623I~
        btn.setOnClickListener(this);                              //~1623I~
        layout.addView(btn);                                       //~1623I~
        System.out.println("Axechild OnCreate end");               //~1623I~
    }                                                              //~1526I~
//********                                                         //~1623I~
    public void onClick(View v)                                    //~1623I~
    {                                                              //~1623I~
        System.out.println("Axechild OnClick");                    //~1623I~
    	finish();                                                  //~1623I~
    }                                                              //~1623I~
//**************************************                           //~1607I~
    @Override                                                      //~1607I~
    public void onDestroy()                                        //~1607I~
	{                                                              //~1607I~
        super.onDestroy();                                         //~1607I~
        System.out.println("Axechild onDestroy");                  //~1623I~
        System.exit(8);//kill process                      //~1623I~
    }                                                              //~1607I~
}//class                                                           //~1621R~