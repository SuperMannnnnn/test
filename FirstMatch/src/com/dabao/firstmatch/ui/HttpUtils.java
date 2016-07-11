package com.dabao.firstmatch.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * ͨ�������ַ���һ���ַ���
 * @author tarena
 *
 */

public class HttpUtils {

	//�����ַ
	public static final String API="http://gank.io/api/data/Android/10/1";
	




	public static String getResponse(){
		String result=null;
		InputStream is=null;
		BufferedReader reader=null;
		StringBuffer sb=null;


		try {
			

			URL url=new URL(API);

			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			//���������
			is=conn.getInputStream();
			reader=new BufferedReader(new InputStreamReader(is));
			sb=new StringBuffer();
			String line=null;
			while((line=reader.readLine())!=null){
				sb.append(line);
			}

			if(sb!=null){
				result=sb.toString();
				
			}


		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(is!=null){
					is.close();
				}if(reader!=null){
					reader.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}



		}
		return result;
	}




}