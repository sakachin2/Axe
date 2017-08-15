//*CID://+dateR~: update#= 272;                                    //~1528I~
//**********************************************************************//~1528I~
//*ListView                                                        //~1530R~
//**********************************************************************//~1528I~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1109R~//~1528I~

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.view.View;
                                                                   //~1528I~
                                                                   //~1528I~
public class AxeSpinner                                               //~1114//~1604R~
{                                                                  //~1528I~
	private static final int VIEWID_SPINNER =R.id.Spinner;         //~1601I~
	private static final int ROWID_SPINNER  =android.R.layout.simple_spinner_item;//~1604R~
                                                                   //~1529I~~
                                                                   //~1528I~
	private int rowId;                                          //~1528I~
	private Spinner  spinnerView;                                  //~1601I~
	private ArrayAdapter<String> adapterSpinner;                   //~1601I~
	private String[] spinnerData;                                  //~1604I~
	private AxeSpinnerI listener;                                  //~1730I~
                                                                   //~1528I~
//*****************                                                //~1528I~
    public AxeSpinner(int PdialogId,ViewGroup PlayoutView,String[] PspinnerData)         //~1604R~
    {                                                              //~1601I~
        spinnerView=(Spinner)(PlayoutView.findViewById(VIEWID_SPINNER));//~1601I~
        rowId=ROWID_SPINNER; 
        spinnerData=PspinnerData;//~1604R~
        initSpinner();                                             //~1601I~
    }                                                              //~1601I~
//*****************                                                //~1601I~
    public static AxeSpinner create(int PdialogId,ViewGroup PlayoutView,String[] PspinnerData)//~1604R~
    {                                                              //~1601I~
                        //~1604I~
        AxeSpinner s= new AxeSpinner(PdialogId,PlayoutView,PspinnerData);              //~1604R~
        return s;                                                 //~1601I~
    }                                                              //~1601I~
//*****************                                                //~1601I~
    public void initSpinner()                                      //~1601I~
    {                                                              //~1601I~
    	setAdapterSpinner();                                       //~1601I~
        //setListener by each dialog                               //~1810I~
    }                                                              //~1601I~
//*****************                                                //~1601I~
    private void setAdapterSpinner()                               //~1601I~
    {                                                              //~1601I~
        adapterSpinner=new ArrayAdapter<String>(AxeG.context,rowId,spinnerData);//~1604I~
        spinnerView.setAdapter(adapterSpinner);                    //~1601I~ 
    }                                                              //~1601I~
                                   //~1528I~
//*********                                                        //~1601I~
    public String getSelectedItem()                                //~1604R~
    {                                                              //~1601I~                                                 //~1601I~
    	String item=(String)spinnerView.getSelectedItem();         //~1601I~
        if (Dump.Y) Dump.println("Spinnerview getCheckedItem="+item);//~1601I~
        return item;                                               //~1601I~
    }                                                              //~1601I~
//*********                                                        //~1601I~
    public int getSelectedPos()                                    //~1604R~
    {                                                              //~1601I~
    	int pos=spinnerView.getSelectedItemPosition();             //~1601I~
        if (Dump.Y) Dump.println("Spinnerview getCheckedItemPos="+pos);//~1601I~
        return pos;                                                //~1601I~
    }                                                              //~1601I~
//*********                                                        //~1601I~
    public void setSelection(int Ppos)                             //~1604R~
    {                                                              //~1601I~
        spinnerView.setSelection(Ppos);                            //~1601I~
        if (Dump.Y) Dump.println("Spinner Setselection pos="+Ppos);//~1601I~
    }//inner class                                                 //~1601I~
//*****************                                                //~1528I~
    public int getItemCount()                                      //~1528I~
    {                                                              //~1528I~
        return spinnerData.length;                                  //~1112I~//~1528I~
    }                                                              //~1528I~
//*****************                                                //~1730I~
    public void setListener(AxeSpinnerI Plistener)                 //~1730I~
    {                                                              //~1730I~
    	listener=Plistener;                                        //~1730I~
        spinnerView.setOnItemSelectedListener(                     //~1730I~
            new AdapterView.OnItemSelectedListener()               //~1730I~
                {                                                  //~1730I~
                    @Override                                      //~1730I~
                    public void onItemSelected(AdapterView<?> Pparent,View Pview,int Ppos,long Pid)//~1730I~
                    {                                              //~1730I~
                        if (Dump.Y) Dump.println("Spinner selected pos="+Ppos);//~1730I~
                        listener.onSpinnerItemSelected(Ppos);      //~1730R~
                    }                                              //~1730I~
                    @Override                                      //~1730I~
                    public void onNothingSelected(AdapterView<?> Pparent)//~1730I~
                    {                                              //~1730I~
                        if (Dump.Y) Dump.println("Spinner nothing selected");//~1730I~
                    }                                              //~1730I~
                }                                                  //~1730I~
                                        );                         //~1730I~
    }                                                              //~1730I~
//*****************                                                //+1813I~
    public void setPrompt(String Pprompt)                           //+1813I~
    {                                                              //+1813I~
        spinnerView.setPrompt(Pprompt);                            //+1813I~
    }                                                              //+1813I~
}//class                                                           //~1528I~
