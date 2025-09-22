package com.t1.marselmkh.service;

import com.t1.marselmkh.dto.ClientProductDto.ClientProductEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientProductProducer {
    private final KafkaTemplate<String, ClientProductEventDto> kafkaTemplate;

    public void send(String topic, ClientProductEventDto eventDto) {
        kafkaTemplate.send(topic, eventDto);
    }
}
