package com.dabao.music.v4.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.dabao.music.v4.R;
import com.dabao.music.v4.fragment.HotMusicFragment;
import com.dabao.music.v4.fragment.NewMusicFragment;
import com.dabao.music.v4.model.MusicModel;

public class MainActivity extends FragmentActivity {
	private RadioGroup radioGroup;
	private ViewPager viewPager;
	private RadioButton rbNew;
	private RadioButton rbHot;
	private ImageView ivCMPic;
	private TextView tvCMTitle;
	private ImageView ivPMAlbum;
	private ImageView ivPMBackground;
	private TextView tvPMTitle;
	private TextView tvPMSinger;
	private SeekBar seekBar;
	private TextView tvPMCurrentTime;
	private TextView tvPMTotalTime;
	private TextView tvPMLrc;
	private RelativeLayout rlPlayMusic;
	private List<Fragment> fragments;
	private PagerAdapter pagerAdapter;
	private ServiceConnection conn;
	//protected MusicBinder musicBinder;
	private UpdateMusicInfoReceiver receiver;
	private SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
	private MusicModel model;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//控件初始化
				setViews();
				//给viewPager设置适配器
				setViewPagerAdapter();
				//实现tab标签与viewpager的联动
				setListeners();
	}


	private void setListeners() {
		
	}

	/**
	 * 给viewPager设置适配器
	 */
	private void setViewPagerAdapter() {
		//构建Fragment数据源
		fragments = new ArrayList<Fragment>();
		fragments.add(new NewMusicFragment());
		fragments.add(new HotMusicFragment());
		pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(pagerAdapter);
		
	}


	private void setViews() {
		tvPMLrc = (TextView) findViewById(R.id.tvPMLrc);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		rbNew = (RadioButton) findViewById(R.id.radioNew);
		rbHot = (RadioButton) findViewById(R.id.radioHot);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		ivCMPic = (ImageView) findViewById(R.id.ivCMPic);
		tvCMTitle = (TextView) findViewById(R.id.tvCMTitle);
		rlPlayMusic = (RelativeLayout) findViewById(R.id.rlPlayMusic);
		ivPMAlbum = (ImageView) findViewById(R.id.ivPMAlbum);
		ivPMBackground = (ImageView) findViewById(R.id.ivPMBackground);
		tvPMTitle = (TextView) findViewById(R.id.tvPMTitle);
		tvPMSinger = (TextView) findViewById(R.id.tvPMSinger);
		seekBar = (SeekBar) findViewById(R.id.seekBar);
		tvPMCurrentTime = (TextView) findViewById(R.id.tvPMCurrentTime);
		tvPMTotalTime = (TextView) findViewById(R.id.tvPMTotalTime);
	}

	class UpdateMusicInfoReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			
		}
		
	}

	/**
	 * 编写viewPager的Adapter
	 */
	class MyPagerAdapter extends FragmentPagerAdapter{

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return fragments.get(position);
		}

		@Override
		public int getCount() {
			return fragments.size();
		}
		
	}

}
