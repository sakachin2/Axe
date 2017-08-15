//*CID://+vay0R~:                                   update#=   12  //~vay0I~
//**************************************************************** //~vay0I~
//jnia.h                                                           //~vay0I~
//**************************************************************** //~vay0I~
//vay0:140710 (Axe)jni exception handling                          //~vay0I~
//**************************************************************** //~vay0I~
#define MAX_FUNCID 64                                              //~vay0I~
#define NDK_TRY_CATCH(env,Pfuncid,Pstmts)  \
  	if (jnisigh_setup(env,Pfuncid)==0)  \
    {                             \
    	Pstmts                    \
    }                             \
    else                          \
    {                             \
    	jnisigh_catch(env);     \
    }                             \
	jnisigh_cleanup()                                              //~vay0I~
//**************************************************************** //~vay0I~
int jnisigh_setup(JNIEnv *Penv,char *Pfuncid);                     //~vay0R~
void jnisigh_cleanup();                                            //~vay0I~
void jnisigh_catch(JNIEnv *env);                                   //~vay0R~
void  jnisigh_handler_save(JNIEnv *Penv,char *Pc2jmethod,char *Pj2cfuncid);//~vay0I~
void  jnisigh_handler_restore(JNIEnv *Penv,char *Pj2cfuncid);      //~vay0R~
void  jnisigh_uerrexit(char *Pmsg);                                //+vay0R~
