package com.dabao.pictruestore.app;

import org.xutils.x;

import com.dabao.pictruestore.entity.Picture;

import android.app.Application;

/**
 *Created by dabao 2016Äê6ÔÂ26ÈÕ
 */
public class MyApplication extends Application{

	private static MyApplication context;
	private Picture picture;
	@Override
	public void onCreate() {
		super.onCreate();
		x.Ext.init(this);
		context = this;
		
	}
	public Picture getPicture() {
		return picture;
	}
	public void setPicture(Picture picture) {
		this.picture = picture;
	}
	public static MyApplication getContext() {
		return context;
	}
	public static void setContext(MyApplication context) {
		MyApplication.context = context;
	}
	
	
	
	
}
