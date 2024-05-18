package com.example.ordenesservices.Orders.Infraestructure.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class MySQLOrdersModel {
    @Id
    private String id;

    @Column(nullable = false)
    private float total;

    @Column(nullable = false)
    private Date orderDate;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "order")
    private List<MySQLOrdersProductsModel> orders_products;
}
