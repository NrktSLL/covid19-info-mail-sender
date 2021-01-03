package com.nrkt.covid19infomailsender.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrkt.covid19infomailsender.models.CountryCaseInfo;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class CountryAssembler {

    public CountryCaseInfo countryCaseInfo(String json) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, CountryCaseInfo.class);
        } catch (JsonProcessingException processingException) {
            log.error(processingException.getMessage());
            throw new Exception(processingException.getMessage());
        }
    }
}
