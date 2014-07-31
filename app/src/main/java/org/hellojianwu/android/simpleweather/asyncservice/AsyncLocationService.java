package org.hellojianwu.android.simpleweather.asyncservice;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;

import rx.Observable;
import rx.Subscriber;

/**
 * Accessing Location info with RxJava
 *
 * Created by <a herf="mailto:hellojianwu@gmail.com">jianwu</a> on 7/25/14.
 */
public class AsyncLocationService {
    private final LocationManager locationManager;

    public AsyncLocationService(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    public Observable<Location> getLocation() {
        return Observable.create(new Observable.OnSubscribe<Location>() {

            public void call(final Subscriber<? super Location> subscriber) {

                final LocationListener locationListener = new LocationListener() {
                    public void onLocationChanged(final Location location) {
                        subscriber.onNext(location);
                        subscriber.onCompleted();

                        Looper.myLooper().quit();
                    }

                    public void onStatusChanged(String provider, int status, Bundle extras) { }
                    public void onProviderEnabled(String provider) { }
                    public void onProviderDisabled(String provider) { }
                };

                final Criteria locationCriteria = new Criteria();
                locationCriteria.setAccuracy(Criteria.ACCURACY_FINE);
                locationCriteria.setPowerRequirement(Criteria.POWER_LOW);
                final String locationProvider = locationManager
                        .getBestProvider(locationCriteria, true);

                Looper.prepare();

                locationManager.requestSingleUpdate(locationProvider,
                        locationListener, Looper.myLooper());

                Looper.loop();
            }
        });
    }
}
