package com.nrkt.covid19infomailsender.utils;

import com.nrkt.covid19infomailsender.models.MailContent;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@UtilityClass
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MailContentBuilder {

    public String generateMailContent(MailContent mailContent, TemplateEngine templateEngine) {
        Context context = new Context();
        context.setVariable("TodayCase", mailContent.getTodayCase());
        context.setVariable("TodayDeaths", mailContent.getTodayDeaths());
        context.setVariable("ActiveCase", mailContent.getActive());
        context.setVariable("CriticalCase", mailContent.getCritical());
        context.setVariable("TotalCase", mailContent.getCases());
        context.setVariable("TotalDeaths", mailContent.getDeaths());
        context.setVariable("TotalRecovered", mailContent.getRecovered());
        context.setVariable("Tests", mailContent.getTests());

        return templateEngine.process("MailContent", context);
    }
}