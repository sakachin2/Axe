//*CID://+vc64R~: update#= 32;                                     //~vc64R~
//**********************************************************************
//vc64 2023/08/08 support html display for other than xehelp by webView//~vc64I~
//vc53 2023/06/12 java error;switch-case requres constant          //~vc53I~
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
	private static final String CN="AxeDlgWebView:";               //~vc53I~
	private static final int LAYOUT=R.layout.dialogwebview;        //~vagFR~

    private final static int btnidBack=R.id.HtmlBack;                           //~vagFI~
    private final static int btnidFwd=R.id.HtmlForward;                         //~vagFI~
    private final static int btnidZin=R.id.HtmlZoomIn;                          //~vagFI~
    private final static int btnidZout=R.id.HtmlZoomOut;                        //~vagFI~
//    private final static int btnidTop=R.id.HtmlTop;                //~vagFI~
//    private final static int btnidBottom=R.id.HtmlBottom;          //~vagFI~
    private WebView  webView;                                      //~vagFM~
    private static String topHtml;
    private boolean swLocal;                                       //~vc64I~
//    private int Soldposy,Stryctr;//~vagFI~                       //~vb12R~
//**********************************
	public AxeDlgWebView()                                         //~vagFR~
    {
    	super(LAYOUT);
    }
//**********************************
    public static AxeDlgWebView showHelp(String Purl)            //~vagFR~
    {
    	if (Dump.Y) Dump.println(CN+"showHelp strUrl="+Purl);        //~vc53I~//~vc64R~
    	topHtml=Purl;                                              //~vagFI~
 //       topHtml="http://www.geocities.jp/sakachin2"; //xehelp/indexe.htm";//~vagFR~
//      topHtml="https://www.google.co.jp";                        //~vagFR~
    	AxeDlgWebView dlg=new AxeDlgWebView();                   //~vagFR~
		dlg.showDialog(AxeDialog.NO_TITLE);                        //~vagFR~
        return dlg;
    }
//**********************************                               //~vc64I~
    public static AxeDlgWebView showLocalHtml(String Purl)         //~vc64I~
    {                                                              //~vc64I~
    	if (Dump.Y) Dump.println(CN+"showLocalHtml strUrl="+Purl); //~vc64I~
    	topHtml=Purl;                                              //~vc64I~
    	AxeDlgWebView dlg=new AxeDlgWebView();                     //~vc64I~
    	dlg.swLocal=true;                                          //~vc64I~
		dlg.showDialog(AxeDialog.NO_TITLE);                        //~vc64I~
        return dlg;                                                //~vc64I~
    }                                                              //~vc64I~
//*********
	@Override
//*loadUrl before setContentView to scroll up to botom of data     //~vagFI~
	protected void setupDialogExtendPre(ViewGroup PlayoutView)     //~vagFR~
    {
    	//********************
    	if (Dump.Y) Dump.println(CN+"setupDialogExtendPre");       //~vc64I~
	    webView=(WebView)layoutView.findViewById(R.id.Webview);    //~vagFR~
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); //~vagFM~
        WebSettings ws=webView.getSettings();                           //~vagFI~
        ws.setSupportZoom(true);      //default true               //~vagFI~
        ws.setBuiltInZoomControls(true);                           //~vagFM~
        ws.setLoadWithOverviewMode(true);  //100% zoom out         //~vagFR~
        ws.setUseWideViewPort(true);    //view over screen pix     //~vagFR~
        ws.setAllowFileAccess(true);                               //~vagFI~
        webView.setWebViewClient(new WebViewClient());                          //~vagFI~
      if (swLocal)                                                 //~vc64I~
        loadUrlLocal(webView,topHtml);                             //~vc64I~
      else                                                         //~vc64I~
        webView.loadUrl(topHtml);                                  //~vagFI~
    }                                                              //~vagFR~
//*********                                                        //~vagFI~
	@Override                                                      //~vagFI~
	protected void setupDialogExtend(ViewGroup PlayoutView)        //~vagFI~
    {                                                              //~vagFI~
    	//********************                                     //~vagFI~
    	if (Dump.Y) Dump.println(CN+"setupDilogExtend");          //~vc53I~
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
//        switch(PbuttonId)                                        //~vc53R~
//        {                                                        //~vc53R~
//        case btnidBack:                                            //~vagFR~//~vc53R~
//            webView.goBack();                                      //~vagFI~//~vc53R~
//            break;                                               //~vc53R~
//        case btnidFwd:                                             //~vagFI~//~vc53R~
//            webView.goForward();                                   //~vagFR~//~vc53R~
//            break;                                                 //~vagFI~//~vc53R~
//        case btnidZin:                                             //~vagFI~//~vc53R~
//            webView.zoomIn();                                      //~vagFI~//~vc53R~
//            break;                                                 //~vagFI~//~vc53R~
//        case btnidZout:                                            //~vagFI~//~vc53R~
//            webView.zoomOut();                                     //~vagFI~//~vc53R~
//            break;                                                 //~vagFI~//~vc53R~
////        case btnidTop:                                           //~vaxgR~//~vc53R~
////            webView.pageUp(true);                                //~vaxgR~//~vc53R~
////            break;                                               //~vaxgR~//~vc53R~
////        case btnidBottom:                                        //~vaxgR~//~vc53R~
////            boolean scrolled=webView.pageDown(true);             //~vaxgR~//~vc53R~
////            if (!scrolled)                                       //~vaxgR~//~vc53R~
////            {                                                    //~vaxgR~//~vc53R~
////                int hh=webView.getContentHeight();               //~vaxgR~//~vc53R~
////                if (Dump.Y)  Dump.println("WebView Button Bottom contentHeight="+hh);//~vaxgR~//~vc53R~
////                if (Soldposy!=0 && Soldposy==hh)                 //~vaxgR~//~vc53R~
////                {                                                //~vaxgR~//~vc53R~
////                    if (++Stryctr>1)                             //~vaxgR~//~vc53R~
////                        Utils.showToast(R.string.Html_TryDoubleTap);    //SHORT//~vaxgR~//~vc53R~
////                }                                                //~vaxgR~//~vc53R~
////                else                                             //~vaxgR~//~vc53R~
////                {                                                //~vaxgR~//~vc53R~
////                    Soldposy=hh;                                 //~vaxgR~//~vc53R~
////                    Stryctr=0;                                   //~vaxgR~//~vc53R~
////                }                                                //~vaxgR~//~vc53R~
////            }                                                    //~vaxgR~//~vc53R~
////            else                                                 //~vaxgR~//~vc53R~
////                Soldposy=0;                                      //~vaxgR~//~vc53R~
////            break;                                               //~vaxgR~//~vc53R~
//        }                                                        //~vc53R~
//      switch(PbuttonId)                                          //~vc53I~
//      {                                                          //~vc53I~
        if (PbuttonId== btnidBack)                                 //~vc53I~
            webView.goBack();                                      //~vc53I~
        else //break;                                              //~vc53I~
        if (PbuttonId== btnidFwd)                                  //~vc53I~
            webView.goForward();                                   //~vc53I~
        else //break;                                              //~vc53I~
        if (PbuttonId== btnidZin)                                  //~vc53I~
            webView.zoomIn();                                      //~vc53I~
        else //break;                                              //~vc53I~
        if (PbuttonId== btnidZout)                                 //~vc53I~
            webView.zoomOut();                                     //~vc53I~
//          break;                                                 //~vc53I~
//      }                                                          //~vc53I~
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
    //****************************************                     //~vc64I~
    private void loadUrlLocal(WebView PwebView,String PstrUrl)     //~vc64I~
    {                                                              //~vc64I~
    	if (Dump.Y) Dump.println(CN+"showHelp strUrl="+PstrUrl);   //~vc64I~
      if (true)                                                    //+vc64I~
        PwebView.loadUrl(PstrUrl);                                 //+vc64I~
      else                                                         //+vc64I~
      {                                                            //+vc64I~
        String data="test";                                        //~vc64I~
        PwebView.loadDataWithBaseURL(PstrUrl,data,null/*mime type=text/html*/,null/*encoding*/,PstrUrl/*historyUrl*/);//~vc64I~
      }                                                            //+vc64I~
    }                                                              //~vc64I~
}
