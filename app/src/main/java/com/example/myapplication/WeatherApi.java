package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//===================================================================================================
//WeatherApi
//===================================================================================================
public interface WeatherApi {
    //----------------------------------------------------------------------------------------
    //  call to API passing a string of comma separated city IDs along with other arguments
    //----------------------------------------------------------------------------------------
    @GET("group")
    Call<CityList> getUserCitiesQuery(@Query("id") String citiesID,
                                      @Query("appid") String apiKey,
                                      @Query("units") String unitMeasure);
    //----------------------------------------------------------------------------------------

    //-----------------------------------------------------------------------------
    // call to API passing a string of a city name along with other arguments
    //-----------------------------------------------------------------------------
    @GET("weather")
    Call<SingleCity> getSingleCityQuery(@Query("q") String city,
                                   @Query("appid") String apiKey,
                                   @Query("units") String unitMeasure);
    //-----------------------------------------------------------------------------
}
//===================================================================================================