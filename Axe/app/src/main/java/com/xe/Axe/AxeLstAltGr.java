//*CID://+dateR~: update#= 285;                                    //~1528I~
//**********************************************************************//~1528I~
//*ListView                                                        //~1530R~
//**********************************************************************//~1528I~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1109R~//~1528I~

import android.view.ViewGroup;
                                                                   //~1528I~
                                                                   //~1528I~
public class AxeLstAltGr extends AxeList                           //~1612I~
{                                                                  //~1528I~
	private static final int ROWID_ALTGR    =R.layout.listrowshiftkey;//~1612R~
//*****************                                                //~1612I~
    public AxeLstAltGr(int PdialogId,ViewGroup PlayoutView,int Pmenuid,int Prowid)//~1612I~
    {                                                              //~1528I~
		super(PdialogId,PlayoutView,Pmenuid,Prowid,true/*editableRow*/,true/*clickable*/);               //~1612I~//+1B02R~
    }                                                              //~1528I~
//*****************                                                //~1612R~
    public static AxeLstAltGr setupListView(int PdialogId,ViewGroup PlayoutView)//~1612R~
    {                                                              //~1612R~
        AxeLstAltGr al=new AxeLstAltGr(PdialogId,PlayoutView,0/*Shift menuId*/,ROWID_ALTGR);//~1612I~
		return al;                                                 //~1612R~
    }                                                              //~1612R~
//*****************                                                //~1612I~
    @Override                                                      //~1612I~
    public void setListViewData()                                  //~1612I~
    {                                                              //~1612I~
		AxeG.axeKey.setListViewData(this);	//fill arraylist for AltGr//~1612I~
    }                                                              //~1612I~
//*****************                                                //~1612I~
                                                  //~1613I~
    public void add(int Plower,int Pupper)                         //~1613R~
    {                                                              //~1611I~
    	ListData linedata=new ListData(Plower,Pupper);             //~1613R~
        if (Dump.Y) Dump.println("AxeList addAltGr low="+Integer.toHexString(Plower)+",upper="+Integer.toHexString(Pupper));//~1612R~
    	add(linedata);                                             //~1611I~
    }                                                              //~1611I~
//******************                                               //~1612I~
    @Override                                                      //~1612I~
    public void updateTbl(int[] Pnewtbl)                           //~1612I~
    {                                                              //~1612I~
    	AxeG.axeKey.updateAltGr(Pnewtbl);                          //~1612R~
    }                                                              //~1612I~
//******************                                               //~1612I~
    @Override                                                      //~1612I~
    public int chkInputValue(int Ppos,String Pvalue,boolean Ptoast)         //~1613R~//~1A29R~
    {                                                              //~1612I~
    	int rc;                                                    //~1613I~
		rc= AxeKey.chkInputAltGr(Pvalue);                     //~1613R~
        if (Ptoast && rc==AxeKey.KEYVALUE_ERR)               //~1613I~
        {                                                          //~1613I~
			int msgid=R.string.Err_InputAltGrData;                 //~1613I~
        	Utils.showToast(msgid);                                //~1613I~
        }                                                          //~1613I~
        return rc;                                                 //~1613I~
    }                                                              //~1612I~
//******************                                               //~1531I~
    @Override                                                      //~1612I~
    public String getKeyname(int Ppos,int Pfldid,int Pkey,String Perr)     //~1612R~//~1A29R~
    {                                                              //~1612R~
		return AxeKey.keyToString(Pkey,Perr);                      //~1612R~
    }                                                              //~1612I~
}//class                                                           //~1528I~
