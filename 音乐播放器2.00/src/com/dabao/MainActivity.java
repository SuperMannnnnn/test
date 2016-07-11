package com.dabao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private ListView lvMusics;
	private MusicAdapter musicAdapter;
	private List<Music> musics;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		lvMusics =(ListView) findViewById(R.id.lv_musics);
		
		musics = getData();
		
		musicAdapter =new MusicAdapter(this, musics);
		
		lvMusics.setAdapter(musicAdapter);
		
	}

	
	
	
//	3. ׼��ListView������
private List<Music> getData() {
	
//	     >> ��д��������ȡsdcard��Music�����еĸ������ݣ�����ӵ�List<Music>������
	List<Music> musics = new ArrayList<Music>();
//	        1) ���sdcard��״̬
	if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
//	        2) ��ȡMusic�ļ��е�File����
		File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
//	        3) ���Music�ļ����Ƿ����
		if(dir.exists()){
//	        4) ��ȡMusic�ļ����������ļ����б�(����)
			File[] files = dir.listFiles();
//	        5) �ж������Ƿ���Ч
			if(files!=null){
//	        6) ��������
				for (int i = 0; i < files.length; i++) {
//	        6.1) �ж��Ƿ����ļ�
					if(files[i].isFile()){
//	        6.2) �ж��Ƿ���.mp3�ļ�
						if(files[i].getName().toLowerCase(Locale.CHINA).endsWith(".mp3")){
//	        6.3) �����µ�Music���󣬲�ȷ��name��path����
							Music music = new Music();
							//	        6.4) ��Music������ӵ�List<Music>������
							music.setName(files[i].getName().substring(0, files[i].getName().length()-4));
							music.setPath(files[i].getAbsolutePath());
							musics.add(music);
							
							
						}
						
					}
					
					
				}
			}
			
		}
		
	}
	
	
	
		return musics;
	}




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
