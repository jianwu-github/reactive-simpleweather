package org.hellojianwu.android.simpleweather.asyncservice;

import org.hellojianwu.android.simpleweather.weatherdata.ForecastData;
import org.hellojianwu.android.simpleweather.weatherdata.WeatherData;

import retrofit.RestAdapter;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Access OpenWeatherMap Service using Retrofit with RxJava
 *
 * Created by <a herf="mailto:hellojianwu@gmail.com">jianwu</a> on 7/25/14.
 */
public class AsyncWeatherService {
    private static final String OPEN_WEATHER_MAP_SERVICE_BASE_URL = "http://api.openweathermap.org/data/2.5";

    private OpenWeatherServiceApi openWeatherService;

    public AsyncWeatherService() {
        RestAdapter restAdapter = new RestAdapter
                                        .Builder()
                                        .setEndpoint(OPEN_WEATHER_MAP_SERVICE_BASE_URL)
                                        .setRequestInterceptor(new RequestHeaderInterceptor())
                                        .setLogLevel(RestAdapter.LogLevel.FULL)
                                        .build();

        openWeatherService = restAdapter.create(OpenWeatherServiceApi.class);
    }

    public Observable<WeatherData> getCurrentWeather(final GeoPoint geoPoint) {
        return Observable.create(new Observable.OnSubscribe<WeatherData>() {
            public void call(Subscriber<? super WeatherData> subscriber) {
                try {
                    subscriber.onNext(openWeatherService.currentWeather(geoPoint.getLongitude(), geoPoint.getLatitude()));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public Observable<ForecastData> get7DayForecasts(final GeoPoint geoPoint) {
        return Observable.create(new Observable.OnSubscribe<ForecastData>() {
            public void call(Subscriber<? super ForecastData> subscriber) {
                try {
                    subscriber.onNext(openWeatherService.weeklyForecast(geoPoint.getLongitude(), geoPoint.getLatitude()));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}
