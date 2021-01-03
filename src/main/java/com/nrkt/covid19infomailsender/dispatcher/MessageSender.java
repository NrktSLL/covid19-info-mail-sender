package com.nrkt.covid19infomailsender.dispatcher;

import com.nrkt.covid19infomailsender.models.PersonDto;

public interface MessageSender {
    Boolean publish(PersonDto person);
}
