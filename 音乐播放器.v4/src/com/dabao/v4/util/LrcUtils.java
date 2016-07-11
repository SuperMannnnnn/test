package com.dabao.v4.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.dabao.v4.entiy.LrcLine;

/**
 * 有关歌词的工具类
 */
public class LrcUtils {
	public static List<LrcLine> parseLrc(File targeFile) throws IOException{
		if(!targeFile.exists()){
		return null;
		}
		FileInputStream is = new FileInputStream(targeFile);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = "";
		List<LrcLine> lines = new ArrayList<LrcLine>();
		while((line =reader.readLine())!=null){
			if("".equals(line)){
				continue;
			}
			String time = line.substring(1, line.indexOf("]"));
			String content = line.substring(line.indexOf("]")+1);
			LrcLine l = new LrcLine(line, content);
			lines.add(l);
		}
		return lines;
		
	}

	/**
	 * 解析歌词   并且在解析的过程中把歌词存入文件
	 * @param is
	 * @param targetFile
	 * @return
	 * @throws IOException 
	 */
	public static List<LrcLine> parseLrc(InputStream is, File targetFile) throws IOException{
		//按行读取输入流
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = null;
		List<LrcLine> lines = new ArrayList<LrcLine>();
		//读取的过程当中把文本存入缓存文件
		PrintWriter out = new PrintWriter(targetFile);
		while( (line=reader.readLine()) != null){
			//line 可能是:   
			//line 可能是:   [00:04.52]词曲：G.E.M. 邓紫棋
			//line 可能是:   [ti:画]
			if("".equals(line)){
				continue;
			}
			//把line中的内容 写入输出流
			out.println(line);
			out.flush();
			String time=line.substring(1, line.indexOf("]"));
			String content=line.substring(line.indexOf("]")+1);
			LrcLine l = new LrcLine(time, content);
			lines.add(l);
		}
		out.close();
		return lines;
	}
}
