//*CID://+vag0R~:                             update#=   37;       //~vag0I~
//**********************************************************************//~vag0I~
//vag0:120719 (Axe)function to download asset from web             //~vag0I~
//**********************************************************************//~vag0I~
//*control to display progress bar                                 //~vag0R~
//**********************************************************************//~vag0I~
package com.xe.Axe;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
public class AxeProgress                                //~vag0I~
{
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
	private ProgressDialog progressDialog;
	private Handler handler;                 //~vag0I~
	private Activity activity; 
	public AxeProgress(Activity Pactivity)                         //~vag0R~
    {   
		activity=Pactivity;
		handler=new ProgressHandler(Pactivity);           //~vag0I~
    }                                                              //~vag0I~
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
		public ProgressHandler(Activity Pactivity)                 //~vag0I~
        {                                                          //~vag0I~
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
						progressDialog = new ProgressDialog(thisActivity);//~vag0I~
						progressDialog.setTitle(pdTitle);          //~vag0I~
						progressDialog.setMessage(pdMsg);          //~vag0I~
						progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//~vag0I~
						progressDialog.setProgress(0);             //~vag0I~
						progressDialog.setMax(maxValue);           //~vag0I~
						// set the message to be sent when this dialog is canceled//~vag0I~
						Message newMsg = Message.obtain(this, MESSAGE_CANCELED);//~vag0I~
						progressDialog.setCancelMessage(newMsg);   //~vag0I~
						progressDialog.setCancelable(true);        //~vag0I~
						progressDialog.show();                     //~vag0I~
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
            if(progressDialog != null)                             //~vag0I~
            {                                                      //~vag0I~
                progressDialog.hide();                             //~vag0I~
                progressDialog.dismiss();                          //~vag0I~
                progressDialog = null;                             //~vag0I~
            }                                                      //~vag0I~
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
//**************************************************************** //+vag0I~
        public void funcError(String Pinfo)                        //+vag0I~
        {                                                          //+vag0I~
            String errMsg = parentActivity.getString(msgidtb[MSGIDX_ERROR])+":"+Pinfo;//+vag0I~
            Message msg = Message.obtain(activityHandler,MESSAGE_ERROR,0,0,errMsg);//+vag0I~
            activityHandler.sendMessage(msg);                      //+vag0I~
        }                                                          //+vag0I~
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
}//class                                                           //~vag0R~
