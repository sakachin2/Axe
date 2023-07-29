//*CID://+vc5bR~: update#= 135;                                    //+vc5bR~
//**********************************************************************//~1107I~
//vc5b 2023/06/29 (Bug)titlebar bottome sometime invalid when rotated. use 1st time value//+vc5bI~
//vc4r 2023/04/14 rename using SAF                                 //~vc4rI~
//vc4q 2023/04/01 support shared storage using SAF(StorageAccessFramework)//~vc4qI~
//vc2Q 2020/09/08 change font size by pinch action                 //~vc2QI~
//vc10 2020/06/14 update Dump to write to terminal(copy from Ahsv) //~vc10I~
//**********************************************************************//~1107I~
package com.ahsv;                                         //~1107R~  //~1108R~//~1109R~//~@@@@R~//~vc10R~

import java.util.Properties;                                       //~vc10I~

import com.ahsv.utils.USAF;
import com.ahsv.utils.USAF2;                                       //~vc4rI~
import com.xe.Axe.Axe;
import com.xe.Axe.AxeG;                                            //~vc10R~
import com.xe.Axe.R;                                               //~vc10I~
import com.ahsv.gui.CommonListener;                                //~vc10I~
//**********************************************************************//~1107I~
public class AG	extends AxeG                                       //~vc10R~
{                                                                  //~0914I~
    public static final int       listViewRowId=R.layout.textrowlist;//~vc10R~
    public static com.ForDeprecated.ProgDlg progDlg26;             //~vc10I~
    public static String dirSep;                                   //~vc10I~
	public  static boolean swSDAvailable=true;                     //~vc10I~
	public  static String dirSD;                                   //~vc10I~
    public static String    appNameE;	//by alphabet              //~vc10I~
    public static String helpFileSuffix;                           //~vc10I~
    public static int       scrWidth,scrHeight;                    //~vc10I~
    public static int       scrWidthReal,scrHeightReal;            //~vc10I~
	public static float dip2pix;                                   //~vc10I~
	public static float sp2pix;                                    //~vc10I~
    public static boolean   portrait;                              //~vc10I~
    public static int       scrNavigationbarRightWidth;            //~vc10I~
    public static boolean   swSmallDevice;      //portrait screen width<800pixel//~vc10I~
    public static double    scaleSmallDevice;   //portrait screen width/800pixel//~vc10I~
    public static int       status=0;                              //~vc10I~
    public static final int STATUS_MAINFRAME_OPEN=1;               //~vc10I~
    public static final int STATUS_STOPFINISH=9;                   //~vc10I~
    public static CommonListener.CommonListenerI aCommonListenerI; //~vc10I~
    public static USAF aUSAF;                                      //~vc4qR~
    public static USAF2 aUSAF2;                                    //~vc4rI~
    public static int titleBarTop,titleBarBottom;                  //+vc5bI~
//**********************************                               //~1211I~
	public AG(Axe Paxe)                                           //~1211I~//~@@@@R~//~vc10R~
    {                                                              //~1211I~
        init(Paxe); //AxeG                                             //~vc10I~
		Properties p=System.getProperties();                       //~vc10I~
		dirSep=p.getProperty("file.separator");                    //~vc10I~
		appName=context.getText(R.string.app_name).toString();     //~vc10I~
//      helpFileSuffix=isLangJP ? "_ja" : "";                      //~vc10I~//~vc2QR~
        setDebugHelpLang();                                        //~vc2QI~
        aUSAF=new USAF();                                          //~vc4qI~
    }                                                              //~1211I~
    //*********************************                            //~vc2QI~
    public static void setDebugHelpLang()                          //~vc2QI~
    {                                                              //~vc2QI~
        if ((AxeG.optDebug & AxeG.DEBUGO_HELP_REVERSELANG)!=0)           //~vc2QI~
	        helpFileSuffix=!isLangJP ? "_ja" : "";                 //~vc2QI~
        else                                                       //~vc2QI~
	        helpFileSuffix=isLangJP ? "_ja" : "";                  //~vc2QI~
    }                                                              //~vc2QI~
}//class AG                                                //~1211R~//~@@@@R~//~vc10R~
