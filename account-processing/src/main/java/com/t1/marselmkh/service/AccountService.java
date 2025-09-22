package com.t1.marselmkh.service;

import com.t1.marselmkh.dto.ClientProductEventDto;
import com.t1.marselmkh.entity.Account;
import com.t1.marselmkh.entity.Status;
import com.t1.marselmkh.mapper.AccountMapper;
import com.t1.marselmkh.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Transactional
    public void createAccount(ClientProductEventDto clientProductEventDto) {
        log.info("Создание аккаунта для клиента: {}", clientProductEventDto.getClientId());
        Account account = accountMapper.toEntity(clientProductEventDto);
        account.setStatus(Status.ACTIVE);
        Account savedAccount = accountRepository.save(account);
        log.info("Аккаунт успешно создан: id={}, статус={}", savedAccount.getId(), savedAccount.getStatus());
    }
}
