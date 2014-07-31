package org.hellojianwu.android.simpleweather.card;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.hellojianwu.android.simpleweather.R;
import org.hellojianwu.android.simpleweather.util.LocalIconFinder;
import org.hellojianwu.android.simpleweather.weatherdata.Main;
import org.hellojianwu.android.simpleweather.weatherdata.Sys;
import org.hellojianwu.android.simpleweather.weatherdata.Weather;
import org.hellojianwu.android.simpleweather.weatherdata.WeatherData;
import org.hellojianwu.android.simpleweather.weatherdata.Wind;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.prototypes.CardWithList;

/**
 * WeatherCard to display weather like Google Now Weather Card
 *
 * Created by <a herf="mailto:hellojianwu@gmail.com">jianwu</a> on 7/28/14.
 */
public class WeatherCard extends CardWithList {

    private WeatherData weatherData;

    public WeatherCard(Context context) {
        super(context);
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    protected CardHeader initCardHeader() {
        //Add Header
        CardHeader header = new CardHeader(getContext(), R.layout.weathercard_inner_header);

        if (weatherData != null && weatherData.getName() != null) {
            header.setTitle(weatherData.getName());
        } else {
            // TODO: hard-coded default title for now
            header.setTitle("Weather");
        }

        return header;
    }

    protected void initCard() {
        // Anything to do here?
    }

    protected List<ListObject> initChildren() {
        if (weatherData != null) {
            //Init the list
            List<ListObject> weatherCardItemList = new ArrayList<ListObject>();

            Weather weather = weatherData.getWeather().get(0);
            Main main = weatherData.getMain();
            Wind wind = weatherData.getWind();
            Sys sys = weatherData.getSys();

            // Weather
            WeatherCardItem weatherCardItem1 = new WeatherCardItem(this);
            weatherCardItem1.weatherCardItemIcon = LocalIconFinder.findLocalWeatherIconId(weather.getIcon());
            weatherCardItem1.weatherCardItemText1 = weather.getMain();
            weatherCardItem1.weatherCardItemText2 = weather.getDescription();
            weatherCardItemList.add(weatherCardItem1);

            // Temperature
            WeatherCardItem weatherCardItem2 = new WeatherCardItem(this);
            weatherCardItem2.weatherCardItemIcon = R.drawable.temperature;
            weatherCardItem2.weatherCardItemText1 = main.getTemp().toString() + "°C";
            if (main.getTempMax() != null && main.getTempMin() != null) {
                weatherCardItem2.weatherCardItemText2 = "( " + main.getTempMin().toString() + "°C - " + main.getTempMax().toString() + "°C )";
            } else {
                weatherCardItem2.weatherCardItemText2 = "";
            }
            weatherCardItemList.add(weatherCardItem2);

            // Wind
            WeatherCardItem weatherCardItem3 = new WeatherCardItem(this);
            weatherCardItem3.weatherCardItemIcon = R.drawable.wind;
            weatherCardItem3.weatherCardItemText1 = wind.getSpeed() + "km/h  " + wind.getDeg() + "°";
            weatherCardItem3.weatherCardItemText2 = "";
            weatherCardItemList.add(weatherCardItem3);

            // Humidity
            WeatherCardItem weatherCardItem4 = new WeatherCardItem(this);
            weatherCardItem4.weatherCardItemIcon = R.drawable.humidity;
            weatherCardItem4.weatherCardItemText1 = main.getHumidity().toString() + "%";
            weatherCardItem4.weatherCardItemText2 = "";
            weatherCardItemList.add(weatherCardItem4);

            // Pressure
            WeatherCardItem weatherCardItem5 = new WeatherCardItem(this);
            weatherCardItem5.weatherCardItemIcon = R.drawable.pressure;
            weatherCardItem5.weatherCardItemText1 = main.getPressure().toString() + "mb";
            weatherCardItem5.weatherCardItemText2 = "";
            weatherCardItemList.add(weatherCardItem5);

            // Sunrise
            Date sunRise = new Date(sys.getSunrise());
            SimpleDateFormat sunRiseFormat = new SimpleDateFormat("KK:mm");

            WeatherCardItem weatherCardItem6 = new WeatherCardItem(this);
            weatherCardItem6.weatherCardItemIcon = R.drawable.sun;
            weatherCardItem6.weatherCardItemText1 = sunRiseFormat.format(sunRise) + "am";
            weatherCardItem6.weatherCardItemText2 = "";
            weatherCardItemList.add(weatherCardItem6);

            // Sunset
            Date sunSet = new Date(sys.getSunset());
            SimpleDateFormat sunSetFormat = new SimpleDateFormat("KK:mm");

            WeatherCardItem weatherCardItem7 = new WeatherCardItem(this);
            weatherCardItem7.weatherCardItemIcon = R.drawable.moon;
            weatherCardItem7.weatherCardItemText1 = sunSetFormat.format(sunSet) + "pm";
            weatherCardItem7.weatherCardItemText2 = "";
            weatherCardItemList.add(weatherCardItem7);

            return weatherCardItemList;
        }

        return null;
    }

    public View setupChildView(int i, ListObject listObject, View view, ViewGroup viewGroup) {
        WeatherCardItem item = (WeatherCardItem)listObject;

        ImageView iconImage = (ImageView)view.findViewById(R.id.weather_card_item_icon);
        TextView text1 = (TextView)view.findViewById(R.id.weather_card_item_text1);
        TextView text2 = (TextView)view.findViewById(R.id.weather_card_item_text2);

        iconImage.setImageResource(item.weatherCardItemIcon);
        text1.setText(item.weatherCardItemText1);
        text2.setText(item.weatherCardItemText2);

        return view;
    }

    public int getChildLayoutId() {
        return R.layout.weathercard_inner_main;
    }

    public class WeatherCardItem extends DefaultListObject {
        public int weatherCardItemIcon;
        public String weatherCardItemText1;
        public String weatherCardItemText2;

        public WeatherCardItem(Card parentCard) {
            super(parentCard);
        }
    }
}
