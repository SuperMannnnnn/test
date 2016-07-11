package com.dabao.contact.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.CallLog.Calls;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dabao.contact.R;
import com.dabao.contact.entity.Calllog;
import com.dabao.contact.util.BitmapUtils;
import com.dabao.contact.util.DateUtils;

public class CalllogAdapter extends BaseAdapter {
	private Context context;
	private List<Calllog> logs;

	public CalllogAdapter(Context context, List<Calllog> logs) {
		this.context = context;
		this.logs = logs;
	}

	@Override
	public int getCount() {
		return logs.size();
	}

	@Override
	public Calllog getItem(int position) {
		return logs.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item_lv_calllog, null);
			holder = new ViewHolder();
			holder.ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
			holder.ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			holder.tvNumber = (TextView) convertView
					.findViewById(R.id.tvNumber);
			holder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
			// ���ÿؼ�������
			Calllog log = getItem(position);
			// ��ʾͷ��
			Bitmap photo = BitmapUtils.getPhoto(context, log.getPhotoId());
			if (photo != null) {
				holder.ivPhoto.setImageBitmap(photo);
			} else {
				holder.ivPhoto.setImageResource(R.drawable.img01g_15);
			}
			// ��������
			holder.tvName.setText(log.getName() == null ? "δ֪����" : log
					.getName());
			// ������������ɫ
			int type = log.getType();
			if (type == Calls.MISSED_TYPE) {
				holder.tvName.setTextColor(Color.RED);
			} else {
				holder.tvName.setTextColor(Color.BLACK);
			}
			// ����ivIcon
			if (type == Calls.OUTGOING_TYPE) {
				holder.ivIcon.setVisibility(View.VISIBLE);
			} else {
				holder.ivIcon.setVisibility(View.GONE);
			}

			// ���õ绰����
			holder.tvNumber.setText(log.getNumber());
			// ����ʱ��
			long time = log.getDate();
			String date = DateUtils.parse(time);
			holder.tvDate.setText(date);
		}
		return null;
	}

	class ViewHolder {
		ImageView ivPhoto;
		TextView tvName;
		ImageView ivIcon;
		TextView tvNumber;
		TextView tvDate;
	}
}
