package com.dabao.music;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener,
		OnItemClickListener, OnCompletionListener {
	private ImageButton ibPrevious;
	private ImageButton ibPlayOrPuase;
	private ImageButton ibNext;
	private ListView lvMusics;
	private BaseAdapter musicAdapter;
	private List<Music> musics;
	private MediaPlayer player;
	private int currentMusicIndex;
	private int pausePosition;
	private TextView tvTitle;
	// TextView：显示当前播放到的时间
	private TextView tvMusicCurrentPosition;
	// TextView：显示当前正在播放的歌曲的时长
	private TextView tvMusicDuration;
	// SeekBar：歌曲播放的进度
	private SeekBar sbProgress;
	// 更新进度的线程
	private UpdateProgressThread updateProgressThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		player = new MediaPlayer();
		// 数据
		setMusicData();
		// 初始化控件
		init();
		// 设置监听器
		setAdapter();
		// 设置事件触发
		setListeners();
	}

	private class UpdateProgressThread extends Thread {
		// 歌曲播放到的位置
				private int currentPosition;
				// 歌曲播放到的百分比
				private int percent;
				// 线程的循环条件
				private boolean isRunning;
				//为了下面的while循环 能被控制 把里面的参数换成变量
				public void setRunning(boolean isRunning) {
					this.isRunning = isRunning;
				}
		@Override
		public void run() {
			//把new的部分 放在循环外面  并更新ui上的控件
			Runnable runner =new Runnable() {
				public void run() {
					//更新当前播放的时间位置
					tvMusicCurrentPosition.setText(getFormattedTime(currentPosition));
					//更新进度条的百分比
					sbProgress.setProgress(percent);
				}
			};
			//循环控制线程的睡眠 和 创建新的线程
			while(isRunning){
				//获取当前播放的位置
				currentPosition= player.getCurrentPosition();
				//获取歌播放歌曲的总时长
				int duration = player.getDuration();
				
				
				//获取当前进度条的百分比的计算
				percent = duration == 0?0:currentPosition*100/duration;
				//更新ui的控件用的方法， 里面的参数Runnable被拿在上面 减少new的次数
				runOnUiThread(runner);
				try {
					//线程的睡眠
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_play_or_puase:
			if (player.isPlaying()) {
				pause();
			} else {
				play();
			}
			break;

		case R.id.ib_previous:
			previous();
			break;

		case R.id.ib_next:
			next();
			break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		currentMusicIndex = position;
		pausePosition = 0;
		play();

	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		Log.d("tag", "播放完毕");
		next();
	}

	private void next() {
		currentMusicIndex++;
		if (currentMusicIndex >= musics.size()) {
			currentMusicIndex = 0;
		}
		pausePosition = 0;
		play();
	}

	private void previous() {
		currentMusicIndex--;
		if (currentMusicIndex < 0) {
			currentMusicIndex = musics.size() - 1;
		}
		pausePosition = 0;
		play();

	}

	private void pause() {
		player.pause();
		pausePosition = player.getCurrentPosition();
		ibPlayOrPuase.setImageResource(android.R.drawable.ic_media_play);
		//判断线程是否  是null 
		if(updateProgressThread!=null){
			updateProgressThread.setRunning(false);
			updateProgressThread = null;
		}
	}

	private void play() {

		try {
			player.reset();
			player.setDataSource(musics.get(currentMusicIndex).getPath());
			player.prepare();
			player.seekTo(pausePosition);
			player.start();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		;
		tvTitle.setText("正在播放："+musics.get(currentMusicIndex).getName());
		ibPlayOrPuase.setImageResource(android.R.drawable.ic_media_pause);
		//更新ui歌曲的总时长
		tvMusicDuration.setText(getFormattedTime(player.getDuration()));
		
		Log.i("tag", "paly里面的总时长"+tvMusicDuration);
		
		//判断是否有线程  没有就创建并启动
		if(updateProgressThread ==null){
			updateProgressThread = new UpdateProgressThread();
			updateProgressThread.setRunning(true);
			updateProgressThread.start();
		}
	}

	private void setListeners() {

		ibNext.setOnClickListener(this);
		ibPrevious.setOnClickListener(this);
		ibPlayOrPuase.setOnClickListener(this);
		lvMusics.setOnItemClickListener(this);
		player.setOnCompletionListener(this);

	}

	private void init() {
		tvTitle = (TextView) findViewById(R.id.tv_music_title);
		ibPrevious = (ImageButton) findViewById(R.id.ib_previous);
		ibNext = (ImageButton) findViewById(R.id.ib_next);
		ibPlayOrPuase = (ImageButton) findViewById(R.id.ib_play_or_puase);
		lvMusics = (ListView) findViewById(R.id.lv_musics);
		tvMusicCurrentPosition = (TextView) findViewById(R.id.tv_music_current_position);
		tvMusicDuration = (TextView) findViewById(R.id.tv_music_duration);
		sbProgress = (SeekBar) findViewById(R.id.sb_music_progress);
		
	}

	private void setAdapter() {
		musicAdapter = new MusicAdapter(this, musics);
		lvMusics.setAdapter(musicAdapter);

	}

	private void setMusicData() {

		musics = new ArrayList<Music>();
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			File dir = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			if (dir.exists()) {
				File[] files = dir.listFiles();
				if (files != null) {
					for (int i = 0; i < files.length; i++) {
						if (files[i].isFile()) {
							if (files[i].getName().toLowerCase(Locale.CHINA)
									.endsWith(".mp3")) {
								Music music = new Music();
								music.setName(files[i].getName().substring(0,
										files[i].getName().length() - 4));
								music.setPath(files[i].getAbsolutePath());
								musics.add(music);
							}
						}
					}
				}
			}
		}

	}
	//时间的格式化工具，并把new的部分拿在外面
	private SimpleDateFormat sdf = new SimpleDateFormat("mm:ss",Locale.CHINA);
	private Date date = new Date();
	// 根据毫秒值显示为mm:ss格式的字符串
	private String getFormattedTime(long timeMillis) {
			date.setTime(timeMillis);
			return sdf.format(date);
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
