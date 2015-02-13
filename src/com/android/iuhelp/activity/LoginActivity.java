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
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.iuhelp.config.Config;
import com.android.iuhelp.net.CheckInternet;
import com.android.iuhelp.net.HttpUtil;

/**
 * @author �ƿ���
 * @describe ִ�е�½
 */
public class LoginActivity extends Activity {

	private Button loginButton;
	private EditText adminEdit;
	private EditText passEdit;
	private TextView registerText;
	private TextView findPasswordText;

	private Map<String, String> map;
	private Handler loginHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity_layout);
		init();

		loginHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				// ��½�ɹ�
				case 001:
					/*
					 * Intent intent = new Intent(LoginActivity.this, ��ҳ��);
					 * startActivity(intent);
					 */
					
					break;
				// �û����������
				case 002:
					Toast.makeText(LoginActivity.this, "�û������������",
							Toast.LENGTH_SHORT).show();
					passEdit.setText("");
					break;
				// �������쳣
				default:
					Toast.makeText(LoginActivity.this, "�������쳣",
							Toast.LENGTH_SHORT).show();
					break;
				}
			}
		};

		// ���ע��
		registerText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(LoginActivity.this, "������ע��", Toast.LENGTH_SHORT)
						.show();
			}
		});

		// ����һ�����
		findPasswordText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(LoginActivity.this, "�������һ�����",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	public void init() {
		loginButton = (Button) findViewById(R.id.login_button);
		adminEdit = (EditText) findViewById(R.id.login_adminedit);
		passEdit = (EditText) findViewById(R.id.login_passEdit);
		registerText = (TextView) findViewById(R.id.login_toregister_text);
		findPasswordText = (TextView) findViewById(R.id.login_tofindpassword_text);
	}

	// ��½����
	public void loginIn(View v) {

		// ���ذ�ť���Ч��
		Animation a = AnimationUtils.loadAnimation(this,
				R.anim.l_r_right_button);
		loginButton.startAnimation(a);

		String admin = adminEdit.getText().toString().trim();
		String password = passEdit.getText().toString().trim();

		// �����������
		if (!CheckInternet.isNetConnect(this)) {
			Toast.makeText(this, "������������", Toast.LENGTH_SHORT).show();
			return;
		}

		// ��������Ƿ�Ϊ��
		if ("".equals(admin) || null == admin) {
			Toast.makeText(this, "�û�������Ϊ��", Toast.LENGTH_SHORT).show();
			return;
		}

		if ("".equals(password) || null == password) {
			Toast.makeText(this, "���벻��Ϊ��", Toast.LENGTH_SHORT).show();
			return;
		}

		// ������ʽ adminName��XX passWord,XX
		map = new HashMap<String, String>();
		map.put(Config.ACTION, Config.ACTION_LOGIN);
		map.put(Config.LOGIN_USER_NAME, admin);
		map.put(Config.LOGIN_PASSWORD, password);
		new Thread() {
			public void run() {
				try {
					String netString = HttpUtil.postRequest(
							Config.LOGIN_AND_REGISTER_URL, map);
					if ("".equals(netString)) {
						loginHandler.sendEmptyMessage(000);
						Log.i("json", "����jsonΪ��");
					} else {
						JSONObject jsonObject = (new JSONObject(netString))
								.getJSONObject("result");
						int status = jsonObject.getInt(Config.KEY_STATUS);
						if (status == Config.STATUS_SUCCESS) {
							loginHandler.sendEmptyMessage(001);
						} else {
							loginHandler.sendEmptyMessage(002);
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

}
