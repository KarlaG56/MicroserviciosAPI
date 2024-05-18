package com.example.ordenesservices.Orders.Domain.Entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
public class Orders {

    private String id;

    private Date orderDate;

    @Setter
    private float total;

    @Setter
    private String status;

    public Orders() {
        this.id = UUID.randomUUID().toString();
        this.orderDate = new Date();
    }
}
