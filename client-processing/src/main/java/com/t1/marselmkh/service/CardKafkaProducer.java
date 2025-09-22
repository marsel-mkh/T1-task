package com.t1.marselmkh.service;

import com.t1.marselmkh.dto.CardCreateDto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CardKafkaProducer {
    private final KafkaTemplate<String, CardCreateDto> kafkaTemplate;


    @Value("${kafka.topic.client-cards}")
    private String topicName;

    public CardKafkaProducer(KafkaTemplate<String, CardCreateDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCard(CardCreateDto requestDto) {
        kafkaTemplate.send(topicName, requestDto);
    }
}
