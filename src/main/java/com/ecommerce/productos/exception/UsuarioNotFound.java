package com.ecommerce.productos.exception;

public class UsuarioNotFound extends RuntimeException {

    public UsuarioNotFound(String message) {
        super(message);
    }

}