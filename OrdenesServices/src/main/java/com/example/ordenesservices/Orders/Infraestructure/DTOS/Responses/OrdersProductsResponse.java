package com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrdersProductsResponse {
    private String id;

    private String orderId;

    private String productId;

    private int quantity;

    private float total;
}
