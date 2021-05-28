package com.ducatillon.graphqlclient.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CountryDto {

  private CountryData data;

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CountryData {

    private Country country;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Country {

      private String name;
      private String capital;
      private String currency;
    }
  }


}

