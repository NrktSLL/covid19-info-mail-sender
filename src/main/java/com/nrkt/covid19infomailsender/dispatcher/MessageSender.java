package com.nrkt.covid19infomailsender.dispatcher;

import com.nrkt.covid19infomailsender.dto.PersonDto;

import java.util.List;

public interface MessageSender {
    void publish(List<PersonDto> person);
}
