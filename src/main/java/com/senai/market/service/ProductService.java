package com.senai.market.service;

import com.senai.market.exception.ProductException;
import com.senai.market.model.dto.CategoryDto;
import com.senai.market.model.dto.ProductDto;
import com.senai.market.model.entity.Category;
import com.senai.market.model.entity.Product;
import com.senai.market.model.repository.ProductRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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

    public List<ProductDto> getAllProducts() {
        List<Product> productsEntitie = productRepository.findAll();
        return productsEntitie.stream().map(product -> {
            return new ProductDto(product.getUuid(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    new CategoryDto(product.getCategory().getName()));
        })
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public void update(UUID id, ProductDto productDto) {
        Optional<Product> productById = productRepository.findByUuid(id);
        if (productById.isEmpty()) {
            logger.log(Level.WARNING, "Usuário tentou atualizar produto com id inexistente nº: " + id);
            throw new ProductException("Não existe produto com esse id!");
        }
        Product product = productById.get();
        if (productDto.getName().isEmpty()) {
            productDto.setName(product.getName());
        }
        if (productDto.getDescription().isEmpty()) {
            productDto.setDescription(product.getDescription());
        }
        if (productDto.getPrice().isNaN() || productDto.getPrice() == null) {
            productDto.setPrice(product.getPrice());
        }
        if (productDto.getCategory() == null) {
            productDto.setCategory(new CategoryDto(product.getCategory().getName()));
        }
        Product productToUpdate = new Product(productDto.getName(), productDto.getDescription(), productDto.getPrice(), categoryService.getCategoryByName(productDto.getCategory().getName()));
        productRepository.save(productToUpdate);
        logger.log(Level.INFO, "Usuário atualizou produto com id " + product.getUuid());
    }

    @SneakyThrows
    public ProductDto getById(UUID id) {
        Optional<Product> byUuid = productRepository.getByUuid(id);
        if (byUuid.isPresent()) {
            logger.log(Level.INFO, "Usuário buscou produto com id " + byUuid.get().getUuid());
            return new ProductDto(byUuid.get().getUuid(), byUuid.get().getName(), byUuid.get().getDescription(), byUuid.get().getPrice(), new CategoryDto(byUuid.get().getCategory().getName()));
        }
        logger.log(Level.WARNING, "Usuário tentou buscar produto com id inexistente nº: " + id);
        throw new ProductException("Não existe produto com esse id!");
    }

    @SneakyThrows
    public ProductDto findMostExpensiveProduct() {
        Optional<Product> productOptional = productRepository.findFirstByOrderByPriceAsc();
        if (productOptional.isPresent()) {
            logger.log(Level.INFO, "Usuário buscou o produto mais caro");
            return new ProductDto(productOptional.get().getUuid(), productOptional.get().getName(), productOptional.get().getDescription(), productOptional.get().getPrice(), new CategoryDto(productOptional.get().getCategory().getName()));
        }
        logger.log(Level.WARNING, "Não existe produto cadastrado");
        throw new ProductException("Não existe produto cadastrado!");
    }
}
