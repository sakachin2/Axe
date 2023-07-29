//*CID://+vc58R~:                             update#=   14;       //~@@@@I~//+vc58R~
//**********************************************************************//~@@@@I~
#ifdef ARM_AXE                                        //           #vc58//~@@@@R~
    #ifndef ARM_AXE_DEFINED                                        //~@@@@I~
		#define exit(exitcode) exitPthread(exitcode)      //           #vc58//~@@@@R~
		extern  "C"                                                        //~2818I~//+vc58I~
		{                                                                  //~2818I~//+vc58I~
    		void exitPthread(int PexitCode);                  //           #vc58//~@@@@R~//+vc58R~
        }                                                          //+vc58I~
	    #define ARM_AXE_DEFINED                                    //~@@@@I~
    #endif                                                         //~@@@@I~
#endif                                                //           #vc58//~@@@@R~
