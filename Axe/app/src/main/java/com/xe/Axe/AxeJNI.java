//CID://+vc5pR~:   update#=  149                                   //~vc5pR~
//*************************************************************
//vc5p 2023/07/22 Dump open with for //Axe/x1.(mime type=text/x-csrc for ////Axe/x1.c)//~vc5pI~
//vc5c 2023/07/04 display directory set to access by ACTION_OPEN_DOCUMENT_TREE//~vc5cI~
//v77w:230519 uri-->path is avalable from api30(android11:R) and readdir using fd gotten by openDescriptor returns null//~v77wI~
//v77g:230424 ARM;rename                                           //~v77gI~
//v77f:230424 ARM;rmdir                                            //~v77fI~
//v77e:230424 ARM;try fd for opendir/readdir                       //~v77eI~
//v77d:230423 ARM;delete                                           //~v77dI~
//v77c:230422 ARM;mkdir                                            //~v77cI~
//vc4s 2023/04/15 edit using SAF                                   //~vc4sI~
//vc4q 2023/04/01 support shared storage using SAF(StorageAccessFramework)//~vc4qI~
//vc4p 2023/03/30 android10(api29) executable permission; try Manifest:extractNativeLibs=true and getApplicationInfo().nativeLibrary//~vc4pI~
//vc45 2022/03/25 deprecated; Java9 new Integer,Boolean,Double,Long-->valueOf//~vc45I~
//vc2X 2020/09/19 return sdpath even no write permission for utrace//~vc2XI~
//vc2W 2020/09/19 at first install, uerrexit by ualloc size=0(Gscrbuffwidth); Because notifyOptionChangedOther is called before initscreen; notify trace option by another interface//~vc2WI~
//vc2J 2020/08/25 add receive intent data scheme content:// additional to file:////~vc2JI~
//vbrh:200824 (AXE)notify ruler changed to Axe dialog              //~vbrhI~
//vc1m 2020/06/23 Toast should be on MainThread                    //~vc1mI~
//vc1f 2020/06/20 ARM;chk sdcard writable                          //~vc1fI~
//vay7:141122 (Axe)actionBar:save/saveas item                      //~vay7I~
//vay0:140710 (Axe)jni exception handling                          //~vay0I~
//vaxa:140628 (Axe)segfault at exit by quit cmd(uerrexit but axe continue process for popup errmsg)//~vaxaI~
//vaiq:130606 Axe:issue initial setup Alert(Be paitient)           //~vaiqI~
//vain:130601 Axe:Bug:popup menu did not processed selected menu item//~vainI~
//v6k1:130524 (Axe)sbcsmap tbl recreate option                     //~v6k1I~
//vad2:120423 (Axe)Charset option by Arm related option dialog     //~vad2I~
//vac6:120217 (Axe)samba share support using jcifs 3.17            //~vac6I~
//vaba:120125 (Axe)crash for ucs4 when android4(getTextWidth output double length of width tbl)//~vabaI~
//vab1:120119 (Axe)abend at android4:ArrayIndexOutOfBoundException //~vab1I~
//vaaz:120106 (Axe:Bug)beep clashed                                //~vaazI~
//vaa8:111111 (Axe)unzip by subthread                              //~v118I~
//*************************************************************
//JNI interface
//*************************************************************
package com.xe.Axe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import com.ahsv.AG;
import com.ahsv.utils.UFile;
import com.ahsv.utils.USAF;
import com.ahsv.utils.Utils;

public class AxeJNI
    	implements AxeTimerI
{
	private static final String CN="AxeJNI:";                      //~vc4qI~
	private static final String JNILIB="axejni";
//  public static native void jniInit(String Psdpath,String Pwkdir,String Plocale,int Popttrace);//~vc1fR~
    public static native void jniInit(String PsdRoot,String Psdpath,String Pwkdir,String Plocale,int Popttrace);//~vc1fI~
    public static native int jniKbdMsg(int Paction,int  Pkey,int Pscan);
    public static native void j2cOnActivityResult(int PreqID,int Prc,String Pparm,String PstrUri,String Ppath);//~vc4qR~
    public static native int jniMouseMsg(int Paction,int Pbutton,int Pflag,int Px,int Py);//~vc4qI~
    public static native void jniSetScreenSize(int Pw,int Ph);
    public static native void jniTerminateXe();
    public static native void jniFullDraw(int Presize);
//  public static native void jniOptionChangedOther(int Prulermode,int Puseact,boolean Pfreecsr,boolean Pbeep,boolean Pqexit);//~vc1fR~
    public static native void jniOptionChangedOther(int Prulermode,int Puseact,boolean Pfreecsr,boolean Pbeep,boolean Pqexit,int PaxeStatus);//~vc1fI~
    public static native void jniOptionChangedColor(int Pbgcolor,int Prulercolor);
//  public static native void jniOptionChangedFont(String Pfontname,int Pfontwidth,int Pfontheight,int Pcellw,int Pcellh,int Pmonospace,boolean Pligature,int Pbaseline,int Pfontoffsx);
    public static native void jniOptionChangedFont(String Pfontname,int Pfontwidth,int Pfontheight,int Pcellw,int Pcellh,int Pmonospace,boolean Pligature,int Pcellw0,int Pcellh0);
    public static native void jniOnTimer(int Ptimerid,long Pcallback);
    public static native void jniGetSampleColor(int[] Psamplecolor);
    public static native void jniSetTraceOpt(int Ptrace);
    public static native void jniSetDebugOpt(int Pdebugopt);       //~vay0I~
    public static native void jniOnContextMenuClosed();
    public static native void jniOnEditCut();
    public static native void jniOnEditCopy();
    public static native void jniOnEditClear();
    public static native void jniOnEditPasteV();
    public static native void jniOnEditPasteIns();
    public static native void jniOnEditPasteRep();
    public static native void jniOnFileEnd();                      //~vainI~
    public static native void jniOnFileCancel();                   //~vainI~
    public static native void jniOnFileOpenWith();                 //~vainI~
    public static native void jniSwipeHorizontal(int PmetaState,int Pdist,int Prate);
    public static native void jniSwipeVertical(int PmetaState,int Pdist,int Prate);
    public static native void jniCmd(String Pcmd,String Popd);
    public static native void jniUserMsg(int Pmsgid,long Pparm);
    public static native void jniDndRepCopy(String Pcmd);
    public static native int  jniIsValidCharset(String Pcharset);  //~vad2R~
    public static native void jniGetCurrentFilename();             //~vay7I~
    public static native void jniSetEnvPath();                     //~vc1mI~
    public static native void jniNotifyOpenDocResult(int Popt,String Pfpath,int PrcOpt,String PrcString,byte[] PrcByte);//~vc4sI~
    public static native void jniNotifyfgetsDocResult(int Popt,String Pfpath,String Pline,int Pbuffsz);//~vc4sR~
    public static native void jniNotifyfreadDocResult(int Popt,String Pfpath,byte[] Pbuff,int Plen);//~vc4sR~
    public static native void jniNotifyfwriteDocResult(int Popt,String Pfpath,int Plen);//~vc4sI~
    public static native void jniNotifyopendirDocResult(int Popt,String Pfpath,int Pfd);//~v77eI~
    public static native void jniNotifycopyDocResult(int Popt,int PerrSrc,int PerrTgt);//~v77gR~
    public static native void jniNotifystatDocResult(int Popt,String Pfpath,int Pfd);//~v77gI~
    public static native void jniNotifygetDocPathResult(int Popt,String Pfpath);//~v77wI~

    private static boolean preinitCalled;
                                                                   //~1620I~9
//**************************************************************
    public AxeJNI(){};
//**************************************************************
//*j2c
//**************************************************************
//*********************************************
	public static void init()
    {
        try
        {
            if (Dump.Y) Dump.println("AxeJNI.jniinit:nativeLibraryDir="+ AG.context.getApplicationInfo().nativeLibraryDir);//~vay7I~
            listNativeLibraryDir();                                //~vc4mI~
            listEnv();  //for test                                 //~vc1mR~
            System.loadLibrary(JNILIB);
            preinitCalled=true;
            if (Dump.Y) Dump.println("jniinit:jnilib loaded "+JNILIB);
		    String sdpath=AxeProp.getSDPath("");
		    String wkdir=AxeProp.getWkdir();
            String data=AxeG.intentData;
            if (data!=null)
            {
	            if (Dump.Y) Dump.println("intentdata @@@@ "+data+",action="+AxeG.intentAction);
//                if (data.startsWith("file://"))                  //~vc2JR~
//                    data=data.substring(7);                      //~vc2JR~
				data=getURIPath(data);                             //~vc2JI~
                Gxeh.initFile=data;
                if (AxeG.intentAction.equals(Intent.ACTION_VIEW))
                	Gxeh.initCmd="-B";	//browse
                else
                	Gxeh.initCmd="-E";	//edit
            }
            if ((AxeG.optTrace & AxeG.TRACEO_REMAP)!=0)            //~v6k1I~
            	Ucs.resetwidthtbl();                               //~v6k1R~
//          jniInit(sdpath,wkdir,AxeG.Glocale,AxeG.optTrace);      //~vac6R~//~vc1fR~
            jniInit(Gxeh.sdRoot,sdpath,wkdir,AxeG.Glocale,AxeG.optTrace);//~vc1fI~
//  		String localecs=AxeG.Glocale+"."+Charset.defaultCharset().displayName();//~vac6R~
//          jniInit(sdpath,wkdir,localecs,AxeG.optTrace);          //~vac6R~
    		setDebugOption(AxeG.optDebug);                         //~vay0I~
            if (Dump.Y) Dump.println("jniinit:returned");
//          listEnv();  //TODO test                                //~vc1mR~
//  		execTest();	//TODO test                                //~v77wR~
        }
        catch(Exception e)
        {
             Dump.println(e,"JNI:init");                           //~vay0R~
        }
    }
//*********************************************
	public static void setScreenSize(int Pw,int Ph)
    {
        if (Dump.Y) Dump.println(CN+"setScreenSize w="+Pw+",h="+Ph);//~v6k1R~//~vc4qR~
        try
        {
        	jniSetScreenSize(Pw,Ph);
        }
        catch(Exception e)
        {
             Dump.println(e,"JNI:setScreenSize");                  //~vay0R~
        }
    }
//**************************************************
	public static void kbdmsg(int Paction,int Pkey,int Pscancode)
    {
		int rc=0;
    //**********
        if (Dump.Y) Dump.println(CN+"kbdmsg action="+Paction+",key=0x"+Integer.toHexString(Pkey)+",scan="+Pscancode);//~vc4qI~//~v77wR~
        if (!AxeProp.initCompleted)                                //~v118I~
        	return;                                                //~v118I~
        try
        {
            rc=jniKbdMsg(Paction,Pkey,Pscancode);

            if (Dump.Y) Dump.println("jni call kbdmsg rc="+rc+",key="+Integer.toHexString(Pkey)+",scancode="+Integer.toHexString(Pscancode));//~vc1mR~
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:kbdmsg");                          //~v118R~
        }
    }
//**************************************************
	public static boolean onTouch(int Paction,int Pbutton,int Pflag,int Px,int Py)
    {
		boolean rc=false;
    //**********
        if (Dump.Y) Dump.println(CN+"onTouch action="+Paction+",button="+Pbutton);//~vc4qI~
        if (!AxeProp.initCompleted)                                //~v118I~
        	return false;                                                //~v118I~
      try                                                          //~v118I~
      {                                                            //~v118I~
         rc=jniMouseMsg(Paction,Pbutton,Pflag,Px,Py)!=0;
      }                                                            //~v118I~
      catch(Exception e)                                           //~v118I~
      {                                                            //~v118I~
         Dump.println(e,"JNI:mousemsg");                           //~v118I~
      }                                                            //~v118I~
        if (Dump.Y) Dump.println("jni call MouseMsg rc="+rc+",Pactioney="+Integer.toHexString(Paction)+",button="+Pbutton+",flag="+Integer.toHexString(Pflag)+",x="+Px+",y="+Py);
        return rc;
    }
//**************************************************               //~vc4qI~
	public static void jniOnActivityResult(int PreqID,int Prc,String Pparm,String PstrUri,String Ppath)//~vc4qI~
    {                                                              //~vc4qI~
        if (Dump.Y) Dump.println("AxeJNI.jniOnActivityResult reqid="+PreqID+",rc="+Prc+",parm="+Pparm+",strUri="+PstrUri+",path="+Ppath);//~vc4qI~
      	try                                                        //~vc4qI~
      	{                                                          //~vc4qI~
        	j2cOnActivityResult(PreqID,Prc,Pparm,PstrUri,Ppath);   //~vc4qI~
      	}                                                          //~vc4qI~
      	catch(Exception e)                                         //~vc4qI~
      	{                                                          //~vc4qI~
        	 Dump.println(e,"JNI:jniOnActivityResult reqid="+PreqID+",parm="+Pparm);//~vc4qI~
      	}                                                          //~vc4qI~
        if (Dump.Y) Dump.println("AxeJNI.jniOnActivityResult exit");//~vc4qI~
    }                                                              //~vc4qI~
//**************************************************************
//*c2j
//**************************************************************
//*********************
//	public static void drawTextFromJNI(String Ptext)
//    {
//        if (Dump.Y) Dump.println("from jni call text="+Ptext);
//    	AxeG.axeScreen.drawTextFromJNI(Ptext);
//    }
//*********************
	public static void beep()
    {
        if (Dump.Y) Dump.println(CN+"from jni call beep");         //~vc4qR~
      try                                                          //~vaazI~
      {                                                            //~vaazI~
    	Utils.beep();
      }                                                            //~vaazI~
      catch(Exception e)                                           //~vaazI~
      {                                                            //~vaazI~
         Dump.println(e,"JNI:mousemsg");                           //~vaazI~
      }                                                            //~vaazI~
    }
//*********************
	public static void quit()
    {
       if (Dump.Y) Dump.println(CN+"from jni call quit");          //~vc4qR~
//  	Utils.exit(0,true/*kill process*/);                        //~vaxaR~
	    Utils.exit(0);  //finish()                         //~vaxaI~
    }
//*********************
	public static void get_charset(byte[] Pcharset)
    {
        Charset cs=Charset.defaultCharset();
        String  name=cs.displayName();
        if (Dump.Y) Dump.println(CN+"get_charset from jni call get_charset displayname="+name);//~vc4qR~
        byte[] bytes=name.getBytes();
        System.arraycopy(bytes,0,Pcharset,0,name.length());
    }

//*********************
//    public static void usetmonospace(int Popt,int[] Ppdata,byte[] Ppdbcs,int Plen,int Pdbcslen,int Pcellw,int[]Ppoffsettb)
//    {
//        Axecsub2.usetmonospace(Popt,Ppdata,Ppdbcs,Plen,Pdbcslen,Pcellw,Ppoffsettb);
//        return;
//    }//usetmonospace
    public static void gettextwidths(int[] Ppdata,float[] Ppfwidthtb)
    {
        if (Dump.Y) Dump.println(CN+"gettextwidth");               //~vc4qI~
    	try                                                        //~vabaI~
        {                                                          //~vabaI~
        AxeScreen.gettextwidths(Ppdata,Ppfwidthtb);
        }                                                          //~vabaI~
        catch(Exception e)                                         //~vabaI~
        {                                                          //~vabaI~
            Dump.println(e,"JNI:getTextWidth");                    //~vabaR~
        }                                                          //~vabaI~
        return;
    }//usetmonospace
    //===============================================================================
    public static int usetmonospace_ligature(int Popt,int[] Ppdata,int Plen,int Pcellw)
    {
        if (Dump.Y) Dump.println(CN+"usetmonospace");              //~vc4qI~
    	return Axecsub2.usetmonospace_ligature(Popt,Ppdata,Plen,Pcellw);
    }
    //===============================================================================
    public static void draw_layout_with_color_monospace(int[] Pucs,int Plen,int Px,int Py,int Pfg,int Pbg,float[] Pfpostb)
    {
        if (Dump.Y) Dump.println(CN+"from jni call draw_layout_width_color_monospace len="+Plen+",x="+Px+",y="+Py+",fg="+Integer.toHexString(Pfg)+",bg="+Integer.toHexString(Pbg));//~vc4qR~
    	try
        {
	    	AxeG.axeScreen.drawText_Monospace(Px,Py,Pucs,Plen,Pfg,Pfpostb);
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:draw_layout_with_color_monospace");//~vay0R~
        }
    }
    //===============================================================================
    public static void draw_layout_with_color_ligature(int[] Pucs,int Plen,int Px,int Py,int Pfg,int Pbg,float Pscale)
    {
        if (Dump.Y) Dump.println(CN+"draw_layout_with_color_ligature from jni call draw_layout_width_color_ligature len="+Plen+",x="+Px+",y="+Py+",fg="+Integer.toHexString(Pfg)+",bg="+Integer.toHexString(Pbg));//~vc4qR~
    	try
        {
	    	AxeG.axeScreen.drawText_Ligature(Px,Py,Pucs,Plen,Pfg,Pscale);
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:draw_layout_with_color_ligature"); //~vay0R~
        }
    }
    //===============================================================================
    public static void draw_rectangle(int Pfill,int Px,int Py,int Pw,int Ph,int Pcolor)
    {
      	if (Dump.Y) Dump.println(CN+"draw_rectangle fill="+Pfill+",x="+Px+",y="+Py+",xe="+(Px+Pw)+",ye="+(Py+Ph));//~v6k1R~//~vc4qI~
      try                                                          //~vac6I~
      {                                                            //~vac6I~
    	AxeG.axeScreen.drawRect(Pfill,Px,Py,Pw,Ph,Pcolor);
      }                                                            //~vac6I~
      catch(Exception e)                                           //~vac6I~
      {                                                            //~vac6I~
        Dump.println(e,"JNI:draw_rectangle");                      //~vay0R~
      }                                                            //~vac6I~
    }
    //===============================================================================
    public static void draw_line(int Pswdirect,int Pwidth,int Px1,int Py1,int Px2,int Py2,int Pcolor)
    {
        if (Dump.Y) Dump.println(CN+"draw_line swdirect="+Pswdirect+",x1="+Px1+",y1="+Py1);//~vc4qR~
      try                                                          //~vac6I~
      {                                                            //~vac6I~
	    AxeG.axeScreen.drawLine(Pswdirect,Pwidth,Px1,Py1,Px2,Py2,Pcolor);
      }                                                            //~vac6I~
      catch(Exception e)                                           //~vac6I~
      {                                                            //~vac6I~
        Dump.println(e,"JNI:draw_line");                           //~vay0R~
      }                                                            //~vac6I~
    }
    //===============================================================================
    public static void set_title(String Ptitle)
    {
        if (Dump.Y) Dump.println(CN+"set_title title="+Ptitle);    //~vc4qI~
      try                                                          //~vac6I~
      {                                                            //~vac6I~
	    AxeG.axeScreen.setTitle(Ptitle);
      }                                                            //~vac6I~
      catch(Exception e)                                           //~vac6I~
      {                                                            //~vac6I~
        Dump.println(e,"JNI:set_title");                           //~vay0R~
      }                                                            //~vac6I~
    }
    //===============================================================================
    public static void jni_exit(int Pexitcode)
    {
        if (Dump.Y) Dump.println(CN+"jni_exit");                   //~vc4qI~
	    Utils.exit(Pexitcode);
    }
    //===============================================================================
    public static int dialog_run(int Pbtnok,int Pbtnno,int Pbtncan,String Pmsg)
    {
    	int flags=0,rc=0;
    //***********************
        if (Pbtnok!=0)
        	flags|=AxeAlert.BUTTON_POSITIVE;
        if (Pbtnno!=0)
        	flags|=AxeAlert.BUTTON_NEGATIVE;
        if (Pbtncan!=0)
        	flags|=AxeAlert.BUTTON_NUTRAL;
        String msg=Pmsg;
        AxeAlertI cb=new AxeAlertI()
        	{
            	@Override
				public int alertButtonAction(int Pbuttonid,int Pitempos)
            	{
                	int rc=0;
            		switch(Pbuttonid)
                    {
                    case AxeAlert.BUTTON_POSITIVE:	//ok
                    	rc=AxeJNIdef.BTN_OK;
                    	break;
                    case AxeAlert.BUTTON_NEGATIVE:   //no
                    	rc=AxeJNIdef.BTN_NO;
                    	break;
                    case AxeAlert.BUTTON_NUTRAL:   	//cancel
                    	rc=AxeJNIdef.BTN_CAN;
                    	break;
                    }
                    if (Dump.Y) Dump.println("dialog_run cb return buttonid="+rc);
                    return rc;
            	}

				@Override
				public int alertOnShow(AxeAlert Paxealert,boolean Pdismiss) {
					return 0;
				}
            };

        AxeAlert.simpleAlertDialog(cb,""/*title*/,msg,flags);
        if (Dump.Y) Dump.println("dialog_run rc="+rc);
        return rc;
    }
    //===============================================================================
    public static void wxe_uerrexitstdo(byte[] Pmsg)
    {
        Axexei.wxe_uerrexitstdo(Pmsg);
    }
    //===============================================================================
    protected static void gxedlg_init()
    {
        if (Dump.Y) Dump.println(CN+"gxedlg_init");                //~vc4qR~
    	try
        {
	        Axegxedlg.gxedlg_init();
	    }                                                          //~vay0R~
        catch(Exception e)
        {
            Dump.println(e,CN+"gxedlg_init");                     //~vay0R~//~vc4qR~
        }
    }
    //===============================================================================
    public static void gxepage_init()
    {
        if (Dump.Y) Dump.println(CN+"gxepage_init:NOP yet");       //~vc4qR~
    }
    //===============================================================================
    public static void draw_drawable()
    {
        if (Dump.Y) Dump.println(CN+"draw_drawable");              //~vc4qR~
        AxeG.axeScreen.uinvalidate();
    }
    //===============================================================================
    public static View lookup_widget(String Pname)
    {
        if (Dump.Y) Dump.println(CN+"lookup_widget "+Pname);       //~vc4qR~
        View v=AxeG.mainView;	//current(port or land)
        return v;
    }
    //===============================================================================
    public static int umsgbox(String Pmsg,int Pflag)
    {
    	int rc=Axecsub.IDOK;
        if (Dump.Y) Dump.println(CN+"umsgbox "+Pmsg+",flag="+Integer.toHexString(Pflag));//~vc4qR~
        try
        {
        	rc=Axecsub.umsgbox(Pmsg,Pflag);
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:umsgbox");                         //~vay0R~
        }
        return rc;
    }
    //===============================================================================
    public static void umsgbox2(String Pmsg,int Pmsgtype,int Pbtntype)
    {
        if (Dump.Y) Dump.println(CN+"umsgbox2 "+Pmsg);             //~vc4qR~
        try
        {
        	Axecsub.umsgbox2(Pmsg,Pmsgtype,Pbtntype);
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:umsgbox2");                        //~vay0R~
        }
    }
    //===============================================================================//~vay0I~
    public static void umsgboxACRA(String Pmsg)                    //~vay0I~
    {                                                              //~vay0I~
		System.out.println("umsgboxACRA:"+Pmsg);                   //~vay0I~
        try                                                        //~vay0I~
        {                                                          //~vay0I~
            String msg=new String(Pmsg);                           //~vay0R~
            Intent intent=new Intent(AxeG.context,AxeNDKCrash.class);//~vay0R~
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);        //~vay0R~
            intent.putExtra("error",msg);                          //~vay0R~
            AxeG.context.startActivity(intent);                    //~vay0R~
//          Toast.makeText(AxeG.context,"NDKCrash:"+Pmsg,Toast.LENGTH_LONG).show();//~vay0R~//~vc1mR~
            Utils.showToastLong("NDKCrash:"+Pmsg);                    //~vc1mI~
        }                                                          //~vay0I~
        catch(Exception e)                                         //~vay0I~
        {                                                          //~vay0I~
            Dump.println(e,"JNI:umsgboxACRA");                     //~vay0I~
        }                                                          //~vay0I~
    }                                                              //~vay0I~
    //===============================================================================
    public static void terminatexe()
    {
        if (Dump.Y) Dump.println(CN+"terminatexe");                //~vc4qR~
        try
        {
            jniTerminateXe();
            if (Dump.Y) Dump.println("jni call terminateXe");
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:terminatexe");                     //~vay0R~
        }
    }
    //===============================================================================
    public static void notifyOptionChangedOther()
    {
        if (Dump.Y) Dump.println(CN+"notifyOptionChangedOther");   //~vc4qR~
        try
        {
//          jniOptionChangedOther(Gxeh.Mrulermode,Gxeh.Museact,Gxeh.Mfreecsr,Gxeh.Mbeep,Gxeh.Mqexit);//~vc1fR~
          if (preinitCalled)                                       //~vc1fI~
          {                                                        //~vc1fI~
            jniOptionChangedOther(Gxeh.Mrulermode,Gxeh.Museact,Gxeh.Mfreecsr,Gxeh.Mbeep,Gxeh.Mqexit,Gxeh.axeStatus);//~vc1fI~
            if (Dump.Y) Dump.println("jni call optionChangedOther");
          }                                                        //~vc1fI~
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:optionChangedOther");              //~vay0R~
        }
    }
    //===============================================================================//~vc2WI~
    public static void notifyOptionChangedOtherPermission()        //~vc2WI~
    {                                                              //~vc2WI~
        if (Dump.Y) Dump.println(CN+"notifyOptionChangedOtherPermission Gxeh.axeStatus="+Gxeh.axeStatus);//~vc2WR~//~vc4qR~
        try                                                        //~vc2WI~
        {                                                          //~vc2WI~
          if (preinitCalled)                                       //~vc2WI~
          {                                                        //~vc2WI~
            jniOptionChangedOther(-1/*id of Permission case */,Gxeh.Museact,Gxeh.Mfreecsr,Gxeh.Mbeep,Gxeh.Mqexit,Gxeh.axeStatus);//~vc2WI~
            if (Dump.Y) Dump.println("jni call returned optionChangedOtherPermission");//~vc2WI~
          }                                                        //~vc2WI~
        }                                                          //~vc2WI~
        catch(Exception e)                                         //~vc2WI~
        {                                                          //~vc2WI~
            Dump.println(e,CN+"optionChangedOtherPermission");    //~vc2WI~//~vc4qR~
        }                                                          //~vc2WI~
    }                                                              //~vc2WI~
    //===============================================================================
    public static void notifyOptionChangedColor()
    {
        if (Dump.Y) Dump.println("notifyOptionChangedColor");
        try
        {
            jniOptionChangedColor(Gxeh.Mbgcolor,Gxeh.Mrulercolor);
            if (Dump.Y) Dump.println("jni call optionChangedColor");
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"optionChangedColor");              //~vay0R~//~vc4qR~
        }
    }
    //===============================================================================
    public static void notifyOptionChangedFont()
    {
        if (Dump.Y) Dump.println("notifyOptionChangedFont");
        try
        {
//          String fontname=Gxeh.Mfontstyle+";"+Gxeh.Mstyle+";"+Gxeh.Mfontsize;
            String fontname=Gxeh.Mfontstyle+";"+Gxeh.Mstyle+";"+Gxeh.Mfontheight;
            if (Dump.Y) Dump.println("jni call optionChangedFont font="+Gxeh.Mfontwidth+","+Gxeh.Mfontheight+",cell="+Gxeh.Mcellw+","+Gxeh.Mcellh+",monospace="+Gxeh.Mmonospace+",ligature="+Gxeh.Mligature);
//          jniOptionChangedFont(fontname,Gxeh.Mfontwidth,Gxeh.Mfontheight,Gxeh.Mcellw,Gxeh.Mcellh,Gxeh.Mmonospace,Gxeh.Mligature,Gxeh.MbaseLine,Gxeh.Mfontoffsx);
            jniOptionChangedFont(fontname,Gxeh.Mfontwidth,Gxeh.Mfontheight,Gxeh.Mcellw,Gxeh.Mcellh,Gxeh.Mmonospace,Gxeh.Mligature,Gxeh.Mcellw0,Gxeh.Mcellh0);
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"notifyOptionChangedFont");               //~vay0R~//~vc4qR~
        }
    }
    //===============================================================================
    public static void fullDraw()
    {
    	fullDraw(false);
    }
    //===============================================================================
    public static void fullDraw(boolean Presize)
    {
        if (Dump.Y) Dump.println(CN+"fullDraw resize="+Presize);   //~vc4qR~
        try
        {
        	int p=Presize?1:0;
            jniFullDraw(p);
            if (Dump.Y) Dump.println(CN+"jni call jniFullDraw");   //~vc4qR~
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"fullDraw");                        //~vay0R~//~vc4qR~
        }
    }
    //===============================================================================
    public static int timeout_add(int Pinterval,int Ptimerid,long Pfunction)
    {
        if (Dump.Y) Dump.println(CN+"timeout_add interval="+Pinterval+",id="+Integer.toHexString(Ptimerid));//~vc4qR~
        int handler=Axecsub.usettimer(Ptimerid,Pinterval,Pfunction);
        return handler;
    }
    //===============================================================================
    public static void OnTimer(int Ptimerid,long Pcallback)
    {
        if (Dump.Y) Dump.println(CN+"OnTimer id="+Integer.toHexString(Ptimerid));//~vc4qR~
        try
        {
            jniOnTimer(Ptimerid,Pcallback);
            if (Dump.Y) Dump.println("jni call onTimer");
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:OnTimer");                         //~vay0R~
        }
    }
    //===============================================================================
    public static void timeout_remove(int Ptimerid)
    {
        if (Dump.Y) Dump.println(CN+"timeout_remove id="+Integer.toHexString(Ptimerid));//~vc4qR~
        AxeTimer.remove(Ptimerid);
        return;
    }
    //===============================================================================
    public static int[] getSampleColor()
    {
        if (Dump.Y) Dump.println(CN+"getSampleColor");             //~vc4qR~
        int[] samplecolor=new int[3];
        try
        {
            if (Dump.Y) Dump.println(CN+"getSampleColor call jniGetSampleColor");//~vc4qI~
            jniGetSampleColor(samplecolor);
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"getSampleColor");                  //~vay0R~//~vc4qR~
            samplecolor[0]=Color.WHITE;
            samplecolor[1]=Color.YELLOW;
            samplecolor[1]=Color.GREEN;
        }
        return samplecolor;
    }
    //===============================================================================
    public static void setTraceOption(int Ptrace)
    {
        if (Dump.Y) Dump.println(CN+"setTraceOpt opt="+Ptrace);    //~vc4qR~
      try                                                          //~vay0I~
      {                                                            //~vay0I~
        if (preinitCalled)
            jniSetTraceOpt(Ptrace);
      }                                                            //~vay0I~
      catch(Exception e)                                           //~vay0I~
      {                                                            //~vay0I~
        Dump.println(e,CN+"setTraceOption");                      //~vay0I~//~vc4qR~
      }                                                            //~vay0I~
        return;
    }
    //===============================================================================//~vay0I~
    public static void setDebugOption(int Pdebugopt)               //~vay0I~
    {                                                              //~vay0I~
        if (Dump.Y) Dump.println(CN+"setDebugOption opt="+Pdebugopt); //~vay0I~//~vc4qR~
      try                                                          //~vay0I~
      {                                                            //~vay0I~
        if (preinitCalled)                                         //~vay0I~
            jniSetDebugOpt(Pdebugopt);                                //~vay0I~
      }                                                            //~vay0I~
      catch(Exception e)                                           //~vay0I~
      {                                                            //~vay0I~
        Dump.println(e,CN+"setDebugOption");                      //~vay0I~//~vc4qR~
      }                                                            //~vay0I~
        return;                                                    //~vay0I~
    }                                                              //~vay0I~
    //**************************************
    //*Clipboard
    //**************************************
    public static void clipboard_get()
    {
        if (Dump.Y) Dump.println(CN+"clipboard_get");              //~vc4qR~
        Axecsub2.clipboard_get();
        return;
    }
    //***************************
    public static String clipboard_wait_for_text()
    {
    	String str;
        try
        {
        	str=Axecsub2.clipboard_wait_for_text();
	        if (Dump.Y) Dump.println("clipboard_wait_for_text return="+str);
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"clipboard_wait_for_text");         //~vay0R~//~vc4qR~
            str=null;
        }
        return str;
    }
    //***************************
    public static void clipboard_set_text(String Pstr)
    {
        if (Dump.Y) Dump.println(CN+"clipboard_set_text str="+Pstr);//~vc4qR~
        try
        {
        	Axecsub2.clipboard_set_text(Pstr);
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"clipboard_set_text");              //~vay0R~//~vc4qR~
        }
    }
    //**************************************
    //*PopupMenu
    //**************************************
    public static void menu_popup(int[] Penabletbl)
    {
        if (Dump.Y) Dump.println(CN+"menu_popup");                 //~vc4qR~
        try
        {
        	AxeG.axeMenu.popupMenu(Penabletbl);
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"menu_popup");                      //~vay0R~//~vc4qR~
        }
    }
    //**************************************
    //*ContextMenu
    //**************************************
    public static void onContextMenuClosed()
    {
        if (Dump.Y) Dump.println(CN+"onContextMenuClosed");        //~vc4qR~
        try
        {
            jniOnContextMenuClosed();
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"onContextMenuClosed");             //~vay0R~//~vc4qR~
        }
    }
    //**************************************
    //*Cut and Paste
    //**************************************
    public static void onEditCut()
    {
        if (Dump.Y) Dump.println(CN+"onEditCut");                  //~vc4qR~
        try
        {
            jniOnEditCut();
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"onEitCut");                        //~vay0R~//~vc4qR~
        }
    }
    public static void onEditCopy()
    {
        if (Dump.Y) Dump.println(CN+"onEditCopy");                 //~vc4qR~
        try
        {
            jniOnEditCopy();
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"onEitCopy");                       //~vay0R~//~vc4qR~
        }
    }
    public static void onEditClear()
    {
        if (Dump.Y) Dump.println(CN+"onEditClear");                //~vc4qR~
        try
        {
            jniOnEditClear();
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"onEitClear");                      //~vay0R~//~vc4qR~
        }
    }
    public static void onEditPasteV()
    {
        if (Dump.Y) Dump.println(CN+"onEditPasteV");               //~vc4qR~
        try
        {
            jniOnEditPasteV();
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"onEitPasteV");                     //~vay0R~//~vc4qR~
        }
    }
    public static void onEditPasteIns()
    {
        if (Dump.Y) Dump.println(CN+"onEditPasteIns");             //~vc4qR~
        try
        {
            jniOnEditPasteIns();
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"onEitPasteIns");                   //~vay0R~//~vc4qR~
        }
    }
    public static void onEditPasteRep()
    {
        if (Dump.Y) Dump.println(CN+"onEditPasteRep");             //~vc4qR~
        try
        {
            jniOnEditPasteRep();
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"onEitPasteRep");                   //~vay0R~//~vc4qR~
        }
    }
    //**************************************                       //~vainI~
    public static void onFileEnd()                                 //~vainI~
    {                                                              //~vainI~
        if (Dump.Y) Dump.println(CN+"onFileEnd");              //~vainI~//~vc4qR~
        try                                                        //~vainI~
        {                                                          //~vainI~
            jniOnFileEnd();                                        //~vainI~
        }                                                          //~vainI~
        catch(Exception e)                                         //~vainI~
        {                                                          //~vainI~
            Dump.println(e,CN+"onFileEnd");                       //~vay0R~//~vc4qR~
        }                                                          //~vainI~
    }                                                              //~vainI~
    //**************************************                       //~vay7R~
    public static void onFileSave(String Pfnm)                     //~vay7R~
    {                                                              //~vay7R~
        if (Dump.Y) Dump.println(CN+"onFileSave");             //~vay7R~//~vc4qR~
        try                                                        //~vay7R~
        {                                                          //~vay7R~
	        AxeJNI.xecmd("Save",Pfnm);                             //~vay7R~
        }                                                          //~vay7R~
        catch(Exception e)                                         //~vay7R~
        {                                                          //~vay7R~
            Dump.println(e,CN+"onFileSave");                      //~vay7R~//~vc4qR~
        }                                                          //~vay7R~
    }                                                              //~vay7R~
    public static void onFileCancel()                              //~vainI~
    {                                                              //~vainI~
        if (Dump.Y) Dump.println(CN+"onFileCancel");           //~vainI~//~vc4qR~
        try                                                        //~vainI~
        {                                                          //~vainI~
            jniOnFileCancel();                                     //~vainI~
        }                                                          //~vainI~
        catch(Exception e)                                         //~vainI~
        {                                                          //~vainI~
            Dump.println(e,CN+"onFileCancel");                    //~vay0R~//~vc4qR~
        }                                                          //~vainI~
    }                                                              //~vainI~
    public static void onFileOpenWith()                            //~vainI~
    {                                                              //~vainI~
        if (Dump.Y) Dump.println(CN+"onFileOpenWith");         //~vainI~//~vc4qR~
        try                                                        //~vainI~
        {                                                          //~vainI~
            jniOnFileOpenWith();                                   //~vainI~
        }                                                          //~vainI~
        catch(Exception e)                                         //~vainI~
        {                                                          //~vainI~
            Dump.println(e,CN+"onFileOpenWith");                  //~vay0R~//~vc4qR~
        }                                                          //~vainI~
    }                                                              //~vainI~
    //**************************************
    //*swipe    dist<0:left/up, dist>0: right,down
    //*         rate: 0<...<100	/rate of screen W/H
    //**************************************
    public static void swipeHorizontal(int PmetaState,int Pdist,int Prate)
    {
        if (Dump.Y) Dump.println(CN+"swipeHorizontal dist="+Pdist+",rate="+Prate+",meta="+Integer.toHexString(PmetaState));//~vc4qR~
        try
        {
            jniSwipeHorizontal(PmetaState,Pdist,Prate);
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"onSwipeHorizontal");               //~vay0R~//~vc4qR~
        }
    }
    public static void swipeVertical(int PmetaState,int Pdist,int Prate)
    {
        if (Dump.Y) Dump.println(CN+"swipeVertical dist="+Pdist+",rate="+Prate+",meta="+Integer.toHexString(PmetaState));//~vc4qR~
        try
        {
            jniSwipeVertical(PmetaState,Pdist,Prate);
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"onSwipeVertical");                 //~vay0R~//~vc4qR~
        }
    }
    //**************************************
    //*OpenWith
    //**************************************
    public static String file2url(String Plocalfile)
    {
        String rc=null;
    //***********************
        if (Dump.Y) Dump.println(CN+"file2url file="+Plocalfile);  //~vc4qR~
        try
        {
            rc=AxeActivity.file2url(Plocalfile);
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"file2url:"+Plocalfile);             //~vc4qR~
        }
        return rc;
    }
    public static String getMimeType(String Purl)
    {
        String rc=null;
    //***********************
        if (Dump.Y) Dump.println(CN+"getMimeApplication mimetype="+Purl);//~vc4qR~
        try
        {
            rc=AxeActivity.getMimeType(Purl);
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"getMimeType:"+Purl);                //~vc4qR~
        }
        if (Dump.Y) Dump.println(CN+"getMimeType rc="+rc);         //~vc5pI~
        return rc;
    }
    public static int getMimeApplication(String Pmimetype)
    {
        int rc=4;
    //***********************
        if (Dump.Y) Dump.println(CN+"getMimeApplication mimetype="+Pmimetype);//~vc4qR~
        try
        {
            rc=AxeActivity.getMimeApplication(Pmimetype);
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"getMimeApplication:"+Pmimetype);    //~vc4qR~
        }
        if (Dump.Y) Dump.println(CN+"getMimeApplication rc="+rc);  //~vc5pI~
        return rc;
    }
    public static int getMimeApplication_dataandtype(String Puri,String Pmimetype)
    {
        int rc=4;
    //***********************
        if (Dump.Y) Dump.println(CN+"getMimeApplication_dataandtype uri="+Puri+",mimetype="+Pmimetype);//~vc4qR~
        try
        {
            rc=AxeActivity.getMimeApplication_dataandtype(Puri,Pmimetype);
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"getMimeApplication_dataandtype");   //~vc4qR~
        }
        if (Dump.Y) Dump.println(CN+"getMimeApplication_dataandtype rc="+rc);//~vc5pI~
        return rc;
    }
    public static int openWith(String Purl,String Pmimetype)
    {
        int rc=4;
    //***********************  
    	if (Dump.Y) Dump.println(CN+"openWith url="+Purl+",mime="+Pmimetype);//~vc4qR~
        try
        {
            if (!isExistsFile(Purl))                               //~vc5pI~
            	return AxeJNIdef.ENOENT;                                     //~vc5pI~
            rc=AxeActivity.openWith(Purl,Pmimetype);
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"openWidth:"+Purl+","+Pmimetype);    //~vc4qR~
        }
        if (Dump.Y) Dump.println(CN+"openWith rc="+rc);            //~vc5pI~
        return rc;
    }
    //***********************************************************
    public static boolean isExistsFile(String Purl)                //~vc5pI~
    {                                                              //~vc5pI~
        boolean rc=false;                                          //+vc5pR~
    //***********************                                      //~vc5pI~
//        try                                                      //~vc5pI~
//        {                                                        //~vc5pI~
          if (Purl.startsWith("file:"))                            //+vc5pR~
          {                                                        //+vc5pR~
        	File f=new File(Purl.substring(7/* file:// */));                    //+vc5pR~
            rc=f.exists();                                         //~vc5pI~
          }                                                        //+vc5pR~
//        else                                                     //~vc5pI~
//        {                                                        //~vc5pI~
//            rc=f.exists();                                       //~vc5pI~
//        }                                                        //~vc5pI~
//        }                                                        //~vc5pI~
//        catch(Exception e)                                       //~vc5pI~
//        {                                                        //~vc5pI~
//            Dump.println(e,CN+"openWidth:"+Purl+","+Pmimetype);  //~vc5pI~
//        }                                                        //~vc5pI~
    	if (Dump.Y) Dump.println(CN+"isExistsFile rc="+rc+",url="+Purl);//~vc5pI~
        return rc;                                                 //~vc5pI~
    }                                                              //~vc5pI~
    //***********************************************************  //~vc5pI~
    //*request xe's command
    //***********************************************************
    public static void xecmd(String Pcmd,String Popd)
    {
      try                                                          //~vay0I~
      {                                                            //~vay0I~
        if (Dump.Y) Dump.println(CN+"xecmd cmd="+Pcmd+",opd="+Popd);//~vc2JI~//~vc4qR~
        jniCmd(Pcmd,Popd);
      }                                                            //~vay0I~
      catch(Exception e)                                           //~vay0I~
      {                                                            //~vay0I~
        Dump.println(e,CN+"xecmd");                               //~vay0I~//~vc4qR~
      }                                                            //~vay0I~
    }
    //***********************************************************
    //*wcwidth
    //***********************************************************
    public static int wcwidth(int Pucs)
    {
        int rc=0;                                                  //~vab1I~
    //***********************
//      return Ucs.wcwidth(Pucs);                                  //~vab1R~
        if (Dump.Y) Dump.println(CN+"wcwidth ucs="+Pucs);          //~vc4qI~
      	try                                                        //~vab1I~
      	{                                                          //~vab1I~
        	rc=Ucs.wcwidth(Pucs);                                  //~vab1R~
      	}                                                          //~vab1I~
      	catch(Exception e)                                         //~vab1I~
      	{                                                          //~vab1I~
            Dump.println(e,CN+"wcwidth ucs=:"+Integer.toHexString(Pucs));//~vab1I~//~vc4qR~
      	}                                                          //~vab1I~
        return rc;                                                 //~vab1I~
    }
    public static void getwcwidthtbl(int Pucsctr,byte[] Pout)
    {
        if (Dump.Y) Dump.println(CN+"getwcwidthtbl ucsctr="+Pucsctr);//~vc4qI~
      try                                                          //~vab1I~
      {                                                            //~vab1I~
		Ucs.getwcwidthtbl(Pucsctr,Pout);
      }                                                            //~vab1I~
      catch(Exception e)                                           //~vab1I~
      {                                                            //~vab1I~
            Dump.println(e,CN+"getwcwidthtbl:ucsctr="+Pucsctr);   //~vab1I~//~vc4qR~
      }                                                            //~vab1I~
    }
    //***********************************************************
    //*send file
    //***********************************************************
    public static int androsend(int Popt,String Pfnm1,String Pfnm2)
    {
        int rc=4;
    //***********************
    	if (Dump.Y) Dump.println(CN+"androsend fnm1="+Pfnm1+",fnm2="+Pfnm2);//~vc4qR~
        try
        {
            rc=AxeActivity.androSend(Popt,Pfnm1,Pfnm2);
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"androsend:"+Pfnm1+","+Pfnm2);       //~vc4qR~
        }
        return rc;
    }
    //***********************************************************
    //*send file by drag
    //***********************************************************
    public static void dragsend(String Plist)
    {
    //***********************
    	if (Dump.Y) Dump.println(CN+"dragsend list="+Plist);       //~vc4qR~
        try
        {
            AxeActivity.dragSend(Plist);
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"dragsend");                         //~vc4qR~
        }
    }
    //***********************************************************
    //*dialog replyed copy rep0lace OK
    //***********************************************************
    public static void dragdropRepCopy(String Pcmd)
    {
    //***********************
    	if (Dump.Y) Dump.println(CN+"dragdropRepCopy cmd="+Pcmd);  //~vc4qR~
		jniDndRepCopy(Pcmd);
    }
    //***********************************************************
    //*upostmsg-->gtk_event_put ;callback user_msg
    //***********************************************************
    public static void event_put(int Pmsgid,long Pparm)
    {
    //***********************
    	if (Dump.Y) Dump.println(CN+"event_put msg(upostmsg)="+Pmsgid);//~vc4qR~
        try
        {
//        	Long parm=new Long(Pparm);                             //~vc45R~
          	Long parm=Long.valueOf(Pparm);                         //~vc45I~
	       AxeTimer t=new AxeTimer(AxeG.axeJNI/*callback*/,1/*milisec delay*/,false/*repeat*/,Pmsgid,parm);
	        t.start();
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"event_put(upostmsg):"+Pmsgid);      //~vc4qR~
        }
    }
//********************************************************
    @Override
    public void onTimerExpired(AxeTimer Ptimer,int Pcallctr,Object Pparm)
    {
    	if (Dump.Y) Dump.println(CN+"onTimerExpired");             //~vc4qI~
        try
        {
        	int msgid=Ptimer.userTimerId;
            long parm=((Long)Pparm).longValue();
    		if (Dump.Y) Dump.println("AxeJNI ontimerexpired(upostmsg)  msg="+msgid+",parm="+Long.toHexString(parm));
			jniUserMsg(msgid,parm);
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"onTimerExpired:");                  //~vc4qR~
        }
    }
    //******************************************
    //*vfatlist
    //******************************************
    public static String getVfatList()
    {
    	String rc=null;
    //***********************
    	if (Dump.Y) Dump.println(CN+"getVfatList");                //~vc4qR~
        try
        {
            rc=AxeProp.getVfatList();
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"getVfatList");                      //~vc4qR~
        }
        return rc;
    }
    //******************************************                   //~vac6R~
    //*smb(c2j)                                                    //~vac6R~
    //******************************************                   //~vac6R~
    public static int smb_ufstat(int Popt,String Pfpath)           //~vac6R~
    {                                                              //~vac6R~
    	if (Dump.Y) Dump.println(CN+"smb_ufstat");                 //~vc4qI~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6R~
      	{                                                          //~vac6R~
			rc=AxeSMBReq.stat(Popt,Pfpath);                        //~vac6R~
      	}                                                          //~vac6R~
      	catch(Exception e)                                         //~vac6R~
      	{                                                          //~vac6R~
            Dump.println(e,CN+"smb_ufstat:fpath="+Pfpath);        //~vac6R~//~vc4qR~
      	}                                                          //~vac6R~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6R~
    //******************************************                   //~vac6R~
    public static int smb_udirlist(int Popt,String Pfpath,int Pattr)//~vac6R~
    {                                                              //~vac6R~
    	if (Dump.Y) Dump.println(CN+"smb_udirlist");               //~vc4qI~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6R~
      	{                                                          //~vac6R~
			rc=AxeSMBReq.dirlist(Popt,Pfpath,Pattr);               //~vac6R~
      	}                                                          //~vac6R~
      	catch(Exception e)                                         //~vac6R~
      	{                                                          //~vac6R~
            Dump.println(e,CN+"smb_ufstat:fpath="+Pfpath);        //~vac6R~//~vc4qR~
      	}                                                          //~vac6R~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6R~
    //******************************************                   //~vac6I~
    public static int smb_nodetest(int Popt,String Pfpath)         //~vac6I~
    {                                                              //~vac6I~
    	if (Dump.Y) Dump.println(CN+"smb_nodetest");               //~vc4qI~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6I~
      	{                                                          //~vac6I~
			rc=AxeSMBReq.connect(Popt,Pfpath);                     //~vac6R~
      	}                                                          //~vac6I~
      	catch(Exception e)                                         //~vac6I~
      	{                                                          //~vac6I~
            Dump.println(e,CN+"smb_nodetest:fpath="+Pfpath);      //~vac6I~//~vc4qR~
      	}                                                          //~vac6I~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6I~
    //******************************************                   //~vac6I~
    public static int smb_getput(int Popt,String Pcmd)             //~vac6I~
    {                                                              //~vac6I~
    	if (Dump.Y) Dump.println(CN+"smb_getput");                 //~vc4qI~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6I~
      	{                                                          //~vac6I~
			rc=AxeSMBReq.getput(Popt,Pcmd);                      //~vac6I~
      	}                                                          //~vac6I~
      	catch(Exception e)                                         //~vac6I~
      	{                                                          //~vac6I~
            Dump.println(e,CN+"smb_getput:cmd="+Pcmd);            //~vac6I~//~vc4qR~
      	}                                                          //~vac6I~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6I~
    //******************************************                   //~vac6I~
    public static int smb_delete(int Popt,String Ppath)            //~vac6I~
    {                                                              //~vac6I~
    	if (Dump.Y) Dump.println(CN+"smb_delete");                 //~vc4qI~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6I~
      	{                                                          //~vac6I~
			rc=AxeSMBReq.delete(Popt,Ppath);                       //~vac6I~
      	}                                                          //~vac6I~
      	catch(Exception e)                                         //~vac6I~
      	{                                                          //~vac6I~
            Dump.println(e,CN+"smb_delete:path="+Ppath);          //~vac6I~//~vc4qR~
      	}                                                          //~vac6I~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6I~
    //******************************************                   //~vac6I~
    public static int smb_rename(int Popt,String Pold,String Pnew) //~vac6I~
    {                                                              //~vac6I~
    	if (Dump.Y) Dump.println(CN+"smb_rename");                 //~vc4qI~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6I~
      	{                                                          //~vac6I~
			rc=AxeSMBReq.rename(Popt,Pold,Pnew);                   //~vac6I~
      	}                                                          //~vac6I~
      	catch(Exception e)                                         //~vac6I~
      	{                                                          //~vac6I~
            Dump.println(e,CN+"smb_rename:path="+Pold+"->"+Pnew); //~vac6I~//~vc4qR~
      	}                                                          //~vac6I~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6I~
    //******************************************                   //~vac6I~
    public static int smb_multidel(int Popt,String Pcmd)           //~vac6I~
    {                                                              //~vac6I~
    	if (Dump.Y) Dump.println(CN+"smb_multidel");               //~vc4qI~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6I~
      	{                                                          //~vac6I~
			rc=AxeSMBReq.multidel(Popt,Pcmd);                     //~vac6I~
      	}                                                          //~vac6I~
      	catch(Exception e)                                         //~vac6I~
      	{                                                          //~vac6I~
            Dump.println(e,CN+"smb_multidel:cmd="+Pcmd);          //~vac6I~//~vc4qR~
      	}                                                          //~vac6I~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6I~
    //******************************************                   //~vac6I~
    public static int smb_multirename(int Popt,String Pcmd)        //~vac6I~
    {                                                              //~vac6I~
    	if (Dump.Y) Dump.println(CN+"smb_multirename");            //~vc4qI~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6I~
      	{                                                          //~vac6I~
			rc=AxeSMBReq.multirename(Popt,Pcmd);                   //~vac6I~
      	}                                                          //~vac6I~
      	catch(Exception e)                                         //~vac6I~
      	{                                                          //~vac6I~
            Dump.println(e,CN+"smb_multirename:cmd="+Pcmd);       //~vac6I~//~vc4qR~
      	}                                                          //~vac6I~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6I~
    //******************************************                   //~vac6I~
    public static int smb_ufindfirst(int Popt,String Ppath,int Pattr)//~vac6R~
    {                                                              //~vac6I~
    	if (Dump.Y) Dump.println(CN+"smb_ufindfirst");             //~vc4qI~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6I~
      	{                                                          //~vac6I~
			rc=AxeSMBReq.ufindfirst(Popt,Ppath,Pattr);             //~vac6R~
      	}                                                          //~vac6I~
      	catch(Exception e)                                         //~vac6I~
      	{                                                          //~vac6I~
            Dump.println(e,CN+"smb_ufindfirst:path="+Ppath);      //~vac6I~//~vc4qR~
      	}                                                          //~vac6I~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6I~
    //******************************************                   //~vac6I~
    public static int smb_mkdir(int Popt,String Ppath)             //~vac6R~
    {                                                              //~vac6I~
    	if (Dump.Y) Dump.println(CN+"smb_mkdir");                  //~vc4qI~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6I~
      	{                                                          //~vac6I~
			rc=AxeSMBReq.mkdir(Popt,Ppath);                        //~vac6R~
      	}                                                          //~vac6I~
      	catch(Exception e)                                         //~vac6I~
      	{                                                          //~vac6I~
            Dump.println(e,CN+"smb_mkdir:path="+Ppath);           //~vac6I~//~vc4qR~
      	}                                                          //~vac6I~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6I~
    //******************************************                   //~vac6I~
    public static int smb_rmdir(int Popt,String Ppath)             //~vac6I~
    {                                                              //~vac6I~
    	if (Dump.Y) Dump.println(CN+"smb_rmdir");                  //~vc4qI~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6I~
      	{                                                          //~vac6I~
			rc=AxeSMBReq.rmdir(Popt,Ppath);                        //~vac6I~
      	}                                                          //~vac6I~
      	catch(Exception e)                                         //~vac6I~
      	{                                                          //~vac6I~
            Dump.println(e,CN+"smb_rmdir:path="+Ppath);           //~vac6I~//~vc4qR~
      	}                                                          //~vac6I~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6I~
    //******************************************                   //~vac6I~
    public static int smb_setattr(int Popt,String Ppath,int Pattr) //~vac6I~
    {                                                              //~vac6I~
    	if (Dump.Y) Dump.println(CN+"smb_setattr");                //~vc4qI~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6I~
      	{                                                          //~vac6I~
			rc=AxeSMBReq.setattr(Popt,Ppath,Pattr);                //~vac6I~
      	}                                                          //~vac6I~
      	catch(Exception e)                                         //~vac6I~
      	{                                                          //~vac6I~
            Dump.println(e,CN+"smb_setattr:path="+Ppath+",attr="+Pattr);//~vac6I~//~vc4qR~
      	}                                                          //~vac6I~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6I~
    //******************************************                   //~vad2I~
    public static boolean isValidCharset(String Pcharset)          //~vad2I~
    {                                                              //~vad2I~
    	boolean brc=false;                                         //~vad2I~
        if (Dump.Y) Dump.println(CN+"isValidCharset");         //~vad2I~//~vc4qR~
        try                                                        //~vad2I~
        {                                                          //~vad2I~
            int rc=jniIsValidCharset(Pcharset);                    //~vad2I~
            if (rc!=0)                                             //~vad2I~
            	brc=true;                                          //~vad2I~
        }                                                          //~vad2I~
        catch(Exception e)                                         //~vad2I~
        {                                                          //~vad2I~
            Dump.println(e,CN+"isValidCharset");                  //~vay0R~//~vc4qR~
        }                                                          //~vad2I~
        return brc;                                                //~vad2I~
    }                                                              //~vad2I~
    //******************************************                   //~vay7I~
    public static void getCurrentFilename()                        //~vay7R~
    {                                                              //~vay7I~
        if (Dump.Y) Dump.println(CN+"getCurrentFilename");         //~vc4qI~
        try                                                        //~vay7I~
        {                                                          //~vay7I~
            jniGetCurrentFilename();                               //~vay7I~
        }                                                          //~vay7I~
        catch(Exception e)                                         //~vay7I~
        {                                                          //~vay7I~
            Dump.println(e,CN+"getCurrentFilename");              //~vay7I~//~vc4qR~
        }                                                          //~vay7I~
        if (Dump.Y) Dump.println(CN+"getCurrentFilename:type="+Gxeh.mCurrentFilenameType+",name="+Gxeh.mCurrentFilename);//~vay7R~//~vc4qR~
    }                                                              //~vay7I~
    //******************************************                   //~vc1mI~
    public static void updateEnvPath()                             //~vc1mI~
    {                                                              //~vc1mI~
        if (Dump.Y) Dump.println(CN+"updateEnvPath");              //~vc4qI~
        try                                                        //~vc1mI~
        {                                                          //~vc1mI~
            jniSetEnvPath();                                       //~vc1mI~
        }                                                          //~vc1mI~
        catch(Exception e)                                         //~vc1mI~
        {                                                          //~vc1mI~
            Dump.println(e,CN+"updateEnvPath");                   //~vc1mI~//~vc4qR~
        }                                                          //~vc1mI~
        if (Dump.Y) Dump.println(CN+"getCurrentFilename:type="+Gxeh.mCurrentFilenameType+",name="+Gxeh.mCurrentFilename);//~vc1mI~//~vc4qR~
    }                                                              //~vc1mI~
    //******************************************                   //~vc1mI~
    public static void listEnv()                                   //~vc1mI~
    {                                                              //~vc1mI~

        Map<String,String> env=System.getenv();                    //~vc1mR~
        if (Dump.Y) Dump.println("AxeJNI.listEnv size="+env.size());//~vc1mR~
//      System.out.println(env);                                   //~vc1mR~
        for (String key:env.keySet())                              //~vc1mR~
        	if (Dump.Y) Dump.println(CN+"listEnv "+key+"="+env.get(key));//~vc1mR~//~vc4qR~
    }                                                              //~vc1mI~
    //***********************************************************  //~vbrhI~
    //*by S+C+F1/2/3                                               //~vbrhI~
    //***********************************************************  //~vbrhI~
    public static void notifyRulerMode(int Prulermode)             //~vbrhI~
    {                                                              //~vbrhI~
        if (Dump.Y) Dump.println(CN+"notifyRulerMode");            //~vc4qI~
        try                                                        //~vbrhI~
        {                                                          //~vbrhI~
	    	Gxeh.Mrulermode=Prulermode;                            //~vbrhI~
        }                                                          //~vbrhI~
        catch(Exception e)                                         //~vbrhI~
        {                                                          //~vbrhI~
            Dump.println(e,CN+"notifyRulerMode");                 //~vbrhI~//~vc4qR~
        }                                                          //~vbrhI~
    }                                                              //~vbrhI~
//*********************************************                    //~vc2JI~
	private static String getURIPath(String Puri)                  //~vc2JI~
    {                                                              //~vc2JI~
        if (Dump.Y) Dump.println(CN+"getURIPath uri="+Puri);       //~vc4qI~
    	String rc=null;                                            //~vc2JI~
        try                                                        //~vc2JI~
        {                                                          //~vc2JI~
	    	Uri uri=Uri.parse(Puri);                              //~vc2JI~
            String scheme=uri.getScheme();                         //~vc2JI~
		    if (Dump.Y) Dump.println(CN+"getURIPath scheme="+scheme);//~vc2JI~//~vc4qR~
            if (scheme!=null)                                      //~vc2JI~
            {                                                      //~vc2JI~
            	if (scheme.equals("file"))                          //~vc2JI~
                {                                                  //~vc2JI~
                	rc=uri.getPath();                              //~vc2JI~
                }                                                  //~vc2JI~
                else                                               //~vc2JI~
            	if (scheme.equals("content"))                       //~vc2JI~
					rc=getURIPathContent(uri);                     //~vc2JI~
            }                                                      //~vc2JI~
        }                                                          //~vc2JI~
        catch(Exception e)                                         //~vc2JI~
        {                                                          //~vc2JI~
            Dump.println(e,CN+"notifyRulerMode");                 //~vc2JI~//~vc4qR~
        }                                                          //~vc2JR~
        if (rc==null)                                              //~vc2JI~
            Utils.showToastLong(Utils.getStr(R.string.Err_getPathFromUri,Puri));//~vc2JI~
        else                                                       //~vc2JI~
        if (rc.startsWith(Gxeh.sdRootPath))                        //~vc2JI~
            rc=rc.replaceFirst(Gxeh.sdRootPath,Gxeh.sdRoot);       //~vc2JI~
        if (Dump.Y) Dump.println(CN+"getURIPath rc="+rc+",Puri="+Puri);//~vc2JI~//~vc4qR~
        return rc;                                                 //~vc2JI~
    }                                                              //~vc2JI~
	private static String getURIPathContent(Uri Puri)              //~vc2JI~
    {                                                              //~vc2JI~
//                    ArrayOf<String> projection=arrayOf<String>MediaStore.MediaColumns.DATA);//~vc2JI~
//                    if (Dump.Y) Dump.println("AxeJNI.getURIPath prohection="+Utils.toString(projection));//~vc2JI~
//                    Cursaor cursor=AxeG.context.getContentResolver().query(uri,projection,null,null,null);//~vc2JI~
//                    Cursor cursor=AxeG.context.getContentResolver().query(uri,null,null,null,null);//~vc2JI~
//                    if (cursor!=null)                            //~vc2JI~
//                    {                                            //~vc2JI~
//                        if (Dump.Y) Dump.println("AxeJNI.getURIPath colnames="+Utils.toString(cursor.getColumnNames()));//~vc2JI~
//                        if (cursor.moveToFirst())                //~vc2JI~
//                        {                                        //~vc2JI~
//                            rc=cursor.getString(0);              //~vc2JI~
//                            for (int ii=0;ii<cursor.getColumnCount();ii++)//~vc2JI~
//                            {                                    //~vc2JI~
//                                if (Dump.Y) Dump.println("AxeJNI.getURIPath ii="+ii+" str="+cursor.getString(ii));//~vc2JI~
//                            }                                    //~vc2JI~
//                        }                                        //~vc2JI~
//                          cursor.close();                        //~vc2JI~
//                    }                                            //~vc2JI~
		String rc;                                                 //~vc2JI~
//        if (Dump.Y) Dump.println("AxeJNI.getURIPathContent getEncodedPath="+Puri.getEncodedPath());//~vc2JR~
//        if (Dump.Y) Dump.println("AxeJNI.getURIPathContent getEncodedSchemeSpecificPart="+Puri.getEncodedSchemeSpecificPart());//~vc2JR~
//        if (Dump.Y) Dump.println("AxeJNI.getURIPathContent getSchemeSpecificPart="+Puri.getSchemeSpecificPart());//~vc2JR~
//        if (Dump.Y) Dump.println("AxeJNI.getURIPathContent getEncodedUserInfo="+Puri.getEncodedUserInfo());//~vc2JR~
//        if (Dump.Y) Dump.println("AxeJNI.getURIPathContent getUserInfo="+Puri.getUserInfo());//~vc2JR~
//        if (Dump.Y) Dump.println("AxeJNI.getURIPathContent getLastSegmentPath="+Puri.getLastPathSegment());//~vc2JR~
        if (Dump.Y) Dump.println(CN+"getURIPathContent getAutority="+Puri.getAuthority());//~vc2JI~//~vc4qR~
        File f= Environment.getRootDirectory();//TODO test         //~vc2JR~
        try                                                        //~vc2JI~
        {                                                          //~vc2JI~
        if (Dump.Y) Dump.println(CN+"getURIPathContent root: exists="+f.exists()+",getName="+f.getName()+",getPath="+f.getPath()+",getCanonicalPath="+f.getCanonicalPath()+",getAbsolutePath="+f.getAbsolutePath());//~vc2JI~//~vc4qR~
        }                                                          //~vc2JI~
        catch(IOException e)                                       //~vc2JI~
        {                                                          //~vc2JI~
            Dump.println(e,CN+"notifyRulerMode");                 //~vc2JI~//~vc4qR~
        }                                                          //~vc2JI~
        rc=Puri.getPath();                                         //~vc2JI~
        f= new File("/root");                                      //~vc2JR~
        try                                                        //~vc2JI~
        {                                                          //~vc2JI~
            if (Dump.Y)
                Dump.println(CN+"getURIPathContent root: exists=" + f.exists() + ",getName=" + f.getName() + ",getPath=" + f.getPath() + ",getCanonicalPath=" + f.getCanonicalPath() + ",getAbsolutePath=" + f.getAbsolutePath());//~vc2JI~//~vc4qR~
        }
            catch(IOException e)                                       //~vc2JI~
        {                                                          //~vc2JI~
            Dump.println(e,CN+"notifyRulerMode");                 //~vc2JI~//~vc4qR~
        }                                                          //~vc2JI~
        f= new File(rc);                                           //~vc2JI~
        try                                                        //~vc2JI~
        {                                                          //~vc2JI~
            if (Dump.Y)                                            //~vc2JI~
                Dump.println(CN+"getURIPathContent root: exists=" + f.exists() + ",getName=" + f.getName() + ",getPath=" + f.getPath() + ",getCanonicalPath=" + f.getCanonicalPath() + ",getAbsolutePath=" + f.getAbsolutePath());//~vc2JI~//~vc4qR~
        }                                                          //~vc2JI~
            catch(IOException e)                                   //~vc2JI~
        {                                                          //~vc2JI~
            Dump.println(e,CN+"notifyRulerMode");                 //~vc2JI~//~vc4qR~
        }                                                          //~vc2JI~
        if (rc!=null && rc.startsWith("/root/"))                    //~vc2JI~
            rc=rc.replaceFirst("/root/","/");                      //~vc2JI~
		if (Dump.Y) Dump.println(CN+"getURIPathContent rc="+rc);//~vc2JI~//~vc4qR~
        return rc;                                                 //~vc2JI~
    }                                                              //~vc2JI~
//*********************************************                    //~vc2JI~
	public static void uriReceived(String Paction,String Puri)     //~vc2JI~
    {                                                              //~vc2JI~
        if (Dump.Y) Dump.println(CN+"uriReceived action="+Paction+",uri="+Puri);//~vc2JI~//~vc4qR~
        if (Puri!=null)                                            //~vc2JI~
        {                                                          //~vc2JI~
            String path=getURIPath(Puri);                          //~vc2JI~
            if (path!=null)                                        //~vc2JI~
            {                                                      //~vc2JI~
        		String cmd;                                        //~vc2JI~
            	if (Paction.equals(Intent.ACTION_VIEW))            //~vc2JI~
                	cmd="B";  //browse                             //~vc2JR~
            	else                                               //~vc2JI~
                	cmd="E";  //edit                               //~vc2JR~
		        xecmd(cmd,path);                                   //~vc2JI~
            }                                                      //~vc2JI~
        }                                                          //~vc2JI~
        if (Dump.Y) Dump.println(CN+"uriReceived returned");          //~vc2JI~//~vc4qR~
    }                                                              //~vc2JI~
//*********************************************                    //~vc4mI~
	private static void listNativeLibraryDir()                            //~vc4mI~
	{                                                              //~vc4mI~
    	String dir=AG.context.getApplicationInfo().nativeLibraryDir;      //~vc4mI~
        File f=new File(dir);                                       //~vc4mI~
        if (Dump.Y) Dump.println(CN+"listNativeLibraryDir isDir="+f.isDirectory()+",dir="+dir);//~vc4mI~//~vc4qR~
        if (f.isDirectory())                                       //~vc4mI~
        {                                                          //~vc4mI~
        	Gxeh.nativeLibraryDir=dir;                             //~vc4pI~
        	String[] list=f.list();                                //~vc4mI~
	        if (Dump.Y) Dump.println(CN+"listNativeLibraryDir list="+Utils.toString(list));//~vc4mI~//~vc4qR~
            try                                                    //~v77wI~
            {                                                      //~v77wI~
                File[] listFiles=f.listFiles();                    //~v77wI~
		        if (Dump.Y) Dump.println(CN+"listNativeLibraryDir listFiles ctr="+listFiles.length);//~v77wI~
                for (File m:listFiles)                             //~v77wI~
                {                                                  //~v77wI~
                    if (Dump.Y) Dump.println(CN+"listNativeLibraryDir path="+m.getAbsolutePath());//~v77wI~
                    if (Dump.Y) Dump.println(CN+"listNativeLibraryDir canExecute="+m.canExecute()+",canWrite="+m.canWrite()+",canRead="+m.canRead()+",size="+m.length());//~v77wR~
                    if (Dump.Y) Dump.println(CN+"listNativeLibraryDir system=0x"+Long.toHexString(System.currentTimeMillis()));//~v77wR~
                    if (Dump.Y) Dump.println(CN+"listNativeLibraryDir cur Dates=0x"+Long.toHexString(new Date().getTime()));//~v77wR~
                    if (Dump.Y) Dump.println(CN+"listNativeLibraryDir curr="+Utils.getTimeStamp(Utils.TS_DATE_TIME));//~v77wI~
                    if (Dump.Y) Dump.println(CN+"listNativeLibraryDir m="+m+",lastModified=0x"+Long.toHexString(m.lastModified()));//~v77wR~
                    if (Dump.Y) Dump.println(CN+"listNativeLibraryDir lastModified="+Utils.getTimeStamp("yyyy/MM/dd-HH.mm.ss",m.lastModified()));//~v77wR~
                    if (Dump.Y) Dump.println(CN+"listNativeLibraryDir File.getLastModified="+ UFile.getLastModified(m,"yyyy/MM/dd-HH.mm.ss"));//~v77wI~
                }                                                  //~v77wI~
            }                                                      //~v77wI~
            catch(Exception e)                                     //~v77wI~
            {                                                      //~v77wI~
                Dump.printlnNoMsg(e,CN+"listFiles dir="+dir);      //~v77wR~
            }                                                      //~v77wI~
//  		execTest();	//TODO test                                //~v77wR~
        }                                                          //~vc4mI~
    }                                                              //~vc4mI~
//*********************************************                    //~vc4qI~
//* by "sp(shortPath) add" cmd                                     //~vc4qI~
//*********************************************                    //~vc4qI~
    public static int startPicker(int Popt,String Palias/*fmt //spname */)//~vc4qI~
    {                                                              //~vc4qI~
        if (Dump.Y) Dump.println(CN+"startPicker alias="+Palias);  //~vc4qI~
    	int rc=-1;
        try                                                        //~vc4qI~
        {                                                          //~vc4qI~
        	rc=USAF.startPicker(Popt,Palias);
        }                                                          //~vc4qR~
        catch(Exception e)                                         //~vc4qI~
        {                                                          //~vc4qI~
            Dump.println(e,CN+"startPicker");                      //~vc4qR~
        }                                                          //~vc4qI~
        return rc;                                                    //~vc4qI~
    }//usetmonospace                                               //~vc4qI~
//**************************************************               //~vc4qI~
	public static int getDirFD(int Popt,String PstrUri)            //~vc4qI~
    {                                                              //~vc4qI~
        if (Dump.Y) Dump.println(CN+"getDirFD opt="+Popt+",strUri="+PstrUri);//~vc4qR~
        int rc=-1;                                                 //~vc4qI~
      	try                                                        //~vc4qI~
      	{                                                          //~vc4qI~
        	rc=USAF.getDirFD(Popt,PstrUri);                        //~vc4qI~
      	}                                                          //~vc4qI~
      	catch(Exception e)                                         //~vc4qI~
      	{                                                          //~vc4qI~
        	 Dump.println(e,CN+"getDirFD strUri="+PstrUri);        //~vc4qR~
      	}                                                          //~vc4qI~
        if (Dump.Y) Dump.println(CN+"getDirFD exit rc="+rc);       //~vc4qR~
        return rc;                                                 //~vc4qI~
    }                                                              //~vc4qI~
//**************************************************               //~vc4qI~
	public static String getDirDoc(int Popt,String PdirName,String PstrUri)//~vc4qI~
    {                                                              //~vc4qI~
        if (Dump.Y) Dump.println(CN+"getDirDoc opt="+Popt+",dirName="+PdirName+",strUri="+PstrUri);//~vc4qR~
        String membData="";                                        //~vc4qI~
      	try                                                        //~vc4qI~
      	{                                                          //~vc4qI~
        	membData=USAF.udirlistDoc(Popt,PdirName,PstrUri);      //~vc4qR~
      	}                                                          //~vc4qI~
      	catch(Exception e)                                         //~vc4qI~
      	{                                                          //~vc4qI~
        	 Dump.println(e,CN+"getDirDoc dirName="+PdirName+",strUri="+PstrUri);//~vc4qR~
      	}                                                          //~vc4qI~
        if (Dump.Y) Dump.println(CN+"getDirDoc exit dirname="+PdirName+",membData="+membData);//~vc4qR~
        return membData;                                           //~vc4qR~
    }                                                              //~vc4qI~
//**************************************************               //~vc4qI~
	public static String getFileStat(int Popt,String PdirName,String PstrUri,int Ppathlen)//~vc4qR~
    {                                                              //~vc4qI~
        if (Dump.Y) Dump.println(CN+"getFileStat opt="+Popt+",dirName="+PdirName+",strUri="+PstrUri+",pathlen="+Ppathlen);//~vc4qR~
        String membData="";                                        //~vc4qI~
      	try                                                        //~vc4qI~
      	{                                                          //~vc4qI~
        	membData=USAF.ufstatDoc(Popt,PdirName,PstrUri,Ppathlen);//~vc4qR~
      	}                                                          //~vc4qI~
      	catch(Exception e)                                         //~vc4qI~
      	{                                                          //~vc4qI~
        	 Dump.println(e,CN+"getFileStat dirName="+PdirName+",strUri="+PstrUri);//~vc4qI~
      	}                                                          //~vc4qI~
        if (Dump.Y) Dump.println(CN+"getFileStat exit dirname="+PdirName+",membData="+membData);//~vc4qI~
        return membData;                                           //~vc4qI~
    }                                                              //~vc4qI~
//**************************************************               //~vc4sI~
//	public static int openDoc(int Popt,String Pfpath,String PstrUri,String PfpathTemp,String[] Ppbuff,int[] PoptRC)//~vc4sR~
  	public static int openDoc(int Popt,String Pfpath,String PstrUri,String PfpathTemp)//~vc4sI~
    {                                                              //~vc4sI~
        if (Dump.Y) Dump.println(CN+"openDoc opt="+Integer.toHexString(Popt)+",fpath="+Pfpath+",strUri="+PstrUri+",fpathTemp="+PfpathTemp);//~vc4sI~
        int rc=-1;                                                 //~vc4sI~
      	try                                                        //~vc4sI~
      	{                                                          //~vc4sI~
//        	rc=AG.aUSAF2.openDoc(Popt,Pfpath,PstrUri,PfpathTemp,Ppbuff,PoptRC);//~vc4sR~
          	rc=AG.aUSAF2.openDoc(Popt,Pfpath,PstrUri,PfpathTemp);  //~vc4sI~
      	}                                                          //~vc4sI~
      	catch(Exception e)                                         //~vc4sI~
      	{                                                          //~vc4sI~
        	 Dump.println(e,CN+"openDoc fpath="+Pfpath+",strUri="+PstrUri);//~vc4sI~
      	}                                                          //~vc4sI~
        if (Dump.Y) Dump.println(CN+"openDoc exit rc="+rc+",fpath="+Pfpath+",strUri="+PstrUri);//~vc4sI~
        return rc;                                                 //~vc4sI~
    }                                                              //~vc4sI~
    //***********************************************************  //~vc4sI~
    public static void notifyOpenDocResult(int Popt,String Pfpath,int PrcOpt,String PrcString,byte[] PrcByte)//~vc4sI~
    {                                                              //~vc4sI~
      	try                                                        //~vc4sI~
      	{                                                          //~vc4sI~
        	if (Dump.Y) Dump.println(CN+"notifyOpenDocResult opt=0x"+Integer.toHexString(Popt)+",rcOpt=0x"+Integer.toHexString(PrcOpt)+",fpath="+Pfpath);//~vc4sI~
    		jniNotifyOpenDocResult(Popt,Pfpath,PrcOpt,PrcString,PrcByte);//~vc4sI~
      	}                                                          //~vc4sI~
      	catch(Exception e)                                         //~vc4sI~
      	{                                                          //~vc4sI~
        	Dump.println(e,CN+"notifyOpenDocResult fpath="+Pfpath);//~vc4sI~
      	}                                                          //~vc4sI~
    }                                                              //~vc4sI~
//**************************************************               //~vc4sI~
  	public static int fgetsDoc(int Popt,String Pfpath,String PstrUri,int Plen)//~vc4sR~
    {                                                              //~vc4sI~
        if (Dump.Y) Dump.println(CN+"fgetsDoc opt="+Integer.toHexString(Popt)+",fpath="+Pfpath+",strUri="+PstrUri+",len="+Plen);//~vc4sR~
        int rc=-1;                                                 //~vc4sI~
      	try                                                        //~vc4sI~
      	{                                                          //~vc4sI~
          	rc=AG.aUSAF2.fgetsDoc(Popt,Pfpath,PstrUri,Plen);       //~vc4sR~
      	}                                                          //~vc4sI~
      	catch(Exception e)                                         //~vc4sI~
      	{                                                          //~vc4sI~
        	 Dump.println(e,CN+"fgetsDoc fpath="+Pfpath);          //~vc4sI~
      	}                                                          //~vc4sI~
        if (Dump.Y) Dump.println(CN+"fgetsDoc exit rc="+rc+",fpath="+Pfpath);//~vc4sR~
        return rc;                                                 //~vc4sI~
    }                                                              //~vc4sI~
    //***********************************************************  //~vc4sI~
    public static void notify_fgetsDocResult(int Popt,String Pfpath,String Pline,int Pbuffsz)//~vc4sR~
    {                                                              //~vc4sI~
      	try                                                        //~vc4sI~
      	{                                                          //~vc4sI~
        	if (Dump.Y) Dump.println(CN+"notifyFgetsDocResult opt=0x"+Integer.toHexString(Popt)+",fpath="+Pfpath+",buffsz="+Pbuffsz+",line="+Pline);//~vc4sR~
    		jniNotifyfgetsDocResult(Popt,Pfpath,Pline,Pbuffsz);   //~vc4sR~
      	}                                                          //~vc4sI~
      	catch(Exception e)                                         //~vc4sI~
      	{                                                          //~vc4sI~
        	Dump.println(e,CN+"notify_fgetsDocResult fpath="+Pfpath);//~vc4sR~
      	}                                                          //~vc4sI~
    }                                                              //~vc4sI~
//**************************************************               //~vc4sI~
  	public static int freadDoc(int Popt,String Pfpath,int Plen)    //~vc4sI~
    {                                                              //~vc4sI~
        if (Dump.Y) Dump.println(CN+"freadDoc opt="+Integer.toHexString(Popt)+",fpath="+Pfpath+",len="+Plen);//~vc4sI~
        int rc=-1;                                                 //~vc4sI~
      	try                                                        //~vc4sI~
      	{                                                          //~vc4sI~
          	rc=AG.aUSAF2.freadDoc(Popt,Pfpath,Plen);               //~vc4sI~
      	}                                                          //~vc4sI~
      	catch(Exception e)                                         //~vc4sI~
      	{                                                          //~vc4sI~
        	 Dump.println(e,CN+"freadDoc fpath="+Pfpath);          //~vc4sI~
      	}                                                          //~vc4sI~
        if (Dump.Y) Dump.println(CN+"freadDoc exit rc="+rc+",fpath="+Pfpath);//~vc4sI~
        return rc;                                                 //~vc4sI~
    }                                                              //~vc4sI~
    //***********************************************************  //~vc4sI~
    public static void notify_freadDocResult(int Popt,String Pfpath,byte[] Pbuff,int Plen)//~vc4sR~
    {                                                              //~vc4sI~
      	try                                                        //~vc4sI~
      	{                                                          //~vc4sI~
        	if (Dump.Y) Dump.println(CN+"notify_freadDocResult opt=0x"+Integer.toHexString(Popt)+",fpath="+Pfpath+",len="+Plen+",Pbuff="+Pbuff);//~vc4sR~
    		jniNotifyfreadDocResult(Popt,Pfpath,Pbuff,Plen);      //~vc4sR~
      	}                                                          //~vc4sI~
      	catch(Exception e)                                         //~vc4sI~
      	{                                                          //~vc4sI~
        	Dump.println(e,CN+"notify_freadDocResult fpath="+Pfpath);//~vc4sR~
      	}                                                          //~vc4sI~
    }                                                              //~vc4sI~
//**************************************************               //~vc4sI~
  	public static int fwriteDoc(int Popt,String Pfpath,byte[] Pbuff,int Plen)//~vc4sR~
    {                                                              //~vc4sI~
        if (Dump.Y) Dump.println(CN+"fwriteDoc opt="+Integer.toHexString(Popt)+",fpath="+Pfpath+",len="+Plen+",byte buff="+Pbuff);//~vc4sR~
        int rc=-1;                                                 //~vc4sI~
      	try                                                        //~vc4sI~
      	{                                                          //~vc4sI~
          	rc=AG.aUSAF2.fwriteDoc(Popt,Pfpath,Pbuff,Plen);        //~vc4sR~
      	}                                                          //~vc4sI~
      	catch(Exception e)                                         //~vc4sI~
      	{                                                          //~vc4sI~
        	 Dump.println(e,CN+"fwriteDoc fpath="+Pfpath);         //~vc4sI~
      	}                                                          //~vc4sI~
        if (Dump.Y) Dump.println(CN+"fwriteDoc exit rc="+rc+",fpath="+Pfpath);//~vc4sI~
        return rc;                                                 //~vc4sI~
    }                                                              //~vc4sI~
    //***********************************************************  //~vc4sI~
    public static void notify_fwriteDocResult(int Popt,String Pfpath,int Plen)//~vc4sI~
    {                                                              //~vc4sI~
      	try                                                        //~vc4sI~
      	{                                                          //~vc4sI~
        	if (Dump.Y) Dump.println(CN+"notify_fwriteDocResult opt=0x"+Integer.toHexString(Popt)+",fpath="+Pfpath+",len="+Plen);//~vc4sI~
    		jniNotifyfwriteDocResult(Popt,Pfpath,Plen);            //~vc4sI~
      	}                                                          //~vc4sI~
      	catch(Exception e)                                         //~vc4sI~
      	{                                                          //~vc4sI~
        	Dump.println(e,CN+"notify_fwriteDocResult fpath="+Pfpath);//~vc4sI~
      	}                                                          //~vc4sI~
    }                                                              //~vc4sI~
//**************************************************               //~vc4sI~
  	public static int fcloseDoc(int Popt,String Pfpath)            //~vc4sI~
    {                                                              //~vc4sI~
        if (Dump.Y) Dump.println(CN+"fcloseDoc opt="+Integer.toHexString(Popt)+",fpath="+Pfpath);//~vc4sI~
        int rc=-1;                                                 //~vc4sI~
      	try                                                        //~vc4sI~
      	{                                                          //~vc4sI~
          	rc=AG.aUSAF2.fcloseDoc(Popt,Pfpath);                   //~vc4sI~
      	}                                                          //~vc4sI~
      	catch(Exception e)                                         //~vc4sI~
      	{                                                          //~vc4sI~
        	 Dump.println(e,CN+"fcloseDoc fpath="+Pfpath);         //~vc4sI~
      	}                                                          //~vc4sI~
        if (Dump.Y) Dump.println(CN+"fcloseDoc exit rc="+rc+",fpath="+Pfpath);//~vc4sI~
        return rc;                                                 //~vc4sI~
    }                                                              //~vc4sI~
//**************************************************               //~v77cR~
  	public static int mkdirDoc(int Popt,String Pfpath,String PstrUri)//~v77cR~
    {                                                              //~v77cR~
        if (Dump.Y) Dump.println(CN+"mkdirDoc opt="+Integer.toHexString(Popt)+",fpath="+Pfpath+",strUri="+PstrUri);//~v77cR~
        int rc=-1;                                                 //~v77cR~
      	try                                                        //~v77cR~
      	{                                                          //~v77cR~
          	rc=AG.aUSAF2.mkdirDoc(Popt,Pfpath,PstrUri);            //~v77cR~
      	}                                                          //~v77cR~
      	catch(Exception e)                                         //~v77cR~
      	{                                                          //~v77cR~
        	 Dump.println(e,CN+"mkdirDoc fpath="+Pfpath+",strUri="+PstrUri);//~v77cR~
      	}                                                          //~v77cR~
        if (Dump.Y) Dump.println(CN+"mkdirDoc exit rc="+rc+",fpath="+Pfpath+",strUri="+PstrUri);//~v77cR~
        return rc;                                                 //~v77cR~
    }                                                              //~v77cR~
//**************************************************               //~v77fR~
  	public static int rmdirDoc(int Popt,String Pfpath,String PstrUri)//~v77fR~
    {                                                              //~v77fR~
        if (Dump.Y) Dump.println(CN+"rmdirDoc opt="+Integer.toHexString(Popt)+",fpath="+Pfpath+",strUri="+PstrUri);//~v77fR~
        int rc=-1;                                                 //~v77fR~
      	try                                                        //~v77fR~
      	{                                                          //~v77fR~
          	rc=AG.aUSAF2.rmdirDoc(Popt,Pfpath,PstrUri);            //~v77fR~
      	}                                                          //~v77fR~
      	catch(Exception e)                                         //~v77fR~
      	{                                                          //~v77fR~
        	 Dump.println(e,CN+"rmdirDoc fpath="+Pfpath+",strUri="+PstrUri);//~v77fR~
      	}                                                          //~v77fR~
        if (Dump.Y) Dump.println(CN+"rmdirDoc exit rc="+rc+",fpath="+Pfpath+",strUri="+PstrUri);//~v77fR~
        return rc;                                                 //~v77fR~
    }                                                              //~v77fR~
//**************************************************               //~v77dI~
  	public static int unlinkDoc(int Popt,String Pfpath,String PstrUri)//~v77dI~
    {                                                              //~v77dI~
        if (Dump.Y) Dump.println(CN+"unlinkDoc opt="+Integer.toHexString(Popt)+",fpath="+Pfpath+",strUri="+PstrUri);//~v77dI~
        int rc=-1;                                                 //~v77dI~
      	try                                                        //~v77dI~
      	{                                                          //~v77dI~
          	rc=AG.aUSAF2.unlinkDoc(Popt,Pfpath,PstrUri);           //~v77dI~
      	}                                                          //~v77dI~
      	catch(Exception e)                                         //~v77dI~
      	{                                                          //~v77dI~
        	 Dump.println(e,CN+"unlinkDoc fpath="+Pfpath+",strUri="+PstrUri);//~v77dI~
      	}                                                          //~v77dI~
        if (Dump.Y) Dump.println(CN+"unlinkDoc exit rc="+rc+",fpath="+Pfpath+",strUri="+PstrUri);//~v77dI~
        return rc;                                                 //~v77dI~
    }                                                              //~v77dI~
//**************************************************               //~v77gI~
  	public static int renameDoc(int Popt,String Pfpath,String PstrUri,String Pnew)//~v77gI~
    {                                                              //~v77gI~
        if (Dump.Y) Dump.println(CN+"renameDoc opt="+Integer.toHexString(Popt)+",fpath="+Pfpath+",new="+Pnew+",strUri="+PstrUri);//~v77gR~
        int rc=-1;                                                 //~v77gI~
      	try                                                        //~v77gI~
      	{                                                          //~v77gI~
          	rc=AG.aUSAF2.renameDoc(Popt,Pfpath,PstrUri,Pnew);      //~v77gI~
      	}                                                          //~v77gI~
      	catch(Exception e)                                         //~v77gI~
      	{                                                          //~v77gI~
        	 Dump.println(e,CN+"renameDoc fpath="+Pfpath+",new="+Pnew+",strUri="+PstrUri);//~v77gI~
      	}                                                          //~v77gI~
        if (Dump.Y) Dump.println(CN+"renameDoc exit rc="+rc+",fpath="+Pfpath+",new="+Pnew+",strUri="+PstrUri);//~v77gR~
        return rc;                                                 //~v77gI~
    }                                                              //~v77gI~
//**************************************************               //~v77eI~
  	public static int opendirDoc(int Popt,String Pfpath,String PstrUri)//~v77eI~
    {                                                              //~v77eI~
        if (Dump.Y) Dump.println(CN+"opendirDoc opt="+Integer.toHexString(Popt)+",fpath="+Pfpath+",strUri="+PstrUri);//~v77eI~
        int rc=-1;                                                 //~v77eI~
      	try                                                        //~v77eI~
      	{                                                          //~v77eI~
          	rc=AG.aUSAF2.opendirDoc(Popt,Pfpath,PstrUri);          //~v77eI~
      	}                                                          //~v77eI~
      	catch(Exception e)                                         //~v77eI~
      	{                                                          //~v77eI~
        	 Dump.println(e,CN+"opendirDoc fpath="+Pfpath+",strUri="+PstrUri);//~v77eI~
      	}                                                          //~v77eI~
        if (Dump.Y) Dump.println(CN+"opendirDoc exit rc="+rc+",fpath="+Pfpath+",strUri="+PstrUri);//~v77eI~
        return rc;                                                 //~v77eI~
    }                                                              //~v77eI~
    //***********************************************************  //~v77eI~
    public static void notify_opendirDocResult(int Popt,String Pfpath,int Pfd)//~v77eI~
    {                                                              //~v77eI~
      	try                                                        //~v77eI~
      	{                                                          //~v77eI~
        	if (Dump.Y) Dump.println(CN+"notify_opendirDocResult opt=0x"+Integer.toHexString(Popt)+",fpath="+Pfpath+",fd="+Pfd);//~v77eI~
    		jniNotifyopendirDocResult(Popt,Pfpath,Pfd);            //~v77eI~
      	}                                                          //~v77eI~
      	catch(Exception e)                                         //~v77eI~
      	{                                                          //~v77eI~
        	Dump.println(e,CN+"notify_opendirDocResult fpath="+Pfpath);//~v77eI~
      	}                                                          //~v77eI~
    }                                                              //~v77eI~
//**************************************************               //~v77gI~
  	public static int copyDoc(int Popt,String Psrc,String PstrUriSrc,String Ptgt,String PstrUriTgt)//~v77gI~
    {                                                              //~v77gI~
        if (Dump.Y) Dump.println(CN+"copyDoc opt="+Integer.toHexString(Popt)+",src="+Psrc+",tgt="+Ptgt+",strUriS="+PstrUriSrc+",strUriT="+PstrUriTgt);//~v77gI~
        int rc=-1;                                                 //+v77gI~s
      	try                                                        //~v77gI~
      	{                                                          //~v77gI~
          	rc=AG.aUSAF2.copyDoc(Popt,Psrc,PstrUriSrc,Ptgt,PstrUriTgt);//~v77gI~
      	}                                                          //~v77gI~
      	catch(Exception e)                                         //~v77gI~
      	{                                                          //~v77gI~
        	 Dump.println(e,CN+"copyDoc src="+Psrc+",tgt="+Ptgt);  //~v77gI~
      	}                                                          //~v77gI~
        if (Dump.Y) Dump.println(CN+"copyDoc exit rc="+rc+",src="+Psrc+",tgt="+Ptgt);//~v77gI~
        return rc;                                                 //~v77gI~
    }                                                              //~v77gI~
    //***********************************************************  //~v77gI~
    public static void notify_copyDocResult(int Popt,String Psrc,String Ptgt,int PerrSrc,int PerrTgt)//~v77gI~
    {                                                              //~v77gI~
      	try                                                        //~v77gI~
      	{                                                          //~v77gI~
        	if (Dump.Y) Dump.println(CN+"notify_copyDocResult opt=0x"+Integer.toHexString(Popt)+",errS="+PerrSrc+",errT="+PerrTgt+",src="+Psrc+",tgt="+Ptgt);//~v77gI~
    		jniNotifycopyDocResult(Popt,PerrSrc,PerrTgt);          //~v77gI~
      	}                                                          //~v77gI~
      	catch(Exception e)                                         //~v77gI~
      	{                                                          //~v77gI~
        	Dump.println(e,CN+"notify_copyDocResult src="+Psrc+",tgt="+Ptgt);//~v77gI~
      	}                                                          //~v77gI~
    }                                                              //~v77gI~
//**************************************************               //~v77gI~
  	public static int statDoc(int Popt,String Pfpath,String PstrUri)//~v77gI~
    {                                                              //~v77gI~
        if (Dump.Y) Dump.println(CN+"statDoc opt="+Integer.toHexString(Popt)+",fpath="+Pfpath+",strUri="+PstrUri);//~v77gI~
        int rc=-1;                                                 //~v77gI~
      	try                                                        //~v77gI~
      	{                                                          //~v77gI~
          	rc=AG.aUSAF2.statDoc(Popt,Pfpath,PstrUri);             //~v77gI~
      	}                                                          //~v77gI~
      	catch(Exception e)                                         //~v77gI~
      	{                                                          //~v77gI~
        	 Dump.println(e,CN+"statDoc fpath="+Pfpath+",strUri="+PstrUri);//~v77gI~
      	}                                                          //~v77gI~
        if (Dump.Y) Dump.println(CN+"statDoc exit rc="+rc+",fpath="+Pfpath+",strUri="+PstrUri);//~v77gI~
        return rc;                                                 //~v77gI~
    }                                                              //~v77gI~
    //***********************************************************  //~v77gI~
    public static void notify_statDocResult(int Popt,String Pfpath,int Pfd)//~v77gI~
    {                                                              //~v77gI~
      	try                                                        //~v77gI~
      	{                                                          //~v77gI~
        	if (Dump.Y) Dump.println(CN+"notify_statDocResult opt=0x"+Integer.toHexString(Popt)+",fpath="+Pfpath+",fd="+Pfd);//~v77gI~
    		jniNotifystatDocResult(Popt,Pfpath,Pfd);               //~v77gI~
      	}                                                          //~v77gI~
      	catch(Exception e)                                         //~v77gI~
      	{                                                          //~v77gI~
        	Dump.println(e,CN+"notify_statDocResult fpath="+Pfpath);//~v77gI~
      	}                                                          //~v77gI~
    }                                                              //~v77gI~
//**************************************************               //~v77wI~
  	public static int getDocPath(int Popt,String Pfpath,String PstrUri)//~v77wI~
    {                                                              //~v77wI~
        if (Dump.Y) Dump.println(CN+"getDocPath opt="+Integer.toHexString(Popt)+",fpath="+Pfpath+",strUri="+PstrUri);//~v77wI~
        int rc=-1;                                                 //~v77wI~
      	try                                                        //~v77wI~
      	{                                                          //~v77wI~
          	rc=AG.aUSAF.getDocPath(Popt,Pfpath,PstrUri);           //~v77wI~
      	}                                                          //~v77wI~
      	catch(Exception e)                                         //~v77wI~
      	{                                                          //~v77wI~
        	 Dump.println(e,CN+"getDocPath fpath="+Pfpath+",strUri="+PstrUri);//~v77wI~
      	}                                                          //~v77wI~
        if (Dump.Y) Dump.println(CN+"getDocPath exit rc="+rc+",fpath="+Pfpath+",strUri="+PstrUri);//~v77wI~
        return rc;                                                 //~v77wI~
    }                                                              //~v77wI~
    //***********************************************************  //~v77wI~
    public static void notify_getDocPathResult(int Popt,String Ppath)//~v77wI~
    {                                                              //~v77wI~
      	try                                                        //~v77wI~
      	{                                                          //~v77wI~
        	if (Dump.Y) Dump.println(CN+"notify_getDocPathResult opt=0x"+Integer.toHexString(Popt)+",path="+Ppath);//~v77wI~
			jniNotifygetDocPathResult(Popt,Ppath);                 //~v77wI~
      	}                                                          //~v77wI~
      	catch(Exception e)                                         //~v77wI~
      	{                                                          //~v77wI~
        	Dump.println(e,CN+"notify_getDocPathResult path="+Ppath);//~v77wI~
      	}                                                          //~v77wI~
    }                                                              //~v77wI~
    //***********************************************************  //~v77wI~
    public static void execTest()                                       //~v77wI~
    {                                                              //~v77wI~
        if (Dump.Y) Dump.println(CN+"execTest isDebuggable="+AxeG.isDebuggable);//~v77wI~
    //if (AxeG.isDebuggable)                                     //~v77wI~
	//	    execNativeTool("xtestenv");                            //~v77wI~
    //    else                                                       //~v77wI~
		execNativeTool("libaxejni.so");                            //~v77wI~
    	String pgm="libxtestenv_so.so";                                  //~v77wR~
		execNativeTool(pgm);                                       //~v77wR~
		execNativeTool("libxtestenv.so");                          //~v77wI~
		execNativeTool("libxfg_so.so");                            //~v77wI~
    }                                                              //~v77wI~
    //***********************************************************  //~v77wI~
    @TargetApi(26)                                                 //~v77wI~
    public static void execNativeTool(String Ptool)                //~v77wR~
    {                                                              //~v77wI~
    	String pgm;                                                //~v77wI~
    if (true )                                                     //~v77wR~
    	pgm=Gxeh.nativeLibraryDir+"/"+Ptool;                       //~v77wR~
    else                                                           //~v77wI~
    	pgm=Ptool;                                                 //~v77wI~
        if (Dump.Y) Dump.println(CN+"execNativeTool name="+Ptool+"\n,pgm="+pgm);//~v77wR~
      	try                                                        //~v77wI~
      	{                                                          //~v77wI~
            BufferedReader in;                                     //~v77wM~
			Process p;                                             //~v77wI~
        if (true)                                                  //~v77wR~
        {                                                          //~v77wI~
			ProcessBuilder pb=new ProcessBuilder(pgm,"abc");       //~v77wR~
		    String wkdir=AxeProp.getWkdir();                       //~v77wI~
            List<String> args=pb.command();                        //~v77wI~
            String[] arr=args.toArray(new String[args.size()]);      //~v77wI~
        	if (Dump.Y) Dump.println(CN+"args="+ Arrays.toString(arr));//~v77wI~
		    pb.directory(new File(wkdir));                         //~v77wI~
            pb.redirectErrorStream(true);	//merge stderr to stdout//~v77wI~
			p=pb.start();                                          //~v77wI~
        	if (Dump.Y) Dump.println(CN+"execNativeTool after start");//~v77wI~
            InputStream is=p.getInputStream();                     //~v77wI~
            in=new BufferedReader(new InputStreamReader(is));      //~v77wI~
        }                                                          //~v77wI~
        else                                                       //~v77wI~
        if (false)                                                 //~v77wR~
        {                                                          //~v77wI~
			ProcessBuilder pb=new ProcessBuilder(pgm,""/*arg*/);   //~v77wR~
		    String wkdir=AxeProp.getWkdir();                       //~v77wI~
		    pb.directory(new File(wkdir));                         //~v77wI~
            String stdofnm=wkdir+"/redirect_"+Ptool;               //~v77wI~
            if (Dump.Y) Dump.println(CN+"execNativeToolstdofnm="+stdofnm);//~v77wI~
            File stdo=new File(stdofnm);                           //~v77wR~
//          FileOutputStream fos=new FileOutputStream(stdo);       //~v77wR~
//          fos.close();                                           //~v77wR~
            pb.redirectErrorStream(true);	//merge stderr to stdout//~v77wI~
            pb.redirectOutput(ProcessBuilder.Redirect.to(stdo));   //~v77wR~
			p=pb.start();                                          //~v77wR~
        	if (Dump.Y) Dump.println(CN+"execNativeTool after start");//~v77wI~
            in=new BufferedReader(new FileReader(stdo));           //~v77wM~
        }                                                          //~v77wI~
        else                                                       //~v77wI~
        {                                                          //~v77wI~
        	Runtime rt=Runtime.getRuntime();                       //~v77wI~
            String[] prm=new String[]{pgm};                        //~v77wI~
            p=rt.exec(prm);                                        //~v77wR~
        	if (Dump.Y) Dump.println(CN+"execNativeTool process exit");//~v77wI~
            InputStream is=p.getInputStream();                     //~v77wI~
            in=new BufferedReader(new InputStreamReader(is));      //~v77wI~
        }                                                          //~v77wI~
            while(true)                                            //~v77wI~
            {                                                      //~v77wI~
            	String line=in.readLine();                         //~v77wI~
                if (line==null)                                    //~v77wI~
                	break;                                         //~v77wI~
		        if (Dump.Y) Dump.println(CN+"execNativeTool stdout="+line);//~v77wI~
            }                                                      //~v77wI~
//          in.close();                                            //~v77wR~
        	if (Dump.Y) Dump.println(CN+"execNativeTool stdout eof");//~v77wI~
            int prc=p.waitFor();                                   //~v77wR~
        	if (Dump.Y) Dump.println(CN+"execNativeTool after waitFor rc="+prc);//~v77wR~
      	}                                                          //~v77wI~
      	catch(Exception e)                                         //~v77wI~
      	{                                                          //~v77wI~
        	Dump.println(e,CN+"execNativeTool tool="+Ptool);       //~v77wI~
      	}                                                          //~v77wI~
    }                                                              //~v77wI~
//**************************************************               //~vc5cI~
  	public static int notifyAllSP(String PallSP)                   //~vc5cI~
    {                                                              //~vc5cI~
        if (Dump.Y) Dump.println(CN+"notifyAllSP allSP="+PallSP+",old="+AG.aUSAF.yourSharedFolder);//~vc5cR~
        AG.aUSAF.yourSharedFolder=PallSP;                          //~vc5cI~
        return 0;                                                  //~vc5cI~
    }                                                              //~vc5cI~
}//class
