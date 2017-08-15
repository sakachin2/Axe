//*CID://+@@@1R~:                                   update#=   22; //~@@@1I~
//***********************************************                  //~@@@1I~
//@@@1 prepend timestamp/threadid                                  //~@@@1I~
//     exception dump                                              //~@@@1I~
//     optional dump by ajago menu option                          //~@@@1I~
//***********************************************                  //~@@@1I~
package com.xe.Axe;

import java.io.*;
import java.util.*;


/**
A class to generate debug information in a dump file.
It is a class with all static members.
*/

public class Dump
{	static PrintWriter Out=null;
	static boolean Terminal=false;                                           //~1228I~
	static public boolean exceptionOnly=false;                     //~1507R~
	static public boolean Y;   //test before call Dump to avoid memory for parameter String//~1506I~
	/** 
	Open a dump file. If this is not called there will be no
	file dumps.
	*/
	public static void openEx (String file)                        //~1504I~
	{                                                              //~1504I~
		open(file);                                                //~1504I~
    	exceptionOnly=true;                                        //~1506M~
        Y=false;//dont call Dump except case of Exceoption         //~1506I~
    }                                                              //~1504I~
	public static void open (String file)
	{                                                              //~1329R~
    	exceptionOnly=false;//not exception only                   //~1506I~
    	if (Out!=null)                                             //~1329I~
        	return;                                                //~1329I~
		try                                                        //~1329I~
		{                                                          //~1227R~
        	OutputStream out=AxeProp.openOutputData("",file);	//SD card//~1329R~
        	if (out!=null)
        	{//~1313R~
        		Out=new PrintWriter(new OutputStreamWriter(out,"UTF-8"),true/*autoFlash*/);//~1227I~//~1309R~
        		Out.println("Locale: "+Locale.getDefault()+"\n");
//              Y=true; //call Dump                                //~1506I~//~@@@1R~
                Terminal=true;                                     //~1511I~//~@@@1R~
        	}
		}
		catch (IOException e)
		{	Out=null;
            System.out.println("Dump open failed");                //~1329I~
		}
	}
	/** dump a string in a line */
	public synchronized static void println (String s)             //~1305R~
	{                                                              //~1228R~
    	if (exceptionOnly)                                         //~1504I~
        	return;                                                //~1504I~
                                                                   //~1504I~
//		if (Out!=null) Out.println(s);                             //~1228I~
  		if (Out!=null)                                             //~1425R~
        {                                                          //~1425I~
	    	String tidts=Utils.getThreadTimeStamp();          //~1425I~
			Out.println(tidts+":"+s);                              //~1425I~
        }                                                          //~1425I~
//		if (Terminal) System.out.println(s);                       //~1228R~
  		if (Terminal)                                              //~1511R~
        {                                                          //~1425I~
	    	String tid=Utils.getThreadId();                   //~1425I~
		 	System.out.println(tid+":"+s);                         //~1425I~
        }                                                          //~1425I~
	}
	public synchronized static void printlnT(String s)             //+@@@1I~
	{                                                              //+@@@1I~
  		if (Terminal)                                              //+@@@1I~
        {                                                          //+@@@1I~
	    	String tid=Utils.getThreadId();                        //+@@@1I~
		 	System.out.println(tid+":"+s);                         //+@@@1I~
        }                                                          //+@@@1I~
	}                                                              //+@@@1I~
//** Exception Dump                                                //~1309I~
	public synchronized static void println(Exception e,String s)  //~1309I~
	{                                                              //~1309I~
	    String tidts=Utils.getThreadTimeStamp();              //~1425I~
        System.out.println(tidts+":"+s);                                 //~1309I~//~1425R~
        e.printStackTrace();                                   //~1309I~//~1329I~
  		if (Out!=null)                                             //~1309I~
        {                                                          //~1309I~
			Out.println(tidts+":"+s+" Exception:"+e.toString());   //~1425R~
            StringWriter sw=new StringWriter();                    //~1311I~
            PrintWriter pw= new PrintWriter(sw);                   //~1311I~
            e.printStackTrace(pw); 
			Out.println(tidts+":"+sw.toString());                  //~1425R~
			Out.flush(); 
			pw.close();//~1309I~
        }                                                          //~1309I~
	}                                                              //~1309I~
	/** dump a string without linefeed */
	public static void print (String s)
	{                                                              //~1504R~
    	if (exceptionOnly)                                         //~1504I~
        	return;                                                //~1504I~
		if (Out!=null) Out.print(s);                               //~1504I~
		if (Terminal) System.out.print(s);
	}
	/** dump int array */                                          //~@@@1I~
	public static void dump(String Pcomment,int[] Pint)            //~@@@1R~
	{                                                              //~@@@1I~
		if (Out==null)                                             //~@@@1I~
			return;                                                //~@@@1I~
        try                                                        //~@@@1I~
        {                                                          //~@@@1I~
            int ctr=Pint.length;                                   //~@@@1R~
            Out.println(Pcomment+":size="+ctr);                    //~@@@1R~
            for (int ii=0;ii<ctr;ii++)                             //~@@@1R~
            {                                                      //~@@@1R~
                if (ii%10==0)                                      //~@@@1M~
                    Out.printf("% 4d %08x",ii,Pint[ii]);           //~@@@1R~
                else                                               //~@@@1M~
                if (ii%5==0)                                       //~@@@1R~
                    Out.printf(" - %08x",Pint[ii]);                //~@@@1R~
                else                                               //~@@@1R~
                if (ii%10==9)                                      //~@@@1R~
                    Out.printf(" %08x\n",Pint[ii]);                //~@@@1R~
                else                                               //~@@@1R~
                    Out.printf(" %08x",Pint[ii]);                  //~@@@1R~
            }                                                      //~@@@1R~
            Out.println("");                                       //~@@@1I~
        }                                                          //~@@@1I~
        catch(Exception e)                                         //~@@@1I~
        {                                                          //~@@@1I~
			println(e,"Dump.dump-"+Pcomment);                      //~@@@1I~
        }
	}//~@@@1I~
	public static void dump(String Pcomment,char[] Pchar,int Pindex,int Pctr)//~@@@1R~
	{                                                              //~@@@1I~
		if (Out==null)                                             //~@@@1I~
			return;                                                //~@@@1I~
        try                                                        //~@@@1I~
        {                                                          //~@@@1I~
            int ctr=Math.min(Pchar.length,Pctr);                   //~@@@1R~
            Out.println(Pcomment+":size="+ctr);                    //~@@@1I~
            int jj=0;                                              //~@@@1I~
            for (int ii=0;ii<ctr;ii++)                            //~@@@1R~
            {                                                      //~@@@1I~
            	int cp=Character.codePointAt(Pchar,Pindex+ii);     //~@@@1R~
                if (jj%10==0)                                      //~@@@1R~
                    Out.printf("% 4d %06x",jj,cp);                 //~@@@1R~
                else                                               //~@@@1I~
                if (jj%5==0)                                       //~@@@1R~
                    Out.printf(" - %06x",cp);                      //~@@@1R~
                else                                               //~@@@1I~
                if (jj%10==9)                                      //~@@@1R~
                    Out.printf(" %06x\n",cp);                      //~@@@1R~
                else                                               //~@@@1I~
                    Out.printf(" %06x",cp);                        //~@@@1R~
                if (cp>0xffff)	//ucs4                             //~@@@1I~
                	ii++;                                          //~@@@1I~
                jj++;	                                           //~@@@1R~
            }                                                      //~@@@1I~
            Out.println("");                                       //~@@@1I~
        }                                                          //~@@@1I~
        catch(Exception e)                                         //~@@@1I~
        {                                                          //~@@@1I~
			println(e,"Dump.dump-"+Pcomment);                      //~@@@1I~
        }                                                          //~@@@1I~
	}                                                              //~@@@1I~
	/** dump int array */                                          //~@@@1I~
	public static void dump(String Pcomment,float[] Pfloat)        //~@@@1I~
	{                                                              //~@@@1I~
		dump(Pcomment,Pfloat,0,Pfloat.length);              //~@@@1I~
    }                                                              //~@@@1I~
	public static void dump(String Pcomment,float[] Pfloat,int Ppos,int Pctr)//~@@@1R~
	{                                                              //~@@@1I~
		if (Out==null)                                             //~@@@1I~
			return;                                                //~@@@1I~
        try                                                        //~@@@1I~
        {                                                          //~@@@1I~
            int ctr=Pfloat.length;                                 //~@@@1I~
            Out.println(Pcomment+":size="+ctr+",pos="+Ppos+",ctr="+Pctr);//~@@@1R~
            ctr=Math.min(Pctr,ctr-Ppos);                           //~@@@1I~
            for (int ii=0;ii<ctr;ii++)                             //~@@@1I~
            {                                                      //~@@@1I~
                float f=Pfloat[Ppos+ii];                          //~@@@1I~
                if (ii%10==0)                                      //~@@@1I~
                    Out.printf("% 4d %f",ii,f);                    //~@@@1R~
                else                                               //~@@@1I~
                if (ii%5==0)                                       //~@@@1I~
                    Out.printf(" - %f",f);                         //~@@@1R~
                else                                               //~@@@1I~
                if (ii%10==9)                                      //~@@@1I~
                    Out.printf(" %f\n",f);                         //~@@@1R~
                else                                               //~@@@1I~
                    Out.printf(" %f",f);                           //~@@@1R~
            }                                                      //~@@@1I~
            Out.println("");                                       //~@@@1I~
        }                                                          //~@@@1I~
        catch(Exception e)                                         //~@@@1I~
        {                                                          //~@@@1I~
			println(e,"Dump.dump-"+Pcomment);                      //~@@@1I~
        }                                                          //~@@@1I~
	}                                                              //~@@@1I~
	/** dump Codepoint array */                                    //~@@@1I~
	public static void dumpucs(String Pcomment,int[] Pint)         //~@@@1I~
	{                                                              //~@@@1I~
		if (Out==null)                                             //~@@@1I~
			return;                                                //~@@@1I~
        Out.println(Pcomment+new String(Pint,0,Pint.length));      //~@@@1R~
	}                                                              //~@@@1I~
	/** close the dump file */
	public static void close ()
	{                                                              //~1503R~
		if (Out!=null)                                             //~1503I~
        {                                                          //~1503I~
        	println("Dump closed");                               //~1503I~
			Out.close();                                           //~1503I~
        }                                                          //~1503I~
    	Out=null;                                                  //~1425I~
	}
	/** determine terminal dumps or not */
	public static void terminal (boolean flag)
	{	Terminal=flag;
	}
//******************************                                   //~1507I~
//*chk Axe option:debugtrace after go.cfg was read               //~1507I~
//******************************                                   //~1507I~
//    public static void checkOption()                               //~1507I~//~@@@1R~
//    {                                                              //~1507I~//~@@@1R~
//        boolean flag=AxeG.getParameter(AxeG.PARM_DEBUGTRACE,false);//~@@@1R~
//        setOption(flag);                                            //~1507I~//~@@@1R~
//    }                                                              //~1507I~//~@@@1R~
	public static void setOption(boolean Pflag)                    //~1507I~
	{                                                              //~1507I~
        println("DumpOption Changed to"+Pflag);                    //~1507I~
        Y=Pflag;	//debug dump                                   //~1507I~
        exceptionOnly=!Pflag;                                      //~1507I~
	}                                                              //~1507I~
}
