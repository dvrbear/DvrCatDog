package com.dvrbear.dvrcatdog.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.dvrbear.dvrcatdog.MainApplication;
import com.dvrbear.dvrcatdog.R;
import com.dvrbear.dvrcatdog.adapters.RecyclerViewAdapter;
import com.dvrbear.dvrcatdog.controllers.RestController;
import com.dvrbear.dvrcatdog.interfaces.DataChangeEvent;
import com.dvrbear.dvrcatdog.models.ItemModel;
import com.dvrbear.dvrcatdog.utils.CONSTANTS;

import java.util.ArrayList;
import java.util.List;

public class TabsFragment extends BaseFragment implements DataChangeEvent{

	private RestController restController;
	private TabLayout tabLayout;
	private LinearLayoutManager linearLayoutManager;
	private RecyclerView recyclerView;
	private RecyclerViewAdapter recyclerViewAdapter;

	private List<ItemModel> modelList;
	private int currentTabLayout = 0;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.tabs_fragment, container, false);

		recyclerView = (RecyclerView) rootView.findViewById(R.id.rv);
		tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);

		tabLayout.addTab(tabLayout.newTab().setText(CONSTANTS.cat));
		tabLayout.addTab(tabLayout.newTab().setText(CONSTANTS.dog));
		tabLayout.getTabAt(global.getTabPositions()).select();
		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				currentTabLayout = tab.getPosition();
				global.setTabPositions(currentTabLayout);
				restController.getDataFromServer(CONSTANTS.pets[currentTabLayout]);
				scrollToLastPosition();
			}
			@Override
			public void onTabUnselected(TabLayout.Tab tab) {}
			@Override
			public void onTabReselected(TabLayout.Tab tab) {}
		});

		recyclerView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_UP){
					saveCurrentPosition();
				}
				return false;
			}
		});

		recyclerView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		modelList = new ArrayList<>();
		linearLayoutManager = new LinearLayoutManager(getActivity());
		recyclerViewAdapter = new RecyclerViewAdapter(modelList, getActivity(), this);
		recyclerView.setLayoutManager(linearLayoutManager);
		recyclerView.setAdapter(recyclerViewAdapter);
		restController = new RestController(recyclerViewAdapter);
		restController.getDataFromServer(CONSTANTS.pets[currentTabLayout]);

		return rootView;
	}

	private void saveCurrentPosition(){
		currentTabLayout = tabLayout.getSelectedTabPosition();
		int scrollPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
		global.setScrollPositions(currentTabLayout, scrollPosition);
	}

	private void scrollToLastPosition(){
		if(recyclerView != null) {
			currentTabLayout = tabLayout.getSelectedTabPosition();
			recyclerView.smoothScrollToPosition(0);
			recyclerView.post(new Runnable() {
				@Override
				public void run() {
					recyclerView.scrollToPosition(global.getScrollPositions(currentTabLayout));
				}
			});
		}
	}
	@Override
	public void onDataLoaded() {
		scrollToLastPosition();
	}

	@Override
	public void onItemClick(ItemModel itemModel) {
		global.setItemModel(itemModel);
		navigator.addNewFragment(CONSTANTS.FRAG_DETAILS);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {

	}
}
