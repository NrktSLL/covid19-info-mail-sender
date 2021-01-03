package com.nrkt.covid19infomailsender.job;

import com.nrkt.covid19infomailsender.service.contract.ContactService;
import com.nrkt.covid19infomailsender.service.mail.CovidCaseMailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class SendMailJob extends QuartzJobBean {

    CovidCaseMailService mailService;
    ContactService contactService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("SendMailJob Running");
        try {
            var contactAllPerson = contactService.getAllPerson();
            if (contactAllPerson.isEmpty()) {
                log.warn("No contact!");
                return;
            }
            contactAllPerson.forEach(mailService::sendMail);
        } catch (Exception ex) {
            log.error(ex.getMessage() + " Control your properties");
        }
    }
}
