package com.dabao.v4.app;

import java.util.List;




import com.dabao.v4.entiy.LrcLine;
import com.dabao.v4.entiy.Music;

import android.app.Application;

/**
 * ��app����ʱ����
 */
public class MusicApplication extends Application {
	private List<Music> musicPlayList;
	private int position;
	private List<LrcLine> lrc;

	@Override
	public void onCreate() {
		super.onCreate();
	}

	public List<Music> getMusicPlayList() {
		return musicPlayList;
	}

	public List<LrcLine> getLrc() {
		return lrc;
	}

	public void setLrc(List<LrcLine> lrc) {
		this.lrc = lrc;
	}

	public void setMusicPlayList(List<Music> musicPlayList) {
		this.musicPlayList = musicPlayList;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	// ����һ��
	public void preMusic() {
		position = position == 0 ? 0 : position - 1;
	}

	// ����һ��
	public void nextMusic() {
		position = position == musicPlayList.size() - 1 ? 0 : position + 1;
	}

	// ��ȡ��ǰ����
	public Music getCurrentMusic() {
		return musicPlayList.get(position);
	}
}