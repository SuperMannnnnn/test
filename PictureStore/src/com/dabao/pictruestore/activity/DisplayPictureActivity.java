package com.dabao.pictruestore.activity;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewSwitcher.ViewFactory;

import com.dabao.pictruestore.R;
import com.dabao.pictruestore.adapter.DisplayPictureAdapter;
import com.dabao.pictruestore.adapter.ShowPictureAdapter;
import com.dabao.pictruestore.app.MyApplication;
import com.dabao.pictruestore.entity.Picture;
import com.dabao.pictruestore.presenter.IShowPicturePresenter;
import com.dabao.pictruestore.presenter.impl.ShowPicturePresenterImpl;
import com.dabao.pictruestore.view.IShowPictureView;

public class DisplayPictureActivity extends Activity implements
		IShowPictureView, OnClickListener {
	/**
	 * ͼƬ������
	 */
	private List<Picture> pictures;
	/**
	 * ImageStoreApplication
	 */
	private MyApplication app;
	/**
	 * ImageSwitcher����ʾͼƬ�Ŀؼ�
	 */
	@ViewInject(R.id.is_pictures)
	private ImageSwitcher isPictures;
	/**
	 * Button����ʾ��һ��
	 */
	@ViewInject(R.id.btn_previous)
	private Button btnPrevious;
	/**
	 * Button����ʾ��һ��
	 */
	@ViewInject(R.id.btn_next)
	private Button btnNext;
	DisplayPictureAdapter adapter;
	/**
	 * ��ǰ��Ҫ��ʾ��ͼƬ��position
	 */
	private int currentPicturePisition;
	private int Pictureid;
	private String src;
	private Animation left2rightInAnim;
	private Animation left2rightOutAnim;
	private Animation right2leftInAnim;
	private Animation right2leftOutAnim;

	private IShowPicturePresenter presenter;

	public DisplayPictureActivity() {
		presenter = new ShowPicturePresenterImpl(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_picture);
		x.view().inject(this);

		presenter.loadGallerys(Pictureid);

		// ��ȡ��ǰ��Ҫ��ʾ��ͼƬ��position
		Intent intent = getIntent();
		currentPicturePisition = intent.getIntExtra("position", 0);
		Pictureid = intent.getIntExtra("Pictureid", 0);
		src = intent.getStringExtra("src");
		Log.i("dabao", "currentPicturePisition="+currentPicturePisition);
		Log.i("dabao", "Pictureid="+Pictureid);
		Log.i("dabao", "src="+src);
		
		// ��ʼ���ؼ�
		isPictures = (ImageSwitcher) findViewById(R.id.is_pictures);
		btnPrevious = (Button) findViewById(R.id.btn_previous);
		btnNext = (Button) findViewById(R.id.btn_next);

		// ����ImageSwitcher
		isPictures.setFactory(new ViewFactory() {
			@Override
			public View makeView() {
				ImageView iv = new ImageView(DisplayPictureActivity.this);
				LayoutParams params = new LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				iv.setLayoutParams(params);
				iv.setScaleType(ScaleType.FIT_CENTER);
				iv.setImageResource(R.drawable.ic_launcher);
				return iv;
			}
		});

		// ��ʾͼƬ
	//	display();

		// Ϊ��ť���ü�����
		btnPrevious.setOnClickListener(this);
		btnNext.setOnClickListener(this);

		// ��ʼ������
		left2rightInAnim = AnimationUtils.loadAnimation(this,
				R.anim.left2right_in);
		left2rightOutAnim = AnimationUtils.loadAnimation(this,
				R.anim.left2right_out);
		right2leftInAnim = AnimationUtils.loadAnimation(this,
				R.anim.right2lef_in);
		right2leftOutAnim = AnimationUtils.loadAnimation(this,
				R.anim.right2left_out);

		// ͳһ����ʱ��
		int animDuration = 1000;
		left2rightInAnim.setDuration(animDuration);
		left2rightOutAnim.setDuration(animDuration);
		right2leftInAnim.setDuration(animDuration);
		right2leftOutAnim.setDuration(animDuration);
	}

	private float downX;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downX = event.getX();
			break;

		case MotionEvent.ACTION_UP:
			if (event.getX() - downX > 60) {
				// ��������
				previous();
			} else if (downX - event.getX() > 60) {
				// ��������
				next();
			}
			break;
		}
		return super.onTouchEvent(event);
	}

	/**
	 * ��ʾͼƬ
	 */
	private void display() {
		// ivPicture.setImageBitmap(pictures.get(currentPicturePisition).getBitmap());
		// BitmapDrawable bd = new BitmapDrawable(getResources(),
		// pictures.get(currentPicturePisition).getBitmap());
		// isPictures.setImageDrawable(bd);
		
		// ����������ʾͼƬ
		InnerTask task = new InnerTask(pictures.get(currentPicturePisition));
		task.execute();
	}

	public static final int MAX_SIZE = 480 / 2;

	private class InnerTask extends AsyncTask<Void, Void, Void> {
		private Picture pic;

		public InnerTask(Picture pic) {
			super();
			this.pic = pic;
		}

		@Override
		protected Void doInBackground(Void... params) {
			// �ж��Ƿ���Ҫ����ͼƬ
			if (pic.getBigBitmap() == null) {
				// ����ͼƬ��С�ı���
				int rate = 1;
				if (pic.getWidth() > MAX_SIZE && pic.getHeight() > MAX_SIZE) {
					if (pic.getWidth() > pic.getHeight()) {
						rate = pic.getHeight() / MAX_SIZE;
					} else {
						rate = pic.getWidth() / MAX_SIZE;
					}
				}
				// ����ͼƬ
				BitmapFactory.Options opts = new BitmapFactory.Options();
				opts.inSampleSize = rate;
				Bitmap bm = BitmapFactory.decodeResource(getResources(), currentPicturePisition, opts);
				// ������ͼƬ�õ���Bitmap����
				pic.setBigBitmap(bm);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			BitmapDrawable bd = new BitmapDrawable(getResources(),
					pic.getBigBitmap());
			isPictures.setImageDrawable(bd);
		}

	}

	/**
	 * ��ʾ��һ��
	 */
	private void previous() {
		// ������һ�ŵ�ͼƬ������
		currentPicturePisition--;
		if (currentPicturePisition < 0) {
			currentPicturePisition = pictures.size() - 1;
		}
		// ���ö���
		isPictures.setInAnimation(left2rightInAnim);
		isPictures.setOutAnimation(left2rightOutAnim);
		// ��ʾ
	//	display();
	}

	/**
	 * ��ʾ��һ��
	 */
	private void next() {
		// ������һ�ŵ�ͼƬ������
		currentPicturePisition++;
		if (currentPicturePisition >= pictures.size()) {
			currentPicturePisition = 0;
		}
		// ���ö���
		isPictures.setInAnimation(right2leftInAnim);
		isPictures.setOutAnimation(right2leftOutAnim);
		// ��ʾ
		//display();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_previous:
			previous();
			break;

		case R.id.btn_next:
			next();
			break;
		}
	}

	@Override
	public void showGalleryList(List<Picture> Pictures) {
		this.pictures = Pictures;
		Log.i("ShowPictureActivity----", "!!!!!"+this.pictures);
		adapter = new DisplayPictureAdapter(this, Pictures);
		//isPictures.setad
		 
		// ΪGridView���ü�����
		//gvShowPicture.setOnItemClickListener(this);
	}

}