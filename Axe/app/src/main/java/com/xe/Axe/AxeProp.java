//*CID://+vay8R~:                                   update#=  114; //~vay8R~
//**************************************************************** //~vaa8I~
//vay8:141124 (Axe)if /sdcrad as alternative of getExternalStorageDirectory is both is same//~vay8I~
//vaiq:130606 Axe:issue initial setup Alert(Be paitient)           //~vaiqI~
//vag0:120719 (Axe)function to download asset from web             //~vag0I~
//vad3:120424 (Axe)crash when SDCard is not mounted,use datadir to avoid it//~vad3I~
//vabd:120126 (Axe)change home to avoid xewd is also at home(grep may loop in searching xewd which contains grep result work)//~vavdI~
//vabc:120126 (Axe)for quick startup,split asset to another zip file; user will download and unzip into /data/data/com.xe.Axe/files//~vbcEI~
//vaaE:120118 icu installed twice                                  //~vaaEI~
//vaaD:120114 install highlight-2.16                               //~vaaDI~
//vaaf:111219 (Axe)ebcdic support                                  //~vaafI~
//vaa8:111111 (Axe)unzip by subthread                              //~vaa8I~
//**************************************************************** //~vaa8I~
package com.xe.Axe;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;
import org.apache.tools.bzip2.*;                                   //~vag0I~

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.os.Environment;


public class AxeProp
{
    public static final String GAMEFILE="GameFileFolder";

	public static final String DATAFILE_PREFIX="save";
	public static final String SDFILE_PREFIX="rename";
    public static final String helpZipfile="xehelp.zip";
    public static final String toolbinZipfile="toolbin.zip";
    public static final String icuZipfile="icucnvadd.zip";         //~vaafI~
    public static final String HOSTS="xehosts";
    public static final String EBCMAP="xeebc.map";                 //~vaafI~
//  public static final String UCONV37="ibm-37_P100-1995.cnv";     //~vaafR~
//  public static final String UCONV930="ibm-930_P120-1999.cnv";   //~vaafR~
//  public static final String UCONV939="ibm-939_P120-1999.cnv";   //~vaafR~
    public static final String helpFolder="xehelp";
    public static final String binFolder="bin";
    public static final String icuFolder="icu";                    //~vaafI~
    public static final String SYNCFG="xesyna.cfg";                //~vaaDR~
    public static final String highlightZipfile="highlight.zip";   //~vaaDR~
    public static final String highlightFolder="highlight-data";   //~vaaDR~
    public static final String icuInstalledTraceFile="InstalledSW";//~vaafI~
    public static final String shFolder="sh";
    public static final String WKDIR="xewd";
    public static final String PROCMOUNT="/proc/mounts";
    public static final String VFAT="vfat";
    public static String helpUnzipFolder;
    public static String slinknameSDCard="/sdcard/";               //~vay8R~
    public static int helpZipfileSize;
	private static boolean availableAxeSD=true;
	private static String dirAxeSD;
    public static String wkDir;
    public static String binDir;
    private static String unzipfilepopup;
    public static boolean initCompleted;                               //~vaa8I~
    public static int assetMode;                                   //~vbcEI~
    private static float bunzipassumedcompressrate=4.0f;           //~vag0R~
    private static int bunzipctr;                                  //~vag0I~
//********************************************************
//*open Assetfile for Agnugo copy
//********************************************************
	public static InputStream openAssetFile(String Pfilename)
	{
    	InputStream is;
    //***************************
        if (Dump.Y) Dump.println("openAssetFile:"+Pfilename);
        try
        {
            is=AxeG.resource.getAssets().open(Pfilename);
        }
        catch(Exception e)
        {
	        Dump.println(e,"openAssetFile failed:"+Pfilename);     //~vaafR~
            is=null;
        }
		return is;
	}
//*******************************************
    public static FileInputStream openInputData(String Pfname)
    {
	    FileInputStream in=null;
        if (Dump.Y) Dump.println("openInputStream:"+Pfname);
        try
        {
            in=AxeG.context.openFileInput(Pfname);
	    	File f=new File(Pfname);
	    	if (Dump.Y) Dump.println("OpenInputData:file="+Pfname+",fullpath="+f.getAbsolutePath());
	    	if (Dump.Y) Dump.println("OpenInputData:file="+Pfname+",fullpathname="+f.getAbsoluteFile());
	    	if (Dump.Y) Dump.println("Test isiexst by File()="+f.exists());
	    	if (Dump.Y) Dump.println("Test tiimestamp="+Long.toHexString(f.lastModified()));
            f=Environment.getDataDirectory();
	    	if (Dump.Y) Dump.println("DataDir Path="+f.getAbsolutePath());
	    	f=new File(AxeG.context.getFilesDir(),Pfname);	//android.app.contextImple
	    	if (Dump.Y) Dump.println(f.toString()+":isiexst="+f.exists());
	    	if (Dump.Y) Dump.println("tiimestamp="+Long.toHexString(f.lastModified()));
        }
        catch(FileNotFoundException e)
        {
            Dump.println(e,"openInputData failed "+Pfname);
        }
        catch (Exception e)
        {
            Dump.println(e,"openInputData exception "+Pfname);
        }//catch
    	return in;
    }
//*********************************************
//*output to SD if avale else private *********
//*********************************************
    public static FileOutputStream openOutputData(String Pdir,String Pfname)
    {
        String fnm,path;
    	if (Dump.Y) Dump.println("openOutputData dir="+Pdir+",file="+Pfname);
		path=getSDPath(Pdir);
        if (path==null)	//no SDCard available
        	return openOutputData(Pfname);
        fnm=path+System.getProperty("file.separator")+Pfname;
        if (Dump.Y) Dump.println("openoutputData on SDcard fnm="+fnm);
	    FileOutputStream out=null;
		try {
			out = new FileOutputStream(fnm);
		} catch (Exception e)
		{
			Dump.println(e,"openOutputData Exception:"+fnm);
		}
    	return out;
    }
//*********************************************
//*get private data file path
//*********************************************
    public static String getDataFileFullpath(String Pfname)
    {
    	if (Dump.Y) Dump.println("getDataFileFullpath:"+Pfname);
		String path=AxeG.context.getFilesDir().toString();
        if (Pfname!=null)
			path+="/"+Pfname;
    	if (Dump.Y) Dump.println("getDataFileFullpath:"+path);
        return path;
    }
//*********************************************
//*get private data file size
//*********************************************
    public static long getDataFileSize(String Pfname)
    {
    	if (Dump.Y) Dump.println("getDataFileSize:"+Pfname);
		String path=AxeG.context.getFilesDir()+"/"+Pfname;
        File f=new File(path);
        long sz;
        if (f.exists())
        	sz=f.length();
        else
        	sz=-1;
        if (Dump.Y) Dump.println("getDataFileSize:"+path+",sz="+sz);
    	return sz;
    }
//**********
    public static InputStream openInputSD(String Pfname)
    {
        String fnm;
    	if (Dump.Y) Dump.println("open Input SD="+Pfname);
		fnm=getSDPath(Pfname);
        if (fnm==null)	//no SDCard available
        	return null;

        if (Dump.Y) Dump.println("openInputSD fnm="+fnm);
	    FileInputStream is=null;
		try
		{
			is=new FileInputStream(fnm);
		}
		catch (FileNotFoundException e)
		{
			Dump.println(e,"openInputSD Exception:"+fnm);
		}
    	return (InputStream)is;
    }
//***********************************
    public static FileOutputStream openOutputData(String Pfname)
    {
    	if (Dump.Y) Dump.println("open Output private file="+Pfname);
	    FileOutputStream out=null;	//FileOutputStream extend OutputStream
        try
        {
            out=AxeG.context.openFileOutput(Pfname,Context.MODE_PRIVATE);
        }
        catch (Exception e)
        {
            Dump.println(e,"open output exception "+Pfname);
        }//catch
    	return out;
    }
//**********
    public static boolean writeOutputData(String Pfname,byte[] Pbytedata)
    {
    	boolean rc=false;
    	if (Dump.Y) Dump.println("write Output private file="+Pfname);
		FileOutputStream os=openOutputData(Pfname);
        if (os==null)
        	return rc;
        try
        {
            os.write(Pbytedata,0,Pbytedata.length);
            os.close();
            rc=true;
        }
        catch (Exception e)
        {
            Dump.println(e,"write output exception "+Pfname);
        }//catch
        return rc;
    }
//*******************************
//*SD card                      *
//*Manifest setting
//* <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
//*******************************
    public static boolean isSDMounted()
    {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
    public static String getSDPath(String Pfile)
    {
		String path;
    //************
        if (!availableAxeSD)
        	return null;
        path=dirAxeSD;
        if (path==null)
        {
			String approot=AxeG.appName;
            if (!isSDMounted())
            {
    			String tempsdcard=getDataDir("sdcard");                      //~vad3I~
                if (tempsdcard!=null)                              //~vad3I~
                {                                                  //~vad3I~
                	dirAxeSD=tempsdcard;                           //~vad3I~
                    return dirAxeSD;                               //~vad3I~
                }                                                  //~vad3I~
                availableAxeSD=false;
                return null;
            }
//      	path=Environment.getExternalStorageDirectory().getPath()+System.getProperty("file.separator")+approot;//~vay8R~
            path=getSDCardDirectory()+System.getProperty("file.separator")+approot;//~vay8I~
            File f=new File(path);
        	if (!f.exists())
            {
            	if (!f.mkdir())
                {
		        	availableAxeSD=false;
			        if (Dump.Y) Dump.println("getSDpath mkdir failed:"+path);
        			return null;
                }
            }
         	dirAxeSD=path;
            if (Dump.Y) Dump.println("SDcard dir="+path);
		}
        if (!Pfile.equals(""))
        	if (Pfile.startsWith("/"))
        		path+=Pfile;
            else
        		path+="/"+Pfile;
        if (Dump.Y) Dump.println("GetSDpath:"+path);
        return path;
    }
//*******************************
//*WorkDir
//*sdpath is vfat,so set on private data dir
//*******************************
    public static String getWkdir()
    {
    //************
//        path=getSDPath("");
//        if (path==null)
//        path=dirAxeSD;
//        if (Dump.Y) Dump.println("GetWkdir: datafilefullpath="+getDataFileFullpath(null));
//        if (path==null)
//        {
//            path=getDataFileFullpath(WKDIR);
//        }
    	if (wkDir==null)
        {
        	wkDir=getDataFileFullpath(WKDIR);
        	File file=new File(wkDir);
//            try
//            {
        	    if (!file.exists())
            	{
                	file.mkdirs();
            	}
//            }
//            catch (IOException e)
//            {
//                Dump.println(e,"getWkdir:mkDir");
//            }
        }
        if (Dump.Y) Dump.println("GetWkdir:"+wkDir);
        return wkDir;
    }
//*******************************                                  //~vaafI~
//*Datadir                                                         //~vaafI~
//*******************************                                  //~vaafI~
    public static String getDataDir(String Psubdir)                //~vaafI~
    {                                                              //~vaafI~
    //************                                                 //~vaafI~
        String datadir=getDataFileFullpath(Psubdir);               //~vaafI~
        File file=new File(datadir);                               //~vaafI~
        if (!file.exists())                                        //~vaafI~
        {                                                          //~vaafI~
        	file.mkdirs();                                         //~vaafI~
        }                                                          //~vaafI~
        if (Dump.Y) Dump.println("GetDataDir:"+datadir);           //~vaafI~
        return datadir;                                            //~vaafI~
    }                                                              //~vaafI~
//**********************************************************************
//*Preference read/write                                               *
//**********************************************************************
    public static String getPreference(String Pkey)
    {
	    return getPreference(Pkey,"");
    }
    //******************                                           //~vaiqI~
    public static int getPreference(String Pkey,int Pdefault)      //~vaiqI~
    {                                                              //~vaiqI~
    	SharedPreferences pref=getPreferenceName();                //~vaiqI~
        int value=pref.getInt(Pkey,Pdefault);                      //~vaiqI~
        return value;                                              //~vaiqI~
    }                                                              //~vaiqI~
    //******************                                           //~vaiqI~
    public static String getPreference(String Pkey,String Pdefault)
    {
    	SharedPreferences pref=getPreferenceName();
        String value=pref.getString(Pkey,Pdefault/*default value*/);
//      if (Dump.Y) Dump.println("getPreference:"+Pkey+"="+(value==null?"null":value)+".");
        return value;
    }//readwriteQNo
    //******************                                           //~vaiqI~
    public static void putPreference(String Pkey,int Pvalue)       //~vaiqI~
    {                                                              //~vaiqI~
    	SharedPreferences pref=getPreferenceName();                //~vaiqI~
        SharedPreferences.Editor editor=pref.edit();               //~vaiqI~
        editor.putInt(Pkey,Pvalue);                                //~vaiqI~
        editor.commit();                                           //~vaiqI~
    }                                                              //~vaiqI~
    //******************
    public static void putPreference(String Pkey,String Pvalue)
    {
        if (Dump.Y) Dump.println("putPreference:"+Pkey+"="+Pvalue);
    	SharedPreferences pref=getPreferenceName();
        SharedPreferences.Editor editor=pref.edit();
        if (Pvalue==null)
    	    editor.remove(Pkey);
        else
    	    editor.putString(Pkey,Pvalue);
        editor.commit();
    }//putPreference                                               //~vaiqR~
    //******************
    private static SharedPreferences getPreferenceName()
    {
        return AxeG.context.getSharedPreferences(AxeG.appName+"-PrivatePreference",Context.MODE_PRIVATE);
    }
    //***************************************************          //~vaa8R~
    //*init xe parameter files--from AxeView constructor           //~vaa8R~
    //**************************************************           //~vaa8R~
    public static void initFiles()
    {
        assetMode=1;    //use will manually expand help,bin,highlight//~vbcEI~
		Gxeh.privateTop=getDataFileFullpath(null);                 //~vavcI~
		Gxeh.homeDir=getDataFileFullpath(null);	//set befor JNI.init;retrieve from jnij2c
		Gxeh.addPath=Gxeh.homeDir+"/"+shFolder+":"+Gxeh.homeDir+"/"+binFolder;
		Gxeh.homeDir=getDataDir("myhome");	//avoid in home with xewd//~vavdR~
	    initEbcMap();   //synchrounous,executed before mapinit     //~vaafI~
      new Thread(new Runnable()                                    //~vaa8I~
      {                                                            //~vaa8I~
       @Override                                                   //~vaa8I~
       public void run()                                           //~vaa8I~
       {                                                           //~vaa8I~
	    initHelpfile();
	    initHosts();
	    unziptool();
	    initHighlight();                                           //~vaaDR~
        initCompleted=true;                                        //~vaa8I~
       }                                                           //~vaa8I~
      }).start();                                                  //~vaa8I~
    }
    //******************
    //*xehelp expand   *
    //******************
    private static void initHelpfile()
    {
        if (assetMode!=0)	//old version                          //~vbcEI~
        	return;                                                //~vbcEI~
    	String folder=getSDPath("");	//mnt/sdcard/Axe
    	int oldsz=AxeG.getParameter(AxeG.PREFKEY_HELPSZ,0/*default*/);
    	int newsz=(int)getAssetFileSize(helpZipfile);
        if (oldsz!=newsz && newsz>0)
        {
            Utils.showToast(R.string.Info_UnzipHelp);              //~vaa8R~
        	if (unzipAsset(folder,helpFolder,helpZipfile))
		    	AxeG.setParameter(AxeG.PREFKEY_HELPSZ,newsz);
            Utils.showToast(R.string.Info_UnzipHelpComp);          //~vaa8I~
        }
    }
    //******************
    //*tools
    //******************
    private static void unziptool()
    {
        if (assetMode!=0)	//old version                          //~vbcEI~
        	return;                                                //~vbcEI~
    	String folder=Gxeh.homeDir+"/"+binFolder; //data/../files
    	int oldsz=AxeG.getParameter(AxeG.PREFKEY_TOOLBINSZ,0/*default*/);
       File file=new File(folder);
       if (!file.exists())
       {
           oldsz=0;	//reload if bin dir was deleted
       }

    	int newsz=(int)getAssetFileSize(toolbinZipfile);
    	int chmod=0755;
        unzipfilepopup=Utils.getResourceString(R.string.Info_ToolInstalling);
        if (oldsz!=newsz && newsz>0)
        {
        	if (unzipAsset(folder,toolbinZipfile,chmod))
		    	AxeG.setParameter(AxeG.PREFKEY_TOOLBINSZ,newsz);
            Utils.showToast(R.string.Info_UnzipToolComp);          //~vaa8I~
        }
	    unzipfilepopup=null;
    }
    private static void initHighlight()                            //~vaaDI~
    {                                                              //~vaaDI~
    	boolean rc;                                                //~vaaDI~
      if (assetMode==0)	//old version                          //~vbcEI~//~vavdR~
      {                                                            //~vavdI~
	    rc=unzipHighlight();                                       //~vaaDI~
    	String bin=Gxeh.homeDir+"/"+highlightFolder+"/highlight";  //~vaaDI~
    	String binfolder=Gxeh.homeDir+"/"+binFolder;               //~vaaDM~
        if (rc)                                                    //~vaaDI~
        {                                                          //~vaaDI~
        	rc=moveFile(bin,binfolder);                            //~vaaDI~
        	if (!rc)                                               //~vaaDI~
            	Utils.showToast(R.string.Info_HighlightInstallFailed);//~vaaDI~
        }                                                          //~vaaDI~
      }                                                            //~vavdI~
	    copyToWkdir(0/*override*/,SYNCFG);                         //~vaaDR~
    }                                                              //~vaaDI~
    //******************                                           //~vaaDI~
    //*highlite-2.16                                               //~vaaDI~
    //******************                                           //~vaaDI~
    private static boolean unzipHighlight()                        //~vaaDR~
    {                                                              //~vaaDI~
    	boolean rc=false;                                          //~vaaDR~
    	String folder=Gxeh.homeDir; //data/../files                //~vaaDI~
    	int oldsz=AxeG.getParameter(AxeG.PREFKEY_HIGHLIGHTSZ,0/*default*/);//~vaaDI~
    	String unziptop=folder+"/"+highlightFolder;                //~vaaDI~
        File file=new File(unziptop);                              //~vaaDI~
        if (!file.exists())                                        //~vaaDI~
        {                                                          //~vaaDI~
           oldsz=0;	//reload if bin dir was deleted                //~vaaDI~
        }                                                          //~vaaDI~
    	int newsz=(int)getAssetFileSize(highlightZipfile);         //~vaaDI~
    	int chmod=0755;                                            //~vaaDI~
        if (oldsz!=newsz && newsz>0)                               //~vaaDI~
        {                                                          //~vaaDI~
	        Utils.showToast(R.string.Info_HighlightInstalling);    //~vaaDI~
        	if (unzipAsset(unziptop,highlightZipfile,chmod))       //~vaaDR~
		    	AxeG.setParameter(AxeG.PREFKEY_HIGHLIGHTSZ,newsz); //~vaaDI~
            Utils.showToast(R.string.Info_UnzipHilightComp);       //~vaaDI~
            rc=true;                                               //~vaaDI~
        }                                                          //~vaaDI~
        return rc;                                                 //~vaaDI~
    }                                                              //~vaaDI~
    //*********************                                        //~vaaDI~
    private static boolean moveFile(String Psrc,String PtgtFolder) //~vaaDI~
    {                                                              //~vaaDI~
    	boolean rc;                                                //~vaaDI~
        File src=new File(Psrc);                                   //~vaaDI~
        if (!src.exists())                                         //~vaaDI~
        	return false;                                          //~vaaDI~
        String tgtfile=PtgtFolder+"/"+ src.getName();              //~vaaDI~
        File tgt=new File(tgtfile);                                 //~vaaDI~
        if (tgt.exists())                                          //~vaaDI~
        {                                                          //~vaaDI~
        	tgt.delete();                                          //~vaaDI~
        }                                                          //~vaaDI~
        rc=src.renameTo(tgt);                                  //~vaaDI~
        return rc;                                                 //~vaaDI~
    }                                                              //~vaaDI~
    //*********************                                        //~vaafI~
    //*unzip ICU converetr                                         //~vaafI~
    //********************                                         //~vaafI~
    private static boolean unzipicu()                                 //~vaafI~
    {                                                              //~vaafI~
//  	String folder=Gxeh.homeDir+"/"+icuFolder; //data/../files/icu//~vaafI~//~vavdR~
    	String folder=Gxeh.privateTop+"/"+icuFolder; //data/../files/icu//~vavdI~
        File file=new File(folder);                                //~vaafI~
       	if (!file.exists())                                        //~vaafI~
       	{                                                          //~vaafI~
        	file.mkdirs();                                         //~vaafI~
    		chmod(file,0777);                                      //~vaafI~
       	}                                                          //~vaafI~
//      else                                                       //~vaafI~//~vaaER~
//      {                                                          //~vaafI~//~vaaER~
        	Gxeh.icuSwFile=folder+"/"+icuInstalledTraceFile;       //~vaafI~
        	File installedSW=new File(Gxeh.icuSwFile);             //~vaafR~
       		if (installedSW.exists())                              //~vaafR~
            	return false;                                      //~vaafI~
//      }                                                          //~vaafI~//~vaaER~
    	int chmod=0755;                                            //~vaafI~
        unzipfilepopup=Utils.getResourceString(R.string.Info_ICUInstalling);//display member in unzipasset//~vaafR~
        unzipAsset(folder,icuZipfile,chmod);                       //~vaafI~
        Utils.showToast(R.string.Info_UnzipICUComp);               //~vaafI~
	    unzipfilepopup=null;
	    return true;//~vaafI~
    }                                                              //~vaafI~
//********************************************************
//*get assetfile size
//********************************************************
	public static long getAssetFileSize(String Pfilename)
	{
    	AssetFileDescriptor fd;
        long sz=-1;
    //***************************
        fd=openAssetFileFD(Pfilename);
        if (fd!=null)
        try
        {
        	sz=fd.getLength();
			fd.close();
		}
        catch (IOException e)
        {
			Dump.println(e,"xception Asset openFD"+Pfilename);
			e.printStackTrace();
		}
        if (Dump.Y) Dump.println("getAssetFileSize file="+Pfilename+",sz="+sz);
        return sz;
	}
//********************************************************
	public static AssetFileDescriptor openAssetFileFD(String Pfilename)
	{
    	AssetFileDescriptor fd;
    //***************************
        if (Dump.Y) Dump.println("openAssetFileFD:"+Pfilename);
        try
        {
            fd=AxeG.resource.getAssets().openFd(Pfilename);
        }
        catch(Exception e)
        {
	        Dump.println(e,"openAssetFileFD failed:"+Pfilename);   //~vaafR~
            fd=null;
        }
		return fd;
	}
//********************************************************
//*unzip asset file
//********************************************************
    public static boolean unzipAsset(String Ppath,String Pfolder,String Pzipfile)
    {
    	String zipFilename=Pzipfile;
        int entryctr=0,et;                                            //~vaa8I~
        int interval=3000;//msg every 5 sec                        //~vaa8I~
        boolean ret=false;
        if (Dump.Y) Dump.println("Unzip src="+Pzipfile+",folder="+Pfolder);
        File file=new File(Ppath+File.separator+Pfolder);
        Utils.setTimeStamp(0);                                     //~vaa8I~
        try
        {
            if (!file.exists())
            {
                file.mkdirs();
            }
            else
            {
                file.delete();
            }
            ZipInputStream inputStream=new ZipInputStream(AxeG.resource.getAssets().open(zipFilename));

            for (ZipEntry entry=inputStream.getNextEntry(); entry!=null; entry=inputStream.getNextEntry())
            {
				et=Utils.getElapsedTimeMillis(0);                   //~vaa8I~
                if (et>=interval)                                  //~vaa8I~
                {                                                  //~vaa8I~
		            Utils.showToast(R.string.Info_UnzipExecuting,":"+entryctr);//~vaa8I~
		        	Utils.setTimeStamp(0);                         //~vaa8I~
                }                                                  //~vaa8I~
                entryctr++;                                        //~vaa8I~
                String innerFileName = Ppath+File.separator+entry.getName();
                if (Dump.Y) Dump.println("Unzip entry="+innerFileName);
                File innerFile = new File(innerFileName);
                if (innerFile.exists())
                {
                    innerFile.delete();
                }
                if (entry.isDirectory())
                {
                    boolean rc=innerFile.mkdirs();
                    if (Dump.Y) Dump.println("mkdir "+entry.getName()+"="+rc);
                }
                else
                {
                    FileOutputStream outputStream = new FileOutputStream(innerFileName);
                    final int BUFFER = 2048;
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream,BUFFER);
                    int count = 0;
                    byte[] data = new byte[BUFFER];
                    while ((count = inputStream.read(data, 0, BUFFER)) != -1)
                    {
                        bufferedOutputStream.write(data, 0, count);
                    }
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                }
                inputStream.closeEntry();
            }
            inputStream.close();
            ret=true;
        }
        catch (IOException e)
        {
            Dump.println(e,"unzipAsset");
        }
        if (Dump.Y) Dump.println("unzipAsset rc="+ret);
        Utils.showToast(R.string.Info_UnzipExecuted,":"+entryctr); //~vaa8I~
        return ret;
    }
//********************************************************
//*unzip asset file:replace mode(no path in zip file)
//********************************************************
    public static boolean unzipAsset(String Ppath,String Pzipfile,int Pchmod)
    {
    	String zipFilename=Pzipfile;
        boolean ret=false;
        if (Dump.Y) Dump.println("Unzip src="+Pzipfile+",folder="+Ppath);
        File file=new File(Ppath);
        try
        {
            if (!file.exists())
            {
                file.mkdirs();
            }
            ZipInputStream inputStream=new ZipInputStream(AxeG.resource.getAssets().open(zipFilename));//~vag0R~

            for (ZipEntry entry=inputStream.getNextEntry(); entry!=null; entry=inputStream.getNextEntry())
            {
                String innerFileName = Ppath+File.separator+entry.getName();
                if (Dump.Y) Dump.println("Unzip entry="+innerFileName);
                File innerFile = new File(innerFileName);
                if (innerFile.exists())
                {
                    innerFile.delete();
                }
                if (entry.isDirectory())
                {
                    boolean rc=innerFile.mkdirs();
                    if (Dump.Y) Dump.println("mkdir "+entry.getName()+"="+rc);
                }
                else
                {
			        if (unzipfilepopup!=null)
                    	Utils.showToast(unzipfilepopup+entry.getName());
                    FileOutputStream outputStream = new FileOutputStream(innerFileName);
                    final int BUFFER = 2048;
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream,BUFFER);
                    int count = 0;
                    byte[] data = new byte[BUFFER];
                    while ((count = inputStream.read(data, 0, BUFFER)) != -1)
                    {
                        bufferedOutputStream.write(data, 0, count);
                    }
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    if (Pchmod!=-1)
    					chmod(innerFile,0755);
                }
                inputStream.closeEntry();
            }
            inputStream.close();
            ret=true;
        }
        catch (IOException e)
        {
            Dump.println(e,"unzipAsset");
        }
        if (Dump.Y) Dump.println("unzipAsset rc="+ret);
        return ret;
    }
	private static int getMsgFreq(long Ptotalsize,int Popunit)          //~vag0I~
    {                                                              //~vag0I~
    	int imaxfreq=1;                                            //~vag0I~
    	float repeatfreq=50.0f;	//progress bar update max 50 times //~vag0I~
    	float maxfreq=(float)Ptotalsize/(float)Popunit;  //repeat cont//~vag0I~
        if (maxfreq>(float)repeatfreq)                             //~vag0I~
        	imaxfreq=(int)(maxfreq/(maxfreq/repeatfreq));          //~vag0I~
        return imaxfreq;                                           //~vag0I~
    }                                                              //~vag0I~
//****************************************                         //~vag0I~
//*unzip on subthread displaying progress bar                      //~vag0R~
//****************************************                         //~vag0I~
    public static boolean unzipProgress(AxeProgress.FuncThread Pthread,String Ppath,String Pzipfile,int Pchmod,boolean Pdelzip)//~vag0R~
    {                                                              //~vag0I~
        final int BUFFER = 8192;                       //~vag0I~
		long zipfsz;                                               //~vag0I~
	//*******************                                          //~vag0I~
    	String zipFilename=Pzipfile;                               //~vag0I~
        boolean ret=false;                                         //~vag0I~
        if (Dump.Y) Dump.println("Unzip src="+Pzipfile+",folder="+Ppath);//~vag0I~
        File file=new File(Ppath);                                 //~vag0I~
        try                                                        //~vag0I~
        {                                                          //~vag0I~
            File zipfile=new File(zipFilename);                    //~vag0I~
            long totalread=0;                                      //~vag0R~
//          long zipfsz=zipfile.length();                          //~vag0R~
//          Pthread.funcStarted(zipFilename,zipfsz);  //zipfile size//~vag0R~
            zipfsz=getUnzipSize(zipFilename);                      //~vag0I~
            if (zipfsz==0)                                         //~vag0I~
            	return false;                                      //~vag0I~
			int maxfreq=getMsgFreq(zipfsz,BUFFER);                 //~vag0I~
            int msgfreq=0;                                         //~vag0I~
            Pthread.funcStarted(zipFilename,zipfsz); //expand size //~vag0R~
            if (!file.exists())                                    //~vag0I~
            {                                                      //~vag0I~
                file.mkdirs();                                     //~vag0I~
            }                                                      //~vag0I~
			BufferedInputStream bis=getBIS(zipFilename);            //~vag0I~
            ZipInputStream inputStream=new ZipInputStream(bis);    //~vag0I~
                                                                   //~vag0I~
            for (ZipEntry entry=inputStream.getNextEntry(); entry!=null; entry=inputStream.getNextEntry())//~vag0I~
            {                                                      //~vag0I~
                String innerFileName = Ppath+File.separator+entry.getName();//~vag0I~
                if (Dump.Y) Dump.println("Unzip entry="+innerFileName);//~vag0I~
                File innerFile = new File(innerFileName);          //~vag0I~
                if (innerFile.exists())                            //~vag0I~
                {                                                  //~vag0I~
                    innerFile.delete();                            //~vag0I~
                }                                                  //~vag0I~
				long mtime=entry.getTime();                    //~vag0I~
//Dump.println("Unzip entry="+innerFileName+",ts="+mtime);         //~vag0R~
                if (entry.isDirectory())                           //~vag0I~
                {                                                  //~vag0I~
                    boolean rc=innerFile.mkdirs();                 //~vag0I~
                    if (Dump.Y) Dump.println("mkdir "+entry.getName()+"="+rc);//~vag0I~
			        innerFile.setLastModified(mtime);              //~vag0I~
                }                                                  //~vag0I~
                else                                               //~vag0I~
                {                                                  //~vag0I~
//  		        if (unzipfilepopup!=null)                      //~vag0R~
//                  	Utils.showToast(unzipfilepopup+entry.getName());//~vag0R~
                    BufferedOutputStream bufferedOutputStream=getBOS(innerFileName,0);//~vag0I~
                    int count = 0;                                 //~vag0I~
                    byte[] data = new byte[BUFFER];                //~vag0I~
                    while ((count = inputStream.read(data, 0, BUFFER)) != -1)//~vag0I~
                    {                                              //~vag0I~
                    	if (Pthread.isInterrupted())               //~vag0I~
                        	break;                                 //~vag0I~
                        bufferedOutputStream.write(data, 0, count);//~vag0I~
                    	totalread+=count;                          //~vag0I~
//                      Dump.println("unzip totalread="+totalread+",fsz="+zipfsz);//~vag0R~
                        if (msgfreq==0)                              //~vag0I~
            				Pthread.funcUpdate(totalread);       //~vag0I~
                        msgfreq++;                                 //~vag0I~
                        msgfreq%=maxfreq;	//each 100K            //~vag0R~
                    }                                              //~vag0I~
                    bufferedOutputStream.flush();                  //~vag0I~
                    bufferedOutputStream.close();                  //~vag0I~
                    if (Pchmod!=-1)                                //~vag0I~
    					chmod(innerFile,0755);                     //~vag0I~
			        innerFile.setLastModified(mtime);              //~vag0I~
                }                                                  //~vag0I~
                if (Pthread.isInterrupted())                       //~vag0I~
                    break;                                         //~vag0I~
                inputStream.closeEntry();                          //~vag0I~
            }                                                      //~vag0I~
            inputStream.close();                                   //~vag0I~
          	if (!Pthread.isInterrupted())                          //~vag0I~
            {                                                      //~vag0I~
            	setUnzipDirTimestamp(Ppath,Pzipfile);              //~vag0I~
            	if (Pdelzip)                                       //~vag0I~
					zipfile.delete();                              //~vag0I~
           		Pthread.funcCompleted();                           //~vag0R~
            	ret=true;                                          //~vag0R~
            }                                                      //~vag0I~
        }                                                          //~vag0I~
        catch (Exception e)                                        //~vag0R~
        {                                                          //~vag0I~
            Dump.println(e,"unzipProgress");                       //~vag0R~
            Pthread.funcError();//~vag0I~
        }                                                          //~vag0I~
        if (Dump.Y) Dump.println("unzipAsset rc="+ret);            //~vag0I~
        return ret;                                                //~vag0I~
    }                                                              //~vag0I~
//****************************************                         //~vag0I~
//*get unzip size                                                  //~vag0I~
//****************************************                         //~vag0I~
    public static long getUnzipSize(String Pzipfile)               //~vag0I~
    {                                                              //~vag0I~
    	long unzipsz=0;                                            //~vag0I~
        try                                                        //~vag0I~
        {                                                          //~vag0I~
//            ZipInputStream inputStream=new ZipInputStream(new FileInputStream(Pzipfile));//~vag0I~
//            for (ZipEntry entry=inputStream.getNextEntry(); entry!=null; entry=inputStream.getNextEntry())//~vag0I~
//            {                                                    //~vag0I~
//                unzipsz+=entry.getSize();                        //~vag0I~
//            }                                                    //~vag0I~
            ZipFile zipf=new ZipFile(Pzipfile);                    //~vag0I~
            Enumeration enumzip=zipf.entries();                    //~vag0I~
            while(enumzip.hasMoreElements())                       //~vag0I~
            {                                                      //~vag0I~
            	ZipEntry entry=(ZipEntry)enumzip.nextElement();    //~vag0I~
                unzipsz+=entry.getSize();                          //~vag0I~
            }                                                      //~vag0I~
        }                                                          //~vag0I~
        catch (ZipException e)                                     //~vag0I~
        {                                                          //~vag0I~
            Dump.println(e,"getUnzipSize not Zipfiel");            //~vag0I~
			Utils.showToastLong(R.string.Err_NotZipFile);          //~vag0I~
        }                                                          //~vag0I~
        catch (Exception e)                                        //~vag0I~
        {                                                          //~vag0I~
            Dump.println(e,"getUnzipSize"+Pzipfile);               //~vag0R~
        }                                                          //~vag0I~
        return unzipsz;                                            //~vag0I~
    }                                                              //~vag0I~
//****************************************                         //~vag0I~
//*set timestamp of dir                                            //~vag0I~
//****************************************                         //~vag0I~
    public static boolean setUnzipDirTimestamp(String Ppath,String Pzipfile)//~vag0I~
    {                                                              //~vag0I~
    	boolean ret=false;                                         //~vag0I~
        try                                                        //~vag0I~
        {                                                          //~vag0I~
            ZipFile zipf=new ZipFile(Pzipfile);                    //~vag0I~
            Enumeration enumzip=zipf.entries();                    //~vag0I~
            while(enumzip.hasMoreElements())                       //~vag0I~
            {                                                      //~vag0I~
            	ZipEntry entry=(ZipEntry)enumzip.nextElement();    //~vag0I~
                String innerFileName = Ppath+File.separator+entry.getName();//~vag0I~
                File innerFile = new File(innerFileName);          //~vag0I~
                if (entry.isDirectory())                           //~vag0I~
                {                                                  //~vag0I~
					long mtime=entry.getTime();                    //~vag0I~
			        innerFile.setLastModified(mtime);              //~vag0I~
                }                                                  //~vag0I~
            }                                                      //~vag0I~
            ret=true;                                              //~vag0I~
        }                                                          //~vag0I~
        catch (Exception e)                                        //~vag0I~
        {                                                          //~vag0I~
            Dump.println(e,"getUnzipSize");                        //~vag0I~
        }                                                          //~vag0I~
        return ret;                                                //~vag0I~
    }                                                              //~vag0I~
//****************************************                         //~vag0I~
//*utar  on subthread displaying progress bar                      //~vag0I~
//****************************************                         //~vag0I~
    public static boolean untarProgress(AxeProgress.FuncThread Pthread,String Ppath,String Pzipfile,boolean Pdelzip,boolean Pgzip,boolean Pbzip)//~vag0R~
    {                                                              //~vag0I~
        final int BUFFER = 8192;                                   //~vag0I~
        TarInputStream inputStream;                                //~vag0I~
		long zipfsz;                                               //~vag0I~
		String lastmdpath=null;                                    //~vag0I~
        File zipfile;                                              //~vag0I~
    //***********************                                      //~vag0I~
    	String zipFilename=Pzipfile;                               //~vag0I~
        boolean ret=false;                                         //~vag0I~
        if (Dump.Y) Dump.println("Untar src="+Pzipfile+",folder="+Ppath);//~vag0I~
        File file=new File(Ppath);                                 //~vag0I~
        try                                                        //~vag0I~
        {                                                          //~vag0I~
            zipfile=new File(zipFilename);                         //~vag0I~
        	if (Pbzip)                                             //~vag0I~
            {                                                      //~vag0I~
            	zipFilename=bunzip2(Pthread,zipFilename,Pdelzip);                  //~vag0I~
                if (zipFilename==null)                             //~vag0I~
                	return false;                                  //~vag0I~
	            zipfile=new File(zipFilename);                     //~vag0I~
            }                                                      //~vag0I~
            long totalread=0;                                      //~vag0I~
            zipfsz=getUntarSize(zipFilename,Pgzip);                //~vag0R~
            if (zipfsz==0)                                         //~vag0I~
            	return false;                                      //~vag0I~
			int maxfreq=getMsgFreq(zipfsz,BUFFER);                 //~vag0I~
            int msgfreq=0;                                         //~vag0I~
            Pthread.funcStarted(zipFilename,zipfsz); //expand size //~vag0I~
            if (!file.exists())                                    //~vag0I~
            {                                                      //~vag0I~
                file.mkdirs();                                     //~vag0I~
            }                                                      //~vag0I~
			BufferedInputStream bis=getBIS(zipFilename);            //~vag0I~
        	if (Pgzip)                                             //~vag0I~
				inputStream=new TarInputStream(new GZIPInputStream(bis));//~vag0R~
            else                                                   //~vag0I~
            	inputStream=new TarInputStream(bis);               //~vag0R~
//            String oldtopdir="";                                   //~vag0R~
            for (TarEntry entry=inputStream.getNextEntry(); entry!=null; entry=inputStream.getNextEntry())//~vag0I~
            {                                                      //~vag0I~
                String entryname=entry.getName();                  //~vag0R~
//Dump.println("createfile Unzip entry="+entryname);               //~vag0R~
//                int firstSlash=entryname.indexOf('/');           //~vag0R~
//                if (firstSlash>0)                                //~vag0R~
//                {                                                //~vag0R~
//                    String topdir=entryname.substring(0,firstSlash);//~vag0R~
//                    if (!topdir.equals(oldtopdir))               //~vag0R~
//                    {                                            //~vag0R~
//                        oldtopdir=topdir;                        //~vag0R~
//                        topdir=Ppath+"/"+topdir;                 //~vag0R~
//                        File topdirFile=new File(topdir);        //~vag0R~
//                        if (!topdirFile.exists())                //~vag0R~
//                        {                                        //~vag0R~
//                            topdirFile.mkdirs();                 //~vag0R~
//                        }                                        //~vag0R~
//                    }                                            //~vag0R~
//                }                                                //~vag0R~
				String opdir;                                      //~vag0I~
                if (entryname.charAt(1)==':')                      //~vag0I~
                  if (entryname.charAt(2)=='/')                    //~vag0I~
                      opdir=entryname.substring(0,1)+entryname.substring(2);//~vag0I~
                  else                                             //~vag0I~
                      opdir=entryname.substring(0,1)+"/"+entryname.substring(2);//~vag0I~
                else                                               //~vag0I~
                  opdir=entryname;                                 //~vag0I~
                String innerFileName = Ppath+File.separator+opdir; //~vag0R~
                lastmdpath=makeTargetPath(innerFileName,lastmdpath);//~vag0I~
                if (Dump.Y) Dump.println("Unzip entry="+innerFileName);//~vag0I~
                File innerFile = new File(innerFileName);          //~vag0I~
                if (entry.isDirectory())                           //~vag0I~
                {                                                  //~vag0I~
                	if (!innerFile.exists())                        //~vag0I~
                    {                                              //~vag0I~
                    	boolean rc=innerFile.mkdirs();             //~vag0R~
                    	if (Dump.Y) Dump.println("mkdir "+entry.getName()+"="+rc);//~vag0R~
                    }                                              //~vag0I~
                }                                                  //~vag0I~
                else                                               //~vag0I~
                {                                                  //~vag0I~
                    BufferedOutputStream bos=getBOS(innerFileName,BUFFER);//~vag0I~
              		inputStream.copyEntryContents(bos);            //~vag0I~
                    bos.flush();                                   //~vag0R~
                    bos.close();                                   //~vag0R~
					Date dmtime=entry.getModTime();                //~vag0I~
        		    long mtime=dmtime.getTime();	//milisec from 1970//~vag0I~
		            innerFile.setLastModified(mtime);              //~vag0I~
            		long entsz=entry.getSize();                    //~vag0I~
                    totalread+=entsz;                              //~vag0I~
                    if (msgfreq==0)                                //~vag0I~
            			Pthread.funcUpdate(totalread);             //~vag0I~
                    msgfreq++;                                     //~vag0I~
                    msgfreq%=maxfreq;	//each 100K                //~vag0I~
                }                                                  //~vag0I~
//  			int mode=entry.getMode();                          //~vag0R~
//              setUntarMode(innerFile,mode);                      //~vag0R~
                 if (Pthread.isInterrupted())                       //~vag0I~
                    break;                                         //~vag0I~
            }                                                      //~vag0I~
            inputStream.close();                                   //~vag0I~
          	if (!Pthread.isInterrupted())                          //~vag0I~
            {                                                      //~vag0I~
            	setUntarDirTimestamp(Ppath,zipFilename,Pgzip);     //~vag0R~
//@@@@      	if (Pdelzip||Pbzip) //del temp tar from bz2        //~vag0R~
//  				zipfile.delete();                              //~vag0R~
           		Pthread.funcCompleted();                           //~vag0I~
            	ret=true;                                          //~vag0I~
            }                                                      //~vag0I~
        }                                                          //~vag0I~
        catch (Exception e)                                        //~vag0R~
        {                                                          //~vag0I~
            Dump.println(e,"untarProgress");                       //~vag0R~
            String msg=e.getMessage();                             //~vag0I~
            Pthread.funcError(msg);                                //~vag0R~
        }                                                          //~vag0I~
        if (Dump.Y) Dump.println("unzipAsset rc="+ret);            //~vag0I~
        return ret;                                                //~vag0I~
    }                                                              //~vag0I~
//****************************************                         //~vag0I~
//*utar  on subthread displaying progress bar                      //~vag0I~
//****************************************                         //~vag0I~
    public static boolean bunzipProgress(AxeProgress.FuncThread Pthread,String Ppath,String Pzipfile,boolean Pdelzip)//~vag0I~
    {                                                              //~vag0I~
        File zipfile;                                              //~vag0I~
        boolean ret=true;                                          //~vag0I~
    //***********************                                      //~vag0I~
    	String zipFilename=Pzipfile;                               //~vag0I~
        if (Dump.Y) Dump.println("Untar src="+Pzipfile+",folder="+Ppath);//~vag0I~
        zipfile=new File(zipFilename);                             //~vag0I~
        if (!zipfile.exists())                                     //~vag0I~
        {                                                          //~vag0I~
            Utils.showToastLong(R.string.Err_FileNotFound,zipFilename);//~vag0I~
            return false;                                          //~vag0I~
        }                                                          //~vag0I~
        zipFilename=bunzip2(Pthread,zipFilename,Pdelzip);          //~vag0I~
        if (zipFilename==null)                                     //~vag0I~
            return false;                                          //~vag0I~
        Pthread.funcCompleted();                                   //~vag0I~
        return ret;                                                //~vag0I~
    }                                                              //~vag0I~
//****************************************                         //~vag0I~
//*once bunzip to tarfile for performance reason                   //~vag0I~
//****************************************                         //~vag0I~
	private static String bunzip2(AxeProgress.FuncThread Pthread,String Pbz2file,boolean Pdelzip)//~vag0R~
    {                                                              //~vag0I~
        final int BUFFER = 8192;                                   //~vag0I~
        long totalread=0;                                           //~vag0I~
        byte[] buffer=new byte[BUFFER];                            //~vag0I~
    	String tarfile=null;                                       //~vag0I~
    	File ifile=new File(Pbz2file);                             //~vag0I~
        long bzfsz=ifile.length();                                  //~vag0I~
        if (!ifile.exists())                                       //~vag0I~
        {                                                          //~vag0I~
        	Utils.showToastLong(R.string.Err_FileNotFound,Pbz2file);//~vag0I~
        	return null;                                           //~vag0I~
        }                                                          //~vag0I~
        if (bzfsz<=0)                                              //~vag0I~
        	return null;                                           //~vag0I~
        try                                                        //~vag0I~
        {                                                          //~vag0I~
        	if (Pbz2file.endsWith(".bz2"))                        //~vag0I~
	        	tarfile=Pbz2file.substring(0,Pbz2file.length()-4); //~vag0I~
            else                                                   //~vag0I~
	        	tarfile=Pbz2file+".tar";                           //~vag0I~
            Pthread.funcStarted(Pbz2file+"(decompressing)",bzfsz); //bz2file//~vag0R~
			BufferedInputStream bis=getBIS(Pbz2file);               //~vag0I~
            bis.read();                                            //~vag0R~
            bis.read();    //2 byte read required                  //~vag0R~
            CBZip2InputStream b2is=new CBZip2InputStream(bis);      //~vag0R~
            BufferedOutputStream bos=getBOS(tarfile,BUFFER);       //~vag0I~
            int maxmsgfreq=(int)(bzfsz*bunzipassumedcompressrate/BUFFER/50);	// //~vag0I~
            if (maxmsgfreq<1)                                      //~vag0I~
            	maxmsgfreq=1;                                      //~vag0I~
            int msgfreq=0;                                             //~vag0I~
            for(;;)                                                  //~vag0I~
            {                                                      //~vag0I~
            	int readlen=b2is.read(buffer);                      //~vag0I~
//Dump.println("readlen="+readlen+",total="+totalread);            //~vag0R~
                if (readlen<0)                                     //~vag0R~
                	break;                                         //~vag0I~
                if (readlen==0)                                    //~vag0I~
                {                                                  //~vag0I~
                	continue;                                      //~vag0I~
                }                                                  //~vag0I~
                if (Pthread.isInterrupted())                       //~vag0I~
                {                                                  //~vag0I~
            		tarfile=null;                                  //~vag0I~
                    break;                                         //~vag0I~
                }                                                  //~vag0I~
                bos.write(buffer,0,readlen);                       //~vag0R~
                totalread+=readlen;                                //~vag0I~
                long assumed3=Math.min((long)(totalread/bunzipassumedcompressrate),bzfsz);//~vag0R~
//              Dump.println("totalread="+totalread+",fsz="+bzfsz);//~vag0R~
                msgfreq++;                                         //~vag0I~
                if (msgfreq>=maxmsgfreq)                           //~vag0R~
                {                                                  //~vag0I~
		            Pthread.funcUpdate(assumed3);                  //~vag0R~
                	msgfreq=0;                                     //~vag0R~
                }                                                  //~vag0I~
            }                                                      //~vag0I~
            bos.flush();                                           //~vag0I~
            bos.close();                                           //~vag0R~
            b2is.close();
            bis.close();//~vag0I~
//Dump.println("bunzip2 total read="+totalread);                   //~vag0R~
            float compressrate=(float)totalread/bzfsz;             //~vag0I~
            if (compressrate>1.0f)                                 //~vag0I~
            {                                                      //~vag0I~
            	bunzipassumedcompressrate=(bunzipassumedcompressrate*bunzipctr+compressrate)/(bunzipctr+1);//~vag0R~
                bunzipctr++;                                       //~vag0I~
            }                                                      //~vag0I~
            if (Pdelzip)                                           //~vag0I~
				ifile.delete();                                    //~vag0I~
        }                                                          //~vag0I~
        catch (Exception e)                                        //~vag0R~
        {                                                          //~vag0I~
            Dump.println(e,"bunzip2");                             //~vag0I~
            String msg=e.getMessage();                             //~vag0I~
            Pthread.funcError(msg);                                //~vag0R~
            tarfile=null;                                          //~vag0I~
        }                                                          //~vag0I~
        return tarfile;                                            //~vag0I~
    }                                                              //~vag0I~
//****************************************                         //~vag0I~
//*make target path                                                //~vag0I~
//****************************************                         //~vag0I~
    private static String makeTargetPath(String Ppath,String Plastmd)//~vag0I~
    {                                                              //~vag0I~
        if (Plastmd!=null && Ppath.startsWith(Plastmd))            //~vag0I~
            return Plastmd;                                        //~vag0I~
    	int idxo=0,idx;                                            //~vag0I~
        String lastpath=null;                                      //~vag0I~
        String path0=Ppath;                                        //~vag0I~
        if (Ppath.endsWith("/"))                                   //~vag0I~
            path0=Ppath.substring(0,Ppath.length()-1);             //~vag0I~
    	while(true)                                                //~vag0I~
        {                                                          //~vag0I~
    		idx=path0.indexOf('/',idxo);                           //~vag0I~
            if (idx<0)                                             //~vag0I~
            	break;	//mkdir only path                          //~vag0I~
            idxo=idx+1;                                            //~vag0I~
            if (idx==0)                                            //~vag0I~
                continue;                                          //~vag0I~
        	String path=path0.substring(0,idx);                    //~vag0I~
            File of=new File(path);                                //~vag0I~
            if (of.exists())                                       //~vag0I~
            	continue;                                          //~vag0I~
            of.mkdir();                                            //~vag0I~
            lastpath=path;                                         //~vag0I~
            System.out.println("mkpath:"+path);                    //~vag0I~
        }                                                          //~vag0I~
        return lastpath+"/";                                       //~vag0I~
    }                                                              //~vag0I~
//****************************************                         //~vag0I~
//*recover directory timestamp                                     //~vag0I~
//****************************************                         //~vag0I~
    public static boolean setUntarDirTimestamp(String Ppath,String Pzipfile,boolean Pgzip)//~vag0R~
    {                                                              //~vag0I~
        TarInputStream inputStream;                                //~vag0I~
    //***********************                                      //~vag0I~
    	String zipFilename=Pzipfile;                               //~vag0I~
        boolean ret=false;                                         //~vag0I~
        try                                                        //~vag0I~
        {                                                          //~vag0I~
			BufferedInputStream bis=getBIS(zipFilename);            //~vag0I~
        	if (Pgzip)                                             //~vag0I~
				inputStream=new TarInputStream(new GZIPInputStream(bis));//~vag0R~
        	else                                                   //~vag0I~
            	inputStream=new TarInputStream(bis);               //~vag0R~
                                                                   //~vag0I~
            for (TarEntry entry=inputStream.getNextEntry(); entry!=null; entry=inputStream.getNextEntry())//~vag0I~
            {                                                      //~vag0I~
                String entryname=entry.getName();                  //~vag0I~
//Dump.println("Untar dirtimestamp entry="+entryname);             //~vag0R~
				String opdir;                                      //~vag0I~
                if (entryname.charAt(1)==':')                      //~vag0I~
                  if (entryname.charAt(2)=='/')                    //~vag0I~
                      opdir=entryname.substring(0,1)+entryname.substring(2);//~vag0I~
                  else                                             //~vag0I~
                      opdir=entryname.substring(0,1)+"/"+entryname.substring(2);//~vag0I~
                else                                               //~vag0I~
                  opdir=entryname;                                 //~vag0I~
                String innerFileName = Ppath+File.separator+opdir; //~vag0R~
                if (Dump.Y) Dump.println("Untar entry="+innerFileName);//~vag0R~
                if (entry.isDirectory())                           //~vag0I~
                {                                                  //~vag0I~
	                File innerFile = new File(innerFileName);      //~vag0I~
					Date dmtime=entry.getModTime();                //~vag0I~
//Dump.println("tar dir ts="+dmtime.toString());                   //~vag0R~
        		    long mtime=dmtime.getTime();	//milisec from 1970//~vag0I~
		            innerFile.setLastModified(mtime);              //~vag0I~
                }                                                  //~vag0I~
            }                                                      //~vag0I~
            bis.close();                                           //~vag0R~
            inputStream.close();                                   //~vag0I~
            ret=true;                                              //~vag0I~
        }                                                          //~vag0I~
        catch (IOException e)                                      //~vag0I~
        {                                                          //~vag0I~
            Dump.println(e,"setDirTimestamp");                     //~vag0I~
        }                                                          //~vag0I~
        return ret;                                                //~vag0I~
    }                                                              //~vag0I~
//****************************************                         //~vag0I~
//*recover mode(owener only)                                       //~vag0I~
//****************************************                         //~vag0I~
    public static boolean setUntarMode(File Pfile,int Pmode)      //~vag0I~
    {                                                              //~vag0I~
        boolean owner,rc;                                          //~vag0R~
        boolean ret=true;                                          //~vag0R~
    //***********************                                      //~vag0I~
        try                                                        //~vag0I~
        {                                                          //~vag0I~
        	owner=(Pmode & 0400)!=0;                               //~vag0I~
            rc=Pfile.setReadable(owner);                           //~vag0R~
            if (!rc)                                               //~vag0I~
            	ret=false;                                         //~vag0I~
            owner=(Pmode & 0200)!=0;                               //~vag0I~
            rc=Pfile.setWritable(owner);                           //~vag0R~
            if (!rc)                                               //~vag0I~
            	ret=false;                                         //~vag0I~
            owner=(Pmode & 0100)!=0;                               //~vag0I~
            rc=Pfile.setExecutable(owner);                         //~vag0R~
            if (!rc)                                               //~vag0I~
            	ret=false;                                         //~vag0I~
        }                                                          //~vag0I~
        catch (Exception e)                                      //~vag0I~
        {                                                          //~vag0I~
        	ret=false;                                             //~vag0I~
            Dump.println(e,"setUntarMode");                        //~vag0I~
        }                                                          //~vag0I~
        return ret;                                                //~vag0I~
    }                                                              //~vag0I~
//****************************************                         //~vag0I~
//*get unzip size                                                  //~vag0I~
//****************************************                         //~vag0I~
    public static long getUntarSize(String Ptarfile,boolean Pgzip) //~vag0R~
    {                                                              //~vag0I~
    	long unzipsz=0;                                            //~vag0I~
		TarInputStream tin;                                        //~vag0I~
    //**************************                                   //~vag0I~
        try                                                        //~vag0I~
        {                                                          //~vag0I~
        	if (!Pgzip)	//use tar file size(little bit larger than total file size) for perfomance reason//~vag0I~
            {                                                      //~vag0I~
            	File f=new File(Ptarfile);                         //~vag0I~
                unzipsz=f.length();                                //~vag0I~
                return unzipsz;                                    //~vag0I~
            }                                                      //~vag0I~
			BufferedInputStream bis=getBIS(Ptarfile);               //~vag0I~
            if (Pgzip)                                             //~vag0I~
                tin = new TarInputStream(new GZIPInputStream(bis));//~vag0I~
            else                                                   //~vag0I~
                tin = new TarInputStream(bis);                     //~vag0I~
            for (TarEntry entry=tin.getNextEntry(); entry!=null; entry=tin.getNextEntry())//~vag0I~
            {                                                      //~vag0I~
                long sz=entry.getSize();                           //~vag0I~
                unzipsz+=sz;                                       //~vag0I~
//Dump.println("getUntarSize:"+entry.getName()+",untarsz ="+sz+",total="+unzipsz);//~vag0R~
            }                                                      //~vag0I~
			tin.close();                                           //~vag0I~
        }                                                          //~vag0I~
        catch (Exception e)                                        //~vag0I~
        {                                                          //~vag0I~
            Dump.println(e,"getUntarSize:"+Ptarfile);              //~vag0I~
            String msg=e.getMessage();                //~vag0I~
			Utils.showToastLong(msg);                              //~vag0I~
        }                                                          //~vag0I~
//      System.out.println("untarsz="+unzipsz);                    //~vag0I~
        return unzipsz;                                            //~vag0I~
    }                                                              //~vag0I~
//****************************************                         //~vag0I~
    private static BufferedInputStream getBIS(String Pfilename) throws FileNotFoundException//~vag0R~
    {                                                              //~vag0I~
		FileInputStream fis=new FileInputStream(Pfilename);        //~vag0I~
		BufferedInputStream bis=new BufferedInputStream(fis);      //~vag0I~
        return bis;                                                //~vag0I~
    }                                                              //~vag0I~
//****************************************                         //~vag0I~
    private static BufferedOutputStream getBOS(String Pfilename,int Pbuffsz) throws FileNotFoundException//~vag0I~
    {                                                              //~vag0I~
        BufferedOutputStream bos;                                  //~vag0I~
        FileOutputStream fos=new FileOutputStream(Pfilename);  //~vag0I~
        if (Pbuffsz!=0)                                            //~vag0I~
        	bos=new BufferedOutputStream(fos,Pbuffsz);             //~vag0I~
        else                                                       //~vag0I~
        	bos=new BufferedOutputStream(fos);                     //~vag0I~
        return bos;                                                //~vag0I~
    }                                                              //~vag0I~
//****************************************                         //~vag0I~
    public static void chmod(File Pf,int Pattr)
    {
    	if ((Pattr & 0011)!=0)
			Pf.setExecutable(true/*owner*/,false/*owner only*/);
        else
        if ((Pattr & 0100)!=0)
		    Pf.setExecutable(true/*owner*/,true/*other*/);
    	if ((Pattr & 0044)!=0)
			Pf.setReadable(true/*owner*/,false/*owner only*/);
        else
        if ((Pattr & 0400)!=0)
		    Pf.setReadable(true/*owner*/,true/*other*/);
    	if ((Pattr & 0022)!=0)
			Pf.setWritable(true/*owner*/,false/*owner only*/);
        else
        if ((Pattr & 0200)!=0)
		    Pf.setWritable(true/*owner*/,true/*other*/);
    }
    //******************
    //*xehosts         *
    //******************
    private static void initHosts()
    {
//        String hosts=getSDPath(HOSTS);  //mnt/sdcard/Axe/xehosts
//    	int sz=(int)getAssetFileSize(HOSTS);// openAssetFD fail by the reason of file is compressed if file size is over limit, required is existing chk//~vaafR~
//        if (sz>=0)	//existing asset file                      //~vaafR~
//            copyAssetToSdcard(0/*no override*/,HOSTS,hosts);
        	copyToWkdir(0/*no override*/,HOSTS);
    }
    //******************                                           //~vaafI~
    //*xeebc.map                                                   //~vaafI~
    //******************                                           //~vaafI~
    private static void initEbcMap()                               //~vaafI~
    {                                                              //~vaafI~
//  	int sz=(int)getAssetFileSize(EBCMAP);                      //~vaafR~
//      if (sz>=0)	//existing asset file                          //~vaafR~
//      openAssetFD fail by the reason of file is compressed, required is existing chk//~vaafI~
        copyToWkdir(0/*no override*/,EBCMAP);                      //~vaafR~
//        copyToIcudir(0/*no override*/,UCONV37);                  //~vaafR~
//        copyToIcudir(0/*no override*/,UCONV930);                 //~vaafR~
//        copyToIcudir(0/*no override*/,UCONV939);                 //~vaafR~
        unzipicu();                                                //~vaafI~
    }                                                              //~vaafI~
//********************************************************
//*copy /sdcard/Ajagoc/cfg file to /data/data private dir
//*retur success/false
//********************************************************
	public static boolean copyAssetToSdcard(int Poverride,String Pfname,String Psdfname)
	{
        InputStream is;
		FileOutputStream fos;
        int len;
        boolean success=true;
        byte [] buff;
        int BUFFSZ=2048;
	//*********************
    	if (Dump.Y) Dump.println("copyToSdcard asset="+Pfname+",data="+Psdfname);
        File f=new File(Psdfname);
        if (f.exists())
        	if (Poverride==0)
            	return false;
	    is=openAssetFile(Pfname);
        if (is==null)
        	return false;
		fos=openOutputFile(Psdfname);
        if (fos==null)
        	return false;
        buff=new byte[BUFFSZ];
        try
        {
        	for (;;)
            {
        		len=is.read(buff);
            	if (len<0)
            		break;
            	fos.write(buff,0,len);
            }
        }
        catch (Exception e)
        {
        	success=false;
            Dump.println(e,"copyToSdcard output exception "+Pfname);
        }//catch
        try
        {
        	is.close();
        	fos.close();
        }
        catch(IOException e)
        {
        	success=false;
        	Dump.println(e,"CopyToSdcard close failed"+Pfname);
        }
        return success;
	}
//********************************************************
//*copy /sdcard/Ajagoc/cfg file to /data/data private dir
//*retur success/false
//********************************************************
	public static boolean copyToWkdir(int Poverride,String Pfname)
	{
        InputStream is;
		FileOutputStream fos;
        int len;
        boolean success=true;
        byte [] buff;
        int BUFFSZ=2048;
	//*********************
    	if (Dump.Y) Dump.println("copyToWkdir asset="+Pfname);
        String wdfnm=getWkdir()+"/"+Pfname;
        File f=new File(wdfnm);
        if (f.exists())
        	if (Poverride==0)
            {                                                      //~vaafI~
		    	if (Dump.Y) Dump.println("copyToWkdir bypass replace for "+wdfnm);//~vaafI~
            	return false;
            }                                                      //~vaafI~
	    is=openAssetFile(Pfname);
        if (is==null)
        	return false;
		fos=openOutputFile(wdfnm);
        if (fos==null)
        	return false;
        buff=new byte[BUFFSZ];
        try
        {
        	for (;;)
            {
        		len=is.read(buff);
            	if (len<0)
            		break;
            	fos.write(buff,0,len);
            }
        }
        catch (Exception e)
        {
        	success=false;
            Dump.println(e,"copyToWkdir output exception "+Pfname);
        }//catch
        try
        {
        	is.close();
        	fos.close();
        }
        catch(IOException e)
        {
        	success=false;
        	Dump.println(e,"CopyToWkdir close failed"+Pfname);
        }
        return success;
	}
//********************************************************         //~vaafI~
//*copy xxx.cnv /data/data private dir                             //~vaafI~
//*retur success/false                                             //~vaafI~
//********************************************************         //~vaafI~
	public static boolean copyToIcudir(int Poverride,String Pfname)//~vaafI~
	{                                                              //~vaafI~
    	if (Dump.Y) Dump.println("copyToIcudir asset="+Pfname);    //~vaafI~
        String tgt=getDataDir("icu")+"/"+Pfname;                   //~vaafI~
		return copyToDataDir(Poverride,Pfname,tgt,0755);           //~vaafR~
	}                                                              //~vaafI~
//********************************************************         //~vaafI~
	public static boolean copyToDataDir(int Poverride,String Psrc,String Ptgt,int Pchmod)//~vaafR~
	{                                                              //~vaafI~
        InputStream is;                                            //~vaafI~
		FileOutputStream fos;                                      //~vaafI~
        int len;                                                   //~vaafI~
        boolean success=true;                                      //~vaafI~
        byte [] buff;                                              //~vaafI~
        int BUFFSZ=2048;                                           //~vaafI~
	//*********************                                        //~vaafI~
    	if (Dump.Y) Dump.println("copyTodataDir src="+Psrc+",tgt="+Ptgt);//~vaafI~
        File f=new File(Ptgt);                                     //~vaafI~
        if (f.exists())                                            //~vaafI~
        	if (Poverride==0)                                      //~vaafI~
            {                                                      //~vaafI~
		    	if (Dump.Y) Dump.println("copyToDataDir bypass replace for "+Ptgt);//~vaafI~
            	return false;                                      //~vaafI~
            }                                                      //~vaafI~
	    is=openAssetFile(Psrc);                                    //~vaafI~
        if (is==null)                                              //~vaafI~
        	return false;                                          //~vaafI~
		fos=openOutputFile(Ptgt);                                  //~vaafI~
        if (fos==null)                                             //~vaafI~
        	return false;                                          //~vaafI~
        buff=new byte[BUFFSZ];                                     //~vaafI~
        try                                                        //~vaafI~
        {                                                          //~vaafI~
        	for (;;)                                               //~vaafI~
            {                                                      //~vaafI~
        		len=is.read(buff);                                 //~vaafI~
            	if (len<0)                                         //~vaafI~
            		break;                                         //~vaafI~
            	fos.write(buff,0,len);                             //~vaafI~
            }                                                      //~vaafI~
        }                                                          //~vaafI~
        catch (Exception e)                                        //~vaafI~
        {                                                          //~vaafI~
        	success=false;                                         //~vaafI~
            Dump.println(e,"copyToDataDir output exception "+Ptgt);//~vaafI~
        }//catch                                                   //~vaafI~
        try                                                        //~vaafI~
        {                                                          //~vaafI~
        	is.close();                                            //~vaafI~
        	fos.close();                                           //~vaafI~
            if (Pchmod>0)                                          //~vaafI~
    			chmod(f,Pchmod);                                   //~vaafI~
        }                                                          //~vaafI~
        catch(IOException e)                                       //~vaafI~
        {                                                          //~vaafI~
        	success=false;                                         //~vaafI~
        	Dump.println(e,"CopyToDataDir close failed"+Ptgt);     //~vaafI~
        }                                                          //~vaafI~
        return success;                                            //~vaafI~
	}                                                              //~vaafI~
//***********************************
    public static FileInputStream openInputFile(String Pfname)
    {
    	if (Dump.Y) Dump.println("open Input File="+Pfname);
	    FileInputStream is=null;
		try
		{
			is=new FileInputStream(Pfname);
		}
		catch (FileNotFoundException e)
		{
			Dump.println(e,"openInputFile Exception:"+Pfname);
		}
    	return is;
    }
//***********************************
    public static FileOutputStream openOutputFile(String Pfname)
    {
	    FileOutputStream out=null;
		try
        {
			out = new FileOutputStream(Pfname);
		} catch (Exception e)
		{
			Dump.println(e,"openOutputSdcard Exception:"+Pfname);
		}
    	return out;
    }
//**********************************************************************
//*file io
//**********************************************************************
    public static boolean load(String Pfname,byte[] Pout)
    {
        boolean rc=true;
        InputStream is;
		int buffsz=65536,len,offs,readlen;		//32760;
    //**********************
    	if (Dump.Y) Dump.println("AxeProp load:"+Pfname);
		is=openInputFile(Pfname);
		if (is==null)
        	return false;
        try
        {
        	for (offs=0;;)
            {
        		readlen=Math.min(buffsz,Pout.length-offs);
        		if (readlen==0)
        			break;
        		len=is.read(Pout,offs,readlen);
            	if (len<0)
            		break;
                offs+=len;
            }
            is.close();
        }
        catch (Exception e)
        {
        	rc=false;
            Dump.println(e,"load "+Pfname);
        }//catch
    	if (Dump.Y) Dump.println("load "+Pfname+",rc"+rc);
        return rc;
    }
//********************************************************
    public static boolean save(String Pfname,byte[] Pout)
    {
        boolean rc=true;
		FileOutputStream fos;
    //**********************
    	if (Dump.Y) Dump.println("AxeProp save:"+Pfname);
		fos=openOutputFile(Pfname);   //check SD card
        if (fos==null)
        	return false;
        try
        {
            fos.write(Pout,0,Pout.length);
            fos.close();
        }
        catch (Exception e)
        {
            Dump.println(e,"write output exception "+Pfname);
            rc=false;
        }//catch
        if (Dump.Y) Dump.println("AxeProp:save "+Pfname+",len="+Pout.length+",rc="+rc);
        return rc;
    }
//********************************************************
//*delete file
//********************************************************
    public static boolean delete(String Pfnm)
    {
    	boolean rc=true;
        if (Dump.Y) Dump.println("Axeprop delete"+Pfnm);
        File file=new File(Pfnm);
        if (file.exists())
		    file.delete();
		 else
			rc=false;
        if (Dump.Y) Dump.println("delete rc="+rc);
        return rc;
    }
//********************************************************
//*vfat list for ulib
//********************************************************
    public static String getVfatList()
    {
    	String rc;
    	StringBuffer list=new StringBuffer();
        if (Dump.Y) Dump.println("Axeprop getVfatList");
    	FileInputStream is=openInputFile(PROCMOUNT);
        if (is==null)
        	return null;
        try
        {
            InputStreamReader in=new InputStreamReader(is);
            BufferedReader br=new BufferedReader(in);
        	for (;;)
            {
        		String line=br.readLine();
                if (line==null)
                	break;
		        if (Dump.Y) Dump.println("vfatlist line:"+line);
                int offs=line.indexOf(VFAT);    // /dev.. /path vfat rw,..
                if (offs>0)
                {
	        		String[] tokens=line.split(" ");
			        int ctr=tokens.length;
        			if (ctr>=3
                	&&  tokens[2].equals(VFAT))
                    	list.append(tokens[1]+"\n");
                }
            }
            is.close();
        }
        catch (Exception e)
        {
        	rc=null;
            Dump.println(e,"getVfatList");
        }//catch
        if (list.length()==0)
        	rc=null;
        else
        	rc=new String(list);
        if (Dump.Y) Dump.println("vfatlist="+rc);
        return rc;
    }
//********************************************************         //~vay8I~
//*use /sdcard if available                                        //~vay8I~
//********************************************************         //~vay8I~
    private static String getSDCardDirectory()                     //~vay8I~
    {                                                              //~vay8I~
        String fpath1,fpath2;                                      //~vay8I~
        String fpath3;                                             //~vay8I~
        File f1=Environment.getExternalStorageDirectory();         //~vay8I~
        String rs=f1.getPath();//no exception                      //~vay8R~
//        try                                                      //+vay8R~
//        {                                                        //+vay8R~
//            fpath1=f1.getAbsolutePath();                         //+vay8R~
//            if (Dump.Y) Dump.println("trySDCard env abs="+fpath1);//+vay8R~
//            fpath1=f1.getCanonicalPath();                        //+vay8R~
//            if (Dump.Y) Dump.println("trySDCard env cannonical="+fpath1);//+vay8R~
//            File f2=new File(slinknameSDCard);                   //+vay8R~
//            if (f2.exists())                                     //+vay8R~
//            {                                                    //+vay8R~
//                fpath2=f2.getAbsolutePath();                     //+vay8R~
//                if (Dump.Y) Dump.println("trySDCard /sdcard abs="+fpath2);//+vay8R~
//                fpath2=f2.getCanonicalPath();                    //+vay8R~
//                if (Dump.Y) Dump.println("trySDCard /sdcard cannonical="+fpath2);//+vay8R~
//                if (fpath2.equals(fpath1))                       //+vay8R~
//                    rs=slinknameSDCard;                          //+vay8R~
//                                                                 //+vay8R~
//                File f3=new File(fpath2);                        //+vay8R~
//                fpath3=f3.getAbsolutePath();                     //+vay8R~
//                if (Dump.Y) Dump.println("trySDCard /sdcard abs="+fpath3);//+vay8R~
//                fpath3=f3.getCanonicalPath();                    //+vay8R~
//                if (Dump.Y) Dump.println("trySDCard /sdcard cannonical="+fpath3);//+vay8R~
//            }                                                    //+vay8R~
//        }                                                        //+vay8R~
//        catch(Exception e)                                       //+vay8R~
//        {                                                        //+vay8R~
//            Dump.println(e,"trySDCard");                         //+vay8R~
//        }                                                        //+vay8R~
        // 2 mount pint /storage/emulated/0 and /storage/emulated/legacy is same//+vay8I~
        // but seculity may not be same                            //+vay8I~
        // so use getExternalStorage Directory                     //+vay8I~
        return rs;                                                 //~vay8I~
    }                                                              //~vay8I~
}//class
