package com.weatherapp.model;

public class WeatherData {
    private double temperature;
    private double windspeed;
    private double humidity;
    private double precipitation;

    public WeatherData(double temperature, double windspeed,
                       double humidity, double precipitation) {
        this.temperature = temperature;
        this.windspeed = windspeed;
        this.humidity = humidity;
        this.precipitation = precipitation;
    }

    public double getTemperature() { return temperature; }
    public double getWindspeed() { return windspeed; }
    public double getHumidity() { return humidity; }
    public double getPrecipitation() { return precipitation; }
}