package com.ecommerce.productos.service.impl;

import com.ecommerce.productos.entity.Categoria;
import com.ecommerce.productos.repository.CategoriaRepository;
import com.ecommerce.productos.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Override
    public List<Categoria> buscarCategorias() {
        return repository.findAll();
    }

    @Override
    public List<Categoria> findByNombre(String name) {
        return repository.findByNombre(name);
    }
}
