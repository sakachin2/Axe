//*CID://+vay0R~:                                   update#=  284; //~vay0R~
//**************************************************************** //~1610I~
//vay0:140710 (Axe)jni exception handling                          //~vay0I~
//vac6:120217 (Axe)samba share support using jcifs 3.17            //~vac6I~
//vab3:120119 (Axe)Warning:DeleteGlobalRef on non-global type=1(android4 changed jobjct from direct pointer to opaque handle)//~vab3I~
//**************************************************************** //~vab3I~
//dummy rtn for gtk on ARM                                         //~1616I~
//**************************************************************** //~1610I~
#include <stdio.h>                                                 //~1610I~
#include <string.h>                                                //~1614I~
//**********************                                           //~1610I~
#include <jni.h>                                                   //~1616R~
#include <jnia.h>                                                  //~1616I~
#include <jnisigh.h>                                               //~vay0I~
#include <android/log.h>                                           //~1616I~
                                                                   //~1618I~
#include <ulib.h>                                                  //~1623I~
#include <ualloc.h>                                                //~1623I~
#include <ustring.h>                                               //~1623I~
#include <utrace.h>                                                //~1715I~
#include <utf2.h>                                                  //~vac6I~
                                                                   //~1623I~
//*************************************************                //~1617I~
//*************************************************                //~1617I~
//*************************************************                //~1622I~
//*************************************************                //~1714I~
JNIEnv *getThreadEnv(void)                                         //~1714I~
{                                                                  //~1714I~
	JNIEnv *env;                                                   //~1714R~
    jint rc;                                                       //~1714I~
//******************                                               //~1714I~
	rc=(*Gvm)->GetEnv(Gvm,(void**)&env,JNI_VERSION);               //~1714R~
//  LOGPD("getThreadEnv env=%p,vm=%p,rc=%d",env,Gvm,rc);           //~1718R~
    if (rc!=JNI_OK)                                                //~1714I~
    	env=NULL;                                                  //~1714R~
    return env;                                                    //~1714R~
}//getVM                                                           //~1714I~
//*************************************************                //~1716I~
int getStaticJint(JNIEnv *Penv,jfieldID *Ppfid,char *Pfname)       //~1716R~
{                                                                  //~1716I~
	jfieldID fid;                                                  //~1716I~
    int value;                                                     //~1716I~
//******************                                               //~1716I~
    UTRACEP("getStaticJInt fieldname=%s\n",Pfname);                //~1716I~
	fid=*Ppfid;                                                    //~1716I~
	if (!fid)                                                      //~1716I~
    {                                                              //~1716I~
		fid=(*Penv)->GetStaticFieldID(Penv,GclassGxeh,Pfname,"I"); //~1716R~
        if (!fid)                                                  //~1716I~
        {                                                          //~1716I~
        	UTRACEP("Field(%s) not found\n",Pfname);               //~1716I~
			PRINT_EXCEPTION(Penv,"getStaticJint")                 //~1716R~//~1803R~
            return 0;                                              //~1716I~
        }                                            \
	    *Ppfid=fid;                                                //~1716I~
    }                                                              //~1716I~
	value=(int)((*Penv)->GetStaticIntField(Penv,GclassGxeh,fid));  //~1716R~
    UTRACEP("getStaticJInt fld=%s=%d,fldid=%p\n",Pfname,value,fid);//~1716R~
    return value;                                                  //~1716I~
}//getStatcJint                                                    //~1716I~
//*************************************************                //~1803I~
int setStaticJint(JNIEnv *Penv,jfieldID *Ppfid,char *Pfname,int Pvalue)//~1803R~
{                                                                  //~1803I~
	jfieldID fid;                                                  //~1803I~
    int rc=0;                                                      //~1803R~
//******************                                               //~1803I~
    UTRACEP("setStaticJInt fieldname=%s\n",Pfname);                //~1803I~
	fid=*Ppfid;                                                    //~1803I~
	if (!fid)                                                      //~1803I~
    {                                                              //~1803I~
		fid=(*Penv)->GetStaticFieldID(Penv,GclassGxeh,Pfname,"I"); //~1803I~
        if (!fid)                                                  //~1803I~
        {                                                          //~1803I~
        	UTRACEP("Field(%s) not found\n",Pfname);               //~1803I~
			PRINT_EXCEPTION(Penv,"setStaticJint")                  //~1803R~
            return 4;                                              //~1803I~//~vac6R~
        }                                            \
	    *Ppfid=fid;                                                //~1803I~
    }                                                              //~1803I~
	(*Penv)->SetStaticIntField(Penv,GclassGxeh,fid,Pvalue);        //~1803R~
    UTRACEP("setStaticJInt rc=%d,fld=%s,fldid=%p,value=%d\n",rc,Pfname,fid,Pvalue);//~1803R~
    return rc;                                                     //~1803R~
}//getStatcJint                                                    //~1803I~
//*************************************************                //~vac6I~
long getStaticJlong(JNIEnv *Penv,jfieldID *Ppfid,char *Pfname)     //~vac6I~
{                                                                  //~vac6I~
	jfieldID fid;                                                  //~vac6I~
    long value;                                                    //~vac6I~
//******************                                               //~vac6I~
    UTRACEP("getStaticJlong fieldname=%s\n",Pfname);               //~vac6I~
	fid=*Ppfid;                                                    //~vac6I~
	if (!fid)                                                      //~vac6I~
    {                                                              //~vac6I~
		fid=(*Penv)->GetStaticFieldID(Penv,GclassGxeh,Pfname,"J"); //~vac6I~
        if (!fid)                                                  //~vac6I~
        {                                                          //~vac6I~
        	UTRACEP("Field(%s) not found\n",Pfname);               //~vac6I~
			PRINT_EXCEPTION(Penv,"getStaticJlong")                 //~vac6I~
            return 0;                                              //~vac6I~
        }                                            \
	    *Ppfid=fid;                                                //~vac6I~
    }                                                              //~vac6I~
	value=(long)((*Penv)->GetStaticIntField(Penv,GclassGxeh,fid)); //~vac6I~
    UTRACEP("getStaticJlong fld=%s=%x,fldid=%p\n",Pfname,value,fid);//~vac6I~
    return value;                                                  //~vac6I~
}//getStatcJint                                                    //~vac6I~
//*************************************************                //~vac6I~
//*return array size                                               //~vac6I~
//*************************************************                //~vac6I~
int getStaticJintArray(int Popt,JNIEnv *Penv,jfieldID *Ppfid,char *Pfname,int **Ppint)//~vac6R~
{                                                                  //~vac6I~
	jfieldID fid;                                                  //~vac6I~
    jobject value;                                                 //~vac6I~
    int *pint,sz=0;                                                //~vac6R~
//******************                                               //~vac6I~
    *Ppint=0;                                                      //~vac6I~
    UTRACEP("setStaticJIntArray fieldname=%s\n",Pfname);           //~vac6I~
	fid=*Ppfid;                                                    //~vac6I~
	if (!fid)                                                      //~vac6I~
    {                                                              //~vac6I~
		fid=(*Penv)->GetStaticFieldID(Penv,GclassGxeh,Pfname,"[I");//~vac6I~
        if (!fid)                                                  //~vac6I~
        {                                                          //~vac6I~
        	UTRACEP("Field(%s) not found\n",Pfname);               //~vac6I~
			PRINT_EXCEPTION(Penv,"setStaticJintArray")             //~vac6I~
            return 0;                                              //~vac6I~
        }                                            \
	    *Ppfid=fid;                                                //~vac6I~
    }                                                              //~vac6I~
	value=(*Penv)->GetStaticObjectField(Penv,GclassGxeh,fid);      //~vac6R~
    if (value)                                                     //~vac6I~
    {                                                              //~vac6I~
        sz=(*Penv)->GetArrayLength(Penv,(jarray)value);            //~vac6R~
        if (sz>0)                                                  //~vac6R~
        {                                                          //~vac6R~
            pint=umalloc(sizeof(int)*sz);                          //~vac6R~
            jint2int(Penv,(jintArray)value,0,sz,pint);             //~vac6R~
            if (Popt & GSO_UNREF)                                  //~vac6R~
                setStaticJintArray(Penv,Ppfid,Pfname,(jobject)NULL);//~vac6R~
            *Ppint=pint;                                           //~vac6R~
        }                                                          //~vac6R~
    }                                                              //~vac6I~
    UTRACEP("setStaticJInt fld=%s,fldid=%p,array sz=%d\n",Pfname,fid,sz);//~vac6R~
    return sz;                                                     //~vac6R~
}//getStatcJintArray                                               //~vac6R~
//*************************************************                //~vac6I~
int setStaticJintArray(JNIEnv *Penv,jfieldID *Ppfid,char *Pfname,jobject Pvalue)//~vac6R~
{                                                                  //~vac6I~
	jfieldID fid;                                                  //~vac6I~
    int rc=0;                                                      //~vac6I~
//******************                                               //~vac6I~
    UTRACEP("setStaticJintArray fieldname=%s\n",Pfname);           //~vac6R~
	fid=*Ppfid;                                                    //~vac6I~
	if (!fid)                                                      //~vac6I~
    {                                                              //~vac6I~
		fid=(*Penv)->GetStaticFieldID(Penv,GclassGxeh,Pfname,"[I");//~vac6I~
        if (!fid)                                                  //~vac6I~
        {                                                          //~vac6I~
        	UTRACEP("Field(%s) not found\n",Pfname);               //~vac6I~
			PRINT_EXCEPTION(Penv,"setStaticJint")                  //~vac6I~
            return 4;                                              //~vac6R~
        }                                            \
	    *Ppfid=fid;                                                //~vac6I~
    }                                                              //~vac6I~
	(*Penv)->SetStaticObjectField(Penv,GclassGxeh,fid,Pvalue);     //~vac6I~
    UTRACEP("setStaticObject rc=%d,fld=%s,fldid=%p\n",rc,Pfname,fid);//~vac6R~
    return rc;                                                     //~vac6I~
}//getStatcJint                                                    //~vac6I~
//*************************************************                //~vac6I~
//*return array size                                               //~vac6I~
//*************************************************                //~vac6I~
int getStaticJlongArray(int Popt,JNIEnv *Penv,jfieldID *Ppfid,char *Pfname,jlong **Ppjlong)//~vac6R~
{                                                                  //~vac6I~
	jfieldID fid;                                                  //~vac6I~
    jobject value;                                                 //~vac6I~
    int sz=0;                                                      //~vac6R~
    jlong *pjlong;                                                 //~vac6I~
//******************                                               //~vac6I~
    UTRACEP("setStaticJlongArray fieldname=%s\n",Pfname);          //~vac6I~
    *Ppjlong=0;                                                    //~vac6R~
	fid=*Ppfid;                                                    //~vac6I~
	if (!fid)                                                      //~vac6I~
    {                                                              //~vac6I~
		fid=(*Penv)->GetStaticFieldID(Penv,GclassGxeh,Pfname,"[J");//~vac6R~
        if (!fid)                                                  //~vac6I~
        {                                                          //~vac6I~
        	UTRACEP("Field(%s) not found\n",Pfname);               //~vac6I~
			PRINT_EXCEPTION(Penv,"setStaticJlongArray");           //~vac6R~
            return 0;                                              //~vac6I~
        }                                            \
	    *Ppfid=fid;                                                //~vac6I~
    }                                                              //~vac6I~
	value=(*Penv)->GetStaticObjectField(Penv,GclassGxeh,fid);      //~vac6R~
    if (value)                                                     //~vac6I~
    {                                                              //~vac6I~
        sz=(*Penv)->GetArrayLength(Penv,(jarray)value);            //~vac6R~
        if (sz>0)                                                  //~vac6R~
        {                                                          //~vac6R~
            pjlong=getjlongarray(Penv,(jlongArray)value,0,sz);     //~vac6R~
            if (Popt & GSO_UNREF)                                  //~vac6R~
                setStaticJlongArray(Penv,Ppfid,Pfname,(jobject)NULL);//~vac6R~
            *Ppjlong=pjlong;                                       //~vac6R~
        }                                                          //~vac6R~
    }                                                              //~vac6I~
    UTRACEP("setStaticJInt fld=%s,fldid=%p,sz=%d\n",Pfname,fid,sz);//~vac6R~
    return sz;                                                     //~vac6R~
}//getStatcJlongArray                                              //~vac6R~
//*************************************************                //~vac6I~
int setStaticJlongArray(JNIEnv *Penv,jfieldID *Ppfid,char *Pfname,jobject Pvalue)//~vac6R~
{                                                                  //~vac6I~
	jfieldID fid;                                                  //~vac6I~
    int rc=0;                                                      //~vac6I~
//******************                                               //~vac6I~
    UTRACEP("setStaticJlongArray fieldname=%s\n",Pfname);          //~vac6I~
	fid=*Ppfid;                                                    //~vac6I~
	if (!fid)                                                      //~vac6I~
    {                                                              //~vac6I~
		fid=(*Penv)->GetStaticFieldID(Penv,GclassGxeh,Pfname,"[J");//~vac6I~
        if (!fid)                                                  //~vac6I~
        {                                                          //~vac6I~
        	UTRACEP("Field(%s) not found\n",Pfname);               //~vac6I~
			PRINT_EXCEPTION(Penv,"setStaticJint")                  //~vac6I~
            return 4;                                              //~vac6R~
        }                                            \
	    *Ppfid=fid;                                                //~vac6I~
    }                                                              //~vac6I~
	(*Penv)->SetStaticObjectField(Penv,GclassGxeh,fid,Pvalue);     //~vac6I~
    UTRACEP("setStaticObject rc=%d,fld=%s,fldid=%p\n",rc,Pfname,fid);//~vac6R~
    return rc;                                                     //~vac6I~
}//getStatcJint                                                    //~vac6I~
//*************************************************                //~1803I~
int setStaticJboolean(JNIEnv *Penv,jfieldID *Ppfid,char *Pfname,int Pvalue)//~1803R~
{                                                                  //~1803I~
	jfieldID fid;                                                  //~1803I~
    int rc=0;                                                      //~1803R~
//******************                                               //~1803I~
    UTRACEP("setStaticJBoolean fieldname=%s\n",Pfname);            //~1803I~
	fid=*Ppfid;                                                    //~1803I~
	if (!fid)                                                      //~1803I~
    {                                                              //~1803I~
		fid=(*Penv)->GetStaticFieldID(Penv,GclassGxeh,Pfname,"Z"); //boolean//~1803I~
        if (!fid)                                                  //~1803I~
        {                                                          //~1803I~
        	UTRACEP("Field(%s) not found\n",Pfname);               //~1803I~
			PRINT_EXCEPTION(Penv,"setStaticBoolean")               //~1803I~
            return 4;                                              //~1803I~//~vac6R~
        }                                            \
	    *Ppfid=fid;                                                //~1803I~
    }                                                              //~1803I~
	(*Penv)->SetStaticBooleanField(Penv,GclassGxeh,fid,Pvalue!=0); //~1803R~
    UTRACEP("setStaticJInt rc=%d,fld=%s,fldid=%p,value=%d\n",rc,Pfname,fid,Pvalue);//~1803I~
    return rc;                                                     //~1803R~
}//setStatcJboolean                                                //~1A12R~
//*************************************************                //~1A12I~
//*return string addr                                              //~vac6I~
//*************************************************                //~vac6I~
//char *getStaticJstring(JNIEnv *Penv,jfieldID *Ppfid,char *Pfname)  //~1A12I~//~vac6R~
char *getStaticJstring(int Popt,JNIEnv *Penv,jfieldID *Ppfid,char *Pfname)//~vac6I~
{                                                                  //~1A12I~
	jfieldID fid;                                                  //~1A12I~
    jstring jstr;                                                  //~1A12I~
    char *pc;                                                      //~1A12I~
//******************                                               //~1A12I~
    UTRACEP("getStaticString fieldname=%s\n",Pfname);              //~1A12I~
	fid=*Ppfid;                                                    //~1A12I~
	if (!fid)                                                      //~1A12I~
    {                                                              //~1A12I~
		fid=(*Penv)->GetStaticFieldID(Penv,GclassGxeh,Pfname,"Ljava/lang/String;"); //boolean//~1A12I~
        if (!fid)                                                  //~1A12I~
        {                                                          //~1A12I~
        	UTRACEP("Field(%s) not found\n",Pfname);               //~1A12I~
			PRINT_EXCEPTION(Penv,"getStaticString")                //~1A12I~
            return 0;                                              //~1A12I~
        }                                            \
	    *Ppfid=fid;                                                //~1A12I~
    }                                                              //~1A12I~
	jstr=(*Penv)->GetStaticObjectField(Penv,GclassGxeh,fid);       //~1A12I~
    if (jstr)                                                      //~1A17I~
    {                                                              //~vac6I~
		pc=jstring2char(Penv,jstr);                                    //~1A12I~//~1A17R~
        if (Popt & GSO_UNREF)                                      //~vac6I~
        	setStaticJstring(Penv,Ppfid,Pfname,(char *)NULL);      //~vac6I~
    }                                                              //~vac6I~
    else                                                           //~1A17I~
    	pc=0;                                                      //~1A17I~
    UTRACEP("getStaticString fld=%s,fldid=%p,value=%s\n",Pfname,fid,pc);//~1A12I~//~1B05R~//+vay0R~
    return pc;                                                     //~1A12I~
}//getStatcJstring                                                 //~1A12I~
//*************************************************                //~1803I~
int setStaticJstring(JNIEnv *Penv,jfieldID *Ppfid,char *Pfname,char *Pvalue)//~1803R~
{                                                                  //~1803I~
	jfieldID fid;                                                  //~1803I~
    jstring jstr;                                                  //~1803I~
    int rc=0;                                                      //~1803I~
//******************                                               //~1803I~
    UTRACEP("setStaticString fieldname=%s\n",Pfname);              //~1803I~
	fid=*Ppfid;                                                    //~1803I~
	if (!fid)                                                      //~1803I~
    {                                                              //~1803I~
		fid=(*Penv)->GetStaticFieldID(Penv,GclassGxeh,Pfname,"Ljava/lang/String;"); //boolean//~1803I~
        if (!fid)                                                  //~1803I~
        {                                                          //~1803I~
        	UTRACEP("Field(%s) not found\n",Pfname);               //~1803I~
			PRINT_EXCEPTION(Penv,"setStaticString")                //~1803I~
            return 4;                                              //~1803I~//~vac6R~
        }                                            \
	    *Ppfid=fid;                                                //~1803I~
    }                                                              //~1803I~
    if (Pvalue)                                                    //~vac6I~
		jstr=utf8z2jstring(Penv,Pvalue);                               //~1803I~//~vac6R~
    else                                                           //~vac6I~
    	jstr=NULL;                                                 //~vac6R~
	(*Penv)->SetStaticObjectField(Penv,GclassGxeh,fid,jstr);       //~1803R~
    UTRACEP("setStaticString rc=%d,fld=%s,fldid=%p,value=%s\n",rc,Pfname,fid,Pvalue);//~1803I~//+vay0R~
    return rc;                                                     //~1803R~
}//setStatcJstring                                                 //~1A12R~
//*************************************************                //~vac6I~
//*return array size                                               //~vac6I~
//*************************************************                //~vac6I~
int getStaticJstringArray(int Popt,JNIEnv *Penv,jfieldID *Ppfid,char *Pfname,char ***Pppc)//~vac6R~
{                                                                  //~vac6I~
	jfieldID fid;                                                  //~vac6I~
    jobject  jstrarray;                                            //~vac6R~
    jstring jstr;                                                  //~vac6I~
    char *pc,**ppc,**ppc0;                                         //~vac6R~
    int sz=0,ii;                                                   //~vac6R~
//******************                                               //~vac6I~
    UTRACEP("getStaticStringArray fieldname=%s\n",Pfname);         //~vac6I~
    *Pppc=0;                                                       //~vac6I~
	fid=*Ppfid;                                                    //~vac6I~
	if (!fid)                                                      //~vac6I~
    {                                                              //~vac6I~
		fid=(*Penv)->GetStaticFieldID(Penv,GclassGxeh,Pfname,"[Ljava/lang/String;"); //boolean//~vac6I~
        if (!fid)                                                  //~vac6I~
        {                                                          //~vac6I~
        	UTRACEP("Field(%s) not found\n",Pfname);               //~vac6I~
			PRINT_EXCEPTION(Penv,"getStaticStringArray")           //~vac6I~
            return 0;                                              //~vac6I~
        }                                                          //~vac6R~
	    *Ppfid=fid;                                                //~vac6I~
    }                                                              //~vac6R~
	jstrarray=(*Penv)->GetStaticObjectField(Penv,GclassGxeh,fid);  //~vac6I~
    if (jstrarray)                                                 //~vac6I~
    {                                                              //~vac6I~
        sz=(*Penv)->GetArrayLength(Penv,(jarray)jstrarray);        //~vac6I~
        if (sz>0)                                                  //~vac6I~
        {                                                          //~vac6I~
            ppc0=ppc=umalloc(sizeof(pc)*sz);                       //~vac6I~
            for (ii=0;ii<sz;ii++)                                  //~vac6I~
            {                                                      //~vac6I~
                jstr=(jstring)(*Penv)->GetObjectArrayElement(Penv,jstrarray,ii);//~vac6I~
                pc=jstring2char(Penv,(jstring)jstr);               //~vac6I~
                *ppc++=pc;                                         //~vac6I~
			    UTRACEP("getStaticStringArray ii=%d,str=%s\n",ii,pc);//~vac6I~
            }                                                      //~vac6I~
            if (Popt & GSO_UNREF)                                  //~vac6I~
                setStaticJstringArray(Penv,Ppfid,Pfname,(jobject)NULL);//~vac6I~
            *Pppc=ppc0;                                            //~vac6I~
        }                                                          //~vac6I~
    }                                                              //~vac6I~
    UTRACEP("getStaticStringArray fld=%s,fldid=%p,sz=%d\n",Pfname,fid,sz);//~vac6I~
    return sz;                                                     //~vac6R~
}//getStatcJstring                                                 //~vac6I~
//*************************************************                //~vac6I~
int setStaticJstringArray(JNIEnv *Penv,jfieldID *Ppfid,char *Pfname,jobject Pvalue)//~vac6R~
{                                                                  //~vac6I~
	jfieldID fid;                                                  //~vac6I~
    int rc=0;                                                      //~vac6I~
//******************                                               //~vac6I~
    UTRACEP("setStaticStringArray fieldname=%s\n",Pfname);         //~vac6I~
	fid=*Ppfid;                                                    //~vac6I~
	if (!fid)                                                      //~vac6I~
    {                                                              //~vac6I~
		fid=(*Penv)->GetStaticFieldID(Penv,GclassGxeh,Pfname,"[Ljava/lang/String;"); //boolean//~vac6I~
        if (!fid)                                                  //~vac6I~
        {                                                          //~vac6I~
        	UTRACEP("Field(%s) not found\n",Pfname);               //~vac6I~
			PRINT_EXCEPTION(Penv,"setStaticStringArray")           //~vac6R~
            return 4;                                              //~vac6R~
        }                                            \
	    *Ppfid=fid;                                                //~vac6I~
    }                                                              //~vac6I~
	(*Penv)->SetStaticObjectField(Penv,GclassGxeh,fid,Pvalue);     //~vac6R~
    UTRACEP("setStaticStringArray rc=%d,fld=%s,fldid=%p,value=%p\n",rc,Pfname,fid,Pvalue);//~vac6R~
    return rc;                                                     //~vac6I~
}//setStatcJstringArray                                            //~vac6R~
//*************************************************                //~1714I~
jbyteArray byte2jbyte(JNIEnv *Penv,char *Pbyte,int Plen)           //~1714R~
{                                                                  //~1622M~
	jbyteArray jba;                                                //~1622M~
    int len;                                                       //~1622M~
//*****************                                                //~1622M~
	if (!(len=Plen))                                               //~1622M~
		len=strlen(Pbyte);                                         //~1622M~
	UTRACEP("byte2jbyte len=%d\n",len);                            //~1715I~
	jba=(*Penv)->NewByteArray(Penv,len);                           //~1714R~
    CHK_ARRAY_EXCEPTION(Penv,"byte2jbyte:NewByteArray")            //~1715I~
    (*Penv)->SetByteArrayRegion(Penv,jba,0,len,(jbyte*)Pbyte);     //~1714R~
    CHK_ARRAY_EXCEPTION(Penv,"byte2jbyte:SetByteArrayRegion")      //~1715I~
//	(*Penv)->NewGlobalRef(Penv,jba);                               //~1715R~
    return jba;                                                    //~1622M~
}                                                                  //~1622M~
//*************************************************                //~1715I~
jintArray int2jint(JNIEnv *Penv,int *Pint,int Plen)                //~1715I~
{                                                                  //~1715I~
	jintArray jia;                                                 //~1715I~
//*****************                                                //~1715I~
	UTRACEP("int2jint len=%d,int=%p\n",Plen,Pint);                 //~1716R~
	jia=(*Penv)->NewIntArray(Penv,Plen);                           //~1715R~
    CHK_ARRAY_EXCEPTION(Penv,"int2jint:NewIntArray")               //~1715I~
    UTRACEP("int2jint newjint=%p\n",jia);                          //~1716I~
    (*Penv)->SetIntArrayRegion(Penv,jia,0,Plen,(jint*)Pint);       //~1715R~
    CHK_ARRAY_EXCEPTION(Penv,"int2jint:SetIntArrayRegion")         //~1715I~
//	(*Penv)->NewGlobalRef(Penv,jia);                               //~1715R~
    return jia;                                                    //~1715R~
}                                                                  //~1715I~
//*************************************************                //~1821I~
jintArray copyint2jint(JNIEnv *Penv,jintArray Pjia,int *Pint,int Plen)//~1821I~
{                                                                  //~1821I~
//*****************                                                //~1821I~
	UTRACEP("copyint2jint len=%d,int=%p\n",Plen,Pint);             //~1821I~
    (*Penv)->SetIntArrayRegion(Penv,Pjia,0,Plen,(jint*)Pint);      //~1821I~
    CHK_ARRAY_EXCEPTION(Penv,"copyint2jint:SetIntArrayRegion")     //~1821I~
    return Pjia;                                                   //~1821I~
}                                                                  //~1821I~
//*************************************************                //~1A14I~
jfloatArray copyfloat2jfloat(JNIEnv *Penv,jfloatArray Pobj,float *Psrc,int Plen)//~1A14R~
{                                                                  //~1A14I~
//*****************                                                //~1A14I~
	UTRACEP("copyfloat2jfloat len=%d,float=%p\n",Plen,Psrc);       //~1A14I~
    (*Penv)->SetFloatArrayRegion(Penv,Pobj,0,Plen,Psrc);           //~1A14I~
    CHK_ARRAY_EXCEPTION(Penv,"copyfloat2jfloat:SetFloatArrayRegion")//~1A14I~
    return Pobj;                                                   //~1A14I~
}                                                                  //~1A14I~
//*************************************************                //~1718I~
jfloatArray newjfloat(JNIEnv *Penv,int Plen)                       //~1718I~
{                                                                  //~1718I~
	jfloatArray jfa;                                               //~1718I~
//*****************                                                //~1718I~
	UTRACEP("float2jfloat len=%d\n",Plen);                         //~1718R~
	jfa=(*Penv)->NewFloatArray(Penv,Plen);                         //~1718I~
    CHK_ARRAY_EXCEPTION(Penv,"floatjfloat:NewFloatArray")          //~1718I~
    UTRACEP("float2jfloat newjfloat=%p\n",jfa);                    //~1718I~
    return jfa;                                                    //~1718I~
}                                                                  //~1718I~
//*************************************************                //~1719I~
jintArray float2jfloat(JNIEnv *Penv,float *Pfloat,int Plen)        //~1719I~
{                                                                  //~1719I~
	jfloatArray jfa;                                               //~1719I~
//*****************                                                //~1719I~
	UTRACEP("float2jfloat len=%d,float=%p\n",Plen,Pfloat);         //~1719I~
	jfa=(*Penv)->NewFloatArray(Penv,Plen);                         //~1719I~
    CHK_ARRAY_EXCEPTION(Penv,"float2jfloat:NewFloatArray")         //~1719I~
    UTRACEP("float2jfloat newjfloat=%p\n",jfa);                    //~1719I~
    (*Penv)->SetFloatArrayRegion(Penv,jfa,0,Plen,(jfloat*)Pfloat); //~1719I~
    CHK_ARRAY_EXCEPTION(Penv,"float2jfloat:SetFloatArrayRegion")   //~1719I~
    return jfa;                                                    //~1719I~
}                                                                  //~1719I~
//*************************************************                //~1703I~
void jbyte2byte(JNIEnv *Penv,jbyteArray Pjba,int Ppos,int Plen,char *Pout)//~1714R~
{                                                                  //~1703I~
//*****************                                                //~1703I~
	UTRACEP("jbyte2byte len=%d\n",Plen);                           //~1715I~
    (*Penv)->GetByteArrayRegion(Penv,Pjba,Ppos,Plen,(jbyte*)Pout); //~1714R~
    CHK_ARRAY_EXCEPTION(Penv,"jbyte2byte:GetByteArrayRegion")      //~1715I~
}                                                                  //~1703I~
//*************************************************                //~1715I~
void jint2int(JNIEnv *Penv,jintArray Pjia,int Ppos,int Plen,int *Pout)//~1715I~
{                                                                  //~1715I~
//*****************                                                //~1715I~
	UTRACEP("jint2int pos=%d,len=%d,jint=%p,out=%p\n",Ppos,Plen,Pjia,Pout);//~1716R~
    (*Penv)->GetIntArrayRegion(Penv,Pjia,Ppos,Plen,(jint*)Pout);   //~1715I~
    CHK_ARRAY_EXCEPTION(Penv,"jint2int:GetIntArrayRegion")         //~1715I~
}                                                                  //~1715I~
//*************************************************                //~vac6I~
jlong *getjlongarray(JNIEnv *Penv,jlongArray Pjia,int Ppos,int Plen)//~vac6R~
{                                                                  //~vac6I~
  	jlong *pjlong;                                                 //~vac6R~
//*****************                                                //~vac6I~
	UTRACEP("jlong2long pos=%d,len=%d,jint=%p\n",Ppos,Plen,Pjia);  //~vac6R~
    pjlong=(jlong*)umalloc(sizeof(jlong)*Plen);                    //~vac6R~
	(*Penv)->GetLongArrayRegion(Penv,Pjia,Ppos,Plen,pjlong);       //~vac6R~
    CHK_ARRAY_EXCEPTION(Penv,"jlong2longarray:GetLongArrayRegion") //~vac6R~
    return pjlong;                                                 //~vac6R~
}                                                                  //~vac6I~
//*************************************************                //~1718I~
void jfloat2float(JNIEnv *Penv,jintArray Pjfa,int Ppos,int Plen,float *Pout)//~1718I~
{                                                                  //~1718I~
//*****************                                                //~1718I~
	UTRACEP("jfloat2float pos=%d,len=%d,jfloat=%p,out=%p\n",Ppos,Plen,Pjfa,Pout);//~1718I~
    (*Penv)->GetFloatArrayRegion(Penv,Pjfa,Ppos,Plen,Pout);        //~1718I~
    CHK_ARRAY_EXCEPTION(Penv,"jfloat2float:GetFloatArrayRegion")   //~1718I~
}                                                                  //~1718I~
//*************************************************                //~1703I~
jbyteArray newjbyte(JNIEnv *Penv,int Plen)                         //~1714R~
{                                                                  //~1703I~
	jbyteArray jba;                                                //~1703I~
//*****************                                                //~1703I~
	UTRACEP("newjbyte len=%d\n",Plen);                             //~1715I~
	jba=(*Penv)->NewByteArray(Penv,Plen);                          //~1714R~
    CHK_ARRAY_EXCEPTION(Penv,"newjbyte:NewByteArray")              //~1715I~
//	(*Penv)->NewGlobalRef(Penv,jba);                               //~1715R~
    return jba;                                                    //~1703I~
}                                                                  //~1703I~
//*************************************************                //~1715I~
jstring utf8z2jstring(JNIEnv *Penv,char *Putf8)                    //~1715R~
{                                                                  //~1715I~
	jstring js;                                                    //~1715I~
//*****************                                                //~1715I~
	UTRACEP("utf8z2jstring utf8=%s\n",Putf8);                      //~1715I~
    utfstrvalidate(0,Putf8,0/*len*/,'.'/*errch*/,0/*okctr*/,0/*repctr*/);//~vac6I~
	js=(*Penv)->NewStringUTF(Penv,Putf8);                          //~1715R~
//	(*Penv)->NewGlobalRef(Penv,js);                                //~1715R~
    return js;                                                     //~1715I~
}                                                                  //~1715I~
//*************************************************                //~1715I~
jstring utf82jstring(JNIEnv *Penv,char *Putf8,int Plen)            //~1715I~
{                                                                  //~1715I~
	char *putf8;                                                   //~1715I~
	jstring js;                                                    //~1715I~
//*****************                                                //~1715I~
	putf8=umalloc(Plen+1);                                         //~1715I~
    UmemcpyZ(putf8,Putf8,Plen);                                    //~1715I~
	UTRACEP("utf82jstring utf8=%s\n",putf8);                       //~1715I~
    utfstrvalidate(0,putf8,Plen,'.'/*errch*/,0/*okctr*/,0/*repctr*/);//~vac6I~
	js=(*Penv)->NewStringUTF(Penv,putf8);                          //~1715R~
//	(*Penv)->NewGlobalRef(Penv,js);                                //~1715R~
    ufree(putf8);                                                  //~1715I~
    return js;                                                     //~1715I~
}                                                                  //~1715I~
//*************************************************                //~1715I~
jintArray newjint(JNIEnv *Penv,int Plen)                           //~1715I~
{                                                                  //~1715I~
	jbyteArray jia;                                                //~1715I~
//*****************                                                //~1715I~
	UTRACEP("newjint len=%d\n",Plen);                              //~1715I~
	jia=(*Penv)->NewIntArray(Penv,Plen);                           //~1715I~
    CHK_ARRAY_EXCEPTION(Penv,"newjint:NewIntArray")                //~1715I~
//	(*Penv)->NewGlobalRef(Penv,jia);                               //~1715R~
    return jia;                                                    //~1715I~
}                                                                  //~1715I~
//*************************************************                //~1623I~
void unrefGlobal(JNIEnv *Penv,jobject Pobject)                     //~1715R~
{                                                                  //~1623I~
	UTRACEP("unrefGlobal obj=%p\n",Pobject);                       //~1715I~
	(*Penv)->DeleteGlobalRef(Penv,Pobject);                        //~1714R~
}                                                                  //~1623I~
//*************************************************                //~1715I~
void unrefLocal(JNIEnv *Penv,jobject Pobject)                      //~1715I~
{                                                                  //~1715I~
	UTRACEP("unrefLocal obj=%p\n",Pobject);                        //~1715I~
//  LOGPD("unrefLocal env=%p,obj=%p\n",Penv,Pobject);              //~vay0R~
	(*Penv)->DeleteLocalRef(Penv,Pobject);                         //~1715I~
}                                                                  //~1715I~
//*************************************************                //~1623I~
char *jstring2char(JNIEnv *Penv,jstring Pjstr)                     //~1714R~
{                                                                  //~1623I~
	const char *utf8str;                                           //~1623I~
	char *str;                                                     //~1623I~
    int len;                                                       //~1623I~
//*****************                                                //~1623I~
	utf8str=(*Penv)->GetStringUTFChars(Penv,Pjstr,0);              //~1714R~
    len=strlen(utf8str);                                           //~1623I~
    str=(char*)umalloc(len+1);                                     //~1623I~
    UmemcpyZ(str,utf8str,len);                                     //~1623I~
    (*Penv)->ReleaseStringUTFChars(Penv,Pjstr,utf8str);            //~1714R~
	return str;                                                    //~1623I~
}                                                                  //~1623I~
//*************************************************                //~1803I~
int jstring2buff(JNIEnv *Penv,jstring Pjstr,char *Pout,int Pbuffsz)//~1803I~
{                                                                  //~1803I~
	const char *utf8str;                                           //~1803I~
    int len;                                                       //~1803I~
//*****************                                                //~1803I~
	utf8str=(*Penv)->GetStringUTFChars(Penv,Pjstr,0);              //~1803I~
    len=strlen(utf8str);                                           //~1803I~
    len=min(len,Pbuffsz-1);                                        //~1803I~
    UmemcpyZ(Pout,utf8str,len);                                    //~1803I~
    (*Penv)->ReleaseStringUTFChars(Penv,Pjstr,utf8str);            //~1803I~
	return len;                                                    //~1803I~
}                                                                  //~1803I~
//***************************************************************  //~1716I~//~1718M~
void *getvoidtb(int Ptbid,int Psize)                               //~1718M~
{                                                                  //~1718M~
static void *Svoidtb[GETTB_MAXTB];                                 //~1718M~
static int Svoidtbsz[GETTB_MAXTB];                                 //~1718M~
	int sz;                                                        //~1718M~
    void *ptb;                                                     //~1718M~
//****************************                                     //~1718M~
	sz=Svoidtbsz[Ptbid];                                           //~1718M~
	ptb=Svoidtb[Ptbid];                                            //~1718M~
	if (Psize>sz)                                                  //~1718M~
    {                                                              //~1718M~
    	if (ptb)                                                   //~1718M~
        	ufree(ptb);                                            //~1718M~
    	Svoidtb[Ptbid]=ptb=umalloc(Psize);                         //~1718M~
    	Svoidtbsz[Ptbid]=sz;                                       //~1718M~
    }                                                              //~1718M~
    return ptb;                                                    //~1718M~
}//getvoidtb;                                                      //~1718M~
//***************************************************************  //~1A14I~
jfloatArray getgbljfloat(JNIEnv *Penv,int Psize)                   //~1A14I~
{                                                                  //~1A14I~
static jobject Sobj;                                               //~1A14I~
static int Ssize;                                                  //~1A14I~
//****************************                                     //~1A14I~
	if (Psize>Ssize)                                               //~1A14I~
    {                                                              //~1A14I~
    	if (Sobj)                                                  //~1A14I~
			unrefGlobal(Penv,Sobj);                                //~1A14R~
    	Sobj=newjfloat(Penv,Psize);                                //~1A14I~
        Sobj=                                                      //~vab3I~
		(*Penv)->NewGlobalRef(Penv,Sobj);                          //~1A14I~
    	Ssize=Psize;                                               //~1A14I~
    }                                                              //~1A14I~
    return (jfloatArray)Sobj;                                      //~1A14I~
}                                                                  //~1A14I~
//***************************************************************  //~1A14I~
jintArray getgbljint(JNIEnv *Penv,int Psize)                       //~1A14I~
{                                                                  //~1A14I~
static jobject Sobj;                                               //~1A14I~
static int Ssize;                                                  //~1A14I~
//static FILE *Sfh;                                                  //~2120I~//~vab3R~
//****************************                                     //~1A14I~
//    if (Sfh==0)                                                    //~2120I~//~vab3R~
//    {                                                              //~2120I~//~vab3R~
//        Sfh=fopen("/data/data/com.xe.Axe/files/jni.trc","w");      //~2120R~//~vab3R~
//    }                                                              //~2120I~//~vab3R~
//    fprintf(Sfh,"Psize=%d,Ssize=%d,Sobj=%p,env=%p\n",Psize,Ssize,Sobj,*Penv);//~2120I~//~vab3R~
	if (Psize>Ssize)                                               //~1A14I~
    {                                                              //~1A14I~
    	if (Sobj)                                                  //~1A14I~
        {                                                          //~2120I~
//            fprintf(Sfh,"unrefGbl\n");                             //~2120I~//~vab3R~
			unrefGlobal(Penv,Sobj);                                //~1A14R~
        }                                                          //~2120I~
    	Sobj=newjint(Penv,Psize);                                  //~1A14I~
        Sobj=                                                      //~vab3I~
		(*Penv)->NewGlobalRef(Penv,Sobj);                          //~1A14I~
    	Ssize=Psize;                                               //~1A14I~
//        fprintf(Sfh,"setGlobal Sobj=%p,Sobj2=%p\n",Sobj,Sobj2);    //~2120I~//~vab3R~
    }                                                              //~1A14I~
    return (jintArray)Sobj;                                        //~1A14I~
}                                                                  //~1A14I~
//***************************************************************  //~1A14I~
jintArray gblint2jint(JNIEnv *Penv,int *Psrc,int Psize)            //~1A14I~
{                                                                  //~1A14I~
	jintArray jobj;                                                //~1A14I~
//****************************                                     //~1A14I~
	jobj=getgbljint(Penv,Psize);                                   //~1A14I~
    copyint2jint(Penv,jobj,Psrc,Psize);                            //~1A14I~
    return jobj;                                                   //~1A14I~
}                                                                  //~1A14I~
//***************************************************************  //~1A14I~
jfloatArray gblfloat2jfloat(JNIEnv *Penv,float *Psrc,int Psize)    //~1A14R~
{                                                                  //~1A14I~
	jfloatArray jobj;                                              //~1A14I~
//****************************                                     //~1A14I~
	jobj=getgbljfloat(Penv,Psize);                                 //~1A14I~
    copyfloat2jfloat(Penv,jobj,Psrc,Psize);                        //~1A14I~
    return jobj;                                                   //~1A14I~
}                                                                  //~1A14I~
