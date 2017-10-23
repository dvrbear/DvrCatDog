package com.dvrbear.dvrcatdog.controllers;

import android.app.FragmentManager;

import com.dvrbear.dvrcatdog.R;
import com.dvrbear.dvrcatdog.fragments.BaseFragment;
import com.dvrbear.dvrcatdog.fragments.DetailsFragment;
import com.dvrbear.dvrcatdog.fragments.TabsFragment;
import com.dvrbear.dvrcatdog.utils.CONSTANTS;

public class NavigatorController {

    private FragmentManager fragmentManager;
    private BaseFragment newFragment;
	private boolean isFirstRun = true;

    private int fragmentLayer = R.id.fragment_layer;

	public void setFragmentManagers(FragmentManager fm){
		fragmentManager = fm;
	}

	public void addNewFragment(String str){
		switch (str) {
			case CONSTANTS.FRAG_DETAILS:
				newFragment = new DetailsFragment();
				break;
			case CONSTANTS.FRAG_TABS:
				newFragment = new TabsFragment();
				break;
		}

		if (fragmentManager != null) {
			fragmentManager.beginTransaction()
					.replace(fragmentLayer, newFragment)
					.addToBackStack(str)
					.commit();
		}
	}

	public boolean isFirstRun() {
		return isFirstRun;
	}

	public void setFirstRun(boolean firstRun) {
		isFirstRun = firstRun;
	}
}
