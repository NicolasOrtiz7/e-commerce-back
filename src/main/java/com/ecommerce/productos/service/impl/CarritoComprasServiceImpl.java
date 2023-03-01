package com.ecommerce.productos.service.impl;

import com.ecommerce.productos.entity.CarritoCompras;
import com.ecommerce.productos.entity.Producto;
import com.ecommerce.productos.repository.CarritoComprasRepository;
import com.ecommerce.productos.service.CarritoComprasService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class CarritoComprasServiceImpl implements CarritoComprasService {

    @Autowired
    private CarritoComprasRepository repository;

    @Override
    public List<CarritoCompras> findAll() {
        return repository.findAll();
    }

    @Override
    public List<CarritoCompras> findByUsuarioId(Long id) {
        return repository.findByUsuarioId(id);
    }

    @Override
    public void deleteByUsuarioId(Long idUsuario) {
        repository.deleteByUsuarioId(idUsuario);
    }

    @Override
    public Long countByUsuarioId(Integer id) {
        return null;
    }

    @Override
    public void deleteProductoById(Long idProducto) {
        repository.deleteProductoById(idProducto);
    }

    @Override
    public void addProducto(CarritoCompras producto) {
        repository.save(producto);
    }


}
