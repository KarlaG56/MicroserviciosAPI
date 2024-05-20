package com.example.ordenesservices.Orders.Infraestructure.Repositories;

import com.example.ordenesservices.Orders.Domain.Entities.OrdersProducts;
import com.example.ordenesservices.Orders.Domain.Ports.IOrdersPort;
import com.example.ordenesservices.Orders.Domain.Ports.IOrdersProductsPort;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses.OrdersProductsResponse;
import com.example.ordenesservices.Orders.Infraestructure.Exception.NotFoundException;
import com.example.ordenesservices.Orders.Infraestructure.Models.MySQLOrdersModel;
import com.example.ordenesservices.Orders.Infraestructure.Models.MySQLOrdersProductsModel;
import com.example.ordenesservices.Orders.Infraestructure.Repositories.JPARepositories.IOrdersProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MySQLOrdersProductsRepository implements IOrdersProductsPort {

    @Autowired
    IOrdersProductsRepository repository;

    @Autowired
    IOrdersPort ordersRepository;

    @Override
    public OrdersProductsResponse createOrdersProducts(OrdersProducts ordersProducts) {
        MySQLOrdersProductsModel ordersProductsModel = new MySQLOrdersProductsModel();
        ordersProductsModel.setId(ordersProducts.getId());
        ordersProductsModel.setOrder(ordersRepository.findAndEnsureExist(ordersProducts.getOrderId()));
        ordersProductsModel.setProduct_id(ordersProducts.getProductId());
        ordersProductsModel.setQuantity(ordersProducts.getQuantity());
        ordersProductsModel.setTotal(ordersProducts.getTotal());
        return from(repository.save(ordersProductsModel));
    }

    @Override
    public List<OrdersProductsResponse> findProductsByOrderId(String orderId){
        List<MySQLOrdersProductsModel> p = repository.findOrdersProductsByOrderId(orderId);
        for (MySQLOrdersProductsModel m : p) {
            System.out.println(m.getProduct_id());
        }
        List<OrdersProductsResponse> productos = p
                .stream().map(this::from).toList();
        if(productos.isEmpty()){
            throw new NotFoundException("No products found");
        }
        return productos;

    }

    private OrdersProductsResponse from(MySQLOrdersProductsModel save) {
        OrdersProductsResponse response = new OrdersProductsResponse();
        response.setId(save.getId());
        response.setOrderId(save.getOrder().getId());
        response.setProductId(save.getProduct_id());
        response.setQuantity(save.getQuantity());
        response.setTotal(save.getTotal());
        return response;
    }
}
