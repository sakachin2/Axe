//*CID://+vc11R~:                             update#=   38;       //+vc1mR~
//**********************************************************************//~vag0I~
//vc11 2020/06/14 add indeterminate=false(having max value) type to ProgressDialog//~vc11I~
//vag0:120719 (Axe)function to download asset from web             //~vag0I~
//**********************************************************************//~vag0I~
//*Download control                                                //~vag0R~
//**********************************************************************//~vag0I~
package com.xe.Axe;
//****************************************************************
//*foloowings are copyed at April 17,2011 from http://www.hassanpur.com
//****************************************************************
/**
 * Copyright (c) 2011 Mujtaba Hassanpur.
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the &quot;Software&quot;), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED &quot;AS IS&quot;, WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
//package com.hassanpur.tutorials.android;
import android.app.Activity;
import android.app.Dialog;
//import android.app.ProgressDialog;
//import android.os.Bundle;                                        //~vag0R~
import android.os.Handler;
import android.os.Message;
//import android.view.View;                                        //~vag0R~
//import android.view.View.OnClickListener;                        //~vag0R~
//import android.widget.Button;                                    //~vag0R~
//import android.widget.EditText;                                  //~vag0R~
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ahsv.AG;
import com.xe.Axe.R;
//public class AndroidFileDownloader extends Activity implements OnClickListener//~vag0R~
public class AndroidFileDownloader                                 //~vag0I~
	extends Dialog                                                 //~vc11I~
{
	private static final int LAYOUTID=R.layout.progdlg_h;          //~vc11I~
	// Used to communicate state changes in the DownloaderThread
	public static final int MESSAGE_DOWNLOAD_STARTED = 1000;
	public static final int MESSAGE_DOWNLOAD_COMPLETE = 1001;
	public static final int MESSAGE_UPDATE_PROGRESS_BAR = 1002;
	public static final int MESSAGE_DOWNLOAD_CANCELED = 1003;
	public static final int MESSAGE_CONNECTING_STARTED = 1004;
	public static final int MESSAGE_ENCOUNTERED_ERROR = 1005;
	public static final int MESSAGE_DOWNLOAD_COMPLETE2= 1006;      //~vag0I~
	
	// instance variables
//    private AndroidFileDownloader thisActivity;                    //~vag0R~
    private Activity thisActivity;                                 //~vag0I~
//  private Thread downloaderThread;                               //~vag0R~
    public  Thread downloaderThread;                               //~vag0I~
//  private ProgressDialog progressDialog;                         //~vc11R~
    private ProgressBar progressDialog,progressDialogH;                            //~vc11I~
	
	private TextView tvMsg;                                        //~vc11I~
	private ViewGroup layoutView;                                  //~vc11I~
	private Dialog androidDialog;                                  //~vc11I~
	private Message cancelMsg;                                     //~vc11I~
	private boolean swDismiss;                                     //~vc11I~
//*****************************************************************://~vc11I~
    /** Called when the activity is first created. */
//    @Override                                                    //~vag0R~
//    public void onCreate(Bundle savedInstanceState)              //~vag0R~
//    {                                                            //~vag0R~
//        super.onCreate(savedInstanceState);                      //~vag0R~
//        thisActivity = this;                                     //~vag0R~
//        downloaderThread = null;                                 //~vag0R~
//        progressDialog = null;                                   //~vag0R~
//        setContentView(R.layout.main);                           //~vag0R~
//        Button button = (Button) this.findViewById(R.id.download_button);//~vag0R~
//        button.setOnClickListener(this);                         //~vag0R~
//    }                                                            //~vag0R~
//    /** Called when the user clicks on something. */             //~vag0R~
//    @Override                                                    //~vag0R~
//    public void onClick(View view)                               //~vag0R~
//    {                                                            //~vag0R~
//        EditText urlInputField = (EditText) this.findViewById(R.id.url_input);//~vag0R~
//        String urlInput = urlInputField.getText().toString();    //~vag0R~
//        downloaderThread = new DownloaderThread(thisActivity, urlInput);//~vag0R~
//        downloaderThread.start();                                //~vag0R~
//    }                                                            //~vag0R~
	
//***********************************************************      //~vc11I~
	public AndroidFileDownloader()                                 //~vc11I~
    {                                                              //~vc11I~
    	super(AG.context);                                         //~vc11I~
        initAndroid();                                             //~vc11I~
    }                                                              //~vc11I~
//***********************************************************      //~vc11I~
	private void initAndroid()                                     //~vc11I~
    {                                                              //~vc11I~
    	layoutView=(ViewGroup)(AG.inflater.inflate(LAYOUTID,null));//~vc11I~
        setContentView(layoutView);                                //~vc11I~
        progressDialog=(ProgressBar)layoutView.findViewById(R.id.progress_bar);//~vc11I~
        progressDialogH=(ProgressBar)layoutView.findViewById(R.id.progress_bar_h);//~vc11I~
        tvMsg=(TextView)layoutView.findViewById(R.id.progMsg);     //~vc11I~
        androidDialog=this;                                        //~vc11I~
    }                                                              //~vc11I~
	public static AndroidFileDownloader onCreate(Activity Pactivity,String Purl,String Plocalfile)//~vag0R~
    {                                                              //~vag0I~
    	AndroidFileDownloader downloader=new AndroidFileDownloader();//~vag0R~
        downloader.thisActivity = Pactivity;                       //~vag0R~
        downloader.downloaderThread=new DownloaderThread(Pactivity,downloader.activityHandler,Purl,Plocalfile);//~vag0R~
        return downloader;                                         //~vag0I~
    }                                                              //~vag0I~
                                                                   //~vag0I~
	/**
	 * This is the Handler for this activity. It will receive messages from the
	 * DownloaderThread and make the necessary updates to the UI.
	 */
	public Handler activityHandler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
				/*
				 * Handling MESSAGE_UPDATE_PROGRESS_BAR:
				 * 1. Get the current progress, as indicated in the arg1 field
				 *    of the Message.
				 * 2. Update the progress bar.
				 */
				case MESSAGE_UPDATE_PROGRESS_BAR:
					if(progressDialog != null)
					{
						int currentProgress = msg.arg1;
						progressDialog.setProgress(currentProgress);
					}
					break;
				
				/*
				 * Handling MESSAGE_CONNECTING_STARTED:
				 * 1. Get the URL of the file being downloaded. This is stored
				 *    in the obj field of the Message.
				 * 2. Create an indeterminate progress bar.
				 * 3. Set the message that should be sent if user cancels.
				 * 4. Show the progress bar.
				 */
				case MESSAGE_CONNECTING_STARTED:
					if(msg.obj != null && msg.obj instanceof String)
					{
						String url = (String) msg.obj;
						// truncate the url
						if(url.length() > 16)
						{
							String tUrl = url.substring(0, 15);
							tUrl += "...";
							url = tUrl;
						}
						String pdTitle = thisActivity.getString(R.string.progress_dialog_title_connecting);
						String pdMsg = thisActivity.getString(R.string.progress_dialog_message_prefix_connecting);
						pdMsg += " " + url;
						
						dismissCurrentProgressDialog();
//  					progressDialog = new ProgressDialog(thisActivity);//~vc11R~
//  					progressDialog.setTitle(pdTitle);          //~vc11R~
    					androidDialog.setTitle(pdTitle);           //~vc11I~
//  					progressDialog.setMessage(pdMsg);          //~vc11R~
    					tvMsg.setText(pdMsg);                   //~vc11I~
//  					progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//~vc11R~
//  					progressDialog.setIndeterminate(true);     //~vc11R~
                        progressDialogH.setVisibility(View.GONE);     //~vc11I~
						// set the message to be sent when this dialog is canceled
						Message newMsg = Message.obtain(this, MESSAGE_DOWNLOAD_CANCELED);
    					cancelMsg=newMsg;                          //~vc11I~
//  					progressDialog.setCancelMessage(newMsg);   //~vc11R~
//  					progressDialog.show();                     //~vc11R~
    					androidDialog.show();                      //~vc11I~
					}
					break;
					
				/*
				 * Handling MESSAGE_DOWNLOAD_STARTED:
				 * 1. Create a progress bar with specified max value and current
				 *    value 0; assign it to progressDialog. The arg1 field will
				 *    contain the max value.
				 * 2. Set the title and text for the progress bar. The obj
				 *    field of the Message will contain a String that
				 *    represents the name of the file being downloaded.
				 * 3. Set the message that should be sent if dialog is canceled.
				 * 4. Make the progress bar visible.
				 */
				case MESSAGE_DOWNLOAD_STARTED:
					// obj will contain a String representing the file name
					if(msg.obj != null && msg.obj instanceof String)
					{
						int maxValue = msg.arg1;
						String fileName = (String) msg.obj;
						String pdTitle = thisActivity.getString(R.string.progress_dialog_title_downloading);
						String pdMsg = thisActivity.getString(R.string.progress_dialog_message_prefix_downloading);
						pdMsg += " " + fileName;
						
                        progressDialog.setVisibility(View.GONE);      //~vc11I~
                        progressDialog=progressDialogH;            //~vc11I~
						dismissCurrentProgressDialog();
//  					progressDialog = new ProgressDialog(thisActivity);//~vc11R~
//  					progressDialog.setTitle(pdTitle);          //~vc11R~
    					androidDialog.setTitle(pdTitle);           //~vc11I~
//  					progressDialog.setMessage(pdMsg);          //~vc11R~
    					tvMsg.setText(pdMsg);                      //~vc11I~
//  					progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//~vc11R~
						progressDialog.setProgress(0);
						progressDialog.setMax(maxValue);
						// set the message to be sent when this dialog is canceled
						Message newMsg = Message.obtain(this, MESSAGE_DOWNLOAD_CANCELED);
//  					progressDialog.setCancelMessage(newMsg);   //~vc11R~
    					cancelMsg=newMsg;                          //~vc11I~
//  					progressDialog.setCancelable(true);        //~vc11R~
    					androidDialog.setCancelable(true);         //~vc11I~
//  					progressDialog.show();                     //~vc11R~
    					androidDialog.show();                      //~vc11I~
					}
					break;
				
				/*
				 * Handling MESSAGE_DOWNLOAD_COMPLETE:
				 * 1. Remove the progress bar from the screen.
				 * 2. Display Toast that says download is complete.
				 */
				case MESSAGE_DOWNLOAD_COMPLETE:
					dismissCurrentProgressDialog();
//					displayMessage(getString(R.string.user_message_download_complete));//~vag0R~
					displayMessage(thisActivity.getString(R.string.user_message_download_complete));//~vag0I~
    				msg = Message.obtain(activityHandler,MESSAGE_DOWNLOAD_COMPLETE2);//~vag0I~
    				activityHandler.sendMessage(msg);	//after dismiss//~vag0I~
					break;
				case MESSAGE_DOWNLOAD_COMPLETE2:                   //~vag0I~
                    AxeDownload.complete();	//start unzip	       //~vag0M~
					break;                                         //~vag0I~
				/*
				 * Handling MESSAGE_DOWNLOAD_CANCELLED:
				 * 1. Interrupt the downloader thread.
				 * 2. Remove the progress bar from the screen.
				 * 3. Display Toast that says download is complete.
				 */
				case MESSAGE_DOWNLOAD_CANCELED:
					if(downloaderThread != null)
					{
						downloaderThread.interrupt();
					}
					dismissCurrentProgressDialog();
//  				displayMessage(getString(R.string.user_message_download_canceled));//~vag0R~
    				displayMessage(thisActivity.getString(R.string.user_message_download_canceled));//~vag0I~
					break;
				
				/*
				 * Handling MESSAGE_ENCOUNTERED_ERROR:
				 * 1. Check the obj field of the message for the actual error
				 *    message that will be displayed to the user.
				 * 2. Remove any progress bars from the screen.
				 * 3. Display a Toast with the error message.
				 */
				case MESSAGE_ENCOUNTERED_ERROR:
					// obj will contain a string representing the error message
					if(msg.obj != null && msg.obj instanceof String)
					{
						String errorMessage = (String) msg.obj;
						dismissCurrentProgressDialog();
						displayMessage(errorMessage);
					}
					break;
					
				default:
					// nothing to do here
					break;
			}
		}
	};
	
	/**
	 * If there is a progress dialog, dismiss it and set progressDialog to
	 * null.
	 */
	public void dismissCurrentProgressDialog()
	{
//  	if(progressDialog != null)                                 //~vc11R~
    	if(androidDialog != null)                                  //~vc11I~
		{
//  		progressDialog.hide();                                 //~vc11R~
//  		progressDialog.dismiss();                              //~vc11R~
//  		progressDialog = null;                                 //~vc11R~
          if (!swDismiss)                                          //~vc11I~
          {                                                        //~vc11I~
    		androidDialog.dismiss();                               //~vc11I~
            swDismiss=true;                                        //~vc11I~
          }                                                        //~vc11I~
		}
	}
	                                                                       
	/**
	 * Displays a message to the user, in the form of a Toast.
	 * @param message Message to be displayed.
	 */
	public void displayMessage(String message)
	{
		if(message != null)
		{
//  		Toast.makeText(thisActivity, message, Toast.LENGTH_SHORT).show();//~vag0R~
    			Toast.makeText(thisActivity, message, Toast.LENGTH_LONG).show();//~vag0I~
		}
	}
}
