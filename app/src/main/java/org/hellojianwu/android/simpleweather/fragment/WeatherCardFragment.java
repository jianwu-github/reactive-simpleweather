package org.hellojianwu.android.simpleweather.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hellojianwu.android.simpleweather.R;
import org.hellojianwu.android.simpleweather.WeatherActivity;
import org.hellojianwu.android.simpleweather.asyncservice.AsyncLocationService;
import org.hellojianwu.android.simpleweather.asyncservice.AsyncWeatherService;
import org.hellojianwu.android.simpleweather.asyncservice.GeoPoint;
import org.hellojianwu.android.simpleweather.card.WeatherCard;
import org.hellojianwu.android.simpleweather.util.PreferenceMonitor;
import org.hellojianwu.android.simpleweather.weatherdata.Weather;
import org.hellojianwu.android.simpleweather.weatherdata.WeatherData;

import java.util.concurrent.TimeUnit;

import it.gmariotti.cardslib.library.view.CardView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * WeatherCardFragment
 *
 * Created by <a herf="mailto:hellojianwu@gmail.com">jianwu</a> on 7/30/14.
 */
public class WeatherCardFragment extends Fragment {
    private static final long LOCATION_TIMEOUT_SECONDS = 7;

    WeatherCard weatherCard;
    GeoPoint currLocation;

    public GeoPoint getCurrLocation() {
        return currLocation;
    }

    public void setCurrLocation(GeoPoint currLocation) {
        this.currLocation = currLocation;
    }

    public WeatherCard initWeatherCard(WeatherData weatherData) {
        weatherCard = new WeatherCard(getActivity());
        weatherCard.setWeatherData(weatherData);
        weatherCard.init();

        return weatherCard;
    }

    public WeatherCard initWeatherCard(WeatherData weatherData, String tempUnit) {
        if (tempUnit == null || tempUnit.trim().length() == 0) {
            return initWeatherCard(weatherData);
        }

        weatherCard = new WeatherCard(getActivity());
        weatherCard.setWeatherData(weatherData);
        weatherCard.setTempUnit(tempUnit);
        weatherCard.init();

        return weatherCard;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.weathercard_fragment, container, false);

        CardView cardView = (CardView) rootView.findViewById(R.id.weathercard_fragment_cardview);

        updateWeatherByLoc(cardView);

        return rootView;
    }

    private void updateWeatherByLoc(final CardView cardView) {
        WeatherActivity parentActivity = (WeatherActivity)getActivity();

        final LocationManager locationManager = (LocationManager) parentActivity.getSystemService(Context.LOCATION_SERVICE);
        final AsyncLocationService locationService = new AsyncLocationService(locationManager);

        final Observable<Observable<WeatherData>> currWeatherObservable = locationService
                .getLocation()
                .timeout(LOCATION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .map(new Func1<Location, Observable<WeatherData>>() {
                    public Observable<WeatherData> call(Location location) {
                        final double longitude = location.getLongitude();
                        final double latitude = location.getLatitude();
                        final GeoPoint geoPoint = new GeoPoint(longitude, latitude);
                        final AsyncWeatherService weatherService = new AsyncWeatherService();

                        setCurrLocation(geoPoint);

                        return weatherService.getCurrentWeatherInMetric(geoPoint);
                    }
                });


        parentActivity.mCompositeSubscription
                .add(currWeatherObservable
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(new Subscriber<Observable<WeatherData>>(){

                            public void onCompleted() {
                                Log.i("WeatherCardFragment", "WeatherData Subscriber.onCompleted");
                            }

                            public void onError(Throwable e) {
                                Log.e("WeatherCardFragment", "WeatherData Subscriber.onError", e);
                            }

                            public void onNext(Observable<WeatherData> weatherDataObservable) {
                                weatherDataObservable
                                        .subscribeOn(Schedulers.newThread())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Action1<WeatherData>() {
                                            public void call(WeatherData weatherData) {
                                                WeatherCard weatherCard = initWeatherCard(weatherData);

                                                cardView.setCard(weatherCard);
                                            }
                                        });
                            }
                        }));
    }

    public void onResume() {
        WeatherActivity parentActivity = (WeatherActivity)getActivity();
        CardView cardView = (CardView) parentActivity.findViewById(R.id.weathercard_fragment_cardview);

        if (PreferenceMonitor.getInstance().isPreferenceChanged()) {
            String currTempUnit = PreferenceMonitor.getInstance().getTempUnit();

            refreshWeatherByLoc(parentActivity, currTempUnit, cardView);

            PreferenceMonitor.getInstance().setPreferenceChanged(false);
        }

        super.onResume();
    }

    private void refreshWeatherByLoc(WeatherActivity parentActivity, final String currTempUnit, final CardView cardView) {
        final AsyncWeatherService weatherService = new AsyncWeatherService();

        if (currTempUnit.equals("metric")) {
            Observable<WeatherData> observable = weatherService.getCurrentWeatherInMetric(getCurrLocation());
            observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<WeatherData>() {
                        public void call(WeatherData weatherData) {
                            WeatherCard weatherCard = initWeatherCard(weatherData, currTempUnit);
                            cardView.refreshCard(weatherCard);
                            cardView.refreshDrawableState();
                        }
                    });
        } else if (currTempUnit.equals("imperial")) {
            Observable<WeatherData> observable = weatherService.getCurrentWeatherInImperial(getCurrLocation());
            observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<WeatherData>() {
                        public void call(WeatherData weatherData) {
                            WeatherCard weatherCard = initWeatherCard(weatherData, currTempUnit);
                            cardView.refreshCard(weatherCard);
                            cardView.refreshDrawableState();
                        }
                    });
        }
    }

}
