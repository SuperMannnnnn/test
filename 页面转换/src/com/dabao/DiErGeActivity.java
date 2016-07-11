package com.dabao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DiErGeActivity extends Activity {
	private Button bt1;
	private Button bt2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_photo);
		
		bt1=(Button) findViewById(R.id.bt1);
		bt2=(Button) findViewById(R.id.bt2);
		
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i =new Intent(DiErGeActivity.this,MainActivity.class);
				startActivity(i);
			}
		});
		bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent a = new Intent(DiErGeActivity.this, MainActivity.class);
				startActivity(a);
			}
		});
	}

	
		
		
		
		
	
	
	
}
