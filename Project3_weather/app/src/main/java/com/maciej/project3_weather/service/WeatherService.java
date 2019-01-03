package com.maciej.project3_weather.service;

import android.os.AsyncTask;

import com.maciej.project3_weather.data.Weather;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidParameterException;

public class WeatherService {
    private WeatherServiceCallback callback;
    private Exception error;

    public WeatherService(WeatherServiceCallback callback) {
        this.callback = callback;
    }

    public void refreshWeather(final String location) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... str) {
                String endpoint = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=APIKEY&units=metric", location);
                try {
                    URL url = new URL(endpoint);
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    return result.toString();
                } catch (Exception e) {
                    error = e;
                }
                return null;
            }
            @Override
            protected void onPostExecute(String s) {
                if(s == null && error != null) {
                    callback.serviceFailure(error);
                    return;
                }
                try {
                    JSONObject data = new JSONObject(s);
                    int codVal = data.optInt("cod");
                    if(codVal == 404) {
                        throw new InvalidParameterException("City not found!");
                    }
                    Weather weather = new Weather();
                    weather.populate(data);
                    callback.serviceSuccess(weather);
                } catch (Exception e) {
                    error = e;
                    callback.serviceFailure(error);
                }
            }
        }.execute(location);
    }
}