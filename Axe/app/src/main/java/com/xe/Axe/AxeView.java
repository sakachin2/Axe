//*CID://+vc5bR~: update#=  217;                                   //~vc5aR~//~vc5bR~
//*************************************************************    //~vab0I~
//vc5b 2023/06/29 (Bug)titlebar bottome sometime invalid when rotated. use 1st time value//~vc5bI~
//vc5a 2023/06/28 (Bug)g10(Api30:A11) portrait, bottom buttons was hidden//~vc5aI~
//vc42 2023/03/25 api33 support;deprecated display.getSize()       //~vc42I~
//vc41 2023/03/25 api33 support;deprecated getDefaultDisplay       //~vc41I~
//vc31 2020/09/21 change titlebar icon when debuggable             //~vc31I~
//vc2z 2020/08/12 Button label for user,recover over restart       //~vc2zI~
//vc2l 2020/07/29 (Bug)large button height when changed to HWKbd layout(one line)//~vc2lI~
//vc1u 2020/07/06 helpdialog for asset/helptexts                   //~vc1uI~
//vc1r 2020/06/26 avoid ime popup implicitly                       //~vc1rI~
//vaye:141125 (Axe)orientationfix allow to change to reverse orientation//~vayeI~
//vayb:141125 (Axe)Disply:getWidth/getHeight was deprecated at aoi13(HONNEYCOMB_MR2) change to getSize//~vaybI~
//vay5:141122 (Axe)actionBar as alternative of menu button for api>=11(android3)//~vay5I~
//            When requestWindowFeature(FEATURE_LEFT_ICON). onCreateOptionsMenu is not called(No ActionBar on android>=3.0)//~vay5I~
//vaiq:130606 Axe:issue initial setup Alert(Be paitient)           //~vaidI~
//vaid:130529 Axe:Bug:when button latout changed to 1 line<-->2line//~vab0I~
//            gray field button line remains until restart         //~vab0I~
//vab0:120119 (Axe)android3(honeycomb) tablet has Ssystem bar at bottom that hide xe button line with 48pix height//~vab0I~
//*************************************************************    //~vab0I~
package com.xe.Axe;                                                //~@@@@I~

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Insets;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.LinearLayout;

import com.ahsv.AG;
import com.ahsv.utils.UView;
import com.ahsv.utils.Utils;                                        //~vc42I~

import java.util.Arrays;

public class AxeView                                               //~1606R~
{                                                                  //~0914I~
	private static final int PARENTVIEW=R.id.MainView;//parent of frame view//~1606R~
//@	private static final int FRAMEVIEW=R.id.ClientArea;//parent of frame view//~1606I~
	private static final int LL_BUTTONS=R.id.Buttons;//parent of frame view//~1606I~
	private static final int LL_BUTTONS1=R.id.Buttons1;//parent of frame view//~vc2lI~
	private static final int LL_BUTTONSDMZ=R.id.MainViewDMZ;
//  private Canvas[] canvasPL=new Canvas[2];     //portrate and landscape//~1608R~
    private Bitmap[] bitmapPL=new Bitmap[2];                      //~1606I~
    private int[] scrWidth=new int[2],scrHeight=new int[2];
    private float[] scrDip2Pix=new float[2];            //~1606I~
//  private Canvas canvas;                                         //~1608R~
    private Bitmap bitmap;                                        //~1606I~
    private LinearLayout parentView;                               //~1606R~
//@ private FrameLayout frameView;                                 //~1606R~
//@ private ImageView   frameView;                                 //~1606R~
//  private LinearLayout llbuttons;                                //~vay5R~
//  public static LinearLayout llbuttons;//see from AxeMenu for popupMenu//~vay5I~//~vc2lR~
    public static LinearLayout llbuttons1;//see from AxeMenu for popupMenu//~vc2lI~
    private AxeScreen screen;                                      //~1606I~
    private int buttonHeight,buttonHeight2;                        //~1822I~
    private int buttonDMZHeight;                                   //~1922I~
	//*******************************                              //~1822I~
	public AxeView()                                               //~1528R~
    {   
	    AxeProp.initFiles();                                       //~1A11R~
    	AxeDlgArmOption.updateEnvPath(false/*PswJNICall*/);	//update Gxeh.envPath(contain $Data1) by Gxeh.addPath(~/sh:_/bin/)        //~vc1pI~//~vay2I~
        AxeG.axeJNI=new AxeJNI();   //avoid GC by static ref       //~1713I~
        AxeG.axecsub=new Axecsub();                                //~1A22R~
        AxeG.axeActivity=new AxeActivity();   //avoid GC by static ref//~1A18I~
//  	AxeJNI.init();                                             //~vaiqR~
//      AxeG.axeAlert=new AxeAlert();                              //~vaiqR~
    	AxeJNI.init();                                             //~vaiqI~
        AxeG.axeMenu=new AxeMenu();                                   //~1528I~
		AxeG.axeMenu.registerContextMenu(AxeG.mainView);           //~1609R~
//      getDisplaySize();                                          //~1606R~
        AxeG.axeKey=new AxeKey();    //before AxeButtonLayout      //~vc2zI~
        AxeG.axeButtonLayout=new AxeButtonLayout();                   //~1528I~
//      AxeG.axeKey=new AxeKey();//~1528R~                         //~vc2zR~
        AxeG.axeMouse=new AxeMouse();                              //~1621I~
        parentView=(LinearLayout)AxeG.mainView.findViewById(PARENTVIEW);//~1217I~//~1606R~
	    if (Dump.Y) Dump.println("AxeView initial parentview="+parentView.toString());//~1821I~
//@     frameView=(FrameLayout)AxeG.mainView.findViewById(FRAMEVIEW);//~1606R~
//@     frameView=(ImageView)AxeG.mainView.findViewById(FRAMEVIEW);//~1606R~
//      llbuttons=(LinearLayout)AxeG.mainView.findViewById(LL_BUTTONS);//~1606I~//~vc2lR~
        llbuttons1=(LinearLayout)AxeG.mainView.findViewById(LL_BUTTONS1);//~vc2lI~
//        measureSize();                                           //~1606R~
//  	int orientationFix=AxeButtonLayout.getOrientationFix();    //~vayeR~
//      requestOrientationFix(orientationFix);                     //~vayeR~
	}                                                              //~1714I~
//*************************                                        //~vayeI~
//*at first called from onCreate to avoid finish at initial orientation change by armoption:orientation fix//~vayeI~
//*************************                                        //~vayeI~
    public static void setInitialOrientation()                     //~vayeI~
    {                                                              //~vayeI~
        if (Dump.Y) Dump.println("setInitialOrientation");         //~vayeI~
    	int orientationFix=AxeButtonLayout.getOrientationFix();    //~vayeM~
        requestOrientationFix(orientationFix);	//do not finish at initial orientation change by armoption:orientation fix//~vayeM~
    }                                                              //~vayeI~
//*************************                                        //~1715I~
//*rc:W and H determined                                           //~1715I~
//*************************                                        //~1715I~
	public boolean initScreen()                                    //~1715R~
    {                                                              //~1714I~
    	boolean rc;                                                //~1715I~
    //**************                                               //~1715I~
//        if (parentView!=null & screen!=null)    //after orientation change//~vay2R~
//        {                                                        //~vay2R~
//            if (parentView==screen.getParent())                  //~vay2R~
//                parentView.removeView(screen);   //create new screen//~vay2R~
//        }                                                        //~vay2R~
		rc=setScreenSize();                                        //~1715R~
        return rc;                                                 //~1715I~
    }                                                              //~0914I~
//*************************                                        //~1606M~
	public void orientationChanged()                               //~1606M~
    {                                                              //~1606M~
        parentView.removeView(screen);                             //~1607M~
	    if (Dump.Y) Dump.println("AxeView orientation changed remove screen from parentview:"+parentView.toString());//~1821I~
		setContentView();                                          //~1606I~
        parentView=(LinearLayout)AxeG.mainView.findViewById(PARENTVIEW);//~1607I~
	    if (Dump.Y) Dump.println("AxeView orientation changed new parentview:"+parentView.toString());//~1821I~
//      llbuttons=(LinearLayout)AxeG.mainView.findViewById(LL_BUTTONS);//~1607I~//~vc2lR~
		setScreenSize();    //createScreen                         //~1822R~
        AxeG.axeButtonLayout.orientationChanged();                 //~1606I~
//@     createBitmap(AxeG.screenW,AxeG.screenH);	//bitmap and canvas//~1822R~
//@     updateScreen();                                            //~1822R~
    }                                                              //~1606M~
//*************************                                        //~1607I~
    public void layoutChanged(boolean PisALine)                    //~1607I~
    {                                                              //~1607M~
        int h;                                                   //~1607M~
//        if (AxeG.displayPL!=0 && AxeButtonLayout.getUseLand())	//landscape 1line mode//~1607M~
//       	return;                                                //~1607M~
        h=scrHeight[AxeG.displayPL];                               //~1607M~
        if (PisALine)	//changed to 1 line                        //~1607M~
        	h+=buttonHeight;                                       //~1607M~
        else                                                       //~1607M~
        	h-=buttonHeight;                                       //~1607M~
	    if (Dump.Y) Dump.println("AxeView updatebitmap 1line="+PisALine+",h="+h);//~vc1uI~
	    screenHeightChanged(h);                                    //~1607I~
    }                                                              //~1607M~
//*************************                                        //~1822I~
    private void screenHeightChanged(int Ph)                       //~1822R~
    {                                                              //~1607I~
	    if (Dump.Y) Dump.println("AxeView screenHeightChanged Ph="+Ph);//~vc42I~
//      parentView.removeView(screen);                             //~1822R~
        createBitmap(AxeG.screenW,Ph);                             //~1822R~
        scrHeight[AxeG.displayPL]=Ph;                              //~1608M~
        AxeG.screenH=Ph;	//addToParent layout param             //~1608I~
//      addToParent();                                             //~1822R~
        updateScreenBitmap();                                      //~1822I~
        changeLayoutParams(Ph);                                    //~vaidR~
        screen.updateButtonLayout(bitmap);	                           //~1607I~
	    AxeJNI.setScreenSize(AxeG.screenW,AxeG.screenH);           //~1822I~
    }                                                              //~1607I~
//*************************                                        //~vaidR~
    private void changeLayoutParams(int Ph)                        //~vaidR~
    {                                                              //~vaidR~
//      RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)(parentView.getLayoutParams());//~vaidR~
        ViewGroup.LayoutParams params=(ViewGroup.LayoutParams)(screen.getLayoutParams());//~vaidR~
        if (Dump.Y) Dump.println("changeLayoutParams h="+params.height+"-->"+Ph);//~vaidR~
        params.height=Ph;                                          //~vaidR~
//      parentView.setLayoutParams(params);                        //~vaidR~
        screen.setLayoutParams(params);                            //~vaidR~
    }                                                              //~vaidR~
//*************************                                        //~1606I~
	public static void setContentView()                                 //~1606I~
    {                                                              //~1606I~
    	View v;                                                    //~1606I~
        int pl,id;                                                 //~1606I~
    //************                                                 //~1606I~
    	getDisplaySize();                                          //~1606I~
        pl=AxeG.displayPL;                                         //~1606I~
//        boolean useland=AxeButtonLayout.getUseLand();              //~1607R~//~1810R~
//        if (!useland)                                              //~1607R~//~1810R~
//            pl=0;       //Portlate:two line button layout          //~1606I~//~1810R~
        v=AxeG.mainViewPL[pl];                                     //~1606I~
        if (Dump.Y) Dump.println("SetContentView pl=="+pl);        //~1606I~
        if (v==null)                                               //~1606I~
        {                                                          //~1606I~
        	id=AxeG.mainLayoutIdPL[pl];                            //~1606I~
        	v=AxeG.inflater.inflate(id,null);                           //~1606I~
	       AxeG.mainViewPL[pl]=v;                                 //~1606I~
        }                                                          //~1606I~
        if (v!=AxeG.mainView)                                      //~1607I~
        {                                                          //~1607I~
		    AxeG.activity.setContentView(v);                       //~1607R~
       		AxeG.mainView=v;                                       //~1607R~
            if (AxeG.axeMenu!=null)                                //~1609I~
				AxeG.axeMenu.registerContextMenu(v);               //~1609R~
//*titlebar icon                                                   //~1811I~
//          int iconRid=R.drawable.wxe;                            //~1811I~//~vc31R~
            int iconRid;                                           //~vc31I~
          	if (AxeG.isDebuggable)                                 //~vc31I~
				iconRid=R.drawable.wxe_debug;                      //~vc31R~
          	else                                                   //~vc31I~
				iconRid=R.drawable.wxe;                            //~vc31I~
          if (AxeG.osVersion<AxeG.HONEYCOMB)  //<android3=api-11 required?//~vay5R~
    		AxeG.activity.getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,iconRid);//~1811I~
        }                                                          //~1607I~
        if (Dump.Y) Dump.println("SetContentView="+v.toString());  //~1607R~
    }                                                              //~1606I~
////**************                                                 //~1606R~
//    private void measureSize()                                   //~1606R~
//    {                                                            //~1606R~
//        LinearLayout v=(LinearLayout)AxeG.mainView.findViewById(R.id.Buttons);//~1606R~
//        if (Dump.Y) Dump.println("buttons ="+v.getWidth()+","+v.getHeight());//~1606R~
//        int fp=ViewGroup.LayoutParams.FILL_PARENT;               //~1606R~
//        int wc=ViewGroup.LayoutParams.WRAP_CONTENT;              //~1606R~
//        LinearLayout ll=new LinearLayout(AxeG.context)           //~1606R~
//        {                                                        //~1606R~
//            @Override                                            //~1606R~
//            public void onSizeChanged(int Pw,int Ph,int Poldw,int Poldh)//~1606R~
//            {                                                    //~1606R~
//                if (Dump.Y) Dump.println("ll onSizeChanged "+Pw+","+Ph+","+Poldw+","+Poldh);//~1606R~
//            }                                                    //~1606R~
//        };                                                       //~1606R~
//        ll.setLayoutParams(new LayoutParams(fp,wc));             //~1606R~
//        frameView.addView(ll);                                   //~1606R~
//    }                                                            //~1606R~
//*********************************                                //~1822I~
//*init or orientation changed                                     //~1822I~
//*********************************                                //~1822I~
    private AxeScreen createScreen()                               //~1606I~
    {                                                              //~1606I~
	    if (Dump.Y) Dump.println("AxeView createScreen AxeG.screenW="+AxeG.screenW+",H="+AxeG.screenH);//~vc42I~
        createBitmap(AxeG.screenW,AxeG.screenH);                   //~1822I~
        if (screen==null)	//one screen for portrait and landscape//~1822I~
        	screen=new AxeScreen(bitmap);                          //~1822R~
        else                                                       //~1822I~
        	updateScreenBitmap();	//update screen bitmap         //~1822I~
        addToParent();                                             //~1822I~
        return screen;//~1606I~
    }                                                              //~1606I~
//*********************************                                //~1822I~
//*replace screen bitmap                                           //~1822I~
//*********************************                                //~1822I~
    private void updateScreenBitmap()                              //~1822R~
    {                                                              //~1822R~
        screen.replaceBitmap(bitmap);                              //~1822R~
    }                                                              //~1822R~
//*********************************                                //~1822I~
    private void addToParent()                                     //~1607I~
    {                                                              //~1607I~
        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(AxeG.screenW,AxeG.screenH);//~1607I~
//        ViewGroup oldparent=(ViewGroup)screen.getParent();       //~1822R~
//        if (oldparent!=null)                                     //~1822R~
//        {                                                        //~1822R~
//            if (Dump.Y) Dump.println("AxeView addToParent remove from oldparent:"+oldparent.toString());//~1822R~
//            oldparent.removeView(screen);                        //~1822R~
//        }                                                        //~1822R~
	    if (Dump.Y) Dump.println("AxeView addToParent parent="+parentView.toString());//~1821M~
        parentView.addView(screen,lp);                             //~1607I~
	    if (Dump.Y) Dump.println("AxeView addToParent parent=("+AxeG.screenW+","+AxeG.screenH+")");//~1607I~//~vc2zR~
        AxeG.axeMouse.initScreenMouse(screen);	//after parent set //~1924I~
    }                                                              //~1607I~
//*********************************                                //~1822I~
//  public void createBitmap(int Pw,int Ph)                        //~1607R~//~vc5aR~
    private void createBitmap(int Pw,int Ph)                       //~vc5aI~
    {                                                              //~1606I~
	    if (Dump.Y) Dump.println("AxeView createBitmap ("+Pw+","+Ph+")"+",navigationBottomHeight="+AxeG.scrNavigationbarBottomHeightA11);//~vc5aR~
        bitmap=bitmapPL[AxeG.displayPL];                            //~1606R~
        int bitmapH=scrHeight[AxeG.displayPL];                     //~1607R~
	    if (Dump.Y) Dump.println("AxeView oldBitmapH="+bitmapH);   //~1608I~
        if (Ph!=bitmapH || bitmap==null)                            //~1607R~
        {                                                          //~1606I~
        	if (bitmap!=null)                                       //~1607I~
            {                                                      //~1607I~
		    	if (Dump.Y) Dump.println("AxeView recycle bitmap "+bitmap.toString());//~1607I~
            	bitmap.recycle();                                  //~1607I~
            }                                                      //~1607I~
        	bitmap=Bitmap.createBitmap(Pw,Ph,Bitmap.Config.ARGB_8888);                       //~1606I~
	    	if (Dump.Y) Dump.println("AxeView createBitmap ("+Pw+","+Ph+")="+bitmap.toString());//~1607I~
//      	canvas=new Canvas(bitmap);                             //~1608R~
            bitmapPL[AxeG.displayPL]=bitmap;                        //~1606R~
//          canvasPL[AxeG.displayPL]=canvas;                       //~1608R~
        }                                                          //~1606I~
    }                                                              //~1606I~
//*************************                                        //~1606I~
//*rc:W and H determined                                           //~1715I~
//*************************                                        //~1715I~
	private boolean setScreenSize()                                //~1715R~
    {                                                              //~1606I~
    	boolean rc=false;                                          //~1715I~
    //****************                                             //~1715I~
//      if (scrWidth[AxeG.displayPL]==0)	//initialy true ,buttonNo1 may changed//~1607R~
        	getScreenSize();                                       //~1606R~
        AxeG.screenH=scrHeight[AxeG.displayPL];                     //~1606R~
        AxeG.screenW=scrWidth[AxeG.displayPL];                      //~1606I~
        AxeG.screenDip2Pix=scrDip2Pix[AxeG.displayPL];              //~1606I~
        if (AxeG.screenW>0 && AxeG.screenH>0)                      //~1715I~
        {                                                          //~1715I~
//          AxeG.axeScreen=createScreen();                         //~1715I~//~vc1rR~
            createScreen();                                        //~vc1rI~
	        AxeJNI.setScreenSize(AxeG.screenW,AxeG.screenH);       //~1715R~
            if (AxeG.optDump==2)                                   //~1922I~
        		Dump.setOption(true);     //if dump2 internal option, strat dump from this point//~1922I~
            if ((AxeG.optTrace & AxeG.TRACEO_REOPEN)!=0)                                  //~1922I~//~1A19R~
	    		AxeJNI.setTraceOption(AxeG.optTrace);                       //~1922I~
            rc=true;                                               //~1715I~
        }                                                          //~1715I~
        return rc;                                                 //~1715I~
    }                                                              //~1606I~
//**********************************                               //~1A6pI~//~vc42I~
    public static void getDisplaySize(Display Pdisplay,Point Ppoint)//~vam6I~//~vc42R~
    {                                                              //~1A6pI~//~vc42I~
        if (Dump.Y) Dump.println("AxeView: getDisplaySize osVersion="+Build.VERSION.SDK_INT);//~vataI~//~vc42R~
		if (Build.VERSION.SDK_INT>=33)   //android-13(T)           //~vataI~//~vc42I~
			getDisplaySize33(Pdisplay,Ppoint);                     //~vataI~//~vc42I~
        else                                                       //~vataI~//~vc42I~
		if (Build.VERSION.SDK_INT>=31)                             //~vam6I~//~vc42I~
			getDisplaySize31(Pdisplay,Ppoint);                     //~vam6I~//~vc42I~
        else                                                       //~vam6I~//~vc42I~
		if (Build.VERSION.SDK_INT>=30)   //android30(R)            //~1aj0I~//~vc42I~
			getDisplaySize30(Pdisplay,Ppoint);                     //~1aj0I~//~vc42I~
        else             //getSize deprecated api30                                          //~1aj0I~//~vc42I~
			getDisplaySize29(Pdisplay,Ppoint);                     //~1aj0I~//~vc42I~
	    if (Dump.Y) Dump.println("AxeView:getDisplaySize point="+Ppoint);//~vam6I~//~vc42R~
    }                                                              //~1A6pI~//~vc42I~
    //*******************************************************      //~vataI~//~vc42I~
    @TargetApi(33)   //>=33 Android13           //~vataI~          //~vc42I~
	public static void getDisplaySize33(Display Pdisplay,Point Ppoint)              //~vataI~//~vc42I~
    {                                                              //~vataI~//~vc42I~
	    if (Dump.Y) Dump.println("AxeView:getDisplaySize33");        //~vataI~//~vc42R~
        WindowMetrics wm=AxeG.activity.getWindowManager().getCurrentWindowMetrics();//~vataI~//~vc42R~
	    int ww0=wm.getBounds().width();                            //~vataI~//~vc42I~
	    int hh0=wm.getBounds().height();                           //~vataI~//~vc42I~
	    if (Dump.Y) Dump.println("AxeView:getDisplaySize33 windowMetrics ww="+ww0+",hh="+hh0);//~vataI~//~vc42R~
        Rect rectDecor=getDecorViewRect();                         //~vataI~//~vc42I~
	    if (Dump.Y) Dump.println("AxeView:getDisplaySize33 rectDecor="+rectDecor.toString());//~vataI~//~vc42R~
        Insets insetnavi=wm.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars());//TODO test//~vataI~//~vc42I~
	    if (Dump.Y) Dump.println("AxeView:getDisplaySize33 insetnavi="+insetnavi.toString());//~vataI~//~vc42R~
        Insets insetstatus=wm.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.statusBars());//TODO test//~vataI~//~vc42I~
	    if (Dump.Y) Dump.println("AxeView:getDisplaySize33 insetstatus="+insetstatus.toString());//~vataI~//~vc42R~
        Insets insetnaviv=wm.getWindowInsets().getInsets(WindowInsets.Type.navigationBars());//TODO test//~vataI~//~vc42I~
	    if (Dump.Y) Dump.println("AxeView:getDisplaySize33 insetnaviv="+Utils.toString(insetnaviv));//~vataI~//~vc42R~
        Insets insetstatusv=wm.getWindowInsets().getInsets(WindowInsets.Type.statusBars());//TODO test//~vataI~//~vc42I~
	    if (Dump.Y) Dump.println("AxeView:getDisplaySize33 insetstatus visible="+Utils.toString(insetstatusv));//~vataI~//~vc42R~
        Insets insetsys=wm.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());//TODO test//~vataI~//~vc42I~
	    if (Dump.Y) Dump.println("AxeView:getDisplaySize33 insetsys="+insetsys.toString());//~vataI~//~vc42R~
                                                                   //~vataI~//~vc42I~
        Insets inset=wm.getWindowInsets().getInsets(WindowInsets.Type.systemGestures());//~vataI~//~vc42I~
	    if (Dump.Y) Dump.println("AxeView:getDisplaySize33 inset systemGesture="+Utils.toString(inset));//~vataI~//~vc42R~
                                                                   //~vataI~//~vc42I~
        int ww,hh;                                                 //~vataI~//~vc42I~
        AxeG.swNavigationbarGestureMode=inset.left!=0 && inset.right !=0 && inset.top!=0 && inset.bottom!=0;//~vataI~//~vc42R~
        ww=ww0-inset.left-inset.right;                             //~vataI~//~vc42I~
        hh=hh0-inset.bottom;  //fullscreen(no title) mode,bottom is 3button/gesture navigationbar//~vataI~//~vc42I~
        if (ww0>hh0)	//landscape                                //~vataI~//~vc42I~
        {                                                          //~vataI~//~vc42I~
            hh=hh0; //hide navigationbar at MainActivity           //~vataR~//~vc42I~
            ww=ww0; //fill hidden navigationbar, but right buttons has to be shift to left//~vataR~//~vc42I~
        }                                                          //~vataI~//~vc42I~
        else                                                       //~vataI~//~vc42I~
	        ww=ww0;                                                //~vataI~//~vc42I~
        AxeG.scrNavigationbarBottomHeightA11=inset.bottom;           //~vataI~//~vc42R~
        int marginLR;                                              //~vataI~//~vc42I~
//        if (AxeG.swNavigationbarGestureMode)                       //~vataR~//~vc42R~
//        {                                                        //~vataR~//~vc42I~
            int left=inset.left;                                   //~vataR~//~vc42I~
            int right=inset.right;                                 //~vataR~//~vc42I~
//      	marginLR=right;                                        //~vataI~//~vc42I~
        	marginLR=Math.max(left,right);                         //~vataR~//~vc42I~
		    if (Dump.Y) Dump.println("AxeView:getDisplaySize33 swPortrait="+ AG.portrait+",marginLR="+marginLR+",left="+left+",right="+right);//~vataR~//~vc42R~
//        }                                                        //~vataR~//~vc42I~
//        else  //3button mode                                     //~vataR~//~vc42I~
//        {                                                        //~vataR~//~vc42I~
//            marginLR=ww0-(inset.right+inset.left);               //~vataR~//~vc42I~
//            if (Dump.Y) Dump.println("AxeView:getDisplaySize33 3 button mode marginLR="+marginLR);//~vataR~//~vc42R~
//        }                                                        //~vataR~//~vc42I~
        AxeG.scrNavigationbarRightWidthA11=marginLR;                 //~vataI~//~vc42R~
        Ppoint.x=ww; Ppoint.y=hh;                                  //~vataI~//~vc42I~
        AxeG.scrStatusBarHeight=inset.top;                           //~vataI~//~vc42R~
	    if (Dump.Y) Dump.println("AxeView:getDisplaySize33 navigationbar bottomHA11="+AxeG.scrNavigationbarBottomHeightA11+",leftWA11="+AxeG.scrNavigationbarLeftWidthA11+",rightWA11="+AxeG.scrNavigationbarRightWidthA11+",swgesturemode="+AxeG.swNavigationbarGestureMode);//~vataI~//~vc42R~
	    if (Dump.Y) Dump.println("AxeView:getDisplaySize33 point="+Ppoint.toString()+",statusBarHeight="+AxeG.scrStatusBarHeight);//~vataI~//~vc42R~
    }                                                              //~vataI~//~vc42I~
    //*******************************************************      //~vam6M~//~vc42I~
    @TargetApi(31)   //>=31                                        //~vam6M~//~vc42I~
    public static void getDisplaySize31(Display Pdisplay,Point Ppoint)//~vam6M~//~vc42I~
    {                                                              //~vam6M~//~vc42I~
        WindowMetrics metrics=getRealMetrics_from31(Pdisplay);     //~vam6M~//~vc42I~
        Insets insetGesture=metrics.getWindowInsets().getInsets(WindowInsets.Type.systemGestures());//~vam6I~//~vc42I~
	    if (Dump.Y) Dump.println("AxeView:getDisplaySize31 insetGesture="+insetGesture);//~vam6I~//~vc42R~
        AxeG.swNavigationbarGestureMode=insetGesture.left!=0 && insetGesture.right !=0 && insetGesture.top!=0 && insetGesture.bottom!=0;//~vam6I~//~vc42R~
                                                                   //~vam6I~//~vc42I~
		Rect bounds=metrics.getBounds();                           //~vam6M~//~vc42I~
	    int ww0=bounds.width();                                    //~vam6M~//~vc42I~
	    int hh0=bounds.height();                                   //~vam6M~//~vc42I~
	    if (Dump.Y) Dump.println("AxeView:getDisplaySize31 bounds="+bounds);//~vam6I~//~vc42R~
        WindowInsets windowInsets=metrics.getWindowInsets();       //~vam6M~//~vc42I~
        Insets inset=windowInsets.getInsetsIgnoringVisibility      //~vam6M~//~vc42I~
						(WindowInsets.Type.navigationBars()|WindowInsets.Type.displayCutout());//~vam6M~//~vc42I~
	    if (Dump.Y) Dump.println("AxeView:getDisplaySize31 inset="+inset);//~vataI~//~vc42R~
        int insetWW=inset.right+inset.left;                        //~vam6M~//~vc42I~
        int insetHH=inset.top+inset.bottom;                        //~vam6M~//~vc42I~
        Rect rectDecor=getDecorViewRect();                         //~vam6M~//~vataM~//~vc42I~
	    if (Dump.Y) Dump.println("AxeView:getDisplaySize31 rectRecor="+rectDecor.toString());//~vataI~//~vc42R~
	    if (Dump.Y) Dump.println("AxeView:getDisplaySize31 insetWW="+insetWW+",insetHH="+insetHH+",insets="+inset);//~vam6M~//~vc42R~
                                                                   //~vam6M~//~vc42I~
        int ww=ww0-insetWW;                                        //~vam6I~//~vc42I~
        int hh=hh0-insetHH;                                        //~vam6I~//~vc42I~
//        if (ww0>hh0)    //landscape                                //~vam6I~//~vc42I~//~vc5aR~
//        {                                                          //~vam6I~//~vc42I~//~vc5aR~
//            hh=hh0; //hide navigationbar at MainActivity           //~vam6I~//~vc42I~//~vc5aR~
//            ww=ww0; //fill hidden navigationbar, but right buttons has to be shift to left//~vam6I~//~vc42I~//~vc5aR~
//        }                                                          //~vam6I~//~vc42I~//~vc5aR~
//        else                                                       //~vam6I~//~vc42I~//~vc5aR~
//            ww=ww0;                                                //~vam6I~//~vc42I~//~vc5aR~
        AxeG.scrNavigationbarBottomHeightA11=inset.bottom;           //~vam6M~//~vc42R~
        int marginLR;                                              //~vam6M~//~vc42I~
//      if (AxeG.swNavigationbarGestureMode)                         //~vam6R~//~vc42R~
//      {                                                          //~vam6R~//~vc42I~
//          int left=rectDecor.left;	//landscape effect delayed to Decorview//~vam6R~//~vc42I~
//          int right=ww0-rectDecor.right;                         //~vam6R~//~vc42I~
//      	marginLR=Math.max(left,right);                         //~vam6R~//~vc42I~
//  	    if (Dump.Y) Dump.println("AxeView:getDisplaySize31 gesture mode marginLR="+marginLR);//~vam6R~//~vc42R~
//      }                                                          //~vam6R~//~vc42I~
//      else  //3button mode                                       //~vam6R~//~vc42I~
//      {                                                          //~vam6R~//~vc42I~
//      	marginLR=ww0-(rectDecor.right-rectDecor.left);	//landscape effect delayed to Decorview//~vam6R~//~vc42I~
//  	    if (Dump.Y) Dump.println("AxeView:getDisplaySize31 3 button mode marginLR="+marginLR);//~vam6R~//~vc42R~
//      }                                                          //~vam6R~//~vc42I~
//      marginLR=0; //hide navigationBar on landscape, no navigationbar on portrait//~vam6I~//~vateR~//~vc42I~
        int left=inset.left;                                       //~vateI~//~vc42I~
        int right=inset.right;                                     //~vateI~//~vc42I~
        marginLR=Math.max(left,right);                             //~vateI~//~vc42I~
		if (Dump.Y) Dump.println("AxeView:getDisplaySize33 swPortrait="+AG.portrait+",marginLR="+marginLR+",left="+left+",right="+right);//~vateI~//~vc42R~
        AxeG.scrNavigationbarRightWidthA11=marginLR;                 //~vam6M~//~vc42R~
        AxeG.scrStatusBarHeight=inset.top;                           //~vam6M~//~vc42R~
                                                                   //~vam6M~//~vc42I~
        Ppoint.x=ww;                                               //~vam6R~//~vc42I~
        Ppoint.y=hh;                                               //~vam6R~//~vc42I~
	    if (Dump.Y) Dump.println("AxeView:getDisplaySize31 navigationbar bottomHA11="+AxeG.scrNavigationbarBottomHeightA11+",leftWA11="+AxeG.scrNavigationbarLeftWidthA11+",rightWA11="+AxeG.scrNavigationbarRightWidthA11+",swgesturemode="+AxeG.swNavigationbarGestureMode);//~vam6M~//~vc42R~
	    if (Dump.Y) Dump.println("AxeView:getDisplaySize31 point="+Ppoint.toString()+",statusBarHeight="+AxeG.scrStatusBarHeight);//~vam6M~//~vc42R~
	    if (Dump.Y) Dump.println("AxeView:getDisplaySize31 point="+Ppoint.toString()+",bounds="+bounds+",insets="+inset);//~vam6M~//~vc42R~
    }                                                              //~vam6M~//~vc42I~
    //*******************************************************      //~1aj0I~//~vc42I~
    @SuppressWarnings("deprecation")                               //~vam6I~//~vc42I~
    @TargetApi(Build.VERSION_CODES.R)   //>=30                     //~1aj0I~//~vc42I~
    public static void getDisplaySize30(Display Pdisplay,Point Ppoint)//~1aj0I~//~vc42I~
    {                                                              //~1aj0I~//~vc42I~
		DisplayMetrics m=new DisplayMetrics();                     //~1aj0I~//~vc42I~
    	Pdisplay.getRealMetrics(m);                                //~1aj0I~//~vc42I~
        Ppoint.x=m.widthPixels;                                    //~1aj0I~//~vc42I~
        Ppoint.y=m.heightPixels;                                   //~1aj0I~//~vc42I~
        getGestureSize30(Ppoint);                                  //~vc5aI~
//      Ppoint.y-=AxeG.scrNavigationbarBottomHeightA11;            //~vc42I~//~vc5aR~
        Ppoint.y-=AxeG.scrNavigationbarBottomHeightA11;            //~vc5aI~
        Ppoint.x-=AxeG.scrNavigationbarRightWidthA11;              //~vc5aI~
	    if (Dump.Y) Dump.println("AxeView:getDisplaySize30 point="+Ppoint.toString()+",navigatiobarHeight="+AxeG.scrNavigationbarBottomHeightA11);//~1aj0I~//~vc42R~//~vc5aR~
    }                                                              //~1A6pI~//~1aj0I~//~vc42I~
    //*******************************************************      //~vc5aI~
    @TargetApi(30)   //>=30                                        //~vc5aI~
    public static void getGestureSize30(Point PpointReal)          //~vc5aI~
    {                                                              //~vc5aI~
	    if (Dump.Y) Dump.println("AxeView:getGestureSize30 realSize="+PpointReal);//~vc5aR~
        WindowMetrics wm=AG.activity.getWindowManager().getCurrentWindowMetrics();//~vc5aI~
        Insets insetGesture=wm.getWindowInsets().getInsets(WindowInsets.Type.systemGestures());//~vc5aI~
        AxeG.swNavigationbarGestureMode=insetGesture.left!=0 && insetGesture.right !=0 && insetGesture.top!=0 && insetGesture.bottom!=0;//~vc5aI~
	    if (Dump.Y) Dump.println("AxeView:getGestureSize30 insetGesture="+Utils.toString(insetGesture)+",gesturemode="+AxeG.swNavigationbarGestureMode);//~vc5aR~
        AxeG.scrNavigationbarBottomHeightA11=insetGesture.bottom;  //~vc5aI~
        Rect rectDecor=getDecorViewRect();                         //~vaegI~//~vc5aI~
        int marginLR;                                              //~vc5aI~
        if (AxeG.swNavigationbarGestureMode)                       //~vc5aI~
        {                                                          //~vc5aI~
            int left=rectDecor.left;                               //~vc5aI~
            int right=PpointReal.x-rectDecor.right;                 //~vc5aI~
        	marginLR=Math.max(left,right);                         //~vc5aI~
		    if (Dump.Y) Dump.println("AxeView:getGestureSize30 gesture mode marginLR="+marginLR);//~vc5aR~
        }                                                          //~vc5aI~
        else  //3button mode                                       //~vc5aI~
        {                                                          //~vc5aI~
        	marginLR=PpointReal.x-(rectDecor.right-rectDecor.left); //~vc5aI~
			if (Dump.Y) Dump.println("AxeView:getGestureSize30 3 button mode marginLR="+marginLR);//~vc5aR~
        }                                                          //~vc5aI~
        AxeG.scrNavigationbarRightWidthA11=marginLR;               //~vc5aI~
        AxeG.scrStatusBarHeight=insetGesture.top;               //~vc5aR~
////TODO test                                                      //~vc5aR~
          WindowInsets windowInsets=wm.getWindowInsets();          //~vc5aR~
          Insets inset=windowInsets.getInsetsIgnoringVisibility    //~vc5aR~
                          (WindowInsets.Type.navigationBars()|WindowInsets.Type.displayCutout());//~vc5aR~
          if (Dump.Y) Dump.println("AxeView:getDGestureSize30 inset="+inset);//~vc5aR~
//        int insetWW=inset.right+inset.left;                      //~vc5aR~
//        int insetHH=inset.top+inset.bottom;                      //~vc5aR~
//        int ww=PpointReal.x-insetWW;                             //~vc5aR~
//        int hh=PpointReal.y-insetHH;                             //~vc5aR~
//        if (Dump.Y) Dump.println("AxeView:getGestureSize30 ww="+ww+",hh="+hh);//~vc5aR~
	    if (Dump.Y) Dump.println("AxeView:getGestureSize30 navigationbar bottomHA11="+AxeG.scrNavigationbarBottomHeightA11+",leftWA11="+AxeG.scrNavigationbarLeftWidthA11+",rightWA11="+AxeG.scrNavigationbarRightWidthA11+",swgesturemode="+AxeG.swNavigationbarGestureMode);//~vc5aI~
	    if (Dump.Y) Dump.println("AxeView:getGestureSize30 statusBarHeight="+AxeG.scrStatusBarHeight);//~vc5aI~
    }                                                              //~vc5aI~
    //*******************************************************      //~1aj0I~//~vc42I~
    //*size contains titlebar exclude statusbar and navigationbar  //~vaefI~//~vc42I~
    //*******************************************************      //~vaefI~//~vc42I~
    @SuppressWarnings("deprecation")                               //~1aj0I~//~vc42I~
//  public static void getDisplaySize29(Display Pdisplay,Point Ppoint)//~1aj0I~//~vam6R~//~vc42I~
    private static void getDisplaySize29(Display Pdisplay,Point Ppoint)//~vam6I~//~vc42I~
    {                                                              //~1aj0I~//~vc42I~
        Pdisplay.getSize(Ppoint);                                  //~1aj0I~//~vc42I~
	    if (Dump.Y) Dump.println("AxeView:getDisplaySize29 point="+Ppoint.toString());//~1aj0I~//~vc42R~
    }                                                              //~1aj0I~//~vc42I~
    //*******************************************************      //~vaegI~//~vc42I~
    public static Rect getDecorViewRect()                          //~vaegI~//~vc42I~
    {                                                              //~vaegI~//~vc42I~
        Rect rect=new Rect();                                      //~vaegI~//~vc42I~
        android.view.Window w=AG.activity.getWindow();             //~vaegI~//~vc42I~
        View v=w.getDecorView();                                   //~vaegI~//~vc42I~
        v.getWindowVisibleDisplayFrame(rect);                      //~vaegI~//~vc42I~
        if (Dump.Y) Dump.println("AxeView.getViewRect DecorView rect="+rect.toString());//~vaegI~//~vc42I~
        return rect;                                               //~vaegI~//~vc42I~
    }                                                              //~vaegI~//~vc42I~
//*************************                                        //~1606I~
	public static void getDisplaySize()                            //~1606R~
    {                                                              //~1606I~
//      Display display=((WindowManager)(AxeG.context.getSystemService(Context.WINDOW_SERVICE))).getDefaultDisplay();//~vaybR~
//      AxeG.displayW=display.getWidth();                          //~vaybR~
//      AxeG.displayH=display.getHeight();                         //~vaybR~
        Point p=getDisplayRegion();                                //~vaybI~
        AxeG.displayW=p.x;                                         //~vaybI~
        AxeG.displayH=p.y;                                         //~vaybI~
        AxeG.displayPL=((AxeG.displayH>AxeG.displayW)?AxeG.PORTRAIT:AxeG.LANDSCAPE);//~1821R~
        if (Dump.Y) Dump.println("AxeView: getDisplaySize display w="+AxeG.displayW+",h="+AxeG.displayH);//~1714R~
    }                                                              //~1606I~
//*************************                                        //~vaybI~
	public static Point getDisplayRegion()                         //~vaybI~
    {                                                              //~vaybI~
        if (Dump.Y) Dump.println("AxeView: getDisplayRegion");     //~vc5aI~
    	Point p;                                                   //~vaybI~
        if (AxeG.osVersion<AxeG.HONEYCOMB_MR2)  //<android3=api-11 required?//~vaybR~
          	p=getDisplayRegion_deprecated();                       //~vaybI~
        else                                                       //~vaybI~
          	p=getDisplayRegion_V13();                              //~vaybI~
        return p;                                                  //~vaybI~
    }                                                              //~vaybI~
//*************************                                        //~vaybI~
    @SuppressWarnings("deprecation")                               //~vaybI~
	public static Point getDisplayRegion_deprecated()                 //~vaybI~
    {                                                              //~vaybI~
        Display display=((WindowManager)(AxeG.context.getSystemService(Context.WINDOW_SERVICE))).getDefaultDisplay();//~vaybI~
        int w=display.getWidth();                                  //~vaybI~
        int h=display.getHeight();                                 //~vaybI~
        if (Dump.Y) Dump.println("AxeView: getDisplaySize_deprecated display w="+w+",h="+h);//~vaybI~
        return new Point(w,h);                                     //~vaybI~
    }                                                              //~vaybI~
//*************************                                        //~vaybI~
	@TargetApi(AxeG.HONEYCOMB_MR2)                                 //~vaybI~
	public static Point getDisplayRegion_V13()                        //~vaybI~
    {                                                              //~vaybI~
//      Display display=((WindowManager)(AxeG.context.getSystemService(Context.WINDOW_SERVICE))).getDefaultDisplay();//~vaybI~//~vc41R~
        Display display=getDefaultDisplay();                       //~vc41I~
		Point p=new Point();                                       //~vaybI~
//      display.getSize(p);                                        //~vaybI~//~vc42R~
        getDisplaySize(display,p);                                 //~vc42I~
        if (Dump.Y) Dump.println("AxeView: getDisplayRegion_V13 display w="+p.x+",h="+p.y);//~vaybI~//~vc5aR~
        return p;                                                  //~vaybI~
    }                                                              //~vaybI~
    //*******************************************************      //~1aj0I~//~vc41I~
	public static Display getDefaultDisplay()                      //~1aj0I~//~vc41I~
    {                                                              //~1aj0I~//~vc41I~
	    if (Dump.Y) Dump.println("AxeView:getDefaultDisplay");       //~1aj0I~//~vc41I~//~vc42R~
    	Display d;                                                 //~1aj0I~//~vc41I~
		if (Build.VERSION.SDK_INT>=30)   //android30(R)            //~1aj0I~//~vc41I~
			d=getDefaultDisplay30();                               //~1aj0I~//~vc41I~
        else                                                       //~1aj0I~//~vc41I~
			d=getDefaultDisplay29();                               //~1aj0I~//~vc41I~
        return d;                                                  //~1aj0I~//~vc41I~
    }                                                              //~1aj0I~//~vc41I~
    //*******************************************************      //~1aj0I~//~vc41I~
    @SuppressWarnings("deprecation")                               //~1aj0I~//~vc41I~
	public static Display getDefaultDisplay29()                    //~1aj0I~//~vc41I~
    {                                                              //~1aj0I~//~vc41I~
	    if (Dump.Y) Dump.println("AxeView:getDefaultDisplay29");     //~1aj0I~//~vc41I~//~vc42R~
//  	Display display=((WindowManager)(AxeG.context.getSystemService(Context.WINDOW_SERVICE))).getDefaultDisplay();//~1aj0I~//~vam6R~//~vc41I~//~vc42R~
    	Display display=getWindowManager().getDefaultDisplay();    //~vam6I~//~vc41I~
        return display;                                            //~1aj0I~//~vc41I~
    }                                                              //~1aj0I~//~vc41I~
    //*******************************************************      //~1aj0I~//~vc41I~
    @TargetApi(Build.VERSION_CODES.R)   //>=30                     //~1aj0I~//~vc41I~
	public static Display getDefaultDisplay30()                    //~1aj0I~//~vc41I~
    {                                                              //~1aj0I~//~vc41I~
	    if (Dump.Y) Dump.println("AxeView:getDefaultDisplay30");     //~1aj0I~//~vc41I~//~vc42R~
		Display display=AxeG.context.getDisplay();                   //~1aj0I~//~vc41R~
        return display;                                            //~1aj0I~//~vc41I~
    }                                                              //~1aj0I~//~vc41I~
    //*******************************************************      //~vam6I~//~vc41I~
	public static WindowManager getWindowManager()                 //~vam6I~//~vc41I~
    {                                                              //~vam6I~//~vc41I~
		WindowManager wm=(WindowManager)(AxeG.context.getSystemService(Context.WINDOW_SERVICE));//~vam6I~//~vc41I~
	    if (Dump.Y) Dump.println("AxeView:getWindowManager mgr="+wm);//~vam6I~//~vc41I~//~vc42R~
        return wm;                                                 //~vc41I~
    }                                                              //~vam6I~//~vc41I~
//*************************                                        //~1606I~
	public int getScreenSize()                                     //~1606I~
    {                                                              //~1606I~
        int w,h;                                                   //~1606I~
        float dip2pix=AxeG.resource.getDisplayMetrics().density;   //~1606M~
        if (Dump.Y) Dump.println("dp2pix="+dip2pix);               //~1606M~
        getDisplaySize();                                          //~1606R~
        int pl=AxeG.displayPL;                                     //~1715R~
//*calc by button height and title size                            //~1606I~
	    getTitleBarHeight();                                       //~1606M~
		int btnh=getButtonHeight();                                //~1607R~
        if (Dump.Y) Dump.println("AxeView: button height="+btnh);  //~1607R~
		w=AxeG.displayW;                                           //~1606R~
        h=AxeG.displayH-(btnh+AxeG.titleBarBottom);                              //~1606I~
        h-=AxeG.bottomSpaceHeight;                                 //~vab0I~
        if (Dump.Y) Dump.println("AxeView: getScreenSize image w="+w+",h="+h+",btnh="+btnh+",bottomspace="+AxeG.bottomSpaceHeight);//~1714R~//~vab0R~
        scrWidth[pl]=w;                                            //~1606I~
        scrHeight[pl]=h;                                           //~1606I~
        scrDip2Pix[pl]=dip2pix;                                    //~1606I~
        UView.getScreenSize();                                     //~vc1uI~
//        if (AG.portrait)                                         //~vc5aR~
//        {                                                        //~vc5aR~
//            scrHeight[AxeG.PORTRAIT]=h-AxeG.scrNavigationbarBottomHeightA11;//~vc5aR~
//            scrWidth[AxeG.PORTRAIT]=w-AxeG.scrNavigationbarRightWidthA11;//~vc5aR~
//        }                                                        //~vc5aR~
//        else                                                     //~vc5aR~
//        {                                                        //~vc5aR~
//            scrHeight[AxeG.LANDSCAPE]=h-AxeG.scrNavigationbarRightWidthA11;//~vc5aR~
//            scrWidth[AxeG.LANDSCAPE]=w-AxeG.scrNavigationbarBottomHeightA11;//~vc5aR~
//        }                                                        //~vc5aR~
//        scrHeight[pl]=h-AxeG.scrNavigationbarBottomHeightA11;    //~vc5aR~
//        scrWidth[pl] =w-AxeG.scrNavigationbarRightWidthA11;      //~vc5aR~
        if (Dump.Y) Dump.println("AxeView:getScreenSize navigatiobar BottomHeightA11="+AxeG.scrNavigationbarBottomHeightA11+",rightWidthA11="+AxeG.scrNavigationbarRightWidthA11);//~vc5aI~
        if (Dump.Y) Dump.println("AxeView:getScreenSize pl="+pl+",scrHeight="+Arrays.toString(scrHeight)+",scrWidth="+ Arrays.toString(scrWidth));//~vc5aI~
        return pl;                                                 //~1606I~
    }                                                              //~1606I~
//  public void getTitleBarHeight()                            //~1606I~//~vc5bR~
    private void getTitleBarHeight()                               //~vc5bI~
    {                                                              //~1606I~
        if (Dump.Y) Dump.println("AxeView.getTitleBarHeight entrry TitleBar top="+AxeG.titleBarTop+",bottom="+AxeG.titleBarBottom);//~vc5bI~
      if (AxeG.titleBarBottom==0)                                  //~vc5bR~
      {                                                            //~vc5bR~
        Rect rect=new Rect();                                      //~1606I~
        Window w=AxeG.activity.getWindow();                          //~1606I~
        View v=w.getDecorView();                                   //~1606I~
        v.getWindowVisibleDisplayFrame(rect);                      //~1606I~
        if (Dump.Y) Dump.println("AxeView DecorView rect="+rect.toString());//~1606I~
        v=w.findViewById(android.view.Window.ID_ANDROID_CONTENT);  //~1606I~
        AxeG.titleBarTop=rect.top;                                 //~1606I~
        AxeG.titleBarBottom=v.getTop();                            //~1606I~
      }                                                            //~vc5bI~
        if (Dump.Y) Dump.println("AxeView.getTitleBarHeight exit TitleBar top="+AxeG.titleBarTop+",bottom="+AxeG.titleBarBottom);//~1606I~//~vc5bR~
    }                                                              //~1606I~
    public int getButtonHeight()                                  //~1607I~
    {                                                              //~1607I~
    	int btnh;                                                  //~1607I~
        int btnno1;                                                //~1822I~
    //******************                                           //~1822I~
        btnno1=AxeG.axeButtonLayout.buttonNo1;                     //~1822I~
    	if (buttonHeight==0)	//initial                          //~1607I~
        {                                                          //~1607I~
	        buttonDMZHeight=((LinearLayout)AxeG.mainView.findViewById(LL_BUTTONSDMZ)).getHeight();//~1922I~
//  		btnh=llbuttons.getHeight();//0 at orientation changed  //~1607I~//~vc2lR~
    		btnh=llbuttons1.getHeight()*2;//0 at orientation changed//~vc2lI~
//      	if (AxeG.displayPL==0||!AxeButtonLayout.getUseLand())  //~1607I~//~1822R~
//          if (AxeG.displayPL==AxeG.PORTRAIT && btnno1!=0)        //~1822I~//~vc2lR~
//          {                                                      //~1822I~//~vc2lR~
        		buttonHeight2=btnh; //2line height                 //~1822I~
        		buttonHeight=(btnh+1)/2; //1line height,round down canvas height                //~1607I~
//          }                                                      //~1822I~//~vc2lR~
//          else                                                   //~1607I~//~vc2lR~
//          {                                                      //~1822I~//~vc2lR~
//      		buttonHeight2=btnh*2; //2line height               //~1822I~//~vc2lR~
//      		buttonHeight=btnh;	//1line height                 //~1607I~//~vc2lR~
//          }                                                      //~1822I~//~vc2lR~
        	if (Dump.Y) Dump.println("AxeView.getButtonHeight llbuttons.getHeight()="+btnh+",height2="+buttonHeight2);//~vc1uI~
        }                                                          //~1607I~
//      int btnno1=AxeG.axeButtonLayout.buttonNo1;                 //~1822R~
//      if ((AxeG.displayPL==0 && btnno1!=0)                       //~1822R~
//      ||  !AxeButtonLayout.getUseLand()                         //~1607I~//~1822R~
//  	)                                                          //~1822R~
        if (AxeG.displayPL==AxeG.PORTRAIT && btnno1!=0)            //~1822I~
        	btnh=buttonHeight2;                                    //~1822I~
        else                                                       //~1607R~
        	btnh=buttonHeight;                                     //~1607R~
        btnh+=buttonDMZHeight;                                     //~1922R~
        if (Dump.Y) Dump.println("AxeView.getButtonHeight rc="+btnh+",DMZ="+buttonDMZHeight+",buttonHeight="+buttonHeight+",btnNo1="+btnno1+",height2="+buttonHeight2);//~1607R~//~vc1uR~
        return btnh;
    }                                                              //~1607I~
	public static boolean isMeasured()                             //~1606I~
    {                                                              //~1606I~
        LinearLayout v=(LinearLayout)AxeG.mainView.findViewById(PARENTVIEW);//~1606I~
        if (Dump.Y) Dump.println("AxeView.isMEasured v.gwtWidth="+v.getWidth());//+vc5bI~
        return v.getWidth()!=0;		//height may be 0 yet          //~1606I~
    }                                                              //~1606I~
    //******************************************                   //~1822I~
    public static boolean requestOrientationFix(int Porientation)  //~1822I~
    {                                                              //~1822I~
    	boolean rc=true;                                               //~1822I~
       int ori=0;                                                            //~1822I~
    //****************                                             //~1822I~
        switch(Porientation)                                       //~1822I~
        {                                                          //~1822I~
        case AxeButtonLayout.LANDSCAPE:                            //~1822I~
//          ori=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;         //~vayeR~
            ori=ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;  //~vayeI~
            break;                                                 //~1822I~
        case AxeButtonLayout.PORTRAIT:                             //~1822I~
//          ori=ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;          //~vayeR~
            ori=ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;   //~vayeI~
            break;                                                 //~1822I~
        case AxeButtonLayout.ROTATE:                               //~1822I~
	        ori=ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;       //~1822I~
            break;                                                 //~1822I~
        default:                                                   //~1822I~
        	rc=false;                                              //~1822I~
        }                                                          //~1822I~
        if (Dump.Y) Dump.println("AxeView setOrientationFix="+ori);//~1822I~
        if (rc)                                                    //~1822I~
        {                                                          //~1822I~
        	AxeG.activity.setRequestedOrientation(ori);            //~1822I~
	        if (Dump.Y) Dump.println("requestedOri ori="+ori);     //~1822I~
        }                                                          //~1822I~
        return rc;                                                 //~1822I~
    }                                                              //~1822I~
    //******************************************                   //~vaiqI~
    public static void startInitializeInfo()                       //~vaiqR~
    {                                                              //~vaiqI~
        int flag=AxeAlert.BUTTON_CLOSE/*OK*/|AxeAlert.CB_ONSHOW;   //~vaiqR~
        AxeAlert.simpleAlertDialog(AxeG.main/*callback*/,0/*title*/,R.string.Info_StartInitialize,flag);//~vaiqR~
//      axedlg.pdlg.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);//~vaiqR~
//      View v=AxeG.activity.getCurrentFocus();                    //~vaiqR~
//     	AxeG.mainView.requestFocus();                              //~vaiqR~
    }                                                              //~vaiqI~
    //******************************************                   //~vaiqI~
    public static void waitInitializeWarning()                     //~vaiqI~
    {                                                              //~vaiqI~
        if (Dump.Y) Dump.println("AxeView.waitInitializeWarning"); //~vc31I~
        int flag=AxeAlert.BUTTON_CLOSE/*OK*/|AxeAlert.CB_ONSHOW|AxeAlert.CB_DISMISS;//~vaiqR~
        AxeAlert dlg=AxeAlert.simpleAlertDialog(AxeG.main/*callback*/,0/*title*/,R.string.Warning_WaitingInitialize,flag);//~vaiqR~
//      dlg.pdlg.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);//~vaiqR~
        dlg.pdlg.setCancelable(false);                             //~vaiqI~
    }                                                              //~vaiqI~
    //*******************************************************************//~vam6I~//~vc42I~
    @TargetApi(31)                                                 //~vam6I~//~vc42I~
    public static WindowMetrics getRealMetrics_from31(Display Pdisplay)//~vam6I~//~vc42I~
    {                                                              //~vam6I~//~vc42I~
		WindowManager mgr=getWindowManager();                      //~vam6I~//~vc42I~
        WindowMetrics metrics=mgr.getCurrentWindowMetrics();       //~vam6I~//~vc42I~
		if (Dump.Y) Dump.println("AxeView.getRealMetrics_31 metrics="+metrics);//~vam6R~//~vc42I~
        return metrics;                                            //~vam6I~//~vc42I~
    }                                                              //~vam6I~//~vc42I~
}                                                                  //~1528R~
