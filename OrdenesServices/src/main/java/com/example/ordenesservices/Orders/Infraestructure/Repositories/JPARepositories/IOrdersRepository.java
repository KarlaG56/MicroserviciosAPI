package com.example.ordenesservices.Orders.Infraestructure.Repositories.JPARepositories;

import com.example.ordenesservices.Orders.Infraestructure.Models.MySQLOrdersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdersRepository extends JpaRepository<MySQLOrdersModel, String> {
}
