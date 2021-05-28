package com.ducatillon.graphqlclient.client;

import com.ducatillon.graphqlclient.data.CountryDto;
import com.ducatillon.graphqlclient.data.GraphqlRequestBody;
import com.ducatillon.graphqlclient.util.GraphqlSchemaReaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class CountryClient {

  private final WebClient webClient;

  public CountryClient(@Value("https://countries.trevorblades.com/") String url) {
    this.webClient = WebClient.builder().baseUrl(url)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .filter(logRequest())
        .build();
  }

  public CountryDto getCountryDetails(final String countryCode) {

    GraphqlRequestBody graphQLRequestBody = new GraphqlRequestBody();

    final String query = GraphqlSchemaReaderUtil.getSchemaFromFile("getCountryDetails");
    final String variables = GraphqlSchemaReaderUtil.getSchemaFromFile("variables");

    graphQLRequestBody.setQuery(query);
    graphQLRequestBody.setVariables(variables.replace("countryCode", countryCode));

    return webClient.post()
        .bodyValue(graphQLRequestBody)
        .retrieve()
        .bodyToMono(CountryDto.class)
        .block();
  }

  private ExchangeFilterFunction logRequest() {
    return (clientRequest, next) -> {
      log.info("Request: {} {}" + clientRequest.method() + clientRequest.url());
      clientRequest.headers()
          .forEach((name, values) -> values.forEach(value -> log.info("{}={}" + name + value)));
      return next.exchange(clientRequest);
    };
  }
}
