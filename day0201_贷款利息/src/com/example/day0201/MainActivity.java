package com.example.day0201;

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
		EditText et1 = (EditText)findViewById(R.id.editText2);
		EditText et2 = (EditText)findViewById(R.id.editText3);
		TextView tv = (TextView)findViewById(R.id.textView1);
		
		double p = Double.parseDouble(et.getText().toString());
		double r = Double.parseDouble(et1.getText().toString());
		double m = Double.parseDouble(et2.getText().toString());
		double a = ((p*10000)*(r/100/12)*Math.pow(1+(r/100/12),m*12))/(Math.pow(1+(r/100/12),m*12)-1);
		
		a*=12;

		tv.setText(String.valueOf(String.format("%.2f",a)));
		
		
		
	}
}
