package com.dabao.v4.service;

import java.io.IOException;

import com.dabao.v4.util.GlobalConsts;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.IBinder;
/**
 * �������ֵ�service
 */
public class PlayMusicService extends Service{
	private MediaPlayer player;
	private boolean isLoop=true;

	@Override
	public void onCreate() {
		//��ʼ��������
		player = new MediaPlayer();
		player.setOnPreparedListener(new OnPreparedListener() {
			//prepare��ɺ�  ִ�и÷���
			public void onPrepared(MediaPlayer mp) {
				mp.start();
				//�����Զ���㲥  ����Activity �����Ѿ���ʼ����
				Intent i = new Intent(GlobalConsts.ACTION_START_PLAY);
				sendBroadcast(i);
			}
		});
		//���������߳�  ÿ1���Activity��һ�ι㲥
		new WorkThread().start();
	}
	
	//ÿ1���Activity��һ�ι㲥
	class WorkThread extends Thread{
		@Override
		public void run() {
			while(isLoop){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//���͹㲥
				if(player.isPlaying()){
					Intent i = new Intent(GlobalConsts.ACTION_UPDATE_PROGRESS);
					i.putExtra("current", player.getCurrentPosition());
					i.putExtra("total", player.getDuration());
					sendBroadcast(i);
				}
			}
		}
	}
	
	/**
	 * ���пͻ��˰󶨸�serviceʱ  ִ�� 
	 * context.bindService()
	 */
	public IBinder onBind(Intent intent) {
		return new MusicBinder();
	}

	/**
	 * ���ظ��ͻ��˵�binder����
	 * �������Ÿ��ͻ��˵��õĽӿڷ���
	 */
	public class MusicBinder extends Binder{
		//��ͣ�򲥷�
		public void playOrPause(){
			if(player.isPlaying()){
				player.pause();
			}else{
				player.start();
			}
		}
		
		/**
		 * ��������
		 * @param url  ���ֵ�·��
		 */
		public void playMusic(String url){
			try {
				player.reset();
				player.setDataSource(url);
				//�첽����������Ϣ
				player.prepareAsync();
				//��player׼����ɺ�  ִ��start����
				//��player���ü���
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}


