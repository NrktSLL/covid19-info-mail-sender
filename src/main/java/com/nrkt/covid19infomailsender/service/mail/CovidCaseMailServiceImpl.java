package com.nrkt.covid19infomailsender.service.mail;

import com.nrkt.covid19infomailsender.domain.Person;
import com.nrkt.covid19infomailsender.models.MailContent;
import com.nrkt.covid19infomailsender.utils.Covid19DailyCase;
import com.nrkt.covid19infomailsender.utils.MailContentBuilder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CovidCaseMailServiceImpl implements CovidCaseMailService {

    JavaMailSender mailSender;
    TemplateEngine templateEngine;

    @Override
    public void sendMail(Person person) {
        var caseInfo = Covid19DailyCase.info(person.getCountry());

        if(caseInfo==null) throw new NullPointerException("Case Information empty");

        var mailContent = MailContent.builder()
                .active(caseInfo.getActive())
                .cases(caseInfo.getCases())
                .critical(caseInfo.getCritical())
                .deaths(caseInfo.getDeaths())
                .recovered(caseInfo.getRecovered())
                .todayCase(caseInfo.getTodayCases())
                .tests(caseInfo.getTests())
                .todayDeaths(caseInfo.getTodayDeaths())
                .build();

        var mailContentBuilder = MailContentBuilder.generateMailContent(mailContent, templateEngine);

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            helper.setTo(person.getEmail());
            helper.setSubject("Daily Case");

            mimeMessage.setContent(mailContentBuilder, "text/html");
            mailSender.send(mimeMessage);

            log.info("Mail Sent: " + person.getEmail());

        } catch (MessagingException ex) {
            log.error(ex.getMessage());
        }
    }
}
