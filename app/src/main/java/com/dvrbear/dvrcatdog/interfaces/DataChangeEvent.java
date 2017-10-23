package com.dvrbear.dvrcatdog.interfaces;

import com.dvrbear.dvrcatdog.models.ItemModel;

/**
 * Created by starlab on 10/20/17.
 */

public interface DataChangeEvent {
	void onDataLoaded();

	void onItemClick(ItemModel itemModel);
}
