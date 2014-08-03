
package org.hellojianwu.android.simpleweather.weatherdata;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Main {
    // "main":{"temp":21.821,"temp_min":21.821,"temp_max":21.821,"pressure":1014.23,"sea_level":1026.01,"grnd_level":1014.23,"humidity":82}

    private Double temp;
    private Integer humidity;
    private Double pressure;

    @SerializedName("temp_min")
    private Double tempMin;

    @SerializedName("temp_max")
    private Double tempMax;

    @SerializedName("sea_level")
    private Double seaLevel;

    @SerializedName("grnd_level")
    private Double groundLevel;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public Double getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(Double seaLevel) {
        this.seaLevel = seaLevel;
    }

    public Double getGroundLevel() {
        return groundLevel;
    }

    public void setGroundLevel(Double groundLevel) {
        this.groundLevel = groundLevel;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
