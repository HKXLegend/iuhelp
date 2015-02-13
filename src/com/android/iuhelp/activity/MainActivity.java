package com.android.iuhelp.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void loginbutton(View v) {
		ComponentName comp = new ComponentName(this, LoginActivity.class);
		Intent intent = new Intent();
		intent.setComponent(comp);
		startActivity(intent);
	}

	public void registerbutton(View v) {
		ComponentName comp = new ComponentName(this, RegisterActivity.class);
		Intent intent = new Intent();
		intent.setComponent(comp);
		startActivity(intent);
	}

	public void hostbutton(View v) {
		ComponentName comp = new ComponentName(this, HostPageActivity.class);
		Intent intent = new Intent();
		intent.setComponent(comp);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
