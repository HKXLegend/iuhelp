package com.android.iuhelp.tools;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author �ƿ���
 * @Description ���ڴ����û��ĵ�¼��Ϣ�������û��������룩
 */
public class SharedPreferencesUtil {

	private static SharedPreferences sp;
	private static SharedPreferences.Editor editor;

	/**
	 * @param context
	 *            ��ǰActivity����
	 * @param filePath
	 *            �洢·��
	 * @param map
	 *            ��ŵ�key-valueֵ
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
	 *            ��ǰActivity����
	 * @param filePath
	 *            ����·��
	 * @param keyString
	 *            ȡ��key��Ӧ��valueֵ
	 * @return Mapֵ
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
