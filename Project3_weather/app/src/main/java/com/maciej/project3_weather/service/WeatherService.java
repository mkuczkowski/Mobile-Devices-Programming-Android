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
    public static WeatherServiceCallback callback;
    private static Exception error;
    private static final String URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";
    private static final String API_KEY = "API_KEY";

    public WeatherService(WeatherServiceCallback callback) { WeatherService.callback = callback; }

    public static void refreshWeather(final String location) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... taskParameters) {
                String urlAddress = String.format(URL, location, API_KEY);
                try {
                    URL url = new URL(urlAddress);
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String currentLine;
                    while((currentLine = reader.readLine()) != null) {
                        result.append(currentLine);
                    }
                    return result.toString();
                } catch (Exception exception) {
                    error = exception;
                }
                return null;
            }
            @Override
            protected void onPostExecute(String receivedResult) {
                if(receivedResult == null && error != null) {
                    callback.serviceFailure(error);
                    return;
                }
                try {
                    JSONObject jsonData = new JSONObject(receivedResult);
                    int cityId = jsonData.optInt("cod");
                    if(cityId == 404) {
                        throw new InvalidParameterException("City not found!");
                    }
                    Weather weather = new Weather();
                    weather.populate(jsonData);
                    callback.serviceRespondedSuccessfully(weather);
                } catch (Exception exception) {
                    error = exception;
                    callback.serviceFailure(error);
                }
            }
        }.execute(location);
    }
}