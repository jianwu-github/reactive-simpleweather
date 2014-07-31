
package org.hellojianwu.android.simpleweather.weatherdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ForecastData {

    private String cod;
    private Double message;
    private City city;
    private Integer cnt;
    private java.util.List<Forecast> forecast = new ArrayList<Forecast>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(java.util.List<Forecast> forecast) {
        this.forecast = forecast;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
