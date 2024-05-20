package com.example.ordenesservices.Orders.Domain.Ports;


import com.example.ordenesservices.Orders.Domain.Entities.OrdersProducts;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses.OrdersProductsResponse;

import java.util.List;

public interface IOrdersProductsPort {
    OrdersProductsResponse createOrdersProducts(OrdersProducts ordersProducts);

    List<OrdersProductsResponse> findProductsByOrderId(String orderId);
}
