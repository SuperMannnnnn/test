package com.dabao.firstmatch.app;

import org.xutils.x;

import android.app.Application;

/**
 *Created by dabao 2016Äê7ÔÂ6ÈÕ
 */
public class MyApplication extends Application{

	private static MyApplication context;
	
	@Override
	public void onCreate() {
		super.onCreate();
		context = this;
		x.Ext.init(this);
		
	}

	public static MyApplication getContext() {
		return context;
	}

	public static void setContext(MyApplication context) {
		MyApplication.context = context;
	}
	
	
}
