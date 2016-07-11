package com.tarena.allrun.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MotionEvent;

import com.tarena.allrun.R;
import com.tarena.allrun.app.TApplication;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TApplication tApplication = (TApplication) getApplication();
		tApplication.ativitylist.add(this);
		
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				startActivity(new Intent(MainActivity.this, MainFragmentACtivity.class));
			}
		}, 2000);
	}
	

	
	@Override
	protected void onDestroy() {
		super.onDestroy();
//		TApplication tApplication = (TApplication) getApplication();
//		tApplication.ativitylist.remove(this);
	}
	
	
	
	@SuppressWarnings("null")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		String s = null;
		s.toCharArray();
		return super.onTouchEvent(event);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
