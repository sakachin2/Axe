//*CID://+vairR~: update#=  195;                                   //~vairR~
//*****************************************************************//~vaatI~
//vair:130606 Axe:reuestforcus reured for IS01 for hard-kbd inputmkey input avail after track ball input.//~vairI~
//            For Nexus7 it popup ime.                             //~vairI~
//            So reuest focus if hard-kbd available.               //~vairI~
//vaif:130530 Axe:give focus to Axescreen at xeKbd dialog shown    //~vaifI~
//vaic:130528 Axe:Bug:when resize cusor/ruler is not displayedatfirst//~vaicI~
//            (invalidate is async,if 2 invalidate() was issued,   //~vaicI~
//            1st onDraw diplay cursor and reset drawCursor,       //~vaicI~
//            2nd onDraw draw line but dose not drow cursor)       //~vaicI~
//            android may call onDraw without invalidate() request.//~vaicI~
//            avoid issue invalidate() when orientation change processing//~vaicI~
//vaia:130526 (Axe)requestFocus() required to fire onKey() when orientationn changed//~vaiaI~
//vai0:130525 (Axe)avoid auto ime popup                            //~vai0I~
//vabc:120126 (Axe)use not string but use char[] for drawPosTex for performance//~vabcI~
//vabb:120126 (Axe)cursor hide text when insert mode               //~vabbI~
//vaba:120125 (Axe)crash for ucs4 when android4(getTextWidth output double length of width tbl)//~vabaI~
//vaat:120103 (Axe:Bug)xekbd should support orientaion change      //~vaatI~
//*****************************************************************//~vaatI~
package com.xe.Axe;                                                //~@@@@I~

import java.text.Format;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.ImageView;
                                                                   //~1716I~
import com.xe.Axe.Gxeh;                                        //~1716I~
                                                                   //~1606I~
                                                                   //~1606I~
//*****************************************************************//~1606I~
                                                                   //~1606I~
public class AxeScreen extends ImageView                                //~1606I~
{                                                                  //~1606I~
	public Bitmap   bitmap;         //offscreen bitmap              //~1620R~//~1719R~
	public Canvas   bitmapCanvas;   //offscreen canvas             //~1620R~//~1719R~
    public Paint    paintText,paintFill,paintLine;                 //~1620R~
    public Paint    paintCursor;                                   //~vabbI~
    private int baseLine;                                          //~1718I~
    private float[] posLine=new float[4];                          //~1809I~
    private float[] posRect=new float[8];                          //~1809I~
    private Rect rectFill=new Rect();                              //~1809I~
//  private List<PendingLine> listPL;                              //~1809I~//~1826I~
    private PendingLine PLcursor=new PendingLine();                //~1826R~
    private PendingLine PLruler1=new PendingLine();                //~1826I~
    private PendingLine PLruler2=new PendingLine();                //~1826I~
    private boolean drawCursor,drawRuler1,drawRuler2;              //~1826I~
    private boolean showKbdWhenFocusOn;                            //~1827I~
    private char[] chLine=new char[(Gxeh.MAXCOL*2)]; //*2:surrogate pare//~vabcI~
//    private boolean initiallyHidden;                             //~1827R~
///////////////////////////////////////////////////////////////////////////////~1809I~
    class PendingLine                                              //~1809I~
    {                                                              //~1809I~
    	public int width,x1,y1,x2,y2,fg;
    	public PendingLine(){}//~1809I~
        public PendingLine(int Pwidth,int Px1,int Py1,int Px2,int Py2,int Pfg)//~1809I~
        {                                                          //~1809I~
        	width=Pwidth;                                          //~1809I~
        	x1=Px1;                                                //~1809I~
        	y1=Py1;                                                //~1809I~
        	x2=Px2;                                                //~1809I~
        	y2=Py2;                                                //~1809I~
        	fg=Pfg;                                                //~1809I~
            if (Dump.Y) Dump.println("PendingLine constructor x1="+Px1+",y1="+Py1+",x2="+Px2+",y2="+Py2);//~vaifI~
        }                                                          //~1809I~
        public void setPendingLine(int Pwidth,int Px1,int Py1,int Px2,int Py2,int Pfg)//~1826I~
        {                                                          //~1826I~
        	width=Pwidth;                                          //~1826I~
        	x1=Px1;                                                //~1826I~
        	y1=Py1;                                                //~1826I~
        	x2=Px2;                                                //~1826I~
        	y2=Py2;                                                //~1826I~
        	fg=Pfg;                                                //~1826I~
            if (Dump.Y) Dump.println("setPendingLine x1="+Px1+",y1="+Py1+",x2="+Px2+",y2="+Py2);//~vaifI~
        }                                                          //~1826I~
    }                                                              //~1809I~
///////////////////////////////////////////////////////////////////////////////~1606I~
    public AxeScreen(Bitmap Pbitmap)                                      //~1607R~
    {                                                              //~1606I~
        super(AxeG.context);                                       //~1606I~
                                                                   //~1726I~
        Gxeh.Mbgcolor=Gxeh.DEF_BGCOLOR;                                      //~1726I~
        Gxeh.Mrulercolor=Gxeh.DEF_RULERCOLOR;                                     //~1726I~
        Gxeh.Mfontstyle=Gxeh.DEF_FONTSTYLE;                        //~1727I~
                                                                   //~1826I~
        bitmap=Pbitmap;                                            //~1607R~
      	bitmapCanvas=new Canvas(bitmap);                           //~1608I~
        setFocusableInTouchMode(true);	//DOC says "FocusableInTouch then Focusable"//~1607I~
        setFocusable(true);                                        //~1607I~
        if (Dump.Y) Dump.println("AxeScreen constructor bitmap="+Pbitmap.toString());//~1607I~
        AxeG.axeKeyAction.setKeyListener(this);                    //~1607R~
        AxeG.axeIME=new AxeIME(this);                              //~1826R~
        addFocusChangeListener(this);                              //~1607I~
//    if (false)                                                   //~vaifR~
      if (!hardKbdOff())	//hardkbd available                    //+vairR~
        requestFocus();                                            //~1607I~
        setupPaint();                                               //~1608I~
    }                                                              //~1606I~
    private void setupPaint()                                           //~1608I~
    {                                                              //~1608I~
        paintText=new Paint(Paint.ANTI_ALIAS_FLAG);                //~1620R~
        paintFill=new Paint();                                     //~1620R~
        paintLine=new Paint();                                     //~1620I~
        paintFill.setStyle(Paint.Style.FILL);//exclude bottom/right edge(if strokewidth=0)//~1809I~
        paintLine.setStyle(Paint.Style.STROKE);                    //~1809I~
//      setColor(paintText,Color.WHITE);                           //~1620R~
//      setColor(paintFill,Color.BLUE);                            //~1620R~
//  	listPL=new ArrayList<PendingLine>();                       //~1809I~//~1826R~
        paintCursor=new Paint();                                   //~vabbI~
//      paintCursor.setXfermode(new PixelXorXfermode(0xffffff));//inverse mode//~vabbR~
    }                                                              //~1608I~
///////////////////////////////////////////////////////////////////////////////~1606I~
//***************************                                      //~va15I~//~1826I~
//*for IME,allow set InputConnection                               //~1826I~
//***************************                                      //~1826I~
    @Override                                                      //~1826I~
    public boolean onCheckIsTextEditor()                           //~1826I~
    {                                                      //~v@@@I~//~va15I~//~1826I~
        if (Dump.Y) Dump.println("OnCheckisTextEditor");                        //~1826I~//~1827R~//~vabbR~
        return true;                                               //~1826I~
    }                                                              //~1826I~
//********************************************************              //~1826I~//~1827R~
// Japanese IME requires this connection for not EditText          //~1827I~
// (But ime crash NPE at jp.co.omronsoft.openwnn.OpenWnnJAJP.checkCommitInfo(OpenWnnJAJP.java:2513) by this connection setting)//~1828I~
// Chinese IME send KeyEvent by default commitText()               //~1827I~
//*******************************************************          //~1827I~
    @Override                                                  //~1826I~//~1827R~
    public InputConnection onCreateInputConnection(EditorInfo outAttrs)//~1826I~//~1827R~
    {                                                          //~1826I~//~1827R~
        if (Dump.Y) Dump.println("OnCreateInputConnection +imeoption="+outAttrs.imeOptions+",inputtype="+outAttrs.inputType);//~1826I~//~1827R~//~vabbR~
        return AxeG.axeIME.createInputConnection(outAttrs);        //~1827R~
    }                                                          //~1826I~//~1827R~
//*********************                                            //~1606R~
    @Override                                                      //~0914R~//~1606I~
    protected void onDraw(Canvas Pcanvas)                           //~0914I~//~1117R~//~1606I~
    {                                                              //~1606I~
    	Rect rect;                                                 //~1608I~
        try                                                        //~1606I~
        {                                                          //~1606I~
            if (Dump.Y) Dump.println("AxeScreen @onDraw PL="+AxeG.displayPL);//~1606R~
            if (Dump.Y) Dump.println("AxeScreen @onDraw bitmap W="+bitmap.getWidth()+",H="+bitmap.getHeight());//~1608I~
            if (Dump.Y) Dump.println("AxeScreen @onDraw canvas W="+Pcanvas.getWidth()+",H="+Pcanvas.getHeight());//~1608I~
            rect=Pcanvas.getClipBounds();                          //~1608I~
            if (Dump.Y) Dump.println("AxeScreen @onDraw canvas cliprect="+rect.toString());//~1608I~
            Pcanvas.drawBitmap(bitmap,0f,0f,null);//canvas contains titlebar area                 //~1606I~//~1719R~
            if (Dump.Y) Dump.println("AxeScreen bitmap="+bitmap.toString());//~1606I~
            if (Dump.Y) Dump.println("AxeScreen canvas="+Pcanvas.toString());//~1606I~
            drawPendingLine(Pcanvas);                              //~1809I~
        }                                                          //~1606I~
        catch(Exception e)                                         //~1606I~
        {                                                          //~1606I~
            Dump.println(e,"AxeSCreen:onDraw");                    //~1606I~
        }                                                          //~1606I~
    }                                                              //~0914I~//~1606I~
    private void drawPendingLine(Canvas Pcanvas)                                   //~1809I~
    {                                                              //~1809I~
//        while(true)                                                   //~1809I~//~1826R~
//        {                                                          //~1809I~//~1826R~
//            if (listPL.isEmpty())                                  //~1809I~//~1826R~
//                return;                                            //~1809I~//~1826R~
//            PendingLine pl=listPL.get(0);                          //~1809I~//~1826R~
//            if (Dump.Y) Dump.println("pending line");              //~1809I~//~1826R~
//            drawLine(Pcanvas,pl.width,pl.x1,pl.y1,pl.x2,pl.y2,pl.fg);//~1809I~//~1826R~
//            listPL.remove(0);                                          //~1809I~//~1826R~
//        }                                                          //~1809I~//~1826R~
		PendingLine pl;                                            //~1826I~
        //******************                                       //~1826I~
		if (Dump.Y) Dump.println("drawPendingLine drawCursor="+drawCursor);//~vaicI~
        if (drawCursor)                                            //~1826I~
        {                                                          //~1826I~
			pl=PLcursor;                                           //~1826I~
//  		drawLine(Pcanvas,pl.width,pl.x1,pl.y1,pl.x2,pl.y2,pl.fg);//~1826I~//~vabbR~
			drawRect(paintCursor,Pcanvas,pl.width,pl.x1,pl.y1,pl.x2,pl.y2,pl.fg);//~vabbR~
//      	drawCursor=false;                                      //~1826I~//~vaicR~
        }                                                          //~1826I~
        if ((Gxeh.Mrulermode & 2)!=0)	//horizontal               //~vaicI~
	        drawRuler1=true;                                       //~vaicI~
        if (drawRuler1)                                            //~1826I~
        {                                                          //~1826I~
			pl=PLruler1;                                           //~1826I~
			if (Dump.Y) Dump.println("pending line ruler1");       //~1826I~
			drawLine(Pcanvas,pl.width,pl.x1,pl.y1,pl.x2,pl.y2,pl.fg);//~1826I~
        	drawRuler1=false;                                      //~1826I~
        }                                                          //~1826I~
        if ((Gxeh.Mrulermode & 1)!=0)	//vertical                 //~vaicI~
	        drawRuler2=true;                                       //~vaicI~
        if (drawRuler2)                                            //~1826I~
        {                                                          //~1826I~
			pl=PLruler2;                                           //~1826I~
			if (Dump.Y) Dump.println("pending line ruler2");       //~1826I~
			drawLine(Pcanvas,pl.width,pl.x1,pl.y1,pl.x2,pl.y2,pl.fg);//~1826I~
        	drawRuler2=false;                                      //~1826I~
        }                                                          //~1826I~
    }                                                              //~1809I~
//    private void clearPendingLine()                                //~1825I~//~1826R~
//    {                                                              //~1825I~//~1826R~
//        listPL.clear();    //clear intermediate request(draw last cursor line and ruler)//~1825I~//~1826R~
//    }                                                              //~1825I~//~1826R~
//*************************                                        //~1606I~
    @Override                                                      //~1606I~
    public void onSizeChanged(int newW,int newH,int oldW,int oldH) //~1606I~
    {                                                              //~1606I~
        if (Dump.Y) Dump.println("onSizeChanged "+newW+","+newH+","+oldW+","+oldH);//~1606I~
    }                                                              //~1606I~
    @Override                                                      //~1606I~
    public void onMeasure(int Pw,int Ph)                           //~1606I~
    {                                                              //~1606I~
        if (Dump.Y) Dump.println("onMeasure w="+Pw+",H="+Ph);      //~1606I~
        setMeasuredDimension(AxeG.screenW,AxeG.screenH);           //~1606I~
    }                                                              //~1606I~
//*********************************************************        //~vaatR~
//*from AxeView:createScreen if already created(2nd time)          //~vaatI~
//*********************************************************        //~vaatI~
//  public void orientationChanged(Bitmap Pbitmap)                 //~1822R~
    public void replaceBitmap(Bitmap Pbitmap)                      //~1822I~
    {                                                              //~1606I~
        bitmap=Pbitmap;                                            //~1607I~
      	bitmapCanvas=new Canvas(bitmap);                           //~1608I~
//    if (false)                                                   //~vaiaR~
        requestFocus();                                            //~1608I~
      if (Dump.Y) Dump.println("replaceBitmap invalidate issue="+((AxeG.main.axeStat & Axe.AXES_ORIENTATION_CHANGING)==0));//~vaicR~
      if ((AxeG.main.axeStat & Axe.AXES_ORIENTATION_CHANGING)==0)  //~vaicR~
      {                                                            //~vaicI~
        invalidate();                                              //~1606I~
      }                                                            //~vaicI~
        AxeG.axeIME.orientationChanged();	//recreate axekbd      //~vaatI~
    }                                                              //~1606I~
//*************************                                        //~1607I~
    public void updateButtonLayout(Bitmap Pbitmap)                 //~1607I~
    {                                                              //~1607I~
        if (Dump.Y) Dump.println("updateButtonLayout bitmap="+Pbitmap.toString());//~1607I~
        bitmap=Pbitmap;                                            //~1607I~
      	bitmapCanvas=new Canvas(bitmap);                           //~1608I~
//    if (false)                                                   //~vaiaR~
        requestFocus();                                            //~1608I~
        invalidate();                                              //~1607I~
        if (Dump.Y) Dump.println("updateButtonLayout invalidate()");//~vaicI~
    }                                                              //~1607I~
//**************************************************************                             //~1607I~//~1827R~
//*from Axe:onWindowFocusChanged                                   //~1827I~
//   AxeScreen:onFocusChanged is not called when dialog closed     //~1827I~
//**************************************************************   //~1827I~
    public void windowFocusChanged(boolean Phasfocus)             //~1827I~
    {                                                              //~1827I~
        if (Dump.Y) Dump.println("windowFocusChanged focus="+Phasfocus);//~1827I~
//        if (!initiallyHidden)                                    //~1827R~
//        {                                                        //~1827R~
//            initiallyHidden=true;                                //~1827R~
//            AxeG.axeIME.hideKbd(this);                           //~1827R~
//        }                                                        //~1827R~
    	focusChanged(Phasfocus);                                   //~1827I~
    }                                                              //~1827I~
    //****************                                             //~1827I~
    private void focusChanged(boolean Phasfocus)                    //~1827I~
    {                                                              //~1827I~
        if (Dump.Y) Dump.println("focusChanged to "+Phasfocus);    //~1827I~
        if (Phasfocus)                                             //~1827I~
        {                                                          //~1827I~
            if (showKbdWhenFocusOn)                                //~1827I~
            {                                                      //~1827I~
                showKbdWhenFocusOn=false;                          //~1827I~
                AxeG.axeIME.showKbdDelayed(this); //issue after waiting a moment//~1827R~
            }                                                      //~1827I~
        }                                                          //~1827I~
        else                                                       //~vaifI~
        {                                                          //~vaifI~
	        if (Dump.Y) Dump.println("focusChanged requestFocus");  //~vaifI~
            requestFocus();                                        //~vaifI~
        }                                                          //~vaifI~
    }                                                              //~1827I~
//************                                                     //~1827I~
    private void addFocusChangeListener(View Pview)             //~1607I~//~1827R~
    {                                                              //~1607I~
        if (Dump.Y) Dump.println("addFocuschange Listener view="+this.toString());//~1607I~
    	Pview.setOnFocusChangeListener(new FocusChangeListener(Pview));//~1827R~
    }                                                              //~1607I~
    class FocusChangeListener implements OnFocusChangeListener     //~1827I~
    {                                                              //~1827I~
    	private View view;                                         //~1827I~
    	public FocusChangeListener(View Pview)                     //~1827I~
        {                                                          //~1827I~
        	view=Pview;                                            //~1827I~
        }                                                          //~1827I~
        @Override                                                  //~1827I~
        public void onFocusChange(View Pview,boolean Phasfocus)    //~1827I~
        {                                                          //~1827I~
            if (Dump.Y) Dump.println("focus changed to "+Phasfocus+",view="+Pview.toString());//~1827R~
            focusChanged(Phasfocus);                               //~1827I~
        }                                                          //~1827I~
    }//class FocusChangeListener                                   //~1827I~
    //**********************************                           //~1827I~
//    @Override                                                    //~1924R~
//    public boolean onTouchEvent(MotionEvent event)   //to continue following event,should return true//~1924R~
//    @Override                                                    //~1924R~
//    public boolean onTouch(View view,MotionEvent me)   //onTouch enables to schedule onclick,onlongclick//~1924R~
//    {                                                            //~1924R~
//        boolean rc=false;                                        //~1924R~
//        int xx,yy,action;                                        //~1924R~
//    //********************                                       //~1924R~
//        try                                                      //~1924R~
//        {                                                        //~1924R~
//            rc=AxeG.axeMouse.onTouchScreen(event);               //~1924R~
//        }                                                        //~1924R~
//        catch(Exception e)                                       //~1924R~
//        {                                                        //~1924R~
//            Dump.println(e,"AxeScreen OnTouchEvent");            //~1924R~
//        }                                                        //~1924R~
//        if (Dump.Y) Dump.println("AxeScreen ontouch rc="+rc);    //~1924R~
//        return rc;                                               //~1924R~
//    }                                                            //~1924R~
//**************                                                   //~1608I~
    public void setColor(Paint Ppaint,int Pcolor)                  //~1608R~
    {                                                              //~1608I~
        Ppaint.setColor(Pcolor);                                   //~1608R~
	}                                                              //~1608I~
//**************                                                   //~1608I~
    public void setFont(Format Pfont)                                //~1608I~
    {                                                              //~1608I~
//        font=Pfont;                                              //~1608I~
//        if (swForeground && ajagocCanvas!=null)                  //~1608I~
//            ajagocCanvas.setFont(Pfont);    //for TextDisplay    //~1608I~
//        else                                                     //~1608I~
//        {          //BigTimer                                    //~1608I~
//            paintText.setTypeface(Pfont.getTypefaceStyle());     //~1620R~
//            paintText.setTextSize(Pfont.getSize());                    //~1117M~//~1620R~
//        }                                                        //~1608I~
    }                                                              //~1608I~
//*******************                                              //~1608I~
//    public void drawText(int Pkey)                                 //~1608I~//~1719R~
//    {                                                              //~1608I~//~1719R~
//        float xx,yy;                                               //~1608I~//~1719R~
//        int hh,ww;                                                 //~1608I~//~1719R~
//    //********************                                         //~1608I~//~1719R~
//        String text=Integer.toHexString(Pkey);                     //~1608I~//~1719R~
//        String mod="Shift="+((Pkey & AxeKeyValue.KBF_SHIFT)!=0) //~1608I~//~1719R~
//                +",Ctrl="+((Pkey & AxeKeyValue.KBF_CONTROL)!=0) //~1609R~//~1719R~
//                +",Alt="+((Pkey & AxeKeyValue.KBF_ALT)!=0);     //~1609R~//~1719R~
//                                                                   //~1608I~//~1719R~
//        xx=(float) 0.0;                                                    //~1608I~//~1719R~
//        yy=(float) 20.0;                                                   //~1608I~//~1719R~
//        ww=AxeG.screenW;                                           //~1608I~//~1719R~
//        hh=AxeG.screenH;                                           //~1608I~//~1719R~
//        fillRect(0,0,ww,hh);                                       //~1608R~//~1719R~
//        bitmapCanvas.drawText(text+" "+mod,xx,yy,paintText);       //~1620R~//~1719R~
//    }                                                              //~1608I~//~1719R~
//    private void fillRect(int Px,int Py,int Pw,int Ph)             //~1608I~//~1809R~
//    {                                                              //~1608I~//~1809R~
//        if (Dump.Y) Dump.println("Graphics fillRect pos=("+Px+","+Py+",w="+Pw+",h="+Ph);//~1608I~//~1809R~
//        Rect rect=new Rect(Px/*left*/,Py/*top*/,Px+Pw/*right*/,Py+Ph/*bottom*/);//~1608I~//~1809R~
//        bitmapCanvas.drawRect(rect,paintFill);                           //~1117R~//~1620R~//~1809R~
//    }                                                              //~1608I~//~1809R~
//*******************                                              //~1610I~
//    public void drawTextFromJNI(String Ptext)                    //~1620R~
//    {                                                            //~1620R~
//        float xx,yy;                                             //~1620R~
//        int hh,ww;                                               //~1620R~
//    //********************                                       //~1620R~
//        xx=(float) 0.0;                                          //~1620R~
//        yy=(float) 60.0;                                         //~1620R~
//        ww=AxeG.screenW;                                         //~1620R~
//        hh=AxeG.screenH;                                         //~1620R~
//        fillRect(0,0,ww,hh);                                     //~1620R~
//        bitmapCanvas.drawText(Ptext,xx,yy,paintText);            //~1620R~
//        invalidate();                                            //~1620R~
//    }                                                            //~1620R~
//*******************                                              //~1620I~
//    public void drawText_Monospace(int Px,int Py,int[] Pucstb,int Pucsctr,int Pfg,int[] Poffsettb)//~1620I~//~1719R~
//    {                                                              //~1620I~//~1719R~
//        float xx,yy,xx0,yy0;                                       //~1718R~//~1719R~
//        int chctr,chpos;//~1620I~                                //~1719R~
//        Paint paint;                                               //~1620I~//~1719R~
//    //********************                                         //~1620I~//~1719R~
//        if (Dump.Y) Dump.println("DrawText_monospace x="+Px+",y="+Py+",ctr="+Pucsctr+",fg="+Integer.toHexString(Pfg));//~1718I~//~1719R~
//        if (Dump.Y) Dump.dumpucs("DrawText_monospace ucstb",Pucstb);//~1718R~//~1719R~
//        if (Dump.Y) Dump.dump("DrawText_monospace offset",Poffsettb);      //~1718I~//~1719R~
//        paint=paintText;                                           //~1620I~//~1719R~
//        xx0=(float)Px;                                             //~1718R~//~1719R~
//        yy=(float)(Py+baseLine);    //in canvas of client area     //~1718R~//~1719R~
//        String textstr=new String(Pucstb,0,Pucsctr);               //~1620I~//~1719R~
//        if (Dump.Y) Dump.println("drawTextMonospace text="+textstr);//~1718I~//~1719R~
//        paint.setColor(Pfg);                                       //~1620R~//~1719R~
//                                                  //~1620I~      //~1719R~
//        for (int ii=0;ii<Pucsctr;ii++)  //String utf16             //~1620I~//~1719R~
//        {                                                          //~1620I~//~1719R~
////            if (Pucstb[ii]>0xffff)  //2                          //~1620I~//~1719R~
////                chctr=2;                                         //~1620I~//~1719R~
////            else                                                 //~1620I~//~1719R~
////                chctr=1;                                         //~1620I~//~1719R~
//            chctr=1;                                             //~1719R~
//            chpos=ii;//~1620I~                                   //~1719R~
//            xx=xx0+Poffsettb[ii];                                  //~1718I~//~1719R~
//            bitmapCanvas.drawText(textstr,chpos,chpos+chctr,xx,yy,paint);//~1620R~//~1719R~
//            if (Dump.Y) Dump.println("drawTextMonospace ii="+ii+",xx="+xx+",yy="+yy);//~1718R~//~1719R~
//        }                                                          //~1620I~//~1719R~
//    }                                                              //~1620I~//~1719R~
    public void drawText_Monospace(int Px,int Py,int[] Pucstb,int Pucsctr,int Pfg,float[] Pfpostb)//~1719I~
    {                                                              //~1719I~
        Paint paint;                                               //~1719I~
        int chctr;                                                 //~vabcI~
    //********************                                         //~1719I~
        if (Dump.Y) Dump.println("DrawText_monospace x="+Px+",y="+Py+",ctr="+Pucsctr+",fg="+Integer.toHexString(Pfg));//~1719I~//~vaiaR~
//      if (Dump.Y) Dump.dumpucs("DrawText_monospace ucstb:",Pucstb);//~1719I~//~1827R~//~1A19R~
//      if (Dump.Y) Dump.dump("DrawText_monospace ucstb:",Pucstb); //~1827I~//~1A19R~
//      if (Dump.Y) Dump.dump("DrawText_monospace offset",Pfpostb);//~1719I~//~1A19R~
        paint=paintText;                                           //~1719I~
//      String textstr=new String(Pucstb,0,Pucsctr);               //~1719I~//~vabcR~
        chctr=Ucs.cpToChars(Pucstb,0,Pucsctr,chLine);              //~vabcI~
//      if (Dump.Y) Dump.println("drawTextMonospace text="+textstr);//~1719I~//~vabcR~
        paint.setColor(Pfg);                                       //~1719I~
        adjustMonospaceOffset(Pfpostb,Px+Gxeh.Mfontoffsx,Py+baseLine);//~1823R~
//      bitmapCanvas.drawPosText(textstr,Pfpostb,paint);           //~1719I~//~vabcR~
        bitmapCanvas.drawPosText(chLine,0,chctr,Pfpostb,paint);    //~vabcI~
//      clearPendingLine(); //for DBCS input;settext+drawline+settext+drawline then ondrow,draw last line to android canvas//~1825I~
    }                                                              //~1719I~
    public void adjustMonospaceOffset(float[] Pfpostb,int Poffsx,int Poffsy)//~1823R~
    {                                                              //~1823I~
    	int sz;                                                    //~1823I~
    //********************                                         //~1823I~
        if (Dump.Y) Dump.println("AdjustMonospaceOffset offsx="+Poffsx+",offsy="+Poffsy);//~1823R~
        sz=Pfpostb.length;                                         //~1823R~
        for (int ii=0;ii<sz;)                                      //~1823R~
        {                                                          //~1823I~
        	Pfpostb[ii++]+=Poffsx;                                 //~1823R~
        	Pfpostb[ii++]=Poffsy;                                  //~1823R~
        }                                                          //~1823I~
        if (Dump.Y) Dump.dump("DrawText_monospace offset",Pfpostb);//~1823I~
    }                                                              //~1823I~
//*******************                                              //~1620I~
    public void drawText_Ligature(int Px,int Py,int[] Pucstb,int Pctr,int Pfg,float Pscale)//~1620R~
    {                                                              //~1620I~
    	float xx,yy;                                               //~1620I~
        Paint paint;                                               //~1620I~
    //********************                                         //~1620I~
        if (Dump.Y) Dump.println("DrawText_Ligature x="+Px+",y="+Py+",ctr="+Pctr+",fg="+Integer.toHexString(Pfg)+",scale="+Pscale);//~1718I~//~vaiaR~
//      if (Dump.Y) Dump.dump("DrawText_Ligature ucstb",Pucstb);         //~1718I~//~1A19R~
        paint=paintText;                                           //~1620I~
        String textstr=new String(Pucstb,0,Pctr);                  //~1620I~
		xx=(float)Px;                                              //~1620I~
        yy=(float)Py+baseLine;                                              //~1620I~//~1719R~
        if (Pscale!=0)                                             //~1620I~
        	paint.setTextScaleX(Pscale);                           //~1620R~
        paint.setColor(Pfg);                                       //~1620R~
        bitmapCanvas.drawText(textstr,xx,yy,paint);                //~1620R~
        if (Pscale!=0)                                             //~1620I~
        	paint.setTextScaleX((float)1.0);                       //~1620R~
//      clearPendingLine(); //for DBCS input;settext+drawline+settext+drawline then ondrow,draw last line to android canvas//~1825I~
    }                                                              //~1620I~
//*******************                                              //~1620I~
    public void drawRect(int Pfill,int Px,int Py,int Pw,int Ph,int Pfg)//~1620I~
    {                                                              //~1620I~
        Paint paint;                                               //~1620I~
    //********************                                         //~1620I~
        paint=paintFill;                                           //~1620R~
        paint.setColor(Pfg);                                       //~1620I~
    	if (Pfill!=0)                                              //~1620I~
        {                                                          //~1620I~
            rectFill.left=Px;                                      //~1809R~
            rectFill.top=Py;                                       //~1809I~
            rectFill.right=Px+Pw;                                  //~1809I~
            rectFill.bottom=Py+Ph;                                 //~1809I~
//          bitmapCanvas.drawRect(rect,paint);                     //~1620R~//~1809R~
            bitmapCanvas.drawRect(rectFill,paint);//exclude bottom/right edge(if strokewidth=0)//~1809R~
        }                                                          //~1620I~
        else                                                       //~1620I~
        {                                                          //~1620I~
            posRect[0]=(float)Px;                                      //~1620I~//~1809R~
            posRect[1]=(float)Py;                                      //~1620I~//~1809R~
            posRect[2]=(float)(Px+Pw);                                 //~1620I~//~1809R~
            posRect[3]=(float)Py;                                      //~1620I~//~1809R~
            posRect[5]=(float)(Px+Pw);                                 //~1620I~//~1809R~
            posRect[6]=(float)(Py+Ph);                                 //~1620I~//~1809R~
            posRect[7]=(float)Px;                                      //~1620I~//~1809R~
            posRect[8]=(float)(Py+Ph);                                 //~1620I~//~1809R~
            bitmapCanvas.drawLines(posRect,0,8,paint);                 //~1620R~//~1809R~
        }                                                          //~1620I~
    }                                                              //~1620I~
//*******************                                              //~1620I~
    public void drawLine(int Pswdirect,int Pwidth,int Px1,int Py1,int Px2,int Py2,int Pfg)//~1620I~//~1809R~
    {                                                              //~1620I~
    //********************                                         //~1620I~
    	if (Dump.Y) Dump.println("Axescreen:drawLine direct="+Pswdirect+",width="+Pwidth+",x1="+Px1+",y1="+Py1+",x2="+Px2+",y2="+Py2+",fg="+Integer.toHexString(Pfg));//~vabcR~
        if ((Pswdirect & AxeJNIdef.DRAWLINE_DIRECT)!=0)                    //~1809I~//~1826R~
        {                                                          //~1809I~
//      	PendingLine pl=new PendingLine(Pwidth,Px1,Py1,Px2,Py2,Pfg);//~1809I~//~1826R~
//          listPL.add(pl);	//avoid GC cause                                        //~1809I~//~1826R~
        	if ((Pswdirect & AxeJNIdef.DRAWLINE_CARET)!=0)                 //~1826I~
            {                                                      //~1826I~
				PLcursor.setPendingLine(Pwidth,Px1,Py1,Px2,Py2,Pfg);//~1826I~
                drawCursor=true;                                   //~1826I~
				if (Dump.Y) Dump.println("drawLine drawCursor="+drawCursor);//~vaicI~
            }                                                      //~1826I~
        	if ((Pswdirect & AxeJNIdef.DRAWLINE_RULER)!=0)                 //~1826I~
            {                                                      //~1826I~
            	if (Py1==Py2)                                      //~1826I~
                {                                                  //~1826I~
					PLruler1.setPendingLine(Pwidth,Px1,Py1,Px2,Py2,Pfg);//~1826I~
                    drawRuler1=true;                               //~1826I~
                }                                                  //~1826I~
                else                                               //~1826I~
                {                                                  //~1826I~
					PLruler2.setPendingLine(Pwidth,Px1,Py1,Px2,Py2,Pfg);//~1826I~
                    drawRuler2=true;                               //~1826I~
                }                                                  //~1826I~
            }                                                      //~1826I~
            return;         //draw at onDraw                       //~1809I~
        }                                                          //~1809I~
    	drawLine(bitmapCanvas,Pwidth,Px1,Py1,Px2,Py2,Pfg);//draw on offscreen//~1809I~
    }                                                              //~1620I~
//*******************                                              //~1809I~
    public void drawLine(Canvas Pcanvas,int Pwidth,int Px1,int Py1,int Px2,int Py2,int Pfg)//~1809I~
    {                                                              //~1809I~
        Paint paint;                                               //~1809I~
    //********************                                         //~1809I~
    	if (Dump.Y) Dump.println("Axescreen:drawLine canvas="+Pcanvas.toString()+",width="+Pwidth+",x1="+Px1+",y1="+Py1+",x2="+Px2+",y2="+Py2);//~1809I~
        paint=paintLine;                                           //~1809I~
        paint.setColor(Pfg);                                       //~1809I~
        paint.setStrokeWidth(Pwidth);                              //~1809I~
        posLine[0]=(float)Px1;                                     //~1809I~
        posLine[1]=(float)Py1;                                     //~1809I~
        posLine[2]=(float)Px2;                                     //~1809I~
        posLine[3]=(float)Py2;                                     //~1809I~
        Pcanvas.drawLines(posLine,0,4,paint);                      //~1809I~
    }                                                              //~1809I~
//*******************                                              //~vabbI~
    public void drawLine(Paint Ppaint,Canvas Pcanvas,int Pwidth,int Px1,int Py1,int Px2,int Py2,int Pfg)//~vabbI~
    {                                                              //~vabbI~
        Paint paint;                                               //~vabbI~
    //********************                                         //~vabbI~
    	if (Dump.Y) Dump.println("Axescreen:drawLine canvas="+Pcanvas.toString()+",width="+Pwidth+",x1="+Px1+",y1="+Py1+",x2="+Px2+",y2="+Py2);//~vabbI~
        paint=Ppaint;                                              //~vabbI~
        paint.setColor(Pfg);                                       //~vabbI~
        paint.setStrokeWidth(Pwidth);                              //~vabbI~
        posLine[0]=(float)Px1;                                     //~vabbI~
        posLine[1]=(float)Py1;                                     //~vabbI~
        posLine[2]=(float)Px2;                                     //~vabbI~
        posLine[3]=(float)Py2;                                     //~vabbI~
        Pcanvas.drawLines(posLine,0,4,paint);                      //~vabbI~
    }                                                              //~vabbI~
//*******************                                              //~vabbI~
    public void drawRect(Paint Ppaint,Canvas Pcanvas,int Pwidth,int Px1,int Py1,int Px2,int Py2,int Pfg)//~vabbR~
    {                                                              //~vabbI~
    //********************                                         //~vabbI~
        if (Dump.Y) Dump.println("DrawRect Cusrsor width="+Pwidth+",y1="+Py1+",y2="+Py2+",x1="+Px1+",x2="+Px2);//~vabbI~
        if (Pwidth<=2)                                             //~vabbI~
        {                                                          //~vabbI~
        	paintCursor.setStyle(Paint.Style.FILL);                //~vabbI~
			drawLine(Ppaint,Pcanvas,Pwidth,Px1,Py1,Px2,Py2,Pfg);   //~vabbI~
            return;                                                //~vabbI~
        }                                                          //~vabbI~
    //draw empty rect                                              //~vabbI~
        paintCursor.setStyle(Paint.Style.STROKE);                  //~vabbI~
        rectFill.left=Px1;                                         //~vabbR~
        rectFill.top=Py1-Pwidth/2;   //Py is cnter of cell         //~vabbR~
        rectFill.right=Px2;                                        //~vabbR~
        rectFill.bottom=Py1+Pwidth/2;                              //~vabbR~
                                                                   //~vabbI~
        Ppaint.setColor(Pfg);                                      //~vabbI~
        Ppaint.setStrokeWidth(0.0f);    //0:hairline(pix=1)        //~vabbI~
        Pcanvas.drawRect(rectFill,Ppaint);                          //~vabbI~
    }                                                              //~vabbI~
    //===============================================================================//~1621I~
    public void setTitle(String Ptitle)                            //~1715R~
    {                                                              //~1621I~
    //******************************                               //~1621I~
	    AxeG.activity.setTitle(Ptitle);                             //~1621I~
    }                                                              //~1621I~
    //===============================================================================//~1715I~
    // screen font                                                 //~1715I~
    //===============================================================================//~1715I~
    public int xxemain_createfont()                         //~1715I~
    {                                                              //~1715I~
        int oldh,oldw;                                    //~1718R~//~1719R~
        FontMetrics fm;                                            //~1718I~
        int baseline;                                           //~1719I~
    //************************************                         //~1715I~
        oldh=Gxeh.Mcellh;                                          //~1716R~
        oldw=Gxeh.Mcellw;                                          //~1716R~
//      ugetfontmetrics(Gfontdata[0].FDfontdesc,&Mfontwidth,&Mfontheight,&Mmonospace);//~1715I~
        fm=Axecsub2.ugetfontmetrics(Gxeh.Gfontdata[0]);    //~1718R~
        baseline=fm.getBaseLine();                                     //~1718I~//~1719R~//~1802R~
        paintText=fm.paintText;                                    //~1718R~
//      Gxeh.Mfontwidth=Gxeh.Mfm_fontwidth;                        //~1822R~
        Gxeh.Mfontwidth=fm.getAvDigitWidth();                      //~1822R~
//      Gxeh.Mfontheight=Gxeh.Mfm_fontheight;                      //~1822R~
//      Gxeh.Mmonospace=Gxeh.Mfm_monospace;                        //~1822R~
        Gxeh.Mmonospace=Axecsub2.ufontismonospace(fm,Gxeh.Mfontwidth);      //~1822I~
        if (Gxeh.Mcellh0!=0)                                          //~1716R~
            Gxeh.Mcellh=Gxeh.Mcellh0;                              //~1716R~
        else                                                       //~1715I~
            Gxeh.Mcellh=Gxeh.Mfontheight;                          //~1716R~
        if (Gxeh.Mcellw0!=0)                                          //~1716R~
            Gxeh.Mcellw=Gxeh.Mcellw0;                              //~1716R~
        else                                                       //~1715I~
            Gxeh.Mcellw=Gxeh.Mfontwidth;                           //~1716R~
        if (Gxeh.Mfontheight<Gxeh.Mcellh-Gxeh.DEF_TEXTCSR_HEIGHT)       //~1716R~
        {                                                          //~1715I~
            Gxeh.Mfontoffsy=(Gxeh.Mcellh-Gxeh.DEF_TEXTCSR_HEIGHT-Gxeh.Mfontheight);//~1716R~
        }                                                          //~1715I~
        else                                                       //~1715I~
            Gxeh.Mfontoffsy=0;                                     //~1716R~
        if (Gxeh.Mfontwidth<Gxeh.Mcellw)                           //~1716R~
            Gxeh.Mfontoffsx=(Gxeh.Mcellw-Gxeh.Mfontwidth)/2;       //~1716R~
        else                                                       //~1715I~
            Gxeh.Mfontoffsx=0;                                     //~1716R~
        baseLine=baseline+Gxeh.Mfontoffsy;                              //~1718I~//~1719R~//~1808R~
        Gxeh.MbaseLine=baseLine;    //notiify to jni;int            //~1719I~
    //setup for monospace                                          //~1715I~
//      pango_layout_set_font_description(Gplayout,Gfontdata[0].FDfontdesc);//~1716R~
//      dprintf("create font old w=%d,h=%d new w=%d,h=%d,desc=%p\n",oldw,oldh,Gxeh.Mcellw,Gxeh.Mcellh,Gfontdata[0].FDfontdesc);//~1716R~
//      if (oldh!=Gxeh.Mcellh||oldw!=Gxeh.Mcellw) //col/row may chnged//~1716R~
//          usetresizehint(Gxeh.Mcellw,Gxeh.Mcellh,Gxeh.Mscrcmaxcol,Gxeh.Mscrcmaxrow);//~1716R~
//      xxe_setsynfontface();                                      //~1716R~
        if (Dump.Y) Dump.println("create font name="+Gxeh.Gfontdata[0].name+",w="+Gxeh.Mfontwidth+",h="+Gxeh.Mfontheight+",offsx="+Gxeh.Mfontoffsx+",offsy="+Gxeh.Mfontoffsy+",baseLine="+baseLine);//~1718I~//~1808R~
        return 0;                                                  //~1715I~
    }//xxemain_createfont                                          //~1715I~
    //===============================================================================//~1716I~
    //write offscreen buff                                         //~1716I~
    //===============================================================================//~1716I~
    public void uinvalidate()                                      //~1716I~
    {                                                              //~1716I~
    //******************************                               //~1716I~
        if (Dump.Y) Dump.println("AxeScreen:uinvalidate");         //~1716I~
        if (Dump.Y) Dump.println("uinvalidate invalidate issue="+((AxeG.main.axeStat & Axe.AXES_ORIENTATION_CHANGING)==0));//~vaicR~
      if ((AxeG.main.axeStat & Axe.AXES_ORIENTATION_CHANGING)==0)  //~vaicR~
        invalidate();                                              //~1716I~
    }                                                              //~1716I~
    public static void gettextwidths(int[] Ppdata,float[] Ppfwidthtb)//~1718R~
    {                                                              //~1718I~
        Paint paint;
        String str;//~1718I~
        int widthctr,ucsctr;                                       //~vabaI~
    //******************************                               //~1718I~
        if (Dump.Y) Dump.dumpucs("gettextwidths ucstb=",Ppdata);   //~1718I~
        paint=AxeG.axeScreen.paintText;
        ucsctr=Ppdata.length;                                      //~vabaI~
//      str=new String(Ppdata,0,Ppdata.length);//~1718I~           //~vabaR~
        str=new String(Ppdata,0,ucsctr);                           //~vabaI~
      widthctr=                                                    //~vabaI~
        paint.getTextWidths(str,Ppfwidthtb);                    //~1718R~
        if (Dump.Y) Dump.println("gettextwidths fwidthtb ucsctr="+ucsctr+",outwidth ctr="+widthctr);//~vabaI~
        if (Dump.Y) Dump.dump("gettextwidths fwidthtb",Ppfwidthtb);//~1718R~
        if (widthctr>ucsctr)    //contains ucs4,android4 output 2 entry for ucs4//~vabaI~
			Ucs.getCodepointWidthTbl(Ppdata,ucsctr,Ppfwidthtb);    //~vabaI~
        return;                                                    //~1718I~
    }//gettextwidths                                               //~1718I~
    public void showKbdRequest()                                   //~1827I~
    {                                                              //~1827I~
    //******************************                               //~1827I~
        if (Dump.Y) Dump.println("AxeScreen showKbdRequest");      //~1827I~
        showKbdWhenFocusOn=true;                                   //~1827I~
    }//gettextwidths                                               //~1827I~
    //**************************************************************//~vairI~
    //*from ImputMethodService                                     //~vairI~
    //*Whether to show IME,return true if IME popup                //~vairI~
    //**************************************************************//~vairI~
    public static boolean hardKbdOff()                             //+vairR~
    {                                                              //~vairI~
        boolean rc;                                                //~vairI~
    //******************************                               //~vairI~
        Configuration cfg=AxeG.resource.getConfiguration();        //~vairI~
        if (Dump.Y) Dump.println("evaluateShowIME keyboard="+Integer.toHexString(cfg.keyboard));//~vairI~
        if (Dump.Y) Dump.println("evaluateShowIME keyboardHidden="+Integer.toHexString(cfg.keyboardHidden));//~vairI~
        if (Dump.Y) Dump.println("evaluateShowIME hardKeyboardHidden="+Integer.toHexString(cfg.hardKeyboardHidden));//~vairI~
        if (Dump.Y) Dump.println("evaluateShowIME screenLayout="+Integer.toHexString(cfg.screenLayout));//~vairI~
        rc=cfg.keyboard==Configuration.KEYBOARD_NOKEYS/*1*/        //~vairI~
//       ||cfg.hardKeyboardHidden==Configuration.HARDKEYBOARDHIDDEN_YES/*2*///+vairR~
        ;   //is01 kb=2,hk-hidden=2(yes)                           //+vairI~
            //nexus7 bluetooth on:kb=2(qwerty),hardkbh=1(no) off:kb=1(nokey),harkbh=2(yes)//+vairI~
        if (Dump.Y) Dump.println("evaluateShowIME rc="+rc);        //~vairI~
        return rc;                                                 //~vairI~
    }                                                              //~vairI~
}                                                                  //~1528R~
