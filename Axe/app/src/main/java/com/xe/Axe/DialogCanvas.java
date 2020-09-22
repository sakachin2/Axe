//*CID://+vc2TR~: update#= 234;                                    //~vc2TR~
//**********************************************************************//~vabdI~
//vc2T 2020/09/13 Color dialog sample is too small                 //~vc2TI~
//vabd:120126 (Axe)surrogate char considerationfor font sample text//~vabdI~
//**********************************************************************//~1726I~
package com.xe.Axe;                                         //~1107R~  //~1108R~//~1109R~//~1117R~//~1726I~
                                                                   //~1726I~
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;                                   //~1726I~
import android.widget.LinearLayout;

import com.ahsv.AG;
                                                                   //~1726I~
public class DialogCanvas                                          //~1726I~
{                                                                  //~1726I~
    private static final double SAMPLE_WIDTH_RATE=0.8;             //~vc2TI~
	private ImageView view;                                        //~1726I~
    private Canvas canvas;                                         //~1726I~
    public  Bitmap  bitmap;                                        //~1726I~
    private int bitmapH,bitmapW;                                       //~1726I~
    private Point dimension;                                       //~1726I~
	private Paint paintfillbg=new Paint();                         //~1726I~
	private Paint paintline=new Paint();                           //~1726I~
    private Paint painttext=new Paint(Paint.ANTI_ALIAS_FLAG); 
 //   private int layoutid;//~1726I~
    private FontMetrics fontMetrics;                               //~1804I~
    private android.graphics.Paint.FontMetricsInt fmInt;           //~1804I~
//******************************                                   //~1726I~
	public DialogCanvas(int Playoutid,ViewGroup PdialogView)                     //~1726I~
    {   
//		layoutid=Playoutid;
//      view=(ImageView)(PdialogView.findViewById(R.id.SETUP_SAMPLE));//~1821R~
//      view.setVisibility(View.VISIBLE);	//reset visibility="gone" on xml of ConnectedGoFrame if !TimerInTitle//~1726I~
        LinearLayout parent=(LinearLayout)(PdialogView.findViewById(R.id.SETUP_SAMPLE));//~1821I~
        getDialogCanvasSize((View)parent);                               //~1821R~
        view=new ImageView(AxeG.context);                          //~1821I~
        initImage(bitmapW,bitmapH);                                //~1726I~
        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(bitmapW,bitmapH);//~1821I~
        parent.addView(view,lp);                                   //~1821I~
	}                                                              //~1726I~
//********************                                             //~1726I~
	public void getDialogCanvasSize(View Pview)                    //~1821R~
    {                                                              //~1726I~
        int y=Pview.getMeasuredHeight();                           //~1821R~
        if (Dump.Y) Dump.println("DialogCanvas measured H="+y);    //~1821I~
        if (y==0)                                                  //~1726I~
        	y=Pview.getLayoutParams().height;                      //~1821R~
        int x=Pview.getMeasuredWidth();                            //~1821R~
        if (x==0)                                                  //~vc2TI~
        	x=Pview.getLayoutParams().width;                       //~vc2TI~
        if (x==-1)  //fill_parent                                  //~vc2TI~
            x=(int)(AG.scrWidth*SAMPLE_WIDTH_RATE);                //~vc2TI~
        if (Dump.Y) Dump.println("DialogCanvas measured W="+x);    //~1821I~
        if (x==0)                                                  //~1726I~
        {                                                          //~1821I~
//            x=AxeG.screenW;                                      //~1821R~
//            MarginLayoutParams mlp=new marginLayoutParams(Pview.getlayoutparams());//~1821I~
//            int margin=mlp.leftMargin+mlp.rightmargin;           //~1821I~
//            if (Dump.Y) Dump.println("margin="+margin);          //~1821I~
//            x-=margin;                                           //~1821I~
        	x=Pview.getLayoutParams().width;    //260px            //~1821R~
        }                                                          //~1821I~
        if (Dump.Y) Dump.println("DialogCanvas getSize x="+x+",y="+y);//~1821R~
        bitmapH=y;                                                 //~1726I~
        bitmapW=x;                                                 //~1726I~
        dimension=new Point(x,y);                                  //~1726I~
    }                                                              //~1726I~
//********************                                             //~1726I~
	public Point getSize()                                          //~1726I~
    {                                                              //~1726I~
        return dimension;                                          //~1726I~
    }                                                              //~1726I~
//********************                                             //~1726I~
    public void initImage(int Pw,int Ph)                           //~1726I~
    {                                                              //~1726I~
	    if (Dump.Y) Dump.println("DialogCanvas initImage ww="+Pw+",hh="+Ph);//~vc2TI~
    	bitmap=Bitmap.createBitmap(Pw,Ph,Bitmap.Config.ARGB_8888); //~1726I~
        canvas=new Canvas(bitmap);	//apply grapic function        //~1726I~
        view.setImageBitmap(bitmap); //view's content bitmap       //~1726I~
	    if (Dump.Y) Dump.println("DialogCanvas createImage bitmap="+bitmap.toString()+",canvas="+canvas.toString());//~1726I~
    }                                                              //~1726I~
//********************                                             //~1726I~
    public void invalidate()                                       //~1726I~
    {                                                              //~1726I~
	    if (Dump.Y) Dump.println("DialogCanvas invalidate");       //~1726I~
        view.invalidate();                                         //~1726I~
    }                                                              //~1726I~
//********************                                             //~1726I~
    public void fillRect(int Pbg)                                  //~1726I~
    {                                                              //~1726I~
    	if (Dump.Y) Dump.println("DialogCanvas fillRect bg="+Integer.toHexString(Pbg));//~1726I~
    	Rect rect=new Rect(0/*left*/,0/*top*/,bitmapW/*right*/,bitmapH/*bottom*/);//~1726I~
		paintfillbg.setColor(Pbg);                                 //~1726I~
    	canvas.drawRect(rect,paintfillbg);                           //~1117R~//~1726I~
	}                                                              //~1726I~
//********************                                             //~1726I~
    public void drawLine(int Pxx1,int Pyy1,int Pxx2,int Pyy2,int Pfg)//~1726I~
    {                                                              //~1726I~
    	float fxx1,fxx2,fyy1,fyy2;                                 //~1726I~
    //****************	                                           //~1726I~
    	if (Dump.Y) Dump.println("DialogCanvas drawline fg="+Integer.toHexString(Pfg)+"("+Pxx1+","+Pyy1+")-("+Pxx2+","+Pyy2+")");//~1726I~
        fxx1=(float)Pxx1;                                          //~1726I~
        fxx2=(float)Pxx2;                                          //~1726I~
        fyy1=(float)Pyy1;                                          //~1726I~
        fyy2=(float)Pyy2;                                          //~1726I~
        if (Pxx2<0)                                                //~1726I~
            fxx2=(float)bitmapW;                                   //~1726I~
        if (Pyy2<0)                                                //~1726I~
            fyy2=(float)bitmapH;                                   //~1726I~
		paintline.setColor(Pfg);                                   //~1726I~
        int w=Math.max(AxeG.RulerWidth,1);                         //~vc2TI~
        paintline.setStrokeWidth(w);                               //~vc2TI~
    	canvas.drawLine(fxx1,fyy1,fxx2,fyy2,paintline);                    //~1117R~//~1726I~
	}                                                              //~1726I~
//********************                                             //~1726I~
//*Color sample view *                                             //~1804I~
//********************                                             //~1804I~
    public void setTextSize(int PtextSize)                         //~1726I~
    {                                                              //~1726I~
		painttext.setTextSize(PtextSize);                          //~1726I~
        fmInt=painttext.getFontMetricsInt();                       //~1804I~
        if (Dump.Y) Dump.println("DialogCanvas setTextSize="+PtextSize);//~1804I~
	}                                                              //~1726I~
//********************                                             //~1804I~
    public void drawString(String s,int Px,int Py,int Pfg)         //~1726R~//~1804M~
    {                                                              //~1726I~//~1804M~
		painttext.setColor(Pfg);                                   //~1726I~//~1804M~
	    canvas.drawText(s,(float)Px,(float)Py,painttext);          //~1117I~//~1726I~//~1804M~
	}                                                              //~1726I~//~1804M~
//********************                                             //~1730I~
//*Font sample view  *                                             //~1804I~
//********************                                             //~1804I~
    public void setFont(Font Pfont)                                //~1730I~
    {                                                              //~1730I~
//        Typeface tf=Typeface.create(Pfont.name,Pfont.getStyle());       //~1730I~//~1804R~
//        painttext.setTypeface(tf);                                 //~1730I~//~1804R~
//        setTextSize(Pfont.getSize());                              //~1730I~//~1804R~
        fontMetrics=FontMetrics.getFontMetrics(Pfont);             //~1804I~
        painttext=fontMetrics.paintText;                            //~1804I~
        if (Dump.Y) Dump.println("DialogCanvas setFont size="+Pfont.getSize()+",name="+Pfont.name+",style="+Pfont.getStyle());//~1730I~
	}                                                              //~1730I~
//********************                                             //~1726I~//~1804M~
    public int getBaseline()                                 //~1726I~//~1803R~//~1804M~
    {                                                              //~1726I~//~1804M~
    	int baseline;                                              //~1804I~
    //*******************                                          //~1804I~
    	if (fmInt!=null)	//ColorSample                          //~1804I~
        	baseline=-fmInt.top;                                   //~1804I~
        else                                                       //~1804I~
        	baseline=fontMetrics.getBaseLine();                    //~1804I~
        if (Dump.Y) Dump.println("DialogCanvas getBaseline="+baseline);//~1804I~
		return baseline;                                               //~1726I~//~1803R~//~1804M~
	}                                                              //~1726I~//~1804M~
//********************                                             //~1803I~//~1804M~
    public int getFontheight()                                     //~1803I~//~1804M~
    {                                                              //~1803I~//~1804M~
    	int height;                                                //~1804I~
    //*******************                                          //~1804I~
    	if (fmInt!=null)	//ColorSample                          //~1804I~
        	height=-fmInt.top+fmInt.bottom;                        //~1804I~
        else                                                       //~1804I~
        	height=fontMetrics.getHeight();                      //~1804I~
        if (Dump.Y) Dump.println("DialogCanvas getFontHeight="+height);//~1804I~
		return height;                                             //~1804I~
	}                                                              //~1803I~//~1804M~
//********************                                             //~1726I~//~1804M~
    public int getStringWidth(String Pstr)                         //~1726I~//~1804M~
    {                                                              //~1726I~//~1804M~
		return (int)painttext.measureText(Pstr);                        //~1726I~//~1804M~
	}                                                              //~1726I~//~1804M~
//********************                                             //~1730I~//~1804M~
    public void drawString(String s,int Px,int Py,int Pfg,int Pcellw,boolean Pligature)//~1730I~//~1803R~//~1804M~
    {                                                              //~1730I~//~1804M~
		painttext.setColor(Pfg);                                   //~1730I~//~1804M~
        int len=s.length();                                          //~1730I~//~1804M~
        if (Dump.Y) Dump.println("DialogCAnvas DrawString "+s+",measuretext="+painttext.measureText(s));//~1801I~//~1804M~
        if (Pligature)                                             //~1803I~//~1804M~
			canvas.drawText(s,(float)Px,(float)Py,painttext);      //~1803I~//~1804M~
        else                                                       //~1803I~//~1804M~
	        for (int ii=0,xx=Px;ii<len;ii++,xx+=Pcellw)            //~1730I~//~1803R~//~1804M~
            {                                                      //~vabdI~
//		    	canvas.drawText(s.substring(ii,ii+1),(float)xx,(float)Py,painttext);//~1730I~//~1803R~//~1804M~//~vabdR~
				int cp=s.codePointAt(ii);                          //~vabdI~
                if (cp>0xffff)	//ucs4                             //~vabdI~
                {                                                  //~vabdI~
					canvas.drawText(s.substring(ii,ii+2),(float)xx,(float)Py,painttext);//~vabdI~
                    ii++;                                          //~vabdI~
                }                                                  //~vabdI~
                else                                               //~vabdI~
                {                                                  //+vc2TI~
					canvas.drawText(s.substring(ii,ii+1),(float)xx,(float)Py,painttext);//~vabdI~
					char ucs=s.charAt(ii);                         //+vc2TI~
			   	   	if (ucs >= 0x2e80 && ucs <= 0xa4cf && ucs != 0x303f)//+vc2TI~
	        			xx+=Pcellw;                                //+vc2TI~
                }                                                  //+vc2TI~
            }                                                      //~vabdI~
	}                                                              //~1730I~//~1804M~
}//class DialogCanvas                                              //~1726I~
