package com.nrkt.covid19infomailsender.service.contract;

import com.nrkt.covid19infomailsender.domain.Person;
import com.nrkt.covid19infomailsender.dto.PersonDto;

import java.util.List;

public interface ContactService {
    PersonDto subscribe(PersonDto person);

    void unsubscribe(String email);

    List<Person> getAllPerson();
}
