package com.dvrbear.dvrcatdog.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dvrbear.dvrcatdog.R;
import com.dvrbear.dvrcatdog.interfaces.DataChangeEvent;
import com.dvrbear.dvrcatdog.models.ItemModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>{

	private List<ItemModel> modelList;
	private Context context;
	private DataChangeEvent notifyer;

	public RecyclerViewAdapter(List<ItemModel> modelList, Context context, DataChangeEvent notifyer){
		this.modelList = modelList;
		this.context = context;
		this.notifyer = notifyer;
	}

	public class ItemViewHolder extends RecyclerView.ViewHolder {
		TextView itemTitle;
		ImageView itemImage;

		ItemViewHolder(View itemView) {
			super(itemView);
			itemTitle = (TextView) itemView.findViewById(R.id.item_title);
			itemImage = (ImageView) itemView.findViewById(R.id.item_image);

		}
	}

	@Override
	public int getItemCount() {
		return modelList.size();
	}

	@Override
	public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
		return new ItemViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ItemViewHolder viewHolder, int i) {
		final int index = i;
		viewHolder.itemTitle.setText(modelList.get(i).getTitle());
		Picasso
				.with(context)
				.load(modelList.get(i).getImageUrl())
				.fit()
				.centerInside()
				.into(viewHolder.itemImage);
		viewHolder.itemImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				notifyer.onItemClick(modelList.get(index));
			}
		});
	}

	@Override
	public void onAttachedToRecyclerView(RecyclerView recyclerView) {
		super.onAttachedToRecyclerView(recyclerView);
	}

	public void updateData(List<ItemModel> newList){
		if(modelList != null || modelList.size() > 0) {
			modelList.clear();
			modelList.addAll(newList);
			notifyDataSetChanged();
			notifyer.onDataLoaded();
		}
	}
}
