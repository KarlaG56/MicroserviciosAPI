package com.example.ordenesservices.Orders.Infraestructure.Repositories.JPARepositories;

import com.example.ordenesservices.Orders.Infraestructure.Models.MySQLOrdersProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdersProductsRepository extends JpaRepository<MySQLOrdersProductsModel, String> {
}
