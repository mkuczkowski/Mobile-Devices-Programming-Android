package com.maciej.project3_weather.data;

import org.json.JSONObject;

public class Temperature implements JSONPopulator {
    private int temperature;

    public int getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optInt("temp");
    }
}
