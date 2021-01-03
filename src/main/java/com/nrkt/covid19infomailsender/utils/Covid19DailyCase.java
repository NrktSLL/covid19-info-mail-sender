package com.nrkt.covid19infomailsender.utils;

import com.nrkt.covid19infomailsender.enums.CountryEnum;
import com.nrkt.covid19infomailsender.models.CountryCaseInfo;
import com.nrkt.covid19infomailsender.proxy.CountryCoronaInformation;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Covid19DailyCase {

    @SneakyThrows
    public CountryCaseInfo info(CountryEnum country) {
        var dailyCaseInCountry = CountryCoronaInformation.getInformation(country);
        if (dailyCaseInCountry == null) return null;
        return CountryAssembler.countryCaseInfo(dailyCaseInCountry);
    }
}
