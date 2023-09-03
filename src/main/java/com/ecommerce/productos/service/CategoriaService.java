package com.ecommerce.productos.service;

import com.ecommerce.productos.entity.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    List<Categoria> findAllCategorias();

    List<Categoria> findByNombre(String name);

    Optional<Categoria> findById(Integer id);

    void saveCategoria(Categoria categoria);

    void deleteCategoria(Integer id);

}
