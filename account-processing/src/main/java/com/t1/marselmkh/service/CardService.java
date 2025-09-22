package com.t1.marselmkh.service;

import com.t1.marselmkh.dto.CardEventDto;
import com.t1.marselmkh.entity.Account;
import com.t1.marselmkh.entity.Card;
import com.t1.marselmkh.entity.Status;
import com.t1.marselmkh.exception.AccountBlockedException;
import com.t1.marselmkh.exception.AccountNotFoundException;
import com.t1.marselmkh.mapper.CardMapper;
import com.t1.marselmkh.repository.AccountRepository;
import com.t1.marselmkh.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {

    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    @Transactional
    public void createCard(CardEventDto cardEventDto) {
        log.info("Запрос на создание карты: {}", cardEventDto);

        Card card = cardMapper.toEntity(cardEventDto);
        Account account = accountRepository.findById(card.getAccountId())
                .orElseThrow(() -> {
                    log.error("Аккаунт с id {} не найден", card.getAccountId());
                    return new AccountNotFoundException("Account not found");
                });

        if (Status.BLOCKED.equals(account.getStatus())) {
            log.warn("Попытка создать карту для заблокированного аккаунта: {}", account.getId());
            throw new AccountBlockedException("Cannot create card for blocked account");
        }

        card.setStatus(Status.ACTIVE);
        cardRepository.save(card);

        log.info("Карта успешно создана: id={}, accountId={}", card.getId(), card.getAccountId());
    }
}
