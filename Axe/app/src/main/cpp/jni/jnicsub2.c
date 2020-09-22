//*CID://+vb91R~:                             update#=  857;       //~vb91R~
//***************************************************************************//~2818I~
//* c subrtn for wxe                                               //~2818I~
//***************************************************************************//~2818I~
//vb91:170228 marge rh9j gxe to arm-gxe                            //~vb91I~
//				vb4A:160815 if UNICOMB UNPR mode and LIGATURE ON, write altch just after base char using GetTextExtentPoint32W//~vb91I~
//vak2:130822 Axe:ndk-r9 warning                                   //~vak2I~
//***************************************************************************//~vXXEI~
//***************************************************************************//~vXXEI~
#include <stdio.h>                                                 //~2907I~
#include <stdlib.h>                                                //~2907I~
#include <string.h>                                                //~2907I~
//*************************************************************    //~2907I~
#include <jnig.h>                                                  //~v@@@R~
#include <jnia.h>                                                  //~v@@@R~
#include <jnic2j.h>                                                //~v@@@I~
#include <jnicsub.h>                                               //~v@@@R~
                                                                   //~v@@@I~
#include <ulib.h>                                                  //~2907I~//~v@@@R~
#include <udbcschk.h>                                              //~v@@@I~
#include <uque.h>                                                  //~v@@@I~
#include <ufile.h>                                                 //~v@@@I~
#include <utrace.h>                                                //~v@@@I~
                                                                   //~v@@@I~
#include <xe.h>                                                    //~v@@@R~
#include <xescr.h>                                                 //~v@@@R~
#include <xefile.h>                                                //~v@@@I~
#include <xesub2.h>                                                //~v@@@I~
                                                                   //~v@@@I~
#include <gxe.h>                                                   //~v@@@I~
#include <xxedef.h>                                                //~v@@@R~
#include <xxexei.h>                                                //~v@@@R~
#include <xxecsub.h>                                               //~v@@@I~
                                                                   //~2818I~
//***************************************************************************//~2818I~
#define PANGO_SCALE 1                                              //~v@@@I~
////***************************************************************************//~vXXEI~//~v@@@R~
////* get font metrics                                               //~vXXEI~//~v@@@R~
////set monospace sw 0:none,1:monospace                            //~v@@@R~
////***************************************************************************//~vXXEI~//~v@@@R~
//void ugetfontmetrics(PangoFontDescription *Pfontdesc,int *Ppwidth,int *Ppheight,int *Ppmonospace)//~vXXER~//~v@@@R~
//{                                                                  //~vXXEI~//~v@@@R~
//    char *fontname;                                              //~v@@@R~
//    int monospace;                                               //~v@@@R~
////*******************************                                  //~vXXEI~//~v@@@R~
//    fontname=Pfontdesc->family_name;                             //~v@@@R~
//    c2j_ugetfontmetrics(fontname,Ppwidth,Ppheight,&monospace);   //~v@@@R~
//    UTRACEP("ugetfontmetrics w=%d,h=%d,monospace=%d\n",*Ppwidth,*Ppheight,*Ppmonospace);//~v@@@R~
//    *Ppmonospace=monospace==1;                                   //~v@@@R~
//    if (monospace<0)                                             //~v@@@R~
//        Gxxestat|=GXXES_FONTLIGATURE;                              //~va1MR~//~v@@@R~
//    else                                                           //~va1MR~//~v@@@R~
//        Gxxestat&=~GXXES_FONTLIGATURE;                             //~va1MR~//~v@@@R~
//    return;                                                        //~vXXEI~//~v@@@R~
//}//ugetfontmetrics                                                 //~vXXEI~//~v@@@R~
//===============================================================================//~v@@@I~
//return string width when write at once                           //~v@@@I~
//===============================================================================//~v@@@I~
int getgeometry(int Popt,PangoLayout *Pplayout,PangoGlyphGeometry **Ppgeom)//~v@@@R~
{                                                                  //~v@@@I~
	float *fwidthtb,fww,fstrwidth=0;	//width tbl                //~v@@@R~
    PangoGlyphGeometry *pgeom;                                     //~v@@@I~
    int sz,ctr,ii;                                                 //~v@@@R~
//******************                                               //~v@@@I~
    c2j_gettextwidths(0,Pplayout,&fwidthtb);	//get offset tbl using Paint.getTextWidts//~v@@@I~
    ctr=Pplayout->jni_ucsctr;                                      //~v@@@R~
    sz=ctr*sizeof(PangoGlyphGeometry);                             //~v@@@R~
    *Ppgeom=pgeom=getvoidtb(GETTB_GEOM,sz);                        //~v@@@I~
    memset(pgeom,0,sz);                                            //~v@@@I~
    for (ii=0;ii<ctr;ii++,pgeom++)                                 //~v@@@I~
    {                                                              //~v@@@I~
        fww=*fwidthtb++;                                           //~v@@@R~
        pgeom->width=fww;                                          //~v@@@I~
        fstrwidth+=fww;                                            //~v@@@I~
    }                                                              //~v@@@I~
    return (int)fstrwidth;                                         //~v@@@R~
}//getgeometry                                                     //~v@@@I~
//===============================================================================//~v@@@I~
//return string width when write at once                           //~v@@@I~
//===============================================================================//~v@@@I~
float getstrwidth(int Popt,PangoLayout *Pplayout)                  //~v@@@R~
{                                                                  //~v@@@I~
//	float *fwidthtb,fstrwidth=0;	//width tbl                    //~v@@@R~
//    int ctr,ii;                                                  //~v@@@R~
//******************                                               //~v@@@I~
//    c2j_gettextwidths(0,Pplayout,&fwidthtb);    //get offset tbl using Paint.getTextWidts//~v@@@R~
//    ctr=Pplayout->jni_ucsctr;                                    //~v@@@R~
//    for (ii=0;ii<ctr;ii++)                                       //~v@@@R~
//    {                                                            //~v@@@R~
//        fstrwidth+=*fwidthtb++;                                  //~v@@@R~
//    }                                                            //~v@@@R~
//    return (int)fstrwidth;                                       //~v@@@R~
	return Pplayout->jni_fstrwidth;                                //~v@@@R~
}//getstrwidth                                                     //~v@@@I~
//===============================================================================//~vX03M~
//set monospaceing                                                 //~vX03M~
//===============================================================================//~vX03M~
//void usetmonospace(PangoLayout *Pplayout,char *Ppdbcs,int Plen,int Pcellw)//~v79zR~
//void usetmonospace(PangoLayout *Pplayout,char *Ppdata,char *Ppdbcs,int Plen,int Pcellw)//~va1DR~
void usetmonospace(int Popt,PangoLayout *Pplayout,char *Ppdata,char *Ppdbcs,int Plen,int Pcellw)//~va1DI~
{                                                                  //~vX03M~
//    GSList *tmp_list;                                              //~vX03M~//~v@@@R~
//    PangoLayoutIter *iter;                                         //~vX03M~//~v@@@R~
//    PangoLayoutLine *line;                                         //~vX03M~//~v@@@R~
//    PangoLayoutRun *run;                                           //~vX03M~//~v@@@R~
//    PangoGlyphString *pglyphstr;                                   //~vX03M~//~v@@@R~
//    PangoGlyphInfo *pglyphinfo;                                    //~vX03M~//~v@@@R~
    PangoGlyphGeometry *pgeom;                                     //~vX03M~//~v@@@R~
    UCHAR *pdbcs,*pdbcse;                                          //~vX03M~//~va30R~//~v@@@R~
    char *pdata;                                                   //~v79zI~
    int ii;                                                        //~vX03M~
    int oldw,neww;                                                 //~v69VI~
#ifdef UTF8SUPPH                                                   //~v7axI~
	int dbcssz;                                                    //~v7axI~
#endif                                                             //~v7axI~
#ifdef UTF8UCS2                                                    //~va20I~
	int swutf8data;                                                //~va20I~
    int oldx,/*oldy,*/wwpango,w0ctr=0,glyphctr=0,swtoperr=0,combspan=0,wwtotal=0;//~vak2R~
    PangoGlyphGeometry *pgeomlast=0;                               //~vak2R~
#endif                                                             //~va20I~
    int ucsctr,putstringatonce;                                    //~v@@@R~
    float *fpostb;                                                 //~v@@@I~
//******************************                                   //~vX03M~
#ifdef UTF8UCS2                                                    //~va20I~
	swutf8data=Popt & USMSO_DDFMT;   //scr data is dd fmt,pdata is not lc,dont access//~va20I~
#endif                                                             //~va20I~
//	iter = pango_layout_get_iter(Pplayout);                        //~vX03R~//~v@@@R~
//  line=pango_layout_iter_get_line (iter);                        //~vX03M~//~v@@@R~
UTRACEP("usetmonospace opt=%x,len=%d\n",Popt,Plen);                //~va30R~
//  if (!line)  //split dbcs is set to layout                      //~v69VR~//~v@@@R~
//      return;                                                    //~v69VR~//~v@@@R~
//  tmp_list=line->runs;                                           //~vX03M~//~v@@@R~
//dprintf("list@@@=%p\n",tmp_list);                                //~v69VR~
  pdata=Ppdata;                                                    //~v79zI~
  pdbcs=(UCHAR*)Ppdbcs;                                                    //~v69VI~//~v@@@R~
  pdbcse=(UCHAR*)Ppdbcs+Plen;		//end addr;                            //~v69VI~//~v@@@R~
  ucsctr=Pplayout->jni_ucsctr;                                     //~v@@@R~
  fpostb=getvoidtb(GETTB_POSTB,sizeof(float)*2*ucsctr);            //~v@@@R~
  Pplayout->jni_fpostb=fpostb;                                     //~v@@@R~
//while(tmp_list)                                                  //~v69VR~//~v@@@R~
//{                                                                //~v69VI~//~v@@@R~
//  run=tmp_list->data;                                            //~vX03M~//~v@@@R~
//  if (!run)  //split dbcs is set to layout                       //~v69VR~//~v@@@R~
//      return;                                                    //~v69VR~//~v@@@R~
//  pglyphstr=run->glyphs;                                         //~vX03M~//~v@@@R~
//  if (!pglyphstr)  //split dbcs is set to layout                 //~v69VR~//~v@@@R~
//      return;                                                    //~v69VR~//~v@@@R~
//UTRACEP("temp list=%p\n",pglyphstr);                               //~va1cR~//~v@@@R~
    pgeom=Pplayout->jni_pgeom;                                     //~v@@@R~
	wwpango=Pcellw*PANGO_SCALE;                                    //~va30R~
    putstringatonce=1;                                             //~v@@@R~
//  for (ii=pglyphstr->num_glyphs,pglyphinfo=pglyphstr->glyphs;ii>0;ii--,pglyphinfo++)//~vX03M~//~v@@@R~
    for (ii=ucsctr;ii>0;ii--,pgeom++)                              //~v@@@R~
    {                                                              //~vX03M~
        glyphctr++;                                                //~va30R~
//      pgeom=&(pglyphinfo->geometry);                             //~vX03M~//~v@@@R~
        pgeomlast=pgeom;                                           //~va30R~
	    oldw=pgeom->width;                                         //~v69VI~
	    oldx=pgeom->x_offset;                                      //~va30R~
//      oldy=pgeom->y_offset;                                      //~vak2R~
//UTRACEP("geometry ii=%d,offs_x=%d,offs_y=%d,width=%d cellw=%d\n",ii,pgeom->x_offset,pgeom->y_offset,pgeom->width,Pcellw);//~v@@@R~
//#ifdef UTF8SUPPH                                                   //~va1cR~//~v@@@R~
		dbcssz=1;	//sbcs                                         //~va1cR~
//#ifdef UTF8UCS2                                                    //~va20I~//~v@@@R~
        if ((swutf8data||*pdata>=0x80)                             //~va20I~
//#else                                                              //~va20I~//~v@@@R~
//        if (*pdata>=0x80                                           //~va1cR~//~v@@@R~
//#endif                                                             //~va20I~//~v@@@R~
//*after ucs dbcsid is converted to locale dbcsid                  //~va20I~
        &&  *pdbcs==UDBCSCHK_DBCS1ST                               //~va1cR~
        &&  pdbcs+(dbcssz=XESUB_DBCSSPLITCTR(pdbcs,(int)((ULONG)pdbcse-(ULONG)pdbcs),0))<=pdbcse//~va1cR~
        )                                                          //~va1cR~
        {                                                          //~va1cR~
            neww=                                                  //~va1cR~
	        pgeom->width=(Pcellw+Pcellw)*PANGO_SCALE;              //~va1cR~
          	if (Popt & USMSO_WWSCRPRT)	//wysiwig print            //~va1DI~
            	pgeom->width=(Pcellw*dbcssz)*PANGO_SCALE;	//for dbcs padding space//~va1DR~
            pdata+=dbcssz; //print width is 2 column but data is 3-4 byte//~va1cR~
            pdbcs+=dbcssz; //after 3/4 byte dbcs "." is padded by csublocale2utfscr//~va1cR~
        }                                                          //~va1cR~
//#else                                                              //~va1cR~//~v@@@R~
//        if (*pdata>=0x80 && *pdbcs==UDBCSCHK_DBCS1ST && pdbcs+1<pdbcse)  //require  2byte write for DBCS//~v79zI~//~v@@@R~
//        {                                                          //~vX03M~//~v@@@R~
//            neww=                                                  //~v69VI~//~v@@@R~
//            pgeom->width=(Pcellw+Pcellw)*PANGO_SCALE;              //~vX03M~//~v@@@R~
//            pdata+=2;                                              //~v79zI~//~v@@@R~
//            pdbcs+=2;                                              //~vX03M~//~v@@@R~
//        }                                                          //~vX03M~//~v@@@R~
//#endif                                                             //~va1cR~//~v@@@R~
        else                                                       //~vX03M~
        {                                                          //~vX03M~
        	neww=                                                  //~v69VI~
	        pgeom->width=Pcellw*PANGO_SCALE;                       //~vX03M~
            pdata++;                                               //~v79zI~
            pdbcs++;                                               //~vX03M~
        }                                                          //~vX03M~
        if (oldw!=neww)	//charwidth!=cell width(*2)                //~v@@@R~
        	putstringatonce=0;                                     //~v@@@R~
        if (oldw)                                                  //~va30R~
        	pgeom->x_offset=(neww-oldw)/2;                         //~va30R~
        if (Popt & USMSO_COMBINEOF)	//1 column for each combining char//~va30R~
        {                                                          //~va30R~
	        if (!oldw)	//width=0                                  //~va30R~
            {                                                      //~va30R~
		        pgeom->width=wwpango;                              //~va30R~
                while (oldx<=0) { oldx+=wwpango; }                 //~va30R~
		        pgeom->x_offset=oldx;                              //~va30R~
	            w0ctr++;                                           //~va30R~
            }                                                      //~va30R~
            else                                                   //~va30R~
            if (w0ctr)  //after combining char                     //~va30R~
            {                                                      //~va30R~
                w0ctr=0;                                           //~va30R~
            }                                                      //~va30R~
        }                                                          //~va30R~
        else                                                       //~va30R~
        if (Popt & USMSO_COMBINEON)	//combine width 0 char         //~va30R~
        {                                                          //~va30R~
	        if (!oldw)	//width=0                                  //~va30R~
            {                                                      //~va30R~
		        pgeom->x_offset=oldx;	//keep original            //~va30R~
		        pgeom->width=0;	//keep original                    //~va30R~
            	if (!w0ctr)	//combine start                        //~va30R~
                {                                                  //~va30R~
                    combspan=0;                                    //~va30R~
                	swtoperr=(glyphctr==1);	//err if str top is combine chae//~va30R~
                    if (swtoperr)                                  //~va30R~
                    {                                              //~va30R~
		        		pgeom->x_offset+=wwpango;	//offset from next cell right boundary//~va30R~
		        		pgeom->width=wwpango;                      //~va30R~
                    }                                              //~va30R~
                    else                                           //~va30R~
                    	combspan=wwpango;	//span to skip to net no combining char//~va30R~
                }                                                  //~va30R~
                else	//2nd combining                            //~va30R~
                {                                                  //~va30R~
	                combspan+=wwpango;  //span to next noncombining char//~va30R~
                    if (swtoperr)                                  //~va30R~
                        pgeom->x_offset+=wwpango;                  //~va30R~
                }                                                  //~va30R~
	            w0ctr++;                                           //~va30R~
            }                                                      //~va30R~
            else    //not combining char                           //~va30R~
            if (w0ctr)  //next of non combining                    //~va30R~
            {                                                      //~va30R~
		        pgeom->width=combspan+wwpango;	//for width0 and itself//~va30R~
		        pgeom->x_offset=pgeom->width-neww+(neww-oldw)/2;   //~va30R~
                w0ctr=0;                                           //~va30R~
            }                                                      //~va30R~
        }                                                          //~va30R~
//UTRACEP("geometry new glyphctr=%d width=%d,offsx=%d,offsy=%d,combspan=%d,swtoperr=%d\n",glyphctr,pgeom->width,pgeom->x_offset,pgeom->y_offset,combspan,swtoperr);//~v@@@R~
		*fpostb++=(float)wwtotal;   //char offset                  //~v@@@R~
		*fpostb++=0;	//y-pos ,set at AxeScreen                  //~v@@@I~
		wwtotal+=pgeom->width;                                     //~va30R~
    }                                                              //~vX03M~
//  tmp_list=tmp_list->next;                                       //~v69VI~//~v@@@R~
//}//runs                                                          //~v69VI~//~v@@@R~
  	if (glyphctr<Plen	//ligatured occured                        //~va30R~
    &&  glyphctr                                                   //~va30R~
    &&  Popt & USMSO_COMBINESP	//add space whe ligture occued     //~va30R~
    )                                                              //~va30R~
    {                                                              //~va30R~
      if (pgeomlast)                                               //~vak2R~
      {                                                            //~vak2I~
  		pgeomlast->width=Plen*wwpango-wwtotal;                     //~va30R~
		UTRACEP("geometry last width after ligature width=%d,glyphctr=%d,len=%d\n",pgeomlast->width,glyphctr,Plen);//~va30R~
      }                                                            //~vak2I~
    }                                                              //~va30R~
    Pplayout->jni_ligature=putstringatonce!=0;	//for gdk_draw_layout_with_color;no need to char position modification//~v@@@R~
// 	pango_layout_iter_free (iter);                                 //~vX03M~//~v@@@R~
  	return;                                                        //~vX03M~
}//usetmonospace                                                   //~vX03I~
//===============================================================================//~va3iI~
//set monospaceing for ligature mode (utf8 file)                   //~va3iI~
//leave spacing to pango                                           //~va3iI~
//  except, adjust last for next string column position            //~va3iI~
//  (combining char under nocombine mode is split by color attr at xefile23)//~va3iI~
//===============================================================================//~va3iI~
int usetmonospace_ligature(int Popt,PangoLayout *Pplayout,         //~va3iR~
							char *Ppdataxx/*not used*/,char *Ppdbcsxx/*not used*/,//~va3iI~
//  						int Plen/*column width*/,int Pcellw)   //~va3iI~//~vb91R~
  							int Plen/*column width*/,int Pcellw,int *Pstrwidth)//~vb4AR~//~vb91R~
{                                                                  //~va3iI~
////    GSList *tmp_list;                                              //~va3iI~//~v@@@R~
////    PangoLayoutIter *iter;                                         //~va3iI~//~v@@@R~
////    PangoLayoutLine *line;                                         //~va3iI~//~v@@@R~
////    PangoLayoutRun *run;                                           //~va3iI~//~v@@@R~
////    PangoGlyphString *pglyphstr;                                   //~va3iI~//~v@@@R~
////    PangoGlyphInfo *pglyphinfo;                                    //~va3iI~//~v@@@R~
//    PangoGlyphGeometry *pgeom,*pgeomlast;                          //~va3iI~//~v@@@R~
//    PangoGlyphGeometry *pgeom0;                                  //~v@@@R~
//    int ii,wwpango,glyphctr=0,wwtotal=0,wwglyph;                   //~va3iR~//~v@@@R~
//    int diff,ccoffs,difftotal;                                     //~va3uR~//~v@@@R~
//    int ucsctr;                                                  //~v@@@R~
//    int putstringatonce=1;                                       //~v@@@R~
//    float *fpostb,*fpostb0;                                      //~v@@@R~
////******************************                                   //~va3iI~//~v@@@R~
////    iter = pango_layout_get_iter(Pplayout);                        //~va3iI~//~v@@@R~
////    line=pango_layout_iter_get_line (iter);                        //~va3iI~//~v@@@R~
//UTRACEP("usetmonospace_ligature opt=%x,len=%d\n",Popt,Plen);       //~va3iR~//~v@@@R~
////dprintf("usetmonospace_ligature opt=%x,len=%d\n",Popt,Plen);       //~va3iI~//~v@@@R~
////    if (!line)  //split dbcs is set to layout                      //~va3iI~//~v@@@R~
////        return 4;                                                  //~va3iR~//~v@@@R~
////    tmp_list=line->runs;                                           //~va3iI~//~v@@@R~
////dprintf("list@@@=%p\n",tmp_list);                                //~va3iI~//~v@@@R~
//    wwpango=Pcellw*PANGO_SCALE;                                    //~va3iI~//~v@@@R~
////    while(tmp_list)                                                //~va3iI~//~v@@@R~
////    {                                                              //~va3iI~//~v@@@R~
////        run=tmp_list->data;                                        //~va3iI~//~v@@@R~
////dprintf("run @@@=%p\n",run);                                     //~va3iI~//~v@@@R~
////        if (!run)  //split dbcs is set to layout                   //~va3iI~//~v@@@R~
////            break;                                                 //~va3iI~//~v@@@R~
////        pglyphstr=run->glyphs;                                     //~va3iI~//~v@@@R~
////        if (!pglyphstr)  //split dbcs is set to layout             //~va3iI~//~v@@@R~
////            reak;                                                 //~va3iI~//~v@@@R~
////UTRACEP("temp list=%p\n",pglyphstr);                               //~va3iI~//~v@@@R~
//        getgeometry(0,Pplayout,&pgeom); //get offset tbl using Paint.getTextWidts//~v@@@R~
//        pgeom0=pgeom;                                            //~v@@@R~
//        ucsctr=Pplayout->jni_ucsctr;                             //~v@@@R~
//        fpostb0=fpostb=getvoidtb(GETTB_POSTB,sizeof(float)*2*ucsctr);//~v@@@R~
////      for (ii=pglyphstr->num_glyphs,pglyphinfo=pglyphstr->glyphs;ii>0;ii--,pglyphinfo++)//~va3iI~//~v@@@R~
//        for (ii=ucsctr;ii>0;ii--,pgeom++)                        //~v@@@R~
//        {                                                          //~va3iI~//~v@@@R~
////          pgeom=&(pglyphinfo->geometry);                         //~va3iI~//~v@@@R~
//            pgeomlast=pgeom;                                       //~va3iI~//~v@@@R~
//UTRACEP("geometry ii=%d,offs_x=%d,offs_y=%d,width=%d cellw=%d\n",ii,pgeom->x_offset,pgeom->y_offset,pgeom->width,Pcellw);//~va3iI~//~v@@@R~
//            wwglyph=pgeom->width;                                  //~va3iI~//~v@@@R~
////          if (!wwglyph && !glyphctr)  //start with combining char//~va3iI~//~va3uR~//~v@@@R~
//            if (!wwglyph)   //combining char                       //~va3uI~//~v@@@R~
//            if (!glyphctr   //start with combining char            //~va3uI~//~v@@@R~
//            ||  (Popt & USMSO_COMBINEOF)    //split mode           //~va3uI~//~v@@@R~
//            )                                                      //~va3uI~//~v@@@R~
//            {                                                      //~va3iI~//~v@@@R~
//                ccoffs=pgeom->x_offset;         //offset from roght boundary of prev cell//~va3uM~//~v@@@R~
//              if (ccoffs<0)                                        //~va3uI~//~v@@@R~
//              {                                                    //~va3uI~//~v@@@R~
//                    wwglyph=wwpango+ccoffs; //offset is right end? //~va3uR~//~v@@@R~
//                    pgeom->width=wwglyph;   //it may be combining char glyph size//~va3uI~//~v@@@R~
//                    pgeom->x_offset=wwglyph;                       //~va3uR~//~v@@@R~
//              }                                                    //~va3uI~//~v@@@R~
//              else                                                 //~va3uI~//~v@@@R~
//              {                                                    //~va3uI~//~v@@@R~
//                pgeom->width=wwglyph=wwpango;   //space for next char//~va3iR~//~v@@@R~
//                pgeom->x_offset+=wwglyph;       //required to see it//~va3iR~//~v@@@R~
//              }                                                    //~va3uI~//~v@@@R~
//                if (Popt & USMSO_COMBINEOF) //split mode           //~va3uI~//~v@@@R~
//                    pgeom->y_offset=0;  //up when continued combining char//~va3uI~//~v@@@R~
//            }                                                      //~va3iI~//~v@@@R~
//            *fpostb=(float)wwtotal;   //char offset              //~v@@@R~
//            fpostb+=2;           //skip set y                    //~v@@@R~
//            wwtotal+=wwglyph;                                      //~va3iR~//~v@@@R~
//            glyphctr++;                                            //~va3iM~//~v@@@R~
//UTRACEP("geometry gliphctr=%d, new width==%d offs==%d totalwidth=%d\n",glyphctr,pgeom->width,pgeom->x_offset,wwtotal);//~va3iR~//~va3uR~//~v@@@R~
//        }                                                          //~va3iI~//~v@@@R~
////        tmp_list=tmp_list->next;                                   //~va3iI~//~v@@@R~
////    }//runs                                                        //~va3iI~//~v@@@R~
//    if (glyphctr)                                                  //~va3iI~//~v@@@R~
//    {                                                              //~va3iI~//~v@@@R~
//        diff=Plen*wwpango-wwtotal;                                 //~va3uI~//~v@@@R~
//UTRACEP("geometry last before adjusted new width==%d offs==%d totalwidth=%d,diff=%d\n",pgeomlast->width,pgeom->x_offset,wwtotal,diff);//~va3uI~//~v@@@R~
//        if (diff>0)                                                //~va3uI~//~v@@@R~
//            pgeomlast->width=diff;                                 //~va3uI~//~v@@@R~
//        else // overflow, do monospacing                           //~va3uR~//~v@@@R~
//        {                                                          //~va3uI~//~v@@@R~
//            putstringatonce=0;                                   //~v@@@R~
//            wwtotal=0;                                             //~va3uI~//~v@@@R~
////          tmp_list=line->runs;                                   //~va3uI~//~v@@@R~
//            difftotal=-diff;                                       //~va3uR~//~v@@@R~
////          while(tmp_list)                                        //~va3uI~//~v@@@R~
////          {                                                      //~va3uI~//~v@@@R~
////              run=tmp_list->data;                                //~va3uI~//~v@@@R~
////              if (!run)  //split dbcs is set to layout           //~va3uI~//~v@@@R~
////                  break;                                         //~va3uI~//~v@@@R~
////              pglyphstr=run->glyphs;                             //~va3uI~//~v@@@R~
////              if (!pglyphstr)  //split dbcs is set to layout     //~va3uI~//~v@@@R~
////                  break;                                         //~va3uI~//~v@@@R~
////              for (ii=pglyphstr->num_glyphs,pglyphinfo=pglyphstr->glyphs;ii>0;ii--,pglyphinfo++)//~va3uI~//~v@@@R~
//                pgeom=pgeom0;                                    //~v@@@R~
//                fpostb=fpostb0;                                  //~v@@@R~
//                for (ii=ucsctr;ii>0;ii--,pgeom++)                //~v@@@R~
//                {                                                  //~va3uI~//~v@@@R~
////                  pgeom=&(pglyphinfo->geometry);                 //~va3uI~//~v@@@R~
//                    wwglyph=pgeom->width;                          //~va3uI~//~v@@@R~
//                    if (wwglyph>wwpango)                           //~va3uI~//~v@@@R~
//                    {                                              //~va3uI~//~v@@@R~
//                        diff=wwglyph-wwpango;                      //~va3uI~//~v@@@R~
//                        difftotal-=diff;                           //~va3uI~//~v@@@R~
//UTRACEP("geometry ii=%d,adjust old offs_x=%d,width=%d\n",ii,pgeom->x_offset,pgeom->width);//~va3uI~//~v@@@R~
//                        pgeom->width=wwpango;                      //~va3uI~//~v@@@R~
//                        if (pgeom->x_offset>wwpango)    //combining ch//~va3uI~//~v@@@R~
//                            pgeom->x_offset=wwpango;               //~va3uI~//~v@@@R~
//UTRACEP("geometry ii=%d,adjust offs_x=%d,width=%d\n",ii,pgeom->x_offset,pgeom->width);//~va3uI~//~v@@@R~
//                    }                                              //~va3uI~//~v@@@R~
//                    *fpostb=(float)wwtotal;   //char offset      //~v@@@R~
//                    fpostb+=2;           //skip set y            //~v@@@R~
//                    wwtotal+=wwglyph;                              //~va3uI~//~v@@@R~
//                    if (difftotal<=0)                              //~va3uI~//~v@@@R~
//                        break;                                     //~va3uI~//~v@@@R~
//                }                                                  //~va3uI~//~v@@@R~
////              if (difftotal<=0)                                  //~va3uI~//~v@@@R~
////                  break;                                         //~va3uI~//~v@@@R~
////              tmp_list=tmp_list->next;                           //~va3uI~//~v@@@R~
////          }//runs                                                //~va3uI~//~v@@@R~
//            pgeomlast->width+=-difftotal;                          //~va3uR~//~v@@@R~
//UTRACEP("geometry last adjusted new width==%d offs==%d totalwidth=%d,diff=%d\n",pgeomlast->width,pgeom->x_offset,wwtotal,diff);//~va3uI~//~v@@@R~
//        }                                                          //~va3uI~//~v@@@R~
//    }                                                              //~va3iI~//~v@@@R~
////    pango_layout_iter_free (iter);                                 //~va3iI~//~v@@@R~
//    Pplayout->jni_ligature=1;   //for gdk_draw_layout_with_color;no need to char position modification//~v@@@R~
//    return 0;                                                      //~va3iI~//~v@@@R~
                                                                   //~v@@@I~
    float fstrwidth,fcolwidth;                                     //~v@@@I~
//******************************                                   //~v@@@I~
UTRACEP("usetmonospace_ligature opt=%x,len=%d\n",Popt,Plen);       //~v@@@I~
    fstrwidth=getstrwidth(0,Pplayout);                             //~v@@@R~
    fcolwidth=(float)(Plen*Pcellw);                                //~v@@@I~
    if (fstrwidth>fcolwidth)                                       //~v@@@I~
    {                                                              //~v@@@I~
	    Pplayout->jni_scale=fcolwidth/fstrwidth;                   //~v@@@I~
    	Pplayout->jni_ligature=2;   //apply scale                  //~v@@@I~
    }                                                              //~v@@@I~
    else                                                           //~v@@@I~
    	Pplayout->jni_ligature=1;   //drawtext at once             //~v@@@I~
    if (Pstrwidth)                                                 //~vb91I~
    	*Pstrwidth=(int)(fstrwidth+0.999);                         //+vb91R~
UTRACEP("usetmonospace_ligaturjni_ligature=%d,strwidth=%f,colwidth=%f\n",Pplayout->jni_ligature,fstrwidth,fcolwidth);//~v@@@R~//~vb91R~
    return 0;                                                      //~v@@@I~
}//usetmonospace_ligature                                          //~va3iI~
//===============================================================================//~v@@@I~
//get layoutinfo:glyphctr,width0 glyph                             //~v@@@I~
//===============================================================================//~v@@@I~
int csublyoutinfo(int Popt,PangoLayout *Pplayout,int *Pretinfo)    //~v@@@I~
{                                                                  //~v@@@I~
    int glyphctr;                                                  //~v@@@I~
//******************************                                   //~v@@@I~
UTRACEP("csublayoutinfo\n");                                       //~v@@@I~
    glyphctr=Pplayout->jni_ucsctr;                                 //~v@@@I~
    Pretinfo[CSUBLI_CTR]=glyphctr;                                 //~v@@@I~
UTRACEP("csublayoutinfo glyphctr=%d\n",glyphctr);                  //~v@@@I~
  	return 0;                                                      //~v@@@I~
}//csublayoutinfo                                                  //~v@@@I~
