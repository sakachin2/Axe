//*CID://+v@@@R~:                             update#=  131;       //~v@@@I~
//*************************************************                //~v@@@I~
//v@@@:110610 Android(-DARM)                                       //~v@@@I~
//**************************************************************   //~v@@@I~
#ifdef ARM                                                         //~v@@@M~
                                                                   //~v@@@I~
    #include <stdio.h>                                             //~v@@@I~
                                                                   //~v@@@I~
    #define gdouble 				double                         //~v@@@R~
    #define gchar   				char                           //~v@@@R~
    #define gpointer    			void*                          //~v@@@I~
    #define gboolean    			int                            //~v@@@R~
    #define gint                    int                            //~v@@@R~
    #define gint8                   char                           //~v@@@I~
    #define guint32     			unsigned int                   //~v@@@I~
    #define guint16     			unsigned short                 //~v@@@R~
    #define guint8      			unsigned char                  //~v@@@I~
    #define guint        			unsigned int                   //+v@@@I~
    #define TRUE                    1                              //~v@@@I~
    #define FALSE                   0                              //~v@@@R~
//*******************************************                      //~v@@@I~
    #define GnomePrintConfig             void                      //~v@@@M~
    #define GnomePrintContext            void                      //~v@@@M~
    #define GnomePrintJob                void                      //~v@@@M~
    #define GnomeFont                    void                      //~v@@@M~
    #define GError                       void                      //~v@@@M~
                                                                   //~v@@@M~
    #define PangoFontDescription         void                      //~v@@@M~
    #define PangoAttrList                void                      //~v@@@M~
    #define PangoLayout                  void                      //~v@@@M~
    #define PangoContext                 void                      //~v@@@M~
    #define PangoLanguage                void                      //~v@@@M~
                                                                   //~v@@@M~
    #define GtkWidget                    void                      //~v@@@M~
    #define GtkButton                    void                      //~v@@@M~
    #define GtkEditable                  void                      //~v@@@M~
    #define GtkClipboard                 void                      //~v@@@M~
    #define GtkIMContext                 void                      //~v@@@M~
    #define GtkSelectionData             void                      //~v@@@M~
    #define GtkMenuItem                  void                      //~v@@@M~
    #define GtkClipboardTextReceivedFunc void*                     //~v@@@M~
                                                                   //~v@@@M~
	#define GdkEventType                 int                       //~v@@@M~
	#define GdkWindow                    void                      //~v@@@I~
    #define GdkPixmap                    void                      //~v@@@M~
    #define GdkGC                        void                      //~v@@@M~
    #define GdkColormap                  void                      //~v@@@M~
    #define GdkDragContext               void                      //~v@@@M~
    #define GdkEvent                     void                      //~v@@@M~
    #define GdkEventFocus                void                      //~v@@@M~
    #define GdkEventConfigure            void                      //~v@@@M~
    #define GdkEventExpose               void                      //~v@@@M~
    #define GdkEventButton               void                      //~v@@@M~
    #define GdkEventScroll               void                      //~v@@@M~
    #define GdkEventCrossing             void                      //~v@@@M~
    #define GdkEventMotion               void                      //~v@@@M~
    #define GdkDragContext               void                      //~v@@@M~
    #define GdkEventClient               void                      //~v@@@M~
                                                                   //~v@@@I~
//*copy from  /usr/include/gtk-2.0/gdk/gdkcolor.h *****************//~v@@@R~
struct _GdkColor                                                   //~v@@@I~
{                                                                  //~v@@@I~
  guint32 pixel;                                                   //~v@@@I~
  guint16 red;                                                     //~v@@@I~
  guint16 green;                                                   //~v@@@I~
  guint16 blue;                                                    //~v@@@I~
};                                                                 //~v@@@I~
	typedef struct _GdkColor	      GdkColor;                    //~v@@I~//~v@@@R~
                                                                   //~v@@@I~
//*copy from  /usr/include/gtk-2.0/gdk/gdktypes.h *****************//~v@@@R~
/* Types of modifiers.                                             //~v@@@I~
 */                                                                //~v@@@I~
typedef enum                                                       //~v@@@I~
{                                                                  //~v@@@I~
  GDK_SHIFT_MASK    = 1 << 0,                                      //~v@@@I~
  GDK_LOCK_MASK	    = 1 << 1,                                      //~v@@@I~
  GDK_CONTROL_MASK  = 1 << 2,                                      //~v@@@I~
  GDK_MOD1_MASK	    = 1 << 3,                                      //~v@@@I~
  GDK_MOD2_MASK	    = 1 << 4,                                      //~v@@@I~
  GDK_MOD3_MASK	    = 1 << 5,                                      //~v@@@I~
  GDK_MOD4_MASK	    = 1 << 6,                                      //~v@@@I~
  GDK_MOD5_MASK	    = 1 << 7,                                      //~v@@@I~
  GDK_BUTTON1_MASK  = 1 << 8,                                      //~v@@@I~
  GDK_BUTTON2_MASK  = 1 << 9,                                      //~v@@@I~
  GDK_BUTTON3_MASK  = 1 << 10,                                     //~v@@@I~
  GDK_BUTTON4_MASK  = 1 << 11,                                     //~v@@@I~
  GDK_BUTTON5_MASK  = 1 << 12,                                     //~v@@@I~
                                                                   //~v@@@I~
  /* The next few modifiers are used by XKB, so we skip to the end.//~v@@@I~
   * Bits 15 - 25 are currently unused. Bit 29 is used internally. //~v@@@I~
   */                                                              //~v@@@I~
                                                                   //~v@@@I~
  GDK_SUPER_MASK    = 1 << 26,                                     //~v@@@I~
  GDK_HYPER_MASK    = 1 << 27,                                     //~v@@@I~
  GDK_META_MASK     = 1 << 28,                                     //~v@@@I~
                                                                   //~v@@@I~
  GDK_RELEASE_MASK  = 1 << 30,                                     //~v@@@I~
                                                                   //~v@@@I~
  GDK_MODIFIER_MASK = 0x5c001fff                                   //~v@@@I~
} GdkModifierType;                                                 //~v@@@I~
                                                                   //~v@@@I~
struct _GdkPoint                                                   //~v@@@I~
{                                                                  //~v@@@I~
  gint x;                                                          //~v@@@I~
  gint y;                                                          //~v@@@I~
};                                                                 //~v@@@I~
                                                                   //~v@@@I~
//*copy from  /usr/include/gtk-2.0/gdk/gdkevents.h *****************//~v@@@I~
typedef struct _GdkEventKey	    GdkEventKey;                       //~v@@@I~
struct _GdkEventKey                                                //~v@@@I~
{                                                                  //~v@@@I~
  GdkEventType type;                                               //~v@@@I~
  GdkWindow *window;                                               //~v@@@I~
  gint8 send_event;                                                //~v@@@I~
  guint32 time;                                                    //~v@@@I~
  guint state;                                                     //~v@@@I~
  guint keyval;                                                    //~v@@@I~
  gint length;                                                     //~v@@@I~
  gchar *string;                                                   //~v@@@I~
  guint16 hardware_keycode;                                        //~v@@@I~
  guint8 group;                                                    //~v@@@I~
  guint is_modifier : 1;                                           //~v@@@I~
};                                                                 //~v@@@I~
//**************************************************************   //~v@@@R~
    #include <gdkkeysyms.h>                                        //~v@@@R~
//**************************************************************   //~v@@@I~
#endif                                                             //~v@@@M~
