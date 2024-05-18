package com.example.ordenesservices.Orders.Infraestructure.Controllers;

import com.example.ordenesservices.Orders.Application.UseCase.UpdateOrdersUseCase;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Requests.UpdateOrdersRequest;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class UpdateOrdersController {
    @Autowired
    private UpdateOrdersUseCase useCase;

    @PutMapping
    public BaseResponse updateOrders(@RequestBody UpdateOrdersRequest updateOrdersRequest) {
        return useCase.run(updateOrdersRequest);
    }
}
