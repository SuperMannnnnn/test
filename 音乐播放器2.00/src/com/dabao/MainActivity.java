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

	
	
	
//	3. 准备ListView的数据
private List<Music> getData() {
	
//	     >> 编写方法，读取sdcard下Music中所有的歌曲数据，并添加到List<Music>集合中
	List<Music> musics = new ArrayList<Music>();
//	        1) 检查sdcard的状态
	if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
//	        2) 获取Music文件夹的File对象
		File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
//	        3) 检查Music文件夹是否存在
		if(dir.exists()){
//	        4) 获取Music文件夹下所有文件的列表(数组)
			File[] files = dir.listFiles();
//	        5) 判断数组是否有效
			if(files!=null){
//	        6) 遍历数组
				for (int i = 0; i < files.length; i++) {
//	        6.1) 判断是否是文件
					if(files[i].isFile()){
//	        6.2) 判断是否是.mp3文件
						if(files[i].getName().toLowerCase(Locale.CHINA).endsWith(".mp3")){
//	        6.3) 创建新的Music对象，并确定name、path属性
							Music music = new Music();
							//	        6.4) 将Music对象添加到List<Music>集合中
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
