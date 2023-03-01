package com.ecommerce.productos.service;

import com.ecommerce.productos.entity.CarritoCompras;
import com.ecommerce.productos.entity.Producto;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarritoComprasService {

    List<CarritoCompras> findAll();

    List<CarritoCompras> findByUsuarioId(Long id);

    void deleteByUsuarioId(Long idUsuario);

    Long countByUsuarioId(Integer id);

    void deleteProductoById(Long idProducto);

    void addProducto(CarritoCompras producto);
}
