package com.example.com.day0201;

import android.os.Bundle;
import android.app.Activity;
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
	public void doClick(View view){
		EditText et = (EditText)findViewById(R.id.editText1);
		TextView tv = (TextView)findViewById(R.id.textView1);
		double r = Double.parseDouble(et.getText().toString());
		double ¦Ð = 3.14;
		double c = 2*¦Ð*r;
		tv.setText("ÖÜ³¤Îª£º"+c);
				
	}

}























































































































































































