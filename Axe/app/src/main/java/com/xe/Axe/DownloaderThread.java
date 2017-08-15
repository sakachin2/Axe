//*CID://+vag0R~:                             update#=   34;       //~vag0I~
//**********************************************************************//~vag0I~
//vag0:120719 (Axe)function to download asset from web             //~vag0I~
//**********************************************************************//~vag0I~
//*DownloaderThread                                                //~vag0R~
//**********************************************************************//~vag0I~
package com.xe.Axe;
import android.app.Activity;                                       //~vag0I~
//****************************************************************
//*foloowings are copyed at April 17,2011 from http://www.hassanpur.com
//****************************************************************
/**
 * Copyright (c) 2011 Mujtaba Hassanpur.
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
//package com.hassanpur.tutorials.android;                         //~vag0R~
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
//import android.os.Environment;                                   //~vag0R~
import android.os.Handler;
import android.os.Message;
/**
 * Downloads a file in a thread. Will send messages to the
 * AndroidFileDownloader activity to update the progress bar.
 */
public class DownloaderThread extends Thread
{
	// constants
	private static final int DOWNLOAD_BUFFER_SIZE = 4096;
    private int connTimeout=60;	//60sec                            //~vag0R~
	
	// instance variables
//  private AndroidFileDownloader parentActivity;                  //~vag0R~
    private Activity parentActivity;                               //~vag0I~
	private Handler activityHandler;                               //~vag0I~
	private String downloadUrl;
	private String localFile;                                      //~vag0I~
	
	/**
	 * Instantiates a new DownloaderThread object.
	 * @param parentActivity Reference to AndroidFileDownloader activity.
	 * @param inUrl String representing the URL of the file to be downloaded.
	 */
//  public DownloaderThread(AndroidFileDownloader inParentActivity, String inUrl)//~vag0R~
    public DownloaderThread(Activity inParentActivity,Handler Phandler,String inUrl,String PlocalFile)//~vag0R~
	{
    	localFile=PlocalFile;                                      //~vag0I~
		downloadUrl = "";
		if(inUrl != null)
		{
			downloadUrl = inUrl;
		}
		parentActivity = inParentActivity;
		activityHandler= Phandler;                                 //~vag0I~
	}
	
	/**
	 * Connects to the URL of the file, begins the download, and notifies the
	 * AndroidFileDownloader activity of changes in state. Writes the file to
	 * the root of the SD card.
	 */
	@Override
	public void run()
	{
		URL url;
		URLConnection conn;
		int fileSize, lastSlash;
		String fileName;
		BufferedInputStream inStream;
		BufferedOutputStream outStream;
		File outFile;
		FileOutputStream fileStream;
		Message msg;
        int repeatfreq=50;	//progress bar update max 50 times     //~vag0I~
		
		// we're going to connect now
//  	msg = Message.obtain(parentActivity.activityHandler,       //~vag0R~
    	msg = Message.obtain(activityHandler,                      //~vag0I~
				AndroidFileDownloader.MESSAGE_CONNECTING_STARTED,
				0, 0, downloadUrl);
//  	parentActivity.activityHandler.sendMessage(msg);           //~vag0R~
    	activityHandler.sendMessage(msg);                          //~vag0I~
		
		try
		{
			url = new URL(downloadUrl);
			conn = url.openConnection();
            if (Dump.Y)	Dump.println("DownloaderThread Timeout old="+conn.getConnectTimeout()+",set="+connTimeout*1000);//~vag0I~
            conn.setConnectTimeout(connTimeout*1000);           //~vag0I~
			conn.setUseCaches(false);
			fileSize = conn.getContentLength();
			
			// get the filename
			lastSlash = url.toString().lastIndexOf('/');
			fileName = "file.bin";
			if(lastSlash >=0)
			{
				fileName = url.toString().substring(lastSlash + 1);
			}
			if(fileName.equals(""))
			{
				fileName = "file.bin";
			}
			
			// notify download start
//  		int fileSizeInKB = fileSize / 1024;                    //~vag0R~
    		int fileSizeInKB = fileSize;                           //~vag0I~
//  		msg = Message.obtain(parentActivity.activityHandler,   //~vag0R~
    		msg = Message.obtain(activityHandler,                  //~vag0I~
					AndroidFileDownloader.MESSAGE_DOWNLOAD_STARTED,
					fileSizeInKB, 0, fileName);
//  		parentActivity.activityHandler.sendMessage(msg);       //~vag0R~
    		activityHandler.sendMessage(msg);                      //~vag0I~
			
			// start download
			inStream = new BufferedInputStream(conn.getInputStream());
//  		outFile = new File(Environment.getExternalStorageDirectory() + "/" + fileName);//~vag0R~
    		outFile = new File(localFile);                         //~vag0R~
			fileStream = new FileOutputStream(outFile);
			outStream = new BufferedOutputStream(fileStream, DOWNLOAD_BUFFER_SIZE);
			byte[] data = new byte[DOWNLOAD_BUFFER_SIZE];
			int bytesRead = 0, totalRead = 0;
                    int maxfreq=(int) (fileSize/DOWNLOAD_BUFFER_SIZE);  //repeat cont//~vag0I~
                    if (maxfreq>repeatfreq)                        //~vag0I~
                    	maxfreq/=maxfreq/repeatfreq;               //~vag0I~
                    else                                           //~vag0I~
                    	maxfreq=1;                                 //~vag0I~
                    int msgfreq=0;                                 //~vag0I~
			while(!isInterrupted() && (bytesRead = inStream.read(data, 0, data.length)) >= 0)
			{
				outStream.write(data, 0, bytesRead);
				
				// update progress bar
				totalRead += bytesRead;
              if (msgfreq==0)                                      //~vag0I~
              {                                                    //~vag0I~
//  			int totalReadInKB = totalRead / 1024;              //~vag0R~
    			int totalReadInKB = totalRead;                     //~vag0I~
//  			msg = Message.obtain(parentActivity.activityHandler,//~vag0R~
    			msg = Message.obtain(activityHandler,              //~vag0I~
						AndroidFileDownloader.MESSAGE_UPDATE_PROGRESS_BAR,
						totalReadInKB, 0);
//  			parentActivity.activityHandler.sendMessage(msg);   //~vag0R~
    			activityHandler.sendMessage(msg);                  //~vag0I~
              }                                                    //~vag0I~
                msgfreq++;                                         //~vag0I~
                msgfreq%=maxfreq;	//each 100K                    //~vag0I~
			}
			
			outStream.close();
			fileStream.close();
			inStream.close();
			
			if(isInterrupted())
			{
				// the download was canceled, so let's delete the partially downloaded file
				outFile.delete();
			}
			else
			{
				// notify completion
//  			msg = Message.obtain(parentActivity.activityHandler,//~vag0R~
    			msg = Message.obtain(activityHandler,              //~vag0I~
						AndroidFileDownloader.MESSAGE_DOWNLOAD_COMPLETE);
//  			parentActivity.activityHandler.sendMessage(msg);   //~vag0R~
    			activityHandler.sendMessage(msg);                  //~vag0I~
			}
		}
		catch(MalformedURLException e)
		{
			String errMsg = parentActivity.getString(R.string.error_message_bad_url);
//  		msg = Message.obtain(parentActivity.activityHandler,   //~vag0R~
    		msg = Message.obtain(activityHandler,                  //~vag0I~
					AndroidFileDownloader.MESSAGE_ENCOUNTERED_ERROR,
					0, 0, errMsg);
//  		parentActivity.activityHandler.sendMessage(msg);       //~vag0R~
    		activityHandler.sendMessage(msg);                      //~vag0I~
		}
		catch(FileNotFoundException e)
		{
			String errMsg = parentActivity.getString(R.string.error_message_file_not_found);
//  		msg = Message.obtain(parentActivity.activityHandler,   //~vag0R~
    		msg = Message.obtain(activityHandler,                  //~vag0I~
					AndroidFileDownloader.MESSAGE_ENCOUNTERED_ERROR,
					0, 0, errMsg);
//  		parentActivity.activityHandler.sendMessage(msg);       //~vag0R~
    		activityHandler.sendMessage(msg);                      //~vag0I~
		}
		catch(IOException e)                                       //~vag0I~
		{                                                          //~vag0I~
	        Dump.println(e,"DownloaderThread IOE:"+downloadUrl);   //~vag0I~
			String errMsg;                                         //~vag0I~
			errMsg = parentActivity.getString(R.string.error_message_connerr);//~vag0R~
            String info=e.getMessage();                                     //~vag0I~
            if (info!=null)                                        //~vag0I~
            	errMsg+=":"+info;                                  //~vag0I~
    		msg = Message.obtain(activityHandler,                  //~vag0I~
					AndroidFileDownloader.MESSAGE_ENCOUNTERED_ERROR,//~vag0I~
					0, 0, errMsg);                                 //~vag0I~
    		activityHandler.sendMessage(msg);                      //~vag0I~
		}                                                          //~vag0I~
		catch(Exception e)
		{
	        Dump.println(e,"DownloaderThread otherEE:"+downloadUrl);//~vag0I~
			String errMsg = parentActivity.getString(R.string.error_message_general);
            String info=e.getMessage();                            //+vag0I~
            if (info!=null)                                        //+vag0I~
            	errMsg+=":"+info;                                  //+vag0I~
//  		msg = Message.obtain(parentActivity.activityHandler,   //~vag0R~
    		msg = Message.obtain(activityHandler,                  //~vag0I~
					AndroidFileDownloader.MESSAGE_ENCOUNTERED_ERROR,
					0, 0, errMsg);
//  		parentActivity.activityHandler.sendMessage(msg);       //~vag0R~
    		activityHandler.sendMessage(msg);                      //~vag0I~
		}
	}
	public void setTimeout(int Psec)                               //~vag0R~
    {                                                              //~vag0I~
    	connTimeout=Psec;                                          //~vag0I~
    }                                                              //~vag0I~
}
