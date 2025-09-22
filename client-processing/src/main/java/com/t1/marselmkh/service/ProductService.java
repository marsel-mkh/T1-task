package com.t1.marselmkh.service;

import com.t1.marselmkh.dto.ProductDto.ProductCreateDto;
import com.t1.marselmkh.dto.ProductDto.ProductUpdateDto;
import com.t1.marselmkh.dto.ProductDto.ProductViewDto;
import com.t1.marselmkh.entity.Product;
import com.t1.marselmkh.exception.ProductNotFoundException;
import com.t1.marselmkh.mapper.ProductMapper;
import com.t1.marselmkh.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

@Transactional
public ProductViewDto createProduct(ProductCreateDto productDto) {
    log.info("Создание нового продукта: {}", productDto);
    Product product = productMapper.toEntity(productDto);
    productRepository.save(product);
    log.debug("Продукт успешно сохранён: {}", product);
    return productMapper.toDto(product);
}

@Transactional
public ProductViewDto updateProduct(Long id, ProductUpdateDto productDto) {
    log.info("Обновление продукта с id={}: {}", id, productDto);
    Product product = productRepository.findById(id)
            .orElseThrow(() -> {
                log.warn("Продукт с id={} не найден для обновления", id);
                return new ProductNotFoundException("Product not found");
            });

    product.setName(productDto.getName());
    product.setKey(productDto.getKey());

    productRepository.save(product);
    log.debug("Продукт с id={} успешно обновлён: {}", id, product);
    return productMapper.toDto(product);
}

@Transactional(readOnly = true)
public ProductViewDto getProduct(Long id) {
    log.info("Запрос продукта с id={}", id);
    Product product = productRepository.findById(id)
            .orElseThrow(() -> {
                log.warn("Продукт с id={} не найден при запросе", id);
                return new ProductNotFoundException("Product not found");
            });
    log.debug("Продукт с id={} найден: {}", id, product);
    return productMapper.toDto(product);
}

@Transactional
public void deleteProduct(Long id) {
    log.info("Удаление продукта с id={}", id);
    if(!productRepository.existsById(id)) {
        log.warn("Попытка удалить несуществующий продукт с id={}", id);
        throw new ProductNotFoundException("Product not found");
    }
    productRepository.deleteById(id);
    log.debug("Продукт с id={} успешно удалён", id);
}
}
