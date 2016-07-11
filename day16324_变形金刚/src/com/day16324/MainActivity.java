package com.day16324;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Button bt5;
	private Button bt4;
	private Button bt3;
	private Button bt2;
	private Button bt1;
	private TextView tv;
	private Transformer t;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 tv = (TextView)findViewById(R.id.tv1);
		 bt1 = (Button)findViewById(R.id.bt1);
		 bt2 = (Button)findViewById(R.id.bt2);
		 bt3 = (Button)findViewById(R.id.bt3);
		 bt4 = (Button)findViewById(R.id.bt4);
		 bt5 = (Button)findViewById(R.id.bt5);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void doClick(View view) {
		
		switch(view.getId()){
		case R.id.bt1:f1();break;
		case R.id.bt2:f2();break;
		case R.id.bt3:f3();break;
		case R.id.bt4:f4();break;
		case R.id.bt5:f5();break;
		
		
		
		}
		

	}

	private void f1() {
		t = new Transformer();
		tv.setText("以创建变形金刚");
	}

	private void f2() {
		t.setWeapon(new Sword());
		tv.setText("倚天剑");
	}

	private void f3() {
		t.setWeapon(new Gan());
		tv.setText("冲锋枪");
	}

	private void f4() {
		t.setWeapon(new Lyb());
		tv.setText("狼牙棒");
		
	}

	private void f5() {
		t.attack(tv);
		
	}

}
