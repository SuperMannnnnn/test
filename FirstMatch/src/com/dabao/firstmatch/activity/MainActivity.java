package com.dabao.firstmatch.activity;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.dabao.firstmatch.R;
import com.dabao.firstmatch.adapter.MyAdapter;
import com.dabao.firstmatch.entity.Result;
import com.dabao.firstmatch.presenter.impl.ITextPresenter;
import com.dabao.firstmatch.presenter.impl.Impl.TextPresenter;
import com.dabao.firstmatch.view.IGithubView;

public class MainActivity extends Activity implements IGithubView{

	@ViewInject(R.id.lv)
	private ListView lv;
	private MyAdapter adapter;
	private List<Result>results;
	private ITextPresenter presenter;
	
	
	
	
	
	public MainActivity() {
		presenter = new TextPresenter(this);
	}




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		x.view().inject(this);
		presenter.loadText();
	}

	
	
	
	@Override
	public void loadText(List<Result> results) {
		this.results = results;
		Log.i("MainActivity----", "!!!!!"+this.results);
		adapter = new MyAdapter(this, results);
		lv.setAdapter(adapter);
	}

}
