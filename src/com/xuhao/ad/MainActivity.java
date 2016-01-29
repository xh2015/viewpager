package com.xuhao.ad;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ViewPager vpad;
	private TextView tv;
	private List<Ad> list = new ArrayList<Ad>();
	private LinearLayout dot_layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initListener();
		initData();

	}

	private void initView() {
		setContentView(R.layout.activity_main);
		vpad = (ViewPager) findViewById(R.id.vp_ad);
		tv = (TextView) findViewById(R.id.tv_ad);
		dot_layout = (LinearLayout) findViewById(R.id.dot_layout);

	}

	private void initListener() {
		vpad.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				updateDocInfo();
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	private void initData() {
		list.add(new Ad(R.drawable.a, "巩俐不低俗，我就不能低俗"));
		list.add(new Ad(R.drawable.b, "朴树又回来了，再唱经典老歌引百万人同唱啊"));
		list.add(new Ad(R.drawable.c, "揭秘北京电影如何升级"));
		list.add(new Ad(R.drawable.d, "乐视网TV版大放送"));
		list.add(new Ad(R.drawable.e, "热血屌丝的反杀"));

		initDots();		
		vpad.setAdapter(new MyPagerAdapter());
		int c=(Integer.MAX_VALUE/2)%list.size();
		vpad.setCurrentItem(Integer.MAX_VALUE/2-c);
		updateDocInfo();
	}

	/**
	 * 初始化点
	 */
	private void initDots() {
		for (int i = 0; i < list.size(); i++) {
			View view = new View(this);
			LayoutParams layoutParams = new LayoutParams(8, 8);
			if (i != 0) {
				layoutParams.leftMargin = 5;
			}
			view.setLayoutParams(layoutParams);
			view.setBackgroundResource(R.drawable.selector_dot);
			dot_layout.addView(view);

		}
	}

	/**
	 * 更新文本以及点
	 */

	private void updateDocInfo() {
		int currentItem = vpad.getCurrentItem();
		int value=currentItem%list.size();
		tv.setText(list.get(value).getDesc());
		for (int i = 0; i < dot_layout.getChildCount(); i++) {
			dot_layout.getChildAt(i).setEnabled(i==value);
		}

	}

	private class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		/**
		 * 相当于baseadpater中的getview()
		 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = View.inflate(MainActivity.this, R.layout.activity_ad,
					null);
			ImageView im = (ImageView) view.findViewById(R.id.iv);

			Ad ad = list.get(position%list.size());

			im.setImageResource(ad.getIcon());

			container.addView(view);

			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// super.destroyItem(container, position, object);
			container.removeView((View) object);
		}

	}
}
