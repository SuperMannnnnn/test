package com.dabao.meun.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.json.JSONException;

import android.os.AsyncTask;

import com.dabao.meun.entity.Joke;
import com.dabao.meun.fragment.JokeFragment;
import com.dabao.meun.util.HttpUtils;

public class JokeModel {
	public void findJokeList(final Callback callback, final int offset, final int size){
		AsyncTask<String, String, List<Joke>> task = new AsyncTask<String, String, List<Joke>>(){
			@Override
			protected List<Joke> doInBackground(String... params) {
				String path = HttpUtils.getJokeListUrl(offset, size);
					List<Joke> jokes;
					try {
						InputStream is = HttpUtils.get(path);
						String json = HttpUtils.isToString(is);
						jokes = JokeFragment.parseSongInfo(json);
						return jokes;
					} catch (JSONException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				return null;
			}
			@Override
			protected void onPostExecute(List<Joke> jokes) {
				callback.onJokeListLoaded(jokes);
			}
			
		};
		task.execute();
	}
	
	public interface Callback {
		/**
		 * ���б������Ϻ� ������ø÷��� 
		 * �ڸ÷�����ʵ������Ҫִ���б������Ϻ��ҵ���߼�
		 * @param musics
		 */
		void onJokeListLoaded(List<Joke> jokes);
	}
	
	
}
