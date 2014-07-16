package com.library;

import android.app.Application;
import android.content.Context;

public abstract class UtilityApplication extends Application
{
	private static final String TAG = "UtilityApplication";

	/**
	 * In initialize one must set up global values by calling setUpGlobals
	 */
	public abstract void Initialize(); 
	
	public void setUpGlobals(Context context, boolean isTest, boolean isConnectionTest, boolean isGZIP, String applicationFolder)
	{
		AndroidLog.i(TAG, "Intializing UTILITYAPPLICATION");
		Globals.context = context; 
		Globals.isTest = isTest; 
		Globals.isConnectionTest = isConnectionTest; 
		Globals.isGZIP = isGZIP;
		Globals.applicationFolder = applicationFolder; 
	}
}
