package com.t1.marselmkh.service;

import com.t1.marselmkh.dto.CardCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardKafkaProducer kafkaProducer;

    public void createCard(CardCreateDto cardCreateDto) {
        kafkaProducer.sendCard(cardCreateDto);
    }
}
