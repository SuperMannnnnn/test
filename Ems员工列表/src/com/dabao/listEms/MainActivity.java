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
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText etName;
	private EditText etSalary;
	private EditText etAge;
	private RadioGroup radioGroup;
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case HANDLER_ADD_EMP_SUCCESS:
				Toast.makeText(MainActivity.this, "恭喜,添加成功!",
						Toast.LENGTH_SHORT).show();
//				Intent intent = new Intent(MainActivity.this, ListEmpActivity.class);
//				startActivity(intent);
				break;

			case HANDLER_ADD_EMP_FAIL:
				Toast.makeText(MainActivity.this, "对不起,添加失败!",
						Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};

	public static final int HANDLER_ADD_EMP_SUCCESS = 1;
	public static final int HANDLER_ADD_EMP_FAIL = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 初始化控件
		setViews();
	}

	private void setViews() {
		etName = (EditText) findViewById(R.id.etName);
		etSalary = (EditText) findViewById(R.id.etSalary);
		etAge = (EditText) findViewById(R.id.etAge);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
	}

	public void doClick(View view) {
		switch (view.getId()) {
		case R.id.btnAdd:
			new Thread() {
				public void run() {
					try {
							addEmp();
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

	private void addEmp() throws IOException, JSONException {
		// 1. URL
		URL url = new URL("http://176.3.16.123:8080/EmsServer/addEmp");
		// 2. HttpUrlConnection
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 3. POST
		conn.setRequestMethod("POST");
		// 4. 属性
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		// 5. 设置参数 output
		OutputStream os = conn.getOutputStream();
		String name = etName.getText().toString().trim();
		String salary = etSalary.getText().toString().trim();
		String age = etAge.getText().toString().trim();
		String gender = radioGroup.getCheckedRadioButtonId() == R.id.radioM ? "m"
				: "f";
		String params = "name=" + name + "&salary=" + salary + "&age=" + age
				+ "&gender=" + gender;
		os.write(params.getBytes("utf-8"));
		os.flush();
		// 6. JSON
		InputStream is = conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = "";
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		String jsonText = sb.toString();
		// 7. 解析
		JSONObject obj = new JSONObject(jsonText);
		String res = obj.getString("result");
		if ("ok".equals(res)) {
			handler.sendEmptyMessage(HANDLER_ADD_EMP_SUCCESS);
		} else {
			Message msg = new Message();
			msg.what = HANDLER_ADD_EMP_FAIL;
			msg.obj = obj.getString("msg");
			handler.sendMessage(msg);
		}

	}

}
