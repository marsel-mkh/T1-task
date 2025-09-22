package com.t1.marselmkh.controller;

import com.t1.marselmkh.dto.CardEventDto;
import com.t1.marselmkh.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity<String> createCard(
            @Valid @RequestBody CardEventDto requestDto) {
        cardService.createCard(requestDto);
        return ResponseEntity.ok("Запрос на создание карты отправлен в Kafka");
    }
}
