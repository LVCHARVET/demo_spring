package com.example.demo.controleurs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exception.GestionErreurs;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(GestionErreurs.class)
    public ResponseEntity<String> handleGestionErreursFonctionnellesException(GestionErreurs ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
