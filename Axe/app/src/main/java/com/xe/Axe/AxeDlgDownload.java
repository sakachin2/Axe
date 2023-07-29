//*CID://+vc53R~:                             update#=   28;       //+vc53R~
//**********************************************************************
//vc53 2023/06/12 java error;switch-case requres constant          //+vc53I~
//vc2M 2020/08/24 xehelp folder download                           //~vc2MI~
//vaxc:140705 (Axe)FileDialog from menutop(from"File" menu,"Open" and Download" to top)//~vaxcI~
//vag0:120719 (Axe)function to download asset from web
//**********************************************************************
//*AlerDlgDownload
//**********************************************************************
package com.xe.Axe;

import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;                                 //~vaxcR~
import android.widget.TextView;                                    //~vaxcR~
public class AxeDlgDownload extends AxeDialog
{
/////////////////////////////////////////////////////////////////////////////
//**********************************************************************
	private static final String HELP_FILE  ="AxeDlgDownload";       //~vc2MI~
	private static final int LAYOUT=R.layout.dialogdownload;
	private static final int TITLE =R.string.DialogTitle_Download;
    private static final String defaultAssetURL="http://www.geocities.jp/sakachin2/Axe/AxeAsset.zip";//~vag0I~
    private EditText   etURL,etLocalFile;                          //~vag0R~
    private ImageButton  btLocalRef;                               //~vaxcR~
    private CheckBox   cbUnzip,cbDel,cbUnziponly;                  //~vag0R~
    private static String SURL="",Slocalfile="";                   //~vag0R~
    private static String SdefaultLocalPath;                       //~vag0I~
    private static boolean Sunzip=true,Sdelzip=true,Sunziponly=false;//~vag0R~
    private static boolean swAsset;//~vag0I~
    private static final String PREFKEY_DLURL="DownloadURL";       //~vag0I~
    private static final String PREFKEY_DLLOCAL="DownloadLocalFile";//~vag0I~
    private static final String PREFKEY_DELZIP="DownloadDelsrc";   //~vag0I~
    private static final String PREFKEY_UNZIPONLY="DownloadUnzipOnly";//~vag0I~
    private static final String PREFKEY_UNZIP="DownloadUnzip";     //~vag0I~
	private static AxeDlgFileChooser fileChooser;                  //~vaxcR~
//**********************************
	public AxeDlgDownload()
    {
    	super(LAYOUT);
        if (Slocalfile==null||Slocalfile.equals(""))               //~vag0I~
        	Slocalfile=SdefaultLocalPath;                          //~vag0R~
    }
//**********************************
//*download assets                                                 //~vag0I~
//**********************************                               //~vag0I~
    public static AxeDlgDownload showDialog(boolean Pswasset)      //~vag0R~
    {
    	swAsset=Pswasset;
        if (SdefaultLocalPath==null)                               //~vag0I~
        {                                                          //~vag0I~
			SdefaultLocalPath=AxeProp.getDataFileFullpath(null);   //~vag0I~
        	getPrefData();                                  //~vag0I~
        }                                                          //~vag0I~
    	AxeDlgDownload dlg=new AxeDlgDownload();
        String title=Utils.getResourceString(TITLE);
		dlg.showDialog(title);
        return dlg;
    }
//*********                                                        //~vag0I~
    private static void getPrefData()                                  //~vag0I~
    {                                                              //~vag0I~
    	SURL=AxeProp.getPreference(PREFKEY_DLURL,"");              //~vag0I~
    	Slocalfile=AxeProp.getPreference(PREFKEY_DLLOCAL,"");      //~vag0R~
    	Sdelzip=AxeProp.getPreference(PREFKEY_DELZIP,"0").equals("1");//~vag0I~
    	Sunziponly=AxeProp.getPreference(PREFKEY_UNZIPONLY,"0").equals("1");//~vag0I~
    	Sunzip=AxeProp.getPreference(PREFKEY_UNZIP,"0").equals("1");//~vag0I~
    }                                                              //~vag0I~
//*********                                                        //~vag0I~
    private static void putPrefData()                                  //~vag0I~
    {                                                              //~vag0I~
    	AxeProp.putPreference(PREFKEY_DLURL,SURL);                 //~vag0R~
    	AxeProp.putPreference(PREFKEY_DLLOCAL,Slocalfile);         //~vag0R~
    	AxeProp.putPreference(PREFKEY_DELZIP,Sdelzip?"1":"0");     //~vag0I~
    	AxeProp.putPreference(PREFKEY_UNZIPONLY,Sunziponly?"1":"0");//~vag0I~
    	AxeProp.putPreference(PREFKEY_UNZIP,Sunzip?"1":"0");      //~vag0I~
    }                                                              //~vag0I~
//*********
	@Override
	protected void setupDialogExtend(ViewGroup PlayoutView)
    {
	    etURL=(EditText)layoutView.findViewById(R.id.DL_URL);      //~vag0R~
	    etLocalFile=(EditText)layoutView.findViewById(R.id.DL_Local);//~vag0I~
	    btLocalRef=(ImageButton)layoutView.findViewById(R.id.DL_BtnLocalRef);//~vaxcR~
	    cbUnzip=(CheckBox)layoutView.findViewById(R.id.DL_Unzip);  //~vag0I~
	    cbDel=(CheckBox)layoutView.findViewById(R.id.DL_DelZipfile);//~vag0I~
	    cbUnziponly=(CheckBox)layoutView.findViewById(R.id.DL_Unziponly);//~vag0I~
        setButtonListener(btLocalRef);                             //~vaxcR~
        if (swAsset)                                               //~vag0I~
	        etURL.setText(defaultAssetURL);                        //~vag0I~
        else                                                       //~vag0I~
	        etURL.setText(SURL);                                   //~vag0R~
        if (swAsset)                                               //~vag0I~
	        etLocalFile.setText(SdefaultLocalPath);                      //~vag0R~
        else                                                       //~vag0I~
	        etLocalFile.setText(Slocalfile);                       //~vag0R~
        if (swAsset)                                               //~vag0I~
        	cbUnzip.setChecked(true);                              //~vag0I~
        else                                                       //~vag0I~
        	cbUnzip.setChecked(Sunzip);                            //~vag0R~
        if (swAsset)                                               //~vag0I~
        	cbDel.setChecked(true);                                //~vag0I~
        else                                                       //~vag0I~
        	cbDel.setChecked(Sdelzip);                             //~vag0R~
        if (swAsset)                                               //~vag0I~
        	cbUnziponly.setChecked(false);                         //~vag0I~
        else                                                       //~vag0I~
        	cbUnziponly.setChecked(Sunziponly);                    //~vag0R~
    }
//************************
//*dialog button Listener*
//************************
	@Override
    protected boolean onClickClose()
    {
    	boolean rc=true;	//dismiss
    //****************
        Sunziponly=cbUnziponly.isChecked();                        //~vag0M~
        String url=etURL.getText().toString();                     //~vag0R~
        if (!Sunziponly)                                           //~vag0I~
            if (!url.equals(""))                                   //~vag0R~
            {                                                      //~vag0R~
                int idx1=url.indexOf("://");                       //~vag0R~
                int idx2=url.indexOf("/");                         //~vag0R~
                if (idx1<0|| idx1>idx2)                            //~vag0R~
                url="http://"+url;                                 //~vag0R~
            }                                                      //~vag0R~
        if (!swAsset)                                              //~vag0I~
            SURL=url;                                              //~vag0I~
        String localfile=etLocalFile.getText().toString();         //~vag0R~
        if (!swAsset)                                              //~vag0I~
        	Slocalfile=localfile;                                  //~vag0I~
        boolean unzip=cbUnzip.isChecked();                         //~vag0I~
        if (!swAsset)                                              //~vag0I~
        	Sunzip=unzip;                                          //~vag0R~
        Sdelzip=cbDel.isChecked();                                 //~vag0I~
                                                                   //~vag0I~
	    putPrefData();                                             //~vag0I~
        if (Sunziponly)                                            //~vag0I~
    		AxeDownload.unzip(url,localfile,Sdelzip);              //~vag0I~
        else                                                       //~vag0I~
        if (url!=null)                                             //~vag0R~
        {
    		AxeDownload.download(url,localfile,unzip,Sdelzip);     //~vag0R~
        }
        return rc;
    }
//******************
	@Override
    protected boolean onClickHelp()
    {
//  	showDialogHelp(R.string.HelpTitle_Download,R.string.Help_Download);//~vc2MR~
    	showDialogHelp(TITLE,HELP_FILE);                           //~vc2MI~
        return false;	//no dismiss
    }
//**********************************                               //~vaxcR~
	@Override                                                      //~vaxcR~
    protected boolean onClickOther(int Pbuttonid)                  //~vaxcR~
    {                                                              //~vaxcR~
    	boolean rc=true;	//dismiss                              //~vaxcR~
    	String fnm;                                                //~vaxcR~
    //********mp.Y) Dump.println("AxeDlgFileChooser onClickOther buttonid="+Integer.toHexString(Pbuttonid));//~vaxcR~
//      switch(Pbuttonid)                                          //~vaxcR~//+vc53R~
//      {                                                          //~vaxcR~//+vc53R~
//      case R.id.DL_BtnLocalRef:                                  //~vaxcR~//+vc53R~
        if (Pbuttonid==R.id.DL_BtnLocalRef)                        //+vc53I~
        {                                                          //+vc53I~
        	fnm=etURL.getText().toString();                        //~vaxcR~
            if (fnm==null)                                         //~vaxcR~
            	fnm="";                                            //~vaxcR~
            else                                                   //~vaxcR~
            {                                                      //~vaxcR~
            	fnm=fnm.trim();                                    //~vaxcR~
                if (fnm.lastIndexOf("/")>0)                        //~vaxcR~
                	fnm=fnm.substring(fnm.lastIndexOf("/")+1);     //~vaxcR~
            }                                                      //~vaxcR~
		    fileChooser=AxeDlgFileChooser.showDialog(AxeLstFileChooser.OPEN_REF,(TextView)etLocalFile,fnm);//~vaxcR~
            rc=false;                                              //~vaxcR~
//      	break;                                                 //~vaxcR~//+vc53R~
        }                                                          //~vaxcR~
        return rc;                                                 //~vaxcR~
    }                                                              //~vaxcR~
}
