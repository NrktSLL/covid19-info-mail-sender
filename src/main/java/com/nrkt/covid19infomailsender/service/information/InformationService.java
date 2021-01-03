package com.nrkt.covid19infomailsender.service.information;

import com.nrkt.covid19infomailsender.enums.CountryEnum;
import com.nrkt.covid19infomailsender.models.PersonDto;

import javax.jms.JMSException;

public interface InformationService {
    Boolean sender(PersonDto person, CountryEnum country) throws JMSException;
}