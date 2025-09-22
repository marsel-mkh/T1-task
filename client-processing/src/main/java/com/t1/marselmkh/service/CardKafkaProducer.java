package com.t1.marselmkh.service;

import com.t1.marselmkh.dto.CardEventDto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CardKafkaProducer {
    private final KafkaTemplate<String, CardEventDto> kafkaTemplate;


    @Value("${kafka.topic.client-cards}")
    private String topicName;

    public CardKafkaProducer(KafkaTemplate<String, CardEventDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCard(CardEventDto requestDto) {
        kafkaTemplate.send(topicName, requestDto);
    }
}
