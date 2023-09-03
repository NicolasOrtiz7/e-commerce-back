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
    public List<Compra> findCompras() {
        return repository.findAll();
    }

    @Override
    public List<Compra> findUsuarioCompras(Integer id) {
        return repository.findAllByUsuario(id);
    }

    @Override
    public void saveCompra(Compra compra) {
        repository.save(compra);
    }

}
