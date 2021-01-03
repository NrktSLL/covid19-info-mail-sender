package com.nrkt.covid19infomailsender.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class LocationInfo {
    @JsonProperty("_id")
    Integer id;
    String iso2;
    String iso3;
    Integer lat;
    @JsonProperty("long")
    Integer lon;
    String flag;
}

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CountryCaseInfo {
    long updated;
    String country;
    @JsonProperty("countryInfo")
    LocationInfo locationInfo;
    Integer cases;
    Integer todayCases;
    Integer deaths;
    Integer todayDeaths;
    Integer recovered;
    Integer todayRecovered;
    Integer active;
    Integer critical;
    Integer casesPerOneMillion;
    Integer deathsPerOneMillion;
    Integer tests;
    Integer testsPerOneMillion;
    Integer population;
    String continent;
    Integer oneCasePerPeople;
    Integer oneDeathPerPeople;
    Integer oneTestPerPeople;
    Double activePerOneMillion;
    Double recoveredPerOneMillion;
    Double criticalPerOneMillion;
}

