//*CID://+vc53R~: update#= 235;                                    //~vc53R~
//**********************************************************************
//*AlerDlgFileChooser                                              //~vayhR~
//**********************************************************************
//vc53 2023/06/12 java error;switch-case requres constant          //~vc53I~
//vc2M 2020/08/24 xehelp folder download                           //~vc2MI~
//vb04 2014/11/30 FileDialog:change sort button color,assending descending//~vb04I~
//vayh:141125 (Axe)save to preference the name for filedialog      //~vayhI~
//vay7:141122 (Axe)actionBar:save/saveas item                      //~vay7I~
//vaxc:140705 (Axe)FileDialog from menutop(from"File" menu,"Open" and Download" to top)//~vaxcI~
//vaxb:140628 (Axe)implement filedialog                            //~vaxbR~
//**********************************************************************//~vaxbR~
package com.xe.Axe;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;                                    //~vaxbI~
import android.widget.ImageButton;
import android.widget.TextView;



//**********************************************************************
public class AxeDlgFileChooser extends AxeDialog                   //~vaxbR~
{
	private static final String PREF_FILTER="DlgFileChooserFilter";//~vaxbI~
	private static final String PREF_NEWNAME="DlgFileChooserNewName";//~vayhI~
	public  static final int DIALOG_FILECHOOSER   =R.layout.dialogfilechooser;//~vaxbR~
	private static final int TITLE_FILECHOOSER    =R.string.DialogTitle_FileChooser;//~vaxbR~
	private static final String HELP_FILE="AxeDlgFileChooser";     //~vc2MI~
//	private static final int COLOR_SORT_ONA        =0xffffff00;    //~vaxbR~//~vb04R~
//	private static final int COLOR_SORT_OND        =0xff00ffff;    //~vaxbR~//~vb04R~
  	private static final int COLOR_SORT_ONA        =0xffff8020; //orange//~vb04R~//~vc2MR~
	private static final int COLOR_SORT_OND        =0xffff8020;    //~vb04R~//~vc2MR~
	private static final int COLOR_SORT_OFF       =Color.BLACK;    //~vaxbI~
    private EditText   etNewName;                                  //~vaxbR~
    private EditText   etFilter;                                   //~vaxbI~
    private Button     btDetail,btUp,btSort1,btSort2,btSave,btEdit,btBrowse,btClose;//~vaxbR~
    private ImageButton     btFilter,btFilterClear,btNameClear;                                   //~vaxbI~//~vaxcR~
//  private LinearLayout lineNewName;                                //~vaxbI~//~vaxcR~
	private AxeLstFileChooser axeListFileChooser;                  //~vaxbI~
    public static int Smode; //0:load,1:saveas                     //~vaxbR~
    private String cmdFilename;                                    //~vaxbI~
    private static String refFilename;                             //~vaxcR~
    private static TextView refOutputView;                         //~vaxcI~
    private int cmdId;                                             //~vaxbI~
//**********************************
	public AxeDlgFileChooser()                                     //~vaxbR~
    {
    	super(DIALOG_FILECHOOSER);                                 //~vaxbR~
	    if (Dump.Y) Dump.println("AxeDlgFileChooser constructor"); //~vaxbI~
    }
//**********************************
    public static AxeDlgFileChooser showDialog(int Pmode)          //~vaxbR~
    {
	    if (Dump.Y) Dump.println("AxeDlgFileChooser static mode="+Pmode);//~vaxbR~
        Smode=Pmode;	                                           //~vaxbI~
    	AxeDlgFileChooser axeDialog=new AxeDlgFileChooser();       //~vaxbR~
        String title=Utils.getResourceString(TITLE_FILECHOOSER);   //~vaxbR~
		axeDialog.showDialog(title);
        return axeDialog;
    }
//**********************************                               //~vaxcI~
//*from Download                                                   //~vaxcI~
//**********************************                               //~vaxcI~
    public static AxeDlgFileChooser showDialog(int Pmode,TextView Poutview,String Pfilename)//~vaxcR~
    {                                                              //~vaxcI~
	    if (Dump.Y) Dump.println("AxeDlgFileChooser static mode="+Pmode);//~vaxcI~
        refFilename=Pfilename;                                     //~vaxcI~
        refOutputView=Poutview;                                    //~vaxcI~
	    return showDialog(Pmode);                                   //~vaxcI~
    }                                                              //~vaxcI~
//*******************************                                  //~vaxbI~
//  before axeList gotten                                          //~vaxbI~
//*******************************                                  //~vaxbI~
	private void setupOtherView(ViewGroup PlayoutView)            //~vaxbI~
	{                                                              //~vaxbI~
	    etNewName=(EditText)PlayoutView.findViewById(R.id.NewName);//~vaxbR~
	    etFilter=(EditText)PlayoutView.findViewById(R.id.Filter);  //~vaxbI~
	    btUp=(Button)PlayoutView.findViewById(R.id.DirUp);         //~vaxbI~
	    btDetail=(Button)PlayoutView.findViewById(R.id.Detail);    //~vaxbI~
	    btSort1=(Button)PlayoutView.findViewById(R.id.SortName);   //~vaxbI~
	    btSort2=(Button)PlayoutView.findViewById(R.id.SortAttr);   //~vaxbI~
	    btSave=(Button)PlayoutView.findViewById(R.id.Save);        //~vaxbI~
	    btEdit=(Button)PlayoutView.findViewById(R.id.Edit);        //~vaxbI~
	    btBrowse=(Button)PlayoutView.findViewById(R.id.Browse);    //~vaxbI~
	    btFilter=(ImageButton)PlayoutView.findViewById(R.id.BtnFilter); //~vaxbI~//~vaxcR~
	    btFilterClear=(ImageButton)PlayoutView.findViewById(R.id.BtnFilterClear);//~vaxcI~
	    btNameClear=(ImageButton)PlayoutView.findViewById(R.id.BtnNameClear);//~vaxcI~
	    btClose=(Button)PlayoutView.findViewById(R.id.Close);      //~vaxcI~
        setButtonListener(btUp);                                   //~vaxbI~
        setButtonListener(btDetail);                               //~vaxbI~
        setButtonListener(btSort1);                                //~vaxbR~
        setButtonListener(btSort2);                                //~vaxbI~
        setButtonListener(btFilter);                               //~vaxbI~
        setButtonListener(btFilterClear);                          //~vaxcI~
        setButtonListener(btNameClear);                            //~vaxcI~
        if (Smode==AxeLstFileChooser.OPEN_REF)  //reference        //~vaxcI~
        {                                                          //~vaxcI~
        	btEdit.setVisibility(View.GONE);                       //~vaxcI~
        	btBrowse.setVisibility(View.GONE);                     //~vaxcI~
        	btSave.setVisibility(View.GONE);                       //~vaxcI~
        	btClose.setVisibility(View.VISIBLE);                      //~vaxcI~
        	setButtonListener(btClose);                               //~vaxcI~
        }                                                          //~vaxcI~
		else                                                       //~vaxcI~
        {                                                          //~vaxbI~//~vaxcR~
        	setButtonListener(btEdit);                               //~vaxbI~
        	setButtonListener(btBrowse);                             //~vaxbI~
        	setButtonListener(btSave);                             //~vaxcI~
          	if (Smode==AxeLstFileChooser.SAVEAS)                   //~vay7I~
          	{                                                      //~vay7I~
        		btEdit.setVisibility(View.GONE);                   //~vay7I~
        		btBrowse.setVisibility(View.GONE);                 //~vay7I~
          	}                                                      //~vay7I~
        	btClose.setVisibility(View.GONE);                         //~vaxcI~
        }                                                          //~vaxbI~//~vaxcR~
        String filter=AxeProp.getPreference(PREF_FILTER,""/*default*/);//~vaxbI~
        etFilter.setText(filter);                                        //~vaxbI~
        if (Smode==AxeLstFileChooser.OPEN_REF)                     //~vaxcI~
        	etNewName.setText(refFilename);                        //~vaxcI~
        else                                                       //~vay7I~
        if (Smode==AxeLstFileChooser.SAVEAS)                       //~vay7I~
        {                                                          //~vay7I~
        	getCurrentFilename();                                  //~vay7R~
            if (!dialogSetupError)                                 //~vay7I~
        		etNewName.setText(Gxeh.mCurrentFilename);          //~vay7I~
        }                                                          //~vay7I~
        else                                                       //~vayhI~
        {                                                          //~vayhI~
        	String newname=AxeProp.getPreference(PREF_NEWNAME,""/*default*/);//~vayhI~
        	etNewName.setText(newname);                            //~vayhI~
        }                                                          //~vayhI~
	}                                                              //~vaxbI~
//*********
	@Override
	protected void setupDialogExtend(ViewGroup PlayoutView)
    {
	    if (Dump.Y) Dump.println("AxeDlgFileChooser setupDialogExtend");//~vaxbI~
		setupOtherView(PlayoutView);                               //~vaxbI~
      if (!dialogSetupError)                                       //~vay7I~
        axeListFileChooser=AxeLstFileChooser.setupListView(this,layoutId,PlayoutView);//~vaxbI~
    }
//**********************************
	@Override
    protected boolean onClickHelp()
    {
	    if (Dump.Y) Dump.println("AxeDlgFileChooser onClickHelp"); //~vaxbI~
//		showDialogHelp(R.string.HelpTitle_FileChooser,R.string.Help_FileChooser);//~vaxbR~//~vc2MR~
  		showDialogHelp(R.string.HelpTitle_FileChooser,HELP_FILE);  //~vc2MI~
        return false;	//not dismiss
    }
//**********************************                               //~vaxcI~
	@Override                                                      //~vaxcI~
    protected boolean onClickClose()                               //~vaxcI~
    {                                                              //~vaxcI~
    	boolean rc=true;//dismiss                                  //~vaxcI~
        String fnm;                                             //~vaxcI~
//        int buttonid;                                              //~vaxcI~
    //**********                                                   //~vaxcI~
	    fnm=getNewName();                                          //~vaxcI~
        refFilename=axeListFileChooser.getSelectedFullpath(R.id.Close,fnm);//mey return null//~vaxcI~
        if (refFilename==null)	//err, missing filename            //~vaxcI~
        	return false;                                          //~vaxcI~
        refOutputView.setText(refFilename);                        //~vaxcI~
        return rc;                                                 //~vaxcI~
    }                                                              //~vaxcI~
//**********************************                               //~vaxbI~
	@Override                                                      //~vaxbI~
    protected boolean onClickOther(int Pbuttonid)                  //~vaxbI~
    {                                                              //~vaxbI~
    	boolean rc=true;	//dismiss                              //~vaxbR~
    	String fnm;
    //********mp.Y) Dump.println("AxeDlgFileChooser onClickOther buttonid="+Integer.toHexString(Pbuttonid));//~vaxbR~
        cmdFilename="";                                            //~vaxbI~
//        switch(Pbuttonid)                                          //~vaxbI~//~vc53R~
//        {                                                          //~vaxbI~//~vc53R~
//        case R.id.Save:                                            //~vaxbR~//~vc53R~
//            fnm=getNewName();                                      //~vaxcI~//~vc53R~
//            AxeProp.putPreference(PREF_NEWNAME,fnm);               //~vayhI~//~vc53R~
//           if (!fnm.startsWith("/"))                               //~vay7I~//~vc53R~
//            cmdFilename=axeListFileChooser.getSelectedFullpath(Pbuttonid,fnm);//mey return null//~vaxbR~//~vaxcR~//~vc53R~
//           else                                                    //~vay7I~//~vc53R~
//            cmdFilename=fnm;                                       //~vay7I~//~vc53R~
//            cmdFilename=encloseIfSpaceEmbedding(cmdFilename);      //~vayhI~//~vc53R~
//            if (cmdFilename==null)  //err, missing filename        //~vaxcI~//~vc53R~
//            {                                                      //~vaxcI~//~vc53R~
//                cmdFilename=""; //no dismiss                       //~vaxcI~//~vc53R~
//            }                                                      //~vaxcI~//~vc53R~
//            break;                                                 //~vaxbR~//~vc53R~
//        case R.id.Edit:                                            //~vaxbI~//~vc53R~
//        case R.id.Browse:                                          //~vaxbI~//~vc53R~
//            fnm=getNewName();                                      //~vaxcR~//~vc53R~
//            AxeProp.putPreference(PREF_NEWNAME,fnm);               //~vayhI~//~vc53R~
//           if (!fnm.startsWith("/"))                               //~vay7I~//~vc53R~
//            cmdFilename=axeListFileChooser.getSelectedFullpath(Pbuttonid,fnm);//may return null//~vaxbI~//~vaxcR~//~vc53R~
//           else                                                    //~vay7I~//~vc53R~
//            cmdFilename=fnm;                                       //~vay7I~//~vc53R~
//            cmdFilename=encloseIfSpaceEmbedding(cmdFilename);      //~vayhI~//~vc53R~
//            break;                                                 //~vaxbI~//~vc53R~
//        case R.id.Detail:                                          //~vaxbI~//~vc53R~
//            axeListFileChooser.toggleDetail();                 //~vaxbI~//~vc53R~
//            rc=false;   //not dismiss               **                                                   //~vaxbI~//~vc53R~
//            break;                                                 //~vaxbI~//~vc53R~
//        case R.id.DirUp:                                           //~vaxbI~//~vc53R~
//            axeListFileChooser.listParent();                       //~vaxbI~//~vc53R~
//            rc=false;   //not dismiss                              //~vaxbI~//~vc53R~
//            break;                                                 //~vaxbI~//~vc53R~
//        case R.id.SortName:                                        //~vaxbR~//~vc53R~
//            axeListFileChooser.sortList(1);                        //~vaxbR~//~vc53R~
//            rc=false;   //not dismiss                              //~vaxbI~//~vc53R~
//            break;                                                 //~vaxbI~//~vc53R~
//        case R.id.SortAttr:                                        //~vaxbI~//~vc53R~
//            axeListFileChooser.sortList(2);                        //~vaxbI~//~vc53R~
//            rc=false;   //not dismiss                              //~vaxbI~//~vc53R~
//            break;                                                 //~vaxbI~//~vc53R~
//        case R.id.BtnFilter:                                       //~vaxbI~//~vc53R~
//            axeListFileChooser.applyFilter();                      //~vaxbI~//~vc53R~
//            rc=false;   //not dismiss                              //~vaxbI~//~vc53R~
//            break;                                                 //~vaxbI~//~vc53R~
//        case R.id.BtnFilterClear:                                  //~vaxcI~//~vc53R~
//            etFilter.setText("");                                   //~vaxcI~//~vc53R~
//            rc=false;   //not dismiss                              //~vaxcI~//~vc53R~
//            break;                                                 //~vaxcI~//~vc53R~
//        case R.id.BtnNameClear:                                    //~vaxcI~//~vc53R~
//            etNewName.setText("");                                 //~vaxcI~//~vc53R~
//            rc=false;   //not dismiss                              //~vaxcI~//~vc53R~
//            break;                                                 //~vaxcI~//~vc53R~
//        }                                                          //~vaxbI~//~vc53R~
//      switch(Pbuttonid)                                          //~vc53I~
//      {                                                          //~vc53I~
        if (Pbuttonid== R.id.Save)                                 //~vc53I~
        {                                                          //~vc53I~
            fnm=getNewName();                                      //~vc53I~
            AxeProp.putPreference(PREF_NEWNAME,fnm);               //~vc53I~
           if (!fnm.startsWith("/"))                               //~vc53I~
            cmdFilename=axeListFileChooser.getSelectedFullpath(Pbuttonid,fnm);//mey return null//~vc53I~
           else                                                    //~vc53I~
            cmdFilename=fnm;                                       //~vc53I~
            cmdFilename=encloseIfSpaceEmbedding(cmdFilename);      //~vc53I~
            if (cmdFilename==null)  //err, missing filename        //~vc53I~
            {                                                      //~vc53I~
                cmdFilename=""; //no dismiss                       //~vc53I~
            }                                                      //~vc53I~
        }                                                          //~vc53I~
        else //break;                                              //~vc53I~
        if (Pbuttonid== R.id.Edit                                  //+vc53R~
        ||  Pbuttonid== R.id.Browse)                               //~vc53I~
        {                                                          //~vc53I~
            fnm=getNewName();                                      //~vc53I~
            AxeProp.putPreference(PREF_NEWNAME,fnm);               //~vc53I~
           if (!fnm.startsWith("/"))                               //~vc53I~
            cmdFilename=axeListFileChooser.getSelectedFullpath(Pbuttonid,fnm);//may return null//~vc53I~
           else                                                    //~vc53I~
            cmdFilename=fnm;                                       //~vc53I~
            cmdFilename=encloseIfSpaceEmbedding(cmdFilename);      //~vc53I~
        }                                                          //~vc53I~
        else //break;                                              //~vc53I~
        if (Pbuttonid== R.id.Detail)                               //~vc53I~
        {                                                          //~vc53I~
            axeListFileChooser.toggleDetail();                     //~vc53I~
            rc=false;   //not dismiss               **             //~vc53I~
        }                                                          //~vc53I~
        else //break;                                              //~vc53I~
        if (Pbuttonid== R.id.DirUp)                                //~vc53I~
        {                                                          //~vc53I~
            axeListFileChooser.listParent();                       //~vc53I~
            rc=false;   //not dismiss                              //~vc53I~
        }                                                          //~vc53I~
        else //break;                                              //~vc53I~
        if (Pbuttonid== R.id.SortName)                             //~vc53I~
        {                                                          //~vc53I~
            axeListFileChooser.sortList(1);                        //~vc53I~
            rc=false;   //not dismiss                              //~vc53I~
        }                                                          //~vc53I~
        else //break;                                              //~vc53I~
        if (Pbuttonid== R.id.SortAttr)                             //~vc53I~
        {                                                          //~vc53I~
            axeListFileChooser.sortList(2);                        //~vc53I~
            rc=false;   //not dismiss                              //~vc53I~
        }                                                          //~vc53I~
        else //break;                                              //~vc53I~
        if (Pbuttonid== R.id.BtnFilter)                            //~vc53I~
        {                                                          //~vc53I~
            axeListFileChooser.applyFilter();                      //~vc53I~
            rc=false;   //not dismiss                              //~vc53I~
        }                                                          //~vc53I~
        else //break;                                              //~vc53I~
        if (Pbuttonid== R.id.BtnFilterClear)                       //~vc53I~
        {                                                          //~vc53I~
            etFilter.setText("");                                  //~vc53I~
            rc=false;   //not dismiss                              //~vc53I~
        }                                                          //~vc53I~
        else //break;                                              //~vc53I~
        if (Pbuttonid== R.id.BtnNameClear)                         //~vc53I~
        {                                                          //~vc53I~
            etNewName.setText("");                                 //~vc53I~
            rc=false;   //not dismiss                              //~vc53I~
        }                                                          //~vc53I~
//          break;                                                 //~vc53I~
//      }                                                          //~vc53I~
        if (cmdFilename.equals(""))                                //~vaxbI~
        	return false;                                          //~vaxbI~
        cmdId=Pbuttonid;                                           //~vaxbI~
        return rc;                                                 //~vaxbI~
    }                                                              //~vaxbI~
//**********************************                               //~vaxbI~
	public void setNewName(String Pfilename)                       //~vaxbR~
    {                                                              //~vaxbI~
		etNewName.setText(Pfilename);                              //~vaxbR~
	}                                                              //~vaxbI~
//**********************************                               //~vaxcI~
	public String getNewName()                                       //~vaxcI~
    {                                                              //~vaxcI~
		String name=etNewName.getText().toString();                 //~vaxcI~
        if (name.length()>0)                                       //~vaxcI~
        	name=name.trim();                                      //~vaxcI~
        return name;                                               //~vaxcI~
	}                                                              //~vaxcI~
//**********************************                               //~vaxbI~
	@Override                                                      //~vaxbI~
    protected void onDismiss()                                     //~vaxbI~
    {                                                              //~vaxbI~
	    if (Dump.Y) Dump.println("AxeDlgFileChooser onDismiss buttonid="+Integer.toHexString(cmdId));//~vaxbR~
        String filter=etFilter.getText().toString();                        //~vaxbI~
        if (filter==null)                                          //~vaxbI~
        	filter="";                                             //~vaxbI~
        AxeProp.putPreference(PREF_FILTER,filter);                 //~vaxbI~
//        switch(cmdId)                                              //~vaxbI~//~vc53R~
//        {                                                          //~vaxbM~//~vc53R~
//        case R.id.Edit:                                            //~vaxbI~//~vc53R~
//            AxeJNI.xecmd("Edit",cmdFilename);                      //~vaxbI~//~vc53R~
//            break;                                                 //~vaxbM~//~vc53R~
//        case R.id.Browse:                                          //~vaxbI~//~vc53R~
//            AxeJNI.xecmd("Browse",cmdFilename);                    //~vaxbI~//~vc53R~
//            break;                                                 //~vaxbI~//~vc53R~
//        case R.id.Save:                                            //~vaxbI~//~vc53R~
//            AxeJNI.xecmd("Save",cmdFilename);                      //~vaxbI~//~vc53R~
//            break;                                                 //~vaxbI~//~vc53R~
//        }                                                          //~vaxbM~//~vc53R~
//      switch(cmdId)                                              //~vc53I~
//      {                                                          //~vc53I~
        if (cmdId== R.id.Edit)                                     //~vc53I~
            AxeJNI.xecmd("Edit",cmdFilename);                      //~vc53I~
        else //break;                                              //~vc53I~
        if (cmdId== R.id.Browse)                                   //~vc53I~
            AxeJNI.xecmd("Browse",cmdFilename);                    //~vc53I~
        else //break;                                              //~vc53I~
        if (cmdId== R.id.Save)                                     //~vc53I~
            AxeJNI.xecmd("Save",cmdFilename);                      //~vc53I~
//          break;                                                 //~vc53I~
//      }                                                          //~vc53I~
    }                                                              //~vaxbI~
//**********************************                               //~vay7R~
//    private static void xecmdSave()                              //~vay7R~
//    {                                                            //~vay7R~
//        AxeJNI.xecmd("Save",""/*filename*/);                     //~vay7R~
//    }                                                            //~vay7R~
//**********************************                               //~vaxbI~
    protected void sorted(int PsortType)                           //~vaxbI~
    {                                                              //~vaxbI~
    //****************	                                           //~vaxbI~
        switch(PsortType)                                          //~vaxbI~
        {                                                          //~vaxbI~
        case AxeLstFileChooser.SORT_ATTR_A:                        //~vaxbI~
            btSort1.setTextColor(COLOR_SORT_OFF);                  //~vaxbI~
            setHorizontalGravity(btSort1,0);                       //~vb04I~
            btSort2.setTextColor(COLOR_SORT_ONA);                  //~vaxbI~
            setHorizontalGravity(btSort2,1);                       //~vb04I~
            break;                                                 //~vaxbI~
        case AxeLstFileChooser.SORT_ATTR_D:                        //~vaxbI~
            btSort1.setTextColor(COLOR_SORT_OFF);                  //~vaxbR~
            setHorizontalGravity(btSort1,0);                       //~vb04I~
            btSort2.setTextColor(COLOR_SORT_OND);                  //~vaxbR~
            setHorizontalGravity(btSort2,-1);                     //~vb04I~
            break;                                                 //~vaxbI~
        case AxeLstFileChooser.SORT_FILE_D:                        //~vaxbI~
            btSort1.setTextColor(COLOR_SORT_OND);                  //~vaxbI~
            setHorizontalGravity(btSort1,-1);                     //~vb04I~
            btSort2.setTextColor(COLOR_SORT_OFF);                  //~vaxbI~
            setHorizontalGravity(btSort2,0);                      //~vb04I~
            break;                                                 //~vaxbI~
        default:                                                   //~vaxbI~
            btSort1.setTextColor(COLOR_SORT_ONA);                  //~vaxbR~
            setHorizontalGravity(btSort1,1);                      //~vb04I~
            btSort2.setTextColor(COLOR_SORT_OFF);                  //~vaxbI~
            setHorizontalGravity(btSort2,0);                      //~vb04I~
        }                                                          //~vaxbI~
    }                                                              //~vaxbI~
//**********************************                               //~vaxbI~
    protected String getFilterString()                             //~vaxbI~
    {                                                              //~vaxbI~
    	return etFilter.getText().toString();                         //~vaxbI~
    }                                                              //~vaxbI~
//**********************************                               //~vay7I~
    private void getCurrentFilename()                              //~vay7R~
    {                                                              //~vay7I~
    	AxeJNI.getCurrentFilename();                               //~vay7I~
        if (Gxeh.mCurrentFilenameType!=1)	//see jnij2c:getCurrentFilename//~vay7I~
        {                                                          //~vay7I~
            dialogSetupError=true;                                 //~vay7I~
			Utils.showToastLong(R.string.Err_CurrentIsNotFilePanel);//~vay7I~
        }                                                          //~vay7I~
    }                                                              //~vay7I~
    //*******************                                          //~vayhI~
    private static String encloseIfSpaceEmbedding(String Pfnm)     //~vayhI~
    {                                                              //~vayhI~
    	if (Pfnm.indexOf(' ')<0)                                   //~vayhI~
        	return Pfnm;                                           //~vayhI~
    	return "\""+Pfnm+"\"";                                     //~vayhI~
    }                                                              //~vayhI~
    //*******************                                          //~vb04I~
    private void setHorizontalGravity(Button Pview,int Phorizontal)//~vb04I~
    {                                                              //~vb04I~
//        int gravity;                                             //~vb04R~
//        if (Phorizontal>0)                                       //~vb04R~
//            gravity=Gravity.TOP;                                 //~vb04R~
//        else                                                     //~vb04R~
//        if (Phorizontal<0)                                       //~vb04R~
//            gravity=Gravity.BOTTOM;                              //~vb04R~
//        else                                                     //~vb04R~
//            gravity=Gravity.CENTER_HORIZONTAL;                   //~vb04R~
//        try                                                      //~vb04R~
//        {                                                        //~vb04R~
////            LayoutParams lp=new LayoutParams();                //~vb04R~
////            lp.gravity=gravity;                                //~vb04R~
////            Pview.setLayoutParams(lp);                         //~vb04R~
//            ((Button)Pview).setGravity(gravity);                 //~vb04R~
//        }                                                        //~vb04R~
//        catch(Exception e)                                       //~vb04R~
//        {                                                        //~vb04R~
//            Dump.println(e,"AxeDlgFileChooser:setHorizontalGravity");//~vb04R~
//        }                                                        //~vb04R~
		String lbl=Pview.getText().toString();                     //~vb04I~
        if (lbl.startsWith("v")||lbl.startsWith("^"))              //~vb04I~
        	lbl=lbl.substring(2);                                   //~vb04I~
        if (Phorizontal>0)                                         //~vb04I~
            lbl="^ "+lbl;                                          //~vb04I~
        else                                                       //~vb04I~
        if (Phorizontal<0)                                         //~vb04I~
            lbl="v "+lbl;                                          //~vb04I~
        Pview.setText(lbl);                                        //~vb04I~
    }                                                              //~vb04I~
}//class
