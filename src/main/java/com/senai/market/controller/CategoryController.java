package com.senai.market.controller;

import com.senai.market.model.dto.CategoryDto;
import com.senai.market.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoryDto categoryDto) {
        categoryService.create(categoryDto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
