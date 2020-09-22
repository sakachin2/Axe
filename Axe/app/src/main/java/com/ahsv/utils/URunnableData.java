//*CID://+vc09R~: update#= 196;                                    //~vc09R~
//**********************************************************************
//vc09 2020/06/14 (Ahsv:1Ah0)for Android9-api28(PlayStore requires),deprected. ProgressDialog at Android8-api26//~vc09I~
//1Ah8 2020/06/01 detect wifip2p discovery stopped(available from API16 android4.1 JellyBean)//~1Ah8I~
//1Ah0 2020/05/30 for Android9-api28(PlayStore requires),deprected. ProgressDialog at Android8-api26//~1Ah0I~
//1A6t 2015/02/18 (BUG)simpleProgress Dialog thread err exception  //~1A6tI~
//**********************************************************************
package com.ahsv.utils;                                                  //~1A6tR~//~vc09R~

import com.ForDeprecated.ProgDlgData;
import com.ForDeprecated.ProgDlgI;                                 //+vc09I~

//import android.app.ProgressDialog;                               //~1Ah0R~
                                                                   //~1A6tI~
public class URunnableData                                         //~1A6tR~
{
//  public ProgressDialog progressDialog;                                           //~1A6tI~//~1Ah0R~
    public ProgDlgData progDlgData;                                //~1Ah0R~
    public ProgDlgI progDlgI;	//callback onCancelProgDlg         //~1Ah8I~
    public URunnableData()                                         //~1A6tR~
    {
    }
//    public URunnableData(ProgressDialog Pdlg)                      //~1A6tI~//~1Ah0R~
//    {                                                              //~1A6tI~//~1Ah0R~
//        progressDialog=Pdlg;    //                                           //~1A6tI~//~1Ah0R~
//    }                                                              //~1A6tI~//~1Ah0R~
}//class
