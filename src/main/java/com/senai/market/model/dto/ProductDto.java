package com.senai.market.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto {
    private String name;
    private String description;
    private String price;
    private CategoryDto category;
    private Set<CustomerDto> customers;
}
