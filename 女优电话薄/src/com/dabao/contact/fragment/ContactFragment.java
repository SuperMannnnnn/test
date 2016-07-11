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
		//����presenter�ķ���  ִ�м�����ϵ�˵�����
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
				//���� �Զ������ AlertDialog
				Builder builder = new Builder(getActivity());
				final AlertDialog dialog=builder.create();
				dialog.show();
				//��ȡalertDialog�Ĵ���
				Window window = dialog.getWindow();
				View dialogView = View.inflate(getActivity(), R.layout.contact_detail, null);
				//��ȡdialogView�еĿؼ�
				ImageView photo=(ImageView) dialogView.findViewById(R.id.ivPhoto);
				ImageView x=(ImageView) dialogView.findViewById(R.id.imageView2);
				TextView tvName = (TextView) dialogView.findViewById(R.id.textView1);
				//��ȡ��ǰѡ�е�Contact����
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
		//�����Զ���adapter
		adapter = new ContactAdapter(getActivity(), contacts);
		gvContact.setAdapter(adapter);
}
}