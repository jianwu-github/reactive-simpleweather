
package org.hellojianwu.android.simpleweather.weatherdata;

import java.util.HashMap;
import java.util.Map;


public class Rain {

    private Integer _3h;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer get3h() {
        return _3h;
    }

    public void set3h(Integer _3h) {
        this._3h = _3h;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
