package com.dabao.music;

public class Music {

	/**
	 * 歌曲名称，用于显示
	 */
	private String name;
	/**
	 * 歌曲路径
	 */
	private String path;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
//	public Music(String name, String path) {
//		super();
//		this.name = name;
//		this.path = path;
//	}

	public Music(){
		super();
	}
	
}
