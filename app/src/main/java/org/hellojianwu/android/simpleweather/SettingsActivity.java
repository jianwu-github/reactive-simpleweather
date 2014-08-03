package org.hellojianwu.android.simpleweather;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import org.hellojianwu.android.simpleweather.fragment.SettingsFragment;

/**
 * Created by <a herf="mailto:hellojianwu@gmail.com">jianwu</a> on 8/1/14.
 */
public class SettingsActivity extends PreferenceActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new SettingsFragment()).commit();
    }
}
