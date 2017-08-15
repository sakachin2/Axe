//*CID://+dateR~: update#= 167;                                    //~1107R~
//**********************************************************************//~1107I~
//*AlerDialog                                                      //~1527R~
//**********************************************************************//~1107I~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1527R~

import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

//**********************************************************************//~1107I~
public class AxeDlgBtnUpdate extends AxeDialog                     //~1604R~
{                                                                  //~0914I~
	public  static final int DIALOG_BTNUPDATE     =R.layout.dialogbuttonupdate;//~1604R~
	private static final int TITLE_BTNUPDATE      =R.string.DialogTitle_BtnUpdate;//~1604R~
                                                                   //~1604I~
	public  static final int VIEWID_CB_DELETE      =R.id.Delete;   //~1604R~
	public  static final int VIEWID_CB_REPLACE     =R.id.Replace;  //~1604R~
	public  static final int VIEWID_CB_INSERTAFTER =R.id.InsertAfter;//~1604R~
	public  static final int VIEWID_CB_INSERTBEFORE=R.id.InsertBefore;//~1604R~
	public  static final int VIEWID_CB_MOVE        =R.id.Move;     //~1604R~
                                                                   //~1604I~
	private static final int VIEWID_TV_MOVEVALUE   =R.id.MoveValue;//~1604I~
    private static final int[] chkboxidtbl={                       //~1604I~
                            VIEWID_CB_DELETE,                      //~1604I~
                            VIEWID_CB_REPLACE,                     //~1604I~
                            VIEWID_CB_INSERTAFTER,                 //~1604I~
                            VIEWID_CB_INSERTBEFORE,                //~1604I~
                            VIEWID_CB_MOVE                         //~1604I~
    						};                                     //~1604I~
    private static final int chkboxtblsz=chkboxidtbl.length; 
    private CheckBox[] chkboxViewTbl;                              //~1605R~
    private CheckBox chkboxViewMove;                               //~1605I~
                                                                   //~1604I~
	Dialog androidDialog;                                          //~1528I~
	ViewGroup layoutView;                                         //~1528I~
    AxeButtonAction action;                                        //~1604R~
    AxeButton button,newbtn;                                       //~1605R~
    int moveValue;;                                                //~1605R~
    EditText moveValueView; 
    AxeSpinner spinner;//~1604I~
    String[] spinnerData;                                          //~1613I~
//**********************************                               //~1211I~
	public AxeDlgBtnUpdate()                                       //~1604R~
    {                                                              //~1211I~
    	super(DIALOG_BTNUPDATE);                                   //~1604R~
        chkboxViewTbl=new CheckBox[chkboxtblsz];                               //~1604I~
    }                                                              //~1602I~
    public static AxeDlgBtnUpdate show(AxeButtonAction Paction)    //~1605R~
    {                                                              //~1602I~
    	AxeDlgBtnUpdate axeDialog=new AxeDlgBtnUpdate();           //~1604R~
    	axeDialog.action=Paction;                                  //~1604R~
        axeDialog.button=Paction.button;;                          //~1605R~
        String title=Utils.getResourceString(TITLE_BTNUPDATE)+"\""+Paction.button.getButtonName()+"\"";//~1604R~
		axeDialog.showDialog(title);                               //~1602I~
        return axeDialog;                                          //~1602I~
    }                                                              //~1211I~
//**********************************
//*override super                                                  //~1602I~
//**********************************                               //~1602M~
	@Override
    protected void setupDialogExtend(ViewGroup PlayoutView)          //~1602I~
    {                                                              //~1602M~
    	int pos;                                        //~1602M~
                                                                   //~1613I~
        //*******************:                                         //~1602M~
    	layoutView=PlayoutView;                                    //~1602I~
        spinnerData=createSpinnerData();                           //~1613I~
        spinner=AxeSpinner.create(DIALOG_BTNUPDATE,layoutView,spinnerData);//~1613R~
        pos=getInitialPos(button);                                 //~1613R~
        spinner.setSelection(pos);                          //~1604R~
	    setCheckBoxListener();                                     //~1602M~
    }                                                              //~1602M~
//*****************                                                //~1613I~
    private String[] createSpinnerData()                           //~1613I~
    {                                                              //~1613I~
    	int ctr1,ctr2;                                             //~1613I~
    //*************************                                    //~1613I~
        ctr1=AxeButton.BUTTON_IDMAX-1;	//skip Gone                        //~1613I~
        ctr2=AxeKeyValue.setUserKeyCandidate(null,0);	//get ctr  //~1613I~
        spinnerData=new String[ctr1+ctr2];                         //~1613I~
        System.arraycopy(AxeButton.buttonName,0,spinnerData,0,ctr1); //~1613I~
        AxeKeyValue.setUserKeyCandidate(spinnerData,ctr1);         //~1613R~
        return spinnerData;                                        //~1613I~
    }                                                              //~1613I~
//*****************                                                //~1613I~
    private int getInitialPos(AxeButton Pbutton)                   //~1613I~
    {                                                              //~1613I~
    	int pos,userGDK;                                           //~1613I~
    //*************************                                    //~1613I~
    	pos=Pbutton.buttonId;                                      //~1613I~
    	if (pos==AxeButton.BUTTON_USER)                            //~1613I~
        {                                                          //~1613I~
        	userGDK=Pbutton.userGDK;                               //~1613I~
	        pos=AxeKeyValue.getUserKeyPos(userGDK)+AxeButton.BUTTON_IDMAX-1;//~1613I~
        }                                                          //~1613I~
	    if (Dump.Y) Dump.println("getInitial pos="+pos);           //~1613I~
        return pos;                                                //~1613I~
    }                                                              //~1613I~
//*****************                                                //~1602I~
//*CheckBox Lister                                                 //~1602I~
//*****************                                                //~1602I~
    private void setCheckBoxListener()                              //~1602I~
    {                                                              //~1602I~
    //************                                                 //~1602I~
        CheckBoxListener l=this.new CheckBoxListener();            //~1604R~
        for (int ii=0;ii<chkboxtblsz;ii++)                         //~1604I~
        {                                                          //~1604I~
    		int id=chkboxidtbl[ii];                               //~1604R~
    		chkboxViewTbl[ii]=(CheckBox)layoutView.findViewById(id);//~1604I~
    		chkboxViewTbl[ii].setOnClickListener(l);               //~1604I~
            if (id==VIEWID_CB_MOVE)                                //~1605I~
            	chkboxViewMove=chkboxViewTbl[ii];                  //~1605I~
        }                                                          //~1604I~
	    moveValueView=(EditText)layoutView.findViewById(VIEWID_TV_MOVEVALUE);//~1604I~
        setEditTextEnable(moveValueView,false); //initialy disable //~1605I~
    }                                                              //~1602I~
//********                                                         //~1602I~
    public class CheckBoxListener implements View.OnClickListener       //~1602I~
    {                                                              //~1602I~
        public void onClick(View Pview)                               //~1602I~
        {                                                          //~1602I~
            if (((CheckBox)Pview).isChecked())	                   //~1604I~
            {                                                      //~1604I~
        		int viewId=Pview.getId();                          //~1604I~
                for (int ii=0;ii<chkboxtblsz;ii++)                  //~1604I~
                {                                                  //~1604I~
                    int id=chkboxidtbl[ii];                       //~1604I~
                    if (id!=viewId) //radio button func            //~1604I~
                        chkboxViewTbl[ii].setChecked(false);        //~1604I~
                }                                                  //~1604I~
            }                                                      //~1604I~
            setEditTextEnable(moveValueView,chkboxViewMove.isChecked());//~1605R~
        }                                                          //~1602I~
    }                                                              //~1602I~
//************************************************                 //~1531I~
//*dialog button ok/can/help                                       //+1923R~
//************************************************                 //~1604I~
	@Override                                                      //+1923I~
    protected boolean onClickHelp()                                //+1923I~
    {                                                              //+1923I~
    	showDialogHelp(R.string.HelpTitle_ButtonUpdate,R.string.Help_ButtonUpdate);//+1923I~
        return false;	//no dismiss                               //+1923I~
    }                                                              //+1923I~
//*****************                                                //+1923I~
	@Override
    protected boolean onClickClose()                               //~1821R~
    {                                                              //~1531I~
    	boolean rc;	//dismiss                                      //~1821R~
        int funcid;                                                //~1605I~
    //**************                                               //~1601I~
        newbtn=button;                                             //~1821R~
        if ((funcid=getBtnUpdateOption())<0)                       //~1821R~
            rc=false;                                              //~1821R~
        else                                                       //~1821I~
            rc=AxeG.axeButtonLayout.buttonUpdate(action,newbtn,funcid,moveValue);//~1821R~
        return rc;                                                 //~1531I~
    }                                                              //~1531I~
//*****************                                                //~1602I~
    private int getBtnUpdateOption()                               //~1605R~
    {                                                              //~1601I~
    	int funcid=-1;                                             //~1605I~
    //************                                                 //~1601I~
        for (int ii=0;ii<chkboxtblsz;ii++)                         //~1604I~
        {                                                          //~1604I~
        	if (chkboxViewTbl[ii].isChecked())                      //~1604I~
            {                                                      //~1604I~
            	funcid=ii;
        		break;                                             //~1604I~                                      //~1604I~
            }                                                      //~1604I~
        }                                                          //~1604I~
        if (funcid<0)                                              //~1604I~
        {                                                          //~1604I~
        	Utils.showToastLong(R.string.Err_BtnUpdate_NoneChecked);         //~1604I~
            return funcid;                                         //~1605R~
        }                                                          //~1604I~
        funcid=chkboxidtbl[funcid]; //viewid                       //~1604R~
        if (funcid==VIEWID_CB_MOVE)                                //~1604R~
        {                                                          //~1604I~
        	String str=moveValueView.getText().toString();           //~1604I~
            moveValue=Utils.strToNum(str,0/*default*/);            //~1604I~
            int mx=AxeButtonLayout.MAX_BUTTONS;                    //~1604I~
            if (moveValue<=-mx||moveValue>=mx||moveValue==0)       //~1604I~
            {                                                      //~1604I~
                Utils.showToastLong(R.string.Err_BtnUpdate_InvalidMoveValue);//~1604I~
                return -1;                                         //~1605R~
            }                                                      //~1604I~
        }                                                          //~1604I~
        else                                                       //~1605I~
        if (funcid!=VIEWID_CB_DELETE)                              //~1605I~
        {                                                          //~1605I~
            newbtn=getSelectedButtonKey();                         //~1613R~
        }                                                          //~1605I~
        return funcid;                                             //~1605R~
    }                                                              //~1601I~
    private AxeButton getSelectedButtonKey()                       //~1613I~
    {   
    	int pos,btnid;//~1613I~
        KeyData kd;                                                //~1613I~
	//******************************
    	btnid=spinner.getSelectedPos();                        //~1613I~
        if (btnid<0)	//no selection                             //~1613I~
          	btnid=0;                                               //~1613I~
	    if (btnid<AxeButton.BUTTON_IDMAX-1)                        //~1613I~
            newbtn=AxeButton.buttonTypeTbl[btnid];                 //~1613I~
        else                                                       //~1613I~
        {                                                          //~1613I~
        	pos=btnid-(AxeButton.BUTTON_IDMAX-1);                  //~1613I~
            kd=AxeKeyValue.getUserGDK(pos);                        //~1613R~
            newbtn=AxeButton.buttonTypeTbl[AxeButton.BUTTON_USER];           //~1613I~
            newbtn.setUserGDK(kd.keyGDK);                            //~1613I~
            newbtn.label=newbtn.name=kd.keyName;
            if (Dump.Y)Dump.println("getselected extkey userGDK="+Integer.toHexString(kd.keyGDK)+",name="+kd.keyName); //~1613I~
        }
	    return newbtn;//~1613I~
    }                                                              //~1613I~
}//class AxeDialog                                                 //~1528R~
