package com.android.iuhelp.config;

public class Config {

	/**
	 * url����
	 */
	public final static String SESSION_ID = "sessionid";//android��sessionֵ
	public final static String COOKIE = "Cookie";//����session�����ӵ�cookie
	
	public final static String LOGIN_AND_REGISTER_URL = "";// ��½��url��ַ

	/**
	 * ��¼��ע�Ჿ��
	 */
	public final static String ACTION = "action"; // �������ֵ�¼��ע�᲻ͬ�Ķ���
	public final static String ACTION_LOGIN = "login";// ִ�е�¼
	public final static String ACTION_REGISTER = "register";// ִ��ע��

	public final static String LOGIN_USER_NAME = "admin_name";// ��¼��ֵ�û���
	public final static String LOGIN_PASSWORD = "admin_password";// ��¼��ֵ����

	public final static String REGISTER_USER_NAME = "name";// ע�ᴫֵ�û���
	public final static String REGISTER_PASSWORD = "password";// ע�ᴫֵ����
	public final static String REGISTER_PHONE = "phone";// ע�ᴫֵ�绰
	public final static String REGISTER_YANZHENGMA = "yanzhengma";// ע�ᴫֵ��֤��
	public final static String REGISTER_GENDER = "gender";// ע�ᴫֵ�Ա�

	public final static String KEY_STATUS = "status";// ����״ֵ̬
	public final static int STATUS_SUCCESS = 01;// ����״̬Ϊ�ɹ�
	public final static int STATUS_FAIL = 02; // ����״̬Ϊʧ��

	/**
	 * ���ݿ��������
	 */
	public final static String SAVE_INFORMATION_FILE = "iu_information";// �����û�������
	public final static String SAVE_NAME = "use_name";// ������û�����
	public final static String SAVE_PASSWORD = "use_password";// ������û�����

}
