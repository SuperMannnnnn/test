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
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistActivity extends Activity {
	private EditText etName;
	private EditText etPassword;
	private EditText etRepwd;
	private EditText etRealname;
	private EditText etEmail;
	private static final int HANDLER_REGIST_SUCCESS = 1;
	private static final int HANDLER_REGIST_FAIL = 2;
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case HANDLER_REGIST_SUCCESS:
				//����setResult������LoginActivity�ش�ע��
				//�ɹ����˺ź�����
				Intent data = new Intent();
				data.putExtra("name", etName.getText().toString());
				data.putExtra("password", etPassword.getText().toString());
				setResult(Activity.RESULT_OK, data);
				finish();
				break;
			case HANDLER_REGIST_FAIL:
				Toast.makeText(RegistActivity.this, "��ϲ!ע��ʧ��:" + msg.obj,
						Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		// �ؼ���ʼ��
		setViews();
	}

	private void setViews() {
		etName = (EditText) findViewById(R.id.etName);
		etPassword = (EditText) findViewById(R.id.etPassword);
		etRepwd = (EditText) findViewById(R.id.etRepwd);
		etRealname = (EditText) findViewById(R.id.etRealname);
		etEmail = (EditText) findViewById(R.id.etEmail);

	}

	public void doClick(View view) {
		switch (view.getId()) {
		case R.id.btnRegist:
			new Thread() {
				public void run() {
					try {
						regist();
					} catch (JSONException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}.start();
			break;

		}

	}

	private void regist() throws IOException, JSONException {

		String name = etName.getText().toString().trim();
		String pwd = etPassword.getText().toString().trim();
		String repwd = etRepwd.getText().toString().trim();
		String realname = etRealname.getText().toString().trim();
		String email = etEmail.getText().toString().trim();
		
		if(!pwd.equals(repwd)){
			Message msg = new Message();
			msg.what = HANDLER_REGIST_FAIL;
			msg.obj = "�������벻һ��Ŷ~~";
			handler.sendMessage(msg);
			return;
		}

		URL url = new URL("http://176.3.16.123:8080/ems/regist.do");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");

		OutputStream os = conn.getOutputStream();
		String params = "loginname=" + name + "&password=" + pwd + "&realname="
				+ realname + "&email=" + email;
		os.write(params.getBytes("utf-8"));
		os.flush();

		InputStream is = conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = "";
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		String jsonText = sb.toString();

		JSONObject obj = new JSONObject(jsonText);
		String res = obj.getString("result");
		if ("ok".equals(res)) {
			handler.sendEmptyMessage(HANDLER_REGIST_SUCCESS);
		} else {
			Message msg = new Message();
			msg.what = HANDLER_REGIST_FAIL;
			msg.obj = obj.getString("msg");
			handler.sendMessage(msg);
		}

	}

}
