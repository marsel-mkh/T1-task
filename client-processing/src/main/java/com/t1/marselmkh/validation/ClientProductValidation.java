package com.t1.marselmkh.validation;

import com.t1.marselmkh.exception.ClientNotFoundException;
import com.t1.marselmkh.exception.ProductNotFoundException;
import com.t1.marselmkh.repository.ClientRepository;
import com.t1.marselmkh.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientProductValidation {
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

    public void validateClientProduct(String clientId, String productId) {
        productExists(productId);
        clientExists(clientId);
    }

    private void productExists(String productId) {
        if (!productRepository.existsByProductId(productId))
            throw new ProductNotFoundException("Product with productId " + productId + " not found");
    }

    private void clientExists(String clientId) {
        if(!clientRepository.existsByClientId(clientId))
            throw new ClientNotFoundException("Client with id " + clientId + " not found");
    }
}
