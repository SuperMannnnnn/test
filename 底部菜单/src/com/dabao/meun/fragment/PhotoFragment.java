package com.dabao.meun.fragment;

import java.io.IOException;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.dabao.meun.R;
import com.dabao.meun.adapter.photoAdapter;
import com.dabao.meun.entity.listPhoto;

public class PhotoFragment extends Fragment{
	private RequestQueue queue;
	private List<listPhoto>photos;
	private GridView gvPhoto;
	private photoAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_photo, null);
		gvPhoto = (GridView) view.findViewById(R.id.gvphoto);
		queue = Volley.newRequestQueue(getActivity());
		
		try {
			loadPhotoList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setAdapter();
		return view;
	}
	

	
	
	
	/**
	 * º”‘ÿÕº∆¨
	 * @throws IOException 
	 */
	public void loadPhotoList() throws IOException{
	String url = "http://apis.baidu.com/tngou/gallery/show";
	
	//TODO
	
	}
	
	/**
	 * ∏¯listView…Ë÷√Adapter
	 */
	protected void setAdapter() {
		adapter = new photoAdapter(getActivity(), photos);
		gvPhoto.setAdapter(adapter);
	}

	
	
}
