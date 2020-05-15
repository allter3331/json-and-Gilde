package com.t3h.jsonandshowimage.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {
    static Retrofit retrofit;
    private static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient();
            client.dispatcher().setMaxRequests(8);
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://144.202.9.52/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public static RetrofitInterface getService() {
        return getRetrofitInstance().create(RetrofitInterface.class);
    }
}
