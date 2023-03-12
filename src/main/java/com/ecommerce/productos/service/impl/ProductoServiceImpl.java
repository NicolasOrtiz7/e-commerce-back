package com.ecommerce.productos.service.impl;

import com.ecommerce.productos.entity.Producto;
import com.ecommerce.productos.repository.ProductoRepository;
import com.ecommerce.productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository repository;

    @Override
    public List<Producto> findAllProductos() {
        return repository.findAll();
    }

    @Override
    public Optional<Producto> findByProductoId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Producto> findByNombreContaining(String nombre){
        return repository.findByNombreContaining(nombre);
    }

    @Override
    public List<Producto> findByCategoriaNombre(String nombreCategoria) {
        return repository.findByCategoriaNombre(nombreCategoria);
    }

    @Override
    public List<Producto> findByCategoriaNombreOrderByNombreAsc(String nombreCategoria) {
        return repository.findByCategoriaNombreOrderByNombreAsc(nombreCategoria);
    }

    @Override
    public List<Producto> findByCategoriaNombreOrderByNombreDesc(String nombreCategoria) {
        return repository.findByCategoriaNombreOrderByNombreDesc(nombreCategoria);
    }

    @Override
    public List<Producto> findByCategoriaNombreOrderByPrecioAsc(String nombreCategoria) {
        return repository.findByCategoriaNombreOrderByPrecioAsc(nombreCategoria);
    }

    @Override
    public List<Producto> findByCategoriaNombreOrderByPrecioDesc(String nombreCategoria) {
        return repository.findByCategoriaNombreOrderByPrecioDesc(nombreCategoria);
    }


    @Override
    public void saveProducto(Producto producto) {
        repository.save(producto);
    }

    @Override
    public void updateProducto(Producto producto) {
        repository.save(producto);
    }

    @Override
    public void deleteProducto(Integer id) {
        repository.deleteById(id);
    }
}
