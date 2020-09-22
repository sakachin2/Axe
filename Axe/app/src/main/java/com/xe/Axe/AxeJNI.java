//CID://+vc2XR~:   update#=   61                                   //~vc2WR~//~vc2XR~
//*************************************************************
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

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import com.ahsv.AG;
import com.ahsv.utils.Utils;

public class AxeJNI
    	implements AxeTimerI
{
	private static final String JNILIB="axejni";
//  public static native void jniInit(String Psdpath,String Pwkdir,String Plocale,int Popttrace);//~vc1fR~
    public static native void jniInit(String PsdRoot,String Psdpath,String Pwkdir,String Plocale,int Popttrace);//~vc1fI~
    public static native int jniKbdMsg(int Paction,int  Pkey,int Pscan);
    public static native int jniMouseMsg(int Paction,int Pbutton,int Pflag,int Px,int Py);
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
        }
        catch(Exception e)
        {
             Dump.println(e,"JNI:init");                           //~vay0R~
        }
    }
//*********************************************
	public static void setScreenSize(int Pw,int Ph)
    {
//      if (Dump.Y) Dump.println("jniSetScreenSize w="+Pw+",h="+Ph);//~v6k1R~
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
        if (Dump.Y) Dump.println("from jni call beep");
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
       if (Dump.Y) Dump.println("from jni call quit");
//  	Utils.exit(0,true/*kill process*/);                        //~vaxaR~
	    Utils.exit(0);  //finish()                         //~vaxaI~
    }
//*********************
	public static void get_charset(byte[] Pcharset)
    {
        Charset cs=Charset.defaultCharset();
        String  name=cs.displayName();
        if (Dump.Y) Dump.println("from jni call get_charset displayname="+name);
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
    	return Axecsub2.usetmonospace_ligature(Popt,Ppdata,Plen,Pcellw);
    }
    //===============================================================================
    public static void draw_layout_with_color_monospace(int[] Pucs,int Plen,int Px,int Py,int Pfg,int Pbg,float[] Pfpostb)
    {
//        if (Dump.Y) Dump.println("from jni call draw_layout_width_color_monospace len="+Plen+",x="+Px+",y="+Py+",fg="+Integer.toHexString(Pfg)+",bg="+Integer.toHexString(Pbg));
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
//        if (Dump.Y) Dump.println("from jni call draw_layout_width_color_ligature len="+Plen+",x="+Px+",y="+Py+",fg="+Integer.toHexString(Pfg)+",bg="+Integer.toHexString(Pbg));
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
      try                                                          //~vac6I~
      {                                                            //~vac6I~
//      if (Dump.Y) Dump.println("draw_rectangle fill="+Pfill+",x="+Px+",y="+Py+",xe="+(Px+Pw)+",ye="+(Py+Ph));//~v6k1R~
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
    public static void gxedlg_init()
    {
        if (Dump.Y) Dump.println("gxedlg_init");
    	try
        {
	        Axegxedlg.gxedlg_init();
	    }                                                          //~vay0R~
        catch(Exception e)
        {
            Dump.println(e,"JNI:gxedlg_init");                     //~vay0R~
        }
    }
    //===============================================================================
    public static void gxepage_init()
    {
        if (Dump.Y) Dump.println("gxepage_init:NOP yet");
    }
    //===============================================================================
    public static void draw_drawable()
    {
        if (Dump.Y) Dump.println("draw_drawable");
        AxeG.axeScreen.uinvalidate();
    }
    //===============================================================================
    public static View lookup_widget(String Pname)
    {
        if (Dump.Y) Dump.println("lookup_widget "+Pname);
        View v=AxeG.mainView;	//current(port or land)
        return v;
    }
    //===============================================================================
    public static int umsgbox(String Pmsg,int Pflag)
    {
    	int rc=Axecsub.IDOK;
        if (Dump.Y) Dump.println("umsgbo2 "+Pmsg+",flag="+Integer.toHexString(Pflag));
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
        if (Dump.Y) Dump.println("umsgbox2 "+Pmsg);
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
        if (Dump.Y) Dump.println("terminate");
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
        if (Dump.Y) Dump.println("notifyOptionChangedOther");
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
        if (Dump.Y) Dump.println("notifyOptionChangedOtherPermission Gxeh.axeStatus="+Gxeh.axeStatus);//~vc2WR~
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
            Dump.println(e,"JNI:optionChangedOtherPermission");    //~vc2WI~
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
            Dump.println(e,"JNI:optionChangedColor");              //~vay0R~
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
            Dump.println(e,"JNI:optionChangedFont");               //~vay0R~
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
        if (Dump.Y) Dump.println("fullDraw resize="+Presize);
        try
        {
        	int p=Presize?1:0;
            jniFullDraw(p);
            if (Dump.Y) Dump.println("jni call jniFullDraw");
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:fullDraw");                        //~vay0R~
        }
    }
    //===============================================================================
    public static int timeout_add(int Pinterval,int Ptimerid,long Pfunction)
    {
        if (Dump.Y) Dump.println("timeout_add interval="+Pinterval+",id="+Integer.toHexString(Ptimerid));
        int handler=Axecsub.usettimer(Ptimerid,Pinterval,Pfunction);
        return handler;
    }
    //===============================================================================
    public static void OnTimer(int Ptimerid,long Pcallback)
    {
        if (Dump.Y) Dump.println("AxeJNI OnTimer id="+Integer.toHexString(Ptimerid));
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
        if (Dump.Y) Dump.println("timeout_remove id="+Integer.toHexString(Ptimerid));
        AxeTimer.remove(Ptimerid);
        return;
    }
    //===============================================================================
    public static int[] getSampleColor()
    {
        if (Dump.Y) Dump.println("getSampleColor");
        int[] samplecolor=new int[3];
        try
        {
            jniGetSampleColor(samplecolor);
            if (Dump.Y) Dump.println("jni call GetSampleColor");
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:getSampleColor");                  //~vay0R~
            samplecolor[0]=Color.WHITE;
            samplecolor[1]=Color.YELLOW;
            samplecolor[1]=Color.GREEN;
        }
        return samplecolor;
    }
    //===============================================================================
    public static void setTraceOption(int Ptrace)
    {
        if (Dump.Y) Dump.println("setTraceOpt opt="+Ptrace);
      try                                                          //~vay0I~
      {                                                            //~vay0I~
        if (preinitCalled)
            jniSetTraceOpt(Ptrace);
      }                                                            //~vay0I~
      catch(Exception e)                                           //~vay0I~
      {                                                            //~vay0I~
        Dump.println(e,"JNI:setTraceOption");                      //~vay0I~
      }                                                            //~vay0I~
        return;
    }
    //===============================================================================//~vay0I~
    public static void setDebugOption(int Pdebugopt)               //~vay0I~
    {                                                              //~vay0I~
        if (Dump.Y) Dump.println("setDebugOption opt="+Pdebugopt); //~vay0I~
      try                                                          //~vay0I~
      {                                                            //~vay0I~
        if (preinitCalled)                                         //~vay0I~
            jniSetDebugOpt(Pdebugopt);                                //~vay0I~
      }                                                            //~vay0I~
      catch(Exception e)                                           //~vay0I~
      {                                                            //~vay0I~
        Dump.println(e,"JNI:setDebugOption");                      //~vay0I~
      }                                                            //~vay0I~
        return;                                                    //~vay0I~
    }                                                              //~vay0I~
    //**************************************
    //*Clipboard
    //**************************************
    public static void clipboard_get()
    {
        if (Dump.Y) Dump.println("clipboard_get");
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
            Dump.println(e,"JNI:clipboard_wait_for_text");         //~vay0R~
            str=null;
        }
        return str;
    }
    //***************************
    public static void clipboard_set_text(String Pstr)
    {
        if (Dump.Y) Dump.println("clipboard_set_text str="+Pstr);
        try
        {
        	Axecsub2.clipboard_set_text(Pstr);
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:clipboard_set_text");              //~vay0R~
        }
    }
    //**************************************
    //*PopupMenu
    //**************************************
    public static void menu_popup(int[] Penabletbl)
    {
        if (Dump.Y) Dump.println("AxeJNI:menu_popup");
        try
        {
        	AxeG.axeMenu.popupMenu(Penabletbl);
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:menu_popup");                      //~vay0R~
        }
    }
    //**************************************
    //*ContextMenu
    //**************************************
    public static void onContextMenuClosed()
    {
        if (Dump.Y) Dump.println("AxeJNI:onContextMenuClosed");
        try
        {
            jniOnContextMenuClosed();
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:onContextMenuClosed");             //~vay0R~
        }
    }
    //**************************************
    //*Cut and Paste
    //**************************************
    public static void onEditCut()
    {
        if (Dump.Y) Dump.println("AxeJNI:onEditCut");
        try
        {
            jniOnEditCut();
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:onEitCut");                        //~vay0R~
        }
    }
    public static void onEditCopy()
    {
        if (Dump.Y) Dump.println("AxeJNI:onEditCopy");
        try
        {
            jniOnEditCopy();
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:onEitCopy");                       //~vay0R~
        }
    }
    public static void onEditClear()
    {
        if (Dump.Y) Dump.println("AxeJNI:onEditClear");
        try
        {
            jniOnEditClear();
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:onEitClear");                      //~vay0R~
        }
    }
    public static void onEditPasteV()
    {
        if (Dump.Y) Dump.println("AxeJNI:onEditPasteV");
        try
        {
            jniOnEditPasteV();
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:onEitPasteV");                     //~vay0R~
        }
    }
    public static void onEditPasteIns()
    {
        if (Dump.Y) Dump.println("AxeJNI:onEditPasteIns");
        try
        {
            jniOnEditPasteIns();
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:onEitPasteIns");                   //~vay0R~
        }
    }
    public static void onEditPasteRep()
    {
        if (Dump.Y) Dump.println("AxeJNI:onEditPasteRep");
        try
        {
            jniOnEditPasteRep();
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:onEitPasteRep");                   //~vay0R~
        }
    }
    //**************************************                       //~vainI~
    public static void onFileEnd()                                 //~vainI~
    {                                                              //~vainI~
        if (Dump.Y) Dump.println("AxeJNI:onFileEnd");              //~vainI~
        try                                                        //~vainI~
        {                                                          //~vainI~
            jniOnFileEnd();                                        //~vainI~
        }                                                          //~vainI~
        catch(Exception e)                                         //~vainI~
        {                                                          //~vainI~
            Dump.println(e,"JNI:onFileEnd");                       //~vay0R~
        }                                                          //~vainI~
    }                                                              //~vainI~
    //**************************************                       //~vay7R~
    public static void onFileSave(String Pfnm)                     //~vay7R~
    {                                                              //~vay7R~
        if (Dump.Y) Dump.println("AxeJNI:onFileSave");             //~vay7R~
        try                                                        //~vay7R~
        {                                                          //~vay7R~
	        AxeJNI.xecmd("Save",Pfnm);                             //~vay7R~
        }                                                          //~vay7R~
        catch(Exception e)                                         //~vay7R~
        {                                                          //~vay7R~
            Dump.println(e,"JNI:onFileSave");                      //~vay7R~
        }                                                          //~vay7R~
    }                                                              //~vay7R~
    public static void onFileCancel()                              //~vainI~
    {                                                              //~vainI~
        if (Dump.Y) Dump.println("AxeJNI:onFileCancel");           //~vainI~
        try                                                        //~vainI~
        {                                                          //~vainI~
            jniOnFileCancel();                                     //~vainI~
        }                                                          //~vainI~
        catch(Exception e)                                         //~vainI~
        {                                                          //~vainI~
            Dump.println(e,"JNI:onFileCancel");                    //~vay0R~
        }                                                          //~vainI~
    }                                                              //~vainI~
    public static void onFileOpenWith()                            //~vainI~
    {                                                              //~vainI~
        if (Dump.Y) Dump.println("AxeJNI:onFileOpenWith");         //~vainI~
        try                                                        //~vainI~
        {                                                          //~vainI~
            jniOnFileOpenWith();                                   //~vainI~
        }                                                          //~vainI~
        catch(Exception e)                                         //~vainI~
        {                                                          //~vainI~
            Dump.println(e,"JNI:onFileOpenWith");                  //~vay0R~
        }                                                          //~vainI~
    }                                                              //~vainI~
    //**************************************
    //*swipe    dist<0:left/up, dist>0: right,down
    //*         rate: 0<...<100	/rate of screen W/H
    //**************************************
    public static void swipeHorizontal(int PmetaState,int Pdist,int Prate)
    {
        if (Dump.Y) Dump.println("AxeJNI:swipeHorizontal dist="+Pdist+",rate="+Prate+",meta="+Integer.toHexString(PmetaState));
        try
        {
            jniSwipeHorizontal(PmetaState,Pdist,Prate);
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:onSwipeHorizontal");               //~vay0R~
        }
    }
    public static void swipeVertical(int PmetaState,int Pdist,int Prate)
    {
        if (Dump.Y) Dump.println("AxeJNI:swipeVertical dist="+Pdist+",rate="+Prate+",meta="+Integer.toHexString(PmetaState));
        try
        {
            jniSwipeVertical(PmetaState,Pdist,Prate);
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:onSwipeVertical");                 //~vay0R~
        }
    }
    //**************************************
    //*OpenWith
    //**************************************
    public static String file2url(String Plocalfile)
    {
        String rc=null;
    //***********************
        if (Dump.Y) Dump.println("AxeJNI:file2url file="+Plocalfile);
        try
        {
            rc=AxeActivity.file2url(Plocalfile);
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:file2url:"+Plocalfile);
        }
        return rc;
    }
    public static String getMimeType(String Purl)
    {
        String rc=null;
    //***********************
        if (Dump.Y) Dump.println("AxeJNI:getMimeApplication mimetype="+Purl);
        try
        {
            rc=AxeActivity.getMimeType(Purl);
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:getMimeType:"+Purl);
        }
        return rc;
    }
    public static int getMimeApplication(String Pmimetype)
    {
        int rc=4;
    //***********************
        if (Dump.Y) Dump.println("AxeJNI:getMimeApplication mimetype="+Pmimetype);
        try
        {
            rc=AxeActivity.getMimeApplication(Pmimetype);
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:getMimeApplication:"+Pmimetype);
        }
        return rc;
    }
    public static int getMimeApplication_dataandtype(String Puri,String Pmimetype)
    {
        int rc=4;
    //***********************
        if (Dump.Y) Dump.println("AxeJNI:getMimeApplication_dataandtype uri="+Puri+",mimetype="+Pmimetype);
        try
        {
            rc=AxeActivity.getMimeApplication_dataandtype(Puri,Pmimetype);
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:getMimeApplication_dataandtype");
        }
        return rc;
    }
    public static int openWith(String Purl,String Pmimetype)
    {
        int rc=4;
    //***********************  
    	if (Dump.Y) Dump.println("AxeJNI openWith url="+Purl+",mime="+Pmimetype);
        try
        {
            rc=AxeActivity.openWith(Purl,Pmimetype);
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:openWidth:"+Purl+","+Pmimetype);
        }
        return rc;
    }
    //***********************************************************
    //*request xe's command
    //***********************************************************
    public static void xecmd(String Pcmd,String Popd)
    {
      try                                                          //~vay0I~
      {                                                            //~vay0I~
        if (Dump.Y) Dump.println("AxeJNI.xecmd cmd="+Pcmd+",opd="+Popd);//~vc2JI~
        jniCmd(Pcmd,Popd);
      }                                                            //~vay0I~
      catch(Exception e)                                           //~vay0I~
      {                                                            //~vay0I~
        Dump.println(e,"JNI:xecmd");                               //~vay0I~
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
      	try                                                        //~vab1I~
      	{                                                          //~vab1I~
        	rc=Ucs.wcwidth(Pucs);                                  //~vab1R~
      	}                                                          //~vab1I~
      	catch(Exception e)                                         //~vab1I~
      	{                                                          //~vab1I~
            Dump.println(e,"JNI:wcwidth ucs=:"+Integer.toHexString(Pucs));//~vab1I~
      	}                                                          //~vab1I~
        return rc;                                                 //~vab1I~
    }
    public static void getwcwidthtbl(int Pucsctr,byte[] Pout)
    {
      try                                                          //~vab1I~
      {                                                            //~vab1I~
		Ucs.getwcwidthtbl(Pucsctr,Pout);
      }                                                            //~vab1I~
      catch(Exception e)                                           //~vab1I~
      {                                                            //~vab1I~
            Dump.println(e,"JNI:getwcwidthtbl:ucsctr="+Pucsctr);   //~vab1I~
      }                                                            //~vab1I~
    }
    //***********************************************************
    //*send file
    //***********************************************************
    public static int androsend(int Popt,String Pfnm1,String Pfnm2)
    {
        int rc=4;
    //***********************
    	if (Dump.Y) Dump.println("AxeJNI androsend fnm1="+Pfnm1+",fnm2="+Pfnm2);
        try
        {
            rc=AxeActivity.androSend(Popt,Pfnm1,Pfnm2);
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:androsend:"+Pfnm1+","+Pfnm2);
        }
        return rc;
    }
    //***********************************************************
    //*send file by drag
    //***********************************************************
    public static void dragsend(String Plist)
    {
    //***********************
    	if (Dump.Y) Dump.println("AxeJNI dragsend list="+Plist);
        try
        {
            AxeActivity.dragSend(Plist);
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:dragsend");
        }
    }
    //***********************************************************
    //*dialog replyed copy rep0lace OK
    //***********************************************************
    public static void dragdropRepCopy(String Pcmd)
    {
    //***********************
    	if (Dump.Y) Dump.println("AxeJNI dragdropRepCopy cmd="+Pcmd);
		jniDndRepCopy(Pcmd);
    }
    //***********************************************************
    //*upostmsg-->gtk_event_put ;callback user_msg
    //***********************************************************
    public static void event_put(int Pmsgid,long Pparm)
    {
    //***********************
    	if (Dump.Y) Dump.println("AxeJNI event_put msg(upostmsg)="+Pmsgid);
        try
        {
        	Long parm=new Long(Pparm);
	       AxeTimer t=new AxeTimer(AxeG.axeJNI/*callback*/,1/*milisec delay*/,false/*repeat*/,Pmsgid,parm);
	        t.start();
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:event_put(upostmsg):"+Pmsgid);
        }
    }
//********************************************************
    @Override
    public void onTimerExpired(AxeTimer Ptimer,int Pcallctr,Object Pparm)
    {
        try
        {
        	int msgid=Ptimer.userTimerId;
            long parm=((Long)Pparm).longValue();
    		if (Dump.Y) Dump.println("AxeJNI ontimerexpired(upostmsg)  msg="+msgid+",parm="+Long.toHexString(parm));
			jniUserMsg(msgid,parm);
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:onTimerExpired:");
        }
    }
    //******************************************
    //*vfatlist
    //******************************************
    public static String getVfatList()
    {
    	String rc=null;
    //***********************
    	if (Dump.Y) Dump.println("AxeJNI getVfatList");
        try
        {
            rc=AxeProp.getVfatList();
        }
        catch(Exception e)
        {
            Dump.println(e,"JNI:getVfatList");
        }
        return rc;
    }
    //******************************************                   //~vac6R~
    //*smb(c2j)                                                    //~vac6R~
    //******************************************                   //~vac6R~
    public static int smb_ufstat(int Popt,String Pfpath)           //~vac6R~
    {                                                              //~vac6R~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6R~
      	{                                                          //~vac6R~
			rc=AxeSMBReq.stat(Popt,Pfpath);                        //~vac6R~
      	}                                                          //~vac6R~
      	catch(Exception e)                                         //~vac6R~
      	{                                                          //~vac6R~
            Dump.println(e,"JNI:smb_ufstat:fpath="+Pfpath);        //~vac6R~
      	}                                                          //~vac6R~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6R~
    //******************************************                   //~vac6R~
    public static int smb_udirlist(int Popt,String Pfpath,int Pattr)//~vac6R~
    {                                                              //~vac6R~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6R~
      	{                                                          //~vac6R~
			rc=AxeSMBReq.dirlist(Popt,Pfpath,Pattr);               //~vac6R~
      	}                                                          //~vac6R~
      	catch(Exception e)                                         //~vac6R~
      	{                                                          //~vac6R~
            Dump.println(e,"JNI:smb_ufstat:fpath="+Pfpath);        //~vac6R~
      	}                                                          //~vac6R~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6R~
    //******************************************                   //~vac6I~
    public static int smb_nodetest(int Popt,String Pfpath)         //~vac6I~
    {                                                              //~vac6I~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6I~
      	{                                                          //~vac6I~
			rc=AxeSMBReq.connect(Popt,Pfpath);                     //~vac6R~
      	}                                                          //~vac6I~
      	catch(Exception e)                                         //~vac6I~
      	{                                                          //~vac6I~
            Dump.println(e,"JNI:smb_nodetest:fpath="+Pfpath);      //~vac6I~
      	}                                                          //~vac6I~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6I~
    //******************************************                   //~vac6I~
    public static int smb_getput(int Popt,String Pcmd)             //~vac6I~
    {                                                              //~vac6I~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6I~
      	{                                                          //~vac6I~
			rc=AxeSMBReq.getput(Popt,Pcmd);                      //~vac6I~
      	}                                                          //~vac6I~
      	catch(Exception e)                                         //~vac6I~
      	{                                                          //~vac6I~
            Dump.println(e,"JNI:smb_getput:cmd="+Pcmd);            //~vac6I~
      	}                                                          //~vac6I~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6I~
    //******************************************                   //~vac6I~
    public static int smb_delete(int Popt,String Ppath)            //~vac6I~
    {                                                              //~vac6I~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6I~
      	{                                                          //~vac6I~
			rc=AxeSMBReq.delete(Popt,Ppath);                       //~vac6I~
      	}                                                          //~vac6I~
      	catch(Exception e)                                         //~vac6I~
      	{                                                          //~vac6I~
            Dump.println(e,"JNI:smb_delete:path="+Ppath);          //~vac6I~
      	}                                                          //~vac6I~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6I~
    //******************************************                   //~vac6I~
    public static int smb_rename(int Popt,String Pold,String Pnew) //~vac6I~
    {                                                              //~vac6I~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6I~
      	{                                                          //~vac6I~
			rc=AxeSMBReq.rename(Popt,Pold,Pnew);                   //~vac6I~
      	}                                                          //~vac6I~
      	catch(Exception e)                                         //~vac6I~
      	{                                                          //~vac6I~
            Dump.println(e,"JNI:smb_rename:path="+Pold+"->"+Pnew); //~vac6I~
      	}                                                          //~vac6I~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6I~
    //******************************************                   //~vac6I~
    public static int smb_multidel(int Popt,String Pcmd)           //~vac6I~
    {                                                              //~vac6I~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6I~
      	{                                                          //~vac6I~
			rc=AxeSMBReq.multidel(Popt,Pcmd);                     //~vac6I~
      	}                                                          //~vac6I~
      	catch(Exception e)                                         //~vac6I~
      	{                                                          //~vac6I~
            Dump.println(e,"JNI:smb_multidel:cmd="+Pcmd);          //~vac6I~
      	}                                                          //~vac6I~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6I~
    //******************************************                   //~vac6I~
    public static int smb_multirename(int Popt,String Pcmd)        //~vac6I~
    {                                                              //~vac6I~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6I~
      	{                                                          //~vac6I~
			rc=AxeSMBReq.multirename(Popt,Pcmd);                   //~vac6I~
      	}                                                          //~vac6I~
      	catch(Exception e)                                         //~vac6I~
      	{                                                          //~vac6I~
            Dump.println(e,"JNI:smb_multirename:cmd="+Pcmd);       //~vac6I~
      	}                                                          //~vac6I~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6I~
    //******************************************                   //~vac6I~
    public static int smb_ufindfirst(int Popt,String Ppath,int Pattr)//~vac6R~
    {                                                              //~vac6I~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6I~
      	{                                                          //~vac6I~
			rc=AxeSMBReq.ufindfirst(Popt,Ppath,Pattr);             //~vac6R~
      	}                                                          //~vac6I~
      	catch(Exception e)                                         //~vac6I~
      	{                                                          //~vac6I~
            Dump.println(e,"JNI:smb_ufindfirst:path="+Ppath);      //~vac6I~
      	}                                                          //~vac6I~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6I~
    //******************************************                   //~vac6I~
    public static int smb_mkdir(int Popt,String Ppath)             //~vac6R~
    {                                                              //~vac6I~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6I~
      	{                                                          //~vac6I~
			rc=AxeSMBReq.mkdir(Popt,Ppath);                        //~vac6R~
      	}                                                          //~vac6I~
      	catch(Exception e)                                         //~vac6I~
      	{                                                          //~vac6I~
            Dump.println(e,"JNI:smb_mkdir:path="+Ppath);           //~vac6I~
      	}                                                          //~vac6I~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6I~
    //******************************************                   //~vac6I~
    public static int smb_rmdir(int Popt,String Ppath)             //~vac6I~
    {                                                              //~vac6I~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6I~
      	{                                                          //~vac6I~
			rc=AxeSMBReq.rmdir(Popt,Ppath);                        //~vac6I~
      	}                                                          //~vac6I~
      	catch(Exception e)                                         //~vac6I~
      	{                                                          //~vac6I~
            Dump.println(e,"JNI:smb_rmdir:path="+Ppath);           //~vac6I~
      	}                                                          //~vac6I~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6I~
    //******************************************                   //~vac6I~
    public static int smb_setattr(int Popt,String Ppath,int Pattr) //~vac6I~
    {                                                              //~vac6I~
    	int rc=8;                                                  //~vac6I~
      	try                                                        //~vac6I~
      	{                                                          //~vac6I~
			rc=AxeSMBReq.setattr(Popt,Ppath,Pattr);                //~vac6I~
      	}                                                          //~vac6I~
      	catch(Exception e)                                         //~vac6I~
      	{                                                          //~vac6I~
            Dump.println(e,"JNI:smb_setattr:path="+Ppath+",attr="+Pattr);//~vac6I~
      	}                                                          //~vac6I~
        return rc;                                                 //~vac6I~
    }                                                              //~vac6I~
    //******************************************                   //~vad2I~
    public static boolean isValidCharset(String Pcharset)          //~vad2I~
    {                                                              //~vad2I~
    	boolean brc=false;                                         //~vad2I~
        if (Dump.Y) Dump.println("AxeJNI:isValidCharset");         //~vad2I~
        try                                                        //~vad2I~
        {                                                          //~vad2I~
            int rc=jniIsValidCharset(Pcharset);                    //~vad2I~
            if (rc!=0)                                             //~vad2I~
            	brc=true;                                          //~vad2I~
        }                                                          //~vad2I~
        catch(Exception e)                                         //~vad2I~
        {                                                          //~vad2I~
            Dump.println(e,"JNI:isValidCharset");                  //~vay0R~
        }                                                          //~vad2I~
        return brc;                                                //~vad2I~
    }                                                              //~vad2I~
    //******************************************                   //~vay7I~
    public static void getCurrentFilename()                        //~vay7R~
    {                                                              //~vay7I~
        try                                                        //~vay7I~
        {                                                          //~vay7I~
            jniGetCurrentFilename();                               //~vay7I~
        }                                                          //~vay7I~
        catch(Exception e)                                         //~vay7I~
        {                                                          //~vay7I~
            Dump.println(e,"JNI:getCurrentFilename");              //~vay7I~
        }                                                          //~vay7I~
        if (Dump.Y) Dump.println("AxeJNI:getCurrentFilename:type="+Gxeh.mCurrentFilenameType+",name="+Gxeh.mCurrentFilename);//~vay7R~
    }                                                              //~vay7I~
    //******************************************                   //~vc1mI~
    public static void updateEnvPath()                             //~vc1mI~
    {                                                              //~vc1mI~
        try                                                        //~vc1mI~
        {                                                          //~vc1mI~
            jniSetEnvPath();                                       //~vc1mI~
        }                                                          //~vc1mI~
        catch(Exception e)                                         //~vc1mI~
        {                                                          //~vc1mI~
            Dump.println(e,"JNI:updateEnvPath");                   //~vc1mI~
        }                                                          //~vc1mI~
        if (Dump.Y) Dump.println("AxeJNI:getCurrentFilename:type="+Gxeh.mCurrentFilenameType+",name="+Gxeh.mCurrentFilename);//~vc1mI~
    }                                                              //~vc1mI~
    //******************************************                   //~vc1mI~
    public static void listEnv()                                   //~vc1mI~
    {                                                              //~vc1mI~
        Map<String,String> env=System.getenv();                    //~vc1mR~
        if (Dump.Y) Dump.println("AxeJNI.listEnv size="+env.size());//~vc1mR~
//      System.out.println(env);                                   //~vc1mR~
        for (String key:env.keySet())                              //~vc1mR~
        	if (Dump.Y) Dump.println("AxeJNI.listEnv "+key+"="+env.get(key));//~vc1mR~
    }                                                              //~vc1mI~
    //***********************************************************  //~vbrhI~
    //*by S+C+F1/2/3                                               //~vbrhI~
    //***********************************************************  //~vbrhI~
    public static void notifyRulerMode(int Prulermode)             //~vbrhI~
    {                                                              //~vbrhI~
        try                                                        //~vbrhI~
        {                                                          //~vbrhI~
	    	Gxeh.Mrulermode=Prulermode;                            //~vbrhI~
        }                                                          //~vbrhI~
        catch(Exception e)                                         //~vbrhI~
        {                                                          //~vbrhI~
            Dump.println(e,"JNI:notifyRulerMode");                 //~vbrhI~
        }                                                          //~vbrhI~
    }                                                              //~vbrhI~
//*********************************************                    //~vc2JI~
	private static String getURIPath(String Puri)                  //~vc2JI~
    {                                                              //~vc2JI~
    	String rc=null;                                            //~vc2JI~
        try                                                        //~vc2JI~
        {                                                          //~vc2JI~
	    	Uri uri=Uri.parse(Puri);                              //~vc2JI~
            String scheme=uri.getScheme();                         //~vc2JI~
		    if (Dump.Y) Dump.println("AxeJNI.getURIPath scheme="+scheme);//~vc2JI~
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
            Dump.println(e,"JNI:notifyRulerMode");                 //~vc2JI~
        }                                                          //~vc2JR~
        if (rc==null)                                              //~vc2JI~
            Utils.showToastLong(Utils.getStr(R.string.Err_getPathFromUri,Puri));//~vc2JI~
        else                                                       //~vc2JI~
        if (rc.startsWith(Gxeh.sdRootPath))                        //~vc2JI~
            rc=rc.replaceFirst(Gxeh.sdRootPath,Gxeh.sdRoot);       //~vc2JI~
        if (Dump.Y) Dump.println("AxeJNI.getURIPath rc="+rc+",Puri="+Puri);//~vc2JI~
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
        if (Dump.Y) Dump.println("AxeJNI.getURIPathContent getAutority="+Puri.getAuthority());//~vc2JI~
        File f= Environment.getRootDirectory();//TODO test         //~vc2JR~
        try                                                        //~vc2JI~
        {                                                          //~vc2JI~
        if (Dump.Y) Dump.println("AxeJNI.getURIPathContent root: exists="+f.exists()+",getName="+f.getName()+",getPath="+f.getPath()+",getCanonicalPath="+f.getCanonicalPath()+",getAbsolutePath="+f.getAbsolutePath());//~vc2JI~
        }                                                          //~vc2JI~
        catch(IOException e)                                       //~vc2JI~
        {                                                          //~vc2JI~
            Dump.println(e,"JNI:notifyRulerMode");                 //~vc2JI~
        }                                                          //~vc2JI~
        rc=Puri.getPath();                                         //~vc2JI~
        f= new File("/root");                                      //~vc2JR~
        try                                                        //~vc2JI~
        {                                                          //~vc2JI~
            if (Dump.Y)
                Dump.println("AxeJNI.getURIPathContent root: exists=" + f.exists() + ",getName=" + f.getName() + ",getPath=" + f.getPath() + ",getCanonicalPath=" + f.getCanonicalPath() + ",getAbsolutePath=" + f.getAbsolutePath());//~vc2JI~
        }
            catch(IOException e)                                       //~vc2JI~
        {                                                          //~vc2JI~
            Dump.println(e,"JNI:notifyRulerMode");                 //~vc2JI~
        }                                                          //~vc2JI~
        f= new File(rc);                                           //~vc2JI~
        try                                                        //~vc2JI~
        {                                                          //~vc2JI~
            if (Dump.Y)                                            //~vc2JI~
                Dump.println("AxeJNI.getURIPathContent root: exists=" + f.exists() + ",getName=" + f.getName() + ",getPath=" + f.getPath() + ",getCanonicalPath=" + f.getCanonicalPath() + ",getAbsolutePath=" + f.getAbsolutePath());//~vc2JI~
        }                                                          //~vc2JI~
            catch(IOException e)                                   //~vc2JI~
        {                                                          //~vc2JI~
            Dump.println(e,"JNI:notifyRulerMode");                 //~vc2JI~
        }                                                          //~vc2JI~
        if (rc!=null && rc.startsWith("/root/"))                    //~vc2JI~
            rc=rc.replaceFirst("/root/","/");                      //~vc2JI~
		if (Dump.Y) Dump.println("AxeJNI.getURIPathContent rc="+rc);//~vc2JI~
        return rc;                                                 //~vc2JI~
    }                                                              //~vc2JI~
//*********************************************                    //~vc2JI~
	public static void uriReceived(String Paction,String Puri)     //~vc2JI~
    {                                                              //~vc2JI~
        if (Dump.Y) Dump.println("AxeJNI.uriReceived action="+Paction+",uri="+Puri);//~vc2JI~
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
        if (Dump.Y) Dump.println("uriReceived returned");          //~vc2JI~
    }                                                              //~vc2JI~
}//class
