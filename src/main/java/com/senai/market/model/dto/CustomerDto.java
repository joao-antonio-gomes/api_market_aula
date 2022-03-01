package com.senai.market.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDto {
    private String name;
    private String cpf;
    private LocalDate birthDate;
}
