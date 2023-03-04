package com.ecommerce.productos.service.impl;

import com.ecommerce.productos.entity.Categoria;
import com.ecommerce.productos.repository.CategoriaRepository;
import com.ecommerce.productos.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Override
    public List<Categoria> findAllCategorias() {
        return repository.findAll();
    }

    @Override
    public List<Categoria> findByNombre(String name) {
        return repository.findByNombre(name);
    }

    @Override
    public Optional<Categoria> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void saveCategoria(Categoria categoria) {
        repository.save(categoria);
    }

    @Override
    public void deleteCategoria(Integer id) {
        repository.deleteById(id);
    }
}
