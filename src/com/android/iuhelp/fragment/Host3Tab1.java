package com.android.iuhelp.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.iuhelp.activity.R;

public class Host3Tab1 extends Fragment {

	private Activity activity;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.host3_tab1_fragment_layout,
				container, false);
		view = v;
		activity = getActivity();
		return super.onCreateView(inflater, container, savedInstanceState);
	}
}
