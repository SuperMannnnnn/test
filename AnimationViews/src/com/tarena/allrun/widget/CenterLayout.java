package com.tarena.allrun.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

//自定义布局(容器)
public class CenterLayout extends ViewGroup {
	int groupWidth, groupHeight;

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		groupHeight = h;
		groupWidth = w;
	}

	public CenterLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// 测量每个子控件
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			// 得到子控件
			View childView = getChildAt(i);
			// 测量子控件
			childView.measure(widthMeasureSpec, heightMeasureSpec);
		}
	}

	// 指定容器内子控件如何显示
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int childViewCount = this.getChildCount();
		// 计算子控件高度的和
		int sum = 0;
		for (int i = 0; i < childViewCount; i++) {
			View childView = getChildAt(i);
			sum = sum + childView.getMeasuredHeight();
		}

		int top = (groupHeight - sum) / 2;
		// 指定每个子控件的位置
		for (int i = 0; i < childViewCount; i++) {
			View childView = getChildAt(i);
			int left = (groupWidth - childView.getMeasuredWidth()) / 2;
			int right = left + childView.getMeasuredWidth();
			int bottom = top + childView.getMeasuredHeight();
			childView.layout(left, top, right, bottom);
			top = top + childView.getMeasuredHeight();
		}
		// 得到子控件
		// View animationView = this.getChildAt(0);
		// View shadeView = this.getChildAt(1);
		// 模拟出linearLayout
		// animationView.layout(0, 0, 60, 60);
		// shadeView.layout(0, 60, 480, 60 + 50);
		// 模拟出frameLayout
		// animationView.layout(0, 0, 60, 60);
		// shadeView.layout(0, 0, 480, 0 + 50);
		// 相对布局
		// animationView.layout(0, 0, 60, 60);
		// shadeView.layout(60, 60, 480, 60 + 50);
	}

}
