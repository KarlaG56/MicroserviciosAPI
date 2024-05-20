package com.example.ordenesservices.Orders.Application.UseCase;

import com.example.ordenesservices.Orders.Domain.Ports.IOrdersPort;
import com.example.ordenesservices.Orders.Domain.Ports.IOrdersProductsPort;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Requests.UpdateOrdersRequest;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses.BaseResponse;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses.OrdersProductsResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateOrdersUseCase {

    @Autowired
    private IOrdersPort repository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private IOrdersProductsPort productsRepository;

    public BaseResponse run(UpdateOrdersRequest request){
        String id = request.getId();
        String status = request.getStatus();
        BaseResponse response = repository.UpdateOrder(id, status);
        if (status.equalsIgnoreCase("Enviado")) {
            List<OrdersProductsResponse> productos = productsRepository.findProductsByOrderId(id);
            for(OrdersProductsResponse producto : productos){
                updateProducts(from(producto));
            }
        }
        return response;
    }

    private String from(OrdersProductsResponse response) {
        return "{\n\"id\": " + response.getProductId() + ",\n\"quantity\": " + response.getQuantity() + "\n}";
    }

    private void updateProducts(String request){
        rabbitTemplate.convertAndSend("queue.change.statusOrden", request);
    }
}
