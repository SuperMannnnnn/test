package com.dabao.httpget;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private EditText etDate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etDate = (EditText) findViewById(R.id.et_date);
	}
	
	public void doClick(View view) {
		switch (view.getId()) {
		case R.id.button1:
			new Thread(){
				public void run() {
					try {
						//findAllflightsByPost();
						findAllFlightsByGet();
					} catch (Exception e) {
					}
				}

				
			}.start();
			break;
		}
	}
	
	private void findAllflightsByPost() throws IOException {
		//1.URL
		URL url = new URL("http://176.3.16.123:8888/android_day02_dabao_jsp/findFlights.jsp");
		//2.HTTPURLConnection
		
		//3.POST
		
		//4.��HTTPURLConnection����post����������
		
		//5.�����˴��ݲ���������Ҫʹ��out�����
		
		//6.�������󲢻�ȡ������(����˷��ص�����)
		
		//7.����
		
		
	}
	
	
	//���������ȡxml�ַ���
	private void findAllFlightsByGet() throws IOException {
		//1.url
		URL url = new URL("http://176.3.16.123:8888/android_day02_dabao_jsp/findFlights.jsp");
		//2.HTTPURLConnection
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//3.GET
		conn.setRequestMethod("GET");
		//4.GETINPUTSTREAM    
		InputStream is = conn.getInputStream();
		//5.����
		BufferedReader reader =  new BufferedReader(new InputStreamReader(is, "utf-8"));
		StringBuilder sb = new StringBuilder();
		String line = "";
		while((line=reader.readLine()) != null ){
			sb.append(line);
		}
		String respText = sb.toString();
		Log.i("dabao", "respText"+respText);
		
	}

}
