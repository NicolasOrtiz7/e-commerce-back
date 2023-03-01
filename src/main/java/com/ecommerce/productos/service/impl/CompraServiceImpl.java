package com.ecommerce.productos.service.impl;

import com.ecommerce.productos.entity.Compra;
import com.ecommerce.productos.repository.CompraRepository;
import com.ecommerce.productos.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraServiceImpl implements CompraService {

    @Autowired
    private CompraRepository repository;

    @Override
    public List<Compra> buscarCompras() {
        return repository.findAll();
    }

    @Override
    public List<Compra> buscarUsuarioCompras(Integer id) {
        return repository.findAllByUsuario(id);
    }
}
