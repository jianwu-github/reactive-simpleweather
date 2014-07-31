package org.hellojianwu.android.simpleweather.asyncservice;

import retrofit.RequestInterceptor;

/**
 * RequestInterceptor injecting Accept "application/json" Header
 *
 * Created by <a herf="mailto:hellojianwu@gmail.com">jianwu</a> on 7/25/14.
 */
public class RequestHeaderInterceptor implements RequestInterceptor {

    public void intercept(RequestFacade request) {
        request.addHeader("Accept", "application/json");
    }
}
