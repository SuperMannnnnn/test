package com.dabao.meun.fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dabao.meun.R;
import com.dabao.meun.adapter.JokeAdapter;
import com.dabao.meun.entity.Joke;
import com.dabao.meun.model.JokeModel;
import com.dabao.meun.model.JokeModel.Callback;

public class JokeFragment extends Fragment implements OnRefreshListener {
	
	/**
	 * 刷新控件
	 */
	private SwipeRefreshLayout refresh;
	private int page;
	
	
	private ListView lvJoke;
	private List<Joke> jokes;
	private JokeAdapter jokeAdapter;
	JokeModel model = new JokeModel();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.joke_listview, null);
		initView(view);
		
		page=1;
		getData(page);
		
		
		refresh.setOnRefreshListener(this);
		return view;
	}

	private void getData(int page) {
		model.findJokeList(new Callback() {
			@Override
			public void onJokeListLoaded(List<Joke> jokes) {
				JokeFragment.this.jokes=jokes;
				setAdapter(jokes);
			}
		}, page, 20);
		
	}

	private void setAdapter(List<Joke>jokes) {
		if(page==1){
		jokeAdapter = new JokeAdapter(getActivity(), jokes,R.layout.joke_listview,R.layout.joke_textview);
		lvJoke.setAdapter(jokeAdapter);
		}else{
			jokes.addAll(jokes);
			jokeAdapter.notifyDataSetChanged();
		}
		
		
	}

	private void initView(View view) {
		lvJoke = (ListView) view.findViewById(R.id.joke_listview);
		refresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
		refresh.setColorScheme(R.color.red, R.color.green, R.color.bule, R.color.gray);
	}





	/**
	 * 发送get请求 获取服务端返回的输入流
	 * 
	 * @param url
	 * @return
	 * @throws IOExceptionException
	 */
	public static void get() throws IOException {
		URL url = new URL(
				"http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_text?page=1");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("apikey", "d2d18ec4477c47ff00ca75ff15397e21");
		conn.connect();
		InputStream is = conn.getInputStream();
		StringBuilder sb = new StringBuilder();
		String line = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		try {
			List<Joke> jokes= parseSongInfo(sb.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.i("dabao", "sb-->:" + sb);
	}

	/**
	 * 解析json字符串 获取音乐的基本信息
	 * 
	 * @param json
	 *            { songurl: {url: [{},{},{},{}] } , songinfo: {} }
	 * @return
	 * @throws JSONException
	 */
	public static List<Joke> parseSongInfo(String json) throws JSONException {
		JSONObject obj = new JSONObject(json);
		JSONObject conObj = obj.getJSONObject("showapi_res_body");
		JSONArray jokeAry = conObj.getJSONArray("contentlist");
		// 解析集合[{}{}{}]
		List<Joke> jokes = parseUrls(jokeAry);
		
		return jokes;
	}

	/**
	 * [{},{},{},{},{}]
	 * 
	 * @param urlAry
	 * @return
	 * @throws JSONException
	 */
	public static List<Joke> parseUrls(JSONArray urlAry) throws JSONException {
		List<Joke> jokes = new ArrayList<Joke>();
		for (int i = 0; i < urlAry.length(); i++) {
			JSONObject obj = urlAry.getJSONObject(i);
			Joke joke = new Joke(obj.getString("title"), obj.getString("text"));
			jokes.add(joke);
		}
		Log.i("dabao", "jokes-->:" + jokes);
		return jokes;
	}

	@Override
	public void onRefresh() {
		page++;
		getData(page);
		
		
		
//new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				try {
//					Thread.sleep(1500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				
//				runOnUiThread(new Runnable() {
//					public void run() {
//						jokeAdapter.notifyDataSetChanged();
//						refresh.setRefreshing(false);
//					}
//				});
//			}
//		}).start();
	
			}

	protected void runOnUiThread(Runnable runnable) {
		// TODO Auto-generated method stub
		
	}

}
