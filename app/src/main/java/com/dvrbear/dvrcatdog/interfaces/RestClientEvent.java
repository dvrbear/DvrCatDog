package com.dvrbear.dvrcatdog.interfaces;

import com.dvrbear.dvrcatdog.utils.CONSTANTS;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestClientEvent {

    @GET(CONSTANTS.endpoint)
    Call<ResponseBody> getCats(@Query(CONSTANTS.query) String value);

}
