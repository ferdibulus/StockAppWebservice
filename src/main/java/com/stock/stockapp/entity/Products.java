package com.stock.stockapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String Name;
    @Column(name = "barcode")
    private String Barcode;
    @Column(name = "amount")
    private int amount;
    @Column(name = "price")
    private double price;
}
