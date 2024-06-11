package com.androiddevs.brailletranslator;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import android.content.Intent;
import android.app.Application;
import android.content.Context;
import android.app.PendingIntent;
import android.app.AlarmManager;

public class AndroidDevsApplication extends Application
{
	private Thread.UncaughtExceptionHandler uncaughtexceptionhandler;
	@Override
	public void onCreate()
	{
		this.uncaughtexceptionhandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
				@Override
				public void uncaughtException(Thread thread, Throwable throwable)
				{
					Intent intent = new Intent(getApplicationContext(), DebugActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
					intent.putExtra("error", getStackTrace(throwable));
					PendingIntent pendingintent = PendingIntent.getActivity(getApplicationContext(), 11111, intent, PendingIntent.FLAG_ONE_SHOT);
					AlarmManager alarmmanager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
					alarmmanager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 1000, pendingintent);
					android.os.Process.killProcess(android.os.Process.myPid());
					System.exit(2);
					uncaughtexceptionhandler.uncaughtException(thread, throwable);
				}
			});
		super.onCreate();
	}
	private String getStackTrace(Throwable throwable)
	{
		final Writer writer = new StringWriter();
		final PrintWriter printwriter = new PrintWriter(writer);
		Throwable cause = throwable;
		while (cause != null)
		{
			cause.printStackTrace(printwriter);
			cause = cause.getCause();
		}
		final String StackTraceString = writer.toString();
		printwriter.close();
		return StackTraceString;
	}
}
