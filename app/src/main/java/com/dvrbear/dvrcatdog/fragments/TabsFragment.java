package com.dvrbear.dvrcatdog.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dvrbear.dvrcatdog.R;
import com.dvrbear.dvrcatdog.adapters.RecyclerViewAdapter;
import com.dvrbear.dvrcatdog.controllers.RestController;
import com.dvrbear.dvrcatdog.models.ItemModel;
import com.dvrbear.dvrcatdog.utils.CONSTANTS;

import java.util.ArrayList;
import java.util.List;

public class TabsFragment extends BaseFragment{

	private RestController restController;
	private TabLayout tabLayout;
	private LinearLayoutManager linearLayoutManager;
	private RecyclerView recyclerView;
	private RecyclerViewAdapter recyclerViewAdapter;
	private Parcelable listState;

	private List<ItemModel> modelList;
	private int[] scrollPositions = new int[] {0, 0};

	private int currentTabLayout = 0;
	private int lastScrollPosition = 0;
	private int newScrollPosition = 0;

	private boolean flag = true;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.tabs_fragment, container, false);

		recyclerView = (RecyclerView) rootView.findViewById(R.id.rv);
		tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);

		tabLayout.addTab(tabLayout.newTab().setText(CONSTANTS.cat));
		tabLayout.addTab(tabLayout.newTab().setText(CONSTANTS.dog));
		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				currentTabLayout = tab.getPosition();
				scrollPositions[currentTabLayout == 0 ? 1 : 0] = linearLayoutManager.findLastCompletelyVisibleItemPosition();
				restController.getDataFromServer(CONSTANTS.pets[currentTabLayout]);
				scrollToLastPosition();
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {}
		});

		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);
				scrollPositions[currentTabLayout == 1 ? 1 : 0] = linearLayoutManager.findLastCompletelyVisibleItemPosition();
			}
		});

		modelList = new ArrayList<>();
		linearLayoutManager = new LinearLayoutManager(getActivity());
		recyclerViewAdapter = new RecyclerViewAdapter(modelList, getActivity());
		recyclerView.setLayoutManager(linearLayoutManager);
		recyclerView.setAdapter(recyclerViewAdapter);
		restController = new RestController(recyclerViewAdapter);
		restController.getDataFromServer(CONSTANTS.pets[currentTabLayout]);

//		scrollToLastPosition();

		rootView.post(new Runnable() {
			@Override
			public void run() {
				post();
			}
		});
		return rootView;
	}

	private void post(){
		recyclerView.scrollToPosition(13);
	}

	@Override
	public void onResume() {
		super.onResume();
		recyclerView.scrollToPosition(13);
	}

	private void scrollToLastPosition(){
		if(recyclerView != null) {
			recyclerView.scrollToPosition(13);
//			recyclerView.scrollToPosition(0);
//			recyclerView.post(new Runnable() {
//				@Override
//				public void run() {
//					Log.e("LOG1", "pos = " + scrollPositions[currentTabLayout]);
////					recyclerView.scrollToPosition(scrollPositions[currentTabLayout]);
//					recyclerView.scrollToPosition(13);
//				}
//			});
		}
	}

//	@Override
//	public void onSaveInstanceState(Bundle state) {
//		super.onSaveInstanceState(state);
//
//		listState = recyclerView.getLayoutManager().onSaveInstanceState();
//
////		listState = linearLayoutManager.onSaveInstanceState();
//		state.putParcelable(CONSTANTS.STATE_KEY, listState);
//	}
//
//	@Override
//	public void onViewStateRestored(Bundle savedInstanceState) {
//		super.onViewStateRestored(savedInstanceState);
//		if(savedInstanceState != null)
//			listState = savedInstanceState.getParcelable(CONSTANTS.STATE_KEY);
//	}
//
//	@Override
//	public void onResume() {
//		super.onResume();
//		if (listState != null) {
//			recyclerView.getLayoutManager().onRestoreInstanceState(listState);
////			linearLayoutManager.onRestoreInstanceState(listState);
//		}
//	}
}
