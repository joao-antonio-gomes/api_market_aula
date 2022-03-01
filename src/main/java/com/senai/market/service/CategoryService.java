package com.senai.market.service;

import com.senai.market.exception.CategoryException;
import com.senai.market.model.entity.Category;
import com.senai.market.model.repository.CategoryRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @SneakyThrows
    public Category getCategoryByName(String name) {
        Optional<Category> category = this.categoryRepository.findCategoryByName(name);
        if (category.isPresent()) {
            return (Category) category.get();
        }
        throw new CategoryException("Categoria não existe!");
    }
}
