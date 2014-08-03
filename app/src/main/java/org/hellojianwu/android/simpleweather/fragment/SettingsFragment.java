package org.hellojianwu.android.simpleweather.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

import org.hellojianwu.android.simpleweather.R;

/**
 * Created by <a herf="mailto:hellojianwu@gmail.com">jianwu</a> on 8/1/14.
 */
public class SettingsFragment extends PreferenceFragment {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
    }
}
