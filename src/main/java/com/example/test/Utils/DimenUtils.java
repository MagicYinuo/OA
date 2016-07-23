package com.example.test.Utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * @项目名: oa
 * @包名: com.example.test.activity
 * @类名: DimenUtils
 * @创建者: 
 * 
 * @描述: TODO
 * 
 * @版本号: $Rev: 9 $
 * @更新人: $Author: xq $
 * @更新时间: $Date: 2015-09-22 15:30:11 +0800 (Tue, 22 Sep 2015) $
 * 
 * @更新内容: TODO
 */
public class DimenUtils
{

	/**
	 * dp 转 px
	 * 
	 * @param context
	 * @param dp
	 * @return
	 */
	public static int dp2px(Context context, int dp)
	{
		// 1px = 1dp * (dpi / 160)

		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		int dpi = metrics.densityDpi;

		return (int) (dp * (dpi / 160f) + 0.5f);
	}

	/**
	 * px 转 dp
	 * 
	 * @param context
	 * @param px
	 * @return
	 */
	public static int px2dp(Context context, int px)
	{
		// 1dp = 1px * 160 / dpi

		DisplayMetrics metrics = context.getResources().getDisplayMetrics();

		int dpi = metrics.densityDpi;
		return (int) (px * 160f / dpi + 0.5f);
	}
}
