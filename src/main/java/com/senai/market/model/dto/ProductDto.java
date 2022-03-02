package com.senai.market.model.dto;

import com.senai.market.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto {
    private UUID uuid;
    private String name;
    private String description;
    private Double price;
    private CategoryDto category;
    private Set<CustomerDto> customers;
}
