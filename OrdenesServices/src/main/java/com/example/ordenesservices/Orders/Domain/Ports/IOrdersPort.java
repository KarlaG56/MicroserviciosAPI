package com.example.ordenesservices.Orders.Domain.Ports;

import com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses.BaseResponse;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses.OrdersResponse;
import com.example.ordenesservices.Orders.Domain.Entities.Orders;
import com.example.ordenesservices.Orders.Infraestructure.Models.MySQLOrdersModel;

public interface IOrdersPort {
    BaseResponse Listar();
    OrdersResponse CreateOrder(Orders order);
    BaseResponse UpdateOrder(String id, String status);

    MySQLOrdersModel findAndEnsureExist(String id);
}
