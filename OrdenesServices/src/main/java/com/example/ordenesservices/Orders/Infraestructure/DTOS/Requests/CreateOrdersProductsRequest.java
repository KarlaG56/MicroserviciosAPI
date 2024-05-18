package com.example.ordenesservices.Orders.Infraestructure.DTOS.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateOrdersProductsRequest {
    private String productId;
    private int quantity;
    private float price;
}
