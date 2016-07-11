package com.example.day0202;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void doClick(View view) {
		EditText et = (EditText) findViewById(R.id.editText1);
		EditText et1 = (EditText) findViewById(R.id.editText2);
		TextView tv = (TextView) findViewById(R.id.textView1);
		int a = Integer.parseInt(et.getText().toString());
		int b = Integer.parseInt(et1.getText().toString());
		int c = 0;
		int d = 0;

		if (a > b) {
			c = b;
		} else {
			c = a;
		}
		Log.d("this is the min one", ""+c);
		for (int i = c; i > 0; i--) {
			if (a % i == 0) {
				 if(b % i == 0){
					 d=i;
				 }
				//break;
			}
		}
		Log.d("////////////", ""+d);
//		 tv.setText(d);
		 tv.setText(""+d);

	}
}
