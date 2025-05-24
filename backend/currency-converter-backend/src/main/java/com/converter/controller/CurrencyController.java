package com.converter.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/convert")
public class CurrencyController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response convert(@QueryParam("from") String from,
                            @QueryParam("to") String to,
                            @QueryParam("amount") double amount) {

        try {
            String url = String.format("https://api.frankfurter.app/latest?amount=%s&from=%s&to=%s",
                    amount, from.toUpperCase(), to.toUpperCase());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(response.body());

            double result = json.get("rates").get(to.toUpperCase()).asDouble();

            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("from", from.toUpperCase());
            resultMap.put("to", to.toUpperCase());
            resultMap.put("amount", amount);
            resultMap.put("result", result);

            return Response.ok(resultMap).build();

        } catch (IOException | InterruptedException | NullPointerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("error", "Conversion failed or unsupported currency."))
                    .build();
        }
    }
}
