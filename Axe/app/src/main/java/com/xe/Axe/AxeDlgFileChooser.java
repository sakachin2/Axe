//*CID://+vb04R~: update#= 231;                                    //~vayhR~//~vb04R~
//**********************************************************************
//*AlerDlgFileChooser                                              //~vayhR~
//**********************************************************************
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
//	private static final int COLOR_SORT_ONA        =0xffffff00;    //~vaxbR~//~vb04R~
//	private static final int COLOR_SORT_OND        =0xff00ffff;    //~vaxbR~//~vb04R~
  	private static final int COLOR_SORT_ONA        =0xffffa080; //orange//~vb04R~
	private static final int COLOR_SORT_OND        =0xffffa080;    //~vb04R~
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
		showDialogHelp(R.string.HelpTitle_FileChooser,R.string.Help_FileChooser);//~vaxbR~
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
        switch(Pbuttonid)                                          //~vaxbI~
        {                                                          //~vaxbI~
        case R.id.Save:                                            //~vaxbR~
	        fnm=getNewName();                                      //~vaxcI~
        	AxeProp.putPreference(PREF_NEWNAME,fnm);               //~vayhI~
           if (!fnm.startsWith("/"))                               //~vay7I~
        	cmdFilename=axeListFileChooser.getSelectedFullpath(Pbuttonid,fnm);//mey return null//~vaxbR~//~vaxcR~
           else                                                    //~vay7I~
        	cmdFilename=fnm;                                       //~vay7I~
            cmdFilename=encloseIfSpaceEmbedding(cmdFilename);      //~vayhI~
            if (cmdFilename==null)	//err, missing filename        //~vaxcI~
            {                                                      //~vaxcI~
            	cmdFilename="";	//no dismiss                       //~vaxcI~
            }                                                      //~vaxcI~
        	break;                                                 //~vaxbR~
        case R.id.Edit:                                            //~vaxbI~
        case R.id.Browse:                                          //~vaxbI~
	        fnm=getNewName();                                      //~vaxcR~
        	AxeProp.putPreference(PREF_NEWNAME,fnm);               //~vayhI~
           if (!fnm.startsWith("/"))                               //~vay7I~
        	cmdFilename=axeListFileChooser.getSelectedFullpath(Pbuttonid,fnm);//may return null//~vaxbI~//~vaxcR~
           else                                                    //~vay7I~
        	cmdFilename=fnm;                                       //~vay7I~
            cmdFilename=encloseIfSpaceEmbedding(cmdFilename);      //~vayhI~
        	break;                                                 //~vaxbI~
        case R.id.Detail:                                          //~vaxbI~
        	axeListFileChooser.toggleDetail();                 //~vaxbI~
	    	rc=false;	//not dismiss               **                                                   //~vaxbI~
        	break;                                                 //~vaxbI~
        case R.id.DirUp:                                           //~vaxbI~
        	axeListFileChooser.listParent();                       //~vaxbI~
	    	rc=false;	//not dismiss                              //~vaxbI~
        	break;                                                 //~vaxbI~
        case R.id.SortName:                                        //~vaxbR~
        	axeListFileChooser.sortList(1);                        //~vaxbR~
	    	rc=false;	//not dismiss                              //~vaxbI~
        	break;                                                 //~vaxbI~
        case R.id.SortAttr:                                        //~vaxbI~
        	axeListFileChooser.sortList(2);                        //~vaxbI~
	    	rc=false;	//not dismiss                              //~vaxbI~
        	break;                                                 //~vaxbI~
        case R.id.BtnFilter:                                       //~vaxbI~
        	axeListFileChooser.applyFilter();                      //~vaxbI~
	    	rc=false;	//not dismiss                              //~vaxbI~
        	break;                                                 //~vaxbI~
        case R.id.BtnFilterClear:                                  //~vaxcI~
        	etFilter.setText("");                                   //~vaxcI~
	    	rc=false;	//not dismiss                              //~vaxcI~
        	break;                                                 //~vaxcI~
        case R.id.BtnNameClear:                                    //~vaxcI~
        	etNewName.setText("");                                 //~vaxcI~
	    	rc=false;	//not dismiss                              //~vaxcI~
        	break;                                                 //~vaxcI~
        }                                                          //~vaxbI~
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
        switch(cmdId)                                              //~vaxbI~
        {                                                          //~vaxbM~
    	case R.id.Edit:                                            //~vaxbI~
	        AxeJNI.xecmd("Edit",cmdFilename);                      //~vaxbI~
            break;                                                 //~vaxbM~
    	case R.id.Browse:                                          //~vaxbI~
	        AxeJNI.xecmd("Browse",cmdFilename);                    //~vaxbI~
            break;                                                 //~vaxbI~
    	case R.id.Save:                                            //~vaxbI~
	        AxeJNI.xecmd("Save",cmdFilename);                      //~vaxbI~
            break;                                                 //~vaxbI~
        }                                                          //~vaxbM~
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
//        int gravity;                                             //+vb04R~
//        if (Phorizontal>0)                                       //+vb04R~
//            gravity=Gravity.TOP;                                 //+vb04R~
//        else                                                     //+vb04R~
//        if (Phorizontal<0)                                       //+vb04R~
//            gravity=Gravity.BOTTOM;                              //+vb04R~
//        else                                                     //+vb04R~
//            gravity=Gravity.CENTER_HORIZONTAL;                   //+vb04R~
//        try                                                      //+vb04R~
//        {                                                        //+vb04R~
////            LayoutParams lp=new LayoutParams();                //+vb04R~
////            lp.gravity=gravity;                                //+vb04R~
////            Pview.setLayoutParams(lp);                         //+vb04R~
//            ((Button)Pview).setGravity(gravity);                 //+vb04R~
//        }                                                        //+vb04R~
//        catch(Exception e)                                       //+vb04R~
//        {                                                        //+vb04R~
//            Dump.println(e,"AxeDlgFileChooser:setHorizontalGravity");//+vb04R~
//        }                                                        //+vb04R~
		String lbl=Pview.getText().toString();                     //+vb04I~
        if (lbl.startsWith("v")||lbl.startsWith("^"))              //+vb04I~
        	lbl=lbl.substring(2);                                   //+vb04I~
        if (Phorizontal>0)                                         //+vb04I~
            lbl="^ "+lbl;                                          //+vb04I~
        else                                                       //+vb04I~
        if (Phorizontal<0)                                         //+vb04I~
            lbl="v "+lbl;                                          //+vb04I~
        Pview.setText(lbl);                                        //+vb04I~
    }                                                              //~vb04I~
}//class
