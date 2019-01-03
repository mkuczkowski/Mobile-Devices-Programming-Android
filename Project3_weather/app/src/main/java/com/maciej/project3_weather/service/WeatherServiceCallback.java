package com.maciej.project3_weather.service;

import com.maciej.project3_weather.data.Weather;

public interface WeatherServiceCallback {
    void serviceSuccess(Weather weather);
    void serviceFailure(Exception ex);
}
