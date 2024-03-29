package com.ecommerce.productos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNotFound extends RuntimeException {

    public UsuarioNotFound(String message) {
        super(message);
    }

}