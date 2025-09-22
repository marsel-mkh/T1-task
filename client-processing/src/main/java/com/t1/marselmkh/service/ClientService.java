package com.t1.marselmkh.service;

import com.t1.marselmkh.dto.ClientCreateDto;
import com.t1.marselmkh.dto.UserViewDto;
import com.t1.marselmkh.entity.Client;
import com.t1.marselmkh.entity.User;
import com.t1.marselmkh.mapper.ClientMapper;
import com.t1.marselmkh.mapper.UserMapper;
import com.t1.marselmkh.repository.ClientRepository;
import com.t1.marselmkh.repository.UserRepository;
import com.t1.marselmkh.validation.ClientValidation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    public final ClientValidation clientValidation;
    private final ClientMapper clientMapper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserViewDto userRegistration(ClientCreateDto clientCreateDto) {
        log.info("Запуск регистрации пользователя: {}", clientCreateDto.getEmail());

        if (clientValidation.isBlackListed(clientCreateDto.getDocumentId())) {
            log.warn("Попытка регистрации пользователя из черного списка: {}", clientCreateDto.getEmail());
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Client is in blacklist");
        }

        User user = clientMapper.toUserEntity(clientCreateDto);
        user.setPassword(passwordEncoder.encode(clientCreateDto.getPassword()));
        userRepository.save(user);
        log.debug("Пользователь успешно сохранён: id={}, email={}", user.getId(), user.getEmail());

        Client client = clientMapper.toClientEntity(clientCreateDto);
        client.setUserId(user.getId());
        clientRepository.save(client);
        log.debug("Клиент успешно сохранён: id={}, userId={}", client.getId(), user.getId());

        UserViewDto userViewDto = userMapper.toUserViewDto(user);
        log.info("Регистрация завершена успешно: userId={}", user.getId());
        return userViewDto;
    }
}
