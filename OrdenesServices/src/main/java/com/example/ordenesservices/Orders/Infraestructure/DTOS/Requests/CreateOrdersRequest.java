package com.example.ordenesservices.Orders.Infraestructure.DTOS.Requests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CreateOrdersRequest {

    private String status;

    private List<CreateOrdersProductsRequest> products;

}
