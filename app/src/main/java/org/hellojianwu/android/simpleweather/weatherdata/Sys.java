
package org.hellojianwu.android.simpleweather.weatherdata;

import java.util.HashMap;
import java.util.Map;

public class Sys {

    private String country;
    private Integer sunrise;
    private Integer sunset;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getSunrise() {
        return sunrise;
    }

    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public Integer getSunset() {
        return sunset;
    }

    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
