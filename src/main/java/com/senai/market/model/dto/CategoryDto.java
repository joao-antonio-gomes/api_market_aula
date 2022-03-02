package com.senai.market.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CategoryDto {
    private UUID uuid;
    private String name;

    public CategoryDto() {
    }
}
