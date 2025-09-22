package com.t1.marselmkh.service;

import com.t1.marselmkh.dto.CardEventDto;
import com.t1.marselmkh.dto.ClientProductEventDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final AccountService accountService;
    private final TransactionService transactionService;
    private final CardService cardService;

    @KafkaListener(topics = "client_products", groupId = "client-group")
    public void productConsumer(@Valid ClientProductEventDto clientProductEventDto) {
        log.info("Получено событие client_products: {}", clientProductEventDto);
        accountService.createAccount(clientProductEventDto);
    }

    @KafkaListener(topics = "client_transactions", groupId = "account-processing")
    public void consumeTransaction() {
        log.info("Получено событие client_transactions");
        transactionService.createTransaction();
    }

    @KafkaListener(topics = "client_cards", groupId = "account-processing")
    public void consumeCard(@Valid CardEventDto cardEventDto) {
        log.info("Получено событие client_cards: {}", cardEventDto);
        cardService.createCard(cardEventDto);
    }
}
