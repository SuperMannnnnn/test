package com.dabao.listEms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class ListEmpActivity extends Activity {
	private ListView listview;
	private ArrayList<Emp> emps;
	private EmpAdapter adapter;
	public static final int HANDLER_LOAD_EMP_SUCCESS = 1;
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case HANDLER_LOAD_EMP_SUCCESS:
				// 集合已经加载完毕 更新adapter
				setAdapter();
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_ems);
		listview = (ListView) findViewById(R.id.listview);

	}

	@Override
	protected void onResume() {
		super.onResume();

		new Thread() {
			public void run() {
				try {
					findAllEmps();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}.start();

	}

	protected void setAdapter() {
		if (adapter == null) {
			adapter = new EmpAdapter(emps, this);
			listview.setAdapter(adapter);

		} else {
			adapter.notifyDataSetChanged();
		}

	}

	private void findAllEmps() throws IOException, JSONException {
		URL url = new URL("http://176.3.16.123:8080/EmsServer/listEmp");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 3. GET
		conn.setRequestMethod("GET");
		// 4. inputStream
		InputStream is = conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = "";
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		String jsonText = sb.toString();
		// 5. 解析json
		JSONObject obj = new JSONObject(jsonText);
		String res = obj.getString("result");
		if ("ok".equals(res)) {
			JSONArray ary = obj.getJSONArray("data");
			if (emps == null) {
				emps = new ArrayList<Emp>();
			} else {
				emps.clear();
				// 清空旧集合 然后向旧集合中添加对象
				// 这样在调用notifyDataSetChanged方法时才不会出错
			}

			for (int i = 0; i < ary.length(); i++) {
				JSONObject empObj = ary.getJSONObject(i);
				Emp emp = new Emp(empObj.getInt("id"),
						empObj.getString("name"), empObj.getDouble("salary"),
						empObj.getInt("age"), empObj.getString("gender"));
				emps.add(emp);
			}
			Log.i("info", "" + emps.toString());
			handler.sendEmptyMessage(HANDLER_LOAD_EMP_SUCCESS);

		} else {

		}

	}

	/**
	 * 注册监听
	 * 
	 * @param view
	 */
	public void doClick(View view) {
		switch (view.getId()) {
		case R.id.btnAdd: // 跳转到添加界面
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			break;
		}
	}

}
