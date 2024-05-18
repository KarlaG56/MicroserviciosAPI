package com.example.ordenesservices.Orders.Infraestructure.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders_products")
@Setter @Getter
public class MySQLOrdersProductsModel {
    @Id
    private String id;

    @Column(nullable = false)
    private String product_id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private float total;

    @ManyToOne
    @JoinColumn(name = "orden_id")
    private MySQLOrdersModel order;
}
