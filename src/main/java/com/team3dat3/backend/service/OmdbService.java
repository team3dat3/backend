package com.team3dat3.backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team3dat3.backend.dto.omdb.OmdbResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class OmdbService {



  @Value("${omdb.api.url}")
  private String apiUrl;

  @Value("${omdb.api.key}")
  private String apiKey;



  public List<OmdbResponse> lookupAPI(String title) {

    List<OmdbResponse> responses = new ArrayList<>();
    try {
      URL url = new URL(String.format("%s?apikey=%s&t=%s", apiUrl, apiKey, title));
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestProperty("Content-Type", "application/json");
      InputStream responseStream = connection.getInputStream();

      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode rootNode = objectMapper.readTree(responseStream);

      if (rootNode.has("Error")) {
        throw new IOException(rootNode.get("Error").asText());
      }

      OmdbResponse response = parseJsonResponse(rootNode.toString());
      responses.add(response);

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return responses;
  }


  public OmdbResponse lookupAPIId(String imdbId) {

    OmdbResponse response = new OmdbResponse();
    try {

      URL url = new URL(String.format("%s/?apikey=%s&i=%s", apiUrl, apiKey, imdbId));
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setRequestProperty("Content-Type", "application/json");
      InputStream responseStream = connection.getInputStream();

      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode rootNode = objectMapper.readTree(responseStream);

      if (rootNode.has("Error")) {
        throw new IOException(rootNode.get("Error").asText());
      }

      response = parseJsonResponse(rootNode.toString());


    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return response;
  }

  public OmdbResponse parseJsonResponse(String json) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode rootNode = objectMapper.readTree(json);

    String title = rootNode.get("Title").asText();
    String director = rootNode.get("Director").asText();
    String actors = rootNode.get("Actors").asText();
    int year = rootNode.get("Year").asInt();
    String rated = rootNode.get("Rated").asText();
    String genre = rootNode.get("Genre").asText();
    String plot = rootNode.get("Plot").asText();
    String runtime = rootNode.get("Runtime").asText();
    String poster = rootNode.get("Poster").asText();
    String imdbId = rootNode.get("imdbID").asText();


    return new OmdbResponse(title, director, actors, year, rated, genre, plot, runtime, poster, imdbId);
  }
}
