package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("group")
    Call<CityList> getUserCitiesQuery(@Query("id") String citiesID,
                                      @Query("appid") String apiKey,
                                      @Query("units") String unitMeasure);

    @GET("weather")
    Call<SingleCity> getSingleCityQuery(@Query("q") String city,
                                   @Query("appid") String apiKey,
                                   @Query("units") String unitMeasure);
}
