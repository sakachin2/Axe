//*CID://+vag0R~:                             update#=   43;       //~vag0I~
//**********************************************************************//~vag0I~
//vag0:120719 (Axe)function to download asset from web             //~vag0I~
//**********************************************************************//~vag0I~
//*AxeDownload                                                     //~vag0R~
//**********************************************************************//~vag0I~
package com.xe.Axe;                                                //~@@@@I~

import java.io.File;

public class AxeDownload                                           //~vag0R~
{                                                                  //~1606I~
    private static String localPath,localFile;                            //~vag0R~
    private	static String fileName="";                                    //~vag0I~
    private	static final int unziptitleid=R.string.progress_dialog_title_unzipping;//~vag0I~
    private static final int[] msgidtb={                          //~vag0I~
    				R.string.progress_dialog_message_prefix_unzipping,	//started//~vag0I~
    				R.string.user_message_unzip_complete,          //~vag0I~
    				R.string.user_message_unzip_canceled,          //~vag0I~
    				R.string.error_message_unzip_general           //~vag0I~
                    };                                             //~vag0I~
    private static AxeDownload axedownload;                                                               //~vag0I~
    private static boolean swunzip,swdelzip;                       //~vag0R~
//**********************************************************************//~1725I~
    public AxeDownload()                                           //~vag0R~
    {
    	axedownload=this;//~1725I~
    }              
    //*********************************                                //~vag0I~
    public static void download(String Purl,String Plocal,boolean Punzip,boolean Pdelzip)//~vag0R~
    {                                                              //~vag0I~
        if (Purl==null||Purl.equals(""))                           //~vag0I~
        {                                                          //~vag0I~
			Utils.showToastLong(R.string.error_message_bad_url);   //~vag0I~
        	return;                                                //~vag0I~
        }                                                          //~vag0I~
        AxeDownload axedl=new AxeDownload();
        swunzip=Punzip;
        swdelzip=Pdelzip;                                          //~vag0I~
	    localFile=axedl.getTargetFile(Purl,Plocal);                //~vag0R~
        if (localPath==null)                                 //~vag0I~
        {                                                          //~vag0I~
			Utils.showToastLong(R.string.error_message_bad_url);   //~vag0I~
        	return;                                           //~vag0I~
        }                                                          //~vag0I~
    	AndroidFileDownloader dl=AndroidFileDownloader.onCreate(AxeG.activity,Purl,localFile);//~vag0R~
        DownloaderThread dlt=(DownloaderThread)(dl.downloaderThread);//~vag0I~
        dlt.start();                                               //~vag0I~
    }                                                              //~vag0I~
//*********************************                                //~vag0I~
//*unziponly                                                       //+vag0I~
//*********************************                                //+vag0I~
    public static void unzip(String Purl,String Plocal,boolean Pdelzip)//~vag0I~
    {                                                              //~vag0I~
//        if (Purl==null||Purl.equals(""))                         //~vag0R~
//        {                                                        //~vag0R~
//            Utils.showToastLong(R.string.error_message_nofile_inurl);//~vag0R~
//            return;                                              //~vag0R~
//        }                                                        //~vag0R~
        AxeDownload axedl=new AxeDownload();
        swdelzip=Pdelzip;                                          //~vag0I~
    	swunzip=true;                                              //~vag0I~
        if (Purl==null||Purl.equals(""))                           //~vag0I~
		    localFile=axedl.getTargetFile(Plocal);                 //~vag0I~
        else                                                       //~vag0I~
		    localFile=axedl.getTargetFile(Purl,Plocal);            //~vag0R~
        if (localPath==null)                                       //~vag0I~
        {                                                          //~vag0I~
			Utils.showToastLong(R.string.error_message_bad_local); //~vag0I~
        	return;                                                //~vag0I~
        }                                                          //~vag0I~
        File f=new File(localFile);                                //+vag0I~
        if (!f.exists())                                           //+vag0I~
        {                                                          //+vag0I~
            Utils.showToastLong(R.string.Err_FileNotFound,localFile);//+vag0I~
            return;                                                //+vag0I~
        }                                                          //+vag0I~
    	complete();	//download completed                           //~vag0I~
    }                                                              //~vag0I~
//*********************************                                //~vag0I~
    private String getTargetFile(String Purl,String Plocal)        //~vag0R~
    {                                                              //~vag0I~
    	boolean swlocalparm=false;                                 //~vag0I~
        if (Plocal!=null && !Plocal.equals(""))                    //~vag0I~
        {                                                          //~vag0I~
        	File f=new File(Plocal);                               //~vag0I~
            if (!f.exists()||!f.isDirectory())                     //~vag0I~
            	return Plocal;                                     //~vag0I~
            localPath=Plocal;	//directory                        //~vag0R~
            swlocalparm=true;                                      //~vag0I~
        }                                                          //~vag0I~
    	fileName="";                                               //~vag0R~
		int lastSlash = Purl.lastIndexOf('/');                     //~vag0I~
		if(lastSlash >=0)                                          //~vag0I~
		{                                                          //~vag0I~
			fileName = Purl.substring(lastSlash + 1);              //~vag0I~
        }                                                          //~vag0I~
        else                                                       //~vag0I~
			fileName = Purl;	//for unzip only                   //~vag0I~
                                                                   //~vag0I~
		if(fileName.equals(""))                                    //~vag0I~
		{                                                          //~vag0I~
			return null;                                           //~vag0I~
		}                                                          //~vag0I~
        if (!swlocalparm)                                          //~vag0I~
	    	localPath=AxeProp.getDataFileFullpath(null);           //~vag0R~
    	return localPath+"/"+fileName;                             //~vag0I~
    }//getTargetFile                                               //~vag0I~
//*********************************                                //~vag0I~
//*for unzip only and without URL parameter                        //~vag0R~
//*********************************                                //~vag0I~
    private String getTargetFile(String Plocal)                    //~vag0I~
    {                                                              //~vag0I~
        if (Plocal==null || Plocal.equals(""))                     //~vag0I~
        	return null;                                           //~vag0I~
        File f=new File(Plocal);                                   //~vag0I~
        if (!f.exists())                                           //~vag0R~
        {                                                          //~vag0I~
        	Utils.showToastLong(R.string.Err_FileNotFound,Plocal);          //~vag0I~
        	return null;                                           //~vag0I~
        }                                                          //~vag0I~
        if (f.isDirectory())                                       //~vag0I~
        {                                                          //~vag0I~
        	Utils.showToastLong(R.string.Err_NotFile,Plocal);               //~vag0I~
        	return null;                                           //~vag0I~
        }                                                          //~vag0I~
		int lastSlash = Plocal.lastIndexOf('/');                   //~vag0I~
		if(lastSlash >=0)                                          //~vag0I~
        {                                                          //~vag0I~
			localPath=Plocal.substring(0,lastSlash);               //~vag0I~
			fileName=Plocal.substring(lastSlash + 1);              //~vag0I~
			if(fileName.equals(""))                                //~vag0I~
				return null;                                       //~vag0I~
        }                                                          //~vag0I~
        else                                                       //~vag0I~
        {                                                          //~vag0I~
	    	localPath=AxeProp.getDataFileFullpath(null);           //~vag0I~
			fileName=Plocal;                                       //~vag0I~
        }                                                          //~vag0I~
    	return localPath+"/"+fileName;                             //~vag0I~
    }//getTargetFile                                               //~vag0I~
//*********************************                                //~vag0I~
    public static void complete()	//download completed           //~vag0R~
    {                                                              //~vag0I~
    	if (!swunzip)                                              //~vag0I~
        	return;                                                //~vag0I~
        boolean tar=false;                                         //~vag0I~
        boolean gzip=fileName.endsWith(".tar.gz")||fileName.endsWith(".tgz");//~vag0I~
        if (gzip)                                                  //~vag0I~
        	tar=true;                                              //~vag0I~
        boolean bzip=fileName.endsWith(".tar.bz2")||fileName.endsWith(".tbz2");//~vag0R~
        if (bzip)                                                  //~vag0I~
        	tar=true;                                              //~vag0I~
        else                                                       //~vag0I~
        	if (fileName.endsWith(".bz2"))                         //~vag0I~
            	bzip=true;                                         //~vag0I~
        tar=tar||fileName.endsWith(".tar");                //~vag0R~
    	ProgressUnzip unzipper=axedownload.new ProgressUnzip(unziptitleid,fileName,msgidtb,swdelzip);//~vag0R~
        unzipper.setTar(tar,gzip,bzip);                            //~vag0R~
        unzipper.startThread();                                        //~vag0I~
    }                                                              //~vag0I~
//*********************************                                //~vag0I~
    public class ProgressUnzip extends AxeProgress                       //~vag0I~
    {                                                              //~vag0I~
    	boolean delzip,gzip,tar,bzip;                              //~vag0R~
    	public ProgressUnzip(int Ptitleid,String Pfilename,int[] Pmsgidtb,boolean Pdelzip)//~vag0R~
        {                                                          //~vag0I~
        	super(AxeG.activity);
            delzip=Pdelzip;                                        //~vag0I~
    		setMsgid(Ptitleid,Pfilename,Pmsgidtb);                  //~vag0I~
        }                                                          //~vag0I~
    	public void setTar(boolean Ptar,boolean Pgzip,boolean Pbzip)//~vag0R~
        {                                                          //~vag0I~
            tar=Ptar;                                              //~vag0I~
    		gzip=Pgzip;                                            //~vag0I~
    		bzip=Pbzip;                                            //~vag0I~
        }                                                          //~vag0I~
    	@Override                                                  //~vag0I~
        public boolean overrideFunction(AxeProgress.FuncThread Pthread)//~vag0R~
        {                                                          //~vag0I~
        	if (tar)                                               //~vag0I~
        		return AxeProp.untarProgress(Pthread,localPath,localFile,delzip,gzip,bzip);//~vag0R~
            else                                                   //~vag0I~
        	if (bzip)	//not tar.bz2 but .bz2                     //~vag0I~
        		return AxeProp.bunzipProgress(Pthread,localPath,localFile,delzip);//~vag0I~
            else                                                   //~vag0I~
        		return AxeProp.unzipProgress(Pthread,localPath,localFile,1/*chmode*/,delzip);//~vag0R~
        }                                                          //~vag0I~
    }                                                              //~vag0I~
}//class                                                           //~1528R~
