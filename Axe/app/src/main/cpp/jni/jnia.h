//*CID://+vc2LR~:                                   update#=  195; //+vc2LR~
//**************************************************************** //~1610I~
//jnia.h                                                           //~vay0R~
//**************************************************************** //~1610I~
//vc2L 2020/09/02 display TMPDIR                                   //+vc2LI~
//vc2g 2020/07/26 AltGr key option                                 //~vc2gI~
//vc1p 2020/06/24 display path env                                 //~vc1iI~
//vc1i 2020/06/22 display not /emulated/.. but /sdcard to header CWD=//~vc1iI~
//vc1f 2020/06/20 ARM;chk sdcard writable                          //~vc1fI~
//vc1d 2020/06/20 ARM;use private dir when SDcard not available    //~vc1dI~
//vc1c 2020/06/19 ARM;/proc/version access denied, use Build.VERSION.SDK_INT R RELEASE//~vc1cI~
//vc03 2020/06/10 #include jni.h required to CMake                 //~vc03I~
//vay7:141122 (Axe)actionBar:save/saveas item                      //~vay7I~
//vay0:140710 (Axe)jni exception handling                          //~vay0I~
//vad2:120423 (Axe)Charset option by Arm related option dialog     //~vad2I~
//v6dg:120220 (Axe)ftp downloaded file attr is 075(sdcard is read only and FAT); change to private dir//~v6dgI~
//vac6:120217 (Axe)samba share support using jcifs 3.17            //~vac6I~
//vabc:120126 (Axe)for quick startup,split asset to another zip file; user will download and unzip into /data/data/com.xe.Axe/files//~vabcI~
//vab7:120122 (Axe)Android4:getTextWidth returns width=0 if request count is too large//~vab7I~
//v6bq:111221 icu additional directory name should be icudtxxl     //~v6bqI~
//**************************************************************   //~1616M~
#include <jni.h>                                                   //~vc03I~
#ifdef JNIGBL                                                      //~1616M~
	#define JNIEXT                                                 //~1616M~
#else                                                              //~1616M~
	#define JNIEXT	extern                                         //~1616M~
#endif                                                             //~1616M~
//*************************************************                //~1714I~
#define JNI_VERSION JNI_VERSION_1_6                                //~1714I~
//*************************************************                //~vac6I~
//Z:boolean                                                        //~vac6I~
//B:byte                                                           //~vac6I~
//C:char                                                           //~vac6I~
//S:short                                                          //~vac6I~
//I:int                                                            //~vac6I~
//J:long                                                           //~vac6I~
//F:float                                                          //~vac6I~
//D:double                                                         //~vac6I~
//L:objectname prefix end with ";"                                 //~vac6I~
//[:array prefix                                                   //~vac6I~
//*************************************************                //~1714I~
JavaVM  *Gvm;                                                      //~1714I~
//JNIEXT JNIEnv *Gpjnienv;                                         //~1714R~
//JNIEXT jobject Gthis;                                            //~1714R~
JNIEXT jclass  Gclass;                                             //~1713I~
JNIEXT jclass  GclassGxeh;                                         //~1716I~
JNIEXT int     GjniScrW,GjniScrH;                                  //~1617R~
JNIEXT int     GaxeStatus;                                         //~vc1dI~
#define AXES_NOPERMISSION_EXTERNAL_STIORAGE_WRITE 0x01             //~vc1dI~
#define AXES_NOT_CANWRITE                         0x02             //~vc1fI~
#define AXES_SDCARD_ALTNAME                       0x04  // altname /sdcard is available//~vc1iI~
#define AXES_ALTGR_RIGHTALT                       0x08  // use Left-Alt as AltGr//~vc2gR~
#define AXES_ALTGR_LEFTALT                        0x10  // use Left-Alt as AltGr//~vc2gI~
#define AXES_ALTGR_RIGHTSHIFT                     0x20  // use RightShift as AltGr//~vc2gR~
JNIEXT char   *Gjnisdpath;                                         //~1A06I~
JNIEXT char   *GjnisdRoot;                                         //~vc1fI~
JNIEXT char   *GjnisdRootPath;                                     //~vc1iI~
JNIEXT char   *GenvVarPATH;                                        //~vc1iI~
JNIEXT char   *Gjniprivatepath;                                    //~v6dgI~
JNIEXT char   *Gdndselection;                                      //~1A19I~
JNIEXT char   *Gicuswfile;                                         //~v6bqI~
                                                                   //~1716I~
JNIEXT jfieldID staticFID_Mcellw;                                  //~1716I~
JNIEXT jfieldID staticFID_Mcellh;                                  //~1716I~
JNIEXT jfieldID staticFID_Mmonospace;                              //~1716I~
JNIEXT jfieldID staticFID_MbaseLine;                               //~1823R~
JNIEXT jfieldID staticFID_Mfontstyle;                              //~1803I~
JNIEXT jfieldID staticFID_Mscrcmaxrow;                             //~1803R~
JNIEXT jfieldID staticFID_Mscrcmaxcol;                             //~1803R~
JNIEXT jfieldID staticFID_Mcellh0;                                 //~1803I~
JNIEXT jfieldID staticFID_Mcellw0;                                 //~1803I~
JNIEXT jfieldID staticFID_Mbgcolor;                                //~1803I~
JNIEXT jfieldID staticFID_Mrulercolor;                             //~1803I~
JNIEXT jfieldID staticFID_Museact;                                 //~1803I~
JNIEXT jfieldID staticFID_Mbeep;                                   //~1803R~
JNIEXT jfieldID staticFID_Mqexit;                                  //~1803I~
JNIEXT jfieldID staticFID_Mfreecsr;                                //~1929I~
JNIEXT jfieldID staticFID_Mrulermode;                              //~1803I~
JNIEXT jfieldID staticFID_Mligature;                               //~1803I~
JNIEXT jfieldID staticFID_Mstyle;                                  //~1803I~
//JNIEXT jfieldID staticFID_Mfontsize;                             //~1822R~
JNIEXT jfieldID staticFID_Mfontheight;                             //~1809I~
JNIEXT jfieldID staticFID_Mfontwidth;                              //~1809I~
JNIEXT jfieldID staticFID_homeDir;                                 //~1A12I~
JNIEXT jfieldID staticFID_envVarTMPDIR;                            //+vc2LI~
JNIEXT jfieldID staticFID_privateTop;                              //~vabcI~
JNIEXT jfieldID staticFID_addPath;                                 //~1A25I~
JNIEXT jfieldID staticFID_initCmd;                                 //~1A17I~
JNIEXT jfieldID staticFID_sdRootPath;                              //~vc1iI~
JNIEXT jfieldID staticFID_initFile;                                //~1A17I~
JNIEXT jfieldID staticFID_envPath;                                 //~1A26I~
JNIEXT jfieldID staticFID_envVarPATH;                              //~vc1iI~
JNIEXT jfieldID staticFID_icuSwFile;                               //~v6bqI~
JNIEXT jfieldID staticFID_osVersion;                               //~vab7R~
JNIEXT jfieldID staticFID_osVersionRelease;                        //~vc1cI~
JNIEXT jfieldID staticFID_axeStatus;                               //~vc1dI~
JNIEXT jfieldID staticFID_localeCharset;                           //~vad2I~
                                                                   //~vac6I~
JNIEXT jfieldID staticFID_SMB_namelist;                            //~vac6I~
JNIEXT jfieldID staticFID_SMB_fileno;                              //~vac6I~
JNIEXT jfieldID staticFID_SMB_attrlist;                            //~vac6I~
JNIEXT jfieldID staticFID_SMB_sizelist;                            //~vac6I~
JNIEXT jfieldID staticFID_SMB_timelist;                            //~vac6I~
JNIEXT jfieldID staticFID_SMB_name;                                //~vac6I~
JNIEXT jfieldID staticFID_SMB_attr;                                //~vac6I~
JNIEXT jfieldID staticFID_SMB_size;                                //~vac6I~
JNIEXT jfieldID staticFID_SMB_time;                                //~vac6I~
JNIEXT jfieldID staticFID_SMB_errmsg;                              //~vac6I~
JNIEXT jfieldID staticFID_mCurrentFilename;                        //~vay7I~
JNIEXT jfieldID staticFID_mCurrentFilenameType;                    //~vay7I~
                                                                   //~1719I~
//JNIEXT int MjniBaseLine;	//textDraw Y position                  //~1823R~
//JNIEXT int MjniFontoffsx;	//textDraw X position                  //~1823R~
//**************************************************************   //~1616M~
#define PRINT_EXCEPTION(env,comment) \
    if ((*(env))->ExceptionCheck(env)) \
    {                                            \
    	UTRACEP("ExceptionCheck:%s\n",comment);  \
	    (*(env))->ExceptionDescribe(env);        \
    }                                                              //~1715I~
//*array JNI func dose not indicate by return code;but may throwed exception//~1715I~
#define CHK_ARRAY_EXCEPTION(env,comment) \
	{                                     \
    	jthrowable jt;                     \
	    jt=(*(env))->ExceptionOccurred(env); \
    	if (jt!=NULL)                        \
        {                                     \
	    	UTRACEP("CHK_ARRAY_EXCEPTION:%s\n",comment);  \
		    (*(env))->ExceptionDescribe(env);        \
            (*(env))->ExceptionClear(env);        \
			(*(env))->Throw(env,jt);             \
        }                                         \
    }                                                              //~1715I~
                                                                   //~1715M~
#define THROW_JAVA_EXCEPTION_IF_SAVED(env,throwable)   \
    if ((throwable)!=NULL) (*(env))->Throw(env,throwable)          //~1715M~
//*see jni.h                                                       //~1715M~
#define C2J_PRE(env,method)      \
                                 do {                              \
										char funcid[MAX_FUNCID];   \
										jnisigh_handler_save(env,#method,funcid);//~vay0M~
#define C2J_POST(env)            \
										jnisigh_handler_restore(env,funcid);\
									}while(0)                      //~vay0M~
//#define C2J_VOIDVOID(env,method)  (*env)->CallStaticVoidMethod(env,Gclass,staticMethod_##method)//~1714R~//~vay0R~
#define C2J_VOIDVOID(env,method) \
                               	C2J_PRE(env,method)\
									(*env)->CallStaticVoidMethod(env,Gclass,staticMethod_##method);\
								C2J_POST(env)                      //~vay0I~
//#define C2J_VOID(env,method,...)  (*env)->CallStaticVoidMethod(env,Gclass,staticMethod_##method,__VA_ARGS__)//~vay0R~
#define C2J_VOID(env,method,...)  \
                               	C2J_PRE(env,method)\
									(*env)->CallStaticVoidMethod(env,Gclass,staticMethod_##method,__VA_ARGS__);\
								C2J_POST(env)                      //~vay0I~
//#define C2J_INT(env,method,...)   (*env)->CallStaticIntMethod(env,Gclass,staticMethod_##method,__VA_ARGS__)//~1714R~//~vay0R~
#define C2J_INT(ret,env,method,...)  \
                               	C2J_PRE(env,method)\
						*(ret)=(int)(*env)->CallStaticIntMethod(env,Gclass,staticMethod_##method,__VA_ARGS__); \
								C2J_POST(env)                      //~vay0R~
//#define C2J_OBJVOID(env,method)   (*env)->CallStaticObjectMethod(env,Gclass,staticMethod_##method)//~1714R~//~1718R~//~vay0R~
#define C2J_OBJVOID(obj,env,method)  \
                               	C2J_PRE(env,method)\
							 *(obj)=(*env)->CallStaticObjectMethod(env,Gclass,staticMethod_##method);\
								C2J_POST(env)                      //~vay0I~
//#define C2J_OBJ(env,method,...)   (*env)->CallStaticObjectMethod(env,Gclass,staticMethod_##method,__VA_ARGS__)//~vay0R~
#define C2J_OBJ(obj,env,method,...) C2J_PRE(env,method)  \
							 *(obj)=(*env)->CallStaticObjectMethod(env,Gclass,staticMethod_##method,__VA_ARGS__);\
                                C2J_POST(env)                      //~vay0R~
#define C2J_GETSTATICMETHODID(env,mname,parm)   (*env)->GetStaticMethodID(env,Gclass,mname,parm)//~1716R~
#define C2J_GETMETHODID_RETIFERR(env,mname,parm)         \
	{                                                \
    	jmethodID method;                            \
        method=staticMethod_##mname;                  \
        if (!method)                                 \
        {                                            \
        	method=C2J_GETSTATICMETHODID(env,#mname,parm);      \
            if (!method)                             \
            {                                        \
                UTRACEP("method(%s) not found\n",#mname);\
				PRINT_EXCEPTION(env,"C2J_GETMETHODID_RETIFERR") \
                return;                              \
            }                                        \
	        staticMethod_##mname=method;               \
            UTRACEP("method(%s) is %p\n",#mname,staticMethod_##mname);\
        }                                            \
    }                                                              //~1616M~
#define C2J_GETMETHODID_GOTOIFERR(env,mname,parm)         \
	{                                                \
    	jmethodID method;                            \
        method=staticMethod_##mname;                  \
        if (!method)                                 \
        {                                            \
        	method=C2J_GETSTATICMETHODID(env,#mname,parm);      \
            if (!method)                             \
            {                                        \
                UTRACEP("method(%s) not found\n",#mname);\
				PRINT_EXCEPTION(env,"C2J_GETMETHODID_GOTOIFERR") \
                goto ret;                            \
            }                                        \
	        staticMethod_##mname=method;               \
            UTRACEP("method(%s) is %p\n",#mname,staticMethod_##mname);\
        }                                            \
    }                                                              //~1715I~
#define C2J_GETMETHODID_RETIFERR_WITHRC(env,rc,mname,parm)         \
	{                                                \
    	jmethodID method;                            \
        method=staticMethod_##mname;                  \
        if (!method)                                 \
        {                                            \
        	method=C2J_GETSTATICMETHODID(env,#mname,parm);      \
            if (!method)                             \
            {                                        \
                UTRACEP("method(%s) not found\n",#mname);\
				PRINT_EXCEPTION(env,"C2J_GETMETHODID_GOTOIFERR_WITHRC") \
                return (rc);                         \
            }                                        \
	        staticMethod_##mname=method;               \
            UTRACEP("method(%s) is %p\n",#mname,staticMethod_##mname);\
        }                                            \
    }                                                              //~1618I~
                                                                   //~1714I~
#define GETSTATIC_INT(env,fldname) (int)getStaticJint(env,&staticFID_##fldname,#fldname)//~1716R~//~1718R~
#define SETSTATIC_INT(env,fldname,value) (int)setStaticJint(env,&staticFID_##fldname,#fldname,value)//~1803R~
#define GETSTATIC_LONG(env,fldname) (jlong)getStaticJlong(env,&staticFID_##fldname,#fldname)//~vac6R~
#define SETSTATIC_BOOLEAN(env,fldname,value) (int)setStaticJboolean(env,&staticFID_##fldname,#fldname,value)//~1803R~
#define GETSTATIC_STRING(env,fldname) getStaticJstring(0,env,&staticFID_##fldname,#fldname)//~1A12R~//~vac6R~
#define GETSTATIC_STRING_UNREF(env,fldname) getStaticJstring(GSO_UNREF,env,&staticFID_##fldname,#fldname)//~vac6I~
#define SETSTATIC_STRING(env,fldname,value) (int)setStaticJstring(env,&staticFID_##fldname,#fldname,value)//~1A12I~
                                                                   //~vac6I~
#define GSO_UNREF  0x01                                            //~vac6I~
#define GETSTATIC_INTARRAY_UNREF(env,fldname,out) getStaticJintArray(GSO_UNREF,env,&staticFID_##fldname,#fldname,out)//~vac6R~
#define GETSTATIC_LONGARRAY_UNREF(env,fldname,out) getStaticJlongArray(GSO_UNREF,env,&staticFID_##fldname,#fldname,out)//~vac6R~
#define GETSTATIC_STRARRAY_UNREF(env,fldname,out) getStaticJstringArray(GSO_UNREF,env,&staticFID_##fldname,#fldname,out)//~vac6R~
                                                                   //~1803I~
//**************************************************************** //~1623I~
JNIEnv *getThreadEnv(void);                                        //~1714I~
jbyteArray byte2jbyte(JNIEnv *Penv,char *Pbyte,int Plen);          //~1714R~
jintArray int2jint(JNIEnv *Penv,int *Pint,int Plen);               //~1715I~
jintArray copyint2jint(JNIEnv *Penv,jintArray Pjia,int *Pint,int Plen);//~1822I~
jbyteArray newjbyte(JNIEnv *Penv,int Plen);                        //~1714R~
void jbyte2byte(JNIEnv *Penv,jbyteArray Pjba,int Ppos,int Plen,char *Pout);//~1714R~
void jint2int(JNIEnv *Penv,jintArray Pjia,int Ppos,int Plen,int *Pout);//~1715I~
//void jlong2long(JNIEnv *Penv,jlongArray Pjia,int Ppos,int Plen,long *Pout);//~vac6R~
jlong *getjlongarray(JNIEnv *Penv,jlongArray Pjia,int Ppos,int Plen);//~vac6R~
void unrefGlobal(JNIEnv *Penv,jobject Pobject);                    //~1715R~
void unrefLocal(JNIEnv *Penv,jobject Pobject);                     //~1715R~
char *jstring2char(JNIEnv *Penv,jstring Pjstr);                    //~1714R~
int jstring2buff(JNIEnv *Penv,jstring Pjstr,char *Pout,int Pbuffsz);//~1803I~
jstring utf8z2jstring(JNIEnv *Penv,char *Putf8);                   //~1715I~
jstring utf82jstring(JNIEnv *Penv,char *Putf8,int Plen);           //~1715I~
int getStaticJint(JNIEnv *Penv,jfieldID *Ppfid,char *Pfname);      //~1716R~
int setStaticJint(JNIEnv *Penv,jfieldID *Ppfid,char *Pfname,int Pvalue);//~1803I~
long getStaticJlong(JNIEnv *Penv,jfieldID *Ppfid,char *Pfname);    //~vac6I~
int setStaticJboolean(JNIEnv *Penv,jfieldID *Ppfid,char *Pfname,int Pvalue);//~1803I~
//char *getStaticJstring(JNIEnv *Penv,jfieldID *Ppfid,char *Pfname); //~1A12R~//~vac6R~
char *getStaticJstring(int Popt,JNIEnv *Penv,jfieldID *Ppfid,char *Pfname);//~vac6I~
int setStaticJstring(JNIEnv *Penv,jfieldID *Ppfid,char *Pfname,char *Pvalue);//~1A12I~
jfloatArray newjfloat(JNIEnv *Penv,int Plen);                      //~1718I~
void jfloat2float(JNIEnv *Penv,jintArray Pjfa,int Ppos,int Plen,float *Pout);//~1718I~
jintArray float2jfloat(JNIEnv *Penv,float *Pfloat,int Plen);       //~1719I~
jfloatArray getgbljfloat(JNIEnv *Penv,int Psize);                  //~1A14I~
jintArray getgbljint(JNIEnv *Penv,int Psize);                      //~1A14I~
jintArray gblint2jint(JNIEnv *Penv,int *Psrc,int Psize);           //~1A14R~
jfloatArray gblfloat2jfloat(JNIEnv *Penv,float *Psrc,int Psize);   //~1A14R~
jintArray copyfloat2float(JNIEnv *Penv,jfloatArray Pobj,float *Psrc,int Plen);//~1A14I~
int getStaticJintArray(int Popt,JNIEnv *Penv,jfieldID *Ppfid,char *Pfname,int **Ppint);//~vac6R~
int setStaticJintArray(JNIEnv *Penv,jfieldID *Ppfid,char *Pfname,jobject Pvalue);//~vac6R~
int getStaticJlongArray(int Popt,JNIEnv *Penv,jfieldID *Ppfid,char *Pfname,jlong **Ppjlong);//~vac6I~
int setStaticJlongArray(JNIEnv *Penv,jfieldID *Ppfid,char *Pfname,jobject Pvalue);//~vac6R~
int getStaticJstringArray(int Popt,JNIEnv *Penv,jfieldID *Ppfid,char *Pfname,char ***Pppc);//~vac6R~
int setStaticJstringArray(JNIEnv *Penv,jfieldID *Ppfid,char *Pfname,jobject Pvalue);//~vac6R~
//*****************                                                //~1718I~
void *getvoidtb(int Ptbid,int Psize);                              //~1718I~
#define GETTB_TEXTWIDTHTB   1                                      //~1718R~
#define GETTB_POSTB         2                                      //~1718R~//~1719R~
#define GETTB_GEOM          3                                      //~1719I~
#define GETTB_MAXTB         4                                      //~1718R~
