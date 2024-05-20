package com.example.ordenesservices.Orders.Application.UseCase;

import com.example.ordenesservices.Orders.Domain.Entities.Orders;
import com.example.ordenesservices.Orders.Domain.Entities.OrdersProducts;
import com.example.ordenesservices.Orders.Domain.Ports.IOrdersPort;
import com.example.ordenesservices.Orders.Domain.Ports.IOrdersProductsPort;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Requests.CreateOrdersProductsRequest;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Requests.CreateOrdersRequest;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses.BaseResponse;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses.CreateOrdersResponse;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses.OrdersProductsResponse;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses.OrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateOrdersUseCase {

    @Autowired
    IOrdersPort ordersRepository;

    @Autowired
    IOrdersProductsPort ordersProductsRepository;

    public BaseResponse run(CreateOrdersRequest request){
        Orders order = new Orders();
        order.setStatus(request.getStatus());
        List<OrdersProducts> ordersProducts = request.getProducts().stream().
                map(ordersProduct -> from(order.getId(), ordersProduct)).toList();
        float total = 0;
        for (OrdersProducts products : ordersProducts) {
            total += products.getTotal();
        }
        order.setTotal(total);
        OrdersResponse ordersResponse = ordersRepository.CreateOrder(order);
        List<OrdersProductsResponse> ordersProductsResponse = ordersProducts.stream().map(this::from).toList();
        CreateOrdersResponse createOrdersResponse = new CreateOrdersResponse();
        createOrdersResponse.setOrder(ordersResponse);
        createOrdersResponse.setProducts(ordersProductsResponse);
        return from(createOrdersResponse, "Order created successfully", true, 201);
    }

    private OrdersProducts from(String id, CreateOrdersProductsRequest createOrdersProductsRequest) {
        OrdersProducts ordersProduct = new OrdersProducts(createOrdersProductsRequest.getPrice(),
                createOrdersProductsRequest.getQuantity());
        ordersProduct.setOrderId(id);
        ordersProduct.setProductId(createOrdersProductsRequest.getProductId());
        return ordersProduct;
    }

    private OrdersProductsResponse from(OrdersProducts ordersProducts) {
        return ordersProductsRepository.createOrdersProducts(ordersProducts);
    }

    private BaseResponse from(CreateOrdersResponse createOrdersResponse, String message, boolean success, int status) {
        BaseResponse baseResponse = BaseResponse.builder().build();
        baseResponse.setData(createOrdersResponse.getOrder());
        baseResponse.setMessage(message);
        baseResponse.setSuccess(success);
        baseResponse.setHttpStatus(HttpStatus.valueOf(status));
        return baseResponse;
    }
}
