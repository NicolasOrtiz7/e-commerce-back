package com.ecommerce.productos.service;

import com.ecommerce.productos.entity.Producto;
import com.ecommerce.productos.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    public List<Producto> findAllProductos();

    public Optional<Producto> findByProductoId(Integer id);

    public List<Producto> findByCategoriaNombre(String nombreCategoria);

    public List<Producto> findByCategoriaNombreOrderByNombreAsc(String nombreCategoria);

    public List<Producto> findByCategoriaNombreOrderByNombreDesc(String nombreCategoria);

    public List<Producto> findByCategoriaNombreOrderByPrecioAsc(String nombreCategoria);

    public List<Producto> findByCategoriaNombreOrderByPrecioDesc(String nombreCategoria);

    public void saveProducto(Producto producto);

    public void updateProducto(Producto producto);

    public void deleteProducto(Integer id);

}
