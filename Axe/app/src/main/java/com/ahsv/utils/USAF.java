//*CID://+vc5rR~:                             update#=  263;       //~vc5rR~
//************************************************************************//~v102I~
//vc5r 2023/07/25 try /sdcard for realpath for api<30              //~vc5rI~
//vc52 2023/06/06 show shared storage path                         //~vc52I~
//vc4y 2023/05/22 >=android11(Api30),access all file option setting by setting-android related dialog//~vc4yI~
//vc4x 2023/05/16 Try to get path for shared storage               //~vc4xI~
//v77y:230519 v77w but readdir returns null(permission required?),try ACTION_MANAGE_ALL_FILE_ACCESS_PERMISSION//~v77yI~
//v77w:230519 uri-->path is avalable from api30(android11:R) and readdir using fd gotten by openDescriptor returns null//~v77wI~
//vc4x 2023/05/16 Try to get path for shared storage               //~vc4xI~
//vc4u 2023/04/25 generate "." and ".." entry for udirlist         //~vc4uI~
//vc4t 2023/04/24 fstat has to allow membername of wildcard        //~vc4tI~
//vc4s 2023/04/15 edit using SAF                                   //~vc4sI~
//vc4r 2023/04/14 rename using SAF                                 //~vc4rI~
//vc4q 2023/04/01 support shared storage using SAF(StorageAccessFramework)//~vc4qI~
//************************************************************************//~v102I~
//*access to shared storage                                        //~vc4qI~
//************************************************************************//~vc4qI~
package com.ahsv.utils;                                            //~vc09R~
                                                                   //~1110I~
import com.xe.Axe.Axe;                                             //~vc09R~
import com.xe.Axe.BuildConfig;
import com.xe.Axe.AxeDlgAllFilesAlert;
import com.xe.Axe.AxeG;
import com.xe.Axe.AxeJNI;

import static com.ahsv.utils.TreeMember.*;
import static com.xe.Axe.AxeJNIdef.*;                              //~vc4sI~
import static com.xe.Axe.Gxeh.*;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.UriPermission;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.DocumentsContract;
import android.provider.Settings;

import androidx.documentfile.provider.DocumentFile;                //~vc4qI~
                                                                   //~vc4qI~
import com.ahsv.AG;                                                //~vc09R~
import com.xe.Axe.Dump;                                            //~vc09R~
import com.xe.Axe.Gxeh;
import com.xe.Axe.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class USAF                                                  //~vc4qR~
{                                                                  //~1110I~
    private static final String CN="USAF.";                        //~vc4qR~
                                                                   //~vc4qI~
    private static final String typeDocument="application/text";   //~vc4qI~
    private static final String PREFIX_SDCARD="/sdcard";            //+vc5rI~
    private static final int AID_CREATE_FILE=Axe.REQID_SAF+1;      //~vc4qR~
    private static final int AID_OPEN_FILE=Axe.REQID_SAF+2;        //~vc4qR~
    private static final int AID_OPEN_TREE=Axe.REQID_SAF+3;        //~vc4qR~
    private static final int AID_MANAGE_ALLFILES=Axe.REQID_SAF+4;  //~v77yI~
                                                                   //~vc4qI~
    private static final String SCHEME_CONTENT="content";          //~vc4qI~
    private static final String SCHEME_FILE="file";                //~vc4qI~
    private static final int BUFFSZ=8192;                          //~vc4qI~
//  private static final String INTENT_PARM="parm"; //Intent.EXTRA_USER;//~vc4qR~
	private static final int  ABOUT_SIZE_MEMBERDATA    =64;        //~vc4qI~
                                                                   //~vc4qI~
                                                                   //~vc4qI~
	private static final int C2JUFSO_UFSTAT    =0x01;                                    //~vby4I~//~vc4qI~
	private static final int C2JUFSO_WILDCARD  =0x02;                                    //~vby4I~//~vc4qI~
                                                                   //~vc4qI~
	private ContentResolver CR;                                    //~vc4qR~
	private StorageManager SM;                                    //~vc4xI~
	private String startActivityParm;                              //~vc4qI~
    private int errno;                                             //~vc4uI~
    private Uri uriUdirlist;                                       //~vc4uI~
	private Map<String,String> mapDocPath=new HashMap<String,String>();//~v77wI~
//  private boolean swCheckedPermissionAllFiles;                   //~vc4yR~
//  private boolean swPermissionAllFilesCurrent;                   //~vc4yR~
    private boolean swOpenDlgAllFiles;                             //~vc4yI~
    public  String  yourSharedFolder;                              //~vc52I~
//********************************************************         //~vc4qI~
	public USAF()                                                    //~vc4qI~
    {                                                              //~vc4qI~
	    CR=AG.context.getContentResolver();                        //~vc4qI~
	    SM=(StorageManager)(AG.context.getSystemService(Context.STORAGE_SERVICE));//~vc4xI~
        if (Dump.Y) Dump.println(CN+"constructor");                //~vc4yI~
	    new USAF2(CR);    //AG.aUSAF2                              //~vc4rR~
        chkPermission();                                           //~vc4yR~
	    chkStorageVolume();                                        //~vc4xI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	public static void createFile(Uri PuriInitial, String Pfnm)         //~v@@@I~//~vc4qR~
	{                                                              //~v@@@I~
        if (Dump.Y) Dump.println(CN+"createFile uri="+PuriInitial+",fnm="+Pfnm);//~vc4qR~
    	Intent intent=new Intent(Intent.ACTION_CREATE_DOCUMENT);   //~vc4qR~
    	intent.addCategory(Intent.CATEGORY_OPENABLE);          //~v@@@I~//~vc4qR~
    	intent.setType(typeDocument);                              //~vc4qI~
//  	intent.putExtra(INTENT_PARM,Pfnm);                         //~vc4qR~
        if (PuriInitial!=null)                                     //~vc4qI~
	    	intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI,PuriInitial); //~vc4qI~
        startActivity(intent,AID_CREATE_FILE,Pfnm);               //~vc4qR~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
    private static void startActivity(Intent Pintent,int Paction,String Pparm)//~vc4qR~
    {                                                              //~vc4qI~
        if (Dump.Y) Dump.println(CN+"startActivity intent="+Pintent+",action="+Paction+",parm="+Pparm);//~vc4qR~
        AG.aUSAF.startActivityParm=new String(Pparm);  //Pparm will be unref-ed at j2c//~vc4qI~
        AxeG.activity.startActivityForResult(Pintent,Paction);                   //~vc4qR~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	public static void openFile(Uri PuriInitial,String Pfnm)       //~vc4qI~
	{                                                              //~vc4qI~
        if (Dump.Y) Dump.println(CN+"openFile uri="+PuriInitial+",fnm="+Pfnm);//~vc4qR~
    	Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT);     //~vc4qI~
    	intent.addCategory(Intent.CATEGORY_OPENABLE);             //~vc4qI~
    	intent.setType(typeDocument);                              //~vc4qI~
//  	intent.putExtra(INTENT_PARM,Pfnm);                         //~vc4qR~
        if (PuriInitial!=null)                                     //~vc4qI~
	    	intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI,PuriInitial); //~vc4qI~
        startActivity(intent,AID_OPEN_FILE,Pfnm);                 //~vc4qR~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	public static void openDir(Uri PuriInitial,String Pfnm)        //~vc4qI~
	{                                                              //~vc4qI~
        if (Dump.Y) Dump.println(CN+"openDir uri="+PuriInitial+",fnm="+Pfnm);//~vc4qR~
    	Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);//~vc4qI~
//  	intent.putExtra(INTENT_PARM,Pfnm);                         //~vc4qR~
//      Bundle b=new Bundle();                                     //~vc4qR~
//      b.putString(INTENT_PARM,Pfnm);                             //~vc4qR~
//      intent.putExtras(b);    //TODO test, disappear at onActivityResult//~vc4qR~
//      AG.aUSAF.startActivityParm=Pfnm;                           //~vc4qR~
        if (PuriInitial!=null)                                     //~vc4qI~
	    	intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI,PuriInitial); //~vc4qI~
        int flags=Intent.FLAG_GRANT_READ_URI_PERMISSION       //0x01//~vc4qR~
        		| Intent.FLAG_GRANT_WRITE_URI_PERMISSION      //0x02//~vc4qR~
				| Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION;	//0x40 persistent thru device restart//~vc4qR~
        intent.addFlags(flags); //TODO test                        //~vc4qI~
        startActivity(intent,AID_OPEN_TREE,Pfnm);                 //~vc4qR~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	public static void onActivityResult(int PreqID,int Presult,Intent Pintent)//~vc4qI~
	{                                                              //~vc4qI~
        if (Dump.Y) Dump.println(CN+"onActivityResult reqID="+PreqID+",result="+Presult+",RESULT_OK="+Activity.RESULT_OK+",intent="+Pintent);//~vc4qR~
        String strUri=null;                                        //~vc4qR~
        String path=null;                                          //~vc4qR~
	    String parm=AG.aUSAF.startActivityParm;                    //~vc4qI~
        int rc=-1;                                                 //~vc4qI~
        if (PreqID==AID_MANAGE_ALLFILES)                           //~v77yI~
        {                                                          //~v77yI~
            onActivityResultManageAllFiles(Presult,Pintent);       //~v77yI~
            return;                                                //~v77yI~
        }                                                          //~v77yI~
        if (Pintent!=null)                                         //~vc4qI~
        {                                                          //~vc4qI~
	        Uri uri=Pintent.getData();
            AG.aUSAF.chkStorageVolume(uri);  //TODO test           //~vc4xI~//~v77wR~//~vc4xR~
        	Uri uriQuery=DocumentsContract.buildDocumentUriUsingTree(uri,DocumentsContract.getTreeDocumentId(uri));//~vc4qI~
//          strUri=uriQuery.toString();                            //~vc4qR~
            strUri=uri.toString();                                 //~vc4qI~
//          parm=Pintent.getStringExtra(INTENT_PARM);              //~vc4qR~
//          Bundle b=Pintent.getExtras();                          //~vc4qR~
	    	if (Presult== Activity.RESULT_OK)                      //~vc4qI~
            {                                                      //~vc4qI~
                setPersistablePermission(Pintent,uri);             //~vc4qI~
                path=getPath(uri);                                 //~vc4qI~
                rc=0;                                              //~vc4qI~
            }                                                      //~vc4qI~
		    if (Dump.Y) Dump.println("USAF.onActivityResult uri="+uri+",uriQuery="+uriQuery+",parm="+parm+",path="+path+",uriQuetPath="+getPath(uriQuery));//~vc4qR~
        }                                                          //~vc4qI~
        AxeJNI.jniOnActivityResult(PreqID,rc,parm,strUri,path);    //~vc4qR~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
    @SuppressLint("WrongConstant")
    public static void setPersistablePermission(Intent Pintent, Uri Puri)//~vc4qI~
	{                                                              //~vc4qI~
    	int flag=Pintent.getFlags() & (Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);//~vc4qI~
        if (Dump.Y) Dump.println(CN+"setPersistablePermission uri="+Puri+",getFlag=0x"+Integer.toHexString(Pintent.getFlags())+",flag="+Integer.toHexString(flag));//~vc4qI~
        AG.aUSAF.CR.takePersistableUriPermission(Puri,flag);       //~vc4qR~
    }                                                              //~vc4qI~
//********************************************************         //~vae0M~//~vc4qI~
	private static InputStream openInputDocument(Uri Puri)                //~vae0M~//~vc4qR~
    {                                                              //~vae0M~//~vc4qI~
	    if (Dump.Y) Dump.println(CN+"openInputDocument uri="+Puri);//~vae0M~//~vc4qI~
        InputStream is=null;                                       //~vae0M~//~vc4qI~
        try                                                        //~vae0M~//~vc4qI~
        {                                                          //~vae0M~//~vc4qI~
        	is=AG.aUSAF.CR.openInputStream(Puri);                           //~vae0M~//~vc4qR~
        }                                                          //~vae0M~//~vc4qI~
        catch(FileNotFoundException e)                             //~vae0M~//~vc4qI~
        {                                                          //~vae0M~//~vc4qI~
        	Dump.println(e,CN+"openInputDocument FileNotFound:"+Puri);//~vae0M~//~vc4qI~
        }                                                          //~vae0M~//~vc4qI~
	    if (Dump.Y) Dump.println(CN+"openInputDocument is="+Utils.toString(is));//~vae0M~//~vc4qI~
        return is;                                                 //~vae0M~//~vc4qI~
    }                                                              //~vae0M~//~vc4qI~
//********************************************************         //~vc4qI~
	private static int open(Uri Puri,String Pmode)                 //~vc4qI~
    {                                                              //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"opene mode="+Pmode+",uri="+Puri);//~vc4qI~
        int fd=-1;                                                 //~vc4qI~
        try                                                        //~vc4qI~
        {                                                          //~vc4qI~
        	ParcelFileDescriptor pfd=AG.aUSAF.CR.openFileDescriptor(Puri,Pmode);//~vc4qI~
            fd=pfd.getFd();                                        //~vc4qI~
        }                                                          //~vc4qI~
        catch(FileNotFoundException e)                             //~vc4qI~
        {                                                          //~vc4qI~
        	Dump.println(e,CN+"openopen FileNotFound uri="+Puri);  //~vc4qI~
        }                                                          //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"open fd="+fd);                //~vc4qR~
        return fd;                                                 //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	private static BufferedReader getReader(Uri Puri)               //~vc4qR~
    {                                                              //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"getReader uri="+Puri);        //~vc4qI~
        InputStream is=openInputDocument(Puri);                    //~vc4qI~
        if (is==null)                                              //~vc4qI~
        	return null;                                           //~vc4qI~
        InputStreamReader isr=new InputStreamReader(is);           //~vc4qI~
        BufferedReader br=new BufferedReader(isr);                 //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"getReader br="+br);           //~vc4qI~
        return br;                                                 //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	private static String readLine(BufferedReader Pbr)             //~vc4qR~
    {                                                              //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"readLine BufferedReader="+Pbr);//~vc4qI~
        String line=null;                                          //~vc4qI~
        try                                                        //~vc4qI~
        {                                                          //~vc4qI~
        	line=Pbr.readLine();                            //~vc4qI~
			if (Dump.Y) Dump.println(CN+"readLine line="+line);    //~vc4qI~
            if (line==null)                                        //~vc4qI~
            	Pbr.close();                                        //~vc4qI~
        }                                                          //~vc4qI~
        catch(IOException e)                                       //~vc4qI~
        {                                                          //~vc4qI~
        	Dump.println(e,CN+"readLine IOException");             //~vc4qI~
        }                                                          //~vc4qI~
	    return line;//~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	private static OutputStream openOutputDocument(Uri Puri)       //~vc4qR~
    {                                                              //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"openOutputDocument uri="+Puri);//~vc4qI~
        OutputStream os=null;                                      //~vc4qI~
        try                                                        //~vc4qI~
        {                                                          //~vc4qI~
        	os=AG.aUSAF.CR.openOutputStream(Puri);                 //~vc4qR~
        }                                                          //~vc4qI~
        catch(FileNotFoundException e)                             //~vc4qI~
        {                                                          //~vc4qI~
        	Dump.println(e,CN+"openOutputDocument FileNotFound:"+Puri);//~vc4qI~
        }                                                          //~vc4qI~
        return os;                                                 //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	private static PrintWriter getWriter(Uri Puri, String Pencoding)//~vc4qR~
    {                                                              //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"getWriter uri="+Puri);        //~vc4qI~
		OutputStream os=openOutputDocument(Puri);                  //~vc4qI~
        if (os==null)                                              //~vc4qI~
        	return null;                                                //~vc4qI~
        String encoding=(Pencoding!=null ? Pencoding : "UTF-8");     //~vc4qI~
        OutputStreamWriter osw= null;//~vc4qI~
        try                                                        //~vc4qR~
		{                                                          //~vc4qI~
            osw = new OutputStreamWriter(os,encoding);
        }                                                          //~vc4qR~
		catch (UnsupportedEncodingException e)                     //~vc4qI~
		{                                                          //~vc4qI~
        	Dump.println(e,CN+"getWriter UnsupportedEncodingException encoding="+encoding);//~vc4qI~
        }
        PrintWriter pw=new PrintWriter(osw,true/*autoFlush*/);     //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"pw="+pw);
        return pw;//~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	private static boolean writeLine(Uri Puri,PrintWriter Ppw,String Pline)//~vc4qR~
    {                                                              //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"writeLine uri="+Puri+",line="+Pline);//~vc4qR~
        boolean rc=true;                                           //~vc4qI~
        Ppw.print(Pline);                                          //~vc4qR~
	    if (Dump.Y) Dump.println(CN+"writeLine rc="+rc);           //~vc4qR~
        return rc;                                                 //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	private static void closeWriter(Uri Puri,PrintWriter Ppw)      //~vc4qR~
    {                                                              //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"closeWriter uri="+Puri);      //~vc4qR~
        Ppw.close();                                               //~vc4qR~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	private static void closeOutputStream(Uri Puri,OutputStream Pos)//~vc4qR~
    {                                                              //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"closeOutputStream uri="+Puri);//~vc4qI~
        try                                                        //~vc4qI~
        {                                                          //~vc4qI~
            Pos.close();                                            //~vc4qI~
        }                                                          //~vc4qI~
        catch(IOException e)                                       //~vc4qI~
        {                                                          //~vc4qI~
        	Dump.println(e,CN+"closeOutputStream IOException:"+Puri);//~vc4qI~
        }                                                          //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vae0I~//~vc4qI~
	public static int writeDocumentFromFile(String PfpathIn,Uri PuriOut)                //~vae0R~//~vc4qR~
    {                                                              //~vae0I~//~vc4qI~
	    if (Dump.Y) Dump.println(CN+"writeDocumentFromFile in="+PfpathIn+",out="+PuriOut);//~vc4qR~
    	OutputStream os=openOutputDocument(PuriOut);                  //~vc4qR~
        if (os==null)                                              //~vae0I~//~vc4qR~
        {                                                          //~vae0I~//~vc4qI~
		    if (Dump.Y) Dump.println(CN+"writeDocumentFromFile rc=-1 by outputStream=null");//~vae0R~//~vc4qR~
        	return -1;                                             //~vae0R~//~vc4qI~
        }                                                          //~vae0I~//~vc4qI~
        int rc=0;                                                  //~vc4qI~
        try                                                        //~vae0I~//~vc4qI~
        {                                                          //~vae0I~//~vc4qI~
        	FileInputStream fis=new FileInputStream(new File(PfpathIn));//~vae0I~//~vc4qR~
            BufferedInputStream bis=new BufferedInputStream(fis);  //~vc4qI~
            byte[] buff=new byte[BUFFSZ];                            //~vc4qR~
            long offs=0;                                           //~vc4qI~
            while(true)                                        //~vae0R~//~vc4qR~
            {                                                  //~vae0I~//~vc4qR~
                int len=bis.read(buff,0,BUFFSZ);                   //~vc4qR~
                if (Dump.Y) Dump.println(CN+"writeDocumentFromFile offs="+offs+",len="+len);//~vc4qR~
                if (len==-1)                                       //~vc4qR~
                    break;                                     //~vae0I~//~vc4qR~
                offs+=len;                                         //~vc4qI~
	        	os.write(buff,0/*offs*/,len);                       //~vc4qI~
            }                                                  //~vae0R~//~vc4qR~
            closeOutputStream(PuriOut,os);                         //~vc4qI~
            bis.close();                                           //~vc4qR~
        }                                                          //~vae0I~//~vc4qI~
        catch(FileNotFoundException e)                             //~vae0I~//~vc4qI~
        {                                                          //~vae0I~//~vc4qI~
        	Dump.println(e,CN+"writeDocumentFromFile in="+PfpathIn+",out="+PuriOut);    //~vae0I~//~vc4qR~
            rc=-1;                                                 //~vc4qI~
        }                                                          //~vae0I~//~vc4qI~
        catch(IOException e)                                       //~vae0I~//~vc4qI~
        {                                                          //~vae0I~//~vc4qI~
        	Dump.println(e,CN+"writeDocumentFromFile in="+PfpathIn+",out="+PuriOut);    //~vae0I~//~vc4qR~
            rc=-1;                                                 //~vc4qI~
        }                                                          //~vae0I~//~vc4qI~
	    if (Dump.Y) Dump.println(CN+"writeDocumentFromFile exit rc="+rc);//~vae0R~//~vc4qR~
        return rc;                                                 //~vae0I~//~vc4qI~
    }                                                              //~vae0I~//~vc4qI~
//********************************************************         //~vc4qI~
	public static int startPicker(int Popt,String Palias)          //~vc4qR~
	{                                                              //~vc4qI~
        if (Dump.Y) Dump.println(CN+"startPicker opt="+Popt+",alias="+Palias);//~vc4qI~
		openDir(null/*PuriInitial*/,Palias/*picker title*/);       //~vc4qI~
        return 0;                                                 //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	private static String getPath(Uri Puri)                        //~vc4qI~
	{                                                              //~vc4qI~
    	String path="";                                            //~vc4qI~
        if (Dump.Y) Dump.println(CN+"getPath uri="+Puri+",getPath="+Puri.getPath());//~vc4qR~
        if (isFileUri(Puri))                                       //~vc4qI~
        {                                                          //~vc4qI~
        	path=new File(Puri.getPath()).getName();               //~vc4qR~
        }                                                          //~vc4qI~
        else                                                       //~vc4qI~
        if (isContentUri(Puri))                                    //~vc4qI~
        {                                                          //~vc4qI~
        	path=getPathContent(Puri);                             //~vc4qR~
        }                                                          //~vc4qI~
        if (Dump.Y) Dump.println(CN+"getPath path="+path);         //~vc4qI~
        return path;                                               //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	private static String getPathContent(Uri Puri)                 //~vc4qI~
	{                                                              //~vc4qI~
    	String path="";                                            //~vc4qI~
        if (Dump.Y) Dump.println(CN+"uri="+Puri);                  //~vc4qR~
        String[] columns={DocumentsContract.Document.COLUMN_DOCUMENT_ID,//~vc4qI~
                 		  DocumentsContract.Document.COLUMN_DISPLAY_NAME//~vc4qI~
                 		 };                                        //~vc4qI~
        Uri uriQuery=DocumentsContract.buildDocumentUriUsingTree(Puri,DocumentsContract.getTreeDocumentId(Puri));//~vc4qI~
        if (Dump.Y) Dump.println(CN+"uriQuery="+uriQuery);         //~vc4qI~
        Cursor cursor=AG.aUSAF.CR.query(uriQuery,columns,null,null,null);//~vc4qR~
        if (cursor!=null && cursor.moveToFirst())                  //~vc4qI~
        {                                                          //~vc4qI~
            int idxColName=cursor.getColumnIndex(columns[1]); 	//DISPLAY_NAME//~vc4qR~
            int idxColID=cursor.getColumnIndex(columns[0]); 	//DISPLAY_NAME//~vc4qI~
            String displayName=cursor.getString(idxColName);       //~vc4qR~
            String documentID=cursor.getString(idxColID);          //~vc4qR~
            if (Dump.Y) Dump.println(CN+"displayName="+displayName+",DOC_ID="+documentID+",ctrRow="+cursor.getCount());//~vc4qR~
            path=displayName;                                      //~vc4qI~
            cursor.close();                                        //~vc4qI~
        }                                                          //~vc4qI~
		getDocument(AID_OPEN_TREE,Puri);	//TODO test            //~vc4qI~
		getDocument(AID_OPEN_TREE,uriQuery);	//TODO test        //~vc4qI~
        if (Dump.Y) Dump.println(CN+"getPath path="+path);         //~vc4qI~
        return path;                                               //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	private static boolean isContentUri(Uri Puri)                  //~vc4qI~
    {                                                              //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"isContentUri uri="+Puri);     //~vc4qI~
        String scheme=Puri.getScheme();                            //~vc4qI~
        boolean rc=SCHEME_CONTENT.equalsIgnoreCase(scheme);        //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"isContentUri rc="+rc+",scheme="+scheme);//~vc4qI~
        return rc;                                                 //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	private static boolean isFileUri(Uri Puri)                     //~vc4qI~
    {                                                              //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"isFileUri uri="+Puri);        //~vc4qI~
        String scheme=Puri.getScheme();                            //~vc4qI~
        boolean rc=SCHEME_FILE.equalsIgnoreCase(scheme);           //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"isFileUri rc="+rc+",scheme="+scheme);//~vc4qI~
        return rc;                                                 //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	private static DocumentFile getDocument(int PactionID,Uri Puri)//~vc4qI~
    {                                                              //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"getDocument actionID="+PactionID+",uri="+Puri);//~vc4qI~
        DocumentFile doc;                                          //~vc4qI~
        if (PactionID==AID_OPEN_TREE)                              //~vc4qI~
//      	doc=DocumentFile.fromTreeUri(AG.context,Puri);         //~vc4qI~//~vc4sR~
        	doc=getDocumentTree(Puri);                             //~vc4sI~
        else                                                       //~vc4qI~
//      	doc=DocumentFile.fromSingleUri(AG.context,Puri);       //~vc4qI~//~vc4sR~
        	doc=getDocumentSingle(Puri);                           //~vc4sI~
	    if (Dump.Y) Dump.println(CN+"getDocument doc="+doc+",getName="+(doc==null? "null" : doc.getName()/*displayName*/+",uri="+doc.getUri()));//~vc4qR~
//	    if (Dump.Y) Dump.println(CN+"getDocument absPath="+new File(Puri.toString()).getAbsolutePath());//TODO test//~vc4qR~
        return doc;                                                //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	public static int getDirFD(int Popt,String PstrUri)            //~vc4qI~
    {                                                              //~vc4qI~
        Uri uri=Uri.parse(PstrUri);                                //~vc4qI~
		int fd=open(uri,"r");                                      //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"getDirFD fd="+fd+",strURI="+PstrUri+",uri="+uri);//~vc4qI~
        return fd;                                                 //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	public static String udirlistDoc(int Popt,String PdirName,String PstrUri)//~vc4qI~
    {                                                              //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"udirlistDoc level0 dirName="+PdirName+",strUri="+PstrUri);//~vc4qI~
		String rc;                                                 //~vc4uI~
      if (true)                                                    //~vc4uI~
		rc=udirlistDoc2(Popt,PdirName,PstrUri);                    //~vc4uI~
      else                                                         //~vc4uI~
		rc=udirlistDoc(Popt,PdirName,PstrUri,PstrUri,0);//~vc4qI~//~vc4sR~//~vc4uR~
	    if (Dump.Y) Dump.println(CN+"udirlistDoc level0 exit rc="+rc+",dirname="+PdirName);//~vc4qI~
        return rc;                                                 //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4uI~
	public static String getPathMemberData(int Popt,String Pfpath,String PstrUri)//~vc4uI~
    {                                                              //~vc4uI~
        String docID;                                              //~vc4uI~
        DocumentFile doc;                                          //~vc4uI~
	//*****************                                            //~vc4uI~
	    if (Dump.Y) Dump.println(CN+"getPathMemberData Pfpath="+Pfpath);//~vc4uR~
		String path=getPathSubdir(Pfpath);                  //~vc4uI~
        Uri uriPath=makeUriItself(PstrUri,path);                   //~vc4uR~
        AG.aUSAF.uriUdirlist=uriPath;                              //~vc4uI~
		doc=getDocumentSingle(uriPath);                            //~vc4uI~
        if (!isExists(Pfpath,doc))                                  //~vc4uI~
        {                                                          //~vc4uI~
		    if (Dump.Y) Dump.println(CN+"getPathMemberData  ENOENT path="+path);//~vc4uI~
        	AG.aUSAF.errno=ENOENT;                                  //~vc4uI~
        	return null;                                           //~vc4uI~
        }                                                          //~vc4uI~
		if (!isDirectory(Pfpath,doc))                              //~vc4uI~
        {                                                          //~vc4uI~
		    if (Dump.Y) Dump.println(CN+"getPathMemberData  ENOTDIR path="+path);//~vc4uI~
        	AG.aUSAF.errno=ENOTDIR;                                 //~vc4uI~
        	return null;                                           //~vc4uI~
        }                                                          //~vc4uI~
        docID=DocumentsContract.getDocumentId(uriPath);     //~vc4uR~
        String membPath=TreeMember.newTreeMember(TMF_ITSELF|TMF_ENTRY_PATH,Pfpath,doc,docID).toStringMemberData();//~vc4uR~
	    if (Dump.Y) Dump.println(CN+"getPathMemberData membPath="+membPath);//~vc4uI~
        String membParent="";                                      //~vc4uI~
        if (!path.equals(""))	//root                             //~vc4uI~
        {                                                          //~vc4uI~
        	String parent;                                         //~vc4uI~
        	int offs=path.lastIndexOf('/');                        //~vc4uI~
            if (offs>0)                                            //~vc4uI~
                parent=path.substring(0,offs);                     //~vc4uI~
            else                                                   //~vc4uI~
        		parent="";                                         //~vc4uI~
	        Uri uriParent=makeUriItself(PstrUri,parent);           //~vc4uI~
			doc=getDocumentSingle(uriParent);                      //~vc4uI~
	        if (!isExists(Pfpath,doc))                              //~vc4uI~
    	    {                                                      //~vc4uI~
			    if (Dump.Y) Dump.println(CN+"getPathMemberData  ERROR_PATH_NOT_FOUND parent="+parent);//~vc4uI~
        		AG.aUSAF.errno=ERROR_PATH_NOT_FOUND;                //~vc4uI~
        		return null;                                       //~vc4uI~
        	}                                                      //~vc4uI~
                                                                   //~vc4uI~
	        docID=DocumentsContract.getDocumentId(uriParent);      //~vc4uI~
	        membParent=TreeMember.newTreeMember(TMF_ITSELF|TMF_ENTRY_PARENT,Pfpath,doc,docID).toStringMemberData();//~vc4uI~
		    if (Dump.Y) Dump.println(CN+"getPathMemberData membParent="+membParent);//~vc4uI~
        }                                                          //~vc4uI~
	    if (Dump.Y) Dump.println(CN+"getPathMemberData rc="+membPath+membParent);//~vc4uI~
        return membPath+membParent;                            //~vc4uI~
    }                                                              //~vc4uI~
//********************************************************         //~vc4uI~
	private static String getSelection(String Pfpath)                     //~vc4uI~
    {                                                              //~vc4uI~
    	String rc=null;                                            //~vc4uI~
    	int offs=Pfpath.lastIndexOf('/');                          //~vc4uI~
        if (offs>0)                                                //~vc4uI~
        {                                                          //~vc4uI~
        	String memb=Pfpath.substring(offs+1);                  //~vc4uI~
	        rc=glob2regex(memb);                              //~vc4uI~
        }	                                                       //~vc4uI~
	    if (Dump.Y) Dump.println(CN+"getSelection rc="+rc+",fpath="+Pfpath);//~vc4uI~
        return rc;                                                 //~vc4uI~
    }                                                              //~vc4uI~
//********************************************************         //~vc4uI~
	public static String udirlistDoc2(int Popt,String PdirName,String PstrUri)//~vc4uI~
    {                                                              //~vc4uI~
	    if (Dump.Y) Dump.println(CN+"udirlistDoc2 dirName="+PdirName+",strUri="+PstrUri);//~vc4uI~
        String pathData=getPathMemberData(Popt,PdirName,PstrUri);  //~vc4uI~
        if (pathData==null)                                        //~vc4uI~
			return getError(AG.aUSAF.errno);                       //~vc4uI~
        Uri uriPath=AG.aUSAF.uriUdirlist;	//output of	getPathMemberData  //~vc4uI~
	    Uri uriQuery=getQueryUri(PdirName,uriPath);                //~vc4uI~
        String selection=getSelection(PdirName);                   //~vc4uI~
		TreeMember[] members=getTreeMember(PstrUri,uriQuery,selection,null/*childName*/);//~vc4uR~
        int ctr=members==null ? 0 : members.length;                //~vc4uI~
        String rc="";                                              //~vc4uI~
        StringBuffer sb=new StringBuffer((ctr+2)*ABOUT_SIZE_MEMBERDATA);//~vc4uR~
        sb.append(pathData);                                        //~vc4uI~
        for (int ii=0;ii<ctr;ii++)                                 //~vc4uR~
        {                                                          //~vc4uR~
            sb.append(members[ii].toStringMemberData());           //~vc4uR~
        }                                                          //~vc4uR~
        rc=sb.toString();                                          //~vc4uR~
	    if (Dump.Y) Dump.println(CN+"udirlistDoc exit ctr="+ctr+",dirName="+PdirName+",strUri="+PstrUri+",rc="+rc);//~vc4uI~
        return rc;                                                 //~vc4uI~
    }                                                              //~vc4uI~
//********************************************************         //~vc4qI~
	public static String udirlistDoc(int Popt,String PdirName,String PstrUriTop,String PstrUri,int Plevel)//~vc4qR~//~vc4sR~
    {                                                              //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"udirlistDoc level="+Plevel+",dirName="+PdirName+",strUriTop="+PstrUriTop+",strUri="+PstrUri);//~vc4qR~//~vc4sR~
        String parentName=getPathName(PdirName,Plevel);            //~vc4qR~
        String childName=getPathName(PdirName,Plevel+1);           //~vc4qR~
        String grandName=getPathName(PdirName,Plevel+2);           //~vc4qR~
        if (childName==null && grandName!=null)                    //~vc4qI~
        {                                                          //~vc4qI~
			if (Dump.Y) Dump.println(CN+"udirlistDoc Pdirname format err Pdirname="+PdirName);//~vc4qI~
			return getErrorPath();                                 //~vc4qI~
        }                                                          //~vc4qI~
    if (true) //TODO test                                          //~vc4qR~
        if (childName==null && Plevel==0)                      //~vc4qI~
            if ((Popt & C2JUFSO_UFSTAT)!=0)		//0x01:by ufstat   //~vc4qI~
            {                                                      //~vc4qI~
            	String rootStat=ufstatDocRoot(parentName,PstrUri); //~vc4qI~
				if (Dump.Y) Dump.println(CN+"udirlistDoc rootStat="+rootStat);//~vc4qI~
                return rootStat;                                   //~vc4qI~
            }                                                      //~vc4qI~
        Uri uri=getQueryUri(PstrUri,Plevel);                       //~vc4qR~
//      int last=PstrUri.lastIndexOf('/');                         //~vc4qR~
//      uri=Uri.parse(PstrUri.substring(0,last)); //TODO test      //~vc4qR~
//      if (Dump.Y) Dump.println(CN+"udirlistDoc strip wild uri="+uri);//~vc4qR~
                                                                   //~vc4qI~
        if (uri==null)                                             //~vc4qR~
        	return "";                                             //~vc4qR~
//		makeUriPath(PstrUriTop,PdirName);      //TODO test         //~vc4sR~
//      String selection=glob2where(childName);	//not null if childName is wildcard//~vc4qR~
        String selection=glob2regex(childName);                    //~vc4qI~
		TreeMember[] members=getTreeMember(PstrUriTop,uri,selection,childName);//~vc4qR~//~vc4sR~
        int ctr=members==null ? 0 : members.length;                //~vc4qM~
//      if (childName!=null && !childName.equals(MEMBER_ALL)) //.../*//~vc4qR~
//      if (childName!=null && !isWildcard(childName))         //.../*.txt//~vc4qR~
//      if (childName!=null ctr==1)         //.../*.txt            //~vc4qR~
        if (grandName!=null)                                       //~vc4qR~
        {	                                                       //~vc4qI~
			if (Dump.Y) Dump.println(CN+"udirlistDoc grandName=null ctr="+ctr);//~vc4qI~
        	if (ctr!=1)                                            //~vc4qI~
				return getErrorPath();                             //~vc4qI~
            if (!members[0].swDir)                                 //~vc4qI~
				return getErrorPath();                             //~vc4qI~
	        return udirlistDocSubdir(Popt,PdirName,PstrUriTop,uri,Plevel+1,members[0],childName);//~vc4qR~//~vc4sR~
        }                                                          //~vc4qI~
        else                                                       //~vc4qI~
        {                                                          //~vc4qI~
			if (Dump.Y) Dump.println(CN+"udirlistDoc grandName="+grandName+",ctr="+ctr);//~vc4qI~
            if ((Popt & C2JUFSO_UFSTAT)!=0)		//0x01:by ufstat//~vc4qI~
            	if (ctr==0)                                        //~vc4qI~
					return getErrorNotFound();                     //~vc4qI~
        }                                                          //~vc4qI~
        String rc="";                                              //~vc4qR~
        if (ctr==0)                                                //~vc4qI~
			return getErrorEmpty();                                //~vc4qI~
        else                                                       //~vc4qI~
        {                                                          //~vc4qI~
        	StringBuffer sb=new StringBuffer(ctr*ABOUT_SIZE_MEMBERDATA);//~vc4qI~
        	for (int ii=0;ii<ctr;ii++)                             //~vc4qI~
            {                                                      //~vc4qI~
            	sb.append(members[ii].toStringMemberData());       //~vc4qR~
            }                                                      //~vc4qI~
            rc=sb.toString();                                      //~vc4qI~
        }                                                          //~vc4qI~
//      if (members!=null)              //TODO test                //~vc4qR~
//          chkSubTree(uri,members);                               //~vc4qR~
	    if (Dump.Y) Dump.println(CN+"udirlistDoc exit ctr="+ctr+",dirName="+PdirName+",strUri="+PstrUri+",rc="+rc);//~vc4qR~
        return rc;                                                 //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4uI~
    private static Uri getQueryUri(String Pfpath,Uri PuriChild)    //~vc4uI~
    {                                                              //~vc4uI~
//      Uri uri=DocumentsContract.buildChildDocumentsUriUsingTree(PuriChild,DocumentsContract.getTreeDocumentId(PuriChild));//~vc4uR~
       Uri uri=DocumentsContract.buildChildDocumentsUriUsingTree(PuriChild,DocumentsContract.getDocumentId(PuriChild));//~vc4uI~
        if (Dump.Y) Dump.println(CN+"getQueryUri fpath="+Pfpath+",rc="+uri+",PuriChild="+PuriChild);//~vc4uI~
        return uri;                                                //~vc4uI~
    }                                                              //~vc4uI~
//********************************************************         //~vc4qR~
    private static Uri getQueryUri(String PstrUri,int Plevel)      //~vc4qR~
    {                                                              //~vc4qR~
		String strUri=PstrUri;                                     //~vc4qR~
		Uri uri;                                                   //~vc4qI~
        int offs=PstrUri.indexOf(SEP_ARM_SHARE_PATH);   //"%3A"    //~vc4qR~
        if (offs<0)                                                //~vc4qR~
        {                                                          //~vc4qR~
            if (Dump.Y) Dump.println(CN+"getQueryUri return null by no seperator PstrUri="+PstrUri);//~vc4qR~
            return null;                                           //~vc4qR~
        }                                                          //~vc4qR~
        offs+=SEP_ARM_SHARE_PATH_LEN;   //"%3A"                    //~vc4qR~
//        String rc=null;                                          //~vc4qR~
//        int offs2=-1;                                            //~vc4qR~
//        int lvl=0;                                               //~vc4qR~
//        for (;lvl<=Plevel;lvl++)                                 //~vc4qR~
//        {                                                        //~vc4qR~
//            offs2=PstrUri.indexOf('/',offs);                     //~vc4qR~
//            if (offs2<0)                                         //~vc4qR~
//                break;                                           //~vc4qR~
//            if (lvl==Plevel)                                     //~vc4qR~
//                break;                                           //~vc4qR~
//            offs=offs2+1;                                        //~vc4qR~
//        }                                                        //~vc4qR~
//        if (lvl==Plevel)                                         //~vc4qR~
//        {                                                        //~vc4qR~
//            if (offs2<0)                                         //~vc4qR~
//                rc=PstrUri;                                      //~vc4qR~
//            else                                                 //~vc4qR~
//                rc=PstrUri.substring(0,offs2);                   //~vc4qR~
//        }                                                        //~vc4qR~
		if (Plevel==0)                                             //~vc4qI~
        {                                                          //~vc4qI~
            int offs2=PstrUri.indexOf('/',offs);                   //~vc4qI~
            if (offs2<0)                                           //~vc4qI~
            	strUri=PstrUri;                                    //~vc4qR~
            else                                                   //~vc4qI~
            	strUri=PstrUri.substring(0,offs2);                 //~vc4qR~
	        Uri uri0=Uri.parse(strUri);                            //~vc4qI~
        	uri=DocumentsContract.buildChildDocumentsUriUsingTree(uri0,DocumentsContract.getTreeDocumentId(uri0));//~vc4qI~
	        if (Dump.Y) Dump.println(CN+"getQueryUri getTreeDocumentID="+DocumentsContract.getTreeDocumentId(uri0));//~vc4sI~
	        if (Dump.Y) Dump.println(CN+"getQueryUri buildDocumentUriUsingTree="+DocumentsContract.buildDocumentUriUsingTree(uri0,DocumentsContract.getTreeDocumentId(uri0)));//~vc4sI~
        }                                                          //~vc4qI~
        else                                                       //~vc4qI~
	        uri=Uri.parse(strUri);                             //~vc4qI~
        if (Dump.Y) Dump.println(CN+"getQueryUri PstrUri="+PstrUri+",level="+Plevel+",rc=uri="+uri);//~vc4qR~
        return uri;                                                //~vc4qR~
    }                                                              //~vc4qR~
//********************************************************         //~vc4qI~
	public static String udirlistDocSubdir(int Popt,String PdirName,String PstrUriTop,Uri PparentUri,int Plevel,TreeMember Pmember,String PchildName)//~vc4qI~//~vc4sR~
    {                                                              //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"udirlistDocSubdir level="+Plevel+",dirName="+PdirName+",stringYriTop="+PstrUriTop+",parentUri="+PparentUri+",childName="+PchildName+",member="+Pmember);//~vc4qI~//~vc4sR~
    	TreeMember memberChild=Pmember;                            //~vc4qI~
        Uri uriChild=DocumentsContract.buildChildDocumentsUriUsingTree(PparentUri,memberChild.docID);//~vc4qI~
	    if (Dump.Y) Dump.println(CN+"udirlistDocSubdir parentUri="+PparentUri+",childDocid="+memberChild.docID+",uriChild="+uriChild);//~vc4qI~
        String rc=udirlistDoc(Popt,PdirName,PstrUriTop,uriChild.toString(),Plevel);//~vc4qI~//~vc4sR~
	    if (Dump.Y) Dump.println(CN+"udirlistDocSubdir level="+Plevel+",dirName="+PdirName+",rc="+rc);//~vc4qI~
        return rc;                                                 //~vc4qI~
    }                                                              //~vc4qI~
////********************************************************       //~vc4qR~
//    public static String udirlistDocSubdir(int Popt,String PdirName,Uri PparentUri,int Plevel,TreeMember[] Pmembers,String PchildName)//~vc4qR~
//    {                                                            //~vc4qR~
//        if (Dump.Y) Dump.println(CN+"udirlistDocSubdir level="+Plevel+",dirName="+PdirName+",parentUri="+PparentUri+",childName="+PchildName+",members="+Pmembers);//~vc4qR~
//        if (Pmembers==null)                                      //~vc4qR~
//            return "";                                           //~vc4qR~
//        TreeMember memberChild=null;                             //~vc4qR~
//        for (TreeMember memb:Pmembers)                           //~vc4qR~
//        {                                                        //~vc4qR~
//            if (Dump.Y) Dump.println(CN+"udirlistDocSubdir memb.name="+memb.name+",PchildName="+PchildName);//~vc4qR~
////          if (memb.name.equals(PchildName))                    //~vc4qR~
//            if (isPathMatch(memb.name,PchildName))               //~vc4qR~
//            {                                                    //~vc4qR~
//                memberChild=memb;                                //~vc4qR~
//                if (Dump.Y) Dump.println(CN+"udirlistDocSubdir member found member="+memberChild);//~vc4qR~
//                break;                                           //~vc4qR~
//            }                                                    //~vc4qR~
//        }                                                        //~vc4qR~
//        if (memberChild==null)                                   //~vc4qR~
//        {                                                        //~vc4qR~
//            if (Dump.Y) Dump.println(CN+"udirlistDocSubdir member not found childName="+PchildName);//~vc4qR~
//            return "";                                           //~vc4qR~
//        }                                                        //~vc4qR~
//        Uri uriChild=DocumentsContract.buildChildDocumentsUriUsingTree(PparentUri,memberChild.docID);//~vc4qR~
//        if (Dump.Y) Dump.println(CN+"udirlistDocSubdir parentUri="+PparentUri+",childDocid="+memberChild.docID+",uriChild="+uriChild);//~vc4qR~
//        String rc=udirlistDoc(Popt,PdirName,uriChild.toString(),Plevel);//~vc4qR~
//        if (Dump.Y) Dump.println(CN+"udirlistDocSubdir level="+Plevel+",dirName="+PdirName+",rc="+rc);//~vc4qR~
//        return rc;                                               //~vc4qR~
//    }                                                            //~vc4qR~
//********************************************************         //~vc4qI~
	private static TreeMember[] getTreeMember(String PstrUriTop,Uri Puri,String Pselection,String PchildName)//~vc4qR~//~vc4sR~
    {                                                              //~vc4qI~
		if (Dump.Y) Dump.println(CN+"getTreeMember entry selection="+Pselection+",Puri="+Puri);//~vc4qR~
    	int ctrLine=0;                                             //~vc4qI~
        TreeMember[] members=null;                                 //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"getTreeMember uri="+Puri);    //~vc4qI~
        String[] columns=COLOMN_DOCFILE;                           //~vc4qI~
        int[] idxCol=new int[columns.length];                      //~vc4qI~
//      Uri uriQuery=DocumentsContract.buildChildDocumentsUriUsingTree(Puri,DocumentsContract.getTreeDocumentId(Puri));//~vc4qR~
        Uri uriQuery=Puri;                                         //~vc4qI~
        Cursor cursor=AG.aUSAF.CR.query(uriQuery,columns,Pselection,null,null);//~vc4qR~
//      Cursor cursor=AG.aUSAF.CR.query(uriQuery,columns,Pselection,null,null,null/*cancel*/);//~vc4qR~
//      Cursor cursor=AG.aUSAF.CR.query(uriQuery,columns,null,null,null);   //Select dose not work, reason is query method is abstruct?//~vc4qR~
//      String[] parm={"\'A%\'"};       //TODO test                //~vc4qR~
//      String[] parm={"\'AxeSub\'"};                              //~vc4qI~
//      Cursor cursor=AG.aUSAF.CR.query(uriQuery,columns,"_display_name like ?",parm,null);//~vc4qR~
        if (Dump.Y) Dump.println(CN+"getTreeMember uriQuery="+uriQuery+",cursor="+Utils.toString(cursor));//~vc4qI~
        int ctrSelected=0;                                         //~vc4qI~
        if (cursor!=null)                                          //~vc4qR~
        {                                                          //~vc4qI~
            ctrLine=cursor.getCount();                             //~vc4qI~
            if (Dump.Y) Dump.println(CN+"getTreeMember ctrLine="+ctrLine);//~vc4qI~
            if (ctrLine>0 && cursor.moveToFirst())                 //~vc4qI~
            {                                                      //~vc4qI~
                members=new TreeMember[ctrLine];                   //~vc4qI~
                for (int ii=0;ii<columns.length;ii++)              //~vc4qR~
                {                                                  //~vc4qR~
                    idxCol[ii]=cursor.getColumnIndex(columns[ii]); //~vc4qR~
                    if (Dump.Y) Dump.println(CN+"getTreeMember colidx ii="+ii+",col="+columns[ii]+",idx="+idxCol[ii]);//~vc4qR~
                }                                                  //~vc4qR~
                ctrLine=cursor.getCount();                         //~vc4qR~
                members=new TreeMember[ctrLine];                   //~vc4qR~
                if (Dump.Y) Dump.println(CN+"getTreeMember ctrLine="+ctrLine);//~vc4qR~
                for (int ii=0;ii<ctrLine;ii++)                     //~vc4qR~
                {                                                  //~vc4qR~
//                    {   //TODO test                              //~vc4sR~
//                        String uriMemb=cursor.getString(idxCol[COLIDX_ID]);//~vc4sR~
//                        String nm=cursor.getString(idxCol[COLIDX_NAME]);//~vc4sR~
//                        Uri uriPath=DocumentsContract.buildDocumentUriUsingTree(Puri,uriMemb);//~vc4sR~
//                        if (Dump.Y) Dump.println(CN+"getTreeMember memb="+nm+",uriMemb="+uriMemb+",buildDocumentUsingTree for member of Puri(uriQuery)="+uriPath);//~vc4sR~
//                        getDocumentSingle(uriPath);              //~vc4sR~
//                        getDocumentTree(uriPath);                //~vc4sR~
//                    }                                            //~vc4sR~
        			String name=cursor.getString(idxCol[COLIDX_NAME]);//~vc4qI~
                    if (isPathMatch(name,Pselection,PchildName))   //~vc4qR~
                    {                                              //~vc4qI~
    	                members[ctrSelected]=TreeMember.newTreeMember(cursor,idxCol);//~vc4qR~//~vc4sR~
//                      makeUriMember(PstrUriTop,members[ctrSelected].docID);	//TODO test//~vc4sR~
    	                ctrSelected++;                             //~vc4sI~
                    }                                              //~vc4qI~
                    cursor.moveToNext();                           //~vc4qR~
                }                                                  //~vc4qR~
            }                                                      //~vc4qI~
            cursor.close();                                        //~vc4qI~
            if (ctrSelected==0)                                    //~vc4qI~
            	members=null;                                      //~vc4qI~
            else                                                   //~vc4qI~
            if (ctrSelected!=ctrLine)                              //~vc4qI~
            {                                                      //~vc4qI~
            	                                                   //~vc4qI~
                TreeMember[] membersSelected=new TreeMember[ctrSelected];       //~vc4qI~
                System.arraycopy(members,0,membersSelected,0,ctrSelected);//~vc4qI~
                members=membersSelected;                           //~vc4qI~
            }                                                      //~vc4qI~
        }                                                          //~vc4qI~
		if (Dump.Y) Dump.println(CN+"getTreeMember exit ctrLine="+ctrLine+",ctrSelected="+ctrSelected+",members="+Utils.toString(members));//~vc4qR~
        return members;                                            //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	private static String ufstatDocRoot(String PpathRoot,String PstrUri)//~vc4qI~
    {                                                              //~vc4qI~
		if (Dump.Y) Dump.println(CN+"ufstatDocRoot entry PathRoot="+PpathRoot+",Puri="+PstrUri);//~vc4qR~
        TreeMember memb;                                           //~vc4qI~
        boolean swDocUri;                                          //~vc4qI~
        String docID;                                              //~vc4qI~
	    Uri uri0=Uri.parse(PstrUri);                                //~vc4qI~
//      swDocUri=DocumentsContract.isDocumentUri(AG.context,uri0); //~vc4qR~
//  	if (Dump.Y) Dump.println(CN+"ufstatDocRoot swDocUri="+swDocUri);//~vc4qR~
//      if (swDocUri)                                              //~vc4qR~
//  		if (Dump.Y) Dump.println(CN+"ufstatDocRoot uri0 docid="+DocumentsContract.getDocumentId(uri0)+",rootid="+DocumentsContract.getRootId(uri0));//~vc4qR~
//  	if (Dump.Y) Dump.println(CN+"ufstatDocRoot uri0 isRoot="+DocumentsContract.isRootUri(AG.context,uri0)+",isRoots="+DocumentsContract.isRootsUri(AG.context,uri0)+",isTree="+DocumentsContract.isTreeUri(uri0));//~vc4qR~
        Uri uri0Tree=getTreeUri(uri0);                             //~vc4qR~
		if (Dump.Y) Dump.println(CN+"ufstatDocRoot uri0Tree isTree="+DocumentsContract.isTreeUri(uri0Tree));//~vc4qI~
        swDocUri=DocumentsContract.isDocumentUri(AG.context,uri0Tree);//~vc4qI~
		if (Dump.Y) Dump.println(CN+"ufstatDocRoot swDocUri="+swDocUri);//~vc4qI~
        if (swDocUri)                                              //~vc4qI~
        {                                                          //~vc4qI~
    		docID=DocumentsContract.getDocumentId(uri0Tree);      //~vc4qR~
    		if (Dump.Y) Dump.println(CN+"ufstatDocRoot uri0Tree docid="+DocumentsContract.getDocumentId(uri0Tree));//~vc4qI~
        }                                                          //~vc4qI~
        else                                                       //~vc4qI~
    		docID="";                                              //~vc4qI~
//        DocumentFile docuri0=DocumentFile.fromTreeUri(AG.context,uri0);//~vc4qR~
//  	  memb=AG.aUSAF.newTreeMember(docuri0,"docuri0");          //~vc4qR~
//        DocumentFile docuri0Tree=DocumentFile.fromTreeUri(AG.context,uri0Tree);//~vc4qR~//~vc4sR~
          DocumentFile docuri0Tree=getDocumentTree(uri0Tree);      //~vc4sI~
          memb=TreeMember.newTreeMember(TMF_ITSELF,PpathRoot,docuri0Tree,docID);               //~vc4qR~//~vc4tR~
//        DocumentFile docuri0Single=DocumentFile.fromSingleUri(AG.context,uri0);//~vc4qR~
//        AG.aUSAF.newTreeMember(docuri0Single,"docuri0Single");   //~vc4qR~
//        DocumentFile docuri0TreeSingle=DocumentFile.fromSingleUri(AG.context,uri0Tree);//~vc4qR~
//        AG.aUSAF.newTreeMember(docuri0TreeSingle,"docuri0TreeSingle");//~vc4qR~
		if (Dump.Y) Dump.println(CN+"ufstatDocRoot exit member="+memb);//~vc4qI~
        return memb.toStringMemberData();                                               //~vc4qR~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
//*for TEST                                                        //~vc4qI~
//********************************************************         //~vc4qI~
	private static void chkSubTree(Uri PuriParent,TreeMember[] Pmembers)//~vc4qI~
    {                                                              //~vc4qI~
		if (Dump.Y) Dump.println(CN+"chkSubTree uriParent="+PuriParent);//~vc4qI~
    	for (TreeMember memb:Pmembers)                             //~vc4qI~
        {                                                          //~vc4qI~
        	if (memb.swDir)                                        //~vc4qI~
            	getSubTree(PuriParent,memb.docID);                       //~vc4qI~
                                                                   //~vc4qI~
        }                                                          //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	private static void getSubTree(Uri PuriParent,String PdocID)   //~vc4qI~
    {                                                              //~vc4qI~
		if (Dump.Y) Dump.println(CN+"getSubTree docID="+PdocID+",uriParent="+PuriParent);//~vc4qR~
        Uri uriParent=PuriParent;                                  //~vc4qI~
        Uri uriChild=DocumentsContract.buildChildDocumentsUriUsingTree(PuriParent,PdocID);//~vc4qI~
        String[] columns=COLOMN_DOCFILE;
        int[] idxCol=new int[columns.length];                      //~vc4qI~
        //~vc4qI~
        Cursor cursor=AG.aUSAF.CR.query(uriChild,columns,null,null,null);//~vc4qI~
        if (Dump.Y) Dump.println(CN+"getSubTree uriChild="+uriChild+",cursor="+cursor);//~vc4qI~
        if (cursor!=null)                                          //~vc4qI~
        {                                                          //~vc4qI~
            int ctrLine=cursor.getCount();                         //~vc4qM~
		    if (Dump.Y) Dump.println(CN+"getSubTree ctrLine="+ctrLine);//~vc4qR~
            if (ctrLine>0 && cursor.moveToFirst())                 //~vc4qR~
            {                                                      //~vc4qI~
                for (int ii=0;ii<columns.length;ii++)              //~vc4qR~
                {                                                  //~vc4qR~
                    idxCol[ii]=cursor.getColumnIndex(columns[ii]); //~vc4qR~
                    if (Dump.Y) Dump.println(CN+"getSubTree colidx ii="+ii+",col="+columns[ii]+",idx="+idxCol[ii]);//~vc4qR~
                }                                                  //~vc4qR~
                for (int ii=0;ii<ctrLine;ii++)                     //~vc4qR~
                {                                                  //~vc4qR~
                    TreeMember m=TreeMember.newTreeMember(cursor,idxCol);//~vc4qR~
                    if (Dump.Y) Dump.println(CN+"getSubTree member="+m);//~vc4qR~
                    cursor.moveToNext();                           //~vc4qR~
                }                                                  //~vc4qR~
            }                                                      //~vc4qI~
            cursor.close();                                        //~vc4qI~
        }                                                          //~vc4qI~
		if (Dump.Y) Dump.println(CN+"getSubTree exit");            //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	public static String ufstatDoc(int Popt,String PdirName,String PstrUri,int Ppathlen)//~vc4qR~
    {                                                              //~vc4qI~
      if (true)                                                    //~vc4sI~
        return ufstatDoc2(Popt,PdirName,PstrUri,Ppathlen);         //~vc4sI~
    	String dir;                                                //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"ufstatDoc opt="+Popt+",dirName="+PdirName+",strUri="+PstrUri+",pathlen="+Ppathlen);//~vc4qR~
                                                                   //~vc4sI~
        if ((Popt & C2JUFSO_WILDCARD)!=0)                          //~vc4qR~
            dir=PstrUri.substring(0,Ppathlen-1);                   //~vc4qR~
        else                                                       //~vc4qR~
            dir=PstrUri;                                          //~vc4qR~
        String rc="";                                              //~vc4qI~
//     if (false)                                                  //~vc4qR~
//     {                                                           //~vc4qR~
//        Uri uri=Uri.parse(PstrUri);                              //~vc4qR~
//        DocumentFile doc=getDocument(uri);                       //~vc4qR~
//        if (doc!=null)                                           //~vc4qR~
//        {                                                        //~vc4qR~
//            String docID=DocumentsContract.getTreeDocumentId(uri);//~vc4qR~
//            TreeMember membData=AG.aUSAF.newTreeMember(doc,docID);//~vc4qR~
//            rc=membData.toStringMemberData();                    //~vc4qR~
//        }                                                        //~vc4qR~
//     }                                                           //~vc4qR~
//     else  //TODO test                                           //~vc4qR~
//     {                                                           //~vc4qR~
		rc=udirlistDoc(Popt/*C2JUFSO_UFSTAT on*/,PdirName,PstrUri);//~vc4qI~
//     }                                                           //~vc4qR~
	    if (Dump.Y) Dump.println(CN+"ufstatDoc rc="+rc);           //~vc4qR~
        return rc;                                                 //~vc4qR~
    }                                                              //~vc4qI~
//********************************************************         //~vc4tI~
    public static DocumentFile getDocumentItself(int Popt,String PstrUri,String PdirName)//~vc4tR~
    {                                                              //~vc4tI~
        Uri uriItself=makeUriItself(PstrUri,PdirName);             //~vc4tI~
        DocumentFile doc=getDocumentSingle(uriItself);             //~vc4tI~
	    if (Dump.Y) Dump.println(CN+"getDocumentItself fpath="+PdirName+",doc="+doc);//~vc4tI~
        return doc;                                                //~vc4tI~
    }                                                              //~vc4tI~
//********************************************************         //~vc4uI~
    public static DocumentFile getDocumentItselfTree(int Popt,String PstrUri,String PdirName)//~vc4uI~
    {                                                              //~vc4uI~
        Uri uriItself=makeUriItself(PstrUri,PdirName);             //~vc4uI~
//      DocumentFile doc=getDocumentSingle(uriItself); //cause UnsupportedOperationException//~vc4uI~
        DocumentFile doc=getDocumentTree(uriItself);//S            //~vc4uI~
	    if (Dump.Y) Dump.println(CN+"getDocumentItselfTree fpath="+PdirName+",doc="+doc);//~vc4uI~
        return doc;                                                //~vc4uI~
    }                                                              //~vc4uI~
//********************************************************         //~vc4sI~
//* fstat also for wildcard itself                                 //~vc4tI~
//********************************************************         //~vc4tI~
	public static String ufstatDoc2(int Popt,String PdirName,String PstrUri,int Ppathlen)//~vc4sI~//~vc4tR~
    {                                                              //~vc4sI~
        DocumentFile doc;                                          //~vc4tI~
    	TreeMember memb;                                           //~vc4tI~
	    if (Dump.Y) Dump.println(CN+"ufstatDoc2 opt="+Popt+",dirName="+PdirName+",strUri="+PstrUri+",pathlen="+Ppathlen);//~vc4sI~
        DocumentFile docItself=getDocumentItself(Popt,PstrUri,PdirName);//~vc4tI~
    if (true)                                                      //~vc4tI~
    {                                                              //~vc4tI~
      	if (isExists(PdirName,docItself))                          //~vc4tI~
      	{                                                          //~vc4tI~
      		doc=docItself;                                         //~vc4tI~
      	}                                                          //~vc4tI~
      	else                                                       //~vc4tI~
      	{                                                          //~vc4tI~
            if (isPathOK(PdirName,PstrUri))                        //~vc4tI~
	        	return getErrorNotFound();                         //~vc4tI~
            return getErrorPath();                                 //~vc4tR~
      	}                                                          //~vc4tI~
    	memb=TreeMember.newTreeMember(TMF_ITSELF,PdirName,doc,doc.getUri().toString());//~vc4tI~
    }                                                              //~vc4tI~
    else                                                           //~vc4tI~
    {                                                              //~vc4tI~
        boolean swItself=false;                                     //~vc4tI~
      if (isExists(PdirName,docItself))                            //~vc4tI~
      {                                                            //~vc4tI~
      	doc=docItself;	                                           //~vc4tI~
        swItself=true;                                             //~vc4tI~
      }                                                            //~vc4tI~
      else                                                         //~vc4tI~
      {                                                            //~vc4tI~
        Uri uriPath=makeUriPath(PstrUri,PdirName);                 //~vc4sR~
        doc=getDocumentSingle(uriPath);               //~vc4sR~    //~vc4tR~
        if (doc==null)                                             //~vc4sR~
            return getErrorInvalidFormat();                        //~vc4sR~
        if (!isExists(uriPath,doc))                                //~vc4sR~
        {                                                          //~vc4sI~
            if (isPathOK(PdirName,PstrUri))                        //~vc4sR~
                return getErrorNotFound();                         //~vc4sR~
            else                                                   //~vc4sR~
                return getErrorPath();                             //~vc4sR~
        }                                                          //~vc4sI~
      }                                                            //~vc4tI~
    	memb=TreeMember.newTreeMember(TMF_ITSELF,PdirName,doc,doc.getUri().toString());//~vc4sR~//~vc4tR~
    }                                                              //~vc4tI~
        String rc=memb.toStringMemberData();                       //~vc4sR~
	    if (Dump.Y) Dump.println(CN+"ufstatDoc2 dirName="+PdirName+",rc="+rc);//~vc4sI~
        return rc;
    }                                                              //~vc4sI~
//********************************************************         //~vc4sI~
	public static boolean isPathOK(String PdirName,String PstrUri) //~vc4sR~
    {                                                              //~vc4sI~
	    if (Dump.Y) Dump.println(CN+"isPathOK dirName="+PdirName+",strUri="+PstrUri);//~vc4sI~
        boolean rc=true;                                           //~vc4sI~
        for (int ii=0;;ii++)                                       //~vc4sI~
        {                                                          //~vc4sI~
			String parent=getPathParent(PdirName,ii);                 //~vc4sI~
            if (parent==null)                                      //~vc4sI~
            	break;                                             //~vc4sI~
        	Uri uriPath=makeUriPath(PstrUri,parent);               //~vc4sI~
        	DocumentFile doc=getDocumentSingle(uriPath);           //~vc4sI~
        	if (doc==null)                                         //~vc4sI~
            {                                                      //~vc4sI~
            	rc=false;                                          //~vc4sI~
                break;                                             //~vc4sI~
            }                                                      //~vc4sI~
	        if (!isExists(uriPath,doc))                            //~vc4sI~
            {                                                      //~vc4sI~
            	rc=false;                                          //~vc4sI~
                break;                                             //~vc4sI~
            }                                                      //~vc4sI~
        }                                                          //~vc4sI~
	    if (Dump.Y) Dump.println(CN+"isPathOk rc="+rc);            //~vc4sI~
        return rc;                                                 //~vc4sI~
    }                                                              //~vc4sI~
//********************************************************         //~vc4qI~
//*get single uri, if failed try fromTree uri                      //~vc4sI~
//********************************************************         //~vc4sI~
	public static DocumentFile getDocument(Uri Puri)               //~vc4qI~
    {                                                              //~vc4qI~
	    DocumentFile doc=null;                                     //~vc4qI~
        boolean swExist=false,swTree=false;;                       //~vc4qI~
		if (Dump.Y) Dump.println(CN+"org uri getType="+AG.aUSAF.CR.getType(Puri));//~vc4qI~
//	    DocumentFile docFile=DocumentFile.fromSingleUri(AG.context,Puri);//~vc4qR~//~vc4sR~
  	    DocumentFile docFile=getDocumentSingle(Puri);              //~vc4sI~
        if (docFile!=null)                                         //~vc4qI~
        {                                                          //~vc4qI~
		    if (Dump.Y) Dump.println(CN+"docFile name="+docFile.getName()+",isDir="+isDirectory(Puri.toString(),docFile));//~vc4qR~
            swExist=isExists(Puri,docFile);                         //~vc4qI~
            if (swExist)                                           //~vc4qI~
                doc=docFile;                                       //~vc4qI~
        }                                                          //~vc4qI~
        if (!swExist)                                              //~vc4qI~
        {                                                          //~vc4qI~
//      	DocumentFile docDir=DocumentFile.fromTreeUri(AG.context,Puri);//~vc4qR~//~vc4sR~
        	DocumentFile docDir=getDocumentTree(Puri);             //~vc4sI~
        	if (docDir!=null)                                      //~vc4qR~
        	{                                                      //~vc4qR~
		    	if (Dump.Y) Dump.println(CN+"docDir name="+docDir.getName()+",isDir="+isDirectory(Puri.toString(),docDir));//~vc4qR~
	            swExist=isExists(Puri,docDir);                     //~vc4qR~
    	        if (swExist)                                       //~vc4qI~
                	doc=docDir;                                    //~vc4qI~
            }                                                      //~vc4qI~
        }                                                          //~vc4qI~
//        Uri uriTree=getTreeUri(Puri);                            //~vc4qR~
//        if (Dump.Y) Dump.println(CN+"tree uri getType="+AG.aUSAF.CR.getType(uriTree));//~vc4qR~
//        DocumentFile docTreeFile=DocumentFile.fromSingleUri(AG.context,uriTree);//~vc4qR~
//        if (docTreeFile!=null)                                   //~vc4qR~
//        {                                                        //~vc4qR~
//            if (Dump.Y) Dump.println(CN+"docTreeFile name="+docTreeFile.getName()+",uri="+uriTree);//~vc4qR~
//            if (Dump.Y) Dump.println(CN+"docTreeFile isExists="+isExists(uriTree.toString(),docTreeFile));//~vc4qR~
//            if (isDirectory(uriTree,docTreeFile))                //~vc4qR~
//                doc=docTreeFile;                                 //~vc4qR~
//        }                                                        //~vc4qR~
//        DocumentFile docTreeDir=DocumentFile.fromTreeUri(AG.context,uriTree);//~vc4qR~
//        if (docTreeDir!=null)                                    //~vc4qR~
//        {                                                        //~vc4qR~
//            if (Dump.Y) Dump.println(CN+"docTreeDir name="+docTreeDir.getName()+",uri="+uriTree);//~vc4qR~
//            if (Dump.Y) Dump.println(CN+"docTreeDir isExists="+isExists(uriTree.toString(),docTreeDir));//~vc4qR~
//            if (isDirectory(uriTree,docTreeDir))                 //~vc4qR~
//                doc=docTreeDir;                                  //~vc4qR~
//        }                                                        //~vc4qR~
	    if (Dump.Y) Dump.println(CN+"getDocument doc="+doc);             //~vc4qI~
        return doc;                                                //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	public static Uri getTreeUri(Uri Puri)                         //~vc4qR~
    {                                                              //~vc4qI~
        Uri uriTree=DocumentsContract.buildDocumentUriUsingTree(Puri,DocumentsContract.getTreeDocumentId(Puri));//~vc4qR~
	    if (Dump.Y) Dump.println(CN+"getTreeUri uri="+uriTree);    //~vc4qR~
        return uriTree;                                            //~vc4qR~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	public static boolean isExists(Uri Puri,DocumentFile Pdoc)     //~vc4qR~
    {                                                              //~vc4qI~
        boolean rc=Pdoc!=null && Pdoc.exists();                                  //~vc4qI~//~vc4tR~
	    if (Dump.Y) Dump.println(CN+"isExists Uri and Doc rc="+rc+",uri="+Puri+",doc="+Pdoc);  //~vc4qR~//~vc4tR~
        return rc;                                                 //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~v77cI~//~vc4tM~
  	public static boolean isExists(String Pfpath,String PstrUri)          //~v77cI~//~vc4tR~
    {                                                              //~v77cI~//~vc4tM~
		boolean rc=false;                                          //~v77cI~//~vc4tM~
      	Uri uri=makeUriPath(PstrUri,Pfpath);                  //~v77cI~//~vc4tI~
		DocumentFile doc=USAF.getDocument(uri);                    //~v77cI~//~vc4tM~
        if (Dump.Y) Dump.println(CN+"isExists doc="+doc);           //~v77cI~//~vc4tI~
        if (doc!=null)                                             //~v77cI~//~vc4tM~
			rc=USAF.isExists(uri,doc);                             //~v77cR~//~vc4tM~
        if (Dump.Y) Dump.println(CN+"isExists strUri rc="+rc+",fpath="+Pfpath+",strUri="+PstrUri);//~v77cI~//~vc4tR~
        return rc;                                                 //~vc4tM~
    }                                                              //~v77cI~//~vc4tM~
//********************************************************         //~vc4tI~
	public static boolean isExists(String Pfpath,DocumentFile Pdoc)//~vc4tI~
    {                                                              //~vc4tI~
        boolean rc=Pdoc!=null && Pdoc.exists();                    //~vc4tI~
	    if (Dump.Y) Dump.println(CN+"isExists Doc rc="+rc+",fpath="+Pfpath+",doc="+Pdoc);//~vc4tR~
        return rc;                                                 //~vc4tI~
    }                                                              //~vc4tI~
//********************************************************         //~vc4qI~
	public static boolean isDirectory(String Pfnm,DocumentFile Pdoc)//~vc4qR~
    {                                                              //~vc4qI~
        boolean rc=Pdoc!=null && Pdoc.isDirectory();                             //~vc4qI~//~vc4tR~
	    if (Dump.Y) Dump.println(CN+"Doc isDirectory rc="+rc+",fnm="+Pfnm+",Pdoc="+Pdoc);//~vc4qR~//~vc4tR~
        return rc;                                                 //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	public static String getError(int Perrcode)                   //~vc4qI~
    {                                                              //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"getError errcode="+Perrcode);//~vc4qI~//~vc4sR~
        return PREFIX_ERROR+Perrcode;                                //~vc4qI~
    }                                                              //~vc4qI~
	public static String getErrorNotFound()                       //~vc4qI~
    {                                                              //~vc4qI~
        return getError(ENOENT);                                   //~vc4qI~
    }                                                              //~vc4qI~
	public static String getErrorPath()                           //~vc4qI~
    {                                                              //~vc4qI~
        return getError(ERROR_PATH_NOT_FOUND);                     //~vc4qI~
    }                                                              //~vc4qI~
	public static String getErrorInvalidFormat()                   //~vc4qI~
    {                                                              //~vc4qI~
        return getError(EINVAL);                                   //~vc4qI~
    }                                                              //~vc4qI~
	public static String getErrorEmpty()                           //~vc4qI~
    {                                                              //~vc4qI~
        return getError(ERROR_NO_MORE_FILES);                      //~vc4qR~
    }                                                              //~vc4qI~
	public static String getErrorNotDir()                          //~vc4qI~
    {                                                              //~vc4qI~
        return getError(ENOTDIR);                                  //~vc4qI~
    }                                                              //~vc4qI~
	public static String getErrorIsDir()                           //~vc4sI~
    {                                                              //~vc4sI~
        return getError(EISDIR);                                   //~vc4sI~
    }                                                              //~vc4sI~
	public static String getErrorFailed()                          //~vc4sI~
    {                                                              //~vc4sI~
        return getError(EINTR);                                    //~vc4sI~
    }                                                              //~vc4sI~
//********************************************************         //~vc4qI~
    private static boolean isPathMatch(String Pmember,String Ppattern,String PchildName)//~vc4qR~
    {                                                              //~vc4qI~
    	boolean rc=true;                                           //~vc4qR~
    	if (Ppattern!=null)    //wildcard by regex                  //~vc4qR~
        {                                                          //~vc4qI~
//            if (Dump.Y) Dump.println(CN+"isPathMatch TEST rc="+"ABC".matches(Ppattern)+","+"ABC"+",pattern="+Ppattern);//~vc4qR~
//            if (Dump.Y) Dump.println(CN+"isPathMatch TEST rc="+"xABC".matches(Ppattern)+","+"xABC"+",pattern="+Ppattern);//~vc4qR~
//            if (Dump.Y) Dump.println(CN+"isPathMatch TEST rc="+"A".matches(Ppattern)+","+"A"+",pattern="+Ppattern);//~vc4qR~
//            if (Dump.Y) Dump.println(CN+"isPathMatch TEST rc="+"AA".matches(Ppattern)+","+"AA"+",pattern="+Ppattern);//~vc4qR~
            if (Ppattern.length()!=0)	//"*"(MEMBER_ALL) case     //~vc4qI~
	            rc=Pmember.matches(Ppattern);                      //~vc4qR~
        }                                                          //~vc4qI~
        else                                                       //~vc4qI~
        if (PchildName!=null)                                       //~vc4qI~
  			rc=Pmember.equals(PchildName);                         //~vc4qR~
	    if (Dump.Y) Dump.println(CN+"isPathMatch rc="+rc+",member="+Pmember+",pattern="+Ppattern+",PchildName="+PchildName);//~vc4qR~
        return rc;
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
    public static boolean isWildcard(String Pmember)              //~vc4qI~//~vc4uR~
    {                                                              //~vc4qI~
    	boolean rc=(Pmember.indexOf('*')>=0 || Pmember.indexOf('?')>=0);//~vc4qI~
	    if (Dump.Y) Dump.println(CN+"isWildcard rc="+rc+",member="+Pmember);//~vc4qI~
        return rc;                                                 //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
//*return "" for "*"(MEMBER_ALL)                                   //~vc4qI~
//********************************************************         //~vc4qI~
    private static String glob2regex(String Pglob)                 //~vc4qI~
    {                                                              //~vc4qI~
        String rc=null;                                            //~vc4qI~
    	if (Pglob!=null && isWildcard(Pglob))                      //~vc4qR~
        {                                                          //~vc4qI~
        	if (Pglob.equals(MEMBER_ALL))                           //~vc4qI~
            {                                                      //~vc4qI~
            	rc="";	//retutrn isPathMatch alwas true           //~vc4qI~
            }                                                      //~vc4qI~
            else                                                   //~vc4qI~
            {                                                      //~vc4qI~
                StringBuffer sb=new StringBuffer("^");             //~vc4qR~
                for (int ii=0;ii<Pglob.length();ii++)              //~vc4qR~
                {                                                  //~vc4qR~
                    char c=Pglob.charAt(ii);                       //~vc4qR~
                    switch(c)                                      //~vc4qR~
                    {                                              //~vc4qR~
                    case '*': sb.append(".*"); break;              //~vc4qR~
                    case '?': sb.append("."); break;               //~vc4qR~
                    case '.': sb.append("\\."); break;             //~vc4qR~
                    case '\\': sb.append("\\\\"); break;           //~vc4qR~
                    default:  sb.append(c);                        //~vc4qR~
                    }                                              //~vc4qR~
                }                                                  //~vc4qR~
                sb.append('$');                                    //~vc4qR~
                rc=sb.toString();                                  //~vc4qR~
            }                                                      //~vc4qI~
        }                                                          //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"glob2regex rc="+rc+",glob="+Pglob);//~vc4qI~
        return rc;                                                 //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
    private static String glob2where(String Pglob)                 //~vc4qI~
    {                                                              //~vc4qI~
    	String rc=null;                                            //~vc4qI~
//      if (Pglob!=null && isWildcard(Pglob))                      //~vc4qR~
        if (Pglob!=null)                                           //~vc4qI~
        {                                                          //~vc4qI~
        	if (isWildcard(Pglob))                                 //~vc4qI~
            {                                                      //~vc4qI~
        		String like=Pglob.replace('*','%');                //~vc4qR~
        		like=like.replace('?','_');                        //~vc4qR~
            	rc=DocumentsContract.Document.COLUMN_DISPLAY_NAME+" like "+ "\'"+like+"\'";//~vc4qR~
            }                                                      //~vc4qI~
            else                                                   //~vc4qI~
            {                                                      //~vc4qI~
            	rc=DocumentsContract.Document.COLUMN_DISPLAY_NAME+" == '"+ Pglob+ "'";//~vc4qR~
            }                                                      //~vc4qI~
        }                                                          //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"glob2where rc="+rc+",glob="+Pglob);//~vc4qI~
        return rc;
    }//~vc4qI~
//********************************************************         //~vc4qM~
	private static String getPathName(String PdirName,int Plevel)  //~vc4qI~
    {                                                              //~vc4qM~
        String rc=null;                                            //~vc4qM~
        int offs=ALIAS_PREFIX_LEN;                                 //~vc4qM~//~vc4sR~
        int offs2=-1;                                              //~vc4qM~
        int lvl=0;                                                 //~vc4qM~
        for (;lvl<=Plevel;lvl++)                                   //~vc4qM~
        {                                                          //~vc4qM~
        	offs2=PdirName.indexOf('/',offs);                      //~vc4qM~
            if (offs2<0)                                           //~vc4qM~
            	break;                                             //~vc4qM~
            if (lvl==Plevel)                                       //~vc4qM~
                break;                                             //~vc4qM~
        	offs=offs2+1;                                          //~vc4qM~
        }                                                          //~vc4qM~
        if (lvl==Plevel)                                           //~vc4qM~
        {                                                          //~vc4qM~
        	if (offs2<0)                                           //~vc4qM~
            {                                                      //~vc4qI~
	        	rc=PdirName.substring(offs);                       //~vc4qM~
                if (rc.length()==0)                                //~vc4qI~
                    rc=null;                                       //~vc4qI~
            }                                                      //~vc4qI~
            else                                                   //~vc4qM~
            {                                                      //~vc4qI~
            	if (offs2!=offs)                                   //~vc4qI~
		        	rc=PdirName.substring(offs,offs2);             //~vc4qR~
            }                                                      //~vc4qI~
        }                                                          //~vc4qM~
	    if (Dump.Y) Dump.println(CN+"getPathName rc="+rc+",level="+Plevel+",dirName="+PdirName);//~vc4qI~
        return rc;                                                 //~vc4qM~
    }                                                              //~vc4qM~
//********************************************************         //~vc4tI~
//*from 1st subdir with starting "/" if top is "//", else return itself//~vc4uR~
//********************************************************         //~vc4uI~
	private static String getPathItself(String Pfpath)             //~vc4tI~
    {                                                              //~vc4tI~
        String rc="";                                              //~vc4tI~
      if (!Pfpath.startsWith(FULLPATHID))	// start by "//"       //~vc4uR~
       	rc=Pfpath;                                                 //~vc4uI~
      else                                                         //~vc4uI~
      {                                                            //~vc4uI~
        int offs=ALIAS_PREFIX_LEN;                                 //~vc4tI~
        int offs2=Pfpath.indexOf('/',offs);                        //~vc4tI~
        if (offs2>0)                                               //~vc4tI~
	        rc=Pfpath.substring(offs2); //1st child                //~vc4tI~
      }                                                            //~vc4uI~
	    if (Dump.Y) Dump.println(CN+"getPathItself rc="+rc+",fpath="+Pfpath);//~vc4tI~
        return rc;                                                 //~vc4tI~
    }                                                              //~vc4tI~
//********************************************************         //~vc4sI~
//*drop wildcard and rootid, return with starting "/"                                                  //~vc4sI~//~vc4uR~
//*if root return "";                                              //~vc4uI~
//********************************************************         //~vc4sI~
	private static String getPathSubdir(String Pfpath)             //~vc4tR~
    {                                                              //~vc4sI~
        String rc="";                                              //~vc4sR~
        int offs=ALIAS_PREFIX_LEN;                                 //~vc4sI~
        int offs2=Pfpath.indexOf('/',offs);                      //~vc4sI~
        if (offs2>0)                                              //~vc4sI~
        {                                                          //~vc4sI~
	        rc=Pfpath.substring(offs2); //1st child                //~vc4sR~
        	int offs3=rc.lastIndexOf('/');                      //~vc4sI~
            String lastName;                                       //~vc4sI~
            if (offs3>0)                                           //~vc4sI~
            {                                                      //~vc4sI~
            	lastName=rc.substring(offs3);                      //~vc4sR~
				if (isWildcard(lastName))                          //~vc4sI~
    	            rc=rc.substring(0,offs3); //drop last wildcard//~vc4sI~//~vc4tR~
            }                                                      //~vc4sI~
            else                                                   //~vc4sI~
            {                                                      //~vc4sI~
                lastName = rc;                                       //~vc4sI~
                if (isWildcard(lastName))                          //~vc4sR~
                    rc = "";                                         //~vc4sR~
            }                                                      //~vc4sI~
        }                                                          //~vc4sI~
	    if (Dump.Y) Dump.println(CN+"getPathSubdir rc="+rc+",fpath="+Pfpath);//~vc4sI~//~vc4tR~
        return rc;                                                 //~vc4sI~
    }                                                              //~vc4sI~
//********************************************************         //~vc4sI~
//*get parent dir path of the level, or last if Plevel=-1          //~vc4sR~
//len dose not include last "/"                                    //~vc4sI~
//********************************************************         //~vc4sI~
	public static String getPathParent(String Pfpath,int Plevel)   //~vc4sR~
    {                                                              //~vc4sI~
        String rc=null;                                            //~vc4sI~
        int offs=ALIAS_PREFIX_LEN;                                 //~vc4sI~
        int offs0=offs;                                            //~vc4sI~
        int pathlen=0;                                             //~vc4sI~
        int lvl=0;                                                 //~vc4sI~
        for (;;)                                                   //~vc4sI~
        {                                                          //~vc4sI~
        	int offs2=Pfpath.indexOf('/',offs);                        //~vc4sI~
            if (offs2<0)                                           //~vc4sR~
            {                                                      //~vc4sI~
            	if (Plevel==-1)	//return last parent               //~vc4sI~
                {                                                  //~vc4sI~
                	if (offs>offs0)                                //~vc4sI~
	                    pathlen=offs-1;                            //~vc4sR~
                }                                                  //~vc4sI~
            	break;                                             //~vc4sI~
            }                                                      //~vc4sI~
            if (lvl==Plevel)                                       //~vc4sI~
            {                                                      //~vc4sI~
            	pathlen=offs2;                                     //~vc4sI~
            	break;                                             //~vc4sI~
            }                                                      //~vc4sI~
            lvl++;                                                 //~vc4sI~
            offs=offs2+1;                                          //~vc4sI~
        }                                                          //~vc4sI~
        if (pathlen>0)                                             //~vc4sI~
	        rc=Pfpath.substring(0,pathlen);                          //~vc4sI~
	    if (Dump.Y) Dump.println(CN+"getPathParent rc="+rc+",pathlen="+pathlen+",fpath="+Pfpath+",level="+Plevel);//~vc4sR~
        return rc;                                                 //~vc4sI~
    }                                                              //~vc4sI~
//********************************************************         //~vc4sI~
	public static String getPathNameLast(String Pfpath)            //~vc4sI~
    {                                                              //~vc4sI~
        int offs=ALIAS_PREFIX_LEN;		//starting "//" of alias   //~vc4sI~
        for (;;)                                                   //~vc4sI~
        {                                                          //~vc4sI~
        	int offs2=Pfpath.indexOf('/',offs);                    //~vc4sI~
            if (offs2<0)                                           //~vc4sI~
            	break;                                             //~vc4sI~
        	offs=offs2+1;                                          //~vc4sI~
        }                                                          //~vc4sI~
        String rc=Pfpath.substring(offs);                                 //~vc4sI~
	    if (Dump.Y) Dump.println(CN+"getPathNameLast rc="+rc+",fpath="+Pfpath);//~vc4sI~
        return rc;                                                 //~vc4sI~
    }                                                              //~vc4sI~
//********************************************************         //~vc4qR~//~vc4sM~
	public static DocumentFile getDocumentSingle(Uri Puri)              //~vc4qR~//~vc4sI~
    {                                                              //~vc4qR~//~vc4sM~
        DocumentFile doc=DocumentFile.fromSingleUri(AG.context,Puri);//~vc4qR~//~vc4sM~
        if (Dump.Y) Dump.println(CN+"getDocumentSingle uri="+Puri+",doc="+doc+",isExist="+isExists(Puri,doc)+",isDir="+doc.isDirectory()+",docUri="+doc.getUri());//~vc4sR~//~vc4tR~
        return doc;                                                //~vc4qR~//~vc4sM~
    }                                                              //~vc4qR~//~vc4sM~
//********************************************************         //~vc4qI~//~vc4sM~
	public static DocumentFile getDocumentTree(Uri Puri)                //~vc4qI~//~vc4sI~
    {                                                              //~vc4qI~//~vc4sM~
        DocumentFile doc=DocumentFile.fromTreeUri(AG.context,Puri);//~vc4qI~//~vc4sM~
        if (Dump.Y) Dump.println(CN+"getDocumentTree uri="+Puri+",doc="+doc+",isExist="+isExists(Puri,doc)+",isDir="+doc.isDirectory()+",docUri="+doc.getUri());//~vc4sR~
        return doc;                                                //~vc4qI~//~vc4sM~
    }                                                              //~vc4qI~//~vc4sM~
//********************************************************         //~vc4sM~
//*make descendant member uri from top uri and fullpath            //~vc4sI~
//********************************************************         //~vc4sI~
    public static Uri makeUriPath(String PstrUriTop,String Pfpath) //~vc4sR~
    {                                                              //~vc4sM~
        Uri uriTop=Uri.parse(PstrUriTop);                          //~vc4sI~
		String topDocID=DocumentsContract.getTreeDocumentId(uriTop);//~vc4sM~
        String pathID=topDocID+getPathSubdir(Pfpath);	//drop wildcard part//~vc4sR~
        Uri uriPath=DocumentsContract.buildDocumentUriUsingTree(uriTop,pathID);//~vc4sM~
        if (Dump.Y) Dump.println(CN+"makeUriPath exit strUriTop="+PstrUriTop+",fpath="+Pfpath+",pathID="+pathID+",uriPath="+uriPath);//~vc4sR~
//		getDocumentSingle(uriPath); //TODO test                    //~vc4sR~
//		getDocumentTree(uriPath);   //TODO test                    //~vc4sR~
        return uriPath;                                            //~vc4sM~
    }                                                              //~vc4sM~
//********************************************************         //~vc4tI~
//*make descendant member uri from top uri and fullpath            //~vc4tI~
//********************************************************         //~vc4tI~
    public static Uri makeUriItself(String PstrUriTop,String Pfpath)//~vc4tI~
    {                                                              //~vc4tI~
        Uri uriTop=Uri.parse(PstrUriTop);                          //~vc4tI~
		String topDocID=DocumentsContract.getTreeDocumentId(uriTop);//~vc4tI~
        String pathID=topDocID+getPathItself(Pfpath);              //~vc4tR~
        Uri uriPath=DocumentsContract.buildDocumentUriUsingTree(uriTop,pathID);//~vc4tI~
        if (Dump.Y) Dump.println(CN+"makeUriItself exit strUriTop="+PstrUriTop+",fpath="+Pfpath+",pathID="+pathID+",uriItself="+uriPath);//~vc4tR~
        return uriPath;                                            //~vc4tI~
    }                                                              //~vc4tI~
//********************************************************         //~vc4sI~
    public static Uri makeUriMember(String PstrUriTop,String PdocID)//~vc4sR~
    {                                                              //~vc4sI~
        Uri uriTop=Uri.parse(PstrUriTop);                          //~vc4sI~
		String topDocID=DocumentsContract.getTreeDocumentId(uriTop);//~vc4sI~
        String pathID=PdocID;                                      //~vc4sR~
        Uri uriMember=DocumentsContract.buildDocumentUriUsingTree(uriTop,pathID);//~vc4sI~
        if (Dump.Y) Dump.println(CN+"makeUriMember strUriTop="+PstrUriTop+",docid="+PdocID+",uriMember="+uriMember);//~vc4sI~
//		getDocumentSingle(uriMember); //TODO test                  //~vc4sR~
//		getDocumentTree(uriMember);   //TODO test                  //~vc4sR~
        return uriMember;                                            //~vc4sI~
    }                                                              //~vc4sI~
//********************************************************         //~vc4xI~
    public void chkStorageVolume(Uri Puri)                         //~vc4xI~
    {                                                              //~vc4xI~
        if (Dump.Y) Dump.println(CN+"chkStorageVolume version="+AxeG.osVersion+",uri="+Puri);//~vc4xI~
//        if (AxeG.osVersion>=AxeG.API29)  //>=android10(Q)=api-29 //~vay5R~//~vc4pR~//~vc4xR~
//        {                                                        //~vc4xR~
//            try                                                  //~vc4xR~
//            {                                                    //~vc4xR~
//==>Missing volumename in URI                                     //~vc4xI~
//                StorageVolume vol=SM.getStorageVolume(Puri);     //~vc4xR~
//                showVol(vol);                                    //~vc4xR~
//            }                                                    //~vc4xR~
//            catch(Exception e)                                   //~vc4xR~
//            {                                                    //~vc4xR~
//                Dump.printlnNoMsg(e,"getStorageVolume");         //~vc4xR~
//            }                                                    //~vc4xR~
//        }                                                        //~vc4xR~
	    chkStorageVolume();                                        //~vc4xI~
	}                                                              //~vc4xI~
    public void chkStorageVolume()                                 //~vc4xI~
    {                                                              //~vc4xI~
      try                                                          //~vc4xI~
      {                                                            //~vc4xI~
		if (AxeG.osVersion>=24)  //android7.0(N)                   //~vc4xI~
        {                                                          //~vc4xI~
        	List<StorageVolume> volList=SM.getStorageVolumes();       //~vc4xI~
	        if (Dump.Y) Dump.println(CN+"chkStorageVolume api>=24 ctr List="+volList.size());//~vc4xI~
            for (StorageVolume v:volList)                          //~vc4xI~
            {                                                      //~vc4xI~
            	showVol(v);                                        //~vc4xI~
            }                                                      //~vc4xI~
        }                                                          //~vc4xI~
      }                                                            //~vc4xI~
      catch(Exception e)                                           //~vc4xI~
      {                                                            //~vc4xI~
      	Dump.printlnNoMsg(e,"getStorageVolume");                   //~vc4xI~
      }                                                            //~vc4xI~
    }                                                              //~vc4xI~
//********************************************************         //~vc4xI~
//02:205245.735:USAF.showVol vol=StorageVolume: 内部共有ストレージ //~vc4xI~
//02:205245.736:USAF.showVol api>=24 description=内部共有ストレージ//~vc4xI~
//02:205245.738:USAF.showVol api>=30 directory getName=0,File=/storage/emulated/0//~vc4xI~
//02:205245.741:USAF.showVol directory getCanoricalPath=/storage/emulated/0//~vc4xI~
//02:205245.743:USAF.showVol directory getCanoricalFile=/storage/emulated/0//~vc4xI~
//02:205245.744:USAF.showVol directory getAbsolutePath=/storage/emulated/0//~vc4xI~
//02:205245.745:USAF.showVol directory getAbsoluteFile=/storage/emulated/0//~vc4xI~
    public void showVol(StorageVolume Pvol)                        //~vc4xI~
    {                                                              //~vc4xI~
        if (Dump.Y) Dump.println(CN+"showVol vol="+Pvol);          //~vc4xI~
		if (AxeG.osVersion>=24)  //android 7.0(N) , for getDescription//~vc4xI~
        {                                                          //~vc4xI~
	        if (Dump.Y) Dump.println(CN+"showVol api>=24 description="+Pvol.getDescription(AG.context));//~vc4xI~
        }                                                          //~vc4xI~
		if (AxeG.osVersion>=30)  //android 11.0(R) , for getDirectory//~vc4xI~
        {                                                          //~vc4xI~
        	File f=Pvol.getDirectory();                            //~vc4xI~
	        if (Dump.Y) Dump.println(CN+"showVol api>=30 directory getName="+f.getName()+",File="+f);//~vc4xR~
            try                                                    //~vc4xI~
            {                                                      //~vc4xI~
	        if (Dump.Y) Dump.println(CN+"showVol directory getCanoricalPath="+f.getCanonicalPath());//~vc4xI~
	        if (Dump.Y) Dump.println(CN+"showVol directory getCanoricalFile="+f.getCanonicalFile());//~vc4xI~
            }                                                      //~vc4xI~
            catch(IOException e)                                   //~vc4xI~
            {                                                      //~vc4xI~
            	Dump.println(e,"getCanonicalPath");                //~vc4xI~
            }                                                      //~vc4xI~
           try                                                     //~vc52R~
           {                                                       //~vc52R~
	        if (Dump.Y) Dump.println(CN+"showVol directory getAbsolutePath="+f.getAbsolutePath());//~vc4xI~
	        if (Dump.Y) Dump.println(CN+"showVol directory getAbsoluteFile="+f.getAbsoluteFile());//~vc4xI~
            Gxeh.SharedStorageAbsPath=f.getAbsolutePath();          //~vc52R~
            Gxeh.SharedStorageVolume=Pvol.getDescription(AG.context); //~vc52R~
            String[] memberList=f.list();                          //~vc52R~
	        if (Dump.Y) Dump.println(CN+"showVol directory list="+Utils.toString(memberList));//~vc4xI~
//            File fAxe=new File(f.getAbsoluteFile()+"/Axe");      //~vc4xR~
//            String[] memberAxe=fAxe.list();                      //~vc4xR~
//            if (Dump.Y) Dump.println(CN+"showVol directory Axe list="+Utils.toString(memberAxe));//~vc4xR~
           }                                                       //~vc52R~
           catch(Exception e)                                    //~vc52R~
           {                                                       //~vc52R~
            Dump.printlnNoMsg(e,"getAbolutePath");                 //~vc52R~
           }                                                       //~vc52R~
        }                                                          //~vc4xI~
    }                                                              //~vc4xI~
//********************************************************         //~v77wI~
//* api30:Android11-R                                              //~vc52I~
//********************************************************         //~vc52I~
    public int getDocPath(int Popt,String Pfpath,String PstrUri)   //~v77wI~
    {                                                              //~v77wI~
        if (Dump.Y) Dump.println(CN+"getDocPath osVesrion="+AxeG.osVersion+",fpath="+Pfpath+",strUri="+PstrUri);//~v77wI~
        String path=null;                                          //~v77wI~
        int rc=0;                                                  //~v77wI~
		if (AxeG.osVersion<30)  //android 11.0(R) , for getDirectory//~v77wI~
        {                                                          //~v77wI~
            if (true)                                              //~vc5rI~
            {                                                      //~vc5rI~
    			path=getDocPath29(Popt,Pfpath,PstrUri);             //~vc5rI~
                if (path!=null)                                    //~vc5rI~
                {                                                  //~vc5rI~
		    		AxeJNI.notify_getDocPathResult(Popt|ROPT_REALPATH29,path);//~vc5rI~
			        if (Dump.Y) Dump.println(CN+"getDocPath osVesrion="+AxeG.osVersion+",path="+path);//~vc5rI~
                    return 0;                                      //~vc5rI~
                }                                                  //~vc5rI~
            }                                                      //~vc5rI~
        	return EINTR;                                          //~v77wI~
        }                                                          //~v77wI~
        int pos=Pfpath.indexOf('/',ALIAS_PREFIX_LEN);              //~v77wR~
        String top;                                                //~vc4xR~
        if (pos>0)                                                 //~vc4xI~
        	top=Pfpath.substring(ALIAS_PREFIX_LEN,pos);            //~vc4xI~
        else                                                       //~vc4xI~
        	top=Pfpath.substring(ALIAS_PREFIX_LEN);                //~vc4xR~
		path=mapDocPath.get(top);                                  //~v77wI~
        if (path!=null)                                            //~v77wI~
        {                                                          //~v77wI~
    		AxeJNI.notify_getDocPathResult(Popt,path);             //~v77wI~
	    	if (Dump.Y) Dump.println(CN+"getDocPath return rc=0 by map top="+top+",path="+path);//~v77wI~
			return 0;                                              //~v77wI~
        }                                                          //~v77wI~
//  	if (AxeG.osVersion>=24)  //android7.0(N)                   //~v77wI~
//      {                                                          //~v77wI~
        	List<StorageVolume> volList=SM.getStorageVolumes();    //~v77wI~
	        if (Dump.Y) Dump.println(CN+"getDocPath api>=24 ctr List="+volList.size());//~v77wR~
            for (StorageVolume v:volList)                          //~v77wI~
            {                                                      //~v77wI~
            	path=getPath(v,top);                        //~v77wI~
        		if (path!=null)                                    //~v77wI~
                    break;                                         //~v77wI~
            }                                                      //~v77wI~
//      }                                                          //~v77wI~
		if (path==null)                                            //~v77wI~
        	rc=ENOENT;                                             //~v77wI~
        else                                                       //~v77wI~
        {                                                          //~v77wI~
		    mapDocPath.put(top,path);                              //~v77wI~
		    if (Dump.Y) Dump.println(CN+"getDocPath put map top="+top+",path="+path);//~v77wI~
    		AxeJNI.notify_getDocPathResult(Popt,path);             //~v77wI~
        }                                                          //~v77wI~
	    if (Dump.Y) Dump.println(CN+"getDocPath exit path="+path+",rc="+rc);//~v77wI~
		return rc;                                               //~v77wI~
	}                                                              //~v77wI~
//********************************************************         //~vc5rI~
//* api30:Android11-R                                              //~vc5rI~
//********************************************************         //~vc5rI~
    public String getDocPath29(int Popt,String Pfpath,String PstrUri)//~vc5rI~
    {                                                              //~vc5rI~
        if (Dump.Y) Dump.println(CN+"getDocPath29 osVesrion="+AxeG.osVersion+",fpath="+Pfpath+",strUri="+PstrUri);//~vc5rI~
        String path=null;                                          //~vc5rI~
		String strUri=PstrUri;                                     //~vc5rI~
        int offs=PstrUri.indexOf(SEP_ARM_SHARE_PATH);   //"%3A"    //~vc5rI~
        if (offs<0)                                                //~vc5rI~
        {                                                          //~vc5rI~
            if (Dump.Y) Dump.println(CN+"getDocPath29 return null by no seperator PstrUri="+PstrUri);//~vc5rI~
            return null;                                           //~vc5rI~
        }                                                          //~vc5rI~
        String subd=strUri.substring(offs+SEP_ARM_SHARE_PATH_LEN);   //after "%3A"//~vc5rI~
        subd.replace(SEP_MEMBER,"/");                              //~vc5rI~
        path=PREFIX_SDCARD+"/"+subd;                               //~vc5rI~
	    if (Dump.Y) Dump.println(CN+"getDocPath29 exit path="+path);//~vc5rI~
		return path;                                               //~vc5rI~
	}                                                              //~vc5rI~
//****************************************                         //~v77wI~
    public String getPath(StorageVolume Pvol,String PtopDir)       //~v77wI~
    {                                                              //~v77wI~
        if (Dump.Y) Dump.println(CN+"getPath vol="+Pvol);          //~v77wI~
        File f=Pvol.getDirectory();                                //~v77wI~
	    if (Dump.Y) Dump.println(CN+"getPath getDirectory File="+f);//~v77wI~
        if (f==null)                                               //~v77wI~
        	return null;                                           //~v77wI~
        String parent=f.getAbsolutePath();                         //~v77wR~
	    if (Dump.Y) Dump.println(CN+"getPath parent="+parent);     //~v77wI~
        String[] memberList=f.list();                              //~v77wI~
	    if (Dump.Y) Dump.println(CN+"getPath directory list="+Utils.toString(memberList));//~v77wI~
        String path=null;                                           //~v77wI~
        for (String memb:memberList)                               //~v77wI~
        {                                                          //~v77wI~
	    	if (Dump.Y) Dump.println(CN+"getPath memb="+memb+",top="+PtopDir);//~v77wI~
        	if (memb.equals(PtopDir))                              //~v77wR~
            {                                                      //~v77wI~
	            path=f.getAbsoluteFile()+"/"+memb;                //~v77wI~
            	break;                                             //~v77wI~
            }                                                      //~v77wI~
        }                                                          //~v77wI~
        return path;                                               //~v77wI~
    }                                                              //~v77wI~
//****************************************                         //~vc4yI~
    public static boolean isAllFileAccess()                        //~vc4yR~
    {                                                              //~vc4yI~
		boolean swAllFile=Environment.isExternalStorageManager();  //~vc4yI~
        if (Dump.Y) Dump.println(CN+"isAllFileAccess="+ swAllFile);//~vc4yI~
        return swAllFile;                                          //~vc4yI~
    }                                                              //~vc4yI~
//****************************************                         //~v77yI~
    private void chkPermission()                                   //~v77yI~//~vc4yR~
    {                                                              //~v77yI~
        if (Dump.Y) Dump.println(CN+"chkPermission osVersion="+AxeG.osVersion);//~vc4yR~
        try                                                        //~v77yI~
        {                                                          //~v77yI~
            if (Dump.Y) Dump.println(CN+"Write External="+UView.isPermissionGrantedExternalStorage());//~v77yR~
            if (Dump.Y) Dump.println(CN+"Read External="+UView.isPermissionGranted(Manifest.permission.READ_EXTERNAL_STORAGE));//~v77yR~
            chkUriPermission();                                    //~v77yR~
            if (AxeG.osVersion>=30)  //>=android11(Q)=api-30       //~v77yI~//~vc4yR~
            {                                                      //~vc4yI~
//				swPermissionAllFilesCurrent=isAllFileAccess();     //~vc4yR~
  				if (!isAllFileAccess())                            //~vc4yI~
                {                                                  //~vc4xI~
                  	if (!AxeG.isNoAllFile())  //not set preference:No-Permission//~vc4yI~
                    {                                              //~vc4xI~
                    	swOpenDlgAllFiles=true;                    //~vc4yI~
                    }                                              //~vc4xI~
                }                                                  //~vc4xI~
                else                                               //~vc4xI~
			        Gxeh.axeStatus|=AXES_GRANT_ALLFILES;//                      =0x40; // for >=api30 access allfiles//~vc4xI~
            }                                                      //~vc4yI~
        }                                                          //~v77yI~
        catch(Exception e)                                         //~v77yI~
        {                                                          //~v77yI~
        	Dump.println(e,CN+"chkPermission");                    //~v77yI~
        }                                                          //~v77yI~
        if (Dump.Y) Dump.println(CN+"chkPermission swOpenDlgAllFiles="+swOpenDlgAllFiles);//~vc4yI~
    }                                                              //~v77yI~
//****************************************                         //~v77yI~
    private void chkUriPermission()                                //~v77yR~
    {                                                              //~v77yI~
        List<UriPermission> list=CR.getPersistedUriPermissions();  //~v77yR~
        for (UriPermission p:list)                                 //~v77yI~
        {                                                          //~v77yI~
	        if (Dump.Y) Dump.println(CN+"chkUriPermission uriPermission="+p);//~v77yR~
        }                                                          //~v77yI~
    }                                                              //~v77yI~
//****************************************                         //~vc4yI~
//*from axe                                                        //~vc4yI~
//****************************************                         //~vc4yI~
    public void chkPermissionAllFiles()                            //~vc4yI~
    {                                                              //~vc4yI~
        if (Dump.Y) Dump.println(CN+"chkPermissionAllFiles osVersion="+AxeG.osVersion+",swOpenDlgAllFiles="+swOpenDlgAllFiles);//~vc4yR~
        try                                                        //~vc4yI~
        {                                                          //~vc4yI~
//            if (AxeG.osVersion>=30)  //>=android11(Q)=api-30     //~vc4yR~
//            {                                                    //~vc4yR~
//                if (!swCheckedPermissionAllFiles)                //~vc4yR~
//                {                                                //~vc4yR~
//                    swCheckedPermissionAllFiles=true;            //~vc4yR~
//                    boolean swAllFile=isAllFileAccess();         //~vc4yR~
//                    if (!swAllFile)                              //~vc4yR~
//                        if (!AxeG.isNoAllFile())  //no dialog popup//~vc4yR~
//                            showDialogAllFiles();                //~vc4yR~
//                }                                                //~vc4yR~
//            }                                                    //~vc4yR~
            if (swOpenDlgAllFiles)	//no permission at constructor //~vc4yI~
            {                                                      //~vc4yI~
	            swOpenDlgAllFiles=false;	//once at strat        //~vc4yI~
				showDialogAllFiles();                              //~vc4yI~
            }                                                      //~vc4yI~
        }                                                          //~vc4yI~
        catch(Exception e)                                         //~vc4yI~
        {                                                          //~vc4yI~
        	Dump.println(e,CN+"chkPermissionAllFiles");            //~vc4yI~
        }                                                          //~vc4yI~
    }                                                              //~vc4yI~
//****************************************                         //~vc4yI~
    private void showDialogAllFiles()                              //~vc4yR~
    {                                                              //~vc4yI~
        if (Dump.Y) Dump.println(CN+"showDialogAllFiles");         //~vc4yI~
//		AxeDlgAllFiles.showDialog();                                    //~1821I~//~vc4yR~
  		AxeDlgAllFilesAlert.showDialog();                          //~vc4yI~
    }                                                              //~vc4yI~
//****************************************                         //~v77yI~
    private void requestManageAllFile()                            //~v77yI~
    {                                                              //~v77yI~
        if (Dump.Y) Dump.println(CN+"requestManageAllFile");       //~v77yI~
    	String action= Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION;//~v77yI~
        Uri uri=Uri.parse("package:"+ BuildConfig.APPLICATION_ID);  //~v77yI~
        if (Dump.Y) Dump.println(CN+"requestManageAllFile uri="+uri);//~v77yI~
    	Intent intent=new Intent(action,uri);//~v77yI~
        AxeG.activity.startActivityForResult(intent,AID_MANAGE_ALLFILES);//~v77yI~
        if (Dump.Y) Dump.println(CN+"requestManageAllFile exit");  //~v77yI~
    }                                                              //~v77yI~
//***************************************************************************//~v77yI~
	public static void onActivityResultManageAllFiles(int Presult,Intent Pintent)//~v77yI~
	{                                                              //~v77yI~
        if (Dump.Y) Dump.println(CN+"onActivityResultManageAllFiles result="+Presult+",RESULT_OK="+Activity.RESULT_OK+",intent="+Pintent);//~v77yI~
//        int rc=-1;                                               //~v77yR~
//        if (Pintent!=null)                                       //~v77yR~
//        {                                                        //~v77yR~
//            Uri uri=Pintent.getData();                           //~v77yR~
//            if (Presult!= Activity.RESULT_OK)                    //~v77yR~
//                Utils.showToastLong(R.string.Err_ManageAllFiles);//~v77yR~
//            if (Dump.Y) Dump.println("USAF.onActivityResult uri="+uri);//~v77yR~
//        }                                                        //~v77yR~
//        boolean swAllFile=isAllFileAccess();                     //~vc4yR~
//        if (swAllFile!=AG.aUSAF.swPermissionAllFilesCurrent)    //changed//~vc4yR~
//        {                                                        //~vc4yR~
//            AG.aUSAF.swPermissionAllFilesCurrent=swAllFile;      //~vc4yR~
//            AG.aUSAF.swCheckedPermissionAllFiles=true;  //avoid AxeDlgAllFiles dialog popup//~vc4yR~
//        }                                                        //~vc4yR~
//        if (swAllFile)                                           //~vc4yR~
//            AxeG.setNoAllFile(false);   //popup at start if changed to off next time//~vc4yR~
    	AG.aUSAF.swOpenDlgAllFiles=false;	//if changed app will restart and open dlg//~vc4yI~
        boolean swAllFile=isAllFileAccess();                       //~vc4yI~
        Utils.showToastLong(Utils.getStr(R.string.Info_ChangedAllFiles,(swAllFile?"ON":"OFF")));//~vc4yI~
        if (swAllFile)                                            //~vc4xR~
        	Gxeh.axeStatus|=AXES_GRANT_ALLFILES;//                      =0x40; // for >=api30 access allfiles//~vc4xI~
        else                                                       //~vc4xI~
        	Gxeh.axeStatus&=~AXES_GRANT_ALLFILES;//                      =0x40; // for >=api30 access allfiles//~vc4xI~
    	AxeJNI.notifyOptionChangedOtherPermission();               //~vc4xI~
        if (Dump.Y) Dump.println(CN+"onActivityResultManageAllFiles isAllFileAccess="+swAllFile);//~vc4yR~//~vc4xR~
    }                                                              //~v77yI~
//***************************************************************************//~vc4yI~
//*from AxeDlgArmOption by change btn                              //~vc4yI~
//***************************************************************************//~vc4yI~
	public void onClickChangeAllFiles()                            //~vc4yI~
	{                                                              //~vc4yI~
//        swCheckedPermissionAllFiles=true;   //avoid AxeDlgAllFiles dialog popup//~vc4yR~
        if (Dump.Y) Dump.println(CN+"onClickChangeAllFiles isAllFileAcess="+isAllFileAccess());//~vc4yR~
        AxeG.setNoAllFile(false);   //popup at start if changed to off next time//~vc4yI~
	    requestManageAllFile();                                    //~vc4yI~
    }                                                              //~vc4yI~
}//class                                                           //~1110I~
