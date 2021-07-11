package com.compassouol.productsms.handler;

import com.compassouol.productsms.exception.BadRequestDetails;
import com.compassouol.productsms.exception.BadRequestException;
import com.compassouol.productsms.exception.NotFoundDetails;
import com.compassouol.productsms.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException brException){
        BadRequestDetails brDetails = new BadRequestDetails( brException.getMessage() ,HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(brDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException nfException){
        NotFoundDetails nfDetails = new NotFoundDetails(nfException.getMessage(), HttpStatus.NOT_FOUND.value());
      return new ResponseEntity<>(nfDetails, HttpStatus.NOT_FOUND);
    }
}
