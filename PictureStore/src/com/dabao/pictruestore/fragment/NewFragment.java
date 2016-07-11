package com.dabao.pictruestore.fragment;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.dabao.pictruestore.R;
import com.dabao.pictruestore.activity.DisplayPictureActivity;
import com.dabao.pictruestore.activity.NewWebViewActivity;
import com.dabao.pictruestore.adapter.DetailAdapter;
import com.dabao.pictruestore.entity.New;
import com.dabao.pictruestore.presenter.IDetailPresenter;
import com.dabao.pictruestore.presenter.impl.DetailPresenterImpl;
import com.dabao.pictruestore.view.IDetailView;

/**
 *Created by dabao 2016年6月26日
 */
public class NewFragment extends Fragment implements IDetailView,OnItemClickListener{
	@ViewInject(R.id.lv_news)
	private ListView lvNews;
	private List<New>news;
	private  IDetailPresenter presenter;
	private DetailAdapter adapter;
	
	public NewFragment() {
		presenter= new DetailPresenterImpl(this);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_new, null);
		x.view().inject(this,view);
		presenter.loadDetail();
		//设置listview动画效果
		setlistViewAnimation();
		
		lvNews.setOnItemClickListener(this);
		
		return view;
	}
	private void setlistViewAnimation() {

		LayoutAnimationController c = new LayoutAnimationController(
                AnimationUtils.loadAnimation(getActivity(), R.anim.anim_item_blog));
        c.setDelay(0.3f);
        c.setOrder(LayoutAnimationController.ORDER_NORMAL);
        lvNews.setLayoutAnimation(c);
	}
	@Override
	public void showDetail(List<New> news) {
		this.news = news;
		adapter = new DetailAdapter(getActivity(), news);
		lvNews.setAdapter(adapter);
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String url = news.get(position).getArticle_url();
		Intent intent = new Intent(getActivity(), NewWebViewActivity.class);
		intent.putExtra("url", url);
		startActivity(intent);
		
	}
}
