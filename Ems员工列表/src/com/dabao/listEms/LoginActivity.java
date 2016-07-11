package com.dabao.listEms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class LoginActivity extends Activity {
	private ImageView ivCode;
	
	private Bitmap bitmap;
	private String JSESSIONID;
	private EditText etName;
	private EditText etPassword;
	private EditText etCode;
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case HANDLER_LOGIN_SUCCESS:
				Toast.makeText(LoginActivity.this, "恭喜,登录成功!", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(LoginActivity.this, ListEmpActivity.class);
				startActivity(intent);
				finish();
				break;
			case HANDLER_LOGIN_FAIL:
				Toast.makeText(LoginActivity.this, "恭喜,登录失败:"+msg.obj, Toast.LENGTH_SHORT).show();
				break;
			case HANDLER_IMAGE_LOAD_SUCCESS:
				if(bitmap!=null){ //图片加载成功
					ivCode.setImageBitmap(bitmap);
				}
				break;
			}
		}
	};
	public static final int HANDLER_IMAGE_LOAD_SUCCESS=1;
	private static final int HANDLER_LOGIN_SUCCESS = 2;
	private static final int HANDLER_LOGIN_FAIL = 3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setView();
		new Thread(){
			public void run() {
				try {
					getCode();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
		
	}
	protected void getCode() throws IOException {
		URL url = new URL("http://176.3.16.123:8080/EmsServer/getCode.do");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		InputStream is = conn.getInputStream();
		bitmap = BitmapFactory.decodeStream(is);
		JSESSIONID = conn.getHeaderField("Set-Cookie");
		if(JSESSIONID!=null){
			JSESSIONID = JSESSIONID.split(";")[0];
		}
		
		handler.sendEmptyMessage(HANDLER_IMAGE_LOAD_SUCCESS);
	}
	
	private void login() throws IOException, JSONException {
		URL url = new URL("http://176.3.16.123:8080/EmsServer/login.do");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Cookie", JSESSIONID);
		OutputStream os = conn.getOutputStream();
		String name = etName.getText().toString().trim();
		String pwd = etPassword.getText().toString();
		String code = etCode.getText().toString();
		String params = "loginname="+name+"&password="+pwd+"&code="+code; 
		os.write(params.getBytes("utf-8"));
		os.flush();
		
		InputStream is = conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = "";
		while((line = reader.readLine()) != null){
			sb.append(line);
		}
		String jsonText = sb.toString();
		
		Log.i("Test", jsonText);
		
		JSONObject obj = new JSONObject(jsonText);
		String res = obj.getString("result");
		if("ok".equals(res)){
			handler.sendEmptyMessage(HANDLER_LOGIN_SUCCESS);
		}else{
			Message msg = new Message();
			msg.what = HANDLER_LOGIN_FAIL;
			msg.obj = obj.getString("msg");
			handler.sendMessage(msg);
		}
		
		
		
	}
	private void setView() {
		ivCode = (ImageView) findViewById(R.id.ivCode);
		etName = (EditText) findViewById(R.id.etName);
		etPassword = (EditText) findViewById(R.id.etPassword);
		etCode = (EditText) findViewById(R.id.etCode);
	}
	
	public void doClick(View view) {
		switch (view.getId()) {
		case R.id.btnToRegist:
			Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
			startActivityForResult(intent, 101);
			break;
		case R.id.btnLogin:
			new Thread(){
				public void run() {
					try {
							login();
						} catch (JSONException e) {
							e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}.start();
			break;
		case R.id.ivCode:
			new Thread(){
				public void run() {
					try {
						getCode();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}.start();
			break;

		}

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 101:  //去注册界面后  返回的结果
			if(resultCode==Activity.RESULT_OK){ //结果成功
				//从data中获取name和password
				String name=data.getStringExtra("name");
				String pwd = data.getStringExtra("password");
				etName.setText(name);
				etPassword.setText(pwd);
			}
			break;

		}
	}

	
}
