package com.android.iuhelp.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author �ƿ��� �ж������������
 */
public class CheckInternet {
	public static boolean isNetConnect(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo currentInfo = manager.getActiveNetworkInfo();
		if (currentInfo == null) {
			return false;
		}
		return (currentInfo.getState() == NetworkInfo.State.CONNECTED);
	}
}
