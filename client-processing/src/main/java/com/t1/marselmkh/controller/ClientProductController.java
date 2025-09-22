package com.t1.marselmkh.controller;

import com.t1.marselmkh.dto.ClientProductDto.ClientProductCreateDto;
import com.t1.marselmkh.dto.ClientProductDto.ClientProductUpdateDto;
import com.t1.marselmkh.dto.ClientProductDto.ClientProductViewDto;
import com.t1.marselmkh.service.ClientProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/client-products")
@RequiredArgsConstructor
public class ClientProductController {

    private final ClientProductService clientProductService;

    @PostMapping
    public ResponseEntity<ClientProductViewDto> create(
            @RequestBody @Valid ClientProductCreateDto clientProductCreateDto) {

        ClientProductViewDto clientProduct = clientProductService.create(clientProductCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientProductViewDto> get(
            @PathVariable @NotBlank @Positive Long id) {

        ClientProductViewDto clientProduct = clientProductService.get(id);
        return ResponseEntity.ok(clientProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientProductViewDto> update(
            @PathVariable @NotBlank @Positive Long id,
            @RequestBody @Valid ClientProductUpdateDto clientProductUpdateDto) {

        ClientProductViewDto clientProduct = clientProductService.update(id, clientProductUpdateDto);
        return ResponseEntity.ok(clientProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientProductService.delete(id);
        return ResponseEntity.noContent().build();
    }
}