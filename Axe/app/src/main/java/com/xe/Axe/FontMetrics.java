//*CID://+vaaxR~:                             update#=    1;       //+vaaxI~
//******************************************************************//+vaaxI~
//vaax:120105 (Axe)80% font size if Key label strlen>3             //+vaaxI~
//******************************************************************//+vaaxI~
package com.xe.Axe;                                                //~1108R~//~1716I~
import android.graphics.Paint;                                     //~1716I~
                                                                   //~1716I~
public class FontMetrics                                           //~1716I~
{                                                                  //~1716I~
	private Font font;                                             //~1718R~
	private android.graphics.Paint.FontMetricsInt fontmetricsInt;        //~1718R~//~1802R~
    public Paint paintText;                                        //~1718R~
    private float[] width256;                                       //~1718R~//~1719R~
    private int avww;                                              //~1718R~
    private int textSize;                                          //~1802I~
    private int baseLine;                                          //~1802I~
    private int height;                                            //~1802I~
                                          //~1719I~
	private FontMetrics(Font Pfont,Paint Ppaint,android.graphics.Paint.FontMetricsInt PfontmetricsInt,int PtextSize)//~1718R~//~1802R~
	{                                                              //~1716I~
    	paintText=Ppaint;                                          //~1718R~
		font=Pfont;                                                //~1716I~
		fontmetricsInt=PfontmetricsInt;                                  //~1716I~//~1802R~
		textSize=PtextSize;                                        //~1802I~
        baseLine=Math.abs(fontmetricsInt.top);//+Math.abs(fontmetricsInt.leading);       //~1719I~//~1802R~
    	height=-fontmetricsInt.top+fontmetricsInt.bottom;         //~1802R~
        if (Dump.Y) Dump.println("getFontMetrics:font="+Pfont.name+" size="+textSize+",baseLine="+baseLine+",height="+height);//~1802R~
	}                                                              //~1716I~
    public static FontMetrics getFontMetrics(Font Pfont)           //~1716I~
    {                                                              //~1716I~
//      int unit=TypedValue.COMPLEX_UNIT_PX;                       //~1802I~//~1804R~
        if (Dump.Y) Dump.println("getFontMetrics entry :font="+Pfont.name);//~1804I~
        if (Pfont.fontMetrics!=null)                               //~1804I~
        	return Pfont.fontMetrics;                              //~1804I~
        Paint paintText=new Paint(Paint.ANTI_ALIAS_FLAG);          //~1718R~
        paintText.setTypeface(Pfont.getTypefaceStyle());           //~1718R~
        paintText.setTextSize((float)Pfont.getSize());                    //~1718R~//~1802R~
        if (Dump.Y) Dump.println("getFontMetrics:font="+Pfont.name+" size="+Pfont.getSize());//~1718I~
//*ascent+descent=textSize+leading?                                 //~1719I~//~1802R~
		int size=Pfont.getSize();
		int trysz=size;//~1719I~
		android.graphics.Paint.FontMetricsInt fm;                  //~1802R~
		while(true)                                                     //~1719I~
       {                                                          //~1719I~
			fm=paintText.getFontMetricsInt();             //~1719I~//~1802R~
//          float ftexth=-fm.ascent+fm.descent;                    //~1802R~
            int texth=-fm.top+fm.bottom;//+Math.abs(fm.leading);   //~1802R~
        	if (Dump.Y) Dump.println("getFontMetrics:font="+Pfont.name+" metrics texth="+texth+",font size="+size);//~1719I~//~1802R~
        	if (Dump.Y) Dump.println("ascent="+-fm.ascent+",descent="+fm.descent+",top="+fm.top+",bottom="+fm.bottom+",leading="+fm.leading);//~1802I~
            if (texth<=size)                                   //~1719I~//~1802R~
            	break;                                             //~1719I~
            trysz--;                                                //~1719I~
	        paintText.setTextSize(trysz);                           //~1719I~//~1802R~
        }                                                          //~1719I~
        Pfont.fontMetrics=new FontMetrics(Pfont,paintText,fm,trysz);          //~1802I~//~1804R~
        return Pfont.fontMetrics;                                  //~1804I~
    }                                                              //~1716I~
	public int stringWidth(String s)                               //~1716I~
	{                                                              //~1716I~
        if (Dump.Y) Dump.println("FontMetrics:font="+font.name+" stringwidth="+s+"="+paintText.measureText(s)+",font.getSize()="+font.getSize());//~1212I~//~1303R~//~1718R~
        return (int)paintText.measureText(s);                      //~1718R~
	}                                                              //~1716I~
	public static float stringWidth(Paint Ppaint,String Pstr)      //+vaaxI~
	{                                                              //+vaaxI~
    	float len;                                                 //+vaaxI~
	//**************	                                           //+vaaxI~
        len=Ppaint.measureText(Pstr);                              //+vaaxI~
        if (Dump.Y) Dump.println("FontMetrics:stringWidth str="+Pstr+",len="+len);//+vaaxI~
        return len;                                                //+vaaxI~
	}                                                              //+vaaxI~
//    public int getAscent()                                         //~1716I~//~1802R~
//    {                                                              //~1716I~//~1802R~
//        int ceiling;                                               //~1716I~//~1802R~
//        ceiling=(int)fontmetrics.ascent;    //android minus;awt plus//~1716I~//~1802R~
//        if ((float)ceiling>fontmetrics.ascent)  //minus cut        //~1716I~//~1802R~
//            ceiling--;                                             //~1716I~//~1802R~
//        ceiling=-ceiling;   //android minus;awt plus               //~1716I~//~1802R~
//        if (Dump.Y) Dump.println(font.name+" ascent="+fontmetrics.ascent+",return int="+ceiling);//~1212I~//~1716I~//~1802R~
//        return ceiling;                                            //~1716I~//~1802R~
//    }                                                              //~1716I~//~1802R~
	public int getBaseLine()                                       //~1802I~
	{                                                              //~1802I~
    	return baseLine;                                           //~1802I~
	}                                                              //~1802I~
//    public int getDescent()                                        //~1716I~//~1802R~
//    {                                                              //~1716I~//~1802R~
//        if (Dump.Y) Dump.println(font.name+" descent="+fontmetrics.descent);//~1716I~//~1802R~
//        return (int)fontmetrics.descent;                           //~1716I~//~1802R~
//    }                                                              //~1716I~//~1802R~
//    public int getLeading()                                        //~1716I~//~1802R~
//    {                                                              //~1716I~//~1802R~
//        if (Dump.Y) Dump.println(font.name+" leading="+fontmetrics.leading);//~1716I~//~1802R~
//        return (int)fontmetrics.leading;                           //~1716I~//~1802R~
//    }                                                              //~1716I~//~1802R~
	public int getHeight()                                         //~1716I~
	{                                                              //~1716I~
//        int height=(int)Math.ceil(Math.abs(fontmetrics.ascent)+Math.abs(fontmetrics.descent));//~1716I~//~1802R~
        if (Dump.Y) Dump.println(font.name+" height="+height);     //~1716I~
        return height;                                             //~1716I~
	}                                                              //~1716I~
	public float[] getWidths()                                       //~1716I~
	{                                                              //~1716I~
    	if (width256!=null)                                        //~1718R~//~1802R~
        	return width256;                                       //~1716I~
        width256=new float[256];                                     //~1718R~//~1719R~
        char[] char256=new char[256];                               //~1719I~
        for (int ii=0;ii<256;ii++)                             //~1212I~//~1716I~
        {                                                          //~1802I~
        	char256[ii]=(char)ii;                                        //~1719I~
			if (Dump.Y) Dump.println("FontMetrics measureText ch="+Integer.toHexString(ii)+",width="+paintText.measureText(new String(char256,ii,1)));//~1802I~
        }                                                          //~1802I~
		if (Dump.Y) Dump.println("FontMetrics measureText ch256 21-7e width="+paintText.measureText(new String(char256,21,94)));//~1802I~
        paintText.getTextWidths(char256,0,256,width256);           //~1719I~
        int ctr=0;                                                 //~1719I~
        float totww=(float)0.0;                                           //~1719I~
        for (int ii=0;ii<256;ii++)                                 //~1719I~
        {                                                          //~1719I~
        	float fww=width256[ii];                                //~1719I~
            if (ii>' ' && ii<0x7f)                                 //~1719R~
            {                                                      //~1719I~
                totww+=fww;                                        //~1719I~
                ctr++;                                             //~1719I~
            }                                                      //~1719I~
        }                                                          //~1719I~
        if (Dump.Y) Dump.println(font.name+" average width="+totww+"/"+ctr+"="+totww/ctr);//~1719I~
        if (Dump.Y) Dump.dump("stringwidths width256",width256);   //~1801I~
        avww=(int)(totww/ctr);                                            //~1716I~//~1719R~
    	return width256;                                           //~1716I~
	}                                                              //~1716I~
	public int getAvWidth()                                        //~1716I~
	{                                                              //~1716I~
        if (avww==0)                                               //~1716I~
			getWidths();                                            //~1716I~
    	return avww;                                               //~1716I~
	}                                                              //~1716I~
	public int getAvDigitWidth()                                   //~1716I~
	{                                                              //~1716I~
    	int totw=0;                                                 //~1716I~
    //*******
    	if (avww==0)                                               //~1716I~
			getWidths();                                            //~1716I~
        for (int ii='0';ii<='9';ii++)                              //~1716I~
        {                                                          //~1716I~
            totw+=width256[ii];                                    //~1716I~
        }                                                          //~1716I~
    	return totw/10;                                            //~1716I~
	}                                                              //~1716I~
//    public int charsWidth(char [] Pchars,int Poffs,int Plen)       //~1716I~//~1719R~
//    {                                                              //~1716I~//~1719R~
//        return stringWidth(new String(Pchars,Poffs,Plen));         //~1716I~//~1719R~
//    }                                                              //~1716I~//~1719R~
//    public int charWidth(char Pchar)                               //~1716I~//~1719R~
//    {                                                              //~1716I~//~1719R~
//        char[] chars=new char[1];                                  //~1716I~//~1719R~
//        chars[0]=Pchar;                                            //~1716I~//~1719R~
//        return paintText.stringWidth(new String(chars,0,1));                 //~1716I~//~1719R~
//    }                                                              //~1716I~//~1719R~
}                                                                  //~1716I~
