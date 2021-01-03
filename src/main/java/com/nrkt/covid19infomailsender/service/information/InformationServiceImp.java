package com.nrkt.covid19infomailsender.service.information;

import com.nrkt.covid19infomailsender.dispatcher.MessageSender;
import com.nrkt.covid19infomailsender.enums.CountryEnum;
import com.nrkt.covid19infomailsender.models.PersonDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class InformationServiceImp implements InformationService {

    MessageSender messageSender;

    public Boolean sender(PersonDto person, CountryEnum country) {
        person.setCountry(country);
        return messageSender.publish(person);
     }
}
