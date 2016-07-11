package com.example.day0801;

import java.util.Arrays;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText et;
	TextView tv1;
	TextView tv2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et=(EditText)findViewById(R.id.editText1);
		 tv1 =(TextView)findViewById(R.id.textView1);
		 tv2=(TextView)findViewById(R.id.textView2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void doClick(View view) {
		int[] a = suiji();
		 sort(a);
		 tv1.setText(Arrays.toString(a));
		

	}

	private int[] suiji() {
		int x =new Random().nextInt(10) ;
		int []a = new int [x];
		for(int i=0;i<a.length;i++){
			a[i]= new Random().nextInt(100);
		}
		return a;
	}

	private void sort(int[] a) {
		int y = Integer.parseInt(et.getText().toString());
		int L=0;
		int R=0;
		int mid;
		while(y>0){
		mid=(L+R)/2;
		if(mid>y){
			R=mid-1;
		}else if(mid<y){
			L=mid+1;
		}else{
			mid=y;
			tv2.setText(mid);
		}return;
		
		}
	}

}
