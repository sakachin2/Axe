//CID://+vai4R~:  update#=     63                                  //~vai4R~
//*************************************************************    //~va15I~
//v6k3:130525 (Axe)dbcs determination by Java paint width;change threshold >=dbcsspace to sbcs*1.2//~vai4I~
//vai4:130525 (Axe)width tbl should be for each encoding           //~vai4I~
//vabc:120126 (Axe)use not string but use char[] for drawPosTex for performance//~vabcI~
//vab9:120125 (Axe)change Ucstbl folder from sdcard to wkdir       //~vab9I~
//vab8:120123 (Axe)Android4:getTextWidth is heavy, try dont call for type:undefined//~vab8I~
//v6c6:120123 (Axe)with v6c5:unprintable for Control char(type=15):64 entry//~v6c5I~
//vab7:120122 (Axe)Android4:getTextWidth returns width=0 if request count is too large//~vab7I~
//vab6:120121 (Axe)loop(bug of vab1); getTextWidths returns number of width[] before android4//~vab6I~
//            on android4(api15),it is true but width is pattern of l1,0,l2,0,...(double count)//~vab6I~
//vab1:120119 (Axe)abend at android4:ArrayIndexOutOfBoundException //~vab1I~
//*************************************************************    //~vab1I~
//JNI interface                                                    //~1610R~
//*************************************************************    //~va15I~
package com.xe.Axe;                                                //~va15I~

import java.io.File;
import java.util.Arrays;

import android.graphics.Paint;
import android.graphics.Typeface;

public class Ucs                                                //~1A13R~
{                                                                  //~va15I~
    private final static String UCSTBLNAME="UcsWidthTb";           //~1A14I~
    private final static float paintUcsSize=(float)16.0;           //~1A13I~
    private static float paintUcsDbcsSize=(float) (paintUcsSize*0.75); //~1A13I~
    private static final float DBCSWIDTH=(float)1.20;               //~vai4I~
    private static int LAST_UCS2=0xffff;                           //~vab9I~
                                                                   //~1A14I~
    private static float[] paintTextWidth1=new float[2];                  //~1A13I~
    private static char[] paintTextChar1=new char[2];              //~1A13R~
    private static Paint paintUcs;                                 //~1A13I~//~1A14M~
//    private static final int Sdbcstb1L=0x2e80,Sdbcstb1H=0x2eff ; //128:CJK Radicals Supplement//~vab8R~
//    private static final int Sdbcstb2L=0x2f00,Sdbcstb2H=0x2fdf ; //224:Kangxi Radicals//~vab8R~
//    private static final int Sdbcstb3L=0x3400,Sdbcstb3H=0x4dbf ; //6112:CJK Unified Ideographs Extensions//~vab8R~
//    private static final int Sdbcstb4L=0xa000,Sdbcstb4H=0xa48f ; //1168:Yi Syllables//~vab8R~
//    private static final int Sdbcstb5L=0xa490,Sdbcstb5H=0xa4cf ; //64:Yi Radicals//~vab8R~
//    private static final int Sdbcstb6L=0xe000,Sdbcstb6H=0xf8ff ; //6400:private//~vab8R~
                                                                   //~1620I~9
    //***********************************************************  //~1A13I~
    //*wcwidth                                                     //~1A13I~
    //*rc:-1:err,width(0,1,2)                                      //~1A13I~
    //***********************************************************  //~1A13I~
    public static int wcwidth(int Pucs)                            //~1A13I~
    {                                                              //~1A13I~
        int rc,type;                                                    //~1A13I~//~1A14R~
        float ww;                                                  //~1A13I~
    //***********************                                      //~1A13I~
        type=Character.getType(Pucs);                              //~1A14I~
        rc=getWidthType(type);                                     //~vab8I~
//        if (type==Character.UNASSIGNED//0                           //~1A13I~//~1A14R~//~vab8R~
//        ||  type==Character.CONTROL //15:64 entry                //~vab8R~
//        )                                                        //~vab8R~
		if (rc==-1)                                                //~vab8I~
        {                                                          //~1A13I~
//      	rc=-1;  //err                                               //~1A13I~//~1A14R~//~vab8R~
            ww=0;                                                  //~1A14I~
	    	if (Dump.Y) Dump.println("Ucs:wcwidth ucs="+Integer.toHexString(Pucs)+" is not defined");//~1A13I~//~vab8R~
        }                                                          //~1A13I~
        else                                                       //~1A13I~
//        if (type==Character.NON_SPACING_MARK||type==Character.FORMAT    //6 || 16//~1A14I~//~vab8R~
//        ||  type==Character.LINE_SEPARATOR       //13 *1         //~vab8R~
//        ||  type==Character.PARAGRAPH_SEPARATOR  //14 *1         //~vab8R~
//        )                                                        //~vab8R~
		if (rc==0)                                                 //~vab8I~
        {                                                          //~1A14I~
//      	rc=0; 	//width=0                                      //~1A14I~//~vab8R~
            ww=0;                                                  //~1A14I~
	    	if (Dump.Y) Dump.println("Ucs:wcwidth ucs="+Integer.toHexString(Pucs)+" is not defined");//~1A14I~//~vab8R~
        }                                                          //~1A14I~
        else                                                       //~1A14I~
        {                                                          //~1A13I~
	    	if (paintUcs==null)                                    //~1A13I~
	        	setupPaint();                                      //~1A13I~
        	char[] chs;                                            //~1A13I~
            chs=paintTextChar1;                                    //~1A13I~
        	if (Pucs<=0xffff)	//bms ucs2                         //~1A13I~
            {                                                      //~1A13I~
        		paintTextChar1[0]=(char)Pucs;
	            paintUcs.getTextWidths(chs,0/*pos*/,1/*ctr*/,paintTextWidth1);//~1A13I~
            }                                                      //~1A13I~
            else                                                   //~1A13I~
            {                                                      //~1A13I~
        		Character.toChars(Pucs,chs,0/*dest pos*/);         //~1A13R~
	            paintUcs.getTextWidths(chs,0/*pos*/,2,paintTextWidth1);//~1A13R~
            }                                                      //~1A13I~
            ww=paintTextWidth1[0];                                   //~1A13I~
	        rc=(ww==0) ? 0 : (ww>=paintUcsDbcsSize ? 2:1) ;          //~1A13I~
        }                                                          //~1A13I~
	    if (Dump.Y) Dump.println("Ucs:wcwidth ucs="+Integer.toHexString(Pucs)+",ww="+ww+",rc="+rc+"type="+type);//~1A13I~//~1A14I~//~vab8R~
        return rc;                                                 //~1A13I~
    }                                                              //~1A13I~
    private static void setupPaint()                                    //~1A13I~
    {                                                              //~1A13I~
        int testsbcs='W',testdbcs=0x3000;                        //~vab8I~
    //***************************                                  //~vab8I~
		paintUcs=new Paint();                                      //~1A13I~
        paintUcs.setTypeface(Typeface.MONOSPACE);                  //~1A13I~
        paintUcs.setTextSize(paintUcsSize);                        //~1A13I~
        paintTextChar1[0]=(char)testsbcs;                         //~vab8R~
	    paintUcs.getTextWidths(paintTextChar1,0/*pos*/,1/*ctr*/,paintTextWidth1);//~vab8I~
	    if (Dump.Y) Dump.println("Ucs setupPaint:width \'W\'="+paintTextWidth1[0]);//~vab8I~
//	    paintUcsDbcsSize=paintTextWidth1[0]*(float)1.51;           //~vai4R~
  	    paintUcsDbcsSize=paintTextWidth1[0]*DBCSWIDTH;             //~vai4I~
	    if (Dump.Y) Dump.println("Ucs setupPaint:width="+paintUcsDbcsSize);//~vai4I~
        paintTextChar1[0]=(char)testdbcs;                         //~vab8I~
	    paintUcs.getTextWidths(paintTextChar1,0/*pos*/,1/*ctr*/,paintTextWidth1);//~vab8I~
	    if (Dump.Y) Dump.println("Ucs setupPaint:width \'  \'="+paintTextWidth1[0]);//~vab8I~
//      if (paintUcsDbcsSize<paintTextWidth1[0])                   //~vai4R~
        if (paintUcsDbcsSize>paintTextWidth1[0])                   //~vai4I~
		    paintUcsDbcsSize=paintTextWidth1[0];                   //~vab8I~
	    if (Dump.Y) Dump.println("Ucs setupPaint:dbcswidth="+paintUcsDbcsSize);//~vab8I~
    }                                                              //~1A13I~
    public static void getwcwidthtbl(int Pucsctr,byte[] Pout)       //~1A13I~
    {                                                              //~1A13I~
     //   int rc;                                                    //~1A13I~
     //   float ww;                                                  //~1A13I~
     //   float[] ucsw;                                              //~1A13I~//~vab7R~
     //   char[] ucs2;                                               //~1A13R~//~vab7R~
     //   int[] ucs4;                                                //~1A13I~
        int ctr;//,start,ctrw;                                  //~1A13I~//~vab7R~//~vab8R~
    	int ucs4start=0x010000;                                     //~1A13I~
    	int surrogatestart=0xd800;                                 //~1A13I~
    	int surrogateend=0xe000;                                   //~1A13I~
//        int requnit=2048,reqctr;                                      //~vab7R~//~vab8R~
    //***********************                                      //~1A13I~
        if (paintUcs==null)                                        //~1A13I~
            setupPaint();                                          //~1A13I~
		if (loadwidthtbl(Pout))	//loaded                   //~1A14I~
        	return;                                                //~1A14I~
//      Utils.showToast(R.string.Info_SbcsTblStart);               //~vab8R~
        Arrays.fill(Pout,(byte) 0xff);	//for srrogate //~1A13I~
        if (Dump.Y) Dump.println("AxeJNI start sbcstbl");          //~vab8I~
    //*before srrogate                                             //~1A13I~
//        requnit=requnit2;                                        //~vab7R~
    	ctr=surrogatestart;		//sarogate                         //~1A13R~
//        ucs2=new char[ctr];                                        //~1A13R~//~vab7R~
//        ucsw=new float[ctr];                                       //~1A13I~//~vab7R~
//        for (int ii=0;ii<ctr;ii++)                                 //~1A13I~//~vab7R~
//        {                                                          //~1A13I~//~vab7R~
//            ucs2[ii]=(char) ii;                                           //~1A13R~//~vab7R~
//        }                                                          //~1A13I~//~vab7R~
//        for (start=0;start<ctr;)                                   //~1A13I~//~vab7R~
//        {                                                          //~1A13I~//~vab7R~
////          ctrw=paintUcs.getTextWidths(ucs2,start,ctr-start,ucsw);//~1A13R~//~vab7R~
//            reqctr=Math.min(requnit,ctr-start);                  //~vab7R~
//            ctrw=paintUcs.getTextWidths(ucs2,start,reqctr,ucsw); //~vab7R~
////          if (Dump.Y) Dump.println("AxeJNI start="+Integer.toHexString(start)+",reqctr="+reqctr+",getwidthctr="+ctrw);//~1A13I~//~vab7R~
//            setwidthtbl(start,ctrw,ucsw,Pout);                     //~1A13I~//~vab7R~
//            start+=ctrw;                                           //~1A13I~//~vab7R~
//        }                                                          //~1A13I~//~vab7R~
    setwidthtbl(0/*startucs*/,ctr,Pout);                           //~vab7I~
    //*after srrogate                                              //~1A13I~
    	ctr=Math.min(ucs4start,Pucsctr);                           //~1A13I~
    	ctr=ctr-surrogateend;		//sarogate                     //~1A13R~
//        ucs2=new char[ctr];                                        //~1A13R~//~vab7R~
//        ucsw=new float[ctr];                                       //~1A13I~//~vab7R~
//        for (int ii=0;ii<ctr;ii++)                                 //~1A13I~//~vab7R~
//        {                                                          //~1A13I~//~vab7R~
//            ucs2[ii]=(char)(surrogateend+ii);                      //~1A13R~//~vab7R~
//        }                                                          //~1A13I~//~vab7R~
//        for (start=0;start<ctr;)                                   //~1A13I~//~vab7R~
//        {                                                          //~1A13I~//~vab7R~
////          ctrw=paintUcs.getTextWidths(ucs2,start,ctr-start,ucsw);//~1A13R~//~vab7R~
//            reqctr=Math.min(requnit,ctr-start);                  //~vab7R~
//            ctrw=paintUcs.getTextWidths(ucs2,start,reqctr,ucsw); //~vab7R~
////          if (Dump.Y) Dump.println("AxeJNI start="+Integer.toHexString(start)+",reqctr="+reqctr+",getwidthctr="+ctrw);//~1A13I~//~vab7R~
//            setwidthtbl(start+surrogateend,ctrw,ucsw,Pout);        //~1A13R~//~vab7R~
//            start+=ctrw;                                           //~1A13I~//~vab7R~
//        }                                                          //~1A13I~//~vab7R~
    setwidthtbl(surrogateend/*startucs*/,ctr,Pout);                //~vab7I~
    //*over BMS                                                    //~1A13I~
//        requnit=requnit4;                                        //~vab7R~
        if ((ctr=Pucsctr-ucs4start)>0)                             //~1A13I~
        {                                                          //~1A13I~
//            ucs2=new char[ctr*2];                                  //~1A13I~//~vab7R~
//            ucsw=new float[ctr*2];  //need*2 for outofbound        //~1A13R~//~vab7R~
//            for (int ii=0;ii<ctr;ii++)                             //~1A13I~//~vab7R~
//            {                                                      //~1A13I~//~vab7R~
//                Character.toChars(ii+ucs4start,ucs2,ii*2);         //~1A13I~//~vab7R~
//            }                                                      //~1A13I~//~vab7R~
//            for (start=0;start<ctr;)                               //~1A13I~//~vab7R~
//            {                                                      //~1A13I~//~vab7R~
////              ctrw=paintUcs.getTextWidths(ucs2,start*2,(ctr-start)*2,ucsw);//return is codepoint ctr//~1A13R~//~vab7R~
//                reqctr=Math.min(requnit,ctr-start);              //~vab7R~
//                ctrw=paintUcs.getTextWidths(ucs2,start*2,reqctr*2,ucsw);//return is codepoint ctr//~vab7R~
////              if (Dump.Y) Dump.println("AxeJNI start="+Integer.toHexString(ucs4start+start)+",reqctr="+reqctr+",getwidthctr="+ctrw);//~1A13I~//~vab7R~
////              ctrw/=2;                                           //~vab1I~//~vab6R~//~vab7R~
//                if (AxeG.osVersion>=AxeG.ICE_CREAM_SANDWICH)       //~vab6I~//~vab7R~
//                {                                                  //~vab6I~//~vab7R~
////                  if (ctrw==(ctr-start)*2)                       //~vab6I~//~vab7R~
//                    if (ctrw==reqctr*2)                          //~vab7R~
//                    {                                              //~vab6I~//~vab7R~
//                        ctrw/=2;                                   //~vab6I~//~vab7R~
//                        for (int ii=0;ii<ctrw;ii++)                //~vab6I~//~vab7R~
//                        {                                          //~vab6I~//~vab7R~
//                            ucsw[ii]=ucsw[ii*2];                    //~vab6I~//~vab7R~
//                        }                                          //~vab6I~//~vab7R~
//                    }                                              //~vab6I~//~vab7R~
//                }                                                  //~vab6I~//~vab7R~
//                setwidthtbl(start+ucs4start,ctrw,ucsw,Pout);       //~1A13R~//~vab7R~
//                start+=ctrw;                                       //~1A13R~//~vab7R~
//            }                                                      //~1A13I~//~vab7R~
    		setwidthtbl(ucs4start/*startucs*/,ctr,Pout);           //~vab7I~
        }                                                          //~1A13I~
   		savewidthtbl(Pout);                                        //~1A14I~
//        Utils.showToast(R.string.Info_SbcsTblEnd);               //~vab8R~
        if (Dump.Y) Dump.println("AxeJNI end sbcstbl");            //~vab8I~
    }                                                              //~1A13I~
    private static void setwidthtbl(int Pstartucs,int Pctr,byte[] Pout)//~vab7I~
    {                                                              //~vab7I~
        float[] ucsw;                                              //~vab7I~
        float width;                                               //~vab8I~
        char[] ucs2;                                               //~vab7I~
        int step;                                                   //~vab7I~
        int start,ctrw,reqctr,requnit=2048;                                            //~vab8I~
        boolean bulk,bulkconfirm;                                      //~vab8I~
    //**************                                               //~vab7I~
//        if (AxeG.osVersion>=AxeG.HONEYCOMB)//difference by bulk string and char 1 by 1 when >android2.3//~vab8R~
//        {                                                        //~vab8R~
//            setwidthtbl_1by1bulk(Pstartucs,Pctr,Pout);           //~vab8R~
//            return;                                              //~vab8R~
//        }                                                        //~vab8R~
      	if (AxeG.osVersion>=AxeG.GINGERBREAD)//difference by bulk string and char 1 by 1 when >android2.3//~vab8I~
        {                                                          //~vab8M~
			setwidthtbl_LR(Pstartucs,Pctr,Pout);	//avoid direction(LTR/RTL) mix//~vab8M~
            return;                                                //~vab8M~
        }//always(targetAPI=9)                                     //~vab8I~
        if (Pstartucs>0xffff)                                      //~vab7I~
        	step=2;                                                //~vab7I~
        else                                                       //~vab7I~
        	step=1;                                                //~vab7I~
        ucs2=new char[Pctr*step];                                   //~vab7I~
        ucsw=new float[requnit*step];                                  //~vab7I~//~vab8R~
        if (Pstartucs>0xffff)                                      //~vab7I~
            for (int ii=0;ii<Pctr;ii++)                            //~vab7M~
            {                                                      //~vab7M~
                Character.toChars(Pstartucs+ii,ucs2,ii*2);         //~vab7M~
            }                                                      //~vab7M~
        else                                                       //~vab7I~
            for (int ii=0;ii<Pctr;ii++)                            //~vab7M~
            {                                                      //~vab7M~
                ucs2[ii]=(char)(Pstartucs+ii);                     //~vab7M~
            }                                                      //~vab7M~
      bulk=(AxeG.osVersion<=AxeG.HONEYCOMB);	//no difference by bulk string and char 1 by 1 when android2.3//~vab8I~
      bulkconfirm=(AxeG.osVersion>=AxeG.ICE_CREAM_SANDWICH);	//getTextWidth is heavy//~vab8R~
      if (bulk)	//no difference by bulk string and char 1 by 1 when android2.3//~vab8R~
      {                                                            //~vab8I~
            for (start=0;start<Pctr;)                              //~vab8I~
            {                                                      //~vab8I~
                reqctr=Math.min(requnit,Pctr-start);                //~vab8I~
                ctrw=paintUcs.getTextWidths(ucs2,start*step,reqctr*step,ucsw);//return is codepoint ctr//~vab8I~
                if (ctrw==reqctr*2)                                //~vab8I~
                {                                                  //~vab8I~
                    ctrw/=2;                                       //~vab8I~
                    for (int ii=0;ii<ctrw;ii++)                    //~vab8I~
                    {                                              //~vab8I~
                        ucsw[ii]=ucsw[ii*2];                       //~vab8I~
                    }                                              //~vab8I~
                }                                                  //~vab8I~
                setwidthtbl(start+Pstartucs,ctrw,ucsw,Pout);       //~vab8I~
                start+=ctrw;                                       //~vab8I~
            }                                                      //~vab8I~
      }                                                            //~vab8I~
      else                                                         //~vab8I~
      if (bulkconfirm)	                                           //~vab8I~
      {                                                            //~vab8I~
        	char[] wkstr  =new char[requnit*step];                 //~vab8I~
        	float[] wkwidth=new float[requnit*step];               //~vab8I~
	        float[] wkwidth2=new float[2];                             //~vab8I~
            for (start=0;start<Pctr;)                              //~vab8I~
            {                                                      //~vab8I~
                reqctr=Math.min(requnit,Pctr-start);               //~vab8I~
                setwidthtbl_bulkconfirm(Pstartucs,ucs2,step,start,reqctr,ucsw,wkstr,wkwidth,wkwidth2);//~vab8R~
                setwidthtbl(start+Pstartucs,reqctr,ucsw,Pout);     //~vab8R~
                start+=reqctr;                                     //~vab8R~
            }                                                      //~vab8I~
      }                                                            //~vab8I~
      else	//android4 refers result by string conbination; issue 1 by 1//~vab8I~
      {                                                            //~vab8I~
        for (int ii=0;ii<Pctr;ii++)                                //~vab7I~
        {                                                          //~vab7I~
        	int ucs=Pstartucs+ii;                                  //~vab8I~
        	int type=Character.getType(ucs);                        //~vab8I~
//        if (type==Character.UNASSIGNED)                          //~vab8R~
          if (getWidthType(type)<=0)	//unprintable or width=0   //~vab8I~
            width=0;                                               //~vab8R~
          else                                                     //~vab8I~
          {                                                        //~vab8I~
//           if (dbcschk                                           //~vab8R~
//           &&  (                                                 //~vab8R~
//                   ucs>=Sdbcstb1L && ucs<=Sdbcstb1H              //~vab8R~
//                || ucs>=Sdbcstb2L && ucs<=Sdbcstb2H              //~vab8R~
//                || ucs>=Sdbcstb3L && ucs<=Sdbcstb3H              //~vab8R~
//                || ucs>=Sdbcstb4L && ucs<=Sdbcstb4H              //~vab8R~
//                || ucs>=Sdbcstb5L && ucs<=Sdbcstb5H              //~vab8R~
//                || ucs>=Sdbcstb6L && ucs<=Sdbcstb6H              //~vab8R~
//               )                                                 //~vab8R~
//           )                                                     //~vab8R~
//            width=paintUcsDbcsSize;                              //~vab8R~
//           else                                                  //~vab8R~
//           {                                                     //~vab8R~
          	paintUcs.getTextWidths(ucs2,ii*step,step,ucsw);        //~vab7I~
            width=ucsw[0];                                         //~vab8I~
//           }                                                     //~vab8R~
          }                                                        //~vab8I~
//          setwidthtbl(Pstartucs+ii,1,ucsw,Pout);                 //~vab7I~//~vab8R~
            setwidthtbl1(ucs,type,width,Pout);                     //~vab8R~
        }                                                          //~vab7I~
      }                                                            //~vab8I~
    }                                                              //~vab7I~
    //*****************************************************        //~vab8I~
    //*avoid direction mix                                         //~vab8I~
    //*****************************************************        //~vab8I~
    private static void setwidthtbl_LR(int Pstartucs,int Pctr,byte[] Pout)//~vab8I~
    {                                                              //~vab8I~
        float[] widthtb;                                              //~vab8I~
        char[] chtb;                                               //~vab8I~
        int startucs,ucs,reqctr,requnit=2048,dir,dirtypeold;      //~vab8R~
        int dirtype,step,ucsctr,type,widthctr;                     //~vab8R~
        int typeunpctr;                                               //~vab8I~
    //**************                                               //~vab8I~
        if (Pstartucs>0xffff)                                      //~vab8I~
        	step=2;                                                //~vab8I~
        else                                                       //~vab8I~
        	step=1;                                                //~vab8I~
        chtb=new char[requnit*step];                               //~vab8I~
        widthtb=new float[requnit*step];                           //~vab8I~
        for (int ii=0;ii<Pctr;)                                    //~vab8I~
        {                                                          //~vab8I~
            reqctr=Math.min(requnit,Pctr-ii);                      //~vab8I~
            startucs=Pstartucs+ii;                                 //~vab8I~
            dirtypeold=0;                                          //~vab8I~
            ucsctr=0;
            ucs=startucs;//~vab8I~
            typeunpctr=0;                                          //~vab8I~
            for (int jj=0;jj<reqctr;jj++,ucs++)                    //~vab8R~
            {                                                      //~vab8I~
        		type=Character.getType(ucs);                       //~vab8I~
			    if (getWidthType(type)<=0)//unprintable or width=0          //~vab8I~
                {                                                  //~vab8I~
    				setwidthtbl1(ucs,type,0,Pout);                  //~vab8I~
                    typeunpctr++;                                  //~vab8I~
                    continue;                                      //~vab8I~
                }                                                  //~vab8I~
                dir=Character.getDirectionality(ucs);              //~vab8R~
                switch(dir)                                        //~vab8R~
                {                                                  //~vab8R~
                case Character.DIRECTIONALITY_RIGHT_TO_LEFT:  //1  //~vab8R~
                case Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC: //2//~vab8R~
                case Character.DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING://16//~vab8R~
                case Character.DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE://17//~vab8R~
//                case Character.DIRECTIONALITY_ARABIC_NUMBER://6=bidi//~vab8R~
                    dirtype=1;                                     //~vab8R~
                    break;                                         //~vab8R~
                default:                                           //~vab8R~
                    dirtype=0;                                     //~vab8R~
                }                                                  //~vab8R~
                if (dirtype!=dirtypeold)                           //~vab8R~
                {                                                  //~vab8R~
                    if (ucsctr>0)                                  //~vab8R~
                    {                                              //~vab8R~
                        if (Dump.Y) Dump.println("setwidthtbl_LR:direction changed at "+Integer.toHexString(ucs)+",dir="+dir);//~vab8R~
                        break;                                     //~vab8R~
                    }                                              //~vab8R~
                    dirtypeold=dirtype;                            //~vab8R~
                }                                                  //~vab8R~
                else                                               //~vab8R~
                if (ucsctr==requnit)                               //~vab8R~
                {                                                  //~vab8R~
                    if (Dump.Y) Dump.println("setwidthtbl_LR:buff limit startucs="+Integer.toHexString(startucs)+",requnit="+requnit);//~vab8R~
                    break;                                         //~vab8R~
                }                                                  //~vab8R~
                Character.toChars(ucs,chtb,ucsctr*step);           //~vab8R~
                ucsctr++;                                          //~vab8R~
            }                                                      //~vab8I~
            if (ucsctr>0)                                            //~vab8I~
            {                                                      //~vab8I~
            	if (Dump.Y) Dump.println("setwidthtbl_LR:getTextWidth startucs="+Integer.toHexString(startucs)+",ctr="+ucsctr);//~vab8R~
//				if (Dump.Y) Dump.dump("input to setTextWidths",chtb,0,ucsctr*step);//~vab8I~
          		widthctr=paintUcs.getTextWidths(chtb,0,ucsctr*step,widthtb);//~vab8R~
        		if (Dump.Y) Dump.println("getTextWidth outctr="+widthctr);//~vab8R~
 //       		if (Dump.Y) Dump.dump("widthtb",widthtb,0,ucsctr*step);//~vab8I~
                if (step==2 && widthctr>ucsctr) //double ctr if android4//~vab8R~
                {                                                  //~vab8I~
                    for (int kk=0;kk<ucsctr;kk++)                  //~vab8I~
                    {                                              //~vab8I~
                        widthtb[kk]=widthtb[kk*2];                 //~vab8I~
                    }                                              //~vab8I~
//		        	if (Dump.Y) Dump.dump("widthtb/2",widthtb,0,ucsctr);//~vab8R~
                }                                                  //~vab8I~
                if (typeunpctr>0)	//ucs is not continuous by unprintable//~vab8I~
                	setwidthtbl_printable(step,chtb,ucsctr,widthtb,Pout);//~vab8I~
                else                                               //~vab8I~
                	setwidthtbl(startucs,ucsctr,widthtb,Pout);     //~vab8R~
            }                                                      //~vab8I~
            else                                                   //~vab8I~
            if (typeunpctr==0)                                     //~vab8I~
            	break;                                              //~vab8I~
            ii+=ucsctr+typeunpctr;                                 //~vab8R~
        }                                                          //~vab8I~
    }                                                              //~vab8I~
    //*****************************************************        //~vab8I~
    //*for performance, issue setTextWidths by bulk                //~vab8I~
    //*but result is diffrent by neughboring characters            //~vab8I~
    //*so,issue setTextWidths for for the string inserting null between each character//~vab8I~
    //*****************************************************        //~vab8I~
    private static void setwidthtbl_1by1bulk(int Pstartucs,int Pctr,byte[] Pout)//~vab8I~
    {                                                              //~vab8I~
        float[] ucsw;                                              //~vab8I~  
        char[] ucs2;                                               //~vab8I~
        int startucs,reqctr,requnit=2048;                        //~vab8I~
    //**************                                               //~vab8I~
        ucs2=new char[requnit*3+1];                                //~vab8I~
        ucsw=new float[requnit*3+1];                               //~vab8I~
        for (int ii=0;ii<Pctr;)                                    //~vab8I~
        {                                                          //~vab8I~
            reqctr=Math.min(requnit,Pctr-ii);                      //~vab8I~
            startucs=Pstartucs+ii;                                 //~vab8I~
            setwidthtbl_bulkskip(startucs,reqctr,ucs2,ucsw);       //~vab8I~
            setwidthtbl(startucs,reqctr,ucsw,Pout);                //~vab8I~
            ii+=reqctr;                                            //~vab8I~
        }                                                          //~vab8I~
    }                                                              //~vab8I~
    private static void setwidthtbl_bulkskip(int Pstartucs,int Pctr,char[] Pucs,float[] Pwidth)//~vab8I~
    {                                                              //~vab8I~
    	int step,chctr,widthctr;                                            //~vab8I~
        boolean swdoublewidth;                                     //~vab8R~
        char  spacer=' ';                                          //~vab8I~
    //**************                                               //~vab8I~
        if (Pstartucs>0xffff)                                      //~vab8I~
        	step=2;                                                //~vab8I~
        else                                                       //~vab8I~
        	step=1;                                                //~vab8I~
        Pucs[0]=spacer;                                            //~vab8R~
        Character.toChars(0,Pucs,0);                               //~vab8I~
        for (int ii=0,jj=1;ii<Pctr;ii++)                           //~vab8I~
        {                                                          //~vab8I~
            Character.toChars(Pstartucs+ii,Pucs,jj);               //~vab8I~
            jj+=step;                                              //~vab8I~
	        Pucs[jj++]=spacer;                                     //~vab8R~
        }                                                          //~vab8I~
        chctr=Pctr*(step+1)+1;                                     //~vab8R~
//      Arrays.fill(Pwidth,(float)999.0);	//@@@@test             //~vab8R~//~vabcR~
//      if (Dump.Y) Dump.dump("input to setTextWidths",Pucs,0,chctr);//~vab8R~
        widthctr=paintUcs.getTextWidths(Pucs,0,chctr,Pwidth);//return is codepoint ctr//~vab8I~
        swdoublewidth=(Pstartucs>0xffff) && (widthctr==chctr);	//android4 set 2 entry for ucs4//~vab8M~
        if (Dump.Y) Dump.println("setTextWidth ret="+widthctr+",reqctr="+chctr+",ucs4="+swdoublewidth);//~vab8R~
        if (Dump.Y) Dump.dump("out from setTextWidths",Pwidth,0,widthctr);//~vab8R~
        for (int ii=0,jj=1;ii<Pctr;ii++)                           //~vab8I~
        {                                                          //~vab8I~
        	Pwidth[ii]=Pwidth[jj];                                 //~vab8I~
            if (swdoublewidth)                                     //~vab8R~
            	jj+=3;                                             //~vab8I~
            else                                                   //~vab8I~
            	jj+=2;                                             //~vab8I~
        }                                                          //~vab8I~
//      if (Dump.Y) Dump.dump("OutWidth",Pwidth,0,Pctr);           //~vab8R~
    }                                                              //~vab8I~
    private static int setwidthtbl_bulkconfirm(int Pstartucs,char[] Pucs,int Pstep,int Poffs,int Pctr,float[] Poutwidth,char[] Pwkstr,float[] Pwkwidth,float[] Pwkwidth2)//~vab8R~
    {                                                              //~vab8I~
        int widthctr,startucs;                                     //~vab8R~
        float width1,width2;                                       //~vab8I~
        float[] widthw;                                            //~vab8I~
     //**************                                               //~vab8I~
     	startucs=Pstartucs+Poffs;                                  //~vab8I~
        widthw=Pwkwidth2;                                          //~vab8R~
        widthctr=paintUcs.getTextWidths(Pucs,Poffs*Pstep,Pctr*Pstep,Poutwidth);//~vab8I~
        if (Dump.Y) Dump.println("setwidthtb_bulkconfirm startusc="+Integer.toHexString(startucs)+",ctr="+Pctr+",outctr="+widthctr);//~vab8R~
        if (Dump.Y) Dump.dump("input char",Pucs,Poffs*Pstep,Pctr*Pstep);//~vab8I~
        if (Dump.Y) Dump.dump("width1",Poutwidth);              //~vab8I~
        if (Pstep==1)                                              //~vab8I~
        {                                                          //~vab8I~
        	for (int ii=0,jj=(Pctr-1);ii<Pctr;)                    //~vab8I~
            	Pwkstr[jj--]=(char)(startucs+ii++);                        //~vab8R~
        	if (Dump.Y) Dump.dump("input rev",Pwkstr,0,Pctr);      //~vab8I~
        	paintUcs.getTextWidths(Pwkstr,0,Pctr,Pwkwidth);        //~vab8I~
        	if (Dump.Y) Dump.dump("width2",Pwkwidth);           //~vab8I~
        	for (int ii=0,jj=(Pctr-1);ii<Pctr;ii++,jj--)           //~vab8R~
            {                                                      //~vab8I~
            	width1=Poutwidth[ii];                               //~vab8I~
            	width2=Pwkwidth[jj];                               //~vab8R~
            	if (width1!=width2)	//width change by neighborhood //~vab8I~
                {                                                  //~vab8I~
		        	paintUcs.getTextWidths(Pucs,Poffs+ii,1,widthw);//~vab8I~
            		Poutwidth[ii]=widthw[0];                        //~vab8I~
                    if (Dump.Y) Dump.println("setwidthtb_bulkconfirm ucs="+Integer.toHexString(startucs+ii)+",ww1="+width1+",ww2="+width2+",char ww="+widthw[0]);//~vab8R~
                }                                                  //~vab8I~
            }                                                      //~vab8I~
        }                                                          //~vab8I~
        else                                                       //~vab8I~
        {                                                          //~vab8I~
        	for (int ii=0,jj=(Pctr-1)*2;ii<Pctr;ii++,jj-=2)        //~vab8R~
            {                                                      //~vab8I~
                Character.toChars(startucs+ii,Pwkstr,jj);          //~vab8I~
            }                                                      //~vab8I~
        	if (Dump.Y) Dump.dump("input rev",Pwkstr,0,Pctr*2);    //~vab8I~
        	paintUcs.getTextWidths(Pwkstr,0,Pctr*2,Pwkwidth);      //~vab8I~
        	if (Dump.Y) Dump.dump("width2",Pwkwidth);           //~vab8I~
        	for (int ii=0,jj=(Pctr-1);ii<Pctr;ii++,jj--)           //~vab8I~
            {                                                      //~vab8I~
            	width1=Poutwidth[ii*2];                             //~vab8I~
            	width2=Pwkwidth[jj*2];                             //~vab8I~
            	if (width1!=width2)	//width change by neighborhood //~vab8I~
                {                                                  //~vab8I~
		        	paintUcs.getTextWidths(Pucs,(Poffs+ii)*2,2,widthw);//~vab8I~
            		Poutwidth[ii]=widthw[0];                        //~vab8I~
                    if (Dump.Y) Dump.println("setwidthtb_bulkconfirm ucs="+Integer.toHexString(startucs+ii)+",ww1="+width1+",ww2="+width2+",char ww="+widthw[0]);//~vab8R~
                }                                                  //~vab8I~
                else                                               //~vab8I~
            		Poutwidth[ii]=width1;	//shrink to half length//~vab8I~
            }                                                      //~vab8I~
        }                                                          //~vab8I~
        return widthctr;                                           //~vab8I~
    }//setwidthtbl_bulkconfirm                                     //~vab8I~
    //*********************************************                //~vab8I~
    private static void setwidthtbl(int Pstartucs,int Pctr,float[] Pwidth,byte[] Pout)//~1A13I~
    {                                                              //~1A13I~
    	//float ww=0;                                                  //~1A13I~//~1A14R~
        int ucs,/*bit,bits,rc,*/type;                             //~1A13R~//~1A14R~
    //***********************                                      //~1A13I~
		for (int ii=0;ii<Pctr;ii++)                                //~1A13R~
        {                                                          //~1A13I~
            ucs=ii+Pstartucs;                                      //~1A13R~
        	type=Character.getType(ucs);                           //~1A14I~
//            if (type==Character.UNASSIGNED)//0                     //~1A14I~//~vab8R~
//            {                                                      //~1A14I~//~vab8R~
//                rc=-1;  //err                                      //~1A14I~//~vab8R~
//            }                                                      //~1A14I~//~vab8R~
//            else                                                   //~1A14I~//~vab8R~
//            if (type==Character.CONTROL)    //15:64 entry          //~v6c5I~//~vab8R~
//            {                                                      //~v6c5I~//~vab8R~
//                rc=-1;  //err                                      //~v6c5I~//~vab8R~
//            }                                                      //~v6c5I~//~vab8R~
//            else                                                   //~v6c5I~//~vab8R~
//            if (type==Character.NON_SPACING_MARK   //6    *875     //~1A15R~//~vab8R~
//            ||  type==Character.FORMAT              //16  *34      //~1A15I~//~vab8R~
//            ||  type==Character.SPACE_SEPARATOR      //12 *18     //~1A15I~//~vab8R~
//            ||  type==Character.LINE_SEPARATOR       //13 *1      //~1A15I~//~vab8R~
//            ||  type==Character.PARAGRAPH_SEPARATOR  //14 *1      //~1A15I~//~vab8R~
////          ||  type==Character.CONTROL                            //~1A15I~//~v6c5R~//~vab8R~
//            )   //see view/KeyCharacterMap.isPrintingKey           //~1A15R~//~vab8R~
//            {                                                      //~1A14I~//~vab8R~
//                rc=0;   //width=0                                  //~1A14I~//~vab8R~
//            }                                                      //~1A14I~//~vab8R~
//            else                                                   //~1A13I~//~vab8R~
//            {                                                      //~1A13I~//~vab8R~
//                ww=Pwidth[ii];                                     //~1A13I~//~vab8R~
//                rc=(ww==0) ? 0 : (ww>=paintUcsDbcsSize ? 2:1);       //~1A13I~//~vab8R~
//            }                                                      //~1A13I~//~vab8R~
//            bit=rc & 0x03;                                         //~1A13I~//~vab8R~
////        bits=0;                                                  //~vab6R~//~vab8R~
////        try                                                      //~vab6R~//~vab8R~
////        {                                                        //~vab6R~//~vab8R~
//            bits=Pout[ucs/4];                                      //~1A13R~//~vab8R~
////        }                                                        //~vab6R~//~vab8R~
////        catch(Exception e)                                       //~vab6R~//~vab8R~
////        {                                                        //~vab6R~//~vab8R~
////            Dump.println(e,"exception");                         //~vab6R~//~vab8R~
////        }                                                        //~vab6R~//~vab8R~
//            switch(ucs%4)                                          //~1A13R~//~vab8R~
//            {                                                      //~1A13I~//~vab8R~
//            case 0:                                                //~1A13I~//~vab8R~
//                bits=(bits&0x3f)|(bit<<6);                         //~1A13I~//~vab8R~
//                break;                                             //~1A13I~//~vab8R~
//            case 1:                                                //~1A13I~//~vab8R~
//                bits=(bits&0xcf)|(bit<<4);                         //~1A13I~//~vab8R~
//                break;                                             //~1A13I~//~vab8R~
//            case 2:                                                //~1A13I~//~vab8R~
//                bits=(bits&0xf3)|(bit<<2);                         //~1A13I~//~vab8R~
//                break;                                             //~1A13I~//~vab8R~
//            default:                                              //~1A13I~//~vab8R~
//                bits=(bits&0xfc)|(bit);                            //~1A13I~//~vab8R~
//            }                                                      //~1A13I~//~vab8R~
//            Pout[ucs/4]=(byte) bits;                               //~1A13R~//~vab8R~
//            if (Dump.Y) Dump.println("AxeJNI setwcwidthtb ucs="+Integer.toHexString(ucs)+",ww="+ww+",rc="+rc+",type="+type);//~1A13R~//~1A14R~//~vab6R~//~vab8R~
    		setwidthtbl1(ucs,type,Pwidth[ii],Pout);                //~vab8I~
        }                                                          //~1A13I~
    }                                                              //~1A13I~
    //*********************************************                //~vab8I~
    private static void setwidthtbl_printable(int Pstep,char[] Pchar,int Pctr,float[] Pwidth,byte[] Pout)//~vab8I~
    {                                                              //~vab8I~
        int ucs,type;                              //~vab8I~
    //***********************                                      //~vab8I~
		for (int ii=0;ii<Pctr;ii++)                                //~vab8I~
        {                                                          //~vab8I~
            ucs=Character.codePointAt(Pchar,ii*Pstep);         //~vab8I~
        	type=Character.getType(ucs);                       //~vab8I~
    		setwidthtbl1(ucs,type,Pwidth[ii],Pout);                //~vab8I~
        }                                                          //~vab8I~
    }                                                              //~vab8I~
    //**************************************************           //~vab8I~
    //see view/KeyCharacterMap.isPrintingKey                       //~vab8I~
	//            case Character.SPACE_SEPARATOR:                  //~vab8I~
	//            case Character.LINE_SEPARATOR:                   //~vab8I~
	//            case Character.PARAGRAPH_SEPARATOR:              //~vab8I~
	//            case Character.CONTROL:                          //~vab8I~
	//            case Character.FORMAT:                           //~vab8I~
    //non printable(rc=-1) or width=0(rc=0)                        //~vab8I~
    //**************************************************           //~vab8I~
    private static int getWidthType(int Ptype)                     //~vab8I~
    {                                                              //~vab8I~
    	int rc;                                                    //~vab8I~
    //***********************                                      //~vab8I~
        switch(Ptype)                                              //~vab8I~
        {                                                          //~vab8I~
        case Character.UNASSIGNED:			//0                    //~vab8I~
        case Character.LINE_SEPARATOR:      //13 *1                //~vab8I~
        case Character.PARAGRAPH_SEPARATOR:  //14 *1                //~vab8I~
        case Character.CONTROL:				//15:64 entry          //~vab8I~
        case Character.FORMAT:      		//16  *34              //~vab8I~
            rc=-1;  //err                                          //~vab8I~
            break;                                                 //~vab8I~
        case Character.NON_SPACING_MARK:   //6    *875              //~vab8I~
            rc=0;  //width=0;                                      //~vab8I~
            break;                                                 //~vab8I~
        default:                                                   //~vab8I~
            rc=1;  //printable                                     //~vab8I~
        }
        return rc;                                                 //~vab8I~
    }                                                              //~vab8I~
    private static void setwidthtbl1(int Pucs,int Ptype,float Pwidth,byte[] Pout)//~vab8I~
    {                                                              //~vab8I~
        int bit,bits,rc;                                      //~vab8I~
    //***********************                                      //~vab8I~
//        if (Ptype==Character.UNASSIGNED)//0                      //~vab8R~
//        {                                                        //~vab8R~
//            rc=-1;  //err                                        //~vab8R~
//        }                                                        //~vab8R~
//        else                                                     //~vab8R~
//        if (Ptype==Character.CONTROL)   //15:64 entry            //~vab8R~
//        {                                                        //~vab8R~
//            rc=-1;  //err                                        //~vab8R~
//        }                                                        //~vab8R~
//        else                                                     //~vab8R~
//        if (Ptype==Character.NON_SPACING_MARK   //6    *875      //~vab8R~
//        ||  Ptype==Character.FORMAT              //16  *34       //~vab8R~
////      ||  Ptype==Character.SPACE_SEPARATOR      //12 *18 includes " " and DBCS space//~vab8R~
//        ||  Ptype==Character.LINE_SEPARATOR       //13 *1        //~vab8R~
//        ||  Ptype==Character.PARAGRAPH_SEPARATOR  //14 *1        //~vab8R~
////      ||  type==Character.CONTROL                              //~vab8R~
//        )   //see view/KeyCharacterMap.isPrintingKey             //~vab8R~
//        {                                                        //~vab8R~
//            rc=0;   //width=0                                    //~vab8R~
//        }                                                        //~vab8R~
//        else                                                     //~vab8R~
    	if ((rc=getWidthType(Ptype))>0)	//at first by character type//~vab8I~
        {                                                          //~vab8I~
            rc=(Pwidth==0) ? 0 : (Pwidth>=paintUcsDbcsSize ? 2:1);     //~vab8I~
        }                                                          //~vab8I~
        bit=rc & 0x03;                                             //~vab8I~
        bits=Pout[Pucs/4];                                          //~vab8I~
        switch(Pucs%4)                                              //~vab8I~
        {                                                          //~vab8I~
        case 0:                                                    //~vab8I~
            bits=(bits&0x3f)|(bit<<6);                             //~vab8I~
            break;                                                 //~vab8I~
        case 1:                                                    //~vab8I~
            bits=(bits&0xcf)|(bit<<4);                             //~vab8I~
            break;                                                 //~vab8I~
        case 2:                                                    //~vab8I~
            bits=(bits&0xf3)|(bit<<2);                             //~vab8I~
            break;                                                 //~vab8I~
        default:                                                   //~vab8I~
            bits=(bits&0xfc)|(bit);                                //~vab8I~
        }                                                          //~vab8I~
        Pout[Pucs/4]=(byte) bits;                                   //~vab8I~
//      if (Dump.Y) Dump.println("Ucs setwcwidthtb ucs="+Integer.toHexString(Pucs)+",ww="+Pwidth+",rc="+rc+",type="+Ptype);//+vai4R~
    }                                                              //~vab8I~
//*******************************************************          //~vai4I~
	private static String getwidthtblname()                        //~vai4I~
    {                                                              //~vai4I~
		return AxeProp.getWkdir()+"/"+UCSTBLNAME+"."+AxeG.Glocale; //~vai4I~
    }                                                              //~vai4I~
//*******************************************************          //~1A14I~
    private static boolean loadwidthtbl(byte[] Pout)   //~1A14I~
    {                                                              //~1A14I~
    	boolean rc;                                                //~1A14I~
    //***********************                                      //~1A14I~
//		String fnm=AxeProp.getSDPath(UCSTBLNAME);                          //~1A14I~//~vab9R~
// 		String fnm=AxeProp.getWkdir()+"/"+UCSTBLNAME;              //~vai4R~
   		String fnm=getwidthtblname();                               //~vai4I~
        File f=new File(fnm);                                      //~1A14I~
        if (!f.exists())                                            //~1A14I~
        	return false;                                          //~1A14I~
        if (f.length()!=(long)(Pout.length))                             //~1A14I~
        	return false;                                          //~1A14I~
        rc=AxeProp.load(fnm,Pout);                                  //~1A14I~
        if (Dump.Y) Dump.println("Ucs loadwidthtbl fnm="+fnm+",rc="+rc);//~1A14I~
        return rc;
    }                                                              //~1A14I~
    private static boolean savewidthtbl(byte[] Pout)               //~1A14I~
    {                                                              //~1A14I~
    	boolean rc;                                                //~1A14I~
    //***********************                                      //~1A14I~
//  	String fnm=AxeProp.getSDPath(UCSTBLNAME);                          //~1A14I~//~vab9R~
//		String fnm=AxeProp.getWkdir()+"/"+UCSTBLNAME;              //~vai4R~
   		String fnm=getwidthtblname();                               //~vai4I~
        rc=AxeProp.save(fnm,Pout);                                  //~1A14I~
        if (Dump.Y) Dump.println("Ucs savewidthtbl fnm="+fnm+",rc="+rc);//~1A14I~
        return rc;
    }                                                              //~1A14I~
    public static boolean resetwidthtbl()                            //~1A14I~
    {                                                              //~1A14I~
    	boolean rc;                                                //~1A14I~
    //***********************                                      //~1A14I~
//		String fnm=AxeProp.getSDPath(UCSTBLNAME);                  //~vai4R~
   		String fnm=getwidthtblname();                               //~vai4I~
        rc=AxeProp.delete(fnm);                                    //~1A14I~
        if (Dump.Y) Dump.println("Ucs resetwidthtbl fnm="+fnm+",rc="+rc);//~1A14I~
        return rc;                                                 //~1A14I~
    }                                                              //~1A14I~
    //******************************************************************************//~vab9I~
    //*reduce android4 getTextWidth output width table to correspond tp codepoint//~vab9I~
    //*return ucs4 count                                           //~vab9I~
    //******************************************************************************//~vab9I~
    public static int getCodepointWidthTbl(int[] Ppdata,int Pctr,float[] Ppfwidthtb)//~vab9I~
    {                                                              //~vab9I~
    	int pos=0;                                                 //~vab9I~
    //******************                                           //~vab9I~
        for (int ii=0;ii<Pctr;ii++,pos++)                          //~vab9I~
        {                                                          //~vab9I~
            Ppfwidthtb[ii]=Ppfwidthtb[pos];                        //~vab9I~
        	if (Ppdata[ii]>LAST_UCS2)                              //~vab9I~
            	pos++;                                             //~vab9I~
        }                                                          //~vab9I~
        return pos-Pctr;                                           //~vab9I~
    }                                                              //~vab9I~
    //******************************************************************************//~vabcI~
    //*codepoint tbl to char tbl                                   //~vabcI~
    //******************************************************************************//~vabcI~
    public static int cpToChars(int[] Ppucs,int Ppos,int Pctr,char[] Ppchar)//~vabcI~
    {                                                              //~vabcI~
    	int pos,outpos,endpos,ucs;                                     //~vabcI~
    //******************                                           //~vabcI~
        for (pos=Ppos,endpos=pos+Pctr,outpos=0;pos<endpos;pos++,outpos++)//~vabcI~
        {                                                          //~vabcI~
        	ucs=Ppucs[pos];                                        //~vabcI~
        	if (ucs>LAST_UCS2)                                     //~vabcI~
                Character.toChars(ucs,Ppchar,outpos++);            //~vabcI~
            else                                                   //~vabcI~
                Ppchar[outpos]=(char) ucs;                                //~vabcI~
        }                                                          //~vabcI~
        return outpos;                                             //~vabcI~
    }                                                              //~vabcI~
}//class                                                           //~va15R~
