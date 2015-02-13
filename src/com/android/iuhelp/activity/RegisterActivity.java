package com.android.iuhelp.activity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.iuhelp.activity.R;
import com.android.iuhelp.config.Config;
import com.android.iuhelp.net.HttpUtil;
import com.android.iuhelp.tools.SharedPreferencesUtil;

/**
 * @author �ƿ���
 * @describe ִ��ע�����
 */
public class RegisterActivity extends Activity {

	// ����http����ע����Ϣ
	Map<String, String> map = new HashMap<String, String>();

	private EditText nameEdit;
	private EditText passwordEdit;
	private EditText re_passwordEdit;
	private EditText phoneEdit;
	private EditText yanzhengmaEdit;
	private Button registerButton;

	// ��Ա������ͬ�����ڴ�����xml�ļ���
	private String name;
	private String password;

	Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_activity_layout);
		init();// ���ز����ļ�

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// ע��ɹ�
				if (msg.what == 01) {
					Toast.makeText(RegisterActivity.this, "ע��ɹ�",
							Toast.LENGTH_SHORT).show();
					HashMap<String, String> hashMap = new HashMap<String, String>();
					hashMap.put(Config.SAVE_NAME, name);
					hashMap.put(Config.SAVE_PASSWORD, password);
					SharedPreferencesUtil.saveInXmlFile(RegisterActivity.this,
							Config.SAVE_INFORMATION_FILE, hashMap);
				}
				// �����쳣
				else {
					Toast.makeText(RegisterActivity.this, "�����쳣",
							Toast.LENGTH_SHORT).show();
				}
			}
		};
	}

	public void init() {
		nameEdit = (EditText) findViewById(R.id.register_name_edit);
		passwordEdit = (EditText) findViewById(R.id.register_password_edit);
		re_passwordEdit = (EditText) findViewById(R.id.register_checkpassword_edit);
		phoneEdit = (EditText) findViewById(R.id.register_phone_edit);
		yanzhengmaEdit = (EditText) findViewById(R.id.register_yanzhengma_edit);
		registerButton = (Button) findViewById(R.id.register_button);
	}

	/**
	 * @Description ���ע����Ϣ
	 */
	public void checkInformation() {
		name = nameEdit.getText().toString().trim();
		password = passwordEdit.getText().toString().trim();
		final String re_password = re_passwordEdit.getText().toString().trim();
		final String phone = phoneEdit.getText().toString().trim();
		final String yanzhengma = yanzhengmaEdit.getText().toString().trim();

		if ("".equals(name) || null == name) {
			Toast.makeText(RegisterActivity.this, "�������ǳ�", Toast.LENGTH_SHORT)
					.show();
			return;
		}

		if ("".equals(password) || null == password) {
			Toast.makeText(RegisterActivity.this, "����������", Toast.LENGTH_SHORT)
					.show();
			return;
		}

		if ("".equals(re_password) || null == re_password) {
			Toast.makeText(RegisterActivity.this, "������ȷ������", Toast.LENGTH_SHORT)
					.show();
			return;
		}

		if ("".equals(phone) || null == phone) {
			Toast.makeText(RegisterActivity.this, "Ԥ���ֻ�����Ϊ��",
					Toast.LENGTH_SHORT).show();
			return;
		}

		if ("".equals(yanzhengma) || null == yanzhengma) {
			Toast.makeText(RegisterActivity.this, "��������֤��", Toast.LENGTH_SHORT)
					.show();
			return;
		}

		if (!(password.equals(re_password))) {
			Toast.makeText(RegisterActivity.this, "�������γ��벻һ��",
					Toast.LENGTH_SHORT).show();
			return;
		}

		new Thread() {
			public void run() {
				map.put(Config.ACTION, Config.ACTION_REGISTER);
				map.put(Config.REGISTER_USER_NAME, name);
				map.put(Config.REGISTER_PASSWORD, password);
				map.put(Config.REGISTER_PHONE, phone);
				map.put(Config.REGISTER_YANZHENGMA, yanzhengma);

				try {
					String getString = HttpUtil.postRequest(
							Config.LOGIN_AND_REGISTER_URL, map);
					if ("".equals(getString)) {
						handler.sendEmptyMessage(000);
						Log.i("json", "����jsonΪ��");
					} else {
						JSONObject jsonObject = (new JSONObject(getString))
								.getJSONObject("result");
						int status = jsonObject.getInt(Config.KEY_STATUS);
						if (status == Config.STATUS_SUCCESS) {
							handler.sendEmptyMessage(01);
						} else {
							handler.sendEmptyMessage(02);
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		}.start();
	}

	// ���ע�ᰴť
	public void goRegister(View b) {
		Animation a = AnimationUtils.loadAnimation(RegisterActivity.this,
				R.anim.l_r_right_button);
		registerButton.startAnimation(a);
		checkInformation();

	}

	/**
	 * @Description ��ȡ��֤���¼�
	 */
	public void getYanzhengma() {
		Toast.makeText(RegisterActivity.this, "������˻�ȡ��֤��", Toast.LENGTH_SHORT)
				.show();
	}

	// �����֤�밴ť
	public void getYanzhengmaFromNet(View v) {
		getYanzhengma();
	}

	// ������˼�
	public void goBack(View v) {
		this.finish();
	}

}
