package com.weatherapp.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.weatherapp.model.WeatherData;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherService {

    private final HttpClient client = HttpClient.newHttpClient();

    /**
 * Recupera i dati meteo correnti per una città specificata.
 *
 * <p>Il metodo esegue due chiamate API:
 * una per ottenere le coordinate della città e una per recuperare i dati meteo.</p>
 *
 * @param city il nome della città (es. "Ariccia")
 * @return un oggetto {@code WeatherData} con temperatura e vento
 * @throws RuntimeException se la città non viene trovata
 * @throws Exception se si verifica un errore nella richiesta HTTP
 *
 * <p><b>Esempio di utilizzo:</b></p>
 * <pre>
 * WeatherService service = new WeatherService();
 * WeatherData data = service.getWeather("Ariccia");
 * System.out.println(data.getTemperature());
 * </pre>
 */
    public WeatherData getWeather(String city) throws Exception {

        // 1. Geocoding (città → coordinate)
        String geoUrl = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + "&count=1";

        String geoResponse = sendRequest(geoUrl);

        JsonObject geoJson = JsonParser.parseString(geoResponse).getAsJsonObject();
        JsonArray results = geoJson.getAsJsonArray("results");

        if (results == null || results.size() == 0) {
            throw new RuntimeException("Città non trovata!");
        }

        JsonObject firstResult = results.get(0).getAsJsonObject();

        double latitude = firstResult.get("latitude").getAsDouble();
        double longitude = firstResult.get("longitude").getAsDouble();

        // 2. Meteo (coordinate → dati meteo)
        String weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude="
        + latitude
        + "&longitude=" + longitude
        + "&current_weather=true"
        + "&hourly=relativehumidity_2m,precipitation";

        String weatherResponse = sendRequest(weatherUrl);

        JsonObject weatherJson = JsonParser.parseString(weatherResponse).getAsJsonObject();
        // Dati correnti
JsonObject current = weatherJson.getAsJsonObject("current_weather");
double temperature = current.get("temperature").getAsDouble();
double windspeed = current.get("windspeed").getAsDouble();

JsonObject hourly = weatherJson.getAsJsonObject("hourly");

// Array
var times = hourly.getAsJsonArray("time");
var humidityArray = hourly.getAsJsonArray("relativehumidity_2m");
var precipitationArray = hourly.getAsJsonArray("precipitation");

// Ora corrente
LocalDateTime now = LocalDateTime.now();

// Formatter API
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

// Trova indice più vicino
int closestIndex = 0;
long minDiff = Long.MAX_VALUE;

for (int i = 0; i < times.size(); i++) {
    String timeStr = times.get(i).getAsString();
    LocalDateTime apiTime = LocalDateTime.parse(timeStr, formatter);

    long diff = Math.abs(java.time.Duration.between(now, apiTime).toMinutes());

    if (diff < minDiff) {
        minDiff = diff;
        closestIndex = i;
    }
}



double humidity = hourly.getAsJsonArray("relativehumidity_2m")
                        .get(0).getAsDouble();

double precipitation = hourly.getAsJsonArray("precipitation")
                             .get(0).getAsDouble();
        return new WeatherData(temperature, windspeed, humidity, precipitation);
    }

    private String sendRequest(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}