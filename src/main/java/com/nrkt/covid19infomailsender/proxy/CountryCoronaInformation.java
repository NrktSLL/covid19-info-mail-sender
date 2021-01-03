package com.nrkt.covid19infomailsender.proxy;

import com.nrkt.covid19infomailsender.enums.CountryEnum;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@UtilityClass
public class CountryCoronaInformation {

    @SneakyThrows
    public String getInformation(CountryEnum countryEnum) {
        String url = String.format("https://disease.sh/v3/covid-19/countries/%s?yesterday=true&twoDaysAgo=false&strict=true&allowNull=false", countryEnum);

        var client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        return response.isSuccessful() ? response.body().string() : null;
    }
}
