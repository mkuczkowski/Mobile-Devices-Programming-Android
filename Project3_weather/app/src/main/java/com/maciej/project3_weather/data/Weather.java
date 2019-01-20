package com.maciej.project3_weather.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Weather implements JSONPopulator {
    private String location;
    private String iconId;
    private String description;
    private int humidity;
    private double windSpeed;
    private int clouds;
    private int pressure;
    private int temperature;

    public int getTemperature() { return temperature; }
    public String getLocation() { return location; }
    public String getIconId() { return iconId; }
    public String getDescription() { return description; }
    public int getHumidity() { return humidity; }
    public double getWindSpeed() { return windSpeed; }
    public int getClouds() { return clouds; }
    public int getPressure() { return pressure; }

    @Override
    public void populate(JSONObject data) {
        location = data.optString("name");
        JSONArray weather = data.optJSONArray("weather");
        try {
            temperature = data.getJSONObject("main").getInt("temp");
            iconId = weather.getJSONObject(0).getString("icon");
            description = weather.getJSONObject(0).getString("description");
            humidity = data.getJSONObject("main").getInt("humidity");
            windSpeed = data.getJSONObject("wind").getDouble("speed");
            clouds = data.getJSONObject("clouds").getInt("all");
            pressure = data.getJSONObject("main").getInt("pressure");
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
    }
}