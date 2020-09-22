//*CID://+v6dgR~:                                   update#=   72; //~v6dgR~
//**************************************************************** //~1610I~
//jnic2j2.h                                                         //~1621R~//~vac6R~
//**************************************************************** //~1610I~
//v6dg:120220 (Axe)ftp downloaded file attr is 075(sdcard is read only and FAT); change to private dir//~v6dgI~
//vac6:120217 (Axe)samba share support using jcifs 3.17            //~vac6I~
//**************************************************************** //~vac6I~
#ifdef ARM                                                         //~1621I~
#define SMBOPT_AFTERSTAT   0x02  //do fstat after operation such as mkdir//~v6dgI~
                                                                   //~v6dgI~
#define SMBREQRC_ASYNC  -2      //see AxeSMBReq.java               //~vac6R~
#define SMBREQRC_BUSY   16      //EBUSY:see AxeSMBReq.java         //~vac6I~
#define SMBREQRC_IE     -11     //internal err                     //~vac6I~
typedef struct _USMBJCMDPARM {                                     //~vac6I~
                            PUFTPHOST USJCPpuftph;                 //~vac6I~
                            char      USJCPlcdpath[_MAX_PATH];          //~v5ckR~//~vac6R~
                            char      USJCPcdpath[_MAX_PATH];           //~v6daI~//~vac6R~
                            int       USJCPcmdtotlen;              //~vac6I~
                            UQUEH     USJCPcmdq;                   //~vac6R~
                            } USMBJCMDPARM,*PUSMBJCMDPARM;         //~vac6R~
#define USMBJCMDPARMSZ (sizeof(USMBJCMDPARM))                      //~vac6I~
//***************************                                      //~vac6I~
#define JNISMBO_FPWOHID    0x01    //fullpath without hostid       //~vac6I~
//**************************************************************** //~vac6I~
unsigned int jnismb_ufstat(int Popt,PUFTPHOST Ppuftph,char *Pfpath,FILEFINDBUF3 *Ppffb3);//~v6daR~//~vac6I~
//**************************************************************** //~vac6I~
int jnismb_udirlist(int Popt,PUFTPHOST Ppuftph,int *Ppentno,char *Pfpath,ULONG Pattr,PUDIRLIST *Pppudl);//~v6daR~//~vac6I~
//**************************************************************** //~vac6I~
int jnismb_nodetest(int Popt,PUFTPHOST Ppuftph);                   //~vac6R~
#define SMBOPT_ASYNC       0x01    //asynchronous process          //~vac6R~
//**************************************************************** //~vac6I~
int jnismb_uchdir(int Popt,PUFTPHOST Ppuftph,char *Pfpath);        //~vac6I~
//**************************************************************** //~vac6I~
int jnismb_putstmt(int Popt,PUSMBJCMDPARM Ppusmbjcp,char *Pcmd,char *Pfnm1,char *Pfnm2);//~vac6R~
//**************************************************************** //~vac6I~
int jnismb_getput(int Popt,PUSMBJCMDPARM Ppusmbjcp);               //~vac6R~
//**************************************************************** //~v6dgI~
int jnismb_delete(int Popt,PUFTPHOST Ppfh,char *Pfpath);           //~v6dgR~
//**************************************************************** //~v6dgI~
char *jniu_getPrivatePath(int Popt);                               //~v6dgR~
//**************************************************************** //~v6dgI~
int jnismb_ufindfirst(int Popt,PUFTPHOST Ppuftph,char *Pfpath,UINT Pattr,FILEFINDBUF3 *Ppffb3);//~v6dgR~
//**************************************************************** //~v6dgI~
int jnismb_putstmtNoCD(int Popt,PUFTPHOST Ppuftph,char *Pfnm,UQUEH *Ppqh);//~v6dgR~
//**************************************************************** //~v6dgI~
int jnismb_multidel(int Popt,PUFTPHOST Ppuftph,UQUEH *Ppqh);       //~v6dgR~
//**************************************************************** //~v6dgI~
int jnismb_rename(int Popt,PUFTPHOST Ppuftph,char *Pold,char *Pnew);//~v6dgI~
//**************************************************************** //~v6dgI~
int jnismb_putstmtNoCD2(int Popt,PUFTPHOST Ppuftph,char *Pfnm1,char *Pfnm2,UQUEH *Ppqh);//~v6dgI~
//**************************************************************** //~v6dgI~
int jnismb_multirename(int Popt,PUFTPHOST Ppuftph,UQUEH *Ppqh);    //~v6dgI~
//**************************************************************** //~v6dgI~
int jnismb_mkdir(int Popt,PUFTPHOST Ppuftph,char *Pfnm,ULONG Pattr,ULONG *Ppattr);//~v6dgR~
//**************************************************************** //~v6dgI~
int jnismb_rmdir(int Popt,PUFTPHOST Ppuftph,char *Pfnm);           //~v6dgI~
//**************************************************************** //~v6dgI~
int jnismb_setattr(int Popt,PUFTPHOST Ppuftph,char *Pfnm,int Pattr,ULONG *Ppattr);//+v6dgR~
#endif //ARM                                                       //~1621I~
