//*CID://+dateR~: update#= 160;                                    //~1107R~
//**********************************************************************//~1107I~
//*AlerDialog                                                      //~1527R~
//**********************************************************************//~1107I~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1527R~

import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;



//**********************************************************************//~1107I~
public class AxeDlgExtkey extends AxeDialog                        //~1602R~
{                                                                  //~0914I~
	public  static final int DIALOG_EXTKEY        =R.layout.dialogextkey;//~1531I~
	private static final int TITLE_EXTKEY         =R.string.DialogTitle_Extkey;//~1531I~
                                                                   //~1601I~
	private static final int VIEWID_CB_SHIFT      =R.id.Shift;     //~1601I~
	private static final int VIEWID_CB_CTL        =R.id.Control;   //~1601I~
	private static final int VIEWID_CB_ALT        =R.id.Alt;       //~1601I~
	private static final int VIEWID_CB_UNICODE    =R.id.Unicode;   //~1601I~
	private static final int VIEWID_TV_UNICODE    =R.id.UnicodeValue;//~1601I~
                                                                   private ViewGroup layoutView;                                         //~1528I~//~1809R~
	private AxeList axeList;                                       //~1809R~
	private AxeSpinner axeSpinner;//~1529I~                        //~1809R~
    private ListData listData;                                     //~1601R~//~1809R~
//**********************************                               //~1211I~
	public AxeDlgExtkey()                                          //~1602R~
    {                                                              //~1211I~
    	super(DIALOG_EXTKEY);                                      //~1602R~
    }                                                              //~1602I~
    public static AxeDlgExtkey show(ListData PlistData,AxeList Paxelist)
    {                                                              //~1602I~
    	AxeDlgExtkey axeDialog=new AxeDlgExtkey();                 //~1602R~
    	axeDialog.listData=PlistData;                              //~1602I~
    	axeDialog.axeList=Paxelist;                                //~1817I~
        String title=Utils.getResourceString(TITLE_EXTKEY)+"\""+AxeKey.keyToString(PlistData.key1,"??")+"\"";//~1602I~
		axeDialog.showDialog(title);                               //~1602I~
        return axeDialog;                                          //~1602I~
    }                                                              //~1211I~
//**********************************
//*setup dialog                                                    //~1608R~
//**********************************                               //~1602M~
	@Override
    protected void setupDialogExtend(ViewGroup PlayoutView)          //~1602I~
    {                                                              //~1602M~
    	int mod,pos,extkey;                                        //~1602M~
        TextView textView2;                                         //~1602M~
        String editdata;                                           //~1602M~
    //*******************:                                         //~1602M~
    	layoutView=PlayoutView;                                    //~1602I~
        textView2=listData.textView2;                                //~1602M~
        editdata=textView2.getText().toString();                    //~1602M~
    	if (listData.isUpdated)                                    //~1602M~
            extkey=AxeKey.chkInputShortcut(editdata);              //~1602M~
        else                                                       //~1602M~
        	extkey=listData.key2;                                  //~1602M~
        switch(extkey)                                             //~1602M~
        {                                                          //~1602M~
        case AxeKey.KEYVALUE_ERR:                                  //~1602M~
        	mod=0;                                                 //~1602M~
            pos=-2;                                                //~1602M~
            break;                                                 //~1602M~
        case AxeKey.KEYVALUE_NOTDEF:                               //~1602M~
        	mod=0;                                                 //~1602M~
            pos=0;                                                 //~1602M~
            break;                                                 //~1602M~
        default:                                                   //~1602M~
        	mod=extkey;                                            //~1602M~
            pos=AxeKeyValue.srchExtendedkey(extkey);	//-1 if err//~1602M~
        }                                                          //~1602M~
        axeSpinner=AxeSpinner.create(DIALOG_EXTKEY,layoutView,AxeKeyValue.SkeyNameList);//~1604R~
	    setExtkeyOption(mod);                                      //~1602M~
        if (AxeKeyValue.isUnicodeKeyValue(mod))                    //~1602M~
        {                                                          //~1602M~
            editdata=Integer.toHexString(AxeKeyValue.getUnicodeKeyValue(mod));//~1602M~
            pos=-1;                                                //~1602M~
        }                                                          //~1602M~
        else                                                       //~1602M~
        {                                                          //~1602M~
            if (pos>=0)                                            //~1602M~
                axeSpinner.setSelection(pos);                  //~1602M~
		}                                                          //~1602M~
        if (pos<0)	//unicode or keyname err                       //~1602M~
        {                                                          //~1602M~
	    	TextView tv=(TextView)layoutView.findViewById(VIEWID_TV_UNICODE);//~1602M~
            tv.setText(editdata);                                  //~1602M~
            if (pos==-2)                                           //~1602M~
            	tv.setTextColor(AxeList.COLOR_FGERR);              //~1602M~
        }                                                          //~1602M~
        setEditableUnicode(pos==-1);                               //~1602M~
	    setCheckBoxListener();                                     //~1602M~
    }                                                              //~1602M~
//*********************                                            //~1602I~
	private void setEditableUnicode(boolean Peditable)             //~1602I~
    {                                                              //~1602I~
	    EditText v=(EditText)layoutView.findViewById(VIEWID_TV_UNICODE);//~1602I~
        setEditTextEnable(v,Peditable);	//AxeDialog                //~1604I~
    }                                                              //~1602I~
//*****************                                                //~1602I~
//*CheckBox Lister                                                 //~1602I~
//*****************                                                //~1602I~
    private void setCheckBoxListener()                              //~1602I~
    {                                                              //~1602I~
    //************                                                 //~1602I~
        CheckBoxListener lfalse=this.new CheckBoxListener(false);  //~1602I~
        CheckBoxListener ltrue=this.new CheckBoxListener(true);     //~1602I~
    	((CheckBox)layoutView.findViewById(VIEWID_CB_UNICODE)).setOnClickListener(ltrue);//~1602I~
        ((CheckBox)layoutView.findViewById(VIEWID_CB_SHIFT)).setOnClickListener(lfalse);//~1602I~
        ((CheckBox)layoutView.findViewById(VIEWID_CB_CTL)).setOnClickListener(lfalse);//~1602I~
        ((CheckBox)layoutView.findViewById(VIEWID_CB_ALT)).setOnClickListener(lfalse);//~1602I~
    }                                                              //~1602I~
//********                                                         //~1602I~
    public class CheckBoxListener implements View.OnClickListener       //~1602I~
    {                                                              //~1602I~
    	boolean isUnicode;                                         //~1602I~
        public CheckBoxListener(boolean PisUnicode)                  //~1602I~
        {                                                          //~1602I~
        	isUnicode=PisUnicode;                                  //~1602I~
        }                                                          //~1602I~
        public void onClick(View Pview)                               //~1602I~
        {                                                          //~1602I~
        	boolean isChecked=((CheckBox)Pview).isChecked();       //~1602I~
	        if (isUnicode)                                         //~1602I~
            {                                                      //~1602I~
				setEditableUnicode(isChecked);                      //~1602I~
                if (isChecked)                                     //~1602I~
                {                                                  //~1602I~
                	((CheckBox)layoutView.findViewById(VIEWID_CB_SHIFT)).setChecked(false);//~1602I~
                	((CheckBox)layoutView.findViewById(VIEWID_CB_CTL)).setChecked(false);//~1602I~
                	((CheckBox)layoutView.findViewById(VIEWID_CB_ALT)).setChecked(false);//~1602I~
                }                                                  //~1602I~
            }                                                      //~1602I~
            else                                                   //~1602I~
            {                                                      //~1602I~
				setEditableUnicode(!isChecked);                     //~1602I~
                ((CheckBox)layoutView.findViewById(VIEWID_CB_UNICODE)).setChecked(false);//~1602I~
            }                                                      //~1602I~
        }                                                          //~1602I~
    }                                                              //~1602I~
//************************************************                 //~1531I~
//*dialog close                                                    //~1608I~
//************************************************                 //~1608I~
	@Override
    protected boolean onClickClose()                               //+1821R~
    {                                                              //~1531I~
    	boolean rc=true;	//dismiss                              //~1531I~
        int mod,intval;                                            //+1821R~
        String value="";                                           //~1601I~
    //**************                                               //~1601I~
        mod=getExtkeyOption();                                     //+1821R~
        if (AxeKeyValue.isUnicodeKeyValue(mod))                    //+1821R~
        {                                                          //+1821R~
            TextView tv=(TextView)layoutView.findViewById(VIEWID_TV_UNICODE);//+1821R~
            String str=tv.getText().toString();                    //+1821R~
            value=str;                                             //+1821R~
            int unicode=Utils.hexstrToNum(str,AxeKey.KEYVALUE_ERR);//+1821R~
            if (unicode==AxeKey.KEYVALUE_ERR)                      //+1821R~
            {                                                      //+1821R~
                Utils.showToastLong(R.string.Err_InvalidUnicode);  //+1821R~
                return false;   //no dismiss                       //+1821R~
            }                                                      //+1821R~
            intval=AxeKeyValue.setUnicodeId(unicode);              //+1821R~
        }                                                          //+1821R~
        else                                                       //+1821R~
        {                                                          //+1821R~
            int pos=axeSpinner.getSelectedPos();                   //+1821R~
            if (pos<0)  //no selection                             //+1821R~
                pos=0;                                             //+1821R~
            KeyData kd=AxeKeyValue.getKeyData(pos);                //+1821R~
            intval=kd.keyGDK;                                      //+1821R~
            if (intval!=AxeKey.KEYVALUE_NOTDEF)                    //+1821R~
            {                                                      //+1821R~
                value=AxeKeyValue.modifierToString(mod,kd.keyName);//+1821R~
            }                                                      //+1821R~
        }                                                          //+1821R~
    	updateItem(intval,value);                          //+1821I~
 
        return rc;                                                 //~1531I~
    }                                                              //~1531I~
//************************************************                 //+1821I~
//*dialog close                                                    //+1821I~
//************************************************                 //+1821I~
	@Override                                                      //+1821I~
    protected boolean onClickOther(int PbuttonId)                  //+1821I~
    {                                                              //+1821I~
    	boolean rc=true;	//dismiss                              //+1821I~
        int intval=AxeKey.KEYVALUE_NOTDEF;                         //+1821I~
        String value="";                                           //+1821I~
    //**************                                               //+1821I~
        switch(PbuttonId)                                          //+1821I~
        {                                                          //+1821I~
        case R.id.Clear:                                           //+1821I~
	    	updateItem(intval,value);                      //+1821I~
            break;                                                 //+1821I~
        }                                                          //+1821I~
        return rc;                                                 //+1821I~
    }                                                              //+1821I~
    private void updateItem(int Pintvalue,String Pstrvalue)//+1821I~
    {                                                              //+1821I~
        TextView textView2;                                        //+1821I~
    //**************                                               //+1821I~
        listData.isUpdated=true;                                      //+1821I~
        listData.key2=Pintvalue;                                      //+1821I~
        textView2=listData.textView2;                              //+1821I~
        textView2.setText(Pstrvalue);                              //+1821I~
        invalidateListView();	//background redraw                //+1821I~
    }                                                              //+1821I~
//*****************                                                //~1817I~
    private void invalidateListView()                                //~1817I~
    {                                                              //~1817I~
    //************                                                 //~1817I~
		axeList.adapter.notifyDataSetChanged();                    //~1817I~
    }                                                              //~1817I~
//*****************                                                //~1602I~
    private int getExtkeyOption()                                  //~1601I~
    {                                                              //~1601I~
    	CheckBox cb;                                               //~1601I~
        int mod=0;                                                 //~1601I~
    //************                                                 //~1601I~
    	cb=(CheckBox)layoutView.findViewById(VIEWID_CB_UNICODE);   //~1601I~
        if (cb.isChecked())                                        //~1601I~
        {                                                          //~1601I~
            mod=AxeKeyValue.setUnicodeId(mod);                     //~1601I~
        }                                                          //~1601I~
        else                                                       //~1601I~
        {                                                          //~1601I~
            cb=(CheckBox)layoutView.findViewById(VIEWID_CB_SHIFT); //~1601R~
            if (cb.isChecked())                                    //~1601R~
                mod|=AxeKeyValue.KBF_SHIFT;                     //~1601R~
            cb=(CheckBox)layoutView.findViewById(VIEWID_CB_CTL);   //~1601R~
            if (cb.isChecked())                                    //~1601R~
                mod|=AxeKeyValue.KBF_CONTROL;                   //~1601R~
            cb=(CheckBox)layoutView.findViewById(VIEWID_CB_ALT);   //~1601R~
            if (cb.isChecked())                                    //~1601R~
                mod|=AxeKeyValue.KBF_ALT;                       //~1601R~
        }                                                          //~1601I~
        return mod;
    }                                                              //~1601I~
    private void setExtkeyOption(int Pmod)                          //~1601I~
    {                                                              //~1601I~
    	CheckBox cb;                                               //~1601I~                                            //~1601I~
        boolean isunicode;                                         //~1601I~
    //************                                                 //~1601I~
    	cb=(CheckBox)layoutView.findViewById(VIEWID_CB_UNICODE);   //~1601I~
        isunicode=AxeKeyValue.isUnicodeKeyValue(Pmod);             //~1601I~
        cb.setChecked(isunicode);                                  //~1601I~
        if (!isunicode)                                            //~1602R~
        {                                                          //~1601I~
            cb=(CheckBox)layoutView.findViewById(VIEWID_CB_SHIFT); //~1601I~
            cb.setChecked((Pmod & AxeKeyValue.KBF_SHIFT)!=0);   //~1601I~
            cb=(CheckBox)layoutView.findViewById(VIEWID_CB_CTL);   //~1601I~
            cb.setChecked((Pmod & AxeKeyValue.KBF_CONTROL)!=0); //~1601I~
            cb=(CheckBox)layoutView.findViewById(VIEWID_CB_ALT);   //~1601I~
            cb.setChecked((Pmod & AxeKeyValue.KBF_ALT)!=0);     //~1601I~
        }                                                          //~1601I~
    }                                                              //~1601I~
}//class AxeDialog                                                 //~1528R~
