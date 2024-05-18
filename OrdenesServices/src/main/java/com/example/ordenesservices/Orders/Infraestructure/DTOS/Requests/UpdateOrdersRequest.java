package com.example.ordenesservices.Orders.Infraestructure.DTOS.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateOrdersRequest {
    private String id;

    private String status;
}
