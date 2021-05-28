package com.ducatillon.graphqlclient.client;


import static org.junit.Assert.assertEquals;

import com.ducatillon.graphqlclient.data.CountryDto;
import com.ducatillon.graphqlclient.data.CountryDto.CountryData;
import com.ducatillon.graphqlclient.data.CountryDto.CountryData.Country;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

class CountryClientTest {

  public static MockWebServer mockBackEnd;
  private CountryClient countryClient;
  private ObjectMapper mapper = new ObjectMapper();


  @BeforeAll
  static void setUp() throws IOException {
    mockBackEnd = new MockWebServer();
    mockBackEnd.start();
  }

  @AfterAll
  static void tearDown() throws IOException {
    mockBackEnd.shutdown();
  }

  @BeforeEach
  void initialize() {
    String baseUrl = String.format("http://localhost:%s",
        mockBackEnd.getPort());
    countryClient = new CountryClient(baseUrl);
  }

  @Test
  void shouldReturnCountryDetails() throws JsonProcessingException, InterruptedException {

    Country countryMock = new Country("Belgium", "Brussels", "EUR");
    CountryData countryDataMock = new CountryData(countryMock);
    CountryDto countryDtoMock = new CountryDto(countryDataMock);

    mockBackEnd.enqueue(new MockResponse().setBody(mapper.writeValueAsString(countryDtoMock))
        .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

    CountryDto countryDtoMono = countryClient.getCountryDetails("BE");

    RecordedRequest recordedRequest = mockBackEnd.takeRequest();

    assertEquals("POST", recordedRequest.getMethod());
    assertEquals("Belgium", countryDtoMono.getData().getCountry().getName());
    assertEquals("Brussels", countryDtoMono.getData().getCountry().getCapital());
    assertEquals("EUR", countryDtoMono.getData().getCountry().getCurrency());

  }

}
