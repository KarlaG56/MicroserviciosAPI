package com.example.ordenesservices.Orders.Domain.Entities;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class OrdersProducts {

    private String id;

    @Setter
    private String orderId;

    @Setter
    private String productId;

    private int quantity;

    private float total;

    public OrdersProducts(float price, int quantity) {
        this.id = UUID.randomUUID().toString();
        this.total = price * quantity;
        this.quantity = quantity;
    }

}
