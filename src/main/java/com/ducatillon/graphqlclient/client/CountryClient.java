package com.ducatillon.graphqlclient.client;

import com.ducatillon.graphqlclient.data.CountryDto;
import com.ducatillon.graphqlclient.data.GraphqlRequestBody;
import com.ducatillon.graphqlclient.util.GraphqlSchemaReaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class CountryClient {

  private final String url;

  public CountryClient(@Value("https://countries.trevorblades.com/") String url) {
    this.url = url;
  }

  public CountryDto getCountryDetails(final String countryCode) {

    WebClient webClient = WebClient.builder().build();

    GraphqlRequestBody graphQLRequestBody = new GraphqlRequestBody();

    final String query = GraphqlSchemaReaderUtil.getSchemaFromFile("getCountryDetails");
    final String variables = GraphqlSchemaReaderUtil.getSchemaFromFile("variables");

    graphQLRequestBody.setQuery(query);
    graphQLRequestBody.setVariables(variables.replace("countryCode", countryCode));

    return webClient.post()
        .uri(url)
        .bodyValue(graphQLRequestBody)
        .retrieve()
        .bodyToMono(CountryDto.class)
        .block();
  }

}
