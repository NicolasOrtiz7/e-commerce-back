package com.ecommerce.productos.service;

import com.ecommerce.productos.entity.DetallesCompra;

import java.util.List;
import java.util.Optional;

public interface DetallesCompraService {

    public Optional<DetallesCompra> detallesCompra(Integer id);

}
