package com.ecommerce.productos.service.impl;

import com.ecommerce.productos.entity.DetallesCompra;
import com.ecommerce.productos.repository.DetallesCompraRepository;
import com.ecommerce.productos.service.DetallesCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetallesCompraImpl implements DetallesCompraService {

    @Autowired
    private DetallesCompraRepository repository;


    @Override
    public Optional<DetallesCompra> detallesCompra(Integer id) {
        return repository.findByCompra(id);
    }
}
