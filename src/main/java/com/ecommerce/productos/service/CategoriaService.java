package com.ecommerce.productos.service;

import com.ecommerce.productos.entity.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    List<Categoria> findAllCategorias();

    List<Categoria> findByNombre(String name);

    Optional<Categoria> findById(Integer id);

    Categoria saveCategoria(Categoria categoria);

    Categoria updateCategoria(Integer id, Categoria categoria);

    Categoria deleteCategoria(Integer id);

}
