package com.nrkt.covid19infomailsender.service.mail;

import com.nrkt.covid19infomailsender.domain.Person;

public interface CovidCaseMailService {
    void sendMail(Person person);
}
