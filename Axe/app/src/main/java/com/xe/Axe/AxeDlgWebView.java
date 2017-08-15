//*CID://+vb12R~: update#= 28;                                     //~vb12R~
//**********************************************************************
//vb12 2017/02/28 WebView:PictureListener was deprecated API-12(Android3.1.x honeycomb MR1)//~vb12I~
//vaxg:140707 (Axe)WebView dose not support frameset,changed to iframe(pagedown is not effective)//~vaxgI~
//vagF:120920 (Axe)local html viewer fail by permission err(uid of process of HtmlViewer was checked)
//**********************************************************************
package com.xe.Axe;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.graphics.Picture;
public class AxeDlgWebView extends AxeDialog                       //~vagFR~
{
/////////////////////////////////////////////////////////////////////////////
//**********************************************************************
	private static final int LAYOUT=R.layout.dialogwebview;        //~vagFR~

    private final static int btnidBack=R.id.HtmlBack;                           //~vagFI~
    private final static int btnidFwd=R.id.HtmlForward;                         //~vagFI~
    private final static int btnidZin=R.id.HtmlZoomIn;                          //~vagFI~
    private final static int btnidZout=R.id.HtmlZoomOut;                        //~vagFI~
//    private final static int btnidTop=R.id.HtmlTop;                //~vagFI~
//    private final static int btnidBottom=R.id.HtmlBottom;          //~vagFI~
    private WebView  webView;                                      //~vagFM~
    private static String topHtml;
//    private int Soldposy,Stryctr;//~vagFI~                       //~vb12R~
//**********************************
	public AxeDlgWebView()                                         //~vagFR~
    {
    	super(LAYOUT);
    }
//**********************************
    public static AxeDlgWebView showHelp(String Purl)            //~vagFR~
    {
    	topHtml=Purl;                                              //~vagFI~
 //       topHtml="http://www.geocities.jp/sakachin2"; //xehelp/indexe.htm";//~vagFR~
//      topHtml="https://www.google.co.jp";                        //~vagFR~
    	AxeDlgWebView dlg=new AxeDlgWebView();                   //~vagFR~
		dlg.showDialog(AxeDialog.NO_TITLE);                        //~vagFR~
        return dlg;
    }
//*********
	@Override
//*loadUrl before setContentView to scroll up to botom of data     //~vagFI~
	protected void setupDialogExtendPre(ViewGroup PlayoutView)     //~vagFR~
    {
    	//********************
	    webView=(WebView)layoutView.findViewById(R.id.Webview);    //~vagFR~
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); //~vagFM~
        WebSettings ws=webView.getSettings();                           //~vagFI~
        ws.setSupportZoom(true);      //default true               //~vagFI~
        ws.setBuiltInZoomControls(true);                           //~vagFM~
        ws.setLoadWithOverviewMode(true);  //100% zoom out         //~vagFR~
        ws.setUseWideViewPort(true);    //view over screen pix     //~vagFR~
        ws.setAllowFileAccess(true);                               //~vagFI~
        webView.setWebViewClient(new WebViewClient());                          //~vagFI~
        webView.loadUrl(topHtml);                                  //~vagFI~
    }                                                              //~vagFR~
//*********                                                        //~vagFI~
	@Override                                                      //~vagFI~
	protected void setupDialogExtend(ViewGroup PlayoutView)        //~vagFI~
    {                                                              //~vagFI~
    	//********************                                     //~vagFI~
        WindowManager.LayoutParams lp=getWindow().getAttributes(); //~vagFI~
        int ww=(int)(AxeG.displayW*0.99);                          //~vagFI~
        int hh=(int)((AxeG.displayH-AxeG.bottomSpaceHeight)*0.99); //~vagFI~
        if (Dump.Y) Dump.println("helpDlg ww="+ww+",hh="+hh);      //~vagFI~
        lp.width=ww;                                               //~vagFI~
        lp.height=hh;                                              //~vagFI~
        getWindow().setAttributes(lp);                             //~vagFI~
//        webView.setPictureListener(new WebView.PictureListener()   //~vagFI~//~vb12R~
//        {                                                          //~vagFI~//~vb12R~
//            @Override                                              //~vagFI~//~vb12R~
//            public void onNewPicture(WebView Pwv,Picture Ppicture) //~vagFI~//~vb12R~
//            {                                                      //~vagFI~//~vb12R~
//                Soldposy=0;                                        //~vagFI~//~vb12R~
//                if (Dump.Y)  Dump.println("onNewPicture,hh="+webView.getContentHeight());//~vagFI~//~vb12R~
//            }                                                      //~vagFI~//~vb12R~
//        });                                                        //~vagFI~//~vb12R~
  }                                                                //~vagFI~
    //****************************************
    //*Back/Fwd/ZoomIn/Zoomout button                              //~vagFR~
    //****************************************
	@Override
    protected boolean onClickOther(int PbuttonId)
    {
    	boolean rc=false;   //not dismiss at return
    //****************
        if (Dump.Y) Dump.println("AxeDlhgArmOption onClickOther buttonid="+Integer.toHexString(PbuttonId));
        switch(PbuttonId)
        {
        case btnidBack:                                            //~vagFR~
			webView.goBack();                                      //~vagFI~
            break;
        case btnidFwd:                                             //~vagFI~
			webView.goForward();                                   //~vagFR~
            break;                                                 //~vagFI~
        case btnidZin:                                             //~vagFI~
			webView.zoomIn();                                      //~vagFI~
            break;                                                 //~vagFI~
        case btnidZout:                                            //~vagFI~
			webView.zoomOut();                                     //~vagFI~
            break;                                                 //~vagFI~
//        case btnidTop:                                           //~vaxgR~
//            webView.pageUp(true);                                //~vaxgR~
//            break;                                               //~vaxgR~
//        case btnidBottom:                                        //~vaxgR~
//            boolean scrolled=webView.pageDown(true);             //~vaxgR~
//            if (!scrolled)                                       //~vaxgR~
//            {                                                    //~vaxgR~
//                int hh=webView.getContentHeight();               //~vaxgR~
//                if (Dump.Y)  Dump.println("WebView Button Bottom contentHeight="+hh);//~vaxgR~
//                if (Soldposy!=0 && Soldposy==hh)                 //~vaxgR~
//                {                                                //~vaxgR~
//                    if (++Stryctr>1)                             //~vaxgR~
//                        Utils.showToast(R.string.Html_TryDoubleTap);    //SHORT//~vaxgR~
//                }                                                //~vaxgR~
//                else                                             //~vaxgR~
//                {                                                //~vaxgR~
//                    Soldposy=hh;                                 //~vaxgR~
//                    Stryctr=0;                                   //~vaxgR~
//                }                                                //~vaxgR~
//            }                                                    //~vaxgR~
//            else                                                 //~vaxgR~
//                Soldposy=0;                                      //~vaxgR~
//            break;                                               //~vaxgR~
        }
        return rc;
    }
    //****************************************                     //~vagFI~
    //*key listener to ignore Back key                             //~vagFI~
    //****************************************                     //~vagFI~
    @Override                                                      //~vagFI~
    public boolean onKeyDown(int Pkeycode,KeyEvent Pevent)         //~vagFI~
    {                                                              //~vagFI~
        if (Pkeycode==KeyEvent.KEYCODE_BACK)                       //~vagFI~
        {                                                          //~vagFI~
            webView.goBack();                                      //~vagFI~
            return true;                                           //~vagFI~
        }                                                          //~vagFI~
        return super.onKeyDown(Pkeycode,Pevent);                   //~vagFI~
    }                                                              //~vagFI~
}
