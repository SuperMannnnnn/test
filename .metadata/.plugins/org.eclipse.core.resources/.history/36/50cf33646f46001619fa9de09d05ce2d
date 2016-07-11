package com.dabao.pictruestore.activity;

import java.util.List;

import org.xutils.x;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageSwitcher;

import com.dabao.pictruestore.R;
import com.dabao.pictruestore.adapter.ShowPictureAdapter;
import com.dabao.pictruestore.entity.Picture;
import com.dabao.pictruestore.presenter.IShowPicturePresenter;
import com.dabao.pictruestore.presenter.impl.ShowPicturePresenterImpl;
import com.dabao.pictruestore.view.IShowPictureView;

public class ShowPictureActivity extends Activity implements IShowPictureView,OnItemClickListener{
//	@ViewInject(R.id.gridView1)
	@SuppressWarnings("deprecation")
	private Gallery gvShowPicture;
	private ShowPictureAdapter adapter;
	private List<Picture>pictures;
	private IShowPicturePresenter presenter;
	private int id;
	private ImageSwitcher isPictures;
/**
 *点击获取图库的id
 * @return
 */
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public ShowPictureActivity() {
		presenter = new ShowPicturePresenterImpl(this);
	}


	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_picture);
		 gvShowPicture = (Gallery) findViewById(R.id.gallery);  
		x.view().inject(this);
		Intent intent=getIntent();
		id=intent.getIntExtra("picId", 0);
		Log.i("picID-->>>",""+id);
		presenter.loadGallerys(id);
		
	}
	

	@Override
	public void showGalleryList(List<Picture> Pictures) {
		this.pictures = Pictures;
		Log.i("ShowPictureActivity----", "!!!!!"+this.pictures);
		adapter = new ShowPictureAdapter(this, Pictures);
		gvShowPicture.setAdapter(adapter);
		 
		// 为GridView配置监听器
		gvShowPicture.setOnItemClickListener(this);
//		gvShowPicture.setOnItemSelectedListener(this);
	}

	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_picture, menu);
		return true;
	}

//
//	@Override
//	public void onItemSelected(AdapterView<?> parent, View view, int position,
//			long id) {
//		
//	}
//
//
//	@Override
//	public void onNothingSelected(AdapterView<?> parent) {
//		// TODO Auto-generated method stub
//		
//	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		int Pictureid = pictures.get(position).getId();
		String src = pictures.get(position).getSrc();
		Intent intent = new Intent(this, DisplayPictureActivity.class);
		intent.putExtra("position", position);
		intent.putExtra("Pictureid", Pictureid);
		intent.putExtra("src", src);
		startActivity(intent);
	}

	
	
	 






}
