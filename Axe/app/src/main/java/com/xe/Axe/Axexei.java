//*CID://+DATER~: update#=   90;                                   //~1528R~
package com.xe.Axe;                                                //~@@@@I~

import android.content.Intent;

//*****************************************************************//~1606I~
                                                                   //~1606I~
public class Axexei                                                //~1623R~
{                                                                  //~1606I~
///////////////////////////////////////////////////////////////////////////////~1606I~
//*********************                                            //~1620I~
    public static void wxe_uerrexitstdo(byte[] Pmsg)               //~1623R~
    {                                                              //~1620I~
        String msg=new String(Pmsg);                               //~1623R~
//       int flag=AxeAlert.BUTTON_POSITIVE|AxeAlert.EXIT;           //~1623R~
//       AxeAlert.simpleAlertDialog(null/*callback*/,null/*title*/,msg,flag);//~1623R~
        Intent intent=new Intent(AxeG.activity,com.xe.Axe.AxeChild.class);//~1623I~
        intent.putExtra("uerrexitmsg",msg);                        //~1623I~
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);            //~1623I~
        AxeG.activity.startActivity(intent);                                     //~1623I~
        Utils.exit(24);                                            //+1623I~
    }                                                              //~1622R~
}                                                                  //~1528R~
