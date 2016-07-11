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
	// TextView����ʾ��ǰ���ŵ���ʱ��
	private TextView tvMusicCurrentPosition;
	// TextView����ʾ��ǰ���ڲ��ŵĸ�����ʱ��
	private TextView tvMusicDuration;
	// SeekBar���������ŵĽ���
	private SeekBar sbProgress;
	// ���½��ȵ��߳�
	private UpdateProgressThread updateProgressThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		player = new MediaPlayer();
		// ����
		setMusicData();
		// ��ʼ���ؼ�
		init();
		// ���ü�����
		setAdapter();
		// �����¼�����
		setListeners();
	}

	private class UpdateProgressThread extends Thread {
		// �������ŵ���λ��
				private int currentPosition;
				// �������ŵ��İٷֱ�
				private int percent;
				// �̵߳�ѭ������
				private boolean isRunning;
				//Ϊ�������whileѭ�� �ܱ����� ������Ĳ������ɱ���
				public void setRunning(boolean isRunning) {
					this.isRunning = isRunning;
				}
		@Override
		public void run() {
			//��new�Ĳ��� ����ѭ������  ������ui�ϵĿؼ�
			Runnable runner =new Runnable() {
				public void run() {
					//���µ�ǰ���ŵ�ʱ��λ��
					tvMusicCurrentPosition.setText(getFormattedTime(currentPosition));
					//���½������İٷֱ�
					sbProgress.setProgress(percent);
				}
			};
			//ѭ�������̵߳�˯�� �� �����µ��߳�
			while(isRunning){
				//��ȡ��ǰ���ŵ�λ��
				currentPosition= player.getCurrentPosition();
				//��ȡ�貥�Ÿ�������ʱ��
				int duration = player.getDuration();
				
				
				//��ȡ��ǰ�������İٷֱȵļ���
				percent = duration == 0?0:currentPosition*100/duration;
				//����ui�Ŀؼ��õķ����� ����Ĳ���Runnable���������� ����new�Ĵ���
				runOnUiThread(runner);
				try {
					//�̵߳�˯��
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
		Log.d("tag", "�������");
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
		//�ж��߳��Ƿ�  ��null 
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
		tvTitle.setText("���ڲ��ţ�"+musics.get(currentMusicIndex).getName());
		ibPlayOrPuase.setImageResource(android.R.drawable.ic_media_pause);
		//����ui��������ʱ��
		tvMusicDuration.setText(getFormattedTime(player.getDuration()));
		
		Log.i("tag", "paly�������ʱ��"+tvMusicDuration);
		
		//�ж��Ƿ����߳�  û�оʹ���������
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
	//ʱ��ĸ�ʽ�����ߣ�����new�Ĳ�����������
	private SimpleDateFormat sdf = new SimpleDateFormat("mm:ss",Locale.CHINA);
	private Date date = new Date();
	// ���ݺ���ֵ��ʾΪmm:ss��ʽ���ַ���
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
