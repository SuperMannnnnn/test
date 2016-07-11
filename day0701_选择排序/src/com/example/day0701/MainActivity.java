package com.example.day0701;

import java.util.Arrays;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView tv1;
	TextView tv2;
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
		tv1 = (TextView)findViewById(R.id.textView1);
		tv2 =(TextView)findViewById(R.id.textView2);
		tv1.setText("");
		tv2.setText("");
		//产生数组
		int [] a=suiji();
		tv1.setText(Arrays.toString(a));
		//对a数组排序
		sort(a);
		//对排列好的数组显示出来
		tv2.append("\n---------------\n");
		tv2.append(Arrays.toString(a));

	}
	private int [] suiji() {
		int len = new Random().nextInt(6);
		int[] a = new int[len];
		for(int i=0;i<a.length;i++){
			a[i] = new Random().nextInt(100);
		}
		return a;
	}

	private void  sort(int [] a){
		/*
		 * 1.循环i从0到<a.length递增
               2.定义min=i,假设i位置就是最小值位置
               3.循环j从i+1到<a.length递增
                   4.如果a[j]<a[min]
                       5.min定位到j位置
               6.交换 i 和 min 位置的值
		 */
		int min = 0;
		for(int i=0;i<a.length;i++){
			for(int j=i+1;j<a.length;j++){
				if(a[j]<a[min]){
					min=j;
				}
			}
			int b=a[i];
			a[i]=a[min];
			a[min]=b;
			
		}
		tv2.append(Arrays.toString(a)+"\n");
	}
}
