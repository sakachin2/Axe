package com.xe.Axe;                                                //~1A09I~
                                                                   //~1A09I~
import java.io.*;                                                  //~1A09I~
                                                                   //~1A09I~
//********************************************************************************************//~1A09I~
//*get NLS text from sdcard(/mnt/sdcard/Axe/nls/text_xx(xx:language like as "de", "ja")//~1A09I~
//********************************************************************************************//~1A09I~
public class Nls                                                   //~1A09I~
{                                                                  //~1A09I~
	private static final String FOLDER="nls";                      //~1A09I~
	private static String folderdefault;                           //~1A09I~
	private static String folder;                                  //~1A09I~
	private static int folderexist=-1;                             //~1A09I~
	private static String seps=System.getProperty("file.separator");//~1A09R~
	//***************************************************************//~1A09I~
	public static String getText(int Presid,int Pnameid)           //~1A09I~
	{                                                              //~1A09I~
        String fnm=AxeG.resource.getString(Pnameid);               //~1A09I~
		return getText(Presid,fnm);                                //~1A09I~
    }                                                              //~1A09I~
	//***************************************************************//~1A09I~
	public static String getText(int Presid,String Pfilename)      //~1A09I~
	{                                                              //~1A09I~
    	String txt=null;                                           //~1A09I~
        if (folderexist<0)                                         //~1A09I~
        	setFolder();                               //~1A09I~
    	if (folderexist>0)                                         //~1A09R~
    		txt=getText(Pfilename);                                //~1A09I~
        if (txt==null)                                             //~1A09I~
        	txt=AxeG.resource.getString(Presid);                   //~1A09I~
        if (Dump.Y) Dump.println("getText:"+txt);                  //~1A09I~
		return txt;                                                //~1A09I~
    }                                                              //~1A09I~
	//***************************************************************//~1A09I~
	private static void setFolder()                                //~1A09I~
    {                                                              //~1A09I~
    	folderexist=0;                                             //~1A09I~
    	folderdefault=FOLDER+seps+FOLDER;                          //~1A09I~
    	folderdefault=AxeProp.getSDPath(folderdefault);	// /mnt/sdcard/nls/nls//~1A09I~
    	folder=FOLDER+seps+FOLDER+"_"+AxeG.language.substring(0,2);	//ja of ja_JP//~1A09I~
    	folder=AxeProp.getSDPath(folder);	// /mnt/sdcard/nls/nls_xx//~1A09I~
        if (Dump.Y) Dump.println("nld folder="+folder+",default="+folderdefault);//~1A09I~
        File f=new File(folder);                                   //~1A09I~
        if (!f.exists())                                           //~1A09I~
        {    
        		folder=null;//~1A09I~
             File f2=new File(folderdefault);
        	 if (!f2.exists())                                     //~1A09R~
            {                                                      //~1A09I~
                if (Dump.Y) Dump.println("No nls folder");//~1A09I~
                folderdefault=null;                                       //~1A09I~
            }                                                      //~1A09I~
            else                                                   //~1A09I~
	        	folderexist=1;                                     //~1A09I~
        }                                                          //~1A09I~
        else                                                       //~1A09I~
        	folderexist=1;                                         //~1A09I~
    }                                                              //~1A09I~
	//***************************************************************//~1A09I~
	private static String getText(String Psubject)                 //~1A09I~
    {                                                              //~1A09I~
    	String textfile=Psubject.replace(' ','_')+".txt";          //~1A09I~
    	String fnm=folder+seps+textfile;	//nls_xx/subject.txt   //~1A09R~
    	String fnmd=folderdefault+seps+textfile;	//nls_xx/subject.txt//~1A09R~
        String txt="";                                             //~1A09I~
        if (!new File(fnm).exists())                               //~1A09R~
        	if (new File(fnmd).exists())                           //~1A09R~
        		fnm=fnmd;                                          //~1A09R~
            else                                                   //~1A09I~
	        	return null;                                       //~1A09R~
		try                                                        //~1A09I~
		{	BufferedReader in;                                     //~1A09I~
			String s;                                              //~1A09I~
			try                                                    //~1A09I~
        	{	in=getEncodedStream(fnm);                   //~1A09I~
				s=in.readLine();                                   //~1A09I~
			}                                                      //~1A09I~
			catch (Exception e)                                    //~1A09I~
			{                                                      //~1A09I~
				Dump.println(e,"Nls:getText 1st");                 //~1A09R~
				return null;                                       //~1A09R~
			}                                                      //~1A09I~
			while (s!=null)                                        //~1A09I~
			{	txt+=s;                                            //~1A09I~
				s=in.readLine();                                   //~1A09I~
			}                                                      //~1A09I~
			in.close();                                            //~1A09I~
		}                                                          //~1A09I~
		catch (Exception e)                                        //~1A09I~
		{                                                          //~1A09I~
			Dump.println(e,"Nls:getText");                         //~1A09I~
			return null;                                           //~1A09I~
		}                                                          //~1A09I~
        if (Dump.Y) Dump.println("getText:"+txt);                  //~1A09I~
        return txt;                                                //~1A09I~
	}                                                              //~1A09I~
	//***************************************************************//~1A09I~
	public static BufferedReader getEncodedStream (String filename)//~1A09I~
	{                                                              //~1A09I~
		String encoding=AxeG.encoding;                             //~1A09I~
		if (encoding==null || encoding.equals(""))                 //+1A09R~
			return getStream(filename);                            //+1A09I~
		return getStream(filename,encoding);                       //+1A09I~
	}                                                              //~1A09I~
	//***************************************************************//~1A09I~
	public static BufferedReader getStream (String filename, String encoding)//~1A09I~
	{	try                                                        //~1A09I~
		{	return new BufferedReader(                             //~1A09I~
				new InputStreamReader(getDataStream(filename),encoding));//~1A09I~
		}                                                          //~1A09I~
		catch (UnsupportedEncodingException e)                     //~1A09I~
		{                                                          //~1A09I~
            Dump.println(e,"Nls:getStream");                       //~1A09I~
			return getStream(filename);                            //~1A09I~
		}                                                          //~1A09I~
	}                                                              //~1A09I~
	                                                               //~1A09I~
	public static BufferedReader getStream (String filename)       //~1A09I~
	{	return new BufferedReader(                                 //~1A09I~
			new InputStreamReader(getDataStream(filename)));       //~1A09I~
	}                                                              //~1A09I~
	public static InputStream getDataStream (String filename)      //~1A09I~
	{	try                                                        //~1A09I~
		{                                                          //~1A09I~
			return new FileInputStream(filename);                  //~1A09I~
		}                                                          //~1A09I~
		catch (Exception e)                                        //~1A09I~
		{                                                          //~1A09I~
        	Dump.println(e,"Nls:getDataStream"+filename);          //~1A09I~
			return null;                                           //~1A09I~
		}                                                          //~1A09I~
	}                                                              //~1A09I~
	                                                               //~1A09I~
}                                                                  //~1A09I~
