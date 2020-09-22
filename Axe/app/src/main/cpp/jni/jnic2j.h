//*CID://+varhR~:                                   update#=   40; //~varhR~
//**************************************************************** //~1610I~
//jnic2j.h                                                         //~1621R~
//**************************************************************** //~1610I~
//vbrh:200824 (AXE)notify ruler changed to Axe dialog              //~varhI~
//vay0:140710 (Axe)jni exception handling                          //~vay0I~
//**************************************************************** //~vac6I~
#ifdef ARM                                                         //~1621I~
//*jnig:dialog_run                                                 //~1826I~
#define BTN_OK  1                                                  //~1622R~//~1826M~
#define BTN_NO  2                                                  //~1622R~//~1826M~
#define BTN_CAN 3                                                  //~1622R~//~1826M~
                                                                   //~1826I~
//*jnig:draw_line                                                  //~1826I~
#define DRAWLINE_DIRECT  1                                         //~1826I~
#define DRAWLINE_CARET   2                                         //~1826I~
#define DRAWLINE_RULER   4                                         //~1826I~
//*****************************************                        //~1826I~
void jni_exit(int Pexitcode);                                      //~1621R~
void c2j_ugetfontmetrics(char *Pfontname,int *Ppwidth,int *Ppheight,int *Ppmonospace);//~1719I~
void c2j_gettextwidths(int Popt,PangoLayout *Pplayout,float **Ppfwidthtb);//~1719I~
int c2j_getvfatlist(char **Pplist);                                //~1A23R~
int jnic2j_androsend(int Popt,char *Pfnm,char *Pattachfnm);        //~1A15R~
int c2j_msgboxACRA(char *Ppmsg);                                   //~vay0I~
int c2j_notifyRulerMode(int Prulermode);                           //+varhR~
#endif //ARM                                                       //~1621I~
