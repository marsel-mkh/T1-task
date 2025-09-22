package com.t1.marselmkh.controller;

import com.t1.marselmkh.dto.ClientCreateDto;
import com.t1.marselmkh.dto.ClientViewDto;
import com.t1.marselmkh.dto.UserViewDto;
import com.t1.marselmkh.service.ClientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<UserViewDto> userRegistration(
            @Valid @RequestBody ClientCreateDto clientCreateDto) {

        UserViewDto userViewDto = clientService.userRegistration(clientCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userViewDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientViewDto> getClientById(@PathVariable @NotNull Long id) {
        clientService.getClientById(id);
        return ResponseEntity.ok().build();
    }
}
