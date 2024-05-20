package com.example.ordenesservices.Orders.Infraestructure.Repositories;

import com.example.ordenesservices.Orders.Domain.Entities.Orders;
import com.example.ordenesservices.Orders.Domain.Ports.IOrdersPort;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses.BaseResponse;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses.OrdersResponse;
import com.example.ordenesservices.Orders.Infraestructure.Exception.NotFoundException;
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
        return from(repository.findAll().stream().map(this::from).toList(),
                "ordenes listadas correctamente", true, 200);
    }

    @Override
    public OrdersResponse CreateOrder(Orders order) {
        MySQLOrdersModel model = new MySQLOrdersModel();
        model.setId(order.getId());
        model.setOrderDate(order.getOrderDate());
        model.setStatus(order.getStatus());
        model.setTotal(order.getTotal());
        return from(repository.save(model));
    }

    @Override
    public BaseResponse UpdateOrder(String id, String status) {
        MySQLOrdersModel order = findAndEnsureExist(id);
        order.setStatus(status);
        return from(from(repository.save(order)), "Orders found", true, 200);
    }

    private BaseResponse from(OrdersResponse response, String message, boolean success, int code) {
        return BaseResponse.builder()
                .data(response).message(message).success(success).httpStatus(HttpStatus.valueOf(code)).build();
    }

    public MySQLOrdersModel findAndEnsureExist(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Not found order"));
    }

    OrdersResponse from(MySQLOrdersModel ordenModel) {
        OrdersResponse ordenResponse = new OrdersResponse();
        ordenResponse.setId(ordenModel.getId());
        ordenResponse.setStatus(ordenModel.getStatus());
        ordenResponse.setOrderDate(ordenModel.getOrderDate());
        ordenResponse.setTotal(ordenModel.getTotal());
        return ordenResponse;
    }

    BaseResponse from(List<OrdersResponse> responses, String message, boolean success, int code) {
        return BaseResponse.builder()
                .data(responses)
                .message(message)
                .success(success)
                .httpStatus(HttpStatus.valueOf(code))
                .build();
    }



}