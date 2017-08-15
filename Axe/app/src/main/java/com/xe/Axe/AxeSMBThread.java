//*CID://+vac6R~:                                   update#=   2;  //+vac6I~
//**************************************************************** //+vac6I~
//vac6:120217 (Axe)samba share support using jcifs 3.17            //+vac6I~
//**************************************************************** //+vac6I~
package com.xe.Axe;
//**********************************************************************
//*SMB client thread
//**********************************************************************
class AxeSMBThread extends Thread
{
//**************************
    AxeSMBThread()
    {
        start();
    }
    public void run ()
    {
        if (Dump.Y) Dump.println("AxeSMBThread started");
        while(true)
        {
            try
            {
                int opid=doRequest();
                if (opid==AxeSMBReq.SMBCMD_STOP)
                {
                    if (Dump.Y) Dump.println("AxeSMBThread return by stopped after recycleBitmap");
                    return;
                }
            }
            catch (Exception e)
            {
                Dump.println(e,"AxeSMBThraed");
            }
        }
    }
    protected int doRequest()
    {
        AxeSMBReq req;
        while(true)
        {
            req=AxeSMBReq.deqRequest();
            if (req==null)
                break;
            if (Dump.Y) Dump.println("AxeSMBThread doRequest id="+req.smbfunc);
            req.doRequest();
        	doResponse(req);
        }
        return req.smbfunc;
    }
    private void doResponse(AxeSMBReq Preq)
    {
    	RespCallback cb=new RespCallback(Preq);
        UiThread.runOnUiThread(false/*nowait*/,cb,null);//do callback on Main Thread
        return;
    }
    //****************************************************
    //*show toast on uithread; requested by sub thread
    //****************************************************
    public class RespCallback implements UiThreadI
    {
        AxeSMBReq req;
    	public RespCallback(AxeSMBReq Preq)
        {
        	req=Preq;
        }
        @Override
		public void runOnUiThread(Object Pparm/*null*/)
        {
			req.doResponse();
        }
    }
}//class
