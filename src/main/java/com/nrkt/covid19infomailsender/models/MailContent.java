package com.nrkt.covid19infomailsender.models;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class MailContent {
    Integer todayCase;
    Integer todayDeaths;
    Integer recovered;
    Integer active;
    Integer critical;
    Integer tests;
    Integer cases;
    Integer deaths;
}
