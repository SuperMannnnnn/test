package com.dabao.volley;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity {
	private RequestQueue queue ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		queue = Volley.newRequestQueue(this);
		setVolley();
		//postVolley();
	}

	private void postVolley() {
		String url = "http://www.baidu.com";
		StringRequest sr = new StringRequest(Method.POST, url , new Listener<String>() {

			@Override
			public void onResponse(String response) {
				
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
			    Map<String, String> map = new HashMap<String, String>();  
		        map.put("params1", "value1");  
		        map.put("params2", "value2");  
		        return map; 
			}
		};
		
		
		
		
	}

	private void setVolley() {
		String url = "http://www.baidu.com";
		StringRequest sr = new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.d("TAG", "------------>"+response);
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("TAG", "------+++++++++++------>"+error.getMessage(), error); 
			}
		});
		queue.add(sr);
		
	}

	
	
	

}
