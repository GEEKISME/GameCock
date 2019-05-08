package com.biotag.gamecock.utiles;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.biotag.gamecock.R;
import com.biotag.gamecock.Widget.SystemBarTintManager;
import com.biotag.gamecock.common.Constants;


public class ViewUtils {

	/** 把自身从父View中移除 */
	public static void removeSelfFromParent(View view) {
		if (view != null) {
			ViewParent parent = view.getParent();
			if (parent != null && parent instanceof ViewGroup) {
				ViewGroup group = (ViewGroup) parent;
				group.removeView(view);
			}
		}
	}

	/** FindViewById的泛型封装，减少强转代码 */
	public static <T extends View> T findViewById(View layout, int id) {
		return (T) layout.findViewById(id);
	}


	/**
	 * 判断是否为全屏
	 * @param activity
	 * @return
     */
	public static boolean isFullScreen(final Activity activity) {
		return (activity.getWindow().getAttributes().flags &
				WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0;
	}

	/**
	 * 判断状态栏是否为透明
	 * @param activity
	 * @return
     */
	@TargetApi(Build.VERSION_CODES.KITKAT)
	public static boolean isTranslucentStatus(final Activity activity) {
		//noinspection SimplifiableIfStatement
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			return (activity.getWindow().getAttributes().flags &
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS) != 0;
		}
		return false;
	}

	/**
	 *当状态栏为透明或半透明时，判断view是否根据状态栏来调整自己的布局，如果fitSystemWindows属性为true，会调整view的paddingTop值为状态栏
	 * 高度，让view的内容不被上拉到状态栏
	 * @param activity
	 * @return
     */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public static boolean isFitsSystemWindows(final Activity activity){
		//noinspection SimplifiableIfStatement
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			return ((ViewGroup)activity.findViewById(android.R.id.content)).getChildAt(0).
					getFitsSystemWindows();
		}
		return false;
	}




	/**
	 * 改变状态栏颜色
	 * @param rootview
	 * @param activity
     */
	public static void changeColorStatusBarColor(View rootview, Activity activity) {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Constants.SHOW_TITLEBAR) {
			rootview.setFitsSystemWindows(true);
			WindowManager.LayoutParams winParams = activity.getWindow().getAttributes();
			winParams.flags = winParams.flags | WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
			activity.getWindow().setAttributes(winParams);

			//设置状态栏颜色
			SystemBarTintManager tintManager = new SystemBarTintManager(activity);
			tintManager.setStatusBarTintEnabled(true);
			tintManager.setStatusBarTintResource(R.color.title_bar_color);
		}
	}

	/**
	 * 动态设置ListView的高度
	 * @param listView
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {

		ListAdapter listAdapter = listView.getAdapter();

		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

}
