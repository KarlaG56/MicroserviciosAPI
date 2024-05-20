package com.example.ordenesservices.Orders.Infraestructure.Advices;

import com.example.ordenesservices.Orders.Infraestructure.Exception.NotFoundException;
import com.example.ordenesservices.Orders.Infraestructure.DTOS.Responses.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerFactory {
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<BaseResponse> handleChangeSetNotFoundException(NotFoundException e) {
        BaseResponse errorResponse = BaseResponse.builder()
                .message(e.getLocalizedMessage())
                .success(false)
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}