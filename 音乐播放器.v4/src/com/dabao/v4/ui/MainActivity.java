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
		//�ؼ���ʼ��
		setViews();
		//��viewPager����������
		setViewPagerAdapter();
		//ʵ��tab��ǩ��viewpager������
		setListeners();
		//��Service
		bindMusicService();
		//ע�����
		registComponent();
	}
	
	/**
	 * ע��������
	 */
	private void registComponent() {
		//ע��㲥������
		receiver = new UpdateMusicInfoReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(GlobalConsts.ACTION_START_PLAY);
		filter.addAction(GlobalConsts.ACTION_UPDATE_PROGRESS);
		this.registerReceiver(receiver, filter);
	}

	/**
	 * ��service
	 */
	private void bindMusicService() {
		Intent intent = new Intent(this, PlayMusicService.class);
		conn = new ServiceConnection() {
			//�쳣�Ͽ�ʱ ִ��
			public void onServiceDisconnected(ComponentName name) {
			}
			//����service�󶨳ɹ��� ִ��
			public void onServiceConnected(ComponentName name, IBinder service) {
				musicBinder = (MusicBinder) service;
				//�󶨳ɹ���  ��musicBinder ��Fragment
				NewMusicFragment f = (NewMusicFragment) fragments.get(0);
				f.setMusicBinder(musicBinder);
			}
		};
		this.bindService(intent, conn, Service.BIND_AUTO_CREATE);
	}

	@Override
	protected void onDestroy() {
		//�����Service�İ�
		this.unbindService(conn);
		this.unregisterReceiver(receiver);
		super.onDestroy();
	}
	
	

	/**
	 * ����
	 */
	private void setListeners() {
		//��rlPM
		rlPlayMusic.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		
		});
		//����viewpager ���� ������
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					Log.i("info","�����˵�1ҳ..");
					rbNew.setChecked(true);
					break;
				case 1:
					Log.i("info","�����˵�2ҳ..");
					rbHot.setChecked(true);
					break;
				}
			}
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		
		//������� ����viewpager
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.radioNew:
					Log.i("info", "ѡ����radioNew..");
					viewPager.setCurrentItem(0);
					break;
				case R.id.radioHot:
					Log.i("info", "ѡ����radioHot..");
					viewPager.setCurrentItem(1);
					break;
				}
			}
		});
	}
	
	/**
	 * ����
	 */
	public void doClick(View view){
		switch (view.getId()) {
		case R.id.ivPMPre:  //��һ��
			MusicApplication app=(MusicApplication) getApplication();
			app.preMusic();
			final Music m = app.getCurrentMusic();
			//����m�Ļ�����Ϣ
			model.getSongInfoBySongId(m.getSong_id(), new SongInfoCallback() {
				public void onSongInfoLoaded(List<SongUrl> urls, SongInfo info) {
					m.setUrls(urls);
					m.setSongInfo(info);
					musicBinder.playMusic(m.getUrls().get(0).getShow_link());
				}
			});
			break;
		case R.id.ivPMPlay:  //��ͣ�򲥷�
			musicBinder.playOrPause();
			break;
		case R.id.ivPMNext:
			app=(MusicApplication) getApplication();
			app.nextMusic();
			final Music m2 = app.getCurrentMusic();
			//�ȼ������ֵĻ�����Ϣ 
			model.getSongInfoBySongId(m2.getSong_id(), new SongInfoCallback() {
				public void onSongInfoLoaded(List<SongUrl> url, SongInfo info) {
					m2.setUrls(url);
					m2.setSongInfo(info);
					musicBinder.playMusic(m2.getUrls().get(0).getShow_link());
				}
			});
			break;
		case R.id.ivCMPic: //��ʾ���Ž���
			showRlPlayMusic();
			break;
		}
	}
	
	/**
	 * ���û�����˷��ذ�ťʱִ��
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
	 * ��ʾ���Ž���
	 */
	private void showRlPlayMusic() {
		rlPlayMusic.setVisibility(View.VISIBLE);
		TranslateAnimation anim = new TranslateAnimation(0, 0, rlPlayMusic.getHeight(), 0);
		anim.setDuration(300);
		rlPlayMusic.startAnimation(anim);
	}

	/**
	 * ���ز��Ž���
	 */
	private void hideRlPlayMusic(){
		rlPlayMusic.setVisibility(View.INVISIBLE);
		TranslateAnimation anim = new TranslateAnimation(0, 0, 0, rlPlayMusic.getHeight());
		anim.setDuration(300);
		rlPlayMusic.startAnimation(anim);
	}
	
	/**
	 * ��viewPager����������
	 */
	private void setViewPagerAdapter() {
		//����Fragment����Դ
		fragments = new ArrayList<Fragment>();
		//��fragments���������Fragment
		fragments.add(new NewMusicFragment());
		fragments.add(new HotMusicFragment());
		pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(pagerAdapter);
	}

	/**
	 * �ؼ���ʼ��
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
	 * ��дviewPager��Adapter
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
	 * �������ڸ������ֽ��ȵĹ㲥������
	 */
	class UpdateMusicInfoReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			String action=intent.getAction();
			//�����Ѿ���ʼ����
			if(action.equals(GlobalConsts.ACTION_UPDATE_PROGRESS)){
				//�������ֵĲ��Ž���  seekbar textView
				int current=intent.getIntExtra("current", 0);
				int total=intent.getIntExtra("total", 0);
				seekBar.setMax(total);
				seekBar.setProgress(current);
				//��������textView
				tvPMCurrentTime.setText(sdf.format(new Date(current)));
				tvPMTotalTime.setText(sdf.format(new Date(total)));
				//���¸����Ϣ
				MusicApplication app = (MusicApplication) getApplication();
				List<LrcLine> lines = app.getLrc();
				if(lines == null){ //��ʻ�û�����سɹ�
					return;
				}
				//��ȡ��ǰʱ����Ҫ��ʾ�ĸ������
				for(int i = 0; i<lines.size(); i++){
					LrcLine line = lines.get(i);
					if(line.equalsTime(current)){
						String content = line.getContent();
						//����TextView
						tvPMLrc.setText(content);
						return ;
					}
				}
			}else if(action.equals(GlobalConsts.ACTION_START_PLAY)){
				//��ȡ����ǰ���ڲ��ŵ�music����
				MusicApplication app = (MusicApplication) getApplication();
				List<Music> list = app.getMusicPlayList();
				int position = app.getPosition();
				Music m = list.get(position);
				//����tvPMTitle   
				tvPMTitle.setText(m.getTitle());
				//����tvPMSinger
				tvPMSinger.setText(m.getArtist_name());
				//����ivPMAlbum    
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
				//����ivPMBackground
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
							//ͼƬ�Ѿ����سɹ�   ��Ҫģ��������
							BitMapUtils.loadBluredBitmap(bitmap, 10, new BitmapCallback() {
								public void onBitmapLoaded(Bitmap bitmap) {
									//ͼƬ�Ѿ�ģ�����������
									ivPMBackground.setImageBitmap(bitmap);
								}
							});
						}else{
							ivPMBackground.setImageResource(R.drawable.fuck);
						}
					}
				});
				//����CircleImageView   TextView
				String picPath = m.getPic_small();
				String title = m.getTitle();
				tvCMTitle.setText(title);
				BitMapUtils.loadBitmap(MainActivity.this, picPath, 50, 50, new BitmapCallback() {
					public void onBitmapLoaded(Bitmap bitmap) {
						if(bitmap != null){
							ivCMPic.setImageBitmap(bitmap);
							//������ת����
							RotateAnimation anim = new RotateAnimation(0, 360, ivCMPic.getWidth()/2, ivCMPic.getHeight()/2);
							anim.setDuration(20000);
							//������ת
							anim.setInterpolator(new LinearInterpolator());
							anim.setRepeatCount(Animation.INFINITE); //һֱת
							ivCMPic.startAnimation(anim);
						}else{
							ivCMPic.setImageResource(R.drawable.ic_launcher);
						}
					}
				});
				//���¸��  
				//1> ���ظ��
				//2> �������
				//3>���ݵ�ǰʱ����ʾ��Ӧ���
				model.downloadLrc(m.getLrclink(), new LrcCallback() {
					public void onLrcLoaded(List<LrcLine> lines) {
						//�Ѹ�ʼ��ϱ�������   ����application
						//Log.i("info", ""+lines);
						MusicApplication app=(MusicApplication) getApplication();
						app.setLrc(lines);
					}
				});
			}
		}
		
	}
	

}
