package com.weatherapp;
import com.weatherapp.model.WeatherData;
import com.weatherapp.service.WeatherService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WeatherService service = new WeatherService();

        System.out.print("Inserisci una città: ");
        String city = scanner.nextLine();

        try {
            WeatherData data = service.getWeather(city);

            System.out.println("\n🌍 Meteo per " + city + ":");
            System.out.println("🌡 Temperatura: " + data.getTemperature() + "°C");
            System.out.println("💨 Vento: " + data.getWindspeed() + " km/h");
            System.out.println("💧 Umidità: " + data.getHumidity() + "%");
            System.out.println("🌧 Precipitazioni: " + data.getPrecipitation() + " mm");

        } catch (Exception e) {
            System.out.println("❌ Errore: " + e.getMessage());
        }

        scanner.close();
    }
}