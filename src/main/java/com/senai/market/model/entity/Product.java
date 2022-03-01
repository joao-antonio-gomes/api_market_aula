package com.senai.market.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String description;
    private String price;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "products")
    private Set<Customer> customers;

    public Product() {

    }
}
