package com.dvrbear.dvrcatdog.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dvrbear.dvrcatdog.R;
import com.dvrbear.dvrcatdog.models.ItemModel;
import com.squareup.picasso.Picasso;

public class DetailsFragment extends BaseFragment{

	private TextView title;
	private ImageView image;
	private ItemModel model;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.details_fragment, container, false);

		title = (TextView) rootView.findViewById(R.id.details_title);
		image = (ImageView) rootView.findViewById(R.id.details_image);
		model = global.getItemModel();

		title.setText(model.getTitle());
		Picasso
				.with(getActivity())
				.load(model.getImageUrl())
				.fit()
				.centerInside()
				.into(image);
		return rootView;
	}
}
