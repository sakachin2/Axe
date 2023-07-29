//*CID://+vc5nR~:                             update#=  314;       //~vc5nR~
//************************************************************************//~v102I~
//vc5n 2023/07/19 copyDoc needs copy timestamp                     //~vc5nI~
//vc5m 2023/07/16 renameDoc create ..(1).ext file if target exist  //~vc5mI~
//vc5k 2023/07/16 copyDoc in //Axe could not override it.          //~vc5kI~
//vc5j 2023/07/12 Dump when copy doc2file if output is readonly file system//~vc5jI~
//vc5i 2023/07/11 avoid Dump and chk existing before api such as openDescriptor etc.//~vc5iI~
//                java.lang.IllegalArgumentException: Failed to determine if primary:Axe/x123r is child of primary:Axe: java.io.FileNotFoundException: Missing file for primary:Axe/x123r at /storage/emulated/0/Axe/x123r//~vc5iI~
//vc5e 2023/07/09 expand 1MB-->100MB limit of ERR_TOO_BIG          //~vc5eI~
//vc5d 2023/07/09 (Bug)writeByte offs parameter should be 0        //~vc5dI~
//vbyh:230514 (ARM)dlcmd "=" support for external storage          //~vbyhI~
//vbyf:230502 (ARM) "//" option required to delete sortpath root(such as //Axe)//~vbyfI~
//vc4w 2023/04/30 (Bug)IllegarArgumentException for openDir //Axe/* by "e //Axe"//~v4cwI~
//vc4v 2023/04/28 (Bug)parent dir was deleted by request delete of wildname member//~v4cvI~
//v77h:230424 ARM;copy                                             //~v77hI~
//v77g:230424 ARM;rename                                           //~v77gI~
//v77d:230423 ARM;delete                                           //~v77dI~
//v77c:230422 ARM;mkdir                                            //~vc4sI~
//vc4s 2023/04/15 edit using SAF                                   //~vc4sI~
//vc4q 2023/04/01 support shared storage using SAF(StorageAccessFramework)//~vc4qI~
//************************************************************************//~v102I~
//*access to shared storage                                        //~vc4qI~
//************************************************************************//~vc4qI~
package com.ahsv.utils;                                            //~vc09R~
                                                                   //~1110I~

import com.xe.Axe.AxeJNI; //~vc09R~

import android.content.ContentResolver;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;

import androidx.documentfile.provider.DocumentFile;                //~vc4qI~
                                                                   //~vc4qI~
import com.ahsv.AG;                                                //~vc09R~
import com.xe.Axe.Dump;                                            //~vc09R~
import static com.xe.Axe.AxeJNIdef.*;                              //~vc4sI~

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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;                                          //~vc4sI~
import java.util.Map;
//~vc4qI~


public class USAF2                                                 //~vc4qR~
{                                                                  //~1110I~
    private static final String CN="USAF2.";                       //~vc4qR~
    private static final int READ_BUFFSZ=4096;                     //~vc4qI~
    private static final int RC_IOE=-2;                            //~vc4qI~
                                                                   //~vc4sI~
    private static final long LEN_FOR_STRING=16*1024;   //16K return buffer//~vc4sR~
//  private static final long LEN_TOO_BIG=1024*1024;    //1MB      //~vc4sI~//~vc5eR~
    private static final long LEN_TOO_BIG=1024*1024*100;//100MB    //~vc5eI~
                                                                   //~vc4qI~
	private ContentResolver CR;                                    //~vc4qI~
	private byte[]  workByteBuffer=new byte[READ_BUFFSZ];                     //~vc4qI~//~vc4sR~
	private boolean swErrReadLine;                                 //~vc4qI~//~vc4sR~
	private String  textRead;                                      //~vc4sI~
	private byte[]  byteRead;                                      //~vc4sI~
    private int errno;                                             //~v77hI~
//  private String  openedPath;                                    //~vc4sR~
//  private BufferedReader openedBR;                               //~vc4sR~
//  private int openedLineno;                                      //~vc4sR~
	private Map<String,DocHandle> mapDocHandle=new HashMap<String,DocHandle>();//~vc4sI~
//  private ParcelFileDescriptor Spfd;	//TODO test                //~v77dR~
    private long lastModifiedOpenCopyDocInput,lastModifiedOpenFileInputStream;   //millisec from 1970//~vc5nI~
//********************************************************         //~vc4qI~
	public USAF2(ContentResolver Pcr)                              //~vc4qR~
    {                                                              //~vc4qI~
        AG.aUSAF2=this;                                            //~vc4qI~
        CR=Pcr;                                                    //~vc4qI~
    }                                                              //~vc4qI~
////********************************************************         //~vc4qR~//~vc4sR~
//    private static Uri getPathUri(String PstrUri/*top*/,String Pfpath)//~vc4qR~//~vc4sR~
//    {                                                              //~vc4qR~//~vc4sR~
//        if (Dump.Y) Dump.println(CN+"getPathUri PstrUri="+PstrUri+",fpath="+Pfpath);//~vc4qR~//~vc4sR~
//        String path=getPath(Pfpath);                               //~vc4qI~//~vc4sR~
////      String strUri=PstrUri+"/"+path;                            //~vc4qI~//~vc4sR~
//        String strUri=PstrUri+SEP_MEMBER+path.replace("/",SEP_MEMBER);//~vc4sR~
//        Uri uri=Uri.parse(strUri);                                 //~vc4qR~//~vc4sR~
//        if (Dump.Y) Dump.println(CN+"getPathUri uri="+strUri);     //~vc4qR~//~vc4sR~
//        return uri;                                             //~vc4qR~//~vc4sR~
//    }                                                              //~vc4qR~//~vc4sR~
////********************************************************         //~vc4qR~//~vc4sR~
//    private static String getPath(String Pfpath)                   //~vc4qR~//~vc4sR~
//    {                                                              //~vc4qR~//~vc4sR~
//        String rc=null;                                            //~vc4qR~//~vc4sR~
//        int pos=Pfpath.indexOf("/",ALIAS_PREFIX_LEN);               //~vc4qR~//~vc4sR~
//        if (pos>0)                                                 //~vc4qI~//~vc4sR~
//            rc=Pfpath.substring(pos+1);                             //~vc4qR~//~vc4sR~
//        if (Dump.Y) Dump.println(CN+"getPath path="+rc+",fpath="+Pfpath);//~vc4qR~//~vc4sR~
//        return rc;                                                 //~vc4qR~//~vc4sR~
//    }                                                              //~vc4qR~//~vc4sR~
//********************************************************         //~vc5iR~
	private boolean isExist(String Ppath,Uri Puri)                 //~vc5iR~
    {                                                              //~vc5iR~
          boolean rc=false;//,rc2=false;                           //~vc5iR~
          DocumentFile doc;                                        //~vc5iR~
          doc=getDocumentSingle(Puri);                             //~vc5iR~
          rc=USAF.isExists(Puri,doc);                              //~vc5iR~
//        if (Dump.Y) Dump.println(CN+"isExist rc="+rc+",path="+Ppath+",SingleDoc="+doc+",uri="+Puri);//~vc5iR~
//        doc=getDocumentTree(Puri);                               //~vc5iR~
//        if (doc!=null)                                           //~vc5iR~
//            rc2=USAF.isExists(Puri,doc);                         //~vc5iR~
//  	boolean rc=USAF.getDocument(Puri)!=null;                   //~vc5iR~
        if (Dump.Y) Dump.println(CN+"isExist rc="+rc+",path="+Ppath+",uri="+Puri);//~vc5iR~
        return rc;                                                 //~vc5iR~
    }                                                              //~vc5iR~
//********************************************************         //~vc5mI~
	private boolean isExist(String Ppath,String PstrUri)           //~vc5mI~
    {                                                              //~vc5mI~
    	Uri uri=strUriToUri(Ppath,PstrUri);                        //~vc5mI~
		boolean rc=isExist(Ppath,uri);                             //~vc5mI~
        if (Dump.Y) Dump.println(CN+"isExist rc="+rc+",path="+Ppath+",strUri="+PstrUri);//~vc5mI~
        return rc;
    }                                                              //~vc5mI~
//********************************************************         //~vc4qR~
	private DocumentFile getDocumentSingle(Uri Puri)              //~vc4qR~//~vc4sR~
    {                                                              //~vc4qR~
        DocumentFile doc=DocumentFile.fromSingleUri(AG.context,Puri);//~vc4qR~
        if (Dump.Y) Dump.println(CN+"getDocumentSingle uri="+Puri+",doc="+doc+",isDir="+doc.isDirectory());//~vc4sI~
        return doc;                                                //~vc4qR~
    }                                                              //~vc4qR~
//********************************************************         //~vc4qI~
	private DocumentFile getDocumentTree(Uri Puri)                //~vc4qI~//~vc4sR~
    {                                                              //~vc4qI~
        DocumentFile doc=DocumentFile.fromTreeUri(AG.context,Puri);//~vc4qI~
        if (Dump.Y) Dump.println(CN+"getDocumentTree uri="+Puri+",doc="+doc+",isDir="+doc.isDirectory());//~vc4sI~
        return doc;                                                //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qM~
	private InputStream openInputStream(Uri Puri)                  //~vc4qR~
    {                                                              //~vc4qM~
	    if (Dump.Y) Dump.println(CN+"openInputStream uri="+Puri);  //~vc4qR~
        InputStream is=null;                                       //~vc4qM~
        try                                                        //~vc4qM~
        {                                                          //~vc4qM~
        	is=CR.openInputStream(Puri);                           //~vc4qM~
        }                                                          //~vc4qM~
        catch(FileNotFoundException e)                             //~vc4qM~
        {                                                          //~vc4qM~
        	Dump.printlnNoMsg(e,CN+"openInputStream FileNotFound:"+Puri);//~vc4qR~//~vbyhR~
        }                                                          //~vc4qM~
        catch(IllegalArgumentException e)	//failed to determin.. file not found//~vbyhI~
        {                                                          //~vbyhI~
        	Dump.printlnNoMsg(e,CN+"openInputStream Exception:"+Puri);//~vbyhI~
        }                                                          //~vbyhI~
	    if (Dump.Y) Dump.println(CN+"openInputStream is="+Utils.toString(is));//~vc4qR~
        return is;                                                 //~vc4qM~
    }                                                              //~vc4qM~
//********************************************************         //~vc4qM~
	private OutputStream openOutputStream(Uri Puri,String PopenOpt)                //~vc4qR~
    {                                                              //~vc4qM~
	    if (Dump.Y) Dump.println(CN+"openOutputStream openOpt="+PopenOpt+",Puri="+Puri);//~vc4qR~
        errno=0;                                                   //~vc5jI~
        OutputStream os=null;                                      //~vc4qM~
        try                                                        //~vc4qM~
        {                                                          //~vc4qM~
        	os=CR.openOutputStream(Puri,PopenOpt);                          //~vc4qM~
        }                                                          //~vc4qM~
        catch(FileNotFoundException e)                             //~vc4qM~
        {                                                          //~vc4qM~
        	Dump.printlnNoMsg(e,CN+"openOutputStream:uri="+Puri);//~vc4qR~//~vc4sR~//~vc5jR~
            errno=ENOENT;                                           //~vc5jI~
        }                                                          //~vc4qM~
        catch(IllegalArgumentException e)                          //~vc4sI~
        {                                                          //~vc4sI~
        	Dump.printlnNoMsg(e,CN+"openOutputStream:uri="+Puri);       //~vc4sI~//~vc5jR~
            errno=EINVAL;                                          //~vc5jI~
        }                                                          //~vc4sI~
        catch(Exception e)                                         //~vc5jI~
        {                                                          //~vc5jI~
        	Dump.printlnNoMsg(e,CN+"openOutputStream:uri="+Puri);  //~vc5jI~
            errno=EINTR;                                           //~vc5jI~
        }                                                          //~vc5jI~
	    if (Dump.Y) Dump.println(CN+"openOutputStream errno="+errno+",os="+Utils.toString(os));//~vc4qR~//~vc5jR~
        return os;                                                 //~vc4qM~
    }                                                              //~vc4qM~
//********************************************************         //~vc4sI~
private PrintWriter openPrintWriter(OutputStream Pos,Uri Puri)              //~vc4sI~
{                                                                  //~vc4sI~
	OutputStreamWriter osw=null;                                   //~vc4sI~
	PrintWriter pw=null;                                           //~vc4sI~
    try                                                            //~vc4sI~
    {                                                              //~vc4sI~
    	osw=new OutputStreamWriter(Pos,"UTF-8");                   //~vc4sI~
    }                                                              //~vc4sI~
    catch(UnsupportedEncodingException e)                          //~vc4sI~
    {                                                              //~vc4sI~
        Dump.println(e,CN+"openPrintWriter:"+Puri);                //~vc4sI~
    }                                                              //~vc4sI~
    if (osw!=null)                                                 //~vc4sI~
		pw=new PrintWriter(osw,true/*autoFlush*/);                 //~vc4sI~
	if (Dump.Y) Dump.println(CN+"openPrintWriter pw="+pw+",Puri="+Puri);         //~vc4sI~
    return pw;                                                     //~vc4sI~
}                                                                  //~vc4sI~
//********************************************************         //~vc4sI~
	private FileInputStream openFileInputStream(String Pfnm)        //~vc4sI~
    {                                                              //~vc4sI~
	    if (Dump.Y) Dump.println(CN+"openFileInputStream entry fnm="+Pfnm);//~vc4sI~
        FileInputStream fis=null;                                  //~vc4sI~
        try                                                        //~vc4sI~
        {                                                          //~vc4sI~
//          fis=new FileInputStream(new File(Pfnm));             //~vc4sR~//~vc5nR~
            File f=new File(Pfnm);                                 //~vc5nI~
            fis=new FileInputStream(f);                            //~vc5nI~
            lastModifiedOpenFileInputStream=f.lastModified();   //milisec from 1970//~vc5nI~
        }                                                          //~vc4sI~
        catch(FileNotFoundException e)                             //~vc4sI~
        {                                                          //~vc4sI~
        	Dump.println(e,CN+"openFileInputStream Not Found fpath="+Pfnm);//~vc4sI~
        }                                                          //~vc4sI~
	    if (Dump.Y) Dump.println(CN+"openFileInputStream fis="+fis+",fnm="+Pfnm);//~vc4sI~
        return fis;                                                //~vc4sI~
    }                                                              //~vc4sI~
//********************************************************         //~vc4sI~
    private FileOutputStream openFileOutputStream(String Pfnm,boolean PswAppend)     //~vc4sR~//~v77hR~
    {                                                              //~vc4sI~
    	FileOutputStream fos=null;                                 //~vc4sI~
    	File f=new File(Pfnm);                                     //~vc4sR~
        errno=0;                                                   //~vc5jI~
        try                                                        //~vc4sI~
        {                                                          //~vc4sI~
	        fos=new FileOutputStream(f,PswAppend);          //~vc4sI~//~v77hR~
        }                                                          //~vc4sI~
        catch(FileNotFoundException e)                             //~vc4sI~
        {                                                          //~vc4sI~
        //*includes err EROFS(Read-only file system)               //~vc5jI~
        	Dump.printlnNoMsg(e,CN+"openFileOutputStream FileNotFound:"+Pfnm);//~vc4sR~//~vc5jR~
            errno=ENOENT;                                          //~vc5jI~
        }                                                          //~vc4sI~
        catch(SecurityException e)                                 //~vc4sI~
        {                                                          //~vc4sI~
        	Dump.printlnNoMsg(e,CN+"openFileOutputStream SecurityException:"+Pfnm);//~vc4sR~//~vc5jR~
            errno=EACCES;  //13                                    //~vc5jI~
        }                                                          //~vc4sI~
        catch(Exception e)                                         //~vc5jI~
        {                                                          //~vc5jI~
        	Dump.printlnNoMsg(e,CN+"openFileOutputStream Exception:"+Pfnm);//~vc5jI~
            errno=EINTR;	//4                                        //~vc5jI~
        }                                                          //~vc5jI~
	    if (Dump.Y) Dump.println(CN+"openFileOutputStream errno="+errno+",fos="+fos+",fnm="+Pfnm);//~vc4sI~//~v77hR~//~vc5dR~//~vc5jR~
        return fos;                                                //~vc4sI~
    }                                                              //~vc4sI~
//********************************************************         //~vc4sI~
	private int getOptRead(int Popt,DocumentFile Pdoc)             //~vc4sI~
    {                                                              //~vc4sI~
//  	int opt=Popt;                                              //~vc4sR~
    	int opt=Popt & ~ROPT_STRING;	//no performance problem,drop STRING option//~vc4sI~
        int sz;                                                    //~vc4sI~
        if (Pdoc==null)                                            //~vc4sI~
        	sz=0;                                                  //~vc4sI~
        else                                                       //~vc4sI~
        	sz=(int)Pdoc.length();                                 //~vc4sR~
	    if (Dump.Y) Dump.println(CN+"getOptRead size="+sz);        //~vc4sI~
        if (sz>LEN_TOO_BIG)                                        //~vc4sI~
        {                                                          //~vc4sI~
        	opt|=ROPT_RC_ERR_TOOBIG;                               //~vc4sI~
		    if (Dump.Y) Dump.println(CN+"getOptRead too big file");//~vc4sI~
        }                                                          //~vc4sI~
        else                                                       //~vc4sI~
//      if (Popt==0 || (Popt & ROPT_DEPEND_FILESIZE)!=0)           //~vc4sR~
        if ((Popt & (ROPT_STRING|ROPT_COPYPRIVATE))==0)            //~vc4sI~
        {                                                          //~vc4sI~
		    if (Dump.Y) Dump.println(CN+"getOptRead len="+sz);     //~vc4sR~
//          if (sz<LEN_FOR_STRING)                                 //~vc4sR~
//              opt=ROPT_STRING;                                   //~vc4sR~
//          else                                                   //~vc4sR~
                opt=ROPT_OPEN;                                     //~vc4sI~
        }                                                          //~vc4sI~
	    if (Dump.Y) Dump.println(CN+"getOptRead opt="+Integer.toHexString(opt)+",Popt="+Integer.toHexString(Popt));//~vc4sR~
        return opt;                                                //~vc4sI~
    }                                                              //~vc4sI~
//********************************************************         //~vc4sI~
private String getOpenOption(int Popt)                                //~vc4sI~
{                                                                  //~vc4sI~
	String rc="";                                                  //~vc4sI~
    if ((Popt & ROPT_INPUT)!=0)                                    //~vc4sI~
    	rc+="r";                                                   //~vc4sI~
    else                                                           //~vc4sI~
    if ((Popt & ROPT_APPEND)!=0)                                   //~vc4sI~
    	rc+="a";                                                   //~vc4sI~
    else                                                           //~vc4sI~
    	rc+="w";                                                   //~vc4sI~
//  if ((Popt & ROPT_BINARY)!=0)                                   //~vc4sR~
//  	rc+="b";      //not prepared                               //~vc4sR~
	if (Dump.Y) Dump.println(CN+"getOpenOption Popt="+Integer.toHexString(Popt)+",rc="+rc);//~vc4sI~
    return rc;                                                     //~vc4sI~
}                                                                  //~vc4sI~
//********************************************************         //~vc4sI~
//	public int openDoc(int Popt,String Pfpath,String PstrUri,String PfpathTemp,String[] Pptext,int[] Ppint)//~vc4sR~
  	public int openDoc(int Popt,String Pfpath,String PstrUri,String PfpathTemp)//~vc4sI~
    {                                                              //~vc4sI~
    	int rc,rcOpt;                                              //~vc4sR~
    	Uri uri=null;                                              //~v77dI~
//      String rcString=null;                                      //~vc4sR~
        byte[] rcByte=null;                                        //~vc4sI~
//      if (true)//TODO test                                       //~vc4sR~
//      {                                                          //~vc4sR~
//            Ppint[0]=98;                                         //~vc4sR~
//            Pptext[0]=new String("testOut");                     //~vc4sR~
//            if (Dump.Y) Dump.println(CN+"openDoc test return");  //~vc4sR~
//  		rcOpt=99;                                              //~vc4sR~
//  		AxeJNI.notifyOpenDocResult(Popt,Pfpath,rcOpt,new String("testOut"),null);//~vc4sR~
//          return 0;                                              //~vc4sR~
//      }                                                          //~vc4sR~
//      openedBR=null;                                             //~vc4sR~
//      openedPath=null;                                           //~vc4sR~
	    if (Dump.Y) Dump.println(CN+"openDoc opt="+Integer.toHexString(Popt)+",fpath="+Pfpath+",fpathTemp="+PfpathTemp+",PstrUri="+PstrUri);//~vc4sR~
//    	Uri uri=getPathUri(PstrUri,Pfpath);                        //~vc4sR~
//    	Uri uri=USAF.makeUriPath(PstrUri,Pfpath);                  //~vc4sI~//~v77dR~
//		DocumentFile doc=USAF.getDocument(uri);                    //~vc4sR~//~v77dR~
        DocumentFile doc=USAF.getDocumentItself(Popt,PstrUri,Pfpath);
        int opt=getOptRead(Popt,doc);                              //~vc4sI~
    if ((Popt & ROPT_INPUT)!=0)                                    //~vc4sI~
    {                                                              //~vc4sI~
        if (doc==null)                                             //~vc4sI~
        	return ENOENT;                                         //~vc4sR~
        uri=doc.getUri();                                          //~v77dI~
//      if (doc.isDirectory())                                     //~vc4sR~
        if (USAF.isDirectory(Pfpath,doc))                          //~vc4sI~
        	return EISDIR;                                         //~vc4sR~
		if ((opt & ROPT_RC_ERR_TOOBIG)!=0)  //    =0x0100;                  //~vc4sI~
        	return EFBIG;                                          //~vc4sI~
    }                                                              //~vc4sI~
    else                                                           //~vc4sI~
    {                                                              //~vc4sI~
        if (USAF.isDirectory(Pfpath,doc))             //~vc4sI~    //~v77dR~
        	return EISDIR;                                         //~vc4sI~
    }                                                              //~vc4sI~
        if ((opt & ROPT_STRING)!=0)                                //~vc4sI~
        {                                                          //~vc4sI~
        	rc=doc2String(Popt,Pfpath,doc);                        //~vc4sI~
            if (rc!=0)                                             //~vc4sR~
            {                                                      //~vc4sI~
//          	Pptext[0]=null;                                    //~vc4sR~
//          	rcString=null;                                     //~vc4sR~
	    		if (Dump.Y) Dump.println(CN+"openDoc String option failed rc="+rc);//~vc4sI~
            }                                                      //~vc4sI~
            else                                                   //~vc4sI~
            {                                                      //~vc4sI~
//          	Pptext[0]=textRead;                                //~vc4sR~
//            	rcString=textRead;                                 //~vc4sR~
    			AxeJNI.notifyOpenDocResult(Popt,Pfpath,opt,textRead,byteRead);//~vc4sR~
		    	if (Dump.Y) Dump.println(CN+"openDoc String option notified");//~vc4sI~
            }                                                      //~vc4sI~
//          Ppint[0]=opt;                                          //~vc4sR~
			textRead=null; //for GC                                //~vc4sI~
			byteRead=null;                                         //~vc4sI~
	    	if (Dump.Y) Dump.println(CN+"openDoc String option return rc="+rc);//~vc4sR~
            return rc;                                             //~vc4sI~
        }                                                          //~vc4sI~
        if ((opt & ROPT_OPEN)!=0)                                  //~vc4sI~
        {                                                          //~vc4sI~
	    	if (Dump.Y) Dump.println(CN+"openDoc Open option");    //~vc4sR~
	        BufferedInputStream bis=null;                          //~vc4sI~
	        BufferedOutputStream bos=null;                         //~vc4sI~
		    BufferedReader br=null;                                //~vc4sI~
			PrintWriter pw=null;                                   //~vc4sI~
            String optOpen=getOpenOption(Popt);   //"rb",wb" ,...                    //~vc4sI~
            if ((Popt & ROPT_INPUT)!=0)                            //~vc4sI~
            {                                                      //~vc4sI~
				InputStream is=openInputStream(uri);               //~vc4sI~
	    	    if (is == null)                                    //~vc4sI~
		        	return ENOENT;                                 //~vc4sI~
                if ((Popt & ROPT_BINARY)!=0)                       //~vc4sR~
                    bis=new BufferedInputStream(is);               //~vc4sR~
                else                                               //~vc4sR~
                {                                                  //~vc4sR~
                    InputStreamReader isr=new InputStreamReader(is);//~vc4sR~
                    br=new BufferedReader(isr);                    //~vc4sR~
                }                                                  //~vc4sR~
            }                                                      //~vc4sI~
            else //output                                          //~vc4sR~
            {                                                      //~vc4sI~
	            if (!USAF.isExists(Pfpath,doc))                     //~v77dR~
                {                                                  //~v77dI~
                	doc=createDocument(Popt,Pfpath,PstrUri);       //~vc4sI~//~v77dR~
                }                                                  //~v77dI~
                uri=doc.getUri();                                  //~v77dI~
				OutputStream os=openOutputStream(uri,optOpen);     //~vc4sR~
	    	    if (os == null)                                    //~vc4sI~
//  	        	return ENOENT;                                 //~vc4sI~//~vc5jR~
    	        	return errno;                                  //~vc5jI~
                if ((Popt & ROPT_BINARY)!=0)                       //~vc4sI~
                    bos=new BufferedOutputStream(os);              //~vc4sI~
                else                                               //~vc4sI~
					pw=openPrintWriter(os,uri);                        //~vc4sI~
            }                                                      //~vc4sI~
    		AxeJNI.notifyOpenDocResult(Popt,Pfpath,opt,null,null);       //~vc4sR~
//          openedBR=br;                                           //~vc4sR~
//          openedPath=Pfpath;                                     //~vc4sR~
//          openedLineno=0;                                        //~vc4sR~
//          Pptext[0]=null;                                        //~vc4sR~
//          Ppint[0]=opt;                                          //~vc4sR~
          if ((opt & ROPT_COPYPRIVATE)==0)                         //~vbyhI~
            saveOpenedHandle(Pfpath,opt,bis,br,bos,pw);            //~vc4sR~
	    	if (Dump.Y) Dump.println(CN+"openDoc Open option openedPath="+Pfpath);//~vc4sR~
            return 0;                                              //~vc4sI~
        }                                                          //~vc4sI~
	    if (Dump.Y) Dump.println(CN+"openDoc CopyLocal option");   //~vc4sR~
        rc=doc2File(Popt,Pfpath,doc,PfpathTemp);                   //~vc4sR~
        return rc;                                                 //~vc4sI~
    }                                                              //~vc4sI~
//********************************************************         //~vc4sI~
//*read line for previously openDoc                                //~vc4sI~
//********************************************************         //~vc4sI~
	public int fgetsDoc(int Popt,String PstrUri,String Pfpath,int Pbuffsz)//~vc4sR~
    {                                                              //~vc4sI~
	    if (Dump.Y) Dump.println(CN+"fgetesDoc opt="+Integer.toHexString(Popt)+",fpath="+Pfpath+",Pbuffsz="+Pbuffsz);//~vc4sR~
//      Ppint[0]=0;                                                //~vc4sR~
//      Pptext[0]=null;                                            //~vc4sR~
//      BufferedReader br=openedBR;                                //~vc4sR~
//      String fpath=openedPath;                                   //~vc4sR~
//      int lineno=openedLineno;                                   //~vc4sR~
//      if (br==null || fpath==null)                               //~vc4sR~
//          return EINTR;                                          //~vc4sR~
        DocHandle dh=getOpenedHandle(Pfpath);                      //~vc4sI~
        if (dh==null)                                              //~vc4sI~
            return EINTR;                                          //~vc4sI~
        BufferedReader br=dh.br;                                   //~vc4sI~
        int lineno=dh.lineNo;                                      //~vc4sI~
        String line=readLine(br,Pfpath,lineno);                    //~vc4sR~
        if (line==null)                                            //~vc4sR~
        {                                                          //~vc4sR~
            boolean err=swErrReadLine|close(br,Pfpath,lineno);      //~vc4sR~
            if (err)                                               //~vc4sR~
                return EINTR;                                      //~vc4sR~
        }                                                          //~vc4sR~
        dh.lineNo++;                                               //~vc4sR~
  		AxeJNI.notify_fgetsDocResult(Popt,Pfpath,line,Pbuffsz);    //~vc4sR~
	    if (Dump.Y) Dump.println(CN+"fgetsDoc exit lineno="+dh.lineNo+",line="+line);//~vc4sR~
        return 0;                                                  //~vc4sR~
    }                                                              //~vc4sI~
//********************************************************         //~vc4sI~
//*read line for previously openDoc                                //~vc4sI~
//********************************************************         //~vc4sI~
	public int freadDoc(int Popt,String Pfpath,int Plen)           //~vc4sR~
    {                                                              //~vc4sI~
	    if (Dump.Y) Dump.println(CN+"freadDoc opt="+Integer.toHexString(Popt)+",fpath="+Pfpath);//~vc4sI~
        DocHandle dh=getOpenedHandle(Pfpath);                      //~vc4sI~
        if (dh==null)                                              //~vc4sI~
            return EINTR;                                          //~vc4sI~
	    BufferedInputStream bis=dh.bis;                            //~vc4sI~
        int offs=dh.offs;                                          //~vc4sI~
        byte[] buffRC=dh.getByteBuffer(Plen);                      //~vc4sR~
		int len=readByte(bis,buffRC,Pfpath,0/*offsInBuff*/,Plen);  //~vc4sR~
        if (len<=0)                                                //~vc4sR~
        {                                                          //~vc4sI~
            boolean err=swErrReadLine|close(bis,Pfpath,offs);       //~vc4sI~
            if (err)                                               //~vc4sI~
                return EINTR;                                      //~vc4sI~
        }                                                          //~vc4sI~
        dh.offs+=len;                                              //~vc4sI~
        if (len>0)                                                 //~vc4sI~
	  		AxeJNI.notify_freadDocResult(Popt,Pfpath,buffRC,len);   //~vc4sR~
	    if (Dump.Y) Dump.println(CN+"freadDoc exit offs="+dh.offs+",len="+len+",buffRC="+buffRC);//~vc4sR~
        return 0;                                                  //~vc4sR~
    }                                                              //~vc4sI~
//********************************************************         //~vc4sI~
//*write byte for previously openDoc                               //~vc4sI~
//********************************************************         //~vc4sI~
	public int fwriteDoc(int Popt,String Pfpath,byte[] Pbuff,int Plen)//~vc4sR~
    {                                                              //~vc4sI~
	    if (Dump.Y) Dump.println(CN+"fwriteDoc opt="+Integer.toHexString(Popt)+",fpath="+Pfpath+",len="+Plen);//~vc4sR~
        DocHandle dh=getOpenedHandle(Pfpath);                      //~vc4sI~
        if (dh==null)                                              //~vc4sI~
            return EINTR;                                          //~vc4sI~
	    BufferedOutputStream bos=dh.bos;                           //~vc4sR~
        int offs=dh.offs;                                          //~vc4sI~
		boolean err=writeByte(bos,Pbuff,0,Plen,Pfpath);            //~vc4sR~
        if (err)                                                   //~vc4sR~
            close(bos,Pfpath,offs);                                //~vc4sR~
        if (err)                                                   //~vc4sI~
            return EINTR;                                          //~vc4sI~
	  	AxeJNI.notify_fwriteDocResult(Popt,Pfpath,Plen);           //~vc4sR~
	    if (Dump.Y) Dump.println(CN+"fwriteDoc exit offs="+dh.offs+",len="+Plen);//~vc4sR~
        return 0;                                                  //~vc4sI~
    }                                                              //~vc4sI~
//********************************************************         //~vc4sI~
	public int fcloseDoc(int Popt,String Pfpath)                   //~vc4sI~
    {                                                              //~vc4sI~
	    if (Dump.Y) Dump.println(CN+"fcloseDoc opt="+Integer.toHexString(Popt)+",fpath="+Pfpath);//~vc4sI~//~v77hR~
        boolean swErr=false;                                       //~vc4sI~
        DocHandle dh=getOpenedHandle(Pfpath);                      //~vc4sI~
        if (dh==null)                                              //~vc4sI~
            return EINTR;                                          //~vc4sI~
        if (dh.bis!=null)                                          //~vc4sI~
        {                                                          //~vc4sI~
			swErr|=close(dh.bis,Pfpath,dh.offs);                   //~vc4sI~
		    dh.bis=null;                                           //~vc4sI~
        }                                                          //~vc4sI~
        if (dh.br!=null)                                           //~vc4sI~
        {                                                          //~vc4sI~
			swErr|=close(dh.br,Pfpath,dh.lineNo);                  //~vc4sI~
		    dh.br=null;                                            //~vc4sI~
        }                                                          //~vc4sI~
        if (dh.bos!=null)                                          //~vc4sI~
        {                                                          //~vc4sI~
			swErr|=close(dh.bos,Pfpath,dh.lineNo);                 //~vc4sI~
		    dh.bos=null;                                           //~vc4sI~
        }                                                          //~vc4sI~
        if (dh.pw!=null)                                           //~vc4sI~
        {                                                          //~vc4sI~
			swErr|=close(dh.pw,Pfpath,dh.lineNo);                  //~vc4sI~
		    dh.pw=null;                                            //~vc4sI~
        }                                                          //~vc4sI~
        int rc=swErr ? -1 : 0;                                     //~vc4sI~
	    if (Dump.Y) Dump.println(CN+"fcloseDoc exit swErr="+swErr);//~vc4sI~
        return rc;                                                 //~vc4sI~
    }                                                              //~vc4sI~
//********************************************************         //~vc4sI~
	private int doc2String(int Popt,String Pfpath,DocumentFile Pdoc)//~vc4sI~
    {                                                              //~vc4sI~
	    if (Dump.Y) Dump.println(CN+"doc2String opt="+Integer.toHexString(Popt)+",fpath="+Pfpath+",doc="+Pdoc);//~vc4sI~
        boolean err=false;                                         //~vc4sR~
        byteRead=null;                                             //~vc4sI~
        textRead=null;                                             //~vc4sI~
        Uri uri=Pdoc.getUri();                                     //~vc4sI~
		InputStream is=openInputStream(uri);                       //~vc4sI~
        if (is == null)                                            //~vc4sI~
        	return ENOENT;                                         //~vc4sR~
        int szFile=(int)Pdoc.length();
        int lineno=0;
        int offs=0;//~vc4sI~
        if (Dump.Y) Dump.println(CN+"doc2String doc length="+szFile);//~vc4sI~
        if ((Popt & ROPT_BINARY)!=0)                               //~vc4sI~
        {                                                          //~vc4sI~
	        BufferedInputStream bis=new BufferedInputStream(is);   //~vc4sI~
            byte[] buffRC=new byte[szFile];                        //~vc4sR~
            for (;;)                                               //~vc4sI~
            {                                                      //~vc4sI~
				int len=readByte(bis,buffRC,Pfpath,offs,READ_BUFFSZ);//~vc4sR~
                if (len<0)	//-1:eof,-2:ioerr                      //~vc4sI~
                {                                                  //~vc4sI~
                    err|=swErrReadLine;                            //~vc4sI~
                    break;                                         //~vc4sI~
                }                                                  //~vc4sI~
                offs+=len;                                         //~vc4sI~
            }                                                      //~vc4sI~
	        err|=close(bis,Pfpath,offs);                         //~vc4sI~
            if (!err)                                              //~vc4sI~
		        byteRead=buffRC;                                   //~vc4sR~
        }                                                          //~vc4sI~
        else                                                       //~vc4sI~
        {                                                          //~vc4sI~
	        InputStreamReader isr=new InputStreamReader(is);       //~vc4sI~
    	    BufferedReader br=new BufferedReader(isr);             //~vc4sI~
            StringBuffer sb=new StringBuffer(szFile);              //~vc4sR~
            for (;;)                                               //~vc4sR~
            {                                                      //~vc4sR~
                String line=readLine(br,Pfpath,lineno);            //~vc4sR~
                if (line==null)                                    //~vc4sR~
                {                                                  //~vc4sR~
                    err|=swErrReadLine;                            //~vc4sR~
                    break;                                         //~vc4sR~
                }                                                  //~vc4sR~
                lineno++;                                          //~vc4sR~
                sb.append(line);                                   //~vc4sR~
                sb.append("\n");                                   //~vc4sR~
                if (Dump.Y) Dump.println(CN+"doc2String lineno="+lineno+"="+line);//~vc4sR~
            }                                                      //~vc4sR~
	        err|=close(br,Pfpath,lineno);                          //~vc4sI~
            if (!err)                                              //~vc4sI~
		        textRead=sb.toString();                            //~vc4sI~
        }                                                          //~vc4sI~
        if (err)                                                   //~vc4sI~
        	return EINTR;                                          //~vc4sR~
	    if (Dump.Y) Dump.println(CN+"doc2String exit lineno="+lineno+",offs="+offs+",textSize="+textRead.length());//~vc4sR~
        return 0;                                                  //~vc4sI~
    }                                                              //~vc4sI~
//********************************************************         //~vc4sI~
	private int doc2File(int Popt,String Pfpath,DocumentFile Pdoc,String PfnmLocal)//~vc4sR~
    {                                                              //~vc4sI~
	    if (Dump.Y) Dump.println(CN+"doc2File dpath="+Pfpath+",doc="+Pdoc+",local="+PfnmLocal);//~vc4sR~
        boolean swErr=false;                                       //~vc4sI~
        FileOutputStream fos=openFileOutputStream(PfnmLocal,false);      //~vc4sR~//~v77hR~
        if (fos==null)                                             //~vc4sI~
        {                                                          //~vc4sI~
//      	return EINTR;                          //~vc4sI~       //~vc5jR~
        	return errno;                                          //~vc5jI~
        }                                                          //~vc4sI~
        BufferedOutputStream bos=new BufferedOutputStream(fos);    //~vc4sI~
        Uri uri=Pdoc.getUri();                                     //~vc4sI~
		InputStream is=openInputStream(uri);                       //~vc4sI~
        if (is == null)                                            //~vc4sI~
        {                                                          //~vc4sI~
        	close(bos,PfnmLocal,0);                                //~vc4sR~
        	return ENOENT;                                         //~vc4sR~
        }                                                          //~vc4sI~
        BufferedInputStream bis=new BufferedInputStream(is);       //~vc4sI~
        int offs=0;                                                //~vc4sI~
        for (;;)                                                   //~vc4sI~
        {                                                          //~vc4sI~
        	int len;                                               //~vc4sI~
            len=readByte(bis,workByteBuffer,Pfpath,0/*offsInBuff*/,READ_BUFFSZ);//~vc4sR~
            if (len==RC_IOE)                                       //~vc4sI~
            {                                                      //~vc4sI~
            	swErr=true;                                        //~vc4sI~
                break;                                             //~vc4sI~
            }                                                      //~vc4sI~
            if (len<=0)//EOF                                       //~vc4sI~
                break;                                             //~vc4sI~
//          swErr=writeByte(bos,workByteBuffer,offs,len,PfnmLocal);//~vc4sR~//~vc5dR~
            swErr=writeByte(bos,workByteBuffer,0/*offs*/,len,PfnmLocal);//~vc5dI~
            if (swErr)                                             //~vc4sI~
                break;                                             //~vc4sI~
            offs+=len;                                             //~vc4sI~
        }                                                          //~vc4sI~
        swErr|=close(bis,Pfpath,offs);                              //~vc4sR~
        swErr|=close(bos,Pfpath,offs);                             //~vbyhI~
        if (swErr)                                                 //~vc4sI~
        	return EINTR;                                          //~vc4sR~
	    if (Dump.Y) Dump.println(CN+"doc2File exit offs="+offs+",fpath="+Pfpath+",local="+PfnmLocal);//~vc4sR~
        return 0;                                                  //~vc4sI~
    }                                                              //~vc4sI~
//********************************************************         //~vc4qI~//~vc4sM~
	private int file2Doc(String Pfpath,Uri Puri)                   //~vc4sR~
    {                                                              //~vc4sI~
    	int rc=-1;                                                 //~vc4sI~
        int offs=0;                                                //~vc4sI~
        String strUri=Puri.toString();                             //~vc4sR~
        boolean swErr=false;                                       //~vc4sI~
        FileInputStream fis=null;                                  //~vc4sI~
	    if (Dump.Y) Dump.println(CN+"file2Doc entry fpath="+Pfpath+",uri="+Puri);//~vc4sR~
        try                                                        //~vc4sI~
        {                                                          //~vc4sI~
            fis=new FileInputStream(Pfpath);                       //~vc4sI~
        }                                                          //~vc4sI~
        catch(FileNotFoundException e)                             //~vc4sI~
        {                                                          //~vc4sI~
            swErr=true;                                            //~vc4sI~
        	Dump.println(e,CN+"file2Document Not Found fpath="+Pfpath);//~vc4sI~
        }                                                          //~vc4sI~
        if (!swErr)                                                //~vc4sI~
        {                                                          //~vc4sI~
            BufferedInputStream bis=new BufferedInputStream(fis);  //~vc4sI~
            if (Dump.Y) Dump.println(CN+"file2Doc bufferedInputStream="+Utils.toString(bis));//~vc4sR~
        	OutputStream os=openOutputStream(Puri,"wb");                //~vc4sI~
            if (os!=null)                                          //~vc4sI~
            {                                                      //~vc4sI~
                BufferedOutputStream bos=new BufferedOutputStream(os);//~vc4sI~
                while(true)                                        //~vc4sI~
                {                                                  //~vc4sI~
                    int len;                                       //~vc4sI~
                    len=readByte(bis,workByteBuffer,Pfpath,0,READ_BUFFSZ);//~vc4sR~
                    swErr|=swErrReadLine;                          //~vc4sI~
                    if (swErr)                                     //~vc4sI~
                    	break;                                     //~vc4sI~
                    if (len<=0)//EOF                               //~vc4sI~
                        break;                                     //~vc4sI~
                    swErr=writeByte(bos,workByteBuffer,0/*offsInBuff*/,len,Pfpath);//~vc4sR~
                    if (swErr)                                     //~vc4sI~
                        break;                                     //~vc4sI~
                    offs+=len;                                     //~vc4sI~
                }                                                  //~vc4sI~
	            swErr|=close(bos,strUri,offs);                     //~vc4sR~
            }                                                      //~vc4sI~
            swErr|=close(bis,Pfpath,offs);                         //~vc4sR~
        }                                                          //~vc4sI~
        if (!swErr)                                                //~vc4sI~
        	rc=0;                                                  //~vc4sI~
	    if (Dump.Y) Dump.println(CN+"file2Doc exit rc="+rc+",path="+Pfpath+",uri="+Puri);//~vc4sR~
        return rc;                                                 //~vc4sI~
    }                                                              //~vc4sI~
//********************************************************         //~vc4qI~//~vc4sM~
//*rc:-1:eof,-2 ioerr                                              //~vc4qI~//~vc4sM~
//********************************************************         //~vc4qI~//~vc4sM~
	public int readByte(BufferedInputStream Pbis,byte[] Pbuff,String Pcmt,int Poffs,int Plen)//~vc4qI~//~vc4sR~
    {                                                              //~vc4qI~//~vc4sM~
    	int len;                                                   //~vc4qI~//~vc4sM~
        swErrReadLine=false;                                       //~vc4sI~
        try                                                        //~vc4qI~//~vc4sM~
        {                                                          //~vc4qI~//~vc4sM~
            len=Pbis.read(Pbuff,Poffs,READ_BUFFSZ);                    //~vc4qI~//~vc4sR~
        }                                                          //~vc4qI~//~vc4sM~
        catch(IOException e)                                       //~vc4qI~//~vc4sM~
        {                                                          //~vc4qI~//~vc4sM~
            Dump.println(e,CN+"readByte IOE; offs="+Poffs+",input="+Pcmt);//~vc4qI~//~vc4sI~
            swErrReadLine=true;                                    //~vc4sI~
            len=RC_IOE;                                            //~vc4qI~//~vc4sM~
        }                                                          //~vc4qI~//~vc4sM~
	    if (Dump.Y) Dump.println(CN+"readByte len="+len+",offs="+Poffs+",reqLen="+Plen+",fnm="+Pcmt);//~vc4qI~//~vc4sR~//~vc5dR~
		return len;                                                //~vc4qI~//~vc4sM~
    }                                                              //~vc4qI~//~vc4sM~
//********************************************************         //~vc4qM~
	private String readLine(BufferedReader Pbr,String Pcmt,int Plineno)//~vc4qR~
    {                                                              //~vc4qM~
	    if (Dump.Y) Dump.println(CN+"readLine BufferedReader for "+Pcmt);//~vc4qR~
        boolean swErrReadLine=false;                               //~vc4qM~
        String line=null;                                          //~vc4qM~
        try                                                        //~vc4qM~
        {                                                          //~vc4qM~
        	line=Pbr.readLine();                                   //~vc4qM~
        }                                                          //~vc4qM~
        catch(IOException e)                                       //~vc4qM~
        {                                                          //~vc4qM~
        	Dump.println(e,CN+"readLine BufferedReader IOException for "+Pcmt+",lineno="+Plineno);//~vc4qR~
            swErrReadLine=true;                                    //~vc4qM~
        }                                                          //~vc4qM~
	    if (Dump.Y) Dump.println(CN+"readLine BufferedReader exit for "+Pcmt+",lieno="+Plineno+",err="+swErrReadLine+",line="+line);//~vc4qR~
        return line;                                               //~vc4qM~
    }                                                              //~vc4qM~
//********************************************************         //~vc4qI~//~vc4sM~
//*rc:-1:eof,-2 ioerr                                              //~vc4qI~//~vc4sM~
//********************************************************         //~vc4qI~//~vc4sM~
	public boolean writeByte(BufferedOutputStream Pbos,byte[] Pbuff,int Poffs,int Plen,String Pcmt)//~vc4qI~//~vc4sR~
    {                                                              //~vc4qI~//~vc4sM~
        boolean swErr=false;                                       //~vc4sR~
        int len=0;                                                 //~vc4sI~
        try                                                        //~vc4qI~//~vc4sM~
        {                                                          //~vc4qI~//~vc4sM~
            Pbos.write(Pbuff,Poffs,Plen);                              //~vc4qI~//~vc4sR~
        }                                                          //~vc4qI~//~vc4sM~
        catch(IOException e)                                       //~vc4qI~//~vc4sM~
        {                                                          //~vc4qI~//~vc4sM~
            Dump.println(e,CN+"writeByte IOE; offs="+Poffs+",len="+Plen+",output="+Pcmt);//~vc4qI~//~vc4sR~
            swErr=true;                                              //~vc4qI~//~vc4sR~
        }                                                          //~vc4qI~//~vc4sM~
	    if (Dump.Y) Dump.println(CN+"writeByte write reqlen="+Plen+",swErr="+swErr+",file="+Pcmt);//~vc4qI~//~vc4sR~
		return swErr;                                                //~vc4qI~//~vc4sR~
    }                                                              //~vc4qI~//~vc4sM~
//********************************************************         //~vc4qM~
	private boolean close(BufferedReader Pbr,String Pcmt,int Plineno)//~vc4qM~
    {                                                              //~vc4qM~
	    if (Dump.Y) Dump.println(CN+"close BufferedReader for "+Pcmt);//~vc4qM~
        boolean err=false;                                         //~vc4qM~
        try                                                        //~vc4qM~
        {                                                          //~vc4qM~
        	Pbr.close();                                           //~vc4qM~
        }                                                          //~vc4qM~
        catch(IOException e)                                       //~vc4qM~
        {                                                          //~vc4qM~
        	Dump.println(e,CN+"close BufferedReader IOException for "+Pcmt+",lineno="+Plineno);//~vc4qM~
            err=true;                                              //~vc4qM~
        }                                                          //~vc4qM~
	    if (Dump.Y) Dump.println(CN+"close BufferedReader exit for "+Pcmt+",lieno="+Plineno+",err="+err);//~vc4qM~
        return err;                                                //~vc4qM~
    }                                                              //~vc4qM~
//********************************************************         //~vc4qI~//~vc4sM~
	public boolean close(BufferedInputStream Pbis,String Pcmt,int Poffs)     //~vc4qI~//~vc4sI~
    {                                                              //~vc4qI~//~vc4sM~
    	boolean err=false;                                         //~vc4qI~//~vc4sM~
        try                                                        //~vc4qI~//~vc4sM~
        {                                                          //~vc4qI~//~vc4sM~
        	Pbis.close();                                          //~vc4qI~//~vc4sM~
        }                                                          //~vc4qI~//~vc4sM~
        catch(IOException e)                                       //~vc4qI~//~vc4sM~
        {                                                          //~vc4qI~//~vc4sM~
        	Dump.println(e,CN+"close BufferedInputStream("+Pcmt+"), offs="+Poffs);//~vc4qI~//~vc4sI~
            err=true;                                               //~vc4qI~//~vc4sM~
        }                                                          //~vc4qI~//~vc4sM~
	    if (Dump.Y) Dump.println(CN+"close BufferedInputStream("+Pcmt+") err="+err+",offs="+Poffs);//~vc4qI~//~vc4sI~
		return err;                                                //~vc4qI~//~vc4sM~
    }                                                              //~vc4qI~//~vc4sM~
//********************************************************         //~vc4qI~//~vc4sM~
	public boolean close(BufferedOutputStream Pbos,String Pcmt,int Poffs)    //~vc4qI~//~vc4sI~
    {                                                              //~vc4qI~//~vc4sM~
    	boolean err=false;                                         //~vc4qI~//~vc4sM~
        try                                                        //~vc4qI~//~vc4sM~
        {                                                          //~vc4qI~//~vc4sM~
        	Pbos.close();                                          //~vc4qI~//~vc4sM~
        }                                                          //~vc4qI~//~vc4sM~
        catch(IOException e)                                       //~vc4qI~//~vc4sM~
        {                                                          //~vc4qI~//~vc4sM~
        	Dump.println(e,CN+"close BufferedOutputStream("+Pcmt+"), offs="+Poffs);//~vc4qI~//~vc4sI~
            err=true;                                              //~vc4qI~//~vc4sM~
        }                                                          //~vc4qI~//~vc4sM~
	    if (Dump.Y) Dump.println(CN+"close BufferedOutputStream("+Pcmt+") err="+err+",offs="+Poffs);//~vc4qI~//~vc4sI~
		return err;                                                //~vc4qI~//~vc4sM~
    }                                                              //~vc4qI~//~vc4sM~
//********************************************************         //~vc4sI~
	public boolean close(PrintWriter Ppw,String Pcmt,int PlineNo)  //~vc4sI~
    {                                                              //~vc4sI~
    	boolean err=false;                                         //~vc4sI~
        Ppw.close();                                           //~vc4sI~
  	    if (Dump.Y) Dump.println(CN+"close PrintWriter("+Pcmt+") err="+err+",lineno="+PlineNo);//~vc4sI~
		return err;                                                //~vc4sI~
    }                                                              //~vc4sI~
//********************************************************         //~vc4sI~
//*output path is from 1st subdir with starting "/",level is 1 for 1st subdir                                  //~vc4sR~//~v77dR~
//********************************************************         //~vc4sI~
	public int getPathLevel(String Pfpath,String[] PpathAndMember)//~vc4sI~
    {                                                              //~vc4sI~
        int offs=ALIAS_PREFIX_LEN;                                 //~vc4sI~
        int offs1st=0,offslast=0;                                  //~vc4sR~
        int lvl=0;                                                 //~vc4sI~
        for (;;)                                                   //~vc4sI~
        {                                                          //~vc4sI~
            if (lvl==1)                                            //~vc4sI~
                offs1st=offs;                                      //~vc4sI~
        	int offs2=Pfpath.indexOf('/',offs);                    //~vc4sI~
            offslast=offs;                                         //~vc4sM~
            if (offs2<0)                                           //~vc4sI~
            {                                                      //~vc4sI~
            	break;                                             //~vc4sI~
            }                                                      //~vc4sI~
            lvl++;                                                 //~vc4sI~
            offs=offs2+1;                                          //~vc4sI~
        }                                                          //~vc4sI~
        if (offs1st!=0 && offslast>offs1st)                        //~vc4sR~
        {                                                          //~vc4sI~
        	PpathAndMember[0]=Pfpath.substring(offs1st,offslast-1);//~vc4sR~
        	PpathAndMember[1]=Pfpath.substring(offslast);          //~vc4sR~
        }                                                          //~vc4sI~
        else                                                       //~vc4sI~
        {                                                          //~vc4sI~
        	PpathAndMember[0]=null;                                //~vc4sR~
            if (offslast>0)                                        //~vc4sI~
	        	PpathAndMember[1]=Pfpath.substring(offslast);      //~vc4sI~
            else                                                   //~vc4sI~
	        	PpathAndMember[1]=null;                            //~vc4sR~
        }                                                          //~vc4sI~
        if (Dump.Y) Dump.println(CN+"getPathLevel offs1st="+offs1st+",offslast="+offslast+",level="+lvl+",path="+PpathAndMember[0]+",member="+PpathAndMember[1]);//~vc4sR~
        return lvl;                                                //~vc4sI~
    }                                                              //~vc4sI~
//********************************************************         //~vc4sI~
//*build uri for the parent path                                   //~v77cI~
//********************************************************         //~v77cI~
    public static Uri makeUriPath(String PstrUriTop,int Plevel,String Psubdir)//~vc4sI~
    {                                                              //~vc4sI~
        Uri uriTop=Uri.parse(PstrUriTop);                          //~vc4sI~
		String topDocID= DocumentsContract.getTreeDocumentId(uriTop);//~vc4sI~
        String pathID;                                             //~vc4sI~
        if (Plevel>1)                                              //~vc4sR~
	        pathID=topDocID+"/"+Psubdir;                           //~vc4sI~
        else                                                       //~vc4sI~
	        pathID=topDocID;                                       //~vc4sI~
        Uri uriPath=DocumentsContract.buildDocumentUriUsingTree(uriTop,pathID);//~vc4sI~
        if (Dump.Y) Dump.println(CN+"makeUriPath exit strUriTop="+PstrUriTop+",level="+Plevel+",subdir="+Psubdir+",uriPath="+uriPath);//~vc4sI~
        return uriPath;                                            //~vc4sI~
    }                                                              //~vc4sI~
//********************************************************         //~vc4sI~
    private DocumentFile createDocument(int Popt,String Pfpath,String PstrUri)//~vc4sI~//~v77dR~
    {                                                              //~vc4sI~
        if (Dump.Y) Dump.println(CN+"createDoucment Popt="+Integer.toHexString(Popt)+",fpath="+Pfpath+",strUri="+PstrUri);//~vc4sI~
//        String pathParent=USAF.getPathParent(Pfpath,-1/*last parent*/);//~vc4sR~
//        String member=Pfpath.substring(pathParent.length()+1);   //~vc4sR~
//        Uri uriParent=USAF.makeUriPath(PstrUri,Pfpath);          //~vc4sR~
        String[] pathAndMember=new String[2];                       //~vc4sI~
        int lvl=getPathLevel(Pfpath,pathAndMember);                 //~vc4sI~
        if (lvl==0)                                                //~vc4sI~
            return null;                                           //~vc4sI~
        String path=pathAndMember[0];                              //~v77cI~
        String member=pathAndMember[1];                            //~v77cI~
        Uri uriParent=makeUriPath(PstrUri,lvl,path);     //~vc4sI~ //~v77cR~
        DocumentFile tree=getDocumentTree(uriParent);              //~vc4sR~
        if (Dump.Y) Dump.println(CN+"createDocument parent canRead="+tree.canRead()+",canWrite="+tree.canWrite()+",name="+tree.getName()+",type="+tree.getType()+",isFile="+tree.isFile()+",exists="+tree.exists());//~vc4qR~//~vc4sR~
//      DocumentFile doc=tree.createFile("text/plain",member);               //~vc4qR~//~vc4sR~//~v77cR~
        DocumentFile doc=tree.createFile("",member);     //~vc4sI~ //~v77cR~
        if (Dump.Y) Dump.println(CN+"createDocument fpath="+Pfpath+",path="+path+",member="+member+",doc="+doc+",docuri="+doc.getUri());//~vc4sR~//~v77cR~
        return doc;                                              //~vc4qR~//~vc4sR~
    }                                                            //~vc4qR~//~vc4sR~
//********************************************************         //~v77cI~
  	public int mkdirDoc(int Popt,String Pfpath,String PstrUri)     //~v77cI~
    {                                                              //~v77cI~
        if (Dump.Y) Dump.println(CN+"mkdirDoc Popt="+Integer.toHexString(Popt)+",fpath="+Pfpath+",strUri="+PstrUri);//~v77cI~
        if (USAF.isExists(Pfpath,PstrUri))                               //~v77cI~//~v77dR~
        {                                                          //~v77cI~
	        if (Dump.Y) Dump.println(CN+"mkdirDoc EEXIST="+Pfpath);//~v77cI~
            return EEXIST;                                         //~v77cI~
        }                                                          //~v77cI~
        String[] pathAndMember=new String[2];                      //~v77cI~
        int lvl=getPathLevel(Pfpath,pathAndMember);                //~v77cI~
        if (lvl==0)                                                //~v77cI~
            return EINVAL;                                         //~v77cI~
        String path=pathAndMember[0];                              //~v77cI~
        String member=pathAndMember[1];                            //~v77cI~
        Uri uriParent=makeUriPath(PstrUri,lvl,path);               //~v77cI~
        DocumentFile tree=getDocumentTree(uriParent);              //~v77cI~
        if (Dump.Y) Dump.println(CN+"mkdirDoc parent canRead="+tree.canRead()+",canWrite="+tree.canWrite()+",name="+tree.getName()+",type="+tree.getType()+",isDirectory="+tree.isDirectory()+",exists="+tree.exists());//~v77cR~
        if (!tree.exists())                                       //~v77cR~
        {                                                          //~v77cI~
	        if (Dump.Y) Dump.println(CN+"mkdirDoc tree ERROR_PATH_NOT_FOUND="+path);//~v77cI~
        	return ERROR_PATH_NOT_FOUND;                           //~v77cI~
        }                                                          //~v77cI~
        if (!USAF.isDirectory(Pfpath,tree))                        //~v77cI~
        {                                                          //~v77cI~
	        if (Dump.Y) Dump.println(CN+"mkdirDoc tree ENOTDIR path="+path);//~v77cI~
            return ENOTDIR;                                        //~v77cI~
        }                                                          //~v77cI~
        DocumentFile doc=tree.createDirectory(member);             //~v77cI~
        if (Dump.Y) Dump.println(CN+"mkdirDoc member="+member+",doc="+doc);//~v77cR~
        if (doc==null)                                             //~v77cI~
            return EINTR;                                          //~v77cI~
        return 0;                                                  //~v77cI~
    }                                                              //~v77cI~
//********************************************************         //~v77dI~
	private Uri getParentUri(String Pfpath,String PstrUri)      //~v77dI~
    {                                                              //~v77dI~
        String[] pathAndMember=new String[2];                      //~v77dI~
        int lvl=getPathLevel(Pfpath,pathAndMember);                //~v77dI~
        if (lvl==0)                                                //~v77dI~
            return null;                                           //~v77dI~
        String path=pathAndMember[0];                              //~v77dI~
        String member=pathAndMember[1];                            //~v77dI~
        Uri uriParent=makeUriPath(PstrUri,lvl,path);               //~v77dI~
        if (Dump.Y) Dump.println(CN+"getParentUri Pfpath="+Pfpath+",parentUri="+uriParent);//~v77dI~
        return uriParent;                                          //~v77dI~
    }                                                              //~v77dI~
//********************************************************         //~v77dI~
	private DocumentFile getParentDoc(String Pfpath,String PstrUri)      //~v77dI~
    {                                                              //~v77dI~
        DocumentFile doc=null;                                     //~v77dI~
		Uri uri=getParentUri(Pfpath,PstrUri);                   //~v77dI~
        if (uri!=null)                                             //~v77dI~
        {                                                          //~v77dI~
        	doc=getDocumentTree(uri);                              //~v77dI~
        }                                                          //~v77dI~
        if (Dump.Y) Dump.println(CN+"getParentDoc Pfpath="+Pfpath+",doc="+doc);//~v77dI~
        return doc;                                          //~v77dI~
    }                                                              //~v77dI~
//********************************************************         //~v77dI~
  	public int rmdirDoc(int Popt,String Pfpath,String PstrUri)     //~v77dI~
    {                                                              //~v77dI~
        if (Dump.Y) Dump.println(CN+"rmdirDoc Popt="+Integer.toHexString(Popt)+",fpath="+Pfpath+",strUri="+PstrUri);//~v77dI~
        DocumentFile docItself=USAF.getDocumentItself(Popt,PstrUri,Pfpath);//~v77dI~
        DocumentFile docParent=getParentDoc(Pfpath,PstrUri);       //~v77dI~
//      if (docItself==null|| docParent==null)                     //~v77dI~//~vbyfR~
        if (docItself==null)    //allow delete top dir             //~vbyfI~
            return EINVAL;                                         //~v77dI~
        if (!USAF.isExists(Pfpath,docItself))                       //~v77dI~
        {                                                          //~v77dI~
            if (!USAF.isExists(Pfpath,docParent))                   //~v77dI~
            {                                                      //~v77dI~
                if (Dump.Y) Dump.println(CN+"rmdirDoc tree ERROR_PATH_NOT_FOUND="+Pfpath);//~v77dI~
                return ERROR_PATH_NOT_FOUND;                       //~v77dI~
            }                                                      //~v77dI~
	        if (Dump.Y) Dump.println(CN+"rmdirDoc ENOENT="+Pfpath);//~v77dI~
            return ENOENT;                                         //~v77dI~
        }                                                          //~v77dI~
        if (!USAF.isDirectory(Pfpath,docItself))                   //~v77dI~
        {                                                          //~v77dI~
	        if (Dump.Y) Dump.println(CN+"rmdirDoc itself ENOTDIR path="+Pfpath);//~v77dI~
            return ENOTDIR;                                        //~v77dI~
        }                                                          //~v77dI~
        boolean rc=docItself.delete();                              //~v77dI~
        if (Dump.Y) Dump.println(CN+"rmdirDoc rc="+rc+",doc="+docItself);//~v77dI~
        if (!rc)                                                   //~v77dI~
            return EINTR;                                          //~v77dI~
        return 0;                                                  //~v77dI~
    }                                                              //~v77dI~
//********************************************************         //~v77dI~
//*no use; fd is not sharable                                      //~v77dI~
//********************************************************         //~v77dI~
    private ParcelFileDescriptor openDescriptor(String Pfpath,Uri Puri,String Pmode)//~v77dI~
    {                                                              //~v77dI~
	    ParcelFileDescriptor pfd=null;                             //~v77dI~
        errno=0;                                                   //~vbyfI~
        if (Dump.Y) Dump.println(CN+"openDescripror fpath="+Pfpath+",uri="+Puri);//~v77dI~//~v77hR~
        try                                                        //~v77dI~
        {                                                          //~v77dI~
          if (!isExist(Pfpath,Puri))                               //~vc5iI~
          {                                                        //~vc5iI~
          	if (Dump.Y) Dump.println(CN+"openDescriptor rc="+ENOENT+"(ENOENT)");//~vc5iI~
            errno=ENOENT;                                          //~vc5iI~
          }                                                        //~vc5iI~
          else                                                     //~vc5iI~
	      	pfd=CR.openFileDescriptor(Puri,Pmode);                 //~v77dI~
        }                                                          //~v77dI~
        catch(FileNotFoundException e)                             //~v77dI~
        {                                                          //~v77dI~
        	Dump.printlnNoMsg(e,CN+"openDescriptor FileNotFound fpath="+Pfpath+",uri="+Puri);//~v77dI~//~v4cwR~//~vbyfR~
            errno=ENOENT;                                          //~vbyfI~
        }                                                          //~v77dI~
        catch(IllegalArgumentException e)                          //~v77hI~
        {                                                          //~v77hI~
        	Dump.printlnNoMsg(e,CN+"openDescriptor IllegalArgument fpath="+Pfpath+",uri="+Puri);//~v77hI~//~vbyfR~
        	if (e.toString().contains("FileNotFoundException"))      //~vbyfI~
            	errno=ENOENT;                                      //~vbyfI~
            else                                                   //~vbyfI~
	            errno=EINVAL;                                      //~vbyfR~
        }                                                          //~v77hI~
        catch(SecurityException e)                                 //~vbyfI~
        {                                                          //~vbyfI~
        	Dump.printlnNoMsg(e,CN+"openDescriptor SecurityException:"+Pfpath+",uri="+Puri);//~vbyfI~
            errno=EACCES;  //13                                    //~vbyfR~
        }                                                          //~vbyfI~
        catch(Exception e)                                         //~vbyfI~
        {                                                          //~vbyfI~
        	Dump.printlnNoMsg(e,CN+"openDescriptor OtherException fpath="+Pfpath+",uri="+Puri);//~vbyfI~
        }                                                          //~vbyfI~
        if (Dump.Y) Dump.println(CN+"openDescriptor pfd="+pfd+",fpath="+Pfpath+",uri="+Puri);//~v77dI~//~v77hR~
        return pfd;                                                //~v77dI~
	}                                                              //~v77dI~
//********************************************************         //~vc5mI~
    private Uri strUriToUri(String Pfpath,String PstrUri)          //~vc5mI~
    {                                                              //~vc5mI~
      	Uri uri;                                                   //~vc5mI~
      	if (Pfpath.endsWith("/"))                                  //~vc5mI~
      		uri=USAF.makeUriItself(PstrUri,Pfpath.substring(0,Pfpath.length()-1));//return err for wildcard//~vc5mI~
      	else                                                       //~vc5mI~
      		uri=USAF.makeUriItself(PstrUri,Pfpath);//return err for wildcard//~vc5mI~
        if (Dump.Y) Dump.println(CN+"strUriToUri path="+Pfpath+",strUri="+PstrUri+",uri="+uri);//~vc5mI~
        return uri;                                                //~vc5mI~
    }                                                              //~vc5mI~
//********************************************************         //~v77dI~
  	public int opendirDoc(int Popt,String Pfpath,String PstrUri)   //~v77dI~
    {                                                              //~v77dI~
        if (Dump.Y) Dump.println(CN+"opendirDoc Popt="+Integer.toHexString(Popt)+",fpath="+Pfpath+",strUri="+PstrUri);//~v77dI~
//      Uri uri=USAF.makeUriPath(PstrUri,Pfpath);                  //~v77dI~//~v77hR~
      	Uri uri;                                                   //~vbyhI~
//    if (Pfpath.endsWith("/"))                                    //~vbyhI~//~vc5mR~
//    	uri=USAF.makeUriItself(PstrUri,Pfpath.substring(0,Pfpath.length()-1));//return err for wildcard//~vbyhR~//~vc5mR~
//    else                                                         //~vbyhI~//~vc5mR~
//    	uri=USAF.makeUriItself(PstrUri,Pfpath);//return err for wildcard//~v4cwR~//~vbyhR~//~vc5mR~
      	uri=strUriToUri(Pfpath,PstrUri);                           //~vc5mI~
        ParcelFileDescriptor pfd=openDescriptor(Pfpath,uri,"r");   //~v77dI~
//      Spfd=pfd;   //TODO test                                    //~v77dR~
        if (pfd==null)                                             //~v77dI~
        {                                                          //~vbyfI~
        	if (errno!=0)                                          //~vbyfR~
            	return errno;                                      //~vbyfI~
            return ENOENT;                                         //~v77dI~
        }                                                          //~vbyfI~
//      int fd=pfd.getFd();                                        //~v77dR~//~v77hR~
        int fd=pfd.detachFd();                                     //~v4cvR~
        if (Dump.Y) Dump.println(CN+"opendirDoc getFd   ="+fd);    //~v77dR~
	  	AxeJNI.notify_opendirDocResult(Popt,Pfpath,fd);            //~v77dI~
//      close(Pfpath,pfd);                                         //~v77dR~
        if (Dump.Y) Dump.println(CN+"opendirDoc rc=0,fd="+fd);  //~v77dI~
        return 0;                                                  //~v77dI~
    }                                                              //~v77dI~
//********************************************************         //~v4cvI~
  	public int statDoc(int Popt,String Pfpath,String PstrUri)      //~v4cvI~
    {                                                              //~v4cvI~
        if (Dump.Y) Dump.println(CN+"statDoc Popt="+Integer.toHexString(Popt)+",fpath="+Pfpath+",strUri="+PstrUri);//~v4cvI~
      	Uri uri=USAF.makeUriItself(PstrUri,Pfpath);                //~vc5eR~
        ParcelFileDescriptor pfd=openDescriptor(Pfpath,uri,"r");   //~v4cvI~
        if (pfd==null)                                             //~v4cvI~
        {                                                          //~vbyfI~
        	if (errno!=0)                                          //~vbyfI~
            	return errno;                                      //~vbyfI~
            return ENOENT;                                         //~v4cvI~
        }                                                          //~vbyfI~
        int fd=pfd.detachFd();                                     //~v4cvI~
        if (Dump.Y) Dump.println(CN+"statDoc getFd   ="+fd);       //~v4cvI~
	  	AxeJNI.notify_statDocResult(Popt,Pfpath,fd);               //~v4cvI~
        if (Dump.Y) Dump.println(CN+"statDoc rc=0,fd="+fd);        //~v4cvI~
        return 0;                                                  //~v4cvI~
    }                                                              //~v4cvI~
//********************************************************         //~v77dI~
private int close(String Pfpath,ParcelFileDescriptor Ppfd)         //~v77dI~
{                                                                  //~v77dI~
	int rc=0;                                                      //~v77dI~
    if (Dump.Y) Dump.println(CN+"close ParcelFileDescriptor fpath="+Pfpath);//~v77dI~
	try                                                            //~v77dI~
    {                                                              //~v77dI~
        Ppfd.close();                                               //~v77dI~
    }                                                              //~v77dI~
    catch(IOException e)                                           //~v77dI~
    {                                                              //~v77dI~
        Dump.println(e,CN+"close ParcelFileDescriptor fpath="+Pfpath);//~v77dI~
    	rc=EINTR;                                                  //~v77dI~
    }                                                              //~v77dI~
    return rc;                                                     //~v77dI~
}                                                                  //~v77dI~
//********************************************************         //~v77dR~
  	public int unlinkDoc(int Popt,String Pfpath,String PstrUri)    //~v77dR~
    {                                                              //~v77dR~
        if (Dump.Y) Dump.println(CN+"unlinkDoc Popt="+Integer.toHexString(Popt)+",fpath="+Pfpath+",strUri="+PstrUri);//~v77dR~
        String[] pathAndMember=new String[2];                      //~v77dR~
        int lvl=getPathLevel(Pfpath,pathAndMember);                //~v77dR~
        if (lvl==0)                                                //~v77dR~
            return EINVAL;                                         //~v77dR~
        String path=pathAndMember[0];                              //~v77dR~
        String member=pathAndMember[1];                            //~v77dR~
        Uri uriParent=makeUriPath(PstrUri,lvl,path);               //~v77dR~
        DocumentFile docTree=getDocumentTree(uriParent);           //~v77dR~
        if (Dump.Y) Dump.println(CN+"unlinkDoc docTree uri="+docTree.getUri());//~v77dR~
        if (Dump.Y) Dump.println(CN+"unlinkDoc parent canRead="+docTree.canRead()+",canWrite="+docTree.canWrite()+",name="+docTree.getName()+",type="+docTree.getType()+",isDirectory="+docTree.isDirectory()+",exists="+docTree.exists());//~v77dR~
        if (!docTree.exists())                                     //~v77dR~
        {                                                          //~v77dR~
	        if (Dump.Y) Dump.println(CN+"unlinkDoc tree ERROR_PATH_NOT_FOUND="+path);//~v77dR~
        	return ERROR_PATH_NOT_FOUND;                           //~v77dR~
        }                                                          //~v77dR~
        if (!docTree.isDirectory())                                //~v77dR~
        {                                                          //~v77dR~
	        if (Dump.Y) Dump.println(CN+"unlinkDoc path is not Dir ERROR_PATH_NOT_FOUND path="+path);//~v77dR~
        	return ERROR_PATH_NOT_FOUND;                           //~v77dR~
        }                                                          //~v77dR~
//    	Uri uriMember=USAF.makeUriPath(PstrUri,Pfpath);            //~v77dR~//~v4cvR~
      	Uri uriMember=USAF.makeUriItself(PstrUri,Pfpath);          //~v4cvI~
        int rc=deleteDocument(Pfpath,uriMember);                   //~v77dR~
        if (Dump.Y) Dump.println(CN+"unlinkDoc member="+member+",fpath="+Pfpath);//~v77dR~
        return 0;                                                  //~v77dR~
    }                                                              //~v77dR~
//********************************************************         //~v77dR~
    private int deleteDocument(String Pfpath,Uri Puri)             //~v77dR~
    {                                                              //~v77dR~
    	int rc=0;                                                  //~v77dR~
	    if (Dump.Y) Dump.println(CN+"deleteDocument fpath="+Pfpath+",uri="+Puri);//~v77dR~
        try                                                        //~v77dR~
        {                                                          //~v77dR~
        	boolean brc=DocumentsContract.deleteDocument(CR,Puri); //~v77dR~
            if (!brc)                                              //~v77dR~
                rc=EINTR;                                          //~v77dR~
        }                                                          //~v77dR~
        catch(FileNotFoundException e)                             //~v77dR~
        {                                                          //~v77dR~
        	Dump.println(e,CN+"deleteDocument fpath="+Pfpath+",uri="+Puri);//~v77dR~
            rc=ENOENT;                                             //~v77dR~
        }                                                          //~v77dR~
        if (Dump.Y) Dump.println(CN+"deleteDoc rc="+rc+",fpath="+Pfpath+",uri="+Puri);//~v77dR~
        return rc;                                                 //~v77dR~
    }                                                              //~v77dR~
//********************************************************         //~vc4qM~
    private boolean deleteDocument(Uri Puri)                       //~vc4qI~
    {                                                              //~vc4qM~
    	boolean rc=false;                                          //~vc4qM~
	    if (Dump.Y) Dump.println(CN+"deleteDocument uri="+Puri);//~vc4qM~
		DocumentFile doc=getDocumentSingle(Puri);                       //~vc4qI~//~vc4sR~
	    rc=deleteDocument(doc);                                     //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"deleteDocument rc="+rc);      //~vc4qM~
        return rc;                                                 //~vc4qM~
    }                                                              //~vc4qM~
//********************************************************         //~vc4qI~
    private boolean deleteDocument(DocumentFile Pdoc)              //~vc4qI~
    {                                                              //~vc4qI~
    	boolean rc=false;                                          //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"deleteDocument doc="+Pdoc);   //~vc4qI~
        if (Pdoc.exists())                                         //~vc4qI~
        {                                                          //~vc4qI~
	    	if (Dump.Y) Dump.println(CN+"deleteDocument exist");   //~vc4qI~
        	Pdoc.delete();                                          //~vc4qI~
            rc=true;                                               //~vc4qI~
        }                                                          //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"deleteDocument rc="+rc+",doc="+Pdoc);//~vc4qI~
        return rc;                                                 //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~v77gI~
  	public int renameDoc(int Popt,String Pfpath,String PstrUri,String Pnew)//~v77gI~
    {                                                              //~v77gI~
        if (Dump.Y) Dump.println(CN+"renameDoc Popt="+Integer.toHexString(Popt)+",fpath="+Pfpath+",new="+Pnew+",strUri="+PstrUri);//~v77gI~
        DocumentFile docItself=USAF.getDocumentItselfTree(Popt,PstrUri,Pfpath);//~v77gR~
        DocumentFile docParent=getParentDoc(Pfpath,PstrUri);       //~v77gI~
        if (docItself==null|| docParent==null)                     //~v77gI~
            return EINVAL;                                         //~v77gI~
        if (!USAF.isExists(Pfpath,docItself))                      //~v77gI~
        {                                                          //~v77gI~
            if (!USAF.isExists(Pfpath,docParent))                  //~v77gI~
            {                                                      //~v77gI~
                if (Dump.Y) Dump.println(CN+"renameDoc tree ERROR_PATH_NOT_FOUND="+Pfpath);//~v77gI~
                return ERROR_PATH_NOT_FOUND;                       //~v77gI~
            }                                                      //~v77gI~
	        if (Dump.Y) Dump.println(CN+"renameDoc ENOENT="+Pfpath);//~v77gI~
            return ENOENT;                                         //~v77gI~
        }                                                          //~v77gI~
        String membNew;                                            //~v77gI~
        int offs=Pnew.lastIndexOf('/');                            //~v77gR~
        if (offs<0)                                                //~v77gI~
        	membNew=Pnew;                                          //~v77gI~
        else                                                       //~v77gI~
	        membNew=Pnew.substring(offs+1);                        //~v77gR~
        if (Dump.Y) Dump.println(CN+"membNew="+membNew);           //~v77gR~
    	Uri uriNew=strUriToUri(Pnew,PstrUri);                      //~vc5mI~
        DocumentFile docNew=USAF.getDocumentItself(Popt,PstrUri,Pnew);//~vc5mI~
        if (isExist(Pnew,uriNew))                                  //~vc5mI~
        {                                                          //~vc5mI~
	        if (Dump.Y) Dump.println(CN+"renameDoc delete existing new="+Pnew);//~vc5mI~
			if (!docNew.delete())                                  //~vc5mI~
            {                                                      //~vc5mI~
		        if (Dump.Y) Dump.println(CN+"renameDoc failed delete existing new="+Pnew);//~vc5mI~
	            return EINTR;                                      //~vc5mI~
            }                                                      //~vc5mI~
        }                                                          //~vc5mI~
        boolean rc=docItself.renameTo(membNew);                    //~v77gR~
        if (Dump.Y) Dump.println(CN+"renameDoc rc="+rc+",fpath="+Pfpath+",new="+Pnew);//~v77gI~
        if (!rc)                                                   //~v77gI~
            return EINTR;                                          //~v77gI~
        return 0;                                                  //~v77gI~
    }                                                              //~v77gI~
//********************************************************         //~vc4qI~
	private void writeDocument(Uri Puri,String Pdata)              //~vc4qI~
    {                                                              //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"writeDocument uri="+Puri+",data="+Pdata);//~vc4qI~
        try                                                        //~vc4qI~
        {                                                          //~vc4qI~
        	OutputStream os=CR.openOutputStream(Puri);             //~vc4qI~
			OutputStreamWriter osw=new OutputStreamWriter(os,"UTF-8");//~vc4qI~
			PrintWriter pw=new PrintWriter(osw,true/*autoFlush*/); //~vc4qI~
        	pw.print(Pdata);                                       //~vc4qI~
            pw.close();                                            //~vc4qI~
        }                                                          //~vc4qI~
        catch(FileNotFoundException e)                             //~vc4qI~
        {                                                          //~vc4qI~
        	Dump.println(e,CN+"writeDocument FileNotFound:"+Puri); //~vc4qI~
        }                                                          //~vc4qI~
        catch(IOException e)                                       //~vc4qI~
        {                                                          //~vc4qI~
        	Dump.println(e,CN+"writeDocument IOException:"+Puri);  //~vc4qI~
        }                                                          //~vc4qI~
	    if (Dump.Y) Dump.println(CN+"writeDocument exit");         //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~v77hI~
  	public int copyDoc(int Popt,String Psrc,String PstrUriS,String Ptgt,String PstrUriT)//~v77hI~
    {                                                              //~v77hI~
    	int rc,rcOpt;                                              //~v77hI~
    	Uri uri=null;                                              //~v77hI~
        byte[] rcByte=null;                                        //~v77hI~
	    if (Dump.Y) Dump.println(CN+"copyDoc opt="+Integer.toHexString(Popt)+",src="+Psrc+",tgt="+Ptgt+",PstrUriS="+PstrUriS+",PstrUriT="+PstrUriT);//~v77hI~//~vbyfR~
	    BufferedInputStream bis=openCopyDocInput(Popt,Psrc,PstrUriS);//~v77hI~
        if (bis==null)                                             //~v77hI~
        {                                                          //~v77hI~
	  		AxeJNI.notify_copyDocResult(Popt,Psrc,Ptgt,errno,0);             //~v77hI~
            return -1;                                             //~v77hI~
        }                                                          //~v77hI~
	    BufferedOutputStream bos=openCopyDocOutput(Popt,Ptgt,PstrUriT);//~v77hI~
        if (bos==null)                                             //~v77hI~
        {                                                          //~v77hI~
        	close(bis,Psrc,0);                                     //~v77hR~
	  		AxeJNI.notify_copyDocResult(Popt,Psrc,Ptgt,0,errno);             //~v77hI~
            return -1;                                             //~v77hI~
        }                                                          //~v77hI~
        int offs=0;                                                //~v77hI~
        boolean swErr=false;                                       //~v77hI~
        for (;;)                                                   //~v77hI~
        {                                                          //~v77hI~
        	int len;                                               //~v77hI~
            len=readByte(bis,workByteBuffer,Psrc,0/*offsInBuff*/,READ_BUFFSZ);//~v77hI~
            if (len==RC_IOE)                                       //~v77hI~
            {                                                      //~v77hI~
            	swErr=true;                                        //~v77hI~
                break;                                             //~v77hI~
            }                                                      //~v77hI~
            if (len<=0)//EOF                                       //~v77hI~
                break;                                             //~v77hI~
//          swErr=writeByte(bos,workByteBuffer,offs,len,Ptgt);     //~v77hI~//~vc5dR~
            swErr=writeByte(bos,workByteBuffer,0/*offs*/,len,Ptgt);//~vc5dI~
            if (swErr)                                             //~v77hI~
                break;                                             //~v77hI~
            offs+=len;                                             //~v77hI~
        }                                                          //~v77hI~
        swErr|=close(bis,Psrc,offs);                               //~v77hI~
        swErr|=close(bos,Ptgt,offs);                               //~v77hI~
        if (swErr)                                                 //~v77hI~
        {                                                          //~v77hI~
		    if (Dump.Y) Dump.println(CN+"copyDoc exit failed rc=-1");//~v77hI~
            return -1;                                             //~v77hI~
        }                                                          //~v77hI~
//      copyPathInfo(Popt,lastModifiedOpenCopyDocInput,Ptgt,PstrUriT);//~vc5nR~
	    if (Dump.Y) Dump.println(CN+"copyDoc exit rc=0");          //~v77hI~
        return 0;                                                  //~v77hI~
    }                                                              //~v77hI~
//********************************************************         //~v77hI~
	private BufferedInputStream openCopyDocInput(int Popt,String Psrc,String PstrUri)//~v77hI~
    {                                                              //~v77hI~
    	if (Dump.Y) Dump.println(CN+"openCopyDocInput src="+Psrc+",strUri="+PstrUri);//~v77hR~
		BufferedInputStream bis=null;                              //~v77hI~
        InputStream is;
        if (PstrUri==null)                                         //~v77hI~
        {                                                          //~v77hI~
			is=openFileInputStream(Psrc);          //~v77hI~
            lastModifiedOpenCopyDocInput=lastModifiedOpenFileInputStream;   //millisec from 1970//~vc5nI~
        }                                                          //~v77hI~
        else                                                       //~v77hI~
        {                                                          //~v77hI~
            DocumentFile doc=USAF.getDocumentItself(Popt,PstrUri,Psrc);//~v77hI~
            lastModifiedOpenCopyDocInput=doc.lastModified();    //millisec from 1970//~vc5nI~
            Uri uri=doc.getUri();                                  //~v77hI~
            is=openInputStream(uri);                   //~v77hI~
        }                                                          //~v77hI~
        if (is == null)                                            //~v77hI~
        {                                                          //~v77hI~
        	errno=ENOENT;                                          //~v77hI~
            return null;                                           //~v77hI~
        }                                                          //~v77hI~
        bis=new BufferedInputStream(is);                           //~v77hI~
    	if (Dump.Y) Dump.println(CN+"openCopyDocInput src="+Psrc+",bis="+bis);//~v77hR~
        return bis;                                                //~v77hI~
    }                                                              //~v77hI~
//********************************************************         //~v77hI~
	private BufferedOutputStream openCopyDocOutput(int Popt,String Ptgt,String PstrUri)//~v77hI~
    {                                                              //~v77hI~
    	if (Dump.Y) Dump.println(CN+"openCopyDocOutput Popt="+Integer.toHexString(Popt)+",tgt="+Ptgt+",strUri="+PstrUri);//~v77hR~//~vc5kR~//~vc5nR~
		BufferedOutputStream bos=null;                             //~v77hI~
        OutputStream os;
        boolean swAppend=(Popt & UFCDO_APPEND)!=0;                 //~vc5kI~
        if (PstrUri==null)                                         //~v77hI~
        {                                                          //~v77hI~
//          boolean swAppend=(Popt & UFCDO_APPEND)!=0;             //~v77hI~//~vc5kR~
			os=openFileOutputStream(Ptgt,swAppend);                //~v77hR~
        }                                                          //~v77hI~
        else                                                       //~v77hI~
        {                                                          //~v77hI~
            DocumentFile doc=USAF.getDocumentItself(Popt,PstrUri,Ptgt);//~v77hI~
            if (!USAF.isExists(Ptgt,doc))                               //~v77hI~
            	doc=createDocument(Popt,Ptgt,PstrUri);              //~v77hI~
            else                                                   //~vc5kI~
            {                                                      //~vc5kI~
              if (!swAppend)                                       //~vc5kI~
              {                                                    //~vc5kI~
    			if (Dump.Y) Dump.println(CN+"openCopyDocOutput delete by !swAppend");//~vc5kI~
		        if (doc.delete())                                  //~vc5kI~
                {                                                  //~vc5kI~
	    			if (Dump.Y) Dump.println(CN+"openCopyDocOutput delete success, createDoc");//~vc5kI~
	            	doc=createDocument(Popt,Ptgt,PstrUri);         //~vc5kI~
                    if (doc==null)                                 //~vc5kI~
                    {                                              //~vc5kI~
				    	if (Dump.Y) Dump.println(CN+"openCopyDocOutput failed to create after delete existing");//~vc5kI~
                        errno=EINTR;                               //~vc5kI~
    	        		return null;                               //~vc5kI~
                    }                                              //~vc5kI~
                }                                                  //~vc5kI~
                else                                               //~vc5kI~
                {                                                  //~vc5kI~
			    	if (Dump.Y) Dump.println(CN+"openCopyDocOutput failed to delete existing");//~vc5kI~
                    errno=EINTR;                                   //~vc5kI~
            		return null;                                   //~vc5kI~
                }                                                  //~vc5kI~
              }                                                    //~vc5kI~
            }                                                      //~vc5kI~
            Uri uri=doc.getUri();                                  //~v77hI~
//          String openopt=(Popt & UFCDO_APPEND)!=0 ? "wa" : "w";  //~v77hI~//~vc5kR~
            String openopt=swAppend ? "wa" : "w";                  //~vc5kI~
            os=openOutputStream(uri,openopt);                      //~v77hR~
        }                                                          //~v77hI~
        if (os==null)                                              //~v77hR~
        {                                                          //~v77hI~
//      	errno=ENOENT;                                          //~v77hI~//~vc5jR~
            errno=EINTR;                                           //~vc5kI~
            return null;                                           //~v77hI~
        }                                                          //~v77hI~
        bos=new BufferedOutputStream(os);                          //~v77hI~
    	if (Dump.Y) Dump.println(CN+"openCopyDocOutput tgt="+Ptgt+",bos="+bos);//~v77hR~//~vc5dR~
        return bos;                                                //~v77hI~
    }                                                              //~v77hI~
//********************************************************         //~vc5nI~
//*not called, for Document file output                            //~vc5nI~
//*for File set lastModified at ufile2l.copyPathInfo               //+vc5nR~
//********************************************************         //~vc5nI~
	private boolean copyPathInfo(int Popt,long PlastModified,String Ptgt,String PstrUri)//~vc5nI~
    {                                                              //~vc5nI~
    	if (Dump.Y) Dump.println(CN+"openPathInfo Popt="+Integer.toHexString(Popt)+",lastModified="+Long.toHexString(PlastModified)+",tgt="+Ptgt+",strUri="+PstrUri);//~vc5nI~
        boolean rc;                                                //~vc5nI~
        boolean swAppend=(Popt & UFCDO_APPEND)!=0;                 //~vc5nI~
        if (swAppend)                                              //~vc5nI~
        {                                                          //~vc5nI~
   		 	if (Dump.Y) Dump.println(CN+"openPathInfo rc=true by append mode");//~vc5nI~
        	return true;                                           //~vc5nR~
        }                                                          //~vc5nI~
        if (PstrUri==null)                                         //~vc5nI~
        {                                                          //~vc5nI~
        	try                                                    //~vc5nI~
            {                                                      //~vc5nI~
	    		File f=new File(Ptgt);                             //~vc5nI~
				rc=f.setLastModified(PlastModified);               //~vc5nI~
		    	if (Dump.Y) Dump.println(CN+"openPathInfo output is File rc="+rc+",setLastModified="+Long.toHexString(PlastModified)+",tgt="+Ptgt+",strUri="+PstrUri);//~vc5nI~
            }                                                      //~vc5nI~
            catch (Exception e)                                    //~vc5nI~
            {                                                      //~vc5nI~
				Dump.printlnNoMsg(e,CN+"setLastModified lastModified="+Long.toHexString(PlastModified)+",tgt="+Ptgt);//~vc5nI~
            	rc=false;                                          //~vc5nI~
            }                                                      //~vc5nI~
        }                                                          //~vc5nI~
        else                                                       //~vc5nI~
        {                                                          //~vc5nI~
        	rc=false;                                              //~vc5nI~
		    if (Dump.Y) Dump.println(CN+"openPathInfo output is Document no api to set last modified");//~vc5nI~
        }                                                          //~vc5nI~
    	if (Dump.Y) Dump.println(CN+"copyPathInfo rc="+rc+",tgt="+Ptgt);//~vc5nI~
        return rc;                                                //~vc5nI~
    }                                                              //~vc5nI~
//********************************************************         //~vc4qI~
//* from UFile.fileToStringBuffer                                  //~vc4qI~
//********************************************************         //~vc4qI~
//    public StringBuffer fileToStringBuffer(String Pmember)       //~vc4qR~
//    {                                                            //~vc4qR~
//        if (Dump.Y) Dump.println(CN+"fileToStringBuffer Member="+Pmember);//~vc4qR~
//        InputStream is=openInputDocument(Pmember);               //~vc4qR~
//        StringBuffer sb=new StringBuffer();                      //~vc4qR~
//        if (is == null)                                          //~vc4qR~
//            return null;                                         //~vc4qR~
//        int lineno=0;                                            //~vc4qR~
//        try                                                      //~vc4qR~
//        {                                                        //~vc4qR~
//            InputStreamReader isr=new InputStreamReader(is);     //~vc4qR~
//            BufferedReader br=new BufferedReader(isr);           //~vc4qR~
//            for (;;)                                             //~vc4qR~
//            {                                                    //~vc4qR~
//                String line=br.readLine();                       //~vc4qR~
//                if (line==null)                                  //~vc4qR~
//                    break;                                       //~vc4qR~
//                lineno++;                                        //~vc4qR~
//                sb.append(line+"\n");                            //~vc4qR~
//                if (Dump.Y) Dump.println(CN+"fileToStringBuffer lineno="+lineno+"="+line);//~vc4qR~
//            }                                                    //~vc4qR~
//            br.close();                                          //~vc4qR~
//        }                                                        //~vc4qR~
//        catch(FileNotFoundException e)                           //~vc4qR~
//        {                                                        //~vc4qR~
//            Dump.println(e,CN+"fileToStringBuffer FileNotFound:"+saveDirTop+"/"+Pmember);//~vc4qR~
//        }                                                        //~vc4qR~
//        catch(IOException e)                                     //~vc4qR~
//        {                                                        //~vc4qR~
//            Dump.println(e,CN+"fileToStringBuffer IOException:"+saveDirTop+"/"+Pmember);//~vc4qR~
//        }                                                        //~vc4qR~
//        if (Dump.Y) Dump.println(CN+"fileToStringBuffer exit lineno="+lineno+",sb="+sb.toString());//~vc4qR~
//        return sb;                                               //~vc4qR~
//    }                                                            //~vc4qR~
////********************************************************       //~vc4qR~
//    private DocumentFile getMemberDocument(String Pmember)       //~vc4qR~
//    {                                                            //~vc4qR~
//        if (Dump.Y) Dump.println(CN+"getMemberDocument member="+Pmember);//~vc4qR~
//        Uri uri=getMemberUri(Pmember);                           //~vc4qR~
//        DocumentFile doc=uriToDocument(uri);                     //~vc4qR~
//        return doc;                                              //~vc4qR~
//    }                                                            //~vc4qR~
////********************************************************       //~vc4qR~
//    public boolean deleteMember(String Pmember)                  //~vc4qR~
//    {                                                            //~vc4qR~
//        if (Dump.Y) Dump.println(CN+"deleteMember Member="+Pmember);//~vc4qR~
//        DocumentFile doc=getMemberDocument(Pmember);             //~vc4qR~
//        boolean rc=doc.delete();                                 //~vc4qR~
//        if (Dump.Y) Dump.println(CN+"deleteMember rc="+rc);      //~vc4qR~
//        return rc;                                               //~vc4qR~
//    }                                                            //~vc4qR~
//********************************************************         //~vc4qI~
//    public boolean isDocumentExist(String Pfpath)                //~vc4qR~
//    {                                                            //~vc4qR~
//        if (Dump.Y) Dump.println(CN+"isDocumentExist fpath="+Pfpath);//~vc4qR~
//        String member=parseMember(Pfpath);                       //~vc4qR~
//        DocumentFile doc=getMemberDocument(member);              //~vc4qR~
//        boolean rc=doc.exists();                                 //~vc4qR~
//        if (Dump.Y) Dump.println(CN+"isDocumentExist rc="+rc);   //~vc4qR~
//        return rc;                                               //~vc4qR~
//    }                                                            //~vc4qR~
//********************************************************         //~vc4qI~
//*rc 0:copyed,1:already exist,-1:failed                           //~vc4qI~
//********************************************************         //~vc4qI~
//    public int writeDocumentFromFile(String Pfpath)              //~vc4qR~
//    {                                                            //~vc4qR~
//        int rc=-1;                                               //~vc4qR~
//        if (Dump.Y) Dump.println(CN+"writeDocumentFromFile path="+Pfpath);//~vc4qR~
//        if (isDocumentExist(Pfpath))                             //~vc4qR~
//        {                                                        //~vc4qR~
//            if (Dump.Y) Dump.println(CN+"writeDocumentFromFile return target exist"+Pfpath);//~vc4qR~
//            return 1;   //exist means db captured /sdcard/eMahjong/*, no need to copy, do not delete and leave it there//~vc4qR~
//        }                                                        //~vc4qR~
//        PrintWriter pw=openOutputDocumentPrintWriter(Pfpath);    //~vc4qR~
//        if (pw==null)                                            //~vc4qR~
//        {                                                        //~vc4qR~
//            if (Dump.Y) Dump.println(CN+"writeDocumentFromFile rc=-1 by printWriter=null");//~vc4qR~
//            return -1;                                           //~vc4qR~
//        }                                                        //~vc4qR~
//        try                                                      //~vc4qR~
//        {                                                        //~vc4qR~
//            BufferedReader br=new BufferedReader(new FileReader(Pfpath));//~vc4qR~
//            if (Dump.Y) Dump.println(CN+"writeDocumentFromFile bufferedReader="+Utils.toString(br));//~vc4qR~
//            if (br!=null)                                        //~vc4qR~
//            {                                                    //~vc4qR~
//                String line;                                     //~vc4qR~
//                while(true)                                      //~vc4qR~
//                {                                                //~vc4qR~
//                    line=br.readLine();                          //~vc4qR~
//                    if (Dump.Y) Dump.println(CN+"writeDocumentFromFile line="+Utils.toString(line));//~vc4qR~
//                    if (line==null)                              //~vc4qR~
//                        break;                                   //~vc4qR~
//                    pw.println(line);                            //~vc4qR~
//                }                                                //~vc4qR~
//                pw.close();                                      //~vc4qR~
//                rc=0;                                            //~vc4qR~
//            }                                                    //~vc4qR~
//        }                                                        //~vc4qR~
//        catch(FileNotFoundException e)                           //~vc4qR~
//        {                                                        //~vc4qR~
//            Dump.println(e,CN+"writeDocumentFromFile:"+Pfpath);  //~vc4qR~
//        }                                                        //~vc4qR~
//        catch(IOException e)                                     //~vc4qR~
//        {                                                        //~vc4qR~
//            Dump.println(e,CN+"writeDocumentFromFile:"+Pfpath);  //~vc4qR~
//        }                                                        //~vc4qR~
//        if (Dump.Y) Dump.println(CN+"writeDocumentFromFile exit rc="+rc+",path="+Pfpath);//~vc4qR~
//        return rc;                                               //~vc4qR~
//    }                                                            //~vc4qR~
//********************************************************         //~vc4sI~
private void saveOpenedHandle(String Pfpath,int Popt,BufferedInputStream Pbis,BufferedReader Pbr,BufferedOutputStream Pbos,PrintWriter Ppw)//~vc4sR~
{                                                                  //~vc4sI~
    if (Dump.Y) Dump.println(CN+"saveOpenedHandle fpath="+Pfpath); //~vc4sI~
	Map<String,DocHandle> map=mapDocHandle;                        //~vc4sR~
	DocHandle dhOld=map.get(Pfpath);                               //~vc4sR~
    if (dhOld!=null)                                               //~vc4sI~
    {                                                              //~vc4sI~
    	if (Dump.Y) Dump.println(CN+"saveOpenedHandle. remove old="+dhOld);//~vc4sI~
		map.remove(Pfpath);                                       //~vc4sI~
    }                                                              //~vc4sI~
	DocHandle dhNew=new DocHandle(Pfpath,Popt,Pbis,Pbr,Pbos,Ppw);  //~vc4sR~
    map.put(Pfpath,dhNew);                                            //~vc4sI~
}                                                                  //~vc4sI~
//********************************************************         //~vc4sI~
private DocHandle getOpenedHandle(String Pfpath)                   //~vc4sR~
{                                                                  //~vc4sI~
	Map<String,DocHandle> map=AG.aUSAF2.mapDocHandle;              //~vc4sI~
	DocHandle dh=map.get(Pfpath);                                  //~vc4sI~
    if (Dump.Y) Dump.println(CN+"getOpenedHandle dh="+(dh==null?"null":dh));//~vc4sI~
    return dh;                                                     //~vc4sI~
}                                                                  //~vc4sI~
//********************************************************         //~vc4sI~
//********************************************************         //~vc4sI~
    class DocHandle                                                //~vc4sI~
    {                                                              //~vc4sI~
    	public int opt;                                            //~vc4sI~
        public String fpath;                                       //~vc4sI~
	    public BufferedInputStream bis;                            //~vc4sI~
	    public BufferedOutputStream bos;                           //~vc4sI~
		public BufferedReader br;                                  //~vc4sI~
		public PrintWriter pw;                                     //~vc4sI~
        public int lineNo,offs;                                    //~vc4sI~
        public int lenBufferReadByte;                              //~vc4sI~
        public byte[] bufferReadByte;                              //~vc4sI~
    //**************************************************************//~vc4sI~
        public DocHandle(String Pfpath,int Popt,BufferedInputStream Pbis,BufferedReader Pbr,BufferedOutputStream Pbos,PrintWriter Ppw)//~vc4sR~
        {                                                          //~vc4sI~
        	opt=Popt; fpath=Pfpath; bis=Pbis; br=Pbr; bos=Pbos; pw=Ppw;//~vc4sR~
		    if (Dump.Y) Dump.println(CN+"DocHandle Constructor fpath="+fpath+",opt="+Integer.toHexString(opt)+"bis="+bis+",br="+br+",bos="+bos);//~vc4sR~
        }                                                          //~vc4sI~
        public String toString()                                   //~vc4sI~
        {                                                          //~vc4sI~
        	return "fpath="+fpath+",opt="+Integer.toHexString(opt)+",lineno="+lineNo+",offs="+offs+",bis="+bis+",br="+br;//~vc4sR~
        }                                                          //~vc4sI~
        public byte[] getByteBuffer(int Plen)                      //~vc4sI~
        {                                                          //~vc4sI~
        	if (Plen>lenBufferReadByte)                            //~vc4sI~
            	bufferReadByte=new byte[Plen];                     //~vc4sI~
		    if (Dump.Y) Dump.println(CN+"DocHandle getByteBuffer len="+Plen+",buff="+bufferReadByte);//~vc4sI~
        	return bufferReadByte;                                 //~vc4sI~
        }                                                          //~vc4sI~
    }                                                              //~vc4sI~
}//class                                                           //~1110I~
