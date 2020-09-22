//*CID://+vc2PR~: update#=151;                                     //~vc2ER~//+vc2PR~
//**********************************************************************//~vaagI~
//vc2P 2020/09/07 on N7, kbdDialogHW is shown at middle of screen. //+vc2PI~
//vc2E 2020/08/21 (Bug)have to consider shift effect for ALt/Ctrl+Ascii symbol//~vc2EI~
//vc2D 2020/08/19 (Bug)kbd send not to Dialog but to AxeScreen when KbdDialogHW opened after AxeDialog Open//~vc2DI~
//vc2s 2020/08/08 (BUG)Back key did not close AxeKbd when HWkabd opedned after AxeKbd open//~vc2sI~
//vc2h 2020/07/26 ignore unicode by Alt+n except extendedKed       //~vc2hI~
//vc2e 2020/07/18 keycode d8(x5c-Yen) is reject                    //~vc2eI~
//vc2d 2020/07/18 hardKB,Alt+1 is invalid. it need modstatus and char code//~vc2dI~
//vc2c 2020/07/17 repeat delay has to consider down-a,down-b,up-a,up-b(missing up before next down)//~vc2cI~
//vc27 2020/07/11 repeatdelay for hardkbd                          //~vc27I~
//vc26 2020/07/11 mix AxeKbdDialog and AxeKbdDialogFix(apply map of AxeLstKbdLayout)//~vc26I~
//vc25 2020/07/11 no need to hard key tbl for ext key such as Fn   //~vc25I~
//vc22 2020/07/10 send kbd msg from hardkbd                        //~vc22I~
//vc1w 2020/07/06 AxeKbd updatelog extends KbdLayoutHW             //~vc1wI~
//vc1r 2020/06/26 avoid ime popup implicitly                       //~vc1rI~
//**********************************************************************//~vaagI~
//*on Zorder top accept hardKbd (dialog view is thin horizontal yellow bar)//~vc1wI~//~vc2hR~
//**********************************************************************//~vc1wI~
package com.xe.Axe.kbd;                                            //~1918R~

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;                                 //~1902I~

import com.ahsv.Alert;
import com.ahsv.utils.Utils;
import com.xe.Axe.AxeDlgKbdLayout;
import com.xe.Axe.AxeDlgKbdLayoutHW;
import com.xe.Axe.AxeKeyValue;
import com.xe.Axe.AxeLstKbdLayout;
import com.xe.Axe.AxeLstKbdLayoutHW;
import com.xe.Axe.AxeScreen;
import com.xe.Axe.AxeView;
import com.xe.Axe.Dump;
import com.xe.Axe.AxeG;
import com.xe.Axe.Gxeh;
import com.xe.Axe.R;

import static android.view.KeyEvent.*;
import static com.xe.Axe.Gxeh.*;

//~@@@@I~
//****************************************************             //~1920I~
//*KEYCODE_SHIFT_LEFT  :0x3b                                       //~vc1wI~
//*KEYCODE_SHIFT_RIGHT :0x3c                                       //~vc1wI~
//*KEYCODE_CTRL_LEFT   :0x71                                       //~vc1wR~
//*KEYCODE_CTRL_RIGHT  :0x72                                       //~vc1wI~
//*KEYCODE_ALT_LEFT    :0x39                                       //~vc1wR~
//*KEYCODE_ALT_RIGHT   :0x3a                                       //~vc1wR~
//*KEYCODE_F1          :0x83                                       //~vc1wI~
//*KEYCODE_0-9         :0x07-0x10 (7-16)                           //~vc27I~
//*KEYCODE_a-z         :0x1d-0x54 (29-54)                          //~vc27I~
//****************************************************             //~vc1wI~
public class AxeKbdDialogHW extends Dialog                           //~1918R~//~vc1rR~
//        implements AxeSoftKbd.OnSoftKbdListener,AxeTimerI          //~1A08R~//~vc1rR~
//        implements                              AxeTimerI        //~vc1rI~//~vc26R~
{                                                                  //~1918R~
    private static final int MAX_KEYCODE=256;
    private static final int DIALOG_HEIGHT=4;                      //+vc2PR~
    public static final int KEYCODE_BACKSPACE=0x08;                //~vc1rI~
    private static final int[] metaTbl=new int[]{0,                //~vc1rM~
									META_SHIFT_ON,                 //~vc1rM~
									META_META_RIGHT_ON,            //~vc1rM~
									META_SHIFT_ON+META_META_RIGHT_ON,//~vc1rM~
                                    };                             //~vc1rM~
//    public  static final String[] metaTblLabel=new String[]{"",       //~vc1rI~//~vc22R~
//                                    "Shift",                       //~vc1rI~//~vc22R~
//                                    "AltGr",                       //~vc1rI~//~vc22R~
//                                    "S+AltGr"                      //~vc1rI~//~vc22R~
//                                    };                             //~vc1rI~//~vc22R~
    public static final int METACTR=metaTbl.length;                //~vc1rI~
    public static final int MOD_CTR=metaTbl.length+1;              //~vc1wI~
	private static final int DIALOG_THEME=R.style.ExtKeyKbdThemeHW;//style for fullscreen//~1918I~//~vc1rR~
	private static final int DIALOG_LAYOUT=R.layout.dialogkbdhw;     //~1918I~//~vc1rR~
//  private static final int DIALOG_FRAMELAYOUT=R.id.KBDFRAME;     //~1918I~//~vc1rR~
    private static final int KBDVIEW=R.id.KbdHW;                   //~vc1rI~
	private ViewGroup layoutView;                                  //~1902I~
//  private FrameLayout frameLayout;                                   //~1902I~//~vc1rR~
	private View kbdView; 
	private Context context;//~1902I~
//  private AxeSoftKbd axeSoftKbd;                                 //~1919R~//~vc1rR~
    private boolean modeChange;
    private boolean isShown;//~1918I~
    private boolean isConstructor;                                 //~vaygI~
    private static int[][] defaultMap;                             //~vc1rR~
    private int modFlag;                                           //~vc1wI~
//  public int ctrMap;                                             //~vc1rR~
    private static boolean swConfigChangedNew;                     //~vc2DR~
//************************************************                 //~1918I~
    public AxeKbdDialogHW(Context Pcontext)                          //~1918R~//~vc1rR~
    {                                                              //~1902R~
     	super(Pcontext,DIALOG_THEME);//style for fullscreen       //~1918R~
        context=Pcontext;                                          //~1918I~
                                                                   //~1902I~
        Display display=((WindowManager)(context.getSystemService(Context.WINDOW_SERVICE))).getDefaultDisplay();//~1902I~
//      int displayW=display.getWidth();                           //~vaybR~
//      int displayH=display.getHeight();                          //~vaybR~
        Point dsz=AxeView.getDisplayRegion();                      //~vaybR~
        int displayW=dsz.x;                                        //~vaybI~
        int displayH=dsz.y;                                        //~vaybI~
        if (Dump.Y) Dump.println("AxeKbdDialogHW.constructor display W="+displayW+",H="+displayH);//~1902I~//~vaygR~//~vc1rR~
		layoutView=(ViewGroup)(AxeG.inflater.inflate(DIALOG_LAYOUT,null));//~1918R~
//      FrameLayout frameLayout=(FrameLayout)(layoutView.findViewById(DIALOG_FRAMELAYOUT));//~1918R~//~vaagR~
//      frameLayout=(FrameLayout)(layoutView.findViewById(DIALOG_FRAMELAYOUT));//~vaagI~//~vc1rR~
//      kbdView=initKbd();                                         //~1902I~//~vc1rR~
        kbdView=(View)(layoutView.findViewById(KBDVIEW));          //~vc1rI~
        if (Dump.Y) Dump.println("AxeKbdDialogHW.constructor kbdview W="+kbdView.getWidth()+",H="+kbdView.getHeight());//~vaybI~//~vaygR~//~vc1rR~//~vc2sR~
                                                                   //~vaybI~
//      frameLayout.addView(kbdView);                                //~1902I~//~vc1rR~
//      dialogKbd.requestWindowFeature(Window.FEATURE_NO_TITLE);   //~1902R~
//      dialogKbd.getWindow().setLayout(LayoutParams.FILL_PARENT/*width*/,Layoutparams.WRAP_CONTENT/*height*/);//~1902R~
   		isConstructor=true;                                        //~vaygI~
        setFullWidth();                                            //~1902R~
   		isConstructor=false;                                       //~vaygI~
        setContentView(layoutView);                                //~1902R~
	    setOnKeyListener(new OnKeyListener()                       //~1A08I~
        						{                                  //~1A08I~
                                @Override	                       //~1A08I~
                                public boolean onKey(DialogInterface dialog,int Pkeycode,KeyEvent Pevent)//~1A08I~
                                	{
                                    if (Dump.Y) Dump.println("AxeKbdDialogHW.onKeyListener.onKey action="+Pevent.getAction()+",Pkeycode="+Integer.toHexString(Pkeycode));//~vaygI~//~vc1rR~//~vc2hR~
//                              	return AxeKbdDialogHW.this.onKey(Pkeycode,Pevent); //~1A08I~//~vc1rR~//~vc26R~
                                		return AxeKbdDialogHW.onKey(Pkeycode,Pevent);//~vc26I~
                                	}
        						}    //~1A08I~
                        );                                         //~1A08I~
		setCancelByOutsideTouch(AxeG.cancelXekbdByOutsideTouch); //lose focus when touch outside but remains on foreground//~vaihI~
        AxeG.axeKbdDialogHW=this;                                  //~vc1rI~
        defaultMap=getDefaultMap();                                           //~vc1rR~
        setOnShow();                                               //~vc2DM~
        if (Dump.Y) Dump.println("AxeKbdDialogHW defaultMap="+ Utils.toString(defaultMap));//~vc1rI~
    }                                                              //~1902R~
//*****************************************                        //~vaihI~
	public void setCancelByOutsideTouch(boolean Pcancel)           //~vaihI~
    {                                                              //~vaihI~
        setCanceledOnTouchOutside(Pcancel);                        //~vaihI~
	    setModeless(!Pcancel);                                     //~vaiiI~
    }                                                              //~vaihI~
//*****************************************                        //~vaatR~
//*if AxeKbdDialogHW was instanciated                                //~vaygI~//~vc1rR~
//*from AxeIME<--AxeScreen:replaceBitmap<--AxeView                 //~vaygR~
//*****************************************                        //~vaatR~
    public void orientationChanged()                               //~vaatR~
    {                                                              //~vaatR~
        if (Dump.Y) Dump.println("AxeKbdDialogHW:orientationChanged");//~vaatR~//~vc1rR~
//        View v=reinitKbd();                                        //~vaatR~//~vc1rR~
//        if (v!=null)                                               //~vaatI~//~vc1rR~
//        {                                                          //~vaatI~//~vc1rR~
//            frameLayout.removeView(kbdView);                       //~vaatI~//~vc1rR~
//            frameLayout.addView(v);                                //~vaatR~//~vc1rR~
//            kbdView=v;                                             //~vaatI~//~vc1rR~
	        setFullWidth();	                                       //~vaygM~
//        }                                                          //~vaatI~//~vc1rR~
    }                                                              //~vaatR~
    public void showKbd()                                               //~1902I~
    {                                                              //~1902I~
    	if (Dump.Y) Dump.println("AxeKbdDialogHW.showKbd isShown="+isShown);      //~1919R~//~vaygR~//~vc1rR~
        if (isShown)                                               //~1919I~
        	return;                                                 //~1919I~
        setOnDismissListener((OnDismissListener) new dismissListener());//~1917I~
        show();                                                    //~1902R~
        isShown=true;                                              //~1919I~
    	if (Dump.Y) Dump.println("AxeKbdDialogHW.showKbd requestForce to AxeScreen");//~vaygI~//~vc1rR~
        AxeScreen.getFocus();  //lose kbd focus once               //~vaygI~
    }                                                              //~1902I~
//*********************************                                //~vaiiI~
    private void setModeless(boolean Pmodeless)                    //~vaiiI~
    {                                                              //~vaiiI~
    	if (Pmodeless)                                             //~vaiiI~
	        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);//~vaihI~//~vaiiI~
        else                                                       //~vaiiI~
	        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);//~vaiiI~
    }                                                              //~vaiiI~
//*********************************                                //~1A08R~
//*OnkeyDown is not called when onkey returned true;               //~vc22I~
//********************************                                 //~vc1wI~
    @Override	//Dialog.KeyEventCallback                                                      //~1A08R~//~vaygR~
    public boolean onKeyDown(int Pkeycode,KeyEvent Pevent)         //~1A08R~
    {                                                              //~1A08R~
    	boolean rc=false;                                                //~1A08R~//~vc1wR~
    //**********************                                       //~1A08R~
        if (Dump.Y) Dump.println("AxeKbdDialogHW.onkeyDown keycode="+Integer.toHexString(Pkeycode));//~1A08R~//~vc1rR~//~vc1wR~
        if (Pkeycode==AxeKeyValue.AKC_BACK)                        //~1A08R~
        {                                                          //~vc2sI~
    		AxeKbdDialog.dismissDlg();                             //~vc2sI~
	      	return false;                                          //~1A08I~//~vc2sR~
        }                                                          //~vc2sI~
        if (Dump.Y) Dump.println("AxeKbdDialogHW dialog onkeyDown rc="+rc+",modFlag="+Integer.toHexString(modFlag));//~1A08I~//~vc1rR~//~vc1wR~
        return rc;                                                 //~1A08R~
    }                                                              //~1A08R~
//********************************                                 //~vc22I~
//*OnkeyUp is not called when onkey returned true;                 //~vc22I~
//********************************                                 //~vc22I~
    @Override	//Dialog.KeyEventCallback                          //~vaygI~
    public boolean onKeyUp(int Pkeycode,KeyEvent Pevent)           //~1A08R~
    {                                                              //~1A08R~
    	boolean rc=false;                                                //~1A08I~//~vc1wR~
    //**********************                                       //~1A08R~
        if (Dump.Y) Dump.println("AxeKbdDialogHW.onKeyUp keycode="+Integer.toHexString(Pkeycode));//~1A08R~//~vaygR~//~vc1rR~
        if (Pkeycode==KeyEvent.KEYCODE_BACK)                       //~1A08R~
        {                                                          //~1A08I~
        	return false;                                          //~1A08I~
        }                                                          //~1A08I~
        if (Dump.Y) Dump.println("AxeKbdDialogHW.onKeyUp rc="+rc+",modFlag="+Integer.toHexString(modFlag));//~1A08I~//~vaygR~//~vc1rR~//~vc1wR~
        return rc;                                                 //~1A08R~
    }                                                              //~1A08R~
//*********************************                                //~vaygI~
// from onKeyListener  by setOnKeyListener                         //~vaygR~
//*********************************                                //~vaygI~
//  public boolean onKey(/*DialogInterface Pdialog,*/int Pkeycode,KeyEvent Pevent)//~1A08R~//~vc26R~
    public static boolean onKey(/*DialogInterface Pdialog,*/int Pkeycode,KeyEvent Pevent)//~vc26I~
    {                                                              //~1A08R~
    	boolean rc;                                                //~1A08I~
    //**********************                                       //~1A08R~
        if (Dump.Y) Dump.println("AxeKbdDialogHW.onKey keycode="+Integer.toHexString(Pkeycode)+",scancode="+Integer.toHexString(Pevent.getScanCode())+",action="+Pevent.getAction()+",meta="+Integer.toHexString(Pevent.getMetaState())+",unicode=0x"+Integer.toHexString(Pevent.getUnicodeChar())+",unimeta="+Integer.toHexString(Pevent.getUnicodeChar(Pevent.getMetaState())));//~vc22R~//~vc2dR~
        if (Pkeycode==KeyEvent.KEYCODE_BACK)                       //~1A08I~
        	return false;                                          //~1A08I~
        if (AxeDlgKbdLayoutHW.isOpenIMKey(Pkeycode,Pevent))        //~vc2hI~
            return true;                                           //~vc2hI~
        int rc2=onKeyOnMapHW(Pkeycode,Pevent);	//modified by cuurentMap AxeLstKbdLayoutHW//~vc26R~
        if (rc2>=0)			//1:sent to xe, 0: ignore event                           //~vc26I~//~vc27R~
        	return rc2!=0;                                         //~vc26I~
    	boolean shortcut=false;	 //find shortcutcode by stacked operation                                   //~vc1rI~//~vc2sR~
		rc=AxeG.axeKeyAction.onKeyFromDialog(Pkeycode,Pevent,shortcut);//~1A10R~
        if (Dump.Y) Dump.println("AxeKbdDialogHW.onKey rc="+rc);//~1A08I~//~vaygR~//~vc1rR~
        return rc;                                                 //~1A08R~
    }                                                              //~1A08R~
                                                                   //~1902I~
//    public int getSize() {                                         //~1902I~//~vc26R~
//        WindowManager.LayoutParams lp = getWindow().getAttributes();//~1902R~//~vc26R~
//                                                                   //~1902I~//~vc26R~
//        if (lp.gravity == Gravity.TOP || lp.gravity == Gravity.BOTTOM) {//~1902I~//~vc26R~
//            return lp.height;                                      //~1902I~//~vc26R~
//        } else {                                                   //~1902I~//~vc26R~
//            return lp.width;                                       //~1902I~//~vc26R~
//        }                                                          //~1902I~//~vc26R~
//    }                                                              //~1902I~//~vc26R~
                                                                   //~1902I~
//    public void setSize(int size) {                                //~1902I~//~vc26R~
//        WindowManager.LayoutParams lp = getWindow().getAttributes();//~1902R~//~vc26R~
//                                                                   //~1902I~//~vc26R~
//        if (lp.gravity == Gravity.TOP || lp.gravity == Gravity.BOTTOM) {//~1902I~//~vc26R~
//            lp.width = -1;                                         //~1902I~//~vc26R~
//            lp.height = size;                                      //~1902I~//~vc26R~
//        } else {                                                   //~1902I~//~vc26R~
//            lp.width = size;                                       //~1902I~//~vc26R~
//            lp.height = -1;                                        //~1902I~//~vc26R~
//        }                                                          //~1902I~//~vc26R~
//        getWindow().setAttributes(lp);                             //~1902R~//~vc26R~
//    }                                                              //~1902I~//~vc26R~
                                                                   //~1902I~
//    public void setGravity(int gravity) {                          //~1902I~//~vc26R~
//        WindowManager.LayoutParams lp = getWindow().getAttributes();//~1902R~//~vc26R~
//                                                                   //~1902I~//~vc26R~
//        boolean oldIsVertical = (lp.gravity == Gravity.TOP || lp.gravity == Gravity.BOTTOM);//~1902I~//~vc26R~
//                                                                   //~1902I~//~vc26R~
//        lp.gravity = gravity;                                      //~1902I~//~vc26R~
//                                                                   //~1902I~//~vc26R~
//        boolean newIsVertical = (lp.gravity == Gravity.TOP || lp.gravity == Gravity.BOTTOM);//~1902I~//~vc26R~
//                                                                   //~1902I~//~vc26R~
//        if (oldIsVertical != newIsVertical) {                      //~1902I~//~vc26R~
//            int tmp = lp.width;                                    //~1902I~//~vc26R~
//            lp.width = lp.height;                                  //~1902I~//~vc26R~
//            lp.height = tmp;                                       //~1902I~//~vc26R~
//            getWindow().setAttributes(lp);                         //~1902R~//~vc26R~
//        }                                                          //~1902I~//~vc26R~
//    }                                                              //~1902I~//~vc26R~
                                                                   //~1902I~
    private void setFullWidth()                                    //~1902R~
    {                                                              //~1902I~
		Window w=getWindow();                                      //~1902R~
   	  if (isConstructor)                                           //~vaygI~
        w.requestFeature(Window.FEATURE_NO_TITLE);                 //~1902M~
        if (Dump.Y) Dump.println("AxeKbdDialogHW.setFullWidth");   //~vc1rR~
      if (false)	//TODO test                                    //+vc2PR~
      {                                                            //+vc2PR~
        WindowManager.LayoutParams wlp=w.getAttributes();          //+vc2PR~
        wlp.gravity=Gravity.BOTTOM;                                //+vc2PR~
        wlp.width=WindowManager.LayoutParams.MATCH_PARENT;         //+vc2PR~
        wlp.height=4;                                              //+vc2PR~
        w.setAttributes(wlp);                                      //+vc2PR~
//      w.setGravity(Gravity.BOTTOM|Gravity.FILL_HORIZONTAL);      //+vc2PR~
//      w.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,4);        //+vc2PR~
      }                                                            //+vc2PR~
      else                                                         //+vc2PR~
      {                                                            //+vc2PR~
        w.setGravity(Gravity.BOTTOM|Gravity.FILL_HORIZONTAL);      //~1902I~
//      w.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);//~vaycM~//+vc2PR~
        float dip2pix=AxeG.resource.getDisplayMetrics().density;   //~1606M~//+vc2PI~
		int pixh=(int)(dip2pix*DIALOG_HEIGHT);                     //+vc2PI~
        if (Dump.Y) Dump.println("AxeKbdDialogHW.setFullWidth dpi2pix="+dip2pix+",height="+pixh);//+vc2PI~
        w.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,pixh);//~vc2EI~//+vc2PR~
      }                                                            //~vc2EI~
    }                                                              //~1902I~
//}//SoftInputWindow class                                         //~1902I~
                                                //~1917I~
//****************************************                                  //~1326I~//~1917I~
//*dismiss listener to start default ime                           //~1919R~
//****************************************                         //~1326I~//~1917I~
    public class dismissListener                                   //~1917I~
    		implements OnDismissListener                           //~1917I~
	{                                                              //~1917I~
        @Override                                                  //~1917I~
        public void onDismiss(DialogInterface Pdialog)             //~1917I~
        {                                                          //~1917I~
			if (Dump.Y) Dump.println("AxeKbdDialogHW.onDismiss"); //~1326I~//~1917I~//~vaygR~//~vc1rR~
        if (Dump.Y) Dump.println("AxeKbdDialogHW.onDismiss kbdview W="+kbdView.getWidth()+",H="+kbdView.getHeight());//~vaycR~//~vaygR~//~vc1rR~
//      if (Dump.Y) Dump.println("AxeKbdDialogHW.onDismiss frame W="+frameLayout.getWidth()+",H="+frameLayout.getHeight());//~vaybM~//~vaygR~//~vc1rR~
        WindowManager.LayoutParams lp = getWindow().getAttributes();//~vaycI~
        if (Dump.Y) Dump.println("AxeKbdDialogHW.onDismiss hh="+lp.height);  //~vaycR~//~vaygR~//~vc1rR~
	        AxeG.axeKbdDialogHW=null;                              //~vc1rI~
	        isShown=false;                                         //~1919I~
        }                                                          //~1917I~
    }                                                              //~1917I~
//    public void startIME()                                         //~1918I~//~vc26R~
//    {                                                              //~1918R~//~vc26R~
//        if (Dump.Y) Dump.println("AxeKbdDialogHW.startIME modeChange old="+modeChange);//~vaygR~//~vc1rR~//~vc26R~
//        modeChange=true;                                           //~1918I~//~vc26R~
////        dismiss();                                               //~1A08R~//~vc26R~
//        showKbdDelayed();                                          //~1A08I~//~vc26R~
//    }                                                              //~1918I~//~vc26R~
//    public void showKbdDelayed()                                   //~1917I~//~vc26R~
//    {                                                              //~1917I~//~vc26R~
//        if (Dump.Y) Dump.println("AxeKbdDialogHW.showKbdDelayed");          //~1917I~//~vaygR~//~vc1rR~//~vc26R~
//        new AxeTimer(this/*callback*/,100/*milisec delay*/,false/*repeat*/,null/*parameter*/).start();//~1917I~//~vc26R~
//    }                                                              //~1917I~//~vc26R~
////****************************************                       //~vc26I~
//    @Override                                                      //~1917I~//~vc26R~
//    public void onTimerExpired(AxeTimer Ptimer,int Pcallctr,Object Pparm)//~1917I~//~vc26R~
//    {                                                              //~1917I~//~vc26R~
//        if (Dump.Y) Dump.println("AxeKbdDialogHW.onTimerExpired");   //~1917I~//~vaygR~//~vc1rR~//~vc26R~
//        AxeG.axeIME.showKbdP();                                    //~1918I~//~vc26R~
//    }                                                              //~1917I~//~vc26R~
//***********************************************                  //~vc26I~
    public static int[][] getDefaultMap()                          //~vc1rR~
    {                                                              //~vc1rI~
    	int unicode,keycode,meta;                                  //~vc1rR~
    //*********************                                        //~vc1rI~
    	if (Dump.Y) Dump.println("AxeKbdDialogHW.getDefaultMap");  //~vc1rI~
    	if (defaultMap!=null)                                      //~vc1rI~
        	return defaultMap;                                     //~vc1rI~
        KeyCharacterMap kmap=KeyCharacterMap.load(KeyCharacterMap.VIRTUAL_KEYBOARD);//~vc1rR~
//      int[][] mapTbl=new int[MAX_KEYCODE][metaTbl.length];       //~vc1rR~//~vc1wR~
        int[][] mapTbl=new int[MAX_KEYCODE][MOD_CTR];              //~vc1wI~
//      int ctrMap=0;                                              //~vc1rR~//~vc22R~
        for (int ii=0;ii<MAX_KEYCODE;ii++)                         //~vc1rI~
        {                                                          //~vc1rI~
        	if (ii==KEYCODE_META_RIGHT||ii==KEYCODE_META_LEFT      //~vc1rI~
        	||  ii==KEYCODE_SHIFT_RIGHT||ii==KEYCODE_SHIFT_LEFT)   //~vc1rI~
            	continue;                                          //~vc1rI~
        	if (ii>=KEYCODE_NUMPAD_0 && ii<=KEYCODE_NUMPAD_RIGHT_PAREN)	//unicode=0xa, duplicated with ENTER//~vc1rR~
            	continue;                                          //~vc1rI~
        	unicode=kmap.get(ii/*keycode*/,0/*metastate*/);        //~vc1rI~
        	if (Dump.Y) Dump.println("AxeKbdDialogHW.getDefaultMap label keycode="+ii+",unicode=0x"+Integer.toHexString(unicode)+"="+ (Character.toChars(unicode))[0]);//~vc1rR~
        	if (Dump.Y) Dump.println("AxeKbdDialogHW.getDefaultMap label keycode="+ii+"+Alt.unicode=0x"+Integer.toHexString(kmap.get(ii,KeyEvent.META_ALT_LEFT_ON)));//TODO test//~vc27I~
	       	if (Dump.Y) Dump.println("AxeKbdDialogHW.getDefaultMap label keycode="+ii+"+Ctl.unicode=0x"+Integer.toHexString(kmap.get(ii,KeyEvent.META_CTRL_LEFT_ON)));//TODO test//~vc27R~
            if (unicode==0)                                        //~vc1rI~
            {                                                      //~vc1rI~
            	if (ii==KEYCODE_DEL) //backspace                    //~vc1rI~
            		unicode=KEYCODE_BACKSPACE;                     //~vc1rI~
            	else                                               //~vc1rI~
                {                                                  //~vc22I~
//                    int idx=AxeKbdKey.findSpinnerKeyCode(ii);       //~vc22I~//~vc25R~
//                  if (idx>0)                                       //~vc22I~//~vc25R~
//                    unicode=idx| AxeLstKbdLayoutHW.HWKEYID_GDK;  //0x20000000//~vc22I~//~vc25R~
//                  else                                             //~vc22I~//~vc25R~
            		continue;                                      //~vc1rR~
                }                                                  //~vc22I~
            }                                                      //~vc1rI~
            mapTbl[ii][0]=unicode;                                 //~vc1rI~
//          ctrMap++;                                              //~vc1rI~//~vc22R~
//          for (int jj=1;jj<metaTbl.length;jj++)                  //~vc1rR~//~vc1wR~
            for (int jj=1;jj<METACTR;jj++)   //fill sift[1],AltGr[2],S+AltGr[3] leaving [4] for Symbol of AxeKbd//~vc1wI~
            {                                                      //~vc1rI~
            	meta=metaTbl[jj];                                  //~vc1rI~
                unicode=kmap.get(ii/*keycode*/,meta/*metastate*/); //~vc1rR~
                mapTbl[ii][jj]=unicode;                            //~vc1rR~
                if (Dump.Y) Dump.println("AxeKbdDialogHW.getDefaultMap keycode="+ii+",meta=0x="+Integer.toHexString(meta)+",unicode=0x"+Integer.toHexString(unicode)+"="+ (unicode!=0 ? (Character.toChars(unicode))[0] : ""));//~vc1rR~//~vc27R~
            }                                                      //~vc1rI~
        }
        return mapTbl;//~vc1rI~
    }                                                              //~vc1rI~
//**********************************                               //~vc2dI~
	public static int keycodeToCode(int Pkeycode)         //~vc2dR~//~vc2eR~
    {                                                              //~vc2dI~
    	int rc=0;                                                  //~vc2dR~
        getDefaultMap();                                           //~vc2eR~
        if (Pkeycode<defaultMap.length)                        //~vc2dR~//~vc2eR~
        {                                                          //~vc2eI~
            rc=defaultMap[Pkeycode][0];                        //~vc2dR~//~vc2eR~
        	if (rc==0)                                             //~vc2eI~
    			if (Pkeycode==KEYCODE_YEN)                                      //~vc2dI~//~vc2eI~
	            	rc=defaultMap[KEYCODE_BACKSLASH][0];           //~vc2eI~
        }                                                          //~vc2eI~
		if (Dump.Y) Dump.println("AxeKbdDialogHW.keycodeToCode keycode=x"+Integer.toHexString(Pkeycode)+",rc="+Integer.toHexString(rc));//~vc2dR~//~vc2eR~
        return rc;                                                 //~vc2dI~
    }                                                              //~vc2dI~
//**********************************                               //~vc2EI~
	public static int keycodeToCode(int Pkeycode,int Pmeta)        //~vc2ER~
    {                                                              //~vc2EI~
		if (Dump.Y) Dump.println("AxeKbdDialogHW.keycodeToCode with meta keycode=x"+Integer.toHexString(Pkeycode)+",meta=x"+Integer.toHexString(Pmeta));//~vc2EI~
		int rc=keycodeToCode(Pkeycode);                            //~vc2EI~
        if (rc==0)                                                 //~vc2ER~
        	return rc;                                             //~vc2EI~
        if (rc>='a' && rc<'z')                                    //~vc2EI~
        {                                                          //~vc2EI~
			if (((Pmeta & META_CAPS_LOCK_ON)!=0) ^ ((Pmeta & META_SHIFT_ON)!=0))//~vc2ER~
            	rc=rc-('a'-'A');                                   //~vc2EI~
			if (Dump.Y) Dump.println("AxeKbdDialogHW.keycodeToCode with meta a-z rc=x"+Integer.toHexString(rc));//~vc2EI~
        	return rc;                                             //~vc2EI~
        }                                                          //~vc2EI~
    	if ((Pmeta & META_SHIFT_ON)==0)                            //~vc2ER~
			return rc;                                             //~vc2EI~
        int[][] cmap= AxeLstKbdLayoutHW.getCurrentMap();           //~vc2EI~
        if (Pkeycode<cmap.length)                                  //~vc2EI~
        {                                                          //~vc2EI~
            int rc2=cmap[Pkeycode][1];                             //~vc2EI~
        	if (rc2==0)                                            //~vc2EI~
    			if (Pkeycode==KEYCODE_YEN)                         //~vc2EI~
	            	rc2=defaultMap[KEYCODE_BACKSLASH][1];          //~vc2EI~
            if (rc2!=0)                                            //~vc2EI~
            	rc=rc2;                                            //~vc2EI~
        }                                                          //~vc2EI~
		if (Dump.Y) Dump.println("AxeKbdDialogHW.keycodeToCode with meta rc=x"+Integer.toHexString(rc));//~vc2EI~
        return rc;                                                 //~vc2EI~
    }                                                              //~vc2EI~
////**********************************                             //~vc2ER~
//    public static boolean isShiftedHW(int Pmeta)                 //~vc2ER~
//    {                                                            //~vc2ER~
//        boolean rc;                                              //~vc2ER~
////        if ((Pmeta & META_CAPS_LOCK_ON)==0)  //Caps applied to a-z only//~vc2ER~
//            rc=(Pmeta & META_SHIFT_ON)!=0;                       //~vc2ER~
////        else                                                   //~vc2ER~
////            rc=(Pmeta & META_SHIFT_ON)==0;                     //~vc2ER~
//        if (Dump.Y) Dump.println("AxeKbdDialogHW.isShiftedHW meta=x"+Integer.toHexString(Pmeta)+",rc="+rc);//~vc2ER~
//        return rc;                                               //~vc2ER~
//    }                                                            //~vc2ER~
//**********************************                               //~vc26I~
//*from Axe;configuration changed                                  //~vc26I~
//*return dialog is opening                                        //~vc26I~
//**********************************                               //~vc26I~
    public static void configChanged(boolean PswChanged)           //~vc26I~
    {                                                              //~vc26I~
	    if (Dump.Y) Dump.println("AxeKbdDialogHW.configChanged swChanged="+PswChanged);  //~vc26I~//~vc2DR~
//  	if (AxeDlgKbdLayoutHW.isShowingDlg())                         //~vc26I~//~vc2DR~
//  		AxeG.axeDlgKbdLayoutHW.configChanged(PswChanged);      //~vc26I~//~vc2DR~
	    if (isShowingDlg())                                        //~vc2DI~
		    configChangedForDialog(PswChanged);                    //~vc2DI~
        else                                                       //~vc2DI~
        	if (PswChanged)                                        //~vc2DI~
	            swConfigChangedNew=true; //request to dialog at onShow//~vc2DR~
        AxeScreen.configChanged(); 	//open AxeKbdDialogHW          //~vc26I~
    }                                                              //~vc26I~
    private static void configChangedForDialog(boolean PswChanged) //~vc2DI~
    {                                                              //~vc2DI~
		if (Dump.Y) Dump.println("AxeKbdDialogHW.configChangedForDialog swConfigChangedNew="+swConfigChangedNew);//~vc2DI~
        swConfigChangedNew=false;                                  //~vc2DI~
    	if (AxeDlgKbdLayout.isShowingDlg())                        //~vc2DM~
    		AxeG.axeDlgKbdLayout.configChanged(PswChanged);        //~vc2DM~
        else                                                       //~vc2DI~
    	if (AxeDlgKbdLayoutHW.isShowingDlg())                      //~vc2DI~
    		AxeG.axeDlgKbdLayoutHW.configChanged(PswChanged);      //~vc2DI~
    }                                                              //~vc2DI~
    private static void configChangedForDialogReopen()             //~vc2DI~
    {                                                              //~vc2DI~
		if (Dump.Y) Dump.println("AxeKbdDialogHW.configChangedForDialogReopen swConfigChangedNew="+swConfigChangedNew);//~vc2DI~
        swConfigChangedNew=false;                                  //~vc2DI~
//        if (AxeDlgKbdLayout.isShowingDlg())                      //~vc2DR~
//            AxeG.axeDlgKbdLayout.configChangedReopen();          //~vc2DR~
//        else                                                     //~vc2DR~
//        if (AxeDlgKbdLayoutHW.isShowingDlg())                    //~vc2DR~
//            AxeG.axeDlgKbdLayoutHW.configChangedReopen();        //~vc2DR~
		if (AxeG.isHardKeyboardActive())                            //~vc2DI~
//      	if (AxeDlgKbdLayout.isShowingDlg()                     //~vc2DR~
//      	||  AxeDlgKbdLayoutHW.isShowingDlg())                  //~vc2DR~
        	if (AxeG.axeDialog!=null && AxeG.axeDialog.isShowing())//~vc2DI~
            	Alert.showMessage(R.string.Title_Warning,R.string.Warning_ConfigChangedKbdHW);//~vc2DR~
    }                                                              //~vc2DI~
//**********************************                               //~vc26I~
    public static boolean isShowingDlg()                           //~vc26I~
    {                                                              //~vc26I~
    	boolean rc=AxeG.axeKbdDialogHW!=null &&  AxeG.axeKbdDialogHW.isShowing();//~vc26I~
		if (Dump.Y) Dump.println("AxeKbdDialogHW.isShowing rc="+rc);//~vc26I~
        return rc;                                                 //~vc26I~
    }                                                              //~vc26I~
//***********************************************************************************//~vc27I~
//*rc=1: sent after traslatted, 0:ignore by repeat delay, -1:no translation occured(goto keyProc)//~vc27I~//~vc2eR~
//***********************************************************************************//~vc27I~
    public static int onKeyOnMapHW(int Pkeycode,KeyEvent Pevent)   //~vc26R~
    {                                                              //~vc26I~
        if (Dump.Y) Dump.println("AxeKbdDialogHW.onKeyOnMapHW keycode="+Integer.toHexString(Pkeycode)+",action="+Pevent.getAction()+",meta="+Integer.toHexString(Pevent.getMetaState())+",unicode=0x"+Integer.toHexString(Pevent.getUnicodeChar())+",unimeta="+Integer.toHexString(Pevent.getUnicodeChar(Pevent.getMetaState())));//~vc26I~//~vc27R~//~vc2eR~
        int action=Pevent.getAction();                             //~vc26M~
        int rc;                                                    //~vc26I~
        int unicode=Pevent.getUnicodeChar();                       //~vc27I~
//      if (unicode!=0 && repeatDelay(action))                     //~vc27R~//~vc2cR~
//      if (unicode!=0 && repeatDelay(action,Pkeycode))            //~vc2cI~//~vc2eR~
        if (repeatDelay(action,Pkeycode)) //ognore also modifierkey pressing continue//~vc2eI~
        {                                                          //~vc27I~
            rc=0;   //ignore this event                            //~vc27I~
        }                                                          //~vc27I~
        else                                                       //~vc27I~
        {                                                          //~vc27I~
            int meta=Pevent.getMetaState();                            //~vc26I~//~vc27I~
//          int unicode=Pevent.getUnicodeChar();                       //~vc26I~//~vc27R~
            int keycode=AxeLstKbdLayout.chkHWLayout(Pkeycode,meta,unicode);//~vc26R~//~vc27I~//~vc2eR~
			if (Dump.Y) Dump.println("AxeKbdDialogHW.onKeyOnMapHW chkHWLayout rc="+Integer.toHexString(keycode));//~vc2eR~
            boolean swAltChar=false;                               //~vc2hI~
            if (keycode==0)	//no AltGr translation	               //~vc2eI~
            {                                                      //~vc2eI~
            	if (unicode!=0)                                    //~vc2eI~
                {                                                  //~vc2hI~
                  if (!isAcceptAltUnicode(meta))                   //~vc2hI~
                  {                                                //~vc2hI~
					keycode=complementCharCode(Pevent,Pkeycode);	//+Alt,+Ctrl charcode//~vc2hI~
                  	swAltChar=true;                                //~vc2hI~
                  }                                                //~vc2hI~
                  else                                             //~vc2hI~
                  {                                                //~vc2EI~
                	keycode=unicode;                              //~vc2eI~
                    if ((keycode>='a' && keycode <='z') || (keycode>='A' && keycode<='Z'))	//Caps and Shift effect//~vc2EI~
                    {                                              //~vc2EI~
						int uni0code=complementCharCode(Pevent,Pkeycode);//~vc2EI~
                        if (uni0code!=0)                           //~vc2EI~
                        	keycode=uni0code;                      //~vc2EI~
						if (Dump.Y) Dump.println("AxeKbdDialogHW.onKeyOnMapHW unicode="+Integer.toHexString(unicode)+",keycode="+Integer.toHexString(keycode));//~vc2EI~
                    }                                              //~vc2EI~
                    	                                           //~vc2EI~
                  }                                                //~vc2EI~
                }                                                  //~vc2hI~
                else                                               //~vc2eI~
                {                                                  //~vc2eI~
					int uni0code=complementCharCode(Pevent,Pkeycode);	//+Alt,+Ctrl charcode//~vc2eR~
            		if (uni0code!=0)                               //~vc2eR~
                    {                                              //~vc2eI~
                    	if (!AxeKeyValue.isExtendedKey(Pkeycode))	//TAb,Backspace,Enter has unicoed value//~vc2eI~
	                		keycode=uni0code;                      //~vc2eR~
                    }                                              //~vc2eI~
                }                                                  //~vc2eI~
            }                                                      //~vc2eI~
            if (swAltChar)                                         //~vc2hI~
            {                                                      //~vc2hI~
            	AxeG.axeKeyAction.onKeyFromDialogHW(action,Pkeycode,Pevent,keycode/*Mod & base unicode*/);//~vc2hR~
                rc=1;       //return from onKey by true            //~vc2hI~
            }                                                      //~vc2hI~
            else                                                   //~vc2hI~
//          if (keycode<=0)                                            //~vc26I~//~vc27R~//~vc2eR~
            if (keycode==0)                                        //~vc2eI~
            {                                                      //~vc27I~
//            if (keycode==-1)                                     //~vc27I~//~vc2eR~
//            {                                                    //~vc27I~//~vc2eR~
//          	AxeG.axeKeyAction.onKeyFromDialogHW(action,Pkeycode,Pevent,0);//~vc27I~//~vc2eR~
//              rc=1;       //return from onKey by true            //~vc27I~//~vc2eR~
//            }                                                    //~vc27I~//~vc2eR~
//            else                                                 //~vc27I~//~vc2eR~
                rc=-1;  //no code translation                          //~vc26I~//~vc27R~
            }                                                      //~vc27I~
            else                                                       //~vc26I~//~vc27R~
            if (keycode==unicode)                                  //~vc2eI~
            	rc=-1;	//no translation                           //~vc2eI~
            else                                                   //~vc2eI~
            {                                                          //~vc26I~//~vc27R~
            	AxeG.axeKeyAction.onKeyFromDialogHW(action,Pkeycode,Pevent,keycode/*gdk or unicode*/);//~1A10R~//~vc26R~//~vc27R~
                rc=1;       //return from onKey by true                //~vc26R~//~vc27R~
            }                                                          //~vc26I~//~vc27R~
        }                                                          //~vc27I~
		if (Dump.Y) Dump.println("AxeKbdDialogHW.onKeyOnMapHW rc="+rc);//~vc26I~
        return rc;                                                 //~vc26I~
    }                                                              //~vc26I~
//***********************************************************************************//~vc2hI~
    private static boolean isAcceptAltUnicode(int Pmeta)           //~vc2hI~
    {                                                              //~vc2hI~
    	boolean rc=true;                                           //~vc2hI~
        if ((Gxeh.axeStatus & AXES_ALTG_RIGHTALT)!=0)	//ALTGR by ALT_RIGHT//~vc2hI~
        {                                                          //~vc2hI~
        	if ((Pmeta & AxeKeyValue.META_ALT_L)!=0)	//Alt Left is for func key//~vc2hI~
            	rc=false;                                          //~vc2hI~
        }                                                          //~vc2hI~
        else                                                       //~vc2hI~
        if ((Gxeh.axeStatus & AXES_ALTG_LEFTALT)!=0)	//ALTGR by ALT_LEFT//~vc2hI~
        {                                                          //~vc2hI~
        	if ((Pmeta & AxeKeyValue.META_ALT_R)!=0)	//Alt Right is for func key//~vc2hR~
            	rc=false;                                          //~vc2hI~
        }                                                          //~vc2hI~
        else                                                       //~vc2hI~
        if ((Gxeh.axeStatus & AXES_ALTG_RIGHTSHIFT)!=0)	//ALTGR by SHIFT_RIGHT//~vc2hI~
        {                                                          //~vc2hI~
        	if ((Pmeta & AxeKeyValue.META_ALT)!=0)	//Both Alt is for func key//~vc2hI~
            	rc=false;                                          //~vc2hI~
        }                                                          //~vc2hI~
        else	//no ALTGR                                         //~vc2hR~
        {                                                          //~vc2hI~
        	if ((Pmeta & AxeKeyValue.META_ALT_L)!=0)	//Alt Left is for func key//~vc2hI~
            	rc=false;                                          //~vc2hI~
        }                                                          //~vc2hI~
		if (Dump.Y) Dump.println("AxeKbdDialogHW.isAcceptAltUnicode rc="+rc+",meta="+Integer.toHexString(Pmeta)+",axeStatus="+Integer.toHexString(Gxeh.axeStatus));//~vc2hI~
        return rc;                                                 //~vc2hI~
    }                                                              //~vc2hI~
//***********************************************************************************//~vc2eI~
    static int ctrContinuing;
    static int prevKey;                                            //~vc2cI~
//  private static boolean repeatDelay(int Paction)                //~vc27I~//~vc2cR~
    private static boolean repeatDelay(int Paction,int PkeyCode)   //~vc2cI~
    {                                                              //~vc27I~
        boolean rc=false;                                          //~vc27R~
        int elapsed;                                               //~vc27I~
        //**************************                               //~vc27I~
        if (Paction==KeyEvent.ACTION_DOWN)                           //~va15I~//~vc27I~
        {                                                          //~vc27I~
        	if (PkeyCode!=prevKey)	//continued down               //~vc2cI~
            	ctrContinuing=0;                                   //~vc2cR~
            switch(ctrContinuing)                                  //~vc27I~
            {                                                      //~vc27I~
            case 0:                                                //~vc27R~
	        	ctrContinuing++;                                   //~vc27I~
            	Utils.setTimeStamp(0/*timerID*/);	//save starttime//~vc27I~
            	break;                                             //~vc27I~
            case 1:                                                //~vc27R~
            	elapsed=Utils.getElapsedTimeMillis(0/*timerID*/);  //~vc27I~
				if (Dump.Y) Dump.println("AxeKbdDialogHW.repeatDelay 2nd elapsed="+elapsed+",repeatDelay="+AxeG.repeatDelay);//~vc27I~
                if (elapsed<AxeG.repeatDelay)                      //~vc27I~
                	rc=true;                                       //~vc27I~
                else                                               //~vc27I~
                {                                                  //~vc27I~
	        		ctrContinuing++;                               //~vc27I~
	            	Utils.setTimeStamp(0/*timerID*/);	//save starttime//~vc27I~
                }                                                  //~vc27I~
            	break;                                             //~vc27I~
            default: //frm 3rd time                                //~vc27I~
            	elapsed=Utils.getElapsedTimeMillis(0/*timerID*/);  //~vc27I~
				if (Dump.Y) Dump.println("AxeKbdDialogHW.repeatDelay 3rd elapsed="+elapsed+",repeatSpeed="+AxeG.repeatSpeed);//~vc27I~
                if (elapsed<AxeG.repeatSpeed)                      //~vc27I~
                	rc=true;                                       //~vc27I~
                else                                               //~vc27I~
	            	Utils.setTimeStamp(0/*timerID*/);	//save starttime//~vc27I~
            }                                                      //~vc27I~
        }                                                          //~vc27I~
        else                                                       //~vc27I~
        {                                                          //~vc27I~
        	ctrContinuing=0;                                       //~vc27I~
        }                                                          //~vc27I~
		if (Dump.Y) Dump.println("AxeKbdDialogHW.repeatDelay rc="+rc+",action="+Paction+",ctr="+ctrContinuing+",Pkeycode="+PkeyCode+",prevKeycode="+prevKey);//~vc27R~//~vc2cR~
        prevKey=PkeyCode;                                          //~vc2cI~
        return rc;
    }                                                              //~vc27I~
    //******************************************************           //~vc2dI~//~vc2eR~
    //* unicode=0 case,set unicode if avail                            //~vc2dI~//~vc2eR~
    //******************************************************           //~vc2dI~//~vc2eR~
    private static int complementCharCode(KeyEvent Pevent,int Pkeycode) //~vc2dR~//~vc2eR~
    {                                                                  //~vc2dI~//~vc2eR~
        int rc=0;                                                   //~vc2dI~//~vc2eR~
    ////      if ((Pmeta & AxeKeyValue.META_ALT_L)!=0 && Punicode==0)    //~vc29R~//~vc2dI~//~vc2eR~
    //        if ((Pmeta & AxeKeyValue.META_ALT_L)!=0)                   //~vc29I~//~vc2dI~//~vc2eR~
    //        {                                                          //~vc29R~//~vc2dI~//~vc2eR~
    //            if (Dump.Y) Dump.println("AxeKbdDialogHW.getKeyValue ALT_LEFT ON keycode="+Integer.toHexString(Pkeycode)+",unicode="+Integer.toHexString(Punicode));//~vc29R~//~vc2dI~//~vc2eR~
    //            if (Pkeycode==KEYCODE_ALT_LEFT)                        //~vc29R~//~vc2dI~//~vc2eR~
    //                return 0;                                          //~vc29R~//~vc2dI~//~vc2eR~
    //            if (AxeKeyValue.isExtendedKey(Pkeycode))               //~vc29I~//~vc2dI~//~vc2eR~
    //                return 0;   //no translation                       //~vc29I~//~vc2dI~//~vc2eR~
    //            return -1;                                             //~vc29R~//~vc2dI~//~vc2eR~
    //        }                                                          //~vc29R~//~vc2dI~//~vc2eR~
    //        else                                                       //~vc29R~//~vc2dI~//~vc2eR~
    //        if ((Pmeta & AxeKeyValue.META_CTL)!=0)                //~vc29I~//~vc2dI~//~vc2eR~
    //        {                                                          //~vc29I~//~vc2dI~//~vc2eR~
    //            if (Dump.Y) Dump.println("AxeKbdDialogHW.getKeyValue CTRL ON keycode="+Integer.toHexString(Pkeycode)+",unicode="+Integer.toHexString(Punicode));//~vc29I~//~vc2dI~//~vc2eR~
    //            if (Pkeycode==KEYCODE_CTRL_LEFT||Pkeycode==KEYCODE_CTRL_RIGHT)//~vc29I~//~vc2dI~//~vc2eR~
    //                return 0;                                          //~vc29I~//~vc2dI~//~vc2eR~
    //            if (AxeKeyValue.isExtendedKey(Pkeycode))               //~vc29I~//~vc2dI~//~vc2eR~
    //                return 0;   //no translation                       //~vc29I~//~vc2dI~//~vc2eR~
    //            return -1;                                             //~vc29I~//~vc2dI~//~vc2eR~
    //        }                                                          //~vc29I~//~vc2dI~//~vc2eR~
    //        else                                                       //~vc29I~//~vc2dI~//~vc2eR~
        int meta=Pevent.getMetaState();                                //~vc2dR~//~vc2eR~
        int mod=AxeKeyValue.metaToMod(meta);                           //~vc2dR~//~vc2eR~
        int val=0;                                                     //~vc2dI~//~vc2eR~
        if ((meta & AxeKeyValue.META_ALT_L)!=0                        //~vc2dI~//~vc2eR~
        ||  (meta & AxeKeyValue.META_CTL)!=0)                        //~vc2dI~//~vc2eR~
//          val=AxeKbdDialogHW.keycodeToCode(Pkeycode);                 //~vc2dI~//~vc2eR~//~vc2ER~
            val=AxeKbdDialogHW.keycodeToCode(Pkeycode,meta);       //~vc2EI~
        else                                                           //~vc2dI~//~vc2eR~
    //  if (mod==0)                                                    //~vc2dR~//~vc2eR~
            val=AxeKbdDialogHW.keycodeToCode(Pkeycode,meta);                 //~vc2dI~//~vc2eR~//~vc2ER~
        if (val!=0)                                                    //~vc2dR~//~vc2eR~
            rc=val | mod;                                              //~vc2dR~//~vc2eR~
        if (Dump.Y) Dump.println("AxeKeyAction.complementCharCode Pekycode=0x"+Integer.toHexString(Pkeycode)+",meta=0x"+Integer.toHexString(meta)+",rc="+Integer.toHexString(rc));//~vc2dR~//~vc2eR~
        return rc;                                                     //~vc2dR~//~vc2eR~
    }                                                                  //~vc2dI~//~vc2eR~
//*********                                                        //~vc2DI~
	@Override                                                      //~vc2DI~
	public void onWindowFocusChanged(boolean PhasFocus)            //~vc2DI~
    {                                                              //~vc2DI~
		if (Dump.Y) Dump.println("AxeKbdDialogHW.onwindowFocusChanged:"+PhasFocus+","+this.toString());//~vc2DI~
//      if (PhasFocus)                                             //~vc2DR~
//      	if (swConfigChangedNew)                                //~vc2DR~
//  		    configChangedForDialog(true/*PswChanged*/);        //~vc2DR~
    }                                                              //~vc2DI~
//**********************************************************************//~vc2DI~
	private void setOnShow()                                       //~vc2DI~
    {                                                              //~vc2DI~
        setOnShowListener(new DialogInterface.OnShowListener()     //~vc2DI~
                                   {                               //~vc2DI~
            					   		@Override                  //~vc2DI~
                                        public void onShow(DialogInterface arg0)//~vc2DI~
                                        {                          //~vc2DI~
                                        	try                    //~vc2DI~
                                            {                      //~vc2DI~
                                        		if (Dump.Y) Dump.println("AxeKbdDialogHW.onShow");//~vc2DI~
                                                onShowDlg();       //~vc2DI~
                                            }                      //~vc2DI~
                                            catch(Exception e)     //~vc2DI~
                                            {                      //~vc2DI~
                                            	Dump.println(e,"AxeKbdDialogHW.onShow");//~vc2DI~
                                            }                      //~vc2DI~
                                        }                          //~vc2DI~
                                   }                               //~vc2DI~
                              );                                   //~vc2DI~
	}                                                              //~vc2DI~
    private void onShowDlg()                                            //~vc2DI~
    {                                                              //~vc2DI~
    	if (swConfigChangedNew)                                    //~vc2DR~
    	    configChangedForDialogReopen();                        //~vc2DR~
    }                                                              //~vc2DI~
}
