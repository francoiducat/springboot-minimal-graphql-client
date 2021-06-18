package com.ducatillon.graphqlclient.data;

import lombok.Getter;

@Getter
public class CountryDto {

  private CountryData data;

  @Getter
  public class CountryData {

    private Country country;

    @Getter
    public class Country {

      private String name;
      private String capital;
      private String currency;
    }
  }
}

