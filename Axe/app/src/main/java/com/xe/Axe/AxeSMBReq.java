//*CID://+vac6R~:                                   update#=  68;  //~vac6I~
//**************************************************************** //~vac6I~
//vac6:120217 (Axe)samba share support using jcifs 3.17            //~vac6I~
//**************************************************************** //~vac6I~
package com.xe.Axe;
import java.io.File;
import java.util.LinkedList;

import jcifs.smb.SmbFile;

//*CID://+dateR~: update#= 340;
//**********************************************************************
//*smb request unit
//**********************************************************************
public class AxeSMBReq
{
    private static LinkedList<AxeSMBReq> requestList=new LinkedList<AxeSMBReq>();
    private static AxeSMBThread smbThread=new AxeSMBThread();
    public static AxeSMBCifs smbCifs=new AxeSMBCifs();

    public static final int SMBCMD_DIR=        0x01;
    public static final int SMBCMD_STAT=       0x02;
    public static final int SMBCMD_GET=        0x03;
    public static final int SMBCMD_PUT=        0x04;
    public static final int SMBCMD_DELETE=     0x05;
    public static final int SMBCMD_RENAME=     0x06;
    public static final int SMBCMD_CHMOD=      0x07;
    public static final int SMBCMD_CD=         0x08;
    public static final int SMBCMD_LCD=        0x09;
    public static final int SMBCMD_PUT2=       0x0a; //put a b(2 opd rename to)
    public static final int SMBCMD_MKDIR=      0x0b;
    public static final int SMBCMD_RMDIR=      0x0c;
    public static final int SMBCMD_PWD=        0x0d;
    public static final int SMBCMD_STATDIR=    0x0e;    //stat for the file in the dir all at once
    public static final int SMBCMD_SETMOD=     0x0f;    //
    public static final int SMBCMD_LIST=       0x10;    //
    public static final int SMBCMD_R2RCOPY=    0x11;    //remote to remote
    public static final int SMBCMD_CONNECT=    0x12;    //test connection
    public static final int SMBCMD_GETPUT=     0x13;    //         //~vac6R~
    public static final int SMBCMD_FINDFIRST=  0x14;    //         //~vac6I~
    public static final int SMBCMD_MULTIDEL=   0x15;    //         //~vac6I~
    public static final int SMBCMD_MULTIREN=   0x16;    //         //~vac6I~
    public static final int SMBCMD_STOP=       0xfe;    //

    public static final int SMBOPT_ASYNC=      0x01;    //asynchronous process
    public static final int SMBOPT_AFTERSTAT=  0x02;    //do fstat after operation such as mkdir//~vac6I~
    public static final int SMBREQRC_ASYNC =-2;
    public static final int SMBREQRC_BUSY  =AxeSMBCifs.BUSY;       //~vac6I~
                                                                   //~vac6I~
    private static final int MSGID_CONNECTING=R.string.Info_Smb_Connecting;//~vac6I~
    private static final int MSGID_CONNECTED=R.string.Info_Smb_Connected;//~vac6I~
    private static final int MSGID_BUSY=R.string.Info_Smb_Busy;    //~vac6I~
    private static boolean connectPending;                         //~vac6I~

    int smbfunc;
    int option;
    String parmStr1,parmStr2;
    int parmInt1;
    int retCode;                                                   //~vac6R~
    int doserr;
    int ntStatus;
    long parmLong1;
//*stat
    String name;    //dir list entry name
    int    attr;       //dos attr
    int    time;       //modtime by seconds from 1970              //~vac6R~
    long   size;
//*dirlist
    int fileNo;           //dirlist ctr
    String[] listName;    //dir list entry name
    int[] listAttr;       //dos attr
    int[] listTime;       //modtime  by seconds from 1970          //~vac6R~
    long[] listSize;       //file size
    //*******************************************************
    static class PathInfo
    {
        public String path;
        public String basename;
        public boolean wildcard;
        public PathInfo(String Ppath,String Pbasename,boolean Pwildcard)
        {
        	path=Ppath;
            basename=Pbasename;
            wildcard=Pwildcard;
        }
        //*****************************************                //~vac6I~
        //*if wildcard split to path and wildname                  //~vac6I~
        //*else if dir=true append "/"(if opt=1) or return it (opt=2)//~vac6I~
        //*     if dir=false split to path and basename            //~vac6I~
        //*****************************************                //~vac6I~
        public static PathInfo parse(int Popt,String Ppath)        //~vac6R~
        {
            String dirname,basename=null;
            boolean wildcard=Ppath.contains("*")||Ppath.contains("?");
            int pos=Ppath.lastIndexOf('/');
            if (pos<0)
            {
                if (wildcard)
                {
                    dirname="./";
                    basename=Ppath;
                }
                else
                {
	            	if (Popt==1)	//force dir                    //~vac6R~
    	                dirname=Ppath+"/";
                    else                                           //~vac6I~
                    if (Popt==2)                                   //~vac6I~
    	                dirname=Ppath;                             //~vac6I~
                    else
                    {
                        dirname="./";
                        basename=Ppath;
                    }
                }
            }
            else
            if (pos+1==Ppath.length())    //end with "/"
            {
                dirname=Ppath;
                basename=null;
            }
            else
            {
                if (wildcard)
                {
	                dirname=Ppath.substring(0,pos+1);              //~vac6R~
    	            basename=Ppath.substring(pos+1);
                }
                else
                {
	            	if (Popt==1)	//force dir                    //~vac6R~
    	                dirname=Ppath+"/";
                    else                                           //~vac6I~
	            	if (Popt==2)	//force dir                    //~vac6I~
    	                dirname=Ppath;                             //~vac6I~
                    else
                    {
		                dirname=Ppath.substring(0,pos+1);
    		            basename=Ppath.substring(pos+1);
                    }
                }
            }
            return new PathInfo(dirname,basename,wildcard);
        }//parse
    }
    //*******************************************************      //~vac6I~
    static class StatInfo                                          //~vac6I~
    {                                                              //~vac6I~
    	public String name;    //dir list entry name               //~vac6I~
    	public int    attr;       //dos attr                       //~vac6I~
    	public int    time;       //modtime by seconds from 1970   //~vac6I~
    	public long   size;                                        //~vac6I~
        public StatInfo(String Pname,int Pattr,long Psize,int Ptime)//~vac6I~
        {                                                          //~vac6I~
        	name=Pname;                                            //~vac6I~
            attr=Pattr;                                            //~vac6I~
            size=Psize;                                            //~vac6I~
            time=Ptime;                                            //~vac6I~
        }                                                          //~vac6I~
    }                                                              //~vac6I~
    //*******************************************************      //~vac6I~
    private StatInfo getParentInfo(String Pfnm)                    //~vac6I~
    {                                                              //~vac6I~
    	int pos,pos2;                                              //~vac6I~
    //**************************                                   //~vac6I~
    	pos=Pfnm.indexOf("://");	//server pos                   //~vac6I~
        if (pos<0)                                                 //~vac6I~
        	return null;                                           //~vac6I~
    	pos=Pfnm.indexOf('/',pos+3);//share pos;                   //~vac6R~
        if (pos<0)                                                 //~vac6I~
        	return null;                                           //~vac6I~
    	pos=Pfnm.indexOf('/',pos+1); //root pos                    //~vac6R~
        if (pos<0)                                                 //~vac6I~
        	return null;                                           //~vac6I~
        pos2=Pfnm.lastIndexOf('/');                               //~vac6I~
        if (pos2<pos)                                              //~vac6I~
        	return null;                                           //~vac6I~
        if (pos2==pos)		//under root                           //~vac6I~
	        if (pos2+1==Pfnm.length())    //end with "/",Pfnm root itself//~vac6I~
            	return null;                                       //~vac6I~
        String dirname=Pfnm.substring(0,pos2);                     //~vac6R~
    	funcSTAT(dirname);                                         //~vac6I~
        return new StatInfo("..",attr,size,time);                 //~vac6I~
    }                                                              //~vac6I~
    //*******************************************************      //~vac6I~
    private static boolean isRootDir(String Pfnm)                  //~vac6I~
    {                                                              //~vac6I~
    	int pos;                                                   //~vac6I~
    //**************************                                   //~vac6I~
    	pos=Pfnm.indexOf("://");	//server pos                   //~vac6I~
        if (pos<0)                                                 //~vac6I~
        	return false;                                          //~vac6I~
    	pos=Pfnm.indexOf('/',pos+3);	//share pos;                   //~vac6I~
        if (pos<0)                                                 //~vac6I~
        	return false;                                          //~vac6I~
    	pos=Pfnm.indexOf('/',pos+1);	//root pos                     //~vac6I~
        if (pos<0)                                                 //~vac6I~
        	return false;                                          //~vac6I~
	    return (pos+1==Pfnm.length());    //end with "/",Pfnm root itself//~vac6I~
    }                                                              //~vac6I~
    //*******************************************************
    public AxeSMBReq(int Popt,int Pfunc,String Pstr1,String Pstr2,int Pint1,long Plong1)
    {
        smbfunc=Pfunc;
        option=Popt;
        parmStr1=Pstr1;
        parmStr2=Pstr2;
        parmInt1=Pint1;
        parmLong1=Plong1;
    }
    //*******************************************************
    public static int dirlist(int Popt,String Pfnm,int Pattr)      //~vac6R~
    {
	    AxeSMBReq req=new AxeSMBReq(Popt,SMBCMD_DIR,Pfnm,null,Pattr,0);
        return AxeSMBReq.enqRequest(req);                          //~vac6I~
    }
    //*******************************************************
    public static int list(int Popt,String Pfnm)                   //~vac6R~
    {
	    AxeSMBReq req=new AxeSMBReq(Popt,SMBCMD_LIST,Pfnm,null,0,0);
        return AxeSMBReq.enqRequest(req);                          //~vac6R~
    }
    //*******************************************************
    public static int stat(int Popt,String Pfnm)                   //~vac6R~
    {
	    AxeSMBReq req=new AxeSMBReq(Popt,SMBCMD_STAT,Pfnm,null,0,0);
        return AxeSMBReq.enqRequest(req);                          //~vac6R~
    }
    //*******************************************************      //~vac6I~
    public static int ufindfirst(int Popt,String Pfnm,int Pattr)   //~vac6R~
    {                                                              //~vac6I~
	    AxeSMBReq req=new AxeSMBReq(Popt,SMBCMD_FINDFIRST,Pfnm,null,Pattr,0);//~vac6R~
        return AxeSMBReq.enqRequest(req);                          //~vac6I~
    }                                                              //~vac6I~
    //*******************************************************
    public static int mkdir(int Popt,String Pfnm)                  //~vac6R~
    {
	    AxeSMBReq req=new AxeSMBReq(Popt,SMBCMD_MKDIR,Pfnm,null,0,0);//~vac6R~
        return AxeSMBReq.enqRequest(req);                          //~vac6R~
    }
    //*******************************************************
    public static int rmdir(int Popt,String Pfnm)                  //~vac6R~
    {
	    AxeSMBReq req=new AxeSMBReq(Popt,SMBCMD_RMDIR,Pfnm,null,0,0);
        return AxeSMBReq.enqRequest(req);                          //~vac6R~
    }
    //*******************************************************
    public static int rename(int Popt,String Pfrom,String Pto)     //~vac6R~
    {
	    AxeSMBReq req=new AxeSMBReq(Popt,SMBCMD_RENAME,Pfrom,Pto,0,0);
        return AxeSMBReq.enqRequest(req);                          //~vac6R~
    }
    //*******************************************************
    public static int delete(int Popt,String Pfnm)
    {
	    AxeSMBReq req=new AxeSMBReq(Popt,SMBCMD_DELETE,Pfnm,null,0,0);
        return AxeSMBReq.enqRequest(req);                          //~vac6R~
    }
    //*******************************************************      //~vac6I~
    public static int multidel(int Popt,String Pcmd)               //~vac6I~
    {                                                              //~vac6I~
	    AxeSMBReq req=new AxeSMBReq(Popt,SMBCMD_MULTIDEL,Pcmd,null,0,0);//~vac6I~
        return AxeSMBReq.enqRequest(req);                          //~vac6I~
    }                                                              //~vac6I~
    //*******************************************************      //~vac6I~
    public static int multirename(int Popt,String Pcmd)            //~vac6I~
    {                                                              //~vac6I~
	    AxeSMBReq req=new AxeSMBReq(Popt,SMBCMD_MULTIREN,Pcmd,null,0,0);//~vac6I~
        return AxeSMBReq.enqRequest(req);                          //~vac6I~
    }                                                              //~vac6I~
    //*******************************************************
    public static int get(int Popt,String Pfnm,String Plocal)      //~vac6R~
    {
	    AxeSMBReq req=new AxeSMBReq(Popt,SMBCMD_GET,Pfnm,Plocal,0,0);
        return AxeSMBReq.enqRequest(req);                          //~vac6R~
    }
    //*******************************************************
    public static int put(int Popt,String Pfnm,String Plocal)      //~vac6R~
    {
	    AxeSMBReq req=new AxeSMBReq(Popt,SMBCMD_PUT,Pfnm,Plocal,0,0);
        return AxeSMBReq.enqRequest(req);                          //~vac6R~
    }
    //*******************************************************      //~vac6I~
    public static int getput(int Popt,String Pcmd)                 //~vac6I~
    {                                                              //~vac6I~
	    AxeSMBReq req=new AxeSMBReq(Popt,SMBCMD_GETPUT,Pcmd,null,0,0);//~vac6I~
        return AxeSMBReq.enqRequest(req);                          //~vac6I~
    }                                                              //~vac6I~
    //*******************************************************
    public static int setattr(int Popt,String Pfnm,int Pattr)      //~vac6R~
    {
	    AxeSMBReq req=new AxeSMBReq(Popt,SMBCMD_SETMOD,Pfnm,null,Pattr,0);
        return AxeSMBReq.enqRequest(req);                          //~vac6R~
    }
    //*******************************************************
    public static int r2rcopy(int Popt,String Pfrom,String Pto)    //~vac6R~
    {
	    AxeSMBReq req=new AxeSMBReq(Popt,SMBCMD_R2RCOPY,Pfrom,Pto,0,0);
        return AxeSMBReq.enqRequest(req);                          //~vac6R~
    }
    //*******************************************************
    public static int connect(int Popt,String Pdest)               //~vac6R~
    {
        int rc;                                                    //~vac6I~
        if (connectPending)                                        //~vac6I~
        	return SMBREQRC_BUSY;                                  //~vac6I~
        connectPending=true;                                       //~vac6I~
	    AxeSMBReq req=new AxeSMBReq(Popt,SMBCMD_CONNECT,Pdest,null,0,0);
        rc=AxeSMBReq.enqRequest(req);                              //~vac6R~
        if (rc==SMBREQRC_ASYNC)                                    //~vac6I~
    		Utils.showToastLong(MSGID_CONNECTING,Pdest);           //~vac6M~
        return rc;                                                 //~vac6I~
    }
    //*******************************************************
    //*******************************************************
    //*******************************************************
    public static int enqRequest(AxeSMBReq Preq)                   //~vac6R~
    {
    	//**********************************
    	if ((Preq.option & SMBOPT_ASYNC)==0)
        {
//   	    boolean dumpopt=Dump.Y;       //@@@@                   //+vac6R~
//      	Dump.Y=true;                                           //+vac6R~
        	Preq.doRequest();
        	Preq.doResponse();                                     //~vac6R~
//          Dump.Y=dumpopt;                                        //+vac6R~
            return Preq.retCode;                                   //~vac6R~
        }
    	synchronized(requestList)
        {
            if (requestList.size()!=0)                             //~vac6I~
            {                                                      //~vac6I~
    			Utils.showToast(MSGID_BUSY);                       //~vac6I~
                return SMBREQRC_BUSY;                              //~vac6I~
            }                                                      //~vac6I~
        	requestList.add(Preq);	//enq to last
            requestList.notifyAll();    //pos wait
        }
        if (Dump.Y) Dump.println("AxeSMBReq enq req func="+Preq.smbfunc);
        return SMBREQRC_ASYNC;
    }
    public static AxeSMBReq deqRequest()
    {
        AxeSMBReq req;
        synchronized(requestList)
        {
            while(requestList.size()==0)
            {
                try
                {
                    requestList.wait();
                }
                catch (InterruptedException e)
                {
                    Dump.println(e,"AxeSMBReq requestList wait interrupted");
                    return null;
                }//wait notufy
            }
            req=requestList.getFirst(); //get top
            requestList.remove(req);//deq
        }
        if (Dump.Y) Dump.println("AxeSMBReq deq posted func="+req.smbfunc);
        return req;
    }
    //*******************************************************
    public void doRequest()  //on subthread
    {
    	Gxeh.SMB_errmsg=null;                                      //~vac6I~
    	AxeSMBCifs.retCode=0;                                      //~vac6R~
    	AxeSMBCifs.doserr=0;
    	AxeSMBCifs.status=0;
        retCode=0;                                                 //~vac6I~
        if (Dump.Y) Dump.println("AxeSMBCifs req func="+smbfunc);
        switch(smbfunc)
        {
        case AxeSMBReq.SMBCMD_STAT://         0x02
            funcSTAT();
        	break;
        case AxeSMBReq.SMBCMD_DIR://          0x01                 //~vac6M~
        	funcDIR();                                             //~vac6M~
        	break;                                                 //~vac6M~
        case AxeSMBReq.SMBCMD_FINDFIRST:                           //~vac6I~
        	funcFINDFIRST();                                       //~vac6I~
        	break;                                                 //~vac6I~
        case AxeSMBReq.SMBCMD_GETPUT:                              //~vac6R~
            funcGETPUT();                                          //~vac6I~
        	break;                                                 //~vac6I~
        case AxeSMBReq.SMBCMD_GET://          0x03
            funcGET();
        	break;
        case AxeSMBReq.SMBCMD_PUT://          0x04
            funcPUT();
        	break;
        case AxeSMBReq.SMBCMD_DELETE://       0x05
            funcDELETE();
        	break;
        case AxeSMBReq.SMBCMD_MULTIDEL://       0x05               //~vac6I~
            funcMULTIDEL();                                        //~vac6I~
        	break;                                                 //~vac6I~
        case AxeSMBReq.SMBCMD_MULTIREN://       0x05               //~vac6I~
            funcMULTIREN();                                        //~vac6I~
        	break;                                                 //~vac6I~
        case AxeSMBReq.SMBCMD_RENAME://       0x06
          funcRENAME();
        	break;
        case AxeSMBReq.SMBCMD_CHMOD://        0x07
//            funcCHMOD();
        	break;
        case AxeSMBReq.SMBCMD_CD://           0x08
//            funcCD();
        	break;
        case AxeSMBReq.SMBCMD_LCD://          0x09
//            funcLCD();
        	break;
        case AxeSMBReq.SMBCMD_PUT2://         0x0a //put a b(2 opd rename to)
//            funcPUT2();
        	break;
        case AxeSMBReq.SMBCMD_MKDIR://        0x0b
            funcMKDIR();
        	break;
        case AxeSMBReq.SMBCMD_RMDIR://        0x0c
            funcRMDIR();
        	break;
        case AxeSMBReq.SMBCMD_PWD://          0x0d
//            funcPWD();
        	break;
        case AxeSMBReq.SMBCMD_STATDIR://      0x0e    //stat for the file in the dir all at once
//            funcSTATDIR();
        	break;
        case AxeSMBReq.SMBCMD_SETMOD://       0x0f    //target:window "arsh" attr
            funcSETMOD();
        	break;
        case AxeSMBReq.SMBCMD_LIST://          0x10
            funcLIST();
        	break;
        case AxeSMBReq.SMBCMD_R2RCOPY://       0x11
            funcR2RCOPY();
        	break;
        case AxeSMBReq.SMBCMD_CONNECT://       0x12
            funcCONNECT();
        	break;
        }
        ntStatus=AxeSMBCifs.status;
	    doserr=AxeSMBCifs.doserr;                                  //~vac6R~
        if (retCode==0)  //no rc by AxeAMBReq                      //~vac6I~
	        retCode=doserr;  //rc by AxeSMBCifs                    //~vac6I~
        if (Dump.Y) Dump.println("AxeSMBCifs func="+smbfunc+",rc="+retCode+",doserr="+doserr+",status="+Integer.toHexString(ntStatus));//~vac6R~
    }
    //****************************************************************
    //****************************************************************
    //****************************************************************
    private void funcDIR()                                         //~vac6I~
    {                                                              //~vac6I~
        funcDIR(parmStr1,parmInt1,false/*findfirst*/);             //~vac6I~
	}                                                              //~vac6I~
    //*****************************
    private int funcDIR(String Ppath,int Pattr,Boolean PfindFirst) //~vac6R~
    {
    	SmbFile[] list;
        SmbFile smbfile;                                           //~vac6I~
        String path=Ppath;                                         //~vac6R~
        int attrmask=Pattr;                                        //~vac6R~
        String wildname,dirname;
        StatInfo parentinfo=null,cdinfo;                           //~vac6I~
        int setcd=0,actr;                                               //~vac6I~
    //*********************                                        //~vac6I~
        PathInfo pi=PathInfo.parse(1/*append "/"*/,path);          //~vac6R~
        if (pi.wildcard)
        	wildname=pi.basename;
        else
        	wildname=null;
        dirname=pi.path;
    	funcSTAT(dirname);                                          //~vac6I~
        if (AxeSMBCifs.retCode!=0)                                 //~vac6R~
        	return AxeSMBCifs.retCode;                             //~vac6R~
        if ((attr & AxeSMBCifs.DOSATTR_DIR)==0)                      //~vac6I~
        {                                                          //~vac6I~
        	return (retCode=AxeSMBCifs.ENOTDIR);                   //~vac6R~
        }                                                          //~vac6I~
        cdinfo=new StatInfo(".",attr,size,time);                   //~vac6I~
        if ((attrmask & AxeSMBCifs.DOSATTR_DIR)!=0                 //~vac6I~
        &&  (wildname==null||wildname.equals("*"))                 //~vac6I~
        )                                                          //~vac6I~
        {                                                          //~vac6I~
        	setcd=2;                                               //~vac6R~
            if (!PfindFirst)                                       //~vac6I~
        		parentinfo=getParentInfo(dirname);                 //~vac6R~
        }
        if ((smbfile=AxeSMBCifs.doSmbFile(dirname))==null)         //~vac6R~
        	return AxeSMBCifs.retCode;                             //~vac6I~
        list=AxeSMBCifs.doListFile(smbfile,wildname,attrmask);     //~vac6R~
        if (AxeSMBCifs.retCode!=0)                                 //~vac6R~
        	return AxeSMBCifs.retCode;                             //~vac6I~
        int fileno=list.length;
        fileno+=setcd;                                             //~vac6I~
        if (PfindFirst)                                            //~vac6I~
	        actr=1;			                                       //~vac6I~
        else                                                       //~vac6I~
	        actr=fileno;                                           //~vac6I~
        String[] listname=new String[actr];                        //~vac6R~
        int[] listattr=new int[actr];                              //~vac6R~
        int[] listtime=new int[actr];                              //~vac6R~
        long[] listsize=new long[actr];                            //~vac6R~
        if (setcd!=0)                                              //~vac6I~
        {                                                          //~vac6I~
            listname[0]=".";                                       //~vac6I~
            listattr[0]=cdinfo.attr;                               //~vac6I~
            listtime[0]=cdinfo.time;                               //~vac6I~
            listsize[0]=cdinfo.size;                               //~vac6I~
            if (!PfindFirst)                                       //~vac6I~
            {                                                      //~vac6I~
                listname[1]="..";                                  //~vac6R~
                if (parentinfo==null)                              //~vac6R~
                {                                                  //~vac6R~
                    listattr[1]=listattr[0];                       //~vac6R~
                    listtime[1]=listtime[0];                       //~vac6R~
                    listsize[1]=listsize[0];                       //~vac6R~
                }                                                  //~vac6R~
                else                                               //~vac6R~
                {                                                  //~vac6R~
                    listattr[1]=parentinfo.attr;                   //~vac6R~
                    listtime[1]=parentinfo.time;                   //~vac6R~
                    listsize[1]=parentinfo.size;                   //~vac6R~
                }                                                  //~vac6R~
            }                                                      //~vac6I~
        }                                                          //~vac6I~
        for (int ii=setcd;ii<actr;ii++)                            //~vac6R~
        {
            SmbFile file=list[ii-setcd];                           //~vac6R~
            listname[ii]=file.getName();
            listattr[ii]=AxeSMBCifs.doGetAttributes(file);         //~vac6R~
            listtime[ii]=(int)(file.getLastModified()/1000);	//milisec to sec//~vac6R~
            listsize[ii]=AxeSMBCifs.doGetSize(file);               //~vac6R~
            if (Dump.Y) Dump.println("AxeSMBCifs dirlist name="+listname[ii]+",attr="+Integer.toHexString(listattr[ii])+",time="+Integer.toHexString(listtime[ii])+",size="+Long.toHexString(listsize[ii]));//~vac6R~
        }
        fileNo=fileno;
        listName=listname;
        listAttr=listattr;
        listTime=listtime;
        listSize=listsize;
        return 0;                                                  //~vac6I~
    }//dirlist
    //***********************************************************  //~vac6R~
    //*for file,return fstat result like as file1l(used for no handle required case only)//~vac6R~
    //*set retCoed:ENOTDIR                                         //~vac6I~
    //***********************************************************  //~vac6R~
    private void funcFINDFIRST()                                   //~vac6I~
    {                                                              //~vac6I~
        funcFINDFIRST(parmStr1,parmInt1);                          //~vac6R~
    }                                                              //~vac6I~
    private void funcFINDFIRST(String Pfnm,int Pattr)              //~vac6R~
    {                                                              //~vac6I~
//        SmbFile[] list;                                          //~vac6R~
//        SmbFile file;                                            //~vac6R~
//        PathInfo pi;                                             //~vac6R~
//    //*************************                                  //~vac6R~
//        String path=Pfnm;                                        //~vac6R~
//        String wildname,dirname;                                 //~vac6R~
//        pi=PathInfo.parse(2/*not append "/"*/,path);             //~vac6R~
//        if (pi.wildcard)                                         //~vac6R~
//            wildname=pi.basename;                                //~vac6R~
//        else                                                     //~vac6R~
//        {                                                        //~vac6R~
//            wildname=null;                                       //~vac6R~
//        }                                                        //~vac6R~
//        attr=0;                                                  //~vac6R~
//        funcSTAT(pi.path);                                       //~vac6R~
//        if (AxeSMBCifs.retCode!=0)                               //~vac6R~
//            return;                                              //~vac6R~
//        if ((attr & AxeSMBCifs.DOSATTR_DIR)==0)//not dir         //~vac6R~
//        {                                                        //~vac6R~
//            retCode=AxeSMBCifs.ENOTDIR; //dos err                //~vac6R~
//            return; //attr,time is set                           //~vac6R~
//        }                                                        //~vac6R~
//        if ((Pattr & AxeSMBCifs.DOSATTR_DIR)!=0                  //~vac6R~
//        &&  (wildname==null||wildname.equals("*"))               //~vac6R~
//        )                                                        //~vac6R~
//        {                                                        //~vac6R~
//            fileNo=1;                                            //~vac6R~
//            name=".";                                            //~vac6R~
//            return;                                              //~vac6R~
//        }                                                        //~vac6R~
//        pi.path+="/";                                            //~vac6R~
//        dirname=pi.path;                                         //~vac6R~
//        file=AxeSMBCifs.doSmbFile(dirname);                      //~vac6R~
//        if (AxeSMBCifs.retCode!=0)                               //~vac6R~
//            return;                                              //~vac6R~
//        list=AxeSMBCifs.doListFile(file,wildname,Pattr);         //~vac6R~
//        if (AxeSMBCifs.retCode!=0)                               //~vac6R~
//            return;                                              //~vac6R~
//        int fileno=list.length;                                  //~vac6R~
//        file=list[0];                                            //~vac6R~
//        name=file.getName();                                     //~vac6R~
//        attr=AxeSMBCifs.doGetAttributes(file);                   //~vac6R~
//        if (AxeSMBCifs.retCode!=0)                               //~vac6R~
//            return;                                              //~vac6R~
//        time=(int)(file.getLastModified()/1000);    //milisec to sec//~vac6R~
//        size=AxeSMBCifs.doGetSize(file);                         //~vac6R~
        boolean wildcard=Pfnm.contains("*")||Pfnm.contains("?");   //~vac6I~
        int rc=funcDIR(Pfnm,Pattr,true/*findfirst*/);              //~vac6I~
        if (rc==AxeSMBCifs.ENOTDIR)                                //~vac6R~
            if (!wildcard)
            {//~vac6I~
                retCode=0;
                  return;
            }//~vac6I~
        if (rc!=0)                                                 //~vac6I~
        	return;                                                //~vac6I~
        if (fileNo==0)	                                           //~vac6I~
        {                                                          //~vac6M~
        	retCode=AxeSMBCifs.NOMOREFILE;
        	return;//~vac6I~
        }                                                          //~vac6M~
        name=listName[0];                                          //~vac6I~
        attr=listAttr[0];                                          //~vac6I~
        time=listTime[0];                                          //~vac6I~
        size=listSize[0];                                          //~vac6I~
                                                                   //~vac6I~
        listName=null;      //for GC                               //~vac6I~
        listAttr=null;      //for GC                               //~vac6I~
        listTime=null;                                             //~vac6I~
        listSize=null;                                             //~vac6I~
    }//findfirst                                                   //~vac6I~
    //*****************************
    private void funcSTAT()
    {
        funcSTAT(parmStr1);                                         //~vac6R~
    }//stat
    private void funcSTAT(String Ppath)                            //~vac6I~
    {                                                              //~vac6I~
		SmbFile file;                                              //~vac6I~
        String path=Ppath;                                         //~vac6I~
		if ((file=AxeSMBCifs.doSmbFile(path))==null)               //~vac6R~
        	return;                                                //~vac6I~
        if (isRootDir(path))                                       //~vac6I~
        {                                                          //~vac6I~
        	attr=AxeSMBCifs.DOSATTR_DIR;                           //~vac6I~
            name="/";                                              //~vac6I~
        }                                                          //~vac6I~
        else                                                       //~vac6I~
        {                                                          //~vac6I~
        	attr=AxeSMBCifs.doGetAttributes(file);                 //~vac6R~
        	if (AxeSMBCifs.retCode!=0)                             //~vac6R~
        		return;                                            //~vac6R~
	        name=file.getName();                                   //~vac6R~
        }                                                          //~vac6M~
        time=(int)(file.getLastModified()/1000);                   //~vac6I~
        size=AxeSMBCifs.doGetSize(file);                           //~vac6I~
    }//stat                                                        //~vac6I~
    //*****************************
    private void funcMKDIR()
    {
		SmbFile file;
        String path=parmStr1;
		if ((file=AxeSMBCifs.doSmbFile(path))==null)               //~vac6R~
        	return;
        AxeSMBCifs.doMkDir(file);                                  //~vac6R~
        if (AxeSMBCifs.retCode!=0)                                 //~vac6R~
        	return;
    	if ((option & SMBOPT_AFTERSTAT)!=0)                         //~vac6R~
        {
            int newattr=AxeSMBCifs.doGetAttributes(file);          //~vac6R~
            newattr&=AxeSMBCifs.DOSATTR_ALL;                       //~vac6R~
            this.attr=newattr;                                     //~vac6R~
        }
    }//mkdir
    //****************************************************************//~vac6R~
    //*"delete cmd" deletes not empte directory,so empty chk required//~vac6R~
    //****************************************************************//~vac6R~
    private void funcRMDIR()
    {
		SmbFile file;
    //***************************                                  //~vac6I~
        String path=parmStr1;                                      //~vac6M~
        funcFINDFIRST(path,-1/*no attr selection*/);               //~vac6R~
        if (retCode==AxeSMBCifs.ENOTDIR)                           //~vac6I~
        	return;                                                //~vac6I~
        if (!(fileNo==0||(fileNo==2 && name.equals("."))))            //~vac6R~
        {                                                          //~vac6R~
            retCode=AxeSMBCifs.ENOTEMPTY;                          //~vac6R~
            return;                                                //~vac6R~
        }                                                          //~vac6R~
        PathInfo pi=PathInfo.parse(1/*assume dir*/,path);          //~vac6R~
        String dirname=pi.path;
		if ((file=AxeSMBCifs.doSmbFile(dirname))==null)            //~vac6R~
        	return;
        AxeSMBCifs.doDelete(file);                                 //~vac6R~
    }//rmdir
    //*****************************
    private void funcDELETE()
    {
	    funcDELETE(parmStr1);                                      //~vac6I~
    }//delete
    //*****************************                                //~vac6I~
    private void funcDELETE(String Pfnm)                           //~vac6I~
    {                                                              //~vac6I~
		SmbFile file;                                              //~vac6I~
        String path=Pfnm;                                          //~vac6I~
		if ((file=AxeSMBCifs.doSmbFile(path))==null)               //~vac6R~
        	return;                                                //~vac6I~
        AxeSMBCifs.doDelete(file);                                 //~vac6I~
    }//delete                                                      //~vac6I~
    //*****************************                                //~vac6I~
    private void funcRENAME()                                      //~vac6I~
    {                                                              //~vac6I~
    	funcRENAME(parmStr1,parmStr2);                            //~vac6I~
    }//rename                                                      //~vac6R~
    //*****************************                                //~vac6I~
    private void funcRENAME(String Pfnm)                            //~vac6I~
    {                                                              //~vac6I~
    	String f1,f2;                                              //~vac6I~
        int pos=Pfnm.indexOf('\t');                                //~vac6I~
        if (pos<0)
        {//~vac6I~
        	retCode=AxeSMBCifs.EINVAL;
        	return;
        }//~vac6I~
        f1=Pfnm.substring(0,pos);                                  //~vac6I~
        f2=Pfnm.substring(pos+1);                                  //~vac6I~
    	funcRENAME(f1,f2);
    }//rename                                                      //~vac6I~
    //*****************************
    private void funcRENAME(String Pfrom,String Pto)               //~vac6R~
    {
		SmbFile f1,f2;
        String path1=Pfrom;                                        //~vac6R~
        String path2=Pto;                                          //~vac6R~
		if ((f1=AxeSMBCifs.doSmbFile(path1))==null)                //~vac6R~
        	return;
		if ((f2=AxeSMBCifs.doSmbFile(path2))==null)                //~vac6R~
        	return;
        AxeSMBCifs.doRename(f1,f2);                                //~vac6R~
    }//rename                                                      //~vac6R~
    //*****************************                                //~vac6I~
    private void funcMULTIDEL()                                    //~vac6I~
    {                                                              //~vac6I~
        funcMULTI('d',parmStr1);                                   //~vac6I~
    }//delete                                                      //~vac6I~
    //*****************************                                //~vac6I~
    private void funcMULTIREN()                                    //~vac6I~
    {                                                              //~vac6I~
	    funcMULTI('r',parmStr1);                                   //~vac6I~
    }//delete                                                      //~vac6I~
    //*****************************                                //~vac6I~
    //*multiple cmd string                                         //~vac6I~
    //*cmd delm "cmd\tremote\tlocal;..." cmd delm:tab, operand delm:";"//~vac6I~
    //*cmd:'p'(put)/'g(get)'/'a'(append)                           //~vac6I~
    //*****************************                                //~vac6I~
    private void funcMULTI(char Pcmd,String Pfnm)                  //~vac6I~
    {                                                              //~vac6I~
        int pos,pos2,sz;                                           //~vac6I~
        String fnm;                                                //~vac6I~
    //**********************                                       //~vac6I~
        sz=Pfnm.length();                                          //~vac6I~
        for(pos=0;pos<sz;)                                         //~vac6I~
        {                                                          //~vac6I~
            pos2=Pfnm.indexOf(';',pos);                            //~vac6I~
            fnm=Pfnm.substring(pos,pos2);                 //~vac6I~
            pos=pos2+1;                                            //~vac6I~
            switch(Pcmd)                                            //~vac6I~
            {                                                      //~vac6I~
            case 'd':                                              //~vac6I~
		    	funcDELETE(fnm);                                   //~vac6I~
            	break;                                             //~vac6I~
            case 'r':                                              //~vac6I~
		    	funcRENAME(fnm);                                   //~vac6I~
            	break;                                             //~vac6I~
            }                                                      //~vac6I~
	        if (AxeSMBCifs.retCode!=0)                             //~vac6I~
    	    	break;                                             //~vac6I~
        }                                                          //~vac6I~
    }//funcMULTI                                                   //~vac6I~
    //*****************************                                //~vac6I~
    //*multiple cmd string                                         //~vac6I~
    //*cmd delm "cmd\tremote\tlocal;..." cmd delm:tab, operand delm:";"//~vac6I~
    //*cmd:'p'(put)/'g(get)'/'a'(append)                           //~vac6I~
    //*****************************                                //~vac6I~
    private void funcGETPUT()                                      //~vac6I~
    {                                                              //~vac6I~
        String pcmd=parmStr1,fnmremote,fnmlocal;                                      //~vac6I~
        int pos,pos2,sz,cmd;                                           //~vac6I~
        sz=pcmd.length();                                            //~vac6I~
        for(pos=0;pos<sz;)                                         //~vac6I~
        {                                                          //~vac6I~
            cmd=pcmd.charAt(pos);                                  //~vac6I~
            pos+=2;                                                //~vac6I~
            pos2=pcmd.indexOf('\t',pos);                           //~vac6I~
            fnmremote=pcmd.substring(pos,pos2);                    //~vac6I~
            pos=pos2+1;                                            //~vac6I~
            pos2=pcmd.indexOf(';',pos);                            //~vac6I~
            fnmlocal=pcmd.substring(pos,pos2);                     //~vac6I~
            pos=pos2+1;                                            //~vac6I~
            switch(cmd)                                            //~vac6I~
            {                                                      //~vac6I~
            case 'g':                                              //~vac6I~
		    	funcGET(fnmremote,fnmlocal);                       //~vac6I~
            	break;                                             //~vac6I~
            case 'p':                                              //~vac6I~
		    	funcPUT(fnmremote,fnmlocal);                       //~vac6I~
            	break;                                             //~vac6I~
            case 'a':                                              //~vac6I~
		    	funcAPPEND(fnmremote,fnmlocal);                    //~vac6I~
            	break;                                             //~vac6I~
            }                                                      //~vac6I~
        }                                                          //~vac6I~
    }//getput                                                      //~vac6I~
    //*****************************                                //~vac6I~
    private void funcGET()
    {
    	funcGET(parmStr1,parmStr2);                                //~vac6I~
    }//get
    private void funcGET(String Pfnm1,String Pfnm2)                //~vac6I~
    {                                                              //~vac6I~
		SmbFile smbFile;                                           //~vac6I~
		File localFile;                                            //~vac6I~
        String path1=Pfnm1;                                        //~vac6I~
        String path2=Pfnm2;                                        //~vac6I~
		if ((smbFile=AxeSMBCifs.doSmbFile(path1))==null)           //~vac6R~
        	return;                                                //~vac6I~
		localFile=new File(path2);                                 //~vac6I~
        AxeSMBCifs.doGet(smbFile,localFile);                       //~vac6R~
    }//get                                                         //~vac6I~
    //*****************************
    private void funcPUT()
    {                                                              //~vac6I~
    	funcPUT(parmStr1,parmStr2);                                //~vac6I~
    }                                                              //~vac6I~
    private void funcPUT(String Pfnm1,String Pfnm2)                //~vac6I~
    {
		SmbFile smbFile;
		File localFile;
        String path1=Pfnm1;                                        //~vac6R~
        String path2=Pfnm2;                                        //~vac6R~
		if ((smbFile=AxeSMBCifs.doSmbFile(path1))==null)           //~vac6R~
        	return;
		localFile=new File(path2);
        AxeSMBCifs.doPut(smbFile,localFile,false/*append*/);       //~vac6R~
    }//put
    private void funcAPPEND(String Pfnm1,String Pfnm2)             //~vac6I~
    {                                                              //~vac6I~
		SmbFile smbFile;                                           //~vac6I~
		File localFile;                                            //~vac6I~
        String path1=Pfnm1;                                        //~vac6I~
        String path2=Pfnm2;                                        //~vac6I~
		if ((smbFile=AxeSMBCifs.doSmbFile(path1))==null)            //~vac6R~
        	return;                                                //~vac6I~
		localFile=new File(path2);                                 //~vac6I~
        AxeSMBCifs.doPut(smbFile,localFile,true/*append*/);        //~vac6R~
    }//put                                                         //~vac6I~
    //*****************************
    private void funcLIST()
    {
		SmbFile smbFile;
        String path=parmStr1;
        PathInfo pi=PathInfo.parse(1/*assume dir*/,path);          //~vac6R~
		if ((smbFile=AxeSMBCifs.doSmbFile(pi.path))==null)         //~vac6R~
        	return;
		AxeSMBCifs.doList(smbFile);                                //~vac6R~
    }//funcList
    //*****************************
    private void funcSETMOD()
    {
		SmbFile smbFile;
        String path=parmStr1;
        int attrmask=parmInt1 & AxeSMBCifs.DOSATTR_MASK;           //~vac6R~
		if ((smbFile=AxeSMBCifs.doSmbFile(path))==null)            //~vac6R~
        	return;
		AxeSMBCifs.doSetAttributes(smbFile,attrmask);              //~vac6R~
        int newattr=AxeSMBCifs.doGetAttributes(smbFile);           //~vac6R~
        if (AxeSMBCifs.retCode!=0)                                 //~vac6R~
        	return;
        newattr &=AxeSMBCifs.DOSATTR_ALL;                          //~vac6R~
//        if (newattr!=attrmask)                                   //~vac6R~
//            retCode=1;                                           //~vac6R~
        attr=newattr;                                              //~vac6R~
    }//funcList
    //*****************************
    private void funcR2RCOPY()
    {
		SmbFile smbFileFrom,smbFileTo;
        String path1=parmStr1;
		if ((smbFileFrom=AxeSMBCifs.doSmbFile(path1))==null)       //~vac6R~
        	return;
        String path2=parmStr2;
		if ((smbFileTo=AxeSMBCifs.doSmbFile(path2))==null)         //~vac6R~
        	return;
		AxeSMBCifs.doCopyTo(smbFileFrom,smbFileTo);                //~vac6R~
    }//funcList
    //*****************************
    private void funcCONNECT()
    {
		SmbFile smbFile;
        String path=parmStr1;
		if ((smbFile=AxeSMBCifs.doSmbFile(path))==null)             //~vac6R~
        	return;
		AxeSMBCifs.doList(smbFile);                                //~vac6R~
    }//funcList
    //****************************************************************
    //****************************************************************
    public int doResponse()                                        //~vac6R~
    {
        if (Dump.Y) Dump.println("AxeSMBReq doResponse func="+smbfunc+",rc="+retCode+",doserr="+doserr);//~vac6R~
        switch(smbfunc)
        {
        case SMBCMD_STAT://         0x02
            respSTAT();
        	break;
        case SMBCMD_DIR://          0x01                           //~vac6M~
        	respDIR();                                             //~vac6M~
        	break;                                                 //~vac6M~
        case SMBCMD_FINDFIRST://          0x01                     //~vac6I~
        	respFINDFIRST();                                       //~vac6I~
        	break;                                                 //~vac6I~
        case SMBCMD_GETPUT://      0x12                            //~vac6I~
//            respGETPUT();                                        //~vac6I~
        	break;                                                 //~vac6I~
        case SMBCMD_GET://          0x03
//            funcGET();
        	break;
        case SMBCMD_PUT://          0x04
//            funcPUT();
        	break;
        case SMBCMD_DELETE://       0x05
            respDELETE();
        	break;
        case SMBCMD_RENAME://       0x06
            respRENAME();
        	break;
        case SMBCMD_CHMOD://        0x07
//            funcCHMOD();
        	break;
        case SMBCMD_CD://           0x08
//            funcCD();
        	break;
        case SMBCMD_LCD://          0x09
//            funcLCD();
        	break;
        case SMBCMD_PUT2://         0x0a //put a b(2 opd rename to)
//            funcPUT2();
        	break;
        case SMBCMD_MKDIR://        0x0b
            respMKDIR();
        	break;
        case SMBCMD_RMDIR://        0x0c
            respRMDIR();
        	break;
        case SMBCMD_PWD://          0x0d
//            funcPWD();
        	break;
        case SMBCMD_STATDIR://      0x0e    //stat for the file in the dir all at once
//            funcSTATDIR();
        	break;
        case SMBCMD_SETMOD://       0x0f    //target:window "arsh" attr
            respSETMOD();
        	break;
        case SMBCMD_CONNECT://      0x12
            respCONNECT();
        	break;
        }
        return retCode;                                       //~vac6R~
    }
    //***************************
    private void respDIR()
    {
        if (Dump.Y) Dump.println("respDir:"+parmStr1+",fileno="+fileNo);
        Gxeh.SMB_fileno=fileNo;	                                   //~vac6I~
        Gxeh.SMB_namelist=listName;                                //~vac6I~
        Gxeh.SMB_attrlist=listAttr;                                //~vac6I~
        Gxeh.SMB_sizelist=listSize;                                //~vac6I~
        Gxeh.SMB_timelist=listTime;                                //~vac6I~
    }
    //***************************
    private void respSTAT()
    {
        if (Dump.Y) Dump.println("respStat:"+parmStr1+",attr="+Integer.toHexString(attr)+",size="+Long.toHexString(size)+",time="+Integer.toHexString(time));//~vac6R~
        Gxeh.SMB_name=name;                                        //~vac6I~
        Gxeh.SMB_attr=attr;                                        //~vac6I~
        Gxeh.SMB_size=size;                                        //~vac6I~
        Gxeh.SMB_time=time;                                        //~vac6I~
    }
    //***************************                                  //~vac6I~
    private void respFINDFIRST()                                   //~vac6I~
    {                                                              //~vac6I~
        Gxeh.SMB_fileno=fileNo;                                    //~vac6I~
        Gxeh.SMB_name=name;                                        //~vac6I~
        Gxeh.SMB_attr=attr;                                        //~vac6I~
        Gxeh.SMB_size=size;                                        //~vac6I~
        Gxeh.SMB_time=time;                                        //~vac6I~
    }                                                              //~vac6I~
    //***************************
    private void respMKDIR()
    {
        if (Dump.Y) Dump.println("respMkdir:"+parmStr1+",attr="+Integer.toHexString(attr));
    }
    //***************************
    private void respRMDIR()
    {
        if (Dump.Y) Dump.println("respRmdir:"+parmStr1+",attr="+Integer.toHexString(attr));//~vac6R~
    }
    //***************************
    private void respDELETE()
    {
        if (Dump.Y) Dump.println("respDelete:"+parmStr1);
    }
    //***************************
    private void respRENAME()
    {
        if (Dump.Y) Dump.println("respRename:"+parmStr1+","+parmStr2);
    }
    //***************************
    private void respSETMOD()
    {
        Gxeh.SMB_attr=attr;	//setattr result                       //~vac6I~
        if (Dump.Y) Dump.println("respSetMod:"+parmStr1+",attr="+Integer.toHexString(attr));
    }
    //***************************
    private void respCONNECT()
    {
		connectPending=false;                                      //~vac6I~
    	if (listName!=null)
        {
    		for (int ii=0;ii<listName.length;ii++)
            {
        		if (Dump.Y) Dump.println("respConnect:ii="+ii+":"+listName[ii]);
        	}
        }
        if (Dump.Y) Dump.println("respConnect rc="+retCode);       //~vac6I~
        if (retCode==0)                                            //~vac6I~
    		Utils.showToastLong(MSGID_CONNECTED,parmStr1);             //~vac6M~
    }
}//class
