package com.android.iuhelp.config;

public class Config {

	/**
	 * url部分
	 */
	public final static String SESSION_ID = "sessionid";//android端session值
	public final static String COOKIE = "Cookie";//保持session长连接的cookie
	
	public final static String LOGIN_AND_REGISTER_URL = "";// 登陆的url地址

	/**
	 * 登录、注册部分
	 */
	public final static String ACTION = "action"; // 用于区分登录、注册不同的动作
	public final static String ACTION_LOGIN = "login";// 执行登录
	public final static String ACTION_REGISTER = "register";// 执行注册

	public final static String LOGIN_USER_NAME = "admin_name";// 登录传值用户名
	public final static String LOGIN_PASSWORD = "admin_password";// 登录传值密码

	public final static String REGISTER_USER_NAME = "name";// 注册传值用户名
	public final static String REGISTER_PASSWORD = "password";// 注册传值密码
	public final static String REGISTER_PHONE = "phone";// 注册传值电话
	public final static String REGISTER_YANZHENGMA = "yanzhengma";// 注册传值验证码
	public final static String REGISTER_GENDER = "gender";// 注册传值性别

	public final static String KEY_STATUS = "status";// 返回状态值
	public final static int STATUS_SUCCESS = 01;// 返回状态为成功
	public final static int STATUS_FAIL = 02; // 返回状态为失败

	/**
	 * 数据库存贮部分
	 */
	public final static String SAVE_INFORMATION_FILE = "iu_information";// 保存用户名密码
	public final static String SAVE_NAME = "use_name";// 保存的用户姓名
	public final static String SAVE_PASSWORD = "use_password";// 保存的用户密码

}
