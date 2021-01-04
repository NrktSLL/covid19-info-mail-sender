package com.nrkt.covid19infomailsender.proxy;

import com.nrkt.covid19infomailsender.enums.CountryEnum;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@UtilityClass
public class CountryCoronaInformation {

    @SneakyThrows
    public String getInformation(CountryEnum countryEnum) {
        String url = String.format("https://disease.sh/v3/covid-19/countries/%s?yesterday=true&twoDaysAgo=false&strict=true&allowNull=false", countryEnum);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url , String.class);

        return response.getBody();
    }
}
