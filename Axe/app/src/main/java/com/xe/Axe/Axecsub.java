//*CID://+vaiqR~: update#=   99;                                   //+vaiqR~
//*****************************************************************//+vaiqI~
//vaiq:130606 Axe:issue initial setup Alert(Be paitient)           //+vaiqI~
//*****************************************************************//+vaiqI~
package com.xe.Axe;                                                //~@@@@I~

//*****************************************************************//~1606I~
                                                                   //~1606I~
public class Axecsub                                               //~1622R~
	implements AxeAlertI                                           //~1A22I~
{                                                                  //~1606I~
    private static final int  GTK_RESPONSE_OK     = -5;                                    //~v@@@I~//~1A22I~
    private static final int  GTK_RESPONSE_CANCEL = -6;                                    //~v@@@I~//~1A22I~
    private static final int  GTK_RESPONSE_CLOSE  = -7;                                    //~v@@@I~//~1A22I~
    private static final int  GTK_RESPONSE_YES    = -8;                                    //~v@@@I~//~1A22I~
    private static final int  GTK_RESPONSE_NO     = -9;                                    //~v@@@I~//~1A22I~
//    private static final int  GTK_RESPONSE_APPLY  = -10;                                   //~v@@@I~//~1A22I~
//    private static final int  GTK_RESPONSE_HELP   = -11;                                    //~v@@@I~//~1A22I~
                                                                   //~1A22I~
	public static final int IDOK=GTK_RESPONSE_OK;                  //~1A22I~
	public static final int IDYES=GTK_RESPONSE_YES;                //~1A22I~
	public static final int IDNO=GTK_RESPONSE_NO;                  //~1A22I~
	public static final int IDCLOSE=GTK_RESPONSE_CLOSE;            //~1A22I~
	public static final int IDCANCEL=GTK_RESPONSE_CANCEL;          //~1A22I~
                                                                   //~1A22I~
    private static enum GtkButtonsType                                   //~1A22I~
    	{                                                          //~1A22I~
          GTK_BUTTONS_NONE,                                        //~1A22I~
          GTK_BUTTONS_OK,                                          //~1A22I~
          GTK_BUTTONS_CLOSE,                                       //~1A22I~
          GTK_BUTTONS_CANCEL,                                      //~1A22I~
          GTK_BUTTONS_YES_NO,                                      //~1A22I~
          GTK_BUTTONS_OK_CANCEL                                    //~1A22I~
        }                                                         //~va15I~//~1A22I~
                                                                   //~1A22I~
	public static int MB_OK=GtkButtonsType.GTK_BUTTONS_OK.ordinal();                  //~1A22I~
	public static final int MB_OKCANCEL=GtkButtonsType.GTK_BUTTONS_OK_CANCEL.ordinal();     //~1A22I~
	public static final int MB_YESNOCANCEL=101;	//xxecsub.h    //~1A22I~
                                                                   //~1A22I~
	private static int callbackId;                                 //~1A22I~
	private static final int CB_UMSGBOX_YNC=1;                       //~1A22I~
	private static String dndCmd;                                  //~1A22I~
///////////////////////////////////////////////////////////////////////////////~1606I~
    public static int umsgbox(String Ppmsg,int Pflag)                                 //~v685I~//~1A22R~
    {                                                                  //~2901I~//~1A22I~
        int flag;                                                    //~v685I~//~1A22I~
        int rc=0;                                                        //~v66AI~//~1A22I~
    //********************************                                 //~2901I~//~1A22I~
        if (Dump.Y) Dump.println("axecsub:umsgbox msg="+Ppmsg+",flag="+Integer.toHexString(Pflag));//~1A22I~
        callbackId=0;                                              //~1A22M~
        dndCmd=null;                                               //~1A22I~
        if (Pflag==MB_YESNOCANCEL)	//curent user is xxexei3:dndcopyfile//~1A22I~
        {                                                          //~1A22I~
    		 flag=AxeAlert.BUTTON_POSITIVE/*OK*/|AxeAlert.BUTTON_NEGATIVE/*No*/|AxeAlert.BUTTON_NUTRAL/*Cancel*/;//~1A22I~
            callbackId=CB_UMSGBOX_YNC;                             //~1A22I~
            int pos=Ppmsg.indexOf(",");                            //~1A22I~
            if (pos>0)                                             //~1A22R~
            {                                                      //~1A22I~
            	dndCmd=Ppmsg.substring(0,pos-1).trim();            //~1A22R~
                if (dndCmd.startsWith("XCOPY"))                     //~1A22I~
                	dndCmd=dndCmd.substring(5).trim();             //~1A22I~
            }                                                      //~1A22I~
            else                                                   //~1A22I~
            	dndCmd=Ppmsg;                                      //~1A22I~
        }                                                          //~1A22I~
        else                                                           //~v685I~//~1A22I~
        if (Pflag==MB_OKCANCEL)	//currentry no user                                        //~v68jR~//~1A22I~
        {                                                          //~1A22I~
    		flag=AxeAlert.BUTTON_POSITIVE/*OK*/|AxeAlert.BUTTON_NUTRAL/*Cancel*/;//~1A22I~
        }                                                          //~1A22I~
        else        //MB_OK                                                   //~v68jI~//~1A22I~
        {                                                          //~1A22I~
    		flag=AxeAlert.BUTTON_CLOSE;                        //~1A22I~
        }                                                          //~1A22I~
        AxeAlert.simpleAlertDialog(AxeG.axecsub/*callback*/,null/*title*/,Ppmsg,flag);//CB:alertButtonAction//~1A22I~
        if (Pflag==MB_YESNOCANCEL)                                     //~v685I~//~1A22I~
        {                                                              //~v68jI~//~1A22I~
        	rc=IDNO;	//modal dialog is not supported;return NO(skip this file) anyway,callback do copy when yes replyed//~1A22R~
        }                                                              //~v68jI~//~1A22I~
        return rc;                                                     //~v66AR~//~1A22I~
    }                                                                  //~2901I~//~1A22I~
//**************************************************************** //~1808I~
    public static void umsgbox2(String Pmsg,int Pmsgtype,int Pbtntype)//~1801I~
    {                                                              //~1620I~
        AxeG.axeMenu.confirmStop();                              //~1622I~//~1802R~
    }                                                              //~1622R~
    public static void umsgbox2Reply(int Pbuttonid)                //~1802I~
    {                                                              //~1802I~
        if (Pbuttonid==AxeAlert.BUTTON_POSITIVE)                   //~1802I~
        	AxeJNI.terminatexe();                                  //~1802I~
    }                                                              //~1802I~
//**************************************************************** //~v55ZI~//~1808I~
//setup timer callback                                             //~vXXEI~//~1808I~
//interval by miliseconds                                          //~vXXEI~//~1808I~
//*rc:ret timer handler id for killtimer                           //~vXXER~//~1808I~
//**************************************************************** //~v55ZI~//~1808I~
    public static int usettimer(int Ptimerid,int Pintvlms,long Pcallback)         //~vXXER~//~1808I~
    {                                                                  //~v55ZI~//~1808I~
    //********************************                                 //~v55ZI~//~1808I~
        if (Dump.Y) Dump.println("axecsub:usettimer id="+Integer.toHexString(Ptimerid)+",mills="+Pintvlms);//~1808I~
        AxeTimer handler=new AxeTimer(Ptimerid,Pintvlms,Pcallback);//~1808I~
        int seqno=handler.start();                                 //~1808R~
        return seqno;                                              //~1808R~
    }//usettimer                                                       //~v55ZI~//~1808I~
//****************************************************             //~1A22I~
//*AxeAlertI:callback                                              //~1A22I~
//****************************************************             //~1A22I~
	@Override                                                      //~1A22I~
    public int alertButtonAction(int Pbuttonid, int Pitempos)      //~1A22I~
    {                                                              //~1A22I~
        if (Dump.Y) Dump.println("Axecsub:alertdialog callback id="+Integer.toHexString(callbackId)+",buttonid="+Integer.toHexString(Pbuttonid));//~1A22I~
        if (callbackId==CB_UMSGBOX_YNC)                            //~1A22I~
        {                                                          //~1A22I~
        	if (Pbuttonid==AxeAlert.BUTTON_POSITIVE)                     //~1A22I~
            {                                                      //~1A22I~
        		AxeJNI.dragdropRepCopy(dndCmd);                    //~1A22R~
                dndCmd=null;                                       //~1A22I~
            }                                                      //~1A22I~
        }                                                          //~1A22I~
		return 0;                                                  //~1A22I~
	}                                                              //~1A22I~
//****************************************************             //+vaiqI~
	@Override
	public int alertOnShow(AxeAlert Paxealert,boolean Pdismiss) {
		return 0;
	}
}//class                                                                  //~1528R~//~1A22R~
