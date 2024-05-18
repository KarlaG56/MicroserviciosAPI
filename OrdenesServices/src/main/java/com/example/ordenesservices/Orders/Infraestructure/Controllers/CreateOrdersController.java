package com.example.ordenesservices.Orders.Infraestructure.Controllers;

import com.example.ordenesservices.Orders.Application.UseCase.CreateOrdersUseCase;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Requests.CreateOrdersRequest;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class CreateOrdersController {
    @Autowired
    private CreateOrdersUseCase useCase;

    @PostMapping
    public BaseResponse createOrders(@RequestBody CreateOrdersRequest createOrdersRequest) {
        return useCase.run(createOrdersRequest);
    }
}
