package com.dabao.customview;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by dabao 2016年7月1日
 */
public class CustomView extends View {
	int viewWidth, viewHeight;
	ArrayList<HashMap<String, String>> list;
	/**
	 * gap是间距
	 */
	int gap;
	/**
	 * 最大价格
	 */
	int maxPrice;
	int touchY;

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		touchY = (int) event.getY();
		int x = (int) event.getX();
		int position = x / gap;

		return super.onTouchEvent(event);
	}

	// 绘制界面
	@Override
	protected void onDraw(Canvas canvas) {
		// 画背景
		// 设置color
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		Rect rect = new Rect(0, 0, viewWidth, viewHeight);
		// canvas画矩形 ，文字，图
		canvas.drawRect(rect, paint);

		paint.setColor(Color.RED);
		if (touchY >= 1) {
			canvas.drawLine(0, touchY, viewWidth, touchY, paint);
		}

		// 画文字
		paint.setColor(Color.WHITE);
		// 代码中单位都是px
		paint.setTextSize(48);
		// 得到最后一个时间的宽度
		String strLastTime = list.get(list.size() - 1).get("time");
		// 用矩形得宽度
		Rect sizeRect = new Rect();
		paint.getTextBounds(strLastTime, 0, strLastTime.length(), sizeRect);
		int lastTimeWidth = sizeRect.width();
		gap = (viewWidth - 40) / (list.size() - 1);
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, String> map = list.get(i);
			String time = map.get("time");
			int timeX = i * gap;
			canvas.drawText(time, i * gap, viewHeight, paint);
			// 画价格
			String strPrice = map.get("price");
			int intPrice = Integer.parseInt(strPrice);
			int priceHeight = viewHeight - sizeRect.height();
			int priceY = intPrice * priceHeight / maxPrice;
			// 值越大，显示在下面，应该显示在上面
			priceY = priceHeight - priceY;
			// 最大值，看不到，画到view的上面
			priceY = priceY + sizeRect.height();
			canvas.drawText(strPrice, timeX, priceY, paint);
			// 画线
			if (i < list.size() - 1) {
				int startX = timeX;
				int startY = priceY;
				int stopX = (i + 1) * gap;
				int stopY = 0;
				int nextPrice = Integer.parseInt(list.get(i + 1).get("price"));
				stopY = nextPrice * priceHeight / maxPrice;
				stopY = priceHeight - stopY;
				stopY = stopY + sizeRect.height();
				canvas.drawLine(startX, startY, stopX, stopY, paint);
			}
		}

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		viewHeight = h;
		viewWidth = w;

	}

	public void setData(ArrayList<HashMap<String, String>>list) {
	
	this.list=list;
	
	for (int i=0;i<list.size();i++)
	{
		HashMap< String, String> map=
				list.get(i);
		int price=
				Integer.parseInt(map.get("price"));
		if (price>maxPrice)
		{
			maxPrice=price;
		}
	}
}
	
}
