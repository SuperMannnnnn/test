package com.dabao.firstmatch.view;

import java.util.List;

import com.dabao.firstmatch.entity.Result;

/**
 *Created by dabao 2016��7��6��
 */
public interface IGithubView {

	/**
	 * 
	 * ��������
	 * @return 
	 */
	
	void loadText(List<Result>results);
}