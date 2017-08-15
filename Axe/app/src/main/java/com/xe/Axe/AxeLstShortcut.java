//*CID://+dateR~: update#= 292;                                    //~1528I~
//**********************************************************************//~1528I~
//*ListView                                                        //~1530R~
//**********************************************************************//~1528I~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1109R~//~1528I~

import android.view.ViewGroup;
                                                                   //~1528I~
                                                                   //~1528I~
public class AxeLstShortcut extends AxeList                        //~1612R~
{                                                                  //~1528I~
	private static final int ROWID_SHORTCUT =R.layout.listrowshortcut;//~1529I~
                                                                   //~1529I~
//*****************                                                //~1612I~
    public AxeLstShortcut(int PdialogId,ViewGroup PlayoutView,int Pmenuid,int Prowid)                                               //~1112I~//~1612I~
    {                                                              //~1612I~
		super(PdialogId,PlayoutView,Pmenuid,Prowid,false/*editableRow*/);                       //~1612I~
    }                                                              //~1612I~
//*****************                                                //~1528I~
    public static AxeLstShortcut setupListView(int PdialogId,ViewGroup PlayoutView)//~1612R~
    {                                                              //~1528I~
        AxeLstShortcut al=new AxeLstShortcut(PdialogId,PlayoutView,0/*Shift menuId*/,ROWID_SHORTCUT);//~1612R~
        return al;                                                 //~1528I~
    }                                                              //~1528I~
//*****************                                                //~1612I~
    @Override                                                      //~1612I~
    public void setListViewData()                                  //~1612I~
    {                                                              //~1612I~
		AxeG.axeKey.setListViewData(this);	//fill arraylist for Shortcut//~1612R~
    }                                                              //~1612I~
//*callback from AxeKey ****************                           //~1612I~
                                                    //~1613I~
    public void add(int Psource,int Ptarget)                       //~1613R~
    {                                                              //~1612I~
    	ListData linedata=new ListData(Psource,Ptarget);           //~1612I~
        if (Dump.Y) Dump.println("AxeList addShortcut src="+Integer.toHexString(Psource)+",tgt="+Integer.toHexString(Ptarget));//~1612I~
    	super.add(linedata);                                       //~1612I~
    }                                                              //~1612I~
//    //****************                                           //~1816R~
//    @Override                                                    //~1816R~
//    public void addClickListener(int Ppos,EditText PeditText)    //~1816R~
//    {                                                            //~1816R~
////      PeditText.setEditable(false);                            //~1816R~
////      BufferType editable=BufferType.NORMAL;  //editable:false //~1816R~
////      PeditText.setText(Ptext,editable);  //set editable:false by xml//~1816R~
////      PeditText.setClickable(true);//avoid SoftKeyboard popup  //~1816R~
////        ClickListener l=new ClickListener(Ppos);               //~1816R~
////        PeditText.setOnClickListener(l);                       //~1816R~
////        if (Dump.Y)Dump.println("addCliskListener="+l.toString()+",edittext="+PeditText.toString());//~1816R~
//    }                                                            //~1816R~
    @Override                                                      //~1809I~
    public void onItemClicked(int Ppos,ListData Pitem)               //~1816R~
    {                                                              //~1809I~
        if (Dump.Y)Dump.println("AxeLstShortcut onItemClickr pos="+Ppos);//~1816R~
//      if (Ppos!=getSelectedPosListView())                        //~1817R~
//      	select(Ppos);                                          //~1817R~
    	AxeDlgExtkey.show(Pitem,this);                             //~1817I~
    }                                                              //~1809I~
//******************                                               //~1612I~
    @Override                                                      //~1612I~
    public void updateTbl(int[] Pnewtbl)                           //~1612I~
    {                                                              //~1612I~
    	AxeG.axeKey.updateShortcut(Pnewtbl);                       //~1612R~
    }                                                              //~1612I~
//******************                                               //~1612I~
    @Override                                                      //~1612I~
    public int chkInputValue(int Ppos,String Pvalue,boolean Ptoast)         //~1613I~//~1A29R~
    {                                                              //~1612I~
    	int rc;                                                    //~1613I~
		rc=AxeKey.chkInputShortcut(Pvalue);                   //~1613R~
        if (Ptoast && rc==AxeKey.KEYVALUE_ERR)               //~1613I~
        {                                                          //~1613I~
	    	int msgid=R.string.Err_InputShortcutData;              //~1613I~
    	    Utils.showToast(msgid);                                //~1613I~
        }                                                          //~1613I~
        return rc;                                                 //~1613I~
    }                                                              //~1612I~
//******************                                               //~1531I~
	@Override                                                      //~1612I~
    public String getKeyname(int Ppos,int Pfldid,int Pkey,String Perr)//~1602R~//+1A29R~
    {                                                              //~1531I~
        if (Pfldid==1)                                             //~1612R~
        	return AxeKey.keyToString(Pkey,Perr);                  //~1611I~
        return AxeKey.extendedkeyToString(Pkey,Perr);              //~1531R~
    }                                                              //~1531I~
}//class                                                           //~1528I~
