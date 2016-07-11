package com.dabao.contact.fragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dabao.contact.R;
import com.dabao.contact.adapter.CalllogAdapter;
import com.dabao.contact.entity.Calllog;
import com.dabao.contact.presenter.ICalllogPresenter;
import com.dabao.contact.presenter.impl.CalllogPresenterImpl;
import com.dabao.contact.view.ICalllogView;

public class CalllogFragment extends Fragment implements ICalllogView {
	private List<Calllog> logs;
	private ICalllogPresenter presenter;
	private ListView lvCalllog;
	private CalllogAdapter adapter;

	public CalllogFragment() {
		this.presenter = new CalllogPresenterImpl(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_calllog, null);
		setViews(view);
		// 调用presenter方法，执行加载联系人的流程
		presenter.loadAllCalllogs();

		return view;
	}

	private void setViews(View view) {
		this.lvCalllog = (ListView) view.findViewById(R.id.lvCalllog);
	}

	@Override
	public void setData(List<Calllog> logs) {
		this.logs = logs;

	}

	@Override
	public void showData() {
		// 设置自定义Adapter
		adapter = new CalllogAdapter(getActivity(),logs);
		lvCalllog.setAdapter(adapter);

	}

}
