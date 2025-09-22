package com.t1.marselmkh.service;

import com.t1.marselmkh.dto.ClientProductDto.ClientProductCreateDto;
import com.t1.marselmkh.dto.ClientProductDto.ClientProductEventDto;
import com.t1.marselmkh.dto.ClientProductDto.ClientProductUpdateDto;
import com.t1.marselmkh.dto.ClientProductDto.ClientProductViewDto;
import com.t1.marselmkh.entity.ClientProduct;
import com.t1.marselmkh.entity.Product;
import com.t1.marselmkh.entity.ProductKey;
import com.t1.marselmkh.entity.Status;
import com.t1.marselmkh.exception.ClientProductNotFound;
import com.t1.marselmkh.exception.ProductKeyNotFound;
import com.t1.marselmkh.exception.ProductNotFoundException;
import com.t1.marselmkh.mapper.ClientProductMapper;
import com.t1.marselmkh.repository.ClientProductRepository;
import com.t1.marselmkh.repository.ProductRepository;
import com.t1.marselmkh.validation.ClientProductValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientProductService {
    private final ClientProductRepository clientProductRepository;
    private final ClientProductMapper clientProductMapper;
    private final ProductRepository productRepository;
    private final ClientProductProducer clientProductProducer;
    private final ClientProductValidation clientProductValidation;

    @Value("${kafka.topic.client-products}")
    private String clientProductTopic;

    @Value("${kafka.topic.credit-products}")
    private String clientCreditProductTopic;

    @Transactional
    public ClientProductViewDto create(ClientProductCreateDto clientProductCreateDto) {
        log.info("Создание нового ClientProduct: {}", clientProductCreateDto);
        ClientProduct clientProduct = clientProductMapper.toEntity(clientProductCreateDto);

        clientProductValidation.validateClientProduct(clientProduct.getClientId(), clientProduct.getProductId());

        clientProductRepository.save(clientProduct);
        log.debug("ClientProduct успешно сохранён с id={}", clientProduct.getId());

        sendToKafka(clientProduct);
        ClientProductViewDto dto = clientProductMapper.toDto(clientProduct);
        log.info("Возвращён DTO созданного ClientProduct: {}", dto);
        return dto;
    }

    @Transactional(readOnly = true)
    public ClientProductViewDto get(Long id) {
        log.info("Запрос на получение ClientProduct по id={}", id);
        ClientProduct clientProduct = clientProductRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("ClientProduct с id={} не найден", id);
                    return new ClientProductNotFound("ClientProduct not found");
                });
        ClientProductViewDto dto = clientProductMapper.toDto(clientProduct);
        log.debug("ClientProduct найден и преобразован в DTO: {}", dto);
        return dto;
    }

    @Transactional
    public ClientProductViewDto update(Long id, ClientProductUpdateDto clientProductUpdateDto) {
        log.info("Запрос на обновление ClientProduct id={} данными: {}", id, clientProductUpdateDto);
        ClientProduct clientProduct = clientProductRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("ClientProduct для обновления с id={} не найден", id);
                    return new ClientProductNotFound("ClientProduct not found");
                });

        clientProduct.setStatus(clientProductUpdateDto.getStatus());
        clientProduct.setCloseDate(clientProductUpdateDto.getCloseDate());

        clientProductRepository.save(clientProduct);
        log.debug("ClientProduct id={} успешно обновлён", id);

        sendToKafka(clientProduct);
        ClientProductViewDto dto = clientProductMapper.toDto(clientProduct);
        log.info("Возвращён DTO обновлённого ClientProduct: {}", dto);
        return dto;
    }

    @Transactional
    public void delete(Long id) {
        log.info("Запрос на удаление ClientProduct id={}", id);
        ClientProduct clientProduct = clientProductRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("ClientProduct для удаления с id={} не найден", id);
                    return new ClientProductNotFound("ClientProduct not found");
                });
        clientProduct.setStatus(Status.CLOSED);
        clientProductRepository.deleteById(id);
        log.debug("ClientProduct id={} успешно удалён", id);
        sendToKafka(clientProduct);
    }

    private void sendToKafka(ClientProduct clientProduct) {
        ClientProductEventDto eventDto = clientProductMapper.toEventDto(clientProduct);
        ProductKey productKey = getProductKey(clientProduct.getProductId());
        switch (productKey) {
            case DC, CC, NS, PENS:
                clientProductProducer.send(clientProductTopic, eventDto);
                break;
            case IPO, PC, AC:
                clientProductProducer.send(clientCreditProductTopic, eventDto);
                break;
            default:
                throw new ProductKeyNotFound("Unknown ProductKey: " + productKey);

        }
        log.debug("ClientProductEventDto отправлен в Kafka: {}", eventDto.getProductId());
    }

    private ProductKey getProductKey(String productId) {
        Product product = productRepository.findByProductId(productId).orElseThrow(() -> {
            log.warn("Product с productId={} не найден", productId);
            return new ProductNotFoundException("Product not found");
        });
        return product.getKey();
    }
}
