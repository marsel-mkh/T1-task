package com.t1.marselmkh.controller;

import com.t1.marselmkh.dto.ProductDto.ProductCreateDto;
import com.t1.marselmkh.dto.ProductDto.ProductUpdateDto;
import com.t1.marselmkh.dto.ProductDto.ProductViewDto;
import com.t1.marselmkh.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductViewDto> createProduct(
            @RequestBody ProductCreateDto productCreateDto) {
        ProductViewDto product = productService.createProduct(productCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductViewDto> getProduct(@PathVariable Long id) {
        ProductViewDto product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductViewDto> updateProduct(
            @PathVariable Long id, @RequestBody ProductUpdateDto productUpdateDto) {
        ProductViewDto product = productService.updateProduct(id, productUpdateDto);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}