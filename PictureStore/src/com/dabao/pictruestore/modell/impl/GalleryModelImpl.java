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
import com.dabao.pictruestore.entity.Gallery;
import com.dabao.pictruestore.entity.TotalGallery;
import com.dabao.pictruestore.modell.IGalleryModel;
import com.dabao.pictruestore.ui.UrlConsts;
import com.google.gson.Gson;

/**
 *Created by dabao 2016Äê7ÔÂ4ÈÕ
 */
public class GalleryModelImpl implements IGalleryModel{

	private List<Gallery>gallerys;
	private RequestQueue queue;
	
	
	
	public GalleryModelImpl() {
		queue = Volley.newRequestQueue(MyApplication.getContext());
	}




	@Override
	public void loadGallerys(final AsynCallback back) {
		String url = UrlConsts.URL_GALLERY;
		final Gson gson=new Gson();

		StringRequest request = new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				TotalGallery totalGallery=gson.fromJson(response, TotalGallery.class);
				  gallerys = totalGallery.getTngou();
				
				
//				Log.i("GalleryModelImpl++image", gallerys+"");
				back.onSuccess(gallerys);
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
