package com.dvrbear.dvrcatdog.interfaces;

import com.dvrbear.dvrcatdog.models.ItemModel;


public interface DataChangeEvent {

	void onDataLoaded();

	void onItemClick(ItemModel itemModel);
}
