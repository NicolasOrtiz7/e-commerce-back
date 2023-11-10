package com.ecommerce.productos.service.impl;

import com.ecommerce.productos.entity.Categoria;
import com.ecommerce.productos.exception.ResourceNotFound;
import com.ecommerce.productos.exception.UsuarioNotFound;
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
    public Categoria saveCategoria(Categoria categoria) {
        return repository.save(categoria);
    }

    @Override
    public Categoria updateCategoria(Integer id, Categoria categoria) {
        Optional<Categoria> categoriaExists = findById(id);

        if (categoriaExists.isEmpty()){
            throw new ResourceNotFound("No existe esa categoría");
        }

        categoria.setId(id);
        return repository.save(categoria);
    }

    @Override
    public Categoria deleteCategoria(Integer id) {
        Categoria cat = findById(id).orElseThrow(
                ()-> new ResourceNotFound("No se encontró la categoría"));

        repository.deleteById(id);
        return cat;
    }
}
