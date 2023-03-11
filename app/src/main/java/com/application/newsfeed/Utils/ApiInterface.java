package com.application.newsfeed.Utils;

import com.application.newsfeed.Pojo.NewsPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Call<NewsPojo> getNewsBySpecificCountry(@Query("country") String country , @Query("pageSize") int pageSize, @Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<NewsPojo> getNewsByCategory(@Query("country") String country ,@Query("category")String category,
                                     @Query("pageSize") int pageSize,@Query("apiKey") String apiKey);
}
