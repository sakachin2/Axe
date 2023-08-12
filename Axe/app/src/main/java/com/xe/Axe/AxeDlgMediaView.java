//*CID://+vc65R~: update#=190;                                     //~vc60R~//~vc65R~
//**********************************************************************
//vc65 2023/08/09 keep MC width                                    //~vc65I~
//vc60 2023/08/03 mediaview as openWith                            //~vc60I~
//**********************************************************************
package com.xe.Axe;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.ahsv.AG;
import com.ahsv.utils.UMediaStore;

import java.io.IOException;

//**********************************************************************//~vc60I~
// fromAxeActivity.openWith                                        //~vc60I~
//**********************************************************************//~vc60I~
public class AxeDlgMediaView extends AxeDialog                       //~vagFR~//~vc60R~
//      implements MediaController.MediaPlayerControl                //~vc60R~
{
//**********************************************************************
    private static final String PKEY_HEIGHTVIDEOMC="HEIGHT_VIDEO_MC";//~vc65I~
	private static final int LAYOUT=R.layout.dialogmediaview;      //~vagFR~//~vc60R~
	private static final String CN="AxeDlgMediaView:";             //~vc60I~
    public static final int MT_IMAGE=1;                            //~vc60R~
    public static final int MT_VIDEO=2;                            //~vc60I~
    public static final int MT_AUDIO=3;                            //~vc60I~
    public static final String MIME_AUDIO="audio/";                //~vc60I~
	private static final String[] SmediaTypeMime={                 //~vc60I~
    	"image/",                                                  //~vc60R~
    	"video/",                                                  //~vc60R~
//      "audio/",                                                  //~vc60R~
        MIME_AUDIO                                                 //~vc60I~
        };                                                          //~vc60I~
	private static final int[] SmediaTypeID={                      //~vc60I~
    	MT_IMAGE,                                                  //~vc60I~
    	MT_VIDEO,                                                  //~vc60I~
        MT_AUDIO,                                                  //~vc60I~
        };                                                          //~vc60I~
    private static final double RATE_DLG_SHORT=0.5;                //~vc60R~
//  private static final double RATE_DLG_LONG=0.33;                //~vc60R~
    private static final double RATE_DLG_AUDIO_W=0.3;              //~vc60I~
    private static final double RATE_DLG_EXPAND_W=0.95;            //~vc60R~
    private static final double RATE_DLG_EXPAND_H=0.90;            //~vc60R~
    //~vc60I~
    private static String parmFID;                                 //~vc60R~
    private static String parmUtf8;                                //~vc60I~
    private static boolean swUri;                                  //~vc60I~
    private static int mediaType;                                  //~vc60I~
//  private static int heightAudioMC;                              //~vc60I~//~vc65R~
    private static int heightVideoMC;                              //~vc60I~
                                                                   //~vc60I~
    private TextView tvTitle;                                     //~vc60I~
    private TextView tvAudioTitle,tvAudioArchist,tvAudioDuration;  //~vc60I~
//  private TextView tvMediaController;                            //~vc60I~//~vc65R~
    private TextView tvAudioMCAnchor;                              //~vc65I~
    private TextView tvVideoMCAnchor;                               //~vc60I~//~vc65R~
    private ImageView imageView;                                   //~vc60I~
    private VideoView videoView;                                   //~vc60I~
    private LinearLayout audioView;                                //~vc60R~
//  private LinearLayout llVideoMC,llllVideoMC;                    //~vc60I~//~vc65R~
    private Button btnRepeat,btnExpand;                            //~vc60R~
    private Uri mediaUri;                                          //~vc60I~
    private static int mediaSize;                                         //~vc60I~
    private MediaController MC;                                    //~vc60I~
    private MediaPlayer MP;                                        //~vc60I~
    private int dlgWWP,dlgHHP,dlgWWL,dlgHHL;                       //~vc60R~
    private int dlgWWPX,dlgHHPX,dlgWWLX,dlgHHLX;                   //~vc60I~
//  private int dlgWW,dlgHH;                                       //~vc60R~
    private int dlgWWOld,dlgHHOld;                                 //~vc60I~
    private int wwContents,hhContents;                             //~vc60I~
    private static boolean SswExpanded;                            //~vc60I~
//  private FrameLayout flAudioMC;                                 //~vc60R~//~vc65R~
    private LinearLayout llAudioMCAnchorAdd;                       //~vc65I~
//  private FrameLayout flVideoMC;                                 //~vc60R~//~vc65R~
//  private RelativeLayout rlVideoMC;                              //~vc60R~//~vc65R~
//  private RelativeLayout rlAudioMC;                              //~vc60R~//~vc65R~
    private LinearLayout llVideo;                                  //~vc60I~
	private int tvTitleHeight;                                     //~vc60I~
	private int buttonHeight;                                      //~vc60I~
	private String strTitle;                                       //~vc60I~
	private UMediaStore.AudioFile audioInfo;                       //~vc60I~
    private boolean swDismiss;                                     //~vc60I~
    private MCI aMCI;                                              //~vc60I~
    private int initHeightMC;                                      //~vc60I~
//**********************************
	public AxeDlgMediaView()                                         //~vagFR~//~vc60R~
    {
    	super(LAYOUT);
        AxeG.axeDlgMediaView=this;                                 //~vc60I~
        if (AG.aUMediaStore==null)                                 //~vc60I~
        	new UMediaStore();                                     //~vc60I~
    }
//**********************************                               //~vc60I~
	public static int getMediaType(String PmimeType)                      //~vc60I~
    {                                                              //~vc60I~
    	int rc=0;                                                  //~vc60I~
    	for (int ii=0;ii<SmediaTypeMime.length;ii++)               //~vc60I~
        {                                                          //~vc60I~
        	if (PmimeType.startsWith(SmediaTypeMime[ii]))          //~vc60R~
            {                                                      //~vc60I~
            	rc=SmediaTypeID[ii];                               //~vc60I~
                break;                                             //~vc60I~
            }                                                      //~vc60I~
        }                                                          //~vc60I~
    	if (Dump.Y) Dump.println(CN+"getMediaType mime="+PmimeType+",rc="+rc);//~vc60I~
        return rc;                                                 //~vc60I~
    }                                                              //~vc60I~
//**********************************                               //~vc60I~
	public static AxeDlgMediaView newInstanceFile(int Ptype,String Pfpath)//~vc60I~
    {                                                              //~vc60I~
    	if (Dump.Y) Dump.println(CN+"newInstanceFile type="+Ptype+",fpath="+Pfpath);//~vc60I~
    	parmFID=Pfpath;                                            //~vc60R~
    	swUri=false;                                               //~vc60I~
    	mediaType=Ptype;                                           //~vc60I~
		AxeDlgMediaView dlg=new AxeDlgMediaView();                 //~vc60I~
        return dlg;
    }                                                              //~vc60I~
//**********************************                               //~vc60I~
	public static AxeDlgMediaView newInstanceUri(int Ptype,String PstrUri,int Psize)//~vc60R~
    {                                                              //~vc60I~
    	if (Dump.Y) Dump.println(CN+"newInstanceUri type="+Ptype+",uri="+PstrUri+",size="+Psize);//~vc60R~
    	parmFID=PstrUri;                                              //~vc60R~
    	swUri=true;                                                //~vc60I~
    	mediaType=Ptype;                                           //~vc60I~
    	mediaSize=Psize;                                           //~vc60I~
		AxeDlgMediaView dlg=new AxeDlgMediaView();                 //~vc60I~
        return dlg;
    }                                                              //~vc60I~
//**************************************************               //~vc60R~
//*from showDialog()                                               //~vc60I~
//**************************************************               //~vc60I~
	@Override                                                      //~vagFI~
	protected void setupDialogExtend(ViewGroup PlayoutView)        //~vagFI~
    {                                                              //~vagFI~
    	int ww,hh;                                                 //~vc60I~
    	//********************                                     //~vagFI~
    	if (Dump.Y) Dump.println(CN+"setupDialogExtend");          //~vc60I~
	    tvTitle=(TextView)PlayoutView.findViewById(R.id.MediaTitle);    //~vagFR~//~vc60I~
	    imageView=(ImageView)PlayoutView.findViewById(R.id.MediaImage);//~vc60I~
	    videoView=(VideoView)PlayoutView.findViewById(R.id.MediaVideo);//~vc60R~
	    audioView=(LinearLayout)PlayoutView.findViewById(R.id.MediaAudio);//~vc60R~
	    btnRepeat=(Button)PlayoutView.findViewById(R.id.Start);    //~vc60I~
	    btnExpand=(Button)PlayoutView.findViewById(R.id.Expand);   //~vc60I~
	    tvAudioTitle=(TextView)PlayoutView.findViewById(R.id.tvAudioTitle);//~vc60I~
	    tvAudioArchist=(TextView)PlayoutView.findViewById(R.id.tvAudioArchist);//~vc60I~
	    tvAudioDuration=(TextView)PlayoutView.findViewById(R.id.tvAudioDuration);//~vc60I~
//      tvMediaController=(TextView)PlayoutView.findViewById(R.id.tvMediaController);//~vc60I~//~vc65R~
        tvAudioMCAnchor=(TextView)PlayoutView.findViewById(R.id.tvAudioMCAnchor);//~vc65I~
	    tvVideoMCAnchor=(TextView)PlayoutView.findViewById(R.id.tvVideoMCAnchor);//~vc60I~//~vc65R~
//     	flAudioMC=(FrameLayout)PlayoutView.findViewById(R.id.FLMediaAudio);//~vc60R~//~vc65R~
      	llAudioMCAnchorAdd=(LinearLayout)PlayoutView.findViewById(R.id.LLAudioMCAnchorAdd);//~vc65I~
//     	rlVideoMC=(RelativeLayout)PlayoutView.findViewById(R.id.RLVideoMC);//~vc60R~
//    	flVideoMC=(FrameLayout)PlayoutView.findViewById(R.id.FLVideo);//~vc60R~
      	llVideo=(LinearLayout)PlayoutView.findViewById(R.id.LLMediaVideo);//~vc60I~
//    	llVideoMC=(LinearLayout)PlayoutView.findViewById(R.id.LLVideoMC);//~vc60I~//~vc65R~
//      llllVideoMC=(LinearLayout)PlayoutView.findViewById(R.id.LLLLVideoMC);//~vc60I~//~vc65R~
//     	rlAudioMC=(RelativeLayout)PlayoutView.findViewById(R.id.RLAudioMC);//~vc60R~//~vc65R~
        initDialogSize();                                         //~vc60R~
//        WindowManager.LayoutParams lp=getWindow().getAttributes(); //~vagFI~//~vc60R~
//        if (AxeG.displayPL==AxeG.PORTRAIT)                     //~vaieI~//~vc60R~
//        {                                                        //~vc60R~
//            ww=(int)(AxeG.displayW*0.8);                          //~vagFI~//~vc60R~
//            hh=Math.min(ww,(int)((AxeG.displayH-AxeG.bottomSpaceHeight)*0.5)); //~vagFI~//~vc60R~
//            dlgWWP=ww; dlgHHP=hh;                                //~vc60R~
//        }                                                        //~vc60R~
//        else                                                     //~vc60R~
//        {                                                        //~vc60R~
//            hh=(int)((AxeG.displayH-AxeG.bottomSpaceHeight)*0.8);//~vc60R~
//            ww=Math.min(hh,(int)(AxeG.displayW*0.5));            //~vc60R~
//            dlgWWL=ww; dlgHHL=hh;                                //~vc60R~
//        }                                                        //~vc60R~
//        lp.width=ww;                                               //~vagFI~//~vc60R~
//        lp.height=hh;                                              //~vagFI~//~vc60R~
//        lp.gravity= Gravity.RIGHT;                               //~vc60R~
//        getWindow().setAttributes(lp);                             //~vagFI~//~vc60R~
//        dlgWW=ww; dlgHH=hh;                                      //~vc60R~
//        if (SswExpanded)                                         //~vc60R~
//        {                                                        //~vc60R~
//            expandDialog();                                      //~vc60R~
//            btnExpand.setText(R.string.Label_Shrink);            //~vc60R~
//        }                                                        //~vc60R~
        btnExpand.setText(SswExpanded ? R.string.Label_Shrink : R.string.Label_Expand);//~vc60I~
        setupMediaView();                                          //~vc60M~
    }                                                                //~vagFI~//~vc60R~
//***********************************************                  //~vc65I~
    private int getInitialHeightVideoMC()                          //~vc65I~
    {                                                              //~vc65I~
    	int hh=AxeProp.getPreference(PKEY_HEIGHTVIDEOMC,0);            //~vc65I~
        if (hh==0)                                                 //~vc65I~
			hh=(int)AG.resource.getDimension(R.dimen.media_mc_init_height);	//returns pix//~vc65I~
        if (Dump.Y) Dump.println(CN+"getInitialHeightVideoMC rc="+hh);//~vc65I~
        return hh;                                                 //~vc65I~
    }                                                              //~vc65I~
//***********************************************                  //~vc65I~
    private void setHeightVideoMC(int Phh)                          //~vc65I~
    {                                                              //~vc65I~
        if (Dump.Y) Dump.println(CN+"setHeightVideoMC hh="+Phh);   //~vc65I~
    	AxeProp.putPreference(PKEY_HEIGHTVIDEOMC,Phh);          //~vc65I~
    }                                                              //~vc65I~
//***********************************************                  //~vc60I~
    private void initDialogSize()                                  //~vc60I~
    {                                                              //~vc60I~
        int scrH,scrW;                                             //~vc60R~
		tvTitleHeight=(int)AG.resource.getDimension(R.dimen.media_title_height);	//returns pix//~vc60R~
		heightVideoMC=getInitialHeightVideoMC();                   //~vc65R~
//      if (heightAudioMC==0)                                      //~vc60I~//~vc65R~
//			heightAudioMC=(int)AG.resource.getDimension(R.dimen.media_mc_init_height_audio);	//returns pix//~vc60R~//~vc65R~
        if (Dump.Y) Dump.println(CN+"initDialogSize tvTitleHeight="+tvTitleHeight+",heightVideoMC="+heightVideoMC);//~vc60R~//~vc65R~
		buttonHeight=(int)AG.resource.getDimension(R.dimen.media_button_height);   //pixel <= dip//~vc60R~
        if (Dump.Y) Dump.println(CN+"initDialogSize buttonHeight="+buttonHeight+",Dip2Pix="+AxeG.screenDip2Pix);//~vc60I~
        scrH=Math.max(AG.scrWidthReal,AG.scrHeightReal);         //portrait device height//~vc60R~
        scrW=Math.min(AG.scrWidthReal,AG.scrHeightReal);         //portrait device height//~vc60I~
        dlgWWP=(int)(scrW*RATE_DLG_SHORT);                             //dialog width when portrate//~vc60R~
//      dlgHHP=(int)(scrH*RATE_DLG_LONG);    //dialog height when portrait//~vc60R~
        dlgHHP=dlgWWP;                       //dialog height when portrait//~vc60I~
        dlgWWPX=(int)(scrW*RATE_DLG_EXPAND_W);                             //dialog width when portrate//~vc60R~
        dlgHHPX=(int)(scrH*RATE_DLG_EXPAND_H)-tvTitleHeight-buttonHeight;    //dialog height when portrait//~vc60R~
        if (mediaType==MT_VIDEO)                                   //~vc60I~
	        dlgHHPX-=heightVideoMC;                                //~vc60I~
//      else                                                       //~vc60I~//~vc65R~
//      if (mediaType==MT_AUDIO)                                   //~vc60I~//~vc65R~
//          dlgHHPX-=heightAudioMC;                                //~vc60I~//~vc65R~
                                                                   //~vc60I~
        dlgHHL=(int)(scrW*RATE_DLG_SHORT);    //dialog height when landscape//~vc60R~
//      dlgWWL=(int)(scrH*RATE_DLG_LONG);                             //dialog height when landscape//~vc60R~
        dlgWWL=dlgHHL;                                             //~vc60I~
        dlgHHLX=(int)(scrW*RATE_DLG_EXPAND_H)-tvTitleHeight-buttonHeight;    //dialog height when landscape//~vc60R~
        if (mediaType==MT_VIDEO)                                   //~vc60I~
	        dlgHHLX-=heightVideoMC;                                //~vc60R~
//      else                                                       //~vc60I~//~vc65R~
//      if (mediaType==MT_AUDIO)                                   //~vc60I~//~vc65R~
//          dlgHHLX-=heightAudioMC;                                //~vc60I~//~vc65R~
        dlgWWLX=(int)(scrH*RATE_DLG_EXPAND_W);                             //dialog height when landscape//~vc60R~
        if (Dump.Y) Dump.println(CN+"initDialogSize scrW="+scrW+",scrH="+scrH);//~vc60I~
        if (Dump.Y) Dump.println(CN+"initDialogSize dlgWWP="+dlgWWP+",dlgHHP="+dlgHHP);//~vc60I~
        if (Dump.Y) Dump.println(CN+"initDialogSize dlgWWL="+dlgWWL+",dlgHHL="+dlgHHL);//~vc60I~
        if (Dump.Y) Dump.println(CN+"initDialogSize dlgWWPX="+dlgWWPX+",dlgHHPX="+dlgHHPX);//~vc60I~
        if (Dump.Y) Dump.println(CN+"initDialogSize dlgWWLX="+dlgWWLX+",dlgHHLX="+dlgHHLX);//~vc60I~
    }                                                              //~vc60I~
////***********************************************                //~vc60R~
//    private void expandDialog()                                  //~vc60R~
//    {                                                            //~vc60R~
//        if (Dump.Y) Dump.println(CN+"expandDialog dlgPort=("+dlgWWP+","+dlgHHP+")");//~vc60R~
//        if (Dump.Y) Dump.println(CN+"expandDialog dlgLand=("+dlgWWL+","+dlgHHL+")");//~vc60R~
//        if (Dump.Y) Dump.println(CN+"expandDialog displayPL="+AxeG.displayPL+",ww="+AxeG.displayW+",hh="+AxeG.displayH);//~vc60R~
//        int hh,ww;                                               //~vc60R~
//        if (AxeG.displayPL==AxeG.PORTRAIT)                       //~vc60R~
//        {                                                        //~vc60R~
//            ww=(int)(AxeG.displayW*0.95);                        //~vc60R~
//            hh=(int)((AxeG.displayH-AxeG.bottomSpaceHeight)*0.95);//~vc60R~
//        }                                                        //~vc60R~
//        else                                                     //~vc60R~
//        {                                                        //~vc60R~
//            hh=(int)((AxeG.displayH-AxeG.bottomSpaceHeight)*0.95);//~vc60R~
//            ww=(int)(AxeG.displayW*0.95);                        //~vc60R~
//        }                                                        //~vc60R~
//        WindowManager.LayoutParams lp=getWindow().getAttributes();//~vc60R~
//        dlgWWOld=lp.width;                                       //~vc60R~
//        dlgHHOld=lp.height;                                      //~vc60R~
////        lp.width=ww;                                           //~vc60R~
////        lp.height=hh;                                          //~vc60R~
////        lp.gravity= Gravity.RIGHT;                             //~vc60R~
////        getWindow().setAttributes(lp);                         //~vc60R~
//        adjustDialogSize(wwContents,hhContents,ww,hh);           //~vc60R~
//    }                                                            //~vc60R~
////***********************************************                //~vc60R~
//    private void shrinkDialog()                                  //~vc60R~
//    {                                                            //~vc60R~
//        if (Dump.Y) Dump.println(CN+"shrinkDialog dlgPort=("+dlgWWP+","+dlgHHP+")");//~vc60R~
//        if (Dump.Y) Dump.println(CN+"shrinkDialog dlgLand=("+dlgWWL+","+dlgHHL+")");//~vc60R~
//        if (Dump.Y) Dump.println(CN+"shrinkDialog displayPL="+AxeG.displayPL+",ww="+AxeG.displayW+",hh="+AxeG.displayH);//~vc60R~
//        if (Dump.Y) Dump.println(CN+"shrinkDialog dlgWWOld="+dlgWWOld+",dlgHHOld="+dlgHHOld);//~vc60R~
//        WindowManager.LayoutParams lp=getWindow().getAttributes();//~vc60R~
//        if (Dump.Y) Dump.println(CN+"shrinkDialog before shrink oldWW="+lp.width+",oldHH="+lp.height);//~vc60R~
//        if (dlgWWOld>0 && dlgHHOld>0)                            //~vc60R~
//        {                                                        //~vc60R~
//            lp.width=dlgWWOld;                                   //~vc60R~
//            lp.height=dlgHHOld;                                  //~vc60R~
//        }                                                        //~vc60R~
//        lp.gravity= Gravity.RIGHT;                               //~vc60R~
//        getWindow().setAttributes(lp);                           //~vc60R~
//    }                                                            //~vc60R~
//***********************************************                  //~vc60I~
	private void adjustDialogSize(boolean PswExpand,int PimageWW,int PimageHH)//~vc60R~
    {                                                              //~vc60I~
        if (Dump.Y) Dump.println(CN+"adjustDialogSize PswExpand="+PswExpand+",imageWW="+PimageWW+",imageHH="+PimageHH);//~vc60R~
        if (Dump.Y) Dump.println(CN+"adjustDialogSize displayPL(portrait=0)="+AxeG.displayPL);//~vc60I~
        if (Dump.Y) Dump.println(CN+"adjustDialogSize heightVideoMC="+heightVideoMC);//~vc60R~
        int dlgHH,dlgWW;                                           //~vc60I~
        if (PswExpand)                                           //~vc60R~
        {                                                          //~vc60I~
        	if (AxeG.displayPL==AxeG.PORTRAIT)                     //~vc60I~
            {	dlgWW=dlgWWPX; dlgHH=dlgHHPX; }	                   //~vc60I~
            else                                                   //~vc60I~
            {	dlgWW=dlgWWLX; dlgHH=dlgHHLX; }                    //~vc60I~
        }                                                          //~vc60I~
        else                                                       //~vc60I~
        {                                                          //~vc60I~
        	if (AxeG.displayPL==AxeG.PORTRAIT)                     //~vc60I~
            {	dlgWW=dlgWWP; dlgHH=dlgHHP; }                      //~vc60I~
            else                                                   //~vc60I~
            {	dlgWW=dlgWWL; dlgHH=dlgHHL; }                      //~vc60I~
        }                                                          //~vc60I~
        double rateWHdlg=(double)dlgWW/dlgHH;	                   //~vc60R~
        double rateWHimage=(double)PimageWW/PimageHH;              //~vc60I~
        if (Dump.Y) Dump.println(CN+"adjustDialogSize rateWHdlg="+rateWHdlg+",rateWHimage="+rateWHimage);//~vc60I~
        int newWW,newHH;                                           //~vc60I~
        if (rateWHimage<rateWHdlg)	//tall image, space in left,rightvc60I~//~vc60R~
        {                                                          //~vc60I~
        	newHH=dlgHH;                                           //~vc60M~
        	newWW=(int)(newHH*rateWHimage);                        //~vc60R~
        }                                                          //~vc60I~
        else                         //flat image,  space intop,bottom//~vc60R~
        {                                                          //~vc60I~
        	newWW=dlgWW;                                          //~vc60R~
        	newHH=(int)(newWW/rateWHimage);                         //~vc60R~
        }                                                          //~vc60I~
        if (Dump.Y) Dump.println(CN+"adjustDialogSize newWW="+newWW+",newHH="+newHH);//~vc60I~
//      LinearLayout.LayoutParams lllp=new LinearLayout.LayoutParams(newWW,newHH);//~vc60I~
//      imageView.setLayoutParams(lllp);	//TODO test            //~vc60I~
        WindowManager.LayoutParams lp=getWindow().getAttributes(); //~vc60I~
//      newWW=(int)(newWW*0.8);	//TODO test                        //~vc60I~
//      lp.setsetMargins(2,2,2,2);	//TODO test                        //~vc60I~
        lp.width=newWW;                                               //~vc60I~
        lp.height=newHH+tvTitleHeight+buttonHeight;                //~vc60R~
        if (mediaType==MT_VIDEO)                                   //~vc60I~
	        lp.height+=heightVideoMC;                              //~vc60R~
        lp.gravity= Gravity.RIGHT;                                 //~vc60I~
        if (Dump.Y) Dump.println(CN+"adjustDialogSize lp.width="+lp.width+",lp.hight="+lp.height+",tvTitleHeight="+tvTitleHeight+",buttonHeight="+buttonHeight);//~vc60R~
        getWindow().setAttributes(lp);                             //~vc60I~
    }                                                              //~vc60I~
//***********************************************                  //~vc60I~
	private void adjustDialogSizeAudio()                           //~vc60I~
    {                                                              //~vc60I~
        if (Dump.Y) Dump.println(CN+"adjustDialogSizeAudio");      //~vc65R~
        WindowManager.LayoutParams lp=getWindow().getAttributes(); //~vc65I~
        lp.gravity= Gravity.RIGHT;                                 //~vc65I~
        getWindow().setAttributes(lp);                             //~vc65I~
        return;                                                    //~vc65I~
    }                                                              //~vc60I~
//**************************************************               //~vc60I~
	private void setupMediaView()                                  //~vc60I~
    {                                                              //~vc60I~
    	int ww,hh;                                                 //~vc60I~
        String fid;                                                //~vc60I~
    	//********************                                     //~vc60I~
    	if (Dump.Y) Dump.println(CN+"setupMediaView");             //~vc60I~
        MC=null;                                                   //~vc60I~
//        int pos=parmFID.lastIndexOf('/');                        //~vc60R~
//        if (pos>0)                                               //~vc60R~
//            fid=parmFID.substring(pos+1);                        //~vc60R~
//        else                                                     //~vc60R~
//            fid=parmFID;                                         //~vc60R~
//        tvTitle.setText(fid);                                    //~vc60R~
//        strTitle=fid;                                            //~vc60R~
        MC=null;                                                   //~vc60I~
        MP=null;                                                   //~vc60I~
        try                                                        //~vc60I~
        {                                                          //~vc60I~
            if (swUri)                                             //~vc60R~
                mediaUri=Uri.parse(parmFID);                       //~vc60R~
            else                                                   //~vc60I~
                mediaUri=fpath2Uri(parmFID);                       //~vc60I~
            parmUtf8=mediaUri.getPath();                            //~vc60I~
    		if (Dump.Y) Dump.println(CN+"setupMediaView parmUtf8="+parmUtf8);//~vc60I~
            int pos=parmUtf8.lastIndexOf('/');                     //~vc60I~
            if (pos>0)                                             //~vc60I~
                fid=parmUtf8.substring(pos+1);                     //~vc60I~
            else                                                   //~vc60I~
                fid=parmUtf8;                                      //~vc60I~
            tvTitle.setText(fid);                                  //~vc60I~
            strTitle=fid;                                          //~vc60I~
                                                                   //~vc60I~
            switch(mediaType)                                      //~vc60R~
            {                                                      //~vc60R~
            case MT_IMAGE:                                         //~vc60R~
                imageView.setVisibility(View.VISIBLE);             //~vc60R~
                btnExpand.setVisibility(View.VISIBLE);             //~vc60I~
                setImage();                                        //~vc60R~
                break;                                             //~vc60R~
            case MT_VIDEO:                                         //~vc60R~
//	    		((LinearLayout)layoutView.findViewById(R.id.LLMediaVideo)).setVisibility(View.VISIBLE);  //TODO test//~vc60R~
//              videoView.setVisibility(View.VISIBLE);             //~vc60R~
//              rlVideo.setVisibility(View.VISIBLE);               //~vc60R~
                llVideo.setVisibility(View.VISIBLE);               //~vc60I~
                btnRepeat.setVisibility(View.VISIBLE);             //~vc60I~
                btnExpand.setVisibility(View.VISIBLE);             //~vc60I~
                startVideo();                                      //~vc60R~
                break;                                             //~vc60R~
            case MT_AUDIO:                                         //~vc60R~
                audioView.setVisibility(View.VISIBLE);             //~vc60R~
                btnRepeat.setVisibility(View.VISIBLE);             //~vc60R~
                startAudio();                                      //~vc60R~
                break;                                             //~vc60R~
            }                                                      //~vc60R~
        }                                                          //~vc60I~
        catch(Exception e)                                         //~vc60I~
        {                                                          //~vc60I~
        	Dump.printlnNoMsg(e,CN+"setupMediaView Exception:"+parmFID);//~vc60I~
        }                                                          //~vc60I~
  	}                                                              //~vc60I~
//**************************************************               //~vc60I~
    protected boolean onClickOther(int PbuttonId)                  //~vc60I~
    {                                                              //~vc60I~
    	if (Dump.Y) Dump.println(CN+"onClickOther id="+Integer.toHexString(PbuttonId)+",mediaType="+mediaType);//~vc60I~
    	if (PbuttonId==R.id.Start)                                 //~vc60I~
        {                                                          //~vc60I~
            switch(mediaType)                                      //~vc60I~
            {                                                      //~vc60I~
            case MT_IMAGE:                                         //~vc60I~
                break;                                             //~vc60I~
            case MT_VIDEO:                                         //~vc60I~
                repeatVideo();                                     //~vc60R~
                break;                                             //~vc60I~
            case MT_AUDIO:                                         //~vc60I~
                repeatAudio();                                     //~vc60R~
                break;                                             //~vc60I~
            }                                                      //~vc60I~
                                                                   //~vc60I~
        }                                                          //~vc60I~
        else                                                       //~vc60I~
    	if (PbuttonId==R.id.Expand)                                //~vc60I~
        {                                                          //~vc60I~
        	if (SswExpanded)                                       //~vc60I~
            {                                                      //~vc60I~
				adjustDialogSize(false,wwContents,hhContents);     //~vc60R~
                btnExpand.setText(R.string.Label_Expand);          //~vc60R~
            }                                                      //~vc60I~
            else                                                   //~vc60I~
            {                                                      //~vc60I~
				adjustDialogSize(true,wwContents,hhContents);      //~vc60R~
                btnExpand.setText(R.string.Label_Shrink);          //~vc60R~
            }                                                      //~vc60I~
            SswExpanded=!SswExpanded;                               //~vc60R~
        }                                                          //~vc60I~
    	return false;	//NO dismiss                               //~vc60I~
    }                                                              //~vc60I~
//**************************************************               //~vc60I~
	private void setImage()                                        //~vc60I~
    {                                                              //~vc60I~
    	if (Dump.Y) Dump.println(CN+"setImage swUri="+swUri+",parmFID="+parmFID);//~vc60I~
        int ww=-1,hh=-1;                                           //~vc60I~
        if (swUri)                                                 //~vc60I~
        {                                                          //~vc60I~
            imageView.setImageURI(mediaUri);                       //~vc60I~
            BitmapDrawable drawable=(BitmapDrawable)(imageView.getDrawable());//~vc60I~
	    	if (Dump.Y) Dump.println(CN+"setImage getDrawable="+drawable);//~vc60R~
            if (drawable!=null)                                    //~vc60I~
            {                                                      //~vc60I~
	    		ww=drawable.getIntrinsicWidth();                   //~vc60I~
	    		hh=drawable.getIntrinsicHeight();                  //~vc60I~
		    	if (Dump.Y) Dump.println(CN+"setImage getDrawable intrinsic ww="+ww+",hh="+hh);//~vc60I~
            }                                                      //~vc60I~
        }                                                          //~vc60I~
        else                                                       //~vc60I~
        {                                                          //~vc60I~
        	Bitmap bmp= BitmapFactory.decodeFile(parmFID);         //~vc60I~
        	imageView.setImageBitmap(bmp);                         //~vc60I~
            ww=bmp.getWidth();                                     //~vc60I~
            hh=bmp.getHeight();                                    //~vc60I~
	    	if (Dump.Y) Dump.println(CN+"setImage file ww="+ww+",hh="+hh);//~vc60I~
        }                                                          //~vc60I~
        if (ww>0 && hh>0)                                          //~vc60I~
        {                                                          //~vc60I~
        	wwContents=ww; hhContents=hh;                          //~vc60I~
			adjustDialogSize(SswExpanded,ww,hh);                     //~vc60R~
        }                                                          //~vc60I~
  	}                                                              //~vc60I~
//**************************************************               //~vc60I~
	private String getDuration(int Pduration)                      //~vc60I~
    {                                                              //~vc60I~
    	if (Pduration<0)                                           //~vc60I~
        {                                                          //~vc60I~
	    	if (Dump.Y) Dump.println(CN+"getDuration int="+Pduration+"<0)");//~vc60I~
        	return "(0:0)";                                        //~vc60I~
        }                                                          //~vc60I~
    	int sec=Pduration/1000;                                    //~vc60I~
        int mm=sec/60;                                             //~vc60I~
        int ss=sec%60;                                             //~vc60I~
        String rc="("+mm+" m "+ss+" s)";                           //~vc60R~
    	if (Dump.Y) Dump.println(CN+"getDuration int="+Pduration+",rc="+rc);//~vc60I~
        return rc;                                                 //~vc60I~
    }                                                              //~vc60I~
//**************************************************               //~vc60I~
	private void startVideo()                                      //~vc60I~
    {                                                              //~vc60I~
    	//********************                                     //~vc60I~
    	if (Dump.Y) Dump.println(CN+"startVideo swUri="+swUri+",parmFID="+parmFID+",mediaUri="+mediaUri);//~vc60R~
//      videoView.setZOrderOnTop(true);                            //~vc60R~
        MC=new MediaController(AxeG.context);                      //~vc60R~
        videoView.setMediaController(MC);                          //~vc60R~
	    videoView.setVideoURI(mediaUri);                           //~vc60R~
        videoView.setOnCompletionListener(                         //~vc60I~
			new MediaPlayer.OnCompletionListener()                 //~vc60I~
            {                                                      //~vc60I~
                @Override                                          //~vc60I~
                public void onCompletion(MediaPlayer Pmp)          //~vc60I~
                {                                                  //~vc60I~
                    if (Dump.Y) Dump.println(CN+"startVideo.onCompletion");//~vc60I~
                }                                                  //~vc60I~
           });                                                     //~vc60I~
        videoView.setOnPreparedListener(                           //~vc60I~
            new MediaPlayer.OnPreparedListener()                   //~vc60I~
            {                                                      //~vc60I~
                @Override                                          //~vc60I~
                public void onPrepared(MediaPlayer Pmp)            //~vc60I~
                {                                                  //~vc60I~
                	MP=Pmp;                                        //~vc60I~
                	int duration=videoView.getDuration();          //~vc60I~
                    if (Dump.Y) Dump.println(CN+"startVideo.onPrepared duration="+duration);//~vc60I~
        			tvTitle.setText(strTitle+getDuration(duration));//~vc60R~
                    if (Dump.Y) Dump.println(CN+"startVideo.onPrepared ww="+Pmp.getVideoWidth()+",hh="+Pmp.getVideoHeight());//~vc60R~
                    wwContents=Pmp.getVideoWidth();                  //~vc60I~
                    hhContents=Pmp.getVideoHeight();               //~vc60I~
					setVideoMC();                            //~vc60I~//~vc65R~
					adjustDialogSize(SswExpanded,Pmp.getVideoWidth(),Pmp.getVideoHeight());//~vc60R~
                    Pmp.setOnVideoSizeChangedListener                  //~vc60I~
                    ( new MediaPlayer.OnVideoSizeChangedListener()             //~vc60I~
                    		{                                      //~vc60I~
                            	@Override                          //~vc60I~
                                public void onVideoSizeChanged(MediaPlayer Pmp,int Pww,int Phh)//~vc60I~
                                {                                  //~vc60I~
				                    if (Dump.Y) Dump.println(CN+"startVideo.onPrepared.onVideoSizeChanged ww="+Pww+",hh="+Phh);//~vc60I~
				        			if (Dump.Y) Dump.println(CN+"startAudio.onVideSizeChanged MC hight="+MC.getHeight()+",width="+MC.getWidth());//~vc60I~
                                    heightVideoMC=MC.getHeight();  //~vc60I~
								    setHeightVideoMC(heightVideoMC);//~vc65I~
				        			if (Dump.Y) Dump.println(CN+"startAudio.onVideSizeChanged heightVideoMC="+heightVideoMC);//~vc65I~
                                    showMC(Pmp);                   //~vc60I~
                                }                                  //~vc60I~
                            }                                      //~vc60I~
                    );                                             //~vc60I~
                }                                                  //~vc60I~
            });                                                    //~vc60I~
       videoView.setOnErrorListener(                               //~vc60I~
            new MediaPlayer.OnErrorListener()                      //~vc60I~
            {                                                      //~vc60I~
                @Override                                          //~vc60I~
                public boolean onError(MediaPlayer Pmp,int Pwhat,int Pextra)//~vc60I~
                {                                                  //~vc60I~
                    if (Dump.Y) Dump.println(CN+"startVideo.onError@@@@ what="+Pwhat+",extra="+Pextra);//~vc60R~
                    resetMP(Pmp,true/*swStop*/);                   //~vc60I~
                    boolean rc=true;    //app processed            //~vc60I~
                    return rc;                                     //~vc60I~
                }                                                  //~vc60I~
            });                                                    //~vc60I~
    	if (Dump.Y) Dump.println(CN+"startVideo issue start");     //~vc60R~
        videoView.start();                                         //~vc60I~
  	}                                                              //~vc60I~
//*************************************************************    //~vc60I~
	private void setVideoMC()                            //~vc60R~ //~vc65R~
    {                                                              //~vc60I~
        if (Dump.Y) Dump.println(CN+"setVideoMC tvVideoMCAnchor="+tvVideoMCAnchor);    //~vc60I~//~vc65R~
    	MC.setAnchorView(tvVideoMCAnchor);                                   //~vc60I~//~vc65R~
	    ((ViewGroup)MC.getParent()).removeView(MC);                //~vc60I~
        if (Dump.Y) Dump.println(CN+"startAudio.onPrepared setVideoMC MC hight="+MC.getHeight()+",width="+MC.getWidth());//~vc60I~
        if (Dump.Y) Dump.println(CN+"startAudio.onPrepared setVideoMC videoView hight="+videoView.getHeight()+",width="+videoView.getWidth());//~vc60I~
		LinearLayout llVideoMCAnchorAdd=(LinearLayout)layoutView.findViewById(R.id.LLVideoMCAnchorAdd);//~vc65I~
    	llVideoMCAnchorAdd.addView(MC);                            //~vc65I~
    }                                                              //~vc60I~
//**************************************************               //~vc60I~
	private void repeatVideo()                                     //~vc60I~
    {                                                              //~vc60I~
    	//********************                                     //~vc60I~
    	if (Dump.Y) Dump.println(CN+"repeatVideo issue start()");  //~vc60I~
        videoView.seekTo(0);                                       //~vc60I~
        videoView.start();                                         //~vc60I~
        showMC(MP);                                                //~vc60I~
  	}                                                              //~vc60I~
//**************************************************               //~vc60I~
//*by repeat button                                                //~vc60I~
//**************************************************               //~vc60I~
	private void repeatAudio()                                     //~vc60R~
    {                                                              //~vc60I~
    	//********************                                     //~vc60I~
    	if (Dump.Y) Dump.println(CN+"repeatAudio");                //~vc60R~
//      MP.seekTo(0);                                              //~vc60R~
//      MP.start();                                                //~vc60R~
        aMCI.seekTo(0);                                            //~vc60R~
        aMCI.start();                                              //~vc60R~
        showMC(MP);                                                //~vc60I~
  	}                                                              //~vc60I~
//********************************************************         //~vc60I~
	private void startAudio()                              //~vc60R~
    {                                                              //~vc60I~
	    if (Dump.Y) Dump.println(CN+"startAudio uri="+mediaUri);   //~vc60R~
		adjustDialogSizeAudio();                                   //~vc60I~
//      btnRepeat.setEnabled(false);                               //~vc60R~
        if (!setAudioInfo(mediaUri))                               //~vc60R~
        	return;                                                //~vc60I~
        MC=new MediaController(AxeG.context);                      //~vc60I~
        MP=new MediaPlayer();                                      //~vc60I~
        try                                                        //~vc60I~
        {                                                          //~vc60I~
	        setStreamType(MP);                                     //~vc60I~
            MP.setDataSource(AxeG.context,mediaUri); //IOException if FileNotFound//~vc60R~
	    	if (Dump.Y) Dump.println(CN+"startAudio uri="+mediaUri);//~vc60R~
//          MP.setLooping(true);                                   //~vc60R~
            MP.setOnPreparedListener(                              //~vc60R~
                    new MediaPlayer.OnPreparedListener()           //~vc60R~
                    {                                              //~vc60R~
                        @Override                                  //~vc60R~
                        public void onPrepared(MediaPlayer Pmp)    //~vc60R~
                        {                                          //~vc60R~
                          try	                                   //~vc60I~
                          {                                        //~vc60I~
							setAudioMC();	                   //~vc60I~//~vc65R~
                            Pmp.start();                           //~vc60I~
                            showMC(MP);                            //~vc60R~
                          }                                        //~vc60I~
                          catch(Exception e)                       //~vc60I~
                          {                                        //~vc60I~
                              Dump.println(e,CN+"startAudio.onPrepared");//~vc60I~
                          }                                        //~vc60I~
                        }                                          //~vc60R~
                    });                                            //~vc60R~
            MP.setOnErrorListener(                                 //~vc60R~
                    new MediaPlayer.OnErrorListener()              //~vc60R~
                    {                                              //~vc60R~
                        @Override                                  //~vc60R~
                        public boolean onError(MediaPlayer Pmp,int Pwhat,int Pextra)//~vc60R~
                        {                                          //~vc60R~
                            if (Dump.Y) Dump.println(CN+"startAudio.onError@@@@ what="+Pwhat+",extra="+Pextra);//~vc60R~
                            resetMP(Pmp,true/*swStop*/);           //~vc60R~
                            boolean rc=true;    //app processed    //~vc60R~
                            return rc;                             //~vc60R~
                        }                                          //~vc60R~
                    });                                            //~vc60R~
            MP.setOnCompletionListener(                            //~vc60R~
                    new MediaPlayer.OnCompletionListener()         //~vc60R~
                    {                                              //~vc60R~
                        @Override                                  //~vc60R~
                        public void onCompletion(MediaPlayer Pmp)  //~vc60R~
                        {                                          //~vc60R~
                            if (Dump.Y) Dump.println(CN+"startAudio.onCompletion");//~vc60R~
                            if (Dump.Y) Dump.println(CN+"startAudio.onCompletion MC hight="+MC.getHeight()+",width="+MC.getWidth());//~vc60I~
                            if (Dump.Y) Dump.println(CN+"startAudio.onCompletion tvAudioMCAnchor hight="+tvAudioMCAnchor.getHeight()+",width="+tvAudioMCAnchor.getWidth());//~vc65R~
                            if (Dump.Y) Dump.println(CN+"startAudio.onPrepared llAudioMCAnchorAdd hight="+llAudioMCAnchorAdd.getHeight()+",width="+llAudioMCAnchorAdd.getWidth());//~vc65R~
//                          resetMP(Pmp,false/*swStop*/);          //~vc60R~
                        }                                          //~vc60R~
                    });                                            //~vc60R~
	    	if (Dump.Y) Dump.println(CN+"startAudio issue prepareAsync");//~vc60I~
            MP.prepareAsync();                                     //~vc60R~
        }                                                          //~vc60I~
        catch(IllegalArgumentException e)                          //~vc60I~
        {                                                          //~vc60I~
        	Dump.printlnNoMsg(e,CN+"startAudio IlleagalArgumentException:"+parmFID);//~vc60R~
        }                                                          //~vc60I~
        catch(IllegalStateException e)                             //~vc60I~
        {                                                          //~vc60I~
        	Dump.printlnNoMsg(e,CN+"startAudio IlleagalStateException:"+parmFID);//~vc60R~
        }                                                          //~vc60I~
        catch(IOException e)                                       //~vc60I~
        {                                                          //~vc60I~
        	Dump.printlnNoMsg(e,CN+"startAudio IOException:"+parmFID);//~vc60R~
        }                                                          //~vc60I~
	    if (Dump.Y) Dump.println(CN+"startAudio exit");            //~vc60R~
    }                                                              //~vc60I~
//*************************************************************    //~vc60I~
	private void setAudioMC()                                 //~vc60I~//~vc65R~
    {                                                              //~vc60I~
        if (Dump.Y) Dump.println(CN+"setAudioMC");                 //~vc65R~
        if (Dump.Y) Dump.println(CN+"setAudioMC tvAudioMCAnchor hight="+tvAudioMCAnchor.getHeight()+",width="+tvAudioMCAnchor.getWidth());//~vc60R~//~vc65I~
        if (Dump.Y) Dump.println(CN+"setAudioMC llAudioMCAnchorAdd hight="+llAudioMCAnchorAdd.getHeight()+",width="+llAudioMCAnchorAdd.getWidth());//~vc65I~
        aMCI=new MCI(MP);                      //~vc60R~           //~vc65I~
        MC.setMediaPlayer(aMCI);               //~vc60I~           //~vc65I~
    	MC.setAnchorView(tvAudioMCAnchor);                         //~vc65R~
	    ((ViewGroup)MC.getParent()).removeView(MC);                //~vc65I~
    	llAudioMCAnchorAdd.addView(MC);                            //~vc65R~
        if (Dump.Y) Dump.println(CN+"startAudio.onPrepared setAudioMC llAudioMCAnchorAdd hight="+llAudioMCAnchorAdd.getHeight()+",width="+llAudioMCAnchorAdd.getWidth());//~vc65R~
        if (Dump.Y) Dump.println(CN+"startAudio.onPrepared setAudioMC MC hight="+MC.getHeight()+",width="+MC.getWidth());//~vc65I~
        return;                                                    //~vc65I~
    }                                                              //~vc60I~
//*************************************************************    //~vc60I~
	private void showMC(MediaPlayer Pmp)                           //~vc60R~
    {                                                              //~vc60I~
		if (Dump.Y) Dump.println(CN+"showMC mp="+Pmp);             //~vc60R~
    	Handler h=new Handler(Looper.getMainLooper());             //~vc60R~
        MediaPlayer mp=Pmp;                                        //~vc60I~
        h.postDelayed(new Runnable()                               //~vc60R~
                    {                                              //~vc60I~
                        public void run()                          //~vc60I~
                        {                                          //~vc60I~
						    if (Dump.Y) Dump.println(CN+"showMC.run");//~vc60I~
                            MediaController mc=AxeG.axeDlgMediaView.MC;//~vc60I~
                            mc.setVisibility(View.VISIBLE);        //~vc60I~
                            mc.setEnabled(true);                   //~vc60I~
                            mc.show(mp.getDuration());             //~vc60R~
//                          mp.start();                            //~vc60R~
//  						mc.setZOrderOnTop(true);               //~vc60R~
                        }                                          //~vc60I~
                    },100);                                        //~vc60R~
    }                                                              //~vc60I~
//*************************************************************    //~vc60I~
//*for intercae MediaController.MediaPlayerControl                 //~vc60I~
//*************************************************************    //~vc60I~
	class MCI                                                      //~vc60I~
	      implements MediaController.MediaPlayerControl            //~vc60I~
    {                                                              //~vc60I~
    	MediaPlayer mciMP;                                          //~vc60I~
    	public MCI(MediaPlayer Pmp)                                //~vc60R~
        {                                                          //~vc60I~
		    if (Dump.Y) Dump.println(CN+"class MCI constructor MP="+Pmp);//~vc60R~
            mciMP=Pmp;                                             //~vc60I~
        }                                                          //~vc60I~
        @Override                                                  //~vc60R~
        public void start() {                                      //~vc60R~
		    if (Dump.Y) Dump.println(CN+"class MCI.start");        //~vc60I~
            if (swDismiss)                                         //~vc60I~
            	return;                                            //~vc60I~
            try{                                                   //~vc60I~
	        	mciMP.start();                                     //~vc60R~
            }catch(Exception e){Dump.printlnNoMsg(e,CN+"MCI start");}//~vc60I~
        }                                                          //~vc60R~
        @Override                                                  //~vc60R~
        public void pause() {                                      //~vc60R~
		    if (Dump.Y) Dump.println(CN+"class MCI.pause");        //~vc60I~
            if (swDismiss)                                         //~vc60I~
            	return;                                            //~vc60I~
            try{                                                   //~vc60I~
            	mciMP.pause();                                     //~vc60R~
            }catch(Exception e){Dump.printlnNoMsg(e,CN+"MCI pause");}//~vc60I~
        }                                                          //~vc60R~
        @Override                                                  //~vc60R~
        public int getDuration() {                                 //~vc60R~
		    if (Dump.Y) Dump.println(CN+"class MCI.getDuration");  //~vc60I~
            if (swDismiss)                                         //~vc60I~
            	return 0;                                          //~vc60I~
            try{                                                   //~vc60I~
            	return mciMP.getDuration();                        //~vc60R~
            }catch(Exception e){Dump.printlnNoMsg(e,CN+"MCI getDuration");}//~vc60I~
            return 0;                                              //~vc60I~
        }                                                          //~vc60R~
        @Override                                                  //~vc60R~
        public int getCurrentPosition() {                          //~vc60R~
		    if (Dump.Y) Dump.println(CN+"class MCI.getCurrentPosition");//~vc60I~
            if (swDismiss)                                         //~vc60I~
            	return 0;                                          //~vc60I~
            try{                                                   //~vc60I~
	            return mciMP.getCurrentPosition();                 //~vc60R~
            }catch(Exception e){Dump.printlnNoMsg(e,CN+"MCI getCurrentPosition");}//~vc60I~
    	    return 0;                                              //~vc60I~
        }                                                          //~vc60R~
        @Override                                                  //~vc60R~
        public void seekTo(int i) {                                //~vc60R~
		    if (Dump.Y) Dump.println(CN+"class MCI.seekTo i="+i);  //~vc60I~
            if (swDismiss)                                         //~vc60I~
            	return;                                            //~vc60I~
            try{                                                   //~vc60I~
            	mciMP.seekTo(i);                                   //~vc60R~
            }catch(Exception e){Dump.printlnNoMsg(e,CN+"MCI seekTo");}//~vc60I~
        }                                                          //~vc60R~
        @Override                                                  //~vc60R~
        public boolean isPlaying() {                               //~vc60R~
		    if (Dump.Y) Dump.println(CN+"class MCI.isPlaying");    //~vc60I~
            if (swDismiss)                                         //~vc60I~
            	return false;                                      //~vc60R~
            try{                                                   //~vc60I~
            	return mciMP.isPlaying();                          //~vc60R~
            }catch(Exception e){Dump.printlnNoMsg(e,CN+"MCI isPlaying");}//~vc60I~
            return false;                                          //~vc60I~
        }                                                          //~vc60R~
        @Override                                                  //~vc60R~
        public int getBufferPercentage() {                         //~vc60R~
		    if (Dump.Y) Dump.println(CN+"class MCI.getBufferPercentage");//~vc60I~
            return 0;                                              //~vc60R~
        }                                                          //~vc60R~
        @Override                                                  //~vc60R~
        public boolean canPause() {                                //~vc60R~
		    if (Dump.Y) Dump.println(CN+"class MCI.canPause");     //~vc60I~
            return true;                                           //~vc60R~
        }                                                          //~vc60R~
        @Override                                                  //~vc60R~
        public boolean canSeekBackward() {                         //~vc60R~
		    if (Dump.Y) Dump.println(CN+"class MCI.canSeekBackward");//~vc60I~
            return true;                                           //~vc60R~
        }                                                          //~vc60R~
        @Override                                                  //~vc60R~
        public boolean canSeekForward() {                          //~vc60R~
		    if (Dump.Y) Dump.println(CN+"class MCI.canSeekFirward");//~vc60I~
            return true;                                           //~vc60R~
        }                                                          //~vc60R~
        @Override          //API18                                 //~vc60R~
        public int getAudioSessionId() {                           //~vc60R~
		    if (Dump.Y) Dump.println(CN+"class MCI.getAudioSessionId");//~vc60I~
            return 0;   //err                                      //~vc60R~
        }                                                          //~vc60R~
    }//class MCI                                                   //~vc60I~
//********************************************************         //~vc60I~
	private void setStreamType(MediaPlayer PMP)                    //~vc60R~
    {                                                              //~vc60I~
	    if (Dump.Y) Dump.println(CN+"setStreamType MP="+PMP);      //~vc60I~
        AG.aUMediaStore.setStreamType(PMP);                        //~vc60I~
    }                                                              //~vc60I~
//********************************************************         //~vc60I~
    private void resetMP(MediaPlayer Pmp,boolean PswStop)          //~vc60I~
    {                                                              //~vc60I~
	    if (Dump.Y) Dump.println(CN+"resetMP uriStarted="+mediaUri+",MP="+Pmp);//~vc60I~
//      MC.setMediaPlayer(null);	//cut off MP from MP==>cause exception NPE//~vc60I~
        AG.aUMediaStore.resetMP(Pmp,PswStop);                      //~vc60I~
    }                                                              //~vc60I~
//********************************************************         //~vc60I~
    @Override                                                      //~vc60I~
    public void  onDismiss()                                       //~vc60I~
    {                                                              //~vc60I~
		if (Dump.Y) Dump.println(CN+"onDismiss MP="+MP+",mediaType="+mediaType);//~vc60R~
	    swDismiss=true;                                            //~vc60I~
        if (MC!=null)                                              //~vc60I~
            MC.hide();                                             //~vc60I~
        if (mediaType==MT_VIDEO)                                   //~vc60I~
        {                                                          //~vc60I~
            if (videoView.isPlaying())                              //~vc60I~
    			resetMP(MP,true/*PswStop*/);                       //~vc60I~
            else                                                   //~vc60I~
    			resetMP(MP,false/*PswStop*/);                      //~vc60I~
            return;                                                //~vc60I~
        }                                                          //~vc60I~
    	if (MP!=null)                                              //~vc60I~
        {                                                          //~vc60I~
    		resetMP(MP,true/*PswStop*/);                           //~vc60I~
            MP=null;                                               //~vc60I~
        }                                                          //~vc60I~
    }                                                              //~vc60I~
//********************************************************         //~vc60I~
    public static void onStopMain()                                         //~vc60I~
    {                                                              //~vc60I~
        AxeDlgMediaView dlg=AxeG.axeDlgMediaView;                  //~vc60I~
		if (Dump.Y) Dump.println(CN+"onStop dlg="+dlg);            //~vc60I~
        if (dlg!=null && dlg.isShowing())                          //~vc60I~
        {                                                          //~vc60I~
            dlg.dismiss();                                         //~vc60I~
        }                                                          //~vc60I~
    }                                                              //~vc60I~
//**************************************************               //~vc60I~
	private Uri fpath2Uri(String Pfpath)                           //~vc60I~
    {                                                              //~vc60I~
    	Uri uri=Uri.parse(Pfpath);                              //~vc60I~
		if (Dump.Y) Dump.println(CN+"fpath2Uri fpath="+",rc="+uri);//~vc60I~
        return uri;                                                //~vc60I~
    }                                                              //~vc60I~
    //****************************************                     //~vagFI~//~vc60I~
    //*key listener to ignore Back key                             //~vagFI~//~vc60I~
    //****************************************                     //~vagFI~//~vc60I~
    @Override                                                      //~vagFI~//~vc60I~
    public boolean onKeyDown(int Pkeycode, KeyEvent Pevent)         //~vagFI~//~vc60I~
    {                                                              //~vagFI~//~vc60I~
		if (Dump.Y) Dump.println(CN+"onkeyDown keycode="+Pkeycode);//~vc60I~
        if (Pkeycode==KeyEvent.KEYCODE_ESCAPE                       //~vagFI~//~vc60R~
        ||  Pkeycode==KeyEvent.KEYCODE_F1                          //~vc60I~
        )                                                          //~vc60I~
        {                                                          //~vagFI~//~vc60I~
			if (Dump.Y) Dump.println(CN+"onkeyDown dismiss by Escape key");//~vc60I~
        	dismiss();                                             //~vc60I~
            return true;                                           //~vagFI~//~vc60I~
        }                                                          //~vagFI~//~vc60I~
        return super.onKeyDown(Pkeycode,Pevent);                   //~vagFI~//~vc60I~
    }                                                              //~vagFI~//~vc60I~
    //******************************************************************//~vc60I~
    public static void orientationChanged()                        //~vc60I~
    {                                                              //~vc60I~
        AxeDlgMediaView dlg=AxeG.axeDlgMediaView;                  //~vc60I~
		if (Dump.Y) Dump.println(CN+"orientationChanged dlg="+dlg);//~vc60R~
        if (dlg!=null && dlg.isShowing())                          //~vc60I~
        {                                                          //~vc60I~
            if (mediaType==MT_AUDIO)                               //~vc60I~
				dlg.adjustDialogSizeAudio();                       //~vc60I~
            else                                                   //~vc60I~
				dlg.adjustDialogSize(SswExpanded,dlg.wwContents,dlg.hhContents);//~vc60R~
        }                                                          //~vc60I~
    }                                                              //~vc60I~
    //******************************************************************//~vc60I~
    public void getAudioInfo(String PstrUri)                       //~vc60R~
    {                                                              //~vc60I~
		if (Dump.Y) Dump.println(CN+"getAudioInfo strUri="+PstrUri+",parmFID="+parmFID);//~vc60R~
        audioInfo=AG.aUMediaStore.getAudioFile(PstrUri,mediaSize); //~vc60R~
    }                                                              //~vc60I~
    //******************************************************************//~vc60I~
    public boolean setAudioInfo(Uri Puri)                          //~vc60I~
    {                                                              //~vc60I~
		if (Dump.Y) Dump.println(CN+"setAudioInfo uri="+Puri);     //~vc60I~
        getAudioInfo(Puri.toString());                             //~vc60R~
        if (audioInfo==null)                                       //~vc60I~
        {                                                          //~vc60I~
//        	Utils.showToastLong(com.ahsv.utils.Utils.getStr(R.string.ErrNotFoundMedia,strTitle));//~vc60R~
//        	return false;                                          //~vc60R~
			int pos=parmUtf8.lastIndexOf('/');                     //~vc60I~
            String title;                                          //~vc60I~
            if (pos>0)                                             //~vc60I~
            	title=parmUtf8.substring(pos);                     //~vc60I~
            else                                                   //~vc60I~
            	title=parmUtf8;                                    //~vc60I~
		    tvAudioTitle.setText(title);                           //~vc60I~
		    tvAudioArchist.setText(R.string.Unknown);              //~vc60I~
	    	tvAudioDuration.setText(R.string.Unknown);             //~vc60I~
			if (Dump.Y) Dump.println(CN+"audioinfo not found;setAudioInfo rc=true");//~vc60I~
        	return true;                                           //~vc60I~
        }                                                          //~vc60I~
		if (Dump.Y) Dump.println(CN+"setAudioInfo title="+audioInfo.title+",artist="+audioInfo.artist+",min="+audioInfo.min+",sec="+audioInfo.sec+",isMusic="+audioInfo.isMusic);//~vc60I~
        if (!audioInfo.isMusic)                                    //~vc60R~
        {                                                          //~vc60I~
			if (Dump.Y) Dump.println(CN+"setAudioInfo not Music"); //~vc60I~
        	Utils.showToastLong(com.ahsv.utils.Utils.getStr(R.string.ErrNotMusic,strTitle));//~vc60R~
        	return false;                                          //~vc60I~
        }                                                          //~vc60I~
	    tvAudioTitle.setText(audioInfo.title);                     //~vc60I~
	    tvAudioArchist.setText(audioInfo.artist);                 //~vc60I~
	    tvAudioDuration.setText(audioInfo.min+" m "+audioInfo.sec+" s");//~vc60R~
		if (Dump.Y) Dump.println(CN+"setAudioInfo rc=true");       //~vc60I~
        return true;                                               //~vc60I~
    }                                                              //~vc60I~
}
