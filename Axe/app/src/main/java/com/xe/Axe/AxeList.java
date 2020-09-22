//*CID://+vc1xR~: update#= 437;                                    //~vaxbR~//~vc1sR~//~vc1wR~//~vc1xR~
//**********************************************************************//~1528I~
//*ListView                                                        //~1530R~
//**********************************************************************//~vaxbI~
//vc1x 2020/07/08 member variable is initialized when defualt constructor defined(not non default constructor is define)//~vc1xI~
//vc1w 2020/07/06 AxeKbd updatelog extends KbdLayoutHW             //~vc1wI~
//vc1s 2020/06/29 hardKeyboard layout;set default                  //~vc1sI~
//vaxb:140628 (Axe)implement filedialog                            //~vaxbI~
//**********************************************************************//~1528I~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1109R~//~1528I~

import java.util.ArrayList;
import java.util.Arrays;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
//import android.widget.Spinner;
import android.widget.TextView;

import com.ahsv.utils.Utils;
                                                                   //~1528I~
                                                                   //~1528I~
class ListData                                                     //~1529R~
{                                                                  //~1528I~
	public int key1,key2,status;                                   //~1529R~
	public String str1="",str2="";                                       //~vaxbI~//~vc1sR~
	public String str3="",str4="";                                 //~vc1sR~
	public String str5="",str6="";                                 //~vc1wR~
    public boolean isUpdated;                                      //~1530R~
    public boolean isEditable;                                  //~1A29R~//~1A30R~
//    public View dataView;                                        //~@@@@R~
    public TextView textView1;                                     //~@@@@I~
    public TextView textView2;                                     //~1816I~
    public TextView textView3;                                     //~vc1sI~
    public TextView textView4;                                     //~vc1sI~
    public TextView textView5;                                     //~vc1wI~
    public TextView textView6;                                     //~vc1wI~
    public int pos;                                                //~@@@@I~
    public boolean dirline;                                        //~vaxbI~
    int col,colerr;                                                //~vc1sR~
    int posCursor;                                                 //~vc1sI~
    int intValue;                                                  //~vc1sI~
    public ListData(int Pkey1,int Pkey2)                           //~1529R~
    {                                                              //~1528I~
    	key1=Pkey1;                                                //~1529R~
        key2=Pkey2;                                                //~1529R~
    }                                                              //~1528I~
    public ListData(int Pkey1,int Pkey2,boolean Peditable)	//for TermBtn User key//~1A29R~//~1A30R~
    {                                                              //~1A29I~
    	key1=Pkey1;                                                //~1A29I~
        key2=Pkey2;                                                //~1A29I~
        isEditable=Peditable;	                               //~1A29R~//~1A30R~
    }                                                              //~1A29I~
    public ListData(String Pstr1,String Pstr2,boolean Pdir)        //~vaxbI~
    {                                                              //~vaxbI~
    	str1=Pstr1;                                                //~vaxbI~
        str2=Pstr2;                                                //~vaxbI~
        dirline=Pdir;                                              //~vaxbI~
    }                                                              //~vaxbI~
    public ListData(int Pkey,String Pstr1,String Pstr2,String Pstr3,String Pstr4)//~vc1sR~
    {                                                              //~vc1sI~
    	key1=Pkey;                                                 //~vc1sI~
        str1=Pstr1; str2=Pstr2; str3=Pstr3; str4=Pstr4;            //~vc1sR~
    }                                                              //~vc1sI~
    public ListData(int Pkey,String Pstr1,String Pstr2,String Pstr3,String Pstr4,String Pstr5)//~vc1wI~
    {                                                              //~vc1wI~
    	key1=Pkey;                                                 //~vc1wI~
        str1=Pstr1; str2=Pstr2; str3=Pstr3; str4=Pstr4;            //~vc1wI~
        str5=Pstr5;                                                //~vc1wI~
    }                                                              //~vc1wI~
    public ListData(int Pkey,String Pstr1,String Pstr2,String Pstr3,String Pstr4,String Pstr5,String Pstr6)//~vc1wI~
    {                                                              //~vc1wI~
    	key1=Pkey;                                                 //~vc1wI~
        str1=Pstr1; str2=Pstr2; str3=Pstr3; str4=Pstr4;            //~vc1wI~
        str5=Pstr5; str6=Pstr6;                                    //~vc1wI~
    }                                                              //~vc1wI~
    public String toString()                                       //~vc1sI~
    {                                                              //~vc1sI~
       return "ListData key1="+Integer.toHexString(key1)+",pos="+pos+",col="+col+",colerr="+colerr+",posCursor="+posCursor+",str="+str1+","+str2+","+str3+","+str4+",str5="+str5+","+str6+",view="+Utils.toString(textView1)+","+Utils.toString(textView2)+","+Utils.toString(textView3)+","+Utils.toString(textView4)+","+Utils.toString(textView5)+","+Utils.toString(textView6);//~vc1sR~//~vc1wR~
    }                                                              //~vc1sI~
    public void updateText(String Ptxt)                            //~vc1sI~
    {                                                              //~vc1sI~
        if (col==1)                                                //~vc1sI~
            str2=Ptxt;                                             //~vc1sI~
        else                                                       //~vc1sI~
        if (col==2)                                                //~vc1sI~
            str3=Ptxt;                                             //~vc1sI~
        else                                                       //~vc1sI~
        if (col==3)                                                //~vc1wI~
            str4=Ptxt;                                             //~vc1sI~
        else                                                       //~vc1wI~
        if (col==4)                                                //~vc1wI~
            str5=Ptxt;                                             //~vc1wI~
        else                                                       //~vc1wI~
            str6=Ptxt;                                             //~vc1wI~
        if (Dump.Y) Dump.println("AxeList.ListData.updateText col="+col+",text="+Ptxt);//~vc1sI~
    }                                                              //~vc1sI~
    public void setColErr(int Pcol)                                //~vc1sI~
    {                                                              //~vc1sI~
    	colerr |= (1<<Pcol);                                       //~vc1sR~
    }                                                              //~vc1sI~
    public void resetColErr(int Pcol)                              //~vc1sI~
    {                                                              //~vc1sI~
        colerr &= ~(1<<Pcol);                                      //~vc1sR~
    }                                                              //~vc1sI~
    public boolean isColErr(int Pcol)                                 //~vc1sI~
    {                                                              //~vc1sI~
        return (colerr & (1<<Pcol))!=0;                            //~vc1sR~
    }                                                              //~vc1sI~
    public String getColStr()                                      //~vc1sI~
    {                                                              //~vc1sI~
    	String rc="";                                              //~vc1sI~
        if (col==1)                                                //~vc1sI~
//  		rc=str1;                                               //~vc1sI~//~vc1wR~
    		rc=str2;                                               //~vc1wI~
        else                                                       //~vc1sI~
        if (col==2)                                                //~vc1sI~
			rc=str3;                                               //~vc1sI~
        else                                                       //~vc1sI~
        if (col==3)                                                //~vc1sI~
			rc=str4;                                               //~vc1sI~
        else                                                       //~vc1sI~
        if (col==4)                                                //~vc1wI~
			rc=str5;                                               //~vc1wI~
        else                                                       //~vc1wI~
        if (col==5)                                                //~vc1wI~
			rc=str6;                                               //~vc1wI~
        else                                                       //~vc1wI~
			rc=str1;                                               //~vc1sI~
        return rc;
    }                                                              //~vc1sI~
}                                                                  //~1528I~
class ShortcutData extends ListData                                //~1529I~
{ 
	public ShortcutData(int Psource,int Ptarget)
	{
		super(Psource,Ptarget);//~1529I~
	}
}                                                                  //~1529I~
class ShiftkeyData extends ListData                                //~1529R~
{	
	public ShiftkeyData(int Plowervalue,int Puppervalue)
	{
		super(Plowervalue,Puppervalue);//~1529I~
	}                                                                  //~1528I~
}                                                                  //~1528I~
//*************************************************                //~@@@@I~
//*ListView convertView Tag data                                   //~@@@@I~
//*************************************************                //~@@@@I~
class ListViewTag                                                  //~@@@@I~
{                                                                  //~@@@@I~
    public TextView view1,view2;                                   //~@@@@I~
    public TextView view3,view4;                                   //~vc1sI~
    public TextView view5,view6;                                   //~vc1wR~
    public int pos,cols;                                           //~vaxbI~
//  public ListViewTag(TextView Pview1,TextView Pview2)            //~vaxbR~
    public ListViewTag(TextView Pview1,TextView Pview2,int Ppos,int Pcols)//~vaxbI~
    {                                                              //~@@@@I~
        view1=Pview1;                                              //~@@@@I~
        view2=Pview2;                                              //~@@@@I~
        pos=Ppos;                                                  //~vaxbI~
        cols=Pcols;                                                //~vaxbI~
    }                                                              //~@@@@I~
    public ListViewTag(TextView Pview1,TextView Pview2,TextView Pview3,TextView Pview4,int Ppos,int Pcols)//~vc1sI~
    {                                                              //~vc1sI~
        view1=Pview1;                                              //~vc1sI~
        view2=Pview2;                                              //~vc1sI~
        view3=Pview3;                                              //~vc1sI~
        view4=Pview4;                                              //~vc1sI~
        pos=Ppos;                                                  //~vc1sI~
        cols=Pcols;                                                //~vc1sI~
    }                                                              //~vc1sI~
    public ListViewTag(TextView Pview1,TextView Pview2,TextView Pview3,TextView Pview4,TextView Pview5,int Ppos,int Pcols)//~vc1wI~
    {                                                              //~vc1wI~
        view1=Pview1;                                              //~vc1wI~
        view2=Pview2;                                              //~vc1wI~
        view3=Pview3;                                              //~vc1wI~
        view4=Pview4;                                              //~vc1wI~
        view5=Pview5;                                              //~vc1wI~
        pos=Ppos;                                                  //~vc1wI~
        cols=Pcols;                                                //~vc1wI~
    }                                                              //~vc1wI~
    public ListViewTag(TextView Pview1,TextView Pview2,TextView Pview3,TextView Pview4,TextView Pview5,TextView Pview6,int Ppos,int Pcols)//~vc1wI~
    {                                                              //~vc1wI~
        view1=Pview1;                                              //~vc1wI~
        view2=Pview2;                                              //~vc1wI~
        view3=Pview3;                                              //~vc1wI~
        view4=Pview4;                                              //~vc1wI~
        view5=Pview5;                                              //~vc1wI~
        view6=Pview6;                                              //~vc1wI~
        pos=Ppos;                                                  //~vc1wI~
        cols=Pcols;                                                //~vc1wI~
    }                                                              //~vc1wI~
    public String toString()                                       //~vc1sI~
    {                                                              //~vc1sI~
        return "ListViewTag:pos="+pos+",col="+cols+",view1="+Utils.toString(view1)+",view2="+Utils.toString(view2)+",view3="+Utils.toString(view3)+"+view4="+Utils.toString(view4)+Utils.toString(view5);//~vc1sI~//~vc1wR~
    }                                                              //~vc1sI~
}                                                                  //~@@@@I~
//*************************************************                //~@@@@I~
public class AxeList                                                  //~1114//~1528I~
{                                                                  //~1528I~
	private static final int VIEWID_LIST    =R.id.List;            //~1529R~
	private static final int VIEWID_KEY1    =R.id.Label;           //~1530R~
	private static final int VIEWID_KEY2    =R.id.Value;           //~1530R~
                                                                   //~1529I~
	protected static final int DATA_ERR       =1;                    //~1529I~//~vc1sR~
	protected static final int DATA_NORMAL    =0;                    //~1529I~//~vc1sR~
	protected static final int COLOR_BG       =Color.WHITE;          //~1530R~//~vc1sR~
	protected static final int COLOR_BG_DISABLE=0xff808080;        //~vc1sR~
	protected static final int COLOR_BG_ENABLE6=0xffe8e8e8;        //~vc1wI~
	protected static final int COLOR_BG_ENABLE5=0xffe0e0e0;        //~vc1wI~
	protected static final int COLOR_BG_ENABLE4=0xffe8e8e8;        //~vc1sR~
	protected static final int COLOR_BG_ENABLE3=0xfff0f0f0;        //~vc1sR~
	protected static final int COLOR_FG       =Color.BLACK;          //~1530I~//~vc1sR~
	public  static final int COLOR_FGERR    =0xfff00000; //red     //~1602R~
	public  static final int COLOR_BGFOCUS =0xffffc070; //orange   //~vaxbI~
	private static final String NOT_DEFINED ="";                   //~1529R~
	protected static final int INVALID_POS    =AdapterView.INVALID_POSITION;//~1601I~//~vc1sR~
                                                                   //~1528I~
    private int options;                                           //~vaxbI~
	private int dialogId,rowId;                                          //~1528I~
	private boolean editableRow;                                   //~1816I~
	private boolean clickableRow;                                  //~@@@@I~
	public  ListView listView;                                     //~1529R~
//	private Spinner  spinnerView;                                  //~1601I~
	protected ArrayList<ListData> arrayData;                         //~1529R~//~vc1sR~
	public ArrayAdapter<ListData> adapter;                         //~1817R~
                                                                   //~1528I~
	protected int cursorpos=INVALID_POS;                             //~1601R~                       //~1601R~//~vc1sR~
                                                                   //~1528I~
	public int menuId;                                           //~1529I~
//  private OnLongClickListener longClickListener;                 //~1601R~
    private ClickListener clickListener,clickListenerV1;           //~@@@@I~
    private FocusListener focusListener;                           //~@@@@I~
    private ListTextWatcher listTextWatcher;                       //~@@@@I~
    protected TextView focusView1,focusView2;                        //~@@@@I~//~vc1sR~
    protected int focusPos;                                          //~@@@@I~//~vc1sR~
    protected AxeList axeList;                                     //~vc1wI~
                                                                   //~1528I~
////*****************                                              //~1604R~
////*for Spinner                                                   //~1604R~
//    public AxeList(int PdialogId,ViewGroup PlayoutView)          //~1604R~
//    {                                                            //~1604R~
//        dialogId=PdialogId;                                      //~1604R~
//        spinnerView=(Spinner)(PlayoutView.findViewById(VIEWID_SPINNER));//~1604R~
//        rowId=ROWID_SPINNER;                                     //~1604R~
//        initSpinner();                                           //~1604R~
//    }                                                            //~1604R~
	public AxeList()                                               //~vc1xR~
	{                                                              //~vc1xR~
        if (Dump.Y) Dump.println("AxeList.defuaultConstructor");   //~vc1xR~
	}	//member init                                              //~vc1xR~
//    public AxeList(int PdialogId,ViewGroup PlayoutView,int Pmenuid,int Prowid)//~@@@@I~//~vc1wR~//+vc1xR~
//    {                                                              //~@@@@I~//~vc1wR~//+vc1xR~
//        axeList=this;                                              //~vc1wR~//+vc1xR~
//        dialogId=PdialogId;                                        //~@@@@I~//~vc1wR~//+vc1xR~
//        menuId=Pmenuid;                                            //~@@@@I~//~vc1wR~//+vc1xR~
//        rowId=Prowid;                                              //~@@@@I~//~vc1wR~//+vc1xR~
//        listView=(ListView)(PlayoutView.findViewById(VIEWID_LIST));//~@@@@I~//~vc1wR~//+vc1xR~
//    }                                                              //~@@@@I~//~vc1wR~//+vc1xR~
    public void initInstance(int PdialogId,ViewGroup PlayoutView,int Pmenuid,int Prowid)//~vc1wR~
    {                                                              //~vc1wI~
        if (Dump.Y) Dump.println("AxeList.initInstance menuid="+Integer.toHexString(Pmenuid)+",rowid="+Integer.toHexString(Prowid));//~vc1wI~
        axeList=this;                                              //~vc1wI~
        dialogId=PdialogId;                                        //~vc1wI~
        menuId=Pmenuid;                                            //~vc1wI~
        rowId=Prowid;                                              //~vc1wI~
        listView=(ListView)(PlayoutView.findViewById(VIEWID_LIST));//~vc1wI~
    }                                                              //~vc1wI~
//    public AxeList(int PdialogId,ViewGroup PlayoutView,int Pmenuid,int Prowid,boolean PeditableRow)//~@@@@I~//~vc1wR~
//    {                                                              //~@@@@I~//~vc1wR~
//        this(PdialogId,PlayoutView,Pmenuid,Prowid);                //~@@@@I~//~vc1wR~
//        editableRow=PeditableRow;                                  //~@@@@I~//~vc1wR~
//        init();                                                    //~@@@@I~//~vc1wR~
//    }                                                              //~@@@@I~//~vc1wR~
    public void initInstance(int PdialogId,ViewGroup PlayoutView,int Pmenuid,int Prowid,boolean PeditableRow)//~vc1wR~
    {                                                              //~vc1wI~
        if (Dump.Y) Dump.println("AxeList.initInstance menuid="+Integer.toHexString(Pmenuid)+",rowid="+Integer.toHexString(Prowid)+",editableRow="+PeditableRow);//~vc1wI~
        initInstance(PdialogId,PlayoutView,Pmenuid,Prowid);        //~vc1wR~
        editableRow=PeditableRow;                                  //~vc1wI~
        init();                                                    //~vc1wI~
    }                                                              //~vc1wI~
//    public AxeList(int PdialogId,ViewGroup PlayoutView,int Pmenuid,int Prowid,boolean PeditableRow,boolean Pclickable)//~vc1xR~
//    {                                                              //~@@@@I~//~vc1xR~
//        this(PdialogId,PlayoutView,Pmenuid,Prowid);                //~@@@@I~//~vc1xR~
//        editableRow=PeditableRow;                                  //~@@@@I~//~vc1xR~
//        clickableRow=Pclickable;                                   //~@@@@I~//~vc1xR~
//        init();                                                    //~@@@@I~//~vc1xR~
//    }                                                              //~@@@@I~//~vc1xR~
    public void initInstance(int PdialogId,ViewGroup PlayoutView,int Pmenuid,int Prowid,boolean PeditableRow,boolean Pclickable)//~vc1xR~
    {                                                              //~vc1xR~
        if (Dump.Y) Dump.println("AxeList.initInstance menuid="+Integer.toHexString(Pmenuid)+",rowid="+Integer.toHexString(Prowid)+",editableRow="+PeditableRow+",clickable="+Pclickable);//~vc1xR~
        initInstance(PdialogId,PlayoutView,Pmenuid,Prowid);        //~vc1xR~
        editableRow=PeditableRow;                                  //~vc1xR~
        clickableRow=Pclickable;                                   //~vc1xR~
        init();                                                    //~vc1xR~
    }                                                              //~vc1xR~
////*for shortcut/shiftkey list                                    //~@@@@R~
//    public AxeList(int PdialogId,ViewGroup PlayoutView,int Pmenuid,int Prowid,boolean PeditableRow)                                               //~1112I~//~@@@@R~
//    {                                                            //~@@@@R~
//        dialogId=PdialogId;                                      //~@@@@R~
//        menuId=Pmenuid;                                          //~@@@@R~
////        if (dialogId==AxeDlgShortcutList.DIALOG_SHORTCUT_MAP)  //~@@@@R~
////            rowId=ROWID_SHORTCUT;                              //~@@@@R~
////        else                                                   //~@@@@R~
////        if (dialogId==AxeDlgShiftList.DIALOG_SHIFTKEY_MAP)     //~@@@@R~
////            rowId=ROWID_SHIFTKEY;                              //~@@@@R~
////        else                                                   //~@@@@R~
////        if (dialogId==AxeDlgAltGrList.DIALOG_ALTGR_MAP)        //~@@@@R~
////            rowId=ROWID_SHIFTKEY;                              //~@@@@R~
////        else                                                   //~@@@@R~
////            return;                                            //~@@@@R~
//        rowId=Prowid;                                            //~@@@@R~
//        editableRow=PeditableRow;                                //~@@@@R~
//        listView=(ListView)(PlayoutView.findViewById(VIEWID_LIST));//~@@@@R~
//        init();                                                  //~@@@@R~
//    }                                                            //~@@@@R~
//*****************                                                //~1612R~
    public static AxeList setupListView(int PdialogId,ViewGroup PlayoutView,int Pmenuid)//~1612R~
    {                                                              //~1612R~
//        AxeList al=new AxeList(PdialogId,PlayoutView,Pmenuid);   //~1612R~
//        return al;                                               //~1612R~
//Overriden by extender                                            //~1612I~
		return null;                                               //~1612I~
    }                                                              //~1612R~
//*****************                                                //~vaxbI~
//    public AxeList(int PdialogId,ViewGroup PlayoutView,int Pmenuid,int Prowid,int Popt)//~vaxbI~//~vc1wR~
//    {                                                              //~vaxbI~//~vc1wR~
//        this(PdialogId,PlayoutView,Pmenuid,Prowid);                //~vaxbI~//~vc1wR~
//        options=Popt;                                              //~vaxbI~//~vc1wR~
//        init();                                                    //~vaxbI~//~vc1wR~
//    }                                                              //~vaxbI~//~vc1wR~
    public void initInstance(int PdialogId,ViewGroup PlayoutView,int Pmenuid,int Prowid,int Popt)//~vc1wR~
    {                                                              //~vc1wI~
        if (Dump.Y) Dump.println("AxeList.initInstance menuid="+Integer.toHexString(Pmenuid)+",rowid="+Integer.toHexString(Prowid)+",opt="+Popt);//~vc1wI~
        initInstance(PdialogId,PlayoutView,Pmenuid,Prowid);        //~vc1wR~
        options=Popt;                                              //~vc1wI~
        init();                                                    //~vc1wI~
    }                                                              //~vc1wI~
//*****************                                                //~1528I~
    public void init()                                             //~1528I~
    {                                                              //~1528I~
		cursorpos=INVALID_POS;                                     //~@@@@I~
		focusPos=INVALID_POS;                                      //~@@@@I~
//      clickListener=new ClickListener(0);                        //~1601R~
        clickListener=addClickListener();//same effect as onitemclick for non editableRow//~1A30R~
//    	longClickListener=new LongClickListener();                 //~1601R~
    	arrayData=new ArrayList<ListData>();                       //~1529R~
//      AxeG.axeKey.setListViewData(dialogId,this,menuId);	//fill arraylist//~1612R~
        setListViewData();	//fill arraylist                       //~1612I~
    	setAdapter();                                              //~1528I~
        if (Dump.Y) Dump.println("AxeList.init cursorpos="+cursorpos);//~vc1sI~
    }                                                              //~1528I~
    public void setListViewData()                                  //~1612I~
    {                                                              //~1612I~
//overrided by extender                                            //~1612I~
    }                                                              //~1612I~
//*****************                                                //~1529I~
    public void add(ListData PlineData)                           //~1529R~
    {                                                              //~1529I~
    	arrayData.add(PlineData);                                  //~1529I~
    }                                                              //~1529I~
//*****************                                                //~vaxbI~
    protected void clearList()                                     //~vaxbI~
    {                                                              //~vaxbI~
    	arrayData.clear();                                         //~vaxbI~
		cursorpos=INVALID_POS;                                     //~vaxbI~
        if (Dump.Y) Dump.println("AxeList.clearList cursorpos="+cursorpos);//~vc1sI~
    }                                                              //~vaxbI~
//*****************                                                //~1528I~
//  private void setAdapter()            //~1112I~//~1114R~     //~1219R~//~vaxbR~
    protected void setAdapter()                                    //~vaxbI~
    {                                                              //~1528I~
        adapter=new ListArrayAdapter(arrayData,rowId);//~1112I~//~1114R~  //~1219R~//~1529R~
        listView.setAdapter(adapter);                                    //~1112I~//~1528I~
    	setMode(listView);                                         //~1528I~
    	setListener(listView);                                     //~1528I~
    }                                                              //~1528I~
//*****************                                                //~vaxbI~
    protected void resetAdapter()                                  //~vaxbI~
    {                                                              //~vaxbI~
        if (Dump.Y) Dump.println("AxeList.resetAdapter");          //~vc1sI~
		adapter.notifyDataSetChanged();                            //~vaxbI~
    }                                                              //~vaxbI~
//*****************                                                //~1528I~
    private void setMode(ListView Plv)                             //~1528I~
    {                                                              //~1528I~
    	if (editableRow)                                           //~1816I~
        {                                                          //~1816I~
			Plv.setItemsCanFocus(true);                            //~1816R~
			Plv.setFocusableInTouchMode(true); //fail if Array is empty//~1816I~
        }                                                          //~1816I~
        if (Dump.Y) Dump.println("AxeList.setMode isFocusable="+Plv.isFocusable());//~1528I~//~vc1sR~
        if (Dump.Y) Dump.println("AxeList.setmode isFocusableInTouchMode="+Plv.isFocusableInTouchMode());//~1528I~//~vc1sR~
        if (Dump.Y) Dump.println("AxeList.setMode isFocusable="+Plv.isFocusable());//~1528I~//~vc1sR~
        if (Dump.Y) Dump.println("AxeList.setmode isFocusableInTouchMode="+Plv.isFocusableInTouchMode());//~1528I~//~vc1sR~
    }                                                              //~1528I~

//*****************                                              //~1604R~
    private void setListener(ListView Plv)                       //~1604R~
    {                                                            //~1604R~
//OnItemClickLisener dose not work when line layout contains focusable child//~1816R~
//      OnItemClickListener l=new ListItemClickListener();       //~1604R~
//      Plv.setOnItemClickListener(l);                           //~1604R~
//      if (dialogId==AxeDialog.DIALOG_SHORTCUT_MAP)             //~1604R~
//          Plv.setOnLongClickListener(longClickListener);       //~1604R~
        OnItemSelectedListener lisl=new ListItemSelectedListener(this);//~1604R~//~1824R~
        Plv.setOnItemSelectedListener(lisl);                     //~1604R~
        if (!editableRow)   //effective only when not focusable    //~1816I~//~1A29R~//~1A30R~
        {                                                          //~1816I~//~1A29R~//~1A30R~
        	OnItemClickListener icl=new ItemClickListener();       //~1816R~
        	Plv.setOnItemClickListener(icl);                       //~1816R~
        }                                                          //~1816I~//~1A29R~//~1A30R~
    }                                                            //~1604R~
//*****************                                                //~1528I~
    public int getSelectedPos()                                    //~1528I~
    {                                                              //~1528I~
        if (Dump.Y) Dump.println("AxeList.getSelectedPos cursorpos="+cursorpos);//~vc1sR~
    	return cursorpos;                                          //~1530R~
    }                                                              //~1528I~
    public int getSelectedPosListView()                           //~1817I~
    {                                                              //~1817I~
    	return listView.getSelectedItemPosition();	//trackball selection//~1817I~
    }                                                              //~1817I~
    public ListData getSelectedItem()                              //~1529R~
    {                                                              //~1528I~
    	int pos;                                                   //~1528I~
    	pos=listView.getCheckedItemPosition();                     //~1528I~
        if (Dump.Y) Dump.println("Listview getCheckedItem pos="+pos);//~1528I~
    	pos=listView.getSelectedItemPosition();	//trackball selection//~1115I~//~1528I~
        if (Dump.Y) Dump.println("Listview getSelectedItem pos="+pos);//~1528I~
        if (Dump.Y) Dump.println("selected item="+listView.getSelectedItem());//~1528I~
        pos=cursorpos;                                             //~1530R~
        if (Dump.Y) Dump.println("Listview cursorpos="+pos);       //~1530R~
        if (pos==INVALID_POS||pos>=arrayData.size())                       //~1114I~//~1118R~//~1601R~
        	return null;                                             //~1528I~
        ListData item=arrayData.get(pos);                             //~1118I~//~1529R~
        if (Dump.Y) Dump.println("Listview cursorpos="+pos);       //~1530R~
        return item;                                               //~1528I~
    }                                                              //~1528I~
//*****************                                                //~1528I~
    public int getItemCount()                                      //~1528I~
    {                                                              //~1528I~
        return arrayData.size();                                  //~1112I~//~1528I~
    }                                                              //~1528I~
//*****************                                                //~1528I~
    public void select(int Ppos)           //~1530R~               //~1810R~
    {                                                            //~1530R~//~1810R~
//        cursorpos=Ppos;                                        //~1530R~//~1810R~
//        if (Dump.Y) Dump.println("Listview select(setSlectedItem) req="+Ppos+",slected="+listView.getSelectedItemPosition());//~1530R~//~1810R~
//        if (Dump.Y) Dump.println("Listview select(setItemChecked) pos="+listView.getCheckedItemPosition());//~1530R~//~1810R~
//        listView.requestFocus();                               //~1530R~//~1810R~
//        listView.setItemChecked(Ppos,true);                        //~1118I~//~1530R~//~1810R~
        if (Dump.Y) Dump.println("AxeList.select pos="+Ppos+",isInTouchMode="+listView.isInTouchMode());//~1530R~//~1810R~//~vc1sR~
        listView.setSelection(Ppos);   //#### setSelection() is ignored if isInTouchMode()//~1530R~//~1810R~
    }                                                            //~1530R~//~1810R~
////*****************                                              //~1816R~
//    public void setFocus(int Ppos)                               //~1816R~
//    {                                                            //~1816R~
//        ListData data;                                           //~1816R~
//        int sz=arrayData.size();                                 //~1816R~
//        TextView  view;                                          //~1816R~
//    //*******************                                        //~1816R~
//        if (Dump.Y) Dump.println("AxeList requestFocus pos="+Ppos);//~1816R~
//        view=getTextView2(Ppos);                                 //~1816R~
//        if (view==null)                                          //~1816R~
//            return;                                              //~1816R~
//        view.requestFocus();                                     //~1816R~
//    }                                                            //~1816R~
//*****************                                                //~1810I~
    public TextView getTextView2(int Ppos)                         //~1816R~
    {                                                              //~1810I~
        ListData data;                                             //~1810I~
        int sz=arrayData.size();                                   //~1810I~
    //*******************                                          //~1810I~
        if (Dump.Y) Dump.println("AxeList getEditTextView pos="+Ppos);//~1810I~
        if (Ppos<0 || Ppos>=sz)                                    //~1810I~
        	return null;                                                //~1810I~
		data=arrayData.get(Ppos);                                  //~1810I~
    	return data.textView2;                                     //~1816R~
    }                                                              //~1810I~
//**********************************************************************//~vc1sI~
//*AxeLst___ override this                                         //~vc1sI~
//**********************************************************************//~vc1sI~
    protected View getViewEach(ListArrayAdapter Padapter,int Ppos, View Pview,ViewGroup Pparent)    //~vc1sI~//~vc1wR~
    {                                                              //~vc1sI~
    	return null;                                               //~vc1sI~
    }                                                              //~vc1sI~
//**********************************************************************//~1528I~
//*ArrayAdapter class                                              //~1528I~
//**********************************************************************//~1528I~
    class ListArrayAdapter extends ArrayAdapter<ListData>           //~1111I~//~1112R~//~1529R~
    {                                                              //~1528I~
    	int rowId;                                                 //~1529I~
        ArrayList<ListData> arryData;                              //~1529R~
        //**************************                               //~1529I~
        public ListArrayAdapter(ArrayList<ListData> ParrayData,int ProwId)//~1111I~//~1112R~//~1114R~//~1529R~
        {                                                          //~1528I~
            super(AxeG.context,ProwId,ParrayData);         //~1111I~//~1211R~//~1529R~
            arrayData=ParrayData;                                  //~1529I~
            rowId=ProwId;                                          //~1529I~
        }                                                          //~1528I~
        @Override                                                  //~1528I~
        public View getView(int Ppos, View Pview,ViewGroup Pparent)//~1528I~
        {                                                          //~1528I~
	        View /*heldview,*/convertview;                                           //~1220I~//~1529R~//~vc1sR~
            ListViewTag tag;                                       //~@@@@I~
            TextView v1,v2;                                        //~@@@@I~
        //*******************                                      //~1528I~
//          if (Dump.Y) Dump.println("ListAdapter getview Ppos="+Ppos+"CheckedItemPos="+((ListView)Pparent).getCheckedItemPosition());//~1814R~//~1824R~//~1A30R~
            convertview=Pview;                      //~1221I~      //~1529R~//~vc1sI~
        	try                                                    //~1529I~
            {                                                      //~1529I~
//              View each=getViewEach(this,Ppos,Pview,Pparent);    //~vc1sR~//~vc1wR~
                View each=axeList.getViewEach(this,Ppos,Pview,Pparent);//~vc1wI~
                if (each!=null)                                    //~vc1sR~
                    return each;                                   //~vc1sR~
                if (convertview==null)      //shown 1st time                                    //~1221I~//~@@@@R~
                {                                                  //~@@@@R~
					if (Dump.Y) Dump.println("ListAdapter.getview convertview=null");//~vc1sI~
                    convertview=AxeG.inflater.inflate(rowId,Pparent,false/*attachToRoot*/);//~@@@@R~
	            	v1=(TextView)(convertview.findViewById(AxeList.VIEWID_KEY1));//~@@@@I~
    	        	v2=(TextView)(convertview.findViewById(AxeList.VIEWID_KEY2));//~@@@@I~
//                  tag=new ListViewTag(v1,v2);                    //~vaxbR~
                    tag=new ListViewTag(v1,v2,0,0);                //~vaxbI~
                    convertview.setTag(tag);                       //~@@@@I~
//                    if (Dump.Y) Dump.println("ListAdapter getview 1st Ppos="+Ppos+" v2="+v2.toString());//~@@@@I~
                }                                                  //~@@@@R~
                else                                               //~@@@@R~
                {                                                  //~@@@@R~
                    tag=(ListViewTag)convertview.getTag();         //~@@@@I~
                    v1=tag.view1;                                  //~@@@@I~
                    v2=tag.view2;                                  //~@@@@I~
//                    if (Dump.Y) Dump.println("ListAdapter getview 2nd Ppos="+Ppos+" v2="+v2.toString());//~@@@@I~
                }                                                  //~@@@@R~
//              setLineView(Ppos,v1,v2);                           //~vaxbR~
                setLineView(convertview,Ppos,v1,v2);               //~vaxbI~
            }                                                      //~1529I~
            catch(Exception e)                                     //~1529I~
            {                                                      //~1529I~
                Dump.println(e,"AexeList:getView");                //~1529I~
            }                                                      //~1529I~
            return convertview;                                  //~1529I~
        }                                                          //~1528I~
    //****************                                             //~1529I~
//        public void setLineView(int Ppos,View Pview,ListData PlineData)//~1530R~
//        {                                                        //~1530R~
//            int bg;                                              //~1530R~
//            ListData data;                                       //~1530R~
//        //*******************                                    //~1530R~
//            data=PlineData;                                      //~1530R~
//            int key1=data.key1;                                  //~1530R~
//            int key2=data.key2;                                  //~1530R~
//            String s1=AxeKey.keyToString(key1);                  //~1530R~
//            String s2=AxeKey.keyToString(key2,NOT_DEFINED);      //~1530R~
//            TextView v1=(TextView)(Pview.findViewById(AxeList.VIEWID_KEY1));//~1530R~
//            EditText v2=(EditText)(Pview.findViewById(AxeList.VIEWID_KEY2));//~1530R~
//            v1.setText(s1);                                      //~1530R~
//            v2.setText(s2);                                      //~1530R~
//            if (data.status==DATA_ERR)                           //~1530R~
//                bg=COLOR_ERR;                                    //~1530R~
//            else                                                 //~1530R~
//            if (Ppos==cursorpos)                                 //~1530R~
//                bg=COLOR_CURSOR;                                 //~1530R~
//            else                                                 //~1530R~
//                bg=COLOR_NORMAL;                                 //~1530R~
//            if (Dump.Y) Dump.println("AxeList setlinedata pos="+Ppos+",cursorpos="+cursorpos+",bg="+Integer.toHexString(bg));//~1530R~
//            v2.setBackgroundColor(bg);                           //~1530R~
//            addTextWatcher(v2,data);                             //~1530R~
////          addEditorActionListener(v2,data);                    //~1530R~
//        }                                                        //~1530R~
//        public View setLineView(int Ppos,ViewGroup Pparent)      //~@@@@R~
//        {                                                        //~@@@@R~
//            int bg,fg;                                           //~@@@@R~
//            ListData data;                                       //~@@@@R~
//            View dataView;                                       //~@@@@R~
//            int key1,key2;                                       //~@@@@R~
//            TextView v1,v2;                                      //~@@@@R~
//        //*******************                                    //~@@@@R~
//            data=arrayData.get(Ppos);                            //~@@@@R~
//            dataView=data.dataView;                              //~@@@@R~
//            key1=data.key1;                                      //~@@@@R~
//            key2=data.key2;                                      //~@@@@R~
//            if (dataView==null)                                  //~@@@@R~
//            {                                                    //~@@@@R~
//                data.dataView=dataView=AxeG.inflater.inflate(rowId,Pparent,false/*attachToRoot*/);//~@@@@R~
//                v1=(TextView)(dataView.findViewById(AxeList.VIEWID_KEY1));//~@@@@R~
//                v2=(TextView)(dataView.findViewById(AxeList.VIEWID_KEY2));//~@@@@R~
//                String s1=getKeyname(Ppos,1,key1,NOT_DEFINED);     //~1920R~//~@@@@R~
//                String s2=getKeyname(Ppos,2,key2,NOT_DEFINED);     //~1920R~//~@@@@R~
//                v1.setText(s1);                                  //~@@@@R~
//                if (editableRow || data.isEditable)    //editable//~1A29I~//~@@@@R~
//                    ((EditText)v2).setText(s2,BufferType.EDITABLE); //popup IM//~@@@@R~
//                else                                             //~@@@@R~
//                    v2.setText(s2);                                    //~1530I~//~@@@@R~
//                data.textView2=v2;                               //~@@@@R~
//                if (Dump.Y) Dump.println("AxeList setLineView settext pos="+Ppos+",str="+s2);//~@@@@R~
//                addTextWatcher(Ppos,v2,data);                    //~@@@@R~
//                if (editableRow)                                   //~1816I~//~@@@@R~
//                {                                                //~@@@@R~
////                    if (data.isNotEditable)    //termBtn           //~1A29I~//~@@@@R~
////                    {                                              //~1A29I~//~@@@@R~
//////                        v2.setFocusableInTouchMode(false);       //~1A29R~//~@@@@R~
//////                        v2.setFocusable(false);                  //~1A29R~//~@@@@R~
//////                        v2.setClickable(false);                  //~1A29R~//~@@@@R~
////                        addFocusChangeListener(Ppos,(EditText)v2); //~1A29I~//~@@@@R~
////                    }                                              //~1A29I~//~@@@@R~
////                    else                                           //~1A29I~//~@@@@R~
////                    {                                              //~1A29I~//~@@@@R~
//                        v2.setFocusableInTouchMode(true);        //~@@@@R~
//                        v2.setFocusable(true);                   //~@@@@R~
//                        v2.setClickable(true);                   //~@@@@R~
////                      addTextWatcher(Ppos,(EditText)v2,data);        //~1816R~//~1824R~//~@@@@R~
//                        addFocusChangeListener(Ppos,(EditText)v2);     //~1816R~//~@@@@R~
////                    }                                              //~1A29I~//~@@@@R~
//                }                                                //~@@@@R~
//                else                                             //~@@@@R~
//                if (data.isEditable)    //termBtn                //~@@@@R~
//                {                                                //~@@@@R~
//                    v2.setFocusableInTouchMode(true);            //~@@@@R~
//                    v2.setFocusable(true);                       //~@@@@R~
//                    v2.setClickable(true);                       //~@@@@R~
//                    addClickListener(Ppos,v2);                         //~1601R~//~@@@@R~
//                }                                                //~@@@@R~
////              else                                             //~@@@@R~
////              addEditorActionListener(v2,data);                //~@@@@R~
////              if (Dump.Y) Dump.println("AxeList setLineView 1st pos="+Ppos+",key="+Integer.toHexString(key1)+",dataview="+dataView.toString());//~@@@@R~
////              v2.setFocusableInTouchMode(true);                //~@@@@R~
////              if (dialogId==AxeDlgShortcutList.DIALOG_SHORTCUT_MAP)//~@@@@R~
////              {                                                //~@@@@R~
////                  dataView.setOnLongClickListener(longClickListener);//~@@@@R~
////                  v2.setOnClickListener(clickListener);        //~@@@@R~
////                  dataView.setOnClickListener(clickListener);  //~@@@@R~
////                  v2.setOnLongClickListener(longClickListener);//~@@@@R~
////                  if (Dump.Y) Dump.println("AxeList setLineView v2="+v2.toString());//~1816R~//~@@@@R~
////                  addClickListener(Ppos,v2);  //popup extkey dialog//~@@@@R~
////              }                                                //~@@@@R~
//            }                                                    //~@@@@R~
//            else                                                 //~@@@@R~
//            {                                                    //~@@@@R~
//                v2=(TextView)(dataView.findViewById(AxeList.VIEWID_KEY2));//~@@@@R~
//                data.textView2=v2;                               //~@@@@R~
////              if (Dump.Y) Dump.println("AxeList setLineView 2nd pos="+Ppos+",key="+Integer.toHexString(key1)+",dataview="+dataView.toString());//~@@@@R~
//            }                                                    //~@@@@R~
//            if (data.status==DATA_ERR)                           //~@@@@R~
//                fg=COLOR_FGERR;                                  //~@@@@R~
//            else                                                 //~@@@@R~
//                fg=COLOR_FG;                                     //~@@@@R~
//            v2.setTextColor(fg);                                 //~@@@@R~
//            if (Ppos==cursorpos)                                 //~@@@@R~
//            {                                                    //~@@@@R~
//                bg=COLOR_BGFOCUS;                                //~@@@@R~
//                if (Dump.Y) Dump.println("AxeList setlinedata cursorpos pos="+Ppos);//~@@@@R~
//            }                                                    //~@@@@R~
//            else                                                 //~@@@@R~
//                bg=COLOR_BG;                                     //~@@@@R~
//            v2.setBackgroundColor(bg);                           //~@@@@R~
////          if (Dump.Y) Dump.println("AxeList setlinedata pos="+Ppos+",cursorpos="+cursorpos+",bg="+Integer.toHexString(bg)+",fg="+Integer.toHexString(fg));//~1814R~//~1A30R~//~@@@@R~
//            return dataView;                                     //~@@@@R~
//        }                                                        //~@@@@R~
//      public void setLineView(int Ppos,TextView Pview1,TextView Pview2)//~vaxbR~
        public void setLineView(View Pconvertview,int Ppos,TextView Pview1,TextView Pview2)//~vaxbI~
        {                                                          //~@@@@I~
            int bg,fg;                                             //~@@@@I~
            ListData data;                                         //~@@@@I~
//            View dataView;                                       //~@@@@I~
            int key1,key2;                                         //~@@@@I~
            TextView v1,v2;                                        //~@@@@I~
            ListViewTag tag1,tag2;                                 //~vaxbI~
        //*******************                                      //~@@@@I~
        	v1=Pview1;                                             //~@@@@I~
        	v2=Pview2;                                             //~@@@@I~
            tag1=new ListViewTag(v1,v2,Ppos,1);                    //~vaxbI~
            tag2=new ListViewTag(v1,v2,Ppos,2);                    //~vaxbI~
//          v1.setId(Ppos);	//for FocusChangeListener              //~vaxbR~
//          v1.setTag(v2);	//for FocusChangeListener              //~vaxbR~
//          v2.setId(Ppos);	//for FocusChangeListener              //~vaxbR~
//          v2.setTag(v1);	//for FocusChangeListener              //~vaxbR~
            v1.setTag(tag1);	//for FocusChangeListener          //~vaxbI~
            v2.setTag(tag2);	//for FocusChangeListener          //~vaxbI~
            data=arrayData.get(Ppos);                              //~@@@@I~
            key1=data.key1;                                        //~@@@@I~
            key2=data.key2;                                        //~@@@@I~
            String s1=getKeyname(Ppos,1,key1,NOT_DEFINED);         //~@@@@I~
            String s2=getKeyname(Ppos,2,key2,NOT_DEFINED);         //~@@@@I~
            v1.setText(s1);                                        //~@@@@I~
//            v2.setText(Editable.Factory.getInstance().newEditable(s2),BufferType.EDITABLE);//~@@@@I~
            v2.setText(s2);                                        //~@@@@I~
//            if (Dump.Y) Dump.println("AxeList setLineView settext pos="+Ppos+",str="+s2+"<"+"v2="+v2.toString());//~@@@@I~
            data.textView1=v1;                                     //~@@@@I~
            data.textView2=v2;	//for getText                      //~@@@@I~
            if (editableRow)                                       //~@@@@I~
            {                                                      //~@@@@I~
            	addFocusChangeListener(v2);                        //~@@@@I~
            }                                                      //~@@@@I~
            else                                                   //~@@@@I~
            if (data.isEditable)    //termBtn Userkey              //~@@@@I~
            {                                                      //~@@@@I~
            	addFocusChangeListener(v2);                        //~@@@@I~
            }                                                      //~@@@@I~
            if (clickableRow)                                      //~@@@@I~
            {                                                      //~@@@@I~
    			addClickListenerV1(v1);                            //~@@@@I~
            }                                                      //~@@@@I~
          if (!setLineViewSub(Pconvertview,Ppos,Pview1,Pview2,data))	//filechooser override and return true//~vaxbI~
          {                                                        //~vaxbI~
            if (data.status==DATA_ERR)                             //~@@@@I~
                fg=COLOR_FGERR;                                    //~@@@@I~
            else                                                   //~@@@@I~
                fg=COLOR_FG;                                       //~@@@@I~
            v2.setTextColor(fg);                                   //~@@@@I~
            if (Ppos==cursorpos)                                   //~@@@@I~
            {                                                      //~@@@@I~
                bg=COLOR_BGFOCUS;                                  //~@@@@I~
//                if (Dump.Y) Dump.println("AxeList getView cusrsopos="+cursorpos+",v2="+v2.toString());//~@@@@I~
            }                                                      //~@@@@I~
            else                                                   //~@@@@I~
                bg=COLOR_BG;                                       //~@@@@I~
            v2.setBackgroundColor(bg);                             //~@@@@I~
          }                                                        //~vaxbI~
        }                                                          //~@@@@I~
    //****************                                             //~1612I~
//        public void addFocusChangeListener(int Ppos,EditText PeditText)//~@@@@R~
//        {                                                        //~@@@@R~
//            FocusListener l=new FocusListener(Ppos,PeditText);   //~@@@@R~
//            PeditText.setOnFocusChangeListener(l);               //~@@@@R~
//        }                                                        //~@@@@R~
    //****************                                             //~1612I~
//        private void addTextWatcher(int Ppos,TextView Ptextview,ListData Pdata)//~1530R~//~@@@@R~
//        {                                                        //~@@@@R~
//            ListTextWatcher w=new ListTextWatcher(Ppos,Ptextview,Pdata);//~1530R~//~@@@@R~
//            Ptextview.addTextChangedListener(w);                   //~1530R~//~@@@@R~
//            if (Dump.Y) Dump.println("addtextWatcher key1="+Integer.toHexString(Pdata.key1)+"view="+Ptextview.toString());//~1530R~//~@@@@R~
//        }                                                        //~@@@@R~
//        private void addEditorActionListener(EditText PeditText,ListData Pdata)//~1601R~
//        {                                                        //~1601R~
//                EditorActionListener l=new EditorActionListener();//~1601R~
//                PeditText.setOnEditorActionListener(l);          //~1601R~
//        }                                                        //~1601R~
    }//inner class ListArrayAdapter                                //~1529R~
  //*************************************************************  //~vc1sI~
    public void addFocusChangeListener(TextView PeditText)     //~@@@@I~//~vc1sI~
    {                                                          //~@@@@I~//~vc1sI~
        if (focusListener==null)                               //~@@@@I~//~vc1sI~
            focusListener=new FocusListener();                 //~@@@@I~//~vc1sI~
        PeditText.setOnFocusChangeListener(focusListener);     //~@@@@I~//~vc1sI~
        if (Dump.Y) Dump.println("AxeList.addFocusChangeListener view="+PeditText.toString());//~vc1sI~
    }                                                          //~@@@@I~//~vc1sI~
  //*************************************************************  //~@@@@I~
    protected void addTextWatcher(TextView PtextView)                //~@@@@I~//~vc1sR~
    {                                                              //~@@@@I~
        if (listTextWatcher==null)                                 //~@@@@I~
        	listTextWatcher=new ListTextWatcher();                 //~@@@@I~
        listTextWatcher.setView(PtextView);                        //~@@@@I~
	    removeTextWatcher(PtextView);	//avoid multiple enq       //~@@@@I~
        PtextView.addTextChangedListener(listTextWatcher);         //~@@@@I~
        if (Dump.Y) Dump.println("AxeList.addtextWatcher pos="+PtextView.getId()+",view="+PtextView.toString());//~@@@@I~//~vc1sR~
    }                                                              //~@@@@I~
    protected void removeTextWatcher(TextView PtextView)             //~@@@@I~//~vc1sR~
    {                                                              //~@@@@I~
        if (listTextWatcher!=null)                                 //~@@@@I~
        	PtextView.removeTextChangedListener(listTextWatcher);  //~@@@@I~
        if (Dump.Y) Dump.println("removetextWatcher pos="+PtextView.getId()+",view="+PtextView.toString());//~@@@@I~
    }                                                              //~@@@@I~
//**********************************************************************//~1A30R~
    public ClickListener addClickListener()                        //~1A30R~
    {                                                              //~1A30R~
        ClickListener l=new ClickListener(0);                      //~@@@@R~
//        if (Dump.Y) Dump.println("addClickListener="+l.toString());//~@@@@R~//~vc1sR~
        return l;                                                  //~1A30R~
    }                                                              //~1A30R~
    public void addClickListener(int Ppos,TextView Pview)    //~1816R~//~1A30R~
    {                                                            //~1816R~//~1A30R~
    	ClickListener l=new ClickListener(Ppos);                   //~@@@@I~
        Pview.setOnClickListener(l);                   //~1816R~   //~@@@@R~
//        if (Dump.Y) Dump.println("addCliskListener="+clickListener.toString());//~@@@@R~
    }                                                            //~1816R~//~1A30R~
    public void addClickListenerV1(View Pview)                     //~@@@@I~
    {                                                              //~@@@@I~
    	if (clickListenerV1==null)                                 //~@@@@I~
    		clickListenerV1=new ClickListener(-1);                 //~@@@@I~
        Pview.setOnClickListener(clickListenerV1);                 //~@@@@I~
//      if (Dump.Y) Dump.println("addClickListenerV1 V1="+Pview.toString());//~@@@@I~//~vc1sR~
    }                                                              //~@@@@I~
//**********************************************************************//~1816R~//~1A30R~
//*EditText click listener for no editableRow                      //~1A30R~
//**********************************************************************//~1816R~//~1A30R~
    class ClickListener implements OnClickListener               //~1816R~//~1A30R~
    {                                                            //~1816R~//~1A30R~
//  	int pos;                                                   //~vaxbR~
    	int pos,cols;                                              //~vaxbI~
        boolean v1listener;                                        //~@@@@I~
        //*********************                                  //~1816R~//~1A30R~
        public ClickListener(int Ppos)                           //~1816R~//~@@@@R~
        {                                                        //~1816R~//~@@@@R~
            pos=Ppos;                                            //~1816R~//~@@@@R~
            if (pos==-1)                                           //~@@@@I~
            	v1listener=true;                                   //~@@@@I~
        }                                                        //~1816R~//~@@@@R~
        //*********                                              //~1816R~//~1A30R~
        public void onClick(View Pview)                          //~1816R~//~1A30R~
        {                                                        //~1816R~//~1A30R~
            int ii,ctr;                                            //~1A30R~
            ListData data=null;                                    //~1A30R~
            TextView v1,v2;                                        //~@@@@I~
        //*********                                                //~1A30R~
            if (Dump.Y) Dump.println("AxeList.OnclickListener.onClick view="+Pview.toString());//~1816R~//~1A30R~//~vc1sR~
            try                                                  //~1816R~//~1A30R~
            {                                                    //~1816R~//~1A30R~
	            if (onClickEach(Pview))                            //~vc1sI~
    	            return;                                        //~vc1sI~
//                ctr=arrayData.size();                            //~@@@@R~
//                for (ii=0;ii<ctr;ii++)                           //~@@@@R~
//                {                                                //~@@@@R~
//                    data=arrayData.get(ii);                      //~@@@@R~
//                    if (Pview==data.textView2)                   //~@@@@R~
//                        break;                                   //~@@@@R~
//                }                                                //~@@@@R~
//                if (Dump.Y) Dump.println("OnClickListener search pos="+ii+"/"+ctr);//~@@@@R~
//                if (ii<ctr)                                      //~@@@@R~
//                {                                                //~@@@@R~
//                    cursorpos=ii;                                //~@@@@R~
//                    onItemClick(cursorpos,data,Pview);                 //~1816R~//~@@@@R~
//                }                                                //~1816R~//~@@@@R~
                ctr=arrayData.size();                              //~@@@@I~
//				pos=Pview.getId();                                 //~vaxbR~
                ListViewTag tag=(ListViewTag)Pview.getTag();       //~vaxbI~
                pos=tag.pos;                                       //~vaxbI~
                cols=tag.cols;                                     //~vaxbI~
                v1=(TextView)tag.view1;                            //~vaxbI~
                v2=(TextView)tag.view2;                            //~vaxbI~
                if (pos>=0 && pos<ctr)                             //~@@@@I~
                {                                                  //~@@@@I~
					cursorpos=pos;                                 //~@@@@I~
                    data=arrayData.get(pos);                       //~@@@@I~
                    if (v1listener)                                //~@@@@I~
                    {                                              //~@@@@I~
//                   	v1=(TextView) Pview;                       //~vaxbR~
//                    	v2=(TextView) Pview.getTag();              //~vaxbR~
                        v2.requestFocus();                         //~@@@@I~
			            if (Dump.Y) Dump.println("AxeList OnclickListenerV1  pos="+cursorpos+",requestFocus="+v2.toString());//~@@@@I~
                    }                                              //~@@@@I~
                    else                                           //~@@@@I~
                    {                                              //~@@@@I~
//                  	v2=(TextView) Pview;                       //~vaxbR~
//                  	v1=(TextView) Pview.getTag();              //~vaxbR~
			            if (Dump.Y) Dump.println("AxeList OnclickListenerV2  pos="+cursorpos+",v1="+v1.toString());//~@@@@I~
                    }                                              //~@@@@I~
			        if (Dump.Y) Dump.println("AxeList.onclick cursorpos="+cursorpos);//~vc1sI~
//                  onItemClick(cursorpos,data,Pview);             //~vaxbR~
                    onClicked(cursorpos,cols,data,Pview);          //~vaxbI~
                }                                                  //~@@@@I~
            }                                                    //~1816R~//~1A30R~
            catch(Exception e)                                   //~1816R~//~1A30R~
            {                                                    //~1816R~//~1A30R~
                Dump.println(e,"AxeList:OnClickListener");      //~1816R~//~1A30R~//~vc1sR~
            }                                                    //~1816R~//~1A30R~
        }                                                        //~1816R~//~1A30R~
    }//inner class                                               //~1816R~//~1A30R~
    //**********************************************************************//~vc1sI~
    public boolean onClickEach(View Pview)                         //~vc1sI~
    {                                                              //~vc1sI~
    //Overrided by AxeLstKbdLayoutHW
        return false;// +vc1sI~
    }                                                              //~vc1sI~
    //**********************************************************************//~1816R~//~1A30R~
//  public void onItemClick(int Ppos,ListData Pitem,View Pview)  //~1816R~//~vaxbR~
    public void onClicked(int Ppos,int cols,ListData Pitem,View Pview)//~vaxbI~
    {                                                            //~1816R~//~1A30R~
    //Overrided by Shortcut and Termbtn                          //~1816R~//~1A30R~
        if (Dump.Y) Dump.println("AxeList OnclickListener pos="+Ppos+",view="+Pview.toString());//~1A30R~
    }                                                            //~1816R~//~1A30R~
//**********************************************************************//~1530I~
    class FocusListener implements OnFocusChangeListener
    {                                                              //~1530I~
//        int pos;                                                 //~@@@@R~
//        EditText editText;                                       //~@@@@R~
        //*********************                                    //~1530I~
//        public FocusListener(int Ppos,EditText PeditText)        //~@@@@R~
//        {                                                        //~@@@@R~
//            pos=Ppos;                                            //~@@@@R~
//            editText=PeditText;                                  //~@@@@R~
//        }                                                        //~@@@@R~
        //*********                                                //~1530I~
        @Override                                                  //~vc1sI~
        public void onFocusChange(View Pview,boolean PhasFocus)                     //~1530I~
        {                                                          //~1530I~
        	int bg;                                                //~1530I~
            int pos;                                               //~@@@@I~
			TextView editText,v2,v1;                               //~@@@@I~
            ListData data;                                         //~@@@@I~
        //**************                                           //~1530I~
        	try                                                    //~1601I~
            {                                                      //~1601I~
                if (Dump.Y) Dump.println("AxeList FocusListener focus="+PhasFocus+",Pview="+Pview.toString());//~vc1sI~
	            if (onFocusChangedEach(Pview,PhasFocus))           //~vc1sR~
    	            return;                                        //~vc1sI~
            	editText=(TextView) Pview;                         //~@@@@I~
//              pos=Pview.getId();                                 //~vaxbR~
                ListViewTag tag=(ListViewTag)Pview.getTag();       //~vaxbI~
                pos=tag.pos;                                       //~vaxbI~
                v1=(TextView)tag.view1;                            //~vaxbI~
                v2=(TextView)tag.view2;                            //~vaxbI~
				data=arrayData.get(pos);                           //~@@@@I~
//              v1=(TextView)Pview.getTag();                       //~vaxbR~
                v2=data.textView2;                                 //~@@@@I~
                if (Dump.Y) Dump.println("AxeList FocusListener idpos="+pos+",v1="+v1.getText().toString());//~@@@@I~
                if (Dump.Y) Dump.println("AxeList FocusListener focus="+PhasFocus+",v2="+v2.toString()+",Pview="+editText.toString());//~@@@@I~
                data.textView1=v1;                                 //~@@@@I~
                data.textView2=editText;                           //~@@@@I~
                if (PhasFocus)                                     //~1601R~
                {                                                  //~1601R~
                    cursorpos=pos;                                 //~1601R~
    //              Pview.invalidate();                            //~1601R~
    //              adapter.notifyDataSetChanged(); //redraw       //~1601R~
    //              listView.invalidate();                         //~1601R~
    //              listView.setSelection(pos);                    //~1601R~
    //              if (Dump.Y) Dump.println("AxeList FocusListener notify Changed req pos="+pos+",focus="+PhasFocus);//~1601R~
                    bg=COLOR_BGFOCUS;                              //~1601R~
    //              listView.invalidateViews();                    //~1601R~
//                    onFocusChanged(pos,true,editText);           //~@@@@R~
                    onFocusChanged(pos,true,data);                 //~@@@@I~
                    addTextWatcher(editText); //many duplicated listner call//~@@@@I~
                    focusView1=v1;                                 //~@@@@I~
                    focusView2=editText;                           //~@@@@I~
                    focusPos=pos;                                  //~@@@@I~
                }                                                  //~1601R~
                else                                               //~1601R~
                {                                                  //~1601R~
//                    chkInput(pos,editText);                            //~1601R~//~@@@@R~
                    chkInput(pos,v1,editText);                     //~@@@@I~
                    bg=COLOR_BG;                                   //~1601R~
//                    onFocusChanged(pos,false,editText);          //~@@@@R~
                    onFocusChanged(pos,false,data);                //~@@@@I~
                    removeTextWatcher(editText); //many duplicated listner call//~@@@@I~
                    cursorpos=INVALID_POS;                         //~@@@@I~
                    focusPos=INVALID_POS;                          //~@@@@I~
                }                                                  //~1601R~
                if (Dump.Y) Dump.println("AxeList FocusListener cursorpos="+cursorpos+",text="+((TextView)Pview).getText());//~@@@@I~//~vc1sR~
                editText.setBackgroundColor(bg);                   //~1601R~
                invalidate();  //not to editText to redraw bg color changed//~1601R~
            }                                                      //~1601I~
            catch(Exception e)                                     //~1601I~
            {                                                      //~1601I~
                Dump.println(e,"AexeList:OnFocusChange");          //~1601I~
            }                                                      //~1601I~
        }                                                          //~1530I~

    }//inner class                                                 //~1530I~
	//***************************                                  //~1810I~
//    public void onFocusChanged(int Ppos,boolean Phasfocus,EditText PeditText)//~@@@@R~
    public void onFocusChanged(int Ppos,boolean Phasfocus,ListData Pdata)//~@@@@I~
    {                                                              //~1810I~
    //override if required                                         //~1810I~
    }                                                              //~1810I~
//**********************************************************************//~vc1sI~
    public boolean onFocusChangedEach(View Pview,boolean PhasFocus)//~vc1sR~
    {                                                              //~vc1sI~
    //override if required and return true                         //~vc1sI~
    	return false;                                              //~vc1sI~
    }                                                              //~vc1sI~
//**********************************************************************//~1529I~
//*EditText Change watcher                                        //~1529I~//~1810R~
//**********************************************************************//~1529I~
    class ListTextWatcher implements TextWatcher                   //~1529I~
    {                                                              //~1529I~
    	ListData data;                                             //~1529I~
        TextView textView;                                         //~1529I~//~1824R~
        int pos;                                                   //~1530I~
//        //*********************                                  //~@@@@R~
//        public ListTextWatcher(int Ppos,TextView Ptextview,ListData Pdata)//~1530R~//~@@@@R~
//        {                                                        //~@@@@R~
//            pos=Ppos;                                            //~@@@@R~
//            data=Pdata;                                          //~@@@@R~
//            textview=Ptextview;                                  //~@@@@R~
//        }                                                        //~@@@@R~
        //*********************                                    //~@@@@I~
        public void setView(TextView Ptextview)                    //~@@@@I~
        {                                                          //~@@@@I~
            textView=Ptextview;                                    //~@@@@I~
        }                                                          //~@@@@I~
        //*********                                                //~1529I~
        public void beforeTextChanged(CharSequence s,int start,int count,int after)//~1529I~
        {                                                          //~1529I~
        	if (Dump.Y) Dump.println("beforeTextChanged view="+textView.toString()+",start="+start+",count="+count+",after="+after+",str="+s.toString());//~@@@@I~
        }                                                          //~1529I~
        public void onTextChanged(CharSequence s,int start,int before,int count)//~1529I~
        {                                                          //~1529I~
        	if (Dump.Y) Dump.println("onTextChanged view="+textView.toString()+",start="+start+",before="+before+",count="+count+",str="+s.toString());//~@@@@I~
        }                                                          //~1529I~
        public void afterTextChanged(Editable s)               //~1529I~
        {                                                          //~1529I~
        	try                                                    //~1601I~
            {                                                      //~1601I~
		        if (afterTextChangedEach(textView,s))              //~vc1sR~
                	return;                                        //~vc1sI~
//            	pos=textView.getId();                              //~vaxbR~
                ListViewTag tag=(ListViewTag)textView.getTag();    //~vaxbI~
                pos=tag.pos;                                       //~vaxbI~
				data=arrayData.get(pos);                           //~@@@@I~
                data.isUpdated=true;                               //~1601R~
                if (Dump.Y) Dump.println("afterTextChanged view="+textView.toString()+",pos="+pos);//~@@@@I~
                if (Dump.Y) Dump.println("afterTextChanged key1="+Integer.toHexString(data.key1)+",str="+s.toString());//~1601R~
//                View dataview=listView.getChildAt(pos);          //~@@@@R~
//                if (Dump.Y) Dump.println("lineview="+(dataview==null?"null":dataview.toString()));//~@@@@R~
                cursorpos=pos;                                     //~1601R~
    //          textview.requestFocus();                           //~1601R~//~1824R~
    //          textview.invalidate();                             //~1601R~//~1824R~
    //          adapter.notifyDataSetChanged();                    //~1601R~
                textView.setBackgroundColor(COLOR_BGFOCUS);        //~1601R~//~1824R~
                textView.setTextColor(COLOR_FG);                   //~1601R~//~1824R~
                data.status=DATA_NORMAL;                           //~1601R~
                AxeList.this.onTextChanged(pos,textView,s.toString());//~@@@@I~
			    if (Dump.Y) Dump.println("AxeList.afterTextChanged cursorpos="+cursorpos);//~vc1sI~
//                invalidate(); //required because focus dose not change by typing//~@@@@R~
            }                                                      //~1601I~
            catch(Exception e)                                     //~1601I~
            {                                                      //~1601I~
                Dump.println(e,"AexeList:AfterTextChange");        //~@@@@I~
            }                                                      //~1601I~
        }                                                          //~1529I~

    }//inner class                                                 //~1529I~
    public void onTextChanged(int Ppos,TextView Pview,String Pstr) //~@@@@I~
    {                                                              //~@@@@I~
//override it                                                      //~@@@@I~
    }                                                              //~@@@@I~
    public boolean afterTextChangedEach(TextView PtextView,Editable Peditable)//~vc1sR~
    {                                                              //~vc1sI~
//override it and return true                                      //~vc1sI~
		return false;                                              //~vc1sI~
    }                                                              //~vc1sI~
////**********************************************************************//~1601R~
////*detect Enter Key                                              //~1601R~
////**********************************************************************//~1601R~
//    class EditorActionListener implements OnEditorActionListener //~1601R~
//    {                                                            //~1601R~
//        ListData data;                                           //~1601R~
//        EditText editText;                                       //~1601R~
//        //*********************                                  //~1601R~
//        public EditorActionListener()                            //~1601R~
//        {                                                        //~1601R~
//        }                                                        //~1601R~
//        //*********                                              //~1601R~
//        public boolean onEditorAction(TextView Pview,int PactionId,KeyEvent Pevent)//~1601R~
//        {                                                        //~1601R~
//            if (Dump.Y) Dump.println("OnEditorAction actionid="+PactionId+",view="+Pview.toString());//~1601R~
//            if (Dump.Y) if (Pevent==null) Dump.println("OnEditorAction keyevent=null");//~1601R~
//                        else Dump.println("OnEditorAction keyevent action="+Pevent.getAction()+",key="+Integer.toHexString(Pevent.getUnicodeChar()));//~1601R~
//            return true;                                         //~1601R~
//        }                                                        //~1601R~
//    }//inner class                                               //~1601R~
////**********************************************************************//~1601R~
////*itemclicklistener                                             //~1601R~
////**********************************************************************//~1601R~
//    class ListItemClickListener implements OnItemClickListener   //~1601R~
//    {                                                            //~1601R~
//        public void onItemClick(AdapterView<?> Plistview,View Ptextview,int Ppos,long Pid)//~1601R~
//        {                                                              //~v@@@I~//~1601R~
//            if (Dump.Y) Dump.println("List OnItemClick pos="+Ppos);                //~v@@@R~//~1601R~
//                                                                 //~1601R~
//            if (Dump.Y) Dump.println("Listview OnItemClick getCheckedItemPos="+Plistview.getSelectedItemPosition());//~1601R~
//            if (Dump.Y) Dump.println("Listview OnItemClick listview isFocusable="+Plistview.isFocusable());//~1601R~
//            if (Dump.Y) Dump.println("Listview OnItemClick listview isFocusableInTouchMode="+Plistview.isFocusableInTouchMode());//~1601R~
//            if (Dump.Y) Dump.println("Listview OnItemClick textview isInTouchMode="+Ptextview.isInTouchMode());//~1601R~
//            Plistview.requestFocusFromTouch();                   //~1601R~
//            if (Dump.Y) Dump.println("Listview OnItemClick listview isFocusable="+Plistview.isFocusable());//~1601R~
//            if (Dump.Y) Dump.println("Listview OnItemClick listview isFocusableInTouchMode="+Plistview.isFocusableInTouchMode());//~1601R~
//            if (Dump.Y) Dump.println("Listview OnItemClick textview isInTouchMode="+Ptextview.isInTouchMode());//~1601R~
////*how avoid listview scroll by setSelectionAfterHeader() after setSelection()?//~1601R~
//            cursorpos=Ppos;                                      //~1601R~
//        }                                                        //~1601R~
//                                                                 //~1601R~
//    }//inner class                                               //~1601R~
//**********************************************************************//~1531I~
////*itemLongClickListener is not work for not simple listview     //~1601R~
////**********************************************************************//~1601R~
//    class LongClickListener implements OnLongClickListener       //~1601R~
//    {                                                            //~1601R~
//        public boolean onLongClick(View PeditText)               //~1601R~
//        {                                                        //~1601R~
//            ListData item=null;                                  //~1601R~
//            int ii,pos;                                          //~1601R~
//        //***************                                        //~1601R~
//            if (Dump.Y) Dump.println("LongClickListener view="+PeditText.toString());//~1601R~
//            int ctr=arrayData.size();                            //~1601R~
//            for (ii=0;ii<ctr;ii++)                               //~1601R~
//            {                                                    //~1601R~
//                item=arrayData.get(ii);                          //~1601R~
//                if (PeditText==item.editText)                    //~1601R~
//                    break;                                       //~1601R~
//            }                                                    //~1601R~
//            if (Dump.Y) Dump.println("LongClickListener search pos="+ii+"/"+ctr);//~1601R~
//            if (ii<ctr)                                          //~1601R~
//            {                                                    //~1601R~
//                pos=ii;                                          //~1601R~
//                AxeDialog.selectExtkey(pos,item.key2,item.editText);//~1601R~
//            }                                                    //~1601R~
//            return true;                                         //~1601R~
//        }                                                        //~1601R~

//    }//inner class                                               //~1601R~
//**********************************************************************//~1530R~
//*item selected listener not by touch                             //~1530R~//~1809R~
//**********************************************************************//~1530R~
    class ListItemSelectedListener implements OnItemSelectedListener//~1530R~
    {                                                              //~1530R~
    	AxeList parent;                                            //~1824I~
	    public ListItemSelectedListener(AxeList axelist)           //~1824I~
        {                                                          //~1824I~
        	parent=axelist;                                        //~1824I~
        }                                                          //~1824I~
        public void onItemSelected(AdapterView<?> Plistview,View Ptextview,int Ppos,long Pid)//~1530R~
        {                                                          //~1530R~
            if (Dump.Y) Dump.println("List OnItemSelected pos="+Ppos);//~1530R~
            try                                                    //~@@@@I~
            {                                                      //~@@@@I~
                cursorpos=Ppos;                                    //~@@@@R~
			    if (Dump.Y) Dump.println("AxeList.onItemSelected cursorpos="+cursorpos);//~vc1sI~
    //            listView.setItemChecked(Ppos,true); //call getView();setSelection() may move cursor//~@@@@R~
                ListData item=arrayData.get(Ppos);                 //~@@@@R~
                TextView v2=(TextView)((ViewGroup)Ptextview).getChildAt(1);//~@@@@I~
                if (v2!=null)                                      //~@@@@I~
                {                                                  //~@@@@I~
					v2.requestFocus();                             //~@@@@I~
	                if (Dump.Y) Dump.println("List OnItemSelected pos="+Ppos+",requestfocus v2="+v2.toString());//~@@@@I~
                }                                                  //~@@@@I~
                parent.onItemSelected(Ppos,item);                  //~@@@@R~
            }                                                      //~@@@@I~
            catch(Exception e)                                     //~@@@@I~
            {                                                      //~@@@@I~
                Dump.println(e,"AexeList:OnItemSelected");         //~@@@@I~
            }                                                      //~@@@@I~
        }                                                          //~1530R~
        public void onNothingSelected(AdapterView<?> arg0) {       //~1530R~
            if (Dump.Y) Dump.println("List OnItemSelected Nothing");//~1530R~
        }                                                          //~1530R~
    }//inner class                                                 //~1530R~
    public void onItemSelected(int Ppos,ListData Pitem)            //~1824R~
    {                                                              //~1824I~
//*overided by TerminalButton                                      //~1824I~
    }                                                              //~1824I~
//**********************************************************************//~1816I~
//*item selected listener not by touch                             //~1816I~
//**********************************************************************//~1816I~
    class ItemClickListener implements OnItemClickListener         //~1816R~
    {                                                              //~1816I~
    	@Override                                                  //~1816I~
        public void onItemClick(AdapterView<?> Plistview,View Ptextview,int Ppos,long Pid)//~1816I~
        {                                                          //~1816I~
            if (Dump.Y) Dump.println("List OnItemClick pos="+Ppos);//~1816I~
        	try                                                    //~1816I~
            {                                                      //~1816I~
                cursorpos=Ppos;                                    //~1816R~
                ListData item=arrayData.get(Ppos);                 //~1816R~
//              onItemClicked(Ppos,item);                          //~vaxbR~
                onItemClickedLine(Ptextview,Ppos,item);            //~vaxbI~
			    if (Dump.Y) Dump.println("AxeList.onItemSClick cursorpos="+cursorpos);//~vc1sI~
            }                                                      //~1816I~
            catch(Exception e)                                     //~1816I~
            {                                                      //~1816I~
                Dump.println(e,"AexeList:OnItemClick");            //~1816I~
            }                                                      //~1816I~
        }                                                          //~1816I~
    }//inner class                                                 //~1816I~
    public void onItemClickedLine(View Plineview,int Ppos,ListData Pitem)//~vaxbI~
    {                                                              //~vaxbI~
	    onItemClicked(Ppos,Pitem);                                 //~vaxbI~
    }                                                              //~vaxbI~
    public void onItemClicked(int Ppos,ListData Pitem)             //~1816R~
    {                                                              //~1816I~
//*overided by Shortcut,TerminalButton                             //~1816I~
    }                                                              //~1816I~
////**********************************************************************//~1604R~
////*itemcselected listener not by touch                           //~1604R~
////**********************************************************************//~1604R~
//    class SpinnerItemSelectedListener implements OnItemSelectedListener//~1604R~
//    {                                                            //~1604R~
//        public void onItemSelected(AdapterView<?> Plistview,View Ptextview,int Ppos,long Pid)//~1604R~
//        {                                                        //~1604R~
//            if (Dump.Y) Dump.println("Spinner OnItemSelected pos="+Ppos);//~1604R~
//            cursorpos=Ppos;                                      //~1604R~
//        }                                                        //~1604R~
//        public void onNothingSelected(AdapterView<?> arg0) {     //~1604R~
//            if (Dump.Y) Dump.println("Spinner OnItemSelected Nothing");//~1604R~
//            cursorpos=INVALID_POS;                               //~1604R~
//        }                                                        //~1604R~
//    }//inner class                                               //~1604R~
////**********************************************************************//~1601R~
////*itemclicklistener                                             //~1601R~
////**********************************************************************//~1601R~
//    class ListTouchListener implements OnTouchListener           //~1601R~
//    {                                                            //~1601R~
//        public boolean onTouch(View view,MotionEvent event)      //~1601R~
//        {                                                        //~1601R~
//            if (Dump.Y) Dump.println("List OnTouch");            //~1601R~
//            return false;                                              //~@@@@I~//~1601R~
//        }                                                              //~0914I~//~1601R~
//    }//ListTouchListener                                         //~1601R~
//**********************************************************************//~1818I~
//*update arrayData by reset button(Shiftkey,KbdLayout)            //~@@@@I~
//**********************************************************************//~1818I~
	public void updateData(int[] Pnewtbl)                          //~1818R~
    {                                                              //~1818I~
        int newkey2,listsz;                                        //~1818I~
        TextView v2;                                               //~1818I~
        ListData data;                                             //~1818I~
    //***********                                                  //~1818I~
        listsz=arrayData.size();                                   //~1818I~
        for (int ii=0;ii<listsz;ii++)                              //~1818I~
        {                                                          //~1818I~
            data=arrayData.get(ii);                                //~1818I~
            newkey2=Pnewtbl[ii];                                   //~1818I~
	        if (Dump.Y) Dump.println("AxeList updateData achild at "+ii+",view="+listView.getChildAt(ii).toString());//~@@@@I~
            v2=data.textView2;                                     //~1818I~
            if (v2==null)	//not displayed yet                    //~1818I~
            	continue;                                          //~1818I~
            if (data.key2==newkey2)                                //~1818I~
            	continue;                                          //~1818I~
            data.isUpdated=true;                                   //~1818I~
	        if (Dump.Y) Dump.println("AxeList updateData key1="+Integer.toHexString(data.key1)+",old="+Integer.toHexString(data.key2)+",new="+Integer.toHexString(newkey2));//~1818I~
            data.key2=newkey2;                                     //~1818I~
        	String str=getKeyname(ii,2,newkey2,""/*set if err*/);     //~1818I~//~1A29R~
//            if (editableRow || data.isEditable)    //editable  //~1A29I~//~@@@@R~
//                ((EditText)v2).setText(str,BufferType.EDITABLE);    //popup IM//~@@@@R~
//            else                                                 //~@@@@R~
//                v2.setText(str);                               //~1818I~//~@@@@R~
           	v2.setText(str);                                       //~@@@@I~
        }                                                          //~1818I~
    	adapter.notifyDataSetChanged();                            //~1818I~
    }//updateData                                                  //~1818I~
//**********************************************************************//~1529I~
//*check update and save to profile at dialog close                //~1602R~
//**********************************************************************//~1529I~
	public boolean saveUpdate()                                    //~1602R~
    {                                                              //~1529I~
    	boolean rc=true;                                           //~1529I~
        TextView v2;                                               //~1816R~
        String value;                                              //~1529I~
        int intvalue;                                              //~1529I~
        ListData data;                                             //~1530R~
        int [] newtbl;                                             //~1531I~
        boolean swtermbtn;                                         //~1810I~
    //***********                                                  //~1529I~
        int listsz=arrayData.size();                               //~1529R~//~1810M~
        if (focusPos!=INVALID_POS && focusPos<listsz)	//last focused entry//~@@@@I~
        {                                                          //~@@@@I~
        	chkInput(focusPos,focusView1,focusView2);              //~@@@@I~
        }                                                          //~@@@@I~
    	newtbl=new int[listsz];                        //~1531R~   //~1810R~
        Arrays.fill(newtbl,AxeKey.KEYVALUE_ERR);                   //~1531R~
        for (int ii=0;ii<listsz;ii++)                              //~1529I~
        {                                                          //~1529I~
            data=arrayData.get(ii);                                //~1530R~
            if (!data.isUpdated)                                   //~1531I~
            	continue;                                          //~1531I~
//            v2=data.textView2;                                   //~@@@@R~
//            if (v2==null)   //not displayed yet                  //~@@@@R~
//                continue;                                        //~@@@@R~
//            value=v2.getText().toString();                       //~@@@@R~
//            if (Dump.Y) Dump.println("AxeList line updated key="+Integer.toHexString(data.key1)+",input="+value);//~@@@@R~
//          intvalue=AxeKey.chkInput(dialogId,value);              //~1612R~
//            if (this instanceof AxeLstKbdlayout)                 //~1920R~
//                intvalue=chkInputValue(ii,value,false/*no toast*/);//~1920R~
//            else                                                 //~1920R~
//                intvalue=chkInputValue(ii,value,false/*no toast*/);   //~1920R~//~@@@@R~
            intvalue=data.key2;                                    //~@@@@I~
            if (Dump.Y) Dump.println("AxeList line updated pos="+ii+",key="+Integer.toHexString(data.key1)+",input="+intvalue);//~@@@@I~
//            if (intvalue==AxeKey.KEYVALUE_ERR) //-1:null/space   //~@@@@R~
            if (data.status==DATA_ERR)                             //~@@@@I~
            {                                                      //~1531R~
//                data.status=DATA_ERR;                            //~@@@@R~
//                v2.setTextColor(COLOR_FGERR);                    //~@@@@R~
                rc=false;                                          //~1531R~
            }                                                      //~1531R~
            else                                                   //~1531R~
            {                                                      //~1531R~
//                data.status=DATA_NORMAL;                         //~@@@@R~
//                data.key2=intvalue;                              //~@@@@R~
                if (this instanceof AxeLstTermBtn)                 //~1810I~
                	newtbl[data.key1]=intvalue;                    //~1810I~
                else                                               //~1920I~
                if (this instanceof AxeLstKbdLayout)               //~1920I~
                	newtbl[data.key1]=intvalue;                    //~1920I~
                else                                               //~1810I~
                	newtbl[data.key1-AxeKey.KEYTBL_START]=intvalue;           //~1531R~//~1810R~
            }                                                      //~1531R~
        }                                                          //~1529I~
        if (Dump.Y) Dump.println("AxeList line chk updated rc="+rc);//~1531I~
        if (!rc)	//err,not dismiss                              //~1531I~
        {                                                          //~1531I~
        	invalidate();                                          //~1531I~
            Utils.showToastLong(R.string.Err_DialogContainsError); //~1531I~
        }                                                          //~1531I~
        else                                                       //~1531I~
        {                                                          //~1531I~
//      	AxeG.axeKey.updateTbl(dialogId,newtbl);                //~1612R~
        	updateTbl(newtbl);                                     //~1612I~
        }                                                          //~1531I~
        return rc;                                                 //~1529I~
    }//saveupdate                                                 //~1531R~//~1809R~
//**************************************                           //~1612I~
    public void updateTbl(int[] Pnewtbl)                           //~1612I~
    {                                                              //~1612I~
//overriden by extender                                            //~1612I~
    }                                                              //~1612I~
//**************************************                           //~1531I~
	public void invalidate()                                     //~1531I~
    {                                                              //~1531I~
    	listView.invalidate();                                     //~1601R~
    }                                                              //~1531I~
//**************************************                           //~1824I~
	public void notifyDataChanged()                                //~1824I~
    {                                                              //~1824I~
		if (Dump.Y) Dump.println("AxeList notifyDataChanged arraydata size="+arrayData.size());//~@@@@I~
    	adapter.notifyDataSetChanged();                            //~1824I~
    }                                                              //~1824I~
//**************************************                           //~1531I~
//*chkInput when focus lost                                        //~@@@@I~
//**************************************                           //~@@@@I~
	public boolean chkInput(int Ppos,TextView Pview1,TextView Pview2)//~@@@@I~
    {                                                              //~1531I~
    	boolean rc=true;                                           //~1531R~
        int intvalue;                                              //~1531I~
        ListData data;                                             //~1A29I~
        String str;                                                //~@@@@I~
    //*******************                                          //~1531I~
		data=arrayData.get(Ppos);                                  //~1A29I~
        data.status=DATA_NORMAL;                                   //~@@@@I~
    	String value=Pview2.getText().toString();                  //~1816R~
//      intvalue=AxeKey.chkInput(dialogId,value);                  //~1612R~
        intvalue=chkInputValue(Ppos,value,true/*toast*/);               //~1613R~//~1A29R~
        switch(intvalue)
        {//~1531I~
        case AxeKey.KEYVALUE_ERR:                                  //~1531I~
        	rc=false;                                              //~1531I~
        	Pview2.setTextColor(COLOR_FGERR);                      //~1816R~
//            if (dialogId==AxeDlgShiftList.DIALOG_SHIFTKEY_MAP)   //~1612R~
//                msgid=R.string.Err_InputShiftkeyData;            //~1612R~
//            else                                                 //~1612R~
//            if (dialogId==AxeDlgAltGrList.DIALOG_ALTGR_MAP)      //~1612R~
//                msgid=R.string.Err_InputAltGrData;               //~1612R~
//            else                                                 //~1612R~
//                msgid=R.string.Err_InputShortcutData;            //~1612R~
//            Utils.showToast(msgid);                              //~1612R~
            data.status=DATA_ERR;                                  //~@@@@I~
            break;                                                 //~1531I~
        case AxeKey.KEYVALUE_NOTDEF:                               //~1531I~
            break;                                                 //~1531I~
        case AxeKey.KEYVALUE_KEY1:                                 //~@@@@I~
            str=getKeyname(Ppos,1,intvalue/*not used*/,value/*set if err*/);//~@@@@I~
            Pview1.setText(str);                                   //~@@@@I~
            Pview2.setText("");                                    //~@@@@I~
            if (Dump.Y) Dump.println("AxeList chkInput settext key1 str="+str);//~@@@@I~
            break;                                                 //~@@@@I~
        default:                                                   //~1531I~
        	data.key2=intvalue;                                    //~@@@@I~
        	str=getKeyname(Ppos,2,intvalue,value/*set if err*/);   //~1601R~//~@@@@R~
            if (Dump.Y) Dump.println("AxeList chkInput settext str="+str);//~1814I~
//            if (editableRow || data.isEditable)    //editable  //~1A29I~//~@@@@R~
//                ((EditText)Pview2).setText(str,BufferType.EDITABLE);    //popup IM//~@@@@R~
//            else                                                 //~@@@@R~
            String old=Pview2.getText().toString();                //~@@@@I~
            if (!str.equals(old))	//if filtered(maxlen=0) this clears v2 and also redundant call to textchanged//~@@@@I~
	            Pview2.setText(str);                                   //~1816R~//~1A29R~
        }                                                          //~1531I~
        return rc;                                                 //~1531I~
    }                                                              //~1531I~
//******************                                               //~1612I~
    public void showInputError()                                   //~1612I~
    {                                                              //~1612I~
//overrided by extender                                            //~1612I~//~1810R~
    }                                                              //~1612I~
//******************                                               //~1531I~
//    private String getKeyname(int Pfldid,int Pkey,String Perr)   //~1612R~
//    {                                                            //~1612R~
//        if (Pfldid==1 || dialogId==AxeDlgShiftList.DIALOG_SHIFTKEY_MAP)//~1612R~
//            return AxeKey.keyToString(Pkey,Perr);                //~1612R~
//        if (Pfldid==1 || dialogId==AxeDlgAltGrList.DIALOG_ALTGR_MAP)//~1612R~
//            return AxeKey.keyToString(Pkey,Perr);                //~1612R~
//        return AxeKey.extendedkeyToString(Pkey,Perr);            //~1612R~
//    }                                                            //~1612R~
    public String getKeyname(int Ppos,int Pfldid,int Pkey,String Perr)     //~1612I~//~1A29R~
    {                                                              //~1612I~
//overrided by extender                                            //~1810R~
    	return "";//~1612I~
    }                                                              //~1612I~
    //****************                                             //~vaxbI~
    public boolean setLineViewSub(View Pconvertview,int Ppos,TextView Pview1,TextView Pview2,ListData Pdata)//~vaxbI~
    {                                                              //~vaxbI~
//override this                                                    //~vaxbI~
			return false;                                          //~vaxbI~
    }                                                              //~vaxbI~
    //****************                                             //~vaxbI~
    protected int chkInputValue(int Ppos,String Pvalue,boolean Ptoast)         //~1613R~//~1A29R~//~vc1wR~
    {                                                              //~1612I~
//overrided by extender                                            //~1612I~//~1810R~
        return AxeKey.KEYVALUE_ERR;                                //~1612I~
    }                                                              //~1612I~
//    public int chkInputValue(int Pidx,String Pvalue,boolean Ptoast)//~1920R~
//    {                                                            //~1920R~
////overrided by extender(AxeKbdLayout)                            //~1920R~
//        return AxeKey.KEYVALUE_ERR;                              //~1920R~
//    }                                                            //~1920R~
    public void onSpinnerItemSelected(int Ppos)                    //~1810I~//~vc1sR~
    {                                                              //~1810I~//~vc1sR~
//overrided by extender(AxeLstTermBtn)                             //~1810I~//~vc1sR~
    }                                                              //~1810I~//~vc1sR~
//    public void drawLineChange(int Poldpos,int Pnewpos)          //~@@@@R~
//    {                                                            //~@@@@R~
//        int listsz,pos;                                          //~@@@@R~
//        View v;                                                  //~@@@@R~
//        ListData data;                                           //~@@@@R~
//    //********************                                       //~@@@@R~
//        listsz=arrayData.size();                                 //~@@@@R~
//        pos=Poldpos;                                             //~@@@@R~
//        for (int ii=0;ii<2;ii++)                                 //~@@@@R~
//        {                                                        //~@@@@R~
//            if (pos>=0 && pos<listsz)                            //~@@@@R~
//            {                                                    //~@@@@R~
//                data=arrayData.get(pos);                         //~@@@@R~
//                v=data.dataView;                                 //~@@@@R~
//                if (v!=null)                                     //~@@@@R~
//                {                                                //~@@@@R~
//                    v.invalidate();                              //~@@@@R~
//                    if (Dump.Y) Dump.println("drawLineChange pos="+pos);//~@@@@R~
//                }                                                //~@@@@R~
//            }                                                    //~@@@@R~
//            pos=Pnewpos;                                         //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~
}//class                                                           //~1528I~
