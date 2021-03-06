package com.senai.market.controller;

import com.senai.market.model.dto.ProductDto;
import com.senai.market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDto productDto) {
        productService.create(productDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody ProductDto productDto) {
        productService.update(id, productDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        ProductDto product = productService.getById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/findMostExpensiveProduct")
    public ResponseEntity<?> findMostExpensiveProduct() {
        ProductDto product = productService.findMostExpensiveProduct();
        return ResponseEntity.ok(product);
    }
}
