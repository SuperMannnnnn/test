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
 * 音乐相关的业务类
 */
public class MusicModel{
	/**
	 * 下载歌词 并且解析歌词 
	 * @param lrcUrl
	 */
	public void downloadLrc(final String lrcUrl, final LrcCallback callback){
		new AsyncTask<String, String, List<LrcLine>>(){
			//异步发送http请求 
			protected List<LrcLine> doInBackground(String... params) {
				//下载歌词
				try {
					InputStream is = HttpUtils.get(lrcUrl);
					//按行读取输入流
					BufferedReader reader = new BufferedReader(new InputStreamReader(is));
					String line = null;
					List<LrcLine> lines = new ArrayList<LrcLine>();
					while( (line=reader.readLine()) != null){
						//line 可能是:   
						//line 可能是:   [00:04.52]词曲：G.E.M. 邓紫棋
						//line 可能是:   [ti:画]
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
			//主线程中执行   调用回调方法  返回list
			protected void onPostExecute(java.util.List<LrcLine> result) {
				callback.onLrcLoaded(result);
			}
		}.execute();
	}
	
	/**
	 * 异步发送请求   解析json获取：  List<SongUrl>  SongInfo
	 * 在主线程中调用callback.onSongInfoLoaded()
	 * @param songId
	 * @param callback
	 */
	public void getSongInfoBySongId(final String songId, final SongInfoCallback callback){
		AsyncTask<String, String, Music> task = new AsyncTask<String, String, Music>(){
			//在工作线程中发送请求  解析json
			protected Music doInBackground(String... params) {
				//发送请求
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
			//主线程中调用callback回调方法
			protected void onPostExecute(Music music) {
				if(music!=null){
					callback.onSongInfoLoaded(music.getUrls(), music.getSongInfo());
				}else{
					callback.onSongInfoLoaded(null, null);
				}
			}
		};
		//执行异步任务
		task.execute();
	}
	
	/**
	 * 查询新歌榜榜单 
	 * @param f
	 * @param offset
	 * @param size
	 */
	public void findNewMusicList(final Callback callback, final int offset, final int size){
		AsyncTask<String, String, List<Music>> task = new AsyncTask<String, String, List<Music>>(){
			//工作线程中执行   发送 http请求 解析List
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
			//主线程中执行  调用callback的方法 执行后续操作
			protected void onPostExecute(List<Music> musics) {
				//Log.i("info", ""+musics);
				//更新UI界面
				callback.onMusicListLoaded(musics);
			}
		};
		task.execute(); //执行异步任务
	}
	
	/**
	 * 访问songInfo所需要的回调接口
	 */
	public interface SongInfoCallback{
		/**
		 * 当音乐的基本信息加载完毕后  
		 * 将会在主线程中自动执行
		 * @param url
		 * @param info
		 */
		void onSongInfoLoaded(List<SongUrl> url, SongInfo info);
	}
	
	public interface Callback {
		/**
		 * 当列表加载完毕后 将会调用该方法 
		 * 在该方法的实现中需要执行列表加载完毕后的业务逻辑
		 * @param musics
		 */
		void onMusicListLoaded(List<Music> musics);
	}
	
	/**
	 * 歌词相关的回调接口
	 */
	public interface LrcCallback{
		/**
		 * 歌词下载完成后 回调该方法
		 * @param lines
		 */
		void onLrcLoaded(List<LrcLine> lines);
	}
	
}