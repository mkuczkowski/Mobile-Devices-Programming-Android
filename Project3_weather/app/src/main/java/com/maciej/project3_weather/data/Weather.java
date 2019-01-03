package com.maciej.project3_weather.data;

import org.json.JSONObject;

public class Weather implements JSONPopulator {
    private Temperature temperature;
    private String location;

    public Temperature getTemperature() {
        return temperature;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = new Temperature();
        temperature.populate(data.optJSONObject("main"));
        location = data.optString("name");
    }
}
