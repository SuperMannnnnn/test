package com.dabao.firstmatch.model.impl;

import java.util.List;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dabao.firstmatch.app.MyApplication;
import com.dabao.firstmatch.entity.Result;
import com.dabao.firstmatch.entity.Text;
import com.dabao.firstmatch.model.ITextModel;
import com.google.gson.Gson;

/**
 *Created by dabao 2016��7��6��
 */
public class TextModel implements ITextModel {

	private List<Result> results;
	private RequestQueue queue;
	
	
	
	public TextModel() {
		
	}



	@Override
	public void loadText(final AsynCallback back) {
		String url ="http://gank.io/api/data/Android/10/1";
//		final String string = HttpUtils.getResponse();
		queue = Volley.newRequestQueue(MyApplication.getContext());
		StringRequest request = new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					Gson gson = new Gson();
					Text text = gson.fromJson(response, Text.class);
					results = text.getResults();
					Log.i("GalleryModelImpl++image", results+"");
					back.onSuccess(results);
					
				} catch (Exception e) {
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		});
		queue.add(request);
	}

}
