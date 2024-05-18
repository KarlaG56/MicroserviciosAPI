package com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter @Getter
public class BaseResponse {

    private Object data;
    private String message;
    private Boolean success;
    private HttpStatus httpStatus;

}
