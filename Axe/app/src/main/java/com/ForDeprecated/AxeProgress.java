//*CID://+vc4dR~:                             update#=   46;       //+vc4dR~
//**********************************************************************//~vag0I~
//vc4d 2023/03/25 androd11(api30) deprecated at api30;Handler default constructor(requires parameter)//+vc4dI~
//vc11 2020/06/14 add indeterminate=false(having max value) type to ProgressDialog//~vc11R~
//vag0:120719 (Axe)function to download asset from web             //~vag0I~
//**********************************************************************//~vag0I~
//*control to display progress bar                                 //~vag0R~
//**********************************************************************//~vag0I~
package com.ForDeprecated;                                         //~vc11R~
import android.app.Dialog;                                         //~vc11R~

import com.ahsv.AG;
import com.xe.Axe.Dump;
import com.xe.Axe.R;                                               //~vc11R~
import com.xe.Axe.Utils;

import android.app.Activity;                                       //~vc11R~
//import android.app.ProgressDialog;                               //~vc11R~
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AxeProgress                                //~vag0I~
	extends Dialog                                                 //~vc11R~
{
	private static final int LAYOUTID=R.layout.progdlg_h;          //~vc11R~
                                                                   //~vc11R~
	public static final int MESSAGE_STARTED = 1000;                //~vag0R~
	public static final int MESSAGE_COMPLETE = 1001;               //~vag0R~
	public static final int MESSAGE_UPDATE = 1002;                 //~vag0R~
	public static final int MESSAGE_CANCELED = 1003;               //~vag0I~
	public static final int MESSAGE_ERROR = 1005;                  //~vag0R~
    private final static int MSGIDX_START=0;                       //~vag0I~
    private final static int MSGIDX_COMPLETE=1;                    //~vag0I~
    private final static int MSGIDX_CANCELED=2;                    //~vag0I~
    private final static int MSGIDX_ERROR=3;                       //~vag0I~
    public final static int MAXMSGTYPE=4;                          //~vag0I~
    public final static long INTMAX=0x7fffffff;                    //~vag0I~
    private int[] msgidtb={0,0,0,0};//start,complete,canceled,err//~vag0I~
    private int titlemsgid;                                        //~vag0I~
    private String objectname;                                     //~vag0I~
	
	public  Thread funcThread;                                     //~vag0R~
//  private ProgressDialog progressDialog;                         //~vc11R~
    private ProgressBar progressDialog;                            //~vc11R~
    private AxeProgress axeProgress;                               //~vc11R~
	private Handler handler;                 //~vag0I~
	private Activity activity; 
	private TextView tvMsg;                                        //~vc11R~
	private ViewGroup layoutView;                                  //~vc11R~
	private Message cancelMsg;                                     //~vc11R~
	private boolean swDismiss;                                     //~vc11I~
//***********************************************************      //~vc11R~
	public AxeProgress(Activity Pactivity)                         //~vag0R~
    {   
    	super(AG.context);                                         //~vc11R~
		activity=Pactivity;
		handler=new ProgressHandler(Pactivity);           //~vag0I~
        initAndroid();                                             //~vc11R~
    }                                                              //~vag0I~
//***********************************************************      //~vc11R~
	private void initAndroid()                                     //~vc11R~
    {                                                              //~vc11R~
    	layoutView=(ViewGroup)(AG.inflater.inflate(LAYOUTID,null));//~vc11R~
        setContentView(layoutView);                                //~vc11R~
//      androidDialog.setOnDismissListener((OnDismissListener) new dismissListener());//~vc11R~
        progressDialog=(ProgressBar)layoutView.findViewById(R.id.progress_bar);//~vc11R~
        progressDialog.setVisibility(View.GONE);                      //~vc11I~
        progressDialog=(ProgressBar)layoutView.findViewById(R.id.progress_bar_h);//~vc11I~
        tvMsg=(TextView)layoutView.findViewById(R.id.progMsg);     //~vc11R~
        axeProgress=this;                                          //~vc11R~
    }                                                              //~vc11R~
//***********************************************************      //~vag0I~
	public void setMsgid(int Ptitleid,String Pname,int[] Pmsgid)  //~vag0I~
    {                                                              //~vag0I~
    	titlemsgid=Ptitleid;                                       //~vag0I~
    	objectname=Pname;                                          //~vag0I~
    	int ctr=Pmsgid.length;                                     //~vag0I~
        for (int ii=0;ii<Math.min(ctr,MAXMSGTYPE);ii++)            //~vag0I~
        	msgidtb[ii]=Pmsgid[ii];                               //~vag0I~
    }                                                              //~vag0I~
//***********************************************************      //~vag0I~
	public boolean overrideFunction(AxeProgress.FuncThread Pthread)//~vag0R~
    {                                                              //~vag0I~
    	return true;                                               //~vag0I~
    }                                                              //~vag0I~
//***********************************************************      //~vag0I~
	public void startThread()                                      //~vag0I~
    {                                                              //~vag0I~
    	funcThread=new FuncThread(activity,handler,objectname);    //~vag0R~
    	funcThread.start();	//execute run()                        //~vag0R~
    }                                                              //~vag0I~
//***********************************************************      //~vag0I~
//*Handler                                                         //~vag0I~
//***********************************************************      //~vag0I~
	public class ProgressHandler extends Handler                   //~vag0I~
	{                                                              //~vag0I~
    	Activity thisActivity;
		public ProgressHandler(Looper Plooper)                     //+vc4dI~
        {                                                          //+vc4dI~
	        super(Plooper);                                            //~@@02I~//+vc4dI~
    	    if (Dump.Y) Dump.println("AxeProgress.ProgressHandler constructor looper="+Plooper.toString());//~@@02I~//+vc4dI~
        }                                                          //+vc4dI~
		public ProgressHandler(Activity Pactivity)                 //~vag0I~
        {                                                          //~vag0I~
        	this(Looper.getMainLooper());                         //+vc4dI~
        	thisActivity=Pactivity;                                //~vag0I~
        }                                                          //~vag0I~
		public void handleMessage(Message msg)                     //~vag0I~
		{                                                          //~vag0I~
			switch(msg.what)                                       //~vag0I~
			{                                                      //~vag0I~
				case MESSAGE_UPDATE:                               //~vag0I~
					if(progressDialog != null)                     //~vag0I~
					{                                              //~vag0I~
						int currentProgress = msg.arg1;            //~vag0I~
						progressDialog.setProgress(currentProgress);//~vag0I~
					}                                              //~vag0I~
					break;                                         //~vag0I~
                                                                   //~vag0I~
				case MESSAGE_STARTED:                              //~vag0I~
					// obj will contain a String representing the file name//~vag0I~
					if(msg.obj != null && msg.obj instanceof String)//~vag0I~
					{                                              //~vag0I~
						int maxValue = msg.arg1;                   //~vag0I~
						String fileName = (String) msg.obj;        //~vag0I~
						String pdTitle = thisActivity.getString(msgidtb[MSGIDX_START]);//~vag0I~
						String pdMsg = thisActivity.getString(titlemsgid);//~vag0I~
						pdMsg += " " + fileName;                 //~vag0I~
                                                                   //~vag0I~
						dismissCurrentProgressDialog();            //~vag0I~
//  					progressDialog = new ProgressDialog(thisActivity);//~vag0I~//~vc11R~
//  					progressDialog.setTitle(pdTitle);          //~vag0I~//~vc11R~
    					axeProgress.setTitle(pdTitle);             //~vc11R~
//  					progressDialog.setMessage(pdMsg);          //~vag0I~//~vc11R~
    					tvMsg.setText(pdMsg);                      //~vc11R~
//  					progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//~vag0I~//~vc11R~
						progressDialog.setProgress(0);             //~vag0I~
						progressDialog.setMax(maxValue);           //~vag0I~
						// set the message to be sent when this dialog is canceled//~vag0I~
						Message newMsg = Message.obtain(this, MESSAGE_CANCELED);//~vag0I~
//  					progressDialog.setCancelMessage(newMsg);   //~vag0I~//~vc11R~
    					cancelMsg=newMsg;                          //~vc11R~
//  					progressDialog.setCancelable(true);        //~vag0I~//~vc11R~
    					axeProgress.setCancelable(true);           //~vc11R~
//  					progressDialog.show();                     //~vag0I~//~vc11R~
    					axeProgress.show();                        //~vc11R~
					}                                              //~vag0I~
					break;                                         //~vag0I~
				case MESSAGE_COMPLETE:                             //~vag0I~
					dismissCurrentProgressDialog();                //~vag0I~
					displayMessage(thisActivity.getString(msgidtb[MSGIDX_COMPLETE]));//~vag0I~
					break;                                         //~vag0I~
				case MESSAGE_CANCELED:                    //~vag0I~
					if(funcThread != null)                         //~vag0I~
					{                                              //~vag0I~
						funcThread.interrupt();                    //~vag0I~
					}                                              //~vag0I~
					dismissCurrentProgressDialog();                //~vag0I~
    				displayMessage(thisActivity.getString(msgidtb[MSGIDX_CANCELED]));//~vag0I~
					break;                                         //~vag0I~
				case MESSAGE_ERROR:                                //~vag0I~
					// obj will contain a string representing the error message//~vag0I~
					if(msg.obj != null && msg.obj instanceof String)//~vag0I~
					{                                              //~vag0I~
						String errorMessage = (String) msg.obj;    //~vag0I~
						dismissCurrentProgressDialog();            //~vag0I~
						displayMessage(errorMessage);              //~vag0I~
					}                                              //~vag0I~
					break;                                         //~vag0I~
                                                                   //~vag0I~
				default:                                           //~vag0I~
					// nothing to do here                          //~vag0I~
					break;                                         //~vag0I~
			}                                                      //~vag0I~
		}                                                          //~vag0I~
//***********************************************************      //~vag0I~
        public void dismissCurrentProgressDialog()                 //~vag0I~
        {                                                          //~vag0I~
//          if(progressDialog != null)                             //~vag0I~//~vc11R~
            if(axeProgress != null)                                //~vc11R~
            {                                                      //~vag0I~
//              progressDialog.hide();                             //~vag0I~//~vc11R~
//              progressDialog.dismiss();                          //~vag0I~//~vc11R~
//              progressDialog = null;                             //~vag0I~//~vc11R~
          	  if (!swDismiss)                                      //~vc11I~
              {                                                    //~vc11I~
              	swDismiss=true;                                    //~vc11I~
                axeProgress.dismiss();                             //~vc11R~
              }                                                      //~vag0I~//~vc11R~
            }                                                      //~vc11I~
        }                                                          //~vag0I~
//***********************************************************      //~vag0I~
        public void displayMessage(String message)                 //~vag0I~
        {                                                          //~vag0I~
            if(message != null)                                    //~vag0I~
            {                                                      //~vag0I~
                Utils.showToastLong(message);                      //~vag0R~
            }                                                      //~vag0I~
        }                                                          //~vag0I~
	}//sub class                                                   //~vag0I~
//**************************************************************** //~vag0I~
//*function Thread                                                 //~vag0I~
//**************************************************************** //~vag0I~
    public class FuncThread extends Thread                         //~vag0R~
    {                                                              //~vag0I~
        private Activity parentActivity;                           //~vag0I~
        private Handler activityHandler;                           //~vag0I~
        private String objname;                                    //~vag0I~
		long unzipSize;                                            //~vag0R~
//**************************************************************** //~vag0I~
        public FuncThread(Activity inParentActivity,Handler Phandler,String Pname)//~vag0R~
        {                                                          //~vag0I~
            objname=Pname;                                         //~vag0I~
            parentActivity = inParentActivity;                     //~vag0I~
            activityHandler= Phandler;                             //~vag0I~
        }                                                          //~vag0I~
//**************************************************************** //~vag0I~
        public void funcStarted(String Pfilename,long Pfsz)        //~vag0R~
        {                                                          //~vag0I~
        	unzipSize=Pfsz;
        	int kb;//~vag0I~
        	if (unzipSize>INTMAX)                                  //~vag0R~
				kb=(int)(Pfsz/1024);                         //~vag0R~
            else                                                   //~vag0I~
            	kb=(int)unzipSize;                                 //~vag0I~
            Message msg = Message.obtain(activityHandler,MESSAGE_STARTED,kb,0,Pfilename);//~vag0R~
            activityHandler.sendMessage(msg);                      //~vag0I~
        }                                                          //~vag0I~
//**************************************************************** //~vag0I~
        public void funcCompleted()                                //~vag0R~
        {                                                          //~vag0I~
    		Message msg=Message.obtain(activityHandler,MESSAGE_COMPLETE);//~vag0I~
    		activityHandler.sendMessage(msg);                      //~vag0I~
        }                                                          //~vag0I~
//**************************************************************** //~vag0I~
        public void funcError()                                    //~vag0R~
        {                                                          //~vag0I~
            String errMsg = parentActivity.getString(msgidtb[MSGIDX_ERROR]);//~vag0I~
            Message msg = Message.obtain(activityHandler,MESSAGE_ERROR,0,0,errMsg);//~vag0I~
            activityHandler.sendMessage(msg);                      //~vag0I~
        }                                                          //~vag0I~
//**************************************************************** //~vag0I~
        public void funcError(String Pinfo)                        //~vag0I~
        {                                                          //~vag0I~
            String errMsg = parentActivity.getString(msgidtb[MSGIDX_ERROR])+":"+Pinfo;//~vag0I~
            Message msg = Message.obtain(activityHandler,MESSAGE_ERROR,0,0,errMsg);//~vag0I~
            activityHandler.sendMessage(msg);                      //~vag0I~
        }                                                          //~vag0I~
//**************************************************************** //~vag0I~
        public void funcUpdate(long Preadsz)                       //~vag0R~
        {                                                          //~vag0I~
        	int kb;
        	if (unzipSize>INTMAX)                                  //~vag0R~
				kb=(int)(Preadsz/1024);                        //~vag0I~
            else                                                   //~vag0I~
            	kb=(int)Preadsz;                                   //~vag0I~
    		Message msg = Message.obtain(activityHandler,MESSAGE_UPDATE,kb, 0);//~vag0I~
    		activityHandler.sendMessage(msg);                      //~vag0I~
        }                                                          //~vag0I~
//**************************************************************** //~vag0I~
        @Override                                                  //~vag0I~
        public void run()                                          //~vag0I~
        {                                                          //~vag0I~
            try                                                    //~vag0I~
            {                                                      //~vag0I~
            	overrideFunction(this);                            //~vag0I~
            }                                                      //~vag0I~
            catch(Exception e)                                     //~vag0I~
            {                                                      //~vag0I~
            	Dump.println(e,"AxeProgress-FuncThread");          //~vag0I~
		        funcError();                                       //~vag0I~
            }                                                      //~vag0I~
        }                                                          //~vag0I~
                                                                   //~vag0I~
    }                                                              //~vag0I~
    private void setCancelCallback()                               //~vc11R~
    {                                                              //~vc11R~
            axeProgress.setOnCancelListener                        //~vc11R~
            (                                                      //~vc11R~
                new DialogInterface.OnCancelListener()             //~vc11R~
                {                                                  //~vc11R~
                    @Override                                      //~vc11R~
                    public void onCancel(DialogInterface Pdlg)     //~vc11R~
                    {                                              //~vc11R~
                        if (Dump.Y) Dump.println("axeProgress onCancel");//~vc11R~
						handler.sendMessage(cancelMsg);            //~vc11R~
                        axeProgress.dismiss();                     //~vc11R~
                    }                                              //~vc11R~
                }                                                  //~vc11R~
            );                                                     //~vc11R~
        if (Dump.Y) Dump.println("axeProgress setCancelCallback"); //~vc11R~
    }                                                              //~vc11R~
}//class                                                           //~vag0R~
