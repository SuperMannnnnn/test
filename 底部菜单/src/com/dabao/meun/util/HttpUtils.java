package com.dabao.meun.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ��װ��������
 */
public class HttpUtils {
	public static InputStream get(String path) throws IOException{
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("apikey", "d2d18ec4477c47ff00ca75ff15397e21");
		
		InputStream is = conn.getInputStream();
		
		return is;
	}
	
	public static String isToString(InputStream is) throws IOException{
		StringBuilder sb = new StringBuilder();
		String line = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}
	
	/**
	 * ��ȡЦ���������ַ
	 * @param offset  ��ʼ��Ŀ��С��
	 * @param size   �������ֵĸ���
	 * @return
	 */
	public static String getJokeListUrl(int offset,int size){
		String path = "http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_text?page=1&offset="+offset+"&size="+size;
		return path;
	}
}
