//*CID://+vc1xR~: update#= 289;                                    //+vc1xR~
//**********************************************************************//+vc1xI~
//vc1x 2020/07/08 member variable is initialized when defualt constructor defined(not non default constructor is define)//+vc1xI~
//**********************************************************************//~1528I~
//*ListView                                                        //~1530R~
//**********************************************************************//~1528I~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1109R~//~1528I~

import android.view.ViewGroup;
                                                                   //~1528I~
public class AxeLstShift extends AxeList                              //~1612R~
{                                                                  //~1528I~
	private static final int ROWID_SHIFTKEY =R.layout.listrowshiftkey;//~1530M~
//*****************                                                //~1612I~
    public AxeLstShift()                                           //+vc1xI~
    {                                                              //+vc1xI~
        if (Dump.Y) Dump.println("AxeLstShift.defaultConstructor");//+vc1xI~
    }                                                              //+vc1xI~
//    public AxeLstShift(int PdialogId,ViewGroup PlayoutView,int Pmenuid,int Prowid)//~1817R~//+vc1xR~
//    {                                                              //~1612I~//+vc1xR~
////        super(PdialogId,PlayoutView,Pmenuid,Prowid,true/*editableRow*/);//~1B02R~//+vc1xR~
//        super(PdialogId,PlayoutView,Pmenuid,Prowid,true/*editableRow*/,true/*clickable*/);//~1B02I~//+vc1xR~
//    }                                                              //~1612I~//+vc1xR~
    public static AxeLstShift newInstance(int PdialogId,ViewGroup PlayoutView,int Pmenuid,int Prowid)//+vc1xI~
    {                                                              //+vc1xI~
        if (Dump.Y) Dump.println("AxeLstShift.newInstance");       //+vc1xI~
        AxeLstShift al=new AxeLstShift();                          //+vc1xI~
        al.initInstance(PdialogId,PlayoutView,Pmenuid,Prowid,true/*editableRow*/,true/*clickable*/);//+vc1xI~
        return al;                                                 //+vc1xI~
    }                                                              //+vc1xI~
//*****************                                                //~1612R~
//  public static AxeLstShift setupListView(int PdialogId,ViewGroup PlayoutView,int Pmenuid)//~1817R~
    public static AxeLstShift setupListView(int PdialogId,ViewGroup PlayoutView)//~1817I~
    {                                                              //~1612R~
        if (Dump.Y) Dump.println("AxeLstShift.setupListView");     //+vc1xI~
//      AxeLstShift al=new AxeLstShift(PdialogId,PlayoutView,0/*Pmenuid*/,ROWID_SHIFTKEY);//~1817R~//+vc1xR~
        AxeLstShift al=newInstance(PdialogId,PlayoutView,0/*Pmenuid*/,ROWID_SHIFTKEY);//+vc1xI~
        return al;                                                 //~1612I~
    }                                                              //~1612R~
//*****************                                                //~1612I~
    @Override                                                      //~1612I~
    public void setListViewData()                                  //~1612I~
    {                                                              //~1612I~
//        if (menuId==AxeKey.SHIFT_RESET101)                       //~1818R~
//        {                                                        //~1818R~
//            AxeG.axeKey.setDefault_Shiftkey(false);              //~1818R~
//        }                                                        //~1818R~
//        else                                                     //~1818R~
//        if (menuId==AxeKey.SHIFT_RESET106)                       //~1818R~
//        {                                                        //~1818R~
//            AxeG.axeKey.setDefault_Shiftkey(true);               //~1818R~
//        }                                                        //~1818R~
		AxeG.axeKey.setListViewData(this);	//fill arraylist for Shortcut//~1612I~
    }                                                              //~1612I~
//*****************                                                //~1818I~
    public void reset2Default(int PbuttonId)                       //~1818I~
    {                                                              //~1818I~
    	boolean isKbd106=false;                                    //~1818I~
    	switch(PbuttonId)                                          //~1818I~
        {                                                          //~1818I~
        case R.id.KBD101:                                          //~1818I~
        	break;                                                 //~1818I~
        case R.id.KBD106:                                          //~1818I~
        	isKbd106=true;                                        //~1818I~
        	break;                                                 //~1818I~
        default:                                                    //~1818I~
        	return;                                                //~1818I~
        }                                                          //~1818I~
		int [] newtbl=AxeG.axeKey.getDefault_Shiftkey(isKbd106);         //~1818I~
        updateData(newtbl);                                //~1818I~
    }                                                              //~1818I~
//*****************                                                //~1612I~
                                                   //~1613I~
    public void add(int Plower,int Pupper)                         //~1613R~
    {                                                              //~1612I~
    	ListData linedata=new ListData(Plower,Pupper);             //~1612I~
        if (Dump.Y) Dump.println("AxeList addShiftkey low="+Integer.toHexString(Plower)+",upper="+Integer.toHexString(Pupper));//~1612I~
//      if (menuId==AxeKey.SHIFT_RESET101||menuId==AxeKey.SHIFT_RESET106)//~1818R~
//      	linedata.isUpdated=true;	//writeback                //~1818R~
    	add(linedata);                                             //~1612I~
    }                                                              //~1612I~
//******************                                               //~1612I~
    @Override                                                      //~1612I~
    public void updateTbl(int[] Pnewtbl)                           //~1612I~
    {                                                              //~1612I~
    	AxeG.axeKey.updateShiftkey(Pnewtbl);                       //~1612R~
    }                                                              //~1612I~
//******************                                               //~1612I~
    @Override                                                      //~1612I~
    public int chkInputValue(int Ppos,String Pvalue,boolean Ptoast)         //~1613I~//~1A29R~
    {                                                              //~1612I~
    	int rc;                                                    //~1613I~
		rc=AxeKey.chkInputShiftkey(Pvalue);                   //~1613R~
        if (Ptoast && rc==AxeKey.KEYVALUE_ERR)               //~1613I~
        {                                                          //~1613I~
	        int msgid=R.string.Err_InputShiftkeyData;              //~1613I~
    	    Utils.showToast(msgid);                                //~1613I~
        }                                                          //~1613I~
        return rc;                                                 //~1613I~
    }                                                              //~1612I~
//**************************************                           //~1612I~
    public String getKeyname(int Ppos,int Pfldid,int Pkey,String Perr)     //~1612I~//~1A29R~
    {                                                              //~1612I~
		return AxeKey.keyToString(Pkey,Perr);                      //~1612I~
    }                                                              //~1612I~
}//class                                                           //~1528I~
