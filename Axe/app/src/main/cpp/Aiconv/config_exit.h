//*CID://+@@@@R~:                             update#=   13;       //~@@@@I~
//**********************************************************************//~@@@@I~
#ifdef ARM_AXE                                        //           #vc58//~@@@@R~
    #ifndef ARM_AXE_DEFINED                                        //+@@@@I~
		#define exit(exitcode) exitPthread(exitcode)      //           #vc58//+@@@@R~
    	void exitPthread(int PexitCode);                  //           #vc58//+@@@@R~
	    #define ARM_AXE_DEFINED                                    //+@@@@I~
    #endif                                                         //+@@@@I~
#endif                                                //           #vc58//~@@@@R~
