package com.dvrbear.dvrcatdog.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dvrbear.dvrcatdog.R;

public class DetailsFragment extends BaseFragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


		rootView = inflater.inflate(R.layout.details_fragment, container, false);

		return rootView;
	}

}
