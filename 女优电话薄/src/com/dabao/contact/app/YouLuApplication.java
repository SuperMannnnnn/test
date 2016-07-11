package com.dabao.contact.app;

import android.app.Application;

public class YouLuApplication extends Application{
	private static YouLuApplication app;
	
	@Override
	public void onCreate() {
		super.onCreate();
		app=this;
	}
	
	public static YouLuApplication getApp(){
		return app;
		
	}
	
}
