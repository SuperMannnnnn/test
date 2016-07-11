package com.tarena.allrun.widget;

import com.tarena.allrun.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

//自定义动画控件
public class AnimationView extends View {
	Bitmap[] array;
	int index=0;
	Thread thread;
	boolean isRunning=true;
	int viewWidth,viewHeight;
	class MyRunnable implements Runnable
	{

		@Override
		public void run() {
			while (isRunning) {
				try {
					index++;
					if (index>array.length-1)
					{
						index=0;
					}
					
					//让线程调onDraw()用invalidate();
					//工作线程调onDraw()
					postInvalidate();
					Log.i("ondraw()", "run index="+index+","+System.currentTimeMillis());

					Thread.currentThread().sleep(100);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			
		}
		
	}

	public AnimationView(Context context, AttributeSet attrs) {
		super(context, attrs);
		array = new Bitmap[4];
		array[0] = BitmapFactory.decodeResource(getResources(),
				R.drawable.logo1);
		array[1] = BitmapFactory.decodeResource(getResources(),
				R.drawable.logo2);
		array[2] = BitmapFactory.decodeResource(getResources(),
				R.drawable.logo3);
		array[3] = BitmapFactory.decodeResource(getResources(),
				R.drawable.logo4);
		thread=new Thread(new MyRunnable());
		thread.start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		try {
			Paint paint=new Paint();
			paint.setColor(Color.RED);
			//画背景
			Rect rect=new Rect(0, 0, viewWidth, viewHeight);
			canvas.drawRect(rect, paint);
			
			Bitmap bitmap=array[index];
			
			canvas.drawBitmap(bitmap, 0, 0, paint);
			Log.i("ondraw()", "index="+index+",bitmap="+bitmap.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
viewHeight=h;
viewWidth=w;
		super.onSizeChanged(w, h, oldw, oldh);
	}
}
