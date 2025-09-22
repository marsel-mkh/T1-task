package com.t1.marselmkh.service;

import com.t1.marselmkh.dto.ClientProductEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {
    private final CreditProcessingService creditProcessingService;

    @KafkaListener(topics = "client_credit_products", groupId = "credit-group")
    public void creditConsumer(ClientProductEventDto clientProductEventDto) {
        creditProcessingService.processCredit(clientProductEventDto);
    }
}
