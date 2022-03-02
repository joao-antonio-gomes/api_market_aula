package com.senai.market.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.senai.market.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private UUID uuid;
    private String name;
    private String description;
    private Double price;
    private CategoryDto category;
    private Set<CustomerDto> customers;

    public ProductDto() {
    }

    public ProductDto(UUID uuid, String name, String description, Double price, CategoryDto category) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
}
