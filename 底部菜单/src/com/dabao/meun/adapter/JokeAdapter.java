package com.dabao.meun.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dabao.meun.R;
import com.dabao.meun.entity.Joke;

public class JokeAdapter extends BaseAdapter{
	
	private Context context;
	private List<Joke> jokes;
	private LayoutInflater inflater;
	
	
	
	public JokeAdapter(Context context, List<Joke> jokes,int resource, int textViewResourceId) {
		super();
		this.context = context;
		this.jokes = jokes;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return jokes.size();
	}

	@Override
	public Joke getItem(int position) {
		return jokes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.joke_textview, null);
			holder = new ViewHolder();
			holder.tvTitle = (TextView) convertView.findViewById(R.id.joke_tvtitle);
			holder.tvText = (TextView) convertView.findViewById(R.id.joke_tvtext);
			convertView.setTag(holder);
		}
			holder = (ViewHolder) convertView.getTag();
			Joke joke = getItem(position);
			holder.tvTitle.setText(joke.getTitle());
			holder.tvText.setText(joke.getText());
			
		return convertView;
	}

	class ViewHolder{
		TextView tvTitle;
		TextView tvText;
	}
	
}
