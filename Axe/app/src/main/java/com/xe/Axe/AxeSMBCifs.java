//*CID://+vac6R~:                                   update#=  44;  //~vac6I~
//**************************************************************** //~vac6I~
//vac6:120217 (Axe)samba share support using jcifs 3.17            //~vac6I~
//**************************************************************** //~vac6I~
package com.xe.Axe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import jcifs.smb.DosFileFilter;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;
import static jcifs.smb.NtStatus.*;        //NT_STATUS_
import static jcifs.smb.SmbFile.*;        //NT_STATUS_

//*CID://+dateR~: update#= 340;
//**********************************************************************
//*cifs cmd process                                                //~vac6R~
//*doserr or nostatus is set if rc!=0                              //~vac6R~
//**********************************************************************
public class AxeSMBCifs
{
	private static final int  ENOENT     =2;  //No such file or directory
	public  static final int  ENOTDIR    =20; //Not a directory
	private static final int  EACCES     =13; //Permission denied
	private static final int  EEXIST     =17; //File exists
	public  static final int  EINVAL     =22; //Invalid argument
	private static final int  EREMOTEIO  =121;//Remote IO error
	private static final int  EFAULT     =14; //Bad address//~vac6I~
	private static final int  ENETUNREACH=101;//Network is unreachable//~vac6I~
	private static final int  EBUSY		 =16;	/* Device or resource busy *///~vac6I~
	public  static final int  ENOTEMPTY	 =39;	/* Directory not empty *///~vac6I~
	public  static final int  ECHILD   	 =10;	/* No child Process *///~vac6R~
                                                                   //~vac6I~
	public  static final int  PATHERR    =ENOTDIR;  //borrow this(diff with standard ENOTDIR is errmsg)//~vac6R~
    public  static final int  IOERR      =EREMOTEIO;//other exception//~vac6R~
    public  static final int  OTHERERR   =EFAULT;   //borrow this               //~vac6I~
    public  static final int  NETERR     =ENETUNREACH;//for all network err//~vac6I~
    public  static final int  BUSY       =EBUSY;//for all network err//~vac6I~
    public  static final int  NOMOREFILE =ECHILD;//borrow          //~vac6I~
//Windows set                                                      //~vac6I~
    private static final int STATUS_NO_MORE_FILES =0x80000006;     //~vac6I~
                                                                   //~vac6I~
	private static final int[][] nts2dosetb=
    {
//      { NT_STATUS_OK,                                           },
//      { NT_STATUS_UNSUCCESSFUL,                                 },
//      { NT_STATUS_NOT_IMPLEMENTED,                              },
//      { NT_STATUS_INVALID_INFO_CLASS,                           },
      { NT_STATUS_ACCESS_VIOLATION,                 EACCES      },
//      { NT_STATUS_INVALID_HANDLE,                               },
      { NT_STATUS_INVALID_PARAMETER,                EINVAL      },
      { NT_STATUS_NO_SUCH_DEVICE,                   PATHERR     },
//    { NT_STATUS_NO_SUCH_FILE,                     ENOENT      }, //~vac6R~
      { NT_STATUS_NO_SUCH_FILE,                     NOMOREFILE  }, //listDIR width attr/wildcard//~vac6I~
//      { NT_STATUS_MORE_PROCESSING_REQUIRED,                     },
      { NT_STATUS_ACCESS_DENIED,                    EACCES      },
//      { NT_STATUS_BUFFER_TOO_SMALL,                             },
      { NT_STATUS_OBJECT_NAME_INVALID,              EINVAL      },
      { NT_STATUS_OBJECT_NAME_NOT_FOUND,            ENOENT      },
//      { NT_STATUS_OBJECT_NAME_COLLISION,                        },
//      { NT_STATUS_PORT_DISCONNECTED,                            },
      { NT_STATUS_OBJECT_PATH_INVALID,              EINVAL      },
      { NT_STATUS_OBJECT_PATH_NOT_FOUND,            PATHERR     },
      { NT_STATUS_OBJECT_PATH_SYNTAX_BAD,           EINVAL      },
      { NT_STATUS_SHARING_VIOLATION,                EACCES      },
//      { NT_STATUS_DELETE_PENDING,                               },
//      { NT_STATUS_NO_LOGON_SERVERS,                             },
//      { NT_STATUS_USER_EXISTS,                                  },
//      { NT_STATUS_NO_SUCH_USER,                                 },
//      { NT_STATUS_WRONG_PASSWORD,                               },
//      { NT_STATUS_LOGON_FAILURE,                                },
//      { NT_STATUS_ACCOUNT_RESTRICTION,                          },
//      { NT_STATUS_INVALID_LOGON_HOURS,                          },
//      { NT_STATUS_INVALID_WORKSTATION,                          },
//      { NT_STATUS_PASSWORD_EXPIRED,                             },
//      { NT_STATUS_ACCOUNT_DISABLED,                             },
//      { NT_STATUS_NONE_MAPPED,                                  },
//      { NT_STATUS_INVALID_SID,                                  },
//      { NT_STATUS_INSTANCE_NOT_AVAILABLE,                       },
//      { NT_STATUS_PIPE_NOT_AVAILABLE,                           },
//      { NT_STATUS_INVALID_PIPE_STATE,                           },
//      { NT_STATUS_PIPE_BUSY,                                    },
//      { NT_STATUS_PIPE_DISCONNECTED,                            },
//      { NT_STATUS_PIPE_CLOSING,                                 },
//      { NT_STATUS_PIPE_LISTENING,                               },
//      { NT_STATUS_FILE_IS_A_DIRECTORY,                          },
      { NT_STATUS_DUPLICATE_NAME,                   EEXIST      },
//      { NT_STATUS_NETWORK_NAME_DELETED,                         },
//      { NT_STATUS_NETWORK_ACCESS_DENIED,                        },
//      { NT_STATUS_BAD_NETWORK_NAME,                             },
//      { NT_STATUS_REQUEST_NOT_ACCEPTED,                         },
//      { NT_STATUS_CANT_ACCESS_DOMAIN_INFO,                      },
//      { NT_STATUS_NO_SUCH_DOMAIN,                               },
//      { NT_STATUS_NOT_A_DIRECTORY,                              },
//      { NT_STATUS_CANNOT_DELETE,                                },
//      { NT_STATUS_INVALID_COMPUTER_NAME,                        },
//      { NT_STATUS_PIPE_BROKEN,                                  },
//      { NT_STATUS_NO_SUCH_ALIAS,                                },
//      { NT_STATUS_LOGON_TYPE_NOT_GRANTED,                       },
//      { NT_STATUS_NO_TRUST_SAM_ACCOUNT,                         },
//      { NT_STATUS_TRUSTED_DOMAIN_FAILURE,                       },
//      { NT_STATUS_NOLOGON_WORKSTATION_TRUST_ACCOUNT,            },
//      { NT_STATUS_PASSWORD_MUST_CHANGE,                         },
//      { NT_STATUS_NOT_FOUND,                                    },
//      { NT_STATUS_ACCOUNT_LOCKED_OUT,                           },
//      { NT_STATUS_PATH_NOT_COVERED,                             },
//      { NT_STATUS_IO_REPARSE_TAG_NOT_HANDLED,                   },
//Windows set                                                      //~vac6I~
        { STATUS_NO_MORE_FILES,                     NOMOREFILE    },//~vac6I~
                    };
    private static final int RC_FAILED    =8;
    private static final int BUFFSZ       =16384;
    public static final int DOSATTR_MASK    =(ATTR_READONLY|ATTR_HIDDEN|ATTR_SYSTEM|ATTR_ARCHIVE);//0x27;//A(20),S(04)H(02),R(01)
    public static final int DOSATTR_DIR     =ATTR_DIRECTORY;
    public static final int DOSATTR_ALL     =0x3f; //DOSATTR_MASK and directory(10)+volume(08)//~vac6I~
    private static final int EXT_DOSATTR_NORMAL=0x80;//SmbFile.ATTR_NORMAL;	//attr selection requires bit ON for acceptance//~vac6I~
    private static final String CONNECTION_ERR="Failed to connect";//~vac6I~
    public static int doserr,retCode;
    public static int status;//~vac6R~
    //****************************************************************
    //************************************
    private static int status2doserr()                             //~vac6R~
    {
        if (Dump.Y) Dump.println("status to doserr status="+Integer.toHexString(status)+",doserr="+doserr);//~vac6R~
    	for (int ii=0;ii<nts2dosetb.length;ii++)
        	if (status==nts2dosetb[ii][0])
            {
            	int doserr=nts2dosetb[ii][1];
		        if (Dump.Y) Dump.println("status to doserr doserr="+Integer.toHexString(doserr));
            	return doserr;
            }
    	return OTHERERR;                                           //~vac6R~
    }//status2doserr                                               //~vac6R~
    //************************************                         //~vac6I~
    private static String getFilePath(String Ppath)                //~vac6I~
    {                                                              //~vac6I~
    	int pos;                                                   //~vac6I~
        if (Dump.Y) Dump.println("status to doserr status="+Integer.toHexString(status)+",doserr="+doserr);//~vac6I~
        pos=Ppath.indexOf("://");   //  smb://...                  //~vac6I~
        if (pos<=0)                                                //~vac6I~
        	return Ppath;                                          //~vac6I~
        pos=Ppath.indexOf('/',pos);  //  smb://server/              //~vac6I~
        if (pos<0)                                                 //~vac6I~
        	return Ppath;                                          //~vac6I~
        return Ppath.substring(pos);                               //~vac6I~
    }//getFilePath                                                 //~vac6I~
    //************************************                         //~vac6I~
    private static void chkSmbException(SmbException e,String Pcmt,SmbFile PsmbFile)//~vac6R~
    {                                                              //~vac6I~
        status=e.getNtStatus();                                    //~vac6M~
    	if (Dump.Y) Dump.println("SmbException:status="+Integer.toHexString(status));//~vac6I~
        String errmsg=e.toString();                                //~vac6I~
        int pos=errmsg.indexOf('\n');                              //~vac6I~
        String toastmsg;                                           //~vac6I~
        String fpath=getFilePath(PsmbFile.getCanonicalPath());     //~vac6R~
        if (pos>0)                                                 //~vac6I~
            toastmsg=Pcmt+":"+fpath+":"+errmsg.substring(0,pos);   //~vac6R~
        else                                                       //~vac6I~
            toastmsg=Pcmt+":"+fpath+":"+errmsg;                    //~vac6R~
        if (errmsg.contains(CONNECTION_ERR))                       //~vac6I~
        	doserr=NETERR;                                         //~vac6I~
        else                                                       //~vac6I~
    		doserr=status2doserr();                                //~vac6I~
        Gxeh.SMB_errmsg=toastmsg;                                  //~vac6I~
        if (doserr!=ENOENT                                         //~vac6I~
//      &&  doserr!=PATHERR                                        //~vac6R~
        )                                                          //~vac6I~
        {                                                          //~vac6I~
    	    Dump.println(e,"SmbException:"+Pcmt+":"+fpath+",status="+Integer.toHexString(status));//~vac6R~
			if (!AxeG.isMainThread())	//Node cmd only            //+vac6I~
            	Utils.showToastLong(toastmsg);                     //+vac6R~
        }                                                          //~vac6I~
        else                                                       //~vac6I~
        {                                                          //~vac6I~
            if (Dump.Y) Dump.println(toastmsg);                    //~vac6I~
        }                                                          //~vac6I~
        retCode=RC_FAILED;                                         //~vac6I~
    }                                                              //~vac6I~
    //************************************                         //~vac6I~
    private static void chkNonSmbException(Exception e,String Pcmt,SmbFile PsmbFile,int Pdoserr)//~vac6R~
    {                                                              //~vac6I~
        String errmsg=e.toString();                                //~vac6I~
        int pos=errmsg.indexOf('\n');                              //~vac6I~
        String toastmsg;                                           //~vac6I~
//      String fpath=getFilePath(Ppath)+Plocal.getAbsolutePath();  //~vac6R~
        if (pos>0)                                                 //~vac6I~
            toastmsg=Pcmt+":"+errmsg.substring(0,pos);             //~vac6R~
        else                                                       //~vac6I~
            toastmsg=Pcmt+":"+errmsg;                              //~vac6R~
		if (!AxeG.isMainThread())	//Node cmd only                //+vac6I~
        	Utils.showToastLong(toastmsg);                         //+vac6R~
        Gxeh.SMB_errmsg=toastmsg;                                  //~vac6I~
        if (Dump.Y) Dump.println(toastmsg);                        //~vac6I~
    	Dump.println(e,"SmbException"+Pcmt+":"+",status="+Integer.toHexString(status)+",doserr="+Pdoserr);//~vac6I~
        doserr=Pdoserr;                                            //~vac6I~
        retCode=RC_FAILED;                                         //~vac6I~
    }                                                              //~vac6I~
    private static void chkNonSmbException(Exception e,String Pcmt,String Ppath,int Pdoserr)//~vac6I~
    {                                                              //~vac6I~
        String errmsg=e.toString();                                //~vac6I~
        int pos=errmsg.indexOf('\n');                              //~vac6I~
        String toastmsg;                                           //~vac6I~
        if (pos>0)                                                 //~vac6I~
            toastmsg=Pcmt+":"+errmsg.substring(0,pos);             //~vac6I~
        else                                                       //~vac6I~
            toastmsg=Pcmt+":"+errmsg;                              //~vac6I~
		if (!AxeG.isMainThread())	//Node cmd only                //+vac6I~
	        Utils.showToastLong(toastmsg);                         //+vac6R~
        Gxeh.SMB_errmsg=toastmsg;                                  //~vac6I~
        if (Dump.Y) Dump.println(toastmsg);                        //~vac6I~
    	Dump.println(e,"SmbException"+Pcmt+":"+",path="+Ppath+",doserr="+Pdoserr);//~vac6I~
        doserr=Pdoserr;                                            //~vac6I~
        retCode=RC_FAILED;                                         //~vac6I~
    }                                                              //~vac6I~
    //************************************
    public static SmbFile doSmbFile(String Pfnm)
    {
		SmbFile smbFile=null;
        if (Dump.Y) Dump.println("@@@@ doSmbFile:"+Pfnm);          //~vac6R~
      	try
        {
			smbFile=new SmbFile(Pfnm);
        }
		catch (MalformedURLException e)
        {
            status=NT_STATUS_OBJECT_NAME_INVALID;                  //~vac6M~
    		chkNonSmbException(e,"put",Pfnm,EINVAL);               //~vac6R~
        }
    	return smbFile;
    }//doSmbFile
    //************************************
    public static String[] doList(SmbFile PsmbFile)                //~vac6R~
    {
        if (Dump.Y) Dump.println("doList:"+PsmbFile.getCanonicalPath());//~vac6R~
        String res[]=null;
      	try
        {
			res=PsmbFile.list();
        }
		catch (SmbException e)
        {
    		chkSmbException(e,"List",PsmbFile);                             //~vac6R~
        }
    	return res;
    }//doSmbFile
    //**********************************************************************************//~vac6R~
    //*Pattr:0 means normal file(not DIR)                          //~vac6R~
    //*-1:force nor attr selection(no user on ulib/xe,but internaly specify from rmdir()//~vac6R~
    //*********************************************************************************//~vac6R~
    public static SmbFile[] doListFile(SmbFile PsmbFile,String Pwildcard,int Pattr)//~vac6R~
    {
    	SmbFile[] list=null;
        DosFileFilter dff;
    	int reqattr=Pattr & DOSATTR_ALL;                           //~vac6I~
    	reqattr|=EXT_DOSATTR_NORMAL;                               //~vac6I~
        if (Dump.Y) Dump.println("doListFile name="+PsmbFile.getCanonicalPath()+",attr="+Integer.toHexString(reqattr));//~vac6R~
        if (Pwildcard!=null)
        	if (Pattr!=-1)
            	dff=new DosFileFilter(Pwildcard,reqattr);          //~vac6R~
            else
            	dff=new DosFileFilter(Pwildcard,0xffff);
        else
        	if (Pattr!=-1)
            	dff=new DosFileFilter("*",reqattr);                //~vac6R~
            else
            	dff=null;
    	try
        {
        	if (dff!=null)
	        	list=PsmbFile.listFiles(dff);
            else
	        	list=PsmbFile.listFiles();
        }
        catch(SmbException e)
        {
    		chkSmbException(e,"ListFile",PsmbFile);                //~vac6R~
        }
    	return list;
    }//doListFile
    //************************************
    public static int doGetAttributes(SmbFile PsmbFile)            //~vac6R~
    {
    	int attr=0,attr2;                                          //~vac6R~
    	try
        {                                                          //~vac6R~
            attr2=PsmbFile.getAttributes();                        //~vac6R~
            attr=attr2 & DOSATTR_ALL;                              //~vac6I~
	        if (Dump.Y) Dump.println("doGetAttribute:"+PsmbFile.getCanonicalPath()+":"+Integer.toHexString(attr2)+"->"+Integer.toHexString(attr));//~vac6R~
        }
        catch(SmbException e)
        {
    		chkSmbException(e,"getAttributes",PsmbFile);           //~vac6R~
        }
    	return attr;
    }//doGetAttribute
    //************************************
    public static long doGetSize(SmbFile PsmbFile)                 //~vac6R~
    {
    	long size=0;
    	try
        {
            size=PsmbFile.length();
	        if (Dump.Y) Dump.println("doGetSize:"+PsmbFile.getCanonicalPath()+":"+Long.toHexString(size));//~vac6R~
        }
        catch(SmbException e)
        {
    		chkSmbException(e,"getSize",PsmbFile);                 //~vac6R~
        }
    	return size;
    }//doGetSize
    //************************************
    public static void doMkDir(SmbFile PsmbFile)                   //~vac6R~
    {
    	try
        {
	        if (Dump.Y) Dump.println("doMkDir:"+PsmbFile.getCanonicalPath());//~vac6I~
            PsmbFile.mkdir();
        }
        catch(SmbException e)
        {
    		chkSmbException(e,"mkdir",PsmbFile);                   //~vac6R~
        }
    }//doGetSize
    //************************************
    public static void doSetAttributes(SmbFile PsmbFile,int Pattr) //~vac6R~
    {
    	try
        {
            int attr=Pattr & DOSATTR_MASK;                         //~vac6R~
            PsmbFile.setAttributes(attr);
	        if (Dump.Y) Dump.println("doSetAttributes:"+PsmbFile.getCanonicalPath()+",attr="+Integer.toHexString(attr));//~vac6R~
        }
        catch(SmbException e)
        {
    		chkSmbException(e,"setAttributes",PsmbFile);           //~vac6R~
        }
    }//doSetAttribute
    //************************************
    //*del file or dir
    //************************************
    public static void doDelete(SmbFile PsmbFile)                  //~vac6R~
    {
    	try
        {
            PsmbFile.delete();
	        if (Dump.Y) Dump.println("doDelete:"+PsmbFile.getCanonicalPath());//~vac6R~
        }
        catch(SmbException e)
        {
    		chkSmbException(e,"delete",PsmbFile);                  //~vac6R~
        }
    }//doDelete
    //************************************
    public static void doRename(SmbFile Pfrom,SmbFile Pto)         //~vac6R~
    {
    	try
        {
            Pfrom.renameTo(Pto);
	        if (Dump.Y) Dump.println("doRename:"+Pfrom.getCanonicalPath()+",to:"+Pto.getCanonicalPath());//~vac6R~
        }
        catch(SmbException e)
        {
    		chkSmbException(e,"renameTo "+getFilePath(Pto.getCanonicalPath()),Pfrom);//~vac6R~
        }
    }//doDelete
    //************************************
    public static long doGet(SmbFile PsmbFile,File Plocal)         //~vac6R~
    {
        long ctr,totctr=0;
        long mtime;                                                //~vac6I~
        byte[] buff=new byte[BUFFSZ];
    	try
        {
        	mtime=PsmbFile.getLastModified();                      //~vac6I~
            SmbFileInputStream in=new SmbFileInputStream(PsmbFile);
            FileOutputStream ot=new FileOutputStream(Plocal);
            while((ctr=in.read(buff)) > 0 )
            {
            	ot.write(buff,0,(int)ctr);
            	totctr+=ctr;
            }
            in.close();
            ot.close();
            Plocal.setLastModified(mtime);                         //~vac6I~
	        if (Dump.Y) Dump.println("doGet:"+PsmbFile.getCanonicalPath()+",total size="+totctr);//~vac6R~
        }
        catch(SmbException e)
        {
    		chkSmbException(e,"get",PsmbFile);                     //~vac6R~
        }
        catch(Exception e)
        {
    		chkNonSmbException(e,"put",PsmbFile,IOERR);            //~vac6R~
        }
        return totctr;
    }//doGet
    //************************************
    public static long doPut(SmbFile PsmbFile,File Plocal,boolean Pappend)//~vac6R~
    {
        long ctr,totctr=0;
        long mtime=0;                                                //~vac6I~
        byte[] buff=new byte[BUFFSZ];
    	try
        {
            if (!Pappend)                                          //~vac6I~
            	mtime=Plocal.lastModified();                       //~vac6I~
            SmbFileOutputStream ot;
			ot = new SmbFileOutputStream(PsmbFile,Pappend);        //~vac6R~
            FileInputStream in=new FileInputStream(Plocal);
				while((ctr=in.read(buff)) > 0 )
				{
					ot.write(buff,0,(int)ctr);
					totctr+=ctr;
				}
            in.close();
            ot.close();
            if (!Pappend)                                          //~vac6I~
            	doSetTime(PsmbFile,mtime);                         //~vac6I~
	        if (Dump.Y) Dump.println("doPut:append="+Pappend+",remote="+PsmbFile.getCanonicalPath()+",local="+Plocal.getCanonicalPath()+",total size="+totctr);//~vac6R~
        }
		catch (MalformedURLException e)
        {
            status=NT_STATUS_OBJECT_NAME_INVALID;                  //~vac6M~
    		chkNonSmbException(e,"put",PsmbFile,EINVAL);           //~vac6R~
        }
		catch (UnknownHostException e)
        {
			status=NT_STATUS_BAD_NETWORK_NAME;                     //~vac6M~
    		chkNonSmbException(e,"put",PsmbFile,NETERR);           //~vac6R~
        }
        catch(SmbException e)
        {
    		chkSmbException(e,"put",PsmbFile);                     //~vac6R~
        }
		catch (FileNotFoundException e)
        {
    		chkNonSmbException(e,"put",PsmbFile,ENOENT);           //~vac6R~
		}
		catch (IOException e)
        {
    		chkNonSmbException(e,"put",PsmbFile,IOERR);            //~vac6R~
		}
        return totctr;
    }//doPut
    //************************************
    public static void doSetTime(SmbFile PsmbFile,long Ptime)      //~vac6R~
    {
    	try
        {
            PsmbFile.setLastModified(Ptime);
	        if (Dump.Y) Dump.println("doSetTime:"+PsmbFile.getCanonicalPath()+",time="+Long.toHexString(Ptime));//~vac6R~
        }
        catch(SmbException e)
        {
    		chkSmbException(e,"setLastModified",PsmbFile);         //~vac6R~
        }
    }//doSetTime
    //************************************
    public static void doCopyTo(SmbFile Pfrom,SmbFile Pto)         //~vac6R~
    {
        if (Dump.Y) Dump.println("doCopyTo:"+Pto.getCanonicalPath()+",to="+Pto.getCanonicalPath());//~vac6R~
    	try
        {
            Pfrom.copyTo(Pto);
        }
        catch(SmbException e)
        {
    		chkSmbException(e,"copyTo "+getFilePath(Pto.getCanonicalPath()),Pfrom);//~vac6R~
        }
    }//doSetTime
}//class
