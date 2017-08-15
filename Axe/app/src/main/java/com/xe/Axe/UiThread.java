//*CID://+DATER~: update#= 122;                                    //+4725R~
//**********************************************************************//~1107I~//~1827I~
//**********************************************************************//~vay2I~
//*main view                                                       //~1107I~//~1827I~
//**********************************************************************//~1107I~//~1827I~
package com.xe.Axe;                                                //~1827I~
                                                                   //~1827I~
                                                                   //~1827I~
import java.util.concurrent.CountDownLatch;                        //~1827I~
                                                                   //~1827I~
//**********************************************************************//~1107I~//~1827I~
public class UiThread                                         //~1214R~//~1827R~
{                                                                  //~0914I~//~1827I~
	                                                               //~1827I~
                                                                   //~1211I~//~1827I~
	private UiThreadI callback=null;                           //~1214R~//~1827R~
	private Object parm=null;                                      //~1214R~//~1827I~
                                                                   //~1827I~
                                                                   //~1827I~
//**********************************                               //~1211I~//~1827I~
//*                                                                //~1211R~//~1827I~
//**********************************                               //~1211I~//~1827I~
	public UiThread(UiThreadI Pcallback,Object Pparm)    //~1214R~ //~1827R~
    {                                                              //~1214I~//~1827I~
    	callback=Pcallback;                                        //~1214I~//~1827I~
        parm=Pparm;                                                //~1214R~//~1827I~
    }                                                              //~1214I~//~1827I~
//===============================================================================//~1310I~//~1827I~
//=on UI Thread;with wait parameter                                //~1310I~//~1827I~
//===============================================================================//~1310I~//~1827I~
    public static void runOnUiThread(boolean Pwait,UiThreadI Pcallback,Object Pparm)//~1310I~//~1827R~
    {                                                              //~1310I~//~1827I~
    	if (Pwait)                                                 //~1310I~//~1827I~
			runOnUiThreadSync(Pcallback,Pparm);                    //~1310I~//~1827R~
        else                                                       //~1310I~//~1827I~
			runOnUiThreadNoSync(Pcallback,Pparm);                  //~1310I~//~1827R~
    }                                                              //~1310I~//~1827I~
//===============================================================================//~v@@@I~//~1212I~//~1214M~//~1827I~
//=no wait UI Thread end                                           //~1220I~//~1827I~
//===============================================================================//~1220I~//~1827I~
    private static void runOnUiThreadNoSync(UiThreadI Pcallback,Object Pparm)//~1214R~//~1221R~//~1309R~//~1827R~
    {                                                              //~1214I~//~1827I~
    	UiThread aut=new UiThread(Pcallback,Pparm);//~1214R~       //~1827R~
    	if (AxeG.isMainThread())                                     //~1214M~//~1827I~
        {                                                          //~1214M~//~1827I~
        	try                                                    //~1512I~//~1827I~
            {                                                      //~1512I~//~1827I~
        		Pcallback.runOnUiThread(aut.parm);                 //~1512R~//~1827R~
            }                                                      //~1512I~//~1827I~
            catch (Exception e)                                    //~1512I~//~1827I~
            {                                                      //~1512I~//~1827I~
                Dump.println(e,"runOnUiThreadNoSync(Mainthread) Exception");//~1512I~//~1827R~
            }                                                      //~1512I~//~1827I~
            return;                                                //~1214M~//~1827I~
        }                                                          //~1214M~//~1827I~
    	aut.runOnUiThreadNoSync2(aut);                  //~1214I~  //~1827R~
    }                                                              //~1214I~//~1827I~
//===============================================================================//~v@@@I~//~1212I~//~1827I~
    private void runOnUiThreadNoSync2(UiThread Paut)//~1212I~//~1214R~//~1221R~//~1827R~
    {                                                              //~1212I~//~1827I~
        final UiThread aut=Paut;                                    //~1214I~//~1827R~
        AxeG.activity.runOnUiThread(              //execute on mainthread after posted//~1214I~//~1827R~
            new Runnable()                                         //~1214I~//~1827I~
            {                                                      //~1214I~//~1827I~
                @Override                                          //~1214I~//~1827I~
                public void run()                                  //~1214I~//~1827I~
                {                                                  //~1214I~//~1827I~
			        if (Dump.Y) Dump.println("RunOnUiThreadNoSync2 start");           //~1214I~//~1308R~//~1506R~//~1827R~
                    if (aut.callback!=null)                        //~1214R~//~1827I~
                    {                                              //~1214I~//~1827I~
			        	if (Dump.Y) Dump.println("RunOnUiThreadNoSync2 parm="+(aut.parm==null?"null":aut.parm.toString()));//~1506R~//~1827R~
        				try                                        //~1311I~//~1827I~
        				{                                          //~1311I~//~1827I~
	                    	aut.callback.runOnUiThread(aut.parm);      //~1214R~//~1311R~//~1827R~
                        }                                          //~1311I~//~1827I~
                        catch (Exception e)             //~1311I~  //~1827I~
                        {                                          //~1311I~//~1827I~
                            Dump.println(e,"runOnUiThreadNoSync2 Exception");//~1311I~//~1827R~
                        }                                          //~1311I~//~1827I~
                    }                                              //~1214I~//~1827I~
			        if (Dump.Y) Dump.println("RunOnUiThreadNoSync2 end");//~1506R~//~1827R~
                }                                                  //~1214I~//~1827I~
            }                                                      //~1214I~//~1827I~
                                  );                               //~1214I~//~1827I~
    }                                                              //~1212I~//~1827I~
//===============================================================================//~1214I~//~1827I~
//= wait UI Thread end                                             //~1220I~//~1827I~
//===============================================================================//~1220I~//~1827I~
    private static void runOnUiThreadSync(UiThreadI Pcallback,Object Pparm)//~1214I~//~1309R~//~1827R~
    {                                                              //~1214I~//~1827I~
    	UiThread aut=new UiThread(Pcallback,Pparm);//~1214I~       //~1827R~
		if (Dump.Y) Dump.println("RunOnUiThreadSync parm="+(Pparm==null?"null":Pparm.toString()));//~1506R~//~1827R~
    	if (AxeG.isMainThread())                                     //~1214I~//~1827R~
        {                                                          //~1214I~//~1827I~
        	try                                                    //~1512I~//~1827I~
            {                                                      //~1512I~//~1827I~
	        	Pcallback.runOnUiThread(aut.parm);                 //~1512R~//~1827R~
            }                                                      //~1512I~//~1827I~
            catch (Exception e)                                    //~1512I~//~1827I~
            {                                                      //~1512I~//~1827I~
                Dump.println(e,"runOnUiThreadSync(Mainthread) Exception");//~1512I~//~1827R~
            }                                                      //~1512I~//~1827I~
            return;                                                //~1214I~//~1827I~
        }                                                          //~1214I~//~1827I~
    	aut.runOnUiThreadSync2();                         //~1214I~//~1827R~
    }                                                              //~1214I~//~1827I~
//===============================================================================//~1214I~//~1827I~
    private void runOnUiThreadSync2()            //~1214I~         //~1827R~
    {                                                              //~1214I~//~1827I~
        if (Dump.Y) Dump.println("AjagoUiThread:runOnUiThreadSync2");              //~1214I~//~1506R~//~1827R~
//create handler is avail on Main thread only                      //~1214R~//~1827R~
//  	Handler handler=new Handler();                             //~1214I~//~1827I~
		CountDownLatch latch=new CountDownLatch(1/*posted by counddown once*/);//~1827I~
        runOnUiThreadSub sub=new runOnUiThreadSub();                                    //~1214R~//~1827R~
        sub.init(this,latch);                                      //~1214I~//~1827I~
        try                                                        //~1214I~//~1827I~
        {                                                          //~1214I~//~1827I~
	        AxeG.activity.runOnUiThread(sub);                        //~1513I~//~1827R~
        	latch.await();   //subthread wakeup by dismiss         //~1214I~//~1827I~
	        if (Dump.Y) Dump.println("UiThread posted wait");                     //~1214I~//~1506R~//~1827R~
        }                                                          //~1214I~//~1827I~
        catch (InterruptedException e)                             //~1214I~//~1827I~
        {                                                          //~1214I~//~1827I~
        	Dump.println(e,"Modal Thread Interrupted ");//~1214I~//~1308R~//~1311R~//~1827I~
        }                                                          //~1214I~//~1827I~
    }                                                              //~1214I~//~1827I~
//*******************************                                  //~1214I~//~1827I~
//*******************************                                  //~1214I~//~1827I~
    class runOnUiThreadSub                                  //~1214I~//~1827R~
			implements Runnable                                    //~1214I~//~1827I~
    {                                                              //~1214I~//~1827I~
    	private UiThread aut;                                      //~1827R~
    	CountDownLatch latch;                                      //~1827I~
    	public runOnUiThreadSub()                                  //~1214R~//~1827R~
        {                                                          //~1214I~//~1827I~
        }                                                          //~1214I~//~1827I~
    	public void init(UiThread Paut,CountDownLatch Platch) //~1214I~//~1827R~
        {                                                          //~1214I~//~1827I~
        	aut=Paut;                                              //~1214I~//~1827I~
        	latch=Platch;                                          //~1214I~//~1827I~
        }                                                          //~1214I~//~1827I~
        @Override                                                  //~1214I~//~1827I~
        public void run()                                          //~1214I~//~1827I~
        {                                                          //~1214I~//~1827I~
			if (Dump.Y) Dump.println("before runOnUiTHreadSub");         //~1214I~//~1506R~//~1827I~
        	try                                                    //~1311I~//~1827I~
        	{                                                      //~1311I~//~1827I~
            	aut.callback.runOnUiThread(aut.parm);                  //~1214I~//~1311R~//~1827R~
            }                                                      //~1311I~//~1827I~
        	catch (Exception e)                         //~1311I~  //~1827I~
        	{                                                      //~1311I~//~1827I~
        		Dump.println(e,"runOnUiThreadcSub(Sync) Exception");//~1311I~//~1827R~
        	}                                                      //~1311I~//~1827I~
			if (Dump.Y) Dump.println("after runOnUiThreadSub");          //~1214I~//~1506R~//~1827R~
            latch.countDown();  //count exausted,post latch.await()//~1214I~//~1827I~
        }                                                          //~1214I~//~1827I~
    }                                                              //~1214I~//~1827I~
}//class UiThread                                             //~1214R~//~1827R~
