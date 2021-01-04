package com.nrkt.covid19infomailsender.job;

import com.nrkt.covid19infomailsender.dispatcher.MessageSender;
import com.nrkt.covid19infomailsender.dto.PersonDto;
import com.nrkt.covid19infomailsender.service.contract.ContactService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class SendMailJob extends QuartzJobBean {

    ContactService contactService;
    MessageSender messageSender;

    @Override
    protected void executeInternal(JobExecutionContext context) {
        log.info("SendMailJob Running");

        var contactAllPerson = contactService.getAllPerson();
        if (contactAllPerson.isEmpty()) {
            log.warn("No contact!");
            return;
        }

        List<PersonDto> personDtoList = contactAllPerson.stream().map(person -> PersonDto.builder()
                .country(person.getCountry())
                .email(person.getEmail())
                .name(person.getName())
                .build()).collect(Collectors.toList());

        messageSender.publish(personDtoList);
    }
}
