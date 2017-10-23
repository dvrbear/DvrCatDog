package com.dvrbear.dvrcatdog.controllers;

import com.dvrbear.dvrcatdog.models.ItemModel;

public class GlobalController {

	private int tabPositions = 0;
	private int[] scrollPositions = new int[] {0, 0};
	private ItemModel itemModel;

	public int getScrollPositions(int side) {
		return scrollPositions[side];
	}

	public void setScrollPositions(int side, int value) {
		scrollPositions[side] = value;
	}

	public int getTabPositions() {
		return tabPositions;
	}

	public void setTabPositions(int tabPositions) {
		this.tabPositions = tabPositions;
	}

	public ItemModel getItemModel() {
		return itemModel;
	}

	public void setItemModel(ItemModel itemModel) {
		this.itemModel = itemModel;
	}
}
