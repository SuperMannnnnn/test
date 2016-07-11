package com.tarena.allrun.app;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Application;
import android.os.Process;

import com.baidu.mapapi.SDKInitializer;
/**
 *Created by dabao 2016��6��30��
 */
public class TApplication extends Application{
	public ArrayList<Activity>ativitylist = new ArrayList<Activity>();
	public void finishActivity(){
		for (Activity activity : ativitylist) {
			try {
				activity.finish();
			} catch (Exception e) {
			}	
		}
		Process.killProcess(Process.myPid());
	}
	@Override
	public void onCreate() {
		super.onCreate();
		//�����쳣û�м�catch����ܵ�crashHandler
//		CrashHandler crashHandler = new CrashHandler(this);
//		Thread.setDefaultUncaughtExceptionHandler(crashHandler);
		SDKInitializer.initialize(this);
	}
}
