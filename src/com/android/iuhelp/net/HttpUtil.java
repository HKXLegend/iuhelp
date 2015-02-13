package com.android.iuhelp.net;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.android.iuhelp.config.Config;

/**
 * @author 黄科翔
 * @Description 用于http的get和post方式连接服务器
 * 
 */
public class HttpUtil {

	private static DefaultHttpClient httpClient = new DefaultHttpClient();
	public static String SessionId = null;

	/**
	 * @param url
	 *            访问的url
	 * @return String类型字符串
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @Description get方式请求
	 */
	public static String getRequest(final String url)
			throws InterruptedException, ExecutionException {
		FutureTask<String> task = new FutureTask<String>(
				new Callable<String>() {

					@Override
					public String call() throws Exception {
						HttpGet get = new HttpGet(url);
						HttpResponse httpResponse = httpClient.execute(get);
						if (null != SessionId) {
							get.setHeader(Config.COOKIE, Config.SESSION_ID
									+ SessionId);
						}
						// 成功返回String字符串
						if (httpResponse.getStatusLine().getStatusCode() == 200) {
							String result = EntityUtils.toString(httpResponse
									.getEntity());
							CookieStore mCookieStore = httpClient
									.getCookieStore();
							List<Cookie> cookies = mCookieStore.getCookies();
							for (int i = 0; i < cookies.size(); i++) {
								// 这里是读取Cookie['PHPSESSID']的值存在静态变量中，保证每次都是同一个值
								if (Config.SESSION_ID.equals(cookies.get(i)
										.getName())) {
									SessionId = cookies.get(i).getValue();
									break;
								}

							}
							return result;
						}
						return null;
					}
				});
		new Thread(task).start();
		return task.get();
	}

	/**
	 * @param url
	 *            访问的url地址
	 * @param map
	 *            传入的map键值对
	 * @return String字符串
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @Description Post方式请求
	 */
	public static String postRequest(final String url,
			final Map<String, String> map) throws InterruptedException,
			ExecutionException {
		FutureTask<String> task = new FutureTask<String>(
				new Callable<String>() {

					@Override
					public String call() throws Exception {

						HttpPost post = new HttpPost(url);
						List<NameValuePair> list = new ArrayList<NameValuePair>();
						for (String key : map.keySet()) {
							list.add(new BasicNameValuePair(key, map.get(key)));
						}

						post.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
						if (null != SessionId) {
							post.setHeader(Config.COOKIE, Config.SESSION_ID
									+ SessionId);
						}

						HttpResponse httpresponse = httpClient.execute(post);
						if (httpresponse.getStatusLine().getStatusCode() == 200) {
							String result = EntityUtils.toString(httpresponse
									.getEntity());
							CookieStore mCookieStore = httpClient
									.getCookieStore();
							List<Cookie> cookies = mCookieStore.getCookies();
							for (int i = 0; i < cookies.size(); i++) {
								// 这里是读取Cookie[SESSION_ID]的值存在静态变量中，保证每次都是同一个值
								if (Config.SESSION_ID.equals(cookies.get(i)
										.getName())) {
									SessionId = cookies.get(i).getValue();
									break;
								}

							}
							return result;
						}
						return null;
					}
				});
		new Thread(task).start();
		return task.get();
	}
}
