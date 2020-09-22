//*CID://+vc12R~: update#=  91;                                    //~vc12R~
//**********************************************************************//~1107I~
//vc12 2020/06/14 clipRect(rectF,OP_REPLACE) deprecated at android.P//~vc12I~
//**********************************************************************//~vc09I~
package com.ForDeprecated;                                         //~1107R~  //~1108R~//~1109R~//~@@@@R~//~vc09R~

import android.graphics.Canvas;                                    //~vc12I~
import android.graphics.Paint;
import android.graphics.Rect;                                      //~vc12I~

import com.ahsv.utils.Utils;
import com.xe.Axe.Dump;

import java.util.Arrays;

//**********************************************************************//~1107I~
public class Funcs                                                 //~vc09R~
{                                                                  //~0914I~
	public static void clipRect(Canvas Pcanvas,Rect Prect)                 //~vc09I~//~vc12R~
    {                                                              //~vc09I~
    	if (Dump.Y) Dump.println("Funcs.setClip rect="+Prect.toString());//~vc09I~
    	Pcanvas.save();                                       //~1Ah3I~//~vc09I~
    	Pcanvas.clipRect(Prect);                              //~1Ah3I~//~vc09I~
    	Pcanvas.restore();                                    //~1Ah3I~//~vc09I~
    }                                                              //~vc09I~
	public static void drawPosText(Canvas Pcanvas, char[] Pchars, int Pidx, int Pctr, float[] Pfposs, Paint Ppaint)//~vc12I~
    {                                                              //~vc12I~
    	if (Dump.Y) Dump.println("Funcs.drawPosText chars="+ Utils.toHexString(Pchars)+",idx="+Pidx+",ctr="+Pctr+",fpos="+Arrays.toString(Pfposs));//+vc12R~
        int sz=Pchars.length;                                      //~vc12I~
        for (int ii=Pidx;ii<Pidx+Pctr && ii<sz;ii++)               //~vc12I~
        {                                                          //~vc12I~
        	Pcanvas.drawText(Pchars,ii,1,Pfposs[ii*2],Pfposs[ii*2+1],Ppaint);//~vc12R~
        }                                                          //~vc12I~
    }                                                              //~vc12I~
}                                                                  //~vc09R~
