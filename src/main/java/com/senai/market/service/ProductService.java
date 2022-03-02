package com.senai.market.service;

import com.senai.market.exception.ProductException;
import com.senai.market.model.dto.ProductDto;
import com.senai.market.model.entity.Category;
import com.senai.market.model.entity.Product;
import com.senai.market.model.repository.ProductRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProductService {

    private final static Logger logger = Logger.getLogger(String.valueOf(ProductService.class));

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @SneakyThrows
    public void create(ProductDto productDto) {
        if (productDto.getName().isEmpty()) {
            logger.log(Level.WARNING, "Usuário tentou cadastrar produto sem nome");
            throw new ProductException("Nome do produto é obrigatório!");
        }
        if (productDto.getDescription().isEmpty()) {
            logger.log(Level.WARNING, "Usuário tentou cadastrar produto sem descrição");
            throw new ProductException("Descrição do produto é obrigatório!");
        }
        if (productDto.getPrice().isNaN() || productDto.getPrice() == null) {
            logger.log(Level.WARNING, "Usuário tentou cadastrar produto sem preço ou preço inválido");
            throw new ProductException("Preço do produto deve ser número e é obrigatório!");
        }
        if (productDto.getCategory() == null) {
            logger.log(Level.WARNING, "Usuário tentou cadastrar produto sem categoria");
            throw new ProductException("Categoria do produto é obrigatório!");
        }
        Category category = categoryService.getCategoryByName(productDto.getCategory().getName());
        Product product = new Product(
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                category);
        Product productSaved = productRepository.save(product);

        logger.log(Level.INFO, "Usuário cadastrou produto id " + productSaved.getId());
    }

    @SneakyThrows
    public void delete(UUID id) {
        Optional<Product> productById = productRepository.findByUuid(id);
        if (productById.isPresent()) {
            logger.log(Level.INFO, "Usuário deletou produto com id " + productById.get().getUuid());
            productRepository.delete(productById.get());
            return;
        }
        logger.log(Level.WARNING, "Usuário tentou deletar produto com id inexistente nº: " + id);
        throw new ProductException("Não existe produto com esse id!");
    }
}
