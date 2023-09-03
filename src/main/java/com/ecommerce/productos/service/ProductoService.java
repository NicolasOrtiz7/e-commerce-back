package com.ecommerce.productos.service;

import com.ecommerce.productos.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    List<Producto> findAllProductos();

    Optional<Producto> findByProductoId(Integer id);

    List<Producto> findByNombreContaining(String nombre);

    List<Producto> findByCategoriaNombre(String nombreCategoria);

    List<Producto> findByCategoriaNombreOrderByNombreAsc(String nombreCategoria);

    List<Producto> findByCategoriaNombreOrderByNombreDesc(String nombreCategoria);

    List<Producto> findByCategoriaNombreOrderByPrecioAsc(String nombreCategoria);

    List<Producto> findByCategoriaNombreOrderByPrecioDesc(String nombreCategoria);

    void saveProducto(Producto producto);

    void updateProducto(Producto producto);

    void deleteProducto(Integer id);

}
