package com.senai.market.service;

import com.senai.market.exception.ProductException;
import com.senai.market.model.dto.ProductDto;
import com.senai.market.model.entity.Category;
import com.senai.market.model.entity.Product;
import com.senai.market.model.repository.ProductRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @SneakyThrows
    public void create(ProductDto productDto) {
        if (productDto.getName().isEmpty()) {
            throw new ProductException("Nome do produto é obrigatório!");
        }
        if (productDto.getDescription().isEmpty()) {
            throw new ProductException("Descrição do produto é obrigatório!");
        }
        if (productDto.getPrice().isNaN() || productDto.getPrice() == null) {
            throw new ProductException("Preço do produto deve ser número e é obrigatório!");
        }
        if (productDto.getCategory() == null) {
            throw new ProductException("Categoria do produto é obrigatório!");
        }
        Category category = categoryService.getCategoryByName(productDto.getCategory().getName());
        Product product = new Product(
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                category);
        productRepository.save(product);
    }
}
