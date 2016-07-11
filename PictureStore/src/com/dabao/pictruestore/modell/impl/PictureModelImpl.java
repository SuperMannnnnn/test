package com.dabao.pictruestore.modell.impl;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dabao.pictruestore.app.MyApplication;
import com.dabao.pictruestore.entity.Picture;
import com.dabao.pictruestore.modell.IPictureModel;
import com.dabao.pictruestore.ui.UrlConsts;

/**
 *Created by dabao 2016Äê6ÔÂ26ÈÕ
 */
public class PictureModelImpl implements IPictureModel{
	private List<Picture>pictures;
	private RequestQueue queue;
	
	
	
	public PictureModelImpl() {
		queue = Volley.newRequestQueue(MyApplication.getContext());
	}



	@Override
	public void loadPictures(AsynCallback back) {
		String url = UrlConsts.URL_PICTURE;
		StringRequest request = new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					JSONObject obj = new JSONObject(response);
					JSONArray array = obj.getJSONArray("tngou");
					
				} catch (JSONException e) {
					e.printStackTrace();
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
