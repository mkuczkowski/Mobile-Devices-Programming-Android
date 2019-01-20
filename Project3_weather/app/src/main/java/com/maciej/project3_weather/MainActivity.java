package com.maciej.project3_weather;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maciej.project3_weather.data.Weather;
import com.maciej.project3_weather.service.WeatherService;
import com.maciej.project3_weather.service.WeatherServiceCallback;
import com.squareup.picasso.Picasso;

import static java.text.MessageFormat.*;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallback {

    private TextView temperature;
    private TextView location;
    private TextView description;
    private TextView humidity;
    private TextView wind;
    private TextView cloudiness;
    private TextView pressure;
    private EditText input;
    private ImageView icon;

    private ProgressDialog dialog;
    private Weather weather;

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

        dialog = new ProgressDialog(this);
        dialog.setMessage("Updating weather...");
        dialog.show();
        WeatherService.callback = this;
        WeatherService.refreshWeather("Lodz");
    }

    @Override
    public void serviceRespondedSuccessfully(Weather updatedWeather) {
        dialog.hide();
        weather = updatedWeather;
        displayWeatherValues();
    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Log.e("Service Failure", exception.getMessage(), exception);
        Toast.makeText(this, "Service error", Toast.LENGTH_SHORT).show();
    }

    private void displayWeatherValues() {
        temperature.setText(format("{0} °C", weather.getTemperature()));
        location.setText(weather.getLocation());
        description.setText(weather.getDescription());
        humidity.setText(format("Humidity: {0}%", weather.getHumidity()));
        wind.setText(format("Wind speed: {0} m/s", weather.getWindSpeed()));
        cloudiness.setText(format("Cloudiness: {0}%", weather.getClouds()));
        pressure.setText(format("Atmospheric pressure: {0} hPa", weather.getPressure()));
        Picasso.get().load("http://openweathermap.org/img/w/" + weather.getIconId() + ".png").into(icon);
    }

    public void updateLocation(View view) {
        String city = input.getText().toString();
        if(!city.equals("")) WeatherService.refreshWeather(city);
    }
}