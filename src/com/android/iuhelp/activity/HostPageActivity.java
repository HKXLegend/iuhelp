package com.android.iuhelp.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;

import com.android.iuhelp.fragment.Fragment2;
import com.android.iuhelp.fragment.AboutMeHost;
import com.android.iuhelp.fragment.MainHost;
import com.android.iuhelp.tools.TabManager;
import com.android.iuhelp.view.slidingmenu.SlidingMenu;

public class HostPageActivity extends FragmentActivity {

	private Button cityButton;
	private SlidingMenu slidingMenu;
	private ListView slidingListView;

	private TabHost mTabHost;
	private TabManager mTabManager;
	public static Handler textHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.host_activity_layout);
		cityButton = (Button) findViewById(R.id.city_button);

		loadSlidingMenu();// 加载左滑组件
		loadingTabHost();// 加载tabhost

	}

	// 三屏Fragment的tabhost
	public void loadingTabHost() {
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
		mTabManager = new TabManager(this, mTabHost, R.id.realtabcontent);
		mTabHost.setCurrentTab(0);

		View v1 = (View) LayoutInflater.from(this).inflate(R.layout.tab_one,
				null);
		View v2 = (View) LayoutInflater.from(this).inflate(R.layout.tab_two,
				null);
		View v3 = (View) LayoutInflater.from(this).inflate(R.layout.tab_three,
				null);

		mTabManager.addTab(mTabHost.newTabSpec("Fragment1").setIndicator(v1),
				MainHost.class, null);
		mTabManager.addTab(mTabHost.newTabSpec("Fragment2").setIndicator(v2),
				Fragment2.class, null);
		mTabManager.addTab(mTabHost.newTabSpec("Fragment3").setIndicator(v3),
				AboutMeHost.class, null);

	}

	// 加载、操作slidingmenu和组件
	public void loadSlidingMenu() {
		slidingMenu = new SlidingMenu(this);
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_offset);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		slidingMenu.setMenu(R.layout.slidingmenu);
		slidingListView = (ListView) findViewById(R.id.slidingmenu_content_listview);
		slidingListView.setDividerHeight(0);
		
		String[] name = new String[] { "问答", "活动", "二维码", "关于" };
		String[] information = new String[] { "知道你不知道的事，赶快看看吧", "参与活动赢帮豆",
				"扫扫分享加入我们", "关于我们的事情" };
		int[] imageId = new int[] { R.drawable.slidingmenu_item_question_pic,
				R.drawable.slidingmenu_item_activitypic,
				R.drawable.slidingmenu_item_erweimapic,
				R.drawable.slidingmenu_item_aboutpic };
		List<Map<String, Object>> listItem = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < name.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pic", imageId[i]);
			map.put("name", name[i]);
			map.put("description", information[i]);
			listItem.add(map);
		}

		SimpleAdapter adapter = new SimpleAdapter(this, listItem,
				R.layout.slidingmenu_entity_list_item, new String[] { "pic",
						"name", "description" }, new int[] {
						R.id.slidingmenu_item_pic, R.id.slidingmenu_item_name,
						R.id.slidingmenu_item_information });
		slidingListView.setAdapter(adapter);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 0x11 && requestCode == 0x11) {
			Bundle bundle = data.getExtras();
			String city = bundle.getString("city");
			cityButton.setText(city);
		}
	}

	// 点击选择城市按钮
	public void selectCity(View v) {
		Intent intent = new Intent(HostPageActivity.this,
				SelectCitysActivity.class);
		startActivityForResult(intent, 0x11);
	}

	// 点击左滑菜单按钮,显示菜单
	public void openSlidingMenu(View v) {
		slidingMenu.toggle(true);
	}
}
