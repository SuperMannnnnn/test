package com.dabao.listEms;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EmpAdapter extends BaseAdapter{
	private List<Emp> emps ;
	private Context context;
	private LayoutInflater inflater;
	
	
	public EmpAdapter(List<Emp> emps, Context context) {
		super();
		this.emps = emps;
		this.context = context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return emps.size();
	}

	@Override
	public Emp getItem(int position) {
		// TODO Auto-generated method stub
		return emps.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.item_list_date, null);
			holder = new ViewHolder();
			holder.tvId = (TextView) convertView.findViewById(R.id.tv_id);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tvAge = (TextView) convertView.findViewById(R.id.tv_age);
			holder.tvSalary = (TextView) convertView.findViewById(R.id.tv_salary);
			holder.tvGender = (TextView) convertView.findViewById(R.id.tv_gender);
			convertView.setTag(holder);
		}
			holder = (ViewHolder) convertView.getTag();
			Emp emp = getItem(position);
			holder.tvId.setText(emp.getId()+"");
			holder.tvName.setText(emp.getName());
			holder.tvSalary.setText(emp.getSalary()+"");
			holder.tvAge.setText(emp.getAge()+"");
			holder.tvGender.setText("m".equals(emp.getGender())?"ÄÐÊ¿":"Å®Ê¿");
		
		return null;
	}
	class ViewHolder{
		TextView tvId;
		TextView tvName;
		TextView tvSalary;
		TextView tvAge;
		TextView tvGender;
	}

}
