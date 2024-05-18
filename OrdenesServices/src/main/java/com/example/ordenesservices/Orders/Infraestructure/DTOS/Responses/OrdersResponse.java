package com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class OrdersResponse {
    private String id;

    private Date orderDate;

    private float total;

    private String status;
}
