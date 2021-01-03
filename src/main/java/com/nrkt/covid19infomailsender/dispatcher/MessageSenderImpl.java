package com.nrkt.covid19infomailsender.dispatcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrkt.covid19infomailsender.models.PersonDto;
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
    public Boolean publish(PersonDto personDto) {
        jmsTemplate.sendAndReceive(queueName, session -> {
            Message message;
            try {
                message = session.createTextMessage(objectMapper.writeValueAsString(personDto));
                message.setStringProperty("_type", "com.nrkt.covid19infomailsender.models.PersonDto");
            } catch (JsonProcessingException ex) {
                throw new JMSException(ex.getMessage());
            }
            return message;
        });
        return true;
    }
}
