package com.dabao.pictruestore.fragment;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.dabao.pictruestore.R;
import com.dabao.pictruestore.activity.ShowPictureActivity;
import com.dabao.pictruestore.adapter.GalleryAdapter;
import com.dabao.pictruestore.entity.Gallery;
import com.dabao.pictruestore.presenter.IGalleryPresenter;
import com.dabao.pictruestore.presenter.impl.GalleryPresenterImpl;
import com.dabao.pictruestore.view.IGalleryView;



/**
 *Created by dabao 2016Äê6ÔÂ26ÈÕ
 */
public class GirlFragment extends Fragment implements  IGalleryView,OnItemClickListener{
	@ViewInject(R.id.gv_picture)
	private GridView gvPicture;
	private List<Gallery>gallerys;
	private GalleryAdapter galleryAdapter;
	private IGalleryPresenter presenter;
	
	public GirlFragment() {
		presenter = new GalleryPresenterImpl(this);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_gril, null);
		x.view().inject(this,view);
		presenter.loadGallerys();
		setListener();
		return view;
	}
	
	

	private void setListener() {
		gvPicture.setOnItemClickListener(this);
	}


	@Override
	public void showGalleryList(List<Gallery> gallerys) {
		this.gallerys = gallerys;
//		Log.i("GirlFragment----", "!!!!!"+this.gallerys);
		galleryAdapter = new GalleryAdapter(getActivity(), gallerys);
		gvPicture.setAdapter(galleryAdapter);
		
		
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
	Gallery gallery=gallerys.get(position);
	int pictureId=gallery.getId();
	Intent intent=new Intent(getActivity(), ShowPictureActivity.class);
	intent.putExtra("picId", pictureId);
	startActivity(intent);
	
		
	}























}
