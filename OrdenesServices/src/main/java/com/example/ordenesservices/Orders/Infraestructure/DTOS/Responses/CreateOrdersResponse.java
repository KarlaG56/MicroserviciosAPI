package com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CreateOrdersResponse {
    private OrdersResponse order;
    private List<OrdersProductsResponse> products;
}
