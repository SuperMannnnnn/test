package com.tarena.allrun.activity;

import com.tarena.allrun.app.TApplication;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 *Created by dabao 2016Äê7ÔÂ1ÈÕ
 */
public class BaseActivity extends FragmentActivity{

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		try {
			TApplication tApplication = (TApplication) getApplication();
			tApplication.ativitylist.add(this);
		} catch (Exception e) {
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		try {
			TApplication tApplication = (TApplication) getApplication();
			tApplication.ativitylist.remove(this);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
