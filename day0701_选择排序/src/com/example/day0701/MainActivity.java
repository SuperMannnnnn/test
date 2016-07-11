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
		//��������
		int [] a=suiji();
		tv1.setText(Arrays.toString(a));
		//��a��������
		sort(a);
		//�����кõ�������ʾ����
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
		 * 1.ѭ��i��0��<a.length����
               2.����min=i,����iλ�þ�����Сֵλ��
               3.ѭ��j��i+1��<a.length����
                   4.���a[j]<a[min]
                       5.min��λ��jλ��
               6.���� i �� min λ�õ�ֵ
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
