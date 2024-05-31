package com.riwi.educationalManagement.api.error_handler;

import com.riwi.educationalManagement.api.dto.errors.BaseErrorResponse;
import com.riwi.educationalManagement.api.dto.errors.ErrorsResponse;
import com.riwi.educationalManagement.utils.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestController {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public BaseErrorResponse handleBadRequest(
        MethodArgumentNotValidException exception){

        List<String> errors = new ArrayList<>();

        exception.getAllErrors()
                .forEach(error -> errors.add(error.getDefaultMessage()));
            
        return ErrorsResponse.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .status(HttpStatus.BAD_REQUEST.name())
                    .errors(errors)
                    .build();
    }

        @ExceptionHandler(BadRequestException.class)
        public BaseErrorResponse badRequest(BadRequestException exception){
        List<String> errors = new ArrayList<>();

        errors.add(exception.getMessage());

        return ErrorsResponse.builder()
            .code(HttpStatus.BAD_REQUEST.value())
            .status(HttpStatus.BAD_REQUEST.name())
            .errors(errors)
            .build();
    }
}
