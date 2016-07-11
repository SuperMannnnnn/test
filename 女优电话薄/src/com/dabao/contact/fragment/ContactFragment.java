package com.dabao.contact.fragment;

import java.util.List;

import com.dabao.contact.R;
import com.dabao.contact.adapter.ContactAdapter;
import com.dabao.contact.entity.Contact;
import com.dabao.contact.presenter.IContactPresenter;
import com.dabao.contact.presenter.impl.ContactPresenterImpl;
import com.dabao.contact.util.BitmapUtils;
import com.dabao.contact.view.IContactView;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactFragment extends Fragment implements IContactView {

	private List<Contact> contacts;
	private IContactPresenter presenter;
	private ContactAdapter adapter;
	private GridView gvContact;
	
	public ContactFragment(){
		this.presenter = new ContactPresenterImpl(this);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_contact, null);
		setViews( view);
		setListeners();
		//调用presenter的方法  执行加载联系人的流程
		 presenter.loadAllContacts();
		return view;
	}

	private void setListeners() {
		gvContact.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(position==0){
					return;
				}
				//弹出 自定义界面 AlertDialog
				Builder builder = new Builder(getActivity());
				final AlertDialog dialog=builder.create();
				dialog.show();
				//获取alertDialog的窗口
				Window window = dialog.getWindow();
				View dialogView = View.inflate(getActivity(), R.layout.contact_detail, null);
				//获取dialogView中的控件
				ImageView photo=(ImageView) dialogView.findViewById(R.id.ivPhoto);
				ImageView x=(ImageView) dialogView.findViewById(R.id.imageView2);
				TextView tvName = (TextView) dialogView.findViewById(R.id.textView1);
				//获取当前选中的Contact对象
				x.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
				Contact c = contacts.get(position);
				tvName.setText(c.getName());
				Bitmap b = BitmapUtils.getPhoto(getActivity(), c.getPhotoId());
				if(b!=null){
					photo.setImageBitmap(b);
				}else{
					photo.setImageResource(R.drawable.img01g_15);
				}
				window.setContentView(dialogView);
			}
		});
		
	}

	private void setViews(View view) {
		this.gvContact = (GridView) view.findViewById(R.id.gvcontact);
	}

	@Override
	public void setData(List<Contact> contacts) {
		this.contacts = contacts;
	}

	@Override
	public void showData() {
		//设置自定义adapter
		adapter = new ContactAdapter(getActivity(), contacts);
		gvContact.setAdapter(adapter);
}
}