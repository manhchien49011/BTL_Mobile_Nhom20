package com.example.btl_nhom20.api;

//import androidx.room.Query;

import com.example.btl_nhom20.model.weather.OpenWeatherMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiWeather {
    // link api : https://api.openweathermap.org/data/2.5/weather?lat=20.8561&lon=106.6822&appid=a1daceff8dd48f26306afa051bf0ae81

    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyyy HH:mm:ss")
            .create();

 ApiWeather apiWeather = new Retrofit.Builder()
         .baseUrl("https://api.openweathermap.org")
         .addConverterFactory(GsonConverterFactory.create(gson))
         .build()
         .create(ApiWeather.class);
    @GET("/data/2.5/weather")
    Call<OpenWeatherMap> openWeatherMap(@Query("lat") double lat,
                                        @Query("lon") double lon,
                                        @Query("appid") String key);
}
