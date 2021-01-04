package com.nrkt.covid19infomailsender.service.mail;

import com.nrkt.covid19infomailsender.dto.PersonDto;

public interface CovidCaseMailService {
    void sendMail(PersonDto person);
}
