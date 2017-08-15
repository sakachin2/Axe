//*CID://+vay0R~:                                   update#=   37; //~vay0R~
//**************************************************************** //~1610I~
//j2c.h                                                            //~1820R~
//**************************************************************** //~1610I~
//vay0:140710 (Axe)jni exception handling                          //~vay0I~
//vab7:120122 (Axe)Android4:getTextWidth returns width=0 if request count is too large//~vab7I~
//**************************************************************** //~vab7I~
#define KBDACTION_UP      0                                        //~1621I~//~1801R~
#define KBDACTION_DOWN    1                                        //~1621R~//~1801I~
#define KBDACTION_CHAR    2                                        //~1616R~
#define KBDACTION_UNICODE 3                                        //~1616I~
                                                                   //~1611I~
#define MOUSEACTION_DOWN    0                                      //~1621I~
#define MOUSEACTION_UP      1                                      //~1621I~
#define MOUSEACTION_MOVE    2                                      //~1621I~
#define MOUSEACTION_CANCEL  3                                      //~1621I~
#define MOUSEACTION_DBLCLICK 4                                     //~1A04I~
#define MOUSEACTION_DRAG    5                                      //~1A04I~
                                                                   //~1621I~
#define KBF_SHIFT          (GDK_SHIFT_MASK<<16)     //0x00010000   //~1616R~
#define KBF_CONTROL        (GDK_CONTROL_MASK<<16) 	//0x00040000;  //~1613R~
#define KBF_ALT            (GDK_MOD1_MASK<<16)      //0x00080000   //~1616R~
#define KBF_ALTGR          (GDK_MOD5_MASK<<16)      //0x00800000   //~1616I~
#define KBF_RIGHTCONTROL   0x04000000                              //~1613R~
#define KBF_UNICODE        0x40000000     //2byte unicode support  //~1613R~
                                                                   //~1803I~
#define COLOR_INT2XXECOLOR(xxecolor,color) \
	xxecolor.red=((((color)>>16) & 0xff)<<8), /*red:ushort*/\
	xxecolor.green=((((color)>>8 ) & 0xff)<<8), \
	xxecolor.blue=(( color      & 0xff)<<8)                        //~1803R~
//********************************                                 //~1614I~
#define API_ICE_CREAM_SANDWICH 14 //android 4, 15=>4.0.3           //~vab7R~
#define DEBUGO_ABEND     0x01      //abend by double Esc           //+vay0R~
#define DEBUGO_UERREXIT  0x02      //same as AxeG.java             //+vay0R~
