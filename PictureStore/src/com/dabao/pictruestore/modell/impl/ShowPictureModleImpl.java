package com.dabao.pictruestore.modell.impl;

import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dabao.pictruestore.app.MyApplication;
import com.dabao.pictruestore.entity.Gallery;
import com.dabao.pictruestore.entity.Picture;
import com.dabao.pictruestore.modell.IShowPictureModel;
import com.dabao.pictruestore.ui.UrlConsts;
import com.google.gson.Gson;

/**
 *Created by dabao 2016Äê7ÔÂ5ÈÕ
 */
public class ShowPictureModleImpl implements IShowPictureModel{
	private List<Picture>pictures;
	private RequestQueue queue;
	
	public ShowPictureModleImpl() {
		queue = Volley.newRequestQueue(MyApplication.getContext());
	}


		

	@Override
	public void loadGallerys(final AsynCallback back, int id) {
		String url = UrlConsts.URL_GALLERY_SHOW+"?id="+id;
//		Log.i("picID-->>>", "id = "+id);
		final Gson gson = new Gson();
		StringRequest request = new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					Gallery gallery = gson.fromJson(response, Gallery.class);
					pictures = gallery.getList();
//					Log.i("dabaoShowPictureModleImpl", ":::"+pictures);
					back.onSuccess(pictures);
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
