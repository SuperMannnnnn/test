package com.dabao.v4.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HttpUtils {
	
	/**
	 * 发送get请求 获取服务端返回的输入流
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws IOExceptionException 
	 */
	public static InputStream get(String path) throws IOException{
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		InputStream is = conn.getInputStream();
		return is;
	}
	

	/**
	 * 发送post请求  
	 * @param path  请求资源路径
	 * @param paramMap 参数集合  如果没有参数 则为null
	 * @return  服务端返回的输入流
	 */
	
	public static InputStream post(String path,Map<String, String>paramMap) throws IOException{
		URL url = new URL(path);
		 HttpURLConnection conn =(HttpURLConnection) url.openConnection();
		 conn.setRequestMethod("POST");
		 conn.setRequestProperty("Content-Tapy", "application/x-www-form-urlencoded");
		 //设置参数
		 conn.setDoOutput(true);
		 StringBuilder params = new StringBuilder();
		 if(paramMap != null){
			 Set<String> keys = paramMap.keySet();
			 Iterator<String> ite = keys.iterator();
			 while(ite.hasNext()){
				 String key = ite.next();
				 String value = paramMap.get(keys);
			 }
			 
			 params.deleteCharAt(params.length()-1);
			 OutputStream os = conn.getOutputStream();
			 os.write(params.toString().getBytes("utf-8"));
			 os.flush();
		 }
		 InputStream is = conn.getInputStream();
		return is;
	}
	/**	
	 * 把输入流 按照utf-8编码解析为字符串
	 * @param is
	 * @return 解析成功的字符串
	 */
	public static String isToString(InputStream is) throws IOException{
		StringBuilder sb = new StringBuilder();
		String line = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		while((line = reader.readLine())!=null){
			sb.append(line);
		}
		return sb.toString();	
		
	}
		
	
				
}
