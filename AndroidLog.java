package com.library;

import android.util.Log;

public class AndroidLog
{
	private static final String MSG_PREFIX = "[AndroidLog] ";
	private static final String TAG_PREFIX = "";

	public static void printStackTrace(String Tag, Exception e)
	{
		if(Globals.isTest)
		{
			if(e != null && e.getStackTrace() != null)
			{
				StringBuilder sb = new StringBuilder();
				StackTraceElement[] stackTraceElements = e.getStackTrace();

				if(e.toString() != null)
				{
					AndroidLog.e(Tag, new StringBuilder().append("------ ").append(e.toString())
							.append(" -----").toString());
				}
				
				if(e.getCause() != null)
				{
					Throwable cause = e.getCause();
					String causeErrorMessage = cause.getMessage();
					AndroidLog.e(Tag, new StringBuilder().append("------ ").append(
							causeErrorMessage).append(" -----").toString());
				}
				
				if(e.getMessage() != null)
				{
					AndroidLog.e(Tag, new StringBuilder().append("------ ").append(e.getMessage())
							.append(" -----").toString());
				}
				
				for(StackTraceElement traceElement : stackTraceElements)
				{
					sb.append(traceElement.getClassName()).append(" - ").append(
							traceElement.getMethodName()).append(" - ").append(
							traceElement.getLineNumber());

					AndroidLog.e(Tag, sb.toString());
					sb = new StringBuilder();
				}
			}
			else
			{

			}
		}
	}

	public static void v(String tag, String message)
	{
		if(Globals.isTest)
			Log.v(TAG_PREFIX + tag, MSG_PREFIX + message);
	}

	public static void d(String tag, String message)
	{
		if(Globals.isTest)
			Log.d(TAG_PREFIX + tag, MSG_PREFIX + message);
	}

	public static void i(String tag, String message)
	{
		if(Globals.isTest)
			Log.i(TAG_PREFIX + tag, MSG_PREFIX + message);
	}

	public static void w(String tag, String message)
	{
		if(Globals.isTest)
			Log.w(TAG_PREFIX + tag, MSG_PREFIX + message);
	}

	public static void e(String tag, String message)
	{
		if(Globals.isTest)
			Log.e(TAG_PREFIX + tag, MSG_PREFIX + message);
	}

	// Message plus throwables
	public static void v(String tag, String message, Throwable tr)
	{
		if(Globals.isTest)
			Log.v(TAG_PREFIX + tag, MSG_PREFIX + message, tr);
	}

	public static void d(String tag, String message, Throwable tr)
	{
		if(Globals.isTest)
			Log.d(TAG_PREFIX + tag, MSG_PREFIX + message, tr);
	}

	public static void i(String tag, String message, Throwable tr)
	{
		if(Globals.isTest)
			Log.i(TAG_PREFIX + tag, MSG_PREFIX + message, tr);
	}

	public static void w(String tag, String message, Throwable tr)
	{
		if(Globals.isTest)
			Log.w(TAG_PREFIX + tag, MSG_PREFIX + message, tr);
	}

	public static void e(String tag, String message, Throwable tr)
	{
		if(Globals.isTest)
			Log.e(TAG_PREFIX + tag, MSG_PREFIX + message, tr);
	}
}
