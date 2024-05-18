package com.example.ordenesservices.Orders.Infraestructure.Repositories;

import com.example.ordenesservices.Orders.Domain.Entities.Orders;
import com.example.ordenesservices.Orders.Domain.Ports.IOrdersPort;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses.BaseResponse;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses.OrdersResponse;
import com.example.ordenesservices.Orders.Infraestructure.Models.MySQLOrdersModel;
import com.example.ordenesservices.Orders.Infraestructure.Repositories.JPARepositories.IOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MySQLOrdersRepository implements IOrdersPort {
    @Autowired
    private IOrdersRepository repository;

    @Override
    public BaseResponse Listar() {
        return from(repository.findAll().stream().map(this::from).toList(), "Orders found", true, 200);
    }

    @Override
    public OrdersResponse CreateOrder(Orders order) {
        MySQLOrdersModel model = new MySQLOrdersModel();
        model.setId(order.getId());
        model.setOrderDate(order.getOrderDate());
        model.setTotal(order.getTotal());
        model.setStatus(order.getStatus());
        System.out.println("Status" + repository.save(model));
        return from(repository.save(model));
    }

    @Override
    public BaseResponse UpdateOrder(String id, String status) {
        MySQLOrdersModel order = findAndEnsureExist(id);
        order.setStatus(status);
        return from(from(repository.save(order)), "Orders found", true, 200);
    }

    @Override
    public MySQLOrdersModel findAndEnsureExist(String id) {
        return repository.findById(id).orElseThrow(()->new RuntimeException("Order not found"));
    }

    private OrdersResponse from(MySQLOrdersModel order) {
        OrdersResponse response = new OrdersResponse();
        response.setId(order.getId());
        response.setStatus(order.getStatus());
        response.setOrderDate(order.getOrderDate());
        response.setTotal(order.getTotal());
        return response;
    }

    private BaseResponse from(List<OrdersResponse> responses, String message, boolean success, int code) {
        BaseResponse response = new BaseResponse();
        response.setData(responses);
        response.setMessage(message);
        response.setSuccess(success);
        response.setHttpStatus(HttpStatus.valueOf(code));
        return response;
    }

    private BaseResponse from(OrdersResponse data, String message, boolean success, int code) {
        BaseResponse response = new BaseResponse();
        response.setData(data);
        response.setMessage(message);
        response.setSuccess(success);
        response.setHttpStatus(HttpStatus.valueOf(code));
        return response;
    }

}
