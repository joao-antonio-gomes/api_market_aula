package com.senai.market.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;

    @OneToOne(mappedBy = "category")
    private Product product;

    public Category() {

    }

    public Category(String name) {
        this.name = name;
    }
}
