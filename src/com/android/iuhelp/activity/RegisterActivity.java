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
 * @author 黄科翔
 * @describe 执行注册界面
 */
public class RegisterActivity extends Activity {

	// 用作http传输注册信息
	Map<String, String> map = new HashMap<String, String>();

	private EditText nameEdit;
	private EditText passwordEdit;
	private EditText re_passwordEdit;
	private EditText phoneEdit;
	private EditText yanzhengmaEdit;
	private Button registerButton;

	// 成员变量，同样用于存贮在xml文件中
	private String name;
	private String password;

	Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_activity_layout);
		init();// 加载布局文件

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// 注册成功
				if (msg.what == 01) {
					Toast.makeText(RegisterActivity.this, "注册成功",
							Toast.LENGTH_SHORT).show();
					HashMap<String, String> hashMap = new HashMap<String, String>();
					hashMap.put(Config.SAVE_NAME, name);
					hashMap.put(Config.SAVE_PASSWORD, password);
					SharedPreferencesUtil.saveInXmlFile(RegisterActivity.this,
							Config.SAVE_INFORMATION_FILE, hashMap);
				}
				// 网络异常
				else {
					Toast.makeText(RegisterActivity.this, "网络异常",
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
	 * @Description 检测注册信息
	 */
	public void checkInformation() {
		name = nameEdit.getText().toString().trim();
		password = passwordEdit.getText().toString().trim();
		final String re_password = re_passwordEdit.getText().toString().trim();
		final String phone = phoneEdit.getText().toString().trim();
		final String yanzhengma = yanzhengmaEdit.getText().toString().trim();

		if ("".equals(name) || null == name) {
			Toast.makeText(RegisterActivity.this, "请输入昵称", Toast.LENGTH_SHORT)
					.show();
			return;
		}

		if ("".equals(password) || null == password) {
			Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT)
					.show();
			return;
		}

		if ("".equals(re_password) || null == re_password) {
			Toast.makeText(RegisterActivity.this, "请输入确认密码", Toast.LENGTH_SHORT)
					.show();
			return;
		}

		if ("".equals(phone) || null == phone) {
			Toast.makeText(RegisterActivity.this, "预留手机不能为空",
					Toast.LENGTH_SHORT).show();
			return;
		}

		if ("".equals(yanzhengma) || null == yanzhengma) {
			Toast.makeText(RegisterActivity.this, "请输入验证码", Toast.LENGTH_SHORT)
					.show();
			return;
		}

		if (!(password.equals(re_password))) {
			Toast.makeText(RegisterActivity.this, "密码两次出入不一致",
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
						Log.i("json", "返回json为空");
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

	// 点击注册按钮
	public void goRegister(View b) {
		Animation a = AnimationUtils.loadAnimation(RegisterActivity.this,
				R.anim.l_r_right_button);
		registerButton.startAnimation(a);
		checkInformation();

	}

	/**
	 * @Description 获取验证码事件
	 */
	public void getYanzhengma() {
		Toast.makeText(RegisterActivity.this, "您点击了获取验证码", Toast.LENGTH_SHORT)
				.show();
	}

	// 点击验证码按钮
	public void getYanzhengmaFromNet(View v) {
		getYanzhengma();
	}

	// 点击回退键
	public void goBack(View v) {
		this.finish();
	}

}
