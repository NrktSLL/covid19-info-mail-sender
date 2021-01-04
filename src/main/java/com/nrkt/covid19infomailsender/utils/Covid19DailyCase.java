package com.nrkt.covid19infomailsender.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrkt.covid19infomailsender.enums.CountryEnum;
import com.nrkt.covid19infomailsender.dto.CountryCaseInfo;
import com.nrkt.covid19infomailsender.proxy.CountryCoronaInformation;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class Covid19DailyCase {

    @SneakyThrows
    public CountryCaseInfo info(CountryEnum country) {
        var dailyCaseInCountry = CountryCoronaInformation.getInformation(country);
        if (dailyCaseInCountry == null) return null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(dailyCaseInCountry, CountryCaseInfo.class);
        } catch (JsonProcessingException processingException) {
            log.error(processingException.getMessage());
            throw new Exception(processingException.getMessage());
        }
    }
}
