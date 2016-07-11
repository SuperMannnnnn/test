package com.dabao.v4.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.dabao.v4.R;
import com.dabao.v4.app.MusicApplication;
import com.dabao.v4.dal.MusicModel;
import com.dabao.v4.dal.MusicModel.LrcCallback;
import com.dabao.v4.dal.MusicModel.SongInfoCallback;
import com.dabao.v4.entiy.LrcLine;
import com.dabao.v4.entiy.Music;
import com.dabao.v4.entiy.SongInfo;
import com.dabao.v4.entiy.SongUrl;
import com.dabao.v4.fragments.HotMusicFragment;
import com.dabao.v4.fragments.NewMusicFragment;
import com.dabao.v4.service.PlayMusicService;
import com.dabao.v4.service.PlayMusicService.MusicBinder;
import com.dabao.v4.util.BitMapUtils;
import com.dabao.v4.util.BitMapUtils.BitmapCallback;
import com.dabao.v4.util.GlobalConsts;

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
	protected MusicBinder musicBinder;
	private UpdateMusicInfoReceiver receiver;
	private SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
	private MusicModel model;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		model = new MusicModel();
		//控件初始化
		setViews();
		//给viewPager设置适配器
		setViewPagerAdapter();
		//实现tab标签与viewpager的联动
		setListeners();
		//绑定Service
		bindMusicService();
		//注册组件
		registComponent();
	}
	
	/**
	 * 注册各种组件
	 */
	private void registComponent() {
		//注册广播接收器
		receiver = new UpdateMusicInfoReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(GlobalConsts.ACTION_START_PLAY);
		filter.addAction(GlobalConsts.ACTION_UPDATE_PROGRESS);
		this.registerReceiver(receiver, filter);
	}

	/**
	 * 绑定service
	 */
	private void bindMusicService() {
		Intent intent = new Intent(this, PlayMusicService.class);
		conn = new ServiceConnection() {
			//异常断开时 执行
			public void onServiceDisconnected(ComponentName name) {
			}
			//当与service绑定成功后 执行
			public void onServiceConnected(ComponentName name, IBinder service) {
				musicBinder = (MusicBinder) service;
				//绑定成功后  把musicBinder 给Fragment
				NewMusicFragment f = (NewMusicFragment) fragments.get(0);
				f.setMusicBinder(musicBinder);
			}
		};
		this.bindService(intent, conn, Service.BIND_AUTO_CREATE);
	}

	@Override
	protected void onDestroy() {
		//解除与Service的绑定
		this.unbindService(conn);
		this.unregisterReceiver(receiver);
		super.onDestroy();
	}
	
	

	/**
	 * 监听
	 */
	private void setListeners() {
		//给rlPM
		rlPlayMusic.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		
		});
		//滑动viewpager 控制 导航栏
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					Log.i("info","滚到了第1页..");
					rbNew.setChecked(true);
					break;
				case 1:
					Log.i("info","滚到了第2页..");
					rbHot.setChecked(true);
					break;
				}
			}
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		
		//点击导航 控制viewpager
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.radioNew:
					Log.i("info", "选择了radioNew..");
					viewPager.setCurrentItem(0);
					break;
				case R.id.radioHot:
					Log.i("info", "选择了radioHot..");
					viewPager.setCurrentItem(1);
					break;
				}
			}
		});
	}
	
	/**
	 * 监听
	 */
	public void doClick(View view){
		switch (view.getId()) {
		case R.id.ivPMPre:  //上一曲
			MusicApplication app=(MusicApplication) getApplication();
			app.preMusic();
			final Music m = app.getCurrentMusic();
			//加载m的基本信息
			model.getSongInfoBySongId(m.getSong_id(), new SongInfoCallback() {
				public void onSongInfoLoaded(List<SongUrl> urls, SongInfo info) {
					m.setUrls(urls);
					m.setSongInfo(info);
					musicBinder.playMusic(m.getUrls().get(0).getShow_link());
				}
			});
			break;
		case R.id.ivPMPlay:  //暂停或播放
			musicBinder.playOrPause();
			break;
		case R.id.ivPMNext:
			app=(MusicApplication) getApplication();
			app.nextMusic();
			final Music m2 = app.getCurrentMusic();
			//先加载音乐的基本信息 
			model.getSongInfoBySongId(m2.getSong_id(), new SongInfoCallback() {
				public void onSongInfoLoaded(List<SongUrl> url, SongInfo info) {
					m2.setUrls(url);
					m2.setSongInfo(info);
					musicBinder.playMusic(m2.getUrls().get(0).getShow_link());
				}
			});
			break;
		case R.id.ivCMPic: //显示播放界面
			showRlPlayMusic();
			break;
		}
	}
	
	/**
	 * 当用户点击了返回按钮时执行
	 */
	@Override
	public void onBackPressed() {
		if(rlPlayMusic.getVisibility() == View.VISIBLE){
			hideRlPlayMusic();
		}else{
			super.onBackPressed();
		}
	}
	
	/**
	 * 显示播放界面
	 */
	private void showRlPlayMusic() {
		rlPlayMusic.setVisibility(View.VISIBLE);
		TranslateAnimation anim = new TranslateAnimation(0, 0, rlPlayMusic.getHeight(), 0);
		anim.setDuration(300);
		rlPlayMusic.startAnimation(anim);
	}

	/**
	 * 隐藏播放界面
	 */
	private void hideRlPlayMusic(){
		rlPlayMusic.setVisibility(View.INVISIBLE);
		TranslateAnimation anim = new TranslateAnimation(0, 0, 0, rlPlayMusic.getHeight());
		anim.setDuration(300);
		rlPlayMusic.startAnimation(anim);
	}
	
	/**
	 * 给viewPager设置适配器
	 */
	private void setViewPagerAdapter() {
		//构建Fragment数据源
		fragments = new ArrayList<Fragment>();
		//向fragments集合中添加Fragment
		fragments.add(new NewMusicFragment());
		fragments.add(new HotMusicFragment());
		pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(pagerAdapter);
	}

	/**
	 * 控件初始化
	 */
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
	
	/**
	 * 接受用于更新音乐进度的广播接收器
	 */
	class UpdateMusicInfoReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			String action=intent.getAction();
			//音乐已经开始播放
			if(action.equals(GlobalConsts.ACTION_UPDATE_PROGRESS)){
				//更新音乐的播放进度  seekbar textView
				int current=intent.getIntExtra("current", 0);
				int total=intent.getIntExtra("total", 0);
				seekBar.setMax(total);
				seekBar.setProgress(current);
				//更新两个textView
				tvPMCurrentTime.setText(sdf.format(new Date(current)));
				tvPMTotalTime.setText(sdf.format(new Date(total)));
				//更新歌词信息
				MusicApplication app = (MusicApplication) getApplication();
				List<LrcLine> lines = app.getLrc();
				if(lines == null){ //歌词还没有下载成功
					return;
				}
				//获取当前时间需要显示的歌词内容
				for(int i = 0; i<lines.size(); i++){
					LrcLine line = lines.get(i);
					if(line.equalsTime(current)){
						String content = line.getContent();
						//设置TextView
						tvPMLrc.setText(content);
						return ;
					}
				}
			}else if(action.equals(GlobalConsts.ACTION_START_PLAY)){
				//获取到当前正在播放的music对象
				MusicApplication app = (MusicApplication) getApplication();
				List<Music> list = app.getMusicPlayList();
				int position = app.getPosition();
				Music m = list.get(position);
				//更新tvPMTitle   
				tvPMTitle.setText(m.getTitle());
				//更新tvPMSinger
				tvPMSinger.setText(m.getArtist_name());
				//更新ivPMAlbum    
				if(m.getSongInfo()==null){
					return;
				}
				String albumPath = m.getSongInfo().getAlbum_500_500();
				Log.i("info", "albumPath:"+albumPath);
				BitMapUtils.loadBitmap(context, albumPath, 0, 0 , new BitmapCallback() {
					public void onBitmapLoaded(Bitmap bitmap) {
						if(bitmap != null){
							ivPMAlbum.setImageBitmap(bitmap);
						}else{
							ivPMAlbum.setImageResource(R.drawable.fuck);
						}
					}
				});
				//更新ivPMBackground
				String backgroundPath = m.getSongInfo().getArtist_480_800();
				if("".equals(backgroundPath)){ //
					backgroundPath = albumPath;
				}
				if("".equals(backgroundPath)){
					backgroundPath = m.getPic_big();
				}
				Log.i("info", "backgroundPath:"+backgroundPath);
				BitMapUtils.loadBitmap(context, backgroundPath, 100, 100, new BitmapCallback() {
					public void onBitmapLoaded(Bitmap bitmap) {
						if(bitmap != null ){
							//图片已经加载成功   需要模糊化处理
							BitMapUtils.loadBluredBitmap(bitmap, 10, new BitmapCallback() {
								public void onBitmapLoaded(Bitmap bitmap) {
									//图片已经模糊化处理完成
									ivPMBackground.setImageBitmap(bitmap);
								}
							});
						}else{
							ivPMBackground.setImageResource(R.drawable.fuck);
						}
					}
				});
				//更新CircleImageView   TextView
				String picPath = m.getPic_small();
				String title = m.getTitle();
				tvCMTitle.setText(title);
				BitMapUtils.loadBitmap(MainActivity.this, picPath, 50, 50, new BitmapCallback() {
					public void onBitmapLoaded(Bitmap bitmap) {
						if(bitmap != null){
							ivCMPic.setImageBitmap(bitmap);
							//启动旋转动画
							RotateAnimation anim = new RotateAnimation(0, 360, ivCMPic.getWidth()/2, ivCMPic.getHeight()/2);
							anim.setDuration(20000);
							//匀速旋转
							anim.setInterpolator(new LinearInterpolator());
							anim.setRepeatCount(Animation.INFINITE); //一直转
							ivCMPic.startAnimation(anim);
						}else{
							ivCMPic.setImageResource(R.drawable.ic_launcher);
						}
					}
				});
				//更新歌词  
				//1> 下载歌词
				//2> 解析歌词
				//3>根据当前时间显示相应歌词
				model.downloadLrc(m.getLrclink(), new LrcCallback() {
					public void onLrcLoaded(List<LrcLine> lines) {
						//把歌词集合保存起来   存入application
						//Log.i("info", ""+lines);
						MusicApplication app=(MusicApplication) getApplication();
						app.setLrc(lines);
					}
				});
			}
		}
		
	}
	

}
