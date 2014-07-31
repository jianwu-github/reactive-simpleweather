package org.hellojianwu.android.simpleweather.asyncservice;

/**
 * POJO to capture geo location
 *
 * Created by <a herf="mailto:hellojianwu@gmail.com">jianwu</a> on 7/25/14.
 */
public class GeoPoint {

    private double longitude;
    private double latitude;

    public GeoPoint() {
    }

    public GeoPoint(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


}
