package com.android.iuhelp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author 黄科翔
 * @Description 选择所在城市的ListView界面
 */
public class SelectCitysActivity extends Activity {

	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_selectcity_layout);
		listView = (ListView) findViewById(R.id.select_city_listview);
		final String[] string = getResources().getStringArray(R.array.city);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.select_city_listview, R.id.city_name, string);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = getIntent();
				Bundle bundle = new Bundle();
				bundle.putString("city", string[arg2]);
				intent.putExtras(bundle);
				setResult(0x11, intent);
				finish();

			}
		});
	}

	public void goBack(View v) {
		this.finish();
	}
}
