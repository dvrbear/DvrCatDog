package com.dvrbear.dvrcatdog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dvrbear.dvrcatdog.controllers.NavigatorController;
import com.dvrbear.dvrcatdog.utils.CONSTANTS;


public class MainActivity extends AppCompatActivity {

	private NavigatorController navigator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		navigator = ((MainApplication) getApplication()).getNavigatorController();
		if(navigator.isFirstRun()) {
			navigator.setFirstRun(false);
			navigator.setFragmentManagers(getFragmentManager());
			navigator.addNewFragment(CONSTANTS.FRAG_TABS);
		}
	}
}
