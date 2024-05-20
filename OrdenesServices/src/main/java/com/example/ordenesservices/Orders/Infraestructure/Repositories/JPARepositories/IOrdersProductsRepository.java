package com.example.ordenesservices.Orders.Infraestructure.Repositories.JPARepositories;

import com.example.ordenesservices.Orders.Infraestructure.Models.MySQLOrdersProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrdersProductsRepository extends JpaRepository<MySQLOrdersProductsModel, String> {
    @Query(value = "SELECT * FROM orders_products WHERE order_id = :orderId ;", nativeQuery = true)
    List<MySQLOrdersProductsModel> findOrdersProductsByOrderId(String orderId);
}
