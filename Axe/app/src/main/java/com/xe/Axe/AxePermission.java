//*CID://+vc2WR~:                                   update#=  126; //~vc2WR~
//**************************************************************** //~vaa8I~
//vc2W 2020/09/19 at first install, uerrexit by ualloc size=0(Gscrbuffwidth); Because notifyOptionChangedOther is called before initscreen; notify trace option by another interface//~vc2WI~
//vc1f 2020/06/20 ARM;chk sdcard writable                          //~vc1fI~
//vc1d 2020/06/20 ARM;use private dir when SDcard not available    //~vc1dI~
//vc1b 2020/06/19 runtime permission frp android6                  //~vc1bI~
//**************************************************************** //~vaa8I~
package com.xe.Axe;

import android.Manifest;
import com.ahsv.utils.UView;

import java.util.Arrays;

public class AxePermission                                         //~vc1bR~
{
//********************************************************         //~vc1bI~
	public AxePermission()                                         //~vc1bI~
    {                                                              //~vc1bI~
        if (Dump.Y) Dump.println("AxePermission constructor");     //~vc1bI~
    }                                                              //~vc1bI~
    public void request()                                          //~vc1bR~
    {                                                              //~vc1bI~
    	String type;                                               //~vc1bI~
        boolean rc;                                                //~vc1bI~
        int id;                                                //~vc1bI~
        if (Dump.Y) Dump.println("AxePermission");//~9930I~        //~vc1bI~
                                                                   //~vc1bI~
        type= Manifest.permission.WRITE_EXTERNAL_STORAGE;          //~vc1bI~
    	id=Axe.PERMISSION_EXTERNAL_STORAGE;	//write //~1AH1I~      //~vc1bI~
    	request(type,id);                                          //~vc1bR~
        type= Manifest.permission.READ_EXTERNAL_STORAGE;           //~vc1bI~
    	id=Axe.PERMISSION_EXTERNAL_STORAGE_READ;                   //~vc1bI~
    	request(type,id);                                          //~vc1bR~
    }                                                              //~vc1bI~
//********************************************************         //~vc1bI~
    public static boolean getSDCardPermission()                    //~vc1dR~
    {                                                              //~vc1bI~
        String type=Manifest.permission.WRITE_EXTERNAL_STORAGE;    //~vc1dI~
    	boolean rc=UView.isPermissionGranted(type);                //~vc1dI~
        if (Dump.Y) Dump.println("AxePermission.getSDCardPermission rc="+rc);//~vc1dI~
        return rc;                                                 //~vc1dI~
    }                                                              //~vc1dI~
//********************************************************         //~vc1dI~
    private void request(String Ptype,int Pid)                     //~vc1dI~
    {                                                              //~vc1dI~
        if (Dump.Y) Dump.println("AxePermission.request type="+Ptype+",id="+Pid);//~vc1bI~
    	boolean rc=UView.isPermissionGranted(Ptype);                       //~vc1bR~
        if (!rc)                                                   //~vc1bI~
	    	UView.requestPermission(Ptype,Pid);                 //~vc1bI~
        if (Pid==Axe.PERMISSION_EXTERNAL_STORAGE)                  //~vc1dI~
        {                                                          //~vc1dI~
        	if (!rc)                                               //~vc1dI~
            	Gxeh.axeStatus|=Gxeh.AXES_NOPERMISSION_EXTERNAL_STIORAGE_WRITE;//~vc1dI~
        	else                                                   //~vc1dI~
            	Gxeh.axeStatus&=~Gxeh.AXES_NOPERMISSION_EXTERNAL_STIORAGE_WRITE;//~vc1dI~
        }                                                          //~vc1dI~
        if (Dump.Y) Dump.println("AxePermission.request axeStatus="+Gxeh.axeStatus);//~vc1dI~
    }                                                              //~vc1bI~
//***************************************************************************//~9930I~//~1AhdI~//~vc1bI~
    public void onRequestPermissionsResult(int PrequestID,String[] Ptypes,int[] Presults)//~9930I~//~1AhdI~//~vc1bI~
    {                                                              //~9930I~//~1AhdI~//~vc1bI~
        if (Dump.Y) Dump.println("AxePermission.onRequestPermissionResult reqid="+PrequestID+",type="+ com.ahsv.utils.Utils.toString(Ptypes)+",result="+ Arrays.toString(Presults));//~9930I~//~1AhdI~//~vc1bR~//+vc2WR~
        boolean granted;                                           //~1AhdI~/~vc1bI~
        switch(PrequestID)                                         //~9930I~//~1AhdI~//~vc1bI~
        {                                                          //~9930I~//~1AhdI~//~vc1bI~
//        case PERMISSION_LOCATION:                                  //~9930I~//~1AhdI~//~vc1bI~
//            granted= UView.isPermissionGranted(Presults[0]); //~9930I~//~1AhdI~//~vc1bI~
//            WDA.grantedWifi(granted);                               //~1AhdI~//~vc1bI~
//            break;                                                 //~9930I~//~1AhdI~//~vc1bI~
        case Axe.PERMISSION_EXTERNAL_STORAGE:      //write                    //~9B09I~//~1AhdI~//~vc1bI~
	      	granted=UView.isPermissionGranted(Presults[0]);//~9B09I~//~1AhdI~//~vc1bI~
            if (!granted)                                          //~vc1dR~
	            Gxeh.axeStatus|=Gxeh.AXES_NOPERMISSION_EXTERNAL_STIORAGE_WRITE;//~vc1dI~
            else                                                   //~vc1dI~
	            Gxeh.axeStatus&=~Gxeh.AXES_NOPERMISSION_EXTERNAL_STIORAGE_WRITE;//~vc1dI~
//  		AxeJNI.notifyOptionChangedOther();                     //~vc1fI~//~vc2WR~
    		AxeJNI.notifyOptionChangedOtherPermission();           //~vc2WI~
            showGranted(granted,Manifest.permission.WRITE_EXTERNAL_STORAGE);//~vc1bI~
        	break;                                                 //~9B09I~//~1AhdI~//~vc1bI~
        case Axe.PERMISSION_EXTERNAL_STORAGE_READ:      //write        //~vc1bI~
	      	granted= UView.isPermissionGranted(Presults[0]);        //~vc1bI~
            showGranted(granted, Manifest.permission.READ_EXTERNAL_STORAGE);//~vc1bI~
        	break;                                                 //~vc1bI~
        }                                                          //~9930I~//~1AhdI~//~vc1bI~
        if (Dump.Y) Dump.println("AxePermission.onRequestPermissionResult axeStatus="+Gxeh.axeStatus);//~vc1dI~
    }                                                              //~9930I~//~1AhdI~//~vc1bI~
//***************************************************************************//~vc1bI~
    public static void showGranted(boolean PswGranted,String Ptype)  //~v@@@I~//~vc1bI~
    {                                                              //~v@@@I~//~vc1bI~
    	boolean rc;                                                //~v@@@I~//~vc1bI~
        if (Dump.Y) Dump.println("AxePermission.showGranted PswGranted="+PswGranted);//~v@@@I~//~vc1bI~//~vc2WR~
        if (!PswGranted)                                           //~v@@@I~//~vc1bI~
        {                                                          //~v@@@I~//~vc1bI~
          	UView.showToastLong(com.ahsv.utils.Utils.getStr(R.string.PermissionNotGranted,Ptype));//~vc1bI~
            return;                                                //~v@@@I~//~vc1bI~
        }	                                                       //~v@@@I~//~vc1bI~
        UView.showToastShort(com.ahsv.utils.Utils.getStr(R.string.PermissionGranted,Ptype));//~vc1bI~
    }                                                              //~v@@@I~//~vc1bI~
}//class
