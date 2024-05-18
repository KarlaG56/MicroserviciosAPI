package com.example.ordenesservices.Orders.Domain.Ports;


import com.example.ordenesservices.Orders.Domain.Entities.OrdersProducts;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses.OrdersProductsResponse;

public interface IOrdersProductsPort {
    OrdersProductsResponse createOrdersProducts(OrdersProducts ordersProducts);
}
