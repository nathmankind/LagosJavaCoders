package com.example.gbenga.javadevelopers.api;

import com.example.gbenga.javadevelopers.model.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by GBENGA on 9/15/2017.
 */

public interface Service {
    @GET("/search/users?q=language:java+location:lagos")
    Call<ItemResponse> getItems();
}
