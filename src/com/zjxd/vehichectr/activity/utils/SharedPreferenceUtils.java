package com.zjxd.vehichectr.activity.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferenceUtils {
	private static final String LOG_TAG = SharedPreferenceUtils.class.getSimpleName();
	public static String getAppList(final Context context, String loadAppType) {
		final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
		return preference.getString(loadAppType, null);
	}
	
	public static void savaAppList(final Context context, final String loadAppType,  final String app_list) {
		final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
		preference.edit().putString(loadAppType, app_list).commit();
	}
}