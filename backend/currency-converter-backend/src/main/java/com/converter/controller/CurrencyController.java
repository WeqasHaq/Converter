package com.converter.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

@Path("/convert")
public class CurrencyController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response convert(@QueryParam("from") String from,
                            @QueryParam("to") String to,
                            @QueryParam("amount") double amount) {

        // Dummy conversion logic for testing purposes
        double conversionRate = 1.1;
        double result = amount * conversionRate;

        Map<String, Object> response = new HashMap<>();
        response.put("from", from);
        response.put("to", to);
        response.put("amount", amount);
        response.put("result", result);

        return Response.ok(response).build();
    }
}
