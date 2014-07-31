package org.hellojianwu.android.simpleweather;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.hellojianwu.android.simpleweather.fragment.WeatherCardFragment;

import rx.subscriptions.CompositeSubscription;

/**
 * Weather Activity is Main Activity of SimpleWeather App
 *
 * Created by <a herf="mailto:hellojianwu@gmail.com">jianwu</a> on 7/30/14.
 */
public class WeatherActivity extends Activity {

    private static final long LOCATION_TIMEOUT_SECONDS = 15;
    public final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new WeatherCardFragment())
                    .commit();
        }
    }

    protected void onDestroy() {
        mCompositeSubscription.unsubscribe();
        super.onDestroy();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.weather, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
