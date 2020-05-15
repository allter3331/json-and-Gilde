package com.t3h.jsonandshowimage.network;

import com.t3h.jsonandshowimage.ModelTest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @GET("api/getAllStoryOfCategoryV2/EN/Action")
    Call<List<ModelTest>> getStorys();
}
