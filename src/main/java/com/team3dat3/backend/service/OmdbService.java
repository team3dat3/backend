package com.team3dat3.backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team3dat3.backend.dto.omdb.OmdbResponse;
import org.springframework.beans.factory.annotation.Autowired;
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


  public List<OmdbResponse> lookupAPI(String title, int prodYear) {

    List<OmdbResponse> responses = new ArrayList<>();
    try {

      URL url = new URL(String.format("%s/?apikey=%s&t=%s&y=%d", apiUrl, apiKey, title, prodYear));
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setRequestProperty("Content-Type", "application/json");
      InputStream responseStream = connection.getInputStream();

      TypeReference<List<OmdbResponse>> typeRef = new TypeReference<List<OmdbResponse>>() {};
      responses = new ObjectMapper().readValue(responseStream, typeRef);

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return responses;
  }
}
