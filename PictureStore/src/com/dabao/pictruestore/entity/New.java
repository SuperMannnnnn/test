package com.dabao.pictruestore.entity;
/**
 *Created by dabao 2016年7月10日
 */
public class New {

    private String title;    //新闻标题  
    private String source;    //新闻来源  
    private String article_url;    //新闻的url地址  
    private String publish_time;       //没意义  
    private long behot_time;  //新闻收录时间，以毫秒计数的整数  
    private long create_time;       //没意义  
    private int digg_count;       //赞的次数  
    private int bury_count;     //踩的次数  
    private int repin_count;      //收藏次数  
    private long group_id; //新闻的id，无需关注  
	public New() {
		super();
	}
	public New(String title, String source, String article_url,
			String publish_time, long behot_time, long create_time,
			int digg_count, int bury_count, int repin_count, long group_id) {
		super();
		this.title = title;
		this.source = source;
		this.article_url = article_url;
		this.publish_time = publish_time;
		this.behot_time = behot_time;
		this.create_time = create_time;
		this.digg_count = digg_count;
		this.bury_count = bury_count;
		this.repin_count = repin_count;
		this.group_id = group_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getArticle_url() {
		return article_url;
	}
	public void setArticle_url(String article_url) {
		this.article_url = article_url;
	}
	public String getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(String publish_time) {
		this.publish_time = publish_time;
	}
	public long getBehot_time() {
		return behot_time;
	}
	public void setBehot_time(long behot_time) {
		this.behot_time = behot_time;
	}
	public long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	public int getDigg_count() {
		return digg_count;
	}
	public void setDigg_count(int digg_count) {
		this.digg_count = digg_count;
	}
	public int getBury_count() {
		return bury_count;
	}
	public void setBury_count(int bury_count) {
		this.bury_count = bury_count;
	}
	public int getRepin_count() {
		return repin_count;
	}
	public void setRepin_count(int repin_count) {
		this.repin_count = repin_count;
	}
	public long getGroup_id() {
		return group_id;
	}
	public void setGroup_id(long group_id) {
		this.group_id = group_id;
	}
	@Override
	public String toString() {
		return "News [title=" + title + ", source=" + source + ", article_url="
				+ article_url + ", publish_time=" + publish_time
				+ ", behot_time=" + behot_time + ", create_time=" + create_time
				+ ", digg_count=" + digg_count + ", bury_count=" + bury_count
				+ ", repin_count=" + repin_count + ", group_id=" + group_id
				+ "]";
	}
    
    
    
    
}
