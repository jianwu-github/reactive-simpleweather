package org.hellojianwu.android.simpleweather.util;

import org.hellojianwu.android.simpleweather.R;

/**
 * Utility Class to find local icon resource for OpenWeatherMap API
 *
 * Created by <a herf="mailto:hellojianwu@gmail.com">jianwu</a> on 7/29/14.
 */
public final class LocalIconFinder {

    public static int findLocalWeatherIconId(String weatherIcon) {
        if (weatherIcon.equals("01d")) {
            return R.drawable.weather_icon_01d;
        } else if (weatherIcon.equals("01n")) {
            return R.drawable.weather_icon_01n;
        } else if (weatherIcon.equals("02d")) {
            return R.drawable.weather_icon_02d;
        } else if (weatherIcon.equals("02n")) {
            return R.drawable.weather_icon_02n;
        } else if (weatherIcon.equals("03d")) {
            return R.drawable.weather_icon_03d;
        } else if (weatherIcon.equals("03n")) {
            return R.drawable.weather_icon_03n;
        } else if (weatherIcon.equals("04d")) {
            return R.drawable.weather_icon_04d;
        } else if (weatherIcon.equals("04n")) {
            return R.drawable.weather_icon_04n;
        } else if (weatherIcon.equals("09d")) {
            return R.drawable.weather_icon_09d;
        } else if (weatherIcon.equals("09n")) {
            return R.drawable.weather_icon_09n;
        } else if (weatherIcon.equals("10d")) {
            return R.drawable.weather_icon_10d;
        } else if (weatherIcon.equals("10n")) {
            return R.drawable.weather_icon_10n;
        } else if (weatherIcon.equals("11d")) {
            return R.drawable.weather_icon_11d;
        } else if (weatherIcon.equals("11n")) {
            return R.drawable.weather_icon_11n;
        } else if (weatherIcon.equals("13d")) {
            return R.drawable.weather_icon_13d;
        } else if (weatherIcon.equals("13n")) {
            return R.drawable.weather_icon_13n;
        } else if (weatherIcon.equals("50d")) {
            return R.drawable.weather_icon_50d;
        } else if (weatherIcon.equals("50n")) {
            return R.drawable.weather_icon_50n;
        }

        return R.drawable.weather_icon_01d;
    }
}
