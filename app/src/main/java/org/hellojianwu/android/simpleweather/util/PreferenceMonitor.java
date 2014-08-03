package org.hellojianwu.android.simpleweather.util;

import android.content.SharedPreferences;
import android.util.Log;

import org.hellojianwu.android.simpleweather.R;

/**
 * PreferenceMonitor is used to tracking the Change of Shared Preference
 *
 * Created by <a herf="mailto:hellojianwu@gmail.com">jianwu</a> on 8/2/14.
 */
public class PreferenceMonitor implements SharedPreferences.OnSharedPreferenceChangeListener {
    private static final PreferenceMonitor preferenceMonitor = new PreferenceMonitor();

    public static final PreferenceMonitor getInstance() {
        return preferenceMonitor;
    }

    private boolean preferenceChanged;
    private String tempUnit;

    private PreferenceMonitor() {
        preferenceChanged = false;
        tempUnit = "metric";
    }

    public boolean isPreferenceChanged() {
        return preferenceChanged;
    }

    public void setPreferenceChanged(boolean preferenceChanged) {
        this.preferenceChanged = preferenceChanged;
    }

    public String getTempUnit() {
        return tempUnit;
    }

    public void setTempUnit(String tempUnit) {
        this.tempUnit = tempUnit;
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Log.i("PreferenceMonitor", "s: " + s);

        String unitKey = "units";
        String unitValue = sharedPreferences.getString(unitKey, "metric");

        Log.i("PreferenceMonitor", "unitKey: " + unitKey);
        Log.i("PreferenceMonitor", "unitValue: " + unitValue);

        if (!unitValue.equals(tempUnit)) {
            tempUnit = unitValue;
            preferenceChanged = true;
        }
    }
}
