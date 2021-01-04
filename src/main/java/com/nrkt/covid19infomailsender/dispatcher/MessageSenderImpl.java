package com.nrkt.covid19infomailsender.dispatcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrkt.covid19infomailsender.dto.PersonDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class MessageSenderImpl implements MessageSender {

    JmsTemplate jmsTemplate;
    ObjectMapper objectMapper;

    @Value("${queue.name}")
    @NonFinal
    String queueName;

    @Override

    public void publish(List<PersonDto> personDtoList) {
        ExecutorService executor = Executors.newFixedThreadPool(personDtoList.size());
        for (PersonDto person : personDtoList) {
            executor.execute(() -> jmsTemplate.send(queueName, session -> {
                Message message;
                try {
                    message = session.createTextMessage(objectMapper.writeValueAsString(person));
                    message.setStringProperty("_type", "com.nrkt.covid19infomailsender.dto.PersonDto");

                } catch (JsonProcessingException ex) {
                    throw new JMSException(ex.getMessage());
                }
                return Objects.requireNonNull(message);
            }));
        }
    }
}
