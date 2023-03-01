package com.ecommerce.productos.service;

import com.ecommerce.productos.entity.Categoria;

import java.util.List;

public interface CategoriaService {

    public List<Categoria> buscarCategorias();

    public List<Categoria> findByNombre(String name);

}
