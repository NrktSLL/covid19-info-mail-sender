package com.nrkt.covid19infomailsender.receiver;

import com.nrkt.covid19infomailsender.dto.PersonDto;
import com.nrkt.covid19infomailsender.service.mail.CovidCaseMailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class Listener {

    CovidCaseMailService mailService;

    @JmsListener(destination = "${queue.name}")
    public void consume(PersonDto person) {
       mailService.sendMail(person);
    }
}
