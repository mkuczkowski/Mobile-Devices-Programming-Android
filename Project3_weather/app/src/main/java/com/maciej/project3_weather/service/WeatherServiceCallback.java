package com.maciej.project3_weather.service;

import com.maciej.project3_weather.data.Weather;

public interface WeatherServiceCallback {
    void serviceRespondedSuccessfully(Weather updatedWeather);
    void serviceFailure(Exception exception);
}