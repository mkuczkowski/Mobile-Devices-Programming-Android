package com.maciej.project3_weather.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Weather implements JSONPopulator {
    private Temperature temperature;
    private String location;
    private String iconVal;
    private String description;
    private int humidity;
    public Temperature getTemperature() { return temperature; }
    public String getLocation() { return location; }
    public String getIconVal() { return iconVal; }
    public String getDescription() { return description; }
    public int getHumidity() { return humidity; }

    @Override
    public void populate(JSONObject data) {
        temperature = new Temperature();
        temperature.populate(data.optJSONObject("main"));
        location = data.optString("name");
        JSONArray weather = data.optJSONArray("weather");
        try {
            iconVal = weather.getJSONObject(0).getString("icon");
            description = weather.getJSONObject(0).getString("description");
            humidity = data.getJSONObject("main").getInt("humidity");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}