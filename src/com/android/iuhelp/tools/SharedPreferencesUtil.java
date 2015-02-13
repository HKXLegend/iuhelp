package com.android.iuhelp.tools;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author 黄科翔
 * @Description 用于存贮用户的登录信息（包含用户名和密码）
 */
public class SharedPreferencesUtil {

	private static SharedPreferences sp;
	private static SharedPreferences.Editor editor;

	/**
	 * @param context
	 *            当前Activity环境
	 * @param filePath
	 *            存储路径
	 * @param map
	 *            存放的key-value值
	 */
	public static void saveInXmlFile(Context context, String filePath,
			Map<String, String> map) {
		sp = context.getSharedPreferences(filePath, Context.MODE_PRIVATE);
		editor = sp.edit();
		Set<String> set = new HashSet<String>();
		set = map.keySet();
		for (String key : set) {
			String value = map.get(key);
			editor.putString(key, value);
			editor.commit();
		}
	}

	/**
	 * @param context
	 *            当前Activity环境
	 * @param filePath
	 *            存贮路径
	 * @param keyString
	 *            取出key对应的value值
	 * @return Map值
	 */
	public static Map<String, String> getFromXmlFile(Context context,
			String filePath, String[] keyString) {
		Map<String, String> map = new HashMap<String, String>();
		sp = context.getSharedPreferences(filePath, Context.MODE_PRIVATE);
		for (String key : keyString) {
			String value = sp.getString(key, null);
			map.put(key, value);
		}

		return map;
	}
}
