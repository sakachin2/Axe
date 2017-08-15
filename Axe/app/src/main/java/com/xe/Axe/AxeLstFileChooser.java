//*CID://+vb03R~: update#= 428;                                    //~vb03R~
//**********************************************************************
//*ListView
//**********************************************************************
//vb03 2014/11/30 FileDialog:keep also sort type between reopen dialog//~vb01I~
//vb01 2014/11/29 FileDialog:selected filename is invalid after list sorted if filter was set//~vb02I~
//vayi:141125 (Axe)redundant / for filedialog fullpath setting     //~vayiI~
//vay7:141122 (Axe)actionBar:save/saveas item                      //~vay7R~
//vaxc:140705 (Axe)FileDialog from menutop(from"File" menu,"Open" and Download" to top)//~vaxcI~
//vaxb:140628 (Axe)implement filedialog                            //~4629I~
//**********************************************************************//~4629I~
package com.xe.Axe;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
                                                                   //~4629I~
import java.io.File;                                               //~4629I~
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;


public class AxeLstFileChooser extends AxeList                     //~4629R~
{
	private static final int ROWID =R.layout.listrowfilechooser;//~4701R~//~4703R~
	private static final String DIRLISTSHOWATTR="DirListShowAttr"; //~4703I~
	private static final String DIRLISTSORT="DirListSort";         //~vb03I~
    private static int mode;                                              //~4629I~//~4630R~
    public final static int OPEN=4;
    public final static int OPEN_REF=5;   //from download  local file reference//~vaxcI~
    public final static int SAVEAS=6;                              //~vay7R~
    private static final int COLOR_FG_DIR   =0xff008080;           //~vaxbR~//~4703M~
	private static final int COLOR_FG_FILE  =Color.BLACK;          //~vaxbI~//~4703I~
	private static final int COLOR_BG       =Color.WHITE;          //~4703I~
	private static final int COLOR_BG_ATTR  =0xffe0ffe0;           //~4704I~
	private static final int COLOR_BGFOCUS_COL=0xffffa050; //lighten orange//~4704I~
	private static final int COLOR_BGFOCUS=AxeList.COLOR_BGFOCUS;   //~4704I~
                                                                   //~4629I~
    private String dirname="";                                     //~4629I~
    private String filterString=null;                              //~4629I~
    private String updownpath=null;                                //~4630I~
    public  String[] namelist;                                     //~4629I~
    public  String[] attrlist;                                     //~4630I~
	private File[] filelist;                                       //~4629I~
	private int[]  name2file;                                      //~4629I~
	private File   selectedFile;                                   //~4629I~
	private static String selectedFilename=null;                   //~4629I~
	private String selectedFileFullpath=null;                      //~vaxcI~
                                                                   //~4629I~
    private static AxeDlgFileChooser Sdialog;                       //~4629I~//~4630R~
    private static SimpleDateFormat fmtdate=new SimpleDateFormat("yyyy/MM/dd HH:mm");//~4630I~
    private static DecimalFormat fmtsz=new DecimalFormat("###,###");//~4630I~
    private int posSelected=-1;                                    //~4630I~
    private boolean swDetail=false;                                //~4630I~
    private View oldFocusView;                                     //~4703I~
    private int sortType;                             //~4703I~
    private boolean acceptMatch;	//matching by regex            //~4704I~
	public static final int SORT_FILE_A=0;                        //~4703I~//~4704R~
	public static final int SORT_FILE_D=1;                        //~4703I~//~4704R~
	public static final int SORT_ATTR_A=2;                        //~4703I~//~4704R~
	public static final int SORT_ATTR_D=3;                        //~4703I~//~4704R~
    private String[] extentionList;                                //~vaxcI~
//*****************
    public AxeLstFileChooser(int PdialogId,ViewGroup PlayoutView,int Pmenuid,int Prowid,int Popt)//~4629R~
    {
		super(PdialogId,PlayoutView,Pmenuid,Prowid,Popt);
    }
//*****************
    public static AxeLstFileChooser setupListView(AxeDialog Pdialog,int PdialogId,ViewGroup PlayoutView)//~4629R~
    {
        Sdialog=(AxeDlgFileChooser)Pdialog;                         //~4629I~//~4630I~
//      int opt=OPT_CLICKABLEVIEW|OPT_NOITEMCLICK;//|OPT_LONGITEMCLICK;                //~4703R~//~4704R~
        int opt=0;//|OPT_LONGITEMCLICK;                            //~4704I~
        AxeLstFileChooser al=new AxeLstFileChooser(PdialogId,PlayoutView,0/*Shift menuId*/,ROWID,opt);//~4629R~//~4703R~
        mode=AxeDlgFileChooser.Smode;	                           //~4630I~
    	al.setListViewDataFileChooser();                              //~4701I~
        return al;
    }
//*****************
    @Override
    public void setListViewData()
    {
    }
//*****************                                                //~4701I~
    private void setListViewDataFileChooser()                      //~4701I~
    {                                                              //~4701I~
		show();                                                    //~4701I~
        super.setAdapter();                                        //~4701I~
    }                                                              //~4701I~
//*callback from AxeKey ****************

    public void add(String Pmember,String Pattr)                   //~4629I~
    {                                                              //~4629I~
    	boolean isdir=Pmember.charAt(Pmember.length()-1)=='/';  //dir entry//~4703I~
        ListData linedata=new ListData(Pmember,Pattr,isdir);             //~4629R~//~4703R~
        if (Dump.Y) Dump.println("AxeList addFileChooser "+Pmember+"attr="+Pattr);//~4629I~
        super.add(linedata);                                       //~4629I~
    }                                                              //~4629I~
    //************************************                         //~4704I~
    //*OnClick :events for each line with line View parm           //~4704I~
    //************************************                         //~4704I~
    @Override                                                      //~4704I~
    public void onItemClickedLine(View Plineview,int Ppos,ListData Pitem)//~4704I~
    {                                                              //~4704I~
        int bg;                                                    //~4704I~
        View parent;                                               //~4704I~
    //************************                                     //~4704I~
        if (Dump.Y)Dump.println("AxeLstFileChooser onClicked pos="+Ppos);//~4704I~
        if (posSelected==Ppos)                                     //~4704I~
        	openSelected(Ppos);                                    //~4704I~
        setNewName(Ppos);                                          //~vaxcR~
        bg=COLOR_BGFOCUS;                                          //~4704I~
        Plineview.setBackgroundColor(bg);                              //~4704I~
        if (posSelected!=Ppos)                                     //~4704I~
        {                                                          //~4704I~
        	bg=COLOR_BG;                                           //~4704R~
        	if (oldFocusView!=null)                                //~4704R~
        		oldFocusView.setBackgroundColor(bg);               //~4704R~
        	oldFocusView=Plineview;                                //~4704R~
	        posSelected=Ppos;                                      //~4704I~
        }                                                          //~4704I~
    }                                                              //~4704I~
//    //************************************                       //~vaxcR~
//    private void resetOldline()                                  //~vaxcR~
//    {                                                            //~vaxcR~
//        if (oldFocusView!=null)                                  //~vaxcR~
//        {                                                        //~vaxcR~
//            int bg=COLOR_BG;                                     //~vaxcR~
//            View parent=(View)oldFocusView.getParent();          //~vaxcR~
//            parent.setBackgroundColor(bg);                       //~vaxcR~
//        }                                                        //~vaxcR~
//    }                                                            //~vaxcR~
//******************
	@Override
    public String getKeyname(int Ppos,int Pfldid,int Pkey,String Perr)
    {
    	String knm;                                                //~4630I~
    //***************                                              //~4630I~
        if (Pfldid==1)                                             //~4630I~
        	knm=namelist[Ppos];                                    //~4630I~
        else                                                       //~4630I~
    		if (swDetail)                                          //~4703I~
        		knm=attrlist[Ppos];                                    //~4630I~//~4703R~
            else                                                   //~4703I~
            	knm="";                                            //~4703I~
        if (Dump.Y) Dump.println("AxeLstFileChooser getKeyname:pos="+Ppos+",fldNo="+Pfldid+",name="+knm);//~4630I~
        return knm;                                                //~4630I~
    }
//******************                                               //~4703I~
	@Override                                                      //~4703I~
    public boolean setLineViewSub(View Pconvertview,int Ppos,TextView Pview1,TextView Pview2,ListData Pdata)//~4703I~
    {                                                              //~4703I~
    	int fg,bg;                                                    //~4703I~
    //******************                                           //~4703I~
        if (!Pdata.dirline)                                        //~4703I~
            fg=COLOR_FG_FILE;                                      //~4703I~
        else                                                       //~4704I~
            fg=COLOR_FG_DIR;                                       //~4704I~
        Pview1.setTextColor(fg);                                   //~4703I~//~4704R~
                                                                   //~4704I~
        bg=COLOR_BG;                                               //~4703I~//~vaxcR~
        Pconvertview.setBackgroundColor(bg);                       //~4703I~
        return true;                                               //~4703I~
    }                                                              //~4703I~
//******************                                               //~4629I~
	public void show()                                             //~4629I~
    {                                                              //~4629I~
    	createList(dirname);                                       //~4629I~
    	if (namelist==null)                                        //~4629I~
        	return;                                                //~4629I~
        setTitleFilename(dirname);                                 //~4629I~
    	setList();                                                 //~4629I~
		selectPos0();                                               //~4704I~
    }                                                              //~4629I~
    //**********************************                           //~4629I~
    //* file submenu selected;create submenu listview              //~4629I~
    //**********************************                           //~4629I~
    private void createList(String Pdirname)                       //~4629I~
    {                                                              //~4629I~
    	File fileCurDir;                                           //~4629I~
   		String[] nldir,nlfile,aldir,alfile;                        //~4703I~
		int[]  n2fdir,n2ffile;                                                //~4703I~
        int iid,iif;                                               //~4703I~
    //************************                                     //~4629I~
    	String path=Pdirname;                                      //~4629I~
        if (Dump.Y) Dump.println("createList path="+path);         //~4629I~
        if (path==null                                             //~4629I~
        ||  path.equals("")                                        //~4629I~
        )                                                          //~4629I~
        {                                                          //~4629I~
        	path=AxeProp.getPreference(AxeProp.GAMEFILE,null/*default*/);//~4629R~
	        int showattr=AxeProp.getPreference(DIRLISTSHOWATTR,0/*default*/);//~4703I~
		    sortType=AxeProp.getPreference(DIRLISTSORT,SORT_FILE_A/*default name assending*/);//for not "up" but last opened file only//~vb03I~
            swDetail=showattr!=0;                                  //~4703I~
            if (path==null)                                        //~4629I~
            	path=AxeProp.getSDPath("");                        //~4629R~
            if (path==null)                                        //~4629I~
            {                                                      //~4629I~
            	Utils.showToast(R.string.NoSDCard);                //~4629I~
    			clearListData();                                   //~4701I~
                return;                                            //~4629I~
            }                                                      //~4629I~
        }                                                          //~4629I~
        else                                                       //~vb03I~
        {                                                          //+vb03I~
		    sortType=SORT_FILE_A;//for the dir opened by parent or dir double tap//~vb03I~
		    AxeProp.putPreference(DIRLISTSORT,sortType);           //+vb03I~
        }                                                          //+vb03I~
        dirname=path;                                              //~4629I~
        try{                                                       //~4629I~
        	if (Dump.Y) Dump.println("FileDialog path="+path);     //~4629I~
            fileCurDir=new File(path);                             //~4629I~
            filelist=fileCurDir.listFiles();   //file and dir      //~4629I~
            if(filelist==null)                                     //~4629I~
            {                                                      //~4629I~
				AxeAlert.simpleAlertDialog(/*AxeAlertI*/null,path,"No Entry",AxeAlert.BUTTON_CLOSE);//~4629R~
            }                                                      //~4629I~
            else                                                   //~4629I~
            {                                                      //~4629I~
                int filelistctr=0,count=0;                                 //~4629I~//~4630R~//~4704M~
                String name;                                       //~4629I~
            	//********************************                 //~4704I~
				filterString=getFilterString();                    //~4704I~
            	name2file=new int[filelist.length+1];          //~4629I~//~4630R~
                for (File file : filelist)                         //~4629I~
                {                                                  //~4629I~
                    name=file.getName();                           //~4629I~
		        	if (Dump.Y) Dump.println("FileDialog file="+name);//~4629I~
                    if(!file.isDirectory())                        //~4629I~
                    {                                              //~4629I~
                    	if (!isFilterAccept(name))//~4629I~//~4630R~//~4704R~
		                	continue;                          //~4629I~//~4630R~
                    }                                              //~4629I~
                    count++;                                       //~4629I~
                }                                                  //~4629I~
                namelist=new String[count+1];  //1 for cd                      //~4629I~//~4630R~
                attrlist=new String[count+1];                      //~4630R~
                int pos=dirname.lastIndexOf("/");                  //~4630I~//~4703M~
                String cd;                                         //~4630I~//~4703M~
                if (pos>=0)                                        //~4630I~//~4703M~
                	cd=dirname.substring(pos+1);                   //~4630I~//~4703M~
                else                                               //~4630I~//~4703M~
                	cd="/";                                        //~4630I~//~4703M~
                namelist[0]="./ ("+cd+")";                //~4629I~//~4630R~//~4703M~
                attrlist[0]=getAttr(fileCurDir);                   //~4701I~//~4703M~
                name2file[0]=-1;                                   //~4630I~//~4703M~
	            count = 1;                                     //~4629I~//~4630R~//~4703M~
                for (File file : filelist)                         //~4703I~
                {                                                  //~4703I~
                    name=file.getName();                           //~4703I~
		        	if (Dump.Y) Dump.println("FileDialog file="+name);//~4703I~
                    if(file.isDirectory())                         //~4703I~
                    	name+="/";	//linux File.pathSeparator;    //~4703I~
                    else                                           //~4703I~
                    {                                              //~4703I~
                    	if (!isFilterAccept(name))                 //~4704I~
                        {                                          //~vb01R~
                        	filelistctr++;                         //~vb01R~
                        	continue;                              //~4703I~
                        }                                          //~vb01R~
                    }                                              //~4703I~
                    namelist[count]=name;                         //~4703I~
                    attrlist[count]=getAttr(file);                 //~4703I~
//                  if (Dump.Y)  Dump.println("beforeSort #+"+count+"="+name+"="+filelistctr);//~vayiI~//~vb01R~
                    name2file[count++]=filelistctr++;               //~4703I~
                }                                                  //~4703I~
                filesort(namelist,attrlist,name2file);             //~4703I~
            }                                                      //~4629I~
        }                                                          //~4629I~
        catch(SecurityException e)                                 //~4629I~
        {                                                          //~4629I~
            Dump.println(e,"AexeFileChooser:createList");          //~4701I~
            Utils.showToast(R.string.FailedListDirBySecurity);     //~4629I~
		    clearListData();                                       //~4701I~
        }                                                          //~4629I~
		catch(Exception e)                                         //~4629I~
		{                                                          //~4629I~
            Dump.println(e,"AexeFileChooser:createList");          //~4701I~
            Utils.showToast(R.string.FailedListDir);               //~4629I~
		    clearListData();                                       //~4701I~
        }                                                          //~4629I~
    }//createList                                                  //~4629I~
    //**********************************                           //~4630I~
    //* init ListView                                              //~4630I~
    //**********************************                           //~4630I~
    private String getAttr(File Pfh)                               //~4630I~
    {                                                              //~4630I~
    	long sz=Pfh.length();	//0 for dir                        //~4630I~
        long ts=Pfh.lastModified();	//milisec from 1970/01/01GT    //~4630I~
        Date dt=new Date(ts);                                      //~4630I~
        String sdt=fmtdate.format(dt);                             //~4630I~
        String ssz,unit,rcs;                                       //~4701R~
        if(Pfh.isDirectory())                                     //~4701I~
        {                                                          //~4701I~
            unit=" ";                                              //~4701I~
            ssz=" ";                                               //~4701I~
        }                                                          //~4701I~
        else                                                       //~4701I~
        {                                                          //~4701I~
            if (sz>=1000000000)     //xGB                              //~4630I~//~4701R~
            {                                                          //~4630I~//~4701R~
                sz/=1000000;            //by MB                        //~4630I~//~4701R~
                unit="M";                                              //~4630I~//~4701R~
            }                                                          //~4630I~//~4701R~
            else                                                       //~4630I~//~4701R~
            if (sz>=1000000)        //xMB                              //~4630I~//~4701R~
            {                                                          //~4630I~//~4701R~
                sz/=1000;           //by KB                            //~4630I~//~4701R~
                unit="K";                                              //~4630I~//~4701R~
            }                                                          //~4630I~//~4701R~
            else                                                       //~4630I~//~4701R~
                unit=" ";                                              //~4630I~//~4701R~
            ssz=fmtsz.format(sz);                                 //~4630R~//~4701R~
        }                                                          //~4701I~
        ssz=String.format("%s%7s ",unit,ssz);                        //~4630I~//~4701R~
        rcs=sdt+" "+ssz;                                           //~4701I~
    	if (Dump.Y) Dump.println("AxeLstFileChooser:getAttr rcs="+rcs);//~4701R~
        return rcs;                                                //~4701R~
    }                                                              //~4630I~
    //**********************************                           //~4629I~
    //* init ListView                                              //~4629I~
    //**********************************                           //~4629I~
    private void setList()                                         //~4629I~
    {                                                              //~4629I~
        int sz=namelist.length;                                    //~4629I~
        for (int ii=0;ii<sz;ii++)                                  //~4629I~
        {                                                          //~4629I~
            add(namelist[ii],attrlist[ii]);                                  //~4629I~//~4630R~
        }                                                          //~4629I~
   }                                                              //~4629I~
    //**********************************                           //~4630I~
    private void clearListData()                                       //~4630I~
    {                                                              //~4630I~
        oldFocusView=null;                                         //~4703I~
        posSelected=-1;                                            //~4703I~
        namelist=null;                                             //~4630I~
        attrlist=null;                                             //~4630I~
    	oldFocusView=null;                                         //~4704I~
    }                                                              //~4630I~
    //**********************************                           //~4629I~
    private void updateList(String Pdirname)                       //~4629I~
    {                                                              //~4629I~
    	if (Dump.Y) Dump.println("FileDialog:updateAdapter dir="+Pdirname);//~4629I~
	    setTitleFilename(Pdirname);	//lis will be cleared at next append//~4629I~
        clearListData();                                               //~4630R~//~4701R~
    	createList(Pdirname);                                      //~4629I~//~4701M~
        if (namelist==null)                                        //~4629I~//~4701M~
        	return;                                                //~4629I~//~4701M~
        super.clearList();                                         //~4701I~
    	setList();                                                 //~4629I~
        super.resetAdapter();                                      //~4701I~
		selectPos0();                                               //~4704I~
        AxeProp.putPreference(AxeProp.GAMEFILE,Pdirname);          //~4629R~
    }//FileList                                                    //~4629I~
	//***************************************                      //~4629I~
    private void setTitleFilename(String Pdirname)                 //~4629I~
    {                                                              //~4629I~
    	Sdialog.setTitle(Pdirname);  //of Dialog                    //~4629I~//~4630R~
    }                                                              //~4629I~
    //**********************************************************************//~4630I~
    //**********************************************************************//~4630I~
    //**********************************************************************//~4630I~
    public String getSelectedFilename()                            //~4630I~
    {                                                              //~4630I~
        if (posSelected>=0 && posSelected<namelist.length)         //~4630R~
            return namelist[posSelected];                          //~4630R~
        else                                                       //~4630R~
        {                                                          //~4630R~
            Utils.showToast(R.string.Warning_ItemNotSelected);     //~4630R~
            return "";                                             //~4630R~
        }                                                          //~4630R~
    }                                                              //~4630I~
    //**********************************************************************//~4630I~
    //*chk list selection                                          //~4630I~
    //*rc:-1:err,0 file selected,1:dir selected,3:currentdir//~4630I~//~vaxcR~
    //**********************************************************************//~4630I~
    public int chkSelectedFilename()                               //~4630R~
    {                                                              //~4630I~
        int pos,rc=0;                                              //~4630I~
    //************                                                 //~4630I~
    	selectedFilename=null;                                     //~4630I~
        pos=posSelected;                                           //~4630R~
        selectedFileFullpath=dirname;//for the case none is selected(rc=-1),cdir(rc=3)//~vaxcI~
        if (Dump.Y) Dump.println("FileDialog selectedPos="+pos);   //~4630I~
        if (pos<0)                                                 //~4630I~
        	return -1;                                             //~4630I~
        if (pos==0)	//currentdir                                   //~4630R~
        {                                                          //~4630I~
            updownpath=dirname;                                    //~4630I~
        	rc=3;                                                  //~4630I~
        }                                                          //~4630I~
        else                                                       //~4630I~
        {                                                          //~4630I~
            int filelistpos=name2file[pos];                        //~4630I~
    		if (Dump.Y) Dump.println("chkSelectedFilenqame inx="+filelistpos+",name2file="+name2file.toString()+",filelist="+filelist.toString());//~vayiI~
            selectedFile=filelist[filelistpos];                    //~4630I~
            selectedFileFullpath=selectedFile.getAbsolutePath();   //~vaxcI~
            if(selectedFile.isDirectory())                         //~4630I~
            {                                                      //~4630I~
                updownpath=selectedFileFullpath;         //~4630I~ //~vaxcR~
                rc=1;                                              //~4630I~
            }                                                      //~4630I~
            else                                                   //~4630I~
            {                                                      //~4630I~
            	selectedFilename=selectedFile.getName();           //~4630I~
            }                                                      //~4630I~
        }                                                          //~4630I~
                                                                   //~4630I~
        if (Dump.Y) Dump.println("FileDialog chkSelectedfile rc="+rc+",name="+selectedFilename);//~4630I~
        return rc;                                                 //~4630I~
    }//chkSelectedFilename                                         //~vaxcR~
    //**********************************************************************//~1403I~//~4629I~//~vaxbM~//~4630M~
    //* when touch already selected item                                       //~4630R~//~vaxcR~
    //**********************************************************************//~1403I~//~4629I~//~vaxbM~//~4630M~
    private boolean openSelected(int Ppos)                                 //~1403I~//~4629I~//~vaxbM~//~4630R~
    {                                                              //~1403I~//~4629I~//~vaxbM~//~4630M~
    	int selectedstatus;                                        //~1403I~//~4629I~//~vaxbM~//~4630M~
        boolean rc=false;                                          //~1403I~//~4629I~//~vaxbM~//~4630M~
    //***                                                          //~1403I~//~4629I~//~vaxbM~//~4630M~
	    if (Dump.Y) Dump.println("AxeDlgFileChooser openSelected");//~4630I~
    	selectedstatus=chkSelectedFilename();                      //~1403I~//~4629I~//~vaxbM~//~4630I~
        switch(selectedstatus)                                     //~1403I~//~4629I~//~vaxbM~//~4630M~
        {                                                          //~1403I~//~4629I~//~vaxbM~//~4630M~
        case -1:	//no selection                                 //~1403R~//~4629I~//~vaxbM~//~4630M~
            break;                                                 //~4630I~
        case 0:	//file selected	                                   //~1403I~//~4629I~//~vaxbM~//~4630M~
              Utils.showToast(R.string.Warning_PressButtonToProcessTheFile);//~vaxcI~
            break;                                                 //~1403I~//~4629I~//~vaxbM~//~4630M~
        case 1:	//dir was selected                                 //~4630I~
    		updateList(updownpath);                                //~4630I~
            break;                                                 //~1403I~//~4629I~//~vaxbM~//~4630M~
        case 3:	//cdir	for SaveAs                                 //~4630I~
              Utils.showToast(R.string.Warning_PressButtonToProcessCurrentDir);//~vaxcI~
            break;                                                 //~4630I~
        }                                                          //~1403I~//~4629I~//~vaxbM~//~4630M~
        return rc;                                                 //~1403I~//~4629I~//~vaxbM~//~4630M~
    }//openSelected                                                              //~1403I~//~4629I~//~vaxbM~//~4630M~//~vaxcR~
    //**********************************************************************//~1402I~//~4629I~//~vaxbM~//~4630M~
    //* chk selected line                                          //~vaxcR~
    //*rc:-1:err,0 file selected,1:dir selected,3:currentdir       //~vaxcI~
    //**********************************************************************//~1402I~//~4629I~//~vaxbM~//~4630M~
    public String getSelectedFullpath(int Pbuttonid,String Pmember)                                 //~1403R~//~4629I~//~vaxbM~//~4630R~//~vaxcR~
    {                                                              //~1402I~//~4629I~//~vaxbM~//~4630M~
    	int selectedstatus;                                        //~1403I~//~4629I~//~vaxbM~//~4630M~
        String fpath;                                              //~vaxcI~
    //***                                                          //~1403I~//~4629I~//~vaxbM~//~4630M~
    	selectedstatus=chkSelectedFilename();                      //~4630I~
        if (Dump.Y) Dump.println("FileDialog getSelectedPath path="+selectedFileFullpath);//~vaxcR~
        if (selectedstatus==0)	//file was selected                //~vaxcI~
        {                                                          //~vaxcI~
	        fpath=selectedFileFullpath;	//file or dir fullpathname //~vaxcI~
        }                                                          //~vaxcI~
        else //dir selected                                        //~vaxcI~
        {                                                          //~vaxcI~
        	if (Pbuttonid==R.id.Save                               //~vaxcR~
        	||  Pbuttonid==R.id.Close)  //Download reference       //~vaxcI~
            	if (Pmember==null||Pmember.length()==0)            //~vaxcI~
                {                                                  //~vaxcI~
					Utils.showToast(R.string.Warning_MissingFilenameForSave);//~vaxcI~
                    return null;                                   //~vaxcI~
                }                                                  //~vaxcI~
            if (Pmember!=null)                                     //~vaxcI~
              if (selectedFileFullpath.endsWith("/"))              //~vayiI~
				fpath=selectedFileFullpath+Pmember;                //~vayiI~
              else                                                 //~vayiI~
				fpath=selectedFileFullpath+"/"+Pmember;            //~vaxcR~
            else
        		fpath=selectedFileFullpath;
        }                                                          //~vaxcI~
        return fpath;                                              //~vaxcR~
    }                                                              //~1402I~//~4629I~//~vaxbM~//~4630M~
    //**********************************************************************//~4630I~
    //* detail                                                     //~4630I~
    //**********************************************************************//~4630I~
    public void toggleDetail()                                     //~4630I~
    {                                                              //~4630I~
    	swDetail=!swDetail;                                        //~4630I~
	    if (Dump.Y) Dump.println("AxeDlgFileChooser toggleDetail to="+swDetail);//~4630I~
        resetAdapter();	//notify changed                           //~4703R~
        AxeProp.putPreference(DIRLISTSHOWATTR,swDetail?1:0);       //~4703I~
    }                                                              //~4630I~
    //**********************************************************************//~4630I~
    //* detail                                                     //~4630I~
    //**********************************************************************//~4630I~
    public void listParent()                                       //~4630I~
    {                                                              //~4630I~
	    if (Dump.Y) Dump.println("AxeDlgFileChooser toggleDetail to="+swDetail);//~4630I~
        String parent=(new File(dirname)).getParent();             //~4630I~
        if (parent==null)                                          //~4630I~
        {                                                          //~4630I~
            Utils.showToast(R.string.Warning_ReachedToRoot);       //~4630I~
            return;                                                //~4630I~
        }                                                          //~4630I~
		updateList(parent);                                        //~4630R~
    }                                                              //~4630I~
    //**********************************************************************//~4630I~
    //* detail                                                     //~4630I~
    //**********************************************************************//~4630I~
    public void setNewName(int Ppos)                               //~4630I~
    {                                                              //~4630I~
	    if (Dump.Y) Dump.println("AxeDlgFileChooser setNewName pos="+Ppos);//~4630I~
        if (Ppos<=0||Ppos>=namelist.length)                        //~4630I~
        	return;                                                //~4630I~
        String fnm=namelist[Ppos];                                 //~4630I~
        if (fnm.charAt(fnm.length()-1)=='/')  //dir entry            //~4630I~
        	return;                                                //~4630I~
        Sdialog.setNewName(fnm);                                   //~4630I~
    }                                                              //~4630I~
    //**********************************************************************//~4703I~
    //* Sort                                                       //~4703I~
    //**********************************************************************//~4703I~
    private void filesort(String[] Pnamelist,String[] Pattrlist,int[] Pidx)//~4703I~
    {                                                              //~4703I~
    	ArrayList<FileSortEntry> al;                               //~4703I~
        boolean swdir;                                             //~4703I~
        String name;                                               //~4703I~
    //**********************************                           //~4703I~
    	if (Dump.Y) Dump.println("filesort namelist="+Pnamelist.toString()+",index="+Pidx.toString()+",filelist="+filelist.toString());//~vayiI~
    	al=new ArrayList<FileSortEntry>();                            //~4703I~
    	for (int ii=0;ii<Pnamelist.length;ii++)                        //~4703I~
        {                                                          //~4703I~
        	name=Pnamelist[ii];                                        //~4703I~
        	if (name.charAt(name.length()-1)=='/')  //dir entry    //~4703I~
            {	                                                   //~4703I~
            	name=name.substring(0,name.length()-1);            //~4703I~
                swdir=true;                                        //~4703I~
            }                                                      //~4703I~
            else                                                   //~4703I~
            {                                                      //~4703I~
            	name=new String(name);                             //~4703I~
            	swdir=false;                                       //~4703I~
            }                                                      //~4703I~
        	al.add(new FileSortEntry(swdir,name,Pattrlist[ii],Pidx[ii]));//~4703I~
//          if (Dump.Y) Dump.println("filesort ii="+ii+",name="+name+",idx="+Pidx[ii]);//~vayiI~//~vb01R~
        }                                                          //~4703I~
        Collections.sort(al,new FileSortCompare());                    //~4703I~
        Iterator<FileSortEntry> it=al.iterator();                  //~4703I~
        int ii=0;                                                  //~4703I~
        while(it.hasNext())                                        //~4703I~
        {                                                          //~4703I~
        	FileSortEntry e=it.next();                             //~4703I~
        	Dump.println("sorted "+e.swdir+"-"+e.name);            //~4703I~
            if (e.swdir)                                           //~4703I~
            	Pnamelist[ii]=e.name+"/";                          //~4703I~
            else                                                   //~4703I~
            	Pnamelist[ii]=e.name;                              //~4703I~
            Pattrlist[ii]=e.attr;                                  //~4703I~
            Pidx[ii]=e.idx;                                        //~4703I~
//          if (Dump.Y) Dump.println("afterSort #+"+ii+"="+Pnamelist[ii]+"="+Pidx[ii]);//~vayiR~//~vb01R~
            ii++;                                                  //~4703I~
        }                                                          //~4703I~
        Sdialog.sorted(sortType);                                  //~4704I~
    }                                                              //~4703I~
    //**********************************************************************//~4704I~
    class FileSortEntry                                            //~4703I~
    {                                                              //~4703I~
    	public String name,attr;                                   //~4703I~
        public int idx;                                            //~4703I~
        public boolean swdir;                                      //~4703I~
        public FileSortEntry(boolean Pswdir,String Pname,String Pattr,int Pidx)//~4703I~
        {                                                          //~4703I~
        	swdir=Pswdir;name=Pname;attr=Pattr;idx=Pidx;           //~4703I~
        }                                                          //~4703I~
    }                                                              //~4703I~
    //**********************************************************************//~4704I~
    class FileSortCompare implements Comparator<FileSortEntry>     //~4703I~
    {                                                              //~4703I~
        public int compare(FileSortEntry Pent1,FileSortEntry Pent2)//~4703I~
        {                                                          //~4703I~
        	int rc=0;                                              //~4703R~
            boolean swdescending;                                  //~4704I~
        //**************************                               //~4704I~
            swdescending=(sortType==SORT_FILE_D||sortType==SORT_ATTR_D);//~4704I~
            if (Pent1.idx<0)	//cur dir                          //~4703I~
            	rc=-1;                                             //~4703R~
            else                                                   //~4703I~
            if (Pent2.idx<0)	//cur dir                          //~4703I~
            	rc=1;                                              //~4703R~
            else                                                   //~4703I~
            if (Pent1.swdir)                                       //~4703I~
            {                                                      //~4703I~
            	if (!Pent2.swdir)                                  //~4703I~
            		rc=(swdescending?1:-1);                        //~4704I~
            }                                                      //~4703I~
            else                                                   //~4703I~
            {                                                      //~4703I~
            	if (Pent2.swdir)                                   //~4703I~
            		rc=(swdescending?-1:1);                        //~4704I~
            }                                                      //~4703I~
            if (rc==0)                                             //~4703I~
            {                                                      //~4703I~
        		switch(sortType)                                   //~4703I~
                {	                                               //~4703I~
                case SORT_FILE_A:                                  //~4703I~
	            	rc=Pent1.name.compareTo(Pent2.name);           //~4703I~
                    break;                                         //~4703I~
                case SORT_FILE_D:                                  //~4703I~
	            	rc=-Pent1.name.compareTo(Pent2.name);          //~4703I~
                    break;                                         //~4703I~
                case SORT_ATTR_A:                                 //~4703I~
	            	rc=Pent1.attr.compareTo(Pent2.attr);           //~4703I~
                    break;                                         //~4703I~
                case SORT_ATTR_D:                                  //~4703I~
	            	rc=-Pent1.attr.compareTo(Pent2.attr);          //~4703I~
                    break;                                         //~4703I~
                default:                                           //~4703I~
	            	rc=Pent1.name.compareTo(Pent2.name);           //~4703I~
                }                                                  //~4703I~
            }                                                      //~4703I~
            return rc;                                             //~4703I~
        }                                                          //~4703I~
    }//subclass                                                    //~4703R~
    //**********************************************************************//~4704I~
    public void sortList(int Pcols)                               //~4703I~//~4704R~
    {                                                              //~4703I~
    	ArrayList<FileSortEntry> al;                               //~4703I~
        boolean swdir;                                             //~4703I~
        String name;                                               //~4703I~
        int cols,msgid;                                            //~4704R~
        String[] nml,atl;                                            //~4704I~
        int   [] ixl;                                               //~4704I~
    //**********************************                           //~4703I~
    	cols=Pcols;                                                //~4704R~
    	if (namelist==null || namelist.length==0)                  //~4703I~
        	return;                                                //~4703I~
    	if (cols==1)	//by name                                  //~4703I~//~4704R~
        {                                                          //~4703I~
            if (sortType==SORT_FILE_A)                             //~4703I~
            	sortType=SORT_FILE_D;                              //~4703I~
            else                                                   //~4703I~
            	sortType=SORT_FILE_A;                              //~4703I~
        }                                                          //~4703I~
        else                                                       //~4703I~
        {                                                          //~4703I~
            if (sortType==SORT_ATTR_A)                             //~4703I~
            	sortType=SORT_ATTR_D;                              //~4703I~
            else                                                   //~4703I~
            	sortType=SORT_ATTR_A;                              //~4703I~
        }                                                          //~4703I~
        ixl=name2file;                                              //~4704I~
        nml=namelist;                                               //~4704I~
        atl=attrlist;                                               //~4704I~
        clearListData();                                           //~4704I~
    	filesort(nml,atl,ixl);                     //~4703I~          //~4704R~
        name2file=ixl;                                              //~4704I~
        namelist=nml;                                               //~4704I~
        attrlist=atl;                                               //~4704I~
        super.clearList();                                         //~4704I~
    	setList();                                                 //~4704I~
        super.resetAdapter();                                      //~4704I~
        switch(sortType)                                           //~4704I~
        {                                                          //~4704I~
        case SORT_FILE_D:                                          //~4704I~
            msgid=R.string.Info_Sorted_NameD;                      //~4704I~
            break;                                                 //~4704I~
        case SORT_ATTR_A:                                          //~4704I~
            msgid=R.string.Info_Sorted_AttrA;                      //~4704I~
            break;                                                 //~4704I~
        case SORT_ATTR_D:                                          //~4704I~
            msgid=R.string.Info_Sorted_AttrD;                      //~4704I~
            break;                                                 //~4704I~
        default:                                                    //~4704I~
            msgid=R.string.Info_Sorted_NameA;                      //~4704I~
        }                                                          //~4704I~
		AxeProp.putPreference(DIRLISTSORT,sortType);               //~vb03I~
        Utils.showToast(msgid);                           //~4704I~
    }                                                              //~4703I~
    //**********************************************************************//~4704I~
    private String getFilterString()                               //~4704I~
    {                                                              //~4704I~
    	String rc;                                                 //~4704I~
    //**********************************                           //~4704I~
    	acceptMatch=false;                                         //~4704I~
		rc=Sdialog.getFilterString();                              //~4704I~
        if (rc==null || rc.length()==0)                            //~4704R~
        	return rc;                                             //~4704I~
        if (rc.startsWith("\"") && rc.endsWith("\""))               //~vaxcI~
        {                                                          //~vaxcI~
	    	acceptMatch=true;                                      //~vaxcI~
        	rc=rc.substring(1,rc.length()-1);                      //~vaxcI~
        }                                                          //~vaxcI~
        else                                                       //~vaxcI~
        if (rc.indexOf('*')>=0 || rc.indexOf('?')>=0)              //~vaxcI~
        {                                                          //~vaxcI~
	    	acceptMatch=true;                                          //~4704I~//~vaxcI~
        	rc=rc.replaceAll("\\?",".{1}");    //{1} repaet only onece//~vaxcI~
        	rc=rc.replaceAll("\\*",".+");      //"+"repeat over 1  //~vaxcI~
        }                                                          //~vaxcI~
        else                                                       //~vaxcI~
        {                                                          //~vaxcI~
        	extentionList=rc.split("\\|");                          //~vaxcR~
        }                                                          //~vaxcI~
		return rc;                                                 //~4704I~
    }                                                              //~4704I~
    //**********************************************************************//~4704I~
    public boolean isFilterAccept(String Pname)                    //~4704I~
    {                                                              //~4704I~
        boolean rc=false;                                          //~4704I~
    //**********************************                           //~4704I~
    	if (filterString==null || filterString.length()==0)              //~4704I~
        	rc=true;                                               //~4704I~
        else                                                       //~4704I~
        {                                                          //~4704I~
        	if (acceptMatch)                                       //~4704I~
        		rc=Pname.matches(filterString);                    //~4704M~
            else                                                   //~4704I~
            {                                                      //~vaxcI~
            	for (int ii=0;ii<extentionList.length;ii++)               //~vaxcI~
                {                                                  //~vaxcI~
                	if (extentionList[ii].length()>0)              //~vaxcI~
                    {                                              //~vaxcI~
        				rc=Pname.endsWith(extentionList[ii]);                   //~4704M~//~vaxcR~
                        if (rc)	//match                            //~vaxcI~
                        	break;                                 //~vaxcI~
                    }                                              //~vaxcI~
                }                                                  //~vaxcI~
            }                                                      //~vaxcI~
        }                                                          //~4704I~
        return rc;                                                 //~4704I~
    }                                                              //~4704I~
    //**********************************************************************//~4704I~
    public void applyFilter()                                      //~4704R~
    {                                                              //~4704I~
    	updateList(dirname);                                       //~4704I~
    }                                                              //~4704I~
    //**********************************************************************//~4704I~
    public void selectPos0()                                       //~4704I~
    {                                                              //~4704I~
    	listView.post(new Runnable()  //need to another thread to getChildAt() returns !=null                             //~4704R~//~4705R~
        			{                                              //~4704I~
                        @Override                                  //~4704I~
                        public void run()                          //~4704I~
                        {                                          //~4704I~
        					try                                    //~vaxcR~
        					{                                      //~vaxcI~
                                View v=listView.getChildAt(0);     //~vaxcR~
                                if (v!=null)                       //~vaxcR~
                                {                                  //~vaxcR~
                                    long id=listView.getItemIdAtPosition(0);//~vaxcR~
                                    listView.performItemClick(v,0,id);//~vaxcR~
                                }                                  //~vaxcR~
        					}                                      //~vaxcR~
        					catch(Exception e)                     //~vaxcI~
							{                                      //~vaxcR~
            					Dump.println(e,"AexeFileChooser:createList");//~vaxcR~
        					}                                      //~vaxcR~
                        }                                          //~4704I~//~4705M~
                    });                                            //~4704I~
    }//selectPos0                                                  //~vaxcR~
}//class
