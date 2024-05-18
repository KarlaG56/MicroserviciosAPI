package com.example.ordenesservices.Orders.Application.UseCase;

import com.example.ordenesservices.Orders.Domain.Ports.IOrdersPort;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Requests.UpdateOrdersRequest;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateOrdersUseCase {

    @Autowired
    private IOrdersPort repository;

    public BaseResponse run(UpdateOrdersRequest request){
        return repository.UpdateOrder(request.getId(), request.getStatus());
    }
}
