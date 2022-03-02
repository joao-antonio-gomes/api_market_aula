package com.senai.market.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDto {
    private UUID uuid;
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private Set<ProductDto> products;
}
