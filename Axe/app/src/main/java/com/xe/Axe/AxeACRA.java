//*CID://+vay0R~:                             update#=    9;       //~vay0R~
//*****************************************************************************************//~vabeR~//~vaipI~
//vay0:140710 (Axe)jni exception handling                          //~vay0I~
//vaip:130603 Axe:add ACRA function                                //~vaipI~
//*****************************************************************************************//~vabeR~//~vaipI~
package com.xe.Axe;                                                //~vaipI~
                                                                   //~vaipI~
import org.acra.*;                                                 //+vaipI~                                       //~vaipI~
import static org.acra.ReportField.*;
import org.acra.annotation.ReportsCrashes;
import android.app.Application;
                                                                   //~vaipI~
@ReportsCrashes(formKey="",                                        //~vaipR~
                mailTo="sak21997jp@gmail.com",                     //~vaipI~
                customReportContent={                              //~vaipI~
//CrashReports-Template.csv                                        //~vaipI~
//REPORT_ID, APP_VERSION_CODE, APP_VERSION_NAME, PACKAGE_NAME, FILE_PATH, PHONE_MODEL, BRAND, PRODUCT, ANDROID_VERSION, BUILD, TOTAL_MEM_SIZE, AVAILABLE_MEM_SIZE, CUSTOM_DATA, IS_SILENT, STACK_TRACE, INITIAL_CONFIGURATION, CRASH_CONFIGURATION, DISPLAY, USER_COMMENT, USER_EMAIL, USER_APP_START_DATE, USER_CRASH_DATE, DUMPSYS_MEMINFO, LOGCAT, INSTALLATION_ID, DEVICE_FEATURES, ENVIRONMENT, SHARED_PREFERENCES, SETTINGS_SYSTEM, SETTINGS_SECURE, SETTINGS_GLOBAL//~vaipR~
//REPORT_ID, APP_VERSION_CODE, APP_VERSION_NAME, PACKAGE_NAME, FILE_PATH, PHONE_MODEL, BRAND, PRODUCT, ANDROID_VERSION, BUILD, TOTAL_MEM_SIZE, AVAILABLE_MEM_SIZE,              IS_SILENT, STACK_TRACE, INITIAL_CONFIGURATION, CRASH_CONFIGURATION, DISPLAY,                           USER_APP_START_DATE, USER_CRASH_DATE,                  LOGCAT,                                   ENVIRONMENT, SHARED_PREFERENCES, SETTINGS_SYSTEM, SETTINGS_SECURE, SETTINGS_GLOBAL//~vay0R~
  REPORT_ID, APP_VERSION_CODE, APP_VERSION_NAME, PACKAGE_NAME, FILE_PATH, PHONE_MODEL, BRAND, PRODUCT, ANDROID_VERSION, BUILD, TOTAL_MEM_SIZE, AVAILABLE_MEM_SIZE, CUSTOM_DATA, IS_SILENT, STACK_TRACE, INITIAL_CONFIGURATION, CRASH_CONFIGURATION, DISPLAY,                           USER_APP_START_DATE, USER_CRASH_DATE,                  LOGCAT,                                   ENVIRONMENT, SHARED_PREFERENCES, SETTINGS_SYSTEM, SETTINGS_SECURE, SETTINGS_GLOBAL//~vay0I~
                },                                                 //~vaipI~
                forceCloseDialogAfterToast=false,                  //+vay0I~
                mode=ReportingInteractionMode.TOAST,               //~vaipI~
                resToastText=R.string.Err_ACRA                      //~vaipI~
               )                                                   //~vaipI~
public class AxeACRA extends Application                           //~vaipI~
{                                                                  //~vaipI~
    @Override                                                      //~1526I~//~vaipI~
    public void onCreate()                            //~1526I~    //~vaipI~
	{                                                              //~1526I~//~vaipI~
    	super.onCreate();                                    //~1526I~//~vaipI~
    	try                                                        //~1528I~//~vaipI~
        {                                                          //~1528I~//~vaipI~
	    	ACRA.init(this);                                       //~vaipI~
        }                                                          //~1528I~//~vaipI~
        catch(Exception e)                                         //~1528I~//~vaipI~
        {                                                          //~1528I~//~vaipI~
        }                                                          //~1528I~//~vaipI~
    }                                                              //~1526I~//~vaipI~
}//class                                                           //~1621R~//~vaipI~