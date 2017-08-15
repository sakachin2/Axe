package com.xe.Axe;                                                //~1716I~
//~1716I~
import android.graphics.Paint;
import android.graphics.Typeface;                                  //~1716I~
import android.widget.Button;                                      //~1716I~
import android.widget.TextView;                                    //~1716I~
                                                                   //~1716I~
public class Font                                                  //~1716I~
{                                                                  //~1716I~
    public static final int PLAIN   = Typeface.NORMAL;             //~1716I~
    public static final int BOLD    = Typeface.BOLD;               //~1716I~
    public static final int ITALIC  = Typeface.ITALIC;             //~1716I~
    public static final int BOLD_ITALIC  = Typeface.BOLD_ITALIC;   //~1730I~
    public static final int STYLE=(PLAIN|BOLD|ITALIC|BOLD_ITALIC); //~1730R~
                                                                   //~1731I~
    public static final String MONOSPACE="Monospaced";             //~1731M~
    public static final String BOLDMONOSPACE="BoldMonospaced";     //~1731M~
    public static final String SANS="SansSerif";                   //~1731M~
    public static final String SERIF="Serif";                      //~1731M~
    public static final String DEFAULT="Default";                  //+1831I~
    public static final String Bold="Bold";                        //~1731M~
    public static final String Italic="Italic";                    //~1731M~
    public static final String BoldItalic="BoldItalic";            //~1731M~
    public static final String Normal="Normal";                    //~1731M~
                                                                   //~1731I~
    public String name;                                         //~1716I~//~1804R~
    public String typefacename;                                    //~1823I~
    private Typeface typeface;                                   //~1716I~//~1804R~
    private int style;                                           //~1716I~//~1804R~
    private int size;                                            //~1716I~//~1804R~
    public FontMetrics fontMetrics;                                //~1804I~
                                                                   //~1716I~
//    private void initializeFont(Hashtable attributes) {          //~1716R~
//        if (this.name == null) {                                 //~1716R~
//            this.name = "Default";                               //~1716R~
//        }                                                        //~1716R~
//    }                                                            //~1716R~
                                                                   //~1716I~
//******************************                                   //~1716R~
    public Font() {                                                //~1716I~
//      name=MONOSPACE;                                            //~1724R~
        name=Gxeh.DEF_FONTSTYLE;                                   //~1724I~
        style=PLAIN;                                               //~1716I~
    	Paint paint=new Paint();                                         //~1716M~
    	size=(int)paint.getTextSize();                                  //~1716I~
    	typeface=Typeface.MONOSPACE;                               //~1716R~
        if (Dump.Y) Dump.println("Font Constructor noparm:name="+name+",size="+size);//~1718I~
    }                                                              //~1716I~
    public Font(int Pfontsize) {                                   //~1719I~
        name=MONOSPACE;                                            //~1719I~
        style=PLAIN;                                               //~1719I~
    	size=Pfontsize;                                            //~1719I~
    	typeface=Typeface.MONOSPACE;                               //~1719I~
        if (Dump.Y) Dump.println("Font Constructor size only:name="+name+",size="+size);//~1719I~
    }                                                              //~1719I~
    public Font(String Pfamily) {                                  //~1716I~
        name=Pfamily;                                              //~1716I~
    	typeface=name2typeface(name);                              //~1716I~
        style=PLAIN;                                               //~1716I~
    	Paint paint=new Paint();                                         //~1716I~
    	size=(int)paint.getTextSize();                                  //~1716I~
        if (Dump.Y) Dump.println("Font Constructor family="+name+",size="+size);//~1718I~
    }                                                              //~1716I~
    public Font(String Pname, int Pstyle, int Psize) {             //~1718R~
    	typeface= name2typeface(Pname);                             //~1718R~
    	name = Pname;                                              //~1718R~
    	style = (Pstyle & STYLE);                                  //~1731R~
    	size = Psize;                                              //~1718R~
        if (Dump.Y) Dump.println("Font Constructor name+style+size="+name+",style="+Pstyle+",size="+size);//~1731R~
//        initializeFont(null);                                    //~1716R~
    }                                                              //~1716I~
    public Font(String Pname, String Pstyle, int Psize) {          //~1716I~
    	 this(Pname,0,Psize);
        style=name2style(Pstyle);//~1716I~
    }                                                              //~1716I~
    public Font(Font Pfont,float Psize) {                          //~1716I~
    	this.typeface= name2typeface(Pfont.name);                  //~1716I~
    	this.name = Pfont.name;                                    //~1716I~
    	this.style = (style & ~0x03) == 0 ? style : 0;             //~1716I~
    	this.size = (int)Psize;                                    //~1716I~
                                                                   //~1716I~
//        initializeFont(null);                                    //~1716R~
    }                                                              //~1716I~
    private Typeface name2typeface(String name)                    //~1716I~
    {                                                              //~1716I~
    	Typeface tf;                                               //~1716I~
    	if (MONOSPACE.equals(name))                                //~1716I~
        	tf=Typeface.MONOSPACE;                                 //~1716I~
        else                                                       //~1716I~
    	if (SANS.equals(name))                                     //~1716I~
        	tf=Typeface.SANS_SERIF;                                //~1716I~
        else                                                       //~1716I~
    	if (SERIF.equals(name))                                    //~1716I~
        	tf=Typeface.SERIF;                                     //~1716I~
        else                                                       //~1716I~
        	tf=Typeface.DEFAULT;                                   //~1716I~
        return tf;                                                 //~1716I~
    }//name2typeface                                               //~1716I~
    private int name2style(String name)                       //~1716I~
    {                                                              //~1716I~
    	int style;                                                 //~1716I~
    	if (Bold.equals(name))                                     //~1716I~
        	style=BOLD;                                            //~1716I~
        else                                                       //~1716I~
    	if (Italic.equals(name))                                   //~1716I~
        	style=ITALIC;                                          //~1716I~
        else                                                       //~1716I~
    	if (BoldItalic.equals(name))                               //~1730I~
        	style=BOLD_ITALIC;                                     //~1730I~
        else                                                       //~1730I~
        	style=PLAIN;                                           //~1716I~
        return style;                                              //~1716I~
    }//name2typeface                                               //~1716I~
                                                                   //~1716I~
    public int getStyle() {                                        //~1716I~
    	return style;                                              //~1716I~
    }                                                              //~1716I~
    public String getStylename() {                                    //~1823I~
    	String stylename;                                          //~1823I~
        switch(style)                                              //~1823I~
        {                                                          //~1823I~
        case BOLD:                                                 //~1823I~
    		stylename=Bold;                                        //~1823I~
            break;                                                 //~1823I~
        case ITALIC:                                               //~1823I~
    		stylename=Italic;                                      //~1823I~
            break;                                                 //~1823I~
        case BOLD_ITALIC:                                          //~1823I~
    		stylename=BoldItalic;                                  //~1823I~
            break;                                                 //~1823I~
        default:                                                   //~1823I~
			stylename=Normal;                                      //~1823I~
        }                                                          //~1823I~
        return stylename;                                          //~1823I~
    }                                                              //~1823I~
    public Typeface getTypeface() {                                //~1716I~
    	return typeface;                                           //~1716I~
    }                                                              //~1716I~
    public Typeface getTypefaceStyle() {                           //~1716I~
    	return Typeface.create(typeface,style);                    //~1716I~
    }                                                              //~1716I~
                                                                   //~1716I~
    public int getSize() {                                         //~1716I~
	    return size;                                               //~1823R~
    }                                                              //~1716I~
	public void setFont(TextView Pview)                            //~1716I~
    {                                                              //~1716I~
    	if (getTypeface()!=null);  //style:bold,italic,..|typeface:monospace,serif,...//~1716I~
        	Pview.setTypeface(getTypefaceStyle());                 //~1716I~
        if (getSize()!=0);                                         //~1716I~
        	Pview.setTextSize(getSize());                          //~1716I~
    }                                                              //~1716I~
	public void setFont(Button Pview)                              //~1716I~
    {                                                              //~1716I~
    	setFont((TextView)Pview);                                  //~1716I~
    }                                                              //~1716I~
}                                                                  //~1716I~
