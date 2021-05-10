package com.ducatillon.graphqlclient;

import com.ducatillon.graphqlclient.client.CountryClient;
import com.ducatillon.graphqlclient.data.CountryDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class GraphqlClientApplication {

  public static void main(String[] args) throws JsonProcessingException {

    ConfigurableApplicationContext context = SpringApplication.run(GraphqlClientApplication.class, args);
    CountryClient client = (CountryClient) context.getBean("countryClient");
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    CountryDto countryDto = client.getCountryDetails("BE");
    log.info(ow.writeValueAsString(countryDto));

  }

}
