//*CID://+v@@@R~:                             update#=  317;       //~v@@@I~
//*************************************************                //~v@@@I~
//v@@@:110610 Android(-DARM)                                       //~v@@@I~
//**************************************************************   //~v@@@I~
#ifdef ARM                                                         //~v@@@M~
                                                                   //~v@@@I~
    #include <stdio.h>                                             //~v@@@I~
    #include <string.h>                                            //~v@@@I~
                                                                   //~v@@@I~
	#define exit(n)	   gtk_main_quit()                             //~v@@@R~
                                                                   //~v@@@I~
    #include <jni.h>                                               //~v@@@I~
                                                                   //~v@@@I~
//*********************************************                    //~v@@@I~
#define GDKCOLOR2COLOR(gdkc)    /*ushort(16bit)-->uchar(8bit)*/\
        (  0xff000000                                          \
		| (( (gdkc)->red>>8 )<<16)                            \
		| (( (gdkc)->green>>8 )<<8)                           \
		| (( (gdkc)->blue>>8 ))                               \
        )                                                          //~v@@@I~
//*********************************************                    //~v@@@I~
//*defines                                                         //~v@@@I~
//*********************************************                    //~v@@@I~
//********                                                         //~v@@@I~
//*glib  *                                                         //~v@@@I~
//********                                                         //~v@@@I~
    #define gdouble 				double                         //~v@@@R~
    #define gchar   				char                           //~v@@@R~
    #define gpointer    			void*                          //~v@@@I~
    #define gboolean    			int                            //~v@@@R~
    #define gint                    int                            //~v@@@R~
    #define gint8                   char                           //~v@@@I~
    #define gint16                  short                          //~v@@@I~
    #define guint32     			unsigned int                   //~v@@@I~
    #define guint16     			unsigned short                 //~v@@@R~
    #define guint8      			unsigned char                  //~v@@@I~
    #define guchar      			unsigned char                  //~v@@@I~
    #define guint        			unsigned int                   //~v@@@I~
    #define gushort        			unsigned short                 //~v@@@I~
	typedef guint32 gunichar;                                      //~v@@@I~
	typedef guint16 gunichar2;                                     //~v@@@I~
	typedef signed int gssize;       //lib/.../gconfig.h           //~v@@@I~
	typedef unsigned int gsize;      //lib/.../gconfig.h           //~v@@@I~
    #define TRUE                    1                              //~v@@@I~
    #define FALSE                   0                              //~v@@@R~
                                                                   //~v@@@I~
    #ifdef G_DISABLE_CONST_RETURNS                                 //~v@@@I~
    #define G_CONST_RETURN                                         //~v@@@I~
    #else                                                          //~v@@@I~
    #define G_CONST_RETURN const                                   //~v@@@I~
    #endif                                                         //~v@@@I~
    #ifndef GSEAL                                                  //~v@@@I~
    /* introduce GSEAL() here for all of Gdk and Gtk+ without the need to modify GLib *///~v@@@I~
    #  ifdef GSEAL_ENABLE                                          //~v@@@I~
    #    define GSEAL(ident)      _g_sealed__ ## ident             //~v@@@I~
    #  else                                                        //~v@@@I~
    #    define GSEAL(ident)      ident                            //~v@@@I~
    #  endif                                                       //~v@@@I~
    #endif /* !GSEAL */                                            //~v@@@I~
    #define GUINT_TO_POINTER(u) ((gpointer)  (u))   //lib/.../glibconfig.h//~v@@@I~
                                                                   //~v@@@I~
    #define GnomePrintConfig             void                      //~v@@@M~
    #define GnomePrintContext            void                      //~v@@@M~
    #define GnomePrintJob                void                      //~v@@@M~
    #define GnomeFont                    void                      //~v@@@M~
    #define GError                       void                      //~v@@@M~
    #define GIConv                       gpointer                  //~v@@@R~
    #define GList                        void                      //~v@@@I~
    #define GPtrArray                    void                      //~v@@@I~
//********                                                         //~v@@@I~
//*pango *                                                         //~v@@@I~
//********                                                         //~v@@@I~
    #define PangoAttrList                void                      //~v@@@M~
    #define PangoContext                 void                      //~v@@@M~
    #define PangoLanguage                void                      //~v@@@M~
//********                                                         //~v@@@I~
//*gdk   *                                                         //~v@@@I~
//********                                                         //~v@@@I~
	#define GdkWindow                    void                      //~v@@@M~
    #define GdkPixmap                    void                      //~v@@@M~
    #define GdkColormap                  void                      //~v@@@M~
    #define GdkEvent                     void                      //~v@@@M~
    #define GdkEventFocus                void                      //~v@@@M~
    #define GdkEventConfigure            void                      //~v@@@M~
    #define GdkEventExpose               void                      //~v@@@M~
    #define GdkEventScroll               void                      //~v@@@M~
    #define GdkEventCrossing             void                      //~v@@@M~
    #define GdkScreen                    void*                     //~v@@@I~
    #define GdkDrawable                  void*                     //~v@@@I~
    #define GdkFont                      void*                     //~v@@@I~
    #define GdkAtom                      void*                     //~v@@@I~
    #define GdkDevice                    void                      //~v@@@I~
    #define GdkDisplay                   void                      //~v@@@I~
                                                                   //~v@@@I~
    #define GDK_ATOM_TO_POINTER(atom) (atom)                       //~v@@@I~
    #define GDK_POINTER_TO_ATOM(ptr)  ((GdkAtom)(ptr))             //~v@@@I~
                                                                   //~v@@@I~
    #define _GDK_MAKE_ATOM(val) ((GdkAtom)GUINT_TO_POINTER(val))   //~v@@@I~
    #define GDK_NONE            _GDK_MAKE_ATOM (0)                 //~v@@@I~
    #define GDK_SELECTION_PRIMARY       _GDK_MAKE_ATOM (1)         //~v@@@I~
    #define GDK_SELECTION_CLIPBOARD     _GDK_MAKE_ATOM (69)        //~v@@@I~
                                                                   //~v@@@I~
	#define GDK_CURRENT_TIME     0L                                //~v@@@I~
                                                                   //~v@@@I~
    typedef enum                                                   //~v@@@I~
    {                                                              //~v@@@I~
      GDK_HINT_POS         = 1 << 0,                               //~v@@@I~
      GDK_HINT_MIN_SIZE    = 1 << 1,                               //~v@@@I~
      GDK_HINT_MAX_SIZE    = 1 << 2,                               //~v@@@I~
      GDK_HINT_BASE_SIZE   = 1 << 3,                               //~v@@@I~
      GDK_HINT_ASPECT      = 1 << 4,                               //~v@@@I~
      GDK_HINT_RESIZE_INC  = 1 << 5,                               //~v@@@I~
      GDK_HINT_WIN_GRAVITY = 1 << 6,                               //~v@@@I~
      GDK_HINT_USER_POS    = 1 << 7,                               //~v@@@I~
      GDK_HINT_USER_SIZE   = 1 << 8                                //~v@@@I~
    } GdkWindowHints;                                              //~v@@@I~
//*                                                                //~v@@@I~
    typedef enum                                                   //~v@@@I~
    {                                                              //~v@@@I~
      GDK_NOTHING       = -1,                                      //~v@@@I~
      GDK_DELETE        = 0,                                       //~v@@@I~
      GDK_DESTROY       = 1,                                       //~v@@@I~
      GDK_EXPOSE        = 2,                                       //~v@@@I~
      GDK_MOTION_NOTIFY = 3,                                       //~v@@@I~
      GDK_BUTTON_PRESS  = 4,                                       //~v@@@I~
      GDK_2BUTTON_PRESS = 5,                                       //~v@@@I~
      GDK_3BUTTON_PRESS = 6,                                       //~v@@@I~
      GDK_BUTTON_RELEASE    = 7,                                   //~v@@@I~
      GDK_KEY_PRESS     = 8,                                       //~v@@@I~
      GDK_KEY_RELEASE   = 9,                                       //~v@@@I~
      GDK_ENTER_NOTIFY  = 10,                                      //~v@@@I~
      GDK_LEAVE_NOTIFY  = 11,                                      //~v@@@I~
      GDK_FOCUS_CHANGE  = 12,                                      //~v@@@I~
      GDK_CONFIGURE     = 13,                                      //~v@@@I~
      GDK_MAP       = 14,                                          //~v@@@I~
      GDK_UNMAP     = 15,                                          //~v@@@I~
      GDK_PROPERTY_NOTIFY   = 16,                                  //~v@@@I~
      GDK_SELECTION_CLEAR   = 17,                                  //~v@@@I~
      GDK_SELECTION_REQUEST = 18,                                  //~v@@@I~
      GDK_SELECTION_NOTIFY  = 19,                                  //~v@@@I~
      GDK_PROXIMITY_IN  = 20,                                      //~v@@@I~
      GDK_PROXIMITY_OUT = 21,                                      //~v@@@I~
      GDK_DRAG_ENTER        = 22,                                  //~v@@@I~
      GDK_DRAG_LEAVE        = 23,                                  //~v@@@I~
      GDK_DRAG_MOTION       = 24,                                  //~v@@@I~
      GDK_DRAG_STATUS       = 25,                                  //~v@@@I~
      GDK_DROP_START        = 26,                                  //~v@@@I~
      GDK_DROP_FINISHED     = 27,                                  //~v@@@I~
      GDK_CLIENT_EVENT  = 28,                                      //~v@@@I~
      GDK_VISIBILITY_NOTIFY = 29,                                  //~v@@@I~
      GDK_NO_EXPOSE     = 30,                                      //~v@@@I~
      GDK_SCROLL            = 31,                                  //~v@@@I~
      GDK_WINDOW_STATE      = 32,                                  //~v@@@I~
      GDK_SETTING           = 33,                                  //~v@@@I~
      GDK_OWNER_CHANGE      = 34,                                  //~v@@@I~
      GDK_GRAB_BROKEN       = 35,                                  //~v@@@I~
      GDK_DAMAGE            = 36,                                  //~v@@@I~
      GDK_EVENT_LAST        /* helper variable for decls */        //~v@@@I~
    } GdkEventType;                                                //~v@@@I~
//*copy from  /usr/include/gtk-2.0/gdk/gdkcolor.h *****************//~v@@@M~
    struct _GdkColor                                               //~v@@@I~
    {                                                              //~v@@@I~
      guint32 pixel;                                               //~v@@@I~
      guint16 red;                                                 //~v@@@I~
      guint16 green;                                               //~v@@@I~
      guint16 blue;                                                //~v@@@I~
    };                                                             //~v@@@I~
	typedef struct _GdkColor	      GdkColor;                    //~v@@I~//~v@@@M~
                                                                   //~v@@@M~
//*copy from  /usr/include/gtk-2.0/gdk/gdktypes.h *****************//~v@@@M~
    typedef enum                                                   //~v@@@I~
    {                                                              //~v@@@I~
      GDK_SHIFT_MASK    = 1 << 0,                                  //~v@@@I~
      GDK_LOCK_MASK     = 1 << 1,                                  //~v@@@I~
      GDK_CONTROL_MASK  = 1 << 2,                                  //~v@@@I~
      GDK_MOD1_MASK     = 1 << 3,                                  //~v@@@I~
      GDK_MOD2_MASK     = 1 << 4,                                  //~v@@@I~
      GDK_MOD3_MASK     = 1 << 5,                                  //~v@@@I~
      GDK_MOD4_MASK     = 1 << 6,                                  //~v@@@I~
      GDK_MOD5_MASK     = 1 << 7,                                  //~v@@@I~
      GDK_BUTTON1_MASK  = 1 << 8,                                  //~v@@@I~
      GDK_BUTTON2_MASK  = 1 << 9,                                  //~v@@@I~
      GDK_BUTTON3_MASK  = 1 << 10,                                 //~v@@@I~
      GDK_BUTTON4_MASK  = 1 << 11,                                 //~v@@@I~
      GDK_BUTTON5_MASK  = 1 << 12,                                 //~v@@@I~
                                                                   //~v@@@I~
      /* The next few modifiers are used by XKB, so we skip to the end.//~v@@@I~
       * Bits 15 - 25 are currently unused. Bit 29 is used internally.//~v@@@I~
       */                                                          //~v@@@I~
                                                                   //~v@@@I~
      GDK_SUPER_MASK    = 1 << 26,                                 //~v@@@I~
      GDK_HYPER_MASK    = 1 << 27,                                 //~v@@@I~
      GDK_META_MASK     = 1 << 28,                                 //~v@@@I~
                                                                   //~v@@@I~
      GDK_RELEASE_MASK  = 1 << 30,                                 //~v@@@I~
                                                                   //~v@@@I~
      GDK_MODIFIER_MASK = 0x5c001fff                               //~v@@@I~
    } GdkModifierType;                                             //~v@@@I~
                                                                   //~v@@@I~
    struct _GdkPoint                                               //~v@@@M~
    {                                                              //~v@@@M~
      gint x;                                                      //~v@@@M~
      gint y;                                                      //~v@@@M~
    };                                                             //~v@@@M~
	typedef struct _GdkPoint	      GdkPoint;                    //~v@@@M~
                                                                   //~v@@@I~
    struct _GdkRectangle                                           //~v@@@M~
    {                                                              //~v@@@M~
      gint x;                                                      //~v@@@M~
      gint y;                                                      //~v@@@M~
      gint width;                                                  //~v@@@M~
      gint height;                                                 //~v@@@M~
    };                                                             //~v@@@M~
	typedef struct _GdkRectangle     GdkRectangle;                 //~v@@@M~
                                                                   //~v@@@I~
    typedef enum                                                   //~v@@@I~
    {                                                              //~v@@@I~
      GDK_LINE_SOLID,                                              //~v@@@I~
      GDK_LINE_ON_OFF_DASH,                                        //~v@@@I~
      GDK_LINE_DOUBLE_DASH                                         //~v@@@I~
    } GdkLineStyle;                                                //~v@@@I~
                                                                   //~v@@@I~
    typedef enum                                                   //~v@@@I~
    {                                                              //~v@@@I~
      GDK_GC_FOREGROUND    = 1 << 0,                               //~v@@@I~
      GDK_GC_BACKGROUND    = 1 << 1,                               //~v@@@I~
      GDK_GC_FONT          = 1 << 2,                               //~v@@@I~
      GDK_GC_FUNCTION      = 1 << 3,                               //~v@@@I~
      GDK_GC_FILL          = 1 << 4,                               //~v@@@I~
      GDK_GC_TILE          = 1 << 5,                               //~v@@@I~
      GDK_GC_STIPPLE       = 1 << 6,                               //~v@@@I~
      GDK_GC_CLIP_MASK     = 1 << 7,                               //~v@@@I~
      GDK_GC_SUBWINDOW     = 1 << 8,                               //~v@@@I~
      GDK_GC_TS_X_ORIGIN   = 1 << 9,                               //~v@@@I~
      GDK_GC_TS_Y_ORIGIN   = 1 << 10,                              //~v@@@I~
      GDK_GC_CLIP_X_ORIGIN = 1 << 11,                              //~v@@@I~
      GDK_GC_CLIP_Y_ORIGIN = 1 << 12,                              //~v@@@I~
      GDK_GC_EXPOSURES     = 1 << 13,                              //~v@@@I~
      GDK_GC_LINE_WIDTH    = 1 << 14,                              //~v@@@I~
      GDK_GC_LINE_STYLE    = 1 << 15,                              //~v@@@I~
      GDK_GC_CAP_STYLE     = 1 << 16,                              //~v@@@I~
      GDK_GC_JOIN_STYLE    = 1 << 17                               //~v@@@I~
    } GdkGCValuesMask;                                             //~v@@@I~
                                                                   //~v@@@I~
    typedef enum                                                   //~v@@@I~
    {                                                              //~v@@@I~
      GDK_CAP_NOT_LAST,                                            //~v@@@I~
      GDK_CAP_BUTT,                                                //~v@@@I~
      GDK_CAP_ROUND,                                               //~v@@@I~
      GDK_CAP_PROJECTING                                           //~v@@@I~
    } GdkCapStyle;                                                 //~v@@@I~
                                                                   //~v@@@I~
    typedef enum                                                   //~v@@@I~
    {                                                              //~v@@@I~
      GDK_COPY,                                                    //~v@@@I~
      GDK_INVERT,                                                  //~v@@@I~
      GDK_XOR,                                                     //~v@@@I~
      GDK_CLEAR,                                                   //~v@@@I~
      GDK_AND,                                                     //~v@@@I~
      GDK_AND_REVERSE,                                             //~v@@@I~
      GDK_AND_INVERT,                                              //~v@@@I~
      GDK_NOOP,                                                    //~v@@@I~
      GDK_OR,                                                      //~v@@@I~
      GDK_EQUIV,                                                   //~v@@@I~
      GDK_OR_REVERSE,                                              //~v@@@I~
      GDK_COPY_INVERT,                                             //~v@@@I~
      GDK_OR_INVERT,                                               //~v@@@I~
      GDK_NAND,                                                    //~v@@@I~
      GDK_NOR,                                                     //~v@@@I~
      GDK_SET                                                      //~v@@@I~
    } GdkFunction;                                                 //~v@@@I~
                                                                   //~v@@@I~
    typedef enum                                                   //~v@@@I~
    {                                                              //~v@@@I~
      GDK_JOIN_MITER,                                              //~v@@@I~
      GDK_JOIN_ROUND,                                              //~v@@@I~
      GDK_JOIN_BEVEL                                               //~v@@@I~
    } GdkJoinStyle;                                                //~v@@@I~
                                                                   //~v@@@I~
    typedef enum                                                   //~v@@@I~
    {                                                              //~v@@@I~
      GDK_GRAVITY_NORTH_WEST = 1,                                  //~v@@@I~
      GDK_GRAVITY_NORTH,                                           //~v@@@I~
      GDK_GRAVITY_NORTH_EAST,                                      //~v@@@I~
      GDK_GRAVITY_WEST,                                            //~v@@@I~
      GDK_GRAVITY_CENTER,                                          //~v@@@I~
      GDK_GRAVITY_EAST,                                            //~v@@@I~
      GDK_GRAVITY_SOUTH_WEST,                                      //~v@@@I~
      GDK_GRAVITY_SOUTH,                                           //~v@@@I~
      GDK_GRAVITY_SOUTH_EAST,                                      //~v@@@I~
      GDK_GRAVITY_STATIC                                           //~v@@@I~
    } GdkGravity;                                                  //~v@@@I~
                                                                   //~v@@@I~
    struct _GdkGCValues                                            //~v@@@I~
    {                                                              //~v@@@I~
      GdkColor      foreground;                                    //~v@@@I~
      GdkColor      background;                                    //~v@@@I~
      GdkFont      *font;                                          //~v@@@I~
    #ifndef ARM                                                    //~v@@@I~
      GdkFunction       function;                                  //~v@@@I~
      GdkFill       fill;                                          //~v@@@I~
      GdkPixmap    *tile;                                          //~v@@@I~
      GdkPixmap    *stipple;                                       //~v@@@I~
      GdkPixmap    *clip_mask;                                     //~v@@@I~
      GdkSubwindowMode  subwindow_mode;                            //~v@@@I~
      gint          ts_x_origin;                                   //~v@@@I~
      gint          ts_y_origin;                                   //~v@@@I~
      gint          clip_x_origin;                                 //~v@@@I~
      gint          clip_y_origin;                                 //~v@@@I~
      gint          graphics_exposures;                            //~v@@@I~
	#endif                                                         //~v@@@I~
      gint          line_width;                                    //~v@@@I~
      GdkLineStyle      line_style;                                //~v@@@I~
    #ifndef ARM                                                    //~v@@@I~
      GdkCapStyle       cap_style;                                 //~v@@@I~
      GdkJoinStyle      join_style;                                //~v@@@I~
	#endif                                                         //~v@@@I~
    };                                                             //~v@@@I~
	typedef struct _GdkGCValues	      GdkGCValues;                 //~v@@@I~
                                                                   //~v@@@M~
    struct _GdkGC                                                  //~v@@@M~
    {                                                              //~v@@@M~
#ifndef ARM                                                        //~v@@@M~
      GObject parent_instance;                                     //~v@@@M~
                                                                   //~v@@@M~
      gint clip_x_origin;                                          //~v@@@M~
      gint clip_y_origin;                                          //~v@@@M~
      gint ts_x_origin;                                            //~v@@@M~
      gint ts_y_origin;                                            //~v@@@M~
                                                                   //~v@@@M~
      GdkColormap *colormap;                                       //~v@@@M~
#else                                                              //~v@@@M~
      gint          line_width;                                    //~v@@@M~
      GdkLineStyle  line_style;                                    //~v@@@M~
      GdkColor      foreground;                                    //~v@@@R~
      GdkColor      background;                                    //~v@@@R~
#endif //ARM                                                       //~v@@@M~
    };                                                             //~v@@@M~
	typedef struct _GdkGC   	      GdkGC;                       //~v@@@M~
//*copy from  /usr/include/gtk-2.0/gdk/gdkevents.h *****************//~v@@@M~
    typedef struct _GdkEventKey     GdkEventKey;                   //~v@@@I~
    struct _GdkEventKey                                            //~v@@@I~
    {                                                              //~v@@@I~
      GdkEventType type;                                           //~v@@@I~
      GdkWindow *window;                                           //~v@@@I~
      gint8 send_event;                                            //~v@@@I~
      guint32 time;          //@@@@unit:milisec                    //~v@@@R~
      guint state;                                                 //~v@@@I~
      guint keyval;                                                //~v@@@I~
      gint length;                                                 //~v@@@I~
      gchar *string;                                               //~v@@@I~
      guint16 hardware_keycode;                                    //~v@@@I~
      guint8 group;                                                //~v@@@I~
      guint is_modifier : 1;                                       //~v@@@I~
    };                                                             //~v@@@I~
//*                                                                //~v@@@I~
    struct _GdkEventButton                                         //~v@@@I~
    {                                                              //~v@@@I~
      GdkEventType type;                                           //~v@@@I~
      GdkWindow *window;                                           //~v@@@I~
      gint8 send_event;                                            //~v@@@I~
      guint32 time;                                                //~v@@@I~
      gdouble x;                                                   //~v@@@I~
      gdouble y;                                                   //~v@@@I~
      gdouble *axes;                                               //~v@@@I~
      guint state;                                                 //~v@@@I~
      guint button;                                                //~v@@@I~
      GdkDevice *device;                                           //~v@@@I~
      gdouble x_root, y_root;                                      //~v@@@I~
    };                                                             //~v@@@I~
    typedef struct _GdkEventButton GdkEventButton;                 //~v@@@M~
//**                                                               //~v@@@I~
    struct _GdkEventMotion                                         //~v@@@I~
    {                                                              //~v@@@I~
      GdkEventType type;                                           //~v@@@I~
      GdkWindow *window;                                           //~v@@@I~
      gint8 send_event;                                            //~v@@@I~
      guint32 time;                                                //~v@@@I~
      gdouble x;                                                   //~v@@@I~
      gdouble y;                                                   //~v@@@I~
      gdouble *axes;                                               //~v@@@I~
      guint state;                                                 //~v@@@I~
      gint16 is_hint;                                              //~v@@@I~
      GdkDevice *device;                                           //~v@@@I~
      gdouble x_root, y_root;                                      //~v@@@I~
    };                                                             //~v@@@I~
    typedef struct _GdkEventMotion GdkEventMotion;                 //~v@@@I~
//**                                                               //~v@@@I~
    typedef struct _GdkEventClient    GdkEventClient;              //~v@@@I~
    struct _GdkEventClient                                         //~v@@@I~
    {                                                              //~v@@@I~
      GdkEventType type;                                           //~v@@@I~
      GdkWindow *window;                                           //~v@@@I~
      gint8 send_event;                                            //~v@@@I~
      GdkAtom message_type;                                        //~v@@@I~
      gushort data_format;                                         //~v@@@I~
      union {                                                      //~v@@@I~
        char b[20];                                                //~v@@@I~
        short s[10];                                               //~v@@@I~
        long l[5];                                                 //~v@@@I~
      } data;                                                      //~v@@@I~
    };                                                             //~v@@@I~
                                                                   //~v@@@I~
//*                                                                //~v@@@I~
    struct _GdkGeometry                                            //~v@@@I~
    {                                                              //~v@@@I~
      gint min_width;                                              //~v@@@I~
      gint min_height;                                             //~v@@@I~
      gint max_width;                                              //~v@@@I~
      gint max_height;                                             //~v@@@I~
      gint base_width;                                             //~v@@@I~
      gint base_height;                                            //~v@@@I~
      gint width_inc;                                              //~v@@@I~
      gint height_inc;                                             //~v@@@I~
      gdouble min_aspect;                                          //~v@@@I~
      gdouble max_aspect;                                          //~v@@@I~
      GdkGravity win_gravity;                                      //~v@@@I~
    };                                                             //~v@@@I~
    typedef struct _GdkGeometry GdkGeometry;                       //~v@@@R~
//*                                                                //~v@@@I~
    typedef enum                                                   //~v@@@I~
    {                                                              //~v@@@I~
      GDK_ACTION_DEFAULT = 1 << 0,                                 //~v@@@I~
      GDK_ACTION_COPY    = 1 << 1,                                 //~v@@@I~
      GDK_ACTION_MOVE    = 1 << 2,                                 //~v@@@I~
      GDK_ACTION_LINK    = 1 << 3,                                 //~v@@@I~
      GDK_ACTION_PRIVATE = 1 << 4,                                 //~v@@@I~
      GDK_ACTION_ASK     = 1 << 5                                  //~v@@@I~
    } GdkDragAction;                                               //~v@@@I~
//*                                                                //~v@@@I~
    typedef enum                                                   //~v@@@I~
    {                                                              //~v@@@I~
      GDK_DRAG_PROTO_MOTIF,                                        //~v@@@I~
      GDK_DRAG_PROTO_XDND,                                         //~v@@@I~
      GDK_DRAG_PROTO_ROOTWIN,     /* A root window with nobody claiming//~v@@@I~
                       * drags */                                  //~v@@@I~
      GDK_DRAG_PROTO_NONE,        /* Not a valid drag window */    //~v@@@I~
      GDK_DRAG_PROTO_WIN32_DROPFILES, /* The simple WM_DROPFILES dnd *///~v@@@I~
      GDK_DRAG_PROTO_OLE2,        /* The complex OLE2 dnd (not implemented) *///~v@@@I~
      GDK_DRAG_PROTO_LOCAL            /* Intra-app */              //~v@@@I~
    } GdkDragProtocol;                                             //~v@@@I~
//*                                                                //~v@@@I~
	typedef struct _GdkDragContext        GdkDragContext;          //~v@@@I~
    struct _GdkDragContext {                                       //~v@@@I~
#ifndef ARM                                                        //~v@@@M~
      GObject parent_instance;                                     //~v@@@I~
#endif //ARM                                                       //~v@@@M~
                                                                   //~v@@@I~
      /*< public >*/                                               //~v@@@I~
                                                                   //~v@@@I~
      GdkDragProtocol protocol;                                    //~v@@@I~
                                                                   //~v@@@I~
      gboolean is_source;                                          //~v@@@I~
                                                                   //~v@@@I~
      GdkWindow *source_window;                                    //~v@@@I~
      GdkWindow *dest_window;                                      //~v@@@I~
                                                                   //~v@@@I~
      GList *targets;                                              //~v@@@I~
      GdkDragAction actions;                                       //~v@@@I~
      GdkDragAction suggested_action;                              //~v@@@I~
      GdkDragAction action;                                        //~v@@@I~
                                                                   //~v@@@I~
      guint32 start_time;                                          //~v@@@I~
                                                                   //~v@@@I~
      /*< private >*/                                              //~v@@@I~
                                                                   //~v@@@I~
      gpointer windowing_data;                                     //~v@@@I~
    };                                                             //~v@@@I~
//********                                                         //~v@@@I~
//*gtk   *                                                         //~v@@@I~
//********                                                         //~v@@@I~
	#define GtkMenuPositionFunc	         void*                     //~v@@@M~
                                                                   //~v@@@I~
    #define GtkButton                    void                      //~v@@@M~
    #define GtkEditable                  void                      //~v@@@M~
    #define GtkClipboard                 void                      //~v@@@M~
    #define GtkIMContext                 void                      //~v@@@M~
    #define GtkClipboardTextReceivedFunc void*                     //~v@@@M~
    #define GtkWindow                    void                      //~v@@@I~
    #define GtkObject                    void                      //~v@@@I~
                                                                   //~v@@@M~
	#define GTK_DIALOG(obj) 		((GtkDialog*)(obj))            //~v@@@M~
	#define GTK_WIDGET(obj) 		((GtkWidget*)(obj))            //~v@@@M~
	#define GTK_MENU_ITEM(obj) 		((GtkMenuItem*)(obj))          //~v@@@M~
	#define GTK_WINDOW(obj) 		((GtkWindow*)(obj))            //~v@@@M~
    #define GTK_MENU(obj) 			((GtkMenu*)(obj))              //~v@@@I~
    #define GTK_OBJECT(obj) 			((GtkObject*)(obj))        //~v@@@I~
	#define GTK_FILE_SELECTION(obj)  ((GtkFileSelection*)(obj))    //~v@@@I~
                                                                   //~v@@@I~
    typedef GdkRectangle GtkAllocation;                            //~v@@@M~
                                                                   //~v@@@I~
    typedef enum                                                   //~v@@@I~
    {                                                              //~v@@@I~
      GTK_MESSAGE_INFO,                                            //~v@@@I~
      GTK_MESSAGE_WARNING,                                         //~v@@@I~
      GTK_MESSAGE_QUESTION,                                        //~v@@@I~
      GTK_MESSAGE_ERROR,                                           //~v@@@I~
      GTK_MESSAGE_OTHER                                            //~v@@@I~
    } GtkMessageType;                                              //~v@@@I~
                                                                   //~v@@@I~
//* from gtkmessagedialog.h                                        //~v@@@I~
    typedef enum                                                   //~v@@@I~
    {                                                              //~v@@@I~
      GTK_BUTTONS_NONE,                                            //~v@@@I~
      GTK_BUTTONS_OK,                                              //~v@@@I~
      GTK_BUTTONS_CLOSE,                                           //~v@@@I~
      GTK_BUTTONS_CANCEL,                                          //~v@@@I~
      GTK_BUTTONS_YES_NO,                                          //~v@@@I~
      GTK_BUTTONS_OK_CANCEL                                        //~v@@@I~
    } GtkButtonsType;                                              //~v@@@I~
//* from gtkdialog.h                                               //~v@@@I~
    typedef enum                                                   //~v@@@I~
    {                                                              //~v@@@I~
      /* GTK returns this if a response widget has no response_id, //~v@@@I~
       * or if the dialog gets programmatically hidden or destroyed.//~v@@@I~
       */                                                          //~v@@@I~
      GTK_RESPONSE_NONE = -1,                                      //~v@@@I~
                                                                   //~v@@@I~
      /* GTK won't return these unless you pass them in            //~v@@@I~
       * as the response for an action widget. They are            //~v@@@I~
       * for your convenience.                                     //~v@@@I~
       */                                                          //~v@@@I~
      GTK_RESPONSE_REJECT = -2,                                    //~v@@@I~
      GTK_RESPONSE_ACCEPT = -3,                                    //~v@@@I~
                                                                   //~v@@@I~
      /* If the dialog is deleted. */                              //~v@@@I~
      GTK_RESPONSE_DELETE_EVENT = -4,                              //~v@@@I~
                                                                   //~v@@@I~
      /* These are returned from GTK dialogs, and you can also use them//~v@@@I~
       * yourself if you like.                                     //~v@@@I~
       */                                                          //~v@@@I~
      GTK_RESPONSE_OK     = -5,                                    //~v@@@I~
      GTK_RESPONSE_CANCEL = -6,                                    //~v@@@I~
      GTK_RESPONSE_CLOSE  = -7,                                    //~v@@@I~
      GTK_RESPONSE_YES    = -8,                                    //~v@@@I~
      GTK_RESPONSE_NO     = -9,                                    //~v@@@I~
      GTK_RESPONSE_APPLY  = -10,                                   //~v@@@I~
      GTK_RESPONSE_HELP   = -11                                    //~v@@@I~
    } GtkResponseType;                                             //~v@@@I~
                                                                   //~v@@@I~
    typedef enum                                                   //~v@@@I~
    {                                                              //~v@@@I~
      GTK_DIALOG_MODAL               = 1 << 0, /* call gtk_window_set_modal (win, TRUE) *///~v@@@I~
      GTK_DIALOG_DESTROY_WITH_PARENT = 1 << 1, /* call gtk_window_set_destroy_with_parent () *///~v@@@I~
      GTK_DIALOG_NO_SEPARATOR        = 1 << 2  /* no separator bar above buttons *///~v@@@I~
    } GtkDialogFlags;                                              //~v@@@I~
                                                                   //~v@@@I~
    #define GTK_STOCK_CANCEL           "gtk-cancel"                //~v@@@I~
    #define GTK_STOCK_NO               "gtk-no"                    //~v@@@I~
    #define GTK_STOCK_YES              "gtk-yes"                   //~v@@@I~
                                                                   //~v@@@I~
    struct _GtkStyle                                               //~v@@@I~
    {                                                              //~v@@@I~
#ifndef ARM                                                        //~v@@@I~
      GObject parent_instance;                                     //~v@@@I~
                                                                   //~v@@@I~
      /*< public >*/                                               //~v@@@I~
                                                                   //~v@@@I~
      GdkColor fg[5];                                              //~v@@@I~
      GdkColor bg[5];                                              //~v@@@I~
      GdkColor light[5];                                           //~v@@@I~
      GdkColor dark[5];                                            //~v@@@I~
      GdkColor mid[5];                                             //~v@@@I~
      GdkColor text[5];                                            //~v@@@I~
      GdkColor base[5];                                            //~v@@@I~
      GdkColor text_aa[5];      /* Halfway between text/base */    //~v@@@I~
                                                                   //~v@@@I~
      GdkColor black;                                              //~v@@@I~
      GdkColor white;                                              //~v@@@I~
      PangoFontDescription *font_desc;                             //~v@@@I~
                                                                   //~v@@@I~
      gint xthickness;                                             //~v@@@I~
      gint ythickness;                                             //~v@@@I~
                                                                   //~v@@@I~
                                                                   //~v@@@I~
#endif //ARM                                                       //~v@@@I~
      GdkGC *fg_gc[5];                                             //~v@@@I~
#ifndef ARM                                                        //~v@@@I~
      GdkGC *bg_gc[5];                                             //~v@@@I~
      GdkGC *light_gc[5];                                          //~v@@@I~
      GdkGC *dark_gc[5];                                           //~v@@@I~
      GdkGC *mid_gc[5];                                            //~v@@@I~
      GdkGC *text_gc[5];                                           //~v@@@I~
      GdkGC *base_gc[5];                                           //~v@@@I~
      GdkGC *text_aa_gc[5];                                        //~v@@@I~
      GdkGC *black_gc;                                             //~v@@@I~
      GdkGC *white_gc;                                             //~v@@@I~
                                                                   //~v@@@I~
      GdkPixmap *bg_pixmap[5];                                     //~v@@@I~
                                                                   //~v@@@I~
      /*< private >*/                                              //~v@@@I~
                                                                   //~v@@@I~
      gint attach_count;                                           //~v@@@I~
                                                                   //~v@@@I~
      gint depth;                                                  //~v@@@I~
      GdkColormap *colormap;                                       //~v@@@I~
      GdkFont *private_font;                                       //~v@@@I~
      PangoFontDescription *private_font_desc; /* Font description for style->private_font or %NULL *///~v@@@I~
                                                                   //~v@@@I~
      /* the RcStyle from which this style was created */          //~v@@@I~
      GtkRcStyle     *rc_style;                                    //~v@@@I~
                                                                   //~v@@@I~
      GSList     *styles;     /* of type GtkStyle* */              //~v@@@I~
      GArray     *property_cache;                                  //~v@@@I~
      GSList         *icon_factories; /* of type GtkIconFactory* *///~v@@@I~
#endif //ARM                                                       //~v@@@I~
    };                                                             //~v@@@I~
    typedef struct _GtkStyle GtkStyle;                             //~v@@@I~
                                                                   //~v@@@I~
    struct _GtkLabel                                               //~v@@@I~
    {                                                              //~v@@@I~
#ifndef ARM                                                        //~v@@@I~
      GtkMisc misc;                                                //~v@@@I~
                                                                   //~v@@@I~
      /*< private >*/                                              //~v@@@I~
      gchar  *GSEAL (label);                                       //~v@@@I~
      guint   GSEAL (jtype)            : 2;                        //~v@@@I~
      guint   GSEAL (wrap)             : 1;                        //~v@@@I~
      guint   GSEAL (use_underline)    : 1;                        //~v@@@I~
      guint   GSEAL (use_markup)       : 1;                        //~v@@@I~
      guint   GSEAL (ellipsize)        : 3;                        //~v@@@I~
      guint   GSEAL (single_line_mode) : 1;                        //~v@@@I~
      guint   GSEAL (have_transform)   : 1;                        //~v@@@I~
      guint   GSEAL (in_click)         : 1;                        //~v@@@I~
      guint   GSEAL (wrap_mode)        : 3;                        //~v@@@I~
      guint   GSEAL (pattern_set)      : 1;                        //~v@@@I~
      guint   GSEAL (track_links)      : 1;                        //~v@@@I~
                                                                   //~v@@@I~
      guint   GSEAL (mnemonic_keyval);                             //~v@@@I~
                                                                   //~v@@@I~
#endif //ARM                                                       //~v@@@I~
      gchar  *GSEAL (text);                                        //~v@@@I~
#ifndef ARM                                                        //~v@@@I~
      PangoAttrList *GSEAL (attrs);                                //~v@@@I~
      PangoAttrList *GSEAL (effective_attrs);                      //~v@@@I~
                                                                   //~v@@@I~
      PangoLayout *GSEAL (layout);                                 //~v@@@I~
                                                                   //~v@@@I~
      GtkWidget *GSEAL (mnemonic_widget);                          //~v@@@I~
      GtkWindow *GSEAL (mnemonic_window);                          //~v@@@I~
                                                                   //~v@@@I~
      GtkLabelSelectionInfo *GSEAL (select_info);                  //~v@@@I~
#endif //ARM                                                       //~v@@@I~
    };                                                             //~v@@@I~
    typedef struct _GtkLabel GtkLabel;                             //~v@@@I~
                                                                   //~v@@@I~
                                                                   //~v@@@I~
    typedef struct _GtkWidget                                      //~v@@@M~
    {                                                              //~v@@@M~
  	  GtkAllocation allocation;                                    //~v@@@M~
      GdkWindow *window;                                           //~v@@@M~
      GtkDialogFlags flags;                                        //~v@@@I~
      GtkMessageType type;                                         //~v@@@I~
      GtkButtonsType buttons;                                      //~v@@@I~
	  GtkStyle       *style;                                       //~v@@@I~
	  guint8 		 state;                                        //~v@@@I~
      #define JNI_WIDGET_MAX_BUTTON  4                             //~v@@@R~
      int            jni_btnctr;                                   //~v@@@I~
      char*          jni_btn_text[JNI_WIDGET_MAX_BUTTON];          //~v@@@I~
      int            jni_btn_respid[JNI_WIDGET_MAX_BUTTON];        //~v@@@I~
      char *         jni_message;                                  //~v@@@I~
    } GtkWidget;                                                   //~v@@@M~
    typedef GtkWidget GtkDialog;                                   //~v@@@I~
	#define GTK_WIDGET_STATE(wid)	(GTK_WIDGET (wid)->state)      //~v@@@M~
                                                                   //~v@@@I~
	typedef gboolean (*GtkFunction)	    (gpointer      data);      //~v@@@I~
                                                                   //~v@@@I~
    struct _GtkMenu                                                //~v@@@I~
    {                                                              //~v@@@I~
#ifndef ARM                                                        //~v@@@I~
      GtkMenuShell GSEAL (menu_shell);                             //~v@@@I~
                                                                   //~v@@@I~
      GtkWidget *GSEAL (parent_menu_item);                         //~v@@@I~
      GtkWidget *GSEAL (old_active_menu_item);                     //~v@@@I~
                                                                   //~v@@@I~
      GtkAccelGroup *GSEAL (accel_group);                          //~v@@@I~
#endif //ARM                                                       //~v@@@I~
      gchar         *GSEAL (accel_path);                           //~v@@@I~
#ifndef ARM                                                        //~v@@@I~
      GtkMenuPositionFunc GSEAL (position_func);                   //~v@@@I~
      gpointer GSEAL (position_func_data);                         //~v@@@I~
                                                                   //~v@@@I~
      guint GSEAL (toggle_size);                                   //~v@@@I~
      /* Do _not_ touch these widgets directly. We hide the reference//~v@@@I~
       * count from the toplevel to the menu, so it must be restored//~v@@@I~
       * before operating on these widgets                         //~v@@@I~
       */                                                          //~v@@@I~
      GtkWidget *GSEAL (toplevel);                                 //~v@@@I~
                                                                   //~v@@@I~
      GtkWidget *GSEAL (tearoff_window);                           //~v@@@I~
      GtkWidget *GSEAL (tearoff_hbox);                             //~v@@@I~
      GtkWidget *GSEAL (tearoff_scrollbar);                        //~v@@@I~
      GtkAdjustment *GSEAL (tearoff_adjustment);                   //~v@@@I~
                                                                   //~v@@@I~
      GdkWindow *GSEAL (view_window);                              //~v@@@I~
      GdkWindow *GSEAL (bin_window);                               //~v@@@I~
                                                                   //~v@@@I~
      gint GSEAL (scroll_offset);                                  //~v@@@I~
      gint GSEAL (saved_scroll_offset);                            //~v@@@I~
      gint GSEAL (scroll_step);                                    //~v@@@I~
      guint GSEAL (timeout_id);                                    //~v@@@I~
                                                                   //~v@@@I~
      /* When a submenu of this menu is popped up, motion in this  //~v@@@I~
       * region is ignored                                         //~v@@@I~
       */                                                          //~v@@@I~
      GdkRegion *GSEAL (navigation_region);                        //~v@@@I~
      guint GSEAL (navigation_timeout);                            //~v@@@I~
                                                                   //~v@@@I~
      guint GSEAL (needs_destruction_ref_count) : 1;               //~v@@@I~
      guint GSEAL (torn_off) : 1;                                  //~v@@@I~
      /* The tearoff is active when it is torn off and the not-torn-off//~v@@@I~
       * menu is not popped up.                                    //~v@@@I~
       */                                                          //~v@@@I~
      guint GSEAL (tearoff_active) : 1;                            //~v@@@I~
                                                                   //~v@@@I~
      guint GSEAL (scroll_fast) : 1;                               //~v@@@I~
                                                                   //~v@@@I~
      guint GSEAL (upper_arrow_visible) : 1;                       //~v@@@I~
      guint GSEAL (lower_arrow_visible) : 1;                       //~v@@@I~
      guint GSEAL (upper_arrow_prelight) : 1;                      //~v@@@I~
      guint GSEAL (lower_arrow_prelight) : 1;                      //~v@@@I~
#endif //ARM                                                       //~v@@@I~
    };                                                             //~v@@@I~
    typedef struct _GtkMenu GtkMenu;                               //~v@@@I~
                                                                   //~v@@@I~
    struct _GtkMenuItem                                            //~v@@@I~
    {                                                              //~v@@@I~
#ifndef ARM                                                        //~v@@@I~
      GtkItem item;                                                //~v@@@I~
#endif //ARM                                                       //~v@@@I~
                                                                   //~v@@@I~
      GtkWidget *GSEAL (submenu);                                  //~v@@@I~
      GdkWindow *GSEAL (event_window);                             //~v@@@I~
                                                                   //~v@@@I~
      guint16 GSEAL (toggle_size);                                 //~v@@@I~
      guint16 GSEAL (accelerator_width);                           //~v@@@I~
      gchar  *GSEAL (accel_path);                                  //~v@@@I~
                                                                   //~v@@@I~
      guint GSEAL (show_submenu_indicator) : 1;                    //~v@@@I~
      guint GSEAL (submenu_placement) : 1;                         //~v@@@I~
      guint GSEAL (submenu_direction) : 1;                         //~v@@@I~
      guint GSEAL (right_justify): 1;                              //~v@@@I~
      guint GSEAL (timer_from_keypress) : 1;                       //~v@@@I~
      guint GSEAL (from_menubar) : 1;                              //~v@@@I~
      guint GSEAL (timer);                                         //~v@@@I~
    };                                                             //~v@@@I~
    typedef struct _GtkMenuItem GtkMenuItem;                       //~v@@@I~
//*                                                                //~v@@@R~
    struct _GtkSelectionData                                       //~v@@@I~
    {                                                              //~v@@@I~
      GdkAtom       GSEAL (selection);                             //~v@@@I~
      GdkAtom       GSEAL (target);                                //~v@@@I~
      GdkAtom       GSEAL (type);                                  //~v@@@I~
      gint          GSEAL (format);                                //~v@@@I~
      guchar       *GSEAL (data);                                  //~v@@@I~
      gint          GSEAL (length);                                //~v@@@I~
      GdkDisplay   *GSEAL (display);                               //~v@@@I~
    };                                                             //~v@@@I~
    typedef struct _GtkSelectionData   GtkSelectionData;           //~v@@@I~
//*                                                                //~v@@@I~
    typedef struct _GtkFileSelection       GtkFileSelection;       //~v@@@I~
    struct _GtkFileSelection                                       //~v@@@I~
    {                                                              //~v@@@I~
      /*< private >*/                                              //~v@@@I~
      GtkDialog parent_instance;                                   //~v@@@I~
                                                                   //~v@@@I~
      /*< public >*/                                               //~v@@@I~
      GtkWidget *dir_list;                                         //~v@@@I~
      GtkWidget *file_list;                                        //~v@@@I~
      GtkWidget *selection_entry;                                  //~v@@@I~
      GtkWidget *selection_text;                                   //~v@@@I~
      GtkWidget *main_vbox;                                        //~v@@@I~
      GtkWidget *ok_button;                                        //~v@@@I~
      GtkWidget *cancel_button;                                    //~v@@@I~
      GtkWidget *help_button;                                      //~v@@@I~
      GtkWidget *history_pulldown;                                 //~v@@@I~
      GtkWidget *history_menu;                                     //~v@@@I~
      GList     *history_list;                                     //~v@@@I~
      GtkWidget *fileop_dialog;                                    //~v@@@I~
      GtkWidget *fileop_entry;                                     //~v@@@I~
      gchar     *fileop_file;                                      //~v@@@I~
      gpointer   cmpl_state;                                       //~v@@@I~
                                                                   //~v@@@I~
      GtkWidget *fileop_c_dir;                                     //~v@@@I~
      GtkWidget *fileop_del_file;                                  //~v@@@I~
      GtkWidget *fileop_ren_file;                                  //~v@@@I~
                                                                   //~v@@@I~
      GtkWidget *button_area;                                      //~v@@@I~
      GtkWidget *action_area;                                      //~v@@@I~
                                                                   //~v@@@I~
      /*< private >*/                                              //~v@@@I~
      GPtrArray *selected_names;                                   //~v@@@I~
      gchar     *last_selected;                                    //~v@@@I~
    };                                                             //~v@@@I~
//********                                                         //~v@@@I~
//*pango *                                                         //~v@@@I~
//********                                                         //~v@@@I~
    typedef enum {                                                 //~v@@@I~
      PANGO_STYLE_NORMAL,                                          //~v@@@I~
      PANGO_STYLE_OBLIQUE,                                         //~v@@@I~
      PANGO_STYLE_ITALIC                                           //~v@@@I~
    } PangoStyle;                                                  //~v@@@I~
                                                                   //~v@@@I~
    typedef enum {                                                 //~v@@@I~
      PANGO_VARIANT_NORMAL,                                        //~v@@@I~
      PANGO_VARIANT_SMALL_CAPS                                     //~v@@@I~
    } PangoVariant;                                                //~v@@@I~
                                                                   //~v@@@I~
    typedef enum {                                                 //~v@@@I~
      PANGO_WEIGHT_THIN = 100,                                     //~v@@@I~
      PANGO_WEIGHT_ULTRALIGHT = 200,                               //~v@@@I~
      PANGO_WEIGHT_LIGHT = 300,                                    //~v@@@I~
      PANGO_WEIGHT_BOOK = 380,                                     //~v@@@I~
      PANGO_WEIGHT_NORMAL = 400,                                   //~v@@@I~
      PANGO_WEIGHT_MEDIUM = 500,                                   //~v@@@I~
      PANGO_WEIGHT_SEMIBOLD = 600,                                 //~v@@@I~
      PANGO_WEIGHT_BOLD = 700,                                     //~v@@@I~
      PANGO_WEIGHT_ULTRABOLD = 800,                                //~v@@@I~
      PANGO_WEIGHT_HEAVY = 900,                                    //~v@@@I~
      PANGO_WEIGHT_ULTRAHEAVY = 1000                               //~v@@@I~
    } PangoWeight;                                                 //~v@@@I~
                                                                   //~v@@@I~
    typedef enum {                                                 //~v@@@I~
      PANGO_STRETCH_ULTRA_CONDENSED,                               //~v@@@I~
      PANGO_STRETCH_EXTRA_CONDENSED,                               //~v@@@I~
      PANGO_STRETCH_CONDENSED,                                     //~v@@@I~
      PANGO_STRETCH_SEMI_CONDENSED,                                //~v@@@I~
      PANGO_STRETCH_NORMAL,                                        //~v@@@I~
      PANGO_STRETCH_SEMI_EXPANDED,                                 //~v@@@I~
      PANGO_STRETCH_EXPANDED,                                      //~v@@@I~
      PANGO_STRETCH_EXTRA_EXPANDED,                                //~v@@@I~
      PANGO_STRETCH_ULTRA_EXPANDED                                 //~v@@@I~
    } PangoStretch;                                                //~v@@@I~
                                                                   //~v@@@I~
    typedef enum {                                                 //~v@@@I~
      PANGO_GRAVITY_SOUTH,                                         //~v@@@I~
      PANGO_GRAVITY_EAST,                                          //~v@@@I~
      PANGO_GRAVITY_NORTH,                                         //~v@@@I~
      PANGO_GRAVITY_WEST,                                          //~v@@@I~
      PANGO_GRAVITY_AUTO                                           //~v@@@I~
    } PangoGravity;                                                //~v@@@I~
                                                                   //~v@@@I~
    typedef struct _PangoGlyphGeometry                             //~v@@@R~
    {                                                              //~v@@@I~
    	float width,x_offset,y_offset;                             //~v@@@R~
    } PangoGlyphGeometry;                                          //~v@@@R~
    struct _PangoFontDescription                                   //~v@@@I~
    {                                                              //~v@@@I~
      char *family_name;                                           //~v@@@I~
                                                                   //~v@@@I~
      PangoStyle style;                                            //~v@@@I~
      PangoVariant variant;                                        //~v@@@I~
      PangoWeight weight;                                          //~v@@@I~
      PangoStretch stretch;                                        //~v@@@I~
      PangoGravity gravity;                                        //~v@@@I~
                                                                   //~v@@@I~
      guint16 mask;                                                //~v@@@I~
      guint static_family : 1;                                     //~v@@@I~
      guint size_is_absolute : 1;                                  //~v@@@I~
                                                                   //~v@@@I~
      int size;                                                    //~v@@@I~
    };                                                             //~v@@@I~
    typedef struct _PangoFontDescription  PangoFontDescription;    //~v@@@I~
                                                                   //~v@@@I~
    struct _PangoLayout                                            //~v@@@R~
    {                                                              //~v@@@R~
    #ifndef ARM                                                    //~v@@@R~
      GObject parent_instance;                                     //~v@@@R~
      /* If you add fields to PangoLayout, be sure to update both  //~v@@@R~
       * the _copy function                                        //~v@@@R~
       */                                                          //~v@@@R~
                                                                   //~v@@@R~
      PangoContext *context;                                       //~v@@@R~
      PangoAttrList *attrs;                                        //~v@@@R~
    #endif //ARM                                                   //~v@@@R~
      PangoFontDescription *font_desc;                             //~v@@@R~
                                                                   //~v@@@R~
      gchar *text;                                                 //~v@@@R~
      int length;           /* length of text in bytes */          //~v@@@R~
      int width;            /* wrap width, in device units */      //~v@@@R~
    #ifndef ARM                                                    //~v@@@I~
      int indent;           /* amount by which first line should be shorter *///~v@@@R~
      int spacing;          /* spacing between lines */            //~v@@@R~
                                                                   //~v@@@R~
      guint justify : 1;                                           //~v@@@R~
      guint alignment : 2;                                         //~v@@@R~
                                                                   //~v@@@R~
      guint single_paragraph : 1;                                  //~v@@@R~
      guint auto_dir : 1;                                          //~v@@@R~
                                                                   //~v@@@R~
      guint wrap : 2;       /* PangoWrapMode */                    //~v@@@R~
      guint ellipsize : 2;      /* PangoEllipsizeMode */           //~v@@@R~
    #endif //ARM                                                   //~v@@@I~
      gint n_chars;             /* Total number of characters in layout *///~v@@@R~
    #ifndef ARM                                                    //~v@@@R~
      PangoLogAttr *log_attrs;  /* Logical attributes for layout's text *///~v@@@R~
                                                                   //~v@@@R~
      int tab_width;        /* Cached width of a tab. -1 == not yet calculated *///~v@@@R~
                                                                   //~v@@@R~
      PangoTabArray *tabs;                                         //~v@@@R~
                                                                   //~v@@@R~
      GSList *lines;                                               //~v@@@R~
    #endif //ARM                                                   //~v@@@R~
        gboolean jni_ligature; //1:write at once,2:apply scale     //~v@@@R~
        gboolean jni_settext;                                      //~v@@@I~
        int 	*jni_ucstb;                                        //~v@@@R~
        int  	jni_ucstbsz;                                       //~v@@@R~
        int  	jni_ucsctr;                                        //~v@@@R~
        float 	*jni_fpostb;	//ucs (x,y) position               //~v@@@R~
        float 	jni_scale;     //scaling when ligature mode        //~v@@@R~
		float   jni_fstrwidth;                                     //~v@@@R~
		PangoGlyphGeometry *jni_pgeom;                             //~v@@@I~
    };                                                             //~v@@@I~
    typedef struct _PangoLayout      PangoLayout;                  //~v@@@R~
                                                                   //~v@@@M~
//**************************************************************   //~v@@@R~
//* functions                                                      //~v@@@R~
//**************************************************************   //~v@@@I~
//*******                                                          //~v@@@I~
//*glib *                                                          //~v@@@I~
//*******                                                          //~v@@@I~
void	g_object_unref(gpointer object);                           //~v@@@I~
void	g_free	          (gpointer	 mem);                         //~v@@@I~
gboolean	g_get_charset (G_CONST_RETURN char **charset);         //~v@@@I~
gboolean 	g_utf8_validate (const gchar  *str,                    //~v@@@I~
                          gssize        max_len,                   //~v@@@M~
                          const gchar **end);                      //~v@@@M~
GIConv 	g_iconv_open   (const gchar  *to_codeset,                  //~v@@@I~
		       const gchar  *from_codeset);                        //~v@@@M~
gchar* 	g_convert_with_iconv    (const gchar  *str,                //~v@@@I~
				gssize        len,                                 //~v@@@M~
				GIConv        converter,                           //~v@@@M~
				gsize        *bytes_read,                          //~v@@@M~
				gsize        *bytes_written,                       //~v@@@M~
//  			GError      **error) G_GNUC_MALLOC;                //~v@@@M~
    			GError      **error);                              //~v@@@M~
gint	g_iconv_close  (GIConv        converter);                  //~v@@@I~
                                                                   //~v@@@M~
//*********                                                        //~v@@@I~
//* gdk   *                                                        //~v@@@I~
//*********                                                        //~v@@@I~
void	gdk_beep(void);                                            //~v@@@I~
GdkColormap*	gdk_colormap_get_system	(void);                    //~v@@@I~
int	gtk_im_context_filter_keypress	(GtkIMContext *Ppimcontext,GdkEventKey *Pevent);//~v@@@R~
gboolean	gdk_colormap_alloc_color	(GdkColormap    *colormap, //~v@@@R~
				   GdkColor       *color,                          //~v@@@I~
				   gboolean        writeable,                      //~v@@@I~
				   gboolean        best_match);                    //~v@@@I~
void	gdk_gc_set_foreground	(GdkGC	    *gc,                   //~v@@@R~
				   const GdkColor   *color);                       //~v@@@I~
void	gdk_gc_set_background	(GdkGC	    *gc,                   //~v@@@R~
				   const GdkColor   *color);                       //~v@@@I~
void	gtk_widget_queue_draw_area	(GtkWidget	       *widget,    //~v@@@R~
					   gint                 x,                     //~v@@@I~
					   gint                 y,                     //~v@@@I~
					   gint                 width,                 //~v@@@I~
					   gint                 height);               //~v@@@I~
void	gdk_draw_line	(GdkDrawable      *drawable,               //~v@@@R~
			 GdkGC            *gc,                                 //~v@@@I~
			 gint              x1_,                                //~v@@@I~
			 gint              y1_,                                //~v@@@I~
			 gint              x2_,                                //~v@@@I~
			 gint              y2_);                               //~v@@@I~
void 	gdk_draw_rectangle (GdkDrawable      *drawable,            //~v@@@R~
			 GdkGC            *gc,                                 //~v@@@I~
			 gboolean          filled,                             //~v@@@I~
			 gint              x,                                  //~v@@@I~
			 gint              y,                                  //~v@@@I~
			 gint              width,                              //~v@@@I~
			 gint              height);                            //~v@@@I~
void 	gdk_draw_layout_with_colors      (GdkDrawable     *drawable,//~v@@@R~
                                       GdkGC           *gc,        //~v@@@I~
                                       gint             x,         //~v@@@I~
                                       gint             y,         //~v@@@I~
                                       PangoLayout     *layout,    //~v@@@I~
                                       const GdkColor  *foreground,//~v@@@I~
                                       const GdkColor  *background);//~v@@@I~
                                                                   //~v@@@I~
void	gdk_draw_drawable  (GdkDrawable      *drawable,            //~v@@@R~
			 GdkGC            *gc,                                 //~v@@@I~
			 GdkDrawable      *src,                                //~v@@@I~
			 gint              xsrc,                               //~v@@@I~
			 gint              ysrc,                               //~v@@@I~
			 gint              xdest,                              //~v@@@I~
			 gint              ydest,                              //~v@@@I~
			 gint              width,                              //~v@@@I~
			 gint              height);                            //~v@@@I~
                                                                   //~v@@@I~
GdkScreen*	gdk_screen_get_default (void);                         //~v@@@R~
gint	gdk_screen_get_width             (GdkScreen   *screen);    //~v@@@R~
gint	gdk_screen_get_height            (GdkScreen   *screen);    //~v@@@R~
GdkPixmap*	gdk_pixmap_new	(GdkDrawable *drawable,                //~v@@@R~
					 gint	      width,                           //~v@@@I~
					 gint	      height,                          //~v@@@I~
					 gint	      depth);                          //~v@@@I~
GdkGC*	gdk_gc_new	(GdkDrawable	    *drawable);                //~v@@@R~
GdkColormap*	gdk_colormap_get_system	        (void);            //~v@@@R~
gboolean	gdk_colormap_alloc_color (GdkColormap    *colormap,    //~v@@@R~
				   GdkColor       *color,                          //~v@@@I~
				   gboolean        writeable,                      //~v@@@I~
				   gboolean        best_match);                    //~v@@@I~
GdkGC*	gdk_gc_new_with_values	  (GdkDrawable	    *drawable,     //~v@@@R~
				   GdkGCValues	    *values,                       //~v@@@I~
				   GdkGCValuesMask   values_mask);                 //~v@@@I~
void	gdk_gc_set_function	  (GdkGC	    *gc,                   //~v@@@R~
				   GdkFunction	     function);                    //~v@@@I~
void	gdk_gc_set_line_attributes (GdkGC	    *gc,               //~v@@@R~
				   gint		     line_width,                       //~v@@@I~
				   GdkLineStyle	     line_style,                   //~v@@@I~
				   GdkCapStyle	     cap_style,                    //~v@@@I~
				   GdkJoinStyle	     join_style);                  //~v@@@I~
void    gdk_event_put	 		(const GdkEvent *event);           //~v@@@R~
void	gdk_pointer_ungrab     (guint32       time_);              //~v@@@I~
void    gdk_keyboard_ungrab    (guint32       time_);              //~v@@@I~
gboolean	gdk_pointer_is_grabbed (void);                         //~v@@@I~
void	      gdk_window_get_geometry	 (GdkWindow	  *window,     //~v@@@I~
					  gint		  *x,                              //~v@@@I~
					  gint		  *y,                              //~v@@@I~
					  gint		  *width,                          //~v@@@I~
					  gint		  *height,                         //~v@@@I~
					  gint		  *depth);                         //~v@@@I~
gchar*  gdk_atom_name   (GdkAtom      atom);                       //~v@@@I~
//*********                                                        //~v@@@I~
//* gtk   *                                                        //~v@@@I~
//*********                                                        //~v@@@I~
void	gtk_main_quit	(void);                                    //~v@@@R~
GtkClipboard*	gtk_clipboard_get	(GdkAtom selection);           //~v@@@R~
gchar*	gtk_clipboard_wait_for_text	(GtkClipboard *clipboard);     //~v@@@R~
GtkIMContext*	gtk_im_multicontext_new	(void);                    //~v@@@R~
GtkWidget*	gtk_message_dialog_new	(GtkWindow      *parent,       //~v@@@R~
                                        GtkDialogFlags  flags,     //~v@@@I~
                                        GtkMessageType  type,      //~v@@@I~
                                        GtkButtonsType  buttons,   //~v@@@I~
                                        const gchar    *message_format,//~v@@@I~
//                                      ...) G_GNUC_PRINTF (5, 6); //~v@@@I~
                                        ...);                      //~v@@@I~
                                                                   //~v@@@I~
GtkWidget*	gtk_dialog_add_button	(GtkDialog   *dialog,          //~v@@@R~
                                         const gchar *button_text, //~v@@@I~
                                         gint         response_id);//~v@@@I~
gint	gtk_dialog_run          (GtkDialog *dialog);               //~v@@@R~
void	gtk_widget_destroy		(GtkWidget	       *widget);       //~v@@@R~
void 	gtk_menu_item_activate	(GtkMenuItem *menu_item);          //~v@@@R~
guint	gtk_timeout_add	(guint32 interval,                         //~v@@@R~
				    GtkFunction	       function,                   //~v@@@I~
				    gpointer	       data);                      //~v@@@I~
void	gtk_timeout_remove	   (guint	       timeout_handler_id);//~v@@@R~
void	gtk_menu_item_set_accel_path	      (GtkMenuItem	   *menu_item,//~v@@@R~
					       const gchar	   *accel_path);           //~v@@@I~
void	gtk_accel_map_add_entry	(const gchar		*accel_path,   //~v@@@R~
					 guint			 accel_key,                    //~v@@@I~
					 GdkModifierType         accel_mods);          //~v@@@I~
gboolean	gtk_accel_map_change_entry(const gchar *accel_path,    //~v@@@R~
					 guint accel_key,                              //~v@@@R~
					 GdkModifierType accel_mods,                   //~v@@@R~
					 gboolean replace);                            //~v@@@R~
void	gtk_label_set_text(GtkLabel *label,                        //~v@@@R~
						   const gchar   *str);                    //~v@@@I~
void 	gtk_label_set_text_with_mnemonic(GtkLabel *label,          //~v@@@R~
						   const gchar      *str);                 //~v@@@I~
void 	gtk_widget_set_sensitive(GtkWidget    *widget,             //~v@@@R~
							 gboolean      sensitive);             //~v@@@I~
void 	gtk_menu_popup(GtkMenu	       *menu,                      //~v@@@R~
					   GtkWidget	       *parent_menu_shell,     //~v@@@I~
					   GtkWidget	       *parent_menu_item,      //~v@@@I~
					   GtkMenuPositionFunc	func,                  //~v@@@I~
					   gpointer		data,                          //~v@@@I~
					   guint		button,                        //~v@@@I~
					   guint32		activate_time);                //~v@@@I~
                                                                   //~v@@@I~
guint32	gtk_get_current_event_time  (void);                        //~v@@@R~
void	gtk_clipboard_set_text(GtkClipboard *clipboard,            //~v@@@R~
				       const gchar           *text,                //~v@@@I~
				       gint                   len);                //~v@@@I~
void	gtk_clipboard_request_text	(GtkClipboard *clipboard,      //~v@@@I~
                                      GtkClipboardTextReceivedFunc callback,//~v@@@I~
                                      gpointer user_data);         //~v@@@I~
void	gtk_signal_emit_by_name			(GtkObject	    *object,   //~v@@@I~
						 const gchar	    *name,                 //~v@@@I~
						 ...);                                     //~v@@@I~
void	gtk_window_set_title	(GtkWindow           *window,      //~v@@@I~
						const gchar         *title);               //~v@@@I~
gchar* gtk_file_selection_get_filename	(GtkFileSelection *filesel);//~v@@@I~
void     gtk_selection_data_set      (GtkSelectionData     *selection_data,//~v@@@I~
				      GdkAtom               type,                  //~v@@@I~
				      gint                  format,                //~v@@@I~
				      const guchar         *data,                  //~v@@@I~
				      gint                  length);               //~v@@@I~
void gtk_drag_finish  (GdkDragContext *context,                    //~v@@@I~
			gboolean        success,                               //~v@@@I~
			gboolean        del,                                   //~v@@@I~
			guint32         time_);                                //~v@@@I~
void	gtk_selection_data_free     (GtkSelectionData *data);      //~v@@@I~
PangoLayout  *gtk_widget_create_pango_layout  (GtkWidget   *widget,//~v@@@I~
					       const gchar *text);                     //~v@@@I~
//*******                                                          //~v@@@I~
//*pango*                                                          //~v@@@I~
//*******                                                          //~v@@@I~
void           pango_layout_set_text       (PangoLayout    *layout,//~v@@@I~
					    const char     *text,                      //~v@@@I~
					    int             length);                   //~v@@@I~
void           pango_layout_set_font_description (PangoLayout                *layout,//~v@@@I~
						  const PangoFontDescription *desc);       //~v@@@I~
PangoFontDescription *pango_font_description_from_string (const char                  *str);//~v@@@I~
char *                pango_font_description_to_string   (const PangoFontDescription  *desc);//~v@@@I~
                                                                   //~v@@@I~
//***********                                                      //~v@@@R~
//anjuta    *                                                      //~v@@@R~
//***********                                                      //~v@@@I~
GtkWidget *lookup_widget(GtkWidget *Pwidget,char *Pname);          //~v@@@R~
    #include <gdkkeysyms.h>                                        //+v@@@R~
//************                                                     //~v@@@I~
//alternative*                                                     //~v@@@I~
//************                                                     //~v@@@I~
int jnig_upopupmenu_menuitem_enable(int Ptblsz,int Pidx,int Penable);//~v@@@I~
//************                                                     //~v@@@I~
//gnome                                                            //~v@@@I~
//************                                                     //~v@@@I~
typedef enum {                                                     //~v@@@M~
	GNOME_VFS_MIME_ACTION_TYPE_NONE,                               //~v@@@M~
	GNOME_VFS_MIME_ACTION_TYPE_APPLICATION,                        //~v@@@M~
	GNOME_VFS_MIME_ACTION_TYPE_COMPONENT                           //~v@@@M~
} GnomeVFSMimeActionType;                                          //~v@@@M~
typedef enum {                                                     //~v@@@I~
	GNOME_VFS_MIME_APPLICATION_ARGUMENT_TYPE_URIS,                 //~v@@@I~
	GNOME_VFS_MIME_APPLICATION_ARGUMENT_TYPE_PATHS,                //~v@@@I~
	GNOME_VFS_MIME_APPLICATION_ARGUMENT_TYPE_URIS_FOR_NON_FILES    //~v@@@I~
} GnomeVFSMimeApplicationArgumentType;                             //~v@@@I~
typedef struct {                                                   //~v@@@I~
	char *name;                                                    //~v@@@I~
	char *command;                                                 //~v@@@I~
	GnomeVFSMimeApplicationArgumentType expects_uris;              //~v@@@I~
	gboolean requires_terminal;                                    //~v@@@I~
                                                                   //~v@@@I~
} GnomeVFSMimeApplication;                                         //~v@@@I~
GnomeVFSMimeApplication *gnome_vfs_mime_get_default_application                   (const char              *mime_type);//~v@@@I~
GnomeVFSMimeApplication *gnome_vfs_mime_get_default_application_dataandtype(char *Puri,char *mime_type);//~v@@@I~
char                   *gnome_vfs_get_mime_type               (const char    *text_uri);//~v@@@I~
GnomeVFSMimeActionType   gnome_vfs_mime_get_default_action_type            (const char              *mime_type);//~v@@@I~
char *gnome_vfs_get_uri_from_local_path      (const char      *local_full_path);//~v@@@I~
void                     gnome_vfs_mime_application_free                          (GnomeVFSMimeApplication *application);//~v@@@I~
                                                                   //~v@@@I~
struct _GtkTargetList {                                            //~v@@@I~
  GList *list;                                                     //~v@@@I~
  guint ref_count;                                                 //~v@@@I~
};                                                                 //~v@@@I~
typedef struct _GtkTargetList    GtkTargetList;                    //~v@@@I~
struct _GtkTargetEntry {                                           //~v@@@I~
  gchar *target;                                                   //~v@@@I~
  guint  flags;                                                    //~v@@@I~
  guint  info;                                                     //~v@@@I~
};                                                                 //~v@@@I~
typedef struct _GtkTargetEntry   GtkTargetEntry;                   //~v@@@I~
#endif                                                             //~v@@@M~
