package com.dabao.pictruestore.modell.impl;

import java.util.List;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dabao.pictruestore.app.MyApplication;
import com.dabao.pictruestore.entity.Detail;
import com.dabao.pictruestore.entity.New;
import com.dabao.pictruestore.modell.IDetailModel;
import com.dabao.pictruestore.ui.UrlConsts;
import com.google.gson.Gson;

/**
 *Created by dabao 2016Äê7ÔÂ10ÈÕ
 */
public class DetailModelImpl implements IDetailModel{
 private List<New> news;
 private RequestQueue queue;
	
 
	public DetailModelImpl() {
		queue = Volley.newRequestQueue(MyApplication.getContext());
	}







	@Override
	public void loadDetail(final AsynCallback back) {
		String url = UrlConsts.URL_NEWS;
		StringRequest request = new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				Gson gson = new Gson();
				try {
					Detail detail = gson.fromJson(response, Detail.class);
					news = detail.getDetail();
					Log.i("DetailModelImpl", "aaaaaaaaa:::"+news);
					back.onSuccess(news);
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
