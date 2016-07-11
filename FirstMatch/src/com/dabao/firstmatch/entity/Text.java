package com.dabao.firstmatch.entity;

import java.util.List;

/**
 * Created by dabao 2016Äê7ÔÂ6ÈÕ
 */
public class Text {
	private boolean error;
	private List<Result> results;
	public Text() {
		super();
	}
	public Text(boolean error, List<Result> results) {
		super();
		this.error = error;
		this.results = results;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public List<Result> getResults() {
		return results;
	}
	public void setResults(List<Result> results) {
		this.results = results;
	}
	
}
