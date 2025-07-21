package br.alura.LiterAlura.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Component;

@Component
public class ConsumirAPI {

  private final String BASE_URL = "https://gutendex.com";

  public String fetch(String path) {
    HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + path))
        .build();

    HttpResponse<String> response = null;

    try {
      response = client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException | InterruptedException e) {
      System.out.println("Erro ao consumir a API: " + e.getMessage());
      e.printStackTrace();
    }

    int statusCode = response.statusCode();
    if (statusCode != 200) {
      throw new RuntimeException("Erro ao consumir a API: status " + statusCode);
    }

    String body = response.body();
    return body;
  }
}
