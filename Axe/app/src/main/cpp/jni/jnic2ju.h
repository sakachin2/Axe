//*CID://+vc5cR~:                                   update#=  110; //~vc5cR~
//**************************************************************** //~1610I~
//jnic2j2.h                                                         //~1621R~//~vac6R~
//**************************************************************** //~1610I~
//vc5c 2023/07/04 display directory set to access by ACTION_OPEN_DOCUMENT_TREE//~vc5cI~
//v77w:230519 uri-->path is avalable from api30(android11:R) and readdir using fd gotten by openDescriptor returns null//~v77wI~
//v77m:230429 ARM:try stat(fpath) by fstat(fd) for ufstat          //~v77mI~
//v77h:230424 ARM;copy                                             //~v77hI~
//v77g:230424 ARM;rename                                           //~v77gI~
//v77f:230424 ARM;rmdir                                            //~v77fI~
//v77e:230424 ARM;try fd for opendir/readdir                       //~v77eI~
//v77d:230423 ARM;delete                                           //~v77dI~
//v77c:230422 ARM;mkdir                                            //~v77cI~
//vby8:230415 (ARM)open document file                              //~vby8I~
//vby4:230402 (ARM)shared resource support by //shareName defined by SP(ShortPath) cmd.//~vby4I~
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
int jnismb_setattr(int Popt,PUFTPHOST Ppuftph,char *Pfnm,int Pattr,ULONG *Ppattr);//~v6dgR~
//**************************************************************** //~vby4I~
int c2j_startPicker(int Popt,char *Palias);                        //~vby4R~
//**************************************************************** //~vby4I~
int c2j_getDirFD(int Popt,char *PstrUri);                          //~vby4I~
//**************************************************************** //~vby4I~
int c2j_udirlistDoc(int Popt,char *PnameDir,char *PstrUri,unsigned Pattr,UDIRLIST **Pppudirlist,int *Pperr);//~vby4R~
//**************************************************************** //~vby4I~
int c2j_ufstatDoc(int Popt,char *PnameDir,char *PstrUri,int Ppathlen,UDIRLIST **Pppudirlist);//~vby4R~
#define C2JUFSO_UFSTAT     0x01                                    //~vby4I~
#define C2JUFSO_WILDCARD   0x02                                    //~vby4I~
//**************************************************************** //~vby8I~
int c2j_openDoc(int Popt,char *Pfpath,char *PstrUri,char *PtempDir,char **Ppbuff,int *Pplen,int *PpoptRC);//~vby8R~
int c2j_fgetsDoc(int Popt,char *Pfpath,char *Pbuff,int Plen);      //~vby8R~
int c2j_freadDoc(int Popt,char *Pfpath,char *Pbuff,int Plen,int *PpreadLen);//~vby8R~
int c2j_fwriteDoc(int Popt,char *Pfpath,char *Pbuff,int Plen,int *Ppwritelen);//~vby8R~
int c2j_fcloseDoc(int Popt,char *Pfpath);                          //~vby8R~
int c2j_mkdirDoc(int Popt,char* Pfpath,char *PstrUri);             //~v77cR~
int c2j_rmdirDoc(int Popt,char* Pfpath,char *PstrUri);             //~v77fI~
int c2j_unlinkDoc(int Popt,char* Pfpath,char *PstrUri);            //~v77dR~
int c2j_opendirDoc(int Popt,char* Pfpath,char *PstrUri,int *Ppfd); //~v77eI~
int c2j_renameDoc(int Popt,char *Pold,char *PstrUri,char *Pnew);   //~v77gR~
int c2j_copyDoc(int Popt,char *Psrc,char *PstrUriSrc,char *Ptgt,char *PstrUriTgt,int *PerrSrc,int *PerrTgt);//~v77hI~
int c2j_statDoc(int Popt,char* Pfpath,char *PstrUri,int *Ppfd);    //~v77mI~
int c2j_getDocPath(int Popt,char* Pfpath,char *PstrUri,char *Ppath);//~v77wR~
int c2j_notifyAllSP(char* Pallsp);                                 //+vc5cR~
#endif //ARM                                                       //~1621I~
