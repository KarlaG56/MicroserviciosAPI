package com.example.ordenesservices.Orders.Infraestructure.Controllers;

import com.example.ordenesservices.Orders.Application.UseCase.GetOrdersUseCase;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class GetOrdersController {
    @Autowired
    private GetOrdersUseCase useCase;

    @GetMapping
    public BaseResponse getOrders() {
        return useCase.run();
    }
}
