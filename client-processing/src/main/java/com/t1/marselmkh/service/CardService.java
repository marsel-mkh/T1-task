package com.t1.marselmkh.service;

import com.t1.marselmkh.dto.CardEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardKafkaProducer kafkaProducer;

    public void createCard(CardEventDto cardEventDto) {
        kafkaProducer.sendCard(cardEventDto);
    }
}
