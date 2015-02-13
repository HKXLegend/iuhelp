package com.android.iuhelp.fragment;

import com.android.iuhelp.activity.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Host3Tab2 extends Fragment {

	private Activity activity;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.host3_tab2_fragment_layout,
				container, false);
		view = v;
		activity = getActivity();
		return super.onCreateView(inflater, container, savedInstanceState);
	}
}
