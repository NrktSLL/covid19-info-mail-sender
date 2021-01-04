package com.nrkt.covid19infomailsender.config;

import com.nrkt.covid19infomailsender.job.SendMailJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail aTestJob() {
        return JobBuilder
                .newJob(SendMailJob.class)
                .withIdentity("MailSenderJob")
                .withDescription("its a mail sender job")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger aTestTrigger(JobDetail jobADetails) {
        return TriggerBuilder
                .newTrigger()
                .forJob(jobADetails)
                .withIdentity("MailSenderJob")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/30 0/1 * 1/1 * ? *")) //Daily
                //.withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 1/1 * ? *")) //Daily
                .build();
    }
}
