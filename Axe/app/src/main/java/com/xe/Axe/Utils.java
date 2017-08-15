//*CID://+vai3R~: update#= 123;                                    //+vai3R~
//**********************************************************************
//vai3:130525 (Axe)hide internal option when release version       //+vai3I~
//vagH:130214 (Axe)System.exit()-->Process.KillProcess             //~vagHI~
//vaa8:111111 (Axe)unzip by subthread                              //~1B11I~
//**********************************************************************
package com.xe.Axe;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.widget.Toast;



//**********************************************************************
public class Utils
{
	static boolean finished=false;
//**********************************
	public static String getResourceString(int Pid)
    {
		return AxeG.resource.getString(Pid);
	}
//*****************

	public static void exit(int Pexitcode)
    {
    	if (Dump.Y) Dump.println("AjagoUtils exit() finshed status="+finished+",code="+Pexitcode);
		finish();
    }
	public static void exit(int Pexitcode,boolean Pkill)
    {
    	if (Dump.Y) Dump.println("AjagoUtils kill exit() finshed status="+finished+",code="+Pexitcode);
        if (Pkill)
        {
            Dump.close();
//        	System.exit(Pexitcode);                                //~vagHR~
            android.os.Process.killProcess(android.os.Process.myPid());                  //~@@@@I~//~vagHI~
        }
		exit(Pexitcode);
    }
	public static void finish()
    {
    	if (Dump.Y) Dump.println("AjagoUtils finish requested "+finished);
    	if (finished)
        	return ;
		AxeG.main.finish();
    	if (Dump.Y) Dump.println("AjagoUtils context finish request");
        sleep(1000);//wait subtread termination  1.0sec
    	if (Dump.Y) Dump.println("AjagoUtils context finish request after sleep 1200");
        Dump.close();
        finished=true;
    }
	public static void sleep(long Pmilisec)
    {
        try
        {
        	Thread.sleep(Pmilisec);//wait subtread termination  1.2sec
        }
        catch(InterruptedException e)
		{
        	Dump.println(e,"sleep interrupted Exception");
		}
    }
//**********************************
//*elapsed time calc
//**********************************
	public static final int TSID_TITLE_TOUCH=0;
	private static final int TSID_MAX        =1;
	private static long[] Stimestamp=new long[TSID_MAX];
	public static long setTimeStamp(int Pid)
    {
        if (Pid>=TSID_MAX)
            return 0;
        long t=System.currentTimeMillis();
        Stimestamp[Pid]=t;
    	if (Dump.Y) Dump.println("AjagoUtils setTimeStamp id="+Pid+",ts="+Long.toHexString(t));
        return t;
    }
	public static int getElapsedTimeMillis(int Pid)
    {
        if (Pid>=TSID_MAX)
            return 0;
        if (Stimestamp[Pid]==0)
            return 0;
        long t=System.currentTimeMillis();
    	if (Dump.Y) Dump.println("AjagoUtils getElapsed now id="+Pid+",ts="+Long.toHexString(t));
        int  elapsed=(int)(t-Stimestamp[Pid]);
    	if (Dump.Y) Dump.println("AjagoUtils getElapsetTimeMillis id="+Pid+",ts="+Integer.toHexString(elapsed));
//      Stimestamp[Pid]=0;	//if cleared next time fail to calc    //~vaa8R~
        return elapsed;
    }
//**********************************
//*edit date/time
//**********************************
	public static final int TS_DATE_TIME=1;
	public static final int TS_MILI_TIME=2;
	private static final SimpleDateFormat fmtdt=new SimpleDateFormat("yyyyMMdd-HHmmss");
	private static final SimpleDateFormat fmtms=new SimpleDateFormat("HHmmss.SSS");
	public static String getTimeStamp(int Popt)
    {
        SimpleDateFormat f;
    //**********************:
    	switch(Popt)
        {
        case TS_DATE_TIME:
        	f=fmtdt;
            break;
        case TS_MILI_TIME:
        	f=fmtms;
            break;
        default:
        	return null;
        }
        return f.format(new Date());
    }
//**********************************
//* Digit Thread ID
//**********************************
	public static String getThreadId()
    {
    //**********************:
    	int tid=(int)Thread.currentThread().getId();
        if (tid<10)
        	return "0"+tid;
        return Integer.toString(tid);
    }
//**********************************
	public static String getThreadTimeStamp()
    {
    //**********************:
    	String tidts=getThreadId()+":"+getTimeStamp(TS_MILI_TIME);
        return tidts;
    }
//**********************************
//* Toast
//**********************************
    public static void showToast(int Presid)
    {
		showToastMsg(Toast.LENGTH_SHORT,Presid,"");
    }
//*****
    public static void showToastLong(int Presid)
    {
		showToastMsg(Toast.LENGTH_LONG,Presid,"");
    }
//*****
    public static void showToast(int Presid,String Ptext)
    {
		showToastMsg(Toast.LENGTH_SHORT,Presid,Ptext);
    }
//*****
    public static void showToastLong(int Presid,String Ptext)
    {
		showToastMsg(Toast.LENGTH_LONG,Presid,Ptext);
    }
//*****
    private static void showToastMsg(int Popt,int Presid,String Ptext)
    {
        String msg=AxeG.resource.getString(Presid)+Ptext;
    	showToastMsg(Popt,msg);
    }
//*****
    public static void showToast(String Ptext)
    {
		showToastMsg(Toast.LENGTH_SHORT,Ptext);
    }
//*****
    public static void showToastLong(String Ptext)
    {
		showToastMsg(Toast.LENGTH_LONG,Ptext);
    }
//*****
    private static void showToastMsg(int Popt,String Ptext)
    {
        if (!AxeG.isMainThread())                                       //~vaa8I~
        {                                                          //~vaa8I~
        	HandleCB cb=new Utils().new HandleCB(Popt,Ptext);                  //~vaa8I~
			UiThread.runOnUiThread(false/*nowait*/,cb/*callback*/,null);//execute on UI thread//~vaa8I~
        	return;                                                //~vaa8I~
        }                                                          //~vaa8I~
    	if (Dump.Y) Dump.println("showToast opt="+Popt+",msg=="+Ptext);
		Toast.makeText(AxeG.context,Ptext,Popt).show();
    }
    //****************************************************         //~vaa8I~
    //*show toast on uithread; requested by sub thread             //~vaa8I~
    //****************************************************         //~vaa8I~
    public class HandleCB implements UiThreadI                     //~vaa8I~
    {                                                              //~vaa8I~
    	int opt;                                                   //~vaa8I~
        String text;                                               //~vaa8I~
    	public HandleCB(int Popt,String Ptext)                     //~vaa8I~
        {                                                          //~vaa8I~
        	opt=Popt;                                              //~vaa8I~
            text=Ptext;                                            //~vaa8I~
        }                                                          //~vaa8I~
        @Override                                                  //~vaa8I~
		public void runOnUiThread(Object Pparm/*null*/)            //~vaa8I~
        {                                                          //~vaa8I~
			showToastMsg(opt,text);                                //~vaa8I~
        }                                                          //~vaa8I~
    }                                                              //~vaa8I~
//**********************************************************
//* string to number ***************************************
//**********************************************************
	public static int strToNum(String Pstr,int Perr)
	{
    	if (Pstr==null)
        	return Perr;
		try
		{
			return Integer.parseInt(Pstr);
		}
		catch (NumberFormatException e)
		{
		}
        return Perr;
	}
//**********
	public static int hexstrToNum(String Pstr,int Perr)
	{
		try
		{
			return Integer.parseInt(Pstr,16);
		}
		catch (NumberFormatException e)	//include the case Pstr==null
		{
		}
        return Perr;
	}
//***********
    public static void beep()
    {
    	ToneGenerator tg=new ToneGenerator(AudioManager.STREAM_ALARM,ToneGenerator.MAX_VOLUME);
    	tg.startTone(ToneGenerator.TONE_PROP_BEEP);   //Beep:35ms
    }
//***********
    public static void copyArray(int[][] Pfrom,int[][] Pto)
    {
    	int sz1=Pfrom.length;
    	int sz2=Pfrom[0].length;
        for (int ii=0;ii<sz1;ii++)
        	System.arraycopy(Pfrom[ii],0,Pto[ii],0,sz2);
        return;
    }
//***********************************************************************//+vai3I~
    public static boolean isDebuggable(Context ctx)                //+vai3I~
    {                                                              //+vai3I~
        PackageManager manager = ctx.getPackageManager();          //+vai3I~
        ApplicationInfo appInfo = null;                            //+vai3I~
        try                                                        //+vai3I~
        {                                                          //+vai3I~
            appInfo = manager.getApplicationInfo(ctx.getPackageName(), 0);//+vai3I~
        }                                                          //+vai3I~
        catch (NameNotFoundException e)                            //+vai3I~
        {                                                          //+vai3I~
            return false;                                          //+vai3I~
        }                                                          //+vai3I~
        if ((appInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) == ApplicationInfo.FLAG_DEBUGGABLE)//+vai3I~
            return true;                                           //+vai3I~
        return false;                                              //+vai3I~
    }                                                              //+vai3I~
}//class AjagoUtils
