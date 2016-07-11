package com.dabao.v4.dal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.os.AsyncTask;

import com.dabao.v4.entiy.LrcLine;
import com.dabao.v4.entiy.Music;
import com.dabao.v4.entiy.SongInfo;
import com.dabao.v4.entiy.SongUrl;
import com.dabao.v4.util.HttpUtils;
import com.dabao.v4.util.JSONParser;
import com.dabao.v4.util.UrlFactory;
import com.dabao.v4.util.XmlParser;

/**
 * ������ص�ҵ����
 */
public class MusicModel{
	/**
	 * ���ظ�� ���ҽ������ 
	 * @param lrcUrl
	 */
	public void downloadLrc(final String lrcUrl, final LrcCallback callback){
		new AsyncTask<String, String, List<LrcLine>>(){
			//�첽����http���� 
			protected List<LrcLine> doInBackground(String... params) {
				//���ظ��
				try {
					InputStream is = HttpUtils.get(lrcUrl);
					//���ж�ȡ������
					BufferedReader reader = new BufferedReader(new InputStreamReader(is));
					String line = null;
					List<LrcLine> lines = new ArrayList<LrcLine>();
					while( (line=reader.readLine()) != null){
						//line ������:   
						//line ������:   [00:04.52]������G.E.M. ������
						//line ������:   [ti:��]
						if("".equals(line)){
							continue;
						}
						String time=line.substring(1, line.indexOf("]"));
						String content=line.substring(line.indexOf("]")+1);
						LrcLine l = new LrcLine(time, content);
						lines.add(l);
					}
					return lines;
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
			//���߳���ִ��   ���ûص�����  ����list
			protected void onPostExecute(java.util.List<LrcLine> result) {
				callback.onLrcLoaded(result);
			}
		}.execute();
	}
	
	/**
	 * �첽��������   ����json��ȡ��  List<SongUrl>  SongInfo
	 * �����߳��е���callback.onSongInfoLoaded()
	 * @param songId
	 * @param callback
	 */
	public void getSongInfoBySongId(final String songId, final SongInfoCallback callback){
		AsyncTask<String, String, Music> task = new AsyncTask<String, String, Music>(){
			//�ڹ����߳��з�������  ����json
			protected Music doInBackground(String... params) {
				//��������
				String path = UrlFactory.getSongInfoUrl(songId);
				try {
					InputStream is = HttpUtils.get(path);
					String json=HttpUtils.isToString(is);
					//Log.i("info", ""+json);
					Music music = JSONParser.parseSongInfo(json);
					return music;
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
				return null;
			}
			//���߳��е���callback�ص�����
			protected void onPostExecute(Music music) {
				if(music!=null){
					callback.onSongInfoLoaded(music.getUrls(), music.getSongInfo());
				}else{
					callback.onSongInfoLoaded(null, null);
				}
			}
		};
		//ִ���첽����
		task.execute();
	}
	
	/**
	 * ��ѯ�¸��� 
	 * @param f
	 * @param offset
	 * @param size
	 */
	public void findNewMusicList(final Callback callback, final int offset, final int size){
		AsyncTask<String, String, List<Music>> task = new AsyncTask<String, String, List<Music>>(){
			//�����߳���ִ��   ���� http���� ����List
			protected List<Music> doInBackground(String... params) {
				String path = UrlFactory.getNewMusicListUrl(offset, size);
				try {
					InputStream is = HttpUtils.get(path);
					List<Music> musics=XmlParser.parseMusicList(is);
					return musics;
					//String xml=HttpUtils.isToString(is);
					//Log.i("info", ""+xml);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e){
					e.printStackTrace();
				}
				return null;
			}
			//���߳���ִ��  ����callback�ķ��� ִ�к�������
			protected void onPostExecute(List<Music> musics) {
				//Log.i("info", ""+musics);
				//����UI����
				callback.onMusicListLoaded(musics);
			}
		};
		task.execute(); //ִ���첽����
	}
	
	/**
	 * ����songInfo����Ҫ�Ļص��ӿ�
	 */
	public interface SongInfoCallback{
		/**
		 * �����ֵĻ�����Ϣ������Ϻ�  
		 * ���������߳����Զ�ִ��
		 * @param url
		 * @param info
		 */
		void onSongInfoLoaded(List<SongUrl> url, SongInfo info);
	}
	
	public interface Callback {
		/**
		 * ���б������Ϻ� ������ø÷��� 
		 * �ڸ÷�����ʵ������Ҫִ���б������Ϻ��ҵ���߼�
		 * @param musics
		 */
		void onMusicListLoaded(List<Music> musics);
	}
	
	/**
	 * �����صĻص��ӿ�
	 */
	public interface LrcCallback{
		/**
		 * ���������ɺ� �ص��÷���
		 * @param lines
		 */
		void onLrcLoaded(List<LrcLine> lines);
	}
	
}