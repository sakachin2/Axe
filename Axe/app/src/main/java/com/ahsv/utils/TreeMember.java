//*CID://+vc4uR~:                             update#=  203;       //~vc4uR~
//************************************************************************//~v102I~
//vc4u 2023/04/25 generate "." and ".." entry for udirlist         //~vc4uI~
//vc4t 2023/04/24 fstat has to allow membername of wildcard        //~vc4tI~
//vc4s 2023/04/15 edit using SAF                                   //~vc4sI~
//vc4r 2023/04/14 rename using SAF                                 //~vc4rI~
//vc4q 2023/04/01 support shared storage using SAF(StorageAccessFramework)//~vc4qI~
//************************************************************************//~v102I~
//*access to shared storage                                        //~vc4qI~
//************************************************************************//~vc4qI~
package com.ahsv.utils;                                            //~vc09R~

import static android.provider.DocumentsContract.Document.*;        //~vc4qR~
import android.database.Cursor;
import android.provider.DocumentsContract;
import androidx.documentfile.provider.DocumentFile;                //~vc4qI~
                                                //~vc09R~
import com.xe.Axe.Dump;                                            //~vc09R~
import static com.ahsv.utils.USAF.*;                               //~vc4uI~
    public class TreeMember                                               //~vc4qI~//~vc4uM~
    {                                                              //~vc4qI~//~vc4uM~
    private static final String CN="TreeMember.";                  //~vc4uI~
//********************************************************         //~vc4qI~
	public static int TMF_ITSELF         =0x01;                    //~vc4uR~
	public static int TMF_ENTRY_PATH     =0x02;                    //~vc4uR~
	public static int TMF_ENTRY_PARENT   =0x04;                    //~vc4uR~
    public static final String[] COLOMN_DOCFILE=                  //~vc4qI~//~vc4uI~
				{DocumentsContract.Document.COLUMN_DOCUMENT_ID,    //~vc4qI~//~vc4uM~
                 DocumentsContract.Document.COLUMN_DISPLAY_NAME,   //~vc4qI~//~vc4uM~
                 DocumentsContract.Document.COLUMN_LAST_MODIFIED,  //~vc4qI~//~vc4uM~
                 DocumentsContract.Document.COLUMN_SIZE,           //~vc4qI~//~vc4uM~
                 DocumentsContract.Document.COLUMN_MIME_TYPE,      //~vc4qI~//~vc4uM~
                 DocumentsContract.Document.COLUMN_FLAGS,          //~vc4qI~//~vc4uM~
                };                                                 //~vc4qI~//~vc4uM~
    public static final int COLIDX_ID         =0;                 //~vc4qI~//~vc4uI~
    public static final int COLIDX_NAME       =1;                 //~vc4qI~//~vc4uI~
    public static final int COLIDX_MODIFIED   =2;                 //~vc4qI~//~vc4uI~
    public static final int COLIDX_SIZE       =3;                 //~vc4qI~//~vc4uI~
    public static final int COLIDX_MIME       =4;                 //~vc4qI~//~vc4uI~
    public static final int COLIDX_FLAGS      =5;                 //~vc4qI~//~vc4uI~
                                                                   //~vc4qI~//~vc4uM~
//********************************************************         //~vc4uI~
    public static TreeMember newTreeMember(Cursor Pcsr, int[] PidxCol)//~vc4qR~//~vc4uM~
    {                                                              //~vc4qI~//~vc4uM~
        String docID=Pcsr.getString(PidxCol[COLIDX_ID]);              //~vc4qR~
        String name=Pcsr.getString(PidxCol[COLIDX_NAME]);          //~vc4qI~
        String mime=Pcsr.getString(PidxCol[COLIDX_MIME]);          //~vc4qI~
        long modified=Pcsr.getLong(PidxCol[COLIDX_MODIFIED]);      //~vc4qI~
        long size=Pcsr.getLong(PidxCol[COLIDX_SIZE]);              //~vc4qI~
        int flags=Pcsr.getInt(PidxCol[COLIDX_FLAGS]);              //~vc4qI~
        TreeMember memb=new TreeMember(name,modified,size);        //~vc4qI~
        memb.docID=docID;                                          //~vc4qR~
        memb.flags=flags;                                          //~vc4qI~
        memb.mimeType=mime;                                        //~vc4qI~
        memb.swDir=mime!=null && mime.equals(MIME_TYPE_DIR);            //~vc4qI~
        memb.setAttr(name);                                            //~vc4qI~//~vc4tR~
        if (Dump.Y) Dump.println(CN+"TreeMember.newTreeMember by cursor "+memb.toString());//~vc4qR~
        return memb;                                               //~vc4qR~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
    public static TreeMember newTreeMember(int Popt,String Pfpath,DocumentFile Pdoc,String PdocID)//~vc4qR~//~vc4tR~//~vc4uR~
    {                                                              //~vc4qI~
    	long size=Pdoc.length();                                   //~vc4qI~
    	long modified=Pdoc.lastModified();                         //~vc4qI~
        boolean swWrite=Pdoc.canWrite();                            //~vc4qI~
        String name;                                               //~vc4uR~
        if ((Popt & TMF_ENTRY_PATH)!=0)                            //~vc4uI~
        	name=".";                                              //~vc4uI~
        else                                                       //~vc4uI~
        if ((Popt & TMF_ENTRY_PARENT)!=0)                          //~vc4uI~
        	name="..";                                             //~vc4uI~
        else                                                       //~vc4uI~
        	name=Pdoc.getName();                                   //~vc4uI~
        if (Dump.Y) Dump.println(CN+"TreeMember.newTreeMember neme="+name+",getName="+Pdoc.getName());//+vc4uI~
	    boolean swDir=false;                                       //~vc4tI~
        if ((Popt & TMF_ITSELF)!=0)                                //~vc4uI~
	        swDir=Pdoc.isDirectory();                          //~vc4qI~//~vc4tR~
        String mime=Pdoc.getType();	//maybe null                   //~vc4qR~
        if (swDir && mime==null)                                   //~vc4qI~
        	mime=MIME_TYPE_DIR;                                    //~vc4qI~
    	int flags=0;                                               //~vc4qI~
        if (swWrite)                                               //~vc4qI~
	        flags|=FLAG_SUPPORTS_WRITE;                           //~vc4qI~
        TreeMember memb=new TreeMember(name,modified,size);        //~vc4qI~
        memb.fpath=Pfpath;                                         //~vc4tI~
        memb.docID=PdocID==null ? "" : PdocID;                     //~vc4qR~
        memb.flags=flags;                                          //~vc4qI~
        memb.mimeType=mime;                                        //~vc4qI~
        memb.swDir=swDir;                                          //~vc4qR~
        memb.swItself=(Popt & TMF_ITSELF)!=0;                      //~vc4uR~
        memb.setAttr(name);                                            //~vc4qI~//~vc4tR~
        if (Dump.Y) Dump.println(CN+"TreeMember.newTreeMember by Document swDir="+swDir+",docID="+PdocID+",doc="+Pdoc);//~vc4tR~
        if (Dump.Y) Dump.println(CN+"TreeMember.newTreeMember by Document;"+memb.toString());//~vc4tI~
        return memb;                                               //~vc4qI~
    }                                                              //~vc4qI~
//********************************************************         //~vc4qI~
	    private static final int  FILE_NORMAL    =0x0000;                                //~v56sR~//~vc4qR~
    	private static final int  FILE_READONLY  =0x0001;                                //~v327R~//~vc4qR~
    	private static final int  FILE_HIDDEN    =0x0002;                                //~v327R~//~vc4qR~
    	private static final int  FILE_SYSTEM    =0x0004;                                //~v327R~//~vc4qR~
	    private static final int  FILE_VOLID     =0x0008; //dos only                     //~v327R~//~vc4qR~
	    private static final int  FILE_MDOSDISK  =0x0008; //files on disket              //~v50GI~//~vc4qR~
	    private static final int  FILE_DIRECTORY =0x0010;                                //~v327R~//~vc4qR~
	    private static final int  FILE_ARCHIVED  =0x0020;                                //~v327R~//~vc4qR~
    	private static final int  FILE_DOCUMENT  =0x0020; //ARM document file, included in FILE_ALL//~vc4qI~
	    private static final int  FILE_REMOTE    =0x0040; //ftp file                     //~v59dI~//~vc4qR~
	    private static final int  FILE_SPECIAL   =0x0080; //special file select request  //~v56sI~//~vc4qR~
                                                                   //~v59fI~//~vc4qI~
    	private static final int  FILE_FTPWKSL   =0x0100; //work flag for ftp slink process//~v59fI~//~vc4qI~
    	private static final int  FILE_SLINKERR  =0x0200; //loop/duplicated slink entry  //~v6kcR~//~vc4qI~
    	private static final int  FILE_FTPWKSL2  =0x0400; //work flag for slink dup chk  //~v59dI~//~vc4qI~
                                                                   //~vc4tI~
    	private static final int  FILE_WILDNAME  =0x2000; //name is wildcard//~vc4tI~
                                                                   //~vc4qI~
        public String name;                                        //~vc4qR~
        public long lastModified,size;                              //~vc4qR~
        public String mimeType;                                    //~vc4qI~
        public String docID;                                       //~vc4qI~
        public String fpath;                                       //~vc4tI~
        public int flags;                                          //~vc4qI~
        public int attr;                                           //~vc4qI~
        public boolean swDir;                                          //~vc4qI~
        public boolean swItself;                                   //~vc4tI~
        public TreeMember(String Pname,long PlastModified,long Psize)//~vc4qI~
        {                                                          //~vc4qI~
        	name=Pname;  size=Psize;                               //~vc4qI~
//          lastModified=Utils.getTimeStamp("yy.MM.dd-HH.mm.ss",PlastModified);//~vc4qR~
            lastModified=PlastModified;                            //~vc4qI~
        }                                                          //~vc4qI~
        public String toString()                                   //~vc4qI~
        {                                                          //~vc4qI~
        	return "name="+name+",fpath="+fpath+",size="+size+",lastModified="+lastModified+",swDir="+swDir+",attr=0x"+Integer.toHexString(attr)+",mimeType="+mimeType+",docID="+docID;//~vc4qR~//~vc4sR~//~vc4tR~
        }                                                          //~vc4qI~
        public String toStringMemberData()                         //~vc4qI~
        {                                                          //~vc4qI~
        	return name+","+size+","+lastModified+","+attr+","+flags+",";//~vc4qR~
        }                                                          //~vc4qI~
        public void setAttr(String Pname)                                      //~vc4qI~//~vc4tR~
        {                                                          //~vc4qI~
        	attr=FILE_DOCUMENT;                                    //~vc4qR~
//          if (mimeType!=null && mimeType.equals(MIME_TYPE_DIR))  //~vc4qR~
//          	attr|=FILE_DIRECTORY;                              //~vc4qR~
            if (swDir)                                             //~vc4qI~
            	attr|=FILE_DIRECTORY;                              //~vc4qI~
            if ((flags & FLAG_SUPPORTS_WRITE)==0)                   //~vc4qI~
            	attr|=FILE_READONLY;                               //~vc4qI~
            if (isWildcard(Pname))                                 //~vc4tR~
            	attr|=FILE_WILDNAME;                               //~vc4tI~
        }                                                          //~vc4qI~
    }//class                                                              //~vc4qI~//~vc4uR~
