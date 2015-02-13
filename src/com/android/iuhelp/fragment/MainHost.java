package com.android.iuhelp.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.iuhelp.activity.R;
import com.android.iuhelp.tools.GetCurrentTime;
import com.android.iuhelp.view.pulltorefresh.XListView;
import com.android.iuhelp.view.pulltorefresh.XListView.IXListViewListener;

public class MainHost extends Fragment implements IXListViewListener {

	private int start = 0;// zanshi
	private ArrayList<String> items = new ArrayList<String>();// zanshi

	private Activity activity;
	private View view;

	private XListView mListView;
	private LinearLayout editLayout1;
	private LinearLayout editLayout2;
	private Button findButton;
	private EditText findEdit;

	private ArrayAdapter<String> mAdapter;
	private Handler mHandler;
	private static int refreshCnt = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.main_fragment_layout,
				container, false);
		activity = getActivity();
		this.view = rootView;
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		geneItems();
		init();
		mListView.setPullLoadEnable(true);
		mListView.setAdapter(mAdapter);
		mListView.setXListViewListener(this);

		// ����任���������
		editLayout1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				editLayout1.setVisibility(View.GONE);
				editLayout2.setVisibility(View.VISIBLE);
			}
		});
		super.onActivityCreated(savedInstanceState);
	}

	// ���س�ʼ������
	public void init() {
		mListView = (XListView) view.findViewById(R.id.host1_listview);
		editLayout1 = (LinearLayout) view
				.findViewById(R.id.host1_fragment_editlayout1);
		editLayout2 = (LinearLayout) view
				.findViewById(R.id.host1_fragment_editlayout2);
		findButton = (Button) view.findViewById(R.id.host1_fragment_findbutton);
		findEdit = (EditText) view.findViewById(R.id.host1_fragment_find_edit);

		mAdapter = new ArrayAdapter<String>(getActivity(),
				R.layout.host1_list_item, items);

		mHandler = new Handler();
	}

	// ��ʱ������ʵ��listview����
	private void geneItems() {
		for (int i = 0; i != 20; ++i) {
			items.add("refresh cnt " + (++start));
		}
	}

	@Override
	// ����ˢ��
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				start = ++refreshCnt;
				items.clear();
				geneItems();
				// mAdapter.notifyDataSetChanged();
				mAdapter = new ArrayAdapter<String>(activity,
						R.layout.host1_list_item, items);
				mListView.setAdapter(mAdapter);
				onLoad();
			}
		}, 2000);
	}

	@Override
	// ������ظ���
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				geneItems();
				mAdapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}

	// �������ؽ���
	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime(GetCurrentTime.getTime());
	}
}
