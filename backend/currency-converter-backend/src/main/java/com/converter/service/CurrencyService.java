package com.converter.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CurrencyService {

    public static double convert(String from, String to, double amount) {
        try {
            String url = String.format("https://api.frankfurter.app/latest?from=%s&to=%s&amount=%s",
                    from, to, amount);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(response.body());

            return json.get("result").asDouble();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return 0.0; // or handle appropriately
        }
    }
}
