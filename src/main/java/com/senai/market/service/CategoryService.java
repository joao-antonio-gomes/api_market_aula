package com.senai.market.service;

import com.senai.market.exception.CategoryException;
import com.senai.market.model.dto.CategoryDto;
import com.senai.market.model.entity.Category;
import com.senai.market.model.repository.CategoryRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CategoryService {
    private final static Logger logger = Logger.getLogger(String.valueOf(CategoryService.class));

    @Autowired
    private CategoryRepository categoryRepository;

    @SneakyThrows
    public void create(CategoryDto categoryDto) {
        Optional<Category> categoryByName = categoryRepository.findCategoryByName(categoryDto.getName());
        if (categoryByName.isPresent()) {
            throw new CategoryException("Já existe categoria registrada com o nome " + categoryByName.get().getName());
        }
        categoryRepository.save(new Category(categoryDto.getName()));
    }

    @SneakyThrows
    public Category getCategoryByName(String name) {
        Optional<Category> category = this.categoryRepository.findCategoryByName(name);
        if (category.isPresent()) {
            return category.get();
        }
        throw new CategoryException("Categoria não existe!");
    }
}
