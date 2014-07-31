package org.hellojianwu.android.simpleweather.asyncservice;

import org.hellojianwu.android.simpleweather.weatherdata.ForecastData;
import org.hellojianwu.android.simpleweather.weatherdata.WeatherData;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * OpenWeatherMap REST JSON Service
 *
 * Created by <a herf="mailto:hellojianwu@gmail.com">jianwu</a> on 7/25/14.
 */
public interface OpenWeatherServiceApi {
    @GET("/weather?units=metric")
    WeatherData currentWeather(@Query("lon") double longitude, @Query("lat") double latitude);

    @GET("/forecast/daily?units=metric&cnt=7")
    ForecastData weeklyForecast(@Query("lon") double longitude, @Query("lat") double latitude);
}
