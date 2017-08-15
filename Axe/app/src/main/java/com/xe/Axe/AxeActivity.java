//CID://+vagFR~: update#=      9                                   //~vagFR~
//*************************************************************    //~va15I~
//vagF:120920 (Axe)local html viewer fail by permission err(uid of process of HtmlViewer was checked)//~vagFI~
//vabe:120126 (Axe)for the case selected xe on selector by "?" cmd, just terminate//~vabeI~
//*************************************************************    //~va15I~
package com.xe.Axe;                                                //~va15I~

import java.io.File;
import java.util.ArrayList;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.webkit.MimeTypeMap;
                                                                   //~1610R~
public class AxeActivity                                           //~1713R~//~1A15R~
    	implements AxeTimerI                                       //~1A18I~
{                                                                  //~va15I~
    private static final String MAIL_TO="To:";                     //~1A15I~
    private static final String MAIL_SUBJECT="Subject:";           //~1A15I~
    private static final String MAIL_TEXT="Text:";                 //~1A15I~
    private static final String MAIL_FILE="File:";                 //~1A16I~
	private static final int MIN_TEXTFILE_SIZE=32768;              //~1A15I~
	private static ArrayList<Uri> Suris;	//attachment file      //~1A16R~
	private static Uri Suritext;                                   //~1A16R~
	private static String mailText,mailSubject;//~1A15R~           //~1A16R~
	private static String[] mailTo;                                //~1A16I~
	private static final int TID_DRAGSEND=1;                       //~1A18I~
    //**************************************                       //~1A06I~//~1A15R~
    //*OpenWith                                                    //~1A06I~//~1A15R~
    //**************************************                       //~1A06I~//~1A15R~
    public static String file2url(String Plocalfile)               //~1A06I~//~1A15R~
    {                                                              //~1A06I~//~1A15R~
        Uri.Builder b=new Uri.Builder();                           //~1A06I~//~1A15R~
        b.scheme("file");                                          //~1A06I~//~1A15R~
        b.path("//"+Plocalfile);                                   //~1A06I~//~1A15R~
        b.build();                                                 //~1A06I~//~1A15R~
        String url=b.toString();                                   //~1A06I~//~1A15R~
        if (Dump.Y) Dump.println("AxeJNI:file2url localfile="+Plocalfile+",url="+url);//~1A06M~//~1A15R~
        return url;                                                //~1A06I~//~1A15R~
    }                                                              //~1A06I~//~1A15R~
    public static String getMimeType(String Purl)                  //~1A06I~
    {                                                              //~1A06I~
        String ext=MimeTypeMap.getFileExtensionFromUrl(Purl);      //~1A06I~
        if (ext==null)                                             //~1A06I~
            return null;                                           //~1A06I~
        ext=ext.toLowerCase();                                     //~1A06I~
        String type=MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext);//~1A06I~
        if (Dump.Y) Dump.println("AxeJNI:getMimeType url="+Purl+",ext="+ext+",mimetype="+type);//~1A06I~
        return type;                                               //~1A06I~
    }                                                              //~1A06I~
    public static int getMimeApplication(String Pmimetype)         //~1A06I~
    {                                                              //~1A06I~
        PackageManager pm=AxeG.context.getPackageManager();        //~1A06I~
        Intent intent=new Intent();                                //~1A06I~
        intent.setAction(Intent.ACTION_VIEW);                      //~1A07R~
//        intent.setAction(Intent.ACTION_MAIN);                    //~1A07R~
        intent.setType(Pmimetype);
        int sz=pm.queryIntentActivities(intent,PackageManager.MATCH_DEFAULT_ONLY).size();//~1A06I~
        if (Dump.Y) Dump.println("AxeJNI:getMimeApplication mime="+Pmimetype+",size="+sz);//~1A06I~
//        if (sz==0)                                               //~1A07R~
//        {                                                        //~1A07R~
//            Intent intent2=Intent.createChooser(intent,Pmimetype);//user selection popup//~1A07R~
//            AxeG.activity.startActivity(intent2);                //~1A07R~
//        }                                                        //~1A07R~
//		ResolveInfo info=pm.resolveActivity(intent,PackageManager.MATCH_DEFAULT_ONLY);//~1A07I~
        return sz;                                                 //~1A06I~
    }                                                              //~1A06I~
    public static int getMimeApplication_dataandtype(String Purl,String Pmimetype)//~1A19I~
    {                                                              //~1A19I~
        PackageManager pm=AxeG.context.getPackageManager();        //~1A19I~
        Intent intent=new Intent();                                //~1A19I~
        intent.setAction(Intent.ACTION_VIEW);                      //~1A19I~
//        intent.setAction(Intent.ACTION_MAIN);                    //~1A19I~
        Uri uri=Uri.parse(Purl);           //file:///mnt/sdcard/Axe/xehelp/..//~1A19I~
        intent.setDataAndType(uri,Pmimetype);                      //~1A19I~
        int sz=pm.queryIntentActivities(intent,PackageManager.MATCH_DEFAULT_ONLY).size();//~1A19I~
        if (Dump.Y) Dump.println("AxeJNI:getMimeApplication_dataandtype uri="+Purl+",mime="+Pmimetype+",size="+sz);//~1A19I~
//        if (sz==0)                                               //~1A19I~
//        {                                                        //~1A19I~
//            Intent intent2=Intent.createChooser(intent,Pmimetype);//user selection popup//~1A19I~
//            AxeG.activity.startActivity(intent2);                //~1A19I~
//        }                                                        //~1A19I~
//		ResolveInfo info=pm.resolveActivity(intent,PackageManager.MATCH_DEFAULT_ONLY);//~1A19I~
        return sz;                                                 //~1A19I~
    }                                                              //~1A19I~
    public static int openWith(String Purl,String Pmimetype)       //~1A06I~
    {                                                              //~1A06I~
        Uri uri;                                                   //~1A06I~
        int rc=0;                                                  //~1A06I~
        Intent intent=null;                                        //~1A06I~
        String label;                                              //~vabeI~
    //***********************  
    	if (Dump.Y) Dump.println("AxeJNI openWith url="+Purl+",mime="+Pmimetype);//~1A06I~
        try                                                        //~1A06I~
        {                                                          //~1A06I~
//            File f=new File(Purl);                               //~1A06R~
//            uri=Uri.fromFile(f);                                 //~1A06R~
		  if (Purl.startsWith("file:")                             //~vagFM~
		  &&  Purl.indexOf("/xehelp/")!=0                          //~vagFM~
          &&  Pmimetype.endsWith("html")                           //~vagFM~
          )                                                        //~vagFM~
			showXeHelp(Purl);                                      //~vagFI~
          else                                                     //~vagFM~
          {                                                        //~vagFM~
            uri=Uri.parse(Purl);           //file:///mnt/sdcard/Axe/xehelp/..//~1A06R~
                                                                   //~1A06R~
            intent=new Intent();                                   //~1A06R~
            intent.setDataAndType(uri,Pmimetype);                  //~1A06R~
            intent.setAction(Intent.ACTION_VIEW);                  //~1A06R~
            int pos=Purl.lastIndexOf('/');                         //~vabeI~
            if (pos>0)                                             //~vabeI~
            	label=Purl.substring(pos+1);                         //~vabeI~
            else                                                   //~vabeI~
            	label=Purl;                                        //~vabeI~
			showChooser(intent,label);                             //~2127I~//~vabeR~
          }                                                        //~vagFI~
        }                                                          //~1A06I~
        catch(Exception e)                                         //~1A06I~
        {                                                          //~1A06I~
            Dump.println(e,"JNI:openWidth");                       //~1A06R~
            rc=4;                                                  //~1A06I~
            if (intent!=null)                                      //~1A06I~
                try                                                //~1A06I~
                {                                                  //~1A06I~
//                    Intent intent2=Intent.createChooser(intent,/*title*/Purl);//user selection popup//~1A06R~//~1A19R~
//                    AxeG.activity.startActivity(intent2);          //~1A06I~//~1A19R~
					showChooser(intent,Purl);                      //~1A19I~
                    rc=1;                                          //~1A06I~
                }                                                  //~1A06I~
                catch(Exception e2)                                 //~1A06I~
                {                                                  //~1A06I~
		            Dump.println(e2,"JNI:openWidth Chooser");       //~1A06I~
                }
        }//~1A06R~
        return rc;                                                 //~1A06R~
    }                                                              //~1A06I~
//**************************************************               //~1A15I~
//*send file to mailer etc                                         //~1A15I~
//*fnm1:filename or null                                           //~1A15I~
//*fnm2:attached filename or @listfile or null                     //~1A15I~
//**************************************************               //~1A15I~
    public static int androSend(int Popt,String Pfnm1,String Pfnm2)//~1A15I~
    {  
    	int rc;//~1A15I~
    //***********************                                      //~1A15I~
        try                                                        //~1A15I~
        {                                                          //~1A15I~
		    rc=actionSend(Popt,Pfnm1,Pfnm2);                       //~1A15I~
        }                                                          //~1A15I~
        catch(Exception e)                                         //~1A15I~
        {                                                          //~1A15I~
            Dump.println(e,"androSend");                           //~1A15I~
            rc=4;                                                  //~1A15I~
        }                                                          //~1A15I~
        mailTo=null;     	//GC                                   //~1A15I~
        mailSubject=null;                                          //~1A15I~
        mailText=null;                                             //~1A15I~
		Suris=null;                                                //~1A15I~
		Suritext=null;                                                //~1A15I~//~1A16R~
        return rc;                                                 //~1A15I~
    }                                                              //~1A15I~
//**************************************************               //~1A18I~
//*send file by DND                                                //~1A18I~
//**************************************************               //~1A18I~
    public static void dragSend(String Plist)                      //~1A18I~
    {                                                              //~1A18I~
    //***********************                                      //~1A18I~
    	if (Dump.Y) Dump.println("AxeActivity dragSend list="+Plist);//~1A18I~
    	String list=new String(Plist);	//once return after schedule timer//~1A18I~
		if (chkAttachmentFromList(list)!=0)                        //~1A19I~
        	return;                                                //~1A19I~
	    AxeTimer t=new AxeTimer(AxeG.axeActivity/*callback*/,1/*milisec delay*/,false/*repeat*/,TID_DRAGSEND,list);//~1A18R~
	    t.start();
    }                                                              //~1A18I~
//**************************************************               //~1A19I~
//*from timer expired                                              //~1A19I~
//**************************************************               //~1A19I~
    private static void dragActionSend()                            //~1A19I~
    {                                                              //~1A19I~
                                                                   //~1A19I~
        int fileno;                                       //~1A19I~
        Intent intent=null;                                        //~1A19I~
    //***********************                                      //~1A19I~
    	if (Dump.Y) Dump.println("AxeActivity dragActionSend");    //~1A19I~
        String choose;                                             //~1A19R~
        fileno=Suris.size();                                       //~1A19I~
        if (fileno==1)  //one file                                 //~1A19I~
        {                                                          //~1A19I~
            intent=new Intent(Intent.ACTION_SEND);                 //~1A19I~
            intent.putExtra(Intent.EXTRA_STREAM,Suris.get(0));  //send a file//~1A19I~
        }                                                          //~1A19I~
        else                                                       //~1A19I~
        {                                                          //~1A19I~
            intent=new Intent(Intent.ACTION_SEND_MULTIPLE);        //~1A19I~
            intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,Suris);//~1A19I~
        }                                                          //~1A19I~
        intent.setType("*/*");                                     //~1A19I~
        choose="DragedFile";                                       //~1A19I~
//        intent.putExtra(Intent.EXTRA_TEXT,text);                 //~1A19R~
//        AxeG.activity.startActivity(Intent.createChooser(intent,choose));//~1A19R~
		showChooser(intent,choose);                                //~1A19I~
    }                                                              //~1A19I~
//**************************************************               //~1A19I~
    private static int actionSend(int Popt,String Pfnm1,String Pfnm2)//~1A15I~
    {                                                              //~1A15I~
                                                   //~1A15I~
        int rc,fileno;                                                    //~1A15I~
        Intent intent=null;
        String choose;                                              //~1A19I~
     //~1A15I~
    //***********************                                      //~1A15I~
    	if (Dump.Y) Dump.println("AxeAction actionSend fnm1="+Pfnm1+",fnm2="+Pfnm2);//~1A15I~//~1A19R~
	    Suris=new ArrayList<Uri>();             //~1A15I~          //~1A16I~
    	fileno=getAttachment(Pfnm2);                              //~1A15I~
    	if (Dump.Y) Dump.println("attachment ctr="+fileno);        //~1A15I~
        if (fileno<0)                                              //~1A15I~
        	return 4;                                              //~1A15I~
    	rc=chkMailText(Pfnm1);                                     //~1A15I~
        if (rc<0)                                                  //~1A15I~
        	return 4;                                              //~1A15I~
        if (rc!=0)	//send fnm1 as stream                          //~1A15I~//~1A16R~
        {                                                          //~1A15I~
        	if (Suritext==null)	//fnm1==null	, fnm2!=null(confirmed at at dcmd_androsend//~1A16R~
            {                                                      //~1A16I~
	        	choose="Send:"+Pfnm2;	                               //~1A16I~//~1A19R~
            	fileno=Suris.size();                               //~1A16I~
                if (fileno==1)	//from @listfile                   //~1A16I~
                {                                                  //~1A16I~
        	 		intent=new Intent(Intent.ACTION_SEND);         //~1A16I~
					intent.putExtra(Intent.EXTRA_STREAM,Suris.get(0));	//send a file//~1A16R~
                }                                                  //~1A16I~
                else                                               //~1A16I~
                {                                                  //~1A16I~
                    intent=new Intent(Intent.ACTION_SEND_MULTIPLE);//~1A16I~
                    intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,Suris);//~1A16I~
                }                                                  //~1A16I~
            }                                                      //~1A16I~
            else                                                   //~1A16I~
            {                                                      //~1A16I~
	        	choose="Send:"+Pfnm1;                                //~1A16I~//~1A19R~
                if (fileno>0)  //pfnm2!=null                                        //~1A15I~//~1A16R~
                {                                                      //~1A15I~//~1A16R~
    //                resid=R.string.Err_FileAndAttachment;              //~1A15I~//~1A16R~
    //                Utils.showToastLong(resid,":"+Pfnm1+","+Pfnm2);          //~1A15I~//~1A16R~
    //                return 4;                                          //~1A15I~//~1A16R~
                    intent=new Intent(Intent.ACTION_SEND_MULTIPLE);//~1A16R~
                    Suris.add(0,Suritext);  //send a file          //~1A16R~
                    intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,Suris);//~1A16R~
                }                                                      //~1A15I~//~1A16R~
                else                                               //~1A16R~
                {                                                  //~1A16R~
                    intent=new Intent(Intent.ACTION_SEND);          //~1A15I~//~1A16R~
                    intent.putExtra(Intent.EXTRA_STREAM,Suritext);  //send a file//~1A15I~//~1A16R~
                }                                                  //~1A16R~
            }                                                      //~1A16I~
        	intent.setType("*/*");                                 //~1A15M~//~1A16R~
//          intent.putExtra(Intent.EXTRA_TEXT,text);     //~1A15I~ //~1A16R~//~1A19R~
//            AxeG.activity.startActivity(Intent.createChooser(intent,choose));//~1A15I~//~1A19R~
			showChooser(intent,choose);                            //~1A19I~
        }                                                          //~1A15I~
        else 	//Text: found                                      //~1A15I~
        {                                                          //~1A15I~
            fileno=Suris.size();
            if (fileno==1)//~1A15I~//~1A16R~
            {                                                      //~1A16I~
        	 	intent=new Intent(Intent.ACTION_SEND);             //~1A16I~
				intent.putExtra(Intent.EXTRA_STREAM,Suris.get(0));	//send a file//~1A15I~//~1A16R~
            }                                                      //~1A16I~
            else                                                   //~1A15I~
            if (fileno>0)                                          //~1A16I~
            {                                                      //~1A16I~
	        	intent=new Intent(Intent.ACTION_SEND_MULTIPLE);          //~1A15I~//~1A16I~
		        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,Suris);//~1A15I~
            }                                                      //~1A16I~
            else                                                   //~1A16I~
        	 	intent=new Intent(Intent.ACTION_SEND);             //~1A16I~
	        intent.putExtra(Intent.EXTRA_TEXT,mailText);           //~1A15I~//~1A16M~
        	intent.setType("*/*");                                 //~1A15I~//~1A16R~
            choose=Pfnm2;                                   //~1A15I~//~1A19R~
            if (mailTo!=null)                                            //~1A15I~
            {                                                      //~1A15I~
				intent.putExtra(Intent.EXTRA_EMAIL,mailTo);	//send a file//~1A15I~
                choose=mailTo[0];                                     //~1A15I~//~1A16R~
            }                                                      //~1A15I~
            if (mailSubject!=null)                                       //~1A15I~//~1A16M~
            {                                                      //~1A15I~//~1A16M~
				intent.putExtra(Intent.EXTRA_SUBJECT,mailSubject);	//send a file//~1A15I~//~1A16M~
                choose=mailSubject;                                //~1A15I~//~1A16M~
            }                                                      //~1A15I~//~1A16M~
			intent.putExtra(Intent.EXTRA_TEXT,mailText);	//send a file//~1A15I~
//            AxeG.activity.startActivity(Intent.createChooser(intent,"Send:"+choose));//~1A15I~//~1A19R~
			showChooser(intent,"Send:"+choose);                    //~1A19I~
        }                                                          //~1A15I~
        return rc;                                                 //~1A15I~
    }                                                              //~1A15I~
	//*******************************************                  //~1A15I~
	//*rc:<0:err, 0:has hdr,1:no hdr(send as stream),2:null        //~1A16R~
	//*******************************************                  //~1A15I~
    private static int chkMailText(String Pfnm)                    //~1A15I~
    {                                                              //~1A15I~
    	int resid,mailtoctr;                                       //~1A16R~
    	String hdr;//~1A15I~
        ArrayList<String> mailto;                                  //~1A16I~
    //*************************                                    //~1A15I~
    	if (Pfnm==null)                                            //~1A16I~
        	return 1;                                              //~1A16R~
        File f=new File(Pfnm);                                     //~1A15I~
        if (!f.exists())                                           //~1A15I~
        {                                                          //~1A15I~
            resid=R.string.Err_FileNotFound;                       //~1A15I~
            Utils.showToastLong(resid,Pfnm);                              //~1A15I~
            return -1;                                             //~1A15R~
        }                                                          //~1A15I~
        long sz=f.length();                                        //~1A15I~
        if (sz>MIN_TEXTFILE_SIZE)                                      //~1A15I~
        {                                                          //~1A15I~
            resid=R.string.Err_TextFileTooLarge;                   //~1A15I~
            Utils.showToastLong(resid,":"+Pfnm+" "+sz+">"+MIN_TEXTFILE_SIZE); //~1A15I~
            return -1;                                             //~1A15R~
        }                                                          //~1A15I~
        byte[] buff=new byte[(int) sz];                                    //~1A15I~
        if (!AxeProp.load(Pfnm,buff))
        		return -1;//~1A15I~
        String list=new String(buff);                              //~1A15I~
        String[] tokens=list.split("\n");                          //~1A15I~
        int ctr=tokens.length;                                         //~1A15I~
        if (ctr==0)                                                //~1A15I~
            return 1;                                              //~1A15R~
        mailTo=null;                                               //~1A15I~
        mailSubject=null;                                          //~1A15I~
        mailText=null;                                             //~1A15I~
        mailto=new ArrayList<String>();                            //~1A16I~
        mailtoctr=0;                                               //~1A16I~
        for (int ii=0;ii<ctr;ii++)                                 //~1A15I~
        {                                                          //~1A15I~
        	String line=tokens[ii];                                //~1A15I~
            hdr=MAIL_TO;                                    //~1A15I~
            if (line.trim().startsWith(hdr))                        //~1A15I~
            {                                                      //~1A16I~
            	mailto.add(line.substring(line.indexOf(hdr)+hdr.length()).trim());//~1A15I~//~1A16R~
                mailtoctr++;                                       //~1A16I~
                continue;                                          //~1A16I~
            }                                                      //~1A16I~
            hdr=MAIL_SUBJECT;                               //~1A15I~
            if (line.trim().startsWith(hdr))                        //~1A15I~
            {                                                      //~1A16I~
            	mailSubject=line.substring(line.indexOf(hdr)+hdr.length()).trim();//~1A15I~//~1A16R~
                continue;                                          //~1A16I~
            }                                                      //~1A16I~
            hdr=MAIL_FILE;                                         //~1A16I~
            if (line.trim().startsWith(hdr))                       //~1A16I~
            {                                                      //~1A16I~
            	String fnm=line.substring(line.indexOf(hdr)+hdr.length()).trim();//~1A16I~
                if (Dump.Y) Dump.println("File apend="+fnm);       //~1A16I~
        		Suris.add(Uri.fromFile(new File(fnm)));              //~1A16I~
                continue;                                          //~1A16I~
            }                                                      //~1A16I~
            hdr=MAIL_TEXT;                                  //~1A15I~
            if (line.trim().startsWith(hdr))                        //~1A15I~
            {                                                      //~1A15I~
            	mailText=list.substring(list.indexOf(hdr)+hdr.length());//~1A15I~
            	break;                                             //~1A15I~
            }                                                      //~1A15I~
        }                                                          //~1A15I~
        if (mailtoctr!=0)                                          //~1A16I~
        	mailTo=(String[])mailto.toArray(new String[0]);        //~1A16I~
                                                                   //~1A16I~
        if (mailText==null)                                        //~1A15I~
        {                                                          //~1A15I~
        	Suritext=Uri.fromFile(new File(Pfnm));                    //~1A15R~//~1A16R~
        	return 1;                                              //~1A15M~
        }                                                          //~1A15I~
        return 0;                                                  //~1A15R~
    }                                                              //~1A15I~
	//*******************************************                  //~1A15I~
	//*rc:attachment file ctr; -1:err                              //~1A15I~
	//*******************************************                  //~1A15I~
    private static int getAttachment(String Pfnm)                  //~1A15I~
    {                                                              //~1A15I~
    	int ctr=0,resid;                                                 //~1A15I~
        String fnm;                                                //~1A16I~
    //*************************                                    //~1A15I~
    	if (Pfnm==null)                                            //~1A15I~
        	;                                                      //~1A15I~
        else                                                       //~1A15I~
    	if (!Pfnm.startsWith("@"))	//listfile                     //~1A15I~
        {                                                          //~1A15I~
        	Suris.add(Uri.fromFile(new File(Pfnm)));                //~1A16I~
        	ctr=1;                                                 //~1A15I~
        }                                                          //~1A15I~
        else	                                                   //~1A15I~
        {                                                          //~1A15I~
	        fnm=Pfnm.substring(1);                                 //~1A16R~
	        File f=new File(fnm);                                  //~1A16I~
        	if (!f.exists())                                       //~1A15I~
            {                                                      //~1A15I~
            	resid=R.string.Err_FileNotFound;                   //~1A15I~
				Utils.showToastLong(resid,fnm);                          //~1A15I~//~1A16R~
        		return -1;                                         //~1A16R~
            }                                                      //~1A15I~
            else                                                   //~1A16I~
            {                                                      //~1A16I~
                long sz=f.length();                                    //~1A15I~//~1A16R~
                if (sz>MIN_TEXTFILE_SIZE)                                  //~1A15I~//~1A16R~
                {                                                      //~1A15I~//~1A16R~
                    resid=R.string.Err_TextFileTooLarge;               //~1A15I~//~1A16R~
                    Utils.showToastLong(resid,":"+Pfnm+" "+sz+">"+MIN_TEXTFILE_SIZE);//~1A15I~//~1A16R~
                    return -1;                                         //~1A15I~//~1A16R~
                }                                                      //~1A15I~//~1A16R~
                byte[] buff=new byte[(int) sz];                                //~1A15I~//~1A16R~
                boolean rc=AxeProp.load(fnm,buff);                 //~1A16R~
                if (!rc)                                           //~1A16R~
                        return -1;//~1A15I~                        //~1A16R~
                String list=new String(buff);                          //~1A15I~//~1A16R~
                String[] token=list.split("\n");                       //~1A15I~//~1A16R~
                ctr=token.length;                                      //~1A15I~//~1A16R~
                if (ctr==0)                                            //~1A15I~//~1A16R~
                    return 0;                                          //~1A15I~//~1A16R~
                for (int ii=0;ii<ctr;ii++)                             //~1A15I~//~1A16R~
                {                                                      //~1A15I~//~1A16R~
                    Suris.add(Uri.fromFile(new File(token[ii].trim())));       //~1A15I~//~1A16R~
                }                                                      //~1A15I~//~1A16R~
            }                                                      //~1A16I~
        }                                                          //~1A15I~
        return ctr;                                                //~1A15I~
    }                                                              //~1A15I~
	//*******************************************                  //~1A19I~
	//*rc:attachment file ctr; -1:err                              //~1A19I~
	//*******************************************                  //~1A19I~
    private static int getAttachmentFromList(String Plist)         //~1A19I~
    {                                                              //~1A19I~
    	int ctr;                                                   //~1A19I~
    //*************************                                    //~1A19I~
        String[] token=Plist.split("\n");                          //~1A19I~
        ctr=token.length;                                          //~1A19I~
        if (ctr==0)                                                //~1A19I~
            return 0;                                              //~1A19I~
        for (int ii=0;ii<ctr;ii++)                                 //~1A19I~
        {                                                          //~1A19I~
    		if (Dump.Y) Dump.println("AxeActivity uri from list="+token[ii]);//~1A19I~
            Suris.add(Uri.parse(token[ii].trim()));                //~1A19I~
        }                                                          //~1A19I~
        return ctr;                                                //~1A19I~
    }                                                              //~1A19I~
    private static int chkAttachmentFromList(String Plist)         //~1A19I~
    {                                                              //~1A19I~
    	int rc=0,resid,ctr;                                                  //~1A19I~
    //*************************                                    //~1A19I~
        String[] token=Plist.split("\n");                          //~1A19I~
        ctr=token.length;                                          //~1A19I~
        if (ctr==0)                                                //~1A19I~
            return 4;                                              //~1A19I~
        for (int ii=0;ii<ctr;ii++)                                 //~1A19I~
        {                                                          //~1A19I~
        	String fnm=token[ii].trim();                           //~1A19I~
    		if (Dump.Y) Dump.println("AxeActivity uri from list="+fnm);//~1A19I~
            Uri uri=Uri.parse(fnm);                                //~1A19I~
            fnm=uri.getPath();                                     //~1A19I~
            File f=new File(fnm);                                  //~1A19I~
            if (!f.exists())                                       //~1A19I~
            {                                                      //~1A19I~
            	resid=R.string.Err_FileNotFound;                   //~1A19I~
            	Utils.showToastLong(resid,fnm);                    //~1A19I~
                rc=4;                                              //~1A19I~
            }                                                      //~1A19I~
            else                                                   //~1A19I~
            if (!f.isFile())                                       //~1A19I~
            {                                                      //~1A19I~
            	resid=R.string.Err_NotFile;                        //~1A19I~
            	Utils.showToastLong(resid,fnm);                    //~1A19I~
                rc=4;                                              //~1A19I~
            }                                                      //~1A19I~
        }                                                          //~1A19I~
        return rc;                                                 //~1A19I~
    }                                                              //~1A19I~
//********************************************************         //~1A18I~
    @Override                                                      //~1A18I~
    public void onTimerExpired(AxeTimer Ptimer,int Pcallctr,Object Pparm)//~1A18I~
    {                                                              //~1A18I~
        try                                                        //~1A18I~
        {                                                          //~1A18I~
 //       	int msgid=Ptimer.userTimerId;                          //~1A18I~
            String list=(String)Pparm;                             //~1A18I~
    		if (Dump.Y) Dump.println("AxeActivity ontimerexpired(dragsend) list="+list);//~1A18I~
		    Suris=new ArrayList<Uri>();                            //~1A19I~
			int ctr=getAttachmentFromList(list);                   //~1A19I~
            if (ctr!=0)                                            //~1A19I~
            	dragActionSend();                                  //~1A19I~
            Suris=null;	//GC                                       //~1A19I~
            	                                                   //~1A19I~
        }                                                          //~1A18I~
        catch(Exception e)                                         //~1A18I~
        {                                                          //~1A18I~
            Dump.println(e,"AxeActivity onTimerExpired");          //~1A18I~
        }                                                          //~1A18I~
    }                                                              //~1A18I~
//********************************************************         //~1A19I~
	private static void showChooser(Intent Pintent,String Plabel)              //~1A19I~
    {                                                              //~1A19I~
      if (Pintent==null)            //test                              //~2127I~//~vabeR~
      {                                                            //~2127I~//~vabeR~
        Intent picker=new Intent(Intent.ACTION_PICK_ACTIVITY);     //~2127I~//~vabeR~
        picker.putExtra(Intent.EXTRA_INTENT,Pintent);              //~2127I~//~vabeR~
        AxeG.activity.startActivity(picker);                       //~2127I~//~vabeR~
      }                                                            //~2127I~//~vabeR~
      else                                                         //~2127I~//~vabeR~
        AxeG.activity.startActivity(Intent.createChooser(Pintent,Plabel));//~1A19I~
    }                                                              //~1A19I~
//********************************************************         //~vagFI~
	private static void showXeHelp(String Purl)                    //~vagFR~
    {                                                              //~vagFI~
    	if (Dump.Y) Dump.println("AxeActivity showXeHelp="+Purl);  //~vagFI~
    	AxeDlgWebView.showHelp(Purl);                            //+vagFR~
    }                                                              //~vagFI~
}//class                                                           //~va15R~
