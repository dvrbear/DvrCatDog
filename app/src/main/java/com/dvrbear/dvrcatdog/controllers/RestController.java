package com.dvrbear.dvrcatdog.controllers;

import com.dvrbear.dvrcatdog.adapters.RecyclerViewAdapter;
import com.dvrbear.dvrcatdog.interfaces.RestClientEvent;
import com.dvrbear.dvrcatdog.models.ItemModel;
import com.dvrbear.dvrcatdog.utils.CONSTANTS;
import com.dvrbear.dvrcatdog.utils.G;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RestController {

		private Retrofit retrofit;
		private RestClientEvent restClientEvent;
		private RecyclerViewAdapter recyclerViewAdapter;
		private List<ItemModel> modelList;

		public RestController(RecyclerViewAdapter recyclerViewAdapter) {
			this.recyclerViewAdapter = recyclerViewAdapter;

			HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
			interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

			OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
			httpClient.addInterceptor(interceptor);

			OkHttpClient client = httpClient.build();

			retrofit = new Retrofit.Builder()
					.baseUrl(CONSTANTS.host)
					.client(client)
					.build();

			restClientEvent = retrofit.create(RestClientEvent.class);
		}

		public void getDataFromServer(String value){
			restClientEvent.getCats(value).enqueue(getCallBack);
		}

		private Callback<ResponseBody> getCallBack = new Callback<ResponseBody>() {
			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				if (response != null) {
					try {
						JSONObject json = new JSONObject(response.body().string());
						if(json.has("data")){
							JSONArray dataArray = json.getJSONArray("data");
							modelList = new ArrayList<>();

							for (int i = 0; i < dataArray.length(); i++) {
								JSONObject itemObject = dataArray.getJSONObject(i);
								ItemModel model = new ItemModel();
								if(itemObject.has("title")){
									model.setTitle(itemObject.get("title").toString());
								}
								if(itemObject.has("url")){
									model.setImageUrl(itemObject.get("url").toString());
								}
								modelList.add(model);
								recyclerViewAdapter.updateData(modelList);
							}
						}
					} catch (JSONException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				G.Log(t.getMessage());
			}
		};

}
