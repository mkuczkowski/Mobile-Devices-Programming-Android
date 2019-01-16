package com.maciej.project3_weather;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maciej.project3_weather.data.Weather;
import com.maciej.project3_weather.service.WeatherService;
import com.maciej.project3_weather.service.WeatherServiceCallback;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallback {

    private TextView temperature;
    private TextView location;
    private TextView description;
    private TextView humidity;
    private TextView wind;
    private TextView cloudiness;
    private TextView pressure;
    private EditText input;
    private String city;
    private ImageView icon;

    private WeatherService service;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperature = findViewById(R.id.temp);
        location = findViewById(R.id.city);
        input = findViewById(R.id.cityInput);
        icon = findViewById(R.id.iconView);
        description = findViewById(R.id.desc);
        humidity = findViewById(R.id.hum);
        wind = findViewById(R.id.wind);
        cloudiness = findViewById(R.id.clouds);
        pressure = findViewById(R.id.pressure);

        service = new WeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Updating weather...");
        dialog.show();
        service.refreshWeather("Lodz");
    }

    @Override
    public void serviceSuccess(Weather weather) {
        dialog.hide();
        temperature.setText(weather.getTemperature() + " °C");
        location.setText(weather.getLocation());
        description.setText(weather.getDescription());
        humidity.setText("Humidity: " + weather.getHumidity() + "%");
        wind.setText("Wind speed: " + weather.getWindSpeed() + " m/s");
        cloudiness.setText("Cloudiness: " + weather.getClouds() + "%");
        pressure.setText("Atmospheric pressure: " + weather.getPressure() + " hPa");
        Picasso.get().load("http://openweathermap.org/img/w/" + weather.getIconVal() + ".png").into(icon);
    }

    @Override
    public void serviceFailure(Exception ex) {
        dialog.hide();
        Toast.makeText(this, "Service error", Toast.LENGTH_SHORT).show();
    }

    public void updateLocation(View view) {
        city = input.getText().toString();
        if(!city.equals("")) service.refreshWeather(city);
    }
}