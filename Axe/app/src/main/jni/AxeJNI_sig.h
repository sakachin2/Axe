/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_xe_Axe_AxeJNI */

#ifndef _Included_com_xe_Axe_AxeJNI
#define _Included_com_xe_Axe_AxeJNI
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_xe_Axe_AxeJNI
 * Method:    jniInit
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V
 */
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniInit
  (JNIEnv *, jclass, jstring, jstring, jstring, jint);             //~1A17R~

/*
 * Class:     com_xe_Axe_AxeJNI
 * Method:    jniKbdMsg
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_com_xe_Axe_AxeJNI_jniKbdMsg
  (JNIEnv *, jclass, jint, jint, jint);

/*
 * Class:     com_xe_Axe_AxeJNI
 * Method:    jniMouseMsg
 * Signature: (IIIII)I                                             //~1927R~
 */
JNIEXPORT jint JNICALL Java_com_xe_Axe_AxeJNI_jniMouseMsg
  (JNIEnv *, jclass,jint, jint, jint, jint, jint);                 //~1927R~

/*
 * Class:     com_xe_Axe_AxeJNI
 * Method:    jniSetScreenSize
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniSetScreenSize
  (JNIEnv *, jclass, jint, jint);

/*
 * Class:     com_xe_Axe_AxeJNI
 * Method:    jniTerminateXe
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniTerminateXe
  (JNIEnv *, jclass);

/*
 * Class:     com_xe_Axe_AxeJNI
 * Method:    jniFullDraw
 * Signature: (I)V                                                 //~1927R~
 */
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniFullDraw
  (JNIEnv *, jclass, jint);                                        //~1927R~

/*
 * Class:     com_xe_Axe_AxeJNI
 * Method:    jniOptionChangedOther
 * Signature: (IIZZZ)V
 */
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOptionChangedOther
  (JNIEnv *, jclass, jint, jint, jboolean, jboolean, jboolean);

/*
 * Class:     com_xe_Axe_AxeJNI
 * Method:    jniOptionChangedColor
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOptionChangedColor
  (JNIEnv *, jclass, jint, jint);

/*
 * Class:     com_xe_Axe_AxeJNI
 * Method:    jniOptionChangedFont
 * Signature: (Ljava/lang/String;IIIIIZ)V
 */
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOptionChangedFont
  (JNIEnv *, jclass, jstring, jint, jint, jint, jint, jint, jboolean,jint,jint);//~1A02R~

/*
 * Class:     com_xe_Axe_AxeJNI
 * Method:    jniOnTimer
 * Signature: (IJ)V
 */
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOnTimer
  (JNIEnv *, jclass, jint, jlong);

/*
 * Class:     com_xe_Axe_AxeJNI
 * Method:    jniGetSampleColor
 * Signature: ([I)V
 */
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniGetSampleColor
  (JNIEnv *, jclass, jintArray);

/*
 * Class:     com_xe_Axe_AxeJNI
 * Method:    jniSetTraceOpt
 * Signature: (I)V                                                 //~1926R~
 */
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniSetTraceOpt
  (JNIEnv *, jclass, jint);                                        //~1926R~

JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOnContextMenuClosed//~1930I~
  (JNIEnv *, jclass);                                              //~1930I~
                                                                   //~1930I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOnEditCut         //~1A02R~
  (JNIEnv *, jclass);                                              //~1A02I~
                                                                   //~1A02I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOnEditCopy        //~1A02R~
  (JNIEnv *, jclass);                                              //~1A02I~
                                                                   //~1A02I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOnEditClear       //~1A02R~
  (JNIEnv *, jclass);                                              //~1A02I~
                                                                   //~1A02I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOnEditPasteV      //~1A02R~
  (JNIEnv *, jclass);                                              //~1A02I~
                                                                   //~1A02I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOnEditPasteIns    //~1A02R~
  (JNIEnv *, jclass);                                              //~1A02I~
                                                                   //~1A02I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOnEditPasteRep    //~1A02R~
  (JNIEnv *, jclass);                                              //~1A02I~
                                                                   //~1A02I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOnFileEnd         //+3601I~
  (JNIEnv *, jclass);                                              //+3601I~
                                                                   //+3601I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOnFileCancel      //+3601I~
  (JNIEnv *, jclass);                                              //+3601I~
                                                                   //+3601I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniOnFileOpenWith    //+3601I~
  (JNIEnv *, jclass);                                              //+3601I~
                                                                   //+3601I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniSwipeHorizontal   //~1A02I~
  (JNIEnv *, jclass, jint,jint,jint);                                   //~1A02I~//~1A03R~
                                                                   //~1A02I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniSwipeVertical     //~1A02I~
  (JNIEnv *, jclass, jint,jint,jint);                                   //~1A02I~//~1A03R~
                                                                   //~1A07I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniCmd               //~1A07I~
  (JNIEnv *, jclass, jstring, jstring);                            //~1A07I~
                                                                   //~1A02I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniUserMsg           //~1A18I~
  (JNIEnv *, jclass, jint, jlong);                                 //~1A18I~
                                                                   //~1A18I~
JNIEXPORT void JNICALL Java_com_xe_Axe_AxeJNI_jniDndRepCopy        //~1A22I~
  (JNIEnv *, jclass, jstring);                                     //~1A22I~
                                                                   //~1A22I~
#ifdef __cplusplus
}
#endif
#endif