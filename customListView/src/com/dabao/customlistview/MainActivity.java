package com.dabao.customlistview;

import java.util.ArrayList;
import java.util.Currency;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.dabao.customlistview.CustomListView.OnRefreshListener;

public class MainActivity extends Activity {

	ArrayList<String> list = new ArrayList<String>();
	CustomListView customListView;
	MyAdapter myAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); 
		for (int i=0;i<10;i++)
		{
			list.add("data"+i);
		}
		myAdapter=new MyAdapter(this, list); 
		customListView=(CustomListView) findViewById(R.id.coustomListView);
		customListView.setAdapter(myAdapter);
		MyOnRefreshListener myOnRefreshListener = new MyOnRefreshListener();
		customListView.setOnRefreshListener(myOnRefreshListener);
	}
	 	
	//2.5 дʵ����
		class MyOnRefreshListener implements  OnRefreshListener{

			@Override
			public void onRefresh(final CustomListView customListView) {
				new Thread(){
					@Override
					public void run() {
						try {
							this.sleep(2000);
							String data = "������ȡ����";
							list.add(data);
							//runOnUiThread�������ڲ����������߳�����
							runOnUiThread(new  Runnable() {
								public void run() {
									myAdapter.notifyDataSetChanged();
									customListView.refreshComplete();
								}
							});
						} catch (Exception e) {
						}
					}
				}.start();
			}
			
		}
	
	
	
	
	
	
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}