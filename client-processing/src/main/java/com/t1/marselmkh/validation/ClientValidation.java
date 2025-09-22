package com.t1.marselmkh.validation;

import com.t1.marselmkh.repository.BlackListRegistryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientValidation {

    private final BlackListRegistryRepository blackListRegistryRepository;

    public boolean isBlackListed(String documentId) {
        return blackListRegistryRepository.existsByDocumentId(documentId);
    }
}
