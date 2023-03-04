package com.ecommerce.productos.service;

import com.ecommerce.productos.entity.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    public List<Categoria> findAllCategorias();

    public List<Categoria> findByNombre(String name);

    public Optional<Categoria> findById(Integer id);

    public void saveCategoria(Categoria categoria);

    public void deleteCategoria(Integer id);

}
