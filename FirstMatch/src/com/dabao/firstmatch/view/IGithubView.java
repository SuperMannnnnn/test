package com.dabao.firstmatch.view;

import java.util.List;

import com.dabao.firstmatch.entity.Result;

/**
 *Created by dabao 2016年7月6日
 */
public interface IGithubView {

	/**
	 * 
	 * 加载数据
	 * @return 
	 */
	
	void loadText(List<Result>results);
}
