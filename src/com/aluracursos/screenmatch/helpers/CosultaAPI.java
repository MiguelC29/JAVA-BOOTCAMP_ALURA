package com.aluracursos.screenmatch.helpers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CosultaAPI {
    private final String apiKey = "9c051dd9";
    private String url;
    private String json;

    public CosultaAPI(String busqueda) {
        this.url = String.format("https://www.omdbapi.com/?apikey=%s&t=%s",
                apiKey, busqueda.replace(" ", "+"));
    }

    public void consultarTitulos() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.url))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        this.json = response.body();
    }

    public String getJson() {
        return json;
    }
}
