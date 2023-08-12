//*CID://+vc62R~:                             update#=  239;       //~vc62R~
//************************************************************************
//vc62 2023/08/06 media search also download from api29            //~vc62I~
//vc61 2023/08/04 (Bug) Uri to file path failes when space embedding//~vc51I~
//************************************************************************
package com.ahsv.utils;                                            //~vavwR~

import com.xe.Axe.AxeDlgMediaView;
import com.xe.Axe.Dump;                                            //~vc51I~
import com.ahsv.AG;                                                //~vc51I~

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;

import static android.app.Activity.*;

import androidx.annotation.RequiresApi;


public class UMediaStore
//        implements UPicker.UPickerI                              //~vc51R~
{
    private static final Uri uriAudioMedia=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI; //sdcard/Music or /SDcard/Download from API-1
//  private static final Uri uriDownloadMedia=MediaStore.Downloads.EXTERNAL_CONTENT_URI;//from api29
    private static final Uri uriImageMedia=MediaStore.Images.Media.EXTERNAL_CONTENT_URI;//~var8R~
//  private static final Uri uriImageMedia=MediaStore.Downloads.EXTERNAL_CONTENT_URI; //from api29 TODO test//~var8I~
    private   Uri uriDownloadMedia;
	private static final String CN="UMediaStore.";
    private static final int COL_ID=0;
    private static final int COL_TITLE=1;
    private static final int COL_ARTIST=2;
    private static final int COL_ISMUSIC=3;
    private static final int COL_MIMETYPE=4;
    private static final int COL_SIZE=5;
    private static final int COL_DURATION=6;
    private static final int COL_DISPLAYNAME=7;                    //~vc51I~
    private static final String[] COLUMNS_AUDIO=
					{MediaStore.Audio.Media._ID,
                     MediaStore.Audio.Media.TITLE,
                     MediaStore.Audio.Media.ARTIST,
                     MediaStore.Audio.Media.IS_MUSIC,
                     MediaStore.Audio.Media.MIME_TYPE,
                     MediaStore.Audio.Media.SIZE,
                     MediaStore.Audio.Media.DURATION,
                     MediaStore.Audio.Media.DISPLAY_NAME,          //~vc51I~
                    };
      private static final int COL_ID_DOWNLOAD=0;                  //~vc62R~
      private static final int COL_TITLE_DOWNLOAD=1;               //~vc62R~
      private static final int COL_MIMETYPE_DOWNLOAD=2;            //~vc62I~
      private static final int COL_SIZE_DOWNLOAD=3;                //~vc62R~
//    private static final int COL_ARTIST_DOWNLOAD=4;              //~vc62R~
      private static final int COL_DURATION_DOWNLOAD=4;            //~vc62R~
      private static final int COL_DISPLAY_NAME_DOWNLOAD=5;        //~vc62I~
      private static final int COL_RELATIVE_PATH_DOWNLOAD=6;       //~vc62I~
      private static final String[] COLUMNS_DOWNLOAD=              //~vc62M~
					{MediaStore.MediaColumns._ID,
					 MediaStore.MediaColumns.TITLE,
					 MediaStore.MediaColumns.MIME_TYPE,
					 MediaStore.MediaColumns.SIZE,
                     MediaStore.MediaColumns.DURATION,             //~vc62I~
					 MediaStore.MediaColumns.DISPLAY_NAME,         //~vc62I~
                     MediaStore.MediaColumns.RELATIVE_PATH,        //~vc62M~
                    };

    private String[] colsAudio;
	private ContentResolver CR;
//  private boolean swTest=false;	//TODO test
    private boolean swPaused;
    private ArrayList<AudioFile> audioFiles;
//  private Uri uriPicked,uriStarted,uriClickedUPicker;            //~vae9R~
    private Uri           uriStarted,uriClickedUPicker;            //~vae9I~
//  private AudioFile afPicked;                                    //~vae9R~
    private MediaPlayer MP;
//  private MainFrameOptions MFO;                                  //~1aK2R~
//  private PrefSetting      MFO;                                  //~1aK2I~//~vae2R~
//    private UMediaStoreI      callback;                             //~vae2I~//~vc51R~
//    private String[] strsUserBGMUri=new String[MAX_USERBGM];       //~vae9R~//~vae2R~//~vc51R~
//    private String[] strsUserBGMTitle=new String[MAX_USERBGM];     //~vae9R~//~vae2R~//~vc51R~
//    private Uri[]    urisUserBGM=new Uri[MAX_USERBGM];             //~vae2R~//~vc51R~
    private Uri uriImageBase;                                      //~var8I~
    private String srchName;                                       //~vc51I~
	private int srchSize;                                          //~vc51I~
    private AudioFile foundAudioFile;                              //~vc51I~
//**********************************                               //~v@@@I~//~1ak3I~//~vae2I~
//    public interface UMediaStoreI                                   //~v@@@R~//~1ak3I~//~vae2R~//~vc51R~
//    {                                                              //~v@@@I~//~1ak3I~//~vae2I~//~vc51R~
//        void BGMSelected(Uri PitemUri,AudioFile PaudioFile);  //~v@@@R~                //~1ak3R~//~vae2R~//~vc51R~
//        void ImageSelected(Uri Puri,String Pid,String PdisplayName,String Ptimestamp,String Pszie);//~var8R~//~vc51R~
//    }                                                              //~v@@@I~//~1ak3I~//~vae2I~//~vc51R~
//********************************************************
    public class AudioFile
    {
        public String title,artist,mimeType;
        public long id;
        public boolean isMusic;
        public long size;
        public int min,sec,duration;
        public String displayName;                                 //~vc51I~
        public AudioFile(String Pid,String Ptitle,String Partist,String PisMusic,String PmimeType,Long Psize,String Pduration,String PdisplayName)//~vc51R~
        {
	        title=Ptitle; artist=Partist; mimeType=PmimeType;
            isMusic=Utils.parseInt(PisMusic,0)!=0;
            id=Utils.parseLong(Pid,0L);
            size=Psize;
            duration=Utils.parseInt(Pduration,0); //millisec
            int s=duration/1000;	//sec
            min=s/60;
            sec=s%60;
            displayName=PdisplayName;                              //~vc51R~
        }
        public String toString()
        {
	        return "displayName="+displayName+",id="+id+",title="+title+",artist="+artist+",isMusic="+isMusic+",mimeType="+mimeType+",size="+size+",duration="+min+"."+sec;//~vc51R~
        }
    }
//********************************************************
    public UMediaStore()
    {
        if (Dump.Y) Dump.println(CN+"constructor osVersion="+AG.osVersion);
        AG.aUMediaStore=this;
//      if (swTest
//      ||  AG.osVersion>= Build.VERSION_CODES.R) //>=Android-11 api30
        try
        {
	        init();
        }
        catch(NoClassDefFoundError e)
        {
            Dump.println(e,CN+"constructor NoClassDefFoundError");//~vc51R~
        }
    }
//********************************************************
    private static UMediaStore getInstance()
    {
        UMediaStore ms=AG.aUMediaStore;
	    if (Dump.Y) Dump.println(CN+"getInstance AG.aUMediaStore="+Utils.toString(ms));
        if (ms==null)
        	ms=new UMediaStore();
        return ms;
    }
//********************************************************
    private void init()
    {
        if (Dump.Y) Dump.println(CN+"init");
        if (AG.osVersion>= 29) //Android10
//  		uriDownloadMedia=MediaStore.Downloads.EXTERNAL_CONTENT_URI;//from api29//~var8R~
    		uriDownloadMedia=getUriDownloadMedia29();               //~var8I~
	    CR=AG.context.getContentResolver();
        colsAudio=COLUMNS_AUDIO;
//      String strUri= Prop.getPreference(AG.PKEY_BGM_STRURI,"");  //~1aK2R~
//      String strUri= Utils.getPreference(PREFKEY_BGM_STRURI,""); //~1aK2I~//~vae2R~
//      if (strUri.compareTo("")!=0)                               //~vae2R~
//      	uriPicked=Uri.parse(strUri);                           //~vae2R~
//   	uriImageBase=uriImageMedia;                                //~var8I~//~vc51R~
//        if (AG.osVersion>= Build.VERSION_CODES.Q) //>=Android-10(Q)=api29//~var8I~//~vc51R~
//        {                                                          //~var8I~//~vc51R~
//            if (Dump.Y) Dump.println(CN+"requestPickupImage API29 volume_EXTERNAL_PRIMARY="+MediaStore.VOLUME_EXTERNAL_PRIMARY);//~var8I~//~vc51R~
//            uriImageBase=MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);//~var8I~//~vc51R~
//        }                                                          //~var8I~//~vc51R~
//        setupUserBGM();                                            //~vae2I~//~vc51R~
    }
//********************************************************         //~vae2I~
	@TargetApi(29)                                                 //~var8I~
	private Uri getUriDownloadMedia29()                         //~var8I~
    {                                                              //~var8I~
		return MediaStore.Downloads.EXTERNAL_CONTENT_URI;//from api29//~var8I~
    }                                                              //~var8I~
//********************************************************         //~vc51I~
 	private AudioFile srchAudioFile(String Pname,int Psize)        //~vc51R~
    {                                                              //~vc51I~
		if (Dump.Y) Dump.println(CN+"srchAudioFile name="+Pname+",size="+Psize);//~vc51R~
    	srchName=Pname;                                            //~vc51I~
      	srchSize=Psize;                                            //~vc51R~
    	foundAudioFile=null;                                       //~vc51I~
        try                                                        //~vc51I~
        {                                                          //~vc51I~
        	listAudioFile(uriAudioMedia);                          //~vc51I~
        }                                                          //~vc51I~
        catch(Exception e)                                         //~vc51I~
        {                                                          //~vc51I~
        	Dump.println(e,CN+"srchAudioFile Exception:"+Pname);   //~vc51I~
        }                                                          //~vc51I~
        srchName=null;                                             //~vc51I~
		if (Dump.Y) Dump.println(CN+"srchAudioFile rc="+foundAudioFile);//~vc51I~
        return foundAudioFile;                                     //~vc51I~
    }                                                              //~vc51I~
//********************************************************
	private ArrayList<AudioFile> listAudioFile(Uri PuirDir)
    {
    	int ctrLine=0;
	    if (Dump.Y) Dump.println(CN+"listAudioFile");
        int[] idxCol=new int[colsAudio.length];
//      String selection="${MediaStore.Audio.Media.IS_MUSIC} > 0}";
        String selection=null;
        Cursor cursor=CR.query(uriAudioMedia,colsAudio,selection,null,null);
        if (Dump.Y) Dump.println(CN+"listAudioFile uriAudioMedia="+uriAudioMedia+",cursor="+Utils.toString(cursor));
        ArrayList<AudioFile> members=new ArrayList<AudioFile>(0/*initial ctr*/);
        if (cursor!=null && cursor.moveToFirst())
        {
//  	    String path=cursor.getString(0);
//  	    if (Dump.Y) Dump.println(CN+"listAudioFile path="+Utils.toString(path));
//          File f=new File(path);
//  	    if (Dump.Y) Dump.println(CN+"listAudioFile File="+f);
        	for (int ii=0;ii<colsAudio.length;ii++)
            {
            	idxCol[ii]=cursor.getColumnIndex(colsAudio[ii]);
		        if (Dump.Y) Dump.println(CN+"listAudioFile colidx ii="+ii+",col="+colsAudio[ii]+",idx="+idxCol[ii]);
            }
            ctrLine=cursor.getCount();
        	members=new ArrayList<AudioFile>(ctrLine);
		    if (Dump.Y) Dump.println(CN+"listAudioFile ctrLine="+ctrLine);
            for (int ii=0;ii<ctrLine;ii++)
            {
                if (Dump.Y) Dump.println(CN+"listAudioFile ii="+ii+",title="+cursor.getString(idxCol[COL_TITLE])+",isMusic="+cursor.getString(idxCol[COL_ISMUSIC]));
                boolean isMusic=Utils.parseInt(cursor.getString(idxCol[COL_ISMUSIC]),0)!=0;
                if (isMusic)
                {
                	if (srchName!=null)                            //~vc51I~
                    {                                              //~vc51I~
		                int sz=(int)(cursor.getLong(idxCol[COL_SIZE]));//~vc51I~
						String name=cursor.getString(idxCol[COL_DISPLAYNAME]);//~vc51I~
		                if (Dump.Y) Dump.println(CN+"listAudioFile search displayName="+name+",sz="+sz+",srchName="+srchName+",srchSize="+srchSize);//~vc51R~
//                      if (sz==srchSize && name.equals(srchName)) //~vc51R~
//                        if (name.equals(srchName))               //~vc51R~
                        if (srchSize==0 || srchSize==sz)           //~vc51I~
                        if (name.equals(srchName))                 //~vc51I~
                        {                                          //~vc51I~
			                if (Dump.Y) Dump.println(CN+"listAudioFile search found name="+name+",sz="+sz+",srchName="+srchName+",srchSize="+srchSize);//~vc51R~
	                		foundAudioFile=newAudioFile(cursor,idxCol);//~vc51I~
                            break;                                 //~vc51I~
                        }                                          //~vc51I~
                    }                                              //~vc51I~
                    else                                           //~vc51I~
                    {                                              //~vc51I~
                	AudioFile af=newAudioFile(cursor,idxCol);
	                members.add(af);
                    }                                              //~vc51I~
                }
                cursor.moveToNext();
            }
        }
        if (cursor!=null)
            cursor.close();
//        if (members.size()>0)   //TODO test
//        {
//            uriPicked=ContentUris.withAppendedId(uriAudioMedia,members.get(0).id);
//            if ((AG.Options & AG.OPTIONS_BGM)!=0)
//                startBGM();
//        }
		if (Dump.Y) Dump.println(CN+"listAudioFile exit members size="+members.size());
        return members;
    }
//********************************************************
	public static Uri getMemberUri(Uri PuriDir,long Pid)
    {
    	Uri uri=ContentUris.withAppendedId(PuriDir,Pid);
		if (Dump.Y) Dump.println(CN+"getMemberUri uriDir="+PuriDir+",id="+Pid+",memberUri="+uri);
        return uri;
    }
//********************************************************         //~vc62I~
 	private AudioFile srchDownloadFile(String Pname,int Psize)     //~vc62I~
    {                                                              //~vc62I~
		if (Dump.Y) Dump.println(CN+"srchDownloadFile name="+Pname+",size="+Psize);//~vc62I~
    	srchName=Pname;                                            //~vc62I~
      	srchSize=Psize;                                            //~vc62I~
    	foundAudioFile=null;                                       //~vc62I~
        try                                                        //~vc62I~
        {                                                          //~vc62I~
        	listDownloadFile();                                    //~vc62R~
        }                                                          //~vc62I~
        catch(Exception e)                                         //~vc62I~
        {                                                          //~vc62I~
        	Dump.println(e,CN+"srchDownloadFile Exception:"+Pname);//~vc62I~
        }                                                          //~vc62I~
        srchName=null;                                             //~vc62I~
		if (Dump.Y) Dump.println(CN+"srchDownloadFile rc="+foundAudioFile);//~vc62I~
        return foundAudioFile;                                     //~vc62I~
    }                                                              //~vc62I~
//********************************************************
	private void listDownloadFile()
    {
    	int ctrLine=0;
	    if (Dump.Y) Dump.println(CN+"listDownloadFile uriDownload="+uriDownloadMedia);
        if (uriDownloadMedia==null)
        	return;
        String[] cols=COLUMNS_DOWNLOAD;
        int[] idxCol=new int[cols.length];
        String selection=null;
        Cursor cursor=CR.query(uriDownloadMedia,cols,selection,null,null);
        if (Dump.Y) Dump.println(CN+"listDownloadFile uriDownloadMedia="+uriDownloadMedia+",cursor="+Utils.toString(cursor));
        ArrayList<AudioFile> members=new ArrayList<AudioFile>(0/*initial ctr*/);
        if (cursor!=null && cursor.moveToFirst())
        {
        	for (int ii=0;ii<cols.length;ii++)                     //~vc62R~
            {
            	idxCol[ii]=cursor.getColumnIndex(cols[ii]);
		        if (Dump.Y) Dump.println(CN+"listDownloadFile colidx ii="+ii+",col="+cols[ii]+",idx="+idxCol[ii]);
            }
            ctrLine=cursor.getCount();
        	members=new ArrayList<AudioFile>(ctrLine);
		    if (Dump.Y) Dump.println(CN+"listDownloadFile ctrLine="+ctrLine);
            for (int ii=0;ii<ctrLine;ii++)
            {
                if (Dump.Y) Dump.println(CN+"listDownloadFile ii="+ii+",title="+cursor.getString(idxCol[COL_TITLE_DOWNLOAD])+",mimeType="+cursor.getString(idxCol[COL_MIMETYPE_DOWNLOAD]));//~vc62R~
                if (Dump.Y) Dump.println(CN+"displayName="+cursor.getString(idxCol[COL_DISPLAY_NAME_DOWNLOAD])+",size="+cursor.getString(idxCol[COL_SIZE_DOWNLOAD]));//~vc62R~
                if (Dump.Y) Dump.println(CN+"relativepath="+cursor.getString(idxCol[COL_RELATIVE_PATH_DOWNLOAD])+",duration="+cursor.getString(idxCol[COL_DURATION_DOWNLOAD]));//~vc62R~
                if (srchName!=null)                                //~vc62I~
                {                                                  //~vc62I~
                    String name=cursor.getString(idxCol[COL_DISPLAY_NAME_DOWNLOAD]);//~vc62R~
                    String mime=cursor.getString(idxCol[COL_MIMETYPE_DOWNLOAD]);//~vc62R~
                    int sz=(int)(cursor.getLong(idxCol[COL_SIZE_DOWNLOAD]));//~vc62R~
                    if (srchSize==0 || srchSize==sz)               //~vc62I~
                    if (mime.startsWith(AxeDlgMediaView.MIME_AUDIO))//~vc62I~
                    if (name.equals(srchName))                     //~vc62I~
                    {                                              //~vc62I~
                        if (Dump.Y) Dump.println(CN+"listDownload search found name="+name+",sz="+sz+",srchName="+srchName+",srchSize="+srchSize);//~vc62I~
                        foundAudioFile=newAudioFile(cursor,idxCol);//~vc62I~
                        break;                                     //~vc62I~
                    }                                              //~vc62I~
                }                                                  //~vc62I~
                cursor.moveToNext();
            }
        }
        if (cursor!=null)
            cursor.close();
    }
//********************************************************
	private AudioFile newAudioFile(Cursor Pcursor,int[] PidxCol)
    {
        if (Dump.Y) Dump.println(CN+"newAudioFile id="+Pcursor.getString(PidxCol[COL_ID])+
					",title="+Pcursor.getString(PidxCol[COL_TITLE])+	//title
					",artist="+Pcursor.getString(PidxCol[COL_ARTIST])+	//artist
					",isMusic="+Pcursor.getString(PidxCol[COL_ISMUSIC])+	//isMusic
					",mimetype="+Pcursor.getString(PidxCol[COL_MIMETYPE])+   //mime_type
					",size="+Pcursor.getLong(PidxCol[COL_SIZE])+   //mime_type
					",duration="+Pcursor.getString(PidxCol[COL_DURATION])+   //mime_type
					",displayname="+Pcursor.getString(PidxCol[COL_DISPLAYNAME]));   //filename//~vc51I~
        AudioFile af=new AudioFile(
					Pcursor.getString(PidxCol[COL_ID]),	//id
					Pcursor.getString(PidxCol[COL_TITLE]),	//title
					Pcursor.getString(PidxCol[COL_ARTIST]),	//artist
					Pcursor.getString(PidxCol[COL_ISMUSIC]),	//isMusic
					Pcursor.getString(PidxCol[COL_MIMETYPE]),   //mime_type
					Pcursor.getLong(PidxCol[COL_SIZE]),   //mime_type
					Pcursor.getString(PidxCol[COL_DURATION]),   //mime_type
					Pcursor.getString(PidxCol[COL_DISPLAYNAME])   //mime_type//~vc51I~
					);
        if (Dump.Y) Dump.println(CN+"newAudioFile AudioFile="+af.toString());
        return af;
    }
//********************************************************
	public AudioFile getAudioInfo(Uri Puri)                        //~vc51R~
    {
	    if (Dump.Y) Dump.println(CN+"getAudioInfo Puri="+Puri);
        int[] idxCol=new int[colsAudio.length];
        Cursor cursor=CR.query(Puri,colsAudio,null,null,null);
        AudioFile af=null;
        if (Dump.Y) Dump.println(CN+"getAudioInfo cursor="+Utils.toString(cursor));
        if (cursor!=null && cursor.moveToFirst())
        {
        	for (int ii=0;ii<colsAudio.length;ii++)
            {
            	idxCol[ii]=cursor.getColumnIndex(colsAudio[ii]);
		        if (Dump.Y) Dump.println(CN+"listAudioInfo colidx ii="+ii+",col="+colsAudio[ii]+",idx="+idxCol[ii]);
            }
		    if (Dump.Y) Dump.println(CN+"getAudioInfo ctrLine="+cursor.getCount());
            af=newAudioFile(cursor,idxCol);
        }
        if (cursor!=null)
            cursor.close();
        return af;
    }
//********************************************************         //~vc51I~
//	public AudioFile getAudioFile(String Puri)                     //~vc51R~
  	public AudioFile getAudioFile(String Puri,int Psize)           //~vc51I~
    {                                                              //~vc51I~
	    if (Dump.Y) Dump.println(CN+"getAudioFile Puri="+Puri+",size="+Psize);//~vc51R~
        AudioFile af=null;                                         //~vc51I~
        Uri uri=Uri.parse(Puri);                                   //~vc62I~
        String path=uri.getPath();                                 //~vc62I~
		int pos=path.lastIndexOf('/');                             //~vc51I~//~vc62R~
        String memb;                                               //~vc62I~
        if (pos>0)                                                 //~vc62I~
            memb=path.substring(pos+1);                            //~vc62I~
        else                                                       //~vc62I~
            memb=path;                                             //~vc62I~
        srchAudioFile(memb,Psize);            //~vc51R~            //~vc62R~
        af=foundAudioFile;                                     //~vc51I~//~vc62R~
        if (af==null)                                              //~vc62R~
        	srchDownloadFile(memb,Psize);                          //~vc62R~
        if (Dump.Y) Dump.println(CN+"getAudioFile path="+path+",rc="+af);        //~vc51I~//~vc62R~
        return af;                                                 //~vc51R~
    }                                                              //~vc51I~
//********************************************************
	private void openAudioFiles(ArrayList<AudioFile> Plist)
    {
	    if (Dump.Y) Dump.println(CN+"openAudioFiles");
        for (AudioFile af:Plist)
        {
            if (Dump.Y) Dump.println(CN+"openAudioFiles AudioFile="+af.toString());
        	if (!af.isMusic)
            	continue;
        	Uri uri= ContentUris.withAppendedId(uriAudioMedia,af.id);
            openAudioFile(uri);
        }
    }
//********************************************************
	private void openAudioFile(Uri Puri)
    {
	    if (Dump.Y) Dump.println(CN+"openAudioFile uri="+Puri);
        try
        {
        	InputStream is=CR.openInputStream(Puri);
        	InputStreamReader isr=new InputStreamReader(is);
        	BufferedReader br=new BufferedReader(isr);
            br.close();
        }
        catch(FileNotFoundException e)
        {
        	Dump.println(e,CN+"openAudioFile FileNotFound:"+Puri);
        }
        catch(IOException e)
        {
        	Dump.println(e,CN+"openAudioFile IOException:"+Puri);
        }
	    if (Dump.Y) Dump.println(CN+"openAudioFile exit");
    }
//********************************************************
    public void resetMP(MediaPlayer Pmp,boolean PswStop)           //~vc51R~
    {
	    if (Dump.Y) Dump.println(CN+"resetMP MP="+Pmp+",swStop="+PswStop);            //~vc51R~//+vc62R~
        if (Pmp==null)
        {
		    if (Dump.Y) Dump.println(CN+"resetMP return by MP=null");
        	return;
        }
//      Uri uri=uriStarted;                                        //~vc51R~
		boolean swPlaying=false;                                   //~vc62I~
        try
        {
        	if (PswStop)	//not known not playing                //~vc62I~
				swPlaying=Pmp.isPlaying();                         //~vc62R~
		    if (Dump.Y) Dump.println(CN+"resetMP stop swPlaying="+swPlaying);//~vc62R~
        	if (PswStop)
              if (swPlaying)                                       //~vc62I~
        		Pmp.stop();
        }
        catch(IllegalArgumentException e)
        {
        	Dump.printlnNoMsg(e,CN+"play IlleagalArgumentException");//~var8R~
        }
        catch(IllegalStateException e)
        {
        	Dump.printlnNoMsg(e,CN+"play IlleagalStateException");//~var8R~
        }
        try
        {
		    if (Dump.Y) Dump.println(CN+"resetMP reset");
          if (swPlaying)                                           //~vc62I~
        	Pmp.reset();
        }
        catch(IllegalArgumentException e)
        {
        	Dump.printlnNoMsg(e,CN+"play IlleagalArgumentException");//~var8R~
        }
        catch(IllegalStateException e)
        {
        	Dump.printlnNoMsg(e,CN+"play IlleagalStateException");//~var8R~
        }
        try
        {
		    if (Dump.Y) Dump.println(CN+"resetMP release");
          if (swPlaying)                                           //~vc62I~
        	Pmp.release();
        }
        catch(IllegalArgumentException e)
        {
        	Dump.printlnNoMsg(e,CN+"play IlleagalArgumentException");//~var8R~
        }
        catch(IllegalStateException e)
        {
        	Dump.printlnNoMsg(e,CN+"play IlleagalStateException");//~var8R~
        }
//      MP=null;                                                   //~vc51R~
    }
//********************************************************
	public void setStreamType(MediaPlayer Pplayer)                 //~vc51R~
    {
        if (AG.osVersion>= 26) //Android8.0 Oreo=Api26
        	setStreamType_26(Pplayer);
        else
        	setStreamType_25(Pplayer);
    }
    @SuppressWarnings("deprecation")
	private void setStreamType_25(MediaPlayer Pplayer)
    {
	    if (Dump.Y) Dump.println(CN+"setStreamPlayer_25");
        Pplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }
	@TargetApi(26)     //KitKat
	private void setStreamType_26(MediaPlayer Pplayer)
    {
	    if (Dump.Y) Dump.println(CN+"setStreamPlayer_26");
        Pplayer.setAudioAttributes(
            new AudioAttributes
                .Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build());
//        AudioAttributes.Builder ab=new AudioAttributes.Builder();
//        ab.setContentType(AudioAttributes.CONTENT_TYPE_MUSIC);
//        ab.setUsage(AudioAttributes.USAGE_MEDIA);
//        AudioAttributes aa=ab.build();
//        Pplayer.setAudioAttributes(aa);
    }
//********************************************************
	private void requestPickup(boolean PswUPicker)                 //~vae9R~
    {
	    if (Dump.Y) Dump.println(CN+"requestPickup osVersion="+AG.osVersion+",swUPicker="+PswUPicker);//~vae9R~
//      if (AG.osVersion>= 30) Android-11(R)                       //~vae9R~
//        if (PswUPicker)                                            //~vae9R~//~vc51R~
//        {                                                        //~vc51R~
//            UPicker.newInstance(uriAudioMedia,this).showDialog();//~vc51R~
//            return;                                              //~vc51R~
//        }                                                        //~vc51R~
      if (false)//TODO test
      {
        Intent intent2=new Intent(Intent.ACTION_OPEN_DOCUMENT);   //can not play on picker,invalid cusrsor data for GetString()
        intent2.addCategory(Intent.CATEGORY_OPENABLE);
//      intent2.setType("*/*");
        intent2.setType("audio/*");
        AG.activity.startActivityForResult(intent2,AG.ACTIVITY_REQUEST_PICKUP_AUDIO);
      }
      else
      if (false)//TODO test
      {
        Intent intent2=new Intent(Intent.ACTION_GET_CONTENT);    //can not play on picker,invalid cusrsor data for GetString()
        intent2.setType("*/*");
        AG.activity.startActivityForResult(intent2,AG.ACTIVITY_REQUEST_PICKUP_AUDIO);
      }
      else
      if (false) //TODO test
      {
        Intent intent2=new Intent(Intent.ACTION_GET_CONTENT);    //can not play on picker,invalid cusrsor data
        intent2.setType("audio/*");
        AG.activity.startActivityForResult(intent2,AG.ACTIVITY_REQUEST_PICKUP_AUDIO);
      }
      else
      {
        Intent intent=new Intent(Intent.ACTION_PICK,uriAudioMedia);// can play on picker to select music
//      intent.setType("audio/*");
        AG.activity.startActivityForResult(intent,AG.ACTIVITY_REQUEST_PICKUP_AUDIO);
      }
	    if (Dump.Y) Dump.println(CN+"requestPickup exit");
    }
//********************************************************         //~var8I~
	private void requestPickupImage()                              //~var8I~
    {                                                              //~var8I~
	    if (Dump.Y) Dump.println(CN+"requestPickupImage osVersion="+AG.osVersion+",uri="+uriImageMedia);//~var8R~
     	String action=Intent.ACTION_PICK;                          //~var8I~
     	uriImageBase=uriImageMedia;                                //~var8R~
        if (AG.osVersion>= Build.VERSION_CODES.Q) //>=Android-10(Q)=api29//~var8I~
        {                                                          //~var8I~
		    if (Dump.Y) Dump.println(CN+"requestPickupImage API29 volume_EXTERNAL_PRIMARY="+MediaStore.VOLUME_EXTERNAL_PRIMARY);//~var8I~
        	uriImageBase=MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);//~var8R~
        }                                                          //~var8I~
     	Intent intent=new Intent(action,uriImageBase);             //~var8R~
        intent.setType("image/*");  //avoid video              //~@@@1R~//~var8I~
        AG.activity.startActivityForResult(intent,AG.ACTIVITY_REQUEST_PICKUP_IMAGE);//~var8I~
	    if (Dump.Y) Dump.println(CN+"requestPickupImage exit action="+action+",uriImageBase="+uriImageBase+",intent="+intent+",data="+intent.getData()+",component="+intent.getComponent());//~vavwR~
	    if (Dump.Y) Dump.println(CN+"requestPickupImage exit strUri="+intent.getDataString());//~vavwI~
	    if (Dump.Y) Dump.println(CN+"requestPickupImage exit type="+intent.getType());//~vavwI~
	    if (Dump.Y) Dump.println(CN+"requestPickupImage exit package="+intent.getPackage());//~vavwI~
    }                                                              //~var8I~
//********************************************************         //~vavwI~
	private void requestPickupImageBySelectedPicker(Intent Pintent)//~vavwI~
    {                                                              //~vavwI~
	    if (Dump.Y) Dump.println(CN+"requestPickupImageBySelectedPicker sVersion="+AG.osVersion+",uri="+uriImageMedia);//~vavwI~
     	uriImageBase=uriImageMedia;                                //~vavwI~
        if (AG.osVersion>= Build.VERSION_CODES.Q) //>=Android-10(Q)=api29//~vavwI~
        {                                                          //~vavwI~
		    if (Dump.Y) Dump.println(CN+"requestPickupImage API29 volume_EXTERNAL_PRIMARY="+MediaStore.VOLUME_EXTERNAL_PRIMARY);//~vavwI~
        	uriImageBase=MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);//~vavwI~
        }                                                          //~vavwI~
      if (true)                                                    //~vavwI~
      {                                                            //~vavwI~
     	Intent intent=new Intent(Intent.ACTION_PICK);              //~vavwI~
        intent.setType("image/*");  //avoid video                  //~vavwI~
        AG.activity.startActivityForResult(intent,AG.ACTIVITY_REQUEST_PICKUP_IMAGE);//~vavwI~
        Pintent=intent;                                            //~vavwI~
      }                                                            //~vavwI~
      else                                                         //~vavwI~
      if (true)                                                    //~vavwI~
      {                                                            //~vavwI~
     	Intent intent=new Intent(Intent.ACTION_PICK,uriImageBase);             //~vavwI~
        intent.setType("image/*");  //avoid video                  //~vavwI~
        ComponentName cmp=Pintent.getComponent();                  //~vavwI~
        intent.setComponent(cmp);                                  //~vavwI~
     	intent.setData(uriImageBase);                              //~vavwI~
        AG.activity.startActivityForResult(intent,AG.ACTIVITY_REQUEST_PICKUP_IMAGE);//~vavwI~
        Pintent=intent;                                            //~vavwI~
      }                                                            //~vavwI~
      else                                                         //~vavwI~
      {                                                            //~vavwI~
     	Pintent.setData(uriImageBase);                             //~vavwI~
        Pintent.setType("image/*");  //avoid video                 //~vavwI~
        AG.activity.startActivityForResult(Pintent,AG.ACTIVITY_REQUEST_PICKUP_IMAGE);//~vavwI~
      }                                                            //~vavwI~
	    if (Dump.Y) Dump.println(CN+"requestPickupImageBySelectedPicker exit uriImageBase="+uriImageBase+",intent="+Pintent+",data="+Pintent.getData()+",component-"+Pintent.getComponent());//~vavwR~
	    if (Dump.Y) Dump.println(CN+"requestPickupImageBySelectedPicker exit strUri="+Pintent.getDataString());//~vavwI~
	    if (Dump.Y) Dump.println(CN+"requestPickupImageBySelectedPicker exit type="+Pintent.getType());//~vavwI~
	    if (Dump.Y) Dump.println(CN+"requestPickupImageBySelectedPicker exit package="+Pintent.getPackage());//~vavwI~
    }                                                              //~vavwI~
//********************************************************
//*from AMain
//********************************************************
//    public static void onActivityResult(int Prequest,int Presult,Intent Pintent)//~vc51R~
//    {                                                            //~vc51R~
//        if (Dump.Y) Dump.println(CN+"onActivityResult request="+Prequest+",result="+Presult);//~vc51R~
//        UMediaStore ums=getInstance();                           //~vc51R~
//        if (Presult==RESULT_OK)                                  //~vc51R~
//        {                                                        //~vc51R~
//            Uri uri=Pintent.getData();                           //~vc51R~
//            AudioFile af=ums.getAudioInfo(uri);                  //~vc51R~
//            if (af==null)                                        //~vc51R~
//            {                                                    //~vc51R~
//                invalidSelection(uri);                           //~vc51R~
//                return;                                          //~vc51R~
//            }                                                    //~vc51R~
//            if (!af.isMusic)                                     //~vc51R~
//            {                                                    //~vc51R~
//                if (Dump.Y) Dump.println(CN+"onActivityResult OK but not Music="+af.toString());//~vc51R~
//            }                                                    //~vc51R~
////          ums.afPicked=af;                                       //~vae9R~//~vc51R~
////          ums.uriPicked=uri;                                     //~vae9R~//~vc51R~
//////          if ((AG.Options & AG.OPTIONS_BGM)!=0)                  //~1aK2R~//~vae2R~//~vc51R~
////            if (!PrefSetting.isNoBGM())                            //~1aK2I~//~vae2R~//~vc51R~
////            {                                                    //~vae2R~//~vc51R~
////                ums.play(uri);                                   //~vae2R~//~vc51R~
////            }                                                    //~vae2R~//~vc51R~
////            if (ums.MFO!=null)                                   //~vae2R~//~vc51R~
////            {                                                    //~vae2R~//~vc51R~
////                ums.MFO.setBGMTitle(ums.afPicked.title,uri.toString()/*save to preference by OK button*/);//~vae2R~//~vc51R~
////            }                                                    //~vae2R~//~vc51R~
//            if (ums.callback!=null)                                //~vae2I~//~vc51R~
//                ums.callback.BGMSelected(uri,af);                  //~vae2R~//~vc51R~
//            if (Dump.Y) Dump.println(CN+"onActivityResult OK uri="+uri);//~vc51R~
//        }                                                        //~vc51R~
////      ums.MFO=null;                                              //~vae2R~//~vc51R~
//        if (Dump.Y) Dump.println(CN+"onActivityResult exit");    //~vc51R~
//    }                                                            //~vc51R~
//********************************************************         //~var8I~
//*from AMain                                                      //~var8I~
//********************************************************         //~var8I~
//    public static void onActivityResultImage(int Presult,Intent Pintent)//~var8I~//~vc51R~
//    {                                                              //~var8I~//~vc51R~
//        if (Dump.Y) Dump.println(CN+"onActivityResultImage result="+Presult+",intent="+Pintent);//~vavxR~//~vc51R~
//        if (Pintent==null)                                         //~vavxI~//~vc51R~
//        {                                                          //~vavxI~//~vc51R~
//            if (Dump.Y) Dump.println(CN+"onActivityResultImage null Intent");//~vavxI~//~vc51R~
//            return;                                                //~vavxI~//~vc51R~
//        }                                                          //~vavxI~//~vc51R~
//        if (Presult==RESULT_OK)                                    //~var8I~//~vc51R~
//        {                                                          //~var8I~//~vc51R~
//            UMediaStore ums=getInstance();                         //~var8I~//~vc51R~
//            Uri uri=Pintent.getData();                             //~var8I~//~vc51R~
//            String path=uri.getPath();                         //~@@@1I~//~var8I~//~vc51R~
//            if (Dump.Y) Dump.println(CN+"onActivityResultResultImage uri="+uri+",path="+path);//~var8I~//~vc51R~
//            if (ums.callback!=null)                                //~var8I~//~vc51R~
//            {                                                      //~var8I~//~vc51R~
//                ums.getSetectedImage(uri);                                  //~2923I~//~var8R~//~vc51R~
//            }                                                      //~var8I~//~vc51R~
//        }                                                          //~var8I~//~vc51R~
//        if (Dump.Y) Dump.println(CN+"onActivityResultImage exit"); //~var8I~//~vc51R~
//    }                                                              //~var8I~//~vc51R~
//********************************************************         //~var8I~
//*from onActivityResult                                           //~var8I~
//********************************************************         //~var8I~
//    private void getSetectedImage(Uri Puri)                             //~2923I~//~var8R~//~vc51R~
//    {                                                              //~2923I~//~var8I~//~vc51R~
////* DATA is deprecated at Api29                                    //~var8I~//~vc51R~
//        if (Dump.Y) Dump.println(CN+"getSelectedImage uri="+Puri+",path="+Puri.getPath()+",encoded="+Puri.getEncodedPath());//~var8I~//~vc51R~
//        String[] columns={MediaStore.Images.Media._ID,             //~var8R~//~vc51R~
//                            MediaStore.Images.Media.DISPLAY_NAME,  //~var8I~//~vc51R~
//                            MediaStore.Images.Media.SIZE,          //~var8I~//~vc51R~
//                            MediaStore.Images.Media.DATE_ADDED,    //~var8I~//~vc51R~
//                            MediaStore.Images.Media.DATE_MODIFIED, //~var8R~//~vc51R~
//                            MediaStore.Images.Media.DATE_TAKEN,    //~var8I~//~vc51R~
//                            };                                     //~var8I~//~vc51R~
//        Cursor cursor=CR.query(Puri,columns,null,null,null,null);//~2923I~//~var8R~//~vc51R~
//        cursor.moveToFirst();                              //~2923I~//~var8I~//~vc51R~
//        int colIdx=cursor.getColumnIndex(columns[0]);      //~2923I~//~var8I~//~vc51R~
//        String id=cursor.getString(colIdx);              //~2923I~ //~var8R~//~vc51R~
//        int colIdx1=cursor.getColumnIndex(columns[1]);     //~@@@1I~//~var8R~//~vc51R~
//        String name=cursor.getString(colIdx1);             //~@@@1I~//~var8R~//~vc51R~
//        int colIdx2=cursor.getColumnIndex(columns[2]);     //~@@@1I~//~var8R~//~vc51R~
//        String size=cursor.getString(colIdx2);             //~@@@1I~//~var8R~//~vc51R~
//        int colIdx3=cursor.getColumnIndex(columns[3]);             //~var8R~//~vc51R~
//        String timeAdd=cursor.getString(colIdx3);                  //~var8R~//~vc51R~
//        int colIdx4=cursor.getColumnIndex(columns[4]);             //~var8I~//~vc51R~
//        String timeMod=cursor.getString(colIdx4);                  //~var8I~//~vc51R~
//        int colIdx5=cursor.getColumnIndex(columns[5]);             //~var8R~//~vc51R~
//        String timeTaken=cursor.getString(colIdx5);                //~var8I~//~vc51R~
//        cursor.close();                                          //~vc51R~
//        String ts=timeAdd==null ? timeMod : timeAdd;               //~var8M~//~vc51R~
//        if (ts==null)                                              //~var8I~//~vc51R~
//            ts=timeTaken;                                          //~var8I~//~vc51R~
//        if (ts==null)                                              //~var8M~//~vc51R~
//            ts="0";                                                //~var8M~//~vc51R~
//        if (size==null)                                            //~var8I~//~vc51R~
//            size="0";                                              //~var8I~//~vc51R~
//        if (Dump.Y) Dump.println(CN+"getSelected ts="+ts+",tsAdd="+timeAdd+",tsMod="+timeMod+",tsTaken="+timeTaken);//~var8I~//~vc51R~
//        if (Dump.Y) Dump.println(CN+"getSelected column="+Utils.toString(columns));//~var8I~//~vc51R~
//        if (Dump.Y) Dump.println(CN+"getSelected uri="+Puri+",id="+id+",displayName="+name+",size="+size+",time="+ts);//~var8R~//~vc51R~
//        callback.ImageSelected(Puri,id,name,ts,size);              //~var8R~//~vc51R~
//    }                                                              //~2923I~//~var8I~//~vc51R~
//********************************************************         //~var8I~
    private Bitmap loadBMP29(Uri Puri,String Pid)                  //~var8R~
    {                                                              //~var8I~
//* DATA is deprecated at Api29                                    //~var8I~
        if (Dump.Y) Dump.println(CN+"loadBMP29 by ID id="+Pid+",uri="+Puri+",path="+Puri.getPath());//~var8R~
     	Uri uri=uriImageBase;
        long lid=Long.parseLong(Pid);                              //~var8R~
        Uri uriID=ContentUris.withAppendedId(uri,lid);              //~var8M~
        if (Dump.Y) Dump.println(CN+"loadBMP29 uri="+uri+",path="+uri.getPath());//~var8I~
        if (Dump.Y) Dump.println(CN+"loadBMP29 uriID="+uriID+",path="+uriID.getPath());//~var8I~
		String[] columns={MediaStore.Images.Media._ID,             //~var8I~
							MediaStore.Images.Media.DISPLAY_NAME,  //~var8I~
							MediaStore.Images.Media.SIZE,          //~var8I~
							MediaStore.Images.Media.DATE_ADDED,    //~var8I~
							MediaStore.Images.Media.DATE_MODIFIED, //~var8I~
							};                                     //~var8I~
        Cursor cursor=CR.query(uriID,columns,null,null,null,null); //~var8R~
        if (cursor.getCount()==0)                                     //~vavbI~
        {                                                          //~vavbI~
        	if (Dump.Y) Dump.println(CN+"loadBMP29 getCount==0, return null");//~vavbI~
            return null;                                           //~vavbI~
        }                                                          //~vavbI~
        cursor.moveToFirst();                                      //~var8I~
        int colIdx=cursor.getColumnIndex(columns[0]);              //~var8I~
        long id=cursor.getLong(colIdx);                            //~var8I~
//2023/01/23 vavb (Bug)Exception at Umediastore if qury count is 0 //~vavbI~
        int colIdx1=cursor.getColumnIndex(columns[1]);             //~var8I~
        String name=cursor.getString(colIdx1);                     //~var8I~
        int colIdx2=cursor.getColumnIndex(columns[2]);             //~var8I~
        String size=cursor.getString(colIdx2);                     //~var8I~
        int colIdx3=cursor.getColumnIndex(columns[3]);             //~var8I~
        String timeAdd=cursor.getString(colIdx3);                  //~var8I~
        int colIdx4=cursor.getColumnIndex(columns[4]);             //~var8I~
        String timeMod=cursor.getString(colIdx4);                  //~var8I~
        cursor.close();                                            //~var8I~
        String ts=timeAdd==null ? timeMod : timeAdd;               //~var8I~
        if (ts==null)                                              //~var8I~
            ts="0";                                                //~var8I~
        if (size==null)                                            //~var8I~
            size="0";                                              //~var8I~
        if (Dump.Y) Dump.println(CN+"loadBMP29 ts="+ts+",tsAdd="+timeAdd+",tsMod="+timeMod);//~var8R~
        if (Dump.Y) Dump.println(CN+"loadBMP29 column="+Utils.toString(columns));//~var8R~
        if (Dump.Y) Dump.println(CN+"loadBMP29 id="+id+",displayName="+name+",size="+size+",time="+ts);//~var8R~
        Bitmap bm=loadBMP29(uriID);
        return bm;//~var8I~
    }                                                              //~var8I~
////********************************************************       //~var8I~
//    private void getSetectedImage(Uri Puri)                      //~var8I~
//    {                                                            //~var8I~
//        String[] columns={MediaStore.Images.Media.DATA/*absolute filesystem path*/,//~var8I~
//                            MediaStore.Images.Media.DISPLAY_NAME,//~var8I~
//                            MediaStore.Images.Media.SIZE,        //~var8I~
//                            MediaStore.Images.Media.DATE_ADDED,  //~var8I~
//                            MediaStore.Images.Media.DATE_MODIFIED,//~var8I~
//                            MediaStore.Images.Media.RELATIVE_PATH,//~var8I~
//                            MediaStore.Images.Media._ID,         //~var8I~
//                            MediaStore.Images.Media.DATE_TAKEN,  //~var8I~
//                            };                                   //~var8I~
//        Cursor cursor=CR.query(Puri,columns,null,null,null,null);//~var8I~
//        cursor.moveToFirst();                                    //~var8I~
//        int colIdx=cursor.getColumnIndex(columns[0]);            //~var8I~
//        String path=cursor.getString(colIdx);                    //~var8I~
//        int colIdx1=cursor.getColumnIndex(columns[1]);           //~var8I~
//        String name=cursor.getString(colIdx1);                   //~var8I~
//        int colIdx2=cursor.getColumnIndex(columns[2]);           //~var8I~
//        String size=cursor.getString(colIdx2);                   //~var8I~
//        int colIdx3=cursor.getColumnIndex(columns[3]);           //~var8I~
//        String timeAdd=cursor.getString(colIdx3);                //~var8I~
//        int colIdx4=cursor.getColumnIndex(columns[4]);           //~var8I~
//        String timeMod=cursor.getString(colIdx4);                //~var8I~
//        int colIdx5=cursor.getColumnIndex(columns[5]);           //~var8I~
//        String rp=cursor.getString(colIdx5);                     //~var8I~
//        if (Dump.Y) Dump.println(CN+"getSelected rp="+rp);       //~var8I~
//        int colIdx6=cursor.getColumnIndex(columns[6]);           //~var8I~
//        long   id=cursor.getLong(colIdx6);                       //~var8I~
//        if (Dump.Y) Dump.println(CN+"getSelected id="+id);       //~var8I~
//        int colIdx7=cursor.getColumnIndex(columns[7]);           //~var8I~
//        String timeTaken=cursor.getString(colIdx7);              //~var8I~
//        if (Dump.Y) Dump.println(CN+"getSelected timeTaken="+timeTaken);//~var8I~
//        Uri uriid=ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,id);//~var8I~
//        if (Dump.Y) Dump.println(CN+"getSelected id uri ="+uriid+",path="+uriid.getPath()+",Puri.path="+Puri.getPath());//~var8I~
////      path=uriid.getPath();   //TODO test                      //~var8I~
//        cursor.close();                                          //~var8I~
//        Bitmap bm;                                               //~var8I~
//        try {                                                    //~var8I~
//            bm = MediaStore.Images.Media.getBitmap(CR, Puri);    //~var8I~
//        }catch(Exception e){Dump.println(CN+"getBMP");bm=null;}  //~var8I~
//            if (bm!=null) if (Dump.Y) Dump.println(CN+"getSelected getBitmap w="+bm.getWidth()+",h="+bm.getHeight());//~var8I~
//        String ts=timeAdd==null ? timeMod : timeAdd;             //~var8I~
//        if (ts==null)                                            //~var8I~
//            ts=getFileTimestamp(path);                           //~var8I~
//        if (ts==null)                                            //~var8I~
//            ts="0";                                              //~var8I~
//        if (Dump.Y) Dump.println(CN+"getSelected tsAdd="+timeAdd+",tsMod="+timeMod+",ts="+ts);//~var8I~
//        if (Dump.Y) Dump.println(CN+"getSelected column="+Utils.toString(columns));//~var8I~
//        if (Dump.Y) Dump.println(CN+"getSelected path="+path+",displayName="+name+",size="+size+",time="+ts);//~var8I~
//        callback.ImageSelected(Puri,path,name,ts,size);          //~var8I~
//    }                                                            //~var8I~
//********************************************************         //~var8M~
	public static Bitmap loadBMP(String Ppath)                     //~var8I~
    {                                                              //~var8M~
	    if (Dump.Y) Dump.println(CN+"loadBMP path="+Ppath);        //~var8M~
        Bitmap bm=BitmapFactory.decodeFile(Ppath);                 //~var8M~
        return bm;                                                 //~var8M~
    }                                                              //~var8M~
//********************************************************         //~var8M~
	public Bitmap loadBMP_Stream(Uri Puri)                         //~var8R~
    {                                                              //~var8M~
	    if (Dump.Y) Dump.println(CN+"loadBMP_Stream uri="+Puri);   //~var8R~
        Bitmap bm=null;                                            //~var8M~
        Uri uri=Puri;                                              //~var8M~
        try                                                        //~var8M~
        {                                                          //~var8M~
        	InputStream is=CR.openInputStream(uri);                //~var8M~
            if (is!=null)                                          //~var8M~
            {                                                      //~var8M~
        		BufferedInputStream bis=new BufferedInputStream(is);//~var8M~
            	bm= BitmapFactory.decodeStream(bis);               //~var8M~
	            bis.close();                                       //~var8M~
            }                                                      //~var8M~
        }                                                          //~var8M~
        catch(FileNotFoundException e)                             //~var8M~
        {                                                          //~var8M~
        	Dump.printlnNoMsg(e,CN+"loadBMP_Stream FileNotFound:"+uri);//~var8R~
        }                                                          //~var8M~
        catch(IOException e)                                       //~var8M~
        {                                                          //~var8M~
        	Dump.printlnNoMsg(e,CN+"loadBMP_Stream IOException:"+uri);//~var8R~
        }                                                          //~var8M~
	    if (Dump.Y) Dump.println(CN+"loadBMP_Stream exit bm="+bm); //~var8R~
        return bm;                                                 //~var8M~
    }                                                              //~var8M~
//********************************************************         //~var8I~
	public Bitmap loadBMP_Descriptor(Uri Puri)                     //~var8I~
    {                                                              //~var8I~
	    if (Dump.Y) Dump.println(CN+"loadBMP_Descriptor uri="+Puri);//~var8I~
        Bitmap bm=null;                                            //~var8I~
        FileDescriptor fd=null;                                                 //~var8I~
        try                                                        //~var8I~
        {                                                          //~var8I~
			ParcelFileDescriptor pfd=CR.openFileDescriptor(Puri,"r");//~var8I~
            fd=pfd.getFileDescriptor();                         //~var8I~
            bm=BitmapFactory.decodeFileDescriptor(fd);             //~var8I~
        }                                                          //~var8I~
        catch(IOException e)                                       //~var8I~
        {                                                          //~var8I~
        	Dump.printlnNoMsg(e,CN+"loadBMP_Descriptor IOException:"+Puri);//~var8R~
        }                                                          //~var8I~
		if (Dump.Y) Dump.println(CN+"loadBMP_Descriptor fd="+fd+",bm="+bm);//~var8I~
        if (bm!=null) if (Dump.Y) Dump.println(CN+"loadBMP_Descriptor ww="+bm.getWidth()+",hh="+bm.getHeight());//~var8I~
        return bm;                                                 //~var8I~
    }                                                              //~var8I~
//********************************************************         //~var8I~
    public Bitmap loadBMP(Uri Puri,String Pid)                     //~var8R~
    {                                                              //~var8I~
        if (Dump.Y) Dump.println(CN+"loadBMP uri="+Puri+",osVersion="+AG.osVersion);//~var8R~
        Bitmap bm=null;                                            //~var8R~
        try                                                        //~var8I~
        {                                                          //~var8I~
            if (AG.osVersion>= Build.VERSION_CODES.Q) //>=Android-10(Q)=api29//~var8R~
                bm=loadBMP29(Puri,Pid);                            //~var8R~
            else                                                   //~var8R~
                bm=loadBMP28(Puri);                                //~var8R~
		}                                                          //~var8I~
        catch(Exception e)                                         //~var8I~
        {                                                          //~var8I~
        	Dump.printlnNoMsg(e,CN+"loadBMP Exception uri="+Puri+",id="+Pid);//~var8R~
        }                                                          //~var8I~
        if (Dump.Y) Dump.println(CN+"getBitmap bm="+bm);           //~var8R~
        if (bm!=null) if (Dump.Y) Dump.println(CN+"loadBMP w="+bm.getWidth()+",h="+bm.getHeight());//~var8I~
        return bm;                                                 //~var8R~
    }                                                              //~var8I~
//********************************************************         //~var8I~
//*api>=29=Q=Android10                                             //~var8R~
//********************************************************         //~var8I~
	@TargetApi(29)                                                 //~var8R~
//  public Bitmap loadBMP29(Uri Puri)                              //~var8R~//~vavbR~
    private Bitmap loadBMP29(Uri Puri)                             //~vavbI~
    {                                                              //~var8I~
        if (Dump.Y) Dump.println(CN+"getBitmap29 uri="+Puri);      //~var8R~
        Bitmap bm=null;                                            //~var8I~
//        if (false)//TODO test                                    //~var8R~
//        {                                                        //~var8R~
//            bm=loadBMP_Stream(Puri);                             //~var8R~
//        }                                                        //~var8R~
//        else                                                     //~var8R~
//        if (true) //todo                                         //~var8R~
//        {                                                        //~var8R~
//            bm=loadBMP_Descriptor(Puri);                         //~var8R~
//        }                                                        //~var8R~
//        else                                                     //~var8R~
        try                                                        //~var8I~
		{                                                          //~var8I~
	        if (Dump.Y) Dump.println(CN+"getBitmap29 by ImageDecoder");//~var8I~
        	ImageDecoder.Source src=ImageDecoder.createSource(CR, Puri);//~var8I~
            if (Dump.Y) Dump.println(CN+"loadBMP29 createSource src="+src);//~var8R~
            bm=ImageDecoder.decodeBitmap(src);                     //~var8I~
	        if (AG.osVersion>= 26) //=Oreo:Android8                //~var8R~
	            bm=convertHardwareBitmap(bm);                       //~var8R~
        }                                                          //~var8I~
        catch(IOException e)                                       //~var8I~
        {                                                          //~var8I~
            Dump.printlnNoMsg(e,CN+"loadBMP29 IOE uri="+Puri); //~var8R~
        }                                                          //~var8I~
        catch(Exception e)                                         //~var8I~
        {                                                          //~var8I~
            Dump.printlnNoMsg(e,CN+"loadBMP29 Exception uri="+Puri);//~var8R~
        }                                                          //~var8I~
        if (Dump.Y) Dump.println(CN+"loadBMP29 bm="+bm);           //~var8R~
        if (bm!=null) if (Dump.Y) Dump.println(CN+"loadBMP29 w="+bm.getWidth()+",h="+bm.getHeight());//~var8R~
        return bm;                                                 //~var8I~
    }                                                              //~var8I~
	@TargetApi(26)                                                 //~var8I~
    //*************************************************            //~var8I~
    public Bitmap convertHardwareBitmap(Bitmap Pbmp)               //~var8I~
    {                                                              //~var8I~
        if (Pbmp==null)                                            //~var8I~
        	return Pbmp;                                           //~var8I~
        if (Dump.Y) Dump.println(CN+"convertHardwareBitmap Pbmp.config="+Pbmp.getConfig()+",const="+Bitmap.Config.HARDWARE);//~var8R~
        if (Pbmp.getConfig()!=Bitmap.Config.HARDWARE)              //~var8I~
        	return Pbmp;                                           //~var8I~
        Bitmap bm=Pbmp.copy(Bitmap.Config.ARGB_8888,false/*isMutable*/);//~var8R~
        Pbmp.recycle();                                            //~var8I~
        if (Dump.Y) Dump.println(CN+"convertHardwareBitmap exit bmp.config="+bm.getConfig());//~var8R~
        return bm;                                                 //~var8I~
    }                                                              //~var8I~
//********************************************************         //~var8I~
//*api<=28                                                         //~var8R~
//********************************************************         //~var8I~
    @SuppressWarnings("deprecation")                               //~va42I~//~var8R~
//  public Bitmap loadBMP28(Uri Puri)                              //~var8R~//~vavbR~
    private Bitmap loadBMP28(Uri Puri)                             //~vavbI~
    {                                                              //~var8I~
        if (Dump.Y) Dump.println(CN+"getBitmap28 by MediaStore.Images.Media.getBitmap uri="+Puri);//~var8R~
        Bitmap bm=null;                                            //~var8I~
        try                                                        //~var8I~
		{                                                          //~var8I~
        	bm = MediaStore.Images.Media.getBitmap(CR, Puri);   //deprecated api29=A10=Q//~var8R~
        }                                                          //~var8I~
        catch(IOException e)                                       //~var8I~
        {                                                          //~var8I~
            Dump.printlnNoMsg(e,CN+"loadBMP28 IOE uri="+Puri); //~var8R~
        }                                                          //~var8I~
        catch(Exception e)                                         //~var8I~
        {                                                          //~var8I~
            Dump.printlnNoMsg(e,CN+"loadBMP28 Exception uri="+Puri);//~var8R~
        }                                                          //~var8I~
        if (Dump.Y) Dump.println(CN+"getBitmap bm="+bm);           //~var8I~
        if (bm!=null) if (Dump.Y) Dump.println(CN+"loadBMP28 w="+bm.getWidth()+",h="+bm.getHeight());//~var8R~
        return bm;                                                 //~var8I~
    }                                                              //~var8I~
//********************************************************         //~var8I~
    private static String getFileTimestamp(String Ppath)           //~var8I~
    {                                                              //~var8I~
        if (Dump.Y) Dump.println(CN+"getFileTimestamp path="+Ppath);//~var8I~
    	long ts=0L;                                                //~var8I~
    	try                                                        //~var8I~
        {                                                          //~var8I~
        	File f=new File(Ppath);                                //~var8I~
            if (f.exists())                                        //~var8I~
            	ts=f.lastModified();                               //~var8I~
        }                                                          //~var8I~
        catch (Exception e)                                        //~var8I~
        {                                                          //~var8I~
        	Dump.printlnNoMsg(e,CN+"getFileTimestamp path="+Ppath);//~var8R~
        }                                                          //~var8I~
        String rc=ts==0L ? null : Long.toString(ts);               //~var8I~
        if (Dump.Y) Dump.println(CN+"getFileTimestamp rc="+rc+",path="+Ppath);//~var8I~
        return rc;                                                 //~var8I~
    }                                                              //~var8I~
//********************************************************         //~var8I~
//    public static void selectImage(UMediaStoreI Pcallback)         //~var8I~//~vc51R~
//    {                                                              //~var8I~//~vc51R~
//        UMediaStore ums=getInstance();                             //~var8I~//~vc51R~
//        if (Dump.Y) Dump.println(CN+"selectImage AG.swGrantedExternalStorageRead="+AG.swGrantedExternalStorageRead);//~var8I~//~vc51R~
//        if (!AG.swGrantedExternalStorageRead)                      //~var8I~//~vc51R~
//        {                                                          //~var8I~//~vc51R~
//            UView.showToast(R.string.ErrNoExternalStoragePermission);//~var8I~//~vc51R~
//            return;                                                //~var8I~//~vc51R~
//        }                                                          //~var8I~//~vc51R~
//        ums.callback=Pcallback;                                    //~var8I~//~vc51R~
//        ums.requestPickupImage();                                  //~var8I~//~vc51R~
//    }                                                              //~var8I~//~vc51R~
////********************************************************       //~vae9R~
//    public static String getCurrentTitle()                       //~vae9R~
//    {                                                            //~vae9R~
//        UMediaStore ums=getInstance();                           //~vae9R~
//        if (Dump.Y) Dump.println(CN+"getCurrentTitle MP="+Utils.toString(ums.MP)+",AudioFile="+Utils.toString(ums.afPicked));//~vae9R~
//        String rc="";                                            //~vae9R~
//        if (ums.afPicked!=null)                                  //~vae9R~
//            rc=ums.afPicked.title;                               //~vae9R~
//        if (Dump.Y) Dump.println(CN+"getCurrentTitle rc="+rc);   //~vae9R~
//        return rc;                                               //~vae9R~
//    }                                                            //~vae9R~
//********************************************************
	public static ArrayList<AudioFile> getMemberList(Uri PuriDir)
    {
        UMediaStore ums=getInstance();
	    if (Dump.Y) Dump.println(CN+"getMemberList uriDir="+PuriDir);
		ArrayList<AudioFile> list=ums.listAudioFile(PuriDir);
        return list;
    }
//********************************************************
//    private static void invalidSelection(Uri Puri)               //~vc51R~
//    {                                                            //~vc51R~
//        UView.showToast(Utils.getStr(R.string.ErrInvalidAudioSelection," "+Puri));//~vc51R~
//    }                                                            //~vc51R~
//********************************************************         //~vavwI~
//    public static void selectImagePicker(UMediaStoreI Pcallback)   //~vavwI~//~vc51R~
//    {                                                              //~vavwI~//~vc51R~
//        UMediaStore ums=getInstance();                             //~vavwI~//~vc51R~
//        if (Dump.Y) Dump.println(CN+"selectImagePicker");          //~vavwI~//~vc51R~
//        if (!AG.swGrantedExternalStorageRead)                      //~vavwI~//~vc51R~
//        {                                                          //~vavwI~//~vc51R~
//            UView.showToast(R.string.ErrNoExternalStoragePermission);//~vavwI~//~vc51R~
//            return;                                                //~vavwI~//~vc51R~
//        }                                                          //~vavwI~//~vc51R~
//        ums.callback=Pcallback;                                    //~vavwI~//~vc51R~
//        ums.requestSelectImagePicker();                            //~vavwI~//~vc51R~
//    }                                                              //~vavwI~//~vc51R~
//********************************************************         //~vavwI~
//    private void requestSelectImagePicker()                        //~vavwR~//~vc51R~
//    {                                                              //~vavwI~//~vc51R~
//        if (Dump.Y) Dump.println(CN+"requestSelectImagePicker osversion="+AG.osVersion);//~vavwR~//~vc51R~
//        String action=Intent.ACTION_PICK_ACTIVITY;                 //~vavwI~//~vc51R~
//        Intent intent=new Intent(action);                          //~vavwI~//~vc51R~
//        intent.putExtra(Intent.EXTRA_INTENT,new Intent(Intent.ACTION_PICK));//~vavwI~//~vc51R~
//        intent.putExtra(Intent.EXTRA_TITLE,Utils.getStr(R.string.Msg_selectImagePicker));//~vavwR~//~vc51R~
//        AG.activity.startActivityForResult(intent,AG.ACTIVITY_REQUEST_PICKUP_ACTION);//~vavwR~//~vc51R~
//        if (Dump.Y) Dump.println(CN+"requestSelectImagePickup exit");//~vavwR~//~vc51R~
//    }                                                              //~vavwI~//~vc51R~
//********************************************************         //~vavwI~
//*from AMain                                                      //~vavwI~
//********************************************************         //~vavwI~
	public static void onActivityResultSelectImagePicker(int Presult,Intent Pintent)//~vavwI~
    {                                                              //~vavwI~
	    if (Dump.Y) Dump.println(CN+"onActivityResultSelectImagePicker result="+Presult+",intent="+Pintent+",data="+Pintent.getData()+",component="+Pintent.getComponent());//~vavwR~
        if (Presult==RESULT_OK)   //-1                             //~vavwR~
        {                                                          //~vavwI~
		    if (Dump.Y) Dump.println(CN+"onActivityResultSelectImagePicker RESULT_OK");//~vavwI~
	        UMediaStore ums=getInstance();                         //~vavwI~
			ums.requestPickupImageBySelectedPicker(Pintent);       //~vavwR~
        }                                                          //~vavwI~
    }                                                              //~vavwI~
}//class
